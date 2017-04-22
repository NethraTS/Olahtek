(function($) {
    "use strict"; // Start of use strict

    // jQuery for page scrolling feature - requires jQuery Easing plugin
    $('a.page-scroll').bind('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: ($($anchor.attr('href')).offset().top - 50)
        }, 1250, 'easeInOutExpo');
        event.preventDefault();
    });

    // Highlight the top nav as scrolling occurs
    $('body').scrollspy({
        target: '.navbar-fixed-top',
        offset: 51
    });

    // Closes the Responsive Menu on Menu Item Click
    $('.navbar-collapse ul li a').click(function() {
        $('.navbar-toggle:visible').click();
    });

    // Offset for Main Navigation
    $('#mainNav').affix({
        offset: {
            top: 100
        }
    })

    // Initialize and Configure Scroll Reveal Animation
    window.sr = ScrollReveal();
    sr.reveal('.sr-icons', {
        duration: 600,
        scale: 0.3,
        distance: '0px'
    }, 200);
    sr.reveal('.sr-button', {
        duration: 1000,
        delay: 200
    });
    sr.reveal('.sr-contact', {
        duration: 600,
        scale: 0.3,
        distance: '0px'
    }, 300);

    // Initialize and Configure Magnific Popup Lightbox Plugin
    $('.popup-gallery').magnificPopup({
        delegate: 'a',
        type: 'image',
        tLoading: 'Loading image #%curr%...',
        mainClass: 'mfp-img-mobile',
        gallery: {
            enabled: true,
            navigateByImgClick: true,
            preload: [0, 1] // Will preload 0 - before current, and 1 after the current image
        },
        image: {
            tError: '<a href="%url%">The image #%curr%</a> could not be loaded.'
        }
    });

})(jQuery); // End of use strict

angular.module('wealthsetter', ['ui.bootstrap', 'ui.router', 'ngCookies','nvd3','angularBootstrapMaterial','ngSanitize', 'ui.select']);
'use strict';

/**
 * Route configuration for the RDash module.
 */
angular.module('wealthsetter').config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        // For unmatched routes
        $urlRouterProvider.otherwise('/');

        // Application routes
        $stateProvider
            .state('index', {
                url: '/',
                templateUrl: 'templates/home.html',
                controller:'HomeCtrl'
            })
            .state('welcome', {
                url: '/welcome',
                templateUrl: 'templates/welcome.html',
                controller:'WelcomeCtrl'
            })
            .state('welcome.0', {
                url: '/basic',
                templateUrl: 'templates/welcome.basic.html'
            })
            .state('welcome.1', {
                url: '/income-expense',
                templateUrl: 'templates/welcome.income-expense.html'
            })
            .state('welcome.2', {
                url: '/assets',
                templateUrl: 'templates/welcome.assets.html'
            })
            .state('dashboard', {
                url: '/dashboard',
                templateUrl: 'templates/dashboard.html',
                controller:'DashboardCtrl'
            })
        	.state('dashboard.home',{
        		url: '/home',
                templateUrl: 'templates/dashboard.home.html'
        	})
        	.state('dashboard.tables', {
                url: '/tables',
                templateUrl: 'templates/dashboard.tables.html'
            });
    }
]);
angular.module('wealthsetter')
    .controller('DashboardCtrl', ['$scope','$rootScope','$uibModal', '$log','$http','$state' ,DashboardCtrl]);
