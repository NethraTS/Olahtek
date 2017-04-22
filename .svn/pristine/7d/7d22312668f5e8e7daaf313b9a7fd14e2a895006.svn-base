package com.mongorest.olahtek.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONArray;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongorest.olahtek.model.Counters;
import com.mongorest.olahtek.model.Emergencyfundmodel;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.Marriagegoalmodel;
import com.mongorest.olahtek.model.User;

@Service("marriageGoalimpl")
@Transactional
public class MarriageGoalimpl {

	String systemLog;
	String incomeProfileCollection;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	ObjectMapper jsonMapper = new ObjectMapper();
	DecimalFormat decimalFloat = new DecimalFormat("#.##");
	String profile_name=null;
	String filingStatus=null;
	String [] goalsArray=null;
	@JsonCreator
	public JSONObject insertMarriageGoalData(Marriagegoalmodel marriagegoalmodel) {
		//System.out.println("inside mrg");
		JSONObject responseToRestController = new JSONObject();
		String goal_id;
		String user_id = marriagegoalmodel.getUser_id();
		String plan_name = marriagegoalmodel.getPlan_name();
		Calendar cal = Calendar.getInstance();
		//JSONObject calAssestResponse=new JSONObject();
		int currentYear = cal.get(Calendar.YEAR);
		long annualExcess = marriagegoalmodel.getAnnualExcess();
		long spouceIncome = marriagegoalmodel.getSpouseIncome();
		try {
			responseToRestController.put("status", "fail");
			String actionHomeType = marriagegoalmodel.getActionHomeType();
			if (actionHomeType.equals("insert")) {
				//-----------------------------create of the marriage goal-----------------------------------------------------------------
				int marriageYear = marriagegoalmodel.getMarriageYear();
				int SpouseStartYear=0;
				long marriageCost = marriagegoalmodel.getMarriageCost();
				FinPlan finDetails = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}", user_id, plan_name).as(FinPlan.class);
				String fin_id=finDetails.get_id();
				User Details = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
				int userAge=Details.getAge();
				int userStartYear=currentYear-userAge+70;
				int spouseAge=marriagegoalmodel.getSpouseAge();
				//System.out.println("Mahendra: spouseAge in marrige goal: " + spouseAge);
				JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
				JSONArray assets=finPlanJson.getJSONArray("assests");
				JSONArray tax=finPlanJson.getJSONArray("tax");
				JSONArray limits=finPlanJson.getJSONArray("limits");
				//System.out.println("Mahi Limits before;  "+limits);
				JSONArray deductions=finPlanJson.getJSONArray("deductions");
				JSONArray fillingExemtion=finPlanJson.getJSONArray("fillingExemtion");
				JSONArray userExpense=finPlanJson.getJSONArray("userExpense");
				JSONArray kidBirthYear=finPlanJson.getJSONArray("kidBirthYear");
				JSONArray expenseObj=new JSONArray();
				JSONObject expense=new JSONObject();
				expense=finPlanJson.getJSONObject("expense");
				if (!expense.isNull("housing_expense")) {
				expenseObj=finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
				}
				//System.out.println("Bala .... Marriage expense obj created..."+expenseObj);
				String finPlanProfileName=finDetails.getProfile_name();

				IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}",user_id ,finPlanProfileName).as(IncomeProfile.class);
				JSONObject incomeProfileJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
				JSONArray userIncome=incomeProfileJson.getJSONArray("user_income");
				JSONArray equity =incomeProfileJson.getJSONArray("equity");
				JSONArray userPlot=incomeProfileJson.getJSONArray("userPlot");
				double housingExpenseRate=incomeProfileDetails.getHousingExpense();
				double nonHousingExpenseRate=incomeProfileDetails.getNonHousingExpense();
				int editExpenseFlag=incomeProfileDetails.getEditExpenseFlag();
				double user401k=finDetails.getUser401k();
				double spouse401k=18000;
				int ageAtRegistration=Details.getAge();
				int spouseAgeForLimits=spouseAge;


				//System.out.println("Mahi limits inside Marrige goal :"+limits);

				//System.out.println("fillingExemtion.."+fillingExemtion);
				//System.out.println("getNewMonthlyExpense.."+marriagegoalmodel.getHousingExpenses());
				//System.out.println("nonHousingExpense.."+marriagegoalmodel.getNonHousingExpense());

				JSONArray SpouseIncomeArray=new JSONArray();

			int birthYear = marriagegoalmodel.getMarriageYear() - spouseAge;

				//System.out.println("Balla current year"+birthYear+"current year"+currentYear+"spouse age"+spouseAge);

				SpouseStartYear=(currentYear-spouseAge)+70;
				int incomeStartYear=birthYear+spouseAge;
				int incomeEndYear = birthYear + 99;
				int startYear = incomeStartYear;
				int endYear = incomeEndYear;
				for (int i = 0; i < userIncome.length(); i++) {
					if (userIncome.getJSONObject(i).getInt("year") >= startYear&&userIncome.getJSONObject(i).getInt("year") <= endYear) {
						JSONObject incomeJson = new JSONObject();
						incomeJson.put("year", (startYear));
						incomeJson.put("value", (marriagegoalmodel.getSpouseIncome()));
						incomeJson.put("retirement_income", 0);
						incomeJson.put("incomeBeforeRetirement", 0);
						SpouseIncomeArray.put(incomeJson);
						startYear++;
					} else {
						JSONObject incomeJson = new JSONObject();
						incomeJson.put("year", (userIncome.getJSONObject(i).getInt("year")));
						incomeJson.put("value", 0);
						incomeJson.put("retirement_income", 0);
						incomeJson.put("incomeBeforeRetirement", 0);
						SpouseIncomeArray.put(incomeJson);
					}
				}
				 //System.out.println("spouse and use Start year"+SpouseStartYear+"......."+userStartYear);
				//------------------------for retirement ---------------------------------------------------
				RetirementGoalServiceImpl goalservice=new RetirementGoalServiceImpl();
				double userAIME=goalservice.findUserAIME( 70,userIncome,userStartYear);
				int userFRA=goalservice.findUserFRA(70, Details.getBirthYear());
				 JSONObject userJsonSSB = goalservice.calculateUserSSB(marriagegoalmodel.getSpouseIncome(), "Yes", userFRA, 0, 856,5157, userAIME, 0);


				    JSONObject spouseJson=new JSONObject();
					spouseJson.put("firetBendPoint", 856);
					spouseJson.put("spouseRetirementAge",70);
					spouseJson.put("userRetirementAge", 70);
					spouseJson.put("useFfra", userFRA);
					spouseJson.put("spouseFfra", goalservice.findUserFRA(70, Details.getSpouseBirthYear()));
					spouseJson.put("userAime",userAIME);
					spouseJson.put("spouseAime", goalservice.findUserAIME( 70,SpouseIncomeArray,SpouseStartYear));
					spouseJson.put("secondBendPoint", 5157);
					int retirementAge=70;
					long retirement_expense=0;
					double newUser401k=0;
					double newSpouse401k=0;
					double newUserIra=0;
					double newSpouseIra=0;
					RetirementGoal retirementObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",finDetails.get_id(),"Retirement").as(RetirementGoal.class);
					if(retirementObj!=null)
					{
						userStartYear=(currentYear-Details.getAge())+retirementObj.getRetirementAge();
						retirementAge=retirementObj.getRetirementAge();
						retirement_expense=retirementObj.getRetirement_expense();
						newUser401k=retirementObj.getUserContributeAmount();
						newSpouse401k=retirementObj.getSpouseContributeAmount();
						newUserIra=retirementObj.getUserContributionInIRA();
						newSpouseIra=retirementObj.getSpouseContributionInIRA();
					}
					else{
						userStartYear=(currentYear-Details.getAge())+70;
					}

				     JSONArray combinedIncome=new JSONArray();
				     JSONObject allIncomes = goalservice.insertRetirementIncomeNew(((Double)(userJsonSSB.get("User"+retirementAge))),spouseJson,user_id,userIncome,SpouseIncomeArray,userStartYear, SpouseStartYear,SpouseStartYear);
				     //System.out.println("allIncomes====="+allIncomes);
				     userIncome=allIncomes.getJSONArray("user_income");
				     SpouseIncomeArray=allIncomes.getJSONArray("spouse_income");
				     combinedIncome=allIncomes.getJSONArray("combined_income");




				//JSONArray combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, SpouseIncomeArray);
				//System.out.println("spouse_income..."+SpouseIncomeArray+"user_Income..."+userIncome+"combined_Incme.."+combinedIncome);
				int emergencyFundAmt=finDetails.getEmergencyFundAmt();
				boolean emergencyFundFlag=finDetails.isEmergencyFundFlag();
			//	CalculationEngineService obj=new CalculationEngineService();
				String maritalStatus="Yes";



				JSONObject retirementData=new JSONObject();
				//System.out.println("Bala... spouse start year ..."+SpouseStartYear+"..user start Year..."+userStartYear);
				retirementData.put("spouseStartYear", SpouseStartYear);
				retirementData.put("userStartYear", userStartYear);
				JSONObject expenseHouse=new JSONObject();
				JSONArray houseExpense=null;
				expenseHouse=finPlanJson.getJSONObject("expense");
				if (!expenseHouse.isNull("housing_expense")) {
				 houseExpense= finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
				}

				int houseEndYear=0;

				if (houseExpense!=null)

				{

					for(int i=0;i<houseExpense.length();i++)
					{
					houseEndYear=houseExpense.getJSONObject(i).getInt("endYear");
					}


					//System.out.println("Bala...houseEnd year"+houseEndYear);

				}
				//-------------------end of adding the retirement goal dependency--------------------------------------
				for(int i=0;i<limits.length();i++)
				{
					if(marriageYear>currentYear+4)
					{
						if(limits.getJSONObject(i).getInt("year")<=marriageYear)
						{
						limits.getJSONObject(i).put("taxable", marriageCost+limits.getJSONObject(i).getInt("taxable"));
						}
						if(deductions.getJSONObject(i).getInt("year")==marriageYear)
						{
							deductions.getJSONObject(i).put("taxable", marriageCost+deductions.getJSONObject(i).getDouble("taxable"));
						}
					}
					else
					{
						if(limits.getJSONObject(i).getInt("year")<marriageYear)
						{
						limits.getJSONObject(i).put("saving", marriageCost+limits.getJSONObject(i).getInt("saving"));
						}
						if(deductions.getJSONObject(i).getInt("year")==marriageYear)
						{
							deductions.getJSONObject(i).put("saving", marriageCost+deductions.getJSONObject(i).getDouble("saving"));
						}
					}

					if(fillingExemtion.getJSONObject(i).getInt("year")>=marriageYear)
					{

						fillingExemtion.getJSONObject(i).put("oldFilingStatus",fillingExemtion.getJSONObject(i).get("fillingStatus"));
						fillingExemtion.getJSONObject(i).put("fillingStatus","Married Filing Jointly");

						fillingExemtion.getJSONObject(i).put("noOfExcemtion",fillingExemtion.getJSONObject(i).getInt("noOfExcemtion") + 1);
						userExpense.getJSONObject(i).put("housingExpense",marriagegoalmodel.getHousingExpenses());
						userExpense.getJSONObject(i).put("nonHousingExpense",marriagegoalmodel.getNonHousingExpense());
						if(userExpense.getJSONObject(i).getInt("mortgageExpense")>0)
						{
							userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("mortgageExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));
						}
						else{
if(houseEndYear==0)

							{

								userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("housingExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));

							}

							else
if(userExpense.getJSONObject(i).getInt("year")<=houseEndYear&&houseEndYear!=0)

							{
			userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("housingExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));
						}
	else

							{

								userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));

							}

						}
						JSONObject newLimits=CalculationEngine.limiteAfterRetirement( user401k,spouse401k,newUser401k,newSpouse401k,newUserIra,newSpouseIra,ageAtRegistration, userIncome.getJSONObject(i).getDouble("value"),
								marriagegoalmodel.getSpouseIncome(),fillingExemtion.getJSONObject(i).getString("fillingStatus"),
								spouseAgeForLimits,limits.getJSONObject(i),(userIncome.getJSONObject(i).getDouble("value")+marriagegoalmodel.getSpouseIncome()),i,null );
						limits.put(i,newLimits);
						spouseAgeForLimits++;
					}


				 ageAtRegistration++;
				}
					String emergencyType=null;
					String monthsOfIncome=null;
					String monthsOfExpense=null;
				 if(emergencyFundFlag==true)
				 {
				    Emergencyfundmodel emergencyObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",finDetails.get_id(),"Emergency Fund").as(Emergencyfundmodel.class);
					emergencyType=emergencyObj.getTimePeriod();
					monthsOfIncome=emergencyObj.getMonthI();
					monthsOfExpense=emergencyObj.getMonthE();
				 }
				 if(Details.getHouseStatus().equals("Own"))
					{
						//System.out.println("hbasdhb");
					int remainingYears=(int)(Details.getRemainingYearsMortgage());
					for(int i=remainingYears;i<userExpense.length();i++)
					{
						userExpense.getJSONObject(i).put("totalExpense", userExpense.getJSONObject(i).getDouble("totalExpense")-userExpense.getJSONObject(i).getDouble("nonHousingExpense"));
						userExpense.getJSONObject(i).put("nonHousingExpense", userExpense.getJSONObject(i).getDouble("nonHousingExpense")-userExpense.getJSONObject(i).getDouble("nonHousingExpense")*10/100);
						if(userExpense.getJSONObject(i).getDouble("nonHousingExpense")<0)
						{
							userExpense.getJSONObject(i).put("nonHousingExpense", 0);
						}
						userExpense.getJSONObject(i).put("totalExpense", userExpense.getJSONObject(i).getDouble("totalExpense")+userExpense.getJSONObject(i).getDouble("nonHousingExpense"));


					}
					}
				 //System.out.println("userExpensebefore-----"+userExpense);
					userExpense=CalculationEngine.retirementExpenseArray(userExpense, SpouseStartYear, userStartYear,"Yes",retirement_expense);
					//System.out.println("userExpenseAfter-----"+userExpense);

				 JSONObject result=CalculationEngine.sweepingOfMoney(userIncome, fin_id,combinedIncome, SpouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, user_id, fillingExemtion, userAge, spouseAge, emergencyFundAmt, emergencyFundFlag,deductions,kidBirthYear, retirementData, retirementObj, expenseObj,emergencyType,monthsOfIncome,monthsOfExpense,finDetails.isRetirementFlag());

				String status=result.getString("status");
				if(status.equals("success"))
				{
					if(retirementObj!=null)
					{
						MongoDBConnection.goalcoll.update("{'_id': '" + retirementObj.get_id() + "'}").upsert().multi()
						.with("{$set: {'spouseRetirementAge':" + 70 + "}}");

					}
				//System.out.println("No Negative value success");
				assets=result.getJSONArray("assets");
				tax=result.getJSONArray("tax");
				//System.out.println("tax in marriage after create..."+tax);

				Counters count = MongoDBConnection.counterColl.findOne().as(Counters.class);
				String income_profile = "incomeProfile" + count.getIncome_id();
				int imp_counter=count.getImp_count();
				//System.out.println("counters...."+imp_counter);
				String profile_name = finDetails.getPlan_name() + "_marriage_income_profile"+count.getIncome_id();


				JSONArray spousePlot = new JSONArray();

				JSONObject tempPlot = new JSONObject();

				for(int i=0;i<SpouseIncomeArray.length();i++)
				{
					if(SpouseIncomeArray.getJSONObject(i).getDouble("value") > 0)
					{
						tempPlot.put("year",SpouseIncomeArray.getJSONObject(i).getInt("year"));
						tempPlot.put("spouseIncome",SpouseIncomeArray.getJSONObject(i).getDouble("value"));
						break;
					}
				}
				spousePlot.put(tempPlot);
				for(int i=0;i<SpouseIncomeArray.length();i++)
				{
					tempPlot = new JSONObject();
					if(SpouseIncomeArray.getJSONObject(i).getDouble("retirement_income") > 0)
					{
						tempPlot.put("year",SpouseIncomeArray.getJSONObject(i).getInt("year"));
						tempPlot.put("spouseIncome",SpouseIncomeArray.getJSONObject(i).getDouble("value"));
						spousePlot.put(tempPlot);
						break;
					}
				}
				tempPlot = new JSONObject();
				tempPlot.put("year",SpouseIncomeArray.getJSONObject(SpouseIncomeArray.length()-1).getInt("year"));
				tempPlot.put("spouseIncome",SpouseIncomeArray.getJSONObject(SpouseIncomeArray.length()-1).getString("value"));
				spousePlot.put(tempPlot);


				MongoDBConnection.counterColl.update("{'income_id':" + count.getIncome_id() + "}").with("{$inc: {income_id: 1}}");
				goal_id = "goal" + count.getGoal_id();
				BasicDBObject doc = new BasicDBObject("_id", goal_id).append("user_id", marriagegoalmodel.getUser_id()).append("fin_id", fin_id)
						.append("plan_name", marriagegoalmodel.getPlan_name()).append("goalType", marriagegoalmodel.getGoalType())
							.append("marriageYear", marriagegoalmodel.getMarriageYear()).append("marriageCost", marriagegoalmodel.getMarriageCost())
							.append("annualexpense", marriagegoalmodel.getMarriageCost()).append("annualExcess", annualExcess).append("created_ts", dateFormat.format(date)).append("spouseIncome", spouceIncome)
							.append("completedStatus", 2).append("modified_ts", dateFormat.format(date));
				doc.append("goalFeasibility", true);
				MongoDBConnection.goalcoll.insert(doc);
				MongoDBConnection.counterColl.update("{'goal_id':" + count.getGoal_id() + "}").with("{$inc: {goal_id: 1}}");
				MongoDBConnection.goalcoll.update("{'_id': '" + goal_id + "'}").upsert().multi().with("{$set: {'housingExpenses':" +marriagegoalmodel.getHousingExpenses() + ",'nonHousingExpense':" +marriagegoalmodel.getNonHousingExpense() + "}}");
				MongoDBConnection.goalcoll.update("{'_id': '" + goal_id + "'}").upsert().multi()
				.with("{$set: {'birthYear':" + birthYear + ",'spouseAge':" + marriagegoalmodel.getSpouseAge() + "}}");
				MongoDBConnection.finplancol.update("{'_id': '" + fin_id + "'}").upsert().multi()
				.with("{$set: {'profile_name':'" + profile_name + "','fillingExemtion':" + fillingExemtion + "}}");
				MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi().with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits + ",'deductions':" + deductions + ",'userExpense':" + userExpense + ",'fillingExemtion':" + fillingExemtion + ",'marital_status':'Yes','spouseAge':"+spouseAge+",'spouseBirthYear':"+(currentYear-spouseAge)+",'spouse401k':"+18000+"}}");

				MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi().with("{$addToSet: {'goals':'" + goal_id + "'}}");


				JSONObject marriage_expensedata = new JSONObject();
				marriage_expensedata.put("annualexpense", marriageCost);
				marriage_expensedata.put("endYear", marriageYear);
				marriage_expensedata.put("startYear", marriageYear);

				JSONArray userExpenseTemp = new JSONArray();
                userExpenseTemp = userExpense;
                for(int i=0;i<userExpenseTemp.length();i++) {
                    userExpenseTemp.getJSONObject(i).put("mortgageExpense", 0);
                    userExpenseTemp.getJSONObject(i).put("kidExpense", 0);
                    userExpenseTemp.getJSONObject(i).put("totalExpense",userExpenseTemp.getJSONObject(i).getInt("nonHousingExpense")+userExpenseTemp.getJSONObject(i).getInt("housingExpense"));



                }
             //   System.out.println("userExpenseTemp..."+userExpenseTemp);

                MongoDBConnection.incomeProfileCol
                .update("{'_id':'" + income_profile + "'}")
                .upsert()
                .multi()
                .with("{$set: {'spouse_income':" + SpouseIncomeArray + ",'spousePlot':" + spousePlot + ",'userPlot':" + userPlot + "," + "'combined_income':" + combinedIncome + "," + "'userExpense':" + userExpenseTemp
                        + "," + "'assests':" + assets + "," + "'user_income':" + userIncome + "," + "'tax':"
                        +tax+ "," + "'oldIncomeProfile':'" + finDetails.getProfile_name() + "'," + "'modifiedTs':'"
                        + dateFormat.format(date) + "'," + "'user_id':'" + user_id + "'," + "'createdTs':'"
                        + dateFormat.format(date) + "'," + "'profile_name':'" + profile_name + "'," + "'limits':" + limits + ",'equity':"+equity+",'housingExpense':"+housingExpenseRate+",'nonHousingExpense':"+nonHousingExpenseRate+",'editExpenseFlag':"+editExpenseFlag+"}}");

				MongoDBConnection.finplancol.update("{'_id': '" + fin_id + "'}").upsert().multi()
				.with("{$set: {'expense.marriage_expense':" + marriage_expensedata + ",'marital_status':'Yes',"
										+ "'spouse401k':" +1+ "}}");
				responseToRestController.put("status", "success");
				responseToRestController.put("goal_id", goal_id);
				//System.out.println(assets);
				JSONObject systemLog = new JSONObject();
				systemLog.put("message", "Goal Created Successfully!!");
				systemLog.put("userName", Details.getName());
				systemLog.put("user_id", user_id);
				systemLog.put("createdTs", dateFormat.format(date));
				BasicDBObject systemLogBasicObject = new BasicDBObject("user_id", user_id).append("userName", systemLog.getString("userName"))
						.append("message", systemLog.getString("message")).append("createdTs", dateFormat.format(date));
				MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);


				}
				else
				{
				responseToRestController.put("status", "fail");
				return responseToRestController;
				}
			} else if(actionHomeType.equals("deleteGoal"))
			{
				//-----------------------------delete of the marriage goal-----------------------------------------------------------------
				//System.out.println("Deleting the goal.....");
				goal_id = marriagegoalmodel.getGoal_id();
				Marriagegoalmodel marriageGoalDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", goal_id).as(Marriagegoalmodel.class);
				int oldMarriageYear = marriageGoalDetails.getMarriageYear();
				long oldMarriageCost = marriageGoalDetails.getMarriageCost();
				plan_name=marriageGoalDetails.getPlan_name();
				//System.out.println("user_id"+user_id+"plan name .."+plan_name);
				FinPlan finDetails = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}", user_id, plan_name).as(FinPlan.class);
				String fin_id=finDetails.get_id();
				User Details = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
				int userAge=Details.getAge();
				//int spouseAge=marriageGoalDetails.getSpouseAge();
				JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
				JSONArray assets=finPlanJson.getJSONArray("assests");
				JSONArray tax=finPlanJson.getJSONArray("tax");
				JSONArray limits=finPlanJson.getJSONArray("limits");
				JSONArray deductions=finPlanJson.getJSONArray("deductions");
				JSONArray fillingExemtion=finPlanJson.getJSONArray("fillingExemtion");
				JSONArray userExpense=finPlanJson.getJSONArray("userExpense");
				JSONArray kidBirthYear=finPlanJson.getJSONArray("kidBirthYear");
				JSONArray expenseObj=new JSONArray();
				JSONObject expense=new JSONObject();
				expense=finPlanJson.getJSONObject("expense");
				if (!expense.isNull("housing_expense")) {
				expenseObj=finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
				}
				String finPlanProfileName=finDetails.getProfile_name();
				IncomeProfile currentIncomeProfileDetails = MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}",user_id ,finPlanProfileName).as(IncomeProfile.class);
				IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}",user_id ,currentIncomeProfileDetails.getOldIncomeProfile()).as(IncomeProfile.class);
				JSONObject incomeProfileJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
				JSONArray userIncome=incomeProfileJson.getJSONArray("user_income");
				RetirementGoal retirementObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",finDetails.get_id(),"Retirement").as(RetirementGoal.class);
				int userStartYear=0;
				int SpouseStartYear=0;
				int userAgeInLImits=Details.getAge();
				long retirement_expense=0;
				double newUser401k=0;
				double newSpouse401k=0;
				double newUserIra=0;
				double newSpouseIra=0;
				if(retirementObj!=null)
				{
					userStartYear=Details.getBirthYear()+retirementObj.getRetirementAge();
					retirement_expense=retirementObj.getRetirement_expense();
					newUser401k=retirementObj.getUserContributeAmount();
					newSpouse401k=retirementObj.getSpouseContributeAmount();
					newUserIra=retirementObj.getUserContributionInIRA();
					newSpouseIra=retirementObj.getSpouseContributionInIRA();
				}
				else{
					userStartYear=Details.getBirthYear()+70;

				}
				JSONObject expenseHouse=new JSONObject();
				JSONArray houseExpense=null;
				expenseHouse=finPlanJson.getJSONObject("expense");
				if (!expenseHouse.isNull("housing_expense")) {
				 houseExpense= finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
				}

				int houseEndYear=0;

				if (houseExpense!=null)

				{

					for(int i=0;i<houseExpense.length();i++)
					{
					houseEndYear=houseExpense.getJSONObject(i).getInt("endYear");
					}


					//System.out.println("Bala...houseEnd year"+houseEndYear);

				}
				for(int i=0;i<limits.length();i++)
				{
					if(oldMarriageYear>currentYear+4)
					{
						if(limits.getJSONObject(i).getInt("year")<=oldMarriageYear)
						{
						limits.getJSONObject(i).put("taxable", limits.getJSONObject(i).getInt("taxable")-oldMarriageCost);
						}
						if(deductions.getJSONObject(i).getInt("year")==oldMarriageYear)
						{
							deductions.getJSONObject(i).put("taxable", deductions.getJSONObject(i).getDouble("taxable")-oldMarriageCost);
						}
					}
					else
					{
						if(limits.getJSONObject(i).getInt("year")<oldMarriageYear)
						{
						limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving")-oldMarriageCost);
						}
						if(deductions.getJSONObject(i).getInt("year")==oldMarriageYear)
						{
							deductions.getJSONObject(i).put("saving", deductions.getJSONObject(i).getDouble("saving")-oldMarriageCost);
						}
					}

					if(fillingExemtion.getJSONObject(i).getInt("year")>=oldMarriageYear)
					{
						String filingStatus = fillingExemtion.getJSONObject(i).getString("oldFilingStatus");
if(filingStatus.equals("Married Filing Jointly"))

						{

							filingStatus = "Single";

						}
if (filingStatus=="Single"&&userExpense.getJSONObject(i).getInt("kidExpense")>0) {
							filingStatus = "Head of Household";
						}
						fillingExemtion.getJSONObject(i).put("fillingStatus",filingStatus);
						fillingExemtion.getJSONObject(i).put("noOfExcemtion",fillingExemtion.getJSONObject(i).getInt("noOfExcemtion") - 1);
						userExpense.getJSONObject(i).put("housingExpense",userExpense.getJSONObject(i).getInt("registerHousingExpense"));
						userExpense.getJSONObject(i).put("nonHousingExpense",userExpense.getJSONObject(i).getInt("registerNonHousingExpense"));
						if(userExpense.getJSONObject(i).getInt("mortgageExpense")>0)
						{
							userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("mortgageExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));
						}
						else{
if(houseEndYear==0)

							{

								userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("housingExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));

							}

							else
if(userExpense.getJSONObject(i).getInt("year")<=houseEndYear&&houseEndYear!=0)

							{
				userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("housingExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));
						}
else

							{

							userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));
					}
					}}
					JSONObject newLimits=CalculationEngine.limiteAfterRetirement( finDetails.getUser401k(),0,newUser401k,newSpouse401k,newUserIra,newSpouseIra,userAgeInLImits, userIncome.getJSONObject(i).getDouble("value"),
							0,fillingExemtion.getJSONObject(i).getString("fillingStatus"),
							0,limits.getJSONObject(i), userIncome.getJSONObject(i).getDouble("value"),i,null );
					limits.put(i,newLimits);
					userAgeInLImits++;
				}

				JSONArray SpouseIncomeArray=new JSONArray();
				IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
				JSONArray combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, SpouseIncomeArray);
				//System.out.println("spouse_income..."+SpouseIncomeArray+"user_Income..."+userIncome+"combined_Incme.."+combinedIncome);
				int emergencyFundAmt=finDetails.getEmergencyFundAmt();
				boolean emergencyFundFlag=finDetails.isEmergencyFundFlag();
				//CalculationEngineService obj=new CalculationEngineService();
				String maritalStatus="No";

				JSONObject retirementData=new JSONObject();
				//System.out.println("Bala... spouse start year ..."+SpouseStartYear+"..user start Year..."+userStartYear);
				retirementData.put("spouseStartYear", SpouseStartYear);
				retirementData.put("userStartYear", userStartYear);
				if(Details.getHouseStatus().equals("Own"))
				{
					//System.out.println("hbasdhb");
				int remainingYears=(int)(Details.getRemainingYearsMortgage());
				for(int i=remainingYears;i<userExpense.length();i++)
				{
					userExpense.getJSONObject(i).put("totalExpense", userExpense.getJSONObject(i).getDouble("totalExpense")-userExpense.getJSONObject(i).getDouble("nonHousingExpense"));
					userExpense.getJSONObject(i).put("nonHousingExpense", userExpense.getJSONObject(i).getDouble("nonHousingExpense")-userExpense.getJSONObject(i).getDouble("nonHousingExpense")*10/100);
					if(userExpense.getJSONObject(i).getDouble("nonHousingExpense")<0)
					{
						userExpense.getJSONObject(i).put("nonHousingExpense", 0);
					}
					userExpense.getJSONObject(i).put("totalExpense", userExpense.getJSONObject(i).getDouble("totalExpense")+userExpense.getJSONObject(i).getDouble("nonHousingExpense"));


				}
				}
				userExpense=CalculationEngine.retirementExpenseArray(userExpense, SpouseStartYear, userStartYear,"No",retirement_expense);
				 String emergencyType=null;
					String monthsOfIncome=null;
					String monthsOfExpense=null;
				 if(emergencyFundFlag==true)
				 {
				    Emergencyfundmodel emergencyObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",finDetails.get_id(),"Emergency Fund").as(Emergencyfundmodel.class);
					emergencyType=emergencyObj.getTimePeriod();
					monthsOfIncome=emergencyObj.getMonthI();
					monthsOfExpense=emergencyObj.getMonthE();
				 }
				JSONObject result=CalculationEngine.sweepingOfMoney(userIncome, fin_id,combinedIncome, SpouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, user_id, fillingExemtion, userAge, 0, emergencyFundAmt, emergencyFundFlag,deductions,kidBirthYear, retirementData, retirementObj, expenseObj,emergencyType,monthsOfIncome,monthsOfExpense,finDetails.isRetirementFlag());

				String status=result.getString("status");
				if(status.equals("success"))
				{
					if(retirementObj!=null)
					{
						MongoDBConnection.goalcoll.update("{'_id': '" + retirementObj.get_id() + "'}").upsert().multi()
						.with("{$set: {'spouseRetirementAge':" + 0 + "}}");

					}
				//System.out.println("No Negative value success");
				assets=result.getJSONArray("assets");
				tax=result.getJSONArray("tax");
				int newSpouseAge=0;

				//MongoDBConnection.incomeProfileCol.remove("{user_id:#,profile_name:#}", user_id, finPlanProfileName);
				JSONArray goalJsonArray = finPlanJson.getJSONArray("goals");
				JSONArray goalsArray = new JSONArray();
				for (int j = 0; j < goalJsonArray.length(); j++) {
					String goalId = (String) goalJsonArray.get(j);
					if (!goalId.equals(goal_id)) {
						goalsArray.put(goalId);
					} else {
						continue;
					}
				}
				//System.out.println("before fin plan update");
				MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi().with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits + ",'goals':"+goalsArray+",'deductions':" + deductions + ",'userExpense':" + userExpense + ",'fillingExemtion':" + fillingExemtion + ",'spouseAge':"+newSpouseAge+",'spouseBirthYear':"+0+"}}");
				//System.out.println(" fin plan update");

				MongoDBConnection.goalcoll.remove("{_id:#}", goal_id);

				/*incomeProfileDetails = MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}",user_id ,currentIncomeProfileDetails.getOldIncomeProfile()).as(IncomeProfile.class);
				incomeProfileJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
				JSONArray userPlot=incomeProfileJson.getJSONArray("userPlot");	*/

				MongoDBConnection.finplancol.update("{'_id': '" + fin_id + "'}").upsert().multi()
				.with("{$set: {'expense.marriage_expense':" + null + ",'profile_name':'" + currentIncomeProfileDetails.getOldIncomeProfile() + "','marital_status':'"+maritalStatus+"'}}");

				/*MongoDBConnection.incomeProfileCol.update("{user_id: #,profile_name:#}", user_id, currentIncomeProfileDetails.getOldIncomeProfile()).upsert().multi()
				.with("{$set: {'userPlot':" + userPlot + "}}");*/
				responseToRestController.put("status", "success");
				}
				else{
					responseToRestController.put("status", "fail");

				}

			}




			else {
//-----------------------------update of the marriage goal-----------------------------------------------------------------
				goal_id = marriagegoalmodel.getGoal_id();
				int newMarriageYear = marriagegoalmodel.getMarriageYear();
				long newMarriageCost = marriagegoalmodel.getMarriageCost();
				long newSpouseIncome = marriagegoalmodel.getSpouseIncome();
				int newSpouseAge = marriagegoalmodel.getSpouseAge();
				/*long newMonthlyExpense = marriagegoalmodel.getNewMonthlyExpense();*/
				long newHousingExpenses=marriagegoalmodel.getHousingExpenses();
				long newNonHousingExpenses=marriagegoalmodel.getNonHousingExpense();
				Marriagegoalmodel marriageGoalDetails = MongoDBConnection.goalcoll.findOne("{_id:#}", goal_id).as(Marriagegoalmodel.class);
				int oldMarriageYear = marriageGoalDetails.getMarriageYear();
				long oldMarriageCost = marriageGoalDetails.getMarriageCost();
				plan_name=marriageGoalDetails.getPlan_name();
				//System.out.println("user_id"+user_id+"plan name .."+plan_name);
				FinPlan finDetails = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}", user_id, plan_name).as(FinPlan.class);
				String fin_id=finDetails.get_id();
				User Details = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
				int userAge=Details.getAge();
				int userStartYear=currentYear-userAge+70;
				//int spouseAge=marriageGoalDetails.getSpouseAge();
				JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
				JSONArray assets=finPlanJson.getJSONArray("assests");
				JSONArray tax=finPlanJson.getJSONArray("tax");
				JSONArray limits=finPlanJson.getJSONArray("limits");
				JSONArray deductions=finPlanJson.getJSONArray("deductions");
				JSONArray fillingExemtion=finPlanJson.getJSONArray("fillingExemtion");
				JSONArray userExpense=finPlanJson.getJSONArray("userExpense");
				JSONArray kidBirthYear=finPlanJson.getJSONArray("kidBirthYear");
				JSONArray expenseObj=new JSONArray();
				JSONObject expense=new JSONObject();
				expense=finPlanJson.getJSONObject("expense");
				if (!expense.isNull("housing_expense")) {
				expenseObj=finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
				}
				String finPlanProfileName=finDetails.getProfile_name();
				JSONObject expenseHouse=new JSONObject();
				JSONArray houseExpense=null;
				expenseHouse=finPlanJson.getJSONObject("expense");
				if (!expenseHouse.isNull("housing_expense")) {
				 houseExpense= finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
				}

				int houseEndYear=0;

				if (houseExpense!=null)

				{

					for(int i=0;i<houseExpense.length();i++)
					{
					houseEndYear=houseExpense.getJSONObject(i).getInt("endYear");
					}


					//System.out.println("Bala...houseEnd year"+houseEndYear);

				}
