package com.mongorest.olahtek.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.JacksonMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eclipsesource.json.JsonArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongorest.olahtek.model.ConstantsValues;

import com.mongorest.olahtek.model.InvestmentPortfolioModel;
import com.mongorest.olahtek.model.MinimumDistributionModel;
import com.mongorest.olahtek.model.StatetaxModel;

@Service("mongoConnection")
@Transactional
public class MongoDBConnection implements MongoConnectionInterface {
	public static Properties serviceProp;
	public static MongoClient mongoClient;
	public static MongoDatabase mongoDb;
	public static Jongo jongo;
	public static String mongoDBName;
	public static DB db;
	public static JSONObject cm;
	public static int numConnection = 0;
	public static String usersCollection;
	public static String counterCollection;
	public static String financialPlanCollection;
	public static String sessionCollection;
	public static String goalCollection;
	public static String incomeProfileCollection;
	public static String minimumDistributioncollection;
	public static String distribution;
	public static String userSession;
	public static String systemLog;
	public static String stateTaxCollection;
	public static String investmentPortfolio;
	public static String statescollection;
	public static String indexfactorcollection;
	public static String olahtekmailusername;
	public static String olahtekmailpassword;
	public static String adminEmail;
	public static String Constantvalue;
	public static String adminPassword;
	public static String homeinsurance;
	public static String mortgageRatesCollection;
	public static String CollegeCostsCollection;
	public static String kid_ChildFactor;
	public static String kid_Expense_MidWest;
	public static String kid_Expense_NorthEast;
	public static String kid_Expense_Rural;
	public static String kid_Expense_South;
	public static String kid_Expense_West;
	public static String kid_InflationAdjustment;
	public static String kid_RegionalAdjustmentFactor;
	public static String kid_Rural_List_2017;
	public static String kidgoal_Married;
	public static String kidgoal_Single;
	public static String state_House_Median_Value;
	public static String city_House_Median_Value;
	public static String county_House_Median_Value;
	public static MongoCollection CollegeCostsColl;
	public static MongoCollection userColl;
	public static MongoCollection Constantvaluecoll;
	public static MongoCollection minimumDistributioncoll;
	public static MongoCollection distributionColl;
	public static MongoCollection incomeProfileCol;
	public static MongoCollection finplancol;
	public static MongoCollection investmentPortfolioCol;
	public static MongoCollection goalcoll;
	public static MongoCollection counterColl;
	public static MongoCollection systemLogCollection;
	public static MongoCollection stateColl;
	public static MongoCollection stateTaxColl;
	public static MongoCollection indexFactor;
	public static MongoCollection userSessionColl;
	public static MongoCollection sessionColl;
	public static MongoCollection homeinsuranceColl;
	public static MongoCollection rateAdjustmentColl;
	public static MongoCollection kid_ChildFactorColl;
	public static MongoCollection kid_Expense_MidWestColl;
	public static MongoCollection kid_Expense_NorthEastColl;
	public static MongoCollection kid_Expense_RuralColl;
	public static MongoCollection kid_Expense_SouthColl;
	public static MongoCollection kid_Expense_WestColl;
	public static MongoCollection kid_InflationAdjustmentColl;
	public static MongoCollection kid_RegionalAdjustmentFactorColl;
	public static MongoCollection kid_Rural_List_2017Coll;
	public static MongoCollection kidgoal_MarriedColl;
	public static MongoCollection kidgoal_SingleColl;
	public static MongoCollection state_House_Median_ValueColl;
	public static MongoCollection city_House_Median_ValueColl;
	public static MongoCollection county_House_Median_ValueColl;
	    public static HazelcastInstance hazelCastClient;
	    public static  IMap<Object, Object>tempData;
	    public static IMap<Object, Object> states;
	    public static MinimumDistributionModel distributionModelData;
	    public static InvestmentPortfolioModel investmentPortfolioData;
	    public static MongoCollection ConstantValue;
	    public static ConstantsValues ConstantDetails;
	    public static ObjectMapper jsonMapper = new ObjectMapper();
	    public static JSONArray  taxRates;
	    public static JSONArray taxRatesMarrid;
	    public static JSONArray marriedFilingJointly;
	    public static JSONArray qualifiedWidow;
	    public static JSONArray headOfHousehold;
	    public static JSONObject constJson;
	    public static StatetaxModel headofHouse;
	    public static JSONObject headofHouses;
	    public static JSONArray stateagilimit;
	    public static JSONArray marriedFilingJointstateagilimit;
	    public static JSONArray headOfHouseholdstateagilimit;
	    public static StatetaxModel  wisconsin;
	    public static  JSONObject wisconsins;
	    public static StatetaxModel newMaxcio;
	    public static StatetaxModel OHIOJson;
	    public static StatetaxModel rhodeislandJson;
	    public static StatetaxModel  arkansas;
	    public static  int aditionalDeductionMinIncomeM;
	    public static   double aditionalDeductionMinIncomeRatesM;
    	public static  StatetaxModel  maryLand;
    	public static  JSONObject maryLandJsons;
        public static JSONArray stateAgiLimitmaryLand;
        public static JSONArray  stateAgiLimitmaryLandMaried;
        public static double  personalExemptionCalifornia;
        public static StatetaxModel  Connecticut;
        public static JSONObject  ConnecticutJsons;
        public static  StatetaxModel  montana;
        public static JSONObject montanaJsons;
        public static StatetaxModel tennessee;
        public static JSONObject tennesseeJsons;
        public static  StatetaxModel oregon;
        public static  StatetaxModel  Newhampshire;
        public static  StatetaxModel  california;
        public static StatetaxModel  utah;
        public static StatetaxModel missouri;
        public static StatetaxModel  michigan;
        public static StatetaxModel massachusetts;

