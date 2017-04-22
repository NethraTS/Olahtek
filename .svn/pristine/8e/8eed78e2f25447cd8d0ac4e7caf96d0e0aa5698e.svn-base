var app = angular.module('selectGoals', []);
var url = window.location.href;
var hashes = url.split("=")[1];
app
		.controller(
				'selectGoalsController',
				function($http, $scope) {
					hashes = (decodeURIComponent(hashes));
					$scope.goalType = [];
					$scope.completedStatus = [];
					$scope.goalNames = [];
					$scope.hashes = {};
					$scope.House = "";
					$scope.Retirement = "";
					$scope.EmergencyFund = "";
					$scope.Married = "";
					$scope.Kids = "";
					$scope.College = "";
					$scope.Car = "";
					$scope.Vacation = "";
					$scope.msg = "hi";
					$scope.hashes.data = hashes;
					$scope.kidsCount = 0;
					// $scope.mustHide=false;
					$scope.marital_status = ""
					// ////alert("id"+hashes);
					$scope.sessionDetails = {};
					$scope.sessionDelete = {};
					$scope.goDashboard = function() {
						window.location.href = "dashboardUser0.jsp?finName="
								+ $scope.hashes.data;
					}

					$scope.deleteAllCookies = function() {
						// //alert("delete all cookies");

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
							// //alert("Session Got deleted");

							window.location.href = "index.jsp";

						}, function(error) {
							// //alert("Session not deleted");

						});
					}

					function readCookie(name) {
						// //alert("hi");
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
						// alert("hello");
						$scope.sessionDetails.cookieId = readCookie("AhTwxlO");
						// //alert( $scope.sessionDetails.cookieId);
						$scope.sessionDetails.lastVisitedPage = document.URL;
						// //alert( $scope.sessionDetails.lastVisitedPage);
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
													// //alert("Cookie ajax
													// Success");
													// alert(result.data.lastVisitedPage);
													if (result.data.lastVisitedPage == "undefined") {

														window.location.href = "dashboardUserr0.jsp";

													} else {
														document.cookie = "lastVisitedPage="
																+ result.data.lastVisitedPage;
														if (result.data.lastVisitedPage == document.URL) {
															// //alert("redirecting
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

												// //alert("Cokkie ajax Fail");
											});
						} else {
							// //alert("Session got expired");
							$scope.deleteAllCookies();
							window.location.href = "index.jsp";
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

					$scope.load1 = function() {
						$scope.hashes.form = "fetchGoalStatus";
						$http(
								{
									method : 'POST',
									url : 'CreateFinPlan',
									data : $.param($scope.hashes),
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									}
								})
								.then(
										function(result) {
											// //alert("Sucess");
											// $scope.message=result.data;

											// ////alert($scope.message);
											// alert(result.data.kidsCount)
											$scope.kidsCount = result.data.kidsCount;
											$scope.kidsCountFinplan = result.data.kidsCountFinplan;
											//alert($scope.kidsCount);
											//alert($scope.kidsCountFinplan);
											
											$scope.goalType = result.data.Goals;
											$scope.marital_status = result.data.maritalstatus;

											$scope.completedStatus = result.data.GoalStatus;
											$scope.collegeGoalCount = result.data.collegeGoalCount;
											$scope.kidGoalCount = result.data.kidGoalCount;
											
											if ($scope.marital_status == "Yes") {

												$scope.mustHide = true;
											}
											for (var i = 0; i < $scope.goalType.length; i++) {

												$scope.goalNames
														.push({
															name : $scope.goalType[i],
															status : $scope.completedStatus[i]
														});
												// alert($scope.goalType[i]);
												if (($scope.goalType[i] == "Home")
														&& ($scope.completedStatus[i] == 1)) {
													// ////alert("colour
													// chnage")
													$scope.House = 1;
												} else if (($scope.goalType[i] == "Retirement")
														&& ($scope.completedStatus[i] == 1)) {
													// ////alert("colour
													// chnage")
													$scope.Retirement = 1;
												} else if (($scope.goalType[i] == "Emergency Fund")
														&& ($scope.completedStatus[i] == 1)) {
													// ////alert("colour
													// chnage")
													$scope.EmergencyFund = 1;
												} else if (($scope.goalType[i] == "Marriage")
														&& ($scope.completedStatus[i] >1)) {
													// ////alert("colour
													// chnage")
													$scope.Married = 1;
												} /*else if (($scope.goalType[i] == "Raising a kid")
														&& (($scope.kidGoalCount == $scope.kidsCount) || ($scope.kidGoalCount > $scope.kidsCount))) {
													// alert("colour chnage")
													$scope.kids = 1;
												} */else if (($scope.goalType[i] == "College Education")
														&& (($scope.collegeGoalCount == $scope.kidsCount*1+$scope.kidsCountFinplan*1) || ($scope.collegeGoalCount > $scope.kidsCount*1+$scope.kidsCountFinplan*1))) {
													// ////alert("colour
													// chnage")
													$scope.College = 1;
												} else if (($scope.goalType[i] == "Car")
														&& ($scope.completedStatus[i] == 1)) {
													// ////alert("colour
													// chnage")
													$scope.Car = 1;
												} else if (($scope.goalType[i] == "Vacation")
														&& ($scope.completedStatus[i] == 1)) {
													// ////alert("colour
													// chnage")
													$scope.Vacation = 1;
												} else {
													// //alert("Customised Goal
													// or the goal type given
													// not Matching")
												}

											}

											/*
											 * if($scope.marital_status=="No") {
											 * 
											 * if($scope.Married!=1) {
											 * 
											 * window.location.href="MarrigeGoal.jsp?finName="+
											 * $scope.hashes.data; } else {
											 * $scope.mustHide=false;
											 * $("#ModalNotification").modal('show'); } }
											 */

										}, function(error) {
											// //alert("fail");

										});
					}

					$scope.goHouse = function() {
						// //alert("house");
						if ($scope.House != 1) {
							window.location.href = "GoalHouse.jsp?finName="
									+ $scope.hashes.data;
						} else {
							
						/*	$scope.goalNotPossible = "Goal already added !!";
							$("#ModalNotification").modal('show');*/
							window.location.href = "GoalHouse.jsp?finName="
								+ $scope.hashes.data;
						}
					}
					$scope.goRetirement = function() {
						// //alert("goRetirement");
						// //alert($scope.hashes.data);
						if ($scope.Retirement != 1) {
							window.location.href = "Goalretirement.jsp?finName="
									+ $scope.hashes.data;
						} else {
							$scope.goalNotPossible = "Goal already added !!";
							$("#ModalNotification").modal('show');
						}
					}
					$scope.goEmergencyFund = function() {
						// //alert("goEmergency");
						if ($scope.EmergencyFund != 1) {
							window.location.href = "GoalEmergencyFund.jsp?finName="
									+ $scope.hashes.data;
						} else {
							$scope.goalNotPossible = "Goal already added !!";
							$("#ModalNotification").modal('show');
						}
					}
					$scope.goCar = function() {
						// //alert("goEmergency");
						if ($scope.Car != 1) {
							window.location.href = "GoalCar.jsp?finName="
									+ $scope.hashes.data;
						} 
						else {
						/*	$scope.goalNotPossible = "Goal already added !!";
							$("#ModalNotification").modal('show');*/
							window.location.href = "GoalCar.jsp?finName="
								+ $scope.hashes.data;
						}
					}
					$scope.goCollegeEducation = function() {
						// //alert("goEmergency");
						if ($scope.kidsCount*1+$scope.kidsCountFinplan*1 > 0) {
							if ($scope.College != 1) {
								window.location.href = "GoalCollegeEducation.jsp?finName="
										+ $scope.hashes.data;
							} else {
								$scope.goalNotPossible = "Goal already added for the existing kids!!";
								$("#ModalNotification").modal('show');
							}
						} else {
							$("#ModalNoKidNotification").modal('show');

						}
					}
					$scope.goCustomisedGoal = function() {
						// //alert("goEmergency");

						window.location.href = "CustomizedGoal.jsp?finName="
								+ $scope.hashes.data;

					}
					$scope.goVacation = function() {
						// //alert("goEmergency");

						if ($scope.Vacation != 1) {
							window.location.href = "GoalVacation.jsp?finName="
									+ $scope.hashes.data;
						} else {
							$scope.goalNotPossible = "Goal already added !!";
							$("#ModalNotification").modal('show');
						}
					}
					$scope.goMarried = function() {

						if ($scope.Married != 1) {

							window.location.href = "MarriageGoal.jsp?finName="
									+ $scope.hashes.data;
						} else {
							$scope.goalNotPossible = "Goal already added !!";
							$("#ModalNotification").modal('show');
						}

					}
					$scope.goKids = function() {
						
							if ($scope.kids != 1) {
								if($scope.Married != 1)
									{
								$("#ModalNoMarriagegoalWarning").modal('show');
									}
								window.location.href = "KidGoal.jsp?finName="
										+ $scope.hashes.data;
							} else {
								$scope.goalNotPossible = "Goal already added for the existing kids!!!";
								$("#ModalNotification").modal('show');
							}
						/* else {

							$("#ModalNoKidNotification").modal('show');
						}*/
					}

				});
