package com.mongorest.olahtek.model;

public class CarExpense {
    int endYear;
    long annualCost;
    int startYear;
    double downPayment;
    private String goalName;
    private String carReccursive;
    private int reccursivePeriod;

    public double getDownPayment() {
        return downPayment;
    }
    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }
    public int getEndYear() {
        return endYear;
    }
    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public long getAnnualCost() {
        return annualCost;
    }
    public void setAnnualCost(long annualCost) {
        this.annualCost = annualCost;
    }
    public int getStartYear() {
        return startYear;
    }
    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }
    public String getGoalName() {
        return goalName;
    }
    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }
    public String getCarReccursive() {
        return carReccursive;
    }
    public void setCarReccursive(String carReccursive) {
        this.carReccursive = carReccursive;
    }
    public int getReccursivePeriod() {
        return reccursivePeriod;
    }
    public void setReccursivePeriod(int reccursivePeriod) {
        this.reccursivePeriod = reccursivePeriod;
    }
}
