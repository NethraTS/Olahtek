<!DOCTYPE html>
<html lang="en" ng-app="formApp">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="Balagopalan" >
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
          			//alert(keyCodeChar.charCodeAt());
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

app.controller('GoalPlane',function($http,$scope){
		$scope.show = 1;
		$scope.date=new Date();
		$scope.sessionDetails={};
		 $scope.sessionDelete={};
                $scope.formdata={};
                $scope.collegedata={};
                $scope.formdata.CollegeEducationAmountPercentage=100;
                $scope.formdata.PercentageCollegeCost =0;
                $scope.kidArrays=[];
                $scope.items =[$scope.date.getFullYear(),$scope.date.getFullYear()+1,$scope.date.getFullYear()+2,
                               $scope.date.getFullYear()+3,$scope.date.getFullYear()+4,$scope.date.getFullYear()+5,$scope.date.getFullYear()+6,
                               $scope.date.getFullYear()+7,$scope.date.getFullYear()+8,$scope.date.getFullYear()+9,$scope.date.getFullYear()+10,
                               $scope.date.getFullYear()+11,$scope.date.getFullYear()+12,$scope.date.getFullYear()+13,$scope.date.getFullYear()+14,
                               $scope.date.getFullYear()+15,$scope.date.getFullYear()+16,$scope.date.getFullYear()+17,$scope.date.getFullYear()+18,
                               $scope.date.getFullYear()+19,$scope.date.getFullYear()+20,$scope.date.getFullYear()+21,$scope.date.getFullYear()+22,
                               $scope.date.getFullYear()+23,$scope.date.getFullYear()+24,$scope.date.getFullYear()+25,$scope.date.getFullYear()+26,
                               
                               $scope.date.getFullYear()+27,$scope.date.getFullYear()+28,$scope.date.getFullYear()+29,$scope.date.getFullYear()+30,
                               $scope.date.getFullYear()+31,$scope.date.getFullYear()+32,$scope.date.getFullYear()+33,$scope.date.getFullYear()+34,
                               $scope.date.getFullYear()+35,$scope.date.getFullYear()+36,$scope.date.getFullYear()+37] 
                	//alert(JSON.stringify($scope.items));
                $scope.CollegeEducationAmountPercentages = [ {name:'100'},{name:'95'},{name:'90'},{name:'85'},{name:'80'},{name:'75'},{name:'70'},{name:'65'},{name:'60'},{name:'55'},{name:'50'},{name:'45'},{name:'35'},{name:'25'},{name:'30'},{name:'25'},{name:'20'},{name:'15'},{name:'10'}];
                $scope.CollegeType = [{name:'2-Years-Public',value:'1,07,010'},{name:'4-Years-Public-in-state',value:'1,69,509'},{name:'4-Years-Public-out-of-state',value:'2,10,677'},{name:'4-Years-Private',value:'2,10,677'},{name:'4-Years-Private-new-England-area',value:'2,10,677'},{name:'2-Years-Public-transferred-to-4-Years-Public-in-state',value:'1,07,010'},{name:'2-Years-Public-transferred-to-4-Years-Public-out-of-state',value:'1,07,010'},{name:'2-Years-Public-transferred-to-4-Years-Private',value:'1,07,010'},{name:'2-Years-Public-transferred-to-4-Years-Private-new-England-area',value:'1,07,010'}];
                $scope.formdata.KidCollegeYear="";
                $scope.goalHouseData={}
                $scope.Math = window.Math;
                $scope.message="Goal Name"
                $scope.progressbar=0;
                $scope.masked = false;
                $scope.formdata.goal_id="";
             $scope.formdata.KidCollegeYear=$scope.date.getFullYear();
             $scope.formdata.CollegeType="2-Years-Public";
             $scope.formdata.CollegeEducationAmountPercentage="100";
             $scope.formdata.PercentageCollegeCost ="0";
             $scope.collegeCostToServlet=[];
                $scope.formdata.plan_name=(decodeURIComponent(hashes));
               
                $scope.addItem = function () {

                    $scope.items.push({
                        amount: $scope.selectedItem,
                        //name: $scope.itemName
                    })

                    // Clear input fields after push
                    $scope.selectedItem = "";
                    $scope.itemName = "";

                };
                $scope.yes1=function()
                {
               	 $scope.plan529Yes=false;
               		$scope.plan529No=true;
               		$scope.formdata.plan529="false";
                    }
 //----------------s---------------------------------------------------------------------     
 			$scope.load1=function() { 
 				$scope.formdata.actionHomeType="getKidData";
     					 $http({
       		  						method: 'POST',
       		 						url: 'GoalCollegeEducation',
       		  						data: $.param($scope.formdata),
       		  						headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
     								}).then(function(result) {
     								if(result.data.status=="success")
     							      {
     								//alert("Cookie ajax Success");
     								//alert(JSON.stringify(result.data));
     								$scope.kidArrays=result.data.childArray;
     								//alert(JSON.stringify($scope.kidArrays))
     								$scope.formdata.Goalname=$scope.kidArrays[0];
     								$scope.formdata.plan529Saved=0;
     								  $scope.collegedata=result.data;
     								 // alert(JSON.stringify($scope.formdata.Goalname))
     								  $scope.ResultforCollegeEducation = result.data.collegeCosts;
     			//					  alert(JSON.stringify(result.data.collegeCosts));
     								  /* .CollegeEducationAmount=result.data.collegeGoalDefaultCostPeryearForinstatepubliccollege;
     								alert("$scope.formdata.CollegeEducationAmount==="+$scope.formdata.CollegeEducationAmount+  "result.data.collegeGoalDefaultCostPeryearForinstatepubliccollege   " 
     										+result.data.collegeGoalDefaultCostPeryearForinstatepubliccollege);
     								 $scope.collegedata..CollegeEducationAmount1=result.data.collegeGoalDefaultCostPeryearForoutofstatepubliccollege;
     								$scope.collegedata.CollegeEducationAmount2=result.data.PeryearForPrivateCollege;
     							 */
     									}

       								 }, function(error) {
       										 
       								////alert("Cokkie ajax Fail");					  
       						 });
     					 
     					 //alert(JSON.stringify($scope.items));
     					/* var a=[];
     					var k=-1;
     					//alert(JSON.stringify(a));
     					//alert(JSON.stringify($scope.formdata.KidCollegeYear))
     					for(l=$scope.formdata.KidCollegeYear.length;l<=($scope.formdata.KidCollegeYear+3);l++) {
     						var b ={};
     						b.push($scope.formdata.KidCollegeYear[l]);
     						b.push($a[++k]);
     						a.push(b);
     						
     					}
     					alert(JSON.stringify(a)); */

     				   }
//----------------------------------------------------------------------------------------------------
       $scope.no1=function()
       {
       	$scope.msgerr="";
       	$scope.plan529Yes=true;
       	$scope.plan529No=false;
       	$scope.formdata.plan529="true";
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
         $scope.progressBar=function() {  
        	 if($scope.formdata.Goalname==null||$scope.formdata.Goalname==undefined||$scope.formdata.Goalname==""){
 			$scope.msgerr="Goal name is empty !!";     		
             } else{
            	 $('#progress_bar').css('width', '25%');
        	 $scope.message="Start Year";
        	 $scope.formdata.actionHomeType="getYearArray";	
    		 $scope.formdata.KidCollegeYear=$scope.formdata.KidCollegeYear;
    		 $http({
    	  		  method: 'POST',
    	  		  url: 'GoalCollegeEducation',
    	  		  data: $.param($scope.formdata),
    	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
    				}).then(function(result) {
    					$scope.items=result.data.year;
    					//alert(JSON.stringify(result.data.year))
    					$scope.formdata.KidCollegeYear=$scope.items[0];
    	  		 }, function(error) {
    		  $scope.message=result.data;
    		  
    	  		 });
        		 $scope.show = 2;
        	 $scope.show1 = 2;
             }
          }  
         $scope.progressBar0=function() {  
        	 if($scope.formdata.KidCollegeYear==null||$scope.formdata.KidCollegeYear==undefined||$scope.formdata.KidCollegeYear==""){
 			$scope.msgerr="Please select any start year !!";     		
             } else{
            	 for(i=$scope.formdata.KidCollegeYear*1;i<($scope.formdata.KidCollegeYear*1+4);i++)
            		 {
            		 $scope.obj={"year":i,"cost":0};
            		 
            		 $scope.collegeCostToServlet.push($scope.obj);
            		 
            		 }
            	 //alert(JSON.stringify($scope.collegeCostToServlet));
            	 $scope.formdata.actionHomeType="getPlan529Amount";
            	 $http({
       	  		  method: 'POST',
       	  		  url: 'GoalCollegeEducation',
       	  		  data: $.param($scope.formdata),
       	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
       				}).then(function(result) {
       					$scope.formdata.totalPlan529=result.data.plan529;
       	  		 }, function(error) {
       		  $scope.message=result.data;
       		  
       	  		 });
            	 
            	 $('#progress_bar').css('width', '35%');
        	 $scope.message="College Type"
        		
    		 $scope.formdata.KidCollegeYear=$scope.formdata.KidCollegeYear;
        	 
        		 $scope.show = 3;
        	 $scope.show1 = 3;
             }
        	
         }  
          $scope.progressBar1=function() { 
        	  if($scope.formdata.CollegeType==null||$scope.formdata.CollegeType==undefined|| $scope.formdata.CollegeType==""){
                  //alert("fsdf");
  			$scope.msgerr2="College Type is empty";
      		
              } else {
            	  $('#progress_bar').css('width', '50%');
        	 $scope.message="College Amount"
        		
        	
        	 //alert(JSON.stringify($scope.ResultforCollegeEducation));
        	// [{name:'2-Years-Public',value:'1,07,010'},{name:'4-Years-Public-in-state',value:'1,69,509'},{name:'4-Years-Public-out-of-state',value:'2,10,677'},{name:'4-Years-Private',value:'2,10,677'},{name:'4-Years-Private-new-England-area',value:'2,10,677'},{name:'2-Years-Public-transferred-to-4-Years-Public-in-state',value:'1,07,010'},{name:'2-Years-Public-transferred-to-4-Years-Public-out-of-state',value:'1,07,010'},{name:'2-Years-Public-transferred-to-4-Years-Private',value:'1,07,010'},{name:'2-Years-Public-transferred-to-4-Years-Private-new-England-area',value:'1,07,010'}];
        	
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
         			
        	  
        		 $scope.show = 4;
        	 $scope.show1 = 4;
              }
         }   
         
//--------------------------------------model for percentage---------------------------
	$scope.progressBar3=function() {  
        	 if($scope.formdata.CollegeEducationAmountPercentage==null||$scope.formdata.CollegeEducationAmountPercentage==undefined||$scope.formdata.CollegeEducationAmountPercentage==""){
                
 			$scope.msgerr3=" College Education Amount percent should not be empty";
 			
     		
             }
        	 else if($scope.formdata.CollegeEducationAmount==0){
               
      			$scope.msgerr3=" College Education percent Amount cannot be zero !!";
      			
          		
                  }
              else{
           
            	 $('#progress_bar').css('width', '85%');
            	 $scope.formdata.plan529demo=true;
            	 $scope.plan529Yes=true;
            	 /* $('#dialog_confirm_map').modal('hide'); */
        	 $scope.message="DETAILS";
        	 
        	$scope.formdata.CollegeEducationAmount=$scope.formdata.CollegeEducationAmount*1;
       
        		 $scope.show =7;
        	 	 $scope.show1 = 7;
 		

             }
        	
             }
             
	$scope.progressBar4=function() {  
		//alert("fgfghf")
		//$scope.formdata.PercentageCollegeCost=$scope.formdata.PercentageCollegeCost;
/* 		for(i=0;i<$scope.ResultforCollegeEducation.length;i++){
			if($scope.formdata.CollegeType == $scope.ResultforCollegeEducation[i].type){
				 if($scope.formdata.PercentageCollegeCost==0){
		         	   $scope.formdata.RealAnnualCollegeCostOverTime=$scope.ResultforCollegeEducation[i].Total;
		                //alert(JSON.stringify($scope.formdata.RealAnnualCollegeCostOverTime))  
		            }
		            else{
		         	   $scope.formdata.RealAnnualCollegeCostOverTime=Number($scope.ResultforCollegeEducation[i].Total)+Number($scope.ResultforCollegeEducation[i].Total * (Math.ceil($scope.formdata.PercentageCollegeCost)/100));
		                //alert(JSON.stringify($scope.formdata.RealAnnualCollegeCostOverTime)) 
		         	   
		            }
	}
} */
		//alert($scope.formdata.PercentageCollegeCost)
   	 if($scope.formdata.PercentageCollegeCost==null||$scope.formdata.PercentageCollegeCost==undefined||$scope.formdata.PercentageCollegeCost==""){
           
		$scope.msgerr3=" Real Annual College Cost Over Time should not be empty";
		
		
        }
   	
         else{
        	
        	 
        	 for(i=0;i<$scope.collegeCostToServlet.length;i++)
         	{
 //       		 alert($scope.collegeCostToServlet[i].year)
         		$scope.collegeCostToServlet[i].cost=((($scope.collegeCostToServlet[i].cost*Math.pow((1+$scope.formdata.PercentageCollegeCost/100),($scope.collegeCostToServlet[i].year-new Date().getFullYear())))));
         	}
        	 
        	 $scope.formdata.RealAnnualCollegeCostOverTime=$scope.collegeCostToServlet[0].cost.toFixed(2);
        	// alert($scope.formdata.RealAnnualCollegeCostOverTime);
   //     	 alert(JSON.stringify($scope.collegeCostToServlet));
       	 $('#progress_bar').css('width', '60%');
       	 $scope.formdata.plan529demo=true;
       	 $scope.plan529Yes=true;
       	 /* $('#dialog_confirm_map').modal('hide'); */
   	 $scope.message="DETAILS";
   	 
   	$scope.formdata.CollegeEducationAmount=$scope.formdata.CollegeEducationAmount*1;
  
   		 $scope.show =5;
   	 	 $scope.show1 = 5;
        }
        }
	$scope.progressBar5=function() {  
		
		//alert(JSON.stringify($scope.ResultforCollegeEducation));
	   	 if($scope.formdata.RealAnnualCollegeCostOverTime==null||$scope.formdata.RealAnnualCollegeCostOverTime==undefined||$scope.formdata.RealAnnualCollegeCostOverTime==""){
	           
			$scope.msgerr3=" Real Annual College Cost Over Time should not be empty";
			
			
	        }
	   	
	         else{
	       
	       	 $('#progress_bar').css('width', '75%');
	       	 $scope.formdata.plan529demo=true;
	       	 $scope.plan529Yes=true;
	       	 /* $('#dialog_confirm_map').modal('hide'); */
	   	 $scope.message="DETAILS";
	   	 
	   	$scope.formdata.CollegeEducationAmount=$scope.formdata.CollegeEducationAmount*1;
	  
	   		 $scope.show =6;
	   	 	 $scope.show1 = 6;
			

	        }
	   	
	        }
//-------------------------------------------------------------------------------------------------------------------
$scope.progressBar6=function() {  
        	 if($scope.formdata.plan529Saved==0 &&  $scope.plan529Yes==false)
    		{
    		$scope.msgerr="Deduction from your 529 plan should not zero !!";
    		}
        	      	
        	else if($scope.formdata.plan529Saved>(4*$scope.formdata.CollegeEducationAmount))
    		{
    		$scope.msgerr="Deduction from your 529 plan should not be greater than College Education Amount !!";
    		}
        	else{
        	$scope.masked = true;
            	 $('#progress_bar').css('width', '100%');
            	 $('#dialog_confirm_map').modal('hide');
        	 $scope.message="DETAILS";
        	 $scope.formdata.actionHomeType="insert";
        	$scope.formdata.CollegeEducationAmount=$scope.formdata.CollegeEducationAmount*1;
        	$scope.formdata.collegeCostToServlet=JSON.stringify($scope.collegeCostToServlet);
      // alert($scope.formdata.collegeCostToServlet);
        		 $scope.show =7;
        	 	 $scope.show1 = 7;
 			$http({
 	  		  method: 'POST',
 	  		  url: 'GoalCollegeEducation',
 	  		  data: $.param($scope.formdata),
 	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
 				}).then(function(result) {
 					$scope.goalHouseData=result.data;
 				$scope.expenseResult=result.data.collegeExpense;
 			$scope.formdata.goal_id = $scope.goalHouseData.goal_id;
 			$scope.kidCollegeYear=result.data.kidCollegeYear;
 			//alert(JSON.stringify($scope.KidCollegeYear))
 			$scope.collegeType=result.data.collegeType;
 			$scope.collegeEducationAmount=result.data.collegeEducationAmount;
         
 			 if($scope.goalHouseData.status=="success")
 			 {
 				
 				$scope.masked = false;
 				 console.log("message"+$scope.message);
 	 			
 					 window.location.href="GoalCollegeEducationEdit.jsp?goalId="+$scope.formdata.goal_id;	
 			 }
 			else
 				{
 				$scope.masked = false;
 				 $scope.errmessage="Goal is not feasible since you are not having sufficient funds !!";
           	  $("#myModal").modal('show');
 		
 				}
 			
 	  		 }, function(error) {
 		  $scope.message=result.data;
 		  
 	  		 });
 			

             }
};

      $scope.values=function(){   
    	  
    	  if($scope.formdata.CollegeType == '2-Years-Public'){
          
                   // $scope.value= 107010;
                    $scope.formdata.CollegeEducationAmount= $scope.value ;//alert($scope.formdata.CollegeEducationAmount);
                   }else if($scope.formdata.CollegeType == '4-Years-Public-in-state'){
                         
                  	// $scope.value= 169509;
                  	 $scope.formdata.CollegeEducationAmount= $scope.value; 
                       }else if($scope.formdata.CollegeType == '4-Years-Public-out-of-state'){
                     
                      	 //$scope.value= 210677;
                      	 $scope.formdata.CollegeEducationAmount= $scope.value; 
                           } 
                       else if($scope.formdata.CollegeType == '4-Years-Private'){
                           
                        	 //$scope.value= 210677;
                        	 $scope.formdata.CollegeEducationAmount= $scope.value; 
                             }
                       else if($scope.formdata.CollegeType == '4-Years-Private-new-England-area'){
                           
                        	 //$scope.value= 210677;
                        	 $scope.formdata.CollegeEducationAmount= $scope.value; 
                             }

                       else if($scope.formdata.CollegeType == '2-Years-Public-transferred-to-4-Years-Public-in-state'){
                           
                         	// $scope.value= 169509;
                         	 $scope.formdata.CollegeEducationAmount= $scope.value; 
                              }else if($scope.formdata.CollegeType == '2-Years-Public-transferred-to-4-Years-Public-out-of-state'){
                            
                             	 //$scope.value= 210677;
                             	 $scope.formdata.CollegeEducationAmount= $scope.value; 
                                  } 
                              else if($scope.formdata.CollegeType == '2-Years-Public-transferred-to-4-Years-Private'){
                                  
                               	 //$scope.value= 210677;
                               	 $scope.formdata.CollegeEducationAmount= $scope.value; 
                                    }
                              else if($scope.formdata.CollegeType == '2-Years-Public-transferred-to-4-Years-Private-new-England-area'){
                                  
                               	 //$scope.value= 210677;
                               	 $scope.formdata.CollegeEducationAmount= $scope.value; 
                                    }

     			  }
         $scope.checkform1=function(){
        	 window.location.href="#";
     		
     		 if($scope.formdata.CollegeEducationAmount==null||$scope.formdata.CollegeEducationAmount==undefined||$scope.formdata.CollegeEducationAmount==""){
			       $scope.errMessage="college education amount is empty";
			       $("#myModal").modal("show");
			       					 }else if($scope.formdata.CollegeEducationAmount==0){
			       				       $scope.errmessage="college education amount cannot be zero !!";
			       				       $("#myModal").modal("show");
			       				       					 }
	 else{
$scope.formdata.actionHomeType="update";
$scope.formdata.plan529demo=$scope.formdata.plan529;
//alert($scope.formdata.plan529);
$http({
		  method: 'POST',
		  url: 'GoalCollegeEducation',
		  data: $.param($scope.formdata),
		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).then(function(result) {
			$scope.expenseResult=result.data.collegeExpense;
		$scope.goalHouseData=result.data;
		$scope.expenseResult=result.data.collegeExpense;
			//$scope.formdata.goal_id = $scope.goalHouseData.goal_id;
			$scope.kidCollegeYear=result.data.kidCollegeYear;
			$scope.collegeType=result.data.collegeType;
			$scope.collegeEducationAmount=result.data.collegeEducationAmount;
		console.log("message"+$scope.message);

		 }, function(error) {
 $scope.message=result.data;
 
		 });
$scope.errmessage="Goal updated successfully !!";
$("#myModal").modal('show');
	 }
			}
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
     				
     				  $scope.load=function() {  
     						//alert("hello");
     						window.history.forward();
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
     				 $scope.goSelectGoal=function()
         			 {
     					window.location.href="goals.jsp?finName="+ $scope.formdata.plan_name;
         				
         			 }
     				$scope.goDashboard=function()
        			 {
    					window.location.href="dashboardUser0.jsp?finName="+ $scope.formdata.plan_name;
        				
        			 }
     	 
});

</script>
<script></script>
     <script type='text/javascript'>


</script> 
      <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css" type="text/css">
      <!-- Plugin CSS -->
      <link rel="stylesheet" href="css/animate.min.css" type="text/css">
      <!-- Custom CSS -->
      <link rel="stylesheet" href="css/creative1.css" type="text/css">
      <style>
      .dropdown-submenu{position:relative;}
.dropdown-submenu>.dropdown-menu{top:0;left:100%;margin-top:-6px;margin-left:-1px;-webkit-border-radius:0 6px 6px 6px;-moz-border-radius:0 6px 6px 6px;border-radius:0 6px 6px 6px;}
.dropdown-submenu>a:after{display:block;content:" ";float:right;width:0;height:0;border-color:transparent;border-style:solid;border-width:5px 0 5px 5px;border-left-color:#cccccc;margin-top:5px;margin-right:-10px;}
.dropdown-submenu:hover>a:after{border-left-color:#555;}
.dropdown-submenu.pull-left{float:none;}.dropdown-submenu.pull-left>.dropdown-menu{left:-100%;margin-left:10px;-webkit-border-radius:6px 0 6px 6px;-moz-border-radius:6px 0 6px 6px;border-radius:6px 0 6px 6px;}
.money-fieldmodel1::before {
    content: "$";
    position: absolute;
    font-size: 15px;
    display: block;
    padding: 3.2%;
    padding-top: 0.7%;
    padding-left: 77%;
}

      .left_content {
    height: 500px; border-radius: 25px;
    
}
@media (min-width: 768px) {
  ul.nav li:hover > ul.dropdown-menu {
    display: block;
  }
  #navbar {
    text-align: center;
  }
}
 .right_content {
  height: 500px; background:white;" 
    
}/* 
      .modal-dialog {
    display:table;
    height: 20%;
    margin-top: 30%;
    width: 100%;
} */
.progress{
width:50%;
margin-left:20%;
}
#container{   display: block;   margin: 5px 0;}
.modal-content {
    /* Bootstrap sets the size of the modal in the modal-dialog class, we need to inherit it */
    width:inherit;
    height:inherit;
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
    margin:0;
}
p{



}
.bigform-content input[type=submit] {
    margin-top:10px;
}</style>
     <script>

$(function () {
  
  //getting click event to show modal
   
        $('#dialog_confirm_map').modal();
      
      //appending modal background inside the bigform-content
        $('.modal-backdrop').appendTo('.right_content');
      //removing body classes to able click events
        //$('body').removeClass();

  //just to prove actions outside modal
    $('#help-button').click(function () {
        alert("Action with modal opened or closed");
    });
  //end just to prove actions outside modal
});
</script>
   </head>
   <body id="page-top"  ng-controller="GoalPlane" onload="addItem()" ng-init="load()" ng-cloak>
   <div class="MaskLayer"
ng-class="{isClosed : !masked}">
<div class="MaskLayer-Content">
<i class="fa fa-refresh fa-spin fa-3x"
										 style="position: absolute;    margin-left: 18%;    margin-top: 50%;    color: white;    z-index: 1;"></i>  
</div> 
</div>

      <nav id="mainNav" style="color: #222;" class="navbar navbar-default navbar-fixed-top">
         <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
               <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               </button>
               <a class="navbar-brand page-scroll" href="dashboardUser0.jsp">WealthSetter</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
               <ul class="nav navbar-nav navbar-right">
                  <li>
                     <a class="page-scroll"  href="#" ng-click="goDashboard()">Home</a>
                  </li>
                  <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Case Studies <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="CaseStudy1.jsp">Case Study 1</a></li>
                            <li><a href="CaseStudy2.jsp">Case Study 2</a></li>
                            <li><a href="CaseStudy3.jsp">Case Study 3</a></li>
						</ul>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Resources <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="mortgageCalculator.jsp" >Mortgage Calculator</a></li>
                            <li class="dropdown dropdown-submenu"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Car Calculator</a>
								<ul class="dropdown-menu">
									<li><a href="carLoanCalculator.jsp" >Car Loan Calculator</a></li>
									<li><a href="carLeaseCalculator.jsp" >Car Lease Calculator</a></li>
								</ul>
							</li>
							<li><a href="ssbCalculator.jsp">Social Security Benefits</a></li>
							<li><a href="FderalincomeTaxCalculator.jsp">Income Tax Calculator</a></li>
						</ul>
					</li>
					<li>
						<!--   <a class="page-scroll" href="Report.jsp">Reports</a> --> <a
						href="#" ng-click="report()">Reports</a>
					</li>
                  <li>
                     <a class="page-scroll" href="#services">How it works?</a>
                  </li>
                  <li><a class="page-scroll" href="userProfile.jsp"><i class="fa fa-user-plus"></i> My Profile</a>
                        </li>
                 
                        <li><a href="#" class="page-scroll" ng-click="deleteAllCookies()"><i class="fa fa-sign-out"></i> Logout</a>
                        </li>
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
             <div class="title-createplan" >College Education <button class="btn btn-primary pull-right" ng-click="goSelectGoal()">Back</button></div>
               <div >
                  <div class="side-panel"  >
					<div class="left_content">
					 <h1>
								
								<div class="sidebar-collapse">
								
								
								
                <ul class="nav" id="main-menu" style="font-size:17px">
                
               <li>     
<a  class="active-menu"><i class="fa fa-arrow "></i>{{message}}</a>
</li> 
                
         
               </ul>     
            </div>
					</h1>
					<form name="goalDetails" id="goalForm">
						<br>
						<div></div>
                        
                        <div class="form-group" align="left" ng-show="show1>1">
                        	<p>Goal Name <input type="text" allow-pattern="[a-z]" maxlength="12" class="form-control"  ng-model="formdata.Goalname">
  						</p>
  						</div>
						 
                        
                        
                        <div class="form-group" align="left" ng-show="show1>2">
                        	<p>Year entering college
  									<select name="KidCollegeYear" ng-model="formdata.KidCollegeYear"  class="form-control"><option ng-repeat="item in items" value="{{item}}">{{item}}</option></select>
  								</p>
						
								</div>
								
								<div class="form-group" align="left" ng-show="show1>3"><p> College Type 
  									<select name="CollegeType" ng-model="formdata.CollegeType"   margin-right: auto;" class="form-control" ng-change="values()"><option  ng-repeat="item in CollegeType" value="{{item.name}}">{{item.name}}</option></select></p> 
  									
									<div class="slider"></div>
								</div>	
								<div class="form-group" align="left" ng-show="show1>4">
									<p>
									Real College Cost Increase Over Time<span class="percent-field"><input
											type="text" class="form-control"
											ng-pattern="/^[1-9][0-9]{0,2}(?:,?[0-9]{3}){0,3}(?:\.[0-9]{1,2})?$/" allow-pattern="\d" name="PercentageCollegeCost" maxlength="1" onchange="ChangeCollegeCost();" value="$0"  placeholder="200" ng-model="formdata.PercentageCollegeCost"></span>
									</p>

								</div>
								<div class="form-group" align="left" ng-show="show1>5">
									<p>
										College Cost Per Year In Current Year Dollars<span class="money-field"><input
											type="text" class="form-control" name="RealAnnualCollegeCostOverTime" maxlength="12"  allow-pattern="\d" placeholder="200000" ng-model="formdata.RealAnnualCollegeCostOverTime"></span>
									</p>

								</div>
								<div class="form-group" align="left" maxlength="12" ng-show="show1>6"> <p>How much do you plan on supporting?
  									<span class="p"><input type="text" class="form-control" allow-pattern="\d" name="CollegeEducationAmountPercent" value="$0" ng-model="formdata.CollegeEducationAmountPercentage"></span></p>
  									
									
								</div>
								
								<div class="form-group" align="left" ng-show="show1>6">
								
								<!--    <a type="button"  data-toggle="collapse" data-target="#demo">ADVANCED</a> -->
 								
   									<input type=hidden ng-model="formdata.actionHomeType" value="form1" ng-init="formdata.actionHomeType='update'">
 									 </div><br>
 									<!-- &nbsp&nbsp<button type="button" class="btn btn-primary" ng-click="checkform1()">Update</button> --> 
								</div>
								
								
													
								
                       </form> 
					</div>
					</div>
					<div class="center-content" style="height: 750px;" >
					<div  class="center_panel"   >
					<div class="align-sidebar fade in " id="dialog_confirm_map" data-backdrop="static" data-keyboard="false" role="dialog" aria-labelledby="dialog_confirm_mapLabel" aria-hidden="true">
            			<div class="modal-dialog">
            				<div class="modal-content"></br></br>
            				      <div class="progress">
  <div class="progress-bar progress-bar-striped active" id="progress_bar" ng-model="progressbar"  aria-valuemin="0" aria-valuemax="100" style="width:2%">
  </div>
</div>
           
                			
                			<div class=" text-center" >
                			<form name="myForm">
                			  <div class="form-group" ng-show="show==1" >
                		       <span style="color:red">{{msgerr}}</span>
       							<p>Goal Name 
       							<br></br>
       							<select name="kidArray"  class="form-control" style="width:40%; margin-left: auto;    margin-right: auto;" ng-model="formdata.Goalname"><option ng-repeat="kidArray in kidArrays" value="{{kidArray}}">{{kidArray}}</option></select>
  						</p>
       						
       							 
       							<button type="button" class="btn btn-primary"   ng-click="progressBar();">Next</button></br></br>
    						</div>
    						
                			  <div class="form-group" ng-show="show==2" >
                		       <span style="color:red">{{msgerr}}</span>
       							<p>Year entering college</p><select name="KidCollegeYear"  class="form-control" style="width:40%; margin-left: auto;    margin-right: auto;" ng-model="formdata.KidCollegeYear"><option ng-repeat="item in items" value="{{item}}">{{item}}</option></select>
       							<!-- <input allow-pattern="\d" type="text" placeholder="Bangalore"  name="KidCollegeYear" ng-model="formdata.KidCollegeYear" ><span id="ad1"></span> --></br></br>
       							 
       							<button type="button" class="btn btn-primary"  ng-click="progressBar0();">Next</button></br></br>
    						</div>
   							<span></span>
        					 <!-- <div class="form-group" ng-show="show==2" >
       							<p>What is your annual pre-tax income?</p> <input type="text" name="middlename"  placeholder="B" ng-model="formdata.firstname"></br></br>
       							 <input type="hidden" name="firstname"><button type="button" class="btn btn-primary" onclick="progress_bar1()" ng-click="progressBar1()">Next</button></br></br>
   							 </div> -->
    							 <span></span>
        					 <div class="form-group" ng-show="show==3" >
        					 <span style="color:red">{{msgerr2}}</span>
      							 <p>College Type</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
        						<!-- <input type="text" name="lastname" allow-pattern="\d" placeholder="$20000" ng-model="formdata.down_payment"> -->
        						<select name="CollegeType" class="form-control" style="width:40%; margin-left: auto;    margin-right: auto;" ng-model="formdata.CollegeType"><option  ng-repeat="item in CollegeType" value="{{item.name}}">{{item.name}}</option></select></br></br>
       							<button type="button" class="btn btn-primary"  ng-click="progressBar1()">Next</button></br></br>
   							 </div>
                			 
   							 <div class="form-group" ng-show="show==4" >
                			 <span style="color:red">{{msgerr3}}</span>
      							 <p>Real College Cost Increase Over Time</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
        						<div class="input-prepend" >
 									<span class="percent-fieldmodel" >
        						<input type="text" name="PercentageCollegeCost" maxlength="4" onkeypress="return isNumberKey(event,this)" placeholder="200" ng-model="formdata.PercentageCollegeCost">
        						
        						</span>
        						
        						</div><br>
        						<input type=hidden ng-pattern="/^[1-9][0-9]{0,2}(?:,?[0-9]{3}){0,3}(?:\.[0-9]{1,2})?$/"  ng-model="formdata.actionHomeType" value="form1" ng-init="formdata.actionHomeType='insert'">
       							<br><br>
       							
       							<button type="button" class="btn btn-primary" onclick="progress_bar3()" ng-click="progressBar4()" >Next</button></br></br>
       							
   							 </div>
   							  <div class="form-group" ng-show="show==5" >
                			 <span style="color:red">{{msgerr3}}</span>
      							 <p>College Cost Per Year In Current Year Dollars</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
        						<div class="input-prepend" >
 									<span class="money-fieldmodel" >
        						<input type="text" name="RealAnnualCollegeCostOverTime" maxlength="12" allow-pattern="\d" placeholder="200000" ng-model="formdata.RealAnnualCollegeCostOverTime">
        						
        						</span>
        						
        						</div><br>
        						<input type=hidden ng-model="formdata.actionHomeType" value="form1" ng-init="formdata.actionHomeType='insert'">
       							<br>
       							
       							<button type="button" class="btn btn-primary" onclick="progress_bar3()" ng-click="progressBar5()" >Next</button></br></br>
       							
   							 </div>
   							 <div class="form-group" ng-show="show==6" >
                			 <span style="color:red">{{msgerr3}}</span>
      							 <p>How much do you plan on supporting?</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
        						<div class="input-prepend" >
 									<span class="percent-fieldmodel">
        						<!--<input type="text" name="CollegeEducationAmountPercentage" maxlength="12" allow-pattern="\d" placeholder="20%" ng-model="formdata.CollegeEducationAmountPercentage">-->
        						
        						<select ng-model="formdata.CollegeEducationAmountPercentage" style="width:40%; margin-left: auto; margin-right: auto;" class="form-control">
                   				<option ng-selected="CollegeEducationAmountPercentage.name == formdata.CollegeEducationAmountPercentage" ng-repeat="CollegeEducationAmountPercentage in CollegeEducationAmountPercentages" ng-value="CollegeEducationAmountPercentage.name" >{{ CollegeEducationAmountPercentage.name }}</option>
                     		 	</select>
                     		 	</span>
        						</div>
        						<input type=hidden ng-model="formdata.actionHomeType" value="form1" ng-init="formdata.actionHomeType='insert'">
       							<br>
       							<button type="button" class="btn btn-primary" onclick="progress_bar3()" ng-click="progressBar3()" >Next</button></br></br>
   							 </div>
   					
   							 <!--  <div class="form-group" ng-show="show==6" >
                			 <span style="color:red">{{msgerr3}}</span>
      							 <p>Real Annual College Cost Over Time</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
        						<div class="input-prepend" >
 									<span class="percent-fieldmodel">
        						<input type="text" name="CollegeEducationAmountPercentage" maxlength="12" allow-pattern="\d" placeholder="20%" ng-model="formdata.CollegeEducationAmountPercentage">
        						
        						<select ng-model="formdata.RealAnnualCollegeCostOverTime" style="width:40%; margin-left: auto; margin-right: auto;" class="form-control">
                   				<option ng-selected="RealAnnualCollegeCostOverTime.name == formdata.RealAnnualCollegeCostOverTime" ng-repeat="RealAnnualCollegeCostOverTime in RealAnnualCollegeCostOverTime" ng-value="0" >{{0}}</option>
                     		 	</select>
                     		 	</span>
        						</div>
        						<input type=hidden ng-model="formdata.actionHomeType" value="form1" ng-init="formdata.actionHomeType='insert'">
       							<br>
       							
       							<button type="button" class="btn btn-primary" onclick="progress_bar3()" ng-click="progressBar4()" >Next</button></br></br>
       						
   							 </div> -->
   							<!--  <div class="form-group" ng-show="show==6" >
                			 <span style="color:red">{{msgerr3}}</span>
      							 <p>Real annual college cost over time</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
        						<div class="input-prepend" >
 									<span class="percentage-fieldmodel" >
        						<input type="text" name="" maxlength="12" allow-pattern="\d"  ng-value="0" ng-model="">
        						</span>
        						</div>       							
       							<button type="button" class="btn btn-primary" onclick="progress_bar3()" ng-click="progressBar3()" >Next</button></br></br>
       							
   							 </div> 
   							  -->
   							 <div class="form-group" ng-show="show==7" >
       							<span style="color:red; font-size: 90%;">{{msgerr}}</span>	
       							 <p>  Do you have an 529 plan?</p>
  		            			<div class="radio">
                					<label>
                   							 <input type="radio" name="radio1"  ng-value="false" ng-click="yes1()" ng-model="formdata.plan529demo" >
                								Yes
               						 </label>
                     				 <label>
                              				<input type="radio" name="radio1" ng-value="true" ng-click="no1()"  ng-model="formdata.plan529demo"/>
                							No
                					</label>
          					  </div>
                        	<div style="font-size:9px;" ng-hide="plan529Yes"  >	 
                        	<span class="money-fieldmodel1" >
                        	
    						<span class="font-para-black">  
	           					 How much do you want to deduct from your 529 plan yearly?  
                  			</span>   
                  			       
                  			         
                  				<input  type="text" maxlength="8" style="width:131px; height:30px; font-size:18px;" onkeypress="return onlyNumbers(event,this)" min="0"  placeholder=""  class="input_bl-long"  ng-model="formdata.plan529Saved"  />
   							   </span>
   							 </div>
   								<div style="font-size:9px;" ng-hide=true>	 <!-- "plan529No" -->
    								<span class="font-para-black" >  
	                   				       How much do you want to deduct from your 529 plan?  
                  					</span>                 
                 			 <input type="number"  style="width:131px; height:30px; font-size:18px;" onkeypress="return onlyNumbers(event,this)" min="0"  placeholder="" maxlength="15" class="input_bl-long"  ng-model="formdata.plan529Contribute"  />
   					 </div><br>
      <button type="button" class="btn btn-primary" onclick="progress_bar()"  ng-click="progressBar6()" >Next</button></br></br>
  					
   							 </div> 
   							 </form>
   							 </div>
                			
                			
		            		</div>
           				 	<!-- /.modal-content -->
        				</div>
       					 <!-- /.modal-dialog -->
       					 </div>
    				<!-- /.modal -->
    						<div class="right_content">
								</br>
								<div class=" text-center" >
								<i class="fa fa-home fa-5x"></i></br></br>
								<p>The annual expense for college education is $ {{collegeEducationAmount}}</p>
 								
 								<table class="table" >
 								<tr>
 								<td>Year entering college</td><td> {{kidCollegeYear}}</td></tr>
 								<tr><td>College Type</td><td> {{ collegeType }}</td></tr>
 								<tr><td>College cost each year</td><td>$ {{ collegeEducationAmount }}</td></tr>
<!--  								<tr><td>Real Annual College Cost Over Time</td><td>$ {{ RealAnnualCollegeCostOverTime }}</td></tr>
 --> 								<tr><td>Year of graduation</td><td> {{kidCollegeYear + 4}}</td></tr>
 								</table>
 						  </div>
 						</div>
                    </div>
                  </div>
               </div>
               </div>
             <!--    <div class="col-lg-10 col-lg-offset-1 text-center">
              <input id="submit-button" type="submit" value="Submit my form" class="btn btn-default" />
                <a href="#services" style="background-color: #E65319;" class="btn btn-primary btn-xl page-scroll ">Next</a> 
					</div>-->
					 <div id="checkSession" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Status</h4>
            </div>
            <div class="modal-body">
                <p>{{errMessage}}</p>
               </div>
            <div class="modal-footer">
                <button type="button"  ng-click="check1()" class="btn btn-default" data-dismiss="modal">Close</button>
               </div>
        </div>
    </div>
		</div>
			<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Message</h4>
            </div>
            <div class="modal-body">
                <p>{{errmessage}}</p>
               </div>
            <div class="modal-footer">
                <button type="button"  ng-click="check1()" class="btn btn-default" data-dismiss="modal" onclick="window.location.reload()">Close</button>
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
                  <p style="color:white;">Copyright &copy; WealthSetter 2017. All Rights Reserved</p>
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
