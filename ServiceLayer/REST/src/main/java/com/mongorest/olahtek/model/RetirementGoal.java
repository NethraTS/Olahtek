package com.mongorest.olahtek.model;

public class RetirementGoal {
	
	String _id;
	String goal_id;
	String user_id;
	String userName;
	String goalType;
	String goalname;
	String plan_name;
	String frequency;
	
	int userSelectedSpouseRetirementAge; 
	double userSavedAmount;
	double userContributeAmount;
	double spouseContributeAmount;
	double userMatchContribution;
	double spouseMatchContribution;
	double userContributionInIRA;
	double spouseContributionInIRA;
	double userContributionInRothIRA;
	double spouseContributionInRothIRA;
	double userSponsoredRothAcn;
	double spouseSponsoredRothAcn;
	double  userSavedInIRA;
	double userSavedInRothIRA;
	double userSavedRothAcn;
	double spouseSavedAmount;
	double spouseSavedInIRA;
	double spouseSavedInRothIRA;
	double spouseSavedRothAcn;
	double userCap;
	double spouseCap;
	int userSelectedSpouseYear;
	
	public int getUserSelectedSpouseRetirementAge() {
		return userSelectedSpouseRetirementAge;
	}

	public void setUserSelectedSpouseRetirementAge(int userSelectedSpouseRetirementAge) {
		this.userSelectedSpouseRetirementAge = userSelectedSpouseRetirementAge;
	}

	public int getUserSelectedSpouseYear() {
		return userSelectedSpouseYear;
	}

