package com.mongorest.olahtek.model;

import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

public class TaxableLimitExpense implements DataSerializable {
	private int year;
	private double value;
	@Override
	public void readData(ObjectDataInput in) throws IOException {
		year=in.readInt();
		value=in.readDouble();
	}
	@Override
	public void writeData(ObjectDataOutput out) throws IOException {
	out.writeInt(year);
	out.writeDouble(value);
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}

}
