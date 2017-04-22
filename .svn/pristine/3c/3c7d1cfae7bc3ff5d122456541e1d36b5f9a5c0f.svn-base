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
import org.json.JSONArray;
import org.json.JSONObject;

public class GoalCollegeEducation extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static Properties serviceProp;
    public static String restURL;
    public static String zillowKey = "zws-id=X1-ZWz1a3at0fzi17_2599m";
    HttpSession session;
    long spouseBeforeTaxIncome = 0;
    private static CloseableHttpClient httpClient;

    public GoalCollegeEducation() {
        try {
            GoalCollegeEducation.serviceProp = new Properties();
            final String home = System.getProperty("user.home");
            final String propFile = "application.properties";
            if (new File(home + "/." + propFile).exists()) {
                final FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
                GoalCollegeEducation.serviceProp.load(inputStream);
                inputStream.close();
                System.out.println("Application is reading configuration for Register Handler from " + home);
            } else {
                System.out.println("Application is reading configuration from resources for Register Handler");
            }
            GoalCollegeEducation.restURL = (String) GoalCollegeEducation.serviceProp.get("restURL");
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
            final String restURL = GoalCollegeEducation.restURL + "GoalCollegeEducation/";
            final HttpPost postRequest = new HttpPost(restURL);
            final String Data = dataToRest.toString();
            System.out.println("Rest URL: " + restURL + " Payload: " + Data);
            final StringEntity input = new StringEntity(Data);
            input.setContentType("application/json");
            postRequest.setEntity(input);
            final HttpResponse httpResponse = httpClient.execute(postRequest);
            if (httpResponse != null) {
                if (httpResponse.getStatusLine().getStatusCode() != 200) {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = response.getWriter();

        session = request.getSession(false);
        final JSONObject dataToConnection = new JSONObject();
        try {
            session = request.getSession(false);
            final String user_id = (String) session.getAttribute("user_id");
            final String userName = (String) session.getAttribute("userName");
            final String actionHomeType = request.getParameter("actionHomeType");
            String plan_name = request.getParameter("plan_name");
            final String kidname = request.getParameter("Goalname");
            dataToConnection.put("plan_name", plan_name);
            if (actionHomeType.equals("edit")) {
                final String goal_id = request.getParameter("goal_id");
                //String  RealAnnualCollegeCostOverTime= request.getParameter("RealAnnualCollegeCostOverTime");
                //int PercentageCollegeCost = Integer.parseInt(request.getParameter("PercentageCollegeCost"));
                dataToConnection.put("user_id", user_id);
                dataToConnection.put("goal_id", goal_id);
                //dataToConnection.put("RealAnnualCollegeCostOverTime", RealAnnualCollegeCostOverTime);
                //System.out.println("raviiiiiiiiiii222222222"+RealAnnualCollegeCostOverTime);
                //dataToConnection.put("PercentageCollegeCost", PercentageCollegeCost);
                //System.out.println("raviiiiiiiiiii222222222"+PercentageCollegeCost);
                dataToConnection.put("actionHomeType", actionHomeType);
            } else if (actionHomeType.equals("deleteGoal")) {
                System.out.println("deleting the goal in servlet");
                final String goal_id = request.getParameter("goal_id");
                plan_name = request.getParameter("plan_name");
                dataToConnection.put("user_id", user_id);
                dataToConnection.put("plan_name", plan_name);
                dataToConnection.put("goal_id", goal_id);
                dataToConnection.put("actionHomeType", actionHomeType);
            } else if (actionHomeType.equals("getYearArray")) {
                dataToConnection.put("user_id", user_id);
                dataToConnection.put("kidName", kidname);
                dataToConnection.put("actionHomeType", actionHomeType);
            } else if (actionHomeType.equals("getKidData")) {
                request.getParameter("goalName");
                final String fin_Id = request.getParameter("fin_Id");
                dataToConnection.put("user_id", user_id);
                dataToConnection.put("fin_Id", fin_Id);
                dataToConnection.put("actionHomeType", actionHomeType);
            }
            else if (actionHomeType.equals("getPlan529Amount")) {
                request.getParameter("goalName");
                final String fin_Id = request.getParameter("fin_Id");
                dataToConnection.put("user_id", user_id);
                dataToConnection.put("fin_Id", fin_Id);
                dataToConnection.put("actionHomeType", actionHomeType);
                final Integer kidCollegeYear = Integer.parseInt(request.getParameter("KidCollegeYear"));
                dataToConnection.put("kidCollegeYear", kidCollegeYear);
            }else {
                final Integer kidCollegeYear = Integer.parseInt(request.getParameter("KidCollegeYear"));
                final String calPlan529 = request.getParameter("plan529demo");
                dataToConnection.put("calPlan529", calPlan529);
                if (calPlan529.equals("false")) {
                    final double plan529Saved = Double.parseDouble(request.getParameter("plan529Saved"));
                    dataToConnection.put("plan529Saved", plan529Saved);
                }
                request.getParameter("goalName");
                request.getParameter("fin_Id");
                final String collegeType = request.getParameter("CollegeType");
                final long collegeEducationAmount = Long.parseLong(request.getParameter("CollegeEducationAmount"));
                final String  RealAnnualCollegeCostOverTime= request.getParameter("RealAnnualCollegeCostOverTime");
                //String  collegeCostToServlet= request.getParameter("collegeCostToServlet");
                //System.out.println("raviKiran###"+RealAnnualCollegeCostOverTime);
                final double percentageCollegeCost = Double.parseDouble(request.getParameter("PercentageCollegeCost"));
                final int collegeEducationAmountPercentage = Integer.parseInt(request.getParameter("CollegeEducationAmountPercentage"));
                final String collegeCostToServlet=request.getParameter("collegeCostToServlet");
                System.out.println("college cost"+collegeCostToServlet);
                final JSONArray collegeGoalAmountData = new JSONArray(collegeCostToServlet);
                System.out.println("collegeGoalAmountData "+collegeGoalAmountData+"percentageCollegeCost >> "+percentageCollegeCost);
                final String goaltype = "College Education";
                dataToConnection.put("user_id", user_id);
                dataToConnection.put("actionHomeType", actionHomeType);
                dataToConnection.put("userName", userName);
                dataToConnection.put("kidCollegeYear", kidCollegeYear);
                dataToConnection.put("goalType", goaltype);
                dataToConnection.put("kidName", kidname);
                dataToConnection.put("collegeType", collegeType);
                dataToConnection.put("collegeEducationAmount", collegeEducationAmount);
                dataToConnection.put("RealAnnualCollegeCostOverTime", RealAnnualCollegeCostOverTime);
                dataToConnection.put("percentageCollegeCost", percentageCollegeCost);
                dataToConnection.put("collegeEducationAmountPercentage", collegeEducationAmountPercentage);
                dataToConnection.put("collegeGoalAmountData", collegeGoalAmountData);

            }
            if (actionHomeType.equals("update")) {
                request.getParameter("houseInsuranceAmount");
                final String collegeType = request.getParameter("CollegeType");
                final String Goalname = request.getParameter("Goalname");
                final String  RealAnnualCollegeCostOverTime= request.getParameter("RealAnnualCollegeCostOverTime");
                final double PercentageCollegeCost = Double.parseDouble(request.getParameter("PercentageCollegeCost"));
                final String goal_id = request.getParameter("goal_id");
                final String collegeCostToServlet=request.getParameter("collegeCostToServlet");
                System.out.println("college cost update"+collegeCostToServlet);
                final JSONArray collegeGoalAmountData = new JSONArray(collegeCostToServlet);
                System.out.println("college cost update in servlet..// "+collegeGoalAmountData);
                dataToConnection.put("collegeGoalAmountData", collegeGoalAmountData);
                dataToConnection.put("goal_id", goal_id);
                dataToConnection.put("kidname", kidname);
                dataToConnection.put("RealAnnualCollegeCostOverTime", RealAnnualCollegeCostOverTime);
                //System.out.println("raviiiiiiiiiii"+RealAnnualCollegeCostOverTime);
                dataToConnection.put("PercentageCollegeCost", PercentageCollegeCost);
                //System.out.println("raviiiiiiiiiii"+PercentageCollegeCost);
                dataToConnection.put("PercentageCollegeCost", Goalname);
                //System.out.println("raviiiiiiiiiii"+Goalname);
                dataToConnection.put("collegeType", collegeType);
                //System.out.println("raviiiiiiiiiii"+collegeType);
                final String calPlan529 = request.getParameter("plan529demo");
                dataToConnection.put("calPlan529", calPlan529);
                if (calPlan529.equals("false")) {
                    final double plan529Saved = Double.parseDouble(request.getParameter("plan529Saved"));
                    System.out.println("Bala...Servlet"+plan529Saved);
                    dataToConnection.put("plan529Saved", plan529Saved);
                }
            }
            final JSONObject rest_response = GoalCollegeEducation.connection(dataToConnection);
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
