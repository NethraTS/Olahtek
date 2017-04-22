<!DOCTYPE html>
<html lang="en" ng-app="formApp">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Balagopalan">
<title>WEALTHSETTER</title>
<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<!-- Custom Fonts -->
<link rel="stylesheet" href="css/fonts.googleapis.css" type="text/css">
<link rel="stylesheet" href="css/fonts.googleapis1.css" type="text/css">
<!--   ------------------ this is used for load graphs from google -------------
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
	------------------------------------------------------------------------>
<script src="js/jquery.min.js"></script>
<!-- LOAD ANGULAR -->
<script src="js/angular.min.js"></script>
<script src="js/app.js"></script>

<script>
var app = angular.module('formApp',[]);
var url = window.location.href;
var hashes = url.split("=")[1];
app.directive('allowPattern', [allowPatternDirective]);

function allowPatternDirective() {
    return {
        restrict: "A",
        compile: function(tElement, tAttrs) {
            return function(scope, element, attrs) {
        // I handle key events
                element.bind("keypress", function(event) {
                    var keyCode = event.which || event.keyCode; // I safely get the keyCode pressed from the event.
                    var keyCodeChar = String.fromCharCode(keyCode); // I determine the char from the keyCode.
          
          // If the keyCode char does not match the allowed Regex Pattern, then don't allow the input into the field.
          			////alert(keyCodeChar.charCodeAt());
          			if(keyCodeChar.charCodeAt()==8||keyCodeChar.charCodeAt()==9)
              			{
                            return true;
              			}
          				
                    if (!keyCodeChar.match(new RegExp(attrs.allowPattern, "i"))) {
            event.preventDefault();
                        return false;
                    }
          
                });
            };
        }
    };
}

function isNumberKey(evt, obj) {

    var charCode = (evt.which) ? evt.which : event.keyCode
    var value = obj.value;
    var dotcontains = value.indexOf(".") != -1;
    if (dotcontains)
        if (charCode == 46) return false;
    if (charCode == 46) return true;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}

