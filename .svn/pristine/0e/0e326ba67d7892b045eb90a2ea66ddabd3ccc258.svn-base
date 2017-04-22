var app = angular.module('KidGoalEdit',[]);
var url = window.location.href;
var hashes = url.split("=")[1];
app.directive('allowPattern', [allowPatternDirective]);

function allowPatternDirective() {
    return {
        restrict: "A",
        compile: function(tElement, tAttrs) {
            return function(scope, element, attrs) {
      
                element.bind("keypress", function(event) {
                    var keyCode = event.which || event.keyCode; 
                    var keyCodeChar = String.fromCharCode(keyCode);
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

app.controller('GoalKid',function($scope,$http){
	
           $scope.formdata={};
           $scope.masked = false;
           $scope.sessionDetails={};
      	 $scope.sessionDelete={};
      	$scope.masked = false;
          
           $scope.Goalstartingyear1=  [{name:'2016'},{name:'2017'},{name:'2018'},{name:'2019'},{name:'2020'},{name:'2021'},{name:'2022'},{name:'2023'},{name:'2024'},{name:'2025'},{name:'2026'},{name:'2027'},{name:'2028'},{name:'2029'},{name:'2030'},{name:'2031'},{name:'2032'},{name:'2033'},{name:'2034'},{name:'2035'},{name:'2036'}];
        
           $scope.Goalendingyear1 = [{name:'2016'},{name:'2017'},{name:'2018'},{name:'2019'},{name:'2020'},{name:'2021'},{name:'2022'},{name:'2023'},{name:'2024'},{name:'2025'},{name:'2026'},{name:'2027'},{name:'2028'},{name:'2029'},{name:'2030'},{name:'2031'},{name:'2032'},{name:'2033'},{name:'2034'},{name:'2035'},{name:'2036'}];
           $scope.formdata.KidCollegeYear="";
           $scope.goalHouseEditData={};
          
 $scope.load1= function() {

	 $scope.masked = true;

	 //alert("jj");
	

	 $scope.formdata.goal_id=(decodeURIComponent(hashes));
	 $("#success-alert").hide();
	 $("#fail-alert").hide(); 
	
	  $scope.formdata.actionHomeType="edit";       		
        			$http({
        			    method: 'POST',
        			    url: 'KidGoal',
        			    data: $.param($scope.formdata),
        			    headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
        			}).then(function(result) {
//alert(JSON.stringify(result.data));
        				if(result.data.goalFeasibility==false)
    					{
    					//alert();
        					$scope.masked = false;
    					$("#fail-warning").show();
    					}
        				$scope.goalHouseEditData=result.data;
        				$scope.planName=result.data.plan_name;
        			
        				$scope.formdata.startYear=$scope.goalHouseEditData.startYear;
        		
        				$scope.formdata.goalCost=$scope.goalHouseEditData.goalCost;
        				$scope.formdata.endYear=$scope.goalHouseEditData.endYear;
        				$scope.startYear=result.data.startYear;
        		
        				$scope.goalCost=result.data.goalCost;
        				$scope.endYear=result.data.endYear;
        				$scope.annualExpense=result.data.annualExpense.toFixed(2);
                        $scope.goal_id=result.data.goal_id
        				
        				
        				$scope.errorName='';
        				$scope.errorSuperhero='';
        				$scope.masked = false;
        			   }, function(error) {
        				   $scope.message=result.data;
        				   $scope.errorName='';
        					$scope.errorSuperhero='';
        			   });
        			 $scope.msgerr="";
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

 
 $scope.checkform1=function(){
				  window.location.href="#";
	 if($scope.formdata.startYear==null||$scope.formdata.startYear==undefined||$scope.formdata.startYear==""){
				       $scope.errmessage1="Goal Cost should not be empty !!";
				       $("#fail-alert").show();  
				       					 }
		 else  if($scope.formdata.goalCost==null||$scope.formdata.goalCost==undefined||$scope.formdata.goalCost==""){
				      				       $scope.errmessage1="Goal cost is empty";
				      				     $("#fail-alert").show();  
				    				       					 }
		 else if($scope.formdata.goalCost==0)
         {
	     	   $scope.errmessage1="Goal Cost cannot be zero !!";
	     	  $("#fail-alert").show();  
		         }
	
		 else{
			 $scope.masked = true;
	 $scope.formdata.actionHomeType="update";
	 $scope.formdata.overrideFlag=1;
	 $http({
	 		  method: 'POST',
	 		  url: 'KidGoal',
	 		  data: $.param($scope.formdata),
	 		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
	 		}).then(function(result) {
	 			//alert(JSON.stringify(result.data));
	 			if(result.data.goalFeasibility==false)
				{
	 			$scope.masked = false;
				$("#fail-warning").show();
				
				}
				$scope.startYear=result.data.startYear;
				$scope.Goalname=result.data.Goalname;
				$scope.goalCost=result.data.goalCost;
				$scope.endYear=result.data.endYear;
				$scope.annualExpense=result.data.annualExpense.toFixed(2);
				//---------Success modal alert------------------------------------------
			if(result.data.status=="success")
				{
				 $("#fail-alert").hide(); 
				$scope.errmessage=" Goal updated successfully";
				$scope.masked = false;
				$("#success-alert").show();
							$("#success-alert").fadeTo(2000, 300).slideUp(300, function(){
			                	
								$("#success-alert").hide();
			                    
			                });   
							//-----------------------------------------------------------------------
				}
			else
				{
				$scope.masked = false;
				 window.location.href="#";
	 				
 				 $scope.errmessage="Goal is not feasible since you are not having sufficient funds !!";
            	  $("#myModal").modal('show');

				
				}
	 		console.log("message"+$scope.message);
	 		$scope.masked = false;
	 		 }, function(error) {
	  
	
	 		 });

	 			}
	       
 }
 $scope.reload=function()
 {
 		
 	window.location.href=window.location.href.slice(0, -1);
 	
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

			  $scope.DeleteGoal=function()
				{ 
					
					$('#myModal1').modal('show');
					}
			  $scope.hideWarning=function()
			  {
			 	 $("#fail-warning").hide();
			 	
			 	 
			  }
			  //----------------for deleting of goals
			  
			  $scope.deletegoal=function(){
				  $scope.masked = true;
					$scope.formdata.actionHomeType="deleteGoal";
					
					$scope.formdata.plan_name=$scope.planName;
				
					$scope.formdata.goal_id=$scope.goal_id;
										
					$http({
				  		  method: 'POST',
				  		  url: 'KidGoal',
				  		  data: $.param($scope.formdata),
				  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
							}).then(function(result) {
							$scope.goDashboard();
							$scope.message1=result.data.status;
							
							$scope.masked =false;

				  		 }, function(error) {
				  			$scope.message=result.data.status;
					  
				  		 }); 

				}
			//---------------------------------------logic for back button-------------------------
				
				$scope.goDashboard=function()
				{
					
				
				
							
					  window.location.href="dashboardUser0.jsp?finName="+ $scope.planName;
							  
					}

			
		 //................update before going back.....................
 
                   $scope.backwithoutsave=function(formdata)
				
				    {
   				   

					if($scope.startYear!=formdata.startYear||$scope.goalCost!=formdata.goalCost)
					{

	   				   
							$('#myModalback').modal('show');
							
						}
				else
					{
					$scope.goDashboard();
					}

					}
			//----------------------------------------------------------
			
 $scope.deleteAllCookies=function() {  
		
	
		$scope.sessionDelete.sessionID=readCookie("AhTwxlO");
	    $http({
				method: 'POST',
				 url: 'Logout',
				data: $.param($scope.sessionDelete),
				headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
			}).then(function(result) {
		
	
		window.location.href="index.jsp";

					 }, function(error) {
							

					 });
	}
	
	function readCookie(name) {
	
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
						
					  $scope.sessionDetails.cookieId=readCookie("AhTwxlO");
					  
					  $scope.sessionDetails.lastVisitedPage=document.URL;
					 
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
								
								
										if(result.data.lastVisitedPage=="undefined")
										{
											
											window.location.href="dashboardUserr0.jsp";
											
										}
										else
										{
											document.cookie="lastVisitedPage=" + result.data.lastVisitedPage;
											if(result.data.lastVisitedPage==document.URL)
												{
											
											}
											
										}
								$scope.load1();

									
							
								
								
								
									}
								else
									{
									
									$scope.errMessage="Session got expired";
									 $("#checkSession").modal("show");
									 var delay = 3000; 
									setTimeout(function(){ $scope.deleteAllCookies() }, delay);
									
									}

									 }, function(error) {
										 
												  
									 });
						  }
					  else
					  {
					  	
					  	$scope.deleteAllCookies();
					  	window.location.href="index.jsp";
					  }
				
				   }
	

});