package com.mongorest.olahtek.model;

import java.util.List;

public class HeadOfHousehold {
	
	//=======================================
    int phaseOutIncome;
    int standardDeduction;
    int additionalDeduction;
    int exemptionAmount;
    int exemptionThreshold;
    int exemptionStep;
    double fICASocialSecurityTaxRate;
    long maxSalarySubjecttoSocialSecurityTax;
    double fICAMedicareTax;
    double standerdDeductionRate;
    double phase_out_limit;
public double getPhase_out_limit() {
		return phase_out_limit;
	}

	public void setPhase_out_limit(double phase_out_limit) {
		this.phase_out_limit = phase_out_limit;
	}
int standardDeductionamountisReduced;
List <Double>creditLimit;


	
	public List<Double> getCreditLimit() {
	return creditLimit;
}

public void setCreditLimit(List<Double> creditLimit) {
	this.creditLimit = creditLimit;
}

	public int getStandardDeductionamountisReduced() {
		return standardDeductionamountisReduced;
	}

	public void setStandardDeductionamountisReduced(
			int standardDeductionamountisReduced) {
		this.standardDeductionamountisReduced = standardDeductionamountisReduced;
	}
    List <Integer> standerdDeductionLimit;
   int exemptionCreditPhaseOut;
   
  List<Double> standarddeductionRange;
  
	public List<Double> getStandarddeductionRange() {
	return standarddeductionRange;
}

public void setStandarddeductionRange(List<Double> standarddeductionRange) {
	this.standarddeductionRange = standarddeductionRange;
}

	public int getExemptionCreditPhaseOut() {
	return exemptionCreditPhaseOut;
}

public void setExemptionCreditPhaseOut(int exemptionCreditPhaseOut) {
	this.exemptionCreditPhaseOut = exemptionCreditPhaseOut;
}

public int getExemptionCreditForEachAgi() {
	return exemptionCreditForEachAgi;
}

public void setExemptionCreditForEachAgi(int exemptionCreditForEachAgi) {
	this.exemptionCreditForEachAgi = exemptionCreditForEachAgi;
}

	int exemptionCreditForEachAgi;
    public List<Integer> getStanderdDeductionLimit() {
		return standerdDeductionLimit;
	}

	public void setStanderdDeductionLimit(List<Integer> standerdDeductionLimit) {
		this.standerdDeductionLimit = standerdDeductionLimit;
	}

	public double getStanderdDeductionRate() {
		return standerdDeductionRate;
	}

	public void setStanderdDeductionRate(double standerdDeductionRate) {
		this.standerdDeductionRate = standerdDeductionRate;
	}

	long thresholdforadditionalMedicareTax;
    List <TaxRates>taxRates;
    public int getPhaseOutIncome() {
		return phaseOutIncome;
	}
    
    List <Integer> personalExcemptionlimit;
    

	public List<Integer> getPersonalExcemptionlimit() {
		return personalExcemptionlimit;
	}

	public void setPersonalExcemptionlimit(List<Integer> personalExcemptionlimit) {
		this.personalExcemptionlimit = personalExcemptionlimit;
	}

	public void setPhaseOutIncome(int phaseOutIncome) {
		this.phaseOutIncome = phaseOutIncome;
	}

	public int getStandardDeduction() {
		return standardDeduction;
	}

	public void setStandardDeduction(int standardDeduction) {
		this.standardDeduction = standardDeduction;
	}

	public int getAdditionalDeduction() {
		return additionalDeduction;
	}

	public void setAdditionalDeduction(int additionalDeduction) {
		this.additionalDeduction = additionalDeduction;
	}

	public int getExemptionAmount() {
		return exemptionAmount;
	}

	public void setExemptionAmount(int exemptionAmount) {
		this.exemptionAmount = exemptionAmount;
	}

	public int getExemptionThreshold() {
		return exemptionThreshold;
	}

	public void setExemptionThreshold(int exemptionThreshold) {
		this.exemptionThreshold = exemptionThreshold;
	}

	public int getExemptionStep() {
		return exemptionStep;
	}

	public void setExemptionStep(int exemptionStep) {
		this.exemptionStep = exemptionStep;
	}

	public double getfICASocialSecurityTaxRate() {
		return fICASocialSecurityTaxRate;
	}

	public void setfICASocialSecurityTaxRate(double fICASocialSecurityTaxRate) {
		this.fICASocialSecurityTaxRate = fICASocialSecurityTaxRate;
	}

	public long getMaxSalarySubjecttoSocialSecurityTax() {
		return maxSalarySubjecttoSocialSecurityTax;
	}

	public void setMaxSalarySubjecttoSocialSecurityTax(long maxSalarySubjecttoSocialSecurityTax) {
		this.maxSalarySubjecttoSocialSecurityTax = maxSalarySubjecttoSocialSecurityTax;
	}

	public double getfICAMedicareTax() {
		return fICAMedicareTax;
	}

	public void setfICAMedicareTax(double fICAMedicareTax) {
		this.fICAMedicareTax = fICAMedicareTax;
	}

	public long getThresholdforadditionalMedicareTax() {
		return thresholdforadditionalMedicareTax;
	}

	public void setThresholdforadditionalMedicareTax(long thresholdforadditionalMedicareTax) {
		this.thresholdforadditionalMedicareTax = thresholdforadditionalMedicareTax;
	}

	public List<TaxRates> getTaxRates() {
		return taxRates;
	}

	public void setTaxRates(List<TaxRates> taxRates) {
		this.taxRates = taxRates;
	}

	
	//=======================================
	
	
	double standarddeduction;
	double personalExcemption;
	 List  stateAgiLimit;
	 
	
    int standardDeductionPhaseOutLimit;
     double standardDeductionPhaseOutRates;
     List incomeLimit;
     List hoh;
     public List getHoh() {
		return hoh;
	}

