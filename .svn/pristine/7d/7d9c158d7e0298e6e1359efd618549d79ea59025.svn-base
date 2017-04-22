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

public class Login extends HttpServlet {
	public static String cookieKey;
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;
	private static CloseableHttpResponse httpResponse;
	private PrintWriter out;
	private static BufferedReader br;

	public Login() {
		try {
			Login.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				Login.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			Login.restURL = (String) Login.serviceProp.get("restURL");
			Login.cookieKey = (String) Login.serviceProp.get("cookieKey");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JSONObject connection(JSONObject dataToRest) {
		JSONObject restResponseToLogin = new JSONObject();
		httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = Login.restURL + "Login/";
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

	private void createSession(HttpServletRequest request, HttpServletResponse response) {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("http://localhost:8080/Servetls/Admindashboard.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject dataToConnection = new JSONObject();
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		try {
			String email = request.getParameter("name");
			String password = request.getParameter("password");
			dataToConnection.put("password", password);
			dataToConnection.put("email", email);
			JSONObject dataFromConnection = Login.connection(dataToConnection);
			String data = dataFromConnection.getString("status");
			String completedStatus = dataFromConnection.getString("completedStatus");
			if (data.equals("success")) {
				String user_id = dataFromConnection.getString("user_id");
				createSession(request, response);
				session = request.getSession();
				String session_id = session.getId();
				/*-----------------------Encryption.encrypt(session_id);-------------------------*/

				String encryptedSession_id = session_id + "" + user_id;
				session.setAttribute("user_id", user_id);
				/*---------------------------Adding Cookies In Browser--------------------------- */
				Cookie sessionCookie = new Cookie("AhTwxlO", encryptedSession_id);
				sessionCookie.setMaxAge(60 * 60 * 24);
				response.addCookie(sessionCookie);

				/*----------------------------Sending Session ID to Rest--------------------------*/

				dataToConnection.put("user_id", user_id);
				dataToConnection.put("action", "LogincheckCoockies");
				dataToConnection.put("sessionID", encryptedSession_id);
				dataFromConnection = Login.connection(dataToConnection);
				/*----------------------------------Sending response to UI---------------------------------*/
				if (dataFromConnection.getString("status").equals("success")) {
					dataFromConnection.put("completedStatus", completedStatus);
					out.println(dataFromConnection);
				}
			} else if (data.equals("incorrect_password")) {
				response.getWriter().write("incorrect_password");
			} else {
				response.getWriter().write("user is not registered");
			}
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
