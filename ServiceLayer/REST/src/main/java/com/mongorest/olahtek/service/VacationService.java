package com.mongorest.olahtek.service;

import org.json.JSONObject;

import com.mongorest.olahtek.model.ModelVacation;

public interface VacationService {
	JSONObject saveVacationGoal(ModelVacation vacation);

}
