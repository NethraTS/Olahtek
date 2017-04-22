package com.mongorest.olahtek.model;

public class InvestmentPortfolioModel {
	private String user_id;
	private String plan_name;
	private int riskScore;
	private int riskFactor;
    private String filingStatusPort;
	private double aggRiskAggressiveWeight;
	private double lqdRiskAggressiveWeight;
	
	private double bndxRiskAggressiveWeight;
	private double vtiRiskAggressiveWeight;
	private double voeRiskAggressiveWeight;
	private double veaRiskAggressiveWeight;
	private double aggRiskNeutralWeight;
	private double lqdRiskNeutralWeight;
	private double bndxRiskNeutralWeight;
	private double vtiRiskNeutralWeight;
	private double voeRiskNeutralWeight;
	private double veaRiskNeutralWeight;
	private double aggRiskAdverseWeight;
	private double lqdRiskAdverseWeight;
	private double bndxRiskAdverseWeight;
	private double vtiRiskAdverseWeight;
	private double voeRiskAdverseWeight;
	private double veaRiskAdverseWeight;
	private double inflationRate;
	private double aggBondYieldStart;
	private double aggBondYieldEnd;
	private double lqdBondYieldStart;
	private double lqdBondYieldEnd;
	private double bndxBondYieldStart;
	private double bndxBondYieldEnd;
	private double vtiGrowthRateStart;
	private double vtiGrowthRateEnd;
	private double vtiDividendYield;
	private double voeGrowthRateStart;
	private double voeGrowthRateEnd;
	private double voeDividendYield;
	private double veaGrowthRateStart;
	private double veaGrowthRateEnd;
	private double veaDividendYield;
	private double aggBondCoupon;
	private double lqdBondCoupon;
	private double bndxBondCoupon;
	private double vti_10_Yr_Historical_Total_Return;
	private double vtv_10_Yr_Historical_Total_Return;
	private double voe_10_Yr_Historical_Total_Return;
	private double vbr_10_Yr_Historical_Total_Return;
	private double vea_10_Yr_Historical_Total_Return;
	private double vwo_10_Yr_Historical_Total_Return;
	
	private double vtvDividendYield;
	private double vbrDividendYield;
	private double vwoDividendYield;
	 
	private double growthRate;
	private double portfolioDividend;
	private double portfolioInterest;
	public String getFilingStatusPort() {
		return filingStatusPort;
	}

	public void setFilingStatusPort(String filingStatusPort) {
		this.filingStatusPort = filingStatusPort;
	}

	private int age;
	
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getGrowthRate() {
		return growthRate;
	}

	public void setGrowthRate(double growthRate) {
		this.growthRate = growthRate;
	}

	public double getPortfolioDividend() {
		return portfolioDividend;
	}

	public void setPortfolioDividend(double portfolioDividend) {
		this.portfolioDividend = portfolioDividend;
	}

	public double getPortfolioInterest() {
		return portfolioInterest;
	}

	public void setPortfolioInterest(double portfolioInterest) {
		this.portfolioInterest = portfolioInterest;
	}

	public double getVtvDividendYield() {
		return vtvDividendYield;
	}

	public void setVtvDividendYield(double vtvDividendYield) {
		this.vtvDividendYield = vtvDividendYield;
	}

	public double getVbrDividendYield() {
		return vbrDividendYield;
	}

	public void setVbrDividendYield(double vbrDividendYield) {
		this.vbrDividendYield = vbrDividendYield;
	}

	public double getVwoDividendYield() {
		return vwoDividendYield;
	}

	public void setVwoDividendYield(double vwoDividendYield) {
		this.vwoDividendYield = vwoDividendYield;
	}

	public double getVti_10_Yr_Historical_Total_Return() {
		return vti_10_Yr_Historical_Total_Return;
	}

	public void setVti_10_Yr_Historical_Total_Return(double vti_10_Yr_Historical_Total_Return) {
		this.vti_10_Yr_Historical_Total_Return = vti_10_Yr_Historical_Total_Return;
	}

	public double getVtv_10_Yr_Historical_Total_Return() {
		return vtv_10_Yr_Historical_Total_Return;
	}

	public void setVtv_10_Yr_Historical_Total_Return(double vtv_10_Yr_Historical_Total_Return) {
		this.vtv_10_Yr_Historical_Total_Return = vtv_10_Yr_Historical_Total_Return;
	}

	public double getVoe_10_Yr_Historical_Total_Return() {
		return voe_10_Yr_Historical_Total_Return;
	}

	public void setVoe_10_Yr_Historical_Total_Return(double voe_10_Yr_Historical_Total_Return) {
		this.voe_10_Yr_Historical_Total_Return = voe_10_Yr_Historical_Total_Return;
	}

	public double getVbr_10_Yr_Historical_Total_Return() {
		return vbr_10_Yr_Historical_Total_Return;
	}

	public void setVbr_10_Yr_Historical_Total_Return(double vbr_10_Yr_Historical_Total_Return) {
		this.vbr_10_Yr_Historical_Total_Return = vbr_10_Yr_Historical_Total_Return;
	}