function DashboardCtrl($scope,$rootScope,$uibModal, $log,$http,$state,$uibModalInstance) {
	$rootScope.boolChangeClass = true;
	$scope.box1= false;
    $scope.alerts = [{
        type: 'success',
        msg: 'Thanks for visiting!!'
    }];

    $scope.addAlert = function() {
        $scope.alerts.push({
            msg: 'Another alert!'
        });
    };

    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

     $scope.options = {
            chart: {
                type: 'lineChart',
                height: 450,
                margin : {
                    top: 20,
                    right: 20,
                    bottom: 40,
                    left: 55
                },
                x: function(d){ return d.x; },
                y: function(d){ return d.y; },
                useInteractiveGuideline: true,
                dispatch: {
                    stateChange: function(e){ console.log("stateChange"); },
                    changeState: function(e){ console.log("changeState"); },
                    tooltipShow: function(e){ console.log("tooltipShow"); },
                    tooltipHide: function(e){ console.log("tooltipHide"); }
                },
                xAxis: {
                    axisLabel: 'Time (ms)'
                },
                yAxis: {
                    axisLabel: 'Voltage (v)',
                    tickFormat: function(d){
                        return d3.format('.02f')(d);
                    },
                    axisLabelDistance: -10
                },
                callback: function(chart){
                    console.log("!!! lineChart callback !!!");
                }
            },
            
            subtitle: {
                enable: true,
                text: '',
                css: {
                    'text-align': 'center',
                    'margin': '10px 13px 0px 7px'
                }
            }
        };

        $scope.data = sinAndCos();

        /*Random Data Generator */
        function sinAndCos() {
            var sin = [],sin2 = [],
                cos = [];

            //Data is represented as an array of {x,y} pairs.
            for (var i = 0; i < 100; i++) {
                sin.push({x: i, y: Math.sin(i/10)});
                sin2.push({x: i, y: i % 10 == 5 ? null : Math.sin(i/10) *0.25 + 0.5});
                cos.push({x: i, y: .5 * Math.cos(i/10+ 2) + Math.random() / 10});
            }

            //Line chart data should be sent as an array of series objects.
            return [
                {
                    values: sin,      //values - represents the array of {x,y} data points
                    key: 'Income', //key  - the name of the series.
                    color: 'rgb(0, 194, 129)',  //color - optional: choose your own line color.
                    strokeWidth: 2,
                    classed: 'dashed'
                },
                {
                    values: cos,
                    key: 'Expense',
                    color: 'rgb(46, 77, 199)'
                }
            ];
        };


        $scope.items = ['item1', 'item2', 'item3'];
        $scope.animationsEnabled = true;

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

        

}



/**
 * Alerts Controller
 */

angular.module('wealthsetter')
    .controller('HomeCtrl', ['$scope','$uibModal', '$log','$http','$state' ,HomeCtrl]);
