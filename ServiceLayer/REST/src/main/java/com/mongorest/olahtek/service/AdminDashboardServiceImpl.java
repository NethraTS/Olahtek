package com.mongorest.olahtek.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.jongo.MongoCursor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongorest.olahtek.model.AdminGoalType;
import com.mongorest.olahtek.model.AdmindashboardModel;
import com.mongorest.olahtek.model.Counters;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.SystemLog;
//import com.mongorest.olahtek.model.Session;
import com.mongorest.olahtek.model.User;
import com.mongorest.olahtek.model.UserSession;

@Service("admindashboardService")
@Transactional
public class AdminDashboardServiceImpl implements AdminDashboardService {

	public static int numConnection = 0;
	String statescollection;
	String usersSessioncollection;
	String systemConfiguration;
	String IncomeProfilecollection;
	ObjectMapper jsonMapper = new ObjectMapper();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	DecimalFormat decimalFloat = new DecimalFormat("#.##");
	@JsonCreator
	public JSONObject regiteredUserCount() {
		JSONObject responseToRestController = new JSONObject();
		MongoCursor<User> cursor = MongoDBConnection.userColl.find().as(User.class);
		int nbResults = cursor.count();
		try {
			Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
			responseToRestController.put("status", "sucess");
			responseToRestController.put("usercount", nbResults);
			responseToRestController.put("hitCount", count.getHitCount());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				cursor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseToRestController;
	}
	public JSONObject visitorCount() {
		JSONObject responseToRestsession = new JSONObject();
		MongoCursor<Session> cursor = MongoDBConnection.sessionColl.find().as(Session.class);
		int nbvistor = cursor.count();
		try {
			responseToRestsession.put("status", "sucess");
			responseToRestsession.put("visitorcount", nbvistor);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				cursor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseToRestsession;
	}
	public JSONObject goalCount() {
		JSONObject responseToRestgoalcount = new JSONObject();
		MongoCursor<RetirementGoal> cursor = MongoDBConnection.goalcoll.find().as(RetirementGoal.class);
		int nbgoal = cursor.count();
		try {
			responseToRestgoalcount.put("status", "sucess");
			responseToRestgoalcount.put("goalcount", nbgoal);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				cursor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseToRestgoalcount;
	}
	public JSONObject planCount() {
		JSONObject responseToplancount = new JSONObject();
		MongoCursor<FinPlan> cursor = MongoDBConnection.finplancol.find().as(FinPlan.class);
		int nbcount = cursor.count();
		try {
			responseToplancount.put("status", "sucess");
			responseToplancount.put("plancount", nbcount);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				cursor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseToplancount;
	}
	public JSONObject incomeCount() {
		JSONObject responseToincomecount = new JSONObject();
		MongoCursor<IncomeProfile> cursor = MongoDBConnection.incomeProfileCol.find().as(IncomeProfile.class);
		int nbincomecount = cursor.count();
		try {
			responseToincomecount.put("status", "sucess");
			responseToincomecount.put("incomecount", nbincomecount);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				cursor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseToincomecount;
	}
	public JSONObject totalUsedMemory() {
		JSONObject responseTousedmemory = new JSONObject();
		AdminDashboardServiceImpl obj = new AdminDashboardServiceImpl();
		String domainName = "free -b";
		String output = obj.executeCommand(domainName);
		try {
			responseTousedmemory.put("status", "sucess");
			responseTousedmemory.put("totalusedmemory", output);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseTousedmemory;
	}
	public JSONObject totalMemory() {
		JSONObject responseTototalmemory = new JSONObject();
		long totalMemory = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
		try {
			responseTototalmemory.put("status", "sucess");
			responseTototalmemory.put("totalmemory", totalMemory);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseTototalmemory;
	}
	public JSONObject freeMemory() {
		JSONObject responseTofreememory = new JSONObject();
		long totalfreememory = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
		try {
			responseTofreememory.put("status", "sucess");
			responseTofreememory.put("freememory", totalfreememory);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseTofreememory;
	}
	public JSONObject userDetailDocument() {
		JSONObject responseTouserDocument = new JSONObject();
		MongoCursor<User> cursor = MongoDBConnection.userColl.find().as(User.class);
		try {

			JSONArray userJson = new JSONArray(jsonMapper.writeValueAsString(cursor));
			responseTouserDocument.put("status", "sucess");
			responseTouserDocument.put("userdocument", userJson);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				cursor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseTouserDocument;
	}
	public JSONObject logMessageDocument() {
		JSONObject responseToLogMesg = new JSONObject();
		MongoCursor<SystemLog> cursor = MongoDBConnection.systemLogCollection.find().as(SystemLog.class);
		try {

			JSONArray userJson = new JSONArray(jsonMapper.writeValueAsString(cursor));
			responseToLogMesg.put("status", "sucess");
			responseToLogMesg.put("logMessage", userJson);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				cursor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseToLogMesg;
	}
	public JSONObject sendFromGMail(String usemailId, String userMessage, String subject) {
		Properties props = System.getProperties();
		String RECIPIENT = usemailId;
		String[] to = { RECIPIENT };
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		//System.out.println("email....."+ MongoDBConnection.adminEmail);
		//System.out.println("password.."+ MongoDBConnection.adminPassword);
		props.put("mail.smtp.user", MongoDBConnection.adminEmail);
		props.put("mail.smtp.password", MongoDBConnection.adminPassword);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		JSONObject mailStatus = new JSONObject();
		try {
			message.setFrom(new InternetAddress(MongoDBConnection.adminEmail));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}
			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			message.setSubject(userMessage);
			String body = subject;
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, MongoDBConnection.adminEmail,MongoDBConnection.adminPassword);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			mailStatus.put("status", "sucess");
			return mailStatus;
		} catch (AddressException ae) {
			ae.printStackTrace();
			return mailStatus;
		} catch (Exception me) {
			me.printStackTrace();
			return mailStatus;
		}
	}
	@Override
	public JSONObject addAdmin(AdmindashboardModel dataAdmin) {
		JSONObject rtesponseToServlet = new JSONObject();
		Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
		String userid = "usr" + count.getUser_id();
		try {
			rtesponseToServlet.put("status", "fail");
			BasicDBObject basicDBObject = new BasicDBObject("name", dataAdmin.getFirstName()).append("_id", userid).append("lname", dataAdmin.getLasttName())
					.append("email", dataAdmin.getEmailAdress()).append("role", "admin").append("password", dataAdmin.getPassword());
			MongoDBConnection.userColl.insert(basicDBObject);
			MongoDBConnection.counterColl.update("{'user_id':" + count.getUser_id() + "}").with("{$inc: {user_id: 1}}");
			rtesponseToServlet.put("firstName", dataAdmin.getFirstName());
			rtesponseToServlet.put("lasttName", dataAdmin.getLasttName());
			rtesponseToServlet.put("emailAdress", dataAdmin.getEmailAdress());
			rtesponseToServlet.put("status", "sucess");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtesponseToServlet;
	}
	@Override
	public JSONObject getStatistics(AdmindashboardModel dataAdmin) {
		JSONObject rtesponseToServlet = new JSONObject();
		User details = MongoDBConnection.userColl.findOne("{email:#}", dataAdmin.getFetchMail()).as(User.class);
		try {
			if (details.get_id() == null) {
				rtesponseToServlet.put("status", "fail");
			} else {
				String userid = details.get_id();
				rtesponseToServlet.put("status", "fail");
				UserSession stats = MongoDBConnection.sessionColl.findOne("{_id:#}", userid).as(UserSession.class);
				JSONObject assetsJson = new JSONObject(jsonMapper.writeValueAsString(stats));
				JSONArray loginDetails = assetsJson.getJSONArray("logedInTime");
				rtesponseToServlet.put("loginDetails", loginDetails);
				rtesponseToServlet.put("goalNotfeasibilityCount", stats.getGoalNotfeasibilityCount());
				rtesponseToServlet.put("planNotfeasibilityCount", stats.getPlanNotfeasibilityCount());
				rtesponseToServlet.put("status", "sucess");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtesponseToServlet;
	}
	@Override
	public JSONObject editProfile(AdmindashboardModel dataAdmin) {
		JSONObject responseToRestController = new JSONObject();
		try {
			UserServiceImpl userService = new UserServiceImpl();
			String encodedPassword = userService.encryptPassword(dataAdmin.getEditUsePassword());
			String user_id = dataAdmin.getUser_id();
			MongoDBConnection.userColl
					.update("{'_id': '" + user_id + "'}")
					.upsert()
					.multi()
					.with("{$set:" + " {'name':'" + dataAdmin.getEditFirst() + "'," + "'lname':'" + dataAdmin.getEditLast() + "'," + "'password':'"
							+ encodedPassword + "'}}");
			responseToRestController.put("editFirst", dataAdmin.getEditFirst());
			responseToRestController.put("editLast", dataAdmin.getEditLast());
			responseToRestController.put("status", "sucess");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseToRestController;
	}
	@Override
	public JSONObject existingUser(AdmindashboardModel dataAdmin) {
		JSONObject existingJson = new JSONObject();
		try {
			MongoDBConnection.userColl.update("{'email': '" + dataAdmin.getEmail_id() + "'}").upsert().multi().with("{$set: {'role':'admin'}}");
			existingJson.put("selectUser", dataAdmin.getSelectUser());
			existingJson.put("status", "sucess");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return existingJson;
	}
	public JSONObject editOnloadProfile(AdmindashboardModel dataAdmin) {
		JSONObject responseToRestController = new JSONObject();
		String user_id = dataAdmin.getUser_id();
		try {
			User profiledata = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
			responseToRestController.put("editFirst", profiledata.getName());
			responseToRestController.put("editLast", profiledata.getLname());
			responseToRestController.put("editEmail", profiledata.getEmail());
			responseToRestController.put("status", "sucess");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseToRestController;
	}
	private String executeCommand(String command) {
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
	public JSONObject onloadServerConfiguration(AdmindashboardModel dataAdmin) {
		JSONObject serverjson = new JSONObject();
		try {
			AdmindashboardModel profiledata = MongoDBConnection.systemLogCollection.findOne("{documentType:#}", "Sys_config").as(AdmindashboardModel.class);
			serverjson.put("mongoDBHost", profiledata.getMongoDBHost());
			serverjson.put("mongoDBPort", profiledata.getMongoDBPort());
			serverjson.put("cachePort", profiledata.getCachePort());
			serverjson.put("mongoDBName", profiledata.getMongoDBName());
			serverjson.put("notificationHost", profiledata.getNotificationHost());
			serverjson.put("notificationPort", profiledata.getNotificationHost());
			serverjson.put("restURI", profiledata.getRestURI());
			serverjson.put("userscollection", profiledata.getUserscollection());
			serverjson.put("nIncomeprdataofilecollection", profiledata.getnIncomeprdataofilecollection());
			serverjson.put("counterscollection", profiledata.getCounterscollection());
			serverjson.put("financialPlancollection", profiledata.getFinancialPlancollection());
			serverjson.put("goalsCollection", profiledata.getGoalsCollection());
			serverjson.put("statescollection", profiledata.getStatescollection());
			serverjson.put("cookieKey", profiledata.getCookieKey());
			serverjson.put("sessioncollection", profiledata.getSessioncollection());
			serverjson.put("zillowKey", profiledata.getZillowKey());
			serverjson.put("indexFactorCollection", profiledata.getIndexFactorCollection());
			serverjson.put("olahtekMailUsername", profiledata.getOlahtekMailUsername());
			serverjson.put("olahtekmailpassword", profiledata.getOlahtekmailpassword());
			serverjson.put("cacheHost", profiledata.getCacheHost());
			serverjson.put("cachePort", profiledata.getCachePort());
			serverjson.put("olahHost", profiledata.getOlahHost());
			serverjson.put("status", "sucess");
			return serverjson;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serverjson;
	}

	@Override
	public JSONObject serverConfig(AdmindashboardModel dataAdmin) {
		JSONObject serverjson = new JSONObject();
		MongoCursor<AdmindashboardModel> cursor = MongoDBConnection.systemLogCollection.find().as(AdmindashboardModel.class);
		try {
			JSONArray userJson = new JSONArray(jsonMapper.writeValueAsString(cursor));
			serverjson.put("userdocument", userJson);
			serverjson.put("status", "sucess");
			MongoDBConnection.systemLogCollection
					.update("{'documentType': 'Sys_config'}")
					.upsert()
					.multi()
					.with("{$set:" + " {'mongoDBHost':'" + dataAdmin.getMongoDBHost() + "'," + "'mongoDBPort':'" + dataAdmin.getMongoDBPort() + "',"
							+ "'cachePort':'" + dataAdmin.getCachePort() + "'," + "'mongoDBName':'" + dataAdmin.getMongoDBName() + "',"
							+ "'notificationHost':'" + dataAdmin.getNotificationHost() + "'," + "'notificationPort':'" + dataAdmin.getNotificationPort() + "',"
							+ "'restURI':'" + dataAdmin.getRestURI() + "'," + "'userscollection':'" + dataAdmin.getUserscollection() + "',"
							+ "'nIncomeprdataofilecollection':'" + dataAdmin.getnIncomeprdataofilecollection() + "'," + "'counterscollection':'"
							+ dataAdmin.getCounterscollection() + "'," + "'financialPlancollection':'" + dataAdmin.getFinancialPlancollection() + "',"
							+ "'goalsCollection':'" + dataAdmin.getGoalsCollection() + "'," + "'statescollection':'" + dataAdmin.getStatescollection() + "',"
							+ "'cookieKey':'" + dataAdmin.getCookieKey() + "'," + "'sessioncollection':'" + dataAdmin.getSessioncollection() + "',"
							+ "'zillowKey':'" + dataAdmin.getZillowKey() + "'," + "'indexFactorCollection':'" + dataAdmin.getIndexFactorCollection() + "',"
							+ "'olahtekMailUsername':'" + dataAdmin.getOlahtekMailUsername() + "'," + "'olahtekmailpassword':'"
							+ dataAdmin.getOlahtekmailpassword() + "'," + "'cacheHost':'" + dataAdmin.getCacheHost() + "'," + "'cachePort':'"
							+ dataAdmin.getCachePort() + "'," + "'olahHost':'" + dataAdmin.getOlahHost() + "'}}");

			serverjson.put("mongoDBHost", dataAdmin.getMongoDBHost());
			serverjson.put("mongoDBPort", dataAdmin.getMongoDBPort());
			serverjson.put("cachePort", dataAdmin.getCachePort());
			serverjson.put("mongoDBName", dataAdmin.getMongoDBName());
			serverjson.put("notificationHost", dataAdmin.getNotificationHost());
			serverjson.put("notificationPort", dataAdmin.getNotificationHost());
			serverjson.put("restURI", dataAdmin.getRestURI());
			serverjson.put("userscollection", dataAdmin.getUserscollection());
			serverjson.put("nIncomeprdataofilecollection", dataAdmin.getnIncomeprdataofilecollection());
			serverjson.put("counterscollection", dataAdmin.getCounterscollection());
			serverjson.put("financialPlancollection", dataAdmin.getFinancialPlancollection());
			serverjson.put("goalsCollection", dataAdmin.getGoalsCollection());
			serverjson.put("statescollection", dataAdmin.getStatescollection());
			serverjson.put("cookieKey", dataAdmin.getCookieKey());
			serverjson.put("sessioncollection", dataAdmin.getSessioncollection());
			serverjson.put("zillowKey", dataAdmin.getZillowKey());
			serverjson.put("indexFactorCollection", dataAdmin.getIndexFactorCollection());
			serverjson.put("olahtekMailUsername", dataAdmin.getOlahtekMailUsername());
			serverjson.put("olahtekmailpassword", dataAdmin.getOlahtekmailpassword());
			serverjson.put("cacheHost", dataAdmin.getCacheHost());
			serverjson.put("cachePort", dataAdmin.getCachePort());
			serverjson.put("olahHost", dataAdmin.getOlahHost());
			serverjson.put("status", "sucess");
			return serverjson;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				cursor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return serverjson;
	}

	@Override
	public JSONObject goalTypeHouse(AdmindashboardModel dataAdmin) {

		JSONObject responseToConnection = new JSONObject();
		try {
			String[] goalType = dataAdmin.getGoalTypeEmaiids();
			JSONArray goalTypeArray = new JSONArray(goalType);
			MongoCursor<AdminGoalType> cursor = MongoDBConnection.goalcoll.find("{ goalType: { $in:" + goalTypeArray + "} }").as(AdminGoalType.class);
			Set<String> hSet = new HashSet<String>();
			while (cursor.hasNext()) {
				AdminGoalType fetch = cursor.next();
				hSet.add(fetch.getUser_id());
			}
			JSONArray uniquegoalType = new JSONArray(hSet.toArray());
			MongoCursor<AdminGoalType> emailIds = MongoDBConnection.userColl.find("{ _id: { $in:" + uniquegoalType + "} }").as(AdminGoalType.class);
			JSONArray emails = new JSONArray();
			while (emailIds.hasNext()) {
				AdminGoalType fetch = emailIds.next();
				emails.put(fetch.getEmail());
			}
			responseToConnection.put("status", "sucess");
			responseToConnection.put("emails", emails);
			emailIds.close();
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseToConnection;
	}
	@Override
	public JSONObject deletUser(AdmindashboardModel dataAdmin) {
		JSONObject serverjson = new JSONObject();
		try {
			String goalType = dataAdmin.getUser_id();
			MongoDBConnection.userColl.remove("{_id:#}", goalType);
			MongoDBConnection.goalcoll.remove("{user_id:#}", goalType);
			MongoDBConnection.sessionColl.remove("{_id:#}", goalType);
			MongoDBConnection.finplancol.remove("{usr_id:#}", goalType);
			MongoDBConnection.userSessionColl.remove("{_id:#}", goalType);
			serverjson.put("status", "sucess");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return serverjson;
	}
	
	
	public JSONObject ConstantValuesForStateTax(){
		JSONObject constantvaluesforstateTax = new JSONObject();
		AdmindashboardModel profiledata = MongoDBConnection.systemLogCollection.findOne("{documentType:#}", "Sys_config").as(AdmindashboardModel.class);

		
		return constantvaluesforstateTax;
		
	}
}
