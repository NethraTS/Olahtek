package com.mongorest.olahtek;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Login {

	@JsonProperty("password")
    String password;
	@JsonProperty("name")
	String name;
	@JsonProperty("_id")
    String _id;
	@JsonCreator
	public Login()
	{
		
	}
	public String getName()
	{
		return name;
	}
	public String getPassword()
	{
		return password;
	}
	
	public String getUser_id()
	{
		return _id;
	}
}
