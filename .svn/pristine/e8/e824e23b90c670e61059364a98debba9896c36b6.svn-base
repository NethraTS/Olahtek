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

public class Goalhouse extends HttpServlet {
    private static final long serialVersionUID = 1L;
    long spouseBeforeTaxIncome = 0;
    private static Properties serviceProp;
    private static String restURLProp;
    private static final long STATUS_CODE = 200;
    private static CloseableHttpClient httpClient;
    private HttpSession session;

    public Goalhouse() {
        try {
            Goalhouse.serviceProp = new Properties();
            final String home = System.getProperty("user.home");
            final String propFile = "application.properties";
            if (new File(home + "/." + propFile).exists()) {
                final FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
                Goalhouse.serviceProp.load(inputStream);
                inputStream.close();
                System.out.println("Application is reading configuration for Register Handler from " + home);
            } else {
                System.out.println("Application is reading configuration from resources for Register Handler");
            }
            Goalhouse.restURLProp = (String) Goalhouse.serviceProp.get("restURL");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject connection(JSONObject dataToRest) {
        final JSONObject obj = new JSONObject();
        obj.put("status", "fail");
        httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            final String restURL = Goalhouse.restURLProp + "Goalhouse/";
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = response.getWriter();
        session = request.getSession(false);
        final JSONObject dataToConnection = new JSONObject();
        final String city = request.getParameter("desired_locationcity");
        dataToConnection.put("city", city);
        int remainingTaxableAmt = 0;
        int remainingTaxable = 0;
        double profitOrLossCal = 0;
        double firstHousePrinciple = 0;
        long downPaymentForAccumulation = 0;
        int houseMarketPrice = 0;
        int brokerCommissionAmount = 0;
        int otherCost = 0;
        int propertyValForRent = 0;
        String rentalActivity = null;
        int rentalIncome = 0;
        int rentalExpense = 0;
        String frequency = "firstHouse";
        String loanPeriod = null;
        int goalDuration = 0;
        double appreciationRate = 0;
        double deprectionAmount=0;

        try {
            session = request.getSession(false);
            final String userId = (String) session.getAttribute("user_id");
            final String actionHomeType = request.getParameter("actionHomeType");
            final String planName = request.getParameter("plan_name");
            String creditScore = null;
            String loanType = null;
            if (request.getParameter("creditsc") != "") {
                creditScore = request.getParameter("creditsc");
            }
            if (request.getParameter("goalDuration") != "" && request.getParameter("goalDuration") != null) {
                loanPeriod = request.getParameter("goalDuration");
                loanType = loanPeriod;
                System.out.println("in servlerttt loanPeriod" + loanPeriod);
                if (loanPeriod.equals("30-year fixed") || loanPeriod.equals("jumbo 30-year fixed")) {
                    goalDuration = 30;
                } else if (loanPeriod.equals("15-year fixed") || loanPeriod.equals("jumbo 15-year fixed")) {
                    goalDuration = 15;
                } else if (loanPeriod.equals("3/1 ARM") || loanPeriod.equals("jumbo 3/1 ARM")) {
                    goalDuration = 3;
                } else {
                    goalDuration = 5;
                }
            }
            if (request.getParameter("appreciationRate") != null) {
                appreciationRate = Double.parseDouble(request.getParameter("appreciationRate"));
            }
            System.out.println("appriciation arte " + appreciationRate);
            dataToConnection.put("plan_name", planName);
            dataToConnection.put("creditScore", creditScore);
            dataToConnection.put("loanPeriod", loanPeriod);
            dataToConnection.put("goalDuration", goalDuration);
            dataToConnection.put("loanType", loanType);
            dataToConnection.put("appreciationRate", appreciationRate);
            System.out.println(
                    "creditScore.." + creditScore + "loanPeriod.." + loanPeriod + "goalDuration " + goalDuration);
            if (request.getParameter("frequency") != "" && request.getParameter("frequency") != null) {
                frequency = request.getParameter("frequency");
                System.out.println("frequency..123" + frequency);
                if (frequency.equals("Replace first house")) {
                    houseMarketPrice = Integer.parseInt(request.getParameter("houseMarketPrice"));
                    brokerCommissionAmount = Integer.parseInt(request.getParameter("brokerCommissionAmount"));
                    otherCost = Integer.parseInt(request.getParameter("otherCost"));
                    if ((request.getParameter("downPayment")) != null) {
                        remainingTaxableAmt = Integer.parseInt((request.getParameter("downPayment")));
                        if (request.getParameter("remainingTaxable") != null) {
                            remainingTaxable = Integer.parseInt((request.getParameter("remainingTaxable")));

                        }
                    }
                    dataToConnection.put("remainingTaxableAmt", remainingTaxableAmt);
                    dataToConnection.put("downPaymentForAccumulation", downPaymentForAccumulation);
                    dataToConnection.put("houseMarketPrice", houseMarketPrice);
                    dataToConnection.put("brokerCommissionAmount", brokerCommissionAmount);
                    dataToConnection.put("remainingTaxable", remainingTaxable);
                    dataToConnection.put("otherCost", otherCost);

                } else if (frequency.equals("Turn first house into a rental")) {
                    rentalActivity = request.getParameter("rentalActivity");
                    rentalIncome = Integer.parseInt(request.getParameter("rentalIncome"));
                    if (request.getParameter("propertyValForRent") != ""
                            && request.getParameter("propertyValForRent") != null) {
                        propertyValForRent = Integer.parseInt(request.getParameter("propertyValForRent"));
                    }
                    if (request.getParameter("profitOrLossCal") != ""
                            && request.getParameter("profitOrLossCal") != null) {
                        profitOrLossCal = Integer.parseInt(request.getParameter("profitOrLossCal"));
                        firstHousePrinciple = Double.parseDouble(request.getParameter("firstHousePrinciple"));
                        rentalExpense = Integer.parseInt(request.getParameter("rentalExpense"));
                    }
                    if (request.getParameter("deprectionAmount") != ""
                            && request.getParameter("deprectionAmount") != null) {
                        deprectionAmount=Double.parseDouble(request.getParameter("deprectionAmount"));
                    }
                    dataToConnection.put("rentalActivity", rentalActivity);
                    dataToConnection.put("rentalIncome", rentalIncome);
                    dataToConnection.put("propertyValForRent", propertyValForRent);
                    dataToConnection.put("profitOrLossCal", profitOrLossCal);
                    dataToConnection.put("firstHousePrinciple", firstHousePrinciple);
                    dataToConnection.put("rentalExpense", rentalExpense);
                    dataToConnection.put("deprectionAmount", deprectionAmount);

                } else if (frequency.equals("Turn second house into a rental")) {
                    if (request.getParameter("rentalIncome") != null) {
                        rentalActivity = request.getParameter("rentalActivity");
                        rentalIncome = Integer.parseInt(request.getParameter("rentalIncome"));
                    }
                    if (request.getParameter("propertyValForRent") != ""
                            && request.getParameter("propertyValForRent") != null) {
                        propertyValForRent = Integer.parseInt(request.getParameter("propertyValForRent"));
                        rentalExpense = Integer.parseInt(request.getParameter("rentalExpenses"));

                    }
                    dataToConnection.put("rentalActivity", rentalActivity);
                    dataToConnection.put("rentalIncome", rentalIncome);
                    dataToConnection.put("propertyValForRent", propertyValForRent);
                    dataToConnection.put("rentalExpense", rentalExpense);

                }

                System.out.println("frequency 123 " + frequency + " houseMarketPrice " + houseMarketPrice
                        + "brokerCommissionAmount" + brokerCommissionAmount + "propertyValForRent"
                        + propertyValForRent);
            }
            if (request.getParameter("downPaymentForAccumulation") != null
                    && request.getParameter("downPaymentForAccumulation") != "") {
                downPaymentForAccumulation = Long.parseLong(request.getParameter("downPaymentForAccumulation"));
            }
            System.out.println("ranj..remainingTaxableAmt" + remainingTaxableAmt + "frequency..." + frequency);
            dataToConnection.put("frequency", frequency);

            /*--------------------cal house value------------------------*/
            if (actionHomeType.equals("calhousevalue")) {
                final String location = request.getParameter("desired_location");
                final String county = request.getParameter("county");
                dataToConnection.put("county", county);
                dataToConnection.put("location", location);
                dataToConnection.put("actionHomeType", actionHomeType);
            } else if (actionHomeType.equals("edit")) {
                final String goal_id = request.getParameter("goal_id");
                creditScore = request.getParameter("creditsc");
                dataToConnection.put("user_id", userId);
                dataToConnection.put("goal_id", goal_id);
                dataToConnection.put("actionHomeType", actionHomeType);
            } else if (actionHomeType.equals("deleteGoal")) {
                System.out.println("deleting the goal in servlet");
                final String goal_id = request.getParameter("goal_id");
                final String location = request.getParameter("location");
                dataToConnection.put("user_id", userId);
                dataToConnection.put("plan_name", planName);
                dataToConnection.put("goal_id", goal_id);
                dataToConnection.put("location", location);
                dataToConnection.put("actionHomeType", actionHomeType);
            } else if (actionHomeType.equals("houseStatus")) {
                System.out.println("inside onload houseStatus in servlet");
                dataToConnection.put("actionHomeType", actionHomeType);
                dataToConnection.put("user_id", userId);
                dataToConnection.put("plan_name", planName);
            } else if (actionHomeType.equals("oldHouseRemaningMortgage")) {
                System.out.println("inside onload houseStatus in servlet");
                final String startYear = request.getParameter("goalYear");
                dataToConnection.put("actionHomeType", actionHomeType);
                dataToConnection.put("user_id", userId);
                dataToConnection.put("startYear", startYear);
                /*
                 * String frequency=request.getParameter("frequency");
                 * dataToConnection.put("frequency", frequency);
                 */
            } else if (actionHomeType.equals("ownHouseRemaningMortgage")) {
                System.out.println("inside servlet for remainnng mortgage");
                final String startYear = request.getParameter("goalYear");
                dataToConnection.put("actionHomeType", actionHomeType);
                dataToConnection.put("user_id", userId);
                dataToConnection.put("startYear", startYear);
            } else {
                System.out.println("in update servlet");
                if ((request.getParameter("location") == "") || (request.getParameter("downPayment") == "")
                        || (request.getParameter("principal_amount") == "")) {
                    final JSONObject restResponse = new JSONObject();
                    restResponse.put("status", "fail");
                    restResponse.put("message", "Please Insert Basic Information");
                    out.println(restResponse);
                    out.close();
                    return;
                }
                if (frequency.equals("Replace first house")) {
                    houseMarketPrice = Integer.parseInt(request.getParameter("houseMarketPrice"));
                    brokerCommissionAmount = Integer.parseInt(request.getParameter("brokerCommissionAmount"));
                    otherCost = Integer.parseInt(request.getParameter("otherCost"));
                    if ((request.getParameter("downPayment")) != null) {
                        remainingTaxableAmt = Integer.parseInt((request.getParameter("downPayment")));
                        if (request.getParameter("remainingTaxable") != null) {
                            remainingTaxable = Integer.parseInt((request.getParameter("remainingTaxable")));

                        }
                    }
                    dataToConnection.put("houseMarketPrice", houseMarketPrice);
                    dataToConnection.put("brokerCommissionAmount", brokerCommissionAmount);
                    dataToConnection.put("otherCost", otherCost);
                    dataToConnection.put("remainingTaxableAmt", remainingTaxableAmt);
                    dataToConnection.put("remainingTaxable", remainingTaxable);

                }
                System.out.println("remainingTaxable >> " + remainingTaxable);
                final String location = request.getParameter("location");
                final String startYear = request.getParameter("goalYear");
                final int downPaymentRate = Integer.parseInt((request.getParameter("downPayment")));
                final double principal_amount = Double.parseDouble(request.getParameter("principal_amount"));
                final String goaltype = "Home";
                dataToConnection.put("user_id", userId);
                dataToConnection.put("actionHomeType", actionHomeType);
                dataToConnection.put("location", location);
                dataToConnection.put("goalType", goaltype);
                dataToConnection.put("downPaymentRate", downPaymentRate);
                dataToConnection.put("principal_amount", principal_amount);
                dataToConnection.put("startYear", startYear);
            }
            if (actionHomeType.equals("update") || actionHomeType.equals("calInterestRate")) {
                String houseInsuranceAmount;
                float pmi;
                float taxrate;

                if ((request.getParameter("houseInsuranceAmount") == "")) {
                    houseInsuranceAmount = "500";
                } else {
                    houseInsuranceAmount = request.getParameter("houseInsuranceAmount");
                }
                if ((request.getParameter("PMI") == "")) {
                    pmi = (float) 0.625;
                } else {
                    pmi = Float.parseFloat((request.getParameter("PMI")));
                }
                if ((request.getParameter("taxrate") == "")) {
                    taxrate = 1;
                } else {
                    taxrate = Float.parseFloat((request.getParameter("taxrate")));
                }
                creditScore = request.getParameter("creditsc");
                final String goalId = request.getParameter("goal_id");
                dataToConnection.put("goal_id", goalId);
                dataToConnection.put("homeInsurance", houseInsuranceAmount);
                // dataToConnection.put("loanPreriod", loanPeriod);
                dataToConnection.put("pmi", pmi);
                // dataToConnection.put("interest", interest);
                dataToConnection.put("property_taxrate", taxrate);
            }
            dataToConnection.put("creditScore", creditScore);
            System.out.println("dataToConnection in servlet >>>> " + dataToConnection);
            final JSONObject restResponse = Goalhouse.connection(dataToConnection);
            System.out.println(restResponse);
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
