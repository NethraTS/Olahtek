var app = angular.module( "Emergencyfund", [] );
var url = window.location.href;
var hashes = url.split( "=" )[1];
app.directive( "allowPattern", [ allowPatternDirective ] );

// -------------------it will allow either numbers or characters in the input
// feild--------------------
function allowPatternDirective() {
	return {
		restrict: "A",
		compile: function( tElement, tAttrs ) {
			return function( scope, element, attrs ) {
				element.bind( "keypress",
						function( event ) {
							var keyCode = event.which || event.keyCode;
							var keyCodeChar = String.fromCharCode( keyCode );

							if ( keyCodeChar.charCodeAt() == 8
									|| keyCodeChar.charCodeAt() == 9 ) {
								return true;
							}

							if ( !keyCodeChar.match( new RegExp( 
									attrs.allowPattern, "i" ) ) ) {
								event.preventDefault();
								return false;
							}

						} );
			};
		}
	};
}

app
		.controller( 
				"Goalemergency",
				function( $http, $scope ) {
					$scope.show = 1;
					$scope.sessionDetails = {};
					$scope.sessionDelete = {};
					$scope.formdata = {};
					$scope.time = [ "1", "2", "3", "4", "5", "6", "7", "8",
							"9", "10", "11", "12" ];
					$scope.time1 = [ "1", "2", "3", "4", "5", "6", "7", "8",
							"9", "10", "11", "12" ];
					$scope.minValue = "";
					$scope.formdata.month = "12";
					$scope.formdata.month1 = "6";
					$scope.masked = false;
					$scope.Goalendingyear1 = [ {
						name: "2016"
					}, {
						name: "2017"
					}, {
						name: "2018"
					}, {
						name: "2019"
					}, {
						name: "2020"
					}, {
						name: "2021"
					}, {
						name: "2022"
					}, {
						name: "2023"
					}, {
						name: "2024"
					}, {
						name: "2025"
					}, {
						name: "2026"
					}, {
						name: "2027"
					}, {
						name: "2028"
					}, {
						name: "2029"
					}, {
						name: "2030"
					}, {
						name: "2031"
					}, {
						name: "2032"
					}, {
						name: "2033"
					}, {
						name: "2034"
					}, {
						name: "2035"
					}, {
						name: "2036"
					}, {
						name: "2037"
					}, {
						name: "2038"
					}, {
						name: "2039"
					}, {
						name: "2040"
					}, {
						name: "2041"
					}, {
						name: "2042"
					}, {
						name: "2043"
					}, {
						name: "2044"
					}, {
						name: "2045"
					}, {
						name: "2046"
					} ];
					$scope.goalemergencyEditData = {};
					$scope.message = "Amount save"
					$scope.progressbar = 0;
					$scope.formdata.timePeriod = "Fix Amount";

					$scope.formdata.plan_name = ( decodeURIComponent( hashes ) );

					$scope.progressBar = function() {

						if ( $scope.formdata.timePeriod == null
								|| $scope.formdata.timePeriod == undefined
								|| $scope.formdata.timePeriod == "" ) {

							$scope.msgerr = "Please select atleast a checkbox !!";

						} else if ( $scope.formdata.timePeriod == "Fix Amount" ) {
							$scope.formdata.amountSave = $scope.minValue;

							$( "#progress_bar" ).css( "width", "50%" );
							$scope.message = "Amount save"

							$scope.show = 3;
							$scope.show1 = 2;

						}

						else if ( $scope.formdata.timePeriod == "Expense" ) {

							$scope.formdata.amountSave = ( ( $scope.tExpense / 12 ) * ( $scope.formdata.month ) );

							$( "#progress_bar" ).css( "width", "50%" );
							$scope.message = "Amount save"

							$scope.show = 3;
							$scope.show1 = 2;
						}

						else {

							$scope.formdata.amountSave = ( $scope.sixIncome / 6 * ( $scope.formdata.month1 ) );

							$( "#progress_bar" ).css( "width", "50%" );
							$scope.message = "Amount save"

							$scope.show = 3;
							$scope.show1 = 2;
						}

					}
					$scope.progressBar1 = function() {
						$scope.masked = true;

						if ( $scope.formdata.amountSave == null
								|| $scope.formdata.amountSave == undefined
								|| $scope.formdata.amountSave == "" ) {
						//	$scope.masked = false;
							//$scope.msgerr2 = "Time period should not be empty";
							$scope.formdata.amountSave=0;
						}
						{
							$( "#progress_bar" ).css( "width", "100%" );
							$( "#dialog_confirm_map" ).modal( "hide" );
							$scope.message = "DETAILS"

							$scope.show = 4;
							$scope.show1 = 3;

							$scope.formdata.actionHomeType = "insert";

							$http( 
									{
										method: "POST",
										url: "EmergencyFund",
										data: $.param( $scope.formdata ),
										headers: {
											"Content-Type": "application/x-www-form-urlencoded"
										}
									} )
									.then( 
											function( result ) {

												$scope.goalemergencyEditData = result.data;
												$scope.messresult = result.data.emi;
												$scope.timePeriod = result.data.timePeriod;
												$scope.amountSave = result.data.amountSave;
												$scope.formdata.emi = $scope.goalemergencyEditData.emi;

												if ( $scope.goalemergencyEditData.status == "success" ) {
													$scope.formdata.goal_id = $scope.goalemergencyEditData.goal_id;

													window.location.href = "Emergencyfundedit.jsp?goalid="
															+ $scope.formdata.goal_id;
													console.log( "message"
															+ $scope.message );
													$scope.masked = false;
												} else {
													$scope.masked = false;
													$scope.errmessage = "Goal is not feasible since you are not having sufficient funds !!";
													$( "#myModal" ).modal( "show" );

												}
												$scope.masked = false;
											}, function( error ) {
												$scope.message = result.data;

											} );

						}

					}

					$scope.report = function() {

						if ( $scope.Count == 0 ) {
							$scope.SuccessMessage1 = "Currently there are no plans to show the reports";
							window.location.href = "#";
							$( "#report-alert" ).show();
							$( "#report-alert" ).fadeTo( 2000, 300 ).slideUp( 400,
									function() {
										$( "#report-alert" ).hide();

									} );

						} else {
							window.location.href = "Report.jsp";
						}
					}

					$scope.deleteAllCookies = function() {

						$scope.sessionDelete.sessionID = readCookie( "AhTwxlO" );
						$http( 
								{
									method: "POST",
									url: "Logout",
									data: $.param( $scope.sessionDelete ),
									headers: {
										"Content-Type": "application/x-www-form-urlencoded"
									}
								} ).then( function( result ) {

							window.location.href = "index.jsp";

						}, function( error ) {

						} );
					}

					function readCookie( name ) {

						var nameEQ = name + "=";
						var ca = document.cookie.split( ";" );
						for ( var i = 0; i < ca.length; i++ ) {
							var c = ca[i];
							while ( c.charAt( 0 ) == " " ){
								c = c.substring( 1, c.length );
							}
							if ( c.indexOf( nameEQ ) == 0 ){
								return c.substring( nameEQ.length, c.length );
							}
						}
						return null;
					}
					$scope.load1 = function() {
						$scope.formdata.plan_name = ( decodeURIComponent( hashes ) );

						$scope.formdata.actionHomeType = "OnLoadEmergencyFund";

						$http( 
								{
									method: "POST",
									url: "EmergencyFund",
									data: $.param( $scope.formdata ),
									headers: {
										"Content-Type": "application/x-www-form-urlencoded"
									}
								} )
								.then( 
										function( result ) {

											$scope.goalemergencyEditData = result.data;
											$scope.liquid_asset = result.data.liquid_asset;
											$scope.sixIncome = result.data.sixMonthOfIncome;
											$scope.tExpense = result.data.twelveMonthOfExpense;

											if ( result.data.sixMonthOfIncome < result.data.twelveMonthOfExpense ) {
												$scope.minValue = result.data.sixMonthOfIncome;
											} else {
												$scope.minValue = result.data.twelveMonthOfExpense;
											}

											console.log( "message"
													+ $scope.message );

										}, function( error ) {
											$scope.message = result.data;

										} );

					}
					// -------------------onload function--------------------
					$scope.load = function() {

						window.history.forward();
						$scope.sessionDetails.cookieId = readCookie( "AhTwxlO" );

						$scope.sessionDetails.lastVisitedPage = document.URL;

						if ( $scope.sessionDetails.cookieId != null ) {

							$http( 
									{
										method: "POST",
										url: "CheckSession",
										data: $.param( $scope.sessionDetails ),
										headers: {
											"Content-Type": "application/x-www-form-urlencoded"
										}
									} )
									.then( 
											function( result ) {

												if ( result.data.status == "success" ) {

													if ( result.data.lastVisitedPage == "undefined" ) {

														window.location.href = "dashboardUserr0.jsp";

													} else {
														document.cookie = "lastVisitedPage="
																+ result.data.lastVisitedPage;
														if ( result.data.lastVisitedPage == document.URL ) {

														}

													}
													$scope.load1();

												} else {

													$scope.errMessage = "Session got expired";
													$( "#checkSession" ).modal( 
															"show" );
													var delay = 3000; 
													setTimeout( 
															function() {
																$scope
																		.deleteAllCookies()
															}, delay );

												}

											}, function( error ) {

											} );
						} else {

							$scope.deleteAllCookies();
							window.location.href = "index.jsp";
						}

					}

					$scope.goSelectGoal = function() {
						window.location.href = "goals.jsp?finName="
								+ $scope.formdata.plan_name;
					}
					$scope.goDashboard = function() {
						window.location.href = "dashboardUser0.jsp?finName="
								+ $scope.formdata.plan_name;
					}

				} );
