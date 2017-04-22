var app = angular.module('formApp3', ['nvd3']);
var url = window.location.href;
var hashes = url.split("=")[1];
app.controller(
		'formController3',
		function($http, $scope) {
			$scope.messageChangeIncome = "";
			$scope.ages = [];
			$scope.incomeProfilesForReport=[];
			$scope.goalNames1 = [];
			$scope.reportsstartYearBR=2062;
			$scope.startYearPorfolioBR=2017;
			$scope.count=0;
			$scope.defaultretirementAge = 70;
			$scope.checkDrag = 0;
			$scope.portfoliohide = false;
			$scope.Math = window.Math;
			$scope.fin_name = (decodeURIComponent(hashes));
			$scope.checkboxData = {};
			$scope.incomeProfile={};
			$scope.planName = [];
			$scope.formDataPortfolio = {};
			$scope.expenseBeforGoal=[];
			$scope.expenseAfterGoalDetails=[];
			$scope.newExpense=[];
			$scope.checkboxData.applyThisYear = false;
			$scope.checkboxData.applyGraduallyYear = true;
			$scope.checkboxData.applyFutureYear = true;
			$scope.hideinprintpage=true;
			$scope.formdata = {};
			$scope.IncomeDetails = {};
			$scope.sessionDetails = {};
			$scope.sessionDelete = {};
			$scope.reportData = {};
			$scope.leaseYear = [];
			$scope.income=[];
			$scope.reportsproperties = [];
			$scope.userincome=[];
			$scope.spouseincome=[];
			$scope.combinedincome=[];
			$scope.parseDate = d3.time.format("%Y").parse;
			$scope.chartUserIncome=[];
			$scope.chartSouseIncome=[];
			$scope.chartCombinedIncome=[];
			$scope.chartYear=[];
			$scope.formData = {};
			$scope.afterSubmit = true;
			$scope.beforeSubmit = false;
			$scope.growthTable= false;
			$scope.nospouse = false;
			$scope.growthChart = false;
			$scope.growthTable = true;
			$scope.Porfolio_tableIncome = [];
			$scope.Porfolio_planDetailsAsset = [];
			$scope.Porfolio_planDetailsTax = [];
			$scope.Porfolio_totalExpense = [];
			$scope.Porfolio_userIncome = [];
			$scope.Portfolio_chartYear = [];
			$scope.useriyear=[];
			$scope.incomeyear=[];
			$scope.incomevalue=[];
			$scope.spouseIncomeSave=[];
			$scope.combinedIncome=[];
			$scope.tableIncome=[];
			$scope.totalYearlyExpense=[];
			$scope.kidExpense=[];
			$scope.customizedExpense=[];
			$scope.collegeXpense=[];
			$scope.chartAssets=[];
			$scope.assetGoals=[];
			$scope.retirement=0;
			$scope.cargoal=0;
			$scope.kid=0;
			$scope.customized=0;
			$scope.vacation=0;
			$scope.college=0;
			$scope.emergency=0;
			$scope.housing=0;
			$scope.Marriage=0;
			$scope.backbutton=false;
			$scope.incomeProfileData="";
			$scope.startYear = 0;
			$scope.endYear = 0;
            $scope.PrintDivchart =function(){
              $scope.hideinprintpage=false;
              $window.print();
             }
			$scope.deleteAllCookies = function() {
				$scope.sessionDelete.sessionID = readCookie("AhTwxlO");
				$http({
						method :'POST',
						url :'Logout',
						data :$.param($scope.sessionDelete),
						headers :{
							'Content-Type' : 'application/x-www-form-urlencoded'
						}
					}).then(function(result) {
						window.location.href = "index.jsp";
					}, function(error) {
				});
			}
			$scope.goDashboard=function()
			{
				window.location.href="dashboardUser0.jsp?finName="+ $scope.formdata.plan_name;
			}
			function readCookie(name) {
				var nameEQ = name + "=";
				var ca = document.cookie.split(';');
				for ( var i = 0; i < ca.length; i++) {
					var c = ca[i];
					while (c.charAt(0) == ' ')
						c = c.substring(1, c.length);
					if (c.indexOf(nameEQ) == 0)
						return c.substring(nameEQ.length, c.length);
				}
				return null;
			}
			$scope.load1 = function() {
				$scope.masked = true;
				$scope.formdata.actionHomeType = "OnLoad";
				$scope.marital_status = "";
				$scope.ageUser=0;
					$scope.ages = [ 
					            {
					        		number: '15'
					        	}, {
					        		number: '16'
					        	}, {
					        		number: '17'
					        	}, {
					        		number: '18'
					        	}, {
					        		number: '19'
					        	}, {
					        		number: '20'
					        	}, {
					        		number: '21'
					        	}, {
					        		number: '22'
					        	}, {
					        		number: '23'
					        	}, {
					        		number: '24'
					        	}, {
					        		number: '25'
					        	}, {
					        		number: '26'
					        	}, {
					        		number: '27'
					        	}, {
					        		number: '28'
					        	}, {
					        		number: '29'
					        	}, {
					        		number: '30'
					        	}, {
					        		number: '31'
					        	}, {
					        		number: '32'
					        	}, {
					        		number: '33'
					        	}, {
					        		number: '34'
					        	}, {
					        		number: '35'
					        	}, {
					        		number: '36'
					        	}, {
					        		number: '37'
					        	}, {
					        		number: '38'
					        	}, {
					        		number: '39'
					        	}, {
					        		number: '40'
					        	}, {
					        		number: '41'
					        	}, {
					        		number: '42'
					        	}, {
					        		number: '43'
					        	}, {
					        		number: '44'
					        	}, {
					        		number: '45'
					        	}, {
					        		number: '46'
					        	}, {
					        		number: '47'
					        	}, {
					        		number: '48'
					        	}, {
					        		number: '49'
					        	}, {
					        		number: '50'
					        	}, {
					        		number: '51'
					        	}, {
					        		number: '52'
					        	}, {
					        		number: '53'
					        	}, {
					        		number: '54'
					        	}, {
					        		number: '55'
					        	}, {
					        		number: '56'
					        	}, {
					        		number: '57'
					        	}, {
					        		number: '58'
					        	}, {
					        		number: '59'
					        	}, {
					        		number: '60'
					        	}, {
					        		number: '61'
					        	}, {
					        		number: '62'
					        	}, {
					        		number: '63'
					        	}, {
					        		number: '64'
					        	}, {
					        		number: '65'
					        	}, {
					        		number: '66'
					        	}, {
					        		number: '67'
					        	}, {
					        		number: '68'
					        	}, {
					        		number : '69'
					        	}, {
					        		number : '70'
					        	}, {
					        		number : '71'
					        	}, {
					        		number : '72'
					        	}, {
					        		number : '73'
					        	}, {
					        		number : '74'
					        	}, {
					        		number : '75'
					        	}, {
					        		number : '76'
					        	}, {
					        		number : '77'
					        	}, {
					        		number : '78'
					        	}, {
					        		number : '79'
					        	}, {
					        		number : '80'
					        	}, {
					        		number : '81'
					        	}, {
					        		number : '82'
					        	}, {
					        		number : '83'
					        	}, {
					        		number : '84'
					        	}, {
					        		number : '85'
					        	}, {
					        		number : '86'
					        	}, {
					        		number : '87'
					        	}, {
					        		number : '88'
					        	}, {
					        		number : '89'
					        	}, {
					        		number : '80'
					        	}, {
					        		number : '81'
					        	}, {
					        		number : '82'
					        	}, {
					        		number : '83'
					        	}, {
					        		number : '84'
					        	}, {
					        		number : '85'
					        	}, {
					        		number : '86'
					        	}, {
					        		number : '87'
					        	}, {
					        		number : '88'
					        	}, {
					        		number : '89'
					        	}, {
					        		number : '90'
					        	}, {
					        		number : '91'
					        	}, {
					        		number : '92'
					        	}, {
					        		number : '93'
					        	}, {
					        		number : '94'
					        	}, {
					        		number : '95'
					        	}, {
					        		number : '96'
					        	}, {
					        		number : '97'
					        	}, {
					        		number : '98'
					        	}, {
					        		number : '99'
					        	} ];
					//$scope.filingOptions= [ {id: 'Single'},{id: 'Married Filing Jointly'} ];
					$scope.filingOptions1=[ {id: 'Risk aggressive'}, {id: 'Risk neutral'},{id: 'Risk Adverse'} ];
					
					$scope.refreshPortfolio();
				$http(
						{
							method : 'POST',
							url : 'Report',
							data : $.param($scope.formdata),
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded'
							}
						}).then(function(result) {
							////alert("sucess");
                           // console.log(result.data);
                           // alert(JSON.stringify(result.data))
							$scope.reportData = result.data;
							$scope.goals = result.data.Plans;
							if($scope.goals==0) {
								$scope.masked = false;
								$scope.errMessage = "Currently the profile dosent have any plans to display the reports. Please create a plan to continue !!";
								$("#checkSession1")
								.modal("show");
								var delay = 3000; //Your delay in milliseconds
								setTimeout(
										function() {
											window.history.go(-1);
										}, delay);
							}
							$scope.genaricExpense=result.data.expenseBeforeGoal;
							$scope.incomeProfilesForReport=result.data.incomeProfilesForReport;
							$scope.userDetails=result.data.userDetails;
							$scope.userDetails.birthYear = result.data.userDetails.birthYear;
							// alert(JSON.stringify(result.data));
							//alert(JSON.stringify($scope.genaricExpense));
							//alert($scope.genaricExpense.length);
							for(var k=0; k<$scope.genaricExpense.length; k++){


								$scope.expenseBeforGoal.push({year:$scope.genaricExpense[k].year,expenseBeGoal:$scope.genaricExpense[k].totalExpense});

							}
							//alert("after 1st for ");

							for ( var i = 0; i < $scope.goals.length; i++) {
								//
								$scope.planName.push({
									name : $scope.goals[i]
								});

							}
							
							$scope.formdata.planName=($scope.planName[0].name);
							
							$scope.goaldetails();
							$scope.masked = false;
							
							/* assigning riskScore */
							if($scope.userDetails.riskScore>=20 && $scope.userDetails.riskScore<=26){
							$scope.riskScorePort="Risk aggressive";
							}else if($scope.userDetails.riskScore>=15 && $scope.userDetails.riskScore<=19){
								$scope.riskScorePort="Risk neutral";
							}else if($scope.userDetails.riskScore>=10 && $scope.userDetails.riskScore<=14){
							$scope.riskScorePort="Risk Adverse";
							}
							
							/* Assigning risk status */
							if($scope.userDetails.marital_status=="No"){
								$scope.riskFactor=1;
								$scope.filingStatusPort="Single";
							}else if ($scope.userDetails.marital_status=="Yes"){
								$scope.riskFactor=2;
								$scope.filingStatusPort="Married Filing Jointly";
							}
							/* end of assigning risk status */
							
							/* Assigning age */
							$scope.agePort = $scope.userDetails.age;
							/* end of assigning age */
							
							
							/* assigning plan name */
							$scope.userDetails.plan_name = $scope.formdata.planName;							 
							/* end of assigning plan name */
							
							var formData = {};
							$scope.submitPortfolio1(0);
							//$scope.submitPortfolio2();
							formData.plan_name = $scope.userDetails.plan_name;
							formData.riskScorLe = $scope.userDetails.riskScore;
							formData.riskFactor = $scope.riskFactor;
							formData.age = $scope.userDetails.age;
							formData.formType = "initialSubmit";
							initiateSubmit(formData);
							
							
						}, 
						function(error) {
							$scope.message = result.data;

						});

				$("#incomeProjection").hide();
				//$scope.submitPortfolio2();
			

			}





			//$scope.getIncomeProfile1=function() {}

	$scope.refreshPortfolio = function() {
				$scope.show = 0;
				$scope.firstQuestion = "6";
				$scope.secondQuestion = "6";
				$scope.myVar = "";
				$scope.thirdQuestion = "6";
				$scope.growthRate=0;
				$scope.fourthQuestion = "1";
				
				$scope.afterSubmit = true;
				$scope.beforeSubmit = false;
				$scope.beforecalculate=false;
				$scope.nospouse = false;
				$scope.growthChart = false;
				$scope.Original1=true;
				$scope.Original2=true;
				$scope.Original3=true;
				$scope.growthTable = true;
				$scope.Porfolio_tableIncome = [];
				$scope.Porfolio_planDetailsAsset = [];
				$scope.Porfolio_planDetailsTax = [];
				$scope.Porfolio_totalExpense = [];
				$scope.Porfolio_userIncome = [];
				$scope.Portfolio_chartYear = [];
				$scope.Porfolio_spouseIncome = [];
				$scope.Portfolio_chartAssets = [];
				if ($scope.investmentPortFolio == "Yes") {
					// alert("hello")
					$scope.afterSubmit = true;
					$scope.beforeSubmit = false;
	//				$scope.formDataPortfolio.riskScore = 24;
				//	$scope.submitPortfolio1();
				}
			}

			function initiateSubmit(formData){
				$http(
						{
							method : 'POST',
							url : 'investmentPortfolio',
							async:false,
							data : $.param(formData),
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded'
							}
						})
						.then(
								function(result) {
									//alert(JSON.stringify(result.data.growthRate));
									$scope.growthRate=result.data.growthRate.growthRate.toFixed(2);
									$scope.portfolioDividend=result.data.growthRate.portfolioDividend.toFixed(2);
									$scope.portfolioInterest=result.data.growthRate.portfolioInterest.toFixed(2);
									
									$scope.vtiGrowthRate=(result.data.growthRate.vti10-result.data.growthRate.vtiD).toFixed(0);
									$scope.voeGrowthRate=(result.data.growthRate.voe10-result.data.growthRate.voeD).toFixed(0);
									$scope.veaDGrowthRate=(result.data.growthRate.vea10-result.data.growthRate.veaD).toFixed(0);
									$scope.vwoDGrowthRate=(result.data.growthRate.vwo10-result.data.growthRate.vwod).toFixed(0);
									$scope.vtvDGrowthRate=(result.data.growthRate.vtv10-result.data.growthRate.vtvd).toFixed(0);
									$scope.vbrDGrowthRate=(result.data.growthRate.vbr10-result.data.growthRate.vbrd).toFixed(0);
									
								
									
									$scope.veaD	=result.data.growthRate.veaD.toFixed(0);
									$scope.voeD	=result.data.growthRate.voeD.toFixed(0);
									$scope.vtiD	=result.data.growthRate.vtiD.toFixed(0);
									$scope.vwod	=result.data.growthRate.vwod.toFixed(0);
									$scope.vtvd	=result.data.growthRate.vtvd.toFixed(0);
									$scope.vbrd	=result.data.growthRate.vbrd.toFixed(0);
									
								
									$scope.afterSubmit = false;
									$scope.beforeSubmit = true;
									$scope.masked = false;
									
								}, function(error) {
									$scope.message = result.data;
								});
				
			}

			$(document).click(function(e){
				 if(e.target.id != "test1"){
					 
				$scope.Edit=false;
				$scope.Original=true;
				$scope.Edit1=false;
				$scope.Original1=true;
				$scope.Edit2=false;
				$scope.Original2=true;
				$scope.Original3=true;
				$scope.Edit3=false;
				$scope.Original5=true;
				$scope.Edit5=false;
				 }
			   if(e.target.id == "test"||e.target.id == "test1"){
				
			     e.preventDefault();      
			     e.stopPropagation(); 
			     
			   }
			   else{
				 
				   var scope = angular.element(
						    document.
						    getElementById("page-top")).
						    scope();
						    /*$scope.apply(function () {
						        scope.disabledemo();
						    });
				  */
			   } 
			});
			
			$scope.submitPortfolio2 = function() {
				//alert("sub2-->>");
				 //$("mu2").hide();
				 //$("mu1").hide();
				//alert("hihihihih");
				//	alert($scope.Porfolio_userIncome);
				$scope.beforecalculate=true;
				$scope.masked = true;
				$scope.portfoliohide = true;
				//$scope.maritalFlag_imp=true;
				$scope.growthTable1=true;
				$scope.formDataPortfolio.growthRate=$scope.growthRate;
				//alert(JSON.stringify($scope.filingStatusPort))
				$scope.formDataPortfolio.filingStatusPort=$scope.filingStatusPort;
				$scope.showfiling=$scope.filingStatusPort;
				$scope.formDataPortfolio.portfolioDividend=$scope.portfolioDividend;
				$scope.formDataPortfolio.portfolioInterest=$scope.portfolioInterest;
				$scope.formDataPortfolio.formType="calulateBasedonGrowthRate";
                //alert((JSON.stringify($scope.formDataPortfolio)));
				$http(
						{
							method : 'POST',
							url : 'investmentPortfolio',
							data : $.param($scope.formDataPortfolio),
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded'
							}
						})
						.then(
								function(result) {
									//alert("hii iam manoj");
									//$scope.drawChart(federalTax,fICAMedicareTax,fICASocialSecurityTax,stateTax);
									//salert(JSON.stringify(result.data))
									if (result.data.status=="success")
										{
										$scope.investMessage="";
									$scope.Porfolio_tableIncome=[];
									$scope.Porfolio_planDetailsAsset = result.data.assests;
									$scope.Porfolio_planDetailsTax = result.data.tax;
									$scope.Porfolio_totalExpense = result.data.userExpense;
									$scope.Porfolio_userIncome = result.data.income;
									$scope.Porfolio_userIncome = result.data.income;
									if($scope.filingStatusPort=="Married Filing Jointly") {
										$scope.Porfolio_spouseIncome = result.data.spouseIncome;
									}
									$scope.sum = 0;
									for (i = 0; i < $scope.Porfolio_userIncome.length-1; i++) {
										$scope.Portfolio_chartYear
												.push({
													"label" : $scope.Porfolio_userIncome[i].year
															.toString()
												});
										$scope.sum = $scope.Porfolio_planDetailsAsset[i].savings
												* 1
												+ $scope.Porfolio_planDetailsAsset[i].taxable_investment_amount
												* 1
												+ $scope.Porfolio_planDetailsAsset[i].nontaxable_investment_amount
												* 1;
										$scope.Portfolio_chartAssets
												.push({
													"value" : $scope.sum,
													"allowDrag" : "0"
												})
												
												//alert(JSON.stringify(result.data.spouseIncome));
										
											//$scope.maritalFlag_imp=true;
											//alert($scope.filingStatus)
											
											//alert(JSON.stringify($scope.filingStatusPort));
										//alert($scope.Porfolio_spouseIncome[0].value);
												if($scope.formDataPortfolio.filingStatusPort=="Married Filing Jointly"){
												 
												 $scope.maritalFlag_imp1=true;
											$scope.nospouse = true;
											$scope.Porfolio_tableIncome
													.push({
														id : i,
														value : $scope.Porfolio_userIncome[i].value,
														spouseValue :   $scope.Porfolio_spouseIncome[i].value,
														year : $scope.Porfolio_userIncome[i].year,
														expense : $scope.Porfolio_totalExpense[i].totalExpense,
													
														federalTax : $scope.Porfolio_planDetailsTax[i].federalTax,
														userfICASocialSecurityTax : $scope.Porfolio_planDetailsTax[i].userSSTax,
														spousefICASocialSecurityTax : $scope.Porfolio_planDetailsTax[i].spouseSSTax,
														stateTax : $scope.Porfolio_planDetailsTax[i].stateTax,
														fICAMedicareTax : $scope.Porfolio_planDetailsTax[i].fICAMedicareTax,
														savings : $scope.Porfolio_planDetailsAsset[i].savings,
														taxable_investment_amount : $scope.Porfolio_planDetailsAsset[i].taxable_investment_amount,
														taxable_investment_income : $scope.Porfolio_planDetailsAsset[i].taxableInvestmentIncome,
														nontaxable_investment_amount : $scope.Porfolio_planDetailsAsset[i].nontaxable_investment_amount,
														nonTaxableInvestmentIncome : $scope.Porfolio_planDetailsAsset[i].nonTaxableInvestmentIncome
													});
											}
											else {
												//alert("else-->>");
												 $scope.maritalFlag_imp1=false;
												 $scope.nospouse = false;
												$scope.Porfolio_tableIncome.push({
													id : i,
													value : $scope.Porfolio_userIncome[i].value,
													year : $scope.Porfolio_userIncome[i].year,
													//spouseValue :  $scope.user_income[i].value,
													expense : $scope.Porfolio_totalExpense[i].totalExpense,
													
													federalTax : $scope.Porfolio_planDetailsTax[i].federalTax,
													userfICASocialSecurityTax : $scope.Porfolio_planDetailsTax[i].userSSTax,
													stateTax : $scope.Porfolio_planDetailsTax[i].stateTax,
													fICAMedicareTax : $scope.Porfolio_planDetailsTax[i].fICAMedicareTax,
													savings : $scope.Porfolio_planDetailsAsset[i].savings,
													taxable_investment_amount : $scope.Porfolio_planDetailsAsset[i].taxable_investment_amount,
													taxable_investment_income : $scope.Porfolio_planDetailsAsset[i].taxableInvestmentIncome,
													nontaxable_investment_amount : $scope.Porfolio_planDetailsAsset[i].nontaxable_investment_amount,
													nonTaxableInvestmentIncome : $scope.Porfolio_planDetailsAsset[i].nonTaxableInvestmentIncome
												});
											}
										} 
									//	alert(JSON.stringify($scope.Porfolio_tableIncome));
									/* nvd3 chart for Area chart implementation */
									//chart._options.controlOptions = ['Expanded', 'Stream'];
									$scope.options = {
								            chart: {
								            	showControls: false,
								                type: 'stackedAreaChart',
								                height: 450,
								                margin : {
								                    top: 20,
								                    right: 20,
								                    bottom: 30,
								                    left: 40
								                },
								                x: function(d){return d[0];},
								                y: function(d){return d[1];},
								                useVoronoi: false,
								                clipEdge: true,
								                duration: 100,
								                useInteractiveGuideline: true,
								                xAxis: {
								                    showMaxMin: false,
								                    tickFormat: function(d) {
								                        return d3.time.format('%Y')(new Date(d))
								                    }
								                },
								                yAxis: {
								                    tickFormat: function(d){
								                        return d3.format(',.2f')(d);
								                    }
								                },
								                
								                zoom: {
								                    enabled: true,
								                    scaleExtent: [1, 10],
								                    useFixedDomain: false,
								                    useNiceScale: false,
								                    horizontalOff: false,
								                    verticalOff: true,
								                    unzoomEventType: 'dblclick.zoom'
								                }
								                
								            }
									
								        };
									//alert("hi")
									//alert(JSON.stringify($scope.options.chart));
									//$scope.options.chart.showControls(false);
									var savings = new Array();
									
									var taxableInvestment = new Array();
									var nonTaxableInvestment = new Array();
									var totalAssets = new Array();
									var parseDate = d3.time.format("%Y").parse;
									console.log($scope.Porfolio_tableIncome)
									for(var i=0;i<$scope.Porfolio_tableIncome.length;i++){
										var sav=0;
										var taxable=0;
										var nontaxable =0;
										var year = parseDate(""+$scope.Porfolio_tableIncome[i].year);
										//alert(JSON.stringify(year));
										if($scope.Porfolio_tableIncome[i].savings !== null){
											sav=parseInt($scope.Porfolio_tableIncome[i].savings); 
										}
										
										savings.push(new Array(year,sav));
										
										if($scope.Porfolio_tableIncome[i].taxable_investment_amount !== null){
											taxable=parseInt($scope.Porfolio_tableIncome[i].taxable_investment_amount); 
										}
										taxableInvestment.push(new Array(year,taxable));
										
										if($scope.Porfolio_tableIncome[i].non_taxable_investment_amount !== null){
											nontaxable=parseInt($scope.Porfolio_tableIncome[i].nontaxable_investment_amount); 
										}
										nonTaxableInvestment.push(new Array(year,nontaxable));
									}
									  
									//$scope.getIncomeProfile1=function() {}
									 $scope.data = [
									                {
									                    "key" : "Savings" ,
									                    "values" :savings,
									                    "color" : "rgb( 205, 21, 51 )"
									                },

									                {
									                    "key" : "Taxable Investment" ,
									                    "values" : taxableInvestment,
									                    "color" : "rgb( 255, 87, 34 )"
									                },

									                {
									                    "key" : "Non taxable Investment" ,
									                    "values" : nonTaxableInvestment,
									                    "color" : "rgb( 63, 81, 181 )"
									                }
									            ]
								
									//alert($scope.maritalFlag_imp);
									//$scope.plotPortfolioChart();
									$scope.afterSubmit = false;
									$scope.beforeSubmit = true;
									$scope.masked = false;
									var firstPiePorfolio={};
							        var secondPiePorfolio={};
							        var count=0;
							        var parseDate = d3.time.format("%Y").parse;
							       // console.log($scope.Porfolio_tableIncome);
							        //alert(JSON.stringify($scope.Porfolio_tableIncome));
							        $scope.yearreportsPorfolioBR = new Array();
							       // $scope.yearreportsPorfolioAR = new Array();
							        var retirementAge=0;
							        //console.log($scope.retirementYear);
							        if(parseInt($scope.retirementYear) < parseInt($scope.spouseretirementYear) && $scope.spouseretirementYear !== undefined ){
							        	retirementAge = parseInt($scope.spouseretirementYear);
							        }else{
							        	retirementAge = parseInt($scope.retirementYear);
							        }
							       // alert(JSON.stringify($scope.Porfolio_tableIncome));
							        for(var i=0;i<$scope.Porfolio_tableIncome.length;i++){
							        	firstPiePorfolio=$scope.Porfolio_tableIncome[0];
							        	//alert(JSON.stringify(firstPiePorfolio));
							        	//if(retirementAge >= parseInt($scope.Porfolio_tableIncome[i]['year'])){
							        		//alert("hi");
							        		//alert(JSON.stringify($scope.Porfolio_tableIncome[i]['year']));
							        	
							        		$scope.yearreportsPorfolioBR.push(parseInt($scope.Porfolio_tableIncome[i]['year']));
							        		//$scope.reportsstartYearBR=parseInt($scope.Porfolio_tableIncome[i].year);
							        		//console.log($scope.Porfolio_tableIncome[i]['year'])
							        	//}else{
							        		
							        		if(count==0){
							        			//alert("true")
							        			secondPiePorfolio=$scope.Porfolio_tableIncome[i];
							        			//alert(JSON.stringify(secondPiePorfolio));
							        			//$scope.startYearPorfolioAR=parseInt($scope.Porfolio_tableIncome[i].year);
							        			//console.log($scope.startYearPorfolioAR);
							        			
							        		}
							        		//$scope.yearreportsPorfolioAR.push(parseInt($scope.Porfolio_tableIncome[i]['year']));
							        	    count++;
							        	//}
							        }
							       // console.log(firstPiePorfolio);
							        //console.log(secondPiePorfolio);
							        
								

							       $scope.optionsPiePorfolio = {
								            chart: {
								                type: 'pieChart',
								                height: 500,
								                x: function(d){return d.key;},
								                y: function(d){return d.y;},
								                showLabels: true,
								                duration: 500,
								                labelThreshold: 0.01,
								                labelSunbeamLayout: true,
								                legend: {
								                    margin: {
								                        top: 5,
								                        right: 35,
								                        bottom: 5,
								                        left: 0
								                    }
								                }
								            }
								        };
							   
							        $scope.dataPieChartPorfolioBR = [
											   {
								                    key: "Savings",
								                    y: firstPiePorfolio.savings,
								                    "color" : "rgb( 205, 21, 51 )"
								                },
								                {
								                    key: "Non-Taxable",
								                    y: firstPiePorfolio.nontaxable_investment_amount,
								                    "color" : "rgb( 255, 87, 34 )"
								                },
								                {
								                    key: "Taxable",
								                    y: firstPiePorfolio.taxable_investment_amount,
								                    "color" : "rgb( 63, 81, 181 )"
								                   
								                }
											        ];
								       /* $scope.dataPieChartPorfolioAR = [
				                        {
									                    key: "Savings",
									                    y: secondPiePorfolio.savings
									                },
									                {
									                    key: "Non-Taxable",
									                    y: secondPiePorfolio.nontaxable_investment_amount
									                },
									                {
									                    key: "Taxable",
									                    y: secondPiePorfolio.taxable_investment_amount
									                }
								        ]*/
								        $scope.changeYearPorfolioBR = function(year){
								        	for(var i=0;i<$scope.Porfolio_tableIncome.length;i++){
								        	if(parseInt(year) == parseInt($scope.Porfolio_tableIncome[i]['year'])){

								        		$scope.dataPieChartPorfolioBR = [
								        					                {
								        					                    key: "Savings",
								        					                    y: $scope.Porfolio_tableIncome[i].savings,
								        					                    "color" :"#8E44AD"
								        					                },
								        					                {
								        					                    key: "Non-Taxable ",
								        					                    y: $scope.Porfolio_tableIncome[i].nontaxable_investment_amount,
								        					                    "color" :"rgb(63, 81, 181)"
								        					                },
								        					                {
								        					                    key: "Taxable ",
								        					                    y: $scope.Porfolio_tableIncome[i].taxable_investment_amount,
								        					                    "color" :"rgb(6, 140, 53)"
								        					                }
								        					            ]      
								        	}
								        	}
								        	}
								        	/*$scope.changeYearPorfolioAR = function(year){
								        		for(var i=0;i<$scope.Porfolio_tableIncome.length;i++){
								        		if(parseInt(year) == parseInt($scope.Porfolio_tableIncome[i]['year'])){

								        			$scope.dataPieChartPorfolioAR = [
								        						                {
								        						                    key: "Savings",
								        						                    y: $scope.Porfolio_tableIncome[i].savings
								        						                },
								        						                {
								        						                    key: "Non-Taxable ",
								        						                    y: $scope.Porfolio_tableIncome[i].nontaxable_investment_amount
								        						                },
								        						                {
								        						                    key: "Taxable ",
								        						                    y: $scope.Porfolio_tableIncome[i].taxable_investment_amount
								        						                }
								        						            ]      
								        		}
								        		}
								        		}
*/
									
								}else{
									$scope.investMessage="Assests going negative hence the investment portfolio will not be updated";
									$scope.masked = false;
								}
									}, function(error) {
									$scope.message = result.data;
								});
				
			};
			$scope.submitPortfolio1 = function($var) {

				//alert("sub1");
				//alert("vijay--->>"+$var);
				
				$scope.masked = true;
				$scope.firstQuestion;
				$scope.secondQuestion;
				$scope.thirdQuestion;
				$scope.fourthQuestion;
				//alert($scope.formDataPortfolio.riskFactor);
				$scope.formDataPortfolio.formType="initialSubmit";
				if ($var==1) {
					//alert("if");
					$scope.formDataPortfolio.riskScore = ($scope.fourthQuestion * 1)
							+ ($scope.firstQuestion * 1)
							+ ($scope.secondQuestion * 1)
							+ ($scope.thirdQuestion * 1);
					if($scope.marital_status=="No")
					{
						//alert("no-->");
						
					$scope.formDataPortfolio.riskFactor=1;
					$scope.filingStatusPort=$scope.filingStatus;
					}
				else if ($scope.marital_status=="Yes")
				{
					//alert("yes-->>");
					$scope.formDataPortfolio.riskFactor=2;
					$scope.filingStatusPort=$scope.filingStatus;
				}
			$scope.formDataPortfolio.plan_name = $scope.FinPlanName;
			//alert($scope.ageForRiskCal);
			$scope.formDataPortfolio.age=$scope.ageForRiskCal*1;
			//alert($scope.formDataPortfolio.age);
			$scope.agePort=$scope.ageUser*1;
			//alert($scope.agePort);
			
			//$scope.filingStatusPort="Single";
			if($scope.formDataPortfolio.riskScore>=20 && $scope.formDataPortfolio.riskScore<=26)
				{
				$scope.riskScorePort="Risk aggressive";
				
				}
				
				else if($scope.formDataPortfolio.riskScore>=15 && $scope.formDataPortfolio.riskScore<=19)
					
				{
					$scope.riskScorePort="Risk neutral";
					
				}	
					else if($scope.formDataPortfolio.riskScore>=10 && $scope.formDataPortfolio.riskScore<=14)
						{
						
						$scope.riskScorePort="Risk Adverse";
						}
			
			
				} else {
					
					//alert("else")
					
					$scope.formDataPortfolio.riskScore = $scope.riskScore;
					
					$scope.formDataPortfolio.riskScore=$scope.riskScorePort;
					$scope.showriskScore = $scope.riskScorePort;
					if($scope.formDataPortfolio.riskScore=="Risk aggressive")
						{
						$scope.formDataPortfolio.riskScore=22;
						}
					else if($scope.formDataPortfolio.riskScore=="Risk neutral")
					{
						
						$scope.formDataPortfolio.riskScore=19;
					}
					else{
						$scope.formDataPortfolio.riskScore=12;
					}

					$scope.formDataPortfolio.plan_name = $scope.FinPlanName;
					if ($scope.formDataPortfolio.plan_name == undefined) {
						$scope.formDataPortfolio.plan_name = null;
					}
					
					$scope.formDataPortfolio.filingStatus=$scope.filingStatusPort;
					if($scope.formDataPortfolio.filingStatus=="Single")
					{
						//alert("iam singal-->");
					$scope.formDataPortfolio.riskFactor=1;
			         
					}
				else if ($scope.formDataPortfolio.filingStatus=="Married Filing Jointly")
				{
					//alert("iam marred-->");
					$scope.formDataPortfolio.riskFactor=2;
				}
					$scope.formDataPortfolio.age=$scope.agePort;

				
				
				}
				
				//alert(JSON.stringify($scope.formDataPortfolio));
				$http(
						{
							method : 'POST',
							url : 'investmentPortfolio',
							async: false,
							data : $.param($scope.formDataPortfolio),
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded'
							}
						})
						.then(
								function(result) {
									
									$scope.growthRate=result.data.growthRate.growthRate.toFixed(2);
									//alert("--->>> "+$scope.growthRate);
									$scope.portfolioDividend=result.data.growthRate.portfolioDividend.toFixed(2);
									$scope.portfolioInterest=result.data.growthRate.portfolioInterest.toFixed(2);
									
									$scope.growthRateTemp=result.data.growthRate.growthRate.toFixed(2);
									//alert("fun-->>"+$scope.growthRateTemp);
									$scope.portfolioDividendTemp=result.data.growthRate.portfolioDividend.toFixed(2);
									$scope.portfolioInterestTemp=result.data.growthRate.portfolioInterest.toFixed(2);
									
									$scope.vtiGrowthRate=(result.data.growthRate.vti10-result.data.growthRate.vtiD).toFixed(0);
									$scope.voeGrowthRate=(result.data.growthRate.voe10-result.data.growthRate.voeD).toFixed(0);
									$scope.veaDGrowthRate=(result.data.growthRate.vea10-result.data.growthRate.veaD).toFixed(0);
									$scope.vwoDGrowthRate=(result.data.growthRate.vwo10-result.data.growthRate.vwod).toFixed(0);
									$scope.vtvDGrowthRate=(result.data.growthRate.vtv10-result.data.growthRate.vtvd).toFixed(0);
									$scope.vbrDGrowthRate=(result.data.growthRate.vbr10-result.data.growthRate.vbrd).toFixed(0);
									
								
									
									$scope.veaD	=result.data.growthRate.veaD.toFixed(0);
									$scope.voeD	=result.data.growthRate.voeD.toFixed(0);
									$scope.vtiD	=result.data.growthRate.vtiD.toFixed(0);
									$scope.vwod	=result.data.growthRate.vwod.toFixed(0);
									$scope.vtvd	=result.data.growthRate.vtvd.toFixed(0);
									$scope.vbrd	=result.data.growthRate.vbrd.toFixed(0);
									
								
									$scope.afterSubmit = false;
									$scope.beforeSubmit = true;
									$scope.masked = false;
									$scope.submitPortfolio2();
								}, function(error) {
									$scope.message = result.data;
								});

				
			};
			
			
			$scope.restore = function() {
				d3.selectAll("svg#incomeExpense").remove();

				d3.selectAll("svg#growthAsset").remove();
				tempData = $scope.modifiedchartIncomeBackup;
				tempData1 = $scope.modifiedchartIncomeSpouseBackup;
				$scope.load1();
				//$scope.changeIncomeProfile($scope.incomeProfilesChart);
				//$scope.clearGraph();
				$scope.showIncomeExp = false;
				//SimpleGraph.prototype.update();
				
				//d3.select('#slider3').remove();
				//$scope.load1();
			}
			

			//--------------------code for chart in the advanced section------------------------
			$scope.getIncomeProfile=function($name)
			{
				/*$("#chart-container1").hide();
							$("#chart-container2").hide();*/
				//alert("qqq"+$scope.incomeProfilesForReport)
				//
				//alert("data..."+$scope.incomeProfile.name)
				$("#reportGraph").hide();
				$("#incomeProjection").show();

           
				$scope.incomeProfile.form="getIncomeChart";
            if($name==undefined)
            	{
            	$scope.incomeProfile.name=$scope.incomeProfilesForReport[0];
            	}
            else
            	{
            	$scope.incomeProfile.name=$name;
            	}
				
				//alert(JSON.stringify("india"+$scope.incomeProfile));
				//$scope.incomeProfile.name="constant_income";



				$http({
					method: 'POST',
					url: 'UserProfile',
					data: $.param($scope.incomeProfile),
					headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
				}).then(function(result) {
					$scope.chartUserIncome=[];
					$scope.chartSouseIncome=[];
					$scope.chartCombinedIncome=[];
					$scope.chartYear=[];
					$combined_income=result.data.combined_income;
					$spouse_income=result.data.spouse_income;
					$user_income=result.data.user_income;
					$marital_status=result.data.marital_status;
					
					$scope.incomeProfileUser = new Array();
					$scope.incomeProfileSpouse = new Array();

					$scope.startYear=$user_income[0].year;
					$scope.endYear=$user_income[$user_income.length-1].year;
					for(i=0;i<$user_income.length;i++)
					{
						$scope.chartYear.push({label :$user_income[i].year.toString()});
						$scope.chartUserIncome.push({value:$user_income[i].value, year:$user_income[i].year,"allowDrag": "0"});
						$scope.incomeProfileUser.push(new Array($scope.parseDate(""+$user_income[i].year),$user_income[i].value));
						if($marital_status=="Yes")
						{

							$scope.chartSouseIncome.push({value:$spouse_income[i].value, year:$spouse_income[i].year,"allowDrag": "0"});
							$scope.incomeProfileSpouse.push(new Array($scope.parseDate(""+$spouse_income[i].year),$spouse_income[i].value));
							$scope.chartCombinedIncome.push({value:$combined_income[i].value, year:$combined_income[i].year,"allowDrag": "0"});
						} else{
							$scope.incomeProfileSpouse.push(new Array($scope.parseDate(""+$user_income[i].year),0));
						}
					}
					
					//alert(JSON.stringify("1"+$scope.incomeProfilesForReport));
					//$scope.incomeProfile.name=$scope.incomeProfilesForReport;
					//alert(JSON.stringify("2"+$scope.incomeProfile.name));
					incomeExpenseChart();		
				}, function(error) {		

				});


			}

			$scope.showReportGraph=function(){

				$("#reportGraph").show();
				
			
				
			

				$("#incomeProjection").hide();
			}
			function incomeExpenseChart(){
				$scope.IncomeProfileoptions = {
			            chart: {
			                type: 'lineChart',
			                height: 450,
			                margin : {
			                    top: 20,
			                    right: 20,
			                    bottom: 30,
			                    left: 40
			                },
			                x: function(d){return d[0];},
			                y: function(d){return d[1];},
			                useVoronoi: false,
			                clipEdge: true,
			                duration: 100,
			                useInteractiveGuideline: true,
			                xAxis: {
			                    showMaxMin: false,
			                    tickFormat: function(d) {
			                        return d3.time.format('%Y')(new Date(d))
			                    }
			                },
			                yAxis: {
			                    tickFormat: function(d){
			                        return d3.format(',.2f')(d);
			                    }
			                },
			                zoom: {
			                    enabled: true,
			                    scaleExtent: [1, 10],
			                    useFixedDomain: false,
			                    useNiceScale: false,
			                    horizontalOff: false,
			                    verticalOff: true,
			                    unzoomEventType: 'dblclick.zoom'
			                }
			            }
			        };
				
          $scope.incomeProfileData= [
	            {
	                "key" : "User Income" ,
	                "values" : $scope.incomeProfileUser,
	                "color" : "#ff0000"
	                
	            },
	            {
	                "key" : "Spouse Income" ,
	                "values" : $scope.incomeProfileSpouse,
	                "color" : "#337ab7"
	                
	            }
	             
	            
	        ]
				/*FusionCharts.ready(function () {

					var salesPrediction = new FusionCharts({
						type: 'dragline',
						renderAt: 'chart-container3',
						width: '1000',
						height: '350',
						dataFormat: 'json',
						dataSource: {
							"chart": {
								"caption": "Income",
								"showvalues": "0",
								"xAxisName": "Year",
								"yAxisName": "Income",
								"bgcolor": "FFFFFF",
								"showalternatehgridcolor": "0",
								"showplotborder": "1",
								"divlinecolor": "CCCCCC",
								"formatNumberScale":"0",
								"showcanvasborder": "0",
								"captionpadding": "20",
								"legendpadding": "10",
								"plotbordercolor": "2f69bf",
								"linethickness": "3",
								"formbtnbgcolor": "666666",
								"btntextcolor": "FFFFFF",
								"showrestorebtn": "0",
								"canvasborderalpha": "0",
								"legendshadow": "0",
								"legendborderalpha": "0",
								"showborder": "0",
								"submitformusingajax": "0"
							},
							"categories": [
							               {
							            	   "category": $scope.chartYear
							               }
							               ],
							               "dataset": [
							                           {
							                        	   "id": "IJ",
							                        	   "seriesname": "User Income",
							                        	   "color": "328de4",
							                        	   "data":$scope.chartUserIncome
							                           },
							                           {
							                        	   "id": "LJ",
							                        	   "seriesname": "Spouse Income",
							                        	   "color": "32e2e2",
							                        	   "data":  $scope.chartSouseIncome
							                           },
							                           {
							                        	   "id": "CR",
							                        	   "seriesname": "Combined Income",
							                        	   "showvalues": "0",
							                        	   "color": "#B0C10C",
							                        	   "data":   $scope.chartCombinedIncome
							                           }

							                           ],
							                           "styles": {
							                        	   "definition": [
							                        	                  {
							                        	                	  "name": "myCaptionFont",
							                        	                	  "type": "font",
							                        	                	  "font": "Arial",
							                        	                	  "size": "14",
							                        	                	  "bold": "1"
							                        	                  },
							                        	                  {
							                        	                	  "name": "mySubCaptionFont",
							                        	                	  "type": "font",
							                        	                	  "font": "Arial",
							                        	                	  "size": "10",
							                        	                	  "bold": "0"
							                        	                  }
							                        	                  ],
							                        	                  "application": [
							                        	                                  {
							                        	                                	  "toobject": "Caption",
							                        	                                	  "styles": "myCaptionFont"
							                        	                                  },
							                        	                                  {
							                        	                                	  "toobject": "SubCaption",
							                        	                                	  "styles": "mySubCaptionFont"
							                        	                                  }
							                        	                                  ]
							                           }
						},
						events: {
							'dataplotdragend': function(evt, arg){



								var dsIndx = arg && arg.datasetIndex,
								dtIndx = arg && arg.dataIndex,
								val = arg && parseInt(arg.endValue, 10);


							}             

						}
					}).render();
				});*/


			} 
			$scope.goaldetails = function() {
				$scope.chartIncome = [];
				$scope.ExpenseIncome = [];
				//alert($scope.formdata.planName);
				$scope.plannames=$scope.formdata.planName;
				$scope.goalNames = [];
				$scope.goalNames1 = [];
				$scope.goalIds = [];
				$scope.goalIds1 = [];
				$scope.expense = [];
				//------------------------------------------------------------------------------------
				$scope.TargetSavings=[];
				$scope.cardetails = [];
				$scope.tablevalues=[];
				$scope.tablevaluesforgoal=[];
				$scope.tablevaluesforretirementgoal=[];
				$scope.customized=[];
				$scope.costamized=[];
				$scope.Names=[];
				$scope.kidName=[];
				$scope.hardcustomized="";
				$scope.costemsize="";
				$scope.costemsize1="";
				$scope.kidsize="";
				$scope.collage_expense="";
				$scope.chartGoals=[];
				$scope.assets=[];
				$scope.formdata.actionHomeType = "goaldetails";
				$http(
						{
							method : 'POST',
							url : 'Report',
							data : $.param($scope.formdata),
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded'
							}
						})
						.then(
								function(result) {
									$scope.income=[];
									$scope.userincome=[];
									$scope.spouseincome=[];
									$scope.combinedincome=[];
									$scope.incomeyear=[];
									$scope.incomevalue=[];
									$scope.vacation_startYear="";
									$scope.ExpenseIncome=[];
									$scope.spouseIncomeSave=[];
									$scope.combinedIncome=[];
									$scope.tableIncome=[];
									$scope.chartIncome=[]; 
									//$scope.RetirementAge="";
									$scope.golNames=[];
									$scope.hardcustomized="";
									$scope.costemsize1=""; 
									$scope.costemsize="";
									$scope.collage_exp="";
									$scope.rentalExpense=result.data.rentalExpense;
									
									//$scope.RetirementAge=result.data.expenses.retirementAge;
									//alert("app"+result.data.expenses.retirementAge)
										//result.data.retirementAge;
									
						
									if(result.data.expenses!=undefined)
										{
										if(result.data.expenses.vacation_expense!=undefined)
											{
												$scope.vtype=result.data.expenses.vacation_expense.frequency;
											}
											
										}
									
									 // alert("frequencyc 1 "+JSON.stringify(result.data.expenses.vacation_expense));
									
									
									$scope.monthlyExpense=result.data.monthlyExpense;
									$scope.sumExpense=(($scope.rentalExpense*1)+($scope.monthlyExpense*1))*12;
									//----------------------------------------------------------------------------------------
									$scope.goalNames = result.data.Goals;
									//alert("$scope.goalsize   "+$scope.goalNames)
									if($scope.goalNames=="noGoals"){
										///alert("no goals   ")
										$("#goalsData").hide();
										
										
										
									}else{
										$("#goalsData").show();
									  
									}
									//alert("before  "+JSON.stringify($scope.goalNames));
									//$scope.goalNames = orderBy($scope.goalNames, '', false);
									
									for(var count=0;count<$scope.goalNames.length;count++)
										{
										var temp="";
										//alert("lenght"+$scope.goalNames.length)
										 for(var count1=1;count1<$scope.goalNames.length;count1++)
											 {
											// alert("axscvdgb    "+$scope.goalNames[count1])
											 if($scope.goalNames[count1-1] > $scope.goalNames[count1]){  
				                                 //swap elements  
				                                 temp = $scope.goalNames[count1-1];  
				                                 $scope.goalNames[count1-1] = $scope.goalNames[count1];  
				                                 $scope.goalNames[count1] = temp;
				                                // alert("axscvdgb    "+$scope.goalNames[count1]);
				                                 
											 }
											 }
										 
										// alert("after   1 "+JSON.stringify(temp));
										}

									//alert("after   "+JSON.stringify($scope.goalNames));
									$scope.genaricExpenseGoal=result.data.expenseafterGoalDetails;
									//alert(JSON.stringify($scope.genaricExpenseGoal));
									//alert($scope.genaricExpense.length);
									$scope.expenseAfterGoalDetails = [];
									for(var y=0; y<$scope.genaricExpenseGoal.length; y++)
									{
										//alert($scope.genaricExpenseGoal[y].totalExpense);

										$scope.expenseAfterGoalDetails.push({year:$scope.genaricExpenseGoal[y].year,expenseBeGoal:$scope.genaricExpenseGoal[y].totalExpense});
										$scope.ExpenseIncome.push({value :"0",year:$scope.genaricExpenseGoal[y].year,"allowDrag": "0"});
										//alert(JSON.stringify($scope.expenseAfterGoalDetails));
									}
									if($scope.goalNames=="noGoals")
									{
										$scope.income=result.data.incomedetails; 

										$scope.userincome=$scope.income.user_income;	

										$scope.cominedincome=$scope.income.combined_income;
										$scope.assets=result.data.incomedetails.assests;
										$scope.maritalstatus=result.data.marital_status;

										for(var i=0;i<$scope.expenseAfterGoalDetails.length;i++)
										{

											$scope.incomeyear.push({label :$scope.userincome[i].year.toString()});
											//alert(JSON.stringify($scope.incomeyear));
											$scope.incomevalue.push({value :$scope.userincome[i].value});

											//$scope.ExpenseIncome.push({value :"0",year:$scope.userincome[i].year,"allowDrag": "0"});
											//alert(JSON.stringify($scope.ExpenseIncome));
											if(result.data.marital_status=="Yes")
											{
												$scope.combinedIncome.push({value:$scope.cominedincome[i].value});
											//	$scope.chartIncome.push({value:$scope.cominedincome[i].value,"allowDrag": "0"});

											}
											else
											{

											//	$scope.chartIncome.push({value :$scope.userincome[i].value,"allowDrag": "0"});
											}
											//alert("1");
											
												$scope.ExpenseIncome[i].value=$scope.expenseAfterGoalDetails[i].expenseBeGoal;
												$scope.ExpenseIncome[i].year=$scope.expenseAfterGoalDetails[i].year;
										
											//alert("2")


										}
										//editChart();
										
										
										for(m=0;m<$scope.assets.length;m++)
										{
											$scope.sum=$scope.assets[m].nontaxable_investment_amount*1+$scope.assets[m].savings*1+$scope.assets[m].taxable_investment_amount*1;
											
											$scope.chartAssets.push({value:$scope.sum,"allowDrag" : "0"});
											$scope.chartGoals.push({value:"0","allowDrag":"0"});

										}
										
										editAssetChart();
										 
									} 
                                 
                                  
									for(var i=0;i<$scope.expenseAfterGoalDetails.length;i++)
									{


										//$scope.ExpenseIncome.push({value:(($scope.expenseAfterGoalDetails[i].expenseBeGoal)+($scope.sumExpense*1))});
										$scope.newExpense.push({value:$scope.expenseAfterGoalDetails[i].expenseBeGoal,year:$scope.expenseAfterGoalDetails[i].year});
										//alert("1stnew "+	$scope.newExpense[i].value);
									}
									//alert("Bala"+$scope.newExpense.length)
									$scope.goalsize=$scope.goalNames.length;

									//alert("yyy");
									//--------------------checking customized_expense not equal to null-------------------------

									/*		if(result.data.expenses.customized_expense!=null){

												$scope.hardcustomized=result.data.expenses.customized_expense;
												$scope.costemsize=$scope.hardcustomized.length;


												$scope.collage_exp=result.data.expenses.college_expense;
												$scope.collage_expense=$scope.collage_exp.length;

											}
												if(result.data.expenses.college_expense!=null){
													$scope.collage_exp=result.data.expenses.college_expense;
													$scope.collage_expense=$scope.collage_exp.length;
												}*/

									/*  for(var i=0;i<$scope.expenseAfterGoalDetails.length;i++)
													{


													$scope.ExpenseIncome.push({value:(($scope.expenseAfterGoalDetails[i].expenseBeGoal)+($scope.sumExpense*1))});
											alert("2st "+	$scope.ExpenseIncome[i].value);
													}*/
									//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
									//alert("yyy1");
									$scope.assets=result.data.incomedetails.assests;

									$scope.maritalstatus=result.data.marital_status;

									$scope.goalIds = result.data.GoalIds;

									$scope.expense = result.data.expenses;
									$scope.reports_properties_equity = result.data.equity;
									//console.log(result.data)
									//alert(JSON.stringify($scope.reports_properties_equity))
									//alert(JSON.stringify(result.data))
									//alert("expense "+JSON.stringify($scope.expense));
									$scope.income=result.data.incomedetails; 

									$scope.userincome=$scope.income.user_income;	

									$scope.spouseincome=$scope.income.spouse_income;

									$scope.cominedincome=$scope.income.combined_income;
									$scope.kidExpense=result.data.incomedetails.kid_expense;

									$scope.customizedExpense=result.data.incomedetails.customized_expense;
									$scope.collegeXpense=result.data.incomedetails.college_expense;


									$scope.goalsize=$scope.goalNames.length;


									//editChart();


									editAssetChart();
									var temp=0;

									if(result.data.expenses !=null && result.data.expenses.vacation_expense!=null){
										//alert("result.data.expenses.vacation_expense.startingYear    ..  "+JSON.stringify(result.data.expenses.vacation_expense.startingYear));
										$scope.vacation_startYear=result.data.expenses.vacation_expense.startingYear;

										//alert($scope.vacation_startYear);
									}



									$scope.lengths=$scope.userincome.length;
									//alert(JSON.stringify($scope.lengths));
									for(var i=0; i<$scope.lengths; i++) { 

										$scope.chartGoals.push({value:"0","allowDrag":"0"});
										$scope.incomeyear.push({label :$scope.userincome[i].year.toString()});
										//alert(JSON.stringify($scope.incomeyear));
										$scope.incomevalue.push({value :$scope.userincome[i].value});

									//	$scope.ExpenseIncome.push({value :"0",year:$scope.userincome[i].year,"allowDrag": "0"});
										//alert(JSON.stringify($scope.ExpenseIncome));
										if(result.data.marital_status=="Yes")
										{

											$scope.spouseIncomeSave.push({value :$scope.spouseincome[i].value});  
											$scope.combinedIncome.push({value:$scope.cominedincome[i].value});

											$scope.tableIncome.push({id:$scope.cominedincome[i].value, value :$scope.userincome[i].value,year :$scope.userincome[i].year.toString(),expense:$scope.totalYearlyExpense,spouseValue:$scope.spouseincome[i].value});																	
											$scope.chartIncome.push({value:$scope.cominedincome[i].value,"allowDrag": "0"});

										}
										else
										{

											$scope.chartIncome.push({value :$scope.userincome[i].value,"allowDrag": "0"});
											$scope.tableIncome.push({value :$scope.userincome[i].value,year :$scope.userincome[i].year.toString(),expense:$scope.totalYearlyExpense});					

										}


									}
									
									var yearCount=0;
									for(var i=0;i<$scope.chartIncome.length;i++)
									{

										if($scope.expense!=null && $scope.expense.car_expense!=null)
										{
										//alert(JSON.stringify($scope.expense));
											//$scope.newExpense[i].value=0;
											//alert($scope.expense.car_expense.startYear);
											//alert($scope.expense.car_expense.endYear);	
											for(var j=$scope.expense.car_expense.startYear;j<=$scope.expense.car_expense.endYear;j++)
											{	

												if($scope.ExpenseIncome[i].year==j)
												{

													$scope.ExpenseIncome[i].value=$scope.ExpenseIncome[i].value+($scope.expense.car_expense.exactAnual_morgage)*1;
													//alert(" ExpenseIncome before adding new exp "+$scope.ExpenseIncome[i].value);
													//alert("newExpense "+$scope.newExpense[i].value);
													//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
													//alert("final value "+$scope.ExpenseIncome[i].value);
												}		
											}
											if($scope.expense.car_expense.endYear==$scope.ExpenseIncome[i].year)
											{
												//$scope.chartGoals[i].value =((($scope.expense.car_expense.endYear)-($scope.expense.car_expense.startYear))*($scope.expense.car_expense.exactAnual_morgage));

											}
											//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
											//alert("final value last "+$scope.ExpenseIncome[i].value);

										}


										if($scope.expense!=null && $scope.expense.marriage_expense!=null)
										{
											for(var j=$scope.expense.marriage_expense.startYear;j<$scope.expense.marriage_expense.endYear;j++)
											{
												if($scope.ExpenseIncome[i].year==j)
												{
													$scope.ExpenseIncome[i].value=$scope.ExpenseIncome[i].value*1;
												}
											}

											if($scope.expense.marriage_expense.endYear==$scope.ExpenseIncome[i].year)
											{
												$scope.ExpenseIncome[i].value=$scope.ExpenseIncome[i].value + $scope.expense.marriage_expense.annualexpense
												//alert("123");
												//$scope.chartGoals[i].value =$scope.chartGoals[i].value + $scope.expense.marriage_expense.annualexpense*(($scope.expense.marriage_expense.endYear-$scope.expense.marriage_expense.startYear)*1+1);

											}
											//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
										}



										if($scope.expense!=null && $scope.expense.retirement_expense!=null)
										{
											for(var j=$scope.expense.retirement_expense.startYear;j<$scope.expense.retirement_expense.endYear;j++)
											{
												if($scope.ExpenseIncome[i].year==j)
												{
													$scope.ExpenseIncome[i].value=$scope.ExpenseIncome[i].value*1;
												}
											}
											//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
										}


										//alert("before"+$scope.expense.vacation_expense.frequency);
										if($scope.expense!=null && $scope.expense.vacation_expense!=null)
										{
											//alert("after"+$scope.expense.vacation_expense.frequency);
											//alert($scope.expense.vacation_expense.endYear);
											//alert($scope.vacation_startYear);
											if($scope.expense.vacation_expense.frequency=="One Time")
											{
                                                  //alert(JSON.stringify($scope.ExpenseIncome));
												for(var j=0;j<$scope.ExpenseIncome.length;j++)
												{ 
													if(parseInt( $scope.ExpenseIncome[j].year)==parseInt($scope.expense.vacation_expense.endYear))
													{

														$scope.ExpenseIncome[j].value=$scope.newExpense[i].value*1+$scope.expense.vacation_expense.expenses*1;
														//alert("One Time " + $scope.ExpenseIncome[i].value);
														//alert(" ExpenseIncome before adding new exp "+$scope.ExpenseIncome[i].value);
														//	alert("newExpense "+$scope.newExpense[i].value);
														//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
														//alert("final value "+$scope.ExpenseIncome[i].value);

													}

												}
												//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);

												//alert("final value last "+$scope.ExpenseIncome[i].value);
												if($scope.expense.vacation_expense.endYear== $scope.ExpenseIncome[i].year)
												{
													//$scope.chartGoals[i].value=$scope.chartGoals[i].value+$scope.expense.vacation_expense.expenses*1;

												}
											}
											else if($scope.expense.vacation_expense.frequency=="Every Year")
											{
												//alert("3");
												for(var j=0;j<$scope.ExpenseIncome.length;j++)
												{

													if($scope.ExpenseIncome[j].year>=$scope.expense.vacation_expense.endYear)
													{	
														//alert("every yaer");
														$scope.ExpenseIncome[j].value=$scope.newExpense[i].value*1+$scope.expense.vacation_expense.expenses*1;
														//alert(" ExpenseIncome before adding new exp "+$scope.ExpenseIncome[i].value);
														//alert("newExpense "+$scope.newExpense[i].value);
														//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
														//alert("final value "+$scope.ExpenseIncome[i].value);
													}
												}
												//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
												//alert("final value last "+$scope.ExpenseIncome[i].value);
												//alert("hellllll");
												//alert($scope.ExpenseIncome[i].year>=$scope.vacation_startYear);
												//alert($scope.ExpenseIncome[i].year<=$scope.expense.vacation_expense.endYear);
												//alert("stsrt "+$scope.vacation_startYear);
												//alert("$scope.ExpenseIncome[i].year "+$scope.ExpenseIncome[i].year);
												//alert("$scope.expense.vacation_expense.endYear "+$scope.expense.vacation_expense.endYear);
												if( /*$scope.ExpenseIncome[i].year>=$scope.vacation_startYear && */$scope.ExpenseIncome[i].year>=$scope.expense.vacation_expense.endYear)
												{
													//alert("entered");
													//alert($scope.vacation_startYear);



													//$scope.chartGoals[i].value=$scope.chartGoals[i].value+$scope.expense.vacation_expense.expenses*1;
													//alert($scope.chartGoals[i].value);

												}
											}
											else if($scope.expense.vacation_expense.frequency=="Every Two Years")
											{
												//alert("every 2 yaer");	
												
												for(var j=0;j<$scope.ExpenseIncome.length;j=j+2)
												{

													if($scope.ExpenseIncome[j].year>=$scope.expense.vacation_expense.endYear)
													{

														$scope.ExpenseIncome[j].value=($scope.newExpense[i].value*1)+$scope.expense.vacation_expense.expenses*1;
														//alert(" ExpenseIncome before adding new exp "+$scope.ExpenseIncome[i].value);
														//alert("newExpense "+$scope.newExpense[i].value);
														//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
														//alert("final value "+$scope.ExpenseIncome[i].value);

													}
												}



												//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
												//alert("final value last "+$scope.ExpenseIncome[i].value);
												if(($scope.expense.vacation_expense.startingYear+yearCount)== $scope.ExpenseIncome[i].year)
												{

													yearCount=yearCount+2;
													/*$scope.chartGoals[i].value=$scope.chartGoals[i].value+(((($scope.expense.vacation_expense.endYear)-
																	($scope.expense.vacation_expense.startingYear))
													 *($scope.expense.vacation_expense.expenses))/2)*1;
													 */

													//$scope.chartGoals[i].value=$scope.chartGoals[i].value+($scope.expense.vacation_expense.expenses)*1
												}
											}
										}
										if($scope.expense!=null && $scope.expense.housing_expense!=null)
										{
											for(var j=$scope.expense.housing_expense.startYear;j<$scope.expense.housing_expense.endYear;j++)
											{
												if($scope.ExpenseIncome[i].year==j)
												{
													$scope.ExpenseIncome[i].value=$scope.ExpenseIncome[i].value*1;
												}
											}
											//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);

											//alert("end"+$scope.expense.housing_expense.endYear);
											//alert("start"+$scope.ExpenseIncome[i].year);
											//alert("morgagae"+$scope.expense.housing_expense.mortgage_expense);
											if($scope.ExpenseIncome[i].year>=$scope.expense.housing_expense.startYear &&  $scope.ExpenseIncome[i].year<=$scope.expense.housing_expense.endYear)
											{

												//$scope.chartGoals[i].value=$scope.chartGoals[i].value+$scope.expense.housing_expense.mortgage_expense;
												//alert("expense.........."+$scope.chartGoals[i].value);

											}
										}

										if($scope.kidExpense!=null)
										{
											for(var k=0;k<$scope.kidExpense.length;k++)
											{

												for(var j=$scope.kidExpense[k].startYear;j<$scope.kidExpense[k].endYear;j++)
												{

													if($scope.ExpenseIncome[i].year==j)
													{

														$scope.ExpenseIncome[i].value=$scope.ExpenseIncome[i].value*1;
														//alert(" kidExpenseIncome before adding new exp "+$scope.ExpenseIncome[i].value);
														//alert(" kidnewExpense "+$scope.newExpense[i].value);
														//$scope.ExpenseIncome[i].value=$scope.ExpenseIncome[i].value+($scope.newExpense[i].value*1);
														//alert("kidfinal value "+$scope.ExpenseIncome[i].value);

													}

												}
												//alert("expense start"+$scope.kidExpense[k].startYear);
												//alert("expense ye"+$scope.ExpenseIncome[i].year);
												if($scope.ExpenseIncome[i].year>=$scope.kidExpense[k].startYear && $scope.ExpenseIncome[i].year<=$scope.kidExpense[k].endYear )
												{
													//alert("years "+$scope.ExpenseIncome[i].year);
													//	alert("totalexpense"+$scope.kidExpense[k].annualExpense);
													//alert("totalexpenseGoals"+$scope.chartGoals[i].value);
													//$scope.chartGoals[i].value=$scope.chartGoals[i].value+ $scope.kidExpense[k].annualExpense;
													//(($scope.expense.housing_expense.endYear)-($scope.expense.housing_expense.startYear))*($scope.expense.housing_expense.mortgage_expense);

												}
											}

											//	$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
											//alert(" kidfinal value last "+$scope.ExpenseIncome[i].value);
										}

										if($scope.customizedExpense!=null)
										{
											//	$scope.newExpense[i].value=0;
											for(var k=0;k<$scope.customizedExpense.length;k++)
											{

												for(var j=$scope.customizedExpense[k].startYear;j<=$scope.customizedExpense[k].endYear;j++)
												{		

													if($scope.ExpenseIncome[i].year==j)
													{
														$scope.ExpenseIncome[i].value=$scope.ExpenseIncome[i].value*1+$scope.customizedExpense[k].goalCost*1;
														//alert(" customized ExpenseIncome before adding new exp "+$scope.ExpenseIncome[i].value);
														//alert(" customizednewExpense "+$scope.newExpense[i].value);
														//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
														//alert(" customizedfinal value "+$scope.ExpenseIncome[i].value);

													}

												}

												if($scope.ExpenseIncome[i].year>=$scope.customizedExpense[k].startYear && $scope.ExpenseIncome[i].year<=$scope.customizedExpense[k].endYear)
												{
													//$scope.chartGoals[i].value=$scope.chartGoals[i].value+($scope.customizedExpense[k].goalCost);
													//((($scope.customizedExpense[k].startYear)-($scope.customizedExpense[k].startYear))*
													//($scope.customizedExpense[k].goalCost))
													//(($scope.expense.housing_expense.endYear)-($scope.expense.housing_expense.startYear))*($scope.expense.housing_expense.mortgage_expense);

												}
											}
											//	$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
											//alert(" customized final value last "+$scope.ExpenseIncome[i].value);

										} 

										if($scope.collegeXpense!=null)
										{

											//$scope.newExpense[i].value=0;
											for(var k=0;k<$scope.collegeXpense.length;k++)
											{

												for(var j=$scope.collegeXpense[k].endYear;j<=$scope.collegeXpense[k].endYear+4;j++)
												{		

													if($scope.ExpenseIncome[i].year==j)
													{

														$scope.ExpenseIncome[i].value=$scope.ExpenseIncome[i].value*1+$scope.collegeXpense[k].collegeExpense*1;
														//alert(" collegeExpenseIncome before adding new exp "+$scope.ExpenseIncome[i].value);
														//alert("collegenewExpense "+$scope.newExpense[i].value);
														//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
														//alert("collegefinal value "+$scope.ExpenseIncome[i].value);

													}

												}
												if($scope.ExpenseIncome[i].year>=$scope.collegeXpense[k].endYear && $scope.ExpenseIncome[i].year<$scope.collegeXpense[k].endYear+4)
												{
													//$scope.chartGoals[i].value=$scope.chartGoals[i].value+$scope.collegeXpense[k].collegeExpense;
													//(($scope.expense.housing_expense.endYear)-($scope.expense.housing_expense.startYear))*($scope.expense.housing_expense.mortgage_expense);

												}
											}
											//$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);
											//alert("collegefinal value last "+$scope.ExpenseIncome[i].value);

										}

                                         //alert(JSON.stringify($scope.assets);
                                         $scope.assetsTotalGrowth = new Array();
										for(var m=0;m<$scope.assets.length;m++)
										{
											$scope.sum=$scope.assets[m].nontaxable_investment_amount*1+$scope.assets[m].savings*1+$scope.assets[m].taxable_investment_amount*1;
											$scope.chartAssets.push({value:$scope.sum,"allowDrag" : "0"});
											$scope.assetsTotalGrowth.push(new Array($scope.parseDate(""+$scope.assets[m].year),$scope.sum));
										}
										//alert("app vqwqqq    "+JSON.stringify($scope.ExpenseIncome[i]));
										//alert($scope.ExpenseIncome[i].value);
										if($scope.ExpenseIncome[i]!=undefined && $scope.newExpense[i]!=undefined)
											{
										//	$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+($scope.newExpense[i].value*1);

											}
										/*else
											{
											if($scope.ExpenseIncome[i]==undefined)
												{
												$scope.ExpenseIncome[i].value=0+($scope.newExpense[i].value*1);

												}
											else if ($scope.newExpense[i]==undefined)
												{
												$scope.ExpenseIncome[i].value=($scope.ExpenseIncome[i].value)*1+0;

												}
											else
												{
												$scope.ExpenseIncome[i].value=0;
												}

											}*/

									}
									//------------------------This function is used for details calculations----------------------------
/*									for(var i=0;i<$scope.goalsize;i++){
										//alert("kkkk");
										if(result.data.expenses.vacation_expense!=null){
											//alert("result.data.expenses.vacation_expense.startingYear    ..  "+JSON.stringify(result.data.expenses.vacation_expense.startingYear));
											$scope.vacation_startYear=result.data.expenses.vacation_expense.startingYear;

											//alert($scope.vacation_startYear);
										}
										if($scope.goalNames[i]=="Emergency Fund"){

											$scope.emergency=1;
											$scope.tablevalues.push({golas :"Emergency Fund", Target_Saving :(result.data.expenses.emergency_expense.emi),Year_Accomplished:result.data.expenses.emergency_expense.startYear}); 


										}

										else if($scope.goalNames[i]=="Marriage"){
											$scope.emergency=2;
											//$scope.TargetSavings.push({value : (((result.data.expenses.marriage_expense.endYear)-(result.data.expenses.marriage_expense.startYear))*(result.data.expenses.marriage_expense.annualexpense)) });
											//alert("marrage...");
											$scope.tablevalues.push({golas :"Marriage", Target_Saving :(result.data.expenses.marriage_expense.annualexpense) ,Year_Accomplished:result.data.expenses.marriage_expense.startYear}); 
											//alert(angular.toJson($scope.TargetSavings));
										}else if($scope.goalNames[i]=="Car"){
											$scope.emergency=3;

											$scope.tablevalues.push({golas :"Car", Target_Saving :(((result.data.expenses.car_expense.endYear)-(result.data.expenses.car_expense.startYear))*(result.data.expenses.car_expense.exactAnual_morgage)) ,Year_Accomplished:result.data.expenses.car_expense.startYear}); 


										}

										else if($scope.goalNames[i]=="Home"){
											$scope.emergency=4;
											//$scope.TargetSavings.push({value : (((result.data.expenses.housing_expense.endYear)-(result.data.expenses.housing_expense.startYear))*(result.data.expenses.housing_expense.mortgage_expense)) });	
											//alert("bying a house");
											$scope.tablevalues.push({golas :"Buying a House", Target_Saving :(((result.data.expenses.housing_expense.endYear)-(result.data.expenses.housing_expense.startYear))*(result.data.expenses.housing_expense.mortgage_expense)) ,Year_Accomplished:result.data.expenses.housing_expense.startYear}); 
											//alert(angular.toJson($scope.TargetSavings));
										}


										else if($scope.goalNames[i]=="Retirement"){

											$scope.emergency=5;
											//$scope.cargoal=1;
											// alert($scope.retirement);
											//$scope.TargetSavings.push({value : (((result.data.expenses.retirement_expense.endYear)-(result.data.expenses.retirement_expense.startYear))*(result.data.expenses.retirement_expense.expenses)) });	
										
											$scope.tablevalues.push({golas :"Retirement", Target_Saving :(result.data.expenses.retirement_expense.expenses) ,Year_Accomplished:result.data.expenses.retirement_expense.startYear}); 
											//alert(angular.toJson($scope.TargetSavings));
										}

										else if($scope.goalNames[i]=="Vacation"){
											$scope.emergency=6;
											
											if(result.data.expenses.vacation_expense.frequency="One Time")
											{
												$scope.tablevalues.push({golas :"Vacation", Target_Saving :(result.data.expenses.vacation_expense.expenses) ,Year_Accomplished:result.data.expenses.vacation_expense.startingYear});
											}
											else if(result.data.expenses.vacation_expense.frequency="Every Year")
											{
												$scope.tablevalues.push({golas :"Vacation", Target_Saving :(((result.data.expenses.vacation_expense.endYear)-(result.data.expenses.vacation_expense.startingYear))*(result.data.expenses.vacation_expense.expenses)) ,Year_Accomplished:result.data.expenses.vacation_expense.startingYear});
											}
											else{
												$scope.tablevalues.push({golas :"Vacation", Target_Saving :((((result.data.expenses.vacation_expense.endYear)-(result.data.expenses.vacation_expense.startingYear))*(result.data.expenses.vacation_expense.expenses))/2) ,Year_Accomplished:result.data.expenses.vacation_expense.startingYear})
											}

											//alert("vacation");

										}
										else if($scope.goalNames[i]=="Raising a kid")
										{
											$scope.emergency=7;

											//alert("vacation called");
											//alert(angular.toJson(result.data.expenses.kid_expense[temp].goalname));
											$scope.tablevalues.push({golas :result.data.expenses.kid_expense[temp].goalname, Target_Saving :(((result.data.expenses.kid_expense[temp].endYear)-(result.data.expenses.kid_expense[temp].startYear))*(result.data.expenses.kid_expense[temp].annualExpense)) ,Year_Accomplished:result.data.expenses.kid_expense[temp].startYear}); 

											temp++;//alert("vacation");
										}
									}*/

							/*		for(var j=0;j<$scope.costemsize;j++){


										for(var i=0;i<$scope.goalsize;i++){

											$scope.golNames.push({value :$scope.goalNames[i]});
											//alert($scope.goalNames[i]+"  i");

											if($scope.goalNames[i]==result.data.expenses.customized_expense[j].goalname+"- Customized Goal"){
												$scope.emergency=8;
												$scope.tablevalues.push({golas :result.data.expenses.customized_expense[j].goalname+"- Customized Goal", Target_Saving :(((result.data.expenses.customized_expense[j].endYear)-(result.data.expenses.customized_expense[j].startYear))*(result.data.expenses.customized_expense[j].goalCost)) ,Year_Accomplished:result.data.expenses.customized_expense[j].startYear}); 
												//alert($scope.tablevalues);


											}


										}
									}*/
									for(var j=0;j<$scope.collage_expense;j++){
										for(var i=0;i<$scope.goalsize;i++){
											$scope.golNames.push({value :$scope.goalNames[i]});
											if($scope.goalNames[i]=="College Education"){
												$scope.emergency=9;
												$scope.tablevalues.push({golas :result.data.expenses.college_expense[j].goalname, Target_Saving :(((result.data.expenses.college_expense[j].endYear)-(result.data.expenses.college_expense[j].startYear))*(result.data.expenses.college_expense[j].collegeExpense)) ,Year_Accomplished:result.data.expenses.college_expense[j].startYear}); 
												break;
											}
										}
									}
									//-----------------------------------------------------------------------------	
									/////////////////code for onload goals details
			
			

									var firstPie={};
							       // var secondPie={};
							        var count=0;
							        console.log($scope.chartIncome)
							        console.log($scope.ExpenseIncome)
							        $scope.yearreportsBR = new Array();
							        //$scope.yearreportsAR = new Array();
							        var retirementAge=0;
							        $scope.retirementYear=$scope.userDetails.birthYear *1 + $scope.defaultretirementAge *1;
							        if(parseInt($scope.retirementYear) < parseInt($scope.spouseretirementYear) && $scope.spouseretirementYear !== undefined ){
							        	retirementAge = parseInt($scope.spouseretirementYear);
							        }else{
							        	retirementAge = parseInt($scope.retirementYear);
							        }
							       // alert(JSON.stringify($scope.yearreportsBR));
							        for(var i=0;i<$scope.assets.length;i++){
							        	firstPie=$scope.assets[45];
							        	//alert(JSON.stringify(firstPie));
							        	//if(retirementAge >= parseInt($scope.assets[i]['year'])){
							        		$scope.yearreportsBR.push(parseInt($scope.assets[i]['year']));
							        	//}else{
							        		if(count==0){
							        			//secondPie=$scope.assets[i];
							        			//$scope.reportsstartYearAR=parseInt($scope.assets[i].year);
							        		}
							        		
							        		//$scope.yearreportsAR.push(parseInt($scope.assets[i]['year']));
							        	    count++;
							        	}
							       // }
							     
	
							       $scope.optionsPiereports = {
								            chart: {
								                type: 'pieChart',
								                height: 500,
								                x: function(d){return d.key;},
								                y: function(d){return d.y;},
								                showLabels: true,
								                duration: 500,
								                labelThreshold: 0.01,
								                labelSunbeamLayout: true,
								                legend: {
								                    margin: {
								                        top: 5,
								                        right: 35,
								                        bottom: 5,
								                        left: 0
								                    }
								                }
								            }
								        };
							       //$scope.assets[i].savings
							        $scope.dataPieChartreportsBR = [
											   {
								                    key: "Savings",
								                    y: firstPie.savings,
								                    "color" : "rgb( 205, 21, 51 )"
								                },
								                {
								                    key: "Non-Taxable",
								                    y: firstPie.nontaxable_investment_amount,
								                    "color" : "rgb( 255, 87, 34 )"
								                },
								                {
								                    key: "Taxable",
								                    y: firstPie.taxable_investment_amount,
								                    "color" : "rgb( 63, 81, 181 )"
								                }
											        ];
							      
									//$scope.goalsize;
									var temp=0;
									var count=0;
									var count1=0;
									temp1=0;
									
									
			
									
									for(var i=0;i<$scope.goalsize;i++){
										$scope.flag=false
									/*	$scope.ageRetirementDisplay=true;
										$scope.ageRetirementDisplayNot = false;
*/

										//alert("colleege data cost  "+JSON.stringify(result.data.expenses.college_expense));

										
										//alert("kkkk");
										if(result.data.expenses!=null && result.data.expenses.vacation_expense!=null){
											
											//alert("aparna  1"+$scope.goalNames[i]);
											//alert("result.data.expenses.vacation_expense.startingYear    ..  "+JSON.stringify(result.data.expenses.vacation_expense.startingYear));
											$scope.vacation_startYear=result.data.expenses.vacation_expense.startingYear;

											//alert($scope.vacation_startYear);
										}
										if($scope.goalNames[i]=="Emergency Fund"){
											
										
											
											
											//$scope.ageRetirementDisplayNot=false;
											
											$scope.emergency=1;
											$scope.tablevaluesforgoal.push({golas :"Emergency Fund", collName:"Goal's Expense ", Target_Saving :(result.data.expenses.emergency_expense.emi),Year_Accomplished:result.data.expenses.emergency_expense.startYear,Year_Accomplished_end: result.data.expenses.emergency_expense.endYear}); 


										}

										else if($scope.goalNames[i]=="Marriage"){
											
											$scope.emergency=2;
											//$scope.TargetSavings.push({value : (((result.data.expenses.marriage_expense.endYear)-(result.data.expenses.marriage_expense.startYear))*(result.data.expenses.marriage_expense.annualexpense)) });
											//alert("marrage...");
											$scope.tablevaluesforgoal.push({golas :"Marriage", collName:"Goal's Expense ", Target_Saving :(result.data.expenses.marriage_expense.annualexpense) ,Year_Accomplished:result.data.expenses.marriage_expense.startYear,Year_Accomplished_end:result.data.expenses.marriage_expense.endYear}); 
											//alert(angular.toJson($scope.TargetSavings));
										}else if($scope.goalNames[i]=="Car"){
											
											$scope.emergency=3;
											//alert("999    "+result.data.expenses.car_expense.exactAnual_morgage);
											
											$scope.tablevaluesforgoal.push({golas :"Car", collName:"Goal's Expense ", Target_Saving :(((result.data.expenses.car_expense.endYear)-(result.data.expenses.car_expense.startYear)+1)*(result.data.expenses.car_expense.exactAnual_morgage)) ,Year_Accomplished:result.data.expenses.car_expense.startYear,Year_Accomplished_end:result.data.expenses.car_expense.endYear}); 


										}

										else if($scope.goalNames[i]=="Home"){
											//$scope.ageRetirementDisplay = true;
											
											//$scope.ageRetirementDisplayNot=false;
											
										//	alert(JSON.stringify(result.data.expenses.housing_expense[count].frequency));
											//alert("2 "+result.data.expenses.housing_expense[count].property_value);
											//alert("1    "+result.data.expenses.housing_expense[count].home_insurance_expense)
											$scope.emergency=4;
											//$scope.TargetSavings.push({value : (((result.data.expenses.housing_expense.endYear)-(result.data.expenses.housing_expense.startYear))*(result.data.expenses.housing_expense.mortgage_expense)) });	
											//alert("bying a house");
											$scope.tablevaluesforgoal.push({golas :("Home -"+result.data.expenses.housing_expense[count].frequency), collName:"Goal's Expense ", Target_Saving :result.data.expenses.housing_expense[count].property_value ,Year_Accomplished:result.data.expenses.housing_expense[count].startYear,Year_Accomplished_end:result.data.expenses.housing_expense[count].endYear}); 
											count++;
											//alert("1"+tablevaluesforgoal);
										}


										else if($scope.goalNames[i]=="Retirement"){
											
											
											/*$scope.flag=true;
											
											$scope.ageRetirementDisplayNot = false;*/
											//alert("aparna r "+$scope.goalNames[i]);
											//$scope.ageRetirementDisplay = false;
											
										
											//$scope.ageRetirementDisplayNot=false;
											

											$scope.emergency=5;
											//$scope.cargoal=1;
											// alert("  1ageRetirementDisplayNot  "+result.data.retirementAge);
											//$scope.TargetSavings.push({value : (((result.data.expenses.retirement_expense.endYear)-(result.data.expenses.retirement_expense.startYear))*(result.data.expenses.retirement_expense.expenses)) });	
											//alert("retirment");
											$scope.tablevaluesforgoal.push({golas :"Retirement", collName:"Retirement Age ",Target_Saving:(result.data.retirementAge) ,Year_Accomplished:result.data.expenses.retirement_expense.startYear,Year_Accomplished_end:result.data.expenses.retirement_expense.endYear}); 
											//alert(angular.toJson($scope.TargetSavings));
											//$("#noAge").show();
											
											//alert("111 "+JSON.stringify(result.data.expenses.customized_expense));
										}

										else if($scope.goalNames[i]=="Vacation"){
											//$scope.ageRetirementDisplayNot=false;
										
											//$("#exportable2").hide();
											$scope.emergency=6;
										
                                                  
											if($scope.vtype=="One Time")
											{
												
												$scope.tablevaluesforgoal.push({golas :"Vacation", 
													collName:"Goal's Expense ",Target_Saving :(result.data.expenses.vacation_expense.expenses) ,
													Year_Accomplished:result.data.expenses.vacation_expense.startingYear,
													Year_Accomplished_end:result.data.expenses.vacation_expense.startingYear+1});
											}
											else if($scope.vtype=="Every Year")
											{
											
												$scope.tablevaluesforgoal.push({golas :"Vacation", Target_Saving :(((result.data.expenses.vacation_expense.endYear)-(result.data.expenses.vacation_expense.startingYear))*(result.data.expenses.vacation_expense.expenses)) ,Year_Accomplished:result.data.expenses.vacation_expense.startingYear,Year_Accomplished_end:result.data.expenses.vacation_expense.endYear});
											}
											else{
												
												//alert("all data"+((((result.data.expenses.vacation_expense.endYear)-(result.data.expenses.vacation_expense.startingYear))*(result.data.expenses.vacation_expense.expenses))/2) );
												$scope.tablevaluesforgoal.push({golas :"Vacation", Target_Saving :((((result.data.expenses.vacation_expense.endYear)-(result.data.expenses.vacation_expense.startingYear))*(result.data.expenses.vacation_expense.expenses))/2) ,Year_Accomplished:result.data.expenses.vacation_expense.startingYear,Year_Accomplished_end:result.data.expenses.vacation_expense.endYear})
											}

											//alert("vacation");

										}
										else  if($scope.goalNames[i]=="Raising a kid")
										{
											 $scope.kidEndYear=result.data.expenses.kid_expense[temp].startYear+18;
											 $scope.expenseyear=$scope.kidEndYear-result.data.expenses.kid_expense[temp].startYear+1;
										//	$("#exportable2").hide();
											$scope.emergency=7;
											//alert("mykid     "+ $scope.kidEndYear);
											//alert("kid        "+($scope.kidEndYear-result.data.expenses.kid_expense[temp].startYear)+1);
											// alert("aiii111   "+$scope.expenseyear)
											//alert("vacation called");
											//alert(angular.toJson(result.data.expenses.kid_expense[temp].goalname));
											$scope.tablevaluesforgoal.push({golas :result.data.expenses.kid_expense[temp].goalname, 
												collName:"Goal's Expense ",Target_Saving :(($scope.expenseyear)*(result.data.expenses.kid_expense[temp].annualExpense)) ,
														Year_Accomplished:result.data.expenses.kid_expense[temp].startYear,
														Year_Accomplished_end:result.data.expenses.kid_expense[temp].startYear+18}); 
											// 
											temp++;//alert("vacation");
										}
										else if($scope.goalNames[i]=="College Education"){
										
											//$("#exportable2").hide();
											//alert("colleege data cost  "+JSON.stringify(result.data.expenses.college_expense[temp1].collegeGoalAmountData));

											$scope.totalAmount=0;
											for(var c=0;c<result.data.expenses.college_expense[temp1].collegeGoalAmountData.length;c++)
												{
												//alert("colleege data cost  "+result.data.expenses.college_expense[temp1].collegeGoalAmountData[c].cost);

												$scope.totalAmount=$scope.totalAmount+result.data.expenses.college_expense[temp1].collegeGoalAmountData[c].cost;
												}
											//alert("colleege data  "+$scope.totalAmount);
											//alert("colleege data cost  "+result.data.expenses.college_expense[temp].collegeGoalAmountData[0].cost);

											$scope.emergency=10;
											
											$scope.tablevaluesforgoal.push({golas :result.data.expenses.college_expense[temp1].goalname, 
												collName:"Goal's Expense ", Target_Saving :($scope.totalAmount) ,Year_Accomplished:result.data.expenses.college_expense[temp1].startYear,Year_Accomplished_end:result.data.expenses.college_expense[temp1].endYear}); 

											temp1++;
											

										}else if($scope.goalNames[i]=="Customized Goal"){
											//$("#exportable2").hide();
										//alert("a    "+result.data.expenses.customized_expense[count1].goalname)
											
											$scope.emergency=11;
			
											$scope.tablevaluesforgoal.push({golas :"Customized Goal -"+result.data.expenses.customized_expense[count1].goalname,  collName:"Goal's Expense ",Target_Saving :(((result.data.expenses.customized_expense[j].endYear)-(result.data.expenses.customized_expense[j].startYear))*(result.data.expenses.customized_expense[count1].goalCost)) ,Year_Accomplished:result.data.expenses.customized_expense[count1].startYear,Year_Accomplished_end:result.data.expenses.customized_expense[count1].endYear}); 
											count1++;
											
										}
										

									}
									
									
							           $scope.lineOptions = {
									            chart: {
									            	showControls: false,
									                type: 'lineChart',
									                height: 450,
									                width:900,
									                margin : {
									                    top: 20,
									                    right: 20,
									                    bottom: 30,
									                    left: 40
									                },
									                x: function(d){return d[0];},
									                y: function(d){return d[1];},
									                useVoronoi: false,
									                clipEdge: true,
									                duration: 100,
									                useInteractiveGuideline: true,
									                xAxis: {
									                    showMaxMin: false,
									                    tickFormat: function(d) {
									                        return d3.time.format('%Y')(new Date(d))
									                    }
									                },
									                yAxis: {
									                    tickFormat: function(d){
									                        return d3.format(',.2f')(d);
									                    }
									                },
									                
									                zoom: {
									                    enabled: true,
									                    scaleExtent: [1, 10],
									                    useFixedDomain: false,
									                    useNiceScale: false,
									                    horizontalOff: false,
									                    verticalOff: true,
									                    unzoomEventType: 'dblclick.zoom'
									                }
									                
									            }
										
									        };
										
										$scope.Income = new Array();
										$scope.Expense = new Array();
										
										var parseDate = d3.time.format("%Y").parse;
										//console.log($scope.chartIncome)
								        //console.log($scope.ExpenseIncome)
										for(var i=0;i<$scope.chartIncome.length;i++){
											var income=0;
											var year = parseDate(""+$scope.ExpenseIncome[i].year);
											if($scope.chartIncome[i].value !== null){
												income=parseInt($scope.chartIncome[i].value); 
											}
											$scope.Income.push(new Array(year,income));
										}
										
											for(var i=0;i<$scope.ExpenseIncome.length;i++){
												var expense=0;
												var year = parseDate(""+$scope.ExpenseIncome[i].year);
											if($scope.ExpenseIncome[i].value !== null){
												expense=parseInt($scope.ExpenseIncome[i].value); 
											}
											$scope.Expense.push(new Array(year,expense));
										}
										 $scope.datalineresult = [
										                {
										                    "key" : "Income" ,
										                    "values" :$scope.Income,
										                    "color" : "#337ab7"									                },

										                {
										                    "key" : "Expense" ,
										                    "values" : $scope.Expense,
										                    "color" : "#ff0000"	
										                }
										            ]
									
									// alert("sddfsd     "+JSON.stringify($scope.tablevaluesforgoal));
		$scope.optionsAreaAssetsandGoals = {
	            chart: {
	            	showControls: false,
	                type: 'stackedAreaChart',
	                height: 450,
	                margin : {
	                    top: 20,
	                    right: 20,
	                    bottom: 30,
	                    left: 40
	                },
	                x: function(d){return d[0];},
	                y: function(d){return d[1];},
	                useVoronoi: false,
	                clipEdge: true,
	                duration: 100,
	                useInteractiveGuideline: true,
	                xAxis: {
	                    showMaxMin: false,
	                    tickFormat: function(d) {
	                        return d3.time.format('%Y')(new Date(d))
	                    }
	                },
	                yAxis: {
	                    tickFormat: function(d){
	                        return d3.format(',.2f')(d);
	                    }
	                },
	                zoom: {
	                    enabled: true,
	                    scaleExtent: [1, 10],
	                    useFixedDomain: false,
	                    useNiceScale: false,
	                    horizontalOff: false,
	                    verticalOff: true,
	                    unzoomEventType: 'dblclick.zoom'
	                }
	            }
	        };
		 //alert(JSON.stringify($scope.reports_properties_equity)) 
		 
		//$scope.goaldetails();
		$scope.housegoalArray = new Array();
		$scope.endyear=0;
		         $scope.reportsproperties = new Array();
				 $scope.GoalsTotalAssets = new Array();
				 var dob = $scope.userDetails.birthYear;
				 var parseDate = d3.time.format("%Y").parse;
				 //var endyear = parseInt(dob)+100;
				 var d= new Date();
				 var currentYear = parseInt(d.getFullYear());
				//alert(JSON.stringify($scope.reports_properties_equity)) 
				 //console.log($scope.assetsTotalGrowth)
				 for(var m=0;m<$scope.reports_properties_equity.length;m++){
					  if($scope.reports_properties_equity == ""){
				         $scope.reportsproperties.push(new Array(parseDate(""+$scope.reports_properties_equity[m]['year']),0));	
				     }
					 else{
						 $scope.reportsproperties.push(new Array(parseDate(""+$scope.reports_properties_equity[m]['year']),$scope.reports_properties_equity[m].value));
					 }
				}
				 for(var l=currentYear;l<($scope.assetsTotalGrowth.length+currentYear);l++){
			
				    $scope.GoalsTotalAssets.push(new Array(l,0));
				    
				 }
				
				// alert(JSON.stringify(l));
				//alert(JSON.stringify($scope.reportsproperties))
				 $scope.totalYear=0;
				 for(var i=0;i<$scope.tablevaluesforgoal.length;i++){
				 
					 for(var j=$scope.tablevaluesforgoal[i].Year_Accomplished;j <=$scope.tablevaluesforgoal[i].Year_Accomplished_end;j++){
					 	var count = 0;
					 	if($scope.tablevaluesforgoal[i].golas=="Home -firstHouse")
						{
					 	//$scope.totalYear=$scope.tablevaluesforgoal[i].Year_Accomplished_end - $scope.tablevaluesforgoal[i].Year_Accomplished;
						$scope.endyear=$scope.tablevaluesforgoal[i].Year_Accomplished_end;
						$scope.housegoalArray.push(parseInt($scope.tablevaluesforgoal[i].Target_Saving));
						}
					 	for(var y=$scope.GoalsTotalAssets[0][0];y<=$scope.GoalsTotalAssets[$scope.GoalsTotalAssets.length-1][0];y++){
						 	if(j == y){
								$scope.GoalsTotalAssets[count][0] = j;
								
								
								$scope.GoalsTotalAssets[count][1] += parseInt($scope.tablevaluesforgoal[i].Target_Saving); 
								$scope.temp=$scope.totalYear-($scope.tablevaluesforgoal[i].Year_Accomplished_end-$scope.endyear);
								//alert(j+"   "+$scope.temp);
								
								if($scope.tablevaluesforgoal[i].golas=="Home -Replace first house" && j<=($scope.tablevaluesforgoal[i].Year_Accomplished_end-($scope.tablevaluesforgoal[i].Year_Accomplished_end-$scope.endyear)))
									{
									$scope.GoalsTotalAssets[count][1] -= $scope.housegoalArray[i];
									}
						 	}
			  				count++;
			  			}
					  }
				  }
				 // alert($scope.housegoalArray[i]);
				  for(var k=0;k < $scope.GoalsTotalAssets.length;k++){
				    $scope.GoalsTotalAssets[k][0] = $scope.parseDate(""+$scope.GoalsTotalAssets[k][0]); 
				    
				    //alert("jjjj")
				    
				   
				  }
				  //alert("1   "+JSON.stringify($scope.housegoalArray));
				  
				 
				  
				//  alert("2    "+JSON.stringify($scope.GoalsTotalAssets))
				  
           $scope.dataAssetsandGoals= [
	            {
	                "key" : "Assets" ,
	                
	                "values" : $scope.assetsTotalGrowth,
	                "color" :"rgb(6, 140, 53)"
	                
	            },{
	                "key" : "Goals" ,
	                "values" : $scope.GoalsTotalAssets,
	                "color" :"rgb(63, 81, 181)"
	                
	            },{
	                "key" : "Property" ,
	                "values" : $scope.reportsproperties,
	                "color" : "#ff0000"	
	                
	            }
	            
	           
	        ]
          

           
          // alert(JSON.stringify($scope.tablevaluesforgoal));	       
                if($scope.dataAssetsandGoals[0].length != 0 || $scope.dataAssetsandGoals[1].length != 0 || $scope.dataAssetsandGoals[2].length != 0){
           			jQuery("#assetsGoalsTitle").show();
           		}


				}, function(error) {
					$scope.message = result.data;

				});
}
			