	public void setUserSelectedSpouseYear(int userSelectedSpouseYear) {
		this.userSelectedSpouseYear = userSelectedSpouseYear;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public double getUserCap() {
		return userCap;
	}

	public void setUserCap(double userCap) {
		this.userCap = userCap;
	}

	public double getSpouseCap() {
		return spouseCap;
	}

	public void setSpouseCap(double spouseCap) {
		this.spouseCap = spouseCap;
	}
	int marriageYear;
	int spouseAge;
	int user401kCont;
	int spouse401kCont;
	int  spouseBirthYear;

	

	public int getUser401kCont() {
	return user401kCont;
}

public void setUser401kCont(int user401kCont) {
	this.user401kCont = user401kCont;
}

public int getSpouse401kCont() {
	return spouse401kCont;
}

public void setSpouse401kCont(int spouse401kCont) {
	this.spouse401kCont = spouse401kCont;
}

	public int getSpouseBirthYear() {

		return spouseBirthYear;

	}

	public void setSpouseBirthYear(int spouseBirthYear) {

		this.spouseBirthYear = spouseBirthYear;

	}	
	
	public int getMarriageYear() {
		return marriageYear;
	}
	public void setMarriageYear(int marriageYear) {
		this.marriageYear = marriageYear;
	}
	public int getSpouseAge() {
		return spouseAge;
	}
	public void setSpouseAge(int spouseAge) {
		this.spouseAge = spouseAge;
	}
	public double getUserSavedAmount() {
		return userSavedAmount;
	}
	public void setUserSavedAmount(double userSavedAmount) {
		this.userSavedAmount = userSavedAmount;
	}
	public double getUserContributeAmount() {
		return userContributeAmount;
	}
	public void setUserContributeAmount(double userContributeAmount) {
		this.userContributeAmount = userContributeAmount;
	}
	public double getSpouseContributeAmount() {
		return spouseContributeAmount;
	}
	public void setSpouseContributeAmount(double spouseContributeAmount) {
		this.spouseContributeAmount = spouseContributeAmount;
	}
	public double getUserMatchContribution() {
		return userMatchContribution;
	}
	public void setUserMatchContribution(double userMatchContribution) {
		this.userMatchContribution = userMatchContribution;
	}
	public double getSpouseMatchContribution() {
		return spouseMatchContribution;
	}
	public void setSpouseMatchContribution(double spouseMatchContribution) {
		this.spouseMatchContribution = spouseMatchContribution;
	}
	public double getUserContributionInIRA() {
		return userContributionInIRA;
	}
	public void setUserContributionInIRA(double userContributionInIRA) {
		this.userContributionInIRA = userContributionInIRA;
	}
	public double getSpouseContributionInIRA() {
		return spouseContributionInIRA;
	}
	public void setSpouseContributionInIRA(double spouseContributionInIRA) {
		this.spouseContributionInIRA = spouseContributionInIRA;
	}
	public double getUserContributionInRothIRA() {
		return userContributionInRothIRA;
	}
	public void setUserContributionInRothIRA(double userContributionInRothIRA) {
		this.userContributionInRothIRA = userContributionInRothIRA;
	}
	public double getSpouseContributionInRothIRA() {
		return spouseContributionInRothIRA;
	}
	public void setSpouseContributionInRothIRA(double spouseContributionInRothIRA) {
		this.spouseContributionInRothIRA = spouseContributionInRothIRA;
	}
	public double getUserSponsoredRothAcn() {
		return userSponsoredRothAcn;
	}
	public void setUserSponsoredRothAcn(double userSponsoredRothAcn) {
		this.userSponsoredRothAcn = userSponsoredRothAcn;
	}
	public double getSpouseSponsoredRothAcn() {
		return spouseSponsoredRothAcn;
	}
	public void setSpouseSponsoredRothAcn(double spouseSponsoredRothAcn) {
		this.spouseSponsoredRothAcn = spouseSponsoredRothAcn;
	}
	public double getUserSavedInIRA() {
		return userSavedInIRA;
	}
	public void setUserSavedInIRA(double userSavedInIRA) {
		this.userSavedInIRA = userSavedInIRA;
	}
	public double getUserSavedInRothIRA() {
		return userSavedInRothIRA;
	}
	public void setUserSavedInRothIRA(double userSavedInRothIRA) {
		this.userSavedInRothIRA = userSavedInRothIRA;
	}
	public double getUserSavedRothAcn() {
		return userSavedRothAcn;
	}
	public void setUserSavedRothAcn(double userSavedRothAcn) {
		this.userSavedRothAcn = userSavedRothAcn;
	}
	public double getSpouseSavedAmount() {
		return spouseSavedAmount;
	}
	public void setSpouseSavedAmount(double spouseSavedAmount) {
		this.spouseSavedAmount = spouseSavedAmount;
	}
	public double getSpouseSavedInIRA() {
		return spouseSavedInIRA;
	}
	public void setSpouseSavedInIRA(double spouseSavedInIRA) {
		this.spouseSavedInIRA = spouseSavedInIRA;
	}
	public double getSpouseSavedInRothIRA() {
		return spouseSavedInRothIRA;
	}
	public void setSpouseSavedInRothIRA(double spouseSavedInRothIRA) {
		this.spouseSavedInRothIRA = spouseSavedInRothIRA;
	}
	public double getSpouseSavedRothAcn() {
		return spouseSavedRothAcn;
	}
	public void setSpouseSavedRothAcn(double spouseSavedRothAcn) {
		this.spouseSavedRothAcn = spouseSavedRothAcn;
	}
	public String getGoalname() {
		return goalname;
	}
	public void setGoalname(String goalname) {
		this.goalname = goalname;
	}
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public void setSpouseRetirementAge(int spouseRetirementAge) {
		this.spouseRetirementAge = spouseRetirementAge;
	}
	int spouseLifeExpectancy;
	int retirementAge;
	String 	fin_id;
	int spouseRetirementAge; 
	long otherRetirementIncome; 
	int userAIME; 
	int spouseAIME ;
	long spouseOtherRetirementIncome;
	double userSSB; 
	double userEarlySSB; 
	long userAgeAtEarlySSB; 
	double spouseSSB;	  
	double spouseEarlySSB;  
			
	long spouseAgeAtEarlySSB;
	long retirement_expense;
	String createdTs;
	String modifiedTs;
	int completedStatus;
	int lifeExpectancy ;
	String formType;
	public RetirementGoal()
	{
		
	}
	public RetirementGoal(String _id,String fin_id,int spouseLifeExpectancy,long retirement_expense,String formType,int lifeExpectancy,String goal_id,String user_id,String userName,String goalType,int retirementAge,int spouseRetirementAge,long otherRetirementIncome,int userAIME,int spouseAIME,long spouseOtherRetirementIncome,double userSSB,double userEarlySSB,long userAgeAtEarlySSB,double spouseSSB,double spouseEarlySSB,long spouseAgeAtEarlySSB,String createdTs,String modifiedTs,int completedStatusdouble ,double userCap, double spouseCap)
	{
		this.completedStatus=completedStatus;
		this.createdTs=createdTs;
		this.goal_id=goal_id;
		this.goalType=goalType;
		this.modifiedTs=modifiedTs;
		this.otherRetirementIncome=otherRetirementIncome;
		this.retirementAge=retirementAge;
		this.spouseRetirementAge=spouseRetirementAge;
		this.spouseAgeAtEarlySSB=spouseAgeAtEarlySSB;
		this.spouseAIME=spouseAIME;
		this.spouseEarlySSB=spouseEarlySSB;
		this.spouseOtherRetirementIncome=spouseOtherRetirementIncome;
		this.spouseSSB=spouseSSB;
		this.user_id=user_id;
		this.userAIME=userAIME;
		this.userEarlySSB=userEarlySSB;
		this.userName=userName;
		this.userAgeAtEarlySSB=userAgeAtEarlySSB;
		this.userSSB=userSSB;
		this.formType=formType;
		this.retirement_expense=retirement_expense;
		this.spouseLifeExpectancy=spouseLifeExpectancy;
		this.fin_id=fin_id;
		this._id=_id;
		this.userCap = userCap;
		this.spouseCap = spouseCap;
	}
	
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getFin_id() {
		return fin_id;
	}
	public void setFin_id(String fin_id) {
		this.fin_id = fin_id;
	}
	public long getRetirement_expense() {
		return retirement_expense;
	}
	public void setRetirement_expense(long retirement_expense) {
		this.retirement_expense = retirement_expense;
	}
	public int getLifeExpectancy() {
		return lifeExpectancy;
	}
	public void setLifeExpectancy(int lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}
	public String getGoal_id() {
		return goal_id;
	}
	public void setGoal_id(String goal_id) {
		this.goal_id = goal_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGoalType() {
		return goalType;
	}
	public void setGoalType(String goalType) {
		this.goalType = goalType;
	}
	public int getRetirementAge() {
		return retirementAge;
	}
	public void setRetirementAge(int retirementAge) {
		this.retirementAge = retirementAge;
	}
	public int getSpouseRetirementAge() {
		return spouseRetirementAge;
	}
	public void setSpouceRetirementAge(int spouseRetirementAge) {
		this.spouseRetirementAge = spouseRetirementAge;
	}
	public long getOtherRetirementIncome() {
		return otherRetirementIncome;
	}
	public void setOtherRetirementIncome(long otherRetirementIncome) {
		this.otherRetirementIncome = otherRetirementIncome;
	}
	public int getUserAIME() {
		return userAIME;
	}
	public void setUserAIME(int userAIME) {
		this.userAIME = userAIME;
	}
	
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}
	public int getSpouseAIME() {
		return spouseAIME;
	}
	public void setSpouseAIME(int spouseAIME) {
		this.spouseAIME = spouseAIME;
	}
	public long getSpouseOtherRetirementIncome() {
		return spouseOtherRetirementIncome;
	}
	public void setSpouseOtherRetirementIncome(long spouseOtherRetirementIncome) {
		this.spouseOtherRetirementIncome = spouseOtherRetirementIncome;
	}
	public double getUserSSB() {
		return userSSB;
	}
	public void setUserSSB(double userSSB) {
		this.userSSB = userSSB;
	}
	public double getUserEarlySSB() {
		return userEarlySSB;
	}
	public void setUserEarlySSB(double userEarlySSB) {
		this.userEarlySSB = userEarlySSB;
	}
	public long getUserAgeAtEarlySSB() {
		return userAgeAtEarlySSB;
	}
	public void setUserAgeAtEarlySSB(long userAgeAtEarlySSB) {
		this.userAgeAtEarlySSB = userAgeAtEarlySSB;
	}
	public double getSpouseSSB() {
		return spouseSSB;
	}
	public void setSpouseSSB(double spouseSSB) {
		this.spouseSSB = spouseSSB;
	}
	public double getSpouseEarlySSB() {
		return spouseEarlySSB;
	}
	public void setSpouseEarlySSB(double spouseEarlySSB) {
		this.spouseEarlySSB = spouseEarlySSB;
	}
	public long getSpouseAgeAtEarlySSB() {
		return spouseAgeAtEarlySSB;
	}
	public void setSpouseAgeAtEarlySSB(long spouseAgeAtEarlySSB) {
		this.spouseAgeAtEarlySSB = spouseAgeAtEarlySSB;
	}
	public String getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(String createdTs) {
		this.createdTs = createdTs;
	}
	public String getModifiedTs() {
		return modifiedTs;
	}
	public void setModifiedTs(String modifiedTs) {
		this.modifiedTs = modifiedTs;
	}
	public int getCompletedStatus() {
		return completedStatus;
	}
	public void setCompletedStatus(int completedStatus) {
		this.completedStatus = completedStatus;
	}
	public int getSpouseLifeExpectancy() {
		return spouseLifeExpectancy;
	}
	public void setSpouseLifeExpectancy(int spouseLifeExpectancy) {
		this.spouseLifeExpectancy = spouseLifeExpectancy;
	}
	

}
