package com.olahtek.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Calendar;
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

public class CustomizedGoal extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Properties serviceProp;
    private static String restURLProp;
    private HttpSession session;
    private static CloseableHttpClient httpClient;
    private static final long STATUS_CODE = 200;

    public CustomizedGoal() {
        super();
        try {
            CustomizedGoal.serviceProp = new Properties();
            final String home = System.getProperty("user.home");
            final String propFile = "application.properties";
            if (new File(home + "/." + propFile).exists()) {
                final FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
                CustomizedGoal.serviceProp.load(inputStream);
                inputStream.close();
                System.out.println("Application is reading configuration for Register Handler from " + home);
            } else {
                System.out.println("Application is reading configuration from resources for Register Handler");
            }
            CustomizedGoal.restURLProp = (String) CustomizedGoal.serviceProp.get("restURL");
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
            final String restURL = CustomizedGoal.restURLProp + "Goalcustomized/";
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
                final BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
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
        System.out.println("actionHomeType-->>" + actionHomeType);
        session = request.getSession(false);
        final JSONObject dataToConnection = new JSONObject();
        try {
            session = request.getSession(false);
            final String userId = (String) session.getAttribute("user_id");
            if (actionHomeType.equals("edit")) {
                final String goalId = request.getParameter("goal_id");
                dataToConnection.put("goal_id", goalId);
                dataToConnection.put("actionHomeType", actionHomeType);
            } else {
                final String goalname = request.getParameter("Goalname");
                final String favoriteColors = request.getParameter("totalCost");
                final long goalCost = Long.parseLong(request.getParameter("Goalcost"));
                int goalEndingyear = Integer.parseInt(request.getParameter("Goalendingyear"));
                int goalStartingYear = Integer.parseInt(request.getParameter("Goalstartingyear"));
                System.out.println("goalStartingYear in costmiz---->" + goalStartingYear);
                goalEndingyear = goalEndingyear + goalStartingYear;
                if (goalStartingYear == 0) {
                    goalStartingYear = Calendar.getInstance().get(Calendar.YEAR);
                }
                final String planName = request.getParameter("plan_name");
                dataToConnection.put("plan_name", planName);
                dataToConnection.put("goalStartingYear", goalStartingYear);
                final String goaltype = "Customized Goal";
                dataToConnection.put("user_id", userId);
                dataToConnection.put("actionHomeType", actionHomeType);
                dataToConnection.put("goalname", goalname);
                dataToConnection.put("typeOfGoalCost", favoriteColors);
                dataToConnection.put("goalType", goaltype);
                dataToConnection.put("goalCost", goalCost);
                dataToConnection.put("goalEndingyear", goalEndingyear);
                if (actionHomeType.equals("update")) {
                    final String goalId = request.getParameter("goal_id");
                    dataToConnection.put("goal_id", goalId);
                    dataToConnection.put("typeOfGoalCost", favoriteColors);
                    dataToConnection.put("goalStartingYear", goalStartingYear);
                    dataToConnection.put("goalname", goalname);
                    dataToConnection.put("goalCost", goalCost);
                    dataToConnection.put("goalEndingyear", goalEndingyear);
                } else if (actionHomeType.equals("deleteGoal")) {
                    System.out.println("inside --- delete--");
                    final String goalId = request.getParameter("goal_id");
                    dataToConnection.put("goal_id", goalId);
                    dataToConnection.put("typeOfGoalCost", favoriteColors);
                    dataToConnection.put("goalStartingYear", goalStartingYear);
                    dataToConnection.put("goalname", goalname);
                    dataToConnection.put("goalCost", goalCost);
                    dataToConnection.put("goalEndingyear", goalEndingyear);
                }
            }
            final JSONObject restResponse = CustomizedGoal.connection(dataToConnection);
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
