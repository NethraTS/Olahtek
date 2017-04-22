var app = angular.module( "Car", [] );
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
				"car",
				function( $http, $scope ) {
					
					$scope.show = 1;
					$scope.showReccurssivePeriod=true;
					$scope.sessionDetails = {};
					$scope.sessionDelete = {};
					$scope.formdata = {};
					$scope.items = [ "2017", "2018", "2019", "2020", "2021",
							"2022", "2023", "2024", "2025", "2026","2027", "2028", "2029", "2030", "2031",
							"2032", "2033", "2034", "2035", "2036"];

					$scope.downPayments = [ {
						name: "0"
					}, {
						name: "1"
					}, {
						name: "2"
					}, {
						name: "3"
					}, {
						name: "4"
					}, {
						name: "5"
					}, {
						name: "6"
					}, {
						name: "7"
					}, {
						name: "8"
					}, {
						name: "9"
					}, {
						name: "10"
					}, {
						name: "15"
					}, {
						name: "20"
					}, {
						name: "25"
					}, {
						name: "30"
					}, {
						name: "35"
					}, {
						name: "40"
					}, {
						name: "45"
					}, {
						name: "50"
					}, {
						name: "60"
					}, {
						name: "70"
					}, {
						name: "80"
					}, {
						name: "90"
					}, {
						name: "100"
					} ];
					$scope.years = [ {
						year: "2",
						value: "55"
					}, {
						year: "3",
						value: "50"
					}, {
						year: "4",
						value: "45"
					} ];
					$scope.times=  [{year:'1',value:'55'},{year:'2',value:'50'},{year:'3',value:'45'},{year:'4',value:'45'},{year:'5',value:'45'}];
					$scope.values = [ "30", "35", "40", "45", "50", "55", "60" ];
					$scope.time = [ "3", "4", "5" ];
					$scope.creditScores = [ {
						name: "760-850"
					}, {
						name: "700-759"
					}, {
						name: "680-699"
					}, {
						name: "660-679"
					}, {
						name: "640-659"
					}, {
						name: "620-639"
					} ];
					$scope.goalCarData = {};
					$scope.formdata.carYear = "2017";
					$scope.formdata.carPrice = "35000";
					$scope.formdata.leaseYear = "3";
					$scope.formdata.moneyFactor = "0.00125";
					$scope.formdata.intrestRate = "3";
					$scope.formdata.timePeriod = "3";
					$scope.value = "";
					$scope.formdata.rentLease = "1";
					$scope.message = "Car buying year";
					$scope.masked = false;
					$scope.progressbar = 0;
					$scope.formdata.plan_name = ( decodeURIComponent( hashes ) );
					$scope.formdata.creditscore = "";
					$scope.formdata.intrestrate = "";
					$scope.formdata.residualValue = "";
					$scope.showReccurssiveBuyingPeriod = true;
					$scope.formdata.down_payment = "20";
					$scope.progressBar = function() {
						if ( $scope.formdata.carYear == null
								|| $scope.formdata.carYear == undefined
								|| $scope.formdata.carYear == "" ) {

							$scope.msgerr = "Car buying year should not be empty";

						} else if ( $scope.formdata.carPrice == 0 ) {
							$scope.msgerr = "Car price cannot be zero !!";

						} else {
							$( "#progress_bar" ).css( "width", "35%" );
							$scope.message = "Car price"

							$scope.formdata.cprice = $scope.formdata.carPrice;

							$scope.show = 3;
							$scope.show1 = 2;
						}

					}
					$scope.showPeriods=function()
					{
						$scope.showReccurssivePeriod=false;
					}
					$scope.hidePeriods=function()
					{
						$scope.showReccurssivePeriod=true;
					}
					
					$scope.hidePeriodsBuying = function() {
						$scope.showReccurssiveBuyingPeriod = true;
						$scope.formdata.reccursiveBuyingPeriod = "";
						$scope.formdata.tradeIn = "";
					}
					$scope.showPeriodsBuying = function() {
						$scope.showReccurssiveBuyingPeriod = false;
					}
					
					$scope.progressBar1 = function() {
						var downPayment = +$scope.formdata.down_payment;
						var principalAmount = +$scope.formdata.carPrice;
						if ( $scope.formdata.carPrice == null
								|| $scope.formdata.carPrice == undefined
								|| $scope.formdata.carPrice == "" ) {

							$scope.msgerr2 = "car price should not be empty";

						}

						else {
							$( "#progress_bar" ).css( "width", "70%" );
							$scope.message = "Leasing or Buying"
							$scope.formdata.downPayment = $scope.formdata.down_payment;
							$scope.show = 4;
							$scope.show1 = 3;
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
					$scope.showReccursive=function()
					{
						//alert();
						$scope.show = 10;
						$scope.formdata.carReccursive="LeasingNo";
						//alert($scope.formdata.carReccursive);
					}
					
					$scope.progressBar4 = function() {

						if ( $scope.formdata.rentLease == "1" ) {

							$scope.formdata.rentLease = "Leasing";

						}
						if ( $scope.formdata.rentLease == "Leasing" ) {

							$scope.show = 2;

						} else {

							$scope.show = 5;
							$( "#progress_bar" ).css( "width", "75%" );

						}
					}
						$scope.progressBar3 = function() {

							if ( $scope.formdata.down_payment == null
									|| $scope.formdata.down_payment == "undefined"
									|| $scope.formdata.down_payment == "" ) {

								$scope.msgerr8 = "down Payment should not be empty";

							} else {
								$scope.show1 = 4;
								$scope.show = 8;
								$( "#progress_bar" ).css( "width", "80%" );

							}

						}
						$scope.showcreditScore = function() {

							$scope.formdata.creditsc = "760-850";
							$scope.show = 24;
							$( "#progress_bar" ).css( "width", "90%" );

						}
						$scope.showRecursiveBuying = function() {
							$( "#progress_bar" ).css( "width", "95%" );
							$scope.show = 514;
							$scope.formdata.carBuyingReccursive = "BuyingNo";
						}
						$scope.clearErrorMsgs = function()
						{
							$scope.msgerr14 = "";
						}
						$scope.progressBar5 = function() {
							$scope.masked = true;
							$scope.porceedFlag = false;
							if ( $scope.formdata.timePeriod == null
									|| $scope.formdata.timePeriod == "undefined"
									|| $scope.formdata.timePeriod == "" ) {

								$scope.msgerr4 = "Time period should not be empty";

							} 
							else {
								if ($scope.formdata.carBuyingReccursive == "BuyingYes") {
									if ($scope.formdata.reccursiveBuyingPeriod == null || $scope.formdata.reccursiveBuyingPeriod == undefined || $scope.formdata.reccursiveBuyingPeriod == "" ) {
										$scope.msgerr14 = "Please select recursive buying period";
									} else if ($scope.formdata.tradeIn == null || $scope.formdata.tradeIn == undefined || $scope.formdata.tradeIn == "" ){
										$scope.msgerr14 = "Please enter trade in value";
									} else {
										$scope.porceedFlag = true;
									}
								} else {
									$scope.porceedFlag = true;
								}
								console.log($scope.formdata);
								if($scope.porceedFlag) {
									
									$http( 
											{
												method: "POST",
												url: "Goalcar",
												data: $.param( $scope.formdata ),
												headers: {
													"Content-Type": "application/x-www-form-urlencoded"
												}
											} )
											.then( 
													function( result ) {
							
														$scope.goalCarData = result.data;
														$scope.messresult = result.data.Anual_morgage;
														$scope.intrest = result.data.intrestrate;
														$scope.formdata.intrestrate = result.data.intrestrate;
														$scope.creditscore = result.data.creditscore;
														$scope.formdata.creditscore = result.data.creditscore;
														$scope.tperiod = result.data.tperiod;
														$scope.cprice = result.data.cprice;
														$scope.downPayment = result.data.downPayment;

														if ( $scope.goalCarData.status == "success" ) {

															window.location.href = "GoalCarEdit.jsp?goalId="
																	+ $scope.goalCarData.goal_id;

															console
																	.log( "message"
																			+ $scope.message );
															$scope.masked = false;
														} else {
															$scope.masked = false;
															$scope.errmessage = "Goal is not feasible since you are not having sufficient funds !!";
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

					

					$scope.calculate = function( leaseYear ) {

						if ( $scope.formdata.leaseYear == "2" ) {

							$scope.value = 55;

						} else if ( $scope.formdata.leaseYear == "3" ) {

							$scope.value = 50;

						} else if ( $scope.formdata.leaseYear == "4" ) {

							$scope.value = 45;

						}
					}
					$scope.progressBar2 = function() {

						$( "#progress_bar" ).css( "width", "100%" );
						$( "#dialog_confirm_map" ).modal( "hide" );
						$scope.message = "DETAILS";

						$scope.formdata.caryear = $scope.formdata.carYear;
						$scope.formdata.intrestrate = "0";

						$scope.calculate( $scope.leaseYear );

						$scope.formdata.residualValue = $scope.value;

						$scope.masked = true;

						$http( 
								{
									method: "POST",
									url: "Goalcar",
									data: $.param( $scope.formdata ),
									headers: {
										"Content-Type": "application/x-www-form-urlencoded"
									}
								} )
								.then( 
										function( result ) {

											$scope.goalCarData = result.data;
											$scope.messresult = result.data.Anual_morgage;
											$scope.intrest = result.data.intrestrate;
											$scope.formdata.intrestrate = result.data.intrestrate;
											$scope.creditscore = result.data.creditscore;
											$scope.formdata.creditscore = result.data.creditscore;
											$scope.tperiod = result.data.tperiod;
											$scope.cprice = result.data.cprice;
											$scope.downPayment = result.data.downPayment;

											if ( $scope.goalCarData.status == "success" ) {
												$scope.masked = false;
												$scope.formdata.goal_id = $scope.goalCarData.goal_id;
												$scope.errmessage = "  created successfully !!";

												window.location.href = "GoalCarEdit.jsp?goalId="
														+ $scope.goalCarData.goal_id;

												console.log( "message"
														+ $scope.message );
												$scope.masked = false;
											} else {
												$scope.masked = false;
												$scope.errmessage = "Goal is not feasible since you are not having sufficient funds !!";
												$( "#myModal" ).modal( "show" );

											}
										}, function( error ) {
											$scope.message = result.data;

										} );

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
					// -------------------onload funtion--------------------
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

					$scope.goSelects = function() {

						window.location.href = "goals.jsp?finName="
								+ $scope.formdata.plan_name;
					}
					$scope.goSelect = function() {

						window.location.href = "goals.jsp?finName="
								+ $scope.formdata.plan_name;
					}
					$scope.goDashboard = function() {

						window.location.href = "dashboardUser0.jsp?finName="
								+ $scope.formdata.plan_name;
					}

				} );