	public double getVea_10_Yr_Historical_Total_Return() {
		return vea_10_Yr_Historical_Total_Return;
	}

	public void setVea_10_Yr_Historical_Total_Return(double vea_10_Yr_Historical_Total_Return) {
		this.vea_10_Yr_Historical_Total_Return = vea_10_Yr_Historical_Total_Return;
	}

	public double getVwo_10_Yr_Historical_Total_Return() {
		return vwo_10_Yr_Historical_Total_Return;
	}

	public void setVwo_10_Yr_Historical_Total_Return(double vwo_10_Yr_Historical_Total_Return) {
		this.vwo_10_Yr_Historical_Total_Return = vwo_10_Yr_Historical_Total_Return;
	}

	private String formType;

	public int getRiskFactor() {
		return riskFactor;
	}

	public void setRiskFactor(int riskFactor) {
		this.riskFactor = riskFactor;
	}
	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	public double getAggRiskAggressiveWeight() {
		return aggRiskAggressiveWeight;
	}

	public void setAggRiskAggressiveWeight(double aggRiskAggressiveWeight) {
		this.aggRiskAggressiveWeight = aggRiskAggressiveWeight;
	}

	public double getLqdRiskAggressiveWeight() {
		return lqdRiskAggressiveWeight;
	}

	public void setLqdRiskAggressiveWeight(double lqdRiskAggressiveWeight) {
		this.lqdRiskAggressiveWeight = lqdRiskAggressiveWeight;
	}

	public double getBndxRiskAggressiveWeight() {
		return bndxRiskAggressiveWeight;
	}

	public void setBndxRiskAggressiveWeight(double bndxRiskAggressiveWeight) {
		this.bndxRiskAggressiveWeight = bndxRiskAggressiveWeight;
	}

	public double getVtiRiskAggressiveWeight() {
		return vtiRiskAggressiveWeight;
	}

	public void setVtiRiskAggressiveWeight(double vtiRiskAggressiveWeight) {
		this.vtiRiskAggressiveWeight = vtiRiskAggressiveWeight;
	}

	public double getVoeRiskAggressiveWeight() {
		return voeRiskAggressiveWeight;
	}

	public void setVoeRiskAggressiveWeight(double voeRiskAggressiveWeight) {
		this.voeRiskAggressiveWeight = voeRiskAggressiveWeight;
	}

	public double getVeaRiskAggressiveWeight() {
		return veaRiskAggressiveWeight;
	}

	public void setVeaRiskAggressiveWeight(double veaRiskAggressiveWeight) {
		this.veaRiskAggressiveWeight = veaRiskAggressiveWeight;
	}

	public double getAggRiskNeutralWeight() {
		return aggRiskNeutralWeight;
	}

	public void setAggRiskNeutralWeight(double aggRiskNeutralWeight) {
		this.aggRiskNeutralWeight = aggRiskNeutralWeight;
	}

	public double getLqdRiskNeutralWeight() {
		return lqdRiskNeutralWeight;
	}

	public void setLqdRiskNeutralWeight(double lqdRiskNeutralWeight) {
		this.lqdRiskNeutralWeight = lqdRiskNeutralWeight;
	}

	public double getBndxRiskNeutralWeight() {
		return bndxRiskNeutralWeight;
	}

	public void setBndxRiskNeutralWeight(double bndxRiskNeutralWeight) {
		this.bndxRiskNeutralWeight = bndxRiskNeutralWeight;
	}

	public double getVtiRiskNeutralWeight() {
		return vtiRiskNeutralWeight;
	}

	public void setVtiRiskNeutralWeight(double vtiRiskNeutralWeight) {
		this.vtiRiskNeutralWeight = vtiRiskNeutralWeight;
	}

	public double getVoeRiskNeutralWeight() {
		return voeRiskNeutralWeight;
	}

	public void setVoeRiskNeutralWeight(double voeRiskNeutralWeight) {
		this.voeRiskNeutralWeight = voeRiskNeutralWeight;
	}

	public double getVeaRiskNeutralWeight() {
		return veaRiskNeutralWeight;
	}

	public void setVeaRiskNeutralWeight(double veaRiskNeutralWeight) {
		this.veaRiskNeutralWeight = veaRiskNeutralWeight;
	}

	public double getAggRiskAdverseWeight() {
		return aggRiskAdverseWeight;
	}

	public void setAggRiskAdverseWeight(double aggRiskAdverseWeight) {
		this.aggRiskAdverseWeight = aggRiskAdverseWeight;
	}

	public double getLqdRiskAdverseWeight() {
		return lqdRiskAdverseWeight;
	}

	public void setLqdRiskAdverseWeight(double lqdRiskAdverseWeight) {
		this.lqdRiskAdverseWeight = lqdRiskAdverseWeight;
	}

	public double getBndxRiskAdverseWeight() {
		return bndxRiskAdverseWeight;
	}

	public void setBndxRiskAdverseWeight(double bndxRiskAdverseWeight) {
		this.bndxRiskAdverseWeight = bndxRiskAdverseWeight;
	}