function HomeCtrl($scope,$uibModal, $log,$http,$state,$uibModalInstance) {
	
    $scope.alerts = [{
        type: 'success',
        msg: 'Thanks for visiting!!'
    }];

    $scope.addAlert = function() {
        $scope.alerts.push({
            msg: 'Another alert!'
        });
    };

    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

    

        $scope.items = ['item1', 'item2', 'item3'];
        $scope.animationsEnabled = true;

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

        $scope.errorNameregister = "Please enter basic details";
    	$scope.show1 = 1;
    	$scope.formData1 = {};
    	$scope.formData1.kids = [];
    	$scope.formData = {};
    	$scope.formDatalogin = {};
    	$scope.formDatalogin.name = "";
    	$scope.formDatalogin.password = "";
    	$scope.formDatalogin.forgotMail == "";
    	$scope.formData.form = "form1";
    	$scope.formData1 = {};
    	$scope.formData2 = {};
    	$scope.formData3 = {};
    	$scope.formData4 = {};
    	$scope.formData1.kids = [];
    	$scope.sessionDetails = {};
    	$scope.dependants = false;
    	$scope.filingOptions = [];
    	$scope.kidForSingle = false;

    	function readCookie( name ) {
    		var nameEQ = name + "=";
    		var ca = document.cookie.split( ";" );
    		for ( var i = 0; i < ca.length; i++ ) {
    			var c = ca[i];
    			while ( c.charAt( 0 ) == " " ) {
    				c = c.substring( 1, c.length );
    			}
    			if ( c.indexOf( nameEQ ) == 0 ) {
    				return c.substring( nameEQ.length, c.length );
    				}
    		}
    		return null;
    	}

    	$scope.load = function() {
    		$scope.show1 = 1;
    		$scope.sessionDetails.cookieId = readCookie( "AhTwxlO" );
    		$scope.sessionDetails.lastVisitedPage = document.URL;
    		if ( $scope.sessionDetails.cookieId != null ) {

    			$http( {
    				method: "POST",
    				url: "CheckSession",
    				data: $.param( $scope.sessionDetails ),
    				headers: {
    					"Content-Type": "application/x-www-form-urlencoded"
    				}
    			} )
    					.then( 			function( result ) {
    								if ( result.data.status == "success" ) {
    									$state.go('welcome.0'); 
    									$uibModalInstance.dismiss('cancel');
    									if ( result.data.lastVisitedPage == "undefined" ) {
    										//window.location.href = "dashboardUserr0.jsp";
    					                 
       
    									} /*else {
    										//document.cookie = "lastVisitedPage="
    											//	+ result.data.lastVisitedPage;
    										$state.go('index');
    											//window.location.href = result.data.lastVisitedPage;
    									}*/
    								} else {
    									$state.go('index');
    									$uibModalInstance.dismiss('cancel');
    								}

    							}, function( error ) {
    							} );
    		}
    	}

    	/*--------------------for clearing log in form data at close model------------------------------------------------------*/
    	$scope.clear = function() {
    		$scope.formData.name = "";
    		$scope.formData.password = "";
    		$scope.message = "";
    		$scope.forgotPasswordResponse = "";
    		$scope.formDatalogin.name = "";
    		$scope.formDatalogin.password = "";

    	}
    	/*------------------------------------------------------for login validation--------------------------------------------------*/
    	$scope.checkloginform = function() {

    		$scope.message = "";
    		$scope.errorName = "";
    		$scope.errorSuperhero = "";

    	}
    	$scope.checkloginformemail = function() {

    		$scope.message = "";
    		$scope.errorName = "";
    		$scope.errorSuperhero = "";
    		if ( $scope.formDatalogin.name == ""
    				|| $scope.formDatalogin.name == undefined ) {

    			$scope.errorName = "Please enter email address";
    		} else if ( !( /^[a-zA-Z0-9]+[a-zA-Z0-9._-]+[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z.]{2,5}$/ )
    				.test( $scope.formDatalogin.name ) ) {
    			$scope.errorName = "Please enter valid email address";
    		}
    	}
    	$scope.forgot = function() {
    		$scope.show1 = 2;
    		$scope.formDatalogin.forgotMail = $scope.formDatalogin.name;
    		/* $scope.formDatalogin.forgotMail=""; */

    	}

    	// /check for dependents=========

    	$scope.loginBack = function() {
    		$scope.show1 = 1;

    		$scope.formDatalogin.name = $scope.formDatalogin.forgotMail;

    	}
    	$scope.checkForgotPaswwordForm = function() {
    		$scope.errorNameForgotPassword = "";
    		$scope.forgotPasswordResponse = "";
    	}
    	$scope.submitEmail = function() {

    		if ( $scope.formDatalogin.forgotMail == ""
    				|| $scope.formDatalogin.forgotMail == undefined ) {

    			$scope.errorNameForgotPassword = "Please enter email address";

    		} else {
    			$http( {
    				method: "POST",
    				url: "ResetPassword",
    				data: $.param( $scope.formDatalogin ),
    				headers: {
    					"Content-Type": "application/x-www-form-urlencoded"
    				}
    			} )
    					.then(
    							function( result ) {

    								if ( result.data.status == "success" ) {

    									$scope.forgotPasswordResponse = "An Email with a new password has been sent to your registered email id !!";

    								} else {
    									$scope.forgotPasswordResponse = result.data.status;
    								}
    								// alert(  "Success" );

    							}, function( error ) {
    								// alert(  "fail" );

    							} );

    		}
    	}
    	$scope.login = function() {

    		$scope.message = "";
    		$scope.errorName = "";
    		$scope.errorSuperhero = "";
    		if ( $scope.formDatalogin.name == ""
    				|| $scope.formDatalogin.name == undefined ) {
    			$scope.errorName = "Please enter email address";
    		} else if ( !( /^[a-zA-Z0-9]+[a-zA-Z0-9._-]+[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z.]{2,5}$/ )
    				.test( $scope.formDatalogin.name ) ) {
    			$scope.errorName = "Please enter valid email address";
    		} else if ( $scope.formDatalogin.password == ""
    				|| $scope.formDatalogin.password == undefined ) {
    			$scope.errorSuperhero = "Please enter password ";
    		}

    		else {
    			$scope.errorNameregister = "";

    			$scope.response = {};

    			$http( {
    				method: "POST",
    				url: "Login",
    				data: $.param( $scope.formDatalogin ),
    				headers: {
    					"Content-Type": "application/x-www-form-urlencoded"
    				}
    			} ).then( function( result ) {

    				$scope.response = result.data;
    				// //alert(  $scope.response.status );
    				if ( angular.equals( $scope.response.status, "success" ) ) {
    					// alert(  $scope.response.completedStatus );
    					if ( $scope.response.role == "admin" ) {
    						window.location.href = "Admindashboard.jsp";
    					} else if ( $scope.response.completedStatus == 1 ) {
    						window.location.href = "dashboardUser0.jsp";
    					} else {
    						//window.location.href = "finInfo.jsp";
    						$state.go('welcome.0');
    						$uibModalInstance.dismiss('cancel');
    					}
    				} else {
    					if ( result.data == "incorrect_password" ) {
    						$scope.message = "incorrect password"
    					}
    					// //alert(  result.data.status );
    					else {
    						$scope.message = result.data;
    					}
    				}
    				$scope.errorName = "";
    				$scope.errorSuperhero = "";
    			}, function( error ) {

    				$scope.message = result.data;
    				$scope.errorName = "";
    				$scope.errorSuperhero = "";
    			} );

    		}
    	}
    	/*---------------------------------------------------------------------------------------------------------------------------------*/

    	$scope.usercheck = function() {

    		$scope.errorNameregister = "";
    		$scope.formData.username == "";
    		if ( ( $scope.formData.username != "" || $scope.formData.username != undefined )
    				&& ( $scope.formData.password != "" || $scope.formData.password != undefined ) ) {
    			if ( sentMessage.email.$dirty || sentMessage.email.$invalid ) {
    				$scope.errorNameregister = "Please enter valid email address";
    			}
    		}

    	}

    	$scope.passworderror = function() {
    		if ( $scope.formData.password == undefined
    				|| $scope.formData.password == "" ) {
    			$scope.errorNameregister = "Password should have atleast 8 alphanumeric characters including an upper case";
    		}

    	}
    	$scope.errorclean = function() {
    		$scope.errorNameregister = "";
    	}
    	$scope.user = {
	      name: "",
	      email:"",
	      mobile:""
	    }
	    $scope.errorMap = {
	      required: "This field is mandatory",
	      email: "Please enter a valid email"
	    }
	    $scope.change = function () {
	      console.log($scope);
	    }

    	
}

// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.angular
angular.module('wealthsetter')
.controller('ModalInstanceCtrl', function ($scope,items,$http,$state,$uibModalInstance) {
  $scope.items = items;
  $scope.selected = {
    item: $scope.items[0]
  };

  $scope.ok = function () {
    $uibModalInstance.close($scope.selected.item);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
  
  $scope.validate = function() {
		/*----------------------------------------------------------------------------------------------------------------------*/
		$scope.errorNameregister = ""
			console.log($scope.formData);
		if ( ( $scope.formData.username == undefined || $scope.formData.username == "" )
				&& ( $scope.formData.email == undefined || $scope.formData.email == "" )
				&& ( $scope.formData.password == undefined || $scope.formData.password == "" ) ) {
			$scope.errorNameregister = "Please enter user details";
		} else if ( $scope.formData.username == undefined
				|| $scope.formData.username == "" ) {
			$scope.errorNameregister = "Please enter username";
		} else if ( $scope.formData.username.length < 3
				|| $scope.formData.username.length > 16 ) {
			$scope.errorNameregister = "Username must have 3 to 16 characters only";

		} else if ( $scope.formData.email == undefined
				|| $scope.formData.email == "" ) {
			$scope.errorNameregister = "Please enter email address";
		}

		else if ( !( /^[a-zA-Z0-9]+[a-zA-Z0-9._-]+[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z.]{2,5}$/ )
				.test( $scope.formData.email ) ) {
			$scope.errorNameregister = "Please enter valid email address";
		} else if ( $scope.formData.password == undefined
				|| $scope.formData.password == "" ) {
			$scope.errorNameregister = "Please enter password";
		} else if ( $scope.formData.confirmpassword == undefined
				|| $scope.formData.confirmpassword == "" ) {
			$scope.errorNameregister = "Please enter Confirm password";
		} else if ( ( $scope.formData.password.length ) > 16 ) {
			$scope.errorNameregister = "Password should not have more than 16 characters";
		} else if ( ( $scope.formData.password.length ) < 8 ) {
			$scope.errorNameregister = "Password should have atleast 8 alphanumeric characters including an upper case";
		} else if ( ( /^[A-Za-z0-9!@#$%^&*(   )_]{8,16}$/ )
				.test( $scope.formData.password ) ) {
			if ( ( /^[\S]+$/ ).test( $scope.formData.password ) ) {

				if ( ( /^[^a-z]+$/ ).test( $scope.formData.password ) ) {
					$scope.errorNameregister = "Password should have atleast one lower case character";
				} else if ( ( /^[^A-Z]+$/ ).test( $scope.formData.password ) ) {
					$scope.errorNameregister = "Password should have atleast one upper case character";
				} else if ( ( /^[^0-9]+$/ ).test( $scope.formData.password ) ) {
					$scope.errorNameregister = "Password should have atleast one numerical character";
				}
				/*
				 * else
				 * if(  (  /^[^!@#$%^&*(   )_+]+$/ ).test(  $scope.formData.password ) ) {
				 * $scope.errorNameregister="Password should have atleast one
				 * special character"; }
				 */
				else if ( $scope.formData.password != $scope.formData.confirmpassword ) {
					$scope.errorNameregister = "Mismatch of Password & Confirm Password";
				} else {

					$http( 
							{
								method: "POST",
								url: "Register",
								data: $.param( $scope.formData ),
								headers: {
									"Content-Type": "application/x-www-form-urlencoded"
								}
							} )
							.then( 
									function( result ) {

										$scope.response = result.data;
										if ( angular.equals( 
												$scope.response.status,
												"registeredEmail" ) ) {
											$scope.errorNameregister = "email address already registered";

										}

										if ( angular.equals( 
												$scope.response.status,
												"success" ) ) {

											//window.location.href = "finInfo.jsp";
											$state.go('welcome.0');
											$uibModalInstance.dismiss('cancel');
										}
										$scope.errorName = "";
										$scope.errorSuperhero = "";
									}, function( error ) {
										$scope.message = result.data;
										$scope.errorName = "";
										$scope.errorSuperhero = "";
									} );
				}

			} else {
				// alert(  "fail" );
				$scope.errorNameregister = "Password should not have space";
			}

		} else {
			// alert(  "fail" );
			$scope.errorNameregister = "Password should not have space";
		}
		/*----------------------------------------------------------------------------------------------------------------------*/

	}

});

/**
 * Master Controller
 */

angular.module('wealthsetter')
    .controller('MasterCtrl', ['$scope', '$cookieStore','$state','$rootScope', MasterCtrl]);

function MasterCtrl($scope, $cookieStore,$state,$rootScope) {
    /**
     * Sidebar Toggle & Cookie Control
     */
	$rootScope.boolChangeClass = false;
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

    window.onresize = function() {
        $scope.$apply();
    };
    $rootScope.sessionDelete = {};
    
    
}
/**
 * Master Controller
 */

angular.module('wealthsetter')
    .controller('WelcomeCtrl', ['$scope', '$cookieStore','$state','$rootScope','$http', WelcomeCtrl]);

function WelcomeCtrl($scope, $cookieStore,$state,$rootScope,$http) {
	//$scope.screen = 0;
	/* redirect page based on state */
	$scope.screen = parseInt($state.current.name.split('.')[1]);
	$state.go('welcome.'+$scope.screen);
    $scope.changeItem = function(item){
    	$state.go('welcome.'+item);
    	$scope.screen = item;
    }
    /* End of redirect page based on state */
    /* listed States */
    $scope.states = [
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
    
    /* Read Cookie */
    function readCookie( name ) {
		var nameEQ = name + "=";
		var ca = document.cookie.split( ";" );
		for ( var i = 0; i < ca.length; i++ ) {
			var c = ca[i];
			while ( c.charAt( 0 ) == " " ) {
				c = c.substring( 1, c.length );
			}
			if ( c.indexOf( nameEQ ) == 0 ) {
				return c.substring( nameEQ.length, c.length );
				}
		}
		return null;
	}
    /* End of Read Cookie */
    
    $scope.welcome = {basic:{spouse:{}}};
    
    /* Used to fetch cities of selected state */
    
    $scope.fetchCities = function(state) {
		$rootScope.sessionDelete.sessionID = readCookie( "AhTwxlO" );
		$scope.input = {};
		$scope.input.city = state;
		$scope.welcome.basic.city = "";
		$scope.input.actionType = "getCities"
		$http({
			method: "POST",
			url: "AutoComplete",
			data: $.param($scope.input),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}
		}).then( function( result ) {
			$scope.cities = result.data;
			$scope.cities.sort();
		}, function( error ) {
			console.log(error);
		});
	}
    /* End of fetch cities of selected state */
    $scope.changeMartialStatus = function(status){
    	if(status == "No"){
    		$scope.welcome.basic.spouse ={};
    	}
    }
    
    
}
angular.module('wealthsetter').filter('propsFilter', function() {
	  return function(items, props) {
	    var out = [];

	    if (angular.isArray(items)) {
	      var keys = Object.keys(props);

	      items.forEach(function(item) {
	        var itemMatches = false;

	        for (var i = 0; i < keys.length; i++) {
	          var prop = keys[i];
	          var text = props[prop].toLowerCase();
	          if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
	            itemMatches = true;
	            break;
	          }
	        }

	        if (itemMatches) {
	          out.push(item);
	        }
	      });
	    } else {
	      // Let the output be the input untouched
	      out = items;
	    }

	    return out;
	  };
	});
/**
 * Loading Directive
 * @see http://tobiasahlin.com/spinkit/
 */

angular
    .module('wealthsetter')
    .directive('rdLoading', rdLoading);

function rdLoading() {
    var directive = {
        restrict: 'AE',
        template: '<div class="loading"><div class="double-bounce1"></div><div class="double-bounce2"></div></div>'
    };
    return directive;
};
angular.module('wealthsetter').directive("scroll", function ($window,$state) {
    return function(scope, element, attrs) {
    		var page = $state.current.name.split('.')[0];
    		angular.element($window).bind("scroll", function() {
	             if (this.pageYOffset >= 50 ) {
	                 scope.boolChangeClass = true;
	             }else if($state.current.name.split('.')[0] == "dashboard"){
	            	 scope.boolChangeClass = true;
	             } else {
	                 scope.boolChangeClass = false;
	             }
	            scope.$apply();
	        });
    	}

        
   
});

/**
 * Widget Body Directive
 */

angular
    .module('wealthsetter')
    .directive('rdWidgetBody', rdWidgetBody);

function rdWidgetBody() {
    var directive = {
        requires: '^rdWidget',
        scope: {
            loading: '=?',
            classes: '@?'
        },
        transclude: true,
        template: '<div class="widget-body" ng-class="classes"><rd-loading ng-show="loading"></rd-loading><div ng-hide="loading" class="widget-content" ng-transclude></div></div>',
        restrict: 'E'
    };
    return directive;
};

/**
 * Widget Footer Directive
 */

angular
    .module('wealthsetter')
    .directive('rdWidgetFooter', rdWidgetFooter);

function rdWidgetFooter() {
    var directive = {
        requires: '^rdWidget',
        transclude: true,
        template: '<div class="widget-footer" ng-transclude></div>',
        restrict: 'E'
    };
    return directive;
};
/**
 * Widget Header Directive
 */

angular
    .module('wealthsetter')
    .directive('rdWidgetHeader', rdWidgetTitle);

function rdWidgetTitle() {
    var directive = {
        requires: '^rdWidget',
        scope: {
            title: '@',
            icon: '@'
        },
        transclude: true,
        template: '<div class="widget-header"><div class="row"><div class="pull-left"><i class="fa" ng-class="icon"></i> {{title}} </div><div class="pull-right col-xs-6 col-sm-4" ng-transclude></div></div></div>',
        restrict: 'E'
    };
    return directive;
};
/**
 * Widget Directive
 */

angular
    .module('wealthsetter')
    .directive('rdWidget', rdWidget);

function rdWidget() {
    var directive = {
        transclude: true,
        template: '<div class="widget" ng-transclude></div>',
        restrict: 'EA'
    };
    return directive;

    function link(scope, element, attrs) {
        /* */
    }
};