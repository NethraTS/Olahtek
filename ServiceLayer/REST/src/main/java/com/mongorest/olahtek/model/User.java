package com.mongorest.olahtek.model;

import java.util.List;

public class User {
    double devidendTaxbleInvestmentRate;
    double gainNonTaxableInvestmentRate;
    double devidendNonTaxbleInvestmentRate;
    double gainTaxableInvestmentRate;
    double savingInterestRate;
    private double realEstate;
    private String investmentPortFolio;
    private int riskScore;
    private String[] finplans;
    private String role;
    private double remainingMortgageNew;
    private int newYearMorgage;
    String filingStatus;
    public int dependants;
    private String newPassword;
    private String oldPassword;
    private double houseValue;
    private String modifiedTs;
    private String createdTs;
    private double remainingMortgage;
    private long remainingYearsMortgage;
    private double remainingMortgageInterestRate;
    String creditScore;
    private String profile_name; // for multiple income profile
    private String profileName; // for fetching income profile for user profile
    private String _id;
    private String name;
    private String action;
    private String loggedInTime;
    private String marital_status;
    private int completedFlag;
    private String projection;
    private Long currentYear;
    private Long currentYearIncome;
    private String dob;
    private String sessionID;
    private String spouse_dob;
    private String password;
    private String county;
    private String address1;
    private String address2;
    private String college_info;
    private String user_id;
    private int childCount;
    private int editExpenseFlag;

    public int getEditExpenseFlag() {
		return editExpenseFlag;
	}


	public void setEditExpenseFlag(int editExpenseFlag) {
		this.editExpenseFlag = editExpenseFlag;
	}


	private String email;
    private String city;
    private String state;
    private int birthYear;
    private int spouseBirthYear;
    private List<SpouseIncome> spouse_income;
    private int age;
    private String marriedStatus;
    private double spouseBeforeTaxIncome;
    private String houseStatus;
    private String childCollegeflag;
    private int noOfDependentfortax;
    private int userDependent;
    private double houseAppreciationRate;
    private double kidcostFactor;
    public double getKidcostFactor() {
        return kidcostFactor;
    }


    public void setKidcostFactor(double kidcostFactor) {
        this.kidcostFactor = kidcostFactor;
    }


    public int getUserDependent() {
        return userDependent;
    }


    public void setUserDependent(int userDependent) {
        this.userDependent = userDependent;
    }


    public int getNoOfDependentfortax() {
        return noOfDependentfortax;
    }


    public void setNoOfDependentfortax(int noOfDependentfortax) {
        this.noOfDependentfortax = noOfDependentfortax;
    }


    public String getChildCollegeflag() {
        return childCollegeflag;
    }


    public void setChildCollegeflag(String childCollegeflag) {
        this.childCollegeflag = childCollegeflag;
    }


    private double rentalExpenses;
    private double otherExpenses;
    private double cash;
    private double userBeforeTaxIncome;
    private double taxableInvestments;
    private long rothRetirement;
    private long taxdeferred;
    private long fiveTnp;
    private String lastVisitedPage;
    private double monthlyExpenses;
    private int SpouseAge;
    private double spouseSSB;
    private double userSSB;
    private String spouseName;
    private String lname;
    private String spouse_lname;
    private List<Child> childs;
    private Long incomeEdit;
    private Long yearEdit;
    private List<Income> user_income;
    private List<Income> income;
    private Long sum;
    private Long kidCostCalculated;
    private String kidCostCalculatedArray;

    public String getKidCostCalculatedArray() {
        return kidCostCalculatedArray;
    }


    public void setKidCostCalculatedArray(String kidCostCalculatedArray) {
        this.kidCostCalculatedArray = kidCostCalculatedArray;
    }


    public Long getKidCostCalculated() {
        return kidCostCalculated;
    }


    public void setKidCostCalculated(Long kidCostCalculated) {
        this.kidCostCalculated = kidCostCalculated;
    }


    public Long getSum() {
        return sum;
    }


    public void setSum(Long sum) {
        this.sum = sum;
    }


    public String getNonTaxableInvestments() {
        return nonTaxableInvestments;
    }


    public void setNonTaxableInvestments(String nonTaxableInvestments) {
        this.nonTaxableInvestments = nonTaxableInvestments;
    }


    private String nonTaxableInvestments;
    private String formType;
    private Long endYear;
    private long houseMarketValue;
    private long whatIsYourCurrentMortgageRate;
    int retirementFlag;
    private double user401k;
    private double spouse401k;
    private double userIRA;
    private double spouseIRA;
    private double user529;

