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

public class EmergencyFund extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Properties serviceProp;
    private static String restURLProp;
    private static final long STATUS_CODE = 200;
    private static CloseableHttpClient httpClient;
    private HttpSession session;

    public EmergencyFund() {
        super();
        try {
            EmergencyFund.serviceProp = new Properties();
            final String home = System.getProperty("user.home");
            final String propFile = "application.properties";
            if (new File(home + "/." + propFile).exists()) {
                final FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
                EmergencyFund.serviceProp.load(inputStream);
                inputStream.close();
                System.out.println("Application is reading configuration for Register Handler from " + home);
            } else {
                System.out.println("Application is reading configuration from resources for Register Handler");
            }
            EmergencyFund.restURLProp = (String) EmergencyFund.serviceProp.get("restURL");
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
            final String restURL = EmergencyFund.restURLProp + "GoalEmergencyFund1/";
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
                        new InputStreamReader((httpResponse.getEntity().getContent())));
                while ((output = br.readLine()) != null) {
                    jsonString = output;
                }
                br.close();
                final JSONObject restResponse = new JSONObject(jsonString);
                System.out.println("Rest response automation: " + restResponse);
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
        String planName = request.getParameter("plan_name");
        session = request.getSession(false);
        final JSONObject dataToConnection = new JSONObject();
        try {
            session = request.getSession(false);
            String userId = (String) session.getAttribute("user_id");
            if (actionHomeType.equals("edit")) {
                final String goalId = request.getParameter("goal_id");
                userId = (String) session.getAttribute("user_id");
                dataToConnection.put("user_id", userId);
                dataToConnection.put("goal_id", goalId);
                dataToConnection.put("actionHomeType", actionHomeType);
            } else if (actionHomeType.equals("deleteGoal")) {
                System.out.println("deleting the goal in servlet");
                final String goalId = request.getParameter("goal_id");
                planName = request.getParameter("plan_name");
                dataToConnection.put("user_id", userId);
                dataToConnection.put("plan_name", planName);
                dataToConnection.put("goal_id", goalId);
                dataToConnection.put("actionHomeType", actionHomeType);
            } else if (actionHomeType.equals("OnLoadEmergencyFund")) {
                dataToConnection.put("plan_name", planName);
                dataToConnection.put("actionHomeType", actionHomeType);
                userId = (String) session.getAttribute("user_id");
                dataToConnection.put("user_id", userId);
            } else {
                long amountSave = Long.parseLong(request.getParameter("amountSave"));
                String timePeriod = request.getParameter("timePeriod");
                String month = request.getParameter("month");
                String month1 = request.getParameter("month1");
                planName = request.getParameter("plan_name");
                dataToConnection.put("plan_name", planName);
                request.getParameter("goalName");
                String goaltype = "Emergency Fund";
                dataToConnection.put("user_id", userId);
                dataToConnection.put("actionHomeType", actionHomeType);
                dataToConnection.put("goalType", goaltype);
                dataToConnection.put("amountSave", amountSave);
                dataToConnection.put("timePeriod", timePeriod);
                dataToConnection.put("monthI", month1);
                dataToConnection.put("monthE", month);
                if (actionHomeType.equals("update")) {
                    amountSave = Long.parseLong(request.getParameter("amountSave"));
                    timePeriod = request.getParameter("timePeriod");
                    month = request.getParameter("month");
                    month1 = request.getParameter("month1");
                    final String goalId = request.getParameter("goal_id");
                    planName = request.getParameter("plan_name");
                    dataToConnection.put("plan_name", planName);
                    request.getParameter("goalName");
                    goaltype = "Emergency Fund";
                    dataToConnection.put("user_id", userId);
                    dataToConnection.put("goal_id", goalId);
                    dataToConnection.put("actionHomeType", actionHomeType);
                    dataToConnection.put("goalType", goaltype);
                    dataToConnection.put("amountSave", amountSave);
                    dataToConnection.put("timePeriod", timePeriod);
                    dataToConnection.put("monthI", month1);
                    dataToConnection.put("monthE", month);
                }
            }
            final JSONObject restResponse = EmergencyFund.connection(dataToConnection);
            final String status = restResponse.getString("status");
            if (status.equals("success")) {
                out.println(restResponse);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        out.close();
    }
}
