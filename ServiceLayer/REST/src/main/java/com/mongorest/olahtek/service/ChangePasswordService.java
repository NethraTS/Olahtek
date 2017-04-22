package com.mongorest.olahtek.service;

public interface ChangePasswordService {
	
	boolean checkUserPassword(String user_id,String encodedOldPassword);
	boolean insertRandomPassword(String user_id,String encodedPassword);
	boolean logOutUser(String user_id);

}