    private double spousePlan529;
    private double userRothIra;
    private double spouseRothIra;

    public double getUserRothIra() {
        return userRothIra;
    }

    public double getUser529() {
        return user529;
    }


    public void setUser529(double user529) {
        this.user529 = user529;
    }

    public void setUserRothIra(double userRothIra) {
        this.userRothIra = userRothIra;
    }


    public double getSpouseRothIra() {
        return spouseRothIra;
    }


    public void setSpouseRothIra(double spouseRothIra) {
        this.spouseRothIra = spouseRothIra;
    }


    public User(String _id, String user_id, String name, long spouseBeforeTaxIncome, String password, int childCount, int age, String email, String spouseName,
            int SpouseAge, String city, long userBeforeTaxIncome, long taxableInvestments, long monthlyExpenses, String marital_status, String state,
            String houseStatus, long rentalExpenses, long otherExpenses, long cash, List<Child> childs, String nonTaxableInvestments, String dob,
            String spouse_dob, long rothRetirement, long taxdeferred, long fiveTnp, long realEstate) {
        this._id = _id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.dob = dob;
        this.spouse_dob = spouse_dob;
        this.user_id = user_id;
        this.city = city;
        this.email = email;
        this.spouseBeforeTaxIncome = spouseBeforeTaxIncome;
        this.userBeforeTaxIncome = userBeforeTaxIncome;
        this.taxableInvestments = taxableInvestments;
        this.monthlyExpenses = monthlyExpenses;
        this.state = state;
        this.cash = cash;
        this.rentalExpenses = rentalExpenses;
        this.houseStatus = houseStatus;
        this.marital_status = marital_status;
        this.otherExpenses = otherExpenses;
        this.SpouseAge = SpouseAge;
        this.spouseName = spouseName;
        this.childs = childs;
        this.childCount = childCount;
        this.nonTaxableInvestments = nonTaxableInvestments;
        this.rothRetirement = rothRetirement;
        this.taxdeferred = taxdeferred;
        this.fiveTnp = fiveTnp;
        this.realEstate = realEstate;
    }


    public int getRetirementFlag() {
        return retirementFlag;
    }


    public void setRetirementFlag(int retirementFlag) {
        this.retirementFlag = retirementFlag;
    }


    public long getWhatIsYourCurrentMortgageRate() {
        return whatIsYourCurrentMortgageRate;
    }

    public void setWhatIsYourCurrentMortgageRate(long whatIsYourCurrentMortgageRate) {
        this.whatIsYourCurrentMortgageRate = whatIsYourCurrentMortgageRate;
    }
    public long getHouseMarketValue() {
        return houseMarketValue;
    }

    public void setHouseMarketValue(long houseMarketValue) {
        this.houseMarketValue = houseMarketValue;
    }



