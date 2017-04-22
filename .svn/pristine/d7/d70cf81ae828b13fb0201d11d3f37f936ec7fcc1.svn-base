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

public class GoalCollegeEducation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	public static String zillowKey = "zws-id=X1-ZWz1a3at0fzi17_2599m";
	HttpSession session;
	long spouseBeforeTaxIncome = 0;
	private static CloseableHttpClient httpClient;

	public GoalCollegeEducation() {
		try {
			GoalCollegeEducation.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				GoalCollegeEducation.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			GoalCollegeEducation.restURL = (String) GoalCollegeEducation.serviceProp.get("restURL");
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
			String restURL = GoalCollegeEducation.restURL + "GoalCollegeEducation/";
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		session = request.getSession(false);
		JSONObject dataToConnection = new JSONObject();
		try {
			session = request.getSession(false);
			String user_id = (String) session.getAttribute("user_id");
			String userName = (String) session.getAttribute("userName");
			String actionHomeType = request.getParameter("actionHomeType");
			String plan_name = request.getParameter("plan_name");
			String kidname = request.getParameter("Goalname");
			dataToConnection.put("plan_name", plan_name);
			if (actionHomeType.equals("edit")) {
				String goal_id = request.getParameter("goal_id");
				//String  RealAnnualCollegeCostOverTime= request.getParameter("RealAnnualCollegeCostOverTime");
				//int PercentageCollegeCost = Integer.parseInt(request.getParameter("PercentageCollegeCost"));	
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("goal_id", goal_id);
				//dataToConnection.put("RealAnnualCollegeCostOverTime", RealAnnualCollegeCostOverTime);
				//System.out.println("raviiiiiiiiiii222222222"+RealAnnualCollegeCostOverTime);
				//dataToConnection.put("PercentageCollegeCost", PercentageCollegeCost);
				//System.out.println("raviiiiiiiiiii222222222"+PercentageCollegeCost);
				dataToConnection.put("actionHomeType", actionHomeType);
			} else if (actionHomeType.equals("deleteGoal")) {
				System.out.println("deleting the goal in servlet");
				String goal_id = request.getParameter("goal_id");
				plan_name = request.getParameter("plan_name");
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("plan_name", plan_name);
				dataToConnection.put("goal_id", goal_id);
				dataToConnection.put("actionHomeType", actionHomeType);
			} else if (actionHomeType.equals("getYearArray")) {
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("kidName", kidname);
				dataToConnection.put("actionHomeType", actionHomeType);
			} else if (actionHomeType.equals("getKidData")) {
				request.getParameter("goalName");
				String fin_Id = request.getParameter("fin_Id");
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("fin_Id", fin_Id);
				dataToConnection.put("actionHomeType", actionHomeType);
			}
			else if (actionHomeType.equals("getPlan529Amount")) {
				request.getParameter("goalName");
				String fin_Id = request.getParameter("fin_Id");
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("fin_Id", fin_Id);
				dataToConnection.put("actionHomeType", actionHomeType);
				Integer kidCollegeYear = Integer.parseInt(request.getParameter("KidCollegeYear"));
				dataToConnection.put("kidCollegeYear", kidCollegeYear);
			}else {
				Integer kidCollegeYear = Integer.parseInt(request.getParameter("KidCollegeYear"));
				String calPlan529 = request.getParameter("plan529demo");
				dataToConnection.put("calPlan529", calPlan529);
				if (calPlan529.equals("false")) {
					double plan529Saved = Double.parseDouble(request.getParameter("plan529Saved"));
					dataToConnection.put("plan529Saved", plan529Saved);
				}
				request.getParameter("goalName");
				request.getParameter("fin_Id");
				String collegeType = request.getParameter("CollegeType");
				long collegeEducationAmount = Long.parseLong(request.getParameter("CollegeEducationAmount"));
				String  RealAnnualCollegeCostOverTime= request.getParameter("RealAnnualCollegeCostOverTime");
				//String  collegeCostToServlet= request.getParameter("collegeCostToServlet");
				//System.out.println("raviKiran###"+RealAnnualCollegeCostOverTime);
				int percentageCollegeCost = Integer.parseInt(request.getParameter("PercentageCollegeCost"));	
				int collegeEducationAmountPercentage = Integer.parseInt(request.getParameter("CollegeEducationAmountPercentage"));
				String collegeCostToServlet=request.getParameter("collegeCostToServlet");
				System.out.println("college cost"+collegeCostToServlet);
		        JSONArray collegeGoalAmountData = new JSONArray(collegeCostToServlet);
		        System.out.println("collegeGoalAmountData "+collegeGoalAmountData+"percentageCollegeCost >> "+percentageCollegeCost);
				String goaltype = "College Education";
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("actionHomeType", actionHomeType);
				dataToConnection.put("userName", userName);
				dataToConnection.put("kidCollegeYear", kidCollegeYear);
				dataToConnection.put("goalType", goaltype);
				dataToConnection.put("kidName", kidname);
				dataToConnection.put("collegeType", collegeType);
				dataToConnection.put("collegeEducationAmount", collegeEducationAmount);
				dataToConnection.put("RealAnnualCollegeCostOverTime", RealAnnualCollegeCostOverTime);
				dataToConnection.put("percentageCollegeCost", percentageCollegeCost);
				dataToConnection.put("collegeEducationAmountPercentage", collegeEducationAmountPercentage);
				dataToConnection.put("collegeGoalAmountData", collegeGoalAmountData);

			}
			if (actionHomeType.equals("update")) {
				request.getParameter("houseInsuranceAmount");
				String collegeType = request.getParameter("CollegeType");
				String Goalname = request.getParameter("Goalname");
				String  RealAnnualCollegeCostOverTime= request.getParameter("RealAnnualCollegeCostOverTime");
				int PercentageCollegeCost = Integer.parseInt(request.getParameter("PercentageCollegeCost"));	
				String goal_id = request.getParameter("goal_id");
				String collegeCostToServlet=request.getParameter("collegeCostToServlet");
				System.out.println("college cost update"+collegeCostToServlet);
		        JSONArray collegeGoalAmountData = new JSONArray(collegeCostToServlet);
				System.out.println("college cost update in servlet..// "+collegeGoalAmountData);
				dataToConnection.put("collegeGoalAmountData", collegeGoalAmountData);
				dataToConnection.put("goal_id", goal_id);
				dataToConnection.put("kidname", kidname);
				dataToConnection.put("RealAnnualCollegeCostOverTime", RealAnnualCollegeCostOverTime);
				//System.out.println("raviiiiiiiiiii"+RealAnnualCollegeCostOverTime);
				dataToConnection.put("PercentageCollegeCost", PercentageCollegeCost);
				//System.out.println("raviiiiiiiiiii"+PercentageCollegeCost);
				dataToConnection.put("PercentageCollegeCost", Goalname);
				//System.out.println("raviiiiiiiiiii"+Goalname);
				dataToConnection.put("collegeType", collegeType);
				//System.out.println("raviiiiiiiiiii"+collegeType);
				String calPlan529 = request.getParameter("plan529demo");
				dataToConnection.put("calPlan529", calPlan529);
				if (calPlan529.equals("false")) {
					double plan529Saved = Double.parseDouble(request.getParameter("plan529Saved"));
					System.out.println("Bala...Servlet"+plan529Saved);
					dataToConnection.put("plan529Saved", plan529Saved);
				}
			}
			JSONObject rest_response = GoalCollegeEducation.connection(dataToConnection);
			String status = rest_response.getString("status");
			if (status.equals("success")) {
				out.println(rest_response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.close();
	}
}
