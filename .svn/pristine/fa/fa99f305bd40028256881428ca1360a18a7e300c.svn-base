package com.mongorest.olahtek.service;

import java.util.List;

import org.json.JSONObject;

import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.User;

public interface UserService {

	JSONObject findByEmail(String email, String password);

	JSONObject getUserDetails(String _id);

	JSONObject EditUserProfile(User user);

	User checkLogin(String email, String password);

	User findByName(String name);

	JSONObject saveUser(User user);

	JSONObject logOutUser(User user);

	JSONObject changePlanName(FinPlan finPlan);

	JSONObject checkSession(User user, String status);

	JSONObject newFinPlan(FinPlan finPlan);

	JSONObject copyFinPlan(FinPlan finPlan);

	

	JSONObject showPlans(FinPlan finPlan);

	JSONObject showGoals(FinPlan finPlan);
	JSONObject deleteGoal(FinPlan finPlan);

	JSONObject deleteFinPlan(FinPlan finPlan);

	JSONObject showGoalStatus(FinPlan finPlan);

	public JSONObject deleteIncomeProfile(String profileName, String user_id);

	void updateUser(User user);

	void deleteUserById(String _id);

	List<User> findAllUsers();

	void deleteAllUsers();

	JSONObject retirementGoal(RetirementGoal goal);

	public boolean isUserExist(String email);

	public boolean isPlanExist(String planName, String user_id);

	public String encryptPassword(String password);

}
