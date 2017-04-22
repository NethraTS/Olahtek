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
import com.mongorest.olahtek.model.Counters;
import com.mongorest.olahtek.model.Customizedgoalmodel;
import com.mongorest.olahtek.model.Emergencyfundmodel;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.User;

@Service("customizedGoalImpl")
@Transactional
public class CustomizedGoalImpl {
    private final JSONArray arry = new JSONArray();
    private final JSONArray amounpre = new JSONArray();
    private final JSONArray second = new JSONArray();
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private final Date date = new Date();
    private final DecimalFormat decimalFloat = new DecimalFormat("#.##");
    private final Calendar cal = Calendar.getInstance();
    private final int currentYear = cal.get(Calendar.YEAR);
    private double mypreviousamount = 0;
    private int mypreviousgoalStartingYear = 0;
    private int mypreviousGoalendingyear = 0;
    private int startyearofgoal = 0;
    private int endyearofgoal = 0;
    private int forgoalcost = 0;
    private final JSONArray limitsFrodelet = new JSONArray();
    private JSONArray deductuonFrodelet = new JSONArray();
    private static final int DEFAULT_RETIRMENT_AGE = 70;
    private static final int FIVE = 5;
    private static final int FOUR = 4;

    @JsonCreator
    public JSONObject insertCustomizedGoalData(final Customizedgoalmodel customizedGoalModel) {
        JSONObject responseToRestController = new JSONObject();
        String goalId;
        final String userId = customizedGoalModel.getUser_id();
        final String planName = customizedGoalModel.getPlan_name();
        final String goalname = customizedGoalModel.getGoalname();
        final double goalcost = customizedGoalModel.getGoalCost();
        final int goalendingyear = customizedGoalModel.getGoalEndingyear();
        final int goalStartingYear = customizedGoalModel.getGoalStartingYear();
        final String typeOfGoal = customizedGoalModel.getTypeOfGoalCost();
        System.out.println("typeOfGoal  " + typeOfGoal);
        final String perYear = customizedGoalModel.getPerYear();
        if (planName != null) {
            final double amountSaves = customizedGoalModel.getGoalCost();
            mypreviousamount = amountSaves;
            mypreviousgoalStartingYear = goalStartingYear;
            mypreviousGoalendingyear = goalendingyear;
        }
        try {
            responseToRestController.put("status", "fail");
            final String actionHomeType = customizedGoalModel.getActionHomeType();
            if (actionHomeType.equals("insert")) {
                final FinPlan finDetailss = MongoDBConnection.finplancol
                        .findOne("{usr_id:#,plan_name:#}", userId, planName).as(FinPlan.class);
                final JSONObject finPlanJsons = new JSONObject(jsonMapper.writeValueAsString(finDetailss));
                final JSONArray deductions2 = finPlanJsons.getJSONArray("deductions");
                deductuonFrodelet = deductions2;
                arry.put(goalStartingYear);
                second.put(goalendingyear);
                final FinPlan getfinplan = MongoDBConnection.finplancol
                        .findOne("{usr_id:#,plan_name:#}", userId, planName).as(FinPlan.class);
                final String finId = getfinplan.get_id();
                responseToRestController = insertionIntoCustomizedGoal(planName, finId, goalname, goalcost,
                        goalendingyear, goalStartingYear, typeOfGoal, perYear, customizedGoalModel);
            } else if (actionHomeType.equals("deleteGoal")) {
                final FinPlan getfinplan = MongoDBConnection.finplancol
                        .findOne("{usr_id:#,plan_name:#}", userId, planName).as(FinPlan.class);
                final String finId = getfinplan.get_id();
                responseToRestController = deleteCustomizedGoal(planName, finId, goalname, goalcost, goalendingyear,
                        goalStartingYear, typeOfGoal, perYear, customizedGoalModel);
            } else {
                goalId = customizedGoalModel.getGoal_id();
                final Customizedgoalmodel customizedgoalfinID = MongoDBConnection.goalcoll.findOne("{_id:#}", goalId)
                        .as(Customizedgoalmodel.class);
                final String finId = customizedgoalfinID.getFin_id();
                responseToRestController = updateCustomizedGoal(planName, finId, goalId, actionHomeType,
                        customizedgoalfinID, goalcost, goalname, goalendingyear, goalStartingYear, typeOfGoal, perYear,
                        customizedGoalModel);
            }

            return responseToRestController;
        } catch (final Exception e) {
            e.printStackTrace();
            return responseToRestController;
        }
    }

    public JSONObject deleteCustomizedGoal(final String planName, final String finId, final String goalname,
            final double goalcost, final int goalendingyear, final int goalStartingYear, final String typeOfGoal,
            final String perYear, final Customizedgoalmodel customizedGoalModel) throws JSONException {
        final JSONObject responseToRestController = new JSONObject();
        JSONArray spouseIncome = new JSONArray();
        JSONArray combinedIncome = new JSONArray();
        JSONArray userIncome = new JSONArray();
        final String userId = customizedGoalModel.getUser_id();
        try {
            final FinPlan finDetails = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}", userId, planName)
                    .as(FinPlan.class);
            final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
            final String profileName = finDetails.getProfile_name();
            final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                    .findOne("{user_id:#,profile_name:#}", userId, profileName).as(IncomeProfile.class);
            final JSONObject incomeProfileJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
            userIncome = incomeProfileJson.getJSONArray("user_income");
            userIncome = incomeProfileJson.getJSONArray("user_income");
            final String maritalStatus = (String) finPlanJson.get("marital_status");
            if (maritalStatus.equals("Yes")) {
                spouseIncome = incomeProfileJson.getJSONArray("spouse_income");
                combinedIncome = incomeProfileJson.getJSONArray("combined_income");
            } else {
                combinedIncome = incomeProfileJson.getJSONArray("user_income");
            }
            final JSONArray userExpense = incomeProfileJson.getJSONArray("userExpense");
            final JSONArray assets = finPlanJson.getJSONArray("assests");
            JSONArray expenseObj = new JSONArray();
            JSONObject expense = new JSONObject();
            expense = finPlanJson.getJSONObject("expense");
            if (!expense.isNull("housing_expense")) {
                expenseObj = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
            }
            final JSONArray tax = finPlanJson.getJSONArray("tax");
            final JSONArray fillingExemtion = finPlanJson.getJSONArray("fillingExemtion");
            final User userInfo = MongoDBConnection.userColl.findOne("{'_id':#}", userId).as(User.class);
            final int age = userInfo.getAge();
            final int spouseAge = userInfo.getSpouseAge();
            final int emergencyFundAmt = finDetails.getEmergencyFundAmt();
            final boolean emergencyFundFlag = finDetails.isEmergencyFundFlag();
            JSONArray kidBirthYear = new JSONArray();
            if (!finPlanJson.isNull(null)) {
                kidBirthYear = finPlanJson.getJSONArray("kidBirthYear");
            }
            final FinPlan finPlanDetails = MongoDBConnection.finplancol
                    .findOne("{usr_id:#,plan_name:#}", userId, planName).as(FinPlan.class);
            final JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(finPlanDetails));
            final JSONObject expenseJson = finPlanJson.getJSONObject("expense");
            final JSONArray customizedArray = expenseJson.getJSONArray("customized_expense");
            final JSONArray goalExpenseArray = new JSONArray();
            final int flage = 0;
            double oldCost = 0;
            int oldStartYear = 0;
            int oldEndYear = 0;
            String oldGoalType = null;
            final JSONArray customizedArrayNew = new JSONArray();
            for (int i = 0; i < customizedArray.length(); i++) {
                if (customizedArray.getJSONObject(i).getString("goalname").equals(goalname)) {

                    oldCost = customizedArray.getJSONObject(i).getDouble("goalCost");
                    oldStartYear = customizedArray.getJSONObject(i).getInt("startYear");
                    oldEndYear = customizedArray.getJSONObject(i).getInt("endYear") - 1;
                    oldGoalType = customizedArray.getJSONObject(i).getString("goalType");
                    if (oldGoalType.equals("total")) {
                        oldCost = oldCost * (oldEndYear - oldStartYear + 1);
                    }
                } else {
                    customizedArrayNew.put(customizedArray.getJSONObject(i));
                }
            }
            JSONObject goalFeasiblity = new JSONObject();
            final JSONArray limits2 = finPlanJson.getJSONArray("limits");
            final JSONArray deductions = finPlanJson.getJSONArray("deductions");
            amounpre.put(goalcost);
            second.put(goalendingyear);
            arry.put(goalStartingYear);
            for (int i = 0; i < amounpre.length(); i++) {
                if (i != 0) {
                    forgoalcost = amounpre.getInt(i - 1);

                } else {
                    forgoalcost = amounpre.getInt(i);
                }
            }
            for (int i = 0; i < arry.length(); i++) {
                if (i != 0) {
                    startyearofgoal = arry.getInt(i - 1);

                } else {
                    startyearofgoal = arry.getInt(i);
                }

            }

