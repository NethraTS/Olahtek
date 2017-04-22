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

public class FDICalculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;
	private static CloseableHttpResponse httpResponse;
	private PrintWriter out;
	private static BufferedReader br;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FDICalculator() {
		try {
			FDICalculator.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				FDICalculator.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			FDICalculator.restURL = (String) FDICalculator.serviceProp.get("restURL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JSONObject connection(JSONObject dataToRest) {
		JSONObject restResponseToLogin = new JSONObject();
		httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = FDICalculator.restURL + "FDICalculator/";
			HttpPost postRequest = new HttpPost(restURL);
			String Data = dataToRest.toString();
			System.out.println("Rest URL: " + restURL + " Payload: " + Data);
			StringEntity input = new StringEntity(Data);
			input.setContentType("application/json");
			postRequest.setEntity(input);
			httpResponse = httpClient.execute(postRequest);
			if (httpResponse.getStatusLine().getStatusCode() != 200) {
			}
			String restResponse = null;
			String output = null;
			br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
			while ((output = br.readLine()) != null) {
				restResponse = output;
			}
			restResponseToLogin = new JSONObject(restResponse);
			System.out.println("Rest response: " + restResponseToLogin);
		} catch (Exception e) {
			restResponseToLogin.put("status", "fail");
			e.printStackTrace();
			return restResponseToLogin;
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
				if (br != null) {
					br.close();
				}
				if (httpResponse != null) {
					httpResponse.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return restResponseToLogin;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject dataToConnection = new JSONObject();
		response.setContentType("text/html;charset=UTF-8");
		session = request.getSession(false);
		out = response.getWriter();
		try {
			String action = request.getParameter("action");
			// dataToConnection.put("action", action);

			// String user_id = (String) session.getAttribute("user_id");
			System.out.println("Action type passed-->> " + action);
			if (action.equals("FDI")) {
				String user_id = request.getParameter("user_id");
				dataToConnection.put("action", action);
				String state = request.getParameter("state");
				String Filling_Status = request.getParameter("filingStatus");
				System.out.println("Filling_Status-->> " + Filling_Status);
				String Gross_Annual_Income = request.getParameter("homeValue");
				System.out.println("Gross_Annual_Income-->>" + Gross_Annual_Income);
				String Personal_Exemption = request.getParameter("personalexe");
				System.out.println("Personal_Exemption-->> " + Personal_Exemption);
				dataToConnection.put("state", state);
				dataToConnection.put("user_id", user_id);
				dataToConnection.put("Filling_Status", Filling_Status);
				dataToConnection.put("Gross_Annual_Income", Gross_Annual_Income);
				dataToConnection.put("Personal_Exemption", Personal_Exemption);

				JSONObject rest_response = FDICalculator.connection(dataToConnection);
				System.out.println("rest_response-->> " + rest_response);
				String status = rest_response.getString("status");
				if (status.equals("success")) {
					out.println(rest_response);
				}
			}

		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}