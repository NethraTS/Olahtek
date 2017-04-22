
angular.module('wealthsetter').controller('MortgageCtrl', ['$scope','$uibModal', '$log','$rootScope','$http','$state' ,MortgageCtrl]);
function MortgageCtrl($scope,$uibModal, $log,$rootScope,$http,$state,$uibModalInstance) {
					$rootScope.boolChangeClass = true;

                    $scope.flag;
                    $scope.income=true;
                    $scope.owner = true;
                    $scope.show = 1;
                    $scope.sessionDetails = {};
                    $scope.sessionDelete = {};
                    $scope.inputData = {};
                    $scope.remainingmortgage = [];
                    $scope.sliderValue = 15;
                    $scope.hideTable = true;
                    $scope.Annual = true;
                    $scope.guest = false;
                    /* $scope.taxguest=true; */
                    $scope.hideButton = false;
                    $scope.showButton = true;
                    $scope.Annual = true;
                    $scope.user_id = "";
                    var mortgageAmountGlobal = 0;

                    $scope.showTable = function() {
                        $scope.hideButton = true;
                        $scope.showButton = false;
                        $scope.Annual = false;
                    }
                    $scope.hideTable = function() {

                        $scope.hideButton = false;
                        $scope.showButton = true;
                        $scope.Annual = true;
                    }
                    var totalinterestGlobal = 0;
                   $scope.mortgageResults = {};
                    $scope.formdata = {};
                    $scope.showResults = false;
                    $scope.rentals =["OWNER-OCCUPIED","RENTAL"];
                    $scope.highesttaxs =["10","15","25","28","33","38","39.6"];
                    $scope.downpayments =["0","1","2","3","4","5","6","7","8","9","10","15","20","25","30","35","40","45","50","60","70","80","90","100"];
                    
                    $scope.loans = [ {
                        name : "30-year fixed"
                    }, {
                        name : "15-year fixed"
                    }, {
                        name : "3/1 ARM"
                    }, {
                        name : "5/1 ARM"
                    }, {
                        name : "jumbo 30-year fixed"
                    }, {
                        name : "jumbo 15-year fixed"
                    }, {
                        name : "jumbo 3/1 ARM"
                    }, {
                        name : "jumbo 5/1 ARM"
                    } ];
                   
                  
                    $scope.editBasicDetail = {};
                    
                    $scope.Annualdata = function() {
                        $scope.Annual = false;
                    }

                    $scope.myFunction = function() {
                        var x = document.getElementById("myDIV");
                        if (x.style.display === "none") {
                            x.style.display = "block";
                        } else {
                            x.style.display = "none";
                        }
                    }
                    $rootScope.userFlag = false;
					$rootScope.homeLocation = "index";
                    $scope.load = function() {

                        $scope.formdata.pmi = "0";
                        $scope.formdata.hoaFee = "0";
                        $scope.formdata.maintenance = "0";
                        $scope.formdata.homeInsurance = "0";
                        $scope.formdata.downPayment = "0";
                        $scope.formdata.propertyTax = "0";
                        $scope.formdata.managementFee = "0";
                        $scope.formdata.rentalIncome = "0";
                        $scope.formdata.propertyLand = "0";
                        $scope.formdata.loanTerm = "30-year fixed";
                        $scope.getStates();
                        $scope.loading = false;
                        $scope.loanTerms();
                        $scope.masked = false;
                        $scope.sessionDetails.cookieId = readCookie("AhTwxlO");
                        $scope.sessionDetails.lastVisitedPage = document.URL;
                       if ($scope.sessionDetails.cookieId != null) {
                        $http(
                                {
                                    method : "POST",
                                    url : "CheckSession",
                                    data : $.param($scope.sessionDetails),
                                    headers : {
                                        "Content-Type" : "application/x-www-form-urlencoded"
                                    }
                                }).then(function(result) {

                           // console.log(result.data);
                            if (result.data.status == "success") {
                            	$scope.user_id = result.data.user_id;
                                $scope.guest = false;
                                $scope.taxguest = true;
                                $scope.flag = 1;
                                $rootScope.userFlag = true;
    							$rootScope.homeLocation = "dashboard.home";

                            } else {
                                $scope.guest = true;
                                $scope.taxguest = false;
                                $scope.flag = 2;
                                $rootScope.userFlag = false;
    							$rootScope.homeLocation = "index";

                            }

                        }, function(error) {
                        });

                    } else {
                    	$scope.guest = true;
                        $scope.taxguest = false;
                        $scope.flag = 2;
                        $rootScope.userFlag = false;
						$rootScope.homeLocation = "index";
                    }
                    };

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

                    $scope.getStates = function() {
                        $scope.inputData.states = $scope.formdata.state;

                        $scope.inputData.action = "getHomeInsurance";
                        $http(
                                {
                                    method : "POST",
                                    url : "MortgageCalculator",
                                    data : $.param($scope.inputData),
                                    headers : {
                                        "Content-Type" : "application/x-www-form-urlencoded"
                                    }
                                })
                                .then(
                                        function(result) {

                                           // console.log(result.data);
                                            if (result.data.status == "success") {
                                                $scope.formdata.homeInsurance = result.data.annual_Homeowners_Insurance;
                                            }
                                        }, function(error) {

                                        });
                        $scope.formData1 = {};
                        $scope.formData1.city = $scope.formdata.state;
                        $scope.formData1.actionType = "getCities";
                        $scope.disabledCity = "true";
                        $scope.msgerr = "";
                        $http(
                                {
                                    method : "POST",
                                    url : "AutoComplete",
                                    data : $.param($scope.formData1),
                                    headers : {
                                        "Content-Type" : "application/x-www-form-urlencoded"
                                    }
                                })
                                .then(
                                        function(result) {
                                            $scope.citys = result.data;
                                            $scope.citys.sort();
                                            //console.log($scope.citys);

                                            $scope.formdata.desired_locationcity = $scope.citys[0];

                                            $scope.disabledCity = false;
                                            $scope.getCounty();

                                        }, function(error) {
                                            window.location.href = "";
                                        });
                    }

                    $scope.getCounty = function() {
                        //$scope.inputData.citys = $scope.formdata.desired_locationcity;
                        $scope.countyData1 = {};
                        $scope.countyData1.country = $scope.formdata.desired_locationcity;
                        $scope.countyData1.city = $scope.formdata.state;
                        $scope.countyData1.actionType = "getCounty";
                        $http(
                                {
                                    method : 'POST',
                                    url : 'AutoComplete',
                                    data : $.param($scope.countyData1),
                                    headers : {
                                        'Content-Type' : 'application/x-www-form-urlencoded'
                                    }
                                })
                                .then(
                                        function(result) {
                                           // console.log(result.data);
                                            $scope.inputData.county = result.data;
                                            $scope.inputData.citys = $scope.formdata.desired_locationcity;
                                            $scope.inputData.states = $scope.formdata.state;
                                            $scope.inputData.action = "getHomeValue";
                                           // console.log($scope.inputData);
                                            $http(
                                                    {
                                                        method : "POST",
                                                        url : "MortgageCalculator",
                                                        data : $
                                                                .param($scope.inputData),
                                                        headers : {
                                                            "Content-Type" : "application/x-www-form-urlencoded"
                                                        }
                                                    })
                                                    .then(
                                                            function(result) {

                                                                
                                                            	//console.log(result.data);
                                                                if (result.data.status == "success") {
                                                                    $scope.formdata.homeValue = result.data.House_Value.toFixed(2);
                                                                }
                                                            }, function(error) {

                                                            });
                                        }, function(error) {

                                        });
                    }

                    $scope.getHOA = function() {
                       // console.log($scope.formdata.propertyType);
                        if ($scope.formdata.propertyType == "Townhouse") {
                            $scope.formdata.hoaFee = "250";
                        } else if ($scope.formdata.propertyType == "Singlefamily") {
                            $scope.formdata.hoaFee = "0";
                        } else {

                        }
                    }
                    
                    $scope.rental = function() {
                        //console.log($scope.formdata.propertyType);
                    	//alert($scope.formdata.forRental);
                    	 if ($scope.formdata.forRental == "OWNER-OCCUPIED") {
                         	
                         	$scope.income=false;
                         	$scope.owner=false;
                         }
                    	 else {
                        	
                        	$scope.income=true;
                        	$scope.owner=true;
                        }
                        /*else {
                        	$scope.income=false;
                        }*/
                    }


                    $scope.loanTerms = function() {
//alert();
                        $scope.inputData.loanTerm = $scope.formdata.loanTerm;
                        $scope.inputData.state = $scope.formdata.state;

                        $scope.inputData.action = "getLoanTerm";
                       // console.log($scope.inputData);
                        $http(
                                {
                                    method : "POST",
                                    url : "MortgageCalculator",
                                    data : $.param($scope.inputData),
                                    headers : {
                                        "Content-Type" : "application/x-www-form-urlencoded"
                                    }
                                })
                                .then(
                                        function(result) {

                                           // console.log(result.data);
                                            if (result.data.status == "success") {

                                                $scope.formdata.interestRate = result.data.interestRate
                                                        .toFixed(2);
                                            }
                                        }, function(error) {

                                        });

                    }

                    $scope.calculate = function() {
                    	
                        $scope.loanTerm = 0;
                        if ($scope.formdata.forRental == null
                                || $scope.formdata.forRental == undefined
                                || $scope.formdata.forRental == "") {

                            $scope.mc_msgerr = "House type should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        } else if ($scope.formdata.managementFee == null && $scope.formdata.rentalIncome == "RENTAL"
                                || $scope.formdata.managementFee == undefined && $scope.formdata.rentalIncome == "RENTAL"
                                || $scope.formdata.managementFee == "" && $scope.formdata.managementFee == "RENTAL" ) {

                            $scope.mc_msgerr = "Management fee should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        }  else if ($scope.formdata.rentalIncome == null && $scope.formdata.rentalIncome == "RENTAL"
                                || $scope.formdata.rentalIncome == undefined && $scope.formdata.rentalIncome == "RENTAL"
                                || $scope.formdata.rentalIncome == "" && $scope.formdata.rentalIncome == "RENTAL") {

                            $scope.mc_msgerr = "Rental income should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        }  else if ($scope.formdata.propertyLand == null && $scope.formdata.propertyLand == "RENTAL"
                                || $scope.formdata.propertyLand == undefined && $scope.formdata.propertyLand == "RENTAL"
                                || $scope.formdata.propertyLand == "" && $scope.formdata.propertyLand == "RENTAL") {

                            $scope.mc_msgerr = "Property value should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        } 
                          else if ($scope.formdata.state == null
                                || $scope.formdata.state == undefined
                                || $scope.formdata.state == "") {

                            $scope.mc_msgerr = "State name should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        } else if ($scope.formdata.desired_locationcity == null
                                || $scope.formdata.desired_locationcity == undefined
                                || $scope.formdata.desired_locationcity == "") {

                            $scope.mc_msgerr = "City name should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        } else if ($scope.formdata.homeValue == null
                                || $scope.formdata.homeValue == undefined
                                || $scope.formdata.homeValue == "") {

                            $scope.mc_msgerr = "Home value should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        }

                        else if ($scope.formdata.downPayment == null
                                || $scope.formdata.downPayment == undefined
                                || $scope.formdata.downPayment == "") {

                            $scope.mc_msgerr = "Down payment should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        } else if ($scope.formdata.downPayment * 1 > $scope.formdata.homeValue * 1) {
                            $scope.mc_msgerr = "Down payment should not greater than Home value";
                            $scope.showResults = false;
                            d3.select("svg").remove();

                        }

                        else if ($scope.formdata.interestRate == null
                                || $scope.formdata.interestRate == undefined
                                || $scope.formdata.interestRate == "") {

                            $scope.mc_msgerr = "Interest rate should not be empty";
                            $scope.showResults = false;
                        } else if ($scope.formdata.interestRate <= 0) {

                            $scope.mc_msgerr = "Interest rate should not be zero";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        }

                        else if ($scope.formdata.loanTerm == null
                                || $scope.formdata.loanTerm == undefined
                                || $scope.formdata.loanTerm == "") {

                            $scope.mc_msgerr = "Loan term should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        }

                        else if ($scope.formdata.propertyTax == null
                                || $scope.formdata.propertyTax == undefined
                                || $scope.formdata.propertyTax == "") {

                            $scope.mc_msgerr = "Property tax should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();

                        }

                        else if ($scope.formdata.pmi == null
                                || $scope.formdata.pmi == undefined
                                || $scope.formdata.pmi == "") {

                            $scope.mc_msgerr = "Pmi should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();

                        }

                        else if ($scope.formdata.propertyType == null
                                || $scope.formdata.propertyType == undefined
                                || $scope.formdata.propertyType == "") {

                            $scope.mc_msgerr = "Property type should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        }

                        else if ($scope.formdata.hoaFee == null
                                || $scope.formdata.hoaFee == undefined
                                || $scope.formdata.hoaFee == "") {

                            $scope.mc_msgerr = "Hoa fee should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        }

                        else if ($scope.formdata.maintenance == null
                                || $scope.formdata.maintenance == undefined
                                || $scope.formdata.maintenance == "") {

                            $scope.mc_msgerr = "Maintenace should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        }

                        else if ($scope.formdata.homeInsurance == null
                                || $scope.formdata.homeInsurance == undefined
                                || $scope.formdata.homeInsurance == "") {

                            $scope.mc_msgerr = "Home insurance should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        }

                        else if ($scope.flag == 2
                                && $scope.formdata.filingStatus == null
                                || $scope.flag == 2
                                && $scope.formdata.filingStatus == undefined
                                || $scope.flag == 2
                                && $scope.formdata.filingStatus == "") {

                            $scope.mc_msgerr = "Filing status should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        }

                        else if ($scope.formdata.highestTaxRate == null
                                || $scope.formdata.highestTaxRate == undefined
                                || $scope.formdata.highestTaxRate == "") {

                            $scope.mc_msgerr = "Highest tax rate should not be empty";
                            $scope.showResults = false;
                            d3.select("svg").remove();
                        }

                        else {
                            if ($scope.formdata.loanTerm == "30-year fixed") {
                                $scope.loanTerm = 30;
                                $scope.showResults = false;
                                d3.select("svg").remove();
                            } else if ($scope.formdata.loanTerm == "15-year fixed") {
                                $scope.loanTerm = 15;
                                $scope.showResults = false;
                                d3.select("svg").remove();
                            } else if ($scope.formdata.loanTerm == "3/1 ARM") {
                                $scope.loanTerm = 3;
                                $scope.showResults = false;
                                d3.select("svg").remove();
                            }

                            else if ($scope.formdata.loanTerm == "5/1 ARM") {
                                $scope.loanTerm = 5;
                                $scope.showResults = false;
                                d3.select("svg").remove();
                            } else if ($scope.formdata.loanTerm == "jumbo 30-year fixed") {
                                $scope.loanTerm = 30;
                                $scope.showResults = false;
                                d3.select("svg").remove();
                            } else if ($scope.formdata.loanTerm == "jumbo 15-year fixed") {
                                $scope.loanTerm = 15;
                                $scope.showResults = false;
                                d3.select("svg").remove();
                            } else if ($scope.formdata.loanTerm == "jumbo 3/1 ARM") {
                                $scope.loanTerm = 3;
                                $scope.showResults = false;
                                d3.select("svg").remove();
                            } else if ($scope.formdata.loanTerm == "jumbo 5/1 ARM") {
                                $scope.loanTerm = 5;
                                $scope.showResults = false;
                                d3.select("svg").remove();
                            }

                            $scope.inputData.action = "edit1";
                            $scope.inputData.for_rental = $scope.formdata.forRental;
                            $scope.inputData.management_fee = $scope.formdata.managementFee;
                            $scope.inputData.rental_income = $scope.formdata.rentalIncome;
                            $scope.inputData.property_land = $scope.formdata.propertyLand;
                            $scope.inputData.home_value = Math.round($scope.formdata.homeValue);
                            $scope.inputData.down_payment = Math.round($scope.formdata.downPayment);
                            $scope.inputData.interest_rate = $scope.formdata.interestRate;
                            $scope.inputData.loan_term = $scope.loanTerm;
                            $scope.inputData.property_tax = $scope.formdata.propertyTax;
                            $scope.inputData.pmi = $scope.formdata.pmi;
                            $scope.inputData.property_type = $scope.formdata.propertyType;
                            $scope.inputData.hoa_fee = $scope.formdata.hoaFee;
                            $scope.inputData.maintenance = $scope.formdata.maintenance;
                            $scope.inputData.home_insurance = $scope.formdata.homeInsurance;
                            $scope.inputData.filing_status = $scope.formdata.filingStatus;
                            $scope.inputData.standard_deduction = $scope.formdata.standardDeduction;
                            $scope.inputData.highest_taxrate = $scope.formdata.highestTaxRate;
                            $scope.inputData.flag = $scope.flag;
                            $scope.inputData.user_id = $scope.user_id;
                           // console.log($scope.inputData);

                            $http(
                                    {
                                        method : "POST",
                                        url : "MortgageCalculator",
                                        data : $.param($scope.inputData),
                                        headers : {
                                            "Content-Type" : "application/x-www-form-urlencoded"
                                        }
                                    })
                                    .then(
                                            function(result) {
                                                if (result.data.status == "success") {
                                                	/*$state.go('calculator.mortgageresult');*/
                                                	 if ($scope.formdata.forRental=='RENTAL') {
                                                		 $scope.income=true;
                                                      }
                                                	 else  
                                                		 {
                                                		 $scope.income=false;
                                                		 }
                                                	 if ($scope.formdata.forRental=='OWNER-OCCUPIED') {
                                             			//alert();
                                             		 $scope.owner=false;
                                                   }
                                             	 else 
                                             		 {
                                             		 $scope.owner=true;
                                             		 }
                                                	// $scope.income=true;
                                                	// alert($scope.income)
                                                    $scope.mc_msgerr = "";
                                                    $scope.others = result.data.others;
                                                    $scope.pmi = result.data.pmi1;
                                                    $scope.Interestrate = result.data.interestrate1;
                                                    $scope.proptax = result.data.proptax;
                                                    $scope.loanpayoff = result.data.loanpayoff;
                                                    $scope.monthlymortgage = result.data.monthlymortgage
                                                            .toFixed(0);
                                                    $scope.exactAnual_morgage = result.data.exactanualmorgage
                                                            .toFixed(0);
                                                    $scope.totalAnualhouseExpense = result.data.totalAnualhouseExpense
                                                            .toFixed(0);
                                                    $scope.totalinterest = result.data.totalinterest
                                                            .toFixed(0);
                                                    $scope.mortgageAmount = result.data.mortgageAmount
                                                            .toFixed(0);
                                                    $scope.taxablededuction = result.data.taxablededuction
                                                            .toFixed(0);
                                                    $scope.taxsaving = result.data.taxsaving
                                                            .toFixed(0);
                                                    $scope.Firstyearinterest1 = result.data.firstyearinterest1
                                                            .toFixed(0);
                                                    $scope.remainingmortgage = result.data.remainingmortgage;
                                                    $scope.interestRate = result.data.interestRate;
                                                    $scope.additionaltax = result.data.additionaltax;
                                                    $scope.incomeaftertaxes = result.data.incomeaftertaxes;
                                                    $scope.taxbenefit = result.data.taxbenefit;
                                                    $scope.costaftertax = result.data.costaftertax;
                                                    
                                                   $scope.data = [
                                                            {
                                                                "key" : "Remaining mortgage",
                                                                "values" : []
                                                            },
                                                            {
                                                                "key" : "Interest",
                                                                "values" : []
                                                            },
                                                            {
                                                                "key" : "principle",
                                                                "values" : []
                                                            } ];

                                                    $scope.options = {
                                                        chart : {
                                                            type : "multiBarChart",
                                                            height : 450,
                                                            margin : {
                                                                top : 20,
                                                                right : 20,
                                                                bottom : 45,
                                                                left : 45
                                                            },
                                                            clipEdge : true,
                                                            duration : 500,
                                                            stacked : true,
                                                            xAxis : {
                                                                axisLabel : "Years",
                                                                showMaxMin : false,
                                                                tickFormat : function(
                                                                        d) {
                                                                    return d3
                                                                            .format(
                                                                                    "f")
                                                                            (d);
                                                                }
                                                            },
                                                            yAxis : {
                                                                axisLabel : "Balance",
                                                                axisLabelDistance : -20,
                                                                tickFormat : function(
                                                                        d) {
                                                                    return d3
                                                                            .format(
                                                                                    "f")
                                                                            (d);
                                                                }
                                                            }
                                                        }
                                                    };

                                                    for (i = 0; i < $scope.remainingmortgage.length; i++) {
                                                        $scope.year = $scope.remainingmortgage[i].year;
                                                        $scope.remmort = $scope.remainingmortgage[i].remmort;
                                                        $scope.interestpaid = $scope.remainingmortgage[i].interestpaid;
                                                        $scope.princ = $scope.remainingmortgage[i].princ;

                                                        var year1 = {
                                                            "x" : $scope.year,
                                                            "y" : $scope.remmort
                                                        };
                                                        var remmort1 = {
                                                            "x" : $scope.year,
                                                            "y" : $scope.interestpaid
                                                        };
                                                        var remmort2 = {
                                                            "x" : $scope.year,
                                                            "y" : $scope.princ
                                                        };

                                                        $scope.data[0].values
                                                                .push(year1);
                                                        $scope.data[1].values
                                                                .push(remmort1);
                                                        $scope.data[2].values
                                                                .push(remmort2);

                                                    }

                                                    $scope.showResults = true;
                                                }
console.log($scope.data);
                                                mortgageAmountGlobal = result.data.mortgageAmount.toFixed(0);
                                                        
                                                totalinterestGlobal = result.data.totalinterest
                                                        .toFixed(0);

                                                $scope.drawChart(
                                                        mortgageAmountGlobal,
                                                        totalinterestGlobal);

                                            }, function(error) {

                                            } );
                                                    
                                                    

                        }
                   }

                    $scope.drawChart = function(a, b) {
                        var st = {};

                        st.data = [ {
                            "label" : "Principal",
                            "value" : a,
                            "pos" : 0
                        }, {
                            "label" : "Interest",
                            "value" : b,
                            "pos" : 1
                        } ];
                        drawPieChartAccessAgesByCountD3();

                        window.onresize = function(event) {
                            drawPieChartAccessAgesByCountD3();
                        }
                        function drawPieChartAccessAgesByCountD3() {
                            drawD3PieChart("#ChartAccessAgesByCountD3",
                                    st.data, [ 0, 1, 2, 3 ]);
                        }
                        function drawD3PieChart(sel, data, row_id_to_bucket_num) {
                            $(sel + " svg").remove();

                            tot = 0;
                            data.forEach(function(e) {
                                tot += e.value;
                            });
                            var w = $(sel).width();
                            var h = $(sel).height();
                            var r = Math.min(w, h) / 2;
                            var color = d3.scale.category20c();
                            var vis = d3.select(sel).append("svg:svg").attr(
                                    "data-chart-context", sel).data([ data ])
                                    .attr("width", w).attr("height", h).append(
                                            "svg:g").attr(
                                            "transform",
                                            "translate( " + (w / 2) + "," + r
                                                    + " )");
                            var svgParent = d3
                                    .select("svg[data-chart-context='" + sel
                                            + "']");
                            var pie = d3.layout.pie().value(function(d) {
                                return d.value;
                            });
                            var arc = d3.svg.arc().outerRadius(r);
                            var arcs = vis.selectAll("g.slice").data(pie)
                                    .enter().append("svg:g").attr("class",
                                            "slice");
                            arcs
                                    .append("svg:path")
                                    .attr("fill", function(d, i) {
                                        return color(i);
                                    })
                                    .attr("stroke", "#fff")
                                    .attr("stroke-width", "1")
                                    .attr("d", function(d) {

                                        return arc(d);
                                    })
                                    .attr("data-legend", function(d) {
                                        return d.data.label;
                                    })
                                    .attr("data-legend-pos", function(d) {
                                        return d.data.pos;
                                    })
                                    .classed("slice", true)
                                    .on(
                                            "click",
                                            function(e) {
                                                var chartDiv = $(sel);
                                                var selectedValue = $(this)
                                                        .attr("data-legend-pos");
                                                var bucket = row_id_to_bucket_num[selectedValue];
                                                var dest = chartDiv
                                                        .attr("data-drilldown-destination");
                                                var param = chartDiv
                                                        .attr("data-drilldown-key");
                                                var baseURL = dest + "/?"
                                                        + param + "=";

                                            }).on(
                                            "mouseover",
                                            function(e) {
                                                $(this).attr("fill-opacity",
                                                        ".8").css({
                                                    "stroke" : "green",
                                                    "stroke-width" : "1px"
                                                });
                                            }).on("mouseout", function(e) {
                                        $(this).attr("fill-opacity", "1").css({
                                            "stroke-width" : "0px"
                                        });
                                    }).attr("style", "cursor:pointer;").append(
                                            "svg:title").text(function(d) {
                                        return d.data.label;
                                    });

                            arcs.append("svg:text").attr(
                                    "transform",
                                    function(d) {
                                        d.innerRadius = 0;
                                        d.outerRadius = r;
                                        return "translate( " + arc.centroid(d)
                                                + " )";
                                    }).attr("text-anchor", "middle").text(
                                    function(d, i) {
                                        return (data[i].value)
                                    }).attr("fill", "#fff").classed(
                                    "slice-label", true);

                            var legend = d3.select("#ChartAccessAgesByCountD3")
                                    .append("svg").attr("class", "legend")
                                    .attr("width", r).attr("height", r * 2)
                                    .selectAll("g").data(data).enter().append(
                                            "g").attr(
                                            "transform",
                                            function(d, i) {
                                                return "translate( 0," + i * 20
                                                        + " )";
                                            });

                            legend.append("rect").attr("width", 18).attr(
                                    "height", 18).style("fill", function(d, i) {
                                return color(i);
                            });

                            legend.append("text").attr("x", 24).attr("y", 9)
                                    .attr("dy", ".35em").text(function(d) {
                                        return d.label;
                                    });

                        }

                    }

                    $scope.deleteAllCookies = function() {

                        $scope.sessionDelete.sessionID = readCookie("AhTwxlO");
                        $http(
                                {
                                    method : "POST",
                                    url : "Logout",
                                    data : $.param($scope.sessionDelete),
                                    headers : {
                                        "Content-Type" : "application/x-www-form-urlencoded"
                                    }
                                }).then(function(result) {
                            window.location.href = "index.jsp";

                        }, function(error) {
                        });
                    }

                    function readCookie(name) {
                        var nameEQ = name + "=";
                        var ca = document.cookie.split(";");
                        for (var i = 0; i < ca.length; i++) {
                            var c = ca[i];
                            while (c.charAt(0) == " ") {
                                c = c.substring(1, c.length);
                            }
                            if (c.indexOf(nameEQ) == 0) {
                                return c.substring(nameEQ.length, c.length);
                            }
                        }
                        return null;

                    }
 
              }
                        
/*-------------------------------------------------------------------------------------*/

