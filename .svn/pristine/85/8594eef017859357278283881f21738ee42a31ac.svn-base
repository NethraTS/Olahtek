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
import org.json.JSONObject;

public class Goalcar extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Properties serviceProp;
    private static String restURLProp;
    private HttpSession session;
    private static CloseableHttpClient httpClient;
    private static final long STATUS_CODE = 200;

    public Goalcar() {
        super();
        try {
            Goalcar.serviceProp = new Properties();
            final String home = System.getProperty("user.home");
            final String propFile = "application.properties";
            if (new File(home + "/." + propFile).exists()) {
                final FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
                Goalcar.serviceProp.load(inputStream);
                inputStream.close();
                System.out.println("Application is reading configuration for Register Handler from " + home);
            } else {
                System.out.println("Application is reading configuration from resources for Register Handler");
            }
            Goalcar.restURLProp = (String) Goalcar.serviceProp.get("restURL");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject connection(final JSONObject dataToRest) {
        final JSONObject obj = new JSONObject();
        obj.put("status", "fail");
        httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            final String restURL = Goalcar.restURLProp + "Goalcar/";
            final HttpPost postRequest = new HttpPost(restURL);
            final String data = dataToRest.toString();
            System.out.println("Rest URL: " + restURL + " Payload: " + data);
            final StringEntity input = new StringEntity(data);
            input.setContentType("application/json");
            postRequest.setEntity(input);
            final HttpResponse httpResponse = httpClient.execute(postRequest);
            if (httpResponse != null) {
                if (httpResponse.getStatusLine().getStatusCode() != STATUS_CODE) {
                    System.out.println("Problem in fetching credentials from BitlaMongo");
                }
                String output = null;
                String jsonString = null;
                final BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpResponse.getEntity().getContent()));
                while ((output = br.readLine()) != null) {
                    jsonString = output;
                }
                br.close();
                final JSONObject restResponse = new JSONObject(jsonString);
                System.out.println("Rest response automation: " + restResponse);
                httpClient.close();
                return restResponse;
            } else {
                return obj;
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return obj;
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = response.getWriter();
        final String actionHomeType = request.getParameter("actionHomeType");
        session = request.getSession(false);
        final JSONObject dataToConnection = new JSONObject();
        String carReccursive=null;
        int reccursivePeriod=0;
        try {
            session = request.getSession(false);
            final String userId = (String) session.getAttribute("user_id");
            String creditScore = null;
            if (actionHomeType.equals("deleteGoal")) {
                System.out.println("deleting the goal in servlet");
                final String goalId = request.getParameter("goal_id");
                final String planName = request.getParameter("plan_name");
                dataToConnection.put("user_id", userId);
                dataToConnection.put("plan_name", planName);
                dataToConnection.put("goal_id", goalId);
                dataToConnection.put("actionHomeType", actionHomeType);
            } else if (actionHomeType.equals("edit")) {
                final String goalId = request.getParameter("goal_id");
                dataToConnection.put("goal_id", goalId);
                dataToConnection.put("actionHomeType", actionHomeType);
            } else {
                final String rentLease = request.getParameter("rentLease");
                final int carYear = Integer.parseInt(request.getParameter("carYear"));
                final long carPrice = Long.parseLong(request.getParameter("carPrice"));
                final String planName = request.getParameter("plan_name");
                if (rentLease.equals("Leasing")) {
                    final int leaseYear = Integer.parseInt(request.getParameter("leaseYear"));
                    final double moneyFactor = Double.parseDouble(request.getParameter("moneyFactor"));
                    final int residualValue = Integer.parseInt(request.getParameter("residualValue"));
                    carReccursive=request.getParameter("carReccursive");
                    if(carReccursive.equals("LeasingYes"))
                    {
                        reccursivePeriod=Integer.parseInt(request.getParameter("reccursivePeriod"));
                    }
                    dataToConnection.put("moneyFactor", moneyFactor);
                    dataToConnection.put("leaseYear", leaseYear);
                    dataToConnection.put("residualValue", residualValue);
                    dataToConnection.put("carReccursive", carReccursive);
                    dataToConnection.put("reccursivePeriod", reccursivePeriod);
                }
                if (rentLease.equals("Buying")) {
                    final int timePeriod = Integer.parseInt(request.getParameter("timePeriod"));
                    //final int intrestRate = Integer.parseInt(request.getParameter("intrestRate"));
                    // double intrestRate =
                    // Double.parseDouble(request.getParameter("intrestRate"));
                    final int downPayment = Integer.parseInt(request.getParameter("down_payment"));
                    if (request.getParameter("creditsc") != "") {
                        creditScore = request.getParameter("creditsc");
                    }
                    dataToConnection.put("carBuyingReccursive", request.getParameter("carBuyingReccursive"));
                    if (request.getParameter("carBuyingReccursive").equals("BuyingNo")) {
                        dataToConnection.put("tradeIn", 0);
                        dataToConnection.put("reccursiveBuyingPeriod", 0);
                    } else {
                        dataToConnection.put("tradeIn", Integer.parseInt(request.getParameter("tradeIn")));
                        dataToConnection.put("reccursiveBuyingPeriod", Integer.parseInt(request.getParameter("reccursiveBuyingPeriod")));
                    }
                    dataToConnection.put("timePeriod", timePeriod);
                    dataToConnection.put("creditScore", creditScore);
                    //dataToConnection.put("intrestRate", intrestRate);
                    dataToConnection.put("downPayment", downPayment);
                }
                dataToConnection.put("plan_name", planName);
                request.getParameter("goalName");
                final String goaltype = "Car";
                dataToConnection.put("user_id", userId);
                dataToConnection.put("actionHomeType", actionHomeType);
                dataToConnection.put("carYear", carYear);
                dataToConnection.put("carPrice", carPrice);
                dataToConnection.put("goalType", goaltype);
                dataToConnection.put("rentLease", rentLease);
                // System.out.println(" dataToConnection "+dataToConnection +"
                // "+rentLease);
                if (actionHomeType.equals("update")) {
                    final String goalId = request.getParameter("goal_id");
                    dataToConnection.put("rentLease", rentLease);
                    dataToConnection.put("goal_id", goalId);
                    dataToConnection.put("carPrice", carPrice);
                    dataToConnection.put("carYear", carYear);
                    if (rentLease.equals("Buying")) {
                        final int timePeriod = Integer.parseInt(request.getParameter("timePeriod"));
                        final double intrestRate = Double.parseDouble(request.getParameter("intrestRate"));
                        final int downPayment = Integer.parseInt(request.getParameter("down_payment"));
                        dataToConnection.put("downPayment", downPayment);
                        dataToConnection.put("timePeriod", timePeriod);
                        dataToConnection.put("intrestRate", intrestRate);
                    }
                    if (rentLease.equals("Leasing")) {
                        final int leaseYear = Integer.parseInt(request.getParameter("leaseYear"));
                        final double moneyFactor = Double.parseDouble(request.getParameter("moneyFactor"));
                        final int residualValue = Integer.parseInt(request.getParameter("residualValue"));
                        dataToConnection.put("moneyFactor", moneyFactor);
                        dataToConnection.put("leaseYear", leaseYear);
                        dataToConnection.put("residualValue", residualValue);
                    }
                }
            }
            final JSONObject restResponse = Goalcar.connection(dataToConnection);
            final String status = restResponse.getString("status");
            if (status.equals("success")) {
                out.println(restResponse);
            } else {

                out.println(restResponse);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        out.close();
    }
}