$scope.changeYearreportsBR = function(year){
for(var i=0;i<$scope.assets.length;i++){
if(parseInt(year) == parseInt($scope.assets[i]['year'])){
	$scope.dataPieChartreportsBR = [
				                {
				                    key: "Savings",
				                    y: $scope.assets[i].savings,
				                    "color" :"#8E44AD"
				                },
				                {
				                    key: "Non-Taxable ",
				                    y: $scope.assets[i].nontaxable_investment_amount,
				                    "color" :"rgb(63, 81, 181)"
				                },
				                {
				                    key: "Taxable ",
				                    y: $scope.assets[i].taxable_investment_amount,
				                    "color" :"rgb(6, 140, 53)"
				                }
				            ] 
	//alert(JSON.stringify($scope.dataPieChartreportsBR))
}
}
}
/*$scope.changeYearreportsAR = function(year){
	for(var i=0;i<$scope.assets.length;i++){
	if(parseInt(year) == parseInt($scope.assets[i]['year'])){

		$scope.dataPieChartreportsAR = [
					                {
					                    key: "Savings",
					                    y: $scope.assets[i].savings
					                },
					                {
					                    key: "Non-Taxable ",
					                    y: $scope.assets[i].nontaxable_investment_amount
					                },
					                {
					                    key: "Taxable ",
					                    y: $scope.assets[i].taxable_investment_amount
					                }
					            ]      
	}
	}
	}*/


			//...............FUNCTION FOR ASSERTS GRAPH
			$scope.parseDate = d3.time.format("%Y").parse;
			$scope.plotAssetGoals=function($data)
			{

				$scope.chartGoals=[];  
                
				for(var i=0; i<$data.length; i++) {

			    //$scope.chartGoals.push({value :$data[i].value,"allowDrag" : "0"});	
               
				} 



				//editchart();


			}

			$scope.yearModal=function($datecurr)
			{

				$scope.chartYear2=[];
				for(var i=0; i<$scope.chartYear.length; i++) {
					if($scope.chartYear[i].label>$datecurr)
					{

						$scope.chartYear2.push({label :$scope.chartYear[i].label.toString()});
					}			  				   
				} 

			}



			///LOGIC FOR GRAPH EXPENSE
			$scope.addExpense=function($Syear,$Eyear,$expense)
			{

				for(var i=0;i<$scope.chartYearExpense.length;i++)	
				{	
					if($scope.chartYearExpense[i].label>=$Syear&&$scope.chartYearExpense[i].label<=$Eyear)
					{	
						$scope.chartExpense[i].value=$scope.chartExpense[i].value*1+$expense*1;
						$scope.tableIncome[i].expense=$scope.tableIncome[i].expense*1+$expense*1;
						// alert( $scope.tableIncome[i].expense)
					}
				}
                
				for(var i=0;i<$scope.chartGoals.length;i++)
				{
					if($scope.assetGoals[i].year>=$Syear&&$$scope.assetGoals[i].year<=$Eyear)
					{	

						//$scope.chartGoals[i].value=$scope.chartGoals[i].value*1+$expense*1;
						// alert( $scope.tableIncome[i].expense)
					}
				}

			}
