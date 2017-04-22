package com.olahtek.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

public class Register extends HttpServlet {
    private static HazelcastInstance hazelCastClient;
    private static final long serialVersionUID = 1L;
    private static final int MINUTES = 60;
    private static final int DAY = 24;
    private static final int STATUS_CODE = 200;
    private static Properties serviceProp;
    private static String restURLprop;
    private HttpSession session;
    private double spouseBeforeTaxIncome = 0;
    private static CloseableHttpClient httpClient;
    private static CloseableHttpResponse httpResponse;
    private static BufferedReader br;

    public Register() {
        try {
            Register.serviceProp = new Properties();
            final String home = System.getProperty("user.home");
            final String propFile = "application.properties";
            if (new File(home + "/." + propFile).exists()) {
                final FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
                Register.serviceProp.load(inputStream);
                inputStream.close();
                System.out.println("Application is reading configuration for Register Handler from " + home);
            } else {
                System.out.println("Application is reading configuration from resources for Register Handler");
            }
            final String cachehost = (String) Register.serviceProp.get("cacheHost");
            final String cachePort = (String) Register.serviceProp.get("cachePort");
            final ClientConfig clientConfig = new ClientConfig();
            clientConfig.getNetworkConfig().addAddress(cachehost + ":" + cachePort);
            Register.hazelCastClient = HazelcastClient.newHazelcastClient(clientConfig);
            Register.restURLprop = (String) Register.serviceProp.get("restURL");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject connection(final JSONObject dataToRest) {
        JSONObject restResponse = new JSONObject();
        httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            final String restURL = Register.restURLprop + "Register/";
            System.out.println("hello");
            final HttpPost postRequest = new HttpPost(restURL);
            System.out.println("DataTo REST"+dataToRest);
            final String data = dataToRest.toString();
            System.out.println("Rest URL: " + restURL + " Payload: " + data);
            final StringEntity input = new StringEntity(data);
            input.setContentType("application/json");
            postRequest.setEntity(input);
            httpResponse = httpClient.execute(postRequest);
            if (httpResponse != null) {
                if (httpResponse.getStatusLine().getStatusCode() != STATUS_CODE) {
                    System.out.println("Problem in fetching credentials from BitlaMongo");
                }
                String output = null;
                String jsonString = null;
                br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
                while ((output = br.readLine()) != null) {
                    jsonString = output;
                }
                br.close();
                restResponse = new JSONObject(jsonString);
                System.out.println("Rest response automation: " + restResponse);
            } else {
                restResponse.put("status", "fail");
                return restResponse;
            }
        } catch (final Exception e) {
            restResponse.put("status", "fail");
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return restResponse;
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = response.getWriter();
        final String formType = request.getParameter("form");
        String marriedStatus;
        String houseStatus;
        long realEstate = 0;
        session = request.getSession(false);
        String userId = (String) session.getAttribute("user_id");
        if (formType.equals("Userform")) {
            try {
                final String name = request.getParameter("username");
                final String lname = request.getParameter("userLname");
                final String email = request.getParameter("email");
                final String password = request.getParameter("password");
                final JSONObject dataToConnection = new JSONObject();
                dataToConnection.put("formType", formType);
                dataToConnection.put("name", name);
                dataToConnection.put("lname", lname);
                dataToConnection.put("email", email);
                dataToConnection.put("password", password);
                JSONObject restResponse = Register.connection(dataToConnection);
                final String status = restResponse.getString("status");
                if (status.equals("success")) {
                    userId = restResponse.getString("userid");
                    /*------------------Creating Sesssion And Cookies--------------------------*/
                    session = request.getSession();
                    final String sessionId = session.getId();
                    /*-----------------------Encryption.encrypt(session_id);-------------------------*/

                    final String encryptedsessionId = sessionId + "" + userId;
                    session.setAttribute("user_id", userId);
                    /*---------------------------Adding Cookies In Browser--------------------------- */
                    final Cookie sessionCookie = new Cookie("AhTwxlO", encryptedsessionId);
                    sessionCookie.setMaxAge(MINUTES * MINUTES * DAY); // 60*60*24
                                                                      // = 24
                                                                      // hrs
                    response.addCookie(sessionCookie);
                    /*-------------------------------Sending Session ID to Rest------------------------*/
                    dataToConnection.put("user_id", userId);
                    dataToConnection.put("action", "LogincheckCoockies");
                    dataToConnection.put("sessionID", encryptedsessionId);
                    restResponse = Register.connection(dataToConnection);
                    /*----------------------------------Sending response to UI---------------------------------*/
                    if (restResponse.getString("status").equals("success")) {
                        final Map<String, String> userDetails = Register.hazelCastClient.getMap("userDetails");
                        userDetails.put(email, userId);
                        out.println(restResponse);
                    }
                    /*----------------------------------Response Error TO UI------------------------------*/
                } else if (status.equals("registeredEmail")) {
                    out.println(restResponse);
                } else {
                    out.println(restResponse);
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        } else if (formType.equals("personalDetails")) {
            System.out.println("inside personal details=====");
            final JSONObject dataToConnection = new JSONObject();
            String filingStatus = null;
            int noofdependents = 0;
            int childCount = 0;
            final String city = request.getParameter("country");
            final String state = request.getParameter("city");
            final String county = request.getParameter("county");
            filingStatus = request.getParameter("filingStatus");
            final int age = Integer.parseInt(request.getParameter("uage"));
            marriedStatus = request.getParameter("married");
            dataToConnection.put("formType", formType);
            dataToConnection.put("user_id", userId);
            dataToConnection.put("city", city);
            dataToConnection.put("state", state);
            dataToConnection.put("county", county);
            dataToConnection.put("age", age);
            dataToConnection.put("filingStatus", filingStatus);
            dataToConnection.put("marital_status", marriedStatus);
            final Map<String, String> childrenDetails = new HashMap<String, String>();
            System.out.println("marriedStatus====" + marriedStatus);
            System.out.println("dependants====" + request.getParameter("dependants"));
            if (marriedStatus.equals("Yes")) {
                final String spouseName = request.getParameter("age");
                final int spouseAge = Integer.parseInt(request.getParameter("sage"));
                childCount = Integer.parseInt(request.getParameter("kidscount"));
                dataToConnection.put("spouseName", spouseName);
                dataToConnection.put("spouseAge", spouseAge);
            }

            if (request.getParameter("dependants") != null && request.getParameter("dependants") != "") {
                noofdependents = Integer.parseInt(request.getParameter("dependants"));
                dataToConnection.put("dependants", noofdependents);
            }

            if (request.getParameter("kidscount") != null && request.getParameter("kidscount") != "") {
                System.out.println("kidcounttttt====" + request.getParameter("kidscount"));
                childCount = Integer.parseInt(request.getParameter("kidscount"));
                dataToConnection.put("childCount", childCount);
            }

            // dataToConnection.put("childs", childrensDetails);

            System.out.print(childrenDetails);
            final JSONArray childrensDetails = new JSONArray();
            for (int i = 0; i < childCount; i++) {
                final String childName = request.getParameter("kids[" + i + "][name]");
                final int childAge = Integer.parseInt(request.getParameter("kids[" + i + "][age]"));
                final String childFlag = request.getParameter("kids[" + i + "][flag]");
                final JSONObject childDetails = new JSONObject();
                childDetails.put("name", childName);
                childDetails.put("age", childAge);
                childDetails.put("flag", childFlag);
                childrensDetails.put(childDetails);
                System.out.println("childCount in servlets====" + childCount);
                dataToConnection.put("childs", childrensDetails);
                System.out.println("dataToConnection====" + dataToConnection.toString());

            }

            final JSONObject restResponse = Register.connection(dataToConnection);
            final String status = restResponse.getString("status");
            if (status.equals("success")) {
                response.getWriter().write("success");
            } else {
                response.getWriter().write("fail");
            }
        } else if (formType.equals("calculateKidCost")) {

            final JSONObject dataToConnection = new JSONObject();
            marriedStatus = request.getParameter("married");
            final long userIncome = Long.parseLong(request.getParameter("userIncome"));
            long sum = 0;
            System.out.println("The marital status in coming from the javscript" + marriedStatus);
            if (marriedStatus.equals("Yes")) {
                final long spouceIncome = Long.parseLong(request.getParameter("spouceIncome"));
                sum = userIncome + spouceIncome;
            } else {
                sum = userIncome;
            }

            dataToConnection.put("formType", formType);
            dataToConnection.put("user_id", userId);
            dataToConnection.put("sum", sum);

            final JSONObject restResponse = Register.connection(dataToConnection);
            final String status = restResponse.getString("status");
            if (status.equals("success")) {
                out.println(restResponse);
            } else {
                response.getWriter().write("fail");
            }
        } else if (formType.equals("IncomeAndExpenses")) {
            marriedStatus = request.getParameter("married");
            session = request.getSession(false);
            final JSONObject dataToConnection = new JSONObject();
            final double userBeforeTaxIncome = Double.parseDouble(request.getParameter("tax"));
            final long kidCostCalculated = Long.parseLong(request.getParameter("kidCostCalculated"));
             double kidcostFactor = Double.parseDouble(request.getParameter("kidcostFactor"));
            System.out.println("kidcostFactor...."+kidcostFactor);
            if ((request.getParameter("stax") != null) && (!request.getParameter("stax").equals(""))) {
                spouseBeforeTaxIncome = Double.parseDouble(request.getParameter("stax"));
            }
            final double monthlyExpenses = Double.parseDouble(request.getParameter("expenses"));
            if(kidcostFactor==0&&monthlyExpenses==0)
            {
                kidcostFactor=0;
            }
            else if(kidcostFactor==0&&monthlyExpenses>0)
            {
                kidcostFactor=0;
            }
            else if(kidcostFactor>0&&monthlyExpenses==0)
            {
                kidcostFactor=0;
            }
            else
            {
            kidcostFactor =  kidcostFactor/monthlyExpenses;
            }
            houseStatus = request.getParameter("houseinfo");
            request.getParameter("mstatus");
            dataToConnection.put("formType", formType);
            dataToConnection.put("user_id", userId);
            dataToConnection.put("marriedStatus", marriedStatus);
            dataToConnection.put("userBeforeTaxIncome", userBeforeTaxIncome);
            dataToConnection.put("monthlyExpenses", monthlyExpenses);
            dataToConnection.put("houseStatus", houseStatus);
            dataToConnection.put("kidcostFactor", kidcostFactor);
            dataToConnection.put("kidCostCalculated", kidCostCalculated);
            dataToConnection.put("kidCostCalculatedArray", request.getParameter("kidCostCalculatedArray"));

            if (!(spouseBeforeTaxIncome == 0)) {
                System.out.print("IF CALLED IN INCOME AND EXPENSES FORM");
                dataToConnection.put("spouseBeforeTaxIncome", spouseBeforeTaxIncome);
            }
            double rentExpenses = 0;
            if ((request.getParameter("houserent") != null) && (request.getParameter("houserent") != "")) {
                rentExpenses = Double.parseDouble(request.getParameter("houserent"));
                dataToConnection.put("rentalExpenses", rentExpenses);
            } else {
                dataToConnection.put("rentalExpenses", rentExpenses);
            }
            System.out.println("dataToConnection"+dataToConnection);
            final JSONObject restResponse = Register.connection(dataToConnection);
            final String status = restResponse.getString("status");
            if (status.equals("success")) {
                response.getWriter().write("success");
            } else {
                response.getWriter().write("fail");
            }
        } else if (formType.equals("Assets")) {
            double user401k = 0;
            double userIRA = 0;
            double userRothIra = 0;
            double user529 = 0;
            double spouse401k = 0;
            double spouseIRA = 0;
            double spouseRothIra = 0;
            double spousePlan529 = 0;
            marriedStatus = request.getParameter("married");
            System.out.println("marriedStatus Ranjitha" + marriedStatus);
            final JSONObject dataToConnection = new JSONObject();
            if ((request.getParameter("realestate") != null) && (request.getParameter("realestate") != "")) {
                realEstate = Long.parseLong(request.getParameter("realestate"));
            }
            final double cash = Double.parseDouble(request.getParameter("cash"));
            final double taxableInvestments = Double.parseDouble(request.getParameter("Taxable_Investments"));
            final String nonTaxableInvestments = request.getParameter("Non_Taxable_Investments");
            houseStatus = request.getParameter("houseinfo");
            if (houseStatus.equals("Own")) {
                final double houseValue = Double.parseDouble(request.getParameter("houseValue"));
                dataToConnection.put("houseValue", houseValue);
                final double remainingMortgage = Double.parseDouble(request.getParameter("remainingMortgage"));
                final double houseAppreciationRate = Double.parseDouble(request.getParameter("houseAppreciationRate"));
                if (remainingMortgage > 0) {
                    final long remainingYearsMortgage = Long.parseLong(request.getParameter("remainingYearsMortgage"));
                    final double remainingMortgageInterestRate = Double
                            .parseDouble(request.getParameter("remainingMortgageInterestRate"));
                    System.out.println("remainingMortgageInterestRate===" + remainingMortgageInterestRate);
                    dataToConnection.put("remainingMortgage", remainingMortgage);
                    dataToConnection.put("remainingYearsMortgage", remainingYearsMortgage);
                    dataToConnection.put("remainingMortgageInterestRate", remainingMortgageInterestRate);
                    dataToConnection.put("houseAppreciationRate", houseAppreciationRate);
                }
            }
            System.out.println("Ranjitha :: mstatus" + request.getParameter("married"));
            if (nonTaxableInvestments.equals("Yes") && marriedStatus.equals("Yes")) {
                user401k = Double.parseDouble(request.getParameter("u401"));
                userIRA = Double.parseDouble(request.getParameter("uIRA"));
                userRothIra = Double.parseDouble(request.getParameter("uRothIra"));
                user529 = Double.parseDouble(request.getParameter("u529"));
                spouse401k = Double.parseDouble(request.getParameter("s401"));
                spouseIRA = Double.parseDouble(request.getParameter("sIRA"));
                spouseRothIra = Double.parseDouble(request.getParameter("sRothIra"));
                spousePlan529 = Double.parseDouble(request.getParameter("s529"));

            }
            if (nonTaxableInvestments.equals("Yes") && marriedStatus.equals("No")) {
                user401k = Double.parseDouble(request.getParameter("u401"));
                userIRA = Double.parseDouble(request.getParameter("uIRA"));
                userRothIra = Double.parseDouble(request.getParameter("uRothIra"));
                user529 = Double.parseDouble(request.getParameter("u529"));
            }

            dataToConnection.put("formType", formType);
            dataToConnection.put("user_id", userId);
            dataToConnection.put("cash", cash);
            dataToConnection.put("taxableInvestments", taxableInvestments);
            dataToConnection.put("nonTaxableInvestments", nonTaxableInvestments);
            dataToConnection.put("user401k", user401k);
            dataToConnection.put("userIRA", userIRA);
            dataToConnection.put("userRothIra", userRothIra);
            dataToConnection.put("user529", user529);
            dataToConnection.put("spouse401k", spouse401k);
            dataToConnection.put("spouseIRA", spouseIRA);
            dataToConnection.put("spouseRothIra", spouseRothIra);
            dataToConnection.put("spousePlan529", spousePlan529);
            dataToConnection.put("realEstate", realEstate);
            System.out.println("user529.." + user529);
            final JSONObject restResponse = Register.connection(dataToConnection);
            final String data = restResponse.getString("status");
            if (data.equals("success")) {
                response.getWriter().write("success");
            } else if (data.equals("personalDetails")) {
                response.getWriter().write("personalDetails_not filled");
            } else if (data.equals("IncomeAndExpenses")) {
                response.getWriter().write("IncomeAndExpenses_not_filled");
            } else if (data.equals("Assets")) {
                response.getWriter().write("Assets_not_filled");
            } else {
                out.println(restResponse);
            }
        }
        out.close();
    }
}
