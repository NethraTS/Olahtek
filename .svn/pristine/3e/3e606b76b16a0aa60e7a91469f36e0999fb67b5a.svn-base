var app = angular.module('formApp3', []);
var federalTax=0;
var fICAMedicareTax=0;
var fICASocialSecurityTax=0;
var stateTax=0;

var url = window.location.href;
var hashes = url.split("=")[1];
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
		
app
		.controller(
				'formController2',
				function($http, $scope) {
					$scope.user_id = "";
					$scope.messageChangeIncome = "";
					$scope.user_id;
					$scope.inputData = {};
					$scope.ages = [];
					$scope.preChangedValue;
					$scope.masked = false;
					$scope.maskedPlan=false;
					$scope.maskedPlotChart=true;
					$scope.preStartIndex;
					$scope.preEndIndex;
					$scope.checkDrag = 0;
					$scope.checkDrag1 = 0;
					$scope.preChangedValue1;
					$scope.preStartIndex1;
					$scope.Math = window.Math;
					$scope.preEndIndex1;
					$scope.profileNameCheckbox = "";
					$scope.fin_name = (decodeURIComponent(hashes));
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
					$scope.tableIncome_imp=[];
					$scope.maritalFlag_imp=false;
					$scope.chartCombinedIncome = [];
					$scope.Count = 0;
					$scope.chartAssets = [];
					$scope.chartGoals = [];
					$scope.assetGoals = [];
					$scope.fICAMedicareTax =0;
					$scope.fICASocialSecurityTax=0;
					$scope. TotalIncomeTaxes=0;
					$scope.TotalFDI=0;
					$scope.incomeAfterTaxes=0;
					$scope.federalTax=0;
					$scope.Taxliability=0;
					$scope.stateTax=0;
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
					$scope.FinPlanName="";
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
					$scope.finPlanDetails=false;
					$scope.incomeProfileDetails=true;
					$scope.calculateFdi= false;
					$scope.retirementAge = ['62','63','64','65','66','67','68','69','70'];
					$scope.userSSB= false;
					 $scope.guest=false;
					$scope.incomeProfile = [];
					$scope.states = [ {name:'ALABAMA'},{name:'ALASKA'},{name:'ARIZONA'},{name:'ARKANSAS'},{name:'CALIFORNIA'},{name:'COLORADO'},{name:'CONNECTICUT'},{name:'DELAWARE'},{name:'FLORIDA'},{name:'GEORGIA'},{name:'HAWAII'},{name:'IDAHO'},{name:'ILLINOIS'},{name:'INDIANA'},{name:'IOWA'},{name:'KANSAS'},{name:'KENTUCKY'},{name:'LOUISIANA'},{name:'MAINE'},{name:'MARYLAND'},{name:'MASSACHUSETTS'},{name:'MICHIGAN'},{name:'MINNESOTA'},{name:'MISSISSIPPI'},{name:'MISSOURI'},{name:'MONTANA'},{name:'NEBRASKA'},{name:'NEVADA'},{name:'NEW HAMPSHIRE'},{name:'NEW JERSEY'},{name:'NEW MEXICO'},{name:'NEW YORK'},{name:'NORTH CAROLINA'},{name:'NORTH DAKOTA'},{name:'OHIO'},{name:'OKLAHOMA'},{name:'OREGON'},{name:'PENNSYLVANIA'},{name:'RHODE ISLAND'},{name:'SOUTH CAROLINA'},{name:'SOUTH DAKOTA'},{name:'TENNESSEE'},{name:'TEXAS'},{name:'UTAH'},{name:'VERMONT'},{name:'VIRGINIA'},{name:'WEST VIRGINIA'},{name:'WISCONSIN'},{name:'WYOMING'},{name:'WASHINGTON'},{name:'WASHINGTON DC'}];
					$scope.filingOptions= [ {id: 'Single'}, {id: 'Head of Household'},{id: 'Married Filing Jointly'} ];
					$scope.filingOptions1=[ {id: 'Risk aggressive'}, {id: 'Risk neutral'},{id: 'Risk Adverse'} ];
					$scope.load = function() {
						
						  $scope.formdata.state = 'ALABAMA';
						  $scope.formdata.filingStatus='Single';
						  $scope.formdata.homeValue='75000';
						  $scope.formdata.personalexe='1';  
						   $scope.TotalFDI="5000";
						$scope.fICAMedicareTax ='20000';
						$scope.fICASocialSecurityTax='2000';
						$scope.stateTax='3000';
						$scope.TotalIncomeTaxes='60000';
					    $scope.incomeAfterTaxes='60000';
						$scope.Taxliability='600000';
						$scope.federalTax='5000';
						//$scope.drawChart(federalTax,fICAMedicareTax,fICASocialSecurityTax,stateTax);
						$scope.calculate();
						$scope.drawChart(5000,20000,2000,3000);	
						
						$scope.sessionDetails.cookieId = readCookie("AhTwxlO");
						$scope.sessionDetails.lastVisitedPage = document.URL;
						
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
											
											console.log(result.data);
											if (result.data.status == "success") {
												 $scope.guest=false;
												
											}
											else{
												 $scope.guest=true;
												
											}
												
										}, function(error) {
										});
							
						       
								
						
						
						
						//alert("load-->>> ");
						/*
						$scope.sessionDetails.cookieId = readCookie("AhTwxlO");
						$scope.sessionDetails.lastVisitedPage = document.URL;
						
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
												console.log(result.data);
												if (result.data.status == "success") {
													$scope.user_id = result.data.user_id;
													console.log($scope.user_id);
													if (result.data.lastVisitedPage == "undefined") {
														window.location.href = "dashboardUserr0.jsp";
													} else {
														document.cookie = "lastVisitedPage="
																+ result.data.lastVisitedPage;
														if (result.data.lastVisitedPage == document.URL) {
														}
													}
													
												} else {
													$scope.errMessage = "Session got expired";
													$("#checkSession").modal("show");
													var delay = 3000; // Your
													setTimeout(
															function() {
																$scope
																		.deleteAllCookies()
															}, delay);
												}

												$scope.masked = false;
												console.log($scope.masked);
											}, function(error) {
											});
						} else {
							// ////alert("Session got expired");
							$scope.deleteAllCookies();
							window.location.href = "index.jsp";
						}*/
						
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
					$scope.update2 = function(){
					    if($scope.formdata.filingStatus == "Single" || $scope.formdata.filingStatus == "Head of Household")
					    	{
					    	$scope.formdata.personalexe="1";
					    	}
					    else if($scope.formdata.filingStatus == "Married Filing Jointly")
					    	$scope.formdata.personalexe="2";
					}
					
					

					
					$scope.update3 = function(){
						//alert($scope.formdata.personalexe);
						if($scope.formdata.filingStatus == "Single" || $scope.formdata.filingStatus == "Head of Household")
				    	{
							if($scope.formdata.personalexe<1 )
					    	{
					    	$scope.errormsg = " Please enter the Personal Exemption greater than 1"
					    	}
							else
						    {
								update5
						    }
				    	}
				    	
						else if($scope.formdata.filingStatus == "Married Filing Jointly")
					    {
							if($scope.formdata.personalexe<2 )
					    	{
					    	$scope.errormsg = " Please Enter the Personal Exemption greater than 2"
					    	}
							else
						    {
						    	$scope.errormsg = "";
						    }
					    }
					    	
					    
					}
					
					$scope.update4 = function(){
						if($scope.formdata.homeValue<1)
				    	{
				    	$scope.errormsg = "Please Enter vaild Gross annual income"
				    	}
						else
					    {
					    	$scope.errormsg = "";
					    }

				
					}
					
					
					
					
					
					
					$scope.refresh=function (){
						
						window.location.reload();
						
					}
					$scope.inputData = {};
					var age = 0;
					$scope.errormsg = "";
				
					$scope.Fdi = function(formdata)
					
					{
					
						if($scope.formdata.state == "" || $scope.formdata.state == undefined)
						{
						
							$scope.errormsg = "Please choose State";
						}
						else if($scope.formdata.Filling_Status=="" || $scope.formdata.Filling_Status==undefined){
							
						$scope.errormsg = "Please choose Status";
						}
						else if($scope.formdata.Gross_Annual_Income == "" || $scope.formdata.Gross_Annual_Income== undefined ){
							
							$scope.errormsg = "Please Enter Gross Annual Income ";
						}
                         else if($scope.formdata.Personal_Exemption == "" || $scope.formdata.Personal_Exemption == undefined )
                        	 {
							
							$scope.errormsg = "Please Enter Personal Exemption  ";
                        	 }
					}
				
					
		
					$scope.calculate=function(formdata)
					{
						$scope.errormsg = "";
						//alert("--->> ");
						
						$scope.inputData.action="FDI";
						$scope.inputData.state=$scope.formdata.state;
						$scope.inputData.filingStatus=$scope.formdata.filingStatus;
						$scope.inputData.homeValue=$scope.formdata.homeValue;
						$scope.inputData.personalexe=$scope.formdata.personalexe;
						//alert(JSON.stringify($scope.inputData));
						$http(
								{
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
										
										//alert(JSON.stringify(result.data.federal));
										if(result.data.status == "success")	{
											//alert("hii");
											//alert(result.data.federal.federalTax);
											$scope.federalTax = result.data.federal.federalTax.toFixed(0);
											//alert(result.data.federal.fICAMedicareTax);
											$scope.fICAMedicareTax = result.data.federal.fICAMedicareTax.toFixed(0);
											$scope.fICASocialSecurityTax = result.data.federal.fICASocialSecurityTax.toFixed(0);
											//alert( result.data.federal.stateTax.toFixed(0));
											$scope.stateTax = result.data.federal.stateTax.toFixed(0);
											$scope.TotalIncomeTaxes=(result.data.federal.federalTax+result.data.federal.fICAMedicareTax+result.data.federal.fICASocialSecurityTax+result.data.federal.stateTax).toFixed(0);
											$scope.TotalFDI= (result.data.federal.federalTax+result.data.federal.fICASocialSecurityTax).toFixed(0);
											$scope.incomeAfterTaxes=(result.data.income-result.data.federal.federalTax-result.data.federal.fICAMedicareTax-result.data.federal.fICASocialSecurityTax-result.data.federal.stateTax).toFixed(0);
											//alert(result.data.federal.maxDeduction);
											$scope.Taxliability=(result.data.income-result.data.federal.maxDeduction-result.data.federal.exemptions).toFixed(0);
											
											//alert(result.data.federal.maxDeduction);
									
											
										
											
											//$scope.federalTax=result.data.;
											//$scope.calculate();
											//alert($scope.Taxliability);
										}
											
											
										//	{
												
											//	$scope.federalTax = $scope.data.federalTax;
												//$scope.FICA = result.data.FICA;
											//	$scope.stateTax = result.data.stateTax;
										     //   $scope.Medicare = result.data.Medicare;
												/*$scope.Tax_liability_before_credits = result.data.Tax_liability_before_credits;
												$scope.Total_Income_Taxes = result.data.Total_Income _axes;
												$scope.Incom_ After_Taxes = result.data.Income_After_Taxes;
												*/
												
												//console.log($scope.showValues);
												//$scope.showValues = true;
												//}
											
										//}
										
										
										federalTax=result.data.federal.federalTax;
										fICAMedicareTax=result.data.federal.fICAMedicareTax;
										fICASocialSecurityTax=result.data.federal.fICASocialSecurityTax;
										stateTax=result.data.federal.stateTax;
										//alert("hjhjh");
										//alert(federalTax);	
										$scope.drawChart(federalTax,fICAMedicareTax,fICASocialSecurityTax,stateTax);
										
										if($scope.formdata.filingStatus == "Single" || $scope.formdata.filingStatus == "Head of Household")
								    	{
											if($scope.formdata.personalexe<1||$scope.formdata.personalexe== ""||$scope.formdata.personalexe==undefined)
									    	{
									    	$scope.errormsg = " Please enter the Personal Exemption greater than 1"
									    		
									    		
									    		
									    		$scope.federalTax = 0;
											//alert(result.data.federal.fICAMedicareTax);
											$scope.fICAMedicareTax =0;
											$scope.fICASocialSecurityTax = 0;
											$scope.stateTax = 0;
											$scope.TotalIncomeTaxes=0;
											$scope.TotalFDI= 0;
											$scope.incomeAfterTaxes=0;
											//alert(result.data.federal.maxDeduction);
											$scope.Taxliability=0;
											
									    	
									    		
									    		
									    	}
											else
										    {
												$scope.errormsg = "";
										    }
								    	}
								    	
										else if($scope.formdata.filingStatus == "Married Filing Jointly")
									    {
											if($scope.formdata.personalexe<2|| $scope.formdata.personalexe== ""||$scope.formdata.personalexe==undefined )
									    	{
									    	$scope.errormsg = " Please Enter the Personal Exemption greater than 2"
									    		

									    		$scope.federalTax = 0;
											//alert(result.data.federal.fICAMedicareTax);
											$scope.fICAMedicareTax =0;
											$scope.fICASocialSecurityTax = 0;
											$scope.stateTax = 0;
											$scope.TotalIncomeTaxes=0;
											$scope.TotalFDI= 0;
											$scope.incomeAfterTaxes=0;
											//alert(result.data.federal.maxDeduction);
											$scope.Taxliability=0;
											
									    	
									    		
									    	}
											else
										    {
										    	$scope.errormsg = "";
										    }
									    }
										
										
										
										
										if($scope.Taxliability<0)
											
								    	{
								    	$scope.errormsg = "FDI Tax Calculation goes negative!! Please reenter the valid gross annual income"
								    		$scope.Taxliability=0;
								    	}
										else
									    {
									    	$scope.errormsg = "";
									    }
										
										
										},function(error) {
										  alert("Session not deleted");
										  

										});
						

						 
						  				
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
					  $scope.drawChart=function (a,b,c,d) {
						 // alert();
					var st = {};
	    			st.data = [{"label":"FederalTax","value":a,"pos":0},{"label":"FICAMedicareTax","value":b,"pos":1},{"label":"FICASocialSecurityTax","value":c,"pos":2},{"label":"StateTax","value":d,"pos":3}] ;


	    			$(document).ready(init);
	    			function init(e) {
	    			    drawPieChartAccessAgesByCountD3();
	    			}

	    			window.onresize = function(event) {
	    			    drawPieChartAccessAgesByCountD3();
	    			}
	    			function drawPieChartAccessAgesByCountD3() {
	    				drawD3PieChart("#ChartAccessAgesByCountD3", st.data, [0, 1, 2, 3]);
	    				}
	    			function drawD3PieChart(sel, data, row_id_to_bucket_num) {
	    				// clear any previously rendered svg
	    				$(sel + " svg").remove();
	    				// compute total
	    				tot = 0;
	    				data.forEach(function(e){ tot += e.value; });		
	    				var w = $(sel).width();
	    				var h = $(sel).height();
	    				var r = Math.min(w, h) /2;		
	    				var color = d3.scale.category20c();
	    				var vis = d3.select(sel).append("svg:svg").attr("data-chart-context",sel).data([data]).attr("width", w).attr("height", h).append("svg:g").attr("transform", "translate(" + (w / 2) + "," + r + ")");
	    				var svgParent = d3.select("svg[data-chart-context='" + sel + "']");
	    				var pie = d3.layout.pie().value(function(d){return d.value;});
	    				var arc = d3.svg.arc().outerRadius(r);
	    				var arcs = vis.selectAll("g.slice").data(pie).enter().append("svg:g").attr("class","slice");
	    				arcs.append("svg:path")
	    					.attr("fill", function(d, i) {
	    						return color(i);
	    					})
	    					.attr("stroke", "#fff")
	    					.attr("stroke-width", "1")
	    					.attr("d", function(d) {
	    						//console.log(arc(d));
	    						return arc(d);
	    					})
	    					.attr("data-legend",function(d) { return d.data.label; })
	    					.attr("data-legend-pos",function(d) { return d.data.pos; })
	    					.classed("slice",true)
	    					.on("click",function(e){				
	    						var chartDiv = $(sel); // retrieve id of chart container div
	    						var selectedValue = $(this).attr("data-legend-pos");
	    						var bucket = row_id_to_bucket_num[selectedValue];
	    						var dest = chartDiv.attr("data-drilldown-destination");
	    						var param = chartDiv.attr("data-drilldown-key");
	    						var baseURL = dest + "/?" + param + "=";
	    						//window.location = baseURL + bucket;
	    			            alert("drill down to " + baseURL + bucket);
	    					})
	    					.on("mouseover",function(e){
	    						$(this)
	    						.attr("fill-opacity", ".8")
	    						.css({"stroke": "green", "stroke-width": "1px"});
	    					})
	    					.on("mouseout",function(e){
	    						$(this)
	    						.attr("fill-opacity", "2")
	    						.css({"stroke-width": "0px"});
	    					})
	    					.attr("style","cursor:pointer;")
	    					.append("svg:title")
	    					   .text(function(d) { return d.data.label; });
	    			    
	    				arcs.append("svg:text").attr("transform", function(d){
	    					d.innerRadius = 0;
	    					d.outerRadius = r;
	    					return "translate(" + arc.centroid(d) + ")";}).attr("text-anchor", "middle").text( function(d, i) {
	    					return (data[i].value / tot ) * 100 > 10 ? ((data[i].value / tot ) * 100).toFixed(1) + "%" : "";
	    					}
	    				).attr("fill","#fff")
	    			    .classed("slice-label",true);
	    			    
	    				var legend = d3.select("#ChartAccessAgesByCountD3").append("svg")
	    			    .attr("class", "legend")
	    			    .attr("width", r)
	    			    .attr("height", r * 2)
	    			    .selectAll("g")
	    			    .data(data)
	    			    .enter().append("g")
	    			    .attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; }); 
	    			legend.append("rect")
	    			    .attr("width", 18)
	    			    .attr("height", 18)
	    			    .style("fill", function(d, i) { return color(i); });
	    			legend.append("text")
	    			    .attr("x", 24)
	    			    .attr("y", 9)
	    			    .attr("dy", ".35em")
	    			    .text(function(d) { return d.label; });
	    			
	    			 
	    			 
	    			}
					  }
				});