package com.mongorest.olahtek.service;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongorest.olahtek.model.Emergencyfundmodel;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.SpouseIncome;
import com.mongorest.olahtek.model.IncomeProfile;
import com.mongorest.olahtek.model.InvestmentPortfolioModel;
import com.mongorest.olahtek.model.User;
@Service("investmentPortfolio")
@Transactional
public class InvestmentPortfolioImpl implements InvestmentPortfolio {
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
	DecimalFormat decimalFloat = new DecimalFormat("#.##");
	ObjectMapper jsonMapper = new ObjectMapper();
	@JsonCreator
	@Override
	public JSONObject calInvestmentPortfolioIncomeProfile(String user_id,String profileName,Double growthRate,Double portfolioDividend,Double portfolioInterest,String filingStatusPort)
	{
		JSONObject responseObj=new JSONObject();
		try {
			IncomeProfile incomeProfileCollData=MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}",user_id,profileName).as(IncomeProfile.class);
			User user = MongoDBConnection.userColl.findOne("{'_id':#}", user_id).as(User.class);
			String kidCostCalculatedarr=user.getKidCostCalculatedArray();
			long kidCostCalculated=user.getKidCostCalculated();
			int userAge=user.getAge();
			JSONObject incomeProfileJson=new JSONObject(jsonMapper.writeValueAsString(incomeProfileCollData));
			int spouseAge=0;
			String maritalStatus=user.getMarital_status();
			/*String maritalStatus="No";
			if(filingStatusPort.equals("Married Filing Jointly"))
			{
				maritalStatus="Yes";
			}
			*/
			//////System.out.println("hello ..."+filingStatusPort+"marital //status"+maritalStatus);

			JSONArray userIncomes=incomeProfileJson.getJSONArray("user_income");
			JSONArray temp1=incomeProfileJson.getJSONArray("user_income");
	//		////System.out.println("hello ::"+userIncomes);
			JSONArray income=new JSONArray();
			JSONArray spouse_income=new JSONArray();
			if (maritalStatus.equals("Yes")) {
				spouseAge=user.getSpouseAge();
				income=incomeProfileJson.getJSONArray("combined_income");
				spouse_income=incomeProfileJson.getJSONArray("spouse_income");
				if(filingStatusPort.equals("Single")){
				spouse_income=new JSONArray();
				income=userIncomes;
				}

			} else {
				////System.out.println("hello in else part");
				//income=incomeProfileJson.getJSONArray("user_income");
				////System.out.println("hello asdhbh1::"+income);
				if(filingStatusPort.equals("Married Filing Jointly")){
					///spouse_income=incomeProfileJson.getJSONArray("user_income");
					JSONArray temp =new JSONArray();
					temp=temp1;
					for(int i=0;i<temp.length();i++)
					{
						////System.out.println("obj"+temp.getJSONObject(i));
						temp.getJSONObject(i).put("value", 0);
		//				////System.out.println("obj //after"+temp.getJSONObject(i));
					}
					spouse_income=new JSONArray();
					////System.out.println("hello temp::"+temp);
					spouse_income=temp;
					spouseAge=userAge;
				}
				incomeProfileJson=new JSONObject(jsonMapper.writeValueAsString(incomeProfileCollData));
				userIncomes=incomeProfileJson.getJSONArray("user_income");
				income=incomeProfileJson.getJSONArray("user_income");
				//income=userIncomes;
			}

	//		////System.out.println("hello asdhbh::"+spouse_income);

			int SpouseStartYear=0;
			int userStartYear=0;
			userStartYear=user.getBirthYear()+70;
			if( user.getMarital_status().equals("Yes")){
				SpouseStartYear=user.getSpouseBirthYear()+70;
				if(filingStatusPort.equals("Single")){

					maritalStatus="No";
				}
			}
			else{
				if(filingStatusPort.equals("Married Filing Jointly")){
				SpouseStartYear=userStartYear;
				maritalStatus="Yes";



				}

			}
			JSONObject retirementData=new JSONObject();
			retirementData.put("spouseStartYear", SpouseStartYear);
			retirementData.put("userStartYear", userStartYear);
			RetirementGoal retirementObj=null;
			JSONArray userExpense=incomeProfileJson.getJSONArray("userExpense");
			JSONArray oldfillingExemtion = new JSONArray();
			String[] out = user.getCreatedTs().split("/");
			int registrationYear = Integer.parseInt(out[0]);
			int noOfExcemtion = 1 + user.getChildCount();
			int year = incomeProfileJson.getJSONArray("user_income").getJSONObject(0).getInt("year");
			if (user.getMarital_status().equals("Yes")) {
				noOfExcemtion = noOfExcemtion + 1;
				if (user.getFilingStatus().equals("Head of Household")) {
					noOfExcemtion = noOfExcemtion + user.getDependants() - 1;
				} else if (user.getFilingStatus().equals("Married Filing Separately")) {
					noOfExcemtion = noOfExcemtion - 1;
				} else if (user.getFilingStatus().equals("Qualified Widow")) {
					noOfExcemtion = noOfExcemtion - 1;
				}
			}
			JSONArray childArray=new JSONArray();
			JSONObject userDetailsFromDB = new JSONObject(jsonMapper.writeValueAsString(user));
			for(int i=0;i<incomeProfileJson.getJSONArray("assests").length();i++)
			{
				int noOfExcemtion1 = noOfExcemtion;
				int kids=0;
				if (user.getChildCount() != 0) {
					childArray = userDetailsFromDB.getJSONArray("childs");
					for (int k = 0; k < user.getChildCount(); k++) {
						if (year - (registrationYear - childArray.getJSONObject(k).getInt("age")) > 18) {
							kids++;
							if ((childArray.getJSONObject(k).getString("flag").equals("Yes") && year
									- (registrationYear - childArray.getJSONObject(k).getInt("age")) > 24)
									|| (!childArray.getJSONObject(k).getString("flag").equals("Yes"))) {
								noOfExcemtion1 = noOfExcemtion1 - 1;
							}
						}
					}
				}
				year++;
				JSONObject obj=new JSONObject();
				UserServiceImpl x=new UserServiceImpl();
				double kidExpenseReduction=x.reduceChildExpense(income.getJSONObject(i).getDouble("value"), userExpense.getJSONObject(i).getDouble("nonHousingExpense"), kids,new JSONArray(kidCostCalculatedarr),user.getKidcostFactor());
				userExpense.getJSONObject(i).put("nonHousingExpense", userExpense.getJSONObject(i).getDouble("nonHousingExpense")-kidExpenseReduction);
				userExpense.getJSONObject(i).put("totalExpense", userExpense.getJSONObject(i).getDouble("nonHousingExpense")+userExpense.getJSONObject(i).getDouble("housingExpense"));
				if(userExpense.getJSONObject(i).getDouble("nonHousingExpense")<0)
				{
					userExpense.getJSONObject(i).put("nonHousingExpense", 0);
				}
				obj.put("fillingStatus", user.getFilingStatus());
				obj.put("noOfExcemtion", noOfExcemtion1);
				obj.put("year",incomeProfileJson.getJSONArray("assests").getJSONObject(i).getInt("year"));
				oldfillingExemtion.put(obj);
			}
			for (int i=0;i<incomeProfileJson.getJSONArray("assests").length();i++)
			{
				userExpense.getJSONObject(i).put("totalExpense", (double)userExpense.getJSONObject(i).getInt("totalExpense"));
				userExpense.getJSONObject(i).put("registerNonHousingExpense", (double)userExpense.getJSONObject(i).getInt("registerNonHousingExpense"));
				userExpense.getJSONObject(i).put("housingExpense", (double)userExpense.getJSONObject(i).getInt("housingExpense"));
				userExpense.getJSONObject(i).put("nonHousingExpense", (double)userExpense.getJSONObject(i).getInt("nonHousingExpense"));
				userExpense.getJSONObject(i).put("mortgageExpense", (double)userExpense.getJSONObject(i).getInt("mortgageExpense"));
				userExpense.getJSONObject(i).put("afterMarriageExpense", (double)userExpense.getJSONObject(i).getInt("afterMarriageExpense"));
				userExpense.getJSONObject(i).put("registerHousingExpense", (double)userExpense.getJSONObject(i).getInt("registerHousingExpense"));
				userExpense.getJSONObject(i).put("kidExpense", (double)userExpense.getJSONObject(i).getInt("kidExpense"));
			}
		//	////System.out.println("limits //ahsbd"+incomeProfileJson.getJSONArray("limits"));

			if(filingStatusPort.equals("Married Filing Jointly")){
				for(int i=0;i<oldfillingExemtion.length();i++){

					oldfillingExemtion.getJSONObject(i).put("fillingStatus", "Married Filing Jointly");
					if(oldfillingExemtion.getJSONObject(i).getInt("noOfExcemtion")<2)
					oldfillingExemtion.getJSONObject(i).put("noOfExcemtion", oldfillingExemtion.getJSONObject(i).getInt("noOfExcemtion")+1);
				}
			}
			else
			{
				for(int i=0;i<oldfillingExemtion.length();i++){

					oldfillingExemtion.getJSONObject(i).put("fillingStatus", "Single");
					if(oldfillingExemtion.getJSONObject(i).getInt("noOfExcemtion")>1)
					oldfillingExemtion.getJSONObject(i).put("noOfExcemtion", oldfillingExemtion.getJSONObject(i).getInt("noOfExcemtion")-1);
				}
			}
			////System.out.println("Hello income"+income);
			////System.out.println("Hello userIncomes"+userIncomes);
			JSONObject result=CalculationEngine.sweepingOfMoneyInvestmentPortfolio1(userIncomes,null,income,spouse_income, userExpense, incomeProfileJson.getJSONArray("limits"), maritalStatus, incomeProfileJson.getJSONArray("assests"), incomeProfileJson.getJSONArray("tax"),user_id, oldfillingExemtion,userAge,spouseAge,

					0,  false, null, null, retirementData, retirementObj, null,null,null,null,false,0,growthRate,portfolioDividend,portfolioInterest);
			if(result.getString("status").equals("success"))
			{

			responseObj.put("statusMsg", "Goal Applied successfully");
//////System.out.println("Bala the output is"+result);
			responseObj.put("assests", result.getJSONArray("assets")).put("tax",result.getJSONArray("tax")).put("userExpense",incomeProfileJson.getJSONArray("userExpense")).put("income",userIncomes);
			responseObj.put("status","success");
			if(maritalStatus.equals("Yes"))

			{

				responseObj.put("spouseIncome",spouse_income);

			}
			}
			else
			{
				responseObj.put("status","fail");
			}
		}

