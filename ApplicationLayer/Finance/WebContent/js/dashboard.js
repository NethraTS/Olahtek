$("#chartDetails").on("shown.bs.modal", function() {
    $(this).find(".modal-dialog").css({
        width : "auto",
        height : "auto",
        "max-height" : "100%"
    });
});

function onlyAlphaNumbers(e, t) {
    var len = t.value.length;
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        } else if (e) {
            var charCode = e.which;
        } else {
            return true;
        }
        if ((charCode > 64 && charCode < 91)
                || (charCode > 96 && charCode < 123) || charCode == 8
                || charCode == 127 || charCode == 32 || charCode == 95
                || charCode == 45 || (charCode >= 48 && charCode <= 57)
                || charCode == 40 || charCode == 41) {
            if (len == 250) {
                if (charCode == 8) {
                    return true;
                } else {
                    // //alert( "Only 25 characters allowed" );
                    return false;
                }
            } else {
                return true;
            }
        } else
            return false;
    } catch (err) {
        // //alert( err.Description );
    }
}

// var app = angular.module( "dashboard", ["nvd3"] ); commenting for
var app = angular.module("dashboard", [ "nvd3" ]);
app
        .directive(
                "loading",
                function() {
                    return {
                        restrict : "E",
                        replace : true,
                        template : "<div class='loading' width='50%' height='50%'><img src='http://www.nasa.gov/multimedia/videogallery/ajax-loader.gif' width='20' height='20' />LOADING...</div>",
                        link : function(scope, element, attr) {
                            scope.$watch("loading", function(val) {
                                if (val)
                                    $(element).show();
                                else
                                    $(element).hide();
                            });
                        }
                    };
                });
