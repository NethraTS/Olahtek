package com.mongorest.olahtek.service;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongorest.olahtek.model.CollegeeducationExpense;
import com.mongorest.olahtek.model.ConstantsValues;
import com.mongorest.olahtek.model.Counters;
import com.mongorest.olahtek.model.Emergencyfundmodel;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.GoalCollegeEducation;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.KidConstants;
import com.mongorest.olahtek.model.KidGoalModel;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.User;

@Service("kidGoalImpl")
@Transactional
public class KidGoalImpl {
    // private static final JSONObject details = null;
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private final Date date = new Date();
    private final DecimalFormat decimalFloat = new DecimalFormat("#.##");
    private static final int DEFAULT_RETIRMENT_AGE = 70;
    private static final int TWELVE = 12;
    private static final int EIGHTEEN = 18;

    @JsonCreator
    public JSONObject insertKidGoalData(final KidGoalModel kidGoalModel) {
        final JSONObject responseToRestController = new JSONObject();
        String goal_id;
        final String userId = kidGoalModel.getUser_id();

        final String planName = kidGoalModel.getPlan_name();
        JSONArray kidExpense = new JSONArray();
        JSONArray collegeExpense = new JSONArray();

        try {
            responseToRestController.put("status", "fail");

            final String actionHomeType = kidGoalModel.getActionHomeType();
            System.out.println("actionHomeType123" + actionHomeType);
            if (actionHomeType.equals("onload")) {

                final KidGoalImpl kidGoalImplObj = new KidGoalImpl();
                // KidGoalImplObj.kidCost(null);

                System.out.println("BALARRR");

                // JSONObject constJson = new
                // JSONObject(jsonMapper.writeValueAsString(MongoDBConnection.ConstantDetails));
                final ConstantsValues constantDetails = MongoDBConnection.Constantvaluecoll
                        .findOne("{_id:#}", "constantValue1").as(ConstantsValues.class);
                if (kidGoalModel.getGoal_id() != null) {
                    final KidGoalModel goaldata = MongoDBConnection.goalcoll
                            .findOne("{_id:#}", kidGoalModel.getGoal_id()).as(KidGoalModel.class);
                    responseToRestController.put("goalCost", goaldata.getGoalCost());
                    responseToRestController.put("startYear", goaldata.getStartYear());
                    responseToRestController.put("endYear", goaldata.getEndYear());
                }

                responseToRestController.put("kidGoalDefaultCostPeryear",
                        constantDetails.getKidGoalDefaultCostPeryear());
                responseToRestController.put("status", "success");
                // System.out.println("responseToRestController==aparna"+responseToRestController);
                return responseToRestController;
            } else if (actionHomeType.equals("insert")) {
                final long goalCost = kidGoalModel.getGoalCost() * TWELVE;
                final int startYear = kidGoalModel.getStartYear();
                final int overrideFlag = kidGoalModel.getOverrideFlag();
                final int endYear = kidGoalModel.getStartYear() + EIGHTEEN;
                final FinPlan finDetails = MongoDBConnection.finplancol
                        .findOne("{usr_id:#,plan_name:#}", userId, planName).as(FinPlan.class);
                final String finId = finDetails.get_id();
                final User details = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
                // System.out.println("UUUser"+user_id);
                /*
                 * String state=Details.getState();
                 * System.out.println("STATE>>>>>>>>"+state);
                 */
                final int userAge = details.getAge();
                // System.out.println("Age>>>>>>>>"+userAge);
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
                JSONArray houseExpense = new JSONArray();
                expense = finPlanJson.getJSONObject("expense");
                if (!expense.isNull("housing_expense")) {
                    expenseObj = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
                    houseExpense = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");

                }
                final String finPlanProfileName = finDetails.getProfile_name();
                // System.out.println("houseExpense..in kid goal "+houseExpense
                // +"length "+houseExpense.length());
                int houseEndYear = 0;

                if (houseExpense != null) {
                    for (int i = 0; i < houseExpense.length(); i++) {
                        houseEndYear = houseExpense.getJSONObject(i).getInt("endYear");
                    }

                    //// System.out.println("Bala...houseEnd
                    //// year"+houseEndYear);

                }









                for (int i = 0; i < limits.length(); i++) {

                    if (fillingExemtion.getJSONObject(i).getInt("year") >= startYear
                            && fillingExemtion.getJSONObject(i).getInt("year") <= endYear) {
                        if (maritalStatus.equals("Yes")) {

                            fillingExemtion.getJSONObject(i).put("fillingStatus", "Married Filing Jointly");

                        } else {

                            fillingExemtion.getJSONObject(i).put("fillingStatus", "Head of Household");
                        }

                        fillingExemtion.getJSONObject(i).put("oldFilingStatus",
                                fillingExemtion.getJSONObject(i).get("fillingStatus"));
                        fillingExemtion.getJSONObject(i).put("noOfExcemtion",
                                fillingExemtion.getJSONObject(i).getInt("noOfExcemtion") + 1);
                        userExpense.getJSONObject(i).put("kidExpense",
                                userExpense.getJSONObject(i).getInt("kidExpense") + goalCost);
                        if (userExpense.getJSONObject(i).getInt("mortgageExpense") > 0) {
                            userExpense.getJSONObject(i).put("totalExpense",
                                    userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                    + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                    + userExpense.getJSONObject(i).getInt("kidExpense"));
                        } else {
                            if (houseEndYear == 0) {

                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("housingExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));

                            } else if (userExpense.getJSONObject(i).getInt("year") <= houseEndYear
                                    && houseEndYear != 0) {
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("housingExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));
                            } else {
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));

                            }

                        }
                    }
                }

                //// System.out.println("fillingExemtion.."+fillingExemtion);
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

                JSONArray kidChanged=new JSONArray();
                int kidcount = details.getChildCount() + kidBirthYear.length() + 1;
                int registerKidCount = details.getChildCount();
                if(kidBirthYear.length()>0)
                {
               int [] kidBirthYearTemp= new int[kidBirthYear.length()];
               for (int i=0;i<kidBirthYear.length();i++) {
                   kidBirthYearTemp[i]=kidBirthYear.getInt(i);
               }
               Arrays.sort(kidBirthYearTemp);

               JSONArray goalsTemp= finPlanJson.getJSONArray("goals");

               for(int j=0;j<kidBirthYearTemp.length;j++) {

               for(int i=0;i<goalsTemp.length();i++) {

               KidGoalModel kidDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", goalsTemp.get(i)).as(KidGoalModel.class);
               if(kidDetails.getGoalType().equals("Raising a kid")) {
                   System.out.println("Inside the reduction function");
                   System.out.println("after comparison of the kid start year "+kidDetails.getStartYear()+"###"+kidBirthYearTemp[j] );
               if(kidDetails.getStartYear()==kidBirthYearTemp[j]&&kidDetails.getOverrideFlag()==0){
                   JSONObject a= new JSONObject();
                   a.put("goalId", goalsTemp.get(i));

                   System.out.println("inside override ");
               if(kidDetails.getDependentCollageGoal()!=null) {
                  GoalCollegeEducation  collegeDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", kidDetails.getDependentCollageGoal()).as(GoalCollegeEducation.class);
                  int collegeStartYear = collegeDetails.getKidCollegeYear();
                  double kidCostCalculated = calculateCost(details.getCounty(), details.getState(), finDetails.getMarital_status(), ++registerKidCount, combinedIncome, 0, kidcount,kidDetails.getStartYear());
                  a.put("expense", kidCostCalculated);
                  for (int e=0;e<userExpense.length();e++) {
                     if(userExpense.getJSONObject(e).getInt("year")>=kidBirthYearTemp[j]&&userExpense.getJSONObject(e).getInt("year")<collegeStartYear) {
                         userExpense.getJSONObject(e).put("nonHousingExpense",
                                 userExpense.getJSONObject(e).getInt("nonHousingExpense")

                                 - kidDetails.getGoalCost());


                         userExpense.getJSONObject(e).put("totalExpense",
                                 userExpense.getJSONObject(e).getInt("totalExpense")

                                 - kidDetails.getGoalCost());

                         userExpense.getJSONObject(e).put("nonHousingExpense",
                                 userExpense.getJSONObject(e).getInt("nonHousingExpense")

                                 + kidCostCalculated);


                         userExpense.getJSONObject(e).put("totalExpense",
                                 userExpense.getJSONObject(e).getInt("totalExpense")

                                + kidCostCalculated);



                      }
                  }
               }
               else {
                   System.out.println("inside override ");
                   double kidCostCalculated = calculateCost(details.getCounty(), details.getState(), finDetails.getMarital_status(), ++registerKidCount, combinedIncome, 0, kidcount,kidDetails.getStartYear());
                   a.put("expense", kidCostCalculated);
                   for (int e=0;e<userExpense.length();e++) {
                       if(userExpense.getJSONObject(e).getInt("year")>=kidBirthYearTemp[j]&&userExpense.getJSONObject(e).getInt("year")<kidBirthYearTemp[j]+19) {

                           userExpense.getJSONObject(e).put("nonHousingExpense",
                                   userExpense.getJSONObject(e).getInt("nonHousingExpense")

                                   - kidDetails.getGoalCost());


                           userExpense.getJSONObject(e).put("totalExpense",
                                   userExpense.getJSONObject(e).getInt("totalExpense")

                                   - kidDetails.getGoalCost());

                           userExpense.getJSONObject(e).put("nonHousingExpense",
                                   userExpense.getJSONObject(e).getInt("nonHousingExpense")

                                   + kidCostCalculated);


                           userExpense.getJSONObject(e).put("totalExpense",
                                   userExpense.getJSONObject(e).getInt("totalExpense")

                                  + kidCostCalculated);




                        }
                    }
               }
               kidChanged.put(a);


               }
               }

                }
               }

                }




                kidBirthYear.put(startYear);
                final int emergencyFundAmt = finDetails.getEmergencyFundAmt();
                final boolean emergencyFundFlag = finDetails.isEmergencyFundFlag();

                //// System.out.println("user_expense"+userExpense);

                final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                        .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement").as(RetirementGoal.class);
                int spouseStartYear = 0;
                int userStartYear = 0;
                long retirementExpense = 0;
                if (retirementObj != null) {
                    userStartYear = details.getBirthYear() + retirementObj.getRetirementAge();
                    retirementExpense = retirementObj.getRetirement_expense();
                    if (finDetails.getMarital_status().equals("Yes") && details.getMarital_status().equals("No")) {
                        spouseStartYear = finDetails.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                    } else if (details.getMarital_status().equals("Yes")) {
                        spouseStartYear = details.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                    }
                } else {
                    userStartYear = details.getBirthYear() + DEFAULT_RETIRMENT_AGE;
                    if (finDetails.getMarital_status().equals("Yes") && details.getMarital_status().equals("No")) {
                        spouseStartYear = finDetails.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                    } else if (details.getMarital_status().equals("Yes")) {
                        spouseStartYear = details.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                    }
                }
                final JSONArray afterRetirementExpense = CalculationEngine.retirementExpenseArray(userExpense,
                        spouseStartYear, userStartYear, finDetails.getMarital_status(), retirementExpense);
                // System.out.println("afterRetirementExpense.."+afterRetirementExpense);
                final JSONObject retirementData = new JSONObject();
                retirementData.put("spouseStartYear", spouseStartYear);
                retirementData.put("userStartYear", userStartYear);
                String emergencyType = null;
                String monthsOfIncome = null;
                String monthsOfExpense = null;
                if (finDetails.isEmergencyFundFlag()) {
                    final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                            .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Emergency Fund")
                            .as(Emergencyfundmodel.class);
                    emergencyType = emergencyObj.getTimePeriod();
                    monthsOfIncome = emergencyObj.getMonthI();
                    monthsOfExpense = emergencyObj.getMonthE();
                }
                MongoDBConnection.finplancol.update("{'_id':'" + finId + "'}").upsert().multi()
                .with("{$inc: {'kidsGoalCount':1}}");

                final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                        spouseIncomeArray, afterRetirementExpense, limits, maritalStatus, assets, tax, userId,
                        fillingExemtion, userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions,
                        kidBirthYear, retirementData, retirementObj, expenseObj, emergencyType, monthsOfIncome,
                        monthsOfExpense, finDetails.isRetirementFlag());
                final String status = result.getString("status");
                if (status.equals("success")) {
                    kidExpense = null;
                    //// System.out.println("No Negative value success");
                    assets = result.getJSONArray("assets");
                    tax = result.getJSONArray("tax");
                    //// System.out.println("tax in marriage after
                    //// create..."+tax);




                    final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                    String goalname;
                    final int kidGoalCount = finDetails.getKidGoalCount() + 1;

                    goal_id = "goal" + count.getGoal_id();
                    goalname = kidGoalModel.getGoalType() + " - " + goal_id;
                    final BasicDBObject doc = new BasicDBObject("_id", goal_id).append("user_id", userId)
                            .append("fin_id", finId).append("plan_name", planName)
                            .append("goalType", kidGoalModel.getGoalType()).append("goalFeasibility", true)
                            .append("startYear", startYear).append("goalname", goalname)
                            .append("annualExpense", goalCost).append("goalCost", goalCost).append("endYear", endYear)
                            .append("created_ts", dateFormat.format(date)).append("completedStatus", 1)
                            .append("overrideFlag", overrideFlag).append("modified_ts", dateFormat.format(date));
                    doc.append("goalFeasibility", true);
                    MongoDBConnection.goalcoll.insert(doc);
                    MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                    .with("{$inc: {goal_id: 1}}");
                    MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                    .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits
                            + ",'deductions':" + deductions + ",'userExpense':" + afterRetirementExpense
                            + ",'fillingExemtion':" + fillingExemtion + ",'kidBirthYear':" + kidBirthYear
                            + "}}");
                    MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                    .with("{$addToSet: {'goals':'" + goal_id + "'}}");
                    final JSONObject kidExpensedata = new JSONObject();
                    final JSONArray expenses = new JSONArray();
                    kidExpensedata.put("goalname", goalname);
                    kidExpensedata.put("annualExpense", goalCost);
                    kidExpensedata.put("startYear", startYear);
                    kidExpensedata.put("endYear", startYear + EIGHTEEN);
                    expenses.put(kidExpensedata);
                    if (!finPlanJson.isNull("expense")) {
                        final JSONObject expenseInFinPlanCol = finPlanJson.getJSONObject("expense");
                        if (!expenseInFinPlanCol.isNull("kid_expense")) {
                            kidExpense = expenseInFinPlanCol.getJSONArray("kid_expense");
                            //// System.out.println("kidExpense --> inside kid
                            //// not eqal null "+kidExpense);
                        }
                    }

                    if (kidExpense == null) {
                        //// System.out.println("kid expance not null in kid
                        //// goal");
                        MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'expense.kid_expense':" + expenses + "}}");
                    } else {
                        //// System.out.println("kid expance not null eqal null
                        //// kid goal");
                        for (int j=0;j<kidChanged.length();j++) {
                            MongoDBConnection.goalcoll.update("{'_id':#}", kidChanged.getJSONObject(j).getString("goalId")).upsert()
                            .with("{$set: {'goalCost':" + kidChanged.getJSONObject(j).getDouble("expense") + ",'annualExpense':" + kidChanged.getJSONObject(j).getDouble("expense") + "}}");
                        for(int i=0;i<kidExpense.length();i++) {
                            if(kidExpense.getJSONObject(i).getString("goalname").contains(kidChanged.getJSONObject(j).getString("goalId"))) {
                                kidExpense.getJSONObject(i).put("annualExpense", kidChanged.getJSONObject(j).getDouble("expense"));
                            }

                        }
                        }
                        kidExpense.put(kidExpensedata);

                        MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'expense.kid_expense':" + kidExpense + "}}");
                    }
                    MongoDBConnection.finplancol.update("{'_id':'" + finId + "'}")
                    .with("{$set: {'kidGoalCount': " + kidGoalCount + "}}");
                    responseToRestController.put("status", "success");
                    responseToRestController.put("goal_id", goal_id);
                    // System.out.println("responseToRestController====="+responseToRestController);
                    final JSONObject systemLog = new JSONObject();
                    systemLog.put("message", "Goal Created Successfully!!");
                    systemLog.put("userName", details.getName());
                    systemLog.put("user_id", userId);
                    systemLog.put("createdTs", dateFormat.format(date));
                    final BasicDBObject systemLogBasicObject = new BasicDBObject("user_id", userId)
                            .append("userName", systemLog.getString("userName"))
                            .append("message", systemLog.getString("message"))
                            .append("createdTs", dateFormat.format(date));
                    MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);

                } else {
                    // MongoDBConnection.finplancol.update("{'_id':'" + fin_id +
                    // "'}").upsert().multi().with("{$dec:
                    // {'kidsGoalCount':1}}");

                    responseToRestController.put("status", "fail");
                }

            } else if (actionHomeType.equals("deleteGoal")) {
                // -----------------------------delete of the marriage
                // goal-----------------------------------------------------------------
                //// System.out.println("Deleting the goal.....");
                goal_id = kidGoalModel.getGoal_id();
                final KidGoalModel goaldata = MongoDBConnection.goalcoll.findOne("{_id:#}", goal_id)
                        .as(KidGoalModel.class);
                final User details = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
                final int userAge = details.getAge();
                final long oldGoalCost = goaldata.getGoalCost();
                final int oldStartYear = goaldata.getStartYear();
                final int oldEndYear = goaldata.getEndYear();
                final String dependentCollegeGoal = goaldata.getDependentCollageGoal();
                final String goalname = goaldata.getGoalname();
                final String finId = goaldata.getFin_id();
                final FinPlan finDetails = MongoDBConnection.finplancol.findOne("{_id:#}", finId).as(FinPlan.class);
                final String maritalStatus = finDetails.getMarital_status();
                final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
                JSONArray assets = finPlanJson.getJSONArray("assests");
                JSONArray tax = finPlanJson.getJSONArray("tax");
                JSONArray limits = finPlanJson.getJSONArray("limits");
                final JSONArray deductions = finPlanJson.getJSONArray("deductions");
                final JSONArray fillingExemtion = finPlanJson.getJSONArray("fillingExemtion");
                final JSONArray userExpense = finPlanJson.getJSONArray("userExpense");
                JSONArray kidBirthYear = finPlanJson.getJSONArray("kidBirthYear");
                JSONArray expenseObj = new JSONArray();
                JSONObject expense = new JSONObject();
                expense = finPlanJson.getJSONObject("expense");
                JSONArray houseExpense = new JSONArray();
                if (!expense.isNull("housing_expense")) {
                    expenseObj = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
                    houseExpense = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
                }
                final String finPlanProfileName = finDetails.getProfile_name();

                int houseEndYear = 0;

                if (houseExpense != null) {
                    for (int i = 0; i < houseExpense.length(); i++) {
                        houseEndYear = houseExpense.getJSONObject(i).getInt("endYear");
                    }

                    //// System.out.println("Bala...houseEnd
                    //// year"+houseEndYear);

                }
                for (int i = 0; i < limits.length(); i++) {
                    if (fillingExemtion.getJSONObject(i).getInt("year") >= oldStartYear
                            && fillingExemtion.getJSONObject(i).getInt("year") <= oldEndYear) {
                        if (maritalStatus.equals("Yes")) {
                            fillingExemtion.getJSONObject(i).put("fillingStatus", "Married Filing Jointly");
                        }
                        fillingExemtion.getJSONObject(i).put("oldFilingStatus",
                                fillingExemtion.getJSONObject(i).get("fillingStatus"));
                        fillingExemtion.getJSONObject(i).put("noOfExcemtion",
                                fillingExemtion.getJSONObject(i).getInt("noOfExcemtion") - 1);
                        userExpense.getJSONObject(i).put("kidExpense",
                                userExpense.getJSONObject(i).getInt("kidExpense") - oldGoalCost);
                        if (userExpense.getJSONObject(i).getInt("kidExpense") < 0) {
                            userExpense.getJSONObject(i).put("kidExpense", 0);
                        }

                        if (userExpense.getJSONObject(i).getInt("mortgageExpense") > 0) {
                            userExpense.getJSONObject(i).put("totalExpense",
                                    userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                    + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                    + userExpense.getJSONObject(i).getInt("kidExpense"));
                        } else {
                            if (houseEndYear == 0) {
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("housingExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));
                            } else if (userExpense.getJSONObject(i).getInt("year") <= houseEndYear
                                    && houseEndYear != 0) {
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("housingExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));
                            } else {
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));
                            }
                        }
                    }
                    if (fillingExemtion.getJSONObject(i).getString("fillingStatus").equals("Head of Household")
                            && fillingExemtion.getJSONObject(i).getInt("noOfExcemtion") <= 1) {
                        fillingExemtion.getJSONObject(i).put("fillingStatus", "Single");
                    }
                }
                if (dependentCollegeGoal != null) {
                    final GoalCollegeEducation goal = MongoDBConnection.goalcoll
                            .findOne("{_id:#}", dependentCollegeGoal).as(GoalCollegeEducation.class);
                    kidExpense = new JSONArray();
                    final FinPlan getfinplan = MongoDBConnection.finplancol
                            .findOne("{usr_id:#,plan_name:#}", goal.getUser_id(), goal.getPlan_name())
                            .as(FinPlan.class);
                    final JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(getfinplan));
                    JSONArray collegeArray = null;
                    final CollegeeducationExpense goalTypeInDb = MongoDBConnection.goalcoll
                            .findOne("{_id:#}", dependentCollegeGoal).as(CollegeeducationExpense.class);
                    final JSONObject expenseJson = userJson.getJSONObject("expense");
                    if (!expenseJson.isNull("college_expense")) {
                        collegeArray = expenseJson.getJSONArray("college_expense");
                    }
                    collegeExpense = new JSONArray();
                    for (int i = 0; i < collegeArray.length(); i++) {
                        if (collegeArray.getJSONObject(i).getString("goalname").equals(goalTypeInDb.getGoalname())) {
                            continue;
                        } else {
                            collegeExpense.put(collegeArray.getJSONObject(i));
                        }
                    }
                    for (int j = 0; j < fillingExemtion.length(); j++) {
                        if (fillingExemtion.getJSONObject(j).getInt("year") > goal
                                .getStartYearForExpenseAndExceptionChange()
                                && fillingExemtion.getJSONObject(j).getInt("year") <= goal
                                .getEndYearForExpenseAndExceptionChange()) {
                            final int noOfexcepmtion = fillingExemtion.getJSONObject(j).getInt("noOfExcemtion") - 1;
                            fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
                        }
                        if (fillingExemtion.getJSONObject(j).getInt("year") >= goal.getKidCollegeYear()
                                && fillingExemtion.getJSONObject(j).getInt("year") <= goaldata.getEndYear()) {
                            userExpense.getJSONObject(j).put("kidExpense",
                                    userExpense.getJSONObject(j).getInt("kidExpense") - oldGoalCost);
                            if (userExpense.getJSONObject(j).getInt("kidExpense") < 0) {
                                userExpense.getJSONObject(j).put("kidExpense", 0);
                            }
                            if (userExpense.getJSONObject(j).getInt("mortgageExpense") > 0) {
                                userExpense.getJSONObject(j).put("totalExpense",
                                        userExpense.getJSONObject(j).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(j).getInt("mortgageExpense")
                                        + userExpense.getJSONObject(j).getInt("kidExpense"));
                            } else {
                                if (houseEndYear == 0) {
                                    userExpense.getJSONObject(j).put("totalExpense",
                                            userExpense.getJSONObject(j).getInt("nonHousingExpense")
                                            + userExpense.getJSONObject(j).getInt("housingExpense")
                                            + userExpense.getJSONObject(j).getInt("kidExpense"));
                                } else if (userExpense.getJSONObject(j).getInt("year") <= houseEndYear
                                        && houseEndYear != 0) {
                                    userExpense.getJSONObject(j).put("totalExpense",
                                            userExpense.getJSONObject(j).getInt("nonHousingExpense")
                                            + userExpense.getJSONObject(j).getInt("housingExpense")
                                            + userExpense.getJSONObject(j).getInt("kidExpense"));
                                } else {

                                    userExpense.getJSONObject(j).put("totalExpense",
                                            userExpense.getJSONObject(j).getInt("nonHousingExpense")
                                            + userExpense.getJSONObject(j).getInt("kidExpense"));
                                }
                            }
                        }
                        if (fillingExemtion.getJSONObject(j).getString("fillingStatus").equals("Head of Household")
                                && fillingExemtion.getJSONObject(j).getInt("noOfExcemtion") <= 1) {
                            fillingExemtion.getJSONObject(j).put("fillingStatus", "Single");
                        }
                    }
                    final double oldCollegeExpense = goal.getCollegeEducationAmount()
                            * goal.getCollegeEducationAmountPercentage() / 100;
                    final int collgeStartYear = goal.getKidCollegeYear();
                    if (goal.getPlan529Saved() == 0) {
                        int userAgeTemp = details.getAge();
                        int spouseAgeTemp = details.getSpouseAge();
                        double tempCost = (oldCollegeExpense * 20 / 100) * 4;
                        for (int i = 0; i < limits.length(); i++) {
                            if (limits.getJSONObject(i).getInt("year") < collgeStartYear) {
                                limits.getJSONObject(i).put("collegeGoalTaxable",
                                        limits.getJSONObject(i).getInt("collegeGoalTaxable")
                                        - (oldCollegeExpense * 20 / 100) * 4);
                            }
                            if (limits.getJSONObject(i).getInt("year") >= collgeStartYear
                                    && limits.getJSONObject(i).getInt("year") <= collgeStartYear + 3) {
                                limits.getJSONObject(i).put("collegeGoalTaxable",
                                        limits.getJSONObject(i).getInt("collegeGoalTaxable") - tempCost);
                                tempCost = tempCost - (oldCollegeExpense * 20 / 100);
                            }
                            if (limits.getJSONObject(i).getInt("year") >= collgeStartYear
                                    && limits.getJSONObject(i).getInt("year") <= collgeStartYear + 3) {
                                deductions.getJSONObject(i).put("collegeGoalTaxable",
                                        deductions.getJSONObject(i).getDouble("collegeGoalTaxable")
                                        - oldCollegeExpense * 20 / 100);
                                if (deductions.getJSONObject(i).getInt("taxable") < 0) {
                                    deductions.getJSONObject(i).put("taxable", 0);
                                }
                                final JSONObject collegeAmount = new JSONObject();
                                collegeAmount.put("currentAmount", 1).put("type", "delete").put("oldAmount",
                                        oldCollegeExpense);
                                final GoalCollegeEducationServiceImpl collegeGoalObj = new GoalCollegeEducationServiceImpl();
                                final JSONObject newLimits = collegeGoalObj.limiteAfterCollegeGoal(userAgeTemp, 0, 0,
                                        fillingExemtion.getJSONObject(i).getString("fillingStatus"), spouseAgeTemp,
                                        limits, 0, i, collegeAmount, deductions.getJSONObject(i));
                                limits = newLimits.getJSONArray("limits");
                                deductions.put(i, newLimits.getJSONObject("deductions"));
                            }
                            userAgeTemp++;
                            spouseAgeTemp++;
                        }
                    } else {
                        double tempCost = (oldCollegeExpense - (goal.getPlan529Saved() / 4)) * 4;
                        for (int i = 0; i < limits.length(); i++) {
                            if (limits.getJSONObject(i).getInt("year") < collgeStartYear) {
                                limits.getJSONObject(i).put("collegeGoalTaxable",
                                        limits.getJSONObject(i).getInt("collegeGoalTaxable")
                                        - (oldCollegeExpense - (goal.getPlan529Saved() / 4)) * 4);
                            }
                            if (limits.getJSONObject(i).getInt("year") >= collgeStartYear
                                    && limits.getJSONObject(i).getInt("year") <= collgeStartYear + 3) {
                                limits.getJSONObject(i).put("collegeGoalTaxable",
                                        limits.getJSONObject(i).getInt("collegeGoalTaxable") - tempCost);
                                tempCost = tempCost - (oldCollegeExpense - (goal.getPlan529Saved() / 4));
                            }
                            if (limits.getJSONObject(i).getInt("year") >= collgeStartYear
                                    && limits.getJSONObject(i).getInt("year") <= collgeStartYear + 3) {
                                deductions.getJSONObject(i).put("collegeGoalTaxable",
                                        deductions.getJSONObject(i).getDouble("collegeGoalTaxable")
                                        - (oldCollegeExpense - (goal.getPlan529Saved() / 4)));
                                if (deductions.getJSONObject(i).getInt("collegeGoalTaxable") < 0) {
                                    deductions.getJSONObject(i).put("collegeGoalTaxable", 0);
                                }
                                deductions.getJSONObject(i).put("collegeGoalNontaxable",
                                        deductions.getJSONObject(i).getDouble("collegeGoalNontaxable")
                                        - goal.getPlan529Saved() / 4);
                                if (deductions.getJSONObject(i).getInt("collegeGoalNontaxable") < 0) {
                                    deductions.getJSONObject(i).put("collegeGoalNontaxable", 0);
                                }
                            }
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
                JSONArray goalsArray = new JSONArray();
                for (int j = 0; j < kidBirthYear.length(); j++) {
                    final int goalId = kidBirthYear.getInt(j);
                    if (oldStartYear != (goalId)) {
                        goalsArray.put(goalId);
                    } else {
                        continue;
                    }
                }
                kidBirthYear = goalsArray;

                JSONArray kidChanged=new JSONArray();
                int kidcount = details.getChildCount() + kidBirthYear.length();
                int registerKidCount = details.getChildCount();
                if(kidBirthYear.length()>0)
                {
               int [] kidBirthYearTemp= new int[kidBirthYear.length()];
               for (int i=0;i<kidBirthYear.length();i++) {
                   kidBirthYearTemp[i]=kidBirthYear.getInt(i);
               }
               Arrays.sort(kidBirthYearTemp);

               JSONArray goalsTemp= finPlanJson.getJSONArray("goals");
               goalsArray = new JSONArray();
               for (int j = 0; j < goalsTemp.length(); j++) {
                   final String goalId = (String) goalsTemp.get(j);
                   if (!goalId.equals(goal_id)) {
                       goalsArray.put(goalId);
                   } else {
                       continue;
                   }
               }
               goalsTemp=goalsArray;
               for(int j=0;j<kidBirthYearTemp.length;j++) {

               for(int i=0;i<goalsTemp.length();i++) {

               KidGoalModel kidDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", goalsTemp.get(i)).as(KidGoalModel.class);
               if(kidDetails.getGoalType().equals("Raising a kid")) {
                   System.out.println("Inside the reduction function");
                   System.out.println("after comparison of the kid start year "+kidDetails.getStartYear()+"###"+kidBirthYearTemp[j] );
               if(kidDetails.getStartYear()==kidBirthYearTemp[j]&&kidDetails.getOverrideFlag()==0){
                   JSONObject a= new JSONObject();
                   a.put("goalId", goalsTemp.get(i));

                   System.out.println("inside override ");
               if(kidDetails.getDependentCollageGoal()!=null) {
                  GoalCollegeEducation  collegeDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", kidDetails.getDependentCollageGoal()).as(GoalCollegeEducation.class);
                  int collegeStartYear = collegeDetails.getKidCollegeYear();
                  double kidCostCalculated = calculateCost(details.getCounty(), details.getState(), finDetails.getMarital_status(), ++registerKidCount, combinedIncome, 0, kidcount,kidDetails.getStartYear());
                  a.put("expense", kidCostCalculated);
                  for (int e=0;e<userExpense.length();e++) {
                     if(userExpense.getJSONObject(e).getInt("year")>=kidBirthYearTemp[j]&&userExpense.getJSONObject(e).getInt("year")<collegeStartYear) {
                         userExpense.getJSONObject(e).put("nonHousingExpense",
                                 userExpense.getJSONObject(e).getInt("nonHousingExpense")

                                 - kidDetails.getGoalCost());


                         userExpense.getJSONObject(e).put("totalExpense",
                                 userExpense.getJSONObject(e).getInt("totalExpense")

                                 - kidDetails.getGoalCost());

                         userExpense.getJSONObject(e).put("nonHousingExpense",
                                 userExpense.getJSONObject(e).getInt("nonHousingExpense")

                                 + kidCostCalculated);


                         userExpense.getJSONObject(e).put("totalExpense",
                                 userExpense.getJSONObject(e).getInt("totalExpense")

                                + kidCostCalculated);



                      }
                  }
               }
               else {
                   System.out.println("inside override ");
                   double kidCostCalculated = calculateCost(details.getCounty(), details.getState(), finDetails.getMarital_status(), ++registerKidCount, combinedIncome, 0, kidcount,kidDetails.getStartYear());
                   a.put("expense", kidCostCalculated);
                   for (int e=0;e<userExpense.length();e++) {
                       if(userExpense.getJSONObject(e).getInt("year")>=kidBirthYearTemp[j]&&userExpense.getJSONObject(e).getInt("year")<kidBirthYearTemp[j]+19) {
                           userExpense.getJSONObject(e).put("nonHousingExpense",
                                   userExpense.getJSONObject(e).getInt("nonHousingExpense")

                                   - kidDetails.getGoalCost());


                           userExpense.getJSONObject(e).put("totalExpense",
                                   userExpense.getJSONObject(e).getInt("totalExpense")

                                   - kidDetails.getGoalCost());

                           userExpense.getJSONObject(e).put("nonHousingExpense",
                                   userExpense.getJSONObject(e).getInt("nonHousingExpense")

                                   + kidCostCalculated);


                           userExpense.getJSONObject(e).put("totalExpense",
                                   userExpense.getJSONObject(e).getInt("totalExpense")

                                  + kidCostCalculated);


                        }
                    }
               }
               kidChanged.put(a);


               }
               }

                }
               }

                }


                final int emergencyFundAmt = finDetails.getEmergencyFundAmt();
                final boolean emergencyFundFlag = finDetails.isEmergencyFundFlag();
                final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                        .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement").as(RetirementGoal.class);
                int spouseStartYear = 0;
                int userStartYear = 0;
                long retirementExpense = 0;
                if (retirementObj != null) {
                    userStartYear = details.getBirthYear() + retirementObj.getRetirementAge();
                    retirementExpense = retirementObj.getRetirement_expense();
                    if (finDetails.getMarital_status().equals("Yes") && details.getMarital_status().equals("No")) {
                        spouseStartYear = finDetails.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                    } else if (details.getMarital_status().equals("Yes")) {
                        spouseStartYear = details.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                    }
                } else {
                    userStartYear = details.getBirthYear() + DEFAULT_RETIRMENT_AGE;
                    if (finDetails.getMarital_status().equals("Yes") && details.getMarital_status().equals("No")) {
                        spouseStartYear = finDetails.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                    } else if (details.getMarital_status().equals("Yes")) {
                        spouseStartYear = details.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                    }
                }
                final JSONArray afterRetirementExpense = CalculationEngine.retirementExpenseArray(userExpense,
                        spouseStartYear, userStartYear, finDetails.getMarital_status(), retirementExpense);
                final JSONObject retirementData = new JSONObject();
                retirementData.put("spouseStartYear", spouseStartYear);
                retirementData.put("userStartYear", userStartYear);
                String emergencyType = null;
                String monthsOfIncome = null;
                String monthsOfExpense = null;
                if (finDetails.isEmergencyFundFlag()) {
                    final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                            .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Emergency Fund")
                            .as(Emergencyfundmodel.class);
                    emergencyType = emergencyObj.getTimePeriod();
                    monthsOfIncome = emergencyObj.getMonthI();
                    monthsOfExpense = emergencyObj.getMonthE();
                }
                final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                        spouseIncomeArray, afterRetirementExpense, limits, maritalStatus, assets, tax, userId,
                        fillingExemtion, userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions,
                        kidBirthYear, retirementData, retirementObj, expenseObj, emergencyType, monthsOfIncome,
                        monthsOfExpense, finDetails.isRetirementFlag());
                final String status = result.getString("status");
                if (status.equals("success")) {
                    //// System.out.println("No Negative value success");
                    assets = result.getJSONArray("assets");
                    tax = result.getJSONArray("tax");
                    JSONArray goalJsonArray = finPlanJson.getJSONArray("goals");
                    goalsArray = new JSONArray();
                    for (int j = 0; j < goalJsonArray.length(); j++) {
                        final String goalId = (String) goalJsonArray.get(j);
                        if (!goalId.equals(goal_id)) {
                            goalsArray.put(goalId);
                        } else {
                            continue;
                        }
                    }
                    MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                    .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits
                            + ",'deductions':" + deductions + ",'goals':" + goalsArray + ",'userExpense':"
                            + afterRetirementExpense + ",'fillingExemtion':" + fillingExemtion
                            + ",'kidBirthYear':" + kidBirthYear + "}}");
                    MongoDBConnection.goalcoll.remove("{_id:#}", goal_id);
                    System.out.println("the dependent collge goal###=="+dependentCollegeGoal);
                    if (dependentCollegeGoal != null) {
                        goalJsonArray = finPlanJson.getJSONArray("goals");
                        System.out.println("the goals array in finaplan###=="+goalJsonArray);
                        goalsArray = new JSONArray();
                        for (int j = 0; j < goalJsonArray.length(); j++) {
                            final String goalId = (String) goalJsonArray.get(j);
                            if (!goalId.equals(goal_id) && !goalId.equals(dependentCollegeGoal)) {
                                goalsArray.put(goalId);
                                System.out.println("inside");
                            } else {
                                System.out.println("outside.."+goalId);
                                continue;
                            }
                        }
                        System.out.println("goals array =="+goalsArray);
                        MongoDBConnection.goalcoll.remove("{_id:#}", dependentCollegeGoal);
                        MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'goals':" + goalsArray + "}}");
                        MongoDBConnection.finplancol.update("{'_id':'" + finId + "'}").upsert().multi()
                        .with("{$set: {'expense.kid_expense':" + kidExpense + "}}}");
                        MongoDBConnection.finplancol.update("{usr_id:#,plan_name:#}", userId, planName)
                        .with("{$set: {'expense.college_expense':" + collegeExpense + "}}");
                    }
                    else {
                        System.out.println("dependednt goal is null");
                        goalJsonArray = finPlanJson.getJSONArray("goals");
                        System.out.println("the goals array in finaplan###=="+goalJsonArray);
                        goalsArray = new JSONArray();
                        for (int j = 0; j < goalJsonArray.length(); j++) {
                            final String goalId = (String) goalJsonArray.get(j);
                            if (!goalId.equals(goal_id)) {
                                goalsArray.put(goalId);
                                System.out.println("inside");
                            } else {
                                System.out.println("outside.."+goalId);
                                continue;
                            }
                        }
                        System.out.println("goals array =="+goalsArray);

                        MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                        .with("{$set: {'goals':" + goalsArray + "}}");
                        MongoDBConnection.finplancol.update("{'_id':'" + finId + "'}").upsert().multi()
                        .with("{$set: {'expense.kid_expense':" + kidExpense + "}}}");

                    }
                    final JSONObject expenseJson = finPlanJson.getJSONObject("expense");
                    final JSONArray kidArray = expenseJson.getJSONArray("kid_expense");
                    kidExpense = new JSONArray();
                    for (int i = 0; i < kidArray.length(); i++) {
                        if (kidArray.getJSONObject(i).getString("goalname").equals(goalname)) {
                            System.out.println("goalname " + goalname + " matched");
                        } else {
                            kidExpense.put(kidArray.getJSONObject(i));
                        }
                    }

                    if(kidExpense!=null) {

                        for (int j=0;j<kidChanged.length();j++) {
                            MongoDBConnection.goalcoll.update("{'_id':#}", kidChanged.getJSONObject(j).getString("goalId")).upsert()
                            .with("{$set: {'goalCost':" + kidChanged.getJSONObject(j).getDouble("expense") + ",'annualExpense':" + kidChanged.getJSONObject(j).getDouble("expense") + "}}");
                        for(int i=0;i<kidExpense.length();i++) {
                            if(kidExpense.getJSONObject(i).getString("goalname").contains(kidChanged.getJSONObject(j).getString("goalId"))) {
                                kidExpense.getJSONObject(i).put("annualExpense", kidChanged.getJSONObject(j).getDouble("expense"));
                            }

                        }
                        }
                    }

                    final int kidsGoalCount = finDetails.getKidsGoalCount() - 1;
                    final int kidGoalCount = finDetails.getKidGoalCount() - 1;
                    MongoDBConnection.finplancol.update("{usr_id:#,plan_name:#}", userId, planName)
                    .with("{$set: {'expense.kid_expense':" + kidExpense + ",'kidGoalCount':" + kidGoalCount
                            + ",'kidsGoalCount':" + kidsGoalCount + "}}");
                    responseToRestController.put("status", "success");
                } else {
                    responseToRestController.put("status", "fail");
                }
            } else if (actionHomeType.equals("startyear")) {
                final KidGoalImpl kidGoalImplObj = new KidGoalImpl();
                kidGoalImplObj.calculateKidCost(kidGoalModel);
                System.out.println("HELLOO");
            } else {
                // --------------------------------update of the kid
                // goal-------------------------------------------------------------------
                goal_id = kidGoalModel.getGoal_id();
                final long newGoalCost = kidGoalModel.getGoalCost();
                final int newStartYear = kidGoalModel.getStartYear();
                final int newEndYear = kidGoalModel.getStartYear() + EIGHTEEN;
                final KidGoalModel goaldata = MongoDBConnection.goalcoll.findOne("{_id:#}", goal_id)
                        .as(KidGoalModel.class);
                final User details = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
                final int userAge = details.getAge();
                final long oldGoalCost = goaldata.getGoalCost();
                final int oldStartYear = goaldata.getStartYear();
                final int oldEndYear = goaldata.getEndYear();
                final String goalname = goaldata.getGoalname();
                final String finId = goaldata.getFin_id();
                final FinPlan finDetails = MongoDBConnection.finplancol.findOne("{_id:#}", finId).as(FinPlan.class);
                final String maritalStatus = finDetails.getMarital_status();
                final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
                JSONArray assets = finPlanJson.getJSONArray("assests");
                JSONArray tax = finPlanJson.getJSONArray("tax");
                final JSONArray limits = finPlanJson.getJSONArray("limits");
                final JSONArray deductions = finPlanJson.getJSONArray("deductions");
                final JSONArray fillingExemtion = finPlanJson.getJSONArray("fillingExemtion");
                final JSONArray userExpense = finPlanJson.getJSONArray("userExpense");
                JSONArray kidBirthYear = finPlanJson.getJSONArray("kidBirthYear");
                JSONArray expenseObj = new JSONArray();
                JSONObject expense = new JSONObject();
                JSONArray houseExpense = new JSONArray();
                expense = finPlanJson.getJSONObject("expense");
                if (!expense.isNull("housing_expense")) {
                    expenseObj = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
                    houseExpense = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
                }
                final String finPlanProfileName = finDetails.getProfile_name();

                int houseEndYear = 0;

                if (houseExpense != null) {
                    for (int j = 0; j < houseExpense.length(); j++) {
                        houseEndYear = houseExpense.getJSONObject(j).getInt("endYear");
                    }

                    //// System.out.println("Bala...houseEnd
                    //// year"+houseEndYear);

                }
                for (int i = 0; i < limits.length(); i++) {
                    if (fillingExemtion.getJSONObject(i).getInt("year") >= oldStartYear
                            && fillingExemtion.getJSONObject(i).getInt("year") <= oldEndYear) {

                        fillingExemtion.getJSONObject(i).put("noOfExcemtion",
                                fillingExemtion.getJSONObject(i).getInt("noOfExcemtion") - 1);

                        if (maritalStatus.equals("Yes")) {
                            fillingExemtion.getJSONObject(i).put("fillingStatus", "Married Filing Jointly");
                        } else {
                            if(fillingExemtion.getJSONObject(i).getInt("noOfExcemtion")>1) {
                                fillingExemtion.getJSONObject(i).put("fillingStatus", "Head of Household");
                            }
                            else {
                            fillingExemtion.getJSONObject(i).put("fillingStatus", "Single");
                            }
                        }

                        fillingExemtion.getJSONObject(i).put("oldFilingStatus",
                                fillingExemtion.getJSONObject(i).get("fillingStatus"));

                        userExpense.getJSONObject(i).put("kidExpense",
                                userExpense.getJSONObject(i).getInt("kidExpense") - oldGoalCost);
                        if (userExpense.getJSONObject(i).getInt("kidExpense") < 0) {
                            userExpense.getJSONObject(i).put("kidExpense", 0);
                        }
                        if (userExpense.getJSONObject(i).getInt("mortgageExpense") > 0) {
                            userExpense.getJSONObject(i).put("totalExpense",
                                    userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                    + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                    + userExpense.getJSONObject(i).getInt("kidExpense"));
                        } else {
                            if (houseEndYear == 0) {
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("housingExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));
                            } else if (userExpense.getJSONObject(i).getInt("year") <= houseEndYear
                                    && houseEndYear != 0) {
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("housingExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));
                            } else {
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));
                            }
                        }
                    }
                }
                for (int i = 0; i < limits.length(); i++) {
                    if (fillingExemtion.getJSONObject(i).getInt("year") >= newStartYear
                            && fillingExemtion.getJSONObject(i).getInt("year") <= newEndYear) {
                        if (maritalStatus.equals("Yes")) {
                            fillingExemtion.getJSONObject(i).put("fillingStatus", "Married Filing Jointly");
                        } else {
                            fillingExemtion.getJSONObject(i).put("fillingStatus", "Head of Household");
                        }

                        fillingExemtion.getJSONObject(i).put("oldFilingStatus",
                                fillingExemtion.getJSONObject(i).get("fillingStatus"));
                        fillingExemtion.getJSONObject(i).put("noOfExcemtion",
                                fillingExemtion.getJSONObject(i).getInt("noOfExcemtion") + 1);
                        userExpense.getJSONObject(i).put("kidExpense",
                                userExpense.getJSONObject(i).getInt("kidExpense") + newGoalCost);
                        if (userExpense.getJSONObject(i).getInt("mortgageExpense") > 0) {
                            userExpense.getJSONObject(i).put("totalExpense",
                                    userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                    + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                    + userExpense.getJSONObject(i).getInt("kidExpense"));
                        } else {
                            if (houseEndYear == 0) {
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("housingExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));

                            } else if (userExpense.getJSONObject(i).getInt("year") <= houseEndYear
                                    && houseEndYear != 0) {
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("housingExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));
                            } else {
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));
                            }
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

                final JSONArray goalsArray = new JSONArray();
                for (int j = 0; j < kidBirthYear.length(); j++) {
                    final int goalId = kidBirthYear.getInt(j);
                    if (oldStartYear != (goalId)) {
                        goalsArray.put(goalId);
                    } else {
                        continue;
                    }
                }
                kidBirthYear = goalsArray;
                kidBirthYear.put(newStartYear);
                final int emergencyFundAmt = finDetails.getEmergencyFundAmt();
                final boolean emergencyFundFlag = finDetails.isEmergencyFundFlag();
                final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                        .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Retirement").as(RetirementGoal.class);
                int spouseStartYear = 0;
                int userStartYear = 0;
                long retirementExpense = 0;
                if (retirementObj != null) {
                    userStartYear = details.getBirthYear() + retirementObj.getRetirementAge();
                    retirementExpense = retirementObj.getRetirement_expense();
                    if (finDetails.getMarital_status().equals("Yes") && details.getMarital_status().equals("No")) {
                        spouseStartYear = finDetails.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                    } else if (details.getMarital_status().equals("Yes")) {
                        spouseStartYear = details.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                    }

                } else {
                    userStartYear = details.getBirthYear() + DEFAULT_RETIRMENT_AGE;
                    if (finDetails.getMarital_status().equals("Yes") && details.getMarital_status().equals("No")) {
                        spouseStartYear = finDetails.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                    } else if (details.getMarital_status().equals("Yes")) {
                        spouseStartYear = details.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                    }
                }
                final JSONArray afterRetirementExpense = CalculationEngine.retirementExpenseArray(userExpense,
                        spouseStartYear, userStartYear, finDetails.getMarital_status(), retirementExpense);
                final JSONObject retirementData = new JSONObject();
                retirementData.put("spouseStartYear", spouseStartYear);
                retirementData.put("userStartYear", userStartYear);
                String emergencyType = null;
                String monthsOfIncome = null;
                String monthsOfExpense = null;
                if (finDetails.isEmergencyFundFlag()) {
                    final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                            .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Emergency Fund")
                            .as(Emergencyfundmodel.class);
                    emergencyType = emergencyObj.getTimePeriod();
                    monthsOfIncome = emergencyObj.getMonthI();
                    monthsOfExpense = emergencyObj.getMonthE();
                }
                final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                        spouseIncomeArray, afterRetirementExpense, limits, maritalStatus, assets, tax, userId,
                        fillingExemtion, userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions,
                        kidBirthYear, retirementData, retirementObj, expenseObj, emergencyType, monthsOfIncome,
                        monthsOfExpense, finDetails.isRetirementFlag());
                final String status = result.getString("status");
                if (status.equals("success")) {
                    kidExpense = null;
                    assets = result.getJSONArray("assets");
                    tax = result.getJSONArray("tax");
                    MongoDBConnection.goalcoll.update("{'_id': '" + goal_id + "'}").upsert().multi()
                    .with("{$set: {'startYear':" + newStartYear + ",'goalCost':" + newGoalCost + ",'endYear':"
                            + newEndYear + ",'annualExpense':" + newGoalCost + ",'overrideFlag':" + 1 + ",'goalFeasibility':" + true
                            + ",'modified_ts':'" + dateFormat.format(date) + "'}}");
                    MongoDBConnection.finplancol.update("{'_id':#}", finId).upsert().multi()
                    .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits
                            + ",'deductions':" + deductions + ",'userExpense':" + afterRetirementExpense
                            + ",'fillingExemtion':" + fillingExemtion + ",'kidBirthYear':" + kidBirthYear
                            + "}}");
                    if (!finPlanJson.isNull("expense")) {
                        final JSONObject expenseInFinPlanCol = finPlanJson.getJSONObject("expense");
                        if (!expenseInFinPlanCol.isNull("kid_expense")) {
                            kidExpense = expenseInFinPlanCol.getJSONArray("kid_expense");
                            //// System.out.println("kidExpense --> inside kid
                            //// not eqal null "+kidExpense);
                        }

                    }
                    //// System.out.println("goal name ..."+goalname);
                    for (int i = 0; i < kidExpense.length(); i++) {
                        if (kidExpense.getJSONObject(i).getString("goalname").equals(goalname)) {
                            kidExpense.getJSONObject(i).put("endYear", newEndYear);
                            kidExpense.getJSONObject(i).put("startYear", newStartYear);
                            kidExpense.getJSONObject(i).put("annualExpense", newGoalCost);
                            break;
                        }
                    }
                    MongoDBConnection.finplancol.update("{'_id': '" + finId + "'}").upsert().multi()
                    .with("{$set: {'expense.kid_expense':" + kidExpense + "}}");
                    responseToRestController.put("goal_id", goal_id);
                    responseToRestController.put("startYear", newStartYear);
                    responseToRestController.put("goalCost", newGoalCost);
                    responseToRestController.put("endYear", newEndYear);
                    responseToRestController.put("annualExpense", newGoalCost);
                    responseToRestController.put("plan_name", planName);
                    responseToRestController.put("goal_name", goalname);
                    responseToRestController.put("status", "success");
                    final JSONObject systemLog = new JSONObject();
                    systemLog.put("message", "Goal updated Successfully!!");
                    systemLog.put("userName", details.getName());
                    systemLog.put("user_id", userId);
                    systemLog.put("createdTs", dateFormat.format(date));
                    final BasicDBObject systemLogBasicObject = new BasicDBObject("user_id", userId)
                            .append("userName", systemLog.getString("userName"))
                            .append("message", systemLog.getString("message"))
                            .append("createdTs", dateFormat.format(date));
                    MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);

                } else {
                    responseToRestController.put("status", "fail");
                }

            }

            return responseToRestController;
        } catch (final Exception e) {
            e.printStackTrace();
            return responseToRestController;
        }

    }

    // -------------------------------------fetching the kid
    // goal-------------------------------------

    public JSONObject editKidGoalData(final KidGoalModel kidGoalModel) {
        final JSONObject responseToRestController = new JSONObject();
        final String goal_id = kidGoalModel.getGoal_id();
        try {
            final KidGoalModel customizedGoalModeldata = MongoDBConnection.goalcoll.findOne("{_id:#}", goal_id)
                    .as(KidGoalModel.class);
            responseToRestController.put("status", "success");
            responseToRestController.put("startYear", customizedGoalModeldata.getStartYear());
            responseToRestController.put("goalCost", customizedGoalModeldata.getGoalCost());
            responseToRestController.put("endYear", customizedGoalModeldata.getStartYear() + EIGHTEEN);
            responseToRestController.put("annualExpense", customizedGoalModeldata.getAnnualExpense());
            responseToRestController.put("plan_name", customizedGoalModeldata.getPlan_name());
            responseToRestController.put("goal_id", customizedGoalModeldata.get_id());
            responseToRestController.put("goal_name", customizedGoalModeldata.getGoalname());
            responseToRestController.put("goalFeasibility", customizedGoalModeldata.getGoalFeasibility());

            return responseToRestController;
        } catch (final Exception e) {

            e.printStackTrace();
        }
        return responseToRestController;

    }

    // -------------------------------Kidcost
    // calculation----------------------------------//
    public JSONObject calculateKidCost(final KidGoalModel kidGoalModel) {
        final JSONObject responseToRestController = new JSONObject();
        final String userId = kidGoalModel.getUser_id();
        final int year = kidGoalModel.getStartYear();
        System.out.println("USer id " + userId + "and startyear" + year);
        try {
            final ObjectMapper jsonmapper = new ObjectMapper();
            final User details = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
            final String county = details.getCounty();
            final String states = details.getState();
            String maritalstatus = details.getMarital_status();
            final String planName = kidGoalModel.getPlan_name();

            final FinPlan finDetails = MongoDBConnection.finplancol.findOne("{plan_name:#,usr_id:#}", planName,userId)
                    .as(FinPlan.class);
            maritalstatus = finDetails.getMarital_status();
            final int kidcount = details.getChildCount() + finDetails.getKidGoalCount() + 1;

            int child = 0;

            if (kidcount >= 3) {
                child = 3;
            } else if (kidcount <= 2 && kidcount > 1) {
                child = 2;
            } else if (kidcount < 2) {
                child = 1;
            }
            double finalCostToreturn = 0.00;
            final String profileName = finDetails.getProfile_name();
            System.out.println("profile name==="+profileName+"user id "+userId);
            final IncomeProfile profileData = MongoDBConnection.incomeProfileCol
                    .findOne("{profile_name:#,user_id:#}", profileName, userId).as(IncomeProfile.class);
            final JSONObject profileDataJson = new JSONObject(jsonmapper.writeValueAsString(profileData));
            final int age = 0;
            final KidGoalImpl obj = new KidGoalImpl();
            if (maritalstatus.equals("Yes")) {
                final JSONArray userIncome = profileDataJson.getJSONArray("combined_income");

                finalCostToreturn = obj.calculateCost(county, states, maritalstatus, child, userIncome, age, kidcount, kidGoalModel.getStartYear());
            } else {
                final JSONArray userIncome = profileDataJson.getJSONArray("user_income");
                finalCostToreturn = obj.calculateCost(county, states, maritalstatus, child, userIncome, age, kidcount, kidGoalModel.getStartYear());
            }
            System.out.println("Total kids" + kidcount);

            /* System.out.println("countyName"+constant.getCountyName()); */

            System.out.println("County" + county);

            responseToRestController.put("finalCostToreturn", finalCostToreturn);
            responseToRestController.put("status", "success");
            return responseToRestController;
        } catch (final Exception e) {

            e.printStackTrace();
        }
        return responseToRestController;

    }

    public double calculateCost(final String county, final String states, final String maritalstatus, final int child,
            final JSONArray userIncome, final int age, final int totalKid, final int year) {

        final KidConstants rural = MongoDBConnection.kid_Rural_List_2017Coll.findOne("{countyName:#}", county)
                .as(KidConstants.class);
        final KidConstants inflationRateObj = MongoDBConnection.kid_InflationAdjustmentColl.findOne("{year:#}", 2013)
                .as(KidConstants.class);
        final double inflationRate = inflationRateObj.getInflationRate();
        double finalCostToreturn = 0.00;
        System.out.println("state name==" + states);

        try {

            int k=0;
            for(int j=0;j<userIncome.length();j++) {
                if(userIncome.getJSONObject(j).getInt("year")==year) {
                    k=j-1;
                    break;
                }

            }
            if (maritalstatus.equals("Yes")) {
                /*
                 * JSONArray
                 * user_income=profileDataJson.getJSONArray("combined_income");
                 */
                System.out.println("user_income123" + userIncome);

                if (rural != null) {
                    final KidConstants incomeLimits = MongoDBConnection.kidgoal_MarriedColl
                            .findOne("{type:#}", "income").as(KidConstants.class);
                    System.out.println("inflationRate" + inflationRate);
                    final double limit1 = incomeLimits.getIncome1() * inflationRate;
                    System.out.println("limit1:" + limit1);
                    final double limit2 = incomeLimits.getIncome2() * inflationRate;
                    System.out.println("limit2:" + limit2);
                    final double limit3 = incomeLimits.getIncome3() * inflationRate;
                    System.out.println("limit3:" + limit3);
                    final JSONArray costKid = new JSONArray();


                    for (int i = age; i < 19; i++) {
                        double finalKidcost = 0.00;
                        final double incomeValue = userIncome.getJSONObject(++k).getDouble("value");
                        final KidConstants costObj = MongoDBConnection.kidgoal_MarriedColl.findOne("{childAge:#}", i)
                                .as(KidConstants.class);
                        // KidConstants
                        // regionalFactorObj=MongoDBConnection.kid_RegionalAdjustmentFactorColl.findOne("{childAge:#}",
                        // i).as(KidConstants.class);
                        // double regionalFactor=regionalFactorObj.getRural();

                        double childAdjusFactor = 0.00;

                        if (totalKid == 1) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", "couple", child).as(KidConstants.class);
                            childAdjusFactor = childAdjusFactorObj.getFirst();
                        }
                        else if(totalKid == 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", "couple", totalKid).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                         }
                        else if (totalKid > 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", "couple", 3).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                            else {
                                childAdjusFactor = childAdjusFactorObj.getThird_and_younger();
                            }
                        }



                        /*if (child == 1) {
                            childAdjusFactor = childAdjusFactorObj.getFirst();
                        } else if (child == 2) {
                            childAdjusFactor = childAdjusFactorObj.getSecond();
                        } else if (child > 3) {
                            childAdjusFactor = childAdjusFactorObj.getThird_and_younger();
                        }*/
                        final int cost1 = costObj.getCost1();
                        final int cost2 = costObj.getCost2();
                        final int cost3 = costObj.getCost3();
                        if (incomeValue <= limit1) {
                            finalKidcost = cost1 * inflationRate;
                        } else if (incomeValue > limit1 && incomeValue <= limit2) {
                            finalKidcost = cost1 * inflationRate + (cost2 * inflationRate - cost1 * inflationRate)
                                    * (incomeValue - limit1) / (limit2 - limit1);
                        } else if (incomeValue > limit2 && incomeValue <= limit3) {
                            finalKidcost = cost2 * inflationRate + (cost3 * inflationRate - cost2 * inflationRate)
                                    * (incomeValue - limit2) / (limit3 - limit2);
                        } else if (incomeValue > limit3) {
                            finalKidcost = cost3 * inflationRate;

                        }
                        costKid.put(finalKidcost * childAdjusFactor);

                    }
                    double sum = 0;
                    for (int i = 0; i < costKid.length(); i++) {
                        sum = sum + costKid.getDouble(i);
                    }
                    finalCostToreturn = sum / costKid.length();
                    System.out.println("the fina cost of the kid" + finalCostToreturn);
                } else if (states.equals("CONNECTICUT") || states.equals("MAINE") || states.equals("MASSACHUSETTS")
                        || states.equals("NEW HAMPSHIRE") || states.equals("NEW JERSEY") || states.equals("NEW YORK")
                        || states.equals("PENNSYLVANIA") || states.equals("RHODE ISLAND") || states.equals("VERMONT")) {
                    System.out.println("Area:Northeast");

                    final KidConstants incomeLimits = MongoDBConnection.kid_Expense_NorthEastColl
                            .findOne("{type:#}", "income").as(KidConstants.class);
                    System.out.println("inflationRate" + inflationRate);
                    final double limit1 = incomeLimits.getIncome1() * inflationRate;
                    System.out.println("limit1:" + limit1);
                    final double limit2 = incomeLimits.getIncome2() * inflationRate;
                    System.out.println("limit2:" + limit2);
                    final double limit3 = incomeLimits.getIncome3() * inflationRate;
                    System.out.println("limit3:" + limit3);
                    final JSONArray costKid = new JSONArray();
                    for (int i = age; i < 19; i++) {
                        double finalKidcost = 0.00;
                        final double incomeValue = userIncome.getJSONObject(++k).getDouble("value");
                        final KidConstants costObj = MongoDBConnection.kid_Expense_NorthEastColl
                                .findOne("{childAge:#}", i).as(KidConstants.class);
                        // KidConstants
                        // regionalFactorObj=MongoDBConnection.kid_RegionalAdjustmentFactorColl.findOne("{childAge:#}",
                        // i).as(KidConstants.class);
                        // double
                        // regionalFactor=regionalFactorObj.getNorthEast();
                        final String type = "couple";
                        double childAdjusFactor = 0.00;

                        if (totalKid == 1) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, child).as(KidConstants.class);
                            childAdjusFactor = childAdjusFactorObj.getFirst();
                        }
                        else if(totalKid == 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, totalKid).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                         }
                        else if (totalKid > 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, 3).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                            else {
                                childAdjusFactor = childAdjusFactorObj.getThird_and_younger();
                            }
                        }
                        System.out.println("asdhjb%%%" + childAdjusFactor);
                        final int cost1 = costObj.getCost1();
                        final int cost2 = costObj.getCost2();
                        final int cost3 = costObj.getCost3();
                        if (incomeValue <= limit1) {
                            finalKidcost = cost1 * inflationRate;
                        } else if (incomeValue > limit1 && incomeValue <= limit2) {
                            finalKidcost = cost1 * inflationRate + (cost2 * inflationRate - cost1 * inflationRate)
                                    * (incomeValue - limit1) / (limit2 - limit1);
                        } else if (incomeValue > limit2 && incomeValue <= limit3) {
                            finalKidcost = cost2 * inflationRate + (cost3 * inflationRate - cost2 * inflationRate)
                                    * (incomeValue - limit2) / (limit3 - limit2);
                        } else if (incomeValue > limit3) {
                            finalKidcost = cost3 * inflationRate;

                        }
                        costKid.put(finalKidcost * childAdjusFactor);

                    }
                    double sum = 0;
                    for (int i = 0; i < costKid.length(); i++) {
                        sum = sum + costKid.getDouble(i);
                    }
                    finalCostToreturn = sum / costKid.length();
                    System.out.println("the fina cost of the kid" + finalCostToreturn);
                } else if (states.equals("ALASKA") || states.equals("ARIZONA") || states.equals("CALIFORNIA")
                        || states.equals("COLORADO") || states.equals("HAWAII") || states.equals("IDAHO")
                        || states.equals("MONTANA") || states.equals("NEVADA") || states.equals("NEW MEXICO")
                        || states.equals("OREGON") || states.equals("UTAH") || states.equals("WASHINGTON")
                        || states.equals("WYOMING")) {

                    System.out.println("Area:West");

                    final KidConstants incomeLimits = MongoDBConnection.kid_Expense_WestColl
                            .findOne("{type:#}", "income").as(KidConstants.class);
                    System.out.println("inflationRate" + inflationRate);
                    final double limit1 = incomeLimits.getIncome1() * inflationRate;
                    System.out.println("limit1:" + limit1);
                    final double limit2 = incomeLimits.getIncome2() * inflationRate;
                    System.out.println("limit2:" + limit2);
                    final double limit3 = incomeLimits.getIncome3() * inflationRate;
                    System.out.println("limit3:" + limit3);
                    final JSONArray costKid = new JSONArray();
                    for (int i = age; i < 19; i++) {
                        double finalKidcost = 0.00;
                        final double incomeValue = userIncome.getJSONObject(++k).getDouble("value");
                        final KidConstants costObj = MongoDBConnection.kid_Expense_WestColl.findOne("{childAge:#}", i)
                                .as(KidConstants.class);
                        // KidConstants
                        // regionalFactorObj=MongoDBConnection.kid_RegionalAdjustmentFactorColl.findOne("{childAge:#}",
                        // i).as(KidConstants.class);
                        // double regionalFactor=regionalFactorObj.getWest();
                        final String type = "couple";
                        double childAdjusFactor = 0.00;

                        if (totalKid == 1) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, child).as(KidConstants.class);
                            childAdjusFactor = childAdjusFactorObj.getFirst();
                        }
                        else if(totalKid == 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, totalKid).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                         }
                        else if (totalKid > 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, 3).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                            else {
                                childAdjusFactor = childAdjusFactorObj.getThird_and_younger();
                            }
                        }
                        System.out.println("asdhjb%%%" + childAdjusFactor);
                        final int cost1 = costObj.getCost1();
                        final int cost2 = costObj.getCost2();
                        final int cost3 = costObj.getCost3();
                        System.out.println("cost1 " + cost1);
                        System.out.println("cost2 " + cost2);
                        System.out.println("cost3 " + cost3);
                        if (incomeValue <= limit1) {
                            finalKidcost = cost1 * inflationRate;
                        } else if (incomeValue > limit1 && incomeValue <= limit2) {
                            finalKidcost = cost1 * inflationRate + (cost2 * inflationRate - cost1 * inflationRate)
                                    * (incomeValue - limit1) / (limit2 - limit1);
                        } else if (incomeValue > limit2 && incomeValue <= limit3) {
                            finalKidcost = cost2 * inflationRate + (cost3 * inflationRate - cost2 * inflationRate)
                                    * (incomeValue - limit2) / (limit3 - limit2);
                        } else if (incomeValue > limit3) {
                            finalKidcost = cost3 * inflationRate;

                        }
                        costKid.put(finalKidcost * childAdjusFactor);

                    }
                    double sum = 0;
                    for (int i = 0; i < costKid.length(); i++) {
                        System.out.println("the cost of the kid in age" + i + "is value" + costKid.getDouble(i));
                        sum = sum + costKid.getDouble(i);
                    }
                    finalCostToreturn = sum / costKid.length();
                    System.out.println("the fina cost of the kid" + finalCostToreturn);
                } else if (states.equals("ILLINOIS") || states.equals("INDIANA") || states.equals("IOWA")
                        || states.equals("KANSAS") || states.equals("MICHIGAN") || states.equals("MINNESOTA")
                        || states.equals("MISSOURI") || states.equals("NEBRASKA") || states.equals("NORTH DAKOTA")
                        || states.equals("OHIO") || states.equals("SOUTH DAKOTA") || states.equals("WISCONSIN")) {
                    System.out.println("Area:MidWest");
                    final KidConstants incomeLimits = MongoDBConnection.kid_Expense_MidWestColl
                            .findOne("{type:#}", "income").as(KidConstants.class);
                    System.out.println("inflationRate" + inflationRate);
                    final double limit1 = incomeLimits.getIncome1() * inflationRate;
                    System.out.println("limit1:" + limit1);
                    final double limit2 = incomeLimits.getIncome2() * inflationRate;
                    System.out.println("limit2:" + limit2);
                    final double limit3 = incomeLimits.getIncome3() * inflationRate;
                    System.out.println("limit3:" + limit3);
                    final JSONArray costKid = new JSONArray();
                    for (int i = age; i < 19; i++) {
                        double finalKidcost = 0.00;
                        final double incomeValue = userIncome.getJSONObject(++k).getDouble("value");
                        final KidConstants costObj = MongoDBConnection.kid_Expense_MidWestColl
                                .findOne("{childAge:#}", i).as(KidConstants.class);
                        // KidConstants
                        // regionalFactorObj=MongoDBConnection.kid_RegionalAdjustmentFactorColl.findOne("{childAge:#}",
                        // i).as(KidConstants.class);
                        // double regionalFactor=regionalFactorObj.getMidWest();
                        final String type = "couple";
                        double childAdjusFactor = 0.00;

                        if (totalKid == 1) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, child).as(KidConstants.class);
                            childAdjusFactor = childAdjusFactorObj.getFirst();
                        }
                        else if(totalKid == 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, totalKid).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                         }
                        else if (totalKid > 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, 3).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                            else {
                                childAdjusFactor = childAdjusFactorObj.getThird_and_younger();
                            }
                        }
                        System.out.println("asdhjb%%%" + childAdjusFactor);
                        final int cost1 = costObj.getCost1();
                        final int cost2 = costObj.getCost2();
                        final int cost3 = costObj.getCost3();
                        if (incomeValue <= limit1) {
                            finalKidcost = cost1 * inflationRate;
                        } else if (incomeValue > limit1 && incomeValue <= limit2) {
                            finalKidcost = cost1 * inflationRate + (cost2 * inflationRate - cost1 * inflationRate)
                                    * (incomeValue - limit1) / (limit2 - limit1);
                        } else if (incomeValue > limit2 && incomeValue <= limit3) {
                            finalKidcost = cost2 * inflationRate + (cost3 * inflationRate - cost2 * inflationRate)
                                    * (incomeValue - limit2) / (limit3 - limit2);
                        } else if (incomeValue > limit3) {
                            finalKidcost = cost3 * inflationRate;

                        }
                        costKid.put(finalKidcost * childAdjusFactor);

                    }
                    double sum = 0;
                    for (int i = 0; i < costKid.length(); i++) {
                        sum = sum + costKid.getDouble(i);
                    }
                    finalCostToreturn = sum / costKid.length();
                    System.out.println("the fina cost of the kidddddddd" + finalCostToreturn);

                } else if (states.equals("ALABAMA") || states.equals("ARKANSAS") || states.equals("DELAWARE")
                        || states.equals("WASHINGTON DC") || states.equals("FLORIDA") || states.equals("GEORGIA")
                        || states.equals("KENTUCKY") || states.equals("LOUISIANA") || states.equals("MARYLAND")
                        || states.equals("MISSISSIPPI") || states.equals("NORTH CAROLINA") || states.equals("OKLAHOMA")
                        || states.equals("SOUTH CAROLINA") || states.equals("TENNESSEE") || states.equals("TEXAS")
                        || states.equals("VIRGINIA") || states.equals("WEST VIRGINIA")) {
                    System.out.println("Area:South");

                    final KidConstants incomeLimits = MongoDBConnection.kid_Expense_SouthColl
                            .findOne("{type:#}", "income").as(KidConstants.class);
                    System.out.println("inflationRate" + inflationRate);
                    final double limit1 = incomeLimits.getIncome1() * inflationRate;
                    System.out.println("limit1:" + limit1);
                    final double limit2 = incomeLimits.getIncome2() * inflationRate;
                    System.out.println("limit2:" + limit2);
                    final double limit3 = incomeLimits.getIncome3() * inflationRate;
                    System.out.println("limit3:" + limit3);
                    final JSONArray costKid = new JSONArray();
                    for (int i = age; i < 19; i++) {
                        double finalKidcost = 0.00;
                        final double incomeValue = userIncome.getJSONObject(++k).getDouble("value");
                        final KidConstants costObj = MongoDBConnection.kid_Expense_SouthColl.findOne("{childAge:#}", i)
                                .as(KidConstants.class);
                        // KidConstants
                        // regionalFactorObj=MongoDBConnection.kid_RegionalAdjustmentFactorColl.findOne("{childAge:#}",
                        // i).as(KidConstants.class);
                        // double regionalFactor=regionalFactorObj.getSouth();
                        final String type = "couple";
                        double childAdjusFactor = 0.00;

                        if (totalKid == 1) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, child).as(KidConstants.class);
                            childAdjusFactor = childAdjusFactorObj.getFirst();
                        }
                        else if(totalKid == 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, totalKid).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                         }
                        else if (totalKid > 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, 3).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                            else {
                                childAdjusFactor = childAdjusFactorObj.getThird_and_younger();
                            }
                        }
                        System.out.println("asdhjb%%%" + childAdjusFactor);
                        final int cost1 = costObj.getCost1();
                        final int cost2 = costObj.getCost2();
                        final int cost3 = costObj.getCost3();
                        if (incomeValue <= limit1) {
                            finalKidcost = cost1 * inflationRate;
                        } else if (incomeValue > limit1 && incomeValue <= limit2) {
                            finalKidcost = cost1 * inflationRate + (cost2 * inflationRate - cost1 * inflationRate)
                                    * (incomeValue - limit1) / (limit2 - limit1);
                        } else if (incomeValue > limit2 && incomeValue <= limit3) {
                            finalKidcost = cost2 * inflationRate + (cost3 * inflationRate - cost2 * inflationRate)
                                    * (incomeValue - limit2) / (limit3 - limit2);
                        } else if (incomeValue > limit3) {
                            finalKidcost = cost3 * inflationRate;

                        }
                        costKid.put(finalKidcost * childAdjusFactor);

                    }
                    double sum = 0;
                    for (int i = 0; i < costKid.length(); i++) {
                        sum = sum + costKid.getDouble(i);
                    }
                    finalCostToreturn = sum / costKid.length();
                    System.out.println("the fina cost of the kidsouth" + finalCostToreturn);

                }

            } else { // ------------------------------------------Single------------------------------------//
                /*
                 * JSONArray
                 * user_income=profileDataJson.getJSONArray("user_income");
                 */
                System.out.println("the user income" + userIncome);
                if (rural != null) {

                    final KidConstants incomeLimits = MongoDBConnection.kidgoal_SingleColl.findOne("{type:#}", "income")
                            .as(KidConstants.class);
                    System.out.println("inflationRate" + inflationRate);
                    final double limit1 = incomeLimits.getIncome1() * inflationRate;
                    System.out.println("limit1:" + limit1);
                    final double limit2 = incomeLimits.getIncome2() * inflationRate;
                    System.out.println("limit2:" + limit2);
                    final double limit3 = incomeLimits.getIncome3() * inflationRate;
                    System.out.println("limit3:" + limit3);
                    final JSONArray costKid = new JSONArray();
                    for (int i = age; i < 19; i++) {
                        double finalKidcost = 0.00;
                        final double incomeValue = userIncome.getJSONObject(++k).getDouble("value");
                        final KidConstants costObj = MongoDBConnection.kidgoal_SingleColl.findOne("{childAge:#}", i)
                                .as(KidConstants.class);
                        final KidConstants regionalFactorObj = MongoDBConnection.kid_RegionalAdjustmentFactorColl
                                .findOne("{childAge:#}", i).as(KidConstants.class);
                        final double regionalFactor = regionalFactorObj.getRural();
                        final String type = "single";
                        double childAdjusFactor = 0.00;
                        if (totalKid == 1) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, child).as(KidConstants.class);
                            childAdjusFactor = childAdjusFactorObj.getFirst();
                        }
                        else if(totalKid == 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, totalKid).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                         }
                        else if (totalKid > 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, 3).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                            else {
                                childAdjusFactor = childAdjusFactorObj.getThird_and_younger();
                            }
                        }
                        System.out.println("asdhjb%%%" + childAdjusFactor);

                        final int cost1 = costObj.getCost1();
                        final int cost2 = costObj.getCost2();
                        final int cost3 = costObj.getCost3();
                        if (incomeValue <= limit1) {
                            finalKidcost = cost1 * inflationRate;
                        } else if (incomeValue > limit1 && incomeValue <= limit2) {
                            finalKidcost = cost1 * inflationRate + (cost2 * inflationRate - cost1 * inflationRate)
                                    * (incomeValue - limit1) / (limit2 - limit1);
                        } else if (incomeValue > limit2 && incomeValue <= limit3) {
                            finalKidcost = cost2 * inflationRate + (cost3 * inflationRate - cost2 * inflationRate)
                                    * (incomeValue - limit2) / (limit3 - limit2);
                        } else if (incomeValue > limit3) {
                            finalKidcost = cost3 * inflationRate;

                        }
                        costKid.put(finalKidcost * childAdjusFactor * regionalFactor);

                    }
                    double sum = 0;
                    for (int i = 0; i < costKid.length(); i++) {
                        sum = sum + costKid.getDouble(i);
                    }
                    finalCostToreturn = sum / costKid.length();
                    System.out.println("the fina cost of the kid single_RURAL" + finalCostToreturn);

                } else if (states.equals("CONNECTICUT") || states.equals("MAINE") || states.equals("MASSACHUSETTS")
                        || states.equals("NEW HAMPSHIRE") || states.equals("NEW JERSEY") || states.equals("NEW YORK")
                        || states.equals("PENNSYLVANIA") || states.equals("RHODE ISLAND") || states.equals("VERMONT")) {
                    System.out.println("Area:Northeast");

                    final KidConstants incomeLimits = MongoDBConnection.kid_Expense_NorthEastColl
                            .findOne("{type:#}", "income").as(KidConstants.class);
                    System.out.println("inflationRate" + inflationRate);
                    final double limit1 = incomeLimits.getIncome1() * inflationRate;
                    System.out.println("limit1:" + limit1);
                    final double limit2 = incomeLimits.getIncome2() * inflationRate;
                    System.out.println("limit2:" + limit2);
                    final double limit3 = incomeLimits.getIncome3() * inflationRate;
                    System.out.println("limit3:" + limit3);
                    final JSONArray costKid = new JSONArray();
                    for (int i = age; i < 19; i++) {
                        double finalKidcost = 0.00;
                        final double incomeValue = userIncome.getJSONObject(++k).getDouble("value");
                        final KidConstants costObj = MongoDBConnection.kid_Expense_NorthEastColl
                                .findOne("{childAge:#}", i).as(KidConstants.class);
                        final KidConstants regionalFactorObj = MongoDBConnection.kid_RegionalAdjustmentFactorColl
                                .findOne("{childAge:#}", i).as(KidConstants.class);
                        final double regionalFactor = regionalFactorObj.getNorthEast();
                        final String type = "single";
                        double childAdjusFactor = 0.00;
                        if (totalKid == 1) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, child).as(KidConstants.class);
                            childAdjusFactor = childAdjusFactorObj.getFirst();
                        }
                        else if(totalKid == 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, totalKid).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                         }
                        else if (totalKid > 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, 3).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                            else {
                                childAdjusFactor = childAdjusFactorObj.getThird_and_younger();
                            }
                        }
                        System.out.println("asdhjb%%%" + childAdjusFactor);

                        final int cost1 = costObj.getCost1();
                        final int cost2 = costObj.getCost2();
                        final int cost3 = costObj.getCost3();
                        if (incomeValue <= limit1) {
                            finalKidcost = cost1 * inflationRate;
                        } else if (incomeValue > limit1 && incomeValue <= limit2) {
                            finalKidcost = cost1 * inflationRate + (cost2 * inflationRate - cost1 * inflationRate)
                                    * (incomeValue - limit1) / (limit2 - limit1);
                        } else if (incomeValue > limit2 && incomeValue <= limit3) {
                            finalKidcost = cost2 * inflationRate + (cost3 * inflationRate - cost2 * inflationRate)
                                    * (incomeValue - limit2) / (limit3 - limit2);
                        } else if (incomeValue > limit3) {
                            finalKidcost = cost3 * inflationRate;

                        }
                        costKid.put(finalKidcost * childAdjusFactor * regionalFactor);

                    }
                    double sum = 0;
                    for (int i = 0; i < costKid.length(); i++) {
                        sum = sum + costKid.getDouble(i);
                    }
                    finalCostToreturn = sum / costKid.length();
                    System.out.println("the fina cost of the kidsingleNE" + finalCostToreturn);

                } else if (states.equals("ALASKA") || states.equals("ARIZONA") || states.equals("CALIFORNIA")
                        || states.equals("COLORADO") || states.equals("HAWAII") || states.equals("IDAHO")
                        || states.equals("MONTANA") || states.equals("NEVADA") || states.equals("NEW MEXICO")
                        || states.equals("OREGON") || states.equals("UTAH") || states.equals("WASHINGTON")
                        || states.equals("WYOMING")) {
                    System.out.println("Area:West");

                    final KidConstants incomeLimits = MongoDBConnection.kid_Expense_WestColl
                            .findOne("{type:#}", "income").as(KidConstants.class);
                    System.out.println("inflationRate" + inflationRate);
                    final double limit1 = incomeLimits.getIncome1() * inflationRate;
                    System.out.println("limit1:" + limit1);
                    final double limit2 = incomeLimits.getIncome2() * inflationRate;
                    System.out.println("limit2:" + limit2);
                    final double limit3 = incomeLimits.getIncome3() * inflationRate;
                    System.out.println("limit3:" + limit3);
                    final JSONArray costKid = new JSONArray();
                    for (int i = age; i < 19; i++) {
                        double finalKidcost = 0.00;
                        final double incomeValue = userIncome.getJSONObject(++k).getDouble("value");
                        final KidConstants costObj = MongoDBConnection.kid_Expense_WestColl.findOne("{childAge:#}", i)
                                .as(KidConstants.class);
                        final KidConstants regionalFactorObj = MongoDBConnection.kid_RegionalAdjustmentFactorColl
                                .findOne("{childAge:#}", i).as(KidConstants.class);
                        final double regionalFactor = regionalFactorObj.getWest();
                        final String type = "single";
                        double childAdjusFactor = 0.00;
                        if (totalKid == 1) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, child).as(KidConstants.class);
                            childAdjusFactor = childAdjusFactorObj.getFirst();
                        }
                        else if(totalKid == 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, totalKid).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                         }
                        else if (totalKid > 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, 3).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                            else {
                                childAdjusFactor = childAdjusFactorObj.getThird_and_younger();
                            }
                        }
                        System.out.println("asdhjb%%%" + childAdjusFactor);

                        final int cost1 = costObj.getCost1();
                        final int cost2 = costObj.getCost2();
                        final int cost3 = costObj.getCost3();
                        if (incomeValue <= limit1) {
                            finalKidcost = cost1 * inflationRate;
                        } else if (incomeValue > limit1 && incomeValue <= limit2) {
                            finalKidcost = cost1 * inflationRate + (cost2 * inflationRate - cost1 * inflationRate)
                                    * (incomeValue - limit1) / (limit2 - limit1);
                        } else if (incomeValue > limit2 && incomeValue <= limit3) {
                            finalKidcost = cost2 * inflationRate + (cost3 * inflationRate - cost2 * inflationRate)
                                    * (incomeValue - limit2) / (limit3 - limit2);
                        } else if (incomeValue > limit3) {
                            finalKidcost = cost3 * inflationRate;

                        }
                        costKid.put(finalKidcost * childAdjusFactor * regionalFactor);

                    }
                    double sum = 0;
                    for (int i = 0; i < costKid.length(); i++) {
                        sum = sum + costKid.getDouble(i);
                    }
                    finalCostToreturn = sum / costKid.length();
                    System.out.println("the fina cost of the kid single_WEST" + finalCostToreturn);
                } else if (states.equals("ILLINOIS") || states.equals("INDIANA") || states.equals("IOWA")
                        || states.equals("KANSAS") || states.equals("MICHIGAN") || states.equals("MINNESOTA")
                        || states.equals("MISSOURI") || states.equals("NEBRASKA") || states.equals("NORTH DAKOTA")
                        || states.equals("OHIO") || states.equals("SOUTH DAKOTA") || states.equals("WISCONSIN")) {
                    System.out.println("Area:MidWest");

                    final KidConstants incomeLimits = MongoDBConnection.kid_Expense_MidWestColl
                            .findOne("{type:#}", "income").as(KidConstants.class);
                    System.out.println("inflationRate" + inflationRate);
                    final double limit1 = incomeLimits.getIncome1() * inflationRate;
                    System.out.println("limit1:" + limit1);
                    final double limit2 = incomeLimits.getIncome2() * inflationRate;
                    System.out.println("limit2:" + limit2);
                    final double limit3 = incomeLimits.getIncome3() * inflationRate;
                    System.out.println("limit3:" + limit3);
                    final JSONArray costKid = new JSONArray();
                    for (int i = age; i < 19; i++) {
                        double finalKidcost = 0.00;
                        final double incomeValue = userIncome.getJSONObject(++k).getDouble("value");
                        final KidConstants costObj = MongoDBConnection.kid_Expense_MidWestColl
                                .findOne("{childAge:#}", i).as(KidConstants.class);
                        final KidConstants regionalFactorObj = MongoDBConnection.kid_RegionalAdjustmentFactorColl
                                .findOne("{childAge:#}", i).as(KidConstants.class);
                        final double regionalFactor = regionalFactorObj.getMidWest();
                        System.out.println("regional factor"+regionalFactor);
                        final String type = "single";
                        double childAdjusFactor = 0.00;
                        if (totalKid == 1) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, child).as(KidConstants.class);
                            childAdjusFactor = childAdjusFactorObj.getFirst();
                        }
                        else if(totalKid == 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, totalKid).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                         }
                        else if (totalKid > 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, 3).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                            else {
                                childAdjusFactor = childAdjusFactorObj.getThird_and_younger();
                            }
                        }
                        System.out.println("asdhjb%%%" + childAdjusFactor);

                        final int cost1 = costObj.getCost1();
                        final int cost2 = costObj.getCost2();
                        final int cost3 = costObj.getCost3();
                        System.out.println("age"+i+"cost1"+cost1+"cost2"+cost2+"cost3"+cost3);
                        if (incomeValue <= limit1) {
                            finalKidcost = cost1 * inflationRate;
                        } else if (incomeValue > limit1 && incomeValue <= limit2) {
                            finalKidcost = cost1 * inflationRate + (cost2 * inflationRate - cost1 * inflationRate)
                                    * (incomeValue - limit1) / (limit2 - limit1);
                        } else if (incomeValue > limit2 && incomeValue <= limit3) {
                            finalKidcost = cost2 * inflationRate + (cost3 * inflationRate - cost2 * inflationRate)
                                    * (incomeValue - limit2) / (limit3 - limit2);
                        } else if (incomeValue > limit3) {
                            finalKidcost = cost3 * inflationRate;

                        }
                        costKid.put(finalKidcost * childAdjusFactor * regionalFactor);

                    }
                    double sum = 0;
                 System.out.println("costKid array"+costKid);
                    for (int j = 0; j < costKid.length(); j++) {
                        sum = sum + costKid.getDouble(j);
                    }
                    finalCostToreturn = sum / costKid.length();
                    System.out.println("the fina cost of the kid single_MID_WEST" + finalCostToreturn);
                } else if (states.equals("ALABAMA") || states.equals("ARKANSAS") || states.equals("DELAWARE")
                        || states.equals("WASHINGTON DC") || states.equals("FLORIDA") || states.equals("GEORGIA")
                        || states.equals("KENTUCKY") || states.equals("LOUISIANA") || states.equals("MARYLAND")
                        || states.equals("MISSISSIPPI") || states.equals("NORTH CAROLINA") || states.equals("OKLAHOMA")
                        || states.equals("SOUTH CAROLINA") || states.equals("TENNESSEE") || states.equals("TEXAS")
                        || states.equals("VIRGINIA") || states.equals("WEST VIRGINIA")) {
                    System.out.println("Area:South");

                    final KidConstants incomeLimits = MongoDBConnection.kid_Expense_SouthColl
                            .findOne("{type:#}", "income").as(KidConstants.class);
                    System.out.println("inflationRate" + inflationRate);
                    final double limit1 = incomeLimits.getIncome1() * inflationRate;
                    System.out.println("limit1:" + limit1);
                    final double limit2 = incomeLimits.getIncome2() * inflationRate;
                    System.out.println("limit2:" + limit2);
                    final double limit3 = incomeLimits.getIncome3() * inflationRate;
                    System.out.println("limit3:" + limit3);
                    final JSONArray costKid = new JSONArray();
                    for (int i = age; i < 19; i++) {
                        double finalKidcost = 0.00;
                        final double incomeValue = userIncome.getJSONObject(++k).getDouble("value");
                        final KidConstants costObj = MongoDBConnection.kid_Expense_SouthColl.findOne("{childAge:#}", i)
                                .as(KidConstants.class);
                        final KidConstants regionalFactorObj = MongoDBConnection.kid_RegionalAdjustmentFactorColl
                                .findOne("{childAge:#}", i).as(KidConstants.class);
                        final double regionalFactor = regionalFactorObj.getSouth();
                        final String type = "single";
                        double childAdjusFactor = 0.00;
                        if (totalKid == 1) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, child).as(KidConstants.class);
                            childAdjusFactor = childAdjusFactorObj.getFirst();
                        }
                        else if(totalKid == 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, totalKid).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                         }
                        else if (totalKid > 2) {
                            final KidConstants childAdjusFactorObj = MongoDBConnection.kid_ChildFactorColl
                                    .findOne("{type:#,child:#}", type, 3).as(KidConstants.class);
                            if(child == 1) {
                                childAdjusFactor = childAdjusFactorObj.getFirst();
                            }
                            else if(child == 2) {
                                childAdjusFactor = childAdjusFactorObj.getSecond();
                            }
                            else {
                                childAdjusFactor = childAdjusFactorObj.getThird_and_younger();
                            }
                        }
                        System.out.println("asdhjb%%%" + childAdjusFactor);

                        final int cost1 = costObj.getCost1();
                        final int cost2 = costObj.getCost2();
                        final int cost3 = costObj.getCost3();
                        if (incomeValue <= limit1) {
                            finalKidcost = cost1 * inflationRate;
                        } else if (incomeValue > limit1 && incomeValue <= limit2) {
                            finalKidcost = cost1 * inflationRate + (cost2 * inflationRate - cost1 * inflationRate)
                                    * (incomeValue - limit1) / (limit2 - limit1);
                        } else if (incomeValue > limit2 && incomeValue <= limit3) {
                            finalKidcost = cost2 * inflationRate + (cost3 * inflationRate - cost2 * inflationRate)
                                    * (incomeValue - limit2) / (limit3 - limit2);
                        } else if (incomeValue > limit3) {
                            finalKidcost = cost3 * inflationRate;

                        }
                        costKid.put(finalKidcost * childAdjusFactor * regionalFactor);

                    }
                    double sum = 0;
                    for (int i = 0; i < costKid.length(); i++) {
                        sum = sum + costKid.getDouble(i);
                    }
                    finalCostToreturn = sum / costKid.length();
                    System.out.println("the fina cost of the kid single_SOUTH" + finalCostToreturn);
                }

            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return finalCostToreturn;
    }

}