app.controller('GoalPlane',function($scope,$http){
	
           $scope.formdata={};
           $scope.planName="";
           $scope.collegeData={};
           $scope.sessionDetails={};
      	 $scope.sessionDelete={};
      	$scope.formdata.PercentageCollegeCost =0;
      	$scope.formdata.PercentageCollegeCost ="0";
      	$scope.masked = false;
      	$scope.showDataTables=true;
      	//$scope.formdata.RealAnnualCollegeCostOverTime=17000;
$scope.collegeCostToServlet=[];      	
            $scope.items = ['2016','2017','2018','2019','2020','2021','2022','2023','2024','2025','2026','2027','2028','2029','2030','2031','2032','2033','2034','2035','2036','2037','2038','2039','2040','2041','2042','2043','2044','2045','2046','2047','2048','2049','2050','2051','2052','2053','2054','2055','2056'];
          
         //  alert("oppp"+  $scope.items)
            $scope.CollegeEducationAmountPercentage=['100','95','90','85','80','75','70','65','60','55','50','45','40','35','30','25','20','15','10','5','0'];
            
            
           $scope.CurrentDate = new Date().getFullYear();
           $scope.kidArrays=[];
         //  $scope.DateDiff=
          // $scope.CollegeType=['2-Years-Public','out-of-state public college','private college'];
                $scope.CollegeType = [{name:'2-Years-Public',value:'1,07,010'},{name:'4-Years-Public-in-state',value:'1,69,509'},{name:'4-Years-Public-out-of-state',value:'2,10,677'},{name:'4-Years-Private',value:'2,10,677'},{name:'4-Years-Private-new-England-area',value:'2,10,677'},{name:'2-Years-Public-transferred-to-4-Years-Public-in-state',value:'1,07,010'},{name:'2-Years-Public-transferred-to-4-Years-Public-out-of-state',value:'1,07,010'},{name:'2-Years-Public-transferred-to-4-Years-Private',value:'1,07,010'},{name:'2-Years-Public-transferred-to-4-Years-Private-new-England-area',value:'1,07,010'}];
           //$scope.formdata.KidCollegeYear="";
           
           
           
           $scope.goalHouseEditData={};
           $scope.states = [ {name:'ALABAMA'},{name:'ALASKA'},{name:'ARIZONA'},{name:'ARKANSAS'},{name:'CALIFORNIA'},{name:'COLORADO'},{name:'CONNECTICUT'},{name:'DELAWARE'},{name:'FLORIDA'},{name:'GEORGIA'},{name:'HAWAII'},{name:'IDAHO'},{name:'ILLINOIS'},{name:'INDIANA'},{name:'IOWA'},{name:'KANSAS'},{name:'KENTUCKY'},{name:'LOUISIANA'},{name:'MAINE'},{name:'MARYLAND'},{name:'MASSACHUSETTS'},{name:'MICHIGAN'},{name:'MINNESOTA'},{name:'MISSISSIPPI'},{name:'MISSOURI'},{name:'MONTANA'},{name:'NEBRASKA'},{name:'NEVADA'},{name:'NEW HAMPSHIRE'},{name:'NEW JERSEY'},{name:'NEW MEXICO'},{name:'NEW YORK'},{name:'NORTH CAROLINA'},{name:'NORTH DAKOTA'},{name:'OHIO'},{name:'OHIOOKLAHOMA'},{name:'OREGON'},{name:'OREGONPENNSYLVANIA'},{name:'RHODE ISLAND'},{name:'OUTH CAROLINA'},{name:'SOUTH DAKOTA'},{name:'TENNESSEE'},{name:'TEXAS'},{name:'UTAH'},{name:'VERMONT'},{name:'VIRGINIA'},{name:'WASHINGTON'},{name:'WEST VIRGINIA'},{name:'WISCONSIN'},{name:'WYOMING'},{name:'WASHINGTON'}];
			$scope.loans = [ {name:'30-year fixed'},{name:'15-year fixed'},{name:'5/1 ARM'}];
			$scope.yes1=function()
            {
           	 $scope.plan529Yes=false;
           		$scope.plan529No=true;
           		$scope.formdata.plan529="false";
                }
   $scope.no1=function()
   {
   	$scope.msgerr="";
   	$scope.plan529Yes=true;
   	$scope.plan529No=false;
   	$scope.formdata.plan529="true";
   	}      
   $scope.hideWarning=function()
	  {
	 	 $("#fail-warning").hide();
	 	
	 	 
	  }
 $scope.load1= function() {
	 $scope.masked = true;
	 $scope.formdata.goal_id=(decodeURIComponent(hashes));
	 $("#success-alert").hide();
	 $("#fail-alert").hide(); 
	  $scope.formdata.actionHomeType="edit";       		
        			$http({
        			    method: 'POST',
        			    url: 'GoalCollegeEducation',
        			    data: $.param($scope.formdata),
        			    headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
        			}).then(function(result) {
        				//alert(JSON.stringify(result.data))
        				$scope.planName=result.data.plan_name;
        			
        			/* 	$scope.kidArrays=result.data.childArray;
						$scope.formdata.Goalname=$scope.kidArrays[0]; */
        			$scope.formdata.Goalname=result.data.goal_name; 
        			/* 	 alert("67 "+result.data.goal_name)
        				 alert("34"+result.data.planName) */
        				if(result.data.goalFeasiblity==false)
        					{
						 
				       $("#fail-warning").show();
        				}
        				$scope.goalHouseEditData=result.data;
        				//$scope.items=$scope.goalHouseEditData.kidCollegeYear;
        				$scope.formdata.KidCollegeYear=$scope.goalHouseEditData.kidCollegeYear;
        				//alert($scope.formdata.KidCollegeYear+".....sdsdd");
        				$scope.costArray=result.data.costArray;
        				$scope.formdata.CollegeEducationAmount=$scope.goalHouseEditData.collegeEducationAmount*1;
        				//$scope.formdata.RealAnnualCollegeCostOverTime = $scope.formdata.CollegeEducationAmount*1;
        		          $scope.formdata.RealAnnualCollegeCostOverTime =$scope.costArray[0].cost.toFixed(2);
        				//alert($scope.formdata.RealAnnualCollegeCostOverTime);
        			//$scope.formdata.CollegeEducationAmountPercentage=$scope.goalHouseEditData.collegeEducationAmountPercentage;
 					    $scope.formdata.CollegeType=$scope.goalHouseEditData.collegeType;
        			$scope.formdata.totalPlan529=result.data.plan529;	
 						 $scope.ResultforCollegeEducation = result.data.collegeCosts;
 						 $scope.totalCollegeCost=$scope.goalHouseEditData.totalCollegeCost;
        				// alert(JSON.stringify($scope.ResultforCollegeEducation));
        				// alert($scope.goalHouseEditData.collegeType);
        				 //alert(JSON.stringify(result.data.costArray));
        				 if($scope.goalHouseEditData.collegeType=="2-Years-Public"||$scope.goalHouseEditData.collegeType=="4-Years-Public-in-state"||$scope.goalHouseEditData.collegeType=="4-Years-Public-out-of-state"||$scope.goalHouseEditData.collegeType=="4-Years-Private"||$scope.goalHouseEditData.collegeType=="4-Years-Private-new-England-area")
        					 {
        					 $scope.showDataTables=false;
        					 $scope.showDataForTransfered=true;
        				 for(var i=0;i<$scope.ResultforCollegeEducation.length;i++)
        					 {
        					 if($scope.ResultforCollegeEducation[i].type==$scope.goalHouseEditData.collegeType)
        					 {
        						 $scope.collegeData=$scope.ResultforCollegeEducation[i];
        						 $scope.TuitionAndFees= $scope.collegeData.TuitionAndFees;
        						 $scope.RoomAndBoard=$scope.collegeData.RoomAndBoard;
        						 $scope.BooksAndSupplies=$scope.collegeData.BooksAndSupplies;
        						 $scope.Transportation=$scope.collegeData.Transportation;
        						 $scope.OtherExpenses=$scope.collegeData.OtherExpenses;
        						 
        					 }
        					 }
        			}
        				 else{
        					 $scope.showDataTables=true;
        					 $scope.showDataForTransfered=false;

        				 }
        	/* 			 for(var j=0;j<	$scope.costArray.length;j++)
        					 { */
        					 $scope.year1cost=$scope.costArray[0].cost;
        					 $scope.year2cost=$scope.costArray[1].cost;
        					 $scope.year3cost=$scope.costArray[2].cost;
        					 $scope.year4cost=$scope.costArray[3].cost;
        					 //alert($scope.year1 +" "+$scope.year2);
/*         					 }
 */        				$scope.collegeCostToServlet=$scope.costArray;
 							if(result.data.plan529Saved==0)
        					{
        					$scope.formdata.plan529demo=true;
        					$scope.plan529Yes=true;
        					}
        				else
        					{
        					$scope.formdata.plan529demo=false;
        					$scope.formdata.plan529Saving=result.data.plan529Saved;
        					}        				
        				
        				$scope.expenseResult=result.data.collegeExpense;
        				$scope.kidCollegeYear=result.data.kidCollegeYear;
        				
        				//$scope.PayitemsPersent=result.data.PayitemsPersent;
        				
        	 			$scope.collegeType=result.data.collegeType;
        	 			$scope.Goalname=result.data.Goalname;
        	 			$scope.collegeEducationAmount=result.data.collegeExpenseAfterDeduction;
        	 			$scope.formdata.CollegeEducationAmountPercentage=result.data.collegeEducationAmountPercentage;
        				$scope.Percent=result.data.collegeEducationAmountPercentage;
        				$scope.formdata.plan529Saving=result.data.plan529Saved;
        				$scope.formdata.PercentageCollegeCost=$scope.goalHouseEditData.PercentageCollegeCost;
        				//alert($scope.Goalname);
        				//alert($scope.formdata.CollegeType);
        				//alert($scope.formdata.CollegeEducationAmount);
        				//alert($scope.goalHouseEditData.collegeExpense);
        				$scope.errorName='';
        				$scope.errorSuperhero='';
        				$scope.masked = false;
        			   }, function(error) {
        				   $scope.message=result.data;
        				   $scope.errorName='';
        					$scope.errorSuperhero='';
        			   });
        			 $scope.msgerr="";
        			 
        			 
        			/*   $scope.ResultforCollegeEducation   = [
        										            {
        										                "id" : 6 ,
        										                "type":"2-Years-Public-transferred-to-4-Years-Public-in-state",
        										                "first_year_cost" :  17000,
        										                "second_year_cost": 17000,
        										                "third_year_cost" :24610,
        										                "fourth_year_cost":24610,
        										                "Total_four_years_cost":83220
        										            },
        										            {
        										            	"id" : 7 ,
        										                "type":"2-Years-Public-transferred-to-4-Years-Public-out-of-state",
        										                "first_year_cost" :  17000,
        										                "second_year_cost": 17000,
        										                "third_year_cost" :39890,
        										                "fourth_year_cost":39890,
        										                "Total_four_years_cost":113780
        										            },
        										      
        										            {
        										                "id" : 8,
        										                "type":"2-Years-Public-transferred-to-4-Years-Private",
        										                "first_year_cost" :  17000,
        										                "second_year_cost": 17000,
        										                "third_year_cost" :49320,
        										                "fourth_year_cost":49320,
        										                "Total_four_years_cost":49320
        										            },
        										           
        										            {
        										            	"id" : 9 ,
        										                "type":"2-Years-Public-transferred-to-4-Years-Private-new-England-area",
        										                "first_year_cost" :  17000,
        										                "second_year_cost": 17000,
        										                "third_year_cost" :60280,
        										                "fourth_year_cost":60280,
        										                "Total_four_years_cost":49320
        										            }
        										            
        										        ]  */
        			 

        		} 
 
 
 
 
	$scope.report = function() {
		

		if ($scope.Count == 0) {
			$scope.SuccessMessage1 = "Currently there are no plans to show the reports";
			window.location.href = "#";
			$("#report-alert").show();
			$("#report-alert").fadeTo(2000, 300).slideUp(400,
					function() {
						$("#report-alert").hide();

					});

		} else {
			window.location.href = "Report.jsp";
		}
	}

 $scope.values=function(){
	 
	 $scope.formdata.CollegeType=$scope.formdata.CollegeType;
/*  	for(k=0;k<$scope.ResultforCollegeEducation.length;k++){
 	  if($scope.formdata.CollegeType == $scope.ResultforCollegeEducation[k].type){
 		  $scope.formdata.CollegeEducationAmount= Math.round($scope.ResultforCollegeEducation[k].Total); 
 	  }
 	 
 	}
 	for(i=0;i<$scope.ResultforCollegeEducation.length;i++){
		if($scope.formdata.CollegeType == $scope.ResultforCollegeEducation[i].type){
			 if($scope.formdata.PercentageCollegeCost==0){
	         	   $scope.formdata.RealAnnualCollegeCostOverTime=Math.round($scope.ResultforCollegeEducation[i].Total)+Math.round($scope.ResultforCollegeEducation[i].Total * (Math.round($scope.formdata.PercentageCollegeCost)/100));
	                //alert(JSON.stringify($scope.formdata.RealAnnualCollegeCostOverTime))  
	            }
	            else{
	         	   $scope.formdata.RealAnnualCollegeCostOverTime=Math.round($scope.ResultforCollegeEducation[i].Total)+Math.round($scope.ResultforCollegeEducation[i].Total * (Math.round($scope.formdata.PercentageCollegeCost)/100));
	                //alert(JSON.stringify($scope.formdata.RealAnnualCollegeCostOverTime)) 
	         	   
	            }
}
} */
$scope.collegeCostToServlet=[];
for(i=$scope.formdata.KidCollegeYear*1;i<($scope.formdata.KidCollegeYear*1+4);i++)
{
$scope.obj={"year":i,"cost":0};
$scope.collegeCostToServlet.push($scope.obj);
}

if("2-Years-Public-transferred-to-4-Years-Private-new-England-area"==$scope.formdata.CollegeType)
{
for(i=0;i<$scope.collegeCostToServlet.length;i++)
{
	if(i<2)
	{
		for(k=0;k<$scope.ResultforCollegeEducation.length;k++)
			{
			if("2-Years-Public" == $scope.ResultforCollegeEducation[k].type){
        		  $scope.formdata.CollegeEducationAmount= $scope.ResultforCollegeEducation[k].Total; 
        	  }
			}
		$scope.collegeCostToServlet[i].cost=$scope.formdata.CollegeEducationAmount;
	}
	else
		{
		for(k=0;k<$scope.ResultforCollegeEducation.length;k++)
		{
		if("4-Years-Private-new-England-area" == $scope.ResultforCollegeEducation[k].type){
    		  $scope.formdata.CollegeEducationAmount= $scope.ResultforCollegeEducation[k].Total; 
    	  }
		}
	$scope.collegeCostToServlet[i].cost=$scope.formdata.CollegeEducationAmount;
		}
}		

}
else if("2-Years-Public-transferred-to-4-Years-Private"==$scope.formdata.CollegeType)
{

for(i=0;i<$scope.collegeCostToServlet.length;i++)
{
	if(i<2)
	{
		for(k=0;k<$scope.ResultforCollegeEducation.length;k++)
			{
			if("2-Years-Public" == $scope.ResultforCollegeEducation[k].type){
        		  $scope.formdata.CollegeEducationAmount= $scope.ResultforCollegeEducation[k].Total; 
        	  }
			}
		$scope.collegeCostToServlet[i].cost=$scope.formdata.CollegeEducationAmount;
	}
	else
		{
		for(k=0;k<$scope.ResultforCollegeEducation.length;k++)
		{
		if("4-Years-Private" == $scope.ResultforCollegeEducation[k].type){
    		  $scope.formdata.CollegeEducationAmount= $scope.ResultforCollegeEducation[k].Total; 
    	  }
		}
	$scope.collegeCostToServlet[i].cost=$scope.formdata.CollegeEducationAmount;
		}
}		



}
else if("2-Years-Public-transferred-to-4-Years-Public-out-of-state"==$scope.formdata.CollegeType)
{

for(i=0;i<$scope.collegeCostToServlet.length;i++)
{
	if(i<2)
	{
		for(k=0;k<$scope.ResultforCollegeEducation.length;k++)
			{
			if("2-Years-Public" == $scope.ResultforCollegeEducation[k].type){
        		  $scope.formdata.CollegeEducationAmount= $scope.ResultforCollegeEducation[k].Total; 
        	  }
			}
		$scope.collegeCostToServlet[i].cost=$scope.formdata.CollegeEducationAmount;
	}
	else
		{
		for(k=0;k<$scope.ResultforCollegeEducation.length;k++)
		{
		if("4-Years-Public-out-of-state" == $scope.ResultforCollegeEducation[k].type){
    		  $scope.formdata.CollegeEducationAmount= $scope.ResultforCollegeEducation[k].Total; 
    	  }
		}
	$scope.collegeCostToServlet[i].cost=$scope.formdata.CollegeEducationAmount;
		}
}	

}
else if("2-Years-Public-transferred-to-4-Years-Public-in-state"==$scope.formdata.CollegeType)
{
for(i=0;i<$scope.collegeCostToServlet.length;i++)
{
	if(i<2)
	{
		for(k=0;k<$scope.ResultforCollegeEducation.length;k++)
			{
			if("2-Years-Public" == $scope.ResultforCollegeEducation[k].type){
        		  $scope.formdata.CollegeEducationAmount= $scope.ResultforCollegeEducation[k].Total; 
        	  }
			}
		$scope.collegeCostToServlet[i].cost=$scope.formdata.CollegeEducationAmount;
	}
	else
		{
		for(k=0;k<$scope.ResultforCollegeEducation.length;k++)
		{
		if("4-Years-Public-in-state" == $scope.ResultforCollegeEducation[k].type){
    		  $scope.formdata.CollegeEducationAmount= $scope.ResultforCollegeEducation[k].Total; 
    	  }
		}
	$scope.collegeCostToServlet[i].cost=$scope.formdata.CollegeEducationAmount;
		}
}	
}
else
{


for(k=0;k<$scope.ResultforCollegeEducation.length;k++)
{
if($scope.formdata.CollegeType == $scope.ResultforCollegeEducation[k].type){
	  $scope.formdata.CollegeEducationAmount= $scope.ResultforCollegeEducation[k].Total;
}
}
	 for(i=0;i<$scope.collegeCostToServlet.length;i++)
{
	$scope.collegeCostToServlet[i].cost=$scope.formdata.CollegeEducationAmount;
} 

}


$scope.formdata.RealAnnualCollegeCostOverTime=$scope.collegeCostToServlet[0].cost;

 }
 $scope.ChangeCollegeCost = function(){
	 //alert();
/*  	for(i=0;i<$scope.ResultforCollegeEducation.length;i++){
		if($scope.formdata.CollegeType == $scope.ResultforCollegeEducation[i].type){
			 if($scope.formdata.PercentageCollegeCost==0){
	         	   $scope.formdata.RealAnnualCollegeCostOverTime=Math.round($scope.ResultforCollegeEducation[i].Total);
	                //alert(JSON.stringify($scope.formdata.RealAnnualCollegeCostOverTime))  
	            }
	            else{
	         	   $scope.formdata.RealAnnualCollegeCostOverTime=Math.round($scope.ResultforCollegeEducation[i].Total)+Math.round($scope.ResultforCollegeEducation[i].Total * (Math.round($scope.formdata.PercentageCollegeCost)/100));
	                //alert(JSON.stringify($scope.formdata.RealAnnualCollegeCostOverTime)) 
	         	   
	            }
}
} */
//alert($scope.collegeCostToServlet.length);
/* for(i=1;i<$scope.collegeCostToServlet.length;i++)
	{
		$scope.collegeCostToServlet[i].cost=($scope.collegeCostToServlet[i].cost*1+(($scope.collegeCostToServlet[i].cost*$scope.formdata.PercentageCollegeCost)/100));
	}
$scope.formdata.RealAnnualCollegeCostOverTime=$scope.collegeCostToServlet[0].cost;
 */ //alert(JSON.stringify($scope.collegeCostToServlet));
 }
 $scope.checkform1=function(){
	// window.location.href="#";
	$scope.masked = true;
	 if($scope.formdata.CollegeEducationAmount==null||$scope.formdata.CollegeEducationAmount==undefined||$scope.formdata.CollegeEducationAmount==""){
		 $scope.masked = false;   
		 window.location.href="#";
		          $scope.errmessage1="College amount is empty";
				       $("#fail-alert").show();  
				       					 }
	 
	 
	 else if($scope.formdata.RealAnnualCollegeCostOverTime==null||$scope.formdata.RealAnnualCollegeCostOverTime==undefined||$scope.formdata.RealAnnualCollegeCostOverTime==""){
   	
   		 $scope.masked = false;   
		 window.location.href="#";
		          $scope.errmessage1="Real Annual College Cost Over Time is empty";
				       $("#fail-alert").show();  
   	 }
 else if($scope.formdata.PercentageCollegeCost==null||$scope.formdata.PercentageCollegeCost==undefined){
	 //alert($scope.formdata.PercentageCollegeCost);
	 $scope.masked = false;   
	 window.location.href="#";
	          $scope.errmessage1="Percentage College Cost is empty";
			       $("#fail-alert").show();  
 
 }
	      else if($scope.formdata.CollegeEducationAmount==0){
	    	  $scope.masked = false;
	    	  window.location.href="#";
		       $scope.errmessage1="college education amount cannot be zero !!";
		       $("#fail-alert").show();  
		       					 }
	      else if($scope.formdata.plan529Saving==null && $scope.plan529Yes==false)
			{
		    	  $scope.masked = false;
		    	  window.location.href="#";
		    	  $scope.errmessage1="Contribute to  529 plan should not be empty !!";
		    	  $("#fail-alert").show();  
			}
	      /* else if($scope.formdata.plan529Saving>$scope.formdata.totalPlan529 && $scope.plan529Yes==false)
		{
	    	  $scope.masked = false;
	    	  window.location.href="#";
	    	  $scope.errmessage1="You don't  have sufficient amount to contribute to your 529 plan !!";
	    	  $("#fail-alert").show();  
		} */
	else if($scope.formdata.plan529Saving==0 && $scope.plan529Yes==false)
	{
		$scope.masked = false;
		window.location.href="#";
  	  	$scope.errmessage1="Contribution Amount to your 529 plan should not zero !!";
  	  	$("#fail-alert").show();  
	}
	else if($scope.formdata.plan529Saving>(4*$scope.formdata.CollegeEducationAmount))
		{
		$scope.masked = false;
		window.location.href="#";
  	  	$scope.errmessage1="Contribution Amount to your 529 plan should not greater than College Education Amount !!";
  	  	$("#fail-alert").show(); 
		}
		 else{
			 $scope.masked = true;
			 $scope.errmessage1="";
	 $scope.formdata.actionHomeType="update";
	// $scope.formdata.plan529demo=$scope.formdata.plan529;
	$scope.values();
	//alert($scope.formdata.PercentageCollegeCost);
	for(i=0;i<$scope.collegeCostToServlet.length;i++)
	{
		$scope.collegeCostToServlet[i].cost=((($scope.collegeCostToServlet[i].cost*Math.pow((1+$scope.formdata.PercentageCollegeCost/100),($scope.collegeCostToServlet[i].year-new Date().getFullYear())))));
		
	}
    $scope.formdata.RealAnnualCollegeCostOverTime=$scope.collegeCostToServlet[0].cost.toFixed(2);
 
	 $scope.formdata.plan529Saved=$scope.formdata.plan529Saving;
	 
	// alert(JSON.stringify($scope.collegeCostToServlet));
	 $scope.formdata.collegeCostToServlet=JSON.stringify($scope.collegeCostToServlet);

	 $scope.formdata.CollegeEducationAmount=$scope.formdata.CollegeEducationAmount*1;

	 
	 

	 //alert(JSON.stringify($scope.formdata));
	 $http({
	 		  method: 'POST',
	 		  url: 'GoalCollegeEducation',
	 		  data: $.param($scope.formdata),
	 		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
	 		}).then(function(result) {
	 			if(result.data.status=="success")
	 			{
	 			//$scope.planName=result.data.plan_name;
	 			$scope.goalHouseEditData=result.data;
	 			$scope.collegeEducationAmount=result.data.collegeExpense;
				$scope.kidCollegeYear=result.data.kidCollegeYear;
				$scope.PayitemsPersent=result.data.PayitemsPersent;
				$scope.collegeType=result.data.collegeType;
	 			$scope.Percent=result.data.collegeEducationAmountPercentage;
	 			$scope.Goalname=result.data.Goalname;
	 			$scope.ResultforCollegeEducation = result.data.collegeCosts;
				$scope.totalCollegeCost=$scope.goalHouseEditData.totalCollegeCost;
				// alert(JSON.stringify($scope.ResultforCollegeEducation));
				// alert($scope.goalHouseEditData.collegeType);
		/* 		 for(var i=0;i<$scope.ResultforCollegeEducation.length;i++)
					 {
					 if($scope.ResultforCollegeEducation[i].type==$scope.goalHouseEditData.collegeType)
					 {
						 $scope.collegeData=$scope.ResultforCollegeEducation[i];
						 $scope.TuitionAndFees= $scope.collegeData.TuitionAndFees;
						 $scope.RoomAndBoard=$scope.collegeData.RoomAndBoard;
						 $scope.BooksAndSupplies=$scope.collegeData.BooksAndSupplies;
						 $scope.Transportation=$scope.collegeData.Transportation;
						 $scope.OtherExpenses=$scope.collegeData.OtherExpenses;
						 
					 }
					 
					 } */
					 if($scope.goalHouseEditData.collegeType=="2-Years-Public"||$scope.goalHouseEditData.collegeType=="4-Years-Public-in-state"||$scope.goalHouseEditData.collegeType=="4-Years-Public-out-of-state"||$scope.goalHouseEditData.collegeType=="4-Years-Private"||$scope.goalHouseEditData.collegeType=="4-Years-Private-new-England-area")
					 {
					 $scope.showDataTables=false;
					 $scope.showDataForTransfered=true;
				 for(var i=0;i<$scope.ResultforCollegeEducation.length;i++)
					 {
					 if($scope.ResultforCollegeEducation[i].type==$scope.goalHouseEditData.collegeType)
					 {
						 $scope.collegeData=$scope.ResultforCollegeEducation[i];
						 $scope.TuitionAndFees= $scope.collegeData.TuitionAndFees;
						 $scope.RoomAndBoard=$scope.collegeData.RoomAndBoard;
						 $scope.BooksAndSupplies=$scope.collegeData.BooksAndSupplies;
						 $scope.Transportation=$scope.collegeData.Transportation;
						 $scope.OtherExpenses=$scope.collegeData.OtherExpenses;
						 
					 }
					 }
			}
				 else{
					 $scope.showDataTables=true;
					 $scope.showDataForTransfered=false;

				 }
						 $scope.costArray=result.data.costArray;
    					 $scope.year1cost=$scope.costArray[0].cost;
    					 $scope.year2cost=$scope.costArray[1].cost;
    					 $scope.year3cost=$scope.costArray[2].cost;
    					 $scope.year4cost=$scope.costArray[3].cost;
    					 //alert($scope.year1 +" "+$scope.year2);
					
	 		//$scope.goalHouseData=result.data;
	 		//---------Success modal alert------------------------------------------
			if(result.data.goalFeasiblity==false)
        					{
				$scope.masked = false;
				       $("#fail-warning").show();
        				}
	 		
	 		
	 			$scope.masked = false;
	 		$scope.errmessage=" Goal updated successfully";			
	$("#success-alert").show();
				$("#success-alert").fadeTo(2000, 300).slideUp(300, function(){
                	$("#success-alert").hide();
   
                });   
				//-----------------------------------------------------------------------
	 		console.log("message"+$scope.message);
	 		if(result.data.plan529Saved==0)
			{
	 			$scope.masked = false;
			$scope.formdata.plan529demo=true;
			$scope.plan529Yes=true;
			}
			else
			{
				$scope.masked = false;
			$scope.formdata.plan529demo=false;
			$scope.formdata.plan529Saving=result.data.plan529Saved;
			} 
	 			}
	 		else
	 			{
	 			$scope.masked = false;
				 window.location.href="#";
	 				
 				 $scope.errmessage="Goal is not feasible since you are not having sufficient funds !!";
            	  $("#myModal").modal('show');
 
	 			
	 			}
	 		 }, function(error) {
	  
	
	 		 });
	/*  $scope.errmessage="Goal updated successfully !!";
	 $("#myModal").modal('show'); */
	 			}
	       
 }
	 
//---------Success modal alert hide------------------------------------------
 $scope.reload=function()
{
		
	window.location.href=window.location.href.slice(0, -1);
	
	}
 
 
 $scope.hideSuccess=function()
 {
	  $("#success-alert").hide();
	  
 }
 $scope.hideFail=function()
 {
	  $("#fail-alert").hide();
	  
 }

 $scope.DeleteGoal=function()
	{ 
		
		$('#myModal1').modal('show')
		}

 $scope.deletegoal=function(){
	 $scope.masked = true;
		$scope.formdata.actionHomeType="deleteGoal";
		
		$scope.formdata.plan_name=$scope.planName;
		
		$scope.formdata.goal_id=(decodeURIComponent(hashes));
		
		
		$http({
	  		  method: 'POST',
	  		  url: 'GoalCollegeEducation',
	  		  data: $.param($scope.formdata),
	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
				}).then(function(result) {
				$scope.goDashboard();
				$scope.message1=result.data.status;
				
				$scope.masked = false;

	  		 }, function(error) {
	  			$scope.message=result.data.status;
		  
	  		 }); 

	}
 //-----------------------------------------------------------------------
 $scope.deleteAllCookies=function() {  
		////alert("delete all cookies");
	
		$scope.sessionDelete.sessionID=readCookie("AhTwxlO");
	    $http({
				method: 'POST',
				 url: 'Logout',
				data: $.param($scope.sessionDelete),
				headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
			}).then(function(result) {
		////alert("Session Got deleted");
	
		window.location.href="index.jsp";

					 }, function(error) {
						////alert("Session not deleted");		

					 });
	}
	
	function readCookie(name) {
		////alert("hi");
	    var nameEQ = name + "=";
	    var ca = document.cookie.split(';');
	    for(var i=0;i < ca.length;i++) {
	        var c = ca[i];
	        while (c.charAt(0)==' ') c = c.substring(1,c.length);
	        if (c.indexOf(nameEQ) == 0) 
		return c.substring(nameEQ.length,c.length);
	    }
	    return null;
	}
				
	//---------------------------------------logic for back button-------------------------
	
	$scope.goDashboard=function()
	{
	window.location.href="dashboardUser0.jsp?finName="+ $scope.planName;
	}

//........................logic for alert back button update........................

	$scope.backwithoutsave=function(formdata)
			
			{
	//alert("$scope.collegeEducationAmount");

				if($scope.goalHouseEditData.kidCollegeYear!=formdata.KidCollegeYear||
						$scope.goalHouseEditData.collegeType!= formdata.CollegeType||
						$scope.goalHouseEditData.collegeEducationAmount!=formdata.CollegeEducationAmount||
						$scope.Percent!=formdata.CollegeEducationAmountPercentage)
				{
					$('#myModalback').modal('show');

				}
			else
				{
	
				$scope.goDashboard();
				}

				}



	
//----------------------------------------------------------

				  $scope.load=function() {  
						//alert("hello");
					  $scope.sessionDetails.cookieId=readCookie("AhTwxlO");
					  ////alert( $scope.sessionDetails.cookieId);
					  $scope.sessionDetails.lastVisitedPage=document.URL;
					  ////alert( $scope.sessionDetails.lastVisitedPage);
					  if($scope.sessionDetails.cookieId!=null)
						  {
						  
						  $http({
		  						method: 'POST',
		 						url: 'CheckSession',
		  						data: $.param($scope.sessionDetails),
		  						headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
								}).then(function(result) {
							
								if(result.data.status=="success")
									{
								////alert("Cookie ajax Success");
								//alert(result.data.lastVisitedPage);
										if(result.data.lastVisitedPage=="undefined")
										{
											
											window.location.href="dashboardUserr0.jsp";
											
										}
										else
										{
											document.cookie="lastVisitedPage=" + result.data.lastVisitedPage;
											if(result.data.lastVisitedPage==document.URL)
												{
											////alert("redirecting since in db there is another lastvisited page")
											//window.location.href=result.data.lastVisitedPage;
											}
											
										}
								$scope.load1();

									
							
								
								
								
									}
								else
									{
									
									$scope.errMessage="Session got expired";
									 $("#checkSession").modal("show");
									 var delay = 3000; //Your delay in milliseconds
									setTimeout(function(){ $scope.deleteAllCookies() }, delay);
									
									}

									 }, function(error) {
										 
								////alert("Cokkie ajax Fail");					  
									 });
						  }
					  else
					  {
					  	////alert("Session got expired");
					  	$scope.deleteAllCookies();
					  	window.location.href="index.jsp";
					  }
				
				   }
	

});

