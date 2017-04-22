package com.mongorest.olahtek.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SystemPropertyUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoOptions;
import com.mongodb.client.MongoDatabase;
import com.mongorest.olahtek.model.Counters;
import com.mongorest.olahtek.model.Emergencyfundmodel;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.User;

@Service("incomeProfileService")
@Transactional
public class IncomeProfileImpl implements IncomeProfileService {
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
	String minimumDistributioncoll = MongoDBConnection.minimumDistributioncollection;
	String usersCollection = MongoDBConnection.usersCollection;
	String systemLog = MongoDBConnection.systemLog;
	String stateTaxCollection = MongoDBConnection.stateTaxCollection;
	String incomeProfileCollection = MongoDBConnection.incomeProfileCollection;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
	Date date = new Date();
	DecimalFormat decimalFloat = new DecimalFormat("#.##");
	ObjectMapper jsonMapper = new ObjectMapper();
	public static double amountMorgage = 0;
	static int newYear = 0;

	@JsonCreator

	/*------------------------------------Saving Expenses-----------------------------------*/
	@Override
	public JSONObject saveExpenses(IncomeProfile dataFormServlet) {
		JSONObject responseToController = new JSONObject();
		try {
			double housingExpense = dataFormServlet.getHousingExpense();
			double nonHousingExpense = dataFormServlet.getNonHousingExpense();

			MongoDBConnection.incomeProfileCol
					.update("{'user_id': '" + dataFormServlet.getUser_id() + "', 'profile_name' : '"
							+ dataFormServlet.getProfile_name() + "'}")
					.upsert().multi().with("{$set: {'editExpenseFlag':" + 1 + ", 'housingExpense' : " + housingExpense
							+ ", 'nonHousingExpense' : " + nonHousingExpense + "}}");
			responseToController.put("status", "success");
			responseToController.put("editExpenseFlag", 1);
			return responseToController;
		} catch (Exception e) {
			e.printStackTrace();
			return responseToController;
		}
	}

