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
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;



public class Portfolioportfoliomanagement extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;
	private static CloseableHttpResponse httpResponse;
	private static BufferedReader br;
	private PrintWriter out;

	public Portfolioportfoliomanagement() {
		try {
			Portfolioportfoliomanagement.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				Portfolioportfoliomanagement.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			Portfolioportfoliomanagement.restURL = (String) Portfolioportfoliomanagement.serviceProp.get("restURL");
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
			String restURL = Portfolioportfoliomanagement.restURL + "investmentPortfolio/";
			HttpPost postRequest = new HttpPost(restURL);
			String Data = dataToRest.toString();
			System.out.println("Rest URL: " + restURL + " Payload: " + Data);
			StringEntity input = new StringEntity(Data);
			input.setContentType("application/json");
			postRequest.setEntity(input);
			httpResponse = httpClient.execute(postRequest);
			if (httpResponse != null) {
				if (httpResponse.getStatusLine().getStatusCode() != 200) {
					System.out.println("Problem in fetching credentials from BitlaMongo");
				}
				String output = null;
				String jsonString = null;
				br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
				while ((output = br.readLine()) != null) {
					jsonString = output;
				}
				JSONObject restResponse = new JSONObject(jsonString);
				System.out.println("Aparna  Rest response: " + restResponse);
				return restResponse;
			} else {
				return responseError;
			}
		} catch (Exception e) {
			return responseError;
		} finally {
			/*try {
				if (httpClient != null) {
					httpClient.close();
				}
				if (httpResponse != null) {
					httpResponse.close();
				}
				if (br != null) {
					br.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
	}

	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("inside dopost    ");
		session = request.getSession(false);
		JSONObject rest_response=new JSONObject();
		String formType="notDuringRegistration";
		String user_id = (String) session.getAttribute("user_id");
		//System.out.println("hello null"+request.getParameter("formType"));
		/*if(!request.getParameter("formType").equals("notDuringRegistration"))
		{*/
		formType=request.getParameter("formType");
		//}
		System.out.println("hello null    "+formType);
		if(formType.equals("initialSubmit"))
		{
			String riskScore = request.getParameter("riskScore");
			String riskFactor = request.getParameter("riskFactor");
			System.out.println("%%^^&&"+request.getParameter("age"));
			int age = Integer.parseInt(request.getParameter("age"));
			String filingStatusPort=request.getParameter("filingStatusPort");
			System.out.println(" risk factor in servelet"+riskFactor);
			System.out.println("riskscore in servlet..."+riskScore);
			JSONObject dataToConnection = new JSONObject();
			dataToConnection.put("user_id", user_id);
			dataToConnection.put("riskScore", riskScore);
			dataToConnection.put("formType", formType);
			dataToConnection.put("riskFactor", riskFactor);
			dataToConnection.put("age", age);
			//Portfolioportfoliomanagement.connection(dataToConnection);
			rest_response = Portfolioportfoliomanagement.connection(dataToConnection);
			System.out.println("if....1   "+rest_response);
		}
		else if(formType.equals("duringRegistration"))
		{
			String riskScore = request.getParameter("riskScore");
			System.out.println("riskscore in servlet..."+riskScore);
			JSONObject dataToConnection = new JSONObject();
			dataToConnection.put("user_id", user_id);
			dataToConnection.put("riskScore", riskScore);
			dataToConnection.put("formType", formType);
			//Portfolioportfoliomanagement.connection(dataToConnection);
			rest_response = Portfolioportfoliomanagement.connection(dataToConnection);
			
		}
		else if(formType.equals("calulateBasedonGrowthRate"))
		{
			System.out.println("Calculate forthe tabel");
		
			Double growthRate = Double.parseDouble(request.getParameter("growthRate"));
			Double portfolioDividend = Double.parseDouble(request.getParameter("portfolioDividend"));
			Double portfolioInterest = Double.parseDouble(request.getParameter("portfolioInterest"));
		String filingStatusPort=request.getParameter("filingStatusPort");
		System.out.println("filingStatusPort======"+filingStatusPort);
			JSONObject dataToConnection = new JSONObject();
			dataToConnection.put("growthRate", growthRate);
			dataToConnection.put("user_id", user_id);
			dataToConnection.put("portfolioInterest", portfolioInterest);
			dataToConnection.put("formType", formType);
			dataToConnection.put("portfolioDividend", portfolioDividend);
			dataToConnection.put("filingStatusPort",filingStatusPort);
			//Portfolioportfoliomanagement.connection(dataToConnection);
			rest_response = Portfolioportfoliomanagement.connection(dataToConnection);
			System.out.println("if....2  "+rest_response);
		}
		else
		{
			System.out.println("else p[art.....");
		String plan_name = null;
		if ((request.getParameter("plan_name") != "") && (request.getParameter("plan_name") != null)) {
			plan_name = request.getParameter("plan_name");
		}
		String riskFactor = request.getParameter("riskFactor");
		String riskScore = request.getParameter("riskScore");
		response.setContentType("application/json");
		
		JSONObject dataToConnection = new JSONObject();
		dataToConnection.put("plan_name", plan_name);
		dataToConnection.put("user_id", user_id);
		dataToConnection.put("riskScore", riskScore);
		dataToConnection.put("riskFactor", riskFactor);
		dataToConnection.put("formType", formType);
		rest_response = Portfolioportfoliomanagement.connection(dataToConnection);
		//out.println(rest_response);
		System.out.println("if....3  "+rest_response);
		if (out != null) {
			out.close();
		}

		}
		
		System.out.println("servlet response---->>> my "+rest_response);
		out = response.getWriter();
		out.println(rest_response);

		
	}

}
