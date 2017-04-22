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

public class MortgageCalculator extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static Properties serviceProp;
    public static String restURL;
    HttpSession session;
    private static CloseableHttpClient httpClient;
    private static CloseableHttpResponse httpResponse;
    private PrintWriter out;
    private static BufferedReader br;

    public MortgageCalculator() {
        super();
        try {
            MortgageCalculator.serviceProp = new Properties();
            final String home = System.getProperty("user.home");
            final String propFile = "application.properties";
            if (new File(home + "/." + propFile).exists()) {
                final FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
                MortgageCalculator.serviceProp.load(inputStream);
                inputStream.close();
                System.out.println("Application is reading configuration for Register Handler from " + home);
            } else {
                System.out.println("Application is reading configuration from resources for Register Handler");
            }
            MortgageCalculator.restURL = (String) MortgageCalculator.serviceProp.get("restURL");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject connection(final JSONObject dataToRest) {
        JSONObject restResponseToLogin = new JSONObject();
        httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            final String restURL = MortgageCalculator.restURL + "MC/";
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
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = response.getWriter();
        final String action = request.getParameter("action");
        // String action1 = request.getParameter("action1");
        System.out.println("actionHomeType-->>" + action);
        session = request.getSession(false);
        final JSONObject dataToConnection = new JSONObject();
        try {
            session = request.getSession(false);
            final String user_id = request.getParameter("user_id"); /*(String) session.getAttribute("user_id")*/;
            System.out.println("user_id>>" + user_id);
            System.out.println("Action type passed" + action);
            if (action.equals("edit1")) {
                dataToConnection.put("action", action);
                final String forrental = request.getParameter("for_rental");
                final String managementfee = request.getParameter("management_fee");
                final String rentalincome = request.getParameter("rental_income");
                final String propertyland = request.getParameter("property_land");
                final String state = request.getParameter("state");
                final String city = request.getParameter("city");
                final String homevalue = request.getParameter("home_value");
                final String downpayment = request.getParameter("down_payment");
                final String interestrate = request.getParameter("interest_rate");
                final String loanterm = request.getParameter("loan_term");
                final String propertytax = request.getParameter("property_tax");
                final String pmi = request.getParameter("pmi");
                final String propertytype = request.getParameter("property_type");
                final String hoafee = request.getParameter("hoa_fee");
                final String maintenance = request.getParameter("maintenance");
                final String homeinsurance = request.getParameter("home_insurance");
                final String filingstatus = request.getParameter("filing_status");
                final String standarddeduction = request.getParameter("standard_deduction");
                final String highesttaxrate = request.getParameter("highest_taxrate");
                final String flag = request.getParameter("flag");

                System.out.println("flag____>>" + flag);

                /* String federaltax = request.getParameter("federal_tax"); */
                System.out.println("user_id inside iif " + user_id);
                System.out.println("mai:" + maintenance);
                dataToConnection.put("forrental", forrental);
                dataToConnection.put("managementfee", managementfee);
                dataToConnection.put("rentalincome", rentalincome);
                dataToConnection.put("propertyland", propertyland);
                dataToConnection.put("state", state);
                dataToConnection.put("city", city);
                dataToConnection.put("homevalue", homevalue);
                dataToConnection.put("downpayment", downpayment);
                dataToConnection.put("interestrate", interestrate);
                dataToConnection.put("loanterm", loanterm);
                dataToConnection.put("propertytax", propertytax);
                dataToConnection.put("pmi", pmi);
                dataToConnection.put("propertytype", propertytype);
                dataToConnection.put("hoafee", hoafee);
                dataToConnection.put("maintenance", maintenance);
                dataToConnection.put("homeinsurance", homeinsurance);
                dataToConnection.put("filingstatus", filingstatus);
                dataToConnection.put("standarddeduction", standarddeduction);
                dataToConnection.put("highesttaxrate", highesttaxrate);
                dataToConnection.put("user_id", user_id);
                dataToConnection.put("flag", flag);

                /*
                 * dataToConnection.put("federaltax", federaltax);
                 */

            }

            else if (action.equals("getHomeInsurance")) {

                dataToConnection.put("action", action);
                final String states = request.getParameter("states");
                dataToConnection.put("states", states);

            }

            else if (action.equals("getHomeValue")) {

                dataToConnection.put("action", action);
                final String citys = request.getParameter("citys");
                final String states = request.getParameter("states");
                final String county = request.getParameter("county");
                dataToConnection.put("city", citys);
                dataToConnection.put("states", states);
                dataToConnection.put("county", county);
                System.out.println("dataToConnection:: " + dataToConnection);

            }

            else if (action.equals("getLoanTerm")) {

                dataToConnection.put("action", action);
                final String loanterm = request.getParameter("loanTerm");
                final String state = request.getParameter("state");

                dataToConnection.put("loanterm", loanterm);
                dataToConnection.put("state", state);
                System.out.println("statestate:: " + state);
                System.out.println("loanterm:: " + loanterm);

            }

            final JSONObject rest_response = MortgageCalculator.connection(dataToConnection);
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
