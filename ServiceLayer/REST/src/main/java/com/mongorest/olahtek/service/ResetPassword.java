package com.mongorest.olahtek.service;

import com.mongorest.olahtek.model.User;

public interface ResetPassword {

	boolean insertRandomPassword(User user, String output1);

	boolean killingSession(User user);

	boolean sendFromGMail(String usermailId, String password);

	boolean sendGMailFromAdmin(String usermailId, String password);

}
