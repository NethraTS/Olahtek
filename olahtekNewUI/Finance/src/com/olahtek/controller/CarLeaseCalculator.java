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

public class CarLeaseCalculator extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static Properties serviceProp;
    public static String restURL;
    HttpSession session;
    private static CloseableHttpClient httpClient;
    private static CloseableHttpResponse httpResponse;
    private PrintWriter out;
    private static BufferedReader br;

    public CarLeaseCalculator() {
        super();
        try {
            CarLeaseCalculator.serviceProp = new Properties();
            final String home = System.getProperty("user.home");
            final String propFile = "application.properties";
            if (new File(home + "/." + propFile).exists()) {
                final FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
                CarLeaseCalculator.serviceProp.load(inputStream);
                inputStream.close();
                System.out.println("Application is reading configuration for Register Handler from " + home);
            } else {
                System.out.println("Application is reading configuration from resources for Register Handler");
            }
            CarLeaseCalculator.restURL = (String) CarLeaseCalculator.serviceProp.get("restURL");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject connection(JSONObject dataToRest) {
        JSONObject restResponseToLogin = new JSONObject();
        httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            final String restURL = CarLeaseCalculator.restURL + "CarLeaseCalculator/";
            final HttpPost postRequest = new HttpPost(restURL);
            final String Data = dataToRest.toString();
            System.out.println("Rest URL: " + restURL + " Payload: " + Data);
            final StringEntity input = new StringEntity(Data);
            input.setContentType("application/json");
            postRequest.setEntity(input);
            httpResponse = httpClient.execute(postRequest);
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
            }
            String restResponse = null;
            String output = null;
            br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            while ((output = br.readLine()) != null) {
                restResponse = output;
            }
            restResponseToLogin = new JSONObject(restResponse);
            System.out.println("Rest response: " + restResponseToLogin);
        } catch (final Exception e) {
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
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return restResponseToLogin;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = response.getWriter();
        final String action = request.getParameter("action");
        //String action1 = request.getParameter("action1");
        System.out.println("actionHomeType-B->>"+action);
        session = request.getSession(false);
        final JSONObject dataToConnection = new JSONObject();
        try {
            session = request.getSession(false);
            //String user_id = (String) session.getAttribute("user_id");
            System.out.println("Action type passed"+action);
            if (action.equals("edit1")) {
                dataToConnection.put("action",action);
                final String carprice = request.getParameter("car_price");
                final String loanterm = request.getParameter("loan_term");
                final String interestrate = request.getParameter("interest_rate");
                final String downpayment = request.getParameter("down_payment");
                final String tradeinvalue = request.getParameter("tradein_value");
                final String licensefee = request.getParameter("license_fee");
                final String acquisitioncost = request.getParameter("acquisition_cost");
                final String residualvalue = request.getParameter("residual_value");
                final String saletax = request.getParameter("sale_tax");


                System.out.println("interestrate:"+interestrate);
                dataToConnection.put("carprice", carprice);
                dataToConnection.put("loanterm",loanterm);
                dataToConnection.put("interestrate",interestrate);
                dataToConnection.put("tradeinvalue",tradeinvalue);
                dataToConnection.put("downpayment",downpayment);
                dataToConnection.put("licensefee", licensefee);
                dataToConnection.put("acquisitioncost",acquisitioncost);
                dataToConnection.put("residualvalue", residualvalue);
                dataToConnection.put("saletax", saletax);





            }

            /*else if (action.equals("getHomeInsurance")) {

				dataToConnection.put("action",action);
				String states = request.getParameter("states");
				 dataToConnection.put("states", states);

			}*/

            final JSONObject rest_response = CarLeaseCalculator.connection(dataToConnection);
            final String status = rest_response.getString("status");
            if (status.equals("success")) {
                out.println(rest_response);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        out.close();
    }
}


















