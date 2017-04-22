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

public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;
	private static CloseableHttpClient httpClient2;
	private FileInputStream inputStream;
	private PrintWriter out;

	public UserProfile() {
		try {
			UserProfile.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				inputStream = new FileInputStream(new File(home + "/." + propFile));
				UserProfile.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			UserProfile.restURL = (String) UserProfile.serviceProp.get("restURL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JSONObject connection(JSONObject dataToRest) {
		JSONObject responseError = new JSONObject();
		JSONObject restResponse = new JSONObject();
		responseError.put("status", "fail");
		httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = UserProfile.restURL + "UserProfile/";
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
				restResponse = new JSONObject(jsonString);
				System.out.println("Rest response: " + restResponse);
				restResponse.put("status", "fail");
				return restResponse;
			} else {
				restResponse.put("status", "success");
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

	public static JSONObject connection1(JSONObject dataToRest) {
		JSONObject responseError = new JSONObject();
		JSONObject restResponse = new JSONObject();
		responseError.put("status", "fail");
		httpClient2 = null;
		try {
			httpClient2 = HttpClients.createDefault();
			String restURL = UserProfile.restURL + "UserProfileEdit/";
			HttpPost postRequest = new HttpPost(restURL);
			String Data = dataToRest.toString();
			System.out.println("Rest URL: " + restURL + " Payload: " + Data);
			StringEntity input = new StringEntity(Data);
			input.setContentType("application/json");
			postRequest.setEntity(input);
			HttpResponse httpResponse = httpClient2.execute(postRequest);
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
				restResponse = new JSONObject(jsonString);
				System.out.println("Rest response: " + restResponse);

				return restResponse;
			} else {
				System.out.println("restResponse else in conn1" + restResponse);
				return responseError;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return responseError;
		} finally {
			try {
				if (httpClient2 != null) {
					httpClient2.close();
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
			String formType = request.getParameter("form");
			if (formType.equals("userDetails")) {
				System.out.println("userDetails....>>>");
				JSONObject dataToConnection = new JSONObject();
				dataToConnection.put("user_id", user_id);
				JSONObject restResponse = UserProfile.connection(dataToConnection);
				out.println(restResponse);
			} else if (formType.equals("editincome")) {
				String houseMarketValue = request.getParameter("houseMarketValue");
				String whatIsYourCurrentMortgageRate = request.getParameter("whatIsYourCurrentMortgageRate");
				String remainingMortgage = request.getParameter("remainingMortgage");
				String remainingYearsMortgage = request.getParameter("remainingYearsMortgage");
				String beforeIncomeTax = request.getParameter("beforeIncomeTax");
				String spouseBeforeIncomeTax = request.getParameter("spouseBeforeIncomeTax");
				String monthlyExpense = request.getParameter("monthlyExpense");
				String rentalExpenses = request.getParameter("rentalExpense");
				String otherExpense = request.getParameter("otherExpense");
				JSONObject dataToConnection1 = new JSONObject();
				dataToConnection1.put("formType", formType);
				dataToConnection1.put("userBeforeTaxIncome", beforeIncomeTax);
				dataToConnection1.put("spouseBeforeTaxIncome", spouseBeforeIncomeTax);
				dataToConnection1.put("monthlyExpenses", monthlyExpense);
				dataToConnection1.put("rentalExpenses", rentalExpenses);
				dataToConnection1.put("otherExpenses", otherExpense);
				dataToConnection1.put("houseMarketValue", houseMarketValue);
				dataToConnection1.put("whatIsYourCurrentMortgageRate", whatIsYourCurrentMortgageRate);
				dataToConnection1.put("remainingMortgage", remainingMortgage);
				dataToConnection1.put("remainingYearsMortgage", remainingYearsMortgage);

				dataToConnection1.put("user_id", user_id);
				JSONObject restResponse1 = UserProfile.connection1(dataToConnection1);
				String status = restResponse1.getString("status");
				if (status.equals("success")) {
					out.print(restResponse1);
					System.out.println("inside sucess servlettttt");
				} else {
					out.print(restResponse1);
					System.out.println("inside failure servletttt");
				}
			} else if (formType.equals("editAssets")) {
				System.out.println("hi....");
				System.out.println("editAssets....>>>");
				String cashEquivalent = request.getParameter("cashEquivalent");
				System.out.println("cashEquivalent   "+cashEquivalent);
				String taxableInvestments = request.getParameter("taxableInvestments");
				String nonTaxableInvestments = request.getParameter("nonTaxableInvestments");
				long Taxdeferred = Long.parseLong(request.getParameter("nonTaxableInvestments1"));
				long rothRetirement = Long.parseLong(request.getParameter("nonTaxableInvestments2"));
				long fiveTnp = Long.parseLong(request.getParameter("nonTaxableInvestments3"));
				long realEstate = Long.parseLong(request.getParameter("realestate"));
				JSONObject dataToConnection2 = new JSONObject();
				double user401k=0;
				double userIRA=0;
				double userRothIra=0;
				double user529=0;
				double spouse401k=0;
				double spouseIRA=0;
				double spouseRothIra=0;
				double spousePlan529=0;
				String marriedStatus = request.getParameter("married");
				if(nonTaxableInvestments.equals("Yes")&& marriedStatus.equals("Yes"))
				{
					user401k=Double.parseDouble(request.getParameter("u401"));
					userIRA=Double.parseDouble(request.getParameter("uIRA"));
					userRothIra=Double.parseDouble(request.getParameter("uRothIra"));
					user529=Double.parseDouble(request.getParameter("u529"));
					spouse401k=Double.parseDouble(request.getParameter("s401"));
					spouseIRA=Double.parseDouble(request.getParameter("sIRA"));
					spouseRothIra=Double.parseDouble(request.getParameter("sRothIra"));
					spousePlan529=Double.parseDouble(request.getParameter("s529"));
					
				}
				if(nonTaxableInvestments.equals("Yes")&& marriedStatus.equals("No"))
				{
					user401k=Double.parseDouble(request.getParameter("u401"));
					userIRA=Double.parseDouble(request.getParameter("uIRA"));
					userRothIra=Double.parseDouble(request.getParameter("uRothIra"));
					user529=Double.parseDouble(request.getParameter("u529"));
				}
				System.out.println("user401k==="+user401k);
				System.out.println("userIRA==="+userIRA);
				System.out.println("userRothIra==="+userRothIra);
				System.out.println("user529==="+user529);
				System.out.println("spouse401k==="+spouse401k);
				System.out.println("spouseIRA==="+spouseIRA);
				System.out.println("spouseRothIra==="+spouseRothIra);
				System.out.println("spousePlan529==="+spousePlan529);
				dataToConnection2.put("user401k", user401k);
				dataToConnection2.put("userIRA", userIRA);
				dataToConnection2.put("userRothIra",userRothIra);
				dataToConnection2.put("user529", user529);
				dataToConnection2.put("spouse401k", spouse401k);
				dataToConnection2.put("spouseIRA",spouseIRA);
				dataToConnection2.put("spouseRothIra", spouseRothIra);
				dataToConnection2.put("spousePlan529", spousePlan529);
				dataToConnection2.put("formType", formType);
				dataToConnection2.put("user_id", user_id);
				dataToConnection2.put("cash", cashEquivalent);
				dataToConnection2.put("taxableInvestments", taxableInvestments);
				dataToConnection2.put("nonTaxableInvestments", nonTaxableInvestments);
				dataToConnection2.put("taxdeferred", Taxdeferred);
				dataToConnection2.put("rothRetirement", rothRetirement);
				dataToConnection2.put("taxdeferred", Taxdeferred);
				dataToConnection2.put("fiveTnp", fiveTnp);
				dataToConnection2.put("realEstate", realEstate);
				JSONObject restResponse2 = UserProfile.connection1(dataToConnection2);
				String status = restResponse2.getString("status");
				if (status.equals("success")) {
					response.getWriter().write("success");
				} else {
					response.getWriter().write("fail");
				}
			} else if (formType.equals("getIncomeChart")) {
				System.out.println("getIncomeChart....>>>");
				String profileName = request.getParameter("name");
				JSONObject dataToConnection2 = new JSONObject();
				dataToConnection2.put("formType", formType);
				dataToConnection2.put("user_id", user_id);
				dataToConnection2.put("profileName", profileName);
				JSONObject restResponse2 = UserProfile.connection1(dataToConnection2);
				String status = restResponse2.getString("status");
				if (status.equals("success")) {
					out.println(restResponse2);
				} else {
					response.getWriter().write("fail");
				}
			} else if (formType.equals("editBasicDetails")) {
				System.out.println("editBasicDetails....>>>");
				JSONObject dataToConnection3 = new JSONObject();
				String fname = request.getParameter("fname");
				String city = request.getParameter("city");
				String Email = request.getParameter("Email");
				String age = request.getParameter("age");
				String college_info = request.getParameter("college_info");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				String county = request.getParameter("county");
				String lname = request.getParameter("lname");
				String state = request.getParameter("state");
				String dob = request.getParameter("dob");
				String filingStatus = request.getParameter("filingStatus");
				String marital_status = request.getParameter("married");
				int dependants = 0;
				if ((request.getParameter("dependants") != null) && (request.getParameter("dependants") != "")) {
					dependants = Integer.parseInt(request.getParameter("dependants"));
				}
				if (marital_status.equals("Yes")) {
					String spouse_fname = request.getParameter("spouse_fname");
					String spouse_lname = request.getParameter("spouse_lname");
					String spouse_age = request.getParameter("spouse_age");
					String spouse_dob = request.getParameter("spouse_dob");
					dataToConnection3.put("spouseName", spouse_fname);
					dataToConnection3.put("spouse_lname", spouse_lname);
					dataToConnection3.put("spouseAge", spouse_age);
					dataToConnection3.put("spouse_dob", spouse_dob);
			
				}
				int childCount = Integer.parseInt(request.getParameter("kidscount"));
				dataToConnection3.put("childCount", childCount);
				if (childCount > 0) {
					JSONArray childrensDetails = new JSONArray();
					for (int i = 0; i < childCount; i++) {
						String childName = request.getParameter("kids[" + i + "][name]");
						int childAge = Integer.parseInt(request.getParameter("kids[" + i + "][age]"));
						String flag = request.getParameter("kids[" + i + "][flag]");
						JSONObject childDetails = new JSONObject();
						childDetails.put("name", childName);
						childDetails.put("age", childAge);
						if(childAge<=18)
						{
							flag="";
						}
						childDetails.put("flag", flag);
						childrensDetails.put(childDetails);
					}
					dataToConnection3.put("childs", childrensDetails);
					System.out.println("dataToConnection3====="+dataToConnection3);
				}
				
				dataToConnection3.put("dependants", dependants);
				dataToConnection3.put("filingStatus", filingStatus);
				dataToConnection3.put("formType", formType);
				dataToConnection3.put("marital_status", marital_status);
				dataToConnection3.put("name", fname);
				dataToConnection3.put("city", city);
				dataToConnection3.put("email", Email);
				dataToConnection3.put("age", age);
				dataToConnection3.put("college_info", college_info);
				dataToConnection3.put("address1", address1);
				dataToConnection3.put("address2", address2);
				dataToConnection3.put("county", county);
				dataToConnection3.put("lname", lname);
				dataToConnection3.put("state", state);
				dataToConnection3.put("dob", dob);
				dataToConnection3.put("user_id", user_id);

				JSONObject restResponse3 = UserProfile.connection1(dataToConnection3);
				String status = restResponse3.getString("status");
				System.out.println("entered inside sucess=====" + restResponse3.toString());
				if (status.equals("success")) {
					out.print(restResponse3);
					System.out.println("restResponse3 sucess" + restResponse3);
				} else {
					restResponse3.put("status", "fail");
					System.out.println("inside fail servlet>>>>>");
					out.print(restResponse3);
				}
			}
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
