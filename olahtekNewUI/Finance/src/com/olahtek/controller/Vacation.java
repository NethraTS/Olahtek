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

public class Vacation extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final long STATUS_CODE = 200;
    private static Properties serviceProp;
    private static String restURLProp;
    private HttpSession session;
    private static CloseableHttpClient httpClient;

    public Vacation() {
        try {
            Vacation.serviceProp = new Properties();
            final String home = System.getProperty("user.home");
            final String propFile = "application.properties";
            if (new File(home + "/." + propFile).exists()) {
                final FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
                Vacation.serviceProp.load(inputStream);
                inputStream.close();
                System.out.println("Application is reading configuration for Register Handler from " + home);
            } else {
                System.out.println("Application is reading configuration from resources for Register Handler");
            }
            Vacation.restURLProp = (String) Vacation.serviceProp.get("restURL");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject connection(final JSONObject dataToRest) {
        httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            final String restURL = Vacation.restURLProp + "vacation/";
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
                System.out.println("Rest response automation: " + jsonString);
                final JSONObject restResponse = new JSONObject(jsonString);
                return restResponse;
            } else {
                final JSONObject obj = new JSONObject();
                obj.put("status", "fail");
                return obj;
            }
        } catch (final Exception e) {
            final JSONObject obj = new JSONObject();
            obj.put("status", "fail");
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
        final PrintWriter out = response.getWriter();
        session = request.getSession(false);
        final String formType = request.getParameter("formType");
        final String userId = (String) session.getAttribute("user_id");
        final String goalId = request.getParameter("goal_id");
        final String planName = request.getParameter("plan_name");
        System.out.println("plan_name.......Bala.." + planName);
        final String goalType = "Vacation";
        if (formType.equals("next")) {
            try {
                final int vacationYear = Integer.parseInt(request.getParameter("vacationYear"));
                final long amountSave = Long.parseLong(request.getParameter("amountSave"));
                final String frequency = request.getParameter("frequency");
                final JSONObject dataToConnection = new JSONObject();
                dataToConnection.put("amountSave", amountSave);
                dataToConnection.put("vacationYear", vacationYear);
                dataToConnection.put("frequency", frequency);
                dataToConnection.put("plan_name", planName);
                dataToConnection.put("user_id", userId);
                dataToConnection.put("goalType", goalType);
                dataToConnection.put("formType", formType);
                dataToConnection.put("goal_id", goalId);
                final JSONObject restResponse = Vacation.connection(dataToConnection);
                out.print(restResponse);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        } else if (formType.equals("deleteGoal")) {
            try {
                System.out.println("deleting the goal in servlet");
                final JSONObject dataToConnection = new JSONObject();
                dataToConnection.put("user_id", userId);
                dataToConnection.put("plan_name", planName);
                dataToConnection.put("goal_id", goalId);
                dataToConnection.put("formType", formType);
                final JSONObject restResponse = Vacation.connection(dataToConnection);
                out.print(restResponse);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        } else if (formType.equals("update")) {
            try {
                final int vacationYear = Integer.parseInt(request.getParameter("vacationYear"));
                final long amountSave = Long.parseLong(request.getParameter("amountSave"));
                final String frequency = request.getParameter("frequency");
                final JSONObject dataToConnection = new JSONObject();
                dataToConnection.put("amountSave", amountSave);
                dataToConnection.put("vacationYear", vacationYear);
                dataToConnection.put("frequency", frequency);
                dataToConnection.put("plan_name", planName);
                dataToConnection.put("user_id", userId);
                dataToConnection.put("goalType", goalType);
                dataToConnection.put("formType", formType);
                dataToConnection.put("goal_id", goalId);
                final JSONObject restResponse = Vacation.connection(dataToConnection);
                out.print(restResponse);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        } else if (formType.equals("edit")) {
            try {
                final JSONObject dataToConnection = new JSONObject();
                dataToConnection.put("goal_id", goalId);
                dataToConnection.put("formType", formType);
                final JSONObject restResponse = Vacation.connection(dataToConnection);
                out.print(restResponse);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
        out.close();
    }
}
