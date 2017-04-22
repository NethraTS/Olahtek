var app = angular.module( "MarriageGoal", [] );
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
							var keyCode = event.which || event.keyCode; // I
																		// safely
																		// get
																		// the
																		// keyCode
																		// pressed
																		// from
																		// the
																		// event.
							var keyCodeChar = String.fromCharCode( keyCode ); // I
																			// determine
																			// the
																			// char
																			// from
																			// the
																			// keyCode.

							// If the keyCode char does not match the allowed
							// Regex Pattern, then don"t allow the input into
							// the field.
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
				"Goalmarriage",
				function( $http, $scope ) {
					
					$scope.show = 1;
					$scope.date = new Date();
					$scope.sessionDetails = {};
					$scope.sessionDelete = {};
					$scope.formdata = {};
					$scope.Goalendingyear1 = [ {
						name: $scope.date.getFullYear()
					}, {
						name: $scope.date.getFullYear() + 1
					}, {
						name: $scope.date.getFullYear() + 2
					}, {
						name: $scope.date.getFullYear() + 3
					}, {
						name: $scope.date.getFullYear() + 4
					}, {
						name: $scope.date.getFullYear() + 5
					}, {
						name: $scope.date.getFullYear() + 6
					}, {
						name: $scope.date.getFullYear() + 7
					}, {
						name: $scope.date.getFullYear() + 8
					}, {
						name: $scope.date.getFullYear() + 9
					}, {
						name: $scope.date.getFullYear() + 10
					}, {
						name: $scope.date.getFullYear() + 11
					}, {
						name: $scope.date.getFullYear() + 12
					}, {
						name: $scope.date.getFullYear() + 13
					}, {
						name: $scope.date.getFullYear() + 14
					}, {
						name: $scope.date.getFullYear() + 15
					}, {
						name: $scope.date.getFullYear() + 16
					}, {
						name: $scope.date.getFullYear() + 17
					}, {
						name: $scope.date.getFullYear() + 18
					}, {
						name: $scope.date.getFullYear() + 19
					}, {
						name: $scope.date.getFullYear() + 20
					}, {
						name: $scope.date.getFullYear() + 21
					}, {
						name: $scope.date.getFullYear() + 22
					}, {
						name: $scope.date.getFullYear() + 23
					}, {
						name: $scope.date.getFullYear() + 24
					}, {
						name: $scope.date.getFullYear() + 25
					}, {
						name: $scope.date.getFullYear() + 26
					}, {
						name: $scope.date.getFullYear() + 27
					}, {
						name: $scope.date.getFullYear() + 28
					}, {
						name: $scope.date.getFullYear() + 29
					}, {
						name: $scope.date.getFullYear() + 30
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
					$scope.masked = false;
					$scope.goalMarriageData = {};
					$scope.message = "Marriage Year";
					$scope.progressbar = 0;

				
					$scope.formdata.marriageyear = $scope.date.getFullYear();

					$scope.formdata.plan_name = ( decodeURIComponent( hashes ) );
					$scope.formdata.marriagecost = 20000;

					$scope.progressBar = function() {
						if ( $scope.formdata.marriageyear == null
								|| $scope.formdata.marriageyear == undefined
								|| $scope.formdata.marriageyear == "" ) {

							$scope.msgerr = "Marriage year should not be empty";
						

						} else {
							$( "#progress_bar" ).css( "width", "50%" );
							$scope.message = "Marriage Cost"
							
							$scope.formdata.cprice = $scope.formdata.carprice;

							
							$scope.show = 3;
							$scope.show1 = 2;
						}

					}
					$scope.progressBar1 = function() {
						
						$scope.masked = true;
						$scope.formdata.actionHomeType = "insert";
						if ( $scope.formdata.marriagecost == null
								|| $scope.formdata.marriagecost == undefined
								|| $scope.formdata.marriagecost == "" ) {

							$scope.msgerr2 = "Marriage cost should not be empty";
							$scope.masked = false;

						} else if ( $scope.formdata.marriagecost == 0 ) {

							$scope.msgerr2 = "Marriage cost cannot be zero !!";
							$scope.masked = false;

						} else {
							$( "#progress_bar" ).css( "width", "100%" );
							$( "#dialog_confirm_map" ).modal( "hide" );
							$scope.message = "DETAILS"
							// $scope.masked = false;
							$scope.show = 4;
							$scope.show1 = 3;
							if ( $scope.formdata.income == null
									|| $scope.formdata.income == undefined
									|| $scope.formdata.income == "" ) {
								$scope.errormassage = "Please enter the value for spouse income!!!";
								$scope.masked = false;

							} else if ( $scope.formdata.sage == null
									|| $scope.formdata.sage == undefined
									|| $scope.formdata.sage == "" ) {

								$scope.errormassage = "Please enter the spouse age!!!";
								$scope.masked = false;

							} else if ( $scope.formdata.newMonthlyExpense == null
									|| $scope.formdata.newMonthlyExpense == undefined
									|| $scope.formdata.newMonthlyExpense == "" ) {

								$scope.errormassage = "Please enter housing expenses after marriage!!!";
								$scope.masked = false;

							}

							else if ( $scope.formdata.NonHousingExpenses == null
									|| $scope.formdata.NonHousingExpenses == undefined
									|| $scope.formdata.NonHousingExpenses == "" ) {

								$scope.errormassage = "Please non housing expenses after marriage!!!";
								$scope.masked = false;

							} else if ( parseInt( $scope.formdata.expensesStart ) > parseInt( $scope.formdata.expensesEnd ) ) {

								$scope.errormassage = "End year cannot be lessthen start year!!!";
								$scope.masked = false;

							} else if ( $scope.formdata.HousingExpenses > $scope.formdata.newMonthlyExpense ) {

								$scope.errormassage = "New expenses should not be lessthan all expenses !!!";
								$scope.masked = false;
							} else if ( $scope.formdata.NonHousingExpense > $scope.formdata.NonHousingExpenses ) {

								$scope.errormassage = "New expenses should not be lessthan all expenses !!!";
								$scope.masked = false;
							} else {

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

													$scope.goalMarriageData = result.data;

													if ( $scope.goalMarriageData.status == "success" ) {
														$scope.formdata.goal_id = $scope.goalMarriageData.goal_id; // assign
																													// value
																													// to
																													// goal
																													// id
														$( "#marriage-modal" )
																.modal( "hide" );
														window.location.href = "MarriageGoalEdit.jsp?goalId="
																+ $scope.formdata.goal_id;
														console
																.log( "message"
																		+ $scope.message );
													} else {
														$scope.masked = false;
														$scope.errmessage = "Goal is not feasible since you are not having sufficient funds !!";
														$( "#marriage-modal" )
																.hide();
														$( "#myModal" ).modal( 
																"show" );
													}
												},
												function( error ) {
													$scope.message = result.data;

												} );

							}
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
					

					$scope.checkform1 = function() {

						window.location.href = "#";
						if ( $scope.formdata.marriagecost == null
								|| $scope.formdata.marriagecost == undefined
								|| $scope.formdata.marriagecost == "" ) {

							$scope.errmessage = "Please Enter The marriage Expense!!!";

							$( "#myModal" ).modal( "show" );

						} else if ( $scope.formdata.marriagecost == 0 ) {

							$scope.errmessage = "Marriage Expense cannot be zero !!";

							$( "#myModal" ).modal( "show" );

						}

						else {
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

												$scope.goalMarriageData = result.data;
												$scope.messresult = result.data.annualexpense;
												$scope.marriageYear = result.data.marriageYear;
												$scope.marriageCost = result.data.marriageCost;
												if ( result.data.goalFeasiblity == false ) {
													$scope.show = 7;
													result.data.marriageFail = "you are not able to save your goal of wedding"
													$scope.result = result.data.marriageFail;
												}
												if ( result.data.goalFeasiblity == true ) {
													$scope.show = 8;
													result.data.marriageSuccess = "You are on your goal to save for your wedding"
													$scope.result = result.data.marriageSuccess;
												}
											
												console.log( "message"
														+ $scope.message );

											}, function( error ) {
												$scope.message = result.data;

											} );

							$scope.errmessage = "Goal updated successfully !!";
							$( "#myModal" ).modal( "show" );
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
						// //alert( "hi" );
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
												//	$scope.load1();

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

					$scope.load1 = function() {
						
						$scope.formdata.plan_name = ( decodeURIComponent( hashes ) );
						$( "#success-alert" ).hide();
						$( "#fail-alert" ).hide();
						$scope.formdata.year=$scope.formdata.marriageyear;
					//	alert(JSON.stringify($scope.formdata));
						$scope.formdata.actionHomeType = "edit";
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
									//		alert(JSON.stringify(result.data));
											$scope.goalMarriageData = result.data;
											
											$scope.formdata.HousingExpenses = result.data.HousingExpenses * 12;
											$scope.formdata.NonHousingExpense = result.data.NonHousingExpense * 12;

										} );
					}

					$scope.spouseIncome = function( $goalid ) {
						$scope.errormassage = "";
						$scope.formdata.actionHomeType = "edit";

						$scope.formdata.goal_id = $goalid;

						{

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

				} );
