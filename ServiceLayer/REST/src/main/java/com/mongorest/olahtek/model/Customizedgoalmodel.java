package com.mongorest.olahtek.model;

import java.util.List;

public class Customizedgoalmodel {
	private String plan_name;
	private String actionHomeType;
	private String goalname;
	private double goalCost;
	private int goalEndingyear;
	private int goalStartingYear;
	private String user_id;
	private String goal_id;
	private String _id;
	private String fin_id;
	private String created_ts;
	private String modified_ts;
	private String goalType;
	private String favoriteColors;
	private String typeOfGoalCost;
	public String getTypeOfGoalCost() {
		return typeOfGoalCost;
	}

	public void setTypeOfGoalCost(String typeOfGoalCost) {
		this.typeOfGoalCost = typeOfGoalCost;
	}

	private String perYear;
	int completedStatus;
	private boolean goalFeasibility;
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

	public Customizedgoalmodel() {

	}

	public Customizedgoalmodel(String plan_name, String actionHomeType, String goalname, double goalCost, String user_id, String goal_id, String _id,
			String fin_id, String created_ts, String modified_ts, String goalType, int goalEndingyear, int goalStartingyear, String typeOfGoalCost,
			String perYear) {
		super();
		this.plan_name = plan_name;
		this.actionHomeType = actionHomeType;
		this.goalname = goalname;
		this.goalCost = goalCost;
		this.goalEndingyear = goalEndingyear;
		this.goalStartingYear = goalStartingyear;
		this.user_id = user_id;
		this.goal_id = goal_id;
		this._id = _id;
		this.fin_id = fin_id;
		this.created_ts = created_ts;
		this.modified_ts = modified_ts;
		this.goalType = goalType;
		this.typeOfGoalCost = typeOfGoalCost;
		this.perYear = perYear;
	}

	public int getGoalEndingyear() {
		return goalEndingyear;
	}

	public void setGoalEndingyear(int goalEndingyear) {
		this.goalEndingyear = goalEndingyear;
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

	public String getGoalname() {
		return goalname;
	}

	public void setGoalname(String goalname) {
		this.goalname = goalname;
	}

	public double getGoalCost() {
		return goalCost;
	}

	public void setGoalCost(double goalCost) {
		this.goalCost = goalCost;
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

	public int getGoalStartingYear() {
		return goalStartingYear;
	}

	public void setGoalStartingYear(int goalStartingYear) {
		this.goalStartingYear = goalStartingYear;
	}

	public String getFavoriteColors() {
		return favoriteColors;
	}

	public void setFavoriteColors(String favoriteColors) {
		this.favoriteColors = favoriteColors;
	}

	public String getPerYear() {
		return perYear;
	}

	public void setPerYear(String perYear) {
		this.perYear = perYear;
	}

}
