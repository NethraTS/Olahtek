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
import org.json.JSONObject;

public class CreateFinPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;
	private PrintWriter out;

	public CreateFinPlan() {
		try {
			CreateFinPlan.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				CreateFinPlan.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			CreateFinPlan.restURL = (String) CreateFinPlan.serviceProp.get("restURL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JSONObject connection(JSONObject dataToRest) {
		JSONObject responseError = new JSONObject();
		responseError.put("status", "fail");
		httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = CreateFinPlan.restURL + "FinPlan/";
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
				System.out.println("Rest response automation: " + restResponse);
				return restResponse;
			} else {
				return responseError;
			}
		} catch (Exception e) {
			return responseError;
		} finally {
			try {
				httpClient.close();
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
			System.out.println("dataToConnection=finplan==aparna="+dataToConnection);
			dataToConnection.put("user_id", user_id);
			String formType = request.getParameter("form");
			if (formType.equals("createNewPlan")) {
				String plan_name = request.getParameter("planname");
				String profile_name = request.getParameter("profile_name");
				JSONObject dataToConnection1 = new JSONObject();
				dataToConnection1.put("formType", formType);
				dataToConnection1.put("plan_name", plan_name);
				dataToConnection1.put("profile_name", profile_name);
				dataToConnection1.put("user_id", user_id);
				JSONObject restResponse1 = CreateFinPlan.connection(dataToConnection1);
				System.out.println("restResponse1===finplan response1=aparna"+restResponse1);
				String status = restResponse1.getString("status");
				if (status.equals("success")) {
					out.print(restResponse1);
				} else {
					out.print(restResponse1);
				}
			} else if (formType.equals("showPlan")) {
				String plan_name = request.getParameter("planName");
				JSONObject dataToConnection1 = new JSONObject();
				dataToConnection1.put("formType", formType);
				dataToConnection1.put("planName", plan_name);
				dataToConnection1.put("user_id", user_id);
				JSONObject restResponse1 = CreateFinPlan.connection(dataToConnection1);
				System.out.println("restResponse1===finplan response2=aparna"+restResponse1);
				String status = restResponse1.getString("status");
				if (status.equals("success")) {
					out.print(restResponse1);
				} else {
					out.print(restResponse1);
				}
			} else if (formType.equals("fetchPlanDetails")) {
				String plan_name = request.getParameter("planName");
				JSONObject dataToConnection1 = new JSONObject();
				dataToConnection1.put("formType", formType);
				dataToConnection1.put("plan_name", plan_name);
				dataToConnection1.put("user_id", user_id);
				JSONObject restResponse1 = CreateFinPlan.connection(dataToConnection1);
				System.out.println("restResponse1===finplan response3=aparna"+restResponse1);
				String status = restResponse1.getString("status");
				if (status.equals("success")) {
					out.print(restResponse1);
				} else {
					out.print(restResponse1);
				}
			} else if (formType.equals("fetchGoalStatus")) {
				String hashes = request.getParameter("data");
				JSONObject dataToConnection1 = new JSONObject();
				dataToConnection1.put("formType", formType);
				dataToConnection1.put("plan_name", hashes);
				dataToConnection1.put("user_id", user_id);
				JSONObject restResponse = CreateFinPlan.connection(dataToConnection1);
				System.out.println("restResponse1===finplan response4=aparna"+restResponse);
				String status = restResponse.getString("status");
				if (status.equals("success")) {
					out.print(restResponse);
				} else {
					out.print(restResponse);
				}
			} else {
				response.getWriter().write("fail");
			}
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}