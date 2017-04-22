package com.mongorest.olahtek.model;

import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

public class UserExpense implements DataSerializable{
	double housingExpense;
	double nonHousingExpense;
	double registerHousingExpense;
	double registerNonHousingExpense;
	double afterMarriageExpense;
	double kidExpense;
	int year;
	double totalExpense;
	double mortgageExpense;

	public double getRegisterHousingExpense() {
		return registerHousingExpense;
	}

	public void setRegisterHousingExpense(double registerHousingExpense) {
		this.registerHousingExpense = registerHousingExpense;
	}

	public double getRegisterNonHousingExpense() {
		return registerNonHousingExpense;
	}

	public void setRegisterNonHousingExpense(double registerNonHousingExpense) {
		this.registerNonHousingExpense = registerNonHousingExpense;
	}

	public double getMortgageExpense() {
		return mortgageExpense;
	}

	public void setMortgageExpense(double mortgageExpense) {
		this.mortgageExpense = mortgageExpense;
	}

	public double getHousingExpense() {
		return housingExpense;
	}

	public void setHousingExpense(double housingExpense) {
		this.housingExpense = housingExpense;
	}

	public double getNonHousingExpense() {
		return nonHousingExpense;
	}

	public void setNonHousingExpense(double nonHousingExpense) {
		this.nonHousingExpense = nonHousingExpense;
	}

	public double getAfterMarriageExpense() {
		return afterMarriageExpense;
	}

	public void setAfterMarriageExpense(double afterMarriageExpense) {
		this.afterMarriageExpense = afterMarriageExpense;
	}

	public double getKidExpense() {
		return kidExpense;
	}

	public void setKidExpense(double kidExpense) {
		this.kidExpense = kidExpense;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(double totalExpense) {
		this.totalExpense = totalExpense;
	}

	@Override
	public void writeData(ObjectDataOutput out) throws IOException {
		out.writeInt(year);
		out.writeDouble(housingExpense);
		out.writeDouble(nonHousingExpense);
		out.writeDouble(afterMarriageExpense);
		out.writeDouble(kidExpense);
		out.writeDouble(totalExpense);
		out.writeDouble(mortgageExpense);
		}

	@Override
	public void readData(ObjectDataInput in) throws IOException {
		year = in.readInt();
		housingExpense = in.readDouble();
		nonHousingExpense = in.readDouble();
		afterMarriageExpense = in.readDouble();
		kidExpense = in.readDouble();
		totalExpense = in.readDouble();
		mortgageExpense = in.readDouble();		
	}

}
