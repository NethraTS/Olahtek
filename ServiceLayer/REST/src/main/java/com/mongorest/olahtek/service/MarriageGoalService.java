package com.mongorest.olahtek.service;

import org.json.JSONObject;

import com.mongorest.olahtek.model.Marriagegoalmodel;

public interface MarriageGoalService {
	JSONObject insertMarriageGoalData(Marriagegoalmodel marriageGoalModel);
	JSONObject editMarriageGoalData(Marriagegoalmodel marriageGoalModel);
	JSONObject createSpouseIncome(Marriagegoalmodel marriageGoalModel);
}
