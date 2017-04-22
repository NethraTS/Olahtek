package com.mongorest.olahtek.model;

public class Reportmodel {
	private String user_id;
	private String created_ts;
	private String modified_ts;
	private String planName;
	private String actionHomeType;

	public Reportmodel() {

	}

	public Reportmodel(String user_id, String created_ts, String modified_ts, String planName, String actionHomeType) {
		super();
		this.user_id = user_id;
		this.created_ts = created_ts;
		this.modified_ts = modified_ts;
		this.planName = planName;
		// this.actionHomeType = actionHomeType;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getActionHomeType() {
		return actionHomeType;
	}

	public void setActionHomeType(String actionHomeType) {
		this.actionHomeType = actionHomeType;
	}

}
