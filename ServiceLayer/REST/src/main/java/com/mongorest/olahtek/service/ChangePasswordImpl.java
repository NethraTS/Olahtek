package com.mongorest.olahtek.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongorest.olahtek.model.Session;
import com.mongorest.olahtek.model.User;

@Service("changePasswordService")
@Transactional
public class ChangePasswordImpl implements ChangePasswordService {

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	ObjectMapper jsonMapper = new ObjectMapper();

	@JsonCreator
	/*--------------------------check  password-------------------------------*/
	public boolean checkUserPassword(String user_id, String encodedOldPassword) {
		try {
			User userData = MongoDBConnection.userColl.findOne("{_id:#,password:#}", user_id, encodedOldPassword).as(User.class);
			if (userData != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertRandomPassword(String user_id, String encodedPassword) {
		try {
			MongoDBConnection.userColl.update("{'_id': '" + user_id + "'}").upsert().multi()
					.with("{$set: {'password':'" + encodedPassword + "','modified_ts':'" + dateFormat.format(date) + "'}}");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean logOutUser(String user_id) {
		try {
			Session sessionDetails = MongoDBConnection.sessionColl.findOne("{user_id:#}", user_id).as(Session.class);
			if (sessionDetails.getUser_id().equals(user_id)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
