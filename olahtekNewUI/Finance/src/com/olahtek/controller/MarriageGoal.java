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

public class MarriageGoal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;

	public MarriageGoal() {
		super();
		try {
			MarriageGoal.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				MarriageGoal.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			MarriageGoal.restURL = (String) MarriageGoal.serviceProp.get("restURL");
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
			String restURL = MarriageGoal.restURL + "" + "Goalmarriage/";
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
		String actionHomeType = request.getParameter("actionHomeType");
		session = request.getSession(false);
		JSONObject dataToConnection = new JSONObject();
		try {
			System.out.println("inside marriage goal servlet" + actionHomeType);
			session = request.getSession(false);
			String user_id = (String) session.getAttribute("user_id");
			if (actionHomeType.equals("edit")) {
				String goal_id = request.getParameter("goal_id");
				dataToConnection.put("goal_id", goal_id);
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("actionHomeType", actionHomeType);
			} else if (actionHomeType.equals("load")) {
				String goal_id = request.getParameter("goal_id");
				dataToConnection.put("goal_id", goal_id);
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("actionHomeType", actionHomeType);
			} else if (actionHomeType.equals("deleteGoal")) {
				System.out.println("deleting the goal in servlet");
				String goal_id = request.getParameter("goal_id");
				String plan_name = request.getParameter("plan_name");
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("plan_name", plan_name);
				dataToConnection.put("goal_id", goal_id);
				dataToConnection.put("actionHomeType", actionHomeType);
			} else {
				System.out.println("Else in the servlet data...");
				int marriageYear = Integer.parseInt(request.getParameter("marriageyear"));
				long marriageCost = Long.parseLong(request.getParameter("marriagecost"));
				String plan_name = request.getParameter("plan_name");
				int spouseAge = Integer.parseInt(request.getParameter("sage"));
				long spouseIncome = Long.parseLong(request.getParameter("income"));
				long housingExpenses = Long.parseLong(request.getParameter("newMonthlyExpense"));
				long nonHousingExpense = Long.parseLong(request.getParameter("NonHousingExpenses"));
				dataToConnection.put("plan_name", plan_name);
				request.getParameter("goalName");
				String goaltype = "Marriage";
				long annualExcess = 70000;
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("actionHomeType", actionHomeType);
				dataToConnection.put("goalType", goaltype);
				dataToConnection.put("marriageYear", marriageYear);
				dataToConnection.put("marriageCost", marriageCost);
				dataToConnection.put("annualExcess", annualExcess);
				dataToConnection.put("housingExpenses", housingExpenses);
				dataToConnection.put("nonHousingExpense", nonHousingExpense);
				dataToConnection.put("spouseIncome", spouseIncome);
				dataToConnection.put("spouseAge", spouseAge);

				if (actionHomeType.equals("update")) {
					System.out.println("sadasasdas========");
					String goal_id = request.getParameter("goal_id");
					String income = request.getParameter("income");
					String sage = request.getParameter("sage");
					String newMonthlyExpense = request.getParameter("newMonthlyExpense");
					dataToConnection.put("goal_id", goal_id);
					dataToConnection.put("marriageYear", marriageYear);
					dataToConnection.put("marriageCost", marriageCost);
					dataToConnection.put("actionHomeType", actionHomeType);
					dataToConnection.put("spouseIncome", income);
					dataToConnection.put("spouseAge", sage);
					dataToConnection.put("newMonthlyExpense", newMonthlyExpense);
					housingExpenses = Long.parseLong(request.getParameter("housingexpenses1"));
					nonHousingExpense = Long.parseLong(request.getParameter("nonhousingexpense1"));
					dataToConnection.put("housingExpenses", housingExpenses);
					dataToConnection.put("nonHousingExpense", nonHousingExpense);
					System.out.println("--------------=" + housingExpenses);
					System.out.println("--------------=" + nonHousingExpense);
				}

			}
			JSONObject rest_response = MarriageGoal.connection(dataToConnection);
			System.out.println("rest response...." + rest_response);
			String status = rest_response.getString("status");
			if (status.equals("success")) {
				out.println(rest_response);
			}
			else {
				out.println(rest_response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			out.close();
		}
	}
}
