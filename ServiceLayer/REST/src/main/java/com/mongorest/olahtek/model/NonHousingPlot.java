package com.mongorest.olahtek.model;

public class NonHousingPlot {
    int year;
    public int getYear() {
        return year;
    }
    public void setYear(final int year) {
        this.year = year;
    }
    public double getNonHousingExpense() {
        return nonHousingExpense;
    }
    public void setNonHousingExpense(final double nonHousingExpense) {
        this.nonHousingExpense = nonHousingExpense;
    }
    double nonHousingExpense;
}
