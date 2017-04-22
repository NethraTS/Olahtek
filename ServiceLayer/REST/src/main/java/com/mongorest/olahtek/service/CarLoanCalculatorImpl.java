package com.mongorest.olahtek.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongorest.olahtek.model.CarLoanCalculator;


@Service("clCalculator")
@Transactional
public class CarLoanCalculatorImpl implements CarLoanCalculatorService{


    @Override
    public JSONObject calculateCLC(CarLoanCalculator clc)
    {
        final long carprice=Long.parseLong(clc.getCarprice() );
        final int loanterm=Integer.parseInt(clc.getLoanterm());
        final int years=loanterm*12;
        final float interestrate=Float.parseFloat(clc.getInterestrate());
        final long downpayment=Long.parseLong(clc.getDownpayment() );
        final long tradeinvalue=Long.parseLong(clc.getTradeinvalue() );
        final long licensefee=Long.parseLong(clc.getLicensefee() );
        final float saletax=Float.parseFloat(clc.getSaletax());


        final JSONObject clcobject=new JSONObject();
        try {
            //-------------------Calculation-------------------------------------//
            float monthlypayment=0;
            final long netcapitalizedcost=carprice-downpayment-tradeinvalue;
            final float interestannual=interestrate;
            final float interest=interestrate/12;
            final long loan=(long) (carprice*(1+saletax/100)-downpayment-tradeinvalue+licensefee);
            /*float monthlypayment=(float) (((Math.pow((1+interest/100),years)*(interest/100))/(Math.pow((1+interest/100),years)-1))*loan);*/
            if(interest==0)
            {
                /*long loan=(long) (carprice*(1+(saletax/100))-(downpayment+tradeinvalue));*/
                monthlypayment=(float) (Math.pow((1+interest)/100,years)*(interest/100)/(Math.pow((1+interest)/100,years)-1)*loan);
            }
            else
            {
                monthlypayment=(float) (Math.pow(1+interest/100,years)*(interest/100)/(Math.pow(1+interest/100,years)-1)*loan);
            }


            //--------------Annual Amortize calculation-----------------------------//
            long ma=loan;
            /*long ma1=loan;
		float mtemp=monthlypayment;*/
            final long mp=(long) (monthlypayment*12);


            final ArrayList<Long> e_balance = new ArrayList<Long>();
            final ArrayList<Long> s_balance = new ArrayList<Long>();
            final ArrayList<Long> principal = new ArrayList<Long>();
            final ArrayList <Long> interest1 = new ArrayList<Long>();

            final JSONArray output=new JSONArray();
            /*for(int year1 = 1; year1<=years; year1++)*/
            for(int year = 1; year<=loanterm; year++) {

                final JSONObject b=new JSONObject();
                b.put("s_balance", ma);
                s_balance.add(ma);

                long endingbalance=(long) (ma * Math.pow(1+interestrate/100/12, 12) - (Math.pow(1+interestrate/100/12, 12) - 1) * (monthlypayment/(interestrate/100/12)));

                endingbalance =endingbalance;

                if(endingbalance<0) {
                    endingbalance = 0;
                }
                e_balance.add( endingbalance);
                final long principal_temp = ma-endingbalance;
                principal.add(principal_temp);
                final long interest_temp = mp-principal_temp;
                interest1.add(interest_temp);
                ma = endingbalance;

                /*//System.out.println("CARma>>B>>"+ma);*/

                b.put("year", year);
                b.put("interest1", interest_temp);
                b.put("principal", principal_temp);
                b.put("e_balance", endingbalance);


                output.put(b);

                //-----------------------------------------------------------------------//

                clcobject.put("netcapitalizedcost", netcapitalizedcost);
                clcobject.put("interest",interestannual);
                clcobject.put("loan", loan);
                clcobject.put("monthlypayment", monthlypayment);
                clcobject.put("Annual", output);

                clcobject.put("status", "success");

            }

            //------------------------------------Monthly------------------------------------------------------------//

            /*float interestnew=interestrate;
		float monthlypayment1=(float) (((Math.pow((1+interestnew/100),years)*(interestnew/100))/(Math.pow((1+interestnew/100),years)-1))*loan);*/
            float ma1=loan;
            final float mp1=monthlypayment;

            final ArrayList<Long> e_balance1 = new ArrayList<Long>();
            final ArrayList<Long> s_balance1 = new ArrayList<Long>();
            final ArrayList<Long> principal1 = new ArrayList<Long>();
            final ArrayList <Long> interest2 = new ArrayList<Long>();

            final JSONArray output1=new JSONArray();
            /*for(int year1 = 1; year1<=years; year1++)*/
            for(int year1 = 1; year1<=loanterm*12; year1++) {

                final JSONObject b1=new JSONObject();
                b1.put("s_balance1", ma1);
                s_balance1.add((long) ma1);

                float endingbalance1=(float) (ma1 * Math.pow(1+interestrate/100/12, 1) - (Math.pow(1+interestrate/100/12, 1) - 1) * (monthlypayment/(interestrate/100/12)));

                endingbalance1 =endingbalance1;

                if(endingbalance1<0) {
                    endingbalance1 = 0;
                }
                e_balance1.add( (long) endingbalance1);
                final float principal_temp1 = ma1-endingbalance1;
                principal1.add((long) principal_temp1);
                final float interest_temp1 = mp1-principal_temp1;
                interest2.add((long) interest_temp1);
                ma1 = endingbalance1;

                /*//System.out.println("CARma>>B>>"+ma);*/

                b1.put("year1", year1);
                b1.put("interest2", interest_temp1);
                b1.put("principal1", principal_temp1);
                b1.put("e_balance1", endingbalance1);


                output1.put(b1);

                //-----------------------------------------------------------------------//


                clcobject.put("Annual1", output1);

                clcobject.put("status", "success");

            }
            //-----------------------------------------------------------------------------------------------------//
            /*JSONArray output1=new JSONArray();
		JSONArray  interest2=new JSONArray();
		JSONArray  e_balance1=new JSONArray();

		for(int year = 1; year<=loanterm*12; year++) {

			JSONObject b=new JSONObject();
			b.put("s_balance", ma1);
			s_balance.add(ma1);

			long endingbalance=(long) (ma1 * Math.pow((1+((interestrate/100)/12)), 12*loanterm) - (Math.pow((1+((interestrate/100)/12)), 12*loanterm) - 1) * (monthlypayment/((interestrate/100)/12)));

			endingbalance =(endingbalance);

			if(endingbalance<0)
				endingbalance = 0;
			e_balance1.put( endingbalance);
			long principal_temp = ma1-endingbalance;
			principal.add(principal_temp);
			float interest_temp = mtemp-principal_temp;
			interest2.put(interest_temp);
			monthlypayment = (long) endingbalance;

			//System.out.println("CARma>>B>>"+ma1);

			b.put("Month", year);
			b.put("interest1", interest2);
			b.put("principal", principal_temp);
			b.put("e_balance", endingbalance);


			output1.put(b);

			//-----------------------------------------------------------------------//


			clcobject.put("monthly", output1);

			clcobject.put("status", "success");*/


            /*}*/

            //System.out.println("CARma>>B>>"+s_balance);
            //System.out.println("CARma>>B>>"+e_balance);
            //System.out.println("CARma>>B>>"+principal);
            //System.out.println("CARma>>B>>"+interest1);
            //System.out.println("finalMonthly"+output);

            //System.out.println("finalMonthly"+output1);

        }
        catch (final Exception e) {

            e.printStackTrace();
        }
        return  clcobject;
    }

}

