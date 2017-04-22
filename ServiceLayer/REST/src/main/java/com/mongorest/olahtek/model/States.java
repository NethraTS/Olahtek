package com.mongorest.olahtek.model;

import java.util.List;

public class States {

	private String _id;
	private String Name;
	private String Code;
	private String thirtyYearFixed;
	private String fifteenYearFixed;
	private String fiveOneARM;
	private List<City> cities;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getThirtyYearFixed() {
		return thirtyYearFixed;
	}

	public void setThirtyYearFixed(String thirtyYearFixed) {
		this.thirtyYearFixed = thirtyYearFixed;
	}

	public String getFifteenYearFixed() {
		return fifteenYearFixed;
	}

	public void setFifteenYearFixed(String fifteenYearFixed) {
		this.fifteenYearFixed = fifteenYearFixed;
	}

	public String getFiveOneARM() {
		return fiveOneARM;
	}

	public void setFiveOneARM(String fiveOneARM) {
		this.fiveOneARM = fiveOneARM;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

}
