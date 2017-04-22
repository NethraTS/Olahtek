package com.mongorest.olahtek.service;

import org.json.JSONObject;

import com.mongorest.olahtek.model.AdmindashboardModel;

public interface AdminDashboardService {
	JSONObject regiteredUserCount();

	JSONObject visitorCount();

	JSONObject goalCount();

	JSONObject planCount();

	JSONObject incomeCount();

	JSONObject totalUsedMemory();

	JSONObject totalMemory();

	JSONObject freeMemory();

	JSONObject userDetailDocument();

	JSONObject logMessageDocument();

	JSONObject getStatistics(AdmindashboardModel dataAdmin);

	JSONObject sendFromGMail(String usemailId, String userMessage, String subject);

	JSONObject addAdmin(AdmindashboardModel dataAdmin);

	JSONObject existingUser(AdmindashboardModel dataAdmin);

	JSONObject editOnloadProfile(AdmindashboardModel dataAdmin);

	JSONObject editProfile(AdmindashboardModel dataAdmin);

	JSONObject serverConfig(AdmindashboardModel dataAdmin);

	JSONObject onloadServerConfiguration(AdmindashboardModel dataAdmin);

	JSONObject goalTypeHouse(AdmindashboardModel dataAdmin);

	JSONObject deletUser(AdmindashboardModel dataAdmin);

}
