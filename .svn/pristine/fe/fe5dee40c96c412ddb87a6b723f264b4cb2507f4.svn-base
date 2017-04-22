var app = angular.module( "formApp2", [] );
app.controller( "formController2",function( $http, $scope ) {
					$scope.show = 1;

					$scope.sessionDetails = {};
					$scope.sessionDelete = {};
					$scope.inputData = {};
					$scope.formdata = {};
					$scope.monthlypayment;
					$scope.temp = [];
					$scope.Annual = [];
					$scope.guest = false;
					$scope.showAnnualTable = false;
					$scope.showMonthlyTable = true;

					$scope.showAnnualData = function() {
						$scope.showAnnualTable = false;
						$scope.showMonthlyTable = true;
					}
					
					$scope.showMonthlyData = function() {
						$scope.showAnnualTable = true;
						$scope.showMonthlyTable = false;
					}
					
					$scope.load = function() {

						$scope.formdata.carPrice = "30000";
						$scope.formdata.loanTerm = "4";
						$scope.formdata.interestRate = "4";
						$scope.formdata.downPayment = "1000";
						$scope.formdata.tradeInValue = "4000";
						$scope.formdata.licenseFee = "500";
						$scope.formdata.saleTax = "8.25";
						$scope.loading = false;
						$scope.masked = false;
						$scope.calculate();
						$scope.drawChart();
						$scope.sessionDetails.cookieId = readCookie( "AhTwxlO" );
						$scope.sessionDetails.lastVisitedPage = document.URL;

				$http({
					                method: "POST",
									url: "CheckSession",
									data: $.param( $scope.sessionDetails ),
									headers: {
										"Content-Type": "application/x-www-form-urlencoded"
									}
								} ).then( function( result ) {

							console.log( result.data );
							if ( result.data.status == "success" ) {
								$scope.guest = false;
							} else {
								$scope.guest = true;
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

					$scope.calculate = function() {

						if ( $scope.formdata.carPrice == null
								|| $scope.formdata.carPrice == undefined
								|| $scope.formdata.carPrice == "" ) {
							$scope.clc_msgerr = "Car price should not be empty";
						} else if ( $scope.formdata.carPrice == 0 ) {
							$scope.clc_msgerr = "Car price should not be zero";
						} else if ( $scope.formdata.loanTerm == null
								|| $scope.formdata.loanTerm == undefined
								|| $scope.formdata.loanTerm == ""
								|| $scope.formdata.loanTerm == 0 ) {
							$scope.clc_msgerr = "Loan term should not be empty";
						}

						else if ( $scope.formdata.interestRate == null
								|| $scope.formdata.interestRate == undefined
								|| $scope.formdata.interestRate == ""
								|| $scope.formdata.interestRate == "0" ) {
							$scope.clc_msgerr = "Interest rate should not be empty and zero";
						} else if ( $scope.formdata.downPayment == null
								|| $scope.formdata.downPayment == undefined
								|| $scope.formdata.downPayment == "" ) {
							$scope.clc_msgerr = "Down payment should not be empty";
						}else if ( $scope.formdata.downPayment * 1 > $scope.formdata.carPrice * 1 ) {
							
							$scope.clc_msgerr = "Down payment should be less than carprice";
							
						} else if ( $scope.formdata.tradeInValue == null
								|| $scope.formdata.tradeInValue == undefined
								|| $scope.formdata.tradeInValue == "" ) {
							$scope.clc_msgerr = "Trade-in value should not be empty";
						} else if ( $scope.formdata.tradeInValue * 1 > $scope.formdata.carPrice * 1 ) {
							$scope.clc_msgerr = " Trade_in value should be less than carprice";
							
						} else if ((Number( $scope.formdata.downPayment) + Number($scope.formdata.tradeInValue)) > Number($scope.formdata.carPrice) ) {
							
							$scope.clc_msgerr = " Sum of Downpayment and Tradevalue less than carprice";
							
						}else if ( $scope.formdata.licenseFee == null
								|| $scope.formdata.licenseFee == undefined
								|| $scope.formdata.licenseFee == "" ) {
							$scope.clc_msgerr = "licenseFee should not be empty";
						} else if ( $scope.formdata.saleTax == null
								|| $scope.formdata.saleTax == undefined
								|| $scope.formdata.saleTax == "" ) {
							$scope.clc_msgerr = "Sale tax should not be empty";
						} else {
							$scope.inputData.action = "edit1";
							$scope.inputData.car_price = $scope.formdata.carPrice;
							$scope.inputData.loan_term = $scope.formdata.loanTerm;
							$scope.inputData.interest_rate = $scope.formdata.interestRate;
							$scope.inputData.down_payment = $scope.formdata.downPayment;
							$scope.inputData.trade_invalue = $scope.formdata.tradeInValue;
							$scope.inputData.license_fee = $scope.formdata.licenseFee;
							$scope.inputData.sale_tax = $scope.formdata.saleTax;
							console.log( $scope.inputData );
							$http( {
										method: "POST",
										url: "CarLoanCalculator",
										data: $.param( $scope.inputData ),
										headers: {
											"Content-Type": "application/x-www-form-urlencoded"
										}
									} )
									.then( function( result ) {

												console.log( result.data );
												if ( result.data.status == "success" ) {

													$scope.clc_msgerr = "";
													$scope.netcapitalizedcost = result.data.netcapitalizedcost;
													$scope.interest = result.data.interest.toFixed( 2 ) + "%";
													$scope.loan = result.data.loan;
													$scope.monthlypayment = result.data.monthlypayment.toFixed( 0 );
													$scope.Annual = result.data.Annual;
													$scope.Annual1 = result.data.Annual1;
													$scope.temp = result.data.finalMonthly;
													$scope.interest1 = 0;
													$scope.principal = 0;
													for ( i = 0; i < $scope.Annual.length; i++ ) {
														$scope.interest1 = $scope.Annual[i].interest1
																+ $scope.interest1;
														$scope.principal = $scope.netcapitalizedcost;
													}

												$scope.drawChart( $scope.interest1,$scope.principal );
												}

											}, function( error ) {
											} );
						}

					}

					$scope.drawChart = function( a, b ) {
						var st = {};
						st.data = [ {
							"label": "Principal",
							"value": b,
							"pos": 0
						}, {
							"label": "Interest",
							"value": a,
							"pos": 1
						} ];
						drawPieChartAccessAgesByCountD3();
						window.onresize = function( event ) {
							drawPieChartAccessAgesByCountD3();

						}
						function drawPieChartAccessAgesByCountD3() {
							drawD3PieChart( "#ChartAccessAgesByCountD3",
									st.data, [ 0, 1, 2, 3 ] );
						}

						function drawD3PieChart( sel, data, row_id_to_bucket_num ) {
							$( sel + " svg" ).remove();
							tot = 0;
							data.forEach( function( e ) {
								tot += e.value;
							} );
							var w = $( sel ).width();
							var h = $( sel ).height();
							var r = Math.min( w, h ) / 2;
							var color = d3.scale.category20c();
							var vis = d3.select( sel ).append( "svg:svg" )
							
							.attr( "data-chart-context", sel ).data( [ data ] ).attr( "width", w ).attr( "height", h ).append( "svg:g" )
							.attr( "transform","translate(   " + ( w / 2 ) + "," + r + "   )" );
							var svgParent = d3.select( "svg[data-chart-context='" + sel + "']" );
							var pie = d3.layout.pie().value( function( d ) {
								return d.value;
							} );
							var arc = d3.svg.arc().outerRadius( r );
							var arcs = vis.selectAll( "g.slice" ).data( pie ).enter().append( "svg:g" ).attr( "class", "slice" );
							arcs
									.append( "svg:path" )
									.attr( "fill", function( d, i ) { return color( i ); } )
									.attr( "stroke", "#fff" )
									.attr( "stroke-width", "1" )
									.attr( "d", function( d ) {return arc( d );} )
									.attr( "data-legend", function( d ) {return d.data.label;} )
									.attr( "data-legend-pos", function( d ) {return d.data.pos;} )
									.classed( "slice", true )
									.on( "click",function( e ) {
												var chartDiv = $( sel );var selectedValue = $( this ).attr( "data-legend-pos" );
												var bucket = row_id_to_bucket_num[selectedValue];
												var dest = chartDiv
														.attr( "data-drilldown-destination" );
												var param = chartDiv
														.attr( "data-drilldown-key" );
												var baseURL = dest + "/?"
														+ param + "=";
											} ).on( "mouseover",
											function( e ) {
												$( this ).attr( "fill-opacity", ".8" ).css( { "stroke": "green", "stroke-width": "1px"} );
											} ).on( "mouseout", function( e ) {
										$( this ).attr( "fill-opacity", "1" ).css( {
											"stroke-width": "0px"
										} );
									} ).attr( "style", "cursor:pointer;" ).append( "svg:title" ).text( function( d ) {
										return d.data.label;
									} );

							arcs.append( "svg:text" ).attr( "transform",function( d ) {
										d.innerRadius = 0;
										d.outerRadius = r;
										return "translate(   " + arc.centroid( d ) + "   )";
								} ).attr( "text-anchor", "middle" ).text( function( d, i ) {
										return ( data[i].value )
									} ).attr( "fill", "#fff" ).classed( "slice-label", true );

							var legend = d3.select( "#ChartAccessAgesByCountD3" )
									.append( "svg" ).attr( "class", "legend" )
									.attr( "width", r ).attr( "height", r * 2 ).selectAll( "g" ).data( data ).enter().append( "g" ).attr( "transform",
											function( d, i ) { return "translate( 0," + i * 20 + " )";} );

							legend.append( "rect" ).attr( "width", 18 ).attr( "height", 18 ).style( "fill", function( d, i ) {
								return color( i );} );
	legend.append( "text" ).attr( "x", 24 ).attr( "y", 9 )
									.attr( "dy", ".35em" ).text( function( d ) {return d.label;} );
						}
					}

					$scope.deleteAllCookies = function() {
						$scope.sessionDelete.sessionID = readCookie( "AhTwxlO" );
						$http({
									method: "POST", url: "Logout",
									data: $.param( $scope.sessionDelete ), headers: {
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

				} );
