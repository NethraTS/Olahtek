package com.mongorest.olahtek.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongorest.olahtek.model.Cargoalmodel;
import com.mongorest.olahtek.model.Counters;
import com.mongorest.olahtek.model.Emergencyfundmodel;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.RateAdjustmentFactor;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.User;

@Service("CarGoalImpl")
@Transactional
public class CarGoalImplementation {
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private final Date date = new Date();

    private final DecimalFormat decimalFloat = new DecimalFormat("#.##");
    private static final double PERCENTAGECONSTANT = 100.0;
    private static final int MONTHS = 12;
    private static final double MONEYFACTORCONSTANT = 2400;
    private static final int RETAIRMENTAGE = 70;
    private static final int GOALWITHINFIVEYEARS = 4;
    private static final double GOALWITHINFIVEYEARSARM = 3.30;
    private static final int GOALWITHINFIVEYEARSINMONTHS = 48;
    private static final int TIMEPERIODFORTHREEYEARS = 3;
    private static final double TIMEPERIODFORTHREEYEARSARM = 3.31;
    private static final int TIMEPERIODFORTHREEYEARSINMONTHS = 36;
    private static final int DEFAULTYEARSINMONTHS = 60;
    private static final double DEFAULTYEARSINMONTHSARM = 3.28;

