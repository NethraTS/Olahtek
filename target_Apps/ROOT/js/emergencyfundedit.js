var app = angular.module('Emergencyfundedit',[]);
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

app.controller('Goalemergencyedit',function($scope,$http){

	$scope.formdata={};
	$scope.sessionDetails={};
	$scope.sessionDelete={};
	$scope.masked = false;
	$scope.newCalc =true;
	$scope.includeinvestment=true;
	$scope.planName="";
	$scope.time = ['1','2','3','4','5','6','7','8','9','10','11','12'];
	$scope.time1 = ['1','2','3','4','5','6','7','8','9','10','11','12'];

	$scope.goalemergencyEditData={};

	$scope.progressBar=function() { 


		$scope.formdata.amountSave=$scope.minValue;

		$scope.message="Amount save"
			 $scope.checkedexp=true;
		 $scope.checkedinc=true;

		$scope.show = 3;
	

	}

	$scope.progressBar1=function() 
	{

      //alert("edit");
		$scope.formdata.amountSave=(($scope.tExpense/12)*($scope.formdata.month));


		$scope.message="Amount save"

			$scope.show = 3;
		 $scope.checkedexp=false;
		$scope.checkedinc=true;

	}
	$scope.progressBar2=function() { 

		$scope.formdata.amountSave=($scope.sixIncome/6*($scope.formdata.month1));

		$scope.message="Amount save"

			$scope.show = 3;
		 $scope.checkedexp=true;
		 $scope.checkedinc=false;


	}




	$scope.load1= function() {
		$scope.masked = true;
		$("#success-alert").hide();
		$("#fail-alert").hide();

		$scope.formdata.goal_id=(decodeURIComponent(hashes));
		$scope.formdata.plan_name=(decodeURIComponent(hashes));

		$scope.formdata.actionHomeType="edit";       		
		$http({
			method: 'POST',
			url: 'EmergencyFund',
			data: $.param($scope.formdata),
			headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).then(function(result) {
			if(result.data.goalFeasiblity==false)
			{

				$("#fail-warning").show();
			}

			$scope.planName=result.data.plan_name;
			$scope.goalemergencyEditData=result.data;
			$scope.formdata.timePeriod=$scope.goalemergencyEditData.timePeriod;
			//alert($scope.goalemergencyEditData.timePeriod);
			$scope.formdata.amountSave=$scope.goalemergencyEditData.amountSave;
		
			$scope.formdata.month=$scope.goalemergencyEditData.monthE;
			$scope.formdata.month1=$scope.goalemergencyEditData.monthI;
			$scope.liquid_asset=result.data.liquid_asset;
			$scope.taxableInvestments=result.data.taxableInvestments ;
			$scope.nonTaxableInvestments=result.data.nonTaxableInvestments;
			$scope.timePeriod=result.data.timePeriod ;
			$scope.amountSave=result.data.amountSave;
			$scope.annualExcess=result.data.annualExcess;
			$scope.monthE=result.data.monthE;
			$scope.monthI=result.data.monthI;
			$scope.messresult=result.data.messresult;
			
			if($scope.messresult=="Congratulations! You already have accomplished this goal with your current savings and taxable investments. However, if you want to exclude your investments from your emergency fund, we can calculate for you how much and how long you need to save for this goal.")
				{
				$scope.newCalc =false;
				}

			if($scope.formdata.timePeriod=="Fix Amount")
			{
 $scope.checkedexp=true;
 $scope.checkedinc=true;
			}
			else{
				$scope.checkedexp=false;
				$scope.checkedinc=false;
				
			}
			if($scope.formdata.timePeriod=="Income")
			{
				$scope.show = 9;
				$scope.result=$scope.monthI;
			}
			else{
				$scope.show = 8;
			}
		/*	if($scope.amountSave<=$scope.liquid_asset)
			{
				$scope.messresult="Congratulations! You already have accomplished this goal with your current savings.";
			}
			else if($scope.amountSave<=($scope.liquid_asset+$scope.taxableInvestments))
			{

				$scope.messresult="Congratulations! You already have accomplished this goal with your current savings and taxable investments. However, if you want to exclude your investments from your emergency fund, we can calculate for you how much and how long you need to save for this goal."
			}
			else if($scope.amountSave<=($scope.liquid_asset+$scope.taxableInvestments+$scope.nonTaxableInvestments))
			{

				$scope.messresult="Your total assets exceed your Emergency Fund goal. However, we recommend not including your non-taxable investments in this calculation."


			}
			else if($scope.amountSave>($scope.liquid_asset+$scope.taxableInvestments))
			{

				$scope.value=$scope.amountSave-($scope.liquid_asset+$scope.taxableInvestments);

				$scope.timey=Number((($scope.value/$scope.annualExcess)*100).toFixed(2));

				$scope.messresult="You are currently $ "+$scope.value+" behind in reaching your goal however, if you start saving now, you can reach your goal in "+$scope.timey+" years.";


			}*/
			$scope.formdata.plan_name=(decodeURIComponent(hashes));

			$scope.formdata.actionHomeType="OnLoadEmergencyFund";


			$http({
				method: 'POST',
				url: 'EmergencyFund',
				data: $.param($scope.formdata),
				headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
			}).then(function(result) {

				$scope.goalemergencyEditData=result.data;
				$scope.liquid_asset=result.data.liquid_asset;
				$scope.sixIncome=result.data.sixMonthOfIncome ;
				$scope.tExpense=result.data.twelveMonthOfExpense;

				if(result.data.sixMonthOfIncome<result.data.twelveMonthOfExpense)
				{
					$scope.minValue=result.data.sixMonthOfIncome;
				}
				else
				{
					$scope.minValue=result.data.twelveMonthOfExpense;
				}

				console.log("message"+$scope.message);

			}, function(error) {
				$scope.message=result.data;

			});
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


	
	
	
	
	$scope.hideFail=function()
	{
		$("#fail-alert").hide();

	}
	$scope.checkform1=function(){
		//alert("edit");
		$scope.masked = true;
		window.location.href="#";
		if($scope.formdata.amountSave==null||$scope.formdata.amountSave==undefined||$scope.formdata.amountSave=="")
		{
			$scope.errmessage1="Please enter Amount to be saved !!";
			$("#fail-alert").show(); 
			$scope.masked = false;
		}
		else if($scope.formdata.amountSave==0)
		{

			$scope.errmessage1="Amount to be saved cannot be zero !!";

			$("#fail-alert").show();  
			$scope.masked = false;

		}
		else if($scope.formdata.timePeriod==null||$scope.formdata.timePeriod==undefined||$scope.formdata.timePeriod=="")
		{
			$scope.errmessage1="Please Enter Time Period!!!";
			$("#fail-alert").show(); 
			$scope.masked = false;
		}
		else if($scope.formdata.timePeriod==0)
		{

			$scope.errmessage1="Time Period cannot be zero !!";

			$("#fail-alert").show();  
			$scope.masked = false;

		}
		else{
			$scope.masked = true;
			$scope.formdata.actionHomeType="update";
			$http({
				method: 'POST',
				url: 'EmergencyFund',
				data: $.param($scope.formdata),
				headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
			}).then(function(result) {
				if(result.data.goalFeasiblity==false)
				{
					//alert();
					$("#fail-warning").show();
					$scope.masked = false;
				}
				$scope.goalemergencyEditData=result.data;
				$scope.formdata.timePeriod=$scope.goalemergencyEditData.timePeriod;
				$scope.formdata.amountSave=$scope.goalemergencyEditData.amountSave;
				$scope.formdata.month=$scope.goalemergencyEditData.monthE;
				$scope.formdata.month1=$scope.goalemergencyEditData.monthI;
				$scope.liquid_asset=result.data.liquid_asset;
				$scope.taxableInvestments=result.data.taxableInvestments ;
				$scope.nonTaxableInvestments=result.data.nonTaxableInvestments;
				$scope.timePeriod=result.data.timePeriod ;
				$scope.amountSave=result.data.amountSave;
				$scope.annualExcess=result.data.annualExcess;
				$scope.monthE=result.data.monthE;
				$scope.monthI=result.data.monthI;

				//---------Success modal alert------------------------------------------


				console.log("message"+$scope.message);
				$scope.load1(); 
				$scope.errmessage=" Goal updated successfully";			
				$("#success-alert").show();
				$scope.masked = false;
				$("#success-alert").fadeTo(2000, 300).slideUp(300, function(){
					$("#success-alert").hide();
					$scope.masked = false;

				});   

			}, function(error) {


			});

		}

	}
	//---------Success modal alert hide------------------------------------------
	$scope.hideSuccess=function()
	{
		$("#success-alert").hide();

	}


	//---------------------------------------logic for back button-------------------------

	$scope.goDashboard=function()
	{
		window.location.href="dashboardUser0.jsp?finName="+ $scope.planName;
	}

	$scope.backwithoutsave=function(formdata)

	{


		if($scope.timePeriod!=formdata.timePeriod|| $scope.amountSave!=formdata.amountSave||$scope.monthE!=formdata.month||$scope.monthI!=formdata.month1)
		{


			$('#myModalback').modal('show');

		}
		else
		{
			$scope.goDashboard();
		}

	}
	$scope.DeleteGoal=function()
	{ 


		$('#myModal1').modal('show');
	}

	$scope.deletegoal=function(){
		 $scope.masked = true;
		$scope.formdata.actionHomeType="deleteGoal";

		$scope.formdata.plan_name=$scope.planName;

		$scope.formdata.goal_id= $scope.formdata.goal_id


		$http({
			method: 'POST',
			url: 'EmergencyFund',
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
	$scope.includeInvestmentfunc=function()
	{
	
		$scope.messresult = "Congratulations! You already have accomplished this goal with your current savings and taxable investments. However, if you want to exclude your investments from your emergency fund, we can calculate for you how much and how long you need to save for this goal.";
		$scope.includeinvestment=true;
		$scope.newCalc =false;
		
   }

	$scope.excludeInvestment=function()
	{
	/*	if($scope.formdata.timePeriod=="Expense")
		{
			$scope.show = 9;

		}
		else if($scope.formdata.timePeriod=="Income")
		{
			$scope.show = 9;

		}
		else{
			$scope.show = 8;
		} */
		
		$scope.value=$scope.amountSave-$scope.liquid_asset;

		$scope.timey=Number((($scope.value/$scope.annualExcess)*100).toFixed(2));

		$scope.messresult="You are currently $ "+$scope.value+" behind in reaching your goal however, if you start saving now, you can reach your goal in "+$scope.timey+" years.";
		//$scope.hide=10;
		$scope.includeinvestment=false;
		$scope.newCalc =true;
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

	$scope.hideWarning=function()
	{
		$("#fail-warning").hide();


	}

});