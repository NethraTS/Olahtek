angular.module('wealthsetter').controller(
		'IncometaxCtrl',
		[ '$scope', '$rootScope', '$uibModal', '$log', '$http', '$state',
				'$timeout', 'toaster', IncometaxCtrl ]);
function IncometaxCtrl($scope, $rootScope, $uibModal, $log, $http, $state,
		$timeout, toaster, $uibModalInstance) {
	$.material.init();
	$rootScope.boolChangeClass = true;
	$scope.$watch('loginLogoutText', function() {
		console.log($rootScope.loginLogoutText);
	});

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
	// $scope.fin_name = (decodeURIComponent(hashes));
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
	$scope.fICAMedicareTax = 0;
	$scope.fICASocialSecurityTax = 0;
	$scope.TotalIncomeTaxes = 0;
	$scope.TotalFDI = 0;
	$scope.incomeAfterTaxes = 0;
	$scope.federalTax = 0;
	$scope.Taxliability = 0;
	$scope.stateTax = 0;
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
	$scope.calculateFdi = false;
	$scope.retirementAge = [ '62', '63', '64', '65', '66', '67', '68', '69',
			'70' ];
	$scope.userSSB = false;
	$scope.guest = false;
	$rootScope.userFlag = true;
	$scope.show1 = false;
	$scope.show2 = false;
	$scope.show3 = false;
	$scope.show4 = false;

	$scope.incomeProfile = [];
	$scope.filingOptions = [ {
		id : 'Single'
	}, {
		id : 'Head of Household'
	}, {
		id : 'Married Filing Jointly'
	} ];
	$scope.filingOptions1 = [ {
		id : 'Risk aggressive'
	}, {
		id : 'Risk neutral'
	}, {
		id : 'Risk Adverse'
	} ];
	$scope.load = function() {

		$scope.formdata.state = 'ALABAMA';
		$scope.formdata.filingStatus = 'Single';
		$scope.formdata.homeValue = '75000';
		$scope.formdata.personalexe = '1';
		$scope.TotalFDI = "5000";
		$scope.fICAMedicareTax = '20000';
		$scope.fICASocialSecurityTax = '2000';
		$scope.stateTax = '3000';
		$scope.TotalIncomeTaxes = '60000';
		$scope.incomeAfterTaxes = '60000';
		$scope.Taxliability = '600000';
		$scope.federalTax = '5000';
		$scope.user_id = "";
		// $scope.drawChart(federalTax,fICAMedicareTax,fICASocialSecurityTax,stateTax);

		$scope.sessionDetails.cookieId = readCookie("AhTwxlO");
		$scope.sessionDetails.lastVisitedPage = document.URL;
		if ($scope.sessionDetails.cookieId != null) {
			$http({
				method : 'POST',
				url : 'CheckSession',
				data : $.param($scope.sessionDetails),
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).then(function(result) {

				console.log(result.data);
				if (result.data.status == "success") {
					$scope.guest = false;
					$rootScope.userFlag = true;
					$scope.user_id = result.data.user_id;
					$rootScope.homeLocation = "dashboard.home";
				} else {
					$scope.guest = true;
					$rootScope.userFlag = false;
					$scope.user_id = "";
					$rootScope.homeLocation = "index";
				}

			}, function(error) {
			});
		} else {
			$scope.user_id = "";
			$scope.guest = true;
			$rootScope.userFlag = false;
			$rootScope.homeLocation = "index";

		}
		$scope.calculate();
		$scope.drawChart(5000, 20000, 2000, 3000);

		// alert("load-->>> ");
		/*
		 * $scope.sessionDetails.cookieId = readCookie("AhTwxlO");
		 * $scope.sessionDetails.lastVisitedPage = document.URL;
		 * 
		 * if ($scope.sessionDetails.cookieId != null) { $http( { method :
		 * 'POST', url : 'CheckSession', data : $.param($scope.sessionDetails),
		 * headers : { 'Content-Type' : 'application/x-www-form-urlencoded' } })
		 * .then( function(result) { console.log(result.data); if
		 * (result.data.status == "success") { $scope.user_id =
		 * result.data.user_id; console.log($scope.user_id); if
		 * (result.data.lastVisitedPage == "undefined") { window.location.href =
		 * "dashboardUserr0.jsp"; } else { document.cookie = "lastVisitedPage=" +
		 * result.data.lastVisitedPage; if (result.data.lastVisitedPage ==
		 * document.URL) { } } } else { $scope.errMessage = "Session got
		 * expired"; $("#checkSession").modal("show"); var delay = 3000; // Your
		 * setTimeout( function() { $scope .deleteAllCookies() }, delay); }
		 * 
		 * $scope.masked = false; console.log($scope.masked); }, function(error) {
		 * }); } else { // ////alert("Session got expired");
		 * $scope.deleteAllCookies(); window.location.href = "index.jsp"; }
		 */

	}

	$scope.checkSave = function() {

		if ($scope.saveFlag == 1) {
			$('#myModalback').modal('show');

		} else {
			window.location.href = "userProfile.jsp";
		}
	}
	$scope.deleteAllCookies = function() {
		// ////alert("delete all cookies");

		$scope.sessionDelete.sessionID = readCookie("AhTwxlO");
		$http({
			method : 'POST',
			url : 'Logout',
			data : $.param($scope.sessionDelete),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(result) {
			// ////alert("Session Got deleted");

			window.location.href = "index.jsp";

		}, function(error) {
			// ////alert("Session not deleted");

		});
	}

	function readCookie(name) {
		// ////alert("hi");
		var nameEQ = name + "=";
		var ca = document.cookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ')
				c = c.substring(1, c.length);
			if (c.indexOf(nameEQ) == 0)
				return c.substring(nameEQ.length, c.length);
		}
		return null;
	}

	$scope.report = function() {

		if ($scope.Count == 0) {
			$scope.SuccessMessage1 = "Currently there are no plans to show the reports";
			window.location.href = "#";
			$("#report-alert").show();
			$("#report-alert").fadeTo(2000, 300).slideUp(400, function() {
				$("#report-alert").hide();

			});

		} else {
			window.location.href = "Report.jsp";
		}
	}
	$scope.update2 = function() {
		if ($scope.formdata.filingStatus == "Single"
				|| $scope.formdata.filingStatus == "Head of Household") {
			$scope.formdata.personalexe = "1";
		} else if ($scope.formdata.filingStatus == "Married Filing Jointly")
			$scope.formdata.personalexe = "2";
	}

	$scope.update3 = function() {
		// alert($scope.formdata.personalexe);
		if ($scope.formdata.filingStatus == "Single"
				|| $scope.formdata.filingStatus == "Head of Household") {
			if ($scope.formdata.personalexe < 1) {
				$scope.errormsg = " Please enter the Personal Exemption greater than 1"
			}

		}

		else if ($scope.formdata.filingStatus == "Married Filing Jointly") {
			if ($scope.formdata.personalexe < 2) {
				$scope.errormsg = " Please Enter the Personal Exemption greater than 2"
			} else {
				$scope.errormsg = "";
			}
		}

	}

	$scope.update4 = function() {
		if ($scope.formdata.homeValue < 1) {
			$scope.errormsg = "Please Enter vaild Gross annual income"
		} else {
			$scope.errormsg = "";
		}

	}

	$scope.refresh = function() {

		window.location.reload();

	}
	$scope.inputData = {};
	var age = 0;
	$scope.errormsg = "";

	$scope.Fdi = function(formdata)

	{

		if ($scope.formdata.state == "" || $scope.formdata.state == undefined) {

			$scope.errormsg = "Please choose State";
		} else if ($scope.formdata.Filling_Status == ""
				|| $scope.formdata.Filling_Status == undefined) {

			$scope.errormsg = "Please choose Status";
		} else if ($scope.formdata.Gross_Annual_Income == ""
				|| $scope.formdata.Gross_Annual_Income == undefined) {

			$scope.errormsg = "Please Enter Gross Annual Income ";
		} else if ($scope.formdata.Personal_Exemption == ""
				|| $scope.formdata.Personal_Exemption == undefined) {

			$scope.errormsg = "Please Enter Personal Exemption  ";
		}
	}

	$scope.calculate = function() {
		if ($scope.formdata.state == "" || $scope.formdata.state == undefined) {

			$scope.show1 = true;
			$scope.errormsg = "Please choose state";

		} else if ($scope.formdata.filingStatus == ""
				|| $scope.formdata.filingStatus == undefined) {

			$scope.show2 = true;
			$scope.errormsg = "Please choose filingstatus";

		} else if ($scope.formdata.homeValue == ""
				|| $scope.formdata.homeValue == undefined) {

			$scope.show3 = true;
			//console.log($scope.incomeTaxForm.grossincome);
			//$scope.incomeTaxForm.grossincome.$invalid = true;
			$scope.errormsg = "Please enter gross annual income ";

		} else if ($scope.formdata.personalexe == ""
				|| $scope.formdata.personalexe == undefined) {

			$scope.show4 = true;
			$scope.errormsg = "Please enter personal exeption  ";
			
		} else {
			$scope.errormsg = "";
			$scope.show1 = false;
			$scope.show2 = false;
			$scope.show3 = false;
			$scope.show4 = false;
			// alert("--->> ");
			$scope.inputData.user_id = $scope.user_id;
			$scope.inputData.action = "FDI";
			$scope.inputData.state = $scope.formdata.state;
			$scope.inputData.filingStatus = $scope.formdata.filingStatus;
			$scope.inputData.homeValue = $scope.formdata.homeValue;
			$scope.inputData.personalexe = $scope.formdata.personalexe;
			// console.log($scope.inputData);
			$http({
				method : 'POST',
				url : 'FDICalculator',
				data : $.param($scope.inputData),
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			})
					.then(
							function(result) {
								console.log(result.data);

								// alert(JSON.stringify(result.data.federal));
								if (result.data.status == "success") {
									// alert("hii");
									// alert(result.data.federal.federalTax);
									$scope.federalTax = result.data.federal.federalTax
											.toFixed(0);
									// alert(result.data.federal.fICAMedicareTax);
									$scope.fICAMedicareTax = result.data.federal.fICAMedicareTax
											.toFixed(0);
									$scope.fICASocialSecurityTax = result.data.federal.fICASocialSecurityTax
											.toFixed(0);
									// alert(
									// result.data.federal.stateTax.toFixed(0));
									$scope.stateTax = result.data.federal.stateTax
											.toFixed(0);
									$scope.TotalIncomeTaxes = (result.data.federal.federalTax
											+ result.data.federal.fICAMedicareTax
											+ result.data.federal.fICASocialSecurityTax + result.data.federal.stateTax)
											.toFixed(0);
									$scope.TotalFDI = (result.data.federal.federalTax + result.data.federal.fICASocialSecurityTax)
											.toFixed(0);
									$scope.incomeAfterTaxes = (result.data.income
											- result.data.federal.federalTax
											- result.data.federal.fICAMedicareTax
											- result.data.federal.fICASocialSecurityTax - result.data.federal.stateTax)
											.toFixed(0);
									// alert(result.data.federal.maxDeduction);
									$scope.Taxliability = (result.data.income
											- result.data.federal.maxDeduction - result.data.federal.exemptions)
											.toFixed(0);

									// alert(result.data.federal.maxDeduction);

									// $scope.federalTax=result.data.;
									// $scope.calculate();
									// alert($scope.Taxliability);
								}

								// {

								// $scope.federalTax = $scope.data.federalTax;
								// $scope.FICA = result.data.FICA;
								// $scope.stateTax = result.data.stateTax;
								// $scope.Medicare = result.data.Medicare;
								/*
								 * $scope.Tax_liability_before_credits =
								 * result.data.Tax_liability_before_credits;
								 * $scope.Total_Income_Taxes =
								 * result.data.Total_Income _axes; $scope.Incom_
								 * After_Taxes = result.data.Income_After_Taxes;
								 */

								// console.log($scope.showValues);
								// $scope.showValues = true;
								// }
								// }
								federalTax = result.data.federal.federalTax;
								fICAMedicareTax = result.data.federal.fICAMedicareTax;
								fICASocialSecurityTax = result.data.federal.fICASocialSecurityTax;
								stateTax = result.data.federal.stateTax;
								// alert("hjhjh");
								// alert(federalTax);
								$scope.drawChart(federalTax, fICAMedicareTax,
										fICASocialSecurityTax, stateTax);

								if ($scope.formdata.filingStatus == "Single"
										|| $scope.formdata.filingStatus == "Head of Household") {
									if ($scope.formdata.personalexe < 1
											|| $scope.formdata.personalexe == ""
											|| $scope.formdata.personalexe == undefined) {
										$scope.errormsg = " Please enter the Personal Exemption greater than 1"

										$scope.federalTax = 0;
										// alert(result.data.federal.fICAMedicareTax);
										$scope.fICAMedicareTax = 0;
										$scope.fICASocialSecurityTax = 0;
										$scope.stateTax = 0;
										$scope.TotalIncomeTaxes = 0;
										$scope.TotalFDI = 0;
										$scope.incomeAfterTaxes = 0;
										// alert(result.data.federal.maxDeduction);
										$scope.Taxliability = 0;

									} else {
										$scope.errormsg = "";
									}
								}

								else if ($scope.formdata.filingStatus == "Married Filing Jointly") {
									if ($scope.formdata.personalexe < 2
											|| $scope.formdata.personalexe == ""
											|| $scope.formdata.personalexe == undefined) {
										$scope.errormsg = " Please Enter the Personal Exemption greater than 2"

										$scope.federalTax = 0;
										// alert(result.data.federal.fICAMedicareTax);
										$scope.fICAMedicareTax = 0;
										$scope.fICASocialSecurityTax = 0;
										$scope.stateTax = 0;
										$scope.TotalIncomeTaxes = 0;
										$scope.TotalFDI = 0;
										$scope.incomeAfterTaxes = 0;
										// alert(result.data.federal.maxDeduction);
										$scope.Taxliability = 0;

									} else {
										$scope.errormsg = "";
									}
								}

								if ($scope.Taxliability < 0)

								{
									$scope.errormsg = "FDI Tax Calculation goes negative!! Please reenter the valid gross annual income"
									$scope.Taxliability = 0;
								} else {
									$scope.errormsg = "";
								}

							}, function(error) {
								alert("Session not deleted");

							});

		}

	}

	$scope.drawChart = function(a, b, c, d) {
		$scope.options = {
			chart : {
				type : 'pieChart',
				height : 450,
				donut : true,
				x : function(d) {
					return d.key;
				},
				y : function(d) {
					return d.y;
				},
				showLabels : true,
				pie : {
					// startAngle: function(d) { return d.startAngle -Math.PI},
					// endAngle: function(d) { return d.endAngle -Math.PI },
					color : function(d, i) {
						var key = i === undefined ? d : i;
						return d.color || color_scale(key);
					},
					labelType : "percent",
				},

				duration : 500,
				legend : {
					margin : {
						top : 5,
						right : 70,
						bottom : 5,
						left : 0
					}
				}
			}
		};

		$scope.data = [ {
			key : "Federal Tax",
			y : a,
			color : "rgb(34, 79, 231)"
		}, {
			key : "FICAMedicareTax",
			y : b,
			color : "rgb(248, 99, 50)"
		}, {
			key : "FICASocialSecurityTax",
			y : c,
			color : "rgb(255, 176, 55)"
		}, {
			key : "State Tax",
			y : d,
			color : "rgb(13, 205, 120)"
		} ];
	}

	$scope.resetForm = function() {
		$scope.formdata.personalexe = "";
		$scope.formdata.homeValue = "";
		$scope.formdata.filingStatus = "";
		$scope.formdata.state = "";
		$scope.errormsg = "";
		$scope.show1 = true;
		$scope.show2 = true;
		$scope.show3 = true;
		$scope.show4 = true;
	}

	$scope.report = function() {

		if ($scope.Count == 0) {
			$scope.SuccessMessage1 = "Currently there are no plans to show the reports";
			window.location.href = "#";
			$("#report-alert").show();
			$("#report-alert").fadeTo(2000, 300).slideUp(400, function() {
				$("#report-alert").hide();

			});

		} else {
			window.location.href = "Report.jsp";
		}
	}

}