</script>

<script type='text/javascript'>

function progress_bar() {  $('#progress_bar').css('width', '35%'); }
function progress_bar1() {  $('#progress_bar').css('width', '70%'); }
function progress_bar2() {  $('#progress_bar').css('width', '70%'); }
function progress_bar3() {  $('#progress_bar').css('width', '100%'); }
function progress_bar4() {  $('#progress_bar').css('width', '100%'); }
/* border: 2px solid #73AD21 ; margin-left: 20px;*/

</script>
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css"
	type="text/css">
<!-- Plugin CSS -->
<link rel="stylesheet" href="css/animate.min.css" type="text/css">
<!-- Custom CSS -->
<link rel="stylesheet" href="css/creative1.css" type="text/css">
<style>
.dropdown-submenu {
	position: relative;
}

.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: -6px;
	margin-left: -1px;
	-webkit-border-radius: 0 6px 6px 6px;
	-moz-border-radius: 0 6px 6px 6px;
	border-radius: 0 6px 6px 6px;
}

.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #cccccc;
	margin-top: 5px;
	margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
	border-left-color: #555;
}

.dropdown-submenu.pull-left {
	float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
	left: -100%;
	margin-left: 10px;
	-webkit-border-radius: 6px 0 6px 6px;
	-moz-border-radius: 6px 0 6px 6px;
	border-radius: 6px 0 6px 6px;
}

