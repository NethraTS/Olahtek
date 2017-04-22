package com.mongorest.olahtek.model;

public class LogedInTime {
	String loginTimeStamp;
	String logoutTimeStamp;
	private String[] visitedPages;

	public String getLoginTimeStamp() {
		return loginTimeStamp;
	}

	public void setLoginTimeStamp(String loginTimeStamp) {
		this.loginTimeStamp = loginTimeStamp;
	}

	public String getLogoutTimeStamp() {
		return logoutTimeStamp;
	}

	public void setLogoutTimeStamp(String logoutTimeStamp) {
		this.logoutTimeStamp = logoutTimeStamp;
	}

	public String[] getVisitedPages() {
		return visitedPages;
	}

	public void setVisitedPages(String[] visitedPages) {
		this.visitedPages = visitedPages;
	}

}
