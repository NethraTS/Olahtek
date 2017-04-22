package com.mongorest.olahtek.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jongo.MongoCursor;
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
import com.mongorest.olahtek.model.HouseMedianValue;
import com.mongorest.olahtek.model.Housegoalmodel;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.RateAdjustmentFactor;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.States;
import com.mongorest.olahtek.model.User;

@Service("HouseGoalImpl")
@Transactional
public class HouseGoalImplementation {

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    DecimalFormat decimalFloat = new DecimalFormat("#.##");
    ObjectMapper jsonMapper = new ObjectMapper();
    public static double mortgageTemp = 0;
    public static double ownHouseMortgage = 0;
    public static String houseStatusTemp = "";

    @JsonCreator
    public JSONObject houseStatus(Housegoalmodel housegoalmodel) {
        final JSONObject responseToRestController = new JSONObject();
        final String user_id = housegoalmodel.getUser_id();
        // System.out.println("user_id..."+user_id);
        try {
            final String planName=housegoalmodel.getPlan_name();
            // System.out.println("planName in onload "+planName);
            final User details = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
            final FinPlan goalsData = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}", user_id,planName).as(FinPlan.class);
            final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(goalsData));
            // System.out.println("getting fin id "+finPlanJson.getString("_id"));
            final String finId = finPlanJson.getString("_id");
            //  System.out.println("in onload finId in houseee "+finId);
            JSONObject housedataJson = null;
            Boolean houseFlag = false;
            double sellingPrice = 0;
            double sellingPriceOwnHouse = 0;
            if (finPlanJson.getJSONArray("goals").length() != 0) {

                // System.out.println("inside no of
                // goals...."+finPlanJson.getJSONArray("goals")+details.getHouseStatus());
                if (!details.getHouseStatus().equals("Own")) {
                    final MongoCursor<Housegoalmodel> cursor = MongoDBConnection.goalcoll
                            .find("{user_id:#,fin_id:#}", user_id, finId).as(Housegoalmodel.class);

                    String goalid = null;
                    while (cursor.hasNext()) {
                        final Housegoalmodel fetch = cursor.next();
                        if (fetch.getGoalType().equals("Home")) {
                            houseFlag = true;
                            goalid = fetch.get_id();
                            break;
                        }

                    }

                    // //System.out.println("cursor "+cursor+"plan "+plan
                    // +goalid);
                    final Housegoalmodel housedata = MongoDBConnection.goalcoll.findOne("{_id:#}", goalid)
                            .as(Housegoalmodel.class);
                    //// System.out.println("housedata>>"+housedata);
                    housedataJson = new JSONObject(jsonMapper.writeValueAsString(housedata));
                    sellingPrice = housedata.getPrincipal_amount()
                            * Math.pow(1 + housedata.getAppreciationRate() / 100, housedata.getLoanPreriod());
                    //  System.out.println("sellingPrice " + sellingPrice);
                }

            }
            if (details.getHouseStatus().equals("Own")) {
                sellingPriceOwnHouse = details.getHouseValue() * Math
                        .pow(1 + details.getHouseAppreciationRate() / 100, details.getRemainingYearsMortgage());
            }
            responseToRestController.put("houseStatus", details.getHouseStatus());
            responseToRestController.put("user_id", user_id);
            responseToRestController.put("status", "success");
            responseToRestController.put("finplans", details.getFinplans());
            responseToRestController.put("houseFlag", houseFlag);
            responseToRestController.put("houseGoalPresence", housedataJson);
            responseToRestController.put("firstHousePrinciple", details.getHouseValue());
            responseToRestController.put("sellingPrice", decimalFloat.format(sellingPrice));
            responseToRestController.put("sellingPriceOwnHouse", sellingPriceOwnHouse);
        } catch (final Exception e) {
            e.printStackTrace();
            return responseToRestController;
        }
        return responseToRestController;

    }

    public JSONObject oldHouseRemaningMortgageCal(Housegoalmodel housegoalmodel) {
        final JSONObject responseToRestController = new JSONObject();
        final String user_id = housegoalmodel.getUser_id();
        //// System.out.println("user_id..."+user_id);
        try {
            //// System.out.println("inside method oldHouseRemaningMortgageCal"
            //// );
            final User details = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
            final String houseStatus = details.getHouseStatus();
            final FinPlan goalsData = MongoDBConnection.finplancol.findOne("{usr_id:#}", user_id).as(FinPlan.class);
            final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(goalsData));
            final String finId = finPlanJson.getString("_id");
            // System.out.println("finPlanJson...finId"+finId);
            JSONObject housedataJson = null;
            double remaningMortgageOriginal = 0;
            int mortgageOldHouseValue = 0;
            double morgagePaymentPerMonthHouse = 0;
            double principleAmountOldhouse = 0;
            int oldHousestartYear = 0;
            if (finPlanJson.getJSONArray("goals").length() != 0) {
                // //System.out.println("inside no of goals....");
                // Housegoalmodel
                // housedata=MongoDBConnection.goalcoll.findOne("{user_id:#}",user_id).as(Housegoalmodel.class);
                final MongoCursor<Housegoalmodel> cursor = MongoDBConnection.goalcoll
                        .find("{user_id:#,fin_id:#}", user_id, finId).as(Housegoalmodel.class);
                String goalid = null;
                while (cursor.hasNext()) {
                    final Housegoalmodel fetch = cursor.next();
                    // System.out.println("cursor.."+cursor.count());
                    // System.out.println("fetch..."+fetch.getGoalType());
                    if (fetch.getGoalType().equals("Home")) {
                        if (fetch.getFrequency().equals("firstHouse")) {
                            goalid = fetch.get_id();
                            break;
                        }
                    }

                }

                // //System.out.println("cursor "+cursor+"plan "+plan +goalid);
                final Housegoalmodel housedata = MongoDBConnection.goalcoll.findOne("{_id:#}", goalid)
                        .as(Housegoalmodel.class);
                housedataJson = new JSONObject(jsonMapper.writeValueAsString(housedata));
                //// System.out.println("housedataJson...."+housedataJson.getString("interest")+"
                //// "+housedataJson.getLong("principal_amount")+"
                //// "+housedataJson.getInt("downPaymentRate"));
                final double remainingYearsMortgageHouse = housedataJson.getInt("loanPreriod") * 12;
                final double remainingMortgageInterestRate = Double.parseDouble(housedataJson.getString("interest"))
                        / 12;
                principleAmountOldhouse = housedataJson.getLong("principal_amount");
                double downPaymentOldhouse = housedataJson.getInt("downPaymentRate");
                downPaymentOldhouse = downPaymentOldhouse / 100;
                downPaymentOldhouse = downPaymentOldhouse * principleAmountOldhouse;
                double morgageAtYearEndHouse = 0;
                morgagePaymentPerMonthHouse = Math.pow(1 + remainingMortgageInterestRate / 100,
                        remainingYearsMortgageHouse) * (remainingMortgageInterestRate / 100)
                        / (Math.pow(1 + remainingMortgageInterestRate / 100, remainingYearsMortgageHouse) - 1)
                        * (principleAmountOldhouse - downPaymentOldhouse);
                oldHousestartYear = housedataJson.getInt("startYear");
                final int secondHouseSyear = housegoalmodel.getStartYear();
                remaningMortgageOriginal = principleAmountOldhouse - downPaymentOldhouse;
                //// System.out.println("oldHousestartYear.."+oldHousestartYear+"remaningMortgageOriginal.."+remaningMortgageOriginal);
                for (int i = oldHousestartYear; i < secondHouseSyear; i++) {
                    morgageAtYearEndHouse = remaningMortgageOriginal
                            * Math.pow(1 + remainingMortgageInterestRate / 100, 12)
                            - (Math.pow(1 + remainingMortgageInterestRate / 100, 12) - 1)
                            * morgagePaymentPerMonthHouse / (remainingMortgageInterestRate / 100);
                    mortgageTemp = morgageAtYearEndHouse;
                    remaningMortgageOriginal = mortgageTemp;
                    //// System.out.println("mortgageTemp.."+mortgageTemp+"remaningMortgageOriginal..."+remaningMortgageOriginal);

                }
                //// System.out.println("remainingYearsMortgageHouse"+remainingYearsMortgageHouse+"
                //// "+remainingMortgageInterestRate+"
                //// "+morgagePaymentPerMonthHouse+" "+downPaymentOldhouse+"
                //// "+principleAmountOldhouse);
                mortgageOldHouseValue = (int) Math.round(remaningMortgageOriginal);
                morgagePaymentPerMonthHouse = (int) Math.round(morgagePaymentPerMonthHouse);
            }

            responseToRestController.put("status", "success");
            responseToRestController.put("remaningMortgageOldhouse", mortgageOldHouseValue);
            responseToRestController.put("monthlyMortgage", morgagePaymentPerMonthHouse);
            responseToRestController.put("firstHousePrinciple", principleAmountOldhouse);
            responseToRestController.put("oldHousestartYear", oldHousestartYear);
            responseToRestController.put("houseStatus", houseStatus);
        } catch (final Exception e) {
            e.printStackTrace();
            return responseToRestController;
        }
        return responseToRestController;

    }

    public JSONObject ownHouseRemaningMortgageCal(Housegoalmodel housegoalmodel) {
        final JSONObject responseToRestController = new JSONObject();
        final String user_id = housegoalmodel.getUser_id();
        //// System.out.println("user_id..."+user_id);
        try {
            //// System.out.println("inside method own HouseRemaningMortgageCal"
            //// );
            final User details = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
            // JSONObject userCollData = new
            // JSONObject(jsonMapper.writeValueAsString(details));
            final String[] createdTs = details.getCreatedTs().split("/");
            final int registrationYear = Integer.parseInt(createdTs[0]);
            final int houseStartYear = housegoalmodel.getStartYear();
            final long mortgageYears = details.getRemainingYearsMortgage();
            final double remainingMortgageOriginal = details.getRemainingMortgage();
            double remainingMortgageInterestRate = details.getRemainingMortgageInterestRate();
            remainingMortgageInterestRate = remainingMortgageInterestRate / 12;
            final double remainingMortgageInterestRateYear = remainingMortgageInterestRate;
            final double remainingYearsMortgage = mortgageYears * 12;
            double remainingMortgage = remainingMortgageOriginal;
            final double firstHousePrinciple = details.getHouseValue();
            // System.out.println("remainingMortgage"+remainingMortgage);
            double morgageAtYearEnd = 0;
            double morgagePaymentPerMonth = 0;
            int oldOwnHouseMortgage = 0;
            morgagePaymentPerMonth = Math.pow(1 + remainingMortgageInterestRate / 100, remainingYearsMortgage)
                    * (remainingMortgageInterestRate / 100)
                    / (Math.pow(1 + remainingMortgageInterestRate / 100, remainingYearsMortgage) - 1)
                    * remainingMortgageOriginal;
            if (houseStartYear >= registrationYear && houseStartYear < registrationYear + mortgageYears) {
                for (int i = registrationYear; i < houseStartYear; i++) {
                    morgageAtYearEnd = remainingMortgage
                            * Math.pow(1 + remainingMortgageInterestRateYear / 100, 12)
                            - (Math.pow(1 + remainingMortgageInterestRateYear / 100, 12) - 1)
                            * morgagePaymentPerMonth / (remainingMortgageInterestRateYear / 100);
                    ownHouseMortgage = morgageAtYearEnd;
                    remainingMortgage = ownHouseMortgage;
                    //// System.out.println("ownHouseMortgage.."+ownHouseMortgage+"remainingMortgage.."+remainingMortgage);
                    //// System.out.println("year.."+i+"remainingMortgage"+remainingMortgage+"morgagePaymentPerMonth
                    //// "+morgagePaymentPerMonth+"remainingYearsMortgage
                    //// "+remainingYearsMortgage+"remainingMortgageInterestRateYear.."+remainingMortgageInterestRateYear+"registrationYear.."+registrationYear);

                }
            }
            morgagePaymentPerMonth = (int) Math.round(morgagePaymentPerMonth);
            //// System.out.println("remainingMortgage"+remainingMortgage+"morgagePaymentPerMonth
            //// "+morgagePaymentPerMonth+"remainingYearsMortgage
            //// "+remainingYearsMortgage+"remainingMortgageInterestRateYear.."+remainingMortgageInterestRateYear+registrationYear);
            oldOwnHouseMortgage = (int) Math.round(remainingMortgage);
            responseToRestController.put("status", "success");
            responseToRestController.put("oldOwnHouseMortgage", oldOwnHouseMortgage);
            responseToRestController.put("monthlyMortgageOwn", morgagePaymentPerMonth);
            responseToRestController.put("firstHousePrinciple", firstHousePrinciple);

        } catch (final Exception e) {
            e.printStackTrace();
            return responseToRestController;
        }
        return responseToRestController;

    }

    public JSONObject insertHouseGoalData(Housegoalmodel housegoalmodel) {
        final JSONObject responseToRestController = new JSONObject();

        States states;
        String goal_id;
        final String user_id = housegoalmodel.getUser_id();
        String plan_name = housegoalmodel.getPlan_name();
        final String finId = housegoalmodel.getFin_id();
        final Calendar cal = Calendar.getInstance();
        final int currentYear = cal.get(Calendar.YEAR);
        long downPayment = 0;
        double downPaymentForLimits = 0;
        try {
            int startYear = housegoalmodel.getStartYear();
            final String actionHomeType = housegoalmodel.getActionHomeType();
            int remainingTaxableAmt = housegoalmodel.getRemainingTaxableAmt();
            int remainingTaxable = housegoalmodel.getRemainingTaxable();
            String frequency = housegoalmodel.getFrequency();
            double profitOrLossCal = housegoalmodel.getProfitOrLossCal();
            final String houseFlag = "0";
            double rentalIncomePerYear=(housegoalmodel.getRentalIncome()*12);
            double  deprectionAmount= housegoalmodel.getDeprectionAmount();
            states = MongoDBConnection.stateColl.findOne("{Name:#}", housegoalmodel.getLocation()).as(States.class);
            // rateFactors=MongoDBConnection.rateAdjustmentColl.findOne("{creditScore:#}",housegoalmodel.getCreditScore()).as(RateAdjustmentFactor.class);
            //// System.out.println("rateFactors"+rateFactors);
            if (states == null) {
                responseToRestController.put("message", "no such state in state collection");
                return responseToRestController;
            }
            final User user = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
            final String[] out = user.getCreatedTs().split("/");
            final int registrationYear = Integer.parseInt(out[0]);
            final String houseStatus = user.getHouseStatus();
            final double remaingMortgageYears = user.getRemainingYearsMortgage();
            final String originalHouseStatus = user.getHouseStatus();
            houseStatusTemp = originalHouseStatus;

            /*--------------------------------fetching Finplan_id--------------------*/
            if (actionHomeType.equals("insert")) {
                final float property_taxrate = 1;
                float pmi = (float) 0.625;
                // System.out.println("goalDuration.."+housegoalmodel.getGoalDuration());
                final int loanPreriod = housegoalmodel.getGoalDuration();
                final long homeInsurance = 500;
                final FinPlan getfinplan = MongoDBConnection.finplancol
                        .findOne("{usr_id:#,plan_name:#}", user_id, plan_name).as(FinPlan.class);
                /*---------------------------Checking finplan null or not---------------------------------------------*/
                final long principal_amount = housegoalmodel.getPrincipal_amount();
                final int downPaymentRate = housegoalmodel.getDownPaymentRate();
                //// System.out.println("Ranju
                //// downPaymentRate......."+downPaymentRate);
                /*-------------------- Calculating downpayment--------------------*/
                if (frequency.equals("Replace first house")) {
                    downPayment = downPaymentRate;
                } else {
                    downPayment = calDownPayment(downPaymentRate, principal_amount);
                }
                /*-------------------Calculating Mortgage Amount-------------------*/
                downPaymentForLimits = downPayment;
                final long mortgageAmount = principal_amount - downPayment;
                final String loanType = housegoalmodel.getLoanType();
                final String credit_score = housegoalmodel.getCreditScore();
                /*----------------------------------------------------------------------*/
                // float interest =
                // Float.parseFloat(states.getThirtyYearFixed());
                final float interest = calculateIntrestRate(housegoalmodel, loanType, credit_score);
                // System.out.println("interestTemp..?? "+interest +"
                // "+credit_score +" "+loanType);
                if (frequency.equals("Turn second house into a rental")) {
                    // System.out.println("Ranjitha :: inside profit loss
                    // calculation> > > "+loanPreriod+" "+interest +"
                    // "+housegoalmodel.getPrincipal_amount()+" "+downPayment);
                    final double remainingYearsMortgageHouse = loanPreriod * 12;
                    final double remainingMortgageInterestRate = interest / 12;
                    final double principleAmount = housegoalmodel.getPrincipal_amount();
                    final double downPaymentHouse = downPayment;
                    // System.out.println(" downPaymentHouse
                    // 1"+downPaymentHouse);
                    final double propertyValForRent = housegoalmodel.getPropertyValForRent();
                    final double rentalIncome = housegoalmodel.getRentalIncome();
                    rentalIncomePerYear=rentalIncome*12;
                    // System.out.println(
                    // "remainingMortgageInterestRate"+remainingMortgageInterestRate+"
                    // "+remainingYearsMortgageHouse+"downPaymentHouse>>
                    // "+downPaymentHouse+" "+propertyValForRent+"
                    // "+rentalIncome);
                    final double morgagePaymentPerMonthHouse = Math.pow(1 + remainingMortgageInterestRate / 100,
                            remainingYearsMortgageHouse) * (remainingMortgageInterestRate / 100)
                            / (Math.pow(1 + remainingMortgageInterestRate / 100, remainingYearsMortgageHouse) - 1)
                            * (principleAmount - downPaymentHouse);
                    final double valueOfProperty = propertyValForRent;
                    final double rentHousePropertyTax = (double) 1 / 100 * principleAmount;
                    final double rentalExpense = rentHousePropertyTax;
                    final double depreciation = valueOfProperty / 27.5;
                    //  final double totalRentCost = 12 * morgagePaymentPerMonthHouse + rentHousePropertyTax
                    // + rentalExpense;
                    final double totalRentCost = 12 * morgagePaymentPerMonthHouse +rentalExpense;
                    final double costWithDeprection = totalRentCost + depreciation;
                    final double cashFlow = rentalIncome * 12 - totalRentCost;
                    profitOrLossCal = rentalIncome * 12 - costWithDeprection;
                    deprectionAmount=costWithDeprection;
                    // System.out.println("Ranjitha
                    // ::morgagePaymentPerMonthHouse 2nd time
                    // "+morgagePaymentPerMonthHouse+" "+valueOfProperty+"
                    // "+rentHousePropertyTax+" "+depreciation+"
                    // "+totalRentCost+" "+costWithDeprection+" "+cashFlow+"
                    // "+profitOrLossCal);
                }
                final double exactAnual_morgage = calAnualMorgage(loanPreriod, interest, mortgageAmount);

                if (downPaymentRate > 20) {
                    pmi = 0.0f;
                }
                final double totalAnualhouseExpense = calTotalMorgage(exactAnual_morgage, homeInsurance, loanPreriod,
                        interest, mortgageAmount, pmi, property_taxrate, principal_amount);
                if (getfinplan == null) {
                    return responseToRestController;
                }
                //// System.out.println(" insert exactAnual_morgage >>
                //// "+exactAnual_morgage +" "+homeInsurance+" "+ loanPreriod+"
                //// "+interest+" "+mortgageAmount+" "+pmi+"
                //// "+property_taxrate+" "+principal_amount);
                //// System.out.println("totalAnualhouseExpense <<
                //// "+totalAnualhouseExpense);
                final String fin_id = getfinplan.get_id();
                final User Details = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
                final int userAge = Details.getAge();
                final String maritalStatus = getfinplan.getMarital_status();
                final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(getfinplan));
                final JSONArray assets = finPlanJson.getJSONArray("assests");
                //// System.out.println("ranju assets "+assets);
                final JSONArray tax = finPlanJson.getJSONArray("tax");
                final JSONArray limits = finPlanJson.getJSONArray("limits");
                final JSONArray deductions = finPlanJson.getJSONArray("deductions");
                final JSONArray fillingExemtion = finPlanJson.getJSONArray("fillingExemtion");
                final JSONArray userExpense = finPlanJson.getJSONArray("userExpense");
                final JSONArray kidBirthYear = finPlanJson.getJSONArray("kidBirthYear");
                final String finPlanProfileName = getfinplan.getProfile_name();
                final float property_tax = property_taxrate / 100 * principal_amount;
                final JSONArray expensesObj = new JSONArray();
                final JSONObject housing_expensedata = new JSONObject();
                housing_expensedata.put("mortgage_expense", totalAnualhouseExpense);
                housing_expensedata.put("pmi_expense", pmi);
                housing_expensedata.put("startYear", startYear);
                housing_expensedata.put("endYear", startYear + loanPreriod);
                housing_expensedata.put("home_insurance_expense", homeInsurance);
                housing_expensedata.put("downPayment", downPayment);
                housing_expensedata.put("property_tax", property_tax);
                housing_expensedata.put("property_value", principal_amount);
                housing_expensedata.put("interest", interest);
                housing_expensedata.put("remainingTaxableAmt", remainingTaxableAmt);
                housing_expensedata.put("remainingTaxable", remainingTaxable);
                housing_expensedata.put("frequency", frequency);
                housing_expensedata.put("actionHomeType", housegoalmodel.getActionHomeType());
                housing_expensedata.put("originalHouseStatus", originalHouseStatus);
                housing_expensedata.put("appreciationRate", housegoalmodel.getAppreciationRate());
                housing_expensedata.put("loanPreriod",loanPreriod);
                housing_expensedata.put("rentalIncomePerYear", rentalIncomePerYear);
                housing_expensedata.put("deprectionAmount",deprectionAmount);
                expensesObj.put(housing_expensedata);
                if (!frequency.equals("firstHouse")) {
                    JSONObject expenseJson = null;
                    if (!finPlanJson.isNull("expense")) {
                        expenseJson = finPlanJson.getJSONObject("expense");
                        if (!expenseJson.isNull("housing_expense")) {
                            JSONArray tempHouseArray = new JSONArray();
                            JSONObject tempHouseObj = new JSONObject();
                            tempHouseArray = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
                            for (int i = 0; i < tempHouseArray.length(); i++) {
                                tempHouseObj = tempHouseArray.getJSONObject(i);
                                expensesObj.put(tempHouseObj);
                            }
                        }
                    }
                }
                // System.out.println("to check the housing expense array in
                // insertion fuction
                // "+expensesObj+"length.."+expensesObj.length());

                if (frequency.equals("Replace first house")) {
                    downPaymentForLimits = 0;
                    if (housegoalmodel.getDownPaymentForAccumulation() > 0) {
                        downPaymentForLimits = housegoalmodel.getDownPaymentForAccumulation();
                    }
                }
                //// System.out.println("downPaymentvaluedownPayment
                //// .."+downPayment);
                for (int i = 0; i < limits.length(); i++) {
                    if (startYear > currentYear + 4) {
                        if (limits.getJSONObject(i).getInt("year") <= startYear) {
                            limits.getJSONObject(i).put("taxable",
                                    limits.getJSONObject(i).getInt("taxable") + downPaymentForLimits);
                        }
                        if (deductions.getJSONObject(i).getInt("year") == startYear) {
                            //// System.out.println("taxable
                            //// val.."+deductions.getJSONObject(i).getDouble("taxable"));
                            deductions.getJSONObject(i).put("taxable",
                                    downPayment + deductions.getJSONObject(i).getDouble("taxable"));
                        }

                    } else {
                        if (limits.getJSONObject(i).getInt("year") < startYear) {
                            limits.getJSONObject(i).put("saving",
                                    limits.getJSONObject(i).getInt("saving") + downPaymentForLimits);
                        }
                        if (deductions.getJSONObject(i).getInt("year") == startYear) {
                            deductions.getJSONObject(i).put("saving",
                                    downPayment + deductions.getJSONObject(i).getDouble("saving"));
                        }

                    }

                    if (fillingExemtion.getJSONObject(i).getInt("year") >= startYear
                            && fillingExemtion.getJSONObject(i).getInt("year") < startYear + loanPreriod) {
                        //// System.out.println("totalAnualhouseExpense
                        //// "+totalAnualhouseExpense+" year
                        //// "+fillingExemtion.getJSONObject(i).getInt("year")
                        //// );
                        userExpense.getJSONObject(i).put("mortgageExpense", totalAnualhouseExpense);
                        userExpense.getJSONObject(i).put("totalExpense",
                                userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                + userExpense.getJSONObject(i).getInt("kidExpense"));
                        //// System.out.println("123.."+userExpense.getJSONObject(i).getInt("mortgageExpense")+"
                        //// "+userExpense.getJSONObject(i).getInt("nonHousingExpense")+"rere"+userExpense.getJSONObject(i).getInt("kidExpense"));
                        if (houseStatus.equals("Own")) {
                            //// System.out.println("startYear?>>> "+startYear+"
                            //// "+userExpense.getJSONObject(i).getInt("housingExpense"));
                            if (fillingExemtion.getJSONObject(i).getInt("year") >= registrationYear && fillingExemtion
                                    .getJSONObject(i).getInt("year") < registrationYear + remaingMortgageYears) {
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("housingExpense")
                                        + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));
                                //// System.out.println("in own
                                //// "+userExpense.getJSONObject(i).getInt("mortgageExpense"));
                            }
                        }
                        if (startYear >= registrationYear && startYear < registrationYear + remaingMortgageYears
                                && frequency.equals("Replace first house") && originalHouseStatus.equals("Own")) {
                            if (fillingExemtion.getJSONObject(i).getInt("year") >= startYear && fillingExemtion
                                    .getJSONObject(i).getInt("year") < registrationYear + remaingMortgageYears) {
                                // System.out.println(" inside own house in impl
                                // ");
                                userExpense.getJSONObject(i).put("nonHousingExpense",
                                        userExpense.getJSONObject(i).getDouble("nonHousingExpense")
                                        - userExpense.getJSONObject(i).getDouble("nonHousingExpense") * 10
                                        / 100);
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));
                            }
                            //// System.out.println("userExpense with
                            //// own.."+userExpense.getJSONObject(i).getInt("nonHousingExpense"));
                        }

                    }

                    else if (fillingExemtion.getJSONObject(i).getInt("year") >= startYear + loanPreriod) {
                        userExpense.getJSONObject(i).put("totalExpense",
                                userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                + userExpense.getJSONObject(i).getInt("kidExpense"));
                    }

                }
                int spouseAge;
                final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                        .findOne("{user_id:#,profile_name:#}", user_id, finPlanProfileName).as(IncomeProfile.class);
                final JSONObject incomeProfileJson = new JSONObject(
                        jsonMapper.writeValueAsString(incomeProfileDetails));
                final JSONArray userIncome = incomeProfileJson.getJSONArray("user_income");
                JSONArray SpouseIncomeArray = new JSONArray();
                JSONArray combinedIncome = new JSONArray();
                int houseStartYear = housegoalmodel.getStartYear();
                if (frequency.equals("Turn first house into a rental")
                        || frequency.equals("Turn second house into a rental")) {
                    if (frequency.equals("Turn second house into a rental")) {
                        houseStartYear = houseStartYear + 1;
                    }
                    // System.out.println("rentalIncomePerYear ?? "+rentalIncomePerYear +" "+loanPreriod  +" "+deprectionAmount);
                    for (int i = 0; i < userIncome.length(); i++) {
                        if (userIncome.getJSONObject(i).getInt("year") >= houseStartYear
                                && userIncome.getJSONObject(i).getInt("year") <= housegoalmodel.getStartYear() +loanPreriod) {
                            /*                 if (profitOrLossCal != 0 && housegoalmodel.getRentalActivity().equals("yes")) {
                                //// System.out.println("profitOrLossCal
                                //// "+profitOrLossCal);
                                if (profitOrLossCal > 0) {
                                    userIncome.getJSONObject(i).put("value",
                                            userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal+rentalIncomePerYear);
                                } else if (profitOrLossCal < -25000) {
                                    //// System.out.println("profitOrLossCal1
                                    //// "+profitOrLossCal);
                                    userIncome.getJSONObject(i).put("value",
                                            userIncome.getJSONObject(i).getDouble("value") - 25000+rentalIncomePerYear);
                                } else {
                                    userIncome.getJSONObject(i).put("value",
                                            userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal+rentalIncomePerYear);
                                }

                            }
                            if (profitOrLossCal > 0 && housegoalmodel.getRentalActivity().equals("no")) {
                                userIncome.getJSONObject(i).put("value",
                                        userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal);
                            }*/
                            if(deprectionAmount!=0)
                            {
                                userIncome.getJSONObject(i).put("value",
                                        userIncome.getJSONObject(i).getDouble("value") -deprectionAmount+rentalIncomePerYear);
                            }
                        }
                    }
                    // System.out.println("in rent userIncome.."+userIncome);
                }
                if (maritalStatus.equals("Yes")) {
                    SpouseIncomeArray = incomeProfileJson.getJSONArray("spouse_income");
                    final IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
                    combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, SpouseIncomeArray);
                    spouseAge = getfinplan.getSpouseAge();
                } else {
                    combinedIncome = userIncome;
                    spouseAge = 0;
                }
                final int emergencyFundAmt = getfinplan.getEmergencyFundAmt();
                final boolean emergencyFundFlag = getfinplan.isEmergencyFundFlag();
                final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                        .findOne("{fin_id:#,goalType:#}", getfinplan.get_id(), "Retirement").as(RetirementGoal.class);
                int SpouseStartYear = 0;
                int userStartYear = 0;
                long retirement_expense = 0;
                if (retirementObj != null) {
                    userStartYear = Details.getBirthYear() + retirementObj.getRetirementAge();
                    retirement_expense = retirementObj.getRetirement_expense();
                    if (getfinplan.getMarital_status().equals("Yes") && Details.getMarital_status().equals("No")) {
                        SpouseStartYear = getfinplan.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                    } else if (Details.getMarital_status().equals("Yes")) {
                        SpouseStartYear = Details.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                    }
                } else {
                    userStartYear = Details.getBirthYear() + 70;
                    if (getfinplan.getMarital_status().equals("Yes") && Details.getMarital_status().equals("No")) {
                        SpouseStartYear = getfinplan.getSpouseBirthYear() + 70;
                    } else if (Details.getMarital_status().equals("Yes")) {
                        SpouseStartYear = Details.getSpouseBirthYear() + 70;
                    }
                }
                final JSONArray afterRetirementExpense = CalculationEngine.retirementExpenseArray(userExpense,
                        SpouseStartYear, userStartYear, getfinplan.getMarital_status(), retirement_expense);
                final JSONObject retirementData = new JSONObject();
                retirementData.put("spouseStartYear", SpouseStartYear);
                retirementData.put("userStartYear", userStartYear);
                String emergencyType = null;
                String monthsOfIncome = null;
                String monthsOfExpense = null;
                if (emergencyFundFlag == true) {
                    final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                            .findOne("{fin_id:#,goalType:#}", getfinplan.get_id(), "Emergency Fund")
                            .as(Emergencyfundmodel.class);
                    emergencyType = emergencyObj.getTimePeriod();
                    monthsOfIncome = emergencyObj.getMonthI();
                    monthsOfExpense = emergencyObj.getMonthE();
                }
                // System.out.println("calling sweeping of moey.....");
                final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, fin_id, combinedIncome,
                        SpouseIncomeArray, afterRetirementExpense, limits, maritalStatus, assets, tax, user_id,
                        fillingExemtion, userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions,
                        kidBirthYear, retirementData, retirementObj, expensesObj, emergencyType, monthsOfIncome,
                        monthsOfExpense, getfinplan.isRetirementFlag());
                final String status = result.getString("status");
                if (status.equals("success")) {
                    final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                    goal_id = "goal" + count.getGoal_id();
                    frequency = housegoalmodel.getFrequency();
                    if (frequency.equals("Replace first house") && originalHouseStatus.equals("Rent")) {
                        final MongoCursor<Housegoalmodel> cursor = MongoDBConnection.goalcoll
                                .find("{user_id:#,fin_id:#}", user_id, finId).as(Housegoalmodel.class);
                        String goalid = null;
                        while (cursor.hasNext()) {
                            final Housegoalmodel fetch = cursor.next();
                            if (fetch.getFrequency().equals("firstHouse")) {
                                goalid = fetch.get_id();
                                break;
                            }

                        }
                        MongoDBConnection.goalcoll.update("{'_id':#}", goalid).upsert().multi()
                        .with("{$set: {'houseFlag':'" + 1 + "'}}");

                    }
                    MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                    .with("{$inc: {goal_id: 1}}");
                    /*-------------------------Inserting Into House Goal Collection----*/
                    final BasicDBObject doc = new BasicDBObject("_id", goal_id)
                            .append("user_id", housegoalmodel.getUser_id()).append("fin_id", fin_id)
                            .append("goalType", housegoalmodel.getGoalType())
                            .append("userName", housegoalmodel.getUserName())
                            .append("location", housegoalmodel.getLocation()).append("city", housegoalmodel.getCity())
                            .append("downPaymentRate", housegoalmodel.getDownPaymentRate())
                            .append("principal_amount", housegoalmodel.getPrincipal_amount())
                            .append("property_taxrate", property_taxrate).append("homeInsurance", homeInsurance)
                            .append("pmi", pmi)
                            .append("FifteenYearFixed", Float.parseFloat(states.getFifteenYearFixed()))
                            .append("FiveOneARM", Float.parseFloat(states.getFiveOneARM()))
                            .append("creditScore", housegoalmodel.getCreditScore())
                            .append("plan_name", housegoalmodel.getPlan_name()).append("interest", interest)
                            .append("ThirtyYearFixed", Float.parseFloat(states.getThirtyYearFixed()))
                            .append("loanPreriod", loanPreriod).append("mortgage_expense", totalAnualhouseExpense)
                            .append("mortgageAmount", mortgageAmount).append("Anual_morgage", exactAnual_morgage)
                            .append("created_ts", dateFormat.format(date)).append("completedStatus", 1)
                            .append("modified_ts", dateFormat.format(date))
                            .append("remainingTaxableAmt", remainingTaxableAmt)
                            .append("remainingTaxable", remainingTaxable)
                            .append("frequency", housegoalmodel.getFrequency())
                            .append("houseMarketPrice", housegoalmodel.getHouseMarketPrice())
                            .append("brokerCommissionAmount", housegoalmodel.getBrokerCommissionAmount())
                            .append("otherCost", housegoalmodel.getOtherCost())
                            .append("rentalActivity", housegoalmodel.getRentalActivity()).append("loanType", loanType)
                            .append("rentalIncome", housegoalmodel.getRentalIncome())
                            .append("propertyValForRent", housegoalmodel.getPropertyValForRent())
                            .append("profitOrLossCal", housegoalmodel.getProfitOrLossCal())
                            .append("firstHousePrinciple", housegoalmodel.getFirstHousePrinciple())
                            .append("rentalExpense", housegoalmodel.getRentalExpense()).append("houseFlag", houseFlag)
                            .append("appreciationRate", housegoalmodel.getAppreciationRate())
                            .append("deprectionAmount",deprectionAmount)
                            .append("rentalIncomePerYear",rentalIncomePerYear)
                            .append("startYear", housegoalmodel.getStartYear());

                    doc.append("goalFeasibility", true);
                    MongoDBConnection.goalcoll.insert(doc);
                    downPayment = housegoalmodel.getDownPaymentRate();

                    updateFinplanForHouseGoal(fin_id, user_id, goal_id, totalAnualhouseExpense, exactAnual_morgage, pmi,
                            homeInsurance, property_taxrate, principal_amount, loanPreriod, downPayment,
                            housegoalmodel.getStartYear(), "insert", 0, interest, housegoalmodel.getFrequency(),
                            remainingTaxableAmt, originalHouseStatus, profitOrLossCal,
                            housegoalmodel.getRentalActivity(), remainingTaxable, housegoalmodel.getLoanType(),
                            housegoalmodel.getCreditScore(),housegoalmodel.getAppreciationRate(),deprectionAmount,rentalIncomePerYear);
                    MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi()
                    .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits
                            + ",'deductions':" + deductions + ",'userExpense':" + afterRetirementExpense
                            + "}}");

                    MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi()
                    .with("{$addToSet: {'goals':'" + goal_id + "'}}");
                    MongoDBConnection.incomeProfileCol
                    .update("{'user_id': '" + user_id + "','profile_name':'" + getfinplan.getProfile_name()
                    + "'}")
                    .upsert().multi().with("{$set: {'user_income':" + userIncome + ",'spouse_income':"
                            + SpouseIncomeArray + ",'combined_income':" + combinedIncome + "}}");
                    responseToRestController.put("status", "success");
                    responseToRestController.put("goal_id", goal_id);
                    responseToRestController.put("mortgage_expense", decimalFloat.format(totalAnualhouseExpense));
                    responseToRestController.put("mortgage_amount", decimalFloat.format(mortgageAmount));
                    responseToRestController.put("Anual_morgage", decimalFloat.format(exactAnual_morgage));
                    responseToRestController.put("property_tax", decimalFloat.format(property_taxrate));
                    responseToRestController.put("year", loanPreriod);
                    responseToRestController.put("principalAmount", housegoalmodel.getPrincipal_amount());
                    responseToRestController.put("downPaymentRate", housegoalmodel.getDownPaymentRate());
                    responseToRestController.put("interest", decimalFloat.format(interest));
                    responseToRestController.put("downPayment", downPayment);

                } else {
                    responseToRestController.put("status", "fail");
                }

            } else if (actionHomeType.equals("deleteGoal")) {
                goal_id = housegoalmodel.getGoal_id();
                final Housegoalmodel Housegoaldbdata = MongoDBConnection.goalcoll.findOne("{_id:#}", goal_id)
                        .as(Housegoalmodel.class);
                final FinPlan getfinplan = MongoDBConnection.finplancol.findOne("{_id:#}", Housegoaldbdata.getFin_id())
                        .as(FinPlan.class);
                final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(getfinplan));
                JSONArray expensesObj = null;
                final JSONObject finPlanExpense = finPlanJson.getJSONObject("expense");
                expensesObj = finPlanExpense.getJSONArray("housing_expense");
                JSONArray assets = finPlanJson.getJSONArray("assests");
                JSONArray tax = finPlanJson.getJSONArray("tax");
                final JSONArray limits = finPlanJson.getJSONArray("limits");
                final JSONArray deductions = finPlanJson.getJSONArray("deductions");
                final JSONArray fillingExemtion = finPlanJson.getJSONArray("fillingExemtion");
                final JSONArray userExpense = finPlanJson.getJSONArray("userExpense");
                final JSONArray kidBirthYear = finPlanJson.getJSONArray("kidBirthYear");
                int oldStartyear = Housegoaldbdata.getStartYear();
                double oldDownpayment = Housegoaldbdata.getDownPaymentRate() * Housegoaldbdata.getPrincipal_amount()
                        / 100;
                downPaymentForLimits = (long) oldDownpayment;
                final String fin_id = Housegoaldbdata.getFin_id();
                frequency = Housegoaldbdata.getFrequency();
                final User Details = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
                final int userAge = Details.getAge();
                final String maritalStatus = getfinplan.getMarital_status();
                //// System.out.println("inside deletegoal
                //// finPlanExpense"+finPlanExpense.getJSONArray("housing_expense")+"expensesObj"+expensesObj+expensesObj.length());
                JSONArray expensesObjTemp = new JSONArray();
                final String finPlanProfileName = getfinplan.getProfile_name();
                for (int k = 0; k < expensesObj.length(); k++) {
                    if (!goal_id.equals(expensesObj.getJSONObject(k).get("_id"))) {
                        expensesObjTemp = expensesObjTemp.put(expensesObj.getJSONObject(k));
                    }
                    //// System.out.println("expensesObjTemp..."+expensesObjTemp);
                }
                // //System.out.println("oldDownpayment "+oldDownpayment);

                // //System.out.println("oldDownpayment.."+oldDownpayment
                // +"expensesObjTemp"+expensesObjTemp+"count>> "+count);
                for (int y = 0; y < expensesObj.length(); y++) {
                    oldStartyear = expensesObj.getJSONObject(y).getInt("startYear");
                    oldDownpayment = expensesObj.getJSONObject(y).getDouble("downPayment");
                    downPaymentForLimits = oldDownpayment;
                    frequency = expensesObj.getJSONObject(y).getString("frequency");
                    if (frequency.equals("Replace first house") && originalHouseStatus.equals("Rent")) {
                        final MongoCursor<Housegoalmodel> cursor = MongoDBConnection.goalcoll
                                .find("{user_id:#,fin_id:#}", user_id, finId).as(Housegoalmodel.class);
                        String goalid = null;
                        while (cursor.hasNext()) {
                            final Housegoalmodel fetch = cursor.next();
                            if (fetch.getFrequency().equals("firstHouse")) {
                                goalid = fetch.get_id();
                                break;
                            }

                        }
                        MongoDBConnection.goalcoll.update("{'_id':#}", goalid).upsert().multi()
                        .with("{$set: {'houseFlag':'" + 0 + "'}}");

                    }
                    if (frequency.equals("Replace first house")) {
                        downPaymentForLimits = 0;
                    }
                    for (int i = 0; i < limits.length(); i++) {
                        if (oldStartyear > currentYear + 4) {
                            if (limits.getJSONObject(i).getInt("year") <= oldStartyear) {
                                limits.getJSONObject(i).put("taxable",
                                        limits.getJSONObject(i).getInt("taxable") - downPaymentForLimits);
                            }
                            if (deductions.getJSONObject(i).getInt("year") == oldStartyear) {
                                deductions.getJSONObject(i).put("taxable",
                                        deductions.getJSONObject(i).getDouble("taxable") - oldDownpayment);
                            }

                        } else {
                            if (limits.getJSONObject(i).getInt("year") < oldStartyear) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - downPaymentForLimits);
                            }
                            if (deductions.getJSONObject(i).getInt("year") == oldStartyear) {
                                deductions.getJSONObject(i).put("saving",
                                        deductions.getJSONObject(i).getDouble("saving") - oldDownpayment);
                            }

                        }
                        if (oldStartyear >= registrationYear && oldStartyear < registrationYear + remaingMortgageYears
                                && frequency.equals("Replace first house") && originalHouseStatus.equals("Own")) {
                            if (fillingExemtion.getJSONObject(i).getInt("year") >= oldStartyear && fillingExemtion
                                    .getJSONObject(i).getInt("year") < registrationYear + remaingMortgageYears) {
                                // System.out.println(" inside own house in impl
                                // "+userExpense.getJSONObject(i).getDouble("nonHousingExpense")+"
                                // ffffcc"
                                // +userExpense.getJSONObject(i).getDouble("nonHousingExpense")*10/100);
                                userExpense.getJSONObject(i).put("nonHousingExpense",
                                        userExpense.getJSONObject(i).getDouble("nonHousingExpense")
                                        + userExpense.getJSONObject(i - 1).getDouble("nonHousingExpense") * 10
                                        / 100);
                            }
                            //// System.out.println("11userExpense with
                            //// own..??"+userExpense.getJSONObject(i).getInt("nonHousingExpense"));
                        }
                        if (fillingExemtion.getJSONObject(i).getInt("year") >= oldStartyear) {
                            userExpense.getJSONObject(i).put("totalExpense",
                                    userExpense.getJSONObject(i).getInt("housingExpense")
                                    + userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                    + userExpense.getJSONObject(i).getInt("kidExpense"));
                            userExpense.getJSONObject(i).put("mortgageExpense", 0);
                        }

                    }
                }
                for (int z = 0; z < expensesObjTemp.length(); z++) {
                    final float property_taxrate = 1;
                    float pmi = (float) 0.625;
                    final int loanPreriod = expensesObjTemp.getJSONObject(z).getInt("loanPreriod");
                    final long homeInsurance = 500;
                    final int downPaymentInPercent = expensesObjTemp.getJSONObject(z).getInt("downPaymentInPercent");
                    final long principal_amount = expensesObjTemp.getJSONObject(z).getLong("property_value");
                    downPayment = (int) expensesObjTemp.getJSONObject(z).getDouble("downPayment");
                    //// System.out.println("Ranju
                    //// downPaymentRate......."+downPayment);
                    frequency = expensesObjTemp.getJSONObject(z).getString("frequency");
                    startYear = expensesObjTemp.getJSONObject(z).getInt("startYear");
                    downPaymentForLimits = downPayment;
                    /*-------------------- Calculating downpayment--------------------*/
                    if (frequency.equals("Replace first house")) {
                        downPaymentForLimits = 0;
                    }
                    /*-------------------Calculating Mortgage Amount-------------------*/
                    final long mortgageAmount = principal_amount - downPayment;
                    final String loanType = expensesObjTemp.getJSONObject(z).getString("loanType");
                    final String credit_score = expensesObjTemp.getJSONObject(z).getString("creditScore");
                    /*----------------------------------------------------------------------*/
                    // float interest =
                    // Float.parseFloat(states.getThirtyYearFixed());
                    final float interest = calculateIntrestRate(housegoalmodel, loanType, credit_score);
                    final double exactAnual_morgage = calAnualMorgage(loanPreriod, interest, mortgageAmount);

                    if (downPaymentInPercent > 20) {
                        pmi = 0.0f;
                    }
                    final double totalAnualhouseExpense = calTotalMorgage(exactAnual_morgage, homeInsurance,
                            loanPreriod, interest, mortgageAmount, pmi, property_taxrate, principal_amount);
                    //// System.out.println("downPaymentvaluedownPayment
                    //// .."+downPayment);
                    for (int i = 0; i < limits.length(); i++) {
                        if (startYear > currentYear + 4) {
                            if (limits.getJSONObject(i).getInt("year") <= startYear) {
                                limits.getJSONObject(i).put("taxable",
                                        limits.getJSONObject(i).getInt("taxable") + downPaymentForLimits);
                            }
                            if (deductions.getJSONObject(i).getInt("year") == startYear) {
                                // //System.out.println("taxable
                                // val.."+deductions.getJSONObject(i).getDouble("taxable"));
                                deductions.getJSONObject(i).put("taxable",
                                        downPayment + deductions.getJSONObject(i).getDouble("taxable"));
                            }

                        } else {
                            if (limits.getJSONObject(i).getInt("year") < startYear) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") + downPaymentForLimits);
                            }
                            if (deductions.getJSONObject(i).getInt("year") == startYear) {
                                deductions.getJSONObject(i).put("saving",
                                        downPayment + deductions.getJSONObject(i).getDouble("saving"));
                            }

                        }

                        if (fillingExemtion.getJSONObject(i).getInt("year") >= startYear
                                && fillingExemtion.getJSONObject(i).getInt("year") < startYear + loanPreriod) {
                            userExpense.getJSONObject(i).put("mortgageExpense", totalAnualhouseExpense);
                            userExpense.getJSONObject(i).put("totalExpense",
                                    userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                    + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                    + userExpense.getJSONObject(i).getInt("kidExpense"));

                            if (houseStatus.equals("Own")) {
                                if (fillingExemtion.getJSONObject(i).getInt("year") >= registrationYear
                                        && fillingExemtion.getJSONObject(i)
                                        .getInt("year") < registrationYear + remaingMortgageYears) {
                                    userExpense.getJSONObject(i).put("totalExpense",
                                            userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                            + userExpense.getJSONObject(i).getInt("housingExpense")
                                            + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                            + userExpense.getJSONObject(i).getInt("kidExpense"));
                                }
                            }

                        } else if (fillingExemtion.getJSONObject(i).getInt("year") >= startYear + loanPreriod) {
                            userExpense.getJSONObject(i).put("totalExpense",
                                    userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                    + userExpense.getJSONObject(i).getInt("kidExpense"));
                        }

                    }

                }
                int spouseAge;
                final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                        .findOne("{user_id:#,profile_name:#}", user_id, finPlanProfileName).as(IncomeProfile.class);
                final JSONObject incomeProfileJson = new JSONObject(
                        jsonMapper.writeValueAsString(incomeProfileDetails));
                final JSONArray userIncome = incomeProfileJson.getJSONArray("user_income");
                JSONArray SpouseIncomeArray = new JSONArray();
                JSONArray combinedIncome = new JSONArray();
                for (int k = 0; k < expensesObj.length(); k++) {
                    frequency = expensesObj.getJSONObject(k).getString("frequency");
                    if (frequency.equals("Turn first house into a rental")
                            || frequency.equals("Turn second house into a rental")) {
                        profitOrLossCal = expensesObj.getJSONObject(k).getDouble("profitOrLossCal");
                        deprectionAmount=expensesObj.getJSONObject(k).getDouble("deprectionAmount");
                        rentalIncomePerYear=expensesObj.getJSONObject(k).getDouble("rentalIncomePerYear");
                        final String rentalActivity = expensesObj.getJSONObject(k).getString("rentalActivity");
                        int houseStartYear = expensesObj.getJSONObject(k).getInt("startYear");
                        final int loanPreriod = expensesObj.getJSONObject(k).getInt("loanPreriod");
                        if (frequency.equals("Turn second house into a rental")) {
                            houseStartYear = houseStartYear + 1;
                        }
                        // System.out.println("profitOrLossCal ...>> in delete
                        // goal "+expensesObj +profitOrLossCal +" rentalActivity
                        // "+rentalActivity+"opp"+frequency);
                        for (int i = 0; i < userIncome.length(); i++) {
                            if (userIncome.getJSONObject(i).getInt("year") >= houseStartYear
                                    && userIncome.getJSONObject(i)
                                    .getInt("year") <= expensesObj.getJSONObject(k).getInt("startYear")
                                    + loanPreriod) {
                                /*              if (rentalActivity.equals("yes") || rentalActivity.equals("true")) {
                                    if (profitOrLossCal > 0) {
                                        //// System.out.println("oyooyoyoyo");
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") - profitOrLossCal);
                                    } else if (profitOrLossCal < -25000) {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") + 25000);
                                    } else {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") - profitOrLossCal);
                                    }
                                }
                                if (rentalActivity.equals("no")) {
                                    if (profitOrLossCal > 0) {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") - profitOrLossCal);
                                    }
                                }*/

                                userIncome.getJSONObject(i).put("value",
                                        userIncome.getJSONObject(i).getDouble("value")+deprectionAmount-rentalIncomePerYear);
                            }
                        }
                        // System.out.println("in deletegoal deleting all changes "+userIncome);
                    }
                }
                for (int a = 0; a < expensesObjTemp.length(); a++) {
                    //// System.out.println("expensesObjTemp in deleteeeeee
                    //// "+expensesObjTemp);
                    frequency = expensesObjTemp.getJSONObject(a).getString("frequency");
                    final int loanPreriod = expensesObjTemp.getJSONObject(a).getInt("loanPreriod");
                    final String rentalActivity = expensesObjTemp.getJSONObject(a).getString("rentalActivity");
                    profitOrLossCal = expensesObjTemp.getJSONObject(a).getDouble("profitOrLossCal");
                    deprectionAmount=expensesObjTemp.getJSONObject(a).getDouble("deprectionAmount");
                    rentalIncomePerYear=expensesObjTemp.getJSONObject(a).getDouble("rentalIncomePerYear");
                    int houseStartYear = expensesObjTemp.getJSONObject(a).getInt("startYear");
                    if (frequency.equals("Turn second house into a rental")) {
                        houseStartYear = houseStartYear + 1;
                    }

                    // System.out.println("profitOrLossCal "+profitOrLossCal);
                    if (frequency.equals("Turn first house into a rental")
                            || frequency.equals("Turn second house into a rental")) {
                        for (int i = 0; i < userIncome.length(); i++) {
                            if (userIncome.getJSONObject(i).getInt("year") >= houseStartYear
                                    && userIncome.getJSONObject(i)
                                    .getInt("year") <= expensesObjTemp.getJSONObject(a).getInt("startYear")
                                    + loanPreriod) {
                                /*          if (rentalActivity.equals("yes") || rentalActivity.equals("true")) {
                                    if (profitOrLossCal >= 0) {
                                        //// System.out.println("dhdghd...///");
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal);
                                    } else if (profitOrLossCal < -25000) {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") - 25000);
                                    } else {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal);
                                    }
                                }
                                if (profitOrLossCal > 0 && rentalActivity.equals("no")) {
                                    if (profitOrLossCal > 0) {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal);
                                    }
                                }*/

                                userIncome.getJSONObject(i).put("value",
                                        userIncome.getJSONObject(i).getDouble("value")-deprectionAmount+rentalIncomePerYear);

                            }
                        }
                        // System.out.println("in recreation
                        // userIncome.."+userIncome);
                    }
                }
                // System.out.println("in recreation out side loop userIncome.."+userIncome);
                if (maritalStatus.equals("Yes")) {
                    SpouseIncomeArray = incomeProfileJson.getJSONArray("spouse_income");
                    final IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
                    combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, SpouseIncomeArray);
                    spouseAge = getfinplan.getSpouseAge();
                } else {
                    combinedIncome = userIncome;
                    spouseAge = 0;
                }
                final int emergencyFundAmt = getfinplan.getEmergencyFundAmt();
                final boolean emergencyFundFlag = getfinplan.isEmergencyFundFlag();
                final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                        .findOne("{fin_id:#,goalType:#}", getfinplan.get_id(), "Retirement").as(RetirementGoal.class);
                int SpouseStartYear = 0;
                int userStartYear = 0;
                long retirement_expense = 0;
                if (retirementObj != null) {
                    userStartYear = Details.getBirthYear() + retirementObj.getRetirementAge();
                    retirement_expense = retirementObj.getRetirement_expense();
                    if (getfinplan.getMarital_status().equals("Yes") && Details.getMarital_status().equals("No")) {
                        SpouseStartYear = getfinplan.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                    } else if (Details.getMarital_status().equals("Yes")) {
                        SpouseStartYear = Details.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                    }

                } else {
                    userStartYear = Details.getBirthYear() + 70;
                    if (getfinplan.getMarital_status().equals("Yes") && Details.getMarital_status().equals("No")) {
                        SpouseStartYear = getfinplan.getSpouseBirthYear() + 70;
                    } else if (Details.getMarital_status().equals("Yes")) {
                        SpouseStartYear = Details.getSpouseBirthYear() + 70;
                    }
                }
                final JSONArray afterRetirementExpense = CalculationEngine.retirementExpenseArray(userExpense,
                        SpouseStartYear, userStartYear, getfinplan.getMarital_status(), retirement_expense);
                final JSONObject retirementData = new JSONObject();
                retirementData.put("spouseStartYear", SpouseStartYear);
                retirementData.put("userStartYear", userStartYear);

                String emergencyType = null;
                String monthsOfIncome = null;
                String monthsOfExpense = null;
                if (emergencyFundFlag == true) {
                    final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                            .findOne("{fin_id:#,goalType:#}", getfinplan.get_id(), "Emergency Fund")
                            .as(Emergencyfundmodel.class);
                    emergencyType = emergencyObj.getTimePeriod();
                    monthsOfIncome = emergencyObj.getMonthI();
                    monthsOfExpense = emergencyObj.getMonthE();
                }
                final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, fin_id, combinedIncome,
                        SpouseIncomeArray, afterRetirementExpense, limits, maritalStatus, assets, tax, user_id,
                        fillingExemtion, userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions,
                        kidBirthYear, retirementData, retirementObj, expensesObjTemp, emergencyType, monthsOfIncome,
                        monthsOfExpense, getfinplan.isRetirementFlag());

                final String status = result.getString("status");
                if (status.equals("success")) {
                    assets = result.getJSONArray("assets");
                    tax = result.getJSONArray("tax");
                    MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi()
                    .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits
                            + ",'deductions':" + deductions + ",'userExpense':" + afterRetirementExpense
                            + "}}");
                    MongoDBConnection.goalcoll.remove("{_id:#}", goal_id);

                    final JSONArray goalJsonArray = finPlanJson.getJSONArray("goals");
                    final JSONArray goalsArray = new JSONArray();
                    for (int j = 0; j < goalJsonArray.length(); j++) {
                        final String goalId = (String) goalJsonArray.get(j);
                        if (!goalId.equals(goal_id)) {
                            goalsArray.put(goalId);
                        } else {
                            continue;
                        }
                    }
                    MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi()
                    .with("{$set: {'goals':" + goalsArray + "}}");

                    MongoDBConnection.finplancol.update("{usr_id:#,plan_name:#}", user_id, plan_name)
                    .with("{$set: {'expense.housing_expense':" + expensesObjTemp + "}}");
                    //// System.out.println(" user_id "+user_id+"
                    //// "+getfinplan.getProfile_name());
                    MongoDBConnection.incomeProfileCol
                    .update("{'user_id': '" + user_id + "','profile_name':'" + getfinplan.getProfile_name()
                    + "'}")
                    .upsert().multi().with("{$set: {'user_income':" + userIncome + ",'spouse_income':"
                            + SpouseIncomeArray + ",'combined_income':" + combinedIncome + "}}");

                    responseToRestController.put("status", "success");
                } else {
                    responseToRestController.put("status", "fail");
                }

                //// System.out.println("count..."+count);
            }

            else {
                goal_id = housegoalmodel.getGoal_id();
                //// System.out.println("goal_id.."+goal_id+"plan_name
                //// "+plan_name);
                final Housegoalmodel Housegoaldbdata = MongoDBConnection.goalcoll.findOne("{_id:#}", goal_id)
                        .as(Housegoalmodel.class);
                final FinPlan getfinplan = MongoDBConnection.finplancol.findOne("{_id:#}", Housegoaldbdata.getFin_id())
                        .as(FinPlan.class);
                final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(getfinplan));
                JSONArray expensesObj = null;
                final JSONObject finPlanExpense = finPlanJson.getJSONObject("expense");
                expensesObj = finPlanExpense.getJSONArray("housing_expense");
                final JSONArray assets = finPlanJson.getJSONArray("assests");
                final JSONArray tax = finPlanJson.getJSONArray("tax");
                final JSONArray limits = finPlanJson.getJSONArray("limits");
                final JSONArray deductions = finPlanJson.getJSONArray("deductions");
                final JSONArray fillingExemtion = finPlanJson.getJSONArray("fillingExemtion");
                final JSONArray userExpense = finPlanJson.getJSONArray("userExpense");
                final JSONArray kidBirthYear = finPlanJson.getJSONArray("kidBirthYear");
                int oldStartyear = Housegoaldbdata.getStartYear();
                double oldDownpayment = Housegoaldbdata.getDownPaymentRate() * Housegoaldbdata.getPrincipal_amount()
                        / 100;
                downPaymentForLimits = (long) oldDownpayment;
                final String fin_id = Housegoaldbdata.getFin_id();
                frequency = Housegoaldbdata.getFrequency();
                final User Details = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
                final int userAge = Details.getAge();
                final String maritalStatus = getfinplan.getMarital_status();
                plan_name = Housegoaldbdata.getPlan_name();
                //// System.out.println("inside update
                //// finPlanExpense"+finPlanExpense.getJSONArray("housing_expense")+"expensesObj"+expensesObj+expensesObj.length());
                JSONArray expensesObjTemp = new JSONArray();
                JSONArray expensesObjTempIncome = new JSONArray();
                final String finPlanProfileName = getfinplan.getProfile_name();
                for (int k = 0; k < expensesObj.length(); k++) {
                    if (!goal_id.equals(expensesObj.getJSONObject(k).get("_id"))) {
                        expensesObjTemp = expensesObjTemp.put(expensesObj.getJSONObject(k));
                        expensesObjTempIncome = expensesObjTempIncome.put(expensesObj.getJSONObject(k));
                    }
                }
                // System.out.println("In update housing expense
                // expensesObjTemp.."+expensesObjTemp+"expensesObjTempIncome>>
                // "+expensesObjTempIncome);
                for (int y = 0; y < expensesObj.length(); y++) {
                    oldStartyear = expensesObj.getJSONObject(y).getInt("startYear");
                    oldDownpayment = expensesObj.getJSONObject(y).getDouble("downPayment");
                    downPaymentForLimits = oldDownpayment;
                    frequency = expensesObj.getJSONObject(y).getString("frequency");
                    if (frequency.equals("Replace first house")) {
                        downPaymentForLimits = 0;
                    }
                    for (int i = 0; i < limits.length(); i++) {
                        if (oldStartyear > currentYear + 4) {
                            if (limits.getJSONObject(i).getInt("year") <= oldStartyear) {
                                limits.getJSONObject(i).put("taxable",
                                        limits.getJSONObject(i).getInt("taxable") - downPaymentForLimits);
                            }
                            if (deductions.getJSONObject(i).getInt("year") == oldStartyear) {
                                deductions.getJSONObject(i).put("taxable",
                                        deductions.getJSONObject(i).getDouble("taxable") - oldDownpayment);
                            }

                        } else {
                            if (limits.getJSONObject(i).getInt("year") < oldStartyear) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") - downPaymentForLimits);
                            }
                            if (deductions.getJSONObject(i).getInt("year") == oldStartyear) {
                                deductions.getJSONObject(i).put("saving",
                                        deductions.getJSONObject(i).getDouble("saving") - oldDownpayment);
                            }

                        }
                        if (oldStartyear >= registrationYear && oldStartyear < registrationYear + remaingMortgageYears
                                && frequency.equals("Replace first house") && originalHouseStatus.equals("Own")) {
                            if (fillingExemtion.getJSONObject(i).getInt("year") >= oldStartyear && fillingExemtion
                                    .getJSONObject(i).getInt("year") < registrationYear + remaingMortgageYears) {
                                //// System.out.println(" inside own house in
                                //// impl
                                //// "+userExpense.getJSONObject(i).getDouble("nonHousingExpense")+"
                                //// ffffcc"
                                //// +userExpense.getJSONObject(i).getDouble("nonHousingExpense")*10/100);
                                userExpense.getJSONObject(i).put("nonHousingExpense",
                                        userExpense.getJSONObject(i).getDouble("nonHousingExpense")
                                        + userExpense.getJSONObject(i - 1).getDouble("nonHousingExpense") * 10
                                        / 100);
                            }
                            //// System.out.println("11userExpense with
                            //// own..??"+userExpense.getJSONObject(i).getInt("nonHousingExpense"));
                        }
                        if (fillingExemtion.getJSONObject(i).getInt("year") >= oldStartyear) {
                            userExpense.getJSONObject(i).put("totalExpense",
                                    userExpense.getJSONObject(i).getInt("housingExpense")
                                    + userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                    + userExpense.getJSONObject(i).getInt("kidExpense"));
                            userExpense.getJSONObject(i).put("mortgageExpense", 0);
                        }

                    }
                }
                for (int z = 0; z < expensesObjTemp.length(); z++) {
                    final float property_taxrate = 1;
                    float pmi = (float) 0.625;
                    final int loanPreriod = expensesObjTemp.getJSONObject(z).getInt("loanPreriod");
                    final long homeInsurance = 500;
                    final int downPaymentInPercent = expensesObjTemp.getJSONObject(z).getInt("downPaymentInPercent");
                    final long principal_amount = expensesObjTemp.getJSONObject(z).getLong("property_value");
                    downPayment = (int) expensesObjTemp.getJSONObject(z).getDouble("downPayment");
                    //// System.out.println("Ranju 123
                    //// downPaymentRate......."+downPayment);
                    frequency = expensesObjTemp.getJSONObject(z).getString("frequency");
                    startYear = expensesObjTemp.getJSONObject(z).getInt("startYear");
                    downPaymentForLimits = downPayment;
                    /*-------------------- Calculating downpayment--------------------*/
                    if (frequency.equals("Replace first house")) {
                        downPaymentForLimits = 0;
                    }
                    /*-------------------Calculating Mortgage Amount-------------------*/
                    final long mortgageAmount = principal_amount - downPayment;

                    /*----------------------------------------------------------------------*/
                    final String loanType = expensesObjTemp.getJSONObject(z).getString("loanType");
                    final String credit_score = expensesObjTemp.getJSONObject(z).getString("creditScore");
                    // float interest =
                    // Float.parseFloat(states.getThirtyYearFixed());
                    final float interest = calculateIntrestRate(housegoalmodel, loanType, credit_score);
                    // System.out.println("interest..1.."+interest +"
                    // "+credit_score+" "+loanType+" "+loanPreriod);
                    final double exactAnual_morgage = calAnualMorgage(loanPreriod, interest, mortgageAmount);

                    if (downPaymentInPercent > 20) {
                        pmi = 0.0f;
                    }
                    final double totalAnualhouseExpense = calTotalMorgage(exactAnual_morgage, homeInsurance,
                            loanPreriod, interest, mortgageAmount, pmi, property_taxrate, principal_amount);
                    //// System.out.println("downPaymentvaluedownPayment
                    //// .."+downPayment);
                    for (int i = 0; i < limits.length(); i++) {
                        if (startYear > currentYear + 4) {
                            if (limits.getJSONObject(i).getInt("year") <= startYear) {
                                limits.getJSONObject(i).put("taxable",
                                        limits.getJSONObject(i).getInt("taxable") + downPaymentForLimits);
                            }
                            if (deductions.getJSONObject(i).getInt("year") == startYear) {
                                //// System.out.println("taxable
                                //// val.."+deductions.getJSONObject(i).getDouble("taxable"));
                                deductions.getJSONObject(i).put("taxable",
                                        downPayment + deductions.getJSONObject(i).getDouble("taxable"));
                            }

                        } else {
                            if (limits.getJSONObject(i).getInt("year") < startYear) {
                                limits.getJSONObject(i).put("saving",
                                        limits.getJSONObject(i).getInt("saving") + downPaymentForLimits);
                            }
                            if (deductions.getJSONObject(i).getInt("year") == startYear) {
                                deductions.getJSONObject(i).put("saving",
                                        downPayment + deductions.getJSONObject(i).getDouble("saving"));
                            }

                        }
                        if (fillingExemtion.getJSONObject(i).getInt("year") >= startYear
                                && fillingExemtion.getJSONObject(i).getInt("year") < startYear + loanPreriod) {
                            userExpense.getJSONObject(i).put("mortgageExpense", totalAnualhouseExpense);
                            userExpense.getJSONObject(i).put("totalExpense",
                                    userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                    + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                    + userExpense.getJSONObject(i).getInt("kidExpense"));
                            if (houseStatus.equals("Own")) {
                                if (fillingExemtion.getJSONObject(i).getInt("year") >= registrationYear
                                        && fillingExemtion.getJSONObject(i)
                                        .getInt("year") < registrationYear + remaingMortgageYears) {
                                    userExpense.getJSONObject(i).put("totalExpense",
                                            userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                            + userExpense.getJSONObject(i).getInt("housingExpense")
                                            + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                            + userExpense.getJSONObject(i).getInt("kidExpense"));
                                }
                            }

                        } else if (fillingExemtion.getJSONObject(i).getInt("year") >= startYear + loanPreriod) {
                            userExpense.getJSONObject(i).put("totalExpense",
                                    userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                    + userExpense.getJSONObject(i).getInt("kidExpense"));
                        }

                    }

                }
                // user_id=housegoalmodel.getPlan_name();
                // plan_name = housegoalmodel.getPlan_name();
                frequency = housegoalmodel.getFrequency();
                startYear = housegoalmodel.getStartYear();
                final float property_taxrate = 1;
                float pmi = 0;
                int loanPreriod = housegoalmodel.getGoalDuration();
                final long homeInsurance = 500;
                final long principal_amount = housegoalmodel.getPrincipal_amount();
                final int downPaymentRate = housegoalmodel.getDownPaymentRate();
                long downPaymentRateTemp = housegoalmodel.getDownPaymentRate();
                String rentalActivity = housegoalmodel.getRentalActivity();
                int rentalIncome = housegoalmodel.getRentalIncome();
                int propertyValForRent = housegoalmodel.getPropertyValForRent();
                profitOrLossCal = housegoalmodel.getProfitOrLossCal();
                remainingTaxableAmt = housegoalmodel.getRemainingTaxableAmt();
                remainingTaxable = housegoalmodel.getRemainingTaxable();
                deprectionAmount=housegoalmodel.getDeprectionAmount();
                rentalIncomePerYear=rentalIncome*12;
                final double property_tax=(housegoalmodel.getProperty_taxrate()/100)*principal_amount;
                //// System.out.println("Ranju downPaymentRateTemp
                //// "+remainingTaxableAmt+"remainingTaxable
                //// "+remainingTaxable);
                if (!frequency.equals("Replace first house")) {
                    downPaymentRateTemp = calDownPayment(downPaymentRate, principal_amount);
                    if (housegoalmodel.getDownPaymentRate() > 20) {
                        pmi = 0.0f;
                    } else {
                        pmi = 0.625f;
                    }
                }
                downPaymentForLimits = downPaymentRateTemp;
                if (frequency.equals("Replace first house")) {
                    downPaymentForLimits = 0;
                }
                //// System.out.println("Ran...exactAnual_morgage"+"loanPreriod.."+loanPreriod+"principal_amount.."+principal_amount+"downPayment
                //// "+downPayment+"downPaymentRateTemp"+downPaymentRateTemp);
                final long mortgageAmount = principal_amount - downPaymentRateTemp;
                // float interest =
                // Float.parseFloat(states.getThirtyYearFixed());
                final String loanType = housegoalmodel.getLoanType();
                final String credit_score = housegoalmodel.getCreditScore();
                final float interest = calculateIntrestRate(housegoalmodel, loanType, credit_score);
                // System.out.println("interest..2.."+interest +"
                // "+credit_score+" "+loanType+" "+loanPreriod);
                final double exactAnual_morgage = calAnualMorgage(loanPreriod, interest, mortgageAmount);
                //// System.out.println("Bala...exactAnual_morgage"+exactAnual_morgage+"loanPreriod.."+loanPreriod+"principal_amount.."+principal_amount+"downPayment
                //// "+downPayment+"mortgageAmount "+mortgageAmount);
                final double totalAnualhouseExpense = calTotalMorgage(exactAnual_morgage, homeInsurance, loanPreriod,
                        interest, mortgageAmount, pmi, property_taxrate, principal_amount);
                // //System.out.println(" Update exactAnual_morgage >>
                // "+exactAnual_morgage +" "+homeInsurance+" "+ loanPreriod+"
                // "+interest+" "+mortgageAmount+" "+pmi+" "+property_taxrate+"
                // "+principal_amount);
                //// System.out.println("Update totalAnualhouseExpense <<
                // "+totalAnualhouseExpense);
                final long propertyTax = (long) (property_taxrate * housegoalmodel.getPrincipal_amount() / 100);
                profitOrLossCal = housegoalmodel.getProfitOrLossCal();
                if (frequency.equals("Turn second house into a rental")) {
                    // //System.out.println("Ranjitha :: inside profit loss
                    // calculation> > > "+loanPreriod+" "+interest +"
                    // "+housegoalmodel.getPrincipal_amount()+" "+downPayment);
                    final double remainingYearsMortgageHouse = loanPreriod * 12;
                    final double remainingMortgageInterestRate = interest / 12;
                    final double principleAmount = housegoalmodel.getPrincipal_amount();
                    final double downPaymentHouse = downPaymentRateTemp;
                    // //System.out.println(" downPaymentHouse
                    // 1"+downPaymentHouse);
                    propertyValForRent = housegoalmodel.getPropertyValForRent();
                    rentalIncome = housegoalmodel.getRentalIncome();
                    rentalIncomePerYear=rentalIncome*12;
                    //// System.out.println(
                    //// "remainingMortgageInterestRate"+remainingMortgageInterestRate+"
                    //// "+remainingYearsMortgageHouse+"downPaymentHouse>>
                    //// "+downPaymentHouse+" "+propertyValForRent+"
                    //// "+rentalIncome);
                    final double morgagePaymentPerMonthHouse = Math.pow(1 + remainingMortgageInterestRate / 100,
                            remainingYearsMortgageHouse) * (remainingMortgageInterestRate / 100)
                            / (Math.pow(1 + remainingMortgageInterestRate / 100, remainingYearsMortgageHouse) - 1)
                            * (principleAmount - downPaymentHouse);
                    final double valueOfProperty = propertyValForRent;
                    final double rentHousePropertyTax = (double) 1 / 100 * principleAmount;
                    final double rentalExpense = (double) housegoalmodel.getRentalExpense() / 100 * principleAmount;
                    final double depreciation = valueOfProperty / 27.5;
                    //final double totalRentCost = 12 * morgagePaymentPerMonthHouse + rentHousePropertyTax
                    //  + rentalExpense;
                    final double totalRentCost = 12 * morgagePaymentPerMonthHouse +rentalExpense;
                    final double costWithDeprection = totalRentCost + depreciation;
                    final double cashFlow = rentalIncome * 12 - totalRentCost;
                    profitOrLossCal = rentalIncome * 12 - costWithDeprection;
                    deprectionAmount=costWithDeprection;
                    //// System.out.println("Ranjitha
                    //// ::morgagePaymentPerMonthHouse 2nd time
                    //// "+morgagePaymentPerMonthHouse+" "+valueOfProperty+"
                    //// "+rentHousePropertyTax+" "+depreciation+"
                    //// "+totalRentCost+" "+costWithDeprection+" "+cashFlow+"
                    //// "+profitOrLossCal);
                }

                final JSONObject housing_expensedata = new JSONObject();
                // System.out.println("update....>>
                // remainingTaxableAmt"+remainingTaxableAmt+"
                // "+remainingTaxable);
                housing_expensedata.put("mortgage_expense", totalAnualhouseExpense);
                housing_expensedata.put("pmi_expense", pmi);
                housing_expensedata.put("startYear", startYear);
                housing_expensedata.put("endYear", startYear + loanPreriod);
                housing_expensedata.put("home_insurance_expense", homeInsurance);
                housing_expensedata.put("downPayment", downPaymentRateTemp);
                housing_expensedata.put("property_tax", propertyTax);
                housing_expensedata.put("property_value", principal_amount);
                housing_expensedata.put("interest", interest);
                housing_expensedata.put("frequency", frequency);
                housing_expensedata.put("remainingTaxableAmt", remainingTaxableAmt);
                housing_expensedata.put("remainingTaxable", remainingTaxable);
                housing_expensedata.put("originalHouseStatus", originalHouseStatus);
                housing_expensedata.put("rentalActivity", rentalActivity);
                housing_expensedata.put("rentalIncome", rentalIncome);
                housing_expensedata.put("propertyValForRent", propertyValForRent);
                housing_expensedata.put("profitOrLossCal", profitOrLossCal);
                housing_expensedata.put("loanPreriod", loanPreriod);
                housing_expensedata.put("loanType", loanType);
                housing_expensedata.put("creditScore", credit_score);
                housing_expensedata.put("_id", housegoalmodel.getGoal_id());
                housing_expensedata.put("appreciationRate", housegoalmodel.getAppreciationRate());
                housing_expensedata.put("rentalIncomePerYear",rentalIncomePerYear);
                housing_expensedata.put("deprectionAmount",deprectionAmount);
                housing_expensedata.put("property_tax", property_tax);
                expensesObjTemp.put(housing_expensedata);
                // System.out.println("expensesObjTemp in the update..in the
                // last "+expensesObjTemp);
                for (int i = 0; i < limits.length(); i++) {
                    if (startYear > currentYear + 4) {
                        if (limits.getJSONObject(i).getInt("year") <= startYear) {
                            limits.getJSONObject(i).put("taxable",
                                    limits.getJSONObject(i).getInt("taxable") + downPaymentForLimits);
                        }
                        if (deductions.getJSONObject(i).getInt("year") == startYear) {
                            //// System.out.println("taxable
                            //// val.."+deductions.getJSONObject(i).getDouble("taxable"));
                            deductions.getJSONObject(i).put("taxable",
                                    downPaymentRateTemp + deductions.getJSONObject(i).getDouble("taxable"));
                        }

                    } else {
                        if (limits.getJSONObject(i).getInt("year") < startYear) {
                            limits.getJSONObject(i).put("saving",
                                    limits.getJSONObject(i).getInt("saving") + downPaymentForLimits);
                        }
                        if (deductions.getJSONObject(i).getInt("year") == startYear) {
                            deductions.getJSONObject(i).put("saving",
                                    downPaymentRateTemp + deductions.getJSONObject(i).getDouble("saving"));
                        }

                    }
                    if (fillingExemtion.getJSONObject(i).getInt("year") >= startYear
                            && fillingExemtion.getJSONObject(i).getInt("year") < startYear + loanPreriod) {
                        userExpense.getJSONObject(i).put("mortgageExpense", totalAnualhouseExpense);
                        userExpense.getJSONObject(i).put("totalExpense",
                                userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                + userExpense.getJSONObject(i).getInt("kidExpense"));
                        if (houseStatus.equals("Own")) {
                            if (fillingExemtion.getJSONObject(i).getInt("year") >= registrationYear && fillingExemtion
                                    .getJSONObject(i).getInt("year") < registrationYear + remaingMortgageYears) {
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("housingExpense")
                                        + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));
                            }
                        }
                        if (startYear >= registrationYear && startYear < registrationYear + remaingMortgageYears
                                && frequency.equals("Replace first house") && originalHouseStatus.equals("Own")) {
                            if (fillingExemtion.getJSONObject(i).getInt("year") >= startYear && fillingExemtion
                                    .getJSONObject(i).getInt("year") < registrationYear + remaingMortgageYears) {
                                //// System.out.println(" inside own house in
                                //// impl ");
                                userExpense.getJSONObject(i).put("nonHousingExpense",
                                        userExpense.getJSONObject(i).getDouble("nonHousingExpense")
                                        - userExpense.getJSONObject(i).getDouble("nonHousingExpense") * 10
                                        / 100);
                                userExpense.getJSONObject(i).put("totalExpense",
                                        userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                        + userExpense.getJSONObject(i).getInt("mortgageExpense")
                                        + userExpense.getJSONObject(i).getInt("kidExpense"));
                            }
                            //// System.out.println("userExpense with
                            //// own.."+userExpense.getJSONObject(i).getInt("nonHousingExpense"));
                        }

                    } else if (fillingExemtion.getJSONObject(i).getInt("year") >= startYear + loanPreriod) {
                        userExpense.getJSONObject(i).put("totalExpense",
                                userExpense.getJSONObject(i).getInt("nonHousingExpense")
                                + userExpense.getJSONObject(i).getInt("kidExpense"));
                    }

                }
                int spouseAge;
                final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                        .findOne("{user_id:#,profile_name:#}", user_id, finPlanProfileName).as(IncomeProfile.class);
                final JSONObject incomeProfileJson = new JSONObject(
                        jsonMapper.writeValueAsString(incomeProfileDetails));
                final JSONArray userIncome = incomeProfileJson.getJSONArray("user_income");
                JSONArray SpouseIncomeArray = new JSONArray();
                JSONArray combinedIncome = new JSONArray();
                for (int k = 0; k < expensesObj.length(); k++) {
                    //// System.out.println("expensesObj in update
                    //// ??.."+expensesObj);
                    frequency = expensesObj.getJSONObject(k).getString("frequency");
                    loanPreriod = expensesObj.getJSONObject(k).getInt("loanPreriod");
                    if (frequency.equals("Turn first house into a rental")
                            || frequency.equals("Turn second house into a rental")) {
                        profitOrLossCal = expensesObj.getJSONObject(k).getDouble("profitOrLossCal");
                        rentalActivity = expensesObj.getJSONObject(k).getString("rentalActivity");
                        deprectionAmount=expensesObj.getJSONObject(k).getDouble("deprectionAmount");
                        rentalIncomePerYear=expensesObj.getJSONObject(k).getDouble("rentalIncomePerYear");
                        //// System.out.println("profitOrLossCal //...>>
                        //// "+expensesObj +profitOrLossCal +" rentalActivity
                        //// "+rentalActivity);
                        int houseStartYear = expensesObj.getJSONObject(k).getInt("startYear");
                        if (frequency.equals("Turn second house into a rental")) {
                            houseStartYear = houseStartYear + 1;
                        }
                        for (int i = 0; i < userIncome.length(); i++) {
                            // //System.out.println("profit 1111
                            // "+profitOrLossCal);
                            if (userIncome.getJSONObject(i).getInt("year") >= houseStartYear
                                    && userIncome.getJSONObject(i)
                                    .getInt("year") <= expensesObj.getJSONObject(k).getInt("startYear")
                                    + loanPreriod) {
                                if (rentalActivity.equals("yes")) {
                                    /*            if (profitOrLossCal > 0) {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") - profitOrLossCal);
                                    } else if (profitOrLossCal < -25000) {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") + 25000);
                                    } else {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") - profitOrLossCal);
                                    }
                                }
                                if (rentalActivity.equals("no")) {
                                    if (profitOrLossCal > 0) {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") - profitOrLossCal);
                                    }
                                }*/
                                    userIncome.getJSONObject(i).put("value",
                                            userIncome.getJSONObject(i).getDouble("value") +deprectionAmount-rentalIncomePerYear);
                                }
                            }
                        }
                        //// System.out.println("in deletegoal rent 787
                        //// userIncome.."+userIncome);

                    }
                }

                for (int a = 0; a < expensesObjTempIncome.length(); a++) {
                    //// System.out.println("expensesObjTempIncome in update
                    //// ??.."+expensesObjTempIncome);
                    frequency = expensesObjTempIncome.getJSONObject(a).getString("frequency");

                    if (frequency.equals("Turn first house into a rental")
                            || frequency.equals("Turn second house into a rental")) {
                        rentalActivity = expensesObjTempIncome.getJSONObject(a).getString("rentalActivity");
                        int houseStartYear = expensesObjTempIncome.getJSONObject(a).getInt("startYear");
                        if (frequency.equals("Turn second house into a rental")) {
                            houseStartYear = houseStartYear + 1;
                        }
                        loanPreriod = expensesObjTempIncome.getJSONObject(a).getInt("loanPreriod");
                        profitOrLossCal = expensesObjTempIncome.getJSONObject(a).getDouble("profitOrLossCal");
                        deprectionAmount=expensesObjTempIncome.getJSONObject(a).getDouble("deprectionAmount");
                        rentalIncomePerYear=expensesObjTempIncome.getJSONObject(a).getDouble("rentalIncomePerYear");
                        for (int i = 0; i < userIncome.length(); i++) {
                            //// System.out.println("profit///
                            //// "+profitOrLossCal);
                            if (userIncome.getJSONObject(i).getInt("year") >= houseStartYear
                                    && userIncome.getJSONObject(i).getInt(
                                            "year") <= expensesObjTempIncome.getJSONObject(a).getInt("startYear")
                                            + loanPreriod) {
                                /*           if (rentalActivity.equals("yes")) {
                                    if (profitOrLossCal > 0) {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal);
                                    } else if (profitOrLossCal < -25000) {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") - 25000);
                                    } else {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal);
                                    }
                                }
                                if (profitOrLossCal > 0 && rentalActivity.equals("no")) {
                                    if (profitOrLossCal > 0) {
                                        userIncome.getJSONObject(i).put("value",
                                                userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal);
                                    }
                                }*/
                                userIncome.getJSONObject(i).put("value",
                                        userIncome.getJSONObject(i).getDouble("value") + rentalIncomePerYear-deprectionAmount);

                            }
                        }
                        //// System.out.println("in recreation
                        //// userIncome.."+userIncome);
                    }
                }

                frequency = housegoalmodel.getFrequency();
                rentalActivity = housegoalmodel.getRentalActivity();
                rentalIncome = housegoalmodel.getRentalIncome();
                propertyValForRent = housegoalmodel.getPropertyValForRent();
                profitOrLossCal = housegoalmodel.getProfitOrLossCal();
                deprectionAmount=housegoalmodel.getDeprectionAmount();
                rentalIncomePerYear=(housegoalmodel.getRentalIncome()*12);
                //// System.out.println("in update .. "+profitOrLossCal
                //// +frequency+housegoalmodel.getStartYear()+rentalActivity);

                if (frequency.equals("Turn first house into a rental")
                        || frequency.equals("Turn second house into a rental")) {
                    int houseStartYear = housegoalmodel.getStartYear();
                    if (frequency.equals("Turn second house into a rental")) {
                        // //System.out.println("Ranjitha :: inside profit loss
                        // calculation> > > "+loanPreriod+" "+interest +"
                        // "+housegoalmodel.getPrincipal_amount()+"
                        // "+downPayment);
                        houseStartYear = houseStartYear + 1;
                        loanPreriod = housegoalmodel.getGoalDuration();
                        final double remainingYearsMortgageHouse = loanPreriod * 12;
                        final double remainingMortgageInterestRate = interest / 12;
                        final double principleAmount = housegoalmodel.getPrincipal_amount();
                        final double downPaymentHouse = downPaymentRateTemp;
                        // //System.out.println(" downPaymentHouse
                        // 1"+downPaymentHouse);
                        propertyValForRent = housegoalmodel.getPropertyValForRent();
                        rentalIncome = housegoalmodel.getRentalIncome();
                        rentalIncomePerYear=rentalIncome*12;
                        //// System.out.println(
                        //// "remainingMortgageInterestRate"+remainingMortgageInterestRate+"
                        //// "+remainingYearsMortgageHouse+"downPaymentHouse>>
                        //// "+downPaymentHouse+" "+propertyValForRent+"
                        //// "+rentalIncome);
                        final double morgagePaymentPerMonthHouse = Math.pow(1 + remainingMortgageInterestRate / 100,
                                remainingYearsMortgageHouse) * (remainingMortgageInterestRate / 100)
                                / (Math.pow(1 + remainingMortgageInterestRate / 100, remainingYearsMortgageHouse)
                                        - 1)
                                * (principleAmount - downPaymentHouse);
                        final double valueOfProperty = propertyValForRent;
                        final double rentHousePropertyTax = (double) 1 / 100 * principleAmount;
                        final double rentalExpense = (double) housegoalmodel.getRentalExpense() / 100
                                * principleAmount;
                        //// System.out.println("////rentalExpense"+rentalExpense);
                        final double depreciation = valueOfProperty / 27.5;
                        /*                        final double totalRentCost = 12 * morgagePaymentPerMonthHouse + rentHousePropertyTax
                                + rentalExpense;*/
                        final double totalRentCost = 12 * morgagePaymentPerMonthHouse +rentalExpense;
                        final double costWithDeprection = totalRentCost + depreciation;
                        final double cashFlow = rentalIncome * 12 - totalRentCost;
                        profitOrLossCal = rentalIncome * 12 - costWithDeprection;
                        deprectionAmount=costWithDeprection;
                        //// System.out.println("Ranjitha
                        //// ::morgagePaymentPerMonthHouse 2nd time
                        //// "+morgagePaymentPerMonthHouse+" "+valueOfProperty+"
                        //// "+rentHousePropertyTax+" "+depreciation+"
                        //// "+totalRentCost+" "+costWithDeprection+"
                        //// "+cashFlow+" "+profitOrLossCal);
                    }

                    for (int i = 0; i < userIncome.length(); i++) {
                        //// System.out.println("profit lassst
                        //// "+profitOrLossCal);
                        if (userIncome.getJSONObject(i).getInt("year") >= houseStartYear && userIncome.getJSONObject(i)
                                .getInt("year") <= housegoalmodel.getStartYear() + loanPreriod) {
                            /*            if (rentalActivity.equals("yes") || rentalActivity.equals("true")) {
                                // //System.out.println("123 ..");
                                if (profitOrLossCal > 0) {
                                    userIncome.getJSONObject(i).put("value",
                                            userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal);
                                } else if (profitOrLossCal < -25000) {
                                    //// System.out.println("12133..");
                                    userIncome.getJSONObject(i).put("value",
                                            userIncome.getJSONObject(i).getDouble("value") - 25000);
                                } else {
                                    userIncome.getJSONObject(i).put("value",
                                            userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal);
                                }
                            }
                            if (profitOrLossCal > 0 && housegoalmodel.getRentalActivity().equals("no")) {
                                if (profitOrLossCal > 0) {
                                    userIncome.getJSONObject(i).put("value",
                                            userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal);
                                }
                            }*/
                            userIncome.getJSONObject(i).put("value",
                                    userIncome.getJSONObject(i).getDouble("value")-deprectionAmount+rentalIncomePerYear);

                        }
                    }
                    // System.out.println("in rent update last
                    // userIncome.."+userIncome);
                }

                if (maritalStatus.equals("Yes")) {
                    SpouseIncomeArray = incomeProfileJson.getJSONArray("spouse_income");
                    final IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
                    combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, SpouseIncomeArray);
                    spouseAge = getfinplan.getSpouseAge();
                } else {
                    combinedIncome = userIncome;
                    spouseAge = 0;
                }
                final int emergencyFundAmt = getfinplan.getEmergencyFundAmt();
                final boolean emergencyFundFlag = getfinplan.isEmergencyFundFlag();
                final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                        .findOne("{fin_id:#,goalType:#}", getfinplan.get_id(), "Retirement").as(RetirementGoal.class);
                int SpouseStartYear = 0;
                int userStartYear = 0;
                long retirement_expense = 0;
                if (retirementObj != null) {
                    userStartYear = Details.getBirthYear() + retirementObj.getRetirementAge();
                    retirement_expense = retirementObj.getRetirement_expense();
                    if (getfinplan.getMarital_status().equals("Yes") && Details.getMarital_status().equals("No")) {
                        SpouseStartYear = getfinplan.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                    } else if (Details.getMarital_status().equals("Yes")) {
                        SpouseStartYear = Details.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
                    }

                } else {
                    userStartYear = Details.getBirthYear() + 70;
                    if (getfinplan.getMarital_status().equals("Yes") && Details.getMarital_status().equals("No")) {
                        SpouseStartYear = getfinplan.getSpouseBirthYear() + 70;
                    } else if (Details.getMarital_status().equals("Yes")) {
                        SpouseStartYear = Details.getSpouseBirthYear() + 70;
                    }
                }
                final JSONArray afterRetirementExpense = CalculationEngine.retirementExpenseArray(userExpense,
                        SpouseStartYear, userStartYear, getfinplan.getMarital_status(), retirement_expense);
                final JSONObject retirementData = new JSONObject();
                retirementData.put("spouseStartYear", SpouseStartYear);
                retirementData.put("userStartYear", userStartYear);

                String emergencyType = null;
                String monthsOfIncome = null;
                String monthsOfExpense = null;
                if (emergencyFundFlag == true) {
                    final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                            .findOne("{fin_id:#,goalType:#}", getfinplan.get_id(), "Emergency Fund")
                            .as(Emergencyfundmodel.class);
                    emergencyType = emergencyObj.getTimePeriod();
                    monthsOfIncome = emergencyObj.getMonthI();
                    monthsOfExpense = emergencyObj.getMonthE();
                }
                final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, fin_id, combinedIncome,
                        SpouseIncomeArray, afterRetirementExpense, limits, maritalStatus, assets, tax, user_id,
                        fillingExemtion, userAge, spouseAge, emergencyFundAmt, emergencyFundFlag, deductions,
                        kidBirthYear, retirementData, retirementObj, expensesObjTemp, emergencyType, monthsOfIncome,
                        monthsOfExpense, getfinplan.isRetirementFlag());
                final String status = result.getString("status");
                if (status.equals("success")) {
                    MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi()
                    .with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits
                            + ",'deductions':" + deductions + ",'userExpense':" + afterRetirementExpense
                            + "}}");

                    MongoDBConnection.finplancol.update("{usr_id:#,plan_name:#}", user_id, plan_name)
                    .with("{$set: {'expense.housing_expense':" + expensesObjTemp + "}}");
                    responseToRestController.put("status", "success");
                    MongoDBConnection.incomeProfileCol
                    .update("{'user_id': '" + user_id + "','profile_name':'" + getfinplan.getProfile_name()
                    + "'}")
                    .upsert().multi().with("{$set: {'user_income':" + userIncome + ",'spouse_income':"
                            + SpouseIncomeArray + ",'combined_income':" + combinedIncome + "}}");

                    MongoDBConnection.goalcoll.update("{'_id': '" + goal_id + "'}").upsert().multi()
                    .with("{$set: {'location':'" + housegoalmodel.getLocation() + "','city':'"
                            + housegoalmodel.getCity() + "','downPaymentRate':"
                            + housegoalmodel.getDownPaymentRate() + ",'appreciationRate':"+housegoalmodel.getAppreciationRate()+",'houseMarketPrice':"
                            + housegoalmodel.getHouseMarketPrice() + ",'brokerCommissionAmount':"
                            + housegoalmodel.getBrokerCommissionAmount() + ",'otherCost':"
                            + housegoalmodel.getOtherCost() + ",'remainingTaxableAmt':"
                            + housegoalmodel.getRemainingTaxableAmt() + ",'frequency':'"
                            + housegoalmodel.getFrequency() + "','loanType':'" + loanType
                            + "','principal_amount':" + housegoalmodel.getPrincipal_amount()
                            + ",'property_taxrate':" + housegoalmodel.getProperty_taxrate() + ",'rentalIncome':"
                            + rentalIncome + ",'propertyValForRent':" + propertyValForRent
                            + ",'profitOrLossCal':" + profitOrLossCal + ",'rentalActivity':'" + rentalActivity
                            + "','creditScore':'" + housegoalmodel.getCreditScore() + "','homeInsurance':"
                            + housegoalmodel.getHomeInsurance() + ",'pmi':" + pmi + ",'interest':" + interest
                            + ",'FifteenYearFixed':" + states.getFifteenYearFixed() + ",'FiveOneARM':"
                            + states.getFiveOneARM() + ",'ThirtyYearFixed':" + states.getThirtyYearFixed()
                            + ",'loanPreriod':" + housegoalmodel.getGoalDuration() + ",'mortgage_expense':"
                            + totalAnualhouseExpense + ",'rentalExpense':" + housegoalmodel.getRentalExpense()
                            + ",'mortgageAmount':" + mortgageAmount + ",'goalFeasibility':" + true
                            + ",'startYear':" + startYear + ",'Anual_morgage':" + exactAnual_morgage
                            + ",'firstHousePrinciple':" + housegoalmodel.getFirstHousePrinciple()
                            + ",'modified_ts':'" + dateFormat.format(date) + "'}}");
                    responseToRestController.put("status", "success");
                    responseToRestController.put("city", housegoalmodel.getCity());
                    responseToRestController.put("mortgage_expense", decimalFloat.format(totalAnualhouseExpense));
                    responseToRestController.put("mortgage_amount", totalAnualhouseExpense);
                    responseToRestController.put("Anual_morgage", decimalFloat.format(exactAnual_morgage));
                    responseToRestController.put("property_tax", decimalFloat.format(property_taxrate));
                    responseToRestController.put("year", housegoalmodel.getLoanPreriod());
                    responseToRestController.put("PMI", pmi);
                    responseToRestController.put("homeInsurance", homeInsurance);
                    responseToRestController.put("principalAmount", housegoalmodel.getPrincipal_amount());
                    responseToRestController.put("creditScore", housegoalmodel.getCreditScore());
                    responseToRestController.put("downPaymentRate", housegoalmodel.getDownPaymentRate());
                    responseToRestController.put("plan_name", housegoalmodel.getPlan_name());
                    responseToRestController.put("downPayment", downPayment);
                    responseToRestController.put("propertyTax", propertyTax);
                    responseToRestController.put("interest", decimalFloat.format(interest));
                    responseToRestController.put("location", housegoalmodel.getLocation());
                    responseToRestController.put("goalFeasiblity", true);
                    responseToRestController.put("frequency", housegoalmodel.getFrequency());
                    responseToRestController.put("houseMarketPrice", housegoalmodel.getHouseMarketPrice());
                    responseToRestController.put("brokerCommissionAmount", housegoalmodel.getBrokerCommissionAmount());
                    responseToRestController.put("otherCost", housegoalmodel.getOtherCost());
                    responseToRestController.put("rentalActivity", rentalActivity);
                    responseToRestController.put("rentalIncome", rentalIncome);
                    responseToRestController.put("propertyValForRent", propertyValForRent);
                    responseToRestController.put("profitOrLossCal", profitOrLossCal);
                    responseToRestController.put("startYear", startYear);
                    responseToRestController.put("loanType", housegoalmodel.getLoanType());
                    responseToRestController.put("appreciationRate", housegoalmodel.getAppreciationRate());

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

    /*------------------------Calculation For Anual Morgage and Total Mogagae-----------------------*/
    public double calAnualMorgage(int years, float interest, long mortgageAmount) {
        double exactAnual_morgage = Math.pow(1 + interest / 100, years) * (interest / 100)
                / (Math.pow(1 + interest / 100, years) - 1) * mortgageAmount;
        if (Double.isNaN(exactAnual_morgage)) {
            exactAnual_morgage = 0;
        }
        return exactAnual_morgage;
    }

    public double calTotalMorgage(double exactAnual_morgage, long homeInsurance, int years, float interest,
            long mortgageAmount, float PMI, float property_taxrate, long principal_amount) {
        final double totalAnualhouseExpense = exactAnual_morgage
                + (homeInsurance + PMI * mortgageAmount / 100 + property_taxrate * principal_amount / 100);

        return totalAnualhouseExpense;
    }

    public long calDownPayment(int downPaymentRate, long principal_amount) {

        return downPaymentRate * principal_amount / 100;

    }

    public float calculateIntrestRate(Housegoalmodel housegoalmodel, String loanType, String credit_score) {
        final States states = MongoDBConnection.stateColl.findOne("{Name:#}", housegoalmodel.getLocation())
                .as(States.class);
        final String constatntScore = "760-850";
        // System.out.println("credit_score in side new method"+credit_score +"
        // "+loanType);
        final RateAdjustmentFactor rateFactors = MongoDBConnection.rateAdjustmentColl
                .findOne("{creditScore:#}", credit_score).as(RateAdjustmentFactor.class);
        final RateAdjustmentFactor constantCreditscore = MongoDBConnection.rateAdjustmentColl
                .findOne("{creditScore:#}", constatntScore).as(RateAdjustmentFactor.class);
        float intrestRateFromApi = 0.0f;
        // System.out.println("rate from api "+intrestRateFromApi +"rateFactors
        // "+rateFactors);
        float intrestRateFromtable = 0.0f;
        float interest = 0.0f;
        if (loanType.equals("30-year fixed")) {
            intrestRateFromApi = Float.parseFloat(states.getThirtyYearFixed());
            intrestRateFromtable = Float.parseFloat(rateFactors.getThirtyYearFixed());
        } else if (loanType.equals("15-year fixed")) {
            intrestRateFromApi = Float.parseFloat(states.getFifteenYearFixed());
            intrestRateFromtable = Float.parseFloat(rateFactors.getFifteenYearFixed());
        } else if (loanType.equals("jumbo 30-year fixed")) {
            intrestRateFromApi = Float.parseFloat(states.getThirtyYearFixed());
            // System.out.println("credit_score "+credit_score+" constatntScore
            // ");
            if (!credit_score.equals("760-850")) {
                // System.out.println("insideeee new call ");
                intrestRateFromApi = intrestRateFromApi
                        * Float.parseFloat(constantCreditscore.getThirtyYearFixedJumbo());
            }
            intrestRateFromtable = Float.parseFloat(rateFactors.getThirtyYearFixedJumbo());
            // System.out.println("api "+states.getThirtyYearFixed()+"
            // "+constantCreditscore.getThirtyYearFixedJumbo()+"
            // "+rateFactors.getThirtyYearFixedJumbo());
        } else if (loanType.equals("jumbo 15-year fixed")) {
            intrestRateFromApi = Float.parseFloat(states.getFifteenYearFixed());
            if (!credit_score.equals("760-850")) {
                intrestRateFromApi = intrestRateFromApi
                        * Float.parseFloat(constantCreditscore.getFifteenYearFixedJumbo());
            }
            intrestRateFromtable = Float.parseFloat(rateFactors.getFifteenYearFixedJumbo());
        } else if (loanType.equals("3/1 ARM")) {
            intrestRateFromApi = Float.parseFloat(states.getFiveOneARM());
            intrestRateFromtable = Float.parseFloat(rateFactors.getThreeOneARM());
            // System.out.println("intrestRateFromApi "+intrestRateFromApi+"
            // intrestRateFromtable"+intrestRateFromtable);
        } else if (loanType.equals("5/1 ARM")) {
            intrestRateFromApi = Float.parseFloat(states.getFiveOneARM());
            intrestRateFromtable = Float.parseFloat(rateFactors.getFiveOneARM());
        } else if (loanType.equals("jumbo 3/1 ARM")) {
            intrestRateFromApi = Float.parseFloat(states.getFiveOneARM());
            if (!credit_score.equals("760-850")) {
                intrestRateFromApi = intrestRateFromApi * Float.parseFloat(constantCreditscore.getThreeOneARMJumbo());
            }
            intrestRateFromtable = Float.parseFloat(rateFactors.getThreeOneARMJumbo());
        } else if (loanType.equals("jumbo 5/1 ARM")) {
            intrestRateFromApi = Float.parseFloat(states.getFiveOneARM());
            if (!credit_score.equals("760-850")) {
                intrestRateFromApi = intrestRateFromApi * Float.parseFloat(constantCreditscore.getFiveOneARMJumbo());
            }
            intrestRateFromtable = Float.parseFloat(rateFactors.getFiveOneARMJumbo());
        } else {
            // System.out.println("none of the above optionn");
        }
        // System.out.println("intrestRateFromApi "+intrestRateFromApi+ "
        // intrestRateFromtable "+intrestRateFromtable);
        interest = intrestRateFromApi * intrestRateFromtable;

        return interest;
    }

    /*----------------------------Update FinPlan Collection For House Goal----------------------------*/
    public JSONObject updateFinplanForHouseGoal(String fin_id, String user_id, String goal_id,
            double totalAnualhouseExpense, double exactAnual_morgage, float PMI, long homeInsurance,
            float property_taxrate, long principal_amount, int loanPeriod, double downPayment, int startYear,
            String actionType, int currentYear, float interest, String frequency, int remainingTaxableAmt,
            String originalHouseStatus, double profitOrLossCal, String rentalActivity, int remainingTaxable,
            String loanType, String creditScore,double appreciationRate,double deprectionAmount,double rentalIncomePerYear) throws JSONException, JsonProcessingException {
        final JSONObject responseToinsertupdateHousegoal = new JSONObject();
        responseToinsertupdateHousegoal.put("status", "fail");
        final JSONObject housing_expensedata = new JSONObject();
        final JSONArray housing_expense = new JSONArray();
        JSONArray expenses = new JSONArray();
        final int endYear = startYear + loanPeriod;

        try {
            int downPaymentInPercent = 0;
            if (!frequency.equals("Replace first house")) {
                downPaymentInPercent = (int) downPayment;
                downPayment = downPayment / 100 * principal_amount;
            }
            final float property_tax = property_taxrate / 100 * principal_amount;
            housing_expensedata.put("mortgage_expense", totalAnualhouseExpense);
            housing_expensedata.put("_id", goal_id);
            housing_expensedata.put("pmi_expense", PMI);
            housing_expensedata.put("startYear", startYear);
            housing_expensedata.put("endYear", endYear);
            housing_expensedata.put("home_insurance_expense", homeInsurance);
            housing_expensedata.put("downPayment", downPayment);
            housing_expensedata.put("property_tax", property_tax);
            housing_expensedata.put("property_value", principal_amount);
            housing_expensedata.put("interest", interest);
            housing_expensedata.put("frequency", frequency);
            housing_expensedata.put("remainingTaxableAmt", remainingTaxableAmt);
            housing_expensedata.put("downPaymentInPercent", downPaymentInPercent);
            housing_expensedata.put("originalHouseStatus", originalHouseStatus);
            housing_expensedata.put("profitOrLossCal", profitOrLossCal);
            housing_expensedata.put("rentalActivity", rentalActivity);
            housing_expensedata.put("remainingTaxable", remainingTaxable);
            housing_expensedata.put("loanPreriod", loanPeriod);
            housing_expensedata.put("loanType", loanType);
            housing_expensedata.put("creditScore", creditScore);
            housing_expensedata.put("appreciationRate", appreciationRate);
            housing_expensedata.put("deprectionAmount", deprectionAmount);
            housing_expensedata.put("rentalIncomePerYear", rentalIncomePerYear);
            housing_expense.put(housing_expensedata);
            expenses = housing_expense;
            if (actionType.equals("insert")) {
                MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi()
                .with("{$addToSet: {'goals':'" + goal_id + "'}}");
            }
            JSONObject expenseJson = null;

            final FinPlan finPlanDetails = MongoDBConnection.finplancol.findOne("{_id:#}", fin_id).as(FinPlan.class);
            final JSONObject finJson = new JSONObject(jsonMapper.writeValueAsString(finPlanDetails));
            if (!finJson.isNull("expense")) {
                expenseJson = finJson.getJSONObject("expense");
                if (expenseJson.isNull("housing_expense")) {
                    MongoDBConnection.finplancol.update("{'_id': '" + fin_id + "'}").upsert().multi()
                    .with("{$set: {'expense.housing_expense':" + expenses + "}}");
                } else {
                    // if (actionType.equals("update")) {
                    // housing_expenseTemp=expenseJson.getJSONObject("expense").getJSONArray("housing_expense");
                    // for(int i=0;i<housing_expenseTemp.length();i++)
                    // {
                    //
                    // }
                    // MongoDBConnection.finplancol.update("{'_id':#}",
                    // fin_id).upsert().multi()
                    // .with("{$set: {'expense.housing_expense':'" +
                    // housing_expensedata + "'}}");
                    // }

                    //// System.out.println("shdbd sdsad");
                    MongoDBConnection.finplancol.update("{'_id': '" + fin_id + "'}").upsert().multi()
                    .with("{$addToSet: {'expense.housing_expense':" + housing_expensedata + "}}");

                    responseToinsertupdateHousegoal.put("status", "success");
                }
            }
        } catch (final JSONException e) {
            e.printStackTrace();
        }
        return responseToinsertupdateHousegoal;

    }

    /*-------------------------fetching Data from goal collection for house Goal----------------------*/

    public JSONObject editHouseGoalData(Housegoalmodel housegoalmodel) {

        final JSONObject responseToRestController = new JSONObject();
        final String goal_id = housegoalmodel.getGoal_id();

        try {

            final Housegoalmodel Housegoalmodeldata = MongoDBConnection.goalcoll.findOne("{_id:#}", goal_id)
                    .as(Housegoalmodel.class);
            if (Housegoalmodeldata == null) {
                responseToRestController.put("message", "no Such GoalID is in housegoal collection........ Error");
                return responseToRestController;
            }
            final double mortgage_expense = Housegoalmodeldata.getMortgage_expense();
            final long propertyTax = (long) (Housegoalmodeldata.getProperty_taxrate()
                    * Housegoalmodeldata.getPrincipal_amount() / 100);
            // long
            // downPayment=calDownPayment(Housegoalmodeldata.getDownPaymentRate(),
            // Housegoalmodeldata.getPrincipal_amount());
            final long downPaymentRate = Housegoalmodeldata.getDownPaymentRate();
            long downPayment = 0;
            if (downPaymentRate <= 100) {
                downPayment = calDownPayment(Housegoalmodeldata.getDownPaymentRate(),
                        Housegoalmodeldata.getPrincipal_amount());
            } else {
                downPayment = downPaymentRate;
            }
            responseToRestController.put("status", "success");
            responseToRestController.put("mortgage_expense", decimalFloat.format(mortgage_expense));
            responseToRestController.put("location", Housegoalmodeldata.getLocation());

            responseToRestController.put("city", Housegoalmodeldata.getCity());
            responseToRestController.put("pmi", Housegoalmodeldata.getPMI());
            responseToRestController.put("mortgage_amount", Housegoalmodeldata.getMortgageAmount());
            responseToRestController.put("Anual_morgage", decimalFloat.format(Housegoalmodeldata.getAnual_morgage()));
            responseToRestController.put("homeInsurance", Housegoalmodeldata.getHomeInsurance());
            responseToRestController.put("property_tax", decimalFloat.format(Housegoalmodeldata.getProperty_taxrate()));
            responseToRestController.put("propertyTax", propertyTax);
            responseToRestController.put("plan_name", Housegoalmodeldata.getPlan_name());
            responseToRestController.put("year", Housegoalmodeldata.getLoanPreriod());
            responseToRestController.put("creditScore", Housegoalmodeldata.getCreditScore());
            responseToRestController.put("interest", decimalFloat.format(Housegoalmodeldata.getInterest()));
            responseToRestController.put("startYear", Housegoalmodeldata.getStartYear());
            responseToRestController.put("principalAmount", Housegoalmodeldata.getPrincipal_amount());
            responseToRestController.put("downPayment", downPayment);
            responseToRestController.put("downPaymentRate", Housegoalmodeldata.getDownPaymentRate());
            responseToRestController.put("goalFeasiblity", Housegoalmodeldata.getGoalFeasibility());
            responseToRestController.put("frequency", Housegoalmodeldata.getFrequency());
            responseToRestController.put("houseMarketPrice", Housegoalmodeldata.getHouseMarketPrice());
            responseToRestController.put("brokerCommissionAmount", Housegoalmodeldata.getBrokerCommissionAmount());
            responseToRestController.put("otherCost", Housegoalmodeldata.getOtherCost());
            responseToRestController.put("rentalIncome", Housegoalmodeldata.getRentalIncome());
            responseToRestController.put("rentalActivity", Housegoalmodeldata.getRentalActivity());
            responseToRestController.put("propertyValForRent", Housegoalmodeldata.getPropertyValForRent());
            responseToRestController.put("firstHousePrinciple", Housegoalmodeldata.getFirstHousePrinciple());
            responseToRestController.put("rentalExpense", Housegoalmodeldata.getRentalExpense());
            responseToRestController.put("appreciationRate",Housegoalmodeldata.getAppreciationRate());
            // System.out.println(" Housegoalmodeldata.."+
            // Housegoalmodeldata.getLoanType());
            responseToRestController.put("loanType", Housegoalmodeldata.getLoanType());
            responseToRestController.put("houseStatus", houseStatusTemp);

            return responseToRestController;
        } catch (final Exception e) {

            e.printStackTrace();
        }
        return responseToRestController;

    }
    /*-----------------------------------------House value fetching from state collection--------------*/

    public JSONObject calHouseValue(Housegoalmodel housegoalmodel) throws JSONException {
        final ObjectMapper jsonMapper = new ObjectMapper();
        final JSONObject resposneToRest = new JSONObject();
        resposneToRest.put("status", "fail");
        double houseValue = 0;
        final int flag = 0;
        final String city = housegoalmodel.getCity();
        // System.out.println("city====="+city);
        final String state = housegoalmodel.getLocation();
        final States stateobj1 = MongoDBConnection.stateColl.findOne("{Name:#}", state).as(States.class);
        final String county = housegoalmodel.getCounty().replace("County", "").trim();
        // States gethousevalue =
        // MongoDBConnection.stateColl.findOne("{Name:#,}",state).as(States.class);
        System.out.println("county====="+county);
        final JSONObject cities;
        try {
            final HouseMedianValue cityobj = MongoDBConnection.city_House_Median_ValueColl
                    .findOne("{city:#,state:#}", city,stateobj1.getCode()).as(HouseMedianValue.class);
            if (cityobj != null) {
                houseValue = cityobj.getValue();
                System.out.println("CIhouseValue====="+houseValue);
            }else {
                final HouseMedianValue countybj = MongoDBConnection.county_House_Median_ValueColl
                        .findOne("{countyName:#,state:#}", county,stateobj1.getCode()).as(HouseMedianValue.class);
                if (countybj != null) {
                    houseValue = countybj.getValue();
                    System.out.println("COhouseValue====="+houseValue);
                    System.out.println("county====="+county);
                }else {

                    final HouseMedianValue stateobj = MongoDBConnection.state_House_Median_ValueColl
                            .findOne("{state:#}", state).as(HouseMedianValue.class);
                    if (stateobj != null) {
                        houseValue = stateobj.getValue();
                        System.out.println("SThouseValue====="+houseValue);
                    } else {
                        houseValue = 0.00;
                    }

                }
            }
            resposneToRest.put("status", "success");
            resposneToRest.put("housevalue",houseValue);
            // resposneToRest.put("housevalue", roundUpNumberByUsingMultipleValue(houseValue, 1000));
            /*
             * cities = new
             * JSONObject(jsonMapper.writeValueAsString(gethousevalue)); for
             * (int i=0;i<cities.getJSONArray("cities").length();i++) { String
             * citydemo=cities.getJSONArray("cities").getJSONObject(i).getString
             * ("city"); if(citydemo.equals(city)) { flag=1;
             * houseValue=cities.getJSONArray("cities").getJSONObject(i).
             * getDouble("housevalue");
             *
             * resposneToRest.put("status","success");
             * resposneToRest.put("city", citydemo);
             * resposneToRest.put("housevalue",roundUpNumberByUsingMultipleValue
             * (houseValue,1000)); }
             *
             * } if(flag==0) {
             *
             * }
             */
            return resposneToRest;
        } catch (final Exception e) {
            e.printStackTrace();
            return resposneToRest;
        }
    }

    public JSONObject calHouseInterest(Housegoalmodel housegoalmodel) {

        final JSONObject resposneToRest = new JSONObject();
        try {
            float interest;
            resposneToRest.put("status", "fail");
            int years = housegoalmodel.getLoanPreriod();

            final States states = MongoDBConnection.stateColl.findOne("{Name:#}", housegoalmodel.getLocation())
                    .as(States.class);
            if (years == 30) {

                resposneToRest.put("status", "success");
                interest = Float.parseFloat(states.getThirtyYearFixed());
            } else if (years == 15) {
                resposneToRest.put("status", "success");
                interest = Float.parseFloat(states.getFifteenYearFixed());
            } else {
                resposneToRest.put("status", "success");
                interest = Float.parseFloat(states.getFiveOneARM());
                years = 30;
            }
            resposneToRest.put("interestRate", decimalFloat.format(interest));
            return resposneToRest;
        } catch (final Exception e) {
            e.printStackTrace();
            return resposneToRest;
        }
    }

    int roundUpNumberByUsingMultipleValue(double number, int multiple) {

        int result = multiple;

        if (number % multiple == 0) {
            return (int) number;
        }

        if (number % multiple != 0) {

            final int division = (int) (number / multiple + 1);

            result = division * multiple;

        }
        return result;

    }
}
