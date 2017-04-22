package com.mongorest.olahtek.model;

public class KidExpense {
	int endYear;
	double annualExpense;
	int startYear;
	String goalname;

	public String getGoalname() {
		return goalname;
	}

	public void setGoalname(String goalname) {
		this.goalname = goalname;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public double getAnnualExpense() {
		return annualExpense;
	}

	public void setAnnualExpense(double annualExpense) {
		this.annualExpense = annualExpense;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

}
