package com.mongorest.olahtek.service;

import org.json.JSONObject;

import com.mongorest.olahtek.model.Housegoalmodel;

public interface HouseGoal {
	JSONObject insertHouseGoalData(Housegoalmodel housegoalmodel);

	JSONObject editHouseGoalData(Housegoalmodel housegoalmodel);

	JSONObject calHouseValue(Housegoalmodel housegoalmodel);
	
	JSONObject houseStatus(Housegoalmodel housegoalmodel);
	
	JSONObject oldHouseRemaningMortgageCal(Housegoalmodel housegoalmodel);
	
	JSONObject ownHouseRemaningMortgageCal(Housegoalmodel housegoalmodel);

}