	public MongoDBConnection() {
		try {

			serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				serviceProp.load(inputStream);
				inputStream.close();
				// //System.out.println("Application is reading configuration from "
				// + home);
			} else {

				// //System.out.println("Application is reading configuration from resources");
			}

			String mongoIP = (String) serviceProp.get("mongoDBHost");
			String mongoPort = (String) serviceProp.get("mongoDBPort");
			usersCollection = (String) serviceProp.get("userscollection");
			Constantvalue = (String) serviceProp.get("ConstantValue");
			//System.out.println("Constantvalue-->>>"+Constantvalue);
			systemLog = (String) serviceProp.get("systemLog");
			sessionCollection = (String) serviceProp.get("sessioncollection");
			userSession = (String) serviceProp.get("userSession");
			counterCollection = (String) serviceProp.get("counterscollection");
			goalCollection = serviceProp.getProperty("goalsCollection");
			financialPlanCollection = (String) serviceProp.get("financialPlancollection");
			incomeProfileCollection = (String) serviceProp.get("incomeprofilecollection");
			minimumDistributioncollection = (String) serviceProp.get("minimumDistributionCollection");
			distribution = (String) serviceProp.get("distribution");
			stateTaxCollection = (String) serviceProp.get("stateTaxCollection");
			investmentPortfolio = (String) serviceProp.get("investmentPortfolioConstants");
			statescollection = (String) serviceProp.get("statescollection");
			indexfactorcollection = (String) serviceProp.get("indexfactorcollection");
			olahtekmailusername = (String) serviceProp.get("Olahtekmailusername");
			olahtekmailpassword = (String) serviceProp.get("Olahtekmailpassword");
			mongoDBName = (String) serviceProp.get("mongoDBName");
			adminEmail = (String) serviceProp.get("adminEmail");
			adminPassword = (String) serviceProp.get("adminPassword");
			homeinsurance = (String) serviceProp.get("homeinsuranceColl");
			mortgageRatesCollection=(String) serviceProp.get("mortgageRatesCollection");
			CollegeCostsCollection=(String) serviceProp.get("CollegeCostsCollection");
			kid_ChildFactor = (String) serviceProp.get("kid_ChildFactorColl");
			kid_Expense_MidWest = (String) serviceProp.get("kid_Expense_MidWestColl");
			kid_Expense_NorthEast = (String) serviceProp.get("kid_Expense_NorthEastColl");
			kid_Expense_Rural = (String) serviceProp.get("kid_Expense_RuralColl");
			kid_Expense_South = (String) serviceProp.get("kid_Expense_SouthColl");
			kid_Expense_West = (String) serviceProp.get("kid_Expense_WestColl");
			kid_InflationAdjustment = (String) serviceProp.get("kid_InflationAdjustmentColl");
			kid_RegionalAdjustmentFactor = (String) serviceProp.get("kid_RegionalAdjustmentFactorColl");
			kid_Rural_List_2017 = (String) serviceProp.get("kid_Rural_List_2017Coll");
			kidgoal_Married = (String) serviceProp.get("kidgoal_MarriedColl");
			kidgoal_Single = (String) serviceProp.get("kidgoal_SingleColl");
			state_House_Median_Value = (String) serviceProp.get("state_House_Median_ValueColl");
			city_House_Median_Value = (String) serviceProp.get("city_House_Median_ValueColl");
			county_House_Median_Value = (String) serviceProp.get("county_House_Median_ValueColl");

			mongoClient = new MongoClient(mongoIP, Integer.parseInt(mongoPort));
			db = mongoClient.getDB(mongoDBName);
			jongo = new Jongo(db, new JacksonMapper.Builder().build());
			userColl = jongo.getCollection(usersCollection);
			incomeProfileCol = jongo.getCollection(incomeProfileCollection);
			finplancol = jongo.getCollection(financialPlanCollection);

			Constantvaluecoll = jongo.getCollection(Constantvalue);
			goalcoll = jongo.getCollection(goalCollection);
			CollegeCostsColl = jongo.getCollection(CollegeCostsCollection);
			minimumDistributioncoll = jongo.getCollection(minimumDistributioncollection);
			distributionColl=jongo.getCollection(distribution);
			counterColl = jongo.getCollection(counterCollection);
			investmentPortfolioCol = jongo.getCollection(investmentPortfolio);
			systemLogCollection = jongo.getCollection(systemLog);
			stateColl = jongo.getCollection(statescollection);
			indexFactor = jongo.getCollection(indexfactorcollection);
			userSessionColl = jongo.getCollection(userSession);
			sessionColl = jongo.getCollection(sessionCollection);
			stateTaxColl = jongo.getCollection(stateTaxCollection);
			homeinsuranceColl = jongo.getCollection(homeinsurance);
			rateAdjustmentColl=jongo.getCollection(mortgageRatesCollection);
			kid_ChildFactorColl = jongo.getCollection(kid_ChildFactor);
			kid_Expense_MidWestColl = jongo.getCollection(kid_Expense_MidWest);
			kid_Expense_NorthEastColl = jongo.getCollection(kid_Expense_NorthEast);
			kid_Expense_RuralColl = jongo.getCollection(kid_Expense_Rural);
			kid_Expense_SouthColl = jongo.getCollection(kid_Expense_South);
			kid_Expense_WestColl = jongo.getCollection(kid_Expense_West);
			kid_InflationAdjustmentColl = jongo.getCollection(kid_InflationAdjustment);
			kid_RegionalAdjustmentFactorColl = jongo.getCollection(kid_RegionalAdjustmentFactor);
			kid_Rural_List_2017Coll = jongo.getCollection(kid_Rural_List_2017);
			kidgoal_MarriedColl = jongo.getCollection(kidgoal_Married);
			kidgoal_SingleColl = jongo.getCollection(kidgoal_Single);
			state_House_Median_ValueColl = jongo.getCollection(state_House_Median_Value);
			city_House_Median_ValueColl = jongo.getCollection(city_House_Median_Value);
			county_House_Median_ValueColl = jongo.getCollection(county_House_Median_Value);

			String cachehost = (String) serviceProp.get("cacheHost");
			String cachePort = (String) serviceProp.get("cachePort");
			  		   	   ClientConfig clientConfig = new ClientConfig();
			clientConfig.getNetworkConfig().addAddress(cachehost + ":" + cachePort);
			hazelCastClient = HazelcastClient.newHazelcastClient(clientConfig);
			tempData=MongoDBConnection.hazelCastClient.getMap("tempData");
			states=MongoDBConnection.hazelCastClient.getMap("states");
			distributionModelData = minimumDistributioncoll.findOne("{_id:#}","minimumDistribution").as(MinimumDistributionModel.class);
		    investmentPortfolioData = investmentPortfolioCol.findOne().as(InvestmentPortfolioModel .class);
		    ConstantDetails = MongoDBConnection.Constantvaluecoll.findOne("{_id:#}","constantValue1").as(ConstantsValues.class);
			 constJson = new JSONObject(jsonMapper.writeValueAsString(ConstantDetails));
		     taxRates= constJson.getJSONObject("single").getJSONArray("taxRates");
		     taxRatesMarrid=constJson.getJSONObject("marriedFilingSeparately").getJSONArray("taxRates");
		     marriedFilingJointly=constJson.getJSONObject("marriedFilingJoint").getJSONArray("taxRates");
		     headOfHousehold=constJson.getJSONObject("headOfHousehold").getJSONArray("taxRates");
		     qualifiedWidow=constJson.getJSONObject("qualifiedWidow").getJSONArray("taxRates");
		     headofHouse=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state1").as(StatetaxModel.class);
		     headofHouses = new JSONObject(jsonMapper.writeValueAsString(headofHouse));
		      stateagilimit=headofHouses.getJSONObject("single").getJSONArray("stateAgiLimit");
		      marriedFilingJointstateagilimit=headofHouses.getJSONObject("marriedFilingJoint").getJSONArray("stateAgiLimit");
		      headOfHouseholdstateagilimit=headofHouses.getJSONObject("headOfHousehold").getJSONArray("stateAgiLimit");
	             maryLand=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state20").as(StatetaxModel.class);
	             maryLandJsons = new JSONObject(jsonMapper.writeValueAsString(maryLand));
		       wisconsin=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state49").as(StatetaxModel.class);
        	  newMaxcio=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state31").as(StatetaxModel.class);
               arkansas=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state4").as(StatetaxModel.class);
               california=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state5").as(StatetaxModel.class);
               OHIOJson=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state35").as(StatetaxModel.class);
                montana=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state26").as(StatetaxModel.class);
               montanaJsons = new JSONObject(jsonMapper.writeValueAsString(montana));
                Newhampshire=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state29").as(StatetaxModel.class);
               tennessee=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state42").as(StatetaxModel.class);
               tennesseeJsons = new JSONObject(jsonMapper.writeValueAsString(tennessee));
                Connecticut=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state7").as(StatetaxModel.class);
                ConnecticutJsons = new JSONObject(jsonMapper.writeValueAsString(Connecticut));
                oregon=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state37").as(StatetaxModel.class);
                rhodeislandJson=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state39").as(StatetaxModel.class);
                utah=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state44").as(StatetaxModel.class);
                missouri=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state25").as(StatetaxModel.class);
                michigan=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state22").as(StatetaxModel.class);
                massachusetts=MongoDBConnection.stateTaxColl.findOne("{_id:#}","state21").as(StatetaxModel.class);






		} catch (Exception e) {
			e.printStackTrace();
          //  //System.out.println("aditionalDeductionMinIncome111111==="+aditionalDeductionMinIncome+"aditionalDeductionMinIncomeRates1111111===="+aditionalDeductionMinIncomeRates);
		}

		// TODO Auto-generated constructor stub
	}

	@Override
    public void closeMongoClient() {

	}
}