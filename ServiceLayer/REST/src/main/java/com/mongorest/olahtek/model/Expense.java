package com.mongorest.olahtek.model;

import java.util.List;

public class Expense {
    private RetirementExpenses retirement_expense;
    private VacationExpense vacation_expense;

    //private HousingExpense housing_expense;
    private List<KidExpense> kid_expense;
    private List<HousingExpense>housing_expense;
    private MarriageExpense marriage_expense;
    private EmergencyExpense emergency_expense;
    //private CustomizedExpense customized_expense;
    private List<CustomizedExpense> customized_expense;
    private List<CollegeeducationExpense> college_expense;
    private List<CarExpense>carGoalExpense;

    public List<CustomizedExpense> getCustomized_expense() {
        return customized_expense;
    }
    public void setCustomized_expense(List<CustomizedExpense> customized_expense) {
        this.customized_expense = customized_expense;
    }
    public RetirementExpenses getRetirement_expense() {
        return retirement_expense;
    }
    public void setRetirement_expense(RetirementExpenses retirement_expense) {
        this.retirement_expense = retirement_expense;
    }
    public VacationExpense getVacation_expense() {
        return vacation_expense;
    }
    public void setVacation_expense(VacationExpense vacation_expense) {
        this.vacation_expense = vacation_expense;
    }

    public MarriageExpense getMarriage_expense() {
        return marriage_expense;
    }
    public void setMarriage_expense(MarriageExpense marriage_expense) {
        this.marriage_expense = marriage_expense;
    }
    public EmergencyExpense getEmergency_expense() {
        return emergency_expense;
    }
    public void setEmergency_expense(EmergencyExpense emergency_expense) {
        this.emergency_expense = emergency_expense;
    }
    /*public CustomizedExpense getCustomized_expense() {
		return customized_expense;
	}
	public void setCustomized_expense(CustomizedExpense customized_expense) {
		this.customized_expense = customized_expense;
	}*/
    public List<CollegeeducationExpense> getCollege_expense() {
        return college_expense;
    }
    public void setCollege_expense(List<CollegeeducationExpense> college_expense) {
        this.college_expense = college_expense;
    }
    public List<KidExpense> getKid_expense() {
        return kid_expense;
    }
    public void setKid_expense(List<KidExpense> kid_expense) {
        this.kid_expense = kid_expense;
    }
    public List<HousingExpense> getHousing_expense() {
        return housing_expense;
    }
    public void setHousing_expense(List<HousingExpense> housing_expense) {
        this.housing_expense = housing_expense;
    }
    public List<CarExpense> getCarGoalExpense() {
        return carGoalExpense;
    }
    public void setCarGoalExpense(List<CarExpense> carGoalExpense) {
        this.carGoalExpense = carGoalExpense;
    }





}
