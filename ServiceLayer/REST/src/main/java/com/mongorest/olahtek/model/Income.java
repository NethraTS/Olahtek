package com.mongorest.olahtek.model;

public class Income {
	int year;
	double value;
	double retirement_income;
	double incomeBeforeRetirement;
	

	public double getIncomeBeforeRetirement() {
		return incomeBeforeRetirement;
	}

	public void setIncomeBeforeRetirement(long incomeBeforeRetirement) {
		this.incomeBeforeRetirement = incomeBeforeRetirement;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public double getRetirement_income() {
		return retirement_income;
	}

	public void setRetirement_income(long retirement_income) {
		this.retirement_income = retirement_income;
	}

}
