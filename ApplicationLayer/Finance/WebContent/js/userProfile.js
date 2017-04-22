var app = angular.module('formApp2',[]);
app.directive('allowPattern', [allowPatternDirective]);

app.controller('formController2',function($http,$scope){
	$scope.ages = [ /* {
	        		number : '1'
	        	}, {
	        		number : '2'
	        	}, {
	        		number : '3'
	        	}, {
	        		number : '4'
	        	}, {
	        		number : '5'
	        	}, {
	        		number : '6'
	        	}, {
	        		number : '7'
	        	}, {
	        		number : '8'
	        	}, {
	        		number : '9'
	        	}, {
	        		number : '10'
	        	}, {
	        		number : '11'
	        	}, {	
	        		number : '12'
	        	}, {
	        		number : '13'
	        	}, {
	        		number : '14'
	        	},*/ {
	        		number : '15'
	        	}, {
	        		number : '16'
	        	}, {
	        		number : '17'
	        	}, {
	        		number : '18'
	        	}, {
	        		number : '19'
	        	}, {
	        		number : '20'
	        	}, {
	        		number : '21'
	        	}, {
	        		number : '22'
	        	}, {
	        		number : '23'
	        	}, {
	        		number : '24'
	        	}, {
	        		number : '25'
	        	}, {
	        		number : '26'
	        	}, {
	        		number : '27'
	        	}, {
	        		number : '28'
	        	}, {
	        		number : '29'
	        	}, {
	        		number : '30'
	        	}, {
	        		number : '31'
	        	}, {
	        		number : '32'
	        	}, {
	        		number : '33'
	        	}, {
	        		number : '34'
	        	}, {
	        		number : '35'
	        	}, {
	        		number : '36'
	        	}, {
	        		number : '37'
	        	}, {
	        		number : '38'
	        	}, {
	        		number : '39'
	        	}, {
	        		number : '40'
	        	}, {
	        		number : '41'
	        	}, {
	        		number : '42'
	        	}, {
	        		number : '43'
	        	}, {
	        		number : '44'
	        	}, {
	        		number : '45'
	        	}, {
	        		number : '46'
	        	}, {
	        		number : '47'
	        	}, {
	        		number : '48'
	        	}, {
	        		number : '49'
	        	}, {
	        		number : '50'
	        	}, {
	        		number : '51'
	        	}, {
	        		number : '52'
	        	}, {
	        		number : '53'
	        	}, {
	        		number : '54'
	        	}, {
	        		number : '55'
	        	}, {
	        		number : '56'
	        	}, {
	        		number : '57'
	        	}, {
	        		number : '58'
	        	}, {
	        		number : '59'
	        	}, {
	        		number : '60'
	        	}, {
	        		number : '61'
	        	}, {
	        		number : '62'
	        	}, {
	        		number : '63'
	        	}, {
	        		number : '64'
	        	}, {
	        		number : '65'
	        	}, {
	        		number : '66'
	        	}, {
	        		number : '67'
	        	}, {
	        		number : '68'
	        	}, {
	        		number : '69'
	        	}, {
	        		number : '70'
	        	}, {
	        		number : '71'
	        	}, {
	        		number : '72'
	        	}, {
	        		number : '73'
	        	}, {
	        		number : '74'
	        	}, {
	        		number : '75'
	        	}, {
	        		number : '76'
	        	}, {
	        		number : '77'
	        	}, {
	        		number : '78'
	        	}, {
	        		number : '79'
	        	}, {
	        		number : '80'
	        	}, {
	        		number : '81'
	        	}, {
	        		number : '82'
	        	}, {
	        		number : '83'
	        	}, {
	        		number : '84'
	        	}, {
	        		number : '85'
	        	}, {
	        		number : '86'
	        	}, {
	        		number : '87'
	        	}, {
	        		number : '88'
	        	}, {
	        		number : '89'
	        	}, {
	        		number : '80'
	        	}, {
	        		number : '81'
	        	}, {
	        		number : '82'
	        	}, {
	        		number : '83'
	        	}, {
	        		number : '84'
	        	}, {
	        		number : '85'
	        	}, {
	        		number : '86'
	        	}, {
	        		number : '87'
	        	}, {
	        		number : '88'
	        	}, {
	        		number : '89'
	        	}, {
	        		number : '90'
	        	}, {
	        		number : '91'
	        	}, {
	        		number : '92'
	        	}, {
	        		number : '93'
	        	}, {
	        		number : '94'
	        	}, {
	        		number : '95'
	        	}, {
	        		number : '96'
	        	}, {
	        		number : '97'
	        	}, {
	        		number : '98'
	        	}, {
	        		number : '99'
	        	} ];
	$scope.limits = [ {id: 'No'}, {id: 'Yes'}];
	$scope.userdetails={};
	$scope.formdata={};
	$scope.values = [ {year:'2015',value: '12000'}, {year:'2016',value: '13000'},{year:'2017',value: '14000'},{year:'2018',value: '15000'}];
	$scope.finPlanCount=0;
	$scope.states = [ {name:'ALABAMA'},{name:'ALASKA'},{name:'ARIZONA'},{name:'ARKANSAS'},{name:'CALIFORNIA'},{name:'COLORADO'},{name:'CONNECTICUT'},{name:'DELAWARE'},{name:'FLORIDA'},{name:'GEORGIA'},{name:'HAWAII'},{name:'IDAHO'},{name:'ILLINOIS'},{name:'INDIANA'},{name:'IOWA'},{name:'KANSAS'},{name:'KENTUCKY'},{name:'LOUISIANA'},{name:'MAINE'},{name:'MARYLAND'},{name:'MASSACHUSETTS'},{name:'MICHIGAN'},{name:'MINNESOTA'},{name:'MISSISSIPPI'},{name:'MISSOURI'},{name:'MONTANA'},{name:'NEBRASKA'},{name:'NEVADA'},{name:'NEW HAMPSHIRE'},{name:'NEW JERSEY'},{name:'NEW MEXICO'},{name:'NEW YORK'},{name:'NORTH CAROLINA'},{name:'NORTH DAKOTA'},{name:'OHIO'},{name:'OKLAHOMA'},{name:'OREGON'},{name:'PENNSYLVANIA'},{name:'RHODE ISLAND'},{name:'SOUTH CAROLINA'},{name:'SOUTH DAKOTA'},{name:'TENNESSEE'},{name:'TEXAS'},{name:'UTAH'},{name:'VERMONT'},{name:'VIRGINIA'},{name:'WEST VIRGINIA'},{name:'WISCONSIN'},{name:'WYOMING'},{name:'WASHINGTON'},{name:'WASHINGTON DC'}];
	$scope.kidsnumber = [  {number: '0'},{number: '1'}, {number: '2'},{number: '3'},{number: '4'},{number: '5'},{number: '6'},{number: '7'},{number: '8'},{number: '9'},{number: '10'}];
	$scope.kidsages = [{id:'0'}, {id: '1'}, {id: '2'},{id: '3'},{id: '4'},{id: '5'},{id: '6'},{id: '7'},{id: '8'},{id: '9'},{id: '10'},{id: '11'}, {id: '12'},{id: '13'},{id: '14'},{id: '15'},{id: '16'},{id: '17'},{id: '18'}, {id: '19'},{id: '20'},{id: '21'},{id: '22'},{id: '23'},{id: '24'},{id: '25'}];
	$scope.options = [ {id: 'No'}, {id: 'Yes'}];
	$scope.dependency = [ {id: 'No'}, {id: 'Yes'}];
	$scope.dependants=false;		
	$scope.filingOptions= [];
	$scope.AlertModal="";
	$scope.kid={};
	$scope.masked = false;
	/*	$cope.errorNameedit="";
		 $scope.incomeandexpenses.beforeIncomeTax="";
		$scope.incomeandexpenses.beforeIncomeTax="";
		$scope.incomeandexpenses.spouseBeforeIncomeTax="";
		$scope.incomeandexpenses.monthlyExpense="";
	    $scope.incomeandexpenses.rentalExpense="";*/
	$scope.Houseandnonhouse=0;

	$scope.houseInfo=false;
	$scope.spouseAndUser="";
	$scope.Houseandnonhouse="";
	$scope.editBasicDetail={};
	$scope.incomeandexpenses={};
	$scope.editAssetDetails={};
	$scope.passwordmissmatcherror="";
	$scope.incomeandexpenses.rentalExpense="";
	$scope.msgerr="";
	$scope.changePassword={};
	$scope.citys=['select city...'];
	$scope.formData1={};
	$scope.disabledCounty="true";
	$scope.incomeProfile={};
	$scope.incomeandexpenses.monthlyExpense1="";
	$scope.incomeandexpenses.beforeIncomeTax1="";
	$scope.incomeandexpenses.rentalExpense1="";
	$scope.incomeProfiles=[];
	$scope.chartUserIncome=[];
	$scope.chartSouseIncome=[];
	$scope.chartCombinedIncome=[];
	$scope.chartYear=[];
	$scope.errorMessege="";
	$scope.married="";
	$scope.monthlyMortgageAmounts="";
	$scope.limitSpouse=true;
	$scope.limit=true;
	$scope.limitSpouseOption=true;
	$scope.limitOption=true;
	$scope.kidsType=[];
	$scope.change = function() {
		//alert("hiii change func");

		$scope.married=$scope.editBasicDetail.married;
		if($scope.editBasicDetail.married=="Yes") {
			//alert("yes");
			$scope.marriedt=true;
			$scope.limitSpouse=false;
			$scope.limit=false;
			$scope.kidForSingle=false;
			$scope.filingOptions= [  {id: 'Married Filing Jointly'},{id: 'Head of Household'}];  
			$scope.editBasicDetail.filingStatus="Married Filing Jointly";

			if($scope.editBasicDetail.filingStatus=="Single"||$scope.editBasicDetail.filingStatus=="")
			{

				$scope.editBasicDetail.filingStatus='Married Filing Jointly';
			}

		}
		else
		{

			$scope.kidForSingle=true;
			$scope.marriedt=false;
			$scope.limit=false;
			$scope.limitSpouse=true;
			//alert("insdie=="+$scope.userdetails.filingStatus);
			if($scope.editBasicDetail.kidscount>0 || $scope.editBasicDetail.dependants>0)

			{
				$scope.editBasicDetail.filingStatus='Head of Household';
				//alert("insdie depeifiweifiwefiwefweh");
				//alert("dependents==="+$scope.editBasicDetail.dependants);
				//alert("$scope.editBasicDetail.kidscount"+$scope.editBasicDetail.kidscount)
				$scope.filingOptions= [{id: 'Single'},{id: 'Head of Household'}];
				//$scope.editBasicDetail.filingStatus=$scope.userdetails.filingStatus;
				//alert("hed of aparna    ="+$scope.editBasicDetail.filingStatus);


			} else if($scope.editBasicDetail.kidscount>0 ){

				$scope.filingOptions= [{id: 'Single'},{id: 'Head of Household'}];
				$scope.editBasicDetail.filingStatus='Head of Household';
				//alert("hed of    00000="+$scope.editBasicDetail.filingStatus);
			}
			else
			{

				$scope.filingOptions= [ {id: 'Single'},{id: 'Head of Household'}];
				$scope.editBasicDetail.filingStatus='Single';
			}


			/*if($scope.editBasicDetail.dependants>0){
				$scope.filingOptions= [{id: 'Head of Household'}];
				$scope.editBasicDetail.filingStatus="Head of Household";

			}else{
				$scope.filingOptions= [ {id: 'Single'},{id: 'Head of Household'}];
				$scope.editBasicDetail.filingStatus='Single';

			}*/

			//          		$scope.editBasicDetail.filingStatus="Single";    	
		}
		if($scope.editBasicDetail.filingStatus=="Head of Household")
		{
			$scope.editBasicDetail.filingStatus='Head of Household';
			$scope.dependants=true;

		}
		else
		{
			//alert("onchange in single");
			$scope.dependants=true;
		}
	}

	$scope.studentType=function()
	{
		//alert(JSON.stringify($scope.kidsType));

		for(var i =0; i<$scope.editBasicDetail.kids.length;i++)
		{
			if($scope.editBasicDetail.kids[i].age>18)
			{
				$scope.kidsType[i]=false;
				//alert("show..."+$scope.kidsType[i]);
			}
			else{
				//	alert("hide");
				$scope.kidsType[i]=true;
				//alert("hide .."+$scope.kidsType[i]);
			}

		}
		//alert("saghg"+JSON.stringify($scope.kidsType));	
	}
	
	
	//////////////////////////links for report
	
	
	
	
	$scope.report = function() {
//alert();
		if ($scope.finPlanCount == 0) {
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
	$scope.check1=function(){

		$scope.disabled=true; 

		//alert($scope.errorMail3);
		//alert("check 1");

		if( angular.equals($scope.errorMail1,"Please Enter Your Income")){
			$scope.errorMessege="Please Enter Your Income";

			window.location.href="#services";
			$scope.disabled=false; 


		}
		else if(angular.equals($scope.errorMail4,"Please Enter Your Monthly expenses")){
			$scope.errorMessege="Please Enter Your Monthly expenses";
			window.location.href="#services";
			$scope.disabled=false; 
			//$location.hash('services');
		}
		else if(angular.equals($scope.errorMail3,"Please Enter Your Expenses Spent on Housing")){
			//$scope.masked = false;
			$scope.errorMessege="Please Enter Your Expenses Spent on Housing";
			window.location.href="#services";
			$scope.disabled=false; 
			//$location.hash('services');
		}
		else if(angular.equals($scope.errorMail5,"Please Enter House Market Value ")){
			$scope.errorMessege="Please Enter House Market Value ";
			window.location.href="#services";
			$scope.disabled=false;
		}

		// $scope.disabled=true; 
	};

	$scope.editincome=function(){
		// alert("ssss");
		$scope.masked = true;

		$scope.errorMail1 = "";
		$scope.errorMail2 = "";
		$scope.errorMail3 = "";
		$scope.errorMail4 = "";
		$scope.errorMail5 = "";
		$scope.errorMail6 = "";
		$scope.errorMail7 = "";
		//$scope.spouseAndUser=0;
		//$scope.monthlyMortgageAmounts=0;
		//$scope.Houseandnonhouse=0;

		//alert(JSON.stringify($scope.incomeandexpenses));


		//alert("remaining mortgage"+$scope.incomeandexpenses.remainingMortgage);
		//alert("reminig years mortage"+$scope.incomeandexpenses.remainingYearsMortgage);
		//alert("house valu"+$scope.incomeandexpenses.houseMarketValue);
		//alert("what is currentmortgage"+$scope.incomeandexpenses.whatIsYourCurrentMortgageRate);

		//$scope.monthlyMortgageAmount=(Math.pow((1+$scope.formData3.remainingMortgageInterestRate*1/1200),$scope.formData3.remainingYearsMortgage*12)*($scope.formData3.remainingMortgageInterestRate/1200)/(Math.pow((1+$scope.formData3.remainingMortgageInterestRate*1/1200),$scope.formData3.remainingYearsMortgage*12)-1)*$scope.formData3.remainingMortgage);
		$scope.monthlyMortgageAmounts=(Math.pow((1+$scope.incomeandexpenses.whatIsYourCurrentMortgageRate*1/1200),$scope.incomeandexpenses.remainingYearsMortgage*12)*($scope.incomeandexpenses.whatIsYourCurrentMortgageRate/1200)/(Math.pow((1+$scope.incomeandexpenses.whatIsYourCurrentMortgageRate*1/1200),$scope.incomeandexpenses.remainingYearsMortgage*12)-1)*$scope.incomeandexpenses.remainingMortgage);
		//alert("$scope.monthlyMortgageAmounts.. "+$scope.monthlyMortgageAmounts);
		//alert("$scope.incomeandexpenses.rentalExpense "+$scope.incomeandexpenses.rentalExpense);
		/* else if(!$scope.formData2.tax)
        		 {
	        		 $scope.masked = false;
        		 $scope.errmessage="Please enter tax income";
        		 $("#myModal").modal('show');
        		 window.location.href="##portfolio";

        		 }*/

		//  if($scope.monthlyMortgageAmounts){

		// }
		//else

		//alert($scope.incomeandexpenses.rentalExpense);
		//if(){

		//}
		//else
		//alert("jjj");
		$scope.Houseandnonhouse =($scope.incomeandexpenses.rentalExpense*1)+($scope.incomeandexpenses.monthlyExpense*1);
		//	alert("$scope.Houseandnonhouse.."+$scope.Houseandnonhouse);
		//alert("Ussr income1 bala"+$scope.incomeandexpenses.beforeIncomeTax);
		if($scope.incomeandexpenses.spouseBeforeIncomeTax==0){
			$scope.spouseAndUser=$scope.incomeandexpenses.beforeIncomeTax*1+$scope.incomeandexpenses.spouseBeforeIncomeTax*1;
			//alert("spous 0");
		}else{
			$scope.spouseAndUser=$scope.incomeandexpenses.beforeIncomeTax*1+$scope.incomeandexpenses.spouseBeforeIncomeTax*1;
			//alert("spou 1");
		}
		//alert("$scope.spouseAndUser.."+$scope.spouseAndUser);
		//	if($scope.Houseandnonhouse > ){

		//}
		//else 
		//alert("$scope.incomeandexpenses.rentalExpense.."+$scope.incomeandexpenses.rentalExpense);
		//alert("$scope.monthlyMortgageAmounts.."+$scope.monthlyMortgageAmounts);
		//alert("Spouse and user bala"+$scope.spouseAndUser)
		//alert("housing and non housing expenses"+$scope.Houseandnonhouse)
		if($scope.Houseandnonhouse>$scope.spouseAndUser){
			$scope.masked = false;
			$scope.AlertModal="Please enter expenses less than your income";
			$('#AlertModal').modal('show');
			$scope.load1();
			//alert("less..");
		}
		else if($scope.incomeandexpenses.rentalExpense<$scope.monthlyMortgageAmounts){
			$scope.AlertModal="Your house mortgage expense is more than the housing expense provided.";
			$('#AlertModal').modal('show');
			$scope.masked = false;
			$scope.load1();
			//alert("your wrong");
		}
		else if( $scope.incomeandexpenses.beforeIncomeTax==undefined||$scope.incomeandexpenses.beforeIncomeTax==""||$scope.incomeandexpenses.beforeIncomeTax==null){
			$scope.masked = false;
			// alert("oooo");
			//	$scope.AlertModal="Your house mortgage expense is more than the housing expense provided.";
			//	$('#AlertModal').modal('show');

			$scope.disabled=false; 	
			$scope.load1();

		}
		/*  else if(!$scope.incomeandexpenses.spouseBeforeIncomeTax){
    	 $scope.masked = false;
    	 	$scope.errorMail2="Please Enter Your Spouse Income" ; 
       }*/
		else if($scope.incomeandexpenses.monthlyExpense==undefined||$scope.incomeandexpenses.monthlyExpense==""||$scope.incomeandexpenses.monthlyExpense==null){
			$scope.masked = false;
			$scope.errorMail4="Please Enter Your Monthly expenses" ;

			$scope.disabled=false;
			$scope.load1();
		}
		else if($scope.incomeandexpenses.rentalExpense==undefined||$scope.incomeandexpenses.rentalExpense==""||$scope.incomeandexpenses.rentalExpense==null){
			$scope.masked = false;
			$scope.errorMail3="Please Enter Your Expenses Spent on Housing";

			$scope.disabled=false;
			$scope.load1();
		}

		//else if(){
		// $("#myModal1").modal('show');
		//}     
		else{

			//alert("kkkkk..."+JSON.stringify($scope.incomeandexpenses));
			$scope.disabled=true;
			$http({
				method: 'POST',
				url: 'UserProfile',
				data: $.param($scope.incomeandexpenses),
				headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
			}).then(function(result) {


				$scope.message=result.data;
				//alert(JSON.stringify(result.data));
				if(result.data.status=="fail"){
					//alert("inside fail expense");
					$scope.masked = false;
					$('#assetsModalExpense').modal('show');

				}

				else
				{
					//alert("inside sucesss  expenseee")
					window.location.href = "userProfile.jsp";
			
				}
				$scope.masked = false;
				//load1();
				//alert("false");
				console.log("message"+$scope.message);
				$scope.masked = false;
				$scope.disabled=true;

				$scope.load1();
			}, function(error) {
				//	 //alert("Fail");
				$scope.message=result.data;

			});
		}
	}
	$scope.editExpense=function(){
		//alert("inside expenseeeee");
		window.location.href = "#services";
		$scope.load1();
	}
	
	$scope.reloadFunction=function()
	{
		//alert();
		window.location.href = "userProfile.jsp";
	}


	$scope.editAssets=function(){
		///alert(JSON.stringify($scope.editAssetDetails));
		$scope.masked = true;
		$scope.errorMessage401="";
		$scope.errorMessageira="";
		$scope.errorMessagerira="";
		$scope.errorMessage529="";
		$scope.errorMessages401="";
		$scope.errorMessagesira="";
		$scope.errorMessagesrira="";
		$scope.errorMessagers529="";
		$scope.errorMessages529="";
		$scope.ajaxExection="1";
		$scope.editAssetDetails.form ="editAssets"
			$scope.editAssetDetails.married=$scope.userdetails.maritalStatus;
		$scope.editAssetDetails.cashEquivalent=$scope.editAssetDetails.cash;
		if($scope.editAssetDetails.nonTaxableInvestments1==null)
		{
			$scope.editAssetDetails.nonTaxableInvestments1=0;

		}
		if($scope.editAssetDetails.nonTaxableInvestments2==null)
		{
			$scope.editAssetDetails.nonTaxableInvestments2=0;

		}
		if($scope.editAssetDetails.nonTaxableInvestments3==null)
		{
			$scope.editAssetDetails.nonTaxableInvestments3=0;

		}
		$scope.sum=($scope.editAssetDetails.nonTaxableInvestments1*1+$scope.editAssetDetails.nonTaxableInvestments2*1+$scope.editAssetDetails.nonTaxableInvestments3*1);

		if($scope.sum>$scope.editAssetDetails.nonTaxableInvestments)
		{

			$scope.editAssetDetails.nonTaxableInvestments=$scope.sum;
		}

		// alert(JSON.stringify($scope.editAssetDetails));
		if($scope.editAssetDetails.nonTaxableInvestments=="Yes"){
			/*alert($scope.editAssetDetails.u401==undefined||$scope.editAssetDetails.u401==null ||($scope.editAssetDetails.u401=="" &&
					$scope.editAssetDetails.u401!='0'))

					alert($scope.editAssetDetails.uIRA==undefined||$scope.editAssetDetails.uIRA==null ||($scope.editAssetDetails.uIRA=="" &&
							$scope.editAssetDetails.uIRA!='0'))

							alert($scope.editAssetDetails.uRothIra==undefined||$scope.editAssetDetails.uRothIra==null ||($scope.editAssetDetails.uRothIra=="" &&
									$scope.editAssetDetails.uRothIra!='0'))
									
									alert($scope.editAssetDetails.u529==undefined||$scope.editAssetDetails.u529==null ||($scope.editAssetDetails.u529=="" &&
											$scope.editAssetDetails.u529!='0'))
											alert($scope.editAssetDetails.s401==undefined||$scope.editAssetDetails.s401==null ||($scope.editAssetDetails.s401=="" &&
												$scope.editAssetDetails.s401!='0'))
												
												alert("jiii"+$scope.editAssetDetails.sIRA==undefined||$scope.editAssetDetails.sIRA==null ||($scope.editAssetDetails.sIRA=="" &&
												$scope.editAssetDetails.sIRA!='0'))*/

									if($scope.editAssetDetails.u401==undefined||$scope.editAssetDetails.u401==null ||($scope.editAssetDetails.u401=="" &&
											$scope.editAssetDetails.u401!='0'))
									{

										$scope.masked = false;
										$scope.ajaxExection="2";
										$scope.errorMessage401="Please enter the 401k value";
										$("#newEdit").modal('show');
										$("#editAssetDetails").modal('show');

									}

									else if($scope.editAssetDetails.uIRA==undefined||$scope.editAssetDetails.uIRA==null ||($scope.editAssetDetails.uIRA=="" &&
											$scope.editAssetDetails.uIRA!='0')){
										$scope.errorMessage401="";
										$scope.masked = false;
										$scope.ajaxExection="2";
										$scope.errorMessageira="Please enter the Ira value";
										$("#editAssetDetails").modal('show');
										$("#newEdit").modal('show');
									}
									else  if($scope.editAssetDetails.uRothIra==undefined||$scope.editAssetDetails.uRothIra==null ||($scope.editAssetDetails.uRothIra=="" &&
											$scope.editAssetDetails.uRothIra!='0'))

										//if($scope.editAssetDetails.uRothIra==""||$scope.editAssetDetails.uRothIra==null||$scope.editAssetDetails.uRothIra==undefined)
									{
//										alert("rira");
										$scope.errorMessage401="";
										$scope.errorMessageira="";
										$scope.masked = false;
										$scope.ajaxExection="2";
										$scope.errorMessagerira="Please enter the Roth Ira value";
										$("#newEdit").modal('show');
										$("#editAssetDetails").modal('show');
									}
									else if($scope.editAssetDetails.u529==undefined||$scope.editAssetDetails.u529==null ||($scope.editAssetDetails.u529=="" &&
											$scope.editAssetDetails.u529!='0'))
										
										//if($scope.editAssetDetails.u529==""||$scope.editAssetDetails.u529==null||$scope.editAssetDetails.u529==undefined)
									{
//										alert("529ira");
										$scope.errorMessage401="";
										$scope.errorMessageira="";
										$scope.errorMessagerira="";
										$scope.masked = false;
										$scope.ajaxExection="2";
										$scope.errorMessage529="Please enter the 529 value";
										$("#editAssetDetails").modal('show');
										$("#newEdit").modal('show');
									}
									else if($scope.editAssetDetails.married=="Yes")
									{
										//if($scope.editAssetDetails.s401==""||$scope.editAssetDetails.s401==null||$scope.editAssetDetails.s401==undefined)
										if($scope.editAssetDetails.s401==undefined||$scope.editAssetDetails.s401==null ||($scope.editAssetDetails.s401=="" &&
												$scope.editAssetDetails.s401!='0'))
										{
											$scope.errorMessage401="";
											$scope.errorMessageira="";
											$scope.errorMessagerira="";
											//alert("s401");
											$scope.masked = false;
											$scope.ajaxExection="2";
											$scope.errorMessages401="Please enter the spouse 401k value";
											$("#newEdit").modal('show');
											$("#editAssetDetails").modal('show');
										}
										else if($scope.editAssetDetails.sIRA==undefined||$scope.editAssetDetails.sIRA==null ||($scope.editAssetDetails.sIRA=="" &&
												$scope.editAssetDetails.sIRA!='0'))
											//if($scope.editAssetDetails.sIRA==""||$scope.editAssetDetails.sIRA==null||$scope.editAssetDetails.sIRA==undefined)
										{
											$scope.errorMessage401="";
											$scope.errorMessageira="";
											$scope.errorMessagerira="";
											$scope.errorMessages401="";
											//alert("sira");
											$scope.masked = false;
											$scope.ajaxExection="2";
											$scope.errorMessagesira="Please enter the spouse Ira value";
											$("#editAssetDetails").modal('show');
											$("#newEdit").modal('show');
										}
										else if($scope.editAssetDetails.sRothIra==undefined||$scope.editAssetDetails.sRothIra==null ||($scope.editAssetDetails.sRothIra=="" &&
												$scope.editAssetDetails.sRothIra!='0'))
											//if($scope.editAssetDetails.sRothIra==""||$scope.editAssetDetails.sRothIra==null||$scope.editAssetDetails.sRothIra==undefined)
										{
											//alert("sprira");
											$scope.errorMessage401="";
											$scope.errorMessageira="";
											$scope.errorMessagerira="";
											$scope.errorMessages401="";
											$scope.errorMessagesira="";
											$scope.masked = false;
											$scope.ajaxExection="2";
											$scope.errorMessagesrira="Please enter the spouse Roth Ira value";
											$("#newEdit").modal('show');
											$("#editAssetDetails").modal('show');
										}
										else if($scope.editAssetDetails.s529==undefined||$scope.editAssetDetails.s529==null ||($scope.editAssetDetails.s529=="" &&
												$scope.editAssetDetails.s529!='0'))
											//if($scope.editAssetDetails.s529==""||$scope.editAssetDetails.s529==null||$scope.editAssetDetails.s529==undefined)
										{
											//alert("529ira");
											$scope.errorMessage401="";
											$scope.errorMessageira="";
											$scope.errorMessagerira="";
											$scope.errorMessages401="";
											$scope.errorMessagesira="";
											$scope.errorMessagesrira="";
											$scope.masked = false;
											$scope.ajaxExection="2";
											$scope.errorMessages529="Please enter the spouse 529 value";

											$("#editAssetDetails").modal('show');
											$("#newEdit").modal('show');


										}
									}
		}

		if($scope.ajaxExection=="1")
		{
			//alert("inside ajax...");

			$scope.errorMessage401="";
			$scope.errorMessageira="";
			$scope.errorMessagerira="";
			$scope.errorMessages401="";
			$scope.errorMessagesira="";
			$scope.errorMessagesrira="";
			$http({
				method: 'POST',
				url: 'UserProfile',
				data: $.param($scope.editAssetDetails),
				headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
			}).then(function(result) {
				//$scope.iralimits();
				$scope.load1();

				$scope.message=result.data;
				//alert($scope.message);
				if($scope.message=="fail")
				{

					$scope.masked = false;
					//alert("assetsModal1")
					$("#assetsModal").modal('show');
					$("#editBasicDetails").modal('hide');
					//$scope.message = result;

				}
				console.log("message"+$scope.message);
				$scope.masked = false;

			}, function(error) {
				// //alert("Fail");
				$scope.message=result.data;

			});
		}

	}
	/*------------------------------------removeChangePasswordError---------------------------- */
	$scope.removeChangePasswordError=function(){
		$scope.msgerr="";
		$scope.passwordmissmatcherror="";
	}
//	-----------------------------------------------------------------------------------	
	$scope.changepassword=function()
	{

		if($scope.changePassword.currentPassword==undefined || $scope.changePassword.currentPassword==null||$scope.changePassword.currentPassword=="")   
		{      //$scope.masked = false;
			$scope.msgerr="Please Enter Current Password";

		} else  if($scope.changePassword.newPassword==undefined || $scope.changePassword.newPassword==null||$scope.changePassword.newPassword=="")   
		{      
			$scope.msgerr="Please Enter New-Password";

		}else if(($scope.changePassword.newPassword.length) > 16){
			$scope.msgerr="Password should not have more than 16 characters";

		}
		else if(($scope.changePassword.newPassword.length) < 8){
			$scope.msgerr="Password should have atleast 8 alphanumeric characters including an upper case";

		}


		else if ($scope.changePassword.reEnterPassword==undefined || $scope.changePassword.reEnterPassword==null||$scope.changePassword.reEnterPassword=="")   
		{      
			$scope.msgerr="Please Re-Enter New Password";

		} else if($scope.changePassword.reEnterPassword!=$scope.changePassword.newPassword)
		{ 
			$scope.passwordmissmatcherror="Password Mismatch ";
		}

		else if((/^[A-Za-z0-9!@#$%^&*()_]{8,16}$/).test($scope.changePassword.newPassword)){
			if((/^[\S]+$/).test($scope.changePassword.newPassword))
			{
				if((/^[^a-z]+$/).test($scope.changePassword.newPassword))
				{
					$scope.msgerr="Password should have atleast one lower case character";
				}
				else if((/^[^A-Z]+$/).test($scope.changePassword.newPassword))
				{
					$scope.msgerr="Password should have atleast one upper case character";
				}
				else if((/^[^0-9]+$/).test($scope.changePassword.newPassword))
				{
					$scope.msgerr="Password should have atleast one numerical character";
				}


				else
				{
					$scope.masked = true;

					$http({
						method: 'POST',
						url: 'ChangePassword',
						data: $.param($scope.changePassword),
						headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
					}).then(function(result) {
						if(result.data.status=="success")
						{
							$scope.masked =false;
							$("#changePassword").modal('hide');
							$scope.massageAfterChangePassword="Password changed successfully !!";
							$("#successPassword").modal('show');
							var delay = 3000; //Your delay in milliseconds

							//setTimeout(function(){ $scope.load1(); }, delay);
							//window.location.href="index.jsp";
						}
						else if(result.data.status=="passwordMismatched")
						{
							$scope.masked = false;
							$scope.msgerr="Incorrect current password !!";

						}

						else if(result.data.status=="fail")
						{
							$scope.masked = false;
							$scope.msgerr="Password did not changed !!";

						}
					}, function(error) {

					});
				}
			}else{
				//alert("fail");
				$scope.errorNameregister="Password should not have space";
			}

		}
		else{
			// alert("fail");
			$scope.errorNameregister="Password should not have space";
		}

	}
//	-------------------------------------------------------------------------------------------------------------------
	$scope.editBasicDetails=function() { 
		//alert( "save");


		$scope.errorMail5="";
		$scope.errorname1="";
		$scope.errorname2="";
		$scope.errorname3="";
		$scope.errorname4="";
		$scope.error1="";
		$scope.error2="";
		$scope.errorname5="";
		$scope.errorname6="";
		$scope.errorname7="1";

		$scope.masked = true;
		$scope.editBasicDetail.city=$scope.formdata.desired_locationcity;


		//alert("dependents.."+$scope.editBasicDetail.dependants);


		//alert($scope.editBasicDetail.dependants);
		/*    if( $scope.kid.name==undefined || $scope.kid.name==""||$scope.kid.name==null){
			   alert("aparna");
			   $scope.masked = false;
			   $scope.errorMail5="Please Enter Your Kid Name" ;


		   }*/
		/*		   if($scope.editBasicDetail.fname==""||$scope.editBasicDetail.fname==null||$scope.editBasicDetail.fname==undefined){
			   $scope.errorname1="Please enter the name";
				 $('#editBasicDetails').modal('show');

		   }
		   else if(!(/^[a-zA-Z0-9]+[a-zA-Z0-9._-]+[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z.]{2,5}$/).test($scope.editBasicDetail.Email)){
			   $scope.errorname2="Please enter the email_id";
				 $('#editBasicDetails').modal('show');

		   }
		   else  if($scope.editBasicDetail.age==""||$scope.editBasicDetail.age==null||$scope.editBasicDetail.age==undefined){
			   $scope.errorname3="Please enter the age";
				 $('#editBasicDetails').modal('show');


		    }

		   else  if($scope.editBasicDetail.lname==""||$scope.editBasicDetail.lname==null||$scope.editBasicDetail.lname==undefined){
			   $scope.errorname4="Please enter the  last name ";
				 $('#editBasicDetails').modal('show');

		   }

		   if($scope.editBasicDetail.married=="Yes"){
			    if($scope.editBasicDetail.spouse_fname==""||$scope.editBasicDetail.spouse_fname==null||$scope.editBasicDetail.spouse_fname==undefined){
			    	 $scope.errorname5="Please enter the spouse first name ";
			    	 $('#editBasicDetails').modal('show');

				   }
			    else if($scope.editBasicDetail.spouse_lname==""||$scope.editBasicDetail.spouse_lname==null||$scope.editBasicDetail.spouse_lname==undefined){
			    	 $scope.errorname6="Please enter the spouse last name ";
			    	 $('#editBasicDetails').modal('show');


			    }
			    else if($scope.editBasicDetail.spouse_age==""||$scope.editBasicDetail.spouse_age==null||$scope.editBasicDetail.spouse_age==undefined){
			    	 $scope.errorname7="Please enter the spouse age ";
			    	 $('#editBasicDetails').modal('show');

			    }
			    else if(!$scope.editBasicDetail.kidscount){
			    	 if($scope.kid.name==""||$scope.kid.name==null){
			    		 $scope.errorname8="Please enter the name of Kid ";
			    	 $('#editBasicDetails').modal('show');
			    	 }

			    	 else{

			    		 $('#editBasicDetails').modal('hide');

			    	 }
			    }

			    else{
			    	//$scope.errmessage="";
					 $('#editBasicDetails').modal('hide');

			    }

		   }
		   else{
				$scope.errmessage="";
				 $('#editBasicDetails').modal('hide');

		   }
		 */	    	

		/*	alert("before "+$scope.married);
		if($scope.married=="No"){
			alert("inside no");
			//$scope.load1();

			$scope.masked = false;

			$scope.editBasicDetail.spouse_fname=="";
			$scope.editBasicDetail.spouse_lname="";
			$scope.load1();
			alert("inside after");
			///$scope.errorname7="1";
			$('#editBasicDetails').modal('hide');

		}*/

		if($scope.editBasicDetail.dependants==undefined||$scope.editBasicDetail.dependants==null ||($scope.editBasicDetail.dependants=="" &&
				$scope.editBasicDetail.dependants!='0'))
		{
			//alert("dependents123");
			//
			{
				$scope.masked = false;
				$scope.errorname7="2";
				$scope.dependantsErr="Please enter the number of dependants !!";
				//	alert($scope.editBasicDetail.kidscount);

				//alert(JSON.stringify($scope.editBasicDetail.kids));
				//alert($scope.editBasicDetail.kids[0].name);
				//alert($scope.editBasicDetail.kids.length);

			}



		}

		else if($scope.editBasicDetail.filingStatus=="Head of Household" && $scope.editBasicDetail.kidscount>0)
		{
			//	alert("kidsname");
			//
			for(var i=0;i<$scope.editBasicDetail.kids.length;i++)
			{
				if($scope.editBasicDetail.kids[i].name==""||$scope.editBasicDetail.kids[i].name==undefined||$scope.editBasicDetail.kids[i].age==""||$scope.editBasicDetail.kids[i].age==undefined)
				{
					$scope.masked = false;
					$scope.errorname7="2";
					$scope.dependantsErr="Please enter the  kid details !!";

				}
			}




		}
		else  if($scope.editBasicDetail.fname==""||$scope.editBasicDetail.fname==null||$scope.editBasicDetail.fname==undefined){
			// alert("asa");
			$scope.masked = false;
			$scope.errorname7="2";
			$scope.errorname1="Please enter the name";
			$('#editBasicDetails').modal('show');

		}
		else if(!(/^[a-zA-Z0-9]+[a-zA-Z0-9._-]+[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z.]{2,5}$/).test($scope.editBasicDetail.Email)){
			$scope.masked = false;
			$scope.errorname7="2";
			$scope.errorname2="Please enter the email_id";
			$('#editBasicDetails').modal('show');

		}
		else  if($scope.editBasicDetail.age==""||$scope.editBasicDetail.age==null||$scope.editBasicDetail.age==undefined){
			$scope.masked = false;
			$scope.errorname7="2";
			$scope.errorname3="Please enter the age";
			$('#editBasicDetails').modal('show');


		}
		else if($scope.editBasicDetail.married=="Yes"){
			//$scope.masked = false;
			//alert("length "+$scope.editBasicDetail.kids);
			if($scope.editBasicDetail.kids==undefined || $scope.editBasicDetail.kids==null || $scope.editBasicDetail.kids==""){
				if($scope.editBasicDetail.spouse_fname==""||$scope.editBasicDetail.spouse_fname==null||$scope.editBasicDetail.spouse_fname==undefined){
					$scope.masked = false;
					//alert(JSON.stringify($scope.editBasicDetail.kids.length));
					$scope.errorname7="2";
					$scope.errorname4="Please enter the spouse first name ";
					$('#editBasicDetails').modal('show');

				}
				else if($scope.editBasicDetail.spouse_age==""||$scope.editBasicDetail.spouse_age==null||$scope.editBasicDetail.spouse_age==undefined){
					$scope.masked = false;
					$scope.errorname7="2";
					$scope.errorname5="Please enter the spouse age ";
					$('#editBasicDetails').modal('show');

				}
				else{

					$scope.masked = false;
					$('#editBasicDetails').modal('hide');

				}
			}else{
				if($scope.editBasicDetail.kids.length>0){
					if($scope.editBasicDetail.spouse_fname==""){
						//alert("l");
						$scope.masked = false;
						//alert(JSON.stringify($scope.editBasicDetail.kids.length));
						$scope.errorname7="2";
						$scope.errorname4="Please enter the spouse first name ";
						$('#editBasicDetails').modal('show');
					}else if($scope.editBasicDetail.spouse_age==""){
						$scope.masked = false;
						$scope.errorname7="2";
						$scope.errorname5="Please enter the spouse age ";
						$('#editBasicDetails').modal('show');
					}
					for(var i=0;i<$scope.editBasicDetail.kids.length;i++){
						$scope.masked = false;
						if($scope.editBasicDetail.kids[i].name==undefined || $scope.editBasicDetail.kids[i].name==null || $scope.editBasicDetail.kids[i].name=="" ){
							$scope.masked = false;
							//alert(i+" 0 "+$scope.editBasicDetail.kids[i].name);
							$scope.errorname7="2";
							$scope.error1="Kids name and age should not be empty!!! ";

							$('#editBasicDetails').modal('show');
						}
						if($scope.editBasicDetail.kids[i].age==undefined || $scope.editBasicDetail.kids[i].age==null || $scope.editBasicDetail.kids[i].age=="" ){

							$scope.masked = false;
							//alert(j+" 0 "+$scope.editBasicDetail.kids[j].age);
							$scope.errorname7="2";
							$scope.error1="Kids name and age should not be empty!!! ";
							$('#editBasicDetails').modal('show');
						} 

					}




				}

				else {
					$scope.masked = false;
					$('#editBasicDetails').modal('hide');
				}
			}


		}



		if($scope.errorname7=="1")
		{

			$scope.masked = true;
			//alert("ssjd"+$scope.editBasicDetail.dependants);
			$scope.userdetails.dependants=$scope.editBasicDetail.dependants;
			//alert("aaaaaaaaaaaaaaa"+$scope.editBasicDetail.dependants);
			//alert("appp"+$scope.userdetails.dependants)
			$scope.dependantsErr="";
			$('#editBasicDetails').modal('hide');
			//alert("apppppppppppppppppppp"+JSON.stringify($scope.editBasicDetail));
			// alert($scope.editBasicDetail.married);
			$http({

				method: 'POST',
				url: 'UserProfile',
				data: $.param($scope.editBasicDetail),
				headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
			}).then(function(result) {
				//alert(JSON.stringify(result.data));
				if(result.data.status=="fail")
				{
					//alert("inside fail");
					$scope.masked = false;
					//alert("assetsModal1")
					$("#assetsModal").modal('show');
					//$scope.message = result;

				}
				else
				{
					window.location.href = "userProfile.jsp";
				}




				$scope.message=result.data;

				$scope.load1();
				$scope.errorName='';
				$scope.errorSuperhero='';
				$scope.masked = false;
				/*	alert(JSON.stringify(result.data));

				if(result.data=="fail"){

					alert("faillllll")

					$("#assetsModal").modal('show');


				}
				else{
					//window.location.href = "userProfile.jsp";
				}*/

			}, function(error) {
				$scope.message=result.data;
				$scope.errorName='';
				$scope.errorSuperhero='';
			});
		}
	}

	$scope.closeEditProfile=function(){
		//alert("closeEditProfile");
		$('#editBasicDetails').modal('show');

	}
	$scope.nextIncomeExpenses=function(){
		//alert("next");

		// $scope.masked = true;
		$scope.errorMail1 = "";
		$scope.errorMail2 = "";
		$scope.errorMail3 = "";
		$scope.errorMail4 = "";
		$scope.beforeIncomeTax1=$scope.incomeandexpenses.beforeIncomeTax1;
		$scope.monthlyExpense1=$scope.incomeandexpenses.monthlyExpense1;
		$scope.rentalExpense1=$scope.incomeandexpenses.rentalExpense1;
		//alert("$scope.rentalExpense1 "+$scope.rentalExpense1);

		// alert("$scope.incomeandexpenses.beforeIncomeTax "+ $scope.incomeandexpenses.beforeIncomeTax);
		// alert("$scope.incomeandexpenses.beforeIncomeTax1 "+ $scope.incomeandexpenses.beforeIncomeTax1);

		if( $scope.incomeandexpenses.beforeIncomeTax==undefined||$scope.incomeandexpenses.beforeIncomeTax==""||$scope.incomeandexpenses.beforeIncomeTax==null){
			$scope.masked = false;
			//alert("ppp "+$scope.incomeandexpenses.beforeIncomeTax);
			$scope.errorMail1="Please Enter Your Income" ;
			$scope.disabled=false;
			window.location.href="#services";
			$("#myModal").modal('show');
			$scope.disabled=true;
		}

		else if($scope.incomeandexpenses.monthlyExpense==undefined||$scope.incomeandexpenses.monthlyExpense==""||$scope.incomeandexpenses.monthlyExpense==null){
			$scope.masked = false;
			$scope.errorMail4="Please Enter Your Monthly expenses" ; 
			$scope.disabled=false;
			window.location.href="#services";
			$("#myModal").modal('show');
			$scope.disabled=true;
		}
		else if($scope.incomeandexpenses.rentalExpense==undefined||$scope.incomeandexpenses.rentalExpense==""||$scope.incomeandexpenses.rentalExpense==null){
			$scope.masked = false;
			$scope.errorMail3="Please Enter Your Expenses Spent on Housing";
			$scope.disabled=false;
			window.location.href="#services";
			$("#myModal").modal('show');
			$scope.disabled=true;
		}else if($scope.incomeandexpenses.beforeIncomeTax1!=$scope.incomeandexpenses.beforeIncomeTax || $scope.incomeandexpenses.monthlyExpense1!=$scope.incomeandexpenses.monthlyExpense || $scope.incomeandexpenses.rentalExpense1!=$scope.incomeandexpenses.rentalExpense){
			$("#myModal1").modal('show');
			window.location.href="#services";

		}
		// else if($scope.beforeIncomeTax1==$scope.incomeandexpenses.beforeIncomeTax || $scope.monthlyExpense1==$scope.incomeandexpenses.monthlyExpense || $scope.rentalExpense1==$scope.incomeandexpenses.rentalExpense){
		// $("#myModal1").modal('show');
		//window.location.href="#services";
		//}
		else{
			$scope.disabled=true;
			window.location.href="#portfolio";

		}
	}
	$scope.cleanEditBasicDetails=function(){
		$scope.load1();

		//alert("$scope.editBasicDetail.fname "+$scope.editBasicDetail.Email);
		if($scope.editBasicDetail.fname!=undefined || $scope.editBasicDetail.fname!=null || $scope.editBasicDetail.fname!="" ){
			$scope.errorname1="";
		}
		if($scope.editBasicDetail.age!=undefined || $scope.editBasicDetail.age!=null || $scope.editBasicDetail.age!=""){
			//alert("age");
			$scope.errorname3="";
		}
		if($scope.editBasicDetail.Email!=undefined || $scope.editBasicDetail.Email!=null || $scope.editBasicDetail.Email!=""){
			//alert("age");
			$scope.errorname2="";
		}
		//$scope.editBasicDetail.fname="";
		//$scope.editBasicDetail.state="";
		//$scope.editBasicDetail.Email="";
		//$scope.editBasicDetail.age="";
		$scope.editBasicDetail.college_info="";
		$scope.editBasicDetail.address1="";
		$scope.editBasicDetail.address2="";
		//$scope.editBasicDetail.lname="";
		//$scope.formdata.desired_locationcity="";
		//$scope.editBasicDetail.county="";
		//$scope.editBasicDetail.filingStatus="";
		//$scope.editBasicDetail.dependants="";
		//$scope.editBasicDetail.married="";
		$scope.editBasicDetail.spouse_fname="";
		$scope.editBasicDetail.spouse_lname="";
		$scope.editBasicDetail.spouse_age="";
		$scope.editBasicDetail.kidscount=0;
		$scope.kid.name="";
		$scope.kid.age="";
		$scope.kid.flag="";
		$scope.editBasicDetail.kids="";
		$scope.editBasicDetail.kids.name="";
		$scope.editBasicDetail.kids.age="";
		$scope.dependantsErr="";
		$scope.error1="";
		$scope.errorname5="";
		$scope.errorname4="";

	}

	$scope.sessionDetails={};
	$scope.sessionDelete={};
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
		//alert($scope.sessionDetails.cookieId);
		$scope.sessionDetails.lastVisitedPage=document.URL;
		//alert( $scope.sessionDetails.lastVisitedPage);
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
					//alert("Cookie ajax Success");
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
			//alert("Session got expired");
			$scope.deleteAllCookies();
			window.location.href="index.jsp";
		}

	}

	$scope.addkids = function() {
		//alert("hai");	
		//alert("kid count "+$scope.editBasicDetail.kidscount);
		$scope.dependantsErr="";
		$scope.message=$scope.editBasicDetail.kidscount;	
		$scope.editBasicDetail.kids1=[];
		//$scope.editBasicDetail.kids=[];
		$scope.lengthh=0;
		//alert("before 1st for loop "+JSON.stringify($scope.editBasicDetail.kids));
		if($scope.editBasicDetail.kids==undefined)
		{
			//	alert("inside ranj");
			///$scope.kidsType[i]=true;
			$scope.editBasicDetail.kids=[];
		}
		//alert("before 1st for loop length "+$scope.editBasicDetail.kids.length);

		if($scope.editBasicDetail.kids!=undefined)
		{
			//alert("inside if");
			for(i=0;i<$scope.editBasicDetail.kids.length;i++)		
			{
				//alert("inside 1st for loop "+$scope.editBasicDetail.kids.length);
				$scope.lengthh=$scope.editBasicDetail.kids.length;


				$scope.editBasicDetail.kids1[i]=$scope.editBasicDetail.kids[i];		
			}
		}
		//alert("Hello   "+$scope.editBasicDetail.kids1[0].name);		
		$scope.lengths=$scope.editBasicDetail.kids1.length;		
		//alert("2---"+$scope.lengths);	
		$scope.editBasicDetail.kids=[];		
		for(i=1;i<=$scope.editBasicDetail.kidscount;i++)	
		{	
			if(i-1<$scope.lengths){					
				$scope.editBasicDetail.kids.push({ 									
					name: $scope.editBasicDetail.kids1[i-1].name,					
					age: $scope.editBasicDetail.kids1[i-1].age,					
					flag:$scope.editBasicDetail.kids1[i-1].flag					
				});				
			}else				
			{										
				//alert("inside else");	
				$scope.editBasicDetail.kids.push({ 
					name: "",					
					age: "",					
					flag:"Yes"					
				});				
			}

			if($scope.editBasicDetail.kidscount>0)
			{

				//alert("$scope.editBasicDetail.kidscount"+$scope.editBasicDetail.kidscount)
				//alert("HIII  "+$scope.lengthh);
				if(i>=$scope.lengthh)
				{
					$scope.kidsType[i]=true;
					//alert(i);
				}
				if($scope.lengthh==0)
				{
					$scope.kidsType[0]=true;
					//	alert("000");
				}
				$scope.filingOptions= [{id: 'Single'},{id: 'Head of Household'}];
				$scope.editBasicDetail.filingStatus="Head of Household";
				$scope.dependants=true;

			} 

		}
		if($scope.editBasicDetail.kidscount==0)
		{
			//alert("inside 123");
			$scope.dependants=true;			
			$scope.filingOptions= [{id: 'Single'},{id: 'Head of Household'}];
			$scope.editBasicDetail.filingStatus="Single";
		}
		if($scope.editBasicDetail.kidscount==0 && $scope.editBasicDetail.dependants>0)
		{
			//alert("inside 456");
			$scope.dependants=true;			
			$scope.filingOptions= [{id: 'Single'},{id: 'Head of Household'}];
			$scope.editBasicDetail.filingStatus="Head of Household";
		}
		if($scope.editBasicDetail.married=="Yes") {

			$scope.filingOptions= [ {id: 'Married Filing Separately'}, {id: 'Married Filing Jointly'},{id: 'Head of Household'},{id: 'Qualified Widow'}];  

			$scope.editBasicDetail.filingStatus='Married Filing Jointly';
			$scope.dependants=false;
		}

	};	


	$scope.addkidsLoad = function($count,$childsOnLoad) {

		//alert("4");
		$scope.editBasicDetail.kids=[];
		for(i=0;i<$count;i++)
		{
			$scope.editBasicDetail.kids.push({ 
				name: $childsOnLoad[i].name,
				age: $childsOnLoad[i].age,
				flag: $childsOnLoad[i].flag
			});
		}
		//alert("1 "+$scope.editBasicDetail.kids.age);

	};

	$scope.iralimits=function(){
		$scope.editAssetDetails.u401="";
		$scope.editAssetDetails.uIRA="";
		$scope.editAssetDetails.uRothIra="";
		$scope.editAssetDetails.u529="";
		$scope.editAssetDetails.s401="";
		$scope.editAssetDetails.sIRA="";
		$scope.editAssetDetails.sRothIra="";
		$scope.editAssetDetails.s529="";

		//alert("status  :"+$scope.userdetails.maritalStatus)
		//alert("nontax in iralimit :"+$scope.editAssetDetails.nonTaxableInvestments)
		if($scope.editAssetDetails.nonTaxableInvestments=="Yes")
		{
			$scope.limitOption=false;
			if ($scope.userdetails.maritalStatus == "Yes") {

				$scope.limitSpouseOption=false;
			}
			else{
				$scope.limitSpouseOption=true;
			}
		}
		else{
			//	alert("hello");
			$scope.limitOption=true;
			$scope.limitSpouseOption=true;
		}

	}



	$scope.checkform4Close=function(){
		//alert("checkform4Close");
		//window.location.href = "#services";

	}
	$scope.newAssetsEdit= function(){
		//alert("inside edit assets====="+JSON.stringify($scope.userdetails));
		// alert ("$scope.editBasicDetail.maritalStatus=="+$scope.editBasicDetail.maritalStatus)
		//alert("$scope.editAssetDetails.nonTaxableInvestments==="+$scope.editAssetDetails.nonTaxableInvestments)
		if($scope.userdetails.nonTaxableInvestments=="Yes"){

			if($scope.userdetails.maritalStatus=="Yes"){
				$scope.limitSpouseOption=false;
				$scope.limitOption=false;

				$scope.editAssetDetails.u401=$scope.userdetails.user401;
				$scope.editAssetDetails.uIRA=$scope.userdetails.userIra;
				$scope.editAssetDetails.uRothIra=$scope.userdetails.userRothira;
				$scope.editAssetDetails.u529=$scope.userdetails.user559;
				$scope.editAssetDetails.s401=$scope.userdetails.spouse401;
				$scope.editAssetDetails.sIRA=$scope.userdetails.spouseIra;
				$scope.editAssetDetails.sRothIra=$scope.userdetails.spouseRothira;
				$scope.editAssetDetails.s529=$scope.userdetails.spouse529;
				$scope.editAssetDetails.cash=$scope.userdetails.cash;
				$scope.editAssetDetails.taxableInvestments=$scope.userdetails.taxableInvestments;
				$scope.editAssetDetails.nonTaxableInvestments=$scope.userdetails.nonTaxableInvestments;
				//alert("cash"+$scope.editAssetDetails.cash+"aaa"+$scope.userdetails.cash);
				//alert("tax"+$scope.editAssetDetails.taxableInvestments+"aaa"+$scope.editAssetDetails.taxableInvestments);
				//alert("nontax"+$scope.editAssetDetails.nonTaxableInvestments+"aaa"+$scope.editAssetDetails.nonTaxableInvestments);

				/*	alert("asa"+$scope.editBasicDetail.u401+"aaa"+$scope.userdetails.user401);
					alert("asa"+$scope.editBasicDetail.uIRA+"aaa"+$scope.userdetails.userIra);
					alert("asa"+$scope.editBasicDetail.uRothIra+"aaa"+$scope.userdetails.userRothira);
					alert("asa"+$scope.editBasicDetail.u529+"aaa"+$scope.userdetails.user559);
					alert("asa"+$scope.editBasicDetail.s401+"aaa"+$scope.userdetails.spouseIra);
					alert("asa"+$scope.editBasicDetail.sRothIra+"aaa"+$scope.userdetails.spouseRothira);
					alert("asa"+$scope.editBasicDetail.sIRA+"aaa"+$scope.userdetails.spouseRothira);
					alert("asa"+$scope.editBasicDetail.s529+"aaa"+$scope.userdetails.spouse529);*/


			}else{
				//alert("inside edit assets"+JSON.stringify($scope.userdetails));
				//alert("inside else======="+$scope.editAssetDetails.cash)
				$scope.limitSpouseOption=true;
				$scope.limitOption=false;
				$scope.editAssetDetails.u401=$scope.userdetails.user401;
				$scope.editAssetDetails.uIRA=$scope.userdetails.userIra;
				$scope.editAssetDetails.uRothIra=$scope.userdetails.userRothira;
				$scope.editAssetDetails.u529=$scope.userdetails.user559;
				//alert($scope.editAssetDetails.cash);
				$scope.editAssetDetails.cash=$scope.userdetails.cash;
				//alert("===="+$scope.editAssetDetails.cash);


			}
		}
		else
		{
			$scope.editAssetDetails.cash=$scope.userdetails.cash;
			//alert("===="+$scope.editAssetDetails.cash);
			//	alert("hjhjjhj");
			$scope.limitSpouseOption=true;
			$scope.limitOption=true;
		}

	}
	/*	$scope.editAssetDetails.cashEquivalent=$scope.userdetails.cash;

			$scope.editAssetDetails.taxableInvestments=$scope.userdetails.taxableInvestments;
			$scope.editAssetDetails.nonTaxableInvestments=$scope.userdetails.nonTaxableInvestments;
			$scope.editAssetDetails.realEstate=$scope.userdetails.realEstate;
			$scope.userdetails.realestate=$scope.userdetails.realEstate;
			$scope.editAssetDetails.realestate=$scope.userdetails.realEstate;
			$scope.editAssetDetails.nonTaxableInvestments1=$scope.userdetails.Taxdeferred;
			$scope.editAssetDetails.nonTaxableInvestments2=$scope.userdetails.rothRetirement;
			$scope.editAssetDetails.nonTaxableInvestments3=$scope.userdetails.fiveTnp;
			$scope.fiveTnp=$scope.userdetails.fiveTnp;
		}*/

	$scope.load1=function() { 
		$scope.masked = true;
		$scope.disabled=true;
		$scope.userdetails.form="userDetails";
		$http({
			method: 'POST',
			url: 'UserProfile',
			data: $.param($scope.userdetails),
			headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).then(function(result) {
			//alert("onloadto=========="+JSON.stringify(result.data));
			//alert("dependents===="+$scope.userdetails.dependants)
			if(result.data.maritalStatus=="Yes")
			{
				$scope.limitSpouse=false;
				$scope.limit=false;
				$scope.SpouceDetail=true;
			}
			else
			{

				$scope.SpouceDetail=false;
				$scope.limitSpouse=true;
				$scope.limit=false;
			}
			//alert("nontax abcd    "+result.data.nonTaxableInvestments);
			if(result.data.nonTaxableInvestments=="No")
			{
				$scope.userdetails.nonTaxableInvestments=result.data.nonTaxableInvestments;
				$scope.limitSpouse=true;
				$scope.limit=true;
				//alert("$scope.limitSpouse    "+$scope.limitSpouse);
				//alert("$scope.limit    "+$scope.limit);
			}
			//alert(JSON);
			$scope.userdetails=result.data;

			$scope.incomeProfiles=result.data.incomeProfiles;

			$scope.editBasicDetail.fname=$scope.userdetails.name;
			// alert($scope.userdetails.city);
			$scope.editBasicDetail.city=$scope.userdetails.city;
			$scope.formdata.citym=$scope.userdetails.city;
			$scope.editBasicDetail.filingStatus=$scope.userdetails.filingStatus
			$scope.finPlanCount=$scope.userdetails.finPlanCount;
			//alert($scope.finPlanCount);
			$scope.editBasicDetail.Email=$scope.userdetails.email;
			$scope.editBasicDetail.age=$scope.userdetails.age;
			$scope.editBasicDetail.college_info=$scope.userdetails.collegeinfo;
			$scope.editBasicDetail.address1=$scope.userdetails.address1;
			$scope.editBasicDetail.address2=$scope.userdetails.address2;
			$scope.editBasicDetail.county=$scope.userdetails.county;
			$scope.editBasicDetail.lname=$scope.userdetails.lastName;
			$scope.editBasicDetail.state=$scope.userdetails.state;
			$scope.getCities();
			// alert("2");
			$scope.editBasicDetail.dob=$scope.userdetails.dob;
			$scope.editBasicDetail.spouse_fname=$scope.userdetails.spouseName;
			//alert("name "+$scope.editBasicDetail.spouse_fname);
			$scope.editBasicDetail.spouse_lname=$scope.userdetails.spouseLastName;
			$scope.editBasicDetail.spouse_age=$scope.userdetails.spouseAge;
			$scope.editBasicDetail.spouse_dob=$scope.userdetails.spouseDob;
			$scope.editBasicDetail.married=$scope.userdetails.maritalStatus;
			$scope.editBasicDetail.dependants=$scope.userdetails.dependants;


			//alert($scope.userdetails.houseinfo);
			// alert( $scope.editBasicDetail.married);

			//if($scope.editBasicDetail.married=="Yes")
			if($scope.userdetails.houseinfo=="Own")
			{
				//alert("inside if");
				$scope.filingOptions= [ {id: 'Married Filing Separately'}, {id: 'Married Filing Jointly'},{id: 'Head of Household'},{id: 'Qualified Widow'}];
				//$("#myip").show();
				//$("#marriedid").show();
				//$("#singleid").hide();
				$scope.houseInfo=true;

			}
			else
			{
				//alert("else");
				$scope.masked = false;
				$scope.editBasicDetail.spouse_fname="";
				$scope.editBasicDetail.spouse_lname="";
				$scope.editBasicDetail.spouse_age="";
				$scope.editBasicDetail.kidscount=0;
				//$scope.kid.name="";
				//$scope.kid.age="";
				//$scope.editBasicDetail.kidscount=0;

				$scope.filingOptions= [ {id: 'Single'}];
				//$("#myip").hide();
				//$("#singleid").show();
				//$("#marriedid").hide();

			}
			$scope.editBasicDetail.filingStatus=$scope.userdetails.filingStatus;
			//alert($scope.editBasicDetail.filingStatus);
			$scope.editBasicDetail.dependants=$scope.userdetails.dependants;
			//alert($scope.userdetails.dependants);
			if($scope.editBasicDetail.filingStatus=="Head of Household")
			{

				$scope.dependants=true;

			}
			else
			{
				$scope.dependants=false;
			}


			$scope.change();
			//alert($scope.userdetails.kidscount);

			//alert();
			//alert($scope.userdetails.childs.length);
			/*  for(i=1;i<=2;i++)
			{
			 $scope.editBasicDetail.kids.push({ 
			            name: "hello",
			            age: ""
			          });
			}  
			 */ 
			if($scope.editBasicDetail.married=="Yes")
			{
				//alert("Changed");
				$scope.editBasicDetail.spouse_fname=$scope.userdetails.spouseName;
				$scope.editBasicDetail.spouse_lname=$scope.userdetails.spouseLastName;
				$scope.editBasicDetail.spouse_age=$scope.userdetails.spouseAge;
			}
			$scope.incomeandexpenses.beforeIncomeTax=Math.round($scope.userdetails.userIncome/12);
			$scope.incomeandexpenses.beforeIncomeTax1=$scope.incomeandexpenses.beforeIncomeTax;
			//alert($scope.incomeandexpenses.beforeIncomeTax1);
			$scope.incomeandexpenses.spouseBeforeIncomeTax=Math.round($scope.userdetails.spouseIncome/12);
			// alert($scope.incomeandexpenses.beforeIncomeTax);
			//( $scope.incomeandexpenses.beforeIncomeTax);
			//alert("Bala"+$scope.userdetails.remainingYearsMortgage);
			$scope.incomeandexpenses.monthlyExpense=$scope.userdetails.monthlyExpenses;
			//alert($scope.incomeandexpenses.monthlyExpense);
			$scope.incomeandexpenses.monthlyExpense1=$scope.incomeandexpenses.monthlyExpense;
			//	alert("$scope.incomeandexpenses.rentalExpense--> "+$scope.incomeandexpenses.monthlyExpense);
			$scope.incomeandexpenses.rentalExpense=$scope.userdetails.rentalExpenses;
			$scope.incomeandexpenses.houseMarketValue=$scope.userdetails.houseValue;
			$scope.incomeandexpenses.whatIsYourCurrentMortgageRate=$scope.userdetails.whatIsYourCurrentMortgageRate;
			//alert("hello");
			//alert(JSON.stringify($scope.userdetails));
			$scope.incomeandexpenses.remainingMortgage =$scope.userdetails.remainingMortgage;
			$scope.incomeandexpenses.remainingYearsMortgage=$scope.userdetails.remainingYearsMortgage;


			//alert("$scope.incomeandexpenses.whatIsYourCurrentMortgageRate "+$scope.incomeandexpenses.whatIsYourCurrentMortgageRate);
			//alert("$scope.incomeandexpenses.remainingYearsMortgage --> "+$scope.incomeandexpenses.remainingYearsMortgage);
			//alert("$scope.incomeandexpenses.currentmorExpense--> "+$scope.incomeandexpenses.currentMortgageRate);
			//alert("$scope.incomeandexpenses.remainingExpense--> "+$scope.incomeandexpenses.remainingMortgage);
			$scope.incomeandexpenses.rentalExpense1=$scope.incomeandexpenses.rentalExpense;
			$scope.incomeandexpenses.otherExpense=$scope.userdetails.otherExpenses;

			$scope.editAssetDetails.cashEquivalent=$scope.userdetails.cash;

			$scope.editAssetDetails.taxableInvestments=$scope.userdetails.taxableInvestments;
			$scope.editAssetDetails.nonTaxableInvestments=$scope.userdetails.nonTaxableInvestments;
			$scope.editAssetDetails.realEstate=$scope.userdetails.realEstate;
			$scope.userdetails.realestate=$scope.userdetails.realEstate;
			$scope.editAssetDetails.realestate=$scope.userdetails.realEstate;
			$scope.editAssetDetails.nonTaxableInvestments1=$scope.userdetails.Taxdeferred;
			$scope.editAssetDetails.nonTaxableInvestments2=$scope.userdetails.rothRetirement;
			$scope.editAssetDetails.nonTaxableInvestments3=$scope.userdetails.fiveTnp;
			$scope.fiveTnp=$scope.userdetails.fiveTnp;
			//$scope.getIncomeProfile( $scope.incomeProfiles[0]);
			$scope.editBasicDetail.kidscount=$scope.userdetails.kidscount;
			//alert("($scope.editBasicDetail.kidscount==="+$scope.editBasicDetail.kidscount);
			if($scope.editBasicDetail.kidscount>0)
			{
				//alert("9000  "+$scope.editBasicDetail.kidscount)
				$scope.addkidsLoad($scope.userdetails.childs.length,$scope.userdetails.childs);
			}
			if($scope.editBasicDetail.kidscount>0 && $scope.editBasicDetail.married=="No"){
				//alert("kisdd==="+$scope.editBasicDetail.kidscount+"    "+$scope.editBasicDetail.married);
				$scope.editBasicDetail.filingStatus=$scope.userdetails.filingStatus;
				$scope.addkidsLoad($scope.userdetails.childs.length,$scope.userdetails.childs);
			}
			//	alert("12");
			if($scope.editBasicDetail.married=="No" && $scope.editBasicDetail.filingStatus=="Single"){

				$scope.kidForSingle=false;
				$scope.dependants=false;
				//alert("uiio"+$scope.editBasicDetail.married+"===="+$scope.editBasicDetail.filingStatus);
				//alert("=uiio==="+$scope.editBasicDetail.kidscount+"$scope.userdetails.childs=="+$scope.userdetails.childs)
				//$scope.editBasicDetail.kidscount=0;
				$scope.addkidsLoad(0,$scope.userdetails.childs);
			}
			$scope.studentType();
			//alert("$scope.editBasicDetail.kidscount "+$scope.editBasicDetail.kidscount);
			//alert("3");
			// alert();
			//alert("$scope.userdetails.childs "+JSON.stringify($scope.userdetails.childs));
			$scope.masked = false;
			//$scope.addkidsLoad($scope.userdetails.childs.length,$scope.userdetails.childs);
			$scope.masked = false;
			//alert($scope.userdetails.rothRetirement);
			/*		$scope.errorName='';
				$scope.errorSuperhero=''; */
		}, function(error) {
			////alert("Fail");
			$scope.message="Fail";
			/*$scope.errorName='';
					$scope.errorSuperhero=''; */
		}); $scope.formdata.desired_locationcity

	}

	$scope.getCities=function()
	{

		$scope.formData1.city=$scope.editBasicDetail.state;
		$scope.formData1.actionType="getCities";
		$scope.disabledCity="true";
		$scope.msgerr="";
		$http({
			method: 'POST',
			url: 'AutoComplete',
			data: $.param($scope.formData1),
			headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).then(function(result) {
			$scope.citys=result.data;
			$scope.citys.sort();

			if($scope.citys.indexOf($scope.userdetails.city)!=-1)
			{
				$scope.formdata.desired_locationcity=$scope.userdetails.city;
			}
			else
			{
				$scope.formdata.desired_locationcity=$scope.citys[0];
			}


			$scope.disabledCity=false;

		}, function(error) {		
			window.location.href="";
		});
	}

	$scope.checkDependants=function()
	{
		//alert("checkDependants");
		$scope.dependantsErr="";
		if($scope.editBasicDetail.filingStatus=="Head of Household")
		{
			$scope.kidForSingle=true;
			$scope.dependants=true;
			$scope.editBasicDetail.kids="";
			$scope.editBasicDetail.kids.name="";
			$scope.editBasicDetail.kids.age="";
			$scope.marriedt=false;
			$scope.editBasicDetail.married="No";
			$scope.editBasicDetail.kidscount=0;

		}
		else if($scope.editBasicDetail.filingStatus=="Single")
		{
			//alert("inside single");
			$scope.editBasicDetail.kidscount=0;
			$scope.editBasicDetail.dependants=0;
			$scope.marriedt=false;
			$scope.kidForSingle=false;
			$scope.dependants=false;
		}

		else
		{
			//alert("status11"+$scope.editBasicDetail.filingStatus);
			//alert("else"+$scope.kidForSingle);
			$scope.dependants=true;
			$scope.marriedt=true;
			$scope.kidForSingle=false;
			$scope.editBasicDetail.married="Yes";


		}




	}

	$scope.getCounty=function()
	{

		$scope.formData1.country=$scope.formdata.desired_locationcity;
		$scope.formData1.city=$scope.editBasicDetail.state;
		$scope.formData1.actionType="getCounty";

		$scope.msgerr="";
		$http({
			method: 'POST',
			url: 'AutoComplete',
			data: $.param($scope.formData1),
			headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).then(function(result) {
			$scope.editBasicDetail.county=result.data;

			$scope.disabledCity=false;

		}, function(error) {		
			window.location.href="index.jsp";
		});
	}

	//--------------------code for chart in the advanced section------------------------
	$scope.getIncomeProfile=function($name)
	{
		$scope.incomeProfile.form="getIncomeChart";
		$scope.incomeProfile.name=$name;

		$http({
			method: 'POST',
			url: 'UserProfile',
			data: $.param($scope.incomeProfile),
			headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).then(function(result) {
			$scope.chartUserIncome=[];
			$scope.chartSouseIncome=[];
			$scope.chartCombinedIncome=[];
			$scope.chartYear=[];
			$combined_income=result.data.combined_income;
			$spouse_income=result.data.spouse_income;
			$user_income=result.data.user_income;
			$marital_status=result.data.marital_status;

			for(i=0;i<$user_income.length;i++)
			{
				$scope.chartYear.push({label :$user_income[i].year.toString()});
				$scope.chartUserIncome.push({value:$user_income[i].value, year:$user_income[i].year,"allowDrag": "0"});

				if($marital_status=="Yes")
				{

					$scope.chartSouseIncome.push({value:$spouse_income[i].value, year:$spouse_income[i].year,"allowDrag": "0"});
					$scope.chartCombinedIncome.push({value:$combined_income[i].value, year:$combined_income[i].year,"allowDrag": "0"});
				} 
			}

//			alert(JSON.stringify($scope.chartYear));
			incomeExpenseChart();		
		}, function(error) {		

		});


	}

	function incomeExpenseChart()
	{

		FusionCharts.ready(function () {

			var salesPrediction = new FusionCharts({
				type: 'dragline',
				renderAt: 'chart-container1',
				width: '1000',
				height: '350',
				dataFormat: 'json',
				dataSource: {
					"chart": {
						"caption": "Income",
						"showvalues": "0",
						"xAxisName": "Year",
						"yAxisName": "Income",
						"bgcolor": "FFFFFF",
						"showalternatehgridcolor": "0",
						"showplotborder": "1",
						"divlinecolor": "CCCCCC",
						"formatNumberScale":"0",
						"showcanvasborder": "0",
						"captionpadding": "20",
						"legendpadding": "10",
						"plotbordercolor": "2f69bf",
						"linethickness": "3",
						"formbtnbgcolor": "666666",
						"btntextcolor": "FFFFFF",
						"showrestorebtn": "0",
						"canvasborderalpha": "0",
						"legendshadow": "0",
						"legendborderalpha": "0",
						"showborder": "0",
						"submitformusingajax": "0"
					},
					"categories": [
					               {
					            	   "category": $scope.chartYear
					               }
					               ],
					               "dataset": [
					                           {
					                        	   "id": "IJ",
					                        	   "seriesname": "User Income",
					                        	   "color": "328de4",
					                        	   "data":$scope.chartUserIncome
					                           },
					                           {
					                        	   "id": "LJ",
					                        	   "seriesname": "Spouse Income",
					                        	   "color": "32e2e2",
					                        	   "data":  $scope.chartSouseIncome
					                           },
					                           {
					                        	   "id": "CR",
					                        	   "seriesname": "Combined Income",
					                        	   "showvalues": "0",
					                        	   "color": "#B0C10C",
					                        	   "data":   $scope.chartCombinedIncome
					                           }

					                           ],
					                           "styles": {
					                        	   "definition": [
					                        	                  {
					                        	                	  "name": "myCaptionFont",
					                        	                	  "type": "font",
					                        	                	  "font": "Arial",
					                        	                	  "size": "14",
					                        	                	  "bold": "1"
					                        	                  },
					                        	                  {
					                        	                	  "name": "mySubCaptionFont",
					                        	                	  "type": "font",
					                        	                	  "font": "Arial",
					                        	                	  "size": "10",
					                        	                	  "bold": "0"
					                        	                  }
					                        	                  ],
					                        	                  "application": [
					                        	                                  {
					                        	                                	  "toobject": "Caption",
					                        	                                	  "styles": "myCaptionFont"
					                        	                                  },
					                        	                                  {
					                        	                                	  "toobject": "SubCaption",
					                        	                                	  "styles": "mySubCaptionFont"
					                        	                                  }
					                        	                                  ]
					                           }
				},
				events: {
					'dataplotdragend': function(evt, arg){



						var dsIndx = arg && arg.datasetIndex,
						dtIndx = arg && arg.dataIndex,
						val = arg && parseInt(arg.endValue, 10);


					}             

				}
			}).render();
		});


	} 





});


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
$('#editBasicDetails').modal('hide'); 