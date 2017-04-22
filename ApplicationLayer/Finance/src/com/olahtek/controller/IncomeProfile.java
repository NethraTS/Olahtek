package com.olahtek.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class IncomeProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;
	private PrintWriter out;

	public IncomeProfile() {
		try {
			IncomeProfile.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				IncomeProfile.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			IncomeProfile.restURL = (String) IncomeProfile.serviceProp.get("restURL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JSONObject connection1(JSONObject dataToRest) {
		JSONObject responseError = new JSONObject();
		responseError.put("status", "fail");
		httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = IncomeProfile.restURL + "" + "incomeProfile/";
			HttpPost postRequest = new HttpPost(restURL);
			String Data = dataToRest.toString();
			System.out.println("Rest URL: " + restURL + " Payload: " + Data);
			StringEntity input = new StringEntity(Data);
			input.setContentType("application/json");
			postRequest.setEntity(input);
			HttpResponse httpResponse = httpClient.execute(postRequest);
			if (httpResponse != null) {
				if (httpResponse.getStatusLine().getStatusCode() != 200) {
					System.out.println("Problem in fetching credentials from BitlaMongo");
				}
				String output = null;
				String jsonString = null;
				BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
				while ((output = br.readLine()) != null) {
					jsonString = output;
				}
				br.close();
				JSONObject restResponse = new JSONObject(jsonString);
				httpClient.close();
				System.out.println("Rest response automation: " + restResponse);
				return restResponse;
			} else {
				return responseError;
			}
		} catch (Exception e) {
			return responseError;
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(false);
		String user_id = (String) session.getAttribute("user_id");
		response.setContentType("application/json");
		out = response.getWriter();
		try {
			JSONObject dataToConnection = new JSONObject();
			String formType = request.getParameter("form");
			JSONArray obj1 = null;
			JSONArray obj2 = null;
			if (formType.equals("update")) {
				String incomeRecieved = (request.getParameter("income"));
				String yearRecieved = (request.getParameter("year"));
				String plan_name = request.getParameter("plan_name");
				String profile_name = request.getParameter("profile_name");
				try {
					obj1 = new JSONArray(incomeRecieved);
					obj2 = new JSONArray(yearRecieved);
				} catch (Exception e) {

					e.printStackTrace();
				}
				JSONArray income = new JSONArray();
				for (int i = 0; i < obj1.length(); i++) {
					int value = obj1.getJSONObject(i).getInt("value");
					String year = obj2.getJSONObject(i).getString("label");
					JSONObject obj = new JSONObject();
					obj.put("value", value);
					obj.put("year", year);
					income.put(obj);
				}
				new JSONObject();
				dataToConnection.put("income", income);
				dataToConnection.put("plan_name", plan_name);
				dataToConnection.put("profile_name", profile_name);
				dataToConnection.put("formType", formType);
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("actionType", "update");
				JSONObject restResponse = IncomeProfile.connection1(dataToConnection);
				String status = restResponse.getString("status");
				if (status.equals("success")) {
					out.print(restResponse);
				} else {
					out.print(restResponse);
				}
			} else if (formType.equals("create")) {
				String profile_name = request.getParameter("profile_name");
				dataToConnection.put("profile_name", profile_name);
				dataToConnection.put("actionType", "create");
				dataToConnection.put("user_id", user_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
