package com.mongorest.olahtek.service;

import org.json.JSONObject;
import com.mongorest.olahtek.model.Customizedgoalmodel;

public interface CustomizedGoalService {
	JSONObject insertCustomizedGoalData(Customizedgoalmodel customizedGoalModel);

	JSONObject editCustomizedGoalData(Customizedgoalmodel customizedGoalModel);

}
