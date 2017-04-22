package com.mongorest.olahtek;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.mongodb.DB;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

@Controller
public class MongoRestController {

	public static Properties serviceProp;
	public static MongoClient mongoClient; 
	public static MongoDatabase mongoDb;
	public static Jongo jongo;
	public static String mongoDBName;
	public static DB db;
	public static JSONObject cm;
	public static int numConnection=0;
	String usersCollection;
	String counterCollection;
	@JsonCreator 
	public MongoRestController() {
		try {
			serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home + "/." + propFile));
				serviceProp.load(inputStream);
				inputStream.close();
				System.out.println("Application is reading configuration from " + home);
			} else {
				System.out.println("Application is reading configuration from resources");
			}
			//String mongoIP = (String) serviceProp.get("mongoDBHost");
			//String mongoPort = (String) serviceProp.get("mongoDBPort");
			usersCollection= (String) serviceProp.get("userscollection");
			counterCollection=(String) serviceProp.get("counterscollection");			
			mongoDBName = (String) serviceProp.get("mongoDBName");
			/*mongoClient = new MongoClient(mongoIP, Integer.parseInt(mongoPort));
			db = mongoClient.getDB(mongoDBName);
			jongo = new Jongo(db, new JacksonMapper.Builder().build());*/
			
		} catch (Exception e) {
			e.printStackTrace();
			mongoClient.close();
		}
	}

	@RequestMapping(value ="/Login/{email}/{password}", method = RequestMethod.GET )
	public String check(@PathVariable String email,@PathVariable String password,ModelMap model) throws FileNotFoundException {
		System.out.println("---REQUEST--- LOGIN VALIDATION- started");
		//boolean flag=false;
		try{
			MongoCollection testcol = jongo.getCollection(usersCollection);
			Login login =  testcol.findOne("{email:#}",email).as(Login.class);
		
		    System.out.println(login+"============");
			if(login!=null)
			{		
				String pwd=login.getPassword();
				
				if(pwd.equals(password))
				{   
					String user_id=login.getUser_id();
					model.addAttribute("data","success");
					model.addAttribute("user_id",user_id);
					return "jsonTemplate";
				}
				else
				{
					model.addAttribute("data","incorrect_password");
					return "jsonTemplate";
				}
			}
			else
			{
				model.addAttribute("data","user not available");
				
				return "jsonTemplate";
			}
			
		
	
	}catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("invalid user");
		return "jsonTemplate";
	}
	}

	//For User Registration
	@RequestMapping(value = "/Register/{type}/{user_id}/{var1}/{var2}/{var3}/{var4}/{var5}/{var6}", method = RequestMethod.GET)
	public String nregister(@PathVariable String var1,@PathVariable String var2,@PathVariable String var3,@PathVariable String var4,@PathVariable String var5,@PathVariable String var6,@PathVariable String type, @PathVariable String user_id ,ModelMap model) throws FileNotFoundException {
					System.out.println("Registration in progress......");
					MongoCollection testcol = jongo.getCollection(usersCollection);
					MongoCollection counters = jongo.getCollection(counterCollection);
					
				    	model.addAttribute("data","success");
					try{
						if(type.equals("UserForm"))
						{
						//Searching the user id from counters collections
						Register register=counters.findOne().as(Register.class);
						Register checkemail =  testcol.findOne("{email:#}",var2).as(Register.class);
						if(checkemail!=null)
						{
						model.addAttribute("data","registeredEmail");
						return "jsonTemplate";
						}
						
						
						String userid="usr"+register.getUser_id();
						//Inserting the data in testcol collection
						BasicDBObject doc = new BasicDBObject("_id", userid).append("name", var1).append("email", var2).append("password", var3);
						testcol.insert(doc);
						//update the counters collection "user_id" value
						counters.update("{'user_id':"+register.getUser_id()+"}").with("{$inc: {user_id: 1}}");
						model.addAttribute("userid",userid);
						return "jsonTemplate";
						}
						
						//personal details
						else if(type.equals("PersonalDetails"))
						{
							
							
							if(var4.equals("yes"))
							{
								testcol.update("{'_id': '"+user_id+"'}").upsert().multi().with("{$set: {'spouseName':'"+ var1+"','SpouseAge':'"+var2+"'}}");	
								
								int child=2;
								if(child>0)
								{
									int var=Integer.parseInt(var2);
									String s=null;
									for(int i=2;i<=child;i++)
									{
										System.out.println(var1+var2+var3+var4);
										 s="{'name':'"+ var1+"','age':"+var+"}";
									}
									s=s+",{'name':'"+ "jhb"+"','age':"+"2"+"}";
									testcol.update("{'_id': 110}").upsert().multi().with("{$set: {'child':["+s+"]}}");
									
								}
								testcol.update("{'_id': '"+user_id+"'}").upsert().multi().with("{$set: {'city':'"+ var1+"','state':'"+var2+"','age':'"+var3+"','marital_status':'"+ var4+"'}}");	
								return "jsonTemplate";
								
							}
							else
							{
								testcol.update("{'_id': '"+user_id+"'}").upsert().multi().with("{$set: {'city':'"+ var1+"','state':'"+var2+"','age':'"+var3+"','marital_status':'"+ var4+"'}}");	
								return "jsonTemplate";
							}
						}
						//Income and Expenses
						else if(type.equals("IncomeAndExpenses"))
						{
							testcol.update("{'_id': '"+ user_id+"'}").upsert().multi().with("{$set: {'beforetaxincome':'"+ var1+"','Monthly_expenses':'"+var2+"','Rental_expenses':'"+var3+"','Other_expenses':'"+var4+"'}}");
							
							return "jsonTemplate";	
						}else if(type.equals("Assets"))
						{
							System.out.println("=================");
							Register available =testcol.findOne("{_id:#}","usr100191").as(Register.class);
							System.out.println(available);
							if(available!=null)
							{
							if(available.getCity()==null)
							{
								model.addAttribute("data","PersonalDetails");
								return "jsonTemplate";
							}
							if(available.getBeforetaxincome()==null)
							{model.addAttribute("data","IncomeAndExpenses");
								return "jsonTemplate";
							}
							}

							testcol.update("{'_id': '"+user_id+"'}").upsert().multi().with("{$set: {'Cash':'"+ var1+"','Taxable_investment':'"+var2+"','Non_Taxable_investment':'"+var3+"'}}");
							
							return "jsonTemplate";
							
						}
						else
						{
							model.addAttribute("data","Fail");
							System.out.println("Form is not valid");
							return "jsonTemplate";	
						}
							
				
				}catch(Exception e)
				{
					model.addAttribute("data","Fail");
					return "jsonTemplate";
				}	
	}

	
	//UserDetails
	@RequestMapping(value ="/UserDetails/{email}/{password}", method = RequestMethod.GET )
	public String userDetails(@PathVariable String email,@PathVariable String password,ModelMap model) throws FileNotFoundException {
		System.out.println("---REQUEST--- LOGIN VALIDATION- started");
		//boolean flag=false;
		try{
			MongoCollection testcol = jongo.getCollection(usersCollection);
			Login login =  testcol.findOne("{email:#}",email).as(Login.class);
		
		    System.out.println(login+"============");
			if(login!=null)
			{		
				String pwd=login.getPassword();
				
				if(pwd.equals(password))
				{   
					String user_id=login.getUser_id();
					model.addAttribute("data","success");
					model.addAttribute("user_id",user_id);
					return "jsonTemplate";
				}
				else
				{
					model.addAttribute("data","incorrect_password");
					return "jsonTemplate";
				}
			}
			else
			{
				model.addAttribute("data","user not available");
				
				return "jsonTemplate";
			}
			
		
	
	}catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("invalid user");
		return "jsonTemplate";
	}
	}
	

}
