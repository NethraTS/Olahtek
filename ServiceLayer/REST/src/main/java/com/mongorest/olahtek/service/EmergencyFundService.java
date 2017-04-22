package com.mongorest.olahtek.service;

import org.json.JSONObject;

import com.mongorest.olahtek.model.Emergencyfundmodel;


public interface EmergencyFundService {
	JSONObject insertEmergencyGoalData(Emergencyfundmodel emergencyFundMode1);
	JSONObject editEmergencyGoalData(Emergencyfundmodel emergencyFundMode1);
	JSONObject deleteEmergencyGoalData(Emergencyfundmodel emergencyFundMode1);
	JSONObject onLoadEmergencyFund(Emergencyfundmodel emergencyFundMode1);
}