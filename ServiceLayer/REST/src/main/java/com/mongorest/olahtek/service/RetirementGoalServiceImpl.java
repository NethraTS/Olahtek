package com.mongorest.olahtek.service;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import org.jongo.Jongo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongorest.olahtek.model.AIMEIndexingFactor;
import com.mongorest.olahtek.model.Counters;
import com.mongorest.olahtek.model.Emergencyfundmodel;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.User;

@Service("goalService")
@Transactional
public class RetirementGoalServiceImpl implements RetirementGoalService {
	public MongoClient mongoClient = MongoDBConnection.mongoClient;
	public MongoDatabase mongoDb = MongoDBConnection.mongoDb;
	public Jongo jongo = MongoDBConnection.jongo;
	public String mongoDBName = MongoDBConnection.mongoDBName;
	public DB db = MongoDBConnection.db;
	public JSONObject cm = MongoDBConnection.cm;
	public double remainingMortgageTmp = 0;
	String counterCollection = MongoDBConnection.counterCollection;
	String financialPlanCollection = MongoDBConnection.financialPlanCollection;
	String sessionCollection = MongoDBConnection.sessionCollection;
	String goalCollection = MongoDBConnection.goalCollection;
	String minimumDistributioncoll=MongoDBConnection.minimumDistributioncollection;

	String usersCollection = MongoDBConnection.usersCollection;
	String systemLog = MongoDBConnection.systemLog;
	String stateTaxCollection = MongoDBConnection.stateTaxCollection;
	String incomeProfileCollection = MongoDBConnection.incomeProfileCollection;

	ObjectMapper jsonMapper = new ObjectMapper();

	@JsonCreator

	// --------------------------GoalRetirement--------------------
	public JSONObject onLoad(RetirementGoal goal) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		JSONObject responseToRestController = new JSONObject();
		User Details1 = MongoDBConnection.userColl.findOne("{_id:#}", goal.getUser_id()).as(User.class);
		try {
			FinPlan finPlan = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}", goal.getUser_id(), goal.getPlan_name()).as(FinPlan.class);

			int userAge = year - Details1.getBirthYear();
			responseToRestController.put("marital_status", finPlan.getMarital_status());
			responseToRestController.put("userAge", userAge);
			responseToRestController.put("statusInFinPlan", finPlan.getMarital_status());
			responseToRestController.put("status", "success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseToRestController;
	}

