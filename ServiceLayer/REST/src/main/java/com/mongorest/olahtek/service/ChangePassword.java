package com.mongorest.olahtek.service;

public interface ChangePassword {
	
	boolean checkUserPassword(String user_id,String oldPassword);
	boolean insertRandomPassword(String user_id,String encodedPassword);

}
