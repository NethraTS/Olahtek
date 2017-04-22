package com.mongorest.olahtek.model;

public class Emergencyfundmodel {
	private String plan_name;
	private String actionHomeType;
	private String timePeriod;
	private long amountSave;
	int completedStatus;
	private String monthI;
	private String monthE;

	public Emergencyfundmodel() {

	}

	public Emergencyfundmodel(String plan_name, String actionHomeType, String timePeriod, long amountSave, double emi, String user_id, String goal_id,
			String _id, String fin_id, String created_ts, String modified_ts, String goalType, String monthI, String monthE) {
		super();
		this.plan_name = plan_name;
		this.actionHomeType = actionHomeType;
		this.timePeriod = timePeriod;
		this.amountSave = amountSave;
		this.emi = emi;
		this.user_id = user_id;
		this.goal_id = goal_id;
		this._id = _id;
		this.fin_id = fin_id;
		this.created_ts = created_ts;
		this.modified_ts = modified_ts;
		this.goalType = goalType;
		this.monthI = monthI;
		this.monthE = monthE;
	}

	public int getCompletedStatus() {
		return completedStatus;
	}

	public void setCompletedStatus(int completedStatus) {
		this.completedStatus = completedStatus;
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

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public long getAmountSave() {
		return amountSave;
	}

	public void setAmountSave(long amountSave) {
		this.amountSave = amountSave;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
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

	public String getMonthI() {
		return monthI;
	}

	public void setMonthI(String monthI) {
		this.monthI = monthI;
	}

	public String getMonthE() {
		return monthE;
	}

	public void setMonthE(String monthE) {
		this.monthE = monthE;
	}

	private double emi;
	private String user_id;
	private String goal_id;
	private String _id;
	private String fin_id;
	private String created_ts;
	private String modified_ts;
	private String goalType;
}