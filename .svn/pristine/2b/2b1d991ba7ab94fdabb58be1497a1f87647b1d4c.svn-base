package com.mongorest.olahtek.service;

import java.util.ArrayList;
import java.util.Calendar;

import org.jongo.Jongo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongorest.olahtek.model.HomeInsurance;
import com.mongorest.olahtek.model.HouseMedianValue;
import com.mongorest.olahtek.model.MC;
import com.mongorest.olahtek.model.RateAdjustmentFactor;
import com.mongorest.olahtek.model.States;
import com.mongorest.olahtek.model.StatetaxModel;
import com.mongorest.olahtek.model.User;

@Service("mCalculator")
@Transactional
public class MortgageCalculatorImpl implements MortgageCalculatorService {
    private final MongoClient mongoClient = MongoDBConnection.mongoClient;
    private final MongoDatabase mongoDb = MongoDBConnection.mongoDb;
    private final Jongo jongo = MongoDBConnection.jongo;
    private final String mongoDBName = MongoDBConnection.mongoDBName;
    private final DB db = MongoDBConnection.db;

    private int a;
    private int remmort;
    private final Calendar cal = Calendar.getInstance();
    private final int currentYear = cal.get(Calendar.YEAR);
    private static final int THOUSAND = 1000;
    private static final double HUNDRED = 100;
    private static final double TWELE = 12;

