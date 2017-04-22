package com.mongorest.olahtek.model;

public class Session {
	private String _id;

	private String user_id;

	private String loggedInTime;

	private String lastVisitedPage;

	private String role;

	public Session() {

	}

	public Session(String role, String lastVisitedPage, String user_id, String loggedInTime, String _id) {
		this._id = _id;
		this.lastVisitedPage = lastVisitedPage;
		this.loggedInTime = loggedInTime;
		this.user_id = user_id;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getLoggedInTime() {
		return loggedInTime;
	}

	public void setLoggedInTime(String loggedInTime) {
		this.loggedInTime = loggedInTime;
	}

	public String getLastVisitedPage() {
		return lastVisitedPage;
	}

	public void setLastVisitedPage(String lastVisitedPage) {
		this.lastVisitedPage = lastVisitedPage;
	}

}