.left_content {
	height: 500px;
	border-radius: 25px;
}

@media ( min-width : 768px) {
	ul.nav li:hover>ul.dropdown-menu {
		display: block;
	}
	#navbar {
		text-align: center;
	}
}

.right_content {
	height: 500px;
	background: white;
	"
} /* 
      .modal-dialog {
    display:table;
    height: 20%;
    margin-top: 30%;
    width: 100%;
} */
.progress {
	width: 50%;
	margin-left: 20%;
}

#container {
	display: block;
	margin: 5px 0;
}

.modal-content {
	/* Bootstrap sets the size of the modal in the modal-dialog class, we need to inherit it */
	width: inherit;
	height: inherit;
	/* To center horizontally */
	margin: 0 auto;
	box-shadow: none;
	border: none;
}

.modal, .modal-backdrop {
	position: absolute !important;
	margin: 0 auto;
}

.bigform-content h1 {
	margin: 0;
}

.bigform-content input[type=submit] {
	margin-top: 10px;
}
</style>
<script>


</script>
</head>
<body id="page-top" ng-controller="GoalPlane" ng-init="load()" ng-cloak>

	<div class="MaskLayer" ng-class="{isClosed : !masked}">
		<div class="MaskLayer-Content">
			<i class="fa fa-refresh fa-spin fa-3x"
				style="position: absolute; margin-left: 18%; margin-top: 50%; color: white; z-index: 1;"></i>
		</div>
	</div>

	<nav id="mainNav" style="color: #222;"
		class="navbar navbar-default navbar-fixed-top">
		<div class="MaskLayer" ng-class="{isClosed : !masked}">
			<div class="MaskLayer-Content">
				<i class="fa fa-refresh fa-spin fa-3x"
					style="position: absolute; margin-left: 18%; margin-top: 50%; color: white; z-index: 1;"></i>
			</div>
		</div>

		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand page-scroll" href="dashboardUser0.jsp">WealthSetter</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a class="page-scroll" href="#" ng-click="goDashboard()">Home</a>
					</li>
					<li><a class="page-scroll" href="#services">How it works?</a>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Case Studies <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="CaseStudy1.jsp">Case Study 1</a></li>
							<li><a href="CaseStudy2.jsp">Case Study 2</a></li>
							<li><a href="CaseStudy3.jsp">Case Study 3</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Resources <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="mortgageCalculator.jsp">Mortgage
									Calculator</a></li>
							<li class="dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Car
									Calculator</a>
								<ul class="dropdown-menu">
									<li><a href="carLoanCalculator.jsp">Car Loan
											Calculator</a></li>
									<li><a href="carLeaseCalculator.jsp">Car Lease
											Calculator</a></li>
								</ul></li>
							<li><a href="ssbCalculator.jsp">Social Security Benefits</a></li>
							<li><a href="FderalincomeTaxCalculator.jsp">Income Tax Calculator</a></li>
						</ul></li>
					<li>
						<!--   <a class="page-scroll" href="Report.jsp">Reports</a> --> <a
						href="#" ng-click="report()">Reports</a>
					</li>
					<li><a class="page-scroll" href="userProfile.jsp"><i
							class="fa fa-user-plus"></i> My Profile</a></li>

					<li><a href="#" class="page-scroll"
						ng-click="deleteAllCookies()" href="index.jsp"><i
							class="fa fa-sign-out"></i> Logout</a></li>
					<!---  <li>
                     <a class="page-scroll" href="#contact">Contact</a>
                     </li> ---->
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<section class="bg-primary" id="about">
		<div class="container">
			<div class="row">
				<div style="display: none" class="alert alert-info text-center"
					id="fail-warning">
					<button type="button" class="close" ng-click="hideWarning()">x</button>
					<strong>Warning! Goal is not feasible currently with your
						income profile !! Please update your income profile in the
						Dashboard to make it feasible </strong> {{errmessage1}}
				</div>
				<div style="display: none" class="alert alert-danger text-center"
					id="fail-alert">
					<button type="button" class="close" ng-click="hideFail()">x</button>
					<strong>Fail! </strong> {{errmessage1}}
				</div>
				<div style="display: none" class="alert alert-success text-center"
					id="success-alert">
					<button type="button" class="close" ng-click="hideSuccess()">x</button>
					<strong>Success! </strong> {{errmessage}}
				</div>
				<div class="title-createplan">
					College Education
					<button class="btn btn-primary pull-right"
						ng-click="backwithoutsave(formdata)">Back</button>
					<button type="button" class="btn btn-primary pull-right"
						ng-click="DeleteGoal()" style="margin-right: 0.3%;">
						<i class="fa fa-trash-o"></i>Delete
					</button>
				</div>
				<div>
					<div class="side-panel" style="padding-bottom: 19%">
						<div class="left_content">
							<h1>

								<div class="sidebar-collapse">



									<ul class="nav" id="main-menu" style="font-size: 17px">

										<li><a class="active-menu"><i class="fa fa-arrow "></i>Details</a>
										</li>


									</ul>
								</div>
							</h1>
							<form name="goalDetails" id="goalForm">
								<br>
								<div></div>
								{{message}}
								<div class="form-group" align="left">
									<p>Goal Name</p>
									
								<input type="text" class="form-control" ng-model="formdata.Goalname">	

								</div>
 
								<div class="form-group" align="left">
									<p>
										Year entering college <select name="KidCollegeYear"
											ng-model="formdata.KidCollegeYear" class="form-control"><option
												ng-repeat="item in items" value="{{item}}">{{item}}</option></select>
									</p>

								</div>
								</p>
								<!--<div class="form-group" align="left">
								 <p> Marital Status <br>
								 <input type="radio" name="sex" value="male" checked>Single 
  								<input type="radio" name="sex" value="female"> Married</p>
  								</div>-->
								<!-- 	<div class="form-group" align="left" ng-show="show>2"><p>Anual Income
  									<input type="text" class="form-control" name="anualIncome" value="$20000"  ng-model="formdata.anualIncome"></p>
								</div> -->
								<!--<div class="form-group" align="left"> <p>Spouse Income
  									<input type="text" class="form-control" id="spouseIncome" value="0" ng-model="formdata.spouseIncome"></p>
								</div>-->

								<div class="form-group" align="left">
									<p>
										College Type <select name="CollegeType"
											ng-model="formdata.CollegeType" class="form-control"
											ng-change="values()"><option
												ng-repeat="item in CollegeType" value="{{item.name}}">{{item.name}}</option></select>
									</p>
									<div class="slider"></div>
								</div>
								<div class="form-group" align="left">
									<p>
										Real College Cost Increase Over Time<span class="percent-field"><input
											type="text" class="form-control" maxlength="4"
											onkeypress="return isNumberKey(event,this)"  value="$0" name="PercentageCollegeCost" maxlength="4" ng-change="ChangeCollegeCost();"  placeholder="200" ng-model="formdata.PercentageCollegeCost"></span>
									</p>
								</div>
								
								
								<div class="form-group" align="left">
									<p>
										College Cost Per Year In Current Year Dollars<span class="money-field"><input
											type="text" class="form-control" value="$0" name="RealAnnualCollegeCostOverTime" maxlength="15" allow-pattern="\d" placeholder="200000" ng-model="formdata.RealAnnualCollegeCostOverTime"></span>
									</p>

								</div>
								<div class="form-group" align="left">
									<p>
										How much you can pay? <span class="percent-field"> <select
											class="form-control" allow-pattern="\d"
											name="CollegeEducationAmount"
											ng-model="formdata.CollegeEducationAmountPercentage"><option
													ng-repeat="CollegeEducationAmountPercentages in CollegeEducationAmountPercentage"
													value="{{CollegeEducationAmountPercentages}}">{{CollegeEducationAmountPercentages}}</option></select>
									</p>
									</span>
									</p>
								</div>

								<div class="form-group">
									Do you have an 529 plan?
									<div class="radio">
										<label> <input type="radio" name="plan529"
											ng-value="false" ng-model="formdata.plan529demo"
											ng-change="yes1()"> Yes
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="plan529"
											ng-value="true"  ng-model="formdata.plan529demo"
											ng-change="no1()"> No
										</label>
									</div>

								</div>
								<div class="form-group" align="left" ng-hide="plan529Yes">
									How much do you want to deduct from your 529 plan yearly?
									<p>
										<input type="text" class="form-control" maxlength="12"
											allow-pattern="\d" name="plan529Saving" value="$0"
											ng-model="formdata.plan529Saving">
									</p>

								</div>
								</p>
								<div class="form-group" align="left">

									<!-- <a type="button"  data-toggle="collapse" data-target="#demo">ADVANCED</a>
 								 <div id="demo" class="collapse">
   						 
 									 </div> -->

									<input type=hidden ng-model="formdata.actionHomeType"
										value="form1" ng-init="formdata.actionHomeType='update'">
									&nbsp&nbsp
									<button type="button" class="btn btn-primary"
										ng-click="checkform1()">Update</button>
								</div>
						</div>

						<!--<div class="form-group" align="left" ng-show="show>5"> <p> Credit Score
  									<input type="text" class="form-control" name="creditScore" value="Very Good" ng-model="formdata.creditScore"> </p>  
								</div>	-->

						</form>
					</div>
				</div>
				<div class="center-content" style="height: 750px;">
					<div class="center_panel">
						<div class="align-sidebar fade in " id="dialog_confirm_map"
							role="dialog" aria-labelledby="dialog_confirm_mapLabel"
							aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									</br>
									</br>
									<div class="progress">
										<div class="progress-bar progress-bar-striped active"
											id="progress_bar" ng-model="progressbar" aria-valuemin="0"
											aria-valuemax="100" style="width: 2%"></div>
									</div>


									<div class=" text-center">

										<div class="form-group" ng-show="show==1">

											<p>What is your desired location?</p>
											<input type="text" placeholder="Bangalore" name="firstname"
												ng-model="formdata.firstname"></br>
											</br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar()" ng-click="progressBar()">Next</button>
											</br>
											</br>
										</div>
										<span>{{formdata.firstname}}</span>
										<!-- <div class="form-group" ng-show="show==2" >
       							<p>What is your annual pre-tax income?</p> <input type="text" name="middlename"  placeholder="B" ng-model="formdata.firstname"></br></br>
       							 <input type="hidden" name="firstname"><button type="button" class="btn btn-primary" onclick="progress_bar1()" ng-click="progressBar1()">Next</button></br></br>
   							 </div> -->
										<span>{{formdata.middlename}}</span>
										<div class="form-group" ng-show="show==3">
											<p>Down Payment</p>
											<input type="hidden" name="middlename"> <input
												type="hidden" name="firstname"> <input type="text"
												name="lastname" placeholder="$20000"
												ng-model="formdata.firstname"></br>
											</br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar2()" ng-click="progressBar1()">Next</button>
											</br>
											</br>
										</div>
										<div class="form-group" ng-show="show==4">
											<p>What are your monthly debt payments?</p>
											<input type="hidden" name="middlename"> <input
												type="hidden" name="firstname"> <input type="text"
												name="lastname" placeholder="$ 0"
												ng-model="formdata.firstname"></br>
											</br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar3()" ng-click="progressBar2()"
												data-dismiss="modal">Next</button>
											</br>
											</br>

										</div>
									</div>


								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- /.modal -->
						<div class="right_content">
							</br>
							<div class=" text-center">
								<i class="fa fa-home fa-5x"></i></br>
								</br>
								<p>The annual expense for college education is $
									{{collegeEducationAmount}}</p>
