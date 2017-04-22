/**
 * 
 */$('#chartDetails').on('shown.bs.modal', function() {
	$(this).find('.modal-dialog').css({
		width : 'auto',
		height : 'auto',
		'max-height' : '100%'
	});
});

function onlyAlphaNumbers(e, t) {
	var len = t.value.length;
	try {
		if (window.event) {
			var charCode = window.event.keyCode;
		} else if (e) {
			var charCode = e.which;
		} else {
			return true;
		}
		if ((charCode > 64 && charCode < 91)
				|| (charCode > 96 && charCode < 123) || charCode == 8
				|| charCode == 127 || charCode == 32 || charCode == 95
				|| charCode == 45 || (charCode >= 48 && charCode <= 57)
				|| charCode == 40 || charCode == 41) {
			if (len == 250) {
				if (charCode == 8) {
					return true;
				} else {
					// //alert('Only 25 characters allowed');
					return false;
				}
			} else {
				return true;
			}
		} else
			return false;
	} catch (err) {
		// //alert(err.Description);
	}
}

var app = angular.module('dashboard', []);

app
		.directive(
				'loading',
				function() {
					return {
						restrict : 'E',
						replace : true,
						template : '<div class="loading" width="50%" height="50%"><img src="http://www.nasa.gov/multimedia/videogallery/ajax-loader.gif" width="20" height="20" />LOADING...</div>',
						link : function(scope, element, attr) {
							scope.$watch('loading', function(val) {
								if (val)
									$(element).show();
								else
									$(element).hide();
							});
						}
					}
				})
