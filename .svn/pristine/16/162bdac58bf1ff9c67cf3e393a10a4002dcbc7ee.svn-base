package com.mongorest.olahtek.model;

import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

public class TaxModel implements DataSerializable {
	int year;
	double federalTax;
	double fICASocialSecurityTax;
	double fICAMedicareTax;
	double userSSTax;
	double spouseSSTax;
	double stateTax;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getFederalTax() {
		return federalTax;
	}

	public void setFederalTax(double federalTax) {
		this.federalTax = federalTax;
	}

	public double getfICASocialSecurityTax() {
		return fICASocialSecurityTax;
	}

	public void setfICASocialSecurityTax(double fICASocialSecurityTax) {
		this.fICASocialSecurityTax = fICASocialSecurityTax;
	}

	public double getfICAMedicareTax() {
		return fICAMedicareTax;
	}

	public void setfICAMedicareTax(double fICAMedicareTax) {
		this.fICAMedicareTax = fICAMedicareTax;
	}

	public double getUserSSTax() {
		return userSSTax;
	}

	public void setUserSSTax(double userSSTax) {
		this.userSSTax = userSSTax;
	}

	public double getSpouseSSTax() {
		return spouseSSTax;
	}

	public void setSpouseSSTax(double spouseSSTax) {
		this.spouseSSTax = spouseSSTax;
	}

	public double getStateTax() {
		return stateTax;
	}

	public void setStateTax(double stateTax) {
		this.stateTax = stateTax;
	}

	@Override
	public void writeData(ObjectDataOutput out) throws IOException {
		out.writeInt(year);
		out.writeDouble(federalTax);
		out.writeDouble(fICASocialSecurityTax);
		out.writeDouble(fICAMedicareTax);
		out.writeDouble(userSSTax);
		out.writeDouble(spouseSSTax);
		out.writeDouble(stateTax);
		
	}

	@Override
	public void readData(ObjectDataInput in) throws IOException {
		year = in.readInt();
		federalTax = in.readDouble();
		fICASocialSecurityTax = in.readDouble();
		fICAMedicareTax = in.readDouble();
		userSSTax = in.readDouble();
		spouseSSTax = in.readDouble();
		stateTax = in.readDouble();
		
	}

}
