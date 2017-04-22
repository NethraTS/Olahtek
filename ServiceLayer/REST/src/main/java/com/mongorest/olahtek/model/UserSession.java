package com.mongorest.olahtek.model;

import java.util.List;

public class UserSession {

	String id;
	String user_name;
	List<LogedInTime> logedInTime;
	int hitCount;
	int goalNotfeasibilityCount;
	int planNotfeasibilityCount;

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public List<LogedInTime> getLogedInTime() {
		return logedInTime;
	}

	public void setLogedInTime(List<LogedInTime> logedInTime) {
		this.logedInTime = logedInTime;
	}

	public int getGoalNotfeasibilityCount() {
		return goalNotfeasibilityCount;
	}

	public void setGoalNotfeasibilityCount(int goalNotfeasibilityCount) {
		this.goalNotfeasibilityCount = goalNotfeasibilityCount;
	}

	public int getPlanNotfeasibilityCount() {
		return planNotfeasibilityCount;
	}

	public void setPlanNotfeasibilityCount(int planNotfeasibilityCount) {
		this.planNotfeasibilityCount = planNotfeasibilityCount;
	}

}
