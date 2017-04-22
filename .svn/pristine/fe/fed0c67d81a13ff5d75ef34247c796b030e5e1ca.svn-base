package com.mongorest.olahtek.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jongo.MongoCursor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.Reportmodel;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.User;

@Service("reportImpl")
@Transactional
public class Reportimpl {
 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
 Date date = new Date();
 ObjectMapper jsonMapper = new ObjectMapper();
 DecimalFormat decimalFloat = new DecimalFormat("#.##");

	@JsonCreator
	public JSONObject fetchingData(Reportmodel reportModel) throws JsonProcessingException, JSONException {
		JSONObject responseToRestController = new JSONObject();
		String user_id = reportModel.getUser_id();
		try {
			if (user_id == null) {

				return responseToRestController;
			} else {
				int i = 0;
				MongoCursor<FinPlan> cursor = MongoDBConnection.finplancol.find("{usr_id:#}", user_id).as(FinPlan.class);
				JSONArray pln = new JSONArray();
				JSONArray incomeProfiles = new JSONArray();
				if(cursor.count()>0)
				{
				while (cursor.hasNext()) {
					i = i + 1;
					FinPlan fetch = cursor.next();
					pln.put(fetch.getPlan_name());
					incomeProfiles.put(fetch.getProfile_name());

				}
				cursor.close();
				FinPlan finPlandetails = MongoDBConnection.finplancol.findOne("{plan_name:#,usr_id:#}", pln.getString(0), reportModel.getUser_id()).as(
						FinPlan.class);
				User Details1 = MongoDBConnection.userColl.findOne("{_id:#}", reportModel.getUser_id()).as(User.class);
				JSONObject finJson = new JSONObject(jsonMapper.writeValueAsString(finPlandetails));
				JSONObject userDetails = new JSONObject(jsonMapper.writeValueAsString(Details1));
				JSONArray expenseBeforeGoal = finJson.getJSONArray("userExpense");
				JSONArray totalXpense = new JSONArray();
				for (int k = 0; k < expenseBeforeGoal.length(); k++) {
					JSONObject userExpJson = new JSONObject();
					userExpJson.put("value", expenseBeforeGoal.getJSONObject(k).getDouble("totalExpense"));
					userExpJson.put("year", expenseBeforeGoal.getJSONObject(k).getInt("year"));
					totalXpense.put(userExpJson);
				}
				IncomeProfile incomeProfile = new IncomeProfile();
				incomeProfile.setUser_id(user_id);

				IncomeProfileImpl incomeProfileImpl = new IncomeProfileImpl();
				incomeProfileImpl.getIncomeProfile(incomeProfile);

				MongoCursor<IncomeProfile> cursor1 = MongoDBConnection.incomeProfileCol.find("{user_id:#}", user_id)
						.as(IncomeProfile.class);
				////System.out.println("aparna userdetails==="+new JSONObject(jsonMapper.writeValueAsString(Details)));

				JSONArray incomeProfilesForReport = new JSONArray();
				while (cursor1.hasNext()) {
					//System.out.println("inside cursor======");
					IncomeProfile fetch = cursor1.next();
					incomeProfilesForReport.put(fetch.getProfile_name());
				}
				cursor1.close();
				//System.out.println("incomeProfilesForReport=="+incomeProfilesForReport);
				responseToRestController.put("incomeProfilesForReport", incomeProfilesForReport);
				responseToRestController.put("expenseBeforeGoal", expenseBeforeGoal);

				responseToRestController.put("equity",finJson.getJSONArray("equity"));
				responseToRestController.put("userDetails",userDetails);
				responseToRestController.put("Plans", pln);
				responseToRestController.put("status", "success");
				}
				else {
				    responseToRestController.put("status", "success");
				    responseToRestController.put("Plans", 0);
				}
				return responseToRestController;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return responseToRestController;
		}
	}

	public JSONObject fetchingGoalData(Reportmodel reportModel) {
		String planName = reportModel.getPlanName();

		JSONObject responseToRestController = new JSONObject();
		try {
			System.out.println("Bala*&*^");
			FinPlan expenseDetail = MongoDBConnection.finplancol.findOne("{plan_name:#,usr_id:#}", planName, reportModel.getUser_id()).as(FinPlan.class);
			User Details1 = MongoDBConnection.userColl.findOne("{_id:#}", reportModel.getUser_id()).as(User.class);
			if(expenseDetail!=null)
			responseToRestController.put("marital_status", expenseDetail.getMarital_status());
			else
			responseToRestController.put("marital_status", Details1.getMarital_status());

			responseToRestController.put("rentalExpense", Details1.getRentalExpense());
			responseToRestController.put("monthlyExpense", Details1.getMonthlyExpenses());
			IncomeProfile incomeProfile = new IncomeProfile();
			incomeProfile.setUser_id(reportModel.getUser_id());
			incomeProfile.setProfile_name((expenseDetail.getProfile_name()));
			IncomeProfileImpl incomeProfileImpl = new IncomeProfileImpl();
			JSONObject jobject = incomeProfileImpl.getIncomeProfile(incomeProfile);
			responseToRestController.put("incomedetails", jobject);
			responseToRestController.put("status", "success");
			JSONArray goalIDarray = new JSONArray();
			JSONArray goalarray = new JSONArray();
			int retirementAge=0;

			FinPlan Details = MongoDBConnection.finplancol.findOne("{plan_name:#,usr_id:#}", reportModel.getPlanName(), reportModel.getUser_id()).as(
					FinPlan.class);
			JSONObject finJsonaftergoal = new JSONObject(jsonMapper.writeValueAsString(Details));
			JSONArray expenseBeforeGoal = finJsonaftergoal.getJSONArray("userExpense");
			if (Details.getGoals() == null || (Details.getGoals() != null && finJsonaftergoal.getJSONArray("goals").length() == 0)) {
				System.out.println("Bala&&&&");
				responseToRestController.put("Goals", "noGoals");
				responseToRestController.put("status", "success");
				responseToRestController.put("expenseafterGoalDetails", expenseBeforeGoal);
				return responseToRestController;
			} else {
				System.out.println("Bala&&&&^^^^");
				for (int i = 0; i < Details.getGoals().length; i++) {
					goalIDarray.put(Details.getGoals()[i]);
					RetirementGoal DetailsGoals = MongoDBConnection.goalcoll.findOne("{_id:#}", Details.getGoals()[i]).as(RetirementGoal.class);
					goalarray.put(DetailsGoals.getGoalType());
					if(DetailsGoals.getGoalType().equals("Retirement"))
					{
						retirementAge=DetailsGoals.getRetirementAge();
						////System.out.println("retiement age "+retirementAge);
						responseToRestController.put("retirementAge", retirementAge);
					}

				}
				responseToRestController.put("Goals", goalarray);
				responseToRestController.put("GoalIds", goalIDarray);
				responseToRestController.put("equity", finJsonaftergoal.getJSONArray("equity"));
				FinPlan ExpenseDetail = MongoDBConnection.finplancol.findOne("{plan_name:#,usr_id:#}", planName, reportModel.getUser_id()).as(FinPlan.class);
				if (ExpenseDetail != null) {
					JSONObject expenseJson = new JSONObject(jsonMapper.writeValueAsString(ExpenseDetail.getExpense()));

					if (!expenseJson.isNull("kid_expense")) {
						jobject.put("kid_expense", expenseJson.getJSONArray("kid_expense"));
					}

					if (!expenseJson.isNull("college_expense")) {
						jobject.put("college_expense", expenseJson.getJSONArray("college_expense"));
					}
					if (!expenseJson.isNull("customized_expense")) {
						jobject.put("customized_expense", expenseJson.getJSONArray("customized_expense"));
					}

					responseToRestController.put("expenses", expenseJson);
					responseToRestController.put("expenseafterGoalDetails", expenseBeforeGoal);
					return responseToRestController;
				}
				responseToRestController.put("status", "success");
				return responseToRestController;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return responseToRestController;
		}

	}
}
