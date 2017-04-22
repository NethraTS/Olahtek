package com.mongorest.olahtek.model;

import java.util.List;
import java.util.Map;

public class Singlestatus {
	//==========================
	int standardDeductionamountisReduced;
	
	public int getStandardDeductionamountisReduced() {
		return standardDeductionamountisReduced;
	}

	public void setStandardDeductionamountisReduced(
			int standardDeductionamountisReduced) {
		this.standardDeductionamountisReduced = standardDeductionamountisReduced;
	}

	int phaseOutIncome;
    int standardDeduction;
    int additionalDeduction;
    int exemptionAmount;
    int exemptionThreshold;
    int exemptionStep;
    double fICASocialSecurityTaxRate;
    long maxSalarySubjecttoSocialSecurityTax;
    double fICAMedicareTax;
    long thresholdforadditionalMedicareTax;
    double phase_out_limit;
public double getPhase_out_limit() {
		return phase_out_limit;
	}

	public void setPhase_out_limit(double phase_out_limit) {
		this.phase_out_limit = phase_out_limit;
	}

List <Double>creditLimit;


	
	public List<Double> getCreditLimit() {
	return creditLimit;
}

public void setCreditLimit(List<Double> creditLimit) {
	this.creditLimit = creditLimit;
}
    private Map<String, ?> calculateTaxableSSBData;
    Limitsconstant limits;


	public Limitsconstant getLimits() {
		return limits;
	}

	public void setLimits(Limitsconstant limits) {
		this.limits = limits;
	}

	int exemptionCreditPhaseOut;
    int  exemptionCreditForEachAgi;


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

	List <TaxRates>taxRates;
    public int getPhaseOutIncome() {
		return phaseOutIncome;
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

	//==========================
	
	
	double standarddeduction;
	double personalExcemption;
	List<Integer> singles;
	List <Integer> standerdDeductionLimit;
      double standerdDeductionRate;
 List<Integer> personalExcemptionlimit;

int dependentExcemption;
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

public List<Integer> getPersonalExcemptionlimit() {
	return personalExcemptionlimit;
}

public void setPersonalExcemptionlimit(List<Integer> personalExcemptionlimit) {
	this.personalExcemptionlimit = personalExcemptionlimit;
}

	List personalExcemptionsValues;
	 List dependentExcemptionValues;
	 List standarddeductionRange;
	 int standerdDeductionAmt;
	 List <Integer> stateAgiLimit;
	 int standardDeductionPhaseOutLimit;
	    int standardDeductionPhaseOutLimitMax;
	    double standardDeductionPhaseOutRates ;
	    int aditionalDeductionMinIncome;
		List incomeLimit;
		 	/*List singles;*/
		double aditionalDeductionMinIncomeRates;
	    List incrementAGI;
    List<Integer> exemptionMax;
	List<Double> personalTaxFactorCTRates;
	   
	   List<Double> personalTaxFactorCTRatesMultipleRate;

	    
	   int CalculatedTaxAGI;
	   int CalculatedTaxAGI_Min;
	   


		public List<Integer> getStateAgiLimit() {
		return stateAgiLimit;
	}

	public void setStateAgiLimit(List<Integer> stateAgiLimit) {
		this.stateAgiLimit = stateAgiLimit;
	}

		public int getStanderdDeductionAmt() {
		return standerdDeductionAmt;
	}

	public void setStanderdDeductionAmt(int standerdDeductionAmt) {
		this.standerdDeductionAmt = standerdDeductionAmt;
	}

		public List getStandarddeductionRange() {
		return standarddeductionRange;
	}

	public void setStandarddeductionRange(List standarddeductionRange) {
		this.standarddeductionRange = standarddeductionRange;
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

		public int getDependentExcemption() {
		return dependentExcemption;
	}

	public void setDependentExcemption(int dependentExcemption) {
		this.dependentExcemption = dependentExcemption;
	}

		
        public double getAditionalDeductionMinIncomeRates() {
			return aditionalDeductionMinIncomeRates;
		}

		public void setAditionalDeductionMinIncomeRates(
				double aditionalDeductionMinIncomeRates) {
			this.aditionalDeductionMinIncomeRates = aditionalDeductionMinIncomeRates;
		}

		
    
	   
 public List getIncrementAGI() {
		return incrementAGI;
	}

	public void setIncrementAGI(List incrementAGI) {
		this.incrementAGI = incrementAGI;
	}

	public List<Integer> getExemptionMax() {
		return exemptionMax;
	}

	public void setExemptionMax(List<Integer> exemptionMax) {
		this.exemptionMax = exemptionMax;
	}

	public List<Double> getPersonalTaxFactorCTRates() {
		return personalTaxFactorCTRates;
	}

	public void setPersonalTaxFactorCTRates(List<Double> personalTaxFactorCTRates) {
		this.personalTaxFactorCTRates = personalTaxFactorCTRates;
	}

	public List<Double> getPersonalTaxFactorCTRatesMultipleRate() {
		return personalTaxFactorCTRatesMultipleRate;
	}

	public void setPersonalTaxFactorCTRatesMultipleRate(
			List<Double> personalTaxFactorCTRatesMultipleRate) {
		this.personalTaxFactorCTRatesMultipleRate = personalTaxFactorCTRatesMultipleRate;
	}

	public List<Integer> getPersonalTaxFactorCT() {
		return personalTaxFactorCT;
	}

	public void setPersonalTaxFactorCT(List<Integer> personalTaxFactorCT) {
		this.personalTaxFactorCT = personalTaxFactorCT;
	}

List<Integer> personalTaxFactorCT;
	 
	


	   
	    public int getCalculatedTaxAGI_Min() {
		return CalculatedTaxAGI_Min;
	}

	public void setCalculatedTaxAGI_Min(int calculatedTaxAGI_Min) {
		CalculatedTaxAGI_Min = calculatedTaxAGI_Min;
	}

		public int getCalculatedTaxAGI() {
		return CalculatedTaxAGI;
	}

	public void setCalculatedTaxAGI(int calculatedTaxAGI) {
		CalculatedTaxAGI = calculatedTaxAGI;
	}

	

		

		public int getAditionalDeductionMinIncome() {
			return aditionalDeductionMinIncome;
		}

		public void setAditionalDeductionMinIncome(int aditionalDeductionMinIncome) {
			this.aditionalDeductionMinIncome = aditionalDeductionMinIncome;
		}



	
	    
	 
	public List getIncomeLimit() {
			return incomeLimit;
		}

		public List<Integer> getSingles() {
		return singles;
	}

	public void setSingles(List<Integer> singles) {
		this.singles = singles;
	}

		public void setIncomeLimit(List incomeLimit) {
			this.incomeLimit = incomeLimit;
		}

	public int getStandardDeductionPhaseOutLimit() {
			return standardDeductionPhaseOutLimit;
		}

		public void setStandardDeductionPhaseOutLimit(int standardDeductionPhaseOutLimit) {
			this.standardDeductionPhaseOutLimit = standardDeductionPhaseOutLimit;
		}

		public int getStandardDeductionPhaseOutLimitMax() {
			return standardDeductionPhaseOutLimitMax;
		}

		public void setStandardDeductionPhaseOutLimitMax(
				int standardDeductionPhaseOutLimitMax) {
			this.standardDeductionPhaseOutLimitMax = standardDeductionPhaseOutLimitMax;
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

	public Map<String, ?> getCalculateTaxableSSBData() {
		return calculateTaxableSSBData;
	}

	public void setCalculateTaxableSSBData(Map<String, ?> calculateTaxableSSBData) {
		this.calculateTaxableSSBData = calculateTaxableSSBData;
	}

}