            for (int i = 0; i < second.length(); i++) {

                if (i != 0) {
                    endyearofgoal = second.getInt(i - 1);

                } else {
                    endyearofgoal = second.getInt(i);
                }

            }
            final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                    .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement").as(RetirementGoal.class);
            //final Calendar cal = Calendar.getInstance();
            //final int currentYear = cal.get(Calendar.YEAR);
            int spouseStartYear = 0;
            int userStartYear = 0;
            if (retirementObj != null) {
                userStartYear = userInfo.getBirthYear() + retirementObj.getRetirementAge();
                if (finPlanDetails.getMarital_status().equals("Yes") && userInfo.getMarital_status().equals("No")) {
                    spouseStartYear = finPlanDetails.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                } else if (userInfo.getMarital_status().equals("Yes")) {
                    spouseStartYear = userInfo.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                }

            } else {
                userStartYear = userInfo.getBirthYear() + DEFAULT_RETIRMENT_AGE;
                if (finPlanDetails.getMarital_status().equals("Yes") && userInfo.getMarital_status().equals("No")) {
                    spouseStartYear = finPlanDetails.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                } else if (userInfo.getMarital_status().equals("Yes")) {
                    spouseStartYear = userInfo.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                }
            }
            final JSONObject retirementData = new JSONObject();
            retirementData.put("spouseStartYear", spouseStartYear);
            retirementData.put("userStartYear", userStartYear);
            final JSONArray limst = limitsRevertBackForCustomizedGoal(userId, goalname, oldGoalType, oldStartYear,
                    currentYear, oldEndYear, userExpense, oldCost, limits2);
            final JSONArray deductio = deductionsRevertBackForCustomizedGoal(userId, goalname, oldStartYear,
                    currentYear, oldCost, deductions, oldEndYear, typeOfGoal);
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
            goalFeasiblity = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome, spouseIncome,
                    userExpense, limst, maritalStatus, assets, tax, userId, fillingExemtion, age, spouseAge,
                    emergencyFundAmt, emergencyFundFlag, deductio, kidBirthYear, retirementData, retirementObj,
                    expenseObj, emergencyType, monthsOfIncome, monthsOfExpense, finDetails.isRetirementFlag());
            if (goalFeasiblity.getString("status").equals("success")) {
                if (flage == 1) {
                    MongoDBConnection.finplancol.update("{usr_id:#,plan_name:#}", userId, finPlanDetails.getPlan_name())
                    .with("{$set: {'goalExpenseArray':" + goalExpenseArray + "}}");
                }
                final JSONArray assets1 = finPlanJson.getJSONArray("assests");
                final JSONArray tax1 = finPlanJson.getJSONArray("tax");
                final JSONArray limits3 = finPlanJson.getJSONArray("limits");
                final JSONArray deductions4 = finPlanJson.getJSONArray("deductions");
                final JSONArray goalsArray = new JSONArray();
                final JSONObject userJsons = new JSONObject(jsonMapper.writeValueAsString(finDetails));
                if (!userJsons.isNull("goals")) {
                    final JSONArray goalJsonArray = userJsons.getJSONArray("goals");
                    for (int j = 0; j < goalJsonArray.length(); j++) {
                        final String goalId = (String) goalJsonArray.get(j);
                        if (!goalId.equals(customizedGoalModel.getGoal_id())) {
                            goalsArray.put(goalId);
                        } else {
                            System.out.println("continue...");
                            continue;
                        }
                    }

                }
                MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi().with("{$set: {'assests':"
                        + assets1 + ",'tax':" + tax1 + ",'limits':" + limits3 + ",'deductions':" + deductions4 + "}}");
                MongoDBConnection.finplancol.update("{usr_id:#,plan_name:#}", userId, finPlanDetails.getPlan_name())
                .with("{$set: {'expense.customized_expense':" + customizedArrayNew + ",'goals':" + goalsArray
                        + "}}");
                responseToRestController.put("status", "sucess");
            } else {
                responseToRestController.put("status", "fail");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return responseToRestController;

    }

    // -----create cargoal-----------------------------
    private JSONObject insertionIntoCustomizedGoal(final String planName, final String finId, final String goalname,
            final double goalcost, final int goalendingyear, final int goalStartingYear, final String typeOfGoal,
            final String perYear, final Customizedgoalmodel customizedGoalModel) throws JSONException {
        final JSONObject responseToRestController = new JSONObject();
        final JSONObject systemLog = new JSONObject();
        JSONArray spouseIncome = new JSONArray();
        JSONArray combinedIncome = new JSONArray();
        JSONArray userIncome = new JSONArray();
        final String userId = customizedGoalModel.getUser_id();
        final User user = MongoDBConnection.userColl.findOne("{'_id':#}", userId).as(User.class);
        try {
            final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
            final String goalId = "goal" + count.getGoal_id();
            final FinPlan finDetails = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}", userId, planName)
                    .as(FinPlan.class);
            final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
            final String profileName = finDetails.getProfile_name();
            final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                    .findOne("{user_id:#,profile_name:#}", userId, profileName).as(IncomeProfile.class);
            final JSONObject incomeProfileJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
            userIncome = incomeProfileJson.getJSONArray("user_income");
            final String maritalStatus = (String) finPlanJson.get("marital_status");
            if (maritalStatus.equals("Yes")) {
                spouseIncome = incomeProfileJson.getJSONArray("spouse_income");
                combinedIncome = incomeProfileJson.getJSONArray("combined_income");

            } else {
                combinedIncome = incomeProfileJson.getJSONArray("user_income");
            }
            final JSONArray userExpense = finPlanJson.getJSONArray("userExpense");
            JSONArray assets = finPlanJson.getJSONArray("assests");
            final JSONArray tax = finPlanJson.getJSONArray("tax");
            final JSONArray fillingExemtion = finPlanJson.getJSONArray("fillingExemtion");
            final User userInfo = MongoDBConnection.userColl.findOne("{'_id':#}", userId).as(User.class);
            final int age = userInfo.getAge();
            final int spouseAge = userInfo.getSpouseAge();
            final int emergencyFundAmt = finDetails.getEmergencyFundAmt();
            final boolean emergencyFundFlag = finDetails.isEmergencyFundFlag();
            JSONArray kidBirthYear = new JSONArray();
            if (!finPlanJson.isNull(null)) {
                kidBirthYear = finPlanJson.getJSONArray("kidBirthYear");
            }
            final JSONArray limits = finPlanJson.getJSONArray("limits");
            JSONArray expenseObj = new JSONArray();
            JSONObject expense = new JSONObject();
            expense = finPlanJson.getJSONObject("expense");
            if (!expense.isNull("housing_expense")) {
                expenseObj = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
            }
            final JSONArray deductions = finPlanJson.getJSONArray("deductions");
            final double customizedExpense = calCustomizedExpense(goalendingyear - 1, goalStartingYear, goalcost,
                    typeOfGoal, perYear);
            // -----checking Goal Feasibilty---------------------
            System.out.println("limits--->>" + limits);
            JSONObject goalFeasiblity = new JSONObject();
            final JSONArray limitss = limitsforCostomizedGoal(typeOfGoal, goalStartingYear, currentYear, goalendingyear,
                    userExpense, goalcost, limits);
            final JSONArray deducTions = deductionsForCostomizedGoal(goalStartingYear, currentYear, goalcost,
                    deductions, goalendingyear - 1, typeOfGoal);
            final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                    .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement").as(RetirementGoal.class);
            int spouseStartYear = 0;
            int userStartYear = 0;
            if (retirementObj != null) {
                userStartYear = userInfo.getBirthYear() + retirementObj.getRetirementAge();
                if (finDetails.getMarital_status().equals("Yes") && userInfo.getMarital_status().equals("No")) {
                    spouseStartYear = finDetails.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                } else if (userInfo.getMarital_status().equals("Yes")) {
                    spouseStartYear = userInfo.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                }

            } else {
                userStartYear = userInfo.getBirthYear() + DEFAULT_RETIRMENT_AGE;
                if (finDetails.getMarital_status().equals("Yes") && userInfo.getMarital_status().equals("No")) {
                    spouseStartYear = finDetails.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                } else if (userInfo.getMarital_status().equals("Yes")) {
                    spouseStartYear = userInfo.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
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
            goalFeasiblity = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome, spouseIncome,
                    userExpense, limitss, maritalStatus, assets, tax, userId, fillingExemtion, age, spouseAge,
                    emergencyFundAmt, emergencyFundFlag, deducTions, kidBirthYear, retirementData, retirementObj,
                    expenseObj, emergencyType, monthsOfIncome, monthsOfExpense, finDetails.isRetirementFlag());
            final BasicDBObject doc = new BasicDBObject("_id", goalId)
                    .append("user_id", customizedGoalModel.getUser_id()).append("fin_id", finId)
                    .append("plan_name", customizedGoalModel.getPlan_name())
                    .append("goalType", customizedGoalModel.getGoalType())
                    .append("goalname", customizedGoalModel.getGoalname()).append("goalCost", customizedExpense)
                    .append("goalEndingyear", customizedGoalModel.getGoalEndingyear())
                    .append("goalStartingYear", customizedGoalModel.getGoalStartingYear())
                    .append("typeOfGoalCost", customizedGoalModel.getTypeOfGoalCost())
                    .append("created_ts", dateFormat.format(date)).append("completedStatus", 1)
                    .append("modified_ts", dateFormat.format(date));
            if (goalFeasiblity.getString("status").equals("success")) {
                doc.append("goalFeasibility", true);
                MongoDBConnection.goalcoll.insert(doc);
                final String goalid = "goal" + count.getGoal_id();
                assets = finPlanJson.getJSONArray("assests");
                MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                .with("{$inc: {goal_id: 1}}");
                MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi().with("{$set: {'assests':"
                        + assets + ",'tax':" + tax + ",'limits':" + limitss + ",'deductions':" + deductions + "}}");
                System.out.println("Bala.  insert customixed goal cost" + goalcost);
                updateFinplanForCustomizedGoal(finId, userId, goalId, goalname, goalcost, goalendingyear,
                        goalStartingYear, typeOfGoal, perYear, "insert");
                MongoDBConnection.finplancol.update("{'usr_id':#,'plan_name':#}", userId, planName).upsert().multi()
                .with("{$addToSet: {'goals':'" + goalid + "'}}");
                responseToRestController.put("status", "success");
                responseToRestController.put("goal_id", goalId);
                responseToRestController.put("Goalname", customizedGoalModel.getGoalname());
                responseToRestController.put("Goalcost", customizedExpense);
                responseToRestController.put("Goalendingyear", customizedGoalModel.getGoalEndingyear());
                responseToRestController.put("Goalstartingyear", customizedGoalModel.getGoalStartingYear());
                responseToRestController.put("favoriteColors", customizedGoalModel.getTypeOfGoalCost());
                responseToRestController.put("plan_name", customizedGoalModel.getPlan_name());
                systemLog.put("type", "success");
                systemLog.put("message", "Goal Created Successfully!!");
                systemLog.put("userName", user.getName());
                systemLog.put("user_id", userId);
                systemLog.put("createdTs", dateFormat.format(date));
                responseToRestController.put("status", "success");
            } else {
                systemLog.put("type", "success");
                systemLog.put("message", "Goal Created Successfully!!");
                systemLog.put("userName", userInfo.getName());
                systemLog.put("user_id", userId);
                systemLog.put("createdTs", dateFormat.format(date));
                responseToRestController.put("status", "fail");
            }

        } catch (final Exception e) {
            systemLog.put("type", "error");
            systemLog.put("message", "Goal Creation Failed!!");
            systemLog.put("userName", user.getName());
            systemLog.put("user_id", userId);
            systemLog.put("createdTs", dateFormat.format(date));
            e.printStackTrace();
        } finally {
            final BasicDBObject systemLogBasicObject = new BasicDBObject("user_id", userId)
                    .append("userName", systemLog.getString("userName")).append("type", systemLog.getString("type"))
                    .append("message", systemLog.getString("message")).append("createdTs", dateFormat.format(date));
            MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);
        }
        return responseToRestController;
    }

    private JSONObject updateCustomizedGoal(final String planName, final String finId, final String goalId,
            final String actionHomeType, final Customizedgoalmodel customizedgoalfinID, final double goalcost,
            final String goalname, final int goalendingyear, final int goalStartingYear, final String typeOfGoal,
            final String perYear, final Customizedgoalmodel customizedGoalModel) throws JSONException {
        /*
         * amounpre.put(Goalcost); second.put(Goalendingyear);
         * arry.put(goalStartingYear); for(int i=0;i<amounpre.length();i++){
         * if(i!=0){ forgoalcost=amounpre.getInt(i-1); } else{
         * forgoalcost=amounpre.getInt(i); } } for(int i=0;i<arry.length();i++){
         * if(i!=0){ startyearofgoal=arry.getInt(i-1);
         *
         * }else{ startyearofgoal=arry.getInt(i); } }
         *
         * for(int i=0;i<second.length();i++){
         *
         * if(i!=0){ endyearofgoal=second.getInt(i-1);
         *
         * } else{ endyearofgoal=second.getInt(i); } }
         */
        System.out.println("Goalendingyear==" + goalendingyear + "..goalStartingYear==" + goalStartingYear
                + "..typeOfGoal=" + typeOfGoal);
        final JSONObject systemLog = new JSONObject();
        User user = new User();
        final double customizedExpense = calCustomizedExpense(goalendingyear - 1, goalStartingYear, goalcost,
                typeOfGoal, perYear);
        final String userId = customizedGoalModel.getUser_id();
        final JSONObject responseToRestController = new JSONObject();
        System.out.println("..customizedExpense...==" + customizedExpense);
        try {
            final FinPlan finDetails = MongoDBConnection.finplancol.findOne("{usr_id:#}", userId).as(FinPlan.class);
            final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
            JSONArray spouseIncome = new JSONArray();
            JSONArray combinedIncome = new JSONArray();
            JSONArray userIncome = new JSONArray();
            final String profileName = finDetails.getProfile_name();
            final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                    .findOne("{user_id:#,profile_name:#}", userId, profileName).as(IncomeProfile.class);
            final JSONObject incomeProfileJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
            userIncome = incomeProfileJson.getJSONArray("user_income");
            final String maritalStatus = (String) finPlanJson.get("marital_status");
            final JSONObject expenseJson = finPlanJson.getJSONObject("expense");
            final JSONArray customizedArray = expenseJson.getJSONArray("customized_expense");
            double oldCost = 0;
            int oldStartYear = 0;
            int oldEndYear = 0;
            String oldGoalType = null;
            for (int i = 0; i < customizedArray.length(); i++) {
                if (customizedArray.getJSONObject(i).getString("goalname").equals(goalname)) {

                    oldCost = customizedArray.getJSONObject(i).getDouble("goalCost");
                    oldStartYear = customizedArray.getJSONObject(i).getInt("startYear");
                    oldEndYear = customizedArray.getJSONObject(i).getInt("endYear") - 1;
                    oldGoalType = customizedArray.getJSONObject(i).getString("goalType");
                    System.out.println("customizedArray===" + customizedArray);
                    System.out.println("oldGoalTypeapppp====" + oldGoalType);
                    if (oldGoalType.equals("total")) {
                        oldCost = oldCost * (oldEndYear - oldStartYear + 1);
                    }
                }
                oldGoalType = customizedArray.getJSONObject(i).getString("goalType");
            }
            if (maritalStatus.equals("Yes")) {
                spouseIncome = incomeProfileJson.getJSONArray("spouse_income");
                combinedIncome = incomeProfileJson.getJSONArray("combined_income");

            } else {
                combinedIncome = incomeProfileJson.getJSONArray("user_income");
            }
            final JSONArray userExpense = finPlanJson.getJSONArray("userExpense");
            final JSONArray assets = finPlanJson.getJSONArray("assests");
            final JSONArray tax = finPlanJson.getJSONArray("tax");
            final JSONArray fillingExemtion = finPlanJson.getJSONArray("fillingExemtion");
            final User userInfo = MongoDBConnection.userColl.findOne("{'_id':#}", userId).as(User.class);
            final int age = userInfo.getAge();
            final int spouseAge = userInfo.getSpouseAge();
            final int emergencyFundAmt = finDetails.getEmergencyFundAmt();
            final boolean emergencyFundFlag = finDetails.isEmergencyFundFlag();
            JSONArray expenseObj = new JSONArray();
            JSONObject expense = new JSONObject();
            expense = finPlanJson.getJSONObject("expense");
            if (!expense.isNull("housing_expense")) {
                expenseObj = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
            }
            JSONArray kidBirthYear = new JSONArray();
            if (!finPlanJson.isNull(null)) {
                kidBirthYear = finPlanJson.getJSONArray("kidBirthYear");
            }
            user = MongoDBConnection.userColl.findOne("{'_id':#}", userId).as(User.class);
            systemLog.put("type", "success");
            systemLog.put("message", "Goal updated sucessfully");
            systemLog.put("userName", user.getName());
            systemLog.put("user_id", userId);
            systemLog.put("createdTs", dateFormat.format(date));
            // ----------------checking Goal Feasibilty---------------------
            JSONObject goalFeasiblity = new JSONObject();
            final JSONArray limits2 = finPlanJson.getJSONArray("limits");
            final JSONArray deductions2 = finPlanJson.getJSONArray("deductions");
            // System.out.println("Goalname===="+Goalname);
            // System.out.println("oldGoalType===="+oldGoalType);
            final JSONArray limst = limitsRevertBackForCustomizedGoal(userId, goalname, oldGoalType, oldStartYear,
                    currentYear, oldEndYear, userExpense, oldCost, limits2);
            final JSONArray deductio = deductionsRevertBackForCustomizedGoal(userId, goalname, oldStartYear,
                    currentYear, oldCost, deductions2, oldEndYear, oldGoalType);
            final JSONArray limitss = limitsforCostomizedGoal(typeOfGoal, goalStartingYear, currentYear, goalendingyear,
                    userExpense, goalcost, limst);
            final JSONArray deducTions = deductionsForCostomizedGoal(goalStartingYear, currentYear, goalcost, deductio,
                    goalendingyear - 1, typeOfGoal);
            final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                    .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement").as(RetirementGoal.class);
            int spouseStartYear = 0;
            int userStartYear = 0;
            if (retirementObj != null) {
                userStartYear = userInfo.getBirthYear() + retirementObj.getRetirementAge();
                if (finDetails.getMarital_status().equals("Yes") && userInfo.getMarital_status().equals("No")) {
                    spouseStartYear = finDetails.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                } else if (userInfo.getMarital_status().equals("Yes")) {
                    spouseStartYear = userInfo.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                }

            } else {
                userStartYear = userInfo.getBirthYear() + DEFAULT_RETIRMENT_AGE;
                if (finDetails.getMarital_status().equals("Yes") && userInfo.getMarital_status().equals("No")) {
                    spouseStartYear = finDetails.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                } else if (userInfo.getMarital_status().equals("Yes")) {
                    spouseStartYear = userInfo.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
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
            goalFeasiblity = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome, spouseIncome,
                    userExpense, limitss, maritalStatus, assets, tax, userId, fillingExemtion, age, spouseAge,
                    emergencyFundAmt, emergencyFundFlag, deducTions, kidBirthYear, retirementData, retirementObj,
                    expenseObj, emergencyType, monthsOfIncome, monthsOfExpense, finDetails.isRetirementFlag());
            if (goalFeasiblity.getString("status").equals("success")) {
                final boolean goalFeasiblityCheck = true;
                final JSONArray limits = finPlanJson.getJSONArray("limits");
                final JSONArray assetss = finPlanJson.getJSONArray("assests");
                final JSONArray taxs = finPlanJson.getJSONArray("tax");
                final JSONArray deductionss = finPlanJson.getJSONArray("deductions");
                updateFinplanForCustomizedGoal(finId, userId, goalId, goalname, goalcost, goalendingyear,
                        goalStartingYear, typeOfGoal, perYear, "insert");

                MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi().with("{$set: {'assests':"
                        + assetss + ",'tax':" + taxs + ",'limits':" + limits + ",'deductions':" + deductionss + "}}");

                MongoDBConnection.goalcoll.update("{'_id': '" + goalId + "'}").upsert().multi()
                .with("{$set: {'goalname':'" + customizedGoalModel.getGoalname() + "','goalType':'"
                        + customizedGoalModel.getGoalType() + "','goalCost':" + customizedExpense
                        + ",'goalEndingyear':" + customizedGoalModel.getGoalEndingyear() + ",'typeOfGoalCost':'"
                        + customizedGoalModel.getTypeOfGoalCost() + "','goalStartingYear':"
                        + customizedGoalModel.getGoalStartingYear() + "," + "'goalFeasibility':"
                        + goalFeasiblityCheck + "," + "'modified_ts':'" + dateFormat.format(date) + "'}}");
                responseToRestController.put("status", "success");
                responseToRestController.put("goal_id", goalId);
                responseToRestController.put("Goalname", customizedGoalModel.getGoalname());
                responseToRestController.put("Goalcost", customizedExpense);
                responseToRestController.put("Goalendingyear", customizedGoalModel.getGoalEndingyear());
                responseToRestController.put("Goalstartingyear", customizedGoalModel.getGoalStartingYear());
                responseToRestController.put("favoriteColors", customizedGoalModel.getTypeOfGoalCost());
                responseToRestController.put("plan_name", customizedGoalModel.getPlan_name());
                responseToRestController.put("goalFeasiblity", goalFeasiblityCheck);
            } else {
                systemLog.put("type", "success");
                systemLog.put("message", "Goal Created Successfully!!");
                systemLog.put("userName", userInfo.getName());
                systemLog.put("user_id", userId);
                systemLog.put("createdTs", dateFormat.format(date));
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
            final BasicDBObject systemLogBasicObject = new BasicDBObject("user_id", user.get_id())
                    .append("userName", systemLog.getString("userName")).append("type", systemLog.getString("type"))
                    .append("message", systemLog.getString("message")).append("createdTs", dateFormat.format(date));
            MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);
        }
        return responseToRestController;
    }

    public double calCustomizedExpense(final int goalendingyear, final int goalstartingyear, final double goalcost,
            final String typeOfGoal, final String perYear) {
        double customizedExpense = 0.0;
        if (typeOfGoal.equals("total")) {
            customizedExpense = goalcost / (((double) goalendingyear - (double) goalstartingyear) + 1.0);

        } else if (typeOfGoal.equals("peryear")) {
            customizedExpense = goalcost;
        }
        return customizedExpense;
    }

    // ------ update finplan collection goal-----------------------------
    public JSONObject updateFinplanForCustomizedGoal(final String finId, final String userId, final String goalId,
            final String goalname, final double goalcost, final int goalendingyear, final int goalStartingYear,
            final String typeOfGoal, final String perYear, final String actionType) throws JSONException {
        final double customizedExpense = calCustomizedExpense(goalendingyear - 1, goalStartingYear, goalcost,
                typeOfGoal, perYear);
        final JSONObject responseToinsertupdateHousegoal = new JSONObject();
        responseToinsertupdateHousegoal.put("status", "fail");
        final JSONObject customizedExpensedata = new JSONObject();
        final JSONArray customizedExpens = new JSONArray();
        try {
            customizedExpensedata.put("startYear", goalStartingYear);
            customizedExpensedata.put("endYear", goalendingyear);
            customizedExpensedata.put("goalname", goalname);
            customizedExpensedata.put("goalCost", customizedExpense);
            customizedExpensedata.put("goalType", typeOfGoal);
            customizedExpens.put(customizedExpensedata);
            if (actionType.equals("insert")) {
                MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                .with("{$addToSet: {'goals':'" + goalId + "'}}");
            }
            final FinPlan finPlanDetails = MongoDBConnection.finplancol.findOne("{_id:#}", finId).as(FinPlan.class);
            final JSONObject finJson = new JSONObject(jsonMapper.writeValueAsString(finPlanDetails));
            JSONArray customizedArray = null;
            JSONObject expenseJson = null;
            if (!finJson.isNull("expense")) {
                expenseJson = finJson.getJSONObject("expense");
                if (!expenseJson.isNull("customized_expense")) {
                    customizedArray = expenseJson.getJSONArray("customized_expense");
                    /*
                     * MongoDBConnection.finplancol.update("{'_id': '" + fin_id
                     * + "'}").upsert().multi() .with(
                     * "{$set: {'expense.customized_expense':" +
                     * customized_expense + "}}");
                     */
                }
            }
            boolean flag = false;
            if (customizedArray == null) {
                MongoDBConnection.finplancol.update("{'_id': '" + finId + "'}").upsert().multi()
                .with("{$set: {'expense.customized_expense':" + customizedExpens + "}}");
            } else {
                for (int i = 0; i < customizedArray.length(); i++) {
                    System.out.println("goal name  " + customizedArray.getJSONObject(i).getString("goalname"));
                    System.out.println("fin id      " + finId);
                    final Customizedgoalmodel customize1 = MongoDBConnection.goalcoll.findOne("{_id:#}", goalId)
                            .as(Customizedgoalmodel.class);
                    System.out.println(
                            "customize  aparna   " + new JSONObject(jsonMapper.writeValueAsString(customize1)));

                    final Customizedgoalmodel customize = MongoDBConnection.goalcoll.findOne("{goalname:#,fin_id:#}",
                            customizedArray.getJSONObject(i).getString("goalname"), finId)
                            .as(Customizedgoalmodel.class);
                    // System.out.println("customize "+customize);
                    // System.out.println("hdgeyjrgfr "+customize.get_id());
                    if (customize.get_id().equals(goalId)) {
                        customizedArray.getJSONObject(i).put("endYear", goalendingyear);
                        customizedArray.getJSONObject(i).put("startYear", goalStartingYear);
                        customizedArray.getJSONObject(i).put("goalCost", customizedExpense);
                        customizedArray.getJSONObject(i).put("goalType", typeOfGoal);
                        customizedArray.getJSONObject(i).put("goalname", goalname);
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("inside flag else true");
                    MongoDBConnection.finplancol.update("{'_id': '" + finId + "'}").upsert().multi()
                    .with("{$set: {'expense.customized_expense':" + customizedArray + "}}");
                } else {
                    System.out.println("inside flag false");
                    customizedArray.put(customizedExpensedata);
                    MongoDBConnection.finplancol.update("{'_id': '" + finId + "'}").upsert().multi()
                    .with("{$set: {'expense.customized_expense':" + customizedArray + "}}");
                }
            }
            responseToinsertupdateHousegoal.put("status", "success");
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return responseToinsertupdateHousegoal;
    }

    // ---------edit/fetching the car goal-----------------------------
    public JSONObject editCustomizedGoalData(final Customizedgoalmodel customizedGoalModel) {
        final JSONObject responseToRestController = new JSONObject();
        final String goalId = customizedGoalModel.getGoal_id();
        try {

            final Customizedgoalmodel customizedGoalModeldata = MongoDBConnection.goalcoll.findOne("{_id:#}", goalId)
                    .as(Customizedgoalmodel.class);
            System.out.println("goal_id" + goalId);
            System.out.println("customizedGoalModeldata" + customizedGoalModeldata);
            final JSONObject goalJsons = new JSONObject(jsonMapper.writeValueAsString(customizedGoalModeldata));
            System.out.println("goalJsons....." + goalJsons);
            responseToRestController.put("status", "success");
            responseToRestController.put("Goalname", customizedGoalModeldata.getGoalname());
            responseToRestController.put("Goalcost", customizedGoalModeldata.getGoalCost());
            responseToRestController.put("Goalendingyear", customizedGoalModeldata.getGoalEndingyear());
            responseToRestController.put("Goalstartingyear", customizedGoalModeldata.getGoalStartingYear());
            responseToRestController.put("typeOfGoalCost", customizedGoalModeldata.getTypeOfGoalCost());
            responseToRestController.put("plan_name", customizedGoalModeldata.getPlan_name());
            responseToRestController.put("goal_id", customizedGoalModeldata.get_id());
            responseToRestController.put("goalFeasiblity", customizedGoalModeldata.getGoalFeasibility());
            return responseToRestController;
        } catch (final Exception e) {

            e.printStackTrace();
        }
        return responseToRestController;
    }

    public JSONArray deductionsForCostomizedGoal(final int goalStartingYear, final int currentYr, final double amountSv,
            final JSONArray deductions, final int goalendingyear, final String goalType) {

        try {
            int i = 0;
            int currYear = currentYr;
            double amountSave = amountSv;
            final int tempCurrentYear = currYear;
            if (goalType.equals("total")) {
                amountSave = amountSave / (goalendingyear - goalStartingYear + 1);
            }
            System.out.println("currentYear.." + currYear + "..goalStartingYear=" + goalStartingYear);
            while (currYear <= goalendingyear) {
                if (goalStartingYear >= (tempCurrentYear + FIVE)) {
                    if (currYear < goalStartingYear) {
                        System.out.println("currentYear.." + currYear);
                    } else if (currYear <= goalendingyear) {
                        deductions.getJSONObject(i).put("taxable",
                                (deductions.getJSONObject(i).getInt("taxable") + amountSave));
                    }
                } else {
                    if (currYear < goalStartingYear) {
                        System.out.println("currentYear.." + currYear);
                    } else if (currYear <= goalendingyear) {
                        deductions.getJSONObject(i).put("saving",
                                (deductions.getJSONObject(i).getInt("saving") + amountSave));
                    }
                }
                currYear++;
                i++;
            }
        } catch (final JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return deductions;
    }

    public JSONArray limitsRevertBackForCustomizedGoal(final String userId, final String goalname,
            final String typeOfGoal, final int goalStartingYear, final int currentYr, final int goalendingyear,
            final JSONArray userExpense, final double amountSave, final JSONArray lmts) {
        JSONArray limits = new JSONArray();
        limits = lmts;
        if ((goalStartingYear - currentYr) >= FIVE) {
            limits = taxableLimitsRevertBackForCustomizedGoal(userId, goalname, typeOfGoal, goalStartingYear,
                    currentYr, goalendingyear, userExpense, amountSave, limits);
        } else {
            limits = savingLimitsRevertBackForCustomizedGoal(userId, goalname, typeOfGoal, goalStartingYear,
                    currentYr, goalendingyear, userExpense, amountSave, limits);
        }

        return limits;
    }

    public JSONArray taxableLimitsRevertBackForCustomizedGoal(final String userId, final String goalname,
            final String typeOfGoal, final int goalStartingYear, final int currentYr, final int goalendingyear,
            final JSONArray userExpense, final double expense, final JSONArray limits) {
        System.out.println("Ravi:: expense==" + expense + "Goalendingyear=" + goalendingyear + "..goalStartingYear="
                + goalStartingYear);
        try {
            int currYear = currentYr;
            if (typeOfGoal.equals("total")) {
                int i = 0;
                final long numberyars = goalendingyear - goalStartingYear + 1;
                double mytemp = (expense / numberyars);
                while (currYear <= goalendingyear) {
                    if (currYear <= goalStartingYear) {

                        if (limits.getJSONObject(i).getInt("taxable") == 0) {

                            limits.getJSONObject(i).put("taxable", 0);

                        } else {

                            limits.getJSONObject(i).put("taxable",
                                    (limits.getJSONObject(i).getInt("taxable") - expense));
                        }
                    } else if (currYear <= goalendingyear) {
                        limits.getJSONObject(i).put("taxable",
                                limits.getJSONObject(i).getInt("taxable") - (expense - mytemp));
                        if (limits.getJSONObject(i).getDouble("taxable") < 0) {
                            limits.getJSONObject(i).put("taxable", 0);
                        }
                        mytemp = mytemp + (expense / numberyars);
                    }
                    /*
                     * else if(currentYear==goalStartingYear){
                     * if(limits.getJSONObject(i).getInt("year")==currentYear){
                     * limits.getJSONObject(i).put("saving",
                     * limits.getJSONObject(i).getInt("saving")-expense*(
                     * Goalendingyear-limits.getJSONObject(i).getInt("year"))-
                     * mytemp);
                     * if(limits.getJSONObject(i).getDouble("saving")<0)
                     * {limits.getJSONObject(i).put("saving", 0);} }
                     *
                     * }
                     */

                    currYear++;
                    i++;

                }
            } else {
                int i = 0;
                while (currYear <= goalendingyear) {
                    if (currYear <= goalStartingYear) {
                        try {
                            if (limits.getJSONObject(i).getInt("taxable") == 0) {
                                limits.getJSONObject(i).put("taxable", 0);
                            } else {

                                limits.getJSONObject(i).put("taxable", (limits.getJSONObject(i).getInt("taxable")
                                        - ((goalendingyear - goalStartingYear + 1) * expense)));
                            }

                        } catch (final JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    } else if (currYear <= goalendingyear) {
                        limits.getJSONObject(i).put("taxable", limits.getJSONObject(i).getInt("taxable")
                                - expense * (goalendingyear - limits.getJSONObject(i).getInt("year") + 1));
                        if (limits.getJSONObject(i).getDouble("taxable") < 0) {
                            limits.getJSONObject(i).put("taxable", 0);
                        }
                    }
                    /*
                     * else if(currentYear==goalStartingYear){
                     *
                     * limits.getJSONObject(i).put("taxable",
                     * limits.getJSONObject(i).getInt("taxable")-expense*(
                     * Goalendingyear-limits.getJSONObject(i).getInt("year")));
                     * if(limits.getJSONObject(i).getDouble("taxable")<0)
                     * {limits.getJSONObject(i).put("taxable", 0);}
                     *
                     * }
                     */

                    currYear++;
                    i++;
                }

            }

        } catch (final JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return limits;
    }

    public JSONArray savingLimitsRevertBackForCustomizedGoal(final String userId, final String goalname,
            final String typeOfGoal, final int goalStartingYear, final int currentYr, final int goalendingyear,
            final JSONArray userExpense, final double amountSave, final JSONArray limits) {
        double expense = 0.0;
        int currYear = currentYr;
        try {
            if (typeOfGoal.equals("total")) {
                int i = 0;
                final long numberyars = goalendingyear - goalStartingYear + 1;
                expense = (amountSave / numberyars);
                final double mytemp = (amountSave / numberyars);
                while (currYear <= goalendingyear) {
                    if (currYear < goalStartingYear) {

                        if (limits.getJSONObject(i).getInt("saving") == 0) {

                            limits.getJSONObject(i).put("saving", 0);

                        } else {

                            limits.getJSONObject(i).put("saving", (limits.getJSONObject(i).getInt("saving")
                                    - (((goalendingyear - goalStartingYear + 1) * expense))));
                        }
                    } else if (currYear == goalStartingYear) {
                        if (limits.getJSONObject(i).getInt("year") == currYear) {
                            limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving")
                                    - expense * (goalendingyear - limits.getJSONObject(i).getInt("year")) - mytemp);
                            if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                limits.getJSONObject(i).put("saving", 0);
                            }
                        }

                    } else if (goalStartingYear <= goalendingyear) {
                        if (limits.getJSONObject(i).getInt("year") >= goalStartingYear
                                && limits.getJSONObject(i).getInt("year") <= goalendingyear) {
                            limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving")
                                    - expense * (goalendingyear - limits.getJSONObject(i).getInt("year")) - mytemp);
                            if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                limits.getJSONObject(i).put("saving", 0);
                            }
                        }

                    }

                    currYear++;
                    i++;

                }
            } else {
                expense = amountSave;
                int i = 0;
                while (currYear <= goalendingyear) {
                    if (currYear < goalStartingYear) {
                        try {
                            if (limits.getJSONObject(i).getInt("saving") == 0) {
                                limits.getJSONObject(i).put("saving", 0);
                            } else {

                                limits.getJSONObject(i).put("saving", (limits.getJSONObject(i).getInt("saving")
                                        - ((goalendingyear - goalStartingYear + 1) * expense)));
                            }

                        } catch (final JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    } else if (currYear == goalStartingYear) {

                        limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving")
                                - expense * (goalendingyear - limits.getJSONObject(i).getInt("year")));
                        if (limits.getJSONObject(i).getDouble("saving") < 0) {
                            limits.getJSONObject(i).put("saving", 0);
                        }

                    } else if (goalStartingYear <= goalendingyear) {
                        if (limits.getJSONObject(i).getInt("year") >= goalStartingYear
                                && limits.getJSONObject(i).getInt("year") <= goalendingyear) {
                            limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving")
                                    - expense * (goalendingyear - limits.getJSONObject(i).getInt("year")));
                            if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                limits.getJSONObject(i).put("saving", 0);
                            }
                        }
                    }

                    currYear++;
                    i++;
                }

            }

        } catch (final JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("reverts bac-->" + limits);
        return limits;
    }

    public JSONArray deductionsRevertBackForCustomizedGoal(final String userId, final String goalname,
            final int goalStartingYear, final int currentYr, final double amountSv, final JSONArray deductions,
            final int goalendingyear, final String goalType) {
        try {
            int i = 0;
            int currYear = currentYr;
            double amountSave = amountSv;
            if (goalType.equals("total")) {
                amountSave = amountSave / (goalendingyear - goalStartingYear + 1);
            }
            final int tempCurrentYear = currYear;
            while (currYear <= goalendingyear) {
                if (goalStartingYear > (tempCurrentYear + FOUR)) {
                    if (deductions.getJSONObject(i).getInt("year") >= goalStartingYear) {
                        deductions.getJSONObject(i).put("taxable",
                                deductions.getJSONObject(i).getDouble("taxable") - amountSave);

                    }
                } else {
                    if (deductions.getJSONObject(i).getInt("year") >= goalStartingYear) {
                        deductions.getJSONObject(i).put("saving",
                                deductions.getJSONObject(i).getDouble("saving") - amountSave);

                        if (deductions.getJSONObject(i).getDouble("saving") < 0) {
                            deductions.getJSONObject(i).put("saving", 0);
                        }
                    }

                }
                currYear++;
                i++;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return deductions;
    }

    public JSONArray savingLimitsforCostomizedGoal(final String typeOfGoal, final int goalStartingYear, final int currentYr,
            final int goalendingyear, final JSONArray userExpense, final double amountSave, final JSONArray limits) {
        int currYear = currentYr;
        if (typeOfGoal.equals("total")) {
            double mytempval = 0.0;
            final double numberyars = goalendingyear - goalStartingYear;
            double mytemp = (amountSave / numberyars);
            int i = 0;
            while (currYear <= goalendingyear) {

                if (currYear < goalStartingYear) {
                    try {
                        if (limits.getJSONObject(i).getInt("saving") == 0) {
                            limits.getJSONObject(i).put("saving",
                                    (((goalendingyear - goalStartingYear) * amountSave) / numberyars));
                        } else {
                            limits.getJSONObject(i).put("saving", (limits.getJSONObject(i).getInt("saving")
                                    + ((goalendingyear - goalStartingYear) * amountSave) / numberyars));
                        }

                    } catch (final JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } else if (currYear == goalStartingYear) {

                    try {
                        if (limits.getJSONObject(i).getInt("saving") == 0) {
                            limits.getJSONObject(i).put("saving",
                                    ((((goalendingyear - goalStartingYear) * amountSave) / numberyars) - mytemp));
                        } else {
                            limits.getJSONObject(i).put("saving",
                                    (limits.getJSONObject(i).getInt("saving")
                                            + (((goalendingyear - goalStartingYear) * amountSave) / numberyars)
                                            - mytemp));
                        }

                    } catch (final JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    mytempval = mytemp;
                    mytemp = mytemp + mytemp;
                } else if (goalStartingYear <= goalendingyear) {
                    double amount = ((((goalendingyear - goalStartingYear) * amountSave) / numberyars) - mytemp);
                    mytemp = mytemp + mytempval;
                    if (amount <= 0) {
                        amount = 0;
                    }

                    try {
                        if (limits.getJSONObject(i).getInt("saving") == 0) {
                            limits.getJSONObject(i).put("saving", amount);
                        } else {
                            limits.getJSONObject(i).put("saving", (limits.getJSONObject(i).getInt("saving") + amount));
                        }

                    } catch (final JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

                currYear++;
                i++;
            }

        } else {
            double mytemp = amountSave;
            int i = 0;
            while (currYear <= goalendingyear) {

                if (currYear < goalStartingYear) {
                    try {
                        if (limits.getJSONObject(i).getInt("saving") == 0) {
                            limits.getJSONObject(i).put("saving", (goalendingyear - goalStartingYear) * amountSave);
                        } else {
                            limits.getJSONObject(i).put("saving", (limits.getJSONObject(i).getInt("saving")
                                    + (goalendingyear - goalStartingYear) * amountSave));
                        }

                    } catch (final JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } else if (currYear == goalStartingYear) {

                    try {
                        if (limits.getJSONObject(i).getInt("saving") == 0) {
                            limits.getJSONObject(i).put("saving",
                                    (((goalendingyear - goalStartingYear) * amountSave) - mytemp));
                        } else {
                            limits.getJSONObject(i).put("saving", (limits.getJSONObject(i).getInt("saving")
                                    + ((goalendingyear - goalStartingYear) * amountSave) - mytemp));
                        }

                    } catch (final JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    mytemp = mytemp + amountSave;

                } else if (goalStartingYear <= goalendingyear) {
                    double amount = (((goalendingyear - goalStartingYear) * amountSave) - mytemp);
                    mytemp = mytemp + amountSave;
                    if (amount <= 0) {
                        amount = 0;
                    }

                    try {
                        if (limits.getJSONObject(i).getInt("saving") == 0) {
                            limits.getJSONObject(i).put("saving", amount);
                        } else {
                            limits.getJSONObject(i).put("saving", (limits.getJSONObject(i).getInt("saving") + amount));
                        }

                    } catch (final JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

                currYear++;
                i++;
            }

        }
        System.out.println("limits revert--->>" + limits);
        return limits;
    }

    public JSONArray taxableLimitsforCostomizedGoal(final String typeOfGoal, final int goalStartingYear,
            final int currentYr, final int goalendingyear, final JSONArray userExpense, final double amountSave,
            final JSONArray limits) {
        int currYear = currentYr;
        if (typeOfGoal.equals("total")) {
            final double numberyars = goalendingyear - goalStartingYear;
            double mytemp = (amountSave / numberyars);
            final double mytempval = mytemp;
            int i = 0;
            while (currYear <= goalendingyear) {

                if (currYear <= goalStartingYear) {
                    try {
                        if (limits.getJSONObject(i).getInt("taxable") == 0) {
                            limits.getJSONObject(i).put("taxable",
                                    (((goalendingyear - goalStartingYear) * amountSave) / numberyars));
                        } else {
                            limits.getJSONObject(i).put("taxable", (limits.getJSONObject(i).getInt("taxable")
                                    + ((goalendingyear - goalStartingYear) * amountSave) / numberyars));
                        }

                    } catch (final JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } else if (goalStartingYear <= goalendingyear) {
                    double amount = ((((goalendingyear - goalStartingYear) * amountSave) / numberyars) - mytemp);
                    mytemp = mytemp + mytempval;
                    if (amount <= 0) {
                        amount = 0;
                    }

                    try {
                        if (limits.getJSONObject(i).getInt("taxable") == 0) {
                            limits.getJSONObject(i).put("taxable", amount);
                        } else {
                            limits.getJSONObject(i).put("taxable",
                                    (limits.getJSONObject(i).getInt("taxable") + amount));
                        }

                    } catch (final JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

                /*
                 * else if(currentYear==goalStartingYear){
                 *
                 * try { if(limits.getJSONObject(i).getInt("taxable")==0){
                 * limits.getJSONObject(i).put("taxable",(((Goalendingyear-
                 * goalStartingYear)*amountSave)/numberyars)); }else{
                 * limits.getJSONObject(i).put("taxable",(limits.getJSONObject(i
                 * ).getInt("taxable")+((Goalendingyear-goalStartingYear)*
                 * amountSave)/numberyars)); }
                 *
                 * } catch (JSONException e) { // TODO Auto-generated catch
                 * block e.printStackTrace(); }
                 *
                 * mytempval=mytemp; mytemp=mytemp+mytemp; }
                 */

                currYear++;
                i++;
            }

        } else {
            double mytemp = amountSave;
            int i = 0;
            while (currYear <= goalendingyear) {

                if (currYear <= goalStartingYear) {
                    try {
                        if (limits.getJSONObject(i).getInt("taxable") == 0) {
                            limits.getJSONObject(i).put("taxable", (goalendingyear - goalStartingYear) * amountSave);
                        } else {
                            limits.getJSONObject(i).put("taxable", (limits.getJSONObject(i).getInt("taxable")
                                    + (goalendingyear - goalStartingYear) * amountSave));
                        }

                    } catch (final JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } else if (goalStartingYear <= goalendingyear) {
                    double amount = (((goalendingyear - goalStartingYear) * amountSave) - mytemp);
                    mytemp = mytemp + amountSave;
                    if (amount <= 0) {
                        amount = 0;
                    }

                    try {
                        if (limits.getJSONObject(i).getInt("taxable") == 0) {
                            limits.getJSONObject(i).put("taxable", amount);
                        } else {
                            limits.getJSONObject(i).put("taxable",
                                    (limits.getJSONObject(i).getInt("taxable") + amount));
                        }

                    } catch (final JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

                /*
                 * else if(currentYear==goalStartingYear){
                 *
                 * try { if(limits.getJSONObject(i).getInt("taxable")==0){
                 * limits.getJSONObject(i).put("taxable",(Goalendingyear-
                 * goalStartingYear)*amountSave); }else{
                 * limits.getJSONObject(i).put("taxable",(limits.getJSONObject(i
                 * ).getInt("taxable")+(Goalendingyear-goalStartingYear)*
                 * amountSave)); }
                 *
                 * } catch (JSONException e) { // TODO Auto-generated catch
                 * block e.printStackTrace(); }
                 *
                 * mytemp=mytemp+amountSave;
                 *
                 * }
                 */

                currYear++;
                i++;
            }

        }
        return limits;
    }

    public JSONArray limitsforCostomizedGoal(final String typeOfGoal, final int goalStartingYear, final int currentYr,
            final int goalendingyear, final JSONArray userExpense, final double amountSave, final JSONArray lmts) {
        final double numberyars = goalStartingYear - currentYr;
        JSONArray limits = new JSONArray();
        limits = lmts;
        if (numberyars >= FIVE) {
            limits = taxableLimitsforCostomizedGoal(typeOfGoal, goalStartingYear, currentYr, goalendingyear,
                    userExpense, amountSave, limits);
        } else {
            limits = savingLimitsforCostomizedGoal(typeOfGoal, goalStartingYear, currentYr, goalendingyear, userExpense,
                    amountSave, limits);
        }
        return limits;
    }
}