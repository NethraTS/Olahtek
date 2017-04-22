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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongorest.olahtek.model.Counters;
import com.mongorest.olahtek.model.Emergencyfundmodel;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.User;

@Service("emergencyGoalimpl")
@Transactional
public class Emergencyfundserimpl {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private final Date date = new Date();
    private final DecimalFormat decimalFloat = new DecimalFormat("#.##");
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private static final int RETAIRMENTAGE = 70;

    @JsonCreator
    public JSONObject insertEmergencyGoalData(final Emergencyfundmodel emergencyFundMode1) {
        JSONObject responseToRestController = new JSONObject();
        String goalId;
        final String userId = emergencyFundMode1.getUser_id();
        final String planName = emergencyFundMode1.getPlan_name();
        try {
            responseToRestController.put("status", "fail");
            final String actionHomeType = emergencyFundMode1.getActionHomeType();
            if (actionHomeType.equals("insert")) {
                final String timePeriod = emergencyFundMode1.getTimePeriod();
                final long amountSave = emergencyFundMode1.getAmountSave();
                final FinPlan getfinplan = MongoDBConnection.finplancol
                        .findOne("{usr_id:#,plan_name:#}", userId, planName).as(FinPlan.class);
                final String finId = getfinplan.get_id();
                responseToRestController = insertionIntoEmergencyGoal(finId, timePeriod, amountSave,
                        emergencyFundMode1);
            } else {
                goalId = emergencyFundMode1.getGoal_id();
                final String timePeriod = emergencyFundMode1.getTimePeriod();
                final long amountSave = emergencyFundMode1.getAmountSave();
                final Emergencyfundmodel cargoalfinID = MongoDBConnection.goalcoll.findOne("{_id:#}", goalId)
                        .as(Emergencyfundmodel.class);
                final String finId = cargoalfinID.getFin_id();
                responseToRestController = updateEmergencyGoal(finId, goalId, actionHomeType, cargoalfinID, timePeriod,
                        amountSave, emergencyFundMode1);
            }
            return responseToRestController;
        } catch (final Exception e) {
            e.printStackTrace();
            return responseToRestController;
        }
    }