    @JsonCreator
    public JSONObject insertCarGoalData(final Cargoalmodel cargoalmodel) {
        final JSONObject responseToRestController = new JSONObject();
        String goalId = cargoalmodel.getGoal_id();
        final String userId = cargoalmodel.getUser_id();
        final String planName = cargoalmodel.getPlan_name();
        long carPrice = cargoalmodel.getCarPrice();
        int carYear = cargoalmodel.getCarYear();

        final String rentLease = cargoalmodel.getRentLease();
        int timePeriod = cargoalmodel.getTimePeriod();
        double intrestRate = cargoalmodel.getIntrestRate();
        String creditScore = cargoalmodel.getCreditScore();
        final double moneyFactor = cargoalmodel.getMoneyFactor();
        final int residualValue = cargoalmodel.getResidualValue();
        final int leaseYear = cargoalmodel.getLeaseYear();
        final int startYear = cargoalmodel.getCarYear();
        final int endYear = cargoalmodel.getLeaseYear() - 1 + startYear;
        final int endYear1 = cargoalmodel.getTimePeriod() - 1 + startYear;
        final Calendar cal = Calendar.getInstance();
        final int currentYear = cal.get(Calendar.YEAR);
        String carReccursive=cargoalmodel.getCarReccursive();
        final int reccursivePeriod=cargoalmodel.getReccursivePeriod();

        System.out.println("0 reccusive option "+carReccursive+" "+reccursivePeriod);
        JSONArray carExpenseDb=new JSONArray();
        try {
            responseToRestController.put("status", "fail");
            final String actionHomeType = cargoalmodel.getActionHomeType();
            if (actionHomeType.equals("insert")) {
                System.out.println("lll " + userId + "  " + planName);
                final FinPlan finDetails = MongoDBConnection.finplancol
                        .findOne("{usr_id:#,plan_name:#}", userId, planName).as(FinPlan.class);
                System.out.println(" find.. " + finDetails);
                final String finId = finDetails.get_id();
                final User details = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
                final int userAge = details.getAge();
                final String maritalStatus = finDetails.getMarital_status();
                final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
                final JSONArray assets = finPlanJson.getJSONArray("assests");
                final JSONArray tax = finPlanJson.getJSONArray("tax");
                final JSONArray limits = finPlanJson.getJSONArray("limits");
                final JSONArray deductions = finPlanJson.getJSONArray("deductions");
                final JSONArray fillingExemtion = finPlanJson.getJSONArray("fillingExemtion");
                final JSONArray userExpense = finPlanJson.getJSONArray("userExpense");
                final JSONArray kidBirthYear = finPlanJson.getJSONArray("kidBirthYear");
                JSONObject expense = new JSONObject();
                expense = finPlanJson.getJSONObject("expense");
                JSONArray housingExpense = new JSONArray();
                if (!expense.isNull("housing_expense")) {
                    housingExpense = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
                }
                final String finPlanProfileName = finDetails.getProfile_name();
                double monthlyPayment = cargoalmodel.getMonthlyPayment();
                double exactAnualMorgage = cargoalmodel.getExactAnual_morgage();
                double annualCost = cargoalmodel.getAnnualCost();
                if (rentLease.equals("Leasing")) {
                    int j=0;
                    try {
                        final double x = residualValue / PERCENTAGECONSTANT * carPrice;
                        final double r = moneyFactor * MONEYFACTORCONSTANT / MONTHS / PERCENTAGECONSTANT;
                        final int n = leaseYear * MONTHS;
                        monthlyPayment = calAnualMorgage(x, r, n, carPrice);
                        annualCost = monthlyPayment * MONTHS;
                        if(carReccursive.equals("LeasingNo")){
                            for (int i = 0; i < limits.length(); i++) {

                                if (limits.getJSONObject(i).getInt("year") < carYear) {
                                    limits.getJSONObject(i).put("saving",
                                            annualCost * leaseYear + limits.getJSONObject(i).getInt("saving"));
                                } else if (limits.getJSONObject(i).getInt("year") >= carYear
                                        && limits.getJSONObject(i).getInt("year") < carYear + leaseYear - 1) {
                                    limits.getJSONObject(i).put("saving",
                                            limits.getJSONObject(i).getInt("saving") + annualCost
                                            * (carYear + leaseYear - limits.getJSONObject(i).getInt("year") - 1));
                                }

                                if (deductions.getJSONObject(i).getInt("year") >= carYear
                                        && deductions.getJSONObject(i).getInt("year") < carYear + leaseYear) {

                                    deductions.getJSONObject(i).put("saving",
                                            annualCost + deductions.getJSONObject(i).getDouble("saving"));
                                }

                            }
                        }
                        else
                        {
                            for (int i = 0; i < limits.length(); i++) {

                                if (limits.getJSONObject(i).getInt("year") < carYear) {
                                    limits.getJSONObject(i).put("saving",
                                            annualCost * leaseYear + limits.getJSONObject(i).getInt("saving"));
                                } else if (limits.getJSONObject(i).getInt("year") >= carYear
                                        && limits.getJSONObject(i).getInt("year") < carYear + leaseYear - 1) {
                                    limits.getJSONObject(i).put("saving",
                                            limits.getJSONObject(i).getInt("saving") + annualCost
                                            * (carYear + leaseYear - limits.getJSONObject(i).getInt("year") - 1));
                                }

                                if (deductions.getJSONObject(i).getInt("year") >= carYear
                                        && deductions.getJSONObject(i).getInt("year") < carYear + leaseYear) {

                                    deductions.getJSONObject(i).put("saving",
                                            annualCost + deductions.getJSONObject(i).getDouble("saving"));
                                }
                                if(limits.getJSONObject(i).getInt("year")==carYear && j<reccursivePeriod)
                                {
                                    carYear=carYear+leaseYear;
                                    j++;
                                    System.out.println(" j "+j);
                                }
                                System.out.println("carYear..."+carYear);
                            }
                        }
                        int spouseAge;
                        final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                                .findOne("{user_id:#,profile_name:#}", userId, finPlanProfileName)
                                .as(IncomeProfile.class);
                        final JSONObject incomeProfileJson = new JSONObject(
                                jsonMapper.writeValueAsString(incomeProfileDetails));
                        final JSONArray userIncome = incomeProfileJson.getJSONArray("user_income");
                        JSONArray spouseIncomeArray = new JSONArray();
                        JSONArray combinedIncome = new JSONArray();
                        if (maritalStatus.equals("Yes")) {
                            spouseIncomeArray = incomeProfileJson.getJSONArray("spouse_income");
                            final IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
                            combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, spouseIncomeArray);
                            spouseAge = finDetails.getSpouseAge();
                        } else {
                            combinedIncome = userIncome;
                            spouseAge = 0;
                        }
                        final int emergencyFundAmt = finDetails.getEmergencyFundAmt();
                        final boolean emergencyFundFlag = finDetails.isEmergencyFundFlag();

                        final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                                .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement")
                                .as(RetirementGoal.class);
                        int spouseStartYear = 0;
                        int userStartYear = 0;

                        if (retirementObj != null) {
                            userStartYear = details.getBirthYear() + retirementObj.getRetirementAge();
                            if (finDetails.getMarital_status().equals("Yes")
                                    && details.getMarital_status().equals("No")) {
                                spouseStartYear = finDetails.getSpouseBirthYear()
                                        + retirementObj.getSpouseRetirementAge();
                            } else if (details.getMarital_status().equals("Yes")) {
                                spouseStartYear = details.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                            }
                        } else {
                            userStartYear = details.getBirthYear() + RETAIRMENTAGE;
                            if (finDetails.getMarital_status().equals("Yes")
                                    && details.getMarital_status().equals("No")) {
                                spouseStartYear = finDetails.getSpouseBirthYear() + RETAIRMENTAGE;
                            } else if (details.getMarital_status().equals("Yes")) {
                                spouseStartYear = details.getSpouseBirthYear() + RETAIRMENTAGE;
                            }
                        }
                        final JSONObject retirementData = new JSONObject();
                        retirementData.put("spouseStartYear", spouseStartYear);
                        retirementData.put("userStartYear", userStartYear);
                        String emergencyType = null;
                        String monthsOfIncome = null;
                        String monthsOfExpense = null;
                        if (emergencyFundFlag) {
                            final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                                    .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Emergency Fund")
                                    .as(Emergencyfundmodel.class);
                            emergencyType = emergencyObj.getTimePeriod();
                            monthsOfIncome = emergencyObj.getMonthI();
                            monthsOfExpense = emergencyObj.getMonthE();
                        }
                        final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                                spouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, userId,
                                fillingExemtion, userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions,
                                kidBirthYear, retirementData, retirementObj, housingExpense, emergencyType,
                                monthsOfIncome, monthsOfExpense, finDetails.isRetirementFlag());
                        final String status = result.getString("status");
                        if (status.equals("success")) {
                            carExpenseDb=null;
                            String goalName;
                            final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                            goalId = "goal" + count.getGoal_id();
                            goalName=cargoalmodel.getGoalType()+ " - " + goalId;
                            final BasicDBObject doc = new BasicDBObject("_id", goalId)
                                    .append("user_id", cargoalmodel.getUser_id()).append("fin_id", finId)
                                    .append("plan_name", cargoalmodel.getPlan_name())
                                    .append("goalType", cargoalmodel.getGoalType())
                                    .append("carYear", cargoalmodel.getCarYear()).append("residualValue", residualValue)
                                    .append("carPrice", cargoalmodel.getCarPrice())
                                    .append("rentLease", cargoalmodel.getRentLease())
                                    .append("moneyFactor", cargoalmodel.getMoneyFactor())
                                    .append("leaseYear", cargoalmodel.getLeaseYear())
                                    .append("goalName", goalName)
                                    .append("monthlyPayment", decimalFloat.format(monthlyPayment))
                                    .append("annualCost", annualCost).append("created_ts", dateFormat.format(date))
                                    .append("carReccursive", cargoalmodel.getCarReccursive())
                                    .append("reccursivePeriod", reccursivePeriod)
                                    .append("completedStatus", 1).append("modified_ts", dateFormat.format(date));
                            doc.append("goalFeasibility", true);
                            MongoDBConnection.goalcoll.insert(doc);
                            MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                            .with("{$inc: {goal_id: 1}}");
                            MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                            .with("{$addToSet: {'goals':'" + goalId + "'}}");
                            /*                            updateFinplanForCarGoal(finId, userId, goalId, startYear, endYear, endYear1, monthlyPayment,
                                    carPrice, annualCost, exactAnualMorgage, rentLease, "insert", 0,goalName);*/
                            final JSONObject carExpenseData = new JSONObject();
                            final JSONArray carExpense = new JSONArray();
                            carExpenseData.put("endYear", endYear);
                            carExpenseData.put("startYear", startYear);
                            carExpenseData.put("monthlyPayment", monthlyPayment);
                            carExpenseData.put("annualCost", annualCost);
                            carExpenseData.put("goalName", goalName);
                            carExpenseData.put("carReccursive", carReccursive);
                            carExpenseData.put("reccursivePeriod", reccursivePeriod);
                            carExpense.put(carExpenseData);
                            if (!finPlanJson.isNull("expense")) {
                                final JSONObject expenseInFinPlanCol = finPlanJson.getJSONObject("expense");
                                if (!expenseInFinPlanCol.isNull("carGoalExpense")) {
                                    carExpenseDb = expenseInFinPlanCol.getJSONArray("carGoalExpense");
                                    //// System.out.println("kidExpense --> inside kid
                                    //// not eqal null "+kidExpense);
                                }
                            }

                            if (carExpenseDb == null) {
                                //// System.out.println("kid expance not null in kid
                                //// goal");
                                MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                                .with("{$set: {'expense.carGoalExpense':" + carExpense + "}}");
                            } else {
                                //// System.out.println("kid expance not null eqal null
                                //// kid goal");
                                carExpenseDb.put(carExpenseData);
                                MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                                .with("{$set: {'expense.carGoalExpense':" + carExpenseDb + "}}");
                            }
                            MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                            .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits
                                    + ",'deductions':" + deductions + "}}");
                            responseToRestController.put("status", "success");
                            responseToRestController.put("goal_id", goalId);
                            responseToRestController.put("monthlyPayment", decimalFloat.format(monthlyPayment));
                            responseToRestController.put("annualCost", decimalFloat.format(annualCost));
                            responseToRestController.put("carYear", cargoalmodel.getCarYear());
                            responseToRestController.put("carPrice", cargoalmodel.getCarPrice());
                            responseToRestController.put("rentLease", cargoalmodel.getCarPrice());
                            responseToRestController.put("leaseYear", cargoalmodel.getCarPrice());
                            responseToRestController.put("residualValue", residualValue);

                            final JSONObject systemLog = new JSONObject();
                            systemLog.put("type", "success");
                            systemLog.put("message", "Goal Created Successfully!!");
                            systemLog.put("userName", details.getName());
                            systemLog.put("user_id", userId);
                            systemLog.put("createdTs", dateFormat.format(date));
                        }
                    } catch (final Exception e) {
                        final JSONObject systemLog = new JSONObject();
                        systemLog.put("type", "error");
                        systemLog.put("message", "Goal Creation Failed!!");
                        systemLog.put("userName", details.getName());
                        systemLog.put("user_id", userId);
                        systemLog.put("createdTs", dateFormat.format(date));
                        e.printStackTrace();
                    } finally {
                        final BasicDBObject systemLogBasicObject = new BasicDBObject("user_id", userId)
                                .append("userName", details.getName())
                                .append("message", "Error could not create leasing of the goal")
                                .append("createdTs", dateFormat.format(date));
                        MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);
                    }
                    return responseToRestController;
                }
                if (rentLease.equals("Buying")) {
                    try {
                        final int reccursiveBuyingPeriod = cargoalmodel.getReccursiveBuyingPeriod();
                        intrestRate = calculateIntrestRate(timePeriod, creditScore);
                        System.out.println("Trade In :: "+cargoalmodel.getTradeIn());
                        carPrice = carPrice - cargoalmodel.getTradeIn();
                        final int downPayment = cargoalmodel.getDownPayment();
                        final double amount = (downPayment * carPrice / 100);
                        final String carBuyingReccursive = cargoalmodel.getCarBuyingReccursive();
                        final double mortgageAmount = carPrice - amount;
                        exactAnualMorgage = calAnualMorgage1(timePeriod, intrestRate, mortgageAmount);
                        if(carBuyingReccursive.equals("BuyingNo")) {
                            for (int i = 0; i < limits.length(); i++) {
                                if (carYear > currentYear + GOALWITHINFIVEYEARS) {
                                    if (limits.getJSONObject(i).getInt("year") < carYear) {
                                        limits.getJSONObject(i).put("taxable", exactAnualMorgage * timePeriod
                                                + limits.getJSONObject(i).getInt("taxable") + amount);
                                    } else if (limits.getJSONObject(i).getInt("year") >= carYear
                                            && limits.getJSONObject(i).getInt("year") < carYear + timePeriod - 1) {
                                        limits.getJSONObject(i).put("taxable",
                                                limits.getJSONObject(i).getInt("taxable") + exactAnualMorgage
                                                * (carYear + timePeriod - limits.getJSONObject(i).getInt("year")));
                                    }
                                    if (deductions.getJSONObject(i).getInt("year") == carYear) {
                                        deductions.getJSONObject(i).put("taxable", exactAnualMorgage + amount
                                                + deductions.getJSONObject(i).getDouble("taxable"));
                                    } else if (deductions.getJSONObject(i).getInt("year") > carYear
                                            && deductions.getJSONObject(i).getInt("year") < carYear + timePeriod) {

                                        deductions.getJSONObject(i).put("taxable",
                                                exactAnualMorgage + deductions.getJSONObject(i).getDouble("taxable"));
                                    }
                                } else {
                                    if (limits.getJSONObject(i).getInt("year") < carYear) {
                                        limits.getJSONObject(i).put("saving", exactAnualMorgage * timePeriod
                                                + limits.getJSONObject(i).getInt("saving") + amount);
                                    } else if (limits.getJSONObject(i).getInt("year") >= carYear
                                            && limits.getJSONObject(i).getInt("year") < carYear + timePeriod - 1) {
                                        limits.getJSONObject(i).put("saving",
                                                limits.getJSONObject(i).getInt("saving") + exactAnualMorgage * (carYear
                                                        + timePeriod - limits.getJSONObject(i).getInt("year") - 1));
                                    }
                                    if (deductions.getJSONObject(i).getInt("year") == carYear) {
                                        deductions.getJSONObject(i).put("saving", exactAnualMorgage + amount
                                                + deductions.getJSONObject(i).getDouble("saving"));
                                    } else if (deductions.getJSONObject(i).getInt("year") > carYear
                                            && deductions.getJSONObject(i).getInt("year") < carYear + timePeriod) {

                                        deductions.getJSONObject(i).put("saving",
                                                exactAnualMorgage + deductions.getJSONObject(i).getDouble("saving"));
                                    }
                                }

                            }
                        } else {
                            int j=0;
                            for (int i = 0; i < limits.length(); i++) {

                                if (limits.getJSONObject(i).getInt("year") < carYear) {
                                    limits.getJSONObject(i).put("saving",
                                            exactAnualMorgage * timePeriod + limits.getJSONObject(i).getInt("saving") + amount);
                                } else if (limits.getJSONObject(i).getInt("year") >= carYear
                                        && limits.getJSONObject(i).getInt("year") < carYear + timePeriod - 1) {
                                    limits.getJSONObject(i).put("saving",
                                            limits.getJSONObject(i).getInt("saving") + exactAnualMorgage
                                            * (carYear + timePeriod - limits.getJSONObject(i).getInt("year") - 1));
                                }

                                if (deductions.getJSONObject(i).getInt("year") >= carYear
                                        && deductions.getJSONObject(i).getInt("year") < carYear + timePeriod) {

                                    deductions.getJSONObject(i).put("saving",
                                            exactAnualMorgage + amount + deductions.getJSONObject(i).getDouble("saving"));
                                }
                                if(limits.getJSONObject(i).getInt("year")==carYear && j<reccursiveBuyingPeriod)
                                {
                                    carYear=carYear+timePeriod;
                                    j++;
                                    System.out.println(" j "+j);
                                }
                                System.out.println("carYear..."+carYear);
                            }
                        }

                        int spouseAge;
                        final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                                .findOne("{user_id:#,profile_name:#}", userId, finPlanProfileName)
                                .as(IncomeProfile.class);
                        final JSONObject incomeProfileJson = new JSONObject(
                                jsonMapper.writeValueAsString(incomeProfileDetails));
                        final JSONArray userIncome = incomeProfileJson.getJSONArray("user_income");
                        JSONArray spouseIncomeArray = new JSONArray();
                        JSONArray combinedIncome = new JSONArray();
                        if (maritalStatus.equals("Yes")) {
                            spouseIncomeArray = incomeProfileJson.getJSONArray("spouse_income");
                            final IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
                            combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, spouseIncomeArray);
                            spouseAge = finDetails.getSpouseAge();
                        } else {
                            combinedIncome = userIncome;
                            spouseAge = 0;
                        }
                        final int emergencyFundAmt = finDetails.getEmergencyFundAmt();
                        final boolean emergencyFundFlag = finDetails.isEmergencyFundFlag();

                        final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                                .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement")
                                .as(RetirementGoal.class);
                        int spouseStartYear = 0;
                        int userStartYear = 0;

                        if (retirementObj != null) {
                            userStartYear = details.getBirthYear() + retirementObj.getRetirementAge();
                            if (finDetails.getMarital_status().equals("Yes")
                                    && details.getMarital_status().equals("No")) {
                                spouseStartYear = finDetails.getSpouseBirthYear()
                                        + retirementObj.getSpouseRetirementAge();
                            } else if (details.getMarital_status().equals("Yes")) {
                                spouseStartYear = details.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                            }
                        } else {
                            userStartYear = details.getBirthYear() + RETAIRMENTAGE;
                            if (finDetails.getMarital_status().equals("Yes")
                                    && details.getMarital_status().equals("No")) {
                                spouseStartYear = finDetails.getSpouseBirthYear() + RETAIRMENTAGE;
                            } else if (details.getMarital_status().equals("Yes")) {
                                spouseStartYear = details.getSpouseBirthYear() + RETAIRMENTAGE;
                            }
                        }
                        final JSONObject retirementData = new JSONObject();
                        retirementData.put("spouseStartYear", spouseStartYear);
                        retirementData.put("userStartYear", userStartYear);
                        String emergencyType = null;
                        String monthsOfIncome = null;
                        String monthsOfExpense = null;
                        if (emergencyFundFlag) {
                            final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                                    .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Emergency Fund")
                                    .as(Emergencyfundmodel.class);
                            emergencyType = emergencyObj.getTimePeriod();
                            monthsOfIncome = emergencyObj.getMonthI();
                            monthsOfExpense = emergencyObj.getMonthE();
                        }

                        final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                                spouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, userId,
                                fillingExemtion, userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions,
                                kidBirthYear, retirementData, retirementObj, housingExpense, emergencyType,
                                monthsOfIncome, monthsOfExpense, finDetails.isRetirementFlag());
                        final String status = result.getString("status");
                        if (status.equals("success")) {
                            carExpenseDb=null;
                            String goalName;
                            final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                            MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                            .with("{$inc: {goal_id: 1}}");
                            goalId = "goal" + count.getGoal_id();
                            goalName=cargoalmodel.getGoalType()+ " - " + goalId;
                            final BasicDBObject doc = new BasicDBObject("_id", goalId)
                                    .append("user_id", cargoalmodel.getUser_id()).append("fin_id", finId)
                                    .append("plan_name", cargoalmodel.getPlan_name())
                                    .append("goalType", cargoalmodel.getGoalType())
                                    .append("carYear", cargoalmodel.getCarYear())
                                    .append("carPrice", cargoalmodel.getCarPrice())
                                    .append("downPayment", cargoalmodel.getDownPayment())
                                    .append("timePeriod", cargoalmodel.getTimePeriod())
                                    .append("intrestRate", intrestRate).append("rentLease", cargoalmodel.getRentLease())
                                    .append("exactAnual_morgage", exactAnualMorgage)
                                    .append("goalName", goalName)
                                    .append("created_ts", dateFormat.format(date))
                                    .append("creditScore", cargoalmodel.getCreditScore()).append("completedStatus", 1)
                                    .append("modified_ts", dateFormat.format(date))
                                    .append("calculatedCarPrice", carPrice)
                                    .append("carReccursive", carBuyingReccursive)
                                    .append("reccursivePeriod", reccursiveBuyingPeriod)
                                    .append("tradeIn", cargoalmodel.getTradeIn());
                            doc.append("goalFeasibility", true);
                            /*                            updateFinplanForCarGoal(finId, userId, goalId, startYear, endYear, endYear1, monthlyPayment,
                                    carPrice, annualCost, exactAnualMorgage, rentLease, "insert", amount,goalName);*/
                            MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                            .with("{$addToSet: {'goals':'" + goalId + "'}}");
                            final JSONObject carExpenseData = new JSONObject();
                            final JSONArray carExpense = new JSONArray();
                            carExpenseData.put("startYear", startYear);
                            carExpenseData.put("endYear", endYear1);
                            carExpenseData.put("annualCost", exactAnualMorgage);
                            carExpenseData.put("downPayment", amount);
                            carExpenseData.put("goalName", goalName);
                            carExpenseData.put("carReccursive", carBuyingReccursive);
                            carExpenseData.put("reccursivePeriod", reccursiveBuyingPeriod);
                            carExpense.put(carExpenseData);
                            System.out.println("carExpense "+ carExpense);
                            if (!finPlanJson.isNull("expense")) {
                                final JSONObject expenseInFinPlanCol = finPlanJson.getJSONObject("expense");
                                if (!expenseInFinPlanCol.isNull("carGoalExpense")) {
                                    carExpenseDb = expenseInFinPlanCol.getJSONArray("carGoalExpense");
                                    //// System.out.println("kidExpense --> inside kid
                                    //// not eqal null "+kidExpense);
                                }
                            }
                            System.out.println("before carExpenseDb"+carExpenseDb);
                            if (carExpenseDb == null) {
                                System.out.println("kid expance null in kidgoal");
                                MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                                .with("{$set: {'expense.carGoalExpense':" + carExpense + "}}");
                            } else {
                                System.out.println("kid expance not null eqal nullkid goal");
                                carExpenseDb.put(carExpenseData);
                                MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                                .with("{$set: {'expense.carGoalExpense':" + carExpenseDb + "}}");
                            }
                            System.out.println("after carExpenseDb"+carExpenseDb);
                            MongoDBConnection.goalcoll.insert(doc);
                            MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                            .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits
                                    + ",'deductions':" + deductions + "}}");
                            responseToRestController.put("status", "success");
                            responseToRestController.put("goal_id", goalId);
                            responseToRestController.put("exactAnual_morgage", exactAnualMorgage);
                            responseToRestController.put("carYear", cargoalmodel.getCarYear());
                            responseToRestController.put("carPrice", cargoalmodel.getCarPrice());
                            responseToRestController.put("timePeriod", cargoalmodel.getTimePeriod());
                            responseToRestController.put("downPayment", cargoalmodel.getDownPayment());
                            final JSONObject systemLog = new JSONObject();
                            systemLog.put("type", "success");
                            systemLog.put("message", "Goal Created Successfully!!");
                            systemLog.put("userName", details.getName());
                            systemLog.put("user_id", userId);
                            systemLog.put("createdTs", dateFormat.format(date));
                        }
                    } catch (final Exception e) {
                        final JSONObject systemLog = new JSONObject();
                        systemLog.put("type", "error");
                        systemLog.put("message", "Goal Creation Failed!!");
                        systemLog.put("userName", details.getName());
                        systemLog.put("user_id", userId);
                        systemLog.put("createdTs", dateFormat.format(date));
                        e.printStackTrace();
                    } finally {
                        final BasicDBObject systemLogBasicObject = new BasicDBObject("user_id", userId)
                                .append("userName", details.getName())

                                .append("message", "Gola inserted successfully !!")
                                .append("createdTs", dateFormat.format(date));
                        MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);
                    }
                    return responseToRestController;
                }
            } else if (actionHomeType.equals("deleteGoal")) {
                goalId = cargoalmodel.getGoal_id();
                final Cargoalmodel cargoalfinID = MongoDBConnection.goalcoll.findOne("{_id:#}", goalId)
                        .as(Cargoalmodel.class);
                final String finId = cargoalfinID.getFin_id();
                if (cargoalfinID.getRentLease().equals("Leasing")) {
                    int k=0;
                    final double oldAnnualExpense = cargoalfinID.getAnnualCost();
                    final int oldTimePeriod = cargoalfinID.getLeaseYear();
                    int oldCarYear = cargoalfinID.getCarYear();
                    final String oldGoalName=cargoalfinID.getGoalName();
                    final String oldCarReccursive=cargoalfinID.getCarReccursive();
                    final FinPlan finDetails = MongoDBConnection.finplancol.findOne("{_id:#}", finId).as(FinPlan.class);
                    final User details = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
                    final int userAge = details.getAge();
                    final String maritalStatus = finDetails.getMarital_status();
                    final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
                    JSONArray assets = finPlanJson.getJSONArray("assests");
                    JSONArray tax = finPlanJson.getJSONArray("tax");
                    final JSONArray limits = finPlanJson.getJSONArray("limits");
                    final JSONArray deductions = finPlanJson.getJSONArray("deductions");
                    final JSONArray fillingExemtion = finPlanJson.getJSONArray("fillingExemtion");
                    final JSONArray userExpense = finPlanJson.getJSONArray("userExpense");
                    final JSONArray kidBirthYear = finPlanJson.getJSONArray("kidBirthYear");
                    final int oldReccurssivePeriod=cargoalfinID.getReccursivePeriod();
                    JSONObject expense = new JSONObject();
                    JSONArray carDataExpense = new JSONArray();
                    JSONObject newCarExpense = new JSONObject();
                    final JSONArray newExpenseArray=new JSONArray();
                    expense = finPlanJson.getJSONObject("expense");
                    if (!expense.isNull("carGoalExpense")) {
                        carDataExpense = finPlanJson.getJSONObject("expense").getJSONArray("carGoalExpense");
                        System.out.println("carDataExpense...length "+carDataExpense+" "+carDataExpense.length());
                        for(int i=0;i<carDataExpense.length();i++)
                        {
                            System.out.println("1..."+carDataExpense.getJSONObject(i).getString("goalName")+" "+oldGoalName);
                            if(!carDataExpense.getJSONObject(i).getString("goalName").equals(oldGoalName))
                            {
                                System.out.println("1...");
                                newCarExpense=carDataExpense.getJSONObject(i);
                                newExpenseArray.put(newCarExpense);
                            }
                        }
                    }
                    JSONArray housingExpense = new JSONArray();
                    if (!expense.isNull("housing_expense")) {
                        housingExpense = finPlanJson.getJSONObject("expense").getJSONArray("housingExpense");
                    }
                    final String finPlanProfileName = finDetails.getProfile_name();
                    if(oldCarReccursive.equals("LeasingNo")){
                        for (int i = 0; i < limits.length(); i++) {

                            if (limits.getJSONObject(i).getInt("year") < oldCarYear) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - oldAnnualExpense * oldTimePeriod);
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            } else if (limits.getJSONObject(i).getInt("year") >= oldCarYear
                                    && limits.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod - 1) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - oldAnnualExpense * (oldCarYear
                                                + oldTimePeriod - limits.getJSONObject(i).getInt("year") - 1));
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            }

                            if (deductions.getJSONObject(i).getInt("year") >= oldCarYear
                                    && deductions.getJSONObject(i).getInt("year") <= oldCarYear + oldTimePeriod) {

                                deductions.getJSONObject(i).put("saving",
                                        deductions.getJSONObject(i).getDouble("saving") - oldAnnualExpense);
                                if (deductions.getJSONObject(i).getDouble("saving") < 0) {
                                    deductions.getJSONObject(i).put("saving", 0);
                                }
                            }

                        }
                    }
                    else{
                        for (int i = 0; i < limits.length(); i++) {

                            if (limits.getJSONObject(i).getInt("year") < oldCarYear) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - oldAnnualExpense * oldTimePeriod);
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            } else if (limits.getJSONObject(i).getInt("year") >= oldCarYear
                                    && limits.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod - 1) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - oldAnnualExpense * (oldCarYear
                                                + oldTimePeriod - limits.getJSONObject(i).getInt("year") - 1));
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            }

                            if (deductions.getJSONObject(i).getInt("year") >= oldCarYear
                                    && deductions.getJSONObject(i).getInt("year") <= oldCarYear + oldTimePeriod) {

                                deductions.getJSONObject(i).put("saving",
                                        deductions.getJSONObject(i).getDouble("saving") - oldAnnualExpense);
                                if (deductions.getJSONObject(i).getDouble("saving") < 0) {
                                    deductions.getJSONObject(i).put("saving", 0);
                                }
                            }
                            if(limits.getJSONObject(i).getInt("year")==oldCarYear && k<oldReccurssivePeriod)
                            {
                                oldCarYear=oldCarYear+oldTimePeriod;
                                k++;
                                System.out.println(" k "+k);
                            }
                        }

                    }

                    final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                            .findOne("{user_id:#,profile_name:#}", userId, finPlanProfileName).as(IncomeProfile.class);
                    final JSONObject incomeProfileJson = new JSONObject(
                            jsonMapper.writeValueAsString(incomeProfileDetails));
                    final JSONArray userIncome = incomeProfileJson.getJSONArray("user_income");
                    JSONArray spouseIncomeArray = new JSONArray();
                    JSONArray combinedIncome = new JSONArray();
                    int spouseAge;
                    if (maritalStatus.equals("Yes")) {
                        spouseIncomeArray = incomeProfileJson.getJSONArray("spouse_income");
                        final IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
                        combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, spouseIncomeArray);
                        spouseAge = finDetails.getSpouseAge();
                    } else {
                        combinedIncome = userIncome;
                        spouseAge = 0;
                    }

                    final int emergencyFundAmt = finDetails.getEmergencyFundAmt();
                    final boolean emergencyFundFlag = finDetails.isEmergencyFundFlag();

                    final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                            .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement")
                            .as(RetirementGoal.class);

                    int spouseStartYear = 0;
                    int userStartYear = 0;

                    if (retirementObj != null) {
                        userStartYear = details.getBirthYear() + retirementObj.getRetirementAge();
                        if (finDetails.getMarital_status().equals("Yes") && details.getMarital_status().equals("No")) {
                            spouseStartYear = finDetails.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                        } else if (details.getMarital_status().equals("Yes")) {
                            spouseStartYear = details.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                        }

                    } else {
                        userStartYear = details.getBirthYear() + RETAIRMENTAGE;
                        if (finDetails.getMarital_status().equals("Yes") && details.getMarital_status().equals("No")) {
                            spouseStartYear = finDetails.getSpouseBirthYear() + RETAIRMENTAGE;
                        } else if (details.getMarital_status().equals("Yes")) {
                            spouseStartYear = details.getSpouseBirthYear() + RETAIRMENTAGE;
                        }
                    }
                    final JSONObject retirementData = new JSONObject();
                    retirementData.put("spouseStartYear", spouseStartYear);
                    retirementData.put("userStartYear", userStartYear);

                    String emergencyType = null;
                    String monthsOfIncome = null;
                    String monthsOfExpense = null;
                    if (emergencyFundFlag) {
                        final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                                .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Emergency Fund")
                                .as(Emergencyfundmodel.class);
                        emergencyType = emergencyObj.getTimePeriod();
                        monthsOfIncome = emergencyObj.getMonthI();
                        monthsOfExpense = emergencyObj.getMonthE();
                    }
                    final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                            spouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, userId, fillingExemtion,
                            userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions, kidBirthYear,
                            retirementData, retirementObj, housingExpense, emergencyType, monthsOfIncome,
                            monthsOfExpense, finDetails.isRetirementFlag());

                    final String status = result.getString("status");
                    if (status.equals("success")) {
                        assets = result.getJSONArray("assets");
                        tax = result.getJSONArray("tax");
                        MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits
                                + ",'deductions':" + deductions + "}}");
                        MongoDBConnection.goalcoll.remove("{_id:#}", goalId);
                        final JSONArray goalJsonArray = finPlanJson.getJSONArray("goals");
                        final JSONArray goalsArray = new JSONArray();
                        for (int j = 0; j < goalJsonArray.length(); j++) {
                            final String goalIds = (String) goalJsonArray.get(j);
                            if (!goalIds.equals(goalId)) {
                                goalsArray.put(goalIds);
                            } else {
                                continue;
                            }
                        }
                        MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'goals':" + goalsArray + "}}");
                        /*             MongoDBConnection.finplancol.update("{usr_id:#,plan_name:#}", userId, planName)
                        .with("{$set: {'expense.carGoalExpense':{'annualCost':" + 0 + "}}}");*/
                        MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'expense.carGoalExpense':" + newExpenseArray + "}}");
                        responseToRestController.put("status", "success");
                    } else {
                        responseToRestController.put("status", "fail");
                    }

                } else {
                    final double oldExactAnualMortgage = cargoalfinID.getExactAnual_morgage();
                    final int oldTimePeriod = cargoalfinID.getTimePeriod();
                    int oldCarYear = cargoalfinID.getCarYear();
                    final int oldDownpayment = cargoalfinID.getDownPayment();
                    final int oldcarPrice = (int) cargoalfinID.getCarPrice();
                    final int oldamount = oldcarPrice * oldDownpayment / 100;
                    final String oldGoalName=cargoalfinID.getGoalName();
                    final FinPlan finDetails = MongoDBConnection.finplancol.findOne("{_id:#}", finId).as(FinPlan.class);
                    final User details = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
                    final int userAge = details.getAge();
                    final String maritalStatus = finDetails.getMarital_status();
                    final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
                    JSONArray assets = finPlanJson.getJSONArray("assests");
                    JSONArray tax = finPlanJson.getJSONArray("tax");
                    final JSONArray limits = finPlanJson.getJSONArray("limits");
                    final JSONArray deductions = finPlanJson.getJSONArray("deductions");
                    final JSONArray fillingExemtion = finPlanJson.getJSONArray("fillingExemtion");
                    final JSONArray userExpense = finPlanJson.getJSONArray("userExpense");
                    final JSONArray kidBirthYear = finPlanJson.getJSONArray("kidBirthYear");
                    JSONObject expense = new JSONObject();
                    expense = finPlanJson.getJSONObject("expense");
                    JSONArray carDataExpense = new JSONArray();
                    JSONObject newCarExpense = new JSONObject();
                    final JSONArray newExpenseArray = new JSONArray();
                    expense = finPlanJson.getJSONObject("expense");
                    if (!expense.isNull("carGoalExpense")) {
                        carDataExpense = finPlanJson.getJSONObject("expense").getJSONArray("carGoalExpense");
                        for(int i=0;i<carDataExpense.length();i++)
                        {
                            if(!carDataExpense.getJSONObject(i).getString("goalName").equals(oldGoalName))
                            {
                                newCarExpense=carDataExpense.getJSONObject(i);
                                newExpenseArray.put(newCarExpense);
                            }
                        }
                    }
                    JSONArray housingExpense = new JSONArray();
                    if (!expense.isNull("housing_expense")) {
                        housingExpense = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
                    }
                    final String finPlanProfileName = finDetails.getProfile_name();
                    final String carBuyingReccursive = cargoalfinID.getCarReccursive();
                    if(carBuyingReccursive.equals("BuyingNo")) {
                        for (int i = 0; i < limits.length(); i++) {
                            if (oldCarYear > currentYear + GOALWITHINFIVEYEARS) {
                                if (limits.getJSONObject(i).getInt("year") < oldCarYear) {
                                    limits.getJSONObject(i).put("taxable", limits.getJSONObject(i).getInt("taxable")
                                            - oldExactAnualMortgage * oldTimePeriod - oldamount);
                                    if (limits.getJSONObject(i).getInt("taxable") < 0) {
                                        limits.getJSONObject(i).put("taxable", 0);
                                    }
                                } else if (limits.getJSONObject(i).getInt("year") >= oldCarYear
                                        && limits.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod - 1) {
                                    limits.getJSONObject(i).put("taxable",
                                            limits.getJSONObject(i).getInt("taxable") - oldExactAnualMortgage * (oldCarYear
                                                    + oldTimePeriod - limits.getJSONObject(i).getInt("year") - 1));
                                    if (limits.getJSONObject(i).getInt("taxable") < 0) {
                                        limits.getJSONObject(i).put("taxable", 0);
                                    }
                                }
                                if (deductions.getJSONObject(i).getInt("year") == oldCarYear) {
                                    deductions.getJSONObject(i).put("taxable",
                                            deductions.getJSONObject(i).getDouble("taxable") - oldExactAnualMortgage
                                            - oldamount);
                                    if (deductions.getJSONObject(i).getDouble("taxable") < 0) {
                                        deductions.getJSONObject(i).put("taxable", 0);
                                    }

                                } else if (deductions.getJSONObject(i).getInt("year") > oldCarYear
                                        && deductions.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod) {

                                    deductions.getJSONObject(i).put("taxable",
                                            deductions.getJSONObject(i).getDouble("taxable") - oldExactAnualMortgage);
                                    if (deductions.getJSONObject(i).getDouble("taxable") < 0) {
                                        deductions.getJSONObject(i).put("taxable", 0);
                                    }
                                }
                            } else {
                                if (limits.getJSONObject(i).getInt("year") < oldCarYear) {
                                    limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving")
                                            - oldExactAnualMortgage * oldTimePeriod - oldamount);
                                    if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                        limits.getJSONObject(i).put("saving", 0);
                                    }
                                } else if (limits.getJSONObject(i).getInt("year") >= oldCarYear
                                        && limits.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod - 1) {
                                    limits.getJSONObject(i).put("saving",
                                            limits.getJSONObject(i).getInt("saving") - oldExactAnualMortgage * (oldCarYear
                                                    + oldTimePeriod - limits.getJSONObject(i).getInt("year") - 1));
                                    if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                        limits.getJSONObject(i).put("saving", 0);
                                    }
                                }
                                if (deductions.getJSONObject(i).getInt("year") == oldCarYear) {
                                    deductions.getJSONObject(i).put("saving",
                                            deductions.getJSONObject(i).getDouble("saving") - oldExactAnualMortgage
                                            - oldamount);
                                    if (deductions.getJSONObject(i).getDouble("saving") < 0) {
                                        deductions.getJSONObject(i).put("saving", 0);
                                    }

                                } else if (deductions.getJSONObject(i).getInt("year") > oldCarYear
                                        && deductions.getJSONObject(i).getInt("year") <= oldCarYear + oldTimePeriod) {

                                    deductions.getJSONObject(i).put("saving",
                                            deductions.getJSONObject(i).getDouble("saving") - oldExactAnualMortgage);
                                    if (deductions.getJSONObject(i).getDouble("saving") < 0) {
                                        deductions.getJSONObject(i).put("saving", 0);
                                    }
                                }

                            }

                        }
                    } else {
                        int j = 0;
                        for (int i = 0; i < limits.length(); i++) {

                            if (limits.getJSONObject(i).getInt("year") < oldCarYear) {
                                limits.getJSONObject(i).put("saving", oldExactAnualMortgage * oldTimePeriod
                                        + limits.getJSONObject(i).getInt("saving") + oldTimePeriod);
                            } else if (limits.getJSONObject(i).getInt("year") >= oldCarYear
                                    && limits.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod - 1) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") + oldExactAnualMortgage * (oldCarYear
                                                + oldTimePeriod - limits.getJSONObject(i).getInt("year") - 1));
                            }
                            if (deductions.getJSONObject(i).getInt("year") == oldCarYear) {
                                deductions.getJSONObject(i).put("saving", oldExactAnualMortgage + oldTimePeriod
                                        + deductions.getJSONObject(i).getDouble("saving"));
                            }
                            if(limits.getJSONObject(i).getInt("year")==oldCarYear && j<reccursivePeriod)
                            {
                                oldCarYear=oldCarYear+oldTimePeriod;
                                j++;
                                System.out.println(" j "+j);
                            }
                        }

                    }


                    final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                            .findOne("{user_id:#,profile_name:#}", userId, finPlanProfileName).as(IncomeProfile.class);
                    final JSONObject incomeProfileJson = new JSONObject(
                            jsonMapper.writeValueAsString(incomeProfileDetails));
                    final JSONArray userIncome = incomeProfileJson.getJSONArray("user_income");
                    JSONArray spouseIncomeArray = new JSONArray();
                    JSONArray combinedIncome = new JSONArray();
                    int spouseAge;
                    if (maritalStatus.equals("Yes")) {
                        spouseIncomeArray = incomeProfileJson.getJSONArray("spouse_income");
                        final IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
                        combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, spouseIncomeArray);
                        spouseAge = finDetails.getSpouseAge();
                    } else {
                        combinedIncome = userIncome;
                        spouseAge = 0;
                    }

                    final int emergencyFundAmt = finDetails.getEmergencyFundAmt();
                    final boolean emergencyFundFlag = finDetails.isEmergencyFundFlag();

                    final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                            .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement")
                            .as(RetirementGoal.class);

                    int spouseStartYear = 0;
                    int userStartYear = 0;

                    if (retirementObj != null) {
                        userStartYear = details.getBirthYear() + retirementObj.getRetirementAge();
                        if (finDetails.getMarital_status().equals("Yes") && details.getMarital_status().equals("No")) {
                            spouseStartYear = finDetails.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                        } else if (details.getMarital_status().equals("Yes")) {
                            spouseStartYear = details.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                        }

                    } else {
                        userStartYear = details.getBirthYear() + RETAIRMENTAGE;
                        if (finDetails.getMarital_status().equals("Yes") && details.getMarital_status().equals("No")) {
                            spouseStartYear = finDetails.getSpouseBirthYear() + RETAIRMENTAGE;
                        } else if (details.getMarital_status().equals("Yes")) {
                            spouseStartYear = details.getSpouseBirthYear() + RETAIRMENTAGE;
                        }
                    }
                    final JSONObject retirementData = new JSONObject();
                    retirementData.put("spouseStartYear", spouseStartYear);
                    retirementData.put("userStartYear", userStartYear);

                    String emergencyType = null;
                    String monthsOfIncome = null;
                    String monthsOfExpense = null;
                    if (emergencyFundFlag) {
                        final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                                .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Emergency Fund")
                                .as(Emergencyfundmodel.class);
                        emergencyType = emergencyObj.getTimePeriod();
                        monthsOfIncome = emergencyObj.getMonthI();
                        monthsOfExpense = emergencyObj.getMonthE();
                    }
                    final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                            spouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, userId, fillingExemtion,
                            userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions, kidBirthYear,
                            retirementData, retirementObj, housingExpense, emergencyType, monthsOfIncome,
                            monthsOfExpense, finDetails.isRetirementFlag());

                    final String status = result.getString("status");
                    if (status.equals("success")) {
                        assets = result.getJSONArray("assets");
                        tax = result.getJSONArray("tax");
                        MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits
                                + ",'deductions':" + deductions + "}}");
                        MongoDBConnection.goalcoll.remove("{_id:#}", goalId);
                        final JSONArray goalJsonArray = finPlanJson.getJSONArray("goals");
                        final JSONArray goalsArray = new JSONArray();
                        for (int j = 0; j < goalJsonArray.length(); j++) {
                            final String goalIds = (String) goalJsonArray.get(j);
                            if (!goalIds.equals(goalId)) {
                                goalsArray.put(goalIds);
                            } else {
                                continue;
                            }
                        }
                        // System.out.println("before fin plan update");
                        MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'goals':" + goalsArray + "}}");
                        /*                        MongoDBConnection.finplancol.update("{usr_id:#,plan_name:#}", userId, planName)
                        .with("{$set: {'expense.carGoalExpense':{'annualCost':" + 0 + "}}}");*/
                        MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'expense.carGoalExpense':" + newExpenseArray + "}}");
                        responseToRestController.put("status", "success");
                    } else {
                        responseToRestController.put("status", "fail");
                    }
                }
            } else { //update
                final Cargoalmodel cargoalfinID = MongoDBConnection.goalcoll.findOne("{_id:#}", goalId)
                        .as(Cargoalmodel.class);
                final String finId = cargoalfinID.getFin_id();
                carReccursive=cargoalfinID.getCarReccursive();
                final FinPlan finDetails = MongoDBConnection.finplancol.findOne("{_id:#}", finId).as(FinPlan.class);
                final User details = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
                final int userAge = details.getAge();
                final String maritalStatus = finDetails.getMarital_status();
                final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
                final JSONArray assets = finPlanJson.getJSONArray("assests");
                final JSONArray tax = finPlanJson.getJSONArray("tax");
                final JSONArray limits = finPlanJson.getJSONArray("limits");
                final JSONArray deductions = finPlanJson.getJSONArray("deductions");
                if (cargoalfinID.getRentLease().equals("Leasing")) {
                    final double oldAnnualExpense = cargoalfinID.getAnnualCost();
                    final int oldTimePeriod = cargoalfinID.getLeaseYear();
                    int oldCarYear = cargoalfinID.getCarYear();
                    final int oldReccurssivePeriod=cargoalfinID.getReccursivePeriod();
                    int k=0;
                    if(carReccursive.equals("LeasingNo")){
                        for (int i = 0; i < limits.length(); i++) {

                            if (limits.getJSONObject(i).getInt("year") < oldCarYear) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - oldAnnualExpense * oldTimePeriod);
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            } else if (limits.getJSONObject(i).getInt("year") >= oldCarYear
                                    && limits.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod - 1) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - oldAnnualExpense * (oldCarYear
                                                + oldTimePeriod - limits.getJSONObject(i).getInt("year") - 1));
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            }

                            if (deductions.getJSONObject(i).getInt("year") >= oldCarYear
                                    && deductions.getJSONObject(i).getInt("year") <= oldCarYear + oldTimePeriod) {

                                deductions.getJSONObject(i).put("saving",
                                        deductions.getJSONObject(i).getDouble("saving") - oldAnnualExpense);
                                if (deductions.getJSONObject(i).getDouble("saving") < 0) {
                                    deductions.getJSONObject(i).put("saving", 0);
                                }
                            }

                        }
                    }
                    else{
                        for (int i = 0; i < limits.length(); i++) {

                            if (limits.getJSONObject(i).getInt("year") < oldCarYear) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - oldAnnualExpense * oldTimePeriod);
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            } else if (limits.getJSONObject(i).getInt("year") >= oldCarYear
                                    && limits.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod - 1) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - oldAnnualExpense * (oldCarYear
                                                + oldTimePeriod - limits.getJSONObject(i).getInt("year") - 1));
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            }

                            if (deductions.getJSONObject(i).getInt("year") >= oldCarYear
                                    && deductions.getJSONObject(i).getInt("year") <= oldCarYear + oldTimePeriod) {

                                deductions.getJSONObject(i).put("saving",
                                        deductions.getJSONObject(i).getDouble("saving") - oldAnnualExpense);
                                if (deductions.getJSONObject(i).getDouble("saving") < 0) {
                                    deductions.getJSONObject(i).put("saving", 0);
                                }
                            }
                            if(limits.getJSONObject(i).getInt("year")==oldCarYear && k<oldReccurssivePeriod)
                            {
                                oldCarYear=oldCarYear+oldTimePeriod;
                                k++;
                                System.out.println(" k "+k);
                            }
                        }
                    }
                } else {
                    final double oldExactAnualMortgage = cargoalfinID.getExactAnual_morgage();
                    final int oldTimePeriod = cargoalfinID.getTimePeriod();
                    int oldCarYear = cargoalfinID.getCarYear();
                    final int oldDownpayment = cargoalfinID.getDownPayment();
                    final int oldCarPrice = (int) cargoalfinID.getCarPrice();
                    final double oldAmount = oldCarPrice * oldDownpayment / 100;
                    final String carBuyingReccursive = cargoalfinID.getCarReccursive();
                    final int reccursiveBuyingPeriod = cargoalfinID.getReccursivePeriod();
                    if(carBuyingReccursive.equals("BuyingNo")) {
                        for (int i = 0; i < limits.length(); i++) {
                            if (oldCarYear > currentYear + GOALWITHINFIVEYEARS) {
                                if (limits.getJSONObject(i).getInt("year") < oldCarYear) {
                                    limits.getJSONObject(i).put("taxable", limits.getJSONObject(i).getInt("taxable")
                                            - oldExactAnualMortgage * oldTimePeriod - oldAmount);
                                    if (limits.getJSONObject(i).getInt("taxable") < 0) {
                                        limits.getJSONObject(i).put("taxable", 0);
                                    }
                                } else if (limits.getJSONObject(i).getInt("year") >= oldCarYear
                                        && limits.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod - 1) {
                                    limits.getJSONObject(i).put("taxable",
                                            limits.getJSONObject(i).getInt("taxable") - oldExactAnualMortgage * (oldCarYear
                                                    + oldTimePeriod - limits.getJSONObject(i).getInt("year") - 1));
                                    if (limits.getJSONObject(i).getInt("taxable") < 0) {
                                        limits.getJSONObject(i).put("taxable", 0);
                                    }
                                }
                                if (deductions.getJSONObject(i).getInt("year") == oldCarYear) {
                                    deductions.getJSONObject(i).put("taxable",
                                            deductions.getJSONObject(i).getDouble("taxable") - oldExactAnualMortgage
                                            - oldAmount);
                                    if (deductions.getJSONObject(i).getDouble("taxable") < 0) {
                                        deductions.getJSONObject(i).put("taxable", 0);
                                    }

                                } else if (deductions.getJSONObject(i).getInt("year") > oldCarYear
                                        && deductions.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod) {

                                    deductions.getJSONObject(i).put("taxable",
                                            deductions.getJSONObject(i).getDouble("taxable") - oldExactAnualMortgage);
                                    if (deductions.getJSONObject(i).getDouble("taxable") < 0) {
                                        deductions.getJSONObject(i).put("taxable", 0);
                                    }
                                }
                            } else {
                                if (limits.getJSONObject(i).getInt("year") < oldCarYear) {
                                    limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving")
                                            - oldExactAnualMortgage * oldTimePeriod - oldAmount);
                                    if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                        limits.getJSONObject(i).put("saving", 0);
                                    }
                                } else if (limits.getJSONObject(i).getInt("year") >= oldCarYear
                                        && limits.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod - 1) {

                                    limits.getJSONObject(i).put("saving",
                                            limits.getJSONObject(i).getInt("saving") - oldExactAnualMortgage * (oldCarYear
                                                    + oldTimePeriod - limits.getJSONObject(i).getInt("year") - 1));
                                    if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                        limits.getJSONObject(i).put("saving", 0);
                                    }
                                }
                                if (deductions.getJSONObject(i).getInt("year") == oldCarYear) {
                                    deductions.getJSONObject(i).put("saving",
                                            deductions.getJSONObject(i).getDouble("saving") - oldExactAnualMortgage
                                            - oldAmount);
                                    if (deductions.getJSONObject(i).getDouble("saving") < 0) {
                                        deductions.getJSONObject(i).put("saving", 0);
                                    }

                                } else if (deductions.getJSONObject(i).getInt("year") > oldCarYear
                                        && deductions.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod) {
                                    deductions.getJSONObject(i).put("saving",
                                            deductions.getJSONObject(i).getDouble("saving") - oldExactAnualMortgage);
                                    if (deductions.getJSONObject(i).getDouble("saving") < 0) {
                                        deductions.getJSONObject(i).put("saving", 0);
                                    }
                                }

                            }

                        }
                    } else {
                        int j =0;
                        for (int i = 0; i < limits.length(); i++) {

                            if (limits.getJSONObject(i).getInt("year") < oldCarYear) {
                                limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving") -
                                        oldExactAnualMortgage * oldTimePeriod  - oldAmount);
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            } else if (limits.getJSONObject(i).getInt("year") >= oldCarYear
                                    && limits.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod - 1) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - oldExactAnualMortgage
                                        * (oldCarYear + oldTimePeriod - limits.getJSONObject(i).getInt("year") - 1));
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            }

                            if (deductions.getJSONObject(i).getInt("year") >= oldCarYear
                                    && deductions.getJSONObject(i).getInt("year") < oldCarYear + oldTimePeriod) {

                                deductions.getJSONObject(i).put("saving",deductions.getJSONObject(i).getDouble("saving")
                                        - oldExactAnualMortgage - oldAmount );
                                if (deductions.getJSONObject(i).getDouble("saving") < 0) {
                                    deductions.getJSONObject(i).put("saving", 0);
                                }
                            }
                            if(limits.getJSONObject(i).getInt("year")==oldCarYear && j<reccursiveBuyingPeriod)
                            {
                                oldCarYear=oldCarYear+timePeriod;
                                j++;
                                System.out.println(" j "+j);
                            }
                            System.out.println("carYear..."+oldCarYear);
                        }
                    }

                }

                final JSONArray fillingExemtion = finPlanJson.getJSONArray("fillingExemtion");
                final JSONArray userExpense = finPlanJson.getJSONArray("userExpense");
                final JSONArray kidBirthYear = finPlanJson.getJSONArray("kidBirthYear");
                JSONObject expense = new JSONObject();
                JSONObject newCarExpense = new JSONObject();
                JSONArray carDataExpense=new JSONArray();
                final JSONArray newExpenseArray=new JSONArray();
                String goalName=cargoalmodel.getGoalType()+ " - " + goalId;
                expense = finPlanJson.getJSONObject("expense");
                if (!expense.isNull("carGoalExpense")) {
                    carDataExpense = finPlanJson.getJSONObject("expense").getJSONArray("carGoalExpense");
                    System.out.println("carDataExpense...length "+carDataExpense+" "+carDataExpense.length());
                    for(int i=0;i<carDataExpense.length();i++)
                    {
                        System.out.println("1..."+carDataExpense.getJSONObject(i).getString("goalName")+" "+goalName);
                        if(!carDataExpense.getJSONObject(i).getString("goalName").equals(goalName))
                        {
                            newCarExpense=carDataExpense.getJSONObject(i);
                            newExpenseArray.put(newCarExpense);
                        }
                    }
                }
                JSONArray housingExpense = new JSONArray();
                if (!expense.isNull("housing_expense")) {
                    housingExpense = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
                }
                final String finPlanProfileName = finDetails.getProfile_name();
                final JSONObject systemLog = new JSONObject();
                User user = null;
                if (rentLease.equals("Leasing")) {
                    try {
                        int j=0;
                        user = MongoDBConnection.userColl.findOne("{'_id':#}", userId).as(User.class);
                        final double x = residualValue / 100.0 * carPrice;
                        final double r = moneyFactor * 2400 / 12 / 100;
                        final int n = leaseYear * 12;
                        final double monthlyPayment = calAnualMorgage(x, r, n, carPrice);
                        final double annualCost = monthlyPayment * 12;
                        final double exactAnualMorgage = cargoalmodel.getExactAnual_morgage();
                        carReccursive=cargoalmodel.getCarReccursive();
                        if(carReccursive.equals("LeasingNo")){
                            for (int i = 0; i < limits.length(); i++) {

                                if (limits.getJSONObject(i).getInt("year") < carYear) {
                                    limits.getJSONObject(i).put("saving",
                                            annualCost * leaseYear + limits.getJSONObject(i).getInt("saving"));
                                } else if (limits.getJSONObject(i).getInt("year") >= carYear
                                        && limits.getJSONObject(i).getInt("year") < carYear + leaseYear - 1) {
                                    limits.getJSONObject(i).put("saving",
                                            limits.getJSONObject(i).getInt("saving") + annualCost
                                            * (carYear + leaseYear - limits.getJSONObject(i).getInt("year") - 1));
                                }

                                if (deductions.getJSONObject(i).getInt("year") >= carYear
                                        && deductions.getJSONObject(i).getInt("year") < carYear + leaseYear) {

                                    deductions.getJSONObject(i).put("saving",
                                            annualCost + deductions.getJSONObject(i).getDouble("saving"));
                                }

                            }
                        }
                        else{
                            for (int i = 0; i < limits.length(); i++) {

                                if (limits.getJSONObject(i).getInt("year") < carYear) {
                                    limits.getJSONObject(i).put("saving",
                                            annualCost * leaseYear + limits.getJSONObject(i).getInt("saving"));
                                } else if (limits.getJSONObject(i).getInt("year") >= carYear
                                        && limits.getJSONObject(i).getInt("year") < carYear + leaseYear - 1) {
                                    limits.getJSONObject(i).put("saving",
                                            limits.getJSONObject(i).getInt("saving") + annualCost
                                            * (carYear + leaseYear - limits.getJSONObject(i).getInt("year") - 1));
                                }

                                if (deductions.getJSONObject(i).getInt("year") >= carYear
                                        && deductions.getJSONObject(i).getInt("year") < carYear + leaseYear) {

                                    deductions.getJSONObject(i).put("saving",
                                            annualCost + deductions.getJSONObject(i).getDouble("saving"));
                                }
                                if(limits.getJSONObject(i).getInt("year")==carYear && j<reccursivePeriod)
                                {
                                    carYear=carYear+leaseYear;
                                    j++;
                                    System.out.println(" j "+j);
                                }
                            }
                        }
                        int spouseAge;
                        final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                                .findOne("{user_id:#,profile_name:#}", userId, finPlanProfileName)
                                .as(IncomeProfile.class);
                        final JSONObject incomeProfileJson = new JSONObject(
                                jsonMapper.writeValueAsString(incomeProfileDetails));
                        final JSONArray userIncome = incomeProfileJson.getJSONArray("user_income");
                        JSONArray spouseIncomeArray = new JSONArray();
                        JSONArray combinedIncome = new JSONArray();
                        if (maritalStatus.equals("Yes")) {
                            spouseIncomeArray = incomeProfileJson.getJSONArray("spouse_income");
                            final IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
                            combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, spouseIncomeArray);
                            spouseAge = finDetails.getSpouseAge();
                        } else {
                            combinedIncome = userIncome;
                            spouseAge = 0;
                        }
                        final int emergencyFundAmt = finDetails.getEmergencyFundAmt();
                        final boolean emergencyFundFlag = finDetails.isEmergencyFundFlag();

                        final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                                .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement")
                                .as(RetirementGoal.class);
                        int spouseStartYear = 0;
                        int userStartYear = 0;

                        if (retirementObj != null) {
                            userStartYear = details.getBirthYear() + retirementObj.getRetirementAge();
                            if (finDetails.getMarital_status().equals("Yes")
                                    && details.getMarital_status().equals("No")) {
                                spouseStartYear = finDetails.getSpouseBirthYear()
                                        + retirementObj.getSpouseRetirementAge();
                            } else if (details.getMarital_status().equals("Yes")) {
                                spouseStartYear = details.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                            }
                        } else {
                            userStartYear = details.getBirthYear() + RETAIRMENTAGE;
                            if (finDetails.getMarital_status().equals("Yes")
                                    && details.getMarital_status().equals("No")) {
                                spouseStartYear = finDetails.getSpouseBirthYear() + RETAIRMENTAGE;
                            } else if (details.getMarital_status().equals("Yes")) {
                                spouseStartYear = details.getSpouseBirthYear() + RETAIRMENTAGE;
                            }
                        }
                        final JSONObject retirementData = new JSONObject();
                        retirementData.put("spouseStartYear", spouseStartYear);
                        retirementData.put("userStartYear", userStartYear);
                        String emergencyType = null;
                        String monthsOfIncome = null;
                        String monthsOfExpense = null;
                        if (emergencyFundFlag) {
                            final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                                    .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Emergency Fund")
                                    .as(Emergencyfundmodel.class);
                            emergencyType = emergencyObj.getTimePeriod();
                            monthsOfIncome = emergencyObj.getMonthI();
                            monthsOfExpense = emergencyObj.getMonthE();
                        }

                        final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                                spouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, userId,
                                fillingExemtion, userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions,
                                kidBirthYear, retirementData, retirementObj, housingExpense, emergencyType,
                                monthsOfIncome, monthsOfExpense, finDetails.isRetirementFlag());
                        final String status = result.getString("status");
                        if (status.equals("success")) {
                            goalName=cargoalmodel.getGoalType()+ " - " + goalId;
                            MongoDBConnection.goalcoll.update("{'_id': '" + goalId + "'}").upsert().multi()
                            .with("{$set: {'carYear':" + cargoalmodel.getCarYear() + ",'carPrice':"
                                    + cargoalmodel.getCarPrice() + ",'rentLease':'"
                                    + cargoalmodel.getRentLease() + "','leaseYear':"
                                    + cargoalmodel.getLeaseYear() + ",'residualValue':"
                                    + cargoalmodel.getResidualValue() + ",'monthlyPayment':" + monthlyPayment
                                    + ",'annualCost':" + annualCost
                                    + /*
                                     * ",'emergencyFundExpense':"+
                                     * emergencyFundExpense +
                                     */",'goalFeasibility':" + true + ",'modified_ts':'"
                                     + dateFormat.format(date) + "','goalName':'"+goalName+"','reccursivePeriod':"+cargoalmodel.getReccursivePeriod()+",'carReccursive':'"+carReccursive+"'}}");
                            responseToRestController.put("monthlyPayment", monthlyPayment);
                            responseToRestController.put("carYear", cargoalmodel.getCarYear());
                            responseToRestController.put("carPrice", cargoalmodel.getCarPrice());
                            responseToRestController.put("annualCost", annualCost);
                            responseToRestController.put("rentLease", cargoalmodel.getRentLease());
                            responseToRestController.put("leaseYear", cargoalmodel.getLeaseYear());
                            responseToRestController.put("residualValue", residualValue);
                            responseToRestController.put("carReccursive", carReccursive);
                            responseToRestController.put("reccursivePeriod", reccursivePeriod);
                            /*                            updateFinplanForCarGoal(finId, userId, goalId, startYear, endYear, endYear1, monthlyPayment,
                                    carPrice, annualCost, exactAnualMorgage, rentLease, "insert", 0,goalName);*/
                            final JSONObject carExpenseData=new JSONObject();
                            carExpenseData.put("endYear", endYear);
                            carExpenseData.put("startYear", startYear);
                            carExpenseData.put("monthlyPayment", monthlyPayment);
                            carExpenseData.put("annualCost", annualCost);
                            carExpenseData.put("goalName", goalName);
                            carExpenseData.put("reccursivePeriod", reccursivePeriod);
                            carExpenseData.put("carReccursive", carReccursive);
                            newExpenseArray.put(carExpenseData);
                            MongoDBConnection.finplancol.update("{'_id': '" + finId + "'}").upsert().multi()
                            .with("{$set: {'expense.carGoalExpense':" + newExpenseArray + "}}");

                            MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                            .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits
                                    + ",'deductions':" + deductions + "}}");
                            responseToRestController.put("status", "success");
                            responseToRestController.put("goal_id", goalId);
                            responseToRestController.put("monthlyPayment", decimalFloat.format(monthlyPayment));
                            responseToRestController.put("annualCost", decimalFloat.format(annualCost));
                            responseToRestController.put("carPrice", cargoalmodel.getCarPrice());
                            responseToRestController.put("moneyFactor", cargoalmodel.getMoneyFactor());
                            systemLog.put("type", "success");
                            systemLog.put("message", "Goal updated sucessfully");
                            systemLog.put("userName", user.getName());
                            systemLog.put("user_id", userId);
                            systemLog.put("createdTs", dateFormat.format(date));
                        }
                    } catch (final Exception e) {
                        systemLog.put("type", "fail");
                        systemLog.put("message", "Goal updating failed");
                        systemLog.put("userName", user.getName());
                        systemLog.put("user_id", user.get_id());
                        systemLog.put("createdTs", dateFormat.format(date));
                        e.printStackTrace();

                    } finally {
                        final BasicDBObject systemLogBasicObject = new BasicDBObject("user_id",
                                systemLog.getString("user_id")).append("userName", systemLog.getString("userName"))
                                .append("type", systemLog.getString("type"))
                                .append("message", systemLog.getString("message"))
                                .append("createdTs", dateFormat.format(date));
                        MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);
                    }
                    return responseToRestController;
                }
                if (rentLease.equals("Buying")) {
                    try {
                        final DecimalFormat df = new DecimalFormat("###.##");
                        user = MongoDBConnection.userColl.findOne("{'_id':#}", userId).as(User.class);
                        final int downPayment = cargoalmodel.getDownPayment();
                        carPrice=carPrice-cargoalmodel.getTradeIn();
                        final double amount = downPayment * carPrice / 100;
                        final long mortgageAmount = (long) (carPrice - amount);
                        timePeriod = cargoalmodel.getTimePeriod();
                        creditScore = cargoalmodel.getCreditScore();
                        final Cargoalmodel intrs = MongoDBConnection.goalcoll.findOne("{'user_id':#,'_id':#}", userId,goalId).as(Cargoalmodel.class);
                        final double intrestRateDb = Math.round(intrs.getIntrestRate() * 100.0) / 100.0;
                        if(cargoalmodel.getIntrestRate() == intrestRateDb) {
                            intrestRate = calculateIntrestRate(timePeriod, creditScore);
                        }else{
                            intrestRate=cargoalmodel.getIntrestRate();
                        }
                        final double monthlyPayment = cargoalmodel.getMonthlyPayment();
                        final double annualCost = cargoalmodel.getAnnualCost();
                        final double exactAnualMorgage = calAnualMorgage1(timePeriod, intrestRate, mortgageAmount);
                        final String carBuyingReccursive = cargoalmodel.getCarBuyingReccursive();
                        final int reccursiveBuyingPeriod = cargoalmodel.getReccursiveBuyingPeriod();
                        if(carBuyingReccursive.equals("BuyingNo")) {
                            for (int i = 0; i < limits.length(); i++) {
                                if (carYear > currentYear + GOALWITHINFIVEYEARS) {
                                    if (limits.getJSONObject(i).getInt("year") < carYear) {
                                        limits.getJSONObject(i).put("taxable", exactAnualMorgage * timePeriod
                                                + limits.getJSONObject(i).getInt("taxable") + amount);
                                    } else if (limits.getJSONObject(i).getInt("year") >= carYear
                                            && limits.getJSONObject(i).getInt("year") < carYear + timePeriod - 1) {
                                        limits.getJSONObject(i).put("taxable",
                                                limits.getJSONObject(i).getInt("taxable") + exactAnualMorgage
                                                * (carYear + timePeriod - limits.getJSONObject(i).getInt("year")));
                                    }
                                    if (deductions.getJSONObject(i).getInt("year") == carYear) {
                                        deductions.getJSONObject(i).put("taxable", exactAnualMorgage + amount
                                                + deductions.getJSONObject(i).getDouble("taxable"));
                                    } else if (deductions.getJSONObject(i).getInt("year") > carYear
                                            && deductions.getJSONObject(i).getInt("year") < carYear + timePeriod) {

                                        deductions.getJSONObject(i).put("taxable",
                                                exactAnualMorgage + deductions.getJSONObject(i).getDouble("taxable"));
                                    }
                                } else {
                                    if (limits.getJSONObject(i).getInt("year") < carYear) {
                                        limits.getJSONObject(i).put("saving", exactAnualMorgage * timePeriod
                                                + limits.getJSONObject(i).getInt("saving") + amount);
                                    } else if (limits.getJSONObject(i).getInt("year") >= carYear
                                            && limits.getJSONObject(i).getInt("year") < carYear + timePeriod - 1) {
                                        limits.getJSONObject(i).put("saving",
                                                limits.getJSONObject(i).getInt("saving") + exactAnualMorgage * (carYear
                                                        + timePeriod - limits.getJSONObject(i).getInt("year") - 1));
                                    }
                                    if (deductions.getJSONObject(i).getInt("year") == carYear) {
                                        deductions.getJSONObject(i).put("saving", exactAnualMorgage + amount
                                                + deductions.getJSONObject(i).getDouble("saving"));
                                    } else if (deductions.getJSONObject(i).getInt("year") > carYear
                                            && deductions.getJSONObject(i).getInt("year") < carYear + timePeriod) {

                                        deductions.getJSONObject(i).put("saving",
                                                exactAnualMorgage + deductions.getJSONObject(i).getDouble("saving"));
                                    }
                                }

                            }
                        } else {
                            int j = 0;
                            for (int i = 0; i < limits.length(); i++) {

                                if (limits.getJSONObject(i).getInt("year") < carYear) {
                                    limits.getJSONObject(i).put("saving", exactAnualMorgage * timePeriod
                                            + limits.getJSONObject(i).getInt("saving") + amount);
                                } else if (limits.getJSONObject(i).getInt("year") >= carYear
                                        && limits.getJSONObject(i).getInt("year") < carYear + timePeriod - 1) {
                                    limits.getJSONObject(i).put("saving",
                                            limits.getJSONObject(i).getInt("saving") + exactAnualMorgage * (carYear
                                                    + timePeriod - limits.getJSONObject(i).getInt("year") - 1));
                                }
                                if (deductions.getJSONObject(i).getInt("year") == carYear) {
                                    deductions.getJSONObject(i).put("saving", exactAnualMorgage + amount
                                            + deductions.getJSONObject(i).getDouble("saving"));
                                }
                                if(limits.getJSONObject(i).getInt("year")==carYear && j<reccursivePeriod)
                                {
                                    carYear=carYear+timePeriod;
                                    j++;
                                    System.out.println(" j "+j);
                                }
                            }
                        }

                        final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                                .findOne("{user_id:#,profile_name:#}", userId, finPlanProfileName)
                                .as(IncomeProfile.class);
                        final JSONObject incomeProfileJson = new JSONObject(
                                jsonMapper.writeValueAsString(incomeProfileDetails));
                        final JSONArray userIncome = incomeProfileJson.getJSONArray("user_income");
                        JSONArray spouseIncomeArray = new JSONArray();
                        JSONArray combinedIncome = new JSONArray();
                        int spouseAge;
                        if (maritalStatus.equals("Yes")) {
                            spouseIncomeArray = incomeProfileJson.getJSONArray("spouse_income");
                            final IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
                            combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, spouseIncomeArray);
                            spouseAge = finDetails.getSpouseAge();
                        } else {
                            combinedIncome = userIncome;
                            spouseAge = 0;
                        }

                        final int emergencyFundAmt = finDetails.getEmergencyFundAmt();
                        final boolean emergencyFundFlag = finDetails.isEmergencyFundFlag();

                        final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                                .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement")
                                .as(RetirementGoal.class);
                        int spouseStartYear = 0;
                        int userStartYear = 0;

                        if (retirementObj != null) {
                            userStartYear = details.getBirthYear() + retirementObj.getRetirementAge();
                            if (finDetails.getMarital_status().equals("Yes")
                                    && details.getMarital_status().equals("No")) {
                                spouseStartYear = finDetails.getSpouseBirthYear()
                                        + retirementObj.getSpouseRetirementAge();
                            } else if (details.getMarital_status().equals("Yes")) {
                                spouseStartYear = details.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                            }

                        } else {
                            userStartYear = details.getBirthYear() + RETAIRMENTAGE;
                            if (finDetails.getMarital_status().equals("Yes")
                                    && details.getMarital_status().equals("No")) {
                                spouseStartYear = finDetails.getSpouseBirthYear() + RETAIRMENTAGE;
                            } else if (details.getMarital_status().equals("Yes")) {
                                spouseStartYear = details.getSpouseBirthYear() + RETAIRMENTAGE;
                            }
                        }
                        final JSONObject retirementData = new JSONObject();
                        retirementData.put("spouseStartYear", spouseStartYear);
                        retirementData.put("userStartYear", userStartYear);
                        String emergencyType = null;
                        String monthsOfIncome = null;
                        String monthsOfExpense = null;
                        if (emergencyFundFlag) {
                            final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                                    .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Emergency Fund")
                                    .as(Emergencyfundmodel.class);
                            emergencyType = emergencyObj.getTimePeriod();
                            monthsOfIncome = emergencyObj.getMonthI();
                            monthsOfExpense = emergencyObj.getMonthE();
                        }
                        final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                                spouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, userId,
                                fillingExemtion, userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions,
                                kidBirthYear, retirementData, retirementObj, housingExpense, emergencyType,
                                monthsOfIncome, monthsOfExpense, finDetails.isRetirementFlag());

                        final String status = result.getString("status");
                        if (status.equals("success")) {
                            goalName=cargoalmodel.getGoalType()+ " - " + goalId;
                            /*                            updateFinplanForCarGoal(finId, userId, goalId, startYear, endYear, endYear1, monthlyPayment,
                                    carPrice, annualCost, exactAnualMorgage, rentLease, "insert", amount,goalName);*/
                            final JSONObject carExpenseData=new JSONObject();
                            carExpenseData.put("startYear", startYear);
                            carExpenseData.put("endYear", endYear1);
                            carExpenseData.put("annualCost", exactAnualMorgage);
                            carExpenseData.put("downPayment", amount);
                            carExpenseData.put("goalName", goalName);
                            newExpenseArray.put(carExpenseData);
                            MongoDBConnection.finplancol.update("{'_id': '" + finId + "'}").upsert().multi()
                            .with("{$set: {'expense.carGoalExpense':" + newExpenseArray + "}}");
                            MongoDBConnection.goalcoll.update("{'_id': '" + goalId + "'}").upsert().multi()
                            .with("{$set: {'carYear':" + cargoalmodel.getCarYear() + ",'downPayment':"
                                    + cargoalmodel.getDownPayment() + ",'carPrice':"
                                    + cargoalmodel.getCarPrice() + ",'creditScore':'" + creditScore
                                    + "','rentLease':'" + cargoalmodel.getRentLease() + "','intrestRate':"
                                    + intrestRate + ",'timePeriod':" + cargoalmodel.getTimePeriod()
                                    + ",'goalFeasibility':" + true + ",'exactAnual_morgage':"
                                    + exactAnualMorgage + ",'modified_ts':'" + dateFormat.format(date) + "','goalName':'"+goalName+"','carReccursive':'"+carBuyingReccursive +"','reccursivePeriod':"+reccursiveBuyingPeriod+",'tradeIn':"+cargoalmodel.getTradeIn()+"}}");
                            MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                            .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits
                                    + ",'deductions':" + deductions + "}}");
                            responseToRestController.put("exactAnual_morgage", decimalFloat.format(exactAnualMorgage));
                            responseToRestController.put("carYear", cargoalmodel.getCarYear());
                            responseToRestController.put("carPrice", cargoalmodel.getCarPrice());
                            responseToRestController.put("timePeriod", timePeriod);
                            responseToRestController.put("rentLease", cargoalmodel.getRentLease());
                            responseToRestController.put("intrestRate", decimalFloat.format(intrestRate));
                            responseToRestController.put("downPayment", cargoalmodel.getDownPayment());
                            responseToRestController.put("creditScore", cargoalmodel.getCreditScore());
                            responseToRestController.put("status", "success");
                            responseToRestController.put("goalFeasibility", true);
                            responseToRestController.put("goal_id", goalId);
                            responseToRestController.put("exactAnual_morgage", decimalFloat.format(exactAnualMorgage));
                            responseToRestController.put("carPrice", cargoalmodel.getCarPrice());
                            responseToRestController.put("tradeIn",cargoalmodel.getTradeIn() );
                            responseToRestController.put("reccursivePeriod", reccursiveBuyingPeriod);
                            responseToRestController.put("carReccursive",carBuyingReccursive);
                            systemLog.put("type", "success");
                            systemLog.put("message", "Goal updated sucessfully");
                            systemLog.put("userName", user.getName());
                            systemLog.put("user_id", userId);
                            systemLog.put("createdTs", dateFormat.format(date));
                        } else {
                            responseToRestController.put("status", "fail");
                        }
                    } catch (final Exception e) {
                        systemLog.put("type", "fail");
                        systemLog.put("message", "Goal updating failed");
                        systemLog.put("userName", user.getName());
                        systemLog.put("user_id", user.get_id());
                        systemLog.put("createdTs", dateFormat.format(date));
                        e.printStackTrace();
                    } finally {
                        final BasicDBObject systemLogBasicObject = new BasicDBObject("user_id",
                                systemLog.getString("user_id")).append("userName", systemLog.getString("userName"))
                                .append("type", systemLog.getString("type"))
                                .append("message", systemLog.getString("message"))
                                .append("createdTs", dateFormat.format(date));
                        MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);
                    }

                    return responseToRestController;
                }

            }
            return responseToRestController;
        } catch (final Exception e) {
            e.printStackTrace();
            return responseToRestController;
        }
    }

    private double calculateIntrestRate(final int timePeriod, final String creditScore) {
        double timePeriodInMonth = 0;
        double armValues = 0;
        double intrest = 0;
        double intrestRateFromtable = 0;

        final RateAdjustmentFactor rateFactors = MongoDBConnection.rateAdjustmentColl
                .findOne("{creditScore:#}", creditScore).as(RateAdjustmentFactor.class);
        if (timePeriod == TIMEPERIODFORTHREEYEARS) {
            timePeriodInMonth = TIMEPERIODFORTHREEYEARSINMONTHS;
            armValues = TIMEPERIODFORTHREEYEARSARM;
            intrestRateFromtable = Double.parseDouble(rateFactors.getThreeSixMonthAuto());
        } else if (timePeriod == GOALWITHINFIVEYEARS) {
            timePeriodInMonth = GOALWITHINFIVEYEARSINMONTHS;
            armValues = GOALWITHINFIVEYEARSARM;
            intrestRateFromtable = Double.parseDouble(rateFactors.getFourEightMonthAuto());
        } else {
            timePeriodInMonth = DEFAULTYEARSINMONTHS;
            armValues = DEFAULTYEARSINMONTHSARM;
            intrestRateFromtable = Double.parseDouble(rateFactors.getSixZeroMonthAuto());

        }
        intrest = armValues * intrestRateFromtable;
        return intrest;
    }

    public double calAnualMorgage(final double x, final double r, final int n, final long carPrice) {
        final double monthlyPayment = (carPrice * Math.pow(1 + r, n) - x) / ((Math.pow(1 + r, n) - 1) / r);
        return monthlyPayment;
    }

    public double calAnualMorgage1(final double timePeriod, final double intrestRate, final double mortgageAmount) {
        final double exactAnualMorgage = Math.pow(1 + intrestRate / 100.0, timePeriod) * (intrestRate / 100.0)
                / (Math.pow(1 + intrestRate / 100.0, timePeriod) - 1) * mortgageAmount;
        return exactAnualMorgage;
    }

    public JSONObject updateFinplanForCarGoal(final String finId, final String userId, final String goalId,
            final int startYear, final int endYear, final int endYear1, final double monthlyPayment,
            final long carPrice, final double annualCost, final double exactAnualMorgage, final String rentLease,
            final String actionType, final double downPayment,final String goalName) throws JSONException {
        final JSONObject responseToinsertupdateHousegoal = new JSONObject();
        responseToinsertupdateHousegoal.put("status", "fail");
        final JSONObject carExpenseData = new JSONObject();
        if (rentLease.equals("Leasing")) {
            try {
                carExpenseData.put("endYear", endYear);
                carExpenseData.put("startYear", startYear);
                carExpenseData.put("monthlyPayment", monthlyPayment);
                carExpenseData.put("annualCost", annualCost);
                carExpenseData.put("goalName", goalName);
                if (actionType.equals("insert")) {
                    MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                    .with("{$addToSet: {'goals':'" + goalId + "'}}");
                }
                MongoDBConnection.finplancol.update("{'_id': '" + finId + "'}").upsert().multi()
                .with("{$set: {'expense.car_expense':" + carExpenseData + "}}");
                responseToinsertupdateHousegoal.put("status", "success");
            } catch (final JSONException e) {
                e.printStackTrace();
            }
            return responseToinsertupdateHousegoal;
        }
        if (rentLease.equals("Buying")) {
            try {
                carExpenseData.put("startYear", startYear);
                carExpenseData.put("endYear", endYear1);
                carExpenseData.put("annualCost", exactAnualMorgage);
                carExpenseData.put("downPayment", downPayment);
                carExpenseData.put("goalName", goalName);
                if (actionType.equals("insert")) {
                    MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                    .with("{$addToSet: {'goals':'" + goalId + "'}}");
                }
                MongoDBConnection.finplancol.update("{'_id': '" + finId + "'}").upsert().multi()
                .with("{$set: {'expense.car_expense':" + carExpenseData + "}}");
                responseToinsertupdateHousegoal.put("status", "success");
            } catch (final JSONException e) {
                e.printStackTrace();
            }
            return responseToinsertupdateHousegoal;
        }
        return responseToinsertupdateHousegoal;
    }

    public JSONObject editCarGoalData(final Cargoalmodel cargoalmodel) {
        final JSONObject responseToRestController = new JSONObject();
        final String goalId = cargoalmodel.getGoal_id();
        try {
            final Cargoalmodel cargoalmodeldata = MongoDBConnection.goalcoll.findOne("{_id:#}", goalId)
                    .as(Cargoalmodel.class);
            final String rentLease = cargoalmodeldata.getRentLease();
            responseToRestController.put("status", "success");
            responseToRestController.put("carYear", cargoalmodeldata.getCarYear());
            responseToRestController.put("carPrice", cargoalmodeldata.getCarPrice());
            responseToRestController.put("planName", cargoalmodeldata.getPlan_name());
            responseToRestController.put("goal_id", cargoalmodeldata.get_id());
            responseToRestController.put("goalFeasiblity", cargoalmodeldata.getGoalFeasibility());
            if (rentLease.equals("Leasing")) {
                responseToRestController.put("annualCost", cargoalmodeldata.getAnnualCost());
                responseToRestController.put("monthlyPayment",
                        decimalFloat.format(cargoalmodeldata.getMonthlyPayment()));
                responseToRestController.put("rentLease", cargoalmodeldata.getRentLease());
                responseToRestController.put("leaseYear", cargoalmodeldata.getLeaseYear());
                responseToRestController.put("moneyFactor", cargoalmodeldata.getMoneyFactor());
                responseToRestController.put("residualValue", cargoalmodeldata.getResidualValue());
                responseToRestController.put("carReccursive",cargoalmodeldata.getCarReccursive());
                responseToRestController.put("reccursivePeriod", cargoalmodeldata.getReccursivePeriod());

            }
            if (rentLease.equals("Buying")) {
                final DecimalFormat df = new DecimalFormat("###.##");
                //responseToRestController.put("intrestRate", cargoalmodeldata.getIntrestRate());
                responseToRestController.put("intrestRate", df.format(cargoalmodeldata.getIntrestRate()));
                responseToRestController.put("timePeriod", cargoalmodeldata.getTimePeriod());
                responseToRestController.put("rentLease", cargoalmodeldata.getRentLease());
                responseToRestController.put("exactAnual_morgage", cargoalmodeldata.getExactAnual_morgage());
                responseToRestController.put("downPayment", cargoalmodeldata.getDownPayment());
                responseToRestController.put("creditScore", cargoalmodeldata.getCreditScore());
                responseToRestController.put("tradeIn", cargoalmodeldata.getTradeIn());
                responseToRestController.put("carReccursive", cargoalmodeldata.getCarReccursive());
                responseToRestController.put("reccursivePeriod", cargoalmodeldata.getReccursivePeriod());
            }

            return responseToRestController;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return responseToRestController;

    }
}
