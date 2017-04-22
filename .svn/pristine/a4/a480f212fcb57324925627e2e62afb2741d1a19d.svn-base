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

public class CopyPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;
	private PrintWriter out;

	public CopyPlan() {
		try {
			CopyPlan.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				CopyPlan.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			CopyPlan.restURL = (String) CopyPlan.serviceProp.get("restURL");
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
			String restURL = CopyPlan.restURL + "CopyPlan/";
			HttpPost postRequest = new HttpPost(restURL);
			String Data = dataToRest.toString();
			System.out.println("Rest URL: " + restURL + " Payload: " + Data);
			StringEntity input = new StringEntity(Data);
			input.setContentType("application/json");
			postRequest.setEntity(input);
			HttpResponse httpResponse = httpClient.execute(postRequest);
			if (httpResponse != null) {
				if (httpResponse.getStatusLine().getStatusCode() != 200) {
				}
				String output = null;
				String jsonString = null;
				BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
				while ((output = br.readLine()) != null) {
					jsonString = output;
				}
				JSONObject restResponse = new JSONObject(jsonString);
				System.out.println("Rest response: " + restResponse);
				br.close();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession(false);
		String user_id = (String) session.getAttribute("user_id");
		String formType = "copyPlan";
		response.setContentType("application/json");
		out = response.getWriter();
		try {
			if ((request.getParameter("formType") != null) && (request.getParameter("formType") != "")) {
				formType = request.getParameter("formType");
			}
			JSONObject dataToConnection = new JSONObject();
			if (formType.equals("changePlanName")) {
				String planName = request.getParameter("plan_name");
				String newPlanname = request.getParameter("newPlanname");
				String goal_id = request.getParameter("goal_id");
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("plan_name", planName);
				dataToConnection.put("goal_id", goal_id);
				dataToConnection.put("newPlanname", newPlanname);
				dataToConnection.put("formType", formType);
				JSONObject restResponse1 = CopyPlan.connection1(dataToConnection);
				String status = restResponse1.getString("status");
				if (status.equals("success")) {
					out.print(restResponse1);
				} else {
					out.print(restResponse1);
				}
			} else if (formType.equals("deleteGoal")) {
				String planName = request.getParameter("plan_name");
				String goal_id = request.getParameter("goal_id");
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("plan_name", planName);
				dataToConnection.put("goal_id", goal_id);
				dataToConnection.put("formType", formType);
				JSONObject restResponse1 = CopyPlan.connection1(dataToConnection);
				String status = restResponse1.getString("status");
				if (status.equals("success")) {
					out.print(restResponse1);
				} else {
					out.print(restResponse1);
				}
			} else if (formType.equals("deletePlan")) {
				String planName = request.getParameter("plan_name");
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("plan_name", planName);
				dataToConnection.put("formType", formType);
				JSONObject restResponse1 = CopyPlan.connection1(dataToConnection);
				String status = restResponse1.getString("status");
				if (status.equals("success")) {
					out.print(restResponse1);
				} else {
					out.print(restResponse1);
				}
			} else if (formType.equals("deleteIncomeProfile")) {
				String profile_name = request.getParameter("profileName");
				System.out.println("income profile...." + profile_name);
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("profile_name", profile_name);
				dataToConnection.put("formType", formType);
				System.out.println(dataToConnection.toString() + "=========inside servlet=========");
				JSONObject restResponse1 = CopyPlan.connection1(dataToConnection);
				String status = restResponse1.getString("status");
				if (status.equals("success")) {
					out.print(restResponse1);
				} else {
					out.print(restResponse1);
				}
			} else {
				String plan_name = request.getParameter("newPlanName");
				String plan_name_old = request.getParameter("plan_name");
				JSONObject dataToConnection1 = new JSONObject();
				dataToConnection1.put("formType", formType);
				dataToConnection1.put("newPlanName", plan_name);
				dataToConnection1.put("oldPlanName", plan_name_old);
				dataToConnection1.put("user_id", user_id);
				JSONObject restResponse1 = CopyPlan.connection1(dataToConnection1);
				String status = restResponse1.getString("status");
				if (status.equals("success")) {
					out.print(restResponse1);
				} else {
					out.print(restResponse1);
				}
			}
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
