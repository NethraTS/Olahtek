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

public class RetirementGoal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	long spouseBeforeTaxIncome = 0;
	private static CloseableHttpClient httpClient;

	public RetirementGoal() {
		try {
			RetirementGoal.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				RetirementGoal.serviceProp.load(inputStream);
				inputStream.close();
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			RetirementGoal.restURL = (String) RetirementGoal.serviceProp.get("restURL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JSONObject connection(JSONObject dataToRest) {
		httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = RetirementGoal.restURL + "retirementGoal/";
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
				JSONObject obj = new JSONObject();
				obj.put("status", "fail");
				return obj;
			}
		} catch (Exception e) {
			JSONObject obj = new JSONObject();
			obj.put("status", "fail");
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
		JSONObject restResponse = null;
		int lifeExpectancy = 0;
		int retirementAge = 0;
		long otherRetirementIncome = 0;
		long spouseOtherRetirementIncome = 0;
		long retirement_expense = 0;
		int spouseRetirementAge = 0;
		double userSavedAmount = 0;
		double userContributeAmount = 0;
		double spouseContributeAmount = 0;
		double userMatchContribution = 0;
		double spouseMatchContribution = 0;
		double userContributionInIRA = 0;
		double spouseContributionInIRA = 0;
		double userContributionInRothIRA = 0;
		double spouseContributionInRothIRA = 0;
		double userSponsoredRothAcn = 0;
		double userSavedInIRA = 0;
		double userSavedInRothIRA = 0;
		double userSavedRothAcn = 0;
		double spouseSavedAmount = 0;
		double spouseSavedInIRA = 0;
		double spouseSavedInRothIRA = 0;
		double spouseSavedRothAcn = 0;
		double userCap=0;
		double spouseCap=0;
		int user401kCont=0;
		int spouse401kCont=0;

		JSONObject dataToConnection = new JSONObject();
		String status = "fail";
		try {
			session = request.getSession(false);
			String user_id = (String) session.getAttribute("user_id");
			String formType = request.getParameter("form");
			String fin_name = request.getParameter("fin_name");
			String goal_id = request.getParameter("goal_id");
			String marital_status = request.getParameter("marital_status");
			dataToConnection.put("formType", formType);
			dataToConnection.put("plan_name", fin_name);
			dataToConnection.put("user_id", user_id);
			dataToConnection.put("goal_id", goal_id);
			dataToConnection.put("marital_status", marital_status);
			if ((request.getParameter("Retirementage") != "") && (request.getParameter("Retirementage") != null)) {
				lifeExpectancy = Integer.parseInt(request.getParameter("Retirementage")) + 20;
			}
			if (formType.equals("next")) {
				if ((request.getParameter("LifeExpectancy") != "") && (request.getParameter("LifeExpectancy") != null)) {
					lifeExpectancy = Integer.parseInt(request.getParameter("LifeExpectancy"));
					dataToConnection.put("lifeExpectancy", lifeExpectancy);
				}
				dataToConnection.put("lifeExpectancy", lifeExpectancy);
				if ((request.getParameter("Retirementage") != "") && (request.getParameter("Retirementage") != null)) {
					retirementAge = Integer.parseInt(request.getParameter("Retirementage"));
					dataToConnection.put("retirementAge", retirementAge);
				}
				if ((request.getParameter("marital_status") != "") && (request.getParameter("marital_status") != null)) {
					if (request.getParameter("marital_status").equals("Yes")) {
						if ((request.getParameter("sRetirementage") != "") && (request.getParameter("sRetirementage") != null)) {
							spouseRetirementAge = Integer.parseInt(request.getParameter("sRetirementage"));
						}
						if (request.getParameter("user401Amount").equals("yes")) {
							userCap=Double.parseDouble(request.getParameter("amountCap"));
							userSavedAmount = Long.parseLong(request.getParameter("amountEmpSav"));
							if(request.getParameter("userCont401").equals("noFunc"))
							{
							userContributeAmount = Long.parseLong(request.getParameter("amountEmpCont"));
							}
							userMatchContribution = Long.parseLong(request.getParameter("amountECost"));
							user401kCont=1;
						}
						userSavedInIRA = Long.parseLong(request.getParameter("iraSaved"));
						if (request.getParameter("userIraAmount").equals("yes1")) {
							userContributionInIRA = Long.parseLong(request.getParameter("iraContribute"));
							dataToConnection.put("userContributionInIRA", userContributionInIRA);
						}
						userSavedInRothIRA = Long.parseLong(request.getParameter("rothIra"));
						if (request.getParameter("userRothIraAmount").equals("yes2")) {
							userContributionInRothIRA = Long.parseLong(request.getParameter("rothIraContribute"));
							dataToConnection.put("userContributionInRothIRA", userContributionInRothIRA);
						}
						if (request.getParameter("rothContribution").equals("yes3")) {
							userSavedRothAcn = Long.parseLong(request.getParameter("rothAccSave"));
							userSponsoredRothAcn = Long.parseLong(request.getParameter("rothAccContribute"));
						}
						if (request.getParameter("spouse401Amount").equals("yes4")) {
							spouseCap=Double.parseDouble(request.getParameter("spouseCapAmount"));
							spouseSavedAmount = Long.parseLong(request.getParameter("spouseAmount"));
							if(request.getParameter("spouseCont401").equals("noFuncSpouse"))
								{
							spouseContributeAmount = Long.parseLong(request.getParameter("spouseCont"));
								}
							spouseMatchContribution = Long.parseLong(request.getParameter("amountSCost"));
							spouse401kCont=1;
						}
						spouseSavedInIRA = Long.parseLong(request.getParameter("sIraSaved"));
						if (request.getParameter("spouseIraAmount").equals("yes5")) {
							spouseContributionInIRA = Long.parseLong(request.getParameter("sIraContribute"));
							dataToConnection.put("spouseContributionInIRA", spouseContributionInIRA);
						}
						spouseSavedInRothIRA = Long.parseLong(request.getParameter("sRothIra"));
						if (request.getParameter("spouseRothAmount").equals("yes6")) {
							spouseContributionInRothIRA = Long.parseLong(request.getParameter("sRothIraContribute"));
							dataToConnection.put("spouseContributionInRothIRA", spouseContributionInRothIRA);
						}
						if (request.getParameter("spouseRothSaved").equals("yes7")) {
							spouseSavedRothAcn = Long.parseLong(request.getParameter("sRothSave"));
							Long.parseLong(request.getParameter("sRothContribute"));
						}
					} else {
						if (request.getParameter("user401Amount").equals("yes")) {
							userCap=Double.parseDouble(request.getParameter("amountCap"));
							userSavedAmount = Long.parseLong(request.getParameter("amountEmpSav"));
							if(request.getParameter("userCont401").equals("noFunc"))
							{
							userContributeAmount = Long.parseLong(request.getParameter("amountEmpCont"));
							}							
							userMatchContribution = Long.parseLong(request.getParameter("amountECost"));
							user401kCont=1;
						}
						userSavedInIRA = Long.parseLong(request.getParameter("iraSaved"));
						if (request.getParameter("userIraAmount").equals("yes1")) {
							userContributionInIRA = Long.parseLong(request.getParameter("iraContribute"));
							dataToConnection.put("userContributionInIRA", userContributionInIRA);
						}
						userSavedInRothIRA = Long.parseLong(request.getParameter("rothIra"));
						if (request.getParameter("userRothIraAmount").equals("yes2")) {
							userContributionInRothIRA = Long.parseLong(request.getParameter("rothIraContribute"));
							dataToConnection.put("userContributionInRothIRA", userContributionInRothIRA);
						}
						if (request.getParameter("rothContribution").equals("yes3")) {
							userSavedRothAcn = Long.parseLong(request.getParameter("rothAccSave"));
							userSponsoredRothAcn = Long.parseLong(request.getParameter("rothAccContribute"));
						}
					}
				}
				dataToConnection.put("userCap", userCap);
				dataToConnection.put("spouse401kCont", spouse401kCont);
				dataToConnection.put("user401kCont", user401kCont);
				dataToConnection.put("userSavedAmount", userSavedAmount);
				dataToConnection.put("userContributeAmount", userContributeAmount);
				dataToConnection.put("userMatchContribution", userMatchContribution);
				dataToConnection.put("userSavedInIRA", userSavedInIRA);
				dataToConnection.put("userSavedInRothIRA", userSavedInRothIRA);
				dataToConnection.put("userSavedRothAcn", userSavedRothAcn);
				dataToConnection.put("userSponsoredRothAcn", userSponsoredRothAcn);
				dataToConnection.put("userSponsoredRothAcn", userSponsoredRothAcn);
				dataToConnection.put("spouseCap", spouseCap);
				dataToConnection.put("spouseSavedAmount", spouseSavedAmount);
				dataToConnection.put("spouseContributeAmount", spouseContributeAmount);
				dataToConnection.put("spouseMatchContribution", spouseMatchContribution);
				dataToConnection.put("spouseRetirementAge", spouseRetirementAge);
				dataToConnection.put("spouseSavedInIRA", spouseSavedInIRA);
				dataToConnection.put("spouseSavedInRothIRA", spouseSavedInRothIRA);
				dataToConnection.put("spouseSavedRothAcn", spouseSavedRothAcn);
				restResponse = RetirementGoal.connection(dataToConnection);
				status = restResponse.getString("status");
			} else if (formType.equals("onload")) {
				dataToConnection.put("formType", formType);
				dataToConnection.put("plan_name", fin_name);
				dataToConnection.put("user_id", user_id);
				restResponse = RetirementGoal.connection(dataToConnection);
				status = restResponse.getString("status");
			} else if (formType.equals("update")) {
				if ((request.getParameter("LifeExpectancy") != "") && (request.getParameter("LifeExpectancy") != null)) {
					lifeExpectancy = Integer.parseInt(request.getParameter("LifeExpectancy"));
					dataToConnection.put("lifeExpectancy", lifeExpectancy);
				}
				if ((request.getParameter("Retirementage") != "") && (request.getParameter("Retirementage") != null)) {
					retirementAge = Integer.parseInt(request.getParameter("Retirementage"));
					dataToConnection.put("retirementAge", retirementAge);
				}
				if (request.getParameter("user401Amount").equals("yes")) {
					userCap=Double.parseDouble(request.getParameter("amountCap"));
					userSavedAmount = Long.parseLong(request.getParameter("amountEmpSav"));
					if(request.getParameter("userCont401").equals("noFunc"))
					{
					userContributeAmount = Long.parseLong(request.getParameter("amountEmpCont"));
					}					
					userMatchContribution = Long.parseLong(request.getParameter("amountECost"));
					user401kCont=1;
					dataToConnection.put("userCap", userCap);
					dataToConnection.put("user401kCont", user401kCont);
					dataToConnection.put("userSavedAmount", userSavedAmount);
					dataToConnection.put("userContributeAmount", userContributeAmount);
					dataToConnection.put("userMatchContribution", userMatchContribution);
				}
				if ((request.getParameter("retirementExpense") != "") && (request.getParameter("retirementExpense") != null)) {
					retirement_expense = Long.parseLong(request.getParameter("retirementExpense"));
					dataToConnection.put("retirement_expense", retirement_expense);
				}
				userSavedInIRA = Long.parseLong(request.getParameter("iraSaved"));
				dataToConnection.put("userSavedInIRA", userSavedInIRA);
				if (request.getParameter("userIraAmount").equals("yes1")) {
					userContributionInIRA = Long.parseLong(request.getParameter("iraContribute"));
					dataToConnection.put("userContributionInIRA", userContributionInIRA);
				}
				userSavedInRothIRA = Long.parseLong(request.getParameter("rothIra"));
				dataToConnection.put("userSavedInRothIRA", userSavedInRothIRA);
				if (request.getParameter("userRothIraAmount").equals("yes2")) {
					userContributionInRothIRA = Long.parseLong(request.getParameter("rothIraContribute"));
					dataToConnection.put("userContributionInRothIRA", userContributionInRothIRA);
				}
				if (request.getParameter("spouse401Amount").equals("yes4")) {
					spouseCap=Double.parseDouble(request.getParameter("spouseCapAmount"));
					spouseSavedAmount = Long.parseLong(request.getParameter("spouseAmount"));
					if(request.getParameter("spouseCont401").equals("noFuncSpouse"))
					{
				spouseContributeAmount = Long.parseLong(request.getParameter("spouseCont"));
					}					
					spouseMatchContribution = Long.parseLong(request.getParameter("amountSCost"));
					spouse401kCont=1;
					dataToConnection.put("spouseCap", spouseCap);
					dataToConnection.put("spouseSavedAmount", spouseSavedAmount);
					dataToConnection.put("spouseContributeAmount", spouseContributeAmount);
					dataToConnection.put("spouseMatchContribution", spouseMatchContribution);
					dataToConnection.put("spouse401kCont", spouse401kCont);
				}
				spouseSavedInIRA = Long.parseLong(request.getParameter("sIraSaved"));
				dataToConnection.put("spouseSavedInIRA", spouseSavedInIRA);
				if (request.getParameter("spouseIraAmount").equals("no5")) {
					spouseContributionInIRA = Long.parseLong(request.getParameter("sIraContribute"));
					dataToConnection.put("spouseContributionInIRA", spouseContributionInIRA);
				}
				spouseSavedInRothIRA = Long.parseLong(request.getParameter("sRothIra"));
				dataToConnection.put("spouseSavedInRothIRA", spouseSavedInRothIRA);
				if (request.getParameter("spouseRothAmount").equals("yes6")) {
					spouseContributionInRothIRA = Long.parseLong(request.getParameter("sRothIraContribute"));
					dataToConnection.put("spouseContributionInRothIRA", spouseContributionInRothIRA);
				}
				if ((request.getParameter("SpouseRI") != null) && (request.getParameter("SpouseRI") != "")) {
					dataToConnection.put("spouseLifeExpectancy", lifeExpectancy);
					spouseOtherRetirementIncome = Long.parseLong(request.getParameter("SpouseRI"));
					dataToConnection.put("spouseOtherRetirementIncome", spouseOtherRetirementIncome);
				}
				if ((request.getParameter("SpouseRA") != null) && (request.getParameter("SpouseRA") != "")) {
					spouseRetirementAge = Integer.parseInt(request.getParameter("SpouseRA"));
					dataToConnection.put("spouseRetirementAge", spouseRetirementAge);
				}
				if ((request.getParameter("otherincome") != null) && (request.getParameter("otherincome") != "")) {
					otherRetirementIncome = Long.parseLong(request.getParameter("otherincome"));
					dataToConnection.put("otherRetirementIncome", otherRetirementIncome);
				}
				restResponse = RetirementGoal.connection(dataToConnection);
				restResponse = RetirementGoal.connection(dataToConnection);
				status = restResponse.getString("status");
			} else if (formType.equals("edit")) {
				dataToConnection.put("goal_id", goal_id);
				restResponse = RetirementGoal.connection(dataToConnection);
				status = restResponse.getString("status");
			}
			if (status.equals("success")) {
				out.print(restResponse);
			} else {
				response.getWriter().write("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
