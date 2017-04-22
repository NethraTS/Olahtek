/**
 * Master Controller
 */

angular.module('wealthsetter')
    .controller('WelcomeCtrl', ['$scope', '$cookieStore','$state','$rootScope','$http','toaster','$location', WelcomeCtrl]);

function WelcomeCtrl($scope, $cookieStore,$state,$rootScope,$http,toaster,$location) {
	//$scope.screen = 0;
	/* redirect page based on state */
	$rootScope.boolChangeClass = true;
	$scope.limit = true;
	$scope.limitSpouse = true;
	$scope.kidForSingle = false;
    $scope.kidcostFactor = false;
	$scope.kidCostCalculated=0;
	$scope.welcome = {basic:{spouse:{},kids:[]}};
	$scope.screen = parseInt($state.current.name.split('.')[1]);
	$scope.userDetails = {};
	function readCookie(name) {
		// ////alert( "hi" );
		var nameEQ = name + "=";
		var ca = document.cookie.split(";");
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == " ")
				c = c.substring(1, c.length);
			if (c.indexOf(nameEQ) == 0)
				return c.substring(nameEQ.length, c.length);
		}
		return null;
	}
    $scope.cookieId = readCookie("AhTwxlO");
    if ($scope.cookieId != null || $scope.cookieId == "") {
    	$rootScope.loginLogoutText = "Logout";
    } else {
    	$rootScope.loginLogoutText = "Login | Signup";
    }
	$scope.$watch('loginLogoutText', function() {
        console.log($rootScope.loginLogoutText);
    });
	$state.go('welcome.'+$scope.screen);
    $scope.changeItem = function(item){
    	$state.go('welcome.'+item);
    	$scope.screen = item;
    }
    $scope.user = {
      name: "",
      notifications: {}
    }
    $scope.errorMap = {
      required: "This field is mandatory",
      email: "Please enter a valid email",
      minlength: "Field limit is exceeded",
    }
	$scope.filingOptions = [ {
		id: "Yes"
	}, {
		id: "No"
	} ];
    $scope.investment = {
    		stocksbonds : {
    			value : ""
    		},
    		userinterest : {
    			value : ""
    		},
    		etfs : {
    			value : ""
    		},
    		profitloss : {
    			value : ""
    		}
    	};

    	$scope.changeStocksBonds = function(input) {
    		$scope.investment.stocksbonds.value = input;
    	}

    	$scope.changeUserInterest = function(input1) {
    		$scope.investment.userinterest.value = input1;
    	}

    	$scope.changeEtfs = function(input2) {
    		$scope.investment.etfs.value = input2;
    	}

    	$scope.changeProfitloss = function(input3) {
    		$scope.investment.profitloss.value = input3;
    	}
    	
    	$scope.checkStocksBonds = function() {
    		if($scope.investment.stocksbonds.value != "" || $scope.investment.stocksbonds.value != undefined || $scope.investment.stocksbonds.value != null) {
    			//$scope.changeItem($scope.screen+1);
    			$state.go('welcome.4');
    		}
    	}
    	
    	$scope.checkUserInterest = function() {
    		if($scope.investment.userinterest.value != "" || $scope.investment.userinterest.value != undefined || $scope.investment.userinterest.value != null) {
    			$state.go('welcome.5');
    		}
    	}
    	
    	$scope.checkEtfs = function() {
    		if($scope.investment.etfs.value != "" || $scope.investment.etfs.value != undefined || $scope.investment.etfs.value != null) {
    			$state.go('welcome.6');
    		}
    	}
    	
    	$scope.checkProfitLoss = function() {
    		if($scope.investment.profitloss.value != "" || $scope.investment.profitloss.value != undefined || $scope.investment.profitloss.value != null) {
    			//$scope.changeItem($scope.screen+1);
    			$state.go('welcome.7');
    		}
    	}
    	
    	$scope.submitPortfolio1 = function() {
    		//$scope.masked = true;
    		$scope.formDataPortfolio = {};
    		$scope.formDataPortfolio.riskScore = ($scope.investment.stocksbonds.value * 1)
    				+ ($scope.investment.userinterest.value * 1)
    				+ ($scope.investment.etfs.value * 1)
    				+ ($scope.investment.profitloss.value * 1);
    		$scope.formDataPortfolio.formType = "duringRegistration";

    		$http( {
    			method: "POST",
    			url: "investmentPortfolio",
    			data: $.param( $scope.formDataPortfolio ),
    			headers: {
    				"Content-Type": "application/x-www-form-urlencoded"
    			}
    		} ).then( function( result ) {
    			//$scope.completedInvestment = 1;
    			//$scope.masked = false;
    			//call function for registering $scope.checkform4(); in app.js
    			
    			$scope.checkform4()
    		}, function( error ) {
    			$scope.message = result.data;
    		} );

    	};
   
    /* End of redirect page based on state */
   
    function makeEmpty(val){
    	var res = parseInt(val);
    	if(res == 0){
    		return ""; 
    	}else{
    		return val;
    	}
    }
    
    /* fetch user Details */
    $scope.fetchUserDetails = function() {
    	var input ={};
    	input.form = "userDetails";
		$http({
			method : "POST",
			url : "UserProfile",
			data : $.param(input),
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded"
			}
		}).then(function(result) {
			$scope.filingStatus = result.data.filingStatus;
			var data = result.data;
			$scope.welcome.basic.age =  data.age;
			$scope.welcome.basic.married = data.martialStatus;
			$scope.welcome.basic.country = data.city;
			$scope.welcome.basic.city = data.state;
			$scope.change();
			$scope.welcome.basic.spouse.name = data.spouseName;
			$scope.welcome.basic.spouse.sage = data.spouseAge;
			$scope.welcome.basic.kidscount = data.kidscount;
			if(data.maritalStatus == 'Yes'){
				$scope.hideStatus = false;
				$scope.marriedt = true;
			}else{
				$scope.hideStatus = true;
				$scope.marriedt = false;
				$scope.welcome.basic.sage = "";
				$scope.formData2.stax = "";
			}
			$scope.welcome.basic.married = data.maritalStatus;
			
			if(data.dependants == 0 && data.childs !== undefined && data.childs.length>0){
				$scope.welcome.basic.filingStatus = "Yes";
				
			}else{
				$scope.welcome.basic.filingStatus = "No";
			}
			$scope.welcome.basic.dependants = data.dependants;
			$scope.welcome.basic.kids = [];
			if(data.childs !== undefined ){
				//$scope.welcome.basic.kids = $scope.addkidsLoad(data.childs.length,data.childs);
				for ( i = 0; i < data.childs.length; i++ ) {
					$scope.welcome.basic.kids.push( {
						name: data.childs[i].name,
						age: ""+data.childs[i].age,
						flag: data.childs[i].flag
					});
				}
			}
			$scope.autocompleteForCounty();
			$scope.welcome.basic.kidscount = data.kidscount;
			$scope.formData2.tax = data.userBeforeTaxIncome;
			$scope.formData2.stax = data.spouseBeforeTaxIncome;
			$scope.formData2.expenses = data.monthlyExpenses;
			
			if(data.kidCostCalculated !== undefined && data.kidCostCalculated != 0){
				$scope.kidcostFactor = true;
				$scope.formData2.kidcostFactor = data.kidCostCalculated;
			}
			if(data.houseinfo !== undefined && data.houseinfo == "Own"){
				$scope.house = true;
			}else{
				$scope.house = false;
			}
			$scope.formData2.houseinfo = data.houseinfo;
			$scope.formData3.houseinfo = data.houseinfo;
			$scope.formData2.houserent = data.rentalExpenses;
			$scope.formData3.cash = data.cash;
			$scope.formData3.Taxable_Investments = data.taxableInvestments;
			$scope.formData3.Non_Taxable_Investments = data.nonTaxableInvestments;
			$scope.formData3.remainingMortgageInterestRate =  data.whatIsYourCurrentMortgageRate;
			$scope.formData3.realEstate  =  data.realEstate;
			$scope.formData3.houseAppreciationRate =  data.houseAppreciationRate;
			$scope.formData3.houseValue = data.houseValue;
			$scope.formData3.remainingMortgage = data.remainingMortgage;
			$scope.formData3.remainingYearsMortgage = data.remainingYearsMortgage;
			$scope.formData3.realestate = data.realEstate;
			$scope.iralimits();
			$scope.formData3.u401=data.user401;
			$scope.formData3.u529=data.user559;
			$scope.formData3.uIRA=data.userIra;
			$scope.formData3.uRothIra= data.userRothira;
			$scope.formData3.s401=data.spouse401;
			$scope.formData3.s529=data.spouse529;
			$scope.formData3.sIRA=data.spouseIra;
			$scope.formData3.sRothIra= data.spouseRothira;
		}, function(error) {
			$scope.message = "Fail";
			$scope.errorName = "";
			$scope.errorSuperhero = "";
		});
	};
	$scope.fetchUserDetails();
    
    /* end of fetch user Details */
    /* Read Cookie */
    function readCookie( name ) {
		var nameEQ = name + "=";
		var ca = document.cookie.split( ";" );
		for ( var i = 0; i < ca.length; i++ ) {
			var c = ca[i];
			while ( c.charAt( 0 ) == " " ) {
				c = c.substring( 1, c.length );
			}
			if ( c.indexOf( nameEQ ) == 0 ) {
				return c.substring( nameEQ.length, c.length );
				}
		}
		return null;
	}
    /* End of Read Cookie */
    
   
    
    /* Used to fetch cities of selected state */
    
    $scope.fetchCities = function(city) {
		$rootScope.sessionDelete.sessionID = readCookie( "AhTwxlO" );
		$scope.input = {};
		$scope.input.city = city;
		$scope.welcome.basic.country= "";
		$scope.input.actionType = "getCities"
		$http({
			method: "POST",
			url: "AutoComplete",
			data: $.param($scope.input),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}
		}).then( function( result ) {
			$scope.cities = result.data;
			$scope.cities.sort();
		}, function( error ) {
			console.log(error);
		});
	}
    /* End of fetch cities of selected state */
   
    $scope.kidsnumber = [];
	for ( i = 0; i < 11; i++ ) {
		var a = {
			number: i.toString()
		}
		$scope.kidsnumber.push( a );
	}
	$scope.kidsages = [];
	for ( i = 1; i < 26; i++ ) {
		var a = {
			id: i.toString()
		}
		$scope.kidsages.push( a );
	}

	$scope.student = [ {
		id: "No"
	}, {
		id: "Yes"
	} ];
	$scope.houses = [ {
		type: "Own"
	}, {
		type: "Rent"
	} ];
	$scope.limits = [ {
		id: "No"
	}, {
		id: "Yes"
	} ];
    $scope.addkids = function(count) {
		var count = parseInt(count);
		$scope.welcome.basic.kids = [];
		for ( i = 1; i <= count; i++ ) {
			$scope.welcome.basic.kids.push( {
				name: "",
				age: "",
				flag: ""
			} );
		}
	};
	$scope.Onload = function() {
		$scope.userdetails.form = "userDetails";
		$http({
			method : "POST",
			url : "UserProfile",
			data : $.param($scope.userdetails),
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded"
			}
		}).then(function(result) {
			$scope.filingStatus = result.data.filingStatus;
		}, function(error) {
			$scope.message = "Fail";
			$scope.errorName = "";
			$scope.errorSuperhero = "";
		});
	};
	
	$scope.autocompleteForCounty = function() {
		$scope.input={};
		$scope.input.city= $scope.welcome.basic.city;
		$scope.input.country= $scope.welcome.basic.country;
		$scope.input.actionType = "getCounty";
		$http({
			method: "POST",
			url: "AutoComplete",
			data: $.param( $scope.input),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}
		}).then( function( result ) {
			$scope.welcome.basic.county = result.data;

		}, function( error ) {
			console.log(error);
		} );
	}
	$scope.formData3={}
	$scope.check = function() {
		if ( $scope.formData2.houseinfo == "Own" ) {
			$scope.formData3.houseinfo = "Own";
			if ( $scope.formData3.remainingMortgage <= 0 || !$scope.formData3.remainingMortgage ) {
				$scope.formData3.remainingMortgage = 0;
			}
			$scope.house = true;
			$scope.formData3.houseValue = 0;
			$scope.formData3.remainingMortgage = 0;
			$scope.formData3.remainingYearsMortgage = 0;
			$scope.formData3.remainingMortgageInterestRate = 0.0;
            $scope.formData3.houseAppreciationRate = 0.0;
			$scope.formData3.realestate = 0;
		} else {
			$scope.house = false;
			$scope.formData3.houseinfo = "Rent";
		}
	};
	$scope.iralimits = function() {
		if ( $scope.formData3.Non_Taxable_Investments == "Yes" ) {
			$scope.limit = false;
			if ( $scope.welcome.basic.married == "Yes" ) {
				$scope.limitSpouse = false;
			} else {
				$scope.limitSpouse = true;
			}
		} else {
			$scope.limit = true;
			$scope.limitSpouse = true;
		}
	}
	$scope.funCheck = function() {
		$scope.dependantsErr = "";
		if ( $scope.welcome.basic.filingStatus == "Yes" ) {
			$scope.dependants = false;
			$scope.kidForSingle = true;
			$scope.dependantsCount = false;
			$scope.dependantsCount1 = true;
			$scope.welcome.basic.kidscount = 0;
			$scope.welcome.basic.dependants = 0;
			if ( $scope.welcome.basic.kidscount == 0 ) {
				$scope.welcome.basic.kids = null;
			}
		} else {
			$scope.kidForSingle = false;
			$scope.dependantsCount1 = false;
			$scope.dependants = false;
			$scope.welcome.basic.kidscount = 0;
			$scope.welcome.basic.dependants = 0;
			if ( $scope.welcome.basic.kidscount == 0 ) {
				$scope.welcome.basic.kids = null;
			}
		}
	}
	$scope.formData1={};
	
	$scope.addkidsLoad = function( $count, $childsOnLoad ) {
		var kids = [];
		for ( i = 0; i < $count; i++ ) {
			kids.push( {
				name: $childsOnLoad[i].name,
				age: $childsOnLoad[i].age,
				flag: $childsOnLoad[i].flag
			} );
		}
		return kids;
		//console.log($scope.welcome.basic.kids);
	};
	
	 /* $scope.$watch(Auth.isLoggedIn, function (value, oldValue) {
	    	console.log(value);
	    	console.log(oldValue);
	        if(!value) {
	          console.log("Disconnect");
	        }else{
	        	console.log('active');
	        	$location.path('/welcome/basic');
	        }
	      },true);
*/
	
	$scope.basicInfo = function(welcome){
		var input = {kids:[]};
		input.form =  "personalDetails";
		input.kidscount = welcome.basic.kidscount;
		input.kids = welcome.basic.kids;
		if ( welcome.basic.kidscount > 0 ) {
			for ( i = 0; i < welcome.basic.kidscount; i++ ) {
				if ( input.kids[i].flag == "" ) {
					input.kids[i].flag = "No";
				}
			}
		}
		input.uage 		= welcome.basic.age;
		input.city 		= welcome.basic.city;
		input.country 	= welcome.basic.country;
		input.county 	= welcome.basic.county;
		input.dependants = welcome.basic.dependants;
		input.married = welcome.basic.married;
		input.filingStatus = welcome.basic.filingStatus;
		if(welcome.basic.married == 'Yes'){
			input.sage = welcome.basic.spouse.sage;
			input.sname = welcome.basic.spouse.name;
		}
		$http( {
			method: "POST",
			url: "Register",
			data: $.param( input),
			headers: {
						"Content-Type": "application/x-www-form-urlencoded"
			}
		} ).then( function( result ) {
				console.log(result.data);
				if(result.data == "success"){
					$state.go('welcome.1');
		            toaster.pop('success', "Saved Successfully!!", "{template: 'toasterData.html', data: 'User details Saved'}", 5000, 'templateWithData');
				}
		}, function( error ) {
                toaster.pop('warning', "Error", "{template: 'toasterData.html', data: 'Problem in saving User Details'}", 5000, 'templateWithData');
               // Auth.setUser(false);
		} );
	}
	$scope.formData2 ={};
	$scope.change = function() {
		// alert(  "chnge===" )
	
		if ( $scope.welcome.basic.married == 'Yes' ) {
			$scope.marriedt = true;
			$scope.kidForSingle = true;
			$scope.hideStatus = false;
			$scope.dependantsCount1 = true;
			$scope.welcome.basic.kidscount = 0;
			$scope.welcome.basic.dependants = 0;
			if ( $scope.welcome.basic.kidscount == 0 ) {
				$scope.welcome.basic.kids = null;
			}
			if ( $scope.welcome.basic.filingStatus == "No"	|| $scope.welcome.basic.filingStatus == "" ) {

				$scope.welcome.basic.filingStatus = "Married Filing Jointly";
			}

		} else {
			$scope.marriedt = false;
			$scope.kidForSingle = false;
			$scope.hideStatus = true;
			$scope.dependantsCount1 = false;
			
			$scope.welcome.basic.sage = "";
			$scope.welcome.basic.kidscount = "";
			$scope.formData2.stax = "";
			$scope.welcome.basic.filingStatus = "";
			$scope.welcome.basic.kidscount = 0;
			$scope.welcome.basic.dependants = 0;
			if ( $scope.welcome.basic.kidscount == 0 ) {
				$scope.welcome.basic.kids = null;
			}

		}

	};
	
	$scope.doneTyping = function() {
		$scope.formData2.kidCostCalculated = 0;
		$scope.welcome.basic.form = "calculateKidCost";
		if ( $scope.formData2.tax == "" || $scope.formData2.tax == "NaN"
				|| $scope.formData2.tax == undefined ) {
			$scope.welcome.basic.tax = 0;
		}
		$scope.welcome.basic.userIncome = $scope.formData2.tax * 1;
		if ( $scope.welcome.basic.married == undefined ) {
			$scope.welcome.basic.married = "No";
		}
		if ( $scope.welcome.basic.married == "Yes" ) {
			if ( $scope.formData2.stax == "" || $scope.formData2.stax == "NaN"
					|| $scope.formData2.stax == undefined ) {
				$scope.welcome.basic.stax = 0;
			}
			if($scope.formData2.stax === undefined || $scope.formData2.stax == ""){
				$scope.welcome.basic.spouceIncome = 0;;
			}else{
				$scope.welcome.basic.spouceIncome = $scope.formData2.stax * 1;	
			}
		}

		$http( {
			method: "POST",
			url: "Register",
			data: $.param( $scope.welcome.basic ),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}
		} ).then( 
				function( result ) {
					$scope.formData2.expenses = Math.ceil( Math
							.ceil( result.data.cost ) / 12 );
					$scope.formData2.kidCostCalculated = Math.ceil( Math
							.ceil( result.data.cost ) / 12 );
					$scope.formData2.kidCostCalculatedArray = JSON
							.stringify( result.data.costArray );
					$scope.kidCostCalculated=$scope.formData2.kidCostCalculated;

				}, function( error ) {
	               // toaster.pop('warning', "Error", "{template: 'toasterData.html', data: 'Problem in calculating cost'}", 5000, 'templateWithData');
				} );
	}
	
	$scope.checkform3 = function() {
		$scope.masked = true;
		$scope.formData2.kidCostCalculated = $scope.kidCostCalculated;
		
		
		if($scope.welcome.basic.kidscount>0) {
			if($scope.formData2.expenses < $scope.kidCostCalculated*1.5 && ($scope.formData2.kidcostFactor==undefined||$scope.formData2.kidcostFactor==0||$scope.formData2.kidcostFactor==null)) {
				$scope.errmessage = "Your non - housing expenses are very low , please enter the amount you will spend for your kid";
				$scope.kidcostFactor=true;
				$scope.masked=false;
                toaster.pop('warning', "Housing expenses are very low !!", "{template: 'toasterData.html', data: 'please enter the amount you will spend for your kid'}", 5000, 'templateWithData');
			}
			else {
				if($scope.formData2.expenses >= $scope.kidCostCalculated*1.5 && ($scope.formData2.kidcostFactor==undefined||$scope.formData2.kidcostFactor==0||$scope.formData2.kidcostFactor==null)) {
					$scope.formData2.kidcostFactor=0;
				}
				
				if ( $scope.welcome.basic.married == "Yes"
					&& ( $scope.formData2.stax * 1 + $scope.formData2.tax * 1 < $scope.formData2.houserent
							* 1 + $scope.formData2.expenses * 1 ) ) {
					toaster.pop('warning', "Income is Low !!", "{template: 'toasterData.html', data: 'Your income cannot be less than ur expenses!!'}", 5000, 'templateWithData');
			} else if ( $scope.welcome.basic.married == "No"
					&& ( $scope.formData2.tax * 1 < $scope.formData2.houserent * 1
							+ $scope.formData2.expenses * 1 ) ) {
				toaster.pop('warning', "Income is Low !!", "{template: 'toasterData.html', data: 'Your income cannot be less than ur expenses !!'}", 5000, 'templateWithData');
			}

			else {
				$scope.masked = true;
				$scope.message = $scope.welcome.basic.kidscount;
				if ( $scope.welcome.basic.married == "Yes" ) {
					$scope.formData2.married = "Yes";
				}
				$http( {
					method: "POST",
					url: "Register",
					data: $.param( $scope.formData2 ),
					headers: {
						"Content-Type": "application/x-www-form-urlencoded"
					}
				} ).then( function( result ) {
					//$scope.changeItem($scope.screen+1);
					$state.go('welcome.2');
					toaster.pop('success', "Success","{template: 'toasterData.html', data: 'Income Expense details Saved'}",  5000, 'templateWithData');
				}, function( error ) {
					$scope.message = result.data;
	                toaster.pop('warning', "Error", "{template: 'toasterData.html', data: 'Problem in saving Income expense Details'}", 5000, 'templateWithData');

				} );
			}
			}
		}else {
		$scope.formData2.kidcostFactor=0;
		if ( $scope.welcome.basic.married == "Yes"
				&& ( $scope.formData2.stax * 1 + $scope.formData2.tax * 1 < $scope.formData2.houserent
						* 1 + $scope.formData2.expenses * 1 ) ) {
			$scope.masked = false;
			$scope.errmessage = "Your income cannot be less than ur expenses !!";
		} else if ( $scope.welcome.basic.married == "No"
				&& ( $scope.formData2.tax * 1 < $scope.formData2.houserent * 1
						+ $scope.formData2.expenses * 1 ) ) {
			$scope.masked = false;
		}

		else {
			$scope.masked = true;
			$scope.message = $scope.welcome.basic.kidscount;
			if ( $scope.welcome.basic.married == "Yes" ) {
				$scope.formData2.married = "Yes";
			}
			$http( {
				method: "POST",
				url: "Register",
				data: $.param( $scope.formData2 ),
				headers: {
					"Content-Type": "application/x-www-form-urlencoded"
				}
			} ).then( function( result ) {
				$state.go('welcome.2');
				$scope.message = result.data;
				console.log( "message" + $scope.message );
				$scope.masked = false;
			}, function( error ) {
				$scope.message = result.data;
			} );
		}
		}
	}
	
	$scope.checkform4 = function(){
		$scope.formData3.married = $scope.welcome.basic.married;
		$http({
					method: "POST",
					url: "Register",
					data: $
							.param( $scope.formData3 ),
					headers: {
						"Content-Type": "application/x-www-form-urlencoded"
					}
			})
			.then(function(result) {
				toaster.pop('success', "Success","{template: 'toasterData.html', data: 'Asset Details saved successfully'}" , 'templateWithData');
				$state.go('dashboard.home');
			},
			function(error) {
                toaster.pop('warning', "Error", "{template: 'toasterData.html', data: 'Problem in saving Asset Details'}", 5000, 'templateWithData');

			} );
	}
}
angular.module('wealthsetter').filter('propsFilter', function() {
	  return function(items, props) {
	    var out = [];
	    if (angular.isArray(items)) {
	      var keys = Object.keys(props);
	      items.forEach(function(item) {
	        var itemMatches = false;
	        for (var i = 0; i < keys.length; i++) {
	          var prop = keys[i];
	          var text = props[prop].toLowerCase();
	          if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
	            itemMatches = true;
	            break;
	          }
	        }

	        if (itemMatches) {
	          out.push(item);
	        }
	      });
	    } else {
	      // Let the output be the input untouched
	      out = items;
	    }

	    return out;
	  };
	});

