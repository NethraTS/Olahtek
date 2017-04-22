package com.mongorest.olahtek.model;

import java.util.List;

public class Marriagegoalmodel {
	private String plan_name;
	private String actionHomeType;
	private int marriageYear;
	int spouseAge;
	int birthYear;
	private long marriageCost;
	private double annualexpense;
	private long annualExcess;
	private int expensesStartYear;
	private int expensesEndYear;
	private long spouseIncome;
	int completedStatus;
	long newMonthlyExpense;
	private long nonHousingExpense;
	
	private long housingExpenses;
	
	// private String _id;
	/*
	 * private double emergencyFundExpense;
	 * 
	 * public double getEmergencyFundExpense() { return emergencyFundExpense; }
	 * 
	 * public void setEmergencyFundExpense(double emergencyFundExpense) {
	 * this.emergencyFundExpense = emergencyFundExpense; }
	 */

	private List<GoalFeasibility> emergencyFundExpense;

	
	public long getNonHousingExpense() {
		return nonHousingExpense;
	}

	public void setNonHousingExpense(long nonHousingExpense) {
		this.nonHousingExpense = nonHousingExpense;
	}

	public long getHousingExpenses() {
		return housingExpenses;
	}

	public void setHousingExpenses(long housingExpenses) {
		this.housingExpenses = housingExpenses;
	}

	public long getNewMonthlyExpense() {
		return newMonthlyExpense;
	}

	public void setNewMonthlyExpense(long newMonthlyExpense) {
		this.newMonthlyExpense = newMonthlyExpense;
	}

	public int getSpouseAge() {
		return spouseAge;
	}

	public void setSpouseAge(int spouseAge) {
		this.spouseAge = spouseAge;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public void setGoalFeasibility(boolean goalFeasibility) {
		this.goalFeasibility = goalFeasibility;
	}

	public List<GoalFeasibility> getEmergencyFundExpense() {
		return emergencyFundExpense;
	}

	public void setEmergencyFundExpense(List<GoalFeasibility> emergencyFundExpense) {
		this.emergencyFundExpense = emergencyFundExpense;
	}

	private boolean goalFeasibility;

	public boolean getGoalFeasibility() {
		return goalFeasibility;
	}

	public void setGoalFeasiblity(boolean goalFeasibility) {
		this.goalFeasibility = goalFeasibility;
	}

	public Marriagegoalmodel() {

	}

	public int getCompletedStatus() {
		return completedStatus;
	}

	public void setCompletedStatus(int completedStatus) {
		this.completedStatus = completedStatus;
	}

	public int getExpensesStartYear() {
		return expensesStartYear;
	}

	public void setExpensesStartYear(int expensesStartYear) {
		this.expensesStartYear = expensesStartYear;
	}

	public int getExpensesEndYear() {
		return expensesEndYear;
	}

	public void setExpensesEndYear(int expensesEndYear) {
		this.expensesEndYear = expensesEndYear;
	}

	public long getSpouseIncome() {
		return spouseIncome;
	}

	public void setSpouseIncome(long spouseIncome) {
		this.spouseIncome = spouseIncome;
	}

	public Marriagegoalmodel(String plan_name, String actionHomeType, int marriageYear, long marriageCost, String user_id, String goal_id, String _id,
			String fin_id, String created_ts, String modified_ts, String goalType, double annualexpense, long annualExcess) {
		super();
		this.plan_name = plan_name;
		this.actionHomeType = actionHomeType;
		this.marriageYear = marriageYear;
		this.marriageCost = marriageCost;
		this.annualexpense = annualexpense;
		this.user_id = user_id;
		this.goal_id = goal_id;
		this._id = _id;
		this.fin_id = fin_id;
		this.created_ts = created_ts;
		this.modified_ts = modified_ts;
		this.goalType = goalType;
		this.annualExcess = annualExcess;
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

	public long getMarriageCost() {
		return marriageCost;
	}

	public void setMarriageCost(long marriageCost) {
		this.marriageCost = marriageCost;
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

	public int getMarriageYear() {
		return marriageYear;
	}

	public void setMarriageYear(int marriageYear) {
		this.marriageYear = marriageYear;
	}

	public double getAnnualexpense() {
		return annualexpense;
	}

	public void setAnnualexpense(double annualexpense) {
		this.annualexpense = annualexpense;
	}

	public long getAnnualExcess() {
		return annualExcess;
	}

	public void setAnnualExcess(long annualExcess) {
		this.annualExcess = annualExcess;
	}

	private String user_id;
	private String goal_id;
	private String _id;
	private String fin_id;
	private String created_ts;
	private String modified_ts;
	private String goalType;

}