	public void setHoh(List hoh) {
		this.hoh = hoh;
	}

	List incrementAGI;
     double incrementDeduction;
     List personalTaxFactorCT;
      List personalTaxFactorCTRates;
      List personalTaxFactorCTRatesMultipleRate;
      List personalExcemptionsValues;
 	 List dependentExcemptionValues;
 	 int standerdDeductionAmt;
 	 
 	 int dependentExcemption;
 	
     public int getDependentExcemption() {
 		return dependentExcemption;
 	}

 	public void setDependentExcemption(int dependentExcemption) {
 		this.dependentExcemption = dependentExcemption;
 	}
     public int getStanderdDeductionAmt() {
		return standerdDeductionAmt;
	}

	public void setStanderdDeductionAmt(int standerdDeductionAmt) {
		this.standerdDeductionAmt = standerdDeductionAmt;
	}

	public List getPersonalExcemptionsValues() {
		return personalExcemptionsValues;
	}

	public void setPersonalExcemptionsValues(List personalExcemptionsValues) {
		this.personalExcemptionsValues = personalExcemptionsValues;
	}

	public List getDependentExcemptionValues() {
		return dependentExcemptionValues;
	}

	public void setDependentExcemptionValues(List dependentExcemptionValues) {
		this.dependentExcemptionValues = dependentExcemptionValues;
	}

	public List getPersonalTaxFactorCT() {
		return personalTaxFactorCT;
	}

	public void setPersonalTaxFactorCT(List personalTaxFactorCT) {
		this.personalTaxFactorCT = personalTaxFactorCT;
	}

	public List getPersonalTaxFactorCTRates() {
		return personalTaxFactorCTRates;
	}

	public void setPersonalTaxFactorCTRates(List personalTaxFactorCTRates) {
		this.personalTaxFactorCTRates = personalTaxFactorCTRates;
	}

	public List getPersonalTaxFactorCTRatesMultipleRate() {
		return personalTaxFactorCTRatesMultipleRate;
	}

	public void setPersonalTaxFactorCTRatesMultipleRate(
			List personalTaxFactorCTRatesMultipleRate) {
		this.personalTaxFactorCTRatesMultipleRate = personalTaxFactorCTRatesMultipleRate;
	}

	int CalculatedTaxAGI;
     int CalculatedTaxAGI_Min;
     
     
     public int getCalculatedTaxAGI() {
		return CalculatedTaxAGI;
	}

	public void setCalculatedTaxAGI(int calculatedTaxAGI) {
		CalculatedTaxAGI = calculatedTaxAGI;
	}

	public int getCalculatedTaxAGI_Min() {
		return CalculatedTaxAGI_Min;
	}

	public void setCalculatedTaxAGI_Min(int calculatedTaxAGI_Min) {
		CalculatedTaxAGI_Min = calculatedTaxAGI_Min;
	}

	public List getIncrementAGI() {
		return incrementAGI;
	}

	public void setIncrementAGI(List incrementAGI) {
		this.incrementAGI = incrementAGI;
	}

	
	
     
     
   public double getIncrementDeduction() {
		return incrementDeduction;
	}

	public void setIncrementDeduction(double incrementDeduction) {
		this.incrementDeduction = incrementDeduction;
	}


public List getIncomeLimit() {
		return incomeLimit;
	}

	public void setIncomeLimit(List incomeLimit) {
		this.incomeLimit = incomeLimit;
	}

int aditionalDeductionMinIncome;
    double aditionalDeductionMinIncomeRates;
    

	public int getAditionalDeductionMinIncome() {
		return aditionalDeductionMinIncome;
	}

	public void setAditionalDeductionMinIncome(int aditionalDeductionMinIncome) {
		this.aditionalDeductionMinIncome = aditionalDeductionMinIncome;
	}

	public double getAditionalDeductionMinIncomeRates() {
		return aditionalDeductionMinIncomeRates;
	}

	public void setAditionalDeductionMinIncomeRates(
			double aditionalDeductionMinIncomeRates) {
		this.aditionalDeductionMinIncomeRates = aditionalDeductionMinIncomeRates;
	}

	public int getStandardDeductionPhaseOutLimit() {
		return standardDeductionPhaseOutLimit;
	}

	public void setStandardDeductionPhaseOutLimit(int standardDeductionPhaseOutLimit) {
		this.standardDeductionPhaseOutLimit = standardDeductionPhaseOutLimit;
	}

	public double getStandardDeductionPhaseOutRates() {
		return standardDeductionPhaseOutRates;
	}

	public void setStandardDeductionPhaseOutRates(
			double standardDeductionPhaseOutRates) {
		this.standardDeductionPhaseOutRates = standardDeductionPhaseOutRates;
	}

	public double getPersonalExcemption() {
		return personalExcemption;
	}

	public List getStateAgiLimit() {
		return stateAgiLimit;
	}

	public void setStateAgiLimit(List stateAgiLimit) {
		this.stateAgiLimit = stateAgiLimit;
	}

	public void setPersonalExcemption(double personalExcemption) {
		this.personalExcemption = personalExcemption;
	}

	List<RatesBrakets> ratesAndBrackets;

	public double getStandarddeduction() {
		return standarddeduction;
	}

	public void setStandarddeduction(double standarddeduction) {
		this.standarddeduction = standarddeduction;
	}

	public List<RatesBrakets> getRatesAndBrackets() {
		return ratesAndBrackets;
	}

	public void setRatesAndBrackets(List<RatesBrakets> ratesAndBrackets) {
		this.ratesAndBrackets = ratesAndBrackets;
	}

}
