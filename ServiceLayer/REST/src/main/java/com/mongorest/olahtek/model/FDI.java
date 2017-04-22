package com.mongorest.olahtek.model;

public class FDI {
	public double Gross_Annual_Income;
	public String state;
    public String Filling_Status;
    public int  Personal_Exemption;
    public String action;
	public String   user_id;

	public double federaltax;
	public double fica;
	public double statetax;
	public double  Medicare;
	public String credits;
	public double totalIncometax;
	public double IncomeAfterTaxes;
	public  double  Total_estimated_tax;
	
	
	
	
	public double getGross_Annual_Income() {
		return Gross_Annual_Income;
	}
	public void setGross_Annual_Income(double gross_Annual_Income) {
		Gross_Annual_Income = gross_Annual_Income;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFilling_Status() {
		return Filling_Status;
	}
	public void setFilling_Status(String filling_Status) {
		Filling_Status = filling_Status;
	}
	public int getPersonal_Exemption() {
		return Personal_Exemption;
	}
	public void setPersonal_Exemption(int personal_Exemption) {
		Personal_Exemption = personal_Exemption;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public double getFederaltax() {
		return federaltax;
	}
	public void setFederaltax(double federaltax) {
		this.federaltax = federaltax;
	}
	public double getFica() {
		return fica;
	}
	public void setFica(double fica) {
		this.fica = fica;
	}
	public double getStatetax() {
		return statetax;
	}
	public void setStatetax(double statetax) {
		this.statetax = statetax;
	}
	public double getMedicare() {
		return Medicare;
	}
	public void setMedicare(double medicare) {
		Medicare = medicare;
	}
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	public double getTotalIncometax() {
		return totalIncometax;
	}
	public void setTotalIncometax(double totalIncometax) {
		this.totalIncometax = totalIncometax;
	}
	public double getIncomeAfterTaxes() {
		return IncomeAfterTaxes;
	}
	public void setIncomeAfterTaxes(double incomeAfterTaxes) {
		IncomeAfterTaxes = incomeAfterTaxes;
	}
	public double getTotal_estimated_tax() {
		return Total_estimated_tax;
	}
	public void setTotal_estimated_tax(double total_estimated_tax) {
		Total_estimated_tax = total_estimated_tax;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}