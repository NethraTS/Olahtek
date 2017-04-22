var app = angular.module('formApp',[]);
var app1 = angular.module('goalHouseEdit',[]);
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
$(document).ready(function() {
    $(".decimalAllow").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
});
app.controller('GoalPlane',function($http,$scope){
	$scope.sessionDetails={};
	$scope.sessionDelete={};
	$scope.disabledCity=true;
	$scope.date=new Date();
	$scope.citys=['select city...'];
	$scope.states = [ {name:'select state...'},{name:'ALABAMA'},{name:'ALASKA'},{name:'ARIZONA'},{name:'ARKANSAS'},{name:'CALIFORNIA'},{name:'COLORADO'},{name:'CONNECTICUT'},{name:'DELAWARE'},{name:'FLORIDA'},{name:'GEORGIA'},{name:'HAWAII'},{name:'IDAHO'},{name:'ILLINOIS'},{name:'INDIANA'},{name:'IOWA'},{name:'KANSAS'},{name:'KENTUCKY'},{name:'LOUISIANA'},{name:'MAINE'},{name:'MARYLAND'},{name:'MASSACHUSETTS'},{name:'MICHIGAN'},{name:'MINNESOTA'},{name:'MISSISSIPPI'},{name:'MISSOURI'},{name:'MONTANA'},{name:'NEBRASKA'},{name:'NEVADA'},{name:'NEW HAMPSHIRE'},{name:'NEW JERSEY'},{name:'NEW MEXICO'},{name:'NEW YORK'},{name:'NORTH CAROLINA'},{name:'NORTH DAKOTA'},{name:'OHIO'},{name:'OKLAHOMA'},{name:'OREGON'},{name:'PENNSYLVANIA'},{name:'RHODE ISLAND'},{name:'SOUTH CAROLINA'},{name:'SOUTH DAKOTA'},{name:'TENNESSEE'},{name:'TEXAS'},{name:'UTAH'},{name:'VERMONT'},{name:'VIRGINIA'},{name:'WEST VIRGINIA'},{name:'WISCONSIN'},{name:'WYOMING'},{name:'WASHINGTON'},{name:'WASHINGTON DC'}];
	/* $scope.states = ["ALABAMA","ALASKA","ARIZONA","ARKANSAS","CALIFORNIA","COLORADO","CONNECTICUT","FLORIDA","GEORGIA","HAWAII","IDAHO","ILLINOIS","INDIANA","IOWA","KANSAS","KENTUCKY","LOUISIANA","MAINE","MARYLAND","MASSACHUSETTS","MICHIGAN","MINNESOTA","MISSISSIPPI","MISSOURI","MONTANA","NEBRASKA","NEVADA","NEW HAMPSHIRE","NEW JERSEY","NEW MEXICO","NEW YORK","NORTH CAROLINA","NORTH DAKOTA","OHIO,OKLAHOMA","OREGON","PENNSYLVANIA","RHODE ISLAND","SOUTH CAROLINA","SOUTH DAKOTA","TENNESSEE","TEXAS","UTAH","VERMONT","VIRGINIA","WASHINGTON","WEST VIRGINIA","WISCONSIN","WYOMING","WASHINGTON"]; */
	$scope.loans = [ {name:'30-year fixed'},{name:'15-year fixed'},{name:'3/1 ARM'},{name:'5/1 ARM'}, {name:'jumbo 30-year fixed'},{name:'jumbo 15-year fixed'},{name:'jumbo 3/1 ARM'},{name:'jumbo 5/1 ARM'}];
	$scope.items = ['2017','2018','2019','2020','2021','2022','2023','2024','2025','2026','2027','2028','2029','2030','2031','2032','2033','2034','2035','2036','2037','2038','2039','2040','2041','2042','2043','2044','2045','2046','2047','2048','2049','2050','2051','2052','2053','2054','2055','2056'];
	$scope.downPayments = [ {name:'0'},{name:'1'},{name:'2'},{name:'3'},{name:'4'},{name:'5'},{name:'6'},{name:'7'},{name:'8'},{name:'9'},{name:'10'},{name:'15'},{name:'20'},{name:'25'},{name:'30'},{name:'35'},{name:'40'},{name:'45'},{name:'50'},{name:'60'},{name:'70'},{name:'80'},{name:'90'},{name:'100'}];
	$scope.properties = [{name:'50'},{name:'60'},{name:'70'},{name:'80'},{name:'90'},{name:'100'}];
    $scope.frequency = [ {string: 'Replace first house'},{string: 'Second house for personal use'}, {string: 'Turn first house into a rental'},{string: 'Turn second house into a rental'}];
    $scope.creditScores = [{name:'760-850'},{name:'700-759'},{name:'680-699'},{name:'660-679'},{name:'640-659'},{name:'620-639'}];
				//$scope.show = 0;
				$scope.formData1={};
                $scope.formdata={};
                $scope.formdata.houseInsuranceAmount=500;
                $scope.formdata.goalDuration=30;
                $scope.formdata.PMI=0.625;
                $scope.formdata.taxrate=1;
                $scope.goalHouseData={};
                $scope.message="Starting Year"
                $scope.progressbar=0;
                $scope.formdata.plan_name=(decodeURIComponent(hashes));
                $scope.formdata.down_payment="20";
                $scope.formdata.desired_location="select state...";
                $scope.formdata.desired_locationcity="select city...";
                $scope.formdata.goalYear=$scope.date.getFullYear();
                //$scope.formdata.frequency="Second house for personal use";
                $scope.houseMarketValue=true;
                $scope.brokerCommission=true;
                $scope.otherAmount=true;
                $scope.firstHouseAmount=true;
                $scope.rentIncome=true;
                $scope.oldHouseValue=true;
                $scope.rentExpense=true;
                $scope.rentActivity=true;
                $scope.oldOwnHouseMortgageAmt={};
                $scope.formdata.rentalExpenses=1;
                $scope.goalYears=[];
                for(var i=$scope.date.getFullYear();i<2080;i++)
                	{
                	var a = {
                			"year": i.toString()
                		}
                	$scope.goalYears.push(a);
                	}
               // $scope.houseAppriciationRate=true;
               // $scope.formdata.remainingTaxable=0;

        /*------------------------------------get cities from state---------------*/
        $scope.getCities=function()
        {
        	$scope.formData1.city=$scope.formdata.desired_location;
        	$scope.formData1.actionType="getCities";
        	$scope.disabledCity="true";
        	$scope.msgerr="";
        	/*$scope.citys=['Abanda'];
        	$scope.formdata.desired_locationcity="Abanda";*/
        	 $http({
					method: 'POST',
					 url: 'AutoComplete',
					data: $.param($scope.formData1),
					headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
				}).then(function(result) {
			$scope.citys=result.data;
			
			$scope.citys.sort();
			$scope.formdata.desired_locationcity=$scope.citys[0];
			$scope.disabledCity=false;
			
						 }, function(error) {		
							 window.location.href="index.jsp";
						 });
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
        
  	  $scope.onloadCreateData=function()
       {
  		//alert("onload");
      	$scope.formdata.actionHomeType="houseStatus";
/*--------------------- AJAX FOR HOUSE Status-------------------------------------*/
       		 $http({
 	  		  method: 'POST',
 	  		  url: 'Goalhouse',
 	  		  data: $.param($scope.formdata),
 	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
 				}).then(function(result) {
 				
 				$scope.goalHouseStatusData=result.data;
 			    if($scope.goalHouseStatusData.houseGoalPresence!=undefined && $scope.goalHouseStatusData.houseStatus=="Rent" && $scope.goalHouseStatusData.houseGoalPresence.goalType=="Home" )
			    	{
 			    	//alert("rent..");
 			    	$scope.formdata.frequency="Second house for personal use";
			    	$scope.secondHouseOption=true;
			    	$scope.goalYears=[];
			    	//$scope.formdata.goalYear="";
			    	  
			    	for(var i=$scope.goalHouseStatusData.houseGoalPresence.startYear+1;i<2080;i++)
	                	{
			    		var a = {
		                			year: i.toString()
		                		}
	                	$scope.goalYears.push(a);
	                	}
			    	  $scope.formdata.goalYear=$scope.goalYears[0].year;
			    	}
 		
 			    else if($scope.goalHouseStatusData.houseStatus=="Own")
 			    	{
 			    	//alert("own..");
 			    	$scope.formdata.frequency="Second house for personal use";
 			    	$scope.secondHouseOption=true;
 			    	}
 			
 			    else {
 			    	//alert();
 			    	$scope.secondHouseOption=false;
 			    	$scope.show = 0;
 			    	$scope.formdata.frequency="firstHouse";
 			    }
 	
 	  		 }, function(error) {
 		  $scope.message=result.data;
 		  
 	  		 });
       }

    $scope.selectSecondHouseAs=function()
    {
    	//$scope.formdata.goalYear=$scope.goalHouseStatusData.houseGoalPresence.startYear+5;
   	 if($scope.formdata.frequency=="Replace first house")
   		 {
   		 $scope.houseMarketValue=false;
          $scope.brokerCommission=false;
          $scope.otherAmount=false;
          $scope.firstHouseAmount=true;
          $scope.rentIncome=true;
          $scope.oldHouseValue=true;
          $scope.rentExpense=true;
          $scope.rentActivity=true;
         // $scope.houseAppriciationRate=false;
          if($scope.goalHouseStatusData.houseStatus=="Own")
     	 {
     	 $scope.formdata.houseMarketPrice=Math.round($scope.goalHouseStatusData.sellingPriceOwnHouse);
     	 }
      else
     	 {
     	 $scope.formdata.houseMarketPrice=Math.round($scope.goalHouseStatusData.sellingPrice);
     	 }
   		 }
   	 else if($scope.formdata.frequency=="Turn first house into a rental"||$scope.formdata.frequency=="Turn second house into a rental")
   		 {
   		// alert( $scope.formdata.rentalActivity);
   		$scope.houseMarketValue=true;
         $scope.brokerCommission=true;
         $scope.otherAmount=true;
         $scope.firstHouseAmount=true;
         $scope.rentIncome=false;
         $scope.oldHouseValue=true;
        // $scope.houseAppriciationRate=true;
         if($scope.formdata.frequency=="Turn first house into a rental")
        	 {
         $scope.oldHouseValue=false;
        	 }
         $scope.rentExpense=false;
         $scope.rentActivity=false;
         $scope.formdata.rentalActivity="yes";
         $scope.formdata.propertyValForRent="300000";
         if($scope.goalHouseStatusData.houseStatus=="Own")
        	 {
        	 $scope.formdata.firstHousePrinciple=$scope.goalHouseStatusData.firstHousePrinciple;
        	 }
         else
        	 {
        	 $scope.formdata.firstHousePrinciple=$scope.goalHouseStatusData.houseGoalPresence.principal_amount;
        	 }
   		 }
   	 else{
   	   $scope.houseMarketValue=true;
        $scope.brokerCommission=true;
        $scope.otherAmount=true;
        $scope.firstHouseAmount=true;
        $scope.rentIncome=true;
        $scope.oldHouseValue=true;
        $scope.rentExpense=true;
        $scope.rentActivity=true;
       // $scope.houseAppriciationRate=false;
   	 }
    }
    
    $scope.secondHouseCal=function()
    {
 	   //alert("insidee function");
 	   $scope.formdata.brokerCommissionAmount=Math.round((5/100)*($scope.formdata.houseMarketPrice));
 	   $scope.proratedPropertyTax=(1/100)*($scope.formdata.houseMarketPrice);
 	   $scope.escrowFee=(50/100)*($scope.proratedPropertyTax);
 	   $scope.formdata.otherCost= Math.round($scope.proratedPropertyTax+$scope.escrowFee+1000);
 	   $scope.costInSelling1stPrice= Math.round($scope.formdata.brokerCommissionAmount+ $scope.formdata.otherCost);
 	   //alert($scope.costInSelling1stPrice);
    }
    
  	$scope.getRemainingMortgage=function()
  	{
  		//alert("qqq");
  	//alert("to find remainig mortgage"+$scope.goalHouseStatusData.houseGoalPresence.startYear+"  "+$scope.formdata.goalYear);
  		 if($scope.goalHouseStatusData.houseGoalPresence!=undefined && $scope.goalHouseStatusData.houseStatus=="Rent" && ($scope.formdata.frequency=="Replace first house" ||$scope.formdata.frequency=="Turn first house into a rental" )&& $scope.goalHouseStatusData.houseGoalPresence.startYear<$scope.formdata.goalYear){

  	  		$scope.formdata.actionHomeType="oldHouseRemaningMortgage";
  	  		//alert(JSON.stringify($scope.formdata));
  	  		/*--------------------- AJAX FOR old HOUSE Mortgage value-------------------------------------*/
  	  		       		 $http({
  	  		 	  		  method: 'POST',
  	  		 	  		  url: 'Goalhouse',
  	  		 	  		  data: $.param($scope.formdata),
  	  		 	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
  	  		 				}).then(function(result) {
  	  		 				
  	  		 				$scope.oldHouseRemainingMortgage=result.data;
  	  		 	//	alert("old jkhhkhouse remaning mortgage "+JSON.stringify($scope.oldHouseRemainingMortgage));
  	  		 		//alert("oldHouseremamingmortgage"+$scope.oldHouseRemainingMortgage.remaningMortgageOldhouse);
  	  		 		$scope.remaningMortgageOldHouse=$scope.oldHouseRemainingMortgage.remaningMortgageOldhouse;
  	  		 		$scope.oldHouseMonthlyMortgage=$scope.oldHouseRemainingMortgage.monthlyMortgage;
  	  		 		//alert($scope.oldHouseMonthlyMortgage+"monthlypay");
  	  			 if($scope.formdata.frequency=="Replace first house"){
  	  				// alert(" "+$scope.formdata.brokerCommissionAmount+" "+$scope.formdata.otherCost);
  	  			  $scope.costInSelling1stPrice= $scope.formdata.brokerCommissionAmount+ $scope.formdata.otherCost;
  	  			 // alert($scope.costInSelling1stPrice+ " "+$scope.remaningMortgageOldHouse);
  	  		 		$scope.moneyRecivedFromHouse1=($scope.formdata.houseMarketPrice)-($scope.costInSelling1stPrice)-($scope.remaningMortgageOldHouse);
  	  		 		//alert("money recived "+$scope.moneyRecivedFromHouse1);
  	  		 	if($scope.moneyRecivedFromHouse1<0)
  	  		 		{
  	  		 		//alert("inside negative");
  	  			$scope.moneyRecivedFromHouse1=0;
  	  			$scope.progressBar0();
  	  		 		}
  	  			 }
  	  		
  	  		   		 }, function(error) {
  	  		 		  $scope.message=result.data;
  	  		 		  
  	  		 	  		 });
  	  	}
  		/* else if($scope.formdata.frequency="Second house for personal use")
  			 {
  			 $scope.progressBar0();
  			 }*/
  	}
 $scope.downPaymentCalculation=function()
 {
	// alert("remainingTaxable ");
	$scope.formdata.remainingTaxable=$scope.moneyRecivedFromHouse1-$scope.formdata.down_payment;
	//alert("remainingTaxable "+$scope.formdata.remainingTaxable);
		 if($scope.formdata.remainingTaxable<0)
			 {
			 $scope.formdata.remainingTaxable=0;
			 $scope.formdata.downPaymentForAccumulation=$scope.formdata.down_payment-$scope.moneyRecivedFromHouse1;
			// alert("downPaymentForAccumulation"+$scope.formdata.downPaymentForAccumulation);
			 }
 }
 $scope.profitLossCal=function()
 {
	  //alert($scope.formdata.goalYear);
	//alert("profit loss"+$scope.formdata.firstHousePrinciple);
	/* $scope.valueOfProperty=($scope.formdata.propertyValForRent/100)*$scope.formdata.firstHousePrinciple;*/
	 $scope.secondHousepropertyTax=(1/100)*$scope.formdata.firstHousePrinciple;
	 $scope.rentalExpense=($scope.formdata.rentalExpenses/100)*$scope.formdata.firstHousePrinciple;
	//alert("  $scope.valueOfProperty "+ $scope.valueOfProperty);
	 $scope.formdata.rentalExpense=Math.round($scope.rentalExpense);
	 $scope.depreciation=$scope.formdata.propertyValForRent/27.5;
	// alert( $scope.depreciation +" "+ $scope.rentalExpense+" "+" "+$scope.secondHousepropertyTax+" "+$scope.oldHouseMonthlyMortgage);
	//alert($scope.oldHouseMonthlyMortgage +" " + $scope.rentalExpense +"   "+$scope.secondHousepropertyTax);
	 $scope.cost=(12*$scope.oldHouseMonthlyMortgage)+ $scope.rentalExpense;
	 //$scope.cost=(12*$scope.oldHouseMonthlyMortgage)+ $scope.secondHousepropertyTax+ $scope.rentalExpense;
	//alert("cost"+ $scope.cost);
	 $scope.costWithDeprection=$scope.cost+ $scope.depreciation;
	//alert($scope.costWithDeprection);
	 $scope.cashFlow=($scope.formdata.rentalIncome*12)-$scope.cost;
	 $scope.formdata.profitOrLossCal=Math.round(($scope.formdata.rentalIncome*12)-$scope.costWithDeprection);
	 $scope.formdata.deprectionAmount= $scope.costWithDeprection;
//	alert("profit loss 123 "+$scope.formdata.profitOrLossCal +"      "+ $scope.formdata.deprectionAmount);
	 
 }
 
  	   $scope.progressbar0=function() {  
      	 $('#progress_bar').css('width', '10%');
      	 $scope.show = 0;
      	$scope.secondHouseCal();
       	$scope.secondHouseOption=false;
       }
  
        /*------------------------------------------------------------------------------------------*/
              
       $scope.progressBar0=function() {  
    		$scope.getRemainingMortgage();
      	 $('#progress_bar').css('width', '20%');
      	/*  $('#progress_bar').css('width', '100%');
      	 $('#dialog_confirm_map').modal('hide'); */
      	//alert("old house year "+$scope.goalHouseStatusData.houseGoalPresence.startYear);
      	//alert(" 2nd house year"+$scope.formdata.goalYear);
      	 
     	  if(($scope.goalHouseStatusData.houseGoalPresence!=undefined )&& $scope.goalHouseStatusData.houseGoalPresence.startYear>=$scope.formdata.goalYear)
		  {
     //	alert("year alert");
		$scope.msgerr="Start year of the second house should not be less than the start year 1st house !!";
		  }
			
     	  else{
     		 // alert("without entering year validation");
     		$scope.msgerr="";
      	 $scope.message="Location";
  	 
  	/*$scope.formdata.principal_amount=$scope.formdata.firstname2;*/
  	// $scope.formdata.taxrate="10%";
  	 //$scope.formdata.creditsc="7";
                    $scope.show = 1;
  	              $scope.show1 = 1;
  	              $scope.secondHouseOption=false;

   }

       }

         $scope.progressBar=function() {  
        	 if($scope.formdata.desired_location==null||$scope.formdata.desired_location==undefined||$scope.formdata.desired_location==""||$scope.formdata.desired_location=="select state..." ){
                 //////alert("fsdf");
                 
 			$scope.msgerr="location is empty !!";
 			//$("#ad1").text($scope.msgerr);
     		
             } 
        	 else if ($scope.formdata.desired_locationcity==null||$scope.formdata.desired_locationcity==""||$scope.formdata.desired_location==undefined||$scope.formdata.desired_locationcity=="select city...")
        		 {
        		 $scope.msgerr="city is empty !!";
        		 }
        	 else{
        		 $scope.countyData1={};
        		 $scope.countyData1.city=$scope.formdata.desired_location;
        		 $scope.countyData1.country=$scope.formdata.desired_locationcity;
        		 $scope.countyData1.actionType = "getCounty";
        			$http({
        				method : 'POST',
        				url : 'AutoComplete',
        				data : $.param($scope.countyData1),
        				headers : {
        					'Content-Type' : 'application/x-www-form-urlencoded'
        				}
        			}).then(function(result) {
        				$scope.formdata.county = result.data;
        				
        				//alert(JSON.stringify(result.data));
        				
        				$scope.formdata.actionHomeType="calhousevalue";
        			       /*--------------------- AJAX FOR HOUSE VALUE-------------------------------------*/
        			        		 $http({
        			  	  		  method: 'POST',
        			  	  		  url: 'Goalhouse',
        			  	  		  data: $.param($scope.formdata),
        			  	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
        			  				}).then(function(result) {
        			  				
        			  				$scope.goalHouseData=result.data;
        			  				if(angular.equals($scope.goalHouseData.status,"success"))
        			  				{
        			  					
        			  				  // window.location.href="#";
        			  			     /*   $scope.errMessage="Goal created successfully !!";
        			  			       $("#myModal").modal("show"); */
        			  					$scope.formdata.firstname2=$scope.goalHouseData.housevalue;
        			  					$scope.msgerr3="It is a median listing price in the pass year";
        			  				 	 $('#progress_bar').css('width', '35%');
                			        	 $scope.message="House Value";
                			        		// var email = $('input[name="firstname"]').val();
                			    		//////alert("email"+email);
                			    		 $scope.formdata.location=$scope.formdata.desired_location;
                			    		
                			        	 //$scope.formdata.location=('#ad').val();
                			        		 $scope.show = 3;
                			        	 $scope.show1 = 2;
                			        	 //$scope.getRemainingMortgage();
        			  			       
        			  				}       
        			  	
        			  	  		 }, function(error) {
        			  		  $scope.message=result.data;
        			  		  
        			  	  		 });
        			        		 
        			        		/*--------------------------------------------------------------------------------------*/
        			           
        			            
        			}, function(error) {
        				window.location.href = "index.jsp";
        			});
        		 
         }
        		 
        		 
        		
        	
         }  
         $scope.showLoanOption=function()
         {
         	  var downPayment  = +$scope.formdata.down_payment;
        	  var principalAmount = +$scope.formdata.firstname2;
        	  if($scope.formdata.down_payment==null||$scope.formdata.down_payment==undefined|| $scope.formdata.down_payment==""){
                  //////alert("fsdf");
        		  //alert("2");
        		  $scope.masked = false;
  			$scope.msgerr2="down payment is empty !!";
      		
              }
        	  else if(principalAmount <= downPayment ){
                 
        		  $scope.masked = false;
  			$scope.msgerr2="Down Payment value should be less than House value !!";
  			//$scope.isDisabled=false;
  			//disable();
      		
              }
        	  else{
        		  $scope.msgerr2="";
        	 $('#progress_bar').css('width', '80%');
        	 $scope.formdata.goalDuration="30-year fixed";
        	 $scope.show = 23;
        	  }
         }
         $scope.showHouseApprecition=function()
         {
        	// alert("123");
        	 $('#progress_bar').css('width', '85%');
        	 $scope.formdata.appreciationRate="1.4";
        	 $scope.show = 25;
         }
         $scope.showCreditScore=function()
         {
        	 $('#progress_bar').css('width', '90%');
        	 $scope.formdata.creditsc="760-850";
        	 $scope.show = 24;
         }
          $scope.progressBar1=function() { 
        	// alert("1");
        	  $scope.masked = true;
        	  var downPayment  = +$scope.formdata.down_payment;
        	  var principalAmount = +$scope.formdata.firstname2;
        	  if($scope.formdata.down_payment==null||$scope.formdata.down_payment==undefined|| $scope.formdata.down_payment==""){
                  //////alert("fsdf");
        		  //alert("2");
        		  $scope.masked = false;
  			$scope.msgerr2="down payment is empty !!";
      		
              }
        	  else if(principalAmount <= downPayment ){
                  //////alert("fsdf");
        		  //alert("3");
        		  $scope.masked = false;
  			$scope.msgerr2="Down Payment value should be less than House value !!";
  			//$scope.isDisabled=false;
  			//disable();
      		
              }
        	  else {
        		//  alert("4");
        		 //$scope.masked = true;
            	  $('#progress_bar').css('width', '100%');
             	 $('#dialog_confirm_map').modal('hide');
            	 
        	$scope.message="DETAILS";
        		// var email = $('input[name="firstname"]').val();
    		// ////alert("email"+email);
    		// $scope.formdata.location=$scope.formdata.firstname1;
        	$scope.formdata.downPayment=$scope.formdata.down_payment;
        		 $scope.show =5;
        	 $scope.show1 = 4;
        	 if($scope.goalHouseStatusData.houseGoalPresence!=undefined)
    		 {
    	//	 alert("before ajax new downpayment");
    		 $scope.formdata.down_payment=$scope.moneyRecivedFromHouse1;
    		 }

        	 $scope.formdata.actionHomeType="insert";
        	//alert(JSON.stringify($scope.formdata));
  			$http({
  	  		  method: 'POST',
  	  		  url: 'Goalhouse',
  	  		  data: $.param($scope.formdata),
  	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
  				}).then(function(result) {
  				
  				$scope.goalHouseData=result.data;
  				
  				if(angular.equals($scope.goalHouseData.status,"success"))
  				{
  					//alert("5")
  					//$scope.masked = true;
  				  // window.location.href="#";
  			     /*   $scope.errMessage="Goal created successfully !!";
  			       $("#myModal").modal("show"); */
  				
  					 $scope.formdata.goal_id = $scope.goalHouseData.goal_id; // assign value to goal id
  		  			  window.location.href="GoalHouseEdit.jsp?goalId="+ $scope.formdata.goal_id; 
  		  			   console.log("message"+$scope.message);
  		  			$scope.masked = false;
  		  			
  				}       
  				else
  					{
  					$scope.masked = false;
  					 $scope.errMessage="Goal is not feasible since you are not having sufficient funds !!";
  	            	  $("#myModal").modal('show');
  					//alert();
  					}
  	
  	  		 }, function(error) {
  		  $scope.message=result.data;
  		  
  	  		 });
  			
              }
         }   
         $scope.progressBar2=function() {  
        	 var principalAmount = +$scope.formdata.firstname2;
        	 if($scope.formdata.firstname2==null||$scope.formdata.firstname2==undefined||$scope.formdata.firstname2==""){
                 //////alert("fsdf");
 			$scope.msgerr3=" House Value should not be empty !!";
 			//$scope.isDisabled=false;
 			//disable();
     		
             }
        	 else if(principalAmount==0){
              
 			$scope.msgerr3=" House Value cannot be zero !!";
 			//$scope.isDisabled=false;
 			//disable();
     		
             } 
        	 else{
            	 $('#progress_bar').css('width', '70%');
            	/*  $('#progress_bar').css('width', '100%');
            	 $('#dialog_confirm_map').modal('hide'); */
        	 
                  $scope.message="Monthly Debt";
        	 
        	$scope.formdata.principal_amount=$scope.formdata.firstname2;
        	// $scope.formdata.taxrate="10%";
        	 //$scope.formdata.creditsc="7";
        	//alert("hii");
        	//alert("housestatus"+$scope.goalHouseStatusData.houseStatus);
        	//alert("frequency.."+$scope.formdata.frequency);
        	 if($scope.goalHouseStatusData.houseStatus=="Own" && ($scope.formdata.frequency=="Replace first house"||$scope.formdata.frequency=="Turn first house into a rental")||$scope.formdata.frequency=="Turn second house into a rental")
        		 {
        		// alert("new downpayment");
        		 //$scope.getRemainingMortgage();
        		 if(($scope.goalHouseStatusData.houseStatus=="Own" && $scope.formdata.frequency=="Replace first house" ||$scope.formdata.frequency=="Turn first house into a rental"||$scope.formdata.frequency=="Turn second house into a rental")){
        		 $scope.formdata.actionHomeType="ownHouseRemaningMortgage";
       	  		//alert("inside own"+JSON.stringify($scope.formdata));
       	  		/*--------------------- AJAX FOR old HOUSE Mortgage value-------------------------------------*/
       	  		       		 $http({
       	  		 	  		  method: 'POST',
       	  		 	  		  url: 'Goalhouse',
       	  		 	  		  data: $.param($scope.formdata),
       	  		 	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
       	  		 				}).then(function(result) {
       	  		 				
       	  		 				$scope.oldOwnHouseMortgageAmt=result.data;
       	  		 		//alert("own house remaning mortgage "+JSON.stringify($scope.oldOwnHouseMortgageAmt));
       	  		 		//alert($scope.oldOwnHouseMortgageAmt.oldOwnHouseMortgage);
       	  		 		$scope.remaningMortgageOldHouse=$scope.oldOwnHouseMortgageAmt.oldOwnHouseMortgage;
       	  		 		$scope.monthlyMortgageOwnHouse=$scope.oldOwnHouseMortgageAmt.monthlyMortgageOwn;
       	  		 		$scope.oldHouseMonthlyMortgage=$scope.monthlyMortgageOwnHouse;
       	  		 		$scope.firstHousePrinciple=$scope.oldOwnHouseMortgageAmt.firstHousePrinciple;
       	  		 		//alert("monthlyMortgageOwnHouse"+$scope.monthlyMortgageOwnHouse+	$scope.monthlyMortgageOwnHouse);
       	  		 		if($scope.formdata.frequency=="Replace first house"){
       	  		 			//alert(" brokerage "+$scope.formdata.brokerCommissionAmount+" "+$scope.formdata.otherCost);
       	  		 	  $scope.costInSelling1stPrice= $scope.formdata.brokerCommissionAmount+ $scope.formdata.otherCost;
       	  		 	 // alert(" "+ $scope.costInSelling1stPrice+" "+$scope.remaningMortgageOldHouse+" "+$scope.formdata.houseMarketPrice);
       	  		 		$scope.moneyRecivedFromHouse1=($scope.formdata.houseMarketPrice)-($scope.costInSelling1stPrice)-($scope.remaningMortgageOldHouse);
       	  		 	if($scope.moneyRecivedFromHouse1<0)
       	  		 		{
       	  		 		//alert("inside negative");
       	  			$scope.moneyRecivedFromHouse1=0;
       	  		 		}
       	 		// alert("hai.."+$scope.moneyRecivedFromHouse1);
        		 $scope.formdata.down_payment=$scope.moneyRecivedFromHouse1;
        		// alert("newDownpayment ...>>>"+$scope.formdata.down_payment);
        		  $scope.show = 21;
        		  $scope.show1 = 3;
       	  		 				}
       	  		 	 if($scope.formdata.frequency=="Turn first house into a rental" ||$scope.formdata.frequency=="Turn second house into a rental")
       	    		 {
       	    		//alert("Turn first house into a rental..");
       	    		 $scope.show = 22;
       	    		 $scope.show1 = 3;
       	    		 }
       	  		   		 }, function(error) {
       	  		 		  $scope.message=result.data;
       	  		 		  
       	  		 	  		 });
       	  	}
        		 
        		 }
        	 else if($scope.goalHouseStatusData.houseGoalPresence!=undefined && $scope.goalHouseStatusData.houseStatus=="Rent" &&( $scope.formdata.frequency=="Turn first house into a rental"||$scope.formdata.frequency=="Turn second house into a rental"))
    		 {
    	//	alert("Turn first house into a rental..22");
    		 $scope.show = 22;
    		 $scope.show1 = 3;
    		 }
        	 else if($scope.goalHouseStatusData.houseGoalPresence!=undefined && $scope.goalHouseStatusData.houseStatus=="Rent" && $scope.formdata.frequency=="Replace first house" )
        		 {
        	//	 alert("inside else if..");
    			// alert(" "+$scope.formdata.brokerCommissionAmount+" "+$scope.formdata.otherCost);
 	  			  	$scope.costInSelling1stPrice= $scope.formdata.brokerCommissionAmount+ $scope.formdata.otherCost;
 	  			 // 	alert(" "+$scope.costInSelling1stPrice+" "+$scope.remaningMortgageOldHouse+" "+$scope.formdata.houseMarketPrice);
 	  		 		$scope.moneyRecivedFromHouse1=($scope.formdata.houseMarketPrice)-($scope.costInSelling1stPrice)-($scope.remaningMortgageOldHouse);
 	  		 		//alert("money recived "+$scope.moneyRecivedFromHouse1);
 	  		 	if($scope.moneyRecivedFromHouse1<0)
 	  		 		{
 	  		 	$scope.moneyRecivedFromHouse1=0;
 	  		 		}
        		 $scope.formdata.down_payment=$scope.moneyRecivedFromHouse1;
        		 $scope.show = 21;
        		 $scope.show1 = 3;
        		 
        		 }
        	 else{
        		// alert();
        		 $scope.show = 4;
	              $scope.show1 = 3;

        	 }
                          
 		

             }
        	 //$scope.message="DETAILS";
             }
       
  
         $scope.progressBar5=function() {  
        	 $('#progress_bar').css('width', '80%');
       if($scope.formdata.frequency=="Turn first house into a rental")
        		 {
    	   		if($scope.formdata.propertyValForRent>$scope.formdata.firstHousePrinciple)
    	   			{
    	   			//alert("it should be lesser than old house val");
    	   			$scope.msgerr4="Property value cannnot be more the old house value !!";
    	   			}
    	   		else{
    	   			$scope.msgerr4="";
        	 	 $scope.profitLossCal();
        	 	 $scope.show = 4;
                 $scope.show1 = 3;
    	   		}
        		 }
       if($scope.formdata.frequency=="Turn second house into a rental")
		 {
 		if($scope.formdata.propertyValForRent>$scope.formdata.firstname2)
 			{
 			//alert("it should be lesser than old house val");
 			$scope.msgerr4="Property value cannnot be more the house value !!";
 			}
 		else{
 			$scope.msgerr4="";
	 	 $scope.profitLossCal();
	 	 $scope.show = 4;
       $scope.show1 = 3;
 		}
		 }
         }
     			 
			
		

         $scope.checkform1=function(){
     				var downPayment = +$scope.formdata.downPayment;
     				var principalAmount = +$scope.formdata.principal_amount;
     		////alert("commiting");	////alert($scope.formdata.plan_name);	 
        	  if($scope.formdata.downPayment==""||$scope.formdata.principal_amount==""){
        		  window.location.href="#";
			       $scope.errMessage="Please Enter all Basic details !!";
			       $("#myModal").modal("show");
			       					 }
        	  else if(principalAmount ==0)
     		 {
     		 window.location.href="#";
     	       $scope.errMessage="Principal amount cannot be zero !!";
     	       $("#myModal").modal("show");
     		 }
        	  else if(principalAmount <= downPayment){
        		  window.location.href="#";
			       $scope.errMessage="Down Payment should less than House value !!";
			       $("#myModal").modal("show");
			       					 }
	 else{
     		
$scope.formdata.actionHomeType="update";
/* if($scope.formdata.goalDuration=="30-year fixed")
	{
	$scope.formdata.goalDuration=30;
	}
if($scope.formdata.goalDuration=="15-year fixed")
	{
	$scope.formdata.goalDuration=15;
	
	} 
if($scope.formdata.goalDuration=="5/1 ARM")
{
$scope.formdata.goalDuration=15;

} */
$http({
		  method: 'POST',
		  url: 'Goalhouse',
		  data: $.param($scope.formdata),
		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).then(function(result) {
		
		$scope.goalHouseData=result.data;
		//alert("rreesulet "+JSON.stringify($scope.goalHouseData));
		if(angular.equals($scope.goalHouseData.status,"success"))
			{
			   window.location.href="#";
		       $scope.errMessage="Goal updated successfully !!";
		       $("#myModal").modal("show");
			}
		$scope.errormessage=$scope.goalHouseData.message;
		console.log("message"+$scope.message);
		if(!angular.equals($scope.errormessage,"Please Insert Basic Information"))
			{
		$scope.formdata.taxrate=$scope.goalHouseData.property_tax;
		$scope.formdata.PMI=$scope.goalHouseData.PMI;
		$scope.formdata.houseInsuranceAmount=$scope.goalHouseData.homeInsurance;
			}
		 }, function(error) {
 $scope.message=result.data;
 
		 });
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
//--------------------------------------------------------------------------------------------------------------

app1.controller('GoalHouseEditController',function($scope,$http){
	$scope.sessionDetails={};
	 $scope.sessionDelete={};
           $scope.formdata={};
           $scope.temp={};
           $scope.formData1={};
           $scope.masked = false;
           $scope.planName="";
           $scope.goalHouseEditData={};
           $scope.houseMarketValue=true;
           $scope.brokerCommission=true;
           $scope.otherAmount=true;
           $scope.firstHouseAmount=true;
           $scope.rentIncome=true;
           $scope.oldHouseValue=true;
           $scope.rentExpense=true;
           $scope.rentActivity=true;
           $scope.rentPropertyValue=true;
           $scope.reantalExpenseDisable=true;
           $scope.houseoptionDisable=true;
           $scope.oldOwnHouseMortgageAmt={};
           $scope.oldHouseRemainingMortgage={};
           $scope.frequency = [ {string: 'Replace first house'},{string: 'Second house for personal use'}, {string: 'Turn first house into a rental'},{string: 'Turn second house into a rental'}];
           $scope.citys=['select city...'];
           $scope.properties = [{name:'50'},{name:'60'},{name:'70'},{name:'80'},{name:'90'},{name:'100'}];
           $scope.items = ['2017','2018','2019','2020','2021','2022','2023','2024','2025','2026','2027','2028','2029','2030','2031','2032','2033','2034','2035','2036','2037','2038','2039','2040','2041','2042','2043','2044','2045','2046','2047','2048','2049','2050','2051','2052','2053','2054','2055','2056'];
      	    $scope.states = [{name:'ALABAMA'},{name:'ALASKA'},{name:'ARIZONA'},{name:'ARKANSAS'},{name:'CALIFORNIA'},{name:'COLORADO'},{name:'CONNECTICUT'},{name:'DELAWARE'},{name:'FLORIDA'},{name:'GEORGIA'},{name:'HAWAII'},{name:'IDAHO'},{name:'ILLINOIS'},{name:'INDIANA'},{name:'IOWA'},{name:'KANSAS'},{name:'KENTUCKY'},{name:'LOUISIANA'},{name:'MAINE'},{name:'MARYLAND'},{name:'MASSACHUSETTS'},{name:'MICHIGAN'},{name:'MINNESOTA'},{name:'MISSISSIPPI'},{name:'MISSOURI'},{name:'MONTANA'},{name:'NEBRASKA'},{name:'NEVADA'},{name:'NEW HAMPSHIRE'},{name:'NEW JERSEY'},{name:'NEW MEXICO'},{name:'NEW YORK'},{name:'NORTH CAROLINA'},{name:'NORTH DAKOTA'},{name:'OHIO'},{name:'OKLAHOMA'},{name:'OREGON'},{name:'PENNSYLVANIA'},{name:'RHODE ISLAND'},{name:'SOUTH CAROLINA'},{name:'SOUTH DAKOTA'},{name:'TENNESSEE'},{name:'TEXAS'},{name:'UTAH'},{name:'VERMONT'},{name:'VIRGINIA'},{name:'WEST VIRGINIA'},{name:'WISCONSIN'},{name:'WYOMING'},{name:'WASHINGTON'},{name:'DISTRICT OF COLUMBIA'}];
			$scope.loans = [ {name:'30-year fixed'},{name:'15-year fixed'},{name:'3/1 ARM'},{name:'5/1 ARM'}, {name:'jumbo 30-year fixed'},{name:'jumbo 15-year fixed'},{name:'jumbo 3/1 ARM'},{name:'jumbo 5/1 ARM'}];
			$scope.downPayments = [ {name:'0'},{name:'1'},{name:'2'},{name:'3'},{name:'4'},{name:'5'},{name:'6'},{name:'7'},{name:'8'},{name:'9'},{name:'10'},{name:'15'},{name:'20'},{name:'25'},{name:'30'},{name:'35'},{name:'40'},{name:'45'},{name:'50'},{name:'60'},{name:'70'},{name:'80'},{name:'90'},{name:'100'}];
		    $scope.creditScores = [{name:'760-850'},{name:'700-759'},{name:'680-699'},{name:'660-679'},{name:'640-659'},{name:'620-639'}];
			$scope.deleteAllCookies=function() {  
				//alert("delete all cookies");
				$scope.disabledCity=false;
			 $scope.citys=['select city...'];
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
			 /*------------------------------------get cities from state---------------*/
	        $scope.getCities=function()
	        {
	        	$scope.formData1.city=$scope.formdata.location;
	        	$scope.formData1.actionType="getCities";
	        	$scope.disabledCity="true";
	        	$scope.msgerr="";
	        	/*$scope.citys=['Abanda'];
	        	$scope.formdata.desired_locationcity="Abanda";*/
	        	 $http({
						method: 'POST',
						 url: 'AutoComplete',
						data: $.param($scope.formData1),
						headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
					}).then(function(result) {
				$scope.citys=result.data;
				if($scope.citys.indexOf($scope.formdata.locationcity)==-1)
					{
				$scope.formdata.locationcity=$scope.citys[0];
					}
				
				$scope.citys.sort();
				$scope.disabledCity=false;
				
							 }, function(error) {		
								 window.location.href="index.jsp";
							 });
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
	        
	        $scope.selectSecondHouseAs=function()
	        {
	      // alert("onchange");
	       	 if($scope.formdata.frequency=="Replace first house")
	       		 {
	       		 $scope.houseMarketValue=false;
	              $scope.brokerCommission=false;
	              $scope.otherAmount=false;
	              $scope.firstHouseAmount=true;
	              $scope.rentIncome=true;
	              $scope.rentExpense=true;
	              $scope.oldHouseValue=true;
	              $scope.rentActivity=true;
	              $scope.rentPropertyValue=true;
	       		 }
	       	 else if($scope.formdata.frequency=="Turn first house into a rental"||$scope.formdata.frequency=="Turn second house into a rental")
	       		 {
	       		$scope.houseMarketValue=true;
	             $scope.brokerCommission=true;
	             $scope.otherAmount=true;
	             $scope.firstHouseAmount=true;
	             $scope.rentIncome=false;
	             $scope.rentExpense=false;
	             if($scope.formdata.frequency=="Turn first house into a rental"){
		             $scope.oldHouseValue=false;

	             }
	             $scope.rentActivity=false;
	             $scope.rentPropertyValue=false;

	       		 }
	       	 else{
	       	   $scope.houseMarketValue=true;
	            $scope.brokerCommission=true;
	            $scope.otherAmount=true;
	            $scope.firstHouseAmount=true;
	            $scope.rentIncome=true;
	            $scope.rentExpense=true;
	            $scope.oldHouseValue=true;
	            $scope.rentActivity=true;
	            $scope.rentPropertyValue=true;

	       	 }
	        }
	        $scope.downPaymentCalculation=function()
	        {
	       	// alert("remainingTaxable ");
	       	$scope.formdata.remainingTaxable=$scope.moneyRecivedFromHouse1-$scope.formdata.downPayment;
	       //	alert("remainingTaxable "+$scope.formdata.remainingTaxable);
	       		 if($scope.formdata.remainingTaxable<0)
	       			 {
	       			 $scope.formdata.remainingTaxable=0;
	       			 $scope.formdata.downPaymentForAccumulation=$scope.formdata.down_payment-$scope.moneyRecivedFromHouse1;
	       			// alert("downPaymentForAccumulation"+$scope.formdata.downPaymentForAccumulation);
	       			 }
	        }
	        $scope.getHousevalue=function()
	        {
	        	$scope.formdata.actionHomeType="calhousevalue";
	        	$scope.formdata.desired_locationcity=$scope.formdata.locationcity;
	        	$scope.formdata.desired_location=$scope.formdata.location;
	  /*--------------------- AJAX FOR HOUSE VALUE-------------------------------------*/
	             		 $http({
	       	  		  method: 'POST',
	       	  		  url: 'Goalhouse',
	       	  		  data: $.param($scope.formdata),
	       	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
	       				}).then(function(result) {
	       				
	       				$scope.goalHouseData=result.data;
	       				if(angular.equals($scope.goalHouseData.status,"success"))
	       				{
	       					$scope.formdata.principal_amount=$scope.goalHouseData.housevalue;
	       					
	       				  // window.location.href="#";
	       			     /*   $scope.errMessage="Goal created successfully !!";
	       			       $("#myModal").modal("show"); */
	       					$scope.formdata.firstname2=$scope.goalHouseData.housevalue.toFixed(2);
	       				}       
	       	
	       	  		 }, function(error) {
	       		  $scope.message=result.data;
	       		  
	       	  		 });
	         
	        }
	       

	        $scope.calculateOldMortgage=function()
	        {
	        //alert("in update...calculateOldMortgage");
	        	if($scope.formdata.frequency!="firstHouse")
	        		{
	        	if($scope.houseStatus=="Rent")
	        		{
	        	//alert("rent");
	      	  		$scope.formdata.actionHomeType="oldHouseRemaningMortgage";
	      	  		/*--------------------- AJAX FOR old HOUSE Mortgage value-------------------------------------*/
	      	  		       		 $http({
	      	  		 	  		  method: 'POST',
	      	  		 	  		  url: 'Goalhouse',
	      	  		 	  		  data: $.param($scope.formdata),
	      	  		 	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
	      	  		 				}).then(function(result) {
	      	  		 				
	      	  		 				$scope.oldHouseRemainingMortgage=result.data;
	      	  		 				//alert("data "+JSON.stringify($scope.oldHouseRemainingMortgage));
	      	  		 		$scope.remaningMortgageOldHouse=$scope.oldHouseRemainingMortgage.remaningMortgageOldhouse;
	      	  		 		$scope.oldHouseMonthlyMortgage=$scope.oldHouseRemainingMortgage.monthlyMortgage;
	      	  		 	 if($scope.frequency=="Replace first house"){
	      	  			  $scope.formdata.brokerCommissionAmount=Math.round((5/100)*($scope.formdata.houseMarketPrice));
	   		     	   $scope.proratedPropertyTax=(1/100)*($scope.formdata.houseMarketPrice);
	   		     	   $scope.escrowFee=(50/100)*($scope.proratedPropertyTax);
	   		     	   $scope.formdata.otherCost=Math.round( $scope.proratedPropertyTax+$scope.escrowFee+1000);
	   		     	   $scope.costInSelling1stPrice= $scope.formdata.brokerCommissionAmount+ $scope.formdata.otherCost;
	   	        	//alert(" fg "+$scope.costInSelling1stPrice+" "+$scope.remaningMortgageOldHouse+" "+$scope.formdata.houseMarketPrice);
	   	  		 		$scope.moneyRecivedFromHouse1=($scope.formdata.houseMarketPrice)-($scope.costInSelling1stPrice)-($scope.remaningMortgageOldHouse);
	   	  		 	//alert("money recived "+$scope.moneyRecivedFromHouse1);
	   	  		 	if($scope.moneyRecivedFromHouse1<0)
	   	  		 		{
	   	  		// 	alert("inside negative");
	   	  			$scope.moneyRecivedFromHouse1=0;
	   	  		 		}
		  		 	$scope.formdata.downPayment=$scope.moneyRecivedFromHouse1;
	      	  		 				}
	      	  		 	 else{
	      	  		 	$scope.formdata.downPayment=$scope.goalHouseEditData.downPaymentRate;
	      	  		 	 }

	      	  		   		 }, function(error) {
	      	  		 		  $scope.message=result.data;
	      	  		 		  
	      	  		 	  		 });
	        		}
	        	else{
	            		 $scope.formdata.actionHomeType="ownHouseRemaningMortgage";
	           	  	//	alert("inside own"+JSON.stringify($scope.formdata));
	           	  		/*--------------------- AJAX FOR old HOUSE Mortgage value-------------------------------------*/
	           	  		       		 $http({
	           	  		 	  		  method: 'POST',
	           	  		 	  		  url: 'Goalhouse',
	           	  		 	  		  data: $.param($scope.formdata),
	           	  		 	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
	           	  		 				}).then(function(result) {
	           	  		 				
	           	  		 				$scope.oldOwnHouseMortgageAmt=result.data;
	           	  		 		//alert("own house remaning mortgage "+JSON.stringify($scope.oldOwnHouseMortgageAmt));
	           	  		 		//alert($scope.oldOwnHouseMortgageAmt.oldOwnHouseMortgage);
	           	  		 		$scope.remaningMortgageOldHouse=$scope.oldOwnHouseMortgageAmt.oldOwnHouseMortgage;
	           	  		 		$scope.monthlyMortgageOwnHouse=$scope.oldOwnHouseMortgageAmt.monthlyMortgageOwn;
	           	  		 		//alert("sadsad"+$scope.monthlyMortgageOwnHouse);
	           	  		 		$scope.oldHouseMonthlyMortgage=$scope.monthlyMortgageOwnHouse;
	   	      	  		 	 if($scope.frequency=="Replace first house"){
	           	  		 		$scope.formdata.brokerCommissionAmount=Math.round((5/100)*($scope.formdata.houseMarketPrice));
	       		     	   $scope.proratedPropertyTax=(1/100)*($scope.formdata.houseMarketPrice);
	       		     	   $scope.escrowFee=(50/100)*($scope.proratedPropertyTax);
	       		     	   $scope.formdata.otherCost= Math.round($scope.proratedPropertyTax+$scope.escrowFee+1000);
	       		     	   $scope.costInSelling1stPrice= $scope.formdata.brokerCommissionAmount+ $scope.formdata.otherCost;
	       	        //	alert(" fg "+$scope.costInSelling1stPrice+" "+$scope.remaningMortgageOldHouse+" "+$scope.formdata.houseMarketPrice);
	       	  		 		$scope.moneyRecivedFromHouse1=($scope.formdata.houseMarketPrice)-($scope.costInSelling1stPrice)-($scope.remaningMortgageOldHouse);
	       	  		 	//alert("money recived "+$scope.moneyRecivedFromHouse1);
	       	  		 	if($scope.moneyRecivedFromHouse1<0)
	       	  		 		{
	       	  		 		//alert("inside negative");
	       	  			$scope.moneyRecivedFromHouse1=0;
	       	  		 		}
			  		 	$scope.formdata.downPayment=$scope.moneyRecivedFromHouse1;
			  		 	if($scope.formdata.goalYear==$scope.goalHouseEditData.startYear){
			  		 	if($scope.moneyRecivedFromHouse1!=$scope.goalHouseEditData.downPaymentRate)
			  		 		{
			  		 		$scope.formdata.downPayment=$scope.goalHouseEditData.downPaymentRate;
			  		 		}
	   	      	  		 	 }
	           	  		 }
	   	      	  		 else{
	 	      	  		 	$scope.formdata.downPayment=$scope.goalHouseEditData.downPaymentRate;
	 	      	  		 	 }
	   	      	  		 	 }, function(error) {
	           	  		 		  $scope.message=result.data;
	           	  		 		  
	           	  		 	  		 });
	           	  	
	        	}
	        
	        		}
	        }
	        $scope.costInSelling=function()
	        {
	        		//alert("in update...costInSelling");
	        		$scope.formdata.brokerCommissionAmount=Math.round((5/100)*($scope.formdata.houseMarketPrice));
  		     	   $scope.proratedPropertyTax=(1/100)*($scope.formdata.houseMarketPrice);
  		     	   $scope.escrowFee=(50/100)*($scope.proratedPropertyTax);
  		     	   $scope.formdata.otherCost=Math.round($scope.proratedPropertyTax+$scope.escrowFee+1000);
  		     	   $scope.costInSelling1stPrice= $scope.formdata.brokerCommissionAmount+ $scope.formdata.otherCost;
  		     	$scope.moneyRecivedFromHouse1=($scope.formdata.houseMarketPrice)-($scope.costInSelling1stPrice)-($scope.remaningMortgageOldHouse);
   	  		 	//alert("money recived "+$scope.moneyRecivedFromHouse1);
   	  		 	if($scope.moneyRecivedFromHouse1<0)
   	  		 		{
   	  		 //	alert("inside negative");
   	  			$scope.moneyRecivedFromHouse1=0;
   	  		 		}
	  		 	$scope.formdata.downPayment=$scope.moneyRecivedFromHouse1;
	        }
	        
	        $scope.RentalExpense=function()
	        {
	        	$scope.formdata.rentalExpenses=(1/100)* $scope.formdata.firstHousePrinciple;
	        }
	        $scope.profitLossCal=function()
	        {
	       //alert("profit loss");
	       	/* $scope.valueOfProperty=($scope.formdata.propertyValForRent/100)* $scope.formdata.firstHousePrinciple;*/
	       	 $scope.secondHousepropertyTax=(1/100)* $scope.formdata.firstHousePrinciple;
	       	 $scope.rentalExpense=$scope.formdata.rentalExpenses;
	        //alert("  $scope.rentalExpense "+ $scope.rentalExpense);
	       	$scope.formdata.rentalExpense= $scope.rentalExpense;
	       	 $scope.depreciation=$scope.formdata.propertyValForRent/27.5;
	      // alert( $scope.depreciation +" "+$scope.formdata.rentalExpenses+" "+" "+$scope.secondHousepropertyTax+" "+$scope.oldHouseMonthlyMortgage);
	       	// $scope.cost=(12*$scope.oldHouseMonthlyMortgage)+ $scope.secondHousepropertyTax+$scope.formdata.rentalExpenses;
	       	 $scope.cost=(12*$scope.oldHouseMonthlyMortgage)+$scope.formdata.rentalExpenses;
	       	 $scope.costWithDeprection=$scope.cost+ $scope.depreciation;
	      //alert($scope.costWithDeprection);
	       	 $scope.cashFlow=($scope.formdata.rentalIncome*12)-$scope.cost;
	       	 $scope.formdata.profitOrLossCal=Math.round(($scope.formdata.rentalIncome*12)-$scope.costWithDeprection);
	    	 $scope.formdata.deprectionAmount=$scope.costWithDeprection;
	    	// alert("deprectionAmount "+ $scope.formdata.deprectionAmount);
	       	 
	        }
/*------------------------------------------------------------------------------------------*/
	      
/*---------------------------- get Intererst Rate-------------------------- */
  $scope.getInterestRate=function()
	        {
	 			 $scope.formdata.actionHomeType="calInterestRate";
	 			 if($scope.formdata.goalDuration=="30-year fixed")
					{
						$scope.formdata.goalDuration=30;
					}
	 			 else if($scope.formdata.goalDuration=="15-year fixed")
					{
						$scope.formdata.goalDuration=15;
					} 
	 			 else if($scope.formdata.goalDuration=="5/1 ARM")
					{
						$scope.formdata.goalDuration=5;
					} 
	             		 $http({
	       	  		  method: 'POST',
	       	  		  url: 'Goalhouse',
	       	  		  data: $.param($scope.formdata),
	       	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
	       				}).then(function(result) {
	       				
	       				$scope.goalHouseData=result.data;
	       				if(angular.equals($scope.goalHouseData.status,"success"))
	       				{
	       					$scope.goalHouseEditData.interest=$scope.goalHouseData.interestRate;
	       				}    
	       				if($scope.formdata.goalDuration==30)
						{
							$scope.formdata.goalDuration="30-year fixed";
						}
		 			 else if($scope.formdata.goalDuration==15)
						{
							$scope.formdata.goalDuration="15-year fixed";
						} 
		 			 else 
						{
							$scope.formdata.goalDuration="5/1 ARM";
						}
	       				$scope.disabledemo();
	       	
	       	  		 }, function(error) {
	       		  $scope.message=result.data;
	       		  
	       	  		 });
	        }
/*---------------------------------------------------------------------------------------------------*/ 
 $scope.load1= function() {
	 $scope.masked = true;
	 $("#success-alert").hide();
	 $("#fail-alert").hide(); 
	
	 $scope.Original=true;
	 $scope.Original1=true;
	 $scope.Original2=true;
	 $scope.Original3=true;
	 $scope.Original5=true;
	 $scope.revertHide=true;
	 $scope.formdata.goal_id=(decodeURIComponent(hashes));
	
	  $scope.formdata.actionHomeType="edit";       		
        			$http({
        			    method: 'POST',
        			    url: 'Goalhouse',
        			    data: $.param($scope.formdata),
        			    headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
        			}).then(function(result) {
        				if(result.data.goalFeasiblity==false)
    					{
    					//alert();
    					$("#fail-warning").show();
    					}
        				$scope.goalHouseEditData=result.data;
        				$scope.resultIntest=result.data.interest;
        				//alert("qtq "+JSON.stringify($scope.goalHouseEditData));
        				$scope.formdata.locationcity=$scope.goalHouseEditData.city;
        				$scope.formdata.creditsc=$scope.goalHouseEditData.creditScore;
        				$scope.formdata.location=$scope.goalHouseEditData.location;
        				$scope.formdata.downPayment=$scope.goalHouseEditData.downPaymentRate;
        				$scope.formdata.principal_amount=$scope.goalHouseEditData.principalAmount;
        				$scope.formdata.houseInsuranceAmount=$scope.goalHouseEditData.homeInsurance;
        				$scope.planName=$scope.goalHouseEditData.plan_name;
        				$scope.goalYear=result.data.startYear;
        				$scope.location=$scope.goalHouseEditData.location;
        				$scope.downPayment=$scope.goalHouseEditData.downPayment;
        				$scope.formdata.downPayment=$scope.goalHouseEditData.downPaymentRate;
        				$scope.frequency=$scope.goalHouseEditData.frequency;
        				$scope.formdata.goalDuration=$scope.goalHouseEditData.loanType;
        				$scope.formdata.appreciationRate=$scope.goalHouseEditData.appreciationRate;
        				//alert($scope.formdata.goalDuration);
        				$scope.houseStatus=$scope.goalHouseEditData.houseStatus;

        				if($scope.frequency!="firstHouse")
        					{
        					$scope.secondHouseOption=true;
        					$scope.formdata.frequency=$scope.frequency;
        			     	 if($scope.frequency=="Replace first house")
        		       		 {
        		       		 $scope.houseMarketValue=false;
        		              $scope.brokerCommission=false;
        		              $scope.otherAmount=false;
        		              $scope.firstHouseAmount=true;
        		              $scope.rentIncome=true;
        		              $scope.rentExpense=true;
        		              $scope.oldHouseValue=true;
        		              $scope.rentActivity=true;
        		              $scope.rentPropertyValue=true;
        		              $scope.downPaymentDropdown=true;
        		              $scope.newDownPayment=false;
        		              $scope.formdata.houseMarketPrice=$scope.goalHouseEditData.houseMarketPrice;
        		              $scope.formdata.brokerCommissionAmount=$scope.goalHouseEditData.brokerCommissionAmount;
        		              $scope.formdata.otherCost=$scope.goalHouseEditData.otherCost;
        		       		 }
        		       	 else if($scope.frequency=="Turn first house into a rental"||$scope.formdata.frequency=="Turn second house into a rental")
        		       		 {
        		       		$scope.houseMarketValue=true;
        		             $scope.brokerCommission=true;
        		             $scope.otherAmount=true;
        		             $scope.firstHouseAmount=true;
        		             $scope.rentIncome=false;
        		             $scope.rentExpense=false;
        		             $scope.oldHouseValue=true;
        		             if($scope.frequency=="Turn first house into a rental")
        		            	 {
            		             $scope.oldHouseValue=false;

        		            	 }
        		             $scope.rentActivity=false;
        		             $scope.rentPropertyValue=false;
        		             $scope.downPaymentDropdown=false;
        		             $scope.newDownPayment=true;
        		            //alert($scope.goalHouseEditData.rentalActivity);
        		             if($scope.goalHouseEditData.rentalActivity=="yes"||$scope.goalHouseEditData.rentalActivity=="true")
        		            	 {
        		            	// alert();
        		            	 $scope.formdata.rentalActivity="yes";
        		            	 }
        		             else{
        		            	 $scope.formdata.rentalActivity="no";
        		             }
        		            // $scope.formdata.rentalActivity=$scope.goalHouseEditData.rentalActivity;
        		             $scope.formdata.rentalIncome=$scope.goalHouseEditData.rentalIncome;
        		             $scope.formdata.propertyValForRent=$scope.goalHouseEditData.propertyValForRent;
        		             $scope.formdata.firstHousePrinciple=$scope.goalHouseEditData.firstHousePrinciple;
        		             $scope.formdata.rentalExpenses= $scope.goalHouseEditData.rentalExpense;
        		       		 }
        					}
        				else{
        					$scope.secondHouseOption=false;
        					$scope.downPaymentDropdown=false;
        					$scope.newDownPayment=true;
        			     	$scope.houseMarketValue=true;
           		            $scope.brokerCommission=true;
           		            $scope.otherAmount=true;
           		            $scope.firstHouseAmount=true;
           		            $scope.rentIncome=true;
           		            $scope.rentExpense=true;
           		            $scope.oldHouseValue=true;
           		            $scope.rentActivity=true;
           		            $scope.rentPropertyValue=true;
          				    $scope.downPaymentDropdown=false;
        		            $scope.newDownPayment=true;
            				//$scope.formdata.downPayment=$scope.goalHouseEditData.downPaymentRate;
        				}
        				$scope.creditsc=$scope.goalHouseEditData.creditScore;
        				$scope.locationcity=$scope.goalHouseEditData.city;
        				$scope.property_tax=$scope.goalHouseEditData.property_tax;
        				$scope.principal_amount=$scope.goalHouseEditData.principalAmount;
        				$scope.houseInsuranceAmount=$scope.goalHouseEditData.homeInsurance;
        				$scope.pmi=$scope.goalHouseEditData.pmi;
        				$scope.interest=$scope.resultIntest;
        				$scope.year=$scope.goalHouseEditData.year;
        				$scope.formdata.goalDuration=$scope.goalHouseEditData.loanType;
        				//$scope.planName=$scope.goalHouseEditData.plan_name;
        				//var loanPeriod=$scope.goalHouseEditData.year;
        				/*if(loanPeriod==30)
        					{
        					$scope.formdata.goalDuration="30-year fixed";
        					}
        				else if(loanPeriod==15)
        					{
        					$scope.formdata.goalDuration="15-year fixed";
        					}
        				else{
        					$scope.formdata.goalDuration="5/1 ARM";
        				}*/
        				
        				$scope.formdata.PMI=$scope.goalHouseEditData.pmi;
        				$scope.formdata.taxrate=$scope.goalHouseEditData.property_tax;
        				$scope.temp.houseInsuranceAmount=$scope.goalHouseEditData.homeInsurance;
        				//$scope.temp.goalDuration=$scope.formdata.goalDuration;
        				$scope.temp.PMI=$scope.formdata.PMI;
        				$scope.temp.taxrate=$scope.formdata.taxrate;
        				$scope.temp.interest=$scope.goalHouseEditData.interest;
        				$scope.errorName='';
        				$scope.errorSuperhero='';
        				$scope.getCities();
        				$scope.formdata.locationcity=$scope.goalHouseEditData.city;
        				$scope.formdata.goalYear=result.data.startYear;
        				$scope.formdata.goalDuration=result.data.loanType;
        		       	if($scope.goalHouseEditData.frequency!="firstHouse"){
            				$scope.calculateOldMortgage();
        		       	}
        				$scope.masked = false;
        				
        			   }, function(error) {
        				   $scope.message=result.data;
        				   $scope.errorName='';
        					$scope.errorSuperhero='';
        			   });
        			 $scope.msgerr="";
        		} 
 /*---------------------Init Disabled-----------------------------*/
	$scope.disabledemo= function() {	
		 	$scope.disabled=true;
		   $scope.disabled1=true;
		   $scope.disabled2=true;
		   $scope.disabled3=true;
		   $scope.disabled4=true;
		   $scope.disabled5=true;
		   $scope.disabled6=true;
		   $scope.disabled7=true;
		   $scope.disabled8=true;
		   $scope.disabled9=true;
		   $scope.disabled10=true;
		   if($scope.temp.interest==$scope.goalHouseEditData.interest){
			   $scope.myStyle1={color:'black'};
			   $scope.revertHide=true;
		   }
		   else{
			   $scope.revertHide=false;
			   $scope.myStyle1={color:'#2ECCFA'};
		   }
		   if($scope.temp.PMI==$scope.formdata.PMI){
			   $scope.myStyle2={color:'black'}; 
		   }
		   if($scope.temp.taxrate==$scope.formdata.taxrate){
			   $scope.myStyle4={color:'black'};
		   }
		   if($scope.temp.goalDuration==$scope.formdata.goalDuration){
			   $scope.myStyle={color:'black'};
		   }
		   if( $scope.temp.houseInsuranceAmount==$scope.formdata.houseInsuranceAmount){
			   $scope.myStyle3={color:'black'};
		   }
		   
		   
		   
		   
		   
			}
	  /*   $(document).keydown(function(e){
	    	 if(e.target.id == "test1"){
	    	     e.preventDefault();      
	    	     e.stopPropagation(); 
	    	     alert("sdsdsdsd");
	    	     
	    	   }
	    }); */

	$(document).click(function(e){
		 if(e.target.id != "test1"){
		$scope.Edit=false;
		$scope.Original=true;
		$scope.Edit1=false;
		$scope.Original1=true;
		$scope.Edit2=false;
		$scope.Original2=true;
		$scope.Original3=true;
		$scope.Edit3=false;
		$scope.Original5=true;
		$scope.Edit5=false;
		 }
	   if(e.target.id == "test"||e.target.id == "test1"){
	     e.preventDefault();      
	     e.stopPropagation(); 
	     
	   }
	   else{
		   var scope = angular.element(
				    document.
				    getElementById("page-top")).
				    scope();
				    scope.$apply(function () {
				        scope.disabledemo();
				    });
		  
	   } 
	});

/* 	$(document).click(function(e){
		   if(e.target.id == "test"){
		    if($scope.disabled8==false){
		     $('.icon8').css({
			        'background-color': 'red',
			        'color': 'white',
			    });
		    }
		   }
		
	  	}); */
 /*-----------------Goto Dashboard back Button--------------*/
 	  $scope.hideWarning=function()
			  {
			 	 $("#fail-warning").hide();
			 	
			 	 
			  }
 $scope.goDashboard=function()
				{
				window.location.href="dashboardUser0.jsp?finName="+ $scope.planName;
				}
 /*----------------------------------------------------------*/
 $scope.checkform1=function(){
//	/ alert("1");
	 
	 //$scope.masked = true;
	    var downPayment = +$scope.formdata.downPayment;
		var principalAmount = +$scope.formdata.principal_amount;
	 	if($scope.formdata.principal_amount==""){
		   window.location.href="#";
	       $scope.errmessage1="Please Enter all Basic details !!";
	       $("#fail-alert").show();  
	        }
	 	else if($scope.formdata.locationcity==""||$scope.formdata.locationcity==undefined)
	 		{
	 		window.location.href="#";
		       $scope.errmessage1="Please select city !!";
		       $("#fail-alert").show();  
	 		}
	    
	   else if(principalAmount ==0)
		 {
		 window.location.href="#";
	       $scope.errmessage1="House Value cannot be zero !!";
	       $("#fail-alert").show();  
		 }
	   else if(principalAmount <= downPayment){
		  window.location.href="#";
	       $scope.errmessage1="Down Payment should less than House value !!";
	       $("#fail-alert").show();  
	       		 }
	   else if($scope.formdata.creditsc=="640 - 659"||$scope.formdata.creditsc=="620 - 639" || $scope.formdata.creditsc=="<= 620")
 		{
	   window.location.href="#";
       $("#creditScoreModal").modal("show");
       	 }
	   else if($scope.formdata.frequency=="Turn first house into a rental" && ($scope.formdata.propertyValForRent>$scope.formdata.firstHousePrinciple) )
	     {
			 window.location.href="#";
		       $scope.errmessage1="Property value cannot be more than old house value !!";
		       $("#fail-alert").show(); 
	     }

		   else if($scope.formdata.frequency=="Turn second house into a rental" && ($scope.formdata.propertyValForRent>principalAmount) )
		     {
				 window.location.href="#";
			       $scope.errmessage1="Property value cannot be more than house value !!";
			       $("#fail-alert").show(); 
		     }
		   else if($scope.formdata.frequency!=undefined && $scope.formdata.frequency!="firstHouse" && $scope.houseStatus=="Rent" && $scope.oldHouseRemainingMortgage.oldHousestartYear>$scope.formdata.goalYear)
		{
			  // alert(" "+$scope.oldHouseRemainingMortgage.oldHousestartYear + " "+$scope.formdata.frequency);
			  // alert(" "+$scope.formdata.goalYear);
			   window.location.href="#";
		       $scope.errmessage1="Goal year cannot be less than old house goal year!!";
		       $("#fail-alert").show();  
		}
      else{
    	  //alert("2");
	  //alert("ajax called");
    	  $("#fail-alert").hide(); 
    	  $scope.masked = true;
	 $scope.formdata.actionHomeType="update";
	 

	$scope.formdata.desired_locationcity=$scope.formdata.locationcity;
	$scope.formdata.interest=$scope.goalHouseEditData.interest;
	
	 if($scope.formdata.frequency=="Replace first house")
     {
	   if($scope.formdata.downPayment<$scope.moneyRecivedFromHouse1)
	   {
	   $scope.formdata.remainingTaxable=$scope.moneyRecivedFromHouse1-$scope.formdata.downPayment;
	   }
	   else{
		   $scope.formdata.remainingTaxable=0;
	   }
	   }
	 if($scope.formdata.frequency=="Turn first house into a rental")
     {
	 $scope.profitLossCal();
     }
	//alert("in update "+JSON.stringify($scope.formdata));
	 $http({
	 		  method: 'POST',
	 		  url: 'Goalhouse',
	 		  data: $.param($scope.formdata),
	 		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
	 		}).then(function(result) {
	 			if(result.data.goalFeasiblity==false)
				{
				//alert();
				$("#fail-warning").show();
				}
	 			$scope.goalHouseEditData=result.data;
	 			//alert(JSON.stringify($scope.goalHouseEditData));
				$scope.goalYear=$scope.goalHouseEditData.startYear;
				$scope.location=$scope.goalHouseEditData.location;
				$scope.downPayment=$scope.goalHouseEditData.downPaymentRate;
				$scope.creditsc=$scope.goalHouseEditData.creditScore;
				$scope.locationcity=$scope.goalHouseEditData.city;
				$scope.property_tax=$scope.goalHouseEditData.property_tax;
				$scope.principal_amount=$scope.goalHouseEditData.principalAmount;
				$scope.houseInsuranceAmount=$scope.goalHouseEditData.homeInsurance;
				$scope.pmi=$scope.goalHouseEditData.PMI;
				$scope.year=$scope.goalHouseEditData.year;
				$scope.formdata.appreciationRate=$scope.goalHouseEditData.appreciationRate;
				//$scope.formdata.goalDuration=$scope.goalHouseEditData.loanType;
				//alert($scope.formdata.goalDuration);
				$scope.load1();
	 			if(angular.equals($scope.goalHouseEditData.status,"success"))
				{ 
	 				//alert("3");
	 				$scope.masked = false;
				   window.location.href="#";
			      /*  $scope.errMessage="Goal updated successfully !!";
			       $("#myModal").modal("show"); */
			     //---------Success modal alert------------------------------------------
			       $scope.errmessage=" Goal updated successfully";			
			       				$("#success-alert").show();
			       				$("#success-alert").fadeTo(2000, 300).slideUp(300, function(){
			                   	$("#success-alert").hide();
			       				});
			       //-----------------------------------------------------------------------
				}
	 			else
	 				{
	 				//alert("4")
	 				$scope.masked = false;
	 				 window.location.href="#";
	 				
	 				 $scope.errMessage="Goal is not feasible since you are not having sufficient funds !!";
	            	  $("#myModal").modal('show');
	            	//  window.location.href=window.location.href.slice(0, -1);
	 				}
	 			//alert("9");
	 		$scope.goalHouseData=result.data;
	 		$scope.getCities();
	 		$scope.formdata.creditsc=$scope.goalHouseEditData.creditScore;
	 		$scope.formdata.PMI=$scope.goalHouseEditData.PMI;
			$scope.formdata.taxrate=$scope.goalHouseEditData.property_tax;
			$scope.formdata.locationcity=$scope.goalHouseEditData.city;
	
			//$scope.planName=$scope.goalHouseEditData.plan_name;
			$scope.formdata.houseInsuranceAmount=$scope.goalHouseEditData.homeInsurance;
			$scope.formdata.goalDuration=$scope.goalHouseEditData.loanType;
	 /*		var loanPeriod=$scope.goalHouseEditData.year;
			if(loanPeriod==30)
				{
				$scope.formdata.goalDuration="30-year fixed";
				}
			else if(loanPeriod==15)
				{
				$scope.formdata.goalDuration="15-year fixed";
				}
			else{
				$scope.formdata.goalDuration="5/1 ARM";
			}*/
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
 $scope.hideFail=function()
 {
	  $("#fail-alert").hide();
	  
 }

	$scope.DeleteGoal=function()
	{ 
		//alert();
		
		$('#myModal1').modal('show');
		}

$scope.deletegoal=function(){
	//alert("del");
	$scope.masked = true;
		$scope.formdata.actionHomeType="deleteGoal";
		
		$scope.formdata.plan_name=$scope.planName;
		
		$scope.formdata.goal_id= $scope.formdata.goal_id
		
		
		$http({
	  		  method: 'POST',
	  		  url: 'Goalhouse',
	  		  data: $.param($scope.formdata),
	  		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
				}).then(function(result) {
					//alert("suc");
				$scope.goDashboard();
				$scope.message1=result.data.status;
				$scope.masked = false;
					

	  		 }, function(error) {
	  			$scope.message=result.data.status;
		  
	  		 }); 

	}

$scope.backwithoutsave=function(formdata)

{

/*alert("1 goalYear "+$scope.goalYear + formdata.goalYear);
alert("2 location "+$scope.location + formdata.location);
alert("3 locationcity "+ $scope.locationcity + formdata.locationcity);
alert("4 principal_amount "+ $scope.principal_amount + formdata.principal_amount);
alert("5 downPayment "+$scope.downPayment + formdata.downPayment);
alert("6 creditsc "+$scope.creditsc + formdata.creditsc);
//alert("7 interest"+$scope.interest + goalHouseEditData.interest);
alert("8 property_tax"+$scope.property_tax + formdata.taxrate);
alert("9 houseInsuranceAmount "+$scope.houseInsuranceAmount + formdata.houseInsuranceAmount);
alert("10.."+$scope.year +formdata.goalDuration);*/

	if($scope.goalYear!=formdata.goalYear||$scope.location!=formdata.location||
			$scope.locationcity!=formdata.locationcity||$scope.principal_amount!=formdata.principal_amount
			||$scope.downPayment!=formdata.downPayment||$scope.creditsc!=formdata.creditsc||
			$scope.property_tax!=formdata.taxrate||$scope.pmi!=formdata.PMI||
			$scope.houseInsuranceAmount!=formdata.houseInsuranceAmount)
		{
	
			$('#myModalback').modal('show');

        }
	else{
		
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
