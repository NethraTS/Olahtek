package com.mongorest.olahtek.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongorest.olahtek.model.Counters;
import com.mongorest.olahtek.model.Emergencyfundmodel;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.ModelVacation;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.User;

@Service("vacationService")
@Transactional
public class VacationServiceImpl implements VacationService {
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final Calendar cal = Calendar.getInstance();
    private final int currentYear = cal.get(Calendar.YEAR);
    private static final int FOUR = 4;
    private static final int DEFAULT_RETIRMENT_AGE = 70;

    @Override
    public JSONObject saveVacationGoal(final ModelVacation vacation) {
        final long goalStartTime = System.nanoTime();
        // System.out.println("goalStartTime-->>"+goalStartTime);
        final JSONObject systemLog = new JSONObject();
        final JSONObject vacationJson = new JSONObject();
        final JSONObject vacationExpensedata = new JSONObject();
        Counters count;
        String goalid;
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final Date date = new Date();
        try {
            vacationJson.put("status", "fail");
            final String userId = vacation.getUser_id();
            String planName = vacation.getPlan_name();
            final String formType = vacation.getFormType();
            if (formType.equals("next")) {
                final User details = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
                try {
                    final long amountSave = vacation.getAmountSave();
                    final String frequency = vacation.getFrequency();
                    final int vacationYear = vacation.getVacationYear();
                    final int startYear = vacation.getVacationYear();
                    int endYear = startYear + (vacation.getVacationYear() - startYear);
                    final FinPlan finDetails = MongoDBConnection.finplancol
                            .findOne("{usr_id:#,plan_name:#}", userId, planName).as(FinPlan.class);
                    final String finId = finDetails.get_id();
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
                    JSONArray expenseObj = new JSONArray();
                    JSONObject expense = new JSONObject();
                    expense = finPlanJson.getJSONObject("expense");
                    if (!expense.isNull("housing_expense")) {
                        expenseObj = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
                    }
                    final String finPlanProfileName = finDetails.getProfile_name();
                    endYear = assets.getJSONObject(assets.length() - 1).getInt("year");
                    double tempForEveryOtherYear = amountSave * (endYear - startYear + 1);
                    if ((endYear - startYear) % 2 == 0) {
                        tempForEveryOtherYear = amountSave * (endYear - startYear + 2);
                    }
                    for (int i = 0; i < limits.length(); i++) {
                        if (startYear > currentYear + FOUR) {
                            if (frequency.equals("Every Year")) {
                                if (limits.getJSONObject(i).getInt("year") <= startYear) {
                                    limits.getJSONObject(i).put("taxable", amountSave * (endYear - startYear + 1)
                                            + limits.getJSONObject(i).getInt("taxable"));
                                }
                                if (limits.getJSONObject(i).getInt("year") > startYear
                                        && limits.getJSONObject(i).getInt("year") <= endYear) {
                                    limits.getJSONObject(i).put("taxable",
                                            amountSave * (endYear - limits.getJSONObject(i).getInt("year") + 1)
                                            + limits.getJSONObject(i).getInt("taxable"));
                                }

                                if (deductions.getJSONObject(i).getInt("year") >= startYear) {
                                    deductions.getJSONObject(i).put("taxable",
                                            amountSave + deductions.getJSONObject(i).getDouble("taxable"));
                                }
                            } else if (frequency.equals("Every Two Years")) {
                                if (limits.getJSONObject(i).getInt("year") < startYear) {
                                    limits.getJSONObject(i).put("taxable", amountSave * (endYear - startYear + 1) / 2
                                            + limits.getJSONObject(i).getInt("taxable"));
                                }
                            } else {
                                if (limits.getJSONObject(i).getInt("year") < startYear) {
                                    limits.getJSONObject(i).put("taxable",
                                            amountSave + limits.getJSONObject(i).getInt("taxable"));
                                }

                                if (deductions.getJSONObject(i).getInt("year") == startYear) {
                                    deductions.getJSONObject(i).put("taxable",
                                            amountSave + deductions.getJSONObject(i).getDouble("taxable"));
                                }
                            }

                        } else {
                            if (frequency.equals("Every Year")) {
                                if (limits.getJSONObject(i).getInt("year") < startYear) {
                                    limits.getJSONObject(i).put("saving", amountSave * (endYear - startYear + 1)
                                            + limits.getJSONObject(i).getInt("saving"));
                                }
                                if (limits.getJSONObject(i).getInt("year") >= startYear
                                        && limits.getJSONObject(i).getInt("year") <= endYear) {
                                    limits.getJSONObject(i).put("saving",
                                            amountSave * (endYear - limits.getJSONObject(i).getInt("year"))
                                            + limits.getJSONObject(i).getInt("saving"));
                                }

                                if (deductions.getJSONObject(i).getInt("year") >= startYear) {
                                    deductions.getJSONObject(i).put("saving",
                                            amountSave + deductions.getJSONObject(i).getDouble("saving"));
                                }
                            } else if (frequency.equals("Every Two Years")) {
                                if (limits.getJSONObject(i).getInt("year") < startYear) {
                                    limits.getJSONObject(i).put("saving",
                                            tempForEveryOtherYear / 2 + limits.getJSONObject(i).getInt("saving"));
                                }
                            } else {
                                if (limits.getJSONObject(i).getInt("year") < startYear) {
                                    limits.getJSONObject(i).put("saving",
                                            amountSave + limits.getJSONObject(i).getInt("saving"));
                                }

                                if (deductions.getJSONObject(i).getInt("year") == startYear) {
                                    deductions.getJSONObject(i).put("saving",
                                            amountSave + deductions.getJSONObject(i).getDouble("saving"));
                                }
                            }
                        }

                    }
                    if (frequency.equals("Every Two Years")) {
                        for (int i = 0; i < assets.length(); i++) {
                            if (frequency.equals("Every Two Years")
                                    && limits.getJSONObject(i).getInt("year") >= startYear) {
                                if (limits.getJSONObject(i).getInt("year") >= startYear
                                        && limits.getJSONObject(i).getInt("year") <= endYear) {
                                    double temp = amountSave * (endYear - limits.getJSONObject(i).getInt("year") - 1);
                                    if ((endYear - startYear) % 2 == 0) {
                                        temp = amountSave * (endYear - limits.getJSONObject(i).getInt("year"));
                                    }
                                    limits.getJSONObject(i).put("saving",
                                            limits.getJSONObject(i).getInt("saving") + temp / 2);
                                    if (limits.getJSONObject(i).getInt("year") != endYear) {
                                        limits.getJSONObject(i + 1).put("saving",
                                                limits.getJSONObject(i + 1).getInt("saving") + temp / 2);
                                    }
                                    if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                        limits.getJSONObject(i).put("saving", 0);
                                    }
                                }
                                if (deductions.getJSONObject(i).getInt("year") >= startYear) {
                                    deductions.getJSONObject(i).put("saving",
                                            deductions.getJSONObject(i).getDouble("saving") + amountSave);
                                    if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                        limits.getJSONObject(i).put("saving", 0);
                                    }
                                }
                                i = i + 1;
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
                            .findOne("{fin_id:#,goalType:#}", finId, "Retirement").as(RetirementGoal.class);
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
                        userStartYear = details.getBirthYear() + DEFAULT_RETIRMENT_AGE;
                        if (finDetails.getMarital_status().equals("Yes") && details.getMarital_status().equals("No")) {
                            spouseStartYear = finDetails.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                        } else if (details.getMarital_status().equals("Yes")) {
                            spouseStartYear = details.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
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
                    //  System.out.println("in vacation create limits "+limits);
                    //  System.out.println("in vacation create assets "+assets);
                    //  System.out.println("in vacation create deductions "+deductions);
                    final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                            spouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, userId, fillingExemtion,
                            userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions, kidBirthYear,
                            retirementData, retirementObj, expenseObj, emergencyType, monthsOfIncome, monthsOfExpense,
                            finDetails.isRetirementFlag());

                    if (result.getString("status").equals("success")) {

                        count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                        goalid = "goal" + count.getGoal_id();
                        MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                        .with("{$inc: {goal_id: 1}}");

                        final BasicDBObject basicDBObject = new BasicDBObject("_id", goalid)
                                .append("amountSave", amountSave).append("vacationYear", vacationYear)
                                .append("frequency", frequency).append("user_id", vacation.getUser_id())
                                .append("plan_name", vacation.getPlan_name())
                                .append("createdTs", dateFormat.format(date))
                                .append("modifiedTs", dateFormat.format(date)).append("goalType", "Vacation")
                                .append("completedStatus", 1).append("goal", 1);
                        vacationExpensedata.put("expenses", amountSave);
                        vacationExpensedata.put("frequency", frequency);
                        vacationExpensedata.put("startingYear", startYear);
                        vacationExpensedata.put("endYear", endYear);

                        MongoDBConnection.goalcoll.insert(basicDBObject);
                        MongoDBConnection.finplancol.update("{'usr_id':#,'plan_name':#}", userId, planName).upsert()
                        .multi()
                        .with("{$set: {'expense.vacation_expense':" + vacationExpensedata + ",'assests':"
                                + assets + ",'tax':" + tax + ",'limits':" + limits + ",'deductions':"
                                + deductions + "}}");
                        MongoDBConnection.goalcoll.update("{'_id':#}", vacation.getGoal_id()).upsert().multi()
                        .with("{$set: {'amountSave':" + amountSave + ",'modifiedTs':'" + dateFormat.format(date)
                        + "','frequency':'" + frequency + "','plan_name':'" + planName + "','user_id':'"
                        + userId + "','vacationYear':" + vacationYear + "}}");
                        MongoDBConnection.finplancol.update("{'usr_id':#,'plan_name':#}", userId, planName).upsert()
                        .multi().with("{$addToSet: {'goals':'" + goalid + "'}}");
                        vacationJson.put("status", "success");
                        vacationJson.put("massage", "Goal created successfully !!");
                        vacationJson.put("amountSave", amountSave);
                        vacationJson.put("frequency", frequency);
                        vacationJson.put("goal_id", goalid);
                        systemLog.put("type", "success");
                        systemLog.put("message", "Goal Created Successfully!!");
                        systemLog.put("userName", details.getName());
                        systemLog.put("user_id", userId);
                        systemLog.put("createdTs", dateFormat.format(date));
                    } else {
                        vacationJson.put("status", "fail");
                    }

                } catch (final Exception e) {
                    systemLog.put("type", "error");
                    systemLog.put("message", "Goal Creation Failed!!");
                    systemLog.put("userName", details.getName());
                    systemLog.put("user_id", userId);
                    systemLog.put("createdTs", dateFormat.format(date));
                    e.printStackTrace();
                }

                // System.out.println("Elapsed time for creating vacation goal:
                // " + (System.nanoTime() - goalStartTime) / 1000000 + " milli
                // seconds");
                return vacationJson;
            } else if (formType.equals("deleteGoal")) {
                final String goalId = vacation.getGoal_id();
                final ModelVacation vacationData = MongoDBConnection.goalcoll.findOne("{_id:#}", goalId)
                        .as(ModelVacation.class);
                final long oldAmountSave = vacationData.getAmountSave();
                final String oldFrequency = vacationData.getFrequency();
                final int oldVacationYear = vacationData.getVacationYear();
                int endYear;
                final FinPlan finDetails = MongoDBConnection.finplancol
                        .findOne("{usr_id:#,plan_name:#}", userId, vacationData.getPlan_name()).as(FinPlan.class);
                final String finId = finDetails.get_id();
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
                endYear = assets.getJSONObject(assets.length() - 1).getInt("year");
                double tempForEveryOtherYear = oldAmountSave * (endYear - oldVacationYear + 1);
                if ((endYear - oldVacationYear) % 2 == 0) {
                    tempForEveryOtherYear = oldAmountSave * (endYear - oldVacationYear + 2);
                }
                for (int i = 0; i < limits.length() - 1; i++) {
                    if (oldVacationYear > currentYear + FOUR) {
                        if (oldFrequency.equals("Every Year")) {
                            if (limits.getJSONObject(i).getInt("year") <= oldVacationYear) {
                                limits.getJSONObject(i).put("taxable", limits.getJSONObject(i).getInt("taxable")
                                        - oldAmountSave * (endYear - oldVacationYear + 1));
                                if (limits.getJSONObject(i).getDouble("taxable") < 0) {
                                    limits.getJSONObject(i).put("taxable", 0);
                                }
                            }
                            if (limits.getJSONObject(i).getInt("year") > oldVacationYear
                                    && limits.getJSONObject(i).getInt("year") <= endYear) {
                                limits.getJSONObject(i).put("taxable", limits.getJSONObject(i).getInt("taxable")
                                        - oldAmountSave * (endYear - limits.getJSONObject(i).getInt("year")));
                                limits.getJSONObject(i).put("taxable", 0);
                            }

                            if (deductions.getJSONObject(i).getInt("year") >= oldVacationYear) {
                                deductions.getJSONObject(i).put("taxable",
                                        deductions.getJSONObject(i).getDouble("taxable") - oldAmountSave);
                                limits.getJSONObject(i).put("taxable", 0);
                            }
                        } else if (oldFrequency.equals("Every Two Years")) {
                            if (limits.getJSONObject(i).getInt("year") < oldVacationYear) {
                                limits.getJSONObject(i).put("taxable", limits.getJSONObject(i).getInt("taxable")
                                        - oldAmountSave * (endYear - oldVacationYear + 1) / 2);
                                if (limits.getJSONObject(i).getDouble("taxable") < 0) {
                                    limits.getJSONObject(i).put("taxable", 0);
                                }
                            }
                            if (limits.getJSONObject(i).getInt("year") >= oldVacationYear
                                    && limits.getJSONObject(i).getInt("year") <= endYear) {
                                limits.getJSONObject(i).put("taxable", limits.getJSONObject(i).getInt("taxable")
                                        - oldAmountSave * (endYear - limits.getJSONObject(i).getInt("year") / 2));
                                limits.getJSONObject(i).put("taxable", 0);
                            }

                            if (deductions.getJSONObject(i).getInt("year") >= oldVacationYear) {
                                deductions.getJSONObject(i).put("taxable",
                                        deductions.getJSONObject(i).getDouble("taxable") - oldAmountSave
                                        * (endYear - (deductions.getJSONObject(i).getInt("year")) / 2));
                                limits.getJSONObject(i).put("taxable", 0);
                            }
                        } else {
                            if (limits.getJSONObject(i).getInt("year") < oldVacationYear) {
                                limits.getJSONObject(i).put("taxable",
                                        limits.getJSONObject(i).getInt("taxable") - oldAmountSave);
                                limits.getJSONObject(i).put("taxable", 0);
                            }

                            if (deductions.getJSONObject(i).getInt("year") == oldVacationYear) {
                                deductions.getJSONObject(i).put("taxable",
                                        deductions.getJSONObject(i).getDouble("taxable") - oldAmountSave);
                                limits.getJSONObject(i).put("taxable", 0);
                            }
                        }

                    } else {
                        if (oldFrequency.equals("Every Year")) {
                            if (limits.getJSONObject(i).getInt("year") < oldVacationYear) {
                                limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving")
                                        - oldAmountSave * (endYear - oldVacationYear + 1));
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            }
                            if (limits.getJSONObject(i).getInt("year") >= oldVacationYear
                                    && limits.getJSONObject(i).getInt("year") <= endYear) {
                                limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving")
                                        - oldAmountSave * (endYear - limits.getJSONObject(i).getInt("year")));
                                limits.getJSONObject(i).put("saving", 0);
                            }

                            if (deductions.getJSONObject(i).getInt("year") >= oldVacationYear) {
                                deductions.getJSONObject(i).put("saving",
                                        deductions.getJSONObject(i).getDouble("saving") - oldAmountSave);
                                limits.getJSONObject(i).put("saving", 0);
                            }
                        } else if (oldFrequency.equals("Every Two Years")) {
                            if (limits.getJSONObject(i).getInt("year") < oldVacationYear) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - tempForEveryOtherYear / 2);
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            }
                        } else {
                            if (limits.getJSONObject(i).getInt("year") < oldVacationYear) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - oldAmountSave);
                                limits.getJSONObject(i).put("saving", 0);
                            }

                            if (deductions.getJSONObject(i).getInt("year") == oldVacationYear) {
                                deductions.getJSONObject(i).put("saving",
                                        deductions.getJSONObject(i).getDouble("saving") - oldAmountSave);
                                limits.getJSONObject(i).put("saving", 0);
                            }
                        }

                    }

                }

                for (int i = 0; i < assets.length(); i++) {
                    if (oldFrequency.equals("Every Two Years")
                            && limits.getJSONObject(i).getInt("year") >= oldVacationYear) {
                        if (limits.getJSONObject(i).getInt("year") >= oldVacationYear
                                && limits.getJSONObject(i).getInt("year") <= endYear) {
                            double temp = oldAmountSave * (endYear - limits.getJSONObject(i).getInt("year") - 1);
                            if ((endYear - oldVacationYear) % 2 == 0) {
                                temp = oldAmountSave * (endYear - limits.getJSONObject(i).getInt("year"));
                            }
                            limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving") - temp / 2);
                            if (limits.getJSONObject(i).getInt("year") != endYear) {
                                limits.getJSONObject(i + 1).put("saving",
                                        limits.getJSONObject(i + 1).getInt("saving") - temp / 2);
                            }
                            if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                limits.getJSONObject(i).put("saving", 0);
                            }
                        }

                        if (deductions.getJSONObject(i).getInt("year") >= oldVacationYear) {
                            deductions.getJSONObject(i).put("saving",
                                    deductions.getJSONObject(i).getDouble("saving") - oldAmountSave);
                            if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                limits.getJSONObject(i).put("saving", 0);
                            }
                        }
                        i = i + 1;
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
                    userStartYear = details.getBirthYear() + DEFAULT_RETIRMENT_AGE;
                    if (finDetails.getMarital_status().equals("Yes") && details.getMarital_status().equals("No")) {
                        spouseStartYear = finDetails.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                    } else if (details.getMarital_status().equals("Yes")) {
                        spouseStartYear = details.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
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
                        retirementData, retirementObj, expenseObj, emergencyType, monthsOfIncome, monthsOfExpense,
                        finDetails.isRetirementFlag());
                final String status = result.getString("status");
                if (status.equals("success")) {

                    assets = result.getJSONArray("assets");
                    tax = result.getJSONArray("tax");
                    final JSONArray goalJsonArray = finPlanJson.getJSONArray("goals");
                    final JSONArray goalsArray = new JSONArray();
                    for (int j = 0; j < goalJsonArray.length(); j++) {
                        final String goalID = (String) goalJsonArray.get(j);
                        if (!goalID.equals(goalId)) {
                            goalsArray.put(goalID);
                        } else {
                            continue;
                        }
                    }
                    MongoDBConnection.finplancol.update("{'usr_id':#,'plan_name':#}", userId, planName).upsert().multi()
                    .with("{$set: {'expense.vacation_expense':" + null + ",'goals':" + goalsArray
                            + ",'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits
                            + ",'deductions':" + deductions + "}}");
                    MongoDBConnection.goalcoll.remove("{'_id':#}", goalId);
                    vacationJson.put("status", "success");
                } else {
                    vacationJson.put("status", "fail");
                }
                // System.out.println("Elapsed time for delet vacation goal: " +
                // (System.nanoTime() - goalStartTime) / 1000000 + " milli
                // seconds");
                return vacationJson;
            } else if (formType.equals("update")) {
                final User details = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
                try {

                    final String goalId = vacation.getGoal_id();
                    final ModelVacation vacationData = MongoDBConnection.goalcoll.findOne("{_id:#}", goalId)
                            .as(ModelVacation.class);
                    final long oldAmountSave = vacationData.getAmountSave();
                    final String oldFrequency = vacationData.getFrequency();
                    final int oldVacationYear = vacationData.getVacationYear();
                    planName = vacationData.getPlan_name();
                    int endYear;
                    final FinPlan finDetails = MongoDBConnection.finplancol
                            .findOne("{usr_id:#,plan_name:#}", userId, vacationData.getPlan_name()).as(FinPlan.class);
                    final String finId = finDetails.get_id();
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
                    endYear = assets.getJSONObject(assets.length() - 1).getInt("year");
                    double tempForEveryOtherYear = oldAmountSave * (endYear - oldVacationYear + 1);
                    if ((endYear - oldVacationYear) % 2 == 0) {
                        tempForEveryOtherYear = oldAmountSave * (endYear - oldVacationYear + 2);
                    }
                    for (int i = 0; i < limits.length(); i++) {
                        if (oldVacationYear > currentYear + FOUR) {
                            if (oldFrequency.equals("Every Year")) {
                                if (limits.getJSONObject(i).getInt("year") < oldVacationYear) {
                                    limits.getJSONObject(i).put("taxable", limits.getJSONObject(i).getInt("taxable")
                                            - oldAmountSave * (endYear - oldVacationYear + 1));
                                    if (limits.getJSONObject(i).getDouble("taxable") < 0) {
                                        limits.getJSONObject(i).put("taxable", 0);
                                    }
                                }
                                if (limits.getJSONObject(i).getInt("year") >= oldVacationYear
                                        && limits.getJSONObject(i).getInt("year") <= endYear) {
                                    limits.getJSONObject(i).put("taxable", limits.getJSONObject(i).getInt("taxable")
                                            - oldAmountSave * (endYear - limits.getJSONObject(i).getInt("year")));
                                    if (limits.getJSONObject(i).getDouble("taxable") < 0) {
                                        limits.getJSONObject(i).put("taxable", 0);
                                    }
                                }

                                if (deductions.getJSONObject(i).getInt("year") >= oldVacationYear) {
                                    deductions.getJSONObject(i).put("taxable",
                                            deductions.getJSONObject(i).getDouble("taxable") - oldAmountSave);
                                    if (limits.getJSONObject(i).getDouble("taxable") < 0) {
                                        limits.getJSONObject(i).put("taxable", 0);
                                    }
                                }
                            } else if (oldFrequency.equals("Every Two Years")) {
                                if (limits.getJSONObject(i).getInt("year") < oldVacationYear) {
                                    limits.getJSONObject(i).put("taxable", limits.getJSONObject(i).getInt("taxable")
                                            - oldAmountSave * (endYear - oldVacationYear + 1) / 2);
                                    if (limits.getJSONObject(i).getDouble("taxable") < 0) {
                                        limits.getJSONObject(i).put("taxable", 0);
                                    }
                                }
                                if (limits.getJSONObject(i).getInt("year") >= oldVacationYear
                                        && limits.getJSONObject(i).getInt("year") <= endYear) {
                                    limits.getJSONObject(i).put("taxable", limits.getJSONObject(i).getInt("taxable")
                                            - oldAmountSave * (endYear - limits.getJSONObject(i).getInt("year") / 2));
                                    if (limits.getJSONObject(i).getDouble("taxable") < 0) {
                                        limits.getJSONObject(i).put("taxable", 0);
                                    }
                                }

                                if (deductions.getJSONObject(i).getInt("year") >= oldVacationYear) {
                                    deductions.getJSONObject(i).put("taxable",
                                            deductions.getJSONObject(i).getDouble("taxable") - oldAmountSave
                                            * (endYear - (deductions.getJSONObject(i).getInt("year")) / 2));
                                    if (limits.getJSONObject(i).getDouble("taxable") < 0) {
                                        limits.getJSONObject(i).put("taxable", 0);
                                    }
                                }
                            } else {
                                if (limits.getJSONObject(i).getInt("year") < oldVacationYear) {
                                    limits.getJSONObject(i).put("taxable",
                                            limits.getJSONObject(i).getInt("taxable") - oldAmountSave);
                                    if (limits.getJSONObject(i).getDouble("taxable") < 0) {
                                        limits.getJSONObject(i).put("taxable", 0);
                                    }
                                }

                                if (deductions.getJSONObject(i).getInt("year") == oldVacationYear) {
                                    deductions.getJSONObject(i).put("taxable",
                                            deductions.getJSONObject(i).getDouble("taxable") - oldAmountSave);
                                    if (limits.getJSONObject(i).getDouble("taxable") < 0) {
                                        limits.getJSONObject(i).put("taxable", 0);
                                    }
                                }
                            }

                        } else {
                            if (oldFrequency.equals("Every Year")) {
                                if (limits.getJSONObject(i).getInt("year") < oldVacationYear) {
                                    limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving")
                                            - oldAmountSave * (endYear - oldVacationYear + 1));
                                    if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                        limits.getJSONObject(i).put("saving", 0);
                                    }
                                }
                                if (limits.getJSONObject(i).getInt("year") >= oldVacationYear
                                        && limits.getJSONObject(i).getInt("year") <= endYear) {
                                    limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving")
                                            - oldAmountSave * (endYear - limits.getJSONObject(i).getInt("year")));
                                    if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                        limits.getJSONObject(i).put("saving", 0);
                                    }
                                }

                                if (deductions.getJSONObject(i).getInt("year") >= oldVacationYear) {
                                    deductions.getJSONObject(i).put("saving",
                                            deductions.getJSONObject(i).getDouble("saving") - oldAmountSave);
                                    if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                        limits.getJSONObject(i).put("saving", 0);
                                    }
                                }
                            } else if (oldFrequency.equals("Every Two Years")) {
                                // System.out.println("on
                                // update...."+tempForEveryOtherYear+"....oldVacationYear=="+oldVacationYear);
                                if (limits.getJSONObject(i).getInt("year") < oldVacationYear) {
                                    // System.out.println("on update iside if");
                                    limits.getJSONObject(i).put("saving",
                                            limits.getJSONObject(i).getInt("saving") - tempForEveryOtherYear / 2);
                                    if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                        limits.getJSONObject(i).put("saving", 0);
                                    }
                                }

                            } else {
                                if (limits.getJSONObject(i).getInt("year") < oldVacationYear) {
                                    limits.getJSONObject(i).put("saving",
                                            limits.getJSONObject(i).getInt("saving") - oldAmountSave);
                                    if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                        limits.getJSONObject(i).put("saving", 0);
                                    }
                                }

                                if (deductions.getJSONObject(i).getInt("year") == oldVacationYear) {
                                    deductions.getJSONObject(i).put("saving",
                                            deductions.getJSONObject(i).getDouble("saving") - oldAmountSave);
                                    if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                        limits.getJSONObject(i).put("saving", 0);
                                    }
                                }
                            }

                        }

                    }

                    for (int i = 0; i < assets.length(); i++) {
                        if (oldFrequency.equals("Every Two Years")
                                && limits.getJSONObject(i).getInt("year") >= oldVacationYear) {
                            if (limits.getJSONObject(i).getInt("year") >= oldVacationYear
                                    && limits.getJSONObject(i).getInt("year") <= endYear) {
                                double temp = oldAmountSave * (endYear - limits.getJSONObject(i).getInt("year") - 1);
                                if ((endYear - oldVacationYear) % 2 == 0) {
                                    temp = oldAmountSave * (endYear - limits.getJSONObject(i).getInt("year"));
                                }
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - temp / 2);
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                                if (limits.getJSONObject(i).getInt("year") != endYear) {
                                    limits.getJSONObject(i + 1).put("saving",
                                            limits.getJSONObject(i + 1).getInt("saving") - temp / 2);
                                }
                            }

                            if (deductions.getJSONObject(i).getInt("year") >= oldVacationYear) {
                                deductions.getJSONObject(i).put("saving",
                                        deductions.getJSONObject(i).getDouble("saving") - oldAmountSave);
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            }
                            i = i + 1;
                        }
                    }

                    final long amountSave = vacation.getAmountSave();
                    final String frequency = vacation.getFrequency();
                    final int vacationYear = vacation.getVacationYear();
                    tempForEveryOtherYear = amountSave * (endYear - vacationYear + 1);
                    if ((endYear - vacationYear) % 2 == 0) {
                        tempForEveryOtherYear = amountSave * (endYear - vacationYear + 2);
                    }

                    for (int i = 0; i < limits.length(); i++) {
                        if (vacationYear > currentYear + FOUR) {
                            if (frequency.equals("Every Year")) {
                                if (limits.getJSONObject(i).getInt("year") <= vacationYear) {
                                    limits.getJSONObject(i).put("taxable", amountSave * (endYear - vacationYear + 1)
                                            + limits.getJSONObject(i).getInt("taxable"));
                                }
                                if (limits.getJSONObject(i).getInt("year") > vacationYear
                                        && limits.getJSONObject(i).getInt("year") <= endYear) {
                                    limits.getJSONObject(i).put("taxable",
                                            amountSave * (endYear - limits.getJSONObject(i).getInt("year"))
                                            + limits.getJSONObject(i).getInt("taxable"));
                                }

                                if (deductions.getJSONObject(i).getInt("year") >= vacationYear) {
                                    deductions.getJSONObject(i).put("taxable",
                                            amountSave + deductions.getJSONObject(i).getDouble("taxable"));
                                }
                            } else if (frequency.equals("Every Two Years")) {
                                if (limits.getJSONObject(i).getInt("year") < vacationYear) {
                                    limits.getJSONObject(i).put("taxable", amountSave * (endYear - vacationYear + 1) / 2
                                            + limits.getJSONObject(i).getInt("taxable"));
                                }
                            } else {
                                if (limits.getJSONObject(i).getInt("year") < vacationYear) {
                                    limits.getJSONObject(i).put("taxable",
                                            amountSave + limits.getJSONObject(i).getInt("taxable"));
                                }

                                if (deductions.getJSONObject(i).getInt("year") == vacationYear) {
                                    deductions.getJSONObject(i).put("taxable",
                                            amountSave + deductions.getJSONObject(i).getDouble("taxable"));
                                }
                            }

                        } else {
                            if (frequency.equals("Every Year")) {
                                if (limits.getJSONObject(i).getInt("year") < vacationYear) {
                                    limits.getJSONObject(i).put("saving", amountSave * (endYear - vacationYear + 1)
                                            + limits.getJSONObject(i).getInt("saving"));
                                }
                                if (limits.getJSONObject(i).getInt("year") >= vacationYear
                                        && limits.getJSONObject(i).getInt("year") <= endYear) {
                                    limits.getJSONObject(i).put("saving",
                                            amountSave * (endYear - limits.getJSONObject(i).getInt("year"))
                                            + limits.getJSONObject(i).getInt("saving"));
                                }

                                if (deductions.getJSONObject(i).getInt("year") >= vacationYear) {
                                    deductions.getJSONObject(i).put("saving",
                                            amountSave + deductions.getJSONObject(i).getDouble("saving"));
                                }
                            } else if (frequency.equals("Every Two Years")) {
                                if (limits.getJSONObject(i).getInt("year") < vacationYear) {
                                    limits.getJSONObject(i).put("saving",
                                            (tempForEveryOtherYear / 2) + limits.getJSONObject(i).getInt("saving"));
                                }
                            } else {
                                if (limits.getJSONObject(i).getInt("year") < vacationYear) {
                                    limits.getJSONObject(i).put("saving",
                                            amountSave + limits.getJSONObject(i).getInt("saving"));
                                }

                                if (deductions.getJSONObject(i).getInt("year") == vacationYear) {
                                    deductions.getJSONObject(i).put("saving",
                                            amountSave + deductions.getJSONObject(i).getDouble("saving"));
                                }
                            }
                        }

                    }

                    for (int i = 0; i < assets.length(); i++) {
                        if (frequency.equals("Every Two Years")
                                && limits.getJSONObject(i).getInt("year") >= vacationYear) {
                            if (limits.getJSONObject(i).getInt("year") >= vacationYear
                                    && limits.getJSONObject(i).getInt("year") <= endYear) {
                                double temp = amountSave * (endYear - limits.getJSONObject(i).getInt("year") - 1);
                                if ((endYear - vacationYear) % 2 == 0) {
                                    temp = amountSave * (endYear - limits.getJSONObject(i).getInt("year"));
                                }
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") + temp / 2);
                                if (limits.getJSONObject(i).getInt("year") != endYear) {
                                    limits.getJSONObject(i + 1).put("saving",
                                            limits.getJSONObject(i + 1).getInt("saving") + temp / 2);
                                }
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            }

                            if (deductions.getJSONObject(i).getInt("year") >= vacationYear) {
                                deductions.getJSONObject(i).put("saving",
                                        deductions.getJSONObject(i).getDouble("saving") + amountSave);
                                if (limits.getJSONObject(i).getDouble("saving") < 0) {
                                    limits.getJSONObject(i).put("saving", 0);
                                }
                            }
                            i = i + 1;
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
                        userStartYear = details.getBirthYear() + DEFAULT_RETIRMENT_AGE;
                        if (finDetails.getMarital_status().equals("Yes") && details.getMarital_status().equals("No")) {
                            spouseStartYear = finDetails.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                        } else if (details.getMarital_status().equals("Yes")) {
                            spouseStartYear = details.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
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
                    //  System.out.println("in vacation update limits "+limits);
                    //   System.out.println("in vacation update assets "+assets);
                    //  System.out.println("in vacation update deductions "+deductions);
                    final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, finId, combinedIncome,
                            spouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, userId, fillingExemtion,
                            userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions, kidBirthYear,
                            retirementData, retirementObj, expenseObj, emergencyType, monthsOfIncome, monthsOfExpense,
                            finDetails.isRetirementFlag());

                    final String status = result.getString("status");
                    if (status.equals("success")) {

                        assets = result.getJSONArray("assets");
                        tax = result.getJSONArray("tax");
                        final int startYear = vacation.getVacationYear();
                        vacationExpensedata.put("expenses", amountSave);
                        vacationExpensedata.put("startingYear", startYear);
                        vacationExpensedata.put("frequency", frequency);
                        vacationExpensedata.put("endYear", endYear);
                        MongoDBConnection.finplancol.update("{'usr_id':#,'plan_name':#}", userId, planName).upsert()
                        .multi().with("{$addToSet: {'goals':'" + vacation.getGoal_id() + "'}}");
                        // System.out.println("Bala... the plan name in the
                        // update of the vacction"+plan_name);
                        MongoDBConnection.finplancol.update("{'usr_id':#,'plan_name':#}", userId, planName).upsert()
                        .multi()
                        .with("{$set: {'expense.vacation_expense':" + vacationExpensedata + ",'assests':"
                                + assets + ",'tax':" + tax + ",'limits':" + limits + ",'deductions':"
                                + deductions + "}}");
                        MongoDBConnection.goalcoll.update("{'_id':#}", vacation.getGoal_id()).upsert().multi()
                        .with("{$set: {'amountSave':" + amountSave + ",'modifiedTs':'" + dateFormat.format(date)
                        + "','frequency':'" + frequency + "','vacationYear':" + vacationYear + "}}");
                        vacationJson.put("frequency", vacation.getFrequency());
                        vacationJson.put("amountSave", vacation.getAmountSave());
                        vacationJson.put("status", "success");
                        vacationJson.put("plan_name", planName);
                        vacationJson.put("vacationYear", vacation.getVacationYear());
                        vacationJson.put("massage", "Goal updated successfully !!");

                    } else {
                        vacationJson.put("status", "fail");
                    }

                } catch (final Exception e) {
                    systemLog.put("type", "error");
                    systemLog.put("message", "Goal updating failed");
                    systemLog.put("userName", details.getName());
                    systemLog.put("user_id", userId);
                    systemLog.put("createdTs", dateFormat.format(date));
                    e.printStackTrace();
                } finally {
                    System.out.println("Finally");
                }
                // System.out.println("Elapsed time for update vacation goal: "
                // + (System.nanoTime() - goalStartTime) / 1000000 + " milli
                // seconds");

                return vacationJson;
            } else if (formType.equals("edit")) {
                try {
                    final ModelVacation vacationDetails = MongoDBConnection.goalcoll
                            .findOne("{'_id':#}", vacation.getGoal_id()).as(ModelVacation.class);
                    System.out.println("vacation goal id"+vacation.getGoal_id());
                    vacationJson.put("frequency", vacationDetails.getFrequency());
                    vacationJson.put("amountSave", vacationDetails.getAmountSave());
                    vacationJson.put("vacationYear", vacationDetails.getVacationYear());
                    vacationJson.put("plan_name", vacationDetails.getPlan_name());
                    vacationJson.put("goal_id", vacationDetails.getGoal_id());
                    vacationJson.put("goalFeasiblity", vacationDetails.getGoalFeasibility());
                    vacationJson.put("status", "success");
                    vacationJson.put("massage", "Fetching Data Sucessfull");
                    return vacationJson;
                } catch (final Exception e) {
                    e.printStackTrace();
                    vacationJson.put("massage", "Error In Fetching Details !!");
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return vacationJson;
        }
        // System.out.println("Elapsed time for edit vacation goal: " +
        // (System.nanoTime() - goalStartTime) / 1000000 + " milli seconds");

        return vacationJson;
    }
}