for(int i=0;i<limits.length();i++)
				{
					if(oldMarriageYear>currentYear+4)
					{
						if(limits.getJSONObject(i).getInt("year")<=oldMarriageYear)
						{
						limits.getJSONObject(i).put("taxable", limits.getJSONObject(i).getInt("taxable")-oldMarriageCost);
						}
						if(deductions.getJSONObject(i).getInt("year")==oldMarriageYear)
						{
							deductions.getJSONObject(i).put("taxable", deductions.getJSONObject(i).getDouble("taxable")-oldMarriageCost);
						}
					}
					else
					{
						if(limits.getJSONObject(i).getInt("year")<oldMarriageYear)
						{
						limits.getJSONObject(i).put("saving", limits.getJSONObject(i).getInt("saving")-oldMarriageCost);
						}
						if(deductions.getJSONObject(i).getInt("year")==oldMarriageYear)
						{
							deductions.getJSONObject(i).put("saving", deductions.getJSONObject(i).getDouble("saving")-oldMarriageCost);
						}
					}

					if(fillingExemtion.getJSONObject(i).getInt("year")>=oldMarriageYear)
					{
						String filingStatus = fillingExemtion.getJSONObject(i).getString("oldFilingStatus");
if(filingStatus.equals("Married Filing Jointly"))

						{

							filingStatus = "Single";

						}
if (filingStatus=="Single"&&userExpense.getJSONObject(i).getInt("kidExpense")>0) {
							filingStatus = "Head of Household";
						}
						fillingExemtion.getJSONObject(i).put("fillingStatus",filingStatus);
						fillingExemtion.getJSONObject(i).put("noOfExcemtion",fillingExemtion.getJSONObject(i).getInt("noOfExcemtion") - 1);
						userExpense.getJSONObject(i).put("housingExpense",userExpense.getJSONObject(i).getInt("registerHousingExpense"));
						userExpense.getJSONObject(i).put("nonHousingExpense",userExpense.getJSONObject(i).getInt("registerNonHousingExpense"));
						if(userExpense.getJSONObject(i).getInt("mortgageExpense")>0)
						{
							userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("mortgageExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));
						}
						else{
if(houseEndYear==0)

							{

								userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("housingExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));

							}

							else
if(userExpense.getJSONObject(i).getInt("year")<=houseEndYear&&houseEndYear!=0)

							{
	userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("housingExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));
						}
else

							{

								userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));
					}
				}
}}
				if(Details.getHouseStatus().equals("Own"))
				{
					//System.out.println("hbasdhb");
				int remainingYears=(int)(Details.getRemainingYearsMortgage());
				for(int i=remainingYears;i<userExpense.length();i++)
				{
					userExpense.getJSONObject(i).put("totalExpense", userExpense.getJSONObject(i).getDouble("totalExpense")-userExpense.getJSONObject(i).getDouble("nonHousingExpense"));
					userExpense.getJSONObject(i).put("nonHousingExpense", userExpense.getJSONObject(i).getDouble("nonHousingExpense")-userExpense.getJSONObject(i).getDouble("nonHousingExpense")*10/100);
					if(userExpense.getJSONObject(i).getDouble("nonHousingExpense")<0)
					{
						userExpense.getJSONObject(i).put("nonHousingExpense", 0);
					}
					userExpense.getJSONObject(i).put("totalExpense", userExpense.getJSONObject(i).getDouble("totalExpense")+userExpense.getJSONObject(i).getDouble("nonHousingExpense"));


				}
				}
				double user401k=finDetails.getUser401k();
				double spouse401k=finDetails.getSpouse401k();
				int ageAtRegistration=Details.getAge();
				IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}",user_id ,finPlanProfileName).as(IncomeProfile.class);
				JSONObject incomeProfileJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
				JSONArray userIncome=incomeProfileJson.getJSONArray("user_income");
				JSONArray spousePlot = incomeProfileJson.getJSONArray("spousePlot");
				System.out.println("spousePlot :: "+spousePlot);
				JSONArray SpouseIncomeArray=new JSONArray();
				int birthYear = newMarriageYear - newSpouseAge;
				int SpouseStartYear=currentYear-newSpouseAge+70;
				int incomeStartYear=birthYear+newSpouseAge;
				int incomeEndYear = birthYear + 99;
				int startYear = incomeStartYear;
				int endYear = incomeEndYear;
				for (int i = 0; i < userIncome.length(); i++) {
					if (userIncome.getJSONObject(i).getInt("year") >= startYear&&userIncome.getJSONObject(i).getInt("year") <= endYear) {
						JSONObject incomeJson = new JSONObject();
						incomeJson.put("year", (userIncome.getJSONObject(i).getInt("year")));
						incomeJson.put("value", (newSpouseIncome));
						incomeJson.put("retirement_income", 0);
						incomeJson.put("incomeBeforeRetirement", 0);

						SpouseIncomeArray.put(incomeJson);
						startYear++;
					} else {
						JSONObject incomeJson = new JSONObject();
						incomeJson.put("year", (userIncome.getJSONObject(i).getInt("year")));
						incomeJson.put("value", 0);
						incomeJson.put("retirement_income", 0);
						incomeJson.put("incomeBeforeRetirement", 0);

						SpouseIncomeArray.put(incomeJson);
					}
				}
				/*IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
				JSONArray combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, SpouseIncomeArray);*/

				int emergencyFundAmt=finDetails.getEmergencyFundAmt();
				boolean emergencyFundFlag=finDetails.isEmergencyFundFlag();
				//CalculationEngineService obj=new CalculationEngineService();
				String maritalStatus="Yes";
				int userRetirementAge=70;
				int spouseRetirementAge=70;
				long retirement_expense=0;
				double newUser401k=0;
				double newSpouse401k=0;
				double newUserIra=0;
				double newSpouseIra=0;
				int UserSelectedSpouseYear=0;
				RetirementGoal retirementObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",finDetails.get_id(),"Retirement").as(RetirementGoal.class);
				if(retirementObj!=null)
				{
					userRetirementAge=retirementObj.getRetirementAge();
					spouseRetirementAge=retirementObj.getSpouseRetirementAge();
					UserSelectedSpouseYear= retirementObj.getUserSelectedSpouseYear();
					userStartYear=Details.getBirthYear()+retirementObj.getRetirementAge();
					retirement_expense=retirementObj.getRetirement_expense();
					newUser401k=retirementObj.getUserContributeAmount();
					newSpouse401k=retirementObj.getSpouseContributeAmount();
					newUserIra=retirementObj.getUserContributionInIRA();
					newSpouseIra=retirementObj.getSpouseContributionInIRA();
					if(finDetails.getMarital_status().equals("Yes")&& Details.getMarital_status().equals("No")){
						SpouseStartYear=finDetails.getSpouseBirthYear()+retirementObj.getSpouseRetirementAge();
					}
					else if(Details.getMarital_status().equals("Yes"))
					{
						SpouseStartYear=Details.getSpouseBirthYear()+retirementObj.getSpouseRetirementAge();
					}

				}
				else{
					userStartYear=Details.getBirthYear()+70;
					if(finDetails.getMarital_status().equals("Yes")&& Details.getMarital_status().equals("No")){
						SpouseStartYear=finDetails.getSpouseBirthYear()+70;
						UserSelectedSpouseYear=SpouseStartYear;
					}
					else if(Details.getMarital_status().equals("Yes"))
					{
						SpouseStartYear=Details.getSpouseBirthYear()+70;
						UserSelectedSpouseYear=SpouseStartYear;
					}
				}




				RetirementGoalServiceImpl goalservice=new RetirementGoalServiceImpl();
				double userAIME=goalservice.findUserAIME( userRetirementAge,userIncome,userStartYear);
				int userFRA=goalservice.findUserFRA(userRetirementAge, Details.getBirthYear());
				 JSONObject userJsonSSB = goalservice.calculateUserSSB(marriagegoalmodel.getSpouseIncome(), "Yes", userFRA, 0, 856,5157, userAIME, 0);


				    JSONObject spouseJson=new JSONObject();
					spouseJson.put("firetBendPoint", 856);
					spouseJson.put("spouseRetirementAge",spouseRetirementAge);
					spouseJson.put("userRetirementAge", userRetirementAge);
					spouseJson.put("useFfra", userFRA);
					spouseJson.put("spouseFfra", goalservice.findUserFRA(spouseRetirementAge, Details.getSpouseBirthYear()));
					spouseJson.put("userAime",userAIME);
					spouseJson.put("spouseAime", goalservice.findUserAIME( spouseRetirementAge,SpouseIncomeArray,SpouseStartYear));
					spouseJson.put("secondBendPoint", 5157);
				     JSONArray combinedIncome=new JSONArray();
				     JSONObject allIncomes = goalservice.insertRetirementIncomeNew(((Double)(userJsonSSB.get("User"+userRetirementAge ))),spouseJson,user_id,userIncome,SpouseIncomeArray,userStartYear, SpouseStartYear,UserSelectedSpouseYear);
				     //System.out.println("allIncomes====="+allIncomes);
				     userIncome=allIncomes.getJSONArray("user_income");
				     SpouseIncomeArray=allIncomes.getJSONArray("spouse_income");
				     combinedIncome=allIncomes.getJSONArray("combined_income");
				//JSONArray combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, SpouseIncomeArray);
				//System.out.println("spouse_income..."+SpouseIncomeArray+"user_Income..."+userIncome+"combined_Incme.."+combinedIncome);
				JSONObject retirementData=new JSONObject();
				retirementData.put("spouseStartYear", SpouseStartYear);
				retirementData.put("userStartYear", userStartYear);


				for(int i=0;i<limits.length();i++)
				{
					if(newMarriageYear>currentYear+4)
					{
						if(limits.getJSONObject(i).getInt("year")<=newMarriageYear)
						{
						limits.getJSONObject(i).put("taxable", newMarriageCost+limits.getJSONObject(i).getInt("taxable"));
						}
						if(deductions.getJSONObject(i).getInt("year")==newMarriageYear)
						{
							deductions.getJSONObject(i).put("taxable", newMarriageCost+deductions.getJSONObject(i).getDouble("taxable"));
						}
					}
					else
					{
						if(limits.getJSONObject(i).getInt("year")<newMarriageYear)
						{
						limits.getJSONObject(i).put("saving", newMarriageCost+limits.getJSONObject(i).getInt("saving"));
						}
						if(deductions.getJSONObject(i).getInt("year")==newMarriageYear)
						{
							deductions.getJSONObject(i).put("saving", newMarriageCost+deductions.getJSONObject(i).getDouble("saving"));
						}
					}

					if(fillingExemtion.getJSONObject(i).getInt("year")>=newMarriageYear)
					{

						fillingExemtion.getJSONObject(i).put("oldFilingStatus",fillingExemtion.getJSONObject(i).get("fillingStatus"));
						fillingExemtion.getJSONObject(i).put("fillingStatus","Married Filing Jointly");
						fillingExemtion.getJSONObject(i).put("noOfExcemtion",fillingExemtion.getJSONObject(i).getInt("noOfExcemtion") + 1);
						userExpense.getJSONObject(i).put("housingExpense",newHousingExpenses);
						userExpense.getJSONObject(i).put("nonHousingExpense",newNonHousingExpenses);
						if(userExpense.getJSONObject(i).getInt("mortgageExpense")>0)
						{
							userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("mortgageExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));
						}
						else{
if(houseEndYear==0)

							{

								userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("housingExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));

							}

							else
if(userExpense.getJSONObject(i).getInt("year")<=houseEndYear&&houseEndYear!=0)

							{
	userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("housingExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));
						}
else

							{

								userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));

							}

						}
						//userExpense.getJSONObject(i).put("totalExpense",userExpense.getJSONObject(i).getInt("housingExpense")+userExpense.getJSONObject(i).getInt("nonHousingExpense")+userExpense.getJSONObject(i).getInt("mortgageExpense")+userExpense.getJSONObject(i).getInt("kidExpense"));
						JSONObject newLimits=CalculationEngine.limiteAfterRetirement( user401k,spouse401k,newUser401k,newSpouse401k,newUserIra,newSpouseIra,ageAtRegistration, userIncome.getJSONObject(i).getDouble("value"),
								marriagegoalmodel.getSpouseIncome(),fillingExemtion.getJSONObject(i).getString("fillingStatus"),
								newSpouseAge,limits.getJSONObject(i),(userIncome.getJSONObject(i).getDouble("value")+marriagegoalmodel.getSpouseIncome()),i,null );
						limits.put(i,newLimits);
					}

				 ageAtRegistration++;
				}
				if(Details.getHouseStatus().equals("Own"))
				{
					//System.out.println("hbasdhb");
				int remainingYears=(int)(Details.getRemainingYearsMortgage());
				for(int i=remainingYears;i<userExpense.length();i++)
				{
					userExpense.getJSONObject(i).put("totalExpense", userExpense.getJSONObject(i).getDouble("totalExpense")-userExpense.getJSONObject(i).getDouble("nonHousingExpense"));
					userExpense.getJSONObject(i).put("nonHousingExpense", userExpense.getJSONObject(i).getDouble("nonHousingExpense")-userExpense.getJSONObject(i).getDouble("nonHousingExpense")*10/100);
					if(userExpense.getJSONObject(i).getDouble("nonHousingExpense")<0)
					{
						userExpense.getJSONObject(i).put("nonHousingExpense", 0);
					}
					userExpense.getJSONObject(i).put("totalExpense", userExpense.getJSONObject(i).getDouble("totalExpense")+userExpense.getJSONObject(i).getDouble("nonHousingExpense"));


				}
				}
				 String emergencyType=null;
					String monthsOfIncome=null;
					String monthsOfExpense=null;
					//System.out.println("Mahi emergencyFundFlag:  "+emergencyFundFlag);
				 if(emergencyFundFlag==true)
				 {
				    Emergencyfundmodel emergencyObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",finDetails.get_id(),"Emergency Fund").as(Emergencyfundmodel.class);
					emergencyType=emergencyObj.getTimePeriod();
					monthsOfIncome=emergencyObj.getMonthI();
					monthsOfExpense=emergencyObj.getMonthE();
				 }
					 //System.out.println("userExpensebefore-----"+userExpense);

					userExpense=CalculationEngine.retirementExpenseArray(userExpense, SpouseStartYear, userStartYear,"Yes",retirement_expense);

					//System.out.println("userExpenseAfter-----"+userExpense);
				JSONObject result=CalculationEngine.sweepingOfMoney(userIncome, fin_id,combinedIncome, SpouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, user_id, fillingExemtion, userAge, newSpouseAge, emergencyFundAmt, emergencyFundFlag,deductions,kidBirthYear, retirementData, retirementObj, expenseObj,emergencyType,monthsOfIncome,monthsOfExpense,finDetails.isRetirementFlag());
				String status=result.getString("status");

				if(status.equals("success"))
				{
				//System.out.println("No Negative value success");
				assets=result.getJSONArray("assets");
				tax=result.getJSONArray("tax");
				JSONArray newSpousePlot = new JSONArray();
				for(int i=0;i<SpouseIncomeArray.length();i++)
				{
					if(SpouseIncomeArray.getJSONObject(i).getDouble("value") > 0)
					{
						JSONObject spousePlotObj = new JSONObject();
						spousePlotObj.put("year",SpouseIncomeArray.getJSONObject(i).getInt("year"));
						spousePlotObj.put("spouseIncome",SpouseIncomeArray.getJSONObject(i).getDouble("value"));
						newSpousePlot.put(spousePlotObj);
						break;
					}
				}

				for(int i=0;i<SpouseIncomeArray.length();i++)
				{
					for(int j=1;j<spousePlot.length();j++)
					{
						if((SpouseIncomeArray.getJSONObject(i).getInt("year") == spousePlot.getJSONObject(j).getInt("year")) && SpouseIncomeArray.getJSONObject(i).getDouble("value") > 0)
						{
							JSONObject spousePlotObj = new JSONObject();
							spousePlotObj.put("year", SpouseIncomeArray.getJSONObject(i).getInt("year"));
							spousePlotObj.put("spouseIncome", SpouseIncomeArray.getJSONObject(i).getDouble("value"));
							newSpousePlot.put(spousePlotObj);
						}
					}
				}

				MongoDBConnection.incomeProfileCol
				.update("{'user_id':'" + user_id + "','profile_name':'" + finPlanProfileName + "'}")
				.upsert()
				.multi()
				.with("{$set: {'spousePlot': "+ newSpousePlot +" ,'spouse_income':" + SpouseIncomeArray + "," + "'combined_income':" + combinedIncome + "," + "'userExpense':" + userExpense
						+ "," + "'user_income':" + userIncome + "," + "'modifiedTs':'"
						+ dateFormat.format(date) + "'," + "'assests':" + assets + "," + "'tax':"+tax+ "}}");

				MongoDBConnection.goalcoll
				.update("{'_id': '" + goal_id + "'}")
				.upsert()
				.multi()
				.with("{$set: {'marriageYear':" + newMarriageYear + ",'marriageCost':" + newMarriageCost
						+ ",'modified_ts':'" + dateFormat.format(date) + "'}}");

				MongoDBConnection.goalcoll.update("{'_id': '" + goal_id + "'}").upsert().multi().with("{$set: {'nonHousingExpense':" +newNonHousingExpenses + ",'housingExpenses':" + newHousingExpenses + "}}");


				MongoDBConnection.goalcoll.update("{'_id': '" + goal_id + "'}").upsert().multi()
				.with("{$set: {'birthYear':" + birthYear + ",'spouseAge':" + newSpouseAge + ",'spouseIncome':" + newSpouseIncome+ "}}");
				MongoDBConnection.finplancol.update("{'_id': '" + fin_id + "'}").upsert().multi()
				.with("{$set: {'fillingExemtion':" + fillingExemtion + "}}");
				MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi().with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits + ",'deductions':" + deductions + ",'userExpense':" + userExpense + ",'fillingExemtion':" + fillingExemtion + ",'marital_status':'Yes','spouseAge':"+newSpouseAge+",'spouseBirthYear':"+(currentYear-newSpouseAge)+"}}");



				JSONObject marriage_expensedata = new JSONObject();
				marriage_expensedata.put("annualexpense", newMarriageCost);
				marriage_expensedata.put("endYear", newMarriageYear);
				marriage_expensedata.put("startYear", newMarriageYear);

				MongoDBConnection.finplancol.update("{'_id': '" + fin_id + "'}").upsert().multi()
				.with("{$set: {'expense.marriage_expense':" + marriage_expensedata + ",'marital_status':'Yes'}}");
				responseToRestController.put("status", "success");
				responseToRestController.put("goal_id", goal_id);
				//System.out.println(assets);
				responseToRestController.put("marriageYear", newMarriageYear);
				responseToRestController.put("marriageCost", newMarriageCost);
				responseToRestController.put("annualexpense", newMarriageCost);
				responseToRestController.put("status", "success");
				responseToRestController.put("goal_id", goal_id);
				/*responseToRestController.put("annualexpense", decimalFloat.format(annualexpense));*/
				responseToRestController.put("plan_name", plan_name);
				JSONObject systemLog = new JSONObject();
				systemLog.put("message", "Goal Updated Successfully!!");
				systemLog.put("userName", Details.getName());
				systemLog.put("user_id", user_id);
				systemLog.put("createdTs", dateFormat.format(date));
				BasicDBObject systemLogBasicObject = new BasicDBObject("user_id", user_id).append("userName", systemLog.getString("userName"))
						.append("message", systemLog.getString("message")).append("createdTs", dateFormat.format(date));
				MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);

				}
				else
				{
				responseToRestController.put("status", "fail");
				}

			}
			//System.out.println("responseToRestController insertMarriageGoalData-->"+responseToRestController);
			//responseToRestController.put("status", "success");
		///	responseToRestController.put("marriageYear", goal_id);
			return responseToRestController;

		} catch (Exception e) {
			e.printStackTrace();
			return responseToRestController;
		}
	}


	public JSONObject editMarriageGoalData(Marriagegoalmodel marriageGoalModel) {
		JSONObject responseToRestController = new JSONObject();

		try {
			FinPlan user = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}", marriageGoalModel.getUser_id(),marriageGoalModel.getPlan_name()).as(FinPlan.class);

			JSONObject ExpenseArrayJson = new JSONObject(jsonMapper.writeValueAsString(user));
            JSONArray userExpense=ExpenseArrayJson.getJSONArray("userExpense");

            if(userExpense.getJSONObject(0).getInt("year")==marriageGoalModel.getMarriageYear()) {
                responseToRestController.put("HousingExpenses",userExpense.getJSONObject(0).getInt("housingExpense")/12);
                responseToRestController.put("NonHousingExpense",userExpense.getJSONObject(0).getInt("nonHousingExpense")/12);
            }
            else {
            for(int i=0;i<userExpense.length();i++) {

                if(userExpense.getJSONObject(i).getInt("year")==marriageGoalModel.getMarriageYear()-1) {
                    responseToRestController.put("HousingExpenses",userExpense.getJSONObject(i).getInt("housingExpense")/12);
                    responseToRestController.put("NonHousingExpense",userExpense.getJSONObject(i).getInt("nonHousingExpense")/12);
                }

            }
            }
			//System.out.println("HousingExpenses..."+user.getRentalExpenses()+"NonHousingExpense..."+user.getMonthlyExpenses());
			responseToRestController.put("status","success");
			return responseToRestController;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return responseToRestController;
	}

	public JSONObject loadMarriageGoalData(Marriagegoalmodel marriageGoalModel) {
		JSONObject responseToRestController = new JSONObject();
		String goal_id = marriageGoalModel.getGoal_id();
		try {
			User user = MongoDBConnection.userColl.findOne("{_id:#}", marriageGoalModel.getUser_id()).as(User.class);
			Marriagegoalmodel marriageGoalModeldata = MongoDBConnection.goalcoll.findOne("{_id:#}", goal_id).as(Marriagegoalmodel.class);
			responseToRestController.put("status", "success");
			responseToRestController.put("marriageYear", marriageGoalModeldata.getMarriageYear());
			responseToRestController.put("marriageCost", marriageGoalModeldata.getMarriageCost());
			responseToRestController.put("annualexpense", marriageGoalModeldata.getAnnualexpense());
			responseToRestController.put("plan_name", marriageGoalModeldata.getPlan_name());
			responseToRestController.put("goal_id", marriageGoalModeldata.get_id());
			responseToRestController.put("newExpense", marriageGoalModeldata.getNewMonthlyExpense());
			responseToRestController.put("spouseAge", marriageGoalModeldata.getSpouseAge());
			responseToRestController.put("spouseIncome", marriageGoalModeldata.getSpouseIncome());
			responseToRestController.put("completedStatus", marriageGoalModeldata.getCompletedStatus());
			responseToRestController.put("goalFeasiblity", marriageGoalModeldata.getGoalFeasibility());
			responseToRestController.put("housingExpenses", marriageGoalModeldata.getHousingExpenses());
			responseToRestController.put("NonHousingExpenses", marriageGoalModeldata.getNonHousingExpense());
			responseToRestController.put("expense", (user.getRentalExpenses() + user.getMonthlyExpenses()) * 12);
			if (marriageGoalModeldata.getGoalFeasibility() == false) {
				responseToRestController.put("marriageFail", "you are not able to save your goal of wedding");
			} else {
				responseToRestController.put("marriageSuccess", "You are on your goal to save for your wedding");
			}
			return responseToRestController;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return responseToRestController;
	}


}
