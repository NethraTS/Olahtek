package com.mongorest.olahtek.model;

import java.util.List;

public class QualifiedWidow {
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
	public void setMaxSalarySubjecttoSocialSecurityTax(
			long maxSalarySubjecttoSocialSecurityTax) {
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
	public void setThresholdforadditionalMedicareTax(
			long thresholdforadditionalMedicareTax) {
		this.thresholdforadditionalMedicareTax = thresholdforadditionalMedicareTax;
	}
	public List<TaxRates> getTaxRates() {
		return taxRates;
	}
	public void setTaxRates(List<TaxRates> taxRates) {
		this.taxRates = taxRates;
	}  

}
