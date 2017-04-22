package com.mongorest.olahtek.model;

import java.util.List;

public class CollegeeducationExpense {
	int endYear;
	int startYear;
	long collegeExpense;
	String goalname;
	private double totalCollegeCost;
	private List<CollegeAmount> collegeGoalAmountData;

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

	public long getCollegeExpense() {
		return collegeExpense;
	}

	public void setCollegeExpense(long collegeExpense) {
		this.collegeExpense = collegeExpense;
	}

	public double getTotalCollegeCost() {
		return totalCollegeCost;
	}

	public void setTotalCollegeCost(double totalCollegeCost) {
		this.totalCollegeCost = totalCollegeCost;
	}

	public List<CollegeAmount> getCollegeGoalAmountData() {
		return collegeGoalAmountData;
	}

	public void setCollegeGoalAmountData(List<CollegeAmount> collegeGoalAmountData) {
		this.collegeGoalAmountData = collegeGoalAmountData;
	}

}
