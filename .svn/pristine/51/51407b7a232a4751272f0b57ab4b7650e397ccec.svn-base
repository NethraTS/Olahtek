package com.olahtek.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

public class CheckSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;
	private static CloseableHttpResponse httpResponse;
	private static BufferedReader br;

	public CheckSession() {
		try {
			CheckSession.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				CheckSession.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			CheckSession.restURL = (String) CheckSession.serviceProp.get("restURL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject connection(String sessionID, String action, String lastVisitedPage) {
		JSONObject dataToRest = new JSONObject();
		JSONObject restResponseToLogin = new JSONObject();
		dataToRest.put("sessionID", sessionID);
		dataToRest.put("action", action);
		dataToRest.put("lastVisitedPage", lastVisitedPage);
		httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpPost postRequest;
			String restURL = CheckSession.restURL;
			if (action == "logout") {
				restURL = restURL + "logout/";
			} else {
				restURL = restURL + "Login/";
			}
			postRequest = new HttpPost(restURL);
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
			br.close();
			restResponseToLogin = new JSONObject(restResponse);
			System.out.println("Rest response automation: " + restResponseToLogin);
		} catch (Exception e) {
			restResponseToLogin.put("status", "fail");
			e.printStackTrace();
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(false);
		PrintWriter out = response.getWriter();
		String sessionID = request.getParameter("cookieId");
		String lastVisitedPage = request.getParameter("lastVisitedPage");
		String user_id1 = (String) session.getAttribute("user_id");
		if ((session != null) && !session.isNew() && (user_id1 != null)) {
			String action = "checkCookies";
			JSONObject dataFromConnection = CheckSession.connection(sessionID, action, lastVisitedPage);
			if (dataFromConnection.getString("status").equals("success")) {
				dataFromConnection.getString("user_id");
				out.println(dataFromConnection);
			} else {
				removeEntityFromCookie("sessionID", request, response);
				response.getWriter().write("fail");
			}
		} else {
			JSONObject dataToConnection = new JSONObject();
			dataToConnection.put("action", "checkCoockies");
			dataToConnection.put("sessionID", sessionID);
			CheckSession.connection(sessionID, "logout", "NULL");
			removeEntityFromCookie("sessionID", request, response);
			response.getWriter().write("fail");
		}
		out.close();
	}

	/*--------------------------Remove Entities  from Cookies------------------------*/
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
