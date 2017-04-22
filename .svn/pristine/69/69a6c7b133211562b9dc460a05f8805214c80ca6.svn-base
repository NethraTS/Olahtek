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

public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;

	public Report() {
		super();
		try {
			Report.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				Report.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			Report.restURL = (String) Report.serviceProp.get("restURL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JSONObject connection(JSONObject dataToRest) {
		JSONObject obj = new JSONObject();
		obj.put("status", "fail");
		httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = Report.restURL + "Report/";
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
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return obj;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		session = request.getSession(false);
		String user_id = (String) session.getAttribute("user_id");
		try {
			String actionHomeType = null;
			JSONObject dataToConnection = new JSONObject();
			if (request.getParameter("actionHomeType") != null) {
				actionHomeType = request.getParameter("actionHomeType");
			}
			if (actionHomeType.equals("OnLoad")) {
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("actionHomeType", actionHomeType);
				JSONObject rest_response = Report.connection(dataToConnection);
				String status = rest_response.getString("status");
				System.out.println("###Bala"+rest_response);
				if (status.equals("success")) {
					out.println(rest_response);
				} else {
					out.print(rest_response);
				}
			} else if (actionHomeType.equals("goaldetails")) {
				String planName = request.getParameter("planName");
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("planName", planName);
				dataToConnection.put("actionHomeType", actionHomeType);
				JSONObject rest_response = Report.connection(dataToConnection);
				String status = rest_response.getString("status");
				if (status.equals("success")) {
					out.println(rest_response);
				} else {
					out.print(rest_response);
				}
			}else if(actionHomeType.equals("getIncomeChart")){

				System.out.println("getIncomeChart....>>>");
				String profileName = request.getParameter("name");
				JSONObject dataToConnection2 = new JSONObject();
				dataToConnection2.put("formType", actionHomeType);
				dataToConnection2.put("user_id", user_id);
				dataToConnection2.put("profileName", profileName);
				JSONObject rest_response = Report.connection(dataToConnection2);
				String status = rest_response.getString("status");
				if (status.equals("success")) {
					out.println(rest_response);
				} else {
					response.getWriter().write("fail");
				}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
