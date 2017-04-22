package com.mongorest.olahtek.service;


import org.json.JSONObject;


public interface InvestmentPortfolio 
{
	
	
	JSONObject calInvestmentPortfoilo(String user_id,String plan_name,int RiskScore);
	JSONObject calInvestmentPortfolioIncomeProfile(String user_id,String profileName,Double growthRate,Double portfolioDividend,Double portfolioInterest,String filingStatusPort);
	void duringRegistration(String user_id,int riskScore);
}
