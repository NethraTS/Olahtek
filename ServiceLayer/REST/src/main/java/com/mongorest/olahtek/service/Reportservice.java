package com.mongorest.olahtek.service;

import org.json.JSONObject;

import com.mongorest.olahtek.model.Reportmodel;

public interface Reportservice {
	JSONObject fetchingData(Reportmodel reportModel);
	JSONObject fetchingGoalData(Reportmodel reportModel);

}
