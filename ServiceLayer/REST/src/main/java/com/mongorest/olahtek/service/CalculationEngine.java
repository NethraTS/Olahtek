package com.mongorest.olahtek.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.jongo.Jongo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.Marriagegoalmodel;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.StatetaxModel;
import com.mongorest.olahtek.model.User;


public class CalculationEngine {

    public static ObjectMapper jsonMapper = new ObjectMapper();
    public MongoClient mongoClient = MongoDBConnection.mongoClient;
    public MongoDatabase mongoDb = MongoDBConnection.mongoDb;
    public Jongo jongo = MongoDBConnection.jongo;
    public String mongoDBName = MongoDBConnection.mongoDBName;
    public DB db = MongoDBConnection.db;
    public JSONObject cm = MongoDBConnection.cm;
    public static double remainingMortgageTmp = 0;
    public static double remainingMortgageHouseTmp = 0;
    String counterCollection = MongoDBConnection.counterCollection;
    String financialPlanCollection = MongoDBConnection.financialPlanCollection;
    String sessionCollection = MongoDBConnection.sessionCollection;
    String goalCollection = MongoDBConnection.goalCollection;
    String minimumDistributioncoll = MongoDBConnection.minimumDistributioncollection;

    String usersCollection = MongoDBConnection.usersCollection;
    String systemLog = MongoDBConnection.systemLog;
    String stateTaxCollection = MongoDBConnection.stateTaxCollection;
    String incomeProfileCollection = MongoDBConnection.incomeProfileCollection;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
    Date date = new Date();
    DecimalFormat decimalFloat = new DecimalFormat("#.##");

    public static double amountMorgage = 0;
    static int newYear = 0;
    static int newYearHouse = 0;
    static int tempYear=0;
    static int ownTempYear=0;
    static double amountMorgageHouse = 0;
    static JSONObject jsonarkansas;

    // ---------------------------------------Bala Sweeping of Money-------------------------------------------------
    public static JSONObject sweepingOfMoney(JSONArray userIncomes, String fin_id, JSONArray income,
            JSONArray spouse_income, JSONArray userExpense, JSONArray limits, String maritalStatus, JSONArray assets,
            JSONArray tax, String user_id, JSONArray fillingExemtion, int userAge, int spouseAge, int emergencyFundAmt,
            boolean emergencyFundFlag, JSONArray deductions, JSONArray kidBirthYear, JSONObject dataFromRetirement,
            RetirementGoal goalCollectionData, JSONArray housing_expense, String emergencyType, String monthsOfIncome,
            String monthsOfExpense, boolean retirementFlag) {
        //System.out.println("income array income ==="+income);
        //System.out.println("income array userIncomes==="+userIncomes);
        amountMorgage = 0;
        newYear = 0;
        newYearHouse = 0;
        amountMorgageHouse = 0;
        boolean flag=false;
        //////System.out.println("user_id aparna    "+user_id);

        final Calendar cal = Calendar.getInstance();

        final int currentYear = cal.get(Calendar.YEAR);
        final JSONObject responseObj = new JSONObject();
        final StringBuilder statusMsg = new StringBuilder("Goal not fesibile because ");
        try {
            //////System.out.println("");
            boolean goalFeasibility = true;
            final InvestmentPortfolioImpl investmentPortfolio = new InvestmentPortfolioImpl();
            //////System.out.println("userid===="+user_id);
            final User user = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
            final FinPlan finPlanDetails = MongoDBConnection.finplancol
                    .findOne("{_id:#}", fin_id ).as(FinPlan.class);
            final int Risk_Score = user.getRiskScore();
            double remaining_amount = user.getCash();
            double taxableInvestmentAmount = user.getTaxableInvestments();
            double nonTaxableInvestmentAmount = 0;
            if (user.getNonTaxableInvestments().equals("Yes")) {
                nonTaxableInvestmentAmount = user.getUser401k() + user.getUserIRA() + user.getUser529()
                + user.getUserRothIra() + user.getSpouse401k() + user.getSpouseIRA() + user.getSpousePlan529()
                + user.getSpouseRothIra();
            }
            final double savingInterestRate = user.getSavingInterestRate();
            double excess = 0;
            final double initialLimit = 0;
            double taxExcess = 0;
            double excessNonTax = 0;
            double maximumContributionForNonTaxable = 0;
            double riskFactor = 1;
            if (spouseAge == 0) {
                spouseAge = user.getSpouseAge();
            }
            int age = currentYear - user.getBirthYear();
            double limitForSavingAcount = 0;
            double limitForTaxableAmount = 0;
            double limitForCollegeGoalTaxable = 0;
            if (maritalStatus.equals("Yes")) {
                riskFactor = 2;
            } else {
                spouse_income = new JSONArray();
            }

            // Find the basic details for calculating the tax from user and fin
            // plan collections.
            final String state = user.getState();
            final int kidcount = user.getChildCount();
            final int spouseBirthYear = user.getSpouseBirthYear();
            final int userBirthYear = user.getBirthYear();
            final long startYear = user.getRemainingYearsMortgage();
            final double remainingMortgageOriginal = user.getRemainingMortgage();
            final double remainingMortgageInterestRate = user.getRemainingMortgageInterestRate();
            final double houseAppreciationRate=user.getHouseAppreciationRate();
            final String[] createdTs = user.getCreatedTs().split("/");
            final int registrationYear = Integer.parseInt(createdTs[0]);
            JSONArray childArray = null;
            JSONObject userJson = null;
            if (user != null) {
                userJson = new JSONObject(jsonMapper.writeValueAsString(user));
                if (!userJson.isNull("childs")) {
                    childArray = userJson.getJSONArray("childs");
                }
            }
            String houseStatus = user.getHouseStatus();
            final String registrationHouseStatus=user.getHouseStatus();
            final double houseValue = user.getHouseValue();

            if (housing_expense != null) {
                houseStatus = "Own";
            }
            // ================================Retirement part starts
            // here===================================================
            final String goal_id = null;
            JSONArray userIncome = null;
            int retirementYear = 0;
            int spouseStartYear = 0;
            double userIRA = 0;
            double spouseIRA = 0;
            int startRetirementYear = 0;
            double userContribute401k = 0;
            double spouseContribute401k = 0;
            double userSSB = 0;
            double spouseSSB = 0;
            double yearlySSB = 0;
            int riskScore = user.getRiskScore();
            // ...limits buckets
            double spouse401k = 0;
            double user401k = 0;
            double userRothIRA = 0;
            double spouseRothIRA = 0;
            double user529Plan = 0;
            double spouse529Plan = 0;
            double tempExcess = 0;
            double userIRALimit = 0;
            double spouseIRALimit = 0;
            // ---limits array model
            double fourNotOneLimit = 0;
            double user529Limit = 0;
            double spouse529Limit = 0;
            double iraLimit = 0;
            double spouseFourNotOne = 0;
            double spouseIraLimit = 0;
            double spouseTempTaxable = 0;
            double AGI = 0;
            double nonTaxableForLimits = 0;
            double user401kContribution = 0;
            double spouse401kContribution = 0;
            double userCap = 0;
            double spouseCap = 0;
            double userMatching = 0;
            double spouseMatching = 0;
            int userStartYearForCap = 0;
            int spouseStartYearForCap = 0;
            double userSavedAmount = 0;
            double spouseSavedAmount = 0;
            double userIraSavedAmount = 0;
            double spouseIraSavedAmount = 0;
            double userRoughtIraSavedAmount = 0;
            double spouseRoughtIraSavedAmount = 0;
            double userTempExcess = 0;
            double tempExcessNew = 0;
            double spouseTempExcess = 0;
            int retFlag = 0;
            if (retirementFlag) {
                retFlag = 1;
            }
            if ((retFlag == 0 && goalCollectionData != null)) {
                userIRA = goalCollectionData.getUserContributionInIRA();
                spouseIRA = goalCollectionData.getSpouseContributionInIRA();
                userContribute401k = (goalCollectionData.getUserContributeAmount()
                        * goalCollectionData.getUserMatchContribution()) / 100;
                spouseContribute401k = (goalCollectionData.getSpouseContributeAmount()
                        * goalCollectionData.getSpouseMatchContribution()) / 100;
                // spouseAge = dataFromRetirement.getInt("spouseAge");
                retirementYear = dataFromRetirement.getInt("userStartYear");
                spouseStartYear = dataFromRetirement.getInt("spouseStartYear");
                // ////System.out.println("spouseStartYear mahi :"+spouseStartYear);
                /*userSSB = dataFromRetirement.getDouble("userssb");
				spouseSSB = dataFromRetirement.getDouble("spousessb");*/
            } else if ((retFlag == 1 && goalCollectionData != null)) {
                userIRA = goalCollectionData.getUserContributionInIRA();
                spouseIRA = goalCollectionData.getSpouseContributionInIRA();
                userContribute401k = (goalCollectionData.getUserContributeAmount()
                        * goalCollectionData.getUserMatchContribution()) / 100;
                spouseContribute401k = (goalCollectionData.getSpouseContributeAmount()
                        * goalCollectionData.getSpouseMatchContribution()) / 100;
                // spouseAge = dataFromRetirement.getInt("spouseAge");
                retirementYear = dataFromRetirement.getInt("userStartYear");
                spouseStartYear = dataFromRetirement.getInt("spouseStartYear");
                // ////System.out.println("spouseStartYear mahi :"+spouseStartYear);
                userSSB = goalCollectionData.getUserSSB();
                spouseSSB = goalCollectionData.getSpouseSSB();
            } else if (retFlag == 1) {
                final RetirementGoal retirementData = MongoDBConnection.goalcoll
                        .findOne("{fin_id:#,user_id:#,goalType:#}", fin_id, user_id, "Retirement").as(RetirementGoal.class);
                userIRA = retirementData.getUserContributionInIRA();
                spouseIRA = retirementData.getSpouseContributionInIRA();
                userContribute401k = (retirementData.getUserContributeAmount()
                        * retirementData.getUserMatchContribution()) / 100;
                spouseContribute401k = (retirementData.getSpouseContributeAmount()
                        * retirementData.getSpouseMatchContribution()) / 100;
                retirementYear = user.getBirthYear() + retirementData.getRetirementAge();
                spouseStartYear = user.getSpouseBirthYear() + retirementData.getSpouseRetirementAge();
                spouseSSB = user.getSpouseSSB();
                userSSB = user.getUserSSB();
                riskScore = user.getRiskScore();
            } else {
                if (dataFromRetirement == null) {
                    if (maritalStatus.equals("Yes")) {
                        spouseStartYear = user.getSpouseBirthYear() + 70;
                        if (spouseStartYear == 70) {
                            spouseStartYear = (retirementYear);
                        }
                    } else {
                        // ////System.out.println("inside no spouse no retirement");
                        spouseAge = 0;
                    }
                } else {
                    // ////System.out.println("***********Mahi New
                    // changes*************");
                    if (maritalStatus.equals("Yes")) {
                        spouseStartYear = dataFromRetirement.getInt("spouseStartYear");
                    } else {
                        spouseAge = 0;

                    }
                }
                retirementYear = user.getBirthYear() + 70;
                spouseSSB = user.getSpouseSSB();
                userSSB = user.getUserSSB();
                riskScore = user.getRiskScore(); // spending
            }
            userIncome = userIncomes;
            int userStartYear = retirementYear;
            if (spouseStartYear != 0 && spouseStartYear < retirementYear) {
                startRetirementYear = spouseStartYear;
            } else {
                startRetirementYear = retirementYear;
            }
            userStartYearForCap = retirementYear;
            spouseStartYearForCap = spouseStartYear;
            final int spouseRetirementStartYear = spouseStartYear;
            final int userRetirementStartYear = retirementYear;
            final double incomeRothIRA = 0;// userRothIRA+spouseRothIRA;
            double contributionAmount = userIRA + userContribute401k + spouseIRA + spouseContribute401k;
            int spouseAgeInFinPlan = 0;
            if (fin_id != null) {
                final Marriagegoalmodel marriagegoalmodel = MongoDBConnection.goalcoll
                        .findOne("{fin_id:#,goalType:#}", fin_id, "Marriage").as(Marriagegoalmodel.class);
                if (marriagegoalmodel != null) {
                    spouseAgeInFinPlan = marriagegoalmodel.getBirthYear();
                }
            }
            //------------------genrating equity array------------------
            JSONArray equity=null;
            if(fin_id!=null){
                final JSONObject finPlanJson=new JSONObject(jsonMapper.writeValueAsString(finPlanDetails));
                if(!finPlanJson.isNull("equity"))
                {
                    equity=finPlanJson.getJSONArray("equity");
                    ////System.out.println("equityFromDb ??? "+equityFromDb);
                }
            }
            else{
                equity = new JSONArray();
                final double equityVal=0.00;
                final double propertyVal=0.00;
                for(int j=0;j<income.length();j++)
                {
                    final JSONObject equityObj=new JSONObject();
                    equityObj.put("year", income.getJSONObject(j).getInt("year"));
                    equityObj.put("value", equityVal);
                    equityObj.put("property", propertyVal);
                    equity.put(equityObj);
                }
            }
            //////System.out.println("equity array "+equity);
            // ========================================Retirement part ends
            // here==========================================================
            // Find the basic details for calculating the tax from user and fin
            // plan collections - Ends here.
            final StatetaxModel stateTaxValue = MongoDBConnection.stateTaxColl.findOne("{'statename':#}", state)
                    .as(StatetaxModel.class);
            ////System.out.println("length of assets array"+assets.length());
            final Long stime=System.currentTimeMillis();
            ////System.out.println("##start time "+stime);
            JSONArray equityReturn=new JSONArray();
            ////System.out.println("limits array"+limits);
            double accumulation529=limits.getJSONObject(0).getInt("user529Plan");
            ////System.out.println("accumulation 529 plan "+accumulation529);
            if(maritalStatus.equals("Yes"))
            {
                accumulation529=limits.getJSONObject(0).getInt("spouse529Plan");
            }
            //System.out.println("Income ::" +income);
            for (int i = 0; i < assets.length(); i++) {

                double growthRate = 0;
                double limitUser529 = 0;
                double limitSpouse529 = 0;
                final double expenses = userExpense.getJSONObject(i).getDouble("totalExpense");
                double spouseIncome = 0;
                if (goalCollectionData != null) {

                    userCap = goalCollectionData.getUserCap() / 100;
                    userMatching = goalCollectionData.getUserMatchContribution() / 100;
                    if (userAge <= 99) {
                        user401kContribution = userCap * userMatching
                                * (userIncomes.getJSONObject(i)).getDouble("value");
                    }

                    userSavedAmount = goalCollectionData.getUserSavedAmount();
                    userIraSavedAmount = goalCollectionData.getUserSavedInIRA();
                    userRoughtIraSavedAmount = goalCollectionData.getUserSavedInRothIRA();
                    //////System.out.println("Ranju : : userSavedAmount.."+userSavedAmount+"userIraSavedAmount.."+userIraSavedAmount+"userRoughtIraSavedAmount.."+userRoughtIraSavedAmount+"maritalStatus.."+maritalStatus);
                    if (maritalStatus.equals("Yes")) {
                        spouseRoughtIraSavedAmount = goalCollectionData.getSpouseSavedInRothIRA();
                        // ////System.out.println("user rought
                        // IRA------="+goalCollectionData.getSpouseSavedInRothIRA());
                        //	////System.out.println("spouseRoughtIraSavedAmount============"+spouseRoughtIraSavedAmount);
                        //////System.out.println("userRoughtIraSavedAmount============"+userRoughtIraSavedAmount);
                    }
                    if (goalCollectionData.getSpouseCap() != 0 || goalCollectionData.getSpouseSavedAmount() != 0
                            || goalCollectionData.getSpouseSavedInIRA() != 0
                            || goalCollectionData.getSpouseMatchContribution() != 0
                            || goalCollectionData.getSpouseSavedAmount() != 0) {
                        spouseCap = goalCollectionData.getSpouseCap() / 100;
                        spouseMatching = goalCollectionData.getSpouseMatchContribution() / 100;
                        spouse401kContribution = spouseCap * spouseMatching
                                * spouse_income.getJSONObject(i).getDouble("value");
                        spouseSavedAmount = goalCollectionData.getSpouseSavedAmount();
                        spouseIraSavedAmount = goalCollectionData.getSpouseSavedInIRA();
                        spouseRoughtIraSavedAmount = goalCollectionData.getSpouseSavedInRothIRA();
                        // ////System.out.println("user rought
                        // IRA------="+goalCollectionData.getSpouseSavedInRothIRA());
                        //////System.out.println("spouseRoughtIraSavedAmount============"+spouseRoughtIraSavedAmount+"spouse401kContribution "+spouse401kContribution+"spouseSavedAmount.."+spouseSavedAmount+"spouseIraSavedAmount .."+spouseIraSavedAmount);
                        //////System.out.println("userRoughtIraSavedAmount============"+userRoughtIraSavedAmount);

                    }
                }

                if (maritalStatus.equals("Yes")) {
                    if (fillingExemtion.getJSONObject(i).getString("fillingStatus").equals("Single") || fillingExemtion
                            .getJSONObject(i).getString("fillingStatus").equals("Head of Household")) {
                        riskFactor = 1;
                    } else if (!fillingExemtion.getJSONObject(i).getString("fillingStatus")
                            .equals("Married Filing Separately")
                            && !fillingExemtion.getJSONObject(i).getString("fillingStatus").equals("Qualified Widow")) {
                        riskFactor = 2;
                    }
                    if (i < spouse_income.length()) {
                        spouseIncome = spouse_income.getJSONObject(i).getDouble("value");
                    } else {
                        spouseIncome = 0;
                    }
                }
                double totalTax = 0;
                final int year = assets.getJSONObject(i).getInt("year");
                //System.out.println("YEAR==---"+year+"expenses..."+expenses);
                if (year < startRetirementYear) {
                	//System.out.println("Year : "+year+" Income Value :: "+income.getJSONObject(i).getDouble("value"));
                    ////System.out.println("marital status==="+maritalStatus);
                    JSONObject tempTax = calTaxPerYear(0, 0, income.getJSONObject(i).getDouble("value"), user_id,
                            fin_id, userIncome.getJSONObject(i).getDouble("value"), spouseIncome, year,
                            fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                            fillingExemtion.getJSONObject(i).getString("fillingStatus"), age, spouseAge, maritalStatus,
                            kidBirthYear, state, kidcount, registrationYear, childArray, houseStatus, houseValue,
                            housing_expense, startYear, remainingMortgageOriginal, remainingMortgageInterestRate, 0,
                            stateTaxValue , taxableInvestmentAmount, nonTaxableInvestmentAmount,equity,finPlanDetails,houseAppreciationRate,registrationHouseStatus);
                    System.out.  println("year"+year+"Tax values"+tempTax);
                    totalTax = tempTax.getDouble("federalTax") + tempTax.getDouble("fICAMedicareTax")
                    + tempTax.getDouble("spouseSSTax") + tempTax.getDouble("userSSTax")
                    + tempTax.getDouble("stateTax");
                    //////System.out.println("tempTax---------"+tempTax+  "  year  1 "+year);

                    // --------------------- checking for limit of saving
                    // account---------------------
                    if (limits != null) {
                        if (emergencyFundFlag == false) {
                            limitForSavingAcount = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2)
                                    + limits.getJSONObject(i).getInt("saving");
                            // ////System.out.println("#####Bala####"+limits.getJSONObject(i).getInt("saving"));
                        } else {

                            if (emergencyType.equals("Fix Amount")) {
                                limitForSavingAcount = (emergencyFundAmt) + limits.getJSONObject(i).getInt("saving");
                            } else if (emergencyType.equals("Expense")) {

                                limitForSavingAcount = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                        * (Integer.parseInt(monthsOfExpense)))
                                        + limits.getJSONObject(i).getInt("saving");
                            } else if (emergencyType.equals("Income")) {

                                limitForSavingAcount = (income.getJSONObject(i).getDouble("value") / 12
                                        * (Integer.parseInt(monthsOfIncome)))
                                        + limits.getJSONObject(i).getInt("saving");
                            }

                        }
                        limitForTaxableAmount = limits.getJSONObject(i).getInt("taxable");
                        limitForCollegeGoalTaxable = limits.getJSONObject(i).getInt("collegeGoalTaxable");
                    } else {
                        if (emergencyFundFlag == false) {
                            limitForSavingAcount = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2);
                        } else {

                            if (emergencyType.equals("Fix Amount")) {
                                limitForSavingAcount = (emergencyFundAmt);
                            } else if (emergencyType.equals("Expense")) {

                                limitForSavingAcount = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                        * (Integer.parseInt(monthsOfExpense)));
                            } else if (emergencyType.equals("Income")) {

                                limitForSavingAcount = (income.getJSONObject(i).getDouble("value") / 12
                                        * (Integer.parseInt(monthsOfIncome)));
                            }

                        }
                        limitForTaxableAmount = 0;
                        limitForCollegeGoalTaxable = 0;
                    }
                    double currYearTaxableAmount = 0;
                    if (i == 0) {
                        currYearTaxableAmount = taxableInvestmentAmount;
                    } else {
                        currYearTaxableAmount = assets.getJSONObject(i - 1).getDouble("taxable_investment_amount");
                    }
                    final JSONObject growthRateJSON = investmentPortfolio.growthRate(Risk_Score, riskFactor, age,
                            MongoDBConnection.investmentPortfolioData);
                    growthRate = growthRateJSON.getDouble("growthRate");
                    if (i != 0) {
                        if (assets.getJSONObject(i - 1)
                                .getDouble("user529Plan") <= limits.getJSONObject(i).getDouble("user529Plan")
                                + user.getUser529()) {
                            limitUser529 = limits.getJSONObject(i).getDouble("user529Plan")
                                    - assets.getJSONObject(i - 1).getDouble("user529Plan");
                            if (limitUser529 < 0) {
                                limitUser529 = 0;
                            }
                            else if(limitUser529 >=14000)
                            {
                                limitUser529 = 14000;
                            }

                        }
                        if(maritalStatus.equals("Yes"))
                        {
                            if (assets.getJSONObject(i - 1)
                                    .getDouble("spouse529Plan") <= limits.getJSONObject(i).getDouble("spouse529Plan")
                                    + user.getSpousePlan529()) {
                                limitSpouse529 = limits.getJSONObject(i).getDouble("spouse529Plan")
                                        - assets.getJSONObject(i - 1).getDouble("spouse529Plan");
                                if (limitSpouse529 < 0) {
                                    limitSpouse529 = 0;
                                }
                                else if(limitSpouse529 >=14000)
                                {
                                    limitSpouse529 = 14000;
                                }

                            }
                        }
                    }
                    else if(i==0)
                    {
                        ////System.out.println("limits bala%%%"+limits.getJSONObject(i).getDouble("user529Plan")+"assets.."+assets.getJSONObject(i).getDouble("user529Plan"));

                        if (assets.getJSONObject(i)
                                .getDouble("user529Plan") <= limits.getJSONObject(i).getDouble("user529Plan")
                                + user.getUser529()) {
                            limitUser529 = limits.getJSONObject(i).getDouble("user529Plan")
                                    - assets.getJSONObject(i).getDouble("user529Plan");

                            if (limitUser529 <= 0) {
                                limitUser529 = 0;
                            }
                            else if(limitUser529 >=14000)
                            {
                                limitUser529 = 14000;
                            }
                            ////System.out.println("limit***"+limitUser529);
                        }

                        if(maritalStatus.equals("Yes"))
                        {
                            if (assets.getJSONObject(i)
                                    .getDouble("spouse529Plan") <= limits.getJSONObject(i).getDouble("spouse529Plan")
                                    + user.getSpousePlan529()) {
                                limitSpouse529 = limits.getJSONObject(i).getDouble("spouse529Plan")
                                        - assets.getJSONObject(i).getDouble("spouse529Plan");
                                if (limitSpouse529 < 0) {
                                    limitSpouse529 = 0;
                                }
                                else if(limitSpouse529 >=14000)
                                {
                                    limitSpouse529 = 14000;
                                }

                            }
                        }
                    }
                    maximumContributionForNonTaxable = limits.getJSONObject(i).getDouble("user401k")
                            + limits.getJSONObject(i).getDouble("spouse401k")
                            + limits.getJSONObject(i).getDouble("userIRA")
                            + limits.getJSONObject(i).getDouble("spouseIRA")
                            + limits.getJSONObject(i).getDouble("userRouthIRA")
                            + limits.getJSONObject(i).getDouble("spouseRouthIRA") + limitUser529 + limitSpouse529;

                    // ////System.out.println("Ravi:::: maximumContributionForNonTaxable==="+maximumContributionForNonTaxable+"..year=="+limits.getJSONObject(i).getInt("year")+"..limitUser529==="+limitUser529+".....limitSpouse529==="+limitSpouse529);
                    double savingAmount = remaining_amount;
                    // ////System.out.println("Mahi :
                    // maximumContributionForNonTaxable=:
                    // "+maximumContributionForNonTaxable);
                    double deductionAmount = 0;
                    if (deductions != null) {
                        deductionAmount = deductions.getJSONObject(i).getDouble("saving");
                    }
                    //////System.out.println("remaining_amount===11"+remaining_amount);
                    //System.out.println("icome==="+income.getJSONObject(i).getDouble("value"));
                    //System.out.println("deductionAmount=1=="+deductionAmount);
                    /*///System.out.println("savingInterestRate==="+savingInterestRate );
					////System.out.println("expenses==22="+expenses);
					////System.out.println("totalTax==1="+totalTax);


                     */
                    remaining_amount = remaining_amount + income.getJSONObject(i).getDouble("value")
                            + (savingInterestRate * remaining_amount / 100) - expenses - totalTax - deductionAmount;
                    ////System.out.println("income"+income.getJSONObject(i).getDouble("value"));
                    //System.out.println("expenses"+expenses);
                    //System.out.println("totalTax"+totalTax);
                    //System.out.println("remaining amount"+remaining_amount);
                    if (remaining_amount >= limitForSavingAcount) {
                        excess = remaining_amount - (limitForSavingAcount);
                        savingAmount = savingAmount - deductionAmount;
                        if (savingAmount < limitForSavingAcount) {
                            savingAmount = limitForSavingAcount - savingAmount;
                        } else {
                            savingAmount = 0;
                        }
                        remaining_amount = limitForSavingAcount;
                    } else {
                        savingAmount = 0;
                        excess = 0;
                    }

                    if (remaining_amount < 0 && age < 70) {
                        taxableInvestmentAmount = taxableInvestmentAmount + remaining_amount;
                        if (taxableInvestmentAmount < 0) {
                            goalFeasibility = false;
                            statusMsg.append("Insufficient balance in savings account");
                            //System.out.println("Insufficient balance in savings account");
                            break;
                        }
                        remaining_amount = 0;
                    }

                    final double limitForTaxableTemp = limitForTaxableAmount;
                    if ((currYearTaxableAmount < limitForTaxableAmount)) {
                        limitForTaxableAmount = limitForTaxableAmount - currYearTaxableAmount;
                        if (excess <= limitForTaxableAmount) {
                            limitForTaxableAmount = excess;
                            taxExcess = 0;
                        } else {
                            taxExcess = excess - limitForTaxableAmount;
                        }
                        if (taxExcess > 0) {
                            excess = taxExcess;
                        } else {
                            excess = 0;
                        }
                    } else {
                        limitForTaxableAmount = 0;
                    }
                    // ////System.out.println("limitForTaxableAmount.."+limitForTaxableAmount);
                    if ((currYearTaxableAmount < limitForTaxableTemp + limitForCollegeGoalTaxable) && excess > 0
                            && limitForCollegeGoalTaxable > 0) {
                        // ////System.out.println("currYearTaxableAmount====="+currYearTaxableAmount+"..limitForTaxableTemp.."+limitForTaxableTemp+"....limitForCollegeGoalTaxable=="+limitForCollegeGoalTaxable);
                        limitForCollegeGoalTaxable = limitForCollegeGoalTaxable
                                - (currYearTaxableAmount - limitForTaxableTemp);
                        if ((0.20 * excess) <= limitForCollegeGoalTaxable) {
                            limitForCollegeGoalTaxable = (0.20 * excess);
                            taxExcess = (0.80 * excess);
                        } else {
                            taxExcess = excess - limitForCollegeGoalTaxable;
                        }
                        if (taxExcess > 0) {
                            excess = taxExcess;
                        } else {
                            excess = 0;
                        }
                    } else {
                        limitForCollegeGoalTaxable = 0;
                    }

                    excessNonTax = 0;
                    double ftempBefore = tempTax.getDouble("federalTax");
                    double ftempEnd = 0;
                    final double incomeTemp = income.getJSONObject(i).getDouble("value");
                    //////System.out.println("excess outsie condition==="+excess);
                    if (excess <= 0) {
                        nonTaxableForLimits = 0;
                        ftempEnd = tempTax.getDouble("federalTax");
                        if (deductions != null) {
                            nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + excess
                                    + (growthRate * nonTaxableInvestmentAmount) / 100
                                    - deductions.getJSONObject(i).getDouble("nontaxable")
                                    - deductions.getJSONObject(i).getDouble("collegeGoalNontaxable");
                        } else {
                            nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + excess
                                    + (growthRate * nonTaxableInvestmentAmount) / 100;
                        }
                        ////System.out.println("Zero Excess. No iteration performed");
                    } else {
                        // ////System.out.println("Excess being iterated is: " +
                        // excess);

                        while (excess > 0) {
                            ////System.out.println("excess---112"+excess);
                            AGI = income.getJSONObject(i).getDouble("value") - excess;
                            tempTax = calTaxPerYear(0, 0, AGI, user_id, fin_id,
                                    userIncome.getJSONObject(i).getDouble("value"), spouseIncome, year,
                                    fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                    fillingExemtion.getJSONObject(i).getString("fillingStatus"), age, spouseAge,
                                    maritalStatus, kidBirthYear, state, kidcount, registrationYear, childArray,
                                    houseStatus, houseValue, housing_expense, startYear, remainingMortgageOriginal,
                                    remainingMortgageInterestRate, 0, stateTaxValue,taxableInvestmentAmount, nonTaxableInvestmentAmount,equity,finPlanDetails,houseAppreciationRate,registrationHouseStatus);
                            excess = incomeTemp - expenses - tempTax.getDouble("federalTax")
                                    - tempTax.getDouble("userSSTax") - tempTax.getDouble("fICAMedicareTax")
                                    - tempTax.getDouble("spouseSSTax") - tempTax.getDouble("stateTax") - savingAmount
                                    - limitForTaxableAmount - limitForCollegeGoalTaxable;
                            ftempEnd = tempTax.getDouble("federalTax");
                            //////System.out.println("AGI---1"+AGI);
                            //	////System.out.println("incomee---1"+income.getJSONObject(i).getDouble("value"));
                            //	////System.out.println("excess---1"+excess);
                            if (excess > maximumContributionForNonTaxable) {
                                nonTaxableForLimits = maximumContributionForNonTaxable;
                                //////System.out.println("nonTaxableForLimits==="+nonTaxableForLimits);
                                AGI = income.getJSONObject(i).getDouble("value") - maximumContributionForNonTaxable;
                                tempTax = calTaxPerYear(0, 0, AGI, user_id, fin_id,
                                        userIncome.getJSONObject(i).getDouble("value"), spouseIncome, year,
                                        fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                        fillingExemtion.getJSONObject(i).getString("fillingStatus"), age, spouseAge,
                                        maritalStatus, kidBirthYear, state, kidcount, registrationYear, childArray,
                                        houseStatus, houseValue, housing_expense, startYear, remainingMortgageOriginal,
                                        remainingMortgageInterestRate, 0, stateTaxValue,taxableInvestmentAmount, nonTaxableInvestmentAmount,equity,finPlanDetails,houseAppreciationRate,registrationHouseStatus);
                                excess = incomeTemp - expenses - tempTax.getDouble("federalTax")
                                        - tempTax.getDouble("userSSTax") - tempTax.getDouble("fICAMedicareTax")
                                        - tempTax.getDouble("spouseSSTax") - tempTax.getDouble("stateTax")
                                        - savingAmount - limitForTaxableAmount - limitForCollegeGoalTaxable;
                                ftempEnd = tempTax.getDouble("federalTax");
                                if (deductions != null) {
                                    nonTaxableInvestmentAmount = nonTaxableInvestmentAmount
                                            + maximumContributionForNonTaxable
                                            + (growthRate * nonTaxableInvestmentAmount) / 100
                                            - deductions.getJSONObject(i).getDouble("nontaxable")
                                            - deductions.getJSONObject(i).getDouble("collegeGoalNontaxable");
                                } else {
                                    nonTaxableInvestmentAmount = nonTaxableInvestmentAmount
                                            + maximumContributionForNonTaxable
                                            + (growthRate * nonTaxableInvestmentAmount) / 100;
                                }
                                excessNonTax = excess - maximumContributionForNonTaxable;
                                break;
                            }
                            if (!((ftempBefore - ftempEnd) <= 5)) {
                                ftempBefore = ftempEnd;
                            } else {
                                nonTaxableForLimits = excess;
                                ftempEnd = tempTax.getDouble("federalTax");
                                if (deductions != null) {
                                    nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + excess
                                            + (growthRate * nonTaxableInvestmentAmount) / 100
                                            - deductions.getJSONObject(i).getDouble("nontaxable")
                                            - deductions.getJSONObject(i).getDouble("collegeGoalNontaxable");
                                } else {
                                    nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + excess
                                            + (growthRate * nonTaxableInvestmentAmount) / 100;
                                }
                                excessNonTax = 0;
                                break;
                            }
                        }
                        // ////System.out.println("Elapsed time for sweeping excess:
                        // " + (System.nanoTime() - sweepingExcessTime) /
                        // 1000000 + " milli seconds");
                    }
                    // Money sweeping into non taxable buckets starts here...
                    JSONObject limitsTemp = new JSONObject();
                    limitsTemp = limits.getJSONObject(i);
                    // ////System.out.println("Ranjitha :: limitsTemp :" +limitsTemp+"userAge"+userAge+"spouseAge"+spouseAge+"year "+year+"growthRate"+growthRate+"nonTaxableForLimits.."+nonTaxableForLimits);
                    fourNotOneLimit = limitsTemp.getDouble("user401k");

                    user529Limit = limitUser529;
                    if(maritalStatus.equals("Yes"))
                    {
                        spouse529Limit=limitSpouse529;
                    }
                    // ////System.out.println("Ravi::::
                    // user529Limit=="+user529Limit);
                    // user529Limit=limitsTemp.getDouble("user529Plan");
                    iraLimit = limitsTemp.getDouble("userIRA");
                    if (i != 0) {
                        user401k = assets.getJSONObject(i - 1).getDouble("user401k");
                        userIRALimit = assets.getJSONObject(i - 1).getDouble("userIRA");
                        user529Plan = assets.getJSONObject(i - 1).getDouble("user529Plan");
                        userRothIRA = (userRothIRA + (userRothIRA * growthRate) / 100);

                    } else {
                        user401k = user.getUser401k() + userSavedAmount;
                        userIRALimit = user.getUserIRA() + userIraSavedAmount;
                        user529Plan = user.getUser529();
                        userRothIRA = user.getUserRothIra() + userRoughtIraSavedAmount;
                        userRothIRA = (userRothIRA + (userRothIRA * growthRate) / 100);
                    }
                    ////System.out.println("year .."+year+"user529Limit 529........"+user529Limit+"nonTaxableForLimits.."+nonTaxableForLimits);
                    if (user529Limit>0) {
                        ////System.out.println("Ravi :: user529Plan= if "+user529Limit);
                        if(nonTaxableForLimits >=14000)
                        {
                            //nonTaxableForLimits = nonTaxableForLimits - user529Limit;
                            if(user529Limit>=14000)
                            {
                                user529Plan = 14000 + (user529Plan + (user529Plan * growthRate) / 100);
                                nonTaxableForLimits = nonTaxableForLimits - 14000;
                                accumulation529=accumulation529-14000;
                            }
                            else
                            {
                                nonTaxableForLimits = nonTaxableForLimits - user529Limit;
                                user529Plan = user529Limit + (user529Plan + (user529Plan * growthRate) / 100);
                                accumulation529=0;
                            }
                        }
                        else
                        {
                            if(nonTaxableForLimits<user529Limit)
                            {
                                user529Plan = nonTaxableForLimits + (user529Plan + (user529Plan * growthRate) / 100);
                                nonTaxableForLimits = 0;
                            }
                            else{
                                user529Plan = user529Limit + (user529Plan + (user529Plan * growthRate) / 100);
                                nonTaxableForLimits = nonTaxableForLimits-user529Limit;
                            }
                        }
                    }
                    else
                    {
                        user529Plan =  (user529Plan + (user529Plan * growthRate) / 100);
                    }
                    if (spouse529Limit>0&&maritalStatus.equals("Yes")) {
                        // ////System.out.println("Ravi :: spouse529Plan= if
                        // "+spouse529Limit);

                        //nonTaxableForLimits = nonTaxableForLimits - spouse529Limit;
                        if(nonTaxableForLimits >=14000)
                        {
                            if(spouse529Limit>=14000)
                            {
                                spouse529Plan = 14000 + (spouse529Plan + (spouse529Plan * growthRate)) / 100;

                                nonTaxableForLimits = nonTaxableForLimits - 14000;
                                accumulation529=accumulation529-14000;
                            }
                            else
                            {
                                nonTaxableForLimits = nonTaxableForLimits - spouse529Limit;
                                spouse529Plan = spouse529Limit + (spouse529Plan + (spouse529Plan * growthRate)) / 100;
                                accumulation529=0;
                            }
                        }
                        else
                        {
                            if(nonTaxableForLimits<spouse529Limit)
                            {
                                spouse529Plan = nonTaxableForLimits + (spouse529Plan + (spouse529Plan * growthRate)) / 100;
                                nonTaxableForLimits = 0;
                            }
                            else{
                                spouse529Plan = spouse529Limit + (spouse529Plan + (spouse529Plan * growthRate)) / 100;
                                nonTaxableForLimits = nonTaxableForLimits-spouse529Limit;
                            }

                        }
                    }
                    else
                    {
                        spouse529Plan =  (spouse529Plan + (spouse529Plan * growthRate)) / 100;
                    }
                    //	////System.out.println("year .."+year+"accumulation 529........"+accumulation529+"nonTaxableForLimits.."+nonTaxableForLimits);
                    if (fillingExemtion.getJSONObject(i).getString("fillingStatus").equals("Married Filing Jointly")
                            && spouse_income.getJSONObject(i).getDouble("value") != 0) {
                        spouseTempTaxable = nonTaxableForLimits / 2;
                        if (spouseTempTaxable < 0) {
                            spouseTempTaxable = 0;
                        }
                        nonTaxableForLimits = nonTaxableForLimits / 2;
                    } else if (fillingExemtion.getJSONObject(i).getString("fillingStatus").equals(
                            "Married Filing Jointly") && spouse_income.getJSONObject(i).getDouble("value") == 0) {
                        if (i != 0) {
                            spouse401k = assets.getJSONObject(i - 1).getDouble("spouse401k");
                            spouseIRALimit = assets.getJSONObject(i - 1).getDouble("spouseIRA");
                            spouse529Plan = assets.getJSONObject(i - 1).getDouble("spouse529Plan");
                            userRothIRA = (userRothIRA + (userRothIRA * growthRate) / 100);
                            spouseRothIRA = (spouseRothIRA + (spouseRothIRA * growthRate) / 100);

                        } else {
                            spouse401k = user.getSpouse401k() + spouseSavedAmount;
                            spouse401k = (spouse401k + (spouse401k * growthRate) / 100);
                            spouseIRALimit = user.getSpouseIRA() + spouseIraSavedAmount;
                            spouseIRALimit = (spouseIRALimit + (spouseIRALimit * growthRate) / 100);
                            spouse529Plan = user.getSpousePlan529();
                            spouse529Plan = (spouse529Plan + (spouse529Plan * growthRate) / 100);
                            spouseRothIRA = user.getSpouseRothIra() + spouseRoughtIraSavedAmount;
                            spouseRothIRA = (spouseRothIRA + (spouseRothIRA * growthRate) / 100);
                        }


                    }
                    if (nonTaxableForLimits <= fourNotOneLimit) {
                        // ////System.out.println("Ravi :: user401k=
                        // "+nonTaxableForLimits);
                        user401k = nonTaxableForLimits + (user401k + (user401k * growthRate) / 100);
                        userIRALimit = userIRALimit + (userIRALimit * growthRate) / 100;
                        // user529Plan = user529Plan + (user529Plan *
                        // growthRate) / 100;
                    } else {
                        // ////System.out.println("Ravi :: user401k= new
                        // "+fourNotOneLimit);
                        user401k = fourNotOneLimit + (user401k + (user401k * growthRate) / 100);
                        tempExcess = nonTaxableForLimits - fourNotOneLimit;
                        if (tempExcess < iraLimit) {
                            // ////System.out.println("Ravi :: userIRALimit=
                            // "+tempExcess);
                            userIRALimit = tempExcess + (userIRALimit + (userIRALimit * growthRate) / 100);
                            // user529Plan = user529Plan + (user529Plan *
                            // growthRate) / 100;
                        } else {
                            // ////System.out.println("Ravi :: userIRALimit=
                            // "+iraLimit);
                            userIRALimit = iraLimit + (userIRALimit + (userIRALimit * growthRate) / 100);
                            tempExcess = tempExcess - iraLimit;
                            // ////System.out.println("tempExcess :: user
                            // "+tempExcess);
                            if (fillingExemtion.getJSONObject(i).getString("fillingStatus")
                                    .equals("Married Filing Jointly")
                                    && spouse_income.getJSONObject(i).getDouble("value") == 0) {
                                // user529Plan=0;
                                spouseIRALimit = tempExcess + (spouseIRALimit + (spouseIRALimit * growthRate) / 100);
                            }
                            userTempExcess = tempExcess;
                        }
                    }
                    // ////System.out.println("Ranjitha ::spouse_income outside
                    // tempExcess user401k "+user401k+"user 529
                    // "+user529Plan+"spouseIRALimit"+spouseIRALimit+"userTempExcess.."+userTempExcess);
                    if (spouse_income.length() > 0
                            && fillingExemtion.getJSONObject(i).getString("fillingStatus")
                            .equals("Married Filing Jointly")
                            && spouse_income.getJSONObject(i).getDouble("value") != 0) {

                        if (i != 0) {
                            spouse401k = assets.getJSONObject(i - 1).getDouble("spouse401k");
                            spouseIRALimit = assets.getJSONObject(i - 1).getDouble("spouseIRA");
                            spouse529Plan = assets.getJSONObject(i - 1).getDouble("spouse529Plan");
                            spouse529Plan = (spouse529Plan + (spouse529Plan * growthRate) / 100);
                            spouseRothIRA = (spouseRothIRA + (spouseRothIRA * growthRate) / 100);
                            //userRothIRA = (userRothIRA + (userRothIRA * growthRate) / 100);
                        } /*else {
							////System.out.println("Ranjitha inside marriage"+spouseRoughtIraSavedAmount+"spouse401kContribution "+spouse401kContribution+"spouseSavedAmount.."+spouseSavedAmount+"spouseIraSavedAmount .."+spouseIraSavedAmount);

							spouse401k = user.getSpouse401k() + spouseSavedAmount;
							spouseIRALimit = user.getSpouseIRA() + spouseIraSavedAmount;
							spouse529Plan = user.getSpousePlan529();
							spouse529Plan = (spouse529Plan + (spouse529Plan * growthRate) / 100);
							spouseRothIRA = user.getSpouseRothIra() + spouseRoughtIraSavedAmount;
							spouseRothIRA = (spouseRothIRA + (spouseRothIRA * growthRate) / 100);

						}*/
                        if((flag==false && spouse_income.getJSONObject(i).getDouble("value")>0)|| i==0)
                        {
                            //////System.out.println("Ranji : : spouse income "+spouse_income.getJSONObject(i).getDouble("value")+"  "+spouse_income.length());
                            if(spouse_income.getJSONObject(i).getDouble("value")>0)
                            {
                                //	////System.out.println("Ranjitha : : inside marriage"+year);
                                spouse401k = user.getSpouse401k() + spouseSavedAmount;
                                spouseIRALimit = user.getSpouseIRA() + spouseIraSavedAmount;
                                spouse529Plan = user.getSpousePlan529();
                                spouse529Plan = (spouse529Plan + (spouse529Plan * growthRate) / 100);
                                spouseRothIRA = user.getSpouseRothIra() + spouseRoughtIraSavedAmount;
                                spouseRothIRA = (spouseRothIRA + (spouseRothIRA * growthRate) / 100);
                                flag=true;
                            }
                        }





                        spouseFourNotOne = limitsTemp.getDouble("spouse401k");
                        spouseIraLimit = limitsTemp.getDouble("spouseIRA");
                        if (i != 0) {
                            spouse529Limit = limitSpouse529;
                        }

                        // ////System.out.println("Ravi::::
                        // spouse529Limit=="+spouse529Limit);
                        // spouse529Limit=limitsTemp.getDouble("spouse529Plan");

                        if (spouseTempTaxable <= spouseFourNotOne) {
                            // ////System.out.println("Ravi ::spouse401k=
                            // "+spouseTempTaxable);
                            spouse401k = spouseTempTaxable + (spouse401k + (spouse401k * growthRate) / 100);
                            spouseIRALimit = spouseIRALimit + (spouseIRALimit * growthRate) / 100;
                            // spouse529Plan = spouse529Plan + (spouse529Plan *
                            // growthRate) / 100;
                        } else {
                            // ////System.out.println("Ravi ::spouse401k=
                            // "+spouseFourNotOne);
                            spouse401k = spouseFourNotOne + (spouse401k + (spouse401k * growthRate) / 100);
                            tempExcess = spouseTempTaxable - spouseFourNotOne;
                            if (userAge >= 50 && spouseAge < 50) {
                                if (nonTaxableForLimits < fourNotOneLimit && spouseTempTaxable > spouseFourNotOne) {
                                    user401k = user401k + tempExcess;
                                    spouseIRALimit = (spouseIRALimit + (spouseIRALimit * growthRate) / 100);
                                }
                                // ////System.out.println("Ranjitha before age
                                // variation userIRALimit "+user401k);
                                if (tempExcess > spouseIraLimit && spouseIraLimit != 0) {
                                    spouseIRALimit = spouseIraLimit
                                            + (spouseIRALimit + (spouseIRALimit * growthRate) / 100);
                                    tempExcess = tempExcess - spouseIraLimit;
                                    // ////System.out.println("spouseIRALimit..."+spouseIRALimit+"tempExcess.."+tempExcess+"userIRALimit.."+userIRALimit);
                                    userIRALimit = userIRALimit + tempExcess;
                                    // ////System.out.println("Ranjitha after age
                                    // variation userIRALimit"+userIRALimit);
                                }
                            } else {
                                if (tempExcess < spouseIraLimit) {
                                    // ////System.out.println("Ravi
                                    // ::spouseIRALimit= 1"+tempExcess);
                                    spouseIRALimit = tempExcess
                                            + (spouseIRALimit + (spouseIRALimit * growthRate) / 100);
                                    // spouse529Plan = spouse529Plan +
                                    // (spouse529Plan * growthRate) / 100;
                                } else {
                                    // ////System.out.println("Ravi
                                    // ::spouseIRALimit= 2"+spouseIraLimit);
                                    spouseIRALimit = spouseIraLimit
                                            + (spouseIRALimit + (spouseIRALimit * growthRate) / 100);
                                    tempExcess = tempExcess - spouseIraLimit;
                                    // ////System.out.println("tempExcess.."+tempExcess);
                                    if (tempExcess > 0 && spouse401k == 0) {
                                        // ////System.out.println("Ranjitha ::
                                        // inside user401k "+user401k +"
                                        // "+userIRALimit);
                                        if (nonTaxableForLimits < fourNotOneLimit) {
                                            final double tempVal = fourNotOneLimit - nonTaxableForLimits;
                                            user401k = user401k + tempVal;
                                            tempExcessNew = tempExcess - tempVal;
                                            // ////System.out.println("tempExcessNew
                                            // :: "+tempExcessNew);
                                        }
                                        if (tempExcessNew <= iraLimit) {
                                            userIRALimit = userIRALimit + tempExcessNew;
                                        }
                                    }
                                }
                            }
                        }
                        if (userTempExcess > 0 && user401k == 0) {
                            // ////System.out.println("Ranjitha :: inside spouse401k
                            // "+spouse401k +" "+spouseIRALimit);
                            if (spouseTempTaxable < spouseFourNotOne) {
                                final double tempSpouseVal = spouseFourNotOne - spouseTempTaxable;
                                spouse401k = spouse401k + tempSpouseVal;
                                spouseTempExcess = userTempExcess - tempSpouseVal;
                            }
                            if (spouseTempExcess <= spouseIraLimit) {
                                spouseIRALimit = spouseIRALimit + spouseTempExcess;
                            }
                        }

                    }
                    if (i != 0
                            && fillingExemtion.getJSONObject(i).getString("fillingStatus")
                            .equals("Married Filing Jointly")
                            && spouse_income.getJSONObject(i).getDouble("value") == 0) {
                        // ////System.out.println("Ranjitha :: inside excess=0 " );
                        spouse529Plan = (spouse529Plan + (spouse529Plan * growthRate) / 100);
                        spouseRothIRA = (spouseRothIRA + (spouseRothIRA * growthRate) / 100);
                        if (nonTaxableForLimits == 0) {
                            spouseIRALimit = spouseIRALimit + (spouseIRALimit * growthRate) / 100;
                        }
                        spouse401k = (spouse401k + (spouse401k * growthRate) / 100);
                        userRothIRA = (userRothIRA + (userRothIRA * growthRate) / 100);
                    }
                    // ////System.out.println("Ranjitha :: All contributions
                    // nonTaxableForLimits"+nonTaxableForLimits+
                    // "spouse401k"+spouse401k+"spouseIRALimit"+spouseIRALimit+"spouse529Plan"+spouse529Plan+"user401k"+user401k
                    // +"userira" + userIRALimit + "user529Plan" + user529Plan);
                    // ////System.out.println("Ravi:::::year"+year+"---user529Plan="+user529Plan+"..spouse529Plan="+spouse529Plan);
                    if(housing_expense!=null)
                    {
                        //////System.out.println("Ranjii taxableInvestmentAmount "+taxableInvestmentAmount+" "+year +" extramoney "+housing_expense.getJSONObject("housing_expense").getInt("remainingTaxableAmt")+"excess "+excess+"excessNonTax.."+excessNonTax);
                        for(int j=0; j<housing_expense.length();j++)
                        {
                            if( housing_expense.getJSONObject(j).getString("frequency")!=null)
                            {
                                if(year==housing_expense.getJSONObject(j).getInt("startYear") && housing_expense.getJSONObject(j).getString("frequency").equals("Replace first house") && housing_expense.getJSONObject(j).getInt("remainingTaxableAmt")>0)
                                {
                                    //////System.out.println("Ranju 11: : inside exta money ");
                                    taxableInvestmentAmount=taxableInvestmentAmount+(housing_expense.getJSONObject(j).getInt("remainingTaxableAmt"));
                                }
                                if(year==housing_expense.getJSONObject(j).getInt("startYear") && housing_expense.getJSONObject(j).getString("frequency").equals("Replace first house") && housing_expense.getJSONObject(j).getInt("remainingTaxable")>0)
                                {
                                    //////System.out.println("Ranju>>: : inside exta money ");
                                    taxableInvestmentAmount=taxableInvestmentAmount+(housing_expense.getJSONObject(j).getInt("remainingTaxable"));
                                }
                            }
                        }
                    }
                    if (deductions != null) {
                        //////System.out.println("user529Plan==="+user529Plan+"deduction==="+deductions.getJSONObject(i).getDouble("collegeGoalNontaxable"));
                        user529Plan = user529Plan - deductions.getJSONObject(i).getDouble("collegeGoalNontaxable");
                        //////System.out.println("deductions===user529Plan==="+user529Plan);
                        if (user529Plan < 0) {
                            spouse529Plan = spouse529Plan + user529Plan;
                            user529Plan = 0;
                            // ////System.out.println("deductions===spouse529Plan==="+spouse529Plan);
                        }
                        // ////System.out.println("Ranjitha :: college user529Plan
                        // one "+user529Plan+"spouse529Plan.."+spouse529Plan);
                    }
                    // Money sweeping into non taxable buckets ends here...
                    tax.getJSONObject(i).put("federalTax", ftempEnd).put("userSSTax", tempTax.getDouble("userSSTax"))
                    .put("fICAMedicareTax", tempTax.getDouble("fICAMedicareTax"))
                    .put("spouseSSTax", tempTax.getDouble("spouseSSTax"))
                    .put("stateTax", tempTax.getDouble("stateTax"));
                    equityReturn=tempTax.getJSONArray("equity");
                    if (deductions != null) {
                        if (assets.getJSONObject(i).getInt("year") == year
                                && deductions.getJSONObject(i).getInt("year") == year) {
                            taxableInvestmentAmount = taxableInvestmentAmount
                                    + ((growthRate * taxableInvestmentAmount) / 100) + excessNonTax
                                    + limitForTaxableAmount + limitForCollegeGoalTaxable
                                    - deductions.getJSONObject(i).getDouble("taxable");
                            if (taxableInvestmentAmount < 0) {
                                remaining_amount = remaining_amount + taxableInvestmentAmount;
                                taxableInvestmentAmount = 0;
                                if (remaining_amount < 0) {
                                    goalFeasibility = false;
                                    statusMsg.append("Insufficient balance in taxable account");
                                    //System.out.println("Insufficient balance in taxable account");
                                    break;
                                }
                            }
                            taxableInvestmentAmount = taxableInvestmentAmount
                                    - deductions.getJSONObject(i).getDouble("collegeGoalTaxable");
                            if (taxableInvestmentAmount < 0) {
                                nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + taxableInvestmentAmount;
                                taxableInvestmentAmount = 0;
                                goalFeasibility = false;
                                statusMsg.append("Insufficient balance in taxable account for college goal");
                                //System.out.println("Insufficient balance in	 taxable account for college goal");
                                break;
                            }
                            if (nonTaxableInvestmentAmount < 0 || user529Plan < 0 || spouse529Plan < 0) {
                                goalFeasibility = false;
                                statusMsg.append("Insufficient balance in non-taxble account");
                                //System.out.println("Insufficient balance in non taxble account");
                                break;
                            }

                            //////System.out.println("saving..." + remaining_amount
                            //+ "taxable..." + taxableInvestmentAmount +"nontaxbale..." + nonTaxableInvestmentAmount);
                        } else {
                            break;
                        }
                    } else {
                        if (assets.getJSONObject(i).getInt("year") == year) {
                            taxableInvestmentAmount = taxableInvestmentAmount
                                    + ((growthRate * taxableInvestmentAmount) / 100) + excessNonTax
                                    + limitForTaxableAmount + limitForCollegeGoalTaxable;
                            if (taxableInvestmentAmount < 0) {
                                remaining_amount = remaining_amount + taxableInvestmentAmount;
                                taxableInvestmentAmount = 0;
                                if (remaining_amount < 0) {
                                    goalFeasibility = false;
                                    statusMsg.append("Insufficient balance in taxable account");
                                    //System.out.println("Insufficient balance in taxable account");
                                    break;
                                }
                            }
                            // ////System.out.println("saving..." + remaining_amount
                            // + "taxable..." + taxableInvestmentAmount +
                            // "nontaxbale..." + nonTaxableInvestmentAmount);
                        } else {
                            break;
                        }
                    }
                    // ////System.out.println("Ranjtha :: before db "+user401k+" "+
                    // userIRALimit+" "+spouse401k+"
                    // "+spouseIRALimit+"user529Plan
                    // "+user529Plan+"spouse529Plan "+spouse529Plan);
                    //	////System.out.println("housing_expense.."+housing_expense);

                    assets.getJSONObject(i)
                    .put("nontaxable_investment_amount",
                            (user401k + user401kContribution + userIRALimit + userRothIRA + user529Plan
                                    + spouse401k + spouse401kContribution + spouseIRALimit + spouseRothIRA
                                    + spouse529Plan));
                    assets.getJSONObject(i).put("taxable_investment_amount", taxableInvestmentAmount);
                    assets.getJSONObject(i).put("savings", remaining_amount);
                    assets.getJSONObject(i).put("user401k", user401k + user401kContribution);
                    assets.getJSONObject(i).put("userIRA", userIRALimit);
                    assets.getJSONObject(i).put("userRothIRA", userRothIRA);
                    assets.getJSONObject(i).put("user529Plan", user529Plan);
                    assets.getJSONObject(i).put("spouse401k", spouse401k + spouse401kContribution);
                    assets.getJSONObject(i).put("spouseIRA", spouseIRALimit);
                    assets.getJSONObject(i).put("spouseRothIRA", spouseRothIRA);
                    assets.getJSONObject(i).put("spouse529Plan", spouse529Plan);
                    limitForSavingAcount = initialLimit;
                    //	////System.out.println("mahi :::::::::::::"+assets.getJSONObject(i));
                    // "+i);

                    age++;
                    if (spouseAge != 0) {
                        spouseAge++;
                    }

                    userAge = age;
                } else {
                    // =======================Retirement year calculation starts
                    // here=================================================
                    ////System.out.println("entering the retirement years");
                    JSONObject responseFromGrowthRate = new JSONObject();
                    final InvestmentPortfolioImpl investmentPortfolioImpl = new InvestmentPortfolioImpl();
                    if ((userAge < 70 && spouseAge < 70) && (assets.getJSONObject(i).getInt("year") == userStartYear
                            || (assets.getJSONObject(i).getInt("year") == spouseStartYear))) {
                        ////System.out.println("user age less than 70");
                        double saving = assets.getJSONObject(i - 1).getDouble("savings");
                        double savingLimite = 0;

                        if (limits != null) {
                            if (emergencyFundFlag == false) {
                                savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2)
                                        + limits.getJSONObject(i).getInt("saving");
                            } else {

                                if (emergencyType.equals("Fix Amount")) {
                                    savingLimite = (emergencyFundAmt) + limits.getJSONObject(i).getInt("saving");
                                } else if (emergencyType.equals("Expense")) {

                                    savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                            * (Integer.parseInt(monthsOfExpense)))
                                            + limits.getJSONObject(i).getInt("saving");
                                } else if (emergencyType.equals("Income")) {

                                    savingLimite = (income.getJSONObject(i).getDouble("value") / 12
                                            * (Integer.parseInt(monthsOfIncome)))
                                            + limits.getJSONObject(i).getInt("saving");
                                }

                            }
                        } else {
                            if (emergencyFundFlag == false) {
                                savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2);
                            } else {

                                if (emergencyType.equals("Fix Amount")) {
                                    savingLimite = (emergencyFundAmt);
                                } else if (emergencyType.equals("Expense")) {

                                    savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                            * (Integer.parseInt(monthsOfExpense)));
                                } else if (emergencyType.equals("Income")) {

                                    savingLimite = (income.getJSONObject(i).getDouble("value") / 12
                                            * (Integer.parseInt(monthsOfIncome)));
                                }

                            }
                        }
                        saving = savingLimite;
                        if (maritalStatus.equals("Yes")) {
                            spouseSSB = spouse_income.getJSONObject(i).getDouble("value");
                            yearlySSB = spouse_income.getJSONObject(i).getDouble("value")
                                    + userIncome.getJSONObject(i).getDouble("value");

                            responseFromGrowthRate = investmentPortfolioImpl.growthRate(riskScore, 2, userAge,
                                    MongoDBConnection.investmentPortfolioData);
                        } else {

                            yearlySSB = userIncome.getJSONObject(i).getDouble("value");
                            responseFromGrowthRate = investmentPortfolioImpl.growthRate(riskScore, 1, userAge,
                                    MongoDBConnection.investmentPortfolioData);
                        }
                        growthRate = responseFromGrowthRate.getDouble("growthRate") / 100;
                        //	////System.out.println("growthRate----------="+growthRate);
                        userSSB = userIncome.getJSONObject(i).getDouble("value");

                        final JSONObject federalJson = calRetirementTax(
                                fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                fillingExemtion.getJSONObject(i).getString("fillingStatus"), fin_id, incomeRothIRA,
                                yearlySSB, userExpense.getJSONObject(i).getDouble("totalExpense"), user_id, 0.0,
                                userAge, spouseAge, userSSB, spouseSSB, 0, goal_id, year, maritalStatus, kidBirthYear,
                                state, kidcount, registrationYear, childArray, houseStatus, houseValue, housing_expense,
                                startYear, remainingMortgageOriginal, remainingMortgageInterestRate, spouseBirthYear,
                                userBirthYear, spouseAgeInFinPlan, spouseRetirementStartYear, userRetirementStartYear,
                                stateTaxValue,equity,houseAppreciationRate,registrationHouseStatus);

                        final double accessMony = federalJson.getDouble("withdrawalFromRetirementAccounts") - 0
                                - federalJson.getDouble("federalTax") - federalJson.getDouble("stateTax")
                                - federalJson.getDouble("userSSTax") - federalJson.getDouble("spouseSSTax")
                                - federalJson.getDouble("fICAMedicareTax");

                        if (accessMony >= 0 && (yearlySSB > userExpense.getJSONObject(i).getDouble("totalExpense"))) {
                            assets.getJSONObject(i).put("taxable_investment_amount",
                                    assets.getJSONObject(i - 1).getDouble("taxable_investment_amount") + accessMony
                                    + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                            * growthRate));
                            if (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") <= 0) {
                                assets.getJSONObject(i).put("user401k", 0);
                                assets.getJSONObject(i).put("spouse401k", 0);
                                assets.getJSONObject(i).put("userIRA", 0);
                                assets.getJSONObject(i).put("spouseIRA", 0);
                                assets.getJSONObject(i).put("nontaxable_investment_amount", 0);
                                assets.getJSONObject(i).put("spouseRothIRA", 0);
                                assets.getJSONObject(i).put("userRothIRA", 0);

                            } else {

                                assets.getJSONObject(i).put("user401k",
                                        assets.getJSONObject(i - 1).getDouble("user401k")
                                        + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate);
                                assets.getJSONObject(i).put("spouse401k",
                                        assets.getJSONObject(i - 1).getDouble("spouse401k")
                                        + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate);
                                assets.getJSONObject(i).put("userIRA", assets.getJSONObject(i - 1).getDouble("userIRA")
                                        + assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate);
                                assets.getJSONObject(i).put("spouseIRA",
                                        assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                        + assets.getJSONObject(i - 1).getDouble("spouseIRA") * growthRate);
                                assets.getJSONObject(i).put("spouseRothIRA", assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                        + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);
                                assets.getJSONObject(i).put("userRothIRA", assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                        + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);
                                if (userStartYearForCap > assets.getJSONObject(i).getInt("year")) {
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i).getDouble("user401k") + user401kContribution);

                                }

                                if (spouseStartYearForCap > assets.getJSONObject(i).getInt("year")) {
                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i).getDouble("spouse401k") + spouse401kContribution);

                                }
                                assets.getJSONObject(i).put("nontaxable_investment_amount",
                                        assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i).getDouble("spouseIRA")
                                        + assets.getJSONObject(i).getDouble("spouseRothIRA")
                                        + assets.getJSONObject(i).getDouble("userRothIRA"));
                            }

                            assets.getJSONObject(i).put("savings", saving);
                            tax.getJSONObject(i).put("federalTax", federalJson.getDouble("federalTax"));
                            tax.getJSONObject(i).put("fICAMedicareTax", federalJson.getDouble("fICAMedicareTax"));
                            tax.getJSONObject(i).put("fICASocialSecurityTax",
                                    federalJson.getDouble("fICASocialSecurityTax"));
                            tax.getJSONObject(i).put("userSSTax", federalJson.getDouble("userSSTax"));
                            tax.getJSONObject(i).put("stateTax", federalJson.getDouble("stateTax"));
                            tax.getJSONObject(i).put("spouseSSTax", federalJson.getDouble("spouseSSTax"));


                            /*						////System.out.println("ira===previous"+ assets.getJSONObject(i).getDouble("userIRA"));
							////System.out.println("ira===next"+ assets.getJSONObject(i-1).getDouble("userIRA"));
							////System.out.println("rothira===currebnt"+ assets.getJSONObject(i).getDouble("userRothIRA"));
							////System.out.println("rothira===next"+ assets.getJSONObject(i-1).getDouble("userRothIRA"));
							////System.out.println("accessMony====="+accessMony);*/
                        } else {
                            final double nontaxtableWithdrawlAmount = federalJson.getDouble("incomeIRA");
                            if (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") <= 0) {
                                assets.getJSONObject(i).put("user401k", 0);
                                assets.getJSONObject(i).put("spouse401k", 0);
                                assets.getJSONObject(i).put("userIRA", 0);
                                assets.getJSONObject(i).put("spouseIRA", 0);
                                assets.getJSONObject(i).put("spouseRothIRA", 0);
                                assets.getJSONObject(i).put("userRothIRA", 0);
                                assets.getJSONObject(i).put("nontaxable_investment_amount", 0);

                            } else {


                                if (assets.getJSONObject(i - 1).getDouble("user401k") > 0
                                        && assets.getJSONObject(i - 1).getDouble("spouse401k") > 0) {

                                    if (assets.getJSONObject(i).getInt("year") < spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") >= userStartYear)

                                    {

                                        assets.getJSONObject(i).put("user401k",
                                                (assets.getJSONObject(i - 1).getDouble("user401k")
                                                        - nontaxtableWithdrawlAmount) * 1.025);

                                        assets.getJSONObject(i).put("spouse401k",
                                                assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate+spouse401kContribution);

                                    }

                                    else if (assets.getJSONObject(i).getInt("year") >= spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") < userStartYear)

                                    {

                                        assets.getJSONObject(i).put("spouse401k",
                                                (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                        - nontaxtableWithdrawlAmount) * 1.025);

                                        assets.getJSONObject(i).put("user401k",
                                                assets.getJSONObject(i - 1).getDouble("user401k")
                                                + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate+user401kContribution);

                                    }

                                    else if (assets.getJSONObject(i).getInt("year") >= spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") >= userStartYear)

                                    {

                                        assets.getJSONObject(i).put("user401k",
                                                (assets.getJSONObject(i - 1).getDouble("user401k")
                                                        - nontaxtableWithdrawlAmount / 2) * 1.025);

                                        assets.getJSONObject(i).put("spouse401k",
                                                (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                        - nontaxtableWithdrawlAmount / 2) * 1.025);

                                    }

                                    assets.getJSONObject(i).put("userIRA",
                                            assets.getJSONObject(i - 1).getDouble("userIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate);

                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseIRA") * growthRate);


                                    assets.getJSONObject(i).put("spouseRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);

                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);

                                } else if (assets.getJSONObject(i - 1).getDouble("spouse401k") > 0
                                        && assets.getJSONObject(i - 1).getDouble("user401k") <= 0) {
                                    if (assets.getJSONObject(i).getInt("year") >= spouseStartYear) {
                                        assets.getJSONObject(i).put("spouse401k",
                                                (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                        - nontaxtableWithdrawlAmount) * 1.025);
                                    }
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("userIRA",
                                            assets.getJSONObject(i - 1).getDouble("userIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate);
                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseIRA") * growthRate);
                                    assets.getJSONObject(i).put("spouseRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);

                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);
                                } else if (assets.getJSONObject(i - 1).getDouble("spouse401k") <= 0
                                        && assets.getJSONObject(i - 1).getDouble("user401k") > 0) {
                                    assets.getJSONObject(i).put("spouse401k", 0);
                                    if (assets.getJSONObject(i).getInt("year") >= userStartYear) {
                                        assets.getJSONObject(i).put("user401k",
                                                (assets.getJSONObject(i - 1).getDouble("user401k")
                                                        - nontaxtableWithdrawlAmount) * 1.025);
                                    }
                                    assets.getJSONObject(i).put("userIRA",
                                            assets.getJSONObject(i - 1).getDouble("userIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate);
                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseIRA") * growthRate);
                                    assets.getJSONObject(i).put("spouseRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);

                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);
                                } else if (assets.getJSONObject(i - 1).getDouble("userIRA") > 0
                                        && assets.getJSONObject(i - 1).getDouble("spouseIRA") > 0) {

                                    if (assets.getJSONObject(i).getInt("year") < spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") >= userStartYear)

                                    {

                                        assets.getJSONObject(i).put("userIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                        - nontaxtableWithdrawlAmount) * 1.025);

                                    }

                                    else if (assets.getJSONObject(i).getInt("year") >= spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") < userStartYear)

                                    {

                                        assets.getJSONObject(i).put("spouseIRA",
                                                (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                        - nontaxtableWithdrawlAmount) * 1.025);

                                    }

                                    else if (assets.getJSONObject(i).getInt("year") >= spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") >= userStartYear)

                                    {

                                        assets.getJSONObject(i).put("userIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                        - nontaxtableWithdrawlAmount / 2) * 1.025);

                                        assets.getJSONObject(i).put("spouseIRA",
                                                (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                        - nontaxtableWithdrawlAmount / 2) * 1.025);

                                    }

                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i - 1).getDouble("spouse401k")
                                            + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate);

                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i - 1).getDouble("user401k")
                                            + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate);

                                } else if (assets.getJSONObject(i - 1).getDouble("spouseIRA") > 0
                                        && assets.getJSONObject(i - 1).getDouble("userIRA") <= 0) {

                                    if (assets.getJSONObject(i).getInt("year") >= spouseStartYear) {
                                        assets.getJSONObject(i).put("spouseIRA",
                                                (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                        - nontaxtableWithdrawlAmount) * 1.025);
                                    }
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i - 1).getDouble("user401k")
                                            + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate);

                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i - 1).getDouble("spouse401k")
                                            + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate);

                                    assets.getJSONObject(i).put("userIRA", 0);
                                } else if (assets.getJSONObject(i - 1).getDouble("spouseIRA") <= 0
                                        && assets.getJSONObject(i - 1).getDouble("userIRA") > 0) {
                                    if (assets.getJSONObject(i).getInt("year") >= userStartYear) {
                                        assets.getJSONObject(i).put("userIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                        - nontaxtableWithdrawlAmount) * 1.025);
                                    }
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i - 1).getDouble("user401k")
                                            + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate);
                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i - 1).getDouble("spouse401k")
                                            + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate);
                                    assets.getJSONObject(i).put("spouseIRA", 0);



                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);
                                    assets.getJSONObject(i).put("spouseRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);



                                } else {
                                    statusMsg.append("Insufficient balance in Non taxable account");
                                    responseObj.put("status", "fail");
                                    responseObj.put("statusMsg", statusMsg);
                                    return responseObj;
                                }

                                assets.getJSONObject(i).put("nontaxable_investment_amount",
                                        assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i).getDouble("spouseIRA")
                                        + assets.getJSONObject(i).getDouble("userRothIRA")
                                        + assets.getJSONObject(i).getDouble("spouseRothIRA"));
                            }
                            assets.getJSONObject(i).put("taxable_investment_amount",
                                    assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                    + assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                    * growthRate);
                            assets.getJSONObject(i).put("savings", saving);
                            tax.getJSONObject(i).put("federalTax", federalJson.getDouble("federalTax"));
                            tax.getJSONObject(i).put("fICAMedicareTax", federalJson.getDouble("fICAMedicareTax"));
                            tax.getJSONObject(i).put("fICASocialSecurityTax",
                                    federalJson.getDouble("fICASocialSecurityTax"));
                            tax.getJSONObject(i).put("userSSTax", federalJson.getDouble("userSSTax"));
                            tax.getJSONObject(i).put("stateTax", federalJson.getDouble("stateTax"));
                            tax.getJSONObject(i).put("spouseSSTax", federalJson.getDouble("spouseSSTax"));
                            /*						////System.out.println("ira==1=previous"+ assets.getJSONObject(i).getDouble("userIRA"));
							////System.out.println("ira=1==next"+ assets.getJSONObject(i-1).getDouble("userIRA"));
							////System.out.println("rothira==1=currebnt"+ assets.getJSONObject(i).getDouble("userRothIRA"));
							////System.out.println("rothira===1next"+ assets.getJSONObject(i-1).getDouble("userRothIRA"));
							////System.out.println("nontaxtableWithdrawlAmount===1=="+nontaxtableWithdrawlAmount);*/
                        }
                        //----------------------new code for529----aaaa--------------------------
                        if(deductions!=null && deductions.getJSONObject(i).getDouble("collegeGoalNontaxable")!=0)
                        {
                            assets.getJSONObject(i).put("user529Plan",( assets.getJSONObject(i-1).getDouble("user529Plan")-deductions.getJSONObject(i).getDouble("collegeGoalNontaxable"))*1.025);
                            assets.getJSONObject(i).put("spouse529Plan", (assets.getJSONObject(i-1).getDouble("spouse529Plan")-deductions.getJSONObject(i).getDouble("collegeGoalNontaxable"))*1.025);

                        }
                        else
                        {
                            assets.getJSONObject(i).put("user529Plan", assets.getJSONObject(i-1).getDouble("user529Plan")+assets.getJSONObject(i-1).getDouble("user529Plan")*growthRate);
                            assets.getJSONObject(i).put("spouse529Plan", assets.getJSONObject(i-1).getDouble("spouse529Plan")+assets.getJSONObject(i-1).getDouble("spouse529Plan")*growthRate);

                        }/*assets.getJSONObject(i).put("nontaxable_investment_amount",
								assets.getJSONObject(i).getDouble("nontaxable_investment_amount")
								+assets.getJSONObject(i).getDouble("spouse529Plan")
								 + assets.getJSONObject(i).getDouble("user529Plan"));*/
                        //////System.out.println("user------"+assets.getJSONObject(i-1).getDouble("user529Plan"));
                        //////System.out.println("user------"+assets.getJSONObject(i).getDouble("user529Plan"));
                        if (assets.getJSONObject(i).getInt("year") == userStartYear) {
                            userStartYear++;

                        }
                        if (assets.getJSONObject(i).getInt("year") == spouseStartYear) {

                            spouseStartYear++;
                        }
                        if (spouseAge != 0) {
                            spouseAge++;
                        }
                        if (userAge != 0) {
                            userAge++;
                        }
                    } else if (((userAge >= 70 && userAge <= 99) || (spouseAge >= 70 && userAge <= 99))) {
                        // -------------------if user age is >
                        // 70---------------------------------
                        //System.out.println("user age > than 70");
                        double saving = assets.getJSONObject(i - 1).getDouble("savings");
                        double savingLimite = 0;

                        if (limits != null) {
                            if (emergencyFundFlag == false) {
                                savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2)
                                        + limits.getJSONObject(i).getInt("saving");
                            } else {

                                if (emergencyType.equals("Fix Amount")) {
                                    savingLimite = (emergencyFundAmt) + limits.getJSONObject(i).getInt("saving");
                                } else if (emergencyType.equals("Expense")) {

                                    savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                            * (Integer.parseInt(monthsOfExpense)))
                                            + limits.getJSONObject(i).getInt("saving");
                                } else if (emergencyType.equals("Income")) {

                                    savingLimite = (income.getJSONObject(i).getDouble("value") / 12
                                            * (Integer.parseInt(monthsOfIncome)))
                                            + limits.getJSONObject(i).getInt("saving");
                                }

                            }
                        } else {
                            if (emergencyFundFlag == false) {
                                savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2);
                            } else {

                                if (emergencyType.equals("Fix Amount")) {
                                    savingLimite = (emergencyFundAmt);
                                } else if (emergencyType.equals("Expense")) {

                                    savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                            * (Integer.parseInt(monthsOfExpense)));
                                } else if (emergencyType.equals("Income")) {

                                    savingLimite = (income.getJSONObject(i).getDouble("value") / 12
                                            * (Integer.parseInt(monthsOfIncome)));
                                }

                            }
                        }
                        saving = savingLimite;
                        contributionAmount = 0;

                        final double usersSSB = userIncome.getJSONObject(i).getDouble("value");
                        if (maritalStatus.equals("Yes")) {
                            spouseSSB = spouse_income.getJSONObject(i).getDouble("value");
                            yearlySSB = spouseSSB + usersSSB;

                            responseFromGrowthRate = investmentPortfolioImpl.growthRate(riskScore, 2, userAge,
                                    MongoDBConnection.investmentPortfolioData);
                        } else {
                            // ////System.out.println("Krishnan:: userIncome:: " +
                            // yearlySSB);
                            yearlySSB = userIncome.getJSONObject(i).getDouble("value");
                            responseFromGrowthRate = investmentPortfolioImpl.growthRate(riskScore, 1, userAge,
                                    MongoDBConnection.investmentPortfolioData);
                        }
                        growthRate = responseFromGrowthRate.getDouble("growthRate") / 100;
                        //////System.out.println--="+growthRate);
                        if (userIncome.length() <= i) {
                            userSSB = userIncome.getJSONObject(i - 1).getDouble("value");
                        } else {
                            userSSB = userIncome.getJSONObject(i).getDouble("value");
                        }

                        double userMinPercentage = 0;
                        double spouseMinPercentage = 0;
                        double minimumWithdrawal = 0;
                        boolean ageFlage = false;
                        // System.out.println("Mahi : spouseage in calingine:"+spouseAge +" useage.."+userAge);
                        if (spouseAge == 0 || ((spouseAge - userAge) < 10 && (userAge - spouseAge) < 10)) {
                            if (maritalStatus.equals("Yes") && spouseAge != 0) {
                                if (MongoDBConnection.distributionModelData.getMinimumPercentageWithdrawal()
                                        .containsKey("" + spouseAge)) {
                                    spouseMinPercentage = (MongoDBConnection.distributionModelData
                                            .getMinimumPercentageWithdrawal().get("" + spouseAge) / 100);
                                }
                            }
                            if (MongoDBConnection.distributionModelData.getMinimumPercentageWithdrawal()
                                    .containsKey("" + userAge)) {
                                userMinPercentage = (MongoDBConnection.distributionModelData
                                        .getMinimumPercentageWithdrawal().get("" + userAge) / 100);
                            }
                        } else {
                            ageFlage = true;
                            if(spouseAge>=70 && spouseAge<=100) {
                                // System.out.println("spouseeee agee "+spouseAge);
                                spouseMinPercentage = (MongoDBConnection.distributionModelData
                                        .getMinimumPercentageWithdrawal().get("" + spouseAge) / 100);
                            }
                            else {
                                spouseMinPercentage=0;
                            }

                            if(userAge>=70 && userAge<=100){
                                userMinPercentage = (MongoDBConnection.distributionModelData
                                        .getMinimumPercentageWithdrawal().get("" + userAge) / 100);
                            }
                            else {
                                userMinPercentage=0;
                            }
                            /*userMinPercentage = MongoDBConnection.distributionModelData.getMinDistribution()
                                                         .get("" + userAge).get(spouseAge - 30);*/
                        }
                        double user401kWithdrawal = 0;
                        double spouse401kWithdrawal = 0;
                        double userIRAWithdrawal = 0;
                        double spouseIRAWithdrawal = 0;
                        double userRouthIRAWithdrawal = 0;
                        double spouseRouthIRAWithdrawal = 0;
                        final double user529PlanWithdrawal = 0;
                        final double spouse529PlanWithdrawal = 0;

                        /*  if (ageFlage) {
                            spouseMinPercentage = userMinPercentage;
                        }
                         */
                        user401kWithdrawal = (assets.getJSONObject(i - 1).getDouble("user401k") * userMinPercentage);
                        spouse401kWithdrawal = (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                * spouseMinPercentage);
                        userIRAWithdrawal = (assets.getJSONObject(i - 1).getDouble("userIRA") * userMinPercentage);
                        spouseIRAWithdrawal = (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                * spouseMinPercentage);
                        userRouthIRAWithdrawal = (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                * userMinPercentage);
                        spouseRouthIRAWithdrawal = (assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                * spouseMinPercentage);
                        minimumWithdrawal = user401kWithdrawal + spouse401kWithdrawal + userIRAWithdrawal
                                + spouseIRAWithdrawal + userRouthIRAWithdrawal + spouseRouthIRAWithdrawal
                                + user529PlanWithdrawal + spouse529PlanWithdrawal;
                        //System.out.println("withdrawal spouse and user" +user401kWithdrawal+"spouse ..."+userIRAWithdrawal);
                        //System.out.println("minimumWithdrawal" +minimumWithdrawal);
                        double nontaxable_investment_amount = 0;
                        JSONObject federalJson = new JSONObject();

                        if (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") > 0) {
                            ////System.out.println("non tax greATER THEN 0");
                            if (userMinPercentage != 0 || spouseMinPercentage != 0) {
                                if (maritalStatus.equals("Yes")) {
                                    if (spouseMinPercentage != 0 && userMinPercentage != 0) {
                                        if (userAge >= 70) {
                                            assets.getJSONObject(i).put("user401k",
                                                    (assets.getJSONObject(i - 1).getDouble("user401k")
                                                            - user401kWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                            - userRouthIRAWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("userIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                            - userIRAWithdrawal) * 1.025);


                                        } else

                                        {

                                            assets.getJSONObject(i).put("user401k",
                                                    (assets.getJSONObject(i - 1).getDouble("user401k") - 0) * 1.025);

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userRothIRA") - 0) * 1.025);

                                            assets.getJSONObject(i).put("userIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userIRA") - 0) * 1.025);

                                        }

                                        if (spouseAge >= 70)

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                            - spouse401kWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                                            - spouseRouthIRAWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                            - spouseIRAWithdrawal) * 1.025);

                                        }

                                        else

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    (assets.getJSONObject(i - 1).getDouble("spouse401k") - 0) * 1.025);

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseRothIRA") - 0)
                                                    * 1.025);

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseIRA") - 0) * 1.025);

                                        }

                                        assets.getJSONObject(i).put("nontaxable_investment_amount",
                                                nontaxable_investment_amount);

                                    } else if (spouseMinPercentage == 0 && userMinPercentage != 0) {

                                        if (userAge >= 70)

                                        {

                                            assets.getJSONObject(i).put("user401k",
                                                    (assets.getJSONObject(i - 1).getDouble("user401k")
                                                            - user401kWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                            - userRouthIRAWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("userIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                            - userIRAWithdrawal) * 1.025);

                                        } else

                                        {

                                            assets.getJSONObject(i).put("user401k",
                                                    (assets.getJSONObject(i - 1).getDouble("user401k") - 0) * 1.025);

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userRothIRA") - 0) * 1.025);

                                            assets.getJSONObject(i).put("userIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userIRA") - 0) * 1.025);

                                        }
                                        // if(spouseAge>=70)

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                    + (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                            * growthRate));

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                                    + (assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                                            * growthRate));

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                    + (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                            * growthRate));

                                        }

                                    } else {

                                        // if(userAge >= 70)

                                        {

                                            assets.getJSONObject(i).put("user401k", assets.getJSONObject(i - 1)
                                                    .getDouble("user401k")
                                                    + (assets.getJSONObject(i - 1).getDouble("user401k") * growthRate));

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                    + (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                            * growthRate));

                                            assets.getJSONObject(i).put("userIRA", assets.getJSONObject(i - 1)
                                                    .getDouble("userIRA")
                                                    + (assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate));

                                        }

                                        if (spouseAge >= 70)

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                            - spouse401kWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                                            - spouseRouthIRAWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                            - spouseIRAWithdrawal) * 1.025);

                                        }

                                        else

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    (assets.getJSONObject(i - 1).getDouble("spouse401k") - 0) * 1.025);

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseRothIRA") - 0)
                                                    * 1.025);

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseIRA") - 0) * 1.025);

                                        }

                                    }
                                } else {

                                    if (userAge >= 70)

                                    {

                                        assets.getJSONObject(i).put("user401k",
                                                (assets.getJSONObject(i - 1).getDouble("user401k") - user401kWithdrawal)
                                                * 1.025);

                                        assets.getJSONObject(i).put("spouse401k", 0);

                                        assets.getJSONObject(i).put("userRothIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                        - userRouthIRAWithdrawal) * 1.025);

                                        assets.getJSONObject(i).put("spouseRothIRA", 0);

                                        assets.getJSONObject(i).put("userIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userIRA") - userIRAWithdrawal)
                                                * 1.025);

                                        assets.getJSONObject(i).put("spouseIRA", 0);

                                    }

                                }

                            }

                            /*                            if (userStartYearForCap > assets.getJSONObject(i).getInt("year")) {
                                assets.getJSONObject(i).put("user401k",
                                        assets.getJSONObject(i).getDouble("user401k") + user401kContribution);

                            }
                            if (spouseStartYearForCap > assets.getJSONObject(i).getInt("year")) {
                                assets.getJSONObject(i).put("spouse401k",
                                        assets.getJSONObject(i).getDouble("spouse401k") + spouse401kContribution);

                            }
                             */                            nontaxable_investment_amount = assets.getJSONObject(i).getDouble("spouseIRA")
                                     + assets.getJSONObject(i).getDouble("user401k")
                                     + assets.getJSONObject(i).getDouble("spouse401k")
                                     + assets.getJSONObject(i).getDouble("userRothIRA")
                                     + assets.getJSONObject(i).getDouble("spouseRothIRA")
                                     + assets.getJSONObject(i).getDouble("userIRA");
                             assets.getJSONObject(i).put("nontaxable_investment_amount", nontaxable_investment_amount);
                             ////System.out.println("user401k"+assets.getJSONObject(i).getDouble("user401k"));
                             ////System.out.println("userRothIRA"+assets.getJSONObject(i).getDouble("userRothIRA"));
                             ////System.out.println("nontaxable amount"+nontaxable_investment_amount);
                             //System.out.println("assets nontaxable amount...before"+assets.getJSONObject(i).getDouble("nontaxable_investment_amount"));
                             //System.out.println("assets user401k amount...before"+assets.getJSONObject(i).getDouble("user401k"));
                             //System.out.println("assets spouse401k amount...before"+assets.getJSONObject(i).getDouble("spouse401k"));
                             //System.out.println("assets userIRA amount...before"+assets.getJSONObject(i).getDouble("userIRA"));
                             //System.out.println("assets spouseIRA amount...before"+assets.getJSONObject(i).getDouble("spouseIRA"));
                             //System.out.println("assets userRothIRA amount...before"+assets.getJSONObject(i).getDouble("userRothIRA"));
                             //System.out.println("assets spouseRothIRA amount...before"+assets.getJSONObject(i).getDouble("spouseRothIRA"));
                             //System.out.println("minimum withdrawal....."+minimumWithdrawal);
                             federalJson = calRetirementTax(fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                     fillingExemtion.getJSONObject(i).getString("fillingStatus"), fin_id, incomeRothIRA,
                                     yearlySSB, userExpense.getJSONObject(i).getDouble("totalExpense"), user_id,
                                     minimumWithdrawal, userAge, spouseAge, userSSB, spouseSSB, contributionAmount,
                                     goal_id, year, maritalStatus, kidBirthYear, state, kidcount, registrationYear,
                                     childArray, houseStatus, houseValue, housing_expense, startYear,
                                     remainingMortgageOriginal, remainingMortgageInterestRate, spouseBirthYear,
                                     userBirthYear, spouseAgeInFinPlan, spouseRetirementStartYear,
                                     userRetirementStartYear, stateTaxValue,equity,houseAppreciationRate,registrationHouseStatus);
                             //System.out.println("assets.getJSONObject(i)user401k..."+assets.getJSONObject(i).getDouble("user401k"));
                             //System.out.println("incomeIraRecieved.."+federalJson.getDouble("incomeIRA"));
                             if (federalJson.getDouble("incomeIRA") > 0) {
                                 final double accessMony = federalJson.getDouble("incomeIRA");


                                 if (accessMony < assets.getJSONObject(i).getDouble("user401k")) {
                                     assets.getJSONObject(i).put("user401k",
                                             assets.getJSONObject(i).getDouble("user401k") - (accessMony * 1.025));
                                 } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                         + assets.getJSONObject(i).getDouble("spouse401k"))) {
                                     final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k");
                                     assets.getJSONObject(i).put("user401k", 0);
                                     assets.getJSONObject(i).put("spouse401k",
                                             assets.getJSONObject(i).getDouble("spouse401k") - (tmp * 1.025));

                                 } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                         + assets.getJSONObject(i).getDouble("spouse401k")
                                         + assets.getJSONObject(i).getDouble("userRothIRA"))) {
                                     final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k")
                                             + assets.getJSONObject(i).getDouble("spouse401k");
                                     assets.getJSONObject(i).put("user401k", 0);
                                     assets.getJSONObject(i).put("spouse401k", 0);
                                     assets.getJSONObject(i).put("userRothIRA",
                                             assets.getJSONObject(i).getDouble("userRothIRA") - (tmp * 1.025));

                                 } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                         + assets.getJSONObject(i).getDouble("spouse401k")
                                         + assets.getJSONObject(i).getDouble("userIRA")
                                         + assets.getJSONObject(i - 1).getDouble("spouseIRA"))) {
                                     final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k")
                                             + assets.getJSONObject(i).getDouble("spouse401k")
                                             + assets.getJSONObject(i).getDouble("userIRA");
                                     assets.getJSONObject(i).put("user401k", 0);
                                     assets.getJSONObject(i).put("spouse401k", 0);
                                     assets.getJSONObject(i).put("userIRA", 0);
                                     assets.getJSONObject(i).put("spouseIRA",
                                             assets.getJSONObject(i).getDouble("spouseIRA") - (tmp * 1.025));

                                 } else if (accessMony > (assets.getJSONObject(i).getDouble("user401k")
                                         + assets.getJSONObject(i).getDouble("spouse401k")
                                         + assets.getJSONObject(i).getDouble("userIRA")
                                         + assets.getJSONObject(i).getDouble("spouseIRA"))) {
                                     goalFeasibility = false;
                                     statusMsg.append("Insufficient balance in non-taxable account");
                                     // ////System.out.println(" Hello Insufficient balance in non-taxable
                                     // account"+assets.getJSONObject(i).getDouble("user401k")+"spouse"+assets.getJSONObject(i).getDouble("spouse401k"));
                                     break;
                                 }

                                 ////System.out.println("year"+year+"assets.getJSONObject(i)"+ assets.getJSONObject(i).getDouble("nontaxable_investment_amount") +"accessMony.."+accessMony);
                                 assets.getJSONObject(i).put("nontaxable_investment_amount",
                                         (assets.getJSONObject(i).getDouble("user401k")
                                                 + assets.getJSONObject(i).getDouble("spouse401k")
                                                 + assets.getJSONObject(i).getDouble("userIRA")
                                                 + assets.getJSONObject(i).getDouble("spouseIRA")
                                                 + assets.getJSONObject(i).getDouble("userRothIRA")
                                                 + assets.getJSONObject(i).getDouble("spouseRothIRA")));
                                 //System.out.println("assets nontaxable amount. before growth rate.."+assets.getJSONObject(i).getDouble("nontaxable_investment_amount"));
                                 assets.getJSONObject(i).put("taxable_investment_amount",
                                         assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                         + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                                 * growthRate));
                                 //System.out.println("assets nontaxable amount..."+assets.getJSONObject(i).getDouble("nontaxable_investment_amount"));
                                 //System.out.println("assets user401k amount..."+assets.getJSONObject(i).getDouble("user401k"));
                                 //System.out.println("assets spouse401k amount..."+assets.getJSONObject(i).getDouble("spouse401k"));
                                 //System.out.println("assets userIRA amount..."+assets.getJSONObject(i).getDouble("userIRA"));
                                 //System.out.println("assets spouseIRA amount..."+assets.getJSONObject(i).getDouble("spouseIRA"));
                                 //System.out.println("assets userRothIRA amount..."+assets.getJSONObject(i).getDouble("userRothIRA"));
                                 //System.out.println("assets spouseRothIRA amount..."+assets.getJSONObject(i).getDouble("spouseRothIRA"));


                             } else {

                                 double accessMony = federalJson.getDouble("withdrawalFromRetirementAccounts")
                                         - contributionAmount - federalJson.getDouble("federalTax")
                                         - federalJson.getDouble("stateTax") - federalJson.getDouble("userSSTax")
                                         - federalJson.getDouble("spouseSSTax")
                                         - federalJson.getDouble("fICAMedicareTax");
                                 if (accessMony < 0) {
                                     accessMony = federalJson.getDouble("withdrawalFromRetirementAccounts");
                                 }

                                 if (accessMony > 0) {
                                     assets.getJSONObject(i).put("taxable_investment_amount", assets.getJSONObject(i - 1)
                                             .getDouble("taxable_investment_amount") + accessMony
                                             + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                                     * growthRate));
                                 }

                             }

                             assets.getJSONObject(i).put("savings", saving);

                             if (assets.getJSONObject(i).getInt("year") == userStartYear) {
                                 userStartYear++;
                             }
                             if (assets.getJSONObject(i).getInt("year") == spouseStartYear) {

                                 spouseStartYear++;
                             }
                             if (spouseAge != 0) {
                                 spouseAge++;
                             }
                             userAge++;

                        } else if (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") <= 0
                                && yearlySSB < userExpense.getJSONObject(i).getDouble("totalExpense")&&assets.getJSONObject(i - 1).getDouble("taxable_investment_amount") <= 0&&assets.getJSONObject(i - 1).getDouble("savings") <= 0) {

                            statusMsg.append("Insufficient balance in Non taxable account");
                            responseObj.put("status", "fail");
                            responseObj.put("statusMsg", statusMsg);
                            return responseObj;
                        } else {
                            ////System.out.println("non tax LESS THEN 0");
                            federalJson = calRetirementTax(fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                    fillingExemtion.getJSONObject(i).getString("fillingStatus"), fin_id, incomeRothIRA,
                                    yearlySSB, userExpense.getJSONObject(i).getDouble("totalExpense"), user_id,
                                    minimumWithdrawal, userAge, spouseAge, userSSB, spouseSSB, contributionAmount,
                                    goal_id, year, maritalStatus, kidBirthYear, state, kidcount, registrationYear,
                                    childArray, houseStatus, houseValue, housing_expense, startYear,
                                    remainingMortgageOriginal, remainingMortgageInterestRate, spouseBirthYear,
                                    userBirthYear, spouseAgeInFinPlan, spouseRetirementStartYear,
                                    userRetirementStartYear, stateTaxValue,equity,houseAppreciationRate,registrationHouseStatus);
                            if (federalJson.getDouble("incomeIRA") > 0) {

                                final double accessMony = federalJson.getDouble("incomeIRA");
                                ////System.out.println("INcome IRA"+accessMony);
                                ////System.out.println("401"+assets.getJSONObject(i-1).getDouble("user401k"));
                                ////System.out.println("s401"+assets.getJSONObject(i-1).getDouble("spouse401k"));
                                int flag1=0;
                                if (accessMony < assets.getJSONObject(i-1).getDouble("user401k")) {
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i-1).getDouble("user401k") - accessMony
                                            + assets.getJSONObject(i-1).getDouble("user401k") * growthRate);
                                } else if (accessMony < (assets.getJSONObject(i-1).getDouble("user401k")
                                        + assets.getJSONObject(i-1).getDouble("spouse401k"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i-1).getDouble("user401k");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i-1).getDouble("spouse401k") - tmp
                                            + assets.getJSONObject(i-1).getDouble("spouse401k") * growthRate);

                                } else if (accessMony < (assets.getJSONObject(i-1).getDouble("user401k")
                                        + assets.getJSONObject(i-1).getDouble("spouse401k")
                                        + assets.getJSONObject(i-1).getDouble("userRothIRA"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i-1).getDouble("user401k")
                                            + assets.getJSONObject(i-1).getDouble("spouse401k");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k", 0);
                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i-1).getDouble("userRothIRA") - tmp
                                            + assets.getJSONObject(i-1).getDouble("userIRA") * growthRate);

                                } else if (accessMony < (assets.getJSONObject(i-1).getDouble("user401k")
                                        + assets.getJSONObject(i-1).getDouble("spouse401k")
                                        + assets.getJSONObject(i-1).getDouble("userIRA")
                                        + assets.getJSONObject(i - 1).getDouble("spouseIRA"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i-1).getDouble("user401k")
                                            + assets.getJSONObject(i-1).getDouble("spouse401k")
                                            + assets.getJSONObject(i-1).getDouble("userIRA");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k", 0);
                                    assets.getJSONObject(i).put("userIRA", 0);
                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i-1).getDouble("spouseIRA") - tmp
                                            + assets.getJSONObject(i-1).getDouble("spouseIRA") * growthRate);

                                } else if (accessMony > (assets.getJSONObject(i-1).getDouble("user401k")
                                        + assets.getJSONObject(i-1).getDouble("spouse401k")
                                        + assets.getJSONObject(i-1).getDouble("userIRA")
                                        + assets.getJSONObject(i-1).getDouble("spouseIRA")
                                        + assets.getJSONObject(i-1).getDouble("userRothIRA")
                                        + assets.getJSONObject(i-1).getDouble("spouseRothIRA"))) {

                                    if(assets.getJSONObject(i-1).getDouble("taxable_investment_amount")>accessMony) {
                                        flag1=1;
                                        assets.getJSONObject(i).put("taxable_investment_amount",assets.getJSONObject(i-1).getDouble("taxable_investment_amount")-accessMony);
                                        assets.getJSONObject(i).put("nontaxable_investment_amount", 0);
                                        assets.getJSONObject(i).put("user401k", 0);
                                        assets.getJSONObject(i).put("spouse401k", 0);
                                        assets.getJSONObject(i).put("userRothIRA", 0);
                                        assets.getJSONObject(i).put("spouseRothIRA", 0);
                                        assets.getJSONObject(i).put("userIRA", 0);
                                        assets.getJSONObject(i).put("spouseIRA", 0);

                                    }
                                    else {
                                        if((assets.getJSONObject(i-1).getDouble("savings")+(assets.getJSONObject(i-1).getDouble("taxable_investment_amount")-accessMony))>0) {
                                            flag1=1;
                                            assets.getJSONObject(i).put("savings",(assets.getJSONObject(i-1).getDouble("savings")+(assets.getJSONObject(i-1).getDouble("taxable_investment_amount")-accessMony)));
                                            assets.getJSONObject(i).put("taxable_investment_amount",0);
                                            assets.getJSONObject(i).put("nontaxable_investment_amount", 0);
                                            assets.getJSONObject(i).put("user401k", 0);
                                            assets.getJSONObject(i).put("spouse401k", 0);
                                            assets.getJSONObject(i).put("userRothIRA", 0);
                                            assets.getJSONObject(i).put("spouseRothIRA", 0);
                                            assets.getJSONObject(i).put("userIRA", 0);
                                            assets.getJSONObject(i).put("spouseIRA", 0);
                                        }
                                        else {
                                            goalFeasibility = false;
                                            statusMsg.append("Insufficient balance in non-taxable account");
                                            ////System.out.println(" Hello Insufficien balance in non-taxable account"+assets.getJSONObject(i).getDouble("user401k")+"spouse"+assets.getJSONObject(i).getDouble("spouse401k"));
                                            break;
                                        }
                                    }



                                }
                                assets.getJSONObject(i).put("nontaxable_investment_amount",
                                        (assets.getJSONObject(i).getDouble("user401k")
                                                + assets.getJSONObject(i).getDouble("spouse401k")
                                                + assets.getJSONObject(i).getDouble("userIRA")
                                                + assets.getJSONObject(i).getDouble("spouseIRA")
                                                + assets.getJSONObject(i).getDouble("userRothIRA")
                                                + assets.getJSONObject(i).getDouble("spouseRothIRA")));
                                if(flag1==1) {
                                    assets.getJSONObject(i).put("taxable_investment_amount",
                                            assets.getJSONObject(i).getDouble("taxable_investment_amount")
                                            + (assets.getJSONObject(i).getDouble("taxable_investment_amount")
                                                    * growthRate));
                                }
                                else {
                                    assets.getJSONObject(i).put("taxable_investment_amount",
                                            assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                            + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                                    * growthRate));
                                }


                            } else {

                                final double accessMony = federalJson.getDouble("withdrawalFromRetirementAccounts")
                                        - contributionAmount - federalJson.getDouble("federalTax")
                                        - federalJson.getDouble("stateTax") - federalJson.getDouble("userSSTax")
                                        - federalJson.getDouble("spouseSSTax")
                                        - federalJson.getDouble("fICAMedicareTax");

                                assets.getJSONObject(i).put("taxable_investment_amount",
                                        assets.getJSONObject(i - 1).getDouble("taxable_investment_amount") + accessMony
                                        + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                                * growthRate));
                                assets.getJSONObject(i).put("nontaxable_investment_amount", 0);
                                assets.getJSONObject(i).put("user401k", 0);
                                assets.getJSONObject(i).put("spouse401k", 0);
                                assets.getJSONObject(i).put("userRothIRA", 0);
                                assets.getJSONObject(i).put("spouseRothIRA", 0);
                                assets.getJSONObject(i).put("userIRA", 0);
                                assets.getJSONObject(i).put("spouseIRA", 0);

                            }

                            assets.getJSONObject(i).put("savings", saving);

                        }
                        ////System.out.println("YEAR==="+year);
                        if (assets.getJSONObject(i).getInt("year") == year) {
                            final double nontaxtableWithdrawlAmount = assets.getJSONObject(i).getDouble(
                                    "nontaxable_investment_amount")/*- deductions.getJSONObject(i).getDouble("taxable")*/;
                            final double nontaxtableAmount = assets.getJSONObject(i).getDouble(
                                    "taxable_investment_amount")/*- deductions.getJSONObject(i).getDouble("taxable")*/;
                            double afterTaxbleDeduction = 0;
                            double afterSavingDeduction = 0;
                            //System.out.println("nontaxtableWithdrawlAmount"+nontaxtableWithdrawlAmount+"nontaxtableAmount.."+nontaxtableAmount);
                            if(nontaxtableWithdrawlAmount <= 0 && nontaxtableAmount > 0) {
                                goalFeasibility = true;
                                assets.getJSONObject(i).put("nontaxable_investment_amount", 0);
                                assets.getJSONObject(i).put("user401k", 0);
                                assets.getJSONObject(i).put("spouse401k", 0);
                                assets.getJSONObject(i).put("userRothIRA", 0);
                                assets.getJSONObject(i).put("spouseRothIRA", 0);
                                assets.getJSONObject(i).put("userIRA", 0);
                                assets.getJSONObject(i).put("spouseIRA", 0);
                                afterTaxbleDeduction = nontaxtableAmount - nontaxtableWithdrawlAmount;
                                if(afterTaxbleDeduction>=0) {

                                    assets.getJSONObject(i).put("taxable_investment_amount", afterTaxbleDeduction);
                                }
                                else if(afterTaxbleDeduction<0) {
                                    afterSavingDeduction = assets.getJSONObject(i).getDouble("savings") - afterTaxbleDeduction;
                                    if(afterSavingDeduction>=0) {

                                        assets.getJSONObject(i).put("taxable_investment_amount", 0);
                                        assets.getJSONObject(i).put("savings", afterSavingDeduction);
                                    }
                                    else {
                                        goalFeasibility = false;

                                        statusMsg.append("Insufficient balance in taxable account ... saving and non taxable");
                                        //////System.out.println("assets..."+assets.getJSONObject(i).getDouble("nontaxable_investment_amount")+"taxable amount"+assets.getJSONObject(i).getDouble("taxable_investment_amount")+"saving.."+assets.getJSONObject(i).getDouble("savings"));

                                        ////System.out.println("Insufficient balance in taxable account ... saving and non taxable");
                                        break;

                                    }
                                }


                                ////System.out.println("year "+year+"assets.>0.."+assets.getJSONObject(i).getDouble("nontaxable_investment_amount")+"taxable amount"+assets.getJSONObject(i).getDouble("taxable_investment_amount")+"saving.."+assets.getJSONObject(i).getDouble("savings"));
                            }

                            else if (nontaxtableWithdrawlAmount < 0 && nontaxtableAmount < 0) {
                                ////System.out.println("assets..."+assets.getJSONObject(i).getDouble("nontaxable_investment_amount")+"taxable amount"+assets.getJSONObject(i).getDouble("taxable_investment_amount")+"saving.."+assets.getJSONObject(i).getDouble("savings"));
                                if(nontaxtableWithdrawlAmount>assets.getJSONObject(i).getDouble("savings"))
                                {
                                    goalFeasibility = false;

                                    statusMsg.append("Insufficient balance in taxable account");


                                    ////System.out.println("Assest going negative in retirement helllo");
                                    break;
                                }
                                else {
                                    goalFeasibility = true;
                                    assets.getJSONObject(i).put("savings",(assets.getJSONObject(i).getDouble("savings") - nontaxtableWithdrawlAmount) );
                                }
                            } else {


                                goalFeasibility = true;

                            }
                        }
                        //System.out.println("year"+year+"taxes.....retirement"+federalJson);
                        tax.getJSONObject(i).put("federalTax", federalJson.getDouble("federalTax"));
                        tax.getJSONObject(i).put("fICAMedicareTax", federalJson.getDouble("fICAMedicareTax"));
                        tax.getJSONObject(i).put("fICASocialSecurityTax",
                                federalJson.getDouble("fICASocialSecurityTax"));
                        tax.getJSONObject(i).put("userSSTax", federalJson.getDouble("userSSTax"));
                        tax.getJSONObject(i).put("stateTax", federalJson.getDouble("stateTax"));
                        tax.getJSONObject(i).put("spouseSSTax", federalJson.getDouble("spouseSSTax"));
                        if(!federalJson.isNull("equity")){
                            equityReturn=federalJson.getJSONArray("equity");
                        }
                        //----------------------new code for529---aaaaa---------------------------

                        if(deductions!=null && deductions.getJSONObject(i).getDouble("collegeGoalNontaxable")!=0)
                        {
                            assets.getJSONObject(i).put("user529Plan",( assets.getJSONObject(i-1).getDouble("user529Plan")-deductions.getJSONObject(i).getDouble("collegeGoalNontaxable"))*1.025);
                            assets.getJSONObject(i).put("spouse529Plan", (assets.getJSONObject(i-1).getDouble("spouse529Plan")-deductions.getJSONObject(i).getDouble("collegeGoalNontaxable"))*1.025);

                        }
                        else
                        {
                            assets.getJSONObject(i).put("user529Plan", assets.getJSONObject(i-1).getDouble("user529Plan")+assets.getJSONObject(i-1).getDouble("user529Plan")*growthRate);
                            assets.getJSONObject(i).put("spouse529Plan", assets.getJSONObject(i-1).getDouble("spouse529Plan")+assets.getJSONObject(i-1).getDouble("spouse529Plan")*growthRate);

                        }
                        /*assets.getJSONObject(i).put("nontaxable_investment_amount",
								assets.getJSONObject(i).getDouble("nontaxable_investment_amount")
								+assets.getJSONObject(i).getDouble("spouse529Plan")
								 + assets.getJSONObject(i).getDouble("user529Plan"));*/
                        //////System.out.println("user------"+assets.getJSONObject(i-1).getDouble("user529Plan"));
                        //////System.out.println("user------"+assets.getJSONObject(i).getDouble("user529Plan"));
                    }

                }


            }
            ////System.out.println("##Bala Json array for equity"+equityReturn);

            final Long etime=System.currentTimeMillis();
            ////System.out.println("End time "+(etime-stime));
            if (goalFeasibility == true) {
                responseObj.put("status", "success");
                responseObj.put("assets", assets);
                responseObj.put("tax", tax);
                responseObj.put("statusMsg", "Goal Applied successfully");
                if(fin_id!=null && equityReturn!=null)
                {
                    MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi().with("{$set: {'equity':" + equityReturn + "}}");
                }
                else{
                    if(equityReturn!=null) {
                        MongoDBConnection.incomeProfileCol.update("{user_id:#,profile_name:#}", user_id, "constant_income").upsert().multi().with("{$set: {'equity':" + equityReturn + "}}");
                    }
                }
            } else {
                responseObj.put("status", "fail");
                responseObj.put("statusMsg", statusMsg);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return responseObj;
    }

    public static int calculateChildTaxcredit(double income, int year, String filingSatus, JSONArray kidBirthYear,
            int kidcount, int registrationYear, JSONArray childArray) {
        int childTaxCredit = 0;
        try {
            int qualifiedKidCount = 0;
            double phaseOutIncome = 0;
            if (childArray != null) {
                for (int i = 0; i < childArray.length(); i++) {
                    final int birthYear = registrationYear - childArray.getJSONObject(i).getInt("age");
                    final int kidEndYear = birthYear + 16;
                    if (year >= birthYear && year <= kidEndYear) {
                        qualifiedKidCount++;
                    }
                }
            }
            //////System.out.println("BALA===qualifiedKidCount before kid birth year"+qualifiedKidCount);
            //////System.out.println("BALA===list of kid birth year"+kidBirthYear);
            if (kidBirthYear != null) {
                for (int i = 0; i < kidBirthYear.length(); i++) {
                    final int kidZeorYear = kidBirthYear.getInt(i);
                    final int kidEndYear = kidZeorYear + 16;
                    if (year >= kidZeorYear && year <= kidEndYear) {
                        qualifiedKidCount++;
                    }
                }
            }
            //	////System.out.println("BALA===qualifiedKidCount after kid birth year"+qualifiedKidCount);
            if (filingSatus.equals("Single") || filingSatus.equals("Head of Household")
                    || filingSatus.equals("Qualified Widow")) {
                //////System.out.println("inside single and HOH");
                phaseOutIncome = 75000;
            } else if (filingSatus.equals("Married Filing Jointly")) {
                phaseOutIncome = 110000;
            } else {
                phaseOutIncome = 55000;
            }
            //////System.out.println("BALA===income level for credit score"+income);
            if (income <= phaseOutIncome) {
                //////System.out.println("income===aparna"+income);
                childTaxCredit = 1000 * qualifiedKidCount;
            } else {
                phaseOutIncome = income - phaseOutIncome;
                phaseOutIncome = ((long) phaseOutIncome + 500) / 1000 * 1000;
                phaseOutIncome = phaseOutIncome * 0.05;
                if (phaseOutIncome > (1000 * qualifiedKidCount)) {
                    childTaxCredit = 0;
                } else {
                    childTaxCredit = (int) ((1000 * qualifiedKidCount) - phaseOutIncome);
                }
            }
            //////System.out.println("BALA===credit score value"+childTaxCredit);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return childTaxCredit;
    }

    // ================new calAssests==============================

    // ----------------------------------federal tax calculation by
    // bala-----------------------------------//

    public static JSONObject calTaxPerYear(int spouseStartYear, int userStartYear, double income, String user_id,
            String fin_id, double userIncome, double spouseIncome, int year, int noOfExcemtion, String fillingStatus,
            int userAge, int spouseAge, String maritalStatus, JSONArray kidBirthYear, String state, int kidcount,
            int registrationYear, JSONArray childArray, String houseStatus, double houseValue,
            JSONArray housing_expense, long startYear, double remainingMortgageOriginal,
            double remainingMortgageInterestRate, double minimumwithdrawal, StatetaxModel stateTaxValue,double taxableInvestmentAmount,double nonTaxableInvestmentAmount,JSONArray equity,FinPlan finPlanDetails,double houseAppreciationRate,String registrationHouseStatus) {
        final JSONArray tax = new JSONArray();
        final JSONObject taxEachYear = new JSONObject();
        JSONObject resultItemized=new JSONObject();
        //////System.out.println("user_id------------="+user_id);
        double taxableIncome = 0;
        double stateTax = 0;
        try {
            double maxDeduction = 0;
            int creditScore = 0;
            int standardDeduction = 0;
            double itemizedDeduction = 0;
            int exemptionThreshold = 0;
            int exemptionStep = 0;
            int exemptionAmount = 0;
            int maxSSTax = 0;
            standardDeduction = MongoDBConnection.constJson.getInt("standardDeduction");
            maxSSTax = MongoDBConnection.constJson.getInt("maxSSTax");
            String modelFillingStatus = null;
            JSONObject constantVal = new JSONObject();
            if (fillingStatus.equals("Single")) {
                modelFillingStatus = "single";
            } else if (fillingStatus.equals("Married Filing Separately")) {
                modelFillingStatus = "marriedFilingSeparately";
            } else if (fillingStatus.equals("Married Filing Jointly")) {
                modelFillingStatus = "marriedFilingJoint";
            } else if (fillingStatus.equals("Head of Household")) {
                modelFillingStatus = "headOfHousehold";
            } else if (fillingStatus.equals("Qualified Widow")) {
                modelFillingStatus = "qualifiedWidow";
            }
            constantVal = MongoDBConnection.constJson.getJSONObject(modelFillingStatus);
            // ////System.out.println("Ranjitha ::			 //constantVal"+constantVal+"stand..ded"+standardDeduction);
            standardDeduction = constantVal.getInt("standardDeduction");
            exemptionThreshold = constantVal.getInt("exemptionThreshold");
            exemptionStep = constantVal.getInt("exemptionStep");
            exemptionAmount = constantVal.getInt("exemptionAmount");
            if (userAge >= 65) {
                // ////System.out.println("userAge====aparna"+userAge);
                // ////System.out.println("additionalDeduction== user"+
                // constantVal.getInt("additionalDeduction"));
                standardDeduction = standardDeduction + constantVal.getInt("additionalDeduction");
            }

            double spouseSSTax = 0;
            // userIncome = userIncome - spouseIncome;
            double userSSTax = 0;
            // ////System.out.println();
            // ////System.out.println("spouseAge===="+spouseAge);
            final double exemptionAdjusent = calculateExemptionAdjustment(income, exemptionThreshold, exemptionStep);
            final double exemptions = noOfExcemtion * exemptionAdjusent * exemptionAmount;
            //		////System.out.println("noOfExcemtion==="+noOfExcemtion);
            //	////System.out.println("state====44"+state);
            JSONArray equityReturn=new JSONArray();
            if (state.equals("NEW HAMPSHIRE") || state.equals("IOWA") || state.equals("ALABAMA")
                    || state.equals("INDIANA") || state.equals("LOUISIANA") || state.equals("MONTANA")
                    || state.equals("TENNESSEE") || state.equals("OREGON")) {
                //	////System.out.println("inside utah else");
                itemizedDeduction = 0;
                equityReturn=equity;
            } else {
                /*	//////System.out.println("insude else   utha called");
				// ////System.out.println("income===="+income+" state=="+state+
				 "fillingStatus==="+fillingStatus+
				" noOfExcemtion===="+noOfExcemtion+" year" +year+
				 "stateTaxValue===="+stateTaxValue);*/
                stateTax = federalTaxParamCal(income, state, fillingStatus, noOfExcemtion, year, stateTaxValue,maritalStatus,childArray,houseStatus,userIncome, spouseIncome,user_id);
                //	////System.out.println("state tax --aparna->>"+stateTax);

                if (stateTax < 0) {
                    stateTax = 0;
                }
                resultItemized= itemizedDeductionForFedralTaxDuringRegistration(user_id, fin_id, stateTax, year,
                        "afterHouseGoal", houseStatus, registrationYear, houseValue, housing_expense, startYear,
                        remainingMortgageOriginal, remainingMortgageInterestRate,equity,houseAppreciationRate,registrationHouseStatus);

                itemizedDeduction = resultItemized.getDouble("itemizedDeduction");
                if(equity!=null)
                {
                    equityReturn= resultItemized.getJSONArray("equity");
                }
                // ////System.out.println("Krishnan itemized deduction time: " +
                // (System.nanoTime() - itemizedStartTime)/1000000 + " milli
                // seconds");
            }
            // ////System.out.println("stateTax=inside caltacper==="+stateTax);
            if (stateTax < 0) {
                stateTax = 0;
            }
            // ////System.out.println("stateTax manoj0--->>>"+stateTax);
            if (maritalStatus.equals("Yes")) {
                if (spouseAge >= 65) {
                    //System.out.println("additionalDeduction----Aparna"+constantVal.getInt("additionalDeduction"));
                    standardDeduction = standardDeduction + constantVal.getInt("additionalDeduction");
                }
            }
            maxDeduction = Math.max(standardDeduction, itemizedDeduction);
            //		////System.out.println("income==="+income);
            //		////System.out.println("maxDeduction==="+maxDeduction);
            taxableIncome = calTaxableIncome(income, exemptions, maxDeduction);
            //		////System.out.println("taxableIncome==="+taxableIncome);

            final double calculatedTax = calculatedTax(taxableIncome, fillingStatus);
            //	////System.out.println("calculatedTax==="+calculatedTax);
            int kidcount1=kidcount;
            if(fin_id!=null)
            {


                //////System.out.println("finJson---aparna"+finJson);
                if(finPlanDetails!=null){
                    kidcount1=kidcount1+finPlanDetails.getKidsGoalCount();
                    //////System.out.println("kidcount1==apap   "+kidcount1+"     finPlanDetails.getKidsGoalCount()=="+finPlanDetails.getKidsGoalCount());
                }
            }
            //////System.out.println("kidcountfor kidsgoal===aparnaqqq    "+kidcount1);
            creditScore = calculateChildTaxcredit(income, year, fillingStatus, kidBirthYear,kidcount1 , registrationYear,
                    childArray);

            //System.out.println("noOfExcemtion===aparna"+noOfExcemtion);
            //////System.out.println("creditScore kidsgoal===aparna"+creditScore);
            // ////System.out.println("Krishnan calceChildTaxcredit time: " +
            // (System.nanoTime() - calceChildTaxcredit)/1000000 + " milli
            // seconds");
            //////System.out.println("kidcount===aparna"+kidcount);
            if ((kidcount==0 && kidcount1==0) ||noOfExcemtion==1) {
                //////System.out.println("inside kidcount is zerooo----");
                creditScore = 0;
            }
            if(creditScore<0){
                creditScore=0;
            }
            double federalTax = calculatedTax - creditScore;

            if(federalTax<0)
            {
                federalTax=0;
            }
            //////System.out.println("federalTax======"+federalTax+"calculatedTax==="+calculatedTax+"creditScore    "+creditScore);
            if (state.equals("NEW HAMPSHIRE") || state.equals("IOWA") || state.equals("ALABAMA")
                    || state.equals("INDIANA") || state.equals("LOUISIANA") || state.equals("MONTANA")
                    || state.equals("TENNESSEE") || state.equals("OREGON")|| state.equals("MISSOURI")) {
                stateTax = federalTaxParamCal(income, state, federalTax, fillingStatus, noOfExcemtion, taxableIncome,
                        year, childArray, stateTaxValue,maritalStatus,childArray, taxableInvestmentAmount,nonTaxableInvestmentAmount);
            }
            if (stateTax < 0) {
                stateTax = 0;
            }
            // ////System.out.println("stateTax manoj1--->>>"+stateTax);
            double FICAMedicareTax = 0;
            if(housing_expense!=null)
            {
                for(int j=0;j<housing_expense.length();j++){
                    if(housing_expense.getJSONObject(j).getString("frequency").equals("Turn first house into a rental")||housing_expense.getJSONObject(j).getString("frequency").equals("Turn second house into a rental")){
                        if(year>=housing_expense.getJSONObject(j).getInt("startYear")&& year<=housing_expense.getJSONObject(j).getInt("endYear"))
                        {
                            userIncome=userIncome-housing_expense.getJSONObject(j).getDouble("rentalIncomePerYear")+housing_expense.getJSONObject(j).getDouble("deprectionAmount");
                            // income=income-housing_expense.getJSONObject(j).getDouble("rentalIncomePerYear")+housing_expense.getJSONObject(j).getDouble("deprectionAmount");
                        }
                    }
                }
                //  //System.out.println("userIncome ..."+userIncome);
            }
            if (userAge >= 70 && spouseAge >= 70) {
                // ////System.out.println("after ret1");
                FICAMedicareTax = 0;
            }

            else if (userStartYear != 0 && userStartYear < spouseStartYear && spouseStartYear > year) {
                // ////System.out.println("after ret2");
                FICAMedicareTax = 1.45 * (spouseIncome) / 100;
            } else if (spouseStartYear != 0 && spouseStartYear < userStartYear && userStartYear > year) {
                // ////System.out.println("after ret3");
                FICAMedicareTax = 1.45 * (userIncome) / 100;
            }
            if (spouseStartYear == 0 && userStartYear == 0) {
                // ////System.out.println("before ret");
                FICAMedicareTax = 1.45 * (userIncome + spouseIncome) / 100;
            }
            if (maritalStatus.equals("Yes")) {
                if (spouseAge >= 65) {
                    //System.out.println("additionalDeduction----Aparna"+constantVal.getInt("additionalDeduction"));
                    standardDeduction = standardDeduction + constantVal.getInt("additionalDeduction");
                }

                if (spouseStartYear != 0 && spouseStartYear <= year) {
                    // ////System.out.println("inside spouse if");
                    spouseSSTax = 0;
                } else {
                    // ////System.out.println("inside spouse else");
                    spouseSSTax = findSSTax(spouseIncome, maxSSTax);
                }
            }
            if (userStartYear != 0 && userStartYear <= year) {
                // ////System.out.println("inside user if");
                userSSTax = 0;
            } else {
                // ////System.out.println("inside user else");
                userSSTax = findSSTax(userIncome, maxSSTax);
            }
            final double FICASocialSecurityTax = Math.min(6.2 * income / 100, maxSSTax);
            // ////System.out.println("stateTax before update-->>"+stateTax);
            taxEachYear.put("federalTax", federalTax);
            taxEachYear.put("fICAMedicareTax", FICAMedicareTax);
            taxEachYear.put("fICASocialSecurityTax", FICASocialSecurityTax);
            taxEachYear.put("userSSTax", userSSTax);
            taxEachYear.put("stateTax", stateTax);
            taxEachYear.put("spouseSSTax", spouseSSTax);
            taxEachYear.put("maxDeduction",maxDeduction);
            taxEachYear.put("exemptions",exemptions);
            taxEachYear.put("equity",equityReturn);

            //         //System.out.println("Aparna Tax each year :"+taxEachYear);
            tax.put(taxEachYear);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return taxEachYear;
    }

    // ---------------------- calcaulate combined Income--------------------
    public static JSONArray calCombinedIncome(JSONArray user_income, JSONArray spouse_income) {
        try {
            final JSONArray combined_income = new JSONArray();
            final int spouse_length = spouse_income.length();
            final int user_length = user_income.length();
            int i = 0;
            int j = 0;
            while (i < user_length && j < spouse_length) {
                final JSONObject yearlyincome = new JSONObject();
                if (user_income.getJSONObject(i).getInt("year") < spouse_income.getJSONObject(j).getInt("year")) {
                    yearlyincome.put("year", user_income.getJSONObject(i).getInt("year"));
                    yearlyincome.put("value", (user_income.getJSONObject(i).getDouble("value")));
                    combined_income.put(yearlyincome);
                    i++;
                } else if (user_income.getJSONObject(i).getInt("year") == spouse_income.getJSONObject(j)
                        .getInt("year")) {
                    yearlyincome.put("year", spouse_income.getJSONObject(j).getInt("year"));
                    yearlyincome.put("value", (user_income.getJSONObject(i).getDouble("value"))
                            + (spouse_income.getJSONObject(j).getDouble("value")));
                    combined_income.put(yearlyincome);
                    i++;
                    j++;
                } else if (user_income.getJSONObject(i).getInt("year") > spouse_income.getJSONObject(j)
                        .getInt("year")) {
                    yearlyincome.put("year", spouse_income.getJSONObject(j).getInt("year"));
                    yearlyincome.put("value", spouse_income.getJSONObject(j).getDouble("value"));
                    combined_income.put(yearlyincome);
                    j++;
                }
            }
            while (i < user_length) {
                final JSONObject yearlyincome = new JSONObject();
                yearlyincome.put("year", user_income.getJSONObject(i).getInt("year"));
                yearlyincome.put("value", (user_income.getJSONObject(i).getDouble("value")));
                combined_income.put(yearlyincome);
                i++;
            }
            while (j < spouse_length) {
                final JSONObject yearlyincome = new JSONObject();
                yearlyincome.put("year", spouse_income.getJSONObject(j).getInt("year"));
                yearlyincome.put("value", (spouse_income.getJSONObject(j).getDouble("value")));
                combined_income.put(yearlyincome);
                j++;
            }
            return combined_income;
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray calRemaningAmount(JSONArray income, IncomeProfile dataFormServlet) {
        try {

            final User user = MongoDBConnection.userColl.findOne("{_id:#}", dataFormServlet.getUser_id()).as(User.class);
            final double expenses = (user.getMonthlyExpenses() + user.getRentalExpenses()) * 12;
            for (int i = 0; i < income.length(); i++) {
                final double remaining_amount = income.getJSONObject(i).getDouble("value") - expenses;
                income.getJSONObject(i).put("value", remaining_amount);
            }
        } catch (final Exception e) {
        }
        return income;
    }

    public static double calculateExemptionAdjustment(double income, int exemptionThreshold, int exemptionStep) {
        final double calAfterRoundUp = 1 - (Math.round((income - exemptionThreshold) / exemptionStep) * 0.02);
        double minValue;
        double exemptionAdjesent;
        if (calAfterRoundUp >= 1) {
            minValue = 1;
        } else {
            minValue = calAfterRoundUp;
        }

        if (minValue <= 0) {
            exemptionAdjesent = 0;
        } else {
            exemptionAdjesent = minValue;
        }
        return exemptionAdjesent;
    }
    //---------------
    public static JSONObject calExemptions(double exemptionAdjesent, String user_id, int exemptionAmount, String finId,
            int currentYear) throws JsonProcessingException, JSONException {
        final JSONObject responseFromExcemption = new JSONObject();
        final User user = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
        final String[] out = user.getCreatedTs().split("/");
        final int registrationYear = Integer.parseInt(out[0]);
        final JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(user));
        int noOfExemptions = 1;
        if (user.getMarital_status().equals("Yes")) {
            noOfExemptions = noOfExemptions + user.getChildCount() + 1; // 1 for
            // spouse
            if (user.getChildCount() != 0) {
                final JSONArray childArray = userJson.getJSONArray("childs");
                for (int i = 0; i < user.getChildCount(); i++) {
                    if (currentYear - (registrationYear - childArray.getJSONObject(i).getInt("age")) > 18) {
                        if ((childArray.getJSONObject(i).getString("flag").equals("Yes")
                                && currentYear - (registrationYear - childArray.getJSONObject(i).getInt("age")) > 24)
                                || (!childArray.getJSONObject(i).getString("flag").equals("Yes"))) {
                            noOfExemptions = noOfExemptions - 1;
                        }
                    }
                }
            }
        }
        if (user.getFilingStatus().equals("Married Filing Separately")
                || user.getFilingStatus().equals("Qualified Widow")) {
            noOfExemptions = 1; // 1 for spouse(
        }
        if (user.getFilingStatus().equals("Head of Household")) {
            noOfExemptions = noOfExemptions + user.getDependants() - 1;
        }
        final double exemptions = noOfExemptions * exemptionAdjesent * 4000; // 4000
        // is
        // Excemption
        // Amount
        responseFromExcemption.put("exemptions", exemptions).put("noOfExemptions", noOfExemptions);
        return responseFromExcemption;
    }

    public static double calTaxableIncome(double income, double exemptions, double maxDeduction) {
        final double taxableIncome = income - exemptions - maxDeduction;
        //////System.out.println("income===="+income+"exemptions  apar"+exemptions+"maxDeduction     "+maxDeduction);
        return taxableIncome;
    }

    public static double calculatedTax(double taxableIncome, String status) throws JSONException {

        double calculatedTax1 = 0;
        double calculatedTax = 0;
        final double value = 0;
        // ////System.out.println("Manoj : status-->"+status);
        try {
            if (status.equals("Single")) {

                // ////System.out.println("single-->");
                // ////System.out.println("taxRates)))__>"+MongoDBConnection.taxRates);
                // ////System.out.println("taxableIncome-->>"+taxableIncome);
                int i = 0;
                while (taxableIncome > MongoDBConnection.taxRates.getJSONObject(i).getLong("brackes")
                        && i < (MongoDBConnection.taxRates.length() - 1)) {
                    calculatedTax1 = calculatedTax1 + (MongoDBConnection.taxRates.getJSONObject(i).getDouble("rates")
                            * ((double) MongoDBConnection.taxRates.getJSONObject(i + 1).getLong("brackes")
                                    - (double) MongoDBConnection.taxRates.getJSONObject(i).getLong("brackes")));
                    // ////System.out.println("Manoj: calculatedTax1
                    // :"+calculatedTax1);

                    i++;

                    if (i<MongoDBConnection.taxRates.length()-1 && taxableIncome < MongoDBConnection.taxRates.getJSONObject(i + 1).getLong("brackes")) {
                        break;
                    }

                }

                if (taxableIncome > 0) {
                    calculatedTax1 = calculatedTax1
                            + (taxableIncome - MongoDBConnection.taxRates.getJSONObject(i).getLong("brackes"))
                            * MongoDBConnection.taxRates.getJSONObject(i).getDouble("rates");
                    if (calculatedTax1 < 0 || ((((taxableIncome
                            - (MongoDBConnection.taxRates.getJSONObject(i).getLong("brackes")))
                            * MongoDBConnection.taxRates.getJSONObject(i).getDouble("rates"))) < value)) {
                        // ////System.out.println("my
                        // rates-->>"+MongoDBConnection.taxRates.getJSONObject(i-1).getDouble("rates"));
                        calculatedTax1 = MongoDBConnection.taxRates.getJSONObject(i - 1).getDouble("rates")
                                * taxableIncome;
                    }
                }
                // ////System.out.println("calculatedTax1 sin-->"+calculatedTax1);

                if (taxableIncome > 0 && taxableIncome <= 9225) {
                    calculatedTax = 0.1 * taxableIncome;
                } else if (taxableIncome > 9225 && taxableIncome <= 37450) {
                    calculatedTax = 0.1 * 9225 + 0.15 * (taxableIncome - 9225);
                } else if (taxableIncome > 37450 && taxableIncome <= 90750) {
                    calculatedTax = 0.1 * 9225 + 0.15 * (37450 - 9225) + 0.25 * (taxableIncome - 37450);
                } else if (taxableIncome > 90750 && taxableIncome <= 189300) {
                    calculatedTax = 0.1 * 9225 + 0.15 * (37450 - 9225) + 0.25 * (90750 - 37450)
                            + 0.28 * (taxableIncome - 90750);
                } else if (taxableIncome > 189300 && taxableIncome <= 411500) {
                    calculatedTax = 0.1 * 9225 + 0.15 * (37450 - 9225) + 0.25 * (90750 - 37450)
                            + 0.28 * (189300 - 90750) + 0.33 * (taxableIncome - 189300);
                } else if (taxableIncome > 411500 && taxableIncome <= 413200) {
                    calculatedTax = 0.1 * 9225 + 0.15 * (37450 - 9225) + 0.25 * (90750 - 37450)
                            + 0.28 * (189300 - 90750) + 0.33 * (200000 - 189300) + 0.35 * (taxableIncome - 411500);
                } else if (taxableIncome > 413200) {
                    calculatedTax = 0.1 * 9225 + 0.15 * (37450 - 9225) + 0.25 * (90750 - 37450)
                            + 0.28 * (189300 - 90750) + 0.33 * (411500 - 189300) + 0.35 * (413200 - 411500)
                            + 0.396 * (taxableIncome - 413200);
                }

                // ////System.out.println("calculatedTax sin--->"+calculatedTax);

                return calculatedTax1;
            } else if (status.equals("Married Filing Separately")) {

                int i = 0;
                while (taxableIncome > MongoDBConnection.taxRatesMarrid.getJSONObject(i).getLong("brackes")
                        && i < (MongoDBConnection.taxRatesMarrid.length() - 1)) {
                    calculatedTax1 = calculatedTax1 + (MongoDBConnection.taxRatesMarrid.getJSONObject(i)
                            .getDouble("rates")
                            * ((double) MongoDBConnection.taxRatesMarrid.getJSONObject(i + 1).getLong("brackes")
                                    - (double) MongoDBConnection.taxRatesMarrid.getJSONObject(i).getLong("brackes")));
                    i++;
                    if (taxableIncome < MongoDBConnection.taxRatesMarrid.getJSONObject(i + 1)
                            .getLong("brackes")) {
                        break;
                    }

                }

                if (taxableIncome > 0) {
                    calculatedTax1 = calculatedTax1 + (taxableIncome
                            - MongoDBConnection.taxRatesMarrid.getJSONObject(i).getLong("brackes"))
                            * MongoDBConnection.taxRatesMarrid.getJSONObject(i).getDouble("rates");
                    if (calculatedTax1 < 0 || ((((taxableIncome
                            - (MongoDBConnection.taxRatesMarrid.getJSONObject(i).getLong("brackes")))
                            * MongoDBConnection.taxRatesMarrid.getJSONObject(i).getDouble("rates"))) < value)) {
                        // ////System.out.println("my
                        // rates-->>"+MongoDBConnection.taxRatesMarrid.getJSONObject(i-1).getDouble("rates"));
                        calculatedTax1 = MongoDBConnection.taxRatesMarrid.getJSONObject(i - 1).getDouble("rates")
                                * taxableIncome;
                    }
                }

                return calculatedTax1;
            } else if (status.equals("Married Filing Jointly")) {

                int i = 0;
                while (taxableIncome > MongoDBConnection.marriedFilingJointly.getJSONObject(i)
                        .getLong("brackes") && i < (MongoDBConnection.marriedFilingJointly.length() - 1)) {

                    calculatedTax1 = calculatedTax1 + (MongoDBConnection.marriedFilingJointly.getJSONObject(i)
                            .getDouble("rates")
                            * ((double) MongoDBConnection.marriedFilingJointly.getJSONObject(i + 1).getLong("brackes")
                                    - (double) MongoDBConnection.marriedFilingJointly.getJSONObject(i)
                                    .getLong("brackes")));
                    i++;
                    if (taxableIncome < MongoDBConnection.marriedFilingJointly.getJSONObject(i + 1)
                            .getLong("brackes")) {
                        break;
                    }

                }
                if (taxableIncome > 0) {

                    calculatedTax1 = calculatedTax1 + (taxableIncome
                            - (MongoDBConnection.marriedFilingJointly.getJSONObject(i).getLong("brackes")))
                            * MongoDBConnection.marriedFilingJointly.getJSONObject(i).getDouble("rates");
                    if (calculatedTax1 < 0 || ((((taxableIncome
                            - (MongoDBConnection.marriedFilingJointly.getJSONObject(i).getLong("brackes")))
                            * MongoDBConnection.marriedFilingJointly.getJSONObject(i).getDouble("rates"))) < value)) {

                        calculatedTax1 = MongoDBConnection.marriedFilingJointly.getJSONObject(i - 1).getDouble("rates")
                                * taxableIncome;
                    }
                }
                if (taxableIncome > 0 && taxableIncome <= 18450) {
                    calculatedTax = 0.1 * taxableIncome;

                } else if (taxableIncome > 18450 && taxableIncome <= 74900) {
                    calculatedTax = 0.1 * 18450 + 0.15 * (taxableIncome - 18450);

                } else if (taxableIncome > 74900 && taxableIncome <= 151200) {
                    calculatedTax = 0.1 * 18450 + 0.15 * (74900 - 18450) + 0.25 * (taxableIncome - 74900);
                } else if (taxableIncome > 151200 && taxableIncome <= 230450) {
                    calculatedTax = 0.1 * 18450 + 0.15 * (74900 - 18450) + 0.25 * (151200 - 74900)
                            + 0.28 * (taxableIncome - 151200);
                } else if (taxableIncome > 230450 && taxableIncome <= 411500) {
                    calculatedTax = 0.1 * 18450 + 0.15 * (74900 - 18450) + 0.25 * (151200 - 74900)
                            + 0.28 * (230450 - 151200) + 0.33 * (taxableIncome - 230450);
                } else if (taxableIncome > 411500 && taxableIncome <= 464850) {
                    calculatedTax = 0.1 * 18450 + 0.15 * (74900 - 18450) + 0.25 * (151200 - 74900)
                            + 0.28 * (230450 - 151200) + 0.33 * (411500 - 230450) + 0.35 * (taxableIncome - 411500);

                } else if (taxableIncome > 464850) {
                    calculatedTax = 0.1 * 18450 + 0.15 * (74900 - 18450) + 0.25 * (151200 - 74900)
                            + 0.28 * (230450 - 151200) + 0.33 * (411500 - 230450) + 0.35 * (464850 - 411500)
                            + 0.396 * (taxableIncome - 464850);
                }

                return calculatedTax1;
            } else if (status.equals("Head of Household")) {
                int i = 0;
                while (taxableIncome > MongoDBConnection.headOfHousehold.getJSONObject(i).getLong("brackes")
                        && i < (MongoDBConnection.headOfHousehold.length() - 1)) {

                    calculatedTax1 = calculatedTax1 + (MongoDBConnection.headOfHousehold.getJSONObject(i)
                            .getDouble("rates")
                            * ((double) MongoDBConnection.headOfHousehold.getJSONObject(i + 1).getLong("brackes")
                                    - (double) MongoDBConnection.headOfHousehold.getJSONObject(i).getLong("brackes")));
                    i++;
                    if (taxableIncome < MongoDBConnection.headOfHousehold.getJSONObject(i + 1)
                            .getLong("brackes")) {
                        break;
                    }

                }

                if (taxableIncome > 0) {
                    calculatedTax1 = calculatedTax1 + (taxableIncome
                            - MongoDBConnection.headOfHousehold.getJSONObject(i).getLong("brackes"))
                            * MongoDBConnection.headOfHousehold.getJSONObject(i).getDouble("rates");

                    if (calculatedTax1 < 0 || ((((taxableIncome
                            - (MongoDBConnection.headOfHousehold.getJSONObject(i).getLong("brackes")))
                            * MongoDBConnection.headOfHousehold.getJSONObject(i).getDouble("rates"))) < value)) {
                        // ////System.out.println("my
                        // rates-->>"+MongoDBConnection.headOfHousehold.getJSONObject(i-1).getDouble("rates"));
                        calculatedTax1 = MongoDBConnection.headOfHousehold.getJSONObject(i - 1).getDouble("rates")
                                * taxableIncome;
                        if (calculatedTax1 < 0 || ((((taxableIncome
                                - (MongoDBConnection.headOfHousehold.getJSONObject(i).getLong("brackes")))
                                * MongoDBConnection.headOfHousehold.getJSONObject(i).getDouble("rates"))) < value)) {
                            // ////System.out.println("my
                            // rates-->>"+MongoDBConnection.headOfHousehold.getJSONObject(i-1).getDouble("rates"));
                            calculatedTax1 = MongoDBConnection.headOfHousehold.getJSONObject(i - 1).getDouble("rates")
                                    * taxableIncome;
                        }
                    }

                }
                //////System.out.println("calculatedTax1==aaaa=="+calculatedTax1);
                return calculatedTax1;
            } else {

                int i = 0;
                while (taxableIncome > MongoDBConnection.qualifiedWidow.getJSONObject(i).getLong("brackes")
                        && i < (MongoDBConnection.qualifiedWidow.length() - 1)) {
                    calculatedTax1 = calculatedTax1 + (MongoDBConnection.qualifiedWidow.getJSONObject(i)
                            .getDouble("rates")
                            * ((double) MongoDBConnection.qualifiedWidow.getJSONObject(i + 1).getLong("brackes")
                                    - (double) MongoDBConnection.qualifiedWidow.getJSONObject(i).getLong("brackes")));
                    i++;
                    if (taxableIncome < MongoDBConnection.qualifiedWidow.getJSONObject(i + 1)
                            .getLong("brackes")) {
                        break;
                    }

                }

                if (taxableIncome > 0) {

                    calculatedTax1 = calculatedTax1 + (taxableIncome
                            - MongoDBConnection.qualifiedWidow.getJSONObject(i).getLong("brackes"))
                            * MongoDBConnection.qualifiedWidow.getJSONObject(i).getDouble("rates");
                    if (calculatedTax1 < 0 || ((((taxableIncome
                            - (MongoDBConnection.qualifiedWidow.getJSONObject(i).getLong("brackes")))
                            * MongoDBConnection.qualifiedWidow.getJSONObject(i).getDouble("rates"))) < value)) {
                        // ////System.out.println("my
                        // rates-->>"+MongoDBConnection.qualifiedWidow.getJSONObject(i-1).getDouble("rates"));
                        calculatedTax1 = MongoDBConnection.qualifiedWidow.getJSONObject(i - 1).getDouble("rates")
                                * taxableIncome;
                    }
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        // ////System.out.println("calculatedTax qow-->"+calculatedTax);

        return calculatedTax1;

    }

    public static JSONArray retirementExpenseArray(JSONArray userExpense, int spouseYear, int userYear,
            String marritalStatus, long retairmentExpenseNew) {
        double totalExpense = 0;
        try {

            double retirementExpense = 0;
            int retirementExpenseStartYear = 0;
            if (spouseYear < userYear && spouseYear != 0) {
                retirementExpenseStartYear = spouseYear;
            } else {
                retirementExpenseStartYear = userYear;
            }
            // totalExpense=userExpense.getJSONObject(k).getDouble("totalExpense")*0.8;
            for (int i = 0; i < userExpense.length(); i++) {

                if ((userExpense.getJSONObject(i).getInt("year") >= (retirementExpenseStartYear - 1))
                        && userExpense.getJSONObject(i + 1).getDouble("mortgageExpense") == 0
                        && userExpense.getJSONObject(i + 1).getDouble("kidExpense") == 0) {
                    // ////System.out.println("Mahi totalExpense year:
                    // :"+userExpense.getJSONObject(i).getDouble("year") );
                    totalExpense = userExpense.getJSONObject(i).getDouble("totalExpense");
                    break;

                }

            }
            // ////System.out.println("Mahi totalExpense: :"+totalExpense );
            //System.out.print("after retirement expense====:");
            for (int i = 0; i < userExpense.length(); i++) {

                if (userExpense.getJSONObject(i).getInt("year") >= retirementExpenseStartYear) {
                    if (marritalStatus.equals("Yes")) {

                        if (userExpense.getJSONObject(i).getInt("year") >= userYear
                                && userExpense.getJSONObject(i).getInt("year") >= spouseYear &&

                                userExpense.getJSONObject(i).getDouble("mortgageExpense") == 0 &&

                                userExpense.getJSONObject(i).getDouble("kidExpense") == 0)

                        {

                            final int diffInYear = spouseYear - (retirementExpenseStartYear - 1);

                            /*
                             * retirementExpense = totalExpense - totalExpense *
                             * (0.05 * diffInYear);
                             */

                            retirementExpense = totalExpense * (0.80);

                            spouseYear++;

                            userYear++;

                        } else if (spouseYear < userYear && spouseYear != 0
                                && userExpense.getJSONObject(i).getDouble("mortgageExpense") == 0
                                && userExpense.getJSONObject(i).getDouble("kidExpense") == 0) {
                            final int diffInYear = spouseYear - (retirementExpenseStartYear - 1);
                            /*
                             * retirementExpense = totalExpense - totalExpense *
                             * (0.05 * diffInYear);
                             */
                            retirementExpense = totalExpense - totalExpense * (0.10);
                            spouseYear++;
                        } else if (spouseYear > userYear && spouseYear != 0
                                && userExpense.getJSONObject(i).getDouble("mortgageExpense") == 0
                                && userExpense.getJSONObject(i).getDouble("kidExpense") == 0) {
                            final int diffInYear = userYear - (retirementExpenseStartYear - 1);
                            /*
                             * retirementExpense = totalExpense - totalExpense *
                             * (0.05 * diffInYear);
                             */
                            retirementExpense = totalExpense - totalExpense * (0.10);
                            userYear++;
                        } else if (userExpense.getJSONObject(i).getDouble("mortgageExpense") == 0
                                && userExpense.getJSONObject(i).getDouble("kidExpense") == 0) {

                            retirementExpense = totalExpense * .80;

                            userYear++;
                            spouseYear++;
                        } else {
                            retirementExpense = userExpense.getJSONObject(i).getDouble("totalExpense");
                        }
                        userExpense.getJSONObject(i).put("totalExpense", retirementExpense);
                    } else {
                        if (userExpense.getJSONObject(i).getDouble("mortgageExpense") == 0
                                && userExpense.getJSONObject(i).getDouble("kidExpense") == 0) {
                            retirementExpense = totalExpense * .80;
                        } else {
                            retirementExpense = userExpense.getJSONObject(i).getDouble("totalExpense");
                        }
                        userExpense.getJSONObject(i).put("totalExpense", retirementExpense);
                    }
                    if (retairmentExpenseNew != 0) {
                        userExpense.getJSONObject(i).put("totalExpense", retairmentExpenseNew);
                    }
                }

            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return userExpense;
    }

    public static JSONObject calRetirementTax(int noOfExcemtion, String fillingStatus, String fin_id,
            double incomeRothIRA, double yearlySSB, double retirementExpense, String user_id, double minimumWithdrawal,
            int age, int spouseAge, double userSSB, double spouseSSB, double contributionAmount, String goal_id,
            int year, String maritalStatus, JSONArray kidBirthYear, String state, int kidcount, int registrationYear,
            JSONArray childArray, String houseStatus, double houseValue, JSONArray housing_expense, long startYear,
            double remainingMortgageOriginal, double remainingMortgageInterestRate, int spouseBirthYear,
            int userBirthYear, int spouseAgeInFinPlan, int spouseRetirementStartYear, int userRetirementStartYear,
            StatetaxModel stateTaxValue,JSONArray equity,double houseAppreciationRate,String registrationHouseStatus) {
        final JSONObject responseToRestController = new JSONObject();
        try {
            final double incomeJsonSSB = yearlySSB;
            double incomeIRA = 0;
            double accessMony = 0;
            final FinPlan finPlanDetails = MongoDBConnection.finplancol
                    .findOne("{_id:#}", fin_id ).as(FinPlan.class);

            accessMony = yearlySSB + minimumWithdrawal - retirementExpense;
            ////System.out.println("accessMony..tax"+accessMony);
            if ((accessMony >= 0 && age < 70) && (accessMony >= 0 && spouseAge < 70)) {
                final double withdrawalFromRetirementAccounts = accessMony;
                incomeIRA = 0;

                final double retirementIncome = retirementIncomeCalculation(incomeIRA, incomeRothIRA, incomeJsonSSB,
                        minimumWithdrawal);
                final double incomeForTaxableSSB = incomeForTaxableSSBCalculation(retirementIncome, incomeJsonSSB,
                        incomeRothIRA);

                double taxableSSB = 0;
                if (goal_id != null) {

                    taxableSSB = calculateTaxableSSB(fin_id, incomeForTaxableSSB, retirementIncome, incomeJsonSSB,
                            user_id);
                    //// ////System.out.println("Elapsed time for sweeping of
                    //// taxableSSB: " + (System.nanoTime() -
                    //// startTimeForIncomeForCalculateTaxableSSB) / 1000000 + "
                    //// milli seconds");
                } else {
                    // long startTimeForIncomeForCalculateTaxableSSB =
                    // System.nanoTime();
                    taxableSSB = calculateTaxableSSB(incomeForTaxableSSB, retirementIncome, incomeJsonSSB, user_id,
                            fillingStatus);
                    //// ////System.out.println("Elapsed time for sweeping of
                    //// taxableSSB: " + (System.nanoTime() -
                    //// startTimeForIncomeForCalculateTaxableSSB) / 1000000 + "
                    //// milli seconds");
                }
                final double federalAGI = calculateFederalAGI(retirementIncome, incomeJsonSSB, contributionAmount,
                        taxableSSB);

                JSONObject tempRetirementTax = new JSONObject();

                tempRetirementTax = calTaxPerYear(spouseRetirementStartYear, userRetirementStartYear, federalAGI,
                        user_id, fin_id, userSSB, spouseSSB, year, noOfExcemtion, fillingStatus, age, spouseAge,
                        maritalStatus, kidBirthYear, state, kidcount, registrationYear, childArray, houseStatus,
                        houseValue, housing_expense, startYear, remainingMortgageOriginal,
                        remainingMortgageInterestRate, minimumWithdrawal, stateTaxValue,0,0,equity,finPlanDetails,houseAppreciationRate,registrationHouseStatus);

                responseToRestController.put("withdrawalFromRetirementAccounts", withdrawalFromRetirementAccounts);
                responseToRestController.put("incomeIRA", incomeIRA);
                responseToRestController.put("federalTax", tempRetirementTax.getDouble("federalTax"));
                responseToRestController.put("fICAMedicareTax", tempRetirementTax.get("fICAMedicareTax"));
                responseToRestController.put("fICASocialSecurityTax", tempRetirementTax.get("fICASocialSecurityTax"));
                responseToRestController.put("userSSTax", tempRetirementTax.get("userSSTax"));
                responseToRestController.put("stateTax", tempRetirementTax.get("stateTax"));
                responseToRestController.put("spouseSSTax", tempRetirementTax.get("spouseSSTax"));
                return responseToRestController;
            } else if ((accessMony >= 0 && age >= 70) || (accessMony >= 0 && spouseAge >= 70)) {
                final double withdrawalFromRetirementAccounts = accessMony;
                incomeIRA = 0;
                final double retirementIncome = retirementIncomeCalculation(incomeIRA, incomeRothIRA, incomeJsonSSB,
                        minimumWithdrawal);

                final double incomeForTaxableSSB = incomeForTaxableSSBCalculation(retirementIncome, incomeJsonSSB,
                        incomeRothIRA);
                if (spouseAge != 0) {

                }
                double taxableSSB = 0;
                if (goal_id != null) {

                    taxableSSB = calculateTaxableSSB(fin_id, incomeForTaxableSSB, retirementIncome, incomeJsonSSB,
                            user_id);
                    //
                } else {
                    // long startTimeForTaxableSSB = System.nanoTime();
                    taxableSSB = calculateTaxableSSB(incomeForTaxableSSB, retirementIncome, incomeJsonSSB, user_id,
                            fillingStatus);
                    //// ////System.out.println("Elapsed time for sweeping of
                    //// TaxableSSB: " + (System.nanoTime() -
                    //// startTimeForTaxableSSB) / 1000000 + " milli seconds");
                }

                final double federalAGI = calculateFederalAGI(retirementIncome, incomeJsonSSB, contributionAmount,
                        taxableSSB);

                JSONObject tempRetirementTax = new JSONObject();
                //System.out.println("federalAGI"+federalAGI+"noOfException"+noOfExcemtion+"state"+state);
                tempRetirementTax = calTaxPerYear(spouseRetirementStartYear, userRetirementStartYear, federalAGI,
                        user_id, fin_id, userSSB, spouseSSB, year, noOfExcemtion, fillingStatus, age, spouseAge,
                        maritalStatus, kidBirthYear, state, kidcount, registrationYear, childArray, houseStatus,
                        houseValue, housing_expense, startYear, remainingMortgageOriginal,
                        remainingMortgageInterestRate, minimumWithdrawal, stateTaxValue,0,0,equity,finPlanDetails,houseAppreciationRate,registrationHouseStatus);
                //////System.out.println("noOfExcemtion===="+noOfExcemtion);

                responseToRestController.put("withdrawalFromRetirementAccounts", withdrawalFromRetirementAccounts);
                responseToRestController.put("incomeIRA", incomeIRA);
                responseToRestController.put("federalTax", tempRetirementTax.getDouble("federalTax"));
                responseToRestController.put("fICAMedicareTax", tempRetirementTax.get("fICAMedicareTax"));
                responseToRestController.put("fICASocialSecurityTax", tempRetirementTax.get("fICASocialSecurityTax"));
                responseToRestController.put("userSSTax", tempRetirementTax.get("userSSTax"));
                responseToRestController.put("stateTax", tempRetirementTax.get("stateTax"));
                responseToRestController.put("spouseSSTax", tempRetirementTax.get("spouseSSTax"));
                responseToRestController.put("equity", tempRetirementTax.getJSONArray("equity"));
                return responseToRestController;
            } else {

                final double withdrawalFromRetirementAccounts = 0;
                incomeIRA = accessMony * -1 + contributionAmount;
                ////System.out.println("Iteration start......");
                JSONObject tempRetirementTax = new JSONObject();
                double federalTaxBefore = 0;


                while (incomeIRA > 0) {
                    //System.out.println("the retirement tax calculation");
                    final double retirementIncome = retirementIncomeCalculation(incomeIRA, incomeRothIRA, incomeJsonSSB,
                            minimumWithdrawal);
                    //System.out.println("retirementIncome=========="+retirementIncome+"incomeJsonSSB"+incomeJsonSSB+"income IRA"+incomeIRA);
                    final double incomeForTaxableSSB = incomeForTaxableSSBCalculation(retirementIncome, incomeJsonSSB,
                            incomeIRA);
                    //System.out.println("incomeForTaxableSSB=========="+incomeForTaxableSSB);
                    double taxableSSB = 0;
                    if (goal_id != null) {

                        taxableSSB = calculateTaxableSSB(fin_id, incomeForTaxableSSB, retirementIncome, incomeJsonSSB,
                                user_id);
                        // ////System.out.println("Elapsed time for sweeping of
                        // taxableSSB: " + (System.nanoTime() -
                        // startTimeFortaxableSSB) / 1000000 + " milli
                        // seconds");
                    } else {

                        taxableSSB = calculateTaxableSSB(incomeForTaxableSSB, retirementIncome, incomeJsonSSB, user_id,
                                fillingStatus);
                        // ////System.out.println("Elapsed time for sweeping of
                        // taxableSSB: " + (System.nanoTime() -
                        // startTimeFortaxableSSB) / 1000000 + " milli
                        // seconds");
                    }

                    contributionAmount = 0;

                    final double federalAGI = calculateFederalAGI(retirementIncome, incomeJsonSSB, incomeIRA, taxableSSB);
                    ///////System.out.println("federalAGI=========="+federalAGI);

                    tempRetirementTax = calTaxPerYear(spouseRetirementStartYear, userRetirementStartYear, federalAGI,
                            user_id, fin_id, userSSB, spouseSSB, year, noOfExcemtion, fillingStatus, age, spouseAge,
                            maritalStatus, kidBirthYear, state, kidcount, registrationYear, childArray, houseStatus,
                            houseValue, housing_expense, startYear, remainingMortgageOriginal,
                            remainingMortgageInterestRate, minimumWithdrawal, stateTaxValue,0,0,equity,finPlanDetails,houseAppreciationRate,registrationHouseStatus);

                    //////System.out.println("noOfExcemtion---->>>>>"+noOfExcemtion);

                    //federalTaxBefore = tempRetirementTax.getDouble("federalTax");
                    //System.out.println("federalTaxBefore" +federalTaxBefore);
                    //System.out.println("tempRetirementTax" +tempRetirementTax.getDouble("federalTax"));
                    if (((tempRetirementTax.getDouble("federalTax") - federalTaxBefore) <= 2)) {

                        federalTaxBefore = tempRetirementTax.getDouble("federalTax");
                        break;
                    } else {
                        incomeIRA = incomeIRA + tempRetirementTax.getDouble("federalTax")
                        + tempRetirementTax.getDouble("stateTax") + tempRetirementTax.getDouble("userSSTax")
                        + tempRetirementTax.getDouble("spouseSSTax")
                        + tempRetirementTax.getDouble("fICAMedicareTax");
                        federalTaxBefore = tempRetirementTax.getDouble("federalTax");
                        //System.out.println("inbetween...."+incomeIRA);
                        //System.out.println("stateTax...."+tempRetirementTax.getDouble("stateTax"));

                    }
                }

                // ////System.out.println("Iteration stop......");

                // ////System.out.println("Bala....the non taxable
                // amount"+incomeIRA);
                /// withdrawalFromRetirementAccounts=incomeIRA;
                responseToRestController.put("withdrawalFromRetirementAccounts", withdrawalFromRetirementAccounts)
                .put("federalTax", federalTaxBefore);
                responseToRestController.put("incomeIRA", incomeIRA);
                responseToRestController.put("fICAMedicareTax", tempRetirementTax.get("fICAMedicareTax"));
                responseToRestController.put("fICASocialSecurityTax", tempRetirementTax.get("fICASocialSecurityTax"));
                responseToRestController.put("userSSTax", tempRetirementTax.get("userSSTax"));
                responseToRestController.put("stateTax", tempRetirementTax.get("stateTax"));
                responseToRestController.put("spouseSSTax", tempRetirementTax.get("spouseSSTax"));
                //System.out.println("stateTax...."+tempRetirementTax.getDouble("stateTax"));
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return responseToRestController;
    }

    public double calculateStateAGI(double userIncome, double SpouseIncome, int userAge, int spouseAge,
            double contributionAmount, double minimumwithdrawal) {
        double stateAGI = 0;
        if (spouseAge != 0) {
            if (spouseAge < userAge) {
                stateAGI = userIncome - contributionAmount;
            } else if (spouseAge > userAge) {
                stateAGI = SpouseIncome - contributionAmount;
            } else {
                stateAGI = minimumwithdrawal - contributionAmount;
            }
        } else {
            stateAGI = minimumwithdrawal - contributionAmount;
        }
        if (stateAGI < 0) {
            return 0;
        } else {
            return stateAGI;
        }
    }

    // ---------------------------------------------------------------------------
    public static double findSSTax(double income, int maxSSTax) {
        double sSTax = 0;
        try {
            sSTax = Math.min(6.2 * income / 100, maxSSTax);

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return sSTax;
    }

    // ============================retirement
    // calculation==========================================
    public static double calculateIncomeSSB(double spouseSsb, double userSsb, int yearOfBirth) {
        /*
         * JSONArray incomeJSONArray=null; JSONObject incomeJsonSSB=null;
         */
        double incomeSSB = 0;
        try {
            incomeSSB = spouseSsb * 12 + userSsb * 12;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return incomeSSB;
    }

    // =========================calculate calculate income
    // IRA======================================
    public static double calculateIncomeIRA(double userIRAWithdrawal, double spouseIRAWithdrawal) {
        double incomeRothIRA = 0;
        try {
            incomeRothIRA = userIRAWithdrawal + spouseIRAWithdrawal;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return incomeRothIRA;
    }

    // =========================calculate calculate income
    // RothIRA======================================
    public static double calculateIncomeRothIRA(double userRothIRAWithdrawal, double spouseRothIRAWithdrawal) {
        double incomeRothIRA = 0;
        try {
            incomeRothIRA = userRothIRAWithdrawal + spouseRothIRAWithdrawal;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return incomeRothIRA;

    }

    // =========================calculate retirement income
    // ======================================
    public static double retirementIncomeCalculation(double incomeIRA, double incomeRothIRA, double incomeSSB,
            double otherIncome) {
        double retirementIncome = 0;
        try {
            retirementIncome = incomeIRA + incomeRothIRA + incomeSSB + otherIncome;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return retirementIncome;
    }

    // =========================calculate incomeForTaxableSSBCalculation
    // ======================================
    public static double incomeForTaxableSSBCalculation(double retirementIncome, double incomSSB,
            double incomeRothIRA) {
        double incomeTaxableSSB = 0;
        try {
            incomeTaxableSSB = retirementIncome - (0.5 * incomSSB) - incomeRothIRA;
            //System.out.println("incomeTaxableSSB===&&"+incomeTaxableSSB);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return incomeTaxableSSB;
    }

    // ==================contribution
    // calculation==========================================
    public static double calWithContribution(double incomeForTaxableSSB, double incomeSSB, double contributionAmount) {
        double contributeAmt = 0;
        double totalIncome = 0;
        try {
            totalIncome = incomeForTaxableSSB + incomeSSB;
            contributeAmt = Math.max(0, (totalIncome - contributionAmount));
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return contributeAmt;
    }

    // ===========================calculate taxable
    // SSB=====================================
    public static double calculateTaxableSSB(double incomeForTaxableSSB, double retirementIncome, double incomSSB,
            String user_id, String fillingStatus) {
        double taxableSSB = 0;
        try {
            if (fillingStatus.equals("Married Filing Jointly")) {
                final double maxOne = Math.max((incomeForTaxableSSB - 44000), 0);
                final double minTwo = Math.min((0.5 * incomSSB), (0.5 * (incomeForTaxableSSB - 42000)));
                final double minThree = Math.min(minTwo, 6000);
                taxableSSB = Math.min((minThree + maxOne * 0.85), (0.85 * incomSSB));
                if (incomeForTaxableSSB <= 32000) {
                    return 0;
                } else {
                    return taxableSSB;
                }
            } else/* if(userStatus.equals("head­of­household")) */
            {
                final double one = Math.max((incomeForTaxableSSB - 34000), 0);
                final double two = Math.min((0.5 * incomSSB), (0.5 * (incomeForTaxableSSB - 25000)));
                final double three = Math.min(two, 4500);
                taxableSSB = Math.min((three + one * 0.85), (0.85 * incomSSB));
                if (incomeForTaxableSSB <= 25000) {
                    return 0;
                } else {
                    // taxableSSB = min {[min { 0.5*incomeSSB,
                    // 0.5*(incomeTaxableSSB- 32000),6000} +
                    // max{(incomeTaxableSSB- 44000),0} *
                    // 0.85],0.85*incomeJsonSSB }
                    return taxableSSB;
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return taxableSSB;
    }

    public static double calculateTaxableSSB(String filingStatus, double incomeForTaxableSSB, double retirementIncome,
            double incomSSB, String user_id) {
        double taxableSSB = 0;
        try {
            if (filingStatus.equals("Married Filing Jointly")) {
                final double maxOne = Math.max((incomeForTaxableSSB - 44000), 0);
                final double minTwo = Math.min((0.5 * incomSSB), (0.5 * (incomeForTaxableSSB - 42000)));
                final double minThree = Math.min(minTwo, 6000);
                taxableSSB = Math.min((minThree + maxOne * 0.85), (0.85 * incomSSB));
                if (incomeForTaxableSSB <= 32000) {
                    return 0;
                } else {
                    return taxableSSB;
                }
            } else/* if(userStatus.equals("head­of­household")) */ {
                final double one = Math.max((incomeForTaxableSSB - 34000), 0);
                final double two = Math.min((0.5 * incomSSB), (0.5 * (incomeForTaxableSSB - 25000)));
                final double three = Math.min(two, 4500);
                taxableSSB = Math.min((three + one * 0.85), (0.85 * incomSSB));
                if (incomeForTaxableSSB <= 25000) {
                    return 0;
                } else {

                    return taxableSSB;
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return taxableSSB;
    }

    // ===========================calculate Federal
    // AGI=====================================
    public static double calculateFederalAGI(double retirementIncome, double incomSSB, double incomeRothIRA,
            double taxableSSB) {
        // federalAGI = retirementIncome ­-incomeSSB -­ incomeRothIRA +
        // taxableSSB
        double federalAGI = 0;
        try {
            federalAGI = retirementIncome - incomeRothIRA - incomSSB + taxableSSB;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return federalAGI;

    }

    public double retirementExpense(JSONArray userExpense, double spouseYear, double userYear) {
        double totalExpense = 0;
        try {
            // totalExpense=userExpense.getJSONObject(k).getDouble("totalExpense")*0.8;
            for (int i = 0; i < userExpense.length(); i++) {
                if ((userExpense.getJSONObject(i).getInt("year") == spouseYear - 1)
                        || (userExpense.getJSONObject(i).getInt("year") == userYear - 1)) {
                    totalExpense = userExpense.getJSONObject(i).getDouble("totalExpense");
                    //// ////System.out.println("totalExpense=="+totalExpense+"
                    //// ==="+userExpense.getJSONObject(i).getInt("year"));
                    return totalExpense;
                }

            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return totalExpense;
    }

    // ---------------------------calculate statetax for remaining year after
    // goal update-------------------------
    public static double federalTaxParamCal(double income, String state, String fillingStatus, int noOfExcemtion,
            int currentYear ,StatetaxModel stateTaxValue,String maritalStatus,JSONArray childArray,String houseStatus,double userIncome,double spouseIncome,String user_id) throws JsonProcessingException, JSONException {
        //  ////System.out.println("childArray inside ==="+user_id);
        // ////System.out.println("statename  ==="+state);
        double personalExcemption = 0;
        final double childTaxCredit = 0;
        double otherCredit = 0;
        double credit = 0;
        double standerdDeduction = 0;
        final double itemizedDeduction = 0;
        double deduction = 0;
        double stateTxaIncome = 0;
        double stateStandardTax = 0;
        final double stateAMT = 0;
        double stateCalculatedTax = 0;
        double stateTax = 0;
        double stateTax1 = 0;
        final double stateAgi = income;
        double dependentExcemption = 0;
        final double otherExcemption = 0;
        double exemption = 0;
        final double otherExemption = 0;
        double TaxCredit = 0;
        String filingStatus = null;
        int kidCount = 0;
        //int kidCountForSingle=0;



        // MongoCollection stateTaxCollection1 =
        // jongo.getCollection(stateTaxCollection);
        // ////System.out.println("state agi :"+stateAgi);
        filingStatus = fillingStatus;
        kidCount = noOfExcemtion;
        //////System.out.println("noOfExcemtion==inital=="+noOfExcemtion);
        if (fillingStatus.equals("Married Filing Jointly")) {
            kidCount = noOfExcemtion - 2;
            if (kidCount < 0) {
                kidCount = 0;
                //////System.out.println("noOfExcemtion==="+noOfExcemtion);
            }
        } else if (fillingStatus.equals("Head of Household")) {
            kidCount = noOfExcemtion-1;

            /*	for(int i=0;i<childArray.length();i++){
				////System.out.println("childArray=="+childArray);
			if(childArray.getJSONObject(i).getString("flag").equals("No")){
				kidCount = noOfExcemtion-1;
				////System.out.println("kidCount==="+kidCount);
			}else{
				kidCount = noOfExcemtion;
				////System.out.println("else====="+noOfExcemtion);
				////System.out.println("kidCount============"+kidCount);
			}

			}*/
            //////System.out.println("noOfExcemtion==aapaa"+noOfExcemtion+"kidCountaaaaa"+kidCount);

        } else {
            kidCount = noOfExcemtion - 1;
            if (kidCount < 0) {
                kidCount = 0;
            }
        }
        //kidCountForSingle=noOfExcemtion - 1;
        //////System.out.println("kidCountForSingle==122="+kidCountForSingle);
        try {

            JSONObject incomeJson = new JSONObject(jsonMapper.writeValueAsString(stateTaxValue));
            // ////System.out.println("MongoDBConnection.california-->>"+MongoDBConnection.california);
            if (state.equals("CALIFORNIA")) {
                final JSONObject californiaJsons = new JSONObject(jsonMapper.writeValueAsString(MongoDBConnection.california));

                if (filingStatus.equals("Single")) {
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");

                    if (stateAgi < californiaJsons.getJSONObject("single").getJSONArray("stateAgiLimit").getInt(0)) {
                        otherCredit = (int) MongoDBConnection.california.getSingle().getPersonalExcemption()
                                + (kidCount * (int) MongoDBConnection.california.getDependentExcemption());
                        credit = otherCredit + childTaxCredit;
                        deduction = Math.max(standerdDeduction, itemizedDeduction);
                        stateTxaIncome = stateAgi - deduction;
                        stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                        stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                        stateTax = stateCalculatedTax - credit;
                        // ////System.out.println(" otherCredit "+otherCredit+"
                        // credit "+credit+" deduction "+deduction+"
                        // stateTxaIncome "+stateTxaIncome+"stateStandardTax
                        // "+stateStandardTax+" stateCalculatedTax
                        // "+stateCalculatedTax+" stateTax "+stateTax);
                        return stateTax;
                    } else {
                        final double creditx = stateAgi
                                - californiaJsons.getJSONObject("single").getJSONArray("stateAgiLimit").getInt(0);
                        final double credity = creditx / 2500;
                        final double creditz = 6 * credity;
                        otherCredit = (int) MongoDBConnection.california.getSingle().getPersonalExcemption()
                                + (kidCount * (int) MongoDBConnection.california.getDependentExcemption());
                        credit = (otherCredit + childTaxCredit) - creditz;
                        if (credit < 0) {
                            credit = 0;
                        }
                        deduction = Math.max(standerdDeduction, itemizedDeduction);
                        stateTxaIncome = stateAgi - deduction;
                        stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                        stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                        stateTax = stateCalculatedTax - credit;
                        return stateTax;
                    }
                } else if (filingStatus.equals("Married Filing Jointly")) {

                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    if (stateAgi < 357417) {
                        otherCredit = 218 + (kidCount * 337);
                        credit = otherCredit + childTaxCredit;
                        //	////System.out.println("credi====t"+credit);
                        deduction = Math.max(standerdDeduction, itemizedDeduction);
                        //////System.out.println("deduction==="+deduction);
                        stateTxaIncome = stateAgi - deduction;
                        //////System.out.println("stateTxaIncome==="+stateTxaIncome);
                        stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                        //////System.out.println("stateStandardTax"+stateStandardTax);
                        stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                        //	////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                        stateTax = stateCalculatedTax - credit;
                        //////System.out.println("stateTax===1="+stateTax);
                        // ////System.out.println("inside if12-->"+stateTax);

                    } else {
                        final double creditx = stateAgi - 357417;
                        final double credity = creditx / 2500;
                        final double creditz = 12 * credity;
                        otherCredit = 218 + (kidCount * 337);
                        credit = (otherCredit + childTaxCredit) - creditz;
                        if (credit < 0) {
                            credit = 0;
                        }
                        deduction = Math.max(standerdDeduction, itemizedDeduction);
                        stateTxaIncome = stateAgi - deduction;
                        stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                        stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                        stateTax = stateCalculatedTax - credit;
                        //////System.out.println("inside else12-->"+stateTax);

                    }

                    // ////System.out.println("state taxa1-->"+stateTax);

                    // ////System.out.println("welcome marrage califonia-->>");
                    // ////System.out.println("stateAgi-->"+stateAgi);
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    // ////System.out.println("standerdDeduction-->"+standerdDeduction);
                    // //////System.out.println("MongoDBConnection.stateAgiLimitCaliforniaMarr.getInt(1)-->"+MongoDBConnection.stateAgiLimitCaliforniaMarr.getInt(1));
                    if (stateAgi < californiaJsons.getJSONObject("marriedFilingJoint").getJSONArray("stateAgiLimit")
                            .getInt(0)) {
                        // ////System.out.println("MongoDBConnection.personalExcemptionCaliMarr-->"+(int)
                        // MongoDBConnection.california.getMarriedFilingJoint().getPersonalExcemption());
                        // ////System.out.println("MongoDBConnection.dependentExcemptionCaliMarr-->"+(int)
                        // MongoDBConnection.california.getDependentExcemption());
                        otherCredit = (int) MongoDBConnection.california.getMarriedFilingJoint().getPersonalExcemption()
                                + (kidCount * (int) MongoDBConnection.california.getDependentExcemption());
                        credit = otherCredit + childTaxCredit;
                        deduction = Math.max(standerdDeduction, itemizedDeduction);
                        stateTxaIncome = stateAgi - deduction;
                        stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                        stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                        stateTax = stateCalculatedTax - credit;
                        // ////System.out.println("stateTax-2->"+stateTax);
                        return stateTax;

                    } else {
                        // ////System.out.println("else part");
                        // ////System.out.println("MongoDBConnection.stateAgiLimitCaliforniaMarr.getInt(0)-->"+californiaJsons.getJSONObject("marriedFilingJoint").getJSONArray("stateAgiLimit").getInt(0));
                        final double creditx = stateAgi - californiaJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("stateAgiLimit").getInt(0);
                        final double credity = creditx / 2500;
                        final double creditz = 12 * credity;
                        // ////System.out.println("MongoDBConnection.dependentExcemptionCaliMarr-->"+(int)
                        // MongoDBConnection.california.getDependentExcemption());
                        otherCredit = (int) MongoDBConnection.california.getMarriedFilingJoint().getPersonalExcemption()
                                + (kidCount * (int) MongoDBConnection.california.getDependentExcemption());
                        credit = (otherCredit + childTaxCredit) - creditz;
                        if (credit < 0) {
                            credit = 0;
                        }
                        deduction = Math.max(standerdDeduction, itemizedDeduction);
                        stateTxaIncome = stateAgi - deduction;
                        stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                        stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                        stateTax = stateCalculatedTax - credit;
                        //	////System.out.println("stateTax--3>"+stateTax);
                        return stateTax;
                    }
                } else if (filingStatus.equals("Head of Household")) {
                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    if (stateAgi < californiaJsons.getJSONObject("headOfHousehold").getJSONArray("stateAgiLimit")
                            .getInt(0)) {
                        if (maritalStatus.equals("No")){
                            otherCredit = (int) MongoDBConnection.california.getSingle().getPersonalExcemption()
                                    + (kidCount * (int) MongoDBConnection.california.getDependentExcemption());
                            /*							////System.out.println("otherCredit===12aaaaaaaaa="+otherCredit+"perspnall==="
									+MongoDBConnection.california.getSingle().getPersonalExcemption()+ "MongoDBConnection.california.getDependentExcemption() "
									+ "jdjjjjfjf==="+MongoDBConnection.california.getDependentExcemption()+"kidcount=="+kidCount);*/
                        }else{
                            otherCredit = (kidCount * (int) MongoDBConnection.california.getDependentExcemption());
                            //	////System.out.println("otherCredit==121212===="+otherCredit);
                        }

                        credit = otherCredit + childTaxCredit;
                        //////System.out.println("credit=="+credit+"otherCredit==="+otherCredit+"childTaxCredit"+childTaxCredit);
                        deduction = Math.max(standerdDeduction, itemizedDeduction);
                        //////System.out.println("deduction===="+deduction);
                        stateTxaIncome = stateAgi - deduction;
                        //////System.out.println("deduction==="+deduction+"stateAgi==="+stateAgi+"deduction==="+deduction);
                        stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                        //////System.out.println("stateStandardTax===="+stateStandardTax);
                        stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                        //////System.out.println("stateCalculatedTax====="+stateCalculatedTax);
                        stateTax = stateCalculatedTax - credit;
                        //////System.out.println("stateTax====4==="+stateTax);
                        return stateTax;
                    } else {
                        final double creditx = stateAgi - californiaJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("stateAgiLimit").getInt(0);
                        final double credity = creditx / 2500;
                        final double creditz = 6 * credity;
                        otherCredit = (kidCount * (int) MongoDBConnection.california.getDependentExcemption());
                        credit = (otherCredit + childTaxCredit) - creditz;
                        if (credit < 0) {
                            credit = 0;
                        }
                        deduction = Math.max(standerdDeduction, itemizedDeduction);
                        stateTxaIncome = stateAgi - deduction;
                        stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                        stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                        stateTax = stateCalculatedTax - credit;
                        return stateTax;
                    }
                }
            }
            if (state.equals("ARKANSAS")) {
                // ////System.out.println("inside
                // ARKANSAS"+MongoDBConnection.arkansas);
                jsonarkansas = new JSONObject(jsonMapper.writeValueAsString(MongoDBConnection.arkansas));
                //// ////System.out.println("jsonarkansas===="+jsonarkansas);

                if (filingStatus.equals("Single")) {

                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    otherCredit = incomeJson.getJSONObject("single").getDouble("personalExcemption")
                            + (kidCount * incomeJson.getDouble("dependentExcemption"));
                    credit = otherCredit + childTaxCredit;
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction;
                    if (stateAgi < jsonarkansas.getJSONObject("single").getJSONArray("stateAgiLimit").getInt(0)) {
                        stateStandardTax = newStateStandardTax(stateTxaIncome, incomeJson, filingStatus);
                    }
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax2-->>"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    stateTax1 = 0;
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    otherCredit = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption")
                            + (kidCount * incomeJson.getDouble("dependentExcemption"));
                    credit = otherCredit + childTaxCredit;
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction;
                    if (stateAgi < jsonarkansas.getJSONObject("marriedFilingJoint").getJSONArray("stateAgiLimit")
                            .getInt(0)) {
                        stateStandardTax = newStateStandardTax(stateTxaIncome, incomeJson, filingStatus);
                    }
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax1 = stateCalculatedTax - credit;

                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    otherCredit = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption")
                            + (kidCount * incomeJson.getDouble("dependentExcemption"));
                    credit = otherCredit + childTaxCredit;
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction;

                    if (stateAgi < jsonarkansas.getJSONObject("marriedFilingJoint").getJSONArray("stateAgiLimit")
                            .getInt(0)) {
                        stateStandardTax = newStateStandardTax(stateTxaIncome, incomeJson, filingStatus);
                    }
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;

                    return stateTax;
                } else if (filingStatus.equals("Head of Household")){
                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");

                    if (maritalStatus.equals("No")){
                        otherCredit = incomeJson.getJSONObject("single").getDouble("personalExcemption")
                                + (kidCount * incomeJson.getDouble("dependentExcemption"));
                        /*	////System.out.println("otherCredit==="+otherCredit+"kidCount=aaa=="+kidCount);
						////System.out.println("personalExemption==aasasasaasasa="+incomeJson.getJSONObject("single").getDouble("personalExcemption"));
						////System.out.println("dependentExemption===="+incomeJson.getDouble("dependentExcemption"));*/

                    }else{
                        otherCredit = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption")
                                + (kidCount * incomeJson.getDouble("dependentExcemption"));
                        //////System.out.println("otherCredit==121212===="+otherCredit);
                    }

                    //otherCredit = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption")
                    //+ (kidCount * incomeJson.getDouble("dependentExcemption"));
                    credit = otherCredit + childTaxCredit;
                    //////System.out.println("credit=aa==="+credit+"otherCredit==aa="+otherCredit+"childTaxCreditaa=="+childTaxCredit);
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    //////System.out.println("deduction== appp"+deduction);
                    stateTxaIncome = stateAgi - deduction;
                    //////System.out.println("stateTxaIncome==="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax==aa="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax==aa="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax==="+stateTax);
                    return stateTax;
                }
            }
            if (state.equals("KENTUCKY")) {
                if (filingStatus.equals("Single")) {
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    TaxCredit = personalExcemption * 1;
                    credit = TaxCredit + childTaxCredit;
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);

                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax1--->"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    TaxCredit = personalExcemption + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    credit = TaxCredit + childTaxCredit;
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax1--->"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {


                    if (maritalStatus.equals("No")){
                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        TaxCredit = personalExcemption +(kidCount * (incomeJson.getDouble("dependentExcemption")));
                        //////System.out.println("personalExcemption==="+personalExcemption+"kidCount jaka"+kidCount+"dependent exemption=="+(incomeJson.getDouble("dependentExcemption")));

                    }else{
                        personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                        TaxCredit = personalExcemption + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                        //////System.out.println("otherCredit==121212===="+otherCredit);
                    }


                    //personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                    //TaxCredit = personalExcemption + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    credit = TaxCredit + childTaxCredit;
                    //////System.out.println("credit===="+credit+"TaxCredit===="+TaxCredit+"childTaxCredit==="+childTaxCredit);
                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    //////System.out.println("standerdDeduction==="+standerdDeduction);
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    //////System.out.println("deduction=====k"+deduction);
                    stateTxaIncome = stateAgi - deduction;
                    //////System.out.println("stateTxaIncome==="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax===="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax====="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax====="+stateTax);
                    // ////System.out.println("credit1--->"+credit);
                    // ////System.out.println("stateTax1--->"+stateTax);
                    return stateTax;
                }
            }
            if (state.equals("DELAWARE")) {
                if (filingStatus.equals("Single")) {
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    // ////System.out.println("personalExcemption===="+personalExcemption);
                    TaxCredit = personalExcemption * 1;
                    // ////System.out.println("TaxCredit===DELAWARE"+TaxCredit);
                    credit = TaxCredit + childTaxCredit;
                    // ////System.out.println("credit===DELAWARE"+credit);
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    // ////System.out.println("standerdDeduction==="+standerdDeduction);
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    // ////System.out.println("deduction===="+deduction);
                    stateTxaIncome = stateAgi - deduction;
                    // ////System.out.println("stateTxaIncome==="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    // ////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    // ////System.out.println("stateCalculatedTax====0"+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax===="+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    TaxCredit = personalExcemption + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    credit = TaxCredit + childTaxCredit;
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction;
                    // ////System.out.println("stateTxaIncome------->>>>"+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    // ////System.out.println("stateStandardTax1--->>"+stateStandardTax);
                    // ////System.out.println("credit-->>"+credit);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax--->>"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {
                    personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");

                    if (maritalStatus.equals("No")){
                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        //////System.out.println("personalExcemption===="+personalExcemption);
                        //TaxCredit = personalExcemption * 1;
                        TaxCredit = personalExcemption +(kidCount * (incomeJson.getDouble("dependentExcemption")));
                        //////System.out.println("TaxCredit==="+TaxCredit+"kidCount==="+kidCount+"dependent=="+(incomeJson.getDouble("dependentExcemption")));

                    }else{
                        TaxCredit = personalExcemption + (kidCount * (incomeJson.getDouble("dependentExcemption")));;
                        ////System.out.println("otherCredit==121212===="+otherCredit);
                    }
                    //TaxCredit = personalExcemption + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    credit = TaxCredit + childTaxCredit;
                    //////System.out.println("credit===d"+credit);
                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    //////System.out.println("standerdDeduction====="+standerdDeduction);
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    //////System.out.println("deduction====="+deduction);
                    stateTxaIncome = stateAgi - deduction;
                    //////System.out.println("stateTxaIncome===="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax===="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateStandardTax===="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax====="+stateTax);
                    return stateTax;
                }
            }
            if (state.equals("NEBRASKA")){
                if (filingStatus.equals("Single")) {
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    TaxCredit = personalExcemption * 1;
                    credit = TaxCredit + childTaxCredit;
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction;
                    // ////System.out.println("stateTxaIncome00-->>"+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);

                    // ////System.out.println("stateStandardTax00-->>"+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    // ////System.out.println("stateCalculatedTax00--->>"+stateCalculatedTax);
                    // ////System.out.println("credit00-->"+credit);

                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax00--->>"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    TaxCredit = personalExcemption + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    credit = TaxCredit + childTaxCredit;
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    // ////System.out.println("stateCalculatedTax00--->"+stateCalculatedTax);
                    // ////System.out.println("credit00--->>"+credit);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax00--->>"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {


                    if (maritalStatus.equals("No")){
                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        //TaxCredit = personalExcemption * 1;
                        //////System.out.println("personalExcemption==aa11="+personalExcemption);
                        TaxCredit = personalExcemption +(kidCount * (incomeJson.getDouble("dependentExcemption")));
                        //////System.out.println("TaxCredit===="+TaxCredit+"kidCount====="+kidCount+"dependent------"+(incomeJson.getDouble("dependentExcemption")));


                    }else{
                        personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                        TaxCredit = personalExcemption + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    }
                    //personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                    //TaxCredit = personalExcemption + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    credit = TaxCredit + childTaxCredit;
                    //////System.out.println("credit===="+credit+"TaxCredit==="+TaxCredit);
                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    //////System.out.println("deduction====="+deduction);
                    stateTxaIncome = stateAgi - deduction;
                    //////System.out.println("stateTxaIncome===="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax===="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax====="+stateTax);
                    // ////System.out.println("stateTax00--->>"+stateTax);
                    return stateTax;
                }
            }
            if (state.equals("MARYLAND")){
                String filingStatusTemp = "";
                double dependentExcemptions=0;
                if (filingStatus.equals("Single")) {
                    filingStatusTemp = "single";

                } else if (filingStatus.equals("Married Filing Jointly")) {
                    // ////System.out.println("marriedFilingJoint--->>");
                    filingStatusTemp = "marriedFilingJoint";
                } else if (filingStatus.equals("Head of Household")) {

                    if(maritalStatus.equals("No")){
                        filingStatusTemp = "single";
                        dependentExcemptions=incomeJson.getDouble("dependentExcemption");
                        personalExcemption = incomeJson.getJSONObject(filingStatusTemp).getDouble("personalExcemption")+incomeJson.getDouble("dependentExcemption");
                        //////System.out.println("personalExcemption===maryland"+personalExcemption);
                        //////System.out.println("dependentExcemptions===maryland"+dependentExcemptions);
                    }
                    filingStatusTemp = "headOfHousehold";
                }

                final JSONArray stateAgiLimitmaryLand = MongoDBConnection.maryLandJsons.getJSONObject(filingStatusTemp)
                        .getJSONArray("stateAgiLimit");

                personalExcemption = incomeJson.getJSONObject(filingStatusTemp).getDouble("personalExcemption");

                standerdDeduction = incomeJson.getJSONObject(filingStatusTemp).getDouble("standarddeduction");

                if (stateAgi > stateAgiLimitmaryLand.getInt(1) && stateAgi < stateAgiLimitmaryLand.getInt(2)) {

                    personalExcemption = MongoDBConnection.maryLandJsons.getJSONObject(filingStatusTemp)
                            .getJSONArray("personalExcemptionlimit").getInt(0);// 800

                } else if (stateAgi > stateAgiLimitmaryLand.getInt(2) && stateAgi < stateAgiLimitmaryLand.getInt(3)) {

                    personalExcemption = MongoDBConnection.maryLandJsons.getJSONObject(filingStatusTemp)
                            .getJSONArray("personalExcemptionlimit").getInt(1);
                } else if (stateAgi > stateAgiLimitmaryLand.getInt(3)) {
                    personalExcemption = MongoDBConnection.maryLandJsons.getJSONObject(filingStatusTemp)
                            .getJSONArray("personalExcemptionlimit").getInt(2);
                } else {
                    if(filingStatus.equals("Head of Household")){
                        if(maritalStatus.equals("No")){
                            personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption")+dependentExcemptions;
                            //////System.out.println("personalExcemption= total=="+incomeJson.getJSONObject("single").getDouble("personalExcemption"));
                            //////System.out.println("dependentExcemptions==="+dependentExcemptions);
                            //////System.out.println("personalExcemption==="+personalExcemption);
                        }
                        //personalExcemption = incomeJson.getJSONObject(filingStatusTemp).getDouble("personalExcemption");
                        ////System.out.println("personalExcemption=7888=="+personalExcemption);
                    }else{
                        personalExcemption = incomeJson.getJSONObject(filingStatusTemp).getDouble("personalExcemption")+dependentExcemptions;
                        //personalExcemption = incomeJson.getJSONObject(filingStatusTemp).getDouble("personalExcemption");
                        ////System.out.println("personalExcemption=7888=67777="+personalExcemption);

                    }

                }
                if (stateAgi > stateAgiLimitmaryLand.getInt(0)) {

                    standerdDeduction = MongoDBConnection.maryLandJsons.getJSONObject(filingStatusTemp)
                            .getDouble("standerdDeductionRate") * stateAgi;// 0.15//

                    if (standerdDeduction < MongoDBConnection.maryLandJsons.getJSONObject(filingStatusTemp)
                            .getJSONArray("standerdDeductionLimit").getInt(0)) {// 1500

                        standerdDeduction = MongoDBConnection.maryLandJsons.getJSONObject(filingStatusTemp)
                                .getJSONArray("standerdDeductionLimit").getInt(0);

                    } else {
                        standerdDeduction = MongoDBConnection.maryLandJsons.getJSONObject(filingStatusTemp)
                                .getJSONArray("standerdDeductionLimit").getInt(1);// 2000

                    }
                } else {
                    //// ////System.out.println(")))-->>");
                    //// ////System.out.println("incomeJson-->"+incomeJson);
                    standerdDeduction = incomeJson.getJSONObject(filingStatusTemp).getInt("standarddeduction");

                    //// ////System.out.println("standerdDeduction====="+standerdDeduction);
                }
                credit = TaxCredit + childTaxCredit;
                //////System.out.println("credit==="+credit);
                deduction = Math.max(standerdDeduction, itemizedDeduction);
                //////System.out.println("deduction===="+deduction);
                stateTxaIncome = stateAgi - deduction - personalExcemption;
                //////System.out.println("stateTxaIncome======"+stateTxaIncome);
                stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                //////System.out.println("stateStandardTax===="+stateStandardTax);
                stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                //////System.out.println("stateCalculatedTax====="+stateCalculatedTax);
                stateTax = stateCalculatedTax - credit;
                //////System.out.println("stateTaxMariland=== "+stateTax);
                return stateTax;

            } else if (state.equals("WISCONSIN")) {
                double standardDeduction = 0;

                // ////System.out.println("wel wis");

                if (filingStatus.equals("Single")) {

                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    exemption = personalExcemption + (kidCount * dependentExcemption);
                    standardDeduction = Math.max(0,
                            MongoDBConnection.wisconsin.getSingle().getStandardDeductionPhaseOutLimitMax() - (income
                                    - MongoDBConnection.wisconsin.getSingle().getStandardDeductionPhaseOutLimit())
                            * MongoDBConnection.wisconsin.getSingle().getStandardDeductionPhaseOutRates());
                    deduction = Math.max(standardDeduction, itemizedDeduction);
                    stateTxaIncome = income - deduction - exemption - otherExemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    return stateTax;

                } else if (filingStatus.equals("Married Filing Jointly")) {

                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    exemption = personalExcemption + (kidCount * dependentExcemption);
                    standardDeduction = Math.max(0,
                            standerdDeduction - ((income - MongoDBConnection.wisconsin.getMarriedFilingJoint()
                                    .getStandardDeductionPhaseOutLimit())
                                    * MongoDBConnection.wisconsin.getMarriedFilingJoint()
                                    .getStandardDeductionPhaseOutRates()));
                    deduction = Math.max(standardDeduction, itemizedDeduction);
                    stateTxaIncome = income - deduction - exemption - otherExemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")){

                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                    if(maritalStatus.equals("No")){
                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        dependentExcemption = incomeJson.getDouble("dependentExcemption");
                        exemption = personalExcemption + (kidCount * dependentExcemption);
                        //////System.out.println("exemption==="+exemption+"kidCount===="+kidCount);
                        //////System.out.println("personalExcemption===="+personalExcemption);
                        //////System.out.println("dependentExcemption==="+dependentExcemption);

                    }else{
                        dependentExcemption = incomeJson.getDouble("dependentExcemption");
                        exemption = personalExcemption + (kidCount * dependentExcemption);
                    }
                    //	dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    //exemption = personalExcemption + (kidCount * dependentExcemption);
                    /*	standardDeduction = Math.max(0,
							MongoDBConnection.wisconsin.getHeadOfHousehold().getStandarddeduction()
									- (income - MongoDBConnection.wisconsin.getHeadOfHousehold()
											.getStandardDeductionPhaseOutLimit())
                     * MongoDBConnection.wisconsin.getHeadOfHousehold()
													.getStandardDeductionPhaseOutRates());*/
                    standardDeduction = Math.max(0, 13240 - ((43217 - 14780) * 0.22515)- (income - 43217) * 0.12);
                    //////System.out.println("sddd"+	MongoDBConnection.wisconsin.getHeadOfHousehold().getStandarddeduction());
                    //////System.out.println("income-----"+income);
                    /*////System.out.println("kkkkk"+ MongoDBConnection.wisconsin.getHeadOfHousehold()
					.getStandardDeductionPhaseOutLimit());
					////System.out.println("ratess"+ MongoDBConnection.wisconsin.getHeadOfHousehold()
					.getStandardDeductionPhaseOutRates());*/

                    deduction = Math.max(standardDeduction, itemizedDeduction);
                    //////System.out.println("standardDeduction==="+standardDeduction);
                    //////System.out.println("deduction==="+deduction);
                    stateTxaIncome = income - deduction - exemption - otherExemption;
                    //////System.out.println("stateTxaIncome==="+stateTxaIncome+"exemption==="+exemption);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax===="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //	////System.out.println("stateTax====="+stateTax);
                    return stateTax;
                }
            } else if (state.equals("NEW MEXICO")){
                double hoseofheadpersonalExcemption = 0;

                double standardDeduction = 0;
                double stateTaxableIncome = 0;
                double aditionalDeduction = 0;
                double otherDeduction = 0;
                // ////System.out.println("filingStatus.equals-->>"+filingStatus);
                final JSONObject newMaxcios = new JSONObject(jsonMapper.writeValueAsString(MongoDBConnection.newMaxcio));
                if (filingStatus.equals("Single")) {

                    standardDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    hoseofheadpersonalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    exemption = personalExcemption + (kidCount * hoseofheadpersonalExcemption);
                    if (income <= 36667) {
                        otherDeduction = 2500;
                    }
                    aditionalDeduction = Math.max(0, 2500 - Math.max(0, (income - 20000) * 0.15));
                    deduction = Math.max(standardDeduction, itemizedDeduction);
                    stateTaxableIncome = income - deduction - exemption - otherExemption - aditionalDeduction
                            - otherDeduction;
                    stateStandardTax = stateStandardTaxValue(stateTaxableIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    credit = childTaxCredit + otherCredit;
                    stateTax = stateCalculatedTax - credit;

                    standardDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    hoseofheadpersonalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    exemption = personalExcemption + (kidCount * hoseofheadpersonalExcemption);

                    standardDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    hoseofheadpersonalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    exemption = personalExcemption + (kidCount * hoseofheadpersonalExcemption);
                    for (int i = 0; i < newMaxcios.getJSONObject("single").getJSONArray("incomeLimit").length(); i++) {
                        if (income <= newMaxcios.getJSONObject("single").getJSONArray("incomeLimit").getInt(i)) {
                            otherDeduction = MongoDBConnection.newMaxcio.getOtherDeduction();
                        }
                    }
                    aditionalDeduction = Math.max(0, MongoDBConnection.newMaxcio.getOtherDeduction() - Math.max(0,
                            (income - MongoDBConnection.newMaxcio.getSingle().getAditionalDeductionMinIncome())
                            * MongoDBConnection.newMaxcio.getSingle().getAditionalDeductionMinIncomeRates()));
                    deduction = Math.max(standardDeduction, itemizedDeduction);
                    stateTaxableIncome = income - deduction - exemption - otherExemption - aditionalDeduction
                            - otherDeduction;
                    stateStandardTax = stateStandardTaxValue(stateTaxableIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    credit = childTaxCredit + otherCredit;
                    stateTax = stateCalculatedTax - credit;

                    return stateTax;

                } else if (filingStatus.equals("Married Filing Jointly")) {
                    // //////System.out.println("welcome merg-->");
                    standerdDeduction = MongoDBConnection.newMaxcio.getMarriedFilingJoint().getStandarddeduction();
                    //// ////System.out.println("standerdDeduction1-->>"+standerdDeduction);
                    personalExcemption = MongoDBConnection.newMaxcio.getMarriedFilingJoint().getPersonalExcemption();
                    // //////System.out.println("personalExcemption1-->>"+personalExcemption);
                    dependentExcemption = MongoDBConnection.newMaxcio.getDependentExcemption();
                    // //////System.out.println("dependentExcemption1-->>"+dependentExcemption);
                    exemption = personalExcemption + (kidCount * dependentExcemption);
                    // //////System.out.println("exemption1-->>"+exemption);

                    // //////System.out.println("newMaxicoOtherDed-->>"+newMaxicoOtherDed);
                    for (int i = 0; i < newMaxcios.getJSONObject("marriedFilingJoint").getJSONArray("incomeLimit")
                            .length(); i++) {
                        // //////System.out.println("incomeLimitM.getInt(i)-->>"+incomeLimitM.getInt(i));
                        if (income <= newMaxcios.getJSONObject("marriedFilingJoint").getJSONArray("incomeLimit")
                                .getInt(i)) {
                            otherDeduction = MongoDBConnection.newMaxcio.getOtherDeduction();
                        }

                    }

                    aditionalDeduction = Math.max(0,
                            otherDeduction - Math.max(0, (income - MongoDBConnection.aditionalDeductionMinIncomeM)
                                    * MongoDBConnection.aditionalDeductionMinIncomeRatesM));
                    // //////System.out.println("aditionalDeduction1-->"+aditionalDeduction);
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    // //////System.out.println("deduction1-->"+deduction);

                    stateTaxableIncome = income - deduction - exemption - otherExemption - aditionalDeduction
                            - otherDeduction;
                    // //////System.out.println("stateTaxableIncome1-->"+stateTaxableIncome);

                    stateStandardTax = stateStandardTaxValue(stateTaxableIncome, incomeJson, filingStatus);
                    //// ////System.out.println("stateStandardTax1-->"+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //// ////System.out.println("stateCalculatedTax1-->"+stateCalculatedTax);

                    credit = childTaxCredit + otherCredit;
                    //// ////System.out.println("credit-->"+credit);
                    stateTax1 = stateCalculatedTax - credit;

                    //// ////System.out.println("stateTax=11==="+stateTax1);
                    return stateTax1;
                } else if (filingStatus.equals("Head of Household")) {

                    standardDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    //// ////System.out.println("standardDeduction in hoh0
                    //// --->>"+standardDeduction);
                    personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                    //// ////System.out.println("personalExcemption in hoh0
                    //// --->>"+personalExcemption);
                    if(maritalStatus.equals("No")){
                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        hoseofheadpersonalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        exemption = personalExcemption + (kidCount * MongoDBConnection.newMaxcio.getDependentExcemption());
                        //////System.out.println("personalExcemption=="+personalExcemption);
                        //////System.out.println("hoseofheadpersonalExcemption=="+MongoDBConnection.newMaxcio.getDependentExcemption());
                        /*////System.out.println("exemption==="+exemption+"kidconnut=="
								+kidCount+"dependentExcemption="+dependentExcemption);*/

                    }else{
                        dependentExcemption = incomeJson.getDouble("dependentExcemption");
                        exemption = (kidCount * MongoDBConnection.newMaxcio.getDependentExcemption());

                    }
                    //dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    //exemption = (kidCount * dependentExcemption);
                    deduction = Math.max(standardDeduction, itemizedDeduction);
                    //////System.out.println("deduction===="+deduction);
                    stateTaxableIncome = income - deduction - exemption - otherExemption - aditionalDeduction
                            - otherDeduction;
                    /*////System.out.println("stateTaxableIncome==="+stateTaxableIncome+"income=="+income+
							"deduction==="+deduction+"exemption:::::"
							+exemption+"otherExemption==="+otherExemption+"aditionalDeduction==="
							+aditionalDeduction);*/
                    stateStandardTax = stateStandardTaxValue(stateTaxableIncome, incomeJson, filingStatus);
                    //	////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    credit = childTaxCredit + otherCredit;
                    //////System.out.println("credit==="+credit);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax==="+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    // ////System.out.println("4----->");
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    exemption = personalExcemption + (kidCount * dependentExcemption);
                    for (int i = 0; i < newMaxcios.getJSONObject("marriedFilingJoint").getJSONArray("incomeLimit")
                            .length(); i++) {
                        if (income <= newMaxcios.getJSONObject("marriedFilingJoint").getJSONArray("incomeLimit")
                                .getInt(i)) {
                            otherDeduction = MongoDBConnection.newMaxcio.getOtherDeduction();
                        }

                    }
                    aditionalDeduction = Math.max(0,
                            otherDeduction - Math.max(0, (income - MongoDBConnection.aditionalDeductionMinIncomeM)
                                    * MongoDBConnection.aditionalDeductionMinIncomeRatesM));
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTaxableIncome = income - deduction - exemption - otherExemption - aditionalDeduction
                            - otherDeduction;
                    stateStandardTax = stateStandardTaxValue(stateTaxableIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    credit = childTaxCredit + otherCredit;
                    stateTax = stateCalculatedTax - credit;
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {
                    // ////System.out.println("5----->");
                    standardDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");


                    if(maritalStatus.equals("No")){
                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        hoseofheadpersonalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        exemption = personalExcemption + (kidCount * hoseofheadpersonalExcemption);

                    }else{
                        dependentExcemption = incomeJson.getDouble("dependentExcemption");
                        exemption = (kidCount * dependentExcemption);

                    }


                    //dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    //exemption = (kidCount * dependentExcemption);
                    deduction = Math.max(standardDeduction, itemizedDeduction);
                    stateTaxableIncome = income - deduction - exemption - otherExemption - aditionalDeduction
                            - otherDeduction;
                    stateStandardTax = stateStandardTaxValue(stateTaxableIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    credit = childTaxCredit + otherCredit;
                    stateTax = stateCalculatedTax - credit;
                    return stateTax;
                }
            } else if (state.equals("OHIO")) {

                // ////System.out.println("ohio-->>");
                // ////System.out.println("hi-->");
                final JSONObject OHIOJsons = new JSONObject(jsonMapper.writeValueAsString(MongoDBConnection.OHIOJson));
                // ////System.out.println("singleOhioLimit-->"+OHIOJsons.getJSONObject("single").getJSONArray("incomeLimit"));
                // ////System.out.println("income-->"+income);

                final double standardDeduction = 0;
                double stateTaxableIncome = 0;
                // ////System.out.println("filingStatus--->>"+filingStatus);
                if (filingStatus.equals("Single")) {

                    deduction = Math.max(standardDeduction, itemizedDeduction);
                    if (income <= 40000) {
                        // ////System.out.println("1-->>");
                        personalExcemption = 2200;
                        dependentExcemption = 2200;
                    } else if (40001 < income && income < 79999) {
                        // ////System.out.println("2-->>");
                        personalExcemption = 1950;
                        dependentExcemption = 1950;
                    } else if (income > 80000) {
                        // ////System.out.println("3-->>");
                        personalExcemption = 1700;
                        dependentExcemption = 1700;
                    }
                    exemption = ((personalExcemption) * 1);
                    stateTaxableIncome = income - deduction - exemption - otherExemption;
                    stateStandardTax = stateStandardTaxValue(stateTaxableIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;

                    // ////System.out.println("in singale state tax-->"+stateTax);

                    deduction = Math.max(standardDeduction, itemizedDeduction);

                    if (income <= OHIOJsons.getJSONObject("single").getJSONArray("incomeLimit").getInt(0)) {
                        // ////System.out.println("1 ))");

                        // static StatetaxModel OHIOJson
                        // =MongoDBConnection.OHIOJson;
                        // static JSONObject OHIOJsons
                        // =MongoDBConnection.OHIOJsons;
                        // ConnecticutJsons.getJSONObject("single").getJSONArray("personalTaxFactorCTRates").getDouble(i)
                        personalExcemption = OHIOJsons.getJSONObject("single").getJSONArray("personalExcemptionsValues")
                                .getInt(0);
                        dependentExcemption = OHIOJsons.getJSONObject("single")
                                .getJSONArray("dependentExcemptionValues").getInt(0);

                    } else if (OHIOJsons.getJSONObject("single").getJSONArray("incomeLimit").getInt(1) < income
                            && income < OHIOJsons.getJSONObject("single").getJSONArray("incomeLimit").getInt(2)) {
                        // ////System.out.println("2 ))");
                        personalExcemption = OHIOJsons.getJSONObject("single").getJSONArray("personalExcemptionsValues")
                                .getInt(1);
                        dependentExcemption = OHIOJsons.getJSONObject("single")
                                .getJSONArray("dependentExcemptionValues").getInt(1);

                    } else if (income > OHIOJsons.getJSONObject("single").getJSONArray("incomeLimit").getInt(3)) {
                        // ////System.out.println("3 ))");
                        personalExcemption = OHIOJsons.getJSONObject("single").getJSONArray("personalExcemptionsValues")
                                .getInt(2);
                        dependentExcemption = OHIOJsons.getJSONObject("single")
                                .getJSONArray("dependentExcemptionValues").getInt(2);

                    }

                    //// ////System.out.println("dependentExcemption1-->"+dependentExcemption);
                    //// ////System.out.println("personalExcemption1-->>"+personalExcemption);
                    exemption = ((personalExcemption) * 1);
                    stateTaxableIncome = income - deduction - exemption - otherExemption;
                    stateStandardTax = stateStandardTaxValue(stateTaxableIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("in singale state tax1-->"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    deduction = Math.max(standardDeduction, itemizedDeduction);

                    if (income <= OHIOJsons.getJSONObject("marriedFilingJoint").getJSONArray("incomeLimit").getInt(0)) {
                        // ////System.out.println("1 ))");
                        personalExcemption = OHIOJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("personalExcemptionsValues").getInt(0);
                        dependentExcemption = OHIOJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("dependentExcemptionValues").getInt(0);

                    } else if (OHIOJsons.getJSONObject("marriedFilingJoint").getJSONArray("incomeLimit")
                            .getInt(1) < income
                            && income < OHIOJsons.getJSONObject("marriedFilingJoint").getJSONArray("incomeLimit")
                            .getInt(2)) {
                        // ////System.out.println("2 ))");
                        personalExcemption = OHIOJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("personalExcemptionsValues").getInt(1);
                        dependentExcemption = OHIOJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("dependentExcemptionValues").getInt(1);

                    } else if (income > OHIOJsons.getJSONObject("marriedFilingJoint").getJSONArray("incomeLimit")
                            .getInt(3)) {
                        // ////System.out.println("3 ))");
                        personalExcemption = OHIOJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("personalExcemptionsValues").getInt(2);
                        dependentExcemption = OHIOJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("dependentExcemptionValues").getInt(2);

                    }

                    if (kidCount > 0) {
                        exemption = ((2 * personalExcemption) + (dependentExcemption * kidCount));
                    } else {
                        exemption = ((2 * personalExcemption));
                    }
                    stateTaxableIncome = income - deduction - exemption - otherExemption;
                    stateStandardTax = stateStandardTaxValue(stateTaxableIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {

                    deduction = Math.max(standardDeduction, itemizedDeduction);
                    if (income <= 40000) {
                        //// ////System.out.println("1-->");
                        personalExcemption = 2200;
                        dependentExcemption = 2200;
                    } else if (40001 < income && income < 79999) {
                        //// ////System.out.println("2-->");
                        personalExcemption = 1950;
                        dependentExcemption = 1950;
                    } else if (income > 80000) {
                        //// ////System.out.println("3-->");
                        personalExcemption = 1700;
                        dependentExcemption = 1700;
                    }

                    if(maritalStatus.equals("No")){



                        if (income <= OHIOJsons.getJSONObject("single").getJSONArray("incomeLimit").getInt(0)) {
                            ////System.out.println("1 ))");

                            // static StatetaxModel OHIOJson
                            // =MongoDBConnection.OHIOJson;
                            // static JSONObject OHIOJsons
                            // =MongoDBConnection.OHIOJsons;
                            // ConnecticutJsons.getJSONObject("single").getJSONArray("personalTaxFactorCTRates").getDouble(i)
                            personalExcemption = OHIOJsons.getJSONObject("single").getJSONArray("personalExcemptionsValues")
                                    .getInt(0);
                            ///////System.out.println("personalExcemption=1==="+personalExcemption);

                        } else if (OHIOJsons.getJSONObject("single").getJSONArray("incomeLimit").getInt(1) < income
                                && income < OHIOJsons.getJSONObject("single").getJSONArray("incomeLimit").getInt(2)) {
                            ////System.out.println("2 ))");
                            personalExcemption = OHIOJsons.getJSONObject("single").getJSONArray("personalExcemptionsValues")
                                    .getInt(1);
                            ////System.out.println("personalExcemption---2"+personalExcemption);

                        } else if (income > OHIOJsons.getJSONObject("single").getJSONArray("incomeLimit").getInt(3)) {
                            ////System.out.println("3 ))");
                            personalExcemption = OHIOJsons.getJSONObject("single").getJSONArray("personalExcemptionsValues")
                                    .getInt(2);
                            ////System.out.println("personalExcemption==3="+personalExcemption);


                        }

                        exemption = ((personalExcemption) +(((kidCount) * dependentExcemption)));
                        //////System.out.println("exemption===="+exemption);
                    }else{
                        exemption = (((kidCount) * dependentExcemption));
                    }
                    //exemption = (((kidCount) * dependentExcemption));
                    stateTaxableIncome = income - deduction - exemption - otherExemption;
                    /*////System.out.println("stateTaxableIncome==="+stateTaxableIncome+
							"deduction==="+deduction+"exemption=="+exemption+"otherExemption=="
							+otherExemption);*/
                    stateStandardTax = stateStandardTaxValue(stateTaxableIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax===="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax==="+stateTax);

                    deduction = Math.max(standardDeduction, itemizedDeduction);

                    if (income <= OHIOJsons.getJSONObject("headOfHousehold").getJSONArray("incomeLimit").getInt(0)) {
                        // ////System.out.println("1 ))");
                        personalExcemption = OHIOJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("personalExcemptionsValues").getInt(0);
                        dependentExcemption = OHIOJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("dependentExcemptionValues").getInt(0);

                    } else if (OHIOJsons.getJSONObject("headOfHousehold").getJSONArray("incomeLimit").getInt(1) < income
                            && income < OHIOJsons.getJSONObject("headOfHousehold").getJSONArray("incomeLimit")
                            .getInt(2)) {
                        // ////System.out.println("2 ))");
                        personalExcemption = OHIOJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("personalExcemptionsValues").getInt(1);
                        dependentExcemption = OHIOJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("dependentExcemptionValues").getInt(1);

                    } else if (income > OHIOJsons.getJSONObject("headOfHousehold").getJSONArray("incomeLimit")
                            .getInt(3)) {
                        // ////System.out.println("3 ))");
                        //// ////System.out.println("headOfHouseholdOhioLimit.getInt(i)-->>"+headOfHouseholdOhioLimit.getInt(3));
                        personalExcemption = OHIOJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("personalExcemptionsValues").getInt(2);
                        //// ////System.out.println("personalExcemption1-->>"+personalExcemption);
                        dependentExcemption = OHIOJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("dependentExcemptionValues").getInt(2);
                        //// ////System.out.println("dependentExcemption1-->>"+dependentExcemption);

                    }

                    //exemption = (((kidCount) * dependentExcemption));
                    stateTaxableIncome = income - deduction - exemption - otherExemption;
                    stateStandardTax = stateStandardTaxValue(stateTaxableIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);

                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax==12="+stateTax);
                    return stateTax;
                }
            } else if (state.equals("RHODE ISLAND")) {
                // public static JSONArray rhodeIsLandJsonsingleIncomeLimit ;
                // public static JSONArray
                // rhodeIsLandJsonmarriedFilingJointIncomeLimit;
                // public static JSONArray
                // rhodeIsLandJsonheadOfHouseholdIncomeLimit ;
                final JSONObject rhodeislandJsons = new JSONObject(
                        jsonMapper.writeValueAsString(MongoDBConnection.rhodeislandJson));

                double standardDeduction = 0;
                double couple = 0;
                double singles = 0;
                double incrementExemption = 0;
                double Hoh = 0;
                if (filingStatus.equals("Single")) {
                    if (income <= rhodeislandJsons.getJSONObject("single").getJSONArray("incomeLimit").getInt(0)) {
                        singles = rhodeislandJsons.getJSONObject("single").getJSONArray("singles").getInt(0);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("single").getJSONArray("incrementAGI")
                                .getInt(0);
                        final double incrementDeduction = MongoDBConnection.rhodeislandJson.getIncrementDeduction();

                        standardDeduction = Math.max(0,
                                (singles - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("single")
                                                .getJSONArray("incomeLimit").getInt(0)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (rhodeislandJsons
                                        .getJSONObject(
                                                "single")
                                        .getJSONArray("exemptionMax").getInt(0)
                                        - Math.max(0,
                                                Math.ceil(income - rhodeislandJsons.getJSONObject("single")
                                                        .getJSONArray("incomeLimit").getInt(0)) / incrementAGI
                                                * incrementExemption)));

                    } else if (income <= rhodeislandJsons.getJSONObject("single").getJSONArray("incomeLimit")
                            .getInt(1)) {
                        singles = rhodeislandJsons.getJSONObject("single").getJSONArray("singles").getInt(1);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("single").getJSONArray("incrementAGI")
                                .getInt(1);
                        final double incrementDeduction = MongoDBConnection.rhodeislandJson.getIncrementDeduction();
                        standardDeduction = Math.max(0,
                                (singles - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("single")
                                                .getJSONArray("incomeLimit").getInt(1)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (rhodeislandJsons
                                        .getJSONObject(
                                                "single")
                                        .getJSONArray("exemptionMax").getInt(1)
                                        - Math.max(0,
                                                Math.ceil(income - rhodeislandJsons.getJSONObject("single")
                                                        .getJSONArray("incomeLimit").getInt(1)) / incrementAGI
                                                * incrementExemption)));
                        // break;
                    } else if (income <= rhodeislandJsons.getJSONObject("single").getJSONArray("incomeLimit")
                            .getInt(2)) {

                        singles = rhodeislandJsons.getJSONObject("single").getJSONArray("singles").getInt(2);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("single").getJSONArray("incrementAGI")
                                .getInt(2);
                        final double incrementDeduction = MongoDBConnection.rhodeislandJson.getIncrementDeduction();
                        standardDeduction = Math.max(0,
                                (singles - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("single")
                                                .getJSONArray("incomeLimit").getInt(2)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (rhodeislandJsons
                                        .getJSONObject(
                                                "single")
                                        .getJSONArray("exemptionMax").getInt(2)
                                        - Math.max(0,
                                                Math.ceil(income - rhodeislandJsons.getJSONObject("single")
                                                        .getJSONArray("incomeLimit").getInt(2)) / incrementAGI
                                                * incrementExemption)));
                        // break;
                    } else if (income <= rhodeislandJsons.getJSONObject("single").getJSONArray("incomeLimit")
                            .getInt(3)) {
                        singles = rhodeislandJsons.getJSONObject("single").getJSONArray("singles").getInt(3);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("single").getJSONArray("incrementAGI")
                                .getInt(3);
                        final double incrementDeduction = MongoDBConnection.rhodeislandJson.getIncrementDeduction();
                        standardDeduction = Math.max(0,
                                (singles - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("single")
                                                .getJSONArray("incomeLimit").getInt(3)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (rhodeislandJsons
                                        .getJSONObject(
                                                "single")
                                        .getJSONArray("exemptionMax").getInt(3)
                                        - Math.max(0,
                                                Math.ceil(income - rhodeislandJsons.getJSONObject("single")
                                                        .getJSONArray("incomeLimit").getInt(3)) / incrementAGI
                                                * incrementExemption)));
                        // break;
                    } else if (income <= rhodeislandJsons.getJSONObject("single").getJSONArray("incomeLimit")
                            .getInt(4)) {
                        // ////System.out.println("5-->");
                        // ////System.out.println(rhodeislandJsons.getJSONObject("single").getJSONArray("incomeLimit").getInt(4));
                        // ////System.out.println(rhodeislandJsons.getJSONObject("single").getJSONArray("singles").getInt(4));
                        singles = rhodeislandJsons.getJSONObject("single").getJSONArray("singles").getInt(4);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("single").getJSONArray("incrementAGI")
                                .getInt(4);
                        final double incrementDeduction = MongoDBConnection.rhodeislandJson.getIncrementDeduction();
                        standardDeduction = Math.max(0,
                                (singles - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("single")
                                                .getJSONArray("incomeLimit").getInt(4)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (rhodeislandJsons
                                        .getJSONObject(
                                                "single")
                                        .getJSONArray("exemptionMax").getInt(4)
                                        - Math.max(0,
                                                Math.ceil(income - rhodeislandJsons.getJSONObject("single")
                                                        .getJSONArray("incomeLimit").getInt(4)) / incrementAGI
                                                * incrementExemption)));
                        // break;
                    } else if (income > rhodeislandJsons.getJSONObject("single").getJSONArray("incomeLimit")
                            .getInt(5)) {
                        // ////System.out.println("6-->");
                        // ////System.out.println(rhodeislandJsons.getJSONObject("single").getJSONArray("incomeLimit").getInt(5));
                        // ////System.out.println(rhodeislandJsons.getJSONObject("single").getJSONArray("singles").getInt(5));
                        singles = rhodeislandJsons.getJSONObject("single").getJSONArray("singles").getInt(5);

                        final double incrementAGI = rhodeislandJsons.getJSONObject("single").getJSONArray("incrementAGI")
                                .getInt(5);
                        final double incrementDeduction = MongoDBConnection.rhodeislandJson.getIncrementDeduction();
                        standardDeduction = Math.max(0,
                                (singles - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("single")
                                                .getJSONArray("incomeLimit").getInt(5)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (rhodeislandJsons
                                        .getJSONObject(
                                                "single")
                                        .getJSONArray("exemptionMax").getInt(5)
                                        - Math.max(0,
                                                Math.ceil(income - rhodeislandJsons.getJSONObject("single")
                                                        .getJSONArray("incomeLimit").getInt(5)) / incrementAGI
                                                * incrementExemption)));
                        // break;
                    }

                    deduction = Math.max(standardDeduction, itemizedDeduction);
                    stateTxaIncome = income - deduction - exemption - otherExemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {

                    if (income <= rhodeislandJsons.getJSONObject("marriedFilingJoint").getJSONArray("incomeLimit")
                            .getInt(0)) {

                        couple = rhodeislandJsons.getJSONObject("marriedFilingJoint").getJSONArray("couple").getInt(0);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("incrementAGI").getInt(0);
                        final double incrementDeduction = rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                .getInt("incrementDeduction");

                        standardDeduction = Math.max(0,
                                (couple - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                                .getJSONArray("incomeLimit").getInt(0)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        /*
                         * exemption = Math.max(0, (3850 - Math.max(0,
                         * Math.ceil(income - 192700) / incrementAGI *
                         * incrementExemption)));
                         */

                        exemption = Math.max(0,
                                (rhodeislandJsons
                                        .getJSONObject(
                                                "marriedFilingJoint")
                                        .getJSONArray("exemptionMax").getInt(0)
                                        - Math.max(0,
                                                Math.ceil(income - rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                                        .getJSONArray("incomeLimit").getInt(0)) / incrementAGI
                                                * incrementExemption)));
                        // break;
                    } else if (income <= rhodeislandJsons.getJSONObject("marriedFilingJoint")
                            .getJSONArray("incomeLimit").getInt(1)) {

                        couple = rhodeislandJsons.getJSONObject("marriedFilingJoint").getJSONArray("couple").getInt(1);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("incrementAGI").getInt(1);
                        final double incrementDeduction = rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                .getInt("incrementDeduction");
                        standardDeduction = Math.max(0,
                                (couple - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                                .getJSONArray("incomeLimit").getInt(1)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (rhodeislandJsons
                                        .getJSONObject(
                                                "marriedFilingJoint")
                                        .getJSONArray("exemptionMax").getInt(1)
                                        - Math.max(0,
                                                Math.ceil(income - rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                                        .getJSONArray("incomeLimit").getInt(1)) / incrementAGI
                                                * incrementExemption)));
                        // break;
                    } else if (income <= rhodeislandJsons.getJSONObject("marriedFilingJoint")
                            .getJSONArray("incomeLimit").getInt(2)) {

                        couple = rhodeislandJsons.getJSONObject("marriedFilingJoint").getJSONArray("couple").getInt(2);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("incrementAGI").getInt(2);
                        final double incrementDeduction = rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                .getInt("incrementDeduction");
                        standardDeduction = Math.max(0,
                                (couple - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                                .getJSONArray("incomeLimit").getInt(2)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (rhodeislandJsons
                                        .getJSONObject(
                                                "marriedFilingJoint")
                                        .getJSONArray("exemptionMax").getInt(2)
                                        - Math.max(0,
                                                Math.ceil(income - rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                                        .getJSONArray("incomeLimit").getInt(2)) / incrementAGI
                                                * incrementExemption)));
                        // break;
                    } else if (income <= rhodeislandJsons.getJSONObject("marriedFilingJoint")
                            .getJSONArray("incomeLimit").getInt(3)) {

                        couple = rhodeislandJsons.getJSONObject("marriedFilingJoint").getJSONArray("couple").getInt(3);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("incrementAGI").getInt(3);
                        final double incrementDeduction = rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                .getInt("incrementDeduction");
                        standardDeduction = Math.max(0,
                                (couple - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                                .getJSONArray("incomeLimit").getInt(3)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (rhodeislandJsons
                                        .getJSONObject(
                                                "marriedFilingJoint")
                                        .getJSONArray("exemptionMax").getInt(3)
                                        - Math.max(0,
                                                Math.ceil(income - rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                                        .getJSONArray("incomeLimit").getInt(3)) / incrementAGI
                                                * incrementExemption)));
                        // break;
                    } else if (income <= rhodeislandJsons.getJSONObject("marriedFilingJoint")
                            .getJSONArray("incomeLimit").getInt(4)) {

                        couple = rhodeislandJsons.getJSONObject("marriedFilingJoint").getJSONArray("couple").getInt(4);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("incrementAGI").getInt(4);
                        final double incrementDeduction = rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                .getInt("incrementDeduction");
                        standardDeduction = Math.max(0,
                                (couple - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                                .getJSONArray("incomeLimit").getInt(4)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (rhodeislandJsons
                                        .getJSONObject(
                                                "marriedFilingJoint")
                                        .getJSONArray("exemptionMax").getInt(4)
                                        - Math.max(0,
                                                Math.ceil(income - rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                                        .getJSONArray("incomeLimit").getInt(4)) / incrementAGI
                                                * incrementExemption)));
                        // break;
                    } else if (income > rhodeislandJsons.getJSONObject("marriedFilingJoint").getJSONArray("incomeLimit")
                            .getInt(5)) {
                        couple = rhodeislandJsons.getJSONObject("marriedFilingJoint").getJSONArray("couple").getInt(5);

                        final double incrementAGI = rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("incrementAGI").getInt(5);
                        final double incrementDeduction = rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                .getInt("incrementDeduction");
                        standardDeduction = Math.max(0,
                                (couple - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                                .getJSONArray("incomeLimit").getInt(5)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (rhodeislandJsons
                                        .getJSONObject(
                                                "marriedFilingJoint")
                                        .getJSONArray("exemptionMax").getInt(5)
                                        - Math.max(0,
                                                Math.ceil(income - rhodeislandJsons.getJSONObject("marriedFilingJoint")
                                                        .getJSONArray("incomeLimit").getInt(5)) / incrementAGI
                                                * incrementExemption)));
                        // break;
                    }

                    deduction = Math.max(standardDeduction, itemizedDeduction);
                    stateTxaIncome = income - deduction - exemption - otherExemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax1-->>"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {

                    // for(int
                    // i=0;i<rhodeIsLandJsonheadOfHouseholdJoincomeLimit.length();i++){
                    if (income <= rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("incomeLimit")
                            .getInt(0)) {
                        Hoh = rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("hoh").getInt(0);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("incrementAGI").getInt(0);
                        final double incrementDeduction = rhodeislandJsons.getJSONObject("headOfHousehold")
                                .getDouble("incrementDeduction");

                        standardDeduction = Math.max(0,
                                (Hoh - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("headOfHousehold")
                                                .getJSONArray("incomeLimit").getInt(0)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (MongoDBConnection.rhodeislandJson.getDependentExcemption() - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("headOfHousehold")
                                                .getJSONArray("incomeLimit").getInt(0)) / incrementAGI
                                        * incrementExemption)));
                        // break;
                    } else if (income <= rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("incomeLimit")
                            .getInt(1)) {

                        Hoh = rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("hoh").getInt(1);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("incrementAGI").getInt(1);
                        final double incrementDeduction = rhodeislandJsons.getJSONObject("headOfHousehold")
                                .getDouble("incrementDeduction");
                        standardDeduction = Math.max(0,
                                (Hoh - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("headOfHousehold")
                                                .getJSONArray("incomeLimit").getInt(1)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (MongoDBConnection.rhodeislandJson.getDependentExcemption() - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("headOfHousehold")
                                                .getJSONArray("incomeLimit").getInt(1)) / incrementAGI
                                        * incrementExemption)));
                        // break;
                    } else if (income <= rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("incomeLimit")
                            .getInt(2)) {

                        Hoh = rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("hoh").getInt(2);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("incrementAGI").getInt(2);
                        final double incrementDeduction = rhodeislandJsons.getJSONObject("headOfHousehold")
                                .getDouble("incrementDeduction");
                        standardDeduction = Math.max(0,
                                (Hoh - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("headOfHousehold")
                                                .getJSONArray("incomeLimit").getInt(2)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (MongoDBConnection.rhodeislandJson.getDependentExcemption() - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("headOfHousehold")
                                                .getJSONArray("incomeLimit").getInt(2)) / incrementAGI
                                        * incrementExemption)));
                        // break;
                    } else if (income <= rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("incomeLimit")
                            .getInt(3)) {

                        Hoh = rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("hoh").getInt(3);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("incrementAGI").getInt(3);
                        final double incrementDeduction = rhodeislandJsons.getJSONObject("headOfHousehold")
                                .getDouble("incrementDeduction");
                        standardDeduction = Math.max(0,
                                (Hoh - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("headOfHousehold")
                                                .getJSONArray("incomeLimit").getInt(3)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (MongoDBConnection.rhodeislandJson.getDependentExcemption() - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("headOfHousehold")
                                                .getJSONArray("incomeLimit").getInt(3)) / incrementAGI
                                        * incrementExemption)));
                        // break;
                    } else if (income <= rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("incomeLimit")
                            .getInt(4)) {

                        Hoh = rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("hoh").getInt(4);
                        final double incrementAGI = rhodeislandJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("incrementAGI").getInt(4);
                        final double incrementDeduction = rhodeislandJsons.getJSONObject("headOfHousehold")
                                .getDouble("incrementDeduction");
                        standardDeduction = Math.max(0,
                                (Hoh - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("headOfHousehold")
                                                .getJSONArray("incomeLimit").getInt(4)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (MongoDBConnection.rhodeislandJson.getDependentExcemption() - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("headOfHousehold")
                                                .getJSONArray("incomeLimit").getInt(5)) / incrementAGI
                                        * incrementExemption)));
                        // break;
                    } else if (income > rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("incomeLimit")
                            .getInt(5)) {
                        // ////System.out.println("6-->");
                        // ////System.out.println(rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("incomeLimit").getInt(5));
                        // ////System.out.println(rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("hoh").getInt(5));
                        Hoh = rhodeislandJsons.getJSONObject("headOfHousehold").getJSONArray("hoh").getInt(5);

                        final double incrementAGI = rhodeislandJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("incrementAGI").getInt(5);
                        final double incrementDeduction = rhodeislandJsons.getJSONObject("headOfHousehold")
                                .getDouble("incrementDeduction");
                        standardDeduction = Math.max(0,
                                (Hoh - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("headOfHousehold")
                                                .getJSONArray("incomeLimit").getInt(5)) / incrementAGI
                                        * incrementDeduction)));
                        incrementExemption = 770;
                        exemption = Math.max(0,
                                (MongoDBConnection.rhodeislandJson.getDependentExcemption() - Math.max(0,
                                        Math.ceil(income - rhodeislandJsons.getJSONObject("headOfHousehold")
                                                .getJSONArray("incomeLimit").getInt(5)) / incrementAGI
                                        * incrementExemption)));
                        // break;
                    }
                    exemption=exemption+MongoDBConnection.rhodeislandJson.getDependentExcemption()*kidCount;
                    ///////System.out.println("exemption of kidss=="+exemption);
                    deduction = Math.max(standardDeduction, itemizedDeduction);
                    ///////System.out.println("deduction===="+deduction);
                    stateTxaIncome = income - deduction - exemption - otherExemption;
                    //////System.out.println("stateTxaIncome==="+stateTxaIncome+"income==="+income+"deduction==="+deduction+"otherExemption==="+otherExemption);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax===="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax==="+stateTax+"credit===="+credit);
                    return stateTax;
                }
            } else if (state.equals("CONNECTICUT")) {
                double personalTaxFactor = 0;

                if (filingStatus.equals("Single")) {

                    exemption = Math.max(0,
                            MongoDBConnection.Connecticut.getSingle().getPersonalExcemption() - Math.max(0,
                                    MongoDBConnection.Connecticut.getSingle().getCalculatedTaxAGI_Min() * Math.ceil(
                                            (income - MongoDBConnection.Connecticut.getSingle().getCalculatedTaxAGI())
                                            / MongoDBConnection.Connecticut.getCalculatedTaxAGI_Every())));
                    stateTxaIncome = income - deduction - exemption - otherExemption;

                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);

                    for (int i = 0; i < MongoDBConnection.ConnecticutJsons.getJSONObject("single")
                            .getJSONArray("personalTaxFactorCT").length(); i++) {

                        personalTaxFactor = personalTaxFactor + Math.max(0,
                                Math.min(
                                        MongoDBConnection.ConnecticutJsons.getJSONObject("single").getJSONArray(
                                                "personalTaxFactorCTRates").getDouble(i),
                                        Math.ceil((MongoDBConnection.ConnecticutJsons.getJSONObject("single")
                                                .getJSONArray("personalTaxFactorCT").getInt(i) - income + 1)
                                                / MongoDBConnection.Connecticut.getSingle().getCalculatedTaxAGI_Min())* MongoDBConnection.ConnecticutJsons.getJSONObject("single")
                                        .getJSONArray("personalTaxFactorCTRatesMultipleRate").getDouble(i))
                                );
                    }
                    //////System.out.println("exemption1-222->"+exemption);
                    //	////System.out.println("personalTaxFactor1-566->"+personalTaxFactor);

                    exemption = Math.max(0, 14500 - Math.max(0, 500 * Math.ceil((income - 29000) / 1000)));

                    stateTxaIncome = income - deduction - exemption - otherExemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);

                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    personalTaxFactor = Math.max(0, Math.min(0.1, Math.ceil((62500 - income + 1) / 500) * 0.01))
                            + Math.max(0, Math.min(0.05, Math.ceil((32200 - income + 1) / 500)* 0.01) )
                            + Math.max(0, Math.min(0.2, Math.ceil((25700 - income + 1) / 500) * 0.05))
                            + Math.max(0, Math.min(0.4, Math.ceil((21600 - income + 1) / 500) * 0.05));
                    //////System.out.println("exemption1-->"+exemption);
                    //	////System.out.println("personalTaxFactor1-->"+personalTaxFactor);

                    credit = personalTaxFactor * stateCalculatedTax;
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("credit11--->"+credit);
                    // ////System.out.println("stateTax11-->>"+stateTax);
                    return stateTax;

                } else if (filingStatus.equals("Married Filing Jointly")) {

                    exemption = Math.max(0,
                            MongoDBConnection.Connecticut.getMarriedFilingJoint().getPersonalExcemption() - Math.max(0,
                                    MongoDBConnection.Connecticut.getMarriedFilingJoint().getCalculatedTaxAGI_Min()
                                    * Math.ceil((income - MongoDBConnection.Connecticut.getMarriedFilingJoint()
                                            .getCalculatedTaxAGI())
                                            / MongoDBConnection.Connecticut.getCalculatedTaxAGI_Every())));

                    // exemption = Math.max(0, 24000 - Math.max(0, 1000 *
                    // Math.ceil((income - 48000) / 1000)));
                    stateTxaIncome = income - deduction - exemption - otherExemption;
                    // ////System.out.println("stateTxaIncome1--->>"+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);

                    for (int i = 0; i < MongoDBConnection.ConnecticutJsons.getJSONObject("marriedFilingJoint")
                            .getJSONArray("personalTaxFactorCT").length(); i++) {

                        personalTaxFactor = personalTaxFactor + Math.max(0, Math.min(
                                MongoDBConnection.ConnecticutJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("personalTaxFactorCTRates").getDouble(i),
                                Math.ceil((MongoDBConnection.ConnecticutJsons.getJSONObject("marriedFilingJoint")
                                        .getJSONArray("personalTaxFactorCT").getInt(i) - income + 1)
                                        / MongoDBConnection.Connecticut.getSingle().getCalculatedTaxAGI_Min())* MongoDBConnection.ConnecticutJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("personalTaxFactorCTRatesMultipleRate").getDouble(i))
                                );
                    }

                    credit = personalTaxFactor * stateCalculatedTax;
                    stateTax = stateCalculatedTax - credit;
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {


                    if(maritalStatus.equals("No")){
                        exemption = Math.max(0,
                                MongoDBConnection.Connecticut.getSingle().getPersonalExcemption() - Math.max(0,
                                        MongoDBConnection.Connecticut.getSingle().getCalculatedTaxAGI_Min() * Math.ceil(
                                                (income - MongoDBConnection.Connecticut.getSingle().getCalculatedTaxAGI())
                                                / MongoDBConnection.Connecticut.getCalculatedTaxAGI_Every())));
                        ////System.out.println("exemption==asa="+exemption);
                    }

                    stateTxaIncome = income - deduction - exemption - otherExemption;
                    //////System.out.println("exemption==="+exemption);
                    //////System.out.println("stateTxaIncome===="+stateTxaIncome);
                    //////System.out.println("income===="+income);
                    //////System.out.println("deduction===="+deduction);
                    //////System.out.println("otherExemption===="+otherExemption);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateStandardTax=connnn==="+stateStandardTax);
                    //////System.out.println("stateStandardTax-----connn"+stateStandardTax);

                    for (int i = 0; i < MongoDBConnection.ConnecticutJsons.getJSONObject("headOfHousehold")
                            .getJSONArray("personalTaxFactorCT").length(); i++) {

                        personalTaxFactor = personalTaxFactor + Math.max(0,
                                Math.min(
                                        MongoDBConnection.ConnecticutJsons.getJSONObject("headOfHousehold")
                                        .getJSONArray("personalTaxFactorCTRates")
                                        .getDouble(i),
                                        Math.ceil((MongoDBConnection.ConnecticutJsons.getJSONObject("headOfHousehold")
                                                .getJSONArray("personalTaxFactorCT").getInt(i) - income + 1)
                                                / MongoDBConnection.Connecticut.getSingle().getCalculatedTaxAGI_Min())* MongoDBConnection.ConnecticutJsons.getJSONObject("headOfHousehold")
                                        .getJSONArray("personalTaxFactorCTRatesMultipleRate").getDouble(i))
                                );

                        /*	////System.out.println("8999121==="+MongoDBConnection.ConnecticutJsons.getJSONObject("headOfHousehold")
						.getJSONArray("personalTaxFactorCTRates")
						.getDouble(i));
						////System.out.println("hhellll"+MongoDBConnection.ConnecticutJsons.getJSONObject("headOfHousehold")
						.getJSONArray("personalTaxFactorCT").getInt(i));
						////System.out.println("fineee"+MongoDBConnection.ConnecticutJsons.getJSONObject("headOfHousehold")
						.getJSONArray("personalTaxFactorCTRatesMultipleRate").getDouble(i));*/
                    }


                    //////System.out.println("personalTaxFactor==asasas=="+personalTaxFactor);




                    /*
					personalTaxFactor = Math.max(0, Math.min(0.1, Math.ceil((78500 - income + 1) / 500) * 0.01))
							+ Math.max(0, Math.min(0.05, Math.ceil((46000 - income + 1) / 500)* 0.01) )
							+ Math.max(0, Math.min(0.2, Math.ceil((35500 - income + 1) / 500) * 0.05))
							+ Math.max(0, Math.min(0.4, Math.ceil((27500 - income + 1) / 500)* 0.05) );
				 ////System.out.println("personalTaxFactor====aaprrr"+personalTaxFactor);*/

                    credit = personalTaxFactor * stateCalculatedTax;
                    //////System.out.println("credit====connn "+credit);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("connicuit st  "+stateTax);
                    return stateTax;
                }
            }

            else if(state.equals("UTAH")){
                //////System.out.println("inside   UTAH");
                final JSONObject utah = new JSONObject(
                        jsonMapper.writeValueAsString(MongoDBConnection.utah));




                if (filingStatus.equals("Single")) {
                    personalExcemption = utah.getJSONObject("single").getDouble("personalExcemption");

                    personalExcemption =personalExcemption*1;
                    //credit = ((9250+3000*2)*0.06-(stateAgi-3000*2-20385)*0.013);
                    standerdDeduction = utah.getJSONObject("single").getDouble("standarddeduction");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction-personalExcemption;
                    //////System.out.println("stateTxaIncome00-->>"+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    // ////System.out.println("stateStandardTax00-->>"+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    // ////System.out.println("stateCalculatedTax00--->>"+stateCalculatedTax);
                    //////System.out.println("credit00-->"+credit);
                    //credit= Math.max(0,((stateAgi - 6300 - personalExcemption) * 0.06 - (stateTxaIncome - 20385) * 0.013));
                    //////System.out.println("credit s1 "+credit);

                    final double federalDeduction=MongoDBConnection.constJson.getJSONObject("single").getDouble("standardDeduction");
                    final double rates1=utah.getJSONObject("single").getJSONArray("creditLimit").getDouble(0);
                    final double rates2=utah.getJSONObject("single").getJSONArray("creditLimit").getDouble(1);


                    final double  phase_out_limit=utah.getJSONObject("single").getDouble("phase_out_limit");

                    /*////System.out.println("rates 1"+rates1+"rates 2 "+rates2);
					////System.out.println(" phase_out_limit   "+ phase_out_limit+"stateTxaIncome==2"
					+stateTxaIncome+"federalDeduction---2"+federalDeduction+"stateAgi===3"+stateAgi);*/

                    credit=Math.max(0,(((stateAgi-federalDeduction-personalExcemption)*rates1)-((stateTxaIncome-phase_out_limit)*rates2)));
                    //////System.out.println("credit==single2--"+credit);

                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax00--->>"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    personalExcemption = utah.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    personalExcemption = personalExcemption + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    //credit = ((12600+3000*4)*0.06-(stateAgi-27180)*0.013);
                    //////System.out.println("kidCount==qq="+kidCount);
                    standerdDeduction = utah.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction-personalExcemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    // ////System.out.println("stateCalculatedTax00--->"+stateCalculatedTax);
                    //////System.out.println("credit00--->>"+credit);
                    //credit= Math.max(0,((stateAgi - 6300 - personalExcemption) * 0.06 - (stateTxaIncome - 27180) * 0.013));
                    // ////System.out.println("credit married 1--"+credit);

                    final double federalDeduction=MongoDBConnection.constJson.getJSONObject("marriedFilingJoint").getDouble("standardDeduction");
                    final double rates1=utah.getJSONObject("marriedFilingJoint").getJSONArray("creditLimit").getDouble(0);
                    final double rates2=utah.getJSONObject("marriedFilingJoint").getJSONArray("creditLimit").getDouble(1);
                    final double  phase_out_limit=utah.getJSONObject("marriedFilingJoint").getDouble("phase_out_limit");

                    /*	////System.out.println("rates 1"+rates1+"rates 2 "+rates2);
						////System.out.println(" phase_out_limit   "+ phase_out_limit+"stateTxaIncome==2"
						+stateTxaIncome+"federalDeduction---2"+federalDeduction+"stateAgi===3"+stateAgi);*/

                    credit=Math.max(0,(((stateAgi-federalDeduction-personalExcemption)*rates1)-((stateTxaIncome-phase_out_limit)*rates2)));
                    //////System.out.println("credit==married2--"+credit);


                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax00--->>"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {


                    if (maritalStatus.equals("No")){
                        personalExcemption = utah.getJSONObject("single").getDouble("personalExcemption");
                        //TaxCredit = personalExcemption * 1;
                        //////System.out.println("personalExcemption==aa11="+personalExcemption);
                        personalExcemption = personalExcemption +(kidCount * (incomeJson.getDouble("dependentExcemption")));
                        //////System.out.println("TaxCredit===="+TaxCredit+"kidCount====="+kidCount+"dependent------"+(incomeJson.getDouble("dependentExcemption")));


                    }else{
                        personalExcemption = utah.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                        personalExcemption = personalExcemption + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    }
                    personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                    TaxCredit = personalExcemption + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    //credit = ((9250+3000*2)*0.06-(stateAgi-3000*2-20385)*0.013);
                    //////System.out.println("credit===="+credit+"TaxCredit==="+TaxCredit);
                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    //////System.out.println("deduction====="+deduction);
                    stateTxaIncome = stateAgi - deduction-personalExcemption;
                    //////System.out.println("stateTxaIncome===="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax===="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax==="+stateCalculatedTax);

                    //credit= Math.max(0,((stateAgi - 6300 - personalExcemption) * 0.06 - (stateTxaIncome - 20385) * 0.013));
                    //////System.out.println("credit==HOH1-"+credit     );


                    final double federalDeduction=MongoDBConnection.constJson.getJSONObject("headOfHousehold").getDouble("standardDeduction");
                    final double rates1=utah.getJSONObject("headOfHousehold").getJSONArray("creditLimit").getDouble(0);
                    final double rates2=utah.getJSONObject("headOfHousehold").getJSONArray("creditLimit").getDouble(1);
                    final double  phase_out_limit=utah.getJSONObject("headOfHousehold").getDouble("phase_out_limit");

                    /*////System.out.println("rates 1"+rates1+"rates 2 "+rates2);
					////System.out.println(" phase_out_limit   "+ phase_out_limit+"stateTxaIncome==2"
					+stateTxaIncome+"federalDeduction---2"+federalDeduction+"stateAgi===3"+stateAgi);*/

                    credit=Math.max(0,(((stateAgi-federalDeduction-personalExcemption)*rates1)-((stateTxaIncome-phase_out_limit)*rates2)));
                    //////System.out.println("credit==HOH2--"+credit);
                    stateTax = stateCalculatedTax - credit;
                    /*////System.out.println("stateTax====="+stateTax);
					////System.out.println("stateTax00--->>"+stateTax);*/
                    return stateTax;
                }





            } else if(state.equals("MICHIGAN")){
                //////System.out.println("inside MICHIGAN");
                final JSONObject michigan= new JSONObject(MongoDBConnection.michigan);





                // ////System.out.println("other state-->>>");
                if (filingStatus.equals("Single")) {
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    personalExcemption = personalExcemption*noOfExcemtion;

                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;

                    // ////System.out.println("stateTax--.."+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    // ////System.out.println("
                    // standerdDeduction======"+standerdDeduction);
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    personalExcemption = personalExcemption*noOfExcemtion;
                    // ////System.out.println("personalExcemption===="+personalExcemption);
                    stateTxaIncome = stateAgi - standerdDeduction - personalExcemption - otherExcemption;
                    // ////System.out.println("stateTxaIncome===="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    // ////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    // ////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax====="+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {

                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");



                    if (maritalStatus.equals("No")){
                        personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");

                        //////System.out.println("personalExcemption   -----  "+personalExcemption);
                        personalExcemption = personalExcemption*noOfExcemtion;
                        //////System.out.println("personalExcemption===123="+personalExcemption+"kidCount====="+kidCount+"dependtt"+(incomeJson.getDouble("dependentExcemption")));
                    }else{
                        personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                        personalExcemption = personalExcemption*noOfExcemtion;
                    }


                    //personalExcemption = personalExcemption
                    //	+ (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    stateTxaIncome = stateAgi - standerdDeduction - personalExcemption - otherExcemption;
                    /*////System.out.println("stateTxaIncome"+stateTxaIncome+"stateAgi===="+stateAgi+
							"standerdDeduction===="+standerdDeduction+"personalExcemption==="
							+personalExcemption+"otherExcemption"+otherExcemption);*/
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax===="+stateTax);

                    return stateTax;
                }








            }else if(state.equals("MASSACHUSETTS")){
                double rentalDeduction=0;

                final User user = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
                //////System.out.println("aparna  user====="+user);

                //////System.out.println("vijay ::"+user.getHouseStatus());
                //	////System.out.println("inside MICHIGAN");
                incomeJson= new JSONObject(MongoDBConnection.massachusetts);





                // ////System.out.println("other state-->>>")   houseStatus    userIncome, spouseIncome;
                if (filingStatus.equals("Single")) {
                    final double userSST=userIncome*((6.2 + 1.45)/100);
                    final double deductAmount=Math.min(userSST,2000);


                    if(houseStatus!=null && houseStatus.equals("Rent"))
                    {
                        //////System.out.println("inside rent====rytyt   ="+user.getRentalExpenses());
                        rentalDeduction = Math.min (3000, 0.5 * (user.getRentalExpenses()*12));
                        //////System.out.println("rentalDeduction 000===========  "+rentalDeduction);
                    }
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    exemption = personalExcemption + (kidCount * dependentExcemption);

                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption-rentalDeduction-deductAmount;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;

                    //////System.out.println("stateTax--.."+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    final double userSST=userIncome*((6.2 + 1.45)/100);
                    final double spouseSST=spouseIncome*((6.2 + 1.45)/100);
                    final double deductAmount=Math.min(userSST,2000)+Math.min(spouseSST,2000);

                    if(houseStatus!=null && houseStatus.equals("Rent"))
                    {
                        rentalDeduction = Math.min (3000, 0.5 * (user.getRentalExpenses()*12));
                    }
                    // ////System.out.println("
                    // standerdDeduction======"+standerdDeduction);
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    exemption = personalExcemption + (kidCount * dependentExcemption);
                    // ////System.out.println("personalExcemption===="+personalExcemption);
                    stateTxaIncome = stateAgi - standerdDeduction - personalExcemption - otherExcemption-rentalDeduction-deductAmount;
                    // ////System.out.println("stateTxaIncome===="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    // ////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    // ////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax====="+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {

                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");

                    final double userSST=userIncome*((6.2 + 1.45)/100);
                    final double deductAmount=Math.min(userSST,2000);
                    //////System.out.println("deductAmount==aaa==="+deductAmount);
                    if(houseStatus!=null && houseStatus.equals("Rent"))
                    {
                        //////System.out.println("inside rent====rytyt   ="+user.getRentalExpenses());
                        rentalDeduction = Math.min (3000, 0.5 * (user.getRentalExpenses()*12));
                        //////System.out.println("rentalDeduction 000===========  "+rentalDeduction);
                    }

                    if (maritalStatus.equals("No")){
                        personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");

                        //////System.out.println("personalExcemption   -----  "+personalExcemption);
                        //personalExcemption = personalExcemption*noOfExcemtion;
                        dependentExcemption = incomeJson.getDouble("dependentExcemption");
                        exemption = personalExcemption + (kidCount * dependentExcemption);
                        //////System.out.println("personalExcemption===123="+personalExcemption+"kidCount====="+kidCount+"dependtt"+(incomeJson.getDouble("dependentExcemption")));
                    }else{
                        personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                        dependentExcemption = incomeJson.getDouble("dependentExcemption");
                        exemption = personalExcemption + (kidCount * dependentExcemption);
                    }
                    //////System.out.println("exemptionppppp"+exemption);

                    //personalExcemption = personalExcemption
                    //	+ (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    stateTxaIncome = stateAgi - standerdDeduction - exemption - otherExcemption-rentalDeduction-deductAmount;
                    /*////System.out.println("stateTxaIncome"+stateTxaIncome+"stateAgi===="+stateAgi+
							"standerdDeduction===="+standerdDeduction+"exemption==="+exemption+
							"otherExcemption"+otherExcemption);*/
                    //////System.out.println("rentalDeduction==="+rentalDeduction+"deductAmount===="+deductAmount);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //	////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax===="+stateTax);

                    return stateTax;
                }

            }


            else {
                // ////System.out.println("other state-->>>");
                if (filingStatus.equals("Single")) {
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    personalExcemption = personalExcemption
                            + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;

                    // ////System.out.println("stateTax--.."+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    // ////System.out.println("
                    // standerdDeduction======"+standerdDeduction);
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    personalExcemption = personalExcemption
                            + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    // ////System.out.println("personalExcemption===="+personalExcemption);
                    stateTxaIncome = stateAgi - standerdDeduction - personalExcemption - otherExcemption;
                    // ////System.out.println("stateTxaIncome===="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    // ////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    // ////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax====="+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {

                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");



                    if (maritalStatus.equals("No")){
                        personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");

                        //	////System.out.println("personalExcemption   -----  "+personalExcemption);
                        personalExcemption = personalExcemption
                                + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                        // ////System.out.println("personalExcemption===123="+personalExcemption+"kidCount====="+kidCount+"dependtt"+(incomeJson.getDouble("dependentExcemption")));
                    }else{
                        personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                        personalExcemption = personalExcemption
                                + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    }


                    //personalExcemption = personalExcemption
                    //	+ (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    stateTxaIncome = stateAgi - standerdDeduction - personalExcemption - otherExcemption;
                    //////System.out.println("stateTxaIncome"+stateTxaIncome+"stateAgi===="+stateAgi+
                    //"standerdDeduction===="+standerdDeduction+"personalExcemption==="+personalExcemption+"otherExcemption"+otherExcemption);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //	////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax===="+stateTax);

                    return stateTax;
                }
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return stateTax;

    }

    private static double newStateStandardTax(double stateTxaIncome, JSONObject incomeJson, String filingStatus) {
        double stateStandardTax = 0;
        try {
            JSONArray jsonRatesAndbracketsArray = new JSONArray();
            if (filingStatus.equals("Married Filing Jointly")) {
                jsonRatesAndbracketsArray = incomeJson.getJSONObject("marriedFilingJoint")
                        .getJSONArray("ratesAndBrackets");
            } else if (filingStatus.equals("Head of Household")) {
                jsonRatesAndbracketsArray = incomeJson.getJSONObject("headOfHousehold")
                        .getJSONArray("ratesAndBrackets");
            } else if (filingStatus.equals("Single")) {
                jsonRatesAndbracketsArray = incomeJson.getJSONObject("single").getJSONArray("ratesAndBrackets");
            }
            for (int i = 0; i < jsonRatesAndbracketsArray.length(); i++) {
                if (jsonRatesAndbracketsArray.getJSONObject(i).getLong("brackets") < stateTxaIncome
                        && i + 1 != jsonRatesAndbracketsArray.length() && jsonRatesAndbracketsArray
                        .getJSONObject(i + 1).getLong("brackets") > stateTxaIncome) {
                    stateStandardTax = stateStandardTax + ((stateTxaIncome
                            - jsonRatesAndbracketsArray.getJSONObject(i).getLong("brackets"))
                            * (jsonRatesAndbracketsArray.getJSONObject(i).getDouble("rates") - 0.001));
                    break;
                } else if (jsonRatesAndbracketsArray.getJSONObject(i).getLong("brackets") < stateTxaIncome
                        && i + 1 == jsonRatesAndbracketsArray.length()) {
                    stateStandardTax = stateStandardTax + ((stateTxaIncome
                            - jsonRatesAndbracketsArray.getJSONObject(i).getLong("brackets"))
                            * (jsonRatesAndbracketsArray.getJSONObject(i).getDouble("rates") - 0.001));
                    break;
                } else {
                    stateStandardTax = stateStandardTax
                            + ((jsonRatesAndbracketsArray.getJSONObject(i + 1).getLong("brackets")
                                    - (double) jsonRatesAndbracketsArray.getJSONObject(i).getLong("brackets"))
                                    * (jsonRatesAndbracketsArray.getJSONObject(i).getDouble("rates") - 0.001));
                }
            }
            return stateStandardTax;
        } catch (final JSONException e) {
            e.printStackTrace();
        }
        return stateStandardTax;
    }

    private static double stateStandardTaxValue(double stateTxaIncome, JSONObject incomeJson, String filingStatus) {
        double stateStandardTax = 0;
        try {
            JSONArray jsonRatesAndbracketsArray = new JSONArray();
            if (filingStatus.equals("Married Filing Jointly")) {
                jsonRatesAndbracketsArray = incomeJson.getJSONObject("marriedFilingJoint")
                        .getJSONArray("ratesAndBrackets");
            } else if (filingStatus.equals("Head of Household")) {
                jsonRatesAndbracketsArray = incomeJson.getJSONObject("headOfHousehold")
                        .getJSONArray("ratesAndBrackets");
            } else if (filingStatus.equals("Single")) {
                jsonRatesAndbracketsArray = incomeJson.getJSONObject("single").getJSONArray("ratesAndBrackets");
            }
            for (int i = 0; i < jsonRatesAndbracketsArray.length(); i++) {
                if (jsonRatesAndbracketsArray.getJSONObject(i).getLong("brackets") <= stateTxaIncome
                        && i + 1 != jsonRatesAndbracketsArray.length() && jsonRatesAndbracketsArray
                        .getJSONObject(i + 1).getLong("brackets") > stateTxaIncome) {
                    stateStandardTax = stateStandardTax + ((stateTxaIncome
                            - jsonRatesAndbracketsArray.getJSONObject(i).getLong("brackets"))
                            * jsonRatesAndbracketsArray.getJSONObject(i).getDouble("rates"));
                    break;
                } else if (jsonRatesAndbracketsArray.getJSONObject(i).getLong("brackets") <= stateTxaIncome
                        && i + 1 == jsonRatesAndbracketsArray.length()) {
                    stateStandardTax = stateStandardTax + ((stateTxaIncome
                            - jsonRatesAndbracketsArray.getJSONObject(i).getLong("brackets"))
                            * jsonRatesAndbracketsArray.getJSONObject(i).getDouble("rates"));
                    break;
                } else {
                    if (i + 1 != jsonRatesAndbracketsArray.length()) {
                        stateStandardTax = stateStandardTax
                                + ((jsonRatesAndbracketsArray.getJSONObject(i + 1).getLong("brackets")
                                        - (double) jsonRatesAndbracketsArray.getJSONObject(i).getLong("brackets"))
                                        * jsonRatesAndbracketsArray.getJSONObject(i).getDouble("rates"));
                    }
                }
                // ////System.out.println("Mahi v stateStandardTax
                // "+stateStandardTax+" i="+i);
            }
            if (stateTxaIncome <= 0) {
                stateStandardTax = 0;
                return stateStandardTax;
            }
            return stateStandardTax;
        } catch (final JSONException e) {

            e.printStackTrace();
        }
        return stateStandardTax;

    }

    private static JSONObject itemizedDeductionForFedralTaxDuringRegistration(String user_id, String fin_id,
            double stateTax, int year, String actionType, String houseStatus, int registrationYear, double houseValue,
            JSONArray housing_expense, long startYear, double remainingMortgageOriginal,
            double remainingMortgageInterestRate,JSONArray equityArray,double houseAppreciationRate,String registrationHouseStatus) {
        final JSONObject result=new JSONObject();
        double itemizedDeduction = 0;
        try {
            //////System.out.println("equityArray..."+equityArray);
            // System.out.println("year...."+year +"houseStatus "+houseStatus +" "+actionType);
            final double mortgageIntrest = 0;
            double pmi = 0;
            double stateIncomeTax = stateTax;
            final double propertyTax = 0.01;
            double propertyTaxValue = 0;
            double remainingMortgage = 0;
            double remainingYearsMortgage = 0;
            double morgagePaymentPerMonth = 0;
            double annualPayment = 0;
            double morgageAtYearEnd = 0;
            double priciplePaid = 0;
            double intrestPaid = 0;
            double remainingMortgageInterestRateYear = 0;
            final double n = 12;
            double housing_expense_value;
            double propertyTaxValueHouse = 0;
            double remainingMortgageHouse = 0;
            double remainingYearsMortgageHouse = 0;
            double morgagePaymentPerMonthHouse = 0;
            double annualPaymentHouse = 0;
            double morgageAtYearEndHouse = 0;
            double priciplePaidHouse = 0;
            double intrestPaidHouse = 0;
            double remainingMortgageInterestRateYearHouse = 0;
            double housePropertyTax=0;
            if (houseStatus!=null && houseStatus.equals("Rent")) {
                itemizedDeduction = mortgageIntrest + pmi + stateIncomeTax + propertyTax;
                //////System.out.println("itemizedDeduction Rent house "+itemizedDeduction+" intrestPaidHouse "+intrestPaidHouse+"pmi "+pmi+"morgageAtYearEndHouse "+morgageAtYearEndHouse+"remainingMortgageHouse "+remainingMortgageHouse+"morgagePaymentPerMonthHouse "+morgagePaymentPerMonthHouse);

            } else {
                //////System.out.println("housing_expense.."+housing_expense+" "+housing_expense.length()+"registrationYear>>"+registrationYear+"houseStatus.."+houseStatus);
                long endYear = startYear + registrationYear;
                final long  startTemp=startYear;
                if(housing_expense!=null){
                    if(housing_expense.length()!=0)
                    {
                        for(int i=0; i<housing_expense.length();i++)
                        {
                            if(housing_expense.getJSONObject(i).getInt("startYear")>=registrationYear && housing_expense.getJSONObject(i).getInt("startYear")<(registrationYear+startYear) && (housing_expense.getJSONObject(i). getString("frequency").equals("Replace first house") ||housing_expense.getJSONObject(i). getString("frequency").equals("Turn first house into a rental")||housing_expense.getJSONObject(i). getString("frequency").equals("Turn second house into a rental"))&& housing_expense.getJSONObject(i).getString("originalHouseStatus").equals("Own"))				{
                                endYear=housing_expense.getJSONObject(i).getInt("startYear");
                                startYear=endYear-registrationYear;
                            }
                        }
                    }
                }
                //////System.out.println("own house start year.."+registrationYear+" endYear"+endYear );
                //////System.out.println("year"+year+"newYear"+newYear);
                if (year >= registrationYear && year < endYear) {
                    remainingMortgage = remainingMortgageOriginal;
                    if ((endYear - year) < startYear) {
                        if ((year - 1) == newYear) {
                            remainingMortgage = amountMorgage;
                            remainingMortgageTmp = remainingMortgage;
                        } else {
                            remainingMortgage = remainingMortgageTmp;
                        }
                    }
                    //////System.out.println("remainingMortgage >>>>"+remainingMortgage);
                    remainingYearsMortgage = startTemp * 12;
                    remainingMortgageInterestRate = remainingMortgageInterestRate / n;
                    remainingMortgageInterestRateYear = remainingMortgageInterestRate;
                    propertyTaxValue = propertyTax * (houseValue);
                    morgagePaymentPerMonth = Math.pow((1 + (remainingMortgageInterestRate / 100)),
                            remainingYearsMortgage) * (remainingMortgageInterestRate / 100)
                            / ((Math.pow((1 + (remainingMortgageInterestRate / 100)), remainingYearsMortgage) - 1))
                            * remainingMortgageOriginal;
                    if (Double.isNaN(morgagePaymentPerMonth)) {
                        morgagePaymentPerMonth = 0;
                    }
                    annualPayment = morgagePaymentPerMonth * 12;
                    stateIncomeTax = stateTax;
                    //////System.out.println("ranji : : :remainingMortgageOriginal ??"+remainingMortgageOriginal +"remainingMortgage" +remainingMortgageInterestRateYear +" monthlypayment"+morgagePaymentPerMonth+"annualpayment"+annualPayment);

                    if (year != newYear) {
                        morgageAtYearEnd = ((remainingMortgage
                                * (Math.pow((1 + (remainingMortgageInterestRateYear / 100)), n)))
                                - ((((Math.pow((1 + (remainingMortgageInterestRateYear / 100)), n) - 1)
                                        * morgagePaymentPerMonth)) / (remainingMortgageInterestRateYear / 100)));
                        if (morgageAtYearEnd < 0) {
                            morgageAtYearEnd = 0;
                        }
                        amountMorgage = morgageAtYearEnd;
                        newYear = year;
                    } else {
                        morgageAtYearEnd = amountMorgage;
                    }
                    //////System.out.println(">< !! ^^^ year"+year+"morgageAtYearEnd"+morgageAtYearEnd +"amountMorgage "+amountMorgage+"remainingMortgageTmp "+remainingMortgageTmp);
                    priciplePaid = remainingMortgage - morgageAtYearEnd;
                    intrestPaid = annualPayment - priciplePaid;
                    //////System.out.println("priciplePaid "+priciplePaid+"intrestPaid "+intrestPaid);
                    if(equityArray!=null)
                    {
                        // final double equityValue=houseValue-morgageAtYearEnd;
                        // final double equityValue =(houseValue * Math.pow(1 + houseAppreciationRate / 100,startYear))-remainingMortgage;
                        //   System.out.println("check year comparision "+"ownTempYear "+ownTempYear +" year "+year +" "+ newYear);
                        for(int k=0;k<equityArray.length();k++)
                        {
                            if(year==equityArray.getJSONObject(k).getInt("year")&& year!=ownTempYear){
                                final double equityValue =(houseValue * Math.pow(1 + houseAppreciationRate / 100,k))-remainingMortgage;
                                double ownHousePropertyTax=(houseValue * Math.pow(1 +houseAppreciationRate / 100,k));
                                ownHousePropertyTax=ownHousePropertyTax*0.01;
                                equityArray.getJSONObject(k).put("property",ownHousePropertyTax);
                                equityArray.getJSONObject(k).put("value", equityArray.getJSONObject(k).getDouble("value")+equityValue);
                                //System.out.println("year present.."+equityArray.getJSONObject(k).getInt("year")+" startYear "+startYear+" "+k+"ownTempYear "+ownTempYear +" year "+year);

                                ownTempYear=year;
                            }
                        }
                    }
                    //////System.out.println("year < < < "+year+" ownTempYear << "+ownTempYear);
                    //////System.out.println(" Own house equityArray "+equityArray);
                }
                if(equityArray!=null && registrationHouseStatus.equals("Own")){
                    for(int k=0;k<equityArray.length();k++)
                    {
                        // System.out.println("year tttt "+equityArray.getJSONObject(k).getInt("year") +" "+endYear+" "+tempYear +" "+equityArray.getJSONObject(equityArray.length()-1).getInt("year"));
                        //  System.out.println("year present.."+equityArray.getJSONObject(k).getInt("year")+" startYear "+startYear +"ownTempYear "+ownTempYear +" year "+year);
                        if(equityArray.getJSONObject(k).getInt("year")>=endYear && year!=ownTempYear && equityArray.getJSONObject(k).getInt("year")<=equityArray.getJSONObject(equityArray.length()-1).getInt("year"))
                        {
                            //   System.out.println("inside years . . ."+k);
                            final double equityAfterEndYear=(houseValue * Math.pow(1 + houseAppreciationRate / 100,k));
                            double ownHousePropertyTax=(houseValue * Math.pow(1 +houseAppreciationRate / 100,k));
                            ownHousePropertyTax=ownHousePropertyTax*0.01;
                            equityArray.getJSONObject(k).put("property",ownHousePropertyTax);
                            equityArray.getJSONObject(k).put("value",equityAfterEndYear);

                        }
                    }
                }
                itemizedDeduction = intrestPaid + pmi + stateIncomeTax + propertyTaxValue;

                //////System.out.println(" << before house goal itemizedDeduction.."+itemizedDeduction+"stateIncomeTax.."+stateIncomeTax+"morgageAtYearEnd.."+morgageAtYearEnd+"year "+year+"newnewYear.."+newYear+"morgagePaymentPerMonth.."+morgagePaymentPerMonth+"remainingMortgageInterestRateYear.."+remainingMortgageInterestRateYear+"remainingYearsMortgage.."+remainingYearsMortgage);
                if(housing_expense!=null){
                    if(housing_expense.length()!=0 )
                    {
                        //////System.out.println("housing_expense in itemized "+housing_expense);
                        final int tempStartYear[]=new int[housing_expense.length()];
                        final int tempEndYear[]=new int[housing_expense.length()];
                        int StartYearTemp=0;
                        int endYearTemp=0;
                        for(int k =0; k<housing_expense.length();k++)
                        {
                            tempStartYear[k]=housing_expense.getJSONObject(k).getInt("startYear");
                            tempEndYear[k]=housing_expense.getJSONObject(k).getInt("endYear");
                        }
                        Arrays.sort(tempStartYear);
                        Arrays.sort(tempEndYear);
                        //////System.out.println("tempStartYear.."+tempStartYear+"tempEndYear "+tempEndYear);
                        for(int j=0; j<tempStartYear.length;j++)
                        {
                            if(j==(tempStartYear.length-1))
                            {
                                StartYearTemp=tempStartYear[j];
                            }
                            else if(year>=tempStartYear[j] && year<tempStartYear[j+1])
                            {
                                StartYearTemp=tempStartYear[j];
                                break;
                            }
                        }

                        endYearTemp=tempEndYear[tempEndYear.length-1];

                        // //System.out.println("StartYearTemp "+StartYearTemp+"endYearTemp "+endYearTemp );
                        for(int i =0; i<housing_expense.length();i++)
                        {
                            if(housing_expense.getJSONObject(i).getInt("startYear")==StartYearTemp)
                            {
                                //////System.out.println("Ranjitha itmezed "+"year value"+year+"Arun :: housing_expense "+housing_expense);
                                housing_expense_value = housing_expense.getJSONObject(i).getLong("property_value");
                                startYear = housing_expense.getJSONObject(i).getInt("startYear");
                                endYear = housing_expense.getJSONObject(i).getInt("endYear");
                                // housePropertyTax=housing_expense.getJSONObject(i).getLong("property_tax");
                                //	////System.out.println("Itemized startYear"+startYear+"year.."+year+"endYear "+endYear);
                                if (year >= startYear && year < endYear) {
                                    remainingMortgageHouse = housing_expense.getJSONObject(i)
                                            .getDouble("property_value")
                                            - housing_expense.getJSONObject(i).getLong("downPayment");
                                    if ((endYear - year) < (endYear - startYear)) {
                                        if ((year - 1) == newYearHouse) {
                                            remainingMortgageHouse = amountMorgageHouse;
                                            remainingMortgageHouseTmp = remainingMortgageHouse;
                                        } else {
                                            remainingMortgageHouse = remainingMortgageHouseTmp;
                                        }
                                    }
                                    remainingYearsMortgageHouse = endYear - startYear;
                                    remainingYearsMortgageHouse = remainingYearsMortgageHouse * 12;
                                    remainingMortgageInterestRate = housing_expense.getJSONObject(i)
                                            .getDouble("interest");
                                    pmi = housing_expense.getJSONObject(i).getDouble("pmi_expense");
                                    remainingMortgageInterestRate = remainingMortgageInterestRate / n;
                                    remainingMortgageInterestRateYearHouse = remainingMortgageInterestRate;
                                    propertyTaxValueHouse = propertyTax
                                            * (housing_expense.getJSONObject(i).getLong("property_value"));
                                    pmi = (housing_expense_value * pmi) / 100;
                                    //////System.out.println("remainingMortgageInterestRate "+remainingMortgageInterestRate);
                                    morgagePaymentPerMonthHouse = Math.pow((1 + (remainingMortgageInterestRate / 100)),
                                            remainingYearsMortgageHouse)
                                            * (remainingMortgageInterestRate / 100)
                                            / ((Math.pow((1 + (remainingMortgageInterestRate / 100)), remainingYearsMortgageHouse)
                                                    - 1))
                                            * (housing_expense.getJSONObject(i).getLong("property_value")
                                                    - housing_expense.getJSONObject(i).getLong("downPayment"));
                                    if (Double.isNaN(morgagePaymentPerMonthHouse)) {
                                        morgagePaymentPerMonthHouse = 0;
                                    }
                                    //	////System.out.println("morgagePaymentPerMonthHouse..."+morgagePaymentPerMonthHouse+"remainingYearsMortgageHouse.."+remainingYearsMortgageHouse);
                                    annualPaymentHouse = morgagePaymentPerMonthHouse * 12;
                                    stateIncomeTax = stateTax;
                                    if (year != newYearHouse) {
                                        morgageAtYearEndHouse = ((remainingMortgageHouse
                                                * (Math.pow((1 + (remainingMortgageInterestRateYearHouse / 100)), n)))
                                                - ((((Math.pow((1 + (remainingMortgageInterestRateYearHouse / 100)), n) - 1)
                                                        * morgagePaymentPerMonthHouse))
                                                        / (remainingMortgageInterestRateYearHouse / 100)));
                                        if (morgageAtYearEndHouse < 0) {
                                            morgageAtYearEndHouse = 0;
                                        }
                                        amountMorgageHouse = morgageAtYearEndHouse;
                                        newYearHouse = year;
                                    } else {
                                        morgageAtYearEndHouse = amountMorgageHouse;
                                    }
                                    priciplePaidHouse = remainingMortgageHouse - morgageAtYearEndHouse;
                                    final double priciplePaid_till_now = housing_expense_value - morgageAtYearEndHouse;
                                    final double equity = priciplePaid_till_now / housing_expense_value * 100;
                                    // final double equityNew =(housing_expense_value * Math.pow(1 + housing_expense.getJSONObject(i).getDouble("appreciationRate") / 100, housing_expense.getJSONObject(i).getInt("loanPreriod")))-remainingMortgageHouse;
                                    // System.out.println("equityNew .."+equityNew +" "+remainingMortgageHouse);
                                    if (pmi > 0 && equity > 20) {
                                        pmi = 0;
                                    }
                                    intrestPaidHouse = annualPaymentHouse - priciplePaidHouse;
                                    if(equityArray!=null )
                                    {
                                        for(int k=0;k<equityArray.length();k++)
                                        {
                                            if(year==equityArray.getJSONObject(k).getInt("year")&& year!=tempYear)
                                            {
                                                final double equityNew =(housing_expense_value * Math.pow(1 + housing_expense.getJSONObject(i).getDouble("appreciationRate") / 100, (year-startYear)))-remainingMortgageHouse;
                                                housePropertyTax=(housing_expense_value * Math.pow(1 + housing_expense.getJSONObject(i).getDouble("appreciationRate") / 100,k));
                                                housePropertyTax=housePropertyTax*0.01;
                                                if(housing_expense.getJSONObject(i).getString("frequency").equals("Second house for personal use"))
                                                {
                                                    /*                                                    equitySum=equitySum+priciplePaid_till_now;
                                                    propertTaxSum=propertTaxSum+housePropertyTax;
                                                    //////System.out.println("equitySum... >> "+equitySum);
                                                     */
                                                    equityArray.getJSONObject(k).put("value",   equityArray.getJSONObject(k).getDouble("value")+equityNew);
                                                    equityArray.getJSONObject(k).put("property",equityArray.getJSONObject(k).getDouble("property")+housePropertyTax);
                                                    // System.out.println("equityNew"+equityNew +"val from db "+ equityArray.getJSONObject(k).getDouble("value"));
                                                    // System.out.println("housePropertyTax "+housePropertyTax+"val from db "+equityArray.getJSONObject(k).getDouble("property"));
                                                    // System.out.println("equityArray in if "+equityArray);

                                                }
                                                /*          equityArray.getJSONObject(k).put("value", equitySum+equityArray.getJSONObject(k).getDouble("value")+equityNew);
                                                equityArray.getJSONObject(k).put("property",housePropertyTax);*/
                                                else{
                                                    equityArray.getJSONObject(k).put("value", equityNew);
                                                    equityArray.getJSONObject(k).put("property",housePropertyTax);
                                                    //  System.out.println("equityArray in else "+equityArray);
                                                }
                                                //////System.out.println("year >>>  "+year+" priciplePaid_till_now "+priciplePaid_till_now);
                                                //System.out.println("year "+year+" "+ equitySum+equityArray.getJSONObject(k).getDouble("value"));
                                                tempYear=year;
                                            }
                                        }
                                    }
                                }
                                for(int k=0;k<equityArray.length();k++)
                                {
                                    if((year==equityArray.getJSONObject(k).getInt("year"))&& equityArray.getJSONObject(k).getInt("year")>=endYear && year!=tempYear && equityArray.getJSONObject(k).getInt("year")<=equityArray.getJSONObject(equityArray.length()-1).getInt("year"))
                                    {
                                        //   System.out.println("power value.. "+year+ " "+(year-startYear) +" "+equityArray.getJSONObject(k).getInt("year"));
                                        final double equityAfterGoalsTime=(housing_expense_value * Math.pow(1 + housing_expense.getJSONObject(i).getDouble("appreciationRate") / 100,(equityArray.getJSONObject(k).getInt("year")-startYear)));
                                        housePropertyTax=housing_expense_value * Math.pow(1 + housing_expense.getJSONObject(i).getDouble("appreciationRate") / 100,k);
                                        housePropertyTax=housePropertyTax*0.01;
                                        if(housing_expense.getJSONObject(i).getString("frequency").equals("Second house for personal use"))
                                        {
                                            //System.out.println(year+" present year value "+equityArray.getJSONObject(k).getInt("year"));
                                            // System.out.println("old property value.. "+equityArray.getJSONObject(k).getDouble("property")+"  "+housePropertyTax);
                                            equityArray.getJSONObject(k).put("value",   equityArray.getJSONObject(k).getDouble("value")+equityAfterGoalsTime);
                                            equityArray.getJSONObject(k).put("property",equityArray.getJSONObject(k).getDouble("property")+housePropertyTax);
                                        }
                                        //  System.out.println("yearrr equityAfterGoalsTime  "+year +" "+equityAfterGoalsTime);
                                        else{
                                            equityArray.getJSONObject(k).put("value",equityAfterGoalsTime);
                                            equityArray.getJSONObject(k).put("property",housePropertyTax);
                                        }
                                        //   System.out.println("ggggg "+equityArray.getJSONObject(k).getDouble("value"));

                                    }
                                }
                                //System.out.println("inside house goal itemized equity value"+equityAfterGoalsTime);
                                if (itemizedDeduction >= 0) {
                                    itemizedDeduction = itemizedDeduction + intrestPaidHouse + pmi + propertyTaxValueHouse;
                                } else {
                                    itemizedDeduction = 0;
                                }
                            }

                            //	////System.out.println("itemizedDeduction inside for loop "+itemizedDeduction+" intrestPaidHouse "+intrestPaidHouse+"pmi "+pmi+"morgageAtYearEndHouse "+morgageAtYearEndHouse+"remainingMortgageHouse "+remainingMortgageHouse+"morgagePaymentPerMonthHouse "+morgagePaymentPerMonthHouse);
                        }
                    }
                    //////System.out.println("itemizedDeduction out side for loop "+itemizedDeduction+" intrestPaidHouse "+intrestPaidHouse+"pmi "+pmi+"morgageAtYearEndHouse "+morgageAtYearEndHouse+"remainingMortgageHouse "+remainingMortgageHouse+"morgagePaymentPerMonthHouse "+morgagePaymentPerMonthHouse);
                }
            }
            //System.out.println("equity==="+equityArray);
            result.put("Status", "success");
            result.put("itemizedDeduction", itemizedDeduction);
            result.put("equity", equityArray);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        //////System.out.println("equityArray % % %"+equityArray);
        //////System.out.println("fin_id % % %"+fin_id);

        /*if(fin_id!=null && equityArray!=null)
		{
			MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi().with("{$set: {'equity':" + equityArray + "}}");
		}
		else{
			if(equityArray!=null)
			MongoDBConnection.incomeProfileCol.update("{user_id:#,profile_name:#}", user_id, "constant_income").upsert().multi().with("{$set: {'equity':" + equityArray + "}}");
		}*/


        return result;
    }
    public static double federalTaxParamCal(double income, String state, double federalTax, String fillingStatus,
            int noOfExcemtion, double taxableIncome, int currentYear,
            JSONArray kidNOAge, StatetaxModel stateTaxValue, String maritalStatus,JSONArray childArray,double taxableInvestmentAmount,double nonTaxableInvestmentAmount)
                    throws JsonProcessingException, JSONException {
        //////System.out.println("taxableInvestmentAmount==="+taxableInvestmentAmount);
        //////System.out.println("nonTaxableInvestmentAmount===="+nonTaxableInvestmentAmount);
        double personalExcemption = 0;
        final double childTaxCredit = 0;
        double otherCredit = 0;
        double credit = 0;
        double standerdDeduction = 0;
        final double itemizedDeduction = 0;
        double deduction = 0;
        double stateTxaIncome = 0;
        double stateStandardTax = 0;
        final double stateAMT = 0;
        double stateCalculatedTax = 0;
        double stateTax = 0;
        double stateTax1 = 0;
        double stateAgi = income;
        double dependentExcemption = 0;
        double otherExcemption = 0;
        double adjustment = 0;
        double exemption = 0;
        final double otherExemption = 0;
        String filingStatus = null;
        int kidCount = 0;

        //////System.out.println("noOfExcemtion==="+noOfExcemtion);

        //int kidCountForSingle=0;

        // MongoCollection stateTaxCollection1 =
        // MongoDBConnection.jongo.getCollection("stateTax");

        filingStatus = fillingStatus;
        kidCount = noOfExcemtion;
        if (fillingStatus.equals("Married Filing Jointly")) {
            kidCount = noOfExcemtion - 2;
        } else if (fillingStatus.equals("Head of Household")) {
            kidCount = noOfExcemtion-1;

            /*	for(int i=0;i<childArray.length();i++){
				////System.out.println("childArray=="+childArray);
			if(childArray.getJSONObject(i).getString("flag").equals("Yes")){
				kidCount = noOfExcemtion-1;
				////System.out.println("kidCount==="+kidCount);
			}
		}
		}else {
			kidCount = noOfExcemtion - 1;*/
        }

        try {

            // ////System.out.println("your state-->"+state);
            // ////System.out.println("you status-->"+filingStatus);

            // StatetaxModel stateTaxValue =
            // stateTaxCollection1.findOne("{'statename':#}",
            // state).as(StatetaxModel.class);
            final JSONObject incomeJson = new JSONObject(jsonMapper.writeValueAsString(stateTaxValue));
            // ////System.out.println("Aparna state tax values :"+incomeJson);
            if (state.equals("IOWA")) {
                if (filingStatus.equals("Single")) {
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    adjustment = federalTax;
                    stateAgi = stateAgi - adjustment;
                    otherCredit = 40 + (kidCount * 40);
                    credit = otherCredit + childTaxCredit;
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateStandardTax - credit;
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    adjustment = federalTax;
                    stateAgi = stateAgi - adjustment;
                    otherCredit = 80 + (kidCount * 40);
                    credit = otherCredit + childTaxCredit;
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {
                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    adjustment = federalTax;
                    stateAgi = stateAgi - adjustment;
                    if(maritalStatus.equals("No")){
                        otherCredit = 80 + (kidCount * 40);
                        ////System.out.println("otherCredit"+otherCredit+"kidCount==="+kidCount);
                    }else{
                        otherCredit = (kidCount * 40);
                    }
                    //otherCredit = (kidCount * 40);
                    credit = otherCredit + childTaxCredit;
                    //////System.out.println("credit=="+credit+"otherCredit=="+otherCredit +"childTaxCredit==="+childTaxCredit);
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    //////System.out.println("deduction===="+deduction);
                    stateTxaIncome = stateAgi - deduction;
                    //////System.out.println("stateTxaIncome===="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax===="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax==="+stateTax);
                    return stateTax;
                }
            }
            if (state.equals("NEW HAMPSHIRE")) {

                final double taxInvAmont = (taxableInvestmentAmount) * MongoDBConnection.Newhampshire.getTaxInvAmt();
                //double nonTaxInvAmt = (nonTaxableInvestmentAmount) * MongoDBConnection.Newhampshire.getNonTaxInvAmt();
                final double taxIncome1 = (taxInvAmont) * MongoDBConnection.Newhampshire.getTaxInvAmtRate();
                //double nonTaxIncome1 = (nonTaxInvAmt) * MongoDBConnection.Newhampshire.getNonTaxInvAmtRate();

                stateAgi = taxIncome1 ;
                //////System.out.println("stateAgi==="+stateAgi);
                //////System.out.println("taxIncome1==="+taxIncome1);
                //////System.out.println("nonTaxIncome1==="+nonTaxIncome1);
                if (filingStatus.equals("Single")) {
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    personalExcemption = personalExcemption
                            + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = (stateCalculatedTax - credit);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    personalExcemption = personalExcemption
                            + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = (stateCalculatedTax - credit);
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {
                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");

                    if(maritalStatus.equals("No")){
                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        personalExcemption = personalExcemption+ (kidCount * (incomeJson.getDouble("dependentExcemption")));
                        //////System.out.println("personalExcemption==="+personalExcemption+"kidCount==="+kidCount+"apppp"+(incomeJson.getDouble("dependentExcemption")));
                    }else{
                        personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                        personalExcemption = personalExcemption
                                + (kidCount * (incomeJson.getDouble("dependentExcemption")));

                    }


                    //personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                    //personalExcemption = personalExcemption
                    //+ (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    //////System.out.println("deduction===="+deduction);
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    //////System.out.println("stateTxaIncome===="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax===="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax===="+stateCalculatedTax);
                    stateTax = (stateCalculatedTax - credit);
                    //////System.out.println("stateTax====="+stateTax);
                    return stateTax;
                }
            }
            if (state.equals("ALABAMA")) {
                if (filingStatus.equals("Single")) {

                    // ////System.out.println("stateAgi0-->>"+stateAgi);
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    // ////System.out.println("standerdDeduction0-->>"+standerdDeduction);
                    if (stateAgi <= 20500) {
                        standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                        // ////System.out.println("standerdDeduction
                        // if-->"+standerdDeduction);
                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        // ////System.out.println("personalExcemption
                        // if-->"+personalExcemption);
                    } else {
                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        // ////System.out.println("personalExcemption
                        // else-->"+personalExcemption);
                        final double standDecX = stateAgi - 20500;
                        final double standDecY = standDecX / 500;
                        standerdDeduction = standerdDeduction - (standDecY * 25);
                        // ////System.out.println("standerdDeduction-0
                        // else->>"+standerdDeduction);
                        if (standerdDeduction < 2000) {
                            standerdDeduction = 2000;
                        }
                    }
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    // ////System.out.println("deduction0-->>"+deduction);
                    otherExcemption = federalTax;
                    // ////System.out.println("otherExcemption-->>"+otherExcemption);
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");

                    for (int i = 0; i < MongoDBConnection.stateagilimit.length(); i++) {
                        // ////System.out.println("state-->>>"+MongoDBConnection.stateagilimit.getInt(i));
                        if (stateAgi <= MongoDBConnection.stateagilimit.getInt(i)) {
                            standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                            personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                            // ////System.out.println("standerdDeduction1-->"+standerdDeduction);
                            // ////System.out.println("personalExcemption1-->"+personalExcemption);
                        } else {
                            personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                            final double standDecX = stateAgi - MongoDBConnection.stateagilimit.getInt(0);
                            final double standDecY = standDecX / MongoDBConnection.headofHouse.getStandedDeductionconstant();
                            standerdDeduction = standerdDeduction - (standDecY
                                    * incomeJson.getJSONObject("single").getDouble("standardDeductionamountisReduced"));
                            if (standerdDeduction < MongoDBConnection.headofHouse.getSingle()
                                    .getStanderdDeductionAmt()) {
                                standerdDeduction = MongoDBConnection.headofHouse.getSingle().getStanderdDeductionAmt();
                            }

                        }
                    }

                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    otherExcemption = federalTax;
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    // ////System.out.println("stateCalculatedTax-->>"+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax2-->>"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    // ////System.out.println("mfj in alabama");
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    // ////System.out.println("stateAgi1-->>"+stateAgi);
                    for (int i = 0; i < MongoDBConnection.marriedFilingJointstateagilimit.length(); i++) {
                        // ////System.out.println("marriedFilingJointstateagilimit-->"+
                        // MongoDBConnection.marriedFilingJointstateagilimit.getInt(i));

                        if (i + 1 != MongoDBConnection.marriedFilingJointstateagilimit.length()) {
                            if (stateAgi <= MongoDBConnection.marriedFilingJointstateagilimit.getInt(i)) {
                                standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint")
                                        .getDouble("standarddeduction");
                                personalExcemption = incomeJson.getJSONObject("marriedFilingJoint")
                                        .getDouble("personalExcemption");
                                personalExcemption = personalExcemption
                                        + (kidCount * incomeJson.getDouble("dependentExcemption"));
                                break;
                            } else if (stateAgi > MongoDBConnection.marriedFilingJointstateagilimit.getInt(i)
                                    && stateAgi <= MongoDBConnection.marriedFilingJointstateagilimit.getInt(i + 1)) {
                                final double standDecX = stateAgi
                                        - MongoDBConnection.marriedFilingJointstateagilimit.getInt(0);
                                final double standDecY = standDecX
                                        / MongoDBConnection.headofHouse.getStandedDeductionconstant();
                                standerdDeduction = standerdDeduction
                                        - (standDecY * incomeJson.getJSONObject("marriedFilingJoint")
                                                .getDouble("standardDeductionamountisReduced"));
                                personalExcemption = incomeJson.getJSONObject("marriedFilingJoint")
                                        .getDouble("personalExcemption");
                                personalExcemption = personalExcemption
                                        + (kidCount * incomeJson.getDouble("dependentExcemption"));
                                personalExcemption = personalExcemption - (kidCount * 500);
                                break;
                            } else {
                                final double standDecX = stateAgi
                                        - MongoDBConnection.marriedFilingJointstateagilimit.getInt(0);
                                final double standDecY = standDecX
                                        / MongoDBConnection.headofHouse.getStandedDeductionconstant();
                                standerdDeduction = standerdDeduction
                                        - (standDecY * incomeJson.getJSONObject("marriedFilingJoint")
                                                .getDouble("standardDeductionamountisReduced"));
                                dependentExcemption = 300;
                                personalExcemption = incomeJson.getJSONObject("marriedFilingJoint")
                                        .getDouble("personalExcemption");
                                personalExcemption = personalExcemption + (kidCount * dependentExcemption);
                                break;

                            }
                        }

                    }

                    if (standerdDeduction < MongoDBConnection.headofHouse.getMarriedFilingJoint()
                            .getStanderdDeductionAmt()) {
                        standerdDeduction = MongoDBConnection.headofHouse.getMarriedFilingJoint()
                                .getStanderdDeductionAmt();

                    }

                    // ////System.out.println("sd0-->"+standerdDeduction);
                    // ////System.out.println("pe0-->"+personalExcemption);

                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    //// ////System.out.println("deduction-->"+deduction);
                    otherExcemption = federalTax;
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    //// ////System.out.println("stateTxaIncome0-->"+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    // //////System.out.println("stateStandardTax0-->"+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    // //////System.out.println("stateCalculatedTax0-->"+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;

                    // ////System.out.println("stateTax0-->"+stateTax);

                    // +++++++++++++
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    if (stateAgi <= 20500) {
                        standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint")
                                .getDouble("standarddeduction");
                        personalExcemption = incomeJson.getJSONObject("marriedFilingJoint")
                                .getDouble("personalExcemption");
                        personalExcemption = personalExcemption
                                + (kidCount * incomeJson.getDouble("dependentExcemption"));
                    } else if (stateAgi > 20500 && stateAgi <= 100000) {
                        final double standDecX = stateAgi - 20500;
                        final double standDecY = standDecX / 500;
                        standerdDeduction = standerdDeduction - (standDecY * incomeJson
                                .getJSONObject("marriedFilingJoint").getDouble("standardDeductionamountisReduced"));
                        personalExcemption = incomeJson.getJSONObject("marriedFilingJoint")
                                .getDouble("personalExcemption");
                        personalExcemption = personalExcemption
                                + (kidCount * incomeJson.getDouble("dependentExcemption"));
                        personalExcemption = personalExcemption - (kidCount * 500);
                    } else {
                        final double standDecX = stateAgi - 20500;
                        final double standDecY = standDecX / 500;
                        standerdDeduction = standerdDeduction - (standDecY * incomeJson
                                .getJSONObject("marriedFilingJoint").getDouble("standardDeductionamountisReduced"));
                        dependentExcemption = 300;
                        personalExcemption = incomeJson.getJSONObject("marriedFilingJoint")
                                .getDouble("personalExcemption");
                        personalExcemption = personalExcemption + (kidCount * dependentExcemption);
                    }
                    if (standerdDeduction < 4000) {
                        standerdDeduction = 4000;
                    }

                    // ////System.out.println("sd1-->"+standerdDeduction);

                    // ////System.out.println("pe1-->"+personalExcemption);
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    //// ////System.out.println("deduction1-->"+deduction);

                    otherExcemption = federalTax;
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    //// ////System.out.println("stateTxaIncome1-->"+stateTxaIncome);

                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //// ////System.out.println("stateStandardTax1-->"+stateStandardTax);

                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    // //////System.out.println("stateCalculatedTax1-->"+stateCalculatedTax);

                    stateTax1 = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax1-->"+stateTax1);
                    // ++++++++++++

                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {

                    // ////System.out.println("hoh in alabama");

                    if(maritalStatus.equals("No")){




                        // ////System.out.println("state-->>>"+MongoDBConnection.stateagilimit.getInt(i));
                        if (stateAgi <= MongoDBConnection.stateagilimit.getInt(0)) {
                            //standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                            personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                            ////System.out.println("personalExcemption11111111-->"+personalExcemption);
                            ////System.out.println("personalExcemption1-->"+personalExcemption);
                        }else if (stateAgi > MongoDBConnection.headOfHouseholdstateagilimit.getInt(0)
                                && stateAgi < MongoDBConnection.headOfHouseholdstateagilimit.getInt(1)) {

                            personalExcemption = incomeJson.getJSONObject("single")
                                    .getDouble("personalExcemption");
                            personalExcemption = personalExcemption
                                    + (kidCount * incomeJson.getDouble("dependentExcemption"));
                            personalExcemption = personalExcemption - (kidCount * 500);
                            if (personalExcemption < 0) {
                                personalExcemption = 0;
                            }
                            /*////System.out.println("personalExcemption=aaa=="+personalExcemption+"kidCount==="+kidCount+
									"dependent"+incomeJson.getDouble("dependentExcemption"));*/
                        }
                        else {
                            personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                            //////System.out.println("personalExcemption1222aaa22-->"+personalExcemption);

                        }

                        /*						personalExcemption = personalExcemption
								+ (kidCount * incomeJson.getDouble("dependentExcemption"));
						////System.out.println("personalExcemption2sas233="+personalExcemption+"kidCount==="+kidCount+"dependent----"+incomeJson.getDouble("dependentExcemption"));*/
                        /*	personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
							personalExcemption = personalExcemption
									+ (kidCount * incomeJson.getDouble("dependentExcemption"));*/

                        //////System.out.println("personalExcemption==="+personalExcemption+"kidCount==="+kidCount+"apppp=="+(incomeJson.getDouble("dependentExcemption")));

                    }else{
                        /*	personalExcemption = incomeJson.getJSONObject("headOfHousehold")
									.getDouble("personalExcemption");
							personalExcemption = personalExcemption
									+ (kidCount * incomeJson.getDouble("dependentExcemption"));*/




                        if (stateAgi <= MongoDBConnection.headOfHouseholdstateagilimit.getInt(0)) {

                            personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");


                        } else if (stateAgi > MongoDBConnection.headOfHouseholdstateagilimit.getInt(0)
                                && stateAgi < MongoDBConnection.headOfHouseholdstateagilimit.getInt(1)) {

                            personalExcemption = incomeJson.getJSONObject("headOfHousehold")
                                    .getDouble("personalExcemption");
                            personalExcemption = personalExcemption
                                    + (kidCount * incomeJson.getDouble("dependentExcemption"));
                            personalExcemption = personalExcemption - (kidCount * 500);
                            if (personalExcemption < 0) {
                                personalExcemption = 0;
                            }
                        }
                        else {
                            personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                            //////System.out.println("personalExcemption122222-->"+personalExcemption);

                        }






                    }




                    //////System.out.println("stateAgi=aaa=="+stateAgi);


                    if (stateAgi <= MongoDBConnection.headOfHouseholdstateagilimit.getInt(0)) {
                        standerdDeduction = incomeJson.getJSONObject("headOfHousehold")
                                .getDouble("standarddeduction");



                    } else if (stateAgi > MongoDBConnection.headOfHouseholdstateagilimit.getInt(0)
                            && stateAgi < MongoDBConnection.headOfHouseholdstateagilimit.getInt(1)) {

                        final double standDecX = stateAgi - MongoDBConnection.headOfHouseholdstateagilimit.getInt(0);
                        final double standDecY = standDecX / MongoDBConnection.headofHouse.getStandedDeductionconstant();
                        standerdDeduction = standerdDeduction - (standDecY * incomeJson
                                .getJSONObject("headOfHousehold").getDouble("standardDeductionamountisReduced"));

                        if (standerdDeduction < incomeJson.getJSONObject("headOfHousehold")
                                .getDouble("standerdDeductionAmt")) {
                            standerdDeduction = incomeJson.getJSONObject("headOfHousehold")
                                    .getDouble("standerdDeductionAmt");
                        }

                        //	////System.out.println("standerdDeductioasasn===="+standerdDeduction);
                        /*////System.out.println("standDecY==as=aaa="+standDecY+"hhhhhhhiooo"+incomeJson
								.getJSONObject("headOfHousehold").
								getDouble("standardDeductionamountisReduced"));*/

                        /*	personalExcemption = incomeJson.getJSONObject("headOfHousehold")
									.getDouble("personalExcemption");
							personalExcemption = personalExcemption
									+ (kidCount * incomeJson.getDouble("dependentExcemption"));
							personalExcemption = personalExcemption - (kidCount * 500);
							if (personalExcemption < 0) {
								personalExcemption = 0;
							}*/
                    } else {
                        final double standDecX = stateAgi - MongoDBConnection.headOfHouseholdstateagilimit.getInt(0);
                        final double standDecY = standDecX / MongoDBConnection.headofHouse.getStandedDeductionconstant();
                        standerdDeduction = standerdDeduction - (standDecY * incomeJson
                                .getJSONObject("headOfHousehold").getDouble("standardDeductionamountisReduced"));
                        dependentExcemption =incomeJson.getJSONObject("headOfHousehold")
                                .getDouble("dependentExcemption");// 300;



                        if (standerdDeduction < incomeJson.getJSONObject("headOfHousehold")
                                .getDouble("standerdDeductionAmt")) {
                            standerdDeduction = incomeJson.getJSONObject("headOfHousehold")
                                    .getDouble("standerdDeductionAmt");
                        }
                    }
                    /*
                     * if (standerdDeduction <
                     * headofHouse.getHeadOfHousehold().
                     * getStanderdDeductionAmt()) { standerdDeduction =
                     * headofHouse.getHeadOfHousehold().
                     * getStanderdDeductionAmt(); }
                     */
                    /*personalExcemption = incomeJson.getJSONObject("headOfHousehold")
									.getDouble("personalExcemption");
							personalExcemption = personalExcemption + (kidCount * dependentExcemption);*/



                    // ////System.out.println("sd0-->"+standerdDeduction);
                    // ////System.out.println("pe0-->"+personalExcemption);
                    //standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    //////System.out.println("standerdDeduction===="+standerdDeduction);
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    //////System.out.println("deduction==="+deduction);
                    otherExcemption = federalTax;
                    //////System.out.println("otherExcemption==="+otherExcemption);
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    //////System.out.println("stateTxaIncome===="+stateTxaIncome+"stateAgi===="+stateAgi+"deduction=="+deduction+"personalExcemption==="+personalExcemption);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax===="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //	////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //	////System.out.println("stateTax===="+stateTax);

                    // ////System.out.println("stateTax0-->>"+stateTax);

                    // ==================
                    /*	standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
					if (stateAgi <= 20500) {
						standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
						personalExcemption = incomeJson.getJSONObject("headOfHousehold")
								.getDouble("personalExcemption");
						personalExcemption = personalExcemption
								+ (kidCount * incomeJson.getDouble("dependentExcemption"));
					} else if (stateAgi > 20500 && stateAgi < 100000) {
						double standDecX = stateAgi - 20500;
						double standDecY = standDecX / 500;
						standerdDeduction = standerdDeduction - (standDecY * incomeJson.getJSONObject("headOfHousehold")
								.getDouble("standardDeductionamountisReduced"));
						if (standerdDeduction < 2000) {
							standerdDeduction = 2000;
						}
						personalExcemption = incomeJson.getJSONObject("headOfHousehold")
								.getDouble("personalExcemption");
						personalExcemption = personalExcemption
								+ (kidCount * incomeJson.getDouble("dependentExcemption"));
						personalExcemption = personalExcemption - (kidCount * 500);
						if (personalExcemption < 0) {
							personalExcemption = 0;
						}
					} else {
						double standDecX = stateAgi - 20500;
						double standDecY = standDecX / 500;
						standerdDeduction = standerdDeduction - (standDecY * incomeJson.getJSONObject("headOfHousehold")
								.getDouble("standardDeductionamountisReduced"));
						dependentExcemption = 300;
						if (standerdDeduction < 2000) {
							standerdDeduction = 2000;
						}
						personalExcemption = incomeJson.getJSONObject("headOfHousehold")
								.getDouble("personalExcemption");
						personalExcemption = personalExcemption + (kidCount * dependentExcemption);
					}
					deduction = Math.max(standerdDeduction, itemizedDeduction);
					////System.out.println("deduction=121=="+deduction);
					otherExcemption = federalTax;
					////System.out.println("otherExcemption==121="+otherExcemption);
					stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
					////System.out.println("stateTxaIncome====211"+stateTxaIncome+"stateAgi===="+stateAgi+"deduction=="+deduction+"personalExcemption==="+personalExcemption);
					stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
					////System.out.println("stateStandardTax====121"+stateStandardTax);
					stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
					////System.out.println("stateCalculatedTax=121=="+stateCalculatedTax);
					stateTax = stateCalculatedTax - credit;
					////System.out.println("stateTax===="+stateTax);*/
                }
            }
            if (state.equals("INDIANA")) {
                if (filingStatus.equals("Single")) {
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    stateStandardTax = 0.033 * stateTxaIncome;
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;

                    // ////System.out.println("stateTax11--->>"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {

                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    personalExcemption = personalExcemption
                            + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    if (kidNOAge != null) {
                        for (int i = 0; i < kidNOAge.length(); i++) {
                            if (kidNOAge.getJSONObject(i).getInt("age") < 19) {
                                personalExcemption = personalExcemption
                                        + ((int) incomeJson.getDouble("aditionalExcemtion"));
                            }
                        }
                    }
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    // ////System.out.println("stateTxaIncome-->>"+stateTxaIncome);
                    // ////System.out.println("taxableIncome-->>>>"+taxableIncome);
                    stateStandardTax = 0.033 * stateTxaIncome;
                    // ////System.out.println("stateStandardTax-->>"+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("credit1--->>"+credit);
                    // ////System.out.println("stateTax1--->"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {
                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");

                    if (maritalStatus.equals("No")){
                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        personalExcemption = personalExcemption
                                + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    }else{
                        personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                        personalExcemption = personalExcemption
                                + (kidCount * (incomeJson.getDouble("dependentExcemption")));


                        //////System.out.println("personalExcemption==="+personalExcemption+"kidCount==="+kidCount+"apppp=="+(incomeJson.getDouble("dependentExcemption")));

                    }
                    if (kidNOAge != null) {
                        for (int i = 0; i < kidNOAge.length(); i++) {
                            if (kidNOAge.getJSONObject(i).getInt("age") < 19) {
                                personalExcemption = personalExcemption
                                        + ((int) incomeJson.getDouble("aditionalExcemtion"));
                            }
                        }
                    }
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    //////System.out.println("deduction===="+deduction);
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    //////System.out.println("stateTxaIncome===="+stateTxaIncome+"stateAgi===="+stateAgi+"deduction=="+deduction+"personalExcemption==="+personalExcemption);

                    stateStandardTax = 0.033 * stateTxaIncome;
                    //////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax====="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax===="+stateTax);
                    return stateTax;
                }
            }
            if (state.equals("LOUISIANA")) {
                if (filingStatus.equals("Single")) {

                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    // ////System.out.println("standerdDeduction--->>"+standerdDeduction);
                    adjustment = federalTax;
                    stateAgi = stateAgi - adjustment;
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    // ////System.out.println("personalExcemption--->>"+personalExcemption);
                    personalExcemption = personalExcemption
                            + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    // ////System.out.println("stateAgi1--->>"+stateAgi+"
                    // deduction-->>"+deduction+"
                    // personalExcemption-->>"+personalExcemption+"
                    // otherExcemption-->>"+otherExcemption);
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    // ////System.out.println("stateTxaIncome1111--->>"+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("credit---->>>"+credit);
                    // ////System.out.println("stateTax123--->>>>>"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    adjustment = federalTax;
                    stateAgi = stateAgi - adjustment;
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    personalExcemption = personalExcemption
                            + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {
                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    adjustment = federalTax;
                    stateAgi = stateAgi - adjustment;

                    if (maritalStatus.equals("No")){


                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        // ////System.out.println("personalExcemption--->>"+personalExcemption);
                        personalExcemption = personalExcemption
                                + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                        /*	////System.out.println("personalExcemption==="+personalExcemption+"kidCount====="
								+kidCount+"dependent==="+(incomeJson.getDouble("dependentExcemption")));*/

                    }else{
                        personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                        personalExcemption = personalExcemption
                                + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    }
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    //////System.out.println("stateTxaIncome==="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //	////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //	////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //	////System.out.println("stateTax========"+stateTax);
                    return stateTax;
                }
            }
            if (state.equals("MONTANA")) {
                // static StatetaxModel montana =MongoDBConnection.montana;
                // static JSONObject
                // montanaJsons=MongoDBConnection.montanaJsons;

                if (filingStatus.equals("Single")) {
                    adjustment = federalTax;
                    stateAgi = stateAgi - adjustment;
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    standerdDeduction = stateAgi
                            * MongoDBConnection.montanaJsons.getJSONObject("single").getDouble("standerdDeductionRate");// 0.20
                    // ////System.out.println("standerdDeductioniiuqawiqwuqwu"+MongoDBConnection.montanaJsons.getJSONObject("single").getDouble("standerdDeductionRate"));
                    if (standerdDeduction < MongoDBConnection.montanaJsons.getJSONObject("single")
                            .getJSONArray("standarddeductionRange").getInt(0)) {
                        standerdDeduction = MongoDBConnection.montanaJsons.getJSONObject("single")
                                .getJSONArray("standarddeductionRange").getInt(0);

                    } else if (standerdDeduction > MongoDBConnection.montanaJsons.getJSONObject("single")
                            .getJSONArray("standarddeductionRange").getInt(1)) {
                        standerdDeduction = MongoDBConnection.montanaJsons.getJSONObject("single")
                                .getJSONArray("standarddeductionRange").getInt(1);

                    }

                    // ////System.out.println("standerdDeduction0000===="+standerdDeduction);
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    personalExcemption = personalExcemption
                            + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {

                    adjustment = federalTax;
                    stateAgi = stateAgi - adjustment;

                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    standerdDeduction = stateAgi
                            * MongoDBConnection.montanaJsons.getJSONObject("single").getDouble("standerdDeductionRate");// 0.20;

                    if (standerdDeduction < MongoDBConnection.montanaJsons.getJSONObject("marriedFilingJoint")
                            .getJSONArray("standarddeductionRange").getInt(0)) {
                        standerdDeduction = MongoDBConnection.montanaJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("standarddeductionRange").getInt(0);

                    } else if (standerdDeduction > MongoDBConnection.montanaJsons.getJSONObject("marriedFilingJoint")
                            .getJSONArray("standarddeductionRange").getInt(1)) {
                        standerdDeduction = MongoDBConnection.montanaJsons.getJSONObject("marriedFilingJoint")
                                .getJSONArray("standarddeductionRange").getInt(1);

                    }

                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    personalExcemption = personalExcemption
                            + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;

                    if (state.equals("INDIANA")) {
                        if (filingStatus.equals("Single")) {
                            standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                            personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                            deduction = Math.max(standerdDeduction, itemizedDeduction);
                            stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                            stateStandardTax = 0.033 * taxableIncome;
                            stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                            stateTax = stateCalculatedTax - credit;
                            return stateTax;
                        } else if (filingStatus.equals("Married Filing Jointly")) {
                            standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint")
                                    .getDouble("standarddeduction");
                            personalExcemption = incomeJson.getJSONObject("marriedFilingJoint")
                                    .getDouble("personalExcemption");
                            personalExcemption = personalExcemption
                                    + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                            for (int i = 0; i < kidNOAge.length(); i++) {
                                if (kidNOAge.getJSONObject(i).getInt("age") < 19) {
                                    personalExcemption = personalExcemption + 1500;
                                }
                            }
                            deduction = Math.max(standerdDeduction, itemizedDeduction);
                            stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                            stateStandardTax = 0.033 * taxableIncome;
                            stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                            stateTax = stateCalculatedTax - credit;
                            return stateTax;
                        } else if (filingStatus.equals("Head of Household")) {
                            standerdDeduction = incomeJson.getJSONObject("headOfHousehold")
                                    .getDouble("standarddeduction");


                            if (maritalStatus.equals("No")){
                                personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                                personalExcemption = personalExcemption
                                        + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                                /*////System.out.println("personalExcemption==="+personalExcemption+"kidCount==="
										+kidCount+"dependent=="
										+(incomeJson.getDouble("dependentExcemption")));*/
                            }else{


                                personalExcemption = incomeJson.getJSONObject("headOfHousehold")
                                        .getDouble("personalExcemption");
                                personalExcemption = personalExcemption
                                        + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                            }
                            for (int i = 0; i < kidNOAge.length(); i++) {
                                if (kidNOAge.getJSONObject(i).getInt("age") < 19) {
                                    personalExcemption = personalExcemption + 1500;
                                }
                            }
                            deduction = Math.max(standerdDeduction, itemizedDeduction);
                            stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                            stateStandardTax = 0.033 * taxableIncome;
                            stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                            stateTax = stateCalculatedTax - credit;
                            return stateTax;
                        }
                    }
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateStandardTax
                    // married=2="+stateStandardTax+"stateCalculatedTax==2=married"+stateCalculatedTax+"stateTax2===married"+stateTax
                    // );
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {
                    //////System.out.println("incomeJson.getJSONObject=="+incomeJson.getJSONObject("headOfHousehold"));
                    //////System.out.println("incomeJson.getJSONObject=="+MongoDBConnection.montanaJsons.getJSONObject("headOfHousehold"));
                    adjustment = federalTax;
                    stateAgi = stateAgi - adjustment;
                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    standerdDeduction = stateAgi
                            * MongoDBConnection.montanaJsons.getJSONObject("single").getDouble("standerdDeductionRate"); // *
                    // 0.20;

                    if (standerdDeduction < MongoDBConnection.montanaJsons.getJSONObject("headOfHousehold")
                            .getJSONArray("standarddeductionRange").getInt(0)) {
                        standerdDeduction = MongoDBConnection.montanaJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("standarddeductionRange").getInt(0);

                    } else if (standerdDeduction > MongoDBConnection.montanaJsons.getJSONObject("headOfHousehold")
                            .getJSONArray("standarddeductionRange").getInt(1)) {
                        standerdDeduction = MongoDBConnection.montanaJsons.getJSONObject("headOfHousehold")
                                .getJSONArray("standarddeductionRange").getInt(1);
                        //////System.out.println("standerdDeduction==monn="+standerdDeduction);

                    }

                    deduction = Math.max(standerdDeduction, itemizedDeduction);

                    if (maritalStatus.equals("No")){
                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        personalExcemption = personalExcemption
                                + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                        //////System.out.println("personalExcemption===="+personalExcemption);
                        //////System.out.println("kidCount===="+kidCount);
                        //////System.out.println("dependent==="+(incomeJson.getDouble("dependentExcemption")));
                    }else{


                        personalExcemption = incomeJson.getJSONObject("headOfHousehold")
                                .getDouble("personalExcemption");
                        personalExcemption = personalExcemption
                                + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    }

                    /*	personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
					personalExcemption = personalExcemption
							+ (kidCount * (incomeJson.getDouble("dependentExcemption")));*/
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    //////System.out.println("stateTxaIncome==="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax===="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax===="+stateTax);
                    //////System.out.println("stateTax hoh"+stateTax);
                    return stateTax;
                }
            } else if (state.equals("TENNESSEE")){

                // ////System.out.println("taxableInvestmentAmount-->>"+taxableInvestmentAmount);
                // ////System.out.println("nonTaxableInvestmentAmount-->>"+nonTaxableInvestmentAmount);
                // ////System.out.println("MongoDBConnection.tennessee.getTaxInvAmt()--->>>"+MongoDBConnection.tennessee.getTaxInvAmt());
                final double taxInvAmont = (taxableInvestmentAmount) * MongoDBConnection.tennessee.getTaxInvAmt();
                // ////System.out.println("taxInvAmont--->>"+taxInvAmont);
                //double nonTaxInvAmt = (nonTaxableInvestmentAmount) * MongoDBConnection.tennessee.getNonTaxInvAmt();
                // ////System.out.println("nonTaxInvAmt--->>"+nonTaxInvAmt);
                final double taxIncome1 = (taxInvAmont) * MongoDBConnection.tennessee.getTaxInvAmtRate();
                // ////System.out.println("MongoDBConnection.tennessee.getTaxInvAmtRate()-->>"+MongoDBConnection.tennessee.getTaxInvAmtRate());
                // ////System.out.println("taxIncome1--->>"+taxIncome1);

                //double nonTaxIncome1 = (nonTaxInvAmt) * MongoDBConnection.tennessee.getNonTaxInvAmtRate();
                stateAgi = taxIncome1 ;
                if (filingStatus.equals("Single")) {

                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    deduction = Math.max(standerdDeduction, 0);
                    exemption = personalExcemption + (kidCount * (0));
                    stateTxaIncome = stateAgi - deduction - exemption - otherExemption;
                    JSONArray jsonRatesAndbracketsArray = new JSONArray();
                    jsonRatesAndbracketsArray = incomeJson.getJSONObject("marriedFilingJoint")
                            .getJSONArray("ratesAndBrackets");
                    double fraction = 0;
                    for (int i = 0; i < jsonRatesAndbracketsArray.length(); i++) {
                        fraction = jsonRatesAndbracketsArray.getJSONObject(i).getDouble("rates");
                    }
                    stateStandardTax = stateTxaIncome * fraction;
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax0--->>"+stateTax);

                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    // ////System.out.println("standerdDeduction123--->>"+standerdDeduction);
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    // ////System.out.println("personalExcemption123--->>>"+personalExcemption);
                    dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    // ////System.out.println("dependentExcemption-->>"+dependentExcemption);
                    deduction = Math.max(standerdDeduction, 0);
                    // ////System.out.println("deduction-->>"+deduction);
                    exemption = personalExcemption + (kidCount * (0));
                    // ////System.out.println("exemption-->>"+exemption);
                    //// ////System.out.println("deduction--->>>"+deduction);
                    stateTxaIncome = stateAgi - deduction - exemption - otherExemption;
                    // ////System.out.println("stateTxaIncome-->>"+stateTxaIncome);
                    // JSONArray jsonRatesAndbracketsArray = new JSONArray();
                    jsonRatesAndbracketsArray = incomeJson.getJSONObject("single").getJSONArray("ratesAndBrackets");
                    fraction = 0;
                    for (int i = 0; i < jsonRatesAndbracketsArray.length(); i++) {
                        fraction = jsonRatesAndbracketsArray.getJSONObject(i).getDouble("rates");
                    }
                    // ////System.out.println("fraction-->>"+fraction);
                    stateStandardTax = stateTxaIncome * fraction;
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    // ////System.out.println("stateCalculatedTax-->>"+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("credit-->>"+credit);
                    // ////System.out.println("stateTax1--->>"+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    deduction = Math.max(standerdDeduction, 0);
                    exemption = personalExcemption + (kidCount * (0));
                    stateTxaIncome = stateAgi - deduction - exemption - otherExemption;
                    JSONArray jsonRatesAndbracketsArray = new JSONArray();
                    jsonRatesAndbracketsArray = incomeJson.getJSONObject("marriedFilingJoint")
                            .getJSONArray("ratesAndBrackets");
                    double fraction = 0;
                    for (int i = 0; i < jsonRatesAndbracketsArray.length(); i++) {
                        fraction = jsonRatesAndbracketsArray.getJSONObject(i).getDouble("rates");
                    }
                    stateStandardTax = stateTxaIncome * fraction;
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {
                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                    dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    deduction = Math.max(standerdDeduction, 0);
                    if (maritalStatus.equals("No")){

                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        exemption = personalExcemption + (kidCount * (0));
                        //////System.out.println("personalExcemption==="+personalExcemption);
                        //////System.out.println("exemption====="+exemption);
                    }else{
                        exemption = personalExcemption + (kidCount * (0));
                    }
                    //exemption = personalExcemption + (kidCount * (0));
                    stateTxaIncome = stateAgi - deduction - exemption - otherExemption;
                    //////System.out.println("stateTxaIncome===="+stateTxaIncome);
                    JSONArray jsonRatesAndbracketsArray = new JSONArray();
                    jsonRatesAndbracketsArray = incomeJson.getJSONObject("marriedFilingJoint")
                            .getJSONArray("ratesAndBrackets");
                    double fraction = 0;
                    for (int i = 0; i < jsonRatesAndbracketsArray.length(); i++) {
                        fraction = jsonRatesAndbracketsArray.getJSONObject(i).getDouble("rates");
                    }
                    stateStandardTax = stateTxaIncome * fraction;
                    //////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax===="+stateTax);
                    return stateTax;
                }
            } else if (state.equals("OREGON")){
                //////System.out.println("oregob===");
                double qualifiedPlanDeduction = 0;
                double otherAdjustment = 0;
                double stateAGI = 0;
                if (filingStatus.equals("Single")) {
                    standerdDeduction = incomeJson.getJSONObject("single").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                    dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    qualifiedPlanDeduction = 0;
                    otherAdjustment = Math.min(MongoDBConnection.oregon.getOtherAdjustmentMinValue(), federalTax);

                    adjustment = otherAdjustment - qualifiedPlanDeduction;
                    stateAGI = income - adjustment;
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAGI - deduction - exemption - otherExemption;
                    final double incomestateStandardTaxValue = stateStandardTaxValue(stateTxaIncome, incomeJson,
                            filingStatus);
                    stateCalculatedTax = Math.max(incomestateStandardTaxValue, stateAMT);
                    otherCredit = personalExcemption + (kidCount * dependentExcemption);
                    credit = otherCredit + childTaxCredit;
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("credit==="+credit);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    qualifiedPlanDeduction = 0;
                    otherAdjustment = Math.min(MongoDBConnection.oregon.getOtherAdjustmentMinValue(), federalTax);
                    adjustment = otherAdjustment - qualifiedPlanDeduction;
                    stateAGI = income - adjustment;
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAGI - deduction - exemption - otherExemption;
                    double incomestateStandardTaxValue = stateStandardTaxValue(stateTxaIncome, incomeJson,
                            filingStatus);
                    stateCalculatedTax = Math.max(incomestateStandardTaxValue, stateAMT);
                    otherCredit = personalExcemption + (kidCount * dependentExcemption);
                    credit = otherCredit + childTaxCredit;
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax1--->>"+stateTax);
                    // ===========================

                    standerdDeduction = incomeJson.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    // ////System.out.println("standerdDeduction-->"+standerdDeduction);
                    personalExcemption = incomeJson.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    // ////System.out.println("personalExcemption-->"+personalExcemption);
                    dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    qualifiedPlanDeduction = 0;
                    otherAdjustment = Math.min(6450, federalTax);
                    adjustment = otherAdjustment - qualifiedPlanDeduction;
                    stateAGI = income - adjustment;
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    stateTxaIncome = stateAGI - deduction - exemption - otherExemption;
                    incomestateStandardTaxValue = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(incomestateStandardTaxValue, stateAMT);
                    otherCredit = personalExcemption + (kidCount * dependentExcemption);
                    credit = otherCredit + childTaxCredit;
                    stateTax = stateCalculatedTax - credit;
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {
                    standerdDeduction = incomeJson.getJSONObject("headOfHousehold").getDouble("standarddeduction");
                    //////System.out.println("personalExcemption==aaaa=="+personalExcemption);
                    if (maritalStatus.equals("No")){
                        personalExcemption = incomeJson.getJSONObject("single").getDouble("personalExcemption");
                        dependentExcemption = incomeJson.getDouble("dependentExcemption");
                        //////System.out.println("personalExcemption===="+personalExcemption);
                        //////System.out.println("dependentExcemption====="+dependentExcemption);
                    }else{
                        personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                        dependentExcemption = incomeJson.getDouble("dependentExcemption");
                    }


                    /*	personalExcemption = incomeJson.getJSONObject("headOfHousehold").getDouble("personalExcemption");
					dependentExcemption = incomeJson.getDouble("dependentExcemption");*/
                    qualifiedPlanDeduction = 0;
                    otherAdjustment = Math.min(MongoDBConnection.oregon.getOtherAdjustmentMinValue(), federalTax);
                    //////System.out.println("otherAdjustment===="+otherAdjustment);
                    adjustment = otherAdjustment - qualifiedPlanDeduction;
                    //////System.out.println("adjustment"+adjustment);
                    stateAGI = income - adjustment;
                    //	////System.out.println("stateAGI===="+stateAGI);
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    //	////System.out.println("deduction====="+deduction);
                    stateTxaIncome = stateAGI - deduction - exemption - otherExemption;
                    //////System.out.println("stateTxaIncome====="+stateTxaIncome);
                    final double incomestateStandardTaxValue = stateStandardTaxValue(stateTxaIncome, incomeJson,
                            filingStatus);
                    //////System.out.println("incomestateStandardTaxValue===="+incomestateStandardTaxValue);
                    stateCalculatedTax = Math.max(incomestateStandardTaxValue, stateAMT);
                    //////System.out.println("stateCalculatedTax===="+stateCalculatedTax);
                    otherCredit = personalExcemption + (kidCount * dependentExcemption);
                    //////System.out.println("otherCredit====="+otherCredit);
                    credit = otherCredit + childTaxCredit;
                    //////System.out.println("credit======"+credit);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax======"+stateTax);
                    return stateTax;
                }
            }else if (state.equals("MISSOURI")){
                ////System.out.println("inside MISSOURI ");
                final JSONObject missouri = new JSONObject(
                        jsonMapper.writeValueAsString(MongoDBConnection.missouri));

                //////System.out.println("federalTax  "+federalTax);
                adjustment = federalTax;
                stateAgi = stateAgi - adjustment;
                //	////System.out.println("stateAgi    "+stateAgi);
                if (filingStatus.equals("Single")) {
                    standerdDeduction = missouri.getJSONObject("single").getDouble("standarddeduction");
                    deduction = Math.max(standerdDeduction, itemizedDeduction);
                    personalExcemption = missouri.getJSONObject("single").getDouble("personalExcemption");
                    personalExcemption = personalExcemption
                            + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    stateTxaIncome = stateAgi - deduction - personalExcemption - otherExcemption;
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    stateTax = stateCalculatedTax - credit;

                    // ////System.out.println("stateTax--.."+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Married Filing Jointly")) {
                    standerdDeduction = missouri.getJSONObject("marriedFilingJoint").getDouble("standarddeduction");
                    // ////System.out.println("
                    // standerdDeduction======"+standerdDeduction);
                    personalExcemption = missouri.getJSONObject("marriedFilingJoint").getDouble("personalExcemption");
                    personalExcemption = personalExcemption
                            + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    // ////System.out.println("personalExcemption===="+personalExcemption);
                    stateTxaIncome = stateAgi - standerdDeduction - personalExcemption - otherExcemption;
                    // ////System.out.println("stateTxaIncome===="+stateTxaIncome);
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    // ////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    // ////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    // ////System.out.println("stateTax====="+stateTax);
                    return stateTax;
                } else if (filingStatus.equals("Head of Household")) {

                    standerdDeduction = missouri.getJSONObject("headOfHousehold").getDouble("standarddeduction");



                    if (maritalStatus.equals("No")){
                        personalExcemption = missouri.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                        personalExcemption = personalExcemption
                                + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                        //////System.out.println("personalExcemption===123="+personalExcemption+"kidCount====="+kidCount+"dependtt"+(incomeJson.getDouble("dependentExcemption")));
                    }else{
                        personalExcemption = missouri.getJSONObject("headOfHousehold").getDouble("personalExcemption");
                        personalExcemption = personalExcemption
                                + (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    }


                    //personalExcemption = personalExcemption
                    //	+ (kidCount * (incomeJson.getDouble("dependentExcemption")));
                    stateTxaIncome = stateAgi - standerdDeduction - personalExcemption - otherExcemption;
                    /*	////System.out.println("stateTxaIncome"+stateTxaIncome+"stateAgi===="+stateAgi+
							"standerdDeduction===="+standerdDeduction+"personalExcemption==="
							+personalExcemption+"otherExcemption"+otherExcemption);*/
                    stateStandardTax = stateStandardTaxValue(stateTxaIncome, incomeJson, filingStatus);
                    //////System.out.println("stateStandardTax==="+stateStandardTax);
                    stateCalculatedTax = Math.max(stateStandardTax, stateAMT);
                    //////System.out.println("stateCalculatedTax==="+stateCalculatedTax);
                    stateTax = stateCalculatedTax - credit;
                    //////System.out.println("stateTax===="+stateTax);

                    return stateTax;
                }





            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return stateTax;
    }

    // =============================new Limit
    // array====================================================
    public static JSONObject limiteAfterRetirement(double user401k, double spouse401k, double newUser401k,
            double newSpouse401k, double newUserIra, double newSpouseIra, int userAge, double userIncome,
            double spouseIncome, String marritalStatus, int spouseAge, JSONObject limits, double AGI, int i,
            JSONObject collegeAmount) {
        final double combinedIncome = 0;
        try {
            // ////System.out.println("Mahi : marritalStatus :"+marritalStatus);
            // ////System.out.println("Ranjitha : values user401k "+user401k+"
            // spouse401k"+spouse401k);
            String modelFillingStatus = null;
            JSONObject constantJsonObj = new JSONObject();
            if (marritalStatus.equals("Married Filing Jointly") || marritalStatus.equals("qualifying widow")) {
                modelFillingStatus = "marriedFilingJoint";
            } else {
                modelFillingStatus = "single";
            }
            //	////System.out.println("BALA***filing status"+modelFillingStatus);
            constantJsonObj = MongoDBConnection.constJson.getJSONObject(modelFillingStatus).getJSONObject("limits");
            final int maxAGI = constantJsonObj.getInt("maxAGI");
            final int minAGI = constantJsonObj.getInt("minAGI");
            final int user401kAgeBelow50 = constantJsonObj.getInt("user401kAgeBelow50");
            final int userIRAAgeBelow50 = constantJsonObj.getInt("userIRAAgeBelow50");
            final int user401kAgeFrom50 = constantJsonObj.getInt("user401kAgeFrom50");
            final int userIRAAgeFrom50 = constantJsonObj.getInt("userIRAAgeFrom50");
            // ////System.out.println("Ranjitha : constantJsonObj
            // :"+constantJsonObj);
            if (marritalStatus.equals("qualifying widow") || marritalStatus.equals("Married Filing Jointly")) {
                final int spouseNotWorkingAGI = constantJsonObj.getInt("spouseNotWorkingAGI");
                final int spouse401kAgeBelow50 = constantJsonObj.getInt("spouse401kAgeBelow50");
                final int spouseIRAAgeBelow50 = constantJsonObj.getInt("spouseIRAAgeBelow50");
                final int spouse401kAgeFrom50 = constantJsonObj.getInt("spouse401kAgeFrom50");
                final int spouseIRAAgeFrom50 = constantJsonObj.getInt("spouseIRAAgeFrom50");
                final int max401kAgi = constantJsonObj.getInt("max401kAgi");
                final int min401kAgi = constantJsonObj.getInt("min401kAgi");
                // ////System.out.println("Ranjitha ::
                // maxAGI"+maxAGI+"spouseNotWorkingAGI"+spouseNotWorkingAGI);
                if (collegeAmount == null) {
                    if (userAge < 50 && (spouseAge < 50 && spouseAge != 0)) {
                        if (spouseIncome == 0)// spouse is not working but user
                            // is working
                        {
                            if (AGI < spouseNotWorkingAGI) {
                                limits.put("spouseIRA", spouseIRAAgeBelow50);
                            }
                            if (user401k == 0)// if user dont have 401kplan
                            {
                                limits.put("user401k", 0);
                                limits.put("spouse401k", 0);
                                if (AGI <= minAGI) {
                                    limits.put("userIRA", userIRAAgeBelow50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("userIRA", (userIRAAgeBelow50 - (AGI - minAGI) * 0.275));
                                }

                                else {
                                    limits.put("userIRA", 0);
                                }
                            } else// if user have 401k plan
                            {
                                if (AGI <= minAGI) {
                                    limits.put("userIRA", userIRAAgeBelow50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("userIRA", (userIRAAgeBelow50 - (AGI - minAGI) * 0.275));
                                } else {
                                    limits.put("userIRA", 0);
                                }
                                limits.put("user401k", user401kAgeBelow50);
                                limits.put("spouse401k", 0);
                            }
                        } else if (userIncome == 0)// spouse is working but user
                            // is not working
                        {
                            if (spouse401k == 0)// spouse dont have 401k plan
                            {
                                limits.put("spouse401k", 0);
                                limits.put("user401k", 0);
                                if (AGI <= minAGI) {
                                    limits.put("spouseIRA", spouseIRAAgeBelow50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("spouseIRA", (spouseIRAAgeBelow50 - (AGI - minAGI) * 0.275));
                                } else {
                                    limits.put("spouseIRA", 0);
                                }
                            } else// spouse have 401k plan
                            {
                                if (AGI <= minAGI) {
                                    limits.put("spouseIRA", spouseIRAAgeBelow50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("spouseIRA", (spouseIRAAgeBelow50 - (AGI - minAGI) * 0.275));
                                } else {
                                    limits.put("spouseIRA", 0);
                                }
                                limits.put("spouse401k", spouse401kAgeBelow50);
                                limits.put("user401k", 0);
                            }
                        } else // <50 both spouse and user is working
                        {
                            if (user401k == 0 && spouse401k == 0)// spouse have
                                // 401k but
                                // user dont
                                // have 401k
                            {
                                limits.put("user401k", 0);
                                limits.put("spouse401k", 0);
                                limits.put("spouseIRA", spouseIRAAgeBelow50);
                                limits.put("userIRA", userIRAAgeBelow50);

                            } else if (user401k == 0 && spouse401k != 0)// spouse
                                // have
                                // 401k
                                // but
                                // user
                                // dont
                                // have
                                // 401k
                            {
                                limits.put("user401k", 0);
                                if (AGI <= min401kAgi) {
                                    limits.put("spouseIRA", spouseIRAAgeBelow50);
                                    limits.put("userIRA", userIRAAgeBelow50);
                                } else if (AGI > min401kAgi && AGI <= max401kAgi) {
                                    limits.put("spouseIRA", (spouseIRAAgeBelow50 - (AGI - min401kAgi) * 0.55));
                                    limits.put("userIRA", (userIRAAgeBelow50 - (AGI - min401kAgi) * 0.55));
                                } else {
                                    limits.put("spouseIRA", 0);
                                    limits.put("userIRA", 0);
                                }
                                limits.put("spouse401k", spouse401kAgeBelow50);
                            } else if (user401k != 0 && spouse401k == 0)// spouse
                                // dont
                                // have
                                // 401k
                                // but
                                // user
                                // have
                                // 401k
                            {
                                limits.put("spouse401k", 0);
                                if (AGI <= min401kAgi) {
                                    limits.put("spouseIRA", spouseIRAAgeBelow50);
                                    limits.put("userIRA", userIRAAgeBelow50);
                                } else if (AGI > min401kAgi && AGI <= max401kAgi) {
                                    limits.put("spouseIRA", (spouseIRAAgeBelow50 - (AGI - min401kAgi) * 0.55));
                                    limits.put("userIRA", (userIRAAgeBelow50 - (AGI - min401kAgi) * 0.55));
                                } else {
                                    limits.put("spouseIRA", 0);
                                    limits.put("userIRA", 0);
                                }
                                limits.put("user401k", user401kAgeBelow50);
                            } else// both have 401k plan
                            {
                                if (AGI <= minAGI) {
                                    limits.put("userIRA", userIRAAgeBelow50);
                                    limits.put("spouseIRA", spouseIRAAgeBelow50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("spouseIRA", (userIRAAgeBelow50 - (AGI - minAGI) * 0.275));
                                    limits.put("userIRA", (spouseIRAAgeBelow50 - (AGI - minAGI) * 0.275));
                                } else {
                                    limits.put("spouseIRA", 0);
                                    limits.put("userIRA", 0);
                                }
                                limits.put("user401k", user401kAgeBelow50);
                                limits.put("spouse401k", spouse401kAgeBelow50);
                            }
                        }
                    } else if (userAge < 50 && spouseAge >= 50) {
                        if (spouseIncome == 0)// spouse is not working but user
                            // is working
                        {
                            if (AGI < spouseNotWorkingAGI) {
                                limits.put("spouseIRA", spouseIRAAgeFrom50);
                            }
                            if (user401k == 0)// if user dont have 401kplan
                            {
                                limits.put("user401k", 0);
                                limits.put("spouse401k", 0);
                                if (AGI <= minAGI) {
                                    limits.put("userIRA", userIRAAgeBelow50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("userIRA", (userIRAAgeBelow50 - (AGI - minAGI) * 0.275));
                                } else {
                                    limits.put("userIRA", 0);
                                }
                            } else// if user have 401k plan
                            {
                                if (AGI <= minAGI) {
                                    limits.put("userIRA", userIRAAgeBelow50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("userIRA", (userIRAAgeBelow50 - (AGI - minAGI) * 0.275));
                                } else {
                                    limits.put("userIRA", 0);
                                }
                                limits.put("user401k", user401kAgeBelow50);
                                limits.put("spouse401k", 0);
                            }
                        } else if (userIncome == 0)// spouse is working but user
                            // is not working
                        {
                            if (spouse401k == 0)// spouse dont have 401k plan
                            {
                                limits.put("spouse401k", 0);
                                limits.put("user401k", 0);
                                if (AGI <= minAGI) {
                                    limits.put("spouseIRA", spouse401kAgeFrom50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("spouseIRA", (spouse401kAgeFrom50 - (AGI - minAGI) * 0.325));
                                } else {
                                    limits.put("spouseIRA", 0);
                                }
                            } else// spouse have 401k plan
                            {
                                if (AGI <= minAGI) {
                                    limits.put("spouseIRA", spouse401kAgeFrom50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("spouseIRA", (spouse401kAgeFrom50 - (AGI - minAGI) * 0.325));
                                } else {
                                    limits.put("spouseIRA", 0);
                                }
                                limits.put("spouse401k", spouse401kAgeFrom50);
                                limits.put("user401k", 0);
                            }
                        } else // <50 both spouse and user is working
                        {
                            if (user401k == 0 && spouse401k == 0)// spouse have
                                // 401k but
                                // user dont
                                // have 401k
                            {
                                limits.put("user401k", 0);
                                limits.put("spouse401k", 0);
                                limits.put("userIRA", userIRAAgeBelow50);
                                limits.put("spouseIRA", spouseIRAAgeFrom50);
                            } else if (user401k == 0 && spouse401k != 0)// spouse
                                // have
                                // 401k
                                // but
                                // user
                                // dont
                                // have
                                // 401k
                            {
                                limits.put("user401k", 0);
                                if (AGI <= min401kAgi) {
                                    limits.put("spouseIRA", spouseIRAAgeFrom50);
                                    limits.put("userIRA", userIRAAgeBelow50);
                                } else if (AGI > min401kAgi && AGI <= max401kAgi) {
                                    limits.put("spouseIRA", (spouseIRAAgeFrom50 - (AGI - min401kAgi) * 0.65));
                                    limits.put("userIRA", (userIRAAgeBelow50 - (AGI - min401kAgi) * 0.55));
                                } else {
                                    limits.put("spouseIRA", 0);
                                    limits.put("userIRA", 0);
                                }
                                limits.put("spouse401k", spouse401kAgeFrom50);
                            } else if (user401k != 0 && spouse401k == 0)// spouse
                                // dont
                                // have
                                // 401k
                                // but
                                // user
                                // have
                                // 401k
                            {
                                limits.put("spouse401k", 0);
                                if (combinedIncome <= min401kAgi) {
                                    limits.put("spouseIRA", spouseIRAAgeFrom50);
                                    limits.put("userIRA", userIRAAgeBelow50);
                                } else if (AGI > min401kAgi && AGI <= max401kAgi) {
                                    limits.put("spouseIRA", (spouseIRAAgeFrom50 - (AGI - min401kAgi) * 0.65));
                                    limits.put("userIRA", (userIRAAgeBelow50 - (AGI - min401kAgi) * 0.55));
                                } else {
                                    limits.put("spouseIRA", 0);
                                    limits.put("userIRA", 0);
                                }
                                limits.put("user401k", user401kAgeBelow50);
                            } else// both have 401k plan
                            {
                                if (combinedIncome <= minAGI) {
                                    limits.put("userIRA", userIRAAgeBelow50);
                                    limits.put("spouseIRA", spouseIRAAgeFrom50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("spouseIRA", (spouseIRAAgeFrom50 - (AGI - minAGI) * 0.375));
                                    limits.put("userIRA", (userIRAAgeBelow50 - (AGI - minAGI) * 0.275));
                                } else {
                                    limits.put("spouseIRA", 0);
                                    limits.put("userIRA", 0);
                                }
                                limits.put("user401k", user401kAgeBelow50);
                                limits.put("spouse401k", spouse401kAgeFrom50);
                            }
                        }
                    } else if (userAge >= 50 && spouseAge < 50 && spouseAge != 0) {
                        if (spouseIncome == 0)// spouse is not working but user
                            // is working
                        {
                            if (AGI < spouseNotWorkingAGI) {
                                limits.put("spouseIRA", spouseIRAAgeBelow50);
                            }
                            if (user401k == 0)// if user dont have 401kplan
                            {
                                limits.put("user401k", 0);
                                limits.put("spouse401k", 0);
                                if (AGI <= minAGI) {
                                    limits.put("userIRA", userIRAAgeFrom50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("userIRA", (userIRAAgeFrom50 - (AGI - minAGI) * 0.325));
                                } else {
                                    limits.put("userIRA", 0);
                                }
                            } else// if user have 401k plan
                            {
                                if (AGI <= minAGI) {
                                    limits.put("userIRA", userIRAAgeFrom50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("userIRA", (userIRAAgeFrom50 - (AGI - minAGI) * 0.325));
                                } else {
                                    limits.put("userIRA", 0);
                                }
                                limits.put("user401k", user401kAgeFrom50);
                                limits.put("spouse401k", 0);
                            }
                        } else if (userIncome == 0)// spouse is working but user
                            // is not working
                        {
                            if (spouse401k == 0)// spouse dont have 401k plan
                            {
                                limits.put("spouse401k", 0);
                                limits.put("user401k", 0);
                                if (AGI <= minAGI) {
                                    limits.put("spouseIRA", spouseIRAAgeBelow50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("spouseIRA", (spouseIRAAgeBelow50 - (AGI - minAGI) * 0.275));
                                } else {
                                    limits.put("spouseIRA", 0);
                                }
                            } else// spouse have 401k plan
                            {
                                if (AGI <= minAGI) {
                                    limits.put("spouseIRA", spouseIRAAgeBelow50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("spouseIRA", (spouseIRAAgeBelow50 - (AGI - minAGI) * 0.275));
                                } else {
                                    limits.put("spouseIRA", 0);
                                }
                                limits.put("spouse401k", spouse401kAgeBelow50);
                                limits.put("user401k", 0);
                            }
                        } else // <50 both spouse and user is working
                        {
                            if (user401k == 0 && spouse401k == 0)// spouse have
                                // 401k but
                                // user dont
                                // have 401k
                            {
                                limits.put("user401k", 0);
                                limits.put("spouse401k", 0);
                                limits.put("spouseIRA", spouseIRAAgeBelow50);
                                limits.put("userIRA", userIRAAgeFrom50);

                            } else if (user401k == 0 && spouse401k != 0)// spouse
                                // have
                                // 401k
                                // but
                                // user
                                // dont
                                // have
                                // 401k
                            {
                                limits.put("user401k", 0);
                                if (AGI <= min401kAgi) {
                                    limits.put("spouseIRA", spouseIRAAgeBelow50);
                                    limits.put("userIRA", userIRAAgeFrom50);
                                } else if (AGI > min401kAgi && AGI <= max401kAgi) {
                                    limits.put("spouseIRA", (spouseIRAAgeBelow50 - (AGI - min401kAgi) * 0.55));
                                    limits.put("userIRA", (userIRAAgeFrom50 - (AGI - min401kAgi) * 0.65));
                                } else {
                                    limits.put("spouseIRA", 0);
                                    limits.put("userIRA", 0);
                                }
                                limits.put("spouse401k", spouse401kAgeBelow50);
                            } else if (user401k != 0 && spouse401k == 0)// spouse
                                // dont
                                // have
                                // 401k
                                // but
                                // user
                                // have
                                // 401k
                            {
                                limits.put("spouse401k", 0);
                                if (AGI <= min401kAgi) {
                                    limits.put("spouseIRA", spouseIRAAgeBelow50);
                                    limits.put("userIRA", userIRAAgeFrom50);
                                } else if (AGI > min401kAgi && AGI <= max401kAgi) {
                                    limits.put("spouseIRA", (spouseIRAAgeBelow50 - (AGI - min401kAgi) * 0.55));
                                    limits.put("userIRA", (userIRAAgeFrom50 - (AGI - min401kAgi) * 0.65));
                                } else {
                                    limits.put("spouseIRA", 0);
                                    limits.put("userIRA", 0);
                                }
                                limits.put("user401k", user401kAgeFrom50);
                            } else// both have 401k plan
                            {
                                if (AGI <= minAGI) {
                                    limits.put("userIRA", userIRAAgeFrom50);
                                    limits.put("spouseIRA", spouseIRAAgeBelow50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("spouseIRA", (spouseIRAAgeBelow50 - (AGI - minAGI) * 0.275));
                                    limits.put("userIRA", (userIRAAgeFrom50 - (AGI - minAGI) * 0.375));
                                } else {
                                    limits.put("spouseIRA", 0);
                                    limits.put("userIRA", 0);
                                }
                                limits.put("user401k", user401kAgeFrom50);
                                limits.put("spouse401k", spouse401kAgeBelow50);
                            }
                        }
                    } else // >50 with marrieage (spouseage>50 && userAge>50)
                    {
                        if (spouseIncome == 0)// user have income but spouse
                            // dont have
                        {
                            if (AGI < spouseNotWorkingAGI) {
                                limits.put("spouseIRA", spouseIRAAgeFrom50);
                            }
                            if (user401k == 0)// user dont have 401k
                            {
                                limits.put("user401k", 0);
                                limits.put("spouse401k", 0);
                                if (AGI <= minAGI) {
                                    limits.put("userIRA", userIRAAgeFrom50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("userIRA", (userIRAAgeFrom50 - (AGI - minAGI) * 0.325));
                                } else {
                                    limits.put("userIRA", 0);
                                }
                            } else// user have 401k
                            {
                                if (AGI <= minAGI) {
                                    limits.put("userIRA", userIRAAgeFrom50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("userIRA", (userIRAAgeFrom50 - (AGI - minAGI) * 0.325));
                                } else {
                                    limits.put("userIRA", 0);
                                }
                                limits.put("user401k", user401kAgeFrom50);
                                limits.put("spouse401k", 0);
                            }
                        } else if (userIncome == 0)// user dont have income but
                            // spouse have
                        {
                            if (spouse401k == 0)// spouse dont have 401k
                            {
                                limits.put("spouse401k", 0);
                                limits.put("user401k", 0);
                                if (AGI <= minAGI) {
                                    limits.put("spouseIRA", spouseIRAAgeFrom50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("spouseIRA", (spouseIRAAgeFrom50 - (AGI - minAGI) * 0.325));
                                } else {
                                    limits.put("spouseIRA", 0);
                                }
                            } else// spouse have 401k
                            {
                                if (AGI <= minAGI) {
                                    limits.put("spouseIRA", spouseIRAAgeFrom50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("spouseIRA", (spouseIRAAgeFrom50 - (AGI - minAGI) * 0.325));
                                } else {
                                    limits.put("spouseIRA", 0);
                                }
                                limits.put("spouse401k", spouse401kAgeFrom50);
                                limits.put("user401k", 0);
                            }
                        } else // >50 both spouse and user is working
                        {
                            if (user401k == 0 && spouse401k == 0)// spouse have
                                // 401k but
                                // user dont
                                // have 401k
                            {
                                limits.put("user401k", 0);
                                limits.put("spouse401k", 0);
                                limits.put("spouseIRA", spouseIRAAgeFrom50);
                                limits.put("userIRA", userIRAAgeFrom50);

                            } else if (user401k == 0 && spouse401k != 0)// user
                                // dont
                                // have
                                // 401k
                                // but
                                // spouse
                                // have
                                // 401k
                            {
                                limits.put("user401k", 0);
                                if (AGI <= min401kAgi) {
                                    limits.put("spouseIRA", spouseIRAAgeFrom50);
                                    limits.put("userIRA", userIRAAgeFrom50);
                                } else if (AGI > min401kAgi && AGI <= max401kAgi) {
                                    limits.put("spouseIRA", (spouseIRAAgeFrom50 - (AGI - min401kAgi) * 0.65));
                                    limits.put("userIRA", (userIRAAgeFrom50 - (AGI - min401kAgi) * 0.65));
                                } else {
                                    limits.put("spouseIRA", 0);
                                    limits.put("userIRA", 0);
                                }
                                limits.put("spouse401k", spouse401kAgeFrom50);
                            } else if (user401k != 0 && spouse401k == 0)// user
                                // have
                                // 401k
                                // but
                                // spouse
                                // dont
                                // have
                                // 401k
                            {
                                limits.put("spouse401k", 0);
                                if (AGI <= min401kAgi) {
                                    limits.put("spouseIRA", spouseIRAAgeFrom50);
                                    limits.put("userIRA", userIRAAgeFrom50);
                                } else if (AGI > min401kAgi && AGI <= max401kAgi) {
                                    limits.put("spouseIRA", (spouseIRAAgeFrom50 - (AGI - min401kAgi) * 0.65));
                                    limits.put("userIRA", (userIRAAgeFrom50 - (AGI - min401kAgi) * 0.65));
                                } else {
                                    limits.put("spouseIRA", 0);
                                    limits.put("userIRA", 0);
                                }
                                limits.put("user401k", user401kAgeFrom50);
                            } else// user and spouse both have 401k
                            {
                                if (AGI <= minAGI) {
                                    limits.put("userIRA", userIRAAgeFrom50);
                                    limits.put("spouseIRA", spouseIRAAgeFrom50);
                                } else if (AGI > minAGI && AGI <= maxAGI) {
                                    limits.put("spouseIRA", (spouseIRAAgeFrom50 - (AGI - minAGI) * 0.375));
                                    limits.put("userIRA", (userIRAAgeFrom50 - (AGI - minAGI) * 0.375));
                                } else {
                                    limits.put("spouseIRA", 0);
                                    limits.put("userIRA", 0);
                                }
                                limits.put("user401k", user401kAgeFrom50);
                                limits.put("spouse401k", spouse401kAgeFrom50);
                            }
                        }
                    } // End >50 with Marriage
                } else {

                    if (collegeAmount.getDouble("currentAmount") != 0) {
                        double newAmount = 0;
                        if (collegeAmount.getString("type").equals("create")) {
                            newAmount = limits.getDouble("user529Plan")
                                    + collegeAmount.getDouble("currentAmount") * .80;
                            limits.put("user529Plan", newAmount);
                            /*
                             * newAmount=limits.getDouble("spouse529Plan")+
                             * collegeAmount.getDouble("currentAmount")*.80;
                             * limits.put("spouse529Plan", newAmount);
                             */
                        } else if (collegeAmount.getString("type").equals("update")) {
                            newAmount = (limits.getDouble("user529Plan") - (collegeAmount.getDouble("oldAmount") * .80))
                                    + (collegeAmount.getDouble("currentAmount") * .80);
                            limits.put("user529Plan", newAmount);
                            /*
                             * newAmount=(limits.getDouble("spouse529Plan")-(
                             * collegeAmount.getDouble("oldAmount")*.80))+(
                             * collegeAmount.getDouble("currentAmount")*.80);
                             * limits.put("spouse529Plan", newAmount);
                             */
                        } else if (collegeAmount.getString("type").equals("delete")) {
                            newAmount = limits.getDouble("user529Plan") - (collegeAmount.getDouble("oldAmount") * .80);
                            limits.put("user529Plan", newAmount);
                            /*
                             * newAmount=limits.getDouble("spouse529Plan")-(
                             * collegeAmount.getDouble("oldAmount")*.80);
                             * limits.put("spouse529Plan", newAmount);
                             */
                        }
                    }
                }
            } else// user is not Marriage
            {
                limits.put("spouseIRA", 0);
                limits.put("spouse401k", 0);
                limits.put("spouse529Plan", 0);
                if (collegeAmount == null) {
                    // ////System.out.println("inside single..");
                    if (userAge < 50)// user age is <50years
                    {
                        // ////System.out.println("less than 50");
                        if (user401k == 0)// user dont have 401k
                        {
                            limits.put("user401k", 0);
                            if (AGI <= minAGI) {
                                limits.put("userIRA", userIRAAgeBelow50);
                            } else if (AGI > minAGI && AGI <= maxAGI) {
                                limits.put("userIRA", (userIRAAgeBelow50 - (AGI - minAGI) * 0.55));
                            } else {
                                limits.put("userIRA", 0);
                            }
                        } else// user have 401k
                        {
                            if (AGI <= minAGI) {
                                limits.put("userIRA", userIRAAgeBelow50);
                            } else if (AGI > minAGI && AGI <= maxAGI) {
                                limits.put("userIRA", (userIRAAgeBelow50 - (AGI - minAGI) * 0.55));
                            } else {
                                limits.put("userIRA", 0);
                            }
                            limits.put("user401k", user401kAgeBelow50);
                        }
                    } else// user age is >50
                    {
                        if (user401k == 0)// user dont have 401k
                        {
                            limits.put("user401k", 0);
                            if (AGI <= minAGI) {
                                limits.put("userIRA", userIRAAgeFrom50);
                            } else if (AGI > minAGI && AGI <= maxAGI) {
                                limits.put("userIRA", (userIRAAgeFrom50 - (AGI - minAGI) * 0.55));
                            } else {
                                limits.put("userIRA", 0);
                            }
                        } else// user have 401k
                        {
                            if (AGI <= minAGI) {
                                limits.put("userIRA", userIRAAgeFrom50);
                            } else if (AGI > minAGI && AGI <= maxAGI) {
                                limits.put("userIRA", (userIRAAgeFrom50 - (AGI - minAGI) * 0.55));
                            } else {
                                limits.put("userIRA", 0);
                            }
                            limits.put("user401k", user401kAgeFrom50);
                        }
                    }
                } else {
                    if (collegeAmount.getDouble("currentAmount") != 0) {
                        double newAmount = 0;
                        if (collegeAmount.getString("type").equals("create")) {
                            newAmount = limits.getDouble("user529Plan")
                                    + collegeAmount.getDouble("currentAmount") * .80;
                            limits.put("user529Plan", newAmount);
                        } else if (collegeAmount.getString("type").equals("update")) {
                            newAmount = (limits.getDouble("user529Plan") - (collegeAmount.getDouble("oldAmount") * .80))
                                    + (collegeAmount.getDouble("currentAmount") * .80);
                            limits.put("user529Plan", newAmount);
                        } else if (collegeAmount.getString("type").equals("delete")) {
                            newAmount = limits.getDouble("user529Plan") - (collegeAmount.getDouble("oldAmount") * .80);
                            limits.put("user529Plan", newAmount);
                        }
                    }
                }
            }
            if (newUser401k != 0) {
                limits.put("user401k", newUser401k);
            }
            if (newUserIra != 0) {
                limits.put("userIRA", newUserIra);
            }
            if (newSpouse401k != 0) {
                limits.put("spouse401k", newSpouse401k);
            }
            if (newSpouseIra != 0) {
                limits.put("spouseIRA", newSpouseIra);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return limits;
    }

    public static JSONObject sweepingOfMoneyInvestmentPortfolio(JSONArray userIncomes, String fin_id, JSONArray income,
            JSONArray spouse_income, JSONArray userExpense, JSONArray limits, String maritalStatus, JSONArray assets,
            JSONArray tax, String user_id, JSONArray fillingExemtion, int userAge, int spouseAge,

            int emergencyFundAmt, boolean emergencyFundFlag, JSONArray deductions, JSONArray kidBirthYear,
            JSONObject dataFromRetirement, RetirementGoal goalCollectionData, JSONArray housing_expense, String emergencyType,
            String monthsOfIncome, String monthsOfExpense, boolean retirementFlag, int RiskScore) {

        amountMorgage = 0;
        newYear = 0;

        newYearHouse = 0;

        amountMorgageHouse = 0;

        final Calendar cal = Calendar.getInstance();

        final int currentYear = cal.get(Calendar.YEAR);

        final JSONObject responseObj = new JSONObject();

        final StringBuilder statusMsg = new StringBuilder("Goal not fesibile because ");

        try {

            boolean goalFeasibility = true;

            final InvestmentPortfolioImpl investmentPortfolio = new InvestmentPortfolioImpl();

            final User user = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
            final FinPlan finPlanDetails = MongoDBConnection.finplancol
                    .findOne("{_id:#}", fin_id ).as(FinPlan.class);

            final int Risk_Score = RiskScore;

            double remaining_amount = user.getCash();

            double taxableInvestmentAmount = user.getTaxableInvestments();

            double nonTaxableInvestmentAmount = 0;

            if (user.getNonTaxableInvestments().equals("Yes")) {

                nonTaxableInvestmentAmount = user.getUser401k() + user.getUserIRA() + user.getUser529()
                + user.getUserRothIra() + user.getSpouse401k() + user.getSpouseIRA() + user.getSpousePlan529()
                + user.getSpouseRothIra();

            }

            final double savingInterestRate = user.getSavingInterestRate();

            double excess = 0;

            final double initialLimit = 0;

            double taxExcess = 0;

            double excessNonTax = 0;

            double maximumContributionForNonTaxable = 0;

            double riskFactor = 1;

            if (spouseAge == 0) {

                spouseAge = user.getSpouseAge();

            }

            int age = currentYear - user.getBirthYear();

            double limitForSavingAcount = 0;

            double limitForTaxableAmount = 0;

            double limitForCollegeGoalTaxable = 0;

            if (maritalStatus.equals("Yes")) {

                riskFactor = 2;

            } else {

                spouse_income = new JSONArray();

            }

            // Find the basic details for calculating the tax from user and fin
            // plan collections.

            final String state = user.getState();

            final int kidcount = user.getChildCount();

            final int spouseBirthYear = user.getSpouseBirthYear();

            final int userBirthYear = user.getBirthYear();

            final long startYear = user.getRemainingYearsMortgage();

            final double remainingMortgageOriginal = user.getRemainingMortgage();

            final double remainingMortgageInterestRate = user.getRemainingMortgageInterestRate();

            final double houseAppreciationRate=user.getHouseAppreciationRate();

            final String[] createdTs = user.getCreatedTs().split("/");

            final int registrationYear = Integer.parseInt(createdTs[0]);

            JSONArray childArray = null;

            JSONObject userJson = null;

            if (user != null) {

                userJson = new JSONObject(jsonMapper.writeValueAsString(user));

                if (!userJson.isNull("childs")) {

                    childArray = userJson.getJSONArray("childs");

                }

            }

            String houseStatus = user.getHouseStatus();

            final double houseValue = user.getHouseValue();
            final String registrationHouseStatus=user.getHouseStatus();
            if (housing_expense != null) {

                houseStatus = "Own";

            }

            // ================================Retirement part starts
            // here===================================================

            final String goal_id = null;

            JSONArray userIncome = null;

            int retirementYear = 0;

            int spouseStartYear = 0;

            double userIRA = 0;

            double spouseIRA = 0;

            int startRetirementYear = 0;

            double userContribute401k = 0;

            double spouseContribute401k = 0;

            double userSSB = 0;

            double spouseSSB = 0;

            double yearlySSB = 0;

            int riskScore = user.getRiskScore();

            // ...limits buckets

            double spouse401k = 0;

            double user401k = 0;

            double userRothIRA = 0;

            double spouseRothIRA = 0;

            double user529Plan = 0;

            double spouse529Plan = 0;

            double tempExcess = 0;

            double userIRALimit = 0;

            double spouseIRALimit = 0;

            // ---limits array model

            double fourNotOneLimit = 0;

            double user529Limit = 0;

            double spouse529Limit = 0;

            double iraLimit = 0;

            double spouseFourNotOne = 0;

            double spouseIraLimit = 0;

            double spouseTempTaxable = 0;

            double AGI = 0;

            double nonTaxableForLimits = 0;

            double user401kContribution = 0;

            double spouse401kContribution = 0;

            double userCap = 0;

            double spouseCap = 0;

            double userMatching = 0;

            double spouseMatching = 0;

            int userStartYearForCap = 0;

            int spouseStartYearForCap = 0;

            double userSavedAmount = 0;

            double spouseSavedAmount = 0;

            double userIraSavedAmount = 0;

            double spouseIraSavedAmount = 0;

            double userTempExcess = 0;

            double tempExcessNew = 0;

            double spouseTempExcess = 0;

            int retFlag = 0;

            if (retirementFlag)

            {

                retFlag = 1;

            }

            if ((retFlag == 0 && goalCollectionData != null)) {

                userIRA = goalCollectionData.getUserContributionInIRA();

                spouseIRA = goalCollectionData.getSpouseContributionInIRA();

                userContribute401k = (goalCollectionData.getUserContributeAmount()
                        * goalCollectionData.getUserMatchContribution()) / 100;

                spouseContribute401k = (goalCollectionData.getSpouseContributeAmount()
                        * goalCollectionData.getSpouseMatchContribution()) / 100;

                // spouseAge = dataFromRetirement.getInt("spouseAge");

                retirementYear = dataFromRetirement.getInt("userStartYear");

                spouseStartYear = dataFromRetirement.getInt("spouseStartYear");

                // ////System.out.println("spouseStartYear mahi :"+spouseStartYear);

                userSSB = dataFromRetirement.getDouble("userssb");

                spouseSSB = dataFromRetirement.getDouble("spousessb");

            }

            else if ((retFlag == 1 && goalCollectionData != null)) {

                userIRA = goalCollectionData.getUserContributionInIRA();

                spouseIRA = goalCollectionData.getSpouseContributionInIRA();

                userContribute401k = (goalCollectionData.getUserContributeAmount()
                        * goalCollectionData.getUserMatchContribution()) / 100;

                spouseContribute401k = (goalCollectionData.getSpouseContributeAmount()
                        * goalCollectionData.getSpouseMatchContribution()) / 100;

                // spouseAge = dataFromRetirement.getInt("spouseAge");

                retirementYear = dataFromRetirement.getInt("userStartYear");

                spouseStartYear = dataFromRetirement.getInt("spouseStartYear");

                // ////System.out.println("spouseStartYear mahi :"+spouseStartYear);

                userSSB = goalCollectionData.getUserSSB();

                spouseSSB = goalCollectionData.getSpouseSSB();

            }

            else if (retFlag == 1) {

                final RetirementGoal retirementData = MongoDBConnection.goalcoll
                        .findOne("{fin_id:#,user_id:#,goalType:#}", fin_id, user_id, "Retirement").as(RetirementGoal.class);

                userIRA = retirementData.getUserContributionInIRA();

                spouseIRA = retirementData.getSpouseContributionInIRA();

                userContribute401k = (retirementData.getUserContributeAmount()
                        * retirementData.getUserMatchContribution()) / 100;

                spouseContribute401k = (retirementData.getSpouseContributeAmount()
                        * retirementData.getSpouseMatchContribution()) / 100;

                retirementYear = user.getBirthYear() + retirementData.getRetirementAge();

                spouseStartYear = user.getSpouseBirthYear() + retirementData.getSpouseRetirementAge();

                spouseSSB = user.getSpouseSSB();

                userSSB = user.getUserSSB();

                riskScore = user.getRiskScore();

            } else {

                if (dataFromRetirement == null) {

                    if (maritalStatus.equals("Yes")) {

                        spouseStartYear = user.getSpouseBirthYear() + 70;

                    } else {

                        // ////System.out.println("inside no spouse no retirement");

                        spouseAge = 0;

                    }

                } else {

                    // ////System.out.println("***********Mahi New
                    // changes*************");

                    if (maritalStatus.equals("Yes")) {

                        spouseStartYear = dataFromRetirement.getInt("spouseStartYear");

                    } else {

                        spouseAge = 0;

                    }

                }

                retirementYear = user.getBirthYear() + 70;

                spouseSSB = user.getSpouseSSB();

                userSSB = user.getUserSSB();

                riskScore = user.getRiskScore(); // spending

            }

            userIncome = userIncomes;

            int userStartYear = retirementYear;

            if (spouseStartYear != 0 && spouseStartYear < retirementYear) {

                startRetirementYear = spouseStartYear;

            } else {

                startRetirementYear = retirementYear;

            }

            userStartYearForCap = retirementYear;

            spouseStartYearForCap = spouseStartYear;

            final int spouseRetirementStartYear = spouseStartYear;

            final int userRetirementStartYear = retirementYear;

            final double incomeRothIRA = 0;// userRothIRA+spouseRothIRA;

            double contributionAmount = userIRA + userContribute401k + spouseIRA + spouseContribute401k;

            int spouseAgeInFinPlan = 0;

            if (fin_id != null) {

                final Marriagegoalmodel marriagegoalmodel = MongoDBConnection.goalcoll
                        .findOne("{fin_id:#,goalType:#}", fin_id, "Marriage").as(Marriagegoalmodel.class);

                if (marriagegoalmodel != null) {

                    spouseAgeInFinPlan = marriagegoalmodel.getBirthYear();

                }

            }
            //------------------genrating equity array------------------
            final JSONArray equity = new JSONArray();
            final double equityVal=0.00;
            for(int j=0;j<income.length();j++)
            {
                final JSONObject equityObj=new JSONObject();
                equityObj.put("year", income.getJSONObject(j).getInt("year"));
                equityObj.put("value", equityVal);
                equity.put(equityObj);
            }

            // ========================================Retirement part ends
            // here==========================================================

            // Find the basic details for calculating the tax from user and fin
            // plan collections - Ends here.

            final StatetaxModel stateTaxValue = MongoDBConnection.stateTaxColl.findOne("{'statename':#}", state)
                    .as(StatetaxModel.class);

            // ////System.out.println("userIncomes77-->"+userIncomes);

            for (int i = 0; i < assets.length(); i++) {

                double growthRate = 0;

                double limitUser529 = 0;

                double limitSpouse529 = 0;

                double portfolioDividend = 0;

                double portfolioInterest = 0;

                double taxableInvestmentIncome = 0;

                double nonTaxableInvestmentIncome = 0;

                final double expenses = userExpense.getJSONObject(i).getDouble("totalExpense");

                double spouseIncome = 0;

                if (goalCollectionData != null)

                {

                    // ////System.out.println("Ranjitha :: inside flag");

                    userCap = goalCollectionData.getUserCap() / 100;

                    userMatching = goalCollectionData.getUserMatchContribution() / 100;

                    if (userAge <= 99)

                    {

                        user401kContribution = userCap * userMatching
                                * (userIncomes.getJSONObject(i)).getDouble("value");

                    }

                    userSavedAmount = goalCollectionData.getUserSavedAmount();

                    userIraSavedAmount = goalCollectionData.getUserSavedInIRA();

                    if (goalCollectionData.getSpouseCap() != 0 || goalCollectionData.getSpouseSavedAmount() != 0
                            || goalCollectionData.getSpouseSavedInIRA() != 0
                            || goalCollectionData.getSpouseMatchContribution() != 0
                            || goalCollectionData.getSpouseSavedAmount() != 0)

                    {

                        spouseCap = goalCollectionData.getSpouseCap() / 100;

                        spouseMatching = goalCollectionData.getSpouseMatchContribution() / 100;

                        spouse401kContribution = spouseCap * spouseMatching
                                * spouse_income.getJSONObject(i).getDouble("value");

                        spouseSavedAmount = goalCollectionData.getSpouseSavedAmount();

                        spouseIraSavedAmount = goalCollectionData.getSpouseSavedInIRA();

                    }

                }

                // ////System.out.println("Ranjitha ::
                // user401kContribution"+user401kContribution+"spouse401kContribution.."+spouse401kContribution+"spouseIraSavedAmount.."+spouseIraSavedAmount);

                if (maritalStatus.equals("Yes")) {

                    if (fillingExemtion.getJSONObject(i).getString("fillingStatus").equals("Single") || fillingExemtion
                            .getJSONObject(i).getString("fillingStatus").equals("Head of Household")) {

                        riskFactor = 1;

                    } else if (!fillingExemtion.getJSONObject(i).getString("fillingStatus")
                            .equals("Married Filing Separately")
                            && !fillingExemtion.getJSONObject(i).getString("fillingStatus").equals("Qualified Widow")) {

                        riskFactor = 2;

                    }

                    if (i < spouse_income.length()) {

                        spouseIncome = spouse_income.getJSONObject(i).getDouble("value");

                    } else {

                        spouseIncome = 0;

                    }

                }

                double totalTax = 0;

                final int year = assets.getJSONObject(i).getInt("year");

                if (year < startRetirementYear) {

                    JSONObject tempTax = calTaxPerYear(0, 0, income.getJSONObject(i).getDouble("value"), user_id,
                            fin_id, userIncome.getJSONObject(i).getDouble("value"), spouseIncome, year,
                            fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"), fillingExemtion.getJSONObject(i)

                            .getString("fillingStatus"),
                            age, spouseAge, maritalStatus, kidBirthYear, state, kidcount, registrationYear, childArray,
                            houseStatus, houseValue, housing_expense, startYear, remainingMortgageOriginal,
                            remainingMortgageInterestRate, 0, stateTaxValue,taxableInvestmentAmount, nonTaxableInvestmentAmount,equity,finPlanDetails,houseAppreciationRate,registrationHouseStatus);

                    // ////System.out.println("Elapsed time for calculating tax: " +
                    // (System.nanoTime() - taxStartTime) / 1000000 + " milli
                    // seconds");

                    totalTax = tempTax.getDouble("federalTax") + tempTax.getDouble("fICAMedicareTax")
                    + tempTax.getDouble("spouseSSTax") + tempTax.getDouble("userSSTax")
                    + tempTax.getDouble("stateTax");

                    // ////System.out.println("Year:" + year + ", Initial Tax: " +
                    // tempTax);

                    // --------------------- checking for limit of saving
                    // account---------------------

                    if (limits != null) {

                        if (emergencyFundFlag == false) {

                            limitForSavingAcount = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2)
                                    + limits.getJSONObject(i).getInt("saving");

                        } else {

                            if (emergencyType.equals("Fix Amount"))

                            {

                                limitForSavingAcount = (emergencyFundAmt) + limits.getJSONObject(i).getInt("saving");

                            }

                            else if (emergencyType.equals("Expense")) {

                                limitForSavingAcount = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                        * (Integer.parseInt(monthsOfExpense)))
                                        + limits.getJSONObject(i).getInt("saving");

                            }

                            else if (emergencyType.equals("Income")) {

                                limitForSavingAcount = (income.getJSONObject(i).getDouble("value") / 12
                                        * (Integer.parseInt(monthsOfIncome)))
                                        + limits.getJSONObject(i).getInt("saving");

                            }

                        }

                        limitForTaxableAmount = limits.getJSONObject(i).getInt("taxable");

                        limitForCollegeGoalTaxable = limits.getJSONObject(i).getInt("collegeGoalTaxable");

                    } else {

                        if (emergencyFundFlag == false) {

                            limitForSavingAcount = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2);

                        } else {

                            if (emergencyType.equals("Fix Amount"))

                            {

                                limitForSavingAcount = (emergencyFundAmt);

                            }

                            else if (emergencyType.equals("Expense")) {

                                limitForSavingAcount = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                        * (Integer.parseInt(monthsOfExpense)));

                            }

                            else if (emergencyType.equals("Income")) {

                                limitForSavingAcount = (income.getJSONObject(i).getDouble("value") / 12
                                        * (Integer.parseInt(monthsOfIncome)));

                            }

                        }

                        limitForTaxableAmount = 0;

                        limitForCollegeGoalTaxable = 0;

                    }

                    double currYearTaxableAmount = 0;

                    if (i == 0) {

                        currYearTaxableAmount = taxableInvestmentAmount;

                    } else {

                        currYearTaxableAmount = assets.getJSONObject(i - 1).getDouble("taxable_investment_amount");

                    }

                    final JSONObject growthRateJSON = investmentPortfolio.growthRate(Risk_Score, riskFactor, age,
                            MongoDBConnection.investmentPortfolioData);

                    growthRate = growthRateJSON.getDouble("growthRate");

                    portfolioDividend = growthRateJSON.getDouble("portfolioDividend");

                    portfolioInterest = growthRateJSON.getDouble("portfolioInterest");

                    if (i != 0)

                    {

                        if (assets.getJSONObject(i - 1).getDouble(
                                "user529Plan") <= limits.getJSONObject(i).getDouble("user529Plan") + user.getUser529())

                        {

                            limitUser529 = limits.getJSONObject(i).getDouble("user529Plan")
                                    - assets.getJSONObject(i - 1).getDouble("user529Plan");

                            if (limitUser529 < 0)

                            {

                                limitUser529 = 0;

                            }

                        }

                        if (assets.getJSONObject(i - 1)
                                .getDouble("spouse529Plan") <= limits.getJSONObject(i).getDouble("spouse529Plan")
                                + user.getSpousePlan529())

                        {

                            limitSpouse529 = limits.getJSONObject(i).getDouble("spouse529Plan")
                                    - assets.getJSONObject(i - 1).getDouble("spouse529Plan");

                            if (limitSpouse529 < 0)

                            {

                                limitSpouse529 = 0;

                            }

                        }

                    }

                    maximumContributionForNonTaxable = limits.getJSONObject(i).getDouble("user401k") +

                            limits.getJSONObject(i).getDouble("spouse401k") +

                            limits.getJSONObject(i).getDouble("userIRA") +

                            limits.getJSONObject(i).getDouble("spouseIRA") +

                            limits.getJSONObject(i).getDouble("userRouthIRA") +

                            limits.getJSONObject(i).getDouble("spouseRouthIRA") +

                            limitUser529 +

                            limitSpouse529;

                    // ////System.out.println("Ravi::::
                    // maximumContributionForNonTaxable==="+maximumContributionForNonTaxable+"..year=="+limits.getJSONObject(i).getInt("year")+"..limitUser529==="+limitUser529+".....limitSpouse529==="+limitSpouse529);

                    double savingAmount = remaining_amount;

                    // ////System.out.println("Mahi :
                    // maximumContributionForNonTaxable=:
                    // "+maximumContributionForNonTaxable);

                    double deductionAmount = 0;

                    if (deductions != null) {

                        deductionAmount = deductions.getJSONObject(i).getDouble("saving");

                    }

                    remaining_amount = remaining_amount + income.getJSONObject(i).getDouble("value")
                            + (savingInterestRate * remaining_amount / 100) - expenses - totalTax - deductionAmount;

                    // ////System.out.println("Maximum Contribution For NonTaxable:
                    // " + maximumContributionForNonTaxable + "Cash: " +
                    // remaining_amount + " Income: " +
                    // income.getJSONObject(i).getDouble("value") + " Expenses:
                    // " + expenses + " Deductions: "

                    // + deductionAmount + " Total tax: " + totalTax);

                    ////System.out.println("Krishnan:: remaining_amount: " + remaining_amount +" limitForSavingAcount " + limitForSavingAcount+"..limitForTaxableAmount.."+limitForTaxableAmount);

                    if (remaining_amount >= limitForSavingAcount) {

                        excess = remaining_amount - (limitForSavingAcount);

                        savingAmount = savingAmount - deductionAmount;

                        if (savingAmount < limitForSavingAcount) {

                            savingAmount = limitForSavingAcount - savingAmount;

                        } else {

                            savingAmount = 0;

                        }

                        remaining_amount = limitForSavingAcount;

                    } else {

                        savingAmount = 0;

                        excess = 0;

                    }

                    ////System.out.println("Ravi excess After saving limit:::"+excess);

                    if (remaining_amount < 0 && age < 70) {

                        taxableInvestmentAmount = taxableInvestmentAmount + remaining_amount;

                        if (taxableInvestmentAmount < 0) {

                            goalFeasibility = false;

                            statusMsg.append("Insufficient balance in savings account");

                            // ////System.out.println("Insufficient balance in
                            // savings account");

                            break;

                        }

                        remaining_amount = 0;

                    }

                    // ////System.out.println("currYearTaxableAmount==="+currYearTaxableAmount+"...limitForTaxableAmount=="+limitForTaxableAmount);

                    final double limitForTaxableTemp = limitForTaxableAmount;

                    if ((currYearTaxableAmount < limitForTaxableAmount)) {

                        limitForTaxableAmount = limitForTaxableAmount - currYearTaxableAmount;

                        if (excess <= limitForTaxableAmount) {

                            limitForTaxableAmount = excess;

                            taxExcess = 0;

                        } else {

                            taxExcess = excess - limitForTaxableAmount;

                        }

                        if (taxExcess > 0) {

                            excess = taxExcess;

                        } else {

                            excess = 0;

                        }

                    }

                    else {

                        limitForTaxableAmount = 0;

                    }

                    // ////System.out.println("limitForTaxableAmount.."+limitForTaxableAmount);

                    if ((currYearTaxableAmount < limitForTaxableTemp + limitForCollegeGoalTaxable) && excess > 0
                            && limitForCollegeGoalTaxable > 0) {

                        // ////System.out.println("currYearTaxableAmount====="+currYearTaxableAmount+"..limitForTaxableTemp.."+limitForTaxableTemp+"....limitForCollegeGoalTaxable=="+limitForCollegeGoalTaxable);

                        limitForCollegeGoalTaxable = limitForCollegeGoalTaxable
                                - (currYearTaxableAmount - limitForTaxableTemp);

                        if ((0.20 * excess) <= limitForCollegeGoalTaxable) {

                            limitForCollegeGoalTaxable = (0.20 * excess);

                            taxExcess = (0.80 * excess);

                        } else {

                            taxExcess = excess - limitForCollegeGoalTaxable;

                        }

                        if (taxExcess > 0) {

                            excess = taxExcess;

                        } else {

                            excess = 0;

                        }

                    } else {

                        limitForCollegeGoalTaxable = 0;

                    }

                    excessNonTax = 0;

                    double ftempBefore = tempTax.getDouble("federalTax");

                    double ftempEnd = 0;

                    final double incomeTemp = income.getJSONObject(i).getDouble("value");

                    if (excess <= 0) {

                        nonTaxableForLimits = 0;

                        ftempEnd = tempTax.getDouble("federalTax");

                        if (deductions != null) {

                            nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + excess
                                    + (growthRate * nonTaxableInvestmentAmount) / 100
                                    - deductions.getJSONObject(i).getDouble("nontaxable")
                                    - deductions.getJSONObject(i).getDouble("collegeGoalNontaxable");

                        } else {

                            nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + excess
                                    + (growthRate * nonTaxableInvestmentAmount) / 100;

                        }

                        // ////System.out.println("Zero Excess. No iteration
                        // performed");

                    } else {

                        // ////System.out.println("Excess being iterated is: " +
                        // excess);

                        while (excess > 0) {

                            AGI = income.getJSONObject(i).getDouble("value") - excess;
                            /*	////System.out.println("AGI==1"+AGI);
							////System.out.println("value==1"+income.getJSONObject(i).getDouble("value"));
							////System.out.println("excess==1"+excess);*/

                            tempTax = calTaxPerYear(0, 0, AGI, user_id, fin_id,
                                    userIncome.getJSONObject(i).getDouble("value"), spouseIncome, year,
                                    fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                    fillingExemtion.getJSONObject(i).getString("fillingStatus"), age,

                                    spouseAge, maritalStatus, kidBirthYear, state, kidcount, registrationYear,
                                    childArray, houseStatus, houseValue, housing_expense, startYear,
                                    remainingMortgageOriginal, remainingMortgageInterestRate, 0, stateTaxValue,taxableInvestmentAmount, nonTaxableInvestmentAmount,equity,finPlanDetails,houseAppreciationRate,registrationHouseStatus);

                            excess = incomeTemp - expenses - tempTax.getDouble("federalTax")
                                    - tempTax.getDouble("userSSTax") - tempTax.getDouble("fICAMedicareTax")
                                    - tempTax.getDouble("spouseSSTax") - tempTax.getDouble("stateTax") - savingAmount
                                    - limitForTaxableAmount - limitForCollegeGoalTaxable;
                            //	////System.out.println("noOfExcemtion caltaxp"+fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"));

                            ftempEnd = tempTax.getDouble("federalTax");

                            if (excess > maximumContributionForNonTaxable) {

                                nonTaxableForLimits = maximumContributionForNonTaxable;

                                AGI = income.getJSONObject(i).getDouble("value") - maximumContributionForNonTaxable;

                                tempTax = calTaxPerYear(0, 0, AGI, user_id, fin_id,
                                        userIncome.getJSONObject(i).getDouble("value"), spouseIncome, year,
                                        fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                        fillingExemtion.getJSONObject(i).getString("fillingStatus"), age,

                                        spouseAge, maritalStatus, kidBirthYear, state, kidcount, registrationYear,
                                        childArray, houseStatus, houseValue, housing_expense, startYear,
                                        remainingMortgageOriginal, remainingMortgageInterestRate, 0, stateTaxValue,taxableInvestmentAmount, nonTaxableInvestmentAmount,equity,finPlanDetails,houseAppreciationRate,registrationHouseStatus);

                                excess = incomeTemp - expenses - tempTax.getDouble("federalTax")
                                        - tempTax.getDouble("userSSTax") - tempTax.getDouble("fICAMedicareTax")
                                        - tempTax.getDouble("spouseSSTax") - tempTax.getDouble("stateTax")
                                        - savingAmount - limitForTaxableAmount - limitForCollegeGoalTaxable;

                                ftempEnd = tempTax.getDouble("federalTax");

                                if (deductions != null) {

                                    nonTaxableInvestmentAmount = nonTaxableInvestmentAmount
                                            + maximumContributionForNonTaxable
                                            + (growthRate * nonTaxableInvestmentAmount) / 100
                                            - deductions.getJSONObject(i).getDouble("nontaxable")
                                            - deductions.getJSONObject(i).getDouble("collegeGoalNontaxable");

                                } else {

                                    nonTaxableInvestmentAmount = nonTaxableInvestmentAmount
                                            + maximumContributionForNonTaxable
                                            + (growthRate * nonTaxableInvestmentAmount) / 100;

                                }

                                excessNonTax = excess - maximumContributionForNonTaxable;

                                break;

                            }

                            if (!((ftempBefore - ftempEnd) <= 5)) {

                                ftempBefore = ftempEnd;

                            } else {

                                nonTaxableForLimits = excess;

                                ftempEnd = tempTax.getDouble("federalTax");

                                if (deductions != null) {

                                    nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + excess
                                            + (growthRate * nonTaxableInvestmentAmount) / 100
                                            - deductions.getJSONObject(i).getDouble("nontaxable")
                                            - deductions.getJSONObject(i).getDouble("collegeGoalNontaxable");

                                } else {

                                    nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + excess
                                            + (growthRate * nonTaxableInvestmentAmount) / 100;

                                }

                                excessNonTax = 0;

                                break;

                            }

                        }

                        // ////System.out.println("Elapsed time for sweeping excess:
                        // " + (System.nanoTime() - sweepingExcessTime) /
                        // 1000000 + " milli seconds");

                    }

                    // Money sweeping into non taxable buckets starts here...

                    JSONObject limitsTemp = new JSONObject();

                    limitsTemp = limits.getJSONObject(i);

                    // ////System.out.println("Ranjitha :: limitsTemp :" +
                    // limitsTemp+"userAge"+userAge+"spouseAge"+spouseAge+"growthRate"+growthRate+"nonTaxableForLimits.."+nonTaxableForLimits);

                    fourNotOneLimit = limitsTemp.getDouble("user401k");

                    if (i != 0)

                    {

                        user529Limit = limitUser529;

                    }

                    // ////System.out.println("Ravi::::
                    // user529Limit=="+user529Limit);

                    // user529Limit=limitsTemp.getDouble("user529Plan");

                    iraLimit = limitsTemp.getDouble("userIRA");

                    if (i != 0) {

                        user401k = assets.getJSONObject(i - 1).getDouble("user401k");

                        userIRALimit = assets.getJSONObject(i - 1).getDouble("userIRA");

                        user529Plan = assets.getJSONObject(i - 1).getDouble("user529Plan");

                    } else {

                        user401k = user.getUser401k() + userSavedAmount;

                        userIRALimit = user.getUserIRA() + userIraSavedAmount;

                        user529Plan = user.getUser529();

                        userRothIRA = user.getUserRothIra();

                    }

                    if (nonTaxableForLimits > user529Limit)

                    {

                        // ////System.out.println("Ravi :: user529Plan= if
                        // "+user529Limit);

                        user529Plan = user529Limit + (user529Plan + (user529Plan * growthRate) / 100);

                        nonTaxableForLimits = nonTaxableForLimits - user529Limit;

                    }

                    else

                    {

                        // ////System.out.println("Ravi :: user529Plan= else
                        // "+nonTaxableForLimits);

                        user529Plan = nonTaxableForLimits + (user529Plan + (user529Plan * growthRate) / 100);

                        nonTaxableForLimits = 0;

                    }

                    if (nonTaxableForLimits > spouse529Limit)

                    {

                        // ////System.out.println("Ravi :: spouse529Plan= if
                        // "+spouse529Limit);

                        spouse529Plan = spouse529Limit + (spouse529Plan + (spouse529Plan * growthRate)) / 100;

                        nonTaxableForLimits = nonTaxableForLimits - spouse529Limit;

                    }

                    else

                    {

                        // ////System.out.println("Ravi :: spouse529Plan= else
                        // "+nonTaxableForLimits);

                        spouse529Plan = nonTaxableForLimits + (spouse529Plan + (spouse529Plan * growthRate)) / 100;

                        nonTaxableForLimits = 0;

                    }

                    if (fillingExemtion.getJSONObject(i).getString("fillingStatus").equals("Married Filing Jointly")
                            && spouse_income.getJSONObject(i).getDouble("value") != 0)

                    {

                        spouseTempTaxable = nonTaxableForLimits / 2;

                        if (spouseTempTaxable < 0)

                        {

                            spouseTempTaxable = 0;

                        }

                        nonTaxableForLimits = nonTaxableForLimits / 2;

                    }

                    if (nonTaxableForLimits <= fourNotOneLimit) {

                        // ////System.out.println("Ravi :: user401k=
                        // "+nonTaxableForLimits);

                        user401k = nonTaxableForLimits + (user401k + (user401k * growthRate) / 100);

                        userIRALimit = userIRALimit + (userIRALimit * growthRate) / 100;

                        // user529Plan = user529Plan + (user529Plan *
                        // growthRate) / 100;

                    } else {

                        // ////System.out.println("Ravi :: user401k= new
                        // "+fourNotOneLimit);

                        user401k = fourNotOneLimit + (user401k + (user401k * growthRate) / 100);

                        tempExcess = nonTaxableForLimits - fourNotOneLimit;

                        if (tempExcess < iraLimit) {

                            // ////System.out.println("Ravi :: userIRALimit=
                            // "+tempExcess);

                            userIRALimit = tempExcess + (userIRALimit + (userIRALimit * growthRate) / 100);

                            // user529Plan = user529Plan + (user529Plan *
                            // growthRate) / 100;

                        } else {

                            // ////System.out.println("Ravi :: userIRALimit=
                            // "+iraLimit);

                            userIRALimit = iraLimit + (userIRALimit + (userIRALimit * growthRate) / 100);

                            tempExcess = tempExcess - iraLimit;

                            // ////System.out.println("tempExcess :: user
                            // "+tempExcess);

                            if (fillingExemtion.getJSONObject(i).getString("fillingStatus").equals(
                                    "Married Filing Jointly") && spouse_income.getJSONObject(i).getDouble("value") == 0)

                            {

                                user529Plan = 0;

                                spouseIRALimit = tempExcess + (spouseIRALimit + (spouseIRALimit * growthRate) / 100);

                            }

                            userTempExcess = tempExcess;

                        }

                    }

                    // ////System.out.println("Ranjitha ::spouse_income outside
                    // tempExcess user401k "+user401k+"user 529
                    // "+user529Plan+"spouseIRALimit"+spouseIRALimit+"userTempExcess.."+userTempExcess);

                    if (spouse_income.length() > 0
                            && fillingExemtion.getJSONObject(i).getString("fillingStatus")
                            .equals("Married Filing Jointly")
                            && spouse_income.getJSONObject(i).getDouble("value") != 0) {

                        if (i != 0) {

                            spouse401k = assets.getJSONObject(i - 1).getDouble("spouse401k");

                            spouseIRALimit = assets.getJSONObject(i - 1).getDouble("spouseIRA");

                            spouse529Plan = assets.getJSONObject(i - 1).getDouble("spouse529Plan");

                        } else {

                            spouse401k = user.getSpouse401k() + spouseSavedAmount;

                            spouseIRALimit = user.getSpouseIRA() + spouseIraSavedAmount;

                            spouse529Plan = user.getSpousePlan529();

                            spouse529Plan = (spouse529Plan + (spouse529Plan * growthRate) / 100);

                            spouseRothIRA = user.getSpouseRothIra();

                        }

                        if (nonTaxableForLimits == 0 && i != 0)

                        {

                            spouse529Plan = (spouse529Plan + (spouse529Plan * growthRate) / 100);

                        }

                        spouseFourNotOne = limitsTemp.getDouble("spouse401k");

                        spouseIraLimit = limitsTemp.getDouble("spouseIRA");

                        if (i != 0)

                        {

                            spouse529Limit = limitSpouse529;

                        }

                        // ////System.out.println("Ravi::::
                        // spouse529Limit=="+spouse529Limit);

                        // spouse529Limit=limitsTemp.getDouble("spouse529Plan");

                        if (spouseTempTaxable <= spouseFourNotOne) {

                            // ////System.out.println("Ravi ::spouse401k=
                            // "+spouseTempTaxable);

                            spouse401k = spouseTempTaxable + (spouse401k + (spouse401k * growthRate) / 100);

                            spouseIRALimit = spouseIRALimit + (spouseIRALimit * growthRate) / 100;

                            // spouse529Plan = spouse529Plan + (spouse529Plan *
                            // growthRate) / 100;

                        } else {

                            // ////System.out.println("Ravi ::spouse401k=
                            // "+spouseFourNotOne);

                            spouse401k = spouseFourNotOne + (spouse401k + (spouse401k * growthRate) / 100);

                            tempExcess = spouseTempTaxable - spouseFourNotOne;

                            if (userAge >= 50 && spouseAge < 50)

                            {

                                if (nonTaxableForLimits < fourNotOneLimit && spouseTempTaxable > spouseFourNotOne)

                                {

                                    user401k = user401k + tempExcess;

                                    spouseIRALimit = (spouseIRALimit + (spouseIRALimit * growthRate) / 100);

                                }

                                // ////System.out.println("Ranjitha before age
                                // variation userIRALimit "+user401k);

                                if (tempExcess > spouseIraLimit && spouseIraLimit != 0)

                                {

                                    spouseIRALimit = spouseIraLimit
                                            + (spouseIRALimit + (spouseIRALimit * growthRate) / 100);

                                    tempExcess = tempExcess - spouseIraLimit;

                                    // ////System.out.println("spouseIRALimit..."+spouseIRALimit+"tempExcess.."+tempExcess+"userIRALimit.."+userIRALimit);

                                    userIRALimit = userIRALimit + tempExcess;

                                    // ////System.out.println("Ranjitha after age
                                    // variation userIRALimit"+userIRALimit);

                                }

                            }

                            else {

                                if (tempExcess < spouseIraLimit) {

                                    // ////System.out.println("Ravi
                                    // ::spouseIRALimit= 1"+tempExcess);

                                    spouseIRALimit = tempExcess
                                            + (spouseIRALimit + (spouseIRALimit * growthRate) / 100);

                                    // spouse529Plan = spouse529Plan +
                                    // (spouse529Plan * growthRate) / 100;

                                } else {

                                    // ////System.out.println("Ravi
                                    // ::spouseIRALimit= 2"+spouseIraLimit);

                                    spouseIRALimit = spouseIraLimit
                                            + (spouseIRALimit + (spouseIRALimit * growthRate) / 100);

                                    tempExcess = tempExcess - spouseIraLimit;

                                    // ////System.out.println("tempExcess.."+tempExcess);

                                    if (tempExcess > 0 && spouse401k == 0)

                                    {

                                        // ////System.out.println("Ranjitha ::
                                        // inside user401k "+user401k +"
                                        // "+userIRALimit);

                                        if (nonTaxableForLimits < fourNotOneLimit)

                                        {

                                            final double tempVal = fourNotOneLimit - nonTaxableForLimits;

                                            user401k = user401k + tempVal;

                                            tempExcessNew = tempExcess - tempVal;

                                            // ////System.out.println("tempExcessNew
                                            // :: "+tempExcessNew);

                                        }

                                        if (tempExcessNew <= iraLimit)

                                        {

                                            userIRALimit = userIRALimit + tempExcessNew;

                                        }

                                    }

                                }

                            }

                        }

                        if (userTempExcess > 0 && user401k == 0)

                        {

                            // ////System.out.println("Ranjitha :: inside spouse401k
                            // "+spouse401k +" "+spouseIRALimit);

                            if (spouseTempTaxable < spouseFourNotOne)

                            {

                                final double tempSpouseVal = spouseFourNotOne - spouseTempTaxable;

                                spouse401k = spouse401k + tempSpouseVal;

                                spouseTempExcess = userTempExcess - tempSpouseVal;

                            }

                            if (spouseTempExcess <= spouseIraLimit)

                            {

                                spouseIRALimit = spouseIRALimit + spouseTempExcess;

                            }

                        }

                    }

                    // ////System.out.println("Ranjitha :: All contributions
                    // nonTaxableForLimits"+nonTaxableForLimits+
                    // "spouse401k"+spouse401k+"spouseIRALimit"+spouseIRALimit+"spouse529Plan"+spouse529Plan+"user401k"+user401k
                    // +"userira" + userIRALimit + "user529Plan" + user529Plan);

                    // ////System.out.println("Ravi:::::year"+year+"---user529Plan="+user529Plan+"..spouse529Plan="+spouse529Plan);

                    if (deductions != null) {

                        user529Plan = user529Plan - deductions.getJSONObject(i).getDouble("collegeGoalNontaxable");

                        // ////System.out.println("deductions===user529Plan==="+user529Plan);

                        if (user529Plan < 0) {

                            spouse529Plan = spouse529Plan + user529Plan;

                            user529Plan = 0;

                            // ////System.out.println("deductions===spouse529Plan==="+spouse529Plan);

                        }

                        // ////System.out.println("Ranjitha :: college user529Plan
                        // one "+user529Plan+"spouse529Plan.."+spouse529Plan);

                    }

                    // Money sweeping into non taxable buckets ends here...

                    tax.getJSONObject(i).put("federalTax", ftempEnd).put("userSSTax", tempTax.getDouble("userSSTax"))
                    .put("fICAMedicareTax", tempTax.getDouble("fICAMedicareTax"))
                    .put("spouseSSTax", tempTax.getDouble("spouseSSTax"))
                    .put("stateTax", tempTax.getDouble("stateTax"));

                    if (deductions != null) {

                        if (assets.getJSONObject(i).getInt("year") == year
                                && deductions.getJSONObject(i).getInt("year") == year) {

                            taxableInvestmentAmount = taxableInvestmentAmount
                                    + ((growthRate * taxableInvestmentAmount) / 100) + excessNonTax
                                    + limitForTaxableAmount + limitForCollegeGoalTaxable
                                    - deductions.getJSONObject(i).getDouble("taxable");

                            if (taxableInvestmentAmount < 0) {

                                remaining_amount = remaining_amount + taxableInvestmentAmount;

                                taxableInvestmentAmount = 0;

                                if (remaining_amount < 0) {

                                    goalFeasibility = false;

                                    statusMsg.append("Insufficient balance in taxable account");

                                    ////System.out.println("Insufficient balance in taxable account");

                                    break;

                                }

                            }

                            taxableInvestmentAmount = taxableInvestmentAmount
                                    - deductions.getJSONObject(i).getDouble("collegeGoalTaxable");

                            if (taxableInvestmentAmount < 0) {

                                nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + taxableInvestmentAmount;

                                taxableInvestmentAmount = 0;

                                goalFeasibility = false;

                                statusMsg.append("Insufficient balance in non-taxable account for college goal");

                                ////System.out.println("Insufficient balance in non-taxable account for college goal");

                                break;

                            }

                            if (nonTaxableInvestmentAmount < 0 || user529Plan < 0 || spouse529Plan < 0) {

                                goalFeasibility = false;

                                statusMsg.append("Insufficient balance in non-taxble account");

                                ////System.out.println("Insufficient balance in account");

                                break;

                            }

                            // ////System.out.println("saving..." + remaining_amount
                            // + "taxable..." + taxableInvestmentAmount +
                            // "nontaxbale..." + nonTaxableInvestmentAmount);

                        } else {

                            break;

                        }

                    } else {

                        if (assets.getJSONObject(i).getInt("year") == year) {

                            taxableInvestmentAmount = taxableInvestmentAmount
                                    + ((growthRate * taxableInvestmentAmount) / 100) + excessNonTax
                                    + limitForTaxableAmount + limitForCollegeGoalTaxable;

                            if (taxableInvestmentAmount < 0) {

                                remaining_amount = remaining_amount + taxableInvestmentAmount;

                                taxableInvestmentAmount = 0;

                                if (remaining_amount < 0) {

                                    goalFeasibility = false;

                                    statusMsg.append("Insufficient balance in taxable account");

                                    // ////System.out.println("Insufficient balance
                                    // in taxable account");

                                    break;

                                }

                            }

                            // ////System.out.println("saving..." + remaining_amount
                            // + "taxable..." + taxableInvestmentAmount +
                            // "nontaxbale..." + nonTaxableInvestmentAmount);

                        } else {

                            break;

                        }

                    }

                    // ////System.out.println("Ranjtha :: before db "+user401k+" "+
                    // userIRALimit+" "+spouse401k+"
                    // "+spouseIRALimit+"user529Plan
                    // "+user529Plan+"spouse529Plan "+spouse529Plan);

                    nonTaxableInvestmentIncome = (nonTaxableInvestmentAmount * (portfolioDividend + portfolioInterest))
                            / 100;

                    taxableInvestmentIncome = (taxableInvestmentAmount * (portfolioDividend + portfolioInterest) * 0.85)
                            / 100;

                    assets.getJSONObject(i).put("nonTaxableInvestmentIncome", nonTaxableInvestmentIncome);

                    assets.getJSONObject(i).put("taxableInvestmentIncome", taxableInvestmentIncome);

                    assets.getJSONObject(i)
                    .put("nontaxable_investment_amount",
                            (user401k + user401kContribution + userIRALimit + userRothIRA + user529Plan
                                    + spouse401k + spouse401kContribution + spouseIRALimit + spouseRothIRA
                                    + spouse529Plan));

                    assets.getJSONObject(i).put("taxable_investment_amount", taxableInvestmentAmount);

                    assets.getJSONObject(i).put("savings", remaining_amount);

                    assets.getJSONObject(i).put("user401k", user401k + user401kContribution);

                    assets.getJSONObject(i).put("userIRA", userIRALimit);

                    assets.getJSONObject(i).put("userRothIRA", userRothIRA);

                    assets.getJSONObject(i).put("user529Plan", user529Plan);

                    assets.getJSONObject(i).put("spouse401k", spouse401k + spouse401kContribution);

                    assets.getJSONObject(i).put("spouseIRA", spouseIRALimit);

                    assets.getJSONObject(i).put("spouseRothIRA", spouseRothIRA);

                    assets.getJSONObject(i).put("spouse529Plan", spouse529Plan);

                    limitForSavingAcount = initialLimit;

                    ////System.out.println("mahi ::::"+assets.getJSONObject(i));

                    age++;

                    if (spouseAge != 0) {

                        spouseAge++;

                    }

                    userAge = age;

                } else {
                    // =======================Retirement year calculation starts
                    // here=================================================
                    JSONObject responseFromGrowthRate = new JSONObject();
                    final InvestmentPortfolioImpl investmentPortfolioImpl = new InvestmentPortfolioImpl();
                    if ((userAge < 70 && spouseAge < 70) && (assets.getJSONObject(i).getInt("year") == userStartYear
                            || (assets.getJSONObject(i).getInt("year") == spouseStartYear))) {

                        double saving = assets.getJSONObject(i - 1).getDouble("savings");
                        double savingLimite = 0;

                        if (limits != null) {
                            if (emergencyFundFlag == false) {
                                savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2)
                                        + limits.getJSONObject(i).getInt("saving");
                            } else {

                                if (emergencyType.equals("Fix Amount")) {
                                    savingLimite = (emergencyFundAmt) + limits.getJSONObject(i).getInt("saving");
                                } else if (emergencyType.equals("Expense")) {

                                    savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                            * (Integer.parseInt(monthsOfExpense)))
                                            + limits.getJSONObject(i).getInt("saving");
                                } else if (emergencyType.equals("Income")) {

                                    savingLimite = (income.getJSONObject(i).getDouble("value") / 12
                                            * (Integer.parseInt(monthsOfIncome)))
                                            + limits.getJSONObject(i).getInt("saving");
                                }

                            }
                        } else {
                            if (emergencyFundFlag == false) {
                                savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2);
                            } else {

                                if (emergencyType.equals("Fix Amount")) {
                                    savingLimite = (emergencyFundAmt);
                                } else if (emergencyType.equals("Expense")) {

                                    savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                            * (Integer.parseInt(monthsOfExpense)));
                                } else if (emergencyType.equals("Income")) {

                                    savingLimite = (income.getJSONObject(i).getDouble("value") / 12
                                            * (Integer.parseInt(monthsOfIncome)));
                                }

                            }
                        }
                        saving = savingLimite;
                        if (maritalStatus.equals("Yes")) {
                            spouseSSB = spouse_income.getJSONObject(i).getDouble("value");
                            yearlySSB = spouse_income.getJSONObject(i).getDouble("value")
                                    + userIncome.getJSONObject(i).getDouble("value");

                            responseFromGrowthRate = investmentPortfolioImpl.growthRate(riskScore, 2, userAge,
                                    MongoDBConnection.investmentPortfolioData);
                        } else {

                            yearlySSB = userIncome.getJSONObject(i).getDouble("value");
                            responseFromGrowthRate = investmentPortfolioImpl.growthRate(riskScore, 1, userAge,
                                    MongoDBConnection.investmentPortfolioData);
                        }
                        growthRate = responseFromGrowthRate.getDouble("growthRate") / 100;
                        //////System.out.println("growthRate----------="+growthRate);
                        userSSB = userIncome.getJSONObject(i).getDouble("value");

                        final JSONObject federalJson = calRetirementTax(
                                fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                fillingExemtion.getJSONObject(i).getString("fillingStatus"), fin_id, incomeRothIRA,
                                yearlySSB, userExpense.getJSONObject(i).getDouble("totalExpense"), user_id, 0.0,
                                userAge, spouseAge, userSSB, spouseSSB, 0, goal_id, year, maritalStatus, kidBirthYear,
                                state, kidcount, registrationYear, childArray, houseStatus, houseValue, housing_expense,
                                startYear, remainingMortgageOriginal, remainingMortgageInterestRate, spouseBirthYear,
                                userBirthYear, spouseAgeInFinPlan, spouseRetirementStartYear, userRetirementStartYear,
                                stateTaxValue,equity,houseAppreciationRate,registrationHouseStatus);

                        final double accessMony = federalJson.getDouble("withdrawalFromRetirementAccounts") - 0
                                - federalJson.getDouble("federalTax") - federalJson.getDouble("stateTax")
                                - federalJson.getDouble("userSSTax") - federalJson.getDouble("spouseSSTax")
                                - federalJson.getDouble("fICAMedicareTax");
                        if (accessMony >= 0 && (yearlySSB > userExpense.getJSONObject(i).getDouble("totalExpense"))) {
                            assets.getJSONObject(i).put("taxable_investment_amount",
                                    assets.getJSONObject(i - 1).getDouble("taxable_investment_amount") + accessMony
                                    + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                            * growthRate));
                            if (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") <= 0) {
                                assets.getJSONObject(i).put("user401k", 0);
                                assets.getJSONObject(i).put("spouse401k", 0);
                                assets.getJSONObject(i).put("userIRA", 0);
                                assets.getJSONObject(i).put("spouseIRA", 0);
                                assets.getJSONObject(i).put("nontaxable_investment_amount", 0);
                                assets.getJSONObject(i).put("spouseRothIRA", 0);
                                assets.getJSONObject(i).put("userRothIRA", 0);

                            } else {

                                assets.getJSONObject(i).put("user401k",
                                        assets.getJSONObject(i - 1).getDouble("user401k")
                                        + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate);
                                assets.getJSONObject(i).put("spouse401k",
                                        assets.getJSONObject(i - 1).getDouble("spouse401k")
                                        + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate);
                                assets.getJSONObject(i).put("userIRA", assets.getJSONObject(i - 1).getDouble("userIRA")
                                        + assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate);
                                assets.getJSONObject(i).put("spouseIRA",
                                        assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                        + assets.getJSONObject(i - 1).getDouble("spouseIRA") * growthRate);
                                assets.getJSONObject(i).put("spouseRothIRA", assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                        + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);
                                assets.getJSONObject(i).put("userRothIRA", assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                        + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);
                                if (userStartYearForCap > assets.getJSONObject(i).getInt("year")) {
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i).getDouble("user401k") + user401kContribution);

                                }

                                if (spouseStartYearForCap > assets.getJSONObject(i).getInt("year")) {
                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i).getDouble("spouse401k") + spouse401kContribution);

                                }
                                assets.getJSONObject(i).put("nontaxable_investment_amount",
                                        assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i).getDouble("spouseIRA")
                                        + assets.getJSONObject(i).getDouble("spouseRothIRA")
                                        + assets.getJSONObject(i).getDouble("userRothIRA"));
                            }

                            assets.getJSONObject(i).put("savings", saving);
                            tax.getJSONObject(i).put("federalTax", federalJson.getDouble("federalTax"));
                            tax.getJSONObject(i).put("fICAMedicareTax", federalJson.getDouble("fICAMedicareTax"));
                            tax.getJSONObject(i).put("fICASocialSecurityTax",
                                    federalJson.getDouble("fICASocialSecurityTax"));
                            tax.getJSONObject(i).put("userSSTax", federalJson.getDouble("userSSTax"));
                            tax.getJSONObject(i).put("stateTax", federalJson.getDouble("stateTax"));
                            tax.getJSONObject(i).put("spouseSSTax", federalJson.getDouble("spouseSSTax"));
                            //////System.out.println("rothira in else==="+ assets.getJSONObject(i).getDouble("userRothIRA"));
                            //////System.out.println("rothira in else=previous=="+ assets.getJSONObject(i-1).getDouble("userRothIRA"));
                            //////System.out.println("ira in else==="+ assets.getJSONObject(i).getDouble("userIRA"));
                            //////System.out.println("ira in else=previous=="+ assets.getJSONObject(i-1).getDouble("userIRA"));

                        } else {
                            final double nontaxtableWithdrawlAmount = federalJson.getDouble("incomeIRA");
                            //////System.out.println("nontaxtableWithdrawlAmount---aparna"+nontaxtableWithdrawlAmount);
                            //////System.out.println("iraa---aparna"+assets.getJSONObject(i - 1).getDouble("userIRA"));
                            if (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") <= 0) {
                                assets.getJSONObject(i).put("user401k", 0);
                                assets.getJSONObject(i).put("spouse401k", 0);
                                assets.getJSONObject(i).put("userIRA", 0);
                                assets.getJSONObject(i).put("spouseIRA", 0);
                                assets.getJSONObject(i).put("spouseRothIRA", 0);
                                assets.getJSONObject(i).put("userRothIRA", 0);
                                assets.getJSONObject(i).put("nontaxable_investment_amount", 0);

                            } else {

                                if (assets.getJSONObject(i - 1).getDouble("user401k") > 0
                                        && assets.getJSONObject(i - 1).getDouble("spouse401k") > 0) {

                                    if (assets.getJSONObject(i).getInt("year") < spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") >= userStartYear)

                                    {

                                        assets.getJSONObject(i).put("user401k",
                                                (assets.getJSONObject(i - 1).getDouble("user401k")
                                                        - nontaxtableWithdrawlAmount) * 1.025);

                                        assets.getJSONObject(i).put("spouse401k",
                                                assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate+spouse401kContribution);

                                    }

                                    else if (assets.getJSONObject(i).getInt("year") >= spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") < userStartYear)

                                    {

                                        assets.getJSONObject(i).put("spouse401k",
                                                (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                        - nontaxtableWithdrawlAmount) * 1.025);

                                        assets.getJSONObject(i).put("user401k",
                                                assets.getJSONObject(i - 1).getDouble("user401k")
                                                + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate+user401kContribution);

                                    }

                                    else if (assets.getJSONObject(i).getInt("year") >= spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") >= userStartYear)

                                    {

                                        assets.getJSONObject(i).put("user401k",
                                                (assets.getJSONObject(i - 1).getDouble("user401k")
                                                        - nontaxtableWithdrawlAmount / 2) * 1.025);

                                        assets.getJSONObject(i).put("spouse401k",
                                                (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                        - nontaxtableWithdrawlAmount / 2) * 1.025);

                                    }

                                    assets.getJSONObject(i).put("userIRA",
                                            assets.getJSONObject(i - 1).getDouble("userIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate);

                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseIRA") * growthRate);


                                    assets.getJSONObject(i).put("spouseRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);

                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);

                                } else if (assets.getJSONObject(i - 1).getDouble("spouse401k") > 0
                                        && assets.getJSONObject(i - 1).getDouble("user401k") <= 0) {
                                    if (assets.getJSONObject(i).getInt("year") >= spouseStartYear) {
                                        assets.getJSONObject(i).put("spouse401k",
                                                (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                        - nontaxtableWithdrawlAmount) * 1.025);
                                    }
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("userIRA",
                                            assets.getJSONObject(i - 1).getDouble("userIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate);
                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseIRA") * growthRate);
                                    assets.getJSONObject(i).put("spouseRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);

                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);
                                } else if (assets.getJSONObject(i - 1).getDouble("spouse401k") <= 0
                                        && assets.getJSONObject(i - 1).getDouble("user401k") > 0) {
                                    assets.getJSONObject(i).put("spouse401k", 0);
                                    if (assets.getJSONObject(i).getInt("year") >= userStartYear) {
                                        assets.getJSONObject(i).put("user401k",
                                                (assets.getJSONObject(i - 1).getDouble("user401k")
                                                        - nontaxtableWithdrawlAmount) * 1.025);
                                    }
                                    assets.getJSONObject(i).put("userIRA",
                                            assets.getJSONObject(i - 1).getDouble("userIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate);
                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseIRA") * growthRate);
                                    assets.getJSONObject(i).put("spouseRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);

                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);
                                } else if (assets.getJSONObject(i - 1).getDouble("userIRA") > 0
                                        && assets.getJSONObject(i - 1).getDouble("spouseIRA") > 0) {

                                    if (assets.getJSONObject(i).getInt("year") < spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") >= userStartYear)

                                    {

                                        assets.getJSONObject(i).put("userIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                        - nontaxtableWithdrawlAmount) * 1.025);

                                    }

                                    else if (assets.getJSONObject(i).getInt("year") >= spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") < userStartYear)

                                    {

                                        assets.getJSONObject(i).put("spouseIRA",
                                                (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                        - nontaxtableWithdrawlAmount) * 1.025);

                                    }

                                    else if (assets.getJSONObject(i).getInt("year") >= spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") >= userStartYear)

                                    {

                                        assets.getJSONObject(i).put("userIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                        - nontaxtableWithdrawlAmount / 2) * 1.025);

                                        assets.getJSONObject(i).put("spouseIRA",
                                                (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                        - nontaxtableWithdrawlAmount / 2) * 1.025);

                                    }

                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i - 1).getDouble("spouse401k")
                                            + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate);

                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i - 1).getDouble("user401k")
                                            + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate);

                                } else if (assets.getJSONObject(i - 1).getDouble("spouseIRA") > 0
                                        && assets.getJSONObject(i - 1).getDouble("userIRA") <= 0) {

                                    if (assets.getJSONObject(i).getInt("year") >= spouseStartYear) {
                                        assets.getJSONObject(i).put("spouseIRA",
                                                (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                        - nontaxtableWithdrawlAmount) * 1.025);
                                    }
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i - 1).getDouble("user401k")
                                            + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate);

                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i - 1).getDouble("spouse401k")
                                            + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate);

                                    assets.getJSONObject(i).put("userIRA", 0);
                                } else if (assets.getJSONObject(i - 1).getDouble("spouseIRA") <= 0
                                        && assets.getJSONObject(i - 1).getDouble("userIRA") > 0) {
                                    if (assets.getJSONObject(i).getInt("year") >= userStartYear) {
                                        assets.getJSONObject(i).put("userIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                        - nontaxtableWithdrawlAmount) * 1.025);
                                    }
                                    ////System.out.println("USERIRA+++"+assets.getJSONObject(i - 1).getDouble("userRothIRA"));
                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);

                                    assets.getJSONObject(i).put("spouseRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i - 1).getDouble("user401k")
                                            + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate);
                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i - 1).getDouble("spouse401k")
                                            + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate);
                                    assets.getJSONObject(i).put("spouseIRA", 0);
                                    //////System.out.println("SPOUSE++++++"+assets.getJSONObject(i).getDouble("userRothIRA"));


                                } else {
                                    statusMsg.append("Insufficient balance in Non taxable account");
                                    responseObj.put("status", "fail");
                                    responseObj.put("statusMsg", statusMsg);
                                    return responseObj;
                                }

                                assets.getJSONObject(i).put("nontaxable_investment_amount",
                                        assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i).getDouble("spouseIRA")
                                        + assets.getJSONObject(i).getDouble("userRothIRA")
                                        + assets.getJSONObject(i).getDouble("spouseRothIRA"));
                            }
                            assets.getJSONObject(i).put("taxable_investment_amount",
                                    assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                    + assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                    * growthRate);
                            assets.getJSONObject(i).put("savings", saving);
                            tax.getJSONObject(i).put("federalTax", federalJson.getDouble("federalTax"));
                            tax.getJSONObject(i).put("fICAMedicareTax", federalJson.getDouble("fICAMedicareTax"));
                            tax.getJSONObject(i).put("fICASocialSecurityTax",
                                    federalJson.getDouble("fICASocialSecurityTax"));
                            tax.getJSONObject(i).put("userSSTax", federalJson.getDouble("userSSTax"));
                            tax.getJSONObject(i).put("stateTax", federalJson.getDouble("stateTax"));
                            tax.getJSONObject(i).put("spouseSSTax", federalJson.getDouble("spouseSSTax"));
                            //////System.out.println("assets appp=="+assets.getJSONObject(i-1));
                            //////System.out.println("iraa 1---aparna"+assets.getJSONObject(i).getDouble("userIRA"));
                            //////System.out.println("rothIra====apar"+assets.getJSONObject(i - 1).getDouble("userRothIRA"));
                            //////System.out.println("roth ira previous==="+	assets.getJSONObject(i).getDouble("userRothIRA"));
                        }
                        //----------------------new code for529------------------------------
                        assets.getJSONObject(i).put("user529Plan", assets.getJSONObject(i-1).getDouble("user529Plan")+assets.getJSONObject(i-1).getDouble("user529Plan")*growthRate);
                        assets.getJSONObject(i).put("spouse529Plan", assets.getJSONObject(i-1).getDouble("spouse529Plan")+assets.getJSONObject(i-1).getDouble("spouse529Plan")*growthRate);
                        /*assets.getJSONObject(i).put("nontaxable_investment_amount",
								assets.getJSONObject(i).getDouble("nontaxable_investment_amount")
								+assets.getJSONObject(i).getDouble("spouse529Plan")
								 + assets.getJSONObject(i).getDouble("user529Plan"));*/
                        //////System.out.println("user------"+assets.getJSONObject(i-1).getDouble("user529Plan"));
                        //////System.out.println("user------"+assets.getJSONObject(i).getDouble("user529Plan"));
                        if (assets.getJSONObject(i).getInt("year") == userStartYear) {
                            userStartYear++;

                        }
                        if (assets.getJSONObject(i).getInt("year") == spouseStartYear) {

                            spouseStartYear++;
                        }
                        if (spouseAge != 0) {
                            spouseAge++;
                        }
                        if (userAge != 0) {
                            userAge++;
                        }
                    } else if (((userAge >= 70 && userAge <= 99) || (spouseAge >= 70 && userAge <= 99))) {
                        // -------------------if user age is >
                        // 70---------------------------------
                        double saving = assets.getJSONObject(i - 1).getDouble("savings");
                        double savingLimite = 0;

                        if (limits != null) {
                            if (emergencyFundFlag == false) {
                                savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2)
                                        + limits.getJSONObject(i).getInt("saving");
                            } else {

                                if (emergencyType.equals("Fix Amount")) {
                                    savingLimite = (emergencyFundAmt) + limits.getJSONObject(i).getInt("saving");
                                } else if (emergencyType.equals("Expense")) {

                                    savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                            * (Integer.parseInt(monthsOfExpense)))
                                            + limits.getJSONObject(i).getInt("saving");
                                } else if (emergencyType.equals("Income")) {

                                    savingLimite = (income.getJSONObject(i).getDouble("value") / 12
                                            * (Integer.parseInt(monthsOfIncome)))
                                            + limits.getJSONObject(i).getInt("saving");
                                }

                            }
                        } else {
                            if (emergencyFundFlag == false) {
                                savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2);
                            } else {

                                if (emergencyType.equals("Fix Amount")) {
                                    savingLimite = (emergencyFundAmt);
                                } else if (emergencyType.equals("Expense")) {

                                    savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                            * (Integer.parseInt(monthsOfExpense)));
                                } else if (emergencyType.equals("Income")) {

                                    savingLimite = (income.getJSONObject(i).getDouble("value") / 12
                                            * (Integer.parseInt(monthsOfIncome)));
                                }

                            }
                        }
                        saving = savingLimite;
                        contributionAmount = 0;

                        final double usersSSB = userIncome.getJSONObject(i).getDouble("value");
                        if (maritalStatus.equals("Yes")) {
                            spouseSSB = spouse_income.getJSONObject(i).getDouble("value");
                            yearlySSB = spouseSSB + usersSSB;

                            responseFromGrowthRate = investmentPortfolioImpl.growthRate(riskScore, 2, userAge,
                                    MongoDBConnection.investmentPortfolioData);
                        } else {
                            // ////System.out.println("Krishnan:: userIncome:: " +
                            // yearlySSB);
                            yearlySSB = userIncome.getJSONObject(i).getDouble("value");
                            responseFromGrowthRate = investmentPortfolioImpl.growthRate(riskScore, 1, userAge,
                                    MongoDBConnection.investmentPortfolioData);
                        }
                        growthRate = responseFromGrowthRate.getDouble("growthRate") / 100;
                        //////System.out.println("growthRate----------="+growthRate);
                        if (userIncome.length() <= i) {
                            userSSB = userIncome.getJSONObject(i - 1).getDouble("value");
                        } else {
                            userSSB = userIncome.getJSONObject(i).getDouble("value");
                        }

                        double userMinPercentage = 0;
                        double spouseMinPercentage = 0;
                        double minimumWithdrawal = 0;
                        boolean ageFlage = false;
                        // ////System.out.println("Mahi : spouseage in
                        // calingine:"+spouseAge);
                        if (spouseAge == 0 || ((spouseAge - userAge) < 10 && (userAge - spouseAge) < 10)) {
                            if (maritalStatus.equals("Yes") && spouseAge != 0) {
                                if (MongoDBConnection.distributionModelData.getMinimumPercentageWithdrawal()
                                        .containsKey("" + spouseAge)) {
                                    spouseMinPercentage = (MongoDBConnection.distributionModelData
                                            .getMinimumPercentageWithdrawal().get("" + spouseAge) / 100);
                                }
                            }
                            if (MongoDBConnection.distributionModelData.getMinimumPercentageWithdrawal()
                                    .containsKey("" + userAge)) {
                                userMinPercentage = (MongoDBConnection.distributionModelData
                                        .getMinimumPercentageWithdrawal().get("" + userAge) / 100);
                            }
                        } else {
                            ageFlage = true;
                            if(spouseAge>=70 && spouseAge<=100) {
                                // System.out.println("spouseeee agee "+spouseAge);
                                spouseMinPercentage = (MongoDBConnection.distributionModelData
                                        .getMinimumPercentageWithdrawal().get("" + spouseAge) / 100);
                            }
                            else {
                                spouseMinPercentage=0;
                            }

                            if(userAge>=70 && userAge<=100){
                                userMinPercentage = (MongoDBConnection.distributionModelData
                                        .getMinimumPercentageWithdrawal().get("" + userAge) / 100);
                            }
                            else {
                                userMinPercentage=0;
                            }
                            /*userMinPercentage = MongoDBConnection.distributionModelData.getMinDistribution()
                                                         .get("" + userAge).get(spouseAge - 30);*/
                        }
                        double user401kWithdrawal = 0;
                        double spouse401kWithdrawal = 0;
                        double userIRAWithdrawal = 0;
                        double spouseIRAWithdrawal = 0;
                        double userRouthIRAWithdrawal = 0;
                        double spouseRouthIRAWithdrawal = 0;
                        final double user529PlanWithdrawal = 0;
                        final double spouse529PlanWithdrawal = 0;

                        /* if (ageFlage) {
                            spouseMinPercentage = userMinPercentage;
                        }*/

                        user401kWithdrawal = (assets.getJSONObject(i - 1).getDouble("user401k") * userMinPercentage);
                        spouse401kWithdrawal = (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                * spouseMinPercentage);
                        userIRAWithdrawal = (assets.getJSONObject(i - 1).getDouble("userIRA") * userMinPercentage);
                        spouseIRAWithdrawal = (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                * spouseMinPercentage);
                        userRouthIRAWithdrawal = (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                * userMinPercentage);
                        spouseRouthIRAWithdrawal = (assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                * spouseMinPercentage);
                        minimumWithdrawal = user401kWithdrawal + spouse401kWithdrawal + userIRAWithdrawal
                                + spouseIRAWithdrawal + userRouthIRAWithdrawal + spouseRouthIRAWithdrawal
                                + user529PlanWithdrawal + spouse529PlanWithdrawal;

                        double nontaxable_investment_amount = 0;
                        JSONObject federalJson = new JSONObject();

                        if (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") > 0) {

                            if (userMinPercentage != 0 || spouseMinPercentage != 0) {
                                if (maritalStatus.equals("Yes")) {
                                    if (spouseMinPercentage != 0 && userMinPercentage != 0) {
                                        if (userAge >= 70) {
                                            assets.getJSONObject(i).put("user401k",
                                                    (assets.getJSONObject(i - 1).getDouble("user401k")
                                                            - user401kWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                            - userRouthIRAWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("userIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                            - userIRAWithdrawal) * 1.025);


                                        } else

                                        {

                                            assets.getJSONObject(i).put("user401k",
                                                    (assets.getJSONObject(i - 1).getDouble("user401k") - 0) * 1.025);

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userRothIRA") - 0) * 1.025);

                                            assets.getJSONObject(i).put("userIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userIRA") - 0) * 1.025);

                                        }

                                        if (spouseAge >= 70)

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                            - spouse401kWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                                            - spouseRouthIRAWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                            - spouseIRAWithdrawal) * 1.025);

                                        }

                                        else

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    (assets.getJSONObject(i - 1).getDouble("spouse401k") - 0) * 1.025);

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseRothIRA") - 0)
                                                    * 1.025);

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseIRA") - 0) * 1.025);

                                        }

                                        assets.getJSONObject(i).put("nontaxable_investment_amount",
                                                nontaxable_investment_amount);

                                    } else if (spouseMinPercentage == 0 && userMinPercentage != 0) {

                                        if (userAge >= 70)

                                        {

                                            assets.getJSONObject(i).put("user401k",
                                                    (assets.getJSONObject(i - 1).getDouble("user401k")
                                                            - user401kWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                            - userRouthIRAWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("userIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                            - userIRAWithdrawal) * 1.025);

                                        } else

                                        {

                                            assets.getJSONObject(i).put("user401k",
                                                    (assets.getJSONObject(i - 1).getDouble("user401k") - 0) * 1.025);

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userRothIRA") - 0) * 1.025);

                                            assets.getJSONObject(i).put("userIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userIRA") - 0) * 1.025);

                                        }
                                        // if(spouseAge>=70)

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                    + (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                            * growthRate));

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                                    + (assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                                            * growthRate));

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                    + (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                            * growthRate));

                                        }

                                    } else {

                                        // if(userAge >= 70)

                                        {

                                            assets.getJSONObject(i).put("user401k", assets.getJSONObject(i - 1)
                                                    .getDouble("user401k")
                                                    + (assets.getJSONObject(i - 1).getDouble("user401k") * growthRate));

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                    + (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                            * growthRate));

                                            assets.getJSONObject(i).put("userIRA", assets.getJSONObject(i - 1)
                                                    .getDouble("userIRA")
                                                    + (assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate));

                                        }

                                        if (spouseAge >= 70)

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                            - spouse401kWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                                            - spouseRouthIRAWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                            - spouseIRAWithdrawal) * 1.025);

                                        }

                                        else

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    (assets.getJSONObject(i - 1).getDouble("spouse401k") - 0) * 1.025);

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseRothIRA") - 0)
                                                    * 1.025);

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseIRA") - 0) * 1.025);

                                        }

                                    }
                                } else {

                                    if (userAge >= 70)

                                    {

                                        assets.getJSONObject(i).put("user401k",
                                                (assets.getJSONObject(i - 1).getDouble("user401k") - user401kWithdrawal)
                                                * 1.025);

                                        assets.getJSONObject(i).put("spouse401k", 0);

                                        assets.getJSONObject(i).put("userRothIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                        - userRouthIRAWithdrawal) * 1.025);

                                        assets.getJSONObject(i).put("spouseRothIRA", 0);

                                        assets.getJSONObject(i).put("userIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userIRA") - userIRAWithdrawal)
                                                * 1.025);

                                        assets.getJSONObject(i).put("spouseIRA", 0);

                                    }

                                }

                            }

                            if (userStartYearForCap > assets.getJSONObject(i).getInt("year")) {
                                assets.getJSONObject(i).put("user401k",
                                        assets.getJSONObject(i).getDouble("user401k") + user401kContribution);

                            }
                            if (spouseStartYearForCap > assets.getJSONObject(i).getInt("year")) {
                                assets.getJSONObject(i).put("spouse401k",
                                        assets.getJSONObject(i).getDouble("spouse401k") + spouse401kContribution);

                            }
                            nontaxable_investment_amount = assets.getJSONObject(i).getDouble("spouseIRA")
                                    + assets.getJSONObject(i).getDouble("user401k")
                                    + assets.getJSONObject(i).getDouble("spouse401k")
                                    + assets.getJSONObject(i).getDouble("userRothIRA")
                                    + assets.getJSONObject(i).getDouble("spouseRothIRA")
                                    + assets.getJSONObject(i).getDouble("userIRA");
                            assets.getJSONObject(i).put("nontaxable_investment_amount", nontaxable_investment_amount);

                            federalJson = calRetirementTax(fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                    fillingExemtion.getJSONObject(i).getString("fillingStatus"), fin_id, incomeRothIRA,
                                    yearlySSB, userExpense.getJSONObject(i).getDouble("totalExpense"), user_id,
                                    minimumWithdrawal, userAge, spouseAge, userSSB, spouseSSB, contributionAmount,
                                    goal_id, year, maritalStatus, kidBirthYear, state, kidcount, registrationYear,
                                    childArray, houseStatus, houseValue, housing_expense, startYear,
                                    remainingMortgageOriginal, remainingMortgageInterestRate, spouseBirthYear,
                                    userBirthYear, spouseAgeInFinPlan, spouseRetirementStartYear,
                                    userRetirementStartYear, stateTaxValue,equity,houseAppreciationRate,registrationHouseStatus);

                            if (federalJson.getDouble("incomeIRA") > 0) {
                                final double accessMony = federalJson.getDouble("incomeIRA");
                                if (accessMony < assets.getJSONObject(i).getDouble("user401k")) {
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i).getDouble("user401k") - (accessMony * 1.025));
                                } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i).getDouble("spouse401k") - (tmp * 1.025));

                                } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userRothIRA"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k")
                                            + assets.getJSONObject(i).getDouble("spouse401k");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k", 0);
                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i).getDouble("userRothIRA") - (tmp * 1.025));

                                } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i - 1).getDouble("spouseIRA"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k")
                                            + assets.getJSONObject(i).getDouble("spouse401k")
                                            + assets.getJSONObject(i).getDouble("userIRA");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k", 0);
                                    assets.getJSONObject(i).put("userIRA", 0);
                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i).getDouble("spouseIRA") - (tmp * 1.025));

                                } else if (accessMony > (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i).getDouble("spouseIRA"))) {
                                    goalFeasibility = false;
                                    statusMsg.append("Insufficient balance in non-taxable account");
                                    // ////System.out.println(" Hello Insufficient
                                    // balance in non-taxable
                                    // account"+assets.getJSONObject(i).getDouble("user401k")+"spouse"+assets.getJSONObject(i).getDouble("spouse401k"));
                                    break;
                                }
                                assets.getJSONObject(i).put("nontaxable_investment_amount",
                                        (assets.getJSONObject(i).getDouble("user401k")
                                                + assets.getJSONObject(i).getDouble("spouse401k")
                                                + assets.getJSONObject(i).getDouble("userIRA")
                                                + assets.getJSONObject(i).getDouble("spouseIRA")
                                                + assets.getJSONObject(i).getDouble("userRothIRA")
                                                + assets.getJSONObject(i).getDouble("spouseRothIRA")));
                                assets.getJSONObject(i).put("taxable_investment_amount",
                                        assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                        + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                                * growthRate));


                            } else {

                                double accessMony = federalJson.getDouble("withdrawalFromRetirementAccounts")
                                        - contributionAmount - federalJson.getDouble("federalTax")
                                        - federalJson.getDouble("stateTax") - federalJson.getDouble("userSSTax")
                                        - federalJson.getDouble("spouseSSTax")
                                        - federalJson.getDouble("fICAMedicareTax");
                                if (accessMony < 0) {
                                    accessMony = federalJson.getDouble("withdrawalFromRetirementAccounts");
                                }

                                if (accessMony > 0) {
                                    assets.getJSONObject(i).put("taxable_investment_amount", assets.getJSONObject(i - 1)
                                            .getDouble("taxable_investment_amount") + accessMony
                                            + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                                    * growthRate));
                                }

                            }

                            assets.getJSONObject(i).put("savings", saving);

                            if (assets.getJSONObject(i).getInt("year") == userStartYear) {
                                userStartYear++;
                            }
                            if (assets.getJSONObject(i).getInt("year") == spouseStartYear) {

                                spouseStartYear++;
                            }
                            if (spouseAge != 0) {
                                spouseAge++;
                            }
                            userAge++;

                        } else if (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") <= 0
                                && yearlySSB < userExpense.getJSONObject(i).getDouble("totalExpense")) {

                            statusMsg.append("Insufficient balance in Non taxable account");
                            responseObj.put("status", "fail");
                            responseObj.put("statusMsg", statusMsg);
                            return responseObj;
                        } else {

                            federalJson = calRetirementTax(fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                    fillingExemtion.getJSONObject(i).getString("fillingStatus"), fin_id, incomeRothIRA,
                                    yearlySSB, userExpense.getJSONObject(i).getDouble("totalExpense"), user_id,
                                    minimumWithdrawal, userAge, spouseAge, userSSB, spouseSSB, contributionAmount,
                                    goal_id, year, maritalStatus, kidBirthYear, state, kidcount, registrationYear,
                                    childArray, houseStatus, houseValue, housing_expense, startYear,
                                    remainingMortgageOriginal, remainingMortgageInterestRate, spouseBirthYear,
                                    userBirthYear, spouseAgeInFinPlan, spouseRetirementStartYear,
                                    userRetirementStartYear, stateTaxValue,equity,houseAppreciationRate,registrationHouseStatus);
                            if (federalJson.getDouble("incomeIRA") > 0) {
                                final double accessMony = federalJson.getDouble("incomeIRA");
                                if (accessMony < assets.getJSONObject(i).getDouble("user401k")) {
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i).getDouble("user401k") - accessMony
                                            + assets.getJSONObject(i).getDouble("user401k") * growthRate);
                                } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i).getDouble("spouse401k") - tmp
                                            + assets.getJSONObject(i).getDouble("spouse401k") * growthRate);

                                } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userRothIRA"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k")
                                            + assets.getJSONObject(i).getDouble("spouse401k");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k", 0);
                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i).getDouble("userRothIRA") - tmp
                                            + assets.getJSONObject(i).getDouble("userIRA") * growthRate);

                                } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i - 1).getDouble("spouseIRA"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k")
                                            + assets.getJSONObject(i).getDouble("spouse401k")
                                            + assets.getJSONObject(i).getDouble("userIRA");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k", 0);
                                    assets.getJSONObject(i).put("userIRA", 0);
                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i).getDouble("spouseIRA") - tmp
                                            + assets.getJSONObject(i).getDouble("spouseIRA") * growthRate);

                                } else if (accessMony > (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i).getDouble("spouseIRA")
                                        + assets.getJSONObject(i).getDouble("userRothIRA")
                                        + assets.getJSONObject(i).getDouble("spouseRothIRA"))) {
                                    goalFeasibility = false;
                                    statusMsg.append("Insufficient balance in non-taxable account");
                                    ////System.out.println(" Hello Insufficien balance in non-taxable account"+assets.getJSONObject(i).getDouble("user401k")+"spouse"+assets.getJSONObject(i).getDouble("spouse401k"));
                                    break;
                                }
                                assets.getJSONObject(i).put("nontaxable_investment_amount",
                                        (assets.getJSONObject(i).getDouble("user401k")
                                                + assets.getJSONObject(i).getDouble("spouse401k")
                                                + assets.getJSONObject(i).getDouble("userIRA")
                                                + assets.getJSONObject(i).getDouble("spouseIRA")
                                                + assets.getJSONObject(i).getDouble("userRothIRA")
                                                + assets.getJSONObject(i).getDouble("spouseRothIRA")));
                                assets.getJSONObject(i).put("taxable_investment_amount",
                                        assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                        + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                                * growthRate));

                            } else {

                                final double accessMony = federalJson.getDouble("withdrawalFromRetirementAccounts")
                                        - contributionAmount - federalJson.getDouble("federalTax")
                                        - federalJson.getDouble("stateTax") - federalJson.getDouble("userSSTax")
                                        - federalJson.getDouble("spouseSSTax")
                                        - federalJson.getDouble("fICAMedicareTax");

                                assets.getJSONObject(i).put("taxable_investment_amount",
                                        assets.getJSONObject(i - 1).getDouble("taxable_investment_amount") + accessMony
                                        + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                                * growthRate));
                                assets.getJSONObject(i).put("nontaxable_investment_amount", 0);
                                assets.getJSONObject(i).put("user401k", 0);
                                assets.getJSONObject(i).put("spouse401k", 0);
                                assets.getJSONObject(i).put("userRothIRA", 0);
                                assets.getJSONObject(i).put("spouseRothIRA", 0);
                                assets.getJSONObject(i).put("userIRA", 0);
                                assets.getJSONObject(i).put("spouseIRA", 0);

                            }

                            assets.getJSONObject(i).put("savings", saving);

                        }

                        if (assets.getJSONObject(i).getInt("year") == year) {
                            final double nontaxtableWithdrawlAmount = assets.getJSONObject(i).getDouble(
                                    "nontaxable_investment_amount")/*- deductions.getJSONObject(i).getDouble("taxable")*/;
                            final double nontaxtableAmount = assets.getJSONObject(i).getDouble(
                                    "taxable_investment_amount")/*- deductions.getJSONObject(i).getDouble("taxable")*/;

                            if (nontaxtableWithdrawlAmount < 0 || nontaxtableAmount < 0) {
                                goalFeasibility = false;

                                statusMsg.append("Insufficient balance in taxable account");
                                ////System.out.println("Assest going negative in retirement");
                                break;
                            } else {
                                goalFeasibility = true;

                            }
                        }
                        tax.getJSONObject(i).put("federalTax", federalJson.getDouble("federalTax"));
                        tax.getJSONObject(i).put("fICAMedicareTax", federalJson.getDouble("fICAMedicareTax"));
                        tax.getJSONObject(i).put("fICASocialSecurityTax",
                                federalJson.getDouble("fICASocialSecurityTax"));
                        tax.getJSONObject(i).put("userSSTax", federalJson.getDouble("userSSTax"));
                        tax.getJSONObject(i).put("stateTax", federalJson.getDouble("stateTax"));
                        tax.getJSONObject(i).put("spouseSSTax", federalJson.getDouble("spouseSSTax"));
                        //----------------------new code for529------------------------------

                        assets.getJSONObject(i).put("user529Plan", assets.getJSONObject(i-1).getDouble("user529Plan")+assets.getJSONObject(i-1).getDouble("user529Plan")*growthRate);
                        assets.getJSONObject(i).put("spouse529Plan", assets.getJSONObject(i-1).getDouble("spouse529Plan")+assets.getJSONObject(i-1).getDouble("spouse529Plan")*growthRate);
                        /*assets.getJSONObject(i).put("nontaxable_investment_amount",
								assets.getJSONObject(i).getDouble("nontaxable_investment_amount")
								+assets.getJSONObject(i).getDouble("spouse529Plan")
								 + assets.getJSONObject(i).getDouble("user529Plan"));*/
                        //////System.out.println("user------"+assets.getJSONObject(i-1).getDouble("user529Plan"));
                        //////System.out.println("user------"+assets.getJSONObject(i).getDouble("user529Plan"));
                    }

                }

            }

            if (goalFeasibility == true) {

                responseObj.put("status", "success");

                responseObj.put("assets", assets);

                responseObj.put("tax", tax);

                responseObj.put("statusMsg", "Goal Applied successfully");

            } else {

                responseObj.put("status", "fail");

                responseObj.put("statusMsg", statusMsg);

            }

        } catch (final Exception e) {

            e.printStackTrace();

        }

        // ////System.out.println("Elapsed time for sweeping of money engine: " +
        // (System.nanoTime() - startTime) / 1000000 + " milli seconds");

        return responseObj;

    }

    public static JSONObject sweepingOfMoneyInvestmentPortfolio1(JSONArray userIncomes, String fin_id, JSONArray income,
            JSONArray spouse_income, JSONArray userExpense, JSONArray limits, String maritalStatus, JSONArray assets,
            JSONArray tax, String user_id, JSONArray fillingExemtion, int userAge, int spouseAge,

            int emergencyFundAmt, boolean emergencyFundFlag, JSONArray deductions, JSONArray kidBirthYear,
            JSONObject dataFromRetirement, RetirementGoal goalCollectionData, JSONArray housing_expense, String emergencyType,
            String monthsOfIncome, String monthsOfExpense, boolean retirementFlag, int RiskScore,Double growthRate,Double portfolioDividend,Double portfolioInterest) {

        amountMorgage = 0;
        newYear = 0;

        newYearHouse = 0;

        amountMorgageHouse = 0;

        final Calendar cal = Calendar.getInstance();

        final int currentYear = cal.get(Calendar.YEAR);

        final JSONObject responseObj = new JSONObject();

        final StringBuilder statusMsg = new StringBuilder("Goal not fesibile because ");

        try {

            boolean goalFeasibility = true;

            final InvestmentPortfolioImpl investmentPortfolio = new InvestmentPortfolioImpl();

            final User user = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
            final FinPlan finPlanDetails = MongoDBConnection.finplancol
                    .findOne("{_id:#}", fin_id ).as(FinPlan.class);

            final int Risk_Score = RiskScore;

            double remaining_amount = user.getCash();

            double taxableInvestmentAmount = user.getTaxableInvestments();

            double nonTaxableInvestmentAmount = 0;

            if (user.getNonTaxableInvestments().equals("Yes")) {

                nonTaxableInvestmentAmount = user.getUser401k() + user.getUserIRA() + user.getUser529()
                + user.getUserRothIra() + user.getSpouse401k() + user.getSpouseIRA() + user.getSpousePlan529()
                + user.getSpouseRothIra();

            }

            final double savingInterestRate = user.getSavingInterestRate();

            double excess = 0;

            final double initialLimit = 0;

            double taxExcess = 0;

            double excessNonTax = 0;

            double maximumContributionForNonTaxable = 0;

            double riskFactor = 1;

            if (spouseAge == 0) {

                spouseAge = user.getSpouseAge();

            }

            int age = currentYear - user.getBirthYear();

            double limitForSavingAcount = 0;

            double limitForTaxableAmount = 0;

            double limitForCollegeGoalTaxable = 0;

            if (maritalStatus.equals("Yes")) {

                riskFactor = 2;

            } else {

                spouse_income = new JSONArray();

            }

            // Find the basic details for calculating the tax from user and fin
            // plan collections.

            final String state = user.getState();

            final int kidcount = user.getChildCount();

            final int spouseBirthYear = user.getSpouseBirthYear();

            final int userBirthYear = user.getBirthYear();

            final long startYear = user.getRemainingYearsMortgage();

            final double remainingMortgageOriginal = user.getRemainingMortgage();

            final double remainingMortgageInterestRate = user.getRemainingMortgageInterestRate();

            final double houseAppreciationRate=user.getHouseAppreciationRate();

            final String[] createdTs = user.getCreatedTs().split("/");

            final int registrationYear = Integer.parseInt(createdTs[0]);

            JSONArray childArray = null;

            JSONObject userJson = null;

            if (user != null) {

                userJson = new JSONObject(jsonMapper.writeValueAsString(user));

                if (!userJson.isNull("childs")) {

                    childArray = userJson.getJSONArray("childs");

                }

            }

            String houseStatus = user.getHouseStatus();

            final double houseValue = user.getHouseValue();
            final String registrationHouseStatus=user.getHouseStatus();
            if (housing_expense != null) {

                houseStatus = "Own";

            }

            // ================================Retirement part starts
            // here===================================================

            final String goal_id = null;

            JSONArray userIncome = null;

            int retirementYear = 0;

            int spouseStartYear = 0;

            double userIRA = 0;

            double spouseIRA = 0;

            int startRetirementYear = 0;

            double userContribute401k = 0;

            double spouseContribute401k = 0;

            double userSSB = 0;

            double spouseSSB = 0;

            double yearlySSB = 0;

            int riskScore = user.getRiskScore();

            // ...limits buckets

            double spouse401k = 0;

            double user401k = 0;

            double userRothIRA = 0;

            double spouseRothIRA = 0;

            double user529Plan = 0;

            double spouse529Plan = 0;

            double tempExcess = 0;

            double userIRALimit = 0;

            double spouseIRALimit = 0;

            // ---limits array model

            double fourNotOneLimit = 0;

            double user529Limit = 0;

            double spouse529Limit = 0;

            double iraLimit = 0;

            double spouseFourNotOne = 0;

            double spouseIraLimit = 0;

            double spouseTempTaxable = 0;

            double AGI = 0;

            double nonTaxableForLimits = 0;

            double user401kContribution = 0;

            double spouse401kContribution = 0;

            double userCap = 0;

            double spouseCap = 0;

            double userMatching = 0;

            double spouseMatching = 0;

            int userStartYearForCap = 0;

            int spouseStartYearForCap = 0;

            double userSavedAmount = 0;

            double spouseSavedAmount = 0;

            double userIraSavedAmount = 0;

            double spouseIraSavedAmount = 0;

            double userTempExcess = 0;

            double tempExcessNew = 0;

            double spouseTempExcess = 0;

            int retFlag = 0;

            if (retirementFlag)

            {

                retFlag = 1;

            }

            if ((retFlag == 0 && goalCollectionData != null)) {

                userIRA = goalCollectionData.getUserContributionInIRA();

                spouseIRA = goalCollectionData.getSpouseContributionInIRA();

                userContribute401k = (goalCollectionData.getUserContributeAmount()
                        * goalCollectionData.getUserMatchContribution()) / 100;

                spouseContribute401k = (goalCollectionData.getSpouseContributeAmount()
                        * goalCollectionData.getSpouseMatchContribution()) / 100;

                // spouseAge = dataFromRetirement.getInt("spouseAge");

                retirementYear = dataFromRetirement.getInt("userStartYear");

                spouseStartYear = dataFromRetirement.getInt("spouseStartYear");

                // ////System.out.println("spouseStartYear mahi :"+spouseStartYear);

                userSSB = dataFromRetirement.getDouble("userssb");

                spouseSSB = dataFromRetirement.getDouble("spousessb");

            }

            else if ((retFlag == 1 && goalCollectionData != null)) {

                userIRA = goalCollectionData.getUserContributionInIRA();

                spouseIRA = goalCollectionData.getSpouseContributionInIRA();

                userContribute401k = (goalCollectionData.getUserContributeAmount()
                        * goalCollectionData.getUserMatchContribution()) / 100;

                spouseContribute401k = (goalCollectionData.getSpouseContributeAmount()
                        * goalCollectionData.getSpouseMatchContribution()) / 100;

                // spouseAge = dataFromRetirement.getInt("spouseAge");

                retirementYear = dataFromRetirement.getInt("userStartYear");

                spouseStartYear = dataFromRetirement.getInt("spouseStartYear");

                // ////System.out.println("spouseStartYear mahi :"+spouseStartYear);

                userSSB = goalCollectionData.getUserSSB();

                spouseSSB = goalCollectionData.getSpouseSSB();

            }

            else if (retFlag == 1) {

                final RetirementGoal retirementData = MongoDBConnection.goalcoll
                        .findOne("{fin_id:#,user_id:#,goalType:#}", fin_id, user_id, "Retirement").as(RetirementGoal.class);

                userIRA = retirementData.getUserContributionInIRA();

                spouseIRA = retirementData.getSpouseContributionInIRA();

                userContribute401k = (retirementData.getUserContributeAmount()
                        * retirementData.getUserMatchContribution()) / 100;

                spouseContribute401k = (retirementData.getSpouseContributeAmount()
                        * retirementData.getSpouseMatchContribution()) / 100;

                retirementYear = user.getBirthYear() + retirementData.getRetirementAge();

                spouseStartYear = user.getSpouseBirthYear() + retirementData.getSpouseRetirementAge();

                spouseSSB = user.getSpouseSSB();

                userSSB = user.getUserSSB();

                riskScore = user.getRiskScore();

            } else {

                if (dataFromRetirement == null) {

                    if (maritalStatus.equals("Yes")) {

                        spouseStartYear = user.getSpouseBirthYear() + 70;

                    } else {

                        // ////System.out.println("inside no spouse no retirement");

                        spouseAge = 0;

                    }

                } else {

                    // ////System.out.println("***********Mahi New
                    // changes*************");

                    if (maritalStatus.equals("Yes")) {

                        spouseStartYear = dataFromRetirement.getInt("spouseStartYear");

                    } else {

                        spouseAge = 0;

                    }

                }

                retirementYear = user.getBirthYear() + 70;

                spouseSSB = user.getSpouseSSB();

                userSSB = user.getUserSSB();

                riskScore = user.getRiskScore(); // spending

            }

            userIncome = userIncomes;

            int userStartYear = retirementYear;

            if (spouseStartYear != 0 && spouseStartYear < retirementYear) {

                startRetirementYear = spouseStartYear;

            } else {

                startRetirementYear = retirementYear;

            }

            userStartYearForCap = retirementYear;

            spouseStartYearForCap = spouseStartYear;

            final int spouseRetirementStartYear = spouseStartYear;

            final int userRetirementStartYear = retirementYear;

            final double incomeRothIRA = 0;// userRothIRA+spouseRothIRA;

            double contributionAmount = userIRA + userContribute401k + spouseIRA + spouseContribute401k;

            int spouseAgeInFinPlan = 0;

            if (fin_id != null) {

                final Marriagegoalmodel marriagegoalmodel = MongoDBConnection.goalcoll
                        .findOne("{fin_id:#,goalType:#}", fin_id, "Marriage").as(Marriagegoalmodel.class);

                if (marriagegoalmodel != null) {

                    spouseAgeInFinPlan = marriagegoalmodel.getBirthYear();

                }

            }
            //------------------genrating equity array------------------
            final JSONArray equity = new JSONArray();
            final double equityVal=0.00;
            for(int j=0;j<income.length();j++)
            {
                final JSONObject equityObj=new JSONObject();
                equityObj.put("year", income.getJSONObject(j).getInt("year"));
                equityObj.put("value", equityVal);
                equity.put(equityObj);
            }
            // ========================================Retirement part ends
            // here==========================================================

            // Find the basic details for calculating the tax from user and fin
            // plan collections - Ends here.

            final StatetaxModel stateTaxValue = MongoDBConnection.stateTaxColl.findOne("{'statename':#}", state)
                    .as(StatetaxModel.class);

            // ////System.out.println("userIncomes77-->"+userIncomes);

            for (int i = 0; i < assets.length(); i++) {

                /*double growthRate = 0;*/

                double limitUser529 = 0;

                double limitSpouse529 = 0;

                /*				double portfolioDividend = 0;

				double portfolioInterest = 0;*/

                double taxableInvestmentIncome = 0;

                double nonTaxableInvestmentIncome = 0;

                final double expenses = userExpense.getJSONObject(i).getDouble("totalExpense");

                double spouseIncome = 0;

                if (goalCollectionData != null)

                {

                    // ////System.out.println("Ranjitha :: inside flag");

                    userCap = goalCollectionData.getUserCap() / 100;

                    userMatching = goalCollectionData.getUserMatchContribution() / 100;

                    if (userAge <= 99)

                    {

                        user401kContribution = userCap * userMatching
                                * (userIncomes.getJSONObject(i)).getDouble("value");

                    }

                    userSavedAmount = goalCollectionData.getUserSavedAmount();

                    userIraSavedAmount = goalCollectionData.getUserSavedInIRA();

                    if (goalCollectionData.getSpouseCap() != 0 || goalCollectionData.getSpouseSavedAmount() != 0
                            || goalCollectionData.getSpouseSavedInIRA() != 0
                            || goalCollectionData.getSpouseMatchContribution() != 0
                            || goalCollectionData.getSpouseSavedAmount() != 0)

                    {

                        spouseCap = goalCollectionData.getSpouseCap() / 100;

                        spouseMatching = goalCollectionData.getSpouseMatchContribution() / 100;

                        spouse401kContribution = spouseCap * spouseMatching
                                * spouse_income.getJSONObject(i).getDouble("value");

                        spouseSavedAmount = goalCollectionData.getSpouseSavedAmount();

                        spouseIraSavedAmount = goalCollectionData.getSpouseSavedInIRA();

                    }

                }

                // ////System.out.println("Ranjitha ::
                // user401kContribution"+user401kContribution+"spouse401kContribution.."+spouse401kContribution+"spouseIraSavedAmount.."+spouseIraSavedAmount);

                if (maritalStatus.equals("Yes")) {

                    /*					if (fillingExemtion.getJSONObject(i).getString("fillingStatus").equals("Single") || fillingExemtion
							.getJSONObject(i).getString("fillingStatus").equals("Head of Household")) {

						riskFactor = 1;

					} else if (!fillingExemtion.getJSONObject(i).getString("fillingStatus")
							.equals("Married Filing Separately")
							&& !fillingExemtion.getJSONObject(i).getString("fillingStatus").equals("Qualified Widow")) {

						riskFactor = 2;

					}
                     */
                    riskFactor = 2;
                    if (i < spouse_income.length()) {

                        spouseIncome = spouse_income.getJSONObject(i).getDouble("value");

                    } else {

                        spouseIncome = 0;

                    }

                }

                double totalTax = 0;

                final int year = assets.getJSONObject(i).getInt("year");

                if (year < startRetirementYear) {

                    JSONObject tempTax = calTaxPerYear(0, 0, income.getJSONObject(i).getDouble("value"), user_id,
                            fin_id, userIncome.getJSONObject(i).getDouble("value"), spouseIncome, year,
                            fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"), fillingExemtion.getJSONObject(i)

                            .getString("fillingStatus"),
                            age, spouseAge, maritalStatus, kidBirthYear, state, kidcount, registrationYear, childArray,
                            houseStatus, houseValue, housing_expense, startYear, remainingMortgageOriginal,
                            remainingMortgageInterestRate, 0, stateTaxValue,taxableInvestmentAmount, nonTaxableInvestmentAmount,equity,finPlanDetails,houseAppreciationRate,registrationHouseStatus);

                    // ////System.out.println("Elapsed time for calculating tax: " +
                    // (System.nanoTime() - taxStartTime) / 1000000 + " milli
                    // seconds");

                    totalTax = tempTax.getDouble("federalTax") + tempTax.getDouble("fICAMedicareTax")
                    + tempTax.getDouble("spouseSSTax") + tempTax.getDouble("userSSTax")
                    + tempTax.getDouble("stateTax");

                    ////System.out.println("Year:" + year + ", Initial Tax: " +tempTax);

                    // --------------------- checking for limit of saving
                    // account---------------------

                    if (limits != null) {

                        if (emergencyFundFlag == false) {

                            limitForSavingAcount = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2)
                                    + limits.getJSONObject(i).getInt("saving");

                        } else {

                            if (emergencyType.equals("Fix Amount"))

                            {

                                limitForSavingAcount = (emergencyFundAmt) + limits.getJSONObject(i).getInt("saving");

                            }

                            else if (emergencyType.equals("Expense")) {

                                limitForSavingAcount = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                        * (Integer.parseInt(monthsOfExpense)))
                                        + limits.getJSONObject(i).getInt("saving");

                            }

                            else if (emergencyType.equals("Income")) {

                                limitForSavingAcount = (income.getJSONObject(i).getDouble("value") / 12
                                        * (Integer.parseInt(monthsOfIncome)))
                                        + limits.getJSONObject(i).getInt("saving");

                            }

                        }

                        limitForTaxableAmount = limits.getJSONObject(i).getInt("taxable");

                        limitForCollegeGoalTaxable = limits.getJSONObject(i).getInt("collegeGoalTaxable");

                    } else {

                        if (emergencyFundFlag == false) {

                            limitForSavingAcount = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2);

                        } else {

                            if (emergencyType.equals("Fix Amount"))

                            {

                                limitForSavingAcount = (emergencyFundAmt);

                            }

                            else if (emergencyType.equals("Expense")) {

                                limitForSavingAcount = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                        * (Integer.parseInt(monthsOfExpense)));

                            }

                            else if (emergencyType.equals("Income")) {

                                limitForSavingAcount = (income.getJSONObject(i).getDouble("value") / 12
                                        * (Integer.parseInt(monthsOfIncome)));

                            }

                        }

                        limitForTaxableAmount = 0;

                        limitForCollegeGoalTaxable = 0;

                    }

                    double currYearTaxableAmount = 0;

                    if (i == 0) {

                        currYearTaxableAmount = taxableInvestmentAmount;

                    } else {

                        currYearTaxableAmount = assets.getJSONObject(i - 1).getDouble("taxable_investment_amount");

                    }

                    /*					JSONObject growthRateJSON = investmentPortfolio.growthRate(Risk_Score, riskFactor, age,
							MongoDBConnection.investmentPortfolioData);

					growthRate = growthRateJSON.getDouble("growthRate");

					portfolioDividend = growthRateJSON.getDouble("portfolioDividend");

					portfolioInterest = growthRateJSON.getDouble("portfolioInterest");
                     */
                    if (i != 0)

                    {

                        if (assets.getJSONObject(i - 1).getDouble(
                                "user529Plan") <= limits.getJSONObject(i).getDouble("user529Plan") + user.getUser529())

                        {

                            limitUser529 = limits.getJSONObject(i).getDouble("user529Plan")
                                    - assets.getJSONObject(i - 1).getDouble("user529Plan");

                            if (limitUser529 < 0)

                            {

                                limitUser529 = 0;

                            }

                        }

                        if (assets.getJSONObject(i - 1)
                                .getDouble("spouse529Plan") <= limits.getJSONObject(i).getDouble("spouse529Plan")
                                + user.getSpousePlan529())

                        {

                            limitSpouse529 = limits.getJSONObject(i).getDouble("spouse529Plan")
                                    - assets.getJSONObject(i - 1).getDouble("spouse529Plan");

                            if (limitSpouse529 < 0)

                            {

                                limitSpouse529 = 0;

                            }

                        }

                    }

                    maximumContributionForNonTaxable = limits.getJSONObject(i).getDouble("user401k") +

                            limits.getJSONObject(i).getDouble("spouse401k") +

                            limits.getJSONObject(i).getDouble("userIRA") +

                            limits.getJSONObject(i).getDouble("spouseIRA") +

                            limits.getJSONObject(i).getDouble("userRouthIRA") +

                            limits.getJSONObject(i).getDouble("spouseRouthIRA") +

                            limitUser529 +

                            limitSpouse529;

                    // ////System.out.println("Ravi::::
                    // maximumContributionForNonTaxable==="+maximumContributionForNonTaxable+"..year=="+limits.getJSONObject(i).getInt("year")+"..limitUser529==="+limitUser529+".....limitSpouse529==="+limitSpouse529);

                    double savingAmount = remaining_amount;

                    // ////System.out.println("Mahi :
                    // maximumContributionForNonTaxable=:
                    // "+maximumContributionForNonTaxable);

                    double deductionAmount = 0;

                    if (deductions != null) {

                        deductionAmount = deductions.getJSONObject(i).getDouble("saving");

                    }

                    remaining_amount = remaining_amount + income.getJSONObject(i).getDouble("value")
                            + (savingInterestRate * remaining_amount / 100) - expenses - totalTax - deductionAmount;

                    // ////System.out.println("Maximum Contribution For NonTaxable:
                    // " + maximumContributionForNonTaxable + "Cash: " +
                    // remaining_amount + " Income: " +
                    // income.getJSONObject(i).getDouble("value") + " Expenses:
                    // " + expenses + " Deductions: "

                    // + deductionAmount + " Total tax: " + totalTax);

                    ////System.out.println("Krishnan:: remaining_amount: " +remaining_amount +" limitForSavingAcount " +    limitForSavingAcount+"..limitForTaxableAmount.."+limitForTaxableAmount);

                    if (remaining_amount >= limitForSavingAcount) {

                        excess = remaining_amount - (limitForSavingAcount);

                        savingAmount = savingAmount - deductionAmount;

                        if (savingAmount < limitForSavingAcount) {

                            savingAmount = limitForSavingAcount - savingAmount;

                        } else {

                            savingAmount = 0;

                        }

                        remaining_amount = limitForSavingAcount;

                    } else {

                        savingAmount = 0;

                        excess = 0;

                    }

                    // ////System.out.println("Ravi excess After saving
                    // limit:::"+excess);

                    if (remaining_amount < 0 && age < 70) {

                        taxableInvestmentAmount = taxableInvestmentAmount + remaining_amount;

                        if (taxableInvestmentAmount < 0) {

                            goalFeasibility = false;

                            //statusMsg.append("Insufficient balance in savings account");

                            ////System.out.println("Insufficient balance in savings account");

                            break;

                        }

                        remaining_amount = 0;

                    }

                    // ////System.out.println("currYearTaxableAmount==="+currYearTaxableAmount+"...limitForTaxableAmount=="+limitForTaxableAmount);

                    final double limitForTaxableTemp = limitForTaxableAmount;

                    if ((currYearTaxableAmount < limitForTaxableAmount)) {

                        limitForTaxableAmount = limitForTaxableAmount - currYearTaxableAmount;

                        if (excess <= limitForTaxableAmount) {

                            limitForTaxableAmount = excess;

                            taxExcess = 0;

                        } else {

                            taxExcess = excess - limitForTaxableAmount;

                        }

                        if (taxExcess > 0) {

                            excess = taxExcess;

                        } else {

                            excess = 0;

                        }

                    }

                    else {

                        limitForTaxableAmount = 0;

                    }

                    // ////System.out.println("limitForTaxableAmount.."+limitForTaxableAmount);

                    if ((currYearTaxableAmount < limitForTaxableTemp + limitForCollegeGoalTaxable) && excess > 0
                            && limitForCollegeGoalTaxable > 0) {

                        // ////System.out.println("currYearTaxableAmount====="+currYearTaxableAmount+"..limitForTaxableTemp.."+limitForTaxableTemp+"....limitForCollegeGoalTaxable=="+limitForCollegeGoalTaxable);

                        limitForCollegeGoalTaxable = limitForCollegeGoalTaxable
                                - (currYearTaxableAmount - limitForTaxableTemp);

                        if ((0.20 * excess) <= limitForCollegeGoalTaxable) {

                            limitForCollegeGoalTaxable = (0.20 * excess);

                            taxExcess = (0.80 * excess);

                        } else {

                            taxExcess = excess - limitForCollegeGoalTaxable;

                        }

                        if (taxExcess > 0) {

                            excess = taxExcess;

                        } else {

                            excess = 0;

                        }

                    } else {

                        limitForCollegeGoalTaxable = 0;

                    }

                    excessNonTax = 0;

                    double ftempBefore = tempTax.getDouble("federalTax");

                    double ftempEnd = 0;

                    final double incomeTemp = income.getJSONObject(i).getDouble("value");

                    if (excess <= 0) {

                        nonTaxableForLimits = 0;

                        ftempEnd = tempTax.getDouble("federalTax");

                        if (deductions != null) {

                            nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + excess
                                    + (growthRate * nonTaxableInvestmentAmount) / 100
                                    - deductions.getJSONObject(i).getDouble("nontaxable")
                                    - deductions.getJSONObject(i).getDouble("collegeGoalNontaxable");

                        } else {

                            nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + excess
                                    + (growthRate * nonTaxableInvestmentAmount) / 100;

                        }

                        // ////System.out.println("Zero Excess. No iteration
                        // performed");

                    } else {

                        // ////System.out.println("Excess being iterated is: " +
                        // excess);

                        while (excess > 0) {

                            AGI = income.getJSONObject(i).getDouble("value") - excess;
                            /*////System.out.println("AGI==1"+AGI);
							////System.out.println("value==1"+income.getJSONObject(i).getDouble("value"));
							////System.out.println("excess==1"+excess);*/

                            tempTax = calTaxPerYear(0, 0, AGI, user_id, fin_id,
                                    userIncome.getJSONObject(i).getDouble("value"), spouseIncome, year,
                                    fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                    fillingExemtion.getJSONObject(i).getString("fillingStatus"), age,

                                    spouseAge, maritalStatus, kidBirthYear, state, kidcount, registrationYear,
                                    childArray, houseStatus, houseValue, housing_expense, startYear,
                                    remainingMortgageOriginal, remainingMortgageInterestRate, 0, stateTaxValue,taxableInvestmentAmount, nonTaxableInvestmentAmount,equity,finPlanDetails,houseAppreciationRate,registrationHouseStatus);

                            excess = incomeTemp - expenses - tempTax.getDouble("federalTax")
                                    - tempTax.getDouble("userSSTax") - tempTax.getDouble("fICAMedicareTax")
                                    - tempTax.getDouble("spouseSSTax") - tempTax.getDouble("stateTax") - savingAmount
                                    - limitForTaxableAmount - limitForCollegeGoalTaxable;
                            //////System.out.println("noOfExcemtion caltaxp"+fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"));

                            ftempEnd = tempTax.getDouble("federalTax");

                            if (excess > maximumContributionForNonTaxable) {

                                nonTaxableForLimits = maximumContributionForNonTaxable;

                                AGI = income.getJSONObject(i).getDouble("value") - maximumContributionForNonTaxable;

                                tempTax = calTaxPerYear(0, 0, AGI, user_id, fin_id,
                                        userIncome.getJSONObject(i).getDouble("value"), spouseIncome, year,
                                        fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                        fillingExemtion.getJSONObject(i).getString("fillingStatus"), age,

                                        spouseAge, maritalStatus, kidBirthYear, state, kidcount, registrationYear,
                                        childArray, houseStatus, houseValue, housing_expense, startYear,
                                        remainingMortgageOriginal, remainingMortgageInterestRate, 0, stateTaxValue,taxableInvestmentAmount, nonTaxableInvestmentAmount,equity,finPlanDetails,houseAppreciationRate,registrationHouseStatus);

                                excess = incomeTemp - expenses - tempTax.getDouble("federalTax")
                                        - tempTax.getDouble("userSSTax") - tempTax.getDouble("fICAMedicareTax")
                                        - tempTax.getDouble("spouseSSTax") - tempTax.getDouble("stateTax")
                                        - savingAmount - limitForTaxableAmount - limitForCollegeGoalTaxable;

                                ftempEnd = tempTax.getDouble("federalTax");

                                if (deductions != null) {

                                    nonTaxableInvestmentAmount = nonTaxableInvestmentAmount
                                            + maximumContributionForNonTaxable
                                            + (growthRate * nonTaxableInvestmentAmount) / 100
                                            - deductions.getJSONObject(i).getDouble("nontaxable")
                                            - deductions.getJSONObject(i).getDouble("collegeGoalNontaxable");

                                } else {

                                    nonTaxableInvestmentAmount = nonTaxableInvestmentAmount
                                            + maximumContributionForNonTaxable
                                            + (growthRate * nonTaxableInvestmentAmount) / 100;

                                }

                                excessNonTax = excess - maximumContributionForNonTaxable;

                                break;

                            }

                            if (!((ftempBefore - ftempEnd) <= 5)) {

                                ftempBefore = ftempEnd;

                            } else {

                                nonTaxableForLimits = excess;

                                ftempEnd = tempTax.getDouble("federalTax");

                                if (deductions != null) {

                                    nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + excess
                                            + (growthRate * nonTaxableInvestmentAmount) / 100
                                            - deductions.getJSONObject(i).getDouble("nontaxable")
                                            - deductions.getJSONObject(i).getDouble("collegeGoalNontaxable");

                                } else {

                                    nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + excess
                                            + (growthRate * nonTaxableInvestmentAmount) / 100;

                                }

                                excessNonTax = 0;

                                break;

                            }

                        }

                        // ////System.out.println("Elapsed time for sweeping excess:
                        // " + (System.nanoTime() - sweepingExcessTime) /
                        // 1000000 + " milli seconds");

                    }

                    // Money sweeping into non taxable buckets starts here...

                    JSONObject limitsTemp = new JSONObject();

                    limitsTemp = limits.getJSONObject(i);

                    // ////System.out.println("Ranjitha :: limitsTemp :" +
                    // limitsTemp+"userAge"+userAge+"spouseAge"+spouseAge+"growthRate"+growthRate+"nonTaxableForLimits.."+nonTaxableForLimits);

                    fourNotOneLimit = limitsTemp.getDouble("user401k");

                    if (i != 0)

                    {

                        user529Limit = limitUser529;

                    }

                    // ////System.out.println("Ravi::::
                    // user529Limit=="+user529Limit);

                    // user529Limit=limitsTemp.getDouble("user529Plan");

                    iraLimit = limitsTemp.getDouble("userIRA");

                    if (i != 0) {

                        user401k = assets.getJSONObject(i - 1).getDouble("user401k");

                        userIRALimit = assets.getJSONObject(i - 1).getDouble("userIRA");

                        user529Plan = assets.getJSONObject(i - 1).getDouble("user529Plan");

                    } else {

                        user401k = user.getUser401k() + userSavedAmount;

                        userIRALimit = user.getUserIRA() + userIraSavedAmount;

                        user529Plan = user.getUser529();

                        userRothIRA = user.getUserRothIra();

                    }

                    if (nonTaxableForLimits > user529Limit)

                    {

                        // ////System.out.println("Ravi :: user529Plan= if
                        // "+user529Limit);

                        user529Plan = user529Limit + (user529Plan + (user529Plan * growthRate) / 100);

                        nonTaxableForLimits = nonTaxableForLimits - user529Limit;

                    }

                    else

                    {

                        // ////System.out.println("Ravi :: user529Plan= else
                        // "+nonTaxableForLimits);

                        user529Plan = nonTaxableForLimits + (user529Plan + (user529Plan * growthRate) / 100);

                        nonTaxableForLimits = 0;

                    }

                    if (nonTaxableForLimits > spouse529Limit)

                    {

                        // ////System.out.println("Ravi :: spouse529Plan= if
                        // "+spouse529Limit);

                        spouse529Plan = spouse529Limit + (spouse529Plan + (spouse529Plan * growthRate)) / 100;

                        nonTaxableForLimits = nonTaxableForLimits - spouse529Limit;

                    }

                    else

                    {

                        // ////System.out.println("Ravi :: spouse529Plan= else
                        // "+nonTaxableForLimits);

                        spouse529Plan = nonTaxableForLimits + (spouse529Plan + (spouse529Plan * growthRate)) / 100;

                        nonTaxableForLimits = 0;

                    }

                    if (fillingExemtion.getJSONObject(i).getString("fillingStatus").equals("Married Filing Jointly")
                            && spouse_income.getJSONObject(i).getDouble("value") != 0)

                    {

                        spouseTempTaxable = nonTaxableForLimits / 2;

                        if (spouseTempTaxable < 0)

                        {

                            spouseTempTaxable = 0;

                        }

                        nonTaxableForLimits = nonTaxableForLimits / 2;

                    }

                    if (nonTaxableForLimits <= fourNotOneLimit) {

                        // ////System.out.println("Ravi :: user401k=
                        // "+nonTaxableForLimits);

                        user401k = nonTaxableForLimits + (user401k + (user401k * growthRate) / 100);

                        userIRALimit = userIRALimit + (userIRALimit * growthRate) / 100;

                        // user529Plan = user529Plan + (user529Plan *
                        // growthRate) / 100;

                    } else {

                        // ////System.out.println("Ravi :: user401k= new
                        // "+fourNotOneLimit);

                        user401k = fourNotOneLimit + (user401k + (user401k * growthRate) / 100);

                        tempExcess = nonTaxableForLimits - fourNotOneLimit;

                        if (tempExcess < iraLimit) {

                            // ////System.out.println("Ravi :: userIRALimit=
                            // "+tempExcess);

                            userIRALimit = tempExcess + (userIRALimit + (userIRALimit * growthRate) / 100);

                            // user529Plan = user529Plan + (user529Plan *
                            // growthRate) / 100;

                        } else {

                            // ////System.out.println("Ravi :: userIRALimit=
                            // "+iraLimit);

                            userIRALimit = iraLimit + (userIRALimit + (userIRALimit * growthRate) / 100);

                            tempExcess = tempExcess - iraLimit;

                            // ////System.out.println("tempExcess :: user
                            // "+tempExcess);

                            if (fillingExemtion.getJSONObject(i).getString("fillingStatus").equals(
                                    "Married Filing Jointly") && spouse_income.getJSONObject(i).getDouble("value") == 0)

                            {

                                user529Plan = 0;

                                spouseIRALimit = tempExcess + (spouseIRALimit + (spouseIRALimit * growthRate) / 100);

                            }

                            userTempExcess = tempExcess;

                        }

                    }

                    // ////System.out.println("Ranjitha ::spouse_income outside
                    // tempExcess user401k "+user401k+"user 529
                    // "+user529Plan+"spouseIRALimit"+spouseIRALimit+"userTempExcess.."+userTempExcess);

                    if (spouse_income.length() > 0
                            && fillingExemtion.getJSONObject(i).getString("fillingStatus")
                            .equals("Married Filing Jointly")
                            && spouse_income.getJSONObject(i).getDouble("value") != 0) {

                        if (i != 0) {

                            spouse401k = assets.getJSONObject(i - 1).getDouble("spouse401k");

                            spouseIRALimit = assets.getJSONObject(i - 1).getDouble("spouseIRA");

                            spouse529Plan = assets.getJSONObject(i - 1).getDouble("spouse529Plan");

                        } else {

                            spouse401k = user.getSpouse401k() + spouseSavedAmount;

                            spouseIRALimit = user.getSpouseIRA() + spouseIraSavedAmount;

                            spouse529Plan = user.getSpousePlan529();

                            spouse529Plan = (spouse529Plan + (spouse529Plan * growthRate) / 100);

                            spouseRothIRA = user.getSpouseRothIra();

                        }

                        if (nonTaxableForLimits == 0 && i != 0)

                        {

                            spouse529Plan = (spouse529Plan + (spouse529Plan * growthRate) / 100);

                        }

                        spouseFourNotOne = limitsTemp.getDouble("spouse401k");

                        spouseIraLimit = limitsTemp.getDouble("spouseIRA");

                        if (i != 0)

                        {

                            spouse529Limit = limitSpouse529;

                        }

                        // ////System.out.println("Ravi::::
                        // spouse529Limit=="+spouse529Limit);

                        // spouse529Limit=limitsTemp.getDouble("spouse529Plan");

                        if (spouseTempTaxable <= spouseFourNotOne) {

                            // ////System.out.println("Ravi ::spouse401k=
                            // "+spouseTempTaxable);

                            spouse401k = spouseTempTaxable + (spouse401k + (spouse401k * growthRate) / 100);

                            spouseIRALimit = spouseIRALimit + (spouseIRALimit * growthRate) / 100;

                            // spouse529Plan = spouse529Plan + (spouse529Plan *
                            // growthRate) / 100;

                        } else {

                            // ////System.out.println("Ravi ::spouse401k=
                            // "+spouseFourNotOne);

                            spouse401k = spouseFourNotOne + (spouse401k + (spouse401k * growthRate) / 100);

                            tempExcess = spouseTempTaxable - spouseFourNotOne;

                            if (userAge >= 50 && spouseAge < 50)

                            {

                                if (nonTaxableForLimits < fourNotOneLimit && spouseTempTaxable > spouseFourNotOne)

                                {

                                    user401k = user401k + tempExcess;

                                    spouseIRALimit = (spouseIRALimit + (spouseIRALimit * growthRate) / 100);

                                }

                                // ////System.out.println("Ranjitha before age
                                // variation userIRALimit "+user401k);

                                if (tempExcess > spouseIraLimit && spouseIraLimit != 0)

                                {

                                    spouseIRALimit = spouseIraLimit
                                            + (spouseIRALimit + (spouseIRALimit * growthRate) / 100);

                                    tempExcess = tempExcess - spouseIraLimit;

                                    // ////System.out.println("spouseIRALimit..."+spouseIRALimit+"tempExcess.."+tempExcess+"userIRALimit.."+userIRALimit);

                                    userIRALimit = userIRALimit + tempExcess;

                                    // ////System.out.println("Ranjitha after age
                                    // variation userIRALimit"+userIRALimit);

                                }

                            }

                            else {

                                if (tempExcess < spouseIraLimit) {

                                    // ////System.out.println("Ravi
                                    // ::spouseIRALimit= 1"+tempExcess);

                                    spouseIRALimit = tempExcess
                                            + (spouseIRALimit + (spouseIRALimit * growthRate) / 100);

                                    // spouse529Plan = spouse529Plan +
                                    // (spouse529Plan * growthRate) / 100;

                                } else {

                                    // ////System.out.println("Ravi
                                    // ::spouseIRALimit= 2"+spouseIraLimit);

                                    spouseIRALimit = spouseIraLimit
                                            + (spouseIRALimit + (spouseIRALimit * growthRate) / 100);

                                    tempExcess = tempExcess - spouseIraLimit;

                                    // ////System.out.println("tempExcess.."+tempExcess);

                                    if (tempExcess > 0 && spouse401k == 0)

                                    {

                                        // ////System.out.println("Ranjitha ::
                                        // inside user401k "+user401k +"
                                        // "+userIRALimit);

                                        if (nonTaxableForLimits < fourNotOneLimit)

                                        {

                                            final double tempVal = fourNotOneLimit - nonTaxableForLimits;

                                            user401k = user401k + tempVal;

                                            tempExcessNew = tempExcess - tempVal;

                                            // ////System.out.println("tempExcessNew
                                            // :: "+tempExcessNew);

                                        }

                                        if (tempExcessNew <= iraLimit)

                                        {

                                            userIRALimit = userIRALimit + tempExcessNew;

                                        }

                                    }

                                }

                            }

                        }

                        if (userTempExcess > 0 && user401k == 0)

                        {

                            // ////System.out.println("Ranjitha :: inside spouse401k
                            // "+spouse401k +" "+spouseIRALimit);

                            if (spouseTempTaxable < spouseFourNotOne)

                            {

                                final double tempSpouseVal = spouseFourNotOne - spouseTempTaxable;

                                spouse401k = spouse401k + tempSpouseVal;

                                spouseTempExcess = userTempExcess - tempSpouseVal;

                            }

                            if (spouseTempExcess <= spouseIraLimit)

                            {

                                spouseIRALimit = spouseIRALimit + spouseTempExcess;

                            }

                        }

                    }

                    // ////System.out.println("Ranjitha :: All contributions
                    // nonTaxableForLimits"+nonTaxableForLimits+
                    // "spouse401k"+spouse401k+"spouseIRALimit"+spouseIRALimit+"spouse529Plan"+spouse529Plan+"user401k"+user401k
                    // +"userira" + userIRALimit + "user529Plan" + user529Plan);

                    // ////System.out.println("Ravi:::::year"+year+"---user529Plan="+user529Plan+"..spouse529Plan="+spouse529Plan);

                    if (deductions != null) {

                        user529Plan = user529Plan - deductions.getJSONObject(i).getDouble("collegeGoalNontaxable");

                        // ////System.out.println("deductions===user529Plan==="+user529Plan);

                        if (user529Plan < 0) {

                            spouse529Plan = spouse529Plan + user529Plan;

                            user529Plan = 0;

                            // ////System.out.println("deductions===spouse529Plan==="+spouse529Plan);

                        }

                        // ////System.out.println("Ranjitha :: college user529Plan
                        // one "+user529Plan+"spouse529Plan.."+spouse529Plan);

                    }

                    // Money sweeping into non taxable buckets ends here...

                    tax.getJSONObject(i).put("federalTax", ftempEnd).put("userSSTax", tempTax.getDouble("userSSTax"))
                    .put("fICAMedicareTax", tempTax.getDouble("fICAMedicareTax"))
                    .put("spouseSSTax", tempTax.getDouble("spouseSSTax"))
                    .put("stateTax", tempTax.getDouble("stateTax"));

                    if (deductions != null) {

                        if (assets.getJSONObject(i).getInt("year") == year
                                && deductions.getJSONObject(i).getInt("year") == year) {

                            taxableInvestmentAmount = taxableInvestmentAmount
                                    + ((growthRate * taxableInvestmentAmount) / 100) + excessNonTax
                                    + limitForTaxableAmount + limitForCollegeGoalTaxable
                                    - deductions.getJSONObject(i).getDouble("taxable");

                            if (taxableInvestmentAmount < 0) {

                                remaining_amount = remaining_amount + taxableInvestmentAmount;

                                taxableInvestmentAmount = 0;

                                if (remaining_amount < 0) {

                                    goalFeasibility = false;

                                    statusMsg.append("Insufficient balance in taxable account");

                                    // ////System.out.println("Insufficient balance
                                    // in taxable account");

                                    break;

                                }

                            }

                            taxableInvestmentAmount = taxableInvestmentAmount
                                    - deductions.getJSONObject(i).getDouble("collegeGoalTaxable");

                            if (taxableInvestmentAmount < 0) {

                                nonTaxableInvestmentAmount = nonTaxableInvestmentAmount + taxableInvestmentAmount;

                                taxableInvestmentAmount = 0;

                                goalFeasibility = false;

                                statusMsg.append("Insufficient balance in non-taxable account for college goal");

                                // ////System.out.println("Insufficient balance in
                                // non-taxable account for college goal");

                                break;

                            }

                            if (nonTaxableInvestmentAmount < 0 || user529Plan < 0 || spouse529Plan < 0) {

                                goalFeasibility = false;

                                statusMsg.append("Insufficient balance in non-taxble account");

                                // ////System.out.println("Insufficient balance in
                                // account");

                                break;

                            }

                            // ////System.out.println("saving..." + remaining_amount
                            // + "taxable..." + taxableInvestmentAmount +
                            // "nontaxbale..." + nonTaxableInvestmentAmount);

                        } else {

                            break;

                        }

                    } else {

                        if (assets.getJSONObject(i).getInt("year") == year) {

                            taxableInvestmentAmount = taxableInvestmentAmount
                                    + ((growthRate * taxableInvestmentAmount) / 100) + excessNonTax
                                    + limitForTaxableAmount + limitForCollegeGoalTaxable;

                            if (taxableInvestmentAmount < 0) {

                                remaining_amount = remaining_amount + taxableInvestmentAmount;

                                taxableInvestmentAmount = 0;

                                if (remaining_amount < 0) {

                                    goalFeasibility = false;

                                    statusMsg.append("Insufficient balance in taxable account");

                                    // ////System.out.println("Insufficient balance
                                    // in taxable account");

                                    break;

                                }

                            }

                            // ////System.out.println("saving..." + remaining_amount
                            // + "taxable..." + taxableInvestmentAmount +
                            // "nontaxbale..." + nonTaxableInvestmentAmount);

                        } else {

                            break;

                        }

                    }

                    // ////System.out.println("Ranjtha :: before db "+user401k+" "+
                    // userIRALimit+" "+spouse401k+"
                    // "+spouseIRALimit+"user529Plan
                    // "+user529Plan+"spouse529Plan "+spouse529Plan);

                    nonTaxableInvestmentIncome = (nonTaxableInvestmentAmount * (portfolioDividend + portfolioInterest))
                            / 100;

                    taxableInvestmentIncome = (taxableInvestmentAmount * (portfolioDividend + portfolioInterest) * 0.85)
                            / 100;

                    assets.getJSONObject(i).put("nonTaxableInvestmentIncome", nonTaxableInvestmentIncome);

                    assets.getJSONObject(i).put("taxableInvestmentIncome", taxableInvestmentIncome);

                    assets.getJSONObject(i)
                    .put("nontaxable_investment_amount",
                            (user401k + user401kContribution + userIRALimit + userRothIRA + user529Plan
                                    + spouse401k + spouse401kContribution + spouseIRALimit + spouseRothIRA
                                    + spouse529Plan));

                    assets.getJSONObject(i).put("taxable_investment_amount", taxableInvestmentAmount);

                    assets.getJSONObject(i).put("savings", remaining_amount);

                    assets.getJSONObject(i).put("user401k", user401k + user401kContribution);

                    assets.getJSONObject(i).put("userIRA", userIRALimit);

                    assets.getJSONObject(i).put("userRothIRA", userRothIRA);

                    assets.getJSONObject(i).put("user529Plan", user529Plan);

                    assets.getJSONObject(i).put("spouse401k", spouse401k + spouse401kContribution);

                    assets.getJSONObject(i).put("spouseIRA", spouseIRALimit);

                    assets.getJSONObject(i).put("spouseRothIRA", spouseRothIRA);

                    assets.getJSONObject(i).put("spouse529Plan", spouse529Plan);

                    limitForSavingAcount = initialLimit;

                    //////System.out.println("mahi ::::"+assets.getJSONObject(i));

                    age++;

                    if (spouseAge != 0) {

                        spouseAge++;

                    }

                    userAge = age;

                } else {
                    // =======================Retirement year calculation starts
                    // here=================================================
                    JSONObject responseFromGrowthRate = new JSONObject();
                    final InvestmentPortfolioImpl investmentPortfolioImpl = new InvestmentPortfolioImpl();
                    if ((userAge < 70 && spouseAge < 70) && (assets.getJSONObject(i).getInt("year") == userStartYear
                            || (assets.getJSONObject(i).getInt("year") == spouseStartYear))) {

                        double saving = assets.getJSONObject(i - 1).getDouble("savings");
                        double savingLimite = 0;

                        if (limits != null) {
                            if (emergencyFundFlag == false) {
                                savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2)
                                        + limits.getJSONObject(i).getInt("saving");
                            } else {

                                if (emergencyType.equals("Fix Amount")) {
                                    savingLimite = (emergencyFundAmt) + limits.getJSONObject(i).getInt("saving");
                                } else if (emergencyType.equals("Expense")) {

                                    savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                            * (Integer.parseInt(monthsOfExpense)))
                                            + limits.getJSONObject(i).getInt("saving");
                                } else if (emergencyType.equals("Income")) {

                                    savingLimite = (income.getJSONObject(i).getDouble("value") / 12
                                            * (Integer.parseInt(monthsOfIncome)))
                                            + limits.getJSONObject(i).getInt("saving");
                                }

                            }
                        } else {
                            if (emergencyFundFlag == false) {
                                savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2);
                            } else {

                                if (emergencyType.equals("Fix Amount")) {
                                    savingLimite = (emergencyFundAmt);
                                } else if (emergencyType.equals("Expense")) {

                                    savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                            * (Integer.parseInt(monthsOfExpense)));
                                } else if (emergencyType.equals("Income")) {

                                    savingLimite = (income.getJSONObject(i).getDouble("value") / 12
                                            * (Integer.parseInt(monthsOfIncome)));
                                }

                            }
                        }
                        saving = savingLimite;
                        if (maritalStatus.equals("Yes")) {
                            spouseSSB = spouse_income.getJSONObject(i).getDouble("value");
                            yearlySSB = spouse_income.getJSONObject(i).getDouble("value")
                                    + userIncome.getJSONObject(i).getDouble("value");

                            responseFromGrowthRate = investmentPortfolioImpl.growthRate(riskScore, 2, userAge,
                                    MongoDBConnection.investmentPortfolioData);
                        } else {

                            yearlySSB = userIncome.getJSONObject(i).getDouble("value");
                            responseFromGrowthRate = investmentPortfolioImpl.growthRate(riskScore, 1, userAge,
                                    MongoDBConnection.investmentPortfolioData);
                        }
                        growthRate = responseFromGrowthRate.getDouble("growthRate") / 100;
                        //////System.out.println("growthRate----------="+growthRate);
                        userSSB = userIncome.getJSONObject(i).getDouble("value");

                        final JSONObject federalJson = calRetirementTax(
                                fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                fillingExemtion.getJSONObject(i).getString("fillingStatus"), fin_id, incomeRothIRA,
                                yearlySSB, userExpense.getJSONObject(i).getDouble("totalExpense"), user_id, 0.0,
                                userAge, spouseAge, userSSB, spouseSSB, 0, goal_id, year, maritalStatus, kidBirthYear,
                                state, kidcount, registrationYear, childArray, houseStatus, houseValue, housing_expense,
                                startYear, remainingMortgageOriginal, remainingMortgageInterestRate, spouseBirthYear,
                                userBirthYear, spouseAgeInFinPlan, spouseRetirementStartYear, userRetirementStartYear,
                                stateTaxValue,equity,houseAppreciationRate,registrationHouseStatus);

                        final double accessMony = federalJson.getDouble("withdrawalFromRetirementAccounts") - 0
                                - federalJson.getDouble("federalTax") - federalJson.getDouble("stateTax")
                                - federalJson.getDouble("userSSTax") - federalJson.getDouble("spouseSSTax")
                                - federalJson.getDouble("fICAMedicareTax");

                        /*						nonTaxableInvestmentIncome = (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") * (portfolioDividend + portfolioInterest))
								/ 100;

						taxableInvestmentIncome = (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount") * (portfolioDividend + portfolioInterest) * 0.85)
								/ 100;

						assets.getJSONObject(i).put("nonTaxableInvestmentIncome", nonTaxableInvestmentIncome);

						assets.getJSONObject(i).put("taxableInvestmentIncome", taxableInvestmentIncome);*/

                        if (accessMony >= 0 && (yearlySSB > userExpense.getJSONObject(i).getDouble("totalExpense"))) {
                            assets.getJSONObject(i).put("taxable_investment_amount",
                                    assets.getJSONObject(i - 1).getDouble("taxable_investment_amount") + accessMony
                                    + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                            * growthRate));
                            if (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") <= 0) {
                                assets.getJSONObject(i).put("user401k", 0);
                                assets.getJSONObject(i).put("spouse401k", 0);
                                assets.getJSONObject(i).put("userIRA", 0);
                                assets.getJSONObject(i).put("spouseIRA", 0);
                                assets.getJSONObject(i).put("nontaxable_investment_amount", 0);
                                assets.getJSONObject(i).put("spouseRothIRA", 0);
                                assets.getJSONObject(i).put("userRothIRA", 0);

                            } else {

                                assets.getJSONObject(i).put("user401k",
                                        assets.getJSONObject(i - 1).getDouble("user401k")
                                        + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate);
                                assets.getJSONObject(i).put("spouse401k",
                                        assets.getJSONObject(i - 1).getDouble("spouse401k")
                                        + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate);
                                assets.getJSONObject(i).put("userIRA", assets.getJSONObject(i - 1).getDouble("userIRA")
                                        + assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate);
                                assets.getJSONObject(i).put("spouseIRA",
                                        assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                        + assets.getJSONObject(i - 1).getDouble("spouseIRA") * growthRate);
                                assets.getJSONObject(i).put("spouseRothIRA", assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                        + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);
                                assets.getJSONObject(i).put("userRothIRA", assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                        + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);
                                if (userStartYearForCap > assets.getJSONObject(i).getInt("year")) {
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i).getDouble("user401k") + user401kContribution);

                                }

                                if (spouseStartYearForCap > assets.getJSONObject(i).getInt("year")) {
                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i).getDouble("spouse401k") + spouse401kContribution);

                                }
                                assets.getJSONObject(i).put("nontaxable_investment_amount",
                                        assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i).getDouble("spouseIRA")
                                        + assets.getJSONObject(i).getDouble("spouseRothIRA")
                                        + assets.getJSONObject(i).getDouble("userRothIRA"));
                            }

                            assets.getJSONObject(i).put("savings", saving);
                            tax.getJSONObject(i).put("federalTax", federalJson.getDouble("federalTax"));
                            tax.getJSONObject(i).put("fICAMedicareTax", federalJson.getDouble("fICAMedicareTax"));
                            tax.getJSONObject(i).put("fICASocialSecurityTax",
                                    federalJson.getDouble("fICASocialSecurityTax"));
                            tax.getJSONObject(i).put("userSSTax", federalJson.getDouble("userSSTax"));
                            tax.getJSONObject(i).put("stateTax", federalJson.getDouble("stateTax"));
                            tax.getJSONObject(i).put("spouseSSTax", federalJson.getDouble("spouseSSTax"));
                            //////System.out.println("rothira in else==="+ assets.getJSONObject(i).getDouble("userRothIRA"));
                            //////System.out.println("rothira in else=previous=="+ assets.getJSONObject(i-1).getDouble("userRothIRA"));
                            //////System.out.println("ira in else==="+ assets.getJSONObject(i).getDouble("userIRA"));
                            //////System.out.println("ira in else=previous=="+ assets.getJSONObject(i-1).getDouble("userIRA"));

                        } else {
                            final double nontaxtableWithdrawlAmount = federalJson.getDouble("incomeIRA");
                            //////System.out.println("nontaxtableWithdrawlAmount---aparna"+nontaxtableWithdrawlAmount);
                            //////System.out.println("iraa---aparna"+assets.getJSONObject(i - 1).getDouble("userIRA"));
                            if (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") <= 0) {
                                assets.getJSONObject(i).put("user401k", 0);
                                assets.getJSONObject(i).put("spouse401k", 0);
                                assets.getJSONObject(i).put("userIRA", 0);
                                assets.getJSONObject(i).put("spouseIRA", 0);
                                assets.getJSONObject(i).put("spouseRothIRA", 0);
                                assets.getJSONObject(i).put("userRothIRA", 0);
                                assets.getJSONObject(i).put("nontaxable_investment_amount", 0);

                            } else {

                                if (assets.getJSONObject(i - 1).getDouble("user401k") > 0
                                        && assets.getJSONObject(i - 1).getDouble("spouse401k") > 0) {

                                    if (assets.getJSONObject(i).getInt("year") < spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") >= userStartYear)

                                    {

                                        assets.getJSONObject(i).put("user401k",
                                                (assets.getJSONObject(i - 1).getDouble("user401k")
                                                        - nontaxtableWithdrawlAmount) * 1.025);

                                        assets.getJSONObject(i).put("spouse401k",
                                                assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate+spouse401kContribution);

                                    }

                                    else if (assets.getJSONObject(i).getInt("year") >= spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") < userStartYear)

                                    {

                                        assets.getJSONObject(i).put("spouse401k",
                                                (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                        - nontaxtableWithdrawlAmount) * 1.025);

                                        assets.getJSONObject(i).put("user401k",
                                                assets.getJSONObject(i - 1).getDouble("user401k")
                                                + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate+user401kContribution);

                                    }

                                    else if (assets.getJSONObject(i).getInt("year") >= spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") >= userStartYear)

                                    {

                                        assets.getJSONObject(i).put("user401k",
                                                (assets.getJSONObject(i - 1).getDouble("user401k")
                                                        - nontaxtableWithdrawlAmount / 2) * 1.025);

                                        assets.getJSONObject(i).put("spouse401k",
                                                (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                        - nontaxtableWithdrawlAmount / 2) * 1.025);

                                    }

                                    assets.getJSONObject(i).put("userIRA",
                                            assets.getJSONObject(i - 1).getDouble("userIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate);

                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseIRA") * growthRate);


                                    assets.getJSONObject(i).put("spouseRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);

                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);

                                } else if (assets.getJSONObject(i - 1).getDouble("spouse401k") > 0
                                        && assets.getJSONObject(i - 1).getDouble("user401k") <= 0) {
                                    if (assets.getJSONObject(i).getInt("year") >= spouseStartYear) {
                                        assets.getJSONObject(i).put("spouse401k",
                                                (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                        - nontaxtableWithdrawlAmount) * 1.025);
                                    }
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("userIRA",
                                            assets.getJSONObject(i - 1).getDouble("userIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate);
                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseIRA") * growthRate);
                                    assets.getJSONObject(i).put("spouseRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);

                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);
                                } else if (assets.getJSONObject(i - 1).getDouble("spouse401k") <= 0
                                        && assets.getJSONObject(i - 1).getDouble("user401k") > 0) {
                                    assets.getJSONObject(i).put("spouse401k", 0);
                                    if (assets.getJSONObject(i).getInt("year") >= userStartYear) {
                                        assets.getJSONObject(i).put("user401k",
                                                (assets.getJSONObject(i - 1).getDouble("user401k")
                                                        - nontaxtableWithdrawlAmount) * 1.025);
                                    }
                                    assets.getJSONObject(i).put("userIRA",
                                            assets.getJSONObject(i - 1).getDouble("userIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate);
                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseIRA") * growthRate);
                                    assets.getJSONObject(i).put("spouseRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);

                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);
                                } else if (assets.getJSONObject(i - 1).getDouble("userIRA") > 0
                                        && assets.getJSONObject(i - 1).getDouble("spouseIRA") > 0) {

                                    if (assets.getJSONObject(i).getInt("year") < spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") >= userStartYear)

                                    {

                                        assets.getJSONObject(i).put("userIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                        - nontaxtableWithdrawlAmount) * 1.025);

                                    }

                                    else if (assets.getJSONObject(i).getInt("year") >= spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") < userStartYear)

                                    {

                                        assets.getJSONObject(i).put("spouseIRA",
                                                (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                        - nontaxtableWithdrawlAmount) * 1.025);

                                    }

                                    else if (assets.getJSONObject(i).getInt("year") >= spouseStartYear
                                            && assets.getJSONObject(i).getInt("year") >= userStartYear)

                                    {

                                        assets.getJSONObject(i).put("userIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                        - nontaxtableWithdrawlAmount / 2) * 1.025);

                                        assets.getJSONObject(i).put("spouseIRA",
                                                (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                        - nontaxtableWithdrawlAmount / 2) * 1.025);

                                    }

                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i - 1).getDouble("spouse401k")
                                            + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate);

                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i - 1).getDouble("user401k")
                                            + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate);

                                } else if (assets.getJSONObject(i - 1).getDouble("spouseIRA") > 0
                                        && assets.getJSONObject(i - 1).getDouble("userIRA") <= 0) {

                                    if (assets.getJSONObject(i).getInt("year") >= spouseStartYear) {
                                        assets.getJSONObject(i).put("spouseIRA",
                                                (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                        - nontaxtableWithdrawlAmount) * 1.025);
                                    }
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i - 1).getDouble("user401k")
                                            + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate);

                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i - 1).getDouble("spouse401k")
                                            + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate);

                                    assets.getJSONObject(i).put("userIRA", 0);
                                } else if (assets.getJSONObject(i - 1).getDouble("spouseIRA") <= 0
                                        && assets.getJSONObject(i - 1).getDouble("userIRA") > 0) {
                                    if (assets.getJSONObject(i).getInt("year") >= userStartYear) {
                                        assets.getJSONObject(i).put("userIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                        - nontaxtableWithdrawlAmount) * 1.025);
                                    }
                                    ////System.out.println("USERIRA+++"+assets.getJSONObject(i - 1).getDouble("userRothIRA"));
                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("userRothIRA") * growthRate);

                                    assets.getJSONObject(i).put("spouseRothIRA",
                                            assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                            + assets.getJSONObject(i - 1).getDouble("spouseRothIRA") * growthRate);
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i - 1).getDouble("user401k")
                                            + assets.getJSONObject(i - 1).getDouble("user401k") * growthRate);
                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i - 1).getDouble("spouse401k")
                                            + assets.getJSONObject(i - 1).getDouble("spouse401k") * growthRate);
                                    assets.getJSONObject(i).put("spouseIRA", 0);
                                    //////System.out.println("SPOUSE++++++"+assets.getJSONObject(i).getDouble("userRothIRA"));


                                } else {
                                    statusMsg.append("Insufficient balance in Non taxable account");
                                    ////System.out.println("Insufficient balance in Non taxable account");
                                    responseObj.put("status", "fail");
                                    responseObj.put("statusMsg", statusMsg);
                                    return responseObj;
                                }

                                assets.getJSONObject(i).put("nontaxable_investment_amount",
                                        assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i).getDouble("spouseIRA")
                                        + assets.getJSONObject(i).getDouble("userRothIRA")
                                        + assets.getJSONObject(i).getDouble("spouseRothIRA"));
                            }
                            assets.getJSONObject(i).put("taxable_investment_amount",
                                    assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                    + assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                    * growthRate);
                            assets.getJSONObject(i).put("savings", saving);
                            tax.getJSONObject(i).put("federalTax", federalJson.getDouble("federalTax"));
                            tax.getJSONObject(i).put("fICAMedicareTax", federalJson.getDouble("fICAMedicareTax"));
                            tax.getJSONObject(i).put("fICASocialSecurityTax",
                                    federalJson.getDouble("fICASocialSecurityTax"));
                            tax.getJSONObject(i).put("userSSTax", federalJson.getDouble("userSSTax"));
                            tax.getJSONObject(i).put("stateTax", federalJson.getDouble("stateTax"));
                            tax.getJSONObject(i).put("spouseSSTax", federalJson.getDouble("spouseSSTax"));
                            //////System.out.println("assets appp=="+assets.getJSONObject(i-1));
                            //////System.out.println("iraa 1---aparna"+assets.getJSONObject(i).getDouble("userIRA"));
                            //////System.out.println("rothIra====apar"+assets.getJSONObject(i - 1).getDouble("userRothIRA"));
                            //////System.out.println("roth ira previous==="+	assets.getJSONObject(i).getDouble("userRothIRA"));
                        }
                        //----------------------new code for529------------------------------
                        assets.getJSONObject(i).put("user529Plan", assets.getJSONObject(i-1).getDouble("user529Plan")+assets.getJSONObject(i-1).getDouble("user529Plan")*growthRate);
                        assets.getJSONObject(i).put("spouse529Plan", assets.getJSONObject(i-1).getDouble("spouse529Plan")+assets.getJSONObject(i-1).getDouble("spouse529Plan")*growthRate);
                        /*assets.getJSONObject(i).put("nontaxable_investment_amount",
								assets.getJSONObject(i).getDouble("nontaxable_investment_amount")
								+assets.getJSONObject(i).getDouble("spouse529Plan")
								 + assets.getJSONObject(i).getDouble("user529Plan"));*/
                        ////System.out.println("user------"+assets.getJSONObject(i-1).getDouble("nontaxable_investment_amount"));
                        ////System.out.println("user------"+assets.getJSONObject(i).getDouble("nontaxable_investment_amount"));
                        if (assets.getJSONObject(i).getInt("year") == userStartYear) {
                            userStartYear++;

                        }
                        if (assets.getJSONObject(i).getInt("year") == spouseStartYear) {

                            spouseStartYear++;
                        }
                        if (spouseAge != 0) {
                            spouseAge++;
                        }
                        if (userAge != 0) {
                            userAge++;
                        }
                    } else if (((userAge >= 70 && userAge <= 99) || (spouseAge >= 70 && userAge <= 99))) {
                        // -------------------if user age is >
                        // 70---------------------------------
                        double saving = assets.getJSONObject(i - 1).getDouble("savings");
                        double savingLimite = 0;

                        if (limits != null) {
                            if (emergencyFundFlag == false) {
                                savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2)
                                        + limits.getJSONObject(i).getInt("saving");
                            } else {

                                if (emergencyType.equals("Fix Amount")) {
                                    savingLimite = (emergencyFundAmt) + limits.getJSONObject(i).getInt("saving");
                                } else if (emergencyType.equals("Expense")) {

                                    savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                            * (Integer.parseInt(monthsOfExpense)))
                                            + limits.getJSONObject(i).getInt("saving");
                                } else if (emergencyType.equals("Income")) {

                                    savingLimite = (income.getJSONObject(i).getDouble("value") / 12
                                            * (Integer.parseInt(monthsOfIncome)))
                                            + limits.getJSONObject(i).getInt("saving");
                                }

                            }
                        } else {
                            if (emergencyFundFlag == false) {
                                savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 2);
                            } else {

                                if (emergencyType.equals("Fix Amount")) {
                                    savingLimite = (emergencyFundAmt);
                                } else if (emergencyType.equals("Expense")) {

                                    savingLimite = (userExpense.getJSONObject(i).getDouble("totalExpense") / 12
                                            * (Integer.parseInt(monthsOfExpense)));
                                } else if (emergencyType.equals("Income")) {

                                    savingLimite = (income.getJSONObject(i).getDouble("value") / 12
                                            * (Integer.parseInt(monthsOfIncome)));
                                }

                            }
                        }
                        saving = savingLimite;
                        contributionAmount = 0;

                        final double usersSSB = userIncome.getJSONObject(i).getDouble("value");
                        if (maritalStatus.equals("Yes")) {
                            spouseSSB = spouse_income.getJSONObject(i).getDouble("value");
                            yearlySSB = spouseSSB + usersSSB;

                            responseFromGrowthRate = investmentPortfolioImpl.growthRate(riskScore, 2, userAge,
                                    MongoDBConnection.investmentPortfolioData);
                        } else {
                            // ////System.out.println("Krishnan:: userIncome:: " +
                            // yearlySSB);
                            yearlySSB = userIncome.getJSONObject(i).getDouble("value");
                            responseFromGrowthRate = investmentPortfolioImpl.growthRate(riskScore, 1, userAge,
                                    MongoDBConnection.investmentPortfolioData);
                        }
                        growthRate = responseFromGrowthRate.getDouble("growthRate") / 100;
                        //////System.out.println("growthRate----------="+growthRate);
                        if (userIncome.length() <= i) {
                            userSSB = userIncome.getJSONObject(i - 1).getDouble("value");
                        } else {
                            userSSB = userIncome.getJSONObject(i).getDouble("value");
                        }

                        double userMinPercentage = 0;
                        double spouseMinPercentage = 0;
                        double minimumWithdrawal = 0;
                        boolean ageFlage = false;
                        // ////System.out.println("Mahi : spouseage in
                        // calingine:"+spouseAge);
                        if (spouseAge == 0 || ((spouseAge - userAge) < 10 && (userAge - spouseAge) < 10)) {
                            if (maritalStatus.equals("Yes") && spouseAge != 0) {
                                if (MongoDBConnection.distributionModelData.getMinimumPercentageWithdrawal()
                                        .containsKey("" + spouseAge)) {
                                    spouseMinPercentage = (MongoDBConnection.distributionModelData
                                            .getMinimumPercentageWithdrawal().get("" + spouseAge) / 100);
                                }
                            }
                            if (MongoDBConnection.distributionModelData.getMinimumPercentageWithdrawal()
                                    .containsKey("" + userAge)) {
                                userMinPercentage = (MongoDBConnection.distributionModelData
                                        .getMinimumPercentageWithdrawal().get("" + userAge) / 100);
                            }
                        } else {
                            ageFlage = true;
                            if(spouseAge>=70 && spouseAge<=100) {
                                // System.out.println("spouseeee agee "+spouseAge);
                                spouseMinPercentage = (MongoDBConnection.distributionModelData
                                        .getMinimumPercentageWithdrawal().get("" + spouseAge) / 100);
                            }
                            else {
                                spouseMinPercentage=0;
                            }

                            if(userAge>=70 && userAge<=100){
                                userMinPercentage = (MongoDBConnection.distributionModelData
                                        .getMinimumPercentageWithdrawal().get("" + userAge) / 100);
                            }
                            else {
                                userMinPercentage=0;
                            }
                            /*userMinPercentage = MongoDBConnection.distributionModelData.getMinDistribution()
                                                         .get("" + userAge).get(spouseAge - 30);*/
                        }
                        double user401kWithdrawal = 0;
                        double spouse401kWithdrawal = 0;
                        double userIRAWithdrawal = 0;
                        double spouseIRAWithdrawal = 0;
                        double userRouthIRAWithdrawal = 0;
                        double spouseRouthIRAWithdrawal = 0;
                        final double user529PlanWithdrawal = 0;
                        final double spouse529PlanWithdrawal = 0;
                        /*
                        if (ageFlage) {
                            spouseMinPercentage = userMinPercentage;
                        }
                         */
                        user401kWithdrawal = (assets.getJSONObject(i - 1).getDouble("user401k") * userMinPercentage);
                        spouse401kWithdrawal = (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                * spouseMinPercentage);
                        userIRAWithdrawal = (assets.getJSONObject(i - 1).getDouble("userIRA") * userMinPercentage);
                        spouseIRAWithdrawal = (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                * spouseMinPercentage);
                        userRouthIRAWithdrawal = (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                * userMinPercentage);
                        spouseRouthIRAWithdrawal = (assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                * spouseMinPercentage);
                        minimumWithdrawal = user401kWithdrawal + spouse401kWithdrawal + userIRAWithdrawal
                                + spouseIRAWithdrawal + userRouthIRAWithdrawal + spouseRouthIRAWithdrawal
                                + user529PlanWithdrawal + spouse529PlanWithdrawal;

                        double nontaxable_investment_amount = 0;
                        JSONObject federalJson = new JSONObject();

                        if (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") > 0) {

                            if (userMinPercentage != 0 || spouseMinPercentage != 0) {
                                if (maritalStatus.equals("Yes")) {
                                    if (spouseMinPercentage != 0 && userMinPercentage != 0) {
                                        if (userAge >= 70) {
                                            assets.getJSONObject(i).put("user401k",
                                                    (assets.getJSONObject(i - 1).getDouble("user401k")
                                                            - user401kWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                            - userRouthIRAWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("userIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                            - userIRAWithdrawal) * 1.025);


                                        } else

                                        {

                                            assets.getJSONObject(i).put("user401k",
                                                    (assets.getJSONObject(i - 1).getDouble("user401k") - 0) * 1.025);

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userRothIRA") - 0) * 1.025);

                                            assets.getJSONObject(i).put("userIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userIRA") - 0) * 1.025);

                                        }

                                        if (spouseAge >= 70)

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                            - spouse401kWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                                            - spouseRouthIRAWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                            - spouseIRAWithdrawal) * 1.025);

                                        }

                                        else

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    (assets.getJSONObject(i - 1).getDouble("spouse401k") - 0) * 1.025);

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseRothIRA") - 0)
                                                    * 1.025);

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseIRA") - 0) * 1.025);

                                        }

                                        assets.getJSONObject(i).put("nontaxable_investment_amount",
                                                nontaxable_investment_amount);

                                    } else if (spouseMinPercentage == 0 && userMinPercentage != 0) {

                                        if (userAge >= 70)

                                        {

                                            assets.getJSONObject(i).put("user401k",
                                                    (assets.getJSONObject(i - 1).getDouble("user401k")
                                                            - user401kWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                            - userRouthIRAWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("userIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userIRA")
                                                            - userIRAWithdrawal) * 1.025);

                                        } else

                                        {

                                            assets.getJSONObject(i).put("user401k",
                                                    (assets.getJSONObject(i - 1).getDouble("user401k") - 0) * 1.025);

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userRothIRA") - 0) * 1.025);

                                            assets.getJSONObject(i).put("userIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("userIRA") - 0) * 1.025);

                                        }
                                        // if(spouseAge>=70)

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                    + (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                            * growthRate));

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                                    + (assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                                            * growthRate));

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                    + (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                            * growthRate));

                                        }

                                    } else {

                                        // if(userAge >= 70)

                                        {

                                            assets.getJSONObject(i).put("user401k", assets.getJSONObject(i - 1)
                                                    .getDouble("user401k")
                                                    + (assets.getJSONObject(i - 1).getDouble("user401k") * growthRate));

                                            assets.getJSONObject(i).put("userRothIRA",
                                                    assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                    + (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                            * growthRate));

                                            assets.getJSONObject(i).put("userIRA", assets.getJSONObject(i - 1)
                                                    .getDouble("userIRA")
                                                    + (assets.getJSONObject(i - 1).getDouble("userIRA") * growthRate));

                                        }

                                        if (spouseAge >= 70)

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    (assets.getJSONObject(i - 1).getDouble("spouse401k")
                                                            - spouse401kWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseRothIRA")
                                                            - spouseRouthIRAWithdrawal) * 1.025);

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseIRA")
                                                            - spouseIRAWithdrawal) * 1.025);

                                        }

                                        else

                                        {

                                            assets.getJSONObject(i).put("spouse401k",
                                                    (assets.getJSONObject(i - 1).getDouble("spouse401k") - 0) * 1.025);

                                            assets.getJSONObject(i).put("spouseRothIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseRothIRA") - 0)
                                                    * 1.025);

                                            assets.getJSONObject(i).put("spouseIRA",
                                                    (assets.getJSONObject(i - 1).getDouble("spouseIRA") - 0) * 1.025);

                                        }

                                    }
                                } else {

                                    if (userAge >= 70)

                                    {

                                        assets.getJSONObject(i).put("user401k",
                                                (assets.getJSONObject(i - 1).getDouble("user401k") - user401kWithdrawal)
                                                * 1.025);

                                        assets.getJSONObject(i).put("spouse401k", 0);

                                        assets.getJSONObject(i).put("userRothIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userRothIRA")
                                                        - userRouthIRAWithdrawal) * 1.025);

                                        assets.getJSONObject(i).put("spouseRothIRA", 0);

                                        assets.getJSONObject(i).put("userIRA",
                                                (assets.getJSONObject(i - 1).getDouble("userIRA") - userIRAWithdrawal)
                                                * 1.025);

                                        assets.getJSONObject(i).put("spouseIRA", 0);

                                    }

                                }

                            }

                            if (userStartYearForCap > assets.getJSONObject(i).getInt("year")) {
                                assets.getJSONObject(i).put("user401k",
                                        assets.getJSONObject(i).getDouble("user401k") + user401kContribution);

                            }
                            if (spouseStartYearForCap > assets.getJSONObject(i).getInt("year")) {
                                assets.getJSONObject(i).put("spouse401k",
                                        assets.getJSONObject(i).getDouble("spouse401k") + spouse401kContribution);

                            }
                            nontaxable_investment_amount = assets.getJSONObject(i).getDouble("spouseIRA")
                                    + assets.getJSONObject(i).getDouble("user401k")
                                    + assets.getJSONObject(i).getDouble("spouse401k")
                                    + assets.getJSONObject(i).getDouble("userRothIRA")
                                    + assets.getJSONObject(i).getDouble("spouseRothIRA")
                                    + assets.getJSONObject(i).getDouble("userIRA");
                            assets.getJSONObject(i).put("nontaxable_investment_amount", nontaxable_investment_amount);

                            federalJson = calRetirementTax(fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                    fillingExemtion.getJSONObject(i).getString("fillingStatus"), fin_id, incomeRothIRA,
                                    yearlySSB, userExpense.getJSONObject(i).getDouble("totalExpense"), user_id,
                                    minimumWithdrawal, userAge, spouseAge, userSSB, spouseSSB, contributionAmount,
                                    goal_id, year, maritalStatus, kidBirthYear, state, kidcount, registrationYear,
                                    childArray, houseStatus, houseValue, housing_expense, startYear,
                                    remainingMortgageOriginal, remainingMortgageInterestRate, spouseBirthYear,
                                    userBirthYear, spouseAgeInFinPlan, spouseRetirementStartYear,
                                    userRetirementStartYear, stateTaxValue,equity,houseAppreciationRate,registrationHouseStatus);

                            if (federalJson.getDouble("incomeIRA") > 0) {
                                final double accessMony = federalJson.getDouble("incomeIRA");
                                if (accessMony < assets.getJSONObject(i).getDouble("user401k")) {
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i).getDouble("user401k") - (accessMony * 1.025));
                                } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i).getDouble("spouse401k") - (tmp * 1.025));

                                } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userRothIRA"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k")
                                            + assets.getJSONObject(i).getDouble("spouse401k");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k", 0);
                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i).getDouble("userRothIRA") - (tmp * 1.025));

                                } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i - 1).getDouble("spouseIRA"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k")
                                            + assets.getJSONObject(i).getDouble("spouse401k")
                                            + assets.getJSONObject(i).getDouble("userIRA");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k", 0);
                                    assets.getJSONObject(i).put("userIRA", 0);
                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i).getDouble("spouseIRA") - (tmp * 1.025));

                                } else if (accessMony > (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i).getDouble("spouseIRA"))) {
                                    goalFeasibility = false;
                                    statusMsg.append("Insufficient balance in non-taxable account");
                                    // ////System.out.println(" Hello Insufficient
                                    // balance in non-taxable
                                    // account"+assets.getJSONObject(i).getDouble("user401k")+"spouse"+assets.getJSONObject(i).getDouble("spouse401k"));
                                    break;
                                }
                                assets.getJSONObject(i).put("nontaxable_investment_amount",
                                        (assets.getJSONObject(i).getDouble("user401k")
                                                + assets.getJSONObject(i).getDouble("spouse401k")
                                                + assets.getJSONObject(i).getDouble("userIRA")
                                                + assets.getJSONObject(i).getDouble("spouseIRA")
                                                + assets.getJSONObject(i).getDouble("userRothIRA")
                                                + assets.getJSONObject(i).getDouble("spouseRothIRA")));
                                assets.getJSONObject(i).put("taxable_investment_amount",
                                        assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                        + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                                * growthRate));


                            } else {

                                double accessMony = federalJson.getDouble("withdrawalFromRetirementAccounts")
                                        - contributionAmount - federalJson.getDouble("federalTax")
                                        - federalJson.getDouble("stateTax") - federalJson.getDouble("userSSTax")
                                        - federalJson.getDouble("spouseSSTax")
                                        - federalJson.getDouble("fICAMedicareTax");
                                if (accessMony < 0) {
                                    accessMony = federalJson.getDouble("withdrawalFromRetirementAccounts");
                                }

                                if (accessMony > 0) {
                                    assets.getJSONObject(i).put("taxable_investment_amount", assets.getJSONObject(i - 1)
                                            .getDouble("taxable_investment_amount") + accessMony
                                            + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                                    * growthRate));
                                }

                            }

                            assets.getJSONObject(i).put("savings", saving);

                            if (assets.getJSONObject(i).getInt("year") == userStartYear) {
                                userStartYear++;
                            }
                            if (assets.getJSONObject(i).getInt("year") == spouseStartYear) {

                                spouseStartYear++;
                            }
                            if (spouseAge != 0) {
                                spouseAge++;
                            }
                            userAge++;

                        } else if (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") <= 0
                                && yearlySSB < userExpense.getJSONObject(i).getDouble("totalExpense")) {

                            statusMsg.append("Insufficient balance in Non taxable account");
                            ////System.out.println("Assest going negative in retirement" +assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount"));
                            responseObj.put("status", "fail");
                            responseObj.put("statusMsg", statusMsg);
                            return responseObj;
                        } else {

                            federalJson = calRetirementTax(fillingExemtion.getJSONObject(i).getInt("noOfExcemtion"),
                                    fillingExemtion.getJSONObject(i).getString("fillingStatus"), fin_id, incomeRothIRA,
                                    yearlySSB, userExpense.getJSONObject(i).getDouble("totalExpense"), user_id,
                                    minimumWithdrawal, userAge, spouseAge, userSSB, spouseSSB, contributionAmount,
                                    goal_id, year, maritalStatus, kidBirthYear, state, kidcount, registrationYear,
                                    childArray, houseStatus, houseValue, housing_expense, startYear,
                                    remainingMortgageOriginal, remainingMortgageInterestRate, spouseBirthYear,
                                    userBirthYear, spouseAgeInFinPlan, spouseRetirementStartYear,
                                    userRetirementStartYear, stateTaxValue,equity,houseAppreciationRate,registrationHouseStatus);
                            if (federalJson.getDouble("incomeIRA") > 0) {
                                final double accessMony = federalJson.getDouble("incomeIRA");
                                if (accessMony < assets.getJSONObject(i).getDouble("user401k")) {
                                    assets.getJSONObject(i).put("user401k",
                                            assets.getJSONObject(i).getDouble("user401k") - accessMony
                                            + assets.getJSONObject(i).getDouble("user401k") * growthRate);
                                } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k",
                                            assets.getJSONObject(i).getDouble("spouse401k") - tmp
                                            + assets.getJSONObject(i).getDouble("spouse401k") * growthRate);

                                } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userRothIRA"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k")
                                            + assets.getJSONObject(i).getDouble("spouse401k");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k", 0);
                                    assets.getJSONObject(i).put("userRothIRA",
                                            assets.getJSONObject(i).getDouble("userRothIRA") - tmp
                                            + assets.getJSONObject(i).getDouble("userIRA") * growthRate);

                                } else if (accessMony < (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i - 1).getDouble("spouseIRA"))) {
                                    final double tmp = accessMony - assets.getJSONObject(i).getDouble("user401k")
                                            + assets.getJSONObject(i).getDouble("spouse401k")
                                            + assets.getJSONObject(i).getDouble("userIRA");
                                    assets.getJSONObject(i).put("user401k", 0);
                                    assets.getJSONObject(i).put("spouse401k", 0);
                                    assets.getJSONObject(i).put("userIRA", 0);
                                    assets.getJSONObject(i).put("spouseIRA",
                                            assets.getJSONObject(i).getDouble("spouseIRA") - tmp
                                            + assets.getJSONObject(i).getDouble("spouseIRA") * growthRate);

                                } else if (accessMony > (assets.getJSONObject(i).getDouble("user401k")
                                        + assets.getJSONObject(i).getDouble("spouse401k")
                                        + assets.getJSONObject(i).getDouble("userIRA")
                                        + assets.getJSONObject(i).getDouble("spouseIRA")
                                        + assets.getJSONObject(i).getDouble("userRothIRA")
                                        + assets.getJSONObject(i).getDouble("spouseRothIRA"))) {
                                    goalFeasibility = false;
                                    statusMsg.append("Insufficient balance in non-taxable account");
                                    ////System.out.println("Hello Insufficien balance in non-taxable account"+assets.getJSONObject(i).getDouble("user401k")+"spouse"+assets.getJSONObject(i).getDouble("spouse401k"));
                                    break;
                                }
                                assets.getJSONObject(i).put("nontaxable_investment_amount",
                                        (assets.getJSONObject(i).getDouble("user401k")
                                                + assets.getJSONObject(i).getDouble("spouse401k")
                                                + assets.getJSONObject(i).getDouble("userIRA")
                                                + assets.getJSONObject(i).getDouble("spouseIRA")
                                                + assets.getJSONObject(i).getDouble("userRothIRA")
                                                + assets.getJSONObject(i).getDouble("spouseRothIRA")));
                                assets.getJSONObject(i).put("taxable_investment_amount",
                                        assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                        + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                                * growthRate));

                            } else {

                                final double accessMony = federalJson.getDouble("withdrawalFromRetirementAccounts")
                                        - contributionAmount - federalJson.getDouble("federalTax")
                                        - federalJson.getDouble("stateTax") - federalJson.getDouble("userSSTax")
                                        - federalJson.getDouble("spouseSSTax")
                                        - federalJson.getDouble("fICAMedicareTax");

                                assets.getJSONObject(i).put("taxable_investment_amount",
                                        assets.getJSONObject(i - 1).getDouble("taxable_investment_amount") + accessMony
                                        + (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount")
                                                * growthRate));
                                assets.getJSONObject(i).put("nontaxable_investment_amount", 0);
                                assets.getJSONObject(i).put("user401k", 0);
                                assets.getJSONObject(i).put("spouse401k", 0);
                                assets.getJSONObject(i).put("userRothIRA", 0);
                                assets.getJSONObject(i).put("spouseRothIRA", 0);
                                assets.getJSONObject(i).put("userIRA", 0);
                                assets.getJSONObject(i).put("spouseIRA", 0);

                            }

                            assets.getJSONObject(i).put("savings", saving);

                        }

                        if (assets.getJSONObject(i).getInt("year") == year) {
                            final double nontaxtableWithdrawlAmount = assets.getJSONObject(i).getDouble(
                                    "nontaxable_investment_amount")/*- deductions.getJSONObject(i).getDouble("taxable")*/;
                            final double nontaxtableAmount = assets.getJSONObject(i).getDouble(
                                    "taxable_investment_amount")/*- deductions.getJSONObject(i).getDouble("taxable")*/;

                            if (nontaxtableWithdrawlAmount < 0 || nontaxtableAmount < 0) {
                                goalFeasibility = false;
                                ////System.out.println("the money"+assets.getJSONObject(i).getDouble("nontaxable_investment_amount"));
                                statusMsg.append("Insufficient balance in taxable account");
                                ////System.out.println("Assest going negative in retirement");
                                break;
                            } else {
                                goalFeasibility = true;

                            }
                        }
                        tax.getJSONObject(i).put("federalTax", federalJson.getDouble("federalTax"));
                        tax.getJSONObject(i).put("fICAMedicareTax", federalJson.getDouble("fICAMedicareTax"));
                        tax.getJSONObject(i).put("fICASocialSecurityTax",
                                federalJson.getDouble("fICASocialSecurityTax"));
                        tax.getJSONObject(i).put("userSSTax", federalJson.getDouble("userSSTax"));
                        tax.getJSONObject(i).put("stateTax", federalJson.getDouble("stateTax"));
                        tax.getJSONObject(i).put("spouseSSTax", federalJson.getDouble("spouseSSTax"));
                        //----------------------new code for529------------------------------
                        nonTaxableInvestmentIncome = (assets.getJSONObject(i - 1).getDouble("nontaxable_investment_amount") * (portfolioDividend + portfolioInterest))
                                / 100;

                        taxableInvestmentIncome = (assets.getJSONObject(i - 1).getDouble("taxable_investment_amount") * (portfolioDividend + portfolioInterest) * 0.85)
                                / 100;

                        assets.getJSONObject(i).put("nonTaxableInvestmentIncome", nonTaxableInvestmentIncome);

                        assets.getJSONObject(i).put("taxableInvestmentIncome", taxableInvestmentIncome);

                        assets.getJSONObject(i).put("user529Plan", assets.getJSONObject(i-1).getDouble("user529Plan")+assets.getJSONObject(i-1).getDouble("user529Plan")*growthRate);
                        assets.getJSONObject(i).put("spouse529Plan", assets.getJSONObject(i-1).getDouble("spouse529Plan")+assets.getJSONObject(i-1).getDouble("spouse529Plan")*growthRate);
                        /*assets.getJSONObject(i).put("nontaxable_investment_amount",
								assets.getJSONObject(i).getDouble("nontaxable_investment_amount")
								+assets.getJSONObject(i).getDouble("spouse529Plan")
								 + assets.getJSONObject(i).getDouble("user529Plan"));*/
                        //////System.out.println("user------"+assets.getJSONObject(i-1).getDouble("user529Plan"));
                        //////System.out.println("user------"+assets.getJSONObject(i).getDouble("user529Plan"));
                    }

                }

            }

            if (goalFeasibility == true) {

                responseObj.put("status", "success");

                responseObj.put("assets", assets);

                responseObj.put("tax", tax);

                responseObj.put("statusMsg", "Goal Applied successfully");

            } else {

                responseObj.put("status", "fail");

                responseObj.put("statusMsg", statusMsg);

            }

        } catch (final Exception e) {

            e.printStackTrace();

        }

        // ////System.out.println("Elapsed time for sweeping of money engine: " +
        // (System.nanoTime() - startTime) / 1000000 + " milli seconds");

        return responseObj;

    }






    // ------------------------------------------END----------------------------------
    // //------------------------------------------END----------------------------------

}