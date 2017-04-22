angular.module('wealthsetter').controller(
		'SsbCtrl',
		[ '$scope', '$rootScope', '$uibModal', '$log', '$http', '$state',
				'$timeout', 'toaster', SsbCtrl ]);
function SsbCtrl($scope, $rootScope, $uibModal, $log, $http, $state, $timeout,
		toaster, $uibModalInstance) {
	$.material.init();
	/*
	 * angular.element(document).ready(function () { //alert(); $scope.load();
	 * });
	 */
	$rootScope.boolChangeClass = true;
	$scope.$watch('loginLogoutText', function() {
		// console.log($rootScope.loginLogoutText);
	});

	$scope.box1 = false;
	$scope.alerts = [ {
		type : 'success',
		msg : 'Thanks for visiting!!'
	} ];
	$scope.errorMap = {
		required : "This field is mandatory",
		email : "Please enter a valid email"
	}
	$scope.addAlert = function() {
		$scope.alerts.push({
			msg : 'Another alert!'
		});
	};

	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	};
	$scope.showSideInput = true;

	$scope.user_id = "";
	$scope.messageChangeIncome = "";
	$scope.user_id;
	$scope.inputData = {};
	$scope.ages = [];
	$scope.preChangedValue;
	$scope.masked = false;
	$scope.maskedPlan = false;
	$scope.maskedPlotChart = true;
	$scope.preStartIndex;
	$scope.preEndIndex;
	$scope.checkDrag = 0;
	$scope.checkDrag1 = 0;
	$scope.preChangedValue1;
	$scope.preStartIndex1;
	$scope.Math = window.Math;
	$scope.preEndIndex1;
	$scope.profileNameCheckbox = "";
	$scope.checkboxData = {};
	$scope.checkboxData.applyThisYear = true;
	$scope.checkboxData.applyGraduallyYear = false;
	$scope.checkboxData.applyFutureYear = false;
	$scope.checkboxData.applyPlan = false;
	$scope.nospouse = false;
	$scope.saveFlag = 0;
	$scope.formdata = {};
	$scope.IncomeDetails = {};
	$scope.count = 0;
	$scope.marriageYear = "";
	$scope.selected = 0;
	$scope.startAge = 70;
	$scope.finplan_name = "";
	$scope.goal_id = "";
	$scope.selectedGoalname = "";
	$scope.position = "";
	$scope.income = {};
	$scope.parseDate = d3.time.format("%Y").parse;
	$scope.chartYear = [];
	$scope.chartIncome = [];
	$scope.chartIncomeOld = [];
	$scope.chartExpense = [];
	$scope.chartYearSave = [];
	$scope.chartIncomeSave = [];
	$scope.totalExpense = [];
	$scope.expense = [];
	$scope.IncomeDetails.income = {};
	$scope.chartExpense = [];
	$scope.ExpenseIncome = [];
	$scope.chartYearExpense = [];
	$scope.spouseIncome = [];
	$scope.spouseIncomeSave = [];
	$scope.chartIncomeSpouse = [];
	$scope.chartIncomeSpouseOld = [];
	$scope.tableIncome = [];
	$scope.tableIncome_imp = [];
	$scope.maritalFlag_imp = false;
	$scope.chartCombinedIncome = [];
	$scope.Count = 0;
	$scope.chartAssets = [];
	$scope.chartGoals = [];
	$scope.assetGoals = [];
	$scope.dragPoint1 = [];
	$scope.dragPoint = [];
	$scope.planNames = [];
	$scope.planNames1 = [];
	$scope.goals = [];
	$scope.goals1 = [];
	$scope.goalsid = [];
	$scope.sessionDetails = {};
	$scope.sessionDelete = {};
	$scope.previousdtIndex = "";
	$scope.FinPlanName = "";
	$scope.nextdtIndex = "";
	$scope.CreatePlanbutton = true;
	$scope.formdata.checkValue = false;
	$scope.hideList = true;
	$scope.CopyPlanbutton = false;
	$scope.show = 0;
	$scope.firstQuestion = "6";
	$scope.secondQuestion = "6";
	$scope.myVar = "";
	$scope.thirdQuestion = "6";
	$scope.fourthQuestion = "1";
	$scope.defaultretirementAge = 70;
	$scope.spousedefaultretirementAge = 70;
	$scope.spouseRetirementPoint;
	$scope.retirementPoint;
	$scope.spousebirthYear;
	$scope.birthYear;
	$scope.formData = {};
	$scope.afterSubmit = true;
	$scope.beforeSubmit = false;
	$scope.nospouse = false;
	$scope.growthChart = false;
	$scope.growthTable = true;
	$scope.Porfolio_tableIncome = [];
	$scope.Porfolio_planDetailsAsset = [];
	$scope.Porfolio_planDetailsTax = [];
	$scope.Porfolio_totalExpense = [];
	$scope.Porfolio_userIncome = [];
	$scope.Portfolio_chartYear = [];
	$scope.investmentPortFolio = "No";
	$scope.riskScore = 0;
	$scope.Portfolio_chartAssets = [];
	$scope.tax_incomeProfile = [];
	$scope.asset_incomeProfile = [];
	$scope.finPlanDetails = false;
	$scope.incomeProfileDetails = true;
	$scope.retirementAge = [ "62", "63", "64", "65", "66", "67", "68", "69",
			"70" ];
	$scope.userSSB = false;
	$scope.incomeProfile = [];

	$scope.userBirth = "";
	$scope.spouseBirth = "";
	$scope.maritalStatus = "";
	$scope.userAge = "";
	$scope.spouseAge = "";
	$scope.radio = "";
	$scope.spouse68 = false;
	$scope.showIncomeProfileRadio = true;
	$scope.userFlag = true;
	$scope.defaultUserRetAge = "";
	$scope.defaultSpouseRetAge = "";
	$scope.defaultUserRetAgeTemp = "70";
	$scope.defaultSpouseRetAgeTemp = "70";
	$scope.defaultUserRetAgeTemp2 = "";
	$scope.defaultSpouseRetAgeTemp2 = "";
	$scope.showThis = false;
	// $scope.showSpouseBirth = false;
	$scope.formdata.spouseret_ageTemp = "";
	$scope.formdata.ret_ageTemp = "";
	$scope.showThis2 = false;
	$scope.goalMarried = "No";

	$scope.showUser62 = false;
	$scope.showUser66 = false;
	$scope.showUser70 = false;
	$scope.userNegative = false;
	$scope.userNegativeShow = [];
	$scope.showSpouse62 = false;
	$scope.showSpouse66 = false;
	$scope.showSpouse70 = false;
	$scope.spouseNegative = false;
	$scope.spouseNegativeShow = [];
	$scope.userSSBArr = [];
	$scope.spouseSSBArr = [];
	$scope.showChart = false;
	$scope.incomeProfileObjects = [];
	$scope.showLabelUser = false;
	$scope.showLabelSpouse = false;
	$scope.selectedFromIncome = false;
	$scope.dataSSB = [];
	$scope.optionsSSB = {};
	
	$scope.deleteAllCookies = function() {
		$scope.sessionDelete.sessionID = readCookie("AhTwxlO");
		$http({
			method : "POST",
			url : "Logout",
			data : $.param($scope.sessionDelete),
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded"
			}
		}).then(function(result) {

			window.location.href = "index.jsp";

		}, function(error) {

		});
	};

	function readCookie(name) {
		var nameEQ = name + "=";
		var ca = document.cookie.split(";");
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == " ") {
				c = c.substring(1, c.length);
			}
			if (c.indexOf(nameEQ) == 0) {
				return c.substring(nameEQ.length, c.length);
			}
		}
		return null;
	}

	$scope.load = function() {
		$scope.sessionDetails.cookieId = readCookie("AhTwxlO");
		$scope.sessionDetails.lastVisitedPage = document.URL.replace("#", "");
		if ($scope.sessionDetails.cookieId != null) {
			$http({
				method : "POST",
				url : "CheckSession",
				data : $.param($scope.sessionDetails),
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded"
				}
			}).then(
					function(result) {
						if (result.data.status == "success") {
							$scope.user_id = result.data.user_id;
							if (result.data.lastVisitedPage == "undefined") {
								$state.go('dashboard.home');
								// window.location.href = "dashboardUserr0.jsp";
							} else {
								document.cookie = "lastVisitedPage="
										+ result.data.lastVisitedPage;
							}
							$scope.formdata.spousework = "No";
							$rootScope.userFlag = true;
							$rootScope.homeLocation = "dashboard.home";
							$scope.showMessageReg = false;
							$scope.load1($scope.user_id);
						} else {
							$scope.errMessage = "Session got expired";
							$rootScope.userFlag = false;
							$rootScope.homeLocation = "index";
						}
						$scope.loading = false;
						$scope.masked = false;
					}, function(error) {
					});

		} else {
			$scope.selectedFromIncome = true;
			$rootScope.userFlag = false;
			$rootScope.homeLocation = "index";
			$scope.showMessageReg = true;
			$scope.showIncomeProfileRadio = false;
			$scope.formdata.income = "HideIncome";
			$scope.showUserBirth = false;
			$scope.showSpouseBirth = false;
			$scope.formdata.ret_age = $scope.defaultUserRetAge;
			$scope.formdata.spouseret_age = $scope.defaultSpouseRetAge;
		}
		/*
		 * if ($scope.userFlag == true) { $scope.showIncomeProfileRadio = true; }
		 * else { $scope.showIncomeProfileRadio = false; $scope.formdata.income =
		 * "HideIncome"; $scope.showUserBirth = false; $scope.showSpouseBirth =
		 * false; $scope.formdata.ret_age = $scope.defaultUserRetAge;
		 * $scope.formdata.spouseret_age = $scope.defaultSpouseRetAge; }
		 */
		// alert($scope.showIncomeProfileRadio);
	};

	$scope.load1 = function(user_id) {
		$scope.inputData.action = "fetchIncomeProfiles";
		$scope.inputData.user_id = user_id;
		$http({
			method : "POST",
			url : "SSBCalculator",
			data : $.param($scope.inputData),
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded"
			}
		}).then(function(result) {
			console.log(result.data);
			$scope.userBirth = result.data.userBirthYear;
			$scope.spouseBirth = result.data.spouseBirthYear;
			$scope.maritalStatus = result.data.userMaritalStatus;
			$scope.userAge = result.data.userAge;
			$scope.spouseAge = result.data.spouseAge;
			if (result.data.goalMarried != undefined) {
				$scope.goalMarried = result.data.goalMarried;
			}
			$scope.incomeProfileTemp = [];
			$scope.incomeProfileObjects = result.data.incomeProfileObjects;
			for (var i = 0; i < result.data.incomeProfile.length; i++) {
				$scope.incomeProfileTemp[i] = result.data.incomeProfile[i];
			}

			$.each($scope.incomeProfileTemp, function(i, el) {
				if ($.inArray(el, $scope.incomeProfile) === -1) {
					$scope.incomeProfile.push(el);
				}
			});
			// alert(JSON.stringify($scope.incomeProfile));
			if ($scope.goalMarried == "Yes") {
				$scope.defaultSpouseRetAgeTemp2 = result.data.spouseRetAge;
			}
			if (result.data.userMaritalStatus == "Yes") {
				$scope.formdata.spousework = "Yes";
				$scope.showThis2 = true;
				if (result.data.spouseRetAge != undefined) {
					$scope.defaultSpouseRetAgeTemp2 = result.data.spouseRetAge;
				}
			} else {
				$scope.formdata.spouseret_age = "70";
			}
			if (result.data.userRetAge != undefined) {
				$scope.defaultUserRetAgeTemp2 = result.data.userRetAge;
			}

		}, function(error) {
		});
	};

	/* change event for show income profiles */
	$scope.RadioChangeIncome = function(radio) {
		$scope.userSSB = false;
		$scope.spouseSSB = false;
		$scope.spouseSSB2 = false;
		$scope.spouseSSB3 = false;
		$scope.spouseSSB4 = false;
		$scope.userNegative = false;
		$scope.spouseNegative = false;
		$scope.showLabelUser = false;
		$scope.showChart = false;
		$scope.showLabelSpouse = false;
		$scope.formdata.spouseaime = "";
		$scope.formdata.spousedob = "";
		$scope.formdata.spouseret_age = "";
		$scope.errormsg = "";
		$scope.formdata.useraime = "";
		$scope.formdata.dob = "";
		$scope.radio = radio;
		if ($scope.radio == "No") {
			$scope.selectedFromIncome = true;
			$scope.showThis3 = false;
			$scope.formdata.spousework = "No";
			$scope.formdata.annaul_income = "";
			$scope.formdata.spouseret_age = "";
			$scope.formdata.ret_age = "";
			// $scope.formdata.ret_age =
			// $scope.defaultUserRetAge;
			$scope.showUserBirth = false;
			$scope.showSpouseBirth = false;
			$scope.formdata.dob = "";
			$scope.formdata.income_prof = "";
			// $scope.formdata.ret_age = "";
			// $scope.formdata.spouseret_age = "";
			if ($scope.maritalStatus == "Yes") {
				$scope.formdata.martial = "Married";
				$scope.formdata.spousedob = "";
				$scope.showThis2 = false;
				$scope.formdata.spousework = "No";
				$scope.showThis3 = true;
				$scope.showThis4 = false;
			} else {
				$scope.formdata.martial = "Single";
				$scope.formdata.spousedob = "";
				// $scope.formdata.spouseret_age =
				// $scope.defaultSpouseRetAge;
				$scope.showThis = false;
				$scope.showThis1 = false;
				$scope.showThis2 = false;
				$scope.showThis4 = false;
			}
		} else {
			$scope.selectedFromIncome = false;
			$scope.showThis3 = false;

			/*
			 * if( $scope.goalMarried == "Yes" ) { $scope.showThis3 = true; }
			 */
			$scope.formdata.spouseret_age = "";
			$scope.formdata.ret_age = "";
			$scope.showUserBirth = true;
			$scope.formdata.dob = "";
			$scope.formdata.spousedob = "";
			if ($scope.maritalStatus == "Yes"
			/*
			 * || $scope.formdata.martial == "Married"
			 */
			) {
				$scope.formdata.martial = "Married";
				$scope.showSpouseBirth = true;
				$scope.showThis2 = true;
				$scope.showThis = false;
				$scope.showThis4 = true;
				// $scope.formdata.income == 'HideIncome';
			} else {
				$scope.formdata.martial = "Single";
				$scope.showSpouseBirth = false;
				$scope.formdata.spousedob = "";
				$scope.showThis = false;
			}
		}
	};

	/* change Income */
	$scope.changeIncome = function() {
		$scope.userSSB = false;
		$scope.spouseSSB = false;
		$scope.spouseSSB2 = false;
		$scope.spouseSSB3 = false;
		$scope.spouseSSB4 = false;
		$scope.userNegative = false;
		$scope.spouseNegative = false;
		$scope.showChart = false;

		if ($scope.maritalStatus == "Yes") {
			$scope.showLabelSpouse = false;
			if ($scope.defaultSpouseRetAgeTemp2.length == 0
					|| $scope.defaultSpouseRetAgeTemp2 == ""
					|| $scope.defaultSpouseRetAgeTemp2 == null
					|| $scope.defaultSpouseRetAgeTemp2 == undefined) {
				todayDate = new Date();
				todayYear = todayDate.getFullYear();
				var spouseTempAge = todayYear - Number($scope.spouseBirth);
				if (todayYear - spouseTempAge >= 1943
						&& todayYear - spouseTempAge <= 1959) {
					$scope.formdata.spouseret_age = "66";
				} else {
					$scope.formdata.spouseret_age = "67";
				}
			} else {
				$scope.formdata.spouseret_age = $scope.defaultSpouseRetAgeTemp2;
			}

			$scope.showThis4 = true;
		} else {
			$scope.formdata.spouseret_age = "";
		}
		if ($scope.goalMarried == "Yes") {
			$scope.showLabelSpouse = false;
			$scope.prof_nameIndex = $scope.incomeProfileObjects.map(
					function(obj) {
						return obj.prof_name;
					}).indexOf($scope.formdata.income_prof);
			$scope.prof_nameValue = 0;
			if ($scope.prof_nameIndex > -1) {
				$scope.prof_nameValue = $scope.incomeProfileObjects[$scope.prof_nameIndex].spouseIncomeLength;
			}
			if ($scope.prof_nameValue > 0) {
				$scope.formdata.martial = "Married";
				$scope.spousedob = $scope.spouseBirth;
				$scope.showSpouseBirth = true;
				$scope.showThis4 = true;
				$scope.showThis2 = true;
				$scope.formdata.spousework = "Yes";
				$scope.showThis3 = false;
				$scope.showThis = false;
				$scope.formdata.spouseret_age = $scope.defaultSpouseRetAgeTemp2;
			} else {
				$scope.formdata.spousework = "No";
				$scope.formdata.martial = "Single";
				$scope.showSpouseBirth = false;
				$scope.showThis2 = false;
				$scope.showThis3 = false;
				$scope.showThis4 = false;
				$scope.formdata.spousedob = "";
				$scope.formdata.spouseret_age = "";
			}
		}

		if ($scope.radio == "HideIncome") {
			$scope.formdata.dob = "";
			$scope.formdata.spousedob = "";
			$scope.formdata.spousework = "No";
			$scope.formdata.spouseret_age = "";
			$scope.showThis2 = false;
		} else {
			$scope.formdata.spousework = "Yes";
			$scope.formdata.dob = $scope.userBirth;
			$scope.formdata.spousedob = $scope.spouseBirth;
			if ($scope.defaultUserRetAgeTemp2.length == 0) {
				todayDate = new Date();
				todayYear = todayDate.getFullYear();
				var userTempAge = todayYear - Number($scope.userBirth);
				if (todayYear - userTempAge >= 1943
						&& todayYear - userTempAge <= 1959) {
					$scope.formdata.ret_age = "66";
				} else {
					$scope.formdata.ret_age = "67";
				}
			} else {
				$scope.formdata.ret_age = $scope.defaultUserRetAgeTemp2;
			}
		}
	};

	/* show user aime */
	$scope.RadioChangeUserAIME = function(uaime) {
		$scope.formdata.annaul_income = "";
		$scope.showLabelUser = true;
		if (uaime == "Yes") {
			$scope.formdata.useraime = "Yes";
			$scope.userLabel = "AIME";
		} else {
			$scope.formdata.useraime = "No";
			$scope.userLabel = "Current monthly income";
		}
	};

	/* marital status change */
	$scope.RadioChangeMarital = function(radio2) {
		$scope.userSSB = false;
		$scope.spouseSSB = false;
		$scope.spouseSSB2 = false;
		$scope.spouseSSB3 = false;
		$scope.spouseSSB4 = false;
		$scope.userNegative = false;
		$scope.spouseNegative = false;
		$scope.showChart = false;

		$scope.radio2 = radio2;
		if ($scope.formdata.income == "HideIncome") {
			$scope.showSpouseBirth = false;
			$scope.formdata.income_prof = "";
			if ($scope.radio2 == "Married") {
				$scope.showThis3 = true;
				$scope.formdata.martial = "Married";
				$scope.formdata.spousedob = "";
				$scope.formdata.spousework = "No";
			} else {
				$scope.showThis3 = false;
				$scope.formdata.martial = "Single";
				$scope.formdata.spousedob = "";
				$scope.formdata.spouseret_age = $scope.defaultSpouseRetAge;
				$scope.showThis = false;
				$scope.showLabelSpouse = false;
				$scope.formdata.spouseannaul_income = "";
			}
		} else {
			$scope.showUserBirth = true;
			$scope.showLabelSpouse = false;
			$scope.formdata.spouseannaul_income = "";
			$scope.formdata.spouseaime = "";
			if ($scope.radio2 == "Married") {
				$scope.formdata.martial = "Married";
				$scope.showThis4 = true;
				if ($scope.maritalStatus == "Yes"
						|| $scope.goalMarried == "Yes") {
					if ($scope.goalMarried == "Yes") {
						$scope.prof_nameIndex = $scope.incomeProfileObjects
								.map(function(obj) {
									return obj.prof_name;
								}).indexOf($scope.formdata.income_prof);
						$scope.prof_nameValue = 0;
						if ($scope.prof_nameIndex > -1) {
							$scope.prof_nameValue = $scope.incomeProfileObjects[$scope.prof_nameIndex].spouseIncomeLength;
						}
						if ($scope.prof_nameValue > 0) {
							$scope.showThis3 = false;
							$scope.showSpouseBirth = true;
							$scope.formdata.spouseret_age = $scope.defaultSpouseRetAgeTemp2;
							$scope.formdata.spousedob = $scope.spouseBirth;
							$scope.formdata.spousework = "Yes";
							$scope.showThis = false;
							$scope.showThis1 = false;
							$scope.showThis2 = true;
						} else {
							$scope.showThis3 = false;
							$scope.showThis4 = false;
							$scope.showSpouseBirth = false;
							$scope.formdata.spouseret_age = "";
							$scope.formdata.spousedob = "";
							$scope.formdata.spousework = "No";
							$scope.showThis = false;
							$scope.showThis1 = false;
							$scope.showThis2 = false;
							$scope.showThis3 = true;
						}
					} else {
						$scope.showThis3 = false;
						$scope.showSpouseBirth = true;
						$scope.formdata.spouseret_age = $scope.defaultSpouseRetAgeTemp2;
						$scope.formdata.spousedob = $scope.spouseBirth;
						$scope.formdata.spousework = "Yes";
						$scope.showThis = false;
						$scope.showThis1 = false;
						$scope.showThis2 = true;
					}

				} else {
					$scope.showThis3 = true;
					$scope.formdata.spousework = "Yes";
					$scope.showSpouseBirth = false;
					$scope.formdata.income == "HideIncome";
					$scope.showThis = true;
					$scope.showThis1 = true;
					$scope.showThis2 = true;
				}

			} else {
				$scope.showThis3 = false;
				$scope.formdata.martial = "Single";
				$scope.showSpouseBirth = false;
				$scope.formdata.spousedob = "";
				$scope.showThis = false;
				$scope.showThis1 = false;
				$scope.showThis2 = false;
				$scope.showThis4 = false;

			}
		}
	};

	/* spouse working */
	$scope.RadioChange1 = function(radio1) {
		// alert(radio1);
		$scope.userSSB = false;
		$scope.spouseSSB = false;
		$scope.spouseSSB2 = false;
		$scope.spouseSSB3 = false;
		$scope.spouseSSB4 = false;
		$scope.userNegative = false;
		$scope.spouseNegative = false;
		$scope.showChart = false;

		$scope.radio1 = radio1;
		if ($scope.radio1 == "No") {
			$scope.showThis4 = false;
			$scope.showThis2 = false;
			$scope.showThis = false;
			$scope.formdata.spouseannaul_income = "";
			$scope.formdata.spousework == "No";
			$scope.showLabelSpouse = false;
			$scope.formdata.spousedob = "";
			$scope.formdata.spouseret_age = "";
		} else {
			$scope.showThis = true;
			$scope.showLabelSpouse = false;
			$scope.formdata.spouseaime = "";
			$scope.formdata.spousedob = "";
			$scope.formdata.spouseret_age = "";
		}

	};

	/* spouse aime */
	$scope.RadioChangeSpouseAIME = function(saime) {
		$scope.formdata.spouseannaul_income = "";
		$scope.showLabelSpouse = true;
		if (saime == "Yes") {
			$scope.formdata.spouseaime = "Yes";
			$scope.spouseLabel = "AIME";
		} else {
			$scope.formdata.spouseaime = "No";
			$scope.spouseLabel = "Current monthly income";
		}
	};

	$scope.userDOBChange = function() {
		$scope.errormsg = "";
		$scope.userSSB = false;
		$scope.spouseSSB = false;
		$scope.spouseSSB2 = false;
		$scope.spouseSSB3 = false;
		$scope.spouseSSB4 = false;
		$scope.userNegative = false;
		$scope.spouseNegative = false;
		$scope.showChart = false;
		if ($scope.formdata.dob.length == 4) {
			var userdob = $scope.formdata.dob;
			todayDate = new Date();
			todayYear = todayDate.getFullYear();
			var tempUserAge = todayYear - parseInt(userdob);
			if (todayYear - tempUserAge >= 1943
					&& todayYear - tempUserAge <= 1959) {
				$scope.formdata.ret_age = "66";
			} else {
				$scope.formdata.ret_age = "67";
			}
		} else {
			$scope.formdata.ret_age = "";
		}
	};

	$scope.spouseDOBChange = function() {
		$scope.errormsg = "";
		$scope.userSSB = false;
		$scope.spouseSSB = false;
		$scope.spouseSSB2 = false;
		$scope.spouseSSB3 = false;
		$scope.spouseSSB4 = false;
		$scope.userNegative = false;
		$scope.spouseNegative = false;
		$scope.showChart = false;
		if ($scope.formdata.spousedob.length == 4) {
			var spousedob = $scope.formdata.spousedob;
			todayDate = new Date();
			todayYear = todayDate.getFullYear();
			var tempSpouseAge = todayYear - parseInt(spousedob);
			if (todayYear - tempSpouseAge >= 1943
					&& todayYear - tempSpouseAge <= 1959) {
				$scope.formdata.spouseret_age = "66";
			} else {
				$scope.formdata.spouseret_age = "67";
			}
		} else {
			$scope.formdata.spouseret_age = "";
		}

	};

	$scope.hideMessages = function() {
		$scope.userSSB = false;
		$scope.spouseSSB = false;
		$scope.spouseSSB2 = false;
		$scope.spouseSSB3 = false;
		$scope.spouseSSB4 = false;
		$scope.userNegative = false;
		$scope.spouseNegative = false;
		$scope.showChart = false;
		$scope.errormsg = "";
	};

	$scope.ssb = function(formdata) {
		// toaster.clear();
		$scope.dataSSB = [];
		$scope.optionsSSB = {};
		$scope.inputData.goalMarried = $scope.goalMarried;
		$scope.userSSB = false;
		$scope.spouseSSB = false;
		$scope.spouseSSB2 = false;
		$scope.spouseSSB3 = false;
		$scope.spouseSSB4 = false;
		$scope.spouseSSB5 = false;
		$scope.userNegative = false;
		$scope.spouseNegative = false;
		$scope.showChart = false;
		$scope.inputData.spouse_birth_year = "";
		if ($scope.formdata.income == "ShowIncome") {
			if (formdata.income_prof == undefined || formdata.income_prof == "") {
				$scope.errormsg = "Please choose income pofile";
				// toaster.pop('error', "", "{template:
				// 'myTemplateWithData.html', data: '" + $scope.errormsg + "'
				// }", 3000, 'templateWithData');
			} else if ($scope.formdata.dob == ""
					|| $scope.formdata.dob == undefined) {
				$scope.errormsg = "Please enter user birth year";
				// toaster.error({title: "", body: $scope.errormsg});
				// toaster.pop('error', "", "{template:
				// 'myTemplateWithData.html', data: '" + $scope.errormsg + "'
				// }", 3000, 'templateWithData');
			} else if ($scope.formdata.ret_age == undefined
					|| $scope.formdata.ret_age == "") {
				$scope.errormsg = "Please enter user retirement age";
				// toaster.error({title: "", body: $scope.errormsg});
				// toaster.pop('error', "", "{template:
				// 'myTemplateWithData.html', data: '" + $scope.errormsg + "'
				// }", 3000, 'templateWithData');
			} else if ($scope.formdata.martial == undefined
					|| $scope.formdata.martial == "") {
				$scope.errormsg = "Please choose marital status";
				// toaster.error({title: "", body: $scope.errormsg});
				// toaster.pop('error', "", "{template:
				// 'myTemplateWithData.html', data: '" + $scope.errormsg + "'
				// }", 3000, 'templateWithData');
			} else {
				$scope.inputData.action = "calculateFromProfile";
				$scope.errormsg = "";
				if ($scope.showUserBirth == false) {

					var userdob = $scope.formdata.dob;

					todayDate = new Date();
					todayYear = todayDate.getFullYear();
					todayMonth = todayDate.getMonth();
					todayDay = todayDate.getDate();
					age = todayYear - parseInt(userdob);

					$scope.inputData.user_age = age;
					$scope.inputData.birth_year = userdob;
					$scope.Userage = age;
				} else {
					$scope.inputData.user_age = $scope.userAge;
					$scope.inputData.birth_year = $scope.userBirth;
					$scope.Userage = $scope.userAge;
				}

				$scope.inputData.prof_name = $scope.formdata.income_prof;
				$scope.inputData.userRetirementAge = formdata.ret_age;
				$scope.inputData.martialStatus = "Single";

				if ($scope.formdata.martial == "Single") {
					todayDate = new Date();
					todayYear = todayDate.getFullYear();

					var minYear = todayYear - 69;

					if (Number($scope.inputData.birth_year) < minYear) {
						$scope.errormsg = "User birth year cannot be less than "
								+ minYear;
						// toaster.error({title: "", body: $scope.errormsg});
						// toaster.pop('error', "", "{template:
						// 'myTemplateWithData.html', data: '" + $scope.errormsg
						// + "' }", 3000, 'templateWithData');
					} else {
						$scope.calculate(formdata);
					}
				} else {
					if ($scope.formdata.martial == "Married") {
						$scope.inputData.spouseAime = "";
						$scope.prof_nameIndex = $scope.incomeProfileObjects
								.map(function(obj) {
									return obj.prof_name;
								}).indexOf($scope.formdata.income_prof);
						$scope.prof_nameValue = 0;
						if ($scope.prof_nameIndex > -1) {
							$scope.prof_nameValue = $scope.incomeProfileObjects[$scope.prof_nameIndex].spouseIncomeLength;
						}

						$scope.inputData.spouse_birth_year = "";
						$scope.inputData.martialStatus = "Married";
						if ($scope.formdata.spousework == "Yes"
								&& $scope.formdata.spousedob == ""
								|| $scope.formdata.spousedob == undefined) {
							$scope.errormsg = "Please enter spouse birth year";
							// toaster.error({title: "", body:
							// $scope.errormsg});
						} else if (!($scope.prof_nameValue > 0)
								&& $scope.goalMarried == "Yes"
								&& $scope.formdata.spousework == "Yes"
								&& ($scope.formdata.spouseannaul_income == undefined || $scope.formdata.spouseannaul_income == "")
								&& ($scope.formdata.spouseaime == undefined || $scope.formdata.spouseaime == "")) {
							$scope.errormsg = "Please choose whether you know your spouse AIME or not";
							// toaster.error({title: "", body:
							// $scope.errormsg});
							// toaster.pop('error', "", "{template:
							// 'myTemplateWithData.html', data: '" +
							// $scope.errormsg + "' }", 3000,
							// 'templateWithData');
						} else if (!($scope.prof_nameValue > 0)
								&& $scope.goalMarried == "Yes"
								&& $scope.formdata.spousework == "Yes"
								&& ($scope.formdata.spouseannaul_income == undefined || $scope.formdata.spouseannaul_income == "")
								&& ($scope.formdata.spouseannaul_income == undefined || $scope.formdata.spouseannaul_income == "")) {
							if ($scope.formdata.spouseaime == "Yes") {
								$scope.errormsg = "Please enter spouse AIME";
								// toaster.error({title: "", body:
								// $scope.errormsg});
								// toaster.pop('error', "", "{template:
								// 'myTemplateWithData.html', data: '" +
								// $scope.errormsg + "' }", 3000,
								// 'templateWithData');
							} else {
								$scope.errormsg = "Please enter spouse current monthly income";
								// toaster.error({title: "", body:
								// $scope.errormsg});
								// toaster.pop('error', "", "{template:
								// 'myTemplateWithData.html', data: '" +
								// $scope.errormsg + "' }", 3000,
								// 'templateWithData');
							}
						} else if ($scope.formdata.spousework == "Yes"
								&& ($scope.formdata.spouseret_age == undefined || $scope.formdata.spouseret_age == "")) {
							$scope.errormsg = "Please enter spouse retirement age";
							// toaster.error({title: "", body:
							// $scope.errormsg});
							// toaster.pop('error', "", "{template:
							// 'myTemplateWithData.html', data: '" +
							// $scope.errormsg + "' }", 3000,
							// 'templateWithData');
						} else {
							if ($scope.showSpouseBirth == false) {

								var spousedob = $scope.formdata.spousedob;
								todayDate = new Date();
								todayYear = todayDate.getFullYear();
								todayMonth = todayDate.getMonth();
								todayDay = todayDate.getDate();
								spouseage = todayYear - parseInt(spousedob);

								$scope.inputData.spouse_birth_year = spousedob;
								$scope.inputData.spouse_age = spouseage;
								$scope.Spouseage = spouseage;
								$scope.inputData.spouseAnnaul_income = $scope.formdata.spouseannaul_income;
							} else {
								$scope.inputData.spouse_age = $scope.spouseAge;
								$scope.inputData.spouse_birth_year = $scope.spouseBirth;
								$scope.Spouseage = $scope.spouseAge;
							}
							if ($scope.formdata.spousework == "Yes") {
								$scope.inputData.spousework = "Yes";
								$scope.inputData.spouseRetirementAge = formdata.spouseret_age;

								$scope.inputData.spouseAnnaul_income = $scope.formdata.spouseannaul_income;
								$scope.inputData.spouseAime = $scope.formdata.spouseaime;

								todayDate = new Date();
								todayYear = todayDate.getFullYear();

								var minYear = todayYear - 69;

								if (Number($scope.inputData.birth_year) < minYear) {
									$scope.errormsg = "User birth year cannot be less than "
											+ minYear;
									// toaster.error({title: "", body:
									// $scope.errormsg});
									// toaster.pop('error', "", "{template:
									// 'myTemplateWithData.html', data: '" +
									// $scope.errormsg + "' }", 3000,
									// 'templateWithData');
								} else if (Number($scope.inputData.spouse_birth_year) < minYear) {
									$scope.errormsg = "Spouse birth year cannot be less than "
											+ minYear;
									// toaster.error({title: "", body:
									// $scope.errormsg});
									// toaster.pop('error', "", "{template:
									// 'myTemplateWithData.html', data: '" +
									// $scope.errormsg + "' }", 3000,
									// 'templateWithData');
								} else {
									$scope.calculate(formdata);
								}
							} else {
								$scope.inputData.spousework = "No";
								$scope.inputData.spouseAnnaul_income = 0;
								$scope.inputData.spouseRetirementAge = "62";
								$scope.inputData.spouse_birth_year = "";
								$scope.inputData.spouse_age = "";
								todayDate = new Date();
								todayYear = todayDate.getFullYear();

								var minYear = todayYear - 69;

								if (Number($scope.inputData.birth_year) < minYear) {
									$scope.errormsg = "User birth year cannot be less than "
											+ minYear;
									// toaster.error({title: "", body:
									// $scope.errormsg});
									// toaster.pop('error', "", "{template:
									// 'myTemplateWithData.html', data: '" +
									// $scope.errormsg + "' }", 3000,
									// 'templateWithData');
								} else {
									$scope.calculate(formdata);
								}
							}
						}

					}
				}

			}
		} else {
			var tempFlag = false;

			if ($scope.userFlag) {
				if ($scope.formdata.income == undefined
						|| $scope.formdata.income == "") {
					$scope.errormsg = "Please select Yes/No for show income profiles";
					// toaster.error({title: "", body: $scope.errormsg});
					// toaster.pop('error', "", "{template:
					// 'myTemplateWithData.html', data: '" + $scope.errormsg +
					// "' }", 3000, 'templateWithData');
				} else if ($scope.formdata.dob == ""
						|| $scope.formdata.dob == undefined) {
					$scope.errormsg = "Please enter user birth year";
					// toaster.error({title: "", body: $scope.errormsg});
					// toaster.pop('error', "", "{template:
					// 'myTemplateWithData.html', data: '" + $scope.errormsg +
					// "' }", 3000, 'templateWithData');
				} else if ($scope.formdata.useraime == undefined
						|| $scope.formdata.useraime == "") {
					$scope.errormsg = "Please choose whether you know your AIME or not";
					// toaster.error({title: "", body: $scope.errormsg});
					// toaster.pop('error', "", "{template:
					// 'myTemplateWithData.html', data: '" + $scope.errormsg +
					// "' }", 3000, 'templateWithData');
				} else if ($scope.formdata.annaul_income == undefined
						|| $scope.formdata.annaul_income == "") {
					if ($scope.formdata.useraime == "Yes") {
						$scope.errormsg = "Please enter user AIME";
						// toaster.error({title: "", body: $scope.errormsg});
						// toaster.pop('error', "", "{template:
						// 'myTemplateWithData.html', data: '" + $scope.errormsg
						// + "' }", 3000, 'templateWithData');
					} else {
						$scope.errormsg = "Please enter user current monthly income";
						// toaster.error({title: "", body: $scope.errormsg});
						// toaster.pop('error', "", "{template:
						// 'myTemplateWithData.html', data: '" + $scope.errormsg
						// + "' }", 3000, 'templateWithData');
					}
				} else if ($scope.formdata.ret_age == undefined
						|| $scope.formdata.ret_age == "") {
					$scope.errormsg = "Please enter user retirement Age";
					// toaster.error({title: "", body: $scope.errormsg});
					// toaster.pop('error', "", "{template:
					// 'myTemplateWithData.html', data: '" + $scope.errormsg +
					// "' }", 3000, 'templateWithData');
				} else if ($scope.formdata.martial == undefined
						|| $scope.formdata.martial == "") {
					$scope.errormsg = "Please choose marital status";
					// toaster.error({title: "", body: $scope.errormsg});
					// toaster.pop('error', "", "{template:
					// 'myTemplateWithData.html', data: '" + $scope.errormsg +
					// "' }", 3000, 'templateWithData');
				} else {
					tempFlag = true;
				}
			} else {
				if ($scope.formdata.dob == ""
						|| $scope.formdata.dob == undefined) {
					$scope.errormsg = "Please enter user birth year";
					// toaster.error({title: "", body: $scope.errormsg});
					// toaster.pop('error', "", "{template:
					// 'myTemplateWithData.html', data: '" + $scope.errormsg +
					// "' }", 3000, 'templateWithData');
				} else if ($scope.formdata.useraime == undefined
						|| $scope.formdata.useraime == "") {
					$scope.errormsg = "Please choose whether you know your AIME or not";
					// toaster.error({title: "", body: $scope.errormsg});
					// toaster.pop('error', "", "{template:
					// 'myTemplateWithData.html', data: '" + $scope.errormsg +
					// "' }", 3000, 'templateWithData');
				} else if ($scope.formdata.annaul_income == undefined
						|| $scope.formdata.annaul_income == "") {
					if ($scope.formdata.useraime == "Yes") {
						$scope.errormsg = "Please enter user AIME";
						// toaster.error({title: "", body: $scope.errormsg});
						// toaster.pop('error', "", "{template:
						// 'myTemplateWithData.html', data: '" + $scope.errormsg
						// + "' }", 3000, 'templateWithData');
					} else {
						$scope.errormsg = "Please enter user current monthly income";
						// toaster.error({title: "", body: $scope.errormsg});
						// toaster.pop('error', "", "{template:
						// 'myTemplateWithData.html', data: '" + $scope.errormsg
						// + "' }", 3000, 'templateWithData');
					}
				} else if ($scope.formdata.ret_age == undefined
						|| $scope.formdata.ret_age == "") {
					$scope.errormsg = "Please enter user retirement age";
					// toaster.error({title: "", body: $scope.errormsg});
					// toaster.pop('error', "", "{template:
					// 'myTemplateWithData.html', data: '" + $scope.errormsg +
					// "' }", 3000, 'templateWithData');
				} else if ($scope.formdata.martial == undefined
						|| $scope.formdata.martial == "") {
					$scope.errormsg = "Please choose marital status";
					// toaster.error({title: "", body: $scope.errormsg});
					// toaster.pop('error', "", "{template:
					// 'myTemplateWithData.html', data: '" + $scope.errormsg +
					// "' }", 3000, 'templateWithData');
				} else {
					tempFlag = true;
				}
			}

			if (tempFlag) {
				$scope.errormsg = "";

				if ($scope.showUserBirth == false) {

					var userdob = $scope.formdata.dob;

					todayDate = new Date();
					todayYear = todayDate.getFullYear();
					todayMonth = todayDate.getMonth();
					todayDay = todayDate.getDate();

					age = todayYear - parseInt(userdob);

					$scope.inputData.user_age = age;
					$scope.inputData.birth_year = userdob;
					$scope.Userage = age;
				} else {
					$scope.inputData.user_age = $scope.userAge;
					$scope.inputData.birth_year = $scope.userBirth;
					$scope.Userage = $scope.userAge;
				}
				$scope.inputData.userAime = formdata.useraime;
				$scope.inputData.userAnnaul_income = formdata.annaul_income;
				$scope.inputData.userRetirementAge = formdata.ret_age;
				$scope.inputData.user_id = $scope.user_id;

				if (formdata.martial == "Single") {
					$scope.inputData.action = "calculateSSBSingle";

					var minYear = todayYear - 69;

					if (Number($scope.inputData.birth_year) < minYear) {
						$scope.errormsg = "User birth year cannot be less than "
								+ minYear;
						// toaster.error({title: "", body: $scope.errormsg});
						// toaster.pop('error', "", "{template:
						// 'myTemplateWithData.html', data: '" + $scope.errormsg
						// + "' }", 3000, 'templateWithData');
					} else {
						$scope.calculate(formdata);
					}
				} else {
					if ($scope.formdata.martial == "Married") {
						$scope.inputData.martialStatus = "Married";

						if ($scope.formdata.spousework == undefined) {
							$scope.errormsg = "Please choose whether spouse is working or not";
							// toaster.error({title: "", body:
							// $scope.errormsg});
							// toaster.pop('error', "", "{template:
							// 'myTemplateWithData.html', data: '" +
							// $scope.errormsg + "' }", 3000,
							// 'templateWithData');
						} else {
							if ($scope.showSpouseBirth == false) {

								var spousedob = $scope.formdata.spousedob;
								todayDate = new Date();
								todayYear = todayDate.getFullYear();
								todayMonth = todayDate.getMonth();
								todayDay = todayDate.getDate();
								spouseage = todayYear - parseInt(spousedob);

								$scope.inputData.spouse_birth_year = spousedob;
								$scope.inputData.spouse_age = spouseage;
								$scope.Spouseage = spouseage;
							} else {
								$scope.inputData.spouse_age = $scope.spouseAge;
								$scope.inputData.spouse_birth_year = $scope.spouseBirth;
								$scope.Spouseage = $scope.spouseAge;
							}
							$scope.inputData.spouseRetirementAge = formdata.spouseret_age;
							if ($scope.formdata.spousework == "Yes") {
								if ($scope.formdata.spousedob == ""
										|| $scope.formdata.spousedob == undefined) {
									$scope.errormsg = "Please enter spouse birth year";
									// toaster.error({title: "", body:
									// $scope.errormsg});
									// toaster.pop('error', "", "{template:
									// 'myTemplateWithData.html', data: '" +
									// $scope.errormsg + "' }", 3000,
									// 'templateWithData');
								} else if ($scope.formdata.spouseaime == undefined
										|| $scope.formdata.spouseaime == "") {
									$scope.errormsg = "Please choose whether you know your spouse AIME or not";
									// toaster.error({title: "", body:
									// $scope.errormsg});
									// toaster.pop('error', "", "{template:
									// 'myTemplateWithData.html', data: '" +
									// $scope.errormsg + "' }", 3000,
									// 'templateWithData');
								} else if ($scope.formdata.spouseannaul_income == undefined
										|| $scope.formdata.spouseannaul_income == "") {
									if ($scope.formdata.spouseaime == "Yes") {
										$scope.errormsg = "Please enter spouse AIME";
										// toaster.error({title: "", body:
										// $scope.errormsg});
										// toaster.pop('error', "", "{template:
										// 'myTemplateWithData.html', data: '" +
										// $scope.errormsg + "' }", 3000,
										// 'templateWithData');
									} else {
										$scope.errormsg = "Please enter spouse current monthly income";
										// toaster.error({title: "", body:
										// $scope.errormsg});
										// toaster.pop('error', "", "{template:
										// 'myTemplateWithData.html', data: '" +
										// $scope.errormsg + "' }", 3000,
										// 'templateWithData');
									}
								} else if ($scope.formdata.spouseret_age == undefined
										|| $scope.formdata.spouseret_age == "") {
									$scope.errormsg = "Please enter spouse retirement age";
									// toaster.error({title: "", body:
									// $scope.errormsg});
									// toaster.pop('error', "", "{template:
									// 'myTemplateWithData.html', data: '" +
									// $scope.errormsg + "' }", 3000,
									// 'templateWithData');
								} else {
									$scope.inputData.action = "calculateSSBMarriedWorking";
									$scope.inputData.spouseAnnaul_income = formdata.spouseannaul_income;
									$scope.inputData.spouseRetirementAge = formdata.spouseret_age;
									$scope.inputData.spouseAime = formdata.spouseaime;

									var minYear = todayYear - 69;
									if (Number($scope.inputData.birth_year) < minYear) {
										$scope.errormsg = "User birth year cannot be less than "
												+ minYear;
										// toaster.error({title: "", body:
										// $scope.errormsg});
										// toaster.pop('error', "", "{template:
										// 'myTemplateWithData.html', data: '" +
										// $scope.errormsg + "' }", 3000,
										// 'templateWithData');

									} else if (Number($scope.inputData.spouse_birth_year) < minYear) {
										$scope.errormsg = "Spouse birth year cannot be less than "
												+ minYear;
										// toaster.error({title: "", body:
										// $scope.errormsg});
										// toaster.pop('error', "", "{template:
										// 'myTemplateWithData.html', data: '" +
										// $scope.errormsg + "' }", 3000,
										// 'templateWithData');
									} else {
										$scope.calculate(formdata);
									}
								}

							} else if ($scope.formdata.spousework == "No") {
								$scope.inputData.action = "calculateSSBMarriedNotWorking";
								$scope.inputData.spouseAnnaul_income = 0;
								$scope.inputData.spouseRetirementAge = $scope.inputData.userRetirementAge;
								var minYear = todayYear - 69;

								if (Number($scope.inputData.birth_year) < minYear) {
									$scope.errormsg = "User birth year cannot be less than "
											+ minYear;
									// toaster.error({title: "", body:
									// $scope.errormsg});
									// toaster.pop('error', "", "{template:
									// 'myTemplateWithData.html', data: '" +
									// $scope.errormsg + "' }", 3000,
									// 'templateWithData');
								} else {
									$scope.calculate(formdata);
								}
							}
						}
					} else
						$scope.calculate(formdata);
				}
			}
		}
	};

	$scope.resetForm = function() {
		$scope.formdata.income_prof = "";
		$scope.formdata.dob = "";
		$scope.formdata.useraime = "";
		$scope.formdata.annaul_income = "";
		$scope.formdata.ret_age = "";
		$scope.formdata.martial = "";
		$scope.formdata.spousework = "";
		$scope.formdata.spousedob = "";
		$scope.formdata.spouseaime = "";
		$scope.formdata.spouseannaul_income = "";
		$scope.formdata.spouseret_age = "";
		$scope.showChart = false;
		$scope.dataSSB = [];
		$scope.optionsSSB = {};
		if($scope.user_id == "") {
			$scope.formdata.income = "HideIncome";
			$scope.showIncomeProfileRadio = false;
			$scope.selectedFromIncome = true;
			$scope.showLabelUser = false;
			$scope.showThis3 = false;
			$scope.showThis = false;
			$scope.showThis4 = false;
			$scope.showLabelSpouse = false;
		} else {
			$scope.formdata.income = "";
			$scope.showIncomeProfileRadio = true;
			$scope.selectedFromIncome = false;
			$scope.showLabelUser = false;
			$scope.showThis3 = false;
			$scope.showThis = false;
			$scope.showThis4 = false;
			$scope.showLabelSpouse = false;
		}
		/*
		 * $scope.showLabelUser = false; $scope.showLabelSpouse = false;
		 * $scope.showThis = false; $scope.showThis2 = false;
		 */
	}

	$scope.calculate = function(formdata) {
		console.log($scope.inputData);
		$scope.user62 = 0;
		$scope.user63 = 0;
		$scope.user64 = 0;
		$scope.user65 = 0;
		$scope.user66 = 0;
		$scope.user67 = 0;
		$scope.user68 = 0;
		$scope.user69 = 0;
		$scope.user70 = 0;
		$scope.spouse62 = 0;
		$scope.spouse63 = 0;
		$scope.spouse64 = 0;
		$scope.spouse65 = 0;
		$scope.spouse66 = 0;
		$scope.spouse67 = 0;
		$scope.spouse68 = 0;
		$scope.spouse69 = 0;
		$scope.spouse70 = 0;
		$http({
			method : "POST",
			url : "SSBCalculator",
			data : $.param($scope.inputData),
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded"
			}
		})
				.then(
						function(result) {
							console.log(result.data);
							if (result.data.status == "success") {
								// $state.go('ssb.result');
								$scope.showChart = true;
								var drawUser = false;
								var drawSpouse = false;
								var userSSBN = false;
								var spouseSSBN = false;
								$scope.userFra = result.data.userFRA;
								if (Number($scope.inputData.userAnnaul_income) > 0
										&& result.data.userSSB.userssb <= 0) {
									/*
									 * $scope.errormsg = "User SSB is going
									 * negative"; toaster.error({title: "",
									 * body: $scope.errormsg});
									 */
									$scope.userSSB = false;
									$scope.spouseSSB = false;
									$scope.spouseSSB2 = false;
									$scope.spouseSSB3 = false;
									$scope.showUser62 = false;
									$scope.showUser66 = false;
									$scope.showUser70 = false;
									$scope.userNegative = false;
									userSSBN = true;
									drawUser = false;
								} else {
									drawUser = true;
									$scope.user62 = result.data.userSSB.User62;
									$scope.user63 = result.data.userSSB.User63;
									$scope.user64 = result.data.userSSB.User64;
									$scope.user65 = result.data.userSSB.User65;
									$scope.user66 = result.data.userSSB.User66;
									$scope.user67 = result.data.userSSB.User67;
									$scope.user68 = result.data.userSSB.User68;
									$scope.user69 = result.data.userSSB.User69;
									$scope.user70 = result.data.userSSB.User70;
									$scope.userNegativeShow = [];

									if ($scope.user62 <= 0) {
										$scope.userNegativeShow.push("62");
										$scope.user62 = 0;
									}

									if ($scope.user63 <= 0) {
										$scope.userNegativeShow.push("63");
										$scope.user63 = 0;
									}

									if ($scope.user64 <= 0) {
										$scope.userNegativeShow.push("64");
										$scope.user64 = 0;
									}

									if ($scope.user65 <= 0) {
										$scope.userNegativeShow.push("65");
										$scope.user65 = 0;
									}

									if ($scope.user66 <= 0) {
										$scope.userNegativeShow.push("66");
										$scope.user66 = 0;
									}

									if ($scope.user67 <= 0) {
										$scope.userNegativeShow.push("67");
										$scope.user67 = 0;
									}

									if ($scope.user68 <= 0) {
										$scope.userNegativeShow.push("68");
										$scope.user68 = 0;
									}

									if ($scope.user69 <= 0) {
										$scope.userNegativeShow.push("69");
										$scope.user69 = 0;
									}
									if ($scope.user70 <= 0) {
										$scope.userNegativeShow.push("70");
										$scope.user70 = 0;
									}

									if (formdata.ret_age == "62") {
										$scope.userssb = $scope.user62;
									} else if (formdata.ret_age == "63") {
										$scope.userssb = $scope.user63;
									} else if (formdata.ret_age == "64") {
										$scope.userssb = $scope.user64;
									} else if (formdata.ret_age == "65") {
										$scope.userssb = $scope.user65;
									} else if (formdata.ret_age == "66") {
										$scope.userssb = $scope.user66;
									} else if (formdata.ret_age == "67") {
										$scope.userssb = $scope.user67;
									} else if (formdata.ret_age == "68") {
										$scope.userssb = $scope.user68;
									} else if (formdata.ret_age == "69") {
										$scope.userssb = $scope.user69;
									} else {
										$scope.userssb = $scope.user70;
									}

									if ($scope.userNegativeShow.length > 0) {
										$scope.userNegative = true;
									} else {
										$scope.userNegative = false;
									}

									$scope.userSSB = true;
								}

								if ($scope.inputData.action == "calculateSSBSingle"
										|| $scope.inputData.action == "calculateFromProfile") {
									if ($scope.formdata.martial == "Married") {
										if ($scope.formdata.spousework == "Yes") {
											$scope.spouseFra = result.data.spouseFRA;
											$scope.earlySpouseRetAge = result.data.earlySpouseRetAge;
											if (Number($scope.inputData.spouseAnnaul_income) > 0
													&& result.data.spouseSSB.spousessb <= 0) {
												if (userSSBN) {
													spouseSSBN = true;
													drawUser = false;
													drawSpouse = false;
													/*
													 * $scope.errormsg = "User
													 * and Spouse SSB's are
													 * going negative";
													 * toaster.error({title: "",
													 * body: $scope.errormsg});
													 */
													$scope.userNegative = false;
													$scope.spouseSSB = false;
													$scope.spouseSSB2 = false;
													$scope.spouseSSB3 = false;
													$scope.spouseSSB4 = false;

													$scope.showSpouse62 = false;
													$scope.showSpouse66 = false;
													$scope.showSpouse70 = false;
													$scope.spouseNegative = false;
												} else {
													drawSpouse = true;
													$scope.spouseSSB = true;
													$scope.spouseSSB2 = false;
													$scope.spouseSSB3 = false;
													$scope.spouseSSB4 = true;
													$scope.spouse62 = result.data.spouseSSB.Spouse62;
													$scope.spouse63 = result.data.spouseSSB.Spouse63;
													$scope.spouse64 = result.data.spouseSSB.Spouse64;
													$scope.spouse65 = result.data.spouseSSB.Spouse65;
													$scope.spouse66 = result.data.spouseSSB.Spouse66;
													$scope.spouse67 = result.data.spouseSSB.Spouse67;
													$scope.spouse68 = result.data.spouseSSB.Spouse68;
													$scope.spouse69 = result.data.spouseSSB.Spouse69;
													$scope.spouse70 = result.data.spouseSSB.Spouse70;

													if ($scope.spouse62 < 0) {
														$scope.spouse62 = ($scope.user62 / 2)
																.toFixed(2);
													}
													if ($scope.spouse63 < 0) {
														$scope.spouse63 = ($scope.user63 / 2)
																.toFixed(2);
													}
													if ($scope.spouse64 < 0) {
														$scope.spouse64 = ($scope.user64 / 2)
																.toFixed(2);
													}
													if ($scope.spouse65 < 0) {
														$scope.spouse65 = ($scope.user65 / 2)
																.toFixed(2);
													}
													if ($scope.spouse66 < 0) {
														$scope.spouse66 = ($scope.user66 / 2)
																.toFixed(2);
													}
													if ($scope.spouse67 < 0) {
														$scope.spouse67 = ($scope.user67 / 2)
																.toFixed(2);
													}
													if ($scope.spouse68 < 0) {
														$scope.spouse68 = ($scope.user68 / 2)
																.toFixed(2);
													}
													if ($scope.spouse69 < 0) {
														$scope.spouse69 = ($scope.user69 / 2)
																.toFixed(2);
													}
													if ($scope.spouse70 < 0) {
														$scope.spouse70 = ($scope.user70 / 2)
																.toFixed(2);
													}

													if (formdata.spouseret_age == "62") {
														$scope.spousessb = $scope.spouse62;
													} else if (formdata.spouseret_age == "63") {
														$scope.spousessb = $scope.spouse63;
													} else if (formdata.spouseret_age == "64") {
														$scope.spousessb = $scope.spouse64;
													} else if (formdata.spouseret_age == "65") {
														$scope.spousessb = $scope.spouse65;
													} else if (formdata.spouseret_age == "66") {
														$scope.spousessb = $scope.spouse66;
													} else if (formdata.spouseret_age == "67") {
														$scope.spousessb = $scope.spouse67;
													} else if (formdata.spouseret_age == "68") {
														$scope.spousessb = $scope.spouse68;
													} else if (formdata.spouseret_age == "69") {
														$scope.spousessb = $scope.spouse69;
													} else {
														$scope.spousessb = $scope.spouse70;
													}

													if ($scope.spousessb <= $scope.userssb / 2) {
														$scope.spousessb = ($scope.userssb / 2)
																.toFixed(2);
													}

													$scope.spouseNegativeShow = [];

													if ($scope.spouse62 <= 0) {
														if ($scope.user62 > 0) {
															$scope.spouse62 = ($scope.user62 / 2)
																	.toFixed(2);
														} else {
															$scope.spouse62 = 0;
															$scope.spouseNegativeShow
																	.push("62");
														}
													} else if ($scope.spouse62 < $scope.user62 / 2) {
														$scope.spouse62 = ($scope.user62 / 2)
																.toFixed(2);
														if ($scope.spouse62 < 0) {
															$scope.spouse62 = 0;
															$scope.spouseNegativeShow
																	.push("62");
														}
													}

													if ($scope.spouse63 <= 0) {
														if ($scope.user63 > 0) {
															$scope.spouse63 = ($scope.user63 / 2)
																	.toFixed(2);
														} else {
															$scope.spouse63 = 0;
															$scope.spouseNegativeShow
																	.push("63");
														}
													} else if ($scope.spouse63 < $scope.user63 / 2) {
														$scope.spouse63 = ($scope.user63 / 2)
																.toFixed(2);
														if ($scope.spouse63 < 0) {
															$scope.spouse63 = 0;
															$scope.spouseNegativeShow
																	.push("63");
														}
													}

													if ($scope.spouse64 <= 0) {
														if ($scope.user64 > 0) {
															$scope.spouse64 = ($scope.user64 / 2)
																	.toFixed(2);
														} else {
															$scope.spouse64 = 0;
															$scope.spouseNegativeShow
																	.push("64");
														}
													} else if ($scope.spouse64 < $scope.user64 / 2) {
														$scope.spouse64 = ($scope.user64 / 2)
																.toFixed(2);
														if ($scope.spouse64 < 0) {
															$scope.spouse64 = 0;
															$scope.spouseNegativeShow
																	.push("64");
														}
													}

													if ($scope.spouse65 <= 0) {
														if ($scope.user65 > 0) {
															$scope.spouse65 = ($scope.user65 / 2)
																	.toFixed(2);
														} else {
															$scope.spouse65 = 0;
															$scope.spouseNegativeShow
																	.push("65");
														}
													} else if ($scope.spouse65 < $scope.user65 / 2) {
														$scope.spouse65 = ($scope.user65 / 2)
																.toFixed(2);
														if ($scope.spouse65 < 0) {
															$scope.spouse65 = 0;
															$scope.spouseNegativeShow
																	.push("65");
														}
													}

													if ($scope.spouse66 <= 0) {
														if ($scope.user66 > 0) {
															$scope.spouse66 = ($scope.user66 / 2)
																	.toFixed(2);
														} else {
															$scope.spouse66 = 0;
															$scope.spouseNegativeShow
																	.push("66");
														}
													} else if ($scope.spouse66 < $scope.user66 / 2) {
														$scope.spouse66 = ($scope.user66 / 2)
																.toFixed(2);
														if ($scope.spouse66 < 0) {
															$scope.spouse66 = 0;
															$scope.spouseNegativeShow
																	.push("66");
														}
													}

													if ($scope.spouse67 <= 0) {
														if ($scope.user67 > 0) {
															$scope.spouse67 = ($scope.user67 / 2)
																	.toFixed(2);
														} else {
															$scope.spouse67 = 0;
															$scope.spouseNegativeShow
																	.push("67");
														}
													} else if ($scope.spouse67 < $scope.user67 / 2) {
														$scope.spouse67 = ($scope.user67 / 2)
																.toFixed(2);
														if ($scope.spouse67 < 0) {
															$scope.spouse67 = 0;
															$scope.spouseNegativeShow
																	.push("67");
														}
													}

													if ($scope.spouse68 <= 0) {
														if ($scope.user68 > 0) {
															$scope.spouse68 = ($scope.user68 / 2)
																	.toFixed(2);
														} else {
															$scope.spouse68 = 0;
															$scope.spouseNegativeShow
																	.push("68");
														}
													} else if ($scope.spouse68 < $scope.user68 / 2) {
														$scope.spouse68 = ($scope.user68 / 2)
																.toFixed(2);
														if ($scope.spouse68 < 0) {
															$scope.spouse68 = 0;
															$scope.spouseNegativeShow
																	.push("68");
														}
													}

													if ($scope.spouse69 <= 0) {
														if ($scope.user69 > 0) {
															$scope.spouse69 = ($scope.user69 / 2)
																	.toFixed(2);
														} else {
															$scope.spouse69 = 0;
															$scope.spouseNegativeShow
																	.push("69");
														}
													} else if ($scope.spouse69 < $scope.user69 / 2) {
														$scope.spouse69 = ($scope.user69 / 2)
																.toFixed(2);
														if ($scope.spouse69 < 0) {
															$scope.spouse69 = 0;
															$scope.spouseNegativeShow
																	.push("69");
														}
													}

													if ($scope.spouse70 <= 0) {
														if ($scope.user70 > 0) {
															$scope.spouse70 = ($scope.user70 / 2)
																	.toFixed(2);
														} else {
															$scope.spouse70 = 0;
															$scope.spouseNegativeShow
																	.push("70");
														}
													} else if ($scope.spouse70 < $scope.user70 / 2) {
														$scope.spouse70 = ($scope.user70 / 2)
																.toFixed(2);
														if ($scope.spouse70 < 0) {
															$scope.spouse70 = 0;
															$scope.spouseNegativeShow
																	.push("70");
														}
													}

													if (formdata.spouseret_age == "62") {
														$scope.spouse62 = $scope.spousessb;
													} else if (formdata.spouseret_age == "63") {
														$scope.spouse63 = $scope.spousessb;
													} else if (formdata.spouseret_age == "64") {
														$scope.spouse64 = $scope.spousessb;
													} else if (formdata.spouseret_age == "65") {
														$scope.spouse65 = $scope.spousessb;
													} else if (formdata.spouseret_age == "66") {
														$scope.spouse66 = $scope.spousessb;
													} else if (formdata.spouseret_age == "67") {
														$scope.spouse67 = $scope.spousessb;
													} else if (formdata.spouseret_age == "68") {
														$scope.spouse68 = $scope.spousessb;
													} else if (formdata.spouseret_age == "69") {
														$scope.spouse69 = $scope.spousessb;
													} else {
														$scope.spouse70 = $scope.spousessb;
													}

													if ($scope.spousessb < 0) {
														spouseSSBN = true;
														drawSpouse = false;
														/*
														 * $scope.errormsg =
														 * "Spouse SSB is going
														 * negative";
														 * toaster.error({title:
														 * "", body:
														 * $scope.errormsg});
														 */
														$scope.spouseSSB = false;
														$scope.spouseSSB2 = false;
														$scope.spouseSSB3 = false;
														$scope.spouseSSB4 = false;

														$scope.showSpouse62 = false;
														$scope.showSpouse66 = false;
														$scope.showSpouse70 = false;
														$scope.spouseNegative = false;
													}

													if ($scope.spouseNegativeShow.length > 0) {
														$scope.spouseNegative = true;
													} else {
														$scope.spouseNegative = false;
													}

												}
											} else {
												drawSpouse = true;

												$scope.spouseSSB = true;
												$scope.spouseSSB2 = true;
												$scope.spouseSSB3 = false;
												$scope.spouseSSB4 = false;
												$scope.spouse62 = result.data.spouseSSB.Spouse62;
												$scope.spouse63 = result.data.spouseSSB.Spouse63;
												$scope.spouse64 = result.data.spouseSSB.Spouse64;
												$scope.spouse65 = result.data.spouseSSB.Spouse65;
												$scope.spouse66 = result.data.spouseSSB.Spouse66;
												$scope.spouse67 = result.data.spouseSSB.Spouse67;
												$scope.spouse68 = result.data.spouseSSB.Spouse68;
												$scope.spouse69 = result.data.spouseSSB.Spouse69;
												$scope.spouse70 = result.data.spouseSSB.Spouse70;

												if (formdata.spouseret_age == "62") {
													$scope.spousessb = $scope.spouse62;
												} else if (formdata.spouseret_age == "63") {
													$scope.spousessb = $scope.spouse63;
												} else if (formdata.spouseret_age == "64") {
													$scope.spousessb = $scope.spouse64;
												} else if (formdata.spouseret_age == "65") {
													$scope.spousessb = $scope.spouse65;
												} else if (formdata.spouseret_age == "66") {
													$scope.spousessb = $scope.spouse66;
												} else if (formdata.spouseret_age == "67") {
													$scope.spousessb = $scope.spouse67;
												} else if (formdata.spouseret_age == "68") {
													$scope.spousessb = $scope.spouse68;
												} else if (formdata.spouseret_age == "69") {
													$scope.spousessb = $scope.spouse69;
												} else {
													$scope.spousessb = $scope.spouse70;
												}

												if ($scope.spousessb < $scope.userssb / 2) {
													$scope.spousessb = ($scope.userssb / 2)
															.toFixed(2);
												}

												$scope.spouseNegativeShow = [];

												if ($scope.spouse62 <= 0) {
													if ($scope.user62 > 0) {
														$scope.spouse62 = ($scope.user62 / 2)
																.toFixed(2);
													} else {
														$scope.spouse62 = 0;
														$scope.spouseNegativeShow
																.push("62");
													}
												} else if ($scope.spouse62 < $scope.user62 / 2) {
													$scope.spouse62 = ($scope.user62 / 2)
															.toFixed(2);
													if ($scope.spouse62 < 0) {
														$scope.spouse62 = 0;
														$scope.spouseNegativeShow
																.push("62");
													}
												}

												if ($scope.spouse63 <= 0) {
													if ($scope.user63 > 0) {
														$scope.spouse63 = ($scope.user63 / 2)
																.toFixed(2);
													} else {
														$scope.spouse63 = 0;
														$scope.spouseNegativeShow
																.push("63");
													}
												} else if ($scope.spouse63 < $scope.user63 / 2) {
													$scope.spouse63 = ($scope.user63 / 2)
															.toFixed(2);
													if ($scope.spouse63 < 0) {
														$scope.spouse63 = 0;
														$scope.spouseNegativeShow
																.push("63");
													}
												}

												if ($scope.spouse64 <= 0) {
													if ($scope.user64 > 0) {
														$scope.spouse64 = ($scope.user64 / 2)
																.toFixed(2);
													} else {
														$scope.spouse64 = 0;
														$scope.spouseNegativeShow
																.push("64");
													}
												} else if ($scope.spouse64 < $scope.user64 / 2) {
													$scope.spouse64 = ($scope.user64 / 2)
															.toFixed(2);
													if ($scope.spouse64 < 0) {
														$scope.spouse64 = 0;
														$scope.spouseNegativeShow
																.push("64");
													}
												}

												if ($scope.spouse65 <= 0) {
													if ($scope.user65 > 0) {
														$scope.spouse65 = ($scope.user65 / 2)
																.toFixed(2);
													} else {
														$scope.spouse65 = 0;
														$scope.spouseNegativeShow
																.push("65");
													}
												} else if ($scope.spouse65 < $scope.user65 / 2) {
													$scope.spouse65 = ($scope.user65 / 2)
															.toFixed(2);
													if ($scope.spouse65 < 0) {
														$scope.spouse65 = 0;
														$scope.spouseNegativeShow
																.push("65");
													}
												}

												if ($scope.spouse66 <= 0) {
													if ($scope.user66 > 0) {
														$scope.spouse66 = ($scope.user66 / 2)
																.toFixed(2);
													} else {
														$scope.spouse66 = 0;
														$scope.spouseNegativeShow
																.push("66");
													}
												} else if ($scope.spouse66 < $scope.user66 / 2) {
													$scope.spouse66 = ($scope.user66 / 2)
															.toFixed(2);
													if ($scope.spouse66 < 0) {
														$scope.spouse66 = 0;
														$scope.spouseNegativeShow
																.push("66");
													}
												}

												if ($scope.spouse67 <= 0) {
													if ($scope.user67 > 0) {
														$scope.spouse67 = ($scope.user67 / 2)
																.toFixed(2);
													} else {
														$scope.spouse67 = 0;
														$scope.spouseNegativeShow
																.push("67");
													}
												} else if ($scope.spouse67 < $scope.user67 / 2) {
													$scope.spouse67 = ($scope.user67 / 2)
															.toFixed(2);
													if ($scope.spouse67 < 0) {
														$scope.spouse67 = 0;
														$scope.spouseNegativeShow
																.push("67");
													}
												}

												if ($scope.spouse68 <= 0) {
													if ($scope.user68 > 0) {
														$scope.spouse68 = ($scope.user68 / 2)
																.toFixed(2);
													} else {
														$scope.spouse68 = 0;
														$scope.spouseNegativeShow
																.push("68");
													}
												} else if ($scope.spouse68 < $scope.user68 / 2) {
													$scope.spouse68 = ($scope.user68 / 2)
															.toFixed(2);
													if ($scope.spouse68 < 0) {
														$scope.spouse68 = 0;
														$scope.spouseNegativeShow
																.push("68");
													}
												}

												if ($scope.spouse69 <= 0) {
													if ($scope.user69 > 0) {
														$scope.spouse69 = ($scope.user69 / 2)
																.toFixed(2);
													} else {
														$scope.spouse69 = 0;
														$scope.spouseNegativeShow
																.push("69");
													}
												} else if ($scope.spouse69 < $scope.user69 / 2) {
													$scope.spouse69 = ($scope.user69 / 2)
															.toFixed(2);
													if ($scope.spouse69 < 0) {
														$scope.spouse69 = 0;
														$scope.spouseNegativeShow
																.push("69");
													}
												}

												if ($scope.spouse70 <= 0) {
													if ($scope.user70 > 0) {
														$scope.spouse70 = ($scope.user70 / 2)
																.toFixed(2);
													} else {
														$scope.spouse70 = 0;
														$scope.spouseNegativeShow
																.push("70");
													}
												} else if ($scope.spouse70 < $scope.user70 / 2) {
													$scope.spouse70 = ($scope.user70 / 2)
															.toFixed(2);
													if ($scope.spouse70 < 0) {
														$scope.spouse70 = 0;
														$scope.spouseNegativeShow
																.push("70");
													}
												}

												if (formdata.spouseret_age == "62") {
													$scope.spouse62 = $scope.spousessb;
												} else if (formdata.spouseret_age == "63") {
													$scope.spouse63 = $scope.spousessb;
												} else if (formdata.spouseret_age == "64") {
													$scope.spouse64 = $scope.spousessb;
												} else if (formdata.spouseret_age == "65") {
													$scope.spouse65 = $scope.spousessb;
												} else if (formdata.spouseret_age == "66") {
													$scope.spouse66 = $scope.spousessb;
												} else if (formdata.spouseret_age == "67") {
													$scope.spouse67 = $scope.spousessb;
												} else if (formdata.spouseret_age == "68") {
													$scope.spouse68 = $scope.spousessb;
												} else if (formdata.spouseret_age == "69") {
													$scope.spouse69 = $scope.spousessb;
												} else {
													$scope.spouse70 = $scope.spousessb;
												}
											}
										} else {
											if (result.data.spouseSSB.spousessb <= 0) {
												if (userSSBN) {
													spouseSSBN = true;
													drawUser = false;
													drawSpouse = false;
													/*
													 * $scope.errormsg = "User
													 * and Spouse SSB's are
													 * going negative";
													 * toaster.error({title: "",
													 * body: $scope.errormsg});
													 */
													$scope.userNegative = false;
												} else {
													drawSpouse = false;
													spouseSSBN = true;
													/*
													 * $scope.errormsg = "Spouse
													 * SSB is going negative";
													 * toaster.error({title: "",
													 * body: $scope.errormsg});
													 */
												}

												$scope.spouseSSB4 = false;
												$scope.spouseSSB = false;
												$scope.spouseSSB2 = false;
												$scope.spouseSSB3 = false;
												$scope.userNegative = false;
												$scope.showSpouse62 = false;
												$scope.showSpouse66 = false;
												$scope.showSpouse70 = false;
												$scope.spouseNegative = false;
											} else {
												drawSpouse = false;
												$scope.showSpouse62 = false;
												$scope.showSpouse66 = false;
												$scope.showSpouse70 = false;
												$scope.spouseNegative = false;
												$scope.spouseSSB4 = false;
												$scope.spouseSSB = false;
												$scope.spouseSSB2 = false;
												$scope.spouseSSB3 = true;
												$scope.spouse62 = result.data.spouseSSB.Spouse62;
												$scope.spouse63 = result.data.spouseSSB.Spouse63;
												$scope.spouse64 = result.data.spouseSSB.Spouse64;
												$scope.spouse65 = result.data.spouseSSB.Spouse65;
												$scope.spouse66 = result.data.spouseSSB.Spouse66;
												$scope.spouse67 = result.data.spouseSSB.Spouse67;
												$scope.spouse68 = result.data.spouseSSB.Spouse68;
												$scope.spouse69 = result.data.spouseSSB.Spouse69;
												$scope.spouse70 = result.data.spouseSSB.Spouse70;
												if (formdata.ret_age == "62") {
													$scope.spousessb = $scope.spouse62;
												} else if (formdata.ret_age == "63") {
													$scope.spousessb = $scope.spouse63;
												} else if (formdata.ret_age == "64") {
													$scope.spousessb = $scope.spouse64;
												} else if (formdata.ret_age == "65") {
													$scope.spousessb = $scope.spouse65;
												} else if (formdata.ret_age == "66") {
													$scope.spousessb = $scope.spouse66;
												} else if (formdata.ret_age == "67") {
													$scope.spousessb = $scope.spouse67;
												} else if (formdata.ret_age == "68") {
													$scope.spousessb = $scope.spouse68;
												} else if (formdata.ret_age == "69") {
													$scope.spousessb = $scope.spouse69;
												} else {
													$scope.spousessb = $scope.spouse70;
												}
											}
										}
									} else {
										drawSpouse = false;
										$scope.spouseSSB = false;
										$scope.spouseSSB2 = false;
										$scope.spouseSSB3 = false;
										$scope.spouseNegative = false;
										$scope.showSpouse62 = false;
										$scope.showSpouse66 = false;
										$scope.showSpouse70 = false;
										$scope.spouseSSB4 = false;
									}
								} else if ($scope.inputData.action == "calculateSSBMarriedWorking") {
									$scope.spouseFra = result.data.spouseFRA;
									$scope.earlySpouseRetAge = result.data.earlySpouseRetAge;
									if (Number($scope.inputData.spouseAnnaul_income) > 0
											&& result.data.spouseSSB.spousessb <= 0) {
										if (userSSBN) {
											drawUser = false;
											drawSpouse = false;
											spouseSSBN = true;
											/*
											 * $scope.errormsg = "User and
											 * Spouse SSB's are going negative";
											 * toaster.error({title: "", body:
											 * $scope.errormsg});
											 */
											$scope.userNegative = false;
											$scope.spouseSSB = false;
											$scope.spouseSSB2 = false;
											$scope.spouseSSB3 = false;
											$scope.spouseSSB4 = false;

											$scope.showSpouse62 = false;
											$scope.showSpouse66 = false;
											$scope.showSpouse70 = false;
											$scope.spouseNegative = false;
										} else {
											drawSpouse = true;
											$scope.spouseSSB = true;
											$scope.spouseSSB2 = false;
											$scope.spouseSSB3 = false;
											$scope.spouseSSB4 = true;
											$scope.spouse62 = result.data.spouseSSB.Spouse62;
											$scope.spouse63 = result.data.spouseSSB.Spouse63;
											$scope.spouse64 = result.data.spouseSSB.Spouse64;
											$scope.spouse65 = result.data.spouseSSB.Spouse65;
											$scope.spouse66 = result.data.spouseSSB.Spouse66;
											$scope.spouse67 = result.data.spouseSSB.Spouse67;
											$scope.spouse68 = result.data.spouseSSB.Spouse68;
											$scope.spouse69 = result.data.spouseSSB.Spouse69;
											$scope.spouse70 = result.data.spouseSSB.Spouse70;

											if ($scope.spouse62 < 0) {
												$scope.spouse62 = ($scope.user62 / 2)
														.toFixed(2);
											}
											if ($scope.spouse63 < 0) {
												$scope.spouse63 = ($scope.user63 / 2)
														.toFixed(2);
											}
											if ($scope.spouse64 < 0) {
												$scope.spouse64 = ($scope.user64 / 2)
														.toFixed(2);
											}
											if ($scope.spouse65 < 0) {
												$scope.spouse65 = ($scope.user65 / 2)
														.toFixed(2);
											}
											if ($scope.spouse66 < 0) {
												$scope.spouse66 = ($scope.user66 / 2)
														.toFixed(2);
											}
											if ($scope.spouse67 < 0) {
												$scope.spouse67 = ($scope.user67 / 2)
														.toFixed(2);
											}
											if ($scope.spouse68 < 0) {
												$scope.spouse68 = ($scope.user68 / 2)
														.toFixed(2);
											}
											if ($scope.spouse69 < 0) {
												$scope.spouse69 = ($scope.user69 / 2)
														.toFixed(2);
											}
											if ($scope.spouse70 < 0) {
												$scope.spouse70 = ($scope.user70 / 2)
														.toFixed(2);
											}

											if (formdata.spouseret_age == "62") {
												$scope.spousessb = $scope.spouse62;
											} else if (formdata.spouseret_age == "63") {
												$scope.spousessb = $scope.spouse63;
											} else if (formdata.spouseret_age == "64") {
												$scope.spousessb = $scope.spouse64;
											} else if (formdata.spouseret_age == "65") {
												$scope.spousessb = $scope.spouse65;
											} else if (formdata.spouseret_age == "66") {
												$scope.spousessb = $scope.spouse66;
											} else if (formdata.spouseret_age == "67") {
												$scope.spousessb = $scope.spouse67;
											} else if (formdata.spouseret_age == "68") {
												$scope.spousessb = $scope.spouse68;
											} else if (formdata.spouseret_age == "69") {
												$scope.spousessb = $scope.spouse69;
											} else {
												$scope.spousessb = $scope.spouse70;
											}

											if ($scope.spousessb <= $scope.userssb / 2) {
												$scope.spousessb = ($scope.userssb / 2)
														.toFixed(2);
											}

											$scope.spouseNegativeShow = [];
											if ($scope.spouse62 <= 0) {
												if ($scope.user62 > 0) {
													$scope.spouse62 = ($scope.user62 / 2)
															.toFixed(2);
												} else {
													$scope.spouse62 = 0;
													$scope.spouseNegativeShow
															.push("62");
												}
											} else if ($scope.spouse62 < $scope.user62 / 2) {
												$scope.spouse62 = ($scope.user62 / 2)
														.toFixed(2);
												if ($scope.spouse62 < 0) {
													$scope.spouse62 = 0;
													$scope.spouseNegativeShow
															.push("62");
												}
											}

											if ($scope.spouse63 <= 0) {
												if ($scope.user63 > 0) {
													$scope.spouse63 = ($scope.user63 / 2)
															.toFixed(2);
												} else {
													$scope.spouse63 = 0;
													$scope.spouseNegativeShow
															.push("63");
												}
											} else if ($scope.spouse63 < $scope.user63 / 2) {
												$scope.spouse63 = ($scope.user63 / 2)
														.toFixed(2);
												if ($scope.spouse63 < 0) {
													$scope.spouse63 = 0;
													$scope.spouseNegativeShow
															.push("63");
												}
											}

											if ($scope.spouse64 <= 0) {
												if ($scope.user64 > 0) {
													$scope.spouse64 = ($scope.user64 / 2)
															.toFixed(2);
												} else {
													$scope.spouse64 = 0;
													$scope.spouseNegativeShow
															.push("64");
												}
											} else if ($scope.spouse64 < $scope.user64 / 2) {
												$scope.spouse64 = ($scope.user64 / 2)
														.toFixed(2);
												if ($scope.spouse64 < 0) {
													$scope.spouse64 = 0;
													$scope.spouseNegativeShow
															.push("64");
												}
											}

											if ($scope.spouse65 <= 0) {
												if ($scope.user65 > 0) {
													$scope.spouse65 = ($scope.user65 / 2)
															.toFixed(2);
												} else {
													$scope.spouse65 = 0;
													$scope.spouseNegativeShow
															.push("65");
												}
											} else if ($scope.spouse65 < $scope.user65 / 2) {
												$scope.spouse65 = ($scope.user65 / 2)
														.toFixed(2);
												if ($scope.spouse65 < 0) {
													$scope.spouse65 = 0;
													$scope.spouseNegativeShow
															.push("65");
												}
											}

											if ($scope.spouse66 <= 0) {
												if ($scope.user66 > 0) {
													$scope.spouse66 = ($scope.user66 / 2)
															.toFixed(2);
												} else {
													$scope.spouse66 = 0;
													$scope.spouseNegativeShow
															.push("66");
												}
											} else if ($scope.spouse66 < $scope.user66 / 2) {
												$scope.spouse66 = ($scope.user66 / 2)
														.toFixed(2);
												if ($scope.spouse66 < 0) {
													$scope.spouse66 = 0;
													$scope.spouseNegativeShow
															.push("66");
												}
											}

											if ($scope.spouse67 <= 0) {
												if ($scope.user67 > 0) {
													$scope.spouse67 = ($scope.user67 / 2)
															.toFixed(2);
												} else {
													$scope.spouse67 = 0;
													$scope.spouseNegativeShow
															.push("67");
												}
											} else if ($scope.spouse67 < $scope.user67 / 2) {
												$scope.spouse67 = ($scope.user67 / 2)
														.toFixed(2);
												if ($scope.spouse67 < 0) {
													$scope.spouse67 = 0;
													$scope.spouseNegativeShow
															.push("67");
												}
											}

											if ($scope.spouse68 <= 0) {
												if ($scope.user68 > 0) {
													$scope.spouse68 = ($scope.user68 / 2)
															.toFixed(2);
												} else {
													$scope.spouse68 = 0;
													$scope.spouseNegativeShow
															.push("68");
												}
											} else if ($scope.spouse68 < $scope.user68 / 2) {
												$scope.spouse68 = ($scope.user68 / 2)
														.toFixed(2);
												if ($scope.spouse68 < 0) {
													$scope.spouse68 = 0;
													$scope.spouseNegativeShow
															.push("68");
												}
											}

											if ($scope.spouse69 <= 0) {
												if ($scope.user69 > 0) {
													$scope.spouse69 = ($scope.user69 / 2)
															.toFixed(2);
												} else {
													$scope.spouse69 = 0;
													$scope.spouseNegativeShow
															.push("69");
												}
											} else if ($scope.spouse69 < $scope.user69 / 2) {
												$scope.spouse69 = ($scope.user69 / 2)
														.toFixed(2);
												if ($scope.spouse69 < 0) {
													$scope.spouse69 = 0;
													$scope.spouseNegativeShow
															.push("69");
												}
											}

											if ($scope.spouse70 <= 0) {
												if ($scope.user70 > 0) {
													$scope.spouse70 = ($scope.user70 / 2)
															.toFixed(2);
												} else {
													$scope.spouse70 = 0;
													$scope.spouseNegativeShow
															.push("70");
												}
											} else if ($scope.spouse70 < $scope.user70 / 2) {
												$scope.spouse70 = ($scope.user70 / 2)
														.toFixed(2);
												if ($scope.spouse70 < 0) {
													$scope.spouse70 = 0;
													$scope.spouseNegativeShow
															.push("70");
												}
											}

											if ($scope.spousessb < 0) {
												drawUser = true;
												drawSpouse = false;
												spouseSSBN = true;
												/*
												 * $scope.errormsg = "Spouse SSB
												 * is going negative";
												 * toaster.error({title: "",
												 * body: $scope.errormsg});
												 */
												$scope.spouseSSB = false;
												$scope.spouseSSB2 = false;
												$scope.spouseSSB3 = false;
												$scope.spouseSSB4 = false;

												$scope.showSpouse62 = false;
												$scope.showSpouse66 = false;
												$scope.showSpouse70 = false;
												$scope.spouseNegative = false;
											}

											if ($scope.spouseNegativeShow.length > 0) {
												$scope.spouseNegative = true;
											} else
												$scope.spouseNegative = false;
										}
									} else {
										drawSpouse = true;
										$scope.spouseSSB = true;
										$scope.spouseSSB2 = true;
										$scope.spouseSSB3 = false;
										$scope.spouseSSB4 = false;
										$scope.spouse62 = result.data.spouseSSB.Spouse62;
										$scope.spouse63 = result.data.spouseSSB.Spouse63;
										$scope.spouse64 = result.data.spouseSSB.Spouse64;
										$scope.spouse65 = result.data.spouseSSB.Spouse65;
										$scope.spouse66 = result.data.spouseSSB.Spouse66;
										$scope.spouse67 = result.data.spouseSSB.Spouse67;
										$scope.spouse68 = result.data.spouseSSB.Spouse68;
										$scope.spouse69 = result.data.spouseSSB.Spouse69;
										$scope.spouse70 = result.data.spouseSSB.Spouse70;

										if (formdata.spouseret_age == "62") {
											$scope.spousessb = $scope.spouse62;
										} else if (formdata.spouseret_age == "63") {
											$scope.spousessb = $scope.spouse63;
										} else if (formdata.spouseret_age == "64") {
											$scope.spousessb = $scope.spouse64;
										} else if (formdata.spouseret_age == "65") {
											$scope.spousessb = $scope.spouse65;
										} else if (formdata.spouseret_age == "66") {
											$scope.spousessb = $scope.spouse66;
										} else if (formdata.spouseret_age == "67") {
											$scope.spousessb = $scope.spouse67;
										} else if (formdata.spouseret_age == "68") {
											$scope.spousessb = $scope.spouse68;
										} else if (formdata.spouseret_age == "69") {
											$scope.spousessb = $scope.spouse69;
										} else {
											$scope.spousessb = $scope.spouse70;
										}

										if ($scope.spousessb <= $scope.userssb / 2) {
											$scope.spousessb = ($scope.userssb / 2)
													.toFixed(2);
										}

										$scope.spouseNegativeShow = [];

										if ($scope.spouse62 <= 0) {
											if ($scope.user62 > 0) {
												$scope.spouse62 = ($scope.user62 / 2)
														.toFixed(2);
											} else {
												$scope.spouse62 = 0;
												$scope.spouseNegativeShow
														.push("62");
											}
										} else if ($scope.spouse62 < $scope.user62 / 2) {
											$scope.spouse62 = ($scope.user62 / 2)
													.toFixed(2);
											if ($scope.spouse62 < 0) {
												$scope.spouse62 = 0;
												$scope.spouseNegativeShow
														.push("62");
											}
										}

										if ($scope.spouse63 <= 0) {
											if ($scope.user63 > 0) {
												$scope.spouse63 = ($scope.user63 / 2)
														.toFixed(2);
											} else {
												$scope.spouse63 = 0;
												$scope.spouseNegativeShow
														.push("63");
											}
										} else if ($scope.spouse63 < $scope.user63 / 2) {
											$scope.spouse63 = ($scope.user63 / 2)
													.toFixed(2);
											if ($scope.spouse63 < 0) {
												$scope.spouse63 = 0;
												$scope.spouseNegativeShow
														.push("63");
											}
										}

										if ($scope.spouse64 <= 0) {
											if ($scope.user64 > 0) {
												$scope.spouse64 = ($scope.user64 / 2)
														.toFixed(2);
											} else {
												$scope.spouse64 = 0;
												$scope.spouseNegativeShow
														.push("64");
											}
										} else if ($scope.spouse64 < $scope.user64 / 2) {
											$scope.spouse64 = ($scope.user64 / 2)
													.toFixed(2);
											if ($scope.spouse64 < 0) {
												$scope.spouse64 = 0;
												$scope.spouseNegativeShow
														.push("64");
											}
										}

										if ($scope.spouse65 <= 0) {
											if ($scope.user65 > 0) {
												$scope.spouse65 = ($scope.user65 / 2)
														.toFixed(2);
											} else {
												$scope.spouse65 = 0;
												$scope.spouseNegativeShow
														.push("65");
											}
										} else if ($scope.spouse65 < $scope.user65 / 2) {
											$scope.spouse65 = ($scope.user65 / 2)
													.toFixed(2);
											if ($scope.spouse65 < 0) {
												$scope.spouse65 = 0;
												$scope.spouseNegativeShow
														.push("65");
											}
										}

										if ($scope.spouse66 <= 0) {
											if ($scope.user66 > 0) {
												$scope.spouse66 = ($scope.user66 / 2)
														.toFixed(2);
											} else {
												$scope.spouse66 = 0;
												$scope.spouseNegativeShow
														.push("66");
											}
										} else if ($scope.spouse66 < $scope.user66 / 2) {
											$scope.spouse66 = ($scope.user66 / 2)
													.toFixed(2);
											if ($scope.spouse66 < 0) {
												$scope.spouse66 = 0;
												$scope.spouseNegativeShow
														.push("66");
											}
										}

										if ($scope.spouse67 <= 0) {
											if ($scope.user67 > 0) {
												$scope.spouse67 = ($scope.user67 / 2)
														.toFixed(2);
											} else {
												$scope.spouse67 = 0;
												$scope.spouseNegativeShow
														.push("67");
											}
										} else if ($scope.spouse67 < $scope.user67 / 2) {
											$scope.spouse67 = ($scope.user67 / 2)
													.toFixed(2);
											if ($scope.spouse67 < 0) {
												$scope.spouse67 = 0;
												$scope.spouseNegativeShow
														.push("67");
											}
										}

										if ($scope.spouse68 <= 0) {
											if ($scope.user68 > 0) {
												$scope.spouse68 = ($scope.user68 / 2)
														.toFixed(2);
											} else {
												$scope.spouse68 = 0;
												$scope.spouseNegativeShow
														.push("68");
											}
										} else if ($scope.spouse68 < $scope.user68 / 2) {
											$scope.spouse68 = ($scope.user68 / 2)
													.toFixed(2);
											if ($scope.spouse68 < 0) {
												$scope.spouse68 = 0;
												$scope.spouseNegativeShow
														.push("68");
											}
										}

										if ($scope.spouse69 <= 0) {
											if ($scope.user69 > 0) {
												$scope.spouse69 = ($scope.user69 / 2)
														.toFixed(2);
											} else {
												$scope.spouse69 = 0;
												$scope.spouseNegativeShow
														.push("69");
											}
										} else if ($scope.spouse69 < $scope.user69 / 2) {
											$scope.spouse69 = ($scope.user69 / 2)
													.toFixed(2);
											if ($scope.spouse69 < 0) {
												$scope.spouse69 = 0;
												$scope.spouseNegativeShow
														.push("69");
											}
										}

										if ($scope.spouse70 <= 0) {
											if ($scope.user70 > 0) {
												$scope.spouse70 = ($scope.user70 / 2)
														.toFixed(2);
											} else {
												$scope.spouse70 = 0;
												$scope.spouseNegativeShow
														.push("70");
											}
										} else if ($scope.spouse70 < $scope.user70 / 2) {
											$scope.spouse70 = ($scope.user70 / 2)
													.toFixed(2);
											if ($scope.spouse70 < 0) {
												$scope.spouse70 = 0;
												$scope.spouseNegativeShow
														.push("70");
											}
										}

										if (formdata.spouseret_age == "62") {
											$scope.spouse62 = $scope.spousessb;
										} else if (formdata.spouseret_age == "63") {
											$scope.spouse63 = $scope.spousessb;
										} else if (formdata.spouseret_age == "64") {
											$scope.spouse64 = $scope.spousessb;
										} else if (formdata.spouseret_age == "65") {
											$scope.spouse65 = $scope.spousessb;
										} else if (formdata.spouseret_age == "66") {
											$scope.spouse66 = $scope.spousessb;
										} else if (formdata.spouseret_age == "67") {
											$scope.spouse67 = $scope.spousessb;
										} else if (formdata.spouseret_age == "68") {
											$scope.spouse68 = $scope.spousessb;
										} else if (formdata.spouseret_age == "69") {
											$scope.spouse69 = $scope.spousessb;
										} else {
											$scope.spouse70 = $scope.spousessb;
										}

										if ($scope.spouseNegativeShow.length > 0) {
											$scope.spouseNegative = true;
										} else {
											$scope.spouseNegative = false;
										}
									}
								} else if ($scope.inputData.action == "calculateSSBMarriedNotWorking"
										|| $scope.inputData.martialStatus == "Married") {
									if (result.data.spouseSSB.spousessb <= 0) {
										if (userSSBN) {
											spouseSSBN = true;
											/*
											 * $scope.errormsg = "User and
											 * Spouse SSB's are going negative";
											 * toaster.error({title: "", body:
											 * $scope.errormsg});
											 */
											$scope.userNegative = false;
											drawUser = false;
											drawSpouse = false;
										} else {
											drawSpouse = false;
											spouseSSBN = true;
											/*
											 * $scope.errormsg = "Spouse SSB is
											 * going negative";
											 * toaster.error({title: "", body:
											 * $scope.errormsg});
											 */
										}

										$scope.spouseSSB = false;
										$scope.spouseSSB2 = false;
										$scope.spouseSSB3 = false;
										$scope.spouseSSB4 = false;
										$scope.showSpouse62 = false;
										$scope.showSpouse66 = false;
										$scope.showSpouse70 = false;
										$scope.spouseNegative = false;
									} else {
										drawSpouse = false;
										$scope.spouseSSB = false;
										$scope.spouseSSB2 = false;
										$scope.spouseSSB3 = true;
										$scope.spouseSSB4 = false;
										$scope.showSpouse62 = false;
										$scope.showSpouse66 = false;
										$scope.showSpouse70 = false;
										$scope.spouseNegative = false;

										$scope.spouse62 = result.data.spouseSSB.Spouse62;
										$scope.spouse63 = result.data.spouseSSB.Spouse63;
										$scope.spouse64 = result.data.spouseSSB.Spouse64;
										$scope.spouse65 = result.data.spouseSSB.Spouse65;
										$scope.spouse66 = result.data.spouseSSB.Spouse66;
										$scope.spouse67 = result.data.spouseSSB.Spouse67;
										$scope.spouse68 = result.data.spouseSSB.Spouse68;
										$scope.spouse69 = result.data.spouseSSB.Spouse69;
										$scope.spouse70 = result.data.spouseSSB.Spouse70;

										$scope.spouseNegativeShow = [];
										if ($scope.spouse62 <= 0) {
											if ($scope.user62 > 0) {
												$scope.spouse62 = ($scope.user62 / 2)
														.toFixed(2);
											} else {
												$scope.spouse62 = 0;
											}
										} else if ($scope.spouse62 < $scope.user62 / 2) {
											$scope.spouse62 = ($scope.user62 / 2)
													.toFixed(2);
											if ($scope.spouse62 < 0) {
												$scope.spouse62 = 0;
											}
										}

										if ($scope.spouse63 <= 0) {
											if ($scope.user63 > 0) {
												$scope.spouse63 = ($scope.user63 / 2)
														.toFixed(2);
											} else {
												$scope.spouse63 = 0;
											}
										} else if ($scope.spouse63 < $scope.user63 / 2) {
											$scope.spouse63 = ($scope.user63 / 2)
													.toFixed(2);
											if ($scope.spouse63 < 0) {
												$scope.spouse63 = 0;
											}
										}

										if ($scope.spouse64 <= 0) {
											if ($scope.user64 > 0) {
												$scope.spouse64 = ($scope.user64 / 2)
														.toFixed(2);
											} else {
												$scope.spouse64 = 0;
											}
										} else if ($scope.spouse64 < $scope.user64 / 2) {
											$scope.spouse64 = ($scope.user64 / 2)
													.toFixed(2);
											if ($scope.spouse64 < 0) {
												$scope.spouse64 = 0;
											}
										}

										if ($scope.spouse65 <= 0) {
											if ($scope.user65 > 0) {
												$scope.spouse65 = ($scope.user65 / 2)
														.toFixed(2);
											} else {
												$scope.spouse65 = 0;
											}
										} else if ($scope.spouse65 < $scope.user65 / 2) {
											$scope.spouse65 = ($scope.user65 / 2)
													.toFixed(2);
											if ($scope.spouse65 < 0) {
												$scope.spouse65 = 0;
											}
										}

										if ($scope.spouse66 <= 0) {
											if ($scope.user66 > 0) {
												$scope.spouse66 = ($scope.user66 / 2)
														.toFixed(2);
											} else {
												$scope.spouse66 = 0;
											}
										} else if ($scope.spouse66 < $scope.user66 / 2) {
											$scope.spouse66 = ($scope.user66 / 2)
													.toFixed(2);
											if ($scope.spouse66 < 0) {
												$scope.spouse66 = 0;
											}
										}

										if ($scope.spouse67 <= 0) {
											if ($scope.user67 > 0) {
												$scope.spouse67 = ($scope.user67 / 2)
														.toFixed(2);
											} else {
												$scope.spouse67 = 0;
											}
										} else if ($scope.spouse67 < $scope.user67 / 2) {
											$scope.spouse67 = ($scope.user67 / 2)
													.toFixed(2);
											if ($scope.spouse67 < 0) {
												$scope.spouse67 = 0;
											}
										}

										if ($scope.spouse68 <= 0) {
											if ($scope.user68 > 0) {
												$scope.spouse68 = ($scope.user68 / 2)
														.toFixed(2);
											} else {
												$scope.spouse68 = 0;
											}
										} else if ($scope.spouse68 < $scope.user68 / 2) {
											$scope.spouse68 = ($scope.user68 / 2)
													.toFixed(2);
											if ($scope.spouse68 < 0) {
												$scope.spouse68 = 0;
											}
										}

										if ($scope.spouse69 <= 0) {
											if ($scope.user69 > 0) {
												$scope.spouse69 = ($scope.user69 / 2)
														.toFixed(2);
											} else {
												$scope.spouse69 = 0;
											}
										} else if ($scope.spouse69 < $scope.user69 / 2) {
											$scope.spouse69 = ($scope.user69 / 2)
													.toFixed(2);
											if ($scope.spouse69 < 0) {
												$scope.spouse69 = 0;
											}
										}

										if ($scope.spouse70 <= 0) {
											if ($scope.user70 > 0) {
												$scope.spouse70 = ($scope.user70 / 2)
														.toFixed(2);
											} else {
												$scope.spouse70 = 0;
											}
										} else if ($scope.spouse70 < $scope.user70 / 2) {
											$scope.spouse70 = ($scope.user70 / 2)
													.toFixed(2);
											if ($scope.spouse70 < 0) {
												$scope.spouse70 = 0;
											}
										}

										if (formdata.ret_age == "62") {
											$scope.spousessb = $scope.spouse62;
										} else if (formdata.ret_age == "63") {
											$scope.spousessb = $scope.spouse63;
										} else if (formdata.ret_age == "64") {
											$scope.spousessb = $scope.spouse64;
										} else if (formdata.ret_age == "65") {
											$scope.spousessb = $scope.spouse65;
										} else if (formdata.ret_age == "66") {
											$scope.spousessb = $scope.spouse66;
										} else if (formdata.ret_age == "67") {
											$scope.spousessb = $scope.spouse67;
										} else if (formdata.ret_age == "68") {
											$scope.spousessb = $scope.spouse68;
										} else if (formdata.ret_age == "69") {
											$scope.spousessb = $scope.spouse69;
										} else {
											$scope.spousessb = $scope.spouse70;
										}
									}
								}

								if (userSSBN && spouseSSBN) {
									$scope.errormsg = "User and Spouse SSB's are going negative";
									// toaster.error({title: "", body:
									// $scope.errormsg});
									// toaster.pop('error', "", "{template:
									// 'myTemplateWithData.html', data: '" +
									// $scope.errormsg + "' }", 3000,
									// 'templateWithData');
								} else if (userSSBN) {
									$scope.errormsg = "User SSB is going negative";
									// toaster.error({title: "", body:
									// $scope.errormsg});
									// toaster.pop('error', "", "{template:
									// 'myTemplateWithData.html', data: '" +
									// $scope.errormsg + "' }", 3000,
									// 'templateWithData');
								} else if (spouseSSBN) {
									$scope.errormsg = "Spouse SSB is going negative";
									// toaster.error({title: "", body:
									// $scope.errormsg});
									// toaster.pop('error', "", "{template:
									// 'myTemplateWithData.html', data: '" +
									// $scope.errormsg + "' }", 3000,
									// 'templateWithData');
								}
								$scope.userSSBArr = [];
								$scope.spouseSSBArr = [];
								var minValueUser = 0;
								var minValueSpouse = 0;
								var minValue = 0;
								var maxValueUser = 0;
								var maxValue = 0;
								var maxValueSpouse = 0;
								$scope.showChart = false;
								$scope.userspousessbObject = [];

								if (drawUser) {
									$scope.userSSBArr.push({
										x : 62,
										y : $scope.user62
									});
									$scope.userSSBArr.push({
										x : 63,
										y : $scope.user63
									});
									$scope.userSSBArr.push({
										x : 64,
										y : $scope.user64
									});
									$scope.userSSBArr.push({
										x : 65,
										y : $scope.user65
									});
									$scope.userSSBArr.push({
										x : 66,
										y : $scope.user66
									});
									$scope.userSSBArr.push({
										x : 67,
										y : $scope.user67
									});
									$scope.userSSBArr.push({
										x : 68,
										y : $scope.user68
									});
									$scope.userSSBArr.push({
										x : 69,
										y : $scope.user69
									});
									$scope.userSSBArr.push({
										x : 70,
										y : $scope.user70
									});

									$scope.tempuserSSBArr = $scope.userSSBArr;
									$scope.tempuserSSBArr.sort(function(a, b) {
										if (Number(a.y) > Number(b.y)) {
											return -1;
										} else if (Number(a.y) < Number(b.y)) {
											return 1;
										} else {
											return 0;
										}
									});

									minValueUser = Math
											.ceil($scope.tempuserSSBArr[$scope.tempuserSSBArr.length - 1].y / 100) * 100;
									maxValueUser = Math
											.ceil($scope.tempuserSSBArr[0].y / 100) * 100;

									$scope.userspousessbObject = [ {
										"values" : $scope.userSSBArr,
										"key" : "User SSB",
										color : "#328de4",
										strokeWidth : 3
									} ];
									$scope.showChart = true;
								}

								if (drawSpouse) {
									$scope.spouseSSBArr.push({
										x : 62,
										y : $scope.spouse62
									});
									$scope.spouseSSBArr.push({
										x : 63,
										y : $scope.spouse63
									});
									$scope.spouseSSBArr.push({
										x : 64,
										y : $scope.spouse64
									});
									$scope.spouseSSBArr.push({
										x : 65,
										y : $scope.spouse65
									});
									$scope.spouseSSBArr.push({
										x : 66,
										y : $scope.spouse66
									});
									$scope.spouseSSBArr.push({
										x : 67,
										y : $scope.spouse67
									});
									$scope.spouseSSBArr.push({
										x : 68,
										y : $scope.spouse68
									});
									$scope.spouseSSBArr.push({
										x : 69,
										y : $scope.spouse69
									});
									$scope.spouseSSBArr.push({
										x : 70,
										y : $scope.spouse70
									});
									$scope.tempspouseSSBArr = $scope.spouseSSBArr;
									$scope.userspousessbObject = [ {
										"values" : $scope.spouseSSBArr,
										"key" : "Spouse SSB",
										color : "#e43234",
										strokeWidth : 3
									} ];
									$scope.tempspouseSSBArr
											.sort(function(a, b) {
												if (Number(a.y) > Number(b.y)) {
													return -1;
												} else if (Number(a.y) < Number(b.y)) {
													return 1;
												} else {
													return 0;
												}
											});
									minValueSpouse = Math
											.ceil($scope.tempspouseSSBArr[$scope.tempspouseSSBArr.length - 1].y / 100) * 100;
									maxValueSpouse = Math
											.ceil($scope.tempspouseSSBArr[0].y / 100) * 100;

									$scope.showChart = true;
								}

								if (drawUser && drawSpouse) {
									$scope.userspousessbObject = [ {
										"values" : $scope.userSSBArr,
										"key" : "User SSB",
										color : "#328de4",
										strokeWidth : 3
									}, {
										"values" : $scope.spouseSSBArr,
										"key" : "Spouse SSB",
										color : "#e43234",
										strokeWidth : 3
									} ];
									$scope.showChart = true;
								}

								if (minValueSpouse > 0
										&& minValueSpouse < minValueUser) {
									minValue = minValueSpouse;
								} else {
									minValue = minValueUser;
								}

								if (maxValueSpouse > 0
										&& maxValueSpouse > maxValueUser) {
									maxValue = maxValueSpouse;
								} else {
									maxValue = maxValueUser;
								}

								$scope.optionsSSB = {
									chart : {
										zoom : {
											enabled : true,
											scaleExtent : [ 1, 10 ],
											useFixedDomain : false,
											useNiceScale : false,
											horizontalOff : false,
											verticalOff : false,
											unzoomEventType : "dblclick.zoom"
										},
										type : "lineChart",
										height : 420,
										margin : {
											top : 20,
											right : 20,
											bottom : 40,
											left : 55
										},
										x : function(d) {
											return d.x;
										},
										y : function(d) {
											return d.y;
										},
										forceY : [ minValue - 1000,
												maxValue + 1000 ],
										forceX : [ 61.5, 70.5 ],
										dispatch : {
											stateChange : function(e) {
												console.log("stateChange");
											},
											changeState : function(e) {
												console.log("changeState");
											},
											tooltipShow : function(e) {
												console.log("tooltipShow");
											},
											tooltipHide : function(e) {
												console.log("tooltipHide");
											}
										},
										useInteractiveGuideline : false,
										xAxis : {
											axisLabel : "Retirement Age",
											showMaxMin : false,
										// axisLabelDistance: 6
										},
										yAxis : {
											showMaxMin : false,
											axisLabel : "SSB",
											tickFormat : function(d) {
												return d3.format(".02f")(d);
											},
											axisLabelDistance : 40

										},
										callback : function(chart) {
										}
									},
									title : {
										enable : false,
										text : "Line Chart"
									},
								};
								$scope.dataSSB = $scope.userspousessbObject;
							}

						}, function(error) {
							console.log("Fail");
						});
	};
}