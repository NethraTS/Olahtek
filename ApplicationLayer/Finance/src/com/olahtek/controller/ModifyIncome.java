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

public class ModifyIncome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;
	private PrintWriter out;

	public ModifyIncome() {
		try {
			ModifyIncome.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				ModifyIncome.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			ModifyIncome.restURL = (String) ModifyIncome.serviceProp.get("restURL");
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
			String restURL = ModifyIncome.restURL + "incomeProfile/";
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
			dataToConnection.put("user_id", user_id);
			String formType = request.getParameter("form");
			if (formType.equals("saveExpenses")) {
				JSONObject dataToConnection1 = new JSONObject();
				dataToConnection1.put("housingExpense", Double.parseDouble(request.getParameter("housingExpense")));
				dataToConnection1.put("nonHousingExpense", Double.parseDouble(request.getParameter("nonHousingExpense")));
				dataToConnection1.put("formType", formType);
				dataToConnection1.put("user_id", user_id);
				dataToConnection1.put("actionType", "saveExpenses");
				dataToConnection1.put("profile_name", request.getParameter("profile_name"));
				JSONObject restResponse1 = ModifyIncome.connection1(dataToConnection1);
				String status = restResponse1.getString("status");
				if (status.equals("success")) {
					out.print(restResponse1);
				} else {
					out.print(restResponse1);
				}
			} else if (formType.equals("update")) {
				String incomeRecieved = (request.getParameter("income"));
				String yearRecieved = (request.getParameter("year"));
				String spouseIncomeRecieved = (request.getParameter("spouseIncome"));
				String profile_name = request.getParameter("profile_name");
				int plan_count = Integer.parseInt(request.getParameter("planCount"));
				JSONArray obj1 = new JSONArray(incomeRecieved);
				JSONArray obj2 = new JSONArray(yearRecieved);
				System.out.println("D3 incomeRecieved::"+incomeRecieved);
				System.out.println("D3 obj1::"+obj1);
				JSONArray income = new JSONArray();
				for (int i = 0; i < obj1.length(); i++) {
					int value = obj1.getJSONObject(i).getInt("userIncome");
					Double dvalue = (double) value;
					String year = obj2.getJSONObject(i).getString("label");
					JSONObject obj = new JSONObject();
					obj.put("value", dvalue);
					obj.put("year", year);
					income.put(obj);
				}
				JSONArray spouseIncome = new JSONArray();
				String marital_status = (request.getParameter("marital_status"));
				if (marital_status.equals("Yes")) {
					JSONArray obj3 = new JSONArray(spouseIncomeRecieved);
					for (int i = 0; i < obj1.length(); i++) {
						int value = obj1.getJSONObject(i).getInt("spouseIncome");
						Double dvalue = (double) value;
						String year = obj2.getJSONObject(i).getString("label");
						JSONObject obj = new JSONObject();
						obj.put("value", dvalue);
						obj.put("year", year);
						spouseIncome.put(obj);
					}
				}
				JSONObject dataToConnection1 = new JSONObject();
				dataToConnection1.put("income", income);
				if (marital_status.equals("Yes")) {
					dataToConnection1.put("spouse_income", spouseIncome);
					JSONArray spouseplotArray = new JSONArray(request.getParameter("spousePlot"));
					dataToConnection1.put("spousePlot",spouseplotArray );
				}
				if (plan_count > 0) {
					String plan_name = request.getParameter("plan_name");
					dataToConnection1.put("plan_name", plan_name);
				}
				dataToConnection1.put("formType", formType);
				dataToConnection1.put("user_id", user_id);
				dataToConnection1.put("actionType", "update");
				dataToConnection1.put("profile_name", profile_name);
				JSONArray userplotArray = new JSONArray(request.getParameter("userPlot"));
				dataToConnection1.put("userPlot",userplotArray );
				JSONObject restResponse1 = ModifyIncome.connection1(dataToConnection1);
				String status = restResponse1.getString("status");
				if (status.equals("success")) {
					out.print(restResponse1);
				} else {
					out.print(restResponse1);
				}
			} else if (formType.equals("create")) {
				String selectedProfileName = request.getParameter("selectedProfileName");
				String incomeRecieved = (request.getParameter("income"));
				String yearRecieved = (request.getParameter("year"));
				String spouseIncomeRecieved = (request.getParameter("spouseIncome"));
				String profile_name = request.getParameter("profile_name");
				int plan_count = Integer.parseInt(request.getParameter("planCount"));
				JSONArray obj1 = new JSONArray(incomeRecieved);
				JSONArray obj2 = new JSONArray(yearRecieved);
				JSONArray income = new JSONArray();
				for (int i = 0; i < obj1.length(); i++) {
					int value = obj1.getJSONObject(i).getInt("userIncome");
					//Double dvalue = (double) value;
					String year = obj2.getJSONObject(i).getString("label");
					JSONObject obj = new JSONObject();
					obj.put("value", value);
					obj.put("year", year);
					income.put(obj);
				}
				JSONArray spouseIncome = new JSONArray();
				String marital_status = (request.getParameter("marital_status"));
				if (marital_status.equals("Yes")) {
					JSONArray obj3 = new JSONArray(spouseIncomeRecieved);
					for (int i = 0; i < obj1.length(); i++) {
						int value = obj1.getJSONObject(i).getInt("spouseIncome");
						//Double dvalue = (double) value;
						String year = obj2.getJSONObject(i).getString("label");
						JSONObject obj = new JSONObject();
						obj.put("value", value);
						obj.put("year", year);
						spouseIncome.put(obj);
					}
				}
				JSONObject dataToConnection1 = new JSONObject();
				dataToConnection1.put("income", income);
				if (marital_status.equals("Yes")) {
					dataToConnection1.put("spouse_income", spouseIncome);
					JSONArray spouseplotArray = new JSONArray(request.getParameter("spousePlot"));
					dataToConnection1.put("spousePlot", spouseplotArray);
				}
				if (plan_count > 0) {
					String plan_name = request.getParameter("plan_name");
					dataToConnection1.put("plan_name", plan_name);
				}
				dataToConnection1.put("actionType", "create");
				dataToConnection1.put("actionType", formType);
				dataToConnection1.put("user_id", user_id);
				dataToConnection1.put("profile_name", profile_name);
				dataToConnection1.put("selectedProfileName", selectedProfileName);
				JSONArray userplotArray = new JSONArray(request.getParameter("userPlot"));
				dataToConnection1.put("userPlot",userplotArray );
				System.out.println("dataToConnection1:: "+dataToConnection1);
				JSONObject restResponse1 = ModifyIncome.connection1(dataToConnection1);
				String status = restResponse1.getString("status");
				if (status.equals("success")) {
					out.print(restResponse1);
				} else {
					out.print(restResponse1);
				}
			} else if (formType.equals("getIncomeProfile")) {
				String profile_name = request.getParameter("profile_name");
				JSONObject dataToConnection1 = new JSONObject();
				dataToConnection1.put("formType", formType);
				dataToConnection1.put("user_id", user_id);
				dataToConnection1.put("actionType", "getIncomeProfile");
				dataToConnection1.put("profile_name", profile_name);
				JSONObject restResponse1 = ModifyIncome.connection1(dataToConnection1);
				//System.out.println("Bala ......"+restResponse1);
				//String status = restResponse1.getString("status");
				out.print(restResponse1);
				
			} else {
				response.getWriter().write("fail");
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
