package com.mongorest.olahtek.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongorest.olahtek.model.CarLeaseCalculator;



@Service("cleCalculator")
@Transactional
public class CarLeaseCalculatorImpl implements CarLeaseCalculatorService{
	
	
	public JSONObject calculateCLEC(CarLeaseCalculator clec)
	{ 
		long carprice=Long.parseLong(clec.getCarprice() );
		int loanterm=Integer.parseInt(clec.getLoanterm());
		int years=loanterm*12;
		float interestratenew=Float.parseFloat(clec.getInterestrate());
		long downpayment=Long.parseLong(clec.getDownpayment());
		long tradeinvalue=Long.parseLong(clec.getTradeinvalue());
        float licensefee=Float.parseFloat(clec.getLicensefee());
        long acquisitioncost=Long.parseLong(clec.getAcquisitioncost());
        long residualvalue=Long.parseLong(clec.getResidualvalue());
        float saletax=Float.parseFloat(clec.getSaletax());
          //  System.out.println("saletax>>>"+saletax);
        
        JSONObject clecobject=new JSONObject();
		try {
			//-------------------Calculation-------------------------------------//
	
			//float monthlyinterestrate1=(float) 0.000025;
			//float interestrate=(float) (0.00125*2400)/100;
			float interestrate=interestratenew;
			//float monthlyinterestrate= (interestrate);
			/*float monthlyinterestrate= (interestrate/100);*/
			float monthlyinterestrate2=interestratenew/12;
			float monthlyinterestrate1=interestratenew/100; //monthlyinterestrate/12;
			//System.out.println("monthlyinterestrate1"+monthlyinterestrate1);
			float loan=carprice+licensefee-downpayment-tradeinvalue-(residualvalue/100);
			//System.out.println("loan"+loan);
			//float loan=(float) (carprice-((residualvalue/100)/Math.pow((1+monthlyinterestrate1),years))+licensefee+acquisitioncost);
			float monthlypayment=(float) (loan/((Math.pow((1+monthlyinterestrate1),years))*(Math.pow((1+monthlyinterestrate1),years)-1)/(monthlyinterestrate1)));
			float monthlypayment1= monthlypayment*(1+saletax);
			//System.out.println("monthlypayment1"+monthlypayment1);
			
			clecobject.put("interestrate", interestrate);
			clecobject.put("monthlyinterestrate2", monthlyinterestrate2);
			clecobject.put("loan",loan);
			clecobject.put("monthlypayment1",monthlypayment1);
			clecobject.put("status", "success");
		
		}
catch (Exception e) {
			
			e.printStackTrace();
		}
		return  clecobject;
	}

}