var url = window.location.href;
var hashes = url.split("=")[1];
app
        .controller(
                "dashboardController",
                function($http, $scope) {
                    $scope.messageChangeIncome = "";
                    $scope.startYearBR = 0;
                    $scope.startYearPorfolioBR = 2017;
                    $scope.ages = [];
                    $scope.investMessage = "";
                    $scope.preChangedValue;
                    $scope.masked = false;
                    $scope.maskedPlan = false;
                    $scope.maskedPlotChart = true;
                    $scope.growthTable1 = false;
                    $scope.dashboardportfoliohide = false;
                    $scope.preStartIndex;
                    $scope.preEndIndex;
                    $scope.checkDrag = 0;
                    $scope.defaultretirementAge = 70;
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
                    $scope.userdetails = {};
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
                    $scope.tableIncome_imp = [];
                    $scope.maritalFlag_imp = false;
                    $scope.maritalFlag_imp1 = false;
                    $scope.chartCombinedIncome = [];
                    $scope.Count = 0;
                    $scope.chartAssets = [];
                    $scope.chartGoals = [];
                    $scope.assetGoals = [];
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
                    $scope.FinPlanName = "";
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
                    $scope.growthTable = false;
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
                    $scope.finPlanDetails = false;
                    $scope.incomeProfileDetails = true;
                    $scope.modifiedchartIncome = [];
                    $scope.modifiedchartIncomeSpouse = [];
                    $scope.modifiedchartIncomeBackup = [];
                    $scope.modifiedchartIncomeSpouseBackup = [];
                    $scope.defaultUserRetirementAge = 70;
                    $scope.defaultSpouseRetirementAge = 70;
                    $scope.ageForRiskCal = 0;
                    $scope.tempData = [];
                    var maxY = 0;
                    var maxY1 = 0;
                    $scope.filingStatus = "";
                    var a = 0, b = 0;
                    var data = [];
                    var tempData = [];
                    var tempYears = [];
                    var drag = [];
                    var drag1 = [];
                    var tempData1 = [];
                    var tempYears1 = [];
                    var tempDataHou = [];
                    var tempDataNonHou = [];
                    var tempYearsHou = [];
                    var tempYearsNonHou = [];
                    var dragHou = [];
                    var dragNonHou = [];

                    $scope.expenseValues = [0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0 ];
                    $scope.housingExpense = 0;
                    $scope.nonHousingExpense = 0;
                    $scope.marital_status = "";
                    $scope.growthRateTemp = 0;
                    $scope.portfolioDividendTemp = 0;
                    $scope.portfolioInterestTemp = 0;
                    $scope.age = 0;
                    $scope.ageUser = 0;

                    $scope.expensesForGraph = [];
                    $scope.taxForGraphIncome = [];
                    $scope.taxForGraphFin = [];
                    
                    $scope.editExpenseFlag = 0;
                    $scope.showEditValues = false;
                    $scope.checkboxData.editExp = "";
                    
                    $scope.ages = [
                    /*
                     * { number: "1" }, { number: "2" }, { number: "3" }, {
                     * number: "4" }, { number: "5" }, { number: "6" }, {
                     * number: "7" }, { number: "8" }, { number: "9" }, {
                     * number: "10" }, { number: "11" }, { number: "12" }, {
                     * number: "13" }, { number: "14" },
                     */
                    {
                        number : "15"
                    }, {
                        number : "16"
                    }, {
                        number : "17"
                    }, {
                        number : "18"
                    }, {
                        number : "19"
                    }, {
                        number : "20"
                    }, {
                        number : "21"
                    }, {
                        number : "22"
                    }, {
                        number : "23"
                    }, {
                        number : "24"
                    }, {
                        number : "25"
                    }, {
                        number : "26"
                    }, {
                        number : "27"
                    }, {
                        number : "28"
                    }, {
                        number : "29"
                    }, {
                        number : "30"
                    }, {
                        number : "31"
                    }, {
                        number : "32"
                    }, {
                        number : "33"
                    }, {
                        number : "34"
                    }, {
                        number : "35"
                    }, {
                        number : "36"
                    }, {
                        number : "37"
                    }, {
                        number : "38"
                    }, {
                        number : "39"
                    }, {
                        number : "40"
                    }, {
                        number : "41"
                    }, {
                        number : "42"
                    }, {
                        number : "43"
                    }, {
                        number : "44"
                    }, {
                        number : "45"
                    }, {
                        number : "46"
                    }, {
                        number : "47"
                    }, {
                        number : "48"
                    }, {
                        number : "49"
                    }, {
                        number : "50"
                    }, {
                        number : "51"
                    }, {
                        number : "52"
                    }, {
                        number : "53"
                    }, {
                        number : "54"
                    }, {
                        number : "55"
                    }, {
                        number : "56"
                    }, {
                        number : "57"
                    }, {
                        number : "58"
                    }, {
                        number : "59"
                    }, {
                        number : "60"
                    }, {
                        number : "61"
                    }, {
                        number : "62"
                    }, {
                        number : "63"
                    }, {
                        number : "64"
                    }, {
                        number : "65"
                    }, {
                        number : "66"
                    }, {
                        number : "67"
                    }, {
                        number : "68"
                    }, {
                        number : "69"
                    }, {
                        number : "70"
                    }, {
                        number : "71"
                    }, {
                        number : "72"
                    }, {
                        number : "73"
                    }, {
                        number : "74"
                    }, {
                        number : "75"
                    }, {
                        number : "76"
                    }, {
                        number : "77"
                    }, {
                        number : "78"
                    }, {
                        number : "79"
                    }, {
                        number : "80"
                    }, {
                        number : "81"
                    }, {
                        number : "82"
                    }, {
                        number : "83"
                    }, {
                        number : "84"
                    }, {
                        number : "85"
                    }, {
                        number : "86"
                    }, {
                        number : "87"
                    }, {
                        number : "88"
                    }, {
                        number : "89"
                    }, {
                        number : "80"
                    }, {
                        number : "81"
                    }, {
                        number : "82"
                    }, {
                        number : "83"
                    }, {
                        number : "84"
                    }, {
                        number : "85"
                    }, {
                        number : "86"
                    }, {
                        number : "87"
                    }, {
                        number : "88"
                    }, {
                        number : "89"
                    }, {
                        number : "90"
                    }, {
                        number : "91"
                    }, {
                        number : "92"
                    }, {
                        number : "93"
                    }, {
                        number : "94"
                    }, {
                        number : "95"
                    }, {
                        number : "96"
                    }, {
                        number : "97"
                    }, {
                        number : "98"
                    }, {
                        number : "99"
                    } ];
                    $scope.filingOptions = [ {
                        id : "Single"
                    }, {
                        id : "Married Filing Jointly"
                    } ];
                    $scope.filingOptions1 = [ {
                        id : "Risk aggressive"
                    }, {
                        id : "Risk neutral"
                    }, {
                        id : "Risk Adverse"
                    } ];

                    $scope.deleteAllCookies = function() {
                        // ////alert( "delete all cookies" );

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
                            // ////alert( "Session Got deleted" );

                            window.location.href = "index.jsp";

                        }, function(error) {
                            // ////alert( "Session not deleted" );

                        });
                    };

                    function readCookie(name) {
                        // ////alert( "hi" );
                        var nameEQ = name + "=";
                        var ca = document.cookie.split(";");
                        for (var i = 0; i < ca.length; i++) {
                            var c = ca[i];
                            while (c.charAt(0) == " ")
                                c = c.substring(1, c.length);
                            if (c.indexOf(nameEQ) == 0)
                                return c.substring(nameEQ.length, c.length);
                        }
                        return null;
                    }

                    $scope.load = function() {

                        // alert( "mkmkm" );
                        $scope.Onload();

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
                                    })
                                    .then(
                                            function(result) {

                                                if (result.data.status == "success") {
                                                    if (result.data.lastVisitedPage == "undefined") {
                                                        window.location.href = "dashboardUserr0.jsp";
                                                    } else {
                                                        document.cookie = "lastVisitedPage="
                                                                + result.data.lastVisitedPage;
                                                        if (result.data.lastVisitedPage == document.URL) {
                                                        }
                                                    }
                                                    $scope.load1();
                                                } else {
                                                    $scope.errMessage = "Session got expired";
                                                    $("#checkSession").modal(
                                                            "show");
                                                    var delay = 3000; // Your
                                                    setTimeout(
                                                            function() {
                                                                $scope
                                                                        .deleteAllCookies();
                                                            }, delay);
                                                }
                                            }, function(error) {
                                            });
                        } else {
                            // ////alert( "Session got expired" );
                            $scope.deleteAllCookies();
                            window.location.href = "index.jsp";
                        }
                    };

                    $scope.Onload = function() {
                        // alert( "onload" );
                        $scope.masked = true;
                        $scope.disabled = true;

                        $scope.userdetails.form = "userDetails";
                        $http(
                                {
                                    method : "POST",
                                    url : "UserProfile",
                                    data : $.param($scope.userdetails),
                                    headers : {
                                        "Content-Type" : "application/x-www-form-urlencoded"
                                    }
                                }).then(function(result) {
                            $scope.filingStatus = result.data.filingStatus;  
                        }, function(error) {
                            // //alert( "Fail" );
                            $scope.message = "Fail";
                            $scope.errorName = "";
                            $scope.errorSuperhero = "";
                        });
                    };

                    $scope.load1 = function() {

                        $scope.growthTable = false;
                        $scope.showIncomeExp = true;
                        $scope.masked = true;
                        $scope.loading = true;
                        $scope.birthYear;
                        $scope.tableIncome = [];
                        $scope.planNames = [];
                        $scope.planNames1 = [];
                        $scope.chartIncomeSpouse = [];
                        $scope.chartYear = [];
                        $scope.chartIncome = [];
                        $scope.retirementPoint;
                        $scope.combined_income = [];
                        $scope.chartIncomeSave = [];
                        $scope.spouseIncomeSave = [];
                        $scope.chartYearSave = [];
                        $scope.assets = [];
                        $scope.tax = [];
                        $scope.assetGoals = [];
                        $("#success-alert").hide();
                        $scope.createPlan = true;
                        $scope.Changedyear = "";
                        $scope.modifiedchartIncome = [];
                        $scope.modifiedchartIncomeSpouse = [];
                        $scope.modifiedchartIncomeBackup = [];
                        $scope.modifiedchartIncomeSpouseBackup = [];
                        $scope.tempData = [];
                        $scope.dataUserIncome = 0;
                        $scope.dataSpouseIncome = 0;

                        maxY = 0;
                        maxY1 = 0;
                        a = 0;
                        b = 0;
                        data = [];
                        tempData = [];
                        tempYears = [];
                        drag = [];
                        drag1 = [];
                        tempData1 = [];
                        tempYears1 = [];
                        tempDataHou = [];
                        tempDataNonHou = [];
                        $scope.formdata.form = "showPlan";
                        $http(
                                {
                                    method : "POST",
                                    url : "CreateFinPlan",
                                    data : $.param($scope.formdata),
                                    headers : {
                                        "Content-Type" : "application/x-www-form-urlencoded"
                                    }
                                })
                                .then(
                                        function(result) {
                                            console.log(result.data);
                                            $scope.editExpenseFlag = result.data.editExpenseFlag;
                                            $scope.incomeProfiles = result.data.income_profiles;

                                            $scope.riskScore = result.data.riskScore;

                                            $scope.planNames = result.data.Plans;
                                            $scope.Count = result.data.Planscount;

                                            $scope.userName = "Welcome "
                                                    + result.data.userName;
                                            $scope.income = result.data.income;
                                            $scope.startYearBR = $scope.income[0].year;
                                            $scope.birthYear = result.data.birthYear;
                                            $scope.marital_status = result.data.marital_status;
                                            $scope.retirementYear = $scope.birthYear
                                                    + $scope.defaultretirementAge;
                                            var tempUser = [];
                                            for (var i = 0; i < $scope.income.length; i++) {
                                                tempUser[i] = $scope.income[i].value;
                                            }
                                            maxY = Math.max.apply(Math,
                                                    tempUser) + 40000;
                                            maxY1 = Math.max.apply(Math,
                                                    tempUser) + 10000;
                                            tempData = result.data.userPlot;
                                            // tempDataHou =
                                            // result.data.housingPlot;
                                            // tempDataNonHou =
                                            // result.data.nonHousingPlot;

                                            var tempDatalength = tempData.length;
                                            if ($scope.retirementYear != Number(tempData[tempDatalength - 2].year)) {
                                                for (var i = 0; i < $scope.income.length; i++) {
                                                    if ($scope.retirementYear == Number($scope.income[i].year)) {
                                                        tempData[tempDatalength - 2].year = $scope.income[i].year;
                                                        tempData[tempDatalength - 2].userIncome = $scope.income[i].value;
                                                        break;
                                                    }
                                                }
                                            }
                                            for (var i = 0; i < tempData.length; i++) {
                                                var tempDataIndex = $scope.income
                                                        .map(function(obj) {
                                                            return obj.year;
                                                        }).indexOf(tempData[i].year);
                                                tempData[i].userIncome = $scope.income[tempDataIndex].value;
                                            }
                                            //tempData[tempDatalength - 1].year = $scope.income[$scope.income.length - 1].year;
                                            //tempData[tempDatalength - 1].userIncome = $scope.income[$scope.income.length - 1].value;
                                            for (var k = 0; k < tempDatalength; k++) {
                                                drag.push(tempData[k].year);
                                                tempYears
                                                        .push(tempData[k].year);
                                            }
                                            /*
                                             * for ( var k = 0; k <
                                             * tempDataHou.length; k++ ) {
                                             * dragHou .push(
                                             * tempDataHou[k].year );
                                             * tempYearsHou .push(
                                             * tempDataHou[k].year ); } for (
                                             * var k = 0; k <
                                             * tempDataNonHou.length; k++ ) {
                                             * dragNonHou .push(
                                             * tempDataNonHou[k].year );
                                             * tempYearsNonHou .push(
                                             * tempDataNonHou[k].year ); }
                                             */
                                            if ($scope.marital_status == "Yes") {
                                                $scope.spouseIncome1 = result.data.spouseIncome;
                                                $scope.combined_income = result.data.combined_income;
                                                $scope.spousebirthYear = result.data.spousebirthYear;
                                                $scope.spouseretirementYear = $scope.spousebirthYear
                                                        + $scope.spousedefaultretirementAge;
                                                var tempCombined = [];
                                                for (i = 0; i < $scope.combined_income.length; i++) {
                                                    tempCombined[i] = $scope.combined_income[i].value;
                                                }
                                                maxY = Math.max.apply(Math,
                                                        tempCombined) + 20000;
                                                maxY1 = Math.max.apply(Math,
                                                        tempCombined) + 5000;
                                                tempData1 = result.data.spousePlot;
                                                var tempData1length = tempData1.length;
                                                var diff = 0;
                                                for (k = 0; k < $scope.spouseIncome1.length; k++) {
                                                    if ($scope.spouseIncome1[k].retirement_income > 0) {
                                                        diff = $scope.spouseIncome1[k].year;
                                                        break;
                                                    }
                                                }
                                                if (Number(diff) != Number(tempData1[tempData1length - 2].year)) {
                                                    for (var i = 0; i < $scope.spouseIncome1.length; i++) {
                                                        if (diff == Number($scope.spouseIncome1[i].year)) {
                                                            tempData1[tempData1length - 2].year = $scope.spouseIncome1[i].year;
                                                            tempData1[tempData1length - 2].spouseIncome = $scope.spouseIncome1[i].value;
                                                            break;
                                                        }
                                                    }
                                                }
                                                for (var i = 0; i < tempData1.length; i++) {
                                                    var tempDataIndex = $scope.spouseIncome1
                                                            .map(function(obj) {
                                                                return obj.year;
                                                            }).indexOf(tempData1[i].year);
                                                    tempData1[i].spouseIncome = $scope.spouseIncome1[tempDataIndex].value;
                                                }
                                                //tempData1[tempData1length - 1].year = $scope.spouseIncome1[$scope.spouseIncome1.length - 1].year;
                                                //tempData1[tempData1length - 1].spouseIncome = $scope.spouseIncome1[$scope.spouseIncome1.length - 1].value;
                                                for (var k = 0; k < tempData1length; k++) {
                                                    drag1
                                                            .push(tempData1[k].year);
                                                    tempYears1
                                                            .push(tempData1[k].year);
                                                }
                                            }
                                            $max = 0;
                                            $scope.tax = result.data.tax;
                                            $scope.assets = result.data.assests;
                                            $scope.age = result.data.age;
                                            $scope.ageForRiskCal = result.data.age;
                                            $scope.ageUser = $scope.income[0].year
                                                    * 1 - $scope.birthYear * 1;
                                            for (var i = (($scope.age * 1) + (1 * 1)); i < 100; i++) {
                                                $scope.ages.push({
                                                    number : i
                                                });
                                            }

                                            $scope.startAge = 70;
                                            $scope.totalExpense = result.data.userExpense;
                                            $scope.expensesForGraph = result.data.userExpense;
                                            $scope.taxForGraphIncome = result.data.tax;

                                            $k = 0;

                                            $scope.spouseIncome = [];
                                            var len = $scope.income.length;
                                            $scope.incomeStartYear = $scope.income[0].year;
                                            $scope.incomeEndYear = $scope.income[len - 1].year;
                                            data = [];

                                            for (i = 0; i < $scope.income.length; i++) {
                                                $scope.modifiedchartIncome[i] = {
                                                    "year" : $scope.income[i].year,
                                                    "userIncome" : $scope.income[i].value
                                                };
                                            }
                                            $scope.modifiedchartIncomeBackup = $scope.modifiedchartIncome;

                                            for (var i = 0; i < $scope.income.length; i++) {
                                                if (result.data.spouseIncome == undefined) {
                                                    data
                                                            .push({
                                                                "userIncome" : $scope.income[i].value,
                                                                "year" : $scope.income[i].year,
                                                                "totalExpense" : $scope.totalExpense[i].totalExpense

                                                            });

                                                } else {

                                                    if ($scope.spouseIncome1.length <= i) {

                                                        data
                                                                .push({
                                                                    "userIncome" : $scope.income[i].value,
                                                                    "year" : $scope.income[i].year,
                                                                    "totalExpense" : $scope.totalExpense[i].totalExpense,
                                                                    "spouseIncome" : 0,
                                                                    "combinedIncome" : $scope.combined_income[i].value
                                                                });
                                                        $scope.modifiedchartIncomeSpouse
                                                                .push({
                                                                    "year" : $scope.income[i].year,
                                                                    "spouseIncome" : 0
                                                                });
                                                    } else {

                                                        data
                                                                .push({
                                                                    "userIncome" : $scope.income[i].value,
                                                                    "year" : $scope.income[i].year,
                                                                    "totalExpense" : $scope.totalExpense[i].totalExpense,
                                                                    "spouseIncome" : $scope.spouseIncome1[i].value,
                                                                    "combinedIncome" : $scope.combined_income[i].value
                                                                });
                                                        $scope.modifiedchartIncomeSpouse
                                                                .push({
                                                                    "year" : $scope.income[i].year,
                                                                    "spouseIncome" : $scope.spouseIncome1[i].value
                                                                });
                                                    }
                                                    $scope.modifiedchartIncomeSpouseBackup = $scope.modifiedchartIncomeSpouse;
                                                }
                                                $scope.chartYearExpense
                                                        .push({
                                                            label : $scope.income[i].year
                                                        });

                                                $scope.chartYearSave
                                                        .push({
                                                            label : $scope.income[i].year
                                                                    .toString()
                                                        });
                                                $scope.chartIncomeSave
                                                        .push({
                                                            "userIncome" : $scope.income[i].value,
                                                            "year" : $scope.income[i].year
                                                        });

                                                $scope.ExpenseIncome
                                                        .push({
                                                            value : $scope.totalExpense[i].totalExpense,
                                                            year : $scope.totalExpense[i].year
                                                        });
                                                // {
                                                if (result.data.spouseIncome == undefined) {
                                                    $scope.spouseIncome
                                                            .push({
                                                                value : 0,
                                                                year : $scope.income[i].year
                                                            });
                                                    $scope.chartCombinedIncome
                                                            .push({
                                                                value : 0,
                                                                "allowDrag" : "0"
                                                            });
                                                } else {

                                                    if ($scope.spouseIncome1.length <= i) {
                                                        $scope.spouseIncome
                                                                .push({
                                                                    value : 0,
                                                                    year : $scope.income[i].year
                                                                });
                                                    } else {
                                                        $scope.spouseIncome
                                                                .push({
                                                                    value : $scope.spouseIncome1[i].value,
                                                                    year : $scope.spouseIncome1[i].year
                                                                });
                                                    }
                                                    $scope.chartCombinedIncome
                                                            .push({
                                                                value : $scope.combined_income[i].value,
                                                                "allowDrag" : "0"
                                                            });

                                                }
                                                $scope.spouseIncomeSave
                                                        .push({
                                                            value : $scope.spouseIncome[i].value
                                                        });
                                                if ($scope.age <= 99) {

                                                    if ($scope.income[i].year == $scope.tax[i].year
                                                            && $scope.income[i].year == $scope.assets[i].year
                                                            && $scope.income[i].year == $scope.totalExpense[i].year) {

                                                        $scope.tableIncome
                                                                .push({
                                                                    id : $scope.age,
                                                                    value : $scope.income[i].value,
                                                                    year : $scope.income[i].year
                                                                            .toString(),
                                                                    expense : $scope.totalExpense[i].totalExpense,
                                                                    spouseValue : $scope.spouseIncome[i].value,
                                                                    federalTax : $scope.tax[i].federalTax,
                                                                    userfICASocialSecurityTax : $scope.tax[i].userSSTax,
                                                                    spousefICASocialSecurityTax : $scope.tax[i].spouseSSTax,
                                                                    stateTax : $scope.tax[i].stateTax,
                                                                    fICAMedicareTax : $scope.tax[i].fICAMedicareTax,
                                                                    savings : $scope.assets[i].savings,
                                                                    taxable_investment_amount : $scope.assets[i].taxable_investment_amount,
                                                                    nontaxable_investment_amount : $scope.assets[i].nontaxable_investment_amount
                                                                });
                                                        $scope.tableIncome_imp
                                                                .push({
                                                                    id : $scope.age,
                                                                    value : $scope.income[i].value,
                                                                    year : $scope.income[i].year
                                                                            .toString(),
                                                                    expense : $scope.totalExpense[i].totalExpense,
                                                                    spouseValue : $scope.spouseIncome[i].value,
                                                                    federalTax : $scope.tax[i].federalTax,
                                                                    userfICASocialSecurityTax : $scope.tax[i].userSSTax,
                                                                    spousefICASocialSecurityTax : $scope.tax[i].spouseSSTax,
                                                                    stateTax : $scope.tax[i].stateTax,
                                                                    fICAMedicareTax : $scope.tax[i].fICAMedicareTax,
                                                                    savings : $scope.assets[i].savings,
                                                                    taxable_investment_amount : $scope.assets[i].taxable_investment_amount,
                                                                    nontaxable_investment_amount : $scope.assets[i].nontaxable_investment_amount
                                                                });
                                                        $scope.sum = $scope.assets[i].savings
                                                                * 1
                                                                + $scope.assets[i].taxable_investment_amount
                                                                * 1
                                                                + $scope.assets[i].nontaxable_investment_amount
                                                                * 1;
                                                        $scope.chartAssets
                                                                .push({
                                                                    value : $scope.sum,
                                                                    "allowDrag" : "0"
                                                                });
                                                    }
                                                }
                                                // alert( "$scope.age--->>
                                                // "+$scope.age );
                                                // }
                                                $scope.age++;
                                            }
                                            // console.log( $scope.tableIncome
                                            // );

                                            // console.log( tempData1 );
                                            //console.log(data);
                                            var indexYearUser = data
                                                    .map(function(obj) {
                                                        return obj.year;
                                                    })
                                                    .indexOf(
                                                            tempYears[tempYears.length - 2] - 1);
                                            $scope.dataUserIncome = data[indexYearUser].userIncome;
                                            if ($scope.marital_status == "Yes") {
                                                var indexYearSpouse = data
                                                        .map(function(obj) {
                                                            return obj.year;
                                                        })
                                                        .indexOf(
                                                                tempYears1[tempYears1.length - 2] - 1);
                                                $scope.dataSpouseIncome = data[indexYearSpouse].spouseIncome;
                                            }

                                            // alert( $scope.dataUserIncome );
                                            // $scope.drawD3Chart();
                                            if ($scope.Count == 0) {
                                                $scope.finPlanDetails = false;
                                                $scope.incomeProfileDetails = true;
                                                $scope.incomeProfilesChart = "constant_income";
                                                $scope.planCountOncopy = false;
                                                $scope.planCountOnload = false;
                                                $scope.notNewUser = false;
                                                $scope.plansExist = false;
                                                $scope.checkboxData.applyPlan = false;
                                                $scope.messagehome = "We extend your income and expense to when you are seventy year old, you can drag the graph to change the future incomes and expenses as you like.";
                                                if ($scope.profileNameCheckbox != ""
                                                        && $scope.profileNameCheckbox != undefined) {
                                                    $scope.incomeProfilesChart = $scope.profileNameCheckbox;
                                                    $scope
                                                            .changeIncomeProfile($scope.profileNameCheckbox);
                                                } else {
                                                    $scope
                                                            .changeIncomeProfile($scope.incomeProfilesChart);
                                                }
                                            } else {
                                                $scope.finPlanDetails = true;
                                                $scope.incomeProfileDetails = false;
                                                $scope.checkboxData.applyPlan = true;
                                                $scope.plansExist = true;
                                                $scope.planCountOncopy = true;
                                                $scope.planCountOnload = true;
                                                $scope.notNewUser = true;
                                                $scope.messagehome = "We extend your income and expense to when you are seventy year old, you can drag the graph to change the future incomes and expenses as you like.";
                                                for (var i = 0; i < $scope.Count; i++) {

                                                    $scope.planNames1
                                                            .push({
                                                                name : $scope.planNames[i],
                                                                profileName : result.data.incomeProfile[i]
                                                            });
                                                    if ($scope.fin_name != undefined) {

                                                        if ($scope.fin_name == $scope.planNames1[i].name) {
                                                            $scope.position = i;
                                                        }

                                                    }
                                                }
                                                $scope.firstPlan = ($scope.planNames1[0].name);
                                                $scope.firstProfile = ($scope.planNames1[0].profileName);
                                                $scope.finplan_name = $scope.firstPlan;
                                                if ($scope.fin_name == "undefined") {
                                                    if ($scope.profileNameCheckbox != ""
                                                            && $scope.profileNameCheckbox != undefined) {
                                                        $scope.incomeProfilesChart = $scope.profileNameCheckbox;
                                                        $scope
                                                                .changeIncomeProfile($scope.profileNameCheckbox);
                                                    }
                                                    $scope
                                                            .changeIncomeProfile($scope.firstProfile);
                                                    $scope
                                                            .fetchPlanDetails($scope.firstPlan);
                                                    $scope.incomeProfilesChart = $scope.firstProfile;
                                                } else {
                                                    if ($scope.profileNameCheckbox != ""
                                                            && $scope.profileNameCheckbox != undefined) {
                                                        $scope.incomeProfilesChart = $scope.profileNameCheckbox;
                                                        $scope
                                                                .changeIncomeProfile($scope.profileNameCheckbox);
                                                    }
                                                    $scope.planchange(
                                                            $scope.fin_name,
                                                            $scope.position);
                                                }
                                            }
                                            $scope.plotGraph($scope.startAge);
                                            $scope
                                                    .plotExpense($scope.ExpenseIncome);
                                            $scope
                                                    .plotAssetGoals($scope.assetGoals);
                                            if (result.data.marital_status == "Yes") {
                                                $scope.nospouse = true;
                                            }
                                            $scope
                                                    .plotGraphSpouse($scope.startAge);
                                            // $scope.makeRetirement();
                                            // plotAssetChart();
                                            // editAssetChart();

                                            $scope.loading = false;
                                            $scope.masked = false;
                                        }, function(error) {
                                        });
                    };
                    
                    $scope.saveShowPopUp = function()
                    {
                    	$scope.messageChangeExpense = "";
                    	$scope.showEditValues = false;
                    	$scope.checkboxData.housingExpense = "";
                    	$scope.checkboxData.nonHousingExpense = "";
                    	$scope.checkboxData.editExp = "";
                    	if($scope.editExpenseFlag == 0) {
                            $("#chartModelEdit").modal(
                            "show");
                            $("#chartModel").modal(
                            "hide");
                    	} else {
                    		$("#chartModelEdit").modal(
                            "hide");
                            $("#chartModel").modal(
                            "show");
                    	}
                    };
                    
                    $scope.showEditExpense = function(input) {
                    	if(input == "yes") {
                    		$scope.showEditValues = true;
                    		$scope.messageChangeExpense = "";
                    	} else {
                    		$scope.showEditValues = false;
                    		$scope.messageChangeExpense = "";
                    	}
                    }
                    
                    $scope.clearMessageExpenses = function() {
                    	$scope.messageChangeExpense = "";
                    	$scope.messageUpdateExpense = "";
                    }
                    
                    $scope.clearExpValues = function() {
                    	$scope.checkboxData.housingExpense = "";
                    	$scope.checkboxData.nonHousingExpense = "";
                    }
                    
                    $scope.proceedUpdateExpenses = function() {
                    	if($scope.checkboxData.housingExpense == null || $scope.checkboxData.housingExpense == "") {
                			$scope.messageUpdateExpense = "Please enter housing expense";
                		} else if($scope.checkboxData.nonHousingExpense == null || $scope.checkboxData.nonHousingExpense == "") {
                			$scope.messageUpdateExpense = "Please enter non housing expense";
                		} else {
                			 $scope.masked = true;
                			$scope.ExpenseDetails = {};
                            $scope.ExpenseDetails.nonHousingExpense = $scope.checkboxData.nonHousingExpense;
                            $scope.ExpenseDetails.housingExpense = $scope.checkboxData.housingExpense;
                            $scope.ExpenseDetails.profile_name = $scope.incomeProfilesChart;
                            $scope.ExpenseDetails.form = "saveExpenses";
                            
                            $http(
                                    {
                                        method : "POST",
                                        url : "ModifyIncome",
                                        data : $.param($scope.ExpenseDetails),
                                        headers : {
                                            "Content-Type" : "application/x-www-form-urlencoded"
                                        }
                                    })
                                    .then(
                                            function(result) {
                                                $scope.message = result.data.status;
                                                $("#chartModelUpdate").modal(
                                                "hide");
                                                if ($scope.message == "success") {
                                                	$scope.messageUpdateExpense = "";
                                                	$scope.editExpenseFlag = result.data.editExpenseFlag;
                                                	$scope.messageUpdateExpenseAlert = "Expense Saved Successfully!!";
                                                	$("#success-alertExp").show();
                                                    $("#success-alertExp").fadeTo(
                                                            5000, 300).slideUp(
                                                            300,
                                                            function() {
                                                                $("#success-alertExp")
                                                                        .hide();
                                                            });

                                                } else {
                                                	
                                                	console.log("Failed");
                                                }
                                                console.log("message"
                                                        + $scope.message);
                                                $scope.masked = false;
                                            },
                                            function(error) {
                                                $scope.message = result.data.status;
                                            });
                		}
                    }
                    
                    
                    $scope.proceedEditExpenses = function() {
                    	if($scope.checkboxData.editExp == "yes") {
                    		if($scope.checkboxData.housingExpense == null || $scope.checkboxData.housingExpense == "") {
                    			$scope.messageChangeExpense = "Please enter housing expense";
                    		} else if($scope.checkboxData.nonHousingExpense == null || $scope.checkboxData.nonHousingExpense == "") {
                    			$scope.messageChangeExpense = "Please enter non housing expense";
                    		} else {
                    			 $scope.masked = true;
                    			$scope.ExpenseDetails = {};
                                $scope.ExpenseDetails.nonHousingExpense = $scope.checkboxData.nonHousingExpense;
                                $scope.ExpenseDetails.housingExpense = $scope.checkboxData.housingExpense;
                                $scope.ExpenseDetails.profile_name = $scope.incomeProfilesChart;
                                $scope.ExpenseDetails.form = "saveExpenses";
                                
                                $http(
                                        {
                                            method : "POST",
                                            url : "ModifyIncome",
                                            data : $.param($scope.ExpenseDetails),
                                            headers : {
                                                "Content-Type" : "application/x-www-form-urlencoded"
                                            }
                                        })
                                        .then(
                                                function(result) {
                                                    $scope.message = result.data.status;

                                                    if ($scope.message == "success") {
                                                    	$scope.messageChangeExpense = "Saved Successfully!!";
                                            			$("#chartModelEdit").modal(
                                                        "hide");
                                                        $("#chartModel").modal(
                                                        "show");
                                                    } else {
                                                    	$scope.messageChangeExpense = "Unable to save";
                                                    }
                                                    console.log("message"
                                                            + $scope.message);
                                                    $scope.masked = false;
                                                },
                                                function(error) {
                                                    $scope.message = result.data.status;
                                                });
                    		}
                    	}  else if ($scope.checkboxData.editExp == "no") {
                    		$scope.checkboxData.housingExpense = "";
                        	$scope.checkboxData.nonHousingExpense = "";
                    		$("#chartModelEdit").modal(
                            "hide");
                            $("#chartModel").modal(
                            "show");
                    	} else {
                    		$scope.messageChangeExpense = "Please choose Yes/No"
                    	}
                    }
                    
                    $scope.drawD3Chart = function() {
                        document.addEventListener("click", function() {
                            return false;
                        });
                        var parseDate = d3.time.format("%y").parse;
                        var userIncomeGraph = false;
                        var spouseIncomeGraph = false;
                        var userClicked = false;
                        var spouseClicked = false;;
                        var combinedIncomeGraph = false;
                        var expenseIncomeGraph = false;
                        var taxGraph = false;

                        var self = null;
                        var self1 = null;

                        var years = [];
                        var div;
                        console.log($scope.tableIncome);
                        console.log($scope.taxForGraphIncome);
                        //alert($scope.Count);
                        var totalUserIncomeCalculated = 0;
                        if($scope.Count != 0){
                        	for(var i=0;i<data.length;i++) {
                        		totalUserIncomeCalculated = data[i].userIncome + totalUserIncomeCalculated;
                            	var taxTempCal = $scope.taxForGraphFin[i].fICAMedicareTax +
                                $scope.taxForGraphFin[i].fICASocialSecurityTax +
                                $scope.taxForGraphFin[i].federalTax +
                                $scope.taxForGraphFin[i].spouseSSTax +
                                $scope.taxForGraphFin[i].stateTax +
                                $scope.taxForGraphFin[i].userSSTax;
                            	data[i].tax = Math.round(taxTempCal).toFixed(2);
                            	data[i].totalExpense = $scope.tableIncome[i].expense;
                            }
                        } else {
                        	for(var i=0;i<data.length;i++) {
                        		totalUserIncomeCalculated = data[i].userIncome + totalUserIncomeCalculated;
                            	var taxTempCal = $scope.taxForGraphIncome[i].fICAMedicareTax +
                                $scope.taxForGraphIncome[i].fICASocialSecurityTax +
                                $scope.taxForGraphIncome[i].federalTax +
                                $scope.taxForGraphIncome[i].spouseSSTax +
                                $scope.taxForGraphIncome[i].stateTax +
                                $scope.taxForGraphIncome[i].userSSTax;
                            	data[i].tax = Math.round(taxTempCal).toFixed(2);
                            	
                            }
                        }
                        

                        $scope.mousemoveFlag = true;
                        SimpleGraph = function(elemid, options) {

                            data.forEach(function(d) {
                                years.push(d.year);
                                d.year = d.year;
                                d.userIncome = +d.userIncome;
                                d.totalExpense = +d.totalExpense;
                                d.spouseIncome = +d.spouseIncome;
                                d.combinedIncome = +d.combinedIncome;
                                d.tax = +d.tax;
                            });
                            div = d3.select("body").append("div").attr("class",
                                    "nvtooltip xy-tooltip").style("opacity", 1).style("position", "absolute").style("transform", "translate(-170px)");

                            self = this;
                            self1 = this;
                            this.chart = document.getElementById(elemid);
                            if (this.chart != null) {
                                this.cx = this.chart.clientWidth;
                                this.cy = this.chart.clientHeight;
                            }

                            this.options = options || {};
                            this.options.xmax = options.xmax || 30;
                            this.options.xmin = options.xmin || 0;
                            this.options.ymax = options.ymax || 10;
                            this.options.ymin = options.ymin || 0;
                            this.padding = {
                                "top" : this.options.title ? 40 : 20,
                                "right" : 30,
                                "bottom" : this.options.xlabel ? 60 : 10,
                                "left" : this.options.ylabel ? 70 : 45
                            };

                            this.size = {
                                "width" : 950,
                                "height" : 450
                            };

                            this.x = d3.scale.linear().domain(
                                    [ this.options.xmin, this.options.xmax ])
                                    .range([ 0, this.size.width ]);

                            this.downx = Math.NaN;

                            this.y = d3.scale.linear().domain(
                                    [ this.options.ymax, this.options.ymin ])
                                    .nice().range([ 0, this.size.height ])
                                    .nice();

                            this.downy = Math.NaN;

                            this.dragged = this.selected = null;
                            this.dragged1 = this.selected1 = null;

                            this.line = d3.svg.line().x(function(d, i) {
                                return this.x(this.points[i].year);
                            }).y(function(d, i) {
                                return this.y(this.points[i].userIncome);
                            });

                            this.line1 = d3.svg.line().x(function(d, i) {
                                return this.x(this.points[i].year);
                            }).y(function(d, i) {
                                return this.y(this.points[i].totalExpense);
                            });

                            this.line2 = d3.svg.line().x(function(d, i) {
                                return this.x(this.points[i].year);
                            }).y(function(d, i) {
                                return this.y(this.points[i].spouseIncome);
                            });

                            this.line3 = d3.svg.line().x(function(d, i) {
                                return this.x(this.points[i].year);
                            }).y(function(d, i) {
                                return this.y(this.points[i].combinedIncome);
                            });
                            
                            this.line4 = d3.svg.line().x(function(d, i) {
                                return
                                this.x(this.points[i].year);
                            }).y(function(d, i) {
                                return this.y(this.points[i].tax);
                            });
                            
                            this.dynamiclineUser = d3.svg.line().x(
                                    function(d, i) {
                                        return this.x(tempData[i].year);
                                    }).y(function(d, i) {
                                return this.y(tempData[i].userIncome);
                            });

                            this.dynamiclineSpouse = d3.svg.line().x(
                                    function(d, i) {
                                        return this.x(tempData1[i].year);
                                    }).y(function(d, i) {
                                return this.y(tempData1[i].spouseIncome);
                            });

                            var xrange = (this.options.xmax - this.options.xmin), yrange2 = (this.options.ymax - this.options.ymin) / 2, yrange4 = yrange2 / 2, datacount = this.size.width / 30;

                            this.points = data;

                            $("body")
                                    .on(
                                            "click",
                                            function(e) {
                                                if (e.toElement.id == "incomeExpense") {
                                                    userIncomeGraph = true;
                                                    spouseIncomeGraph = true;
                                                    combinedIncomeGraph = true;
                                                    expenseIncomeGraph = true;
                                                    taxGraph = true;
                                                    userClicked = false;
                                                    spouseClicked = false;
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#userIncomeSVG")
                                                            .remove();
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#combinedIncomeSVG")
                                                            .remove();
                                                    self.vis.selectAll(
                                                            "svg#expenseSVG")
                                                            .remove();
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#spouseIncomeSVG")
                                                            .remove();
                                                    //self.vis.selectAll("svg#taxSVG").remove();
                                                    self.plotUser();
                                                    self.plotSpouse();
                                                    self.plotExpense();
                                                    self.plotCombined();
                                                    //self.plotTax();
                                                    self.plotPointsUser();
                                                    if ( totalUserIncomeCalculated < 1) {
                                                    	self.vis.selectAll("svg#expenseSVG").remove();
                                                    	self.vis.selectAll("svg#combinedIncomeSVG").remove();
                                                    }
                                                    return false;
                                                }
                                            });

                            this.vis = d3
                                    .select(this.chart)
                                    .append("svg")
                                    .attr("id", "incomeExpense")
                                    .attr("width", 1020)
                                    .attr("height", 600)
                                    .append("g")
                                    .attr("class", "")
                                    .attr(
                                            "transform",
                                            "translate( " + this.padding.left
                                                    + "," + this.padding.top
                                                    + " )")
                                    .on(
                                            "mousemove",
                                            function() {
                                                if (d3.event.target.nodeName == "rect"
                                                        && $scope.mousemoveFlag) {
                                                    var beforeRoundIncome = Number(self.y
                                                            .invert(
                                                                    Math
                                                                            .max(
                                                                                    0,
                                                                                    Math
                                                                                            .min(
                                                                                                    self.size.width,
                                                                                                    d3
                                                                                                            .mouse(self.vis
                                                                                                                    .node())[1])))
                                                            .toFixed(0));
                                                    var p1 = {
                                                        "year" : Number(self.x
                                                                .invert(
                                                                        Math
                                                                                .max(
                                                                                        0,
                                                                                        Math
                                                                                                .min(
                                                                                                        self.size.width,
                                                                                                        d3
                                                                                                                .mouse(self.vis
                                                                                                                        .node())[0])))
                                                                .toFixed(0)),
                                                        "income" : Math
                                                                .ceil(beforeRoundIncome / 500) * 500
                                                    };

                                                    div.transition()
                                                    
                                                    .style("opacity", .9)
                                                            .style("display",
                                                                    "visible");
                                                    var myPoint = data.map(function(obj) {
                                                		return obj.year;
                                                    }).indexOf(p1.year);
                                                    
                                                    if(myPoint > -1 ) {
                                                    	if ($scope.marital_status == "Yes") {
                                                        	div.html(
                                                        	"<table><thead><tr><td colspan=3><strong class=x-value>" + p1.year + "</strong></td></tr></thead><tbody>" +
                                                			"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                			"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                			"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                			"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                			"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                            .style("left",(d3.event.pageX) + "px")
                                                            .style("top",(d3.event.pageY - 28)+ "px");
                                                        } else {
                                                        	div.html(
                                                        	"<table><thead><tr><td colspan=3><strong class=x-value>" + p1.year + "</strong></td></tr></thead><tbody>" +
                                                			"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                			"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                			"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr></tbody></table>")
                                                            .style("left",(d3.event.pageX) + "px")
                                                            .style("top",(d3.event.pageY - 28)+ "px");
                                                        }
                                                    }     
                                                }
                                            }).on("mouseout", function(d) {
                                        div.transition()

                                        .style("opacity", 0);
                                    });

                            this.plot = this.vis.append("rect").attr("width",
                                    this.size.width).attr("height",
                                    this.size.height).style("fill", "#FFF")/*.on(
                                    "click", self.click)*/.attr("pointer-events",
                                    "all");


                            var margin = {
                                top : 0,
                                right : 40,
                                bottom : 70,
                                left : 120
                            }, width = 600 - margin.left - margin.right, height = 300
                                    - margin.top - margin.bottom;

                            this.vis
                                    .append("text")
                                    .attr("x", this.size.width / 2 - 125)
                                    .attr("y", height + 300)
                                    .attr("class", "legend")
                                    .style("fill", "black")
                                    .on(
                                            "click",
                                            function() {
                                                userIncomeGraph = true;
                                                spouseIncomeGraph = true;
                                                combinedIncomeGraph = true;
                                                expenseIncomeGraph = true;
                                                taxGraph = true;
                                                userClicked = false;
                                                spouseClicked = false;
                                                self.vis.selectAll(
                                                        "svg#userIncomeSVG")
                                                        .remove();
                                                self.vis
                                                        .selectAll(
                                                                "svg#combinedIncomeSVG")
                                                        .remove();
                                                self.vis.selectAll(
                                                        "svg#expenseSVG")
                                                        .remove();
                                                self.vis.selectAll(
                                                        "svg#spouseIncomeSVG")
                                                        .remove();
                                                //self.vis.selectAll("svg#taxSVG").remove();
                                                self.plotUser();
                                                self.plotSpouse();
                                                self.plotExpense();
                                                self.plotCombined();
                                                //self.plotTax();
                                                self.plotPointsUser();
                                                if ( totalUserIncomeCalculated < 1) {
                                                	self.vis.selectAll("svg#expenseSVG").remove();
                                                	self.vis.selectAll("svg#combinedIncomeSVG").remove();
                                                }
                                            }).text("All");

                            /* Click event for userIncome */
                            this.vis
                                    .append("text")
                                    .attr("x", this.size.width / 2 - 100)
                                    .attr("y", height + 300)
                                    .attr("class", "legend")
                                    .style("fill", "#328de4")
                                    .on(
                                            "click",
                                            function() {
                                                userIncomeGraph = true;
                                                spouseIncomeGraph = false;
                                                combinedIncomeGraph = false;
                                                expenseIncomeGraph = false;
                                                userClicked = false;
                                                spouseClicked = false;
                                                taxGraph = false;
                                                self.vis.selectAll(
                                                        "svg#userIncomeSVG")
                                                        .remove();
                                                self.vis
                                                        .selectAll(
                                                                "svg#combinedIncomeSVG")
                                                        .remove();
                                                self.vis.selectAll(
                                                        "svg#expenseSVG")
                                                        .remove();
                                                self.vis.selectAll(
                                                        "svg#spouseIncomeSVG")
                                                        .remove();
                                                //self.vis.selectAll("svg#taxSVG").remove();
                                                self.plotUser();
                                                self.plotPointsUser();
                                            }).text("User Income");

                            /* Click event for Expense */
                            this.vis
                                    .append("text")
                                    .attr("x", this.size.width / 2 - 25)
                                    .attr("y", height + 300)
                                    .attr("class", "legend")
                                    .style("fill", "#e43234")
                                    .on(
                                            "click",
                                            function() {
                                                userIncomeGraph = false;
                                                spouseIncomeGraph = false;
                                                combinedIncomeGraph = false;
                                                expenseIncomeGraph = true;
                                                taxGraph = false;
                                                userClicked = false;
                                                spouseClicked = false;
                                                self.vis.selectAll(
                                                        "svg#userIncomeSVG")
                                                        .remove();
                                                self.vis
                                                        .selectAll(
                                                                "svg#combinedIncomeSVG")
                                                        .remove();
                                                self.vis.selectAll(
                                                        "svg#expenseSVG")
                                                        .remove();
                                                self.vis.selectAll(
                                                        "svg#spouseIncomeSVG")
                                                        .remove();
                                                //self.vis.selectAll("svg#taxSVG").remove();
                                                self.plotExpense();
                                                self.plotPointsExpense();
                                            }).text("Expense");

                            if ($scope.marital_status == "Yes") {
                                /* Click event for spouseIncome */
                                self.vis
                                        .append("text")
                                        .attr("x", this.size.width / 2 + 25)
                                        .attr("y", height + 300)
                                        .attr("class", "legend")
                                        .style("fill", "#32e2e2")
                                        .on(
                                                "click",
                                                function() {
                                                    userIncomeGraph = false;
                                                    spouseIncomeGraph = true;
                                                    combinedIncomeGraph = false;
                                                    expenseIncomeGraph = false;
                                                    taxGraph = false;
                                                    userClicked = false;
                                                    spouseClicked = false;
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#userIncomeSVG")
                                                            .remove();
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#combinedIncomeSVG")
                                                            .remove();
                                                    self.vis.selectAll(
                                                            "svg#expenseSVG")
                                                            .remove();
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#spouseIncomeSVG")
                                                            .remove();
                                                    //self.vis.selectAll("svg#taxSVG").remove();
                                                    self.plotSpouse();
                                                    self.plotPointsSpouse();
                                                }).text("Spouse Income");

                                /* Click event for combinedIncome */
                                self.vis
                                        .append("text")
                                        .attr("x", this.size.width / 2 + 110)
                                        .attr("y", height + 300)
                                        .attr("class", "legend")
                                        .style("fill", "#b0c10c")
                                        .on(
                                                "click",
                                                function() {
                                                    userIncomeGraph = false;
                                                    spouseIncomeGraph = false;
                                                    combinedIncomeGraph = true;
                                                    expenseIncomeGraph = false;
                                                    taxGraph = false;
                                                    userClicked = false;
                                                    spouseClicked = false;
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#userIncomeSVG")
                                                            .remove();
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#combinedIncomeSVG")
                                                            .remove();
                                                    self.vis.selectAll(
                                                            "svg#expenseSVG")
                                                            .remove();
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#spouseIncomeSVG")
                                                            .remove();
                                                    //self.vis.selectAll("svg#taxSVG").remove();
                                                    self.plotCombined();
                                                    self.plotPointsCombined();
                                                }).text("Combined Income");
                            }

                            // add Chart Title
                            if (this.options.title) {
                                this.vis.append("text").attr("class", "axis")
                                        .text(this.options.title).attr("x",
                                                this.size.width / 2).attr("dy",
                                                "-0.8em").style("text-anchor",
                                                "middle").style("color",
                                                "black");
                            }

                            // Add the x-axis label
                            if (this.options.xlabel) {
                                this.vis.append("text").attr("class", "axis")
                                        .text(this.options.xlabel).attr("x",
                                                this.size.width / 2).attr("y",
                                                this.size.height + 20).attr(
                                                "dy", "2.4em").style(
                                                "text-anchor", "middle");
                            }

                            // add y-axis label
                            if (this.options.ylabel) {
                                this.vis.append("g").append("text").attr(
                                        "class", "axis").text(
                                        this.options.ylabel).style(
                                        "text-anchor", "middle").attr(
                                        "transform",
                                        "translate( " + -60 + " "
                                                + this.size.height / 2
                                                + " ) rotate( -90 )");
                            }

                            d3.select(this.chart).on("mousemove.drag",
                                    self.mousemove()).on("touchmove.drag",
                                    self.mousemove()).on("mouseup.drag",
                                    self.mouseup()).on("touchend.drag",
                                    self.mouseup());

                            
                            
                            if ($scope.marital_status == "Yes") {
                                self.plotSpouse();
                                self.plotCombined();
                            }
                            self.plotExpense();
                            self.plotUser();
                            this.plotPointsUser();
                            userIncomeGraph = true;
                            this.redraw()();
                        };

                        SimpleGraph.prototype.click = function(line) {
                            if (line == "user") {
                            	userIncomeGraph = true;
                                spouseIncomeGraph = false;
                                
                                if (d3.event.defaultPrevented)
                                    return;
                                self.vis.selectAll("svg#combinedIncomeSVG")
                                        .remove();
                                self.vis.selectAll("svg#expenseSVG").remove();
                                self.vis.selectAll("svg#spouseIncomeSVG")
                                        .remove();
                                self.vis.selectAll("svg#taxSVG").remove();
                                var beforeRoundIncome = Number(self.y.invert(
                                        Math.max(0, Math.min(self.size.width,
                                                d3.mouse(self.vis.node())[1])))
                                        .toFixed(0));
                                var p1 = {
                                    "year" : Number(self.x
                                            .invert(
                                                    Math
                                                            .max(
                                                                    0,
                                                                    Math
                                                                            .min(
                                                                                    self.size.width,
                                                                                    d3
                                                                                            .mouse(self.vis
                                                                                                    .node())[0])))
                                            .toFixed(0)),
                                    "userIncome" : Math
                                            .ceil(beforeRoundIncome / 500) * 500
                                };

                                var tempYearsLength = tempYears.length;
                                if (p1.year < tempYears[tempYearsLength - 2]
                                        && p1.year >= tempYears[0]) {
                                    var tempYearsIndex = tempYears
                                            .indexOf(p1.year);
                                    if (tempYearsIndex > -1) {
                                        tempData.splice(tempYearsIndex, 1);
                                        tempData.push(p1);
                                        tempData.sort(function(a, b) {
                                            return a.year - b.year;
                                        });
                                    } else {
                                        tempYears.push(p1.year);
                                        tempData.push(p1);
                                        tempData.sort(function(a, b) {
                                            return a.year - b.year;
                                        });
                                        tempYears.sort(function(a, b) {
                                            return a - b;
                                        });
                                    }

                                    self.vis.selectAll("svg#userIncomeSVG")
                                            .remove();

                                    self.vis.append("svg").attr("top", 0).data(
                                            tempData).attr("cx", function(d) {
                                        return d.year;
                                    }).attr("cy", function(d) {
                                        return d.userIncome;
                                    }).attr("left", 0).attr("width",
                                            self.size.width).attr("height",
                                            self.size.height).attr(
                                            "viewBox",
                                            "0 0 " + self.size.width + " "
                                                    + self.size.height).attr(
                                            "id", "userIncomeSVG").append(
                                            "path").attr("class", "line").attr(
                                            "id", "userIncome").attr("d",
                                            self.dynamiclineUser(tempData))
                                            .style("cursor", "pointer").on(
                                                    "mouseover",
                                                    function(d) {
                                                        div.transition().duration(200)
                                                                .style("opacity", .9)
                                                                .style("display",
                                                                        "visible");
                                                        var p2 = {
                                                                "year" : Number(self.x
                                                                        .invert(
                                                                                Math
                                                                                        .max(
                                                                                                0,
                                                                                                Math
                                                                                                        .min(
                                                                                                                self.size.width,
                                                                                                                d3
                                                                                                                        .mouse(self.vis
                                                                                                                                .node())[0])))
                                                                        .toFixed(0)),
                                                                "userIncome" : Number(self.y.invert(
                                                                        Math.max(0, Math.min(self.size.width,
                                                                                d3.mouse(self.vis.node())[1])))
                                                                        .toFixed(0))
                                                            };
                                                        var myPoint = data.map(function(obj) {
                                                    		return obj.year;
                                                        }).indexOf(p2.year);
                                                        
                                                        if(myPoint > -1 ) {
                                                        	if ($scope.marital_status == "Yes") {
                                                            	div.html(
                                                                    	"<table><thead><tr><td colspan=3><strong class=x-value>" + p2.year + "</strong></td></tr></thead><tbody>" +
                                                                    	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                            			"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                            			"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                            			"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                            			"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                                .style("left",(d3.event.pageX) + "px")
                                                                .style("top",(d3.event.pageY - 28)+ "px");
                                                            } else {
                                                            	div.html(
                                                            	"<table><thead><tr><td colspan=3><strong class=x-value>" + p2.year + "</strong></td></tr></thead><tbody>" +
                                                            	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                    			"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                    			"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr></tbody></table>")
                                                                .style("left",(d3.event.pageX) + "px")
                                                                .style("top",(d3.event.pageY - 28)+ "px");
                                                            }
                                                        }

                                                    }).on(
                                                    "mouseout",
                                                    function(d) {
                                                        div.transition().duration(200)
                                                                .style("opacity", 0);
                                                    }).on(
                                                    "mousedown", function() { userClicked = true; spouseClicked = false;
                                                        self.click("user");
                                                    });

                                    self.vis.select("path").attr("d",
                                            self.dynamiclineUser(tempData));

                                    var circle = self.vis.select(
                                            "svg#userIncomeSVG").selectAll(
                                            "circle").data(tempData,
                                            function(d) {
                                                return d;
                                            });
                                    circle
                                            .enter()
                                            .append("circle")
                                            .attr("id", "userCircle")
                                            .style("stroke", "#328de4")
                                            .attr("cx", function(d) {
                                                return self.x(d.year);
                                            })
                                            .attr("cy", function(d) {
                                                return self.y(d.userIncome);
                                            })
                                            .attr("r", 4.0)
                                            .on(
                                                    "mouseover",
                                                    function(d) {
                                                        div.transition().duration(200)
                                                        .style("opacity", .9)
                                                        .style("display",
                                                                "visible");
                                                var myPoint = data.map(function(obj) {
                                            		return obj.year;
                                                }).indexOf(d.year);
                                                
                                                if(myPoint > -1 ) {
                                                	if ($scope.marital_status == "Yes") {
                                                    	div.html(
                                                    	"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year + "</strong></td></tr></thead><tbody>" +
                                                    	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                            			"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                            			"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                            			"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                            			"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                        .style("left",(d3.event.pageX) + "px")
                                                        .style("top",(d3.event.pageY - 28)+ "px");
                                                    } else {
                                                    	div.html(
                                                    	"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year + "</strong></td></tr></thead><tbody>" +
                                                    	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                            			"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                            			"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr></tbody></table>")
                                                        .style("left",(d3.event.pageX) + "px")
                                                        .style("top",(d3.event.pageY - 28)+ "px");
                                                    }
                                                }

                                            })
                                            .on(
                                                    "mouseout",
                                                    function(d) {
                                                        div
                                                                .transition()
                                                                .duration(200)
                                                                .style(
                                                                        "opacity",
                                                                        0);
                                                    }).style("cursor",
                                                    "ns-resize").on(
                                                    "mousedown.drag",
                                                    self.datapoint_dragUser())
                                            .on("touchstart.drag",
                                                    self.datapoint_dragUser());

                                    circle.style("opacity", 1).style("stroke",
                                            "#328de4").attr("cx", function(d) {
                                        return self.x(d.year);
                                    }).attr("cy", function(d) {
                                        return self.y(d.userIncome);
                                    });

                                    circle.exit().remove();

                                    if (d3.event && d3.event.keyCode) {
                                        d3.event.preventDefault();
                                        d3.event.stopPropagation();
                                    }

                                    if (drag.indexOf(p1.year) == -1) {
                                        drag.push(p1.year);
                                        drag.sort(function(a, b) {
                                            return a - b;
                                        });
                                    }

                                    $scope.modifiedchartIncome = [];
                                    $scope.modifiedchartIncome = data;
                                    var leftIndex = drag.indexOf(p1.year) - 1;
                                    if (leftIndex == -1)
                                        leftIndex = 0;

                                    var rightIndex = drag.indexOf(p1.year) + 1;

                                    var leftPoint = drag[leftIndex];
                                    var rightPoint = drag[rightIndex];
                                    var initValue = tempData[leftIndex].userIncome;

                                    var diff = p1.year - leftPoint;
                                    var diff1 = rightPoint - p1.year;

                                    var interpolateLeft = ((p1.userIncome - initValue) / diff)
                                            .toFixed(0);

                                    var changedpoint = data.map(function(obj) {
                                        return obj.year;
                                    }).indexOf(p1.year);
                                    var leftStart = data.map(function(obj) {
                                        return obj.year;
                                    }).indexOf(leftPoint);

                                    if (tempYears.indexOf(p1.year) == 0) {
                                        if ($scope.marital_status == "Yes") {
                                            $scope.modifiedchartIncome[0] = {
                                                "year" : p1.year,
                                                "userIncome" : Math
                                                        .ceil(p1.userIncome * 1),
                                                "totalExpense" : data[0].totalExpense,
                                                "spouseIncome" : data[0].spouseIncome,
                                                "combinedIncome" : data[0].combinedIncome,
                                                "tax" : data[0].tax 
                                            };
                                        } else {
                                            $scope.modifiedchartIncome[0] = {
                                                "year" : p1.year,
                                                "userIncome" : Math
                                                        .ceil(p1.userIncome * 1),
                                                "totalExpense" : data[0].totalExpense,
                                                "tax" : data[0].tax 
                                            };
                                        }
                                    } else {
                                        $scope.modifiedchartIncome[0] = data[0];
                                    }

                                    if ($scope.marital_status == "Yes") {
                                        for (var i = leftStart + 1; i <= changedpoint; i++) {
                                            $scope.modifiedchartIncome[i] = {
                                                "year" : data[i].year,
                                                "userIncome" : Math
                                                        .ceil($scope.modifiedchartIncome[i - 1].userIncome
                                                                * 1
                                                                + interpolateLeft
                                                                * 1),
                                                "spouseIncome" : data[i].spouseIncome,
                                                "combinedIncome" : data[i].combinedIncome,
                                                "totalExpense" : data[i].totalExpense,
                                                "tax" : data[i].tax 
                                            };
                                        }
                                    } else {
                                        for (var i = leftStart + 1; i <= changedpoint; i++) {

                                            $scope.modifiedchartIncome[i] = {
                                                "year" : data[i].year,
                                                "userIncome" : Math
                                                        .ceil($scope.modifiedchartIncome[i - 1].userIncome
                                                                * 1
                                                                + interpolateLeft
                                                                * 1),
                                                "totalExpense" : data[i].totalExpense,
                                                "tax" : data[i].tax 
                                            };
                                        }
                                    }

                                    var changedpoint2 = data.map(function(obj) {
                                        return obj.year;
                                    }).indexOf(rightPoint);
                                    var changedpoint2Value = data[changedpoint2].userIncome;

                                    var interpolateRight = ((p1.userIncome - changedpoint2Value) / diff1)
                                            .toFixed(0);

                                    if ($scope.marital_status == "Yes") {
                                        for (var i = changedpoint + 1; i < changedpoint2; i++) {
                                            $scope.modifiedchartIncome[i] = {
                                                "year" : data[i].year,
                                                "userIncome" : Math
                                                        .ceil($scope.modifiedchartIncome[i - 1].userIncome
                                                                * 1
                                                                - interpolateRight
                                                                * 1),
                                                "spouseIncome" : data[i].spouseIncome,
                                                "combinedIncome" : data[i].combinedIncome,
                                                "totalExpense" : data[i].totalExpense,
                                                "tax" : data[i].tax 
                                            };
                                        }
                                    } else {
                                        for (var i = changedpoint + 1; i < changedpoint2; i++) {
                                            $scope.modifiedchartIncome[i] = {
                                                "year" : data[i].year,
                                                "userIncome" : Math
                                                        .ceil($scope.modifiedchartIncome[i - 1].userIncome
                                                                * 1
                                                                - interpolateRight
                                                                * 1),
                                                "totalExpense" : data[i].totalExpense,
                                                "tax" : data[i].tax 
                                            };
                                        }
                                    }

                                    if ($scope.marital_status == "Yes") {
                                        for (var i = leftStart + 1; i <= changedpoint; i++) {
                                            $scope.modifiedchartIncome[i].combinedIncome = $scope.modifiedchartIncome[i].userIncome
                                                    + $scope.modifiedchartIncome[i].spouseIncome;
                                        }
                                        for (var i = changedpoint + 1; i < changedpoint2; i++) {
                                            $scope.modifiedchartIncome[i].combinedIncome = $scope.modifiedchartIncome[i].userIncome
                                                    + $scope.modifiedchartIncome[i].spouseIncome;
                                        }
                                    }
                                } else {
                                    self.vis.selectAll("svg#userIncomeSVG")
                                            .remove();
                                    self.plotUser();
                                    self.plotPointsUser();
                                }
                            } else if (line == "spouse") {
                            	userIncomeGraph = false;
                                spouseIncomeGraph = true;
                                
                                if (d3.event.defaultPrevented)
                                    return;
                                self.vis.selectAll("svg#combinedIncomeSVG")
                                        .remove();
                                self.vis.selectAll("svg#expenseSVG").remove();
                                self.vis.selectAll("svg#userIncomeSVG")
                                        .remove();
                                self.vis.selectAll("svg#taxSVG").remove();
                                var beforeRoundSpouse = Number(self.y.invert(
                                        Math.max(0, Math.min(self.size.width,
                                                d3.mouse(self.vis.node())[1])))
                                        .toFixed(0));
                                var p1 = {
                                    "year" : Number(self.x
                                            .invert(
                                                    Math
                                                            .max(
                                                                    0,
                                                                    Math
                                                                            .min(
                                                                                    self.size.width,
                                                                                    d3
                                                                                            .mouse(self.vis
                                                                                                    .node())[0])))
                                            .toFixed(0)),
                                    "spouseIncome" : Math
                                            .ceil(beforeRoundSpouse / 500) * 500
                                };

                                var tempYears1Length = tempYears1.length;
                                if (p1.year < tempYears1[tempYears1Length - 2]
                                        && p1.year >= tempYears1[0]) {
                                    var tempYears1Index = tempYears1
                                            .indexOf(p1.year);
                                    if (tempYears1Index > -1) {
                                        tempData1.splice(tempYears1Index, 1);
                                        tempData1.push(p1);
                                        tempData1.sort(function(a, b) {
                                            return a.year - b.year;
                                        });
                                    } else {
                                        tempYears1.push(p1.year);
                                        tempData1.push(p1);
                                        tempData1.sort(function(a, b) {
                                            return a.year - b.year;
                                        });
                                        tempYears1.sort(function(a, b) {
                                            return a - b;
                                        });
                                    }
                                    self.vis.selectAll("svg#userIncomeSVG")
                                            .remove();
                                    self.vis.selectAll("svg#spouseIncomeSVG")
                                            .remove();

                                    self.vis
                                            .append("svg")
                                            .attr("top", 0)
                                            .data(tempData1)
                                            .attr("cx", function(d) {
                                                return d.year;
                                            })
                                            .attr("cy", function(d) {
                                                return d.spouseIncome;
                                            })
                                            .attr("left", 0)
                                            .attr("width", self.size.width)
                                            .attr("height", self.size.height)
                                            .attr(
                                                    "viewBox",
                                                    "0 0 " + self.size.width
                                                            + " "
                                                            + self.size.height)
                                            .attr("id", "spouseIncomeSVG")
                                            .append("path")
                                            .attr("class", "line2")
                                            .attr("id", "spouseIncome")
                                            .attr(
                                                    "d",
                                                    self
                                                            .dynamiclineSpouse(tempData1))
                                            .style("cursor", "pointer").on(
                                                    "mouseover",
                                                    function(d) {
                                                        div.transition().duration(200)
                                                                .style("opacity", .9)
                                                                .style("display",
                                                                        "visible");
                                                        
                                                        var p2 = {
                                                                "year" : Number(self.x
                                                                        .invert(
                                                                                Math
                                                                                        .max(
                                                                                                0,
                                                                                                Math
                                                                                                        .min(
                                                                                                                self.size.width,
                                                                                                                d3
                                                                                                                        .mouse(self.vis
                                                                                                                                .node())[0])))
                                                                        .toFixed(0)),
                                                                "userIncome" : Number(self.y.invert(
                                                                        Math.max(0, Math.min(self.size.width,
                                                                                d3.mouse(self.vis.node())[1])))
                                                                        .toFixed(0))
                                                            };
                                                        
                                                        var myPoint = data.map(function(obj) {
                                                    		return obj.year;
                                                        }).indexOf(p2.year);
                                                        
                                                        if(myPoint > -1 ) {
                                                        	div.html(
                                                                	"<table><thead><tr><td colspan=3><strong class=x-value>" + p2.year + "</strong></td></tr></thead><tbody>" +
                                                                	"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                        			"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                        			"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                        			"<tr style='border-top: 1px solid #32e2e2;border-bottom: 1px solid #32e2e2;'><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                        			"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                            .style("left",(d3.event.pageX) + "px")
                                                            .style("top",(d3.event.pageY - 28)+ "px");                
                                                        }

                                                    }).on(
                                                    "mouseout",
                                                    function(d) {
                                                        div.transition().duration(200)
                                                                .style("opacity", 0);
                                                    }).on(
                                                    "mousedown", function() { userClicked = false; spouseClicked = true;
                                                        self.click("spouse");
                                                    });

                                    self.vis.select("path").attr("d",
                                            self.dynamiclineSpouse(tempData1));
                                    var circle = self.vis.select(
                                            "svg#spouseIncomeSVG").selectAll(
                                            "circle").data(tempData1,
                                            function(d) {
                                                return d;
                                            });
                                    circle
                                            .enter()
                                            .append("circle")
                                            .attr("id", "spouseCircle")
                                            .style("stroke", "#32e2e2")
                                            .attr("cx", function(d) {
                                                return self.x(d.year);
                                            })
                                            .attr("cy", function(d) {
                                                return self.y(d.spouseIncome);
                                            })
                                            .attr("r", 4.0)
                                            .on(
                                                    "mouseover",
                                                    function(d) {
                                                        div
                                                                .transition()
                                                                .duration(200)
                                                                .style(
                                                                        "opacity",
                                                                        .9)
                                                                .style(
                                                                        "display",
                                                                        "visible");
                                                        var myPoint = data.map(function(obj) {
                                                    		return obj.year;
                                                        }).indexOf(d.year.toFixed(0));
                                                        if(myPoint > -1) {
                                                        	div.html(
                                                                	"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year + "</strong></td></tr></thead><tbody>" +
                                                                	"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                        			"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                        			"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                        			"<tr style='border-top: 1px solid #32e2e2;border-bottom: 1px solid #32e2e2;'><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                        			"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                            .style("left",(d3.event.pageX) + "px")
                                                            .style("top",(d3.event.pageY - 28)+ "px");
                                                        }
                                                    })
                                            .on(
                                                    "mouseout",
                                                    function(d) {
                                                        div
                                                                .transition()
                                                                .duration(200)
                                                                .style(
                                                                        "opacity",
                                                                        0);
                                                    })
                                            .style("cursor", "ns-resize")
                                            .on("mousedown.drag",
                                                    self.datapoint_dragSpouse())
                                            .on("touchstart.drag",
                                                    self.datapoint_dragSpouse());

                                    circle.style("opacity", 1).style("stroke",
                                            "#32e2e2").attr("cx", function(d) {
                                        return self.x(d.year);
                                    }).attr("cy", function(d) {
                                        return self.y(d.spouseIncome);
                                    });

                                    circle.exit().remove();

                                    if (d3.event && d3.event.keyCode) {
                                        d3.event.preventDefault();
                                        d3.event.stopPropagation();
                                    }

                                    if (drag1.indexOf(p1.year) == -1) {
                                        drag1.push(p1.year);
                                        drag1.sort(function(a, b) {
                                            return a - b;
                                        });
                                    }

                                    $scope.modifiedchartIncomeSpouse = [];
                                    $scope.modifiedchartIncomeSpouse = data;

                                    var leftIndex = drag1.indexOf(p1.year) - 1;
                                    if (leftIndex == -1)
                                        leftIndex = 0;

                                    var rightIndex = drag1.indexOf(p1.year) + 1;

                                    var leftPoint = drag1[leftIndex];
                                    var rightPoint = drag1[rightIndex];

                                    var initValue = tempData1[leftIndex].spouseIncome;

                                    var diff = p1.year - leftPoint;
                                    var diff1 = rightPoint - p1.year;

                                    var interpolateLeft = ((p1.spouseIncome - initValue) / diff)
                                            .toFixed(0);

                                    var changedpoint = data.map(function(obj) {
                                        return obj.year;
                                    }).indexOf(p1.year);
                                    var leftStart = data.map(function(obj) {
                                        return obj.year;
                                    }).indexOf(leftPoint);

                                    var startIndex = data.map(function(obj) {
                                        return obj.year;
                                    }).indexOf(tempYears1[0]);

                                    if (tempYears1.indexOf(p1.year) == 0) {
                                        $scope.modifiedchartIncomeSpouse[startIndex] = {
                                            "year" : p1.year,
                                            "userIncome" : data[startIndex].userIncome,
                                            "totalExpense" : data[startIndex].totalExpense,
                                            "spouseIncome" : Math
                                                    .ceil(p1.spouseIncome * 1),
                                            "combinedIncome" : data[startIndex].combinedIncome,
                                            "tax" : data[startIndex].tax 
                                        };
                                    } else {
                                        $scope.modifiedchartIncomeSpouse[startIndex] = data[startIndex];
                                    }

                                    for (i = leftStart + 1; i <= changedpoint; i++) {
                                        $scope.modifiedchartIncomeSpouse[i] = {
                                            "year" : data[i].year,
                                            "spouseIncome" : Math
                                                    .ceil($scope.modifiedchartIncomeSpouse[i - 1].spouseIncome
                                                            * 1
                                                            + interpolateLeft
                                                            * 1),
                                            "userIncome" : data[i].userIncome,
                                            "combinedIncome" : data[i].combinedIncome,
                                            "totalExpense" : data[i].totalExpense,
                                            "tax" : data[i].tax 
                                        };
                                    }

                                    var changedpoint2 = data.map(function(obj) {
                                        return obj.year;
                                    }).indexOf(rightPoint);
                                    var changedpoint2Value = data[changedpoint2].spouseIncome;

                                    var interpolateRight = ((p1.spouseIncome - changedpoint2Value) / diff1)
                                            .toFixed(0);

                                    for (var i = changedpoint + 1; i < changedpoint2; i++) {

                                        $scope.modifiedchartIncomeSpouse[i] = {
                                            "year" : data[i].year,
                                            "spouseIncome" : Math
                                                    .ceil($scope.modifiedchartIncomeSpouse[i - 1].spouseIncome
                                                            * 1
                                                            - interpolateRight
                                                            * 1),
                                            "userIncome" : data[i].userIncome,
                                            "combinedIncome" : data[i].combinedIncome,
                                            "totalExpense" : data[i].totalExpense,
                                            "tax" : data[i].tax 
                                        };
                                    }

                                    for (var i = leftStart + 1; i <= changedpoint; i++) {
                                        $scope.modifiedchartIncomeSpouse[i].combinedIncome = $scope.modifiedchartIncomeSpouse[i].userIncome
                                                + $scope.modifiedchartIncomeSpouse[i].spouseIncome;
                                    }
                                    for (var i = changedpoint + 1; i < changedpoint2; i++) {
                                        $scope.modifiedchartIncomeSpouse[i].combinedIncome = $scope.modifiedchartIncomeSpouse[i].userIncome
                                                + $scope.modifiedchartIncomeSpouse[i].spouseIncome;
                                    }
                                } else {
                                    self.vis.selectAll("svg#spouseIncomeSVG")
                                            .remove();
                                    self.plotSpouse();
                                    self.plotPointsSpouse();
                                }
                            } else if (expenseIncomeGraph == true) {
                                self.vis.selectAll("svg#userIncomeSVG")
                                        .remove();
                                self.vis.selectAll("svg#combinedIncomeSVG")
                                        .remove();
                                self.vis.selectAll("svg#expenseSVG").remove();
                                self.vis.selectAll("svg#spouseIncomeSVG")
                                        .remove();
                                //self.vis.selectAll("svg#taxSVG").remove();
                                self.plotExpense();
                                self.plotPointsExpense();
                            } else if (combinedIncomeGraph == true) {
                                self.vis.selectAll("svg#userIncomeSVG")
                                        .remove();
                                self.vis.selectAll("svg#combinedIncomeSVG")
                                        .remove();
                                self.vis.selectAll("svg#expenseSVG").remove();
                                self.vis.selectAll("svg#spouseIncomeSVG")
                                        .remove();
                                //self.vis.selectAll("svg#taxSVG").remove();
                                self.plotCombined();
                                self.plotPointsCombined();
                            } /*else if (taxGraph == true) {
                                self.vis.selectAll("svg#userIncomeSVG")
                                .remove();
                		        self.vis.selectAll("svg#combinedIncomeSVG")
                		                .remove();
                		        self.vis.selectAll("svg#expenseSVG").remove();
                		        self.vis.selectAll("svg#spouseIncomeSVG")
                		                .remove();
                		        self.vis.selectAll("svg#taxSVG").remove();
                		        self.plotTax();
                		        self.plotPointsTax();
                            }*/	
                            $scope.mousemoveFlag = true;
                        };
                        
                        SimpleGraph.prototype.plotTax = function() // Plot Tax
                        {
                            this.points = data;
                            this.vis.append("svg").attr("top", 0).data(data)
                                    .attr("cx", function(d) {
                                        return d.year;
                                    }).attr("cy", function(d) {
                                        return d.tax;
                                    }).attr("left", 0).attr("width",
                                            this.size.width).attr("height",
                                            this.size.height).attr(
                                            "viewBox",
                                            "0 0 " + this.size.width + " "
                                                    + this.size.height).attr(
                                            "id", "taxSVG").append("path")
                                    .attr("class", "lineTax").attr("id",
                                            "tax").attr("d",
                                            this.line4(this.points));
                        };
                        
                        SimpleGraph.prototype.plotUser = function() // Plot userIncome
                        {
                            this.points = data;
                            this.vis.append("svg").attr("top", 0).data(data)
                                    .attr("cx", function(d) {
                                        return d.year;
                                    }).attr("cy", function(d) {
                                        return d.userIncome;
                                    }).attr("left", 0).attr("width",
                                            this.size.width).attr("height",
                                            this.size.height).attr(
                                            "viewBox",
                                            "0 0 " + this.size.width + " "
                                                    + this.size.height).attr(
                                            "id", "userIncomeSVG").append(
                                            "path").attr("class", "line").attr(
                                            "id", "userIncome").attr("d",
                                            this.line(this.points)).style(
                                            "cursor", "pointer").on(
                                                    "mousemove",
                                                    function(d) {
                                                    	var beforeRoundIncome = Number(self.y.invert(
                                                                Math.max(0, Math.min(self.size.width,
                                                                        d3.mouse(self.vis.node())[1])))
                                                                .toFixed(0));
                                                        var p1 = {
                                                            "year" : Number(self.x
                                                                    .invert(Math.max(0,Math.min(self.size.width, d3.mouse(self.vis.node())[0])))
                                                                    .toFixed(0)),
                                                            "userIncome" : Math
                                                                    .ceil(beforeRoundIncome / 500) * 500
                                                        };
                                                    	$scope.mousemoveFlag = false;
                                                        div.transition().duration(200)
                                                                .style("opacity", .9)
                                                                .style("display",
                                                                        "visible");
                                                        var myPoint = data.map(function(obj) {
                                                    		return obj.year;
                                                        }).indexOf(p1.year);
                                                        if(myPoint > -1 ) {
                                                        	if ($scope.marital_status == "Yes") {
                                                            	div.html(
                                                            	"<table><thead><tr><td colspan=3><strong class=x-value>" + p1.year + "</strong></td></tr></thead><tbody>" +
                                                            	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                                .style("left",(d3.event.pageX) + "px")
                                                                .style("top",(d3.event.pageY - 28)+ "px");
                                                            } else {
                                                            	div.html(
                                                            	"<table><thead><tr><td colspan=3><strong class=x-value>" + p1.year + "</strong></td></tr></thead><tbody>" +
                                                            	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr></tbody></table>")
                                                                .style("left",(d3.event.pageX) + "px")
                                                                .style("top",(d3.event.pageY - 28)+ "px");
                                                            }
                                                        }

                                                    }).on(
                                                    "mouseout",
                                                    function(d) {
                                                    	$scope.mousemoveFlag = true;
                                                        div.transition().duration(200)
                                                                .style("opacity", 0);
                                                    }).on(
                                            "mousedown", function() { userClicked = true; spouseClicked = false;
                                                self.click("user");
                                            });
                        };

                        SimpleGraph.prototype.plotSpouse = function() // Plot spouseIncome
                        {
                            this.points = data;
                            this.vis.append("svg").attr("top", 0).data(data)
                                    .attr("cx", function(d) {
                                        return d.year;
                                    }).attr("cy", function(d) {
                                        return d.spouseIncome;
                                    }).attr("left", 0).attr("width",
                                            this.size.width).attr("height",
                                            this.size.height).attr(
                                            "viewBox",
                                            "0 0 " + this.size.width + " "
                                                    + this.size.height).attr(
                                            "id", "spouseIncomeSVG").append(
                                            "path").attr("class", "line2")
                                    .attr("id", "spouseIncome").attr("d",
                                            this.line2(this.points)).style(
                                            "cursor", "pointer").on(
                                                    "mousemove",
                                                    function(d) {
                                                    	var beforeRoundIncome = Number(self.y.invert(
                                                                Math.max(0, Math.min(self.size.width,
                                                                        d3.mouse(self.vis.node())[1])))
                                                                .toFixed(0));
                                                        var p1 = {
                                                            "year" : Number(self.x
                                                                    .invert(Math.max(0,Math.min(self.size.width, d3.mouse(self.vis.node())[0])))
                                                                    .toFixed(0)),
                                                            "spouseIncome" : Math.ceil(beforeRoundIncome / 500) * 500
                                                        };
                                                    	$scope.mousemoveFlag = false;
                                                        div.transition().duration(200)
                                                                .style("opacity", .9)
                                                                .style("display",
                                                                        "visible");
                                                        var myPoint = data.map(function(obj) {
                                                    		return obj.year;
                                                        }).indexOf(p1.year);
                                                        
                                                        if(myPoint > -1 ) {
                                                        	div.html(
                                                        	"<table><thead><tr><td colspan=3><strong class=x-value>" + p1.year + "</strong></td></tr></thead><tbody>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                        	"<tr style='border-top: 1px solid #32e2e2;border-bottom: 1px solid #32e2e2;'><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                            .style("left",(d3.event.pageX) + "px")
                                                            .style("top",(d3.event.pageY - 28)+ "px");
                                                        }

                                                    }).on(
                                                    "mouseout",
                                                    function(d) {
                                                    	$scope.mousemoveFlag = true;
                                                        div.transition().duration(200)
                                                                .style("opacity", 0);
                                                    }).on(
                                            "mousedown", function() { userClicked = false; spouseClicked = true;
                                                self.click("spouse");
                                            });
                        };

                        SimpleGraph.prototype.plotExpense = function() // Plot Expense
                        {
                            this.points = data;
                            this.vis.append("svg").attr("top", 0).data(data)
                                    .attr("cx", function(d) {
                                        return d.year;
                                    }).attr("cy", function(d) {
                                        return d.spouseIncome;
                                    }).attr("left", 0).attr("width",
                                            this.size.width).attr("height",
                                            this.size.height).attr(
                                            "viewBox",
                                            "0 0 " + this.size.width + " "
                                                    + this.size.height).attr(
                                            "id", "expenseSVG").append("path")
                                    .attr("class", "line1").attr("id",
                                            "expense").attr("d",
                                            this.line1(this.points))
                                            .on(
                                                    "mousemove",
                                                    function(d) {
                                                        var p1 = {
                                                            "year" : Number(self.x
                                                                    .invert(Math.max(0,Math.min(self.size.width, d3.mouse(self.vis.node())[0])))
                                                                    .toFixed(0))
                                                        };

                                                        div.transition().duration(200)
                                                                .style("opacity", .9)
                                                                .style("display",
                                                                        "visible");
                                                        var myPoint = data.map(function(obj) {
                                                    		return obj.year;
                                                        }).indexOf(p1.year);
                                                        if(myPoint > -1 ) {
                                                        	if ($scope.marital_status == "Yes") {
                                                            	div.html(
                                                            	"<table><thead><tr><td colspan=3><strong class=x-value>" + p1.year + "</strong></td></tr></thead><tbody>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                            	"<tr style='border-top: 1px solid #e43234;border-bottom: 1px solid #e43234;'><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                                .style("left",(d3.event.pageX) + "px")
                                                                .style("top",(d3.event.pageY - 28)+ "px");
                                                            } else {
                                                            	div.html(
                                                            	"<table><thead><tr><td colspan=3><strong class=x-value>" + p1.year + "</strong></td></tr></thead><tbody>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                            	"<tr style='border-top: 1px solid #e43234;border-bottom: 1px solid #e43234;'><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr></tbody></table>")
                                                                .style("left",(d3.event.pageX) + "px")
                                                                .style("top",(d3.event.pageY - 28)+ "px");
                                                            }
                                                        }

                                                    }).on(
                                                    "mouseout",
                                                    function(d) {
                                                    	$scope.mousemoveFlag = true;
                                                        div.transition().duration(200)
                                                                .style("opacity", 0);
                                                    });
                        };

                        SimpleGraph.prototype.plotCombined = function() // Plot combinedIncome
                        {
                            this.points = data;
                            this.vis.append("svg").attr("top", 0).data(data)
                                    .attr("cx", function(d) {
                                        return d.year;
                                    }).attr("cy", function(d) {
                                        return d.combinedIncome;
                                    }).attr("left", 0).attr("width",
                                            this.size.width).attr("height",
                                            this.size.height).attr(
                                            "viewBox",
                                            "0 0 " + this.size.width + " "
                                                    + this.size.height).attr(
                                            "id", "combinedIncomeSVG").append(
                                            "path").attr("class", "line3")
                                    .attr("id", "combinedIncome").attr("d",
                                            this.line3(this.points))
                                            .on(
                                                    "mousemove",
                                                    function(d) {

                                                        var p1 = {
                                                            "year" : Number(self.x.invert(Math.max(0,Math.min(self.size.width, d3.mouse(self.vis.node())[0]))).toFixed(0))
                                                        };
                                                        div.transition().duration(200)
                                                                .style("opacity", .9)
                                                                .style("display",
                                                                        "visible");
                                                        var myPoint = data.map(function(obj) {
                                                    		return obj.year;
                                                        }).indexOf(p1.year);
                                                        
                                                        if(myPoint > -1 ) {
                                                        	div.html(
                                                        	"<table><thead><tr><td colspan=3><strong class=x-value>" + p1.year + "</strong></td></tr></thead><tbody>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                        	"<tr style='border-top: 1px solid #b0c10c;border-bottom: 1px solid #b0c10c;'><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                            .style("left",(d3.event.pageX) + "px")
                                                            .style("top",(d3.event.pageY - 28)+ "px");
                                                        }

                                                    }).on(
                                                    "mouseout",
                                                    function(d) {
                                                        div.transition().duration(200)
                                                                .style("opacity", 0);
                                                    });
                        };
                        
                        SimpleGraph.prototype.plotPointsTax = function() // Plot Tax points
                        {
                            self = this;
                            this.vis.select("path").attr("d",
                                    this.line4(this.points));
                            var circle = this.vis.select("svg#taxSVG")
                                    .selectAll("circle").data(this.points,
                                            function(d) {
                                                return d;
                                            });
                            circle.enter().append("circle").attr("id",
                                    "taxCircle").style("stroke", "#ff7f0e")
                            .attr("cx", function(d) {
                                return self.x(d.year);
                            }).attr("cy", function(d) {
                                return self.y(d.tax);
                            }).attr("r", 4.0).on(
                                    "mouseover",
                                    function(d) {
                                        div.transition().duration(200).style(
                                                "opacity", .9).style("display",
                                                "visible");
                                        div.html(
                                                "Tax "
                                                        + d.year.toFixed(0)
                                                        + ", "
                                                        + d.tax
                                                                .toFixed(0))

                                        .style("left", (d3.event.pageX) + "px")
                                                .style(
                                                        "top",
                                                        (d3.event.pageY - 28)
                                                                + "px");
                                    }).on(
                                    "mouseout",
                                    function(d) {
                                        div.transition().duration(200).style(
                                                "opacity", 0);
                                    }).style("cursor", "default");

                            circle.style("opacity", 1).style("stroke",
                                    "#ff7f0e")

                            .attr("cx", function(d) {
                                return self.x(d.year);
                            }).attr("cy", function(d) {
                                return self.y(d.tax);
                            });

                            circle.exit().remove();

                            if (d3.event && d3.event.keyCode) {
                                d3.event.preventDefault();
                                d3.event.stopPropagation();
                            }

                        };
                        
                        SimpleGraph.prototype.plotPointsExpense = function() // Plot Expense Points
                        {
                            self = this;
                            this.vis.select("path").attr("d",
                                    this.line1(this.points));
                            var circle = this.vis.select("svg#expenseSVG")
                                    .selectAll("circle").data(this.points,
                                            function(d) {
                                                return d;
                                            });
                            circle.enter().append("circle").attr("id",
                                    "expenseCircle").style("stroke", "#e43234")
                            .attr("cx", function(d) {
                                return self.x(d.year);
                            }).attr("cy", function(d) {
                                return self.y(d.totalExpense);
                            }).attr("r", 4.0).on(
                                    "mouseover",
                                    function(d) {
                                    	$scope.mousemoveFlag = false;
                                        div.transition().duration(200).style(
                                                "opacity", .9).style("display",
                                                "visible");
                                        var myPoint = data.map(function(obj) {
                                    		return obj.year;
                                        }).indexOf(d.year);
                                        
                                        if(myPoint > -1 ) {
                                        	if ($scope.marital_status == "Yes") {
                                            	div.html(
                                    			"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year + "</strong></td></tr></thead><tbody>" +
                                    			"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                    			"<tr style='border-top: 1px solid #e43234;border-bottom: 1px solid #e43234;'><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                            	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                            	"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                            	"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                .style("left",(d3.event.pageX) + "px")
                                                .style("top",(d3.event.pageY - 28)+ "px");
                                            } else {
                                            	div.html(
                                            	"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year + "</strong></td></tr></thead><tbody>" +
                                            	"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                            	"<tr style='border-top: 1px solid #e43234;border-bottom: 1px solid #e43234;'><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                            	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr></tbody></table>")
                                                .style("left",(d3.event.pageX) + "px")
                                                .style("top",(d3.event.pageY - 28)+ "px");
                                            }
                                        }
                                    }).on(
                                    "mouseout",
                                    function(d) {
                                    	$scope.mousemoveFlag = true;
                                        div.transition().duration(200).style(
                                                "opacity", 0);
                                    }).style("cursor", "default");

                            circle.style("opacity", 1).style("stroke",
                                    "#e43234")

                            .attr("cx", function(d) {
                                return self.x(d.year);
                            }).attr("cy", function(d) {
                                return self.y(d.totalExpense);
                            });

                            circle.exit().remove();

                            if (d3.event && d3.event.keyCode) {
                                d3.event.preventDefault();
                                d3.event.stopPropagation();
                            }

                        };

                        SimpleGraph.prototype.plotPointsCombined = function() // Plot CombinedIncome Points
                        {
                            self = this;
                            $scope.modifiedchartIncome = this.points;
                            this.vis.select("path").attr("d",
                                    this.line3(this.points));
                            var circle = this.vis.select(
                                    "svg#combinedIncomeSVG")
                                    .selectAll("circle").data(this.points,
                                            function(d) {
                                                return d;
                                            });
                            circle
                                    .enter()
                                    .append("circle")
                                    .attr("id", "combinedCircle")
                                    .style("stroke", "#b0c10c")
                                    .attr("cx", function(d) {
                                        return self.x(d.year);
                                    })
                                    .attr("cy", function(d) {
                                        return self.y(d.combinedIncome);
                                    })
                                    .attr("r", 4.0)
                                    .on(
                                            "mouseover",
                                            function(d) {
                                            	$scope.mousemoveFlag = false;
                                                div.transition().duration(200).style(
                                                        "opacity", .9).style("display",
                                                        "visible");
                                                var myPoint = data.map(function(obj) {
                                            		return obj.year;
                                                }).indexOf(d.year);
                                                
                                                if(myPoint > -1 ) {
                                                	div.html(
                                                			"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year + "</strong></td></tr></thead><tbody>" +
                                                			"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                			"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                        	"<tr style='border-top: 1px solid #b0c10c;border-bottom: 1px solid #b0c10c;'><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                    .style("left",(d3.event.pageX) + "px")
                                                    .style("top",(d3.event.pageY - 28)+ "px");
                                                }
                                            }).on(
                                            "mouseout",
                                            function(d) {
                                            	$scope.mousemoveFlag = true;;
                                                div.transition().duration(200)
                                                        .style("opacity", 0);
                                            }).style("cursor", "default");

                            circle.style("opacity", 1).style("stroke",
                                    "#b0c10c")
                            .attr("cx", function(d) {
                                return self.x(d.year);
                            }).attr("cy", function(d) {
                                return self.y(d.combinedIncome);
                            });

                            circle.exit().remove();

                            if (d3.event && d3.event.keyCode) {
                                d3.event.preventDefault();
                                d3.event.stopPropagation();
                            }

                        };

                        SimpleGraph.prototype.plotPointsUser = function() 
                        {
                            self = this;
                            this.vis.select("path").attr("d",
                                    this.line(this.points));

                            var circle = this.vis.select("svg#userIncomeSVG")
                                    .selectAll("circle").data(tempData,
                                            function(d) {
                                                return d;
                                            });
                            circle
                                    .enter()
                                    .append("circle")
                                    .attr("id", "userCircle")
                                    .style("stroke", "#328de4")
                                    .attr("cx", function(d) {
                                        return self.x(d.year);
                                    })
                                    .attr("cy", function(d) {
                                        return self.y(d.userIncome);
                                    })
                                    .attr("r", 4.0)
                                    .on(
                                            "mouseover",
                                            function(d) {
                                            	$scope.mousemoveFlag = false;
                                                div.transition().duration(200)
                                                        .style("opacity", .9)
                                                        .style("display",
                                                                "visible");
                                                
                                                var myPoint = data.map(function(obj) {
                                            		return obj.year;
                                                }).indexOf(d.year);
                                                if(myPoint > -1 ) {
                                                	if ($scope.marital_status == "Yes") {
                                                    	div.html(
                                                            	"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year + "</strong></td></tr></thead><tbody>" +
                                                            	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                        .style("left",(d3.event.pageX) + "px")
                                                        .style("top",(d3.event.pageY - 28)+ "px");
                                                    } else {
                                                    	div.html(
                                                    	"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year + "</strong></td></tr></thead><tbody>" +
                                                    	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                    	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                    	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr></tbody></table>")
                                                        .style("left",(d3.event.pageX) + "px")
                                                        .style("top",(d3.event.pageY - 28)+ "px");
                                                    }
                                                }

                                            }).on(
                                            "mouseout",
                                            function(d) {
                                            	$scope.mousemoveFlag = true;
                                                div.transition().duration(200)
                                                        .style("opacity", 0);
                                            })

                                    .style("cursor", "ns-resize").on(
                                            "mousedown.drag",
                                            self.datapoint_dragUser()).on(
                                            "touchstart.drag",
                                            self.datapoint_dragUser());

                            circle.style("opacity", 1).style("stroke",
                                    "#328de4")
                            .attr("cx", function(d) {
                                return self.x(d.year);
                            }).attr("cy", function(d) {
                                return self.y(d.userIncome);
                            });
                            circle.exit().remove();

                            if (d3.event && d3.event.keyCode) {
                                d3.event.preventDefault();
                                d3.event.stopPropagation();
                            }
                        };

                        SimpleGraph.prototype.plotPointsSpouse = function() // Plot spouseIncome Points
                        {
                            self = this;
                            this.vis.select("path").attr("d",
                                    this.line2(this.points));

                            var circle1 = this.vis
                                    .select("svg#spouseIncomeSVG").selectAll(
                                            "circle").data(tempData1,
                                            function(d) {
                                                return d;
                                            });

                            circle1
                                    .enter()
                                    .append("circle")
                                    .attr("id", "spouseCircle")
                                    .style("stroke", "#32e2e2")
                                    .style("fill", "white")
                                    .attr("cx", function(d) {
                                        return self.x(d.year);
                                    })
                                    .attr("cy", function(d) {
                                        return self.y(d.spouseIncome);
                                    })
                                    .attr("r", 4.0)
                                    .on(
                                            "mouseover",
                                            function(d) {
                                            	$scope.mousemoveFlag = false;
                                                div.transition().duration(200).style(
                                                        "opacity", .9).style("display",
                                                        "visible");
                                                var myPoint = data.map(function(obj) {
                                            		return obj.year;
                                                }).indexOf(d.year);
                                                
                                                if(myPoint > -1 ) {
                                                	div.html(
                                        			"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year + "</strong></td></tr></thead><tbody>" +
                                        			"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                        			"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                	"<tr style='border-top: 1px solid #32e2e2;border-bottom: 1px solid #32e2e2;'><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                	"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                    .style("left",(d3.event.pageX) + "px")
                                                    .style("top",(d3.event.pageY - 28)+ "px");
                                                }
                                            }).on(
                                            "mouseout",
                                            function(d) {
                                            	$scope.mousemoveFlag = true;;
                                                div.transition().duration(200)
                                                        .style("opacity", 0);
                                            }).style("cursor", "ns-resize").on(
                                            "mousedown.drag",
                                            self.datapoint_dragSpouse()).on(
                                            "touchstart.drag",
                                            self.datapoint_dragSpouse());

                            circle1.style("opacity", 1).style("stroke",
                                    "#32e2e2")
                            .attr("cx", function(d) {
                                return self.x(d.year);
                            }).attr("cy", function(d) {
                                return self.y(d.spouseIncome);
                            });

                            circle1.exit().remove();

                            if (d3.event && d3.event.keyCode) {
                                d3.event.preventDefault();
                                d3.event.stopPropagation();
                            }
                        };

                        /* On Drag update the userIncome line */
                        SimpleGraph.prototype.updateUser = function() {

                            self = this;
                            this.vis.select("path").attr("d",
                                    this.dynamiclineUser(tempData));
                            var circle = this.vis.select("svg#userIncomeSVG")
                                    .selectAll("circle").data(tempData,
                                            function(d) {
                                                return d;
                                            });
                            circle.enter().append("circle").attr("id",
                                    "userCircle").style("stroke", "#328de4")
                            .attr("cx", function(d) {
                                return self.x(d.year);
                            }).attr("cy", function(d) {
                                return self.y(d.userIncome);
                            }).attr("r", 4.0).on(
                                    "mouseover",
                                    function(d) {
                                        div.transition().duration(200).style(
                                                "opacity", .9).style("display",
                                                "visible");
                                        div.html(
                                        		"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year.toFixed(0) + "</strong></td></tr></thead><tbody>"+
                                            	"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + d.userIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                .style("left",(d3.event.pageX) + "px")
                                                .style("top",(d3.event.pageY - 28)+ "px");
                                    }).on(
                                    "mouseout",
                                    function(d) {
                                        div.transition().duration(200).style(
                                                "opacity", 0);
                                    }).style("cursor", "ns-resize")
                                    .on("mousedown.drag",
                                            self.datapoint_dragUser()).on(
                                            "touchstart.drag",
                                            self.datapoint_dragUser());

                            circle.style("opacity", 1).style("stroke",
                                    "#328de4")
                            .attr("cx", function(d) {
                                return self.x(d.year);
                            }).attr("cy", function(d) {
                                return self.y(d.userIncome);
                            });

                            circle.exit().remove();

                            if (d3.event && d3.event.keyCode) {
                                d3.event.preventDefault();
                                d3.event.stopPropagation();
                            }
                        };

                        /* On Drag update the spouseIncome line */
                        SimpleGraph.prototype.updateSpouse = function() {

                            self = this;
                            $scope.modifiedchartIncome = this.points;
                            this.vis.select("path").attr("d",
                                    this.dynamiclineSpouse(tempData1));
                            var circle1 = this.vis
                                    .select("svg#spouseIncomeSVG").selectAll(
                                            "circle").data(tempData1,
                                            function(d) {
                                                return d;
                                            });
                            circle1
                                    .enter()
                                    .append("circle")
                                    .attr("id", "spouseCircle")
                                    .style("stroke", "#32e2e2")
                                    .style("fill", "white")
                                    .attr("cx", function(d) {
                                        return self.x(d.year);
                                    })
                                    .attr("cy", function(d) {
                                        return self.y(d.spouseIncome);
                                    })
                                    .attr("r", 4.0)
                                    .on(
                                            "mouseover",
                                            function(d) {
                                                div.transition().duration(200)
                                                        .style("opacity", .9)
                                                        .style("display",
                                                                "visible");
                                                div.html(
                                                		"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year.toFixed(0) + "</strong></td></tr></thead><tbody>"+
                                                    	"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + d.spouseIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                        .style("left",(d3.event.pageX) + "px")
                                                        .style("top",(d3.event.pageY - 28)+ "px");

                                            }).on(
                                            "mouseout",
                                            function(d) {
                                                div.transition().duration(200)
                                                        .style("opacity", 0);
                                            }).style("cursor", "ns-resize").on(
                                            "mousedown.drag",
                                            self.datapoint_dragSpouse()).on(
                                            "touchstart.drag",
                                            self.datapoint_dragSpouse());

                            circle1.style("opacity", 1).style("stroke",
                                    "#32e2e2")
                            .attr("cx", function(d) {
                                return self.x(d.year);
                            }).attr("cy", function(d) {
                                return self.y(d.spouseIncome);
                            });

                            circle1.exit().remove();

                            if (d3.event && d3.event.keyCode) {
                                d3.event.preventDefault();
                                d3.event.stopPropagation();
                            }
                        };

                        /* Drag userIncome line */
                        SimpleGraph.prototype.datapoint_dragUser = function() {
                            self = this;
                            return function(d) {
                                if (d3.event.shiftKey) {
                                    console.log("In If:: Shift Key");
                                    if (d3.event.defaultPrevented)
                                        return;

                                    var beforeRoundIncome = Number(self.y
                                            .invert(
                                                    Math
                                                            .max(
                                                                    0,
                                                                    Math
                                                                            .min(
                                                                                    self.size.width,
                                                                                    d3
                                                                                            .mouse(self.vis
                                                                                                    .node())[1])))
                                            .toFixed(0));
                                    var p1 = {
                                        "year" : Number(self.x.invert(
                                                Math.max(0, Math.min(
                                                        self.size.width,
                                                        d3.mouse(self.vis
                                                                .node())[0])))
                                                .toFixed(0)),
                                        "userIncome" : Math
                                                .ceil(beforeRoundIncome / 500) * 500
                                    };
                                    if (tempYears.includes(p1.year)) {
                                        var selectedIndex = 0;
                                        var tempYearsLength = tempYears.length;
                                        if (p1.year < tempYears[tempYearsLength - 2]
                                                && p1.year > tempYears[0]) {
                                            selectedIndex = tempYears
                                                    .indexOf(p1.year);

                                            var leftIndex = selectedIndex - 1;
                                            if (leftIndex == -1)
                                                leftIndex = 0;
                                            var leftYear = Number(tempData[leftIndex].year);
                                            var leftIncome = Number(tempData[leftIndex].userIncome);

                                            var rightIndex = leftIndex + 2;
                                            var rightYear = Number(tempData[rightIndex].year);
                                            var rightIncome = Number(tempData[rightIndex].userIncome);

                                            // Find the start and end year index
                                            // from data array.
                                            var leftStart = data.map(
                                                    function(obj) {
                                                        return obj.year;
                                                    }).indexOf(leftYear);
                                            var rightEnd = data.map(
                                                    function(obj) {
                                                        return obj.year;
                                                    }).indexOf(rightYear);

                                            if (tempYears.length == 4) {
                                                rightYear = Number(tempData[rightIndex].year) - 1;
                                                rightIncome = $scope.dataUserIncome;
                                            }

                                            // Formula for finding step
                                            var diff = (rightIncome - leftIncome)
                                                    / (rightYear - leftYear);

                                            for (var i = leftStart + 1, j = 1; i < rightEnd; i++, j++) {
                                                data[i].userIncome = Math
                                                        .ceil(leftIncome + diff
                                                                * j);
                                                $scope.modifiedchartIncome[i].userIncome = Math
                                                        .ceil(leftIncome + diff
                                                                * j);
                                            }
                                            if ($scope.marital_status == "Yes") {
                                                for (var i = leftStart + 1; i < rightEnd; i++) {
                                                    $scope.modifiedchartIncome[i].combinedIncome = $scope.modifiedchartIncome[i].userIncome
                                                            + $scope.modifiedchartIncome[i].spouseIncome;
                                                }
                                            }
                                            drag.splice(selectedIndex, 1);
                                            tempYears.splice(selectedIndex, 1);
                                            tempData.splice(selectedIndex, 1);
                                        }
                                    }

                                    self.vis.selectAll("svg#userIncomeSVG")
                                            .remove();
                                    self.vis.selectAll("svg#expenseSVG")
                                            .remove();
                                    self.plotUser();
                                    self.plotPointsUser();
                                    div.transition().duration(200).style(
                                            "opacity", 0);
                                } else {
                                  	self.vis.selectAll(
                                    "svg#expenseSVG")
                                    .remove();
                            self.vis
                                    .selectAll(
                                            "svg#spouseIncomeSVG")
                                    .remove();
                            self.vis
                                    .selectAll(
                                            "svg#combinedIncomeSVG")
                                    .remove();
                            self.vis
                            .selectAll(
                                    "svg#taxSVG")
                            .remove();
                                    $scope.mousemoveFlag = false;
                                    registerKeyboardHandler(self.keydown());
                                    document.onselectstart = function() {
                                        return false;
                                    };
                                    self.selected = d;
                                    var tempYearsLength = tempYears.length;
                                    if (self.selected.year < tempYears[tempYearsLength - 2]
                                            && self.selected.year >= tempYears[0]) {
                                        self.dragged = self.selected;
                                        //angular.element(document).bind('mouseup', function(event){
                                        document.onmouseup = function(event) {
                                            if (userIncomeGraph) {
                                                if (event.shiftKey) {
                                                    return;
                                                }
                                                var p1 = {
                                                    "year" : self.selected.year,
                                                    "userIncome" : Math
                                                            .ceil(self.selected.userIncome / 500) * 500
                                                };
                                                
                                                if (p1.year < tempYears[tempYearsLength - 2]
                                                        && p1.year >= tempYears[0]) {
                                                    var tempYearsIndex = tempYears
                                                            .indexOf(p1.year);
                                                    if (tempYearsIndex > -1) {
                                                        tempData.splice(
                                                                tempYearsIndex,
                                                                1);
                                                        tempData.push(p1);
                                                        tempData.sort(function(
                                                                a, b) {
                                                            return a.year - b.year;
                                                        });
                                                    } else {
                                                        tempYears.push(p1.year);
                                                        tempData.push(p1);
                                                        tempData.sort(function(
                                                                a, b) {
                                                            return a.year - b.year;
                                                        });
                                                        tempYears
                                                                .sort(function(
                                                                        a, b) {
                                                                    return a - b;
                                                                });
                                                    }

                                                    self.vis.selectAll(
                                                            "svg#expenseSVG")
                                                            .remove();
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#userIncomeSVG")
                                                            .remove();
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#spouseIncomeSVG")
                                                            .remove();
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#combinedIncomeSVG")
                                                            .remove();
                                                    self.vis
                                                    .selectAll(
                                                            "svg#taxSVG")
                                                    .remove();

                                                    self.vis
                                                            .append("svg")
                                                            .attr("top", 0)
                                                            .data(tempData)
                                                            .attr(
                                                                    "cx",
                                                                    function(d) {
                                                                        return d.year;
                                                                    })
                                                            .attr(
                                                                    "cy",
                                                                    function(d) {
                                                                        return d.userIncome;
                                                                    })
                                                            .attr("left", 0)
                                                            .attr(
                                                                    "width",
                                                                    self.size.width)
                                                            .attr(
                                                                    "height",
                                                                    self.size.height)
                                                            .attr(
                                                                    "viewBox",
                                                                    "0 0 "
                                                                            + self.size.width
                                                                            + " "
                                                                            + self.size.height)
                                                            .attr("id",
                                                                    "userIncomeSVG")
                                                            .append("path")
                                                            .attr("class",
                                                                    "line")
                                                            .attr("id",
                                                                    "userIncome")
                                                            .attr(
                                                                    "d",
                                                                    self
                                                                            .dynamiclineUser(tempData))
                                                            .style("cursor",
                                                                    "pointer")
                                                                    .on(
                                                    "mousemove",
                                                    function(d) {
                                                    	$scope.mousemoveFlag = false;
                                                        div.transition().duration(200)
                                                                .style("opacity", .9)
                                                                .style("display",
                                                                        "visible");

                                                        var p2 = {
                                                            "year" : Number(self.x
                                                                    .invert(
                                                                            Math
                                                                                    .max(
                                                                                            0,
                                                                                            Math
                                                                                                    .min(
                                                                                                            self.size.width,
                                                                                                            d3
                                                                                                                    .mouse(self.vis
                                                                                                                            .node())[0])))
                                                                    .toFixed(0)),
                                                            "userIncome" : Number(self.y.invert(
                                                                    Math.max(0, Math.min(self.size.width,
                                                                            d3.mouse(self.vis.node())[1])))
                                                                    .toFixed(0))
                                                        };
                                                        var myPoint = data.map(function(obj) {
                                                    		return obj.year;
                                                        }).indexOf(p2.year);
                                                        
                                                        if(myPoint > -1 ) {
                                                        	if ($scope.marital_status == "Yes") {
                                                        		div.html(
                                                            	"<table><thead><tr><td colspan=3><strong class=x-value>" + p2.year + "</strong></td></tr></thead><tbody>" +
                                                            	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                                .style("left",(d3.event.pageX) + "px")
                                                                .style("top",(d3.event.pageY - 28)+ "px");
                                                        	} else {
                                                        		div.html(
                                                            	"<table><thead><tr><td colspan=3><strong class=x-value>" + p2.year + "</strong></td></tr></thead><tbody>" +
                                                            	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                            	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>")
                                                                .style("left",(d3.event.pageX) + "px")
                                                                .style("top",(d3.event.pageY - 28)+ "px");
                                                        	}
                                                        	
                                                        }

                                                    }).on(
                                                    "mouseout",
                                                    function(d) {
                                                    	$scope.mousemoveFlag = true;
                                                        div.transition().duration(200)
                                                                .style("opacity", 0);
                                                    })
                                                            .on(
                                                                    "mousedown",
                                                                    function() { userClicked = true; spouseClicked = false;
                                                                        self
                                                                                .click("user");
                                                                    });

                                                    self.vis
                                                            .select("path")
                                                            .attr(
                                                                    "d",
                                                                    self
                                                                            .dynamiclineUser(tempData));
                                                    var circle = self.vis
                                                            .select(
                                                                    "svg#userIncomeSVG")
                                                            .selectAll("circle")
                                                            .data(
                                                                    tempData,
                                                                    function(d) {
                                                                        return d;
                                                                    });
                                                    circle
                                                            .enter()
                                                            .append("circle")
                                                            .attr("id",
                                                                    "userCircle")
                                                            .style("stroke",
                                                                    "#328de4")
                                                            .attr(
                                                                    "cx",
                                                                    function(d) {
                                                                        return self
                                                                                .x(d.year);
                                                                    })
                                                            .attr(
                                                                    "cy",
                                                                    function(d) {
                                                                        return self
                                                                                .y(d.userIncome);
                                                                    })
                                                            .attr("r", 4.0)
                                                            .on(
                                                                    "mouseover",
                                                                    function(d) {
                                                                        div
                                                                                .transition()
                                                                                .duration(
                                                                                        200)
                                                                                .style(
                                                                                        "opacity",
                                                                                        .9)
                                                                                .style(
                                                                                        "display",
                                                                                        "visible");
                                                                        var p2 = {
                                                                                "year" : Number(self.x
                                                                                        .invert(
                                                                                                Math
                                                                                                        .max(
                                                                                                                0,
                                                                                                                Math
                                                                                                                        .min(
                                                                                                                                self.size.width,
                                                                                                                                d3
                                                                                                                                        .mouse(self.vis
                                                                                                                                                .node())[0])))
                                                                                        .toFixed(0)),
                                                                                "userIncome" : Number(self.y.invert(
                                                                                        Math.max(0, Math.min(self.size.width,
                                                                                                d3.mouse(self.vis.node())[1])))
                                                                                        .toFixed(0))
                                                                            };
                                                                        var myPoint = data.map(function(obj) {
                                                                    		return obj.year;
                                                                        }).indexOf(p2.year);
                                                                        
                                                                        if(myPoint > -1 ) {
                                                                        	if ($scope.marital_status == "Yes") {
                                                                            	div.html(
                                                                            	"<table><thead><tr><td colspan=3><strong class=x-value>" + p2.year + "</strong></td></tr></thead><tbody>" +
                                                                            	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                                            	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                                            	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                                            	"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                                            	"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                                                .style("left",(d3.event.pageX) + "px")
                                                                                .style("top",(d3.event.pageY - 28)+ "px");
                                                                            } else {
                                                                            	div.html(
                                                                            	"<table><thead><tr><td colspan=3><strong class=x-value>" + p2.year + "</strong></td></tr></thead><tbody>" +
                                                                            	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                                            	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                                            	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr></tbody></table>")
                                                                                .style("left",(d3.event.pageX) + "px")
                                                                                .style("top",(d3.event.pageY - 28)+ "px");
                                                                            }
                                                                        }
                                                                    })
                                                            .on(
                                                                    "mouseout",
                                                                    function(d) {
                                                                        div
                                                                                .transition()
                                                                                .duration(
                                                                                        200)
                                                                                .style(
                                                                                        "opacity",
                                                                                        0);
                                                                    })
                                                            .style("cursor",
                                                                    "ns-resize")
                                                            .on(
                                                                    "mousedown.drag",
                                                                    self
                                                                            .datapoint_dragUser())
                                                            .on(
                                                                    "touchstart.drag",
                                                                    self
                                                                            .datapoint_dragUser());

                                                    circle
                                                            .style("opacity", 1)
                                                            .style("stroke",
                                                                    "#328de4")
                                                            .attr(
                                                                    "cx",
                                                                    function(d) {
                                                                        return self
                                                                                .x(d.year);
                                                                    })
                                                            .attr(
                                                                    "cy",
                                                                    function(d) {
                                                                        return self
                                                                                .y(d.userIncome);
                                                                    });

                                                    circle.exit().remove();

                                                    if (d3.event
                                                            && d3.event.keyCode) {
                                                        d3.event
                                                                .preventDefault();
                                                        d3.event
                                                                .stopPropagation();
                                                    }
                                                    if (drag.indexOf(p1.year) == -1) {
                                                        drag.push(p1.year);
                                                        drag
                                                                .sort(function(
                                                                        a, b) {
                                                                    return a
                                                                            - b;
                                                                });
                                                    }

                                                    $scope.modifiedchartIncome = [];
                                                    $scope.modifiedchartIncome = data;
                                                    var leftIndex = drag
                                                            .indexOf(p1.year) - 1;
                                                    if (leftIndex == -1)
                                                        leftIndex = 0;
                                                    var rightIndex = drag
                                                            .indexOf(p1.year) + 1;
                                                    
                                                    var leftPoint = drag[leftIndex];
                                                    var rightPoint = drag[rightIndex];

                                                    var initValue = tempData[leftIndex].userIncome;

                                                    var diff = p1.year
                                                            - leftPoint;
                                                    var diff1 = rightPoint
                                                            - p1.year;

                                                    var interpolateLeft = ((p1.userIncome - initValue) / diff)
                                                            .toFixed(0);

                                                    var changedpoint = data
                                                            .map(
                                                                    function(
                                                                            obj) {
                                                                        return obj.year;
                                                                    }).indexOf(
                                                                    p1.year);
                                                    var leftStart = data
                                                            .map(
                                                                    function(
                                                                            obj) {
                                                                        return obj.year;
                                                                    }).indexOf(
                                                                    leftPoint);

                                                    if (tempYears
                                                            .indexOf(p1.year) == 0) {
                                                        if ($scope.marital_status == "Yes") {
                                                            $scope.modifiedchartIncome[0] = {
                                                                "year" : p1.year,
                                                                "userIncome" : Math
                                                                        .ceil(p1.userIncome * 1),
                                                                "totalExpense" : data[0].totalExpense,
                                                                "spouseIncome" : data[0].spouseIncome,
                                                                "combinedIncome" : data[0].combinedIncome,
                                                                "tax" : data[0].tax
                                                            };
                                                        } else {
                                                            $scope.modifiedchartIncome[0] = {
                                                                "year" : p1.year,
                                                                "userIncome" : Math
                                                                        .ceil(p1.userIncome * 1),
                                                                "totalExpense" : data[0].totalExpense,
                                                                "tax" : data[0].tax
                                                            };
                                                        }
                                                    } else {
                                                        $scope.modifiedchartIncome[0] = data[0];
                                                    }

                                                    if ($scope.marital_status == "Yes") {
                                                        for (i = leftStart + 1; i <= changedpoint; i++) {
                                                            $scope.modifiedchartIncome[i] = {
                                                                "year" : data[i].year,
                                                                "userIncome" : Math
                                                                        .ceil($scope.modifiedchartIncome[i - 1].userIncome
                                                                                * 1
                                                                                + interpolateLeft
                                                                                * 1),
                                                                "spouseIncome" : data[i].spouseIncome,
                                                                "combinedIncome" : data[i].combinedIncome,
                                                                "totalExpense" : data[i].totalExpense,
                                                                "tax" : data[i].tax
                                                            };
                                                        }
                                                    } else {
                                                        for (i = leftStart + 1; i <= changedpoint; i++) {

                                                            $scope.modifiedchartIncome[i] = {
                                                                "year" : data[i].year,
                                                                "userIncome" : Math
                                                                        .ceil($scope.modifiedchartIncome[i - 1].userIncome
                                                                                * 1
                                                                                + interpolateLeft
                                                                                * 1),
                                                                "totalExpense" : data[i].totalExpense,
                                                                "tax" : data[i].tax
                                                            };
                                                        }
                                                    }

                                                    var changedpoint2 = data
                                                            .map(
                                                                    function(
                                                                            obj) {
                                                                        return obj.year;
                                                                    }).indexOf(
                                                                    rightPoint);
                                                    
                                                    var changedpoint2Value = data[changedpoint2].userIncome;

                                                    var interpolateRight = ((p1.userIncome - changedpoint2Value) / diff1)
                                                            .toFixed(0);

                                                    if ($scope.marital_status == "Yes") {
                                                        for (i = changedpoint + 1; i < changedpoint2; i++) {
                                                            $scope.modifiedchartIncome[i] = {
                                                                "year" : data[i].year,
                                                                "userIncome" : Math
                                                                        .ceil($scope.modifiedchartIncome[i - 1].userIncome
                                                                                * 1
                                                                                - interpolateRight
                                                                                * 1),
                                                                "spouseIncome" : data[i].spouseIncome,
                                                                "combinedIncome" : data[i].combinedIncome,
                                                                "totalExpense" : data[i].totalExpense,
                                                                "tax" : data[i].tax
                                                            };
                                                        }
                                                    } else {
                                                        for (i = changedpoint + 1; i < changedpoint2; i++) {
                                                            $scope.modifiedchartIncome[i] = {
                                                                "year" : data[i].year,
                                                                "userIncome" : Math
                                                                        .ceil($scope.modifiedchartIncome[i - 1].userIncome
                                                                                * 1
                                                                                - interpolateRight
                                                                                * 1),
                                                                "totalExpense" : data[i].totalExpense,
                                                                "tax" : data[i].tax
                                                            };
                                                        }
                                                    }

                                                    if ($scope.marital_status == "Yes") {
                                                        for (var i = leftStart + 1; i <= changedpoint; i++) {
                                                            $scope.modifiedchartIncome[i].combinedIncome = $scope.modifiedchartIncome[i].userIncome
                                                                    + $scope.modifiedchartIncome[i].spouseIncome;
                                                        }
                                                        for (var i = changedpoint + 1; i < changedpoint2; i++) {
                                                            $scope.modifiedchartIncome[i].combinedIncome = $scope.modifiedchartIncome[i].userIncome
                                                                    + $scope.modifiedchartIncome[i].spouseIncome;
                                                        }
                                                    }

                                                }

                                                $scope.mousemoveFlag = true;
                                                self.selected = [];
                                                //console.log($scope.modifiedchartIncome);
                                            }
                                        };
                                    }
                                }
                            };
                            
                        };

                        /* Drag spouseIncome line */
                        SimpleGraph.prototype.datapoint_dragSpouse = function() {
                            self = this;
                            return function(d) {
                                if (d3.event.shiftKey) {
                                    if (d3.event.defaultPrevented)
                                        return;

                                    var beforeRoundIncome = Number(self.y
                                            .invert(
                                                    Math
                                                            .max(
                                                                    0,
                                                                    Math
                                                                            .min(
                                                                                    self.size.width,
                                                                                    d3
                                                                                            .mouse(self.vis
                                                                                                    .node())[1])))
                                            .toFixed(0));
                                    var p1 = {
                                        "year" : Number(self.x.invert(
                                                Math.max(0, Math.min(
                                                        self.size.width,
                                                        d3.mouse(self.vis
                                                                .node())[0])))
                                                .toFixed(0)),
                                        "spouseIncome" : Math
                                                .ceil(beforeRoundIncome / 500) * 500
                                    };

                                    if (tempYears1.includes(p1.year)) {
                                        var selectedIndex = 0;
                                        var tempYears1Length = tempYears1.length;
                                        if (p1.year < tempYears1[tempYears1Length - 2]
                                                && p1.year > tempYears1[0]) {
                                            selectedIndex = tempYears1
                                                    .indexOf(p1.year);

                                            var leftIndex = selectedIndex - 1;
                                            if (leftIndex == -1)
                                                leftIndex = 0;
                                            var leftYear = Number(tempData1[leftIndex].year);
                                            var leftIncome = Number(tempData1[leftIndex].spouseIncome);

                                            var rightIndex = leftIndex + 2;
                                            var rightYear = Number(tempData1[rightIndex].year);
                                            var rightIncome = Number(tempData1[rightIndex].spouseIncome);

                                            // Find the start and end year index
                                            // from data array.
                                            var leftStart = data.map(
                                                    function(obj) {
                                                        return obj.year;
                                                    }).indexOf(leftYear);
                                            var rightEnd = data.map(
                                                    function(obj) {
                                                        return obj.year;
                                                    }).indexOf(rightYear);

                                            if (tempYears1.length == 4) {
                                                rightYear = Number(tempData1[rightIndex].year) - 1;
                                                rightIncome = $scope.dataSpouseIncome;
                                            }

                                            // Formula for finding step
                                            var diff = (rightIncome - leftIncome)
                                                    / (rightYear - leftYear);

                                            for (var i = leftStart + 1, j = 1; i < rightEnd; i++, j++) {
                                                $scope.modifiedchartIncomeSpouse[i].spouseIncome = Math
                                                        .ceil(leftIncome + diff
                                                                * j);
                                                data[i].spouseIncome = Math
                                                        .ceil(leftIncome + diff
                                                                * j);
                                            }
                                            if ($scope.marital_status == "Yes") {
                                                for (var i = leftStart + 1; i < rightEnd; i++) {
                                                    $scope.modifiedchartIncomeSpouse[i].combinedIncome = $scope.modifiedchartIncomeSpouse[i].userIncome
                                                            + $scope.modifiedchartIncomeSpouse[i].spouseIncome;
                                                }
                                            }
                                            drag1.splice(selectedIndex, 1);
                                            tempYears1.splice(selectedIndex, 1);
                                            tempData1.splice(selectedIndex, 1);
                                        }
                                    }
                                    self.vis.selectAll("svg#spouseIncomeSVG")
                                            .remove();
                                    self.vis.selectAll("svg#expenseSVG")
                                            .remove();
                                    self.plotSpouse();
                                    self.plotPointsSpouse();
                                    div.transition().duration(200).style(
                                            "opacity", 0);
                                } else {
                                	self.vis.selectAll(
                                    "svg#expenseSVG")
                                    .remove();
                            self.vis
                                    .selectAll(
                                            "svg#userIncomeSVG")
                                    .remove();
                            self.vis
                                    .selectAll(
                                            "svg#combinedIncomeSVG")
                                    .remove();
                            self.vis
                            .selectAll(
                                    "svg#taxSVG")
                            .remove();
                                    $scope.mousemoveFlag = false;
                                    registerKeyboardHandler(self.keydown());
                                    document.onselectstart = function() {
                                        return false;
                                    };
                                    self.selected1 = d;
                                    var tempYears1Length = tempYears1.length;
                                    if (self.selected1.year < tempYears1[tempYears1Length - 2]
                                            && self.selected1.year >= tempYears1[0]) {
                                        self.dragged1 = self.selected1;
                                        //angular.element(document).bind('mouseup', function(event){
                                        document.onmouseup = function(event) {
                                            if (spouseIncomeGraph) {
                                                if (event.shiftKey) {
                                                    return;
                                                }
                                                var p1 = {
                                                    "year" : self.selected1.year,
                                                    "spouseIncome" : Math
                                                            .ceil(self.selected1.spouseIncome / 500) * 500
                                                };

                                                if (p1.year < tempYears1[tempYears1Length - 2]
                                                        && p1.year >= tempYears1[0]) {
                                                    var tempYears1Index = tempYears1
                                                            .indexOf(p1.year);
                                                    if (tempYears1Index > -1) {
                                                        tempData1
                                                                .splice(
                                                                        tempYears1Index,
                                                                        1);
                                                        tempData1.push(p1);
                                                        tempData1
                                                                .sort(function(
                                                                        a, b) {
                                                                    return a.year
                                                                            - b.year;
                                                                });
                                                    } else {
                                                        tempYears1
                                                                .push(p1.year);
                                                        tempData1.push(p1);
                                                        tempData1
                                                                .sort(function(
                                                                        a, b) {
                                                                    return a.year
                                                                            - b.year;
                                                                });
                                                        tempYears1
                                                                .sort(function(
                                                                        a, b) {
                                                                    return a
                                                                            - b;
                                                                });
                                                    }
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#spouseIncomeSVG")
                                                            .remove();
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#userIncomeSVG")
                                                            .remove();
                                                    self.vis.selectAll(
                                                            "svg#expenseSVG")
                                                            .remove();
                                                    self.vis
                                                            .selectAll(
                                                                    "svg#combinedIncomeSVG")
                                                            .remove();
                                                    self.vis
                                                    .selectAll(
                                                            "svg#taxSVG")
                                                    .remove();

                                                    self.vis
                                                            .append("svg")
                                                            .attr("top", 0)
                                                            .data(tempData1)
                                                            .attr(
                                                                    "cx",
                                                                    function(d) {
                                                                        return d.year;
                                                                    })
                                                            .attr(
                                                                    "cy",
                                                                    function(d) {
                                                                        return d.spouseIncome;
                                                                    })
                                                            .attr("left", 0)
                                                            .attr(
                                                                    "width",
                                                                    self.size.width)
                                                            .attr(
                                                                    "height",
                                                                    self.size.height)
                                                            .attr(
                                                                    "viewBox",
                                                                    "0 0 "
                                                                            + self.size.width
                                                                            + " "
                                                                            + self.size.height)
                                                            .attr("id",
                                                                    "spouseIncomeSVG")
                                                            .append("path")
                                                            .attr("class",
                                                                    "line2")
                                                            .attr("id",
                                                                    "spouseIncome")
                                                            .attr(
                                                                    "d",
                                                                    self
                                                                            .dynamiclineSpouse(tempData1))
                                                            .style("cursor",
                                                                    "pointer")
                                                                    .on(
                                                    "mousemove",
                                                    function(d) {
                                                    	$scope.mousemoveFlag = false;
                                                        div.transition().duration(200)
                                                                .style("opacity", .9)
                                                                .style("display",
                                                                        "visible");
                                                        
                                                        var p2 = {
                                                                "year" : Number(self.x
                                                                        .invert(
                                                                                Math
                                                                                        .max(
                                                                                                0,
                                                                                                Math
                                                                                                        .min(
                                                                                                                self.size.width,
                                                                                                                d3
                                                                                                                        .mouse(self.vis
                                                                                                                                .node())[0])))
                                                                        .toFixed(0)),
                                                                "spouseIncome" : Number(self.y.invert(
                                                                        Math.max(0, Math.min(self.size.width,
                                                                                d3.mouse(self.vis.node())[1])))
                                                                        .toFixed(0))
                                                            };
                                                        
                                                        var myPoint = data.map(function(obj) {
                                                    		return obj.year;
                                                        }).indexOf(p2.year);
                                                        
                                                        if(myPoint > -1 ) {
                                                        	div.html(
                                                        	"<table><thead><tr><td colspan=3><strong class=x-value>" + p2.year + "</strong></td></tr></thead><tbody>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                        	"<tr style='border-top: 1px solid #32e2e2;border-bottom: 1px solid #32e2e2;'><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                        	"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                            .style("left",(d3.event.pageX) + "px")
                                                            .style("top",(d3.event.pageY - 28)+ "px");
                                                        }

                                                    }).on(
                                                    "mouseout",
                                                    function(d) {
                                                    	$scope.mousemoveFlag = true;
                                                        div.transition().duration(200)
                                                                .style("opacity", 0);
                                                    })
                                                            .on(
                                                                    "mousedown",
                                                                    function() { userClicked = false; spouseClicked = true;
                                                                        self
                                                                                .click("spouse");
                                                                    });

                                                    self.vis
                                                            .select("path")
                                                            .attr(
                                                                    "d",
                                                                    self
                                                                            .dynamiclineSpouse(tempData1));

                                                    var circle = self.vis
                                                            .select(
                                                                    "svg#spouseIncomeSVG")
                                                            .selectAll("circle")
                                                            .data(
                                                                    tempData1,
                                                                    function(d) {
                                                                        return d;
                                                                    });
                                                    circle
                                                            .enter()
                                                            .append("circle")
                                                            .attr("id",
                                                                    "spouseCircle")
                                                            .style("stroke",
                                                                    "#32e2e2")
                                                            .attr(
                                                                    "cx",
                                                                    function(d) {
                                                                        return self
                                                                                .x(d.year);
                                                                    })
                                                            .attr(
                                                                    "cy",
                                                                    function(d) {
                                                                        return self
                                                                                .y(d.spouseIncome);
                                                                    })
                                                            .attr("r", 4.0)
                                                            .on(
                                                                    "mouseover",
                                                                    function(d) {
                                                                        div
                                                                                .transition()
                                                                                .duration(
                                                                                        200)
                                                                                .style(
                                                                                        "opacity",
                                                                                        .9)
                                                                                .style(
                                                                                        "display",
                                                                                        "visible");
                                                                        var p2 = {
                                                                                "year" : Number(self.x
                                                                                        .invert(
                                                                                                Math
                                                                                                        .max(
                                                                                                                0,
                                                                                                                Math
                                                                                                                        .min(
                                                                                                                                self.size.width,
                                                                                                                                d3
                                                                                                                                        .mouse(self.vis
                                                                                                                                                .node())[0])))
                                                                                        .toFixed(0)),
                                                                                "spouseIncome" : Number(self.y.invert(
                                                                                        Math.max(0, Math.min(self.size.width,
                                                                                                d3.mouse(self.vis.node())[1])))
                                                                                        .toFixed(0))
                                                                            };
                                                                        var myPoint = data.map(function(obj) {
                                                                    		return obj.year;
                                                                        }).indexOf(p2.year);
                                                                        
                                                                        if(myPoint > -1 ) {
                                                                        	div.html(
                                                                        	"<table><thead><tr><td colspan=3><strong class=x-value>" + p2.year + "</strong></td></tr></thead><tbody>" +
                                                                        	"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                                                                        	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                                        	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                                        	"<tr style='border-top: 1px solid #32e2e2;border-bottom: 1px solid #32e2e2;'><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                                        	"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                                            .style("left",(d3.event.pageX) + "px")
                                                                            .style("top",(d3.event.pageY - 28)+ "px");
                                                                        }
                                                                    })
                                                            .on(
                                                                    "mouseout",
                                                                    function(d) {
                                                                        div
                                                                                .transition()
                                                                                .duration(
                                                                                        200)
                                                                                .style(
                                                                                        "opacity",
                                                                                        0);
                                                                    })
                                                            .style("cursor",
                                                                    "ns-resize")
                                                            .on(
                                                                    "mousedown.drag",
                                                                    self
                                                                            .datapoint_dragSpouse())
                                                            .on(
                                                                    "touchstart.drag",
                                                                    self
                                                                            .datapoint_dragSpouse());

                                                    circle
                                                            .style("opacity", 1)
                                                            .style("stroke",
                                                                    "#32e2e2")
                                                            .attr(
                                                                    "cx",
                                                                    function(d) {
                                                                        return self
                                                                                .x(d.year);
                                                                    })
                                                            .attr(
                                                                    "cy",
                                                                    function(d) {
                                                                        return self
                                                                                .y(d.spouseIncome);
                                                                    });

                                                    circle.exit().remove();

                                                    if (d3.event
                                                            && d3.event.keyCode) {
                                                        d3.event
                                                                .preventDefault();
                                                        d3.event
                                                                .stopPropagation();
                                                    }

                                                    if (drag1.indexOf(p1.year) == -1) {
                                                        drag1.push(p1.year);
                                                        drag1.sort(function(a,
                                                                b) {
                                                            return a - b;
                                                        });
                                                    }

                                                    $scope.modifiedchartIncomeSpouse = [];
                                                    $scope.modifiedchartIncomeSpouse = data;

                                                    var leftIndex = drag1
                                                            .indexOf(p1.year) - 1;
                                                    if (leftIndex == -1)
                                                        leftIndex = 0;

                                                    var rightIndex = drag1
                                                            .indexOf(p1.year) + 1;

                                                    var leftPoint = drag1[leftIndex];
                                                    var rightPoint = drag1[rightIndex];

                                                    var initValue = tempData1[leftIndex].spouseIncome;

                                                    var diff = p1.year
                                                            - leftPoint;
                                                    var diff1 = rightPoint
                                                            - p1.year;

                                                    var interpolateLeft = ((p1.spouseIncome - initValue) / diff)
                                                            .toFixed(0);

                                                    var changedpoint = data
                                                            .map(
                                                                    function(
                                                                            obj) {
                                                                        return obj.year;
                                                                    }).indexOf(
                                                                    p1.year);
                                                    var leftStart = data
                                                            .map(
                                                                    function(
                                                                            obj) {
                                                                        return obj.year;
                                                                    }).indexOf(
                                                                    leftPoint);

                                                    var startIndex = data
                                                            .map(
                                                                    function(
                                                                            obj) {
                                                                        return obj.year;
                                                                    })
                                                            .indexOf(
                                                                    tempYears1[0]);

                                                    if (tempYears1
                                                            .indexOf(p1.year) == 0) {
                                                        $scope.modifiedchartIncomeSpouse[startIndex] = {
                                                            "year" : p1.year,
                                                            "userIncome" : data[startIndex].userIncome,
                                                            "totalExpense" : data[startIndex].totalExpense,
                                                            "spouseIncome" : Math
                                                                    .ceil(p1.spouseIncome * 1),
                                                            "combinedIncome" : data[startIndex].combinedIncome,
                                                            "tax" : data[startIndex].tax
                                                        };
                                                    } else {
                                                        $scope.modifiedchartIncomeSpouse[startIndex] = data[startIndex];
                                                    }

                                                    for (i = leftStart + 1; i <= changedpoint; i++) {
                                                        $scope.modifiedchartIncomeSpouse[i] = {
                                                            "year" : data[i].year,
                                                            "spouseIncome" : Math
                                                                    .ceil($scope.modifiedchartIncomeSpouse[i - 1].spouseIncome
                                                                            * 1
                                                                            + interpolateLeft
                                                                            * 1),
                                                            "userIncome" : data[i].userIncome,
                                                            "combinedIncome" : data[i].combinedIncome,
                                                            "totalExpense" : data[i].totalExpense,
                                                            "tax" : data[i].tax
                                                        };
                                                    }

                                                    var changedpoint2 = data
                                                            .map(
                                                                    function(
                                                                            obj) {
                                                                        return obj.year;
                                                                    }).indexOf(
                                                                    rightPoint);
                                                    var changedpoint2Value = data[changedpoint2].spouseIncome;

                                                    var interpolateRight = ((p1.spouseIncome - changedpoint2Value) / diff1)
                                                            .toFixed(0);

                                                    for (i = changedpoint + 1; i < changedpoint2; i++) {
                                                        $scope.modifiedchartIncomeSpouse[i] = {
                                                            "year" : data[i].year,
                                                            "spouseIncome" : Math
                                                                    .ceil($scope.modifiedchartIncomeSpouse[i - 1].spouseIncome
                                                                            * 1
                                                                            - interpolateRight
                                                                            * 1),
                                                            "userIncome" : data[i].userIncome,
                                                            "combinedIncome" : data[i].combinedIncome,
                                                            "totalExpense" : data[i].totalExpense,
                                                            "tax" : data[i].tax
                                                        };
                                                    }

                                                    for (var i = leftStart + 1; i <= changedpoint; i++) {
                                                        $scope.modifiedchartIncomeSpouse[i].combinedIncome = $scope.modifiedchartIncomeSpouse[i].userIncome
                                                                + $scope.modifiedchartIncomeSpouse[i].spouseIncome;
                                                    }
                                                    for (var i = changedpoint + 1; i < changedpoint2; i++) {
                                                        $scope.modifiedchartIncomeSpouse[i].combinedIncome = $scope.modifiedchartIncomeSpouse[i].userIncome
                                                                + $scope.modifiedchartIncomeSpouse[i].spouseIncome;
                                                    }
                                                }

                                                $scope.mousemoveFlag = true;
                                                self.selected1 = [];
                                            }
                                        }
                                    }
                                }

                            };
                        };

                        SimpleGraph.prototype.mousemove = function() {
                            self = this;
                            return function() {
                                var p = d3.mouse(self.vis[0][0]), t = d3.event.changedTouches;

                                if (self.dragged) {
                                    self.dragged.userIncome = self.y
                                            .invert(Math.max(0, Math.min(
                                                    self.size.height, p[1])));
                                    self.updateUser();
                                }
                                
                                if (self.dragged1) {
                                    self1.dragged1.spouseIncome = self.y
                                            .invert(Math.max(0, Math.min(
                                                    self.size.height, p[1])));
                                    self.updateSpouse();
                                }
                                
                                if (!isNaN(self.downx) && self.downx>0) {
                                    d3.select("body").style("cursor",
                                            "ew-resize");
                                    var rupx = self.x.invert(p[0]), xaxis1 = self.x
                                            .domain()[0], xaxis2 = self.x
                                            .domain()[1], xextent = xaxis2
                                            - xaxis1;
                                    if (rupx > 0) {
                                        var changex, new_domain;
                                        changex = self.downx / rupx;
                                        new_domain = [ xaxis1,
                                                xaxis1 + (xextent * changex) ];
                                        self.x.domain(new_domain);
                                        self.redraw()();
                                        //self.after_axis_drag();
                                    }
                                    d3.event.preventDefault();
                                    d3.event.stopPropagation();
                                }
                                
                                if (!isNaN(self.downy) && self.downy>0) {
                                    d3.select("body").style("cursor",
                                            "ns-resize");
                                    var rupy = self.y.invert(p[1]), yaxis1 = self.y
                                            .domain()[1], yaxis2 = self.y
                                            .domain()[0], yextent = yaxis2
                                            - yaxis1;
                                    if (rupy > 0) {
                                        var changey, new_domain;
                                        changey = self.downy / rupy;
                                        new_domain = [
                                                yaxis1 + (yextent * changey),
                                                yaxis1 ];
                                        self.y.domain(new_domain);
                                        self.redraw()();
                                        //self.after_axis_drag();
                                    }
                                    d3.event.preventDefault();
                                    d3.event.stopPropagation();
                                }
                            };
                        };

                        SimpleGraph.prototype.mouseup = function() {
                            self = this;
                            return function() {
                                document.onselectstart = function() {
                                    return true;
                                };
                                d3.select("body").style("cursor", "auto");
                                d3.select("body").style("cursor", "auto");
                                if (!isNaN(self.downx) && self.downx>0) {
                                    self.redraw()();
                                    self.downx = Math.NaN;
                                    d3.event.preventDefault();
                                    d3.event.stopPropagation();
                                }
                                
                                if (!isNaN(self.downy) && self.downy>0) {
                                    self.redraw()();
                                    self.downy = Math.NaN;
                                    d3.event.preventDefault();
                                    d3.event.stopPropagation();
                                }
                                if (self.dragged) {
                                    self.dragged = null;
                                }
                                if (self.dragged1) {
                                    self.dragged1 = null;
                                }
                            }
                        };

                        SimpleGraph.prototype.keydown = function() {
                            self = this;
                            return function() {
                                if (!self.selected)
                                    return;
                            };
                        };

                        SimpleGraph.prototype.redraw = function() {
                            self = this;
                            return function() {
                                var tx = function(d) {
                                    return "translate( " + self.x(d) + ",0 )";
                                }, ty = function(d) {
                                    return "translate( 0," + self.y(d) + " )";
                                }, stroke = function(d) {
                                    return d ? "#ccc" : "#666";
                                }, fx = self.x.tickFormat(d3.format("d")), fy = self.y
                                        .tickFormat(d3.format("d"));

                                var gx = self.vis.selectAll("g.x").data(
                                        self.x.ticks(20)).attr("transform", tx);

                                gx.select("text").text(fx);

                                var gxe = gx.enter().insert("g", "a").attr(
                                        "class", "x").attr("transform", tx);

                                gxe.append("line").attr("stroke", stroke).attr(
                                        "y1", 0).attr("y2", self.size.height);

                                gxe.append("text").attr("class", "axis").attr(
                                        "y", self.size.height)
                                        .attr("dy", "1em").attr("text-anchor",
                                                "middle").text(fx).style(
                                                "cursor", "ew-resize")/*.on(
                                                "mouseout",
                                                self.after_axis_drag)*/.on(
                                                "mousedown.drag",
                                                self.xaxis_drag()).on(
                                                "touchstart.drag",
                                                self.xaxis_drag());

                                gx.exit().remove();

                                var gy = self.vis.selectAll("g.y").data(
                                        self.y.ticks(20)).attr("transform", ty);

                                gy.select("text").text(fy);

                                var gye = gy.enter().insert("g", "a").attr(
                                        "class", "y").attr("transform", ty);

                                gye.append("line").attr("stroke", stroke).attr(
                                        "x1", 0).attr("x2", self.size.width);

                                gye.append("text").attr("class", "axis").attr(
                                        "x", -3).attr("dy", ".35em").attr(
                                        "text-anchor", "end").text(fy).style(
                                        "cursor", "ns-resize")
                                /*.on("mouseout", self.after_axis_drag)*/.on(
                                        "mousedown.drag", self.yaxis_drag())
                                        .on("touchstart.drag",
                                                self.yaxis_drag());

                                gy.exit().remove();
                                self.redrawGraph();
                                
                                /*if (self.dragged != null)
                                    self.updateUser();
                                if (self.dragged1 != null)
                                    self.updateSpouse();*/
                                
                                
                            };

                        };
                        
                        SimpleGraph.prototype.redrawGraph = function() {            
                        	self.vis.selectAll("svg#userIncomeSVG").remove();
                        	self.vis.selectAll("svg#expenseSVG").remove();
                        	self.vis.selectAll("svg#spouseIncomeSVG").remove();
                        	self.vis.selectAll("svg#combinedIncomeSVG").remove();
                        	self.plotUser();
	                     	self.plotPointsUser();
	                     	self.plotExpense();

	                     	if ($scope.marital_status == "Yes") {	                     		
			                    self.plotSpouse();		                    			                    
			                    self.plotCombined();
	                     	}
	                     	
                        	if ( totalUserIncomeCalculated < 1 )
                        	{
                        		self.vis.selectAll("svg#expenseSVG").remove();
                        		if ($scope.marital_status == "Yes") {
                        			self.vis.selectAll("svg#combinedIncomeSVG").remove();
                        		}
                        	}
		                                         	
                        }
                        
                        SimpleGraph.prototype.after_axis_drag = function() {
                            if (userIncomeGraph==true && spouseIncomeGraph == false
                                    && combinedIncomeGraph == false
                                    && expenseIncomeGraph == false && taxGraph == false && userClicked == true) {
                                self.vis.selectAll("svg#userIncomeSVG")
                                        .remove();
                                self.plotUser();
                                self.plotPointsUser();
                            } else if (userIncomeGraph==false && spouseIncomeGraph == true
                                    && combinedIncomeGraph == false
                                    && expenseIncomeGraph == false && taxGraph == false && userClicked == false) {
                                self.vis.selectAll("svg#spouseIncomeSVG")
                                        .remove();
                                self.plotSpouse();
                                self.plotPointsSpouse();
                            } else if (userIncomeGraph==false && spouseIncomeGraph == false
                                    && combinedIncomeGraph == true
                                    && expenseIncomeGraph == false && taxGraph == false && userClicked == false) {
                                self.vis.selectAll("svg#combinedIncomeSVG")
                                        .remove();
                                self.plotCombined();
                                self.plotPointsCombined();
                            } else if (userIncomeGraph==false && spouseIncomeGraph == false
                                    && combinedIncomeGraph == false
                                    && expenseIncomeGraph == true && taxGraph == false && userClicked == false) {
                                self.vis.selectAll("svg#expenseSVG").remove();
                                self.plotExpense();
                                self.plotPointsExpense();
                            } /*else if (userIncomeGraph==false && spouseIncomeGraph == false
                                    && combinedIncomeGraph == false
                                    && expenseIncomeGraph == false && taxGraph == true && userClicked == false) {
                                self.vis.selectAll("svg#taxSVG").remove();
                                self.plotTax();
                                self.plotPointsTax();
                            }*/ else if (userIncomeGraph == true
                                    && spouseIncomeGraph == true
                                    && combinedIncomeGraph == true
                                    && expenseIncomeGraph == true && taxGraph == true && userClicked == false) {
                            	self.vis.selectAll("svg#userIncomeSVG")
                                .remove();
                                self.plotUser();
                                self.plotPointsUser();
                                
                                self.vis.selectAll("svg#expenseSVG").remove();
                                self.plotExpense();
                                
                                self.vis.selectAll("svg#spouseIncomeSVG")
		                        .remove();
			                    self.plotSpouse();
			                    
                                self.vis.selectAll("svg#combinedIncomeSVG")
	                            .remove();
			                    self.plotCombined();            
                                
                                /*self.vis.selectAll("svg#taxSVG").remove();
                                self.plotTax();*/
                            }
                        };

                        SimpleGraph.prototype.xaxis_drag = function() {
                            self = this;
                            return function(d) {
                                document.onselectstart = function() {
                                    return false;
                                };
                                var p = d3.mouse(self.vis[0][0]);
                                self.downx = self.x.invert(p[0]);
                            };
                        };

                        SimpleGraph.prototype.yaxis_drag = function() {
                            self = this;
                            return function(d) {
                                document.onselectstart = function() {
                                    return false;
                                };
                                var p = d3.mouse(self.vis[0][0]);
                                self.downy = self.y.invert(p[1]);
                            };
                        };

                        if ($scope.incomeStartYear % 2 == 0)
                            a = $scope.incomeStartYear - 2;
                        else
                            a = $scope.incomeStartYear - 1;

                        b = $scope.incomeEndYear + 2;

                        onload(a, b);

                        function onload(a, b) {
                            graph = new SimpleGraph("d3-chart-container1", {
                                "xmax" : b,
                                "xmin" : a,
                                "ymax" : maxY,
                                "ymin" : 0,
                                "title" : "",
                                "xlabel" : "Year",
                                "ylabel" : "Income and Expenses"
                            });

                        }

                        registerKeyboardHandler = function(callback) {
                            var callback = callback;
                            d3.select(window).on("keydown", callback);
                        };
                        
                        /* nvd3 chart for Area chart implementation */

                        $scope.DrawTaxesAreaChart();
                        $scope.DrawAssetsAreaChart();
                        // $scope.drawExpenseChart();

                    };


                    $scope.DrawTaxesAreaChart = function() {

                        var firstPie = {};
                        var secondPie = {};
                        var count = 0;

                        var parseDate = d3.time.format("%Y").parse;
                        var retirementAge = 0;

                        if (parseInt($scope.retirementYear) < parseInt($scope.spouseretirementYear)
                                && $scope.spouseretirementYear !== undefined) {
                            retirementAge = parseInt($scope.spouseretirementYear);
                        } else {
                            retirementAge = parseInt($scope.retirementYear);
                        }

                        $scope.yearBR = new Array();
                        $scope.yearAR = new Array();

                        if ($scope.Count == 0) {
                            for (var i = 0; i < $scope.tableIncome_imp.length; i++) {
                                firstPie = $scope.tableIncome_imp[0];
                                $scope.yearBR
                                        .push(parseInt($scope.tableIncome_imp[i]["year"]));

                                if (count == 0) {

                                    secondPie = $scope.tableIncome_imp[i];
                                    $scope.startYearAR = parseInt($scope.tableIncome_imp[i].year);
                                }

                                $scope.yearAR
                                        .push(parseInt($scope.tableIncome_imp[i]["year"]));
                                count++;
                            }
                        } else {

                            for (var i = 0; i < $scope.tableIncome.length; i++) {
                                firstPie = $scope.tableIncome[0];
                                $scope.yearBR
                                        .push(parseInt($scope.tableIncome[i]["year"]));

                                if (count == 0) {

                                    secondPie = $scope.tableIncome[i];
                                    $scope.startYearAR = parseInt($scope.tableIncome[i].year);
                                }

                                $scope.yearAR
                                        .push(parseInt($scope.tableIncome[i]["year"]));
                                count++;
                            }
                        }

                        var firstPiereports = {};
                        var secondPiereports = {};
                        var count = 0;
                        $scope.yearreportsBR = new Array();
                        for (var i = 0; i < $scope.Porfolio_tableIncome.length; i++) {
                            firstPiereports = $scope.Porfolio_tableIncome[0];
                            $scope.yearreportsBR
                                    .push(parseInt($scope.Porfolio_tableIncome[i]["year"]));
                            if (count == 0) {

                            }
                            count++;
                        }
                        $scope.optionsPie = {
                            chart : {
                                type : "pieChart",
                                height : 500,
                                x : function(d) {
                                    return d.key;
                                },
                                y : function(d) {
                                    return d.y;
                                },
                                showLabels : true,
                                duration : 500,
                                labelThreshold : 0.01,
                                labelSunbeamLayout : true,
                                legend : {
                                    margin : {
                                        top : 5,
                                        right : 35,
                                        bottom : 5,
                                        left : 0
                                    }
                                }
                            }
                        };

                        $scope.dataPieChartBR = [];
                        if(firstPie.savings > 0 || firstPie.taxable_investment_amount > 0 || firstPie.nontaxable_investment_amount > 0 || $scope.properties_equity[0].value > 0)
                        {
                        	$scope.dataPieChartBR = [ {
                                key : "Savings",
                                y : firstPie.savings,
                                "color" : "rgb( 205, 21, 51 )"
                            }, {
                                key : "Taxable",
                                y : firstPie.taxable_investment_amount,
                                "color" : "rgb( 63, 81, 181 )"
                            }, {
                                key : "Tax-Advantaged",
                                y : firstPie.nontaxable_investment_amount,
                                "color" : "rgb( 255, 87, 34 )"
                            }, {
                                key : "Property ",
                                y : $scope.properties_equity[0].value,
                                "color" : "rgb( 6, 140, 53 )"
                            } ];
                        }

                        var stateTaxArrBR = new Array();
                        var userfICASocialSecurityTaxBR = new Array();
                        var spousefICASocialSecurityTaxBR = new Array();
                        var fICAMedicareTaxBR = new Array();
                        var federalTaxBR = new Array();
                        var propertyTaxValue=new Array();
                        var parseDate = d3.time.format("%Y").parse;
                        var retirementAge = 0;
                        var maxValuesTaxGraph = [];
                        var propTax = 0;
                        
                        if (parseInt($scope.retirementYear) < parseInt($scope.spouseretirementYear)
                                && $scope.spouseretirementYear !== undefined) {
                            retirementAge = parseInt($scope.spouseretirementYear);
                        } else {
                            retirementAge = parseInt($scope.retirementYear);
                        }
                        if ($scope.Count == 0) {
                            for (var i = 0; i < $scope.tableIncome_imp.length; i++) {
                            	if ($scope.properties_equity == "") {
                                    propertyTaxValue
                                    .push(new Array(
                                            parseDate($scope.tableIncome_imp[i]["year"]),
                                            0));
                                    propTax = 0;
                                } else {
                                    propertyTaxValue
                                    .push(new Array(
                                            parseDate($scope.tableIncome_imp[i]["year"]),
                                            $scope.properties_equity[i].property));
                                    propTax = $scope.properties_equity[i].property;
                                }
                                stateTaxArrBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome_imp[i]["year"]),
                                                $scope.tableIncome_imp[i]["stateTax"]));
                                userfICASocialSecurityTaxBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome_imp[i]["year"]),
                                                ($scope.tableIncome_imp[i]["userfICASocialSecurityTax"]+$scope.tableIncome_imp[i]["spousefICASocialSecurityTax"])));
                                fICAMedicareTaxBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome_imp[i]["year"]),
                                                $scope.tableIncome_imp[i]["fICAMedicareTax"]));
                                federalTaxBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome_imp[i]["year"]),
                                                $scope.tableIncome_imp[i]["federalTax"]));
                                /*spousefICASocialSecurityTaxBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome_imp[i]["year"]),
                                                $scope.tableIncome_imp[i]["spousefICASocialSecurityTax"]));*/
                                
                                maxValuesTaxGraph.push($scope.tableIncome_imp[i]["federalTax"] + 
                                		$scope.tableIncome_imp[i]["fICAMedicareTax"] +
                                		$scope.tableIncome_imp[i]["userfICASocialSecurityTax"]+$scope.tableIncome_imp[i]["spousefICASocialSecurityTax"] +
                                		$scope.tableIncome_imp[i]["stateTax"] + propTax
                                	);
                            }

                        } else {
                            for (var i = 0; i < $scope.tableIncome.length; i++) {
                            	if ($scope.properties_equity == "") {
                                    propertyTaxValue
                                    .push(new Array(
                                            parseDate($scope.tableIncome_imp[i]["year"]),
                                            0));
                                    propTax = 0;
                                } else {
                                    propertyTaxValue
                                    .push(new Array(
                                            parseDate($scope.tableIncome_imp[i]["year"]),
                                            $scope.properties_equity[i].property));
                                    propTax = $scope.properties_equity[i].property;
                                }

                                stateTaxArrBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome[i]["year"]),
                                                $scope.tableIncome[i]["stateTax"]));
                                userfICASocialSecurityTaxBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome[i]["year"]),
                                                ($scope.tableIncome[i]["userfICASocialSecurityTax"]+$scope.tableIncome[i]["spousefICASocialSecurityTax"])));
                                fICAMedicareTaxBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome[i]["year"]),
                                                $scope.tableIncome[i]["fICAMedicareTax"]));
                                federalTaxBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome[i]["year"]),
                                                $scope.tableIncome[i]["federalTax"]));
                                /*spousefICASocialSecurityTaxBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome[i]["year"]),
                                                $scope.tableIncome[i]["spousefICASocialSecurityTax"]));*/
                                
                                maxValuesTaxGraph.push($scope.tableIncome[i]["federalTax"] + 
                                		$scope.tableIncome[i]["fICAMedicareTax"] +
                                		$scope.tableIncome[i]["userfICASocialSecurityTax"]+$scope.tableIncome[i]["spousefICASocialSecurityTax"] +
                                		$scope.tableIncome[i]["stateTax"] + propTax
                                	);
                            }
                        }
                        console.log(federalTaxBR);
                        // alert( JSON.stringify( federalTaxBR ) )
                        $scope.dataTax = [ 
                        {
                            "key" : "Federal",
                            "values" : federalTaxBR,
                            "color" : "rgb( 6, 140, 53 )"
                        },{
                            "key" : "State",
                            "values" : stateTaxArrBR,
                            "color" : "rgb( 205, 21, 51 )"
                        }, {
                            "key" : "Social Security Tax",
                            "values" : userfICASocialSecurityTaxBR,
                            "color" : "rgb( 63, 81, 181 )"
                        }, {
                            "key" : "Medicare",
                            "values" : fICAMedicareTaxBR,
                            "color" : "rgb( 255, 87, 34 )"
                        },/* {
                            "key" : "Spouse Fica",
                            "values" : spousefICASocialSecurityTaxBR,
                            "color" : "#8E44AD"
                        }*/{
                            "key" : "Property Tax ",
                            "values": propertyTaxValue,
                            "color" : "rgb(53, 218, 216)"
                        }

                        ];
                        maxValuesTaxGraph.sort( function( a, b ) {
                            if ( Number( a ) > Number( b ) ) {
                                return -1;
                            } else if ( Number( a ) < Number( b ) ) {
                                return 1;
                            } else {
                                return 0;
                            }
                        } );
                        /*if ($scope.incomeStartYear % 2 == 0)
                            a = $scope.incomeStartYear - 2;
                        else
                            a = $scope.incomeStartYear - 1;*/
                        var tempa = "" + $scope.incomeStartYear + "";
                        var c = parseDate(tempa);
                        b = $scope.incomeEndYear + 2;
                        var tempb = "" + b + "";
                        var d = parseDate(tempb);
                        
                        $scope.optionsTax = {
                                chart : {
                                    showControls : false,
                                    type : "stackedAreaChart",
                                    height : 450,
                                    margin : {
                                        top : 20,
                                        right : 20,
                                        bottom : 30,
                                        left : 40
                                    },
                                    x : function(d) {
                                        return d[0];
                                    },
                                    y : function(d) {
                                        return d[1];
                                    },
                                    forceX: [c, d],
                                    forceY: [0, Math.ceil(maxValuesTaxGraph[0] / 1000) * 1000 ],   
                                    useVoronoi : false,
                                    clipEdge : true,
                                    duration : 100,
                                    useInteractiveGuideline : true,
                                    xAxis : {
                                        showMaxMin : false,
                                        tickFormat : function(d) {
                                            return d3.time.format("%Y")
                                                    (new Date(d))
                                        }
                                    },
                                    yAxis : {
                                        tickFormat : function(d) {
                                            return d3.format(",.2f")(d);
                                        }
                                    },
                                    zoom : {
                                        enabled : true,
                                        scaleExtent : [ 1, 10 ],
                                        useFixedDomain : false,
                                        useNiceScale : false,
                                        horizontalOff : false,
                                        verticalOff : true,
                                        unzoomEventType : "dblclick.zoom"
                                    }
                                }
                            };

                    };
                    $scope.DrawAssetsAreaChart = function() {
                    	
                        var dashBoardProperties = new Array();
                        var savingsBR = new Array();
                        var taxableBR = new Array();
                        var nontaxableBR = new Array();
                        var dashBoardPropertiesTemp = new Array();
                        var maxValuesAssetGraph = new Array();
                        var propMax = 0;
                        var parseDate = d3.time.format("%Y").parse;
                        var retirementAge = 0;
                        if (parseInt($scope.retirementYear) < parseInt($scope.spouseretirementYear)
                                && $scope.spouseretirementYear !== undefined) {
                            retirementAge = parseInt($scope.spouseretirementYear);
                        } else {
                            retirementAge = parseInt($scope.retirementYear);
                        }
                       // console.log($scope.properties_equity)
                        if ($scope.Count == 0) {
                            for (var i = 0; i < $scope.tableIncome_imp.length; i++) {
                                if ($scope.properties_equity == "") {
                                    dashBoardProperties
                                            .push(new Array(
                                                    parseDate($scope.tableIncome_imp[i]["year"]),
                                                    0));
                                    propMax = 0;
                                } else {
                                    dashBoardProperties
                                            .push(new Array(
                                                    parseDate($scope.tableIncome_imp[i]["year"]),
                                                    $scope.properties_equity[i].value));
                                    propMax = $scope.properties_equity[i].value;
                                }

                                savingsBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome_imp[i]["year"]),
                                                $scope.tableIncome_imp[i]["savings"]));
                                
                                taxableBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome_imp[i]["year"]),
                                                $scope.tableIncome_imp[i]["taxable_investment_amount"]));
                                
                                nontaxableBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome_imp[i]["year"]),
                                                $scope.tableIncome_imp[i]["nontaxable_investment_amount"]));
                                
                                maxValuesAssetGraph.push( $scope.tableIncome_imp[i]["nontaxable_investment_amount"] + 
                                		$scope.tableIncome_imp[i]["taxable_investment_amount"] + 
                                		$scope.tableIncome_imp[i]["savings"] + propMax
                                		);
                            }

                        } else {
                            for (var i = 0; i < $scope.tableIncome.length; i++) {
                                if ($scope.properties_equity == "") {
                                    dashBoardProperties
                                            .push(new Array(
                                                    parseDate($scope.tableIncome[i]["year"]),
                                                    0));
                                    propMax = 0;
                        
                                } else {
                                    dashBoardProperties
                                            .push(new Array(
                                                    parseDate($scope.tableIncome[i]["year"]),
                                                    $scope.properties_equity[i].value));
                                    propMax = $scope.properties_equity[i].value;
                                }

                                savingsBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome[i]["year"]),
                                                $scope.tableIncome[i]["savings"]));
                                
                                taxableBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome[i]["year"]),
                                                $scope.tableIncome[i]["taxable_investment_amount"]));
                                
                                nontaxableBR
                                        .push(new Array(
                                                parseDate($scope.tableIncome[i]["year"]),
                                                $scope.tableIncome[i]["nontaxable_investment_amount"]));
                                
                                maxValuesAssetGraph.push( $scope.tableIncome[i]["nontaxable_investment_amount"] + 
                                		$scope.tableIncome[i]["taxable_investment_amount"] + 
                                		$scope.tableIncome[i]["savings"] + propMax
                                		);
                            }
                        }
                        // alert( JSON.stringify( dashBoardProperties ) )

                        $scope.dataBRAsset = [ {
                            "key" : "Savings",
                            "values" : savingsBR,
                            "color" : "rgb( 205, 21, 51 )"
                        }, {
                            "key" : "Taxable Investment",
                            "values" : taxableBR,
                            "color" : "rgb( 63, 81, 181 )"
                        }, {
                            "key" : "Tax-Advantaged Investments",
                            "values" : nontaxableBR,
                            "color" : "rgb( 255, 87, 34 )"
                        }, {
                            "key" : "Property",
                            "values" : dashBoardProperties,
                            "color" : "rgb( 6, 140, 53 )"
                        }
                        
                        ];
                        
                        maxValuesAssetGraph.sort( function( a, b ) {
                            if ( Number( a ) > Number( b ) ) {
                                return -1;
                            } else if ( Number( a ) < Number( b ) ) {
                                return 1;
                            } else {
                                return 0;
                            }
                        } );
                        /*if ($scope.incomeStartYear % 2 == 0)
                            a = $scope.incomeStartYear - 2;
                        else
                            a = $scope.incomeStartYear - 1;*/
                        var tempa = "" + $scope.incomeStartYear + "";
                        var c = parseDate(tempa);
                        b = $scope.incomeEndYear + 2;
                        var tempb = "" + b + "";
                        var d = parseDate(tempb);
                        $scope.options = {
                                chart : {
                                    showControls : false,
                                    type : "stackedAreaChart",
                                    height : 450,
                                    margin : {
                                        top : 20,
                                        right : 20,
                                        bottom : 30,
                                        left : 40
                                    },
                                    x : function(d) {
                                        return d[0];
                                    },
                                    y : function(d) {
                                        return d[1];
                                    },
                                    forceX: [ c, d ],
                                    forceY: [0, Math.ceil(maxValuesAssetGraph[0] / 1000) * 1000 ],
                                    useVoronoi : false,
                                    clipEdge : true,
                                    duration : 100,
                                    useInteractiveGuideline : true,
                                    xAxis : {
                                        showMaxMin : false,
                                        tickFormat : function(d) {
                                            return d3.time.format("%Y")(new Date(d))
                                        }
                                    },
                                    yAxis : {
                                        tickFormat : function(d) {
                                            return d3.format(",.2f")(d);
                                        }
                                    },
                                    zoom : {
                                        enabled : true,
                                        scaleExtent : [ 1, 10 ],
                                        useFixedDomain : false,
                                        useNiceScale : false,
                                        horizontalOff : false,
                                        verticalOff : true,
                                        unzoomEventType : "dblclick.zoom"
                                    }
                                }
                            };
                    };
                    // $scope.drawD3Chart();
                    // $scope.changeIncomeProfile();
                    // /$scope.fetchPlanDetails();
                    $scope.changeAge = function() {
                        Array.prototype.splice.apply($scope.income, [ 0,
                                $scope.chartIncome.length ]
                                .concat($scope.chartIncome));
                        if ($scope.marital_status == "Yes") {
                            Array.prototype.splice.apply($scope.spouseIncome, [
                                    0, $scope.chartIncomeSpouse.length ]
                                    .concat($scope.chartIncomeSpouse));
                        }
                        $scope.plotGraphSpouse($scope.startAge);

                    };

                    $scope.changeYearBR = function(year) {
                    	//console.log($scope.tableIncome);
                        for (var i = 0; i < $scope.tableIncome.length; i++) {
                            if (parseInt(year) == parseInt($scope.tableIncome[i]["year"])) {
                            	//console.log($scope.tableIncome[i]);
                                $scope.dataPieChartBR = [
                                        {
                                            key : "Savings",
                                            y : $scope.tableIncome[i].savings,
                                            "color" : "rgb( 205, 21, 51 )"
                                        },
                                        {
                                            key : "Taxable ",
                                            y : $scope.tableIncome[i].taxable_investment_amount,
                                            "color" : "rgb( 63, 81, 181 )"
                                        },
                                        {
                                            key : "Tax-Advantaged ",
                                            y : $scope.tableIncome[i].nontaxable_investment_amount,
                                            "color" : "rgb( 255, 87, 34 )"
                                        },
                                        {
                                            key : "Property ",
                                            y : $scope.properties_equity[i].value,
                                            "color" : "rgb( 6, 140, 53 )"
                                        } ];
                                //console.log($scope.dataPieChartBR);
                            }
                        }
                        //console.log( $scope.dataPieChartBR);
                    };

                    /*
                     * $scope.changeYearAR = function( year ){
                     * 
                     * for( var i=0;i<$scope.tableIncome.length;i++ ){ if(
                     * parseInt( year ) == parseInt(
                     * $scope.tableIncome[i]["year"] ) ){
                     * 
                     * $scope.dataPieChartAR = [ { key: "Savings", y:
                     * $scope.tableIncome[i].savings }, { key: "Tax-Advantaged ",
                     * y: $scope.tableIncome[i].nontaxable_investment_amount }, {
                     * key: "Taxable ", y:
                     * $scope.tableIncome[i].taxable_investment_amount } ]; } } }
                     */

                    /*
                     * $scope.changeYearPorfolioBR = function(
                     * startYearPorfolioBR ){
                     * 
                     * for( var i=0;i<$scope.Porfolio_tableIncome.length;i++ ){
                     * if( parseInt( startYearPorfolioBR ) == parseInt(
                     * $scope.Porfolio_tableIncome[i]["year"] ) ){
                     * 
                     * $scope.dataPieChartBR = [ { key: "Savings", y:
                     * $scope.Porfolio_tableIncome[i].savings }, { key:
                     * "Tax-Advantaged ", y:
                     * $scope.Porfolio_tableIncome[i].nontaxable_investment_amount }, {
                     * key: "Taxable ", y:
                     * $scope.Porfolio_tableIncome[i].taxable_investment_income } ]; } } }
                     * 
                     * $scope.changeYearPorfolioAR = function(
                     * startYearPorfolioAR ){
                     * 
                     * for( var i=0;i<$scope.Porfolio_tableIncome.length;i++ ){
                     * if( parseInt( startYearPorfolioAR ) == parseInt(
                     * $scope.Porfolio_tableIncome[i]["year"] ) ){
                     * 
                     * $scope.dataPieChartAR = [ { key: "Savings", y:
                     * $scope.Porfolio_tableIncome[i].savings }, { key:
                     * "Tax-Advantaged ", y:
                     * $scope.Porfolio_tableIncome[i].nontaxable_investment_amount }, {
                     * key: "Taxable ", y:
                     * $scope.Porfolio_tableIncome[i].taxable_investment_income } ]; } } }
                     */

                    $scope.plotGraphSpouse = function($age) {
                        $scope.chartIncomeSpouse = [];
                        for (var i = 0; i < $scope.spouseIncome.length; i++) {

                            if ($scope.spouseIncome[i].year == ($scope.birthYear * 1 + $age * 1)) {

                                break;
                            } else {
                                $scope.chartIncomeSpouse.push({
                                    value : $scope.spouseIncome[i].value,
                                    year : $scope.spouseIncome[i].year,
                                    "allowDrag" : "1"
                                });

                            }
                        }
                        editchart();
                    };

                    $scope.limiting = function(category) {
                        // alert( "hi" )
                        if (category.id <= 99) {
                            return category;
                        }
                    };
                    $scope.plotGraph = function($age) {
                        $scope.chartYear = [];
                        $scope.chartIncome = [];
                        for (var i = 0; i < $scope.income.length; i++) {
                            /*
                             * if ( $scope.income[i].year == ( $scope.birthYear *
                             * 1 + $age * 1 ) ) { // //alert(
                             * $scope.income[i].year ) break; } else {
                             */
                            $scope.chartYear.push({
                                label : $scope.income[i].year.toString()
                            });
                            $scope.chartIncome.push({
                                value : $scope.income[i].value,
                                year : $scope.income[i].year,
                                "allowDrag" : "1"
                            });

                            // }
                        }
                        $scope.yearModal(new Date().getFullYear());
                        editchart();
                    };

                    $scope.plotExpense = function($data) {
                        $scope.chartExpense = [];
                        for (var i = 0; i < $data.length; i++) {
                            $scope.chartExpense.push({
                                value : $data[i].value,
                                "allowDrag" : "0"
                            });
                            $scope.assetGoals.push({
                                value : "0",
                                "allowDrag" : "0"
                            });
                        }
                        editchart();
                    };

                    $scope.plotAssetGoals = function($data) {
                        $scope.chartGoals = [];
                        for (var i = 0; i < $data.length; i++) {
                            $scope.chartGoals.push({
                                value : $data[i].value,
                                "allowDrag" : "0"
                            });
                        }
                        editchart();
                    };

                    $scope.restore = function() {
                        d3.selectAll("svg#incomeExpense").remove();

                        d3.selectAll("svg#growthAsset").remove();
                        $scope.load1();
                        // $scope.changeIncomeProfile(
                        // $scope.incomeProfilesChart );
                        // $scope.clearGraph();
                        $scope.showIncomeExp = false;
                        // SimpleGraph.prototype.update();

                        // d3.select( "#slider3" ).remove();
                        // $scope.load1();
                    };
                    $scope.restore1 = function() {
                        $scope.growthRate = $scope.growthRateTemp;
                        // alert( "restor--->"+$scope.growthRate );
                        $scope.portfolioDividend = $scope.portfolioDividendTemp;
                        $scope.portfolioInterest = $scope.portfolioInterestTemp;

                        $scope.formDataPortfolio.growthRate = $scope.growthRateTemp;
                        $scope.formDataPortfolio.portfolioDividend = $scope.portfolioDividendTemp;
                        $scope.formDataPortfolio.portfolioInterest = $scope.portfolioInterestTemp;
                        // alert( $scope.growthRate );

                    };

                    $scope.yearModal = function($datecurr) {
                        $scope.chartYear2 = [];
                        for (var i = 0; i < $scope.chartYear.length; i++) {
                            if ($scope.chartYear[i].label > $datecurr) {

                                $scope.chartYear2.push({
                                    label : $scope.chartYear[i].label
                                            .toString()
                                });
                            }
                        }
                    };

                    $scope.fetchPlanDetails = function($event) {
                       //  alert( "Plan name: " +$event );
                        // alert( "Fetch plan details" );
                        // $scope.maskedPlan=true;
                        $scope.goals1 = [];
                        $scope.chartGoals = [];
                        $scope.goals = [];
                        for (var i = 0; i < 95; i++) {

                            $scope.chartGoals.push({
                                value : 0,
                                "allowDrag" : "0"
                            });
                        }
                        $scope.formdata.form = "fetchPlanDetails";
                        $scope.formdata.planName = $event;
                        $scope.FinPlanName = $event;
                        $http(
                                {
                                    method : "POST",
                                    url : "CreateFinPlan",
                                    data : $.param($scope.formdata),
                                    headers : {
                                        "Content-Type" : "application/x-www-form-urlencoded"
                                    }
                                })
                                .then(
                                        function(result) {
                                            console.log(result.data);

                                            /*
                                             * alert( "hello" ); alert(
                                             * JSON.stringify(
                                             * result.data.userIncomeProfile ) );
                                             * alert( JSON.stringify(
                                             * result.data.spouseIncomeProfile ) );
                                             */
                                            $scope.editExpenseFlag = result.data.editExpenseFlag;
                                            $scope.housingExpense = result.data.housingExpense;
                                            $scope.nonHousingExpense = result.data.nonHousingExpense;
                                            $scope.checkboxData.housingExpense = result.data.housingExpense;
                                        	$scope.checkboxData.nonHousingExpense = result.data.nonHousingExpense;
                                            $scope.planDetailsAsset = [];
                                            $scope.planDetailsTax = [];
                                            $scope.spouseIncomeProfile = [];
                                            $scope.combinedIncomeProfile = [];
                                            // console.log( result.data.Goals );
                                            $scope.goalsTemp = [];
                                            $scope.goalsTemp = result.data.Goals;
                                            // console.log( $scope.goalsTemp );
                                            for (i = 0; i < $scope.goalsTemp.length; i++) {
                                                if ($scope.goalsTemp[i]
                                                        .indexOf("-") != -1)
                                                    $scope.goals[i] = $scope.goalsTemp[i]
                                                            .split("-")[0];
                                                else
                                                    $scope.goals[i] = $scope.goalsTemp[i];

                                            }
                                            // alert( JSON.stringify(
                                            // $scope.goals ) );
                                            // console.log( $scope.goals );
                                            $scope.goalsid = result.data.GoalIds;
                                            $scope.planDetailsAsset = result.data.assests;
                                            $scope.planDetailsTax = result.data.tax;
                                            $scope.totalExpense = result.data.userExpense;
                                            for(i=0;i<data.length;i++)
                                                data[i].totalExpense = $scope.totalExpense[i].totalExpense;
                                            $scope.expensesForGraph = result.data.userExpense;
                                            $scope.taxForGraphFin = result.data.tax;

                                            $scope.userIncomeProfile = result.data.userIncomeProfile;
                                            // alert( JSON.stringify(
                                            // result.data.equity ) )
                                            //alert($scope.userIncomeProfile)
                                        //    alert("change income profile")
                                            $scope.changeIncomeProfile(result.data.finIncomeProfile);
                                            
                                            $scope.properties_equity = result.data.equity;
                                            $scope.marital_status_finPlan = result.data.marital_status;
                                            if ($scope.marital_status_finPlan == "Yes") {
                                                $scope.nospouse = true;
                                                $scope.spouseIncomeProfile = result.data.spouseIncomeProfile;
                                                $scope.combinedIncomeProfile = result.data.combinedIncomeProfile;
                                            } else {
                                                $scope.nospouse = false;
                                            }
                                            // alert( $scope.chartYearExpense );
                                            for (var i = 0; i < $scope.chartYearExpense.length - 1; i++) {
                                                // alert( i );
                                                if ($scope.totalExpense[i].year * 1 == $scope.chartYearExpense[i].label * 1) {
                                                    $scope.chartExpense[i].value = $scope.totalExpense[i].totalExpense;
                                                    if ($scope.tableIncome[i].year * 1 == $scope.totalExpense[i].year * 1) {
                                                        $scope.tableIncome[i].expense = $scope.totalExpense[i].totalExpense;
                                                                                                            }
                                                }
                                            }
                                            
                                            // console.log( $scope.tableIncome
                                            // );
                                            // alert( "hello" );
                                            if ($scope.goals == "noGoals"
                                                    || $scope.goals == undefined
                                                    || $scope.goals == ""
                                                    || $scope.goals.length == 0) {
                                                // alert( "hi" );
                                                $scope.noGoals = false;
                                                $scope.messagehome = "We extend your income and expense to when you are seventy year old, you can drag the graph to change the future incomes and expenses as you like.";
                                                $scope.chartAssets = [];
                                                // console.log(
                                                // $scope.tableIncome );
                                                for (i = 0; i < $scope.planDetailsAsset.length; i++) {
                                                    if ($scope.tableIncome[i].year == $scope.planDetailsAsset[i].year) {
                                                        if ($scope.marital_status_finPlan == "Yes") {
                                                            $scope.tableIncome[i].spouseValue = $scope.spouseIncomeProfile[i].value;
                                                        }
                                                        $scope.tableIncome[i].value = $scope.userIncomeProfile[i].value;

                                                        $scope.tableIncome[i].federalTax = $scope.planDetailsTax[i].federalTax;
                                                        $scope.tableIncome[i].userfICASocialSecurityTax = $scope.planDetailsTax[i].userSSTax;
                                                        $scope.tableIncome[i].spousefICASocialSecurityTax = $scope.planDetailsTax[i].spouseSSTax;
                                                        $scope.tableIncome[i].stateTax = $scope.planDetailsTax[i].stateTax;
                                                        $scope.tableIncome[i].fICAMedicareTax = $scope.planDetailsTax[i].fICAMedicareTax;
                                                        $scope.tableIncome[i].savings = $scope.planDetailsAsset[i].savings;
                                                        // alert(
                                                        // $scope.tableIncome[i].savings
                                                        // );
                                                        $scope.tableIncome[i].nontaxable_investment_amount = $scope.planDetailsAsset[i].nontaxable_investment_amount;
                                                        $scope.tableIncome[i].taxable_investment_amount = $scope.planDetailsAsset[i].taxable_investment_amount;
                                                        $scope.sum = $scope.planDetailsAsset[i].savings
                                                                * 1
                                                                + $scope.planDetailsAsset[i].taxable_investment_amount
                                                                * 1
                                                                + $scope.planDetailsAsset[i].nontaxable_investment_amount
                                                                * 1;
                                                        $scope.chartAssets
                                                                .push({
                                                                    value : $scope.sum,
                                                                    "allowDrag" : "0"
                                                                });
                                                    }
                                                } // $scope.maskedPlan=false;
                                            } else {
                                                $scope.chartAssets = [];
                                                $scope.noGoals = true;
                                                $scope.messagehome = "We extend your income and expense to when you are seventy year old, you can drag the graph to change the future incomes and expenses as you like.";

                                                for (var i = 0; i < $scope.goals.length; i++) {

                                                    $scope.goals1.push({
                                                        name : $scope.goals[i],
                                                        id : $scope.goalsid[i]
                                                    });

                                                }
                                                $scope.firstgoal = ($scope.goals1[0].name);
                                                $scope.goal_id = $scope.goalsid[0];
                                                $scope.selectedGoalname = $scope.firstgoal;
                                                // $scope.ExpenseDetails=[];
                                                $scope.ExpenseDetails = result.data.expenses;
                                                // alert( "Bala" )
                                                // alert( JSON.stringify(
                                                // $scope.ExpenseDetails ) );
                                                // $scope.plotExpense(
                                                // $scope.ExpenseIncome );
                                                //alert(" car  "+JSON.stringify($scope.ExpenseDetails));
                                                $scope
                                                        .plotAssetGoals($scope.assetGoals);

                                                if ($scope.ExpenseDetails.housing_expense != null
                                                        && $scope.ExpenseDetails.housing_expense.mortgage_expense != 0) {
                                                    $scope
                                                            .addExpenseGoals(
                                                                    $scope.ExpenseDetails.housing_expense.startYear,
                                                                    $scope.ExpenseDetails.housing_expense.endYear,
                                                                    $scope.ExpenseDetails.housing_expense.mortgage_expense);
                                                    $scope
                                                            .addExpenseGoals(
                                                                    $scope.ExpenseDetails.housing_expense.startYear,
                                                                    $scope.ExpenseDetails.housing_expense.startYear,
                                                                    $scope.ExpenseDetails.housing_expense.downPayment);
                                                    $scope
                                                            .addExpense(
                                                                    $scope.ExpenseDetails.housing_expense.startYear,
                                                                    $scope.ExpenseDetails.housing_expense.startYear,
                                                                    $scope.ExpenseDetails.housing_expense.downPayment);
                                                }
                                                if ($scope.ExpenseDetails.carGoalExpense!= null) {
                                                    /*alert( JSON.stringify(
                                                    $scope.ExpenseDetails.carGoalExpense));*/
                                                    for (i = 0; i < $scope.ExpenseDetails.carGoalExpense.length; i++) {
                                                    $scope
                                                            .addExpense(
                                                                    $scope.ExpenseDetails.carGoalExpense[i].startYear,
                                                                    $scope.ExpenseDetails.carGoalExpense[i].endYear,
                                                                    $scope.ExpenseDetails.carGoalExpense[i].annualCost);
                                                    $scope
                                                            .addExpense(
                                                                    $scope.ExpenseDetails.carGoalExpense[i].startYear,
                                                                    $scope.ExpenseDetails.carGoalExpense[i].startYear,
                                                                    $scope.ExpenseDetails.carGoalExpense[i].downPayment);
                                                    }

                                                }
                                                if (result.data.kid_expense != null) {

                                                    for (i = 0; i < result.data.kid_expense.length; i++) {
                                                        $scope
                                                                .addExpenseGoals(
                                                                        result.data.kid_expense[i].startYear,
                                                                        result.data.kid_expense[i].endYear,
                                                                        result.data.kid_expense[i].annualExpense);
                                                    }

                                                }
                                                if (result.data.college_expense != null) {
                                                    for (i = 0; i < result.data.college_expense.length; i++) {
                                                        /*
                                                         * $year =
                                                         * result.data.college_expense[i].startYear *
                                                         * 1; $yearEnd = $year *
                                                         * 1 + 3;
                                                         */
                                                        for (var h = 0; h < result.data.college_expense[i].collegeGoalAmountData.length; h++) {

                                                            $scope
                                                                    .addExpense(
                                                                            result.data.college_expense[i].collegeGoalAmountData[h].year * 1,
                                                                            result.data.college_expense[i].collegeGoalAmountData[h].year * 1,
                                                                            result.data.college_expense[i].collegeGoalAmountData[h].cost * 1);

                                                        }
                                                    }

                                                }

                                                if (result.data.customized_expense != null) {
                                                    for (i = 0; i < result.data.customized_expense.length; i++) {
                                                        $scope
                                                                .addExpense(
                                                                        result.data.customized_expense[i].startYear,
                                                                        result.data.customized_expense[i].endYear * 1 - 1,
                                                                        result.data.customized_expense[i].goalCost);
                                                    }

                                                }
                                                // alert( "hello1" );
                                                // alert( JSON.stringify(
                                                // $scope.ExpenseDetails.marriage_expense
                                                // ) );
                                                if ($scope.ExpenseDetails.marriage_expense != null) {
                                                    // alert( "heello please "
                                                    // );
                                                    $scope.marriageYear = $scope.ExpenseDetails.marriage_expense.endYear;
                                                    $scope
                                                            .addExpense(
                                                                    $scope.ExpenseDetails.marriage_expense.endYear,
                                                                    $scope.ExpenseDetails.marriage_expense.endYear,
                                                                    $scope.ExpenseDetails.marriage_expense.annualexpense
                                                                            * (($scope.ExpenseDetails.marriage_expense.endYear - $scope.ExpenseDetails.marriage_expense.startYear) * 1 + 1));

                                                    $scope.marital_status == "Yes";
                                                    for (h = 0; h < $scope.spouseIncomeProfile.length; h++) {
                                                        if ($scope.spouseIncomeProfile[h].year * 1 == $scope.tableIncome[h].year) {
                                                            $scope.tableIncome[h].spouseValue = $scope.spouseIncomeProfile[h].value;
                                                            $scope.spouseIncome[h].value = $scope.spouseIncomeProfile[h].value;
                                                        }
                                                    }
                                                    for (i = 0; i < $scope.chartYear.length; i++) {
                                                        if ($scope.chartYear[i].label == $scope.marriageYear) {
                                                            for (var l = 0; l < i; l++) {
                                                                $scope.chartIncomeSpouse[l].allowDrag = "0";
                                                            }
                                                        }
                                                    }
                                                    $scope.MarriageYear = result.data.MarriageYear;
                                                    $scope.spouceAge = result.data.spouceAge;
                                                    /*
                                                     * $scope.spousebirthYear =
                                                     * $scope.MarriageYear -
                                                     * $scope.spouceAge;
                                                     */
                                                    $scope.spousebirthYear = result.data.spouceBirthYear;
                                                    $scope.SuccessMessage_marriage = "A new income profile has been created for the marriage goal";
                                                    $("#warning-alert2").show();
                                                    $("#warning-alert2")
                                                            .fadeTo(2000, 500)
                                                            .slideUp(
                                                                    1000,
                                                                    function() {
                                                                        $(
                                                                                "#warning-alert2")
                                                                                .hide();
                                                                    });
                                                }

                                                if (result.data.retirementFlag == true) {
                                                    //data = [];
                                                    $scope.tempData = [];
                                                    $scope.modifiedchartIncome = [];
                                                    $scope.modifiedchartIncomeSpouse = [];
                                                    $scope.tempData = [];
                                                    maxY = 0;
                                                    maxY1 = 0;
                                                    tempData = [];
                                                    tempYears = [];
                                                    drag = [];
                                                    drag1 = [];
                                                    tempData1 = [];
                                                    tempYears1 = [];
                                                    len = $scope.userIncomeProfile.length;
                                                    // $scope.spouseIncomeProfile
                                                    // =
                                                    // result.data.spouseIncomeProfile;
                                                    // $scope.retirementYear =
                                                    // $scope.birthYear +
                                                    // $scope.defaultretirementAge;
                                                    $scope.defaultretirementAge = result.data.userRetirementAge;
                                                    $scope.defaultUserRetirementAge = result.data.userRetirementAge;
                                                    $scope.tempRetAgeUser = result.data.userRetirementAge
                                                            + $scope.birthYear;
                                                    // alert( "In fetch plan
                                                    // details:
                                                    // "+$scope.tempRetAgeUser
                                                    // );
                                                    tempData = result.data.userPlot;
                                                    var tempDatalength = tempData.length;
                                                    var diff2 = 0;
                                                    for (var i = 0; i < $scope.userIncomeProfile.length; i++) {
                                                        if ($scope.userIncomeProfile[i].retirement_income > 0) {
                                                            diff2 = $scope.userIncomeProfile[i].year;
                                                            break;
                                                        }
                                                    }

                                                    if (diff2 != Number(tempData[tempDatalength - 2].year)) {
                                                        for (var i = 0; i < $scope.userIncomeProfile.length; i++) {
                                                            if (diff2 == Number($scope.userIncomeProfile[i].year)) {
                                                                tempData[tempDatalength - 2].year = $scope.userIncomeProfile[i].year;
                                                                tempData[tempDatalength - 2].userIncome = $scope.userIncomeProfile[i].value;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    for (var i = 0; i < tempData.length; i++) {
                                                        var tempDataIndex = $scope.userIncomeProfile
                                                                .map(function(obj) {
                                                                    return obj.year;
                                                                }).indexOf(tempData[i].year);
                                                        tempData[i].userIncome = $scope.userIncomeProfile[tempDataIndex].value;
                                                    }
                                                    //tempData[tempDatalength - 1].year = $scope.userIncomeProfile[$scope.userIncomeProfile.length - 1].year;
                                                    //tempData[tempDatalength - 1].userIncome = $scope.userIncomeProfile[$scope.userIncomeProfile.length - 1].value;

                                                    for (var k = 0; k < tempDatalength; k++) {
                                                        drag
                                                                .push(tempData[k].year);
                                                        tempYears
                                                                .push(tempData[k].year);
                                                    }
                                                    for (i = 0; i < $scope.userIncomeProfile.length; i++) {
                                                        $scope.modifiedchartIncome[i] = {
                                                            "year" : $scope.userIncomeProfile[i].year,
                                                            "userIncome" : $scope.userIncomeProfile[i].value
                                                        };
                                                    }

                                                    $scope.modifiedchartIncomeBackup = $scope.modifiedchartIncome;
                                                    if ($scope.marital_status == "Yes") {
                                                        tempData1 = result.data.spousePlot;

                                                        $scope.spousedefaultretirementAge = result.data.spouseRetirementAge;
                                                        $scope.defaultSpouseRetirementAge = result.data.spouseRetirementAge;
                                                        $scope.tempRetAgeSpouse = result.data.spouseRetirementAge
                                                                + $scope.spousebirthYear;
                                                        var tempCombined = [];
                                                        for (i = 0; i < $scope.combinedIncomeProfile.length; i++) {
                                                            tempCombined[i] = $scope.combinedIncomeProfile[i].value;
                                                        }
                                                        maxY = Math.max.apply(
                                                                Math,
                                                                tempCombined) + 20000;
                                                        maxY1 = Math.max.apply(
                                                                Math,
                                                                tempCombined) + 5000;
                                                        var tempData1length = tempData1.length;
                                                        var diff = 0;
                                                        for (var i = 0; i < $scope.spouseIncome1.length; i++) {
                                                            if ($scope.spouseIncome1[i].retirement_income > 0) {
                                                                diff = $scope.spouseIncome1[i].year;
                                                                break;
                                                            }
                                                        }
                                                        if (diff != Number(tempData1[tempData1length - 2].year)) {
                                                            for (var i = 0; i < $scope.spouseIncome1.length; i++) {
                                                                if (diff == Number($scope.spouseIncome1[i].year)) {
                                                                    tempData1[tempData1length - 2].year = $scope.spouseIncome1[i].year;
                                                                    tempData1[tempData1length - 2].spouseIncome = $scope.spouseIncome1[i].value;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        for (var i = 0; i < tempData1.length; i++) {
                                                            var tempDataIndex = $scope.spouseIncome1
                                                                    .map(function(obj) {
                                                                        return obj.year;
                                                                    }).indexOf(tempData1[i].year);
                                                            tempData1[i].userIncome = $scope.spouseIncome1[tempDataIndex].value;
                                                        }
                                                        //tempData1[tempData1length - 1].year = $scope.spouseIncome1[$scope.spouseIncome1.length - 1].year;
                                                        //tempData1[tempData1length - 1].spouseIncome = $scope.spouseIncome1[$scope.spouseIncome1.length - 1].value;
                                                        for (var k = 0; k < tempData1length; k++) {
                                                            drag1
                                                                    .push(tempData1[k].year);
                                                            tempYears1
                                                                    .push(tempData1[k].year);
                                                        }
                                                    } else {
                                                        $scope.maritalFlag_imp = false;
                                                        var tempUser = [];
                                                        for (var i = 0; i < $scope.userIncomeProfile.length; i++) {
                                                            tempUser[i] = $scope.userIncomeProfile[i].value;
                                                        }
                                                        maxY = Math.max.apply(
                                                                Math, tempUser) + 40000;
                                                        maxY1 = Math.max.apply(
                                                                Math, tempUser) + 10000;
                                                    }

                                                    for (var i = 0; i < $scope.userIncomeProfile.length; i++) {

                                                        if ($scope.marital_status != "Yes") {
                                                            data[i].userIncome = $scope.userIncomeProfile[i].value;
                                                            data[i].year = $scope.userIncomeProfile[i].year;
                                                            
                                                        } else {
                                                            if ($scope.spouseIncome1.length <= i) {
                                                                data[i].userIncome = $scope.income[i].value;
                                                                data[i].year = $scope.income[i].year;
                                                                data[i].spouseIncome = 0;
                                                                data[i].combinedIncome = $scope.combined_income[i].value;
      
                                                                $scope.modifiedchartIncomeSpouse
                                                                        .push({
                                                                            "year" : $scope.income[i].year,
                                                                            "spouseIncome" : 0
                                                                        });
                                                            } else {
                                                                data[i].userIncome = $scope.income[i].value;
                                                                data[i].year = $scope.income[i].year;
                                                                data[i].spouseIncome =  $scope.spouseIncome1[i].value;
                                                                data[i].combinedIncome = $scope.combined_income[i].value;
            
                                                                $scope.modifiedchartIncomeSpouse
                                                                        .push({
                                                                            "year" : $scope.income[i].year,
                                                                            "spouseIncome" : $scope.spouseIncome1[i].value
                                                                        });
                                                            }
                                                            $scope.modifiedchartIncomeSpouseBackup = $scope.modifiedchartIncomeSpouse;
                                                        }
                                                    }
                                                    /* D3 code ends */

                                                    d3
                                                            .selectAll(
                                                                    "svg#incomeExpense")
                                                            .remove();
                                                    d3.selectAll(
                                                            "svg#growthAsset")
                                                            .remove();

                                                    var indexYearUser = data
                                                            .map(
                                                                    function(
                                                                            obj) {
                                                                        return obj.year;
                                                                    })
                                                            .indexOf(
                                                                    tempYears[tempYears.length - 2] - 1);
                                                    $scope.dataUserIncome = data[indexYearUser].userIncome;
                                                    if ($scope.marital_status == "Yes") {
                                                        var indexYearSpouse = data
                                                                .map(
                                                                        function(
                                                                                obj) {
                                                                            return obj.year;
                                                                        })
                                                                .indexOf(
                                                                        tempYears1[tempYears1.length - 2] - 1);
                                                        $scope.dataSpouseIncome = data[indexYearSpouse].spouseIncome;
                                                    }
                                                }

                                                if ($scope.ExpenseDetails.vacation_expense != null
                                                        && $scope.ExpenseDetails.vacation_expense.expenses != 0) {
                                                    if ($scope.ExpenseDetails.vacation_expense.frequency == "One Time") {
                                                        for (var i = 0; i < $scope.chartYearExpense.length; i++) {
                                                            if ($scope.chartYearExpense[i].label == $scope.ExpenseDetails.vacation_expense.startingYear) {
                                                                $scope.chartExpense[i].value = $scope.chartExpense[i].value
                                                                        + $scope.ExpenseDetails.vacation_expense.expenses;
                                                                $scope.tableIncome[i].expense = $scope.tableIncome[i].expense
                                                                        + $scope.ExpenseDetails.vacation_expense.expenses;
                                                                $scope.chartGoals[i].value = $scope.chartGoals[i].value
                                                                        * 1
                                                                        + $scope.ExpenseDetails.vacation_expense.expenses
                                                                        * 1;
                                                            }
                                                        }

                                                    } else if ($scope.ExpenseDetails.vacation_expense.frequency == "Every Year") {
                                                        for (var i = 0; i < $scope.chartYearExpense.length; i++) {

                                                            if ($scope.chartYearExpense[i].label >= $scope.ExpenseDetails.vacation_expense.startingYear) {
                                                                $scope.chartExpense[i].value = $scope.chartExpense[i].value
                                                                        + $scope.ExpenseDetails.vacation_expense.expenses;
                                                                $scope.tableIncome[i].expense = $scope.tableIncome[i].expense
                                                                        + $scope.ExpenseDetails.vacation_expense.expenses;
                                                                $scope.chartGoals[i].value = $scope.chartGoals[i].value
                                                                        * 1
                                                                        + $scope.ExpenseDetails.vacation_expense.expenses
                                                                        * 1;
                                                            }
                                                        }
                                                    } else if ($scope.ExpenseDetails.vacation_expense.frequency == "Every Two Years") {
                                                        for (var i = 0; i < $scope.chartYearExpense.length; i = i + 2) {

                                                            if ($scope.chartYearExpense[i].label >= $scope.ExpenseDetails.vacation_expense.startingYear) {
                                                                $scope.chartExpense[i].value = $scope.chartExpense[i].value
                                                                        + $scope.ExpenseDetails.vacation_expense.expenses;
                                                                $scope.tableIncome[i].expense = $scope.tableIncome[i].expense
                                                                        + $scope.ExpenseDetails.vacation_expense.expenses;
                                                                $scope.chartGoals[i].value = $scope.chartGoals[i].value
                                                                        * 1
                                                                        + $scope.ExpenseDetails.vacation_expense.expenses
                                                                        * 1;
                                                            }
                                                        }

                                                    }

                                                }

                                                for (i = 0; i < $scope.tableIncome.length; i++) { // planDetailsAsset
                                                    // console.log( i );
                                                    // console.log(
                                                    // $scope.tableIncome[i].year
                                                    // );
                                                    if ($scope.tableIncome[i].year == $scope.planDetailsAsset[i].year) {
                                                        if ($scope.marital_status_finPlan == "Yes") {
                                                            $scope.tableIncome[i].spouseValue = $scope.spouseIncomeProfile[i].value;
                                                        }
                                                        $scope.tableIncome[i].value = $scope.userIncomeProfile[i].value;
                                                        $scope.tableIncome[i].federalTax = $scope.planDetailsTax[i].federalTax;
                                                        $scope.tableIncome[i].userfICASocialSecurityTax = $scope.planDetailsTax[i].userSSTax;
                                                        $scope.tableIncome[i].spousefICASocialSecurityTax = $scope.planDetailsTax[i].spouseSSTax;
                                                        $scope.tableIncome[i].stateTax = $scope.planDetailsTax[i].stateTax;
                                                        $scope.tableIncome[i].fICAMedicareTax = $scope.planDetailsTax[i].fICAMedicareTax;
                                                        $scope.tableIncome[i].savings = $scope.planDetailsAsset[i].savings;

                                                        $scope.tableIncome[i].nontaxable_investment_amount = $scope.planDetailsAsset[i].nontaxable_investment_amount;
                                                        $scope.tableIncome[i].taxable_investment_amount = $scope.planDetailsAsset[i].taxable_investment_amount;
                                                        $scope.sum = $scope.planDetailsAsset[i].savings
                                                                * 1
                                                                + $scope.planDetailsAsset[i].taxable_investment_amount
                                                                * 1
                                                                + $scope.planDetailsAsset[i].nontaxable_investment_amount
                                                                * 1;
                                                        $scope.chartAssets
                                                                .push({
                                                                    value : $scope.sum,
                                                                    "allowDrag" : "0"
                                                                });
                                                    }
                                                }
                                                /*
                                                 * if (
                                                 * result.data.retirementFlag==true ) {
                                                 * $scope.drawD3Chart();
                                                 * 
                                                 * //plotAssetChart(); }
                                                 */

                                                // $scope.makeRetirement();
                                                d3.selectAll(
                                                        "svg#incomeExpense")
                                                        .remove();
                                                d3.selectAll("svg#growthAsset")
                                                        .remove();
                                                $scope.drawD3Chart();
                                              //  alert("Drawing finplan asset")
                                                $scope.DrawAssetsAreaChart();
                                                $scope.DrawTaxesAreaChart();
                                                editchart();
                                                // editAssetChart();
                                                // $scope.masked=false;
                                                // $scope.maskedPlan=false;
                                            }
                                        }, function(error) {
                                        });

                    };
                    $scope.makeRetirement = function() {
                        $scope.retirementYear = $scope.birthYear * 1
                                + $scope.defaultretirementAge * 1;
                        for (var i = 0; i < $scope.chartYear.length; i++) {
                            if ($scope.chartYear[i].label * 1 == $scope.retirementYear * 1) {

                                $scope.retirementPoint = i;
                                break;
                            } else {
                                $scope.retirementPoint = $scope.chartYear.length - 1;
                            }
                        }
                        for (j = $scope.retirementPoint; j < $scope.chartYear.length; j++) {

                            $scope.chartIncome[j].allowDrag = "0";

                        }
                        // alert( $scope.marital_status );
                        if ($scope.marital_status == "Yes") {
                            // /alert( "birtYear"+$scope.spousebirthYear );
                            $scope.spouseretirementYear = $scope.spousebirthYear
                                    + $scope.spousedefaultretirementAge;
                            // alert( $scope.spouseretirementYear );
                            for (i = 0; i < $scope.chartYear.length; i++) {
                                if ($scope.chartYear[i].label == $scope.spouseretirementYear) {
                                    $scope.spouseRetirementPoint = i;
                                    break;
                                } else {
                                    $scope.spouseRetirementPoint = $scope.chartYear.length - 1;
                                }

                            }
                            for (j = $scope.spouseRetirementPoint; j < $scope.chartYear.length; j++) {
                                $scope.chartIncomeSpouse[j].allowDrag = "0";
                            }
                            $scope.dragPoint1[$scope.dragPoint1.length - 1] = $scope.spouseRetirementPoint;
                        }
                        $scope.dragPoint[$scope.dragPoint.length - 1] = $scope.retirementPoint;
                    }

                    $scope.addExpense = function($Syear, $Eyear, $expense) {
                        for (var i = 0; i < $scope.chartYearExpense.length; i++) {
                            if ($scope.chartYearExpense[i].label >= $Syear
                                    && $scope.chartYearExpense[i].label <= $Eyear) {
                                $scope.chartExpense[i].value = $scope.chartExpense[i].value
                                        * 1 + $expense * 1;
                                data[i].totalExpense = data[i].totalExpense * 1
                                        + $expense * 1;
                                $scope.tableIncome[i].expense = $scope.tableIncome[i].expense
                                        * 1 + $expense * 1;
                                $scope.chartGoals[i].value = $scope.chartGoals[i].value
                                        * 1 + $expense * 1;
                            }
                        }
                    }
                    $scope.addExpenseGoals = function($Syear, $Eyear, $expense) {

                        for (var i = 0; i < $scope.chartYearExpense.length; i++) {
                            if ($scope.chartYearExpense[i].label >= $Syear
                                    && $scope.chartYearExpense[i].label <= $Eyear) {

                                $scope.chartGoals[i].value = $scope.chartGoals[i].value
                                        * 1 + $expense * 1;
                                data[i].totalExpense = data[i].totalExpense * 1
                                        + $expense * 1;
                            }
                        }

                    }

                    $scope.planchange = function($event, index) {
                        // alert( "changing paln" );
                        // alert( JSON.stringify( $scope.chartExpense ) );
                        /*
                         * for( var i=0;i<$scope.chartExpense.length;i++ ) {
                         * $scope.chartExpense[i].value=0; } alert(
                         * JSON.stringify( $scope.chartExpense ) );
                         */
                        for(var i=0;i<data.length;i++)
                            data[i].totalExpense = 0;
                        $scope.finplan_name = $event;
                        $scope.selected = index;
                        
                    //    alert("change income profile")
                    //    $scope.changeIncomeProfile($scope.planNames1[$scope.selected].profileName);
                        
                        $scope.incomeProfilesChart = $scope.planNames1[$scope.selected].profileName;
                        //alert("fetch plan details")
                        $scope.fetchPlanDetails($scope.finplan_name);
                        /*
                         * $scope.IncomeDetails.profile_name =
                         * $scope.planNames1[$scope.selected].profileName;
                         * $scope.IncomeDetails.form = "getIncomeProfile";
                         * $http( { method: "POST", url: "ModifyIncome", data:
                         * $.param( $scope.IncomeDetails ), headers: {
                         * "Content-Type": "application/x-www-form-urlencoded" } } )
                         * .then( function( result ) {
                         * 
                         * $scope.message = result.data.status; // alert(
                         * $scope.message ); if ( $scope.message == "success" ) {
                         * 
                         * $scope.user_income = result.data.user_income;
                         * $scope.spouse_income = result.data.spouse_income;
                         * $scope.combined_income = result.data.combined_income;
                         * $scope.marital_status = result.data.marital_status;
                         * $scope.tax_incomeProfile =
                         * result.data.tax_incomeProfile;
                         * $scope.asset_incomeProfile =
                         * result.data.asset_incomeProfile;
                         * 
                         * 
                         * for ( var i = 0; i < $scope.chartYear.length; i++ ) {
                         * 
                         * if ( $scope.chartYear[i].label ==
                         * $scope.user_income[i].year && $scope.income[i].year ==
                         * $scope.user_income[i].year ) { $scope.income[i].value =
                         * $scope.user_income[i].value;
                         * $scope.chartIncome[i].value =
                         * $scope.user_income[i].value;
                         * $scope.tableIncome[i].federalTax =
                         * $scope.tax_incomeProfile[i].federalTax;
                         * $scope.tableIncome[i].userfICASocialSecurityTax =
                         * $scope.tax_incomeProfile[i].userSSTax;
                         * $scope.tableIncome[i].spousefICASocialSecurityTax =
                         * $scope.tax_incomeProfile[i].spouseSSTax;
                         * $scope.tableIncome[i].stateTax =
                         * $scope.tax_incomeProfile[i].stateTax;
                         * $scope.tableIncome[i].fICAMedicareTax =
                         * $scope.tax_incomeProfile[i].fICAMedicareTax;
                         * $scope.tableIncome[i].savings =
                         * $scope.asset_incomeProfile[i].savings; //alert(
                         * $scope.planDetailsAsset[i].saving );
                         * $scope.tableIncome[i].nontaxable_investment_amount =
                         * $scope.asset_incomeProfile[i].nontaxable_investment_amount;
                         * 
                         * $scope.tableIncome[i].taxable_investment_amount =
                         * $scope.asset_incomeProfile[i].taxable_investment_amount;
                         * 
                         * if ( $scope.marital_status == "Yes" &&
                         * $scope.chartYear[i].label ==
                         * $scope.spouse_income[i].year &&
                         * $scope.chartYear[i].label ==
                         * $scope.combined_income[i].year ) {
                         * $scope.chartCombinedIncome[i].value =
                         * $scope.combined_income[i].value;
                         * $scope.chartIncomeSpouse[i].value =
                         * $scope.spouse_income[i].value;
                         * 
                         * $scope.spouseIncome[i].value =
                         * $scope.spouse_income[i].value;
                         * 
                         * if ( $scope.tableIncome[i].year*1 ==
                         * $scope.spouse_income[i].year*1 ) {
                         * $scope.tableIncome[i].spouseValue =
                         * $scope.spouse_income[i].value;
                         * $scope.tableIncome[i].value =
                         * $scope.user_income[i].value; } } else {
                         * 
                         * if ( $scope.tableIncome[i].year ==
                         * $scope.user_income[i].year ) {
                         * 
                         * $scope.tableIncome[i].value =
                         * $scope.user_income[i].value; } } } }
                         * $scope.onClickCreatePlan = false; $scope.createPlan =
                         * true; $scope.formdata.checkValue = false;
                         * $scope.hideList = false; $scope.onCheckedRenamePlans =
                         * false; $scope.CopyPlanbutton = false;
                         * $scope.CreatePlanbutton = true; $scope.emptyMassage =
                         * ""; // alert( $event ); $scope.fetchPlanDetails(
                         * $event ); // editchart(); } else if ( $scope.message ==
                         * "fail" ) { } } );
                         */

                    }

                    $scope.changeGoals = function($event, $name) {

                        /*
                         * var index = $scope.goalsid.indexOf( $event ); alert(
                         * JSON.stringify( $scope.goals ) ); $scope.goal_id =
                         * $scope.goalsid[index]; alert( JSON.stringify(
                         * $scope.goalsid ) );
                         */
                        $scope.selectedGoalname = $name;
                        $scope.goal_id = $event;

                    }

                    $scope.goEditGoals = function() {

                        if ($scope.selectedGoalname == "Home") {

                            window.location.href = "GoalHouseEdit.jsp?goalId="
                                    + $scope.goal_id;

                        } else if ($scope.selectedGoalname == "Emergency Fund") {
                            window.location.href = "Emergencyfundedit.jsp?goalId="
                                    + $scope.goal_id;
                        } else if ($scope.selectedGoalname == "Retirement") {
                            window.location.href = "Goalretirementedit.jsp?goalId="
                                    + $scope.goal_id + "&showNote=getValue";
                        } else if ($scope.selectedGoalname == "Car") {
                            window.location.href = "GoalCarEdit.jsp?goalId="
                                    + $scope.goal_id;
                        } else if ($scope.selectedGoalname
                                .includes("College Education")) {
                            window.location.href = "GoalCollegeEducationEdit.jsp?goalId="
                                    + $scope.goal_id;
                        } else if ($scope.selectedGoalname == "Vacation") {
                            window.location.href = "GoalVacationEdit.jsp?goalId="
                                    + $scope.goal_id;
                        } else if ($scope.selectedGoalname == "Marriage") {
                            window.location.href = "MarriageGoalEdit.jsp?goalId="
                                    + $scope.goal_id;
                        } else if ($scope.selectedGoalname
                                .includes("Raising a kid")) {
                            window.location.href = "KidGoalEdit.jsp?goalId="
                                    + $scope.goal_id;
                        } else {
                            window.location.href = "CustomizedGoalEdit.jsp?goalId="
                                    + $scope.goal_id;
                            // ////alert( "goal not found" );
                        }

                    }
                    // ==================================Rename
                    // Plan=====================================
                    $scope.renamePlan = function() {
                        $scope.plan = ""

                        $scope.onCheckedRenamePlans = true;
                        $scope.createPlan = false;

                    }
                    $scope.RenamePlanName = function() {
                        $scope.formdata.goal_id = $scope.goal_id;
                        $scope.formdata.plan_name = $scope.plan;
                        $scope.formdata.formType = "changePlanName";
                        $scope.formdata.newPlanname = $scope.formdata.planname;
                        if ($scope.formdata.newPlanname == null
                                || $scope.formdata.newPlanname == ""
                                || $scope.formdata.newPlanname == undefined) {
                            $scope.emptyMassage = "Please Enter Plan Name!!";
                        } else if ($scope.plan == null || $scope.plan == ""
                                || $scope.plan == undefined) {
                            $scope.emptyMassage = "Please select plan!!";
                        } else {
                            $http(
                                    {
                                        method : "POST",
                                        url : "CopyPlan",
                                        data : $.param($scope.formdata),
                                        headers : {
                                            "Content-Type" : "application/x-www-form-urlencoded"
                                        }
                                    })
                                    .then(
                                            function(result) {
                                                $scope.SuccessMessage = result.data.status;
                                                $("#success-alert").show();
                                                $("#success-alert").fadeTo(
                                                        2000, 300).slideUp(
                                                        300,
                                                        function() {
                                                            $("#success-alert")
                                                                    .hide();
                                                        });

                                                $scope.SuccessMessage = result.data.status;
                                                if ($scope.SuccessMessage == "Plan Name Exists") {
                                                    $scope.formdata.planname = "";
                                                    $scope.load1();
                                                    $scope.cancle();
                                                    $("#warning-alert").show();
                                                    $("#warning-alert")
                                                            .fadeTo(2000, 500)
                                                            .slideUp(
                                                                    500,
                                                                    function() {
                                                                        $(
                                                                                "#warning-alert")
                                                                                .hide();
                                                                    });

                                                } else {
                                                    window.location.href = "dashboardUser0.jsp?finName="
                                                            + $scope.formdata.newPlanname;
                                                }
                                            },
                                            function(error) {
                                                $scope.message = result.data.status;

                                            });
                        }
                    }
                    // ================================going to dask
                    // board======================================
                    $scope.cancle = function() {
                        // alert();

                        $scope.formdata.planname = "";
                        $scope.onClickCreatePlan = false;
                        $scope.createPlan = true;
                        $scope.formdata.checkValue = false;
                        $scope.hideList = false;
                        $scope.onCheckedRenamePlans = false;
                        $scope.CopyPlanbutton = false;
                        $scope.CreatePlanbutton = true;
                        $scope.emptyMassage = "";

                    }

                    // ==================================show plan
                    // list==================================

                    $scope.showPlanList = function() {

                        if ($scope.formdata.checkValue == true) {

                            $scope.onCheckedCopyPlans = true;
                            $scope.CreatePlanbutton = false;
                            $scope.hideList = true;
                            $scope.CopyPlanbutton = true;
                            $scope.plan = $scope.finplan_name;
                        } else {
                            $scope.onCheckedCopyPlans = false;
                            $scope.CreatePlanbutton = true;
                        }
                    }

                    // ---------------------------------------------------copy
                    // plan---------------------------------
                    $scope.copyPlan = function() {
                        $scope.formdata.plan_name = $scope.plan;

                        $scope.formdata.newPlanName = $scope.formdata.planname;

                        if ($scope.formdata.newPlanName == null
                                || $scope.formdata.newPlanName == ""
                                || $scope.formdata.newPlanName == undefined) {
                            $scope.emptyMassage = "Please Enter NewPlan Name!!";
                        } else if ($scope.plan == null || $scope.plan == ""
                                || $scope.plan == undefined) {
                            $scope.emptyMassage = "Please select plan!!";
                        } else {

                            $http(
                                    {
                                        method : "POST",
                                        url : "CopyPlan",
                                        data : $.param($scope.formdata),
                                        headers : {
                                            "Content-Type" : "application/x-www-form-urlencoded"
                                        }
                                    })
                                    .then(
                                            function(result) {
                                                $scope.SuccessMessage = result.data.status;
                                                if ($scope.SuccessMessage == "Plan Name Exists") {
                                                    // $scope.load1();
                                                    $("#warning-alert").show();
                                                    $("#warning-alert")
                                                            .fadeTo(2000, 300)
                                                            .slideUp(
                                                                    300,
                                                                    function() {
                                                                        $(
                                                                                "#warning-alert")
                                                                                .hide();
                                                                    });

                                                } else {
                                                    window.location.href = "dashboardUser0.jsp?finName="
                                                            + $scope.formdata.newPlanname;
                                                }

                                            }, function(error) {

                                            });
                        }

                    }
                    // ---------------------------------------------------Delete
                    // plan---------------------------------
                    $scope.deletePlan = function() {
                        $("#myModalFin").modal("show")
                    }
                    $scope.goDeletePlan = function() {
                        $scope.formdata.plan_name = $scope.finplan_name;
                        $scope.formdata.formType = "deletePlan";
                        if ($scope.finplan_name == null
                                || $scope.finplan_name == ""
                                || $scope.finplan_name == undefined) {
                            $scope.emptyMassage = "Please select plan!!";
                        } else {
                            $http(
                                    {
                                        method : "POST",
                                        url : "CopyPlan",
                                        data : $.param($scope.formdata),
                                        headers : {
                                            "Content-Type" : "application/x-www-form-urlencoded"
                                        }
                                    })
                                    .then(
                                            function(result) {
                                                $scope.SuccessMessage = result.data.status;
                                                window.location.href = "dashboardUser0.jsp";
                                                if ($scope.SuccessMessage == "success") {
                                                    $scope.SuccessMessage = " Plan deleted successfully !!";
                                                    $("#success-alert").show();
                                                    $("#success-alert")
                                                            .fadeTo(2000, 300)
                                                            .slideUp(
                                                                    300,
                                                                    function() {
                                                                        $(
                                                                                "#success-alert")
                                                                                .hide();
                                                                    });
                                                } else {
                                                    window.location.href = "dashboardUser0.jsp?finName="
                                                            + $scope.formdata.newPlanname;
                                                }

                                            }, function(error) {

                                            });
                        }

                    }
                    // ========================================delete
                    // goal===================================
                    $scope.DeleteGoal = function() {

                        $("#myModal2").modal("show")
                    }
                    $scope.goDeleteGoals = function() {
                        $scope.formdata.goal_id = $scope.goal_id;
                        $scope.formdata.plan_name = $scope.finplan_name;
                        $scope.formdata.formType = "deleteGoal";
                        $http(
                                {
                                    method : "POST",
                                    url : "CopyPlan",
                                    data : $.param($scope.formdata),
                                    headers : {
                                        "Content-Type" : "application/x-www-form-urlencoded"
                                    }
                                })
                                .then(
                                        function(result) {
                                            $scope.formdata.planname = "";
                                            $scope.onClickCreatePlan = false;
                                            $scope.createPlan = true;
                                            $scope.formdata.checkValue = false;
                                            $scope.hideList = false;
                                            $scope.onCheckedRenamePlans = false;
                                            $scope.CopyPlanbutton = false;
                                            $scope.CreatePlanbutton = true;
                                            $scope.emptyMassage = "";
                                            $scope.load1();
                                            $scope.SuccessMessage = " Goal deleted successfully !!";
                                            $("#success-alert").show();
                                            $("#success-alert").fadeTo(2000,
                                                    300).slideUp(
                                                    300,
                                                    function() {
                                                        $("#success-alert")
                                                                .hide();
                                                    });
                                            $scope.message1 = result.data.status;

                                        },
                                        function(error) {
                                            $scope.message = result.data.status;

                                        });

                    }

                    $scope.goSelectGoals = function() {
                        window.location.href = "goals.jsp?finName="
                                + $scope.finplan_name;
                    }

                    $scope.modalform = function() {
                        $scope.masked = true;

                        // document.getElementById( "create" ).disabled = true;
                        if ($scope.formdata.planname == undefined
                                || $scope.formdata.planname == null
                                || $scope.formdata.planname == "") {
                            $scope.emptyMassage = "Please Enter NewPlan Name!!";
                            $scope.masked = false;
                        } else {
                            $scope.formdata.profile_name = $scope.incomeProfilesChart;
                            $scope.formdata.form = "createNewPlan";
                            $http(
                                    {
                                        method : "POST",
                                        url : "CreateFinPlan",
                                        data : $.param($scope.formdata),
                                        headers : {
                                            "Content-Type" : "application/x-www-form-urlencoded"
                                        }
                                    })
                                    .then(
                                            function(result) {
                                                // document.getElementById(
                                                // "create" ).disabled
                                                // = false;
                                                $scope.message1 = result.data.status;
                                                if ($scope.message1 == "success") {
                                                    // ////alert(
                                                    // $scope.formdata.planname
                                                    // );
                                                    $scope.masked = false;
                                                    window.location.href = "goals.jsp?finName="
                                                            + $scope.formdata.planname;

                                                } else {
                                                    if (result.data.status == "Plan Name Exists") {
                                                        $scope.emptyMassage = result.data.status;
                                                        $scope.masked = false;
                                                    } else {
                                                        $scope.masked = false;
                                                        $("#retirmentModal")
                                                                .modal("show");
                                                    }
                                                    $scope.masked = false;
                                                }
                                                console.log("message"
                                                        + $scope.message);

                                            },
                                            function(error) {
                                                $scope.message = result.data.status;

                                            });

                        }

                    }

                    function editchart() {
                        $scope.maskedPlotChart = true;
                        $scope.dataSet = [];
                        if ($scope.marital_status_imp == "Yes") {
                            // $scope.nospouse = true;
                            $scope.dataSet = [];
                            $scope.dataSet.push({
                                id : "IJ",
                                seriesname : "User Income",
                                color : "328de4",
                                data : $scope.chartIncome
                            }, {
                                id : "LJ",
                                seriesname : "Spouse Income",
                                color : "32e2e2",
                                data : $scope.chartIncomeSpouse
                            }, {
                                id : "CR",
                                seriesname : "Combined Income",
                                showvalues : "0",
                                color : "#B0C10C",
                                data : $scope.chartCombinedIncome
                            }, {
                                id : "CR",
                                seriesname : "Expense",
                                showvalues : "0",
                                color : "e43234",
                                data : $scope.chartExpense
                            });

                        } else {
                            // $scope.nospouse = false;
                            $scope.dataSet = [];
                            $scope.dataSet.push({
                                id : "IJ",
                                seriesname : "User Income",
                                color : "328de4",
                                data : $scope.chartIncome
                            }, {
                                id : "CR",
                                seriesname : "Expense",
                                showvalues : "0",
                                color : "e43234",
                                data : $scope.chartExpense
                            });

                        }

                        $scope.maskedPlotChart = false;
                    }
                    /*
                     * function plotAssetChart() {} function editAssetChart() {}
                     */
                    $scope.chartReload = function() {
                        // alert();

                        editchart();
                    }

                    $scope.modifyUserIncome = function($startIndex, $endIndex,
                            $changedPoint) {

                        $scope.startIndex = $startIndex;
                        $scope.endIndex = $endIndex;
                        $scope.changedPoint = $changedPoint;

                        incValue = ($scope.val - $scope.chartIncome[$scope.startIndex].value)
                                / ($scope.changedPoint - $scope.startIndex);

                        for (i = $scope.startIndex + 1; i <= $scope.changedPoint; i++) {

                            $scope.chartIncome[i].value = Math
                                    .ceil($scope.chartIncome[i - 1].value * 1
                                            + incValue * 1);

                            if ($scope.marital_status == "Yes") {
                                $scope.chartCombinedIncome[i].value = Math
                                        .ceil($scope.chartIncome[i].value
                                                + $scope.chartIncomeSpouse[i].value);
                            }

                            // alert( JSON.stringify( $scope.chartCombinedIncome
                            // ) );
                        }

                        dcValue = ($scope.val - $scope.chartIncome[$scope.endIndex].value)
                                / ($scope.endIndex - $scope.changedPoint);

                        for (i = $scope.changedPoint + 1; i <= $scope.endIndex; i++) {

                            $scope.chartIncome[i].value = Math
                                    .ceil($scope.chartIncome[i - 1].value * 1
                                            - dcValue * 1);

                            if ($scope.marital_status == "Yes") {
                                $scope.chartCombinedIncome[i].value = Math
                                        .ceil($scope.chartIncome[i].value
                                                + $scope.chartIncomeSpouse[i].value);
                            }

                        }

                        editchart();

                    }

                    $scope.modifySpouseIncome = function($startIndex,
                            $endIndex, $changedPoint) {

                        $scope.startIndex1 = $startIndex;
                        $scope.endIndex1 = $endIndex;
                        $scope.changedPoint1 = $changedPoint;
                        incValue = ($scope.val1 - $scope.chartIncomeSpouse[$scope.startIndex1].value)
                                / ($scope.changedPoint1 - $scope.startIndex1);

                        for (i = $scope.startIndex1 + 1; i <= $scope.changedPoint1; i++) {

                            $scope.chartIncomeSpouse[i].value = Math
                                    .ceil($scope.chartIncomeSpouse[i - 1].value
                                            * 1 + incValue * 1);
                            $scope.chartCombinedIncome[i].value = Math
                                    .ceil($scope.chartIncome[i].value
                                            + $scope.chartIncomeSpouse[i].value);

                        }

                        dcValue = ($scope.val1 - $scope.chartIncomeSpouse[$scope.endIndex1].value)
                                / ($scope.endIndex1 - $scope.changedPoint1);

                        for (i = $scope.changedPoint1 + 1; i <= $scope.endIndex1; i++) {

                            $scope.chartIncomeSpouse[i].value = Math
                                    .ceil($scope.chartIncomeSpouse[i - 1].value
                                            * 1 - dcValue * 1);
                            $scope.chartCombinedIncome[i].value = Math
                                    .ceil($scope.chartIncome[i].value
                                            + $scope.chartIncomeSpouse[i].value);
                        }

                        editchart();

                    }

                    // -------------------------------------------------------------------------------------------------------------------

                    $scope.save = function() {
                        $scope.masked = true;
                        $scope.saveFlag = 0;
                        $scope.profileNameCheckbox = $scope.checkboxData.profileName;
                        $scope.IncomeDetails = {};
                        
                        if (($scope.checkboxData.applyThisYear == undefined && $scope.checkboxData.applyFutureYear == undefined)
                                || ($scope.checkboxData.applyThisYear == false && $scope.checkboxData.applyFutureYear == false)) {
                            $scope.messageChangeIncome = "Please select atleast an option !!";
                            $scope.masked = false;
                        } else if ($scope.checkboxData.applyFutureYear == true
                                && ($scope.checkboxData.profileName == "" || $scope.checkboxData.profileName == undefined)) {

                            $scope.messageChangeIncome = "Please enter the profile name!!";
                            $scope.masked = false;
                        } else {

                            if ($scope.checkboxData.applyThisYear == true) {
                                $scope.IncomeDetails.profile_name = $scope.incomeProfilesChart;
                            }
                            if ($scope.checkboxData.applyFutureYear == true) {
                                $scope.IncomeDetails.profile_name = $scope.checkboxData.profileName;
                                $scope.IncomeDetails.selectedProfileName = $scope.incomeProfilesChart;
                            }
                            if ($scope.checkboxData.applyThisYear == true
                                    && $scope.checkboxData.applyPlan == false) {

                                $scope.IncomeDetails.form = "update";
                                $scope.IncomeDetails.planCount = 0;
                            } else if ($scope.checkboxData.applyFutureYear == true
                                    && $scope.checkboxData.applyPlan == false) {

                                $scope.IncomeDetails.form = "create";
                                $scope.IncomeDetails.planCount = 0;
                                
                            } else if ($scope.checkboxData.applyThisYear == true
                                    && $scope.checkboxData.applyPlan == true) {

                                $scope.IncomeDetails.form = "update";
                                $scope.IncomeDetails.planCount = $scope.Count;
                                $scope.IncomeDetails.plan_name = $scope.finplan_name;

                            } else if ($scope.checkboxData.applyFutureYear == true
                                    && $scope.checkboxData.applyPlan == true) {

                                $scope.IncomeDetails.form = "create";
                                $scope.IncomeDetails.planCount = $scope.Count;
                                $scope.IncomeDetails.plan_name = $scope.finplan_name;

                            }

                            $scope.chartIncomeSpouseOld = [];
                            $scope.chartIncomeOld = [];

                            Array.prototype.splice.apply(
                                    $scope.chartIncomeSave, [ 0,
                                            $scope.chartIncome.length ]
                                            .concat($scope.chartIncome));
                            if ($scope.marital_status == "Yes") {
                                Array.prototype.splice
                                        .apply(
                                                $scope.spouseIncomeSave,
                                                [
                                                        0,
                                                        $scope.chartIncomeSpouse.length ]
                                                        .concat($scope.chartIncomeSpouse));
                            }
                            Array.prototype.splice.apply($scope.chartYearSave,
                                    [ 0, $scope.chartYear.length ]
                                            .concat($scope.chartYear));

                            $scope.chartIncomeSpouseOld = $scope.spouseIncomeSave;
                            $scope.chartIncomeOld = $scope.chartIncomeSave;
                            /*
                             * var len = $scope.modifiedchartIncome.length; for(
                             * j=0;j<$scope.tempData.length;j++ ) {
                             * $scope.modifiedchartIncome[len] =
                             * $scope.tempData[j]; len++; }
                             */

                            $scope.finalIncome = [];
                            if ($scope.marital_status == "Yes") {
                                for (i = 0; i < $scope.modifiedchartIncomeSpouse.length; i++) {
                                    $scope.finalIncome
                                            .push({
                                                "year" : $scope.modifiedchartIncome[i].year,
                                                "userIncome" : $scope.modifiedchartIncome[i].userIncome,
                                                "spouseIncome" : $scope.modifiedchartIncomeSpouse[i].spouseIncome
                                            });
                                }
                            } else {
                                for (i = 0; i < $scope.modifiedchartIncome.length; i++) {
                                    $scope.finalIncome
                                            .push({
                                                "year" : $scope.modifiedchartIncome[i].year,
                                                "userIncome" : $scope.modifiedchartIncome[i].userIncome
                                            });
                                }
                            }
                            console.log($scope.finalIncome);

                            $scope.IncomeDetails.income = JSON
                                    .stringify($scope.finalIncome);
                            $scope.IncomeDetails.year = JSON
                                    .stringify($scope.chartYearSave);
                            $scope.IncomeDetails.userPlot = JSON
                                    .stringify(tempData);
                            if ($scope.marital_status == "Yes") {
                                $scope.IncomeDetails.spouseIncome = JSON
                                        .stringify($scope.spouseIncomeSave);
                                $scope.IncomeDetails.marital_status = "Yes";
                                $scope.IncomeDetails.spousePlot = JSON
                                        .stringify(tempData1);
                            } else {
                                $scope.IncomeDetails.marital_status = "No";
                            }
                            console.log($scope.IncomeDetails);
                            // alert( JSON.stringify( $scope.IncomeDetails ) );
                            $http(
                                    {
                                        method : "POST",
                                        url : "ModifyIncome",
                                        data : $.param($scope.IncomeDetails),
                                        headers : {
                                            "Content-Type" : "application/x-www-form-urlencoded"
                                        }
                                    })
                                    .then(
                                            function(result) {

                                                $scope.SuccessMessage = " User income saved successfully !!";

                                                $scope.message = result.data.status;
                                                // alert( JSON.stringify(
                                                // result.data ) );
                                                if ($scope.message == "success") {
                                                    $scope.messageChangeIncome = "";
                                                    $scope.checkboxData.profileName = "";
                                                    $("#chartModel").modal(
                                                            "hide");
                                                    $scope.chartIncomeSpouse = [];
                                                    $scope.chartIncome = [];
                                                    $scope.chartIncomeSpouse = [];
                                                    $scope.chartYear = [];
                                                    $scope.chartIncome = [];
                                                    $scope.planNames1 = [];
                                                    $scope.chartIncomeSave = [];
                                                    $scope.spouseIncomeSave = [];
                                                    $scope.chartYearSave = [];
                                                    $scope.tableIncome = [];
                                                    $scope.tableIncome_imp = [];
                                                    $scope.chartCombinedIncome = [];
                                                    $scope.chartYearExpense = [];
                                                    // d3.select( "svg"
                                                    // ).remove();
                                                    // d3.select( "#slider3"
                                                    // ).remove();
                                                    d3
                                                            .selectAll(
                                                                    "svg#incomeExpense")
                                                            .remove();
                                                    d3.selectAll(
                                                            "svg#growthAsset")
                                                            .remove();
                                                    $scope.load1();
                                                    // $scope.changeIncomeProfile(
                                                    // $scope.finplan_name );
                                                    $("#success-alert").show();
                                                    $("#success-alert")
                                                            .fadeTo(2000, 300)
                                                            .slideUp(
                                                                    300,
                                                                    function() {
                                                                        $(
                                                                                "#success-alert")
                                                                                .hide();
                                                                    });
                                                    // $( "#chartModel" ).modal(
                                                    // "toggle" );
                                                    $("#chartModel").modal(
                                                            "hide");
                                                } else {
                                                    // alert( $scope.message );
                                                    if (result.data.message == "income profile already exist") {
                                                        $scope.messageChangeIncome = "Profile name already exits!!";
                                                    } else {
                                                        $("#chartModel").modal(
                                                                "hide");
                                                        $("#negativeAssets")
                                                                .modal("show");
                                                    }
                                                }
                                                console.log("message"
                                                        + $scope.message);
                                                $scope.masked = false;
                                            },
                                            function(error) {
                                                // alert( "Fail" );
                                                $scope.message = result.data.status;

                                            });

                        }
                    }

                    $scope.selectThisYear = function() {
                        $scope.messageChangeIncome = "";
                        if ($scope.checkboxData.applyThisYear == true) {
                            $scope.checkboxData.applyGraduallyYear = false;
                            $scope.checkboxData.applyFutureYear = false;
                            $scope.incomeProfileName = false;
                            $scope.checkboxData.profileName = "";

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
                    $scope.selectGraduallyYear = function() {
                        $scope.messageChangeIncome = "";
                        if ($scope.checkboxData.applyGraduallyYear == true) {
                            $scope.checkboxData.applyThisYear = false;

                        }

                    }
                    $scope.selectFutureYear = function() {

                        if ($scope.checkboxData.applyFutureYear == true) {
                            $scope.messageChangeIncome = "";
                            $scope.checkboxData.applyThisYear = false;
                            $scope.incomeProfileName = true;
                            // $scope.checkboxData.profileName="";

                        }

                    }

                    $scope.showEdit = function() {
                    	//console.log($scope.tableIncome_imp);
                    	var tempincome=0;
                    	var tempspouseincome=0;
                    	for(var j=0;j<$scope.tableIncome_imp.length;j++)
                    		{
                    		tempincome=$scope.tableIncome_imp[j].value+tempincome;
                    		tempspouseincome=$scope.tableIncome_imp[j].spouseValue+tempspouseincome;
                    		}
                    	if(tempincome==0 &&tempspouseincome==0)
                    		{
                    		//alert();
                    		 $("#zeroIncome").modal("show");
                    		}
                    	else{
                    		//alert("1");
                    		   $scope.createPlan = false;

                               $scope.onClickCreatePlan = true;
                               if ($scope.Count == 0) {
                                   $scope.hideCheckBox = false;
                               } else {
                                   $scope.hideCheckBox = true;
                               }

                    	}
                     
                    }

                    // -------------checking the graph is saved or not
                    // ------------
                    $scope.checkSave = function() {

                        if ($scope.saveFlag == 1) {
                            $("#myModalback").modal("show");

                        } else {
                            window.location.href = "userProfile.jsp";
                        }
                    }

                    $scope.gouserProfile = function() {

                        window.location.href = "userProfile.jsp";
                    }

                    $scope.clearGraph = function() {
                        $scope.dragPoint1 = [];
                        $scope.dragPoint = [];
                        $scope.checkDrag = 0;
                        $scope.checkDrag1 = 0;

                    }

                    $scope.changeIncomeProfile = function($profileName) {
                    //alert("ChangeIncomeProfile");	
                   // alert($scope.Count);
                        $scope.masked = true;
                        /* $scope.chartAssets=[]; */
                        $scope.IncomeDetails.profile_name = $profileName;
                        $scope.IncomeDetails.form = "getIncomeProfile";
                        $http(
                                {
                                    method : "POST",
                                    url : "ModifyIncome",
                                    data : $.param($scope.IncomeDetails),
                                    headers : {
                                        "Content-Type" : "application/x-www-form-urlencoded"
                                    }
                                })
                                .then(
                                        function(result) {
                                            console.log(result.data);
                                            // $scope.chartAssets=[];
                                            $scope.message = result.data.status;
                                            // alert( JSON.stringify(
                                            // result.data ) );
                                            // alert( $scope.message );
                                            if ($scope.message == "success") {
                                            	$scope.editExpenseFlag = result.data.editExpenseFlag;
                                                $scope.housingExpense = result.data.housingExpense;
                                                $scope.nonHousingExpense = result.data.nonHousingExpense;
                                                $scope.checkboxData.housingExpense = result.data.housingExpense;
                                            	$scope.checkboxData.nonHousingExpense = result.data.nonHousingExpense;
                                                d3.selectAll(
                                                        "svg#incomeExpense")
                                                        .remove();
                                                d3.selectAll("svg#growthAsset")
                                                        .remove();
                                                // data = [];
                                                $scope.tempData = [];
                                                $scope.modifiedchartIncome = [];
                                                $scope.modifiedchartIncomeSpouse = [];
                                                $scope.tempData = [];
                                                maxY = 0;
                                                maxY1 = 0;
                                                tempData = [];
                                                tempYears = [];
                                                drag = [];
                                                drag1 = [];
                                                tempData1 = [];
                                                tempYears1 = [];
                                       
                                                $scope.user_income = result.data.user_income;
                                                if($scope.Count==0) {
                                                $scope.properties_equity = result.data.equity;
                                                }
                                                tempData = result.data.userPlot;
                                                var tempDatalength = tempData.length;
                                                var diff2 = 0;
                                                for (var i = 0; i < $scope.user_income.length; i++) {
                                                    if ($scope.user_income[i].retirement_income > 0) {
                                                        diff2 = $scope.user_income[i].year;
                                                        break;
                                                    }
                                                }
                                                if (diff2 != Number(tempData[tempDatalength - 2].year)) {
                                                    for (var i = 0; i < $scope.user_income.length; i++) {
                                                        if (diff2 == Number($scope.user_income[i].year)) {
                                                            tempData[tempDatalength - 2].year = $scope.user_income[i].year;
                                                            tempData[tempDatalength - 2].userIncome = $scope.user_income[i].value;
                                                            break;
                                                        }
                                                    }
                                                }
                                                for (var i = 0; i < $scope.user_income.length; i++) {
                                                    if (diff2 == Number($scope.user_income[i].year)) {
                                                        tempData[tempDatalength - 2].year = $scope.user_income[i].year;
                                                        tempData[tempDatalength - 2].userIncome = $scope.user_income[i].value;
                                                        break;
                                                    }
                                                }
                                                for (var i = 0; i < tempData.length; i++) {
                                                    var tempDataIndex = $scope.user_income
                                                            .map(function(obj) {
                                                                return obj.year;
                                                            }).indexOf(tempData[i].year);
                                                    tempData[i].userIncome = $scope.user_income[tempDataIndex].value;
                                                }
                                                //tempData[tempDatalength - 1].year = $scope.user_income[$scope.user_income.length - 1].year;
                                                //tempData[tempDatalength - 1].userIncome = $scope.user_income[$scope.user_income.length - 1].value;
                                                for (var k = 0; k < tempDatalength; k++) {
                                                    drag.push(tempData[k].year);
                                                    tempYears
                                                            .push(tempData[k].year);
                                                }
                                                // alert( "In change income:
                                                // "+$scope.retirementYear );
                                                // alert(
                                                // result.data.marital_status );
                                                $scope.marital_status_imp = result.data.marital_status;
                                                len = $scope.user_income.length;
                                                if ($scope.marital_status_imp == "Yes") {
                                                    tempData1 = result.data.spousePlot;
                                                    $scope.marital_status = "Yes";
                                                    $scope.maritalFlag_imp = true;
                                                    $scope.spouse_income = result.data.spouse_income;
                                                    // alert( JSON.stringify(
                                                    // $scope.spouse_income ) );

                                                    $scope.combined_income = result.data.combined_income;
                                                    var tempCombined = [];
                                                    for (i = 0; i < $scope.combined_income.length; i++) {
                                                        tempCombined[i] = $scope.combined_income[i].value;
                                                    }
                                                    maxY = Math.max.apply(Math,
                                                            tempCombined) + 20000;
                                                    maxY1 = Math.max.apply(
                                                            Math, tempCombined) + 5000;
                                                    var tempData1length = tempData1.length;
                                                    var diff = 0;
                                                    for (var i = 0; i < $scope.spouse_income.length; i++) {
                                                        if ($scope.spouse_income[i].retirement_income > 0) {
                                                            diff = $scope.spouse_income[i].year;
                                                            break;
                                                        }
                                                    }
                                                    if (diff != Number(tempData1[tempData1length - 2].year)) {
                                                        for (var i = 0; i < $scope.spouse_income.length; i++) {
                                                            if (diff == Number($scope.spouse_income[i].year)) {
                                                                tempData1[tempData1length - 2].year = $scope.spouse_income[i].year;
                                                                tempData1[tempData1length - 2].spouseIncome = $scope.spouse_income[i].value;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    for (var i = 0; i < tempData1.length; i++) {
                                                        var tempDataIndex = $scope.spouse_income
                                                                .map(function(obj) {
                                                                    return obj.year;
                                                                }).indexOf(tempData1[i].year);
                                                        tempData1[i].spouseIncome = $scope.spouse_income[tempDataIndex].value;
                                                    }
                                                    //tempData1[tempData1length - 1].year = $scope.spouse_income[$scope.spouse_income.length - 1].year;
                                                    //tempData1[tempData1length - 1].spouseIncome = $scope.spouse_income[$scope.spouse_income.length - 1].value;
                                                    for (var k = 0; k < tempData1length; k++) {
                                                        drag1
                                                                .push(tempData1[k].year);
                                                        tempYears1
                                                                .push(tempData1[k].year);
                                                    }
                                                } else {
                                                    $scope.marital_status = "No";
                                                    $scope.maritalFlag_imp = false;
                                                    var tempUser = [];
                                                    for (var i = 0; i < $scope.user_income.length; i++) {
                                                        tempUser[i] = $scope.user_income[i].value;
                                                    }
                                                    maxY = Math.max.apply(Math,
                                                            tempUser) + 40000;
                                                    maxY1 = Math.max.apply(
                                                            Math, tempUser) + 10000;
                                                }
                                                // alert( $scope.maritalFlag_imp
                                                // );
                                                $scope.tax_incomeProfile = result.data.tax_incomeProfile;
                                                $scope.asset_incomeProfile = result.data.asset_incomeProfile;
                                                /* D3 code */

                                                $scope.expensesForGraph = result.data.userExpense;
                                                $scope.taxForGraphIncome = result.data.tax_incomeProfile;

                                                for (i = 0; i < $scope.user_income.length; i++) {
                                                    $scope.modifiedchartIncome[i] = {
                                                        "year" : $scope.user_income[i].year,
                                                        "userIncome" : $scope.user_income[i].value
                                                    }
                                                }
                                                $scope.modifiedchartIncomeBackup = $scope.modifiedchartIncome;

                                                for (var i = 0; i < $scope.user_income.length; i++) {

                                                    if (result.data.spouse_income == undefined
                                                            || result.data.spouse_income == null
                                                            || result.data.spouse_income.length < 1) {
                                                        data[i].year = $scope.user_income[i].year;
                                                        data[i].userIncome = $scope.user_income[i].value;
                                                        if(!$scope.finPlanDetails) {
                                                            
                                                        	if(data[i].totalExpense != $scope.expensesForGraph[i].totalExpense) {
                                                                data[i].totalExpense = $scope.expensesForGraph[i].totalExpense;
                                                            }
                                                            
                                                        }
                                                    } else {
                                                        if ($scope.spouse_income.length <= i) {
                                                            data[i].year = $scope.user_income[i].year;
                                                            data[i].userIncome = $scope.user_income[i].value;
                                                            data[i].spouseIncome = 0;
                                                            data[i].combinedIncome = $scope.combined_income[i].value;
                                                            if(!$scope.finPlanDetails) {
                                                                
                                                            	if(data[i].totalExpense != $scope.expensesForGraph[i].totalExpense) {
                                                                    data[i].totalExpense = $scope.expensesForGraph[i].totalExpense;
                                                                }
                                                                
                                                            }
                                                            $scope.modifiedchartIncomeSpouse
                                                                    .push({
                                                                        "year" : $scope.user_income[i].year,
                                                                        "spouseIncome" : 0
                                                                    });
                                                        } else {
                                                            data[i].year = $scope.user_income[i].year;
                                                            data[i].userIncome = $scope.user_income[i].value;
                                                            data[i].spouseIncome = $scope.spouse_income[i].value;
                                                            data[i].combinedIncome = $scope.combined_income[i].value;
                                                            if(!$scope.finPlanDetails) {
                                                                
                                                            	if(data[i].totalExpense != $scope.expensesForGraph[i].totalExpense) {
                                                                    data[i].totalExpense = $scope.expensesForGraph[i].totalExpense;
                                                                }
                                                                
                                                            }
                                                            $scope.modifiedchartIncomeSpouse
                                                                    .push({
                                                                        "year" : $scope.spouse_income[i].year,
                                                                        "spouseIncome" : $scope.spouse_income[i].value
                                                                    });
                                                        }
                                                        $scope.modifiedchartIncomeSpouseBackup = $scope.modifiedchartIncomeSpouse;
                                                    }
                                                }

                                                /* D3 code ends */
                                                console.log(data);
                                                var indexYearUser = data
                                                        .map(function(obj) {
                                                            return obj.year;
                                                        })
                                                        .indexOf(
                                                                tempYears[tempYears.length - 2] - 1);
                                                $scope.dataUserIncome = data[indexYearUser].userIncome;
                                                if ($scope.marital_status == "Yes") {
                                                    var indexYearSpouse = data
                                                            .map(
                                                                    function(
                                                                            obj) {
                                                                        return obj.year;
                                                                    })
                                                            .indexOf(
                                                                    tempYears1[tempYears1.length - 2] - 1);
                                                    $scope.dataSpouseIncome = data[indexYearSpouse].spouseIncome;
                                                }
                                                $scope.drawD3Chart();
                                                // plotAssetChart();
                                                // console.log( $scope.chartYear
                                                // );
                                                // alert( $scope.Count );
                                                // alert(
                                                // $scope.chartYear[$scope.chartYear.length-1].label
                                                // );
                                                // alert( JSON.stringify(
                                                // $scope.tableIncome_imp ) );
                                                for (var i = 0; i < $scope.chartYear.length - 1; i++) {

                                                    if ($scope.chartYear[i].label == $scope.user_income[i].year
                                                            && $scope.income[i].year == $scope.user_income[i].year) {
                                                        $scope.income[i].value = $scope.user_income[i].value;
                                                        $scope.chartIncome[i].value = $scope.user_income[i].value;
                                                        $scope.tableIncome_imp[i].expense = data[i].totalExpense;
                                                        $scope.tableIncome_imp[i].federalTax = $scope.tax_incomeProfile[i].federalTax;
                                                        $scope.tableIncome_imp[i].userfICASocialSecurityTax = $scope.tax_incomeProfile[i].userSSTax;
                                                        $scope.tableIncome_imp[i].spousefICASocialSecurityTax = $scope.tax_incomeProfile[i].spouseSSTax;
                                                        $scope.tableIncome_imp[i].stateTax = $scope.tax_incomeProfile[i].stateTax;
                                                        $scope.tableIncome_imp[i].fICAMedicareTax = $scope.tax_incomeProfile[i].fICAMedicareTax;
                                                        $scope.tableIncome_imp[i].savings = $scope.asset_incomeProfile[i].savings;
                                                        // alert(
                                                        // $scope.planDetailsAsset[i].saving
                                                        // );
                                                        $scope.tableIncome_imp[i].nontaxable_investment_amount = $scope.asset_incomeProfile[i].nontaxable_investment_amount;

                                                        $scope.tableIncome_imp[i].taxable_investment_amount = $scope.asset_incomeProfile[i].taxable_investment_amount;

                                                        $scope.total = $scope.asset_incomeProfile[i].savings
                                                                * 1
                                                                + $scope.asset_incomeProfile[i].taxable_investment_amount
                                                                * 1
                                                                + $scope.asset_incomeProfile[i].nontaxable_investment_amount;

                                                        // $scope.chartAssets[i].value=$scope.total;
                                                        $scope.chartAssets
                                                                .push({
                                                                    value : $scope.total,
                                                                    "allowDrag" : "0"
                                                                });
                                                        // console.log(
                                                        // $scope.tableIcome )
                                                        if ($scope.marital_status_imp == "Yes"
                                                                && $scope.chartYear[i].label == $scope.spouse_income[i].year
                                                                && $scope.chartYear[i].label == $scope.combined_income[i].year) {
                                                            $scope.chartCombinedIncome[i].value = $scope.combined_income[i].value;
                                                            // console.log(
                                                            // $scope.chartYear[i]
                                                            // );
                                                            // alert(
                                                            // $scope.spouse_income[i].year
                                                            // );
                                                            // $scope.chartIncomeSpouse[i].value
                                                            // =
                                                            // $scope.spouse_income[i].value;
                                                            // $scope.spouseIncome[i].value
                                                            // =
                                                            // $scope.spouse_income[i].value;
                                                            if ($scope.tableIncome[i].year * 1 == $scope.spouse_income[i].year * 1) {
                                                                $scope.tableIncome_imp[i].spouseValue = $scope.spouse_income[i].value;
                                                                $scope.tableIncome_imp[i].value = $scope.user_income[i].value;
                                                            }
                                                        } else {

                                                            if ($scope.tableIncome[i].year == $scope.user_income[i].year) {

                                                                $scope.tableIncome_imp[i].value = $scope.user_income[i].value;
                                                            }
                                                        }
                                                    }
                                                }
                                                // console.log(
                                                // $scope.chartAssets );
                                                // $scope.fetchPlanDetails(
                                                // $profileName );
                                                // plotAssetChart();
                                              //  alert("Drawing income profile asset")
                                                if($scope.Count==0) {
                                                $scope.DrawAssetsAreaChart();
                                                $scope.DrawTaxesAreaChart();
                                                }
                                                editchart();
                                                /* editAssetChart(); */
                                                $scope.masked = false;
                                            } else if ($scope.message == "fail") {

                                            }

                                        });
                    }

                    $scope.hideSuccess = function() {
                        $("#success-alert").hide();

                    }
                    $scope.hideWarning = function() {
                        $("#warning-alert").hide();

                    }

                    $scope.next = function() {
                        $scope.show++;
                    }
                    $scope.back = function() {
                        $scope.show--;

                    };

                    $scope.plotPortfolioChart = function() {

                    }
                    $scope.refreshPortfolio = function() {
                        $scope.show = 0;
                        $scope.firstQuestion = "6";
                        $scope.secondQuestion = "6";
                        $scope.myVar = "";
                        $scope.thirdQuestion = "6";
                        $scope.fourthQuestion = "1";
                        $scope.formDataPortfolio = {};
                        $scope.afterSubmit = true;
                        $scope.beforeSubmit = false;
                        $scope.beforecalculate = false;
                        $scope.nospouse = false;
                        $scope.growthChart = false;
                        $scope.Original1 = true;
                        $scope.Original2 = true;
                        $scope.Origina3 = true;
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
                            // alert( "hello" )
                            submitPortfolio1(1);
                            $scope.afterSubmit = false;
                            $scope.beforeSubmit = true;
                            // $scope.formDataPortfolio.riskScore = 24;
                            // $scope.submitPortfolio1();
                        }
                        $scope.submitPortfolio1(1);
                        $scope.afterSubmit = false;
                        $scope.beforeSubmit = true;
                    }

                    $scope.showtable = function() {

                        $scope.growthChart = false;
                        $scope.growthTable = true;
                    }
                    $scope.showChart = function() {
                        $scope.growthChart = true;
                        $scope.growthTable = false;

                    }
                    $scope.submitPortfolio1 = function($var) {

                        // alert( "vijay--->>" );

                        $scope.masked = true;
                        $scope.firstQuestion;
                        $scope.secondQuestion;
                        $scope.thirdQuestion;
                        $scope.fourthQuestion;
                        // alert( $scope.formDataPortfolio.riskFactor );
                        $scope.formDataPortfolio.formType = "initialSubmit";
                        if ($var == 1) {
                            // alert( "if" );
                            $scope.formDataPortfolio.riskScore = ($scope.fourthQuestion * 1)
                                    + ($scope.firstQuestion * 1)
                                    + ($scope.secondQuestion * 1)
                                    + ($scope.thirdQuestion * 1);
                            if ($scope.marital_status == "No") {
                                // alert( "no-->" );

                                $scope.formDataPortfolio.riskFactor = 1;
                                $scope.filingStatusPort = $scope.filingStatus;
                            } else if ($scope.marital_status == "Yes") {
                                // alert( "yes-->>" );
                                $scope.formDataPortfolio.riskFactor = 2;
                                $scope.filingStatusPort = $scope.filingStatus;
                            }
                            $scope.formDataPortfolio.plan_name = $scope.FinPlanName;
                            // alert( $scope.ageForRiskCal );
                            $scope.formDataPortfolio.age = $scope.ageForRiskCal * 1;
                            // alert( $scope.formDataPortfolio.age );
                            $scope.agePort = $scope.ageUser * 1;
                            // alert( $scope.agePort );

                            // $scope.filingStatusPort="Single";
                            if ($scope.formDataPortfolio.riskScore >= 20
                                    && $scope.formDataPortfolio.riskScore <= 26) {
                                $scope.riskScorePort = "Risk aggressive";

                            } else if ($scope.formDataPortfolio.riskScore >= 15
                                    && $scope.formDataPortfolio.riskScore <= 19)

                            {
                                $scope.riskScorePort = "Risk neutral";

                            } else if ($scope.formDataPortfolio.riskScore >= 10
                                    && $scope.formDataPortfolio.riskScore <= 14) {

                                $scope.riskScorePort = "Risk Adverse";
                            }

                        } else {

                            // alert( "else" )

                            $scope.formDataPortfolio.riskScore = $scope.riskScore;

                            $scope.formDataPortfolio.riskScore = $scope.riskScorePort;
                            if ($scope.formDataPortfolio.riskScore == "Risk aggressive") {
                                $scope.formDataPortfolio.riskScore = 22;
                            } else if ($scope.formDataPortfolio.riskScore == "Risk neutral") {

                                $scope.formDataPortfolio.riskScore = 19;
                            } else {
                                $scope.formDataPortfolio.riskScore = 12;
                            }

                            $scope.formDataPortfolio.plan_name = $scope.FinPlanName;
                            if ($scope.formDataPortfolio.plan_name == undefined) {
                                $scope.formDataPortfolio.plan_name = null;
                            }

                            $scope.formDataPortfolio.filingStatus = $scope.filingStatusPort;
                            if ($scope.formDataPortfolio.filingStatus == "Single") {
                                // alert( "iam singal-->" );
                                $scope.formDataPortfolio.riskFactor = 1;

                            } else if ($scope.formDataPortfolio.filingStatus == "Married Filing Jointly") {
                                // alert( "iam marred-->" );
                                $scope.formDataPortfolio.riskFactor = 2;
                            }
                            $scope.formDataPortfolio.age = $scope.agePort;

                        }

                        // alert( JSON.stringify( $scope.formDataPortfolio ) );
                        $http(
                                {
                                    method : "POST",
                                    url : "investmentPortfolio",
                                    data : $.param($scope.formDataPortfolio),
                                    headers : {
                                        "Content-Type" : "application/x-www-form-urlencoded"
                                    }
                                })
                                .then(
                                        function(result) {

                                            $scope.growthRate = result.data.growthRate.growthRate
                                                    .toFixed(2);
                                            // alert( "--->>>
                                            // "+$scope.growthRate );
                                            $scope.portfolioDividend = result.data.growthRate.portfolioDividend
                                                    .toFixed(2);
                                            $scope.portfolioInterest = result.data.growthRate.portfolioInterest
                                                    .toFixed(2);

                                            $scope.growthRateTemp = result.data.growthRate.growthRate
                                                    .toFixed(2);
                                            // alert(
                                            // "fun-->>"+$scope.growthRateTemp
                                            // );
                                            $scope.portfolioDividendTemp = result.data.growthRate.portfolioDividend
                                                    .toFixed(2);
                                            $scope.portfolioInterestTemp = result.data.growthRate.portfolioInterest
                                                    .toFixed(2);

                                            $scope.vtiGrowthRate = (result.data.growthRate.vti10 - result.data.growthRate.vtiD)
                                                    .toFixed(0);
                                            $scope.voeGrowthRate = (result.data.growthRate.voe10 - result.data.growthRate.voeD)
                                                    .toFixed(0);
                                            $scope.veaDGrowthRate = (result.data.growthRate.vea10 - result.data.growthRate.veaD)
                                                    .toFixed(0);
                                            $scope.vwoDGrowthRate = (result.data.growthRate.vwo10 - result.data.growthRate.vwod)
                                                    .toFixed(0);
                                            $scope.vtvDGrowthRate = (result.data.growthRate.vtv10 - result.data.growthRate.vtvd)
                                                    .toFixed(0);
                                            $scope.vbrDGrowthRate = (result.data.growthRate.vbr10 - result.data.growthRate.vbrd)
                                                    .toFixed(0);

                                            $scope.veaD = result.data.growthRate.veaD
                                                    .toFixed(0);
                                            $scope.voeD = result.data.growthRate.voeD
                                                    .toFixed(0);
                                            $scope.vtiD = result.data.growthRate.vtiD
                                                    .toFixed(0);
                                            $scope.vwod = result.data.growthRate.vwod
                                                    .toFixed(0);
                                            $scope.vtvd = result.data.growthRate.vtvd
                                                    .toFixed(0);
                                            $scope.vbrd = result.data.growthRate.vbrd
                                                    .toFixed(0);

                                            $scope.afterSubmit = false;
                                            $scope.beforeSubmit = true;
                                            $scope.masked = false;
                                        }, function(error) {
                                            $scope.message = result.data;
                                        });

                    };

                    $(document)
                            .click(
                                    function(e) {
                                        if (e.target.id != "test1") {

                                            $scope.Edit = false;
                                            $scope.Original = true;
                                            $scope.Edit1 = false;
                                            $scope.Original1 = true;
                                            $scope.Edit2 = false;
                                            $scope.Original2 = true;
                                            $scope.Original3 = true;
                                            $scope.Edit3 = false;
                                            $scope.Original5 = true;
                                            $scope.Edit5 = false;
                                        }
                                        if (e.target.id == "test"
                                                || e.target.id == "test1") {

                                            e.preventDefault();
                                            e.stopPropagation();

                                        } else {

                                            var scope = angular
                                                    .element(
                                                            document
                                                                    .getElementById("page-top"))
                                                    .scope();
                                            /*
                                             * $scope.apply( function () {
                                             * scope.disabledemo(); } );
                                             */
                                        }
                                    });

                    $scope.submitPortfolio2 = function() {
                        // $( "mu2" ).hide();
                        // $( "mu1" ).hide();
                        // alert( "hihihihih" );
                        // alert( $scope.Porfolio_userIncome );
                        $scope.beforecalculate = true;
                        $scope.masked = true;
                        $scope.dashboardportfoliohide = true;
                        // $scope.maritalFlag_imp=true;
                        $scope.growthTable1 = true;
                        $scope.formDataPortfolio.growthRate = $scope.growthRate;
                        $scope.formDataPortfolio.filingStatusPort = $scope.filingStatusPort;
                        $scope.formDataPortfolio.portfolioDividend = $scope.portfolioDividend;
                        $scope.formDataPortfolio.portfolioInterest = $scope.portfolioInterest;
                        $scope.formDataPortfolio.formType = "calulateBasedonGrowthRate";
                        // alert( $scope.formDataPortfolio.plan_name );
                        $http(
                                {
                                    method : "POST",
                                    url : "investmentPortfolio",
                                    data : $.param($scope.formDataPortfolio),
                                    headers : {
                                        "Content-Type" : "application/x-www-form-urlencoded"
                                    }
                                })
                                .then(
                                        function(result) {
                                            // $scope.drawChart(
                                            // federalTax,fICAMedicareTax,fICASocialSecurityTax,stateTax
                                            // );
                                            // salert( JSON.stringify(
                                            // result.data ) )
                                            if (result.data.status == "success") {
                                                $scope.investMessage = "";
                                                $scope.Porfolio_tableIncome = [];
                                                $scope.Porfolio_planDetailsAsset = result.data.assests;
                                                $scope.Porfolio_planDetailsTax = result.data.tax;
                                                $scope.Porfolio_totalExpense = result.data.userExpense;
                                                $scope.Porfolio_userIncome = result.data.income;
                                                $scope.Porfolio_userIncome = result.data.income;
                                                if ($scope.filingStatusPort == "Married Filing Jointly") {
                                                    $scope.Porfolio_spouseIncome = result.data.spouseIncome;
                                                }
                                                $scope.sum = 0;
                                                for (i = 0; i < $scope.Porfolio_userIncome.length - 1; i++) {
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

                                                    // alert( JSON.stringify(
                                                    // result.data.spouseIncome
                                                    // ) );

                                                    // $scope.maritalFlag_imp=true;
                                                    // alert(
                                                    // $scope.filingStatus )

                                                    // alert( JSON.stringify(
                                                    // $scope.filingStatusPort )
                                                    // );
                                                    // alert(
                                                    // $scope.Porfolio_spouseIncome[0].value
                                                    // );
                                                    if ($scope.formDataPortfolio.filingStatusPort == "Married Filing Jointly") {

                                                        $scope.maritalFlag_imp1 = true;
                                                        $scope.nospouse = true;
                                                        $scope.Porfolio_tableIncome
                                                                .push({
                                                                    id : i,
                                                                    value : $scope.Porfolio_userIncome[i].value,
                                                                    spouseValue : $scope.Porfolio_spouseIncome[i].value,
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
                                                    } else {
                                                        // alert( "else-->>" );
                                                        $scope.maritalFlag_imp1 = false;
                                                        $scope.nospouse = false;
                                                        $scope.Porfolio_tableIncome
                                                                .push({
                                                                    id : i,
                                                                    value : $scope.Porfolio_userIncome[i].value,
                                                                    year : $scope.Porfolio_userIncome[i].year,
                                                                    // spouseValue
                                                                    // :
                                                                    // $scope.user_income[i].value,
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
                                                // alert( JSON.stringify(
                                                // $scope.Porfolio_tableIncome )
                                                // );

                                                // alert( $scope.maritalFlag_imp
                                                // );
                                                // $scope.plotPortfolioChart();
                                                $scope.afterSubmit = false;
                                                $scope.beforeSubmit = true;
                                                $scope.masked = false;
                                                var firstPiePorfolio = {};
                                                // var secondPiePorfolio={};
                                                var count = 0;
                                                var parseDate = d3.time
                                                        .format("%Y").parse;
                                                // console.log(
                                                // $scope.Porfolio_tableIncome
                                                // );
                                                // alert( JSON.stringify(
                                                // $scope.Porfolio_tableIncome )
                                                // );
                                                $scope.yearreportsPorfolioBR = new Array();
                                                // $scope.yearreportsPorfolioAR
                                                // = new Array();
                                                var retirementAge = 0;

                                                if (parseInt($scope.retirementYear) < parseInt($scope.spouseretirementYear)
                                                        && $scope.spouseretirementYear !== undefined) {
                                                    retirementAge = parseInt($scope.spouseretirementYear);
                                                } else {
                                                    retirementAge = parseInt($scope.retirementYear);
                                                }
                                                // alert( JSON.stringify(
                                                // $scope.Porfolio_tableIncome )
                                                // );
                                                for (var i = 0; i < $scope.Porfolio_tableIncome.length; i++) {
                                                    firstPiePorfolio = $scope.Porfolio_tableIncome[0];
                                                    // alert( JSON.stringify(
                                                    // firstPiePorfolio ) );
                                                    // if( retirementAge >=
                                                    // parseInt(
                                                    // $scope.Porfolio_tableIncome[i]["year"]
                                                    // ) ){
                                                    // alert( "hi" );
                                                    // alert( JSON.stringify(
                                                    // $scope.Porfolio_tableIncome[i]["year"]
                                                    // ) );

                                                    $scope.yearreportsPorfolioBR
                                                            .push(parseInt($scope.Porfolio_tableIncome[i]["year"]));
                                                    // $scope.reportsstartYearBR=parseInt(
                                                    // $scope.Porfolio_tableIncome[i].year
                                                    // );
                                                    // console.log(
                                                    // $scope.Porfolio_tableIncome[i]["year"]
                                                    // )
                                                    // }else{

                                                    // if( count==0 ){
                                                    // alert( "true" )
                                                    // secondPiePorfolio=$scope.Porfolio_tableIncome[i];
                                                    // alert( JSON.stringify(
                                                    // secondPiePorfolio ) );
                                                    // $scope.startYearPorfolioAR=parseInt(
                                                    // $scope.Porfolio_tableIncome[i].year
                                                    // );
                                                    // console.log(
                                                    // $scope.startYearPorfolioAR
                                                    // );

                                                    // }
                                                    // $scope.yearreportsPorfolioAR.push(
                                                    // parseInt(
                                                    // $scope.Porfolio_tableIncome[i]["year"]
                                                    // ) );
                                                    // count++;
                                                    // }
                                                }
                                                // console.log( firstPiePorfolio
                                                // );
                                                // console.log(
                                                // secondPiePorfolio );

                                                $scope.optionsPiePorfolio = {
                                                    chart : {
                                                        type : "pieChart",
                                                        height : 500,
                                                        x : function(d) {
                                                            return d.key;
                                                        },
                                                        y : function(d) {
                                                            return d.y;
                                                        },
                                                        showLabels : true,
                                                        duration : 500,
                                                        labelThreshold : 0.01,
                                                        labelSunbeamLayout : true,
                                                        legend : {
                                                            margin : {
                                                                top : 5,
                                                                right : 35,
                                                                bottom : 5,
                                                                left : 0
                                                            }
                                                        }
                                                    }
                                                };
                                                $scope.dataPieChartPorfolioBR = [
                                                        {
                                                            key : "Savings",
                                                            y : firstPiePorfolio.savings,
                                                            "color" : "#8E44AD"
                                                        },
                                                        {
                                                            key : "Tax-Advantaged",
                                                            y : firstPiePorfolio.nontaxable_investment_amount,
                                                            "color" : "rgb( 63, 81, 181 )"
                                                        },
                                                        {
                                                            key : "Taxable",
                                                            y : firstPiePorfolio.taxable_investment_amount,
                                                            "color" : "rgb( 6, 140, 53 )"

                                                        } ];
                                               
                                                /*
                                                 * $scope.dataPieChartPorfolioAR = [ {
                                                 * key: "Savings", y:
                                                 * secondPiePorfolio.savings }, {
                                                 * key: "Tax-Advantaged", y:
                                                 * secondPiePorfolio.nontaxable_investment_amount }, {
                                                 * key: "Taxable", y:
                                                 * secondPiePorfolio.taxable_investment_amount } ]
                                                 */
                                                $scope.changeYearPorfolioBR = function(
                                                        year) {
                                                    for (var i = 0; i < $scope.Porfolio_tableIncome.length; i++) {
                                                        if (parseInt(year) == parseInt($scope.Porfolio_tableIncome[i]["year"])) {

                                                            $scope.dataPieChartPorfolioBR = [
                                                                    {
                                                                        key : "Savings",
                                                                        y : $scope.Porfolio_tableIncome[i].savings,
                                                                        "color" : "#8E44AD"
                                                                    },
                                                                    {
                                                                        key : "Tax-Advantaged ",
                                                                        y : $scope.Porfolio_tableIncome[i].nontaxable_investment_amount,
                                                                        "color" : "rgb( 63, 81, 181 )"
                                                                    },
                                                                    {
                                                                        key : "Taxable ",
                                                                        y : $scope.Porfolio_tableIncome[i].taxable_investment_amount,
                                                                        "color" : "rgb( 6, 140, 53 )"
                                                                    } ]
                                                        }
                                                    }
                                                }
                                                /*
                                                 * $scope.changeYearPorfolioAR =
                                                 * function( year ){ for( var
                                                 * i=0;i<$scope.Porfolio_tableIncome.length;i++ ){
                                                 * if( parseInt( year ) ==
                                                 * parseInt(
                                                 * $scope.Porfolio_tableIncome[i]["year"] ) ){
                                                 * 
                                                 * $scope.dataPieChartPorfolioAR = [ {
                                                 * key: "Savings", y:
                                                 * $scope.Porfolio_tableIncome[i].savings }, {
                                                 * key: "Tax-Advantaged ", y:
                                                 * $scope.Porfolio_tableIncome[i].nontaxable_investment_amount }, {
                                                 * key: "Taxable ", y:
                                                 * $scope.Porfolio_tableIncome[i].taxable_investment_amount } ] } } }
                                                 */

                                            } else {
                                                $scope.investMessage = "Assests going negative hence the investment portfolio will not be updated";
                                                $scope.masked = false;
                                            }
                                        }, function(error) {
                                            $scope.message = result.data;
                                        });

                    };
                    $scope.deleteIncomeProfile = function() {
                        if ($scope.incomeProfilesChart == "constant_income") {

                            $scope.SuccessMessage = "Constant income profile cannot be deleted !!!";
                            $("#warning-alert").show();
                            $("#warning-alert").fadeTo(2000, 500).slideUp(500,
                                    function() {
                                        $("#warning-alert").hide();
                                    });

                        } else {
                            $("#deleteIncomeProfile").modal("show")
                        }
                    }

                    $scope.refresh = function() {

                        window.location.reload();

                    }

                    $scope.goDeleteIncomeProfile = function() {

                        $scope.formdata.profileName = $scope.incomeProfilesChart;
                        $scope.formdata.formType = "deleteIncomeProfile";

                        $http(
                                {
                                    method : "POST",
                                    url : "CopyPlan",
                                    data : $.param($scope.formdata),
                                    headers : {
                                        "Content-Type" : "application/x-www-form-urlencoded"
                                    }
                                })
                                .then(
                                        function(result) {
                                            $scope.SuccessMessage = "Income profile deleted successfully";
                                            window.location.reload();
                                            $("#success-alert").show();
                                            $("#success-alert").fadeTo(2000,
                                                    300).slideUp(
                                                    300,
                                                    function() {
                                                        $("#success-alert")
                                                                .hide();
                                                    });
                                        }, function(error) {

                                        });

                    }

                    $scope.drawChart = function(a, b, c, d) {
                        // alert();
                        var st = {};
                        st.data = [ {
                            "label" : "FederalTax",
                            "value" : a,
                            "pos" : 0
                        }, {
                            "label" : "FICAMedicareTax",
                            "value" : b,
                            "pos" : 1
                        }, {
                            "label" : "FICASocialSecurityTax",
                            "value" : c,
                            "pos" : 2
                        }, {
                            "label" : "StateTax",
                            "value" : d,
                            "pos" : 3
                        } ];

                        $(document).ready(init);

                        function init(e) {
                            drawPieChartAccessAgesByCountD3();
                        }

                        window.onresize = function(event) {
                            drawPieChartAccessAgesByCountD3();
                        }

                        function drawPieChartAccessAgesByCountD3() {
                            drawD3PieChart("#ChartAccessAgesByCountD3",
                                    st.data, [ 0, 1, 2, 3 ]);
                        }

                        function drawD3PieChart(sel, data, row_id_to_bucket_num) {
                            // clear any previously rendered svg
                            $(sel + " svg").remove();
                            // compute total
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
                            var svgParent = d3.select("svg[data-chart-context="
                                    + sel + "]");
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
                                        // console.log( arc( d ) );
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
                                                var chartDiv = $(sel); // retrieve
                                                // id of
                                                // chart
                                                // container
                                                // div
                                                var selectedValue = $(this)
                                                        .attr("data-legend-pos");
                                                var bucket = row_id_to_bucket_num[selectedValue];
                                                var dest = chartDiv
                                                        .attr("data-drilldown-destination");
                                                var param = chartDiv
                                                        .attr("data-drilldown-key");
                                                var baseURL = dest + "/?"
                                                        + param + "=";
                                                // window.location = baseURL +
                                                // bucket;
                                                /*alert("drill down to "
                                                        + baseURL + bucket);*/
                                            }).on(
                                            "mouseover",
                                            function(e) {
                                                $(this).attr("fill-opacity",
                                                        ".8").css({
                                                    "stroke" : "green",
                                                    "stroke-width" : "1px"
                                                });
                                            }).on("mouseout", function(e) {
                                        $(this).attr("fill-opacity", "2").css({
                                            "stroke-width" : "0px"
                                        });
                                    }).attr("style", "cursor:pointer;").append(
                                            "svg:title").text(function(d) {
                                        return d.data.label;
                                    });

                            arcs
                                    .append("svg:text")
                                    .attr(
                                            "transform",
                                            function(d) {
                                                d.innerRadius = 0;
                                                d.outerRadius = r;
                                                return "translate( "
                                                        + arc.centroid(d)
                                                        + " )";
                                            })
                                    .attr("text-anchor", "middle")
                                    .text(
                                            function(d, i) {
                                                return (data[i].value / tot) * 100 > 10 ? ((data[i].value / tot) * 100)
                                                        .toFixed(1)
                                                        + "%"
                                                        : "";
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
                });