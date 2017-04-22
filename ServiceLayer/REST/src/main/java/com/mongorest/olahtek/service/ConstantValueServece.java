package com.mongorest.olahtek.service;

import org.json.JSONObject;

import com.mongorest.olahtek.model.ConstantValuesModel;
import com.mongorest.olahtek.model.ConstantsValues;
import com.mongorest.olahtek.model.StatetaxModel;

public interface ConstantValueServece {
	JSONObject onLoadStateData(String stateName);
	JSONObject onLoadConstantValues(StatetaxModel stateName);
	JSONObject updateStateConstantValues(StatetaxModel servletJson);
	JSONObject updateConstantValues(ConstantsValues servletJson);
    JSONObject onLoadAllStateNames();
    JSONObject onLoadFdiData(ConstantValuesModel constantsValues);
	JSONObject updateConstantValuesOfFdi(ConstantValuesModel constantsValues);
}
