/**
 * Master Controller
 */

angular.module('wealthsetter')
    .controller('MasterCtrl', ['$scope', '$uibModal', '$log', '$cookieStore','$state','$rootScope', '$http','Auth','$location', MasterCtrl]);

function MasterCtrl($scope, $uibModal, $log, $cookieStore,$state,$rootScope, $http,Auth,$location) {
    /**
     * Sidebar Toggle & Cookie Control
     */
	$rootScope.boolChangeClass = false;
    $scope.mobileView = 992;
    $scope.userdetails = {};
    $scope.sessionDetails = {};
	$scope.sessionDelete = {};
    $scope.getWidth = function() {
        return window.innerWidth;
    };
    
    /* listed States */
    $rootScope.states = [
                    "ALABAMA", "ALASKA", "ARIZONA", "ARKANSAS", "CALIFORNIA",
          			"COLORADO", "CONNECTICUT", "DELAWARE", "FLORIDA", "GEORGIA",
          			"HAWAII", "IDAHO", "ILLINOIS", "INDIANA", "IOWA", "KANSAS",
          			"KENTUCKY", "LOUISIANA", "MAINE", "MARYLAND", "MASSACHUSETTS",
          			"MICHIGAN", "MINNESOTA", "MISSISSIPPI", "MISSOURI", "MONTANA",
          			"NEBRASKA", "NEVADA", "NEW HAMPSHIRE", "NEW JERSEY", "NEW MEXICO",
          			"NEW YORK", "NORTH CAROLINA", "NORTH DAKOTA", "OHIO", "OKLAHOMA",
          			"OREGON", "PENNSYLVANIA", "RHODE ISLAND", "SOUTH CAROLINA",
          			"SOUTH DAKOTA", "TENNESSEE", "TEXAS", "UTAH", "VERMONT",
          			"VIRGINIA", "WEST VIRGINIA", "WISCONSIN", "WYOMING", "WASHINGTON",
          			"WASHINGTON DC" 
          			];
    /* end of listed State */
    $rootScope.loginLogoutText = "Login | Signup";
    $rootScope.homeLocation = "index";
    $rootScope.userFlag = true;
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
    $scope.$watch(Auth.isLoggedIn, function (value, oldValue) {
    	console.log(value);
    	console.log(oldValue);
        if(!value ) {
          console.log("Disconnect");
          
        }else{
        	console.log('active');
        	$location.path('/dashboard/home');
        }
      },true);
    
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
    $scope.cookieId = readCookie("AhTwxlO");
    if ($scope.cookieId != null || $scope.cookieId == "") {
    	$rootScope.loginLogoutText = "Logout";
    	$rootScope.homeLocation = "dashboard.home";
    } else {
    	$rootScope.loginLogoutText = "Login | Signup";
    	$rootScope.homeLocation = "index";
    }
 
    $scope.loginLogoutClick = function() {
    	if($rootScope.loginLogoutText == "Logout") {
    		$scope.deleteAllCookies();
    	} else {
    		$scope.open("lg");
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
			Auth.setUser(false);
			$location.path('/');
			$cookieStore.remove("AhTwxlO");
		}, function(error) {
			// ////alert( "Session not deleted" );
			Auth.setUser(false);
			$location.path('/');
		});
	};
	
    $scope.open = function (size) {
        var modalInstance = $uibModal.open({
          animation: $scope.animationsEnabled,
          templateUrl: 'myModalContent.html',
          controller: 'ModalInstanceCtrl',
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

    $scope.toggleAnimation = function () {
        $scope.animationsEnabled = !$scope.animationsEnabled;
    };
    
    $scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
        $cookieStore.put('toggle', $scope.toggle);
    };

    window.onresize = function() {
        $scope.$apply();
    };
    $rootScope.sessionDelete = {};
    
    $scope.load = function() {
		$scope.Onload();
		$scope.sessionDetails.cookieId = readCookie("AhTwxlO");
		$scope.sessionDetails.lastVisitedPage = document.URL.replace( "#", "" );
		console.log($scope.sessionDetails.cookieId);
		if ($scope.sessionDetails.cookieId != null) {
			$rootScope.loginLogoutText = "Logout";
			$http({
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
									Auth.setUser(result.data);
									//$location.path($location.path());
;									//$state.go('dashboard.home');
									/*if (result.data.lastVisitedPage == "undefined") {
										// window.location.href =
										// "dashboardUserr0.jsp";
										alert(Auth.isLoggedIn());
										$state.go('dashboard.home');
									}*/ 
									//$scope.load1();
								} else {
									$scope.deleteAllCookies();
								//	Auth.setUser(false);
									//alert(Auth.isLoggedIn());
									// window.location.href = "index.jsp";
									
								}
							}, function(error) {
							});
		} else {
			// ////alert( "Session got expired" );
			$scope.deleteAllCookies();
			// window.location.href = "index.jsp";
		}
	};
	$scope.Onload = function() {
		// alert( "onload" );
		// $scope.masked = true;
		// $scope.disabled = true;

		$scope.userdetails.form = "userDetails";
		$http({
			method : "POST",
			url : "UserProfile",
			data : $.param($scope.userdetails),
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded"
			}
		}).then(function(result) {
			$scope.filingStatus = result.data.filingStatus;
		}, function(error) {
			//$state.go('index');
			// //alert( "Fail" );
			$scope.message = "Fail";
			$scope.errorName = "";
			$scope.errorSuperhero = "";
		});
	};
    
}
