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

public class MarriageGoalSpouseIncome extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;

	public MarriageGoalSpouseIncome() {
		super();
		try {
			MarriageGoalSpouseIncome.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				MarriageGoalSpouseIncome.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			MarriageGoalSpouseIncome.restURL = (String) MarriageGoalSpouseIncome.serviceProp.get("restURL");
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
			String restURL = MarriageGoalSpouseIncome.restURL + "Goalmarriage/";
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
		JSONObject dataToConnection = new JSONObject();
		try {
			String goal_id = request.getParameter("goal_id");
			int expensesStartYear = Integer.parseInt(request.getParameter("expensesStart"));
			int expensesEndYear = Integer.parseInt(request.getParameter("expensesEnd"));
			int spouseAge = Integer.parseInt(request.getParameter("sage"));
			long spouseIncome = Long.parseLong(request.getParameter("income"));
			long newMonthlyExpense = Long.parseLong(request.getParameter("newMonthlyExpense"));
			String actionHomeType = "updateIncomeAndExpense";
			String user_id = (String) session.getAttribute("user_id");
			long housingExpenses = Long.parseLong(request.getParameter("newMonthlyExpense"));
			long nonHousingExpense = Long.parseLong(request.getParameter("NonHousingExpenses"));
			dataToConnection.put("housingExpenses", housingExpenses);
			dataToConnection.put("nonHousingExpense", nonHousingExpense);
			System.out.println("--------------=" + request.getParameter("newMonthlyExpense"));
			System.out.println("--------------=" + request.getParameter("NonHousingExpenses"));
			dataToConnection.put("user_id", user_id);
			dataToConnection.put("expensesStartYear", expensesStartYear);
			dataToConnection.put("expensesEndYear", expensesEndYear);
			dataToConnection.put("actionHomeType", actionHomeType);
			dataToConnection.put("spouseIncome", spouseIncome);
			dataToConnection.put("spouseAge", spouseAge);
			dataToConnection.put("newMonthlyExpense", newMonthlyExpense);
			dataToConnection.put("goal_id", goal_id);
			JSONObject rest_response = MarriageGoalSpouseIncome.connection(dataToConnection);
			String status = rest_response.getString("status");
			if (status.equals("success")) {
				out.println(rest_response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
