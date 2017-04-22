// ------------------For GoalVacation.jsp----------------------------------------
var app = angular.module('vacationFormApp',[]);
var url = window.location.href;
var hashes = url.split("=")[1];
app.controller('GoalPlane',function($http,$scope){
	 $scope.fin_name=(decodeURIComponent(hashes));
		
	$scope.show = 1;
		
				var date = new Date();
				var currentYear = parseInt(date.getFullYear()); 
                $scope.formdata={};
                $scope.resultArray={};
                $scope.masked = false;
                $scope.vacationYear1=[{number: currentYear},{number: currentYear+1}, {number: currentYear+2}, {number: currentYear+3}, {number: currentYear+4}]
                $scope.frequency = [ {string: 'One Time'},{string: 'Every Year'}, {string: 'Every Two Years'}];
                $scope.formdata.frequency="One Time";
                $scope.formdata.vacationYear=currentYear;
                $scope.formdata.amountSave="1000";
                
                $scope.message="amountSave"
                $scope.progressbar=0;
         $scope.progressBar=function() { 
        	 if($scope.formdata.amountSave==undefined || $scope.formdata.amountSave==null||$scope.formdata.amountSave=="")   
  			{      
     			 $scope.msgerr="Please enter amount to be saved";
     		       

           	} 
     		 else if(parseInt($scope.formdata.amountSave)==0)
	    	  	 {
	    	  		 $scope.msgerr="How much should I budget for your vacation? cannot be zero !!";
			    	 
	    	  	 }
     		 else
         	  {
         	  
        			 $('#progress_bar').css('width', '35%');
        	 $scope.message="frequency"
        	$scope.formdata.frequency=$scope.formdata.frequency;
        		 $scope.show = 3;
        	 $scope.show1 = 2;
        	 $scope.msgerr="";
        		}
         }  
         
         $scope.progressBar2=function() { 
        	      	  
        		$('#progress_bar').css('width', '70%');
        	 	$scope.message="frequency"
        		 $scope.show = 4;
        		 $scope.show1 = 3;
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
          $scope.progressBar1=function() { 
        	  //alert("progressBar1");
        	  $scope.masked = true;
        	  $scope.formdata.formType="next";
        	  if($scope.formdata.frequency==undefined|| $scope.formdata.frequency==null||$scope.formdata.frequency=="")   
  			{      
        		  $scope.masked = false;
          		 $scope.msgerr="Please fill the feild";

           } else{
        	   $scope.masked = true;
        	 $scope.formdata.TimePeriod= $scope.formdata.TimePeriod;
        	 $scope.formdata.completedStatus=1;
       
        	  $scope.show =5;
        	 $scope.show1 = 4;
        	 $('#progress_bar').css('width', '100%');
        	 $('#dialog_confirm_map').modal('hide');
        	 $scope.formdata.plan_name=$scope.fin_name;
        	 
        	 $http({
     		    method: 'POST',
     		    url: 'Vacation',
     		    data: $.param($scope.formdata),
     		    headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
     		}).then(function(result) {
     			$scope.resultArray=result.data;
     			$scope.errMessage=result.data.massage;
     			$scope.formdata.goal_id=result.data.goal_id;
     			$scope.messresult=result.data.amountSave;
     			if($scope.resultArray.status=="success")
     				{
     					window.location.href="GoalVacationEdit.jsp?goalId="+result.data.goal_id;
     					$scope.errorName='';
     					$scope.errorSuperhero='';
     					$scope.masked = false;
     				}
     			else
     				{
     				$scope.masked = false;
     					$scope.errMessage="Goal is not feasible since you are not having sufficient funds !!";
     					$("#myModal").modal('show');
     				}
     			
     		   }, function(error) 
     		   {
     			 
     			   $scope.messresult=result.data.emi;
     			   $scope.errorName='';
     			   $scope.errorSuperhero='';
     		   });
        	 	$scope.msgerr="";

        	 	$scope.processForm1 = function() {
        	 		//alert("1");
        		 $scope.formdata.formType="update";
        		 if($scope.formdata.amountSave==undefined || $scope.formdata.amountSave==null||$scope.formdata.amountSave=="")   
     			{      
        			 $scope.errMessage="Please enter amount to be saved";
        		       $("#myModal").modal("show");

              	} 
        		 else if(parseInt($scope.formdata.amountSave)==0)
  	    	  	 {
  	    	  		 $scope.errMessage="How much should I budget for your vacation? cannot be zero";
  			    	 window.location.href="#";
  			      	 $("#myModal").modal("show");
  	    	  	 }
        		 else
            	  {
            	  
        		 
     			$http({
     			    method: 'POST',
     			    url: 'Vacation',
     			    data: $.param($scope.formdata),
     			    headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
     			}).then(function(result) {
     				$scope.resultArray=result.data;
     				$scope.errMessage=result.data.massage;
     				$("#myModal").modal("show");
					$scope.formdata.amountSave=result.data.amountSave;
      				$scope.formdata.frequency=result.data.frequency;
      				$scope.messresult=result.data.amountSave;
     				$scope.errorName='';
     				$scope.errorSuperhero='';
     			   }, function(error) {
     				  
     				   $scope.messresult=result.data.emi;
     				   $scope.errorName='';
     					$scope.errorSuperhero='';
     			   });
     			 $scope.msgerr="";
     		}
           }
         }
        	  //$scope.masked = false;
        }
     	 $scope.sessionDetails={};
    	 $scope.sessionDelete={};
    	 $scope.deleteAllCookies=function()
    	 {  
    		 $scope.sessionDelete.sessionID=readCookie("AhTwxlO");
    		 $http({
    				method: 'POST',
    				 url: 'Logout',
    				data: $.param($scope.sessionDelete),
    				headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
    			}).then(function(result)
    					{
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
    					   window.history.forward();
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
   									 var delay = 3000; //Your delay in milliseconds
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
    				   $scope.goSelectGoal=function()
		     			 {
		     				 
    					   window.location.href="goals.jsp?finName="+ $scope.fin_name;
		     			 }
    				   $scope.goDashboard=function()
		     			 {
		     				 
  					   window.location.href="dashboardUser0.jsp?finName="+ $scope.fin_name;
		     			 }
    				   $(function () {
    					   
    					   //getting click event to show modal
    					    
    					         $('#dialog_confirm_map').modal();
    					       
    					       //appending modal background inside the bigform-content
    					         $('.modal-backdrop').appendTo('.right_content');
    					       //removing body classes to able click events
    					         //$('body').removeClass();

    					   //just to prove actions outside modal
    					     $('#help-button').click(function () {
    					         ////alert("Action with modal opened or closed");
    					     });
    					   //end just to prove actions outside modal
    					 });

}); 

//-----------------------for VacationGoalEdit.jsp---------------------------------------
var app = angular.module('vacationEditFormApp',[]);
var url = window.location.href;
var hashes = url.split("=")[1];
var date = new Date();
	var currentYear = parseInt(date.getFullYear()); 
app.controller('formController2',function($http,$scope){
	$scope.vacationYear=[{number: currentYear},{number: currentYear+1}, {number: currentYear+2}, {number: currentYear+3}, {number: currentYear+4}]
    $scope.frequency = [ {string: 'One Time'},{string: 'Every Year'}, {string: 'Every Two Years'}];
    $scope.hashes={};
    $scope.goal_id=(decodeURIComponent(hashes));
	$scope.hashes.data=hashes;
	$scope.planName="";
	$scope.resultArray={};  
	$scope.formdata={};
	$scope.sessionDetails={};
	$scope.sessionDelete={};
	$scope.dataWarning={};
	$scope.masked = false;
	

	$scope.deleteAllCookies=function()
	{  
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
	
	 function readCookie(name)
	 {
		
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
	$scope.formdata.hashes=hashes;
	$scope.formdata.amountSave="";
	$scope.formdata.frequency=""
	  
	$scope.load1=function()
	{ 
		//alert("l1");
		$scope.masked = true;
		 $("#success-alert").hide();
		 $("#fail-alert").hide();  
	
		 $scope.formdata.formType="edit";
		 $scope.formdata.goal_id=$scope.goal_id;
		
  		   $http({
			    method: 'POST',
			    url: 'Vacation',
			    data: $.param($scope.formdata),
			    headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
			}).then(function(result) {
				/*if(result.data.goalFeasiblity==false)
				{
					$scope.masked = false;
				
				$("#fail-warning").show();
				}*/
				$scope.resultArray=result.data;
				$scope.goal_id=$scope.resultArray.goal_id;
				$scope.planName=result.data.plan_name;
    			$scope.formdata.amountSave=result.data.amountSave;
    			
    			$scope.formdata.frequency=result.data.frequency;
    			$scope.messresult=result.data.amountSave;
    			$scope.formdata.vacationYear=result.data.vacationYear;
				$scope.errorName='';
				$scope.errorSuperhero='';
				$scope.masked = false;
			   }, function(error) {
				
				   $scope.message=result.data;
				   
				   $scope.errorName='';
					$scope.errorSuperhero='';
			   });
	   }
	$scope.formdata.amountSave="";
	$scope.formdata.frequency="";
	$scope.processform1=function()
	{ 
 	   //alert("2");
		$scope.masked = true;
		$scope.formdata.formType="update";
 	
		if($scope.formdata.amountSave==null||$scope.formdata.amountSave==undefined||$scope.formdata.amountSave=="")
		{
			$scope.masked = false;
			$scope.errmessage1="Please enter amount to be saved";
			$("#fail-alert").show();   	
			window.location.href="#";
		}
		else if(parseInt($scope.formdata.amountSave)==0)
 	   {
			//$scope.masked = true;
			 $scope.errmessage1="How much should I budget for your vacation? cannot be zero !!";
		     window.location.href="#";
		     $scope.masked = false;
		     $("#fail-alert").show(); 
 	   }
			
		else
			{
				//alert(JSON.stringify($scope.formdata));
		   $http({
			    method: 'POST',
			    url: 'Vacation',
			    data: $.param($scope.formdata),
			    headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
			}).then(function(result) {
				if(result.data.goalFeasiblity==false)
				{
					$scope.masked = false;
				$("#fail-warning").show();
				}
				$scope.planName=result.data.plan_name;
				$scope.resultArray=result.data;
				
 //---------Success modal alert------------------------------------------
 		
 			if(result.data.status=="success")
 				{
 				$("#fail-alert").hide();
 				$scope.masked = false;
 					$scope.errmessage=" Goal updated successfully";			
 					window.location.href="#";
 					$("#success-alert").show();
 					$("#success-alert").fadeTo(2000, 300).slideUp(300, function()
 					{
 							$("#success-alert").hide();
 			   
 			         });   
 			
 				}
 			else
 				{
 				$scope.masked = false;
 					window.location.href="#";
 					$scope.errMessage="Goal is not feasible since you are not having sufficient funds !!";
	            	$("#myModal").modal('show');
 				
 				}
 
				$scope.formdata.amountSave=result.data.amountSave;
				$scope.formdata.frequency=result.data.frequency;
				$scope.messresult=result.data.amountSave;
		
				$scope.errorName='';
				$scope.errorSuperhero='';
				$scope.masked = false;
			   }, function(error) {
				   $scope.message=result.data;
				   
				   $scope.errorName='';
					$scope.errorSuperhero='';
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
	  $scope.hideWarning=function()
	  {
	 	 $("#fail-warning").hide();
	 	
	 	 
	  }
	  $scope.DeleteGoal=function()
		{ 
			
			$('#myModal1').modal('show')
		}
	  		$scope.deletegoal=function(){
			
	  			$scope.masked = true;
			$scope.formdata.formType="deleteGoal";
			
			$scope.formdata.plan_name=$scope.planName;
			
			$scope.formdata.goal_id=$scope.formdata.goal_id;
			
			//alert(JSON.stringify($scope.formdata));
			$http({
		  		  method: 'POST',
		  		  url: 'Vacation',
		  		  data: $.param($scope.formdata),
		  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
					}).then(function(result) {
					//alert(JSON.stringify(result.data));
					$scope.goDashboard();
					$scope.message1=result.data.status;
					
					$scope.masked = false;

		  		 }, function(error) {
		  			$scope.message=result.data.status;
			  
		  		 }); 

		}

//---------------------------------------logic for back button-------------------------

	$scope.goDashboard=function()
	{
		window.location.href="dashboardUser0.jsp?finName="+ $scope.planName;
	}
//----------------------------------------------------------
	function numbersonly(e){
	    var unicode=e.charCode? e.charCode : e.keyCode
	    if (unicode!=8){ //if the key isn't the backspace key (which we should allow)
	        if (unicode<48||unicode>57) //if not a number
	            return false //disable key press
	    }
	}
	$scope.backwithoutsave=function(formdata)
			
			{
				if($scope.resultArray.amountSave!=formdata.amountSave|| $scope.resultArray.frequency!=formdata.frequency|| $scope.resultArray.vacationYear!=formdata.vacationYear)
				{
				
					$('#myModalback').modal('show');

				}
			else
				{
				$scope.goDashboard();
				}

				}

			$scope.load=function()
			{  
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
								$scope.dataWarning=result.data;
								
						
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
									var delay = 3000; //Your delay in milliseconds
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