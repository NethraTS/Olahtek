package com.mongorest.olahtek.model;

import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

public class Assets implements DataSerializable{
	int year;
	double savings;
	double taxable_investment_amount;
	double nontaxable_investment_amount;
	double plan529;
	double spouse401k;
	double user401k;
	double userRothIRA;
	double spouseRothIRA;
	double userIRA;
	double spouseIRA;
	double user529Plan;
	double spouse529Plan;
   
	

	public double getSpouse401k() {
		return spouse401k;
	}

	public void setSpouse401k(double spouse401k) {
		this.spouse401k = spouse401k;
	}

	public double getUser401k() {
		return user401k;
	}

	public void setUser401k(double user401k) {
		this.user401k = user401k;
	}

	public double getUserRothIRA() {
		return userRothIRA;
	}

	public void setUserRothIRA(double userRothIRA) {
		this.userRothIRA = userRothIRA;
	}

	public double getSpouseRothIRA() {
		return spouseRothIRA;
	}

	public void setSpouseRothIRA(double spouseRothIRA) {
		this.spouseRothIRA = spouseRothIRA;
	}


	

	public double getUserIRA() {
		return userIRA;
	}

	public void setUserIRA(double userIRA) {
		this.userIRA = userIRA;
	}

	public double getSpouseIRA() {
		return spouseIRA;
	}

	public void setSpouseIRA(double spouseIRA) {
		this.spouseIRA = spouseIRA;
	}

	public double getUser529Plan() {
		return user529Plan;
	}

	public void setUser529Plan(double user529Plan) {
		this.user529Plan = user529Plan;
	}

	public double getSpouse529Plan() {
		return spouse529Plan;
	}

	public void setSpouse529Plan(double spouse529Plan) {
		this.spouse529Plan = spouse529Plan;
	}

	public double getPlan529() {
		return plan529;
	}

	public void setPlan529(double plan529) {
		this.plan529 = plan529;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getSavings() {
		return savings;
	}

	public void setSavings(double savings) {
		this.savings = savings;
	}

	public double getTaxable_investment_amount() {
		return taxable_investment_amount;
	}

	public void setTaxable_investment_amount(double taxable_investment_amount) {
		this.taxable_investment_amount = taxable_investment_amount;
	}

	public double getNontaxable_investment_amount() {
		return nontaxable_investment_amount;
	}

	public void setNontaxable_investment_amount(double nontaxable_investment_amount) {
		this.nontaxable_investment_amount = nontaxable_investment_amount;
	}

	@Override
	public void writeData(ObjectDataOutput out) throws IOException {
		out.writeInt(year);
		out.writeDouble(savings);
		out.writeDouble(taxable_investment_amount);
		out.writeDouble(nontaxable_investment_amount);
		out.writeDouble(plan529);
	}

	@Override
	public void readData(ObjectDataInput in) throws IOException {
		year = in.readInt();
		savings = in.readDouble();
		taxable_investment_amount = in.readDouble();
		nontaxable_investment_amount = in.readDouble();
		plan529 = in.readDouble();
		
	}

}