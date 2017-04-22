package com.mongorest.olahtek.model;

import java.util.List;

public class IncomeProfile {
    String _id;
    String plan_name;
    String profile_name;
    String user_id;
    String actionType;
    String oldIncomeProfile;
    String selectedProfileName;
    public String getSelectedProfileName() {
		return selectedProfileName;
	}

	public void setSelectedProfileName(String selectedProfileName) {
		this.selectedProfileName = selectedProfileName;
	}

	public int getEditExpenseFlag() {
		return editExpenseFlag;
	}

	public void setEditExpenseFlag(int editExpenseFlag) {
		this.editExpenseFlag = editExpenseFlag;
	}
	double housingExpense;
	double nonHousingExpense;
    int editExpenseFlag;

	public double getHousingExpense() {
		return housingExpense;
	}

	public void setHousingExpense(double housingExpense) {
		this.housingExpense = housingExpense;
	}

	public double getNonHousingExpense() {
		return nonHousingExpense;
	}

	public void setNonHousingExpense(double nonHousingExpense) {
		this.nonHousingExpense = nonHousingExpense;
	}
	List<Income> income;
    List<TaxModel> tax;
    List<UserExpense> userExpense;
    List<Assets> assests;
    private List<Limits> limits;
    private List<Equity> equity;
    public List<Limits> getLimits() {
        return limits;
    }

    public void setLimits(final List<Limits> limits) {
        this.limits = limits;
    }

    public List<UserExpense> getUserExpense() {
        return userExpense;
    }

    public void setUserExpense(final List<UserExpense> userExpense) {
        this.userExpense = userExpense;
    }

    public List<Assets> getAssests() {
        return assests;
    }

    public void setAssests(final List<Assets> assests) {
        this.assests = assests;
    }

    public List<Income> getIncome() {
        return income;
    }

    public void setIncome(final List<Income> income) {
        this.income = income;
    }
    List<UserPlot> userPlot;
    List<SpousePlot> spousePlot;
    List<HousingPlot> housingPlot;
    List<NonHousingPlot> nonHousingPlot;

    public List<UserPlot> getUserPlot() {
        return userPlot;
    }

    public void setUserPlot(final List<UserPlot> userPlot) {
        this.userPlot = userPlot;
    }

    public List<SpousePlot> getSpousePlot() {
        return spousePlot;
    }

    public void setSpousePlot(final List<SpousePlot> spousePlot) {
        this.spousePlot = spousePlot;
    }

    public List<HousingPlot> getHousingPlot() {
        return housingPlot;
    }

    public void setHousingPlot(final List<HousingPlot> housingPlot) {
        this.housingPlot = housingPlot;
    }

    public List<NonHousingPlot> getNonHousingPlot() {
        return nonHousingPlot;
    }

    public void setNonHousingPlot(final List<NonHousingPlot> nonHousingPlot) {
        this.nonHousingPlot = nonHousingPlot;
    }
    List<Income> user_income;
    List<Income> spouse_income;
    List<Income> combined_income;
    long remaning_amount;
    String modifiedTs;
    String createdTs;

    public List<TaxModel> getTax() {
        return tax;
    }

    public void setTax(final List<TaxModel> tax) {
        this.tax = tax;
    }

    public String get_id() {
        return _id;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(final String actionType) {
        this.actionType = actionType;
    }

    public void set_id(final String _id) {
        this._id = _id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(final String plan_name) {
        this.plan_name = plan_name;
    }

    public String getProfile_name() {
        return profile_name;
    }

    public void setProfile_name(final String profile_name) {
        this.profile_name = profile_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(final String user_id) {
        this.user_id = user_id;
    }

    public List<Income> getUser_income() {
        return user_income;
    }

    public void setUser_income(final List<Income> user_income) {
        this.user_income = user_income;
    }

    public List<Income> getSpouse_income() {
        return spouse_income;
    }

    public void setSpouse_income(final List<Income> spouse_income) {
        this.spouse_income = spouse_income;
    }

    public List<Income> getCombined_income() {
        return combined_income;
    }

    public void setCombined_income(final List<Income> combine_income) {
        this.combined_income = combine_income;
    }

    public long getRemaning_amount() {
        return remaning_amount;
    }

    public void setRemaning_amount(final long remaning_amount) {
        this.remaning_amount = remaning_amount;
    }

    public String getModifiedTs() {
        return modifiedTs;
    }

    public void setModifiedTs(final String modifiedTs) {
        this.modifiedTs = modifiedTs;
    }

    public String getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(final String createdTs) {
        this.createdTs = createdTs;
    }

    public String getOldIncomeProfile() {
        return oldIncomeProfile;
    }

    public void setOldIncomeProfile(final String oldIncomeProfile) {
        this.oldIncomeProfile = oldIncomeProfile;
    }

    public List<Equity> getEquity() {
        return equity;
    }

    public void setEquity(final List<Equity> equity) {
        this.equity = equity;
    }


}