	/*------------------------------------Creating Income Profile-----------------------------------*/
	@Override
	public JSONObject createIncomeProfile(IncomeProfile dataFormServlet) {
		JSONObject responseToController = new JSONObject();
		int flag = 0;
		try {
			MongoCollection systemLogCollection = jongo.getCollection(systemLog);
			JSONObject systemLog = new JSONObject();
			MongoCollection counters = jongo.getCollection(counterCollection);
			MongoCollection incomeProfileCol = jongo.getCollection(incomeProfileCollection);
			MongoCollection userColl = jongo.getCollection(usersCollection);
			MongoCollection finPlanCol = jongo.getCollection(financialPlanCollection);
			JSONObject retPlot = new JSONObject();
			JSONObject lastPlot = new JSONObject();

			int retPlotIndex = 0;
			int lastPlotIndex = 0;
			User checkMeritalStatus = userColl.findOne("{_id:#}", dataFormServlet.getUser_id()).as(User.class);
			try {
				responseToController.put("status", "fail");
				IncomeProfile incomeProfile = incomeProfileCol.findOne("{user_id:#,profile_name:#}",
						dataFormServlet.getUser_id(), dataFormServlet.getProfile_name()).as(IncomeProfile.class);
				if (incomeProfile != null) {
					responseToController.put("status", "fail");
					responseToController.put("message", "income profile already exist");
					return responseToController;
				}
				User userDetailsTemp = MongoDBConnection.userColl.findOne("{_id:#}", dataFormServlet.getUser_id())
						.as(User.class);
				JSONObject incomeJson = new JSONObject(jsonMapper.writeValueAsString(dataFormServlet));
				//// System.out.println("incomeJson:: "+incomeJson);
				JSONArray user_income = incomeJson.getJSONArray("income");
				JSONArray userPlot = incomeJson.getJSONArray("userPlot");
				JSONArray spousePlot = new JSONArray();
				JSONArray combined_income = new JSONArray();
				JSONArray spouse_income = new JSONArray();
				spouse_income = null;
				JSONArray assests = new JSONArray();
				JSONArray userExpense = new JSONArray();
				IncomeProfile incomeProf = incomeProfileCol
						.findOne("{user_id:#,profile_name:#}", dataFormServlet.getUser_id(), "constant_income")
						.as(IncomeProfile.class);
				double housingExpense = incomeProf.getHousingExpense();
				double nonHousingExpense = incomeProf.getNonHousingExpense();

				JSONObject incomeJsonFromConstantProfile = new JSONObject(jsonMapper.writeValueAsString(incomeProf));
				JSONArray userIncomeDB = incomeJsonFromConstantProfile.getJSONArray("user_income");
				JSONArray spouseIncomeDB = incomeJsonFromConstantProfile.getJSONArray("spouse_income");
				userExpense = incomeJsonFromConstantProfile.getJSONArray("userExpense");
				JSONArray equity = incomeJsonFromConstantProfile.getJSONArray("equity");
				String marital_status;
				if (checkMeritalStatus.getMarital_status().equals("Yes")
						|| !incomeJson.isNull("spouse_income") == true) {
					if (incomeJson.getJSONArray("spouse_income").length() > 0) {
						spouse_income = incomeJson.getJSONArray("spouse_income");
						combined_income = calCombinedIncome(user_income, spouse_income);
						spousePlot = incomeJson.getJSONArray("spousePlot");
					} else {
						spouse_income = null;
						combined_income = null;
					}
					marital_status = "Yes";
				} else {
					combined_income = user_income;
					marital_status = "No";
				}
				String user_id = dataFormServlet.getUser_id();
				JSONArray oldfillingExemtion = new JSONArray();
				String[] out = userDetailsTemp.getCreatedTs().split("/");
				int registrationYear = Integer.parseInt(out[0]);
				int noOfExcemtion = 1 + userDetailsTemp.getChildCount();
				int year = incomeJson.getJSONArray("income").getJSONObject(0).getInt("year");
				if (checkMeritalStatus.getMarital_status().equals("Yes")
						|| !incomeJson.isNull("spouse_income") == true) {
					noOfExcemtion = noOfExcemtion + 1;

					if (userDetailsTemp.getFilingStatus().equals("Head of Household")) {
						noOfExcemtion = noOfExcemtion + userDetailsTemp.getDependants() - 1;
					} else if (userDetailsTemp.getFilingStatus().equals("Married Filing Separately")) {
						noOfExcemtion = noOfExcemtion - 1;
					} else if (userDetailsTemp.getFilingStatus().equals("Qualified Widow")) {
						noOfExcemtion = noOfExcemtion - 1;
					}
				}
				JSONArray childArray = new JSONArray();
				JSONObject userDetailsFromDB = new JSONObject(jsonMapper.writeValueAsString(userDetailsTemp));
				for (int i = 0; i < combined_income.length(); i++) {
					int noOfExcemtion1 = noOfExcemtion;
					if (userDetailsTemp.getChildCount() != 0) {
						childArray = userDetailsFromDB.getJSONArray("childs");
						for (int k = 0; k < userDetailsTemp.getChildCount(); k++) {
							if (year - (registrationYear - childArray.getJSONObject(k).getInt("age")) > 18) {
								if ((childArray.getJSONObject(k).getString("flag").equals("Yes")
										&& year - (registrationYear - childArray.getJSONObject(k).getInt("age")) > 24)
										|| (!childArray.getJSONObject(k).getString("flag").equals("Yes"))) {
									noOfExcemtion1 = noOfExcemtion1 - 1;
								}
							}
						}
					}
					year++;
					JSONObject obj = new JSONObject();
					obj.put("fillingStatus", userDetailsTemp.getFilingStatus());
					obj.put("noOfExcemtion", noOfExcemtion1);
					obj.put("year", combined_income.getJSONObject(i).getInt("year"));
					oldfillingExemtion.put(obj);
				}
				for (int i = 0; i < combined_income.length(); i++) {
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

				int age = userDetailsTemp.getAge();
				JSONObject result = new JSONObject();
				//// System.out.println("Bala..combined
				//// income"+combined_income);
				//// System.out.println("Bala..spouse income"+spouse_income);
				//// System.out.println("Bala..marital status"+marital_status);
				//// System.out.println("Bala..marital
				//// status"+oldfillingExemtion);
				double user401k = 18000;
				double spouse401k = 18000;
				// -------------------------------------------new code
				// (Mahi)--------------------------------------
				int SpouseStartYear = 0;
				int userStartYear = 0;
				int userReterementAge = 70;
				int spouseReterementAge = 70;
				double newUser401k = 0;
				double newSpouse401k = 0;
				double newUserIra = 0;
				double newSpouseIra = 0;
				userStartYear = userDetailsTemp.getBirthYear() + 70;
				if (userDetailsTemp.getMarital_status().equals("Yes")) {
					SpouseStartYear = userDetailsTemp.getSpouseBirthYear() + 70;
				} else if (userDetailsTemp.getMarital_status().equals("Yes")) {
					SpouseStartYear = userDetailsTemp.getSpouseBirthYear() + 70;
				}

				if (checkMeritalStatus.getMarital_status().equals("Yes")
						|| !incomeJson.isNull("spouse_income") == true && spouseIncomeDB.length() > 0) {
					if (userStartYear < SpouseStartYear) {
						for (int u = 0; u < user_income.length(); u++) {
							if (userIncomeDB.getJSONObject(u).getInt("year") < userStartYear) {
								double housing = userExpense.getJSONObject(u).getInt("housingExpense");
								double nonHousing = userExpense.getJSONObject(u).getInt("nonHousingExpense");
								double diff1 = user_income.getJSONObject(u).getDouble("value")
										- userIncomeDB.getJSONObject(u).getDouble("value");
								double diff2 = spouse_income.getJSONObject(u).getDouble("value")
										- spouseIncomeDB.getJSONObject(u).getDouble("value");
								double diff = diff1 + diff2;
								double newHousing = housing + (housingExpense * diff);
								double newNonHousing = nonHousing + (nonHousingExpense * diff);
								JSONObject expenseResponse = calculateExpenses(housing, nonHousing, newHousing,
										newNonHousing);
								userExpense.getJSONObject(u).put("totalExpense",
										expenseResponse.getDouble("totalExpense"));
								userExpense.getJSONObject(u).put("housingExpense",
										expenseResponse.getDouble("housingExpense"));
								userExpense.getJSONObject(u).put("nonHousingExpense",
										expenseResponse.getDouble("nonHousingExpense"));
							}
						}
					} else {
						for (int u = 0; u < user_income.length(); u++) {
							if (spouseIncomeDB.getJSONObject(u).getInt("year") < SpouseStartYear) {
								double housing = userExpense.getJSONObject(u).getInt("housingExpense");
								double nonHousing = userExpense.getJSONObject(u).getInt("nonHousingExpense");
								double diff1 = user_income.getJSONObject(u).getDouble("value")
										- userIncomeDB.getJSONObject(u).getDouble("value");
								double diff2 = spouse_income.getJSONObject(u).getDouble("value")
										- spouseIncomeDB.getJSONObject(u).getDouble("value");
								double diff = diff1 + diff2;
								double newHousing = housing + (housingExpense * diff);
								double newNonHousing = nonHousing + (nonHousingExpense * diff);
								JSONObject expenseResponse = calculateExpenses(housing, nonHousing, newHousing,
										newNonHousing);
								userExpense.getJSONObject(u).put("totalExpense",
										expenseResponse.getDouble("totalExpense"));
								userExpense.getJSONObject(u).put("housingExpense",
										expenseResponse.getDouble("housingExpense"));
								userExpense.getJSONObject(u).put("nonHousingExpense",
										expenseResponse.getDouble("nonHousingExpense"));
							}
						}
					}

				} else {
					for (int u = 0; u < user_income.length(); u++) {
						if (userIncomeDB.getJSONObject(u).getInt("year") < userStartYear) {
							double housing = userExpense.getJSONObject(u).getInt("housingExpense");
							double nonHousing = userExpense.getJSONObject(u).getInt("nonHousingExpense");
							double diff = user_income.getJSONObject(u).getDouble("value")
									- userIncomeDB.getJSONObject(u).getDouble("value");
							double newHousing = housing + (housingExpense * diff);
							double newNonHousing = nonHousing + (nonHousingExpense * diff);
							JSONObject expenseResponse = calculateExpenses(housing, nonHousing, newHousing,
									newNonHousing);
							userExpense.getJSONObject(u).put("totalExpense", expenseResponse.getDouble("totalExpense"));
							userExpense.getJSONObject(u).put("housingExpense",
									expenseResponse.getDouble("housingExpense"));
							userExpense.getJSONObject(u).put("nonHousingExpense",
									expenseResponse.getDouble("nonHousingExpense"));
						}
					}
				}
				System.out.println("Calculated Expenses:: " + userExpense);
				RetirementGoalServiceImpl goalservice = new RetirementGoalServiceImpl();
				double userAIME = goalservice.findUserAIME(userReterementAge, user_income, userStartYear);
				//// System.out.println("mahi AIME ;"+userAIME);
				int userFRA = goalservice.findUserFRA(userReterementAge, userDetailsTemp.getBirthYear());
				JSONObject userJsonSSB = new JSONObject();
				JSONObject spouseJson = new JSONObject();
				JSONObject allIncomes = new JSONObject();
				int retirementAge = userReterementAge;
				System.out.println(userDetailsTemp.getMarital_status().equals("Yes"));
				System.out.println(incomeJson.isNull("spouse_income"));
				System.out.println(spouseIncomeDB.length());
				if (userDetailsTemp.getMarital_status().equals("Yes")
						|| !incomeJson.isNull("spouse_income") == true && spouseIncomeDB.length() > 0) {
					double spouseAIME = goalservice.findUserAIME(spouseReterementAge, spouse_income, SpouseStartYear);
					userJsonSSB = goalservice.calculateUserSSB(
							spouse_income.getJSONObject(spouse_income.length() / 2).getDouble("value"), "Yes", userFRA,
							0, 856, 5157, userAIME, spouseAIME);

					spouseJson.put("firetBendPoint", 856);
					spouseJson.put("spouseRetirementAge", spouseReterementAge);
					spouseJson.put("userRetirementAge", userReterementAge);
					spouseJson.put("useFfra", userFRA);
					spouseJson.put("spouseFfra",
							goalservice.findUserFRA(spouseReterementAge, userDetailsTemp.getSpouseBirthYear()));
					spouseJson.put("userAime", userAIME);
					spouseJson.put("spouseAime", spouseAIME);
					spouseJson.put("secondBendPoint", 5157);
					allIncomes = goalservice.insertRetirementIncomeNew(
							((Double) (userJsonSSB.get("User" + retirementAge))), spouseJson, user_id, user_income,
							spouse_income, userStartYear, SpouseStartYear, SpouseStartYear);
					user_income = allIncomes.getJSONArray("user_income");
					spouse_income = allIncomes.getJSONArray("spouse_income");
					combined_income = allIncomes.getJSONArray("combined_income");
					System.out.println("Before user_income in if :: " + user_income);
					if (userPlot.length() > 3) {
						retPlot = userPlot.getJSONObject(userPlot.length() - 2);
						lastPlot = userPlot.getJSONObject(userPlot.length() - 3);
						for (int u = 0; u < user_income.length(); u++) {
							if (user_income.getJSONObject(u).getInt("year") == retPlot.getInt("year")) {
								retPlotIndex = u;
							}
							if (user_income.getJSONObject(u).getInt("year") == lastPlot.getInt("year")) {
								lastPlotIndex = u;
							}
						}
						double interpolateFinal = (user_income.getJSONObject(retPlotIndex).getDouble("value")
								- user_income.getJSONObject(lastPlotIndex).getDouble("value"))
								/ (retPlot.getInt("year") - lastPlot.getInt("year"));
						/* if ( lastPlotIndex != 0 ) { */
						for (int u = lastPlotIndex + 1; u < retPlotIndex; u++) {
							double newUserIncomeValue = user_income.getJSONObject(u - 1).getDouble("value")
									+ interpolateFinal;
							user_income.getJSONObject(u).put("value", newUserIncomeValue);
						}
						/*
						 * } else { for ( int u = lastPlotIndex + 1; u <
						 * retPlotIndex; u++ ) { double newUserIncomeValue =
						 * user_income.getJSONObject(u-1).getDouble("value") +
						 * interpolateFinal;
						 * user_income.getJSONObject(u).put("value",
						 * newUserIncomeValue); } }
						 */
					}
					System.out.println("After user_income in if:: " + user_income);
					System.out.println("Before spouse_income in if:: " + spouse_income);
					if (spousePlot.length() > 3) {
						retPlot = spousePlot.getJSONObject(spousePlot.length() - 2);
						lastPlot = spousePlot.getJSONObject(spousePlot.length() - 3);
						retPlotIndex = 0;
						lastPlotIndex = 0;
						for (int s = 0; s < spouse_income.length(); s++) {
							if (spouse_income.getJSONObject(s).getInt("year") == retPlot.getInt("year")) {
								retPlotIndex = s;
							}
							if (spouse_income.getJSONObject(s).getInt("year") == lastPlot.getInt("year")) {
								lastPlotIndex = s;
							}
						}
						double interpolateFinal = (spouse_income.getJSONObject(retPlotIndex).getDouble("value")
								- spouse_income.getJSONObject(lastPlotIndex).getDouble("value"))
								/ (retPlot.getInt("year") - lastPlot.getInt("year"));

						/* if ( lastPlotIndex != 0 ) { */
						for (int s = lastPlotIndex + 1; s < retPlotIndex; s++) {
							double newIncomeValue = spouse_income.getJSONObject(s - 1).getDouble("value")
									+ interpolateFinal;
							spouse_income.getJSONObject(s).put("value", newIncomeValue);
						}
						/*
						 * } else { for ( int s = lastPlotIndex + 1; s <
						 * retPlotIndex; s++ ) { double newIncomeValue =
						 * spouse_income.getJSONObject(s-1).getDouble("value") +
						 * interpolateFinal;
						 * spouse_income.getJSONObject(s).put("value",
						 * newIncomeValue); } }
						 */
					}
					System.out.println("After spouse_income in if:: " + spouse_income);
				} else {
					userJsonSSB = goalservice.calculateUserSSB(0, "No", userFRA, 0, 856, 5157, userAIME, 0);

					/*
					 * spouseJson.put("firetBendPoint", 856);
					 * spouseJson.put("spouseRetirementAge",spouseReterementAge)
					 * ; spouseJson.put("userRetirementAge", userReterementAge);
					 * spouseJson.put("useFfra", userFRA);
					 * spouseJson.put("spouseFfra",
					 * goalservice.findUserFRA(spouseReterementAge,
					 * userDetailsTemp.getSpouseBirthYear()));
					 * spouseJson.put("userAime",userAIME);
					 * spouseJson.put("spouseAime", goalservice.findUserAIME(
					 * spouseReterementAge,spouse_income,SpouseStartYear));
					 * spouseJson.put("secondBendPoint", 5157);
					 */
					spouseJson = null;

					allIncomes = goalservice.insertRetirementIncomeNewSingle(
							((Double) (userJsonSSB.get("User" + retirementAge))), spouseJson, user_id, user_income,
							spouse_income, userStartYear, SpouseStartYear);
					user_income = allIncomes.getJSONArray("user_income");
					// spouse_income=allIncomes.getJSONArray("spouse_income");
					combined_income = allIncomes.getJSONArray("combined_income");
					System.out.println("Before user_income in else :: " + user_income);
					if (userPlot.length() > 3) {
						retPlot = userPlot.getJSONObject(userPlot.length() - 2);
						lastPlot = userPlot.getJSONObject(userPlot.length() - 3);
						for (int u = 0; u < user_income.length(); u++) {
							if (user_income.getJSONObject(u).getInt("year") == retPlot.getInt("year")) {
								retPlotIndex = u;
							}
							if (user_income.getJSONObject(u).getInt("year") == lastPlot.getInt("year")) {
								lastPlotIndex = u;
							}
						}
						double interpolateFinal = (user_income.getJSONObject(retPlotIndex).getDouble("value")
								- user_income.getJSONObject(lastPlotIndex).getDouble("value"))
								/ (retPlot.getInt("year") - lastPlot.getInt("year"));
						/* if ( lastPlotIndex != 0 ) { */
						for (int u = lastPlotIndex + 1; u < retPlotIndex; u++) {
							double newUserIncomeValue = user_income.getJSONObject(u - 1).getDouble("value")
									+ interpolateFinal;
							user_income.getJSONObject(u).put("value", newUserIncomeValue);
						}
						/*
						 * } else { for ( int u = lastPlotIndex + 1; u <
						 * retPlotIndex; u++ ) { double newUserIncomeValue =
						 * user_income.getJSONObject(u-1).getDouble("value") -
						 * interpolateFinal;
						 * user_income.getJSONObject(u).put("value",
						 * newUserIncomeValue); } }
						 */
					}
					System.out.println("After user_income in else :: " + user_income);
				}

				user401k = 18000;
				spouse401k = 18000;

				// -----------------------------------------------------------------------------------------
				int ageAtRegistration = checkMeritalStatus.getAge();
				int spouseAgeForLimits = checkMeritalStatus.getSpouseAge();
				JSONArray impLimits = incomeJsonFromConstantProfile.getJSONArray("limits");
				for (int i = 0; i < impLimits.length(); i++) {
					if (checkMeritalStatus.getMarital_status().equals("Yes")) {
						JSONObject newLimits = CalculationEngine.limiteAfterRetirement(user401k, spouse401k, 0, 0, 0, 0,
								ageAtRegistration, user_income.getJSONObject(i).getDouble("value"),
								spouse_income.getJSONObject(i).getDouble("value"),
								oldfillingExemtion.getJSONObject(i).getString("fillingStatus"), spouseAgeForLimits,
								impLimits.getJSONObject(i), (user_income.getJSONObject(i).getDouble("value")
										+ spouse_income.getJSONObject(i).getDouble("value")),
								i, null);

						impLimits.put(i, newLimits);
						spouseAgeForLimits++;
					} else {
						JSONObject newLimits = CalculationEngine.limiteAfterRetirement(user401k, 0, 0, 0, 0, 0,
								ageAtRegistration, user_income.getJSONObject(i).getDouble("value"), 0,
								oldfillingExemtion.getJSONObject(i).getString("fillingStatus"), 0,
								impLimits.getJSONObject(i), (user_income.getJSONObject(i).getDouble("value") + 0), i,
								null);

						impLimits.put(i, newLimits);
						spouseAgeForLimits++;

					}
				}

				result = CalculationEngine.sweepingOfMoney(user_income, null, combined_income, spouse_income,
						userExpense, impLimits, marital_status, incomeJsonFromConstantProfile.getJSONArray("assests"),
						incomeJsonFromConstantProfile.getJSONArray("tax"), user_id, oldfillingExemtion, age,
						userDetailsTemp.getSpouseAge(), 0, false, null, null, null, null, null, null, null, null,
						false);

				String status = result.getString("status");

				if (status.equals("success")) {
					//// System.out.println("Bala..inside success of income
					//// profile edit, creation of new profile");
					if (dataFormServlet.getPlan_name() != null) {
						incomeProf = incomeProfileCol
								.findOne("{user_id:#,profile_name:#}", dataFormServlet.getUser_id(), dataFormServlet.getSelectedProfileName())
								.as(IncomeProfile.class);
						housingExpense = incomeProf.getHousingExpense();
						nonHousingExpense = incomeProf.getNonHousingExpense();

						incomeJsonFromConstantProfile = new JSONObject(jsonMapper.writeValueAsString(incomeProf));
						userIncomeDB = incomeJsonFromConstantProfile.getJSONArray("user_income");
						spouseIncomeDB = incomeJsonFromConstantProfile.getJSONArray("spouse_income");
						userExpense = incomeJsonFromConstantProfile.getJSONArray("userExpense");
						equity = incomeJsonFromConstantProfile.getJSONArray("equity");
						
						FinPlan finPlanAssests = finPlanCol.findOne("{usr_id:#,plan_name:#}",
								dataFormServlet.getUser_id(), dataFormServlet.getPlan_name()).as(FinPlan.class);
						JSONObject finAssestsJSON = new JSONObject(jsonMapper.writeValueAsString(finPlanAssests));
						JSONArray userExpenseFin = finAssestsJSON.getJSONArray("userExpense");
						JSONArray expenseObj = new JSONArray();
						JSONObject finPlanExpense = finAssestsJSON.getJSONObject("expense");
						if (!finPlanExpense.isNull("housing_expense")) {
							expenseObj = finPlanExpense.getJSONArray("housing_expense");
						}
						/*
						 * JSONObject j=new JSONObject(); j=null;
						 * if(finPlanExpense.isNull("housing_expense")==true) {
						 * expenseObj.put("housing_expense", j); } else {
						 * expenseObj.put("housing_expense",
						 * finPlanExpense.getJSONObject("housing_expense")); }
						 */
						JSONArray goals = finAssestsJSON.getJSONArray("goals");
						RetirementGoal retirementObj = null;
						for (int i = 0; i < goals.length(); i++) {
							RetirementGoal goalDetails = MongoDBConnection.goalcoll
									.findOne("{_id:#}", goals.getString(i)).as(RetirementGoal.class);
							if (goalDetails.getGoalType().equals("Retirement")) {
								retirementObj = goalDetails;
							}
						}
						SpouseStartYear = 0;
						userStartYear = 0;
						int spouseRetirementAge = 70;
						int userRetirementAge = 70;
						int UserSelectedSpouseYear = 0;
						newUser401k = 0;
						newSpouse401k = 0;
						newUserIra = 0;
						newSpouseIra = 0;
						if (retirementObj != null) {
							userStartYear = userDetailsTemp.getBirthYear() + retirementObj.getRetirementAge();
							userRetirementAge = retirementObj.getRetirementAge();
							newUser401k = retirementObj.getUserContributeAmount();
							UserSelectedSpouseYear = retirementObj.getUserSelectedSpouseYear();
							newSpouse401k = retirementObj.getSpouseContributeAmount();
							newUserIra = retirementObj.getUserContributionInIRA();
							newSpouseIra = retirementObj.getSpouseContributionInIRA();
							if (finPlanAssests.getMarital_status().equals("Yes")
									&& userDetailsTemp.getMarital_status().equals("No")) {
								SpouseStartYear = finPlanAssests.getSpouseBirthYear()
										+ retirementObj.getSpouseRetirementAge();
								spouseRetirementAge = retirementObj.getSpouseRetirementAge();
							} else if (userDetailsTemp.getMarital_status().equals("Yes")) {
								SpouseStartYear = userDetailsTemp.getSpouseBirthYear()
										+ retirementObj.getSpouseRetirementAge();
								spouseRetirementAge = retirementObj.getSpouseRetirementAge();
							}

						} else {
							userStartYear = userDetailsTemp.getBirthYear() + 70;
							if (finPlanAssests.getMarital_status().equals("Yes")
									&& userDetailsTemp.getMarital_status().equals("No")) {
								SpouseStartYear = finPlanAssests.getSpouseBirthYear() + 70;
							} else if (userDetailsTemp.getMarital_status().equals("Yes")) {
								SpouseStartYear = userDetailsTemp.getSpouseBirthYear() + 70;
							}
							UserSelectedSpouseYear = SpouseStartYear;

						}

						JSONObject retirementData = new JSONObject();
						retirementData.put("spouseStartYear", SpouseStartYear);
						retirementData.put("userStartYear", userStartYear);

						String emergencyType = null;
						String monthsOfIncome = null;
						String monthsOfExpense = null;
						if (finPlanAssests.isEmergencyFundFlag() == true) {
							Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
									.findOne("{fin_id:#,goalType:#}", finPlanAssests.get_id(), "Emergency Fund")
									.as(Emergencyfundmodel.class);
							emergencyType = emergencyObj.getTimePeriod();
							monthsOfIncome = emergencyObj.getMonthI();
							monthsOfExpense = emergencyObj.getMonthE();
						}

						// --------------------------------converting income
						// array to retirement income + normal income
						// -------------------------------------------//
						goalservice = new RetirementGoalServiceImpl();
						userAIME = goalservice.findUserAIME(userRetirementAge, user_income, userStartYear);

						userFRA = goalservice.findUserFRA(spouseRetirementAge, userDetailsTemp.getBirthYear());
						userJsonSSB = new JSONObject();
						spouseJson = new JSONObject();
						retirementAge = userRetirementAge;
						if (finPlanAssests.getMarital_status().equals("Yes")) {
							double spouseAIME = goalservice.findUserAIME(spouseRetirementAge, spouse_income,
									SpouseStartYear);
							userJsonSSB = goalservice.calculateUserSSB(
									spouse_income.getJSONObject(spouse_income.length() / 2).getDouble("value"), "Yes",
									userFRA, 0, 856, 5157, userAIME, spouseAIME);
							spouseJson.put("firetBendPoint", 856);
							spouseJson.put("spouseRetirementAge", spouseRetirementAge);
							spouseJson.put("userRetirementAge", userRetirementAge);
							spouseJson.put("useFfra", userFRA);
							spouseJson.put("spouseFfra",
									goalservice.findUserFRA(spouseRetirementAge, finPlanAssests.getSpouseBirthYear()));
							spouseJson.put("userAime", userAIME);
							spouseJson.put("spouseAime", spouseAIME);
							spouseJson.put("secondBendPoint", 5157);
							allIncomes = goalservice.insertRetirementIncomeNew(
									((Double) (userJsonSSB.get("User" + retirementAge))), spouseJson, user_id,
									user_income, spouse_income, userStartYear, SpouseStartYear, UserSelectedSpouseYear);
							user_income = allIncomes.getJSONArray("user_income");
							spouse_income = allIncomes.getJSONArray("spouse_income");
							combined_income = allIncomes.getJSONArray("combined_income");
							System.out.println("Before finPlan in if user_income :: " + user_income);
							if (userPlot.length() > 3) {
								retPlot = userPlot.getJSONObject(userPlot.length() - 2);
								lastPlot = userPlot.getJSONObject(userPlot.length() - 3);
								for (int u = 0; u < user_income.length(); u++) {
									if (user_income.getJSONObject(u).getInt("year") == retPlot.getInt("year")) {
										retPlotIndex = u;
									}
									if (user_income.getJSONObject(u).getInt("year") == lastPlot.getInt("year")) {
										lastPlotIndex = u;
									}
								}
								double interpolateFinal = (user_income.getJSONObject(retPlotIndex).getDouble("value")
										- user_income.getJSONObject(lastPlotIndex).getDouble("value"))
										/ (retPlot.getInt("year") - lastPlot.getInt("year"));

								/* if ( lastPlotIndex != 0 ) { */
								for (int u = lastPlotIndex + 1; u < retPlotIndex; u++) {
									double newUserIncomeValue = user_income.getJSONObject(u - 1).getDouble("value")
											+ interpolateFinal;
									user_income.getJSONObject(u).put("value", newUserIncomeValue);
								}
								/*
								 * } else { for ( int u = lastPlotIndex + 1; u <
								 * retPlotIndex; u++ ) { double
								 * newUserIncomeValue =
								 * user_income.getJSONObject(u-1).getDouble(
								 * "value") + interpolateFinal;
								 * user_income.getJSONObject(u).put("value",
								 * newUserIncomeValue); } }
								 */
							}
							System.out.println("After finPlan in if user_income :: " + user_income);
							System.out.println("Before finPlan in if spouse_income :: " + spouse_income);
							if (spousePlot.length() > 3) {
								retPlot = spousePlot.getJSONObject(spousePlot.length() - 2);
								lastPlot = spousePlot.getJSONObject(spousePlot.length() - 3);
								retPlotIndex = 0;
								lastPlotIndex = 0;
								for (int s = 0; s < spouse_income.length(); s++) {
									if (spouse_income.getJSONObject(s).getInt("year") == retPlot.getInt("year")) {
										retPlotIndex = s;
									}
									if (spouse_income.getJSONObject(s).getInt("year") == lastPlot.getInt("year")) {
										lastPlotIndex = s;
									}
								}

								double interpolateFinal = (spouse_income.getJSONObject(retPlotIndex).getDouble("value")
										- spouse_income.getJSONObject(lastPlotIndex).getDouble("value"))
										/ (retPlot.getInt("year") - lastPlot.getInt("year"));

								/* if ( lastPlotIndex != 0 ) { */
								for (int s = lastPlotIndex + 1; s < retPlotIndex; s++) {
									double newIncomeValue = spouse_income.getJSONObject(s - 1).getDouble("value")
											+ interpolateFinal;
									spouse_income.getJSONObject(s).put("value", newIncomeValue);
								}
								/*
								 * } else { for ( int s = lastPlotIndex + 1; s <
								 * retPlotIndex; s++ ) { double newIncomeValue =
								 * spouse_income.getJSONObject(s-1).getDouble(
								 * "value") + interpolateFinal;
								 * spouse_income.getJSONObject(s).put("value",
								 * newIncomeValue); } }
								 */
							}
							System.out.println("After finPlan in if spouse_income :: " + spouse_income);
						} else {
							userJsonSSB = goalservice.calculateUserSSB(0, "No", userFRA, 0, 856, 5157, userAIME, 0);

							/*
							 * spouseJson.put("firetBendPoint", 856);
							 * spouseJson.put("spouseRetirementAge",
							 * spouseRetirementAge);
							 * spouseJson.put("userRetirementAge",
							 * userRetirementAge); spouseJson.put("useFfra",
							 * userFRA); spouseJson.put("spouseFfra",
							 * goalservice.findUserFRA(spouseRetirementAge,
							 * userDetailsTemp.getSpouseBirthYear()));
							 * spouseJson.put("userAime",userAIME);
							 * spouseJson.put("spouseAime",
							 * goalservice.findUserAIME(
							 * spouseRetirementAge,spouse_income,SpouseStartYear
							 * )); spouseJson.put("secondBendPoint", 5157);
							 */
							spouseJson = null;
							allIncomes = goalservice.insertRetirementIncomeNewSingle(
									((Double) (userJsonSSB.get("User" + retirementAge))), spouseJson, user_id,
									user_income, spouse_income, userStartYear, SpouseStartYear);
							user_income = allIncomes.getJSONArray("user_income");
							// spouse_income=allIncomes.getJSONArray("spouse_income");
							combined_income = allIncomes.getJSONArray("combined_income");
							System.out.println("Before finPlan in else user_income :: " + user_income);
							if (userPlot.length() > 3) {
								retPlot = userPlot.getJSONObject(userPlot.length() - 2);
								lastPlot = userPlot.getJSONObject(userPlot.length() - 3);
								for (int u = 0; u < user_income.length(); u++) {
									if (user_income.getJSONObject(u).getInt("year") == retPlot.getInt("year")) {
										retPlotIndex = u;
									}
									if (user_income.getJSONObject(u).getInt("year") == lastPlot.getInt("year")) {
										lastPlotIndex = u;
									}
								}
								double interpolateFinal = (user_income.getJSONObject(retPlotIndex).getDouble("value")
										- user_income.getJSONObject(lastPlotIndex).getDouble("value"))
										/ (retPlot.getInt("year") - lastPlot.getInt("year"));

								/* if ( lastPlotIndex != 0 ) { */
								for (int u = lastPlotIndex + 1; u < retPlotIndex; u++) {
									double newUserIncomeValue = user_income.getJSONObject(u - 1).getDouble("value")
											+ interpolateFinal;
									user_income.getJSONObject(u).put("value", newUserIncomeValue);
								}
								/*
								 * } else { for ( int u = lastPlotIndex + 1; u <
								 * retPlotIndex; u++ ) { double
								 * newUserIncomeValue =
								 * user_income.getJSONObject(u-1).getDouble(
								 * "value") + interpolateFinal;
								 * user_income.getJSONObject(u).put("value",
								 * newUserIncomeValue); } }
								 */
							}
							System.out.println("After finPlan in else user_income :: " + user_income);

						}
						if (checkMeritalStatus.getMarital_status().equals("Yes")
								|| !incomeJson.isNull("spouse_income") == true && spouseIncomeDB.length() > 0) {
							if (userStartYear < SpouseStartYear) {
								for (int u = 0; u < user_income.length(); u++) {
									if (userIncomeDB.getJSONObject(u).getInt("year") < userStartYear) {
										double housing = userExpenseFin.getJSONObject(u).getInt("housingExpense");
										double nonHousing = userExpenseFin.getJSONObject(u).getInt("nonHousingExpense");
										double diff1 = user_income.getJSONObject(u).getDouble("value")
												- userIncomeDB.getJSONObject(u).getDouble("value");
										double diff2 = spouse_income.getJSONObject(u).getDouble("value")
												- spouseIncomeDB.getJSONObject(u).getDouble("value");
										double diff = diff1 + diff2;
										double newHousing = housing + (housingExpense * diff);
										double newNonHousing = nonHousing + (nonHousingExpense * diff);
										JSONObject expenseResponse = calculateExpenses(housing, nonHousing, newHousing,
												newNonHousing);
										userExpenseFin.getJSONObject(u).put("totalExpense",
												expenseResponse.getDouble("totalExpense"));
										userExpenseFin.getJSONObject(u).put("housingExpense",
												expenseResponse.getDouble("housingExpense"));
										userExpenseFin.getJSONObject(u).put("nonHousingExpense",
												expenseResponse.getDouble("nonHousingExpense"));
									}
								}
							} else {
								for (int u = 0; u < user_income.length(); u++) {
									if (spouseIncomeDB.getJSONObject(u).getInt("year") < SpouseStartYear) {
										double housing = userExpenseFin.getJSONObject(u).getInt("housingExpense");
										double nonHousing = userExpenseFin.getJSONObject(u).getInt("nonHousingExpense");
										double diff1 = user_income.getJSONObject(u).getDouble("value")
												- userIncomeDB.getJSONObject(u).getDouble("value");
										double diff2 = spouse_income.getJSONObject(u).getDouble("value")
												- spouseIncomeDB.getJSONObject(u).getDouble("value");
										double diff = diff1 + diff2;
										double newHousing = housing + (housingExpense * diff);
										double newNonHousing = nonHousing + (nonHousingExpense * diff);
										JSONObject expenseResponse = calculateExpenses(housing, nonHousing, newHousing,
												newNonHousing);
										userExpenseFin.getJSONObject(u).put("totalExpense",
												expenseResponse.getDouble("totalExpense"));
										userExpenseFin.getJSONObject(u).put("housingExpense",
												expenseResponse.getDouble("housingExpense"));
										userExpenseFin.getJSONObject(u).put("nonHousingExpense",
												expenseResponse.getDouble("nonHousingExpense"));
									}
								}
							}

						} else {
							for (int u = 0; u < user_income.length(); u++) {
								if (userIncomeDB.getJSONObject(u).getInt("year") < userStartYear) {
									double housing = userExpenseFin.getJSONObject(u).getInt("housingExpense");
									double nonHousing = userExpenseFin.getJSONObject(u).getInt("nonHousingExpense");
									double diff = user_income.getJSONObject(u).getDouble("value")
											- userIncomeDB.getJSONObject(u).getDouble("value");
									double newHousing = housing + (housingExpense * diff);
									double newNonHousing = nonHousing + (nonHousingExpense * diff);
									JSONObject expenseResponse = calculateExpenses(housing, nonHousing, newHousing,
											newNonHousing);
									userExpenseFin.getJSONObject(u).put("totalExpense",
											expenseResponse.getDouble("totalExpense"));
									userExpenseFin.getJSONObject(u).put("housingExpense",
											expenseResponse.getDouble("housingExpense"));
									userExpenseFin.getJSONObject(u).put("nonHousingExpense",
											expenseResponse.getDouble("nonHousingExpense"));
								}
							}
						}

						System.out.println("Calculated Expenses:: " + userExpenseFin);
						user401k = 18000;
						spouse401k = 18000;
						ageAtRegistration = checkMeritalStatus.getAge();
						spouseAgeForLimits = finPlanAssests.getSpouseAge();

						JSONArray finLimits = finAssestsJSON.getJSONArray("limits");
						for (int i = 0; i < finLimits.length(); i++) {
							if (finPlanAssests.getMarital_status().equals("Yes")) {
								JSONObject newLimits = CalculationEngine.limiteAfterRetirement(user401k, spouse401k,
										newUser401k, newSpouse401k, newUserIra, newSpouseIra, ageAtRegistration,
										user_income.getJSONObject(i).getDouble("value"),
										spouse_income.getJSONObject(i).getDouble("value"),
										finAssestsJSON.getJSONArray("fillingExemtion").getJSONObject(i)
												.getString("fillingStatus"),
										spouseAgeForLimits, finLimits.getJSONObject(i),
										(user_income.getJSONObject(i).getDouble("value")
												+ spouse_income.getJSONObject(i).getDouble("value")),
										i, null);

								finLimits.put(i, newLimits);
								spouseAgeForLimits++;
							} else {
								JSONObject newLimits = CalculationEngine.limiteAfterRetirement(user401k, 0, newUser401k,
										newSpouse401k, newUserIra, newSpouseIra, ageAtRegistration,
										user_income.getJSONObject(i).getDouble("value"), 0,
										finAssestsJSON.getJSONArray("fillingExemtion").getJSONObject(i)
												.getString("fillingStatus"),
										0, finLimits.getJSONObject(i),
										(user_income.getJSONObject(i).getDouble("value") + 0), i, null);

								finLimits.put(i, newLimits);
								spouseAgeForLimits++;

							}
						}
						//// System.out.println("the
						//// userBAla%%%123"+userJsonSSB);
						result = CalculationEngine.sweepingOfMoney(user_income, finPlanAssests.get_id(),
								combined_income, spouse_income, userExpenseFin, finLimits,
								finPlanAssests.getMarital_status(), finAssestsJSON.getJSONArray("assests"),
								finAssestsJSON.getJSONArray("tax"), dataFormServlet.getUser_id(),
								finAssestsJSON.getJSONArray("fillingExemtion"), userDetailsTemp.getAge(),
								finPlanAssests.getSpouseAge(), finPlanAssests.getEmergencyFundAmt(),
								finPlanAssests.isEmergencyFundFlag(), finAssestsJSON.getJSONArray("deductions"),
								finAssestsJSON.getJSONArray("kidBirthYear"), retirementData, retirementObj, expenseObj,
								emergencyType, monthsOfIncome, monthsOfExpense, false);
						status = result.getString("status");
						if (status.equals("success")) {
							JSONArray assets = result.getJSONArray("assets");
							JSONArray tax = result.getJSONArray("tax");
							finPlanCol
									.update("{usr_id:'" + dataFormServlet.getUser_id() + "',plan_name:'"
											+ dataFormServlet.getPlan_name() + "'}")
									.upsert().multi()
									.with("{$set: {'userExpense' : " + userExpenseFin + " ,'assests':" + assets
											+ ",'tax':" + tax + ",'profile_name':'" + dataFormServlet.getProfile_name()
											+ "','limits':" + finLimits + "}}");
							responseToController.put("status", "success");
							Counters count = counters.findOne().as(Counters.class);
							String income_id = "incomeProfile" + count.getIncome_id();
							BasicDBObject dataExceptIncome = new BasicDBObject().append("_id", income_id)
									.append("createdTs", dateFormat.format(date))
									.append("profile_name", dataFormServlet.getProfile_name())
									.append("user_id", dataFormServlet.getUser_id())
									.append("housingExpense", housingExpense)
									.append("nonHousingExpense", nonHousingExpense).append("editExpenseFlag", 1);
							incomeProfileCol.insert(dataExceptIncome);
							counters.update("{'income_id':" + count.getIncome_id() + "}")
									.with("{$inc: {income_id: 1}}");
							userPlot.getJSONObject(userPlot.length() - 1).put("userIncome",
									user_income.getJSONObject(user_income.length() - 1).getDouble("retirement_income"));
							for (int user = 0; user < user_income.length(); user++) {
								if (user_income.getJSONObject(user).getDouble("retirement_income") > 0) {
									userPlot.getJSONObject(userPlot.length() - 2).put("userIncome",
											user_income.getJSONObject(user).getDouble("retirement_income"));
									break;
								}
							}
							if (spousePlot.length() > 0) {
								spousePlot.getJSONObject(spousePlot.length() - 1).put("spouseIncome", spouse_income
										.getJSONObject(spouse_income.length() - 1).getDouble("retirement_income"));
								for (int user = 0; user < spouse_income.length(); user++) {
									if (spouse_income.getJSONObject(user).getDouble("retirement_income") > 0) {
										spousePlot.getJSONObject(spousePlot.length() - 2).put("spouseIncome",
												spouse_income.getJSONObject(user).getDouble("retirement_income"));
										break;
									}
								}
							}
							if (checkMeritalStatus.getMarital_status().equals("Yes")
									|| !incomeJson.isNull("spouse_income") == true) {
								incomeProfileCol
										.update("{'_id': '" + income_id + "','profile_name':'"
												+ dataFormServlet.getProfile_name() + "'}")
										.upsert().multi()
										.with("{$set: {'user_income':" + user_income + ",'spouse_income':"
												+ spouse_income + ",'combined_income':" + combined_income + ",'tax':"
												+ tax + ",'userExpense':"
												+ incomeJsonFromConstantProfile.getJSONArray("userExpense") + "}}");
							} else {
								incomeProfileCol
										.update("{'_id': '" + income_id + "','profile_name':'"
												+ dataFormServlet.getProfile_name() + "'}")
										.upsert().multi()
										.with("{$set: {'user_income':" + user_income + ",'tax':" + tax
												+ ",'userExpense':"
												+ incomeJsonFromConstantProfile.getJSONArray("userExpense")
												+ ",'spouse_income':" + new JSONArray() + "}}");
							}
							incomeProfileCol
									.update("{'_id': '" + income_id + "','profile_name':'"
											+ dataFormServlet.getProfile_name() + "'}")
									.upsert().multi()
									.with("{$set: {'userExpense' : " + userExpense + ", 'assests':" + assets
											+ ",'equity':" + equity + ",'tax':" + tax + ",'limits':" + impLimits
											+ ",'oldIncomeProfile':'constant_income','userPlot' : " + userPlot
											+ ",'spousePlot' : " + spousePlot + "}}");
						} else {
							flag = 1;
							responseToController.put("status", "fail");
						}

					} else {
						assests = result.getJSONArray("assets");
						JSONArray tax = result.getJSONArray("tax");
						Counters count = counters.findOne().as(Counters.class);
						String income_id = "incomeProfile" + count.getIncome_id();
						////// System.out.println("income_id:: "+income_id);
						BasicDBObject dataExceptIncome = new BasicDBObject().append("_id", income_id)
								.append("createdTs", dateFormat.format(date))
								.append("profile_name", dataFormServlet.getProfile_name())
								.append("user_id", dataFormServlet.getUser_id())
								.append("housingExpense", housingExpense).append("nonHousingExpense", nonHousingExpense)
								.append("editExpenseFlag", 1);
						;
						incomeProfileCol.insert(dataExceptIncome);
						counters.update("{'income_id':" + count.getIncome_id() + "}").with("{$inc: {income_id: 1}}");
						userPlot.getJSONObject(userPlot.length() - 1).put("userIncome",
								user_income.getJSONObject(user_income.length() - 1).getDouble("retirement_income"));
						for (int user = 0; user < user_income.length(); user++) {
							if (user_income.getJSONObject(user).getDouble("retirement_income") > 0) {
								userPlot.getJSONObject(userPlot.length() - 2).put("userIncome",
										user_income.getJSONObject(user).getDouble("retirement_income"));
								break;
							}
						}
						if (spousePlot.length() > 0) {
							spousePlot.getJSONObject(spousePlot.length() - 1).put("spouseIncome", spouse_income
									.getJSONObject(spouse_income.length() - 1).getDouble("retirement_income"));
							for (int user = 0; user < spouse_income.length(); user++) {
								if (spouse_income.getJSONObject(user).getDouble("retirement_income") > 0) {
									spousePlot.getJSONObject(spousePlot.length() - 2).put("spouseIncome",
											spouse_income.getJSONObject(user).getDouble("retirement_income"));
									break;
								}
							}
						}
						////// System.out.println("userPlot:: " +userPlot);
						////// System.out.println("spousePlot:: " +spousePlot);
						if (checkMeritalStatus.getMarital_status().equals("Yes")
								|| !incomeJson.isNull("spouse_income") == true) {
							incomeProfileCol
									.update("{'_id': '" + income_id + "','profile_name':'"
											+ dataFormServlet.getProfile_name() + "'}")
									.upsert().multi()
									.with("{$set: {'user_income':" + user_income + ",'spouse_income':" + spouse_income
											+ ",'combined_income':" + combined_income + ",'tax':" + tax
											+ ",'userExpense':"
											+ incomeJsonFromConstantProfile.getJSONArray("userExpense")
											+ ",'spousePlot' : " + spousePlot + "}}");
						} else {
							incomeProfileCol
									.update("{'_id': '" + income_id + "','profile_name':'"
											+ dataFormServlet.getProfile_name() + "'}")
									.upsert().multi()
									.with("{$set: {'user_income':" + user_income + ",'tax':" + tax + ",'userExpense':"
											+ incomeJsonFromConstantProfile.getJSONArray("userExpense")
											+ ",'spouse_income':" + new JSONArray() + "}}");
						}
						incomeProfileCol
								.update("{'_id': '" + income_id + "','profile_name':'"
										+ dataFormServlet.getProfile_name() + "'}")
								.upsert().multi()
								.with("{$set: {'userExpense' : " + userExpense + " ,'assests':" + assests + ",'tax':"
										+ tax + ",'limits':" + incomeJsonFromConstantProfile.getJSONArray("limits")
										+ ",'equity':" + equity + ",'userPlot':" + userPlot + "}}");
					}
				} else {
					flag = 1;
					responseToController.put("status", "fail");
				}
				systemLog.put("type", "success");
				systemLog.put("message", "Income Profile Created Successfully!!");
				systemLog.put("userName", checkMeritalStatus.getName());
				systemLog.put("user_id", dataFormServlet.getUser_id());
				systemLog.put("createdTs", dateFormat.format(date));
			} catch (Exception e) {
				systemLog.put("type", "error");
				systemLog.put("message", "Income Profile Creation Failed!!");
				systemLog.put("userName", checkMeritalStatus.getName());
				systemLog.put("user_id", dataFormServlet.getUser_id());
				systemLog.put("createdTs", dateFormat.format(date));
				responseToController.put("status", "fail");
				e.printStackTrace();
			} finally {
				BasicDBObject systemLogBasicObject = new BasicDBObject("user_id", systemLog.getString("user_id"))
						.append("userName", systemLog.getString("userName")).append("type", systemLog.getString("type"))
						.append("message", systemLog.getString("message")).append("createdTs", dateFormat.format(date));
				systemLogCollection.insert(systemLogBasicObject);
			}
			if (flag == 0) {
				return responseToController.put("status", "success");
			} else {
				return responseToController.put("status", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return responseToController;
		}
	}

	/*------------------------------------Update Income Profile-----------------------------------*/
	@Override
	public JSONObject updateIncomeProfile(IncomeProfile dataFormServlet) {
		JSONObject responseToRestController = new JSONObject();

		try {
			responseToRestController.put("status", "fail");

			User checkMeritalStatus = MongoDBConnection.userColl.findOne("{_id:#}", dataFormServlet.getUser_id())
					.as(User.class);
			JSONObject incomeJson = new JSONObject(jsonMapper.writeValueAsString(dataFormServlet));
			JSONArray user_income = incomeJson.getJSONArray("income");
			JSONArray combined_income = new JSONArray();
			JSONArray spouse_income = new JSONArray();
			JSONArray taxIncmProfile = new JSONArray();
			JSONArray assetsIncmProfile = new JSONArray();
			JSONArray userPlot = incomeJson.getJSONArray("userPlot");
			JSONArray spousePlot = new JSONArray();
			JSONObject retPlot = new JSONObject();
			JSONObject lastPlot = new JSONObject();

			int retPlotIndex = 0;
			int lastPlotIndex = 0;
			int count = 0;
			int flag = 0;

			if (checkMeritalStatus.getMarital_status().equals("Yes") || !incomeJson.isNull("spouse_income") == true) {
				spouse_income = incomeJson.getJSONArray("spouse_income");
				spousePlot = incomeJson.getJSONArray("spousePlot");
				combined_income = calCombinedIncome(user_income, spouse_income);

			} else {
				combined_income = user_income;
			}
			System.out.println("user_income after calculating :: " + user_income);
			System.out.println("spouse_income after calculating :: " + spouse_income);
			IncomeProfile obj = MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}",
					dataFormServlet.getUser_id(), dataFormServlet.getProfile_name()).as(IncomeProfile.class);
			double housingExpense = obj.getHousingExpense();
			double nonHousingExpense = obj.getNonHousingExpense();
			JSONObject incomeJSON = new JSONObject(jsonMapper.writeValueAsString(obj));
			String user_id = dataFormServlet.getUser_id();
			User userDetailsTemp = MongoDBConnection.userColl.findOne("{_id:#}", dataFormServlet.getUser_id())
					.as(User.class);
			JSONArray userExpense = incomeJSON.getJSONArray("userExpense");
			JSONArray userIncomeDB = incomeJSON.getJSONArray("user_income");
			JSONArray spouseIncomeDB = incomeJSON.getJSONArray("spouse_income");
			JSONArray oldfillingExemtion = new JSONArray();
			String[] out = userDetailsTemp.getCreatedTs().split("/");
			int registrationYear = Integer.parseInt(out[0]);
			int noOfExcemtion = 1 + userDetailsTemp.getChildCount();
			////// System.out.println("hello old income profile
			////// "+obj.getOldIncomeProfile());
			int year = incomeJson.getJSONArray("income").getJSONObject(0).getInt("year");
			if (checkMeritalStatus.getMarital_status().equals("Yes") || (!incomeJson.isNull("spouse_income") == true)) {
				if (incomeJson.getJSONArray("spouse_income").length() > 0) {
					noOfExcemtion = noOfExcemtion + 1;
					if (userDetailsTemp.getFilingStatus().equals("Head of Household")) {
						noOfExcemtion = noOfExcemtion + userDetailsTemp.getDependants() - 1;
					} else if (userDetailsTemp.getFilingStatus().equals("Married Filing Separately")) {
						noOfExcemtion = noOfExcemtion - 1;
					} else if (userDetailsTemp.getFilingStatus().equals("Qualified Widow")) {
						noOfExcemtion = noOfExcemtion - 1;

					}
				}
			}
			JSONArray childArray = new JSONArray();
			JSONObject userDetailsFromDB = new JSONObject(jsonMapper.writeValueAsString(userDetailsTemp));
			for (int i = 0; i < incomeJSON.getJSONArray("assests").length(); i++) {
				int noOfExcemtion1 = noOfExcemtion;
				if (userDetailsTemp.getChildCount() != 0) {
					childArray = userDetailsFromDB.getJSONArray("childs");
					for (int k = 0; k < userDetailsTemp.getChildCount(); k++) {
						if (year - (registrationYear - childArray.getJSONObject(k).getInt("age")) > 18) {
							if ((childArray.getJSONObject(k).getString("flag").equals("Yes")
									&& year - (registrationYear - childArray.getJSONObject(k).getInt("age")) > 24)
									|| (!childArray.getJSONObject(k).getString("flag").equals("Yes"))) {
								noOfExcemtion1 = noOfExcemtion1 - 1;
							}
						}
					}
				}
				year++;
				JSONObject objFiling = new JSONObject();
				objFiling.put("fillingStatus", userDetailsTemp.getFilingStatus());
				objFiling.put("noOfExcemtion", noOfExcemtion1);
				objFiling.put("year", incomeJSON.getJSONArray("assests").getJSONObject(i).getInt("year"));
				oldfillingExemtion.put(objFiling);
			}
			for (int i = 0; i < incomeJSON.getJSONArray("assests").length(); i++) {

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

			JSONObject result = new JSONObject();
			// -------------------------------------------new code
			// (Mahi)--------------------------------------
			int SpouseStartYear = 0;
			int userStartYear = 0;
			int userReterementAge = 70;
			int spouseReterementAge = 70;
			double newUser401k = 0;
			double newSpouse401k = 0;
			double newUserIra = 0;
			double newSpouseIra = 0;
			double user401k = 18000;
			double spouse401k = 18000;
			userStartYear = userDetailsTemp.getBirthYear() + 70;
			if (userDetailsTemp.getMarital_status().equals("Yes")) {
				SpouseStartYear = userDetailsTemp.getSpouseBirthYear() + 70;
			} else if (dataFormServlet.getProfile_name().contains("marriage_income_profile")) {
				FinPlan finPlanDet = MongoDBConnection.finplancol.findOne("{usr_id:#,profile_name:#}",
						dataFormServlet.getUser_id(), dataFormServlet.getProfile_name()).as(FinPlan.class);
				SpouseStartYear = finPlanDet.getSpouseBirthYear() + 70;
			}
			System.out.println("before Calculated Expenses:: " + userExpense);
			if (checkMeritalStatus.getMarital_status().equals("Yes") || !incomeJson.isNull("spouse_income") == true) {
				if (userStartYear < SpouseStartYear) {
					System.out.println("user<sposue");
					for (int i = 0; i < user_income.length(); i++) {
						if (userIncomeDB.getJSONObject(i).getInt("year") < userStartYear) {
							double housing = userExpense.getJSONObject(i).getInt("housingExpense");
							double nonHousing = userExpense.getJSONObject(i).getInt("nonHousingExpense");
							double diff1 = user_income.getJSONObject(i).getDouble("value")
									- userIncomeDB.getJSONObject(i).getDouble("value");
							double diff2 = spouse_income.getJSONObject(i).getDouble("value")
									- spouseIncomeDB.getJSONObject(i).getDouble("value");
							double diff = diff1 + diff2;
							double newHousing = housing + (housingExpense * diff);
							double newNonHousing = nonHousing + (nonHousingExpense * diff);
							JSONObject expenseResponse = calculateExpenses(housing, nonHousing, newHousing,
									newNonHousing);
							userExpense.getJSONObject(i).put("totalExpense", expenseResponse.getDouble("totalExpense"));
							userExpense.getJSONObject(i).put("housingExpense",
									expenseResponse.getDouble("housingExpense"));
							userExpense.getJSONObject(i).put("nonHousingExpense",
									expenseResponse.getDouble("nonHousingExpense"));
						}
					}
				} else {
					System.out.println("else ...usersposue" + SpouseStartYear);
					for (int i = 0; i < user_income.length(); i++) {
						if (userIncomeDB.getJSONObject(i).getInt("year") < SpouseStartYear) {
							double housing = userExpense.getJSONObject(i).getInt("housingExpense");
							double nonHousing = userExpense.getJSONObject(i).getInt("nonHousingExpense");
							double diff1 = user_income.getJSONObject(i).getDouble("value")
									- userIncomeDB.getJSONObject(i).getDouble("value");
							double diff2 = spouse_income.getJSONObject(i).getDouble("value")
									- spouseIncomeDB.getJSONObject(i).getDouble("value");
							double diff = diff1 + diff2;
							double newHousing = housing + (housingExpense * diff);
							double newNonHousing = nonHousing + (nonHousingExpense * diff);
							JSONObject expenseResponse = calculateExpenses(housing, nonHousing, newHousing,
									newNonHousing);
							userExpense.getJSONObject(i).put("totalExpense", expenseResponse.getDouble("totalExpense"));
							userExpense.getJSONObject(i).put("housingExpense",
									expenseResponse.getDouble("housingExpense"));
							userExpense.getJSONObject(i).put("nonHousingExpense",
									expenseResponse.getDouble("nonHousingExpense"));
						}
					}
				}

			} else {
				for (int i = 0; i < user_income.length(); i++) {
					if (userIncomeDB.getJSONObject(i).getInt("year") < userStartYear) {
						double housing = userExpense.getJSONObject(i).getInt("housingExpense");
						double nonHousing = userExpense.getJSONObject(i).getInt("nonHousingExpense");
						double diff = user_income.getJSONObject(i).getDouble("value")
								- userIncomeDB.getJSONObject(i).getDouble("value");
						double newHousing = housing + (housingExpense * diff);
						double newNonHousing = nonHousing + (nonHousingExpense * diff);
						JSONObject expenseResponse = calculateExpenses(housing, nonHousing, newHousing, newNonHousing);
						userExpense.getJSONObject(i).put("totalExpense", expenseResponse.getDouble("totalExpense"));
						userExpense.getJSONObject(i).put("housingExpense", expenseResponse.getDouble("housingExpense"));
						userExpense.getJSONObject(i).put("nonHousingExpense",
								expenseResponse.getDouble("nonHousingExpense"));
					}
				}
			}

			System.out.println("Calculated Expenses:: " + userExpense);
			RetirementGoalServiceImpl goalservice = new RetirementGoalServiceImpl();
			double userAIME = goalservice.findUserAIME(userReterementAge, user_income, userStartYear);
			//// System.out.println("mahi AIME ;"+userAIME);
			int userFRA = goalservice.findUserFRA(userReterementAge, userDetailsTemp.getBirthYear());
			JSONObject userJsonSSB = new JSONObject();
			JSONObject spouseJson = new JSONObject();
			if (userDetailsTemp.getMarital_status().equals("Yes")
					|| dataFormServlet.getProfile_name().contains("marriage_income_profile") || !incomeJson.isNull("spouse_income") == true) {
				double spouseAIME = goalservice.findUserAIME(spouseReterementAge, spouse_income, SpouseStartYear);
				userJsonSSB = goalservice.calculateUserSSB(
						spouse_income.getJSONObject(spouse_income.length() / 2).getDouble("value"), "Yes", userFRA, 0,
						856, 5157, userAIME, spouseAIME);

				spouseJson.put("firetBendPoint", 856);
				spouseJson.put("spouseRetirementAge", spouseReterementAge);
				spouseJson.put("userRetirementAge", userReterementAge);
				spouseJson.put("useFfra", userFRA);
				spouseJson.put("spouseFfra",
						goalservice.findUserFRA(spouseReterementAge, userDetailsTemp.getSpouseBirthYear()));
				spouseJson.put("userAime", userAIME);
				spouseJson.put("spouseAime", spouseAIME);
				spouseJson.put("secondBendPoint", 5157);
			} else {
				userJsonSSB = goalservice.calculateUserSSB(0, "No", userFRA, 0, 856, 5157, userAIME, 0);

				spouseJson.put("firetBendPoint", 856);
				/*
				 * spouseJson.put("spouseRetirementAge", spouseReterementAge);
				 */
				spouseJson.put("userRetirementAge", userReterementAge);
				spouseJson.put("useFfra", userFRA);
				/*
				 * spouseJson.put("spouseFfra",
				 * goalservice.findUserFRA(spouseReterementAge,
				 * userDetailsTemp.getSpouseBirthYear()));
				 */
				spouseJson.put("userAime", userAIME);
				/*
				 * spouseJson.put("spouseAime",
				 * goalservice.findUserAIME(spouseReterementAge, spouse_income,
				 * SpouseStartYear));
				 */
				spouseJson.put("secondBendPoint", 5157);
			}

			int retirementAge = userReterementAge;
			JSONObject allIncomes = goalservice.insertRetirementIncomeNew(
					((Double) (userJsonSSB.get("User" + retirementAge))), spouseJson, user_id, user_income,
					spouse_income, userStartYear, SpouseStartYear, SpouseStartYear);
			user_income = allIncomes.getJSONArray("user_income");
			spouse_income = allIncomes.getJSONArray("spouse_income");
			combined_income = allIncomes.getJSONArray("combined_income");
			System.out.println("Before user_income :: " + user_income);
			user401k = 18000;
			spouse401k = 18000;
			if (userPlot.length() > 3) {
				retPlot = userPlot.getJSONObject(userPlot.length() - 2);
				lastPlot = userPlot.getJSONObject(userPlot.length() - 3);
				for (int u = 0; u < user_income.length(); u++) {
					if (user_income.getJSONObject(u).getInt("year") == retPlot.getInt("year")) {
						retPlotIndex = u;
					}
					if (user_income.getJSONObject(u).getInt("year") == lastPlot.getInt("year")) {
						lastPlotIndex = u;
					}
				}

				double interpolateFinal = (user_income.getJSONObject(retPlotIndex).getDouble("value")
						- user_income.getJSONObject(lastPlotIndex).getDouble("value"))
						/ (retPlot.getInt("year") - lastPlot.getInt("year"));

				/* if ( lastPlotIndex != 0 ) { */
				for (int u = lastPlotIndex + 1; u < retPlotIndex; u++) {
					double newUserIncomeValue = user_income.getJSONObject(u - 1).getDouble("value") + interpolateFinal;
					user_income.getJSONObject(u).put("value", newUserIncomeValue);
				}
				/*
				 * } else { for ( int u = lastPlotIndex + 1; u < retPlotIndex;
				 * u++ ) { double newUserIncomeValue =
				 * user_income.getJSONObject(u-1).getDouble("value") +
				 * interpolateFinal; user_income.getJSONObject(u).put("value",
				 * newUserIncomeValue); } }
				 */
			}
			System.out.println("After user_income :: " + user_income);
			System.out.println("Before spouse_income :: " + spouse_income);
			if (spousePlot.length() > 3) {
				retPlot = spousePlot.getJSONObject(spousePlot.length() - 2);
				lastPlot = spousePlot.getJSONObject(spousePlot.length() - 3);
				retPlotIndex = 0;
				lastPlotIndex = 0;
				for (int s = 0; s < spouse_income.length(); s++) {
					if (spouse_income.getJSONObject(s).getInt("year") == retPlot.getInt("year")) {
						retPlotIndex = s;
					}
					if (spouse_income.getJSONObject(s).getInt("year") == lastPlot.getInt("year")) {
						lastPlotIndex = s;
					}
				}
				double interpolateFinal = (spouse_income.getJSONObject(retPlotIndex).getDouble("value")
						- spouse_income.getJSONObject(lastPlotIndex).getDouble("value"))
						/ (retPlot.getInt("year") - lastPlot.getInt("year"));

				/* if ( lastPlotIndex != 0 ) { */
				for (int s = lastPlotIndex + 1; s < retPlotIndex; s++) {
					double newIncomeValue = spouse_income.getJSONObject(s - 1).getDouble("value") + interpolateFinal;
					spouse_income.getJSONObject(s).put("value", newIncomeValue);
				}
				/*
				 * } else { for ( int s = lastPlotIndex + 1; s < retPlotIndex;
				 * s++ ) { double newIncomeValue =
				 * spouse_income.getJSONObject(s-1).getDouble("value") +
				 * interpolateFinal; spouse_income.getJSONObject(s).put("value",
				 * newIncomeValue); } }
				 */
			}
			System.out.println("After spouse_income :: " + spouse_income);
			// -----------------------------------------------------------------------------------------

			int ageAtRegistration = userDetailsTemp.getAge();
			int spouseAgeForLimits = userDetailsTemp.getSpouseAge();
			JSONArray impLimits = incomeJSON.getJSONArray("limits");
			for (int i = 0; i < impLimits.length(); i++) {
				if (checkMeritalStatus.getMarital_status().equals("Yes")) {
					JSONObject newLimits = CalculationEngine.limiteAfterRetirement(user401k, spouse401k, 0, 0, 0, 0,
							ageAtRegistration, user_income.getJSONObject(i).getDouble("value"),
							spouse_income.getJSONObject(i).getDouble("value"),
							oldfillingExemtion.getJSONObject(i).getString("fillingStatus"), spouseAgeForLimits,
							impLimits.getJSONObject(i), (user_income.getJSONObject(i).getDouble("value")
									+ spouse_income.getJSONObject(i).getDouble("value")),
							i, null);

					impLimits.put(i, newLimits);
					spouseAgeForLimits++;
				} else {
					JSONObject newLimits = CalculationEngine.limiteAfterRetirement(user401k, 0, 0, 0, 0, 0,
							ageAtRegistration, user_income.getJSONObject(i).getDouble("value"), 0,
							oldfillingExemtion.getJSONObject(i).getString("fillingStatus"), 0,
							impLimits.getJSONObject(i), (user_income.getJSONObject(i).getDouble("value") + 0), i, null);

					impLimits.put(i, newLimits);
					spouseAgeForLimits++;

				}
			}

			result = CalculationEngine.sweepingOfMoney(user_income, null, combined_income, spouse_income, userExpense,
					impLimits, userDetailsTemp.getMarital_status(), incomeJSON.getJSONArray("assests"),
					incomeJSON.getJSONArray("tax"), user_id, oldfillingExemtion, userDetailsTemp.getAge(),
					userDetailsTemp.getSpouseAge(), 0, false, null, null, null, null, null, null, null, null, false);
			if (result.getString("status").equals("success")) {
				taxIncmProfile = result.getJSONArray("tax");
				assetsIncmProfile = result.getJSONArray("assets");
				JSONArray pln = new JSONArray();
				int i = 0;
				MongoCursor<FinPlan> cursor = MongoDBConnection.finplancol.find("{usr_id:#,profile_name:#}",
						dataFormServlet.getUser_id(), dataFormServlet.getProfile_name()).as(FinPlan.class);
				JSONArray incomeProfilesName = new JSONArray();
				while (cursor.hasNext()) {
					i = i + 1;
					FinPlan fetch = cursor.next();
					pln.put(fetch.getPlan_name());
					if (fetch.getProfile_name() != null) {
						incomeProfilesName.put(fetch.getProfile_name());
					}
					incomeProfilesName.put(fetch.getProfile_name());
				}
				cursor.close();
				count = 0;
				for (int k = 0; k < pln.length(); k++) {
					if (incomeProfilesName.getString(k).equals(dataFormServlet.getProfile_name())) {
						// System.out.println("plan name"+pln.getString(k));
						FinPlan finPlanAssests = MongoDBConnection.finplancol
								.findOne("{usr_id:#,plan_name:#}", dataFormServlet.getUser_id(), pln.getString(k))
								.as(FinPlan.class);
						User Details = MongoDBConnection.userColl.findOne("{_id:#}", dataFormServlet.getUser_id())
								.as(User.class);
						JSONObject finAssestsJSON = new JSONObject(jsonMapper.writeValueAsString(finPlanAssests));
						JSONArray userExpenseFin = finAssestsJSON.getJSONArray("userExpense");
						JSONArray expenseObj = new JSONArray();
						JSONObject finPlanExpense = finAssestsJSON.getJSONObject("expense");
						if (!finPlanExpense.isNull("housing_expense")) {
							expenseObj = finPlanExpense.getJSONArray("housing_expense");
						}
						JSONArray goals = finAssestsJSON.getJSONArray("goals");
						RetirementGoal retirementObj = null;
						for (int p = 0; p < goals.length(); p++) {
							RetirementGoal goalDetails = MongoDBConnection.goalcoll
									.findOne("{_id:#}", goals.getString(p)).as(RetirementGoal.class);
							if (goalDetails.getGoalType().equals("Retirement")) {
								retirementObj = goalDetails;
							}
						}
						/*
						 * JSONObject j=new JSONObject(); j=null;
						 * if(finPlanExpense.isNull("housing_expense")==true) {
						 * expenseObj.put("housing_expense", j); } else {
						 * expenseObj.put("housing_expense",
						 * finPlanExpense.getJSONObject("housing_expense")); }
						 */
						int UserSelectedSpouseYear = 0;
						if (retirementObj != null) {
							userReterementAge = retirementObj.getRetirementAge();
							userStartYear = Details.getBirthYear() + retirementObj.getRetirementAge();
							newUser401k = retirementObj.getUserContributeAmount();
							newSpouse401k = retirementObj.getSpouseContributeAmount();
							newUserIra = retirementObj.getUserContributionInIRA();
							UserSelectedSpouseYear = retirementObj.getUserSelectedSpouseYear();
							newSpouseIra = retirementObj.getSpouseContributionInIRA();
							if (finPlanAssests.getMarital_status().equals("Yes")
									&& Details.getMarital_status().equals("No")) {
								SpouseStartYear = finPlanAssests.getSpouseBirthYear()
										+ retirementObj.getSpouseRetirementAge();
								spouseReterementAge = retirementObj.getSpouseRetirementAge();
							} else if (Details.getMarital_status().equals("Yes")) {
								SpouseStartYear = Details.getSpouseBirthYear() + retirementObj.getSpouseRetirementAge();
								spouseReterementAge = retirementObj.getSpouseRetirementAge();
							}

						} else {
							userStartYear = Details.getBirthYear() + 70;
							if (finPlanAssests.getMarital_status().equals("Yes")
									&& Details.getMarital_status().equals("No")) {
								SpouseStartYear = finPlanAssests.getSpouseBirthYear() + 70;
							} else if (Details.getMarital_status().equals("Yes")) {
								SpouseStartYear = Details.getSpouseBirthYear() + 70;
							}
							UserSelectedSpouseYear = SpouseStartYear;
						}

						JSONObject retirementData = new JSONObject();
						retirementData.put("spouseStartYear", SpouseStartYear);
						retirementData.put("userStartYear", userStartYear);
						String emergencyType = null;
						String monthsOfIncome = null;
						String monthsOfExpense = null;
						if (finPlanAssests.isEmergencyFundFlag() == true) {
							Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
									.findOne("{fin_id:#,goalType:#}", finPlanAssests.get_id(), "Emergency Fund")
									.as(Emergencyfundmodel.class);
							emergencyType = emergencyObj.getTimePeriod();
							monthsOfIncome = emergencyObj.getMonthI();
							monthsOfExpense = emergencyObj.getMonthE();
						}
						// --------------------------------converting income
						// array to retirement income + normal income
						// -------------------------------------------//
						goalservice = new RetirementGoalServiceImpl();
						userAIME = goalservice.findUserAIME(userReterementAge, user_income, userStartYear);
						//// System.out.println("mahi AIME ;"+userAIME);
						userFRA = goalservice.findUserFRA(userReterementAge, Details.getBirthYear());
						userJsonSSB = new JSONObject();
						spouseJson = new JSONObject();
						if (finPlanAssests.getMarital_status().equals("Yes")) {
							double spouseAIME = goalservice.findUserAIME(spouseReterementAge, spouse_income,
									SpouseStartYear);
							userJsonSSB = goalservice.calculateUserSSB(
									spouse_income.getJSONObject(spouse_income.length() / 2).getDouble("value"), "Yes",
									userFRA, 0, 856, 5157, userAIME, spouseAIME);

							spouseJson.put("firetBendPoint", 856);
							spouseJson.put("spouseRetirementAge", spouseReterementAge);
							spouseJson.put("userRetirementAge", userReterementAge);
							spouseJson.put("useFfra", userFRA);
							spouseJson.put("spouseFfra",
									goalservice.findUserFRA(spouseReterementAge, finPlanAssests.getSpouseBirthYear()));
							spouseJson.put("userAime", userAIME);
							spouseJson.put("spouseAime", spouseAIME);
							spouseJson.put("secondBendPoint", 5157);
						} else {
							userJsonSSB = goalservice.calculateUserSSB(0, "No", userFRA, 0, 856, 5157, userAIME, 0);

							spouseJson.put("firetBendPoint", 856);
							/*
							 * spouseJson.put("spouseRetirementAge",
							 * spouseReterementAge);
							 */
							spouseJson.put("userRetirementAge", userReterementAge);
							spouseJson.put("useFfra", userFRA);
							/*
							 * spouseJson.put("spouseFfra",
							 * goalservice.findUserFRA(spouseReterementAge,
							 * Details.getSpouseBirthYear()));
							 */
							spouseJson.put("userAime", userAIME);
							/*
							 * spouseJson.put("spouseAime",
							 * goalservice.findUserAIME(spouseReterementAge,
							 * spouse_income, SpouseStartYear));
							 */
							spouseJson.put("secondBendPoint", 5157);
						}

						retirementAge = userReterementAge;
						allIncomes = goalservice.insertRetirementIncomeNew(
								((Double) (userJsonSSB.get("User" + retirementAge))), spouseJson, user_id, user_income,
								spouse_income, userStartYear, SpouseStartYear, UserSelectedSpouseYear);
						user_income = allIncomes.getJSONArray("user_income");
						spouse_income = allIncomes.getJSONArray("spouse_income");
						combined_income = allIncomes.getJSONArray("combined_income");
						System.out.println("Before finplan user_income :: " + user_income);
						if (userPlot.length() > 3) {
							retPlot = userPlot.getJSONObject(userPlot.length() - 2);
							lastPlot = userPlot.getJSONObject(userPlot.length() - 3);
							for (int u = 0; u < user_income.length(); u++) {
								if (user_income.getJSONObject(u).getInt("year") == retPlot.getInt("year")) {
									retPlotIndex = u;
								}
								if (user_income.getJSONObject(u).getInt("year") == lastPlot.getInt("year")) {
									lastPlotIndex = u;
								}
							}
							double interpolateFinal = (user_income.getJSONObject(retPlotIndex).getDouble("value")
									- user_income.getJSONObject(lastPlotIndex).getDouble("value"))
									/ (retPlot.getInt("year") - lastPlot.getInt("year"));

							/* if ( lastPlotIndex != 0 ) { */
							for (int u = lastPlotIndex + 1; u < retPlotIndex; u++) {
								double newUserIncomeValue = user_income.getJSONObject(u - 1).getDouble("value")
										+ interpolateFinal;
								user_income.getJSONObject(u).put("value", newUserIncomeValue);
							}
							/*
							 * } else { for ( int u = lastPlotIndex + 1; u <
							 * retPlotIndex; u++ ) { double newUserIncomeValue =
							 * user_income.getJSONObject(u-1).getDouble("value")
							 * + interpolateFinal;
							 * user_income.getJSONObject(u).put("value",
							 * newUserIncomeValue); } }
							 */
						}

						if (checkMeritalStatus.getMarital_status().equals("Yes")
								|| !incomeJson.isNull("spouse_income") == true) {
							System.out.println("Before finplan spouse_income :: " + spouse_income);
							if (spousePlot.length() > 3) {
								retPlot = spousePlot.getJSONObject(spousePlot.length() - 2);
								lastPlot = spousePlot.getJSONObject(spousePlot.length() - 3);
								retPlotIndex = 0;
								lastPlotIndex = 0;
								for (int s = 0; s < spouse_income.length(); s++) {
									if (spouse_income.getJSONObject(s).getInt("year") == retPlot.getInt("year")) {
										retPlotIndex = s;
									}
									if (spouse_income.getJSONObject(s).getInt("year") == lastPlot.getInt("year")) {
										lastPlotIndex = s;
									}
								}
								double interpolateFinal = (spouse_income.getJSONObject(retPlotIndex).getDouble("value")
										- spouse_income.getJSONObject(lastPlotIndex).getDouble("value"))
										/ (retPlot.getInt("year") - lastPlot.getInt("year"));

								/* if ( lastPlotIndex != 0 ) { */
								for (int s = lastPlotIndex + 1; s < retPlotIndex; s++) {
									double newIncomeValue = spouse_income.getJSONObject(s - 1).getDouble("value")
											+ interpolateFinal;
									spouse_income.getJSONObject(s).put("value", newIncomeValue);
								}
								/*
								 * } else { for ( int s = lastPlotIndex + 1; s <
								 * retPlotIndex; s++ ) { double newIncomeValue =
								 * spouse_income.getJSONObject(s-1).getDouble(
								 * "value") + interpolateFinal;
								 * spouse_income.getJSONObject(s).put("value",
								 * newIncomeValue); } }
								 */
							}
							System.out.println("After finplan spouse_income :: " + spouse_income);
							if (userStartYear < SpouseStartYear) {
								System.out.println("In if");
								for (int u = 0; u < user_income.length(); u++) {
									if (userIncomeDB.getJSONObject(u).getInt("year") < userStartYear) {
										double housing = userExpenseFin.getJSONObject(u).getInt("housingExpense");
										double nonHousing = userExpenseFin.getJSONObject(u).getInt("nonHousingExpense");
										/*
										 * System.out.println("Year :: "
										 * +userIncomeDB.getJSONObject(u).getInt
										 * ("year")); System.out.println(
										 * "New income :: "
										 * +user_income.getJSONObject(u).
										 * getDouble("value"));
										 * System.out.println("Old income :: "
										 * +userIncomeDB.getJSONObject(u).
										 * getDouble("value"));
										 * System.out.println(
										 * "New income Spouse :: "
										 * +spouse_income.getJSONObject(u).
										 * getDouble("value"));
										 * System.out.println(
										 * "Old income Spouse :: "
										 * +spouseIncomeDB.getJSONObject(u).
										 * getDouble("value"));
										 */
										double diff1 = user_income.getJSONObject(u).getDouble("value")
												- userIncomeDB.getJSONObject(u).getDouble("value");
										double diff2 = spouse_income.getJSONObject(u).getDouble("value")
												- spouseIncomeDB.getJSONObject(u).getDouble("value");
										double diff = diff1 + diff2;
										// System.out.println("diff :: "+diff);
										double newHousing = housing + (housingExpense * diff);
										double newNonHousing = nonHousing + (nonHousingExpense * diff);
										JSONObject expenseResponse = calculateExpenses(housing, nonHousing, newHousing,
												newNonHousing);
										userExpenseFin.getJSONObject(u).put("totalExpense",
												expenseResponse.getDouble("totalExpense"));
										userExpenseFin.getJSONObject(u).put("housingExpense",
												expenseResponse.getDouble("housingExpense"));
										userExpenseFin.getJSONObject(u).put("nonHousingExpense",
												expenseResponse.getDouble("nonHousingExpense"));
									}
								}
							} else {
								for (int u = 0; u < user_income.length(); u++) {
									if (userIncomeDB.getJSONObject(u).getInt("year") < SpouseStartYear) {
										double housing = userExpenseFin.getJSONObject(u).getInt("housingExpense");
										double nonHousing = userExpenseFin.getJSONObject(u).getInt("nonHousingExpense");
										/*
										 * System.out.println("Year :: "
										 * +userIncomeDB.getJSONObject(u).getInt
										 * ("year")); System.out.println(
										 * "New income :: "
										 * +user_income.getJSONObject(u).
										 * getDouble("value"));
										 * System.out.println("Old income :: "
										 * +userIncomeDB.getJSONObject(u).
										 * getDouble("value"));
										 * System.out.println(
										 * "New income Spouse :: "
										 * +spouse_income.getJSONObject(u).
										 * getDouble("value"));
										 * System.out.println(
										 * "Old income Spouse :: "
										 * +spouseIncomeDB.getJSONObject(u).
										 * getDouble("value"));
										 */
										double diff1 = user_income.getJSONObject(u).getDouble("value")
												- userIncomeDB.getJSONObject(u).getDouble("value");
										double diff2 = spouse_income.getJSONObject(u).getDouble("value")
												- spouseIncomeDB.getJSONObject(u).getDouble("value");
										double diff = diff1 + diff2;
										// System.out.println("diff in else::
										// "+diff);
										double newHousing = housing + (housingExpense * diff);
										double newNonHousing = nonHousing + (nonHousingExpense * diff);
										JSONObject expenseResponse = calculateExpenses(housing, nonHousing, newHousing,
												newNonHousing);
										userExpenseFin.getJSONObject(u).put("totalExpense",
												expenseResponse.getDouble("totalExpense"));
										userExpenseFin.getJSONObject(u).put("housingExpense",
												expenseResponse.getDouble("housingExpense"));
										userExpenseFin.getJSONObject(u).put("nonHousingExpense",
												expenseResponse.getDouble("nonHousingExpense"));
									}
								}
							}

						} else {
							for (int u = 0; u < user_income.length(); u++) {
								if (userIncomeDB.getJSONObject(u).getInt("year") < userStartYear) {
									double housing = userExpenseFin.getJSONObject(u).getInt("housingExpense");
									double nonHousing = userExpenseFin.getJSONObject(u).getInt("nonHousingExpense");
									double diff = user_income.getJSONObject(u).getDouble("value")
											- userIncomeDB.getJSONObject(u).getDouble("value");
									double newHousing = housing + (housingExpense * diff);
									double newNonHousing = nonHousing + (nonHousingExpense * diff);
									JSONObject expenseResponse = calculateExpenses(housing, nonHousing, newHousing,
											newNonHousing);
									userExpenseFin.getJSONObject(u).put("totalExpense",
											expenseResponse.getDouble("totalExpense"));
									userExpenseFin.getJSONObject(u).put("housingExpense",
											expenseResponse.getDouble("housingExpense"));
									userExpenseFin.getJSONObject(u).put("nonHousingExpense",
											expenseResponse.getDouble("nonHousingExpense"));
								}
							}
						}
						System.out.println("Calculated Expenses:: " + userExpenseFin);
						user401k = 18000;
						spouse401k = 18000;
						ageAtRegistration = Details.getAge();
						spouseAgeForLimits = finPlanAssests.getSpouseAge();
						JSONArray finLimits = finAssestsJSON.getJSONArray("limits");
						for (i = 0; i < finLimits.length(); i++) {
							if (finPlanAssests.getMarital_status().equals("Yes")) {
								JSONObject newLimits = CalculationEngine.limiteAfterRetirement(user401k, spouse401k,
										newUser401k, newSpouse401k, newUserIra, newSpouseIra, ageAtRegistration,
										user_income.getJSONObject(i).getDouble("value"),
										spouse_income.getJSONObject(i).getDouble("value"),
										finAssestsJSON.getJSONArray("fillingExemtion").getJSONObject(i)
												.getString("fillingStatus"),
										spouseAgeForLimits, finLimits.getJSONObject(i),
										(user_income.getJSONObject(i).getDouble("value")
												+ spouse_income.getJSONObject(i).getDouble("value")),
										i, null);

								finLimits.put(i, newLimits);
								spouseAgeForLimits++;
							} else {
								JSONObject newLimits = CalculationEngine.limiteAfterRetirement(user401k, 0, newUser401k,
										newSpouse401k, newUserIra, newSpouseIra, ageAtRegistration,
										user_income.getJSONObject(i).getDouble("value"), 0,
										finAssestsJSON.getJSONArray("fillingExemtion").getJSONObject(i)
												.getString("fillingStatus"),
										0, finLimits.getJSONObject(i),
										(user_income.getJSONObject(i).getDouble("value") + 0), i, null);

								finLimits.put(i, newLimits);
								spouseAgeForLimits++;

							}
						}
						// System.out.println("Expense=="+finAssestsJSON.getJSONArray("userExpense"));
						result = CalculationEngine.sweepingOfMoney(user_income, finPlanAssests.get_id(),
								combined_income, spouse_income, userExpenseFin, finLimits,
								finPlanAssests.getMarital_status(), finAssestsJSON.getJSONArray("assests"),
								finAssestsJSON.getJSONArray("tax"), dataFormServlet.getUser_id(),
								finAssestsJSON.getJSONArray("fillingExemtion"), Details.getAge(),
								finPlanAssests.getSpouseAge(), finPlanAssests.getEmergencyFundAmt(),
								finPlanAssests.isEmergencyFundFlag(), finAssestsJSON.getJSONArray("deductions"),
								finAssestsJSON.getJSONArray("kidBirthYear"), retirementData, retirementObj, expenseObj,
								emergencyType, monthsOfIncome, monthsOfExpense, finPlanAssests.isRetirementFlag());
						String status = result.getString("status");
						if (status.equals("success")) {
							//// System.out.println("No Negative value
							//// success");
							JSONArray assetsFinPlan = result.getJSONArray("assets");
							JSONArray taxFinPLan = result.getJSONArray("tax");
							MongoDBConnection.finplancol
									.update("{usr_id:'" + dataFormServlet.getUser_id() + "',plan_name:'"
											+ pln.getString(k) + "'}")
									.upsert().multi().with("{$set: {'userExpense' : " + userExpenseFin + " ,'assests':"
											+ assetsFinPlan + ",'tax':" + taxFinPLan + ",'limits':" + finLimits + "}}");

							count++;
						} else {
							flag = 1;
						}

					}
				}
			} else {
				flag = 1;
			}

			if (flag == 0) {
				userPlot.getJSONObject(userPlot.length() - 1).put("userIncome",
						user_income.getJSONObject(user_income.length() - 1).getDouble("retirement_income"));
				for (int user = 0; user < user_income.length(); user++) {
					if (user_income.getJSONObject(user).getDouble("retirement_income") > 0) {
						userPlot.getJSONObject(userPlot.length() - 2).put("userIncome",
								user_income.getJSONObject(user).getDouble("retirement_income"));
						break;
					}
				}
				if (spouse_income.length() > 0) {
					spousePlot.getJSONObject(spousePlot.length() - 1).put("spouseIncome",
							spouse_income.getJSONObject(spouse_income.length() - 1).getDouble("retirement_income"));
					for (int user = 0; user < spouse_income.length(); user++) {
						if (spouse_income.getJSONObject(user).getDouble("retirement_income") > 0) {
							spousePlot.getJSONObject(spousePlot.length() - 2).put("spouseIncome",
									spouse_income.getJSONObject(user).getDouble("retirement_income"));
							break;
						}
					}
				}

				MongoDBConnection.incomeProfileCol
						.update("{'user_id': '" + dataFormServlet.getUser_id() + "','profile_name':'"
								+ dataFormServlet.getProfile_name() + "'}")
						.upsert().multi()
						.with("{$set: {'userExpense' : " + userExpense + ",'user_income':" + user_income
								+ ",'spouse_income':" + spouse_income + ",'combined_income':" + combined_income
								+ ",'tax':" + taxIncmProfile + ",'assests':" + assetsIncmProfile + ",'modifiedTs':'"
								+ dateFormat.format(date) + "','limits':" + impLimits + ",'userPlot':" + userPlot
								+ ",'spousePlot':" + spousePlot + "}}");
				responseToRestController.put("status", "success");
			} else {

				if (count > 0) {
					JSONArray pln = new JSONArray();
					int i = 0;
					MongoCursor<FinPlan> cursor = MongoDBConnection.finplancol
							.find("{usr_id:#}", dataFormServlet.getUser_id()).as(FinPlan.class);
					JSONArray incomeProfilesName = new JSONArray();
					while (cursor.hasNext()) {
						i = i + 1;
						FinPlan fetch = cursor.next();
						pln.put(fetch.getPlan_name());
						if (fetch.getProfile_name() != null) {
							incomeProfilesName.put(fetch.getProfile_name());
						}
						incomeProfilesName.put(fetch.getProfile_name());
					}
					cursor.close();
					for (int k = 0; k < pln.length(); k++) {
						if (incomeProfilesName.getString(k).equals(dataFormServlet.getProfile_name())) {
							FinPlan finPlanAssests = MongoDBConnection.finplancol
									.findOne("{usr_id:#,plan_name:#}", dataFormServlet.getUser_id(), pln.getString(k))
									.as(FinPlan.class);
							User Details = MongoDBConnection.userColl.findOne("{_id:#}", dataFormServlet.getUser_id())
									.as(User.class);
							JSONObject finAssestsJSON = new JSONObject(jsonMapper.writeValueAsString(finPlanAssests));
							IncomeProfile incomeObj = MongoDBConnection.incomeProfileCol
									.findOne("{user_id:#,profile_name:#}", dataFormServlet.getUser_id(),
											finPlanAssests.getProfile_name())
									.as(IncomeProfile.class);
							JSONObject incomeJSON1 = new JSONObject(jsonMapper.writeValueAsString(incomeObj));
							user_income = incomeJSON1.getJSONArray("user_income");
							JSONArray expenseObj = new JSONArray();
							JSONObject finPlanExpense = finAssestsJSON.getJSONObject("expense");
							if (!finPlanExpense.isNull("housing_expense")) {
								expenseObj = finPlanExpense.getJSONArray("housing_expense");
							}
							JSONArray goals = finAssestsJSON.getJSONArray("goals");
							RetirementGoal retirementObj = null;
							for (int p = 0; p < goals.length(); p++) {
								RetirementGoal goalDetails = MongoDBConnection.goalcoll
										.findOne("{_id:#}", goals.getString(i)).as(RetirementGoal.class);
								if (goalDetails.getGoalType().equals("Retirement")) {
									retirementObj = goalDetails;
								}
							}
							if (finPlanAssests.getMarital_status().equals("Yes")) {
								combined_income = incomeJSON1.getJSONArray("combined_income");
								spouse_income = incomeJSON1.getJSONArray("spouse_income");
							} else {
								combined_income = incomeJSON1.getJSONArray("user_income");
							}

							String emergencyType = null;
							String monthsOfIncome = null;
							String monthsOfExpense = null;
							if (finPlanAssests.isEmergencyFundFlag() == true) {
								Emergencyfundmodel emergencyObj = MongoDBConnection.goalcoll
										.findOne("{fin_id:#,goalType:#}", finPlanAssests.get_id(), "Emergency Fund")
										.as(Emergencyfundmodel.class);
								emergencyType = emergencyObj.getTimePeriod();
								monthsOfIncome = emergencyObj.getMonthI();
								monthsOfExpense = emergencyObj.getMonthE();
							}
							result = CalculationEngine.sweepingOfMoney(user_income, finPlanAssests.get_id(),
									combined_income, spouse_income, finAssestsJSON.getJSONArray("userExpense"),
									finAssestsJSON.getJSONArray("limits"), finPlanAssests.getMarital_status(),
									finAssestsJSON.getJSONArray("assests"), finAssestsJSON.getJSONArray("tax"),
									dataFormServlet.getUser_id(), finAssestsJSON.getJSONArray("fillingExemtion"),
									Details.getAge(), finPlanAssests.getSpouseAge(),
									finPlanAssests.getEmergencyFundAmt(), finPlanAssests.isEmergencyFundFlag(),
									finAssestsJSON.getJSONArray("deductions"),
									finAssestsJSON.getJSONArray("kidBirthYear"), null, retirementObj, expenseObj,
									emergencyType, monthsOfIncome, monthsOfExpense, finPlanAssests.isRetirementFlag());
							String status = result.getString("status");
							if (status.equals("success")) {
								//// System.out.println("No Negative value
								//// success");
								JSONArray assets = result.getJSONArray("assets");
								JSONArray tax = result.getJSONArray("tax");
								MongoDBConnection.finplancol
										.update("{usr_id:'" + dataFormServlet.getUser_id() + "',plan_name:'"
												+ pln.getString(k) + "'}")
										.upsert().multi().with("{$set: {'assests':" + assets + ",'tax':" + tax + "}}");
							}
						}
					}
				}
				responseToRestController.put("status", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return responseToRestController;
		}
		return responseToRestController;
	}

	private JSONObject calculateExpenses(double housingVal, double nonHousingVal, double newHousingVal,
			double newNonHousingVal) {
		JSONObject expenseCalculated = new JSONObject();
		try {
			double totExp = 0;
			if (newHousingVal < 0) {
				expenseCalculated.put("housingExpense", 0);
			} else {
				expenseCalculated.put("housingExpense", newHousingVal);
			}
			if (newNonHousingVal < 0) {
				expenseCalculated.put("nonHousingExpense", 0);
			} else {
				expenseCalculated.put("nonHousingExpense", newNonHousingVal);
			}
			if (newHousingVal < 0 && newNonHousingVal < 0) {
				if (housingVal > nonHousingVal) {
					totExp = housingVal - nonHousingVal;
				} else {
					totExp = nonHousingVal - housingVal;
				}
				expenseCalculated.put("totalExpense", totExp);
			} else if (newHousingVal < 0 && newNonHousingVal > 0) {
				expenseCalculated.put("totalExpense", newNonHousingVal);
			} else if (newHousingVal > 0 && newNonHousingVal < 0) {
				expenseCalculated.put("totalExpense", newHousingVal);
			} else {
				totExp = newHousingVal + newNonHousingVal;
				expenseCalculated.put("totalExpense", totExp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expenseCalculated;
	}

	@Override
	public JSONObject getIncomeProfile(IncomeProfile dataFormServlet) {
		JSONObject responseToRestController = new JSONObject();
		try {
			responseToRestController.put("status", "fail");
			User Details = MongoDBConnection.userColl.findOne("{_id:#}", dataFormServlet.getUser_id()).as(User.class);
			IncomeProfile incomeProfileCollData = MongoDBConnection.incomeProfileCol
					.findOne("{user_id:#,profile_name:#}", dataFormServlet.getUser_id(),
							dataFormServlet.getProfile_name())
					.as(IncomeProfile.class);
			responseToRestController = new JSONObject(jsonMapper.writeValueAsString(incomeProfileCollData));
			JSONArray user_income = responseToRestController.getJSONArray("user_income");
			JSONArray tax_incomeProfile = responseToRestController.getJSONArray("tax");
			JSONArray asset_incomeProfile = responseToRestController.getJSONArray("assests");
			JSONArray userPlotArray = responseToRestController.getJSONArray("userPlot");
			responseToRestController.put("user_income", user_income);
			responseToRestController.put("tax_incomeProfile", tax_incomeProfile);
			responseToRestController.put("asset_incomeProfile", asset_incomeProfile);
			responseToRestController.put("marital_status", "No");
			responseToRestController.put("status", "success");
			responseToRestController.put("userPlot", userPlotArray);
			responseToRestController.put("editExpenseFlag", incomeProfileCollData.getEditExpenseFlag());
			responseToRestController.put("housingExpense", incomeProfileCollData.getHousingExpense());
			responseToRestController.put("nonHousingExpense", incomeProfileCollData.getNonHousingExpense());
			if (Details.getMarital_status().equals("Yes")
					|| !responseToRestController.isNull("spouse_income") == true) {
				if (responseToRestController.getJSONArray("spouse_income").length() > 0) {
					JSONArray spouse_income = responseToRestController.getJSONArray("spouse_income");
					JSONArray combined_income = responseToRestController.getJSONArray("combined_income");
					JSONArray spousePlotArray = responseToRestController.getJSONArray("spousePlot");
					responseToRestController.put("spouse_income", spouse_income);
					responseToRestController.put("combined_income", combined_income);
					responseToRestController.put("marital_status", "Yes");
					responseToRestController.put("spousePlot", spousePlotArray);
				}
			}
			responseToRestController.put("status", "success");

		} catch (Exception e) {
			e.printStackTrace();

		}
		////// System.out.println("Bala ... rest...efrom
		////// incomeProfile"+responseToRestController);
		return responseToRestController;
	}

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
					combined_income.put(yearlyincome);
					i++;
				} else if (user_income.getJSONObject(i).getInt("year") == spouse_income.getJSONObject(j)
						.getInt("year")) {
					yearlyincome.put("year", spouse_income.getJSONObject(j).getInt("year"));
					yearlyincome.put("value", (user_income.getJSONObject(i).getDouble("value"))
							+ (spouse_income.getJSONObject(j).getDouble("value")));
					combined_income.put(yearlyincome);
					i++;
					j++;
				} else if (user_income.getJSONObject(i).getInt("year") > spouse_income.getJSONObject(j)
						.getInt("year")) {
					yearlyincome.put("year", spouse_income.getJSONObject(j).getInt("year"));
					yearlyincome.put("value", spouse_income.getJSONObject(j).getDouble("value"));
					combined_income.put(yearlyincome);
					j++;
				}
			}
			while (i < user_length) {
				JSONObject yearlyincome = new JSONObject();
				yearlyincome.put("year", user_income.getJSONObject(i).getInt("year"));
				yearlyincome.put("value", (user_income.getJSONObject(i).getDouble("value")));
				combined_income.put(yearlyincome);
				i++;
			}
			while (j < spouse_length) {
				JSONObject yearlyincome = new JSONObject();
				yearlyincome.put("year", spouse_income.getJSONObject(j).getInt("year"));
				yearlyincome.put("value", (spouse_income.getJSONObject(j).getDouble("value")));
				combined_income.put(yearlyincome);
				j++;
			}
			return combined_income;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