<div ng-hide="showDataTables">
								<table class="table">
								<!-- <tr>
										<td>Goal Name</td>
										<td>{{Goalname}}</td>
									</tr> -->
									<tr>
										<td>Year entering college</td>
										<td>{{kidCollegeYear}}</td>
									</tr>
									<tr>
									<td>College Type</td>
									<td>{{ collegeType }}</td>
									</tr>
									<tr>
									<td>Year of graduation</td>
									<td>{{kidCollegeYear + 3}}</td>
									</tr>
									
										<tr>
									<td>Tuition and Fees</td>
									<td>{{TuitionAndFees}}</td>
									</tr>
										<tr>
									<td>Room and Board</td>
									<td>{{RoomAndBoard}}</td>
									</tr>
										<tr>
									<td>Books and Supplies</td>
									<td>{{BooksAndSupplies}}</td>
									</tr>
										<tr>
									<td>Transportation</td>
									<td>{{Transportation}}</td>
									</tr>
										<tr>
									<td>OtherExpenses</td>
									<td>{{OtherExpenses}}</td>
									</tr>
									<tr>
									<td>First year cost</td>
									<td>{{year1cost.toFixed(2)}}</td>
									</tr>
																			<tr>
									<td>Second year cost</td>
									<td>{{year2cost.toFixed(2)}}</td>
									</tr>
																			<tr>
									<td>Third year cost</td>
									<td>{{year3cost.toFixed(2)}}</td>
									</tr>
																			<tr>
									<td>Fourth year cost</td>
									<td>{{year4cost.toFixed(2)}}</td>
									</tr>
															<tr>
									<td>College Cost per year in Current Year Dollars</td>
									<td>$ {{ totalCollegeCost }}</td>
									</tr>
									
								</table>

