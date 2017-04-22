package com.mongorest.olahtek.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import com.mongorest.olahtek.model.CollegeeducationExpense;
import com.mongorest.olahtek.model.ConstantsValues;
import com.mongorest.olahtek.model.Counters;
import com.mongorest.olahtek.model.Emergencyfundmodel;
import com.mongorest.olahtek.model.FinPlan;
import com.mongorest.olahtek.model.RetirementGoal;
import com.mongorest.olahtek.model.GoalCollegeEducation;
import com.mongorest.olahtek.model.IncomeProfile;

import com.mongorest.olahtek.model.User;

@Service("goalCollegeEducationServiceImpl")
@Transactional
public class GoalCollegeEducationServiceImpl implements GoalCollegeEducationService {
	ObjectMapper jsonMapper = new ObjectMapper();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	DecimalFormat decimalFloat = new DecimalFormat("#.##");
	FinPlan finPlanDataTemp;
	@Override
    @JsonCreator

	//---------------------- For college education Goal----------------------------------------------
	public JSONObject insertGoalCollegeEducationData(GoalCollegeEducation goalCollegeEducation)
	{
		JSONObject responseToRestController=new JSONObject();

		User userDetails =  MongoDBConnection.userColl.findOne("{_id:#}",goalCollegeEducation.getUser_id()).as(User.class);
		int startYearForExpenseAndExceptionChange=0;
		int endYearForExpenseAndExceptionChange=0;
		String childType="";
		String goal_id;
		String goalname=null;
		String kidGoalName=null;
		FinPlan getfinplan;
		String user_id=goalCollegeEducation.getUser_id();
		String plan_name=goalCollegeEducation.getPlan_name();
		try{
			String actionHomeType=goalCollegeEducation.getActionHomeType();
			JSONObject collegeDataArray=new JSONObject(jsonMapper.writeValueAsString(goalCollegeEducation));
			JSONArray collegeGoalAmountData=collegeDataArray.getJSONArray("collegeGoalAmountData");
			System.out.println("collegeGoalAmountData in impl file > > > "+collegeGoalAmountData);
			if(plan_name==null)
			{
				GoalCollegeEducation getGoalData = MongoDBConnection.goalcoll.findOne("{_id:#}",goalCollegeEducation.getGoal_id()).as(GoalCollegeEducation.class);
				getfinplan = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}",user_id,getGoalData.getPlan_name()).as(FinPlan.class);
			}
			else{
				getfinplan = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}",user_id,plan_name).as(FinPlan.class);
			}
			JSONObject finPlanJson=new JSONObject(jsonMapper.writeValueAsString(getfinplan));
			JSONArray fillingExemtion=finPlanJson.getJSONArray("fillingExemtion");
			JSONArray userExpense=finPlanJson.getJSONArray("userExpense");
			System.out.println("userExpense in begining.... "+userExpense);
			JSONArray kid_expense=null;
			if(actionHomeType.equals("update"))
			{
				GoalCollegeEducation getGoalData = MongoDBConnection.goalcoll.findOne("{_id:#}",goalCollegeEducation.getGoal_id()).as(GoalCollegeEducation.class);
				String [] goalName=getGoalData.getGoalname().split("-");
				goalCollegeEducation.setKidName(goalName[0]);
				if(getGoalData.getGoalFeasibility()==true)
				{plan_name=getGoalData.getPlan_name();
				getfinplan = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}",user_id,getGoalData.getPlan_name()).as(FinPlan.class);

				System.out.println("fillingExemtion...ll "+fillingExemtion +"mmmm "+getGoalData.getChildType());
				//adding code...........
				JSONObject userJson=new JSONObject(jsonMapper.writeValueAsString(userDetails));
				String[] out=userDetails.getCreatedTs().split("/");
				int registrationYear=Integer.parseInt(out[0]);
				//	System.out.println("userJson "+userJson.getJSONArray("childs"));
				if(!userJson.isNull("childs")){
					String[] goalNameVal = goalCollegeEducation.getKidName().split("-");
					String collegeGoalkidName = goalNameVal[0];
					System.out.println("collegeGoalName in updaaa.."+collegeGoalkidName.substring(18));

					for(int i=0;i<userJson.getJSONArray("childs").length();i++)	{
						System.out.println("name comparion in update "+userJson.getJSONArray("childs").getJSONObject(i).getString("name") +"  "+goalCollegeEducation.getKidName());

						if(userJson.getJSONArray("childs").getJSONObject(i).getString("name").equals(collegeGoalkidName.substring(18)))
						{
							childType="kidAtThaTimeOfRegistration";
							int childAge=userJson.getJSONArray("childs").getJSONObject(i).getInt("age");
							int childBirthYear=registrationYear-childAge;
							int startYearForExcemption=childBirthYear+18;
							int collegeEndYear=getGoalData.getKidCollegeYear()+3;
							System.out.println("child details in update"+childAge +"   "+childBirthYear+"  "+startYearForExcemption);
							for(int j=0;j<fillingExemtion.length();j++)
							{
								System.out.println("ewrwer "+getGoalData.getStartYearForExpenseAndExceptionChange()+"fdfdf"+getGoalData.getEndYearForExpenseAndExceptionChange() +" "+getGoalData.getKidCollegeYear()+3);
								int endYearExemption=getGoalData.getStartYearForExpenseAndExceptionChange()+6;
								if(fillingExemtion.getJSONObject(j).getInt("year")>getGoalData.getStartYearForExpenseAndExceptionChange() && (getGoalData.getKidCollegeYear()>childBirthYear+18)&& fillingExemtion.getJSONObject(j).getInt("year")<=endYearExemption)
								{
									int noOfexcepmtion = fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")-1;
									System.out.println("noOfexcepmtion cretaing goal after 34 "+noOfexcepmtion +" "+fillingExemtion.getJSONObject(j).getInt("year"));
									fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
									if(fillingExemtion.getJSONObject(j).getString("fillingStatus").equals("Head of Household"))
									{
										fillingExemtion.getJSONObject(j).put("fillingStatus","Single");
									}
								}
								else if(fillingExemtion.getJSONObject(j).getInt("year")>getGoalData.getStartYearForExpenseAndExceptionChange() && (getGoalData.getKidCollegeYear()>(getGoalData.getStartYearForExpenseAndExceptionChange()+6))&&(fillingExemtion.getJSONObject(j).getInt("year")<=(getGoalData.getStartYearForExpenseAndExceptionChange()+6)))
								{
									System.out.println("after creating coll goal in 2046 "+getGoalData.getStartYearForExpenseAndExceptionChange()+"fdfdf"+getGoalData.getEndYearForExpenseAndExceptionChange() +" "+getGoalData.getKidCollegeYear()+3);
									int noOfexcepmtion = fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")-1;
									System.out.println("noOfexcepmtion "+noOfexcepmtion +" "+fillingExemtion.getJSONObject(j).getInt("year"));
									fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
									if(fillingExemtion.getJSONObject(j).getString("fillingStatus").equals("Head of Household"))
									{
										fillingExemtion.getJSONObject(j).put("fillingStatus","Single");
									}
								}
								else if(collegeEndYear<=childBirthYear+18 &&  fillingExemtion.getJSONObject(j).getInt("year")>=getGoalData.getKidCollegeYear() && fillingExemtion.getJSONObject(j).getInt("year")<=childBirthYear+18)
								{
									int noOfexcepmtion = fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")-1;
									System.out.println("noOfexcepmtion before 34 creation  "+noOfexcepmtion +" "+fillingExemtion.getJSONObject(j).getInt("year"));
									fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
									if(fillingExemtion.getJSONObject(j).getString("fillingStatus").equals("Head of Household"))
									{
										fillingExemtion.getJSONObject(j).put("fillingStatus","Single");
									}

								}

							}
							if((goalCollegeEducation.getKidCollegeYear()+3)-startYearForExcemption<=6)
							{
								for(int j=0;j<fillingExemtion.length();j++)
								{
									System.out.println(" in upddddate "+fillingExemtion.getJSONObject(j).getInt("year"));

									if(fillingExemtion.getJSONObject(j).getInt("year")>startYearForExcemption && fillingExemtion.getJSONObject(j).getInt("year")<=goalCollegeEducation.getKidCollegeYear()+3)
									{
										int noOfexcepmtion=fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")+1;
										fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
									}
									else if(collegeEndYear<=childBirthYear+18 &&  fillingExemtion.getJSONObject(j).getInt("year")>=getGoalData.getKidCollegeYear() && fillingExemtion.getJSONObject(j).getInt("year")<=childBirthYear+18)
									{
										System.out.println("else if before 19 age...");
										int noOfexcepmtion=fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")+1;
										fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
										if(fillingExemtion.getJSONObject(j).getString("fillingStatus").equals("Single"))
										{
											fillingExemtion.getJSONObject(j).put("fillingStatus","Head of Household");
										}
									}
								}
								System.out.println(">>??? update "+fillingExemtion);
								startYearForExpenseAndExceptionChange=startYearForExcemption;
								endYearForExpenseAndExceptionChange=goalCollegeEducation.getKidCollegeYear()+3;
								System.out.println(">>??? updatee "+startYearForExpenseAndExceptionChange +"  "+endYearForExpenseAndExceptionChange);
							}
							else
							{
								int endYear=startYearForExcemption+6;
								for(int j=0;j<fillingExemtion.length();j++)
								{
									if(fillingExemtion.getJSONObject(j).getInt("year")>startYearForExcemption&&fillingExemtion.getJSONObject(j).getInt("year")<=endYear)
									{
										System.out.println("updateeeee else "+fillingExemtion.getJSONObject(j).getInt("year"));
										int noOfexcepmtion=fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")+1;
										fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
									}
								}
								startYearForExpenseAndExceptionChange=startYearForExcemption;
								endYearForExpenseAndExceptionChange=endYear;
							}
							break;
						}
					}
				}
				//.....................
				if(getGoalData.getChildType().equals("Raising A Kid"))
				{
					for(int j=0;j<fillingExemtion.length();j++)
					{

						if(fillingExemtion.getJSONObject(j).getInt("year")>getGoalData.getStartYearForExpenseAndExceptionChange() && (getGoalData.getKidCollegeYear()>=(getGoalData.getStartYearForExpenseAndExceptionChange()+6))&&(fillingExemtion.getJSONObject(j).getInt("year")<=(getGoalData.getStartYearForExpenseAndExceptionChange()+6)))
						{
							System.out.println("after creating coll goal in 2046 "+getGoalData.getStartYearForExpenseAndExceptionChange()+"fdfdf"+getGoalData.getEndYearForExpenseAndExceptionChange() +" "+getGoalData.getKidCollegeYear()+3);
							int noOfexcepmtion = fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")-1;
							System.out.println("noOfexcepmtion in ffff"+noOfexcepmtion +" "+fillingExemtion.getJSONObject(j).getInt("year"));
							fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
							if(fillingExemtion.getJSONObject(j).getString("fillingStatus").equals("Head of Household"))
							{
								fillingExemtion.getJSONObject(j).put("fillingStatus","Single");
							}
						}
						else if(fillingExemtion.getJSONObject(j).getInt("year")>getGoalData.getStartYearForExpenseAndExceptionChange() && (getGoalData.getKidCollegeYear()>getGoalData.getStartYearForExpenseAndExceptionChange()) && (getGoalData.getKidCollegeYear()<(getGoalData.getStartYearForExpenseAndExceptionChange()+6) && fillingExemtion.getJSONObject(j).getInt("year")<=(getGoalData.getKidCollegeYear()+3))&&((getGoalData.getKidCollegeYear()+3)<=(getGoalData.getStartYearForExpenseAndExceptionChange()+6)))
						{
							System.out.println("ewrwer in 2037 >> "+getGoalData.getStartYearForExpenseAndExceptionChange()+"fdfdf"+getGoalData.getEndYearForExpenseAndExceptionChange() +" "+getGoalData.getKidCollegeYear()+3);
							int noOfexcepmtion = fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")-1;
							System.out.println("noOfexcepmtion in elsssssssssseiff "+noOfexcepmtion +" "+fillingExemtion.getJSONObject(j).getInt("year"));
							fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
							if(fillingExemtion.getJSONObject(j).getString("fillingStatus").equals("Head of Household"))
							{
								fillingExemtion.getJSONObject(j).put("fillingStatus","Single");
							}
						}
						else if(fillingExemtion.getJSONObject(j).getInt("year")>getGoalData.getStartYearForExpenseAndExceptionChange() && (getGoalData.getKidCollegeYear()>getGoalData.getStartYearForExpenseAndExceptionChange()) && (getGoalData.getKidCollegeYear()<(getGoalData.getStartYearForExpenseAndExceptionChange()+6) && fillingExemtion.getJSONObject(j).getInt("year")<=getGoalData.getStartYearForExpenseAndExceptionChange()+6)&&((getGoalData.getKidCollegeYear()+3)>(getGoalData.getStartYearForExpenseAndExceptionChange()+6)))
						{
							System.out.println("ewrwer in 2040 >> "+getGoalData.getStartYearForExpenseAndExceptionChange()+"fdfdf"+getGoalData.getEndYearForExpenseAndExceptionChange() +" "+getGoalData.getKidCollegeYear()+3);
							int noOfexcepmtion = fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")-1;
							System.out.println("noOfexcepmtion in elsssssssssseiff "+noOfexcepmtion +" "+fillingExemtion.getJSONObject(j).getInt("year"));
							fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
							if(fillingExemtion.getJSONObject(j).getString("fillingStatus").equals("Head of Household"))
							{
								fillingExemtion.getJSONObject(j).put("fillingStatus","Single");
							}
						}
						System.out.println("fillingExemtion 33   "+fillingExemtion);
					}
					kid_expense=finPlanJson.getJSONObject("expense").getJSONArray("kid_expense");
					int kidIndex=0;
					String[] goalNameVal = goalCollegeEducation.getKidName().split("-");
					String collegeGoalName = goalNameVal[0];
					for(int i=0;i<kid_expense.length();i++)
					{
						//System.out.println("name // "+kid_expense.getJSONObject(i).getString("goalname")+"compare"+collegeGoalName.substring(18));
						if(kid_expense.getJSONObject(i).getString("goalname").equals(collegeGoalName.substring(18)))
						{
							//System.out.println("iside equals method");
							int newEndYear=kid_expense.getJSONObject(i).getInt("startYear")+18;
							kid_expense.getJSONObject(i).put("endYear", newEndYear);
							kidIndex=i;
							break;
						}
					}
					//System.out.println("kid_expense rr "+kid_expense);
					int collegeGoalYearCounter=0;
					//System.out.println("beforreeeee deleeeete "+userExpense +"dhjgf"+getGoalData.getKidCollegeYear());
					for (int i = 0; i < userExpense.length(); i++) {
						if(getGoalData.getKidCollegeYear()+collegeGoalYearCounter<=kid_expense.getJSONObject(kidIndex).getInt("startYear")+18 && userExpense.getJSONObject(i).getInt("year") <=kid_expense.getJSONObject(kidIndex).getInt("startYear")+18 && fillingExemtion.getJSONObject(i).getInt("year") >=getGoalData.getKidCollegeYear())
						{
							//System.out.println("yeraaa valueee. "+userExpense.getJSONObject(i).getInt("year"));
							double newKidExpense=userExpense.getJSONObject(i).getDouble("kidExpense")+kid_expense.getJSONObject(kidIndex).getDouble("annualExpense");
							double newTotalExpense=userExpense.getJSONObject(i).getDouble("totalExpense")+kid_expense.getJSONObject(kidIndex).getDouble("annualExpense");
							userExpense.getJSONObject(i).put("totalExpense", newTotalExpense).put("kidExpense", newKidExpense);
							collegeGoalYearCounter++;
							//	System.out.println("kidIndex "+kidIndex);
						}
					}
					//System.out.println("gggggggggggg "+userExpense);

					if(!finPlanJson.isNull("expense"))
					{

						if(!finPlanJson.getJSONObject("expense").isNull("kid_expense"))
						{
							int collegeGoalYearCounter1=0;
							for(int i=0;i<kid_expense.length();i++)
							{

								if((goalCollegeEducation.getKidCollegeYear()+3)-kid_expense.getJSONObject(i).getInt("endYear")<=6)
								{
									for(int j=0;j<fillingExemtion.length();j++)
									{
										//System.out.println("year.. "+fillingExemtion.getJSONObject(j).getInt("year") );
										//System.out.println(" "+goalCollegeEducation.getKidCollegeYear()+"  "+collegeGoalYearCounter+"  "+kid_expense.getJSONObject(i).getInt("endYear")+"  "+goalCollegeEducation.getKidCollegeYear());
										if(goalCollegeEducation.getKidCollegeYear()+collegeGoalYearCounter1<=kid_expense.getJSONObject(i).getInt("endYear") && fillingExemtion.getJSONObject(j).getInt("year") <=kid_expense.getJSONObject(i).getInt("endYear") && fillingExemtion.getJSONObject(j).getInt("year") >=goalCollegeEducation.getKidCollegeYear())
										{
											//System.out.println("rrrrrbeffff "+userExpense.getJSONObject(j).getDouble("kidExpense")+"subb "+kid_expense.getJSONObject(i).getDouble("annualExpense"));
											double newKidExpense=userExpense.getJSONObject(j).getDouble("kidExpense")-kid_expense.getJSONObject(i).getDouble("annualExpense");
											double newTotalExpense=userExpense.getJSONObject(j).getDouble("totalExpense")-kid_expense.getJSONObject(i).getDouble("annualExpense");
											userExpense.getJSONObject(j).put("totalExpense", newTotalExpense).put("kidExpense", newKidExpense);
											collegeGoalYearCounter1++;
											//System.out.println("rrrnewKidExpense "+newKidExpense +" newTotalExpense "+newTotalExpense);
											//System.out.println("rrrruserExpense before passing to method "+userExpense);
										}
										//System.out.println("rrrr userExpense in updddddte "+userExpense);
										//System.out.println("yyyy "+kid_expense +" fdf "+goalCollegeEducation.getKidCollegeYear()+"mmm"+(goalCollegeEducation.getKidCollegeYear()+3)+"xxxxx "+kid_expense.getJSONObject(i).getInt("endYear"));
										if(fillingExemtion.getJSONObject(j).getInt("year")>kid_expense.getJSONObject(i).getInt("endYear")&&fillingExemtion.getJSONObject(j).getInt("year")<=goalCollegeEducation.getKidCollegeYear()+3)
										{
											//	System.out.println("in yeeeeeeeear"+fillingExemtion.getJSONObject(j).getInt("year"));
											//	System.out.println("hjfjdfhjfh "+fillingExemtion.getJSONObject(j).getInt("noOfExcemtion"));
											int noOfexcepmtion=fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")+1;
											fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
											if(fillingExemtion.getJSONObject(j).getString("fillingStatus").equals("Single"))
											{
												fillingExemtion.getJSONObject(j).put("fillingStatus","Head of Household");
											}
										}
									}
									startYearForExpenseAndExceptionChange=kid_expense.getJSONObject(i).getInt("endYear");
									kid_expense.getJSONObject(i).put("endYear", goalCollegeEducation.getKidCollegeYear()+3);
									endYearForExpenseAndExceptionChange=goalCollegeEducation.getKidCollegeYear()+3;
								}
								else
								{
									int endYear=kid_expense.getJSONObject(i).getInt("endYear")+6;
									startYearForExpenseAndExceptionChange=kid_expense.getJSONObject(i).getInt("endYear");
									endYearForExpenseAndExceptionChange=endYear;
									//System.out.println("endYear tttt "+endYear +" djjd "+startYearForExpenseAndExceptionChange);
									kid_expense.getJSONObject(i).put("endYear",endYear);
									//System.out.println("in else end year "+endYear);
									for(int j=0;j<fillingExemtion.length();j++)
									{
										if(fillingExemtion.getJSONObject(j).getInt("year")>kid_expense.getJSONObject(i).getInt("endYear")-6 && fillingExemtion.getJSONObject(j).getInt("year")<=endYear)
										{
											//System.out.println("ooooooooo "+fillingExemtion.getJSONObject(j).getInt("year"));
											int noOfexcepmtion=fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")+1;
											//System.out.println("ooooooooo "+noOfexcepmtion);
											fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
											if(fillingExemtion.getJSONObject(j).getString("fillingStatus").equals("Single"))
											{
												fillingExemtion.getJSONObject(j).put("fillingStatus","Head of Household");
											}
										}
									}
								}

							}
						}}
					System.out.println("expenneseeeeeee "+userExpense);
				}
				}
			}
			responseToRestController.put("status","fail");
			if(goalCollegeEducation.getKidName()!=null)
			{
				getfinplan =  MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}",user_id,plan_name).as(FinPlan.class);

				finPlanJson=new JSONObject(jsonMapper.writeValueAsString(getfinplan));
				JSONObject userJson=new JSONObject(jsonMapper.writeValueAsString(userDetails));
				String[] out=userDetails.getCreatedTs().split("/");
				int registrationYear=Integer.parseInt(out[0]);
				if(!userJson.isNull("childs")){
					for(int i=0;i<userJson.getJSONArray("childs").length();i++)	{
						System.out.println("name comparion in insert "+userJson.getJSONArray("childs").getJSONObject(i).getString("name") +"  "+goalCollegeEducation.getKidName());
						if(userJson.getJSONArray("childs").getJSONObject(i).getString("name").equals(goalCollegeEducation.getKidName()))
						{
							childType="kidAtThaTimeOfRegistration";
							int childAge=userJson.getJSONArray("childs").getJSONObject(i).getInt("age");
							int childBirthYear=registrationYear-childAge;
							int startYearForExcemption=childBirthYear+18;
							System.out.println("child details in insertion "+childAge +"   "+childBirthYear+"  "+startYearForExcemption);
							if((goalCollegeEducation.getKidCollegeYear()+3)-startYearForExcemption<=6)
							{
								for(int j=0;j<fillingExemtion.length();j++)
								{
									if(fillingExemtion.getJSONObject(j).getInt("year")>startYearForExcemption && fillingExemtion.getJSONObject(j).getInt("year")<=goalCollegeEducation.getKidCollegeYear()+3)
									{
										System.out.println(" in inserrrrtion "+fillingExemtion.getJSONObject(j).getInt("year"));
										int noOfexcepmtion=fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")+1;
										fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
									}
								}
								startYearForExpenseAndExceptionChange=startYearForExcemption;
								endYearForExpenseAndExceptionChange=goalCollegeEducation.getKidCollegeYear()+3;
								System.out.println(">>??? "+fillingExemtion);
								System.out.println(">>??? "+startYearForExpenseAndExceptionChange +"  "+endYearForExpenseAndExceptionChange);
							}
							else
							{
								int endYear=startYearForExcemption+6;
								for(int j=0;j<fillingExemtion.length();j++)
								{
									if(fillingExemtion.getJSONObject(j).getInt("year")>startYearForExcemption&&fillingExemtion.getJSONObject(j).getInt("year")<=endYear)
									{
										System.out.println("inseerttionn else "+fillingExemtion.getJSONObject(j).getInt("year"));
										int noOfexcepmtion=fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")+1;
										System.out.println("noOfexcepmtion "+noOfexcepmtion +" "+startYearForExcemption +" endYear ++ "+endYear);
										fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
									}
								}
								startYearForExpenseAndExceptionChange=startYearForExcemption;
								endYearForExpenseAndExceptionChange=endYear;
							}
							break;
						}
					}
				}
				if(!finPlanJson.isNull("expense"))
				{

					if(!finPlanJson.getJSONObject("expense").isNull("kid_expense"))
					{
						if(kid_expense==null)
						{
							kid_expense=finPlanJson.getJSONObject("expense").getJSONArray("kid_expense");
						}
						for(int i=0;i<kid_expense.length();i++)
						{
							if(kid_expense.getJSONObject(i).getString("goalname").equals(goalCollegeEducation.getKidName()))
							{
								childType="Raising A Kid";
								if((goalCollegeEducation.getKidCollegeYear()+3)-kid_expense.getJSONObject(i).getInt("endYear")<=6)
								{
									int collegeGoalYearCounter=0;
									for(int j=0;j<fillingExemtion.length();j++)
									{
										System.out.println("year.. in with kid goal "+fillingExemtion.getJSONObject(j).getInt("year") );
										System.out.println(" "+goalCollegeEducation.getKidCollegeYear()+"  "+collegeGoalYearCounter+"  "+kid_expense.getJSONObject(i).getInt("endYear")+"  "+goalCollegeEducation.getKidCollegeYear());
										if(goalCollegeEducation.getKidCollegeYear()+collegeGoalYearCounter<=kid_expense.getJSONObject(i).getInt("endYear") && fillingExemtion.getJSONObject(j).getInt("year") <=kid_expense.getJSONObject(i).getInt("endYear") && fillingExemtion.getJSONObject(j).getInt("year") >=goalCollegeEducation.getKidCollegeYear())
										{
											System.out.println("beffff "+userExpense.getJSONObject(j).getDouble("kidExpense")+"subb "+kid_expense.getJSONObject(i).getDouble("annualExpense"));
											double newKidExpense=userExpense.getJSONObject(j).getDouble("kidExpense")-kid_expense.getJSONObject(i).getDouble("annualExpense");
											double newTotalExpense=userExpense.getJSONObject(j).getDouble("totalExpense")-kid_expense.getJSONObject(i).getDouble("annualExpense");
											userExpense.getJSONObject(j).put("totalExpense", newTotalExpense).put("kidExpense", newKidExpense);
											collegeGoalYearCounter++;
											System.out.println("newKidExpense "+newKidExpense +" newTotalExpense "+newTotalExpense);
											System.out.println("userExpense before passing to method "+userExpense);
										}
										if(fillingExemtion.getJSONObject(j).getInt("year")>kid_expense.getJSONObject(i).getInt("endYear")&&fillingExemtion.getJSONObject(j).getInt("year")<=goalCollegeEducation.getKidCollegeYear()+3)
										{
											System.out.println("insertttttt yesrrrrr "+fillingExemtion.getJSONObject(j).getInt("year"));
											int noOfexcepmtion=fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")+1;
											System.out.println("noOfexcepmtion  adddingggg "+noOfexcepmtion);
											fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
											if(fillingExemtion.getJSONObject(j).getString("fillingStatus").equals("Single"))
											{
												fillingExemtion.getJSONObject(j).put("fillingStatus","Head of Household");
											}
										}
									}
									startYearForExpenseAndExceptionChange=kid_expense.getJSONObject(i).getInt("endYear");
									kid_expense.getJSONObject(i).put("endYear", goalCollegeEducation.getKidCollegeYear()+3);
									endYearForExpenseAndExceptionChange=goalCollegeEducation.getKidCollegeYear()+3;
								}
								else
								{
									int endYear=kid_expense.getJSONObject(i).getInt("endYear")+6;
									startYearForExpenseAndExceptionChange=kid_expense.getJSONObject(i).getInt("endYear");
									endYearForExpenseAndExceptionChange=endYear;
									kid_expense.getJSONObject(i).put("endYear",endYear);
									System.out.println("in else end year "+endYear);
									for(int j=0;j<fillingExemtion.length();j++)
									{
										if(fillingExemtion.getJSONObject(j).getInt("year")>kid_expense.getJSONObject(i).getInt("endYear")-6 && fillingExemtion.getJSONObject(j).getInt("year")<=endYear)
										{
											System.out.println("<<<< year.." +fillingExemtion.getJSONObject(j).getInt("year"));
											int noOfexcepmtion=fillingExemtion.getJSONObject(j).getInt("noOfExcemtion")+1;
											System.out.println("noOfexcepmtion oo hoo "+noOfexcepmtion);
											fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
											if(fillingExemtion.getJSONObject(j).getString("fillingStatus").equals("Single"))
											{
												fillingExemtion.getJSONObject(j).put("fillingStatus","Head of Household");
											}
										}
									}
								}
							}
						}
					}
				}

				kidGoalName=goalCollegeEducation.getKidName();
				goalname=goalCollegeEducation.getGoalType()+" "+goalCollegeEducation.getKidName();
			}
			else{
				if(getfinplan.getCollegeGoalCount()==0)
				{
					goalname=goalCollegeEducation.getGoalType()+1;
				}
				else{
					goalname=goalCollegeEducation.getGoalType()+getfinplan.getCollegeGoalCount();

				}
			}

			int kidCollegeYear=goalCollegeEducation.getKidCollegeYear();
			String collegeType=goalCollegeEducation.getCollegeType();
			long collegeEducationAmount=goalCollegeEducation.getCollegeEducationAmount();
			String goalType= goalCollegeEducation.getGoalType();
			if(actionHomeType.equals("insert"))
			{

				String fin_id= getfinplan.get_id();
				responseToRestController=InsertionIntoGoalCollegeEducation(kidGoalName,goalname,fin_id,kidCollegeYear,goalType,user_id,collegeType,collegeEducationAmount,goalCollegeEducation,startYearForExpenseAndExceptionChange,endYearForExpenseAndExceptionChange,childType,userExpense,fillingExemtion,kid_expense,collegeGoalAmountData);

			}
			else 	{
				System.out.println("userExpense beforee passing to method.. "+userExpense);
				goal_id=goalCollegeEducation.getGoal_id();
				String fin_id=getfinplan.get_id();
				responseToRestController=updateGoalCollegeEducation(fin_id,goal_id,actionHomeType,kidCollegeYear,collegeType,collegeEducationAmount,goalCollegeEducation,userExpense,fillingExemtion,kid_expense,collegeGoalAmountData);
			}
			return responseToRestController;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return responseToRestController;
		}
	}
	//---------------------- Insertion In  Goal College Education----------------------------------------------
	private JSONObject InsertionIntoGoalCollegeEducation(String kidGoalName,String goalname,String fin_id, int kidCollegeYear, String goalType, String user_id,
			String collegeType,long collegeEducationAmount, GoalCollegeEducation goalCollegeEducation,int startYearForExpenseAndExceptionChange,int endYearForExpenseAndExceptionChange,String childType,JSONArray userExpense,JSONArray fillingExemtion,JSONArray kid_expense,JSONArray collegeGoalAmountData) throws JSONException {

		JSONObject responseToRestController=new JSONObject();
		User user =  MongoDBConnection.userColl.findOne("{'_id':#}",user_id).as(User.class);
		JSONObject systemLog=new JSONObject();
		try
		{
			double totalCollegeCost=0;
			for(int y=0;y<collegeGoalAmountData.length();y++)
			{
				totalCollegeCost=totalCollegeCost+collegeGoalAmountData.getJSONObject(y).getDouble("cost");
			}
			//	System.out.println("Ranjitha :: new array "+collegeGoalAmountData +"totalCollegeCost "+totalCollegeCost);
			long collegeExpense=goalCollegeEducation.getCollegeEducationAmountPercentage()*goalCollegeEducation.getCollegeEducationAmount()/100;
			double totalAnualCollegeExpense= calCollegeExpense(collegeExpense,kidCollegeYear);
			//System.out.println("collegeGoalAmountData in impl file : : : " +goalCollegeEducation.getCollegeGoalAmountData());
			//System.out.println("college Data..."+goalCollegeEducation.getPlan529Saved()+"......"+goalCollegeEducation.getCalPlan529());
			FinPlan finDetails = MongoDBConnection.finplancol.findOne("{_id:#}", fin_id).as(FinPlan.class);
			User Details = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
			int userAge=Details.getAge();
			int userAgeTemp=Details.getAge();
			int spouseAgeTemp=Details.getSpouseAge();
			String maritalStatus=finDetails.getMarital_status();
			JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
			JSONArray assets=finPlanJson.getJSONArray("assests");
			JSONArray tax=finPlanJson.getJSONArray("tax");
			JSONArray limits=finPlanJson.getJSONArray("limits");
			JSONArray deductions=finPlanJson.getJSONArray("deductions");
			JSONArray kidBirthYear=finPlanJson.getJSONArray("kidBirthYear");
			JSONArray expenseObj=new JSONArray();
			JSONArray filingExcemptionJSON=finPlanJson.getJSONArray("fillingExemtion");
			JSONObject expense=new JSONObject();
			expense=finPlanJson.getJSONObject("expense");
			if (!expense.isNull("housing_expense")) {
				expenseObj=finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
			}			String finPlanProfileName=finDetails.getProfile_name();
			double tempCost=(totalCollegeCost*20/100);
			if(goalCollegeEducation.getPlan529Saved()==0)
			{
				int m=0;
				for(int i=0;i<limits.length();i++)
				{
					if(limits.getJSONObject(i).getInt("year")<kidCollegeYear)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")+(totalCollegeCost*20/100));
					}
					if(limits.getJSONObject(i).getInt("year")>=kidCollegeYear && limits.getJSONObject(i).getInt("year")<=kidCollegeYear+3)
					{
						System.out.println("mth valll : "+collegeGoalAmountData.getJSONObject(m).getDouble("cost")+" "+m);
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")+tempCost);
						tempCost=tempCost-((collegeGoalAmountData.getJSONObject(m).getDouble("cost"))*20/100);
					}
					if(limits.getJSONObject(i).getInt("year")>=kidCollegeYear&&limits.getJSONObject(i).getInt("year")<=kidCollegeYear+3)
					{
						System.out.println(" m index val in if"+m +" "+(collegeGoalAmountData.getJSONObject(m).getDouble("cost"))*20/100+" "+deductions.getJSONObject(i).getDouble("collegeGoalTaxable"));
						deductions.getJSONObject(i).put("collegeGoalTaxable",deductions.getJSONObject(i).getDouble("collegeGoalTaxable")+((collegeGoalAmountData.getJSONObject(m).getDouble("cost"))*20/100));
						System.out.println("deductions after array ?? "+deductions);
						JSONObject collegeAmount=new JSONObject();
						collegeAmount.put("currentAmount", collegeGoalAmountData.getJSONObject(m).getDouble("cost")).put("type","create").put("oldAmount", 0);
						System.out.println("collegeAmount >>"+collegeAmount);
						JSONObject newLimits=limiteAfterCollegeGoal(userAgeTemp, 0, 0, filingExcemptionJSON.getJSONObject(i).getString("fillingStatus"),spouseAgeTemp, limits, 0, i, collegeAmount,deductions.getJSONObject(i));
						limits=newLimits.getJSONArray("limits");
						System.out.println("newlimits deduction.. "+newLimits.getJSONObject("deductions"));
						deductions.put(i,newLimits.getJSONObject("deductions"));
						m++;
					}
					userAgeTemp++;
					spouseAgeTemp++;
				}
				System.out.println("limits in college goal "+limits);
				System.out.println("deductions in college goal "+deductions);

			}
			else{
				tempCost=totalCollegeCost-goalCollegeEducation.getPlan529Saved();
				System.out.println("in elseeee tempCost "+tempCost +" totalCollegeCosthhh "+totalCollegeCost+"goalCollegeEducation.getPlan529Saved() "+goalCollegeEducation.getPlan529Saved());
				int m=0;
				for(int i=0;i<limits.length();i++)
				{

					if(limits.getJSONObject(i).getInt("year")<kidCollegeYear)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")+(totalCollegeCost-goalCollegeEducation.getPlan529Saved()));
					}
					if(limits.getJSONObject(i).getInt("year")>=kidCollegeYear && limits.getJSONObject(i).getInt("year")<=kidCollegeYear+3)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")+tempCost);
						System.out.println("mth valll : in else condition "+collegeGoalAmountData.getJSONObject(m).getDouble("cost")+" "+m);
						tempCost=tempCost-((collegeGoalAmountData.getJSONObject(m).getDouble("cost"))-(goalCollegeEducation.getPlan529Saved()/4));
						//System.out.println("mth valll : after increment in else condion.."+collegeGoalAmountData.getJSONObject(m).getDouble("collegeCost")+" "+m);
					}
					if(limits.getJSONObject(i).getInt("year")>=kidCollegeYear&&limits.getJSONObject(i).getInt("year")<=kidCollegeYear+3)
					{
						//	System.out.println(" m index val in else "+(collegeGoalAmountData.getJSONObject(m).getDouble("cost")));
						System.out.println("529 plan >> "+goalCollegeEducation.getPlan529Saved());
						deductions.getJSONObject(i).put("collegeGoalTaxable",deductions.getJSONObject(i).getDouble("collegeGoalTaxable")+(collegeGoalAmountData.getJSONObject(m).getDouble("cost"))-(goalCollegeEducation.getPlan529Saved()/4));
						deductions.getJSONObject(i).put("collegeGoalNontaxable",deductions.getJSONObject(i).getDouble("collegeGoalNontaxable")+goalCollegeEducation.getPlan529Saved()/4);
						m++;
					}
				}

			}

			IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}",user_id ,finPlanProfileName).as(IncomeProfile.class);
			JSONObject incomeProfileJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
			JSONArray userIncome=incomeProfileJson.getJSONArray("user_income");
			JSONArray SpouseIncomeArray=new JSONArray();
			JSONArray combinedIncome =new JSONArray();
			int spouseAge;
			if(maritalStatus.equals("Yes"))
			{
				SpouseIncomeArray=incomeProfileJson.getJSONArray("spouse_income");
				IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
				combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, SpouseIncomeArray);
				spouseAge=finDetails.getSpouseAge();
			}
			else
			{
				combinedIncome=userIncome;
				spouseAge=0;
			}
			int emergencyFundAmt=finDetails.getEmergencyFundAmt();
			boolean emergencyFundFlag=finDetails.isEmergencyFundFlag();
			RetirementGoal retirementObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",finDetails.get_id(),"Retirement").as(RetirementGoal.class);

			int SpouseStartYear=0;
			int userStartYear=0;

			if(retirementObj!=null)
			{
				userStartYear=Details.getBirthYear()+retirementObj.getRetirementAge();
				if(finDetails.getMarital_status().equals("Yes")&& Details.getMarital_status().equals("No")){
					SpouseStartYear=finDetails.getSpouseBirthYear()+retirementObj.getSpouseRetirementAge();
				}
				else if(Details.getMarital_status().equals("Yes"))
				{
					SpouseStartYear=Details.getSpouseBirthYear()+retirementObj.getSpouseRetirementAge();
				}

			}
			else{
				userStartYear=Details.getBirthYear()+70;
				if(finDetails.getMarital_status().equals("Yes")&& Details.getMarital_status().equals("No")){
					SpouseStartYear=finDetails.getSpouseBirthYear()+70;
				}
				else if(Details.getMarital_status().equals("Yes"))
				{
					SpouseStartYear=Details.getSpouseBirthYear()+70;
				}
			}
			JSONObject retirementData=new JSONObject();
			retirementData.put("spouseStartYear", SpouseStartYear);
			retirementData.put("userStartYear", userStartYear);

			String emergencyType=null;
			String monthsOfIncome=null;
			String monthsOfExpense=null;
			if(emergencyFundFlag==true)
			{
				Emergencyfundmodel emergencyObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",finDetails.get_id(),"Emergency Fund").as(Emergencyfundmodel.class);
				emergencyType=emergencyObj.getTimePeriod();
				monthsOfIncome=emergencyObj.getMonthI();
				monthsOfExpense=emergencyObj.getMonthE();
			}
			System.out.println(" insertion  limits "+limits+" deduction "+deductions +" assests "+assets );
			System.out.println(" userExpense .. in insert "+userExpense+" "+" "+kid_expense);
			JSONObject result=CalculationEngine.sweepingOfMoney(userIncome, fin_id,combinedIncome, SpouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, user_id, fillingExemtion, userAge,spouseAge, emergencyFundAmt, emergencyFundFlag,deductions,kidBirthYear, retirementData, retirementObj, expenseObj,emergencyType,monthsOfIncome,monthsOfExpense,finDetails.isRetirementFlag());

			String status=result.getString("status");
			if(status.equals("success"))
			{
				assets=result.getJSONArray("assets");
				tax=result.getJSONArray("tax");
				Counters count=MongoDBConnection.counterColl.findOne().as(Counters.class);
				String goal_id="goal"+count.getGoal_id();
				FinPlan getfinplan = MongoDBConnection.finplancol.findOne("{_id:#}",fin_id).as(FinPlan.class);
				finPlanJson=new JSONObject(jsonMapper.writeValueAsString(getfinplan));


				BasicDBObject doc = new BasicDBObject("_id", goal_id).append("user_id", goalCollegeEducation.getUser_id())
						.append("fin_id",fin_id).append("goalType", goalCollegeEducation.getGoalType())
						.append("kidCollegeYear",goalCollegeEducation.getKidCollegeYear())
						.append("collegeType",goalCollegeEducation.getCollegeType())
						.append("collegeEducationAmount",goalCollegeEducation.getCollegeEducationAmount())
						.append("completedStatus", 1)
						.append("goalname", goalname)
						.append("plan_name",goalCollegeEducation.getPlan_name())
						.append("childType",childType)
						.append("startYearForExpenseAndExceptionChange", startYearForExpenseAndExceptionChange)
						.append("endYearForExpenseAndExceptionChange",endYearForExpenseAndExceptionChange)
						.append("collegeEducationAmountPercentage",goalCollegeEducation.getCollegeEducationAmountPercentage())
						.append("percentageCollegeCost",goalCollegeEducation.getPercentageCollegeCost())
						.append("totalCollegeCost", totalCollegeCost)
						//.append("collegeGoalAmountData",null)
						.append("created_ts",dateFormat.format(date));

				doc.append("goalFeasibility",true);
				responseToRestController.append("goalFeasibility",true);


				MongoDBConnection.finplancol.update("{'_id':'"+fin_id+"'}").upsert().multi().with("{$addToSet: {'goals':'"+goal_id+"'}}");
				updateFinplanForGoalCollegeEducation(goalname,fin_id,goal_id,collegeEducationAmount,kidCollegeYear,collegeExpense,"insert",collegeGoalAmountData);
				MongoDBConnection.finplancol.update("{'_id':'"+fin_id+"'}").with("{$inc: {collegeGoalCount: 1}}");
				MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi().with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits + ",'deductions':" + deductions + ",'userExpense':" + userExpense + ",'fillingExemtion':" + fillingExemtion + "}}");
				if(!(goalCollegeEducation.getPlan529Saved()==0))
				{
					doc.append("plan529Saved",goalCollegeEducation.getPlan529Saved());
				}
				MongoDBConnection.goalcoll.insert(doc);
				MongoDBConnection.goalcoll.update("{'_id':#}", goal_id).upsert().multi().with("{$set: {'collegeGoalAmountData':"+collegeGoalAmountData+ "}}");
				MongoDBConnection.counterColl.update("{'goal_id':"+count.getGoal_id()+"}").with("{$inc: {goal_id: 1}}");
				MongoDBConnection.finplancol.update("{'_id':'"+getfinplan.get_id()+"'}").upsert().with("{$set: {'expense.kid_expense':"+kid_expense+"}}}");
				MongoDBConnection.goalcoll.update("{'user_id':#,'goalname':#}", user_id ,kidGoalName).upsert().multi().with("{$set: {'dependentCollageGoal':'"+ goal_id + "'}}");
				responseToRestController.put("status","success");
				responseToRestController.put("goal_id",goal_id);
				responseToRestController.put("goalType", goalCollegeEducation.getGoalType());
				responseToRestController.put("kidCollegeYear",goalCollegeEducation.getKidCollegeYear());
				responseToRestController.put("collegeType",goalCollegeEducation.getCollegeType());
				responseToRestController.put("collegeEducationAmount",goalCollegeEducation.getCollegeEducationAmount());
				responseToRestController.put("collegeExpense", totalAnualCollegeExpense);
				responseToRestController.put("goal_name", goalCollegeEducation.getGoalname());
				systemLog.put("type", "success");
				systemLog.put("message", "Goal Created Successfully!!");
				systemLog.put("userName", user.getName());
				systemLog.put("user_id", user_id);
				systemLog.put("createdTs", dateFormat.format(date));
			}
			else
			{
				responseToRestController.put("status","fail");
			}
		}
		catch(Exception e)
		{
			systemLog.put("type", "error");
			systemLog.put("message", "Goal Creation Failed!!");
			systemLog.put("userName", user.getName());
			systemLog.put("user_id", user_id);
			systemLog.put("createdTs", dateFormat.format(date));
			e.printStackTrace();
		}
		finally
		{
			/*BasicDBObject systemLogBasicObject = new BasicDBObject("user_id", user_id)
					.append("userName", systemLog.getString("userName"))
					.append("type",systemLog.getString("type"))
					.append("message", systemLog.getString("message"))
					.append("createdTs", dateFormat.format(date));
			MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);*/
			////System.out.println("systemLog collection=========="+systemLog.toString());

		}
		return responseToRestController;
	}


	/*------------------------Calculation For Anual Morgage and Total Mogagae-----------------------*/
	public double calCollegeExpense(long collegeEducationAmount,int kidCollegeYear)
	{

		double collegeExpense=collegeEducationAmount;
		return collegeExpense;
	}
	/*-------------------------Update House Goal Collection Data-------------------------------------------------------*/
	public JSONObject updateGoalCollegeEducation(String fin_id,String goal_id,String actionHomeType, int kidCollegeYear,String collegeType,long collegeEducationAmount,GoalCollegeEducation goalCollegeEducation,JSONArray userExpense,JSONArray fillingExemtion, JSONArray kid_expense,JSONArray collegeGoalAmountData) throws JSONException {

		//System.out.println("collegeGoalAmountData in update>> "+collegeGoalAmountData);
		JSONArray collegeGoalAmountDataForFinplan=collegeGoalAmountData;
		String user_id=goalCollegeEducation.getUser_id();
		GoalCollegeEducation goalDetails=new GoalCollegeEducation();
		User user=new User();
		String goalName=null;
		String plan_name=null;
		JSONObject systemLog=new JSONObject();
		String type="success";
		JSONObject responseToRestController=new JSONObject();
		try
		{
			//System.out.println("goalCollegeEducation......."+goalCollegeEducation.getPlan529Saved());
			user = MongoDBConnection.userColl.findOne("{'_id':#}",user_id).as(User.class);
			goalDetails=MongoDBConnection.goalcoll.findOne("{_id:#}", goal_id).as(GoalCollegeEducation.class);
			goalName=goalDetails.getGoalname();
			double oldCollegeExpense=goalDetails.getCollegeEducationAmount()*goalDetails.getCollegeEducationAmountPercentage()/100;
			int oldStartYear=goalDetails.getKidCollegeYear();
			double totalCollegeCost=0;
			for(int y=0;y<collegeGoalAmountData.length();y++)
			{
				totalCollegeCost=totalCollegeCost+collegeGoalAmountData.getJSONObject(y).getDouble("cost");
			}
			JSONObject collegeDataFromdb = new JSONObject(jsonMapper.writeValueAsString(goalDetails));
			JSONArray collegeGoalAmountDataFromdb=collegeDataFromdb.getJSONArray("collegeGoalAmountData");
			double totalCollegeCostDb=0;
			for(int j=0;j<collegeGoalAmountDataFromdb.length();j++)
			{
				totalCollegeCostDb=totalCollegeCostDb+collegeGoalAmountDataFromdb.getJSONObject(j).getDouble("cost");
			}
			//	System.out.println("Ranjitha :: new array from servlet "+collegeGoalAmountData +"totalCollegeCost "+totalCollegeCost);
			//	System.out.println("Ranjitha :: new array from servlet "+collegeGoalAmountDataFromdb +"totalCollegeCost "+totalCollegeCostDb);
			plan_name=goalCollegeEducation.getPlan_name();;
			long collegeExpense=goalCollegeEducation.getCollegeEducationAmountPercentage()*goalCollegeEducation.getCollegeEducationAmount()/100;
			double totalAnualCollegeExpense= calCollegeExpense(collegeExpense,kidCollegeYear);
			int startYear=goalCollegeEducation.getKidCollegeYear();

			FinPlan finDetails = MongoDBConnection.finplancol.findOne("{_id:#}", fin_id).as(FinPlan.class);
			User Details = MongoDBConnection.userColl.findOne("{_id:#}", user_id).as(User.class);
			int userAge=Details.getAge();
			int userAgeTemp=Details.getAge();
			int spouseAgeTemp=Details.getSpouseAge();
			String maritalStatus=finDetails.getMarital_status();
			JSONObject finPlanJson = new JSONObject(jsonMapper.writeValueAsString(finDetails));
			JSONArray assets=finPlanJson.getJSONArray("assests");
			JSONArray tax=finPlanJson.getJSONArray("tax");
			JSONArray limits=finPlanJson.getJSONArray("limits");
			JSONArray deductions=finPlanJson.getJSONArray("deductions");
			JSONArray kidBirthYear=finPlanJson.getJSONArray("kidBirthYear");
			JSONArray filingExcemptionJSON=finPlanJson.getJSONArray("fillingExemtion");
			JSONArray expenseObj=new JSONArray();
			JSONObject expense=new JSONObject();
			expense=finPlanJson.getJSONObject("expense");
			if (!expense.isNull("housing_expense")) {
				expenseObj=finPlanJson.getJSONObject("expense").getJSONArray("housing_expense");
			}			String finPlanProfileName=finDetails.getProfile_name();
			if(goalDetails.getPlan529Saved()==0)
			{
				System.out.println("inside update 529==0");
				int m=0;
				double tempCost=(totalCollegeCostDb*20/100);
				for(int i=0;i<limits.length();i++)
				{
					if(limits.getJSONObject(i).getInt("year")<oldStartYear)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")-(totalCollegeCostDb*20/100));
					}
					if(limits.getJSONObject(i).getInt("year")>=oldStartYear && limits.getJSONObject(i).getInt("year")<=oldStartYear+3)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")-tempCost);
						tempCost=tempCost-((collegeGoalAmountDataFromdb.getJSONObject(m).getDouble("cost"))*20/100);
					}
					if(limits.getJSONObject(i).getInt("year")>=oldStartYear&&limits.getJSONObject(i).getInt("year")<=oldStartYear+3)
					{
						deductions.getJSONObject(i).put("collegeGoalTaxable",deductions.getJSONObject(i).getDouble("collegeGoalTaxable")-(collegeGoalAmountDataFromdb.getJSONObject(m).getDouble("cost")*20/100));
						if(deductions.getJSONObject(i).getInt("collegeGoalTaxable")<0)
						{
							deductions.getJSONObject(i).put("collegeGoalTaxable",0);
						}
						JSONObject collegeAmount=new JSONObject();
						collegeAmount.put("currentAmount", 1).put("type","delete").put("oldAmount", collegeGoalAmountDataFromdb.getJSONObject(m).getDouble("cost"));
						JSONObject newLimits=limiteAfterCollegeGoal(userAgeTemp, 0, 0, filingExcemptionJSON.getJSONObject(i).getString("fillingStatus"),spouseAgeTemp, limits, 0, i, collegeAmount,deductions.getJSONObject(i));
						limits=newLimits.getJSONArray("limits");
						deductions.put(i,newLimits.getJSONObject("deductions"));
						m++;
					}
				}
				//System.out.println("after deleting the arrays from db values limits "+limits);
				//System.out.println("after deleting the arrays from db values deductions "+deductions);

				if(goalCollegeEducation.getPlan529Saved()==0)
				{	m=0;
				tempCost=(totalCollegeCost*20/100);
				for(int i=0;i<limits.length();i++)
				{
					if(limits.getJSONObject(i).getInt("year")<startYear)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")+(totalCollegeCost*20/100));
					}
					if(limits.getJSONObject(i).getInt("year")>=startYear && limits.getJSONObject(i).getInt("year")<=startYear+3)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")+tempCost);
						tempCost=tempCost-((collegeGoalAmountData.getJSONObject(m).getDouble("cost")*20/100));
					}
					if(limits.getJSONObject(i).getInt("year")>=startYear&&limits.getJSONObject(i).getInt("year")<=startYear+3)
					{
						deductions.getJSONObject(i).put("collegeGoalTaxable",deductions.getJSONObject(i).getDouble("collegeGoalTaxable")+(collegeGoalAmountData.getJSONObject(m).getDouble("cost")*20/100));
						//deductions.getJSONObject(i).put("collegeGoalNontaxable",deductions.getJSONObject(i).getDouble("collegeGoalNontaxable")+collegeExpense*80/100);
						JSONObject collegeAmount=new JSONObject();
						collegeAmount.put("currentAmount", collegeGoalAmountData.getJSONObject(m).getDouble("cost")).put("type","create").put("oldAmount", 0);
						JSONObject newLimits=limiteAfterCollegeGoal(userAgeTemp, 0, 0, filingExcemptionJSON.getJSONObject(i).getString("fillingStatus"),spouseAgeTemp, limits, 0, i, collegeAmount,deductions.getJSONObject(i));
						limits=newLimits.getJSONArray("limits");
						deductions.put(i,newLimits.getJSONObject("deductions"));
						m++;
					}
					userAgeTemp++;
					spouseAgeTemp++;
				}
				//System.out.println("after recreating  the arrays from db values limits "+limits);
				//	System.out.println("after recreating the arrays from db values deductions "+deductions);
				}
				else
				{
					m=0;
					tempCost=(totalCollegeCost-(goalCollegeEducation.getPlan529Saved()));
					for(int i=0;i<limits.length();i++)
					{
						if(limits.getJSONObject(i).getInt("year")<startYear)
						{
							limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")+(totalCollegeCost-(goalCollegeEducation.getPlan529Saved())));
						}
						if(limits.getJSONObject(i).getInt("year")>=startYear && limits.getJSONObject(i).getInt("year")<=startYear+3)
						{
							limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")+tempCost);
							tempCost=tempCost-(collegeGoalAmountData.getJSONObject(m).getDouble("cost")-(goalCollegeEducation.getPlan529Saved()/4));
						}
						if(limits.getJSONObject(i).getInt("year")>=startYear&&limits.getJSONObject(i).getInt("year")<=startYear+3)
						{
							deductions.getJSONObject(i).put("collegeGoalTaxable",deductions.getJSONObject(i).getDouble("collegeGoalTaxable")+collegeGoalAmountData.getJSONObject(m).getDouble("cost")-(goalCollegeEducation.getPlan529Saved()/4));
							deductions.getJSONObject(i).put("collegeGoalNontaxable",deductions.getJSONObject(i).getDouble("collegeGoalNontaxable")+goalCollegeEducation.getPlan529Saved()/4);
							m++;
						}
					}
				}
			}
			else
			{
				//	System.out.println("inside update else 529==0");
				double tempCost=(totalCollegeCost-(goalDetails.getPlan529Saved()));
				int m=0;
				for(int i=0;i<limits.length();i++)
				{
					if(limits.getJSONObject(i).getInt("year")>=startYear && limits.getJSONObject(i).getInt("year")<=startYear+3)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")-tempCost);
						tempCost=tempCost-(collegeGoalAmountData.getJSONObject(m).getDouble("cost")-(goalDetails.getPlan529Saved()/4));
					}
					if(limits.getJSONObject(i).getInt("year")<oldStartYear)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")-(totalCollegeCostDb-(goalDetails.getPlan529Saved())));
					}
					if(limits.getJSONObject(i).getInt("year")>=oldStartYear&&limits.getJSONObject(i).getInt("year")<=oldStartYear+3)
					{
						deductions.getJSONObject(i).put("collegeGoalTaxable",deductions.getJSONObject(i).getDouble("collegeGoalTaxable")-collegeGoalAmountDataFromdb.getJSONObject(m).getDouble("cost")-(goalDetails.getPlan529Saved()/4));
						if(deductions.getJSONObject(i).getInt("collegeGoalTaxable")<0)
						{
							deductions.getJSONObject(i).put("collegeGoalTaxable",0);
						}
						deductions.getJSONObject(i).put("collegeGoalNontaxable",deductions.getJSONObject(i).getDouble("collegeGoalNontaxable")-(goalDetails.getPlan529Saved()/4));
						m++;
					}
				}
				if(goalCollegeEducation.getPlan529Saved()==0)
				{	 m=0;
				tempCost=(totalCollegeCost*20/100);
				for(int i=0;i<limits.length();i++)
				{
					if(limits.getJSONObject(i).getInt("year")<startYear)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")+(totalCollegeCost*20/100));
					}
					if(limits.getJSONObject(i).getInt("year")>=startYear && limits.getJSONObject(i).getInt("year")<=startYear+3)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")+tempCost);
						tempCost=tempCost-(collegeGoalAmountData.getJSONObject(m).getDouble("cost")*20/100);
					}
					if(limits.getJSONObject(i).getInt("year")>=startYear&&limits.getJSONObject(i).getInt("year")<=startYear+3)
					{
						deductions.getJSONObject(i).put("collegeGoalTaxable",deductions.getJSONObject(i).getDouble("collegeGoalTaxable")+collegeGoalAmountData.getJSONObject(m).getDouble("cost")*20/100);
						JSONObject collegeAmount=new JSONObject();
						collegeAmount.put("currentAmount", collegeExpense).put("type","create").put("oldAmount", oldCollegeExpense);
						JSONObject newLimits=limiteAfterCollegeGoal(userAgeTemp, 0, 0, filingExcemptionJSON.getJSONObject(i).getString("fillingStatus"),spouseAgeTemp, limits, 0, i, collegeAmount,deductions.getJSONObject(i));
						limits=newLimits.getJSONArray("limits");
						deductions.put(i,newLimits.getJSONObject("deductions"));
						m++;
					}
					userAgeTemp++;
					spouseAgeTemp++;
				}
				}
				else
				{
					m=0;
					tempCost=(totalCollegeCost-(goalCollegeEducation.getPlan529Saved()));
					for(int i=0;i<limits.length();i++)
					{
						if(limits.getJSONObject(i).getInt("year")<startYear)
						{
							limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")+(totalCollegeCost-(goalCollegeEducation.getPlan529Saved())));
						}
						if(limits.getJSONObject(i).getInt("year")>=startYear && limits.getJSONObject(i).getInt("year")<=startYear+3)
						{
							limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")+tempCost);
							tempCost=tempCost-(collegeGoalAmountData.getJSONObject(m).getDouble("cost")-(goalCollegeEducation.getPlan529Saved()/4));
						}
						if(limits.getJSONObject(i).getInt("year")>=startYear&&limits.getJSONObject(i).getInt("year")<=startYear+3)
						{
							deductions.getJSONObject(i).put("collegeGoalTaxable",deductions.getJSONObject(i).getDouble("collegeGoalTaxable")+(collegeGoalAmountData.getJSONObject(m).getDouble("cost")-(goalCollegeEducation.getPlan529Saved()/4)));
							deductions.getJSONObject(i).put("collegeGoalNontaxable",deductions.getJSONObject(i).getDouble("collegeGoalNontaxable")+goalCollegeEducation.getPlan529Saved()/4);
							m++;
						}
					}
				}
			}

			IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}",user_id ,finPlanProfileName).as(IncomeProfile.class);
			JSONObject incomeProfileJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
			JSONArray userIncome=incomeProfileJson.getJSONArray("user_income");
			JSONArray SpouseIncomeArray=new JSONArray();
			JSONArray combinedIncome =new JSONArray();
			int spouseAge;
			if(maritalStatus.equals("Yes"))
			{
				SpouseIncomeArray=incomeProfileJson.getJSONArray("spouse_income");
				IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
				combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, SpouseIncomeArray);
				spouseAge=finDetails.getSpouseAge();
			}
			else
			{
				combinedIncome=userIncome;
				spouseAge=0;
			}
			int emergencyFundAmt=finDetails.getEmergencyFundAmt();
			boolean emergencyFundFlag=finDetails.isEmergencyFundFlag();
			RetirementGoal retirementObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",finDetails.get_id(),"Retirement").as(RetirementGoal.class);
			int SpouseStartYear=0;
			int userStartYear=0;

			if(retirementObj!=null)
			{
				userStartYear=Details.getBirthYear()+retirementObj.getRetirementAge();
				if(finDetails.getMarital_status().equals("Yes")&& Details.getMarital_status().equals("No")){
					SpouseStartYear=finDetails.getSpouseBirthYear()+retirementObj.getSpouseRetirementAge();
				}
				else if(Details.getMarital_status().equals("Yes"))
				{
					SpouseStartYear=Details.getSpouseBirthYear()+retirementObj.getSpouseRetirementAge();
				}
			}
			else{
				userStartYear=Details.getBirthYear()+70;
				if(finDetails.getMarital_status().equals("Yes")&& Details.getMarital_status().equals("No")){
					SpouseStartYear=finDetails.getSpouseBirthYear()+70;
				}
				else if(Details.getMarital_status().equals("Yes"))
				{
					SpouseStartYear=Details.getSpouseBirthYear()+70;
				}
			}
			JSONObject retirementData=new JSONObject();
			retirementData.put("spouseStartYear", SpouseStartYear);
			retirementData.put("userStartYear", userStartYear);

			String emergencyType=null;
			String monthsOfIncome=null;
			String monthsOfExpense=null;
			if(emergencyFundFlag==true)
			{
				Emergencyfundmodel emergencyObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",finDetails.get_id(),"Emergency Fund").as(Emergencyfundmodel.class);
				emergencyType=emergencyObj.getTimePeriod();
				monthsOfIncome=emergencyObj.getMonthI();
				monthsOfExpense=emergencyObj.getMonthE();
			}
			System.out.println("userExpense in update "+userExpense);
			JSONObject result=CalculationEngine.sweepingOfMoney(userIncome, fin_id,combinedIncome, SpouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, user_id, fillingExemtion, userAge,spouseAge, emergencyFundAmt, emergencyFundFlag,deductions,kidBirthYear, retirementData, retirementObj, expenseObj,emergencyType,monthsOfIncome,monthsOfExpense,finDetails.isRetirementFlag());

			String status=result.getString("status");
			System.out.println("status >> "+status);
			if(status.equals("success"))
			{
				assets=result.getJSONArray("assets");
				tax=result.getJSONArray("tax");
				MongoDBConnection.goalcoll.update("{'_id': '" + goal_id + "'}").upsert().multi().with("{$set: {'kidCollegeYear':"
						+ goalCollegeEducation.getKidCollegeYear() + ",'collegeType':'" + goalCollegeEducation.getCollegeType()
						+ "','collegeEducationAmountPercentage':"+goalCollegeEducation.getCollegeEducationAmountPercentage()+",'collegeEducationAmount':" + goalCollegeEducation.getCollegeEducationAmount()  +",'goalFeasibility':"+true+ ",'collegeExpense':"+collegeExpense+ ",'modified_ts':'" +dateFormat.format(date)+ "','plan529Saved':"+goalCollegeEducation.getPlan529Saved()+",'collegeGoalAmountData':"+ collegeGoalAmountData +",'percentageCollegeCost':"+goalCollegeEducation.getPercentageCollegeCost()+",'totalCollegeCost':"+totalCollegeCost+"}}");

				System.out.println("collegeGoalAmountDataForFinplan ??? "+collegeGoalAmountDataForFinplan);
				updateFinplanForGoalCollegeEducation(goalName,fin_id,goal_id,collegeEducationAmount,kidCollegeYear,totalAnualCollegeExpense,"update",collegeGoalAmountDataForFinplan);
				MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi().with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits + ",'deductions':" + deductions + ",'userExpense':" + userExpense + ",'fillingExemtion':" + fillingExemtion + "}}");


				MongoDBConnection.finplancol.update("{'_id':'"+fin_id+"'}").upsert().multi().with("{$set: {'expense.kid_expense':"+kid_expense+"}}}");
				responseToRestController.put("status","success");
				responseToRestController.put("goalType", goalCollegeEducation.getGoalType());
				responseToRestController.put("kidCollegeYear",goalCollegeEducation.getKidCollegeYear());
				responseToRestController.put("collegeType",goalCollegeEducation.getCollegeType());
				responseToRestController.put("collegeEducationAmount",goalCollegeEducation.getCollegeEducationAmount());
				responseToRestController.put("modified_ts",goalCollegeEducation.getModified_ts());
				responseToRestController.put("collegeExpense", totalAnualCollegeExpense);
				responseToRestController.put("plan_name", plan_name);
				responseToRestController.put("goal_name", goalCollegeEducation.getGoalname());
				responseToRestController.put("collegeEducationAmountPercentage", goalCollegeEducation.getCollegeEducationAmountPercentage());
				responseToRestController.put("goalFeasiblity",true);
				responseToRestController.put("plan529Saved",goalCollegeEducation.getPlan529Saved());
				responseToRestController.put("PercentageCollegeCost",goalCollegeEducation.getPercentageCollegeCost());
				responseToRestController.put("costArray",collegeGoalAmountData);
				responseToRestController.put("totalCollegeCost",decimalFloat.format(totalCollegeCost));
				org.jongo.MongoCursor<GoalCollegeEducation> collegeCosts = MongoDBConnection.CollegeCostsColl.find().as(GoalCollegeEducation.class);
				JSONArray collegeCostsTemp = new JSONArray();
				while(collegeCosts.hasNext())
				{
					GoalCollegeEducation goal = collegeCosts.next();
					JSONObject tempObject = new JSONObject();
					tempObject.put("type", goal.getType());
					tempObject.put("Total", goal.getTotal());
					tempObject.put("TuitionAndFees", goal.getTuitionAndFees());
					tempObject.put("RoomAndBoard", goal.getRoomAndBoard());
					tempObject.put("Transportation", goal.getTransportation());
					tempObject.put("BooksAndSupplies", goal.getBooksAndSupplies());
					tempObject.put("OtherExpenses", goal.getOtherExpenses());
					collegeCostsTemp.put(tempObject);
				}
				//System.out.println("collegeCosts::: "+collegeCostsTemp);
				responseToRestController.put("collegeCosts",collegeCostsTemp);
			}
			else
			{
				System.out.println("iin impl faillll ");
				responseToRestController.put("status","fail");
			}
			systemLog.put("type", "success");
			systemLog.put("message", "Goal updated Successfully!!");
			systemLog.put("userName", user.getName());
			systemLog.put("goalDetails", goalDetails.getUser_id());
			systemLog.put("createdTs", dateFormat.format(date));
		}
		catch(Exception e)
		{
			type="fail";
			e.printStackTrace();
		}
		finally {

			BasicDBObject systemLogBasicObject = new BasicDBObject("user_id", goalDetails.getUser_id())
					.append("userName", user.getName())

					.append("type",type)
					.append("message", systemLog.getString("message"))
					.append("createdTs", dateFormat.format(date));
			MongoDBConnection.systemLogCollection.insert(systemLogBasicObject);
		}
		return responseToRestController;

	}
	/*--------------------------Update FinPlan Collection For College Education Goal----------------------------*/
	public JSONObject updateFinplanForGoalCollegeEducation(String goalName,String fin_id,String goal_id,long collegeEducationAmount,int kidCollegeYear,double totalAnualCollegeExpense,String actionType,JSONArray collegeGoalAmountData) throws JSONException {
		JSONObject responseToinsertupdateGoalCollegeEducation = new JSONObject();
		responseToinsertupdateGoalCollegeEducation.put("status","fail");
		JSONObject collegeeducation_expensedata = new JSONObject();
		JSONArray college_expense = new JSONArray();
		try {
			//System.out.println("collegeGoalAmountData /// "+collegeGoalAmountData);
			collegeeducation_expensedata.put("collegeExpense", totalAnualCollegeExpense);
			collegeeducation_expensedata.put("startYear", kidCollegeYear);
			collegeeducation_expensedata.put("endYear", kidCollegeYear+3);
			collegeeducation_expensedata.put("goalname", goalName);
			collegeeducation_expensedata.put("collegeGoalAmountData",collegeGoalAmountData);
			college_expense.put(collegeeducation_expensedata);
			FinPlan finPlanDetails=MongoDBConnection.finplancol.findOne("{_id:#}", fin_id).as(FinPlan.class);
			JSONObject finJson=new JSONObject(jsonMapper.writeValueAsString(finPlanDetails));
			JSONArray collegeArray=null;
			JSONObject expenseJson=null;
			if(!finJson.isNull("expense"))
			{
				expenseJson=finJson.getJSONObject("expense");

				if(!expenseJson.isNull("college_expense"))
				{
					collegeArray=expenseJson.getJSONArray("college_expense");

				}
			}
			boolean flag=false;
			System.out.println("collegeArray "+collegeArray);
			if(collegeArray==null)
			{
				MongoDBConnection.finplancol.update("{'_id': '" + fin_id + "'}").upsert().multi()
				.with("{$set: {'expense.college_expense':" + college_expense + "}}");
			}
			else
			{
				for(int i=0;i<collegeArray.length();i++)
				{
					if(collegeArray.getJSONObject(i).getString("goalname").equals(goalName))
					{

						collegeArray.getJSONObject(i).put("endYear", kidCollegeYear+3);
						collegeArray.getJSONObject(i).put("startYear", kidCollegeYear);
						collegeArray.getJSONObject(i).put("collegeExpense", totalAnualCollegeExpense);
						collegeArray.getJSONObject(i).put("collegeGoalAmountData",collegeGoalAmountData);
						flag=true;
						break;

					}
				}
				//System.out.println("flag "+flag +"collegeArray "+collegeArray);
				if(flag==false)
				{
					collegeArray.put(collegeeducation_expensedata);
					MongoDBConnection.finplancol.update("{'_id': '" + fin_id + "'}").upsert().multi()
					.with("{$set: {'expense.college_expense':" + collegeArray + "}}");
				}
				else
				{
					MongoDBConnection.finplancol.update("{'_id': '" + fin_id + "'}").upsert().multi()
					.with("{$set: {'expense.college_expense':" + collegeArray + "}}");
				}
			}

			responseToinsertupdateGoalCollegeEducation.put("status","success");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseToinsertupdateGoalCollegeEducation;


	}

	/*-------------------------fetching Data from goal collection for college education Goal----------------------*/

	@Override
    public JSONObject editGoalCollegeEducationData(GoalCollegeEducation goalCollegeEducation) {
		JSONObject responseToRestController= new JSONObject();
		String goal_id=goalCollegeEducation.getGoal_id();
		try {
			GoalCollegeEducation goalCollegeEducationData=MongoDBConnection.goalcoll.findOne("{_id:#}",goal_id).as(GoalCollegeEducation.class);
			long collegeExpense=goalCollegeEducationData.getCollegeEducationAmountPercentage()*goalCollegeEducationData.getCollegeEducationAmount()/100;
			goalCollegeEducationData.setPlan_name(goalCollegeEducationData.getPlan_name());
			String[]temp=goalCollegeEducationData.getGoalname().split("-");
			goalCollegeEducationData.setKidName(temp[0]);
			JSONObject	resposneFromCalArray=calYearArray(goalCollegeEducationData);
			goalCollegeEducation.setPlan_name(goalCollegeEducationData.getPlan_name());
			goalCollegeEducation.setUser_id(goalCollegeEducationData.getUser_id());
			JSONObject plan529JSON=getPlan529Amount(goalCollegeEducation);
			JSONObject collegeDataArray=new JSONObject(jsonMapper.writeValueAsString(goalCollegeEducationData));
			JSONArray costArray=collegeDataArray.getJSONArray("collegeGoalAmountData");
			responseToRestController.put("status","success");
			responseToRestController.put("goalType", goalCollegeEducationData.getGoalType());
			responseToRestController.put("kidCollegeYear",goalCollegeEducationData.getKidCollegeYear());
			responseToRestController.put("collegeType",goalCollegeEducationData.getCollegeType());
			responseToRestController.put("collegeEducationAmount",goalCollegeEducationData.getCollegeEducationAmount());
			responseToRestController.put("collegeExpenseAfterDeduction",collegeExpense);
			responseToRestController.put("modified_ts",goalCollegeEducationData.getModified_ts());
			responseToRestController.put("collegeExpense", goalCollegeEducationData.getCollegeExpense());
			responseToRestController.put("plan_name", goalCollegeEducationData.getPlan_name());
			responseToRestController.put("goal_name", goalCollegeEducationData.getGoalname());
			responseToRestController.put("collegeEducationAmountPercentage", goalCollegeEducationData.getCollegeEducationAmountPercentage());
			responseToRestController.put("goalFeasiblity",goalCollegeEducationData.getGoalFeasibility() );
			responseToRestController.put("plan529Saved",goalCollegeEducationData.getPlan529Saved());
			responseToRestController.put("year",resposneFromCalArray.getJSONArray("year"));
			responseToRestController.put("plan529",plan529JSON.getDouble("plan529"));
			responseToRestController.put("PercentageCollegeCost",goalCollegeEducationData.getPercentageCollegeCost());
			responseToRestController.put("totalCollegeCost", decimalFloat.format(goalCollegeEducationData.getTotalCollegeCost()));
			responseToRestController.put("costArray",costArray);

			org.jongo.MongoCursor<GoalCollegeEducation> collegeCosts = MongoDBConnection.CollegeCostsColl.find().as(GoalCollegeEducation.class);
			JSONArray collegeCostsTemp = new JSONArray();
			while(collegeCosts.hasNext())
			{
				GoalCollegeEducation goal = collegeCosts.next();
				JSONObject tempObject = new JSONObject();
				tempObject.put("type", goal.getType());
				tempObject.put("Total", goal.getTotal());
				tempObject.put("TuitionAndFees", goal.getTuitionAndFees());
				tempObject.put("RoomAndBoard", goal.getRoomAndBoard());
				tempObject.put("Transportation", goal.getTransportation());
				tempObject.put("BooksAndSupplies", goal.getBooksAndSupplies());
				tempObject.put("OtherExpenses", goal.getOtherExpenses());
				collegeCostsTemp.put(tempObject);
			}
			//System.out.println("collegeCosts::: "+collegeCostsTemp);
			responseToRestController.put("collegeCosts",collegeCostsTemp);
			//////System.out.println("Sending Response to Rest HOME from editcollegeGoalData");
			return responseToRestController;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return responseToRestController;


	}


	@Override
    public JSONObject fetchKidData(GoalCollegeEducation goalCollegeEducation) {
		JSONObject responseToRestController=new JSONObject();
		JSONArray childArray=new JSONArray();
		JSONArray collegeChildArray=new JSONArray();
		JSONArray responseArray=new JSONArray();
		try
		{
			/*MongoCollection finplancol = jongo.getCollection(finplancollection);
			MongoCollection userncol = jongo.getCollection(usersCollection);*/
			FinPlan getfinplan =  MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}",goalCollegeEducation.getUser_id(),goalCollegeEducation.getPlan_name()).as(FinPlan.class);
			User userDetails =  MongoDBConnection.userColl.findOne("{_id:#}",goalCollegeEducation.getUser_id()).as(User.class);
			JSONObject finPlanJson=new JSONObject(jsonMapper.writeValueAsString(getfinplan));
			JSONObject userColData=new JSONObject(jsonMapper.writeValueAsString(userDetails));
			if(!userColData.isNull("childs"))
			{
				for(int i=0;i<userColData.getJSONArray("childs").length();i++)
				{
					childArray.put(" - "+userColData.getJSONArray("childs").getJSONObject(i).getString("name"));
				}
			}
			if(!finPlanJson.isNull("expense"))
			{
				if(!finPlanJson.getJSONObject("expense").isNull("college_expense"))
				{
					for(int i=0;i<finPlanJson.getJSONObject("expense").getJSONArray("college_expense").length();i++)
					{
						String [] goalName=finPlanJson.getJSONObject("expense").getJSONArray("college_expense").getJSONObject(i).getString("goalname").split("-");
						collegeChildArray.put(goalName[1]);
					}
				}
				if(!finPlanJson.getJSONObject("expense").isNull("kid_expense"))
				{
					for(int i=0;i<finPlanJson.getJSONObject("expense").getJSONArray("kid_expense").length();i++)
					{
						childArray.put(finPlanJson.getJSONObject("expense").getJSONArray("kid_expense").getJSONObject(i).getString("goalname"));
					}
				}
			}
			for(int i=0;i<childArray.length();i++)
			{
				boolean flag=true;
				for(int j=0;j<collegeChildArray.length();j++)
				{
				    String [] goalName = childArray.getString(i).split("-");
					if(goalName[1].equals(collegeChildArray.getString(j)))
					{
						flag=false;
					}
				}
				if(flag==true)
				{
					responseArray.put(childArray.getString(i));
				}
			}
			ConstantsValues ConstantDetails = MongoDBConnection.Constantvaluecoll.findOne("{_id:#}","constantValue1").as(ConstantsValues.class);

			responseToRestController.put("collegeGoalDefaultCostPeryearForinstatepubliccollege",ConstantDetails.getCollegeGoalDefaultCostPeryearForinstatepubliccollege() );
			responseToRestController.put("collegeGoalDefaultCostPeryearForoutofstatepubliccollege",ConstantDetails.getCollegeGoalDefaultCostPeryearForoutofstatepubliccollege() );
			responseToRestController.put("collegeGoalDefaultCostPeryearForPrivateCollege",ConstantDetails.getCollegeGoalDefaultCostPeryearForPrivateCollege() );
			responseToRestController.put("status","success").put("childArray",responseArray).put("plan529",userDetails.getUser529()+userDetails.getSpousePlan529());
			System.out.println("aparna college details     "+responseToRestController);
			org.jongo.MongoCursor<GoalCollegeEducation> collegeCosts = MongoDBConnection.CollegeCostsColl.find().as(GoalCollegeEducation.class);
			JSONArray collegeCostsTemp1 = new JSONArray();
			while(collegeCosts.hasNext())
			{
				GoalCollegeEducation goal1 = collegeCosts.next();
				JSONObject tempObject1 = new JSONObject();
				tempObject1.put("type", goal1.getType());
				tempObject1.put("Total", goal1.getTotal());
				collegeCostsTemp1.put(tempObject1);
			}
			//System.out.println("collegeCosts::: "+collegeCostsTemp1);
			responseToRestController.put("collegeCosts",collegeCostsTemp1);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return responseToRestController;

	}
	@Override
    public JSONObject calYearArray(GoalCollegeEducation goalCollegeEducation)
	{
		JSONObject responseToServlet=new JSONObject();
		try{
			int startYear=0;
			/*MongoCollection finplancol = jongo.getCollection(finplancollection);
			MongoCollection userncol = jongo.getCollection(usersCollection);*/
			JSONArray yearArray=new JSONArray();
			FinPlan getfinplan =  MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}",goalCollegeEducation.getUser_id(),goalCollegeEducation.getPlan_name()).as(FinPlan.class);
			User userDetails =  MongoDBConnection.userColl.findOne("{_id:#}",goalCollegeEducation.getUser_id()).as(User.class);
			String [] goalType=goalCollegeEducation.getKidName().split("-");
			System.out.println("goalCollegeEducation.getKidName()====="+goalCollegeEducation.getKidName());
			System.out.println("goalCollegeEducation.getKidName()====="+goalType[0]);
			JSONObject finPlanJson=new JSONObject(jsonMapper.writeValueAsString(getfinplan));
			JSONObject userColData=new JSONObject(jsonMapper.writeValueAsString(userDetails));
			String[] out=userDetails.getCreatedTs().split("/");
			int registrationYear=Integer.parseInt(out[0]);
			String goalName=goalType[0];
			/*if(goalType.length>3)
			{
				goalName=goalType[2];
			}*/
			if(goalName.contains("Raising"))
			{
				for(int i=0;i<finPlanJson.getJSONObject("expense").getJSONArray("kid_expense").length();i++)
				{

					if(finPlanJson.getJSONObject("expense").getJSONArray("kid_expense").getJSONObject(i).getString("goalname").equals(goalCollegeEducation.getKidName()))
					{
						//  kidExpenseAnual=kid_expense.getJSONObject(i).getDouble("annualExpense");
						startYear=finPlanJson.getJSONObject("expense").getJSONArray("kid_expense").getJSONObject(i).getInt("startYear")+10;
						//kid_expense.getJSONObject(i).put("endYear", newEndYear);
						break;
					}
					//childArray.put(finPlanJson.getJSONObject("expense").getJSONArray("kid_expense").getJSONObject(i).getString("goalname"));
				}
			}
			else{

				for(int i=0;i<userColData.getJSONArray("childs").length();i++)
				{
				    //System.out.println();
					if((" - "+userColData.getJSONArray("childs").getJSONObject(i).getString("name")).equals(goalCollegeEducation.getKidName()))
					{
						//  kidExpenseAnual=kid_expense.getJSONObject(i).getDouble("annualExpense");
						startYear=Math.max(registrationYear-userColData.getJSONArray("childs").getJSONObject(i).getInt("age")+10,registrationYear);
						//kid_expense.getJSONObject(i).put("endYear", newEndYear);
						break;
					}
				}
			}
			for(int i=startYear;i<startYear+24;i++)
			{
				yearArray.put(i);
			}
			responseToServlet.put("status","success").put("year",yearArray).put("plan529",userDetails.getUser529()+userDetails.getSpousePlan529());
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
		return responseToServlet;
	}

	@Override
    public JSONObject deleteGoal(GoalCollegeEducation goalCollegeEducation)
	{
		JSONObject responseToRestController=new JSONObject();
		try{
			String user_id=goalCollegeEducation.getUser_id();
			String goal_id=goalCollegeEducation.getGoal_id();
			String plan_name=goalCollegeEducation.getPlan_name();
			JSONArray kid_expense=new JSONArray();
			User user = MongoDBConnection.userColl.findOne("{_id:#}", goalCollegeEducation.getUser_id()).as(User.class);
			FinPlan getfinplan =  MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}",goalCollegeEducation.getUser_id(),goalCollegeEducation.getPlan_name()).as(FinPlan.class);
			String fin_id=getfinplan.get_id();
			JSONObject userJson=new JSONObject(jsonMapper.writeValueAsString(getfinplan));


			GoalCollegeEducation goal = MongoDBConnection.goalcoll.findOne("{_id:#}",  goalCollegeEducation.getGoal_id())
					.as(GoalCollegeEducation.class);
			JSONObject collegeDataArray=new JSONObject(jsonMapper.writeValueAsString(goal));
			JSONArray collegeGoalAmountData=collegeDataArray.getJSONArray("collegeGoalAmountData");
			//System.out.println("collegeGoalAmountData in impl file > > > in delete method "+collegeGoalAmountData);
			JSONArray collegeArray = null;
			CollegeeducationExpense goalTypeInDb = MongoDBConnection.goalcoll.findOne("{_id:#}",  goalCollegeEducation.getGoal_id())
					.as(CollegeeducationExpense.class);

			JSONObject expenseJson = userJson.getJSONObject("expense");
			if (!expenseJson.isNull("college_expense")) {
				collegeArray = expenseJson.getJSONArray("college_expense");
			}
			JSONArray college_expense = new JSONArray();
			for (int i = 0; i < collegeArray.length(); i++) {
				if (collegeArray.getJSONObject(i).getString("goalname").equals(goalTypeInDb.getGoalname())) {
					continue;
				} else {
					college_expense.put(collegeArray.getJSONObject(i));
				}
			}
			JSONArray fillingExemtion = userJson.getJSONArray("fillingExemtion");
			JSONArray userExpense = userJson.getJSONArray("userExpense");
			String[] goalName = goal.getGoalname().split("-");
			String kidName = goalName[0];
			for (int j = 0; j < fillingExemtion.length(); j++) {
				if (fillingExemtion.getJSONObject(j).getInt("year") > goal
						.getStartYearForExpenseAndExceptionChange()
						&& fillingExemtion.getJSONObject(j).getInt("year") <= goal
						.getEndYearForExpenseAndExceptionChange()) {
					System.out.println("in delelellete "+fillingExemtion.getJSONObject(j).getInt("year") +"  "+goal
							.getStartYearForExpenseAndExceptionChange()+"   "+goal
							.getEndYearForExpenseAndExceptionChange());
					int noOfexcepmtion = fillingExemtion.getJSONObject(j).getInt("noOfExcemtion") - 1;
					System.out.println("in deeeleeteee noOfexcepmtion "+noOfexcepmtion);
					fillingExemtion.getJSONObject(j).put("noOfExcemtion", noOfexcepmtion);
					if(fillingExemtion.getJSONObject(j).getString("fillingStatus").equals("Head of Household"))
					{
						fillingExemtion.getJSONObject(j).put("fillingStatus","Single");
					}
				}
			}
			System.out.println("excemption in delete "+fillingExemtion);
			//System.out.println("goal.getChildType"+goal.getChildType()+" "+kidName +"ffff"+kidName.substring(18));
			if (goal.getChildType().equals("Raising A Kid")) {
				int kidIndex=0;
				kid_expense = expenseJson.getJSONArray("kid_expense");
				System.out.println("kid_expense ffff "+kid_expense);
				//System.out.println("kidName in side if loop "+kidName);
				for (int i = 0; i < kid_expense.length(); i++) {
					if (kid_expense.getJSONObject(i).getString("goalname").equals(kidName.substring(18))) {
						int newEndYear = kid_expense.getJSONObject(i).getInt("startYear") + 18;
						kid_expense.getJSONObject(i).put("endYear", newEndYear);
						//System.out.println("inside loop..."+kid_expense);
						kidIndex=i;
						break;
					}
				}
				System.out.println("in delete kid_expense"+kid_expense);
				int collegeGoalYearCounter=0;
				System.out.println("beforreeeee deleeeete "+userExpense);
				for (int i = 0; i < userExpense.length(); i++) {
					if(goal.getKidCollegeYear()+collegeGoalYearCounter<=kid_expense.getJSONObject(kidIndex).getInt("startYear")+18 && userExpense.getJSONObject(i).getInt("year") <=kid_expense.getJSONObject(kidIndex).getInt("startYear")+18 && fillingExemtion.getJSONObject(i).getInt("year") >=goal.getKidCollegeYear())
					{
						System.out.println("yeraaa valueee. "+userExpense.getJSONObject(i).getInt("year"));
						double newKidExpense=userExpense.getJSONObject(i).getDouble("kidExpense")+kid_expense.getJSONObject(kidIndex).getDouble("annualExpense");
						double newTotalExpense=userExpense.getJSONObject(i).getDouble("totalExpense")+kid_expense.getJSONObject(kidIndex).getDouble("annualExpense");
						userExpense.getJSONObject(i).put("totalExpense", newTotalExpense).put("kidExpense", newKidExpense);
						collegeGoalYearCounter++;
					}
				}

			}
			System.out.println("userExpense in delllletete "+userExpense);
			double totalCollegeCost=0;
			for(int y=0;y<collegeGoalAmountData.length();y++)
			{
				totalCollegeCost=totalCollegeCost+collegeGoalAmountData.getJSONObject(y).getDouble("cost");
			}
			System.out.println(" in delete collegeGoalAmountData"+collegeGoalAmountData+" totalCollegeCost "+totalCollegeCost);
			double oldCollegeExpense=goal.getCollegeEducationAmount()*goal.getCollegeEducationAmountPercentage()/100;
			int oldStartYear=goal.getKidCollegeYear();
			int userAge=user.getAge();
			int userAgeTemp=user.getAge();
			int spouseAgeTemp=user.getSpouseAge();
			String maritalStatus=getfinplan.getMarital_status();

			JSONArray assets=userJson.getJSONArray("assests");
			JSONArray tax=userJson.getJSONArray("tax");
			JSONArray limits=userJson.getJSONArray("limits");
			JSONArray deductions=userJson.getJSONArray("deductions");
			JSONArray kidBirthYear=userJson.getJSONArray("kidBirthYear");
			JSONArray filingExcemptionJSON=userJson.getJSONArray("fillingExemtion");
			JSONArray expenseObj=new JSONArray();
			JSONObject expense=new JSONObject();
			expense=userJson.getJSONObject("expense");
			if (!expense.isNull("housing_expense")) {
				expenseObj=userJson.getJSONObject("expense").getJSONArray("housing_expense");
			}		String finPlanProfileName=getfinplan.getProfile_name();
			if(goal.getPlan529Saved()==0)
			{
				//double tempCost=(oldCollegeExpense*20/100)*4;
				int m=0;
				double tempCost=(totalCollegeCost*20/100);
				for(int i=0;i<limits.length();i++)
				{
					if(limits.getJSONObject(i).getInt("year")<oldStartYear)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")-(totalCollegeCost*20/100));
					}
					if(limits.getJSONObject(i).getInt("year")>=oldStartYear && limits.getJSONObject(i).getInt("year")<=oldStartYear+3)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")-tempCost);
						tempCost=tempCost-((collegeGoalAmountData.getJSONObject(m).getDouble("cost"))*20/100);;
					}
					if(limits.getJSONObject(i).getInt("year")>=oldStartYear&&limits.getJSONObject(i).getInt("year")<=oldStartYear+3)
					{
						deductions.getJSONObject(i).put("collegeGoalTaxable",deductions.getJSONObject(i).getDouble("collegeGoalTaxable")-((collegeGoalAmountData.getJSONObject(m).getDouble("cost"))*20/100));
						if(deductions.getJSONObject(i).getInt("collegeGoalTaxable")<0)
						{
							deductions.getJSONObject(i).put("collegeGoalTaxable",0);
						}
						/*deductions.getJSONObject(i).put("collegeGoalNontaxable",deductions.getJSONObject(i).getDouble("collegeGoalNontaxable")-oldCollegeExpense*80/100);
			if(deductions.getJSONObject(i).getInt("collegeGoalNontaxable")<0)
			{
				deductions.getJSONObject(i).put("collegeGoalNontaxable",0);
			}*/
						JSONObject collegeAmount=new JSONObject();
						collegeAmount.put("currentAmount", 1).put("type","delete").put("oldAmount", collegeGoalAmountData.getJSONObject(m).getDouble("cost"));
						JSONObject newLimits=limiteAfterCollegeGoal(userAgeTemp, 0, 0, filingExcemptionJSON.getJSONObject(i).getString("fillingStatus"),spouseAgeTemp, limits, 0, i, collegeAmount,deductions.getJSONObject(i));
						limits=newLimits.getJSONArray("limits");
						deductions.put(i,newLimits.getJSONObject("deductions"));
						m++;
					}
					userAgeTemp++;
					spouseAgeTemp++;
				}
				System.out.println(" in delete limits in college goal"+limits);
				System.out.println("  in delete deductions in college goal "+deductions);
			}
			else{
				//double tempCost=(oldCollegeExpense-(goal.getPlan529Saved()/4))*4;
				double tempCost=totalCollegeCost-goal.getPlan529Saved();
				int m=0;
				for(int i=0;i<limits.length();i++)
				{
					if(limits.getJSONObject(i).getInt("year")<oldStartYear)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")-(totalCollegeCost-goal.getPlan529Saved()));
					}
					if(limits.getJSONObject(i).getInt("year")>=oldStartYear && limits.getJSONObject(i).getInt("year")<=oldStartYear+3)
					{
						limits.getJSONObject(i).put("collegeGoalTaxable", limits.getJSONObject(i).getInt("collegeGoalTaxable")-tempCost);
						tempCost=tempCost-((collegeGoalAmountData.getJSONObject(m).getDouble("cost"))-(goal.getPlan529Saved()/4));
					}
					if(limits.getJSONObject(i).getInt("year")>=oldStartYear&&limits.getJSONObject(i).getInt("year")<=oldStartYear+3)
					{
						deductions.getJSONObject(i).put("collegeGoalTaxable",deductions.getJSONObject(i).getDouble("collegeGoalTaxable")-((collegeGoalAmountData.getJSONObject(m).getDouble("cost"))-(goal.getPlan529Saved()/4)));
						if(deductions.getJSONObject(i).getInt("collegeGoalTaxable")<0)
						{
							deductions.getJSONObject(i).put("collegeGoalTaxable",0);
						}
						deductions.getJSONObject(i).put("collegeGoalNontaxable",deductions.getJSONObject(i).getDouble("collegeGoalNontaxable")-goal.getPlan529Saved()/4);
						if(deductions.getJSONObject(i).getInt("collegeGoalNontaxable")<0)
						{
							deductions.getJSONObject(i).put("collegeGoalNontaxable",0);
						}
						m++;
					}
				}

			}
			IncomeProfile incomeProfileDetails = MongoDBConnection.incomeProfileCol.findOne("{user_id:#,profile_name:#}",user_id ,finPlanProfileName).as(IncomeProfile.class);
			JSONObject incomeProfileJson = new JSONObject(jsonMapper.writeValueAsString(incomeProfileDetails));
			JSONArray userIncome=incomeProfileJson.getJSONArray("user_income");
			JSONArray SpouseIncomeArray=new JSONArray();
			JSONArray combinedIncome =new JSONArray();
			int spouseAge;
			if(maritalStatus.equals("Yes"))
			{
				SpouseIncomeArray=incomeProfileJson.getJSONArray("spouse_income");
				IncomeProfileImpl calCombinedIncome = new IncomeProfileImpl();
				combinedIncome = calCombinedIncome.calCombinedIncome(userIncome, SpouseIncomeArray);
				spouseAge=getfinplan.getSpouseAge();
			}
			else
			{
				combinedIncome=userIncome;
				spouseAge=0;
			}
			int emergencyFundAmt=getfinplan.getEmergencyFundAmt();
			boolean emergencyFundFlag=getfinplan.isEmergencyFundFlag();
			RetirementGoal retirementObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",getfinplan.get_id(),"Retirement").as(RetirementGoal.class);
			Calendar cal = Calendar.getInstance();
			int currentYear = cal.get(Calendar.YEAR);

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

			String emergencyType=null;
			String monthsOfIncome=null;
			String monthsOfExpense=null;
			if(emergencyFundFlag==true)
			{
				Emergencyfundmodel emergencyObj=MongoDBConnection.goalcoll.findOne("{fin_id:#,goalType:#}",getfinplan.get_id(),"Emergency Fund").as(Emergencyfundmodel.class);
				emergencyType=emergencyObj.getTimePeriod();
				monthsOfIncome=emergencyObj.getMonthI();
				monthsOfExpense=emergencyObj.getMonthE();
			}
			JSONObject result=CalculationEngine.sweepingOfMoney(userIncome, fin_id,combinedIncome, SpouseIncomeArray, userExpense, limits, maritalStatus, assets, tax, user_id, fillingExemtion, userAge,spouseAge, emergencyFundAmt, emergencyFundFlag,deductions,kidBirthYear, retirementData, retirementObj, expenseObj,emergencyType,monthsOfIncome,monthsOfExpense,getfinplan.isRetirementFlag());

			String status=result.getString("status");
			if(status.equals("success"))
			{
				assets=result.getJSONArray("assets");
				tax=result.getJSONArray("tax");


				MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi().with("{$set: {'assests':" + assets + ",'tax':" + tax + ",'limits':" + limits + ",'deductions':" + deductions + ",'userExpense':" + userExpense + ",'fillingExemtion':" + fillingExemtion + "}}");

				MongoDBConnection.goalcoll.update("{user_id:#,dependentCollageGoal:#}",user_id, goal_id).with("{$set: {'dependentCollageGoal':"+null+"}}}");
				MongoDBConnection.finplancol.update("{'_id':'"+fin_id+"'}").with("{$inc: {collegeGoalCount: -1}}");
				MongoDBConnection.finplancol.update("{'_id':'"+fin_id+"'}").upsert().multi().with("{$set: {'expense.kid_expense':"+kid_expense+"}}}");
				MongoDBConnection.finplancol.update("{usr_id:#,plan_name:#}", user_id, plan_name)
				.with("{$set: {'expense.college_expense':" + college_expense + "}}");



				JSONArray goalJsonArray = userJson.getJSONArray("goals");
				JSONArray goalsArray = new JSONArray();
				for (int j = 0; j < goalJsonArray.length(); j++) {
					String goalId = (String) goalJsonArray.get(j);
					if (!goalId.equals(goal_id)) {
						goalsArray.put(goalId);
					} else {
						continue;
					}
				}


				//System.out.println("goals array........."+goalsArray+"goal id..."+goal_id);
				MongoDBConnection.finplancol.update("{'_id':#}", fin_id).upsert().multi().with("{$set: {'goals':"+goalsArray+"}}");
				MongoDBConnection.goalcoll.remove("{_id:#}", goal_id);
				responseToRestController.put("status", "success");

			}
			else
			{
				responseToRestController.put("status", "fail");
			}




		}
		catch(Exception e){
			e.printStackTrace();
		}
		return responseToRestController;
	}

	public static JSONObject limiteAfterCollegeGoal(int userAge,double userIncome,double spouseIncome,String marritalStatus,int spouseAge,JSONArray limits,double AGI,int i,JSONObject collegeAmount,JSONObject deductions) throws JSONException
	{
		JSONObject response=new JSONObject();
		try
		{
			//System.out.println("Mahi    : userAge   :"+userAge);
			//System.out.println("Mahi    : marritalStatus   :"+marritalStatus);
			//System.out.println("Mahi    : userIncome   :"+userIncome);
			//System.out.println("Mahi    : spouseIncome   :"+spouseIncome);
			if(marritalStatus.equals("qualifying widow") || marritalStatus.equals("Married Filing Jointly"))
			{
				if(collegeAmount.getDouble("currentAmount")!=0)
				{
					double newAmount=0;
					if(collegeAmount.getString("type").equals("create"))
					{
						if(collegeAmount.getDouble("currentAmount")*.80>14000 && collegeAmount.getDouble("currentAmount")*.80<=28000)
						{
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")+14000;
							limits.getJSONObject(i).put("user529Plan", newAmount);
							newAmount=limits.getJSONObject(i).getDouble("spouse529Plan")+collegeAmount.getDouble("currentAmount")*.80-14000;
							limits.getJSONObject(i).put("spouse529Plan", newAmount);
							deductions.put("collegeGoalNontaxable",deductions.getDouble("collegeGoalNontaxable")+(collegeAmount.getDouble("currentAmount")*.80));
							for(int j=0;j<i;j++)
							{
								limits.getJSONObject(j).put("user529Plan",limits.getJSONObject(j).getDouble("user529Plan")+14000);
								limits.getJSONObject(j).put("spouse529Plan",limits.getJSONObject(j).getDouble("spouse529Plan")+collegeAmount.getDouble("currentAmount")*.80-14000);
							}
						}
						else if( collegeAmount.getDouble("currentAmount")*.80>28000)
						{
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")+14000;
							limits.getJSONObject(i).put("user529Plan", newAmount);
							newAmount=limits.getJSONObject(i).getDouble("spouse529Plan")+14000;
							limits.getJSONObject(i).put("spouse529Plan", newAmount);
							newAmount=deductions.getDouble("collegeGoalTaxable")+collegeAmount.getDouble("currentAmount")*.80-28000;
							deductions.put("collegeGoalTaxable", newAmount);
							deductions.put("collegeGoalNontaxable",deductions.getDouble("collegeGoalNontaxable")+(28000));
							for(int j=0;j<i;j++)
							{
								limits.getJSONObject(j).put("user529Plan",limits.getJSONObject(j).getDouble("user529Plan")+14000);
								limits.getJSONObject(j).put("spouse529Plan",limits.getJSONObject(j).getDouble("spouse529Plan")+14000);
							}
						}
						else{
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")+collegeAmount.getDouble("currentAmount")*.80;
							limits.getJSONObject(i).put("user529Plan", newAmount);
							deductions.put("collegeGoalNontaxable",deductions.getDouble("collegeGoalNontaxable")+(collegeAmount.getDouble("currentAmount")*.80));
							for(int j=0;j<i;j++)
							{
								limits.getJSONObject(j).put("user529Plan",limits.getJSONObject(j).getDouble("user529Plan")+(collegeAmount.getDouble("currentAmount")*.80));
							}
						}
						/*newAmount=limits.getDouble("spouse529Plan")+collegeAmount.getDouble("currentAmount")*.80;
							limits.put("spouse529Plan", newAmount);*/
					}
					else if(collegeAmount.getString("type").equals("update"))
					{
						if(collegeAmount.getDouble("oldAmount")*.80>14000)
						{
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")-14000;
							limits.getJSONObject(i).put("user529Plan", newAmount);
							newAmount=limits.getJSONObject(i).getDouble("spouse529Plan")-collegeAmount.getDouble("oldAmount")*.80+14000;
							limits.getJSONObject(i).put("spouse529Plan", newAmount);
						}
						else{
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")-collegeAmount.getDouble("oldAmount")*.80;
							limits.getJSONObject(i).put("user529Plan", newAmount);
						}
						if(collegeAmount.getDouble("currentAmount")*.80>14000)
						{
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")+14000;
							limits.getJSONObject(i).put("user529Plan", newAmount);
							newAmount=limits.getJSONObject(i).getDouble("spouse529Plan")+collegeAmount.getDouble("currentAmount")*.80-14000;
							limits.getJSONObject(i).put("spouse529Plan", newAmount);
						}
						else{
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")+collegeAmount.getDouble("currentAmount")*.80;
							limits.getJSONObject(i).put("user529Plan", newAmount);
							for(int j=0;j<i;j++)
							{
								limits.getJSONObject(j).put("user529Plan",limits.getJSONObject(j).getDouble("user529Plan")+(collegeAmount.getDouble("currentAmount")*.80));
							}
						}
					}
					else if(collegeAmount.getString("type").equals("delete"))
					{
						if(collegeAmount.getDouble("oldAmount")*.80>14000 && collegeAmount.getDouble("oldAmount")*.80<=28000)
						{
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")-14000;
							limits.getJSONObject(i).put("user529Plan", newAmount);
							newAmount=limits.getJSONObject(i).getDouble("spouse529Plan")-collegeAmount.getDouble("oldAmount")*.80+14000;
							limits.getJSONObject(i).put("spouse529Plan", newAmount);
							deductions.put("collegeGoalNontaxable",deductions.getDouble("collegeGoalNontaxable")-(collegeAmount.getDouble("oldAmount")*.80));
							for(int j=0;j<i;j++)
							{
								limits.getJSONObject(j).put("user529Plan",limits.getJSONObject(j).getDouble("user529Plan")-14000);
								limits.getJSONObject(j).put("spouse529Plan",limits.getJSONObject(j).getDouble("spouse529Plan")-collegeAmount.getDouble("oldAmount")*.80+14000);
							}
						}
						else if( collegeAmount.getDouble("oldAmount")*.80>28000)
						{
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")-14000;
							limits.getJSONObject(i).put("user529Plan", newAmount);
							newAmount=limits.getJSONObject(i).getDouble("spouse529Plan")-14000;
							limits.getJSONObject(i).put("spouse529Plan", newAmount);
							newAmount=deductions.getDouble("collegeGoalTaxable")-collegeAmount.getDouble("oldAmount")*.80+28000;
							deductions.put("collegeGoalTaxable", newAmount);
							deductions.put("collegeGoalNontaxable",deductions.getDouble("collegeGoalNontaxable")-28000);
							for(int j=0;j<i;j++)
							{
								limits.getJSONObject(j).put("user529Plan",limits.getJSONObject(j).getDouble("user529Plan")-14000);
								limits.getJSONObject(j).put("spouse529Plan",limits.getJSONObject(j).getDouble("spouse529Plan")-14000);
							}
						}
						else{
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")-collegeAmount.getDouble("oldAmount")*.80;
							limits.getJSONObject(i).put("user529Plan", newAmount);
							deductions.put("collegeGoalNontaxable",deductions.getDouble("collegeGoalNontaxable")-(collegeAmount.getDouble("oldAmount")*.80));
							for(int j=0;j<i;j++)
							{
								limits.getJSONObject(j).put("user529Plan",limits.getJSONObject(j).getDouble("user529Plan")-(collegeAmount.getDouble("oldAmount")*.80));
							}
						}
					}
				}
			}
			else// user is not Marriage
			{
				if(collegeAmount.getDouble("currentAmount")!=0)
				{
					double newAmount=0;
					System.out.println("typeeeee "+collegeAmount.getString("type"));
					if(collegeAmount.getString("type").equals("create"))
					{
						System.out.println("hdjhfjdfh  "+(collegeAmount.getDouble("currentAmount")));
						if(collegeAmount.getDouble("currentAmount")*.80<14000 )
						{
							System.out.println("in if ");
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")+collegeAmount.getDouble("currentAmount")*.80;
							System.out.println("newAmount  ?? "+newAmount);
							limits.getJSONObject(i).put("user529Plan", newAmount);
							deductions.put("collegeGoalNontaxable",deductions.getDouble("collegeGoalNontaxable")+(collegeAmount.getDouble("currentAmount")*.80));
							for(int j=0;j<i;j++)
							{
								limits.getJSONObject(j).put("user529Plan",limits.getJSONObject(j).getDouble("user529Plan")+collegeAmount.getDouble("currentAmount")*.80);
							}
						}
						else{
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")+14000;
							limits.getJSONObject(i).put("user529Plan", newAmount);
							if(collegeAmount.getDouble("oldAmount")>0)
							{
								newAmount=deductions.getDouble("collegeGoalTaxable")+collegeAmount.getDouble("oldAmount")*.80-14000;
							}
							else{
								newAmount=deductions.getDouble("collegeGoalTaxable")+collegeAmount.getDouble("currentAmount")*.80-14000;

							}
							deductions.put("collegeGoalTaxable", newAmount);
							deductions.put("collegeGoalNontaxable",deductions.getDouble("collegeGoalNontaxable")+14000);
							for(int j=0;j<i;j++)
							{
								limits.getJSONObject(j).put("user529Plan",limits.getJSONObject(j).getDouble("user529Plan")+14000);
							}
						}
					}
					else if(collegeAmount.getString("type").equals("update"))
					{
						//System.out.println("in update?>>> "+limits.getJSONObject(i).getDouble("user529Plan")+"   "+ (collegeAmount.getDouble("oldAmount")*.80) +" " +(collegeAmount.getDouble("currentAmount")*.80));
						newAmount=(limits.getJSONObject(i).getDouble("user529Plan")-(collegeAmount.getDouble("oldAmount")*.80))+(collegeAmount.getDouble("currentAmount")*.80);
						//System.out.println("newAmount "+newAmount);

						limits.getJSONObject(i).put("user529Plan", newAmount);
					}
					else if(collegeAmount.getString("type").equals("delete"))
					{
						System.out.println("in delete new limits ");
						if(collegeAmount.getDouble("oldAmount")*.80<14000 )
						{
							System.out.println("amount  ?? "+collegeAmount.getDouble("oldAmount"));
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")-collegeAmount.getDouble("oldAmount")*.80;
							System.out.println("newAmount >> "+newAmount );
							limits.getJSONObject(i).put("user529Plan", newAmount);
							deductions.put("collegeGoalNontaxable",deductions.getDouble("collegeGoalNontaxable")-(collegeAmount.getDouble("oldAmount")*.80));
							for(int j=0;j<i;j++)
							{
								limits.getJSONObject(j).put("user529Plan",limits.getJSONObject(j).getDouble("user529Plan")-collegeAmount.getDouble("oldAmount")*.80);
							}
						}
						else{
							newAmount=limits.getJSONObject(i).getDouble("user529Plan")-14000;
							limits.getJSONObject(i).put("user529Plan", newAmount);
							newAmount=deductions.getDouble("collegeGoalTaxable")-collegeAmount.getDouble("oldAmount")*.80+14000;
							deductions.put("collegeGoalTaxable", newAmount);
							deductions.put("collegeGoalNontaxable",deductions.getDouble("collegeGoalNontaxable")-14000);
							for(int j=0;j<i;j++)
							{
								limits.getJSONObject(j).put("user529Plan",limits.getJSONObject(j).getDouble("user529Plan")-14000);
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{

			e.printStackTrace();
		}

		return response.put("limits", limits).put("deductions", deductions);
	}







	@Override
	public JSONObject getPlan529Amount(GoalCollegeEducation goalCollegeEducation)
	{
		JSONObject resposneToRestController=new JSONObject();
		try{
			resposneToRestController.put("status","fail");
			FinPlan getfinplan = MongoDBConnection.finplancol.findOne("{usr_id:#,plan_name:#}",goalCollegeEducation.getUser_id(),goalCollegeEducation.getPlan_name()).as(FinPlan.class);
			JSONObject finPlanJson=new JSONObject(jsonMapper.writeValueAsString(getfinplan));
			JSONArray assestsJSON=finPlanJson.getJSONArray("assests");
			double totalPlan529Amount=0;
			for(int i=0;assestsJSON.getJSONObject(i).getInt("year")<=goalCollegeEducation.getKidCollegeYear();i++)
			{
				if(assestsJSON.getJSONObject(i).getInt("year")==goalCollegeEducation.getKidCollegeYear())
				{
					totalPlan529Amount=totalPlan529Amount+assestsJSON.getJSONObject(i).getDouble("user529Plan");
					totalPlan529Amount=totalPlan529Amount+assestsJSON.getJSONObject(i).getDouble("spouse529Plan");
				}
			}
			resposneToRestController.put("plan529",totalPlan529Amount).put("status","success");
			//System.out.println("resposneToRestController==="+resposneToRestController);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resposneToRestController;
	}








}