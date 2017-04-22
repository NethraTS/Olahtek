package com.mongorest.olahtek.model;

public class EmergencyFundEdit {
	private String _id;
	private long amountSave;
	private String timePeriod;
    private String formType;
    private String goal_id;
    private String plan_name;
    private String user_id;
    private String goalType;
    private String completedStatus;


	public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
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


	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}





	public String getCompletedStatus() {
		return completedStatus;
	}

	public void setCompletedStatus(String completedStatus) {
		this.completedStatus = completedStatus;
	}


	public EmergencyFundEdit() {
	}

	public EmergencyFundEdit(long timePeriod, long amountSave, long Amountsave, long Timeperiod, String _id, String formType, String goal_id, String user_id,
			String goalType, String completedStatus, String plan_name) {


		this.formType = formType;
		this.goal_id = goal_id;
		this.goalType = goalType;
		this.user_id = user_id;
		this.completedStatus = completedStatus;
		this._id = _id;
		this.plan_name = plan_name;
	}



	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getGoal_id() {
		return goal_id;
	}

	public void setGoal_id(String goal_id) {
		this.goal_id = goal_id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Emergency Fund: _id = " + this.get_id() ;

	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}