</div>
<div ng-hide="showDataForTransfered">
								<table class="table">
								<!-- <tr>
										<td>Goal Name</td>
										<td>{{Goalname}}</td>
									</tr> -->
									<tr>
										<td>Year entering college</td>
										<td>{{kidCollegeYear}}</td>
									</tr>
									<tr>
									<td>College Type</td>
									<td>{{ collegeType }}</td>
									</tr>
									<tr>
									<td>Year of graduation</td>
									<td>{{kidCollegeYear + 3}}</td>
									</tr>
							
									<tr>
									<td>First year cost</td>
									<td>{{year1cost.toFixed(2)}}</td>
									</tr>
																			<tr>
									<td>Second year cost</td>
									<td>{{year2cost.toFixed(2)}}</td>
									</tr>
																			<tr>
									<td>Third year cost</td>
									<td>{{year3cost.toFixed(2)}}</td>
									</tr>
																			<tr>
									<td>Fourth year cost</td>
									<td>{{year4cost.toFixed(2)}}</td>
									</tr>
															<tr>
									<td>College Cost per year in Current Year Dollars</td>
									<td>$ {{ totalCollegeCost }}</td>
									</tr>
									
								</table>

</div>




							</div>
						</div>

					</div>

				</div>
			</div>

			<div id="checkSession" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title">Status</h4>
						</div>
						<div class="modal-body">
							<p>{{errMessage}}</p>
						</div>
						<div class="modal-footer">
							<button type="button" ng-click="check1()" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
			<div id="myModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title">Message</h4>
						</div>
						<div class="modal-body">
							<p>{{errmessage}}</p>
						</div>
						<div class="modal-footer">
							<button type="button" ng-click="reload()" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="myModal1" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Message</h4>
						</div>
						<div class="modal-body">
							<p>Are you sure you want to delete the goal</p>
						</div>
						<div class="modal-footer">
							<button type="button" ng-click="deletegoal()"
								class="btn btn-primary pull-right" data-dismiss="modal">
								<i class="fa fa-trash-o"></i>Delete
							</button>

							<button type="button" class="btn btn-default"
								data-dismiss="modal"
								style="background-color: #B7B7BB; margin-right: 0.3%;">Close</button>
						</div>
					</div>

				</div>
			</div>

			<div class="modal fade" id="myModalback" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Message</h4>
						</div>
						<div class="modal-body">
							<p>You had made some changes,are you sure you want to go back
								without updating this goal ?</p>
						</div>
						<div class="modal-footer">
							<button type="button" id="dialog-ok" ng-click="goDashboard()"
								class="btn btn-primary pull-right" data-dismiss="modal">Yes</button>

							<button type="button" class="btn btn-default"
								data-dismiss="modal">No</button>
						</div>
					</div>

				</div>
			</div>
			<div class="modal fade" id="caseStudy" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Message</h4>
						</div>
						<div class="modal-body">
							<p>Page Under Construction</p>
						</div>
						<div class="modal-footer">

							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>

		</div>

	</section>
	<!-- Footer -->
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<br></br>
					<p style="color: white;">Copyright &copy; WealthSetter 2017. All
						Rights Reserved</p>
				</div>
			</div>
		</div>
	</footer>


	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Plugin JavaScript -->
	<script src="js/jquery.easing.min.js"></script>
	<script src="js/jquery.fittext.js"></script>
	<script src="js/wow.min.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="js/creative.js"></script>
	<script src="assets/js/fusioncharts.theme.fint.js"></script>
	<script src="assets/js/fusioncharts.powercharts.js"></script>
	<script src="assets/js/fusioncharts.js"></script>
</body>
</html>
