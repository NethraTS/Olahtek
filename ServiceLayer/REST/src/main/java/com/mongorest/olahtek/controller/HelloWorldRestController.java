package com.mongorest.olahtek.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mongorest.olahtek.model.AdmindashboardModel;
import com.mongorest.olahtek.model.CarLeaseCalculator;
import com.mongorest.olahtek.model.CarLoanCalculator;
import com.mongorest.olahtek.model.Cargoalmodel;
import com.mongorest.olahtek.model.ConstantValuesModel;
import com.mongorest.olahtek.model.Customizedgoalmodel;
import com.mongorest.olahtek.model.Emergencyfundmodel;
import com.mongorest.olahtek.model.FDI;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.GoalCollegeEducation;
import com.mongorest.olahtek.model.Housegoalmodel;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.InvestmentPortfolioModel;
import com.mongorest.olahtek.model.KidGoalModel;
import com.mongorest.olahtek.model.MC;
import com.mongorest.olahtek.model.Marriagegoalmodel;
import com.mongorest.olahtek.model.ModelVacation;
import com.mongorest.olahtek.model.Reportmodel;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.SSB;
import com.mongorest.olahtek.model.StatetaxModel;
import com.mongorest.olahtek.model.User;
import com.mongorest.olahtek.passwordbuilder.PasswordBuilder;
import com.mongorest.olahtek.service.AdminDashboardService;
import com.mongorest.olahtek.service.CalculationEngine;
import com.mongorest.olahtek.service.CarGoalImplementation;
import com.mongorest.olahtek.service.CarLeaseCalculatorService;
import com.mongorest.olahtek.service.CarLoanCalculatorService;
import com.mongorest.olahtek.service.ChangePasswordService;
import com.mongorest.olahtek.service.ConstantValueServece;
import com.mongorest.olahtek.service.CustomizedGoalImpl;
import com.mongorest.olahtek.service.Emergencyfundserimpl;
import com.mongorest.olahtek.service.GoalCollegeEducationServiceImpl;
import com.mongorest.olahtek.service.HouseGoalImplementation;
import com.mongorest.olahtek.service.IncomeProfileService;
import com.mongorest.olahtek.service.InvestmentPortfolio;
import com.mongorest.olahtek.service.InvestmentPortfolioImpl;
import com.mongorest.olahtek.service.KidGoalImpl;
import com.mongorest.olahtek.service.MarriageGoalimpl;
import com.mongorest.olahtek.service.MongoConnectionInterface;
import com.mongorest.olahtek.service.MongoDBConnection;
import com.mongorest.olahtek.service.MortgageCalculatorService;
import com.mongorest.olahtek.service.Reportimpl;
import com.mongorest.olahtek.service.ResetPassword;
import com.mongorest.olahtek.service.RetirementGoalService;
import com.mongorest.olahtek.service.SSBCalculatorImpl;
import com.mongorest.olahtek.service.UserService;
import com.mongorest.olahtek.service.VacationService;


@RestController
public class HelloWorldRestController {

    @Autowired
    MongoConnectionInterface mongoConnection;
    @Autowired
    UserService userService; // Service which will do all data

    @Autowired
    ConstantValueServece constantValueServece;

    @Autowired // retirement
    RetirementGoalService goalService;

    @Autowired // house goal
    HouseGoalImplementation HouseGoalImpl;

    @Autowired // Emergency goal
    Emergencyfundserimpl emergencyGoalimpl;

    @Autowired // car goal
    CarGoalImplementation CarGoalImpl;

    @Autowired // marriage goal
    MarriageGoalimpl marriageGoalimpl;

    @Autowired // customized goal
    CustomizedGoalImpl customizedGoalImpl;

    @Autowired // kid goal
    KidGoalImpl kidGoalImpl;

    @Autowired // college goal
    GoalCollegeEducationServiceImpl goalCollegeEducationServiceImpl;

    @Autowired // Emergency goal
    VacationService vacationService;

    @Autowired
    ResetPassword resetPassword;

    @Autowired
    ChangePasswordService changePasswordService;

    @Autowired
    IncomeProfileService incomeProfileService;

    @Autowired
    Reportimpl reportImpl;

    @Autowired
    AdminDashboardService admindashboardService;

    @Autowired
    InvestmentPortfolio investmentPortfolio;

    @Autowired
    SSBCalculatorImpl ssbCalculator;

    @Autowired
    MortgageCalculatorService mCalculator;

    @Autowired
    CarLoanCalculatorService clCalculator;

    @Autowired
    CarLeaseCalculatorService cleCalculator;

