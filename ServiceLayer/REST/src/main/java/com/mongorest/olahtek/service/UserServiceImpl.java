package com.mongorest.olahtek.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEParameterSpec;

import org.jongo.MongoCursor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongorest.olahtek.Register;
import com.mongorest.olahtek.model.Cargoalmodel;
import com.mongorest.olahtek.model.CopyPlanModel;
import com.mongorest.olahtek.model.Counters;
import com.mongorest.olahtek.model.Customizedgoalmodel;
import com.mongorest.olahtek.model.Emergencyfundmodel;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.GoalCollegeEducation;
import com.mongorest.olahtek.model.Housegoalmodel;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.KidGoalModel;
import com.mongorest.olahtek.model.Marriagegoalmodel;
import com.mongorest.olahtek.model.ModelVacation;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.Session;
import com.mongorest.olahtek.model.User;
import com.mongorest.olahtek.model.UserSession;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final int HOURS = 24;
    private static final int MINUTES = 60;
    private static final int MILLISECS = 1000;
    private static final int CHILD_AGE_LIMIT = 18;
    private static final int USER_MAX_AGE = 99;
    private static final int MONTHS = 12;
    private static final int TEN = 10;
    private static final int PERCENT = 100;
    private static final int DEFAULT_RETIRMENT_AGE = 70;
    private static final int FIRST_BEND_POINT = 856;
    private static final int SECOND_BEND_POINT = 5157;
    private static final int IRA_LIMIT = 18000;
    private static final int THREE = 3;
    private static final int THIRTY = 30;
    private static final int TWENTY_FIVE_THOUSAND = 25000;
    private static final byte XA3 = (byte) 0xa3;
    private static final byte X21 = (byte) 0x21;
    private static final byte X24 = (byte) 0x24;
    private static final byte X2C = (byte) 0x2c;
    private static final byte XF2 = (byte) 0xf2;
    private static final byte XD2 = (byte) 0xd2;
    private static final byte X3E = (byte) 0x3e;
    private static final byte X19 = (byte) 0x19;
    private static final int TWENTY = 20;

    private static List<User> users;
    private final DateFormat dateFormatType = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private final Date dateCurrent = new Date();
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();

    @Override
    @JsonCreator
    // ----------------------------maintaining
    // session--------------------------------------

    public JSONObject checkSession(final User user, final String status) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final Date date = new Date();
        final JSONObject jobject = new JSONObject();
        final String sessionID = user.getSessionID();
        try {
            final User login = MongoDBConnection.userColl.findOne("{email:#}", user.getEmail()).as(User.class);

            jobject.put("status", "fail");
            if (status.equals("LogincheckCoockies")) {
                String lastVisitedPage = user.getLastVisitedPage();
                if (login.getRole().equals("admin")) {
                    lastVisitedPage = "Admindashboard.jsp";
                }
                final BasicDBObject insertSessionData = new BasicDBObject("_id", sessionID)
                        .append("user_id", user.getUser_id()).append("loggedInTime", dateFormat.format(date))
                        .append("lastVisitedPage", lastVisitedPage).append("role", login.getRole());
                MongoDBConnection.sessionColl.insert(insertSessionData);
                jobject.put("status", "success");
                jobject.put("sessionID", sessionID);
                jobject.put("user_id", user.getUser_id());
                jobject.put("role", login.getRole());
                jobject.put("lastVisitedPage", user.getLastVisitedPage());
                JSONArray logedInTimeArray = new JSONArray();
                final UserSession userSession = MongoDBConnection.userSessionColl.findOne("{_id:#}", user.getUser_id())
                        .as(UserSession.class);
                if (userSession != null) {
                    final JSONObject sessionJson = new JSONObject(jsonMapper.writeValueAsString(userSession));
                    logedInTimeArray = sessionJson.getJSONArray("logedInTime");
                    final JSONArray visitedPage = logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1)
                            .getJSONArray("visitedPages");
                    boolean flag = true;
                    for (int i = 0; i < visitedPage.length(); i++) {
                        if (visitedPage.get(i).equals(user.getLastVisitedPage())) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        if (user.getLastVisitedPage() != null) {
                            logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1).getJSONArray("visitedPages")
                            .put(user.getLastVisitedPage());
                            MongoDBConnection.userSessionColl.update("{'_id':'" + login.get_id() + "'}").upsert()
                            .multi().with("{$set: {'logedInTime':" + logedInTimeArray + "}}");
                        }

                    }
                }
                // ----------------------------------------------------------------------------------------------------------------
                return jobject;
            } else {
                MongoDBConnection.sessionColl.ensureIndex("{ 'loggedInTime': 1 }, { expireAfterSeconds: 86400} ");
                final Session sessionDetails = MongoDBConnection.sessionColl.findOne("{_id:#}", sessionID)
                        .as(Session.class);
                if (sessionDetails != null) {
                    if (sessionDetails.get_id().equals(sessionID)) {
                        String lastVisitedPages = user.getLastVisitedPage();
                        lastVisitedPages= lastVisitedPages.replace("#", "");
                        if (sessionDetails.getRole().equals("admin")) {
                            lastVisitedPages = "Admindashboard.jsp";
                        }
                        jobject.put("status", "success");
                        jobject.put("sessionID", sessionDetails.get_id());
                        jobject.put("user_id", sessionDetails.getUser_id());
                        jobject.put("role", sessionDetails.getRole());
                        jobject.put("lastVisitedPage", sessionDetails.getLastVisitedPage());
                        MongoDBConnection.sessionColl.update("{'_id': '" + sessionID + "'}").upsert().multi()
                        .with("{$set: {'lastVisitedPage':'" + lastVisitedPages + "'}}");
                        // -----------------------insert visited page in
                        JSONArray logedInTimeArray = new JSONArray();
                        //////////// //System.out.println("hello...bala"+MongoDBConnection.userSessionColl);
                        final UserSession userSession = MongoDBConnection.userSessionColl
                                .findOne("{_id:#}", sessionDetails.getUser_id()).as(UserSession.class);
                        if (userSession != null) {
                            final JSONObject sessionJson = new JSONObject(jsonMapper.writeValueAsString(userSession));
                            logedInTimeArray = sessionJson.getJSONArray("logedInTime");
                            final JSONArray visitedPage = logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1)
                                    .getJSONArray("visitedPages");
                            boolean flag = true;
                            for (int i = 0; i < visitedPage.length(); i++) {
                                if (visitedPage.get(i).equals(user.getLastVisitedPage())) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                if (user.getLastVisitedPage() != null) {
                                    logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1)
                                    .getJSONArray("visitedPages").put(user.getLastVisitedPage());
                                    MongoDBConnection.userSessionColl
                                    .update("{'_id':'" + sessionDetails.getUser_id() + "'}").upsert().multi()
                                    .with("{$set: {'logedInTime':" + logedInTimeArray + "}}");
                                }

                            }
                        }
                        // ----------------------------------------------------------------------------------------------------------------
                        return jobject;
                    }
                    return jobject;
                } else {

                    jobject.put("massage", "sessionExpired");
                    return jobject;
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject;
        }
    }

    // -----------------------------Login------------------------------------------
    @Override
    public JSONObject findByEmail(final String email, final String password) {
        final JSONObject jobject = new JSONObject();

        JSONArray logedInTimeArray = new JSONArray();
        try {
            jobject.put("status", "fail");
            // MongoCollection counterCol =
            // jongo.getCollection(counterCollection);
            // MongoCollection testcol = jongo.getCollection(usersCollection);
            final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
            final User login = MongoDBConnection.userColl.findOne("{email:#}", email).as(User.class);
            if (login != null) {
                final String pwd = login.getPassword();
                if (pwd.equals(encryptPassword(password))) {
                    jobject.put("status", "success");
                    if (login.getCompletedFlag() == 1) {
                        jobject.put("completedStatus", 1);
                    } else {
                        jobject.put("completedStatus", 0);
                    }
                    jobject.put("user_id", login.get_id());
                    final UserSession userSession = MongoDBConnection.sessionColl.findOne("{_id:#}", login.get_id())
                            .as(UserSession.class);
                    if (userSession != null) {
                        final JSONObject sessionJson = new JSONObject(jsonMapper.writeValueAsString(userSession));
                        logedInTimeArray = sessionJson.getJSONArray("logedInTime");
                        final JSONObject sessionFill = new JSONObject();
                        final JSONArray emptyArray = new JSONArray();
                        emptyArray.put("http://localhost:8080/Servetls/index.jsp");
                        sessionFill.put("loginTimeStamp", dateFormatType.format(dateCurrent));
                        sessionFill.put("visitedPages", emptyArray);
                        logedInTimeArray.put(sessionFill);
                        MongoDBConnection.sessionColl.update("{'_id':'" + login.get_id() + "'}").upsert().multi()
                        .with("{$set: {'logedInTime':" + logedInTimeArray + "}}");
                        MongoDBConnection.counterColl.update("{'hitCount':" + count.getHitCount() + "}")
                        .with("{$inc: {hitCount: 1}}");
                        final String loginTime = logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1)
                                .getString("loginTimeStamp");
                        String logOut = null;
                        if (logedInTimeArray.getJSONObject(logedInTimeArray.length() - 2).getString("logoutTimeStamp")
                                .equals("null")) {
                            final long diff = compareDate(loginTime, logedInTimeArray
                                    .getJSONObject(logedInTimeArray.length() - 1).getString("loginTimeStamp"));
                            if (diff < 1) {
                                logOut = dateFormatType.format(dateCurrent);
                                logedInTimeArray.getJSONObject(logedInTimeArray.length() - 2).put("logoutTimeStamp",
                                        logOut);
                            } else {
                                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                final Calendar calendar = Calendar.getInstance();
                                calendar.setTime(simpleDateFormat.parse(loginTime));
                                calendar.add(Calendar.DATE, 1);
                                logOut = simpleDateFormat.format(calendar.getTime());
                                logedInTimeArray.getJSONObject(logedInTimeArray.length() - 2).put("logoutTimeStamp",
                                        logOut);
                            }
                            MongoDBConnection.sessionColl.update("{'_id':'" + login.get_id() + "'}").upsert().multi()
                            .with("{$set: {'logedInTime':" + logedInTimeArray + "}}");
                        }
                        /// end here
                    } else {
                        final JSONObject sessionFill = new JSONObject();
                        final JSONArray emptyArray = new JSONArray();
                        emptyArray.put("http://localhost:8080/Servetls/index.jsp");
                        sessionFill.put("loginTimeStamp", dateFormatType.format(dateCurrent));
                        sessionFill.put("visitedPages", emptyArray);
                        logedInTimeArray.put(sessionFill);
                        MongoDBConnection.sessionColl.update("{'_id':'" + login.get_id() + "'}").upsert().multi()
                        .with("{$set: {'logedInTime':" + logedInTimeArray + ",'goalNotfeasibilityCount':" + 0
                                + ",'planNotfeasibilityCount':" + 0 + "}}");
                        MongoDBConnection.counterColl.update("{'hitCount':" + count.getHitCount() + "}")
                        .with("{$inc: {hitCount: 1}}");
                    }
                    // --------------------------------------------------------------------------------------------------------
                    return jobject;
                } else {
                    jobject.put("status", "incorrect_password");
                    jobject.put("completedStatus", 0);
                    return jobject;
                }
            } else {
                jobject.put("status", "user_is_not_registered");
                jobject.put("completedStatus", 0);
                return jobject;
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject;
        }
    }

    // -----------------------------logOut------------------------------------
    @Override
    public JSONObject logOutUser(final User user) {
        final JSONObject jobject = new JSONObject();

        try {
            JSONArray logedInTimeArray = new JSONArray();
            final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            final Date date = new Date();
            jobject.put("status", "fail");
            final String sessionID = user.getSessionID();
            final Session sessionDetails = MongoDBConnection.sessionColl.findOne("{_id:#}", sessionID)
                    .as(Session.class);
            if (sessionDetails != (null)) {
                final MongoCursor<FinPlan> cursor = MongoDBConnection.finplancol
                        .find("{usr_id:#}", sessionDetails.getUser_id()).as(FinPlan.class);

                final JSONArray plan = new JSONArray();
                while (cursor.hasNext()) {
                    final FinPlan fetch = cursor.next();
                    plan.put(fetch.getPlan_name());
                }
                final int plancount = 0;
                final int goalCount = 0;

                MongoDBConnection.userSessionColl.update("{'_id':'" + sessionDetails.getUser_id() + "'}").upsert()
                .multi().with("{$set: {'goalNotfeasibilityCount':'" + goalCount
                        + "','planNotfeasibilityCount':'" + plancount + "'}}");

                if (sessionDetails.get_id().equals(sessionID)) {
                    MongoDBConnection.sessionColl.remove("{_id:#}", sessionID);
                    jobject.put("status", "success");
                    final UserSession userSession = MongoDBConnection.userSessionColl
                            .findOne("{_id:#}", user.getUser_id()).as(UserSession.class);
                    if (userSession != null) {
                        final JSONObject sessionJson = new JSONObject(jsonMapper.writeValueAsString(userSession));
                        logedInTimeArray = sessionJson.getJSONArray("logedInTime");

                        final String loginTime = logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1)
                                .getString("loginTimeStamp");
                        long diff = compareDate(loginTime, dateFormat.format(date));
                        String logOut = null;
                        if (diff < 1) {
                            logOut = dateFormat.format(date);
                        } else {
                            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            final Calendar calendar = Calendar.getInstance();
                            calendar.setTime(simpleDateFormat.parse(loginTime));
                            calendar.add(Calendar.DATE, 1);

                            logOut = simpleDateFormat.format(calendar.getTime());
                        }
                        if (logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1).getString("logoutTimeStamp")
                                .equals("null")) {
                            diff = compareDate(loginTime, logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1)
                                    .getString("loginTimeStamp"));
                            if (diff < 1) {
                                logOut = dateFormat.format(date);
                                logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1).put("logoutTimeStamp",
                                        logOut);
                                MongoDBConnection.sessionColl.update("{'_id':'" + user.getUser_id() + "'}").upsert()
                                .multi().with("{$set: {'logedInTime':" + logedInTimeArray + "}}");
                            } else {
                                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                final Calendar calendar = Calendar.getInstance();
                                calendar.setTime(simpleDateFormat.parse(loginTime));
                                calendar.add(Calendar.DATE, 1);
                                logOut = simpleDateFormat.format(calendar.getTime());
                                logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1).put("logoutTimeStamp",
                                        logOut);
                                MongoDBConnection.sessionColl.update("{'_id':'" + user.getUser_id() + "'}").upsert()
                                .multi().with("{$set: {'logedInTime':" + logedInTimeArray + "}}");
                            }
                        } else if (logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1)
                                .get("logoutTimeStamp") != null) {
                            MongoDBConnection.userSessionColl.update("{'_id':'" + user.getUser_id() + "'}").upsert()
                            .multi().with("{$set: {'logedInTime':" + logedInTimeArray + "}}");
                        }

                    } else {
                        final UserSession userSessions = MongoDBConnection.sessionColl
                                .findOne("{_id:#}", sessionDetails.getUser_id()).as(UserSession.class);
                        if (userSessions != null) {
                            final JSONObject sessionJson = new JSONObject(jsonMapper.writeValueAsString(userSessions));
                            logedInTimeArray = sessionJson.getJSONArray("logedInTime");

                            final String loginTime = logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1)
                                    .getString("loginTimeStamp");
                            long diff = compareDate(loginTime, dateFormat.format(date));
                            String logOut = null;
                            if (diff < 1) {
                                logOut = dateFormat.format(date);
                            } else {
                                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                final Calendar calendar = Calendar.getInstance();
                                calendar.setTime(simpleDateFormat.parse(loginTime));
                                calendar.add(Calendar.DATE, 1);
                                logOut = simpleDateFormat.format(calendar.getTime());
                            }
                            if (logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1)
                                    .getString("logoutTimeStamp").equals("null")) {
                                diff = compareDate(
                                        logedInTimeArray.getJSONObject(logedInTimeArray.length() - 2)
                                        .getString("loginTimeStamp"),
                                        logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1)
                                        .getString("loginTimeStamp"));
                                if (diff < 1) {
                                    logOut = dateFormat.format(date);
                                    logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1).put("logoutTimeStamp",
                                            logOut);
                                    MongoDBConnection.userSessionColl.update("{'_id':'" + user.getUser_id() + "'}")
                                    .upsert().multi().with("{$set: {'logedInTime':" + logedInTimeArray + "}}");
                                } else {
                                    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                                            "yyyy/MM/dd HH:mm:ss");
                                    final Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(simpleDateFormat.parse(loginTime));
                                    calendar.add(Calendar.DATE, 1); // number
                                    logOut = simpleDateFormat.format(calendar.getTime());
                                    logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1).put("logoutTimeStamp",
                                            logOut);
                                    MongoDBConnection.userSessionColl.update("{'_id':'" + user.getUser_id() + "'}")
                                    .upsert().multi().with("{$set: {'logedInTime':" + logedInTimeArray + "}}");
                                }
                            } else if (logedInTimeArray.getJSONObject(logedInTimeArray.length() - 1)
                                    .get("logoutTimeStamp") != null) {
                                MongoDBConnection.userSessionColl
                                .update("{'_id':'" + sessionDetails.getUser_id() + "'}").upsert().multi()
                                .with("{$set: {'logedInTime':" + logedInTimeArray + "}}");
                            }
                        }
                    }
                    cursor.close();
                    return jobject;
                } else {
                    cursor.close();
                    return jobject;
                }
            } else {
                return jobject;
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject;
        }
    }

    public static long compareDate(final String loginTime, final String currentDate) {
        final String dateStart = loginTime;
        final String dateStop = currentDate;
        final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        long diffDays = 0;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
            final long diff = d2.getTime() - d1.getTime();
            diffDays = diff / (HOURS * MINUTES * MINUTES * MILLISECS);
           // System.out.print(diffDays + " days, ");
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return diffDays;
    }

    // -----------------user register-----------------------
    @Override
    public JSONObject saveUser(final User user) {
        final JSONObject systemLog = new JSONObject();
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int year1 = cal.get(Calendar.YEAR);
        final String formType = user.getFormType();
        final JSONObject jobject = new JSONObject();
        final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
        final String userid = "usr" + count.getUser_id();
        try {
            jobject.put("status", "fail");
            if (formType.equals("Userform")) {
                try {
                    final String encodedPassword = encryptPassword(user.getPassword());
                    final BasicDBObject doc = new BasicDBObject("_id", userid).append("role", "user")
                            .append("name", user.getName()).append("lname", user.getLastName())
                            .append("email", user.getEmail()).append("password", encodedPassword)
                            .append("createdTs", dateFormatType.format(dateCurrent)).append("completedFlag", 0);
                    MongoDBConnection.userColl.insert(doc);
                    MongoDBConnection.counterColl.update("{'user_id':" + count.getUser_id() + "}")
                    .with("{$inc: {user_id: 1}}");
                    jobject.put("userid", userid);
                    final String incomeId = "incomeProfile" + count.getIncome_id();
                    MongoDBConnection.counterColl.update("{'income_id':" + count.getIncome_id() + "}")
                    .with("{$inc: {income_id: 1}}");
                    final BasicDBObject insertIncome = new BasicDBObject("_id", incomeId)
                            .append("modifiedTs", dateFormatType.format(dateCurrent))
                            .append("createdTs", dateFormatType.format(dateCurrent))
                            .append("profile_name", "constant_income").append("user_id", userid)
                            .append("editExpenseFlag",0).append("housingExpense", 0).append("nonHousingExpense", 0);
                    MongoDBConnection.incomeProfileCol.insert(insertIncome);
                    jobject.put("status", "success");
                    systemLog.put("type", "success");
                    systemLog.put("message", "Registered Successfully!!");
                    systemLog.put("userName", user.getName());
                    systemLog.put("user_id", userid);
                    systemLog.put("createdTs", dateFormatType.format(dateCurrent));
                    JSONArray logedInTimeArray = new JSONArray();
                    final UserSession userSession = MongoDBConnection.userSessionColl.findOne("{_id:#}", userid)
                            .as(UserSession.class);
                    if (userSession != null) {
                        final JSONObject sessionJson = new JSONObject(jsonMapper.writeValueAsString(userSession));
                        logedInTimeArray = sessionJson.getJSONArray("logedInTime");
                        final JSONObject sessionFill = new JSONObject();
                        final JSONArray emptyArray = new JSONArray();
                        emptyArray.put("http://localhost:8080/Servetls/index.jsp");
                        sessionFill.put("loginTimeStamp", dateFormatType.format(dateCurrent));
                        sessionFill.put("visitedPages", emptyArray);
                        logedInTimeArray.put(sessionFill);
                        MongoDBConnection.userSessionColl.update("{'_id':'" + userid + "'}").upsert().multi()
                        .with("{$set: {'logedInTime':" + logedInTimeArray + "}}");
                        MongoDBConnection.counterColl.update("{'hitCount':" + count.getHitCount() + "}")
                        .with("{$inc: {hitCount: 1}}");
                    } else {
                        final JSONObject sessionFill = new JSONObject();
                        final JSONArray emptyArray = new JSONArray();
                        emptyArray.put("http://localhost:8080/Servetls/index.jsp");
                        sessionFill.put("loginTimeStamp", dateFormatType.format(dateCurrent));
                        sessionFill.put("visitedPages", emptyArray);
                        logedInTimeArray.put(sessionFill);
                        MongoDBConnection.userSessionColl.update("{'_id':'" + userid + "'}").upsert().multi()
                        .with("{$set: {'logedInTime':" + logedInTimeArray + ",'goalNotfeasibilityCount':" + 0
                                + ",'planNotfeasibilityCount':" + 0 + "}}");
                        MongoDBConnection.counterColl.update("{'hitCount':" + count.getHitCount() + "}")
                        .with("{$inc: {hitCount: 1}}");
                    }
                } catch (final Exception e) {
                    systemLog.put("type", "error");
                    systemLog.put("message", "Registration Failed!!");
                    systemLog.put("userName", user.getName());
                    systemLog.put("user_id", userid);
                    systemLog.put("createdTs", dateFormatType.format(dateCurrent));
                    jobject.put("status", "fail");
                    e.printStackTrace();
                } finally {
                    final BasicDBObject systemLogBasicObject = new BasicDBObject("user_id",
                            systemLog.getString("user_id")).append("userName", systemLog.getString("userName"))
                            .append("type", systemLog.getString("type"))
                            .append("message", systemLog.getString("message"))
                            .append("createdTs", dateFormatType.format(dateCurrent));
                    MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);
                }
                return jobject;
            } else if (formType.equals("personalDetails")) {
                int noOfDependent = 0;
                final int birthYear = cal.get(Calendar.YEAR) - user.getAge();
                String fillingStatus = "Single";
                final int childcount = user.getChildCount();
                final int noOfDependentfortax = user.getDependants() + childcount;
                noOfDependent = user.getDependants();
                if (childcount > 0) {
                    fillingStatus = "Head of Household";
                }
                if (user.getMarital_status().equals("No")) {
                    if (user.getFilingStatus().equals("YES")) {
                        fillingStatus = "Head of Household";
                    }
                    if (user.getDependants() >= 0) {
                        fillingStatus = "Head of Household";
                    }
                }
                if (user.getMarital_status().equals("No")) {
                    if (user.getFilingStatus().equals("NO")) {
                        fillingStatus = "Single";
                    }
                } else {
                    if (user.getDependants() >= 0) {
                        fillingStatus = "Married Filing Jointly";
                    }
                }
                if (childcount > 0) {
                    final JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(user));
                    final JSONArray childsJsonArray = userJson.getJSONArray("childs");
                    for (int i = 0; i < childsJsonArray.length(); i++) {
                        if (childsJsonArray.getJSONObject(i).getInt("age") < CHILD_AGE_LIMIT) {
                            noOfDependent++;
                        }
                        if (childsJsonArray.getJSONObject(i).getString("flag").equals("Yes")
                                && childsJsonArray.getJSONObject(i).getInt("age") >= CHILD_AGE_LIMIT) {
                            noOfDependent++;
                        }
                    }
                    MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                    .with("{$set: {'childs':" + childsJsonArray + "}}");
                }
                MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                .with("{$set: {'modifiedTs':'" + dateFormatType.format(dateCurrent) + "','city':'"
                        + user.getCity() + "','state':'" + user.getState() + "','county':'" + user.getCounty()
                        + "','age':'" + user.getAge() + "','birthYear':'" + birthYear + "','marital_status':'"
                        + user.getMarital_status() + "','filingStatus':'" + fillingStatus + "','childCount':'"
                        + childcount + "','dependants':" + noOfDependent + ",'noOfDependentfortax':"
                        + noOfDependentfortax + ",'userDependent':" + user.getDependants() + "}}");
                if (user.getMarital_status().equals("Yes")) {
                    final int spouseBirthYear = cal.get(Calendar.YEAR) - user.getSpouseAge();
                    MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                    .with("{$set: {'spouseName':'" + user.getSpouseName() + "','spouseBirthYear':'"
                            + spouseBirthYear + "','SpouseAge':'" + user.getSpouseAge() + "',"
                            + "'childCount':'" + user.getChildCount()
                            + "','filingStatus':'Married Filing Jointly','dependants':" + noOfDependent + "}}");
                    //System.out.println("noOfDependent====" + noOfDependent);
                    return jobject.put("status", "success");
                }
                return jobject.put("status", "success");
            } else if (formType.equals("calculateKidCost")) {
                //System.out.println("before the accessing of the kid details");

                final User details1 = MongoDBConnection.userColl.findOne("{_id:#}", user.getUser_id()).as(User.class);
                //System.out.println("before the accessing of the kid details");
                int ageUser = (details1.getAge());
                final JSONArray incomeCombined = new JSONArray();
                final int childcount = details1.getChildCount();

                while (ageUser <= USER_MAX_AGE) {
                    final JSONObject yearAndValue = new JSONObject();
                    yearAndValue.put("year", year);
                    yearAndValue.put("value", user.getSum() * MONTHS);
                    incomeCombined.put(yearAndValue);
                    year++;
                    ageUser++;
                }

                final KidGoalImpl obj1 = new KidGoalImpl();
                double sum = 0;
                //System.out.println("before the accessing of the kid details");
                final JSONArray kidCostArray = new JSONArray();
                if (childcount > 0) {

                    final JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(details1));
                    final JSONArray childsJsonArray = userJson.getJSONArray("childs");
                    //System.out.println("childsJsonArray==aparna===" + childsJsonArray);
                    int k = 0;

                    for (int i = 0; i < childsJsonArray.length(); i++) {
                        final JSONObject kidObj = new JSONObject();
                        //System.out.println("child age" + childsJsonArray.getJSONObject(i).getInt("age"));
                        ++k;
                        final double temp = obj1.calculateCost(details1.getCounty(), details1.getState(),
                                details1.getMarital_status(), k, incomeCombined,
                                childsJsonArray.getJSONObject(i).getInt("age"),childsJsonArray.length(),incomeCombined.getJSONObject(0).getInt("year"));
                        kidObj.put("age", childsJsonArray.getJSONObject(i).getInt("age"));
                        kidObj.put("cost", temp);
                        kidCostArray.put(kidObj);
                        sum = sum + temp;
                    }
                }
                jobject.put("cost", sum);
                jobject.put("costArray", kidCostArray);

                return jobject.put("status", "success");

            } else if (formType.equals("IncomeAndExpenses")) {
                final long kidCostCalculated = user.getKidCostCalculated();
                final String kidCostCalculatedArray = user.getKidCostCalculatedArray();
                final double kidcostFactor = user.getKidcostFactor();
                System.out.println("kidcostFactor"+kidcostFactor);
                final User details = MongoDBConnection.userColl.findOne("{_id:#}", user.getUser_id()).as(User.class);
                System.out.println("user_id...."+userid);
                MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                .with("{$set: {'modifiedTs':'" + dateFormatType.format(dateCurrent)
                + "','userBeforeTaxIncome':'" + user.getUserBeforeTaxIncome() + "','monthlyExpenses':'"
                + user.getMonthlyExpenses() + "','houseStatus':'" + user.getHouseStatus()
                + "','otherExpenses':'" + user.getOtherExpenses() + "','kidCostCalculated':'"
                + kidCostCalculated + "','kidCostCalculatedArray':'" + kidCostCalculatedArray + "','kidcostFactor':'" + kidcostFactor + "'}}");
                if (user.getRentalExpense() != 0) {
                    MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                    .with("{$set: {'rentalExpenses':'" + user.getRentalExpense() + "'}}");
                }
                /*-----------------calculating income profile collection _id-------------------------------*/
                int userAge = (details.getAge());
                final JSONArray income = new JSONArray();
                final JSONArray userExpense = new JSONArray();

                while (userAge <= USER_MAX_AGE) {

                    final JSONObject yearAndValue = new JSONObject();
                    final JSONObject expense = new JSONObject();
                    yearAndValue.put("year", year);
                    yearAndValue.put("value", user.getUserBeforeTaxIncome() * MONTHS + "");
                    expense.put("year", year);
                    expense.put("housingExpense", user.getRentalExpense() * MONTHS);
                    expense.put("nonHousingExpense", user.getMonthlyExpenses() * MONTHS);
                    expense.put("afterMarriageExpense", 0);
                    expense.put("mortgageExpense", 0);
                    expense.put("kidExpense", 0);
                    expense.put("totalExpense", user.getRentalExpense() * MONTHS + user.getMonthlyExpenses() * MONTHS);
                    userExpense.put(expense);

                    income.put(yearAndValue);
                    year++;
                    userAge++;
                }
                MongoDBConnection.incomeProfileCol
                .update("{'user_id': '" + user.getUser_id() + "','profile_name':'constant_income'}").upsert()
                .with("{$set: {'userExpense':" + userExpense + "}}");
                if (details.getMarital_status() != null && details.getMarital_status().equals("Yes")) {
                    MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                    .with("{$set: {'spouseBeforeTaxIncome':'" + user.getSpouseBeforeTaxIncome() + "'}}");
                    int spouseAge = (details.getSpouseAge());
                    final JSONArray spouseIncome = new JSONArray();
                    JSONArray combinedIncome = new JSONArray();
                    while (spouseAge <= USER_MAX_AGE) {
                        final JSONObject yearAndValue = new JSONObject();
                        yearAndValue.put("year", year1);
                        yearAndValue.put("value", user.getSpouseBeforeTaxIncome() * MONTHS + "");
                        yearAndValue.put("retirement_income", 0);
                        spouseIncome.put(yearAndValue);
                        year1++;
                        spouseAge++;
                    }
                    combinedIncome = calCombinedIncome.calCombinedIncome(income, spouseIncome);

                    if (income.length() < combinedIncome.length()) {
                        for (int i = income.length(); i < combinedIncome.length(); i++) {
                            final JSONObject yearAndValue = new JSONObject();
                            yearAndValue.put("year", combinedIncome.getJSONObject(i).getInt("year"));
                            yearAndValue.put("value", 0);
                            yearAndValue.put("retirement_income", 0);
                            income.put(yearAndValue);
                        }
                    }
                    if (spouseIncome.length() < combinedIncome.length()) {
                        for (int i = spouseIncome.length(); i < combinedIncome.length(); i++) {
                            final JSONObject yearAndValue = new JSONObject();
                            yearAndValue.put("year", combinedIncome.getJSONObject(i).getInt("year"));
                            yearAndValue.put("value", 0);
                            yearAndValue.put("retirement_income", 0);
                            spouseIncome.put(yearAndValue);
                        }
                    }

                    MongoDBConnection.incomeProfileCol
                    .update("{'user_id': '" + user.getUser_id() + "','profile_name':'constant_income'}")
                    .with("{$set: {'spouse_income':" + spouseIncome + ",'user_income':" + income
                            + ",'combined_income':" + combinedIncome + "}}");
                    return jobject.put("status", "success");
                } else {
                    MongoDBConnection.incomeProfileCol
                    .update("{'user_id': '" + user.getUser_id() + "','profile_name':'constant_income'}")
                    .upsert().with("{$set: {'user_income':" + income + "}}");
                    return jobject.put("status", "success");
                }
            } else if (formType.equals("Assets")) {
                final User available = MongoDBConnection.userColl.findOne("{_id:#}", user.getUser_id()).as(User.class);
                final String kidCostCalculatedArray = available.getKidCostCalculatedArray();
                MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                .with("{$set: {'modifiedTs':'" + dateFormatType.format(dateCurrent) + "','cash':"
                        + user.getCash() + ",'taxableInvestments':" + user.getTaxableInvestments()
                        + ",'completedFlag':1,'nonTaxableInvestments':" + "'" + user.getNonTaxableInvestments()
                        + "'" + ",'houseValue':" + user.getHouseValue() + ",'realEstate':"
                        + user.getRealEstate() + ",'remainingMortgage':" + user.getRemainingMortgage()
                        + ",'remainingYearsMortgage':" + user.getRemainingYearsMortgage()
                        + ",'remainingMortgageInterestRate':" + user.getRemainingMortgageInterestRate()
                        + ",'houseAppreciationRate':" + user.getHouseAppreciationRate()
                        + ",'devidendTaxbleInvestmentRate':2," + "'gainNonTaxableInvestmentRate':5,"
                        + "'devidendNonTaxbleInvestmentRate':2," + "'gainTaxableInvestmentRate':5,"
                        + "'creditScore':0," + "'savingInterestRate':0,'user401k':" + user.getUser401k()
                        + ",'userIRA':" + user.getUserIRA() + ",'userRothIra':" + user.getUserRothIra()
                        + ",'user529':" + user.getUser529() + ",'spouse401k':" + user.getSpouse401k()
                        + ",'spouseIRA':" + user.getSpouseIRA() + ",'spouseRothIra':" + user.getSpouseRothIra()
                        + ",'spousePlan529':" + user.getSpousePlan529() + "}}");

                year = cal.get(Calendar.YEAR);
                int userAge = available.getAge();
                JSONArray userExpense = new JSONArray();
                JSONArray assests = new JSONArray();
                JSONArray tax = new JSONArray();
                int spouseAge = available.getSpouseAge();
                while (userAge <= USER_MAX_AGE || (spouseAge != 0 && spouseAge <= USER_MAX_AGE)) {

                    final JSONObject expense = new JSONObject();
                    final JSONObject assetObj = new JSONObject();
                    final JSONObject taxObj = new JSONObject();
                    assetObj.put("year", year);
                    taxObj.put("year", year);
                    taxObj.put("federalTax", 0);
                    taxObj.put("fICAMedicareTax", 0);
                    taxObj.put("fICASocialSecurityTax", 0);
                    taxObj.put("userSSTax", 0);
                    taxObj.put("stateTax", 0);
                    taxObj.put("spouseSSTax", 0);
                    assetObj.put("nontaxable_investment_amount", 0);
                    assetObj.put("taxable_investment_amount", 0);
                    assetObj.put("savings", 0);
                    assetObj.put("user529Plan", 0);
                    assetObj.put("spouse529Plan", 0);

                    assests.put(assetObj);
                    tax.put(taxObj);
                    expense.put("year", year);
                    expense.put("housingExpense", available.getRentalExpense() * MONTHS);
                    expense.put("nonHousingExpense", available.getMonthlyExpenses() * MONTHS);
                    expense.put("registerHousingExpense", available.getRentalExpense() * MONTHS);
                    expense.put("registerNonHousingExpense", available.getMonthlyExpenses() * MONTHS);
                    expense.put("afterMarriageExpense", 0);
                    expense.put("mortgageExpense", 0);
                    expense.put("kidExpense", 0);
                    expense.put("totalExpense",
                            available.getRentalExpense() * MONTHS + available.getMonthlyExpenses() * MONTHS);
                    userExpense.put(expense);
                    year++;
                    userAge++;
                    if (!(spouseAge == 0)) {
                        spouseAge++;
                    }
                }
                MongoDBConnection.incomeProfileCol
                .update("{'user_id': '" + user.getUser_id() + "','profile_name':'constant_income'}").upsert()
                .with("{$set: {'userExpense':" + userExpense + "}}");
                JSONArray userIncomeArray = new JSONArray();
                JSONArray incomeArray = new JSONArray();
                final IncomeProfile incomeProf = MongoDBConnection.incomeProfileCol
                        .findOne("{user_id:#,profile_name:#}", user.getUser_id(), "constant_income")
                        .as(IncomeProfile.class);
                final JSONObject incomeJson = new JSONObject(jsonMapper.writeValueAsString(incomeProf));
                userIncomeArray = incomeJson.getJSONArray("user_income");
                JSONArray spouseIncomeAraay = new JSONArray();
                if (available.getMarital_status().equals("Yes")) {
                    incomeArray = incomeJson.getJSONArray("combined_income");
                    spouseIncomeAraay = incomeJson.getJSONArray("spouse_income");
                } else {
                    incomeArray = userIncomeArray;
                }

                final JSONArray oldfillingExemtion = new JSONArray();
                final String[] out = available.getCreatedTs().split("/");
                final int registrationYear = Integer.parseInt(out[0]);
                int noOfExcemtion = 1 + available.getChildCount();
                year = incomeArray.getJSONObject(0).getInt("year");
                if (available.getMarital_status().equals("Yes") || !incomeJson.isNull("spouse_income")) {
                    noOfExcemtion = noOfExcemtion + available.getNoOfDependentfortax() - available.getChildCount() + 1;

                    if (available.getFilingStatus().equals("Head of Household")) {
                        noOfExcemtion = noOfExcemtion + available.getNoOfDependentfortax() - available.getChildCount();
                    } else if (available.getFilingStatus().equals("Married Filing Separately")) {
                        noOfExcemtion = noOfExcemtion - 1;

                    } else if (available.getFilingStatus().equals("Qualified Widow")) {
                        noOfExcemtion = noOfExcemtion - 1;
                    }
                } else if (available.getFilingStatus().equals("Head of Household")) {
                    noOfExcemtion = noOfExcemtion + available.getNoOfDependentfortax() - available.getChildCount();
                }
                JSONArray childArray = new JSONArray();
                final JSONObject userDetailsFromDB = new JSONObject(jsonMapper.writeValueAsString(available));
                for (int i = 0; i < incomeArray.length(); i++) {
                    int noOfExcemtion1 = noOfExcemtion;
                    int kids = 0;
                    if (available.getChildCount() != 0) {
                        childArray = userDetailsFromDB.getJSONArray("childs");
                        for (int k = 0; k < available.getChildCount(); k++) {
                            if (year - (registrationYear
                                    - childArray.getJSONObject(k).getInt("age")) > CHILD_AGE_LIMIT) {
                                kids++;
                                if ((childArray.getJSONObject(k).getString("flag").equals("Yes") && year
                                        - (registrationYear - childArray.getJSONObject(k).getInt("age")) > HOURS)
                                        || ((childArray.getJSONObject(k).getString("flag").equals("No"))
                                                && year - (registrationYear - childArray.getJSONObject(k)
                                                        .getInt("age")) > CHILD_AGE_LIMIT)) {
                                    noOfExcemtion1 = noOfExcemtion1 - 1;
                                }
                            }
                        }
                    }
                    year++;
                    final JSONObject obj = new JSONObject();

                    final double kidExpenseReduction = reduceChildExpense(
                            incomeArray.getJSONObject(i).getDouble("value"),
                            userExpense.getJSONObject(i).getDouble("nonHousingExpense"), kids,
                            new JSONArray(kidCostCalculatedArray),available.getKidcostFactor());
                    userExpense.getJSONObject(i).put("nonHousingExpense",
                            userExpense.getJSONObject(i).getDouble("nonHousingExpense") - kidExpenseReduction);
                    userExpense.getJSONObject(i).put("totalExpense",
                            userExpense.getJSONObject(i).getDouble("nonHousingExpense")
                            + userExpense.getJSONObject(i).getDouble("housingExpense"));
                    if (userExpense.getJSONObject(i).getDouble("nonHousingExpense") < 0) {
                        userExpense.getJSONObject(i).put("nonHousingExpense", 0);
                    }

                    obj.put("noOfExcemtion", noOfExcemtion1);
                    obj.put("fillingStatus", available.getFilingStatus());
                    if (noOfExcemtion1 <= 1) {
                        obj.put("fillingStatus", "Single");
                    }
                    obj.put("year", incomeArray.getJSONObject(i).getInt("year"));
                    oldfillingExemtion.put(obj);
                }
                if (available.getHouseStatus().equals("Own")) {
                    final int remainingYears = (int) (user.getRemainingYearsMortgage());
                    for (int i = remainingYears; i < userExpense.length(); i++) {
                        userExpense.getJSONObject(i).put("totalExpense",
                                userExpense.getJSONObject(i).getDouble("totalExpense")
                                - userExpense.getJSONObject(i).getDouble("nonHousingExpense"));
                        userExpense.getJSONObject(i).put("nonHousingExpense",
                                userExpense.getJSONObject(i).getDouble("nonHousingExpense")
                                - userExpense.getJSONObject(i).getDouble("nonHousingExpense") * TEN / PERCENT);
                        if (userExpense.getJSONObject(i).getDouble("nonHousingExpense") < 0) {
                            userExpense.getJSONObject(i).put("nonHousingExpense", 0);
                        }
                        userExpense.getJSONObject(i).put("totalExpense",
                                userExpense.getJSONObject(i).getDouble("totalExpense")
                                + userExpense.getJSONObject(i).getDouble("nonHousingExpense"));
                    }
                }
                // ----------------------------------------------------------------------------------------------
                int spouseStartYear = 0;
                final int userStartYear = incomeArray.getJSONObject(0).getInt("year") - available.getAge()
                        + DEFAULT_RETIRMENT_AGE;
                if (available.getMarital_status().equals("Yes")) {
                    spouseStartYear = incomeArray.getJSONObject(0).getInt("year") - available.getSpouseAge()
                            + DEFAULT_RETIRMENT_AGE;
                }
                final RetirementGoalServiceImpl goalservice = new RetirementGoalServiceImpl();
                final double userAIME = goalservice.findUserAIME(DEFAULT_RETIRMENT_AGE, userIncomeArray, userStartYear);
                final int userFRA = goalservice.findUserFRA(DEFAULT_RETIRMENT_AGE, available.getBirthYear());
                final JSONObject userJsonSSB = goalservice.calculateUserSSB(user.getUserBeforeTaxIncome(),
                        available.getMarital_status(), userFRA, 0, FIRST_BEND_POINT, SECOND_BEND_POINT, userAIME, 0);
                if (available.getMarital_status().equals("Yes")) {
                    spouseAge = available.getSpouseAge();
                    final JSONObject spouseJson = new JSONObject();
                    spouseJson.put("firetBendPoint", FIRST_BEND_POINT);
                    spouseJson.put("spouseRetirementAge", DEFAULT_RETIRMENT_AGE);
                    spouseJson.put("userRetirementAge", DEFAULT_RETIRMENT_AGE);
                    spouseJson.put("useFfra", userFRA);
                    spouseJson.put("spouseFfra",
                            goalservice.findUserFRA(DEFAULT_RETIRMENT_AGE, user.getSpouseBirthYear()));
                    spouseJson.put("userAime", userAIME);
                    spouseJson.put("spouseAime",
                            goalservice.findUserAIME(DEFAULT_RETIRMENT_AGE, spouseIncomeAraay, spouseStartYear));
                    spouseJson.put("secondBendPoint", SECOND_BEND_POINT);

                    final JSONObject allIncomes = goalservice.insertRetirementIncomeNew(
                            ((Double) (userJsonSSB.get("User70"))), spouseJson, user.getUser_id(), userIncomeArray,
                            spouseIncomeAraay, userStartYear, spouseStartYear, spouseStartYear);
                    userIncomeArray = allIncomes.getJSONArray("user_income");
                    spouseIncomeAraay = allIncomes.getJSONArray("spouse_income");
                    incomeArray = allIncomes.getJSONArray("combined_income");
                } else {
                    userIncomeArray = goalservice.insertUserRetirementIncomeNew(userIncomeArray, user.getUser_id(),
                            null, (Double) (userJsonSSB.get("User70")), userStartYear);
                    incomeArray = userIncomeArray;
                }
                userExpense = CalculationEngine.retirementExpenseArray(userExpense, spouseStartYear, userStartYear,
                        available.getMarital_status(), 0);
                JSONObject result = new JSONObject();
                final JSONArray limitsIra = new JSONArray();
                double spouseIncomeValue = 0;
                userAge = available.getAge();
                spouseAge--;
                year = cal.get(Calendar.YEAR);
                for (int i = 0; i < incomeArray.length(); i++) {
                    JSONObject limitobj = new JSONObject();
                    limitobj.put("year", year);
                    limitobj.put("saving", 0);
                    limitobj.put("taxable", 0);
                    limitobj.put("spouse401k", 0);
                    limitobj.put("userIRA", 0);
                    limitobj.put("spouseIRA", 0);
                    limitobj.put("user401k", 0);
                    limitobj.put("userRouthIRA", 0);
                    limitobj.put("spouseRouthIRA", 0);
                    limitobj.put("user529Plan", 0);
                    limitobj.put("spouse529Plan", 0);
                    limitobj.put("collegeGoalTaxable", 0);
                    if (spouseIncomeAraay.length() != 0) {
                        spouseIncomeValue = spouseIncomeAraay.getJSONObject(i).getDouble("value");
                        spouseAge++;
                    }
                    limitobj = CalculationEngine.limiteAfterRetirement(IRA_LIMIT, IRA_LIMIT, 0, 0, 0, 0, userAge,
                            userIncomeArray.getJSONObject(i).getDouble("value"), spouseIncomeValue,
                            oldfillingExemtion.getJSONObject(i).getString("fillingStatus"), spouseAge, limitobj,
                            incomeArray.getJSONObject(i).getDouble("value"), i, null);
                    limitsIra.put(limitobj);
                    year++;
                    userAge++;
                }
                result = CalculationEngine.sweepingOfMoney(userIncomeArray, null, incomeArray, spouseIncomeAraay,
                        userExpense, limitsIra, available.getMarital_status(), assests, tax, available.get_id(),
                        oldfillingExemtion, available.getAge(), available.getSpouseAge(), 0, false, null, null, null,
                        null, null, null, null, null, false);
                if (result.getString("status").equals("success")) {
                    assests = result.getJSONArray("assets");
                    tax = result.getJSONArray("tax");
                    final JSONArray userPlot = new JSONArray();
                    final JSONArray spousePlot = new JSONArray();
                    JSONObject tempPlot = new JSONObject();
                    tempPlot.put("year", userIncomeArray.getJSONObject(0).getInt("year"));
                    tempPlot.put("userIncome", userIncomeArray.getJSONObject(0).getDouble("value"));
                    userPlot.put(tempPlot);
                    boolean userFlagRet = true;
                    boolean spouseFlagRet = true;
                    for (int i = 0; i < userIncomeArray.length(); i++) {
                        tempPlot = new JSONObject();
                        if (userIncomeArray.getJSONObject(i).getDouble("retirement_income") > 0) {
                            userFlagRet = false;
                            tempPlot.put("year", userIncomeArray.getJSONObject(i).getInt("year"));
                            tempPlot.put("userIncome", userIncomeArray.getJSONObject(i).getDouble("value"));
                            userPlot.put(tempPlot);
                            break;
                        }
                    }
                    if (userFlagRet) {
                        final int userRetAge = DEFAULT_RETIRMENT_AGE + available.getBirthYear();
                        for (int i = 0; i < userIncomeArray.length(); i++) {
                            tempPlot = new JSONObject();
                            if (userIncomeArray.getJSONObject(i).getInt("year") == userRetAge) {
                                tempPlot.put("year", userIncomeArray.getJSONObject(i).getInt("year"));
                                tempPlot.put("userIncome", userIncomeArray.getJSONObject(i).getDouble("value"));
                                userPlot.put(tempPlot);
                                break;
                            }
                        }
                    }
                    tempPlot = new JSONObject();
                    tempPlot.put("year", userIncomeArray.getJSONObject(userIncomeArray.length() - 1).getInt("year"));
                    tempPlot.put("userIncome",
                            userIncomeArray.getJSONObject(userIncomeArray.length() - 1).getString("value"));
                    userPlot.put(tempPlot);
                    if (available.getMarital_status().equals("Yes")) {
                        tempPlot = new JSONObject();
                        tempPlot.put("year", spouseIncomeAraay.getJSONObject(0).getInt("year"));
                        tempPlot.put("spouseIncome", spouseIncomeAraay.getJSONObject(0).getDouble("value"));
                        spousePlot.put(tempPlot);
                        for (int i = 0; i < spouseIncomeAraay.length(); i++) {
                            tempPlot = new JSONObject();
                            if (spouseIncomeAraay.getJSONObject(i).getDouble("retirement_income") > 0) {
                                spouseFlagRet = false;
                                tempPlot.put("year", spouseIncomeAraay.getJSONObject(i).getInt("year"));
                                tempPlot.put("spouseIncome", spouseIncomeAraay.getJSONObject(i).getDouble("value"));
                                spousePlot.put(tempPlot);
                                break;
                            }
                        }
                        if (spouseFlagRet) {
                            final int spouseRetAge = DEFAULT_RETIRMENT_AGE + available.getSpouseBirthYear();
                            for (int i = 0; i < spouseIncomeAraay.length(); i++) {
                                tempPlot = new JSONObject();
                                if (spouseIncomeAraay.getJSONObject(i).getInt("year") == spouseRetAge) {
                                    tempPlot.put("year", spouseIncomeAraay.getJSONObject(i).getInt("year"));
                                    tempPlot.put("spouseIncome", spouseIncomeAraay.getJSONObject(i).getDouble("value"));
                                    spousePlot.put(tempPlot);
                                    break;
                                }
                            }
                        }
                        tempPlot = new JSONObject();
                        tempPlot.put("year",
                                spouseIncomeAraay.getJSONObject(spouseIncomeAraay.length() - 1).getInt("year"));
                        tempPlot.put("spouseIncome",
                                spouseIncomeAraay.getJSONObject(spouseIncomeAraay.length() - 1).getString("value"));
                        spousePlot.put(tempPlot);
                    }

                    MongoDBConnection.incomeProfileCol
                    .update("{'user_id': '" + user.getUser_id() + "','profile_name':'constant_income'}")
                    .upsert().multi().with("{$set: {'tax':" + tax + ",'userExpense':" + userExpense + "}}");
                    MongoDBConnection.incomeProfileCol
                    .update("{'user_id': '" + user.getUser_id() + "','profile_name':'constant_income'}")
                    .upsert().multi()
                    .with("{$set: {'assests':" + assests + ",'user_income':" + userIncomeArray
                            + ",'spouse_income':" + spouseIncomeAraay + ",'combined_income':" + incomeArray
                            + ",'limits':" + limitsIra + ",'userPlot': " + userPlot + ",'spousePlot': "
                            + spousePlot + " }}");
                    return jobject.put("status", "success");
                } else {
                    return jobject.put("status", "fail");
                }
            } else {
                return jobject;
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject;
        }
    }

    // subroutine to reduce he kid expense in with kid profile

    public double reduceChildExpense(final double income, final double nonhousing, final int kidCount,
            final JSONArray totalKidCostArr, final double kidCostFactor) {
        double expenseDeduction = 0;
        try {

       System.out.println("nonhousing..." + nonhousing + "total kid cost..." + totalKidCostArr + "kidcount..." + kidCount + "array length" + kidCostFactor);
if(nonhousing<=0) {
    expenseDeduction=0;
}
else {
       if(kidCostFactor>0) {
                if (kidCount > 0 && kidCount < totalKidCostArr.length()) {
                    double sum = 0;
                    for (int i = 0; i < kidCount; i++) {
                        sum = sum + totalKidCostArr.getJSONObject(i).getDouble("cost");
                    }
                 sum =sum *kidCostFactor;
                 expenseDeduction=sum;
                } else if (kidCount == totalKidCostArr.length()) {
                    double sum = 0;
                    for (int i = 0; i < totalKidCostArr.length(); i++) {
                        sum = sum + totalKidCostArr.getJSONObject(i).getDouble("cost");
                    }
                    sum =sum *kidCostFactor;
                    expenseDeduction=sum;

                    System.out.println("expenseDeduction..."+expenseDeduction);
                }


            }
            else {

                if (kidCount > 0 && kidCount < totalKidCostArr.length()) {
                    double sum = 0;
                    for (int i = 0; i < kidCount; i++) {
                        sum = sum + totalKidCostArr.getJSONObject(i).getDouble("cost");
                    }
                System.out.println("sum " +sum);

                    expenseDeduction=sum;


                } else if (kidCount == totalKidCostArr.length()) {
                    double sum = 0;
                    for (int i = 0; i < totalKidCostArr.length(); i++) {
                        sum = sum + totalKidCostArr.getJSONObject(i).getDouble("cost");
                    }
                    expenseDeduction=sum;
                }

            }
    }


        } catch (final Exception e) {
            e.printStackTrace();
        }
        System.out.println("expenseDeduction.."+expenseDeduction);
        return expenseDeduction;
    }

    // ----------------------------Fetching details for User
    @Override
    public JSONObject getUserDetails(final String user) {
        final JSONObject jobject = new JSONObject();
        final Calendar cal = Calendar.getInstance();
        final int year = cal.get(Calendar.YEAR);
        try {
            final User details = MongoDBConnection.userColl.findOne("{_id:#}", user).as(User.class);
      /*      final MongoCursor<IncomeProfile> cursor = MongoDBConnection.incomeProfileCol.find("{user_id:#}", user)
                    .as(IncomeProfile.class);
            final JSONArray incomeProfiles = new JSONArray();
            while (cursor.hasNext()) {
                final IncomeProfile fetch = cursor.next();
                incomeProfiles.put(fetch.getProfile_name());
            }
            cursor.close();
            jobject.put("incomeProfiles", incomeProfiles);
            final IncomeProfile incomeProf = MongoDBConnection.incomeProfileCol
                    .findOne("{user_id:#,profile_name:#}", user, "constant_income").as(IncomeProfile.class);
            final JSONObject incomeJson = new JSONObject(jsonMapper.writeValueAsString(incomeProf));
            final JSONArray userIncomeArray = incomeJson.getJSONArray("user_income");
            JSONArray spouseIncomeArray = null;
            jobject.put("spouseIncome", 0);
            if (details.getMarital_status().equals("Yes")) {
                if (!incomeJson.isNull("spouse_income")) {
                    spouseIncomeArray = incomeJson.getJSONArray("spouse_income");
                }
            }
            for (int i = 0; i < userIncomeArray.length(); i++) {
                if (userIncomeArray.getJSONObject(i).getInt("year") == year) {
                    if (details.getMarital_status().equals("Yes")) {
                        jobject.put("spouseIncome", spouseIncomeArray.getJSONObject(i).getDouble("value"));
                    }
                    jobject.put("userIncome", userIncomeArray.getJSONObject(i).getDouble("value"));
                    break;
                }
            }
*/
            long count = MongoDBConnection.finplancol.count("{user_id:#}", user);
            //////// //System.out.println("Details.getFilingStatus()==="+Details.getFilingStatus());
            jobject.put("status", "pass");
            jobject.put("name", details.getName());
            jobject.put("finPlanCount", count);
            jobject.put("lastName", details.getLastName());
            jobject.put("age", details.getAge());
            jobject.put("city", details.getCity());
            jobject.put("county", details.getCounty());
            jobject.put("address1", details.getAddress1());
            jobject.put("address2", details.getAddress2());
            jobject.put("collegeinfo", details.getCollege_info());
            jobject.put("state", details.getState());
            jobject.put("email", details.getEmail());
            jobject.put("dob", details.getDob());
            jobject.put("houseinfo", details.getHouseStatus());
            jobject.put("kidscount", details.getChildCount());
            jobject.put("rothRetirement", details.getRothRetirement());
            jobject.put("Taxdeferred", details.getTaxdeferred());
            jobject.put("fiveTnp", details.getFiveTnp());
            jobject.put("maritalStatus", details.getMarital_status());
            jobject.put("filingStatus", details.getFilingStatus());
            jobject.put("dependants", details.getUserDependent());
            jobject.put("spouseDob", details.getSpouseDob());
            jobject.put("spouseName", details.getSpouseName());
            jobject.put("spouseLastName", details.getSpouseLastName());
            jobject.put("spouseAge", details.getSpouseAge());
            jobject.put("userBeforeTaxIncome", details.getUserBeforeTaxIncome());
            jobject.put("spouseBeforeTaxIncome", details.getSpouseBeforeTaxIncome());
            jobject.put("rentalExpenses", details.getRentalExpenses());
            jobject.put("monthlyExpenses", details.getMonthlyExpenses());
            jobject.put("otherExpenses", details.getOtherExpenses());
            jobject.put("cash", details.getCash());
            jobject.put("taxableInvestments", details.getTaxableInvestments());
            jobject.put("nonTaxableInvestments", details.getNonTaxableInvestments());
            jobject.put("realEstate", details.getRealEstate());
            jobject.put("houseValue", details.getHouseValue());
            jobject.put("remainingMortgage", details.getRemainingMortgage());
            jobject.put("remainingYearsMortgage", details.getRemainingYearsMortgage());
            jobject.put("realEstate", details.getRealEstate());
            jobject.put("whatIsYourCurrentMortgageRate", details.getRemainingMortgageInterestRate());
            jobject.put("houseAppreciationRate", details.getHouseAppreciationRate());
            jobject.put("user401", details.getUser401k());
            jobject.put("kidCostCalculated", details.getKidCostCalculated());
            jobject.put("userIra", details.getUserIRA());
            jobject.put("userRothira", details.getUserRothIra());
            jobject.put("user559", details.getUser529());
            jobject.put("spouse401", details.getSpouse401k());
            jobject.put("spouseIra", details.getSpouseIRA());
            jobject.put("spouseRothira", details.getSpouseRothIra());
            jobject.put("spouse529", details.getSpousePlan529());

            final JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(details));

            if (details.getChildCount() > 0) {
                if (!userJson.isNull("childs")) {
                    jobject.put("childs", userJson.getJSONArray("childs"));
                }
            }
            return jobject;
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject;
        }
    }

    // --------------------------------Edit USer
    @Override
    public JSONObject EditUserProfile(final User user) {

        final User details = MongoDBConnection.userColl.findOne("{_id:#}", user.getUser_id()).as(User.class);
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        final int currYear = cal.get(Calendar.YEAR);
        final String formType = user.getFormType();
        final JSONObject jobject = new JSONObject();
        final JSONArray spousePlot = new JSONArray();
        MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
        .with("{$set: {'rothRetirement':" + user.getRothRetirement() + ",'taxdeferred':" + user.getTaxdeferred()
        + ",'fiveTnp':" + user.getFiveTnp() + "}}");
        try {
            final IncomeProfile incomeProf = MongoDBConnection.incomeProfileCol
                    .findOne("{user_id:#,profile_name:#}", user.getUser_id(), "constant_income")
                    .as(IncomeProfile.class);
            final JSONObject incomeJson = new JSONObject(jsonMapper.writeValueAsString(incomeProf));
            int oldSpouseBirthYear = 0;
            int oldUserBirthYear = 0;
            String oldState;
            String oldCity;
            jobject.put("status", "fail");
            if (formType.equals("editBasicDetails")) {
                final int length = USER_MAX_AGE - user.getAge() + 1;
                final User userDetailsTemp = MongoDBConnection.userColl.findOne("{_id:#}", user.getUser_id())
                        .as(User.class);
                JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(userDetailsTemp));
                final JSONObject userJson1 = new JSONObject(jsonMapper.writeValueAsString(user));
                JSONArray childsJsonArray = null;
                final String userId = user.getUser_id();
                final IncomeProfile incomeProfTemp = MongoDBConnection.incomeProfileCol
                        .findOne("{user_id:#,profile_name:#}", userId, "constant_income").as(IncomeProfile.class);
                final JSONObject incomeJsonTemp = new JSONObject(jsonMapper.writeValueAsString(incomeProfTemp));
                final JSONArray assetsIncome = incomeJsonTemp.getJSONArray("assests");
                JSONArray userExpense = incomeJsonTemp.getJSONArray("userExpense");
                JSONArray userIncome = incomeJsonTemp.getJSONArray("user_income");
                JSONArray combinedIncomeChild = new JSONArray();
                JSONArray spouseIncome = new JSONArray();
                JSONArray tax = incomeJsonTemp.getJSONArray("tax");
                final JSONArray fillingExemtion = new JSONArray();
                final JSONArray impLimits = incomeJsonTemp.getJSONArray("limits");
                if (details.getMarital_status().equals("Yes")) {
                    spouseIncome = incomeJsonTemp.getJSONArray("spouse_income");
                    combinedIncomeChild = incomeJsonTemp.getJSONArray("combined_income");
                } else {
                    combinedIncomeChild = userIncome;
                }
                final JSONArray kidCostArray = new JSONArray();
                if (user.getChildCount() > 0) {
                    childsJsonArray = userJson1.getJSONArray("childs");
                    MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi().with(
                            "{$set: {'childCount':" + user.getChildCount() + ",'childs':" + childsJsonArray + "}}");

                    final KidGoalImpl obj1 = new KidGoalImpl();
                    double sum = 0;
                    int k = 0;
                    for (int i = 0; i < childsJsonArray.length(); i++) {
                        final JSONObject kidObj = new JSONObject();
                        //System.out.println("child age" + childsJsonArray.getJSONObject(i).getInt("age"));
                        ++k;
                        final double temp = obj1.calculateCost(user.getCounty(), user.getState(),
                                user.getMarital_status(), k, combinedIncomeChild,
                                childsJsonArray.getJSONObject(i).getInt("age"),childsJsonArray.length(),combinedIncomeChild.getJSONObject(0).getInt("year"));
                        kidObj.put("age", childsJsonArray.getJSONObject(i).getInt("age"));
                        kidObj.put("cost", temp);
                        kidCostArray.put(kidObj);
                        sum = sum + temp;
                    }
                }
                final int userExpenseLength = userExpense.length();
                final int userIncomeLength = userIncome.length();
                final int spouseIncomeLength = spouseIncome.length();
                final int limitsLength = impLimits.length();
                final int assetsIncomeLength = assetsIncome.length();
                final int taxLength = tax.length();
                int noOfDependent = 0;
                noOfDependent = user.getDependants();
                for (int i = 0; i < length; i++) {
                    JSONObject tempjson = new JSONObject();
                    if (i >= assetsIncomeLength) {
                        tempjson.put("nontaxable_investment_amount", 0);
                        tempjson.put("userRothIRA", 0);
                        tempjson.put("spouse529Plan", 0);
                        tempjson.put("savings", 0);
                        tempjson.put("user529Plan", 0);
                        tempjson.put("spouseIRA", 0);
                        tempjson.put("plan529", 0);
                        tempjson.put("year", (assetsIncome.getJSONObject(assetsIncomeLength - 1).getInt("year"))
                                + (i - assetsIncomeLength) + 1);
                        tempjson.put("userIRA", 0);
                        tempjson.put("user401k", 0);
                        tempjson.put("spouse401k", 0);
                        tempjson.put("taxable_investment_amount", 0);
                        tempjson.put("spouseRothIRA", 0);
                        assetsIncome.put(tempjson);
                        tempjson = new JSONObject();
                    }
                    if (i >= userExpenseLength) {
                        tempjson.put("totalExpense",
                                userExpense.getJSONObject(userExpenseLength - 1).getDouble("totalExpense"));
                        tempjson.put("registerNonHousingExpense", userExpense.getJSONObject(userExpenseLength - 1)
                                .getDouble("registerNonHousingExpense"));
                        tempjson.put("housingExpense",
                                userExpense.getJSONObject(userExpenseLength - 1).getDouble("housingExpense"));
                        tempjson.put("nonHousingExpense",
                                userExpense.getJSONObject(userExpenseLength - 1).getDouble("nonHousingExpense"));
                        tempjson.put("mortgageExpense",
                                userExpense.getJSONObject(userExpenseLength - 1).getDouble("mortgageExpense"));
                        tempjson.put("afterMarriageExpense",
                                userExpense.getJSONObject(userExpenseLength - 1).getDouble("afterMarriageExpense"));
                        tempjson.put("registerHousingExpense",
                                userExpense.getJSONObject(userExpenseLength - 1).getDouble("registerHousingExpense"));
                        tempjson.put("kidExpense",
                                userExpense.getJSONObject(userExpenseLength - 1).getDouble("kidExpense"));
                        tempjson.put("year", (userExpense.getJSONObject(userExpenseLength - 1).getInt("year"))
                                + (i - userExpenseLength) + 1);
                        userExpense.put(tempjson);
                        tempjson = new JSONObject();
                    }
                    if (i >= userIncomeLength) {
                        tempjson.put("value", userIncome.getJSONObject(userIncomeLength - 1).getDouble("value"));
                        tempjson.put("retirement_income",
                                userIncome.getJSONObject(userIncomeLength - 1).getDouble("retirement_income"));
                        tempjson.put("year", userIncome.getJSONObject(userIncomeLength - 1).getInt("year")
                                + (i - userIncomeLength) + 1);
                        tempjson.put("incomeBeforeRetirement",
                                userIncome.getJSONObject(userIncomeLength - 1).getDouble("incomeBeforeRetirement"));
                        userIncome.put(tempjson);
                        tempjson = new JSONObject();
                    }
                    if (i >= spouseIncomeLength && details.getMarital_status().equals("Yes")) {
                        tempjson.put("value", spouseIncome.getJSONObject(spouseIncomeLength - 1).getDouble("value"));
                        tempjson.put("retirement_income",
                                spouseIncome.getJSONObject(spouseIncomeLength - 1).getDouble("retirement_income"));
                        tempjson.put("year", spouseIncome.getJSONObject(spouseIncomeLength - 1).getInt("year")
                                + (i - spouseIncomeLength) + 1);
                        tempjson.put("incomeBeforeRetirement",
                                spouseIncome.getJSONObject(spouseIncomeLength - 1).getDouble("incomeBeforeRetirement"));

                        spouseIncome.put(tempjson);
                        tempjson = new JSONObject();
                    }
                    if (i >= taxLength) {
                        tempjson.put("userSSTax", 0);
                        tempjson.put("fICAMedicareTax", 0);
                        tempjson.put("stateTax", 0);
                        tempjson.put("year", tax.getJSONObject(taxLength - 1).getInt("year") + (i - taxLength) + 1);
                        tempjson.put("fICASocialSecurityTax", 0);
                        tempjson.put("spouseSSTax", 0);
                        tempjson.put("federalTax", 0);
                        tax.put(tempjson);
                        tempjson = new JSONObject();
                    }
                    if (i >= limitsLength) {
                        tempjson.put("collegeGoalTaxable", 0);
                        tempjson.put("spouseRouthIRA", 0);
                        tempjson.put("spouseIRA", 0);
                        tempjson.put("taxable", 0);
                        tempjson.put("saving", 0);
                        tempjson.put("spouse529Plan", 0);
                        tempjson.put("userIRA", 0);
                        tempjson.put("year",
                                impLimits.getJSONObject(limitsLength - 1).getInt("year") + (i - limitsLength) + 1);
                        tempjson.put("userRouthIRA", 0);
                        tempjson.put("user401k", 0);
                        tempjson.put("spouse401k", 0);
                        tempjson.put("user529Plan", 0);
                        impLimits.put(tempjson);
                        tempjson = new JSONObject();
                    }

                }
                final int noOfDependentfortax = user.getDependants() + user.getChildCount();
                final JSONArray oldfillingExemtion = new JSONArray();
                String[] out = userDetailsTemp.getCreatedTs().split("/");
                int registrationYear = Integer.parseInt(out[0]);
                int noOfExcemtion = 1 + userDetailsTemp.getChildCount();
                year = incomeJsonTemp.getJSONArray("user_income").getJSONObject(0).getInt("year");
                if (userDetailsTemp.getMarital_status().equals("Yes")) {
                    noOfExcemtion = noOfExcemtion + noOfDependentfortax - user.getChildCount() + 1;
                    if (userDetailsTemp.getFilingStatus().equals("Head of Household")) {
                        noOfExcemtion = noOfExcemtion + noOfDependentfortax - user.getChildCount();
                    } else if (userDetailsTemp.getFilingStatus().equals("Married Filing Separately")) {
                        noOfExcemtion = noOfExcemtion - 1;
                    } else if (userDetailsTemp.getFilingStatus().equals("Qualified Widow")) {
                        noOfExcemtion = noOfExcemtion - 1;
                    }
                } else if (user.getFilingStatus().equals("Head of Household")) {
                    noOfExcemtion = noOfExcemtion + noOfDependentfortax - user.getChildCount();
                } else if (user.getFilingStatus().equals("Single")) {
                    noOfExcemtion = 1;
                }
                JSONArray childArray = new JSONArray();
                final JSONObject userDetailsFromDB = new JSONObject(jsonMapper.writeValueAsString(userDetailsTemp));
                for (int i = 0; i < assetsIncome.length(); i++) {
                    int noOfExcemtion1 = noOfExcemtion;
                    if (userDetailsTemp.getChildCount() != 0) {
                        childArray = userDetailsFromDB.getJSONArray("childs");
                        for (int k = 0; k < userDetailsTemp.getChildCount(); k++) {
                            if (year - (registrationYear
                                    - childArray.getJSONObject(k).getInt("age")) > CHILD_AGE_LIMIT) {
                                if ((childArray.getJSONObject(k).getString("flag").equals("Yes") && year
                                        - (registrationYear - childArray.getJSONObject(k).getInt("age")) > HOURS)
                                        || (!childArray.getJSONObject(k).getString("flag").equals("Yes"))) {
                                    noOfExcemtion1 = noOfExcemtion1 - 1;
                                }
                            }
                        }
                    }
                    year++;
                    final JSONObject obj = new JSONObject();
                    obj.put("fillingStatus", user.getFilingStatus());
                    obj.put("noOfExcemtion", noOfExcemtion1);
                    if (noOfExcemtion1 <= 1) {
                        obj.put("fillingStatus", "Single");
                    }
                    obj.put("year", assetsIncome.getJSONObject(i).getInt("year"));
                    oldfillingExemtion.put(obj);
                }
                JSONArray combinedIncome = new JSONArray();
                String maritalStatus;
                int userStartYear = ((currYear - user.getAge()) + DEFAULT_RETIRMENT_AGE);
                oldUserBirthYear = userDetailsTemp.getBirthYear();
                oldState = userDetailsTemp.getState();
                oldCity = userDetailsTemp.getCity();
                MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                .with("{$set: {'birthYear':" + (currYear - user.getAge()) + ",'city':'" + user.getCity()
                + "','state':'" + user.getState() + "'}}");
                final int currUserBirthYear = currYear - user.getAge();
                int currSpouseBirthYear = 0;
                int spouseStartYear = 0;
                for (int i = 0; i < userIncome.length(); i++) {
                    if (userIncome.getJSONObject(i).getDouble("incomeBeforeRetirement") > 0) {
                        userIncome.getJSONObject(i).put("value",
                                userIncome.getJSONObject(i).getDouble("incomeBeforeRetirement"));
                    }
                }
                if (user.getMarital_status().equals("Yes")) {
                    oldSpouseBirthYear = userDetailsTemp.getSpouseBirthYear();
                    MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                    .with("{$set: {'spouseBirthYear':" + (currYear - user.getSpouseAge()) + ",'birthYear':"
                            + (currYear - user.getAge()) + "}}");
                    maritalStatus = "Yes";
                    currSpouseBirthYear = currYear - user.getSpouseAge();
                    spouseStartYear = ((currYear - user.getSpouseAge()) + DEFAULT_RETIRMENT_AGE);
                    if (userDetailsTemp.getMarital_status().equals("No")) {
                        for (int i = 0; i < userIncome.length(); i++) {
                            final JSONObject incomeJson1 = new JSONObject();
                            incomeJson1.put("year", (userIncome.getJSONObject(i).getInt("year")));
                            incomeJson1.put("value", 0);
                            incomeJson1.put("retirement_income", 0);
                            incomeJson1.put("incomeBeforeRetirement", 0);
                            spouseIncome.put(incomeJson1);
                        }
                    }
                    for (int i = 0; i < spouseIncome.length(); i++) {
                        if (spouseIncome.getJSONObject(i).getDouble("incomeBeforeRetirement") > 0) {
                            spouseIncome.getJSONObject(i).put("value",
                                    spouseIncome.getJSONObject(i).getDouble("incomeBeforeRetirement"));
                        }
                    }
                    final RetirementGoalServiceImpl goalservice = new RetirementGoalServiceImpl();
                    final double userAIME = goalservice.findUserAIME(DEFAULT_RETIRMENT_AGE, userIncome, userStartYear);
                    final int userFRA = goalservice.findUserFRA(DEFAULT_RETIRMENT_AGE, (currYear - user.getAge()));
                    final JSONObject userJsonSSB = goalservice.calculateUserSSB(0, "Yes", userFRA, 0, FIRST_BEND_POINT,
                            SECOND_BEND_POINT, userAIME, 0);
                    final JSONObject spouseJson = new JSONObject();
                    spouseJson.put("firetBendPoint", FIRST_BEND_POINT);
                    spouseJson.put("spouseRetirementAge", DEFAULT_RETIRMENT_AGE);
                    spouseJson.put("userRetirementAge", DEFAULT_RETIRMENT_AGE);
                    spouseJson.put("useFfra", userFRA);
                    spouseJson.put("spouseFfra",
                            goalservice.findUserFRA(DEFAULT_RETIRMENT_AGE, details.getSpouseBirthYear()));
                    spouseJson.put("userAime", userAIME);
                    spouseJson.put("spouseAime",
                            goalservice.findUserAIME(DEFAULT_RETIRMENT_AGE, spouseIncome, spouseStartYear));
                    spouseJson.put("secondBendPoint", SECOND_BEND_POINT);
                    final JSONObject allIncomes = goalservice.insertRetirementIncomeNew(
                            ((Double) (userJsonSSB.get("User" + DEFAULT_RETIRMENT_AGE))), spouseJson, userId,
                            userIncome, spouseIncome, userStartYear, spouseStartYear, spouseStartYear);
                    userIncome = allIncomes.getJSONArray("user_income");
                    spouseIncome = allIncomes.getJSONArray("spouse_income");
                    combinedIncome = allIncomes.getJSONArray("combined_income");

                } else {
                    oldSpouseBirthYear = userDetailsTemp.getSpouseBirthYear();
                    final RetirementGoalServiceImpl goalservice = new RetirementGoalServiceImpl();
                    final double userAIME = goalservice.findUserAIME(DEFAULT_RETIRMENT_AGE, userIncome, userStartYear);
                    final int userFRA = goalservice.findUserFRA(DEFAULT_RETIRMENT_AGE, (currYear - user.getAge()));
                    final JSONObject userJsonSSB = goalservice.calculateUserSSB(0, "Yes", userFRA, 0, FIRST_BEND_POINT,
                            SECOND_BEND_POINT, userAIME, 0);
                    final JSONObject allIncomes = goalservice.insertRetirementIncomeNewSingle(
                            ((Double) (userJsonSSB.get("User" + DEFAULT_RETIRMENT_AGE))), null, userId, userIncome,
                            null, userStartYear, 0);
                    userIncome = allIncomes.getJSONArray("user_income");
                    combinedIncome = userIncome;
                    maritalStatus = "No";
                }
                if (assetsIncome.length() > combinedIncome.length()) {
                    final JSONObject obj = new JSONObject();
                    obj.put("year", assetsIncome.getJSONObject(assetsIncome.length() - 1).getInt("year"));
                    obj.put("value", 0);
                    obj.put("retirement_income", 0);
                    obj.put("incomeBeforeRetirement", 0);
                    combinedIncome.put(obj);
                }
                out = userDetailsTemp.getCreatedTs().split("/");
                registrationYear = Integer.parseInt(out[0]);

                noOfExcemtion = 1 + user.getChildCount();
                year = incomeJsonTemp.getJSONArray("user_income").getJSONObject(0).getInt("year");
                if (user.getMarital_status().equals("Yes")) {
                    noOfExcemtion = noOfExcemtion + noOfDependentfortax - user.getChildCount() + 1;
                    if (user.getFilingStatus().equals("Head of Household")) {
                        noOfExcemtion = noOfExcemtion + noOfDependentfortax - user.getChildCount();
                    } else if (user.getFilingStatus().equals("Married Filing Separately")) {
                        noOfExcemtion = noOfExcemtion - 1;
                    } else if (user.getFilingStatus().equals("Qualified Widow")) {
                        noOfExcemtion = noOfExcemtion - 1;
                    }
                } else if (user.getFilingStatus().equals("Head of Household")) {
                    noOfExcemtion = noOfExcemtion + noOfDependentfortax - user.getChildCount();
                } else if (user.getFilingStatus().equals("Single")) {
                    noOfExcemtion = 1;
                }
                childArray = new JSONArray();
                final JSONObject userDetailsFromModal = new JSONObject(jsonMapper.writeValueAsString(user));
                for (int i = 0; i < assetsIncome.length(); i++) {
                    userExpense.getJSONObject(i).put("totalExpense",
                            (double) userExpense.getJSONObject(i).getInt("totalExpense"));
                    userExpense.getJSONObject(i).put("registerNonHousingExpense",
                            (double) userExpense.getJSONObject(i).getInt("registerNonHousingExpense"));
                    userExpense.getJSONObject(i).put("housingExpense",
                            (double) userExpense.getJSONObject(i).getInt("housingExpense"));
                    userExpense.getJSONObject(i).put("nonHousingExpense",
                            (double) userExpense.getJSONObject(i).getInt("nonHousingExpense"));
                    userExpense.getJSONObject(i).put("mortgageExpense",
                            (double) userExpense.getJSONObject(i).getInt("mortgageExpense"));
                    userExpense.getJSONObject(i).put("afterMarriageExpense",
                            (double) userExpense.getJSONObject(i).getInt("afterMarriageExpense"));
                    userExpense.getJSONObject(i).put("registerHousingExpense",
                            (double) userExpense.getJSONObject(i).getInt("registerHousingExpense"));
                    userExpense.getJSONObject(i).put("kidExpense",
                            (double) userExpense.getJSONObject(i).getInt("kidExpense"));
                }
                for (int i = 0; i < assetsIncome.length(); i++) {
                    int noOfExcemtion1 = noOfExcemtion;
                    int kids = 0;
                    if (user.getChildCount() > 0) {
                        childArray = userDetailsFromModal.getJSONArray("childs");
                        for (int k = 0; k < user.getChildCount(); k++) {
                            if (year - (registrationYear
                                    - childArray.getJSONObject(k).getInt("age")) > CHILD_AGE_LIMIT) {
                                kids++;
                                if ((childArray.getJSONObject(k).getString("flag").equals("Yes") && ((year
                                        - (registrationYear - childArray.getJSONObject(k).getInt("age"))) > HOURS))) {
                                    noOfExcemtion1 = noOfExcemtion1 - 1;
                                } else if (!childArray.getJSONObject(k).getString("flag").equals("Yes")) {
                                    noOfExcemtion1 = noOfExcemtion1 - 1;
                                }
                            }
                        }
                    }
                    year++;
                    final JSONObject obj = new JSONObject();
                    userExpense.getJSONObject(i).put("nonHousingExpense",
                            userDetailsTemp.getMonthlyExpenses() * MONTHS);
                    final double kidExpenseReduction = reduceChildExpense(
                            combinedIncome.getJSONObject(i).getDouble("value"),
                            userExpense.getJSONObject(i).getDouble("nonHousingExpense"), kids, kidCostArray, details.getKidcostFactor());
                    userExpense.getJSONObject(i).put("nonHousingExpense",
                            userExpense.getJSONObject(i).getDouble("nonHousingExpense") - kidExpenseReduction);
                    if (userExpense.getJSONObject(i).getDouble("mortgageExpense") > 0) {
                        userExpense.getJSONObject(i).put("totalExpense",
                                userExpense.getJSONObject(i).getDouble("nonHousingExpense")
                                + userExpense.getJSONObject(i).getDouble("mortgageExpense")
                                + userExpense.getJSONObject(i).getInt("kidExpense"));
                    } else {
                        userExpense.getJSONObject(i).put("totalExpense",
                                userExpense.getJSONObject(i).getDouble("nonHousingExpense")
                                + userExpense.getJSONObject(i).getDouble("housingExpense")
                                + userExpense.getJSONObject(i).getInt("kidExpense"));
                    }
                    obj.put("fillingStatus", user.getFilingStatus());
                    obj.put("noOfExcemtion", noOfExcemtion1);
                    if (noOfExcemtion1 <= 1) {
                        obj.put("fillingStatus", "Single");
                    }
                    obj.put("year", userExpense.getJSONObject(i).getInt("year"));
                    fillingExemtion.put(obj);
                }
                double user401k = IRA_LIMIT;
                double spouse401k = IRA_LIMIT;
                int ageAtRegistration = user.getAge();
                int spouseAgeForLimits = user.getSpouseAge();
                for (int i = 0; i < impLimits.length(); i++) {
                    if (maritalStatus.equals("Yes")) {
                        final JSONObject newLimits = CalculationEngine.limiteAfterRetirement(user401k, spouse401k, 0, 0,
                                0, 0, ageAtRegistration, userIncome.getJSONObject(i).getDouble("value"),
                                spouseIncome.getJSONObject(i).getDouble("value"),
                                fillingExemtion.getJSONObject(i).getString("fillingStatus"), spouseAgeForLimits,
                                impLimits.getJSONObject(i), (userIncome.getJSONObject(i).getDouble("value")
                                        + spouseIncome.getJSONObject(i).getDouble("value")),
                                i, null);

                        impLimits.put(i, newLimits);
                        ageAtRegistration++;
                        spouseAgeForLimits++;
                    } else {
                        final JSONObject newLimits = CalculationEngine.limiteAfterRetirement(user401k, 0, 0, 0, 0, 0,
                                ageAtRegistration, userIncome.getJSONObject(i).getDouble("value"), 0,
                                fillingExemtion.getJSONObject(i).getString("fillingStatus"), 0,
                                impLimits.getJSONObject(i), (userIncome.getJSONObject(i).getDouble("value") + 0), i,
                                null);
                        impLimits.put(i, newLimits);
                        ageAtRegistration++;
                    }
                }
                final int age = user.getAge();
                JSONObject result = new JSONObject();
                JSONObject tempPlot = new JSONObject();
                if (spouseIncome.length() > 0) {
                    tempPlot.put("year", spouseIncome.getJSONObject(0).getInt("year"));
                    tempPlot.put("spouseIncome", spouseIncome.getJSONObject(0).getDouble("value"));
                    spousePlot.put(tempPlot);
                }
                for (int i = 0; i < spouseIncome.length(); i++) {
                    tempPlot = new JSONObject();
                    if (spouseIncome.getJSONObject(i).getDouble("retirement_income") > 0) {
                        tempPlot.put("year", spouseIncome.getJSONObject(i).getInt("year"));
                        tempPlot.put("spouseIncome", spouseIncome.getJSONObject(i).getDouble("value"));
                        spousePlot.put(tempPlot);
                        break;
                    }
                }
                tempPlot = new JSONObject();
                if (spouseIncome.length() > 0) {
                    tempPlot.put("year", spouseIncome.getJSONObject(spouseIncome.length() - 1).getInt("year"));
                    tempPlot.put("spouseIncome",
                            spouseIncome.getJSONObject(spouseIncome.length() - 1).getString("value"));
                    spousePlot.put(tempPlot);
                }
                if (maritalStatus.equals("Yes")) {
                    userExpense = CalculationEngine.retirementExpenseArray(userExpense, spouseStartYear, userStartYear,
                            "Yes", 0);
                    result = CalculationEngine.sweepingOfMoney(userIncome, null, combinedIncome, spouseIncome,
                            userExpense, impLimits, maritalStatus, assetsIncome, tax, userId, fillingExemtion, age,
                            user.getSpouseAge(), 0, false, null, null, null, null, null, null, null, null, false);
                } else {
                    userExpense = CalculationEngine.retirementExpenseArray(userExpense, spouseStartYear, userStartYear,
                            "No", 0);
                    result = CalculationEngine.sweepingOfMoney(userIncome, null, combinedIncome, spouseIncome,
                            userExpense, impLimits, maritalStatus, assetsIncome, tax, userId, fillingExemtion, age, 0,
                            0, false, null, null, null, null, null, null, null, null, false);
                }
                final String status = result.getString("status");
                JSONArray assests = new JSONArray();
                if (status.equals("success")) {
                    assests = result.getJSONArray("assets");
                    tax = result.getJSONArray("tax");
                    if (!userJson.isNull("finplans")) {
                        for (int i = 0; i < userJson.getJSONArray("finplans").length(); i++) {
                            final FinPlan finDetails = MongoDBConnection.finplancol
                                    .findOne("{'_id': '" + userJson.getJSONArray("finplans").getString(i) + "'}")
                                    .as(FinPlan.class);
                            if (finDetails.getProfile_name().equals("constant_income")) {
                                final JSONObject finPlanJson = new JSONObject(
                                        jsonMapper.writeValueAsString(finDetails));
                                JSONArray finPlanExemption = finPlanJson.getJSONArray("fillingExemtion");
                                if ((finDetails.getMarital_status().equals("No") && user.getChildCount() > 0)) {
                                    finPlanExemption = fillingExemtion;
                                } else {
                                    finPlanExemption = finPlanJson.getJSONArray("fillingExemtion");
                                }
                                JSONArray finPlanUserExpense = finPlanJson.getJSONArray("userExpense");
                                JSONArray finLimits = finPlanJson.getJSONArray("limits");
                                JSONArray finAssets = finPlanJson.getJSONArray("assests");
                                JSONArray finTax = finPlanJson.getJSONArray("tax");
                                JSONArray finDeductions = finPlanJson.getJSONArray("deductions");

                                //code for update of age

                                final int userExpenseLength1 = finPlanUserExpense.length();
                                final int limitsLength1 = finLimits.length();
                                final int assetsIncomeLength1 = finAssets.length();
                                final int taxLength1 = finTax.length();
                                final int deductionsLength1 = finDeductions.length();
                                final int fillingExemptionsLength1 = finPlanExemption.length();
                                System.out.println("limitsLength1"+limitsLength1);
                                System.out.println("deductionsLength1"+deductionsLength1);
                                int noOfDependent1 = 0;
                                noOfDependent = user.getDependants();
                                for (int j = 0; j < userIncome.length(); j++) {
                                    JSONObject tempjson = new JSONObject();
                                    if(j >= fillingExemptionsLength1) {

                                        tempjson.put("fillingStatus",
                                                finPlanExemption.getJSONObject(fillingExemptionsLength1 - 1).getString("fillingStatus"));
                                        tempjson.put("oldFilingStatus",
                                                finPlanExemption.getJSONObject(fillingExemptionsLength1 - 1).getString("oldFilingStatus"));
                                        tempjson.put("noOfExcemtion",
                                                finPlanExemption.getJSONObject(fillingExemptionsLength1 - 1).getInt("noOfExcemtion"));

                                        tempjson.put("year", (finPlanExemption.getJSONObject(fillingExemptionsLength1 - 1).getInt("year"))
                                                + (j - fillingExemptionsLength1) + 1);
                                        finPlanExemption.put(tempjson);
                                        tempjson = new JSONObject();
                                    }

                                    if (j >= assetsIncomeLength1) {
                                        tempjson.put("nontaxable_investment_amount", 0);
                                        tempjson.put("userRothIRA", 0);
                                        tempjson.put("spouse529Plan", 0);
                                        tempjson.put("savings", 0);
                                        tempjson.put("user529Plan", 0);
                                        tempjson.put("spouseIRA", 0);
                                        tempjson.put("plan529", 0);
                                        tempjson.put("year", (finAssets.getJSONObject(assetsIncomeLength1 - 1).getInt("year"))
                                                + (j - assetsIncomeLength1) + 1);
                                        tempjson.put("userIRA", 0);
                                        tempjson.put("user401k", 0);
                                        tempjson.put("spouse401k", 0);
                                        tempjson.put("taxable_investment_amount", 0);
                                        tempjson.put("spouseRothIRA", 0);
                                        finAssets.put(tempjson);
                                        tempjson = new JSONObject();
                                    }
                                    if (j >= userExpenseLength1) {
                                        tempjson.put("totalExpense",
                                                userExpense.getJSONObject(userExpenseLength1 - 1).getDouble("totalExpense"));
                                        tempjson.put("registerNonHousingExpense", userExpense.getJSONObject(userExpenseLength1 - 1)
                                                .getDouble("registerNonHousingExpense"));
                                        tempjson.put("housingExpense",
                                                userExpense.getJSONObject(userExpenseLength1 - 1).getDouble("housingExpense"));
                                        tempjson.put("nonHousingExpense",
                                                userExpense.getJSONObject(userExpenseLength1 - 1).getDouble("nonHousingExpense"));
                                        tempjson.put("mortgageExpense",
                                                userExpense.getJSONObject(userExpenseLength1 - 1).getDouble("mortgageExpense"));
                                        tempjson.put("afterMarriageExpense",
                                                userExpense.getJSONObject(userExpenseLength1 - 1).getDouble("afterMarriageExpense"));
                                        tempjson.put("registerHousingExpense",
                                                userExpense.getJSONObject(userExpenseLength1 - 1).getDouble("registerHousingExpense"));
                                        tempjson.put("kidExpense",
                                                userExpense.getJSONObject(userExpenseLength1 - 1).getDouble("kidExpense"));
                                        tempjson.put("year", (finPlanUserExpense.getJSONObject(userExpenseLength1 - 1).getInt("year"))
                                                + (j - userExpenseLength1) + 1);
                                        finPlanUserExpense.put(tempjson);
                                        tempjson = new JSONObject();
                                    }
                                    if (j >= taxLength1) {
                                        tempjson.put("userSSTax", 0);
                                        tempjson.put("fICAMedicareTax", 0);
                                        tempjson.put("stateTax", 0);
                                        tempjson.put("year", finTax.getJSONObject(taxLength1 - 1).getInt("year") + (j - taxLength1) + 1);
                                        tempjson.put("fICASocialSecurityTax", 0);
                                        tempjson.put("spouseSSTax", 0);
                                        tempjson.put("federalTax", 0);
                                        finTax.put(tempjson);
                                        tempjson = new JSONObject();
                                    }
                                    if (j >= limitsLength1) {
                                        tempjson.put("collegeGoalTaxable", 0);
                                        tempjson.put("spouseRouthIRA", 0);
                                        tempjson.put("spouseIRA", 0);
                                        tempjson.put("taxable", 0);
                                        tempjson.put("saving", 0);
                                        tempjson.put("spouse529Plan", 0);
                                        tempjson.put("userIRA", 0);
                                        tempjson.put("year",
                                                finLimits.getJSONObject(limitsLength1 - 1).getInt("year") + (j - limitsLength1) + 1);
                                        tempjson.put("userRouthIRA", 0);
                                        tempjson.put("user401k", 0);
                                        tempjson.put("spouse401k", 0);
                                        tempjson.put("user529Plan", 0);
                                        finLimits.put(tempjson);
                                        tempjson = new JSONObject();
                                    }
                                    if (j >= deductionsLength1) {
                                        tempjson.put("taxable", 0);
                                        tempjson.put("nontaxable", 0);
                                        tempjson.put("collegeGoalNontaxable", 0);
                                        tempjson.put("saving", 0);
                                        tempjson.put("year",
                                                finDeductions.getJSONObject(deductionsLength1 - 1).getInt("year") + (j - deductionsLength1) + 1);
                                        finDeductions.put(tempjson);
                                        tempjson = new JSONObject();
                                    }

                                }

                                System.out.println("limitsLength1"+finLimits.length());
                                System.out.println("deductionsLength1"+finDeductions.length());
// end of the code




                                JSONArray expenseObj = new JSONArray();
                                JSONObject expense = new JSONObject();
                                expense = finPlanJson.getJSONObject("expense");
                                if (!expense.isNull("housing_expense")) {
                                    expenseObj = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
                                }
                                for (i = 0; i < finPlanExemption.length(); i++) {
                                    if (!(finDetails.getMarital_status().equals("No") && user.getChildCount() > 0)) {

                                        finPlanExemption.getJSONObject(i).put("fillingStatus",
                                                fillingExemtion.getJSONObject(i).getString("fillingStatus"));
                                        finPlanExemption.getJSONObject(i).put("noOfExcemtion",
                                                fillingExemtion.getJSONObject(i).getInt("noOfExcemtion")
                                                + fillingExemtion.getJSONObject(i).getInt("noOfExcemtion")
                                                - oldfillingExemtion.getJSONObject(i).getInt("noOfExcemtion"));
                                    }
                                    finPlanUserExpense.getJSONObject(i).put("nonHousingExpense",
                                            userExpense.getJSONObject(i).getDouble("nonHousingExpense"));

                                    if (finPlanUserExpense.getJSONObject(i).getDouble("mortgageExpense") > 0) {
                                        finPlanUserExpense.getJSONObject(i).put("totalExpense",
                                                finPlanUserExpense.getJSONObject(i).getDouble("nonHousingExpense")
                                                + finPlanUserExpense.getJSONObject(i)
                                                .getDouble("mortgageExpense")
                                                + finPlanUserExpense.getJSONObject(i).getInt("kidExpense"));
                                    } else {
                                        finPlanUserExpense.getJSONObject(i).put("totalExpense",
                                                finPlanUserExpense.getJSONObject(i).getDouble("nonHousingExpense")
                                                + finPlanUserExpense.getJSONObject(i)
                                                .getDouble("housingExpense")
                                                + finPlanUserExpense.getJSONObject(i).getInt("kidExpense"));
                                    }

                                }
                                final JSONArray goals = finPlanJson.getJSONArray("goals");
                                RetirementGoal retirementObj = null;
                                for (int k = 0; k < goals.length(); k++) {
                                    final RetirementGoal goalDetails = MongoDBConnection.goalcoll
                                            .findOne("{_id:#}", goals.getString(k)).as(RetirementGoal.class);
                                    if (goalDetails.getGoalType().equals("Retirement")) {
                                        retirementObj = goalDetails;
                                    }
                                }

                                final int currentYear = cal.get(Calendar.YEAR);

                                spouseStartYear = 0;
                                userStartYear = 0;
                                long retirementExpense = 0;
                                if (retirementObj != null) {
                                    userStartYear = currUserBirthYear + retirementObj.getRetirementAge();
                                    if (finDetails.getMarital_status().equals("Yes")
                                            && details.getMarital_status().equals("No")) {
                                        spouseStartYear = currSpouseBirthYear + retirementObj.getSpouseRetirementAge();
                                    } else if (details.getMarital_status().equals("Yes")) {
                                        spouseStartYear = currSpouseBirthYear + retirementObj.getSpouseRetirementAge();
                                    }
                                    retirementExpense = retirementObj.getRetirement_expense();

                                    // ------------------------------------------------
                                    final RetirementGoalServiceImpl goalservice = new RetirementGoalServiceImpl();
                                    final double userAIME = goalservice.findUserAIME(retirementObj.getRetirementAge(),
                                            userIncome, userStartYear);
                                    final int userFRA = goalservice.findUserFRA(retirementObj.getRetirementAge(),
                                            details.getBirthYear());
                                    final JSONObject userJsonSSB = goalservice.calculateUserSSB(
                                            userIncome.getJSONObject(0).getDouble("value"), "Yes", userFRA, 0,
                                            FIRST_BEND_POINT, SECOND_BEND_POINT, userAIME, 0);

                                    final JSONObject spouseJson = new JSONObject();
                                    spouseJson.put("firetBendPoint", FIRST_BEND_POINT);
                                    spouseJson.put("spouseRetirementAge", retirementObj.getSpouseRetirementAge());
                                    spouseJson.put("userRetirementAge", retirementObj.getRetirementAge());
                                    spouseJson.put("useFfra", userFRA);
                                    spouseJson.put("spouseFfra", goalservice.findUserFRA(
                                            retirementObj.getSpouseRetirementAge(), details.getSpouseBirthYear()));
                                    spouseJson.put("userAime", userAIME);
                                    spouseJson.put("spouseAime", goalservice.findUserAIME(
                                            retirementObj.getSpouseRetirementAge(), spouseIncome, spouseStartYear));
                                    spouseJson.put("secondBendPoint", SECOND_BEND_POINT);
                                    final JSONObject allIncomes = goalservice
                                            .insertRetirementIncomeNew(
                                                    ((Double) (userJsonSSB
                                                            .get("User" + retirementObj.getRetirementAge()))),
                                                    spouseJson, userId, userIncome, spouseIncome, userStartYear,
                                                    spouseStartYear, spouseStartYear);
                                    userIncome = allIncomes.getJSONArray("user_income");
                                    spouseIncome = allIncomes.getJSONArray("spouse_income");
                                    combinedIncome = allIncomes.getJSONArray("combined_income");
                                } else {
                                    userStartYear = currUserBirthYear + DEFAULT_RETIRMENT_AGE;
                                    if (finDetails.getMarital_status().equals("Yes")
                                            && details.getMarital_status().equals("No")) {
                                        spouseStartYear = currSpouseBirthYear + DEFAULT_RETIRMENT_AGE;
                                    } else if (details.getMarital_status().equals("Yes")) {
                                        spouseStartYear = currSpouseBirthYear + DEFAULT_RETIRMENT_AGE;
                                    }
                                }

                                final JSONObject retirementData = new JSONObject();
                                retirementData.put("spouseStartYear", spouseStartYear);
                                retirementData.put("userStartYear", userStartYear);
                                String emergencyType = null;
                                String monthsOfIncome = null;
                                String monthsOfExpense = null;
                                if (finDetails.isEmergencyFundFlag()) {
                                    final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                                            .findOne("{fin_id:#,goalType:#}", finDetails.get_id(), "Emergency Fund")
                                            .as(Emergencyfundmodel.class);
                                    emergencyType = emergencyObj.getTimePeriod();
                                    monthsOfIncome = emergencyObj.getMonthI();
                                    monthsOfExpense = emergencyObj.getMonthE();
                                }
                                user401k = IRA_LIMIT;
                                spouse401k = IRA_LIMIT;
                                ageAtRegistration = user.getAge();
                                spouseAgeForLimits = finDetails.getSpouseAge();


                                for (i = 0; i < finLimits.length(); i++) {
                                    if (finDetails.getMarital_status().equals("Yes")) {
                                        final JSONObject newLimits = CalculationEngine
                                                .limiteAfterRetirement(user401k, spouse401k, 0, 0, 0, 0,
                                                        ageAtRegistration,
                                                        userIncome.getJSONObject(i).getDouble("value"),
                                                        spouseIncome.getJSONObject(i).getDouble("value"),
                                                        user.getFilingStatus(), spouseAgeForLimits,
                                                        impLimits.getJSONObject(i),
                                                        (userIncome.getJSONObject(i).getDouble("value")
                                                                + spouseIncome.getJSONObject(i).getDouble("value")),
                                                        i, null);

                                        finLimits.put(i, newLimits);
                                        spouseAgeForLimits++;
                                    } else {
                                        final JSONObject newLimits = CalculationEngine.limiteAfterRetirement(user401k,
                                                0, 0, 0, 0, 0, ageAtRegistration,
                                                userIncome.getJSONObject(i).getDouble("value"), 0,
                                                user.getFilingStatus(), 0, impLimits.getJSONObject(i),
                                                (userIncome.getJSONObject(i).getDouble("value") + 0), i, null);

                                        finLimits.put(i, newLimits);
                                        spouseAgeForLimits++;

                                    }
                                }
                                finPlanUserExpense = CalculationEngine.retirementExpenseArray(finPlanUserExpense,
                                        spouseStartYear, userStartYear, finDetails.getMarital_status(),
                                        retirementExpense);

                                final JSONObject resultFinplanUpdate = CalculationEngine.sweepingOfMoney(userIncome,
                                        finDetails.get_id(), combinedIncome, spouseIncome, finPlanUserExpense,
                                        finLimits, user.getMarital_status(), finAssets,
                                        finTax, userId, fillingExemtion, user.getAge(),
                                        user.getSpouseAge(), finDetails.getEmergencyFundAmt(),
                                        finDetails.isEmergencyFundFlag(), finDeductions,
                                        finPlanJson.getJSONArray("kidBirthYear"), retirementData, retirementObj,
                                        expenseObj, emergencyType, monthsOfIncome, monthsOfExpense,
                                        finDetails.isRetirementFlag());
                                if (resultFinplanUpdate.getString("status").equals("success")) {

                                    final JSONArray assets1 = resultFinplanUpdate.getJSONArray("assets");
                                    final JSONArray tax1 = resultFinplanUpdate.getJSONArray("tax");

                                    MongoDBConnection.finplancol.update("{'_id':#}", finDetails.get_id()).upsert()
                                    .multi()
                                    .with("{$set: {'assests':" + assets1 + ",'tax':" + tax1
                                            + ",'fillingExemtion':" + finPlanExemption + ",'limits':" + finLimits + ",'deductions':" + finDeductions + ",'userExpense':"
                                            + finPlanUserExpense + "}}");
                                }

                            }
                        }
                    }

                    final int birthYear = cal.get(Calendar.YEAR) - user.getAge();
                    MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                    .with("{$set: {'lname':'" + user.getLname() + "','address2':'" + user.getAddress2()
                    + "','county':'" + user.getCounty() + "','address1':'" + user.getAddress1()
                    + "','college_info':'" + user.getCollege_info() + "','email':'" + user.getEmail()
                    + "','name':'" + user.getName() + "','city':'" + user.getCity() + "','state':'"
                    + user.getState() + "','age':'" + user.getAge() + "','birthYear':'" + birthYear
                    + "','marital_status':'" + user.getMarital_status() + "','filingStatus':'"
                    + user.getFilingStatus() + "','dependants':'" + user.getDependants()
                    + "','childCount':'" + user.getChildCount() + "','kidCostCalculatedArray':'"
                    + kidCostArray + "'}}");
                    final int childcount = user.getChildCount();
                    if (childcount > 0) {
                        userJson = new JSONObject(jsonMapper.writeValueAsString(user));
                        childsJsonArray = userJson.getJSONArray("childs");
                        for (int i = 0; i < childsJsonArray.length(); i++) {
                            if (childsJsonArray.getJSONObject(i).getInt("age") < CHILD_AGE_LIMIT) {
                                noOfDependent++;
                            }
                            if (childsJsonArray.getJSONObject(i).getString("flag").equals("Yes")
                                    && childsJsonArray.getJSONObject(i).getInt("age") >= CHILD_AGE_LIMIT) {
                                noOfDependent++;
                            }
                        }
                        MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                        .with("{$set: {'childs':" + childsJsonArray + ",'dependants':'" + noOfDependent
                                + "','noOfDependentfortax':'" + user.getNoOfDependentfortax()
                                + "','userDependent':" + user.getDependants() + "}}");
                    }
                    MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                    .with("{$set: {'dependants':" + noOfDependent + ",'noOfDependentfortax':"
                            + user.getNoOfDependentfortax() + ",'userDependent':" + user.getDependants()
                            + "}}");
                    if (user.getMarital_status().equals("Yes")) {
                        final int spouseBirthYear = cal.get(Calendar.YEAR) - user.getSpouseAge();
                        MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                        .with("{$set: {'spouseName':'" + user.getSpouseName() + "','spouseBirthYear':'"
                                + spouseBirthYear + "'," + "'SpouseAge':'" + user.getSpouseAge()
                                + "','childCount':'" + user.getChildCount()
                                + "','filingStatus':'Married Filing Jointly','dependants':" + noOfDependent
                                + ",'noOfDependentfortax':" + user.getNoOfDependentfortax()
                                + ",'userDependent':" + user.getDependants() + "}}");
                    }
                    if (user.getMarital_status().equals("Yes")) {
                        MongoDBConnection.incomeProfileCol
                        .update("{'user_id': '" + user.getUser_id() + "','profile_name':'constant_income'}")
                        .with("{$set: {'spouse_income':" + spouseIncome + ",'spousePlot':" + spousePlot
                                + ",'combined_income':" + combinedIncome + "}}");
                        if (!userJson.isNull("finplans")) {
                            for (int i = 0; i < userJson.getJSONArray("finplans").length(); i++) {
                                MongoDBConnection.finplancol
                                .update("{'_id': '" + userJson.getJSONArray("finplans").getString(i)
                                        + "','profile_name':'constant_income'}")
                                .with("{$set: {'marital_status':'Yes'}}");
                            }
                        }
                    } else {
                        final JSONArray a = new JSONArray();
                        MongoDBConnection.incomeProfileCol
                        .update("{'user_id': '" + user.getUser_id() + "','profile_name':'constant_income'}")
                        .with("{$set: {'spouse_income':" + a + "}}");
                        if (!userJson.isNull("finplans")) {
                            for (int i = 0; i < userJson.getJSONArray("finplans").length(); i++) {
                                MongoDBConnection.finplancol
                                .update("{'_id': '" + userJson.getJSONArray("finplans").getString(i)
                                        + "','profile_name':'constant_income'}")
                                .with("{$set: {'marital_status':'No'}}");
                            }
                        }
                    }
                    MongoDBConnection.incomeProfileCol
                    .update("{'user_id': '" + user.getUser_id() + "','profile_name':'constant_income'}")
                    .upsert().multi().with("{$set: {'tax':" + tax + ",'userExpense':" + userExpense
                            + ",'user_income':" + userIncome + ",'combined_income':" + combinedIncome+ ",'limits':" + impLimits+ "}}");
                    MongoDBConnection.incomeProfileCol
                    .update("{'user_id': '" + user.getUser_id() + "','profile_name':'constant_income'}")
                    .upsert().multi().with("{$set: {'assests':" + assests + "}}");
                    return jobject.put("status", "success");
                } else {
                    final int zero = 0;
                    MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                    .with("{$set: {'spouseBirthYear':" + oldSpouseBirthYear + ",'birthYear':" + oldUserBirthYear
                            + ",'city':'" + oldCity + "','state':'" + oldState + "'}}");
                    return jobject.put("status", "fail");
                }
            } else if (formType.equals("editincome")) {
                final double realEstate = user.getHouseMarketValue() - user.getRemainingMortgage();
                final User available = MongoDBConnection.userColl.findOne("{_id:#}", user.getUser_id()).as(User.class);
                final String kidCalculatedCost = available.getKidCostCalculatedArray();
                final int userAge = available.getAge();
                final int currentYear = cal.get(Calendar.YEAR);
                int userStartYear = currentYear - userAge + DEFAULT_RETIRMENT_AGE;
                int spouseAge;
                int spouseStartYear = 0;
                if (available.getMarital_status().equals("Yes")) {
                    spouseAge = available.getSpouseAge();
                    spouseStartYear = currentYear - spouseAge + DEFAULT_RETIRMENT_AGE;
                }
                MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                .with("{$set: {'realEstate':" + realEstate + ",'houseValue':" + user.getHouseMarketValue()
                + ",'remainingMortgage':" + user.getRemainingMortgage() + ",'remainingYearsMortgage':"
                + user.getRemainingYearsMortgage() + ",'remainingMortgageInterestRate':"
                + user.getWhatIsYourCurrentMortgageRate() + "}}");
                final double oldrealEstate = available.getRealEstate();
                final double oldhouseValue = available.getHouseValue();
                final double oldremainingMortgage = available.getRemainingMortgage();
                final long oldremainingYearsOfMortgage = available.getRemainingYearsMortgage();
                final double oldremainingMortgageInterestRate = available.getRemainingMortgageInterestRate();
                int flag = 0;
                final User userDetails = MongoDBConnection.userColl.findOne("{_id:#}", user.getUser_id())
                        .as(User.class);
                final JSONObject userJSON = new JSONObject(jsonMapper.writeValueAsString(userDetails));
                JSONArray spouseIncomeArray = null;
                jobject.put("spouseIncome", 0);
                JSONArray combinedIncomeArray = null;
                if (details.getMarital_status().equals("Yes")) {
                    spouseIncomeArray = incomeJson.getJSONArray("spouse_income");
                    combinedIncomeArray = incomeJson.getJSONArray("combined_income");
                }
                final IncomeProfile incomeProfs = MongoDBConnection.incomeProfileCol
                        .findOne("{user_id:#,profile_name:#}", user.getUser_id(), "constant_income")
                        .as(IncomeProfile.class);
                final JSONObject incomeJsons = new JSONObject(jsonMapper.writeValueAsString(incomeProfs));
                JSONArray userExpenses = incomeJsons.getJSONArray("userExpense");
                final double montlyexpancess = (user.getMonthlyExpenses() * MONTHS);
                final double getrentexpensess = (user.getRentExpenses() * MONTHS);
                for (int i = 0; i < userExpenses.length(); i++) {
                    if (userExpenses.getJSONObject(i).getInt("year") == cal.get(Calendar.YEAR)
                            || userExpenses.getJSONObject(i).getInt("year") > cal.get(Calendar.YEAR)) {
                        userExpenses.getJSONObject(i).put("housingExpense", montlyexpancess);
                        userExpenses.getJSONObject(i).put("nonHousingExpense", getrentexpensess);
                        userExpenses.getJSONObject(i).put("totalExpense", (montlyexpancess + getrentexpensess));
                    }
                }
                if (available.getHouseStatus().equals("Own")) {
                    ////////// //System.out.println("hbasdhb");
                    final int remainingYears = (int) (user.getRemainingYearsMortgage());
                    for (int i = remainingYears; i < userExpenses.length(); i++) {
                        userExpenses.getJSONObject(i).put("totalExpense",
                                userExpenses.getJSONObject(i).getDouble("totalExpense")
                                - userExpenses.getJSONObject(i).getDouble("nonHousingExpense"));
                        userExpenses.getJSONObject(i).put("nonHousingExpense",
                                userExpenses.getJSONObject(i).getDouble("nonHousingExpense")
                                - userExpenses.getJSONObject(i).getDouble("nonHousingExpense") * TEN / PERCENT);
                        if (userExpenses.getJSONObject(i).getDouble("nonHousingExpense") < 0) {
                            userExpenses.getJSONObject(i).put("nonHousingExpense", 0);
                        }
                        userExpenses.getJSONObject(i).put("totalExpense",
                                userExpenses.getJSONObject(i).getDouble("totalExpense")
                                + userExpenses.getJSONObject(i).getDouble("nonHousingExpense"));

                    }
                }
                if (available.getMarital_status().equals("Yes")) {
                    userExpenses = CalculationEngine.retirementExpenseArray(userExpenses, spouseStartYear,
                            userStartYear, "Yes", 0);
                } else {
                    userExpenses = CalculationEngine.retirementExpenseArray(userExpenses, spouseStartYear,
                            userStartYear, "No", 0);
                }
                final JSONArray userIncome = incomeJsons.getJSONArray("user_income");
                if (details.getMarital_status().equals("Yes")) {
                    spouseIncomeArray = incomeJson.getJSONArray("spouse_income");
                    combinedIncomeArray = incomeJson.getJSONArray("combined_income");
                } else {
                    combinedIncomeArray = incomeJsons.getJSONArray("user_income");
                }
                final JSONArray oldfillingExemtion = new JSONArray();
                final String[] out = available.getCreatedTs().split("/");
                final int registrationYear = Integer.parseInt(out[0]);
                final int noOfDependentfortax = user.getDependants() + available.getChildCount();
                int noOfExcemtion = 1 + available.getChildCount();
                year = incomeJson.getJSONArray("user_income").getJSONObject(0).getInt("year");
                if (available.getMarital_status().equals("Yes") || !incomeJson.isNull("spouse_income")) {
                    noOfExcemtion = noOfExcemtion + noOfDependentfortax - available.getChildCount() + 1;
                    if (available.getFilingStatus().equals("Head of Household")) {
                        noOfExcemtion = noOfExcemtion + noOfDependentfortax - available.getChildCount();
                    } else if (available.getFilingStatus().equals("Married Filing Separately")) {
                        noOfExcemtion = noOfExcemtion - 1;
                    } else if (available.getFilingStatus().equals("Qualified Widow")) {
                        noOfExcemtion = noOfExcemtion - 1;
                    }
                } else if (available.getFilingStatus().equals("Head of Household")) {
                    noOfExcemtion = noOfExcemtion + noOfDependentfortax - available.getChildCount();
                } else if (user.getFilingStatus().equals("Single")) {
                    noOfExcemtion = 1;
                }
                JSONArray childArray = new JSONArray();
                final JSONObject userDetailsFromDB = new JSONObject(jsonMapper.writeValueAsString(available));
                for (int i = 0; i < combinedIncomeArray.length(); i++) {
                    int noOfExcemtion1 = noOfExcemtion;
                    int kids = 0;
                    if (available.getChildCount() != 0 && !userDetailsFromDB.isNull("childs")) {

                        childArray = userDetailsFromDB.getJSONArray("childs");
                        for (int k = 0; k < available.getChildCount(); k++) {
                            if (year - (registrationYear
                                    - childArray.getJSONObject(k).getInt("age")) > CHILD_AGE_LIMIT) {
                                kids++;
                                if ((childArray.getJSONObject(k).getString("flag").equals("Yes") && year
                                        - (registrationYear - childArray.getJSONObject(k).getInt("age")) > HOURS)
                                        || (!childArray.getJSONObject(k).getString("flag").equals("Yes"))) {
                                    noOfExcemtion1 = noOfExcemtion1 - 1;
                                }
                            }
                        }
                    }
                    year++;
                    final JSONObject obj = new JSONObject();
                    obj.put("fillingStatus", available.getFilingStatus());
                    obj.put("noOfExcemtion", noOfExcemtion1);
                    final double kidExpenseReduction = reduceChildExpense(
                            combinedIncomeArray.getJSONObject(i).getDouble("value"),
                            userExpenses.getJSONObject(i).getDouble("nonHousingExpense"), kids,
                            new JSONArray(kidCalculatedCost),details.getKidcostFactor());
                    userExpenses.getJSONObject(i).put("nonHousingExpense",
                            userExpenses.getJSONObject(i).getDouble("nonHousingExpense") - kidExpenseReduction);
                    if (userExpenses.getJSONObject(i).getDouble("mortgageExpense") > 0) {
                        userExpenses.getJSONObject(i).put("totalExpense",
                                userExpenses.getJSONObject(i).getDouble("nonHousingExpense")
                                + userExpenses.getJSONObject(i).getDouble("mortgageExpense")
                                + userExpenses.getJSONObject(i).getInt("kidExpense"));
                    } else {
                        userExpenses.getJSONObject(i).put("totalExpense",
                                userExpenses.getJSONObject(i).getDouble("nonHousingExpense")
                                + userExpenses.getJSONObject(i).getDouble("housingExpense")
                                + userExpenses.getJSONObject(i).getInt("kidExpense"));
                    }

                    obj.put("year", combinedIncomeArray.getJSONObject(i).getInt("year"));
                    oldfillingExemtion.put(obj);
                }

                JSONArray tax = incomeJsons.getJSONArray("tax");
                final JSONArray assests = incomeJsons.getJSONArray("assests");
                JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, null, combinedIncomeArray,
                        spouseIncomeArray, userExpenses, incomeJsons.getJSONArray("limits"),
                        available.getMarital_status(), assests, tax, available.get_id(), oldfillingExemtion,
                        user.getAge(), user.getSpouseAge(), 0, false, null, null, null, null, null, null, null, null,
                        false);
                String status = result.getString("status");
                if (status.equals("success")) {
                    final JSONArray assetsIncome = result.getJSONArray("assets");
                    final JSONArray taxIncome = result.getJSONArray("tax");
                    if (!userJSON.isNull("finplans") && userJSON.getJSONArray("finplans").length() != 0) {
                        final JSONArray finplanArry = userJSON.getJSONArray("finplans");
                        final double montlyexpances = (user.getMonthlyExpenses() * MONTHS);
                        final double getrentexpenses = (user.getRentExpenses() * MONTHS);
                        for (int k = 0; k < finplanArry.length(); k++) {
                            final FinPlan finplans = MongoDBConnection.finplancol.findOne("{_id:#}", finplanArry.get(k))
                                    .as(FinPlan.class);
                            if (finplans.getProfile_name().equals("constant_income")) {
                                final JSONObject userFinPlan = new JSONObject(jsonMapper.writeValueAsString(finplans));
                                JSONArray userExpense1 = userFinPlan.getJSONArray("userExpense");
                                JSONArray expenseObj = new JSONArray();
                                JSONObject expense = new JSONObject();
                                expense = userFinPlan.getJSONObject("expense");
                                if (!expense.isNull("housing_expense")) {
                                    expenseObj = userFinPlan.getJSONObject("expense").getJSONArray("housing_expense");
                                }
                                if (userExpense1.getJSONObject(k).getInt("year") == cal.get(Calendar.YEAR)
                                        || userExpense1.getJSONObject(k).getInt("year") > cal.get(Calendar.YEAR)) {
                                    for (int i = 0; i < userExpense1.length(); i++) {

                                        final double extraExpense = (montlyexpances
                                                - userExpense1.getJSONObject(i).getDouble("housingExpense"))
                                                + (getrentexpenses
                                                        - userExpense1.getJSONObject(i).getDouble("nonHousingExpense"));

                                        userExpense1.getJSONObject(i).put("housingExpense", montlyexpances);
                                        userExpense1.getJSONObject(i).put("nonHousingExpense", getrentexpenses);
                                        userExpense1.getJSONObject(i).put("totalExpense",
                                                userExpense1.getJSONObject(i).getDouble("totalExpense") + extraExpense);

                                        userExpense1.getJSONObject(i).put("nonHousingExpense",
                                                userExpenses.getJSONObject(i).getDouble("nonHousingExpense"));

                                        if (userExpense1.getJSONObject(i).getDouble("mortgageExpense") > 0) {
                                            userExpense1.getJSONObject(i).put("totalExpense",
                                                    userExpense1.getJSONObject(i).getDouble("nonHousingExpense")
                                                    + userExpense1.getJSONObject(i).getDouble("mortgageExpense")
                                                    + userExpense1.getJSONObject(i).getInt("kidExpense"));
                                        } else {
                                            userExpense1.getJSONObject(i).put("totalExpense",
                                                    userExpense1.getJSONObject(i).getDouble("nonHousingExpense")
                                                    + userExpense1.getJSONObject(i).getDouble("housingExpense")
                                                    + userExpense1.getJSONObject(i).getInt("kidExpense"));
                                        }
                                    }
                                }
                                int finSpousestartYear = 0;
                                int finUserstartYear;
                                final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                                        .findOne("{fin_id:#,goalType:#}", finplans.get_id(), "Retirement")
                                        .as(RetirementGoal.class);
                                if (retirementObj != null) {
                                    finUserstartYear = retirementObj.getRetirementAge();
                                    if (finplans.getMarital_status().equals("Yes")) {
                                        finSpousestartYear = retirementObj.getSpouseAge();
                                        userExpense1 = CalculationEngine.retirementExpenseArray(userExpense1,
                                                finSpousestartYear, finUserstartYear, "Yes", 0);
                                    } else {
                                        userExpense1 = CalculationEngine.retirementExpenseArray(userExpense1,
                                                finSpousestartYear, finUserstartYear, "No", 0);
                                    }
                                } else {
                                    if (finplans.getMarital_status().equals("Yes")) {
                                        userExpense1 = CalculationEngine.retirementExpenseArray(userExpense1,
                                                spouseStartYear, userStartYear, "Yes", 0);
                                    } else {
                                        userExpense1 = CalculationEngine.retirementExpenseArray(userExpense1,
                                                spouseStartYear, userStartYear, "No", 0);
                                    }
                                }
                                final IncomeProfile incomeProfTemp = MongoDBConnection.incomeProfileCol
                                        .findOne("{user_id:#,profile_name:#}", finplans.getUsr_id(), "constant_income")
                                        .as(IncomeProfile.class);
                                final JSONObject incomeJsonTemp = new JSONObject(
                                        jsonMapper.writeValueAsString(incomeProfTemp));
                                JSONArray combinedIncome = new JSONArray();

                                JSONArray spouseIncome = new JSONArray();

                                if (finplans.getMarital_status().equals("Yes")) {
                                    combinedIncome = incomeJsonTemp.getJSONArray("combined_income");
                                    spouseIncome = incomeJsonTemp.getJSONArray("spouse_income");
                                } else {
                                    combinedIncome = incomeJsonTemp.getJSONArray("user_income");
                                }
                                int spouseStartYearNew = 0;
                                userStartYear = 0;
                                if (retirementObj != null) {
                                    userStartYear = details.getBirthYear() + retirementObj.getRetirementAge();
                                    if (finplans.getMarital_status().equals("Yes")
                                            && details.getMarital_status().equals("No")) {
                                        spouseStartYearNew = finplans.getSpouseBirthYear()
                                                + retirementObj.getSpouseRetirementAge();
                                    } else if (details.getMarital_status().equals("Yes")) {
                                        spouseStartYearNew = details.getSpouseBirthYear()
                                                + retirementObj.getSpouseRetirementAge();
                                    }

                                } else {
                                    userStartYear = details.getBirthYear() + DEFAULT_RETIRMENT_AGE;
                                    if (finplans.getMarital_status().equals("Yes")
                                            && details.getMarital_status().equals("No")) {
                                        spouseStartYearNew = finplans.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                                    } else if (details.getMarital_status().equals("Yes")) {
                                        spouseStartYearNew = details.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                                    }
                                }
                                final JSONObject retirementData = new JSONObject();
                                retirementData.put("spouseStartYear", spouseStartYearNew);
                                retirementData.put("userStartYear", userStartYear);

                                String emergencyType = null;
                                String monthsOfIncome = null;
                                String monthsOfExpense = null;
                                if (finplans.isEmergencyFundFlag()) {
                                    final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                                            .findOne("{fin_id:#,goalType:#}", finplans.get_id(), "Emergency Fund")
                                            .as(Emergencyfundmodel.class);
                                    emergencyType = emergencyObj.getTimePeriod();
                                    monthsOfIncome = emergencyObj.getMonthI();
                                    monthsOfExpense = emergencyObj.getMonthE();
                                }
                                result = CalculationEngine.sweepingOfMoney(userIncome, finplans.get_id(),
                                        combinedIncome, spouseIncome, userExpense1, userFinPlan.getJSONArray("limits"),
                                        finplans.getMarital_status(), userFinPlan.getJSONArray("assests"),
                                        userFinPlan.getJSONArray("tax"), details.get_id(),
                                        userFinPlan.getJSONArray("fillingExemtion"), userDetails.getAge(),
                                        finplans.getSpouseAge(), finplans.getEmergencyFundAmt(),
                                        finplans.isEmergencyFundFlag(), userFinPlan.getJSONArray("deductions"),
                                        userFinPlan.getJSONArray("kidBirthYear"), retirementData, retirementObj,
                                        expenseObj, emergencyType, monthsOfIncome, monthsOfExpense,
                                        finplans.isRetirementFlag());
                                status = result.getString("status");
                                if (status.equals("success")) {
                                    final JSONArray assets = result.getJSONArray("assets");
                                    tax = result.getJSONArray("tax");
                                    MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert()
                                    .multi()
                                    .with("{$set: {'monthlyExpenses':'" + user.getMonthlyExpenses()
                                    + "','rentalExpenses':'" + user.getRentExpenses()
                                    + "','otherExpenses':'" + user.getOtherExpenses() + "'}}");
                                    MongoDBConnection.incomeProfileCol
                                    .update("{user_id:#,profile_name:#}", user.getUser_id(), "constant_income")
                                    .upsert().multi().with("{$set: {'userExpense':" + userExpenses + ",'tax':"
                                            + taxIncome + ",'assests':" + assetsIncome + "}}");
                                    MongoDBConnection.finplancol.update("{'_id': '" + finplanArry.get(k) + "'}")
                                    .upsert().multi().with("{$set: {'userExpense':" + userExpense1 + "}}");
                                    MongoDBConnection.finplancol.update("{_id:'" + finplanArry.get(k) + "'}").upsert()
                                    .multi().with("{$set: {'assests':" + assets + ",'tax':" + tax + "}}");
                                } else {
                                    flag = 1;
                                }
                            }
                        }
                    } else {
                        MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                        .with("{$set: {'monthlyExpenses':'" + user.getMonthlyExpenses() + "','rentalExpenses':'"
                                + user.getRentExpenses() + "','otherExpenses':'" + user.getOtherExpenses()
                                + "'}}");
                        MongoDBConnection.incomeProfileCol
                        .update("{user_id:#,profile_name:#}", user.getUser_id(), "constant_income").upsert()
                        .multi().with("{$set: {'userExpense':" + userExpenses + ",'tax':" + taxIncome
                                + ",'assests':" + assetsIncome + "}}");
                    }
                } else {
                    flag = 1;
                }

                if (flag == 0) {
                    return jobject.put("status", "success");
                } else {
                    MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                    .with("{$set: {'realEstate':" + oldrealEstate + ",'houseValue':" + oldhouseValue
                            + ",'remainingMortgage':" + oldremainingMortgage + ",'remainingYearsMortgage':"
                            + oldremainingYearsOfMortgage + ",'remainingMortgageInterestRate':"
                            + oldremainingMortgageInterestRate + "}}");
                    return jobject.put("status", "fail");
                }
            } else if (formType.equals("editAssets")) {
                final User userDetailsTemp = MongoDBConnection.userColl.findOne("{_id:#}", user.getUser_id())
                        .as(User.class);
                final JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(userDetailsTemp));
                final double user401kPrevVal = userJson.getDouble("user401k");
                final double userIRAPrevVal = userJson.getDouble("userIRA");
                final double userRothIraPrevVal = userJson.getDouble("userRothIra");
                final double user529PrevVal = userJson.getDouble("user529");
                final double spouse401kPrevVal = userJson.getDouble("spouse401k");
                final double spouseIRAPrevVal = userJson.getDouble("spouseIRA");
                final double spouseRothIraPrevVal = userJson.getDouble("spouseRothIra");
                final double spousePlan529PrevVal = userJson.getDouble("spousePlan529");
                final double cashPrevVal = userJson.getDouble("cash");
                final double taxableInvestmentsPreVal = userJson.getDouble("taxableInvestments");
                final String nonTaxablePreVal = userJson.getString("nonTaxableInvestments");
                MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                .with("{$set: {'cash':'" + user.getCash() + "','taxableInvestments':'"
                        + user.getTaxableInvestments() + "','nonTaxableInvestments':'"
                        + user.getNonTaxableInvestments() + "','user401k':" + user.getUser401k() + ",'userIRA':"
                        + user.getUserIRA() + ",'userRothIra':" + user.getUserRothIra() + ",'user529':"
                        + user.getUser529() + ",'spouse401k':" + user.getSpouse401k() + ",'spouseIRA':"
                        + user.getSpouseIRA() + ",'spouseRothIra':" + user.getSpouseRothIra()
                        + ",'spousePlan529':" + user.getSpousePlan529() + "}}");

                final User available = MongoDBConnection.userColl.findOne("{_id:#}", user.getUser_id()).as(User.class);
                final int userAge = available.getAge();
                final int currentYear = cal.get(Calendar.YEAR);
                int userStartYear = currentYear - userAge + DEFAULT_RETIRMENT_AGE;
                int spouseAge;
                int spouseStartYear = 0;
                if (available.getMarital_status().equals("Yes")) {
                    spouseAge = available.getSpouseAge();
                    spouseStartYear = currentYear - spouseAge + DEFAULT_RETIRMENT_AGE;
                }
                int flag = 0;
                final User userDetails = MongoDBConnection.userColl.findOne("{_id:#}", user.getUser_id())
                        .as(User.class);
                final JSONObject userJSON = new JSONObject(jsonMapper.writeValueAsString(userDetails));
                JSONArray spouseIncomeArray = null;
                jobject.put("spouseIncome", 0);
                JSONArray combinedIncomeArray = null;
                if (details.getMarital_status().equals("Yes")) {
                    spouseIncomeArray = incomeJson.getJSONArray("spouse_income");
                    combinedIncomeArray = incomeJson.getJSONArray("combined_income");
                }
                final IncomeProfile incomeProfs = MongoDBConnection.incomeProfileCol
                        .findOne("{user_id:#,profile_name:#}", user.getUser_id(), "constant_income")
                        .as(IncomeProfile.class);
                final JSONObject incomeJsons = new JSONObject(jsonMapper.writeValueAsString(incomeProfs));
                JSONArray userExpenses = incomeJsons.getJSONArray("userExpense");
                if (available.getMarital_status().equals("Yes")) {
                    userExpenses = CalculationEngine.retirementExpenseArray(userExpenses, spouseStartYear,
                            userStartYear, "Yes", 0);
                } else {
                    userExpenses = CalculationEngine.retirementExpenseArray(userExpenses, spouseStartYear,
                            userStartYear, "No", 0);
                }
                final JSONArray userIncome = incomeJsons.getJSONArray("user_income");
                if (details.getMarital_status().equals("Yes")) {
                    spouseIncomeArray = incomeJson.getJSONArray("spouse_income");
                    combinedIncomeArray = incomeJson.getJSONArray("combined_income");
                } else {
                    combinedIncomeArray = incomeJsons.getJSONArray("user_income");
                }
                final JSONArray oldfillingExemtion = new JSONArray();
                final String[] out = available.getCreatedTs().split("/");
                final int registrationYear = Integer.parseInt(out[0]);
                final int noOfDependentfortax = user.getDependants() + available.getChildCount();
                int noOfExcemtion = 1 + available.getChildCount();
                year = incomeJson.getJSONArray("user_income").getJSONObject(0).getInt("year");
                if (available.getMarital_status().equals("Yes")) {
                    noOfExcemtion = noOfExcemtion + noOfDependentfortax - available.getChildCount() + 1;
                    if (available.getFilingStatus().equals("Head of Household")) {
                        noOfExcemtion = noOfExcemtion + noOfDependentfortax - available.getChildCount();
                    } else if (available.getFilingStatus().equals("Married Filing Separately")) {
                        noOfExcemtion = noOfExcemtion - 1;
                    } else if (available.getFilingStatus().equals("Qualified Widow")) {
                        noOfExcemtion = noOfExcemtion - 1;
                    }
                } else if (available.getFilingStatus().equals("Head of Household")) {
                    noOfExcemtion = noOfExcemtion + noOfDependentfortax - available.getChildCount();
                } else if (available.getFilingStatus().equals("Single")) {
                    noOfExcemtion = 1;
                }
                JSONArray childArray = new JSONArray();
                final JSONObject userDetailsFromDB = new JSONObject(jsonMapper.writeValueAsString(available));
                for (int i = 0; i < combinedIncomeArray.length(); i++) {
                    int noOfExcemtion1 = noOfExcemtion;
                    int kids = 0;
                    if (available.getChildCount() != 0 && !userDetailsFromDB.isNull("childs")) {
                        childArray = userDetailsFromDB.getJSONArray("childs");
                        for (int k = 0; k < available.getChildCount(); k++) {
                            if (year - (registrationYear
                                    - childArray.getJSONObject(k).getInt("age")) > CHILD_AGE_LIMIT) {
                                kids++;
                                if ((childArray.getJSONObject(k).getString("flag").equals("Yes") && year
                                        - (registrationYear - childArray.getJSONObject(k).getInt("age")) > HOURS)
                                        || (!childArray.getJSONObject(k).getString("flag").equals("Yes"))) {
                                    noOfExcemtion1 = noOfExcemtion1 - 1;
                                }
                            }
                        }
                    }
                    year++;
                    final JSONObject obj = new JSONObject();
                    obj.put("fillingStatus", available.getFilingStatus());
                    obj.put("noOfExcemtion", noOfExcemtion1);
                    obj.put("year", combinedIncomeArray.getJSONObject(i).getInt("year"));
                    oldfillingExemtion.put(obj);
                }

                JSONArray tax = incomeJsons.getJSONArray("tax");
                final JSONArray assests = incomeJsons.getJSONArray("assests");
                JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, null, combinedIncomeArray,
                        spouseIncomeArray, userExpenses, incomeJsons.getJSONArray("limits"),
                        available.getMarital_status(), assests, tax, available.get_id(), oldfillingExemtion,
                        user.getAge(), user.getSpouseAge(), 0, false, null, null, null, null, null, null, null, null,
                        false);
                String status = result.getString("status");
                if (status.equals("success")) {
                    final JSONArray assetsIncome = result.getJSONArray("assets");
                    final JSONArray taxIncome = result.getJSONArray("tax");
                    if (!userJSON.isNull("finplans") && userJSON.getJSONArray("finplans").length() != 0) {
                        final JSONArray finplanArry = userJSON.getJSONArray("finplans");

                        for (int k = 0; k < finplanArry.length(); k++) {
                            final FinPlan finplans = MongoDBConnection.finplancol.findOne("{_id:#}", finplanArry.get(k))
                                    .as(FinPlan.class);
                            if (finplans.getProfile_name().equals("constant_income")) {
                                final JSONObject userFinPlan = new JSONObject(jsonMapper.writeValueAsString(finplans));
                                JSONArray userExpense1 = userFinPlan.getJSONArray("userExpense");
                                JSONArray expenseObj = new JSONArray();
                                JSONObject expense = new JSONObject();
                                expense = userFinPlan.getJSONObject("expense");
                                if (!expense.isNull("housing_expense")) {
                                    expenseObj = userFinPlan.getJSONObject("expense").getJSONArray("housing_expense");
                                }
                                int finSpousestartYear = 0;
                                int finUserstartYear;
                                final RetirementGoal retirementObj = MongoDBConnection.goalcoll
                                        .findOne("{fin_id:#,goalType:#}", finplans.get_id(), "Retirement")
                                        .as(RetirementGoal.class);
                                if (retirementObj != null) {
                                    finUserstartYear = retirementObj.getRetirementAge();
                                    if (finplans.getMarital_status().equals("Yes")) {
                                        finSpousestartYear = retirementObj.getSpouseAge();
                                        userExpense1 = CalculationEngine.retirementExpenseArray(userExpense1,
                                                finSpousestartYear, finUserstartYear, "Yes", 0);
                                    } else {
                                        userExpense1 = CalculationEngine.retirementExpenseArray(userExpense1,
                                                finSpousestartYear, finUserstartYear, "No", 0);
                                    }
                                } else {
                                    if (finplans.getMarital_status().equals("Yes")) {
                                        userExpense1 = CalculationEngine.retirementExpenseArray(userExpense1,
                                                spouseStartYear, userStartYear, "Yes", 0);
                                    } else {
                                        userExpense1 = CalculationEngine.retirementExpenseArray(userExpense1,
                                                spouseStartYear, userStartYear, "No", 0);
                                    }
                                }
                                final IncomeProfile incomeProfTemp = MongoDBConnection.incomeProfileCol
                                        .findOne("{user_id:#,profile_name:#}", finplans.getUsr_id(), "constant_income")
                                        .as(IncomeProfile.class);
                                final JSONObject incomeJsonTemp = new JSONObject(
                                        jsonMapper.writeValueAsString(incomeProfTemp));
                                JSONArray combinedIncome = new JSONArray();

                                JSONArray spouseIncome = new JSONArray();

                                if (finplans.getMarital_status().equals("Yes")) {
                                    combinedIncome = incomeJsonTemp.getJSONArray("combined_income");
                                    spouseIncome = incomeJsonTemp.getJSONArray("spouse_income");
                                } else {
                                    combinedIncome = incomeJsonTemp.getJSONArray("user_income");
                                }
                                int spouseStartYearNew = 0;
                                userStartYear = 0;
                                if (retirementObj != null) {
                                    userStartYear = details.getBirthYear() + retirementObj.getRetirementAge();
                                    if (finplans.getMarital_status().equals("Yes")
                                            && details.getMarital_status().equals("No")) {
                                        spouseStartYearNew = finplans.getSpouseBirthYear()
                                                + retirementObj.getSpouseRetirementAge();
                                    } else if (details.getMarital_status().equals("Yes")) {
                                        spouseStartYearNew = details.getSpouseBirthYear()
                                                + retirementObj.getSpouseRetirementAge();
                                    }

                                } else {
                                    userStartYear = details.getBirthYear() + DEFAULT_RETIRMENT_AGE;
                                    if (finplans.getMarital_status().equals("Yes")
                                            && details.getMarital_status().equals("No")) {
                                        spouseStartYearNew = finplans.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                                    } else if (details.getMarital_status().equals("Yes")) {
                                        spouseStartYearNew = details.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE;
                                    }
                                }
                                final JSONObject retirementData = new JSONObject();
                                retirementData.put("spouseStartYear", spouseStartYearNew);
                                retirementData.put("userStartYear", userStartYear);

                                String emergencyType = null;
                                String monthsOfIncome = null;
                                String monthsOfExpense = null;
                                if (finplans.isEmergencyFundFlag()) {
                                    final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                                            .findOne("{fin_id:#,goalType:#}", finplans.get_id(), "Emergency Fund")
                                            .as(Emergencyfundmodel.class);
                                    emergencyType = emergencyObj.getTimePeriod();
                                    monthsOfIncome = emergencyObj.getMonthI();
                                    monthsOfExpense = emergencyObj.getMonthE();
                                }

                                result = CalculationEngine.sweepingOfMoney(userIncome, finplans.get_id(),
                                        combinedIncome, spouseIncome, userExpense1, userFinPlan.getJSONArray("limits"),
                                        finplans.getMarital_status(), userFinPlan.getJSONArray("assests"),
                                        userFinPlan.getJSONArray("tax"), userDetailsTemp.get_id(),
                                        userFinPlan.getJSONArray("fillingExemtion"), userDetails.getAge(),
                                        finplans.getSpouseAge(), finplans.getEmergencyFundAmt(),
                                        finplans.isEmergencyFundFlag(), userFinPlan.getJSONArray("deductions"),
                                        userFinPlan.getJSONArray("kidBirthYear"), retirementData, retirementObj,
                                        expenseObj, emergencyType, monthsOfIncome, monthsOfExpense,
                                        finplans.isRetirementFlag());
                                status = result.getString("status");
                                if (status.equals("success")) {
                                    final JSONArray assets = result.getJSONArray("assets");
                                    tax = result.getJSONArray("tax");
                                    MongoDBConnection.incomeProfileCol
                                    .update("{user_id:#,profile_name:#}", user.getUser_id(), "constant_income")
                                    .upsert().multi().with("{$set: {'userExpense':" + userExpenses + ",'tax':"
                                            + taxIncome + ",'assests':" + assetsIncome + "}}");
                                    MongoDBConnection.finplancol.update("{'_id': '" + finplanArry.get(k) + "'}")
                                    .upsert().multi().with("{$set: {'userExpense':" + userExpense1 + "}}");
                                    MongoDBConnection.finplancol.update("{_id:'" + finplanArry.get(k) + "'}").upsert()
                                    .multi().with("{$set: {'assests':" + assets + ",'tax':" + tax + "}}");
                                } else {
                                    flag = 1;
                                }
                            }
                        }
                    } else {
                        MongoDBConnection.incomeProfileCol
                        .update("{user_id:#,profile_name:#}", user.getUser_id(), "constant_income").upsert()
                        .multi().with("{$set: {'userExpense':" + userExpenses + ",'tax':" + taxIncome
                                + ",'assests':" + assetsIncome + "}}");
                    }
                } else {
                    flag = 1;
                }

                if (flag == 0) {
                    return jobject.put("status", "success");
                } else {
                    MongoDBConnection.userColl.update("{'_id': '" + user.getUser_id() + "'}").upsert().multi()
                    .with("{$set: {'cash':'" + cashPrevVal + "','taxableInvestments':'"
                            + taxableInvestmentsPreVal + "','nonTaxableInvestments':'" + nonTaxablePreVal
                            + "','user401k':" + user401kPrevVal + ",'userIRA':" + userIRAPrevVal
                            + ",'userRothIra':" + userRothIraPrevVal + ",'user529':" + user529PrevVal
                            + ",'spouse401k':" + spouse401kPrevVal + ",'spouseIRA':" + spouseIRAPrevVal
                            + ",'spouseRothIra':" + spouseRothIraPrevVal + ",'spousePlan529':"
                            + spousePlan529PrevVal + "}}");
                    return jobject.put("status", "fail");
                }
            } else if (formType.equals("getIncomeChart")) {
                IncomeProfile userAndSpouseIncome;
                userAndSpouseIncome = MongoDBConnection.incomeProfileCol
                        .findOne("{user_id:#,profile_name:#}", user.getUser_id(), user.getProfileName())
                        .as(IncomeProfile.class);
                final JSONObject userAndSpouseIncomeJson = new JSONObject(
                        jsonMapper.writeValueAsString(userAndSpouseIncome));
                final JSONArray incomeJsonArray = userAndSpouseIncomeJson.getJSONArray("user_income");
                if (details.getMarital_status().equals("Yes")) {
                    final JSONArray combinedIncomeJsonArray = userAndSpouseIncomeJson.getJSONArray("combined_income");
                    final JSONArray spouseIncomeJsonArray = userAndSpouseIncomeJson.getJSONArray("spouse_income");
                    jobject.put("spouse_income", spouseIncomeJsonArray);
                    jobject.put("combined_income", combinedIncomeJsonArray);
                }
                jobject.put("marital_status", details.getMarital_status());
                jobject.put("user_income", incomeJsonArray);
                return jobject.put("status", "success");
            } else {
                return jobject;
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject;
        }
    }

    // --------------------------------Changing the income
    public JSONObject changeIncome(final User user1) {
        final String formType = user1.getFormType();
        final String userId = user1.getUser_id();
        final int income = Integer.parseInt(user1.getIncomeEdit().toString());
        int currentYearIncome = Integer.parseInt(user1.getCurrentYearIncome().toString());
        final int year = Integer.parseInt(user1.getYearEdit().toString());
        final int endYear = Integer.parseInt(user1.getEndYear().toString());
        final int currentYear = Integer.parseInt(user1.getCurrentYear().toString());
        final String projection = user1.getProjection();
        final JSONObject jobject = new JSONObject();
        final User incomeToUpdate = MongoDBConnection.incomeProfileCol.findOne("{user_id:#}", userId).as(User.class);
        if (projection.equals("Yes")) {
            final int increment = (income - currentYearIncome) / (endYear - currentYear);
            for (int i = currentYear; i <= year; i++) {
                incomeToUpdate.getIncome().get(i).setValue(currentYearIncome);
                currentYearIncome = currentYearIncome + increment;
            }
        } else if (projection.equals("No")) {
            for (int i = currentYear; i <= year; i++) {
                incomeToUpdate.getIncome().get(i).setValue(income);
            }
        }
        for (int i = year; i <= endYear; i++) {
            incomeToUpdate.getIncome().get(i).setValue(income);
        }
        try {
            jobject.put("status", "fail");
            if (formType.equals("changeIncome")) {
                MongoDBConnection.incomeProfileCol.save(incomeToUpdate);
                jobject.put("status", "success");
                return jobject;
            } else {
                return jobject;
            }
        } catch (final Exception e) {
            return jobject;
        }
    }

    // --------------------------------Changing the income details
    public JSONObject changeIncome1(final User user1) {
        final String formType = user1.getFormType();
        final String userId = user1.getUser_id();
        final User details = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
        final JSONObject jobject = new JSONObject();
        try {
            jobject.put("status", "fail");
            if (formType.equals("modifyIncome")) {
                final JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(user1));
                final JSONArray income1 = userJson.getJSONArray("income");
                if (details.getMarital_status().equals("Yes")) {
                    jobject.put("status", "success");
                    final JSONArray income2 = userJson.getJSONArray("spouse_income");
                    final JSONArray combinedIncome = calCombinedIncome.calCombinedIncome(income1, income2);
                    MongoDBConnection.incomeProfileCol
                    .update("{'user_id': '" + userId + "','profile_name': 'constant_income'}")
                    .with("{$set: {'user_income':" + income1 + ",'spouse_income':" + income2
                            + ",'combined_income':" + combinedIncome + "}}");
                } else {
                    MongoDBConnection.incomeProfileCol
                    .update("{'user_id': '" + userId + "','profile_name': 'constant_income'}").upsert().multi()
                    .with("{$set: {'user_income':" + income1 + "}}");
                    jobject.put("status", "success");
                    return jobject;
                }
            } else {
                return jobject;
            }

        } catch (final Exception e) {
            e.printStackTrace();
            return jobject;
        }
        return jobject;
    }

    // ---------------------------------Creating a new financial
    @Override
    public JSONObject newFinPlan(final FinPlan finPlan) {
        final JSONObject systemLog = new JSONObject();
        final String formType = finPlan.getFormType();
        final String userId = finPlan.getUser_id();
        final User user = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
        final JSONObject jobject = new JSONObject();
        try {
            final JSONObject userColJson = new JSONObject(jsonMapper.writeValueAsString(user));
            jobject.put("status", "fail");
            if (formType.equals("createNewPlan")) {
                try {
                    // Searching the user id from counters collections
                    final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                            .findOne("{user_id:#,profile_name:#}", userId, finPlan.getProfile_name())
                            .as(IncomeProfile.class);
                    final JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
                    final JSONArray assests = userJson.getJSONArray("assests");
                    final JSONArray expense = userJson.getJSONArray("userExpense");
                    final JSONArray limits = userJson.getJSONArray("limits");
                    JSONArray equity = new JSONArray();
                    if (!userJson.isNull("equity")) {
                        equity = userJson.getJSONArray("equity");
                    }
                    final JSONArray deductions = new JSONArray();
                    JSONArray userIncome = new JSONArray();
                    JSONArray spouseIncome = new JSONArray();
                    JSONArray combinedIncome = new JSONArray();
                    final JSONObject expenseObj = new JSONObject();
                    final JSONArray goals = new JSONArray();
                    final int emergencyFundAmt = 0;
                    final boolean emergencyFundFlag = false;
                    final JSONArray kidBirthYear = new JSONArray();
                    double spouse401k = 0;
                    if (user.getMarital_status().equals("Yes")) {
                        spouse401k = 1;
                    }
                    final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                    final String finid = "fin" + count.getFin_id();
                    final BasicDBObject doc = new BasicDBObject("_id", finid).append("usr_name", user.getName())
                            .append("usr_id", userId).append("plan_name", finPlan.getPlan_name())
                            .append("profile_name", finPlan.getProfile_name())
                            .append("rentalExpenses", user.getRentalExpenses())
                            .append("monthlyExpenses", user.getMonthlyExpenses())
                            .append("created_ts", dateFormatType.format(dateCurrent)).append("user401k", 1)
                            .append("spouse401k", spouse401k);

                    final JSONArray fillingExemtion = new JSONArray();
                    final String[] out = user.getCreatedTs().split("/");
                    final int registrationYear = Integer.parseInt(out[0]);

                    String fillingStatus = "Single";
                    int year = userJson.getJSONArray("user_income").getJSONObject(0).getInt("year");
                    int spouseAge = 0;
                    int spouseBirthYear = 0;
                    userIncome = userJson.getJSONArray("user_income");
                    String maritalStatus;
                    if (user.getMarital_status().equals("Yes")
                            || (userJson.getJSONArray("spouse_income").length() > 0)) {
                        maritalStatus = "Yes";
                    } else {
                        maritalStatus = "No";
                    }
                    final JSONObject temjson = limits.getJSONObject(0);
                    for (int i = 0; i < assests.length(); i++) {

                        int noOfExcemtion = 1 + user.getChildCount();
                        if (user.getFilingStatus().equals("Head of Household")) {
                            noOfExcemtion = noOfExcemtion + user.getDependants() ;
                            fillingStatus = "Head of Household";
                        }
                        if (user.getMarital_status().equals("Yes")
                                || (userJson.getJSONArray("spouse_income").length() > 0
                                        && !userJson.isNull("spouse_income") && userJson.getJSONArray("spouse_income")
                                        .getJSONObject(i).getInt("value") != 0)) {
                            spouseAge = user.getSpouseAge();
                            spouseBirthYear = user.getSpouseBirthYear();
                            if (spouseAge == 0) {
                                spouseAge = user.getAge() - 1;
                                spouseBirthYear = user.getBirthYear() + 1;
                            }
                            noOfExcemtion = noOfExcemtion + 1;
                            fillingStatus = "Married Filing Jointly";
                            if (user.getFilingStatus().equals("Head of Household")) {
                                noOfExcemtion = noOfExcemtion + user.getDependants();
                                fillingStatus = "Head of Household";

                            } else if (user.getFilingStatus().equals("Married Filing Separately")) {
                                noOfExcemtion = noOfExcemtion - 1;
                                fillingStatus = "Married Filing Separately";

                            } else if (user.getFilingStatus().equals("Qualified Widow")) {
                                noOfExcemtion = noOfExcemtion - 1;
                                fillingStatus = "Qualified Widow";
                            }
                            spouseIncome = userJson.getJSONArray("spouse_income");
                            combinedIncome = userJson.getJSONArray("combined_income");

                        } else {
                            combinedIncome = userJson.getJSONArray("user_income");

                        }
                        final JSONObject fillingJson = new JSONObject();
                        final JSONObject deductionsobj = new JSONObject();
                        fillingJson.put("fillingStatus", fillingStatus);
                        fillingJson.put("year", year);
                        deductionsobj.put("year", year);
                        deductionsobj.put("saving", 0);
                        deductionsobj.put("taxable", 0);
                        deductionsobj.put("nontaxable", 0);
                        if (limits.length() <= i) {
                            limits.put(temjson);
                            limits.getJSONObject(i).put("taxable", 0);
                            limits.getJSONObject(i).put("saving", 0);
                        } else {
                            limits.getJSONObject(i).put("taxable", 0);
                            limits.getJSONObject(i).put("saving", 0);
                        }
                        int noOfExcemtion1 = noOfExcemtion;
                        if (user.getChildCount() != 0) {
                            final JSONArray childArray = userColJson.getJSONArray("childs");
                            for (int k = 0; k < user.getChildCount(); k++) {
                                if (year - (registrationYear
                                        - childArray.getJSONObject(k).getInt("age")) > CHILD_AGE_LIMIT) {
                                    if ((childArray.getJSONObject(k).getString("flag").equals("Yes") && year
                                            - (registrationYear - childArray.getJSONObject(k).getInt("age")) > HOURS)
                                            || (!childArray.getJSONObject(k).getString("flag").equals("Yes"))) {
                                        noOfExcemtion1 = noOfExcemtion1 - 1;
                                    }
                                }
                            }
                        }
                        fillingJson.put("noOfExcemtion", noOfExcemtion1);
                        if (noOfExcemtion1 <= 1) {
                            fillingJson.put("fillingStatus", "Single");
                        }
                        fillingJson.put("oldFilingStatus", fillingStatus);
                        fillingExemtion.put(fillingJson);
                        deductions.put(deductionsobj);
                        year++;
                    }

                    final JSONObject result = CalculationEngine.sweepingOfMoney(userIncome, null, combinedIncome,
                            spouseIncome, userJson.getJSONArray("userExpense"), limits, maritalStatus,
                            userJson.getJSONArray("assests"), userJson.getJSONArray("tax"), userId, fillingExemtion,
                            user.getAge(), spouseAge, 0, false, null, null, null, null, null, null, null, null, false);
                    if (result.getString("status").equals("success")) {
                        MongoDBConnection.finplancol.insert(doc);
                        MongoDBConnection.finplancol.update("{'_id': '" + finid + "'}").upsert().multi()
                        .with("{$set: {'assests':" + result.getJSONArray("assets") + ",'equity':" + equity
                                + ",'tax':" + result.getJSONArray("tax") + "," + "'userExpense':" + expense
                                + "," + "'fillingExemtion':" + fillingExemtion + "," + "'marital_status':'"
                                + maritalStatus + "'," + "'filingStatus':'" + user.getFilingStatus() + "',"
                                + "'limits':" + limits + "," + "'kidsGoalCount':" + 0 + "," + "'deductions':"
                                + deductions + "," + "'userRetirementAge':" + DEFAULT_RETIRMENT_AGE + "," + ""
                                + "'spouseRetirementAge':" + DEFAULT_RETIRMENT_AGE + "," + "'retirementFlag':"
                                + false + "," + "" + "'emergencyFundAmt':" + emergencyFundAmt + "," + ""
                                + "'emergencyFundFlag':" + emergencyFundFlag + "," + "" + "'kidBirthYear':"
                                + kidBirthYear + "," + "'expense':" + expenseObj + "," + "" + "'goals':" + goals
                                + "," + "'spouseAge':" + spouseAge + "," + "'spouseBirthYear':"
                                + spouseBirthYear + "}}");
                        MongoDBConnection.userColl.update("{'_id':#}", userId).upsert().multi()
                        .with("{$addToSet: {'finplans':'" + finid + "'}}");
                        MongoDBConnection.counterColl.update("{'fin_id':" + count.getFin_id() + "}")
                        .with("{$inc: {fin_id: 1}}");
                        jobject.put("finid", finid);
                        jobject.put("status", "success");
                        systemLog.put("type", "success");
                        systemLog.put("message", "Plan Created Successfully!!");
                        systemLog.put("userName", user.getName());
                        systemLog.put("user_id", userId);
                        systemLog.put("createdTs", dateFormatType.format(dateCurrent));
                    } else {
                        jobject.put("status", "fail");
                    }
                } catch (final Exception e) {
                    systemLog.put("type", "error");
                    systemLog.put("message", "Plan Creation Failed!!");
                    systemLog.put("userName", user.getName());
                    systemLog.put("user_id", userId);
                    systemLog.put("createdTs", dateFormatType.format(dateCurrent));
                    jobject.put("status", "fail");
                    e.printStackTrace();
                }
                return jobject;
            } else {
                return jobject;
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject;
        }
    }

    // ---------------------------------copy fin
    @Override
    public JSONObject copyFinPlan(final FinPlan finPlan) {
        final String userId = finPlan.getUser_id();
        final JSONObject jobject = new JSONObject();
        final String newPlanName = finPlan.getNewPlanName();
        final String oldPlanName = finPlan.getOldPlanName();
        try {
            final FinPlan finPlanDetails = MongoDBConnection.finplancol
                    .findOne("{usr_id:#,plan_name:#}", userId, oldPlanName).as(FinPlan.class);
            jobject.put("status", "fail");
            // -------------------copy finplan
            final Counters counts = MongoDBConnection.counterColl.findOne().as(Counters.class);
            final String finid = "fin" + counts.getFin_id();
            // Inserting the data in fincol collection
            finPlanDetails.set_id(finid);


            finPlanDetails.setPlan_name(newPlanName);
            MongoDBConnection.finplancol.insert(finPlanDetails);
            // update the counters collection "user_id" value
            MongoDBConnection.counterColl.update("{'fin_id':" + counts.getFin_id() + "}").with("{$inc: {fin_id: 1}}");
            jobject.put("finid", finid);
            // -------------------copy goals---------------------------------
            final JSONArray goalsArray = new JSONArray();
            JSONArray equity = new JSONArray();
            final JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(finPlanDetails));
            JSONArray houseGoalExpense = new JSONArray();
            //System.out.println("The value is "+userJson.getJSONObject("expense").get("housing_expense"));
            if(!userJson.getJSONObject("expense").get("housing_expense").equals(null))
            {
            //System.out.println("hello inside the income array");
            houseGoalExpense=userJson.getJSONObject("expense").getJSONArray("housing_expense");
            //System.out.println("goalExpense > > "+houseGoalExpense);
            }
            final JSONArray goalJsonArray = userJson.getJSONArray("goals");
            final List<String> collegeNames = new ArrayList<String>();
            for (int j = 0; j < goalJsonArray.length(); j++) {
                // -------find a goalid then copy that goal from db
                final String oldGoalId = (String) goalJsonArray.get(j);
                //// //////////////System.out.println(oldGoalId);
                final CopyPlanModel goalTypeInDb = MongoDBConnection.goalcoll.findOne("{_id:#}", oldGoalId)
                        .as(CopyPlanModel.class);
                final String goalType = goalTypeInDb.getGoalType();

                // copy goal from one plan to another plan
                if (goalType.equals("Vacation")) {
                    final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                    final ModelVacation goalsDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", oldGoalId)
                            .as(ModelVacation.class);
                    final String newGoalId = "goal" + count.getGoal_id();
                    goalsArray.put(newGoalId);
                    goalsDetails.set_id(newGoalId);
                    goalsDetails.setPlan_name(newPlanName);
                    goalsDetails.setFin_id(finid);
                    // --------insert goal into db-------------
                    MongoDBConnection.goalcoll.insert(goalsDetails);
                    // ---------increase goal counters-----------------
                    MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                    .with("{$inc: {goal_id: 1}}");
                } else if (goalType.equals("Emergency Fund")) {
                    final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                    final Emergencyfundmodel goalsDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", oldGoalId)
                            .as(Emergencyfundmodel.class);
                    final String newGoalId = "goal" + count.getGoal_id();
                    // //System.out.println("new goal id"+newGoalId);
                    goalsArray.put(newGoalId);
                    goalsDetails.set_id(newGoalId);
                    goalsDetails.setPlan_name(newPlanName);
                    goalsDetails.setFin_id(finid);
                    // --------insert goal into db-------------
                    MongoDBConnection.goalcoll.insert(goalsDetails);
                    // ---------increase goal counters-----------------
                    MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                    .with("{$inc: {goal_id: 1}}");
                } else if (goalType.equals("Retirement")) {
                    final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                    final RetirementGoal goalsDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", oldGoalId)
                            .as(RetirementGoal.class);
                    final String newGoalId = "goal" + count.getGoal_id();
                    goalsArray.put(newGoalId);
                    goalsDetails.set_id(newGoalId);
                    goalsDetails.setPlan_name(newPlanName);
                    goalsDetails.setFin_id(finid);
                    // --------insert goal into db-------------
                    MongoDBConnection.goalcoll.insert(goalsDetails);
                    // ---------increase goal counters-----------------
                    MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                    .with("{$inc: {goal_id: 1}}");
                } else if (goalType.equals("Home")) {
                    final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                    final Housegoalmodel goalsDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", oldGoalId)
                            .as(Housegoalmodel.class);
                    final String newGoalId = "goal" + count.getGoal_id();
                    goalsArray.put(newGoalId);
                    goalsDetails.set_id(newGoalId);
                    goalsDetails.setPlan_name(newPlanName);
                    goalsDetails.setFin_id(finid);

                    for(int k=0;k<houseGoalExpense.length();k++)
                    {
                        if(houseGoalExpense.getJSONObject(k).getString("_id").equals(oldGoalId))
                        {
                            houseGoalExpense.getJSONObject(k).put("_id",newGoalId);
                        }
                    }
                    // --------insert goal into db-------------
                    MongoDBConnection.goalcoll.insert(goalsDetails);
                    // ---------increase goal counters-----------------
                    MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                    .with("{$inc: {goal_id: 1}}");
                } else if (goalType.equals("Marriage")) {
                    final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                    final Marriagegoalmodel goalsDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", oldGoalId)
                            .as(Marriagegoalmodel.class);
                    final String newGoalId = "goal" + count.getGoal_id();
                    goalsArray.put(newGoalId);
                    goalsDetails.set_id(newGoalId);
                    goalsDetails.setPlan_name(newPlanName);
                    goalsDetails.setFin_id(finid);
                    // --------insert goal into db-------------
                    MongoDBConnection.goalcoll.insert(goalsDetails);
                    // ---------increase goal counters-----------------
                    MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                    .with("{$inc: {goal_id: 1}}");
                } else if (goalType.equals("Raising a kid")) {
                    final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                    final KidGoalModel goalsDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", oldGoalId)
                            .as(KidGoalModel.class);
                    final String newGoalId = "goal" + count.getGoal_id();
                    goalsArray.put(newGoalId);
                    goalsDetails.set_id(newGoalId);
                    goalsDetails.setPlan_name(newPlanName);
                    goalsDetails.setFin_id(finid);

                    // --------insert goal into db-------------

                    // ---------increase goal counters-----------------
                    MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                    .with("{$inc: {goal_id: 1}}");

                    if(goalsDetails.getDependentCollageGoal()!=null) {


                        final Counters count1 = MongoDBConnection.counterColl.findOne().as(Counters.class);
                        final GoalCollegeEducation goalsDetails1 = MongoDBConnection.goalcoll.findOne("{_id:#}", goalsDetails.getDependentCollageGoal())
                                .as(GoalCollegeEducation.class);

                        final String newGoalId1 = "goal" + count1.getGoal_id();
                        goalsDetails.setDependentCollageGoal(newGoalId1);
                        goalsArray.put(newGoalId1);
                        goalsDetails1.set_id(newGoalId1);
                        goalsDetails1.setPlan_name(newPlanName);
                        goalsDetails1.setFin_id(finid);
                        // --------insert goal into db-------------
                        MongoDBConnection.goalcoll.insert(goalsDetails1);
                        collegeNames.add(goalsDetails1.getGoalname());
                        //System.out.println("collegeNames"+collegeNames);
                        // ---------increase goal counters-----------------
                        MongoDBConnection.counterColl.update("{'goal_id':" + count1.getGoal_id() + "}")
                        .with("{$inc: {goal_id: 1}}");
                    }

                    MongoDBConnection.goalcoll.insert(goalsDetails);
                    // //////////////System.out.println("goal"+count.getGoal_id());
                } else if (goalType.equals("College Education")&&!collegeNames.contains(goalTypeInDb.getGoalname())) {
                    //System.out.println("inside the college education copy");
                    final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                    final GoalCollegeEducation goalsDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", oldGoalId)
                            .as(GoalCollegeEducation.class);
                    final String newGoalId = "goal" + count.getGoal_id();
                    goalsArray.put(newGoalId);
                    goalsDetails.set_id(newGoalId);
                    goalsDetails.setPlan_name(newPlanName);
                    goalsDetails.setFin_id(finid);
                    // --------insert goal into db-------------
                    MongoDBConnection.goalcoll.insert(goalsDetails);
                    // ---------increase goal counters-----------------
                    MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                    .with("{$inc: {goal_id: 1}}");
                    // ////////////////System.out.println("goal"+count.getGoal_id());
                } else if (goalType.equals("Car")) {
                    final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                    final Cargoalmodel goalsDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", oldGoalId)
                            .as(Cargoalmodel.class);
                    final String newGoalId = "goal" + count.getGoal_id();
                    goalsArray.put(newGoalId);
                    goalsDetails.set_id(newGoalId);
                    goalsDetails.setPlan_name(newPlanName);
                    goalsDetails.setFin_id(finid);
                    // --------insert goal into db-------------
                    MongoDBConnection.goalcoll.insert(goalsDetails);
                    // ---------increase goal counters-----------------
                    MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                    .with("{$inc: {goal_id: 1}}");
                } else {
                    if(!goalType.equals("College Education"))  {
                    final Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
                    final Customizedgoalmodel goalsDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", oldGoalId)
                            .as(Customizedgoalmodel.class);
                    final String newGoalId = "goal" + count.getGoal_id();
                    goalsArray.put(newGoalId);
                    goalsDetails.set_id(newGoalId);
                    goalsDetails.setPlan_name(newPlanName);
                    goalsDetails.setFin_id(finid);
                    // --------insert goal into db-------------
                    MongoDBConnection.goalcoll.insert(goalsDetails);
                    // ---------increase goal counters-----------------
                    MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}")
                    .with("{$inc: {goal_id: 1}}");
                    }
                }
            }
            if(!userJson.isNull("equity"))
            {
                equity=userJson.getJSONArray("equity");
            }
            // //System.out.println("houseGoalExpense "+houseGoalExpense);
            MongoDBConnection.finplancol.update("{usr_id:#,plan_name:#}", userId, newPlanName)
            .with("{$set: {'goals':" + goalsArray + ",'equity':"+equity+"}}");
            if(houseGoalExpense!=null)
            {
                MongoDBConnection.finplancol.update("{usr_id:#,plan_name:#}", userId, newPlanName)
                .with("{$set: {'expense.housing_expense':" + houseGoalExpense + "}}");
            }
            MongoDBConnection.userColl.update("{'_id':#}", userId).upsert().multi()
            .with("{$addToSet: {'finplans':'" + finid + "'}}");
            jobject.put("status", "success");
            jobject.put("plan_name", newPlanName);
            return jobject;
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject;
        }
    }

    // ---------------------------------Show all financial
    @Override
    public JSONObject deleteFinPlan(final FinPlan finPlan) {
        final JSONObject restResponse = new JSONObject();
        try {
            restResponse.put("status", "fail");
            String userId = finPlan.getUser_id();
            if (userId == null) {
                userId = finPlan.getUsr_id();
            }

            final FinPlan finPlanData = MongoDBConnection.finplancol
                    .findOne("{plan_name:#,usr_id:#}", finPlan.getPlan_name(), userId).as(FinPlan.class);
            User userColData = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
            ////////// //System.out.println("Mahi: retirement
            ////////// flag"+finPlanData.isRetirementFlag());
            final JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finPlanData));
            if (!finPlanJson.isNull("expense")) {
                JSONObject expense = new JSONObject();
                expense = finPlanJson.getJSONObject("expense");
                JSONArray expenseObj = new JSONArray();
                if (!expense.isNull("housing_expense")) {
                    expenseObj = finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
                    int spouseAge;
                    // ////////System.out.println("user_id.."+user_id+"planname.."+finPlanData.getPlan_name()+finPlanData.getProfile_name());
                    final IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol
                            .findOne("{user_id:#,profile_name:#}", userId, finPlanData.getProfile_name())
                            .as(IncomeProfile.class);
                    final JSONObject incomeProfileJson = new JSONObject(
                            jsonMapper.writeValueAsString(incomeProfileDetails));
                    final JSONArray userIncome = incomeProfileJson.getJSONArray("user_income");
                    JSONArray spouseIncomeArray = new JSONArray();
                    JSONArray combinedIncome = new JSONArray();
                    for (int j = 0; j < expenseObj.length(); j++) {
                        final String frequency = expenseObj.getJSONObject(j).getString("frequency");
                        if (frequency.equals("Turn first house into a rental")) {
                            final double profitOrLossCal = expenseObj.getJSONObject(j).getDouble("profitOrLossCal");
                            final String rentalActivity = expenseObj.getJSONObject(j).getString("rentalActivity");
                            final double deprectionAmount=expenseObj.getJSONObject(j).getDouble("deprectionAmount");
                            final double rentalIncomePerYear=expenseObj.getJSONObject(j).getDouble("rentalIncomePerYear");
                            for (int i = 0; i < userIncome.length(); i++) {
                                if (userIncome.getJSONObject(i).getInt("year") >= expenseObj.getJSONObject(j)
                                        .getInt("startYear")
                                        && userIncome.getJSONObject(i).getInt(
                                                "year") <= (expenseObj.getJSONObject(j).getInt("startYear") + THIRTY)) {
                                    /*  if (rentalActivity.equals("yes") || rentalActivity.equals("true")) {
                                        if (profitOrLossCal > 0) {
                                            userIncome.getJSONObject(i).put("value",
                                                    userIncome.getJSONObject(i).getDouble("value") - profitOrLossCal);
                                       } else if (profitOrLossCal < -TWENTY_FIVE_THOUSAND) {
                                            userIncome.getJSONObject(i).put("value",
                                                    userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal);
                                        } else {
                                            userIncome.getJSONObject(i).put("value",
                                                    userIncome.getJSONObject(i).getDouble("value")
                                                    + TWENTY_FIVE_THOUSAND);
                                        }
                                    }
                                    if (rentalActivity.equals("no") || rentalActivity.equals("false")) {
                                        if (profitOrLossCal > 0) {
                                            userIncome.getJSONObject(i).put("value",
                                                    userIncome.getJSONObject(i).getDouble("value") + profitOrLossCal);
                                        }
                                    }*/
                                    userIncome.getJSONObject(i).put("value",
                                            userIncome.getJSONObject(i).getDouble("value") - rentalIncomePerYear+deprectionAmount);
                                }
                            }

                        }
                    }
                    final String maritalStatus = finPlanData.getMarital_status();
                    if (maritalStatus.equals("Yes")) {
                        spouseIncomeArray = incomeProfileJson.getJSONArray("spouse_income");
                        final IncomeProfileImpl calCombinedIncomeArr = new IncomeProfileImpl();
                        combinedIncome = calCombinedIncomeArr.calCombinedIncome(userIncome, spouseIncomeArray);
                        spouseAge = finPlan.getSpouseAge();
                    } else {
                        combinedIncome = userIncome;
                        spouseAge = 0;
                    }
                    MongoDBConnection.incomeProfileCol
                    .update("{'user_id': '" + userId + "','profile_name':'" + finPlanData.getProfile_name()
                    + "'}")
                    .upsert().multi().with("{$set: {'user_income':" + userIncome + ",'spouse_income':"
                            + spouseIncomeArray + ",'combined_income':" + combinedIncome + "}}");
                }
            }

            if (finPlanData.isRetirementFlag()) {
                deleteGoal(finPlanData);
                // userColData.setRetirementFlag(0);
            }
            MongoDBConnection.goalcoll.remove("{plan_name:#,user_id:#}", finPlan.getPlan_name(), userId);
            MongoDBConnection.finplancol.remove("{plan_name:#,usr_id:#}", finPlan.getPlan_name(), userId);
            if (userColData == null) {
                userColData = MongoDBConnection.userColl.findOne("{_id:#}", userId).as(User.class);
            }

            final JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(userColData));
            final JSONArray planJsonArray = userJson.getJSONArray("finplans");
            final JSONArray planArray = new JSONArray();
            for (int j = 0; j < planJsonArray.length(); j++) {

                final String finId = (String) planJsonArray.get(j);
                if (!finId.equals(finPlanData.get_id())) {
                    planArray.put(finId);
                } else {
                    continue;
                }
            }

            MongoDBConnection.userColl.update("{'_id':#}", userId).upsert().multi()
            .with("{$set: {'finplans':" + planArray + "}}");

            restResponse.put("status", "success");
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return restResponse;
    }

    // ----------------------------------------------------------------------------------
    @Override
    public JSONObject showPlans(final FinPlan finPlan) {
        int marriageFlag = 0;
        final JSONObject jobject = new JSONObject();
        try {
            final String formType = finPlan.getFormType();
            jobject.put("status", "fail");
            if (formType.equals("showPlan")) {
                // Searching the user id from counters collections
                final User details = MongoDBConnection.userColl.findOne("{_id:#}", finPlan.getUser_id()).as(User.class);
                jobject.put("userName", details.getName());
                jobject.put("completedStatus", details.getCompletedFlag());
                jobject.put("usr_name", finPlan.getUsr_name());
                int i = 0;
                final MongoCursor<FinPlan> cursor = MongoDBConnection.finplancol
                        .find("{usr_id:#}", finPlan.getUser_id()).as(FinPlan.class);
                final JSONArray pln = new JSONArray();
                final JSONArray incomeProfiles = new JSONArray();
                while (cursor.hasNext()) {
                    i = i + 1;
                    final FinPlan fetch = cursor.next();
                    pln.put(fetch.getPlan_name());
                    incomeProfiles.put(fetch.getProfile_name());
                }
                cursor.close();
                jobject.put("Plans", pln);
                if (pln.length() > 0) {
                    jobject.put("plan_name", pln.getString(0));
                } else {
                    jobject.put("plan_name", "");
                }
                jobject.put("incomeProfile", incomeProfiles);
                IncomeProfile userAndSpouseIncome;
                jobject.put("Planscount", i);
                jobject.put("status", "success");
                /*----------------------------------------getting income profile-------------------------------------------*/
                if (i == 0) {
                    userAndSpouseIncome = MongoDBConnection.incomeProfileCol
                            .findOne("{user_id:#,profile_name:#}", finPlan.getUser_id(), "constant_income")
                            .as(IncomeProfile.class);
                } else {
                    final FinPlan profileNmaeFetch = MongoDBConnection.finplancol
                            .findOne("{usr_id:#,plan_name:#}", finPlan.getUser_id(), pln.getString(0))
                            .as(FinPlan.class);
                    final String profileName = profileNmaeFetch.getProfile_name();
                    if (profileName.contains("marriage_income_profile")
                            || profileNmaeFetch.getMarital_status().equals("Yes")) {
                        marriageFlag = 1;
                    } else {
                        marriageFlag = 0;
                    }
                    userAndSpouseIncome = MongoDBConnection.incomeProfileCol
                            .findOne("{user_id:#,profile_name:#}", finPlan.getUser_id(), profileName)
                            .as(IncomeProfile.class);
                }
                jobject.put("editExpenseFlag", userAndSpouseIncome.getEditExpenseFlag());
                final JSONObject userAndSpouseIncomeJson = new JSONObject(
                        jsonMapper.writeValueAsString(userAndSpouseIncome));
                final MongoCursor<IncomeProfile> incomeProfileForOnload = MongoDBConnection.incomeProfileCol
                        .find("{user_id:#}", finPlan.getUser_id()).as(IncomeProfile.class);
                final JSONArray incomeProfilesOfUser = new JSONArray();
                while (incomeProfileForOnload.hasNext()) {
                    final IncomeProfile incomeProfileDocument = incomeProfileForOnload.next();
                    incomeProfilesOfUser.put(incomeProfileDocument.getProfile_name());
                    jobject.put("income_profiles", incomeProfilesOfUser);
                }
                incomeProfileForOnload.close();
                final JSONArray incomeJsonArray = userAndSpouseIncomeJson.getJSONArray("user_income");

                final JSONArray userPlotArray = userAndSpouseIncomeJson.getJSONArray("userPlot");
                jobject.put("userPlot", userPlotArray);

                if (details.getMarital_status().equals("Yes") || marriageFlag == 1) {
                    jobject.put("spousebirthYear", details.getSpouseBirthYear());
                    final JSONArray combinedIncomeJsonArray = userAndSpouseIncomeJson.getJSONArray("combined_income");
                    jobject.put("combined_income", combinedIncomeJsonArray);
                    final JSONArray spousePlotArray = userAndSpouseIncomeJson.getJSONArray("spousePlot");
                    jobject.put("spousePlot", spousePlotArray);
                }
                final JSONArray taxJsonArray = userAndSpouseIncomeJson.getJSONArray("tax");
                jobject.put("income", incomeJsonArray);

                // returning the tax from the income profile collection
                jobject.put("tax", taxJsonArray);
                if (marriageFlag == 1) {
                    jobject.put("marital_status", "Yes");
                } else {
                    jobject.put("marital_status", details.getMarital_status());
                }
                JSONArray spouseIncomeJsonArray = null;
                FinPlan finPlanDetails = null;
                IncomeProfile incomeDetails = null;
                if (i == 0) {
                    incomeDetails = MongoDBConnection.incomeProfileCol
                            .findOne("{user_id:#,profile_name:#}", finPlan.getUser_id(), "constant_income")
                            .as(IncomeProfile.class);
                } else {
                    finPlanDetails = MongoDBConnection.finplancol
                            .findOne("{usr_id:#,plan_name:#}", finPlan.getUser_id(), pln.getString(0))
                            .as(FinPlan.class);
                }
                if (finPlanDetails != null) {
                    final JSONObject assetsJson = new JSONObject(jsonMapper.writeValueAsString(finPlanDetails));
                    final JSONArray assests = assetsJson.getJSONArray("assests");
                    final JSONArray tax = assetsJson.getJSONArray("tax");
                    jobject.put("tax", tax);
                    jobject.put("assests", assests);
                    final JSONArray userExpense = assetsJson.getJSONArray("userExpense");
                    jobject.put("userExpense", userExpense);
                } else {
                    final JSONObject assetsJson = new JSONObject(jsonMapper.writeValueAsString(incomeDetails));
                    final JSONArray assests = assetsJson.getJSONArray("assests");
                    jobject.put("assests", assests);
                    final JSONArray tax = assetsJson.getJSONArray("tax");
                    jobject.put("tax", tax);
                    final JSONArray userExpense = assetsJson.getJSONArray("userExpense");
                    jobject.put("userExpense", userExpense);
                }
                if (details.getMarital_status().equals("Yes") || marriageFlag == 1) {
                    if (!userAndSpouseIncomeJson.isNull("spouse_income")) {
                        spouseIncomeJsonArray = userAndSpouseIncomeJson.getJSONArray("spouse_income");
                        jobject.put("spouseIncome", spouseIncomeJsonArray);
                    }
                }

                jobject.put("income", incomeJsonArray);
                jobject.put("birthYear", details.getBirthYear());
                jobject.put("age", details.getAge());
                jobject.put("investmentPortFolio", details.getInvestmentPortFolio());
                jobject.put("riskScore", details.getRiskScore());
                return jobject;
            } else {
                return jobject;
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject;
        }
    }

    // ---------------------------------Show all goals in financial
    @Override
    public JSONObject showGoals(final FinPlan finPlan) {
        final String formType = finPlan.getFormType();
        final JSONObject jobject = new JSONObject();
        try {
            jobject.put("status", "fail");
            if (formType.equals("fetchPlanDetails")) {
                JSONArray goalIDarray = new JSONArray();
                JSONArray goalarray = new JSONArray();
                final MongoCursor<RetirementGoal> collegeGoalCountCursor = MongoDBConnection.goalcoll
                        .find("{goalType:#,plan_name:#,user_id:#}", "College Education", finPlan.getPlan_name(),
                                finPlan.getUser_id())
                        .as(RetirementGoal.class);
                jobject.put("collegeGoalCount", collegeGoalCountCursor.count());
                final MongoCursor<RetirementGoal> kidGoalCountCursor = MongoDBConnection.goalcoll
                        .find("{goalType:#,plan_name:#,user_id:#}", "Raising a kid", finPlan.getPlan_name(),
                                finPlan.getUser_id())
                        .as(RetirementGoal.class);
                jobject.put("kidGoalCount", kidGoalCountCursor.count());
                kidGoalCountCursor.close();
                collegeGoalCountCursor.close();
                final FinPlan details = MongoDBConnection.finplancol
                        .findOne("{plan_name:#,usr_id:#}", finPlan.getPlan_name(), finPlan.getUser_id())
                        .as(FinPlan.class);

                IncomeProfile incomeDetails = null;
                if (details != null) {
                    jobject.put("finIncomeProfile", details.getProfile_name());
                    incomeDetails = MongoDBConnection.incomeProfileCol
                            .findOne("{user_id:#,profile_name:#}", finPlan.getUser_id(), details.getProfile_name())
                            .as(IncomeProfile.class);
                    final JSONObject incomeUserSpouseJson = new JSONObject(
                            jsonMapper.writeValueAsString(incomeDetails));
                    final JSONObject finJson = new JSONObject(jsonMapper.writeValueAsString(details));
                    final JSONArray userIncomeProfile = incomeUserSpouseJson.getJSONArray("user_income");
                    jobject.put("userIncomeProfile", userIncomeProfile);
                    jobject.put("equity", finJson.getJSONArray("equity"));
                    jobject.put("marital_status", details.getMarital_status());
                    final JSONArray userPlotArray = incomeUserSpouseJson.getJSONArray("userPlot");

                    jobject.put("userPlot", userPlotArray);
                    if (details.getMarital_status().equals("Yes")) {
                        final JSONArray spouseIncomeProfile = incomeUserSpouseJson.getJSONArray("spouse_income");
                        final JSONArray combinedIncomeProfile = incomeUserSpouseJson.getJSONArray("combined_income");
                        jobject.put("spouseIncomeProfile", spouseIncomeProfile);
                        jobject.put("combinedIncomeProfile", combinedIncomeProfile);
                        final JSONArray spousePlotArray = incomeUserSpouseJson.getJSONArray("userPlot");
                        jobject.put("spousePlot", spousePlotArray);
                    }
                    final JSONObject assetsJson = new JSONObject(jsonMapper.writeValueAsString(details));
                    final JSONArray assests = assetsJson.getJSONArray("assests");
                    jobject.put("assests", assests);
                    final JSONArray tax = assetsJson.getJSONArray("tax");
                    jobject.put("tax", tax);
                    final JSONArray userExpense = assetsJson.getJSONArray("userExpense");
                    jobject.put("userExpense", userExpense);
                } else {
                    incomeDetails = MongoDBConnection.incomeProfileCol
                            .findOne("{user_id:#,profile_name:#}", finPlan.getUser_id(), "constant_income")
                            .as(IncomeProfile.class);
                    final JSONObject assetsJson = new JSONObject(jsonMapper.writeValueAsString(incomeDetails));
                    final JSONArray assests = assetsJson.getJSONArray("assests");

                    final JSONArray tax = assetsJson.getJSONArray("tax");
                    jobject.put("tax", tax);
                    jobject.put("assests", assests);
                    final JSONArray userExpense = assetsJson.getJSONArray("userExpense");
                    jobject.put("userExpense", userExpense);
                    jobject.put("equity", assetsJson.getJSONArray("equity"));
                }
                jobject.put("editExpenseFlag", incomeDetails.getEditExpenseFlag());
                jobject.put("housingExpense", incomeDetails.getHousingExpense());
                jobject.put("nonHousingExpense", incomeDetails.getNonHousingExpense());
            //    //System.out.println("details ....."+details.getGoals()[0]);
                if (details.getGoals() == null) {
                    jobject.put("Goals", "noGoals");
                    jobject.put("status", "success");
                    return jobject;
                } else {
                    ////System.out.println("bala%%%%"+details.getGoals().length);
                    String temp = "";
                    int flag = 0;
                    int count = 0;
                    for (int i = 0; i < details.getGoals().length; i++) {
                        goalIDarray.put(details.getGoals()[i]);

                        final RetirementGoal detailsGoals = MongoDBConnection.goalcoll
                                .findOne("{_id:#}", details.getGoals()[i]).as(RetirementGoal.class);
                        if (detailsGoals != null) {
                            if (detailsGoals.getGoalType().equals("Raising a kid")) {
                                goalarray.put(detailsGoals.getGoalname());
                            } else if (detailsGoals.getGoalType().equals("College Education")) {
                                goalarray.put(detailsGoals.getGoalname());
                            } else if (detailsGoals.getGoalType().equals("Home")
                                    && detailsGoals.getFrequency().equals("firstHouse")) {
                                temp = detailsGoals.get_id();
                                count++;
                                goalarray.put(detailsGoals.getGoalType());
                            } else if (detailsGoals.getGoalType().equals("Home")
                                    && detailsGoals.getFrequency().equals("Replace first house")) {
                                flag = 1;
                                count++;
                                goalarray.put(detailsGoals.getGoalType());
                            } else {
                                goalarray.put(detailsGoals.getGoalType());
                            }
                            if (detailsGoals.getGoalType().equals("Retirement")) {
                                jobject.put("userRetirementAge", detailsGoals.getRetirementAge());
                                jobject.put("retirementFlag", details.isRetirementFlag());
                                if (details.getMarital_status().equals("Yes")) {
                                    jobject.put("spouseRetirementAge",
                                            detailsGoals.getUserSelectedSpouseRetirementAge());
                                }
                            }
                            if (detailsGoals.getGoalType().equals("Marriage")) {
                                jobject.put("MarriageYear", detailsGoals.getMarriageYear());
                                jobject.put("spouceAge", detailsGoals.getSpouseAge());
                                jobject.put("spouceBirthYear", details.getSpouseBirthYear());
                            }
                        }
                    }

                    if (flag == 1 && count != 1) {
                        final List<String> list = new ArrayList<String>();
                        final List<String> listGoalName = new ArrayList<String>();
                        for (int i = 0; i < goalIDarray.length(); i++) {
                            list.add(goalIDarray.get(i).toString());
                            listGoalName.add(goalarray.get(i).toString());
                        }
                        final int firstHouse = list.indexOf(temp);
                        list.remove(temp);
                        listGoalName.remove(firstHouse);
                        goalIDarray = new JSONArray(list);
                        goalarray = new JSONArray(listGoalName);
                    }
                    jobject.put("Goals", goalarray);
                    jobject.put("GoalIds", goalIDarray);

                    if (details != null) {
                        final JSONObject expenseJson = new JSONObject(
                                jsonMapper.writeValueAsString(details.getExpense()));
                        if (!expenseJson.isNull("kid_expense")) {
                            jobject.put("kid_expense", expenseJson.getJSONArray("kid_expense"));
                        }
                        if (!expenseJson.isNull("college_expense")) {
                            jobject.put("college_expense", expenseJson.getJSONArray("college_expense"));
                        }
                        if (!expenseJson.isNull("customized_expense")) {
                            jobject.put("customized_expense", expenseJson.getJSONArray("customized_expense"));
                        }
                        jobject.put("expenses", expenseJson);
                    }
                    jobject.put("status", "success");
                }
            } else {
                return jobject;
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject;
        }
        return jobject;
    }

    // ---------------------------------Show all goals in financial
    @Override
    public JSONObject showGoalStatus(final FinPlan finPlan) {
        final String formType = finPlan.getFormType();
        final JSONObject jobject = new JSONObject();
        try {
            jobject.put("status", "fail");
            if (formType.equals("fetchGoalStatus")) {
                final JSONArray goalIDarray = new JSONArray();
                final JSONArray goalarray = new JSONArray();
                final JSONArray goalStatusarray = new JSONArray();
                final MongoCursor<RetirementGoal> collegeGoalCountCursor = MongoDBConnection.goalcoll
                        .find("{goalType:#,plan_name:#,user_id:#}", "College Education", finPlan.getPlan_name(),
                                finPlan.getUser_id())
                        .as(RetirementGoal.class);
                final MongoCursor<RetirementGoal> kidGoalCountCursor = MongoDBConnection.goalcoll
                        .find("{goalType:#,plan_name:#,user_id:#}", "Raising a kid", finPlan.getPlan_name(),
                                finPlan.getUser_id())
                        .as(RetirementGoal.class);
                jobject.put("kidGoalCount", kidGoalCountCursor.count());
                collegeGoalCountCursor.close();
                kidGoalCountCursor.close();
                final FinPlan details = MongoDBConnection.finplancol
                        .findOne("{plan_name:#,usr_id:#}", finPlan.getPlan_name(), finPlan.getUser_id())
                        .as(FinPlan.class);
                jobject.put("collegeGoalCount", details.getCollegeGoalCount());
                final User details1 = MongoDBConnection.userColl.findOne("{_id:#}", finPlan.getUser_id())
                        .as(User.class);
                jobject.put("kidsCount", details1.getChildCount());
                jobject.put("maritalstatus", details1.getMarital_status());
                jobject.put("kidsCountFinplan", details.getKidsGoalCount());
                if (details.getGoals() == null) {
                    jobject.put("Goals", "noGoals");
                    jobject.put("status", "success");
                    return jobject;
                } else {
                    for (int i = 0; i < details.getGoals().length; i++) {
                        goalIDarray.put(details.getGoals()[i]);
                        final RetirementGoal detailsGoals = MongoDBConnection.goalcoll
                                .findOne("{_id:#}", details.getGoals()[i]).as(RetirementGoal.class);
                        goalarray.put(detailsGoals.getGoalType());
                        goalStatusarray.put(detailsGoals.getCompletedStatus());
                    }
                    jobject.put("Goals", goalarray);
                    jobject.put("GoalIds", goalIDarray);
                    jobject.put("GoalStatus", goalStatusarray);
                    jobject.put("status", "success");
                }
            } else {
                return jobject;
            }
        } catch (final Exception e) {
            return jobject;
        }
        return jobject;
    }

    // ----------------------------------------------------------------------------------------------------
    @Override
    public JSONObject retirementGoal(final RetirementGoal goal) {
        final JSONObject jobject = new JSONObject();
        return jobject;
    }

    // -----------------------------------------------------------------------------------------
    @Override
    public void updateUser(final User user) {
        final int index = users.indexOf(user);
        users.set(index, user);
    }

    @Override
    public void deleteUserById(final String id) {
        for (final Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
            final User user = iterator.next();
            if (user.get_id().equals(id)) {
                iterator.remove();
            }
        }
    }

    @Override
    public boolean isUserExist(final String email) {
        final Register checkemail = MongoDBConnection.userColl.findOne("{email:#}", email).as(Register.class);
        if (checkemail != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isPlanExist(final String planName, final String userid) {
        final FinPlan checkplan = MongoDBConnection.finplancol.findOne("{plan_name:#,usr_id:#}", planName, userid)
                .as(FinPlan.class);
        if (checkplan != null) {
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        final User login = MongoDBConnection.userColl.findOne().as(User.class);
        return (List<User>) login;
    }

    @Override
    public void deleteAllUsers() {
        users.clear();
    }

    @Override
    public User findByName(final String name) {
        for (final User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User checkLogin(final String email, final String password) {

        try {
            final User login = MongoDBConnection.userColl.findOne("{password:#,email:#}", password, email)
                    .as(User.class);
            if (login != null) {
                final String pwd = login.getPassword();
                if (pwd.equals(password)) {
                    return login;
                }
            } else {
                return login;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*------------------------------------- Password Encryption Method---------------------------------------------*/
    @Override
    public String encryptPassword(final String password) {
        final sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        Cipher encryptCipher;
        Cipher decryptCipher;
        java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE());
        final char[] pass = "A".toCharArray();
        final byte[] salt = { XA3, X21, X24, X2C, XF2, XD2, X3E, X19 };
        String encoded = null;
        try {
            final PBEParameterSpec ps = new javax.crypto.spec.PBEParameterSpec(salt, TWENTY);
            final SecretKeyFactory kf = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            final SecretKey k = kf.generateSecret(new javax.crypto.spec.PBEKeySpec(pass));
            encryptCipher = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, k, ps);
            decryptCipher = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE, k, ps);
            final byte[] utf8 = password.getBytes();
            final byte[] enc = encryptCipher.doFinal(utf8);
            encoded = encoder.encode(enc);
        } catch (final Exception e) {
            throw new SecurityException("Could not encrypt: " + e.getMessage());
        }
        return encoded;

    }

    /*------------------------------------- Password Decryption Method---------------------------------------------*/
    public String decryptPassword(final String password) {
        final sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        Cipher encryptCipher;
        Cipher decryptCipher;
        java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE());
        final char[] pass = "A".toCharArray();
        final byte[] salt = { XA3, X21, X24, X2C, XF2, XD2, X3E, X19 };
        String decoded = null;

        try {
            final PBEParameterSpec ps = new javax.crypto.spec.PBEParameterSpec(salt, TWENTY);
            final SecretKeyFactory kf = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            final SecretKey k = kf.generateSecret(new javax.crypto.spec.PBEKeySpec(pass));
            encryptCipher = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, k, ps);
            decryptCipher = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE, k, ps);
            final byte[] dec = decoder.decodeBuffer(password);
            final byte[] utf8 = decryptCipher.doFinal(dec);
            decoded = new String(utf8, "UTF8");

        } catch (final Exception e) {
            throw new SecurityException("Could not decrypt: " + e.getMessage());
        }
        return decoded;
    }

    // ----------------------delete goal--------------------------
    @Override
    public JSONObject deleteGoal(final FinPlan finPlan) {

        String userId = finPlan.getUser_id();
        if (userId == null) {
            userId = finPlan.getUsr_id();
        }
        final JSONObject jobject = new JSONObject();

        try {
            FinPlan finPlanDetails = null;
            jobject.put("status", "fail");
            if (finPlan.get_id() == null) {
                finPlanDetails = MongoDBConnection.finplancol
                        .findOne("{usr_id:#,plan_name:#}", userId, finPlan.getPlan_name()).as(FinPlan.class);
            } else {
                finPlanDetails = finPlan;
            }
            final String profileName = finPlanDetails.getProfile_name();
            final JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(finPlanDetails));
            final JSONArray goalJsonArray = userJson.getJSONArray("goals");
            final JSONArray goalsArray = new JSONArray();
            String goalid = null;
            RetirementGoal goalTypeInDb = null;
            for (int j = 0; j < goalJsonArray.length(); j++) {
                final String goalId = (String) goalJsonArray.get(j);
                if (!goalId.equals(finPlan.getGoal_id())) {

                    goalsArray.put(goalId);
                    goalTypeInDb = MongoDBConnection.goalcoll.findOne("{_id:#}", goalId).as(RetirementGoal.class);
                    if (goalTypeInDb.getGoalType().equals("Retirement")) {
                        goalid = goalTypeInDb.get_id();
                        break;
                    }
                } else {
                    continue;
                }
            }
            String goalType = null;
            if (goalid == null) {
                MongoDBConnection.finplancol.update("{usr_id:#,plan_name:#}", userId, finPlan.getPlan_name()).upsert()
                .multi().with("{$set: {'goals':" + goalsArray + "}}");
                goalTypeInDb = MongoDBConnection.goalcoll.findOne("{_id:#}", finPlan.getGoal_id())
                        .as(RetirementGoal.class);
                goalType = goalTypeInDb.getGoalType();
            } else {
                goalTypeInDb = MongoDBConnection.goalcoll.findOne("{_id:#}", goalid).as(RetirementGoal.class);
                goalType = goalTypeInDb.getGoalType();
            }

            if (goalType.equals("Retirement")) {
                final User userData = MongoDBConnection.userColl.findOne("{'_id':#}", userId).as(User.class);
                final JSONObject dataFromFinPlan = new JSONObject(jsonMapper.writeValueAsString(finPlanDetails));
                final RetirementGoalServiceImpl goalServiceImpl = new RetirementGoalServiceImpl();
                final JSONObject allRetirementIncome = goalServiceImpl
                        .deleteRetirementGoalOnUpdate(finPlanDetails.getMarital_status(), profileName, userId);
                JSONArray incomeArray = null;
                JSONArray userIncomeArray = allRetirementIncome.getJSONArray("user_income");
                JSONArray spouseIncomeAraay = new JSONArray();
                final JSONArray userExpense = goalServiceImpl.expenseRecalculate(finPlanDetails.get_id());
                int spouseStartYear = 0;

                if (finPlanDetails.getMarital_status().equals("Yes")) {
                    incomeArray = allRetirementIncome.getJSONArray("combined_income");
                    spouseIncomeAraay = allRetirementIncome.getJSONArray("spouse_income");
                    spouseStartYear = userIncomeArray.getJSONObject(0).getInt("year") - finPlanDetails.getSpouseAge()
                            + DEFAULT_RETIRMENT_AGE;
                }
                final int userStartYear = userData.getBirthYear() + DEFAULT_RETIRMENT_AGE;
                final RetirementGoalServiceImpl goalservice = new RetirementGoalServiceImpl();
                final double userAIME = goalservice.findUserAIME(DEFAULT_RETIRMENT_AGE, userIncomeArray, userStartYear);
                final int userFRA = goalservice.findUserFRA(DEFAULT_RETIRMENT_AGE, userData.getBirthYear());
                final JSONObject userJsonSSB = goalservice.calculateUserSSB(userData.getUserBeforeTaxIncome(),
                        finPlanDetails.getMarital_status(), userFRA, 0, FIRST_BEND_POINT, SECOND_BEND_POINT, userAIME,
                        0);

                if (finPlanDetails.getMarital_status().equals("Yes")) {
                    final JSONObject spouseJson = new JSONObject();
                    spouseJson.put("firetBendPoint", FIRST_BEND_POINT);
                    spouseJson.put("spouseRetirementAge", DEFAULT_RETIRMENT_AGE);
                    spouseJson.put("userRetirementAge", DEFAULT_RETIRMENT_AGE);
                    spouseJson.put("useFfra", userFRA);
                    spouseJson.put("spouseFfra",
                            goalservice.findUserFRA(DEFAULT_RETIRMENT_AGE, userData.getSpouseBirthYear()));
                    spouseJson.put("userAime", userAIME);
                    spouseJson.put("spouseAime",
                            goalservice.findUserAIME(DEFAULT_RETIRMENT_AGE, spouseIncomeAraay, spouseStartYear));
                    spouseJson.put("secondBendPoint", SECOND_BEND_POINT);
                    final JSONObject allIncomes = goalservice.insertRetirementIncomeNew(
                            ((Double) (userJsonSSB.get("User70"))), spouseJson, userData.get_id(), userIncomeArray,
                            spouseIncomeAraay, userStartYear, spouseStartYear, spouseStartYear);
                    userIncomeArray = allIncomes.getJSONArray("user_income");
                    spouseIncomeAraay = allIncomes.getJSONArray("spouse_income");
                    incomeArray = allIncomes.getJSONArray("combined_income");
                } else {
                    userIncomeArray = goalservice.insertUserRetirementIncomeNew(userIncomeArray, userData.get_id(),
                            null, (Double) (userJsonSSB.get("User70")), userStartYear);
                    incomeArray = userIncomeArray;
                }
                final JSONArray userExpenseAfterRet = CalculationEngine.retirementExpenseArray(userExpense,
                        spouseStartYear, userStartYear, finPlanDetails.getMarital_status(), 0);
                JSONObject result = new JSONObject();
                JSONArray assests = dataFromFinPlan.getJSONArray("assests");
                JSONArray tax = dataFromFinPlan.getJSONArray("tax");
                double spouseIncomeValue = 0;
                int ageAtRegistration = userData.getAge();
                int spouseAge = 0;
                final JSONObject retirementJson = new JSONObject();

                if (userData.getMarital_status().equals("Yes")) {
                    spouseAge = userData.getSpouseAge();
                    retirementJson.put("spouseStartYear", userData.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE);
                    spouseAge--;
                } else if (finPlanDetails.getMarital_status().equals("Yes")) {
                    spouseAge = finPlanDetails.getSpouseAge();
                    retirementJson.put("spouseStartYear", finPlanDetails.getSpouseBirthYear() + DEFAULT_RETIRMENT_AGE);
                    spouseAge--;
                }
                retirementJson.put("userStartYear", userData.getBirthYear() + DEFAULT_RETIRMENT_AGE);
                final JSONArray assestLimit = dataFromFinPlan.getJSONArray("limits");
                final JSONArray fillingExemtion = dataFromFinPlan.getJSONArray("fillingExemtion");
                MongoDBConnection.userColl.update("{_id:#}", userId).upsert().multi()
                .with("{$set: {'retirementFlag':" + 0 + "}}");
                for (int i = 0; i < assestLimit.length(); i++) {

                    if (spouseIncomeAraay.length() != 0) {
                        spouseIncomeValue = spouseIncomeAraay.getJSONObject(i).getDouble("value");
                        spouseAge++;
                    }
                    final JSONObject newLimits = CalculationEngine.limiteAfterRetirement(IRA_LIMIT, IRA_LIMIT, 0, 0, 0,
                            0, ageAtRegistration, userIncomeArray.getJSONObject(i).getDouble("value"),
                            spouseIncomeValue, fillingExemtion.getJSONObject(i).getString("fillingStatus"), spouseAge,
                            assestLimit.getJSONObject(i), incomeArray.getJSONObject(i).getDouble("value"), i, null);
                    assestLimit.put(i, newLimits);
                    ageAtRegistration++;
                }
                final JSONArray kidBirthYear = dataFromFinPlan.getJSONArray("kidBirthYear");
                dataFromFinPlan.getJSONObject("expense");
                JSONArray expenseObj = new JSONArray();
                JSONObject expense = new JSONObject();
                expense = dataFromFinPlan.getJSONObject("expense");
                if (!expense.isNull("housing_expense")) {
                    expenseObj = dataFromFinPlan.getJSONObject("expense").getJSONArray("housing_expense");
                }
                String emergencyType = null;
                String monthsOfIncome = null;
                String monthsOfExpense = null;
                if (finPlanDetails.isEmergencyFundFlag()) {
                    final Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
                            .findOne("{fin_id:#,goalType:#}", finPlanDetails.get_id(), "Emergency Fund")
                            .as(Emergencyfundmodel.class);
                    emergencyType = emergencyObj.getTimePeriod();
                    monthsOfIncome = emergencyObj.getMonthI();
                    monthsOfExpense = emergencyObj.getMonthE();
                }
                result = CalculationEngine.sweepingOfMoney(userIncomeArray, finPlanDetails.get_id(), incomeArray,
                        spouseIncomeAraay, userExpense, assestLimit, finPlanDetails.getMarital_status(), assests, tax,
                        userData.get_id(), dataFromFinPlan.getJSONArray("fillingExemtion"), userData.getAge(),
                        finPlanDetails.getSpouseAge(), finPlanDetails.getEmergencyFundAmt(),
                        finPlanDetails.isEmergencyFundFlag(), dataFromFinPlan.getJSONArray("deductions"), kidBirthYear,
                        retirementJson, null, expenseObj, emergencyType, monthsOfIncome, monthsOfExpense, false);
                ////////// //System.out.println("=====after function
                ////////// call=============");
                if (result.getString("status").equals("success")) {
                    assests = result.getJSONArray("assets");
                    tax = result.getJSONArray("tax");
                    MongoDBConnection.finplancol.update("{_id:#}", finPlanDetails.get_id())
                    .with("{$set: {'tax':" + tax + ",'assests':" + assests + ",'userExpense':"
                            + userExpenseAfterRet + ",'limits':" + assestLimit + ",'retirementFlag':" + false
                            + "}}");
                    if (finPlanDetails.getMarital_status().equals("Yes")) {
                        MongoDBConnection.incomeProfileCol
                        .update("{'user_id':#,'profile_name':#}", userData.get_id(),
                                finPlanDetails.getProfile_name())
                        .upsert().multi().with("{$set: {'user_income':" + userIncomeArray + ",'spouse_income':"
                                + spouseIncomeAraay + ",'combined_income':" + incomeArray + "}}");
                    } else {
                        MongoDBConnection.incomeProfileCol
                        .update("{'user_id':#,'profile_name':#}", userData.get_id(),
                                finPlanDetails.getProfile_name())
                        .upsert().multi().with("{$set: {'user_income':" + userIncomeArray + "}}");
                    }
                    MongoDBConnection.goalcoll.remove("{_id:#}", finPlan.getGoal_id());

                    jobject.put("plan_name", finPlan.getPlan_name());
                    jobject.put("goal_id", finPlan.getGoal_id());
                    return jobject.put("status", "success");
                } else {
                    return jobject.put("status", "fail");
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject;
        }
        return jobject;

    }

    // =============change plan name==================
    @Override
    public JSONObject changePlanName(final FinPlan finPlan) {
        final JSONObject jobject = new JSONObject();
        try {
            jobject.put("status", "fail");
            MongoDBConnection.finplancol.update("{usr_id:#,plan_name:#}", finPlan.getUser_id(), finPlan.getPlan_name())
            .with("{$set: {'plan_name':'" + finPlan.getNewPlanname() + "'}}");
            MongoDBConnection.goalcoll.update("{user_id:#,plan_name:#}",finPlan.getUser_id(), finPlan.getPlan_name()).multi()
            .with("{$set: {'plan_name':'" + finPlan.getNewPlanname() + "'}}");
            jobject.put("plan_name", finPlan.getNewPlanname());
            jobject.put("status", "success");
            return jobject;
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject;
        }
    }

    // ------**********************----------- Reset Expense for Delete
    JSONArray resetExpenseForDeleteGoals(final JSONObject finPlanJson, final int startYear, final int endYear,
            final double goalCost, final String finId, final String goalType) {
        try {
            int startYearTemp = startYear;
            final JSONArray userExpense = finPlanJson.getJSONArray("userExpense");
            if (goalType.equals("Home")) {
                for (int i = 0; i < userExpense.length(); i++) {
                    if (userExpense.getJSONObject(i).getInt("year") >= startYear
                            && userExpense.getJSONObject(i).getInt("year") <= endYear) {
                        final double totalExpense = userExpense.getJSONObject(i).getDouble("totalExpense")
                                + userExpense.getJSONObject(i).getDouble("housingExpense") - goalCost;
                        userExpense.getJSONObject(i).put("mortgageExpense", 0).put("totalExpense", totalExpense);
                    }
                }
            }
            if (goalType.equals("Raising a kid")) {
                for (int i = 0; i < userExpense.length(); i++) {
                    if (userExpense.getJSONObject(i).getInt("year") >= startYear
                            && userExpense.getJSONObject(i).getInt("year") <= endYear) {
                        final double kidExpense = userExpense.getJSONObject(i).getDouble("kidExpense") - goalCost;
                        final double totalExpense = userExpense.getJSONObject(i).getDouble("totalExpense") - goalCost;
                        userExpense.getJSONObject(i).put("kidExpense", kidExpense).put("totalExpense", totalExpense);
                    }
                }
            }
            if (goalType.equals("Marriage")) {
                for (int i = 0; i < userExpense.length(); i++) {
                    if (userExpense.getJSONObject(i).getInt("year") >= startYear
                            && userExpense.getJSONObject(i).getInt("year") <= endYear) {
                        userExpense.getJSONObject(i).put("afterMarriageExpense", 0);
                        userExpense.getJSONObject(i).put("totalExpense",
                                userExpense.getJSONObject(i).getDouble("totalExpense") - goalCost);
                        startYearTemp = startYearTemp + 1;
                    }
                }
            }
            MongoDBConnection.finplancol.update("{'_id': '" + finId + "'}").upsert().multi()
            .with("{$set: {'userExpense':" + userExpense + "}}");
            return userExpense;
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    JSONArray resetExpenseForDeleteMarriageGoal(final JSONObject userJson, final int startYear, final int endYear,
            final long housingExpenses, final long nonHousingExpenses) {
        JSONArray userExpense = new JSONArray();
        int startYearTemp = startYear;
        try {
            userExpense = userJson.getJSONArray("userExpense");
            for (int i = 0; i < userExpense.length(); i++) {
                userExpense.getJSONObject(i).put("afterMarriageExpense", 0);
                userExpense.getJSONObject(i).put("totalExpense",
                        +userExpense.getJSONObject(i).getDouble("registerNonHousingExpense")
                        + userExpense.getJSONObject(i).getDouble("registerHousingExpense")
                        + userExpense.getJSONObject(i).getDouble("mortgageExpense")
                        + userExpense.getJSONObject(i).getDouble("kidExpense"));

                startYearTemp++;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return userExpense;
    }

    public JSONArray recalculateExpcemptionForKidGoal(final JSONObject finPlanJson, final int startYear,
            final int endYear, final String filingStatus, final String finId) {
        try {
            String filingStatusTemp = filingStatus;
            final JSONArray filingExcemption = finPlanJson.getJSONArray("fillingExemtion");
            for (int i = 0; i < filingExcemption.length(); i++) {
                if (filingExcemption.getJSONObject(i).getInt("year") >= startYear
                        && filingExcemption.getJSONObject(i).getInt("year") <= endYear) {
                    filingStatusTemp = "Single";
                    final int excemptions = filingExcemption.getJSONObject(i).getInt("noOfExcemtion") - 1;
                    if (excemptions > 1) {
                        if (!filingExcemption.getJSONObject(i).getString("fillingStatus")
                                .equals("Married Filing Jointly")) {
                            filingStatusTemp = "Head of Household";
                        } else {
                            filingStatusTemp = "Married Filing Jointly";
                        }
                    }
                    filingExcemption.getJSONObject(i).put("noOfExcemtion", excemptions).put("fillingStatus",
                            filingStatusTemp);
                } else {
                    if (filingExcemption.getJSONObject(i).getInt("year") > endYear && i < filingExcemption.length()) {
                        if (filingExcemption.getJSONObject(i).getInt("noOfExcemtion") > 1 && (!filingExcemption
                                .getJSONObject(i).getString("fillingStatus").equals("Married Filing Jointly"))) {
                            filingStatusTemp = "Head of Household";
                        }
                        filingExcemption.getJSONObject(i).put("fillingStatus", filingStatusTemp);
                    }
                }
            }
            MongoDBConnection.finplancol.update("{'_id': '" + finId + "'}").upsert().multi()
            .with("{$set: {'fillingExemtion':" + filingExcemption + "}}");
            return filingExcemption;
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray recalculateExpcemptionForMarriageGoal(final JSONObject finPlanJson, final int startYear,
            final int endYear, final String filingStatus, final String finId) {
        try {
            final JSONArray filingExcemption = finPlanJson.getJSONArray("fillingExemtion");
            String filingStatusTemp = "Single";
            for (int i = 0; i < filingExcemption.length(); i++) {
                if (filingExcemption.getJSONObject(i).getInt("year") >= startYear
                        && filingExcemption.getJSONObject(i).getInt("year") <= endYear) {
                    final int excemptions = filingExcemption.getJSONObject(i).getInt("noOfExcemtion") - 1;
                    if (excemptions > 1) {
                        filingStatusTemp = "Head of Household";
                    }
                    filingExcemption.getJSONObject(i).put("noOfExcemtion", excemptions).put("fillingStatus",
                            filingStatusTemp);
                }
            }
            MongoDBConnection.finplancol.update("{'_id': '" + finId + "'}").upsert().multi()
            .with("{$set: {'fillingExemtion':" + filingExcemption + "}}");
            return filingExcemption;
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ----------------delete income
    @Override
    public JSONObject deleteIncomeProfile(final String profileName, final String userId) {
        final JSONObject respToServlet = new JSONObject();
        try {
            respToServlet.put("status", "fail");
            final MongoCursor<FinPlan> cursor = MongoDBConnection.finplancol
                    .find("{usr_id:#,profile_name:#}", userId, profileName).as(FinPlan.class);
            FinPlan finPlan = null;
            if (!profileName.equals("constant_income")) {
                while (cursor.hasNext()) {
                    //////////// //System.out.println("fin_id------" + c++);
                    finPlan = MongoDBConnection.finplancol.findOne("{_id:#}", cursor.next().get_id()).as(FinPlan.class);
                    deleteFinPlan(finPlan);
                }
                MongoDBConnection.incomeProfileCol.remove("{profile_name:#,user_id:#}", profileName, userId);
            }
            cursor.close();
            respToServlet.put("status", "success");
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return respToServlet;
    }

}
