package com.mongorest.olahtek.model;

public class Deductions {
	private int year;
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
	public double getNontaxable() {
		return nontaxable;
	}
	public void setNontaxable(double nontaxable) {
		this.nontaxable = nontaxable;
	}

	public double getCollegeGoalTaxable() {
		return collegeGoalTaxable;
	}
	public void setCollegeGoalTaxable(double collegeGoalTaxable) {
		this.collegeGoalTaxable = collegeGoalTaxable;
	}

	public double getCollegeGoalNontaxable() {
		return collegeGoalNontaxable;
	}
	public void setCollegeGoalNontaxable(double collegeGoalNontaxable) {
		this.collegeGoalNontaxable = collegeGoalNontaxable;
	}

	private double saving;
	private double taxable;
	private double nontaxable;
	private double collegeGoalTaxable;
	private double collegeGoalNontaxable;
}
