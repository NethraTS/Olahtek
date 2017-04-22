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

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

/*** Servlet implementation class SSBCalculator */

public class SSBCalculator extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final long STATUS_CODE = 200;
    private static Properties serviceProp;
    private static String restURLProp;
    private static CloseableHttpClient httpClient;
    private static CloseableHttpResponse httpResponse;
    private PrintWriter out;
    private static BufferedReader br;

    /*** @see HttpServlet#HttpServlet() */
    public SSBCalculator() {
        try {
            SSBCalculator.serviceProp = new Properties();
            final String home = System.getProperty("user.home");
            final String propFile = "application.properties";
            if (new File(home + "/." + propFile).exists()) {
                final FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
                SSBCalculator.serviceProp.load(inputStream);
                inputStream.close();
                System.out.println("Application is reading configuration for Register Handler from " + home);
            } else {
                System.out.println("Application is reading configuration from resources for Register Handler");
            }
            SSBCalculator.restURLProp = (String) SSBCalculator.serviceProp.get("restURL");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject connection(final JSONObject dataToRest) {
        JSONObject restResponseToLogin = new JSONObject();
        httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            final String restURL = SSBCalculator.restURLProp + "SSB/";
            final HttpPost postRequest = new HttpPost(restURL);
            final String data = dataToRest.toString();
            System.out.println("Rest URL: " + restURL + " Payload: " + data);
            final StringEntity input = new StringEntity(data);
            input.setContentType("application/json");
            postRequest.setEntity(input);
            httpResponse = httpClient.execute(postRequest);
            if (httpResponse.getStatusLine().getStatusCode() != STATUS_CODE) {
                System.out.println("Problem in fetching credentials from BitlaMongo");
            }
            String restResponse = null;
            String output = null;
            br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
            while ((output = br.readLine()) != null) {
                restResponse = output;
            }
            restResponseToLogin = new JSONObject(restResponse);
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

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final JSONObject dataToConnection = new JSONObject();
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        try {
            final String action = request.getParameter("action");
            dataToConnection.put("action", action);
            if (action.equals("calculateSSBSingle") || action.equals("calculateSSBMarriedWorking")
                    || action.equals("calculateSSBMarriedNotWorking")) {
                dataToConnection.put("user_id", request.getParameter("user_id"));
                dataToConnection.put("userAnnaul_income", request.getParameter("userAnnaul_income"));
                dataToConnection.put("userRetirementAge", request.getParameter("userRetirementAge"));
                dataToConnection.put("user_age", request.getParameter("user_age"));
                dataToConnection.put("birth_year", request.getParameter("birth_year"));
                dataToConnection.put("userAime", request.getParameter("userAime"));
                if (action.equals("calculateSSBMarriedWorking")) {
                    dataToConnection.put("spouseAnnaul_income", request.getParameter("spouseAnnaul_income"));
                    dataToConnection.put("spouseRetirementAge", request.getParameter("spouseRetirementAge"));
                    dataToConnection.put("spouse_age", request.getParameter("spouse_age"));
                    dataToConnection.put("spouseAime", request.getParameter("spouseAime"));
                } else {
                    dataToConnection.put("spouseAnnaul_income", request.getParameter("spouseAnnaul_income"));
                    dataToConnection.put("spouseRetirementAge", request.getParameter("spouseRetirementAge"));
                    dataToConnection.put("spouse_age", request.getParameter("spouse_age"));
                }
                final JSONObject restResponse = SSBCalculator.connection(dataToConnection);
                if (restResponse.getString("status").equals("success")) {
                    out.println(restResponse);
                } else {
                    restResponse.put("status", "fail");
                    out.println(restResponse);
                }
            } else if (action.equals("fetchIncomeProfiles")) {
                dataToConnection.put("user_id", request.getParameter("user_id"));
                final JSONObject restResponse = SSBCalculator.connection(dataToConnection);
                if (restResponse.getString("status").equals("success")) {
                    out.println(restResponse);
                } else {
                    restResponse.put("status", "fail");
                    out.println(restResponse);
                }
            } else if (action.equals("calculateFromProfile")) {
                dataToConnection.put("user_id", request.getParameter("user_id"));
                dataToConnection.put("user_age", request.getParameter("user_age"));
                dataToConnection.put("birth_year", request.getParameter("birth_year"));
                dataToConnection.put("prof_name", request.getParameter("prof_name"));
                dataToConnection.put("userRetirementAge", request.getParameter("userRetirementAge"));
                dataToConnection.put("spouseWork", request.getParameter("spousework"));
                dataToConnection.put("goalMarried", request.getParameter("goalMarried"));

                final String martialStatus = request.getParameter("martialStatus");
                if (martialStatus.equals("Married")) {
                    dataToConnection.put("spouse_age", request.getParameter("spouse_age"));
                    dataToConnection.put("spouseRetirementAge", request.getParameter("spouseRetirementAge"));
                    dataToConnection.put("spouseAnnaul_income", request.getParameter("spouseAnnaul_income"));
                    dataToConnection.put("spouse_birth_year", request.getParameter("spouse_birth_year"));
                    dataToConnection.put("spouseAime", request.getParameter("spouseAime"));
                }
                dataToConnection.put("martialStatus", martialStatus);
                final JSONObject restResponse = SSBCalculator.connection(dataToConnection);
                if (restResponse.getString("status").equals("success")) {
                    out.println(restResponse);
                } else {
                    restResponse.put("status", "fail");
                    out.println(restResponse);
                }
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
