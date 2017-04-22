package com.mongorest.olahtek.model;

public class CustomizedExpense {
	String goalname;
	int endYear;
	int startYear;
	double goalCost;
	String goalType;

	public String getGoalType() {
		return goalType;
	}

	public void setGoalType(String goalType) {
		this.goalType = goalType;
	}

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

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public double getGoalCost() {
		return goalCost;
	}

	public void setGoalCost(double goalCost) {
		this.goalCost = goalCost;
	}

}
