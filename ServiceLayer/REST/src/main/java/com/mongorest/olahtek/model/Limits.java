package com.mongorest.olahtek.model;

public class Limits {

	private int year;
	private double saving;
	private double taxable;
	private double user401k;
	private double userIRA;
	private double userRouthIRA;
	private double user529Plan;
	private double spouse401k;
	private double spouseIRA;
	private double spouseRouthIRA;
	private double spouse529Plan;
	private double collegeGoalTaxable;
	public double getUser401k() {
		return user401k;
	}
	public void setUser401k(double user401k) {
		this.user401k = user401k;
	}
	public double getUserIRA() {
		return userIRA;
	}
	public void setUserIRA(double userIRA) {
		this.userIRA = userIRA;
	}
	public double getUserRouthIRA() {
		return userRouthIRA;
	}
	public void setUserRouthIRA(double userRouthIRA) {
		this.userRouthIRA = userRouthIRA;
	}
	public double getUser529Plan() {
		return user529Plan;
	}
	public void setUser529Plan(double user529Plan) {
		this.user529Plan = user529Plan;
	}
	public double getSpouse401k() {
		return spouse401k;
	}
	public void setSpouse401k(double spouse401k) {
		this.spouse401k = spouse401k;
	}
	public double getSpouseIRA() {
		return spouseIRA;
	}
	public void setSpouseIRA(double spouseIRA) {
		this.spouseIRA = spouseIRA;
	}
	public double getSpouseRouthIRA() {
		return spouseRouthIRA;
	}
	public void setSpouseRouthIRA(double spouseRouthIRA) {
		this.spouseRouthIRA = spouseRouthIRA;
	}
	public double getSpouse529Plan() {
		return spouse529Plan;
	}
	public void setSpouse529Plan(double spouse529Plan) {
		this.spouse529Plan = spouse529Plan;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getSaving() {
		return saving;
	}
	public void setSaving(double saving) {
		this.saving = saving;
	}
	public double getTaxable() {
		return taxable;
	}
	public void setTaxable(double taxable) {
		this.taxable = taxable;
	}
	public double getCollegeGoalTaxable() {
		return collegeGoalTaxable;
	}
	public void setCollegeGoalTaxable(double collegeGoalTaxable) {
		this.collegeGoalTaxable = collegeGoalTaxable;
	}
	
}