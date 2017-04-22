var app = angular.module('KidGoal',[]);
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

app.controller('GoalKid',function($http,$scope){
		$scope.show = 2;
		 $scope.date=new Date();
		 $scope.sessionDetails={};
		 $scope.sessionDelete={};
		 $scope.defaultCost=0;
		 $scope.overrideFlag=0;
		 $scope.formdata={};
            	//$scope.formdata.goalCost=8890;
                $scope.Goalendingyear1= [{name:$scope.date.getFullYear()},{name:$scope.date.getFullYear()+1},{name:$scope.date.getFullYear()+2},
                                         {name:$scope.date.getFullYear()+3},{name:$scope.date.getFullYear()+4},{name:$scope.date.getFullYear()+5},
                                         {name:$scope.date.getFullYear()+6},{name:$scope.date.getFullYear()+7},{name:$scope.date.getFullYear()+8},
                                         {name:$scope.date.getFullYear()+9} ,{name:$scope.date.getFullYear()+10},{name:$scope.date.getFullYear()+11},{name:$scope.date.getFullYear()+12},
                                         {name:$scope.date.getFullYear()+13},{name:$scope.date.getFullYear()+14},{name:$scope.date.getFullYear()+15},{name:$scope.date.getFullYear()+16},
                                         {name:$scope.date.getFullYear()+17},{name:$scope.date.getFullYear()+18},{name:$scope.date.getFullYear()+19},{name:$scope.date.getFullYear()+20},
                                         {name:$scope.date.getFullYear()+21},{name:$scope.date.getFullYear()+22},{name:$scope.date.getFullYear()+23},
                                         {name:$scope.date.getFullYear()+24},{name:$scope.date.getFullYear()+25},{name:$scope.date.getFullYear()+26},{name:$scope.date.getFullYear()+27},
                                         {name:$scope.date.getFullYear()+28},{name:$scope.date.getFullYear()+29},{name:$scope.date.getFullYear()+30}];
                
                $scope.Goalstartingyear1= [{name:$scope.date.getFullYear()},{name:$scope.date.getFullYear()+1},{name:$scope.date.getFullYear()+2},
                                           {name:$scope.date.getFullYear()+3},{name:$scope.date.getFullYear()+4},{name:$scope.date.getFullYear()+5},
                                           {name:$scope.date.getFullYear()+6},{name:$scope.date.getFullYear()+7},{name:$scope.date.getFullYear()+8},
                                           {name:$scope.date.getFullYear()+9} ,{name:$scope.date.getFullYear()+10},{name:$scope.date.getFullYear()+11},{name:$scope.date.getFullYear()+12},
                                           {name:$scope.date.getFullYear()+13},{name:$scope.date.getFullYear()+14},{name:$scope.date.getFullYear()+15},{name:$scope.date.getFullYear()+16},
                                           {name:$scope.date.getFullYear()+17},{name:$scope.date.getFullYear()+18},{name:$scope.date.getFullYear()+19},{name:$scope.date.getFullYear()+20},
                                           {name:$scope.date.getFullYear()+21},{name:$scope.date.getFullYear()+22},{name:$scope.date.getFullYear()+23},
                                           {name:$scope.date.getFullYear()+24},{name:$scope.date.getFullYear()+25},{name:$scope.date.getFullYear()+26},{name:$scope.date.getFullYear()+27},
                                           {name:$scope.date.getFullYear()+28},{name:$scope.date.getFullYear()+29},{name:$scope.date.getFullYear()+30}];
                $scope.goalHouseEditData={};
                $scope.message="Start Year"
                $scope.progressbar=0;
                $scope.formdata.plan_name=(decodeURIComponent(hashes));
                $scope.formdata.creditscore="";
                $scope.formdata.startYear=$scope.date.getFullYear();
                $scope.masked = false;
                
                
         $scope.progressBar=function() {  
        	 //alert("progressBar");
        	 $scope.msgerr="";
        	 if($scope.formdata.Goalname==null||$scope.formdata.Goalname==undefined||$scope.formdata.Goalname=="")
        	
        	 {
                 
 			$scope.msgerr="Please enter the name of the goal";
 			//$("#ad1").text($scope.msgerr);
     		
             } else
             	{
            	 $('#progress_bar').css('width', '50%');
        	 $scope.message="Goal Cost";
        		// var email = $('input[name="firstname"]').val();
    		
    		 $scope.formdata.cprice=$scope.formdata.carprice;
    		
        	 //$scope.formdata.location=('#ad').val();
        		 $scope.show = 2;
        	 $scope.show1 = 2;
             }
        	
         }  
          $scope.progressBar1=function() { 
        	  //alert("progressBar1");
        	  $scope.msgerr="";
        	  if($scope.formdata.startYear==null||$scope.formdata.startYear==undefined|| $scope.formdata.startYear==""){
                  
        		  $scope.msgerr="Goal start year should not be empty !!";
      		
              }
               else {
            	 /* $('#progress_bar').css('width', '50%');
        	 $scope.message="Goal ending year";
        	$scope.formdata.downPayment=$scope.formdata.down_payment;
        		 $scope.show = 3;
        	 $scope.show1 = 3;*/
            
        	  
        	  $scope.formdata.actionHomeType="startyear";
        	  $scope.formdata.startYear=$scope.formdata.startYear;
        	  

        	  $http({
        	  		  method: 'POST',
        	  		  url: 'KidGoal',
        	  		  data: $.param($scope.formdata),
        	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
        	  		}).then(function(result) {
        	  	//alert(JSON.stringify(result.data));
        	  	 $('#progress_bar').css('width', '50%');
            	 $scope.message="Goal ending year";
            	 
            	$scope.formdata.downPayment=$scope.formdata.down_payment;
            	
            	$scope.formdata.goalCost=Math.ceil(result.data.finalCostToreturn/12);
            	$scope.defaultCost=Math.ceil(result.data.finalCostToreturn/12);
            		 $scope.show = 3;
            	 $scope.show1 = 3;
        	  		 }, function(error) {
        	   $scope.message=result.data;
        	   
        	  		 });
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
         $scope.progressBar2=function() {  
        	 //alert("progressBar2");
        	 $scope.masked = true;
        	 
        	 if($scope.formdata.goalCost==null||$scope.formdata.goalCost==undefined|| $scope.formdata.goalCost==""){
        		 $scope.masked = false;
       			$scope.msgerr2="Goal Cost should not be empty !!";
           		
                   }
             	  else if($scope.formdata.goalCost==0){
             		 $scope.masked = false;
             			$scope.msgerr2="Goal Cost cannot be zero !!";
                 		
                         }
             else{
            	 $scope.masked = true;
            	 $scope.errmessage="Your Goal is successfully created and you can add starting year of your goal in advanced!!!"; 
				 
            	 $('#progress_bar').css('width', '100%');
            	 $('#dialog_confirm_map').modal('hide');
        	    $scope.message="DETAILS";
        	 	
        	   // $scope.formdata.goalCost=Math.ceil($scope.formdata.goalCost*1);
        	    if($scope.defaultCost!=$scope.formdata.goalCost) {
        	    	$scope.overrideFlag=1;
        	    }
        	    $scope.formdata.overrideFlag = $scope.overrideFlag;
        	//$scope.formdata.tperiod=$scope.formdata.selectedItem;
        	$scope.formdata.intrestrate=0;
        	
        	$scope.formdata.actionHomeType="insert";
        	
        		 $scope.show =5;
        	 $scope.show1 = 4;
        	 $scope.CurrentDate = new Date().getFullYear();
        	 $scope.formdata.Goalstartingyear=$scope.CurrentDate;
 			
 			$http({
 	  		  method: 'POST',
 	  		  url: 'KidGoal',
 	  		  data: $.param($scope.formdata),
 	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
 				}).then(function(result) {
 					$scope.goalHouseEditData=result.data;
 					//alert("before edit   "+JSON.stringify(result.data));
 		 			$scope.formdata.Goalname=$scope.goalHouseEditData.Goalname;
 					$scope.formdata.Goalcost=$scope.goalHouseEditData.Goalcost;
 					$scope.formdata.Goalendingyear=$scope.goalHouseEditData.Goalendingyear;
 					$scope.formdata.Goalstartingyear=$scope.goalHouseEditData.Goalstartingyear;

 					$scope.Goalname=result.data.Goalname;
 					$scope.Goalcost=result.data.Goalcost;
 					$scope.Goalendingyear=result.data.Goalendingyear;
 					$scope.Goalstartingyear=result.data.Goalstartingyear;
 					
 					if($scope.goalHouseEditData.status=="success")
 						{
 			$scope.formdata.goal_id = $scope.goalHouseEditData.goal_id; // assign value to goal id
 			/* $scope.errmessage="Goal created successfully !!"; 
			  $("#myModal").modal('show'); */
 			//alert("new goal  "+$scope.formdata.goal_id)
			  window.location.href="KidGoalEdit.jsp?goalId="+$scope.formdata.goal_id;

				console.log("message"+$scope.message);
				 $scope.masked = false;
 						}
 					else
 						{
 						 $scope.masked = false;
 						 $scope.errmessage="Goal is not feasible since you are not having sufficient funds !!";
 		            	  $("#myModal").modal('show');
 						///alert();
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
     		        // alert($scope.formdata.Goalstartingyear);
        	// alert();
     		        window.location.href="#";
     		          if($scope.formdata.Goalname==null||$scope.formdata.Goalname==undefined||$scope.formdata.Goalname=="")
         		         {
     		     	   $scope.errmessage="Please Enter The Goal Name!!!";
 					  $("#myModal").modal('show');
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
    		         else if($scope.formdata.startYear > $scope.formdata.endYear){
    		        	 $scope.errmessage="Goal starting year cannot be greater than Goal ending year !!!";
    					  $("#myModal").modal('show');
    			         }
     		    
     		         else{  
$scope.formdata.actionHomeType="update";



$http({
		  method: 'POST',
		  url: 'KidGoal',
		  data: $.param($scope.formdata),
		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).then(function(result) {
			$scope.goalHouseEditData=result.data;
	 			$scope.formdata.Goalname=$scope.goalHouseEditData.Goalname;
				$scope.formdata.Goalcost=$scope.goalHouseEditData.Goalcost;
				$scope.formdata.Goalendingyear=$scope.goalHouseEditData.Goalendingyear;
				$scope.formdata.Goalstartingyear=$scope.goalHouseEditData.Goalstartingyear;

				$scope.Goalname=result.data.Goalname;
				$scope.Goalcost=result.data.Goalcost;
				
				$scope.Goalendingyear=result.data.Goalendingyear;
				$scope.Goalstartingyear=result.data.Goalstartingyear;
			
		console.log("message"+$scope.message);
		$scope.load();
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
     						
     							window.history.forward();
     							  $scope.sessionDetails.cookieId=readCookie("AhTwxlO");
     							// alert( "on loaddddddddd");
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
     										$scope.load2();

     											
     									
     										
     										
     										
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
     						 $scope.load2=function(){
     							 //alert("hiiii");
     							 

     							$scope.formdata.actionHomeType = "onload";
     							//alert("$scope.fdiConstant.actionType==="+ $scope.fdiConstant.actionType);

     							$http(
     									{
     										method : 'POST',
     										url : 'KidGoal',
     										data : $.param($scope.formdata),
     										headers : {
     											'Content-Type' : 'application/x-www-form-urlencoded'
     										}
     									})
     									.then(
     											function(result) {
     												//alert("sucess")
     												//alert(JSON.stringify(result.data));
     												$scope.formdata.goalCost=result.data.kidGoalDefaultCostPeryear;
     												$scope.formdata.Goalcost=result.data.goalCost;
     												$scope.formdata.Goalendingyear=result.data.startYear;
     												$scope.formdata.Goalstartingyear=result.data.endYear;   												
     												
     												
     												console.log("message"
    														+ $scope.message);

    											}, function(error) {
    												//alert("fail");
    												$scope.message = result.data;

    											});
    						}
     			  
     			  
     			  
     			  
     			  
});