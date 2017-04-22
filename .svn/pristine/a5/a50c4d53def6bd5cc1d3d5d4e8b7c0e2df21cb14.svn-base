package com.olahtek.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class EmergencyFundEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;

	public EmergencyFundEdit() {
		try {
			EmergencyFundEdit.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				EmergencyFundEdit.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			EmergencyFundEdit.restURL = (String) EmergencyFundEdit.serviceProp.get("restURL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject connection(JSONObject dataToRest) {
		httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = EmergencyFundEdit.restURL + "EmergencyFundEdit/";
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
				System.out.println("Rest response: " + restResponse);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getParameter("data");
		String hashes1 = request.getParameter("hashes");
		request.getParameter("formType");
		long Amountsave = 0;
		long Timeperiod = 0;
		if (!(request.getParameter("Amountsave").equals("") && request.getParameter("Amountsave").equals(""))) {
			Amountsave = Long.parseLong(request.getParameter("Amountsave"));
			Timeperiod = Long.parseLong(request.getParameter("Timeperiod"));
		}
		JSONObject dataToConnection = new JSONObject();
		dataToConnection.put("_id", hashes1);
		dataToConnection.put("amountSave", Amountsave);
		dataToConnection.put("timePeriod", Timeperiod);
		JSONObject restResponse = EmergencyFundEdit.connection(dataToConnection);
		response.getWriter().write(restResponse.toString());
	}
}