    private JSONObject insertionIntoEmergencyGoal(final String finId, final String timePeriod, final long amountSave,
            final Emergencyfundmodel emergencyFundMode1) throws JSONException {
        final JSONObject responseToRestController = new JSONObject();
        final String userId = emergencyFundMode1.getUser_id();
        final User user = MongoDBConnection.userColl.findOne("{'_id':#}", userId).as(User.class);
        final JSONObject systemLog = new JSONObject();
        final int year = Calendar.getInstance().get(Calendar.YEAR);
        try {
            systemLog.put("type", "success");
            systemLog.put("message", "Goal Created Successfully!!");
            systemLog.put("userName", user.getName());
            systemLog.put("user_id", userId);
            systemLog.put("createdTs", dateFormat.format(date));
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
            JSONArray expenseObj = new JSONArray();
            JSONObject expense = new JSONObject();
            expense = finPlanJson.getJSONObject("expense");
            if (!expense.isNull("housing_expense")) {
                expenseObj = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
            }
            final String finPlanProfileName = finDetails.getProfile_name();
            final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                    .findOne("{user_id:#,profile_name:#}", userId, finPlanProfileName).as(IncomeProfile.class);
            final JSONObject incomeProfileJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
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
            final long emergencyFundAmt = amountSave;
            final boolean emergencyFundFlag = true;

            // System.out.println("user_expense"+userExpense);
            final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                    .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement").as(RetirementGoal.class);
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
            final String emergencyType = emergencyFundMode1.getTimePeriod();
            final String monthsOfIncome = emergencyFundMode1.getMonthI();
            final String monthsOfExpense = emergencyFundMode1.getMonthE();
            final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                    spouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, userId, fillingExemtion,
                    userAge, spouseAge, (int) emergencyFundAmt, emergencyFundFlag, deductions, kidBirthYear,
                    retirementData, retirementObj, expenseObj, emergencyType, monthsOfIncome, monthsOfExpense,
                    finDetails.isRetirementFlag());
            final String status = result.getString("status");
            if (status.equals("success")) {

                final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                final String goalId = "goal" + count.getGoal_id();
                assets = result.getJSONArray("assets");
                tax = result.getJSONArray("tax");
                updateFinplanForEmergencyGoal(finId, userId, goalId, timePeriod, amountSave, year,
                        Integer.parseInt(emergencyFundMode1.getMonthI()),
                        Integer.parseInt(emergencyFundMode1.getMonthE()), "insert");
                MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'emergencyFundFlag':" + true
                                + ",'emergencyFundAmt':" + (int) amountSave + "}}");
                final BasicDBObject doc = new BasicDBObject("_id", goalId)
                        .append("user_id", emergencyFundMode1.getUser_id()).append("fin_id", finId)
                        .append("plan_name", emergencyFundMode1.getPlan_name())
                        .append("goalType", emergencyFundMode1.getGoalType())
                        .append("timePeriod", emergencyFundMode1.getTimePeriod())
                        .append("amountSave", emergencyFundMode1.getAmountSave())
                        .append("monthI", emergencyFundMode1.getMonthI())
                        .append("monthE", emergencyFundMode1.getMonthE()).append("created_ts", dateFormat.format(date))
                        .append("completedStatus", 1).append("modified_ts", dateFormat.format(date));
                responseToRestController.put("timePeriod", emergencyFundMode1.getTimePeriod());
                responseToRestController.put("amountSave", emergencyFundMode1.getAmountSave());
                responseToRestController.put("monthI", emergencyFundMode1.getMonthI());
                responseToRestController.put("monthE", emergencyFundMode1.getMonthE());
                MongoDBConnection.goalcoll.insert(doc);
                MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                        .with("{$inc: {goal_id: 1}}");
                responseToRestController.put("status", "success");
                responseToRestController.put("goal_id", goalId);
                responseToRestController.put("timePeriod", emergencyFundMode1.getTimePeriod());
                responseToRestController.put("amountSave", emergencyFundMode1.getAmountSave());
                responseToRestController.put("monthI", emergencyFundMode1.getMonthI());
                responseToRestController.put("monthE", emergencyFundMode1.getMonthE());
            } else {
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

    public double calemi(final int year, final int timePeriod, final long amountSave) {
        final double emi = amountSave / (timePeriod * 12);
        return emi;
    }

    // -------------------------------------update emergency
    private JSONObject updateEmergencyGoal(final String finId, final String goalId, final String actionHomeType,
            final Emergencyfundmodel cargoalfinID, final String timePeriod, final long amountSave,
            final Emergencyfundmodel emergencyFundMode1) throws JSONException, JsonProcessingException {
        final int year = Calendar.getInstance().get(Calendar.YEAR);
        final String userId = emergencyFundMode1.getUser_id();
        final String planName = emergencyFundMode1.getPlan_name();
        final JSONObject systemLog = new JSONObject();
        final JSONObject responseToRestController = new JSONObject();

        try {
            final FinPlan finDetails = MongoDBConnection.finplancol.findOne("{_id:#}", finId).as(FinPlan.class);
            final User details = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
            final int userAge = details.getAge();
            System.out.println("plan id"+finId);
            final String maritalStatus = finDetails.getMarital_status();
            final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
            JSONArray assets = finPlanJson.getJSONArray("assests");
            JSONArray tax = finPlanJson.getJSONArray("tax");
            final JSONArray limits = finPlanJson.getJSONArray("limits");
            final JSONArray deductions = finPlanJson.getJSONArray("deductions");
            final JSONArray fillingExemtion = finPlanJson.getJSONArray("fillingExemtion");
            final JSONArray userExpense = finPlanJson.getJSONArray("userExpense");
            final JSONArray kidBirthYear = finPlanJson.getJSONArray("kidBirthYear");
            JSONArray expenseObj = new JSONArray();
            JSONObject expense = new JSONObject();
            expense = finPlanJson.getJSONObject("expense");
            if (!expense.isNull("housing_expense")) {
                expenseObj = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
            }
            final String finPlanProfileName = finDetails.getProfile_name();
            final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                    .findOne("{user_id:#,profile_name:#}", userId, finPlanProfileName).as(IncomeProfile.class);
            final JSONObject incomeProfileJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
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
            final long emergencyFundAmt = amountSave;
            final boolean emergencyFundFlag = true;

            final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                    .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement").as(RetirementGoal.class);
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
            final String emergencyType = emergencyFundMode1.getTimePeriod();
            final String monthsOfIncome = emergencyFundMode1.getMonthI();
            final String monthsOfExpense = emergencyFundMode1.getMonthE();
            final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                    spouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, userId, fillingExemtion,
                    userAge, spouseAge, (int) emergencyFundAmt, emergencyFundFlag, deductions, kidBirthYear,
                    retirementData, retirementObj, expenseObj, emergencyType, monthsOfIncome, monthsOfExpense,
                    finDetails.isRetirementFlag());

            final String status = result.getString("status");
            if (status.equals("success")) {

                assets = result.getJSONArray("assets");
                tax = result.getJSONArray("tax");

                updateFinplanForEmergencyGoal(finId, userId, goalId, timePeriod, amountSave, year,
                        Integer.parseInt(emergencyFundMode1.getMonthI()),
                        Integer.parseInt(emergencyFundMode1.getMonthI()), "insert");
                MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'emergencyFundFlag':" + true
                                + ",'emergencyFundAmt':" + (int) amountSave + "}}");
                MongoDBConnection.goalcoll.update("{'_id': '" + goalId + "'}").upsert().multi()
                        .with("{$set: {'timePeriod':'" + emergencyFundMode1.getTimePeriod() + "','amountSave':"
                                + emergencyFundMode1.getAmountSave() + ",'monthI':'" + emergencyFundMode1.getMonthI()
                                + "','monthE':'" + emergencyFundMode1.getMonthE() + "','modified_ts':'"
                                + dateFormat.format(date) + "'}}");
                responseToRestController.put("timePeriod", emergencyFundMode1.getTimePeriod());
                responseToRestController.put("amountSave", emergencyFundMode1.getAmountSave());
                responseToRestController.put("monthI", emergencyFundMode1.getMonthI());
                responseToRestController.put("monthE", emergencyFundMode1.getMonthE());

                responseToRestController.put("status", "success");
                responseToRestController.put("plan_name", planName);
                responseToRestController.put("goal_id", goalId);
                responseToRestController.put("timePeriod", emergencyFundMode1.getTimePeriod());
                responseToRestController.put("amountSave", emergencyFundMode1.getAmountSave());
                systemLog.put("type", "success");
                systemLog.put("message", "Goal updated sucessfully");

                systemLog.put("user_id", userId);
                systemLog.put("createdTs", dateFormat.format(date));
            } else {
                responseToRestController.put("status", "fail");

            }
        } catch (final Exception e) {
            systemLog.put("type", "fail");
            systemLog.put("message", "Goal updating failed");

            systemLog.put("createdTs", dateFormat.format(date));
            e.printStackTrace();
        }
        return responseToRestController;
    }

    // ------------------------------------- update finplan collection
    // goal-----------------------------
    public JSONObject updateFinplanForEmergencyGoal(final String finId, final String userId, final String goalId,
            final String timePeriod, final long amountSave, final int year, final int monthI, final int monthE,
            final String actionType) throws JSONException {
        final JSONObject responseToinsertupdateHousegoal = new JSONObject();
        responseToinsertupdateHousegoal.put("status", "fail");
        final JSONObject emergencyExpensedata = new JSONObject();
        try {
            emergencyExpensedata.put("amountSave", amountSave);
            emergencyExpensedata.put("type", timePeriod);
            emergencyExpensedata.put("startYear", year);
            if (timePeriod.equals("Expense")) {
                emergencyExpensedata.put("timePeriod", monthE);
            } else if (timePeriod.equals("Income")) {
                emergencyExpensedata.put("timePeriod", monthI);
            } else {
                emergencyExpensedata.put("timePeriod", 0);
            }
            if (actionType.equals("insert")) {
                MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$addToSet: {'goals':'" + goalId + "'}}");
            }
            MongoDBConnection.finplancol.update("{'_id': '" + finId + "'}").upsert().multi()
                    .with("{$set: {'expense.emergency_expense':" + emergencyExpensedata + "}}");
            responseToinsertupdateHousegoal.put("status", "success");
        } catch (final JSONException e) {
            e.printStackTrace();
        }
        return responseToinsertupdateHousegoal;
    }

    public JSONObject onLoadEmergencyFund(final Emergencyfundmodel emergencyFundMode1)
            throws JSONException, JsonProcessingException {
        final ObjectMapper jsonMapperValue = new ObjectMapper();
        final JSONObject resposneToController = new JSONObject();
        resposneToController.put("status", "fail");
        final String userId = emergencyFundMode1.getUser_id();
        if (userId == null) {
            return resposneToController;
        } else {
            final IncomeProfile getIncomeAndExpence = MongoDBConnection.incomeProfileCol.findOne("{user_id:#}", userId)
                    .as(IncomeProfile.class);
            final User getExpenses = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
            final JSONObject income = new JSONObject(jsonMapperValue.writeValueAsString(getIncomeAndExpence));
            final int year = Calendar.getInstance().get(Calendar.YEAR);
            final JSONArray incomeArray = income.getJSONArray("user_income");
            double yearlyIncome = 0;
            for (int i = 0; i < incomeArray.length(); i++) {
                if (incomeArray.getJSONObject(i).getInt("year") == year) {
                    yearlyIncome = incomeArray.getJSONObject(i).getLong("value");
                    break;
                }
            }
            final double monthlyIncome = yearlyIncome / 12;
            final double monthlyExpenses = getExpenses.getMonthlyExpenses();
            final double rentalExpenses = getExpenses.getRentalExpenses();
            final double taxableInvestments = getExpenses.getTaxableInvestments();
            double nonTaxableInvestments = 0;
            if (getExpenses.getNonTaxableInvestments().equals("Yes")) {
                nonTaxableInvestments = getExpenses.getUser401k() + getExpenses.getUserIRA() + getExpenses.getUser529()
                        + getExpenses.getUserRothIra() + getExpenses.getSpouse401k() + getExpenses.getSpouseIRA()
                        + getExpenses.getSpousePlan529() + getExpenses.getSpouseRothIra();
            }
            final double sixMonthOfIncome = 6 * monthlyIncome;
            final double twelveMonthOfExpense = 12 * (monthlyExpenses + rentalExpenses);
            final double liquidAsset = getExpenses.getCash();
            final double taxDeduction = (monthlyIncome - monthlyExpenses) * 12 * 0.034;
            final double annualExcess = ((monthlyIncome - monthlyExpenses) * 12) - taxDeduction;
            resposneToController.put("status", "success");
            resposneToController.put("income", monthlyIncome);
            resposneToController.put("monthlyExpenses", monthlyExpenses);
            resposneToController.put("sixMonthOfIncome", sixMonthOfIncome);
            resposneToController.put("twelveMonthOfExpense", twelveMonthOfExpense);
            resposneToController.put("liquid_asset", liquidAsset);
            resposneToController.put("taxableInvestments", taxableInvestments);
            resposneToController.put("nonTaxableInvestments", nonTaxableInvestments);
            resposneToController.put("annualExcess", annualExcess);
        }
        return resposneToController;
    }

    // -------------------------------------edit/fetching the car
    // goal-----------------------------
    public JSONObject editMarriageGoalData(final Emergencyfundmodel emergencyFundMode1) {
        JSONObject responseToRestController = new JSONObject();
        final String userId = emergencyFundMode1.getUser_id();
        final String goalId = emergencyFundMode1.getGoal_id();
        final ObjectMapper jsonMapperVal = new ObjectMapper();
        try {
            final Emergencyfundmodel marriageGoalModeldata = MongoDBConnection.goalcoll.findOne("{_id:#}", goalId)
                    .as(Emergencyfundmodel.class);
            final IncomeProfile getIncomeAndExpence = MongoDBConnection.incomeProfileCol.findOne("{user_id:#}", userId)
                    .as(IncomeProfile.class);
            final User getExpenses = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
            final JSONObject income = new JSONObject(jsonMapperVal.writeValueAsString(getIncomeAndExpence));
            final double monthlyIncome = income.getJSONArray("user_income").getJSONObject(0).getDouble("value");
            final double monthlyExpenses = getExpenses.getMonthlyExpenses();
            final double liquidAsset = getExpenses.getCash();
            final double taxableInvestments = getExpenses.getTaxableInvestments();
            double nonTaxableInvestments = 0;
            if (getExpenses.getNonTaxableInvestments().equals("Yes")) {
                nonTaxableInvestments = getExpenses.getUser401k() + getExpenses.getUserIRA() + getExpenses.getUser529()
                        + getExpenses.getUserRothIra() + getExpenses.getSpouse401k() + getExpenses.getSpouseIRA()
                        + getExpenses.getSpousePlan529() + getExpenses.getSpouseRothIra();
            }
            final double taxDeduction = (monthlyIncome - monthlyExpenses) * 12 * 0.034;
            final double annualExcess = ((monthlyIncome - monthlyExpenses) * 12) - taxDeduction;
            // System.out.println("on load emergency fund =" +
            // marriageGoalModeldata.getAmountSave());
            responseToRestController = calculationForGoalAcomplishment(liquidAsset, taxableInvestments,
                    nonTaxableInvestments, annualExcess, marriageGoalModeldata.getAmountSave(), emergencyFundMode1);
            responseToRestController.put("status", "success");
            responseToRestController.put("timePeriod", marriageGoalModeldata.getTimePeriod());
            responseToRestController.put("amountSave", marriageGoalModeldata.getAmountSave());
            responseToRestController.put("liquid_asset", liquidAsset);
            responseToRestController.put("monthI", marriageGoalModeldata.getMonthI());
            responseToRestController.put("monthE", marriageGoalModeldata.getMonthE());
            responseToRestController.put("taxableInvestments", taxableInvestments);
            responseToRestController.put("nonTaxableInvestments", nonTaxableInvestments);
            responseToRestController.put("annualExcess", annualExcess);
            responseToRestController.put("plan_name", marriageGoalModeldata.getPlan_name());
            responseToRestController.put("monthlyIncome", monthlyIncome);
            responseToRestController.put("monthlyExpenses", monthlyExpenses);
            return responseToRestController;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return responseToRestController;
    }

    JSONObject calculationForGoalAcomplishment(final double liquidAsset, final double taxableInvestments,
            final double nonTaxableInvestments, final double annualExcess, final long amountSave,
            final Emergencyfundmodel emergencyFundMode1) {
        final JSONObject responseToRestController = new JSONObject();
        String messresult;

        try {

            if (amountSave <= liquidAsset) {

                messresult = "Congratulations! You already have accomplished this goal with your current savings.";
                responseToRestController.put("messresult", messresult);
                responseToRestController.put("status", "success");
            } else if (amountSave <= (liquidAsset + taxableInvestments)) {

                messresult = "Congratulations! You already have accomplished this goal with your current "
                        + "savings and taxable investments. However, if you want to exclude your investments from your emergency fund, "
                        + "we can calculate for you how much and how long you need to save for this goal.";
                responseToRestController.put("messresult", messresult);
                responseToRestController.put("status", "success");
            } else if (amountSave <= (liquidAsset + nonTaxableInvestments + taxableInvestments)) {

                messresult = "Your total assets exceed your Emergency Fund goal. However, "
                        + "we recommend not including your non-taxable investments in this calculation.";
                responseToRestController.put("messresult", messresult);
                responseToRestController.put("status", "success");
            } else if (amountSave > (liquidAsset + taxableInvestments)) {

                final double value = (amountSave - (liquidAsset + taxableInvestments));
                final String timey = decimalFloat.format((value / annualExcess) * 100);
                messresult = "You are currently $ " + value
                        + " behind in reaching your goal however, if you start saving now, you can reach your goal in "
                        + timey + " years.";
                responseToRestController.put("messresult", messresult);
                responseToRestController.put("status", "success");
            } else {
                messresult = "In last condition ";
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return responseToRestController;
    }

    public JSONObject deleteEmergencyGoalData(final Emergencyfundmodel emergencyFundMode1) {
        final JSONObject responseToRestController = new JSONObject();
        try {

            final FinPlan finDetails = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}",
                    emergencyFundMode1.getUser_id(), emergencyFundMode1.getPlan_name()).as(FinPlan.class);
            final String finId = finDetails.get_id();
            final User details = MongoDBConnection.userColl.findOne("{_id:#}", emergencyFundMode1.getUser_id())
                    .as(User.class);
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
            JSONArray expenseObj = new JSONArray();
            JSONObject expense = new JSONObject();
            expense = finPlanJson.getJSONObject("expense");
            if (!expense.isNull("housing_expense")) {
                expenseObj = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
            }
            final String finPlanProfileName = finDetails.getProfile_name();
            final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                    .findOne("{user_id:#,profile_name:#}", emergencyFundMode1.getUser_id(), finPlanProfileName)
                    .as(IncomeProfile.class);
            final JSONObject incomeProfileJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
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
            final long emergencyFundAmt = 0;
            final boolean emergencyFundFlag = false;

            final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                    .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement").as(RetirementGoal.class);
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
            final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                    spouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, emergencyFundMode1.getUser_id(),
                    fillingExemtion, userAge, spouseAge, (int) emergencyFundAmt, emergencyFundFlag, deductions,
                    kidBirthYear, retirementData, retirementObj, expenseObj, null, null, null,
                    finDetails.isRetirementFlag());

            final String status = result.getString("status");
            if (status.equals("success")) {
                assets = result.getJSONArray("assets");
                tax = result.getJSONArray("tax");
                MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'emergencyFundFlag':" + false
                                + ",'emergencyFundAmt':" + (int) emergencyFundAmt + "}}");
                MongoDBConnection.goalcoll.remove("{_id:#}", emergencyFundMode1.getGoal_id());

                final JSONArray goalJsonArray = finPlanJson.getJSONArray("goals");
                final JSONArray goalsArray = new JSONArray();
                for (int j = 0; j < goalJsonArray.length(); j++) {
                    final String goalId = (String) goalJsonArray.get(j);
                    if (!goalId.equals(emergencyFundMode1.getGoal_id())) {
                        goalsArray.put(goalId);
                    } else {
                        continue;
                    }
                }
                // System.out.println("before fin plan update");
                MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'goals':" + goalsArray + "}}");
                MongoDBConnection.finplancol.update("{'_id': '" + finId + "'}").upsert().multi()
                        .with("{$set: {'expense.emergency_expense':" + null + "}}");
                responseToRestController.put("status", "success");
            } else {
                responseToRestController.put("status", "fail");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return responseToRestController;
    }
}