    //-------------------------Constent values update code--------------------------------------
    @RequestMapping(value = "/constantValues/", method = RequestMethod.POST)
    public String constantUpdate(@RequestBody final ConstantValuesModel constantsValues, final UriComponentsBuilder ucBuilder) {
        JSONObject jobject = new JSONObject();
        try {
            if(constantsValues.getFormType().equals("onLoadfdiValues"))
            {
                jobject =constantValueServece.onLoadFdiData(constantsValues);
            }

            else
            {
                System.out.println("aparna values    =="+constantsValues.getKidGoalDefaultCostPeryear());
                jobject =constantValueServece.updateConstantValuesOfFdi(constantsValues);

            }
            if (jobject.get("status").equals("success")) {
                return jobject.toString();
            } else {
                jobject.put("status", "fail");
                return jobject.toString();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject.toString();
        }
    }
    //------------------------------for state Tax update----------------------------------------
    @RequestMapping(value = "/StateTaxConstant/", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String stateTaxConstantUpdate(@RequestBody final StatetaxModel statetaxModel, final UriComponentsBuilder ucBuilder) {
        JSONObject jobject = new JSONObject();
        try {
            final String status = "session";
            jobject.put("status", "fail");
            System.out.println("stateTax val 888--->>>");
            System.out.println("statetaxModel.getActionType()-->>"+statetaxModel.getActionType());
            if(statetaxModel.getActionType().equals("onLoad"))
            {
                jobject =constantValueServece.onLoadStateData(statetaxModel.getStatename());
                System.out.println("jobject2--->>"+jobject);
                return jobject.toString();

            }
            else if(statetaxModel.getActionType().equals("allstatesnames")){
                jobject =constantValueServece.onLoadAllStateNames();
                System.out.println("jobject-->"+jobject);
                return jobject.toString();
            }
            else if(statetaxModel.getActionType().equals("onLoadUpdate")){
                System.out.println("onload hello rest");
                jobject =constantValueServece.onLoadConstantValues(statetaxModel);
                return jobject.toString();
            }

            else
            {
                jobject =constantValueServece.updateStateConstantValues(statetaxModel);
            }

            if (jobject.get("status").equals("success")) {
                System.out.println("succewrrr__>");
                return jobject.toString();
            } else {
                jobject.put("status", "fail");
                return jobject.toString();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject.toString();
        }
    }
    // ------------------------------UserLogin--------------------------------------------------

    @RequestMapping(value = "/Login/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkUser(@RequestBody final User user) {
        final String email = user.getEmail();
        final String password = user.getPassword();
        JSONObject jobject = new JSONObject();
        try {
            if (user.getAction() != null) {
                if (user.getAction().equals("LogincheckCoockies")) {
                    System.out.println("rest check coockies");
                    final String status = "LogincheckCoockies";
                    jobject.put("status", "fail");
                    jobject = userService.checkSession(user, status);
                    System.out.println("json data from rest check coockies-------" + jobject);
                    return jobject.toString();
                } else {
                    System.out.println("rest check coockies in false");
                    final String status = "login";
                    jobject.put("status", "fail");
                    jobject = userService.checkSession(user, status);
                    return jobject.toString();
                }
            } else {
                jobject.put("status", "fail");
                jobject = userService.findByEmail(email, password);
                return jobject.toString();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject.toString();
        }
    }

    // ---------------------------------session-------------------------------------------
    @RequestMapping(value = "/session/", method = RequestMethod.POST)
    public String createsession(@RequestBody final User user, final UriComponentsBuilder ucBuilder) {
        JSONObject jobject = new JSONObject();
        try {
            final String status = "session";
            jobject.put("status", "fail");
            jobject = userService.checkSession(user, status);
            if (jobject.get("status").equals("success")) {
                return jobject.toString();
            } else {
                jobject.put("status", "fail");
                return jobject.toString();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject.toString();
        }
    }

    // ------------------------------------Registration-------------------------------------

    @RequestMapping(value = "/Register/", method = RequestMethod.POST)
    public String createUser(@RequestBody final User user, final UriComponentsBuilder ucBuilder) {

        System.out.println("Creating User ");
        System.out.println("action======"+user.getAction());
        final String email=user.getEmail();
        JSONObject jobject = new JSONObject();
        // HttpHeaders headers = new HttpHeaders();
        try {
            jobject.put("status", "fail");

            if (user.getAction() == null) {
                if (user.getFormType().equals("Userform")) {

                    if (userService.isUserExist(email)) {
                        //System.out.println("A User with name " + user.getName() + " already exist");
                        jobject.put("status","registeredEmail"); return
                                jobject.toString(); }

                    System.out.println("new user save user....");
                    jobject = userService.saveUser(user);
                    // JSONObject jobject=new JSONObject();
                    System.out.println("in register" + jobject);
                    // headers.setLocation(ucBuilder.path("/Register/").buildAndExpand(user.get_id()).toUri());
                    System.out.println("in register");
                    return jobject.toString();
                } else {
                    System.out.println("inside Register============");
                    System.out.println("jobject===="+jobject);
                    jobject = userService.saveUser(user);
                    return jobject.toString();
                }
            } else {
                System.out.println("rest check coockies");
                final String status = "LogincheckCoockies";
                jobject.put("status", "fail");
                jobject = userService.checkSession(user, status);
                System.out.println("json data from rest check coockies-------" + jobject);
                return jobject.toString();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject.toString();
        }
    }

    // ------------------------------User
    // Profile--------------------------------------------

    @RequestMapping(value = "/UserProfile/", method = RequestMethod.POST)
    public String fetchUserdetails(@RequestBody final User user, final UriComponentsBuilder ucBuilder) {
        final String user_id = user.getUser_id();
        JSONObject jobject = new JSONObject(user);
        try {
            jobject.put("status", "fail");
            {
                jobject = userService.getUserDetails(user_id);
                return jobject.toString();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject.toString();
        }
    }
    // -------------------------------------User Profile Edit
    // ---------------------------------

    @RequestMapping(value = "/UserProfileEdit/", method = RequestMethod.POST)
    public String EditUserProfile(@RequestBody final User user, final UriComponentsBuilder ucBuilder) {

        System.out.println("Editing User Details in User Profile");
        JSONObject jobject = new JSONObject(user);
        System.out.println("user details in user profileEdit=====" + jobject.toString());
        try {
            jobject.put("status", "fail");
            {
                jobject = userService.EditUserProfile(user);
                return jobject.toString();
            }

        } catch (final Exception e) {
            e.printStackTrace();
            return jobject.toString();
        }
    }

    // ---------------------------------copy fin
    // plan----------------------------
    @RequestMapping(value = "/CopyPlan/", method = RequestMethod.POST)
    public String copyFinPlan(@RequestBody final FinPlan finPlan, final UriComponentsBuilder ucBuilder) {

        JSONObject jobject = new JSONObject();
        System.out.println(finPlan.getFormType());
        if (finPlan.getFormType().equals("changePlanName")) {
            try {
                jobject.put("status", "fail");
                final String planName = finPlan.getNewPlanname();
                if (userService.isPlanExist(planName, finPlan.getUser_id())) {
                    jobject.put("status", "Plan Name Exists");
                    System.out.println("Plan Name Exists");
                    return jobject.toString();
                } else {
                    jobject = userService.changePlanName(finPlan);
                    return jobject.toString();
                }
            } catch (final Exception e) {
                e.printStackTrace();
                return jobject.toString();
            }
        } else if (finPlan.getFormType().equals("deletePlan")) {
            jobject = userService.deleteFinPlan(finPlan);
            return jobject.toString();
        }
        else if (finPlan.getFormType().equals("deleteGoal")) {
            jobject = userService.deleteGoal(finPlan);
            return jobject.toString();
        }
        else if (finPlan.getFormType().equals("deleteIncomeProfile")) {
            jobject = userService.deleteIncomeProfile(finPlan.getProfile_name(), finPlan.getUser_id());
            return jobject.toString();
        } else {
            try {
                final String planName = finPlan.getNewPlanName();
                if (userService.isPlanExist(planName, finPlan.getUser_id())) {
                    jobject.put("status", "Plan Name Exists");
                    return jobject.toString();
                } else {
                    jobject = userService.copyFinPlan(finPlan);
                    return jobject.toString();
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
            return jobject.toString();
        }
    }
    // ---------------------------------Create New Financial
    // Plan------------------------------

    @RequestMapping(value = "/FinPlan/", method = RequestMethod.POST)
    public String createFinPlan(@RequestBody final FinPlan finPlan, final UriComponentsBuilder ucBuilder) {

        final String planName = finPlan.getPlan_name();
        JSONObject jobject = new JSONObject();
        try {
            jobject.put("status", "fail");
            if (finPlan.getFormType().equals("createNewPlan")) {
                if (userService.isPlanExist(planName, finPlan.getUser_id())) {
                    jobject.put("status", "Plan Name Exists");
                    return jobject.toString();

                } else {
                    jobject = userService.newFinPlan(finPlan);
                    return jobject.toString();
                }
            } else if (finPlan.getFormType().equals("showPlan")) {
                jobject = userService.showPlans(finPlan);
                return jobject.toString();
            } else if (finPlan.getFormType().equals("fetchPlanDetails")) {
                jobject = userService.showGoals(finPlan);
                return jobject.toString();
            } else if (finPlan.getFormType().equals("fetchGoalStatus")) {
                jobject = userService.showGoalStatus(finPlan);
                return jobject.toString();

            } else {
                return jobject.toString();
            }

        } catch (final Exception e) {
            e.printStackTrace();
            return jobject.toString();
        }
    }

    // -------------------------------------create retirement
    // goal-----------------------------

    @RequestMapping(value = "/retirementGoal/", method = RequestMethod.POST)
    public String createRetirementGoal(@RequestBody final RetirementGoal goal, final UriComponentsBuilder ucBuilder) {
        JSONObject jobject = new JSONObject();
        try {
            if (goal.getFormType().equals("onload")) {
                jobject = goalService.onLoad(goal);
                return jobject.toString();
            }
            jobject = goalService.saveGoalRetirement(goal);
            return jobject.toString();
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // -------------------------------------create Update and Edit House

    @RequestMapping(value = "/Goalhouse/", method = RequestMethod.POST)
    public String Goalhouse(@RequestBody final Housegoalmodel Housegoalmodel, final UriComponentsBuilder ucBuilder) {
        System.out.println("In MongoRest For House Goal Creation.....>>>>>>>>>>>>>>>........");
        JSONObject responseToConnection = new JSONObject();
        try {
            responseToConnection.put("status", "success");
            if (Housegoalmodel.getActionHomeType().equals("edit")) {
                responseToConnection = HouseGoalImpl.editHouseGoalData(Housegoalmodel);
            } else if (Housegoalmodel.getActionHomeType().equals("calhousevalue")) {
                responseToConnection = HouseGoalImpl.calHouseValue(Housegoalmodel);
            }
            else if(Housegoalmodel.getActionHomeType().equals("houseStatus"))
            {
                responseToConnection=HouseGoalImpl.houseStatus(Housegoalmodel);
            }
            else if(Housegoalmodel.getActionHomeType().equals("oldHouseRemaningMortgage"))
            {
                responseToConnection=HouseGoalImpl.oldHouseRemaningMortgageCal(Housegoalmodel);
            }
            else if(Housegoalmodel.getActionHomeType().equals("ownHouseRemaningMortgage"))
            {
                responseToConnection=HouseGoalImpl.ownHouseRemaningMortgageCal(Housegoalmodel);
            }
            else if (Housegoalmodel.getActionHomeType().equals("calInterestRate")) {

                responseToConnection = HouseGoalImpl.calHouseInterest(Housegoalmodel);
            } else {
                responseToConnection = HouseGoalImpl.insertHouseGoalData(Housegoalmodel);
            }
            return responseToConnection.toString();

        } catch (final Exception e) {
            e.printStackTrace();
            return responseToConnection.toString();
        }

    }

    // -------------------------------------create Update and Edit
    // Emergencyfund1 goal-----------------------------------------------

    @RequestMapping(value = "/GoalEmergencyFund1/", method = RequestMethod.POST)
    public String Emergencygoal(@RequestBody final Emergencyfundmodel emergencyFundMode1, final UriComponentsBuilder ucBuilder) {
        JSONObject responseToConnection = new JSONObject();
        try {
            responseToConnection.put("status", "success");
            if (emergencyFundMode1.getActionHomeType().equals("OnLoadEmergencyFund")) {
                responseToConnection = emergencyGoalimpl.onLoadEmergencyFund(emergencyFundMode1);

            } else if (emergencyFundMode1.getActionHomeType().equals("edit")) {
                responseToConnection = emergencyGoalimpl.editMarriageGoalData(emergencyFundMode1);
            }else if (emergencyFundMode1.getActionHomeType().equals("deleteGoal")) {
                responseToConnection = emergencyGoalimpl.deleteEmergencyGoalData(emergencyFundMode1);
            }
            else {
                responseToConnection = emergencyGoalimpl.insertEmergencyGoalData(emergencyFundMode1);
            }
            return responseToConnection.toString();
        } catch (final Exception e) {
            e.printStackTrace();
            return responseToConnection.toString();
        }

    }

    // -------------------------------------Report
    // goal-----------------------------------------------

    @RequestMapping(value = "/Report/", method = RequestMethod.POST)
    public String Reportdoc(@RequestBody final Reportmodel reportModel, final UriComponentsBuilder ucBuilder) {
        System.out.println("In MongoRest For Report  Creation.....>>>>>>>>>>>>>>>........");

        JSONObject responseToConnection = new JSONObject();
        try {
            responseToConnection.put("status", "success");
            if (reportModel.getActionHomeType() != null) {
                if (reportModel.getActionHomeType().equals("OnLoad")) {
                    responseToConnection = reportImpl.fetchingData(reportModel);
                } else if (reportModel.getActionHomeType().equals("goaldetails")) {
                    responseToConnection = reportImpl.fetchingGoalData(reportModel);
                }
            } else {
            }
            return responseToConnection.toString();
        } catch (final Exception e) {
            e.printStackTrace();
            return responseToConnection.toString();
        }

    }

    /*-------------------------------------create Update and Edit College Education goal-----------------------------------------------*/
    @RequestMapping(value = "/GoalCollegeEducation/", method = RequestMethod.POST)
    public String Goalhouse(@RequestBody final GoalCollegeEducation goalCollegeEducation, final UriComponentsBuilder ucBuilder) {

        JSONObject responseToConnection = new JSONObject();

        try {
            responseToConnection.put("status", "success");
            if (goalCollegeEducation.getActionHomeType().equals("edit")) {

                responseToConnection = goalCollegeEducationServiceImpl
                        .editGoalCollegeEducationData(goalCollegeEducation);

            } else if (goalCollegeEducation.getActionHomeType().equals("getYearArray")) {

                responseToConnection = goalCollegeEducationServiceImpl.calYearArray(goalCollegeEducation);

            } else if (goalCollegeEducation.getActionHomeType().equals("getKidData")) {

                responseToConnection = goalCollegeEducationServiceImpl.fetchKidData(goalCollegeEducation);

            }else if (goalCollegeEducation.getActionHomeType().equals("deleteGoal")) {

                responseToConnection = goalCollegeEducationServiceImpl.deleteGoal(goalCollegeEducation);

            }
            else if (goalCollegeEducation.getActionHomeType().equals("getPlan529Amount")) {

                responseToConnection = goalCollegeEducationServiceImpl.getPlan529Amount(goalCollegeEducation);

            }
            else {
                responseToConnection = goalCollegeEducationServiceImpl
                        .insertGoalCollegeEducationData(goalCollegeEducation);
            }

            return responseToConnection.toString();

        } catch (final Exception e) {
            e.printStackTrace();
            return responseToConnection.toString();
        }

    }

    // -------------------------------------create Update and Edit CAR
    // goal-----------------------------------------------

    @RequestMapping(value = "/Goalcar/", method = RequestMethod.POST)
    public String Goalcar(@RequestBody final Cargoalmodel Cargoalmodel, final UriComponentsBuilder ucBuilder) {

        JSONObject responseToConnection = new JSONObject();
        // HttpHeaders headers = new HttpHeaders();
        try {
            responseToConnection.put("status", "success");
            if (Cargoalmodel.getActionHomeType().equals("edit")) {

                responseToConnection = CarGoalImpl.editCarGoalData(Cargoalmodel);

            } else {
                responseToConnection = CarGoalImpl.insertCarGoalData(Cargoalmodel);

            }

            return responseToConnection.toString();
        } catch (final Exception e) {
            e.printStackTrace();
            return responseToConnection.toString();
        }

    }

    // -------------------------------------create Update and Edit Marriage goal-----------------------------------------------

    @RequestMapping(value = "/Goalmarriage/", method = RequestMethod.POST)
    public String Marriagegoal(@RequestBody final Marriagegoalmodel marriageGoalModel, final UriComponentsBuilder ucBuilder) {
        JSONObject responseToConnection = new JSONObject();
        try {
            if (marriageGoalModel.getActionHomeType().equals("edit")) {
                responseToConnection = marriageGoalimpl.editMarriageGoalData(marriageGoalModel);

            }
            else if(marriageGoalModel.getActionHomeType().equals("load"))
            {
                responseToConnection = marriageGoalimpl.loadMarriageGoalData(marriageGoalModel);
            }
            else {
                responseToConnection = marriageGoalimpl.insertMarriageGoalData(marriageGoalModel);
            }
            return responseToConnection.toString();
        } catch (final Exception e) {
            e.printStackTrace();
            return responseToConnection.toString();
        }

    }

    // -------------------------------------create Update and Edit customized
    // goal-----------------------------------------------

    @RequestMapping(value = "/Goalcustomized/", method = RequestMethod.POST)
    public String Goalcustomized(@RequestBody final Customizedgoalmodel customizedGoalModel, final UriComponentsBuilder ucBuilder) {

        JSONObject responseToConnection = new JSONObject();
        // HttpHeaders headers = new HttpHeaders();
        try {
            responseToConnection.put("status", "success");
            if (customizedGoalModel.getActionHomeType().equals("edit")) {
                responseToConnection = customizedGoalImpl.editCustomizedGoalData(customizedGoalModel);
            } else {
                responseToConnection = customizedGoalImpl.insertCustomizedGoalData(customizedGoalModel);

            }
            return responseToConnection.toString();

        } catch (final Exception e) {
            e.printStackTrace();
            return responseToConnection.toString();
        }

    }

    // -------------------------------------create Update and Edit raising a kid
    // goal-----------------------------------------------

    @RequestMapping(value = "/Kidgoal/", method = RequestMethod.POST)
    public String goalKid(@RequestBody final KidGoalModel kidGoalModel, final UriComponentsBuilder ucBuilder) {
        JSONObject responseToConnection = new JSONObject();
        try {
            responseToConnection.put("status", "success");
            if (kidGoalModel.getActionHomeType().equals("edit")) {
                responseToConnection = kidGoalImpl.editKidGoalData(kidGoalModel);
            }
            else if (kidGoalModel.getActionHomeType().equals("startyear")) {

                responseToConnection = kidGoalImpl.calculateKidCost(kidGoalModel);
            }
            else {
                responseToConnection = kidGoalImpl.insertKidGoalData(kidGoalModel);
            }
            return responseToConnection.toString();

        } catch (final Exception e) {
            e.printStackTrace();
            return responseToConnection.toString();
        }

    }

    // ----------------------------create a goal
    // Vacation--------------------------------
    @RequestMapping(value = "/vacation/", method = RequestMethod.POST)
    public String vacation(@RequestBody final ModelVacation vacation, final UriComponentsBuilder ucBuilder) {
        JSONObject jobject = new JSONObject();
        jobject = vacationService.saveVacationGoal(vacation);
        return jobject.toString();
    }

    /*-----------------------------------LogOut----------------------------------*/

    @RequestMapping(value = "/logout/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String logout(@RequestBody final User user) {
        JSONObject jobject = new JSONObject();
        jobject = userService.logOutUser(user);
        return jobject.toString();
    }



    // --------------------change password--------------------------------
    @RequestMapping(value = "/changePassword/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String changePassword(@RequestBody final User user) {

        final JSONObject responseFromChangePassword = new JSONObject();
        try {
            responseFromChangePassword.put("status", "fail");
            final String newPassword = user.getNewPassword();
            final String oldPassword = user.getOldPassword();
            final String user_id = user.getUser_id();
            // -------------Encode password------------------------------------

            final String encodedPassword = userService.encryptPassword(newPassword);
            final String encodedOldPassword = userService.encryptPassword(oldPassword);

            final boolean checkOldPassword = changePasswordService.checkUserPassword(user_id, encodedOldPassword);
            if (checkOldPassword == true) {

                // --------------insert password in
                // db---------------------------
                final boolean responseFrominsertPassword = changePasswordService.insertRandomPassword(user_id,
                        encodedPassword);
                if (responseFrominsertPassword == true) {
                    responseFromChangePassword.put("Massage", "Password Successfully Changed");
                    final boolean responseFromLogOUt = changePasswordService.logOutUser(user_id);
                    if (responseFromLogOUt == true) {
                        responseFromChangePassword.put("status", "success");
                        return responseFromChangePassword.toString();
                    } else {
                        return responseFromChangePassword.toString();
                    }
                } else {
                    return responseFromChangePassword.toString();
                }
            } else {
                responseFromChangePassword.put("status", "passwordMismatched");
                return responseFromChangePassword.toString();
            }
        } catch (final Exception e) {

            e.printStackTrace();
        }

        return responseFromChangePassword.toString();
    }

    /*-----------------------------------------------Reset Password-----------------------------------------------*/

    @RequestMapping(value = "/resetPassword/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String resetPassword(@RequestBody final User user) {

        final JSONObject responseForResetPassword = new JSONObject();
        try {
            responseForResetPassword.put("status", "fail");
            /*--------------checking Email ID already registered or not------------*/
            if (!userService.isUserExist(user.getEmail())) {
                responseForResetPassword.put("status", " email address not registered");
                return responseForResetPassword.toString();
            }

            final PasswordBuilder randomPassword = new PasswordBuilder(10);

            final String password = new String(randomPassword.get());

            final String encodedPassword = userService.encryptPassword(password);

            boolean responseFromsendFromGMail = false;
            if (user.getFormType().equals("admin")) {
                responseFromsendFromGMail = resetPassword.sendGMailFromAdmin(user.getEmail(), password);
            } else {
                responseFromsendFromGMail = resetPassword.sendFromGMail(user.getEmail(), password);
            }
            if (responseFromsendFromGMail == true) {

                final boolean responseFrominsertRandomPassword = resetPassword.insertRandomPassword(user, encodedPassword);
                if (responseFrominsertRandomPassword == true) {
                    responseForResetPassword.put("status", "success");

                } else {
                    return responseForResetPassword.toString();
                }
            } else {
                return responseForResetPassword.toString();
            }

            /*--------------------------- killing Session ------------------------------*/

            final boolean responseFromkillingSession = resetPassword.killingSession(user);
            if (responseFromkillingSession == true) {
                responseForResetPassword.put("status", "success");

            }
        } catch (final Exception e) {

            e.printStackTrace();
        }

        return responseForResetPassword.toString();
    }

    /*-----------------------------------------Income Profile Operations--------------------------------------------*/

    @RequestMapping(value = "/incomeProfile/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String incomeProfile(@RequestBody final IncomeProfile dataFormServlet) {

        JSONObject responseToServlet = new JSONObject();
        try {

            if (dataFormServlet.getActionType().equals("create")) {
                responseToServlet = incomeProfileService.createIncomeProfile(dataFormServlet);
                //	responseToServlet = incomeProfileService.updateIncomeProfile(dataFormServlet);
            } else if (dataFormServlet.getActionType().equals("update")) {
                responseToServlet = incomeProfileService.updateIncomeProfile(dataFormServlet);
            } else if (dataFormServlet.getActionType().equals("getIncomeProfile")) {
                responseToServlet = incomeProfileService.getIncomeProfile(dataFormServlet);
            } else if (dataFormServlet.getActionType().equals("saveExpenses")) {
                responseToServlet = incomeProfileService.saveExpenses(dataFormServlet);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return responseToServlet.toString();
    }

    @RequestMapping(value = "/adminDashboard/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String totalUsersCount(@RequestBody final AdmindashboardModel dataAdmin) {

        final JSONObject responseToServlet = new JSONObject();
        try {
            if (dataAdmin.getActionType().equals("sendMail")) {
                final JSONObject responseToadmin = admindashboardService.sendFromGMail(dataAdmin.getEmail_id(),
                        dataAdmin.getSubject(), dataAdmin.getMessage());

                return responseToadmin.toString();
            }

            if (dataAdmin.getActionType().equals("usercount")) {

                final JSONObject responseToadmin = admindashboardService.regiteredUserCount();
                return String.valueOf(responseToadmin);
            }

            else if (dataAdmin.getActionType().equals("visitorcount")) {

                final JSONObject responseTovisitorsession = admindashboardService.visitorCount();
                return String.valueOf(responseTovisitorsession);
            } else if (dataAdmin.getActionType().equals("goalcount")) {
                final JSONObject responseTovgoalcount = admindashboardService.goalCount();
                return String.valueOf(responseTovgoalcount);
            } else if (dataAdmin.getActionType().equals("plancount")) {
                final JSONObject responseTovplancount = admindashboardService.planCount();
                return String.valueOf(responseTovplancount);
            } else if (dataAdmin.getActionType().equals("incomecount")) {
                final JSONObject responseTovplancount = admindashboardService.incomeCount();
                return String.valueOf(responseTovplancount);
            } else if (dataAdmin.getActionType().equals("totalusedmemory")) {
                final JSONObject responseToUsedMemory = admindashboardService.totalUsedMemory();
                return String.valueOf(responseToUsedMemory);
            } else if (dataAdmin.getActionType().equals("totalmemory")) {

                final JSONObject responseToTotalMemory = admindashboardService.totalMemory();

                return String.valueOf(responseToTotalMemory);
            } else if (dataAdmin.getActionType().equals("freememory")) {

                final JSONObject responseToFreeMemory = admindashboardService.freeMemory();

                return String.valueOf(responseToFreeMemory);
            } else if (dataAdmin.getActionType().equals("userdocument")) {

                final JSONObject responseToUserDetail = admindashboardService.userDetailDocument();

                return String.valueOf(responseToUserDetail);
            } else if (dataAdmin.getActionType().equals("logMessage")) {

                final JSONObject responseToLogmesg = admindashboardService.logMessageDocument();
                return String.valueOf(responseToLogmesg);

            } else if (dataAdmin.getActionType().equals("Statistics")) {

                final JSONObject responseToLogmesg = admindashboardService.getStatistics(dataAdmin);
                return String.valueOf(responseToLogmesg);

            } else if (dataAdmin.getActionType().equals("Exsistinguser")) {
                JSONObject responseToAddAdmin = new JSONObject();
                if (userService.isUserExist(dataAdmin.getEmail_id())) {
                    responseToAddAdmin = admindashboardService.existingUser(dataAdmin);
                    return responseToAddAdmin.toString();
                } else {
                    responseToAddAdmin.put("status", "fail");
                    responseToAddAdmin.put("message", "email not registered!!");
                    return responseToAddAdmin.toString();
                }

            }

            else if (dataAdmin.getActionType().equals("Addnewuser")) {
                final JSONObject emiluser = new JSONObject();
                if (userService.isUserExist(dataAdmin.getEmailAdress())) {
                    emiluser.put("status", "email address is already registered");
                    return emiluser.toString();
                }

                final PasswordBuilder randomPassword = new PasswordBuilder(10);
                final String password = new String(randomPassword.get());
                final String encodedPassword = userService.encryptPassword(password);

                dataAdmin.setPassword(encodedPassword);
                final JSONObject responseToAddNewAdmin = admindashboardService.addAdmin(dataAdmin);

                if (responseToAddNewAdmin.get("status").equals("sucess")) {
                    resetPassword.sendGMailFromAdmin(dataAdmin.getEmailAdress(), password);
                }
                return responseToAddNewAdmin.toString();
            } else if (dataAdmin.getActionType().equals("edituserprofile")) {
                final JSONObject responseToEditProfile = admindashboardService.editProfile(dataAdmin);
                return responseToEditProfile.toString();

            } else if (dataAdmin.getActionType().equals("onloadEditProfile")) {
                final JSONObject responseToOnloadEditProfile = admindashboardService.editOnloadProfile(dataAdmin);
                return responseToOnloadEditProfile.toString();

            } else if (dataAdmin.getActionType().equals("serverOnload")) {
                final JSONObject responseToOnloadEditProfile = admindashboardService.onloadServerConfiguration(dataAdmin);
                return responseToOnloadEditProfile.toString();

            } else if (dataAdmin.getActionType().equals("serverConfiguration")) {
                final JSONObject responseToServerConfig = admindashboardService.serverConfig(dataAdmin);
                return responseToServerConfig.toString();
            }

            else if (dataAdmin.getActionType().equals("houseGoalIds")) {

                final JSONObject responseTohousemails = admindashboardService.goalTypeHouse(dataAdmin);
                return responseTohousemails.toString();
            } else if (dataAdmin.getActionType().equals("applicationConfiguration")) {
                JSONObject responseToadmin = new JSONObject();
                final JSONArray emailJSON = new JSONArray(dataAdmin.getEmail_id());
                for (int i = 0; i < emailJSON.length(); i++) {

                    responseToadmin = admindashboardService.sendFromGMail(emailJSON.getString(i),
                            "Reminding mail for your goal .... OLAHTEK", dataAdmin.getMessage());

                }
                return responseToadmin.toString();
            }

            else if (dataAdmin.getActionType().equals("deleteUser")) {

                final JSONObject responseTodeleteUser = admindashboardService.deletUser(dataAdmin);

                return responseTodeleteUser.toString();

            }
            /*else if (dataAdmin.getActionType().equals("statehomevalue")) {

				JSONObject responseTostatehomevalue = admindashboardService.statehomevalue(dataAdmin);

				return responseTostatehomevalue.toString();

			}
			else if (dataAdmin.getActionType().equals("rateadjustmentvalue")) {

				JSONObject responseTorateadjustmentvalue = admindashboardService.rateadjustmentvalue(dataAdmin);

				return responseTorateadjustmentvalue.toString();

			}
			else if (dataAdmin.getActionType().equals("collegevalue")) {

				JSONObject responseTocollegevalue = admindashboardService.collegevalue(dataAdmin);

				return responseTocollegevalue.toString();

			}*/

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return responseToServlet.toString();

    }
    /*-----------------------------------------Investment Portfolio Operations--------------------------------------------*/

    @RequestMapping(value = "/investmentPortfolio/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String investmentPortfolio(@RequestBody final InvestmentPortfolioModel dataFormServlet,
            final UriComponentsBuilder ucBuilder) {
        JSONObject responseToServlet = new JSONObject();
        try {

            System.out.println("dataFormServlet.getFormType()-->>  "+dataFormServlet.getFormType());
            if(dataFormServlet.getFormType().equals("initialSubmit"))
            {
                System.out.println("helloworld--->>" +dataFormServlet.getRiskScore());
                investmentPortfolio.duringRegistration(dataFormServlet.getUser_id(),dataFormServlet.getRiskScore());
                final int riskScore =dataFormServlet.getRiskScore();
                final int riskFactor=dataFormServlet.getRiskFactor();
                final int age=dataFormServlet.getAge();
                System.out.println("Bala  Age:"+age+"riskScore  :"+riskScore+"riskFactor"+riskFactor);
                final InvestmentPortfolioModel investmentPortfolio=MongoDBConnection.investmentPortfolioData;
                //InvestmentPortfolioImpl.growthRate(riskScore, riskFactor, age, investmentPortfolio);
                responseToServlet.put("growthRate", InvestmentPortfolioImpl.growthRate(riskScore, riskFactor, age, investmentPortfolio));


            }
            else if(dataFormServlet.getFormType().equals("duringRegistration"))
            {
                System.out.println("durrigs-->> ");
                investmentPortfolio.duringRegistration(dataFormServlet.getUser_id(),dataFormServlet.getRiskScore());

            }

            else if(dataFormServlet.getFormType().equals("calulateBasedonGrowthRate"))
            {
                System.out.println("cail-->> ");

                final Double growthRate = dataFormServlet.getGrowthRate();
                final Double portfolioDividend = dataFormServlet.getPortfolioDividend();
                final Double portfolioInterest = dataFormServlet.getPortfolioInterest();
                final String filingStatusPort=dataFormServlet.getFilingStatusPort();
                System.out.println("filingStatusPort in helooworld==============="+filingStatusPort);


                responseToServlet = investmentPortfolio.calInvestmentPortfolioIncomeProfile(dataFormServlet.getUser_id(), "constant_income",growthRate,portfolioDividend,portfolioInterest,filingStatusPort);

            }
            else

            {
                if (dataFormServlet.getPlan_name() == null || dataFormServlet.getPlan_name().equals("undefined")) {
                    System.out.println("khatriiiiiiii called");
                    //responseToServlet = investmentPortfolio.calInvestmentPortfolioIncomeProfile(dataFormServlet.getUser_id(), "constant_income",dataFormServlet.getRiskScore());
                    final int riskScore =dataFormServlet.getRiskScore();
                    final int riskFactor=dataFormServlet.getRiskFactor();
                    System.out.println("RiskFactor---->>> "+riskFactor);
                    final double age=0;
                    final InvestmentPortfolioModel investmentPortfolio=MongoDBConnection.investmentPortfolioData;
                    InvestmentPortfolioImpl.growthRate(riskScore, riskFactor, age, investmentPortfolio);
                    responseToServlet.put("growthRate", InvestmentPortfolioImpl.growthRate(riskScore, riskFactor, age, investmentPortfolio));

                    System.out.println("if---->>> "+responseToServlet);
                } else {
                    //	responseToServlet = investmentPortfolio.calInvestmentPortfoilo(dataFormServlet.getUser_id(),dataFormServlet.getPlan_name(), dataFormServlet.getRiskScore());

                    System.out.println("els if---->>> "+responseToServlet);

                }
            }
        } catch (final Exception e) {

            System.out.println("exception------------>>>>>>>>>");
            e.printStackTrace();


        }
        System.out.println( "helloworld response is--->>>  "+responseToServlet);
        return responseToServlet.toString();
    }


    //------------------------------SSB Calculator--------------------------------------------------

    @RequestMapping(value = "/SSB/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String ssbCalculator(@RequestBody final SSB ssb) {
        JSONObject jobject = new JSONObject();
        try {
            if (ssb.getAction() != null) {
                if (ssb.getAction().equals("fetchIncomeProfiles")) {
                    jobject = ssbCalculator.fetchIncomeProfile(ssb);
                    return jobject.toString();
                }
                else if (ssb.getAction().equals("calculateSSBSingle")) {
                    jobject = ssbCalculator.calculateFinalSSB(ssb);
                    return jobject.toString();
                }
                else if (ssb.getAction().equals("calculateSSBMarriedWorking")) {
                    jobject = ssbCalculator.calculateFinalSSB(ssb);
                    return jobject.toString();
                }
                else if (ssb.getAction().equals("calculateSSBMarriedNotWorking")) {
                    jobject = ssbCalculator.calculateFinalSSB(ssb);
                    return jobject.toString();
                }
                else if(ssb.getAction().equals("calculateFromProfile"))
                {
                    jobject = ssbCalculator.calculateSSBFromProfile(ssb);
                    return jobject.toString();
                }
                else {
                    return jobject.toString();
                }
            } else {
                jobject.put("status", "fail");
                return jobject.toString();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return jobject.toString();
        }
    }

    //--------------------------------FDICalculator----------------------------------------------//

    @RequestMapping(value = "/FDICalculator/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String fdiCalculator(@RequestBody final FDI fdi) {
        final JSONObject fdiobject = new JSONObject();

        try
        {

            System.out.println("iam in rest-->>>  "+fdi.getAction());

            /*			return result.toString();
             */

            if(fdi.getAction()!=null){

                System.out.println("In HelloWorld Rest COntroller");

                final double 	Gross_Annual_Income=fdi.getGross_Annual_Income();
                System.out.println("Gross_Annual_Income is  " + Gross_Annual_Income);
                final String  state=fdi.getState();
                System.out.println("state is"+state);
                System.out.println();
                final String fillingStatus=fdi.getFilling_Status();
                System.out.println("fillingStatus is"+fillingStatus);
                final double federaltax=0;
                final int noOfExcemtion=fdi.getPersonal_Exemption();
                System.out.println("noOfExcemtion"+noOfExcemtion);
                final double taxableIncome=0;
                final int currentYear=0;
                final int kidNOAge=0;
                // StatetaxModel stateTaxValue=null;
                final String maritalStatus="No";
                final JSONArray childArray=null;
                final double taxableInvestmentAmount=0;
                final double nonTaxableInvestmentAmount=0;
                final int spouseStartYear=0;
                final int userStartYear=0;
                final double income=Gross_Annual_Income;
                final String user_id=fdi.getUser_id();
                final String fin_id=null;
                final double userIncome=Gross_Annual_Income;
                final double spouseIncome=0;
                final double federalTax=0;
                final double fICAMedicareTa=0;
                final int year=0;
                final int userAge=0;
                final int spouseAge=0;
                final JSONArray kidBirthYear=null;
                final int kidcount=0;
                final int registrationYear=0;

                final double houseValue=0;
                final JSONArray housing_expense=null;
                final long startYear=0;
                final double remainingMortgageOriginal=0;
                final double  remainingMortgageInterestRate=0;
                final double  houseAppreciationRate=0;
                final double minimumwithdrawal=0;
                final double fICAMedicareTax=0;
                final double sateTax=0;
                final double Medicaretax=0;
                final double    MaxSTDItem =0;

                final StatetaxModel stateTaxValue = MongoDBConnection.stateTaxColl.findOne("{'statename':#}", state)
                        .as(StatetaxModel.class);
                final User login = MongoDBConnection.userColl.findOne("{_id:#}", fdi.getUser_id()).as(User.class);
                String houseStatus=null;
                String registrationHouseStatus=null;
                if(login!=null)
                {
                    houseStatus=login.getHouseStatus();
                    registrationHouseStatus=houseStatus;
                }




                System.out.println(" vijay vaules are"+federaltax+" "+state+"");


                CalculationEngine.federalTaxParamCal(income, state, fillingStatus, noOfExcemtion, currentYear, stateTaxValue, maritalStatus, childArray, houseStatus, userIncome, spouseIncome, user_id);
                //  public static JSONObject calTaxPerYear(int spouseStartYear, int userStartYear, double income, String user_id,String fin_id, double userIncome, double spouseIncome, int year, int noOfExcemtion, String fillingStatus,int userAge, int spouseAge, String maritalStatus, JSONArray kidBirthYear, String state, int kidcount,int registrationYear, JSONArray childArray, String houseStatus, double houseValue,JSONObject housing_expense, long startYear, double remainingMortgageOriginal,double remainingMortgageInterestRate, double minimumwithdrawal, StatetaxModel stateTaxValue,double taxableInvestmentAmount,double nonTaxableInvestmentAmount,double Gross_Annual_Income ) {
                //  System.out.println(	"calTaxPerYear values are"+ CalculationEngine.calTaxPerYear(spouseStartYear, userStartYear, income, user_id, fin_id, userIncome, spouseIncome, year, noOfExcemtion, fillingStatus, userAge, spouseAge, maritalStatus, kidBirthYear, state, kidcount, registrationYear, childArray, houseStatus, houseValue, housing_expense, startYear, remainingMortgageOriginal, remainingMortgageInterestRate, minimumwithdrawal, stateTaxValue, taxableInvestmentAmount, nonTaxableInvestmentAmount));
                fdiobject.put("federal", CalculationEngine.calTaxPerYear(spouseStartYear, userStartYear, income, user_id, fin_id, userIncome, spouseIncome, year, noOfExcemtion, fillingStatus, userAge, spouseAge, maritalStatus, kidBirthYear, state, kidcount, registrationYear, childArray, houseStatus, houseValue, housing_expense, startYear, remainingMortgageOriginal, remainingMortgageInterestRate, minimumwithdrawal, stateTaxValue, taxableInvestmentAmount, nonTaxableInvestmentAmount,null,null,houseAppreciationRate,registrationHouseStatus));
                //fdiobject.put("federal",CalculationEngine.federalTaxParamCal(income, state, fillingStatus, noOfExcemtion, currentYear, stateTaxValue, maritalStatus, childArray, houseStatus, userIncome, spouseIncome, user_id));

                fdiobject.put("income", Gross_Annual_Income);


                fdiobject.put("status", "success");




                System.out.println("json data from rest-------" + fdiobject);




                return fdiobject.toString();
            }
            else{
                System.out.println("Nothing.....");
                fdiobject.put("status", "fail");
            }


        }
        catch (final Exception e) {
            e.printStackTrace();

        }
        return fdiobject.toString();
    }



    //--------------------------------MortgageCalculator----------------------------------------------//

    @RequestMapping(value = "/MC/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String mCalculator(@RequestBody final MC mc) {
        JSONObject mobject = new JSONObject();
        try
        {




            System.out.println("MC"+mc);

            if(mc.getAction().equals("edit1")){

                System.out.println("In HelloWorld Rest COntroller");
                mobject=mCalculator.calculateMC(mc);
                System.out.println("json data from rest-------" + mobject);
                return mobject.toString();
            }
            else if(mc.getAction().equals("getHomeInsurance")){

                System.out.println("In HelloWorld Rest COntroller");
                mobject=mCalculator.getHomeInsurance(mc);
                System.out.println("json data from rest-------" + mobject);
                return mobject.toString();
            }

            else if(mc.getAction().equals("getHomeValue")){
                System.out.println("In HelloWorld Rest COntroller1");
                mobject=mCalculator.calHouseValue(mc);
                System.out.println("json data from rest1-------" + mobject);
                return mobject.toString();
            }

            else if(mc.getAction().equals("getLoanTerm")){
                //System.out.println("In HelloWorld Rest COntroller1");
                mobject=mCalculator.getLoanTerm(mc);
                System.out.println("json data from rest1-------" + mobject);
                return mobject.toString();
            }

            else{
                System.out.println("Nothing.....");
            }


        }
        catch (final Exception e) {
            e.printStackTrace();

        }
        return mobject.toString();
    }


    //----------------------------CarLoanCalculator--------------------------------------//


    @RequestMapping(value = "/CarLoanCalculator/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String clCalculator(@RequestBody final CarLoanCalculator clc) {
        JSONObject clcobject = new JSONObject();
        try
        {




            System.out.println("MC"+clc);

            if(clc.getAction().equals("edit1")){

                System.out.println("In HelloWorld Rest COntroller");
                clcobject=clCalculator.calculateCLC(clc);
                System.out.println("json data from rest-------" + clcobject);
                return clcobject.toString();
            }
            /*else if(mc.getAction().equals("getHomeInsurance")){

			System.out.println("In HelloWorld Rest COntroller");
	        mobject=mCalculator.getHomeInsurance(mc);
			System.out.println("json data from rest-------" + mobject);
			return mobject.toString();
		}*/
            else{
                System.out.println("CLC..BALA...");
            }


        }
        catch (final Exception e) {
            e.printStackTrace();

        }
        return clcobject.toString();
    }

    //----------------------------CarLeaseCalculator--------------------------------------//


    @RequestMapping(value = "/CarLeaseCalculator/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String cleCalculator(@RequestBody final CarLeaseCalculator clec) {
        JSONObject clecobject = new JSONObject();
        try
        {




            System.out.println("MC"+clec);

            if(clec.getAction().equals("edit1")){

                System.out.println("In HelloWorld Rest COntroller");
                clecobject=cleCalculator.calculateCLEC(clec);
                System.out.println("json data from rest-------" + clecobject);
                return clecobject.toString();
            }
            /*else if(mc.getAction().equals("getHomeInsurance")){

			System.out.println("In HelloWorld Rest COntroller");
	        mobject=mCalculator.getHomeInsurance(mc);
			System.out.println("json data from rest-------" + mobject);
			return mobject.toString();
		}*/
            else{
                System.out.println("CLEC..BALA...");
            }


        }
        catch (final Exception e) {
            e.printStackTrace();

        }
        return clecobject.toString();
    }
}
