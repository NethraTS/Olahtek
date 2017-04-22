angular.module('wealthsetter').controller(
		'EmergencyCtrl',
		[ '$scope', '$rootScope', '$uibModal', '$log', '$http', '$state', '$timeout' ,'toaster',
		  EmergencyCtrl ]);
function EmergencyCtrl($scope, $rootScope, $uibModal, $log, $http, $state, $timeout, toaster, 
		$uibModalInstance) {
	
	$scope.$watch('loginLogoutText', function() {
        console.log($rootScope.loginLogoutText);
    });

	$rootScope.boolChangeClass = true;
	$scope.show1 = false;
	$scope.show2=true;
	$scope.sessionDetails = {};
	$scope.sessionDelete = {};
	$scope.formdata = {};
	$scope.time = [ "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "11", "12" ];
	$scope.time1 = [ "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "11", "12" ];
	$scope.minValue = "";
	//$scope.formdata.month = "12";
	//$scope.formdata.month1 = "6";
	$scope.masked = false;
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
	$scope.goalemergencyEditData = {};
	$scope.message = "Amount save"
		$scope.progressbar = 0;
		$scope.formdata.timePeriod = "Fix Amount";
	//$scope.formdata.plan_name = ( decodeURIComponent( hashes ) );

		$scope.progressBar = function() {
			alert("ddd");
			if ( $scope.formdata.timePeriod == null
					|| $scope.formdata.timePeriod == undefined
					|| $scope.formdata.timePeriod == "" ) {

				$scope.msgerr = "Please select atleast a checkbox !!";

			} else if ( $scope.formdata.timePeriod == "Fix Amount" ) {
				$scope.formdata.amountSave = $scope.minValue;

				$( "#progress_bar" ).css( "width", "50%" );
				$scope.message = "Amount save"

					$scope.show2=false;
				$scope.show1 = true;
				$scope.show1 = 2;

			}

			else if ( $scope.formdata.timePeriod == "Expense" ) {

				$scope.formdata.amountSave = ( ( $scope.tExpense / 12 ) * ( $scope.formdata.month ) );

				$( "#progress_bar" ).css( "width", "50%" );
				$scope.message = "Amount save"

					$scope.show2=false;
				$scope.show1 = true;

				$scope.show1 = 2;
			}

			else {

				$scope.formdata.amountSave = ( $scope.sixIncome / 6 * ( $scope.formdata.month1 ) );

				$( "#progress_bar" ).css( "width", "50%" );
				$scope.message = "Amount save"

					$scope.show2=false;
				$scope.show1 = true;

				$scope.show1 = 2;
			}

		}
		$scope.progressBar1 = function() {
			//alert("ff");
			$scope.masked = true;

			if ( $scope.formdata.amountSave == null
					|| $scope.formdata.amountSave == undefined
					|| $scope.formdata.amountSave == "" ) {
				$scope.masked = false;
				$scope.msgerr2 = "Time period should not be empty";

			}

			else {
				$( "#progress_bar" ).css( "width", "100%" );
				$( "#dialog_confirm_map" ).modal( "hide" );
				$scope.message = "DETAILS"

				$scope.show = 4;
				$scope.show1 = 3;

				$scope.formdata.actionHomeType = "insert";

				$http( 
						{
							method: "POST",
							url: "EmergencyFund",
							data: $.param( $scope.formdata ),
							headers: {
								"Content-Type": "application/x-www-form-urlencoded"
							}
						} )
						.then( 
								function( result ) {

									$scope.goalemergencyEditData = result.data;
									$scope.messresult = result.data.emi;
									$scope.timePeriod = result.data.timePeriod;
									$scope.amountSave = result.data.amountSave;
									$scope.formdata.emi = $scope.goalemergencyEditData.emi;

									if ( $scope.goalemergencyEditData.status == "success" ) {
										$scope.formdata.goal_id = $scope.goalemergencyEditData.goal_id;

										window.location.href = "Emergencyfundedit.jsp?goalid="
												+ $scope.formdata.goal_id;
										console.log( "message"
												+ $scope.message );
										$scope.masked = false;
									} else {
										$scope.masked = false;
										$scope.errmessage = "Goal is not feasible since you are not having sufficient funds !!";
										$( "#myModal" ).modal( "show" );

									}
									$scope.masked = false;
								}, function( error ) {
									$scope.message = result.data;

								} );

			}

		}
	}