	public double getVtiRiskAdverseWeight() {
		return vtiRiskAdverseWeight;
	}

	public void setVtiRiskAdverseWeight(double vtiRiskAdverseWeight) {
		this.vtiRiskAdverseWeight = vtiRiskAdverseWeight;
	}

	public double getVoeRiskAdverseWeight() {
		return voeRiskAdverseWeight;
	}

	public void setVoeRiskAdverseWeight(double voeRiskAdverseWeight) {
		this.voeRiskAdverseWeight = voeRiskAdverseWeight;
	}

	public double getVeaRiskAdverseWeight() {
		return veaRiskAdverseWeight;
	}

	public void setVeaRiskAdverseWeight(double veaRiskAdverseWeight) {
		this.veaRiskAdverseWeight = veaRiskAdverseWeight;
	}

	public double getInflationRate() {
		return inflationRate;
	}

	public void setInflationRate(double inflationRate) {
		this.inflationRate = inflationRate;
	}

	public double getAggBondYieldStart() {
		return aggBondYieldStart;
	}

	public void setAggBondYieldStart(double aggBondYieldStart) {
		this.aggBondYieldStart = aggBondYieldStart;
	}

	public double getAggBondYieldEnd() {
		return aggBondYieldEnd;
	}

	public void setAggBondYieldEnd(double aggBondYieldEnd) {
		this.aggBondYieldEnd = aggBondYieldEnd;
	}

	public double getLqdBondYieldStart() {
		return lqdBondYieldStart;
	}

	public void setLqdBondYieldStart(double lqdBondYieldStart) {
		this.lqdBondYieldStart = lqdBondYieldStart;
	}

	public double getLqdBondYieldEnd() {
		return lqdBondYieldEnd;
	}

	public void setLqdBondYieldEnd(double lqdBondYieldEnd) {
		this.lqdBondYieldEnd = lqdBondYieldEnd;
	}

	public double getBndxBondYieldStart() {
		return bndxBondYieldStart;
	}

	public void setBndxBondYieldStart(double bndxBondYieldStart) {
		this.bndxBondYieldStart = bndxBondYieldStart;
	}

	public double getBndxBondYieldEnd() {
		return bndxBondYieldEnd;
	}

	public void setBndxBondYieldEnd(double bndxBondYieldEnd) {
		this.bndxBondYieldEnd = bndxBondYieldEnd;
	}

	public double getVtiGrowthRateStart() {
		return vtiGrowthRateStart;
	}

	public void setVtiGrowthRateStart(double vtiGrowthRateStart) {
		this.vtiGrowthRateStart = vtiGrowthRateStart;
	}

	public double getVtiGrowthRateEnd() {
		return vtiGrowthRateEnd;
	}

	public void setVtiGrowthRateEnd(double vtiGrowthRateEnd) {
		this.vtiGrowthRateEnd = vtiGrowthRateEnd;
	}

	public double getVtiDividendYield() {
		return vtiDividendYield;
	}

	public void setVtiDividendYield(double vtiDividendYield) {
		this.vtiDividendYield = vtiDividendYield;
	}

	public double getVoeGrowthRateStart() {
		return voeGrowthRateStart;
	}

	public void setVoeGrowthRateStart(double voeGrowthRateStart) {
		this.voeGrowthRateStart = voeGrowthRateStart;
	}

	public double getVoeGrowthRateEnd() {
		return voeGrowthRateEnd;
	}

	public void setVoeGrowthRateEnd(double voeGrowthRateEnd) {
		this.voeGrowthRateEnd = voeGrowthRateEnd;
	}

	public double getVoeDividendYield() {
		return voeDividendYield;
	}

	public void setVoeDividendYield(double voeDividendYield) {
		this.voeDividendYield = voeDividendYield;
	}

	public double getVeaGrowthRateStart() {
		return veaGrowthRateStart;
	}

	public void setVeaGrowthRateStart(double veaGrowthRateStart) {
		this.veaGrowthRateStart = veaGrowthRateStart;
	}

	public double getVeaGrowthRateEnd() {
		return veaGrowthRateEnd;
	}

	public void setVeaGrowthRateEnd(double veaGrowthRateEnd) {
		this.veaGrowthRateEnd = veaGrowthRateEnd;
	}

	public double getVeaDividendYield() {
		return veaDividendYield;
	}

	public void setVeaDividendYield(double veaDividendYield) {
		this.veaDividendYield = veaDividendYield;
	}

	public double getAggBondCoupon() {
		return aggBondCoupon;
	}

	public void setAggBondCoupon(double aggBondCoupon) {
		this.aggBondCoupon = aggBondCoupon;
	}

	public double getLqdBondCoupon() {
		return lqdBondCoupon;
	}

	public void setLqdBondCoupon(double lqdBondCoupon) {
		this.lqdBondCoupon = lqdBondCoupon;
	}

	public double getBndxBondCoupon() {
		return bndxBondCoupon;
	}

	public void setBndxBondCoupon(double bndxBondCoupon) {
		this.bndxBondCoupon = bndxBondCoupon;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(int riskScore) {
		this.riskScore = riskScore;
	}

}