	public JSONObject saveGoalRetirement(RetirementGoal goal) {

		JSONObject systemLog = new JSONObject();
		String formType = goal.getFormType();
		String fin_name = goal.getPlan_name();

		String user_id = goal.getUser_id();

		double firetBendPoint = 856;
		double secondBendPoint = 5157;

		JSONObject userJsonSSB = new JSONObject();
		JSONObject spousJsonSSB = new JSONObject();
		JSONObject jsonSSB = new JSONObject();
		JSONObject retirement_expensedata = new JSONObject();
		double userSsb = 0;
		double spouseSsb = 0;
		double spouseEarlySSB = 0;
		double userEarlySSB = 0;
		Counters count;
		String goalid = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		User user = MongoDBConnection.userColl.findOne("{_id:#}", goal.getUser_id()).as(User.class);
		
		

		try {
			RetirementGoal retirementData =null;
			FinPlan getfinplan =null;
			if(goal.getGoal_id()!=null)
			{
				retirementData = MongoDBConnection.goalcoll.findOne("{_id:#}", goal.getGoal_id()).as(RetirementGoal.class);
				getfinplan = MongoDBConnection.finplancol.findOne("{_id:#}", retirementData.getFin_id()).as(FinPlan.class);
				
			}
			else
			{

				getfinplan = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}", user_id, fin_name).as(FinPlan.class);
			}
			IncomeProfile incomeProf =null;
			if(getfinplan!=null)
			{
				incomeProf = MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}", user_id, getfinplan.getProfile_name()).as(IncomeProfile.class);

			}
			else
			{
				incomeProf = MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}", user_id, "constant_income").as(IncomeProfile.class);

			}
			JSONObject incomeJson = new JSONObject(jsonMapper.writeValueAsString(incomeProf));
		//	////System.out.println("fin json object======="+new JSONObject(jsonMapper.writeValueAsString(getfinplan)));
			JSONArray userIncomeArray = incomeJson.getJSONArray("user_income");
			JSONArray spouseIncomeAraay = new JSONArray();
			JSONArray incomeArray=new JSONArray();


			
			int retirementAge = goal.getRetirementAge();
			if (retirementAge < 62) {
				retirementAge = 62;
			} else if (retirementAge > 70) {
				retirementAge = 70;
			}
			int spouseRetirementAge = 0;
			int userSelectedSpouseRetirementAge = 0;
			if (user.getMarital_status().equals("Yes")||getfinplan.getMarital_status().equals("Yes")) {
				spouseRetirementAge = goal.getSpouseRetirementAge();
				userSelectedSpouseRetirementAge=spouseRetirementAge;
				if (spouseRetirementAge == 0) {
					spouseRetirementAge = 70;
				} else if (spouseRetirementAge > 0 && spouseRetirementAge < 62) {
					spouseRetirementAge = 62;
				} else if (spouseRetirementAge > 70) {
					spouseRetirementAge = 70;
				}
			}
			int userStartYear = user.getBirthYear() + retirementAge;
			int SpouseStartYear=0;
			int userSelectedSpouseYear=0;
			if (user.getMarital_status().equals("Yes")) {
				SpouseStartYear = user.getSpouseBirthYear() + spouseRetirementAge;
				userSelectedSpouseYear=user.getSpouseBirthYear()+userSelectedSpouseRetirementAge;
			}
			if(user.getMarital_status().equals("No")&& getfinplan.getMarital_status().equals("Yes"))
			{
				SpouseStartYear = getfinplan.getSpouseBirthYear() + spouseRetirementAge;
				userSelectedSpouseYear=getfinplan.getSpouseBirthYear()+userSelectedSpouseRetirementAge;
			}
			if (getfinplan.getMarital_status().equals("Yes")) {
				incomeArray = incomeJson.getJSONArray("combined_income");
				spouseIncomeAraay = incomeJson.getJSONArray("spouse_income");
			}
			else
			{
				incomeArray = userIncomeArray;
			}
			int spouseAge=0;
			int spouseAgeCal=0;
			if(user.getMarital_status().equals("Yes"))
			{
				spouseAge=user.getSpouseAge();
			}
			else if(getfinplan.getMarital_status().equals("Yes"))
			{
				spouseAge=getfinplan.getSpouseAge();
				////System.out.println("spouseAge inside Finplan"+spouseAge);
			}
			spouseAgeCal=spouseAge;
			//System.out.println("userstart year:  "+userStartYear);
			//System.out.println("SpouseStartYear:  "+SpouseStartYear);
			//System.out.println("spouseAge:  "+spouseAge);
			//System.out.println("user age:  "+user.getAge());
			String emergencyType=null;
			String monthsOfIncome=null;
			String monthsOfExpense=null;
			if(getfinplan.isEmergencyFundFlag())
			{
				Emergencyfundmodel emergencyObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",getfinplan.get_id(),"Emergency Fund").as(Emergencyfundmodel.class);
				emergencyType=emergencyObj.getTimePeriod();
				monthsOfIncome=emergencyObj.getMonthI();
				monthsOfExpense=emergencyObj.getMonthE();
			}
			// ------------------------------------------------------------------------------------------
			if (formType.equals("next")) {
				systemLog.put("type", "success");
				systemLog.put("message", "Goal Created Successfully!!");
				systemLog.put("userName", user.getName());
				systemLog.put("user_id", user.get_id());
				systemLog.put("createdTs", dateFormat.format(date));
				try {
					// //////System.out.println("------------------NEXT Called-------------------------");
					FinPlan finPlanData = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}", user_id, fin_name).as(FinPlan.class);

					double userAime = findUserAIME( 70,userIncomeArray,userStartYear);
					double spouseAime = 0;
					int useFfra = findUserFRA(retirementAge, user.getBirthYear());
					int spouseFfra = 0;
					if (user.getMarital_status().equals("Yes")||finPlanData.getMarital_status().equals("Yes")) {
						spouseAime = findUserAIME( 70,spouseIncomeAraay,SpouseStartYear);
						spouseFfra = findUserFRA(retirementAge, user.getSpouseAge());
					}
					userJsonSSB = calculateUserSSB(user.getSpouseBeforeTaxIncome(), user.getMarital_status(), useFfra, spouseFfra, firetBendPoint,
							secondBendPoint, userAime, spouseAime);
					if (user.getMarital_status().equals("Yes") && user.getSpouseBeforeTaxIncome() != 0) {
						spousJsonSSB = calculateSpouseSSB(user, useFfra, spouseFfra, firetBendPoint, secondBendPoint, userAime, spouseAime);
					}
					userEarlySSB = (Double) (userJsonSSB.get("User62"));
					userSsb = (Double) (userJsonSSB.get("User" + retirementAge));
					userJsonSSB.put("userExpectedSSB", userSsb);
					if (user.getMarital_status().equals("Yes") && user.getSpouseBeforeTaxIncome() != 0) {
						spouseSsb = (Double) (spousJsonSSB.get("Spouse" + spouseRetirementAge));
						spouseEarlySSB = (Double) (spousJsonSSB.get("Spouse62"));
					} else if (user.getMarital_status().equals("Yes") && user.getSpouseBeforeTaxIncome() == 0) {
						spouseSsb = (Double) (userJsonSSB.get("Spouse" + spouseRetirementAge));
						spouseEarlySSB = (Double) (userJsonSSB.get("Spouse62"));
					} else {
						spouseSsb = 0;
					}
					count = MongoDBConnection.counterColl.findOne().as(Counters.class);
					goalid = "goal" + count.getGoal_id();
					MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}").with("{$inc: {goal_id: 1}}");
					String fin_id = getfinplan.get_id();
					double retirementExpense = 0;
					int startYear = user.getBirthYear() + retirementAge;
					int endYear = user.getBirthYear() + 99;
					retirement_expensedata.put("retirementAge", retirementAge);
					retirement_expensedata.put("startYear", startYear);
					retirement_expensedata.put("endYear", endYear);
					for (int i = 62; i <= 70; i++) {
						jsonSSB.put("User" + i, (Double) (userJsonSSB.get("User" + i)));
						if (user.getMarital_status().equals("Yes") && user.getSpouseBeforeTaxIncome() != 0) {
							jsonSSB.put("Spouse" + i, (Double) (spousJsonSSB.get("Spouse" + i)));

						} else if (user.getMarital_status().equals("Yes") && user.getSpouseBeforeTaxIncome() == 0) {
							jsonSSB.put("Spouse" + i, (Double) (userJsonSSB.get("Spouse" + i)));

						}
					}
					jsonSSB.put("goal_id", goalid);
					jsonSSB.put("massage", "Goal created successfully !!");
					jsonSSB.put("status", "success");
					jsonSSB.put("marital_status", user.getMarital_status());
					String income_profileName = null;
					if (getfinplan.getProfile_name() != null) {
						income_profileName = getfinplan.getProfile_name();
					} else {
						income_profileName = "constant_income";
					}
					boolean flag = false;
					JSONObject spouseJson=new JSONObject();
					if (user.getMarital_status().equals("Yes")||finPlanData.getMarital_status().equals("Yes")) {
						spouseJson.put("firetBendPoint", firetBendPoint);
						spouseJson.put("useFfra", useFfra);
						spouseJson.put("spouseFfra", spouseFfra);
						spouseJson.put("spouseRetirementAge", goal.getSpouseRetirementAge());
						spouseJson.put("userRetirementAge", goal.getRetirementAge());
						spouseJson.put("userAime", userAime);
						spouseJson.put("spouseAime", spouseAime);
						spouseJson.put("secondBendPoint", secondBendPoint);
						JSONObject allIncomes = insertRetirementIncomeNew(userSsb,spouseJson,user.getUser_id(),userIncomeArray,spouseIncomeAraay,userStartYear, SpouseStartYear,userSelectedSpouseYear);
						////System.out.println("allIncomes====="+allIncomes);
						userIncomeArray=allIncomes.getJSONArray("user_income");
						spouseIncomeAraay=allIncomes.getJSONArray("spouse_income");
						incomeArray=allIncomes.getJSONArray("combined_income");
					} else {
						userIncomeArray =insertUserRetirementIncomeNew(userIncomeArray,user.getUser_id(), null,userSsb,userStartYear );
						incomeArray=userIncomeArray;
						////System.out.println("Bala...Length"+incomeArray.length());
					}
					if (flag == false) {
						jsonSSB.put("status", "success");
					}
					// calculation---------------------------
					JSONObject assestsData = new JSONObject();
					IncomeProfile incomeProfile = MongoDBConnection.incomeProfileCol.findOne("{'user_id':#,'profile_name':#}", user_id, income_profileName).as(
							IncomeProfile.class);
					JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfile));
					double incomeJsonSSB = calculateIncomeSSB(spouseSsb, userSsb, user.getBirthYear());
					JSONArray combinedIncome = new JSONArray();
					JSONArray userIncome = new JSONArray();
					JSONArray spouseIncome = new JSONArray();
					userIncome = userJson.getJSONArray("user_income");
					if (user.getMarital_status().equals("Yes")||finPlanData.getMarital_status().equals("Yes")) {
						spouseIncome = userJson.getJSONArray("spouse_income");
						JSONObject allIncomes = insertRetirementIncomeNew(userSsb,spouseJson,user.getUser_id(),userIncome,spouseIncome,userStartYear, SpouseStartYear,userSelectedSpouseYear);
						userIncome=allIncomes.getJSONArray("user_income");
						spouseIncome=allIncomes.getJSONArray("spouse_income");
						combinedIncome=allIncomes.getJSONArray("combined_income");
					}
					else
					{
						userIncome = insertUserRetirementIncomeNew(userIncome,user.getUser_id(), null,userSsb,userStartYear );
						combinedIncome=userIncome;
					}
					assestsData.put("ssb", incomeJsonSSB);
					assestsData.put("userssb", userSsb);
					assestsData.put("spousessb", spouseSsb);
					assestsData.put("retirementAge", retirementAge);
					assestsData.put("spouseStartYear", SpouseStartYear);
					assestsData.put("userAge", retirementAge);
					assestsData.put("spouseAge", spouseRetirementAge);
					assestsData.put("userStartYear", userStartYear);
					assestsData.put("goal_id", goalid);
					assestsData.put("user_id", user_id);
					assestsData.put("combinedIncome", combinedIncome);
					assestsData.put("userIncome", userIncome);
					assestsData.put("spouseIncome", spouseIncome);
					//============================new logic for retirement===========================================
					finPlanData = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}", user_id,fin_name).as(FinPlan.class);
					JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finPlanData));
					JSONArray userExpense=CalculationEngine.retirementExpenseArray(finPlanJson.getJSONArray("userExpense"), SpouseStartYear, userStartYear,finPlanData.getMarital_status(),0);
					////System.out.println("Retirement expense array .."+userExpense);
					JSONArray assestLimit=finPlanJson.getJSONArray("limits");
					int ageAtRegistration=user.getAge();
					JSONArray fillingExemtion=finPlanJson.getJSONArray("fillingExemtion");
					double spouseIncomeValue=0;
					spouseAge--;
					////System.out.println("Mahi: in GoalImpl spouse401kCont :"+goal.getSpouse401kCont());
					////System.out.println("Mahi: in GoalImpl user401kCont :"+goal.getUser401kCont());
					for(int i=0;i<assestLimit.length();i++)
					{
						if(spouseIncome.length()!=0)
						{
							spouseIncomeValue=spouseIncome.getJSONObject(i).getDouble("value");
							spouseAge++;
						}
						////System.out.println("tUser401kCont"+ goal.getUser401kCont());
						JSONObject newLimits=CalculationEngine.limiteAfterRetirement( goal.getUser401kCont(), goal.getSpouse401kCont(),goal.getUserContributeAmount(),goal.getSpouseContributeAmount(),goal.getUserContributionInIRA(),goal.getSpouseContributionInIRA(),ageAtRegistration, userIncomeArray.getJSONObject(i).getDouble("value"),
								spouseIncomeValue,fillingExemtion.getJSONObject(i).getString("fillingStatus"),
								spouseAge,assestLimit.getJSONObject(i),incomeArray.getJSONObject(i).getDouble("value"),i,null );
						assestLimit.put(i,newLimits);
						ageAtRegistration++;
					}
					JSONArray kidBirthYear=finPlanJson.getJSONArray("kidBirthYear");
					JSONArray expenseObj=new JSONArray();
					JSONObject expense=new JSONObject();
					expense=finPlanJson.getJSONObject("expense");
					if (!expense.isNull("housing_expense")) {
					expenseObj=finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
					}
					JSONObject goalFeasibilityData=CalculationEngine.sweepingOfMoney(userIncome,fin_id, combinedIncome, spouseIncome,
							userExpense, assestLimit, finPlanData.getMarital_status(), finPlanJson.getJSONArray("assests"),
							finPlanJson.getJSONArray("tax"), user_id, finPlanJson.getJSONArray("fillingExemtion"), user.getAge(),
							spouseAgeCal,finPlanData.getEmergencyFundAmt(),finPlanData.isEmergencyFundFlag(),finPlanJson.getJSONArray("deductions"),kidBirthYear,assestsData,goal,expenseObj,emergencyType, monthsOfIncome,monthsOfExpense,finPlanData.isRetirementFlag());
					if(goalFeasibilityData.getString("status").equals("success"))
					{
						BasicDBObject basicDBObject = new BasicDBObject("_id", goalid).append("user_id", user_id).append("plan_name", fin_name)
								.append("fin_id", fin_id).append("goalType", "Retirement")
								.append("retirementAge", retirementAge).append("userSelectedSpouseRetirementAge", userSelectedSpouseRetirementAge)
								.append("userSelectedSpouseYear", userSelectedSpouseYear)
								.append("spouseRetirementAge", spouseRetirementAge).append("otherRetirementIncome", goal.getOtherRetirementIncome())
								.append("spouseOtherRetirementIncome", goal.getSpouseOtherRetirementIncome()).append("userAgeAtEarlySSB", 62)
								.append("spouseAgeAtEarlySSB", 62).append("spouseSSB", spouseSsb).append("userSSB", userSsb).append("userEarlySSB", userEarlySSB)
								.append("spouseEarlySSB", spouseEarlySSB).append("userAIME", userAime).append("spouseAIME", spouseAime)
								.append("createdTs", dateFormat.format(date)).append("modifiedTs", dateFormat.format(date)).append("spouseLifeExpectancy", 0)
								.append("lifeExpectancy", goal.getLifeExpectancy()).append("completedStatus", 1)
								.append("spouseSavedRothAcn", goal.getSpouseSavedRothAcn()).append("spouseSavedInRothIRA", goal.getSpouseSavedInRothIRA())
								.append("spouseSavedInIRA", goal.getSpouseSavedInIRA()).append("spouseSavedAmount", goal.getSpouseSavedAmount())
								.append("userSavedRothAcn", goal.getUserSavedRothAcn()).append("userSavedInRothIRA", goal.getUserSavedInRothIRA())
								.append("userSavedInIRA", goal.getUserSavedInIRA()).append("spouseSponsoredRothAcn", goal.getSpouseSponsoredRothAcn())
								.append("userSponsoredRothAcn", goal.getUserSponsoredRothAcn())
								.append("spouseContributionInRothIRA", goal.getSpouseContributionInRothIRA())
								.append("userContributionInRothIRA", goal.getUserContributionInRothIRA())
								.append("spouseContributionInIRA", goal.getSpouseContributionInIRA())
								.append("userContributionInIRA", goal.getUserContributionInIRA())
								.append("spouseMatchContribution", goal.getSpouseMatchContribution())
								.append("userMatchContribution", goal.getUserMatchContribution())
								.append("spouseContributeAmount", goal.getSpouseContributeAmount()).append("userSavedAmount", goal.getUserSavedAmount())
								.append("userContributeAmount", goal.getUserContributeAmount())
								.append("userCap", goal.getUserCap())
								.append("spouseCap", goal.getSpouseCap())
								.append("retirement_expense",0);
						
						MongoDBConnection.goalcoll.insert(basicDBObject);
						MongoDBConnection.finplancol.update("{'usr_id':#,'plan_name':#}", user_id, fin_name).upsert().multi()
						.with("{$addToSet: {'goals':'" + goalid + "'}}");
						MongoDBConnection.finplancol.update("{'usr_id':#,'plan_name':#}", user_id, fin_name).with(
								"{$set: {'expense.retirement_expense':" + retirement_expensedata + ","
										+" 'tax':" + goalFeasibilityData.getJSONArray("tax") + ","
										+ "'assests':" + goalFeasibilityData.getJSONArray("assets") + ","
										+ "'userExpense':" + userExpense + ","+ "'user401k':" + goal.getUser401kCont() + ","
										+ "'spouse401k':" + goal.getSpouse401kCont() + ","
										+ "'retirementFlag':" +true+ ","
										+ "'limits':" + assestLimit+ "}}");
						MongoDBConnection.userColl
						.update("{'_id': '" + user.get_id() + "'}")
						.upsert()
						.multi()
						.with("{$set: {'retirementFlag':"+1+"}}");

						if(finPlanData.getMarital_status().equals("Yes"))
						{							 	
							MongoDBConnection.incomeProfileCol.update("{'user_id':#,'profile_name':#}", user_id, income_profileName).upsert().multi()
							.with("{$set: {'user_income':" + userIncome + ",'spouse_income':" + spouseIncome + ",'combined_income':" + combinedIncome + "}}");
						}
						else
						{
							MongoDBConnection.incomeProfileCol.update("{'user_id':#,'profile_name':#}", user_id, income_profileName).upsert().multi()
							.with("{$set: {'user_income':" + userIncome + "}}");
						}
						return jsonSSB;
					}
					else
					{
						jsonSSB.put("status", "fail");
					}

				} catch (Exception e) {
					systemLog.put("type", "error");
					systemLog.put("message", "Goal Creation Failed!!");
					systemLog.put("userName", user.getName());
					systemLog.put("user_id", user.get_id());
					systemLog.put("createdTs", dateFormat.format(date));
					e.printStackTrace();
				} finally {
					BasicDBObject systemLogBasicObject = new BasicDBObject("user_id", systemLog.getString("user_id"))
							.append("userName", systemLog.getString("userName")).append("type", systemLog.getString("type"))
							.append("message", systemLog.getString("message")).append("createdTs", dateFormat.format(date));
					MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);
				}

			} else if (formType.equals("update")) {
				systemLog.put("type", "success");
				systemLog.put("message", "Goal updated successfully");
				systemLog.put("userName", user.getName());
				systemLog.put("user_id", user.get_id());
				systemLog.put("createdTs", dateFormat.format(date));
				try {
					// //////System.out.println("------------------UPDATE Called-------------------------");
					retirementData = MongoDBConnection.goalcoll.findOne("{_id:#}", goal.getGoal_id()).as(RetirementGoal.class);
					FinPlan finPlanData = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}", user_id, retirementData.getPlan_name()).as(
							FinPlan.class);
					
					
					//System.out.println("update data-----"+finPlanData.toString());
					if(finPlanData!=null)
					{
						user.setMarital_status(finPlanData.getMarital_status());
					}
					double userAime = findUserAIME( 70,userIncomeArray,userStartYear);
					double spouseAime = 0;
					int useFfra = findUserFRA(retirementAge, user.getBirthYear());
					int spouseFfra = 0;
					//System.out.println("SpouseStartYear:::::::"+SpouseStartYear);
					if (finPlanData.getMarital_status().equals("Yes")) {
						spouseAime = findUserAIME( 70,spouseIncomeAraay,SpouseStartYear);
						spouseFfra = findUserFRA(retirementAge, user.getSpouseAge());
					}
					if (finPlanData.getMarital_status().equals("Yes") && user.getSpouseBeforeTaxIncome() != 0) {
						spousJsonSSB = calculateSpouseSSB(user, useFfra, spouseFfra, firetBendPoint, secondBendPoint, userAime, spouseAime);
					}
					userJsonSSB = calculateUserSSB(user.getSpouseBeforeTaxIncome(), finPlanData.getMarital_status(), useFfra, spouseFfra, firetBendPoint,
							secondBendPoint, userAime, spouseAime);

					userEarlySSB = (Double) (userJsonSSB.get("User62"));
					userSsb = (Double) (userJsonSSB.get("User" + retirementAge));
					if (finPlanData.getMarital_status().equals("Yes") && user.getSpouseBeforeTaxIncome() != 0) {
						spouseSsb = (Double) (spousJsonSSB.get("Spouse" + spouseRetirementAge));
						spouseEarlySSB = (Double) (spousJsonSSB.get("Spouse62"));
					} else if (finPlanData.getMarital_status().equals("Yes") && user.getSpouseBeforeTaxIncome() == 0) {
						spouseSsb = (Double) (userJsonSSB.get("Spouse" + spouseRetirementAge));
						spouseEarlySSB = (Double) (userJsonSSB.get("Spouse62"));
					} else {
						spouseSsb = 0;
					}

					String income_profileName = null;
					if (finPlanData.getProfile_name() != null) {
						income_profileName = finPlanData.getProfile_name();
					} else {
						income_profileName = "constant_income";
					}
					JSONObject spouseJson=new JSONObject();
					if (user.getMarital_status().equals("Yes")) {

						spouseJson.put("firetBendPoint", firetBendPoint);
						spouseJson.put("spouseRetirementAge", goal.getSpouseRetirementAge());
						spouseJson.put("userRetirementAge", goal.getRetirementAge());
						spouseJson.put("useFfra", useFfra);
						spouseJson.put("spouseFfra", spouseFfra);
						spouseJson.put("userAime", userAime);
						spouseJson.put("spouseAime", spouseAime);
						spouseJson.put("secondBendPoint", secondBendPoint);
					} 
					//============================logic for delete retirement data========================================
					double incomeJsonSSB = calculateIncomeSSB(spouseSsb, userSsb, user.getBirthYear());
					JSONObject allRetirementIncome=deleteRetirementGoalOnUpdate(finPlanData.getMarital_status(),income_profileName, user_id);
					JSONArray userExpense= expenseRecalculate(finPlanData.get_id());
					JSONArray combinedIncome=null;
					JSONArray userIncome=allRetirementIncome.getJSONArray("user_income");
					JSONArray spouseIncome=new JSONArray();

					if(finPlanData.getMarital_status().equals("Yes"))
					{
						combinedIncome=allRetirementIncome.getJSONArray("combined_income");
						spouseIncome=allRetirementIncome.getJSONArray("spouse_income");
					}
					else
					{
						combinedIncome=userIncome;
					}
					if (user.getMarital_status().equals("Yes")||finPlanData.getMarital_status().equals("Yes")) {
						JSONObject allIncomes = insertRetirementIncomeNew(userSsb,spouseJson,user.getUser_id(),userIncome,spouseIncome,userStartYear, SpouseStartYear,userSelectedSpouseYear);
						userIncome=allIncomes.getJSONArray("user_income");
						spouseIncome=allIncomes.getJSONArray("spouse_income");
						////System.out.println("Mahi:   spouseIncome:   "+spouseIncome);
						combinedIncome=allIncomes.getJSONArray("combined_income");
					}
					else
					{
						userIncome = insertUserRetirementIncomeNew(userIncome,user.getUser_id(), null,userSsb,userStartYear );
						combinedIncome=userIncome;
					}
					JSONObject assestsData = new JSONObject();
					assestsData.put("ssb", incomeJsonSSB);
					assestsData.put("userssb", userSsb);
					assestsData.put("spousessb", spouseSsb);
					assestsData.put("retirementAge", retirementAge);
					assestsData.put("spouseStartYear", SpouseStartYear);
					assestsData.put("userAge", retirementAge);
					assestsData.put("spouseAge", spouseRetirementAge);
					assestsData.put("userStartYear", userStartYear);
					assestsData.put("goal_id", goalid);
					assestsData.put("user_id", user_id);
					assestsData.put("combinedIncome", combinedIncome);
					assestsData.put("userIncome", userIncome);
					assestsData.put("spouseIncome", spouseIncome);
					////System.out.println("SpouseStartYear in update:  "+SpouseStartYear);
					////System.out.println("userStartYear in update:  "+userStartYear);
					JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finPlanData));
					////System.out.println("Ranjitha new updated expenxe.."+goal.getRetirement_expense());
					JSONArray afterRetirementExpense=CalculationEngine.retirementExpenseArray(userExpense, SpouseStartYear, userStartYear,finPlanData.getMarital_status(),goal.getRetirement_expense());
					JSONArray assestLimit=finPlanJson.getJSONArray("limits");
					JSONArray fillingExemtion=finPlanJson.getJSONArray("fillingExemtion");
					double spouseIncomeValue=0;
					int ageAtRegistration=user.getAge();
					spouseAge--;
					for(int i=0;i<assestLimit.length();i++)
					{

						if(spouseIncome.length()!=0)
						{
							spouseIncomeValue=spouseIncome.getJSONObject(i).getDouble("value");
							spouseAge++;
						}
						JSONObject newLimits=CalculationEngine.limiteAfterRetirement( goal.getUser401kCont(),goal.getSpouse401kCont(),goal.getUserContributeAmount(),goal.getSpouseContributeAmount(),goal.getUserContributionInIRA(),goal.getSpouseContributionInIRA(),ageAtRegistration, userIncomeArray.getJSONObject(i).getDouble("value"),
								spouseIncomeValue,fillingExemtion.getJSONObject(i).getString("fillingStatus"),
								spouseAge,assestLimit.getJSONObject(i),incomeArray.getJSONObject(i).getDouble("value"),i,null);
						assestLimit.put(i,newLimits);
						ageAtRegistration++;
					}	
					JSONArray kidBirthYear=finPlanJson.getJSONArray("kidBirthYear");
					JSONArray expenseObj=new JSONArray();
					JSONObject expense=new JSONObject();
					expense=finPlanJson.getJSONObject("expense");
					if (!expense.isNull("housing_expense")) {
					expenseObj=finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
					}
					JSONObject goalFeasibilityData=CalculationEngine.sweepingOfMoney(userIncome,finPlanData.get_id(), combinedIncome, spouseIncome,
							afterRetirementExpense, assestLimit, finPlanData.getMarital_status(), finPlanJson.getJSONArray("assests"),
							finPlanJson.getJSONArray("tax"), user_id, finPlanJson.getJSONArray("fillingExemtion"), user.getAge(),
							spouseAgeCal,finPlanData.getEmergencyFundAmt(),finPlanData.isEmergencyFundFlag(),finPlanJson.getJSONArray("deductions"),kidBirthYear,assestsData,goal,expenseObj,emergencyType, monthsOfIncome,monthsOfExpense,finPlanData.isRetirementFlag());
					if(goalFeasibilityData.getString("status").equals("success"))
					{
						MongoDBConnection.goalcoll
						.update("{'_id':#}", goal.getGoal_id())
						.upsert()
						.multi()
						.with("{$set: {'retirementAge':" + goal.getRetirementAge() + ",'spouseOtherRetirementIncome':"
								+ goal.getSpouseOtherRetirementIncome() + ",'userSelectedSpouseYear':" + userSelectedSpouseYear + ",'spouseRetirementAge':" + spouseRetirementAge + ",'userSelectedSpouseRetirementAge':" + userSelectedSpouseRetirementAge+ ",'otherRetirementIncome':" + goal.getOtherRetirementIncome() + ",'spouseSSB':" + spouseSsb + ",'userSSB':" + userSsb
								+ ",'userEarlySSB':" + userEarlySSB + ",'spouseEarlySSB':" + spouseEarlySSB + ",'userAIME':" + userAime + ",'spouseAIME':"
								+ spouseAime + ",'modifiedTs':'" + dateFormat.format(date) + "','spouseLifeExpectancy':" + goal.getSpouseLifeExpectancy()
								+ ",'lifeExpectancy':" + goal.getLifeExpectancy() + ",'retirement_expense':" + goal.getRetirement_expense()
								+ ",'userSavedInIRA':" + goal.getUserSavedInIRA() + ",'userContributionInIRA':" + goal.getUserContributionInIRA()
								+ ",'userSavedInRothIRA':" + goal.getUserSavedInRothIRA() + ",'userContributionInRothIRA':"
								+ goal.getUserContributionInRothIRA() + ",'spouseSavedInIRA':" + goal.getSpouseSavedInIRA() + ",'spouseContributionInIRA':"
								+ goal.getSpouseContributionInIRA() + ",'spouseSavedInRothIRA':" + goal.getSpouseSavedInRothIRA()
								+ ",'spouseContributionInRothIRA':" + goal.getSpouseContributionInRothIRA() + ",'userCap':"+ goal.getUserCap() +",'userSavedAmount':"+goal.getUserSavedAmount()+",'userContributeAmount':"+goal.getUserContributeAmount()+",'userMatchContribution':"+goal.getUserMatchContribution()+",'spouseCap':"+ goal.getSpouseCap() +",'spouseSavedAmount':"+goal.getSpouseSavedAmount()+",'spouseContributeAmount':"+goal.getSpouseContributeAmount()+",'spouseMatchContribution':"+goal.getSpouseMatchContribution()+"}}");
						MongoDBConnection.finplancol.findAndModify("{'usr_id':#,'plan_name':#}", user_id, fin_name);
						////System.out.println("afterRetirementExpense   mahi:  "+afterRetirementExpense);
						RetirementGoal goalData = MongoDBConnection.goalcoll.findOne("{_id:#}", goal.getGoal_id()).as(RetirementGoal.class);
						fin_name = goalData.getPlan_name();
						int startYear = user.getBirthYear() + retirementAge;
						int endYear = user.getBirthYear() + 99;
						retirement_expensedata.put("retirementAge", retirementAge);
						retirement_expensedata.put("startYear", startYear);
						retirement_expensedata.put("endYear", endYear);
						MongoDBConnection.finplancol.update("{'usr_id':#,'plan_name':#}", user_id, fin_name).with(
								"{$set: {'expense.retirement_expense':" + retirement_expensedata + ","
										+" 'tax':" + goalFeasibilityData.getJSONArray("tax") + ","
										+ "'assests':" + goalFeasibilityData.getJSONArray("assets") + ","
										+ "'userExpense':" + afterRetirementExpense + ","+ "'user401k':" + goal.getUser401kCont() + ","
										+ "'spouse401k':" + goal.getSpouse401kCont()+ ","
										+ "'limits':" + assestLimit+ "}}");
						if(finPlanData.getMarital_status().equals("Yes"))
						{							 	

							MongoDBConnection.incomeProfileCol.update("{'user_id':#,'profile_name':#}", user_id, income_profileName).upsert().multi()
							.with("{$set: {'user_income':" + userIncome + ",'spouse_income':" + spouseIncome + ",'combined_income':" + combinedIncome + "}}");
						}
						else
						{
							MongoDBConnection.incomeProfileCol.update("{'user_id':#,'profile_name':#}", user_id, income_profileName).upsert().multi()
							.with("{$set: {'user_income':" + userIncome + "}}");

						}
						jsonSSB.put("userExpectedSSB", userSsb);
						jsonSSB.put("massage", "Goal updated successfully !!");
						jsonSSB.put("status", "success");
						jsonSSB.put("plan_name", fin_name);
						jsonSSB.put("marital_status", user.getMarital_status());
						jsonSSB.put("retirementAge", goal.getRetirementAge());
						jsonSSB.put("lifeExpectancy", goal.getLifeExpectancy());
						jsonSSB.put("iraSaved", goalData.getUserSavedInIRA());
						jsonSSB.put("iraContribue", goalData.getUserContributionInIRA());
						jsonSSB.put("rothIraSaved", goalData.getUserSavedInRothIRA());
						jsonSSB.put("rothIraContribue", goalData.getUserContributionInRothIRA());
						jsonSSB.put("spouseIra", goalData.getSpouseSavedInIRA());
						jsonSSB.put("spouseIraContribue", goalData.getSpouseContributionInIRA());
						jsonSSB.put("spouserothIraSaved", goalData.getSpouseSavedInRothIRA());
						jsonSSB.put("spouserothIraContribue", goalData.getSpouseContributionInRothIRA());
						jsonSSB.put("spouserothIraContribue", goalData.getSpouseContributionInRothIRA());
						
						
						return jsonSSB;
					}
					else
					{
						jsonSSB.put("status", "fail");
					}
				} catch (Exception e) {
					systemLog.put("type", "fail");
					systemLog.put("message", "Goal updating failed");
					systemLog.put("userName", user.getName());
					systemLog.put("user_id", user.get_id());
					systemLog.put("createdTs", dateFormat.format(date));
					e.printStackTrace();
				} finally {
					BasicDBObject systemLogBasicObject = new BasicDBObject("user_id", systemLog.getString("user_id"))
							.append("userName", systemLog.getString("userName")).append("type", systemLog.getString("type"))
							.append("message", systemLog.getString("message")).append("createdTs", dateFormat.format(date));
					MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);
				}

			} else if (formType.equals("edit")) {
				try {
					int spouseRetirementAgeEdit = 0;
					int userSelectedSpouseRetirementAgeEdit=0;
					RetirementGoal goalData = MongoDBConnection.goalcoll.findOne("{_id:#}", goal.getGoal_id()).as(RetirementGoal.class);
					FinPlan finPlanData = MongoDBConnection.finplancol.findOne("{_id:#}",goalData.getFin_id()).as(FinPlan.class);

					if(finPlanData!=null)
					{

						user.setMarital_status(finPlanData.getMarital_status());
					}
					int retirementAgeEdit = goalData.getRetirementAge();
					
					if (retirementAgeEdit < 62) {
						retirementAgeEdit = 62;
					} else if (retirementAgeEdit > 70) {
						retirementAgeEdit = 70;
					}
					double userAime = goalData.getUserAIME();
					double spouseAime = 0;
					if (user.getMarital_status().equals("Yes")) {
						spouseAime = goalData.getSpouseAIME();
					}
					////////System.out.println("Marital_status inside edit===="+user.getMarital_status());
					if (user.getMarital_status().equals("Yes")) {
						spouseRetirementAgeEdit = goalData.getSpouseRetirementAge();
						userSelectedSpouseRetirementAgeEdit=spouseRetirementAgeEdit;
						if (spouseRetirementAgeEdit == 0) {
							spouseRetirementAgeEdit = 70;
						} else if (spouseRetirementAgeEdit > 0 && spouseRetirementAgeEdit < 62) {
							spouseRetirementAgeEdit = 62;
						} else if (spouseRetirementAgeEdit > 70) {
							spouseRetirementAgeEdit = 70;
						}
					}
					int useFfra = findUserFRA(retirementAge, user.getBirthYear());
					int spouseFfra = 0;
					if (user.getMarital_status().equals("Yes")) {
						spouseAime = findUserAIME( 70,spouseIncomeAraay,SpouseStartYear);
						spouseFfra = findUserFRA(retirementAge, user.getSpouseAge());
					}
					int planingHorizone = 99;

					if (user.getMarital_status().equals("Yes") && user.getSpouseBeforeTaxIncome() != 0) {
						spousJsonSSB = calculateSpouseSSB(user, useFfra, spouseFfra, firetBendPoint, secondBendPoint, userAime, spouseAime);
					}
					// //////System.out.println("user aime is========="+userAime);
					userJsonSSB = calculateUserSSB(user.getSpouseBeforeTaxIncome(), user.getMarital_status(), useFfra, spouseFfra, firetBendPoint,
							secondBendPoint, userAime, spouseAime);

					userSsb = (Double) (userJsonSSB.get("User" + retirementAgeEdit));
					
					for (int i = 62; i <= 70; i++) {
						
						jsonSSB.put("User" + i, (Double) (userJsonSSB.get("User" + i)));
						if (user.getMarital_status().equals("Yes") && user.getSpouseBeforeTaxIncome() != 0) {
							jsonSSB.put("Spouse" + i, (Double) (spousJsonSSB.get("Spouse" + i)));
							////System.out.println("Baladkjdk.....inside if..."+user.getSpouseBeforeTaxIncome()+"hello....."+spousJsonSSB.get("Spouse" + i));

						} else if (user.getMarital_status().equals("Yes") && user.getSpouseBeforeTaxIncome() == 0) {
							jsonSSB.put("Spouse" + i, (Double) (userJsonSSB.get("Spouse" + i)));

						}
					}
					jsonSSB.put("lifeExpectancy", planingHorizone);
					jsonSSB.put("spouseLifeExpectancy", goalData.getSpouseLifeExpectancy());
					jsonSSB.put("retirementAge", goalData.getRetirementAge());
					//jsonSSB.put("spouseRetirementAge", goalData.getSpouseRetirementAge());
					jsonSSB.put("spouseRetirementAge", goalData.getUserSelectedSpouseRetirementAge());
					jsonSSB.put("otherRetirementIncome", goalData.getOtherRetirementIncome());
					jsonSSB.put("spouseOtherRetirementIncome", goalData.getSpouseOtherRetirementIncome());
					jsonSSB.put("retirement_expense", goalData.getRetirement_expense());
					jsonSSB.put("status", "success");
					jsonSSB.put("massage", "Fetching Data Sucessfull");
					jsonSSB.put("marital_status", user.getMarital_status());
					jsonSSB.put("userExpectedSSB", userSsb);
					jsonSSB.put("plan_name", goalData.getPlan_name());
					jsonSSB.put("iraSaved", goalData.getUserSavedInIRA());
					jsonSSB.put("iraContribue", goalData.getUserContributionInIRA());
					jsonSSB.put("rothIraSaved", goalData.getUserSavedInRothIRA());
					jsonSSB.put("rothIraContribue", goalData.getUserContributionInRothIRA());
					jsonSSB.put("spouseIra", goalData.getSpouseSavedInIRA());
					jsonSSB.put("spouseIraContribue", goalData.getSpouseContributionInIRA());
					jsonSSB.put("spouserothIraSaved", goalData.getSpouseSavedInRothIRA());
					jsonSSB.put("spouserothIraContribue", goalData.getSpouseContributionInRothIRA());
					jsonSSB.put("userCap401", goalData.getUserCap());
					jsonSSB.put("userSavedAmount401", goalData.getUserSavedAmount());
					jsonSSB.put("userContribution401", goalData.getUserContributeAmount());
					jsonSSB.put("userMatching401", goalData.getUserMatchContribution());
					jsonSSB.put("spouseCap401", goalData.getSpouseCap());
					jsonSSB.put("spouseSavedAmount401", goalData.getSpouseSavedAmount());
					jsonSSB.put("spouseContribution401", goalData.getSpouseContributeAmount());
					jsonSSB.put("spouseMatching401", goalData.getSpouseMatchContribution());
					return jsonSSB;
				} catch (Exception e) {
					e.printStackTrace();
					jsonSSB.put("massage", "Problem Fetching Data Sucessfull");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonSSB;
	}
	public JSONObject insertRetirementIncomeNew(double userSsb,JSONObject spouseJson,String user_id, JSONArray userIncomeJsonArray,JSONArray spouseIncomeJsonArray,int userRetirementAge,
			int spouseRetirementAge,int userSelectedSpouseRetirementYear) {
		try {
			double pension = 0.0;
			double userRetirementIncome =(userSsb ) * 12.0;
			double spouseRetirementIncome = 0;
			JSONObject retirementIncome=new JSONObject();
			int tempUserAge=userRetirementAge;
			for (int i = 0; i < userIncomeJsonArray.length(); i++) {
				//////System.out.println("Bala......userIncomeJsonArray"+userIncomeJsonArray);
				/*if(userIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement")>0)
				{
				userIncomeJsonArray.getJSONObject(i).put("value", userIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement"));		
				}*/
				if (userIncomeJsonArray.getJSONObject(i).getInt("year") == userRetirementAge) {
					if(userIncomeJsonArray.getJSONObject(i).getDouble("retirement_income")==0)
					{
						userIncomeJsonArray.getJSONObject(i).put("incomeBeforeRetirement", userIncomeJsonArray.getJSONObject(i).getDouble("value"));
					}
					userIncomeJsonArray.getJSONObject(i).put("retirement_income", userRetirementIncome);
					userIncomeJsonArray.getJSONObject(i).put("value", userRetirementIncome);
					userRetirementAge++;
				} else {
					userIncomeJsonArray.getJSONObject(i).put("retirement_income", 0);
					
					if(userIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement")>0)
					{
						userIncomeJsonArray.getJSONObject(i).put("value", userIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement"));
					}
//					userIncomeJsonArray.getJSONObject(i).put("value", userIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement"));
					
				}
			}
			for (int i = 0; i < spouseIncomeJsonArray.length(); i++) {
				/*if(spouseIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement")>0)
				{
					spouseIncomeJsonArray.getJSONObject(i).put("value", spouseIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement"));		
				}*/
				if(spouseJson.getInt("spouseAime")!=0)
				{
					//System.out.println("tempUserAge=="+tempUserAge);
					//System.out.println("spouseRetirementAge=="+spouseRetirementAge);
					//System.out.println("YEAR=="+spouseIncomeJsonArray.getJSONObject(i).getInt("year"));
					//System.out.println("tempUserAge=="+tempUserAge);
					if((spouseRetirementAge==spouseIncomeJsonArray.getJSONObject(i).getInt("year")))
					{
						spouseRetirementIncome= calculateSpouseSSB(spouseJson);
						spouseRetirementIncome = (spouseRetirementIncome + pension) * 12.0;
						spouseRetirementIncome=Math.max(spouseRetirementIncome, userRetirementIncome*0.5);
					}
				}
				else
				{
					spouseRetirementIncome= userSsb/2*12;					

				}
				//System.out.println();
				if (spouseIncomeJsonArray.getJSONObject(i).getInt("year") == spouseRetirementAge) {
					//System.out.println(" year==========="+spouseIncomeJsonArray.getJSONObject(i).getInt("year"));
					//System.out.println(" spouseRetirementAge==========="+spouseRetirementAge);
					if(spouseIncomeJsonArray.getJSONObject(i).getDouble("retirement_income")==0)
					{
						spouseIncomeJsonArray.getJSONObject(i).put("incomeBeforeRetirement", spouseIncomeJsonArray.getJSONObject(i).getDouble("value"));
					}
					spouseIncomeJsonArray.getJSONObject(i).put("retirement_income", spouseRetirementIncome);
					spouseIncomeJsonArray.getJSONObject(i).put("value", spouseRetirementIncome);

					spouseRetirementAge++;
				} else {
					spouseIncomeJsonArray.getJSONObject(i).put("retirement_income", 0);
				if(spouseIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement")>0)
				{
					spouseIncomeJsonArray.getJSONObject(i).put("value", spouseIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement"));
				}			
					if (spouseIncomeJsonArray.getJSONObject(i).getInt("year") >= userSelectedSpouseRetirementYear)
					{
						spouseIncomeJsonArray.getJSONObject(i).put("incomeBeforeRetirement", spouseIncomeJsonArray.getJSONObject(i).getDouble("value"));
						spouseIncomeJsonArray.getJSONObject(i).put("value", 0);
						
					}
					
				}
			}
			JSONArray combinedIncomeJsonArray = calCombinedIncome(userIncomeJsonArray, spouseIncomeJsonArray);
			retirementIncome.put("user_income", userIncomeJsonArray);
			retirementIncome.put("spouse_income", spouseIncomeJsonArray);
			retirementIncome.put("combined_income", combinedIncomeJsonArray);
			return retirementIncome ;
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public JSONObject insertRetirementIncomeNewSingle(double userSsb,JSONObject spouseJson,String user_id, JSONArray userIncomeJsonArray,JSONArray spouseIncomeJsonArray,int userRetirementAge,
			int spouseRetirementAge) {
		try {
			double pension = 0.0;
			double userRetirementIncome =(userSsb ) * 12.0;
			double spouseRetirementIncome = 0;
			JSONObject retirementIncome=new JSONObject();
			int tempUserAge=userRetirementAge;
			for (int i = 0; i < userIncomeJsonArray.length(); i++) {
				//////System.out.println("Bala......userIncomeJsonArray"+userIncomeJsonArray);
				/*if(userIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement")>0)
				{
				userIncomeJsonArray.getJSONObject(i).put("value", userIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement"));		
				}*/
				if (userIncomeJsonArray.getJSONObject(i).getInt("year") == userRetirementAge) {
					if(userIncomeJsonArray.getJSONObject(i).getDouble("retirement_income")==0)
					{
						userIncomeJsonArray.getJSONObject(i).put("incomeBeforeRetirement", userIncomeJsonArray.getJSONObject(i).getDouble("value"));
					}
					userIncomeJsonArray.getJSONObject(i).put("retirement_income", userRetirementIncome);
					userIncomeJsonArray.getJSONObject(i).put("value", userRetirementIncome);
					userRetirementAge++;
				} else {
					userIncomeJsonArray.getJSONObject(i).put("retirement_income", 0);
//					userIncomeJsonArray.getJSONObject(i).put("value", userIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement"));
					if(userIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement")>0)
					{
						userIncomeJsonArray.getJSONObject(i).put("value", userIncomeJsonArray.getJSONObject(i).getDouble("incomeBeforeRetirement"));
					}
				}
			}
			
			JSONArray combinedIncomeJsonArray = userIncomeJsonArray;
			retirementIncome.put("user_income", userIncomeJsonArray);
			retirementIncome.put("spouse_income", spouseIncomeJsonArray);
			retirementIncome.put("combined_income", combinedIncomeJsonArray);
			return retirementIncome ;
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
	// -----------------------method for new user retirement-------------------------------
	public JSONArray insertUserRetirementIncomeNew(JSONArray userIncomeJsonArray, String user_id, String income_profileName, double userSsb, int userRetirementAge) {
		try {
			long pension = 0;
			long userRetirementIncome = (long) ((userSsb + pension) * 12.0);
			if(income_profileName!=null)
			{
				IncomeProfile incomeProfile = MongoDBConnection.incomeProfileCol.findOne("{'user_id':#,'profile_name':#}", user_id, income_profileName).as(
						IncomeProfile.class);

				JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfile));
				userIncomeJsonArray = userJson.getJSONArray("user_income");
			}
			for (int i = 0; i < userIncomeJsonArray.length(); i++) {
				if (userIncomeJsonArray.getJSONObject(i).getInt("year") == userRetirementAge) {
					if(userIncomeJsonArray.getJSONObject(i).getDouble("retirement_income")==0)
					{
						userIncomeJsonArray.getJSONObject(i).put("incomeBeforeRetirement", userIncomeJsonArray.getJSONObject(i).getDouble("value"));
					}
					userIncomeJsonArray.getJSONObject(i).put("retirement_income", userRetirementIncome);
					userIncomeJsonArray.getJSONObject(i).put("value", userRetirementIncome);
					userRetirementAge++;
				} else {
					userIncomeJsonArray.getJSONObject(i).put("retirement_income", 0);
				}
			}
			return userIncomeJsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return userIncomeJsonArray;
		}
	}
	// calCombinedIncome=================================
	public JSONArray calCombinedIncome(JSONArray user_income, JSONArray spouse_income) {
		try {
			JSONArray combined_income = new JSONArray();
			int spouse_length = spouse_income.length();
			int user_length = user_income.length();
			int i = 0;
			int j = 0;
			while (i < user_length && j < spouse_length) {
				JSONObject yearlyincome = new JSONObject();
				if (user_income.getJSONObject(i).getInt("year") < spouse_income.getJSONObject(j).getInt("year")) {
					yearlyincome.put("year", user_income.getJSONObject(i).getInt("year"));
					yearlyincome.put("value", (user_income.getJSONObject(i).getDouble("value")));
					if (user_income.getJSONObject(i).getDouble("retirement_income") != 0.0) {
						yearlyincome.put("retirement_income", (user_income.getJSONObject(i).getDouble("retirement_income")));
					} else {
						yearlyincome.put("retirement_income", 0.0);
					}
					combined_income.put(yearlyincome);
					i++;
				} else if (user_income.getJSONObject(i).getInt("year") == spouse_income.getJSONObject(j).getInt("year")) {
					// //////////System.out.println(user_income.getJSONObject(i).getDouble("retirement_income"));
					yearlyincome.put("year", spouse_income.getJSONObject(j).getInt("year"));
					yearlyincome.put("value", (user_income.getJSONObject(i).getDouble("value")) + (spouse_income.getJSONObject(j).getDouble("value")));
					if (user_income.getJSONObject(i).getDouble("retirement_income") != 0.0
							&& spouse_income.getJSONObject(i).getDouble("retirement_income") != 0.0) {
						yearlyincome.put("retirement_income",
								(user_income.getJSONObject(i).getDouble("retirement_income") + (spouse_income.getJSONObject(i).getLong("retirement_income"))));
					} else if (user_income.getJSONObject(i).getDouble("retirement_income") != 0.0
							&& spouse_income.getJSONObject(i).getLong("retirement_income") == 0.0) {
						yearlyincome.put("retirement_income", (user_income.getJSONObject(i).getDouble("retirement_income") + 0.0));

					} else if (user_income.getJSONObject(i).getDouble("retirement_income") == 0.0
							&& spouse_income.getJSONObject(i).getDouble("retirement_income") != 0.0) {
						yearlyincome.put("retirement_income", (spouse_income.getJSONObject(i).getDouble("retirement_income") + 0.0));

					} else {
						yearlyincome.put("retirement_income", 0.0);
					}
					j++;
					i++;
				} else if (user_income.getJSONObject(i).getInt("year") > spouse_income.getJSONObject(j).getInt("year")) {
					yearlyincome.put("year", spouse_income.getJSONObject(j).getInt("year"));
					yearlyincome.put("value", spouse_income.getJSONObject(j).getDouble("value"));
					if (spouse_income.getJSONObject(i).getDouble("retirement_income") != 0.0) {
						yearlyincome.put("retirement_income", (spouse_income.getJSONObject(i).getDouble("retirement_income")));
					} else {
						yearlyincome.put("retirement_income", 0.0);
					}
					j++;
				} else {
					yearlyincome.put("retirement_income", 0.0);
				}
				combined_income.put(yearlyincome);
			}
			while (i < user_length) {
				JSONObject yearlyincome = new JSONObject();
				yearlyincome.put("year", user_income.getJSONObject(i).getInt("year"));
				yearlyincome.put("value", (user_income.getJSONObject(i).getDouble("value")));
				if (user_income.getJSONObject(i).getDouble("retirement_income") != 0.0) {
					yearlyincome.put("retirement_income", (user_income.getJSONObject(i).getDouble("retirement_income")));
				} else {
					yearlyincome.put("retirement_income", 0.0);
				}
				combined_income.put(yearlyincome);
				i++;
			}
			while (j < spouse_length) {
				JSONObject yearlyincome = new JSONObject();
				yearlyincome.put("year", spouse_income.getJSONObject(j).getInt("year"));
				yearlyincome.put("value", (spouse_income.getJSONObject(j).getDouble("value")));
				if (spouse_income.getJSONObject(i).getDouble("retirement_income") != 0) {
					yearlyincome.put("retirement_income", (spouse_income.getJSONObject(i).getDouble("retirement_income")));
				} else {
					yearlyincome.put("retirement_income", 0.0);
				}
				combined_income.put(yearlyincome);
				j++;
			}
			// //////System.out.println("combined_income----------="+combined_income);
			return combined_income;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public int findUserFRA(int retirementAge,int yearOfBirth) {
		int fra;

		try {

			if (yearOfBirth >= 1943 && yearOfBirth <= 1959) {
				// ////////System.out.println(yearOfBirth);
				fra = 66;
				return fra;
			} else if (yearOfBirth > 1959) {
				fra = 67;
				return fra;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	public double findUserAIME( int retirementAge,JSONArray incomeJsonArray,int endYear) {
		////System.out.println("BALA###"+incomeJsonArray);
		
	//	//System.out.println("retirementAge"+retirementAge);
	//	//System.out.println("End Year::"+endYear);
		double userAime = 0;
		try {
			int numberOfWorkingYear = 0;
			DecimalFormat decimalFloat = new DecimalFormat("#.##");
			double incomeArray[] = new double[100];
			int incomeStartYear = incomeJsonArray.getJSONObject(0).getInt("year");
		//	//System.out.println("incomeStartYear:"+incomeStartYear);
			numberOfWorkingYear = endYear - incomeStartYear;
	//		//System.out.println("numberOfWorkingYear========="+numberOfWorkingYear);
			try {
				System.out.print("inside user AIME===========one");
				for (int i = 0; i < numberOfWorkingYear; i++) {
					////System.out.println("New::::"+incomeJsonArray.getJSONObject(i).getInt("value"));
		//			System.out.print(incomeJsonArray.getJSONObject(i).getInt("value")+"   ");
					int incomeYear = incomeJsonArray.getJSONObject(i).getInt("year");
					double userIncome = incomeJsonArray.getJSONObject(i).getInt("value");
					////System.out.println("Krishna incomeYear::"+incomeYear);
					AIMEIndexingFactor indexFactor = MongoDBConnection.indexFactor.findOne("{'year':#}", incomeYear).as(AIMEIndexingFactor.class);
					if (indexFactor == null) {
						incomeArray[i] = userIncome * 1;
					} else {
						incomeArray[i] = userIncome * indexFactor.getIndexingFactor();

					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			Arrays.sort(incomeArray);
			int arrayLength = incomeArray.length;
			int j = 1;
			////System.out.println("inside user AIME===========");
			for (int i=arrayLength-1;i>=0;i--) 
			{
				System.out.print(incomeArray[i]+"   ");
				userAime=userAime+incomeArray[i];
				if(j==numberOfWorkingYear || j==35)
				{
					break;
				}
				j++;
			}
		//	//System.out.println("userAime::"+userAime);
			userAime=Double.parseDouble(decimalFloat.format(userAime/420));
	//		//System.out.println("userAime=============="+userAime);
			if(userAime<0)
			{
				userAime=0;
			}
			return userAime;
		} catch (Exception e) {
			e.printStackTrace();
			return userAime;
		}
	}
	public JSONObject calculateUserSSB(double spouseIncome, String marritalStatus, int useFfra, int spouseFfra, double firetBendPoint, double secondBendPoint,
			double userAime, double spouseAime) {
		// //////System.out.println("userAime=============="+userAime);
		if (spouseFfra == 0) {
			spouseFfra = useFfra;
		}
		JSONObject jsonSSB = new JSONObject();
		double userSsb = 0;
		double spouseSsb = 0;
		try {
			////System.out.println("spouse income============="+spouseIncome);
			userSsb = calculateSSB(firetBendPoint, secondBendPoint, userAime);
			if(userSsb<0)
			{
				userSsb=0;
			}
			if (useFfra == 66) {
				// userSsb=0.9*firetBendPoint+0.32*(secondBendPoint-firetBendPoint)+0.15*(userAime-secondBendPoint);
				spouseSsb = userSsb;
				jsonSSB.put("User62",userSsb * 0.75);
				jsonSSB.put("User63",userSsb * 0.8);
				jsonSSB.put("User64", userSsb * 0.867);
				jsonSSB.put("User65", userSsb * 0.933);
				jsonSSB.put("User66", userSsb * 1.0);
				jsonSSB.put("User67", userSsb * 1.08);
				jsonSSB.put("User68", userSsb * 1.16);
				jsonSSB.put("User69", userSsb * 1.24);
				jsonSSB.put("User70", userSsb * 1.32);
				//////System.out.println("marital_status ===="+Double.parseDouble(decimalFloat.format(userSsb * 1.32)));
				if (marritalStatus.equals("Yes")) {

					if (spouseIncome == 0) {

						spouseSsb = userSsb;
						jsonSSB.put("Spouse62", spouseSsb * 0.325);
						jsonSSB.put("Spouse63", spouseSsb * 0.35);
						jsonSSB.put("Spouse64", spouseSsb * 0.375);
						jsonSSB.put("Spouse65", spouseSsb * 0.417);
						jsonSSB.put("Spouse66", spouseSsb * 0.458);
						jsonSSB.put("Spouse67", spouseSsb * 0.5);
						jsonSSB.put("Spouse68", spouseSsb * 0.5);
						jsonSSB.put("Spouse69", spouseSsb * 0.5);
						jsonSSB.put("Spouse70", spouseSsb * 0.5);
					}
				}

			} else if (useFfra == 67) {
				// userSsb=0.9*firetBendPoinFica
				// Medicaret+0.32*(secondBendPoint-firetBendPoint)+0.15*(userAime-secondBendPoint);
				spouseSsb = userSsb;
				jsonSSB.put("User62", userSsb * 0.7);
				jsonSSB.put("User63", userSsb * 0.75);
				jsonSSB.put("User64", userSsb * 0.8);
				jsonSSB.put("User65", userSsb * 0.867);
				jsonSSB.put("User66", userSsb * 0.933);
				jsonSSB.put("User67", userSsb * 1.0);
				jsonSSB.put("User68", userSsb * 1.08);
				jsonSSB.put("User69", userSsb * 1.16);
				jsonSSB.put("User70", userSsb * 1.24);
				if (marritalStatus.equals("Yes")) {

					if (spouseIncome == 0) {
						// //////System.out.println("inside spouse income ==0");
						spouseSsb = userSsb;
						jsonSSB.put("Spouse62", spouseSsb * 0.325);
						jsonSSB.put("Spouse63", spouseSsb * 0.35);
						jsonSSB.put("Spouse64", spouseSsb * 0.375);
						jsonSSB.put("Spouse65", spouseSsb * 0.417);
						jsonSSB.put("Spouse66", spouseSsb * 0.458);
						jsonSSB.put("Spouse67", spouseSsb * 0.5);
						jsonSSB.put("Spouse68", spouseSsb * 0.5);
						jsonSSB.put("Spouse69", spouseSsb * 0.5);
						jsonSSB.put("Spouse70", spouseSsb * 0.5);
					}
				}
			}
			// jsonSSB.put("status","success");

			//////System.out.println("aaaaaaa+++++++++"+jsonSSB);
			return jsonSSB;
		} catch (Exception e) {

			e.printStackTrace();
			return jsonSSB;
		}
	}

	// ================================spouse ssb
	// calculation=====================================
	public JSONObject calculateSpouseSSB(User user, int useFfra, int spouseFfra, double firetBendPoint, double secondBendPoint, double userAime,
			double spouseAime) {
		if (spouseFfra == 0) {
			spouseFfra = useFfra;
		}
		JSONObject jsonSSB = new JSONObject();

		double spouseSsb = 0;
		try {
			//DecimalFormat decimalFloat = new DecimalFormat("#.##");

			spouseSsb = calculateSSB(firetBendPoint, secondBendPoint, spouseAime);
			//System.out.println("spouseSsb    "+spouseSsb);
			if(spouseSsb<0)
			{
				spouseSsb=0;
			}
			if (spouseFfra == 66) {
				// spouseSsb=0.9*firetBendPoint+0.32*(secondBendPoint-firetBendPoint)+0.15*(spouseAime-secondBendPoint);
				jsonSSB.put("Spouse62", spouseSsb * 0.75);
				jsonSSB.put("Spouse63", spouseSsb * 0.8);
				jsonSSB.put("Spouse64", spouseSsb * 0.867);
				jsonSSB.put("Spouse65", spouseSsb * 0.933);
				jsonSSB.put("Spouse66", spouseSsb * 1.0);
				jsonSSB.put("Spouse67", spouseSsb * 1.08);
				jsonSSB.put("Spouse68", spouseSsb * 1.16);
				jsonSSB.put("Spouse69", spouseSsb * 1.24);
				jsonSSB.put("Spouse70", spouseSsb * 1.32);


			} else if (spouseFfra == 67) {
				// spouseSsb=0.9*firetBendPoint+0.3Fica
				// Medicare2*(secondBendPoint-firetBendPoint)+0.15*(spouseAime-secondBendPoint);
				jsonSSB.put("Spouse62", spouseSsb * 0.7);
				jsonSSB.put("Spouse63", spouseSsb * 0.75);
				jsonSSB.put("Spouse64", spouseSsb * 0.8);
				jsonSSB.put("Spouse65", spouseSsb * 0.867);
				jsonSSB.put("Spouse66", spouseSsb * 0.933);
				jsonSSB.put("Spouse67", spouseSsb * 1.0);
				jsonSSB.put("Spouse68", spouseSsb * 1.08);
				jsonSSB.put("Spouse69", spouseSsb * 1.16);
				jsonSSB.put("Spouse70", spouseSsb * 1.24);

			}

			////System.out.println("aaaaaaa+++++++++"+jsonSSB);
			return jsonSSB;
		} catch (Exception e) {

			e.printStackTrace();
			return jsonSSB;
		}
	}
	//============new ssb cal for spouse=============================
	public double calculateSpouseSSB(JSONObject spouseJson) {
		//////System.out.println("userAime======Fica Medicare========"+userAime);
		int spouseFfra =0;
		int spouseAge=0;

		JSONObject jsonSSB = new JSONObject();

		double spouseSsb = 0;
		try {
			if (spouseJson.getInt("spouseFfra")== 0) {
				spouseFfra = spouseJson.getInt("useFfra");

			}
			else
			{
				spouseFfra = spouseJson.getInt("spouseFfra");
			}
			if(spouseJson.getInt("spouseRetirementAge")==0)
			{
				spouseAge=70;
			}
			else
			{
				spouseAge=spouseJson.getInt("spouseRetirementAge");
			}
			//DecimalFormat decimalFloat = new DecimalFormat("#.##");

			spouseSsb = calculateSSB(spouseJson.getInt("firetBendPoint"), spouseJson.getInt("secondBendPoint"),spouseJson.getInt("spouseAime") );
			//System.out.println("spouseSsb    "+spouseSsb);
			if(spouseSsb<0)
			{
				spouseSsb=0;
			}
			if (spouseFfra == 66) {

				jsonSSB.put("Spouse62", spouseSsb * 0.75);
				jsonSSB.put("Spouse63", spouseSsb * 0.8);
				jsonSSB.put("Spouse64", spouseSsb * 0.867);
				jsonSSB.put("Spouse65", spouseSsb * 0.933);
				jsonSSB.put("Spouse66", spouseSsb * 1.0);
				jsonSSB.put("Spouse67", spouseSsb * 1.08);
				jsonSSB.put("Spouse68", spouseSsb * 1.16);
				jsonSSB.put("Spouse69", spouseSsb * 1.24);
				jsonSSB.put("Spouse70", spouseSsb * 1.32);
				jsonSSB.put("spouseSSB",jsonSSB.getDouble("Spouse"+spouseAge));


			} else if (spouseFfra == 67) {

				jsonSSB.put("Spouse67", spouseSsb);
				jsonSSB.put("Spouse62", spouseSsb * 0.7);
				jsonSSB.put("Spouse63", spouseSsb * 0.75);
				jsonSSB.put("Spouse64", spouseSsb * 0.8);
				jsonSSB.put("Spouse65", spouseSsb * 0.867);
				jsonSSB.put("Spouse66", spouseSsb * 0.933);
				jsonSSB.put("Spouse67", spouseSsb * 1.0);
				jsonSSB.put("Spouse68", spouseSsb * 1.08);
				jsonSSB.put("Spouse69", spouseSsb * 1.16);
				jsonSSB.put("Spouse70", spouseSsb * 1.24);
				jsonSSB.put("spouseSSB",jsonSSB.getDouble("Spouse"+spouseAge));
			}
			////System.out.println("spouse ssb==="+jsonSSB.getDouble("spouseSSB"));
			return jsonSSB.getDouble("spouseSSB");

		}
		catch (Exception e)
		{

			e.printStackTrace();
			return 0;
		}
	}

	// =========================calculate Income
	// SSB======================================
	public double calculateIncomeSSB(double spouseSsb, double userSsb, int yearOfBirth) {
		/*
		 * JSONArray incomeJSONArray=null; JSONObject incomeJsonSSB=null;
		 */
		double incomeSSB = 0;
		try {
			incomeSSB = spouseSsb * 12.0 + userSsb * 12.0;
			//System.out.println("userSSb=="+userSsb+"==spousessb=="+spouseSsb+"==incomessb==="+incomeSSB);
			if(incomeSSB<0)
			{
				incomeSSB=0;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return incomeSSB;
	}

	// =========================calculate calculate income
	// IRA======================================


	// SSB=====================================




	public JSONObject deleteRetirementGoalOnUpdate(String marritalStatus,String profileName, String user_id) {
		//////System.out.println("inside retirement goal delete");
		JSONObject afterDeleteIncome=new JSONObject();
		try {
			User user = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
			IncomeProfile incomeProfile = MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}", user_id, profileName)
					.as(IncomeProfile.class);
			JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfile));
			JSONArray userIcomeArray = userJson.getJSONArray("user_income");


			JSONArray spouseIncomeArray = new JSONArray();
			if (user.getMarital_status().equals("Yes") || marritalStatus.equals("Yes")) {
				spouseIncomeArray = userJson.getJSONArray("spouse_income");
				//////System.out.println("innerJson for Pesonal Details"+spouseIncomeArray);
				for (int i = 0; i < spouseIncomeArray.length(); i++) {

					double retirement_income = spouseIncomeArray.getJSONObject(i).getDouble("retirement_income");
					// value=spouseIncomeArray.getJSONObject(i).getDouble("value");
					int year = spouseIncomeArray.getJSONObject(i).getInt("year");
					//////System.out.println("spouse retirement income====="+spouseIncomeArray.toString());
					if (retirement_income != 0) {

						spouseIncomeArray.getJSONObject(i).put("retirement_income", 0.0);
						spouseIncomeArray.getJSONObject(i).put("value", spouseIncomeArray.getJSONObject(i).getDouble("incomeBeforeRetirement"));
						spouseIncomeArray.getJSONObject(i).put("year", year);

					}
				}
			}
			//////System.out.println("innerJson for Pesonal Details"+userIcomeArray);
			for (int i = 0; i < userIcomeArray.length(); i++) {

				double retirement_income = userIcomeArray.getJSONObject(i).getDouble("retirement_income");
				// double
				// value=userIcomeArray.getJSONObject(i).getDouble("value");
				int year = userIcomeArray.getJSONObject(i).getInt("year");
				if (retirement_income != 0) {

					userIcomeArray.getJSONObject(i).put("retirement_income", 0.0);
					userIcomeArray.getJSONObject(i).put("value", userIcomeArray.getJSONObject(i).getDouble("incomeBeforeRetirement"));
					userIcomeArray.getJSONObject(i).put("year", year);

				}
			}
			if (user.getMarital_status().equals("Yes")||marritalStatus.equals("Yes")) {
				JSONArray combinedIncomeJsonArray = calCombinedIncome(userIcomeArray, spouseIncomeArray);
				afterDeleteIncome.put("user_income", userIcomeArray);
				afterDeleteIncome.put("spouse_income", spouseIncomeArray);
				afterDeleteIncome.put("combined_income", combinedIncomeJsonArray);



			} else {
				afterDeleteIncome.put("user_income", userIcomeArray);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return afterDeleteIncome;

	}

	// ;----------------------------------------------------------------
	public JSONArray expenseRecalculate(String fin_id) {
		// //////System.out.println("------------inside retirement expense recalculate----------------");
		JSONArray expense = new JSONArray();
		try {
		    /*/ MongoCollection finplan=jongo.getCollection(fincollection); /*/
			FinPlan finPlan = MongoDBConnection.finplancol.findOne("{_id:#}", fin_id).as(FinPlan.class);
			JSONObject userJson = new JSONObject(jsonMapper.writeValueAsString(finPlan));
			// ////////System.out.println("innerJson for Pesonal Details"+userJson.toString()
			// );
			expense = userJson.getJSONArray("userExpense");
			JSONObject expenses=new JSONObject();
			JSONArray houseExpense=new JSONArray();
			expenses=userJson.getJSONObject("expense");
			if (!expenses.isNull("housing_expense")) {
			houseExpense= userJson.getJSONObject("expense").getJSONArray("housing_expense");
			}
			int houseEndYear=0;

			if (houseExpense!=null)

			{
				for(int i=0;i<houseExpense.length();i++)
				{
				houseEndYear=houseExpense.getJSONObject(i).getInt("endYear");
				}

				////System.out.println("Bala...houseEnd year"+houseEndYear);

			}	
			// JSONArray
			// combinedIncomeArray=userJson.getJSONArray("combined_income");
			double newExpense = 0;
			for (int i = 0; i < expense.length(); i++) {
				if (expense.getJSONObject(i).getDouble("mortgageExpense") > 0) {
					if (expense.getJSONObject(i).getDouble("kidExpense") > 0) {
						newExpense = finPlan.getUserExpense().get(i).getKidExpense() + finPlan.getUserExpense().get(i).getMortgageExpense() + finPlan.getUserExpense().get(i).getAfterMarriageExpense() + finPlan.getUserExpense().get(i).getNonHousingExpense();
					} else {
						newExpense = finPlan.getUserExpense().get(i).getMortgageExpense() + finPlan.getUserExpense().get(i).getAfterMarriageExpense() + finPlan.getUserExpense().get(i).getNonHousingExpense();
					}
				} else {
					if (expense.getJSONObject(i).getDouble("kidExpense") > 0) {
		if(houseEndYear==0)

						{

							newExpense = finPlan.getUserExpense().get(i).getHousingExpense() + finPlan.getUserExpense().get(i).getAfterMarriageExpense() + finPlan.getUserExpense().get(i).getNonHousingExpense();

						}

						else	
if(expense.getJSONObject(i).getInt("year")<=houseEndYear&&houseEndYear!=0)

						{
			newExpense = finPlan.getUserExpense().get(i).getHousingExpense() + finPlan.getUserExpense().get(i).getKidExpense() + finPlan.getUserExpense().get(i).getAfterMarriageExpense() + finPlan.getUserExpense().get(i).getNonHousingExpense();
}
else

						{

							newExpense = finPlan.getUserExpense().get(i).getKidExpense() + finPlan.getUserExpense().get(i).getAfterMarriageExpense() + finPlan.getUserExpense().get(i).getNonHousingExpense();

						}
					} else {
if(houseEndYear==0)

						{

							newExpense = finPlan.getUserExpense().get(i).getHousingExpense() + finPlan.getUserExpense().get(i).getAfterMarriageExpense() + finPlan.getUserExpense().get(i).getNonHousingExpense();

						}

						else
if(expense.getJSONObject(i).getInt("year")<=houseEndYear&&houseEndYear!=0)

						{
						newExpense = finPlan.getUserExpense().get(i).getHousingExpense() + finPlan.getUserExpense().get(i).getAfterMarriageExpense() + finPlan.getUserExpense().get(i).getNonHousingExpense();
					}
else

						{

						newExpense = finPlan.getUserExpense().get(i).getAfterMarriageExpense() + finPlan.getUserExpense().get(i).getNonHousingExpense();

						}

				}
}
				expense.getJSONObject(i).put("totalExpense", newExpense);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return expense;
	}

	public double calculateSSB(double firstBendPoint, double secondBendPoint, double aime) {
		double ssb = 0;
		////System.out.println("firstBendPoint----="+firstBendPoint);
		////System.out.println("secondBendPoint----="+secondBendPoint);
		////System.out.println("aime----="+aime);
		try {
			if (aime <= firstBendPoint) {
				ssb = (aime) * 0.9;
			} else if (aime > firstBendPoint && aime <= secondBendPoint) {
				ssb = firstBendPoint * 0.9 + (aime - firstBendPoint) * 0.32;
			} else {
				ssb = firstBendPoint * 0.9 + (secondBendPoint - firstBendPoint) * 0.32 + (aime - secondBendPoint) * 0.15;
			}
			////System.out.println("ssb in goal service implementation-------------"+ssb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ssb;
	}

	// ==================update retirement after Marriage goal
	// creation======================================

}
