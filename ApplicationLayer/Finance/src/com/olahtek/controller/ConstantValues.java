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

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

/**
 * Servlet implementation class ConstantValues
 */

public class ConstantValues extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Properties serviceProp;
	public static String restURL;
	private static CloseableHttpClient httpClient;
       
  
    public ConstantValues() {
      
    	
    	try {
    		ConstantValues.serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				ConstantValues.serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration for Register Handler from " + home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			ConstantValues.restURL = (String) ConstantValues.serviceProp.get("restURL");
			
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
			String restURL = ConstantValues.restURL +"constantValues/";
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
				System.out.println("Rest response: " + restResponse);
				httpClient.close();
				return restResponse;
			} else {
				return responseError;
			}
		} catch (Exception e) {
			return responseError;
		}
	}
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			
			JSONObject dataToConnection = new JSONObject();
			String actionType = null;
			actionType = request.getParameter("actionType");
			System.out.println("Khatri::::...actionType of FDI constant-->>"+actionType);
			dataToConnection.put("formType", actionType);
			if(actionType.equals("fdiConstantUpadte"))
			{
				System.out.println("1   "+request.getParameter("kidGoalCostPerYear"));
				System.out.println("2   "+request.getParameter("collegegoalCostPerYearForinstatepublic"));
				System.out.println("3  "+request.getParameter("collegeCostPeryearforoutofstatepublic"));
				System.out.println("4  "+request.getParameter("collegeCostPerYearForPrivate"));
				 dataToConnection.put("kidGoalDefaultCostPeryear",request.getParameter("kidGoalCostPerYear"));
				 dataToConnection.put("collegeGoalDefaultCostPeryearForinstatepubliccollege", request.getParameter("collegegoalCostPerYearForinstatepublic"));
				 dataToConnection.put("collegeGoalDefaultCostPeryearForoutofstatepubliccollege", request.getParameter("collegeCostPeryearforoutofstatepublic"));
				 dataToConnection.put("collegeGoalDefaultCostPeryearForPrivateCollege", request.getParameter("collegeCostPerYearForPrivate"));
				 System.out.println("dataToConnection=====aparna goals"+dataToConnection);
				dataToConnection.put("filingStatus", request.getParameter("filingStatus"));
				dataToConnection.put("phaseOutIncomeRate", request.getParameter("phaseOutIncomeRate"));
				dataToConnection.put("phaseOutIncomeConstantValue1", request.getParameter("phaseOutIncomeConstantValue1"));
				dataToConnection.put("perChildCredit", request.getParameter("perChildCredit"));
				dataToConnection.put("retirementExpenseRate", request.getParameter("retirementExpenseRate"));
				dataToConnection.put("maxSSTax", request.getParameter("maxSSTax"));
				dataToConnection.put("standardDeduction", request.getParameter("standardDeduction"));
				if(request.getParameter(request.getParameter("filingStatus"))!=null)
				{
				dataToConnection.put(request.getParameter("filingStatus"), new JSONObject(request.getParameter(request.getParameter("filingStatus"))));
				}
				 System.out.println("dataToConnection=====aparna goals"+dataToConnection);
				

				
			}
			
			System.out.println("aparna====="+actionType);
			
			 
				 
			System.out.println("Khatri:::::.......dataToConnection==New009===="+request.getParameter("single"));
			JSONObject rest_response = ConstantValues.connection(dataToConnection);
			System.out.println("Khatri::::....rest_response form FDI007-->"+rest_response);
			
			if (rest_response.getString("status").equals("success")) {
				System.out.println("rest_response--->>"+rest_response);
				out.println(rest_response);
			}
			 else {
				out.println("response failed");
			}	
			
		} catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		
	}

}
