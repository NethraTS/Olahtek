package com.mongorest.olahtek.model;

import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

public class FilingExemption implements DataSerializable{
	String fillingStatus;
	String oldFilingStatus;
	int noOfExcemtion;
	int year;

	public String getOldFilingStatus() {
		return oldFilingStatus;
	}

	public void setOldFilingStatus(String oldFilingStatus) {
		this.oldFilingStatus = oldFilingStatus;
	}

	public String getFillingStatus() {
		return fillingStatus;
	}

	public void setFillingStatus(String fillingStatus) {
		this.fillingStatus = fillingStatus;
	}

	public int getNoOfExcemtion() {
		return noOfExcemtion;
	}

	public void setNoOfExcemtion(int noOfExcemtion) {
		this.noOfExcemtion = noOfExcemtion;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public void writeData(ObjectDataOutput out) throws IOException {
		out.writeInt(year);
		out.writeInt(noOfExcemtion);
		out.writeUTF(fillingStatus);
	}

	@Override
	public void readData(ObjectDataInput in) throws IOException {
		year = in.readInt();
		noOfExcemtion = in.readInt();
		fillingStatus = in.readUTF();
	}

}
