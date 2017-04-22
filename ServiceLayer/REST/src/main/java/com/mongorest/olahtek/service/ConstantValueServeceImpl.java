package com.mongorest.olahtek.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.jongo.MongoCursor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eclipsesource.json.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongorest.olahtek.model.ConstantValuesModel;
import com.mongorest.olahtek.model.ConstantsValues;
import com.mongorest.olahtek.model.StatetaxModel;

@Service("constantValueServece")
@Transactional
public class ConstantValueServeceImpl implements ConstantValueServece {
	public  ObjectMapper jsonMapper = new ObjectMapper();
	public JSONObject onLoadStateData(String stateName)
	{
		JSONObject responseFromRest=new JSONObject();
		try
		{
			////System.out.println("stateName--->>."+stateName);
			responseFromRest.put("status", "fail");			
			StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName).as(StatetaxModel.class);
			responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
			////System.out.println("responseFromRest-->>>>>>>"+responseFromRest);
			responseFromRest.put("status", "success");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return responseFromRest;
	}
	
	
	public JSONObject onLoadAllStateNames()
	{
		
		JSONObject responseFromRest=new JSONObject();
		
		
		try
		{
			////System.out.println("welcome state--->>.");
			responseFromRest.put("status", "fail");		
			JSONArray plan = new JSONArray();
			MongoCursor<StatetaxModel> cursor = MongoDBConnection.stateTaxColl.find().as(StatetaxModel.class);
			
			while (cursor.hasNext()) {
			
				StatetaxModel fetch = cursor.next();
				JSONObject responseFromRest2=new JSONObject();
				////System.out.println("fetch-->>"+fetch.getStatename());
				responseFromRest2.put("state",fetch.getStatename());
				plan.put(responseFromRest2);
				//plan.put(fetch);
				
			}
			responseFromRest.put("allStates",plan);
			////System.out.println("cursor in all states--->>"+responseFromRest);
			//responseFromRest = new JSONObject(jsonMapper.writeValueAsString(StatetaxModel);
			responseFromRest.put("status", "success");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return responseFromRest;
	}
	
	public JSONObject onLoadConstantValues(StatetaxModel stateName)
	{
		//System.out.println("california===1111  "+stateName.getState());
	//	personalExcemption
		JSONObject responseFromRest=new JSONObject();
		try
		{
			//System.out.println("Aparna statevalues==="+new JSONObject(jsonMapper.writeValueAsString(stateName)));

			if(stateName.getState().equals("WISCONSIN")){


   			
    				ArrayList rates = new ArrayList();
    				ArrayList brakets = new ArrayList();
    				Iterator crunchifyIterator =stateName.getRates().iterator();
    				while (crunchifyIterator.hasNext()) {
    					////System.out.println("lllll--->>>"+rates.add(crunchifyIterator.next()));
    				}

    				Iterator crunchifyIterator1 =stateName.getBrackets().iterator();
    				while (crunchifyIterator1.hasNext()) {
    					////System.out.println("lllll2--->>>"+brakets.add(crunchifyIterator1.next()));
    				}
    				 Collections.sort(rates);
    				   Collections.sort(brakets);

    				
    				
    				   JSONArray rates1=new JSONArray(rates);
    				   JSONArray brakets1=new JSONArray(brakets);
    				
    					double myDependentExcemption = stateName.getDependentExcemption();
    				int dependentExcemption = (int) myDependentExcemption;
    				BasicDBObject systemLogBasicObject = new BasicDBObject();
    				
    				JSONArray ratesandBrakets=new JSONArray();
    				
    				JSONArray ratesandBrakets1=new JSONArray();
    				
    				responseFromRest.put("status", "fail");	
    				
    				/*MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'StandedDeductionconstant':" + stateName.getMinStandardDeduction() + ",'minStandardDeduction':"+stateName.getMinAGIlimit()+"}}");*/
    				

    					if(stateName.getFillingStatus().equals("Single")){
    						JSONArray standerdDeductionLimits=new JSONArray();
    						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
    						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
    						JSONArray maxAGIlimit=new JSONArray();
    							maxAGIlimit.put(stateName.getMaxAGIlimit());
    						maxAGIlimit.put(stateName.getMaxAGIlimit1());
    						maxAGIlimit.put(stateName.getMaxAGIlimit2());
    						maxAGIlimit.put(stateName.getMaxAGIlimit3());
    						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

    						JSONArray personalExcemptionlimit=new JSONArray();
    						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
    						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
    						personalExcemptionlimit.put(0);
    						//standerdDeductionLimit
    						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
    						
    						JSONArray standarddeductionRange=new JSONArray(); 
    						standarddeductionRange.put(stateName.getMinStandardDeduction());
    						standarddeductionRange.put(stateName.getMaxStandardDeduction());
    						JSONArray incomeLimit=new JSONArray(); 
    						incomeLimit.put(stateName.getIncomeLimits1());
    						incomeLimit.put(stateName.getIncomeLimits2());
    						incomeLimit.put(stateName.getIncomeLimits3());
    						incomeLimit.put(stateName.getIncomeLimits4());
    						////System.out.println("incomeLimit12---->>>>"+incomeLimit);
    						
    						JSONArray personalExcemptionsValues=new JSONArray(); 
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues1());
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues2());
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues3());
    						////System.out.println("standerd-->>"+stateName.getStandardDeductionPhaseOutRates()/100);
    						double standerdpashoutrates=(((double)stateName.getStandardDeductionPhaseOutRates())/100);
    						////System.out.println("standerdpashoutrates-->>"+standerdpashoutrates);
    						////System.out.println("personalExcemptionsValues--->>"+personalExcemptionsValues);
    						//standerdDeductionLimit
    						////System.out.println("single-----------------------");
    						////System.out.println("stateName.getPersonalExcemptionsValues1()--->>"+stateName.getPersonalExcemptionsValues1());
    						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
    						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

    						//	////System.out.println("limt___"+stateAgiLimit);
    						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
    					//	standarddeductionRange
    					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.personalExcemption':" + stateName.getPersonalExcemption() + ",'single.standarddeduction':" + stateName.getStandardDeduction()+",'single.standardDeductionPhaseOutRates':"+standerdpashoutrates+",'single.standardDeductionPhaseOutLimit':"+stateName.getStandardDeductionPhaseOutLimit()+"}}");
    					

    						
    					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

    						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
    						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
    					
    						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
    						
    						
    						for(int i=0;i<rates1.length();i++){
    							ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
    							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

    							

    						}
    						
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets + "}}");
    						
    						for(int i=0;i<brakets1.length();i++){
    							
    							ratesandBrakets1.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
    							

    						}
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets1 + "}}");

    						
    					} else if(stateName.getFillingStatus().equals("Married Filling Jointly")){
    						JSONArray standerdDeductionLimits=new JSONArray();
    						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
    						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
    						JSONArray maxAGIlimit=new JSONArray();
    						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
    						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
    						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
    						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

    						maxAGIlimit.put(stateName.getMaxAGIlimit());
    						maxAGIlimit.put(stateName.getMaxAGIlimit1());
    						maxAGIlimit.put(stateName.getMaxAGIlimit2());
    						maxAGIlimit.put(stateName.getMaxAGIlimit3());
    						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

    						JSONArray personalExcemptionlimit=new JSONArray();
    						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
    						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
    						personalExcemptionlimit.put(0);
    						//standerdDeductionLimit
    						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
    						
    						JSONArray standarddeductionRange=new JSONArray(); 
    						standarddeductionRange.put(stateName.getMinStandardDeduction());
    						standarddeductionRange.put(stateName.getMaxStandardDeduction());
    						JSONArray incomeLimit=new JSONArray(); 
    						incomeLimit.put(stateName.getIncomeLimits1());
    						incomeLimit.put(stateName.getIncomeLimits2());
    						incomeLimit.put(stateName.getIncomeLimits3());
    						incomeLimit.put(stateName.getIncomeLimits4());
    						////System.out.println("incomeLimit12---->>>>"+incomeLimit);
    						
    						JSONArray personalExcemptionsValues=new JSONArray(); 
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues1());
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues2());
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues3());
    						double standerdpashoutrates=(((double)stateName.getStandardDeductionPhaseOutRates())/100);
    						////System.out.println("personalExcemptionsValues--->>"+personalExcemptionsValues);
    						//standerdDeductionLimit
    						////System.out.println("single-----------------------");
    						////System.out.println("stateName.getPersonalExcemptionsValues1()--->>"+stateName.getPersonalExcemptionsValues1());
    						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
    						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

    						//	////System.out.println("limt___"+stateAgiLimit);
    						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
    					//	standarddeductionRange
    					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.personalExcemption':" + stateName.getPersonalExcemption() + ",'marriedFilingJoint.standarddeduction':" + stateName.getStandardDeduction()+",'marriedFilingJoint.standardDeductionPhaseOutRates':"+standerdpashoutrates+",'marriedFilingJoint.standardDeductionPhaseOutLimit':"+stateName.getStandardDeductionPhaseOutLimit()+"}}");
    					

    						
    					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

    						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
    						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
    					
    						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
    						
    						
    						for(int i=0;i<rates1.length();i++){
    							ratesandBrakets.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
    							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

    							

    						}
    						
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets + "}}");
    						
    						for(int i=0;i<brakets1.length();i++){
    							
    							ratesandBrakets1.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
    							

    						}
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets1 + "}}");

    						
    					}
    					 else if(stateName.getFillingStatus().equals("Head of HouseHold")){
     						JSONArray standerdDeductionLimits=new JSONArray();
     						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
     						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
     						JSONArray maxAGIlimit=new JSONArray();
     						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
     						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
     						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
     						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

     						maxAGIlimit.put(stateName.getMaxAGIlimit());
     						maxAGIlimit.put(stateName.getMaxAGIlimit1());
     						maxAGIlimit.put(stateName.getMaxAGIlimit2());
     						maxAGIlimit.put(stateName.getMaxAGIlimit3());
     						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

     						JSONArray personalExcemptionlimit=new JSONArray();
     						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
     						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
     						personalExcemptionlimit.put(0);
     						//standerdDeductionLimit
     						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
     						
     						JSONArray standarddeductionRange=new JSONArray(); 
     						standarddeductionRange.put(stateName.getMinStandardDeduction());
     						standarddeductionRange.put(stateName.getMaxStandardDeduction());
     						JSONArray incomeLimit=new JSONArray(); 
     						incomeLimit.put(stateName.getIncomeLimits1());
     						incomeLimit.put(stateName.getIncomeLimits2());
     						incomeLimit.put(stateName.getIncomeLimits3());
     						incomeLimit.put(stateName.getIncomeLimits4());
     						////System.out.println("incomeLimit12---->>>>"+incomeLimit);
     						
     						JSONArray personalExcemptionsValues=new JSONArray(); 
     						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues1());
     						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues2());
     						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues3());
     						////System.out.println("personalExcemptionsValues--->>"+personalExcemptionsValues);
     						//standerdDeductionLimit
     						////System.out.println("single-----------------------");
     						////System.out.println("stateName.getPersonalExcemptionsValues1()--->>"+stateName.getPersonalExcemptionsValues1());
     						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
     						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));
     						double standerdpashoutrates=(((double)stateName.getStandardDeductionPhaseOutRates())/100);
     						//	////System.out.println("limt___"+stateAgiLimit);
     						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
     					//	standarddeductionRange
     					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.personalExcemption':" + stateName.getPersonalExcemption() + ",'headOfHousehold.standarddeduction':" + stateName.getStandardDeduction()+",'headOfHousehold.standardDeductionPhaseOutRates':"+standerdpashoutrates+",'headOfHousehold.standardDeductionPhaseOutLimit':"+stateName.getStandardDeductionPhaseOutLimit()+"}}");
     					

     						
     					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

     						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
     						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
     					
     						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
     						
     						
     						for(int i=0;i<rates1.length();i++){
     							ratesandBrakets.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
     							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

     							

     						}
     						
     						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets + "}}");
     						
     						for(int i=0;i<brakets1.length();i++){
     							
     							ratesandBrakets1.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
     							

     						}
     						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets1 + "}}");

     						
     					}
    					
    					//////System.out.println("ratesandBrakets--->>"+ratesandBrakets);
    				
    				
    				responseFromRest.put("status", "success");
   			
				
			}
			
		else if(stateName.getState().equals("RHODE ISLAND")){

   				////System.out.println("welcome RHODE ISLAND-->>>");
   				

    				////System.out.println("inside-------++_-->>>");
    				////System.out.println("Actiontype--->>"+stateName.getActionType());
    				////System.out.println("stateName--->>"+stateName.getState());
    				////System.out.println("min stadaer-->>"+stateName.getMinStandardDeduction());
    				////System.out.println("minStandardDeduction-->>"+stateName.getMinAGIlimit());
    				////System.out.println("dependentExcemption-->>"+stateName.getDependentExcemption());
    				////System.out.println("tateName.getMaritalstatus()===>>"+stateName.getMaritalstatus());
    				////System.out.println("tateName.filling()===>>"+stateName.getFillingStatus());
    				////System.out.println("tateName.StandardDeductionAmt===>>"+stateName.getStandardDeductionAmt());
    				////System.out.println("StandardDeduction--->"+stateName.getStandardDeduction());
    				////System.out.println("personalExcemption----->>"+stateName.getPersonalExcemption());
    				////System.out.println("rates --->>>"+stateName.getRates());
    				////System.out.println("brakets --->>>"+stateName.getBrackets());
    				//JSONArray rates=new JSONArray();
    			//	JSONArray brakets=new JSONArray();
    				ArrayList rates = new ArrayList();
    				ArrayList brakets = new ArrayList();
    				Iterator crunchifyIterator =stateName.getRates().iterator();
    				while (crunchifyIterator.hasNext()) {
    					////System.out.println("lllll--->>>"+rates.add(crunchifyIterator.next()));
    				}

    				Iterator crunchifyIterator1 =stateName.getBrackets().iterator();
    				while (crunchifyIterator1.hasNext()) {
    					////System.out.println("lllll2--->>>"+brakets.add(crunchifyIterator1.next()));
    				}
    				 Collections.sort(rates);
    				   Collections.sort(brakets);

    				
    				
    				   JSONArray rates1=new JSONArray(rates);
    				   JSONArray brakets1=new JSONArray(brakets);
    				
    			////System.out.println("aa1--->>>"+rates1);
    			////System.out.println("brakets1-->"+brakets1);
    		
    				
    				
    				double myDependentExcemption = stateName.getDependentExcemption();
    				int dependentExcemption = (int) myDependentExcemption;
    				BasicDBObject systemLogBasicObject = new BasicDBObject();
    				
    				JSONArray ratesandBrakets=new JSONArray();
    				
    				JSONArray ratesandBrakets1=new JSONArray();
    				
    				responseFromRest.put("status", "fail");	
    				
    				/*MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'StandedDeductionconstant':" + stateName.getMinStandardDeduction() + ",'minStandardDeduction':"+stateName.getMinAGIlimit()+"}}");*/
    				

    					if(stateName.getFillingStatus().equals("Single")){
    						JSONArray standerdDeductionLimits=new JSONArray();
    						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
    						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
    						JSONArray maxAGIlimit=new JSONArray();
    						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
    						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
    						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
    						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

    						maxAGIlimit.put(stateName.getMaxAGIlimit());
    						maxAGIlimit.put(stateName.getMaxAGIlimit1());
    						maxAGIlimit.put(stateName.getMaxAGIlimit2());
    						maxAGIlimit.put(stateName.getMaxAGIlimit3());
    						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

    						JSONArray personalExcemptionlimit=new JSONArray();
    						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
    						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
    						personalExcemptionlimit.put(0);
    						//standerdDeductionLimit
    						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
    						
    						JSONArray standarddeductionRange=new JSONArray(); 
    						standarddeductionRange.put(stateName.getMinStandardDeduction());
    						standarddeductionRange.put(stateName.getMaxStandardDeduction());
    						JSONArray incomeLimit=new JSONArray(); 
    						incomeLimit.put(stateName.getIncomeLimits1());
    						incomeLimit.put(stateName.getIncomeLimits2());
    						incomeLimit.put(stateName.getIncomeLimits3());
    						incomeLimit.put(stateName.getIncomeLimits4());
    						////System.out.println("incomeLimit12---->>>>"+incomeLimit);
    						
    						JSONArray personalExcemptionsValues=new JSONArray(); 
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues1());
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues2());
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues3());
    						////System.out.println("personalExcemptionsValues--->>"+personalExcemptionsValues);
    						//standerdDeductionLimit
    						////System.out.println("single-----------------------");
    						////System.out.println("stateName.getPersonalExcemptionsValues1()--->>"+stateName.getPersonalExcemptionsValues1());
    						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
    						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

    						//	////System.out.println("limt___"+stateAgiLimit);
    						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
    					//	standarddeductionRange
    					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.personalExcemption':" + stateName.getPersonalExcemption() + ",'single.standarddeduction':" + stateName.getStandardDeduction()+"}}");
    					

    						
    					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

    						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
    						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
    					
    						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
    						
    						
    						for(int i=0;i<rates1.length();i++){
    							ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
    							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

    							

    						}
    						
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets + "}}");
    						
    						for(int i=0;i<brakets1.length();i++){
    							
    							ratesandBrakets1.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
    							

    						}
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets1 + "}}");

    						
    					} else if(stateName.getFillingStatus().equals("Married Filling Jointly")){
    						JSONArray standerdDeductionLimits=new JSONArray();
    						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
    						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
    						JSONArray maxAGIlimit=new JSONArray();
    						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
    						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
    						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
    						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

    						maxAGIlimit.put(stateName.getMaxAGIlimit());
    						maxAGIlimit.put(stateName.getMaxAGIlimit1());
    						maxAGIlimit.put(stateName.getMaxAGIlimit2());
    						maxAGIlimit.put(stateName.getMaxAGIlimit3());
    						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

    						JSONArray personalExcemptionlimit=new JSONArray();
    						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
    						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
    						personalExcemptionlimit.put(0);
    						//standerdDeductionLimit
    						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
    						
    						JSONArray standarddeductionRange=new JSONArray(); 
    						standarddeductionRange.put(stateName.getMinStandardDeduction());
    						standarddeductionRange.put(stateName.getMaxStandardDeduction());
    						JSONArray incomeLimit=new JSONArray(); 
    						incomeLimit.put(stateName.getIncomeLimits1());
    						incomeLimit.put(stateName.getIncomeLimits2());
    						incomeLimit.put(stateName.getIncomeLimits3());
    						incomeLimit.put(stateName.getIncomeLimits4());
    						////System.out.println("incomeLimit12---->>>>"+incomeLimit);
    						
    						JSONArray personalExcemptionsValues=new JSONArray(); 
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues1());
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues2());
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues3());
    						////System.out.println("personalExcemptionsValues--->>"+personalExcemptionsValues);
    						//standerdDeductionLimit
    						////System.out.println("single-----------------------");
    						////System.out.println("stateName.getPersonalExcemptionsValues1()--->>"+stateName.getPersonalExcemptionsValues1());
    						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
    						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

    						//	////System.out.println("limt___"+stateAgiLimit);
    						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
    					//	standarddeductionRange
    					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.personalExcemption':" + stateName.getPersonalExcemption() + ",'marriedFilingJoint.standarddeduction':" + stateName.getStandardDeduction()+"}}");
    					

    						
    					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

    						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
    						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
    					
    						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
    						
    						
    						for(int i=0;i<rates1.length();i++){
    							ratesandBrakets.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
    							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

    							

    						}
    						
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets + "}}");
    						
    						for(int i=0;i<brakets1.length();i++){
    							
    							ratesandBrakets1.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
    							

    						}
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets1 + "}}");

    						
    					}
    					 else if(stateName.getFillingStatus().equals("Head of HouseHold")){
     						JSONArray standerdDeductionLimits=new JSONArray();
     						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
     						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
     						JSONArray maxAGIlimit=new JSONArray();
     						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
     						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
     						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
     						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

     						maxAGIlimit.put(stateName.getMaxAGIlimit());
     						maxAGIlimit.put(stateName.getMaxAGIlimit1());
     						maxAGIlimit.put(stateName.getMaxAGIlimit2());
     						maxAGIlimit.put(stateName.getMaxAGIlimit3());
     						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

     						JSONArray personalExcemptionlimit=new JSONArray();
     						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
     						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
     						personalExcemptionlimit.put(0);
     						//standerdDeductionLimit
     						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
     						
     						JSONArray standarddeductionRange=new JSONArray(); 
     						standarddeductionRange.put(stateName.getMinStandardDeduction());
     						standarddeductionRange.put(stateName.getMaxStandardDeduction());
     						JSONArray incomeLimit=new JSONArray(); 
     						incomeLimit.put(stateName.getIncomeLimits1());
     						incomeLimit.put(stateName.getIncomeLimits2());
     						incomeLimit.put(stateName.getIncomeLimits3());
     						incomeLimit.put(stateName.getIncomeLimits4());
     						////System.out.println("incomeLimit12---->>>>"+incomeLimit);
     						
     						JSONArray personalExcemptionsValues=new JSONArray(); 
     						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues1());
     						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues2());
     						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues3());
     						////System.out.println("personalExcemptionsValues--->>"+personalExcemptionsValues);
     						//standerdDeductionLimit
     						////System.out.println("single-----------------------");
     						////System.out.println("stateName.getPersonalExcemptionsValues1()--->>"+stateName.getPersonalExcemptionsValues1());
     						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
     						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

     						//	////System.out.println("limt___"+stateAgiLimit);
     						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
     					//	standarddeductionRange
     					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.personalExcemption':" + stateName.getPersonalExcemption() + ",'headOfHousehold.standarddeduction':" + stateName.getStandardDeduction()+"}}");
     					

     						
     					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

     						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
     						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
     					
     						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
     						
     						
     						for(int i=0;i<rates1.length();i++){
     							ratesandBrakets.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
     							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

     							

     						}
     						
     						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets + "}}");
     						
     						for(int i=0;i<brakets1.length();i++){
     							
     							ratesandBrakets1.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
     							

     						}
     						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets1 + "}}");

     						
     					}
    					
    					//////System.out.println("ratesandBrakets--->>"+ratesandBrakets);
    				
    				
    				responseFromRest.put("status", "success");
   			

				
			
			}
		else if(stateName.getState().equals("OHIO")){
   				////System.out.println("welcome OHIO-->>>");
   				

    				////System.out.println("inside-------++_-->>>");
    				////System.out.println("Actiontype--->>"+stateName.getActionType());
    				////System.out.println("stateName--->>"+stateName.getState());
    				////System.out.println("min stadaer-->>"+stateName.getMinStandardDeduction());
    				////System.out.println("minStandardDeduction-->>"+stateName.getMinAGIlimit());
    				////System.out.println("dependentExcemption-->>"+stateName.getDependentExcemption());
    				////System.out.println("tateName.getMaritalstatus()===>>"+stateName.getMaritalstatus());
    				////System.out.println("tateName.filling()===>>"+stateName.getFillingStatus());
    				////System.out.println("tateName.StandardDeductionAmt===>>"+stateName.getStandardDeductionAmt());
    				////System.out.println("StandardDeduction--->"+stateName.getStandardDeduction());
    				////System.out.println("personalExcemption----->>"+stateName.getPersonalExcemption());
    				////System.out.println("rates --->>>"+stateName.getRates());
    				////System.out.println("brakets --->>>"+stateName.getBrackets());
    				//JSONArray rates=new JSONArray();
    			//	JSONArray brakets=new JSONArray();
    				ArrayList rates = new ArrayList();
    				ArrayList brakets = new ArrayList();
    				Iterator crunchifyIterator =stateName.getRates().iterator();
    				while (crunchifyIterator.hasNext()) {
    				rates.add(crunchifyIterator.next());
    				}

    				Iterator crunchifyIterator1 =stateName.getBrackets().iterator();
    				while (crunchifyIterator1.hasNext()) {
    					brakets.add(crunchifyIterator1.next());
    				}
    				 Collections.sort(rates);
    				   Collections.sort(brakets);

    				
    				
    				   JSONArray rates1=new JSONArray(rates);
    				   JSONArray brakets1=new JSONArray(brakets);
    				
    			////System.out.println("aa1--->>>"+rates1);
    			////System.out.println("brakets1-->"+brakets1);
    		
    				
    				
    				double myDependentExcemption = stateName.getDependentExcemption();
    				int dependentExcemption = (int) myDependentExcemption;
    				BasicDBObject systemLogBasicObject = new BasicDBObject();
    				
    				JSONArray ratesandBrakets=new JSONArray();
    				
    				JSONArray ratesandBrakets1=new JSONArray();
    				
    				responseFromRest.put("status", "fail");	
    				
    				/*MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'StandedDeductionconstant':" + stateName.getMinStandardDeduction() + ",'minStandardDeduction':"+stateName.getMinAGIlimit()+"}}");*/
    				

    					if(stateName.getFillingStatus().equals("Single")){
    						JSONArray standerdDeductionLimits=new JSONArray();
    						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
    						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
    						JSONArray maxAGIlimit=new JSONArray();
    						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
    						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
    						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
    						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

    						maxAGIlimit.put(stateName.getMaxAGIlimit());
    						maxAGIlimit.put(stateName.getMaxAGIlimit1());
    						maxAGIlimit.put(stateName.getMaxAGIlimit2());
    						maxAGIlimit.put(stateName.getMaxAGIlimit3());
    						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

    						JSONArray personalExcemptionlimit=new JSONArray();
    						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
    						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
    						personalExcemptionlimit.put(0);
    						//standerdDeductionLimit
    						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
    						
    						JSONArray standarddeductionRange=new JSONArray(); 
    						standarddeductionRange.put(stateName.getMinStandardDeduction());
    						standarddeductionRange.put(stateName.getMaxStandardDeduction());
    						JSONArray incomeLimit=new JSONArray(); 
    						incomeLimit.put(stateName.getIncomeLimits1());
    						incomeLimit.put(stateName.getIncomeLimits2());
    						incomeLimit.put(stateName.getIncomeLimits3());
    						incomeLimit.put(stateName.getIncomeLimits4());
    						////System.out.println("incomeLimit12---->>>>"+incomeLimit);
    						
    						JSONArray personalExcemptionsValues=new JSONArray(); 
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues1());
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues2());
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues3());
    						////System.out.println("personalExcemptionsValues--->>"+personalExcemptionsValues);
    						//standerdDeductionLimit
    						////System.out.println("single-----------------------");
    						////System.out.println("stateName.getPersonalExcemptionsValues1()--->>"+stateName.getPersonalExcemptionsValues1());
    						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
    						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

    						//	////System.out.println("limt___"+stateAgiLimit);
    						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
    					//	standarddeductionRange
    					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.personalExcemption':" + stateName.getPersonalExcemption() + ",'single.standarddeduction':" + stateName.getStandardDeduction()+",'single.incomeLimit':"+incomeLimit+",'single.personalExcemptionsValues':"+personalExcemptionsValues+"}}");
    					

    						
    					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

    						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
    						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
    					
    						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
    						
    						
    						for(int i=0;i<rates1.length();i++){
    							ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
    							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

    							

    						}
    						
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets + "}}");
    						
    						for(int i=0;i<brakets1.length();i++){
    							
    							ratesandBrakets1.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
    							

    						}
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets1 + "}}");

    						
    					} else if(stateName.getFillingStatus().equals("Married Filling Jointly")){
    						JSONArray standerdDeductionLimits=new JSONArray();
    						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
    						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
    						JSONArray maxAGIlimit=new JSONArray();
    						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
    						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
    						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
    						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

    						maxAGIlimit.put(stateName.getMaxAGIlimit());
    						maxAGIlimit.put(stateName.getMaxAGIlimit1());
    						maxAGIlimit.put(stateName.getMaxAGIlimit2());
    						maxAGIlimit.put(stateName.getMaxAGIlimit3());
    						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

    						JSONArray personalExcemptionlimit=new JSONArray();
    						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
    						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
    						personalExcemptionlimit.put(0);
    						//standerdDeductionLimit
    						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
    						
    						JSONArray standarddeductionRange=new JSONArray(); 
    						standarddeductionRange.put(stateName.getMinStandardDeduction());
    						standarddeductionRange.put(stateName.getMaxStandardDeduction());
    						JSONArray incomeLimit=new JSONArray(); 
    						incomeLimit.put(stateName.getIncomeLimits1());
    						incomeLimit.put(stateName.getIncomeLimits2());
    						incomeLimit.put(stateName.getIncomeLimits3());
    						incomeLimit.put(stateName.getIncomeLimits4());
    						////System.out.println("incomeLimit12---->>>>"+incomeLimit);
    						
    						JSONArray personalExcemptionsValues=new JSONArray(); 
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues1());
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues2());
    						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues3());
    						////System.out.println("personalExcemptionsValues--->>"+personalExcemptionsValues);
    						//standerdDeductionLimit
    						////System.out.println("single-----------------------");
    						////System.out.println("stateName.getPersonalExcemptionsValues1()--->>"+stateName.getPersonalExcemptionsValues1());
    						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
    						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

    						//	////System.out.println("limt___"+stateAgiLimit);
    						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
    					//	standarddeductionRange
    					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.personalExcemption':" + stateName.getPersonalExcemption() + ",'marriedFilingJoint.standarddeduction':" + stateName.getStandardDeduction()+",'marriedFilingJoint.incomeLimit':"+incomeLimit+",'marriedFilingJoint.personalExcemptionsValues':"+personalExcemptionsValues+"}}");
    					

    						
    					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

    						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
    						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
    					
    						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
    						
    						
    						for(int i=0;i<rates1.length();i++){
    							ratesandBrakets.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
    							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

    							

    						}
    						
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets + "}}");
    						
    						for(int i=0;i<brakets1.length();i++){
    							
    							ratesandBrakets1.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
    							

    						}
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets1 + "}}");

    						
    					}
    					 else if(stateName.getFillingStatus().equals("Head of HouseHold")){
     						JSONArray standerdDeductionLimits=new JSONArray();
     						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
     						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
     						JSONArray maxAGIlimit=new JSONArray();
     						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
     						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
     						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
     						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

     						maxAGIlimit.put(stateName.getMaxAGIlimit());
     						maxAGIlimit.put(stateName.getMaxAGIlimit1());
     						maxAGIlimit.put(stateName.getMaxAGIlimit2());
     						maxAGIlimit.put(stateName.getMaxAGIlimit3());
     						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

     						JSONArray personalExcemptionlimit=new JSONArray();
     						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
     						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
     						personalExcemptionlimit.put(0);
     						//standerdDeductionLimit
     						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
     						
     						JSONArray standarddeductionRange=new JSONArray(); 
     						standarddeductionRange.put(stateName.getMinStandardDeduction());
     						standarddeductionRange.put(stateName.getMaxStandardDeduction());
     						JSONArray incomeLimit=new JSONArray(); 
     						incomeLimit.put(stateName.getIncomeLimits1());
     						incomeLimit.put(stateName.getIncomeLimits2());
     						incomeLimit.put(stateName.getIncomeLimits3());
     						incomeLimit.put(stateName.getIncomeLimits4());
     						////System.out.println("incomeLimit12---->>>>"+incomeLimit);
     						
     						JSONArray personalExcemptionsValues=new JSONArray(); 
     						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues1());
     						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues2());
     						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues3());
     						////System.out.println("personalExcemptionsValues--->>"+personalExcemptionsValues);
     						//standerdDeductionLimit
     						////System.out.println("single-----------------------");
     						////System.out.println("stateName.getPersonalExcemptionsValues1()--->>"+stateName.getPersonalExcemptionsValues1());
     						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
     						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

     						//	////System.out.println("limt___"+stateAgiLimit);
     						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
     					//	standarddeductionRange
     					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.personalExcemption':" + stateName.getPersonalExcemption() + ",'headOfHousehold.standarddeduction':" + stateName.getStandardDeduction()+",'headOfHousehold.incomeLimit':"+incomeLimit+",'headOfHousehold.personalExcemptionsValues':"+personalExcemptionsValues+"}}");
     					

     						
     					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

     						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
     						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
     					
     						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
     						
     						
     						for(int i=0;i<rates1.length();i++){
     							ratesandBrakets.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
     							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

     							

     						}
     						
     						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets + "}}");
     						
     						for(int i=0;i<brakets1.length();i++){
     							
     							ratesandBrakets1.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
     							

     						}
     						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets1 + "}}");

     						
     					}
    					
    					//////System.out.println("ratesandBrakets--->>"+ratesandBrakets);
    				
    				
    				responseFromRest.put("status", "success");
   			

			}
		else if(stateName.getState().equals("NEW MEXICO")){


   				////System.out.println("welcome MONTANA-->>>");

    				////System.out.println("inside-------++_-->>>");
    				////System.out.println("Actiontype--->>"+stateName.getActionType());
    				////System.out.println("stateName--->>"+stateName.getState());
    				////System.out.println("min stadaer-->>"+stateName.getMinStandardDeduction());
    				////System.out.println("minStandardDeduction-->>"+stateName.getMinAGIlimit());
    				////System.out.println("dependentExcemption-->>"+stateName.getDependentExcemption());
    				////System.out.println("tateName.getMaritalstatus()===>>"+stateName.getMaritalstatus());
    				////System.out.println("tateName.filling()===>>"+stateName.getFillingStatus());
    				////System.out.println("tateName.StandardDeductionAmt===>>"+stateName.getStandardDeductionAmt());
    				////System.out.println("StandardDeduction--->"+stateName.getStandardDeduction());
    				////System.out.println("personalExcemption----->>"+stateName.getPersonalExcemption());
    				////System.out.println("rates --->>>"+stateName.getRates());
    				////System.out.println("brakets --->>>"+stateName.getBrackets());
    				//JSONArray rates=new JSONArray();
    			//	JSONArray brakets=new JSONArray();
    				ArrayList rates = new ArrayList();
    				ArrayList brakets = new ArrayList();
    				Iterator crunchifyIterator =stateName.getRates().iterator();
    				while (crunchifyIterator.hasNext()) {
    					////System.out.println("lllll--->>>"+rates.add(crunchifyIterator.next()));
    				}

    				Iterator crunchifyIterator1 =stateName.getBrackets().iterator();
    				while (crunchifyIterator1.hasNext()) {
    					////System.out.println("lllll2--->>>"+brakets.add(crunchifyIterator1.next()));
    				}
    				 Collections.sort(rates);
    				   Collections.sort(brakets);

    				
    				
    				   JSONArray rates1=new JSONArray(rates);
    				   JSONArray brakets1=new JSONArray(brakets);
    				
    			////System.out.println("aa1--->>>"+rates1);
    			////System.out.println("brakets1-->"+brakets1);
    		
    				
    				
    				double myDependentExcemption = stateName.getDependentExcemption();
    				int dependentExcemption = (int) myDependentExcemption;
    				BasicDBObject systemLogBasicObject = new BasicDBObject();
    				
    				JSONArray ratesandBrakets=new JSONArray();
    				
    				JSONArray ratesandBrakets1=new JSONArray();
    				
    				responseFromRest.put("status", "fail");	
    				
    				/*MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'StandedDeductionconstant':" + stateName.getMinStandardDeduction() + ",'minStandardDeduction':"+stateName.getMinAGIlimit()+"}}");*/
    				

    					if(stateName.getFillingStatus().equals("Single")){
    						JSONArray standerdDeductionLimits=new JSONArray();
    						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
    						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
    						JSONArray maxAGIlimit=new JSONArray();
    						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
    						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
    						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
    						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

    						maxAGIlimit.put(stateName.getMaxAGIlimit());
    						maxAGIlimit.put(stateName.getMaxAGIlimit1());
    						maxAGIlimit.put(stateName.getMaxAGIlimit2());
    						maxAGIlimit.put(stateName.getMaxAGIlimit3());
    						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

    						JSONArray personalExcemptionlimit=new JSONArray();
    						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
    						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
    						personalExcemptionlimit.put(0);
    						//standerdDeductionLimit
    						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
    						
    						JSONArray standarddeductionRange=new JSONArray(); 
    						standarddeductionRange.put(stateName.getMinStandardDeduction());
    						standarddeductionRange.put(stateName.getMaxStandardDeduction());
    						JSONArray incomeLimit=new JSONArray(); 
    						incomeLimit.put(stateName.getIncomeLimits());
    						//standerdDeductionLimit
    						////System.out.println("single-----------------------");
    						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
    						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

    						//	////System.out.println("limt___"+stateAgiLimit);
    						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
    					//	standarddeductionRange
    					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.personalExcemption':" + stateName.getPersonalExcemption() + ",'single.standarddeduction':" + stateName.getStandardDeduction()+",'otherDeduction':"+stateName.getOtherDeduction()+",'incomeLimit':"+incomeLimit+"}}");
    					

    						
    					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

    						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
    						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
    					
    						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
    						
    						
    						for(int i=0;i<rates1.length();i++){
    							ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
    							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

    							

    						}
    						
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets + "}}");
    						
    						for(int i=0;i<brakets1.length();i++){
    							
    							ratesandBrakets1.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
    							

    						}
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets1 + "}}");

    						
    					} else if(stateName.getFillingStatus().equals("Married Filling Jointly")){
    						JSONArray standerdDeductionLimits=new JSONArray();
    						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
    						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
    						JSONArray maxAGIlimit=new JSONArray();
    						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
    						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
    						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
    						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

    						maxAGIlimit.put(stateName.getMaxAGIlimit());
    						maxAGIlimit.put(stateName.getMaxAGIlimit1());
    						maxAGIlimit.put(stateName.getMaxAGIlimit2());
    						maxAGIlimit.put(stateName.getMaxAGIlimit3());
    						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

    						JSONArray personalExcemptionlimit=new JSONArray();
    						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
    						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
    						personalExcemptionlimit.put(0);
    						//standerdDeductionLimit
    						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
    						
    						JSONArray standarddeductionRange=new JSONArray(); 
    						standarddeductionRange.put(stateName.getMinStandardDeduction());
    						standarddeductionRange.put(stateName.getMaxStandardDeduction());
    						JSONArray incomeLimit=new JSONArray(); 
    						incomeLimit.put(stateName.getIncomeLimits());
    						//standerdDeductionLimit
    						////System.out.println("single-----------------------");
    						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
    						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

    						//	////System.out.println("limt___"+stateAgiLimit);
    						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
    					//	standarddeductionRange
    					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.personalExcemption':" + stateName.getPersonalExcemption() + ",'marriedFilingJoint.standarddeduction':" + stateName.getStandardDeduction()+",'otherDeduction':"+stateName.getOtherDeduction()+",'marriedFilingJoint.incomeLimit':"+incomeLimit+"}}");
    					

    						
    					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

    						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
    						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
    					
    						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
    						
    						
    						for(int i=0;i<rates1.length();i++){
    							ratesandBrakets.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
    							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

    							

    						}
    						
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets + "}}");
    						
    						for(int i=0;i<brakets1.length();i++){
    							
    							ratesandBrakets1.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
    							

    						}
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets1 + "}}");

    						
    					}
    					 else if(stateName.getFillingStatus().equals("Head of HouseHold")){
     						JSONArray standerdDeductionLimits=new JSONArray();
     						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
     						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
     						JSONArray maxAGIlimit=new JSONArray();
     						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
     						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
     						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
     						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

     						maxAGIlimit.put(stateName.getMaxAGIlimit());
     						maxAGIlimit.put(stateName.getMaxAGIlimit1());
     						maxAGIlimit.put(stateName.getMaxAGIlimit2());
     						maxAGIlimit.put(stateName.getMaxAGIlimit3());
     						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

     						JSONArray personalExcemptionlimit=new JSONArray();
     						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
     						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
     						personalExcemptionlimit.put(0);
     						//standerdDeductionLimit
     						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
     						
     						JSONArray standarddeductionRange=new JSONArray(); 
     						standarddeductionRange.put(stateName.getMinStandardDeduction());
     						standarddeductionRange.put(stateName.getMaxStandardDeduction());
     						JSONArray incomeLimit=new JSONArray(); 
     						incomeLimit.put(stateName.getIncomeLimits());
     						//standerdDeductionLimit
     						////System.out.println("single-----------------------");
     						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
     						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

     						//	////System.out.println("limt___"+stateAgiLimit);
     						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
     					//	standarddeductionRange
     					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.personalExcemption':" + stateName.getPersonalExcemption() + ",'headOfHousehold.standarddeduction':" + stateName.getStandardDeduction()+",'otherDeduction':"+stateName.getOtherDeduction()+",'headOfHousehold.incomeLimit':"+incomeLimit+"}}");
     					

     						
     					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

     						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
     						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
     					
     						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
     						
     						
     						for(int i=0;i<rates1.length();i++){
     							ratesandBrakets.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
     							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

     							

     						}
     						
     						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets + "}}");
     						
     						for(int i=0;i<brakets1.length();i++){
     							
     							ratesandBrakets1.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
     							

     						}
     						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets1 + "}}");

     						
     					}
    					
    					//////System.out.println("ratesandBrakets--->>"+ratesandBrakets);
    				
    				
    				responseFromRest.put("status", "success");
   			
			
				
			}
		else if(stateName.getState().equals("MONTANA")){

   				////System.out.println("welcome MONTANA-->>>");

    				////System.out.println("inside-------++_-->>>");
    				////System.out.println("Actiontype--->>"+stateName.getActionType());
    				////System.out.println("stateName--->>"+stateName.getState());
    				////System.out.println("min stadaer-->>"+stateName.getMinStandardDeduction());
    				////System.out.println("minStandardDeduction-->>"+stateName.getMinAGIlimit());
    				////System.out.println("dependentExcemption-->>"+stateName.getDependentExcemption());
    				////System.out.println("tateName.getMaritalstatus()===>>"+stateName.getMaritalstatus());
    				////System.out.println("tateName.filling()===>>"+stateName.getFillingStatus());
    				////System.out.println("tateName.StandardDeductionAmt===>>"+stateName.getStandardDeductionAmt());
    				////System.out.println("StandardDeduction--->"+stateName.getStandardDeduction());
    				////System.out.println("personalExcemption----->>"+stateName.getPersonalExcemption());
    				////System.out.println("rates --->>>"+stateName.getRates());
    				////System.out.println("brakets --->>>"+stateName.getBrackets());
    				//JSONArray rates=new JSONArray();
    			//	JSONArray brakets=new JSONArray();
    				ArrayList rates = new ArrayList();
    				ArrayList brakets = new ArrayList();
    				Iterator crunchifyIterator =stateName.getRates().iterator();
    				while (crunchifyIterator.hasNext()) {
    					////System.out.println("lllll--->>>"+rates.add(crunchifyIterator.next()));
    				}

    				Iterator crunchifyIterator1 =stateName.getBrackets().iterator();
    				while (crunchifyIterator1.hasNext()) {
    					////System.out.println("lllll2--->>>"+brakets.add(crunchifyIterator1.next()));
    				}
    				 Collections.sort(rates);
    				   Collections.sort(brakets);

    				
    				
    				   JSONArray rates1=new JSONArray(rates);
    				   JSONArray brakets1=new JSONArray(brakets);
    				
    			////System.out.println("aa1--->>>"+rates1);
    			////System.out.println("brakets1-->"+brakets1);
    		
    				
    				
    				double myDependentExcemption = stateName.getDependentExcemption();
    				int dependentExcemption = (int) myDependentExcemption;
    				BasicDBObject systemLogBasicObject = new BasicDBObject();
    				
    				JSONArray ratesandBrakets=new JSONArray();
    				
    				JSONArray ratesandBrakets1=new JSONArray();
    				
    				responseFromRest.put("status", "fail");	
    				
    				/*MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'StandedDeductionconstant':" + stateName.getMinStandardDeduction() + ",'minStandardDeduction':"+stateName.getMinAGIlimit()+"}}");*/
    				

    					if(stateName.getFillingStatus().equals("Single")){
    						JSONArray standerdDeductionLimits=new JSONArray();
    						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
    						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
    						JSONArray maxAGIlimit=new JSONArray();
    						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
    						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
    						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
    						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

    						maxAGIlimit.put(stateName.getMaxAGIlimit());
    						maxAGIlimit.put(stateName.getMaxAGIlimit1());
    						maxAGIlimit.put(stateName.getMaxAGIlimit2());
    						maxAGIlimit.put(stateName.getMaxAGIlimit3());
    						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

    						JSONArray personalExcemptionlimit=new JSONArray();
    						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
    						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
    						personalExcemptionlimit.put(0);
    						//standerdDeductionLimit
    						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
    						
    						JSONArray standarddeductionRange=new JSONArray(); 
    						standarddeductionRange.put(stateName.getMinStandardDeduction());
    						standarddeductionRange.put(stateName.getMaxStandardDeduction());
    						//standerdDeductionLimit
    						////System.out.println("single-----------------------");
    						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
    						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

    						//	////System.out.println("limt___"+stateAgiLimit);
    						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
    					//	standarddeductionRange
    					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.personalExcemption':" + stateName.getPersonalExcemption() + ",'single.standarddeduction':" + stateName.getStandardDeduction()+",'single.standerdDeductionRate':"+(stateName.getStanderdDeductionRate()/100)+",'single.standarddeductionRange':"+standarddeductionRange+"}}");

    						
    					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

    						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
    						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
    					
    						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
    						
    						
    						for(int i=0;i<rates1.length();i++){
    							ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
    							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

    							

    						}
    						
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets + "}}");
    						
    						for(int i=0;i<brakets1.length();i++){
    							
    							ratesandBrakets1.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
    							

    						}
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets1 + "}}");

    						
    					} else if(stateName.getFillingStatus().equals("Married Filling Jointly")){
    						JSONArray standerdDeductionLimits=new JSONArray();
    						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
    						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
    						JSONArray maxAGIlimit=new JSONArray();
    						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
    						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
    						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
    						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

    						maxAGIlimit.put(stateName.getMaxAGIlimit());
    						maxAGIlimit.put(stateName.getMaxAGIlimit1());
    						maxAGIlimit.put(stateName.getMaxAGIlimit2());
    						maxAGIlimit.put(stateName.getMaxAGIlimit3());
    						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

    						JSONArray personalExcemptionlimit=new JSONArray();
    						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
    						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
    						personalExcemptionlimit.put(0);
    						//standerdDeductionLimit
    						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
    						
    						JSONArray standarddeductionRange=new JSONArray(); 
    						standarddeductionRange.put(stateName.getMinStandardDeduction());
    						standarddeductionRange.put(stateName.getMaxStandardDeduction());
    						//standerdDeductionLimit
    						////System.out.println("single-----------------------");
    						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
    						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

    						//	////System.out.println("limt___"+stateAgiLimit);
    						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
    					//	standarddeductionRange
    					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.personalExcemption':" + stateName.getPersonalExcemption() + ",'marriedFilingJoint.standarddeduction':" + stateName.getStandardDeduction()+",'marriedFilingJoint.standerdDeductionRate':"+(stateName.getStanderdDeductionRate()/100)+",'marriedFilingJoint.standarddeductionRange':"+standarddeductionRange+"}}");

    						
    					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

    						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
    						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
    					
    						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
    						
    						
    						for(int i=0;i<rates1.length();i++){
    							ratesandBrakets.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
    							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

    							

    						}
    						
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets + "}}");
    						
    						for(int i=0;i<brakets1.length();i++){
    							
    							ratesandBrakets1.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
    							

    						}
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets1 + "}}");

    						
    					}
    					 else if(stateName.getFillingStatus().equals("Head of HouseHold")){
     						JSONArray standerdDeductionLimits=new JSONArray();
     						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
     						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
     						JSONArray maxAGIlimit=new JSONArray();
     						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
     						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
     						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
     						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

     						maxAGIlimit.put(stateName.getMaxAGIlimit());
     						maxAGIlimit.put(stateName.getMaxAGIlimit1());
     						maxAGIlimit.put(stateName.getMaxAGIlimit2());
     						maxAGIlimit.put(stateName.getMaxAGIlimit3());
     						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

     						JSONArray personalExcemptionlimit=new JSONArray();
     						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
     						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
     						personalExcemptionlimit.put(0);
     						//standerdDeductionLimit
     						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
     						
     						JSONArray standarddeductionRange=new JSONArray(); 
     						standarddeductionRange.put(stateName.getMinStandardDeduction());
     						standarddeductionRange.put(stateName.getMaxStandardDeduction());
     						//standerdDeductionLimit
     						////System.out.println("single-----------------------");
     						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
     						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

     						//	////System.out.println("limt___"+stateAgiLimit);
     						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
     					//	standarddeductionRange
     					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.personalExcemption':" + stateName.getPersonalExcemption() + ",'headOfHousehold.standarddeduction':" + stateName.getStandardDeduction()+",'headOfHousehold.standerdDeductionRate':"+(stateName.getStanderdDeductionRate()/100)+",'headOfHousehold.standarddeductionRange':"+standarddeductionRange+"}}");

     						
     					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

     						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
     						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
     					
     						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
     						
     						
     						for(int i=0;i<rates1.length();i++){
     							ratesandBrakets.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
     							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

     							

     						}
     						
     						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets + "}}");
     						
     						for(int i=0;i<brakets1.length();i++){
     							
     							ratesandBrakets1.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
     							

     						}
     						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets1 + "}}");

     						
     					}
    					
    					//////System.out.println("ratesandBrakets--->>"+ratesandBrakets);
    				
    				
    				responseFromRest.put("status", "success");
   			
			}
		else if(stateName.getState().equals("MARYLAND")){
   				////System.out.println("welcome MARYLAND-->>>");

    				////System.out.println("inside-------++_-->>>");
    				////System.out.println("Actiontype--->>"+stateName.getActionType());
    				////System.out.println("stateName--->>"+stateName.getState());
    				////System.out.println("min stadaer-->>"+stateName.getMinStandardDeduction());
    				////System.out.println("minStandardDeduction-->>"+stateName.getMinAGIlimit());
    				////System.out.println("dependentExcemption-->>"+stateName.getDependentExcemption());
    				////System.out.println("tateName.getMaritalstatus()===>>"+stateName.getMaritalstatus());
    				////System.out.println("tateName.filling()===>>"+stateName.getFillingStatus());
    				////System.out.println("tateName.StandardDeductionAmt===>>"+stateName.getStandardDeductionAmt());
    				////System.out.println("StandardDeduction--->"+stateName.getStandardDeduction());
    				////System.out.println("personalExcemption----->>"+stateName.getPersonalExcemption());
    				////System.out.println("rates --->>>"+stateName.getRates());
    				////System.out.println("brakets --->>>"+stateName.getBrackets());
    				//JSONArray rates=new JSONArray();
    			//	JSONArray brakets=new JSONArray();
    				ArrayList rates = new ArrayList();
    				ArrayList brakets = new ArrayList();
    				Iterator crunchifyIterator =stateName.getRates().iterator();
    				while (crunchifyIterator.hasNext()) {
    					////System.out.println("lllll--->>>"+rates.add(crunchifyIterator.next()));
    				}

    				Iterator crunchifyIterator1 =stateName.getBrackets().iterator();
    				while (crunchifyIterator1.hasNext()) {
    					////System.out.println("lllll2--->>>"+brakets.add(crunchifyIterator1.next()));
    				}
    				 Collections.sort(rates);
    				   Collections.sort(brakets);

    				
    				
    				   JSONArray rates1=new JSONArray(rates);
    				   JSONArray brakets1=new JSONArray(brakets);
    				
    			////System.out.println("aa1--->>>"+rates1);
    			////System.out.println("brakets1-->"+brakets1);
    		
    				
    				
    				double myDependentExcemption = stateName.getDependentExcemption();
    				int dependentExcemption = (int) myDependentExcemption;
    				BasicDBObject systemLogBasicObject = new BasicDBObject();
    				
    				JSONArray ratesandBrakets=new JSONArray();
    				
    				JSONArray ratesandBrakets1=new JSONArray();
    				
    				responseFromRest.put("status", "fail");	
    				
    				/*MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'StandedDeductionconstant':" + stateName.getMinStandardDeduction() + ",'minStandardDeduction':"+stateName.getMinAGIlimit()+"}}");*/
    				

    					if(stateName.getFillingStatus().equals("Single")){
    						JSONArray standerdDeductionLimits=new JSONArray();
    						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
    						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
    						JSONArray maxAGIlimit=new JSONArray();
    						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
    						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
    						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
    						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

    						maxAGIlimit.put(stateName.getMaxAGIlimit());
    						maxAGIlimit.put(stateName.getMaxAGIlimit1());
    						maxAGIlimit.put(stateName.getMaxAGIlimit2());
    						maxAGIlimit.put(stateName.getMaxAGIlimit3());
    						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

    						JSONArray personalExcemptionlimit=new JSONArray();
    						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
    						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
    						personalExcemptionlimit.put(0);
    						//standerdDeductionLimit
    						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
    						//standerdDeductionLimit
    						////System.out.println("single-----------------------");
    						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
    						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

    						//	////System.out.println("limt___"+stateAgiLimit);
    						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.personalExcemption':" + stateName.getPersonalExcemption() + ",'single.standarddeduction':" + stateName.getStandardDeduction()+",'single.standerdDeductionRate':"+(stateName.getStanderdDeductionRate()/100)+",'single.standerdDeductionLimit':"+standerdDeductionLimits+",'single.stateAgiLimit':"+maxAGIlimit+",'single.personalExcemptionlimit':"+personalExcemptionlimit+"}}");

    						
    					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

    						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
    						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
    					
    						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
    						
    						
    						for(int i=0;i<rates1.length();i++){
    							ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
    							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

    							

    						}
    						
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets + "}}");
    						
    						for(int i=0;i<brakets1.length();i++){
    							
    							ratesandBrakets1.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
    							

    						}
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets1 + "}}");

    						
    					} else if(stateName.getFillingStatus().equals("Married Filling Jointly")){
    						JSONArray standerdDeductionLimits=new JSONArray();
    						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
    						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
    						JSONArray maxAGIlimit=new JSONArray();
    						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
    						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
    						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
    						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

    						maxAGIlimit.put(stateName.getMaxAGIlimit());
    						maxAGIlimit.put(stateName.getMaxAGIlimit1());
    						maxAGIlimit.put(stateName.getMaxAGIlimit2());
    						maxAGIlimit.put(stateName.getMaxAGIlimit3());
    						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

    						JSONArray personalExcemptionlimit=new JSONArray();
    						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
    						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
    						personalExcemptionlimit.put(0);
    						//standerdDeductionLimit
    						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
    						//standerdDeductionLimit
    						////System.out.println("single-----------------------");
    						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
    						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

    						//	////System.out.println("limt___"+stateAgiLimit);
    						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.personalExcemption':" + stateName.getPersonalExcemption() + ",'marriedFilingJoint.standarddeduction':" + stateName.getStandardDeduction()+",'marriedFilingJoint.standerdDeductionRate':"+(stateName.getStanderdDeductionRate()/100)+",'marriedFilingJoint.standerdDeductionLimit':"+standerdDeductionLimits+",'marriedFilingJoint.stateAgiLimit':"+maxAGIlimit+",'marriedFilingJoint.personalExcemptionlimit':"+personalExcemptionlimit+"}}");

    						
    					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

    						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
    						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
    					
    						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
    						
    						
    						for(int i=0;i<rates1.length();i++){
    							ratesandBrakets.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
    							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

    							

    						}
    						
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets + "}}");
    						
    						for(int i=0;i<brakets1.length();i++){
    							
    							ratesandBrakets1.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
    							

    						}
    						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets1 + "}}");

    						
    					}
    					 else if(stateName.getFillingStatus().equals("Head of HouseHold")){	
    				    JSONArray standerdDeductionLimits=new JSONArray();
 						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
 						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
 						JSONArray maxAGIlimit=new JSONArray();
 						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
 						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
 						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
 						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

 						maxAGIlimit.put(stateName.getMaxAGIlimit());
 						maxAGIlimit.put(stateName.getMaxAGIlimit1());
 						maxAGIlimit.put(stateName.getMaxAGIlimit2());
 						maxAGIlimit.put(stateName.getMaxAGIlimit3());
 						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

 						JSONArray personalExcemptionlimit=new JSONArray();
 						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
 						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
 						personalExcemptionlimit.put(0);
 						//standerdDeductionLimit
 						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
 						//standerdDeductionLimit
 						////System.out.println("single-----------------------");
 						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
 						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

 						//	////System.out.println("limt___"+stateAgiLimit);
 						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
 						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.personalExcemption':" + stateName.getPersonalExcemption() + ",'headOfHousehold.standarddeduction':" + stateName.getStandardDeduction()+",'headOfHousehold.standerdDeductionRate':"+(stateName.getStanderdDeductionRate()/100)+",'headOfHousehold.standerdDeductionLimit':"+standerdDeductionLimits+",'headOfHousehold.stateAgiLimit':"+maxAGIlimit+",'headOfHousehold.personalExcemptionlimit':"+personalExcemptionlimit+"}}");

 						
 					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

 						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
 						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
 					
 						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
 						
 						
 						for(int i=0;i<rates1.length();i++){
 							ratesandBrakets.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
 							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

 							

 						}
 						
 						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets + "}}");
 						
 						for(int i=0;i<brakets1.length();i++){
 							
 							ratesandBrakets1.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
 							

 						}
 						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets1 + "}}");
}
    					
    					//////System.out.println("ratesandBrakets--->>"+ratesandBrakets);
    				
    				
    				responseFromRest.put("status", "success");
    				
    				
    			
                }		
			else if(stateName.getState().equals("INDIANA")){
				//System.out.println("welcome indiana-->>");
				////System.out.println("welcome INDIANA-->>>");

				////System.out.println("inside-------++_-->>>");
				////System.out.println("Actiontype--->>"+stateName.getActionType());
				////System.out.println("stateName--->>"+stateName.getState());
				////System.out.println("min stadaer-->>"+stateName.getMinStandardDeduction());
				////System.out.println("minStandardDeduction-->>"+stateName.getMinAGIlimit());
				////System.out.println("dependentExcemption-->>"+stateName.getDependentExcemption());
				////System.out.println("tateName.getMaritalstatus()===>>"+stateName.getMaritalstatus());
				//System.out.println("tateName.filling()===>>"+stateName.getFillingStatus());
				////System.out.println("tateName.StandardDeductionAmt===>>"+stateName.getStandardDeductionAmt());
				////System.out.println("StandardDeduction--->"+stateName.getStandardDeduction());
				////System.out.println("personalExcemption----->>"+stateName.getPersonalExcemption());
				////System.out.println("rates --->>>"+stateName.getRates());
				////System.out.println("brakets --->>>"+stateName.getBrackets());
				//JSONArray rates=new JSONArray();
			//	JSONArray brakets=new JSONArray();
				ArrayList rates = new ArrayList();
				ArrayList brakets = new ArrayList();
				Iterator crunchifyIterator =stateName.getRates().iterator();
				//System.out.println("iiiipppp");
				while (crunchifyIterator.hasNext()) {
					//System.out.println("lllll--->>>"+rates.add(crunchifyIterator.next()));
				}
//System.out.println("1------>>");
				Iterator crunchifyIterator1 =stateName.getBrackets().iterator();
				while (crunchifyIterator1.hasNext()) {
					//System.out.println("lllll2--->>>"+brakets.add(crunchifyIterator1.next()));
				}
				 Collections.sort(rates);
				   Collections.sort(brakets);

				
				   //System.out.println("2------>>");

				   JSONArray rates1=new JSONArray(rates);
				   JSONArray brakets1=new JSONArray(brakets);
				
			////System.out.println("aa1--->>>"+rates1);
			////System.out.println("brakets1-->"+brakets1);
		
				
				
			//	double myDependentExcemption = stateName.getDependentExcemption();
			//	int dependentExcemption = (int) myDependentExcemption;
			//	BasicDBObject systemLogBasicObject = new BasicDBObject();
				
				JSONArray ratesandBrakets=new JSONArray();
				
				JSONArray ratesandBrakets1=new JSONArray();
				
				responseFromRest.put("status", "fail");	
				
				/*MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'StandedDeductionconstant':" + stateName.getMinStandardDeduction() + ",'minStandardDeduction':"+stateName.getMinAGIlimit()+"}}");*/
				
//System.out.println("before-->>");
					if(stateName.getFillingStatus().equals("Single")){
						
						//System.out.println("welcome-->>");
					//	////System.out.println("stateAgiLimit--->>"+stateAgiLimit);
						
						//System.out.println("single-----------------------"+stateName.getAge());
						//System.out.println("stateName.getAditionalExcemtion()-->>"+stateName.getAditionalExcemtion());
					
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.personalExcemption':" + stateName.getPersonalExcemption() + ",'single.standarddeduction':" + stateName.getStandardDeduction()+",'dependentExcemption':"+stateName.getAge()+",'aditionalExcemtion':"+stateName.getAditionalExcemtion()+"}}");

						
					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
					
						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
						
						
						for(int i=0;i<rates1.length();i++){
							ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

							

						}
						
					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets + "}}");
						
						for(int i=0;i<brakets1.length();i++){
							
							ratesandBrakets1.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
							

						}
				MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets1 + "}}");

						
					} else if(stateName.getFillingStatus().equals("Married Filling Jointly")){
						
						
					//	////System.out.println("stateAgiLimit--->>"+stateAgiLimit);
						
						////System.out.println("Married Filling Jointly-----------------------");
					
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.personalExcemption':" + stateName.getPersonalExcemption() + ",'marriedFilingJoint.standarddeduction':" + stateName.getStandardDeduction()+",'dependentExcemption':"+stateName.getAge()+",'aditionalExcemtion':"+stateName.getAditionalExcemtion()+"}}");

						
					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
					
						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
						
						
						for(int i=0;i<rates1.length();i++){
							ratesandBrakets.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

							

						}
						
					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets + "}}");
						
						for(int i=0;i<brakets1.length();i++){
							
							ratesandBrakets1.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
							

						}
				MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets1 + "}}");

						
					}
					 else if(stateName.getFillingStatus().equals("Head of HouseHold")){
							
							
							//	////System.out.println("stateAgiLimit--->>"+stateAgiLimit);
								
								////System.out.println("Married Filling Jointly-----------------------");
							
								MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.personalExcemption':" + stateName.getPersonalExcemption() + ",'headOfHousehold.standarddeduction':" + stateName.getStandardDeduction()+",'dependentExcemption':"+stateName.getAge()+",'aditionalExcemtion':"+stateName.getAditionalExcemtion()+"}}");

								
							//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

								StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
								responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
							
								//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
								
								
								for(int i=0;i<rates1.length();i++){
									ratesandBrakets.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
									//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

									

								}
								
							MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets + "}}");
								
								for(int i=0;i<brakets1.length();i++){
									
									ratesandBrakets1.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
									

								}
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets1 + "}}");

								
							}
					
					//System.out.println("ratesandBrakets--->>"+ratesandBrakets);
				
				
				responseFromRest.put("status", "success");
				
			
				
				
				
				
			}
			
			else if(stateName.getState().equals("CALIFORNIA")){
				////System.out.println("welcome CALIFORNIA-->>>");

				////System.out.println("inside-------++_-->>>");
				////System.out.println("Actiontype--->>"+stateName.getActionType());
				////System.out.println("stateName--->>"+stateName.getState());
				////System.out.println("min stadaer-->>"+stateName.getMinStandardDeduction());
				////System.out.println("minStandardDeduction-->>"+stateName.getMinAGIlimit());
				////System.out.println("dependentExcemption-->>"+stateName.getDependentExcemption());
				////System.out.println("tateName.getMaritalstatus()===>>"+stateName.getMaritalstatus());
				////System.out.println("tateName.filling()===>>"+stateName.getFillingStatus());
				////System.out.println("tateName.StandardDeductionAmt===>>"+stateName.getStandardDeductionAmt());
				////System.out.println("StandardDeduction--->"+stateName.getStandardDeduction());
				////System.out.println("personalExcemption----->>"+stateName.getPersonalExcemption());
				////System.out.println("rates --->>>"+stateName.getRates());
				////System.out.println("brakets --->>>"+stateName.getBrackets());
				//JSONArray rates=new JSONArray();
			//	JSONArray brakets=new JSONArray();
				ArrayList rates = new ArrayList();
				ArrayList brakets = new ArrayList();
				Iterator crunchifyIterator =stateName.getRates().iterator();
				while (crunchifyIterator.hasNext()) {
					rates.add(crunchifyIterator.next());
				}

				Iterator crunchifyIterator1 =stateName.getBrackets().iterator();
				while (crunchifyIterator1.hasNext()) {
				brakets.add(crunchifyIterator1.next());
				}
				////System.out.println("rates--->>"+rates);
				 Collections.sort(rates);
				   Collections.sort(brakets);

				////System.out.println("rates1--->>"+rates);
				////System.out.println("brakets--->>"+brakets);
				
				   JSONArray rates1=new JSONArray(rates);
				   JSONArray brakets1=new JSONArray(brakets);
				
			////System.out.println("aa1--->>>"+rates1);
			////System.out.println("brakets1-->"+brakets1);
		
				
				
				double myDependentExcemption = stateName.getDependentExcemption();
				int dependentExcemption = (int) myDependentExcemption;
				BasicDBObject systemLogBasicObject = new BasicDBObject();
				
				JSONArray ratesandBrakets=new JSONArray();
				
				JSONArray ratesandBrakets1=new JSONArray();
				
				responseFromRest.put("status", "fail");	
				
				/*MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'StandedDeductionconstant':" + stateName.getMinStandardDeduction() + ",'minStandardDeduction':"+stateName.getMinAGIlimit()+"}}");*/
				

					if(stateName.getFillingStatus().equals("Single")){
						JSONArray stateAgiLimit=new JSONArray();
						stateAgiLimit.put(stateName.getMinAGIlimitMax());
						stateAgiLimit.put(0);
						stateAgiLimit.put(0);
						stateAgiLimit.put(0);
						
					//	////System.out.println("stateAgiLimit--->>"+stateAgiLimit);
						
						////System.out.println("single-----------------------");
						////System.out.println("limt___"+stateAgiLimit);
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.personalExcemption':" + stateName.getPersonalExcemption() + ",'single.standarddeduction':" + stateName.getStandardDeduction()+"}}");

						
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.exemptionCreditForEachAgi':" + stateName.getExemptionCreditForEachAgi() + ",'single.exemptionCreditPhaseOut':" + stateName.getExemptionCreditPhaseOut()+"}}");
						//System.out.println("exemptionnn===" + stateName.getExemptionCreditForEachAgi() +"exemption pashout"+ stateName.getExemptionCreditPhaseOut());
                  
						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
					
						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
						
						
						for(int i=0;i<rates1.length();i++){
							ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

							

						}
						
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets + "}}");
						
						for(int i=0;i<brakets1.length();i++){
							
							ratesandBrakets1.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
							

						}
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets1 + "}}");

						
					} else if(stateName.getFillingStatus().equals("Married Filling Jointly")){
						JSONArray stateAgiLimit=new JSONArray();
						stateAgiLimit.put(stateName.getMinAGIlimitMax());
						stateAgiLimit.put(0);
						stateAgiLimit.put(0);
						stateAgiLimit.put(0);
						
					//	////System.out.println("stateAgiLimit--->>"+stateAgiLimit);
						
						////System.out.println("Married Filling Jointly-----------------------");
						////System.out.println("limt___"+stateAgiLimit);
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.personalExcemption':" + stateName.getPersonalExcemption() + ",'marriedFilingJoint.standarddeduction':" + stateName.getStandardDeduction()+"}}");

						
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.stateAgiLimit':" +stateAgiLimit+"}}");

						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.exemptionCreditForEachAgi':" + stateName.getExemptionCreditForEachAgi() + ",'marriedFilingJoint.exemptionCreditPhaseOut':" + stateName.getExemptionCreditPhaseOut()+"}}");

					
						
						
						for(int i=0;i<rates1.length();i++){
							ratesandBrakets.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));

							

						}
						
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets + "}}");
						
						for(int i=0;i<brakets1.length();i++){
							
							ratesandBrakets1.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
							

						}
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets1 + "}}");

						
					}
					 else if(stateName.getFillingStatus().equals("Head of HouseHold")){
							JSONArray stateAgiLimit=new JSONArray();
							stateAgiLimit.put(stateName.getMinAGIlimitMax());
							stateAgiLimit.put(0);
							stateAgiLimit.put(0);
							stateAgiLimit.put(0);
							
						//	////System.out.println("stateAgiLimit--->>"+stateAgiLimit);
							
							////System.out.println("Head of HouseHold-----------------------");
							////System.out.println("limt___"+stateAgiLimit);
							MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.personalExcemption':" + stateName.getPersonalExcemption() + ",'headOfHousehold.standarddeduction':" + stateName.getStandardDeduction()+"}}");

							
							MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.stateAgiLimit':" +stateAgiLimit+"}}");
							MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.exemptionCreditForEachAgi':" + stateName.getExemptionCreditForEachAgi() + ",'headOfHousehold.exemptionCreditPhaseOut':" + stateName.getExemptionCreditPhaseOut()+"}}");


							StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
							responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
						
							
							
							for(int i=0;i<rates1.length();i++){
								ratesandBrakets.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));

								

							}
							
							MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets + "}}");
							
							for(int i=0;i<brakets1.length();i++){
								
								ratesandBrakets1.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
								

							}
							MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets1 + "}}");

							
						}
					
					//////System.out.println("ratesandBrakets--->>"+ratesandBrakets);
				
				
				responseFromRest.put("status", "success");
				
			
				
				
				
				
			}
		else if(stateName.getState().equals("ALABAMA")){
			////System.out.println("inside-------++_-->>>");
			////System.out.println("Actiontype--->>"+stateName.getActionType());
			////System.out.println("stateName--->>"+stateName.getState());
			////System.out.println("min stadaer-->>"+stateName.getMinStandardDeduction());
			////System.out.println("minStandardDeduction-->>"+stateName.getMinAGIlimit());
			////System.out.println("dependentExcemption-->>"+stateName.getDependentExcemption());
			////System.out.println("tateName.getMaritalstatus()===>>"+stateName.getMaritalstatus());
			////System.out.println("tateName.filling()===>>"+stateName.getFillingStatus());
			////System.out.println("tateName.StandardDeductionAmt===>>"+stateName.getStandardDeductionAmt());
			////System.out.println("StandardDeduction--->"+stateName.getStandardDeduction());
			////System.out.println("personalExcemption----->>"+stateName.getPersonalExcemption());
			////System.out.println("rates --->>>"+stateName.getRates());
			////System.out.println("brakets --->>>"+stateName.getBrackets());
			//JSONArray rates=new JSONArray();
		//	JSONArray brakets=new JSONArray();
			ArrayList rates = new ArrayList();
			ArrayList brakets = new ArrayList();
			Iterator crunchifyIterator =stateName.getRates().iterator();
			while (crunchifyIterator.hasNext()) {
				rates.add(crunchifyIterator.next());
			}

			Iterator crunchifyIterator1 =stateName.getBrackets().iterator();
			while (crunchifyIterator1.hasNext()) {
				brakets.add(crunchifyIterator1.next());
			}
			 Collections.sort(rates);
			   Collections.sort(brakets);

			
			
			   JSONArray rates1=new JSONArray(rates);
			   JSONArray brakets1=new JSONArray(brakets);
			
		////System.out.println("aa--->>>"+rates1);
		////System.out.println("brakets-->"+brakets1);
	
			
			
			double myDependentExcemption = stateName.getDependentExcemption();
			int dependentExcemption = (int) myDependentExcemption;
			BasicDBObject systemLogBasicObject = new BasicDBObject();
			
			JSONArray ratesandBrakets=new JSONArray();
			
			JSONArray ratesandBrakets1=new JSONArray();
			
			responseFromRest.put("status", "fail");	
			
			/*MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'StandedDeductionconstant':" + stateName.getMinStandardDeduction() + ",'minStandardDeduction':"+stateName.getMinAGIlimit()+"}}");*/
			
				MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'StandedDeductionconstant':" + stateName.getMinStandardDeduction() + ",'minStandardDeduction':" + stateName.getMinAGIlimit()+",'dependentExcemption':"+dependentExcemption+"}}");

				if(stateName.getFillingStatus().equals("Single")){
					
					
					
					
					JSONArray stateAgiLimit=new JSONArray();
					stateAgiLimit.put(stateName.getMinAGIlimit());
					////System.out.println("stateAgiLimit--->>"+stateAgiLimit);
					
					//System.out.println("single-----------------------"+stateName.getStandardDeductionamountisReduced());
					
					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.standerdDeductionAmt':" +stateName.getStandardDeductionAmt()+",'single.standarddeduction':"+stateName.getStandardDeduction()+",'single.personalExcemption':"+stateName.getPersonalExcemption()+",'single.standardDeductionamountisReduced':"+stateName.getStandardDeductionamountisReduced()+"}}");

					StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
					responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
					////System.out.println("limt___"+stateAgiLimit);
					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
					
					
					for(int i=0;i<rates1.length();i++){
						ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
						//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

						

					}
					
					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets + "}}");
					
					for(int i=0;i<brakets1.length();i++){
						
						ratesandBrakets1.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
						

					}
					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets1 + "}}");

					
				} else if(stateName.getFillingStatus().equals("Married Filling Jointly")){
					
					
					
					
					
					
					////System.out.println("marrid-->>>>");
					
					
					
					
					JSONArray stateAgiLimit=new JSONArray();
					stateAgiLimit.put(stateName.getMinAGIlimit());
					stateAgiLimit.put(stateName.getMinAGIlimitMax());
					////System.out.println("stateAgiLimit--->>"+stateAgiLimit);
					
					////System.out.println("single-----------------------");
					
					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.standerdDeductionAmt':" +stateName.getStandardDeductionAmt()+",'marriedFilingJoint.standarddeduction':"+stateName.getStandardDeduction()+",'marriedFilingJoint.personalExcemption':"+stateName.getPersonalExcemption()+",'dependentExcemption':"+stateName.getDependentExcemption()+",'marriedFilingJoint.standardDeductionamountisReduced':"+stateName.getStandardDeductionamountisReduced()+"}}");

					StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
					responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
					////System.out.println("limt___"+stateAgiLimit);
					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.stateAgiLimit':" + stateAgiLimit + "}}");
					
					
					for(int i=0;i<rates1.length();i++){
						ratesandBrakets.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
						//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

						

					}
					
					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets + "}}");
					
					for(int i=0;i<brakets1.length();i++){
						
						ratesandBrakets1.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
						

					}
					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.ratesAndBrackets':" + ratesandBrakets1 + "}}");

				}
				 else if(stateName.getFillingStatus().equals("Head of HouseHold")){
						
						
						
						
						
						
						////System.out.println("marrid-->>>>");
						
						
						
						
						JSONArray stateAgiLimit=new JSONArray();
						stateAgiLimit.put(stateName.getMinAGIlimit());
						stateAgiLimit.put(stateName.getMinAGIlimitMax());
						////System.out.println("stateAgiLimit--->>"+stateAgiLimit);
						
						////System.out.println("single-----------------------");
						
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.standerdDeductionAmt':" +stateName.getStandardDeductionAmt()+",'headOfHousehold.standarddeduction':"+stateName.getStandardDeduction()+",'headOfHousehold.personalExcemption':"+stateName.getPersonalExcemption()+",'dependentExcemption':"+stateName.getDependentExcemption()+",'headOfHousehold.standardDeductionamountisReduced':"+stateName.getStandardDeductionamountisReduced()+"}}");

						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
						////System.out.println("limt___"+stateAgiLimit);
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.stateAgiLimit':" + stateAgiLimit + "}}");
						
						
						for(int i=0;i<rates1.length();i++){
							ratesandBrakets.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

							

						}
						
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets + "}}");
						
						for(int i=0;i<brakets1.length();i++){
							
							ratesandBrakets1.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
							

						}
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets1 + "}}");

					}
				
				//////System.out.println("ratesandBrakets--->>"+ratesandBrakets);
			
			
			responseFromRest.put("status", "success");
			
		}
		else{
			

			//System.out.println("else1--->>>>  ");
			//System.out.println("personalExcemptionsValues--->>   "+stateName.getPersonalExcemption());
				

		//	//System.out.println("inside-------++_-->>>");
			//	//System.out.println("Actiontype--->>"+stateName.getActionType());
		//		//System.out.println("stateName--->>"+stateName.getState());
		//		//System.out.println("min stadaer-->>"+stateName.getMinStandardDeduction());
		//		//System.out.println("minStandardDeduction-->>"+stateName.getMinAGIlimit());
		//		//System.out.println("dependentExcemption-->>"+stateName.getDependentExcemption());
		//		//System.out.println("tateName.getMaritalstatus()===>>"+stateName.getMaritalstatus());
		//		//System.out.println("tateName.filling()===>>"+stateName.getFillingStatus());
		//		//System.out.println("tateName.StandardDeductionAmt===>>"+stateName.getStandardDeductionAmt());
		//		//System.out.println("StandardDeduction--->"+stateName.getStandardDeduction());
		//		//System.out.println("personalExcemption----->>"+stateName.getPersonalExcemption());
		//		//System.out.println("rates --->>>"+stateName.getRates());
		//		//System.out.println("brakets --->>>"+stateName.getBrackets());
				//JSONArray rates=new JSONArray();
			//	JSONArray brakets=new JSONArray();
				ArrayList rates = new ArrayList();
				ArrayList brakets = new ArrayList();
				Iterator crunchifyIterator =stateName.getRates().iterator();
				while (crunchifyIterator.hasNext()) {
					rates.add(crunchifyIterator.next());
				}

				Iterator crunchifyIterator1 =stateName.getBrackets().iterator();
				while (crunchifyIterator1.hasNext()) {
					brakets.add(crunchifyIterator1.next());
				}
				 Collections.sort(rates);
				   Collections.sort(brakets);

				
				
				   JSONArray rates1=new JSONArray(rates);
				   JSONArray brakets1=new JSONArray(brakets);
				
			//System.out.println("aa1--->>>"+rates1);
			//System.out.println("brakets1-->"+brakets1);
		
				
				
				double myDependentExcemption = stateName.getDependentExcemption();
				int dependentExcemption = (int) myDependentExcemption;
				BasicDBObject systemLogBasicObject = new BasicDBObject();
				
				JSONArray ratesandBrakets=new JSONArray();
				
				JSONArray ratesandBrakets1=new JSONArray();
				
				responseFromRest.put("status", "fail");	
				
				/*MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'StandedDeductionconstant':" + stateName.getMinStandardDeduction() + ",'minStandardDeduction':"+stateName.getMinAGIlimit()+"}}");*/
				//System.out.println("fillingStatus-----------="+stateName.getFillingStatus());

					if(stateName.getFillingStatus().equals("Single")){
						//System.out.println("single--->>+ ");
						
						
						JSONArray standerdDeductionLimits=new JSONArray();
						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
						JSONArray maxAGIlimit=new JSONArray();
						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

						maxAGIlimit.put(stateName.getMaxAGIlimit());
						maxAGIlimit.put(stateName.getMaxAGIlimit1());
						maxAGIlimit.put(stateName.getMaxAGIlimit2());
						maxAGIlimit.put(stateName.getMaxAGIlimit3());
						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

						JSONArray personalExcemptionlimit=new JSONArray();
						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
						personalExcemptionlimit.put(0);
						//standerdDeductionLimit
						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
						
						JSONArray standarddeductionRange=new JSONArray(); 
						standarddeductionRange.put(stateName.getMinStandardDeduction());
						standarddeductionRange.put(stateName.getMaxStandardDeduction());
						JSONArray incomeLimit=new JSONArray(); 
						incomeLimit.put(stateName.getIncomeLimits1());
						incomeLimit.put(stateName.getIncomeLimits2());
						incomeLimit.put(stateName.getIncomeLimits3());
						incomeLimit.put(stateName.getIncomeLimits4());
						////System.out.println("incomeLimit12---->>>>"+incomeLimit);
						
						JSONArray personalExcemptionsValues=new JSONArray(); 
						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues1());
						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues2());
						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues3());
					
						//standerdDeductionLimit
						////System.out.println("single-----------------------");
						////System.out.println("stateName.getPersonalExcemptionsValues1()--->>"+stateName.getPersonalExcemptionsValues1());
						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

						//	////System.out.println("limt___"+stateAgiLimit);
						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
					//	standarddeductionRange
					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.personalExcemption':" + stateName.getPersonalExcemption() + ",'single.standarddeduction':" + stateName.getStandardDeduction()+"}}");
					

						
					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
					
						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
						
						
						for(int i=0;i<rates1.length();i++){
							ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

							

						}
						
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets + "}}");
						
						for(int i=0;i<brakets1.length();i++){
							
							ratesandBrakets1.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
							

						}
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets1 + "}}");
						responseFromRest.put("status", "success");
						
					} else if(stateName.getFillingStatus().equals("Married Filling Jointly")){
						JSONArray standerdDeductionLimits=new JSONArray();
						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
						JSONArray maxAGIlimit=new JSONArray();
						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

						maxAGIlimit.put(stateName.getMaxAGIlimit());
						maxAGIlimit.put(stateName.getMaxAGIlimit1());
						maxAGIlimit.put(stateName.getMaxAGIlimit2());
						maxAGIlimit.put(stateName.getMaxAGIlimit3());
						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

						JSONArray personalExcemptionlimit=new JSONArray();
						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
						personalExcemptionlimit.put(0);
						//standerdDeductionLimit
						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
						
						JSONArray standarddeductionRange=new JSONArray(); 
						standarddeductionRange.put(stateName.getMinStandardDeduction());
						standarddeductionRange.put(stateName.getMaxStandardDeduction());
						JSONArray incomeLimit=new JSONArray(); 
						incomeLimit.put(stateName.getIncomeLimits1());
						incomeLimit.put(stateName.getIncomeLimits2());
						incomeLimit.put(stateName.getIncomeLimits3());
						incomeLimit.put(stateName.getIncomeLimits4());
						////System.out.println("incomeLimit12---->>>>"+incomeLimit);
						
						JSONArray personalExcemptionsValues=new JSONArray(); 
						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues1());
						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues2());
						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues3());
						////System.out.println("personalExcemptionsValues--->>"+personalExcemptionsValues);
						//standerdDeductionLimit
						////System.out.println("single-----------------------");
						////System.out.println("stateName.getPersonalExcemptionsValues1()--->>"+stateName.getPersonalExcemptionsValues1());
						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

						//	////System.out.println("limt___"+stateAgiLimit);
						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
					//	standarddeductionRange
					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'marriedFilingJoint.personalExcemption':" + stateName.getPersonalExcemption() + ",'marriedFilingJoint.standarddeduction':" + stateName.getStandardDeduction()+"}}");
					

						
					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
					
						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
						
						
						for(int i=0;i<rates1.length();i++){
							ratesandBrakets.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

							

						}
						
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets + "}}");
						
						for(int i=0;i<brakets1.length();i++){
							
							ratesandBrakets1.put(responseFromRest.getJSONObject("marriedFilingJoint").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
							

						}
						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.ratesAndBrackets':" + ratesandBrakets1 + "}}");

						
					}
					 else if(stateName.getFillingStatus().equals("Head of HouseHold")){
 						JSONArray standerdDeductionLimits=new JSONArray();
 						standerdDeductionLimits.put(stateName.getMinStandardDeduction());
 						standerdDeductionLimits.put(stateName.getMaxStandardDeduction1());
 						JSONArray maxAGIlimit=new JSONArray();
 						////System.out.println("stateName.--->"+stateName.getMaxAGIlimit());
 						////System.out.println("stateName1.--->"+stateName.getMaxAGIlimit1());
 						////System.out.println("stateName2.--->"+stateName.getMaxAGIlimit2());
 						////System.out.println("stateName3.--->"+stateName.getMaxAGIlimit3());

 						maxAGIlimit.put(stateName.getMaxAGIlimit());
 						maxAGIlimit.put(stateName.getMaxAGIlimit1());
 						maxAGIlimit.put(stateName.getMaxAGIlimit2());
 						maxAGIlimit.put(stateName.getMaxAGIlimit3());
 						////System.out.println("maxAGIlimit____>>>"+maxAGIlimit);

 						JSONArray personalExcemptionlimit=new JSONArray();
 						personalExcemptionlimit.put(stateName.getExcemptionlimit1());
 						personalExcemptionlimit.put(stateName.getExcemptionlimit2());
 						personalExcemptionlimit.put(0);
 						//standerdDeductionLimit
 						////System.out.println("personalExcemptionlimit--->>"+personalExcemptionlimit);
 						
 						JSONArray standarddeductionRange=new JSONArray(); 
 						standarddeductionRange.put(stateName.getMinStandardDeduction());
 						standarddeductionRange.put(stateName.getMaxStandardDeduction());
 						JSONArray incomeLimit=new JSONArray(); 
 						incomeLimit.put(stateName.getIncomeLimits1());
 						incomeLimit.put(stateName.getIncomeLimits2());
 						incomeLimit.put(stateName.getIncomeLimits3());
 						incomeLimit.put(stateName.getIncomeLimits4());
 						////System.out.println("incomeLimit12---->>>>"+incomeLimit);
 						
 						JSONArray personalExcemptionsValues=new JSONArray(); 
 						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues1());
 						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues2());
 						personalExcemptionsValues.put(stateName.getPersonalExcemptionsValues3());
 						////System.out.println("personalExcemptionsValues--->>"+personalExcemptionsValues);
 						//standerdDeductionLimit
 						////System.out.println("single-----------------------");
 						////System.out.println("stateName.getPersonalExcemptionsValues1()--->>"+stateName.getPersonalExcemptionsValues1());
 						//////System.out.println("stateName.getStanderdDeductionRate()---->>"+(stateName.getMinStandardDeduction()));
 						//////System.out.println("stateName.getStanderdDeductionRate()1---->>"+(stateName.getMaxStandardDeduction1()));

 						//	////System.out.println("limt___"+stateAgiLimit);
 						//,'single.standerdDeductionRate':"+stateName.getStanderdDeductionRate()+"
 					//	standarddeductionRange
 					MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.personalExcemption':" + stateName.getPersonalExcemption() + ",'headOfHousehold.standarddeduction':" + stateName.getStandardDeduction()+"}}");
 					

 						
 					//	MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" +stateAgiLimit+"}}");

 						StatetaxModel stateTaxDetails = MongoDBConnection.stateTaxColl.findOne("{statename:#}", stateName.getState()).as(StatetaxModel.class);
 						responseFromRest = new JSONObject(jsonMapper.writeValueAsString(stateTaxDetails));
 					
 						//MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'single.stateAgiLimit':" + stateAgiLimit + "}}");
 						
 						
 						for(int i=0;i<rates1.length();i++){
 							ratesandBrakets.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("rates", rates1.getDouble(i)));
 							//ratesandBrakets.put(responseFromRest.getJSONObject("single").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getInt(i)));

 							

 						}
 						
 						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets + "}}");
 						
 						for(int i=0;i<brakets1.length();i++){
 							
 							ratesandBrakets1.put(responseFromRest.getJSONObject("headOfHousehold").getJSONArray("ratesAndBrackets").getJSONObject(i).put("brackets", brakets1.getDouble(i)));
 							

 						}
 						MongoDBConnection.stateTaxColl.update("{statename:#}", stateName.getState()).upsert().multi().with("{$set: {'headOfHousehold.ratesAndBrackets':" + ratesandBrakets1 + "}}");

 						
 					}
					
					//////System.out.println("ratesandBrakets--->>"+ratesandBrakets);
				
				
				responseFromRest.put("status", "sucess");
			

			
		
		
		}
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return responseFromRest;
	}
	public JSONObject updateStateConstantValues(StatetaxModel servletJson)
	{
		return null;
	}
	public JSONObject updateConstantValues(ConstantsValues servletJson)
	{
		return null;
	}


	@Override
	public JSONObject onLoadFdiData(ConstantValuesModel constantsValues) {
		JSONObject responseForOnLoadFdi=new JSONObject();
		try{
			responseForOnLoadFdi.put("status","success");
			if(MongoDBConnection.constJson!=null)
			{
				ConstantsValues	ConstantDetails = MongoDBConnection.Constantvaluecoll.findOne("{_id:#}","constantValue1").as(ConstantsValues.class);
				responseForOnLoadFdi = new JSONObject(jsonMapper.writeValueAsString(ConstantDetails));
				//System.out.println("Ranjitha--onloadvalues111"+responseForOnLoadFdi);
				responseForOnLoadFdi.put("status","success");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return responseForOnLoadFdi;
	}


	@Override
	public JSONObject updateConstantValuesOfFdi(ConstantValuesModel constantsValues) {
		JSONObject resposneFromUpdateFdiConstant=new JSONObject();
		try
		{
			resposneFromUpdateFdiConstant.put("status","fail");
			JSONObject dataFromServlet = new JSONObject(jsonMapper.writeValueAsString(constantsValues));
			//System.out.println("Khatri::::::===="+dataFromServlet);
			
			 MongoDBConnection.Constantvaluecoll.update("{'_id': 'constantValue1'}")
				.upsert()
				.multi()
				.with("{$set:" + " {'kidGoalDefaultCostPeryear':" + constantsValues.getKidGoalDefaultCostPeryear() + ","
						+ "" + "'collegeGoalDefaultCostPeryearForinstatepubliccollege':" +  constantsValues.getCollegeGoalDefaultCostPeryearForinstatepubliccollege() + ","
								+ ""
						+ "'collegeGoalDefaultCostPeryearForoutofstatepubliccollege':" +constantsValues.getCollegeGoalDefaultCostPeryearForoutofstatepubliccollege() + ","
								+ "" + "'collegeGoalDefaultCostPeryearForPrivateCollege':" + constantsValues.getCollegeGoalDefaultCostPeryearForPrivateCollege()+ ","
						+  "}}");
			 ////System.out.println("values..filling status "+constantsValues.getFilingStatus()+" "+dataFromServlet.getJSONObject(constantsValues.getFilingStatus()));
			 	MongoDBConnection.Constantvaluecoll.update("{'_id': 'constantValue1'}").upsert().multi().with("{$set: {"+constantsValues.getFilingStatus()+":" + dataFromServlet.getJSONObject(constantsValues.getFilingStatus())+ ",'standardDeduction':" +constantsValues.getStandardDeduction()+",'perChildCredit':"+constantsValues.getPerChildCredit()+",'phaseOutIncomeRate':"+constantsValues.getPhaseOutIncomeRate()+",'phaseOutIncomeConstantValue1':"+constantsValues.getPhaseOutIncomeConstantValue1()+",'retirementExpenseRate':"+constantsValues.getRetirementExpenseRate()+",'maxSSTax':"+constantsValues.getMaxSSTax()+"}}");
				MongoDBConnection.constJson.put(constantsValues.getFilingStatus(), dataFromServlet.getJSONObject(constantsValues.getFilingStatus()));
				MongoDBConnection.taxRates= MongoDBConnection.constJson.getJSONObject("single").getJSONArray("taxRates");
				MongoDBConnection.taxRatesMarrid=MongoDBConnection.constJson.getJSONObject("marriedFilingSeparately").getJSONArray("taxRates");
				MongoDBConnection.marriedFilingJointly=MongoDBConnection.constJson.getJSONObject("marriedFilingJoint").getJSONArray("taxRates");
				MongoDBConnection.headOfHousehold=MongoDBConnection.constJson.getJSONObject("headOfHousehold").getJSONArray("taxRates");
				MongoDBConnection.qualifiedWidow=MongoDBConnection.constJson.getJSONObject("qualifiedWidow").getJSONArray("taxRates");
			resposneFromUpdateFdiConstant.put("status","success");
			resposneFromUpdateFdiConstant.put("kidGoalDefaultCostPeryear", constantsValues.getKidGoalDefaultCostPeryear());
			resposneFromUpdateFdiConstant.put("collegeGoalDefaultCostPeryearForinstatepubliccollege",  constantsValues.getCollegeGoalDefaultCostPeryearForinstatepubliccollege());
			resposneFromUpdateFdiConstant.put("collegeGoalDefaultCostPeryearForoutofstatepubliccollege", constantsValues.getCollegeGoalDefaultCostPeryearForoutofstatepubliccollege());
			resposneFromUpdateFdiConstant.put("collegeGoalDefaultCostPeryearForPrivateCollege",  constantsValues.getCollegeGoalDefaultCostPeryearForPrivateCollege());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resposneFromUpdateFdiConstant;
	}



}