var url = window.location.href;
var hashes = url.split("=")[1];
//alert(hashes);
app.controller(
				'dashboardController',
				function($http, $scope) {
					$scope.messageChangeIncome = "";
					$scope.ages = [];
					$scope.preChangedValue;
					$scope.preStartIndex;
					$scope.preEndIndex;
					$scope.checkDrag = 0;
					$scope.checkDrag1 = 0;
					$scope.preChangedValue1;
					$scope.preStartIndex1;
					$scope.Math = window.Math;
					$scope.preEndIndex1;
					$scope.profileNameCheckbox = "";
					$scope.user_id = (decodeURIComponent(hashes));
				//	alert($scope.user_id);
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
					$scope.selected = 0;
					$scope.completedStatus=0;
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
					$scope.nextdtIndex = "";
					$scope.CreatePlanbutton = true;
					$scope.formdata.checkValue = false;
					$scope.hideList = true;
					$scope.CopyPlanbutton = false;
					$scope.deleteAllCookies = function() {
						// ////alert("delete all cookies");

						$scope.sessionDelete.sessionID = readCookie("AhTwxlO");
						$http(
								{
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

					$scope.load = function() {
						// ////alert("hello");

						$scope.sessionDetails.cookieId = readCookie("AhTwxlO");
						// ////alert( $scope.sessionDetails.cookieId);
						$scope.sessionDetails.lastVisitedPage = document.URL;
						// ////alert( $scope.sessionDetails.lastVisitedPage);
						if ($scope.sessionDetails.cookieId != null) {

							$http(
									{
										method : 'POST',
										url : 'CheckSession',
										data : $.param($scope.sessionDetails),
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									})
									.then(
											function(result) {

												if (result.data.status == "success") {
													// ////alert("Cookie ajax
													// Success");
													// //alert(result.data.lastVisitedPage);
													if (result.data.lastVisitedPage == "undefined") {

														window.location.href = "dashboardUserr0.jsp";

													} else {
														document.cookie = "lastVisitedPage="
																+ result.data.lastVisitedPage;
														if (result.data.lastVisitedPage == document.URL) {
															// ////alert("redirecting
															// since in db there
															// is another
															// lastvisited
															// page")
															// window.location.href=result.data.lastVisitedPage;
														}

													}
													$scope.load1();

												} else {

													$scope.errMessage = "Session got expired";
													$("#checkSession").modal(
															"show");
													var delay = 3000; // Your
																		// delay
																		// in
																		// milliseconds
													setTimeout(
															function() {
																$scope
																		.deleteAllCookies()
															}, delay);

												}

											}, function(error) {

												// ////alert("Cokkie ajax
												// Fail");
											});
						} else {
							// ////alert("Session got expired");
							$scope.deleteAllCookies();
							window.location.href = "index.jsp";
						}

					}

					$scope.load1 = function() {
						// ////alert("calling load1 function");
						$scope.loading = true;
						$scope.tableIncome = [];
						$scope.planNames = [];
						$scope.planNames1 = [];
						$scope.dragPoint1 = [];
						$scope.dragPoint = [];
						$scope.chartIncomeSpouse = [];
						$scope.chartYear = [];
						$scope.chartIncome = [];
						$scope.combined_income = [];
						$scope.chartIncomeSave = [];
						$scope.spouseIncomeSave = [];
						$scope.chartYearSave = [];
						$scope.assets = [];
						$scope.tax = [];
						$scope.assetGoals = [];

						$("#success-alert").hide();
						$scope.createPlan = true;
						$scope.Changedyear = "";
						$scope.formdata.form = "showPlan";
						$scope.formdata.user_id=$scope.user_id;
						$http(
								{
									method : 'POST',
									url : 'DashboardAdmin',
									data : $.param($scope.formdata),
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									}
								})
								.then(
										function(result) {
										//	alert("Success");
											$scope.completedStatus=result.data.completedStatus;
											if($scope.completedStatus==0)
												{
												$scope.registrationIncomplete=true;
												$scope.registrationComplete=false;
												}
											else
												{
												$scope.registrationIncomplete=false;
												$scope.registrationComplete=true;
												
												}
												
										
											$scope.incomeProfiles = result.data.income_profiles;

											$scope.planNames = result.data.Plans;
											$scope.Count = result.data.Planscount;
											$scope.userName =result.data.userName;

											// $scope.expense=result.data.expenses;
											// alert(result.data.income.length);

											$scope.income = result.data.income;
											$scope.birthYear = result.data.birthYear;
											$scope.spouseIncome1 = result.data.spouseIncome;
											$max = 0;

											/*
											 * if(result.data.marital_status=="Yes") {
											 * 
											 * $max=$scope.income.length;
											 *  } else if($scope.spouseIncome<$scope.income.length) {
											 * 
											 * $max=$scope.income.length;
											 *  } else
											 * if($scope.spouseIncome>$scope.income.length) {
											 * $max=$scope.spouseIncome.length;
											 *  } else {
											 * $max=$scope.income.length; }
											 */
											$scope.tax = result.data.tax;
					//						alert(JSON.stringify($scope.tax));
											$scope.assets = result.data.assests;
											$scope.combined_income = result.data.combined_income;
											// alert(JSON.stringify($scope.combined_income));
											$scope.marital_status = result.data.marital_status;
											// //alert($scope.birthYear);
											$scope.age = result.data.age;
											// //alert($scope.age);

											for (var i = (($scope.age * 1) + (1 * 1)); i < 96; i++) {

												$scope.ages.push({
													number : i
												});

											}

											$scope.startAge = 70;
											// //alert($scope.birthYear)
											$scope.monthlyExpense = result.data.MonthlyExpense;
											$scope.rentalExpense = result.data.MonthlyRentalExpense;
											$scope.totalYearlyExpense = (($scope.monthlyExpense * 1) + ($scope.rentalExpense * 1)) * 12;
											$k = 0;
											// alert($scope.income.length)
											$scope.spouseIncome=[];
											for (var i = 0; i < $scope.income.length; i++) {

												$scope.chartYearExpense
														.push({
															label : $scope.income[i].year
														});
												$scope.chartYearSave
														.push({
															label : $scope.income[i].year
																	.toString()
														});
												$scope.chartIncomeSave
														.push({
															value : $scope.income[i].value,
															year : $scope.income[i].year
														});
												$scope.ExpenseIncome
														.push({
															value : $scope.totalYearlyExpense,
															year : $scope.income[i].year
														});

												if (result.data.marital_status == "Yes") {
													$scope.chartCombinedIncome
															.push({
																value : $scope.combined_income[i].value,
																"allowDrag" : "0"
															})
													$scope.spouseIncome
															.push({
																value : $scope.spouseIncome1[i].value,
																year : $scope.spouseIncome1[i].year
															});

													$scope.spouseIncomeSave
															.push({
																value : $scope.spouseIncome[i].value
															});
													
													if ($scope.income[i].year == $scope.tax[i].year
															&& $scope.income[i].year == $scope.assets[i].year) {
														$scope.tableIncome
																.push({
																	id : $scope.age,
																	value : $scope.income[i].value,
																	year : $scope.income[i].year
																			.toString(),
																	expense : $scope.totalYearlyExpense,
																	spouseValue : $scope.spouseIncome[i].value,
																	federalTax : $scope.tax[i].federalTax,
																	userfICASocialSecurityTax : $scope.tax[i].userSSTax,
																	spousefICASocialSecurityTax : $scope.tax[i].spouseSSTax,
																	stateTax : $scope.tax[i].stateTax,
																	fICAMedicareTax : $scope.tax[i].fICAMedicareTax,
																	savings : $scope.assets[i].savings,
																	taxable_investment_amount : $scope.assets[i].taxable_investment_amount,
																	nontaxable_investment_amount : $scope.assets[i].nontaxable_investment_amount
																});
													
														$scope.sum = $scope.assets[i].savings
																* 1
																+ $scope.assets[i].taxable_investment_amount
																* 1
																+ $scope.assets[i].nontaxable_investment_amount
																* 1;
														$scope.chartAssets
																.push({
																	value : $scope.sum,
																	"allowDrag" : "0"
																})
													}
												} else {

													if ($scope.income[i].year == $scope.tax[i].year
															&& $scope.income[i].year == $scope.assets[i].year) {
														$scope.tableIncome
																.push({
																	id : $scope.age,
																	value : $scope.income[i].value,
																	year : $scope.income[i].year
																			.toString(),
																	expense : $scope.totalYearlyExpense,
																	federalTax : $scope.tax[i].federalTax,
																	userfICASocialSecurityTax : $scope.tax[i].userSSTax,
																	spousefICASocialSecurityTax : $scope.tax[i].spouseSSTax,
																	stateTax : $scope.tax[i].stateTax,
																	fICAMedicareTax : $scope.tax[i].fICAMedicareTax,
																	savings : $scope.assets[i].savings,
																	taxable_investment_amount : $scope.assets[i].taxable_investment_amount,
																	nontaxable_investment_amount : $scope.assets[i].nontaxable_investment_amount
																});
														
														$scope.sum = $scope.assets[i].savings
																* 1
																+ $scope.assets[i].taxable_investment_amount
																* 1
																+ $scope.assets[i].nontaxable_investment_amount
																* 1;
														$scope.chartAssets
																.push({
																	value : $scope.sum,
																	"allowDrag" : "0"
																})
													}

												}
												$scope.age++;
											}

											if ($scope.Count == 0) {
												$scope.incomeProfilesChart = "constant_income";
												$scope.planCountOncopy = false;
												$scope.planCountOnload = false;
												$scope.notNewUser = false;
												$scope.plansExist = false;
												$scope.checkboxData.applyPlan = false;
												$scope.messagehome = "We extend your income and expense to when you are seventy year old, you can drag the graph to change the future incomes and expenses as you like.";
											} else {
												$scope.checkboxData.applyPlan = true;
												$scope.plansExist = true;
												$scope.planCountOncopy = true;
												$scope.planCountOnload = true;
												$scope.notNewUser = true;
												$scope.messagehome = "We extend your income and expense to when you are seventy year old, you can drag the graph to change the future incomes and expenses as you like.";
												for (var i = 0; i < $scope.Count; i++) {

													$scope.planNames1
															.push({
																name : $scope.planNames[i],
																profileName : result.data.incomeProfile[i]
															});
													if ($scope.fin_name != undefined) {

														if ($scope.fin_name == $scope.planNames1[i].name) {
															$scope.position = i;
															// //alert(i);
														}

													}
												}
												// //alert($scope.planNames1[0].name);
												$scope.firstPlan = ($scope.planNames1[0].name);
												$scope.firstProfile = ($scope.planNames1[0].profileName);

												$scope.finplan_name = $scope.firstPlan;
												// //alert($scope.fin_name)
												if ($scope.fin_name == undefined) {
													// //alert("hi");
													$scope
															.fetchPlanDetails($scope.firstPlan);
													$scope
															.changeIncomeProfile($scope.firstProfile);
													$scope.incomeProfilesChart = $scope.firstProfile;
												} else {

													// $scope.fetchPlanDetails($scope.fin_name);
													$scope.planchange(
															$scope.fin_name,
															$scope.position);

												}

											}

											// alert($scope.profileNameCheckbox);
											if ($scope.profileNameCheckbox != "") {
												$scope.incomeProfilesChart = $scope.profileNameCheckbox;
												$scope
														.changeIncomeProfile($scope.profileNameCheckbox);
											}

											$scope.plotGraph($scope.startAge);

											$scope
													.plotExpense($scope.ExpenseIncome);
											$scope
													.plotAssetGoals($scope.assetGoals);
											if (result.data.marital_status == "Yes") {
												$scope.nospouse = true;
												$scope
														.plotGraphSpouse($scope.startAge);
											}
											editAssetChart();
											$scope.loading = false;

										}, function(error) {
											//alert("Fail");
											// $scope.message=result.data.status;

										});

					}
					
					$scope.changeAge=function()
					{
						Array.prototype.splice.apply($scope.income, [ 0,
										$scope.chartIncome.length ]
										.concat($scope.chartIncome));
						//alert(JSON.stringify($scope.income));
						if ($scope.marital_status == "Yes") {
							
						
						Array.prototype.splice
						.apply(
								$scope.spouseIncome,
								[
										0,
										$scope.chartIncomeSpouse.length ]
										.concat($scope.chartIncomeSpouse));
						
						//alert(JSON.stringify($scope.spouseIncome));
					}
					//alert($scope.startAge);
						
	
						$scope.plotGraph($scope.startAge);
						$scope.plotGraphSpouse($scope.startAge);
						
					}

					$scope.plotGraphSpouse = function($age) {

						/*$scope.checkDragSpouse = 0;*/

						$scope.chartIncomeSpouse = [];

						for (var i = 0; i < $scope.spouseIncome.length; i++) {

							if ($scope.spouseIncome[i].year == ($scope.birthYear * 1 + $age * 1)) {

								break;
							} else {

								$scope.chartIncomeSpouse.push({
									value : $scope.spouseIncome[i].value,
									year : $scope.spouseIncome[i].year,
									"allowDrag" : "0"
								});

							}
						}

						editchart();

					}

					$scope.plotGraph = function($age) {

						
						$scope.chartYear = [];
						$scope.chartIncome = [];

						for (var i = 0; i < $scope.income.length; i++) {
						
							if ($scope.income[i].year == ($scope.birthYear * 1 + $age * 1)) {
								// //alert($scope.income[i].year)
								break;
							} else {
								$scope.chartYear.push({
									label : $scope.income[i].year.toString()
								});
								$scope.chartIncome.push({
									value : $scope.income[i].value,
									year : $scope.income[i].year,
									"allowDrag" : "0"
								});
						
							}
						}
						$scope.dragPoint[$scope.dragPoint.length-1]=$scope.chartYear.length - 1;
						$scope.dragPoint1[$scope.dragPoint1.length-1]=$scope.chartYear.length - 1;
						//alert(JSON.stringify($scope.dragPoint));
						//alert(JSON.stringify($scope.dragPoint1));
						$scope.yearModal(new Date().getFullYear());
						editchart();

					}

					$scope.plotExpense = function($data) {

						$scope.chartExpense = [];

						for (var i = 0; i < $data.length; i++) {

							$scope.chartExpense.push({
								value : $data[i].value,
								"allowDrag" : "0"
							});
							$scope.assetGoals.push({
								value : "0",
								"allowDrag" : "0"
							});

						}

						editchart();

					}

					$scope.plotAssetGoals = function($data) {

						$scope.chartGoals = [];

						for (var i = 0; i < $data.length; i++) {

							$scope.chartGoals.push({
								value : $data[i].value,
								"allowDrag" : "0"
							});

						}

						editchart();

					}

					$scope.restore = function() {

						$scope.changeIncomeProfile($scope.incomeProfilesChart);

					}

					$scope.yearModal = function($datecurr) {

						$scope.chartYear2 = [];
						for (var i = 0; i < $scope.chartYear.length; i++) {
							if ($scope.chartYear[i].label > $datecurr) {

								$scope.chartYear2.push({
									label : $scope.chartYear[i].label
											.toString()
								});
							}
						}

					}

					$scope.fetchPlanDetails = function($event) {

						$scope.goals1 = [];
						$scope.chartGoals = [];
						$scope.formdata.form = "fetchPlanDetails";
						$scope.formdata.planName = $event;
						$scope.formdata.user_id=$scope.user_id;
						$http(
								{
									method : 'POST',
									url : 'DashboardAdmin',
									data : $.param($scope.formdata),
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									}
								})
								.then(
										function(result) {

											$scope.planDetailsAsset = [];
											$scope.planDetailsTax = [];
											$scope.goals = result.data.Goals;
											$scope.goalsid = result.data.GoalIds;
											$scope.planDetailsAsset = result.data.assests;
											$scope.planDetailsTax = result.data.tax;
											//alert(JSON.stringify($scope.planDetailsTax));

											// alert(JSON.stringify(result.data.customized_expense));
											// alert(JSON.stringify(result.data.college_expense));
											// alert(JSON.stringify(result.data.tax));
											// $scope.sum=$scope.assets[i].savings*1+$scope.assets[i].taxable_investment_amount*1+$scope.assets[i].nontaxable_investment_amount*1;
											$scope.chartAssets = [];
											// alert($scope.planDetailsAsset[0].saving);
											for (i = 0; i < $scope.planDetailsAsset.length; i++) {
												if ($scope.tableIncome[i].year = $scope.planDetailsAsset[i].year) {
													// alert($scope.tableIncome[i].federalTax);
													$scope.tableIncome[i].federalTax = $scope.planDetailsTax[i].federalTax;
													$scope.tableIncome[i].userfICASocialSecurityTax = $scope.planDetailsTax[i].userSSTax;
													$scope.tableIncome[i].spousefICASocialSecurityTax = $scope.planDetailsTax[i].spouseSSTax;
													$scope.tableIncome[i].stateTax = $scope.planDetailsTax[i].stateTax;
													$scope.tableIncome[i].fICAMedicareTax = $scope.planDetailsTax[i].fICAMedicareTax;
													$scope.tableIncome[i].savings = $scope.planDetailsAsset[i].savings;
													// alert($scope.planDetailsAsset[i].saving);
													$scope.tableIncome[i].nontaxable_investment_amount = $scope.planDetailsAsset[i].nontaxable_investment_amount;

													$scope.tableIncome[i].taxable_investment_amount = $scope.planDetailsAsset[i].taxable_investment_amount;
													$scope.sum = $scope.planDetailsAsset[i].savings
															* 1
															+ $scope.planDetailsAsset[i].taxable_investment_amount
															* 1
															+ $scope.planDetailsAsset[i].nontaxable_investment_amount
															* 1;
													$scope.chartAssets.push({
														value : $scope.sum,
														"allowDrag" : "0"
													})
												}

											}

											if ($scope.goals == 'noGoals'
													|| $scope.goals == undefined) {

												$scope.noGoals = false;
												$scope.messagehome = "We extend your income and expense to when you are seventy year old, you can drag the graph to change the future incomes and expenses as you like.";

											} else {
												$scope.noGoals = true;
												$scope.messagehome = "We extend your income and expense to when you are seventy year old, you can drag the graph to change the future incomes and expenses as you like.";
												for (var i = 0; i < $scope.goals.length; i++) {

													$scope.goals1.push({
														name : $scope.goals[i],
														id : $scope.goalsid[i]
													});

												}

												$scope.firstgoal = ($scope.goals1[0].name);
												$scope.goal_id = $scope.goalsid[0];
												$scope.selectedGoalname = $scope.firstgoal;

												$scope.ExpenseDetails = result.data.expenses;

												$scope
														.plotExpense($scope.ExpenseIncome);
												$scope
														.plotAssetGoals($scope.assetGoals);

												if ($scope.ExpenseDetails.car_expense != null
														&& $scope.ExpenseDetails.car_expense.exactAnual_morgage != 0) {
													// alert(JSON.stringify($scope.ExpenseDetails));
													// alert("car is become")
													$scope
															.addExpense(
																	new Date()
																			.getFullYear(),
																	new Date()
																			.getFullYear() + 4,
																	$scope.ExpenseDetails.car_expense.exactAnual_morgage)

												}
												if (result.data.college_expense != null) {

													// alert("college education
													// is become")
													for (i = 0; i < result.data.college_expense.length; i++) {
														$scope
																.addExpense(
																		result.data.college_expense[i].startYear,
																		result.data.college_expense[i].endYear,
																		result.data.college_expense[i].collegeExpense)
													}

												}

												if (result.data.customized_expense != null) {
													// alert("customized
													// expenses")
													for (i = 0; i < result.data.customized_expense.length; i++) {
														$scope
																.addExpense(
																		result.data.customized_expense[i].startYear,
																		result.data.customized_expense[i].endYear,
																		result.data.customized_expense[i].goalCost)
													}

												}
												/*
												 * if($scope.ExpenseDetails.emergency_expense!=null&&$scope.ExpenseDetails.emergency_expense.emi!=0) {
												 * 
												 * $scope.addExpense(new
												 * Date().getFullYear(),new
												 * Date().getFullYear(),$scope.ExpenseDetails.emergency_expense.emi)
												 *  }
												 */
												if ($scope.ExpenseDetails.housing_expense != null
														&& $scope.ExpenseDetails.housing_expense.mortgage_expense != 0) {

													// alert("housing expenses")
													$scope
															.addExpense(
																	$scope.ExpenseDetails.housing_expense.startYear,
																	$scope.ExpenseDetails.housing_expense.endYear,
																	$scope.ExpenseDetails.housing_expense.mortgage_expense)

												}

												if (result.data.kid_expense != null) {
													// alert("kid expenses")
													for (i = 0; i < result.data.kid_expense.length; i++) {
														$scope
																.addExpense(
																		result.data.kid_expense[i].startYear,
																		result.data.kid_expense.endYear,
																		$scope.ExpenseDetails.kid_expense.annualExpense)
													}
												}
												if ($scope.ExpenseDetails.marriage_expense != null
														&& $scope.ExpenseDetails.marriage_expense.annualexpense != 0) {
													// alert("marriage
													// expenses")
													$scope
															.addExpense(
																	$scope.ExpenseDetails.marriage_expense.startYear,
																	$scope.ExpenseDetails.marriage_expense.endYear,
																	$scope.ExpenseDetails.marriage_expense.annualexpense)

												}

												if ($scope.ExpenseDetails.retirement_expense != null
														&& $scope.ExpenseDetails.retirement_expense.expenses != 0) {
													// alert("retirement
													// expenses")
													$scope
															.addExpense(
																	$scope.ExpenseDetails.retirement_expense.startYear,
																	$scope.ExpenseDetails.retirement_expense.endYear,
																	$scope.ExpenseDetails.retirement_expense.expenses)

												}

												if ($scope.ExpenseDetails.vacation_expense != null
														&& $scope.ExpenseDetails.vacation_expense.expenses != 0) {

													// alert("vacation
													// expenses")
													if ($scope.ExpenseDetails.vacation_expense.frequency == "One Time") {
														for (var i = 0; i < $scope.chartYearExpense.length; i++) {
															if ($scope.chartYearExpense[i].label == $scope.ExpenseDetails.vacation_expense.endYear) {
																$scope.chartExpense[i].value = $scope.chartExpense[i].value
																		+ $scope.ExpenseDetails.vacation_expense.expenses;
																$scope.tableIncome[i].expense = $scope.tableIncome[i].expense
																		+ $scope.ExpenseDetails.vacation_expense.expenses;
																$scope.chartGoals[i].value = $scope.chartGoals[i].value
																		* 1
																		+ $scope.ExpenseDetails.vacation_expense.expenses
																		* 1;
															}
														}

													} else if ($scope.ExpenseDetails.vacation_expense.frequency == "Every Year") {
														for (var i = 0; i < $scope.chartYearExpense.length; i++) {

															if ($scope.chartYearExpense[i].label >= $scope.ExpenseDetails.vacation_expense.endYear) {
																$scope.chartExpense[i].value = $scope.chartExpense[i].value
																		+ $scope.ExpenseDetails.vacation_expense.expenses;
																$scope.tableIncome[i].expense = $scope.tableIncome[i].expense
																		+ $scope.ExpenseDetails.vacation_expense.expenses;
																$scope.chartGoals[i].value = $scope.chartGoals[i].value
																		* 1
																		+ $scope.ExpenseDetails.vacation_expense.expenses
																		* 1;
															}
														}
														// alert($scope.chartGoals[0].value)

													} else if ($scope.ExpenseDetails.vacation_expense.frequency == "Every Two Years") {
														for (var i = 0; i < $scope.chartYearExpense.length; i = i + 2) {

															if ($scope.chartYearExpense[i].label >= $scope.ExpenseDetails.vacation_expense.endYear) {
																$scope.chartExpense[i].value = $scope.chartExpense[i].value
																		+ $scope.ExpenseDetails.vacation_expense.expenses;
																$scope.tableIncome[i].expense = $scope.tableIncome[i].expense
																		+ $scope.ExpenseDetails.vacation_expense.expenses;
																$scope.chartGoals[i].value = $scope.chartGoals[i].value
																		* 1
																		+ $scope.ExpenseDetails.vacation_expense.expenses
																		* 1;
															}
														}

													}

												}
												editchart();
												editAssetChart();

											}
										}, function(error) {
											// //////alert("Fail");
											// $scope.message=result.data.status;

										});

					}
					$scope.addExpense = function($Syear, $Eyear, $expense) {

						for (var i = 0; i < $scope.chartYearExpense.length; i++) {
							if ($scope.chartYearExpense[i].label >= $Syear
									&& $scope.chartYearExpense[i].label <= $Eyear) {
								$scope.chartExpense[i].value = $scope.chartExpense[i].value
										* 1 + $expense * 1;
								$scope.tableIncome[i].expense = $scope.tableIncome[i].expense
										* 1 + $expense * 1;
								$scope.chartGoals[i].value = $scope.chartGoals[i].value
										* 1 + $expense * 1;
								// alert( $scope.tableIncome[i].expense)
							}
						}

					}

					$scope.planchange = function($event, index) {

						$scope.finplan_name = $event;
						$scope.selected = index;

						$scope
								.changeIncomeProfile($scope.planNames1[$scope.selected].profileName);
						$scope.incomeProfilesChart = $scope.planNames1[$scope.selected].profileName;

						$scope.onClickCreatePlan = false;
						$scope.createPlan = true;
						$scope.formdata.checkValue = false;
						$scope.hideList = false;
						$scope.onCheckedRenamePlans = false;
						$scope.CopyPlanbutton = false;
						$scope.CreatePlanbutton = true;
						$scope.emptyMassage = "";
						$scope.fetchPlanDetails($event);

					}

					$scope.changeGoals = function($event) {

						var index = $scope.goals.indexOf($event);

						$scope.goal_id = $scope.goalsid[index];
						$scope.selectedGoalname = $event;

					}

					$scope.goEditGoals = function() {

						if ($scope.selectedGoalname == "Home") {

							window.location.href = "GoalHouseEdit.jsp?goalId="
									+ $scope.goal_id;

						} else if ($scope.selectedGoalname == "Emergency Fund") {
							window.location.href = "Emergencyfundedit.jsp?goalId="
									+ $scope.goal_id;
						} else if ($scope.selectedGoalname == "Retirement") {
							window.location.href = "Goalretairmentedit.jsp?goalId="
									+ $scope.goal_id;
						} else if ($scope.selectedGoalname == "Car") {
							window.location.href = "GoalCarEdit.jsp?goalId="
									+ $scope.goal_id;
						} else if ($scope.selectedGoalname
								.includes("College Education")) {
							window.location.href = "GoalCollegeEducationEdit.jsp?goalId="
									+ $scope.goal_id;
						} else if ($scope.selectedGoalname == "Vacation") {
							window.location.href = "GoalVacationEdit.jsp?goalId="
									+ $scope.goal_id;
						} else if ($scope.selectedGoalname == "Marriage") {
							window.location.href = "MarriageGoalEdit.jsp?goalId="
									+ $scope.goal_id;
						} else if ($scope.selectedGoalname
								.includes("Raising a kid")) {
							window.location.href = "KidGoalEdit.jsp?goalId="
									+ $scope.goal_id;
						} else {
							window.location.href = "CustomizedGoalEdit.jsp?goalId="
									+ $scope.goal_id;
							// ////alert("goal not found");
						}

					}
					// ==================================Rename
					// Plan=====================================
					$scope.renamePlan = function() {

						$scope.onCheckedRenamePlans = true;
						$scope.createPlan = false;

					}
					$scope.RenamePlanName = function() {
						// alert($scope.onCheckedCopyPlans);
						$scope.formdata.goal_id = $scope.goal_id;
						$scope.formdata.plan_name = $scope.plan;
						$scope.formdata.formType = "changePlanName";
						$scope.formdata.newPlanname = $scope.formdata.planname;
						// $scope.plan=$scope.formdata.plan_name;

						/*
						 * alert($scope.formdata.plan_name); alert($scope.plan);
						 */
						if ($scope.formdata.newPlanname == null
								|| $scope.formdata.newPlanname == ""
								|| $scope.formdata.newPlanname == undefined) {
							$scope.emptyMassage = "Please Enter Plan Name!!";
						} else if ($scope.plan == null || $scope.plan == ""
								|| $scope.plan == undefined) {
							$scope.emptyMassage = "Please select plan!!";
						} else {

							$http(
									{
										method : 'POST',
										url : 'CopyPlan',
										data : $.param($scope.formdata),
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									})
									.then(
											function(result) {
												$scope.SuccessMessage = result.data.status;
												$("#success-alert").show();
												$("#success-alert").fadeTo(
														2000, 300).slideUp(
														300,
														function() {
															$("#success-alert")
																	.hide();
														});

												$scope.SuccessMessage = result.data.status;
												if ($scope.SuccessMessage == "Plan Name Exists") {
													$scope.formdata.planname = "";
													$scope.load1();
													$scope.cancle();
													$("#warning-alert").show();
													$("#warning-alert")
															.fadeTo(2000, 500)
															.slideUp(
																	500,
																	function() {
																		$(
																				"#warning-alert")
																				.hide();
																	});

												} else {
													window.location.href = "dashboardUser0.jsp?finName="
															+ $scope.formdata.newPlanname;
												}

												// $scope.SuccessMessage=" Goal
												// deleted successfully !!";

											},
											function(error) {
												$scope.message = result.data.status;

											});
						}
					}
					// ================================going to dask
					// board======================================
					$scope.cancle = function() {
						$scope.formdata.planname = "";
						$scope.onClickCreatePlan = false;
						$scope.createPlan = true;
						$scope.formdata.checkValue = false;
						$scope.hideList = false;
						$scope.onCheckedRenamePlans = false;
						$scope.CopyPlanbutton = false;
						$scope.CreatePlanbutton = true;
						$scope.emptyMassage = "";

					}

					// ==================================show plan
					// list==================================

					$scope.showPlanList = function() {

						if ($scope.formdata.checkValue == true) {

							$scope.onCheckedCopyPlans = true;
							$scope.CreatePlanbutton = false;
							$scope.hideList = true;
							$scope.CopyPlanbutton = true;
							$scope.plan = $scope.finplan_name;
						} else {
							$scope.onCheckedCopyPlans = false;
							$scope.CreatePlanbutton = true;
						}

						// alert($scope.onCheckedCopyPlans);
					}

					// ---------------------------------------------------copy
					// plan---------------------------------
					$scope.copyPlan = function() {
						$scope.formdata.plan_name = $scope.plan;

						$scope.formdata.newPlanName = $scope.formdata.planname;

						if ($scope.formdata.newPlanName == null
								|| $scope.formdata.newPlanName == ""
								|| $scope.formdata.newPlanName == undefined) {
							$scope.emptyMassage = "Please Enter NewPlan Name!!";
						} else if ($scope.plan == null || $scope.plan == ""
								|| $scope.plan == undefined) {
							$scope.emptyMassage = "Please select plan!!";
						} else {

							$http(
									{
										method : 'POST',
										url : 'CopyPlan',
										data : $.param($scope.formdata),
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									})
									.then(
											function(result) {
												$scope.SuccessMessage = result.data.status;
												if ($scope.SuccessMessage == "Plan Name Exists") {
													$scope.load1();
													$("#success-alert").show();
													$("#success-alert")
															.fadeTo(2000, 300)
															.slideUp(
																	300,
																	function() {
																		$(
																				"#success-alert")
																				.hide();
																	});
												} else {
													window.location.href = "dashboardUser0.jsp?finName="
															+ $scope.formdata.newPlanname;
												}

											}, function(error) {

											});
						}

					}
					// ---------------------------------------------------Delete
					// plan---------------------------------
					$scope.deletePlan = function() {
						// alert("inside delete plan");
						$scope.formdata.plan_name = $scope.finplan_name;
						// alert($scope.finplan_name);
						$scope.formdata.formType = "deletePlan";
						if ($scope.finplan_name == null
								|| $scope.finplan_name == ""
								|| $scope.finplan_name == undefined) {
							// alert("false");
							$scope.emptyMassage = "Please select plan!!";
						} else {
							$http(
									{
										method : 'POST',
										url : 'CopyPlan',
										data : $.param($scope.formdata),
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									})
									.then(
											function(result) {
												$scope.SuccessMessage = result.data.status;
												// $scope.load1();
												window.location.href = "dashboardUser0.jsp";
												if ($scope.SuccessMessage == "success") {
													$scope.SuccessMessage = " Plan deleted successfully !!";
													$("#success-alert").show();
													$("#success-alert")
															.fadeTo(2000, 300)
															.slideUp(
																	300,
																	function() {
																		$(
																				"#success-alert")
																				.hide();
																	});
												} else {
													window.location.href = "dashboardUser0.jsp?finName="
															+ $scope.formdata.newPlanname;
												}

											}, function(error) {

											});
						}

					}
					// ========================================delete
					// goal===================================
					$scope.DeleteGoal = function() {

						$('#myModal2').modal('show')
					}
					$scope.goDeleteGoals = function() {
						$scope.formdata.goal_id = $scope.goal_id;
						$scope.formdata.plan_name = $scope.finplan_name;
						$scope.formdata.formType = "deleteGoal";
						// //alert$scope.formdata.goal_id);
						// alert($scope.formdata.formType);
						$http(
								{
									method : 'POST',
									url : 'CopyPlan',
									data : $.param($scope.formdata),
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									}
								})
								.then(
										function(result) {
											$scope.formdata.planname = "";
											$scope.onClickCreatePlan = false;
											$scope.createPlan = true;
											$scope.formdata.checkValue = false;
											$scope.hideList = false;
											$scope.onCheckedRenamePlans = false;
											$scope.CopyPlanbutton = false;
											$scope.CreatePlanbutton = true;
											$scope.emptyMassage = "";
											$scope.load1();
											$scope.SuccessMessage = " Goal deleted successfully !!";
											$("#success-alert").show();
											$("#success-alert").fadeTo(2000,
													300).slideUp(
													300,
													function() {
														$("#success-alert")
																.hide();
													});
											$scope.message1 = result.data.status;

										},
										function(error) {
											$scope.message = result.data.status;

										});

					}

					$scope.goSelectGoals = function() {
						// ////alert("Hi");
						// $location.url('http://localhost:8080/Servetls/GoalHouseEdit.jsp');
						// $location.path('http://localhost:8080/Servetls/GoalHouseEdit.jsp').search({param:
						// 'value'});
						// //alert($scope.finplan_name);

						window.location.href = "goals.jsp?finName="
								+ $scope.finplan_name;

					}

					$scope.modalform = function() {
						// //alert($scope.formdata.planname);

						if ($scope.formdata.planname == undefined
								|| $scope.formdata.planname == null
								|| $scope.formdata.planname == "") {
							$scope.emptyMassage = "Please Enter NewPlan Name!!";
						} else {
							$scope.formdata.profile_name = $scope.incomeProfilesChart;
							$scope.formdata.form = "createNewPlan";
							// ////alert($scope.formdata.form);
							$scope.formdata.user_id=$scope.user_id;
							$http(
									{
										method : 'POST',
										url : 'DashboardAdmin',
										data : $.param($scope.formdata),
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									})
									.then(
											function(result) {

												$scope.message1 = result.data.status;
												if ($scope.message1 == "success") {
													// ////alert($scope.formdata.planname);
													window.location.href = "goals.jsp?finName="
															+ $scope.formdata.planname;
												}
												console.log("message"
														+ $scope.message);

											},
											function(error) {
												$scope.message = result.data.status;

											});

						}

					}

					function editchart() {
						// //alert("hello");

$scope.dataSet=[];

if($scope.marital_status=="Yes")
	{
	$scope.dataSet=[];
	$scope.dataSet.push(
		{
			id : "IJ",
			seriesname : "User Income",
			color : "328de4",
			data : $scope.chartIncome
		},
		{
			id : "LJ",
			seriesname : "Spouse Income",
			color : "32e2e2",
			data : $scope.chartIncomeSpouse
		},
		{
			id : "CR",
			seriesname : "Combined Income",
			showvalues : "0",
			color : "#B0C10C",
			data : $scope.chartCombinedIncome
		},
		{
			id : "CR",
			seriesname : "Expense",
			showvalues : "0",
			color : "e43234",
			data : $scope.chartExpense
		});	
	
	}
else
	{
	$scope.dataSet=[];
	$scope.dataSet.push(
			{
				id : "IJ",
				seriesname : "User Income",
				color : "328de4",
				data : $scope.chartIncome
			},
			{
				id : "CR",
				seriesname : "Expense",
				showvalues : "0",
				color : "e43234",
				data : $scope.chartExpense
			});	
		
		}
	
	
						FusionCharts
								.ready(function() {

									var salesPrediction = new FusionCharts(
											{
												type : 'dragline',
												renderAt : 'chart-container1',
												width : '1000',
												height : '350',
												dataFormat : 'json',
												dataSource : {
													"chart" : {
														"caption" : "Income - Expense",
														"showvalues" : "0",
														"xAxisName" : "Year",
														"yAxisName" : "Income and Expenses",
														"bgcolor" : "FFFFFF",
														"formatNumberScale":"0",
														"showalternatehgridcolor" : "0",
														"showplotborder" : "1",
														"divlinecolor" : "CCCCCC",
														"showcanvasborder" : "0",
														"captionpadding" : "20",
														"legendpadding" : "10",
														"plotbordercolor" : "2f69bf",
														"linethickness" : "3",
														"formbtnbgcolor" : "666666",
														"btntextcolor" : "FFFFFF",
														"showrestorebtn" : "0",
														"canvasborderalpha" : "0",
														"legendshadow" : "0",
														"legendborderalpha" : "0",
														"showborder" : "0",
														"submitformusingajax" : "0"
													},
													"categories" : [ {
														"category" : $scope.chartYear
													} ],
													"dataset" :$scope.dataSet,
													"styles" : {
														"definition" : [
																{
																	"name" : "myCaptionFont",
																	"type" : "font",
																	"font" : "Arial",
																	"size" : "14",
																	"bold" : "1"
																},
																{
																	"name" : "mySubCaptionFont",
																	"type" : "font",
																	"font" : "Arial",
																	"size" : "10",
																	"bold" : "0"
																} ],
														"application" : [
																{
																	"toobject" : "Caption",
																	"styles" : "myCaptionFont"
																},
																{
																	"toobject" : "SubCaption",
																	"styles" : "mySubCaptionFont"
																} ]
													}
												},
												events : {
													'dataplotdragend' : function(
															evt, arg) {

														console.log("begining"
																+ new Date());

														var dsIndx = arg
																&& arg.datasetIndex, dtIndx = arg
																&& arg.dataIndex, val = arg
																&& parseInt(
																		arg.endValue,
																		10);

														$scope.saveFlag = 1;
														//alert("hello");
														
														if (arg.datasetName == "User Income") {
															
															$scope.val = val;
															$scope.dtIndex = dtIndx;
															// alert($scope.dtIndex);

															if ($scope.checkDrag == 0) {
																
																$scope.checkDrag = 1;
																$scope.dragPoint
																		.push(0);
																$scope.dragPoint
																		.push($scope.chartYear.length - 1);
																	
																if (($scope.dragPoint
																		.indexOf($scope.dtIndex - 1)) != -1) {
																	if($scope.dragPoint.indexOf($scope.dtIndex - 1)==0||$scope.dragPoint.indexOf($scope.dtIndex - 1)==$scope.dragPoint.length-1)
																	{
																		
																	$scope.chartIncome[$scope.dtIndex - 1].value = Math.ceil($scope.val);
																
																	$scope.chartCombinedIncome[$scope.dtIndex - 1].value=Math.ceil($scope.chartIncome[$scope.dtIndex - 1].value*1+$scope.chartIncomeSpouse[$scope.dtIndex - 1].value*1);	
																	}
																	else
																		{
																		
																		$scope.indexChangedPoint = ($scope.dragPoint
																				.indexOf($scope.dtIndex - 1));

																		$scope
																				.modifyUserIncome(
																						$scope.dragPoint[($scope.indexChangedPoint) - 1],
																						($scope.dragPoint[($scope.indexChangedPoint) * 1 + 1 * 1]),
																						$scope.dtIndex - 1);
																		
																		
																		}
																	$scope.chartReload();
																
																} else {

																	$scope.dragPoint
																			.push($scope.dtIndex - 1);

																	$scope.dragPoint
																			.sort(function(
																					a,
																					b) {
																				return a
																						- b
																			});

																	$scope.indexChangedPoint = ($scope.dragPoint
																			.indexOf($scope.dtIndex - 1));

																	$scope
																			.modifyUserIncome(
																					$scope.dragPoint[($scope.indexChangedPoint) - 1],
																					($scope.dragPoint[($scope.indexChangedPoint) * 1 + 1 * 1]),
																					$scope.dtIndex - 1);
																	
																	
																	
																}
																
																
																
																
																
																
																
																
																
															} else {

																if (($scope.dragPoint
																		.indexOf($scope.dtIndex - 1)) != -1) {

																	if($scope.dragPoint.indexOf($scope.dtIndex - 1)==0||$scope.dragPoint.indexOf($scope.dtIndex - 1)==$scope.dragPoint.length-1)
																	{
																		//alert("first");
																	$scope.chartIncome[$scope.dtIndex - 1].value = Math.ceil($scope.val);
																
																	$scope.chartCombinedIncome[$scope.dtIndex - 1].value=Math.ceil($scope.chartIncome[$scope.dtIndex - 1].value*1+$scope.chartIncomeSpouse[$scope.dtIndex - 1].value*1);	
																	}
																	else
																		{
																		
																		$scope.indexChangedPoint = ($scope.dragPoint
																				.indexOf($scope.dtIndex - 1));

																		$scope
																				.modifyUserIncome(
																						$scope.dragPoint[($scope.indexChangedPoint) - 1],
																						($scope.dragPoint[($scope.indexChangedPoint) * 1 + 1 * 1]),
																						$scope.dtIndex - 1);
																		
																		
																		}
																	$scope.chartReload();
																
																} else {

																	$scope.dragPoint
																			.push($scope.dtIndex - 1);

																	$scope.dragPoint
																			.sort(function(
																					a,
																					b) {
																				return a
																						- b
																			});

																	$scope.indexChangedPoint = ($scope.dragPoint
																			.indexOf($scope.dtIndex - 1));

																	$scope
																			.modifyUserIncome(
																					$scope.dragPoint[($scope.indexChangedPoint) - 1],
																					($scope.dragPoint[($scope.indexChangedPoint) * 1 + 1 * 1]),
																					$scope.dtIndex - 1);
																	
																	
																	
																}

															}

														}

														else if (arg.datasetName == "Spouse Income") {

															$scope.val1 = val;
															$scope.dtIndex1 = dtIndx;

															if ($scope.checkDrag1 == 0) {
																$scope.checkDrag1 = 1;
																$scope.dragPoint1
																		.push(0);
																$scope.dragPoint1
																		.push($scope.chartYear.length - 1);

																if (($scope.dragPoint1
																		.indexOf($scope.dtIndex1 - 1)) != -1) {
																	if($scope.dragPoint1.indexOf($scope.dtIndex1 - 1)==0||$scope.dragPoint1.indexOf($scope.dtIndex1 - 1)==$scope.dragPoint1.length-1)
																	{

																	$scope.chartIncomeSpouse[$scope.dtIndex1 - 1].value = Math.ceil($scope.val1);
																
																	$scope.chartCombinedIncome[$scope.dtIndex1 - 1].value=Math.ceil($scope.chartIncome[$scope.dtIndex1 - 1].value*1+$scope.chartIncomeSpouse[$scope.dtIndex1 - 1].value*1);	
																	}
																	else
																		{
																		
																		$scope.indexChangedPoint1 = ($scope.dragPoint1
																				.indexOf($scope.dtIndex1 - 1));

																		$scope
																				.modifySpouseIncome(
																						$scope.dragPoint1[($scope.indexChangedPoint1) - 1],
																						($scope.dragPoint1[($scope.indexChangedPoint1) * 1 + 1 * 1]),
																						$scope.dtIndex1 - 1);
																		
																		
																		}

																	
																	
																	$scope.chartReload();
																} else {

																	$scope.dragPoint1
																			.push($scope.dtIndex1 - 1);

																	$scope.dragPoint1
																			.sort(function(
																					a,
																					b) {
																				return a
																						- b
																			});

																	$scope.indexChangedPoint1 = ($scope.dragPoint1
																			.indexOf($scope.dtIndex1 - 1));

																	$scope
																			.modifySpouseIncome(
																					$scope.dragPoint1[($scope.indexChangedPoint1) - 1],
																					($scope.dragPoint1[($scope.indexChangedPoint1) * 1 + 1 * 1]),
																					$scope.dtIndex1 - 1);
																	

																}

																

															} else {

																if (($scope.dragPoint1
																		.indexOf($scope.dtIndex1 - 1)) != -1) {


																	if($scope.dragPoint1.indexOf($scope.dtIndex1 - 1)==0||$scope.dragPoint1.indexOf($scope.dtIndex1 - 1)==$scope.dragPoint1.length-1)
																	{
																		//alert("first");
																	$scope.chartIncomeSpouse[$scope.dtIndex1 - 1].value = Math.ceil($scope.val1);
																
																	$scope.chartCombinedIncome[$scope.dtIndex1 - 1].value=Math.ceil($scope.chartIncome[$scope.dtIndex1 - 1].value*1+$scope.chartIncomeSpouse[$scope.dtIndex1 - 1].value*1);	
																	}
																	else
																		{
																		
																		$scope.indexChangedPoint1 = ($scope.dragPoint1
																				.indexOf($scope.dtIndex1 - 1));

																		$scope
																				.modifySpouseIncome(
																						$scope.dragPoint1[($scope.indexChangedPoint1) - 1],
																						($scope.dragPoint1[($scope.indexChangedPoint1) * 1 + 1 * 1]),
																						$scope.dtIndex1 - 1);
																		
																		
																		}


																	
																	
																	$scope.chartReload();
																} else {

																	$scope.dragPoint1
																			.push($scope.dtIndex1 - 1);

																	$scope.dragPoint1
																			.sort(function(
																					a,
																					b) {
																				return a
																						- b
																			});

																	$scope.indexChangedPoint1 = ($scope.dragPoint1
																			.indexOf($scope.dtIndex1 - 1));

																	$scope
																			.modifySpouseIncome(
																					$scope.dragPoint1[($scope.indexChangedPoint1) - 1],
																					($scope.dragPoint1[($scope.indexChangedPoint1) * 1 + 1 * 1]),
																					$scope.dtIndex1 - 1);
																	

																}

															}

														} else {
														}

													}

												}
											}).render();
								});

					}

					function editAssetChart() {

						FusionCharts
								.ready(function() {

									var salesPrediction = new FusionCharts(
											{
												type : 'dragline',
												renderAt : 'chart-container2',
												width : '1000',
												height : '350',
												dataFormat : 'json',
												dataSource : {
													"chart" : {
														"caption" : "Asset - Growth",
														"showvalues" : "0",
														"xAxisName" : "Year",
														"formatNumberScale":"0",
														"yAxisName" : "Asset",
														"bgcolor" : "FFFFFF",
														"showalternatehgridcolor" : "0",
														"showplotborder" : "1",
														"divlinecolor" : "CCCCCC",
														"showcanvasborder" : "0",
														"captionpadding" : "20",
														"legendpadding" : "10",
														"plotbordercolor" : "2f69bf",
														"linethickness" : "3",
														"formbtnbgcolor" : "666666",
														"btntextcolor" : "FFFFFF",
														"showrestorebtn" : "0",
														"canvasborderalpha" : "0",
														"legendshadow" : "0",
														"legendborderalpha" : "0",
														"showborder" : "0",
														"submitformusingajax" : "0"
													},
													"categories" : [ {
														"category" : $scope.chartYear
													} ],
													"dataset" : [

															{
																"id" : "CR",
																"seriesname" : "Goals",
																"showvalues" : "0",
																"color" : "#B0C10C",
																"data" : $scope.chartGoals
															},
															{
																"id" : "CR",
																"seriesname" : "Assets",
																"showvalues" : "0",
																"color" : "e43234",
																"data" : $scope.chartAssets
															} ],
													"styles" : {
														"definition" : [
																{
																	"name" : "myCaptionFont",
																	"type" : "font",
																	"font" : "Arial",
																	"size" : "14",
																	"bold" : "1"
																},
																{
																	"name" : "mySubCaptionFont",
																	"type" : "font",
																	"font" : "Arial",
																	"size" : "10",
																	"bold" : "0"
																} ],
														"application" : [
																{
																	"toobject" : "Caption",
																	"styles" : "myCaptionFont"
																},
																{
																	"toobject" : "SubCaption",
																	"styles" : "mySubCaptionFont"
																} ]
													}
												},
												events : {
													'dataplotdragend' : function(
															evt, arg) {

														var dsIndx = arg
																&& arg.datasetIndex, dtIndx = arg
																&& arg.dataIndex, val = arg
																&& parseInt(
																		arg.endValue,
																		10);

													}

												}
											}).render();
								});

					}
					$scope.chartReload=function()
					{
						
						editchart();
					}

					$scope.modifyUserIncome = function($startIndex, $endIndex,
							$changedPoint) {

						$scope.startIndex = $startIndex;
						$scope.endIndex = $endIndex;
						$scope.changedPoint = $changedPoint;
						

						incValue = ($scope.val - $scope.chartIncome[$scope.startIndex].value)
								/ ($scope.changedPoint - $scope.startIndex);
						

						for (i = $scope.startIndex + 1; i <= $scope.changedPoint; i++) {

							$scope.chartIncome[i].value = Math.ceil($scope.chartIncome[i - 1].value
									* 1 + incValue * 1);

							if($scope.marital_status=="Yes")
								{
							$scope.chartCombinedIncome[i].value=Math.ceil($scope.chartIncome[i].value+$scope.chartIncomeSpouse[i].value);
								}
							
							//	alert($scope.chartIncome[i].value);
						}
						
						dcValue = ($scope.val - $scope.chartIncome[$scope.endIndex].value)
								/ ($scope.endIndex - $scope.changedPoint);

						for (i = $scope.changedPoint + 1; i <= $scope.endIndex; i++) {
							
							$scope.chartIncome[i].value =Math.ceil( $scope.chartIncome[i - 1].value
									* 1 - dcValue * 1);
							

							if($scope.marital_status=="Yes")
								{
							$scope.chartCombinedIncome[i].value=Math.ceil($scope.chartIncome[i].value+$scope.chartIncomeSpouse[i].value);
								}

						}

						editchart();

					}

					$scope.modifySpouseIncome = function($startIndex,
							$endIndex, $changedPoint) {

						$scope.startIndex1 = $startIndex;
						$scope.endIndex1 = $endIndex;
						$scope.changedPoint1 = $changedPoint;
						incValue = ($scope.val1 - $scope.chartIncomeSpouse[$scope.startIndex1].value)
								/ ($scope.changedPoint1 - $scope.startIndex1);

						for (i = $scope.startIndex1 + 1; i <= $scope.changedPoint1; i++) {

							$scope.chartIncomeSpouse[i].value = Math.ceil($scope.chartIncomeSpouse[i - 1].value
									* 1 + incValue * 1);
							$scope.chartCombinedIncome[i].value=Math.ceil($scope.chartIncome[i].value+$scope.chartIncomeSpouse[i].value);

						}

						dcValue = ($scope.val1 - $scope.chartIncomeSpouse[$scope.endIndex1].value)
								/ ($scope.endIndex1 - $scope.changedPoint1);

						for (i = $scope.changedPoint1 + 1; i <= $scope.endIndex1; i++) {

							$scope.chartIncomeSpouse[i].value =Math.ceil( $scope.chartIncomeSpouse[i - 1].value
									* 1 - dcValue * 1);
							$scope.chartCombinedIncome[i].value=Math.ceil($scope.chartIncome[i].value+$scope.chartIncomeSpouse[i].value);
						}

						editchart();

					}

					// -------------------------------------------------------------------------------------------------------------------

					$scope.save = function() {
						$scope.saveFlag = 0;
						$scope.profileNameCheckbox = $scope.checkboxData.profileName;
						if (($scope.checkboxData.applyThisYear == undefined && $scope.checkboxData.applyFutureYear == undefined)
								|| ($scope.checkboxData.applyThisYear == false && $scope.checkboxData.applyFutureYear == false)) {
							$scope.messageChangeIncome = "Please select atleast a checkbox !!";
						}

						else if ($scope.checkboxData.applyFutureYear == true
								&& ($scope.checkboxData.profileName == "" || $scope.checkboxData.profileName == undefined)) {

							$scope.messageChangeIncome = "Please enter the profile name!!";

						}

						else {

							if ($scope.checkboxData.applyThisYear == true) {
								$scope.IncomeDetails.profile_name = $scope.incomeProfilesChart;
							}
							if ($scope.checkboxData.applyFutureYear == true) {
								$scope.IncomeDetails.profile_name = $scope.checkboxData.profileName;
							}
							if ($scope.checkboxData.applyThisYear == true
									&& $scope.checkboxData.applyPlan == false) {

								$scope.IncomeDetails.form = "update";
								$scope.IncomeDetails.planCount = 0;
							} else if ($scope.checkboxData.applyFutureYear == true
									&& $scope.checkboxData.applyPlan == false) {

								$scope.IncomeDetails.form = "create";
								$scope.IncomeDetails.planCount = 0;
							} else if ($scope.checkboxData.applyThisYear == true
									&& $scope.checkboxData.applyPlan == true) {

								$scope.IncomeDetails.form = "update";
								$scope.IncomeDetails.planCount = $scope.Count;
								$scope.IncomeDetails.plan_name = $scope.finplan_name;

							} else if ($scope.checkboxData.applyFutureYear == true
									&& $scope.checkboxData.applyPlan == true) {

								$scope.IncomeDetails.form = "create";
								$scope.IncomeDetails.planCount = $scope.Count;
								$scope.IncomeDetails.plan_name = $scope.finplan_name;

							}

							// window.location.href="#";
							$scope.checkDrag = 0;
							$scope.checkDragSpouse = 0;
							// alert($scope.checkDrag);

							$scope.chartIncomeSpouseOld = [];
							$scope.chartIncomeOld = [];

							Array.prototype.splice.apply(
									$scope.chartIncomeSave, [ 0,
											$scope.chartIncome.length ]
											.concat($scope.chartIncome));
							if ($scope.marital_status == "Yes") {
								Array.prototype.splice
										.apply(
												$scope.spouseIncomeSave,
												[
														0,
														$scope.chartIncomeSpouse.length ]
														.concat($scope.chartIncomeSpouse));
							}
							Array.prototype.splice.apply($scope.chartYearSave,
									[ 0, $scope.chartYear.length ]
											.concat($scope.chartYear));

							$scope.chartIncomeSpouseOld = $scope.spouseIncomeSave;

							$scope.chartIncomeOld = $scope.chartIncomeSave;

							// alert(JSON.stringify($scope.chartIncomeSave));
							// / alert(JSON.stringify($scope.chartYearSave));

							$scope.IncomeDetails.income = JSON
									.stringify($scope.chartIncomeSave);
							$scope.IncomeDetails.year = JSON
									.stringify($scope.chartYearSave);
							if ($scope.marital_status == "Yes") {
								$scope.IncomeDetails.spouseIncome = JSON
										.stringify($scope.spouseIncomeSave);
								$scope.IncomeDetails.marital_status = "Yes";
							} else {
								$scope.IncomeDetails.marital_status = "No";
							}
							// alert($scope.IncomeDetails.income);
							$http(
									{
										method : 'POST',
										url : 'ModifyIncome',
										data : $.param($scope.IncomeDetails),
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									})
									.then(
											function(result) {

												$scope.SuccessMessage = " User income saved successfully !!";

												$scope.message = result.data.status;
												// alert($scope.message);
												if ($scope.message == "success") {
													$scope.messageChangeIncome = "";
													$scope.checkboxData.profileName = "";
													$("#chartModel").modal(
															'hide');
													// $scope.chartIncome=[];
													$scope.chartIncomeSpouse = [];
													$scope.chartIncome = [];
													$scope.checkDrag = 0;
													$scope.checkDragSpouse = 0;
													$scope.chartIncomeSpouse = [];
													$scope.chartYear = [];
													$scope.chartIncome = [];
													$scope.planNames1 = [];
													$scope.chartIncomeSave = [];
													$scope.spouseIncomeSave = [];
													$scope.chartYearSave = [];
													$scope.tableIncome = [];
													$scope.chartCombinedIncome = [];
													$scope.load1();

													$("#success-alert").show();
													$("#success-alert")
															.fadeTo(2000, 300)
															.slideUp(
																	300,
																	function() {
																		$(
																				"#success-alert")
																				.hide();
																	});
												} else if ($scope.message == "fail") {
													$scope.messageChangeIncome = "Profile name already exits!!";

												}
												console.log("message"
														+ $scope.message);

											},
											function(error) {
												// //alert("Fail");
												$scope.message = result.data.status;

											});

						}
					}

					$scope.selectThisYear = function() {
						$scope.messageChangeIncome = "";
						if ($scope.checkboxData.applyThisYear == true) {
							$scope.checkboxData.applyGraduallyYear = false;
							$scope.checkboxData.applyFutureYear = false;
							$scope.incomeProfileName = false;
							$scope.checkboxData.profileName = "";

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
					$scope.selectGraduallyYear = function() {
						$scope.messageChangeIncome = "";
						if ($scope.checkboxData.applyGraduallyYear == true) {
							$scope.checkboxData.applyThisYear = false;

						}

					}
					$scope.selectFutureYear = function() {

						if ($scope.checkboxData.applyFutureYear == true) {
							$scope.messageChangeIncome = "";
							$scope.checkboxData.applyThisYear = false;
							$scope.incomeProfileName = true;
							// $scope.checkboxData.profileName="";

						}

					}

					$scope.showEdit = function() {
						$scope.createPlan = false;
						$scope.onClickCreatePlan = true;
						if ($scope.Count == 0) {
							$scope.hideCheckBox = false;
						} else {
							$scope.hideCheckBox = true;
						}

					}

					// -------------checking the graph is saved or not
					// ------------
					$scope.checkSave = function() {

						if ($scope.saveFlag == 1) {
							$('#myModalback').modal('show');

						} else {
							window.location.href = "userProfile.jsp";
						}
					}

					$scope.gouserProfile = function() {

						window.location.href = "userProfile.jsp";
					}

					$scope.changeIncomeProfile = function($profileName) {
						// alert($profileName);
						$scope.dragPoint1 = [];
						$scope.dragPoint = [];
						$scope.checkDrag = 0;
						$scope.checkDrag1 = 0;
						$scope.IncomeDetails.profile_name = $profileName;
						$scope.IncomeDetails.form = "getIncomeProfile";
						$http(
								{
									method : 'POST',
									url : 'ModifyIncome',
									data : $.param($scope.IncomeDetails),
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									}
								})
								.then(
										function(result) {

											$scope.message = result.data.status;
											// alert($scope.message);
											if ($scope.message == "success") {

												$scope.user_income = result.data.user_income;
												$scope.spouse_income = result.data.spouse_income;
												$scope.combined_income = result.data.combined_income;
												// alert(JSON.stringify($scope.combined_income));
												for (var i = 0; i < $scope.chartYear.length; i++) {

													if ($scope.chartYear[i].label == $scope.user_income[i].year
															&& $scope.income[i].year == $scope.user_income[i].year) {
														$scope.income[i].value = $scope.user_income[i].value;
														$scope.chartIncome[i].value = $scope.user_income[i].value;

														if ($scope.marital_status == "Yes"
																&& $scope.chartYear[i].label == $scope.spouse_income[i].year
																&& $scope.chartYear[i].label == $scope.combined_income[i].year) {
															$scope.chartCombinedIncome[i].value = $scope.combined_income[i].value;
															$scope.chartIncomeSpouse[i].value = $scope.spouse_income[i].value;

															$scope.spouseIncome[i].value = $scope.spouse_income[i].value;

															if ($scope.tableIncome[i].year == $scope.spouse_income[i].year) {
																$scope.tableIncome[i].spouseValue = $scope.spouse_income[i].value;
																$scope.tableIncome[i].value = $scope.user_income[i].value;
															}

														} else {

															if ($scope.tableIncome[i].year == $scope.user_income[i].year) {

																$scope.tableIncome[i].value = $scope.user_income[i].value;
															}
														}

													}

												}

												editchart();

											} else if ($scope.message == "fail") {

											}

										});
					}

					$(document).click(function(e) {
						// $scope.createPlan=true;
						// $scope.onClickCreatePlan=false;
					});

					$scope.hideSuccess = function() {
						$("#success-alert").hide();

					}
					$scope.hideWarning = function() {
						$("#warning-alert").hide();

					}
					
					
					
					
				});
