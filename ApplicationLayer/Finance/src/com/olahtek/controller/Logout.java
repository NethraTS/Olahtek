package com.olahtek.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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

public class Logout extends HttpServlet {
	public static String cookieKey;
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;
	private static CloseableHttpResponse httpResponse;

	public Logout() {
		try {
			Logout.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				Logout.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			Logout.restURL = (String) Logout.serviceProp.get("restURL");
			Logout.cookieKey = (String) Logout.serviceProp.get("cookieKey");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static JSONObject connection(JSONObject dataToRest) {
		JSONObject restResponseToLogin = new JSONObject();
		httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = Logout.restURL + "" + "logout/";
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
			return restResponseToLogin;
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
		session = request.getSession(false);
		request.getParameter("lastVisitedPage");
		JSONObject dataToConnection = new JSONObject();
		JSONObject dataFromConnection = new JSONObject();
		String sessionID = request.getParameter("sessionID");
		String user_id = (String) session.getAttribute("user_id");
		request.getSession().invalidate();
		try {
			removeEntityFromCookie("AhTwxlO", request, response);
			request.getSession().invalidate();
			dataToConnection.put("user_id", user_id);
			dataToConnection.put("action", "checkCoockies");
			dataToConnection.put("sessionID", sessionID);
			dataFromConnection = Logout.connection(dataToConnection);
			if (dataFromConnection.getString("status").equals("success")) {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void removeEntityFromCookie(String level, HttpServletRequest request, HttpServletResponse response) {
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals(level)) {
					cookie.setMaxAge(0);
					cookie.setValue("");
					response.addCookie(cookie);
				}
			}
		}
	}
}
