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

/**
 * Servlet implementation class AdminDashboard
 */

public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	HttpSession session;
	private static CloseableHttpClient httpClient;

	public AdminDashboard() {
		try {
			AdminDashboard.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				AdminDashboard.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			AdminDashboard.restURL = (String) AdminDashboard.serviceProp.get("restURL");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject connection(JSONObject dataToRest) {
		JSONObject responseError = new JSONObject();
		responseError.put("status", "fail");		
		try {
			httpClient = HttpClients.createDefault();
			String restURL = AdminDashboard.restURL + "adminDashboard/";
			HttpPost postRequest = new HttpPost(restURL);
			String Data = dataToRest.toString();
			System.out.println("Rest URL: " + restURL + " Payload: " + Data);
			StringEntity input = new StringEntity(Data);
			input.setContentType("application/json");
			postRequest.setEntity(input);
			HttpResponse httpResponse = httpClient.execute(postRequest);

			if (httpResponse != null) {
				if (httpResponse.getStatusLine().getStatusCode() != 200) {
					System.out.println("Problem in fetching credentials from BitlaMongo. Error code: " + httpResponse.getStatusLine().getStatusCode());
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
				httpClient.close();
				return restResponse;
			} else {
				return responseError;
			}
		} catch (Exception e) {
			return responseError;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(false);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		try {
			JSONObject dataToConnection = new JSONObject();
			String actionType = null;
			actionType = request.getParameter("actionType");
			session = request.getSession(false);
			String user_id = (String) session.getAttribute("user_id");
			dataToConnection.put("user_id", user_id);

			if (request.getParameter("actionType").equals("sendMail")) {
				dataToConnection.put("actionType", request.getParameter("actionType"));
				dataToConnection.put("email_id", request.getParameter("to"));
				dataToConnection.put("subject", request.getParameter("subject"));
				dataToConnection.put("message", request.getParameter("message"));
			} else if (actionType.equals("Exsistinguser")) {
				String selectUser = request.getParameter("selectUser");
				dataToConnection.put("email_id", selectUser);
			} else if (actionType.equals("Statistics")) {
				String selectUser = request.getParameter("fetchMail");
				dataToConnection.put("fetchMail", selectUser);
			} else if (actionType.equals("Addnewuser")) {
				String firstName = request.getParameter("firstName");
				String lasttName = request.getParameter("lasttName");
				String emailAdress = request.getParameter("emailAdress");
				dataToConnection.put("firstName", firstName);
				dataToConnection.put("lasttName", lasttName);
				dataToConnection.put("emailAdress", emailAdress);
			} else if (actionType.equals("edituserprofile")) {
				String editFirst = request.getParameter("editFirst");
				String editLast = request.getParameter("editLast");
				String editEmail = request.getParameter("editEmail");
				String editUsePassword = request.getParameter("editUsePassword");
				String conformPassword = request.getParameter("conformPassword");
				dataToConnection.put("editFirst", editFirst);
				dataToConnection.put("editLast", editLast);
				dataToConnection.put("editEmail", editEmail);
				dataToConnection.put("editUsePassword", editUsePassword);
				dataToConnection.put("conformPassword", conformPassword);
			} else if (actionType.equals("onloadEditProfile")) {
				dataToConnection.put("actionType", request.getParameter("actionType"));
			} else if (actionType.equals("serverOnload")) {
				dataToConnection.put("actionType", request.getParameter("actionType"));
			} else if (actionType.equals("serverConfiguration")) {
				String mongoDBHost = request.getParameter("mongoDBHost");
				String mongoDBPort = request.getParameter("mongoDBPort");
				String mongoDBName = request.getParameter("mongoDBName");
				String notificationHost = request.getParameter("notificationHost");
				String notificationPort = request.getParameter("notificationPort");
				String restURI = request.getParameter("restURI");
				String olahHost = request.getParameter("olahHost");
				String userscollection = request.getParameter("userscollection");
				String nIncomeprdataofilecollection = request.getParameter("nIncomeprdataofilecollection");
				String counterscollection = request.getParameter("counterscollection");
				String financialPlancollection = request.getParameter("financialPlancollection");
				String goalsCollection = request.getParameter("goalsCollection");
				String statescollection = request.getParameter("statescollection");
				String cookieKey = request.getParameter("cookieKey");
				String sessioncollection = request.getParameter("sessioncollection");
				String zillowKey = request.getParameter("zillowKey");
				String indexFactorCollection = request.getParameter("indexFactorCollection");
				String olahtekMailUsername = request.getParameter("olahtekMailUsername");
				String olahtekmailpassword = request.getParameter("olahtekmailpassword");
				String cacheHost = request.getParameter("cacheHost");
				String cachePort = request.getParameter("cachePort");
				dataToConnection.put("mongoDBHost", mongoDBHost);
				dataToConnection.put("mongoDBPort", mongoDBPort);
				dataToConnection.put("mongoDBName", mongoDBName);
				dataToConnection.put("notificationHost", notificationHost);
				dataToConnection.put("notificationPort", notificationPort);
				dataToConnection.put("restURI", restURI);
				dataToConnection.put("olahHost", olahHost);
				dataToConnection.put("userscollection", userscollection);
				dataToConnection.put("nIncomeprdataofilecollection", nIncomeprdataofilecollection);
				dataToConnection.put("counterscollection", counterscollection);
				dataToConnection.put("financialPlancollection", financialPlancollection);
				dataToConnection.put("goalsCollection", goalsCollection);
				dataToConnection.put("statescollection", statescollection);
				dataToConnection.put("cookieKey", cookieKey);
				dataToConnection.put("sessioncollection", sessioncollection);
				dataToConnection.put("zillowKey", zillowKey);
				dataToConnection.put("indexFactorCollection", indexFactorCollection);
				dataToConnection.put("olahtekMailUsername", olahtekMailUsername);
				dataToConnection.put("olahtekmailpassword", olahtekmailpassword);
				dataToConnection.put("cacheHost", cacheHost);
				dataToConnection.put("cachePort", cachePort);
				AdminDashboard.connection(dataToConnection);
			} else if (actionType.equals("houseGoalIds")) {
				String goalEmailids = request.getParameter("goalEmailids");
				JSONArray goalTypeEmaiids = new JSONArray(goalEmailids);
				dataToConnection.put("actionType", request.getParameter("actionType"));
				dataToConnection.put("goalTypeEmaiids", goalTypeEmaiids);
			} else if (actionType.equals("applicationConfiguration")) {
				String emailGoalTypeIds1 = request.getParameter("emailGoalTypeIds1");
				new JSONArray(emailGoalTypeIds1);
				dataToConnection.put("actionType", request.getParameter("actionType"));
				dataToConnection.put("message", request.getParameter("messageConfig"));
				dataToConnection.put("email_id", emailGoalTypeIds1);
			} else if (request.getParameter("actionType").equals("deleteUser")) {
				dataToConnection.put("actionType", request.getParameter("actionType"));
				dataToConnection.put("user_id", request.getParameter("userIds"));
			}
			actionType = request.getParameter("actionType");
			if (request.getParameter("actionType") != null) {
				dataToConnection.put("actionType", actionType);
			} else {
				out.println("fail");
			}
			JSONObject rest_response = AdminDashboard.connection(dataToConnection);
			if (rest_response.getString("status").equals("sucess")) {
				out.println(rest_response);
			} else if (rest_response.getString("status").equals("email address is already registered")) {
				out.println(rest_response);
			} else {
				out.println("response failed");
			}
		} catch (Exception e) {
			System.out.println("Exception in Admindashboard: ");
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
