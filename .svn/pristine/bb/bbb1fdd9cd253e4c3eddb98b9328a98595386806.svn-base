var app = angular.module( "MarriageGoalEdit", [] );
var url = window.location.href;
var hashes = url.split( "=" )[1];
app.directive( "allowPattern", [ allowPatternDirective ] );

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
				"GoalMarriageEdit",
				function( $scope, $http ) {

					$scope.formdata = {};
					$scope.sessionDetails = {};
					$scope.sessionDelete = {};
					$scope.planName = "";

					$scope.Goalstartingyear1 = [ {
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
					$scope.ages = [ {
						number: "1"
					}, {
						number: "2"
					}, {
						number: "3"
					}, {
						number: "4"
					}, {
						number: "5"
					}, {
						number: "6"
					}, {
						number: "7"
					}, {
						number: "8"
					}, {
						number: "9"
					}, {
						number: "10"
					}, {
						number: "11"
					}, {
						number: "12"
					}, {
						number: "13"
					}, {
						number: "14"
					}, {
						number: "15"
					}, {
						number: "16"
					}, {
						number: "17"
					}, {
						number: "18"
					}, {
						number: "19"
					}, {
						number: "20"
					}, {
						number: "21"
					}, {
						number: "22"
					}, {
						number: "23"
					}, {
						number: "24"
					}, {
						number: "25"
					}, {
						number: "26"
					}, {
						number: "27"
					}, {
						number: "28"
					}, {
						number: "29"
					}, {
						number: "30"
					}, {
						number: "31"
					}, {
						number: "32"
					}, {
						number: "33"
					}, {
						number: "34"
					}, {
						number: "35"
					}, {
						number: "36"
					}, {
						number: "37"
					}, {
						number: "38"
					}, {
						number: "39"
					}, {
						number: "40"
					}, {
						number: "41"
					}, {
						number: "42"
					}, {
						number: "43"
					}, {
						number: "44"
					}, {
						number: "45"
					}, {
						number: "46"
					}, {
						number: "47"
					}, {
						number: "48"
					}, {
						number: "49"
					}, {
						number: "50"
					}, {
						number: "51"
					}, {
						number: "52"
					}, {
						number: "53"
					}, {
						number: "54"
					}, {
						number: "55"
					}, {
						number: "56"
					}, {
						number: "57"
					}, {
						number: "58"
					}, {
						number: "59"
					}, {
						number: "60"
					}, {
						number: "61"
					}, {
						number: "62"
					}, {
						number: "63"
					}, {
						number: "64"
					}, {
						number: "65"
					}, {
						number: "66"
					}, {
						number: "67"
					}, {
						number: "68"
					}, {
						number: "69"
					}, {
						number: "70"
					}, {
						number: "71"
					}, {
						number: "72"
					}, {
						number: "73"
					}, {
						number: "74"
					}, {
						number: "75"
					}, {
						number: "76"
					}, {
						number: "77"
					}, {
						number: "78"
					}, {
						number: "79"
					}, {
						number: "80"
					}, {
						number: "81"
					}, {
						number: "82"
					}, {
						number: "83"
					}, {
						number: "84"
					}, {
						number: "85"
					}, {
						number: "86"
					}, {
						number: "87"
					}, {
						number: "88"
					}, {
						number: "89"
					}, {
						number: "80"
					}, {
						number: "81"
					}, {
						number: "82"
					}, {
						number: "83"
					}, {
						number: "84"
					}, {
						number: "85"
					}, {
						number: "86"
					}, {
						number: "87"
					}, {
						number: "88"
					}, {
						number: "89"
					}, {
						number: "90"
					}, {
						number: "91"
					}, {
						number: "92"
					}, {
						number: "93"
					}, {
						number: "94"
					}, {
						number: "95"
					}, {
						number: "96"
					}, {
						number: "97"
					}, {
						number: "98"
					}, {
						number: "99"
					} ];
					$scope.formdata.KidCollegeYear = "";
					$scope.formdata.expensesStart = "2016";
					$scope.formdata.expensesEnd = "2016";
					$scope.formdata.marriagecost = "25000";
					$scope.goalMarriageEditData = {};
					$scope.masked = false;
					$scope.formdata.NonHousingExpenses = 0;
					$scope.formdata.marriagecost = 0;
					$scope.formdata.monthlyExpense = 0;
					$scope.formdata.HousingExpenses = 0;
					$scope.formdata.housingexpenses1 = 0;
					$scope.formdata.nonhousingexpense1 = 0;
					$scope.formdata.NonHousingExpense = 0;
					$scope.monthlyExpense = 0;

					$scope.load1 = function() {
						$scope.formdata.goal_id = ( decodeURIComponent( hashes ) );
						$( "#success-alert" ).hide();
						$( "#fail-alert" ).hide();

						$scope.formdata.actionHomeType = "load";
						$http( 
								{
									method: "POST",
									url: "MarriageGoal",
									data: $.param( $scope.formdata ),
									headers: {
										"Content-Type": "application/x-www-form-urlencoded"
									}
								} )
								.then( 
										function( result ) {
											if ( result.data.goalFeasiblity == false ) {
												$( "#fail-warning" ).show();
											}
											$scope.goalMarriageData = result.data;
											$scope.formdata.housingexpenses1 = result.data.HouseValue;
											$scope.formdata.nonhousingexpense1 = result.data.NonHousingValue;
											$scope.goal_id = $scope.goalMarriageData.goal_id;
											$scope.messresult = result.data.annualexpense;
											$scope.marriageYear = result.data.marriageYear;
											$scope.marriageCost = result.data.marriageCost;
											$scope.formdata.marriagecost = result.data.marriageCost;
											$scope.formdata.marriageyear = result.data.marriageYear;
											$scope.formdata.fin_id = result.data.fin_id;
											$scope.planName = result.data.plan_name;
											$scope.formdata.monthlyExpense = result.data.expense;
											$scope.formdata.HousingExpenses = ( result.data.HousingExpenses * 12 );
											$scope.formdata.housingexpenses1 = result.data.housingExpenses;
											$scope.formdata.nonhousingexpense1 = result.data.NonHousingExpenses;
											$scope.formdata.NonHousingExpense = ( result.data.NonHousingExpense * 12 );
											$scope.monthlyExpense = result.data.expense;
											$scope.formdata.sage = result.data.spouseAge;
											$scope.formdata.income = result.data.spouseIncome;
											$scope.formdata.newMonthlyExpense = ( result.data.newExpense * 12 );
											$scope.formdata.NonHousingExpenses = $scope.formdata.newMonthlyExpense;
											if ( result.data.goalFeasiblity == false
													|| result.data.goalFeasiblity == true ) {
												$scope.show = 8;
												$scope.result = result.data.marriageSuccess;
											}
												$scope.formdata.goal_id = $scope.goalMarriageData.goal_id;

											$scope.errorName = "";
											$scope.errorSuperhero = "";
										}, function( error ) {
											$scope.message = result.data;
											$scope.errorName = "";
											$scope.errorSuperhero = "";
										} );
						$scope.msgerr = "";
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

					// ----------------------------------spouse
					// income------------------------------------------------
					$scope.spouseIncome = function() {
						$scope.errormassage = "";
						$scope.formdata.goal_id = ( decodeURIComponent( hashes ) );
			if ( $scope.formdata.income == null
								|| $scope.formdata.income == undefined
								|| $scope.formdata.income == "" ) {
							$scope.errormassage = "Please enter the value for spouse income!!!";

						} else if ( $scope.formdata.sage == null
								|| $scope.formdata.sage == undefined
								|| $scope.formdata.sage == "" ) {

							$scope.errormassage = "Please enter the spouse age!!!";

						} else if ( $scope.formdata.newMonthlyExpense == null
								|| $scope.formdata.newMonthlyExpense == undefined
								|| $scope.formdata.newMonthlyExpense == "" ) {

							$scope.errormassage = "Please enter housing expenses after marriage!!!";

						}

						else if ( $scope.formdata.NonHousingExpenses == null
								|| $scope.formdata.NonHousingExpenses == undefined
								|| $scope.formdata.NonHousingExpenses == "" ) {

							$scope.errormassage = "Please non housing expenses after marriage!!!";

						} else if ( parseInt( $scope.formdata.expensesStart ) > parseInt( $scope.formdata.expensesEnd ) ) {

							$scope.errormassage = "End year cannot be lessthen start year!!!";

						} else if ( $scope.formdata.HousingExpenses > $scope.formdata.newMonthlyExpense ) {

							$scope.errormassage = "New expenses should not be lessthan all expenses !!!";
						} else if ( $scope.formdata.NonHousingExpense > $scope.formdata.NonHousingExpenses ) {

							$scope.errormassage = "New expenses should not be lessthan all expenses !!!";
						} else {

							$scope.errormassage = "";
							$scope.masked = true;
							$scope.formdata.expensesStart = 2016;
							$scope.formdata.expensesEnd = 2016;

							$http( 
									{
										method: "POST",
										url: "MarriageGoalSpouseIncome",
										data: $.param( $scope.formdata ),
										headers: {
											"Content-Type": "application/x-www-form-urlencoded"
										}
									} ).then( function( result ) {

								if ( result.data.status == "success" ) {
									$( "#myModal1" ).modal( "hide" );
									$scope.load1();
									$scope.masked = false;
								}

							}, function( error ) {

							} );
						}

					}

					$scope.deletegoal = function() {
						$scope.masked = true;
						$scope.formdata.actionHomeType = "deleteGoal";

						$scope.formdata.plan_name = $scope.planName;

						$scope.formdata.goal_id = $scope.goal_id;

						$http( 
								{
									method: "POST",
									url: "MarriageGoal",
									data: $.param( $scope.formdata ),
									headers: {
										"Content-Type": "application/x-www-form-urlencoded"
									}
								} )
								.then( 
										function( result ) {
											$scope.masked = false;
											$scope.message1 = result.data.status;
											if ( $scope.message1 == "success" ) {
												$scope.goDashboard();
											} else {
												window.location.href = "#";
												$scope.errmessage = "Deletion of this goal will result the assets to go neagative !!";
												$( "#myModal" ).modal( "show" );
											}

										},
										function( error ) {
											$scope.message = result.data.status;

										} );

					}

					// ---------------------------------------------------------------------------------

					$scope.checkform3 = function() {
						// //alert();
						$scope.formdata.actionHomeType = "spouseDetails";
						$http( 
								{
									method: "POST",
									url: "Register",
									data: $.param( $scope.formData2 ),
									headers: {
										"Content-Type": "application/x-www-form-urlencoded"
									}
								} ).then( function( result ) {

							$scope.message = result.data;
							console.log( "message" + $scope.message );
						}, function( error ) {
							$scope.message = result.data;
						} );

					};
					$scope.checkform1 = function() {
					
						$scope.masked = true;
						$scope.formdata.goal_id = ( decodeURIComponent( hashes ) );

						if ( $scope.formdata.marriagecost == null
								|| $scope.formdata.marriagecost == undefined
								|| $scope.formdata.marriagecost == "" ) {
							$scope.errmessage1 = "Please Enter The marriage Expense!!!";
							$scope.masked = false;
							$( "#fail-alert" ).show();
						} else if ( $scope.formdata.marriagecost == 0 ) {
							$scope.errmessage1 = "Marriage Expense cannot be zero !!";
							$scope.masked = false;
							$( "#fail-alert" ).show();

						}

						else if ( $scope.formdata.income == null
								|| $scope.formdata.income == undefined ) {
							$scope.masked = false;
							$scope.errmessage1 = "Please enter the value for spouse income!!!";
							$( "#fail-alert" ).show();

						} else if ( $scope.formdata.income == ""
								&& $scope.formdata.income != 0 ) {
							// alert( "3`1" );
							$scope.masked = false;
							$scope.errmessage1 = "Please enter the value for spouse income!!!";
							$( "#fail-alert" ).show();

						} else if ( $scope.formdata.sage == null
								|| $scope.formdata.sage == undefined
								|| $scope.formdata.sage == "" ) {
							$scope.masked = false;
							$scope.errmessage1 = "Please enter the spouse age!!!";
							$( "#fail-alert" ).show();

						}
						else if ( parseInt( $scope.formdata.expensesStart ) > parseInt( $scope.formdata.expensesEnd ) ) {
							$scope.masked = false;
							$scope.errmessage1 = "End year cannot be lessthen start year!!!";
							$( "#fail-alert" ).show();

						}

						else {
							$scope.masked = true;
							$scope.formdata.actionHomeType = "update";
							$http( 
									{
										method: "POST",
										url: "MarriageGoal",
										data: $.param( $scope.formdata ),
										headers: {
											"Content-Type": "application/x-www-form-urlencoded"
										}
									} )
									.then( 
											function( result ) {
												if ( result.data.goalFeasiblity == false ) {
													$scope.masked = false;
													$( "#fail-warning" ).show();
												}
												$scope.goalMarriageData = result.data;
												$scope.messresult = result.data.annualexpense;
												$scope.marriageYear = result.data.marriageYear;
												$scope.marriageCost = result.data.marriageCost;
												$scope.formdata.marriagecost = result.data.marriageCost;
												$scope.formdata.marriageyear = result.data.marriageYear;

												if ( result.data.marriageFail == "you are not able to save your goal of wedding" ) {
													$scope.masked = false;
													$scope.show = 7;
													$scope.result = result.data.marriageFail;
												}
												if ( result.data.marriageSuccess == "You are on your goal to save for your wedding" ) {
													$scope.masked = false;
													$scope.show = 8;
													$scope.result = result.data.marriageSuccess;
												}

												console.log( "message"
														+ $scope.message );
												if ( result.data.status == "success" ) {
													$( "#fail-alert" ).hide();
													$scope.masked = false;
													// ---------Success modal
													// alert------------------------------------------
													$scope.errmessage = " Goal updated successfully";
													$( "#success-alert" ).show();
													$( "#success-alert" )
															.fadeTo( 2000, 300 )
															.slideUp( 
																	300,
																	function() {
																		$( 
																				"#success-alert" )
																				.hide();

																	} );

												} else {
													$scope.masked = false;
													window.location.href = "#";

													$scope.errmessage = "Goal is not feasible since you are not having sufficient funds !!";
													$( "#myModal" ).modal( "show" );
												}
												// -----------------------------------------------------------------------

												console.log( "message"
														+ $scope.message );
												$scope.masked = false;

											}, function( error ) {

											} );

						}

					}
					$scope.reload = function() {

						window.location.href = window.location.href
								.slice( 0, -1 );

					}
					// ---------Success modal alert
					// hide------------------------------------------
					$scope.hideSuccess = function() {
						$( "#success-alert" ).hide();

					}

					$scope.DeleteGoal = function() {

						$( "#myModal2" ).modal( "show" );
					}
					$scope.hideFail = function() {
						$( "#fail-alert" ).hide();

					}
					$scope.hideWarning = function() {
						$( "#fail-warning" ).hide();

					}

					// -----------------------------------------------------------------------
					// --------------------------------------------------------------------------------------
					$scope.goDashboard = function() {

						window.location.href = "dashboardUser0.jsp?finName="
								+ $scope.planName;
					}

					$scope.backwithoutsave = function( formdata )

					{

						if ( $scope.marriageCost !== formdata.marriagecost
								|| $scope.marriageYear != formdata.marriageyear ) {
							$( "#myModalback" ).modal( "show" );

						} else {
							$scope.goDashboard();
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

					$scope.load = function() {

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

				} );