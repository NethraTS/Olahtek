var tempData = [];
var tempData1 = [];
angular.module('wealthsetter').controller(
		'DashboardCtrl',
		[ '$scope', '$rootScope', '$uibModal', '$log', '$http', '$state', '$timeout' ,'toaster','Auth','$cookieStore',
				DashboardCtrl ]);
function DashboardCtrl($scope, $rootScope, $uibModal, $log, $http, $state, $timeout, toaster, Auth,$cookieStore,
		$uibModalInstance) {
	
	$scope.$watch('loginLogoutText', function() {
        console.log($rootScope.loginLogoutText);
    });
	
	$rootScope.checkboxData = {};
	$rootScope.checkboxData.housingExpense = "";
	$rootScope.checkboxData.nonHousingExpense = "";
	$rootScope.boolChangeClass = true;
	
	 
	
	$scope.box1 = false;
	$scope.alerts = [ {
		type : 'success',
		msg : 'Thanks for visiting!!'
	} ];
	$scope.errorMap = {
		      required: "This field is mandatory",
		      email: "Please enter a valid email"
		    }
	$scope.addAlert = function() {
		$scope.alerts.push({
			msg : 'Another alert!'
		});
	};
	
	$scope.openSaveModal = function (size) {
        var modalInstance = $uibModal.open({
          animation: $scope.animationsEnabled,
          templateUrl: 'saveIncome.html',
          controller: 'SaveIncomeCtrl',
          size: size,
          resolve: {
            items: function () {
              return $scope.items;
            }
          }
        });

        modalInstance.result.then(function (selectedItem) {
          $scope.selected = selectedItem;
        }, function () {
          $log.info('Modal dismissed at: ' + new Date());
        });
    };
    
    $scope.openEditModal = function (size) {
        var modalInstance = $uibModal.open({
          animation: $scope.animationsEnabled,
          templateUrl: 'editExpense.html',
          controller: 'EditExpenseCtrl',
          size: size,
          resolve: {
            items: function () {
              return $scope.items;
            }
          }
        });

        modalInstance.result.then(function (selectedItem) {
          $scope.selected = selectedItem;
        }, function () {
          $log.info('Modal dismissed at: ' + new Date());
        });
    };
    
    $scope.openUpdateModal = function (size) {
        var modalInstance = $uibModal.open({
          animation: $scope.animationsEnabled,
          templateUrl: 'updateExpense.html',
          controller: 'UpdateExpenseCtrl',
          size: size,
          resolve: {
            items: function () {
              return $scope.items;
            }
          }
        });

        modalInstance.result.then(function (selectedItem) {
          $scope.selected = selectedItem;
        }, function () {
          $log.info('Modal dismissed at: ' + new Date());
        });
    };
	
	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	};
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
	$scope.fin_name = "undefined";
	$rootScope.checkboxData = {};
	$rootScope.checkboxData.applyGraduallyYear = false;
	$scope.nospouse = false;
	$scope.saveFlag = 0;
	$scope.formdata = {};
	$scope.IncomeDetails = {};
	$rootScope.Count = 0;
	$scope.marriageYear = "";
	$scope.selected = 0;
	$scope.startAge = 70;
	$rootScope.finplan_name = "";
	$scope.goal_id = "";
	$scope.selectedGoalname = "";
	$scope.position = "";
	$scope.income = {};
	$scope.userdetails = {};
	$rootScope.chartYear = [];
	$rootScope.chartIncome = [];
	$rootScope.chartIncomeOld = [];
	$scope.chartExpense = [];
	$rootScope.chartYearSave = [];
	$rootScope.chartIncomeSave = [];
	$scope.totalExpense = [];
	$scope.expense = [];
	$scope.IncomeDetails.income = {};
	$scope.chartExpense = [];
	$scope.ExpenseIncome = [];
	$rootScope.chartYearExpense = [];
	$scope.spouseIncome = [];
	$rootScope.spouseIncomeSave = [];
	$rootScope.chartIncomeSpouse = [];
	$rootScope.chartIncomeSpouseOld = [];
	$scope.tableIncome = [];
	$scope.tableIncome_imp = [];
	$scope.maritalFlag_imp = false;
	$scope.maritalFlag_imp1 = false;
	$scope.chartCombinedIncome = [];
	$rootScope.Count = 0;
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
	$rootScope.modifiedchartIncome = [];
	$rootScope.modifiedchartIncomeSpouse = [];
	$rootScope.modifiedchartIncomeBackup = [];
	$rootScope.modifiedchartIncomeSpouseBackup = [];
	$scope.defaultUserRetirementAge = 70;
	$scope.defaultSpouseRetirementAge = 70;
	$scope.ageForRiskCal = 0;
	$scope.tempData = [];
	var maxY = 0;
	var maxY1 = 0;
	$scope.filingStatus = "";
	var a = 0, b = 0;
	var data = [];
	/*var tempData = [];*/
	var tempYears = [];
	var drag = [];
	var drag1 = [];
	/*var tempData1 = [];*/
	var tempYears1 = [];
	var tempDataHou = [];
	var tempDataNonHou = [];
	var tempYearsHou = [];
	var tempYearsNonHou = [];
	var dragHou = [];
	var dragNonHou = [];

	$scope.expenseValues = [ 0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9,
			1.0 ];
	$scope.housingExpense = 0;
	$scope.nonHousingExpense = 0;
	$rootScope.marital_status = "";
	$scope.growthRateTemp = 0;
	$scope.portfolioDividendTemp = 0;
	$scope.portfolioInterestTemp = 0;
	$scope.age = 0;
	$scope.ageUser = 0;

	$scope.expensesForGraph = [];
	$scope.taxForGraph = [];

	$scope.editExpenseFlag = 0;
	$scope.showEditValues = false;
	$rootScope.checkboxData.editExp = "";

	$scope.ages = [
	/*
	 * { number: "1" }, { number: "2" }, { number: "3" }, { number: "4" }, {
	 * number: "5" }, { number: "6" }, { number: "7" }, { number: "8" }, {
	 * number: "9" }, { number: "10" }, { number: "11" }, { number: "12" }, {
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
	
	$scope.downloadPDF = function() {
		if($scope.maritalFlag_imp) {
			var dataSource = shield.DataSource.create({
	            data: "#pdfContent",
	            schema: {
	                type: "table",
	                fields: {
	                    UAge : { type: Number },
	                    Year: { type: Number },
	                    Income: { type: Number },
	                    SIncome: {type: Number },
	                    Expense: { type: Number },
	                    FDI: { type: Number },
	                    UserFicaSST: { type: Number },
	                    SpouseFicaSST: { type: Number },
						FicaMedicare: { type: Number },
	                    StateTax: { type: Number },
	                    Saving: { type: Number },
						Taxable: { type: Number },
	                    NonTaxable: { type: Number }
	                }
	            }
	        });
			
	        dataSource.read().then(function (data) {
	        	var len = data.length;
	            var pdf = new shield.exp.PDFDocument({
	                created: new Date()
	            });
	            while(len > 0) {
	            	var splitData = data.splice(0,25);
	            	len = data.length;
	            	pdf.addPage("a4", "landscape");  
	                pdf.table(
	                    10,
	                    40,
	                    splitData,
	                    [
	                        { field: "UAge", title: "User Age", width: 55 },
	                        { field: "Year", title: "Year", width: 40 },
	                        { field: "Income", title: "Income", width: 50 },
	                        { field: "SIncome", title: "Spouse Income", width: 85 },
	    					{ field: "Expense", title: "Expense", width: 50 },
	                        { field: "FDI", title: "FDI", width: 40 },
	                        { field: "UserFicaSST", title: "User Fica SST", width: 80 },
	                        { field: "SpouseFicaSST", title: "Spouse Fica SST", width: 90 },
	    					{ field: "FicaMedicare", title: "Fica Medicare", width: 80 },
	                        { field: "StateTax", title: "State Tax", width: 60 },
	                        { field: "Saving", title: "Saving", width: 40 },
	    					{ field: "Taxable", title: "Taxable", width: 60 },
	                        { field: "NonTaxable", title: "Tax-Advantaged", width: 90 }
	                    ],
	                    {
	                        margins: {
	                            top: 80,
	                            left: 5,
	                            right: 5
	                        }
	                    }
	                );
	            }
	            if($scope.finPlanDetails) {
	            	pdf.saveAs({
		                fileName: "finplandetails"
		            });
	            } else {
	            	pdf.saveAs({
		                fileName: "incomeprofiledetails"
		            });
	            }	            
	        });
		} else {
			var dataSource = shield.DataSource.create({
	            data: "#pdfContent",
	            schema: {
	                type: "table",
	                fields: {
	                    UAge : { type: Number },
	                    Year: { type: Number },
	                    Income: { type: Number },
						Expense: { type: Number },
	                    FDI: { type: Number },
	                    UserFicaSST: { type: Number },
						FicaMedicare: { type: Number },
	                    StateTax: { type: Number },
	                    Saving: { type: Number },
						Taxable: { type: Number },
	                    NonTaxable: { type: Number }
	                }
	            }
	        });
			
	        dataSource.read().then(function (data) {
	        	var len = data.length;
	            var pdf = new shield.exp.PDFDocument({
	                created: new Date()
	            });
	            while(len > 0) {
	            	var splitData = data.splice(0,25);
	            	len = data.length;
	            	pdf.addPage("a4", "landscape");  
	                pdf.table(
	                    20,
	                    40,
	                    splitData,
	                    [
	                        { field: "UAge", title: "User Age", width: 55 },
	                        { field: "Year", title: "Year", width: 50 },
	                        { field: "Income", title: "Income", width: 70 },
	    					{ field: "Expense", title: "Expense", width: 70 },
	                        { field: "FDI", title: "FDI", width: 70 },
	                        { field: "UserFicaSST", title: "User Fica SST", width: 80 },
	    					{ field: "FicaMedicare", title: "Fica Medicare", width: 80 },
	                        { field: "StateTax", title: "State Tax", width: 70 },
	                        { field: "Saving", title: "Saving", width: 70 },
	    					{ field: "Taxable", title: "Taxable", width: 95 },
	                        { field: "NonTaxable", title: "Tax-Advantaged", width: 95 }
	                    ],
	                    {
	                        margins: {
	                            top: 50,
	                            left: 10,
	                            right: 10
	                        }
	                    }
	                );
	            }
	            
	            if($scope.finPlanDetails) {
	            	pdf.saveAs({
		                fileName: "finplandetails"
		            });
	            } else {
	            	pdf.saveAs({
		                fileName: "incomeprofiledetails"
		            });
	            }
	        });
		}
	}
	
	
	$scope.onClickCreatePlan = false;
	$scope.showEdit = function() {
		$("#createFinPlan").slideToggle();
        //$scope.createPlan = false;
		$scope.onClickCreatePlan = !$scope.onClickCreatePlan;
		if($scope.onClickCreatePlan) {
	        if ($rootScope.Count == 0) {
	            $scope.hideCheckBox = false;
	        } else {
	            $scope.hideCheckBox = true;
	        }
		}
    }
	$scope.cancle = function() {
		$("#createFinPlan").slideToggle();
		$scope.onClickCreatePlan = !$scope.onClickCreatePlan;
        $scope.formdata.planname = "";
        $scope.onClickCreatePlan = false;
        $scope.createPlan = true;
        $scope.formdata.checkValue = false;
        $scope.hideList = false;
        $scope.onCheckedRenamePlans = false;
        $scope.CopyPlanbutton = false;
        $scope.CreatePlanbutton = true;
    }
	
	$scope.showPlanList = function() {

        if ($scope.formdata.checkValue == true) {

            $scope.onCheckedCopyPlans = true;
            $scope.CreatePlanbutton = false;
            $scope.hideList = true;
            $scope.CopyPlanbutton = true;
            $scope.plan = $rootScope.finplan_name;
        } else {
            $scope.onCheckedCopyPlans = false;
            $scope.CreatePlanbutton = true;
        }
    }
	
	 $scope.modalform = function() {
         $scope.masked = true;
         $scope.formdata.profile_name = $rootScope.incomeProfilesChart;
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
                        	 console.log(result.data);
                             $scope.message1 = result.data.status;
                             if ($scope.message1 == "success") {
                                 $scope.masked = false;
                                 toaster.pop('success', "", "{template: 'toasterData.html', data: 'Plan created successfully!!'}", 5000, 'templateWithData');
                                 $scope.load1();
                                 /*window.location.href = "goals.jsp?finName="
                                         + $scope.formdata.planname;*/

                             } else {
                                 if (result.data.status == "Plan Name Exists") {
                                     toaster.pop('warning', "", "{template: 'toasterData.html', data: 'Plan Name Exists'}", 5000, 'templateWithData');
                                     $scope.masked = false;
                                 } else {
                                     $scope.masked = false;
                                     toaster.pop('warning', "Assets  are going negative!!", "{template: 'toasterData.html', data: 'The plan cannot be created since you are using the same income profile having the retirement goal to create the new financial plan. Please delete the retirement goal and continue creating the new plan'}", 5000, 'templateWithData');
                                 }
                                 $scope.masked = false;
                             }
                             console.log("message" + $scope.message);
                             $scope.cancle();
                             $scope.load();

                         },
                         function(error) {
                             $scope.message = "fail";
                             Auth.setUser(false);
                         });

     };
     
     $scope.copyPlan = function() {
         $scope.formdata.plan_name = $scope.plan;

         $scope.formdata.newPlanName = $scope.formdata.planname;

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
                                	 toaster.pop('warning', "", "{template: 'toasterData.html', data: 'Plan name exists'}", 5000, 'templateWithData');

                                 } else {
                                	 toaster.pop('success', "", "{template: 'toasterData.html', data: 'Plan copied successfully'}", 5000, 'templateWithData');
                                     //window.location.href = "dashboardUser0.jsp?finName=" + $scope.formdata.newPlanname;
                                 }

                             }, function(error) {

                             });
             $scope.cancle();
             $scope.load();

     }
	$scope.isShow = false;
	$scope.isShowExpenseToggle = false;
	
	/*$scope.toggle = function() {
		$("#saveContainer").slideToggle();
		$scope.isShow = !$scope.isShow;
	}
	
	$scope.toggleSave = function() {
		$("#saveContainer").slideToggle();
		$scope.isShow = !$scope.isShow;
		$scope.isShowExpenseToggle = false;
	}
	$scope.toggle1 = function() {
		$("#expenseUpdateContainer").slideToggle();
		$scope.isShowExpenseToggle = !$scope.isShowExpenseToggle;
		$scope.isShow = false;
	}*/
	$scope.mobileView = 992;
    $scope.getWidth = function() {
        return window.innerWidth;
    };
	$scope.$watch($scope.getWidth, function(newValue, oldValue) {
        if (newValue >= $scope.mobileView) {
            if (angular.isDefined($cookieStore.get('toggle'))) {
                $scope.toggle = ! $cookieStore.get('toggle') ? false : true;
            } else {
                $scope.toggle = true;
            }
        } else {
            $scope.toggle = false;
        }

    });
	$scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
        $cookieStore.put('toggle', $scope.toggle);
    };

	 $rootScope.checkboxData.applyYear = { value : "true" };
	 $rootScope.checkboxData.applyPlan = { value : true };
	 //alert(typeof $rootScope.checkboxData.applyYear.value);	
	 $scope.incomeProfileName = false;
	 

	 $scope.saveShowPopUp = function()
     {
     	$scope.showEditValues = false;
     	$rootScope.checkboxData.editExp = "";
     	if($scope.editExpenseFlag == 0) {
     		$scope.openEditModal('md');
     	} else {
     		$scope.openSaveModal('md');
     	}
     }
     
     
	$scope.deleteAllCookies = function() {
		// ////alert( "delete all cookies" );

		$scope.sessionDelete.sessionID = readCookie("AhTwxlO");
		$http({
			method : "POST",
			url : "Logout",
			data : $.param($scope.sessionDelete),
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded"
			}
		}).then(function(result) {
			// ////alert( "Session Got deleted" );
			$rootScope.loginLogoutText = "Login | Signup";
			// window.location.href = "index.jsp";
			$state.go('index');

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
	

	

	$scope.load1 = function() {
		$scope.growthTable = false;
		$scope.showIncomeExp = true;
		$scope.masked = true;
		$scope.loading = true;
		$scope.birthYear;
		$scope.tableIncome = [];
		$scope.planNames = [];
		$scope.planNames1 = [];
		$rootScope.chartIncomeSpouse = [];
		$rootScope.chartYear = [];
		$rootScope.chartIncome = [];
		$scope.retirementPoint;
		$scope.combined_income = [];
		$rootScope.chartIncomeSave = [];
		$rootScope.spouseIncomeSave = [];
		$rootScope.chartYearSave = [];
		$scope.assets = [];
		$scope.tax = [];
		$scope.assetGoals = [];
		$("#success-alert").hide();
		$scope.createPlan = true;
		$scope.Changedyear = "";
		$rootScope.modifiedchartIncome = [];
		$rootScope.modifiedchartIncomeSpouse = [];
		$rootScope.modifiedchartIncomeBackup = [];
		$rootScope.modifiedchartIncomeSpouseBackup = [];
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
		$http({
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
							$rootScope.Count = result.data.Planscount;

							$scope.userName = result.data.userName;
							$scope.income = result.data.income;
							$scope.startYearBR = $scope.income[0].year;
							$scope.birthYear = result.data.birthYear;
							$rootScope.marital_status = result.data.marital_status;
							$scope.retirementYear = $scope.birthYear
									+ $scope.defaultretirementAge;
							var tempUser = [];
							for (var i = 0; i < $scope.income.length; i++) {
								tempUser[i] = $scope.income[i].value;
							}
							maxY = Math.max.apply(Math, tempUser) + 40000;
							maxY1 = Math.max.apply(Math, tempUser) + 10000;
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
								var tempDataIndex = $scope.income.map(
										function(obj) {
											return obj.year;
										}).indexOf(tempData[i].year);
								tempData[i].userIncome = $scope.income[tempDataIndex].value;
							}
							// tempData[tempDatalength - 1].year =
							// $scope.income[$scope.income.length - 1].year;
							// tempData[tempDatalength - 1].userIncome =
							// $scope.income[$scope.income.length - 1].value;
							for (var k = 0; k < tempDatalength; k++) {
								drag.push(tempData[k].year);
								tempYears.push(tempData[k].year);
							}
							/*
							 * for ( var k = 0; k < tempDataHou.length; k++ ) {
							 * dragHou .push( tempDataHou[k].year );
							 * tempYearsHou .push( tempDataHou[k].year ); } for (
							 * var k = 0; k < tempDataNonHou.length; k++ ) {
							 * dragNonHou .push( tempDataNonHou[k].year );
							 * tempYearsNonHou .push( tempDataNonHou[k].year ); }
							 */
							if ($rootScope.marital_status == "Yes") {
								$scope.spouseIncome1 = result.data.spouseIncome;
								$scope.combined_income = result.data.combined_income;
								$scope.spousebirthYear = result.data.spousebirthYear;
								$scope.spouseretirementYear = $scope.spousebirthYear
										+ $scope.spousedefaultretirementAge;
								var tempCombined = [];
								for (i = 0; i < $scope.combined_income.length; i++) {
									tempCombined[i] = $scope.combined_income[i].value;
								}
								maxY = Math.max.apply(Math, tempCombined) + 20000;
								maxY1 = Math.max.apply(Math, tempCombined) + 5000;
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
								// tempData1[tempData1length - 1].year =
								// $scope.spouseIncome1[$scope.spouseIncome1.length
								// - 1].year;
								// tempData1[tempData1length - 1].spouseIncome =
								// $scope.spouseIncome1[$scope.spouseIncome1.length
								// - 1].value;
								for (var k = 0; k < tempData1length; k++) {
									drag1.push(tempData1[k].year);
									tempYears1.push(tempData1[k].year);
								}
							}
							$max = 0;
							$scope.tax = result.data.tax;
							$scope.assets = result.data.assests;
							$scope.age = result.data.age;
							$scope.ageForRiskCal = result.data.age;
							$scope.ageUser = $scope.income[0].year * 1
									- $scope.birthYear * 1;
							for (var i = (($scope.age * 1) + (1 * 1)); i < 100; i++) {
								$scope.ages.push({
									number : i
								});
							}

							$scope.startAge = 70;
							$scope.totalExpense = result.data.userExpense;
							$scope.expensesForGraph = result.data.userExpense;
							$scope.taxForGraph = result.data.tax;

							$k = 0;

							$scope.spouseIncome = [];
							var len = $scope.income.length;
							$scope.incomeStartYear = $scope.income[0].year;
							$scope.incomeEndYear = $scope.income[len - 1].year;
							data = [];

							for (i = 0; i < $scope.income.length; i++) {
								$rootScope.modifiedchartIncome[i] = {
									"year" : $scope.income[i].year,
									"userIncome" : $scope.income[i].value
								};
							}
							$rootScope.modifiedchartIncomeBackup = $rootScope.modifiedchartIncome;

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
										$rootScope.modifiedchartIncomeSpouse.push({
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
										$rootScope.modifiedchartIncomeSpouse
												.push({
													"year" : $scope.income[i].year,
													"spouseIncome" : $scope.spouseIncome1[i].value
												});
									}
									$rootScope.modifiedchartIncomeSpouseBackup = $rootScope.modifiedchartIncomeSpouse;
								}
								$rootScope.chartYearExpense.push({
									label : $scope.income[i].year
								});

								$rootScope.chartYearSave.push({
									label : $scope.income[i].year.toString()
								});
								$rootScope.chartIncomeSave.push({
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
									$scope.spouseIncome.push({
										value : 0,
										year : $scope.income[i].year
									});
									$scope.chartCombinedIncome.push({
										value : 0,
										"allowDrag" : "0"
									});
								} else {

									if ($scope.spouseIncome1.length <= i) {
										$scope.spouseIncome.push({
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
								$rootScope.spouseIncomeSave.push({
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
										$scope.chartAssets.push({
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
							console.log(data);
							var indexYearUser = data.map(function(obj) {
								return obj.year;
							}).indexOf(tempYears[tempYears.length - 2] - 1);
							$scope.dataUserIncome = data[indexYearUser].userIncome;
							if ($rootScope.marital_status == "Yes") {
								var indexYearSpouse = data.map(function(obj) {
									return obj.year;
								}).indexOf(
										tempYears1[tempYears1.length - 2] - 1);
								$scope.dataSpouseIncome = data[indexYearSpouse].spouseIncome;
							}

							// alert( $scope.dataUserIncome );
							// $scope.drawD3Chart();
							if ($rootScope.Count == 0) {
								$scope.showIncomeHeading = false;
								$scope.finPlanDetails = false;
								$scope.incomeProfileDetails = true;
								$rootScope.incomeProfilesChart = "constant_income";
								$scope.planCountOncopy = false;
								$scope.planCountOnload = false;
								$scope.notNewUser = false;
								$scope.plansExist = false;
								$rootScope.checkboxData.applyPlan = false;
								$scope.messagehome = "We extend your income and expense to when you are seventy year old, you can drag the graph to change the future incomes and expenses as you like.";
								if ($scope.profileNameCheckbox != ""
										&& $scope.profileNameCheckbox != undefined) {
									$rootScope.incomeProfilesChart = $scope.profileNameCheckbox;
									$scope
											.changeIncomeProfile($scope.profileNameCheckbox);
								} else {
									$scope
											.changeIncomeProfile($rootScope.incomeProfilesChart);
								}
							} else {
								$scope.showIncomeHeading = true;
								$scope.finPlanDetails = true;
								$scope.incomeProfileDetails = false;
								$rootScope.checkboxData.applyPlan = true;
								$scope.plansExist = true;
								$scope.planCountOncopy = true;
								$scope.planCountOnload = true;
								$scope.notNewUser = true;
								$scope.messagehome = "We extend your income and expense to when you are seventy year old, you can drag the graph to change the future incomes and expenses as you like.";
								for (var i = 0; i < $rootScope.Count; i++) {

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
								$rootScope.finplan_name = $scope.firstPlan;
								if ($scope.fin_name == "undefined") {
									if ($scope.profileNameCheckbox != ""
											&& $scope.profileNameCheckbox != undefined) {
										$rootScope.incomeProfilesChart = $scope.profileNameCheckbox;
										$scope
												.changeIncomeProfile($scope.profileNameCheckbox);
									}
									$scope
											.changeIncomeProfile($scope.firstProfile);
									$scope.fetchPlanDetails($scope.firstPlan);
									$rootScope.incomeProfilesChart = $scope.firstProfile;
								} else {
									if ($scope.profileNameCheckbox != ""
											&& $scope.profileNameCheckbox != undefined) {
										$rootScope.incomeProfilesChart = $scope.profileNameCheckbox;
										$scope
												.changeIncomeProfile($scope.profileNameCheckbox);
									}
									$scope.planchange($scope.fin_name,
											$scope.position);
								}
							}
							$scope.plotGraph($scope.startAge);
							$scope.plotExpense($scope.ExpenseIncome);
							$scope.plotAssetGoals($scope.assetGoals);
							if (result.data.marital_status == "Yes") {
								$scope.nospouse = true;
							}
							//$scope.plotGraphSpouse($scope.startAge);
							// $scope.makeRetirement();
							// plotAssetChart();
							// editAssetChart();

							$scope.loading = false;
							$scope.masked = false;
						}, function(error) {
						});
	};
	$scope.load1();
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
        //editchart();
    };

    $scope.plotAssetGoals = function($data) {
        $scope.chartGoals = [];
        for (var i = 0; i < $data.length; i++) {
            $scope.chartGoals.push({
                value : $data[i].value,
                "allowDrag" : "0"
            });
        }
       // editchart();
    };
	$scope.planchange = function($event, index) {

		for (var i = 0; i < data.length; i++)
			data[i].totalExpense = 0;
		$rootScope.finplan_name = $event;
		$scope.selected = index;
		$scope.fetchPlanDetails($rootScope.finplan_name);
		$scope
				.changeIncomeProfile($scope.planNames1[$scope.selected].profileName);
		$rootScope.incomeProfilesChart = $scope.planNames1[$scope.selected].profileName;

	};

	$scope.fetchPlanDetails = function($event) {
		// alert( "Plan name: " +$event );
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
		$http({
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
							 * alert( "hello" ); alert( JSON.stringify(
							 * result.data.userIncomeProfile ) ); alert(
							 * JSON.stringify( result.data.spouseIncomeProfile ) );
							 */
							$scope.editExpenseFlag = result.data.editExpenseFlag;
							$scope.housingExpense = result.data.housingExpense;
							$scope.nonHousingExpense = result.data.nonHousingExpense;
							$rootScope.checkboxData.housingExpense = result.data.housingExpense;
							$rootScope.checkboxData.nonHousingExpense = result.data.nonHousingExpense;
							$scope.planDetailsAsset = [];
							$scope.planDetailsTax = [];
							$scope.spouseIncomeProfile = [];
							$scope.combinedIncomeProfile = [];
							// console.log( result.data.Goals );
							$scope.goalsTemp = [];
							$scope.goalsTemp = result.data.Goals;
							// console.log( $scope.goalsTemp );
							for (i = 0; i < $scope.goalsTemp.length; i++) {
								if ($scope.goalsTemp[i].indexOf("-") != -1)
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
							for (i = 0; i < data.length; i++)
								data[i].totalExpense = $scope.totalExpense[i].totalExpense;
							$scope.expensesForGraph = result.data.userExpense;
							$scope.taxForGraph = result.data.tax;

							$scope.userIncomeProfile = result.data.userIncomeProfile;
							// alert( JSON.stringify(
							// result.data.equity ) )
							$scope.properties_equity = result.data.equity;
							$rootScope.marital_status_finPlan = result.data.marital_status;
							if ($rootScope.marital_status_finPlan == "Yes") {
								$scope.nospouse = true;
								$scope.spouseIncomeProfile = result.data.spouseIncomeProfile;
								$scope.combinedIncomeProfile = result.data.combinedIncomeProfile;
							} else {
								$scope.nospouse = false;
							}
							// alert( $rootScope.chartYearExpense );
							for (var i = 0; i < $rootScope.chartYearExpense.length - 1; i++) {
								if ($scope.totalExpense[i].year * 1 == $rootScope.chartYearExpense[i].label * 1) {
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
										if ($rootScope.marital_status_finPlan == "Yes") {
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
										$scope.chartAssets.push({
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
								$scope.plotExpense($scope.ExpenseIncome );
								// alert(" car
								// "+JSON.stringify($scope.ExpenseDetails));
								$scope.plotAssetGoals($scope.assetGoals);

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
								if ($scope.ExpenseDetails.carGoalExpense != null) {
									/*
									 * alert( JSON.stringify(
									 * $scope.ExpenseDetails.carGoalExpense));
									 */
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
										 * 1; $yearEnd = $year * 1 + 3;
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

									$rootScope.marital_status == "Yes";
									for (h = 0; h < $scope.spouseIncomeProfile.length; h++) {
										if ($scope.spouseIncomeProfile[h].year * 1 == $scope.tableIncome[h].year) {
											$scope.tableIncome[h].spouseValue = $scope.spouseIncomeProfile[h].value;
											$scope.spouseIncome[h].value = $scope.spouseIncomeProfile[h].value;
										}
									}
									for (i = 0; i < $rootScope.chartYear.length; i++) {
										if ($rootScope.chartYear[i].label == $scope.marriageYear) {
											for (var l = 0; l < i; l++) {
												$rootScope.chartIncomeSpouse[l].allowDrag = "0";
											}
										}
									}
									$scope.MarriageYear = result.data.MarriageYear;
									$scope.spouceAge = result.data.spouceAge;
									/*
									 * $scope.spousebirthYear =
									 * $scope.MarriageYear - $scope.spouceAge;
									 */
									$scope.spousebirthYear = result.data.spouceBirthYear;
									$scope.SuccessMessage_marriage = "A new income profile has been created for the marriage goal";
									$("#warning-alert2").show();
									$("#warning-alert2").fadeTo(2000, 500)
											.slideUp(1000, function() {
												$("#warning-alert2").hide();
											});
								}

								if (result.data.retirementFlag == true) {
									// data = [];
									$scope.tempData = [];
									$rootScope.modifiedchartIncome = [];
									$rootScope.modifiedchartIncomeSpouse = [];
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
									// tempData[tempDatalength - 1].year =
									// $scope.userIncomeProfile[$scope.userIncomeProfile.length
									// - 1].year;
									// tempData[tempDatalength - 1].userIncome =
									// $scope.userIncomeProfile[$scope.userIncomeProfile.length
									// - 1].value;

									for (var k = 0; k < tempDatalength; k++) {
										drag.push(tempData[k].year);
										tempYears.push(tempData[k].year);
									}
									for (i = 0; i < $scope.userIncomeProfile.length; i++) {
										$rootScope.modifiedchartIncome[i] = {
											"year" : $scope.userIncomeProfile[i].year,
											"userIncome" : $scope.userIncomeProfile[i].value
										};
									}

									$rootScope.modifiedchartIncomeBackup = $rootScope.modifiedchartIncome;
									if ($rootScope.marital_status == "Yes") {
										tempData1 = result.data.spousePlot;

										$scope.spousedefaultretirementAge = result.data.spouseRetirementAge;
										$scope.defaultSpouseRetirementAge = result.data.spouseRetirementAge;
										$scope.tempRetAgeSpouse = result.data.spouseRetirementAge
												+ $scope.spousebirthYear;
										var tempCombined = [];
										for (i = 0; i < $scope.combinedIncomeProfile.length; i++) {
											tempCombined[i] = $scope.combinedIncomeProfile[i].value;
										}
										maxY = Math.max.apply(Math,
												tempCombined) + 20000;
										maxY1 = Math.max.apply(Math,
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
													}).indexOf(
															tempData1[i].year);
											tempData1[i].userIncome = $scope.spouseIncome1[tempDataIndex].value;
										}
										// tempData1[tempData1length - 1].year =
										// $scope.spouseIncome1[$scope.spouseIncome1.length
										// - 1].year;
										// tempData1[tempData1length -
										// 1].spouseIncome =
										// $scope.spouseIncome1[$scope.spouseIncome1.length
										// - 1].value;
										for (var k = 0; k < tempData1length; k++) {
											drag1.push(tempData1[k].year);
											tempYears1.push(tempData1[k].year);
										}
									} else {
										$scope.maritalFlag_imp = false;
										var tempUser = [];
										for (var i = 0; i < $scope.userIncomeProfile.length; i++) {
											tempUser[i] = $scope.userIncomeProfile[i].value;
										}
										maxY = Math.max.apply(Math, tempUser) + 40000;
										maxY1 = Math.max.apply(Math, tempUser) + 10000;
									}

									for (var i = 0; i < $scope.userIncomeProfile.length; i++) {

										if ($rootScope.marital_status != "Yes") {
											data[i].userIncome = $scope.userIncomeProfile[i].value;
											data[i].year = $scope.userIncomeProfile[i].year;

										} else {
											if ($scope.spouseIncome1.length <= i) {
												data[i].userIncome = $scope.income[i].value;
												data[i].year = $scope.income[i].year;
												data[i].spouseIncome = 0;
												data[i].combinedIncome = $scope.combined_income[i].value;

												$rootScope.modifiedchartIncomeSpouse
														.push({
															"year" : $scope.income[i].year,
															"spouseIncome" : 0
														});
											} else {
												data[i].userIncome = $scope.income[i].value;
												data[i].year = $scope.income[i].year;
												data[i].spouseIncome = $scope.spouseIncome1[i].value;
												data[i].combinedIncome = $scope.combined_income[i].value;

												$rootScope.modifiedchartIncomeSpouse
														.push({
															"year" : $scope.income[i].year,
															"spouseIncome" : $scope.spouseIncome1[i].value
														});
											}
											$rootScope.modifiedchartIncomeSpouseBackup = $rootScope.modifiedchartIncomeSpouse;
										}
									}
									/* D3 code ends */

									d3.selectAll("svg#incomeExpense").remove();
									d3.selectAll("svg#growthAsset").remove();

									var indexYearUser = data
											.map(function(obj) {
												return obj.year;
											})
											.indexOf(
													tempYears[tempYears.length - 2] - 1);
									$scope.dataUserIncome = data[indexYearUser].userIncome;
									if ($rootScope.marital_status == "Yes") {
										var indexYearSpouse = data
												.map(function(obj) {
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
										for (var i = 0; i < $rootScope.chartYearExpense.length; i++) {
											if ($rootScope.chartYearExpense[i].label == $scope.ExpenseDetails.vacation_expense.startingYear) {
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
										for (var i = 0; i < $rootScope.chartYearExpense.length; i++) {

											if ($rootScope.chartYearExpense[i].label >= $scope.ExpenseDetails.vacation_expense.startingYear) {
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
										for (var i = 0; i < $rootScope.chartYearExpense.length; i = i + 2) {

											if ($rootScope.chartYearExpense[i].label >= $scope.ExpenseDetails.vacation_expense.startingYear) {
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
										if ($rootScope.marital_status_finPlan == "Yes") {
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
										$scope.chartAssets.push({
											value : $scope.sum,
											"allowDrag" : "0"
										});
									}
								}
								/*
								 * if ( result.data.retirementFlag==true ) {
								 * $scope.drawD3Chart();
								 * 
								 * //plotAssetChart(); }
								 */

								// $scope.makeRetirement();
								d3.selectAll("svg#incomeExpense").remove();
								d3.selectAll("svg#growthAsset").remove();
								$scope.drawD3Chart();
								$scope.DrawAssetsAreaChart();
								$scope.DrawTaxesAreaChart();
								//editchart();
								// editAssetChart();
								// $scope.masked=false;
								// $scope.maskedPlan=false;
							}
						}, function(error) {
						});

	};

	$scope.changeIncomeProfile = function($profileName) {
		$rootScope.incomeProfilesChart = $profileName;
		$scope.masked = true;
		/* $scope.chartAssets=[]; */
		$scope.IncomeDetails.profile_name = $profileName;
		$scope.IncomeDetails.form = "getIncomeProfile";
		$http({
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
								$rootScope.checkboxData.housingExpense = result.data.housingExpense;
								$rootScope.checkboxData.nonHousingExpense = result.data.nonHousingExpense;
								d3.selectAll("svg#incomeExpense").remove();
								d3.selectAll("svg#growthAsset").remove();
								// data = [];
								$scope.tempData = [];
								$rootScope.modifiedchartIncome = [];
								$rootScope.modifiedchartIncomeSpouse = [];
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
								$scope.properties_equity = result.data.equity;
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
									var tempDataIndex = $scope.user_income.map(
											function(obj) {
												return obj.year;
											}).indexOf(tempData[i].year);
									tempData[i].userIncome = $scope.user_income[tempDataIndex].value;
								}
								// tempData[tempDatalength - 1].year =
								// $scope.user_income[$scope.user_income.length
								// - 1].year;
								// tempData[tempDatalength - 1].userIncome =
								// $scope.user_income[$scope.user_income.length
								// - 1].value;
								for (var k = 0; k < tempDatalength; k++) {
									drag.push(tempData[k].year);
									tempYears.push(tempData[k].year);
								}
								// alert( "In change income:
								// "+$scope.retirementYear );
								// alert(
								// result.data.marital_status );
								$rootScope.marital_status_imp = result.data.marital_status;
								len = $scope.user_income.length;
								if ($rootScope.marital_status_imp == "Yes") {
									tempData1 = result.data.spousePlot;
									$rootScope.marital_status = "Yes";
									$scope.maritalFlag_imp = true;
									$scope.spouse_income = result.data.spouse_income;
									// alert( JSON.stringify(
									// $scope.spouse_income ) );

									$scope.combined_income = result.data.combined_income;
									var tempCombined = [];
									for (i = 0; i < $scope.combined_income.length; i++) {
										tempCombined[i] = $scope.combined_income[i].value;
									}
									maxY = Math.max.apply(Math, tempCombined) + 20000;
									maxY1 = Math.max.apply(Math, tempCombined) + 5000;
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
									// tempData1[tempData1length - 1].year =
									// $scope.spouse_income[$scope.spouse_income.length
									// - 1].year;
									// tempData1[tempData1length -
									// 1].spouseIncome =
									// $scope.spouse_income[$scope.spouse_income.length
									// - 1].value;
									for (var k = 0; k < tempData1length; k++) {
										drag1.push(tempData1[k].year);
										tempYears1.push(tempData1[k].year);
									}
								} else {
									$rootScope.marital_status = "No";
									$scope.maritalFlag_imp = false;
									var tempUser = [];
									for (var i = 0; i < $scope.user_income.length; i++) {
										tempUser[i] = $scope.user_income[i].value;
									}
									maxY = Math.max.apply(Math, tempUser) + 40000;
									maxY1 = Math.max.apply(Math, tempUser) + 10000;
								}
								// alert( $scope.maritalFlag_imp
								// );
								$scope.tax_incomeProfile = result.data.tax_incomeProfile;
								$scope.asset_incomeProfile = result.data.asset_incomeProfile;
								/* D3 code */

								$scope.expensesForGraph = result.data.userExpense;
								$scope.taxForGraph = result.data.tax_incomeProfile;

								for (i = 0; i < $scope.user_income.length; i++) {
									$rootScope.modifiedchartIncome[i] = {
										"year" : $scope.user_income[i].year,
										"userIncome" : $scope.user_income[i].value
									}
								}
								$rootScope.modifiedchartIncomeBackup = $rootScope.modifiedchartIncome;

								for (var i = 0; i < $scope.user_income.length; i++) {

									if (result.data.spouse_income == undefined
											|| result.data.spouse_income == null
											|| result.data.spouse_income.length < 1) {
										data[i].year = $scope.user_income[i].year;
										data[i].userIncome = $scope.user_income[i].value;
										if (!$scope.finPlanDetails) {
											if (data[i].totalExpense != $scope.expensesForGraph[i].totalExpense) {
												data[i].totalExpense = $scope.expensesForGraph[i].totalExpense;
											}
										}
									} else {
										if ($scope.spouse_income.length <= i) {
											data[i].year = $scope.user_income[i].year;
											data[i].userIncome = $scope.user_income[i].value;
											data[i].spouseIncome = 0;
											data[i].combinedIncome = $scope.combined_income[i].value;
											if (!$scope.finPlanDetails) {
												if (data[i].totalExpense != $scope.expensesForGraph[i].totalExpense) {
													data[i].totalExpense = $scope.expensesForGraph[i].totalExpense;
												}
											}
											$rootScope.modifiedchartIncomeSpouse
													.push({
														"year" : $scope.user_income[i].year,
														"spouseIncome" : 0
													});
										} else {
											data[i].year = $scope.user_income[i].year;
											data[i].userIncome = $scope.user_income[i].value;
											data[i].spouseIncome = $scope.spouse_income[i].value;
											data[i].combinedIncome = $scope.combined_income[i].value;
											if (!$scope.finPlanDetails) {
												if (data[i].totalExpense != $scope.expensesForGraph[i].totalExpense) {
													data[i].totalExpense = $scope.expensesForGraph[i].totalExpense;
												}
											}
											$rootScope.modifiedchartIncomeSpouse
													.push({
														"year" : $scope.spouse_income[i].year,
														"spouseIncome" : $scope.spouse_income[i].value
													});
										}
										$rootScope.modifiedchartIncomeSpouseBackup = $rootScope.modifiedchartIncomeSpouse;
									}
								}

								/* D3 code ends */
								console.log(data);
								var indexYearUser = data.map(function(obj) {
									return obj.year;
								}).indexOf(tempYears[tempYears.length - 2] - 1);
								$scope.dataUserIncome = data[indexYearUser].userIncome;
								if ($rootScope.marital_status == "Yes") {
									var indexYearSpouse = data
											.map(function(obj) {
												return obj.year;
											})
											.indexOf(
													tempYears1[tempYears1.length - 2] - 1);
									$scope.dataSpouseIncome = data[indexYearSpouse].spouseIncome;
								}
								$scope.drawD3Chart();
								// plotAssetChart();
								// console.log( $rootScope.chartYear
								// );
								// alert( $rootScope.Count );
								// alert(
								// $rootScope.chartYear[$rootScope.chartYear.length-1].label
								// );
								// alert( JSON.stringify(
								// $scope.tableIncome_imp ) );
								for (var i = 0; i < $rootScope.chartYear.length - 1; i++) {

									if ($rootScope.chartYear[i].label == $scope.user_income[i].year
											&& $scope.income[i].year == $scope.user_income[i].year) {
										$scope.income[i].value = $scope.user_income[i].value;
										$rootScope.chartIncome[i].value = $scope.user_income[i].value;
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
										$scope.chartAssets.push({
											value : $scope.total,
											"allowDrag" : "0"
										});
										// console.log(
										// $scope.tableIcome )
										if ($rootScope.marital_status_imp == "Yes"
												&& $rootScope.chartYear[i].label == $scope.spouse_income[i].year
												&& $rootScope.chartYear[i].label == $scope.combined_income[i].year) {
											$scope.chartCombinedIncome[i].value = $scope.combined_income[i].value;
											// console.log(
											// $rootScope.chartYear[i]
											// );
											// alert(
											// $scope.spouse_income[i].year
											// );
											// $rootScope.chartIncomeSpouse[i].value
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
								$scope.DrawAssetsAreaChart();
								$scope.DrawTaxesAreaChart();
								//editchart();
								/* editAssetChart(); */
								$scope.masked = false;
							} else if ($scope.message == "fail") {

							}

						});
	};
	$scope.plotGraph = function($age) {
        $rootScope.chartYear = [];
        $rootScope.chartIncome = [];
        for (var i = 0; i < $scope.income.length; i++) {
            /*
             * if ( $scope.income[i].year == ( $scope.birthYear *
             * 1 + $age * 1 ) ) { // //alert(
             * $scope.income[i].year ) break; } else {
             */
            $rootScope.chartYear.push({
                label : $scope.income[i].year.toString()
            });
            $rootScope.chartIncome.push({
                value : $scope.income[i].value,
                year : $scope.income[i].year,
                "allowDrag" : "1"
            });

            // }
        }
        $scope.yearModal(new Date().getFullYear());
       //editchart();
    };
    
    $scope.yearModal = function($datecurr) {
        $rootScope.chartYear2 = [];
        for (var i = 0; i < $rootScope.chartYear.length; i++) {
            if ($rootScope.chartYear[i].label > $datecurr) {

                $rootScope.chartYear2.push({
                    label : $rootScope.chartYear[i].label
                            .toString()
                });
            }
        }
    };
    
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
        for(var i=0;i<data.length;i++) {
        	var taxTempCal = $scope.taxForGraph[i].fICAMedicareTax +
            $scope.taxForGraph[i].fICASocialSecurityTax +
            $scope.taxForGraph[i].federalTax +
            $scope.taxForGraph[i].spouseSSTax +
            $scope.taxForGraph[i].stateTax +
            $scope.taxForGraph[i].userSSTax;
        	data[i].tax = Math.round(taxTempCal).toFixed(2);                       	
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
                    "nvtooltip xy-tooltip").style("opacity", 1).style("position", "absolute").style("transform", "translate(-85px)");

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

            this.redraw()();
            self.plotUser();
            if ($scope.marital_status == "Yes") {
                self.plotSpouse();
                self.plotCombined();
            }
            self.plotExpense();
            this.plotPointsUser();
            userIncomeGraph = true;
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
                                            	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + d.userIncome.toFixed(0) + "</td></tr>" +
                                    			"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                    			"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                    			"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                    			"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                        .style("left",(d3.event.pageX) + "px")
                                        .style("top",(d3.event.pageY - 28)+ "px");
                                    } else {
                                    	div.html(
                                    	"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year + "</strong></td></tr></thead><tbody>" +
                                    	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + d.userIncome.toFixed(0) + "</td></tr>" +
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
                                        			"<tr style='border-top: 1px solid #32e2e2;border-bottom: 1px solid #32e2e2;'><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + d.spouseIncome.toFixed(0) + "</td></tr>" +
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
                                                    	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + p1.userIncome.toFixed(0) + "</td></tr>" +
                                                    	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                                    	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                                    	"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                                    	"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                                .style("left",(d3.event.pageX) + "px")
                                                .style("top",(d3.event.pageY - 28)+ "px");
                                            } else {
                                            	div.html(
                                            	"<table><thead><tr><td colspan=3><strong class=x-value>" + p1.year + "</strong></td></tr></thead><tbody>" +
                                            	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + p1.userIncome.toFixed(0) + "</td></tr>" +
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
                                                	"<tr style='border-top: 1px solid #32e2e2;border-bottom: 1px solid #32e2e2;'><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + p1.spouseIncome.toFixed(0) + "</td></tr>" +
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
                            			"<tr style='border-top: 1px solid #e43234;border-bottom: 1px solid #e43234;'><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + d.totalExpense.toFixed(0) + "</td></tr>" +
                                    	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                    	"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                    	"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                .style("left",(d3.event.pageX) + "px")
                                .style("top",(d3.event.pageY - 28)+ "px");
                            } else {
                            	div.html(
                            	"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year + "</strong></td></tr></thead><tbody>" +
                            	"<tr><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + data[myPoint].userIncome.toFixed(0) + "</td></tr>" +
                            	"<tr style='border-top: 1px solid #e43234;border-bottom: 1px solid #e43234;'><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + d.totalExpense.toFixed(0) + "</td></tr>" +
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
                                        	"<tr style='border-top: 1px solid #b0c10c;border-bottom: 1px solid #b0c10c;'><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + d.combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
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
                                            	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + d.userIncome.toFixed(0) + "</td></tr>" +
                                            	"<tr><td class=legend-color-guide><div style='background-color: #e43234;'></div></td><td class=key> Expense </td><td class=value>" + data[myPoint].totalExpense.toFixed(0) + "</td></tr>" +
                                            	"<tr><td class=legend-color-guide><div style='background-color: #ff7f0e;'></div></td><td class=key> Tax </td><td class=value>" + data[myPoint].tax.toFixed(0) + "</td></tr>" +
                                            	"<tr><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + data[myPoint].spouseIncome.toFixed(0) + "</td></tr>" +
                                            	"<tr><td class=legend-color-guide><div style='background-color: #b0c10c;'></div></td><td class=key> Combined Income </td><td class=value>" + data[myPoint].combinedIncome.toFixed(0) + "</td></tr></tbody></table>")
                                        .style("left",(d3.event.pageX) + "px")
                                        .style("top",(d3.event.pageY - 28)+ "px");
                                    } else {
                                    	div.html(
                                    	"<table><thead><tr><td colspan=3><strong class=x-value>" + d.year + "</strong></td></tr></thead><tbody>" +
                                    	"<tr style='border-top: 1px solid #328de4;border-bottom: 1px solid #328de4;'><td class=legend-color-guide><div style='background-color: #328de4;'></div></td><td class=key> User Income </td><td class=value>" + d.userIncome.toFixed(0) + "</td></tr>" +
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
                                        	"<tr style='border-top: 1px solid #32e2e2;border-bottom: 1px solid #32e2e2;'><td class=legend-color-guide><div style='background-color: #32e2e2;'></div></td><td class=key> Spouse Income </td><td class=value>" + d.spouseIncome.toFixed(0) + "</td></tr>" +
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
                ;
                if (self.dragged1) {
                    self1.dragged1.spouseIncome = self.y
                            .invert(Math.max(0, Math.min(
                                    self.size.height, p[1])));
                    self.updateSpouse();
                }
                ;
                if (!isNaN(self.downx)) {
                    d3.select("body").style("cursor",
                            "ew-resize");
                    var rupx = self.x.invert(p[0]), xaxis1 = self.x
                            .domain()[0], xaxis2 = self.x
                            .domain()[1], xextent = xaxis2
                            - xaxis1;
                    if (rupx != 0) {
                        var changex, new_domain;
                        changex = self.downx / rupx;
                        new_domain = [ xaxis1,
                                xaxis1 + (xextent * changex) ];
                        self.x.domain(new_domain);
                        self.redraw()();
                    }
                    d3.event.preventDefault();
                    d3.event.stopPropagation();
                }
                ;
                if (!isNaN(self.downy)) {
                    d3.select("body").style("cursor",
                            "ns-resize");
                    var rupy = self.y.invert(p[1]), yaxis1 = self.y
                            .domain()[1], yaxis2 = self.y
                            .domain()[0], yextent = yaxis2
                            - yaxis1;
                    if (rupy != 0) {
                        var changey, new_domain;
                        changey = self.downy / rupy;
                        new_domain = [
                                yaxis1 + (yextent * changey),
                                yaxis1 ];
                        self.y.domain(new_domain);
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
                if (!isNaN(self.downx)) {
                    self.redraw()();
                    self.downx = Math.NaN;
                    d3.event.preventDefault();
                    d3.event.stopPropagation();
                }
                ;
                if (!isNaN(self.downy)) {
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
                                "cursor", "ew-resize").on(
                                "mouseout",
                                self.after_axis_drag).on(
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
                // .on( "mouseover", function( d ) {
                // d3.select( this ).style( "font-weight",
                // "bold" );} )
                .on("mouseout", self.after_axis_drag).on(
                        "mousedown.drag", self.yaxis_drag())
                        .on("touchstart.drag",
                                self.yaxis_drag());

                gy.exit().remove();
                // self.plot.call( d3.behavior.zoom().x( self.x
                // ).y( self.y ).on( "zoom",
                // self.redraw() ) );
                if (self.dragged != null)
                    self.updateUser();
                if (self.dragged1 != null)
                    self.updateSpouse();
            };

        };

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

                self.vis.selectAll("svg#spouseIncomeSVG")
                        .remove();
                self.plotSpouse();

                self.vis.selectAll("svg#combinedIncomeSVG")
                        .remove();
                self.plotCombined();

                self.vis.selectAll("svg#expenseSVG").remove();
                self.plotExpense();
                
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
                "color" : "rgb(43, 206, 115)"
            }, {
                key : "Taxable",
                y : firstPie.taxable_investment_amount,
                "color" : "rgb(46, 74, 236)"
            }, {
                key : "Tax-Advantaged",
                y : firstPie.nontaxable_investment_amount,
                "color" : "rgb(243, 102, 33)"
            }, {
                key : "Property ",
                y : $scope.properties_equity[0].value,
                "color" : "rgb(252, 179, 22)"
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

        // alert( JSON.stringify( federalTaxBR ) )
        $scope.dataTax = [ 
        {
            "key" : "Federal",
            "values" : federalTaxBR,
            "color" : "rgb(43, 206, 115)"
        },{
            "key" : "State",
            "values" : stateTaxArrBR,
            "color" : "rgb(46, 74, 236)"
        }, {
            "key" : "Social Security Tax",
            "values" : userfICASocialSecurityTaxBR,
            "color" : "rgb(243, 102, 33)"
        }, {
            "key" : "Medicare",
            "values" : fICAMedicareTaxBR,
            "color" : "rgb(127, 144, 248)"
        },/* {
            "key" : "Spouse Fica",
            "values" : spousefICASocialSecurityTaxBR,
            "color" : "#8E44AD"
        }*/{
            "key" : "Property Tax ",
            "values": propertyTaxValue,
            "color" : "rgb(252, 179, 22)"
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
            "color" : "rgb(43, 206, 115)"
        }, {
            "key" : "Taxable Investment",
            "values" : taxableBR,
            "color" : "rgb(46, 74, 236)"
        }, {
            "key" : "Tax-Advantaged Investments",
            "values" : nontaxableBR,
            "color" : "rgb(243, 102, 33)"
        }, {
            "key" : "Property",
            "values" : dashBoardProperties,
            "color" : "rgb(252, 179, 22)"
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
                    forceY: [0, Math.ceil(maxValuesAssetGraph[0] / 1000) * 1000 ],
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
    
    $scope.changeAge = function() {
        Array.prototype.splice.apply($scope.income, [ 0,
                $rootScope.chartIncome.length ]
                .concat($rootScope.chartIncome));
        if ($rootScope.marital_status == "Yes") {
            Array.prototype.splice.apply($scope.spouseIncome, [
                    0, $rootScope.chartIncomeSpouse.length ]
                    .concat($rootScope.chartIncomeSpouse));
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
                            "color" : "rgb(43, 206, 115)"
                        },
                        {
                            key : "Taxable ",
                            y : $scope.tableIncome[i].taxable_investment_amount,
                            "color" : "rgb(46, 74, 236)"
                        },
                        {
                            key : "Tax-Advantaged ",
                            y : $scope.tableIncome[i].nontaxable_investment_amount,
                            "color" : "rgb(243, 102, 33)"
                        },
                        {
                            key : "Property ",
                            y : $scope.properties_equity[i].value,
                            "color" : "rgb(252, 179, 22)"
                        } ];
                //console.log($scope.dataPieChartBR);
            }
        }
        //console.log( $scope.dataPieChartBR);
    };
    
    $scope.limiting = function(category) {
        // alert( "hi" )
        if (category.id <= 99) {
            return category;
        }
    };
    
    $scope.restore = function() {
        d3.selectAll("svg#incomeExpense").remove();

        d3.selectAll("svg#growthAsset").remove();
        $scope.load1();
        // $scope.changeIncomeProfile(
        // $rootScope.incomeProfilesChart );
        // $scope.clearGraph();
        $scope.showIncomeExp = false;
        // SimpleGraph.prototype.update();

        // d3.select( "#slider3" ).remove();
        // $scope.load1();
        $scope.isShow = false;
        $scope.isShowExpenseToggle = false;
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
    
    $scope.makeRetirement = function() {
        $scope.retirementYear = $scope.birthYear * 1
                + $scope.defaultretirementAge * 1;
        for (var i = 0; i < $rootScope.chartYear.length; i++) {
            if ($rootScope.chartYear[i].label * 1 == $scope.retirementYear * 1) {

                $scope.retirementPoint = i;
                break;
            } else {
                $scope.retirementPoint = $rootScope.chartYear.length - 1;
            }
        }
        for (j = $scope.retirementPoint; j < $rootScope.chartYear.length; j++) {

            $rootScope.chartIncome[j].allowDrag = "0";

        }
        // alert( $rootScope.marital_status );
        if ($rootScope.marital_status == "Yes") {
            // /alert( "birtYear"+$scope.spousebirthYear );
            $scope.spouseretirementYear = $scope.spousebirthYear
                    + $scope.spousedefaultretirementAge;
            // alert( $scope.spouseretirementYear );
            for (i = 0; i < $rootScope.chartYear.length; i++) {
                if ($rootScope.chartYear[i].label == $scope.spouseretirementYear) {
                    $scope.spouseRetirementPoint = i;
                    break;
                } else {
                    $scope.spouseRetirementPoint = $rootScope.chartYear.length - 1;
                }

            }
            for (j = $scope.spouseRetirementPoint; j < $rootScope.chartYear.length; j++) {
                $rootScope.chartIncomeSpouse[j].allowDrag = "0";
            }
            $scope.dragPoint1[$scope.dragPoint1.length - 1] = $scope.spouseRetirementPoint;
        }
        $scope.dragPoint[$scope.dragPoint.length - 1] = $scope.retirementPoint;
    };

    $scope.addExpense = function($Syear, $Eyear, $expense) {
        for (var i = 0; i < $rootScope.chartYearExpense.length; i++) {
            if ($rootScope.chartYearExpense[i].label >= $Syear
                    && $rootScope.chartYearExpense[i].label <= $Eyear) {
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
    };
    $scope.addExpenseGoals = function($Syear, $Eyear, $expense) {

        for (var i = 0; i < $rootScope.chartYearExpense.length; i++) {
            if ($rootScope.chartYearExpense[i].label >= $Syear
                    && $rootScope.chartYearExpense[i].label <= $Eyear) {

                $scope.chartGoals[i].value = $scope.chartGoals[i].value
                        * 1 + $expense * 1;
                data[i].totalExpense = data[i].totalExpense * 1
                        + $expense * 1;
            }
        }

    };
    
    
    $scope.selectThisYear = function() {
        $scope.messageChangeIncome = "";
        if ($rootScope.checkboxData.applyThisYear == true) {
            $rootScope.checkboxData.applyGraduallyYear = false;
            $rootScope.checkboxData.applyFutureYear = false;
            $scope.incomeProfileName = false;
            $rootScope.checkboxData.profileName = "";

        }

    };
    
    
    
	$scope.items = [ 'item1', 'item2', 'item3' ];
	$scope.animationsEnabled = true;

	$scope.open = function(size) {
		var modalInstance = $uibModal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'myModalContent.html',
			controller : 'ModalInstanceCtrl',
			size : size,
			resolve : {
				items : function() {
					return $scope.items;
				}
			}
		});

		modalInstance.result.then(function(selectedItem) {
			$scope.selected = selectedItem;
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

	$scope.toggleAnimation = function() {
		$scope.animationsEnabled = !$scope.animationsEnabled;
	};
	
	$rootScope.$on("showToaster", function(event,data){
		console.log(data);
		console.log(event);	
		if(data.type == "updateExpenses") {
			if(data.status == "success") {
				toaster.pop('success', "", "{template: 'toasterData.html', data: 'Expense Saved Successfully!!'}", 5000, 'templateWithData');
			} else {
				toaster.pop('warning', "", "{template: 'toasterData.html', data: 'Unable to save'}", 5000, 'templateWithData');
			}
		} else if(data.type == "editExpenses") {
			if(data.status == "success") {
				toaster.pop('success', "", "{template: 'toasterData.html', data: 'Expense Saved Successfully!!'}", 5000, 'templateWithData');
				$scope.openSaveModal('md');
			} else {
				toaster.pop('warning', "", "{template: 'toasterData.html', data: 'Unable to save'}", 5000, 'templateWithData');
			}
		} else if(data.type == "saveIncome") {
			if (data.status == "success") {
            	toaster.pop('success', "", "{template: 'toasterData.html', data: 'User income saved successfully !!'}", 5000, 'templateWithData');
                $scope.messageChangeIncome = "";
                $rootScope.checkboxData.profileName = "";
                $rootScope.chartIncomeSpouse = [];
                $rootScope.chartIncome = [];
                $rootScope.chartIncomeSpouse = [];
                $rootScope.chartYear = [];
                $rootScope.chartIncome = [];
                $scope.planNames1 = [];
                $rootScope.chartIncomeSave = [];
                $rootScope.spouseIncomeSave = [];
                $rootScope.chartYearSave = [];
                $scope.tableIncome = [];
                $scope.tableIncome_imp = [];
                $scope.chartCombinedIncome = [];
                $rootScope.chartYearExpense = [];
                d3.selectAll("svg#incomeExpense").remove();
                d3.selectAll("svg#growthAsset").remove();
                
            } else {
               
                if (data.status == "income profile already exist") {
                    toaster.pop('warning', "", "{template: 'toasterData.html', data: 'Profile name already exits!!'}", 5000, 'templateWithData');
                } else {
                	toaster.pop('warning', "", "{template: 'toasterData.html', data: 'Your assets are going negative. Hence the income profile will not be updated'}", 5000, 'templateWithData');
                }
            }
            $scope.load1();	
		}
		
     });
}

angular.module('wealthsetter').controller('SaveIncomeCtrl', function ($scope,$rootScope,items,$http,$state,Auth,$uibModalInstance) {
	
	
	$rootScope.checkboxData.applyYear.value = "true";
	$scope.ok = function () {
	    $uibModalInstance.close($scope.selected.item);
	  };

	  $scope.cancel = function () {
	    $uibModalInstance.dismiss('cancel');
	  };
	  
	$scope.selectYear = function() {
		 if($rootScope.checkboxData.applyYear.value == true || $rootScope.checkboxData.applyYear.value == "true") {
			 $rootScope.checkboxData.applyGraduallyYear = false;
            $scope.incomeProfileName = false;
            $rootScope.checkboxData.profileName = "";
		 } else {
			 $scope.incomeProfileName = true;
		 }
	 }
	$scope.save = function() {
		console.log($rootScope.incomeProfilesChart);
        $scope.masked = true;
        $scope.saveFlag = 0;
        $scope.profileNameCheckbox = $rootScope.checkboxData.profileName;
        $scope.IncomeDetails = {};

            if ($rootScope.checkboxData.applyYear.value == true || $rootScope.checkboxData.applyYear.value == "true") {
                $scope.IncomeDetails.profile_name = $rootScope.incomeProfilesChart;
            } else {
            	$scope.IncomeDetails.profile_name = $rootScope.checkboxData.profileName;
            }
           
            if (($rootScope.checkboxData.applyYear.value == true || $rootScope.checkboxData.applyYear.value == "true")
                    && $rootScope.checkboxData.applyPlan == false) {

                $scope.IncomeDetails.form = "update";
                $scope.IncomeDetails.planCount = 0;
            } else if (($rootScope.checkboxData.applyYear.value == "false" || $rootScope.checkboxData.applyYear.value == false)
                    && $rootScope.checkboxData.applyPlan == false) {
                $scope.IncomeDetails.form = "create";
                $scope.IncomeDetails.planCount = 0;
            } else if (($rootScope.checkboxData.applyYear.value == true || $rootScope.checkboxData.applyYear.value == "true")
                    && $rootScope.checkboxData.applyPlan == true) {
            	
                $scope.IncomeDetails.form = "update";
                $scope.IncomeDetails.planCount = $rootScope.Count;
                $scope.IncomeDetails.plan_name = $rootScope.finplan_name;

            } else if (($rootScope.checkboxData.applyYear.value == "false" || $rootScope.checkboxData.applyYear.value == false)
                    && $rootScope.checkboxData.applyPlan == true) {

                $scope.IncomeDetails.form = "create";
                $scope.IncomeDetails.planCount = $rootScope.Count;
                $scope.IncomeDetails.plan_name = $rootScope.finplan_name;

            }

            $rootScope.chartIncomeSpouseOld = [];
            $rootScope.chartIncomeOld = [];

            Array.prototype.splice.apply(
                    $rootScope.chartIncomeSave, [ 0,
                            $rootScope.chartIncome.length ]
                            .concat($rootScope.chartIncome));
            if ($rootScope.marital_status == "Yes") {
                Array.prototype.splice
                        .apply(
                                $rootScope.spouseIncomeSave,
                                [
                                        0,
                                        $rootScope.chartIncomeSpouse.length ]
                                        .concat($rootScope.chartIncomeSpouse));
            }
            Array.prototype.splice.apply($rootScope.chartYearSave,
                    [ 0, $rootScope.chartYear.length ]
                            .concat($rootScope.chartYear));

            $rootScope.chartIncomeSpouseOld = $rootScope.spouseIncomeSave;
            $rootScope.chartIncomeOld = $rootScope.chartIncomeSave;
            /*
             * var len = $rootScope.modifiedchartIncome.length; for(
             * j=0;j<$scope.tempData.length;j++ ) {
             * $rootScope.modifiedchartIncome[len] =
             * $scope.tempData[j]; len++; }
             */

            $scope.finalIncome = [];
            if ($rootScope.marital_status == "Yes") {
                for (i = 0; i < $rootScope.modifiedchartIncomeSpouse.length; i++) {
                    $scope.finalIncome
                            .push({
                                "year" : $rootScope.modifiedchartIncome[i].year,
                                "userIncome" : $rootScope.modifiedchartIncome[i].userIncome,
                                "spouseIncome" : $rootScope.modifiedchartIncomeSpouse[i].spouseIncome
                            });
                }
            } else {
                for (i = 0; i < $rootScope.modifiedchartIncome.length; i++) {
                    $scope.finalIncome
                            .push({
                                "year" : $rootScope.modifiedchartIncome[i].year,
                                "userIncome" : $rootScope.modifiedchartIncome[i].userIncome
                            });
                }
            }

            $scope.IncomeDetails.income = JSON
                    .stringify($scope.finalIncome);
            $scope.IncomeDetails.year = JSON
                    .stringify($rootScope.chartYearSave);
            $scope.IncomeDetails.userPlot = JSON
                    .stringify(tempData);
            if ($rootScope.marital_status == "Yes") {
                $scope.IncomeDetails.spouseIncome = JSON
                        .stringify($rootScope.spouseIncomeSave);
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
                            	console.log(result.data);
                                                                
                                $scope.message = result.data.status;
                                result.data.type = "saveIncome";
                                $rootScope.$emit("showToaster", result.data);
                                console.log("message"
                                        + $scope.message);
                                //$scope.masked = false;
                            },
                            function(error) {
                            	console.log("fail");
                                $scope.message = "fail";

                            });
            	$scope.cancel();

    };
});

angular.module('wealthsetter').controller('UpdateExpenseCtrl', function ($scope,$rootScope,items,$http,$state,Auth,$uibModalInstance) {
	$scope.expenseValues = [ 0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9,
		             			1.0 ];
		$scope.ok = function () {
		    $uibModalInstance.close($scope.selected.item);
		  };

		  $scope.cancel = function () {
		    $uibModalInstance.dismiss('cancel');
		  };
		  
		  $scope.proceedUpdateExpenses = function() {
				 $scope.masked = true;
				$scope.ExpenseDetails = {};
	          $scope.ExpenseDetails.nonHousingExpense = $rootScope.checkboxData.nonHousingExpense;
	          $scope.ExpenseDetails.housingExpense = $rootScope.checkboxData.housingExpense;
	          $scope.ExpenseDetails.profile_name = $rootScope.incomeProfilesChart;
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
	                              result.data.type = "updateExpenses";
	                              if ($scope.message == "success") {
	                              	$rootScope.editExpenseFlag = result.data.editExpenseFlag;                             	
	                              }
	                              $rootScope.$emit("showToaster", result.data);
	                              console.log("message" + $scope.message);
	                              $scope.masked = false;
	                          },
	                          function(error) {
	                              $scope.message = "error";
	                          });
	          		$scope.cancel();
	  }
});

angular.module('wealthsetter').controller('EditExpenseCtrl', function ($scope,$rootScope,items,$http,$state,Auth,$uibModalInstance) {
	
	$scope.expenseValues = [ 0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9,
	             			1.0 ];
	$scope.ok = function () {
	    $uibModalInstance.close($scope.selected.item);
	  };

	  $scope.cancel = function () {
	    $uibModalInstance.dismiss('cancel');
	  };
	  $scope.showEditExpense = function(input) {
	     	if(input == "yes") {
	     		$scope.showEditValues = true;
	     	} else {
	     		$scope.showEditValues = false;
	     	}
	     };
  
  $scope.proceedEditExpenses = function() {
  	if($rootScope.checkboxData.editExp == "yes") {
  			//$scope.masked = true;
  				$scope.ExpenseDetails = {};
              $scope.ExpenseDetails.nonHousingExpense = $rootScope.checkboxData.nonHousingExpense;
              $scope.ExpenseDetails.housingExpense = $rootScope.checkboxData.housingExpense;
              $scope.ExpenseDetails.profile_name = $rootScope.incomeProfilesChart;
              $scope.ExpenseDetails.form = "saveExpenses";
              //console.log($scope.ExpenseDetails);
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
                             	 console.log(result.data);
                                  $scope.message = result.data.status;
                                  result.data.type = "editExpenses";
                                  if ($scope.message == "success") {
                                	  $rootScope.editExpenseFlag = result.data.editExpenseFlag;
                                	  $scope.cancel();
                                  } else {
                                 	 
                                  }  
                                  $rootScope.$emit("showToaster", result.data);
                                  console.log("message" + $scope.message);
                                  //$scope.masked = false;
                              },
                              function(error) {
                             	 console.log("fail");
                                  $scope.message = "fail";
                              });
  	}  else if ($rootScope.checkboxData.editExp == "no") {
  		$rootScope.checkboxData.housingExpense = "";
      	$rootScope.checkboxData.nonHousingExpense = "";
      	$scope.cancel();
  	}
  };
});