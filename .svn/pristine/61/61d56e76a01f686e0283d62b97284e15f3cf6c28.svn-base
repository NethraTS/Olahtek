package com.olahtek.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;



/**
 * Servlet implementation class StateTaxConstant
 */
//@WebServlet("/StateTaxConstant")
public class StateTaxConstant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CloseableHttpClient httpClient;
	public static Properties serviceProp;
	public static String restURL;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StateTaxConstant() {
    	try {
    		StateTaxConstant.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				StateTaxConstant.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			StateTaxConstant.restURL = (String) StateTaxConstant.serviceProp.get("restURL");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	
    
    
    public static JSONObject connection(JSONObject dataToRest) {
		JSONObject responseError = new JSONObject();
		responseError.put("status", "fail");		
		try {
			System.out.println("rest--<<<");
			httpClient = HttpClients.createDefault();
			String restURL = StateTaxConstant.restURL +"StateTaxConstant/";
			System.out.println("restURL my-->"+restURL);
			HttpPost postRequest = new HttpPost(restURL);
			System.out.println("postRequest-->"+postRequest);
			String Data = dataToRest.toString();
			System.out.println("Rest URL: " + restURL + " Payload: " + Data);
			StringEntity input = new StringEntity(Data);
			System.out.println("input-->>"+input);
			
			input.setContentType("application/json");
			System.out.println("input1-->>"+input);
			postRequest.setEntity(input);
			System.out.println("postRequest-->>"+postRequest);
			HttpResponse httpResponse = httpClient.execute(postRequest);
			System.out.println("httpResponse--->>"+httpResponse);

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
		
		//onLoad
		
		try{
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			
			JSONObject dataToConnection = new JSONObject();
			//JSONObject dataToConnection3 = new JSONObject();
			JSONArray dataToConnection2 = new JSONArray();
			JSONArray dataToConnection3 = new JSONArray();
			String actionType = null;
			actionType = request.getParameter("actionType");
			
		//	System.out.println("request.getParameterValues);-->>"+request.getParameter("arryOfrate"));
			System.out.println("actionType of statetax-->>"+actionType);
			if(actionType.equals("allstateconstantValuesupdate")){
				dataToConnection.put("actionType", actionType);
				/*
				
				
				
				*/
			}
			
			
			else if(actionType !=null){
				System.out.println("imple-->>");
				System.out.println("onLoad--->>>");
				System.out.println("map-->>"+request.getParameterMap());
				//Map<String, String[]>   params=	request.getParameterMap();
				//System.out.println("params--."+params.toString());
			
				//String taxSlabs = params.get("taxSlabs")[0];
				//System.out.println("taxSlabs------->"+taxSlabs);
				
				System.out.println("request.getParameterNames-->>>"+request.getParameterNames());
			//	System.out.println("request.getParameterValues);-->>"+request.getParameterValues("arryOfrate"));
				
			
				if(actionType.equals("onLoadUpdate")){
				
				 Enumeration keys = request.getParameterNames();
				
				   while (keys.hasMoreElements() )
				   {
				      String key = (String)keys.nextElement();
				      System.out.println("key-->>>>"+key);
				      //To retrieve a single value
				      if(key.contains("rates")){
				    	 
				       double value = Double.parseDouble((request.getParameter(key)));
				      System.out.println("valueq11--->>>>>"+value);
				      
				      dataToConnection2.put(value);
				    
				      }else if(key.contains("brackets")){
				    	  int value = Integer.parseInt(request.getParameter(key));
				    	  System.out.println("valueq21--->>>>>"+value);
				    	  dataToConnection3.put(value);
				    	//  dataToConnection.put("brackets",value);
				    	  
				      }
				      else if(key.contains("status")){
				    	  String value = request.getParameter(key);
				    	  System.out.println("valueq--->>>>>"+value);
				    	  dataToConnection.put("maritalstatus",value); 
				      }
				      else{
				    	  String value = request.getParameter(key);
				    	  System.out.println("key in esle part"+key);
				    	  System.out.println("values in else part-->"+value);
				    	  dataToConnection.put(key, value);
				      }
				   }
				   System.out.println("dataToConnection2--->>"+dataToConnection2);
				   dataToConnection.put("rates",dataToConnection2);
				   dataToConnection.put("brackets",dataToConnection3);
				   System.out.println("dataToConnection0000--->>>"+dataToConnection);
				/*  System.out.println("fillingStatus--->>"+request.getParameter("fillingStatus"));
				  System.out.println("personalExcemption-->>"+request.getParameter("personalExcemption"));
				  System.out.println("standardDeduction-->>>"+request.getParameter("standardDeduction"));
				   dataToConnection.put("actionType", actionType);
				   dataToConnection.put("statename", request.getParameter("statename"));
				   dataToConnection.put("fillingStatus", request.getParameter("fillingStatus"));
				   dataToConnection.put("personalExcemption", request.getParameter("personalExcemption"));
				   dataToConnection.put("standardDeduction", request.getParameter("standardDeduction"));
				
				   if(request.getParameter("fillingStatus").equals("Single")){
					   System.out.println("oooooooooooo");*/
					 //  dataToConnection.put("stateAgiLimit", request.getParameter("minAGIlimit"));
					   
					 //  dataToConnection.put("standardDeduction", request.getParameter("standardDeduction"));
					 //  dataToConnection.put("age", request.getParameter("age"));
					   
					  // dataToConnection.put("minStandardDeduction", request.getParameter("minStandardDeduction"));
					 //  dataToConnection.put("dependentExcemption", request.getParameter("dependentExcemption"));
					   
					//   System.out.println("minAGIlimit-->>"+request.getParameter("minAGIlimit"));
					  // System.out.println("standardDeduction-->>"+request.getParameter("standardDeduction"));
					  //System.out.println("age-->>"+request.getParameter("age"));
                       // System.out.println("minStandardDeduction-->"+request.getParameter("minStandardDeduction"));
					   //System.out.println("dependentExcemption-->"+request.getParameter("dependentExcemption"));
					//   dataToConnection.put("standardDeductionAmt", request.getParameter("standardDeductionAmt"));
					  
					  
					   
				//   }
				   
				   //System.out.println("dataToConnection3-->>>"+dataToConnection3);
				
				   //System.out.println("dataToConnection2-0->>>"+dataToConnection2);
				}
				else{
				dataToConnection.put("actionType", actionType);
				
				
				dataToConnection.put("statename", request.getParameter("statename"));
				}
			}
			
		
			
			JSONObject rest_response = StateTaxConstant.connection(dataToConnection);
			System.out.println("rest_responsellll-->"+rest_response);
			if (rest_response.getString("status").equals("success")) {
				System.out.println("rest_response--->>"+rest_response);
				out.println(rest_response);
			}
			
			else if (rest_response.getString("status").equals("email address is already registered")) {
				out.println(rest_response);
			} else {
				out.println("response failed");
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		
	}

}
