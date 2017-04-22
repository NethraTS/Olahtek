var app = angular.module('formApp',[]);
var app1 = angular.module('customizedGoalEdit',[]);
app.directive('allowPattern', [allowPatternDirective]);
app1.directive('allowPattern', [allowPatternDirective]);

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
          			if(keyCodeChar.charCodeAt()==8||keyCodeChar.charCodeAt()==9||keyCodeChar.charCodeAt()==32)
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
app.controller('Goalcar',function($http,$scope){

	$scope.show = 1;
	 $scope.sessionDetails={};
	 $scope.sessionDelete={};
            $scope.formdata={};
            $scope.Goalendingyear1=  [{name:'1'},{name:'2'},{name:'3'},{name:'4'},{name:'5'},{name:'6'},{name:'7'},{name:'8'},{name:'9'},{name:'10'}];
           $scope.formdata.Goalendingyear="1";
            $scope.Goalstartingyear1=  [{name:'2016'},{name:'2017'},{name:'2018'},{name:'2019'},{name:'2020'},{name:'2021'},{name:'2022'},{name:'2023'},{name:'2024'},{name:'2025'},{name:'2026'},{name:'2027'},{name:'2028'},{name:'2029'},{name:'2030'},{name:'2031'},{name:'2032'},{name:'2033'},{name:'2034'},{name:'2035'},{name:'2036'},{name:'2037'},{name:'2038'},{name:'2039'},{name:'2040'},{name:'2041'},{name:'2042'},{name:'2043'},{name:'2044'},{name:'2045'},{name:'2046'}];
            $scope.goalHouseEditData={};
            $scope.customizedData={};
            $scope.message="Goal Name"
            $scope.progressbar=0;
            $scope.formdata.plan_name=(decodeURIComponent(hashes));
            $scope.formdata.creditscore="";
            $scope.formdata.Goalstartingyear="2017";
            $scope.formdata.totalCost="1";
            $scope.masked = false;
            $scope.formdata.Goalcost="2000";
            $scope.formdata.Goalcostmath="";
            //ng-value=true;
     $scope.progressBar=function() {  
    	 if($scope.formdata.Goalname==null||$scope.formdata.Goalname==undefined||$scope.formdata.Goalname==""){
             
			$scope.msgerr="Goal Name should not be empty";
			//$("#ad1").text($scope.msgerr);
 		
         } else{
        	 $('#progress_bar').css('width', '35%');
    	 $scope.message="Goal Start Year";
    		// var email = $('input[name="firstname"]').val();
		
		 $scope.formdata.cprice=$scope.formdata.carprice;
		
    	 //$scope.formdata.location=('#ad').val();
    		 $scope.show = 3;
    	 $scope.show1 = 2;
         }
    	
     }  
     $scope.progressBar0=function() { 
   	  if($scope.formdata.Goalstartingyear==null||$scope.formdata.Goalstartingyear==undefined|| $scope.formdata.Goalstartingyear==""){
             
			$scope.msgerr2="Goal starting year cannot be empty";
 		
         }
   	  else if($scope.formdata.Goalstartingyear==0){
             
   			$scope.msgerr2="Goal starting year cannot be empty!!";
       		
               }
          else {
       	  $('#progress_bar').css('width', '45%');
   	 $scope.message="Goal Cost";
   	$scope.formdata.downPayment=$scope.formdata.down_payment;
   		 $scope.show = 4;
   	 $scope.show1 = 3;
         }
    }   
      $scope.progressBar1=function() { 
          //alert($scope.formdata.totalCost);
if($scope.formdata.totalCost==1)
{
$scope.formdata.totalCost="total";
}
          // alert($scope.formdata.perYear);
    	  if($scope.formdata.totalCost==null||$scope.formdata.totalCost==undefined|| $scope.formdata.totalCost==""){
              
			$scope.msgerr2="total Cost should not be empty";
  		
          }else if($scope.formdata.Goalcost==null||$scope.formdata.Goalcost==undefined|| $scope.formdata.Goalcost==""){
        	  $scope.msgerr2="Goal Cost should not be empty";

        	  if($scope.formdata.totalCost=="total")
        		{
        		$scope.formdata.totalCost=1;
        		}
        	  else
        	  {
          		$scope.formdata.totalCost="peryear";
          		}
               }
    	  else if($scope.formdata.Goalcost==0){
    			$scope.msgerr2="Goal Cost cannot be zero !!";
                }
           else {
        	  // alert($scope.radio.totalCost);
        	  $('#progress_bar').css('width', '70%');
    	 $scope.message="Goal ending year";
    	$scope.formdata.downPayment=$scope.formdata.down_payment;
    		 $scope.show = 5;
    	 $scope.show1 = 4;
          }
     }   
     $scope.progressBar2=function() {  
    	 //alert("22");
    	 $scope.masked = true;
    	// alert("iii");
    	 //alert("$scope--->"+$scope.formdata.Goalendingyear);
    	 if($scope.formdata.Goalendingyear==null||$scope.formdata.Goalendingyear==undefined||$scope.formdata.Goalendingyear==""){
    		 $scope.masked = false;
			$scope.msgerr3=" Goal ending year should not be empty";
			//$scope.isDisabled=false;
			//disable();
 		
         } else{
        	 $scope.masked = true;
        	// $scope.formdata.Goalendingyear=$scope.formdata.Goalstartingyear + $scope.formdata.Goalendingyear;
        	// alert($scope.formdata.Goalendingyear);
        	//$scope.errmessage="Your Goal is successfully created and you can add starting year of your goal in advanced!!!"; 
			  //$("#myModal").modal('show');
			  //alert($scope.formdata.Goalendingyear);
        	 $scope.formdata.Goalendingyear=$scope.formdata.Goalendingyear;
        	 $('#progress_bar').css('width', '100%');
        	$('#dialog_confirm_map').modal('hide');
    	    $scope.message="DETAILS";
    	
    	//$scope.formdata.tperiod=$scope.formdata.selectedItem;
    	$scope.formdata.intrestrate=0;
    	
    	
    	// $scope.formdata.Goalendingyear=$scope.formdata.Goalendingyear;
    		// $scope.show =6;
    	   $scope.show1 = 5;
    	 $scope.CurrentDate = new Date().getFullYear();
    	 //$scope.formdata.Goalstartingyear=$scope.CurrentDate;
			
			$http({
	  		  method: 'POST',
	  		  url: 'CustomizedGoal',
	  		  data: $.param($scope.formdata),
	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
				}).then(function(result) {
					$scope.goalHouseEditData=result.data;
		 			$scope.formdata.Goalname=$scope.goalHouseEditData.Goalname;
					//$scope.formdata.Goalcost=$scope.goalHouseEditData.Goalcost;
					//$scope.formdata.Goalendingyear=$scope.goalHouseEditData.Goalendingyear;
					$scope.formdata.Goalstartingyear=$scope.goalHouseEditData.Goalstartingyear;
					$scope.Goalname=result.data.Goalname;
					//alert(result.data.favoriteColors);
                 if(result.data.favoriteColors=="total"){
					$scope.Goalcost=result.data.Goalcost;//alert($scope.Goalcost);
					$scope.Goalcost1=$scope.formdata.Goalcost;//alert($scope.Goalcost1);
                     } else {
                    	$scope.Goalcost=$scope.formdata.Goalcost;//alert($scope.Goalcost);
     					$scope.Goalcost1=result.data.Goalcost*$scope.formdata.Goalendingyear;//alert($scope.Goalcost1);
                                           
                        } 
					
					$scope.Goalendingyear=result.data.Goalendingyear;
					$scope.Goalstartingyear=result.data.Goalstartingyear;
					
				
				if($scope.goalHouseEditData.status=="success")
					{
			$scope.formdata.goal_id = $scope.goalHouseEditData.goal_id; // assign value to goal id
		/* 	$scope.errmessage="Goal created successfully !!"; 
		  $("#myModal").modal('show');
	 */	  window.location.href="CustomizedGoalEdit.jsp?goalId="+$scope.formdata.goal_id;

			console.log("message"+$scope.message);
			 $scope.masked = false;
					}
				else
					{
					 $scope.masked = false;
					 $scope.errmessage="Goal is not feasible since you are not having sufficient funds !!";
	            	  $("#myModal").modal('show');

					//alert();
					
					}
	  		 }, function(error) {
		  $scope.message=result.data;
		  
	  		 });
		

         }
    	 //$scope.message="DETAILS";
         }
    
      

     //var n=0;

     
 
			
			/* if(!$scope.formData1.country || !$scope.formData1.city || !$scope.formData1.uage || !$scope.formData1.married){
				   $scope.errmessage="Please Enter your basic details.....";
			  window.location.href="#about";
 			  $("#myModal").modal('show');}*/
 			 
		
	

     $scope.checkform1=function(){
    	//alert("g2");
 		        window.location.href="#";
 		      
 		          if($scope.formdata.Goalname==null||$scope.formdata.Goalname==undefined||$scope.formdata.Goalname=="")
     		         {
 		     	       $scope.errmessage="Please Enter The Goal Name!!!";
					       $("#myModal").modal('show');
     		         }
 		          else if($scope.formdata.totalCost==null||$scope.formdata.totalCost==undefined|| $scope.formdata.totalCost==""){
 	                  
 		   			$scope.msgerr2="total Cost should not be empty";
 		       		
 		               }
 		        else if($scope.formdata.Goalcost==null||$scope.formdata.Goalcost==undefined||$scope.formdata.Goalcost=="")
		         {
	     	        $scope.errmessage="Please Enter The Goal Cost!!!";
			        $("#myModal").modal('show');
		         }else if($scope.formdata.Goalcost==0)
		         {
			     	   $scope.errmessage="Goal Cost cannot be zero !!";
					  $("#myModal").modal('show');
	    		         }
                   
  		        /*  else if($scope.formdata.Goalstartingyear > $scope.formdata.Goalendingyear){
		        	 $scope.errmessage="Goal starting year cannot be greater than Goal ending year!!!";
					  $("#myModal").modal('show');
    		         } */
 		    
 		         else{  
$scope.formdata.actionHomeType="update";

//alert($scope.formdata);
$http({
	  method: 'POST',
	  url: 'CustomizedGoal',
	  data: $.param($scope.formdata),
	  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
	}).then(function(result) {
		$scope.goalHouseEditData=result.data;
 			$scope.formdata.Goalname=$scope.goalHouseEditData.Goalname;
			//$scope.formdata.Goalcost=$scope.goalHouseEditData.Goalcost;
			//$scope.formdata.Goalendingyear=$scope.goalHouseEditData.Goalendingyear;
			$scope.formdata.Goalstartingyear=$scope.goalHouseEditData.Goalstartingyear;

			$scope.Goalname=result.data.Goalname;
			//alert(result.data.favoriteColors);

            if(result.data.favoriteColor=="total"){

				$scope.Goalcost=result.data.Goalcost;
               // alert($scope.Goalcost);
				$scope.Goalcost1=$scope.formdata.Goalcost;
               // alert($scope.Goalcost1);
               }else {
                   
               	$scope.Goalcost=$scope.formdata.Goalcost;//alert($scope.Goalcost);
					$scope.Goalcost1=result.data.Goalcost*$scope.formdata.Goalendingyear;//alert($scope.Goalcost1);                 
                   }
			$scope.Goalendingyear=result.data.Goalendingyear;
			$scope.Goalstartingyear=result.data.Goalstartingyear;
		
	console.log("message"+$scope.message);

	 }, function(error) {
$scope.message=result.data;

	 });
//$scope.errmessage="Goal updated successfully !!";
//$("#myModal").modal('show');
		}
  
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
 							//	alert("hello");
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
//-----------------------------------------------------------------------------------------------------
app1.controller('customizedGoalEdit',function($scope,$http){
	
    $scope.formdata={};
    $scope.sessionDetails={};
	 $scope.sessionDelete={};
	 $scope.masked = false;
    //$scope.items = ['2020','2021','2022','2023'];
    //$scope.CurrentDate = new Date().getFullYear();
    $scope.Goalstartingyear1=  [{name:'2016'},{name:'2017'},{name:'2018'},{name:'2019'},{name:'2020'},{name:'2021'},{name:'2022'},{name:'2023'},{name:'2024'},{name:'2025'},{name:'2026'},{name:'2027'},{name:'2028'},{name:'2029'},{name:'2030'},{name:'2031'},{name:'2032'},{name:'2033'},{name:'2034'},{name:'2035'},{name:'2036'},{name:'2037'},{name:'2038'},{name:'2039'},{name:'2040'},{name:'2041'},{name:'2042'},{name:'2043'},{name:'2044'},{name:'2045'},{name:'2046'}];
  //  $scope.DateDiff=
   // $scope.CollegeType=['in-state public college','out-of-state public college','private college'];
   $scope.Goalendingyear1=  [{name:'1'},{name:'2'},{name:'3'},{name:'4'},{name:'5'},{name:'6'},{name:'7'},{name:'8'},{name:'9'},{name:'10'}];
    $scope.formdata.KidCollegeYear="";
    $scope.goalHouseEditData={};
    $scope.goalcost1="";
    $scope.planName="";
    $scope.formdata.totalCost="total";
$scope.load1= function() {
	 $scope.masked = true;
$("#success-alert").hide();
$("#fail-alert").hide(); 	

$scope.formdata.goal_id=(decodeURIComponent(hashes));

$scope.formdata.actionHomeType="edit";  
	
 			$http({
 			    method: 'POST',
 			    url: 'CustomizedGoal',
 			    data: $.param($scope.formdata),
 			    headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
 			}).then(function(result) {
 				$scope.goalHouseEditData=result.data;
 				if(result.data.goalFeasiblity==false)
					{
					//alert();
 					 $scope.masked = false;
					$("#fail-warning").show();
					}
 				
 				
 				//alert(JSON.stringify($scope.goalHouseEditData));
 				$scope.goalcost1=Math.ceil($scope.goalHouseEditData.Goalcost);
 	
 				
 				$scope.goal_id=	$scope.goalHouseEditData.goal_id;
 	 			$scope.formdata.Goalname=$scope.goalHouseEditData.Goalname;
 				
 				$scope.endYearB=($scope.goalHouseEditData.Goalendingyear-$scope.goalHouseEditData.Goalstartingyear);
 				$scope.formdata.Goalendingyear=$scope.goalHouseEditData.Goalendingyear-$scope.goalHouseEditData.Goalstartingyear;
 			
 				$scope.formdata.Goalstartingyear=$scope.goalHouseEditData.Goalstartingyear;
 				$scope.formdata.totalCost=$scope.goalHouseEditData.typeOfGoalCost;
               //  $scope.formdata.totalCost=result.data.favoriteColors;
              //  alert("r u mental=developer=="+ $scope.formdata.totalCost);
             
                 $scope.formdata.Goalcost=$scope.goalHouseEditData.Goalcost*$scope.formdata.Goalendingyear;
                  
                 //   $scope.formdata.Goalcost=$scope.goalHouseEditData.Goalcost;
               
                
				
                 $scope.planName=result.data.plan_name;//alert($scope.planName);
 				$scope.Goalname=result.data.Goalname;
 				//alert(result.data.favoriteColors)
                    
                 if(result.data.typeOfGoalCost=="total"){

     				$scope.Goalcost=result.data.Goalcost;
  //alert($scope.Goalcost);
     				$scope.Goalcost1=$scope.formdata.Goalcost;
     				
// alert($scope.Goalcost1);
                    }else {
                        
                    	$scope.Goalcost=$scope.formdata.Goalcost;//alert($scope.Goalcost);
     					$scope.Goalcost1=result.data.Goalcost*$scope.formdata.Goalendingyear;//alert($scope.Goalcost1);                 
                        }
 				$scope.Goalendingyear=result.data.Goalendingyear;
 				$scope.Goalstartingyear=result.data.Goalstartingyear;
 				
 				$scope.errorName='';
 				$scope.errorSuperhero='';
 				if($scope.formdata.totalCost=="peryear")
           	 {
 					$scope.formdata.Goalcost=$scope.formdata.Goalcost/$scope.formdata.Goalendingyear;
           	 }   		
 				 $scope.masked = false;
 			}, function(error) {
 				   $scope.message=result.data;
 				   $scope.errorName='';
 					$scope.errorSuperhero='';
 			   });
 			 $scope.msgerr="";
 		} 
//---------Success modal alert hide------------------------------------------
$scope.hideSuccess=function()
{
$("#success-alert").hide();

}
$scope.hideFail=function()
{
$("#fail-alert").hide();

}
//-----------------------------------------------------------------------
//---------------------------------------logic for back button-------------------------

$scope.goDashboard=function()
{
window.location.href="dashboardUser0.jsp?finName="+ $scope.planName;
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


$scope.backwithoutsave=function(formdata)

{
	if($scope.totalcost=="total")
	{
		
		$scope.goalHouseEditData.Goalcost=$scope.goalHouseEditData.Goalcost*$scope.endYearB;
	}

	//$scope.endYearB= parseInt($scope.formdata.Goalendingyear)+parseInt($scope.formdata.Goalstartingyear);
//console.log(formdata);
//console.log($scope.goalHouseEditData)
//console.log($scope.endYearB);
	if($scope.goalHouseEditData.Goalname!=formdata.Goalname ||  $scope.goalHouseEditData.Goalstartingyear!=formdata.Goalstartingyear ||$scope.goalHouseEditData.Goalcost!=formdata.Goalcost||$scope.endYearB!=parseInt(formdata.Goalendingyear)||$scope.goalHouseEditData.typeOfGoalCost!=formdata.totalCost)
	{

		$('#myModalback').modal('show');

	}
	
else
	{
	
	$scope.goDashboard();
	}

	}
$scope.hideWarning=function()
{
	 $("#fail-warning").hide();
	
	 
}

$scope.DeleteGoal=function()
	{ 
		
		$('#myModal1').modal('show')
		}
//----------------------------------------------------------
	$scope.deletegoal=function(){
		$scope.masked = true;
		//alert("delete--->>>");
		$scope.formdata.actionHomeType="deleteGoal";
		
		$scope.formdata.plan_name=$scope.planName;
		
		$scope.formdata.goal_id=$scope.goal_id;
		
		
		$http({
	  		  method: 'POST',
	  		 // url: 'CopyPlan',
	  		  url : 'CustomizedGoal',
	  		  data: $.param($scope.formdata),
	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
				}).then(function(result) {
				$scope.goDashboard();
				$scope.message1=result.data.status;
				//alert($scope.message1);
				$scope.masked = false;
					

	  		 }, function(error) {
	  			$scope.message=result.data.status;
		  
	  		 }); 

	}
	
$scope.checkform1=function(){
	 //alert("2");
	$scope.masked = true;
//	alert("g1");
			  //window.location.href="#";
if($scope.formdata.Goalname==null||$scope.formdata.Goalname==undefined||$scope.formdata.Goalname==""){
	$scope.masked = false;
			       $scope.errmessage1="Goal name is empty";
			       $("#fail-alert").show();  
			       					 }
else if($scope.formdata.totalCost==null||$scope.formdata.totalCost==undefined|| $scope.formdata.totalCost==""){
	$scope.masked = false;
		$scope.msgerr2="total Cost should not be empty";
	
    }
	 else  if($scope.formdata.Goalcost==null||$scope.formdata.Goalcost==undefined||$scope.formdata.Goalcost==""){
		 $scope.masked = false;	      				       
		 $scope.errmessage1="Goal cost is empty";
			      				     $("#fail-alert").show();  
			    				       					 }
	 else if($scope.formdata.Goalcost==0)
  {
		 $scope.masked = false;
  	   $scope.errmessage1="Goal Cost cannot be zero !!";
  	  $("#fail-alert").show();  
	         }
	 /* else if($scope.formdata.Goalstartingyear > $scope.formdata.Goalendingyear){
 	 $scope.errmessage="Goal starting year cannot be greater than Goal ending year !!!";
		  $("#myModal").modal('show');
      } */
	 else{
		// alert("kkkkkk")
$scope.formdata.actionHomeType="update";
//alert(JSON.stringify($scope.formdata));
$http({
		  method: 'POST',
		  url: 'CustomizedGoal',
		  data: $.param($scope.formdata),
		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).then(function(result) {
			$scope.goalHouseEditData=result.data;
			$scope.customizedData=result.data
			if(result.data.goalFeasiblity==false)
			{
			//alert();
				$scope.masked = false;
			$("#fail-warning").show();
			}
/* 	 		
			$scope.formdata.Goalname=$scope.goalHouseEditData.Goalname;

			$scope.formdata.Goalstartingyear=$scope.goalHouseEditData.Goalstartingyear;
			// $scope.planName=result.data.plan_name;//alert($scope.planName);
			$scope.Goalname=result.data.Goalname;
			//alert(result.data.favoriteColors); */
			$scope.formdata.Goalname=$scope.goalHouseEditData.Goalname;
 				$scope.formdata.Goalcost=$scope.goalHouseEditData.Goalcost;

 				$scope.endYearB=($scope.goalHouseEditData.Goalendingyear-$scope.goalHouseEditData.Goalstartingyear);
 				$scope.formdata.Goalendingyear=$scope.goalHouseEditData.Goalendingyear-$scope.goalHouseEditData.Goalstartingyear;
 				$scope.formdata.Goalstartingyear=$scope.goalHouseEditData.Goalstartingyear;
 				$scope.totalcost=$scope.goalHouseEditData.favoriteColors;
                $scope.formdata.totalCost=	$scope.customizedData.favoriteColors;
              
                 $scope.planName=result.data.plan_name;//alert($scope.planName);
 				$scope.Goalname=result.data.Goalname;

/*                 if(result.data.favoriteColors=="total"){

				$scope.Goalcost=result.data.Goalcost;
                  
				$scope.Goalcost1=$scope.formdata.Goalcost;
                  
            }else {
                
            	$scope.Goalcost=$scope.formdata.Goalcost*$scope.formdata.Goalendingyear;//alert($scope.Goalcost);
					$scope.Goalcost1=result.data.Goalcost*$scope.formdata.Goalendingyear;//alert($scope.Goalcost1);                 
                } */
			$scope.Goalendingyear=result.data.Goalendingyear;

			//alert("final value"+ $scope.formdata.totalCost);
			$scope.Goalstartingyear=result.data.Goalstartingyear;
			//---------Success modal alert------------------------------------------
			if(result.data.status=="success")
				{
				window.location.reload();
				$("#fail-alert").hide();
				$scope.masked = false;
			$scope.errmessage=" Goal updated successfully";			
			$("#success-alert").show();
						$("#success-alert").fadeTo(2000, 300).slideUp(300, function(){
		                	$("#success-alert").hide();
		   
		                });
						
				}
			else
				{
				
				 
				$scope.masked = false;
				 $scope.errmessage="Goal is not feasible since you are not having sufficient funds !!";
         	  $("#myModal").modal('show');
				}
						//-----------------------------------------------------------------------
			/* $scope.goalHouseEditData=result.data;
			$scope.formdata.Goalname=$scope.goalHouseEditData.Goalname;
			$scope.formdata.Goalcost=$scope.goalHouseEditData.Goalcost;
			//$scope.formdata.Goalendingyear=$scope.goalHouseEditData.Goalendingyear;

			$scope.Goalname=result.data.Goalname;
			$scope.Goalcost=result.data.Goalcost;
			$scope.Goalendingyear=result.data.Goalendingyear;
			$scope.formdata.Goalendingyear=result.data.Goalendingyear-result.data.Goalstartingyear;
			$scope.formdata.Goalstartingyear=$scope.goalHouseEditData.Goalstartingyear;
				$scope.Goalstartingyear=result.data.Goalstartingyear; */
		//$scope.goalHouseData=result.data;
		console.log("message"+$scope.message);
		$scope.masked = false;

		 }, function(error) {


		 });
// $scope.errmessage="Goal updated successfully !!";
//  $("#myModal").modal('show');
			}
    
}

$scope.reload=function()
{
	
window.location.href=window.location.href.slice(0, -1);

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