    public int getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }

    public String getInvestmentPortFolio() {
        return investmentPortFolio;
    }

    public void setInvestmentPortFolio(String investmentPortFolio) {
        this.investmentPortFolio = investmentPortFolio;
    }

    public int getNewYearMorgage() {
        return newYearMorgage;
    }

    public void setNewYearMorgage(int newYearMorgage) {
        this.newYearMorgage = newYearMorgage;
    }

    public int getDependants() {
        return dependants;
    }

    public void setDependants(int dependants) {
        this.dependants = dependants;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFilingStatus() {
        return filingStatus;
    }

    public void setFilingStatus(String filingStatus) {
        this.filingStatus = filingStatus;
    }

    public double getRemainingMortgageInterestRate() {
        return remainingMortgageInterestRate;
    }

    public void setRemainingMortgageInterestRate(double remainingMortgageInterestRate) {
        this.remainingMortgageInterestRate = remainingMortgageInterestRate;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
    }

    public double getDevidendTaxbleInvestmentRate() {
        return devidendTaxbleInvestmentRate;
    }

    public void setDevidendTaxbleInvestmentRate(double devidendTaxbleInvestmentRate) {
        this.devidendTaxbleInvestmentRate = devidendTaxbleInvestmentRate;
    }

    public double getGainNonTaxableInvestmentRate() {
        return gainNonTaxableInvestmentRate;
    }

    public void setGainNonTaxableInvestmentRate(double gainNonTaxableInvestmentRate) {
        this.gainNonTaxableInvestmentRate = gainNonTaxableInvestmentRate;
    }

    public double getDevidendNonTaxbleInvestmentRate() {
        return devidendNonTaxbleInvestmentRate;
    }

    public void setDevidendNonTaxbleInvestmentRate(double devidendNonTaxbleInvestmentRate) {
        this.devidendNonTaxbleInvestmentRate = devidendNonTaxbleInvestmentRate;
    }

    public double getGainTaxableInvestmentRate() {
        return gainTaxableInvestmentRate;
    }

    public void setGainTaxableInvestmentRate(double gainTaxableInvestmentRate) {
        this.gainTaxableInvestmentRate = gainTaxableInvestmentRate;
    }

    public double getSavingInterestRate() {
        return savingInterestRate;
    }

    public void setSavingInterestRate(double savingInterestRate) {
        this.savingInterestRate = savingInterestRate;
    }

    public double getHouseValue() {
        return houseValue;
    }

    public void setHouseValue(long houseValue) {
        this.houseValue = houseValue;
    }

    public double getRemainingMortgage() {
        return remainingMortgage;
    }

    public void setRemainingMortgage(long remainingMortgage) {
        this.remainingMortgage = remainingMortgage;
    }

    public long getRemainingYearsMortgage() {
        return remainingYearsMortgage;
    }

    public void setRemainingYearsMortgage(long remainingYearsMortgage) {
        this.remainingYearsMortgage = remainingYearsMortgage;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Long getCurrentYearIncome() {
        return currentYearIncome;
    }

    public void setCurrentYearIncome(Long currentYearIncome) {
        this.currentYearIncome = currentYearIncome;
    }

    public Long getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Long currentYear) {
        this.currentYear = currentYear;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public int getCompletedFlag() {
        return completedFlag;
    }

    public void setCompletedFlag(int completedFlag) {
        this.completedFlag = completedFlag;
    }

    public List<SpouseIncome> getSpouse_income() {
        return spouse_income;
    }

    public void setSpouse_income(List<SpouseIncome> spouse_income) {
        this.spouse_income = spouse_income;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getModifiedTs() {
        return modifiedTs;
    }

    public void setModifiedTs(String modifiedTs) {
        this.modifiedTs = modifiedTs;
    }

    public String getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(String createdTs) {
        this.createdTs = createdTs;
    }

    public String getSpouse_dob() {
        return spouse_dob;
    }

    public void setSpouse_dob(String spouse_dob) {
        this.spouse_dob = spouse_dob;
    }

    public String getCollege_info() {
        return college_info;
    }

    public void setCollege_info(String college_info) {
        this.college_info = college_info;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getSpouse_lname() {
        return spouse_lname;
    }

    public void setSpouse_lname(String spouse_lname) {
        this.spouse_lname = spouse_lname;
    }

    public double getRentalExpenses() {
        return rentalExpenses;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }


    public int getSpouseBirthYear() {
        return spouseBirthYear;
    }

    public void setSpouseBirthYear(int spouseBirthYear) {
        this.spouseBirthYear = spouseBirthYear;
    }

    public long getRothRetirement() {
        return rothRetirement;
    }

    public void setRothRetirement(long rothRetirement) {
        this.rothRetirement = rothRetirement;
    }

    public long getTaxdeferred() {
        return taxdeferred;
    }

    public void setTaxdeferred(long taxdeferred) {
        this.taxdeferred = taxdeferred;
    }

    public long getFiveTnp() {
        return fiveTnp;
    }

    public void setFiveTnp(long fiveTnp) {
        this.fiveTnp = fiveTnp;
    }

    public Long getIncomeEdit() {
        return incomeEdit;
    }

    public void setIncomeEdit(Long incomeEdit) {
        this.incomeEdit = incomeEdit;
    }

    public Long getYearEdit() {
        return yearEdit;
    }

    public void setYearEdit(Long yearEdit) {
        this.yearEdit = yearEdit;
    }

    public String getLastVisitedPage() {
        return lastVisitedPage;
    }

    public void setLastVisitedPage(String lastVisitedPage) {
        this.lastVisitedPage = lastVisitedPage;
    }

    public String getLoggedInTime() {
        return loggedInTime;
    }

    public void setLoggedInTime(String loggedInTime) {
        this.loggedInTime = loggedInTime;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getNonTaxtableInvesment() {
        return nonTaxableInvestments;
    }

    public void setNonTaxtableInvesment(String nonTaxableInvestments) {
        this.nonTaxableInvestments = nonTaxableInvestments;
    }

    public List<Child> getChilds() {
        return childs;
    }

    public void setChilds(List<Child> childs) {
        this.childs = childs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSpouseAge() {
        return SpouseAge;
    }

    public String getSpouseDob() {
        return spouse_dob;
    }

    public String getDob() {
        return dob;
    }

    public void setSpouseAge(int SpouseAge) {
        this.SpouseAge = SpouseAge;
    }

    public void setSpouseDob(String spouse_dob) {
        this.spouse_dob = spouse_dob;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public String getSpouseLastName() {
        return spouse_lname;
    }

    public String getLastName() {
        return lname;
    }

    public String getCounty() {
        return county;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public double getUserBeforeTaxIncome() {
        return userBeforeTaxIncome;
    }

    public Long getEndYear() {
        return endYear;
    }

    public void setEndYear(Long endYear) {
        this.endYear = endYear;
    }

    public void setUserBeforeTaxIncome(long userBeforeTaxIncome) {
        this.userBeforeTaxIncome = userBeforeTaxIncome;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public User() {
        _id = null;
    }



    public double getTaxableInvestments() {
        return taxableInvestments;
    }

    public void setTaxableInvestments(long taxableInvestments) {
        this.taxableInvestments = taxableInvestments;
    }

    public double getSpouseBeforeTaxIncome() {
        return spouseBeforeTaxIncome;
    }

    public void setSpouseBeforeTaxIncome(long spouseBeforeTaxIncome) {
        this.spouseBeforeTaxIncome = spouseBeforeTaxIncome;
    }

    public double getRentExpenses() {
        return rentalExpenses;
    }

    public double getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(long monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMarriedStatus() {
        return marriedStatus;
    }

    public void setMarriedStatus(String marriedStatus) {
        this.marriedStatus = marriedStatus;
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public double getRentalExpense() {
        return rentalExpenses;
    }

    public void setRentalExpenses(long rentalExpenses) {
        this.rentalExpenses = rentalExpenses;
    }

    public double getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(long otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public double getCash() {
        return cash;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // @Overr_ide
    /*
     * public int hashCode() { final int prime = 31; int result = 1; result =
     * prime * result + (int) (_id ^ (_id >>> 32)); return result; }
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (_id != other._id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User [_id=" + _id + ", name1=" + name + ", age=" + age + ",UserBeforeTaxIncome=" + userBeforeTaxIncome + "]";
    }

    public String getUserId() {
        return user_id;
    }

    public String getProfile_name() {
        return profile_name;
    }

    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }

    public List<Income> getUser_income() {
        return user_income;
    }

    public void setUser_income(List<Income> user_income) {
        this.user_income = user_income;
    }

    public List<Income> getIncome() {
        return income;
    }

    public void setIncome(List<Income> income) {
        this.income = income;
    }

    public double getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(long realEstate) {
        this.realEstate = realEstate;
    }

    public String[] getFinplans() {
        return finplans;
    }

    public void setFinplans(String[] finplans) {
        this.finplans = finplans;
    }

    public double getRemainingMortgageNew() {
        return remainingMortgageNew;
    }



    public double getSpouseSSB() {
        return spouseSSB;
    }

    public void setSpouseSSB(double spouseSSB) {
        this.spouseSSB = spouseSSB;
    }

    public double getUserSSB() {
        return userSSB;
    }

    public void setUserSSB(double userSSB) {
        this.userSSB = userSSB;
    }

    public void setRemainingMortgageNew(double remainingMortgageNew) {
        this.remainingMortgageNew = remainingMortgageNew;
    }


    public double getUser401k() {
        return user401k;
    }


    public void setUser401k(double user401k) {
        this.user401k = user401k;
    }


    public double getSpouse401k() {
        return spouse401k;
    }


    public void setSpouse401k(double spouse401k) {
        this.spouse401k = spouse401k;
    }


    public double getUserIRA() {
        return userIRA;
    }


    public void setUserIRA(double userIRA) {
        this.userIRA = userIRA;
    }


    public double getSpouseIRA() {
        return spouseIRA;
    }


    public void setSpouseIRA(double spouseIRA) {
        this.spouseIRA = spouseIRA;
    }

    public double getSpousePlan529() {
        return spousePlan529;
    }


    public void setSpousePlan529(double spousePlan529) {
        this.spousePlan529 = spousePlan529;
    }


    public double getHouseAppreciationRate() {
        return houseAppreciationRate;
    }


    public void setHouseAppreciationRate(double houseAppreciationRate) {
        this.houseAppreciationRate = houseAppreciationRate;
    }

}
