var app = angular.module('caredit',[]); 
var url = window.location.href;
var hashes = url.split("=")[1];
///$scope.masked = false;
app.directive('allowPattern', [allowPatternDirective]);
//-------------------it will allow either numbers or characters in the input feild--------------------
function allowPatternDirective() {
	return {
		restrict: "A",
		compile: function(tElement, tAttrs) {
			return function(scope, element, attrs) {

				element.bind("keypress", function(event) {
					var keyCode = event.which || event.keyCode; // I safely get the keyCode pressed from the event.
					var keyCodeChar = String.fromCharCode(keyCode); // I determine the char from the keyCode.


					if(keyCodeChar.charCodeAt()==8||keyCodeChar.charCodeAt()==9||keyCodeChar.charCodeAt()==46)
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

app.controller('Goalcar',function($scope,$http){
	$scope.masked = false;
	$scope.sessionDetails={};
	$scope.sessionDelete={};
	$scope.planName="";
	$scope.goal_id="";
	
	$scope.formdata={};
	$scope.items = ['2017','2018','2019','2020','2021','2022','2023','2024','2025','2026',"2027", "2028", "2029", "2030", "2031",
					"2032", "2033", "2034", "2035", "2036"];
	$scope.years=  [{year:'2',value:'55'},{year:'3',value:'50'},{year:'4',value:'45'}];
	$scope.times=  [{year:'1',value:'55'},{year:'2',value:'50'},{year:'3',value:'45'},{year:'4',value:'45'},{year:'5',value:'45'}];
	$scope.downPayments = [ {name:'0'},{name:'1'},{name:'2'},{name:'3'},{name:'4'},{name:'5'},{name:'6'},{name:'7'},{name:'8'},{name:'9'},{name:'10'},{name:'15'},{name:'20'},{name:'25'},{name:'30'},{name:'35'},{name:'40'},{name:'45'},{name:'50'},{name:'60'},{name:'70'},{name:'80'},{name:'90'},{name:'100'}];
	$scope.values =['30','35','40','45','50','55','60'];
	$scope.time = ['3','4','5'];
	$scope.creditScores = [{name:'760-850'},{name:'700-759'},{name:'680-699'},{name:'660-679'},{name:'640-659'},{name:'620-639'}];
    //$scope.creditScores = [{name:'760-850'},{name:'700-759'},{name:'680-699'},{name:'660 - 679'},{name:'640 - 659'},{name:'620 - 639'}];
	$scope.formdata.moneyFactor="0.00125";
	$scope.carPrice="";
//	$scope.masked = false;
	$scope.formdata.down_payment="20";
	$scope.showReccurssivePeriod=true;

	$scope.goalCarEditData={};


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
	//-------------------onload function--------------------
	$scope.load=function() { 
		$scope.masked = true;		
		
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
	$scope.showPeriods=function()
	{
		//alert();
		$scope.showReccurssivePeriod=false;
	}
	$scope.hidePeriods=function()
	{
		$scope.showReccurssivePeriod=true;
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
	$scope.hidePeriodsBuying = function() {
		$scope.showBuyingReccurssivePeriod = true;
	}
	$scope.showPeriodsBuying = function() {
		$scope.showBuyingReccurssivePeriod = false;
	}
	$scope.load1= function() {




		$scope.formdata.goal_id=(decodeURIComponent(hashes));
		$("#success-alert").hide();



		$scope.formdata.actionHomeType="edit";       		
		$http({
			method: 'POST',
			url: 'Goalcar',
			data: $.param($scope.formdata),
			headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).then(function(result) {
			console.log(result.data);
			$scope.goalCarEditData=result.data;
		//alert(JSON.stringify($scope.goalCarEditData));

			if(result.data.goalFeasiblity==false)
			{
				//$scope.masked = false;
				$("#fail-warning").show();
			}

			$scope.formdata.carPrice=$scope.goalCarEditData.carPrice;
			$scope.formdata.carYear=$scope.goalCarEditData.carYear;
			$scope.goal_id=$scope.goalCarEditData.goal_id;
			$scope.tempLease = $scope.goalCarEditData.rentLease;
			$scope.temprecPeriod = $scope.goalCarEditData.reccursivePeriod;
			$scope.temprec = $scope.goalCarEditData.carReccursive;
			
			if($scope.goalCarEditData.rentLease=="Leasing")
			{
				//alert($scope.goalCarEditData.rentLease);
				$scope.formdata.rentLease=$scope.goalCarEditData.rentLease;
				$scope.formdata.reccursivePeriod=$scope.goalCarEditData.reccursivePeriod;
				$scope.formdata.carReccursive=$scope.goalCarEditData.carReccursive;
				if($scope.goalCarEditData.carReccursive=="LeasingYes")
					{
					$scope.formdata.carReccursive=$scope.goalCarEditData.carReccursive;
					$scope.showReccurssivePeriod=false;
					}
				if($scope.goalCarEditData.carReccursive=="LeasingNo")
				{
					$scope.formdata.carReccursive=$scope.goalCarEditData.carReccursive;
					$scope.showReccurssivePeriod=true;

				}

			}

			if($scope.goalCarEditData.rentLease=="Buying")
			{
				$scope.formdata.rentLease=$scope.goalCarEditData.rentLease;
				$scope.formdata.creditsc=$scope.goalCarEditData.creditScore;
				$scope.formdata.reccursiveBuyingPeriod=$scope.goalCarEditData.reccursivePeriod;
				$scope.formdata.carBuyingReccursive=$scope.goalCarEditData.carReccursive;
				if($scope.goalCarEditData.carReccursive=="BuyingYes")
				{
					$scope.formdata.carBuyingReccursive=$scope.goalCarEditData.carReccursive;
					$scope.formdata.tradeIn=$scope.goalCarEditData.tradeIn;
					$scope.showBuyingReccurssivePeriod=false;
				}
				else
				{
					$scope.formdata.carBuyingReccursive=$scope.goalCarEditData.carReccursive;
					$scope.showBuyingReccurssivePeriod=true;
	
				}
			}

			if($scope.formdata.rentLease=="Leasing")
			{

				$scope.Buying=false;
				$scope.Buying1=false;
				$scope.show=7;

			}
			else
			{
				$scope.Buying=true;
				$scope.Buying1=true;
				$scope.show=9;
			}

			if($scope.formdata.rentLease=="Buying")
			{
				$scope.Leasing=false;
				$scope.Leasing1=false;
				$scope.show=9;
			}
			else
			{
				$scope.Leasing=true;
				$scope.Leasing1=true;
				$scope.show=7;
			}
			$scope.formdata.leaseYear=$scope.goalCarEditData.leaseYear;
			$scope.formdata.residualValue=$scope.goalCarEditData.residualValue;
			$scope.formdata.moneyFactor=$scope.goalCarEditData.moneyFactor;
			$scope.formdata.timePeriod=$scope.goalCarEditData.timePeriod;
			
			
			//alert("hello"+$scope.goalCarEditData.downPayment);
			$scope.formdata.down_payment=$scope.goalCarEditData.downPayment;
			$scope.formdata.intrestRate=$scope.goalCarEditData.intrestRate ;
			$scope.formdata.exactAnual_morgage=$scope.goalCarEditData.exactAnual_morgage;
			$scope.monthlyPayment=result.data.monthlyPayment;
			
			$scope.messresult=parseFloat(result.data.exactAnual_morgage).toFixed(2);
			$scope.carPrice=result.data.carPrice ;
			$scope.carYear=result.data.carYear;

			$scope.leaseYear=result.data.leaseYear;
			$scope.residualValue=result.data.residualValue;
			$scope.moneyFactor=result.data.moneyFactor;
			$scope.timePeriod=result.data.timePeriod;
			$scope.intrestRate=result.data.intrestRate;
			
			$scope.planName=result.data.planName;
			$scope.goal_id=result.data.goal_id;
			$scope.errorName='';
			$scope.errorSuperhero='';
			$scope.masked = false;
		}, function(error) {
			$scope.goalCarEditData=result.data;

			$scope.errorName='';
			$scope.errorSuperhero='';
		});
		$scope.msgerr="";
	} 

	$scope.goDashboard=function()
	{
		window.location.href="dashboardUser0.jsp?finName="+ $scope.planName;
	}



	$scope.backwithoutsave=function(formdata)

	{

/*	 alert("$scope.carYear"+$scope.carYear+"formdata.carYear--"+formdata.carYear);
	        alert("$scope.carPrice =="+$scope.carPrice +"formdata.carPrice"+formdata.carPrice);
	        alert("$scope.timePeriod"+$scope.timePeriod+"formdata.timePeriod=="+formdata.timePeriod);

	        alert("$scope.intrestRate =="+$scope.intrestRate +"formdata.intrestRate="+formdata.intrestRate);

	        alert("$scope.residualValuee =="+$scope.residualValue +"formdata.residualValue="+formdata.residualValue);
	       alert("$scope.moneyFactor =="+$scope.moneyFactor +"formdata.moneyFacto=="+formdata.moneyFacto);
	       //alert("formdata.rentLease=="+formdata.rentLease);
	       alert("$scope.down_payment =="+$scope.down_payment +"formdata.down_payment=="+formdata.down_payment);
           alert("$scope.formdata.rentLease==="+$scope.formdata.rentLease+"formdata.rentLease=="+formdata.rentLease);
           alert("$scope.leaseYear=="+$scope.leaseYear+"formdata.leaseYear=="+formdata.leaseYear);*/
/*
		if($scope.carYear!=formdata.carYear|| $scope.carPrice !=formdata.carPrice||$scope.timePeriod!=formdata.timePeriod||$scope.intrestRate!=formdata.intrestRate||$scope.residualValue!=
			formdata.residualValue||$scope.moneyFactor!=formdata.moneyFactor)*/

		if($scope.carYear!=formdata.carYear|| $scope.carPrice !=formdata.carPrice||$scope.timePeriod!=formdata.timePeriod
				||$scope.intrestRate!=formdata.intrestRate||$scope.residualValue!=
			formdata.residualValue||$scope.moneyFactor!=formdata.moneyFactor||$scope.goalCarEditData.downPayment!=formdata.down_payment
          ||$scope.formdata.rentLease!=formdata.rentLease||$scope.leaseYear!=formdata.leaseYear)

		{
			$('#myModalback').modal('show');

		}
		else
		{
			$scope.goDashboard();
		}

	}


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
       // alert();
		$('#myModal1').modal('show')
	}

	$scope.deletegoal=function(){

		$scope.masked = true;
		$scope.formdata.actionHomeType="deleteGoal";
		//alert();

		$scope.formdata.plan_name=$scope.planName;

		$scope.formdata.goal_id=$scope.goal_id;



		$http({
			method: 'POST',
			url: 'Goalcar',
			data: $.param($scope.formdata),
			headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).then(function(result) {
			$scope.goDashboard();
			$scope.message1=result.data.status;
            // alert();
			$scope.masked = false;

		}, function(error) {
			$scope.message=result.data.status;

		}); 



	}
//update function
	$scope.checkform1=function(){
	//	alert("hfdfhdsfsd");
		
	
		
		$scope.masked = true;



window.location.href="#";
	

		if($scope.formdata.rentLease=="Leasing")
		{
			
			if($scope.formdata.carPrice==null||$scope.formdata.carPrice==undefined||$scope.formdata.carPrice=="")
			{ 
				$scope.masked = false;
				$scope.errmessage1="Please Enter The Car price!!!";
				$("#fail-alert").show();  

			}
			else if($scope.formdata.carPrice==0)
			{
				$scope.masked = false;
				$scope.errmessage1="Car price cannot be zero!!!";
				$("#fail-alert").show();  
			}
			else if($scope.formdata.carPrice==0)
			{
				$scope.masked = false;
				$scope.errmessage1="Car price cannot be zero!!!";
				$("#fail-alert").show();  
			}
			else if($scope.formdata.leaseYear==null||$scope.formdata.leaseYear==undefined||$scope.formdata.leaseYear=="")
			{
				$scope.masked = false;
				$scope.errmessage1="Please Enter The Lease Year!!!";
				$("#fail-alert").show();  

			}
			else if($scope.formdata.residualValue==null||$scope.formdata.residualValue==undefined||$scope.formdata.residualValue=="")
			{
				$scope.masked = false;
				$scope.errmessage1="Please Enter The residualValue!!!";
				$("#fail-alert").show();  

			}
			else if($scope.formdata.moneyFactor==null||$scope.formdata.moneyFactor==undefined||$scope.formdata.moneyFactor=="")
			{
				$scope.masked = false;
				$scope.errmessage1="Please Enter The Money Factor!!!";
				$("#fail-alert").show();  

			}
			else if($scope.formdata.moneyFactor==0)
			{   
				$scope.masked = false;
				$scope.errmessage1="Money Factor cannot be zero!!!";
				$("#fail-alert").show(); 
		
			}
			else if($scope.formdata.carReccursive==null||$scope.formdata.carReccursive==undefined||$scope.formdata.carReccursive=="")
			{   
				//alert($scope.formdata.carReccursive);
				$scope.masked = false;
				$scope.errmessage1="Reccursive option cannot be empty!!!";
				$("#fail-alert").show(); 
		
			}
			else if(($scope.formdata.reccursivePeriod==null||$scope.formdata.reccursivePeriod==undefined||$scope.formdata.reccursivePeriod=="") && $scope.formdata.carReccursive=="LeasingYes")
			{   
				//alert($scope.formdata.reccursivePeriod);
				//alert($scope.formdata.carReccursive);
				$scope.masked = false;
				$scope.errmessage1="Reccursive Period cannot be empty!!!";
				$("#fail-alert").show(); 
		
			}
			
			else{
				$scope.formdata.actionHomeType="update";
				//alert($scope.formdata.carReccursive);

				$http({
					method: 'POST',
					url: 'Goalcar',
					data: $.param($scope.formdata),
					headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
				}).then(function(result) {
					
					$scope.masked = false;
				//	alert($scope.masked);
					$scope.goalCarEditData=result.data;
					if(result.data.goalFeasiblity==false)
					{
//						$scope.masked = false;
						$("#fail-warning").show();
					}
					
					$scope.formdata.carPrice=$scope.goalCarEditData.carPrice;

					$scope.formdata.carYear=$scope.goalCarEditData.carYear;
					$scope.formdata.rentLease=$scope.goalCarEditData.rentLease;
					$scope.formdata.leaseYear=$scope.goalCarEditData.leaseYear;

					$scope.formdata.residualValue=$scope.goalCarEditData.residualValue;

					$scope.formdata.moneyFactor=$scope.goalCarEditData.moneyFactor;

					$scope.formdata.timePeriod=$scope.goalCarEditData.timePeriod;
					$scope.formdata.down_payment=$scope.goalCarEditData.downPayment;
					$scope.formdata.intrestRate=$scope.goalCarEditData.intrestRate 
					$scope.monthlyPayment=result.data.monthlyPayment;

					$scope.carPrice=result.data.carPrice ;
					$scope.carYear=result.data.carYear;
					$scope.leaseYear=result.data.leaseYear;
					$scope.residualValue=result.data.residualValue;
					$scope.moneyFactor=result.data.moneyFactor;
					$scope.intrestRate=result.data.intrestRate;
					$scope.rentLease=result.data.rentLease;
				
					
					//$scope.down_payment=result.data.downPayment;
				
					$scope.timePeriod=result.data.timePeriod;
				/*	formdata.carYear
					formdata.carPrice
					formdata.rentLease
					formdata.leaseYear
					formdata.residualValue ///lease year
					formdata.moneyFactor
					buying
					formdata.rentLease
					formdata.down_payment
					formdata.timePeriod
					formdata.intrestRate*/
					
				
					$scope.planName=result.data.planName;
					$scope.show=7;

					if(result.data.status=="success")
					{
///						$scope.masked = false;
						
						$("#fail-alert").hide();
                        $scope.errmessage=" Goal updated successfully";			
						$("#success-alert").show();
						$("#success-alert").fadeTo(2000, 300).slideUp(300, function(){
							$("#success-alert").hide();

						});
					}
					else
					{

	//					$scope.masked = false;
						window.location.href="#";

						$scope.errmessage="Goal is not feasible since you are not having sufficient funds !!";
						$("#myModal").modal('show');
					}
					$scope.goalCarData=result.data;
					console.log("message"+$scope.message);
					//$scope.masked = false;

				}, function(error) {


				});


			}

		}

		if($scope.formdata.rentLease=="Buying")
		{
			if($scope.formdata.down_payment==null||$scope.formdata.down_payment==undefined||$scope.formdata.down_payment=="")
			{
				$scope.masked = false;
				$scope.errmessage1="Please Enter The down payment!!!";
				$("#fail-alert").show();  

			}
			else if($scope.formdata.carPrice==0)
			{
				$scope.masked = false;
				$scope.errmessage1="Car price cannot be zero!!!";
				$("#fail-alert").show();  
			}
			else if($scope.formdata.creditsc==null||$scope.formdata.creditsc==undefined||$scope.formdata.creditsc=="")
			{
				$scope.masked = false;
				$scope.errmessage1="Credit score cannot be empty !!!";
				$("#fail-alert").show();  
			}
			else if($scope.formdata.intrestRate==null||$scope.formdata.intrestRate==undefined||$scope.formdata.intrestRate=="")
			{
				$scope.masked = false;
				$scope.errmessage1="Please Enter The Interest rate!!!";
				$("#fail-alert").show();  

			}
			else if($scope.formdata.intrestRate==0)
			{
				$scope.masked = false;
				$scope.errmessage1="Interest rate cannot be zero!!!";
				$("#fail-alert").show();  
			} 
			else if($scope.formdata.carBuyingReccursive==null||$scope.formdata.carBuyingReccursive==undefined||$scope.formdata.carBuyingReccursive=="")
			{   
				$scope.masked = false;
				$scope.errmessage1="Reccursive option cannot be empty!!!";
				$("#fail-alert").show(); 
		
			}
			else if(($scope.formdata.reccursiveBuyingPeriod==null||$scope.formdata.reccursiveBuyingPeriod==undefined||$scope.formdata.reccursiveBuyingPeriod=="") && $scope.formdata.carBuyingReccursive=="BuyingYes")
			{   
				//alert($scope.formdata.reccursivePeriod);
				//alert($scope.formdata.carReccursive);
				$scope.masked = false;
				$scope.errmessage1="Reccursive Period cannot be empty!!!";
				$("#fail-alert").show(); 
		
			}
			else if(($scope.formdata.tradeIn==null||$scope.formdata.tradeIn==undefined||$scope.formdata.tradeIn=="" ) && $scope.formdata.carBuyingReccursive=="BuyingYes")
			{   
				//alert($scope.formdata.carReccursive);
				//alert($scope.formdata.carBuyingReccursive);
				$scope.masked = false;
				$scope.errmessage1="Trade in value cannot be empty!!!";
				$("#fail-alert").show(); 
		
			}
			else{
				$scope.formdata.actionHomeType="update";
				
				
				$http({
					method: 'POST',
					url: 'Goalcar',
					data: $.param($scope.formdata),
					headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
				}).then(function(result) {
					$scope.masked=false;
					$scope.goalCarEditData=result.data;
				//alert(JSON.stringify(result.data));
					$scope.formdata.carPrice=$scope.goalCarEditData.carPrice;

					$scope.formdata.carYear=$scope.goalCarEditData.carYear;
					$scope.formdata.rentLease=$scope.goalCarEditData.rentLease;
					$scope.formdata.down_payment=$scope.goalCarEditData.downPayment;
					$scope.formdata.timePeriod=$scope.goalCarEditData.timePeriod;
					$scope.formdata.intrestRate=$scope.goalCarEditData.intrestRate;
					$scope.formdata.creditsc=$scope.goalCarEditData.creditScore;
					$scope.carPrice=result.data.carPrice ;
					$scope.carYear=result.data.carYear;
				//	alert("$scope.down_payment=="+result.data.downPayment);
					
					$scope.down_payment=result.data.downPayment;
					
					$scope.planName=result.data.planName;
					$scope.timePeriod=result.data.timePeriod;
					$scope.intrestRate=result.data.intrestRate;
					$scope.rentLease=result.data.rentLease;
					$scope.messresult=$scope.goalCarEditData.exactAnual_morgage;
					$scope.show=9;

                     if(result.data.status=="fail"){
                    	 
                    	 $scope.masked = false;
     				
     					
     					 window.location.href="#";
 		 				
    	 				 $scope.errmessage="Goal is not feasible since you are not having sufficient funds !!";
    	            	  $("#myModal").modal('show');
     					
                    	 
                    	 
                     }
                     else{
					$scope.masked = false;
					$("#fail-alert").hide();
					$scope.errmessage=" Goal updated successfully";			
					$("#success-alert").show();
					$("#success-alert").fadeTo(2000, 300).slideUp(300, function(){
						$("#success-alert").hide();

					});   
					$scope.goalCarData=result.data;
					console.log("message"+$scope.message);
                     
                     }

				}, function(error) {


				});

			}
		//	$scope.masked = false;

		}
		
//		$scope.masked = false;
	};

	$scope.reload=function()
	{

		window.location.href=window.location.href.slice(0, -1);

	}
	$scope.change=function()
	{

		$scope.Buying=false;
		$scope.Leasing=true;
		$scope.formdata.leaseYear="3";
		$scope.formdata.residualValue="50";
		$scope.formdata.moneyFactor="0.00125";
		if($scope.temprentLease == "Leasing") {
			$scope.formdata.reccursivePeriod = $scope.temprecPeriod;
			$scope.formdata.carReccursive = $scope.temprec;
		}
	}
	$scope.change1=function()
	{

		$scope.Buying=true;
		$scope.Leasing=false;
		$scope.formdata.intrestRate="3";
		$scope.formdata.timePeriod="3";
		$scope.formdata.down_payment="20";
		$scope.formdata.carBuyingReccursive="BuyingYes";
		if($scope.temprentLease == "Buying") {
			$scope.formdata.reccursiveBuyingPeriod = $scope.temprecPeriod;
			alert($scope.temprec);
			$scope.formdata.carBuyingReccursive = $scope.temprec;
		}

	} 

	$scope.calculate=function(leaseYear)
	{

		if($scope.formdata.leaseYear == '2'){


			$scope.formdata.residualValue= 55;


		}else if($scope.formdata.leaseYear == '3'){

			$scope.formdata.residualValue= 50;

		}else if($scope.formdata.leaseYear == '4'){

			$scope.formdata.residualValue= 45;


		}
	}


});