/*			$scope.changeYearreportsBR = function(year){
for(var i=0;i<$scope.assets.length;i++){
if(parseInt(year) == parseInt($scope.assets[i]['year'])){

	$scope.dataPieChartreportsBR = [
				                {
				                    key: "Savings",
				                    y: $scope.assets[i].savings
				                },
				                {
				                    key: "Non-Taxable ",
				                    y: $scope.assets[i].nontaxable_investment_amount
				                },
				                {
				                    key: "Taxable ",
				                    y: $scope.assets[i].taxable_investment_amount
				                }
				            ]      
}
}
}
$scope.changeYearreportsAR = function(year){
	for(var i=0;i<$scope.assets.length;i++){
	if(parseInt(year) == parseInt($scope.assets[i]['year'])){

		$scope.dataPieChartreportsAR = [
					                {
					                    key: "Savings",
					                    y: $scope.assets[i].savings
					                },
					                {
					                    key: "Non-Taxable ",
					                    y: $scope.assets[i].nontaxable_investment_amount
					                },
					                {
					                    key: "Taxable ",
					                    y: $scope.assets[i].taxable_investment_amount
					                }
					            ]      
	}
	}
	}*/
			$scope.load = function() {

				//alert("load-->>");
				$scope.sessionDetails.cookieId = readCookie("AhTwxlO");

				$scope.sessionDetails.lastVisitedPage = document.URL;
				////////alert( $scope.sessionDetails.lastVisitedPage);
				if ($scope.sessionDetails.cookieId != null) {

					$http(
							{
								method : 'POST',
								url : 'CheckSession',
								data : $
								.param($scope.sessionDetails),
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							})
							.then(
									function(result) {

										if (result.data.status == "success") {
											////////alert("Cookie ajax Success");
											//////alert(result.data.lastVisitedPage);
											if (result.data.lastVisitedPage == "undefined") {

												window.location.href = "dashboardUserr0.jsp";

											} else {
												document.cookie = "lastVisitedPage="
													+ result.data.lastVisitedPage;
												if (result.data.lastVisitedPage == document.URL) {
													////////alert("redirecting since in db there is another lastvisited page")
													//window.location.href=result.data.lastVisitedPage;
												}

											}
											$scope.load1();

										} else {

											$scope.errMessage = "Session got expired";
											$("#checkSession")
											.modal("show");
											var delay = 3000; //Your delay in milliseconds
											setTimeout(
													function() {
														$scope
														.deleteAllCookies()
													}, delay);

										}

									}, function(error) {


									});
				} else {

					$scope.deleteAllCookies();
					window.location.href = "index.jsp";
				}

			}

			$scope.check2 = function() {
				window.history.go(-1);
			}
			$scope.show = function() {
				//alert("ki")
				$("#chart-container2").hide();
				$("#chart-container1").hide();
				$("#exportable1").hide();
				$("#exportable").show();
				//$("#exportable2").hide();
				$("#but").hide();
				$scope.backbutton=true;
				$scope.sortType = 'name';
				$scope.sortReverse = false;
				$scope.searchGoals = '';


				$scope.IsVisible = true;

			}

			$scope.show1=function()
			{
				$("#chart-container2").show();
			$("#chart-container1").show();
			$("#exportable1").show();
			$("#but").show();
			$scope.backbutton=false;
			$scope.IsVisible = false;

			}

			$scope.exportData = function () {
				var today = new Date();
				var dd = today.getDate();
				var mm = today.getMonth()+1; 
				var yyyy = today.getFullYear();

				if(dd<10) {
					dd='0'+dd
				} 

				if(mm<10) {
					mm='0'+mm
				} 

				today = mm+'/'+dd+'/'+yyyy;

				var blob = new Blob([document.getElementById('exportable').innerHTML], {
					type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
				});
				saveAs(blob,"Report-"+$scope.plannames+	"-"+today+".xls"  );

				$scope.show1();
			};

			/*function editChart() {}*/

			function editAssetChart()
			{
				//alert("hi");

				/*FusionCharts.ready(function () {
					var salesPrediction = new FusionCharts({
						type: 'dragline',
						renderAt: 'chart-container2',
						width: '1000',
						height: '350',
						dataFormat: 'json',
						dataSource: {
							"chart": {
								"caption": "Asset - Growth",
								"showvalues": "0",
								"xAxisName": "Year",
								"yAxisName": "Asset",
								"bgcolor": "FFFFFF",
								"showalternatehgridcolor": "0",
								"showplotborder": "1",
								"divlinecolor": "CCCCCC",
								"formatNumberScale":"0",
								"showcanvasborder": "0",
								"captionpadding": "20",
								"legendpadding": "10",
								"plotbordercolor": "2f69bf",
								"linethickness": "3",
								"formbtnbgcolor": "666666",
								"btntextcolor": "FFFFFF",
								"showrestorebtn": "0",
								"canvasborderalpha": "0",
								"legendshadow": "0",
								"legendborderalpha": "0",
								"showborder": "0",
								"submitformusingajax": "0"
							},
							"categories": [
							               {
							            	   "category":  $scope.incomeyear
							               }
							               ],
							               "dataset": [

							                           {
							                        	   "id": "CR",
							                        	   "seriesname": "Goals",
							                        	   "showvalues": "0",
							                        	   "color": "#B0C10C",
							                        	   "data":  $scope.chartGoals
							                           }, 
							                           {
							                        	   "id": "CR",
							                        	   "seriesname": "Assets",
							                        	   "showvalues": "0",
							                        	   "color": "e43234",
							                        	   "data":  $scope.chartAssets
							                           }
							                           ],
							                           "styles": {
							                        	   "definition": [
							                        	                  {
							                        	                	  "name": "myCaptionFont",
							                        	                	  "type": "font",
							                        	                	  "font": "Arial",
							                        	                	  "size": "14",
							                        	                	  "bold": "1"
							                        	                  },
							                        	                  {
							                        	                	  "name": "mySubCaptionFont",
							                        	                	  "type": "font",
							                        	                	  "font": "Arial",
							                        	                	  "size": "10",
							                        	                	  "bold": "0"
							                        	                  }
							                        	                  ],
							                        	                  "application": [
							                        	                                  {
							                        	                                	  "toobject": "Caption",
							                        	                                	  "styles": "myCaptionFont"
							                        	                                  },
							                        	                                  {
							                        	                                	  "toobject": "SubCaption",
							                        	                                	  "styles": "mySubCaptionFont"
							                        	                                  }
							                        	                                  ]
							                           }
						},
						events: {
							'dataplotdragend': function(evt, arg){



								var dsIndx = arg && arg.datasetIndex,
								dtIndx = arg && arg.dataIndex,
								val = arg && parseInt(arg.endValue, 10);



							}


						}
					}).render();
				});
*/


			}



		});
		