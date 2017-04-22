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

public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;
	private static CloseableHttpResponse httpResponse;
	private FileInputStream inputStream;

	public ChangePassword() {
		try {
			ChangePassword.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				inputStream = new FileInputStream(new File(home + "/." + propFile));
				ChangePassword.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			ChangePassword.restURL = (String) ChangePassword.serviceProp.get("restURL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JSONObject connection(JSONObject dataToRest) {
		JSONObject restResponseToLogin = new JSONObject();
		httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = ChangePassword.restURL + "changePassword/";
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
			BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
			while ((output = br.readLine()) != null) {
				restResponse = output;
			}
			br.close();
			restResponseToLogin = new JSONObject(restResponse);
			System.out.println("Rest response: " + restResponseToLogin);
		} catch (Exception e) {
			restResponseToLogin.put("status", "fail");
			e.printStackTrace();
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject dataToConnection = new JSONObject();
		session = request.getSession(false);
		try {
			String user_id = (String) session.getAttribute("user_id");
			PrintWriter out = response.getWriter();
			String oldPassword = request.getParameter("currentPassword");
			String newPassword = request.getParameter("newPassword");
			dataToConnection.put("user_id", user_id);
			dataToConnection.put("oldPassword", oldPassword);
			dataToConnection.put("newPassword", newPassword);
			JSONObject dataFromConnection = ChangePassword.connection(dataToConnection);
			dataFromConnection.getString("status");
			if (dataFromConnection.getString("status").equals("success")) {
				dataFromConnection.put("Massage", "Password Successfully Changed");
				out.println(dataFromConnection);
			} else {
				dataFromConnection.put("Massage", "Please try after some time");
				out.println(dataFromConnection);
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
