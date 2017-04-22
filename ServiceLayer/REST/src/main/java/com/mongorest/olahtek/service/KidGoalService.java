package com.mongorest.olahtek.service;

import org.json.JSONObject;
import com.mongorest.olahtek.model.KidGoalModel;

public interface KidGoalService {

	JSONObject insertCustomizedGoalData(KidGoalModel kidGoalModel);

	JSONObject editCustomizedGoalData(KidGoalModel kidGoalModel);
	JSONObject calculateKidCost(KidGoalModel kidGoalModel);
	
	
}
