angular.module('wealthsetter').controller('CarleaseCtrl', ['$scope','$uibModal', '$log','$http','$rootScope','$state' ,CarleaseCtrl]);
function CarleaseCtrl($scope,$uibModal, $log,$http,$rootScope,$state,$uibModalInstance) {
	                $.material.init();
					$scope.show = 1;
					$scope.sessionDetails = {};
					$scope.sessionDelete = {};
					$scope.inputData = {};
					$scope.formdata = {};
					$scope.monthlypayment;
					 $scope.showResults = true;
					$scope.guest = false;
					/*$scope.showSideInput = true;*/
					$rootScope.boolChangeClass = true;
					
					$rootScope.userFlag = false;
					$rootScope.homeLocation = "index";
					$scope.load = function() {

						$scope.formdata.carPrice = "30000";
						$scope.formdata.loanTerm = "4";
						$scope.formdata.interestRate = "3";
						$scope.formdata.downPayment = "5000";
						$scope.formdata.tradeInValue = "5000";
						$scope.formdata.licenseFee = "250";
						$scope.formdata.acquisitionCost = "500";
						$scope.formdata.residualValue = "50";
						$scope.formdata.saleTax = "8.25";

						$scope.loading = false;
						$scope.masked = false;
						$scope.calculate();
						$scope.cle_msgerr = "";
						$scope.sessionDetails.cookieId = readCookie( "AhTwxlO" );
						$scope.sessionDetails.lastVisitedPage = document.URL;

						$http( 
								{
									method: "POST",
									url: "CheckSession",
									data: $.param( $scope.sessionDetails ),
									headers: {
										"Content-Type": "application/x-www-form-urlencoded"
									}
								} ).then( function( result ) {

							console.log( result.data );
							if ( result.data.status == "success" ) {
								$scope.guest=false;
								$rootScope.userFlag = true;
								 $rootScope.homeLocation = "dashboard.home";

							} else {
								$scope.guest=true;
								 $rootScope.userFlag = false;
								 $rootScope.homeLocation = "index";

							}

						}, function( error ) {
						} );

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
					$scope.Reset = function() {
						$scope.formdata.carPrice="";
						$scope.formdata.loanTerm ="";
						 $scope.formdata.interestRate="";
						 $scope.formdata.downPayment="";
						 $scope.formdata.tradeInValue="";
						 $scope.formdata.licenseFee="";
						 $scope.formdata.acquisitionCost="";
						 $scope.formdata.residualValue="";
						 $scope.formdata.saleTax ="";
						 $scope.showResults = false;
						/* 
						 scope.monthlyinterestrate2="";
						 $scope.interestrate="";
						 $scope.loan="";
						 $scope.monthlypayment1=""*/
						
					}
					
					$scope.calculate = function() {

						if ( $scope.formdata.carPrice == null
								|| $scope.formdata.carPrice == undefined
								|| $scope.formdata.carPrice == "" 
								|| $scope.formdata.carPrice == 0) {
							$scope.cle_msgerr = "Car price should not be empty";
						} else if ( $scope.formdata.loanTerm == null
								|| $scope.formdata.loanTerm == undefined
								|| $scope.formdata.loanTerm == ""
								|| $scope.formdata.loanTerm == 0 ) {

							$scope.cle_msgerr = "Loan term should not be empty";
						}

						else if ( $scope.formdata.interestRate == null
								|| $scope.formdata.interestRate == undefined
								|| $scope.formdata.interestRate == ""
								|| $scope.formdata.interestRate == 0 ) {

							$scope.cle_msgerr = "Interest rate should not be empty";
						} else if($scope.formdata.interestRate >100){
							$scope.cle_msgerr = "Interest rate should be less than or equal to hundred percentage";
						}
						
						else if ( $scope.formdata.downPayment == null
								|| $scope.formdata.downPayment == undefined
								|| $scope.formdata.downPayment == ""
								|| $scope.formdata.downPayment == 0 ) {

							$scope.cle_msgerr = "Down payment should not be empty";
						} else if ( $scope.formdata.downPayment * 1 > $scope.formdata.carPrice * 1 ) {
									
									$scope.cle_msgerr = " Down payment should be less than carprice";
									
								}else if ( $scope.formdata.tradeInValue == null
								|| $scope.formdata.tradeInValue == undefined
								|| $scope.formdata.tradeInValue == ""
								|| $scope.formdata.tradeInValue == 0 ) {

							$scope.cle_msgerr = "Trade_in value should not be empty";
						}else if ( $scope.formdata.tradeInValue * 1 > $scope.formdata.carPrice * 1 ) {
							$scope.cle_msgerr = "Trade_in value should be less than carprice";
							
						} else if (( Number( $scope.formdata.downPayment) + Number($scope.formdata.tradeInValue)) > Number($scope.formdata.carPrice) ) {
							
							$scope.cle_msgerr = " Sum of Downpayment and Tradevalue less than carprice";
							
						}else if ( $scope.formdata.licenseFee == null
								|| $scope.formdata.licenseFee == undefined
								|| $scope.formdata.licenseFee == "" ) {

							$scope.cle_msgerr = "License fee should not be empty";
					} else if ( $scope.formdata.acquisitionCost == null
								|| $scope.formdata.acquisitionCost == undefined
								|| $scope.formdata.acquisitionCost == "" ) {

							$scope.cle_msgerr = "Acquisition cost should not be empty";
								}

						else if ( $scope.formdata.residualValue == null
								|| $scope.formdata.residualValue == undefined
								|| $scope.formdata.residualValue == ""
								|| $scope.formdata.residualValue == 0 ) {

							$scope.cle_msgerr = "Residual value should not be empty";
							
						} else if ( $scope.formdata.saleTax == null
								|| $scope.formdata.saleTax == undefined
								|| $scope.formdata.saleTax == ""
								|| $scope.formdata.saleTax == 0 ) {

							$scope.cle_msgerr = "Sale Tax should not be empty";
							
						}else if ($scope.formdata.saleTax > 100){
							$scope.cle_msgerr = "Sale Tax should be less than or equal to hundred percentage";
						}

						else {
							

							$scope.inputData.action = "edit1";
							$scope.inputData.car_price = $scope.formdata.carPrice;
							$scope.inputData.loan_term = $scope.formdata.loanTerm;
							$scope.inputData.interest_rate = $scope.formdata.interestRate;
							$scope.inputData.down_payment = $scope.formdata.downPayment;
							$scope.inputData.tradein_value = $scope.formdata.tradeInValue;
							$scope.inputData.license_fee = $scope.formdata.licenseFee;
							$scope.inputData.acquisition_cost = $scope.formdata.acquisitionCost;
							$scope.inputData.residual_value = $scope.formdata.residualValue;
							$scope.inputData.sale_tax = $scope.formdata.saleTax;

							console.log( $scope.inputData );
							$http( 
									{
										method: "POST",
										url: "CarLeaseCalculator",
										data: $.param( $scope.inputData ),
										headers: {
											"Content-Type": "application/x-www-form-urlencoded"
										}
									} )
									.then( 
											function( result ) {
												
												console.log( result.data );
												if ( result.data.status == "success" ) {
													/*$state.go('calculator.carleaseresult');*/
													$scope.cle_msgerr = "";
													$scope.monthlyinterestrate2 = result.data.monthlyinterestrate2
															.toFixed( 2 );
													$scope.interestrate = result.data.interestrate
															.toFixed( 2 );
													$scope.loan = result.data.loan;
													$scope.monthlypayment1 = result.data.monthlypayment1
															.toFixed( 2 );
													
												}


											}, function( error ) {

											} );

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

				}
