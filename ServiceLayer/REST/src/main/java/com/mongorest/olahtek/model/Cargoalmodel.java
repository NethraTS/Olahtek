package com.mongorest.olahtek.model;

import java.util.List;

public class Cargoalmodel {
    private String plan_name;
    private String actionHomeType;
    private int carYear;
    private long carPrice;
    private String rentLease;
    private int residualValue;
    private int leaseYear;
    private double moneyFactor;
    private String user_id;
    private String goal_id;
    private String _id;
    private String fin_id;
    private double monthlyPayment;
    private double annualCost;
    private int timePeriod;
    private double intrestRate;
    private double exactAnual_morgage;
    int completedStatus;
    private int downPayment;
    private String creditScore;
    private String goalName;
    private String carReccursive;
    private int reccursivePeriod;
    private String carBuyingReccursive;
    private int tradeIn;
    private int reccursiveBuyingPeriod;
    public String getCarBuyingReccursive() {
        return carBuyingReccursive;
    }

    public void setCarBuyingReccursive(String carBuyingReccursive) {
        this.carBuyingReccursive = carBuyingReccursive;
    }

    public int getTradeIn() {
        return tradeIn;
    }

    public void setTradeIn(int tradeIn) {
        this.tradeIn = tradeIn;
    }

    public int getReccursiveBuyingPeriod() {
        return reccursiveBuyingPeriod;
    }

    public void setReccursiveBuyingPeriod(int reccursiveBuyingPeriod) {
        this.reccursiveBuyingPeriod = reccursiveBuyingPeriod;
    }

    /*
     * private double emergencyFundExpense;
     *
     * public double getEmergencyFundExpense() { return emergencyFundExpense; }
     *
     * public void setEmergencyFundExpense(double emergencyFundExpense) {
     * this.emergencyFundExpense = emergencyFundExpense; }
     */
    private List<GoalFeasibility> emergencyFundExpense;

    public List<GoalFeasibility> getEmergencyFundExpense() {
        return emergencyFundExpense;
    }

    public void setEmergencyFundExpense(List<GoalFeasibility> emergencyFundExpense) {
        this.emergencyFundExpense = emergencyFundExpense;
    }

    public int getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(int downPayment) {
        this.downPayment = downPayment;
    }

    String createdTs;
    String modifiedTs;
    private boolean goalFeasibility;

    public boolean getGoalFeasibility() {
        return goalFeasibility;
    }

    public void setGoalFeasiblity(boolean goalFeasibility) {
        this.goalFeasibility = goalFeasibility;
    }

    public int getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(int completedStatus) {
        this.completedStatus = completedStatus;
    }

    public String getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(String createdTs) {
        this.createdTs = createdTs;
    }

    public String getModifiedTs() {
        return modifiedTs;
    }

    public void setModifiedTs(String modifiedTs) {
        this.modifiedTs = modifiedTs;
    }

    public Cargoalmodel(double exactAnual_morgage) {
        super();

    }

    public int getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(int timePeriod) {
        this.timePeriod = timePeriod;
    }

    public double getIntrestRate() {
        return intrestRate;
    }

    public void setIntrestRate(double intrestRate) {
        this.intrestRate = intrestRate;
    }

    public Cargoalmodel(double monthlyPayment, double annualCost) {
        super();
        this.monthlyPayment = monthlyPayment;
        this.annualCost = annualCost;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getAnnualCost() {
        return annualCost;
    }

    public void setAnnualCost(double annualCost) {
        this.annualCost = annualCost;
    }

    public Cargoalmodel() {

    }

    public Cargoalmodel(String plan_name, String actionHomeType, int carYear, long carPrice, String rentLease, int residualValue, int timePeriod,
            double intrestRate, int leaseYear, double moneyFactor, String user_id, String goal_id, String _id, String fin_id, String created_ts,
            String modified_ts, String goalType, long mortgage_expense, long mortgageAmount, double anual_morgage, double exactAnual_morgage, int downPayment) {
        super();
        this.plan_name = plan_name;
        this.actionHomeType = actionHomeType;
        this.carYear = carYear;
        this.carPrice = carPrice;
        this.rentLease = rentLease;
        this.residualValue = residualValue;
        this.timePeriod = timePeriod;
        this.intrestRate = intrestRate;
        this.leaseYear = leaseYear;
        this.moneyFactor = moneyFactor;
        this.user_id = user_id;
        this.goal_id = goal_id;
        this._id = _id;
        this.fin_id = fin_id;
        this.created_ts = created_ts;
        this.modified_ts = modified_ts;
        this.goalType = goalType;
        this.mortgage_expense = mortgage_expense;
        this.mortgageAmount = mortgageAmount;
        this.exactAnual_morgage = exactAnual_morgage;
        this.Anual_morgage = anual_morgage;
        this.downPayment = downPayment;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getActionHomeType() {
        return actionHomeType;
    }

    public void setActionHomeType(String actionHomeType) {
        this.actionHomeType = actionHomeType;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public long getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(long carPrice) {
        this.carPrice = carPrice;
    }

    public String getRentLease() {
        return rentLease;
    }

    public void setRentLease(String rentLease) {
        this.rentLease = rentLease;
    }

    public int getResidualValue() {
        return residualValue;
    }

    public void setResidualValue(int residualValue) {
        this.residualValue = residualValue;
    }

    public int getLeaseYear() {
        return leaseYear;
    }

    public void setLeaseYear(int leaseYear) {
        this.leaseYear = leaseYear;
    }

    public double getMoneyFactor() {
        return moneyFactor;
    }

    public void setMoneyFactor(double moneyFactor) {
        this.moneyFactor = moneyFactor;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(String goal_id) {
        this.goal_id = goal_id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFin_id() {
        return fin_id;
    }

    public void setFin_id(String fin_id) {
        this.fin_id = fin_id;
    }

    public String getCreated_ts() {
        return created_ts;
    }

    public void setCreated_ts(String created_ts) {
        this.created_ts = created_ts;
    }

    public String getModified_ts() {
        return modified_ts;
    }

    public void setModified_ts(String modified_ts) {
        this.modified_ts = modified_ts;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public long getMortgage_expense() {
        return mortgage_expense;
    }

    public void setMortgage_expense(long mortgage_expense) {
        this.mortgage_expense = mortgage_expense;
    }

    public long getMortgageAmount() {
        return mortgageAmount;
    }

    public void setMortgageAmount(long mortgageAmount) {
        this.mortgageAmount = mortgageAmount;
    }

    public double getAnual_morgage() {
        return Anual_morgage;
    }

    public void setAnual_morgage(long anual_morgage) {
        Anual_morgage = anual_morgage;
    }

    public double getExactAnual_morgage() {
        return exactAnual_morgage;
    }

    public void setExactAnual_morgage(double exactAnual_morgage) {
        this.exactAnual_morgage = exactAnual_morgage;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
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

    private String created_ts;
    private String modified_ts;
    private String goalType;
    private long mortgage_expense;
    private long mortgageAmount;
    private double Anual_morgage;

}
