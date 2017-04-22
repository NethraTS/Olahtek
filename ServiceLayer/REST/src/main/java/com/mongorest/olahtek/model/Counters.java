package com.mongorest.olahtek.model;

public class Counters {

	private int user_id;
	private int goal_id;
	private int fin_id;
	private int role_id;
	private int income_id;
	private int hitCount;
	private int imp_count;

	public int getImp_count() {
		return imp_count;
	}

	public void setImp_count(int imp_count) {
		this.imp_count = imp_count;
	}

	public Counters() {

	}

	public Counters(int user_id, int goal_id, int fin_id, int role_id, int income_id) {
		this.user_id = user_id;
		this.goal_id = goal_id;
		this.fin_id = fin_id;
		this.role_id = role_id;
		this.income_id = income_id;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getGoal_id() {
		return goal_id;
	}

	public void setGoal_id(int goal_id) {
		this.goal_id = goal_id;
	}

	public int getFin_id() {
		return fin_id;
	}

	public void setFin_id(int fin_id) {
		this.fin_id = fin_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getIncome_id() {
		return income_id;
	}

	public void setIncome_id(int income_id) {
		this.income_id = income_id;
	}

}
