package com.mongorest.olahtek.model;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MinimumDistributionModel {
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	private String _id;
	@JsonProperty("minimumPercentageWithdrawal")
	private HashMap<String, Double> minimumPercentageWithdrawal;
	public HashMap<String, Double> getMinimumPercentageWithdrawal() {
		return minimumPercentageWithdrawal;
	}

	public void setMinimumPercentageWithdrawal(HashMap<String, Double> minimumPercentageWithdrawal) {
		this.minimumPercentageWithdrawal = minimumPercentageWithdrawal;
	}
	private HashMap<String,List<Double>> minDistribution;
	public HashMap<String,List<Double>> getMinDistribution() {
		return minDistribution;
	}
	public void setMinDistribution(HashMap<String,List<Double>> minDistribution) {
		this.minDistribution = minDistribution;
	}

}
