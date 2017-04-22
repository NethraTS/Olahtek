package com.olahtek.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

public class AutoComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	HazelcastInstance hazelCastClient;
	private FileInputStream inputStream;

	public AutoComplete() {
		try {
			AutoComplete.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				inputStream = new FileInputStream(new File(home + "/." + propFile));
				AutoComplete.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			String cachehost = (String) AutoComplete.serviceProp.get("cacheHost");
			String cachePort = (String) AutoComplete.serviceProp.get("cachePort");
			ClientConfig clientConfig = new ClientConfig();
			clientConfig.getNetworkConfig().addAddress(cachehost + ":" + cachePort);
			hazelCastClient = HazelcastClient.newHazelcastClient(clientConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONArray connection(JSONObject dataToRest) {
		JSONArray restResponseToLogin = new JSONArray();
		String stateCode = "";
		try {
			JSONArray cityAndCounty = new JSONArray();
			String state = dataToRest.getString("state");
			Map<String, String> states = hazelCastClient.getMap("state");
			for (String key : states.keySet()) {
				if (key.equals(state)) {
					stateCode = states.get(key);
				}
			}
			Map<String, String> stateMap = hazelCastClient.getMap(stateCode);
			cityAndCounty = new JSONArray(stateMap.keySet());
			return cityAndCounty;
		} catch (Exception e) {
		}
		return restResponseToLogin;
	}

	@SuppressWarnings("finally")
	public String connectionToGetCounty(JSONObject dataToRest) {
		String stateCode = "";
		String county = "";
		try {
			String state = dataToRest.getString("state");
			String city = dataToRest.getString("city");
			Map<String, String> states = hazelCastClient.getMap("state");
			for (String key : states.keySet()) {
				if (key.equals(state)) {
					stateCode = states.get(key);
				}
			}
			Map<String, String> stateMap = hazelCastClient.getMap(stateCode);
			for (String key : stateMap.keySet()) {
				if (key.equals(city)) {
					county = stateMap.get(key);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return county;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String city = request.getParameter("country");
		String state = request.getParameter("city");
		JSONObject requestToConnection = new JSONObject();
		requestToConnection.put("city", city);
		requestToConnection.put("state", state);
		if (request.getParameter("actionType").equals("userDetailsAutocopmlete")) {
			JSONArray userDetails = userDetailsAutocomplete();
			out.println(userDetails);
		} else if (request.getParameter("actionType").equals("getCities")) {
			JSONArray autocomplete = connection(requestToConnection);
			out.println(autocomplete);
		} else if (request.getParameter("actionType") != null) {
			String county = connectionToGetCounty(requestToConnection);
			out.println(county);
		} else {
			JSONArray autocomplete = connection(requestToConnection);
			out.println(autocomplete);
		}
		out.close();
	}

	public JSONArray userDetailsAutocomplete() {
		JSONArray userDetailsEmail = new JSONArray();
		try {
			Map<String, String> userDetails = hazelCastClient.getMap("userDetails");
			userDetailsEmail = new JSONArray(userDetails.keySet());
			return userDetailsEmail;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDetailsEmail;
	}

}
