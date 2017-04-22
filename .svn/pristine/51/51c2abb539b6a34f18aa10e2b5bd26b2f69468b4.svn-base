//------------------For GoalRetirement.jsp-------------------------

var app = angular.module('retirementFormApp',[]);
var url = window.location.href;
var hashes = url.split("=")[1];
app.directive('allowPattern', [allowPatternDirective]);

//-------------------it will allow either numbers or characters in the input feild--------------------
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
app.controller('GoalPlane',function($http,$scope){
	$scope.formdata={};
	$scope.Math = window.Math;
	$scope.userAges = [ {number: '62'},{number: '63'},{number: '64'},{number: '65'},{number: '66'},{number: '67'},{number: '68'},{number: '69'},{number:'70'}	];
	$scope.ages = [ {number: '50'},{number: '51'},{number: '52'},{number: '53'},{number: '54'},{number: '55'},{number: '56'},{number: '57'},{number: '58'},{number: '59'},{number: '60'},{number: '61'},{number: '62'},{number: '63'},{number: '64'},{number: '65'},{number: '66'},{number: '67'},{number: '68'},{number: '69'},{number:'70'}	];
	$scope.items=[];
	$scope.sitems=[];
	$scope.userCap=[];
	$scope.spouseCap=[];
	$scope.formdata.Retirementage="70";
	$scope.formdata.sRetirementage="70";
	$scope.fin_name=(decodeURIComponent(hashes));
	$scope.show = 1;
	$scope.emplyoerscontribution=true;
	$scope.emplyoerscontribution1=true;
	$scope.contrbutionPlan=true;
	$scope.ira=true;
	$scope.ira1=true;
	$scope.rothira=true;
	$scope.rothira1=true;
	$scope.rothplan=true;
	$scope.rothplan1=true;
	$scope.masked = false;
	$scope.marital_status;
	$scope.spouseContribution=true;
	$scope.spouseContribution1=true;
	$scope.spouseContribution2=true;
	$scope.sIra=true;
	$scope.sIra1=true;
	$scope.srothira=true;
	$scope.srothira1=true;
	$scope.srothplan=true;
	$scope.srothplan1=true;
	$scope.new401Cont=true;
	$scope.spouseNew401Cont=true;
	//$scope.showlast=false;
	$scope.masked = false;
	$scope.showNote = false;
	//$scope.msgerrfinal="hello";
	  // $scope.isDisabled = false;
	$scope.sessionDetails={};
	$scope.sessionDelete={};
	$scope.formdata.otherincome=0;
    $scope.formdata.LifeExpectancy=0;
    $scope.formdata.SpouseLE=0;
    $scope.formdata.SpouseRI=0;
    $scope.formdata.retirementExpense=0;
    $scope.formdata.SpouseRA=0;
    $scope.RetirementData={};
    $scope.Sidemessage="Retirement Age";
    $scope.progressbar=0;
    $scope.marital_status="";
    $scope.statusInFinPlan="";
    $scope.formdata.user401Amount="1";
    $scope.formdata.userIraAmount="1";
    $scope.formdata.userRothIraAmount="1";
    $scope.formdata.rothContribution="1";
    $scope.formdata.spouse401Amount="1";
    $scope.formdata.spouseIraAmount="1";
    $scope.formdata.spouseRothAmount="1";
    $scope.formdata.spouseRothSaved="1";
    $scope.formdata.userCont401="1";
    $scope.formdata.spouseCont401="1";
    $scope.massages="true";
    $scope.date = new Date();
 
         $scope.progressBar=function() { 
        	 if($scope.formdata.Retirementage==undefined || $scope.formdata.Retirementage==null||$scope.formdata.Retirementage=="")   
 			{      
          $scope.msgerr="Please fill the field";

          } 
             else{ 
              	 $('#progress_bar').css('width', '10%');
    if(	$scope.marital_status=="Yes"||$scope.statusInFinPlan=="Yes")
    	{
     	 $scope.show=56;
    	
    	}
    else
    	{
              	 $scope.show=2;
                 $scope.show1=2;
    	}	
         }
         }
             $scope.progressBar56=function() { 
            	 if($scope.formdata.sRetirementage==undefined || $scope.formdata.sRetirementage==null||$scope.formdata.sRetirementage=="")   
     			{      
              $scope.msgerr="Please fill the field";

              } 
                 else{ 
                  	 $('#progress_bar').css('width', '10%');
                  /*	if($scope.formdata.sRetirementage < 62 )
                  	{
                  		$scope.formdata.sRetirementage = 62; 
                  		$scope.showNote = true;
                  	}*/
                  		
                     $scope.show=2;
                     $scope.show1=2;
             }
          }

         $scope.yes=function(){
 $scope.emplyoerscontribution=false;
 $scope.emplyoerscontribution1=false;
 $scope.new401Cont=false;
             }

         $scope.no=function(){
        	 $scope.msgerr="";
        	 $scope.emplyoerscontribution=true;
        	 $scope.emplyoerscontribution1=true;
        	 $scope.contrbutionPlan=true;
        	 $scope.new401Cont=true;
             }
         $scope.yesFunc=function()
         {
        	 $scope.contrbutionPlan=true;
         }
         $scope.noFunc=function()
         {
        	 $scope.contrbutionPlan=false;
         }
         $scope.progressBar1=function() {
        	
        	 if($scope.formdata.user401Amount=="yes")
     		{
        		 $scope.massages="true";
        		 if($scope.formdata.amountCap==0)
    			 {
    			 
    			 }
    		 else if($scope.formdata.amountCap == null || $scope.formdata.amountCap =="" || $scope.formdata.amountCap == undefined)
 				{
 			
 					$scope.massages="false";
 				}
        		 if($scope.formdata.amountEmpSav ==0)
        			 {
        			 
        			 }
        		 else if($scope.formdata.amountEmpSav == null || $scope.formdata.amountEmpSav =="" || $scope.formdata.amountEmpSav == undefined)
     				{
     			
     					$scope.massages="false";
     				}
        		 if($scope.formdata.amountEmpCont == 0)
        			 {
        			 
        			 
        			 }
        		 else if(($scope.formdata.userCont401=="noFunc") && ($scope.formdata.amountEmpCont == null || $scope.formdata.amountEmpCont =="" || $scope.formdata.amountEmpCont == undefined))
     			{
     		
     				$scope.massages="false";
     			}
     			if($scope.formdata.amountECost == null || $scope.formdata.amountECost =="" || $scope.formdata.amountECost == undefined)
     			{
     			
     				$scope.massages="false";
     			}

     		}
     		else
     		{
     			$scope.massages="true";
     			$scope.msgerr="";
     		}
     	
     	if($scope.massages=="false")
     		{
     			$scope.msgerr="Please fill the field";

     		}

     	else
     		{
     		
     		$scope.msgerr="";
        	 $('#progress_bar').css('width', '20%');
        $scope.show=3;
     
        if( $scope.formdata.user401Amount=="yes")
   	 {

   $scope.show1 = 3;
   if($scope.formdata.userCont401=="noFunc")
	   {
	   $scope.show1=12;
	   }
		
   	 }
     		} 	

         }

         $scope.yes1=function()
         {
        	 $scope.formdata.iraContribute =undefined;
        	 $scope.ira=false;
        		$scope.ira1=false;
        		
        		
             }
$scope.no1=function()
{
	
	$scope.msgerr="";
	$scope.ira=true;
	$scope.ira1=true;
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
$scope.progressBar2=function()
{
	//alert("progressBar2");
	$scope.msgerr="";


if( $scope.formdata.iraSaved == undefined||($scope.formdata.userIraAmount=="yes1"&&($scope.formdata.iraContribute==undefined)))
{
	
	$scope.msgerrfinal="Please fill the field";
	
}


	else
		{
		$scope.msgerrfinal="";
		$scope.msgerr="";
	 		$('#progress_bar').css('width', '40%');
	 		$scope.show=4;
    		 if( $scope.formdata.userIraAmount=="yes1")
   			 {

				 $scope.show1 = 4;
	
  		 	 }
		}

		}
	$scope.yes2=function()
	{
		$scope.formdata.rothIraContribute=undefined;
		$scope.rothira=false;
		$scope.rothira1=false;

		}
	$scope.no2=function()
	{
		$scope.msgerr="";
		$scope.rothira=true;
		$scope.rothira1=true;

		}
	$scope.progressBar3=function()
	{
		$scope.msgerr="";
		
			 $scope.massages="true";

			
			if($scope.userAge<50)
	    	 {
				 
	    	$scope.totalRothAmount=($scope.formdata.iraSaved*1+$scope.formdata.iraContribute*1+$scope.formdata.rothIra*1+$scope.formdata.rothIraContribute*1); 
	  		
	    	  if($scope.totalRothAmount>5500)
	    		{
	    		 
	    		$scope.massages="nan";
	    		
	    		
	    		}
	    	 
	    	 }
	     
			   if($scope.userAge>50)
	    	{
				 //alert("3");
	    	 $scope.totalRothAmount=($scope.formdata.iraSaved*1+$scope.formdata.iraContribute*1+$scope.formdata.rothIra*1+$scope.formdata.rothIraContribute*1); 
	  		
	    	if($scope.totalContribution>6500)
	    		{
	    		$scope.massages="man";
	    		
	    		
	    		}
	    	
	    	} 
		

			    if( $scope.formdata.rothIra == undefined||($scope.formdata.userRothIraAmount=="yes2"&&($scope.formdata.rothIraContribute==undefined)))
				 {
				
				 	$scope.msgerrfinal="Please fill the field";
				 }
			    else if($scope.massages=="nan")
					{
			   //	alert("4");
					$scope.msgerr="Toatal amount should not exceed $5500 so please enter according to requirment";
					
					}
				 else if($scope.massages=="man")
				  {
					//alert("5");
				$scope.msgerr="Toatal amount should not exceed $6500 so please enter according to requirment";
				
				  }
				
	else
		{
		//alert("6");
		$scope.msgerrfinal="";
		$scope.msgerr="";
		 $('#progress_bar').css('width', '60%');
		 
		   if( $scope.formdata.userRothIraAmount=="yes2")
		   	 {

				 $scope.show1 = 5;
				
		   	 }
		   $scope.show=5;  
		}
		}
	
		$scope.yes3=function()
		{
			$scope.rothplan=false;
			$scope.rothplan1=false;

			}
		$scope.no3=function()
		{
			$scope.msgerr="";
			$scope.rothplan=true;
			$scope.rothplan1=true;

			}
		$scope.progressBar4=function()
		{
			$scope.msgerr="";
			if($scope.formdata.rothContribution=="yes3")
			{
				 $scope.massages="true";
				 if($scope.formdata.rothAccSave == 0)
					 {
					 
					 
					 }
				 else if($scope.formdata.rothAccSave == null || $scope.formdata.rothAccSave =="" || $scope.formdata.rothAccSave == undefined)
					{
		
						$scope.massages="false";
					}
				 if($scope.formdata.rothAccContribute ==0)
					 {
					 
					 }
				 else if($scope.formdata.rothAccContribute == null || $scope.formdata.rothAccContribute =="" || $scope.formdata.rothAccContribute == undefined)
				{
			
					$scope.massages="false";
				}
			}
			else
			{
				$scope.massages="true";
				$scope.msgerr="";
			}
		
		if($scope.massages=="false")
			{
				$scope.msgerr="Please fill the field";

			}
		else
			{
			
			  if( $scope.formdata.rothContribution=="yes3")
			   	 {
					 $scope.show1 = 6;
			   	 }
	
			if($scope.marital_status=="Yes"||$scope.statusInFinPlan=="Yes")
				{
			 $('#progress_bar').css('width', '75%');
			 $scope.show=6;
				}
			else{
		//alert("hgh1222222222222222222222222222");
				$scope.progressBar8();
				 document.getElementById("btn1").disabled = 'true';
	        	
				}
			}
			}
		$scope.yes4=function()
		{
			$scope.spouseContribution=false;
			$scope.spouseContribution1=false;
			$scope.spouseNew401Cont=false;

			}
		$scope.no4=function()
		{
			$scope.msgerr="";
			$scope.spouseContribution=true;
			$scope.spouseContribution1=true;
			$scope.spouseContribution2=true;
			$scope.spouseNew401Cont=true;

			}
		$scope.yesFuncSpouse=function()
		{
			$scope.spouseContribution2=true;
		}
		$scope.noFuncSpouse=function()
		{
			$scope.spouseContribution2=false;
		}
		$scope.progressBar5=function()
		{
		
			$scope.msgerr="";
			if($scope.formdata.spouse401Amount=="yes4")
			{
				 $scope.massages="true";
				 if($scope.formdata.spouseCapAmount == 0)
					{
					
					}
			 else if($scope.formdata.spouseCapAmount == null || $scope.formdata.spouseCapAmount =="" || $scope.formdata.spouseCapAmount == undefined)
				{
		
					$scope.massages="false";
				}
					if($scope.formdata.spouseAmount == 0)
						{
						
						}
				 else if($scope.formdata.spouseAmount == null || $scope.formdata.spouseAmount =="" || $scope.formdata.spouseAmount == undefined)
					{
			
						$scope.massages="false";
					}
					if($scope.formdata.spouseCont==0)
						{
						
						}
				 else if(($scope.formdata.spouseCont401=="noFuncSpouse") &&($scope.formdata.spouseCont == null || $scope.formdata.spouseCont =="" || $scope.formdata.spouseCont == undefined))
				{
		
					$scope.massages="false";
				}
				if($scope.formdata.amountSCost == null || $scope.formdata.amountSCost =="" || $scope.formdata.amountSCost == undefined)
				{
	
					$scope.massages="false";
				}
				
			}
			else
			{
				$scope.massages="true";
				$scope.msgerr="";
			}
		////alert($scope.massages);
		if($scope.massages=="false")
			{
				$scope.msgerr="Please fill the field";

			}

		else
			{
			$scope.msgerr="";
			 $('#progress_bar').css('width', '80%');
			 $scope.show=7;

			  if( $scope.formdata.spouse401Amount=="yes4")
			   	 {
					 $scope.show1 = 7;
					 if($scope.formdata.spouseCont401=="noFuncSpouse")
						 {
						 $scope.show1 = 13;
						 $scope.show1 = 7;
						 }
			   	 }
			}
	
			}
		$scope.yes5=function()
		{
			$scope.formdata.sIraContribute=undefined;
			$scope.sIra=false;
			$scope.sIra1=false;
			}
		$scope.no5=function()
		{
		//alert("aparna9000")
	        
			$scope.msgerr="";
			$scope.sIra=true;
			$scope.sIra1=true;
			
			}
		$scope.progressBar6=function()
		{
			
			if( $scope.formdata.sIraSaved == undefined||($scope.formdata.spouseIraAmount=="yes5"&&($scope.formdata.sIraContribute==undefined)))
			{
				
				$scope.msgerrfinal="Please fill the field";
				
			}
		else
			{
			$scope.msgerrfinal="";
			$scope.msgerr="";
			 $('#progress_bar').css('width', '90%');
			 $scope.show=8;
			  if( $scope.formdata.spouseIraAmount=="yes5")
			   	 {
					 $scope.show1 = 8;
			   	 }
			}
			}	
			$scope.yes6=function()
			{
				$scope.formdata.sRothIraContribute=undefined;
				$scope.srothira=false;
				$scope.srothira1=false;
				}
			$scope.no6=function()
			{
				$scope.msgerr="";
				$scope.srothira=true;
				$scope.srothira1=true;

				}
			$scope.progressBar7=function()
			{
				$scope.msgerr="";
				
				 $scope.massages="true";


				
		   	     if($scope.spouseAge<50)
		    	 {

		   	    	$scope.totalRothAmount=($scope.formdata.sIraContribute*1+$scope.formdata.sIraSaved*1+$scope.formdata.sRothIra*1+$scope.formdata.sRothIraContribute*1); 
			  		
			    	if($scope.totalRothAmount>5500)
			    		{
			    		$scope.massages="nan";
			    		
			    		
			    		}
			    	 
			    	 }
			     
			     if($scope.spouseAge>50)
			    	{
			    	  	$scope.totalRothAmount=($scope.formdata.sIraContribute*1+$scope.formdata.sIraSaved*1+$scope.formdata.sRothIra*1+$scope.formdata.sRothIraContribute*1); 
			  
			    	if($scope.totalContribution>6500)
			    		{
			    		$scope.massages="man";
			    		
			    		
			    		}
		    	
		    	} 

			
			     if( $scope.formdata.sRothIra == undefined||($scope.formdata.spouseRothAmount=="yes6"&&($scope.formdata.sRothIraContribute==undefined)))
				 {
					// alert("1");
					 
				 	$scope.msgerrfinal="Please fill the field";
		         }
			     else if($scope.massages=="nan")
					{
					$scope.msgerr="Toatal amount should not exceed $5500 so please enter according to requirment";
					
					}
				 else if($scope.massages=="man")
				    {
				     $scope.msgerr="Toatal amount should not exceed $6500 so please enter according to requirment";
				
				    }
			     
		else
			{
			$scope.msgerr="";
				 $('#progress_bar').css('width', '95%');
				 $scope.show=9;
				  if( $scope.formdata.spouseRothAmount=="yes6")
				   	 {
						 $scope.show1 = 9;
				   	 }
			}
				}	
			$scope.yes7=function()
			{
				$scope.srothplan=false;
				$scope.srothplan1=false;
				}
			$scope.no7=function()
			{
				$scope.msgerr="";
				$scope.srothplan=true;
				$scope.srothplan1=true;

				}
			$scope.progressBar8=function()
			{
				//alert("100000000000000000000");
				 document.getElementById("btn1").disabled = 'true';
				if($scope.formdata.spouseRothSaved=="yes7")
				{
					 $scope.massages="true";
					 if($scope.formdata.sRothSave==0)
						 {
						 
						 
						 }
					 else if($scope.formdata.sRothSave == null || $scope.formdata.sRothSave =="" || $scope.formdata.sRothSave == undefined)
						{
							
							$scope.massages="false";
						}
					 if($scope.formdata.sRothContribute==0)
					 {
					 
					 
					 }
					 else if($scope.formdata.sRothContribute == null || $scope.formdata.sRothContribute =="" || $scope.formdata.sRothContribute == undefined)
					{
						
						$scope.massages="false";
					}
				}
				else
				{
					$scope.massages="true";
					$scope.msgerr="";
				}
			
			if($scope.massages=="false")
				{
					$scope.msgerr="Please fill the field";

				}
			else
				{
				$scope.msgerr="";
				 $('#progress_bar').css('width', '100%');
				 $scope.formdata.Retirementage=$scope.formdata.Retirementage;
	        	 $scope.formdata.form="next";
	        	 $scope.formdata.fin_name=$scope.fin_name;
	          	 $scope.formdata.marital_status=$scope.marital_status;
	          	 $scope.masked = true;
	        	 $http({
	     		    method: 'POST',
	     		    url: 'RetirmentGoal',
	     		    data: $.param($scope.formdata),
	     		    headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
	     		}).then(function(result) {
	     			$scope.errMessage=result.data.massage;
	     			
	     			$scope.RetirementData=result.data;
	     			$scope.message=result.data;
	     			//$scope.showlast=false;
	     			
	     			if($scope.RetirementData.status=="success")
	     				{
	     				//alert("success");
	     				$scope.masked = false;
	     				console.log($scope.formdata.Retirementage);
	     			 $("#myModal").modal("show"); 
	     			window.location.href="Goalretirementedit.jsp?goalId="+$scope.RetirementData.goal_id+"&notesFlag="+$scope.showNote;
	     			$scope.errorName='';
	     			$scope.errorSuperhero='';
	     				}
	     			else
	     			{
	     				$scope.masked = false;
						$scope.errMessage="Goal is not feasible since you are not having sufficient funds !!";
						$("#firstId").hide();
						$("#myModal").modal('show');
	     			//	window.location.href="#";
						$scope.errorName='';
		     			$scope.errorSuperhero='';
	     				}
	     		
	     		}, function(error) {
	     			   $scope.message=result.data;
	     			   $scope.errorName='';
	     				$scope.errorSuperhero='';
	     		   });
	        	
	          } 
			}
				
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
      	//-------------------------------------------------------------------------------
          $scope.load1=function(){

        	  //$scope.masked = true;
        	  $scope.formdata.form="onload";
        	  $scope.formdata.fin_name=$scope.fin_name;
     
   for(var i =0;i<=100;i=i+5)
	  {

	   $scope.items.push(i);
	   $scope.userCap.push(i);
	 
	  }
   for(var i =0;i<=100;i=i+5)
	  {

	   $scope.sitems.push(i);
	   $scope.spouseCap.push(i);
	  }
     
	$http({
	    method: 'POST',
	    url: 'RetirmentGoal',
	    data: $.param($scope.formdata),
	    headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
	}).then(function(result) {
		
		
		$scope.RetirementData=result.data;
		
	$scope.marital_status=$scope.RetirementData.marital_status;
//	alert($scope.marital_status);
	$scope.userAge=	$scope.RetirementData.userAge;
	$scope.spouseAge=$scope.RetirementData.spouseAge;
	$scope.statusInFinPlan=$scope.RetirementData.statusInFinPlan;
		$scope.message=result.data;
		$scope.errorName='';
		$scope.errorSuperhero='';
		// $scope.masked = false;
	   }, function(error) {
		  
		   $scope.message=result.data;
		   $scope.errorName='';
			$scope.errorSuperhero='';
	   });
	 $scope.msgerr="";       

      		}
      	//----------------------------------------------------------------------------- */
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
        	        //////alert("Action with modal opened or closed");
        	    });
        	  //end just to prove actions outside modal
        	});

});

