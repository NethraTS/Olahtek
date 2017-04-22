/**
 * Alerts Controller
 */

angular.module('wealthsetter')
    .controller('HomeCtrl', ['$scope','$rootScope','$uibModal', '$log','$http','$state','Auth',HomeCtrl]);
function HomeCtrl($scope,$rootScope,$uibModal, $log,$http,$state,Auth,$uibModalInstance) {
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

    

        //$scope.items = ['item1', 'item2', 'item3'];
        $scope.animationsEnabled = true;
        $rootScope.userFlag = true;
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

    	/*$scope.load = function() {
    		$scope.show1 = 1;
    		$scope.sessionDetails.cookieId = readCookie( "AhTwxlO" );
    		$scope.sessionDetails.lastVisitedPage = document.URL;
    		if ( $scope.sessionDetails.cookieId != null ) {

    			$http({
    				method: "POST",
    				url: "CheckSession",
    				data: $.param( $scope.sessionDetails ),
    				headers: {
    					"Content-Type": "application/x-www-form-urlencoded"
    				}
    			}).then(function( result ) {
					if ( result.data.status == "success" ) {
						Auth.setUser(result.data);
						$state.go('welcome.0');
						if($uibModalInstance !== undefined){
							$uibModalInstance.dismiss('cancel');
						}
						if ( result.data.lastVisitedPage == "undefined" ) {
							//window.location.href = "dashboardUserr0.jsp";
					                 
   
									} else {
							//document.cookie = "lastVisitedPage="
								//	+ result.data.lastVisitedPage;
							$state.go('index');
								//window.location.href = result.data.lastVisitedPage;
						}
					} else {
						//Auth.setUser(false);
						//$state.go('index');
						$uibModalInstance.dismiss('cancel');
					}
				}, function( error ) {
				} );
    		}
    	}*/

    	/*--------------------for clearing log in form data at close model------------------------------------------------------*/
    	$scope.clear = function() {
    		$scope.formData.name = "";
    		$scope.formData.password = "";
    		$scope.errorNameLogin = "";
    		$scope.forgotPasswordResponse = "";
    		$scope.formDatalogin.name = "";
    		$scope.formDatalogin.password = "";

    	}
    	/*------------------------------------------------------for login validation--------------------------------------------------*/
    	$scope.checkloginform = function() {

    		$scope.errorNameLogin = "";
    		$scope.errorName = "";
    		$scope.errorSuperhero = "";

    	}
    	$scope.checkloginformemail = function() {

    		$scope.errorNameLogin = "";
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
   
    	/*---------------------------------------------------------------------------------------------------------------------------------*/


    	$scope.passworderror = function() {
    		if ( $scope.formData.password == undefined
    				|| $scope.formData.password == "" ) {
    			$scope.errorNameregister = "Password should have atleast 8 alphanumeric characters including an upper case";
    		}

    	}
    	$scope.errorclean = function() {
    		$scope.errorNameregister = "";
    		$scope.errorNameLogin = "";
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
.controller('ModalInstanceCtrl', function ($scope,items,$http,$state,Auth,$uibModalInstance) {
  /*$scope.items = items;
  $scope.selected = {
    item: $scope.items[0]
  };*/
  $scope.tabName = "Login";
  $scope.hideForgot = true;
  $scope.forgot = function() {
	  $scope.hideForgot = false;
	  $scope.errorNameForgotPassword= "";
	  $scope.formData.forgotMail = "";
	  $scope.tabName = "Forgot Password";
  };
  
  $scope.showLogin = function() {
	  $scope.hideForgot = true;
	  $scope.formData.name = "";
	  $scope.formData.pwd = "";
	  $scope.errorNameLogin = "";
	  $scope.tabName = "Login";
  }
  $scope.ok = function () {
    $uibModalInstance.close($scope.selected.item);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
  
  $scope.checkForgotPaswwordForm = function() {
		$scope.errorNameForgotPassword = "";
	}
  
  $scope.submitEmail = function() {

		if ( $scope.formData.forgotMail == ""
				|| $scope.formData.forgotMail == undefined ) {

			$scope.errorNameForgotPassword = "Please enter email address";

		} else {
			$scope.errorNameForgotPassword = "";
			$http( {
				method: "POST",
				url: "ResetPassword",
				data: $.param( $scope.formData ),
				headers: {
					"Content-Type": "application/x-www-form-urlencoded"
				}
			} )
					.then(
							function( result ) {

								if ( result.data.status == "success" ) {

									$scope.errorNameForgotPassword = "An Email with a new password has been sent to your registered email id !!";

								} else {
									$scope.errorNameForgotPassword = result.data.status;
								}
								// alert(  "Success" );

							}, function( error ) {
								// alert(  "fail" );

							} );

		}
	}
  
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
											if($uibModalInstance !== undefined)
												$uibModalInstance.dismiss('cancel');
										}
										$scope.errorName = "";
										$scope.errorSuperhero = "";
									}, function( error ) {
										$scope.errorNameregister = result.data;
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
  
  $scope.checkloginform = function() {

		$scope.errorNameLogin = "";

	}
	$scope.checkloginformemail = function() {

		$scope.errorNameLogin = "";
		if ( $scope.formData.name == ""
				|| $scope.formData.name == undefined ) {

			$scope.errorNameLogin = "Please enter email address";
		} else if ( !( /^[a-zA-Z0-9]+[a-zA-Z0-9._-]+[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z.]{2,5}$/ )
				.test( $scope.formData.name ) ) {
			$scope.errorNameLogin = "Please enter valid email address";
		}
	}
	
	$scope.login = function() {

		$scope.errorNameLogin = "";
		$scope.errorNameregister = "";
		if ( $scope.formData.name == ""
				|| $scope.formData.name == undefined ) {
			$scope.errorNameLogin = "Please enter email address";
		} else if ( !( /^[a-zA-Z0-9]+[a-zA-Z0-9._-]+[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z.]{2,5}$/ )
				.test( $scope.formData.name ) ) {
			$scope.errorNameLogin = "Please enter valid email address";
		} else if ( $scope.formData.pwd == ""
				|| $scope.formData.pwd == undefined ) {
			$scope.errorNameLogin = "Please enter password ";
		}

		else {

			console.log($scope.formData);
			$scope.errorNameregister = "";
			$scope.errorNameLogin= "";
			$scope.response = {};

			$http( {
				method: "POST",
				url: "Login",
				data: $.param( $scope.formData ),
				headers: {
					"Content-Type": "application/x-www-form-urlencoded"
				}
			} ).then( function( result ) {

				$scope.response = result.data;
				if ( angular.equals( $scope.response.status, "success" ) ) {
					Auth.setUser(result.data);
					if ( $scope.response.role == "admin" ) {
						 
						//window.location.href = "Admindashboard.jsp";
						//$state.go('dashboard.home');
						alert("admin Dashboard");
					} else if ( $scope.response.completedStatus == 1 ) {
						//window.location.href = "dashboardUser0.jsp";
						$state.go('dashboard.home');
					} else {
						//window.location.href = "finInfo.jsp";
						$state.go('welcome.0');
					}
					$uibModalInstance.dismiss('cancel');
				} else {
					if ( result.data == "incorrect_password" ) {
						$scope.errorNameLogin = "incorrect password"
					}
					else {
						$scope.errorNameLogin = result.data;
					}
				}
			}, function( error ) {

				$scope.errorNameLogin = result.data;
			} );

		}
	}

});