    @Override
    public JSONObject calculateMC(final MC mc) {
        final String forrental = mc.getForrental();
        //final String user_id = mc.getUser_id();
        // final String filingstatus = mc.getFilingstatus();
        final long managementfee = Long.parseLong(mc.getManagementfee());
        final long rentalincome = Long.parseLong(mc.getRentalincome()) * 12;
        final long propertyland = Long.parseLong(mc.getPropertyland());

        System.out.println("forrental"+forrental);
        System.out.println("managementfee"+managementfee);
        System.out.println("rentalincome"+rentalincome);
        System.out.println("propertyland"+propertyland);
        final String state = mc.getState();
        final String states = mc.getStates();
        final long homevalue = Long.parseLong(mc.getHomevalue());
        long downpayment = Long.parseLong(mc.getDownpayment());
        final float interestrate = Float.parseFloat(mc.getInterestrate());
        final int years = Integer.parseInt(mc.getLoanterm()) * 12;
        final float propertytax = Float.parseFloat(mc.getPropertytax());
        final int pmi = Integer.parseInt(mc.getPmi());
        final int hoafee = Integer.parseInt(mc.getHoafee());
        final int maintenance = Integer.parseInt(mc.getMaintenance());
        final Double homeinsurance = Double.parseDouble(mc.getHomeinsurance());
        final int years1 = Integer.parseInt(mc.getLoanterm());
        final String filingstatus = mc.getFilingstatus();
        final String fillingstatus = mc.getFilingstatus();
        final int highesttaxrate = Integer.parseInt(mc.getHighesttaxrate());
        final int flag = Integer.parseInt(mc.getFlag());

        downpayment = (long) (homevalue * downpayment / HUNDRED);
        /*-----------------------------standard Deduction----------------------------------------*/

        int standardDeduction = 0;
        String modelFillingStatus = null;
        final String userid = mc.getUser_id();

        if (flag == 1) {
            final User details = MongoDBConnection.userColl.findOne("{_id:#}", userid).as(User.class);
            final String maritalStatus = details.getMarital_status();
            final int childcount = details.getChildCount();
            if (maritalStatus.equals("Yes")) {
                modelFillingStatus = "marriedFilingJoint";

            } else if (maritalStatus.equals("No") && childcount > 0) {

                modelFillingStatus = "headOfHousehold";
            } else if (maritalStatus.equals("No") && childcount == 0) {

                modelFillingStatus = "single";
            }
        } else if (flag == 2) {
            if (filingstatus.equals("Married Filing Jointly")) {
                modelFillingStatus = "marriedFilingJoint";

            } else if (filingstatus.equals("Head of Household")) {

                modelFillingStatus = "headOfHousehold";
            } else if (filingstatus.equals("Single")) {

                modelFillingStatus = "single";
            }

        }

        JSONObject constantVal = new JSONObject();
        try {
            constantVal = MongoDBConnection.constJson.getJSONObject(modelFillingStatus);
        } catch (final JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            standardDeduction = constantVal.getInt("standardDeduction");
        } catch (final JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        /*---------------------------------standard Deduction---------------------------------------------*/

        final JSONObject mobject = new JSONObject();
        try {
            final int others = (int) (pmi + hoafee + maintenance + homeinsurance);
            final float proptax = homevalue * propertytax / 100;
            final int pmi1 = pmi;
            final int loanpayoff = years1 + currentYear - 1;
            final float interestrate1 = interestrate;

            /*-------------------Calculating Mortgage Amount---------------------------*/

            final long mortgageAmount = homevalue - downpayment;
            final double exactanualmorgage = calAnualMorgage(years, interestrate, mortgageAmount);
            final double totalAnualhouseExpense = exactanualmorgage * years1;
            final double totalinterest = totalAnualhouseExpense - mortgageAmount;
            final double monthlymortgage = exactanualmorgage / 12;
            // ----------------------------Remainingmortgage----------------------------------//
            double mA = mortgageAmount;
            final ArrayList<Double> remmor = new ArrayList<Double>();
            final JSONArray output = new JSONArray();
            double remmortgagefirst = 0;
            double annualmonthlymortgage = 0;
            double firstyearinterest = 0;
            double firstyearinterest1 = 0;
            double taxablededuction = 0;
            double taxsaving = 0;
            double oldmort = 0;
            double newmort = 0;
            double principal = 0;
            final ArrayList<Double> princ = new ArrayList<Double>();
            final ArrayList<Double> interestpaid = new ArrayList<Double>();
            oldmort = mortgageAmount;
            for (int yearcount = currentYear; yearcount < currentYear + years1; yearcount++) {
                double remainingmortgage = mA * Math.pow(1 + interestrate / HUNDRED / TWELE, TWELE)
                        - (Math.pow(1 + interestrate / HUNDRED / TWELE, TWELE) - 1)
                        * (monthlymortgage / (interestrate / HUNDRED / TWELE));
                if (remainingmortgage < 0) {
                    remainingmortgage = 0;
                }
                remmor.add(remainingmortgage);
                mA = remainingmortgage;
                newmort = remainingmortgage;
                principal = oldmort - newmort;
                princ.add(principal);
                oldmort = newmort;

                double annualmonthlymortgage1 = 0;

                annualmonthlymortgage = monthlymortgage * TWELE;
                annualmonthlymortgage1 = annualmonthlymortgage - principal;
                interestpaid.add(annualmonthlymortgage1);

                if (yearcount == currentYear) {
                    remmortgagefirst = remainingmortgage;
                }
                firstyearinterest = mortgageAmount - remmortgagefirst;
                firstyearinterest1 = annualmonthlymortgage - firstyearinterest;

                taxablededuction = firstyearinterest1 + proptax;
                if(taxablededuction<standardDeduction){
                    taxsaving=0;
                }else{

                    taxsaving = (taxablededuction - standardDeduction) * highesttaxrate / HUNDRED;
                    /* interest first year------------------- */
                }
                final double homeequ = homevalue - remainingmortgage;

                final JSONObject b = new JSONObject();

                b.put("princ", principal);
                b.put("interestpaid", annualmonthlymortgage1);
                b.put("remmort", remainingmortgage);
                b.put("houseEquity", homeequ);
                b.put("year", yearcount);

                output.put(b);

            }

            // -------------------------------home
            // equity-------------------------------------//

            final ArrayList<Double> homeequity = new ArrayList<Double>();
            for (int homecount = 0; homecount < remmor.size(); homecount++) {
                final double homeequ = homevalue - remmor.get(homecount);
                homeequity.add(homeequ);

            }
            //--------------------classify owner or rental--------------------//
            long depreciation=0;
            long depreciation1=0;
            long depreciation2=0;
            long depreciation3=0;

            if(years1<=27){
                depreciation = (long) (propertyland / 27.5);
            }else if(years1==28){
                depreciation1 = (long) (propertyland / 27.5);
                depreciation2 = propertyland / 55;
                depreciation = depreciation1+depreciation2;
            }else{
                depreciation1 = (long) (propertyland / 27.5);
                depreciation2 = propertyland / 55;
                depreciation3 = 0;
                depreciation =depreciation1+depreciation2+depreciation3;
            }

            System.out.println("depreciation"+depreciation);
            System.out.println("monthlymortgage"+monthlymortgage);



            final long expenses=homevalue*1/100+managementfee;
            final long cashflow=rentalincome-expenses;
            final long taxableincome=rentalincome-expenses-depreciation;
            final double cost=monthlymortgage*12 + expenses;
            final double costwithdepreciation= depreciation + cost;
            final double profitorloss= rentalincome - costwithdepreciation;
            if(rentalincome>profitorloss){
                System.out.println("PROFIT");
            }else{
                System.out.println("LOSS");
            }


            System.out.println("cashflow"+cashflow);
            System.out.println("taxableincome"+taxableincome);
            System.out.println("cost"+cost);
            System.out.println("profitorloss"+profitorloss);

            //---------------------------------------------------------------------------------------//
            //Object marriedFilingJoint;

            if(modelFillingStatus.equals("marriedFilingJoint")){
                modelFillingStatus = "Married Filing Jointly";
            }else if(modelFillingStatus.equals("headOfHousehold")){
                modelFillingStatus = "Head of Household";
            }else if(modelFillingStatus.equals("single")){
                modelFillingStatus = "Single";
            }


            final JSONObject fdiobject = new JSONObject();
            final String fillingStatus=modelFillingStatus;
            System.out.println("fillingStatus is"+fillingStatus);
            final double federaltal;
            int noOfExcemtion = 0;
            if(fillingStatus.equals("Single")){
                noOfExcemtion=1;
            }else if(fillingStatus.equals("Married Filing Jointly")){
                noOfExcemtion=2;
            }else if(fillingStatus.equals("Head of Household")){
                noOfExcemtion=1;
            }

            // System.out.println("noOfExcemtion"+noOfExcemtion);
            final double taxableIncome=0;
            final int currentYear=0;
            final int kidNOAge=0;
            // StatetaxModel stateTaxValue=null;
            final String maritalStatus="No";
            final JSONArray childArray=null;
            final double taxableInvestmentAmount=0;
            final double nonTaxableInvestmentAmount=0;
            final int spouseStartYear=0;
            final int userStartYear=0;
            final double income=profitorloss;
            final String user_id=userid ;
            final String fin_id=null;
            final double userIncome=profitorloss;
            final double spouseIncome=0;
            final double federalTax=0;
            final double fICAMedicareTa=0;
            final int year=0;
            final int userAge=0;
            final int spouseAge=0;
            final JSONArray kidBirthYear=null;
            final int kidcount=0;
            final int registrationYear=0;

            final double houseValue=0;
            final JSONArray housing_expense=null;
            final long startYear=0;
            final double remainingMortgageOriginal=0;
            final double  remainingMortgageInterestRate=0;
            final double minimumwithdrawal=0;
            final double fICAMedicareTax=0;
            final double sateTax=0;
            final double Medicaretax=0;
            final double    MaxSTDItem =0;

            final StatetaxModel stateTaxValue = MongoDBConnection.stateTaxColl.findOne("{'statename':#}", state)
                    .as(StatetaxModel.class);
            final User login = MongoDBConnection.userColl.findOne("{_id:#}", mc.getUser_id()).as(User.class);
            String houseStatus=null;
            if(login!=null)
            {
                houseStatus=login.getHouseStatus();
            }

            //  final String houseStatus = null;
            //CalculationEngine.federalTaxParamCal(income, state, fillingStatus, noOfExcemtion, currentYear, stateTaxValue, maritalStatus, childArray, houseStatus, userIncome, spouseIncome, user_id);

            //  public static JSONObject calTaxPerYear(int spouseStartYear, int userStartYear, double income, String user_id,String fin_id, double userIncome, double spouseIncome, int year, int noOfExcemtion, String fillingStatus,int userAge, int spouseAge, String maritalStatus, JSONArray kidBirthYear, String state, int kidcount,int registrationYear, JSONArray childArray, String houseStatus, double houseValue,JSONObject housing_expense, long startYear, double remainingMortgageOriginal,double remainingMortgageInterestRate, double minimumwithdrawal, StatetaxModel stateTaxValue,double taxableInvestmentAmount,double nonTaxableInvestmentAmount,double Gross_Annual_Income ) {
            //  System.out.println( "calTaxPerYear values are"+ CalculationEngine.calTaxPerYear(spouseStartYear, userStartYear, income, user_id, fin_id, userIncome, spouseIncome, year, noOfExcemtion, fillingStatus, userAge, spouseAge, maritalStatus, kidBirthYear, state, kidcount, registrationYear, childArray, houseStatus, houseValue, housing_expense, startYear, remainingMortgageOriginal, remainingMortgageInterestRate, minimumwithdrawal, stateTaxValue, taxableInvestmentAmount, nonTaxableInvestmentAmount));
            fdiobject.put("federal", CalculationEngine.calTaxPerYear(spouseStartYear, userStartYear, income, user_id,
                    fin_id,userIncome, spouseIncome, year, noOfExcemtion,
                    fillingStatus, userAge, spouseAge, maritalStatus, kidBirthYear,
                    state, kidcount, registrationYear, childArray, houseStatus, houseValue,
                    housing_expense, startYear, remainingMortgageOriginal, remainingMortgageInterestRate,
                    minimumwithdrawal,
                    stateTaxValue, taxableInvestmentAmount, nonTaxableInvestmentAmount,null,null, MaxSTDItem,null));
            //fdiobject.put("federal",CalculationEngine.federalTaxParamCal(income, state, fillingStatus, noOfExcemtion, currentYear, stateTaxValue, maritalStatus, childArray, houseStatus, userIncome, spouseIncome, user_id));

            fdiobject.put("income", profitorloss);
            fdiobject.put("status", "success");

            final double stateTax = 0;
            final double fICASocialSecurityTax = 0;
            // final long TotalIncomeTaxes = (long) (federalTax+fICAMedicareTax+fICASocialSecurityTax+stateTax);
            //System.out.println("TotalIncomeTaxes"+TotalIncomeTaxes);
            //String loudScreaming = json.getJSONObject("fdiobject").getString("fICAMedicareTax");
            System.out.println("json data from restTER-------" + fdiobject);
            //final DecimalFormat df = new DecimalFormat("###.##");
            final double a= fdiobject.getJSONObject("federal").getDouble("federalTax");
            final double b= fdiobject.getJSONObject("federal").getDouble("fICAMedicareTax");
            final double c=  fdiobject.getJSONObject("federal").getDouble("fICASocialSecurityTax");
            final double d=  fdiobject.getJSONObject("federal").getDouble("stateTax");
            double additionaltax = 0;
            double incomeaftertaxes = 0;
            double taxbenefit = 0;
            double costaftertax =0;
            // final double e=  fdiobject.getJSONObject("federal").getDouble("income");
            // final double TotalIncomeTaxes = federalTax+fICAMedicareTax+fICASocialSecurityTax+stateTax;
            if(profitorloss>0){
                additionaltax =a+b+c+d;
                incomeaftertaxes =profitorloss-a-b-c-d;
            }
            else if(profitorloss<0){

                taxbenefit = (a+b+c+d) * -1;
                costaftertax = taxbenefit+profitorloss * -1;
            }



            System.out.println("additionaltax"+additionaltax);
            System.out.println("incomeaftertaxes"+incomeaftertaxes);


            System.out.println("taxbenefit"+taxbenefit);

            System.out.println("costaftertax"+costaftertax);



            //System.out.println("BALA"+fdiobject.getJSONObject("federal").getInt("fICAMedicareTax"));

            //--------------------------------------------------------------------------------------------------//
            mobject.put("pmi1", pmi1);
            mobject.put("interestrate1", interestrate1);
            mobject.put("others", others);
            mobject.put("proptax", proptax);
            mobject.put("loanpayoff", loanpayoff);
            mobject.put("monthlymortgage", monthlymortgage);
            mobject.put("exactanualmorgage", exactanualmorgage);
            mobject.put("totalAnualhouseExpense", totalAnualhouseExpense);
            mobject.put("totalinterest", totalinterest);
            mobject.put("taxablededuction", taxablededuction);
            mobject.put("taxsaving", taxsaving);
            mobject.put("mortgageAmount", mortgageAmount);
            mobject.put("firstyearinterest1", firstyearinterest1);
            mobject.put("additionaltax", additionaltax);
            mobject.put("incomeaftertaxes", incomeaftertaxes);
            mobject.put("taxbenefit", taxbenefit);
            mobject.put("costaftertax", costaftertax);
            mobject.put("status", "success");
            mobject.put("remainingmortgage", output);

        } catch (final Exception e) {

            e.printStackTrace();
        }
        return mobject;
    }

    /*------------------------------------annual mortgage------------------------------------*/

    double calAnualMorgage(final int years, final float interestrate, final long mortgageAmount) {
        double exactanualmorgage = Math.pow(1 + interestrate / HUNDRED / TWELE, years)
                * (interestrate / HUNDRED / TWELE) / (Math.pow(1 + interestrate / HUNDRED / TWELE, years) - 1)
                * mortgageAmount;
        if (Double.isNaN(exactanualmorgage)) {
            exactanualmorgage = 0;
        }

        return exactanualmorgage * TWELE;
    }

    @Override
    public JSONObject getHomeInsurance(final MC mc) {
        final JSONObject response = new JSONObject();
        try {
            final String states = mc.getStates();
            final HomeInsurance home = MongoDBConnection.homeinsuranceColl.findOne("{state:#}", states)
                    .as(HomeInsurance.class);

            response.put("status", "success");
            if (home != null) {
                response.put("annual_Homeowners_Insurance", home.getAnnual_Homeowners_Insurance());
            }

        } catch (final JSONException e) {

            e.printStackTrace();
        }
        return response;
    }

    /*
     * public JSONObject getHomeValue(final MC mc) { final JSONObject response =
     * new JSONObject(); try { final String city = mc.getCity(); final String
     * states = mc.getStates(); final States cty =
     * MongoDBConnection.stateColl.findOne("{Name:#}", states).as(States.class);
     * response.put("status", "success"); if (cty != null) {
     *
     * final ObjectMapper jsonMapper = new ObjectMapper(); final JSONObject
     * cities = new JSONObject(jsonMapper.writeValueAsString(cty)); for (int i =
     * 0; i < cities.getJSONArray("cities").length(); i++) { final String
     * citydemo =
     * cities.getJSONArray("cities").getJSONObject(i).getString("city"); if
     * (citydemo.equals(city)) { final double houseValue =
     * cities.getJSONArray("cities").getJSONObject(i).getDouble("housevalue");
     * response.put("House_Value", roundUpNumberByUsingMultipleValue(houseValue,
     * THOUSAND)); break; } } } } catch (final Exception e) {
     *
     * e.printStackTrace(); } return response;
     *
     * }
     */

    int roundUpNumberByUsingMultipleValue(final double number, final int multiple) {

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

    @Override
    public JSONObject getLoanTerm(final MC mc) {

        final JSONObject response = new JSONObject();
        try {
            final String state = mc.getState();
            final String loanterm = mc.getLoanterm();
            final double interestRate = calculateIntrestRate(state, loanterm);
            response.put("interestRate", interestRate);
            response.put("status", "success");
        } catch (final JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response;

    }

    public float calculateIntrestRate(final String state, final String loanterm) {
        final States states = MongoDBConnection.stateColl.findOne("{Name:#}", state).as(States.class);
        final String constatntScore = "760-850";
        final RateAdjustmentFactor rateFactors = MongoDBConnection.rateAdjustmentColl
                .findOne("{creditScore:#}", constatntScore).as(RateAdjustmentFactor.class);
        float intrestRateFromApi = 0.0f;
        float intrestRateFromtable = 0.0f;
        float interest = 0.0f;
        if (loanterm.equals("30-year fixed")) {
            intrestRateFromApi = Float.parseFloat(states.getThirtyYearFixed());
            intrestRateFromtable = Float.parseFloat(rateFactors.getThirtyYearFixed());
        } else if (loanterm.equals("15-year fixed")) {
            intrestRateFromApi = Float.parseFloat(states.getFifteenYearFixed());
            intrestRateFromtable = Float.parseFloat(rateFactors.getFifteenYearFixed());
        } else if (loanterm.equals("jumbo 30-year fixed")) {
            intrestRateFromApi = Float.parseFloat(states.getThirtyYearFixed());
            intrestRateFromtable = Float.parseFloat(rateFactors.getThirtyYearFixedJumbo());
        } else if (loanterm.equals("jumbo 15-year fixed")) {
            intrestRateFromApi = Float.parseFloat(states.getFifteenYearFixed());
            intrestRateFromtable = Float.parseFloat(rateFactors.getFifteenYearFixedJumbo());
        } else if (loanterm.equals("3/1 ARM")) {
            intrestRateFromApi = Float.parseFloat(states.getFiveOneARM());
            intrestRateFromtable = Float.parseFloat(rateFactors.getThreeOneARM());
        } else if (loanterm.equals("5/1 ARM")) {
            intrestRateFromApi = Float.parseFloat(states.getFiveOneARM());
            intrestRateFromtable = Float.parseFloat(rateFactors.getFiveOneARM());
        } else if (loanterm.equals("jumbo 3/1 ARM")) {
            intrestRateFromApi = Float.parseFloat(states.getFiveOneARM());
            intrestRateFromtable = Float.parseFloat(rateFactors.getThreeOneARMJumbo());
        } else if (loanterm.equals("jumbo 5/1 ARM")) {
            intrestRateFromApi = Float.parseFloat(states.getFiveOneARM());

            intrestRateFromtable = Float.parseFloat(rateFactors.getFiveOneARMJumbo());
        } else {
            System.out.println();

        }
        interest = intrestRateFromApi * intrestRateFromtable;

        return interest;

    }

    @Override
    public JSONObject calHouseValue(final MC mc) {
        // TODO Auto-generated method stub
        final ObjectMapper jsonMapper = new ObjectMapper();
        final JSONObject resposneToRest = new JSONObject();
        try {
            resposneToRest.put("status", "fail");
        } catch (final JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        final String city = mc.getCity();
        final String state = mc.getStates();
        final States stateobj1 = MongoDBConnection.stateColl.findOne("{Name:#}", state).as(States.class);
        double houseValue = 0;
        final int flag = 0;
        // final String city = housegoalmodel.getCity();
        // System.out.println("city====="+city);
        // final String state = housegoalmodel.getLocation();
        final String county = mc.getCounty().replace("County", "").trim();
        System.out.println("county ::" + county);
        // System.out.println("state ::"+state);
        // System.out.println("city ::"+city);
        // States gethousevalue =
        // MongoDBConnection.stateColl.findOne("{Name:#,}",state).as(States.class);
        final JSONObject cities;
        try {
            final HouseMedianValue cityobj = MongoDBConnection.city_House_Median_ValueColl
                    .findOne("{city:#,state:#}", city,stateobj1.getCode()).as(HouseMedianValue.class);
            if (cityobj != null) {
                houseValue = cityobj.getValue();
            } else {
                final HouseMedianValue countybj = MongoDBConnection.county_House_Median_ValueColl
                        .findOne("{countyName:#,state:#}", county,stateobj1.getCode()).as(HouseMedianValue.class);
                if (countybj != null) {
                    houseValue = countybj.getValue();
                } else {

                    final HouseMedianValue stateobj = MongoDBConnection.state_House_Median_ValueColl
                            .findOne("{state:#,}", state).as(HouseMedianValue.class);
                    if (stateobj != null) {
                        houseValue = stateobj.getValue();
                    } else {
                        houseValue = 0.00;
                    }

                }
            }
            resposneToRest.put("status", "success");
            resposneToRest.put("House_Value", houseValue);
            System.out.println("resposneToRest :: " + resposneToRest);
            // resposneToRest.put("housevalue",
            // roundUpNumberByUsingMultipleValue(houseValue, 1000));
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
}