		catch(Exception e){

			e.printStackTrace();

		}
		return responseObj;

	}
	@Override
	//-----------------------------Bala portfolio management new architecture--------------------------------------------------------
	public JSONObject calInvestmentPortfoilo(String user_id,String plan_name,int Risk_Score) {
		JSONObject responseObj=new JSONObject();
		try {
			FinPlan getfinplan = MongoDBConnection.finplancol.findOne("{plan_name:#,usr_id:#}",plan_name,user_id).as(FinPlan.class);
			IncomeProfile incomeProfileCollData=MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}",user_id,getfinplan.getProfile_name()).as(IncomeProfile.class);
			User user = MongoDBConnection.userColl.findOne("{'_id':#}", user_id).as(User.class);
			int userAge=user.getAge();
			String fin_id=getfinplan.get_id();

			JSONObject finPlanJson=new JSONObject(jsonMapper.writeValueAsString(getfinplan));

			JSONObject incomeProfileJson=new JSONObject(jsonMapper.writeValueAsString(incomeProfileCollData));

			int spouseAge=getfinplan.getSpouseAge();

			String maritalStatus=getfinplan.getMarital_status();

			JSONArray userIncomes=incomeProfileJson.getJSONArray("user_income");

			JSONArray income=new JSONArray();

			JSONArray spouse_income=new JSONArray();

			if (maritalStatus.equals("Yes")) {
				income=incomeProfileJson.getJSONArray("combined_income");

				spouse_income=incomeProfileJson.getJSONArray("spouse_income");

			} else {

				income=incomeProfileJson.getJSONArray("user_income");

			}

			int emergencyFundAmt=getfinplan.getEmergencyFundAmt();

			boolean emergencyFundFlag=getfinplan.isEmergencyFundFlag();

			String emergencyType=null;

			String monthsOfIncome=null;

			String monthsOfExpense=null;

			if(emergencyFundFlag==true)

			{

				Emergencyfundmodel emergencyObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",fin_id,"Emergency Fund").as(Emergencyfundmodel.class);

				emergencyType=emergencyObj.getTimePeriod();

				monthsOfIncome=emergencyObj.getMonthI();

				monthsOfExpense=emergencyObj.getMonthE();

			}

		RetirementGoal retirementObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",fin_id,"Retirement").as(RetirementGoal.class);
			int SpouseStartYear=0;

			int userStartYear=0;

			if(retirementObj!=null)

			{

				userStartYear=user.getBirthYear()+retirementObj.getRetirementAge();

				if(getfinplan.getMarital_status().equals("Yes")&& user.getMarital_status().equals("No")){

					SpouseStartYear=getfinplan.getSpouseBirthYear()+retirementObj.getSpouseRetirementAge();

				}

				else if(user.getMarital_status().equals("Yes"))

				{

					SpouseStartYear=user.getSpouseBirthYear()+retirementObj.getSpouseRetirementAge();
				}

			}

			else{

				userStartYear=user.getBirthYear()+70;

				if(getfinplan.getMarital_status().equals("Yes")&& user.getMarital_status().equals("No")){

					SpouseStartYear=getfinplan.getSpouseBirthYear()+70;

				}

				else if(user.getMarital_status().equals("Yes"))

				{

					SpouseStartYear=user.getSpouseBirthYear()+70;

				}

			}

			JSONObject retirementData=new JSONObject();

			retirementData.put("spouseStartYear", SpouseStartYear);

			retirementData.put("userStartYear", userStartYear);

			JSONArray expenseObj=new JSONArray();
			JSONObject finPlanExpense=finPlanJson.getJSONObject("expense");
			expenseObj=finPlanExpense.getJSONObject("expense").getJSONArray("housing_expense");

			/*JSONObject result=CalculationEngine.sweepingOfMoneyInvestmentPortfolio1(userIncomes,fin_id,income,spouse_income, finPlanJson.getJSONArray("userExpense"), finPlanJson.getJSONArray("limits"), maritalStatus, finPlanJson.getJSONArray("assests"), finPlanJson.getJSONArray("tax"),user_id, finPlanJson.getJSONArray("fillingExemtion"),userAge,spouseAge,

					emergencyFundAmt,  emergencyFundFlag, finPlanJson.getJSONArray("deductions"), finPlanJson.getJSONArray("kidBirthYear"), retirementData, retirementObj, expenseObj,emergencyType,monthsOfIncome,monthsOfExpense,getfinplan.isRetirementFlag(),Risk_Score,0,0,0);
*/
			JSONObject result=new JSONObject();
			responseObj.put("statusMsg", "Goal Applied successfully");

			responseObj.put("assests", result.getJSONArray("assets")).put("tax",result.getJSONArray("tax")).put("userExpense",finPlanJson.getJSONArray("userExpense")).put("income",userIncomes);

			if(maritalStatus.equals("Yes"))

			{

				responseObj.put("spouseIncome",spouse_income);

			}

		}
		catch(Exception e){

			e.printStackTrace();

		}
		return responseObj;

	}

	public static JSONObject growthRate(int riskScore,double riskFactor,double age,InvestmentPortfolioModel investmentPortfolio)

	{


		JSONObject responseFromGrowthRate=new JSONObject();

		try

		{

			double bondWeight=0;

			double equityWeight=0;

			double agg=0;

			double lqd=0;

			double bndx=0;

			double vti=0;

			double voe=0;

			double vea=0;

			double bondInterestYield=0;

			double vtv10 = investmentPortfolio.getVtv_10_Yr_Historical_Total_Return();

			 double vbr10 =investmentPortfolio.getVbr_10_Yr_Historical_Total_Return();
		    double vea10 =	investmentPortfolio.getVea_10_Yr_Historical_Total_Return();
		     double voe10=investmentPortfolio.getVoe_10_Yr_Historical_Total_Return();
			double vti10=investmentPortfolio.getVti_10_Yr_Historical_Total_Return();
			 double vwo10 =investmentPortfolio.getVwo_10_Yr_Historical_Total_Return();

			double veaD= investmentPortfolio.getVeaDividendYield();
			 double voeD =investmentPortfolio.getVoeDividendYield();
			double vtiD= investmentPortfolio.getVtiDividendYield();

			double vwod=investmentPortfolio.getVwoDividendYield();
			double vtvd= investmentPortfolio.getVtiDividendYield();
			double vbrd=investmentPortfolio.getVbrDividendYield();

			double inflationRate=investmentPortfolio.getInflationRate();

			double aggBondYieldStart=investmentPortfolio.getAggBondYieldStart();

			double aggBondYieldEnd=investmentPortfolio.getAggBondYieldEnd();

			double lqdBondYieldStart=investmentPortfolio.getLqdBondYieldStart();

			double lqdBondYieldEnd=investmentPortfolio.getLqdBondYieldEnd();

			double bndxBondYieldStart=investmentPortfolio.getBndxBondYieldStart();

			double bndxBondYieldEnd=investmentPortfolio.getBndxBondYieldEnd();

			double vtiGrowthRateStart=investmentPortfolio.getVtiGrowthRateStart();

			double vtiGrowthRateEnd=investmentPortfolio.getVtiGrowthRateEnd();

			double vtiDividendYield=investmentPortfolio.getVtiDividendYield();

			double voeGrowthRateStart=investmentPortfolio.getVoeGrowthRateStart();

			double voeGrowthRateEnd=investmentPortfolio.getVoeGrowthRateEnd();

			double voeDividendYield=investmentPortfolio.getVoeDividendYield();

			double veaGrowthRateStart=investmentPortfolio.getVeaGrowthRateStart();

			double veaGrowthRateEnd=investmentPortfolio.getVeaGrowthRateEnd();

			double veaDividendYield=investmentPortfolio.getVeaDividendYield();

			double aggBondCoupon=investmentPortfolio.getAggBondCoupon();

			double lqdBondCoupon=investmentPortfolio.getLqdBondCoupon();

			double bndxBondCoupon=investmentPortfolio.getBndxBondCoupon();
//System.out.println("Risk score"+riskScore);
if(riskScore==0) {
    riskScore=19;
}
			if(riskScore>=20 && riskScore<=26)

			{
				bondWeight =  (Math.min (30, Math.max ( 0, ( age - 30) ) ) *40 / 30 )+ 5*(riskFactor + 1);

				agg=investmentPortfolio.getAggRiskAggressiveWeight();

				lqd=investmentPortfolio.getLqdRiskAggressiveWeight();

				bndx=investmentPortfolio.getBndxRiskAggressiveWeight();

				vti=investmentPortfolio.getVtiRiskAggressiveWeight();

				voe=investmentPortfolio.getVoeRiskAggressiveWeight();

				vea=investmentPortfolio.getVeaRiskAggressiveWeight();

			}

			else if(riskScore>=15 && riskScore<=19)

			{

				bondWeight =  (Math.min (30, Math.max ( 0, ( age - 30) ) ) *35 / 30 )+ (5*(riskFactor + 1))+10;
//////System.out.println("Bala bond weight:"+bondWeight);
				agg=investmentPortfolio.getAggRiskNeutralWeight();

				lqd=investmentPortfolio.getLqdRiskNeutralWeight();

				bndx=investmentPortfolio.getBndxRiskNeutralWeight();

				vti=investmentPortfolio.getVtiRiskNeutralWeight();

				voe=investmentPortfolio.getVoeRiskNeutralWeight();

				vea=investmentPortfolio.getVeaRiskNeutralWeight();

			}

			else if(riskScore>=10 && riskScore<=14)

			{

				bondWeight =  Math.min (30, Math.max ( 0, ( age - 30) ) )+ (5*(riskFactor + 1))+25;

				agg=investmentPortfolio.getAggRiskAdverseWeight();

				lqd=investmentPortfolio.getLqdRiskAdverseWeight();

				bndx=investmentPortfolio.getBndxRiskAdverseWeight();

				vti=investmentPortfolio.getVtiRiskAdverseWeight();

				voe=investmentPortfolio.getVoeRiskAdverseWeight();

				vea=investmentPortfolio.getVeaRiskAdverseWeight();


			}

			equityWeight=100-bondWeight;
			bondInterestYield=(40*aggBondCoupon+30*lqdBondCoupon+30*bndxBondCoupon)/100;
			agg=(bondWeight/100)*agg;

			lqd=(bondWeight/100)*lqd;

			bndx=(bondWeight/100)*bndx;




			vti=(equityWeight/100)*vti;

			voe=(equityWeight/100)*voe;

			vea=(equityWeight/100)*vea;
			double overallBondYield = (agg * (aggBondYieldStart- aggBondYieldEnd) +lqd * (lqdBondYieldStart - lqdBondYieldEnd) +

					bndx * (bndxBondYieldStart - bndxBondYieldEnd)) / bondWeight;
			double overallStockDividendYield = (vti * vtiDividendYield + voe * voeDividendYield +vea *

					veaDividendYield) /equityWeight;
			double overallStockGrowthRate = (vti * (vtiGrowthRateStart -vtiGrowthRateEnd) +voe * (voeGrowthRateStart -

					voeGrowthRateEnd) + vea * (veaGrowthRateStart -veaGrowthRateEnd))/equityWeight;

			double bondInterest=overallBondYield - inflationRate;
			double	stockGrowth=overallStockGrowthRate-inflationRate;
			double growthRate = (bondWeight/100) * bondInterest+ (equityWeight/100) * (stockGrowth);

			double portfolioDividend = (equityWeight/100) * (overallStockDividendYield);
			double portfolioInterest = (bondWeight/100) * (bondInterestYield);
		//	////System.out.println("asjdnad"+growthRate);
			//System.out.println("portfolioDividend"+portfolioDividend);
			//System.out.println("portfolioInterest"+portfolioInterest);
			//System.out.println("growthRate"+growthRate);
			responseFromGrowthRate.put("portfolioDividend", portfolioDividend).put("portfolioInterest",portfolioInterest).put("growthRate",growthRate);
			responseFromGrowthRate.put("vtv10", vtv10).put("vbr10",vbr10).put("vea10",vea10 ).put("voe10", voe10).put("vti10", vti10).put("vwo10", vwo10);
			responseFromGrowthRate.put("veaD", veaD).put("voeD", voeD).put("vtiD", vtiD).put("vwod", vwod).put("vtvd", vtvd).put("vbrd", vbrd);



		}
		catch (JSONException e)

		{

			e.printStackTrace();

		}
		  //////System.out.println("Sreveice response is"+responseFromGrowthRate);
		return responseFromGrowthRate;

	}



	public double calculateMaximumContributionForNontaxableAcount(double federalAgi, String maritalStatus, int userAge, int spouseAge) {

		double totalLimit = 0;

		if (maritalStatus.equals("Married")) {

			totalLimit = 18000 + 5500;

			if (userAge >= 50 && spouseAge >= 50) {

				federalAgi = federalAgi - 24000 - 24000;

			} else if (userAge < 50 && spouseAge < 50) {

				federalAgi = federalAgi - 18000 - 18000;

			} else {

				federalAgi = federalAgi - 24000 - 18000;

			}

			if (userAge >= 50) {

				totalLimit = 24000 + 6500;

				if (federalAgi >= 98000 && federalAgi <= 118000) {

					totalLimit = totalLimit - 0.325 * (federalAgi - 98000);

				} else if (federalAgi < 98000) {

					totalLimit = totalLimit - 6500;

				} else if (federalAgi > 118000) {

					totalLimit = totalLimit - 0;

				}

			} else {

				if (federalAgi >= 98000 && federalAgi <= 118000) {

					totalLimit = totalLimit - 0.275 * (federalAgi - 98000);

				} else if (federalAgi < 98000) {

					totalLimit = totalLimit - 5500;

				} else if (federalAgi > 118000) {

					totalLimit = totalLimit - 0;

				}

			}

			if (spouseAge >= 50) {

				totalLimit = totalLimit + 24000 + 6500;

				if (federalAgi >= 98000 && federalAgi <= 118000) {

					totalLimit = totalLimit - 0.325 * (federalAgi - 98000);

				} else if (federalAgi < 98000) {

					totalLimit = totalLimit - 6500;

				} else if (federalAgi > 118000) {

					totalLimit = totalLimit - 0;

				}

			} else {

				totalLimit = totalLimit + 18000 + 5500;

				if (federalAgi >= 98000 && federalAgi <= 118000) {

					totalLimit = totalLimit - 0.275 * (federalAgi - 98000);

				} else if (federalAgi < 98000) {

					totalLimit = totalLimit - 5500;

				} else if (federalAgi > 118000) {

					totalLimit = totalLimit - 0;

				}

			}

		} else if (maritalStatus.equals("Single")) {

			totalLimit = 18000 + 5500;

			if (userAge >= 50) {

				federalAgi = federalAgi - 24000;

				totalLimit = 24000 + 6500;

				if (federalAgi >= 61000 && federalAgi <= 71000) {

					totalLimit = totalLimit - 0.65 * (federalAgi - 61000);

				} else if (federalAgi < 61000) {

					totalLimit = totalLimit - 6500;

				} else if (federalAgi > 71000) {

					totalLimit = totalLimit - 0;

				}

			} else {

				federalAgi = federalAgi - 18000;

				if (federalAgi >= 61000 && federalAgi <= 71000) {



					totalLimit = totalLimit - 0.55 * (federalAgi - 61000);

				} else if (federalAgi < 61000) {

					totalLimit = totalLimit - 5500;

				} else if (federalAgi > 71000) {

					totalLimit = totalLimit - 0;

				}

			}

		} else if (maritalStatus.equals("Head of Household")) {

			totalLimit = 18000 + 5500;

			if (userAge >= 50) {

				federalAgi = federalAgi - 24000;

				totalLimit = 24000 + 6500;

				if (federalAgi >= 61000 && federalAgi <= 71000) {

					totalLimit = totalLimit - 0.65 * (federalAgi - 61000);

				} else if (federalAgi < 61000) {

					totalLimit = totalLimit - 6500;

				} else if (federalAgi > 71000) {

					totalLimit = totalLimit - 0;

				}

			} else {

				federalAgi = federalAgi - 18000;

				if (federalAgi >= 61000 && federalAgi <= 71000) {

					totalLimit = totalLimit - 0.55 * (federalAgi - 61000);

				} else if (federalAgi < 61000) {

					totalLimit = totalLimit - 5500;

				} else if (federalAgi > 71000) {

					totalLimit = totalLimit - 0;

				}

			}
		}

		return totalLimit;

	}

	@Override
    public void duringRegistration(String user_id,int riskScore)

	{
		////System.out.println("riskscore in interface .."+riskScore);
		MongoDBConnection.userColl.update("{'_id': '" + user_id + "'}").upsert().multi()
		.with("{$set: {'riskScore':'" + riskScore + "'}}");
	}

}