//--------------------------For GoalRetirementEdit.jsp--------------------------------

var app = angular.module('retirementEditFormApp',[]);
var url = window.location.href;
//console.log(url);
var hashes1 = url.split("&");
//console.log(hashes1);
var hashes2 = hashes1[0].split("=")[1];
var hashes3 = hashes1[1].split("=")[1];

app.directive('allowPattern', [allowPatternDirective]);
/* 

$scope.goal_id=(decodeURIComponent(hashes1)); */
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
          			//////alert(keyCodeChar.charCodeAt());
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
app.controller('GoalPlane',function($scope,$http){

	$scope.userAges = [ {number: '62'},{number: '63'},{number: '64'},{number: '65'},{number: '66'},{number: '67'},{number: '68'},{number: '69'},{number:'70'}	];
	$scope.ages = [ {number: '50'},{number: '51'},{number: '52'},{number: '53'},{number: '54'},{number: '55'},{number: '56'},{number: '57'},{number: '58'},{number: '59'},{number: '60'},{number: '61'},{number: '62'},{number: '63'},{number: '64'},{number: '65'},{number: '66'},{number: '67'},{number: '68'},{number: '69'},{number:'70'}	];
	
	$scope.sessionDetails={};
	 $scope.sessionDelete={};
	$scope.goal_id=(decodeURIComponent(hashes2));
	$scope.flagNotes = (decodeURIComponent(hashes3));
	//console.log(typeof $scope.flagNotes);
	if($scope.flagNotes == "true")
	{
		$scope.showNote = true;
		//$scope.formdata.Retirementage = 62;
	}	
	else if($scope.flagNotes == "false")
	{
		$scope.showNote = false;
	}
	else
	{
		
	}	
	//$scope.fin_name=(decodeURIComponent(hashes));
           $scope.formdata={};
           $scope.RetirementData=[];
       	$scope.items=[];
    	$scope.sitems=[];
    	$scope.userCap=[];
    	$scope.spouseCap=[];
           $scope.Math=window.Math; 
           $scope.message="Location"
                $scope.progressbar=0;
                $scope.planName="";
                $scope.ira=true;
                $scope.masked = false;
                $scope.iraDetailsShow=false;
                $scope.iraDetailsShowone=false;
                $scope.rothIraDetailsShow=false;
                $scope.rothIraDetailsShowone=false;  
                $scope.spouseIraDetailsShow=false;
                $scope.spouseIraDetailsShowone=true;
                $scope.spouseRothIraDetailsShow=false;
                $scope.spouseRothIraDetailsShowone=false;
            	$scope.contrbutionPlan=true;
           	 	$scope.spouseContribution2=true;
                $scope.RetirementData.User63="";
                $scope.RetirementData.User62="";
                $scope.RetirementData.User64="";
                $scope.RetirementData.User65="";
                $scope.RetirementData.User66="";
                $scope.RetirementData.User67="";
                $scope.RetirementData.User68="";
                $scope.RetirementData.User69="";
                $scope.RetirementData.User70="";
                $scope.RetirementData.Spouse62="";
                $scope.RetirementData.Spouse63="";
                $scope.RetirementData.Spouse64="";
                $scope.RetirementData.Spouse65="";
                $scope.RetirementData.Spouse66="";
                $scope.RetirementData.Spouse67="";
                $scope.RetirementData.Spouse68="";
                $scope.RetirementData.Spouse69="";
                $scope.RetirementData.Spouse70="";
                $scope.RetirementData.userExpectedSSB="";
                var countProcess = 0;  
     $scope.processForm1 = function() {
    	 
    	 $scope.masked = true;
			 if($scope.formdata.LifeExpectancy==null||$scope.formdata.LifeExpectancy==undefined||$scope.formdata.LifeExpectancy==""){
	      				 $scope.errmessage1="Planning Horizon is empty";
	      				$scope.masked = false;
	      				$("#fail-alert").show(); 
	      				 window.location.href="#";
	      				 
	      				 
	       					 }
		
			 else
			 {////alert("update");
				
				 $scope.formdata.form="update";
				 $scope.formdata.goal_id=$scope.goal_id;
				// $scope.formdata.fin_name=$scope.fin_name;
				 
	  	 			 if(parseInt($scope.formdata.LifeExpectancy)<parseInt($scope.formdata.Retirementage))
					 {
					 	$scope.errmessage1="Planning Horizon must be greater than Retirement age !!";
					 	 
					 	$("#fail-alert").show();  
					 	 $scope.masked = false;
					 	window.location.href="#";
					 	
					 }
	  	 		
	  	 		
	 else if($scope.formdata.iraSaved == null )
	 				{
	 			
	 	 $scope.errmessage1="Please fill the iraSaved feild";
	  	$("#fail-alert").show();  
	  	 $scope.masked = false;
	 	window.location.href="#";
	 				}
	 
	 			else if($scope.formdata.iraContribute == null )
	 			{
	 		
	 				 $scope.errmessage1="Please fill the iraContribute feild";
	 				$("#fail-alert").show();  
	 				 $scope.masked = false;
	 			 	window.location.href="#";
	 			}
	 	
	 				else if($scope.formdata.rothIra == null )
	 				{
	 				
	 					 $scope.errmessage1="Please fill the rothIra feild";
	 					$("#fail-alert").show();  
	 					 $scope.masked = false;
		 			 	window.location.href="#";
	 				}
	 		
	 				else if($scope.formdata.rothIraContribute == null)
	 			{

	 					 $scope.errmessage1="Please fill the rothIraContribute feild";
	 					$("#fail-alert").show(); 
	 					 $scope.masked = false;
		 			 	window.location.href="#";
	 			}
	 						
	 			
	 				 else if($scope.formdata.sIraSaved == null )
	 					{
	 						
	 					 $scope.errmessage1="Please fill the spouse IraSaved feild";
	 					$("#fail-alert").show(); 
	 					 $scope.masked = false;
	 				 	window.location.href="#";
	 					 
	 					}
	 		
	 				 else if($scope.formdata.sIraContribute == null )
	 				{
	 					 $scope.errmessage1="Please fill the spouse IraContribute feild";
	 					$("#fail-alert").show();  
	 					 $scope.masked = false;
	 				 	window.location.href="#";
	 					 
	 				}
	
	 				 else if($scope.formdata.sRothIra == null )
	 					{
	 						
	 					 $scope.errmessage1="Please fill the spouse RothIra feild";
	 					$("#fail-alert").show();  
	 					 $scope.masked = false;
	 				 	window.location.href="#";
	 					}
	 		
	 					else if($scope.formdata.sRothIraContribute == null )
	 				{
	 					
	 						 $scope.errmessage1="Please fill the spouse RothIraContribute feild";
	 						$("#fail-alert").show();  
	 						 $scope.masked = false;
	 					 	window.location.href="#";
	 				}
	 				
	  	 			else
	  	 				{
         		 		/*if($scope.formdata.SpouseRA < 62)
         		 		{
         		 			$scope.showNote = true; 
         		 			$scope.formdata.SpouseRA = 62;
         		 		}
         		 		else*/
         		 		{
         		 			$scope.showNote = false; 
         		 		}
        			$http({
        			    method: 'POST',
        			    url: 'RetirmentGoal',
        			    data: $.param($scope.formdata),
        			    headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
        			}).then(function(result) {
        				if(result.data.status=="success")
      					{
        				$scope.planName=result.data.plan_name;
        				if(result.data.goalFeasiblity==false)
    					{
    					//alert();
    					$("#fail-warning").show();
    					}
        				
        				/*if(countProcess == 0)
        				{
        					$scope.masked = true;
          					countProcess++;
        					$scope.processForm1();
        				}*/

         		 		$scope.RetirementData=result.data;

        				$scope.message=result.data;
//        				location.reload(); 
        					$scope.formdata.Retirementage=$scope.RetirementData.retirementAge;
        					$scope.formdata.LifeExpectancy=$scope.RetirementData.lifeExpectancy;
        					$scope.formdata.iraSaved=$scope.RetirementData.iraSaved;
        					$scope.formdata.iraContribute=$scope.RetirementData.iraContribue;
        					$scope.formdata.rothIra=$scope.RetirementData.rothIraSaved;
        					$scope.formdata.rothIraContribute=$scope.RetirementData.rothIraContribue;
        					$scope.formdata.sIraSaved=$scope.RetirementData.spouseIra;
        					$scope.formdata.sIraContribute=$scope.RetirementData.spouseIraContribue;
        					$scope.formdata.sRothIra=$scope.RetirementData.spouserothIraSaved;
        					$scope.formdata.sRothIraContribute=$scope.RetirementData.spouserothIraContribue;
        				
       
      				//---------Success modal alert------------------------------------------
      					console.log(result.data);
      				
      					$("#fail-alert").hide();
      					 $scope.masked = false;
      				$scope.errmessage=" Goal updated successfully";			
      					$("#success-alert").show();
      								$("#success-alert").fadeTo(2000, 300).slideUp(300, function(){
      				                	$("#success-alert").hide();
      				   
      				                });
      					$scope.load1();			
      					}
      				else
      					{

					 	 $scope.masked = false;

      					 window.location.href="#";
     	 				
    	 				 $scope.errMessage="Goal is not feasible since you are not having sufficient funds !!";
    	            	  $("#myModal").modal('show');
      					
      					}
      				//-----------------------------------------------------------------------
        				$scope.RetirementData=result.data;

    					if(result.data.marital_status=="Yes"||$scope.statusInFinPlan=="Yes")
    					{
    						$scope.SpouceDetail=true;
    					}
    					else
    					{
    						$scope.SpouceDetail=false;
    					}
        				
        				$scope.message=result.data;
        				$scope.errorName='';
        				$scope.errorSuperhero='';
        			   }, function(error) {
        				   ////alert("Fail");
        				   $scope.message=result.data;
        				   $scope.errorName='';
        					$scope.errorSuperhero='';
        			   });
        			 $scope.msgerr="";
			 }
	  	 			 
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
 $scope.reload=function()
 {
 		
 	window.location.href=window.location.href.slice(0, -1);
 	
 	}
 $scope.load1=function(){
	 $scope.masked = true;
	 $("#success-alert").hide();
	 //$scope.masked = false;
	 $("#fail-alert").hide(); 

		$scope.formdata.form="edit";
		 $scope.formdata.goal_id=$scope.goal_id;
		 $scope.userCap = [];
		 $scope.items = [];
		 $scope.spouseCap = [];
		   for(var i =0;i<=100;i=i+5)
			  {

			   $scope.items.push(i);
			   $scope.userCap.push(i);
			 
			  }
		   //console.log($scope.userCap);
		   for(var i =0;i<=100;i=i+5)
			  {

			   $scope.sitems.push(i);
			   $scope.spouseCap.push(i);
			  }
		$http({
			  method: 'POST',
			  url: 'RetirmentGoal',
			  data: $.param($scope.formdata),
			  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
			}).then(function(result) {
				
				//alert("hjghhh");
				//
				//alert(JSON.stringify(result.data));
				$scope.planName=result.data.plan_name;
				$scope.RetirementData=result.data;			
				$scope.message=result.data;
				$scope.RetirementData.User62=Math.round($scope.RetirementData.User62);
			    $scope.RetirementData.User63=Math.round($scope.RetirementData.User63);
			    $scope.RetirementData.User64=Math.round($scope.RetirementData.User64);
			    $scope.RetirementData.User65=Math.round($scope.RetirementData.User65);
			    $scope.RetirementData.User66=Math.round($scope.RetirementData.User66);
			    $scope.RetirementData.User67=Math.round($scope.RetirementData.User67);
			    $scope.RetirementData.User68=Math.round($scope.RetirementData.User68);
			    $scope.RetirementData.User69=Math.round($scope.RetirementData.User69);
			    $scope.RetirementData.User70=Math.round($scope.RetirementData.User70);
			    $scope.RetirementData.Spouse62=Math.round($scope.RetirementData.Spouse62);
			    $scope.RetirementData.Spouse63=Math.round($scope.RetirementData.Spouse63);
			    $scope.RetirementData.Spouse64=Math.round($scope.RetirementData.Spouse64);
			    $scope.RetirementData.Spouse65=Math.round($scope.RetirementData.Spouse65);
			    $scope.RetirementData.Spouse66=Math.round($scope.RetirementData.Spouse66);
			    $scope.RetirementData.Spouse67=Math.round($scope.RetirementData.Spouse67);
			    $scope.RetirementData.Spouse68=Math.round($scope.RetirementData.Spouse68);
			    $scope.RetirementData.Spouse69=Math.round($scope.RetirementData.Spouse69);
			    $scope.RetirementData.Spouse70=Math.round($scope.RetirementData.Spouse70);
			    $scope.RetirementData.userExpectedSSB=Math.round($scope.RetirementData.userExpectedSSB);
				//alert(JSON.stringify($scope.RetirementData));
			
				
				if(result.data.goalFeasiblity==false)
				{
				//alert();
				$("#fail-warning").show();
				}
		
					$scope.formdata.Retirementage=$scope.RetirementData.retirementAge;
					$scope.formdata.LifeExpectancy=$scope.RetirementData.lifeExpectancy;
					$scope.formdata.retirementExpense=$scope.RetirementData.retirement_expense;
					$scope.formdata.otherincome=$scope.RetirementData.otherRetirementIncome;
					$scope.formdata.SpouseRA=$scope.RetirementData.spouseRetirementAge;
					/*if($scope.RetirementData.spouseRetirementAge < 62)
					{
						$scope.formdata.SpouseRA = 62;
						$scope.showNote = true;
					}	*/
					$scope.formdata.SpouseRI=$scope.RetirementData.spouseOtherRetirementIncome;
					$scope.formdata.SpouseLE=$scope.RetirementData.spouseLifeExpectancy;
					$scope.formdata.iraSaved=$scope.RetirementData.iraSaved;
					$scope.formdata.iraContribute=$scope.RetirementData.iraContribue;
					$scope.formdata.rothIra=$scope.RetirementData.rothIraSaved;
					$scope.formdata.rothIraContribute=$scope.RetirementData.rothIraContribue;
					$scope.formdata.sIraSaved=$scope.RetirementData.spouseIra;
					$scope.formdata.sIraContribute=$scope.RetirementData.spouseIraContribue;
					$scope.formdata.sRothIra=$scope.RetirementData.spouserothIraSaved;
					$scope.formdata.sRothIraContribute=$scope.RetirementData.spouserothIraContribue;
					$scope.formdata.amountCap=$scope.RetirementData.userCap401;
					$scope.formdata.amountEmpSav=$scope.RetirementData.userSavedAmount401;
					$scope.formdata.amountEmpCont=$scope.RetirementData.userContribution401;
					$scope.formdata.amountECost=$scope.RetirementData.userMatching401;
					$scope.marital_status=$scope.RetirementData.marital_status;
					$scope.formdata.spouseCapAmount=$scope.RetirementData.spouseCap401;
					$scope.formdata.spouseAmount=$scope.RetirementData.spouseSavedAmount401;
					$scope.formdata.spouseCont=$scope.RetirementData.spouseContribution401;
					$scope.formdata.amountSCost=$scope.RetirementData.spouseMatching401;
					
	if($scope.marital_status=="Yes"||$scope.statusInFinPlan=="Yes")
	{

 $scope.spouseirad=true;
 $scope.spouserothirad=true;
 $scope.spouse401kd=true;
	}
if($scope.formdata.amountCap==0 && $scope.formdata.amountEmpSav==0 && $scope.formdata.amountEmpCont==0 && $scope.formdata.amountECost==0)
	{
	$scope.formdata.user401Amount="no";
	$scope.hideuser401=true;
	}
else{
	  $scope.formdata.user401Amount="yes";
		 $scope.hideuser401=false;
}
if($scope.formdata.amountEmpCont!=0)
	{
	$scope.formdata.userCont401="noFunc";
	$scope.contrbutionPlan=false;
	}
else{
	$scope.formdata.userCont401="yesFunc";
	$scope.contrbutionPlan=true;
}
if(($scope.formdata.iraSaved!=0 && $scope.formdata.iraContribute==0))
	 {
     $scope.formdata.userIraAmount="no1";
     $scope.iraDetailsShow=false; 
	 $scope.iraDetailsShowone=true; 
	 }

else if($scope.formdata.iraSaved==0 && $scope.formdata.iraContribute==0)
		
{
	
		  $scope.formdata.userIraAmount="no1";
	
		  $scope.iraDetailsShow=false; 
			 $scope.iraDetailsShowone=true; 

}
 else{
	
	 $scope.formdata.userIraAmount="yes1";
	 $scope.iraDetailsShow=false; 
	 $scope.iraDetailsShowone=false; 
	 
     }

 if(($scope.formdata.rothIra!=0&&$scope.formdata.rothIraContribute==0))
	 {
	 $scope.formdata.userRothIraAmount="no2";
	 $scope.rothIraDetailsShow=false;  
	 $scope.rothIraDetailsShowone=true;  
	 
	 }
 else if($scope.formdata.rothIra==0&&$scope.formdata.rothIraContribute==0)
 {
 $scope.formdata.userRothIraAmount="no2";
 $scope.rothIraDetailsShow=false;  
 $scope.rothIraDetailsShowone=true;  
 }
 else{
	 $scope.formdata.userRothIraAmount="yes2";
	 $scope.rothIraDetailsShow=false;  
	 $scope.rothIraDetailsShowone=false;  
	 
	 
     }
 if($scope.formdata.spouseCapAmount==0 && $scope.formdata.spouseAmount==0 && $scope.formdata.spouseCont==0 && $scope.formdata.amountSCost==0)
	{
		$scope.formdata.spouse401Amount="no4";
		 $scope.hidespouse401=true;
	}
else{
	 $scope.formdata.spouse401Amount="yes4";
	  $scope.hidespouse401=false; 
}
 if($scope.formdata.spouseCont!=0 ){
	$scope.formdata.spouseCont401="noFuncSpouse"; 
	$scope.spouseContribution2=false;
	//alert("hi")
	
 }
 else{
		$scope.formdata.spouseCont401="yesFuncSpouse";
		//alert("hi2")
 }
 if(($scope.formdata.sIraSaved!=0 && $scope.formdata.sIraContribute==0))
	
 {
	 $scope.formdata.spouseIraAmount="yes5";
	 $scope.spouseIraDetailsShow=false;
	 $scope.spouseIraDetailsShowone=true;
 }

 else if($scope.formdata.sIraSaved==0 && $scope.formdata.sIraContribute==0)
		
 {
	 $scope.formdata.spouseIraAmount="yes5";
	 $scope.spouseIraDetailsShow=false;
	 $scope.spouseIraDetailsShowone=true;

 }

else{
	 
	 $scope.formdata.spouseIraAmount="no5";
	 $scope.spouseIraDetailsShow=false;
	 $scope.spouseIraDetailsShowone=false;

 }
 if(($scope.formdata.sRothIra!=0 && $scope.formdata.sRothIraContribute==0))
 {
	 $scope.formdata.spouseRothAmount="yes6";
	 $scope.spouseRothIraDetailsShow=false;
	 $scope.spouseRothIraDetailsShowone=true;
	 
	 //alert("h");

 }
 else if($scope.formdata.sRothIra==0&&$scope.formdata.sRothIraContribute==0)
 {
	 $scope.formdata.spouseRothAmount="yes6";
	 $scope.spouseRothIraDetailsShow=false;
	 $scope.spouseRothIraDetailsShowone=true;
	 //alert("h1123");

 }
else{
	 $scope.formdata.spouseRothAmount="no6";
	 $scope.spouseRothIraDetailsShow=false;
	 $scope.spouseRothIraDetailsShowone=false;
	 //alert("h2")
 }


					if(result.data.marital_status=="Yes"||$scope.statusInFinPlan=="Yes")
					{
						$scope.SpouceDetail=true;
					}
					else
					{
						$scope.SpouceDetail=false;
					}
				$scope.errorName='';
				$scope.errorSuperhero='';
				$scope.masked = false;
		

			 }, function(error) {
				////alert("Fail");
				 //$scope.message=result.data.status;
	  
			 });
		}
 $scope.yesFunc=function()
 {
	 $scope.contrbutionPlan=true;
 }
 $scope.noFunc=function()
 {
	 $scope.contrbutionPlan=false;
 }   
	$scope.yesFuncSpouse=function()
	{
		//alert("inside")
		$scope.spouseContribution2=true;
	}
	$scope.noFuncSpouse=function()
	{	//alert("outside")
		$scope.spouseContribution2=false;
	}

 $scope.show401kDetails=function()
 {
	 $scope.hideuser401=false;
 }
 $scope.hide401kDetails=function()
 {
	 $scope.hideuser401=true;
 }
 $scope.spouseShowdetails=function()
 {
	 $scope.hidespouse401=false; 
 }
 $scope.spouseHidedetails=function()
 {
	 $scope.hidespouse401=true; 
 }
 $scope.showIraDetails=function()
 {
	 
	$scope.iraDetailsShow=false; 
	$scope.iraDetailsShowone=true; 
 }
 $scope.hideIraDetails=function()
 {
	 
	 $scope.iraDetailsShow=false; 
	 $scope.iraDetailsShowone=false; 
 }
 $scope.showRothIraDetails=function()
 {
	 
		$scope.rothIraDetailsShow=false;
		 $scope.rothIraDetailsShowone=true;  
 }
 $scope.hideRothIraDetails=function()
 {
	 
	 $scope.rothIraDetailsShow=false;  
	 $scope.rothIraDetailsShowone=false;  
 }
 $scope.showspouseIraDetails=function()
 {
	 
	 $scope.spouseIraDetailsShow=false;
	 $scope.spouseIraDetailsShowone=true;
 }
 $scope.hidespouseIraDetails=function()
 {
	 
	 $scope.spouseIraDetailsShow=false;
	 $scope.spouseIraDetailsShowone=false;
 }
 $scope.showspouseRothIraDetails=function()
 {
	 
	 $scope.spouseRothIraDetailsShow=false;
	 $scope.spouseRothIraDetailsShowone=false;
	 //alert();
 }
 $scope.hidespouseRothIraDetails=function()
 {
	 $scope.spouseRothIraDetailsShow=false;
	 
	 $scope.spouseRothIraDetailsShowone=true;
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
 //-----------------------------------------------------------------------
 
 $scope.DeleteGoal=function()
	{ 
	
		$('#myModal1').modal('show')
		}
 
//----------------------------------------------------------
	$scope.deletegoal=function(){
		$scope.masked = true;
		$scope.formdata.formType="deleteGoal";
		
		$scope.formdata.plan_name=$scope.planName;
		
		$scope.formdata.goal_id=$scope.goal_id;
		
		
		$http({
	  		  method: 'POST',
	  		  url: 'CopyPlan',
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
//---------------------------------------logic for back button-------------------------
	
	$scope.goDashboard=function()
	{
	window.location.href="dashboardUser0.jsp?finName="+ $scope.planName;
	}
//----------------------------------------------------------

	$scope.backwithoutsave=function(formdata)
	
	{ 

if($scope.message.retirementAge
!=formdata.Retirementage  ||   $scope.message.lifeExpectancy!=formdata.LifeExpectancy)
		{
		
			$('#myModalback').modal('show');

		}
	else
		{
		$scope.goDashboard();
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
		//alert("Session Got deleted");
	
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
			
					//alert(result.data);
					/*  if(result.data.massage=="fail")
					{
						 alert(result.data.massage);
						 $scope.errMessage=result.data.massage;
	        			 $("#myModal").modal("show");
						 var delay = 3000; //Your delay in milliseconds
						setTimeout(function(){ window.location.href="index.jsp"; }, delay);
					}  */
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
					
					//alert("Session got expired");
					 $scope.errMessage="Session got expired";
					 $("#checkSession").modal("show");
					 var delay = 3000; //Your delay in milliseconds
					setTimeout(function(){ $scope.deleteAllCookies() }, delay); 
					//$scope.deleteAllCookies();
					
					//window.location.href="index.jsp";
					
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

