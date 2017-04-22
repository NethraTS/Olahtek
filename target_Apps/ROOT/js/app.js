// define angular module/app["ui.bootstrap"]
var formApp = angular.module( "formApp", [ "ui.bootstrap" ] );
// create angular controller and pass in $scope and $http for index.html
var formApp1 = angular.module( "formApp1", [] );
// create angular controller and pass in $scope and $http for userprofile.jsp
var formApp2 = angular.module( "formApp2", [] );

/*-----------------------------------------------------------------------------*/

function formController2( $scope, $http ) {
	$scope.editBasicDetail = {};
	$scope.message = "hi";
	$scope.editBasicDetail = function() {
		$scope.message = "editBasicDetail";

		$http( {
			method: "POST",
			url: "Register",
			data: $.param( $scope.editBasicDetail ),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}
		} ).then( function( result ) {

			$scope.message = result.data;
			if ( result.data == "success" ) {
				window.location.href = "finInfo.jsp";
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

function formController1( $scope, $http ) {
	$scope.errorNameregister = "Please enter basic details";
	$scope.show1 = 1;
	$scope.formData1 = {};
	$scope.formData1.kids = [];
	$scope.kidcostFactor=false;
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
	$scope.kidCostCalculated=0;
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
									if ( result.data.lastVisitedPage == "undefined" ) {
										window.location.href = "dashboardUserr0.jsp";
									} else {
										document.cookie = "lastVisitedPage="
												+ result.data.lastVisitedPage;
											window.location.href = result.data.lastVisitedPage;
									}
								} else {
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
						window.location.href = "finInfo.jsp";
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
	/*----------------------------------------------------------------------------------------------------------------------*/

	$scope.validate = function() {
		/*----------------------------------------------------------------------------------------------------------------------*/
		$scope.errorNameregister = ""
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

											window.location.href = "finInfo.jsp";
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

}

// create angular controller and pass in $scope and $http for finInfo.html
function formController( $scope, $http ) {
	$scope.formData1 = {};
	$scope.formData3 = {};
	$scope.limit = true;
	$scope.limitSpouse = true;
	$scope.userdetails = {};
	$scope.sessionDetails = {};
	$scope.sessionDelete = {};
	$scope.errmessage = "";
	$scope.completedInvestment = 0;
	$scope.masked = false;
	$scope.show = 0;
	$scope.firstQuestion = "6";
	$scope.secondQuestion = "6";
	$scope.myVar = "";
	$scope.thirdQuestion = "6";
	$scope.fourthQuestion = "1";
	$scope.citys = [];
	$scope.choices = [ {} ];
	$scope.cities = [ {
		name: "New York"
	}, {
		name: "Los Angels"
	}, {
		name: "Chicago"
	}, {
		name: "Boston"
	} ];

	$scope.states = [ "ALABAMA", "ALASKA", "ARIZONA", "ARKANSAS", "CALIFORNIA",
			"COLORADO", "CONNECTICUT", "DELAWARE", "FLORIDA", "GEORGIA",
			"HAWAII", "IDAHO", "ILLINOIS", "INDIANA", "IOWA", "KANSAS",
			"KENTUCKY", "LOUISIANA", "MAINE", "MARYLAND", "MASSACHUSETTS",
			"MICHIGAN", "MINNESOTA", "MISSISSIPPI", "MISSOURI", "MONTANA",
			"NEBRASKA", "NEVADA", "NEW HAMPSHIRE", "NEW JERSEY", "NEW MEXICO",
			"NEW YORK", "NORTH CAROLINA", "NORTH DAKOTA", "OHIO", "OKLAHOMA",
			"OREGON", "PENNSYLVANIA", "RHODE ISLAND", "SOUTH CAROLINA",
			"SOUTH DAKOTA", "TENNESSEE", "TEXAS", "UTAH", "VERMONT",
			"VIRGINIA", "WEST VIRGINIA", "WISCONSIN", "WYOMING", "WASHINGTON",
			"WASHINGTON DC" ];
	$scope.ages = [];
	for ( i = 15; i < 100; i++ ) {
		var a = {
			number: i.toString()
		}
		$scope.ages.push( a );
	}

	$scope.options = [ {
		id: "No"
	}, {
		id: "Yes"
	} ];
	$scope.houses = [ {
		type: "Own"
	}, {
		type: "Rent"
	} ];
	$scope.limits = [ {
		id: "No"
	}, {
		id: "Yes"
	} ];
	$scope.kidsnumber = [];
	for ( i = 0; i < 11; i++ ) {
		var a = {
			number: i.toString()
		}
		$scope.kidsnumber.push( a );
	}
	$scope.kidsages = [];
	for ( i = 1; i < 26; i++ ) {
		var a = {
			id: i.toString()
		}
		$scope.kidsages.push( a );
	}

	$scope.student = [ {
		id: "No"
	}, {
		id: "Yes"
	} ];
	$scope.calculateEquity = function() {

		$scope.formData3.realestate = $scope.formData3.houseValue
				- $scope.formData3.remainingMortgage;

	}

	var typingTimer; // timer identifier
	var doneTypingInterval = 1; // time in ms, 5 second for example
	var $input = $( "#myInput" );

	// on keyup, start the countdown
	$input.on( "keyup", function() {

		clearTimeout( typingTimer );
		typingTimer = setTimeout( doneTyping, doneTypingInterval );

	} );

	// on keydown, clear the countdown
	$input.on( "keydown", function() {
		clearTimeout( typingTimer );

	} );

	$scope.doneTyping = function() {
		$scope.formData2.kidCostCalculated = 0;
		$scope.formData1.form = "calculateKidCost";
		// alert(  $scope.formData2.tax );

		if ( $scope.formData2.tax == "" || $scope.formData2.tax == "NaN"
				|| $scope.formData2.tax == undefined ) {
			$scope.formData2.tax = 0;
		}
		$scope.formData1.userIncome = $scope.formData2.tax * 1;
		if ( $scope.formData1.married == undefined ) {
			$scope.formData1.married = "No";
		}
		// alert(  $scope.formData1.married );
		if ( $scope.formData1.married == "Yes" ) {
			// alert(  "bala"+$scope.formData2.stax );
			if ( $scope.formData2.stax == "" || $scope.formData2.stax == "NaN"
					|| $scope.formData2.stax == undefined ) {
				$scope.formData2.stax = 0;
			}
			// alert(  $scope.formData2.stax*1 );
			$scope.formData1.spouceIncome = $scope.formData2.stax * 1;
			// alert(  $scope.formData1.spouceIncome );
		}

		$http( {
			method: "POST",
			url: "Register",
			data: $.param( $scope.formData1 ),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}
		} ).then( 
				function( result ) {
					// alert(  JSON.stringify(  result.data ) );
					$scope.formData2.expenses = Math.ceil( Math
							.ceil( result.data.cost ) / 12 );
					$scope.formData2.kidCostCalculated = Math.ceil( Math
							.ceil( result.data.cost ) / 12 );
					$scope.formData2.kidCostCalculatedArray = JSON
							.stringify( result.data.costArray );
					$scope.kidCostCalculated=$scope.formData2.kidCostCalculated;
				}, function( error ) {
					window.location.href = "index.jsp";
				} );

	}

	var typingTimer; // timer identifier
	var doneTypingInterval = 1; // time in ms, 5 second for example
	var $input = $( "#myInput1" );

	// on keyup, start the countdown
	$input.on( "keyup", function() {

		clearTimeout( typingTimer );
		typingTimer = setTimeout( doneTyping, doneTypingInterval );

	} );

	
	$input.on( "keydown", function() {
		clearTimeout( typingTimer );

	} );
	/*
	 * $(  document ).ready(  function(   ) { $(  "#myInput" ).bind(  "paste",
	 * function(  event ) { var _this = this; clearTimeout(  typingTimer );
	 * typingTimer = setTimeout(  doneTyping, doneTypingInterval ); } ); } );
	 */
	// user is "finished typing," do something
	/*-------------------------------------------auto complete--------------------------------------------*/
	$scope.autocomplete = function() {
		$scope.sessionDelete.sessionID = readCookie( "AhTwxlO" );
		$scope.formData1.actionType = "getCities"
		$http( {
			method: "POST",
			url: "AutoComplete",
			data: $.param( $scope.formData1 ),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}
		} ).then( function( result ) {
			$scope.citys = result.data;
			$scope.citys.sort();
		}, function( error ) {
			window.location.href = "index.jsp";
		} );
	}

	$scope.autocompleteForCounty = function() {
		$scope.formData1.actionType = "getCounty";
		$http( {
			method: "POST",
			url: "AutoComplete",
			data: $.param( $scope.formData1 ),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}
		} ).then( function( result ) {
			$scope.formData1.county = result.data;

		}, function( error ) {
			window.location.href = "index.jsp";
		} );
	}
	// --------------------------------- for invetment portfoliio
	// ---------------------------------
	$scope.next = function() {
		$scope.show++;
	}
	$scope.back = function() {
		$scope.show--;

	};

	// -------------------------------------------------------------------------------------------------------

	$scope.demo = function() {
		// alert(  "dsdsd" );
	}

	/*------------------------------hide selected city------------------------------------------------*/
	$scope.hideselectedcity = function() {
		$scope.formData1.country = "";
	}
	/*------------------------------------------------------------------------------*/
	$scope.funCheck = function() {
		// alert(  "hiiiiii" );

		// alert(  "checkDependants======" );
		$scope.dependantsErr = "";
		// alert(  "$scope.formData1.filingStatus======"+$scope.formData1.filingStatus )
		if ( $scope.formData1.filingStatus == "YES" ) {
			// alert(  "ppppp" )

			$scope.dependants = false;
			$scope.kidForSingle = true;
			$scope.dependantsCount = false;
			$scope.dependantsCount1 = true;
			$scope.formData1.kidscount = 0;
			$scope.formData1.dependants = 0;
			if ( $scope.formData1.kidscount == 0 ) {
				$scope.formData1.kids = null;
			}
		} else {
			$scope.kidForSingle = false;
			$scope.dependantsCount1 = false;
			$scope.dependants = false;
			$scope.formData1.kidscount = 0;
			$scope.formData1.dependants = 0;
			if ( $scope.formData1.kidscount == 0 ) {
				$scope.formData1.kids = null;
			}
		}
	}

	$scope.change = function() {
		// alert(  "chnge===" )
		$scope.filingOptions = [ {
			id: "YES"
		}, {
			id: "NO"
		} ];
		if ( $scope.formData1.married == "Yes" ) {
			$scope.marriedt = true;
			$scope.kidForSingle = true;
			$scope.hideStatus = false;
			$scope.dependantsCount1 = true;
			$scope.formData1.kidscount = 0;
			$scope.formData1.dependants = 0;
			if ( $scope.formData1.kidscount == 0 ) {
				$scope.formData1.kids = null;
			}
			if ( $scope.formData1.filingStatus == "Single"
					|| $scope.formData1.filingStatus == "" ) {

				$scope.formData1.filingStatus = "Married Filing Jointly";
			}

		} else {
			$scope.marriedt = false;
			$scope.kidForSingle = false;
			$scope.hideStatus = true;
			$scope.dependantsCount1 = false;
			$scope.formData1.age = "";
			$scope.formData1.sage = "";
			$scope.formData1.kidscount = "";
			$scope.formData2.stax = "";
			$scope.formData1.filingStatus = "";
			$scope.formData1.kidscount = 0;
			$scope.formData1.dependants = 0;
			if ( $scope.formData1.kidscount == 0 ) {
				$scope.formData1.kids = null;
			}

		}

	};

	$scope.deleteAllCookies = function() {
		$scope.sessionDelete.sessionID = readCookie( "AhTwxlO" );
		$http( {
			method: "POST",
			url: "Logout",
			data: $.param( $scope.sessionDelete ),
			headers: {
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
			while ( c.charAt( 0 ) == " " ) {
				c = c.substring( 1, c.length );
			}
			if ( c.indexOf( nameEQ ) == 0 ) {
				return c.substring( nameEQ.length, c.length );
			}
		}
		return null;
	}
	$scope.addkidsLoad = function( $count, $childsOnLoad ) {
		$scope.formData1.kids = [];
		for ( i = 0; i < $count; i++ ) {
			$scope.formData1.kids.push( {
				name: $childsOnLoad[i].name,
				age: $childsOnLoad[i].age,
				flag: $childsOnLoad[i].flag
			} );
		}
	};
	$scope.load = function() {
		window.history.forward();
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
					.then( 
							function( result ) {
$scope.response=result.data;
								if ( result.data.status == "success" ) {
									if ( $scope.response.role == "admin" ) {
										window.location.href = "Admindashboard.jsp";
									} else if ( result.data.lastVisitedPage == "undefined" ) {
										window.location.href = "dashboardUserr0.jsp";
									} else {
										document.cookie = "lastVisitedPage="
												+ result.data.lastVisitedPage;
										if ( result.data.lastVisitedPage == document.URL ) {
											
										}
									}

									$scope.userdetails.form = "userDetails";
									$http( 
											{
												method: "POST",
												url: "UserProfile",
												data: $
														.param( $scope.userdetails ),
												headers: {
													"Content-Type": "application/x-www-form-urlencoded"
												}
											} )
											.then( 
													function( result ) {
														$scope.userdetails = result.data;
														$scope.formData1.country = $scope.userdetails.city;
														$scope.formData1.uage = $scope.userdetails.age;
														$scope.formData1.city = $scope.userdetails.state;
														$scope.formData1.married = $scope.userdetails.maritalStatus;
														$scope.change();
														$scope.formData1.age = $scope.userdetails.spouseName;
														$scope.formData1.sage = $scope.userdetails.spouseAge;
														$scope.formData1.dependants = $scope.userdetails.dependants
														$scope.formData1.kidscount = $scope.userdetails.kidscount;

														$scope
																.addkidsLoad( 
																		$scope.userdetails.childs.length,
																		$scope.userdetails.childs );

														$scope.formData2.tax = $scope.userdetails.userBeforeTaxIncome;
														$scope.formData2.stax = $scope.userdetails.spouseBeforeTaxIncome;
														$scope.formData2.expenses = $scope.userdetails.monthlyExpenses;
														$scope.formData2.houseinfo = $scope.userdetails.houseinfo;
														$scope.formData2.houserent = $scope.userdetails.rentalExpenses;
														$scope.formData3.cash = $scope.userdetails.cash;
														$scope.formData3.Taxable_Investments = $scope.userdetails.taxableInvestments;
														$scope.formData3.Non_Taxable_Investments = $scope.userdetails.nonTaxableInvestments;
														$scope.formData3.houseValue = $scope.userdetails.houseValue;
														$scope.formData3.remainingMortgage = $scope.userdetails.remainingMortgage;
														// alert(  $scope.formData3.remainingMortgage );
														$scope.formData3.remainingYearsMortgage = $scope.userdetails.remainingYearsMortgage;
														$scope.formData3.realestate = $scope.userdetails.realestate;

														if ( $scope.formData1.married == "Yes" ) {
															$scope.marriedt = true;

														} else {
															$scope.marriedt = false;

															$scope.formData1.age = "";
															$scope.formData1.sage = "";
															$scope.formData1.kidscount = "";
															$scope.formData2.stax = "";
														}

														$scope.check();
														/*
														 * $scope.errorName="";
														 * $scope.errorSuperhero="";
														 */
													},
													function( error ) {
														// //////alert(  "Fail" );
														$scope.message = "Fail";
														/*
														 * $scope.errorName="";
														 * $scope.errorSuperhero="";
														 */
													} );
								} else {

									// //alert(  "Session got expired" );
									$scope.deleteAllCookies();
									window.location.href = "index.jsp";

								}

							}, function( error ) {

								// //alert(  "Cokkie ajax Fail" );
							} );
		} else {
			// //alert(  "Session got expired" );
			$scope.deleteAllCookies();
			window.location.href = "index.jsp";
		}

	}

	$scope.check = function() {

		if ( $scope.formData2.houseinfo == "Own" ) {
			$scope.formData3.houseinfo = "Own";
			if ( $scope.formData3.remainingMortgage <= 0
					|| !$scope.formData3.remainingMortgage ) {
				// alert(  "hello" );
				$scope.formData3.remainingMortgage = 0;
			}
			$scope.house = true;
			$scope.formData3.houseValue = 0;
			$scope.formData3.remainingMortgage = 0;
			$scope.formData3.remainingYearsMortgage = 0;
			$scope.formData3.remainingMortgageInterestRate = 0.0;
            $scope.formData3.houseAppreciationRate = 0.0;
			$scope.formData3.realestate = 0;
			// $scope.formData2.houserent="";
		} else {
			$scope.house = false;
			$scope.formData3.houseinfo = "Rent";
		}

	};

	$scope.iralimits = function() {
		if ( $scope.formData3.Non_Taxable_Investments == "Yes" ) {
			$scope.limit = false;
			if ( $scope.formData1.married == "Yes" ) {
				$scope.limitSpouse = false;
			} else {
				$scope.limitSpouse = true;
			}
		} else {
			$scope.limit = true;
			$scope.limitSpouse = true;
		}
	}
	$scope.checkform4Close = function() {
		// alert(  "checkform4Close" );
		window.location.href = "#services";

	}

	$scope.addkids = function() {
		// alert(  "hwww==="+$scope.formData1.kidscount )
		/*
		 * if(  $scope.formData1.kidscount==0 ){ //alert(  "nnnnn" )
		 * $scope.dependants=false; //$scope.dependantsCount=true; }else{
		 * $scope.dependantsCount=false; $scope.dependants=false }
		 */

		$scope.message = $scope.formData1.kidscount;
		$scope.formData1.kids = [];
		for ( i = 1; i <= $scope.formData1.kidscount; i++ ) {
			$scope.formData1.kids.push( {
				name: "",
				age: "",
				flag: ""
			} );
		}
	};

	$scope.checkform2 = function() {
		$scope.errmessage = "Next Called";
		$scope.formData1.form = "personalDetails";

		//alert($scope.formData1.filingStatus);
		//alert($scope.formData1.married);
		if($scope.formData1.filingStatus==""&&$scope.formData1.married!="Yes")
			{
			//alert($scope.formData1.filingStatus);
			$scope.formData1.filingStatus="NO";
			}
		// alert(   );
		/*
		 * alert(  JSON.stringify(  $scope.formData1.kids ) ); alert(  "h" );
		 * alert(  $scope.formData1.kidscount );
		 */
		if ( $scope.formData1.kidscount > 0 ) {

			for ( i = 0; i < $scope.formData1.kidscount; i++ ) {

				// alert(  $scope.formData1.kids[i].flag );
				if ( $scope.formData1.kids[i].flag == "" ) {
					$scope.formData1.kids[i].flag = "No";
				}

			}
		}

		// alert(  JSON.stringify(  $scope.formData1.kids ) );
		/*
		 * alert(  JSON.stringify(  $scope.formData1.kids ) );
		 */
		if ( !$scope.formData1.country || !$scope.formData1.city
				|| !$scope.formData1.uage || !$scope.formData1.married ) {
			$scope.errmessage = "Please Enter your basic details.....";
			window.location.href = "#about";
			$( "#myModal" ).modal( "show" );
		} else if ( $scope.formData1.married == "Yes" ) {
			if ( !$scope.formData1.age || !$scope.formData1.sage ) {
				$scope.errmessage = "Please Enter your basic details.....";
				window.location.href = "#about";
				$( "#myModal" ).modal( "show" );
			}

			else if ( $scope.formData1.kidscount.number > 0 ) {
				// alert(  "helo" )
				// n = 0;
				for ( i = 0; i < $scope.formData1.kidscount.number; i++ ) {
					// alert(  "helo1" )
					// alert(  $scope.formData1.kids[i].flag );
					if ( !$scope.formData1.kids[i].name
							|| !$scope.formData1.kids[i].age.id ) {
						$scope.errmessage = "Please Enter your basic details.....";
						window.location.href = "#about";
						$( "#myModal" ).modal( "show" );
						break;
					} else {
						n = n + 1;

					}
				}
				if ( n == $scope.formData1.kidscount.number ) {

					$http( 
							{
								method: "POST",
								url: "Register",
								data: $.param( $scope.formData1 ),
								headers: {
									"Content-Type": "application/x-www-form-urlencoded"
								}
							} ).then( function( result ) {

						$scope.message = result.data;
						console.log( "message" + $scope.message );

					}, function( error ) {
						$scope.message = result.data;

					} );
				}

			} else {

				$http( {
					method: "POST",
					url: "Register",
					data: $.param( $scope.formData1 ),
					headers: {
						"Content-Type": "application/x-www-form-urlencoded"
					}
				} ).then( function( result ) {

					$scope.message = result.data;
					console.log( "message" + $scope.message );

				}, function( error ) {
					$scope.message = result.data;

				} );

			}
		}

		else {
			$scope.message = $scope.formData1.kidscount;

			$http( {
				method: "POST",
				url: "Register",
				data: $.param( $scope.formData1 ),
				headers: {
					"Content-Type": "application/x-www-form-urlencoded"
				}
			} ).then( function( result ) {

				$scope.message = result.data;
				console.log( "message" + $scope.message );

			}, function( error ) {
				$scope.message = result.data;

			} );
		}
	}
	$scope.check1 = function() {

		if ( angular.equals( $scope.errmessage,
				"Please Enter your basic details....." ) ) {
			window.location.href = "#about";
		} else if ( angular.equals( $scope.errmessage,
				"Please Enter your INCOME and EXPENSES details....." ) ) {
			window.location.href = "#services";
			$location.hash( "services" );
		} else if ( angular.equals( $scope.errmessage,
				"Please Enter ASSETS details....." ) ) {
			$scope.masked = false;
			window.location.href = "#portfolio";
		}
	}
	$scope.checkform3 = function() {
		$scope.masked = true;
		/*alert( $scope.formData2.expenses);
	alert($scope.formData1.kidscount);
		alert($scope.kidCostCalculated);*/
		if($scope.formData1.kidscount>0) {
			if($scope.formData2.expenses < $scope.kidCostCalculated*1.5 && ($scope.formData2.kidcostFactor==undefined||$scope.formData2.kidcostFactor==0||$scope.formData2.kidcostFactor==null)) {
				$scope.errmessage = "Your non - housing expenses are very low , please enter the amount you will spend for your kid";
				
				$scope.kidcostFactor=true;
				$scope.masked=false;
				$( "#myModal" ).modal( "show" );
				window.location.href = "#services";
			}
			else {
				if($scope.formData2.expenses >= $scope.kidCostCalculated*1.5 && ($scope.formData2.kidcostFactor==undefined||$scope.formData2.kidcostFactor==0||$scope.formData2.kidcostFactor==null)) {
					$scope.formData2.kidcostFactor=0;
				}
				if ( $scope.formData1.married == "Yes"
					&& ( $scope.formData2.stax * 1 + $scope.formData2.tax * 1 < $scope.formData2.houserent
							* 1 + $scope.formData2.expenses * 1 ) ) {
				$scope.masked = false;
				$scope.errmessage = "Your income cannot be less than ur expenses !!";
				window.location.href = "#about";
				$( "#myModal" ).modal( "show" );
			} else if ( $scope.formData1.married == "No"
					&& ( $scope.formData2.tax * 1 < $scope.formData2.houserent * 1
							+ $scope.formData2.expenses * 1 ) ) {
				$scope.masked = false;
				$scope.errmessage = "Your income cannot be less than ur expenses !!";
				window.location.href = "#about";
				$( "#myModal" ).modal( "show" );
			}

			else {
				$scope.masked = true;
				$scope.message = $scope.formData1.kidscount;
				if ( $scope.formData1.married == "Yes" ) {
					$scope.formData2.married = "Yes";
				}
				$http( {
					method: "POST",
					url: "Register",
					data: $.param( $scope.formData2 ),
					headers: {
						"Content-Type": "application/x-www-form-urlencoded"
					}
				} ).then( function( result ) {
					// $(  "#PortfolioDetails" ).modal(  "show" );
					window.location.href = "#portfolio";
					$scope.message = result.data;
					console.log( "message" + $scope.message );
					$scope.masked = false;
				}, function( error ) {
					$scope.message = result.data;
				} );
			}
				
				
				
			}
			
			
		}
		else {
		$scope.formData2.kidcostFactor=0;
		if ( $scope.formData1.married == "Yes"
				&& ( $scope.formData2.stax * 1 + $scope.formData2.tax * 1 < $scope.formData2.houserent
						* 1 + $scope.formData2.expenses * 1 ) ) {
			$scope.masked = false;
			$scope.errmessage = "Your income cannot be less than ur expenses !!";
			window.location.href = "#about";
			$( "#myModal" ).modal( "show" );
		} else if ( $scope.formData1.married == "No"
				&& ( $scope.formData2.tax * 1 < $scope.formData2.houserent * 1
						+ $scope.formData2.expenses * 1 ) ) {
			$scope.masked = false;
			$scope.errmessage = "Your income cannot be less than ur expenses !!";
			window.location.href = "#about";
			$( "#myModal" ).modal( "show" );
		}

		else {
			$scope.masked = true;
			$scope.message = $scope.formData1.kidscount;
			if ( $scope.formData1.married == "Yes" ) {
				$scope.formData2.married = "Yes";
			}
			$http( {
				method: "POST",
				url: "Register",
				data: $.param( $scope.formData2 ),
				headers: {
					"Content-Type": "application/x-www-form-urlencoded"
				}
			} ).then( function( result ) {
				// $(  "#PortfolioDetails" ).modal(  "show" );
				window.location.href = "#portfolio";
				$scope.message = result.data;
				console.log( "message" + $scope.message );
				$scope.masked = false;
			}, function( error ) {
				$scope.message = result.data;
			} );
		}
		}
	}

	$scope.submitPortfolio1 = function() {
		$scope.masked = true;
		$scope.firstQuestion;
		$scope.secondQuestion;
		$scope.thirdQuestion;
		$scope.fourthQuestion;
		$scope.formDataPortfolio = {};
		$scope.formDataPortfolio.riskScore = ( $scope.fourthQuestion * 1 )
				+ ( $scope.firstQuestion * 1 ) + ( $scope.secondQuestion * 1 )
				+ ( $scope.thirdQuestion * 1 );
		// alert(  $scope.formDataPortfolio.riskScore );
		$scope.formDataPortfolio.formType = "duringRegistration";

		$http( {
			method: "POST",
			url: "investmentPortfolio",
			data: $.param( $scope.formDataPortfolio ),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}
		} ).then( function( result ) {
			$scope.completedInvestment = 1;
			$scope.masked = false;
			$( "#PortfolioDetails" ).modal( "toggle" );
			$scope.checkform4();
			// /window.location.href = "dashboardUser0.jsp";
		}, function( error ) {
			$scope.message = result.data;
		} );

	};

	$scope.checkform4 = function() {
		//alert($scope.formData1.filingStatus);
		if($scope.formData1.filingStatus==""&&$scope.formData1.married!="Yes")
			{
			$scope.formData1.filingStatus="NO";
			}
		
		$scope.masked = true;

		$scope.monthlyMortgageAmount = ( Math
				.pow( 
						( 1 + $scope.formData3.remainingMortgageInterestRate * 1 / 1200 ),
						$scope.formData3.remainingYearsMortgage * 12 )
				* ( $scope.formData3.remainingMortgageInterestRate / 1200 )
				/ ( Math
						.pow( 
								( 1 + $scope.formData3.remainingMortgageInterestRate * 1 / 1200 ),
								$scope.formData3.remainingYearsMortgage * 12 ) - 1 ) * $scope.formData3.remainingMortgage );
		// alert(  $scope.monthlyMortgageAmount +
		// $scope.formData3.houseValue*0.01 );
		// alert(  $scope.formData2.houserent );
		// $scope.masked = true;

		// alert(  $scope.formData1.county )
		if ( $scope.formData1.city == undefined
				|| $scope.states.indexOf( $scope.formData1.city ) == -1 ) {

			$scope.masked = false;
			$scope.errmessage = "Please enter a valid state name";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#about";
		} else if ( $scope.formData1.county == undefined ) {
			$scope.masked = false;
			$scope.errmessage = "Please enter valid city name";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#about";
		}

		else if ( $scope.formData1.uage == undefined ) {

			$scope.masked = false;
			$scope.errmessage = "Please enter Age";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#about";
		}

		else if ( $scope.formData1.married == undefined ) {

			$scope.masked = false;
			$scope.errmessage = "Please enter maritalstatus";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#about";
		} else if ( $scope.formData1.age == undefined
				&& $scope.formData1.married == "Yes" ) {
			$scope.masked = false;
			$scope.errmessage = "Please enter spousename";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#about";
		} else if ( $scope.formData1.sage == undefined
				&& $scope.formData1.married == "Yes" ) {
			$scope.masked = false;
			$scope.errmessage = "Please enter spouseage";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#about";
		} else if ( $scope.completedInvestment == 0 ) {
			$scope.masked = false;
			$( "#PortfolioDetails" ).modal( "show" );

		}

		else if ( $scope.formData2.houseinfo == undefined ) {
			$scope.masked = false;
			$scope.errmessage = "Please enter type of house";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#services";
		}

		/*
		 * else if(  $scope.formData2.houseinfo.type=="Rent" &&
		 * !$scope.formData2.houserent ) { $scope.errmessage="Please enter rental
		 * expense"; $(  "#myModal" ).modal(  "show" );
		 * window.location.href="#services";}
		 */
		/*
		 * else if(  !$scope.formData2.otherexpense ) { $scope.errmessage="Please
		 * enter other expense"; $(  "#myModal" ).modal(  "show" );
		 * window.location.href="#services";}
		 */
		else if ( $scope.formData2.houseinfo == "Own"
				&& !$scope.formData3.houseValue ) {
			$scope.masked = false;
			$scope.errmessage = "Please Enter the house value.....";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#portfolio";
		}

		else if ( $scope.formData3.remainingMortgage > 0
				&& !$scope.formData3.remainingYearsMortgage ) {
			$scope.masked = false;
			$scope.errmessage = "Please enter the remaining years of mortgage.....";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#portfolio";
		}

		else if ( $scope.formData3.remainingMortgage > 0
				&& !$scope.formData3.remainingMortgageInterestRate ) {
			$scope.masked = false;
			$scope.errmessage = "Please enter the interest rate for remaining mortgage....";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#portfolio";
		}

		else if ( $scope.formData2.houseinfo == "Own"
				&& ( $scope.monthlyMortgageAmount * 1
						+ $scope.formData3.houseValue * 0.01 / 12 > $scope.formData2.houserent ) ) {
			$scope.masked = false;
			$scope.errmessage = "Your monthly mortgage and property tax for house is greater than provided housing expenses ....";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#portfolio";
		}

		else if ( $scope.formData2.houseinfo == "Own"
				&& $scope.formData3.realestate == null ) {
			$scope.masked = false;
			$scope.errmessage = "Enter RealEstate value....";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#portfolio";
		}

		else if ( $scope.formData3.cash == null ) {
			$scope.masked = false;
			$scope.errmessage = "Please Enter ASSETS details.....";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#portfolio";
		} else if ( $scope.formData3.Taxable_Investments == null ) {
			$scope.masked = false;
			$scope.errmessage = "Please Enter ASSETS details.....";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#portfolio";
		} else if ( $scope.formData3.Non_Taxable_Investments == null ) {
			$scope.masked = false;
			$scope.errmessage = "Please Enter ASSETS details.....";
			$( "#myModal" ).modal( "show" );
			window.location.href = "#portfolio";
		}

		else if ( $scope.formData1.kidscount.number > 0 ) {

			if ( !$scope.formData3.remainingMortgage ) {
				$scope.formData3.remainingMortgage = 0;
				$scope.formData3.remainingYearsMortgage = 0;
			}
			if ( $scope.formData1.married == "Yes" ) {
				$scope.formData2.married = "Yes";

			}
			n = 0;
			for ( i = 0; i < $scope.formData1.kidscount.number; i++ ) {
				if ( !$scope.formData1.kids[i].name
						|| !$scope.formData1.kids[i].age.id ) {
					$scope.errmessage = "Please Enter kids details.....";
					window.location.href = "#about";
					$( "#myModal" ).modal( "show" );
					break;
				} else {
					n = n + 1;
				}
			}

			if ( n == $scope.formData1.kidscount.number ) {
				// alert(  "hhhhhhh" )
				if ( formData1.dependants == 0 ) {
					$scope.formData1.dependantsCount == "No"
				} else {
					$scope.formData1.dependantsCount == "Yes"
				}
				// alert(  "aparna=====number=="+JSON.stringify(  $scope.formData1 ) );
				$http( {
					method: "POST",
					url: "Register",
					data: $.param( $scope.formData1 ),
					headers: {
						"Content-Type": "application/x-www-form-urlencoded"
					}
				} )
						.then( 
								function( result ) {

									$scope.message = result.data;
									console.log( "message" + $scope.message );
									$http( 
											{
												method: "POST",
												url: "Register",
												data: $
														.param( $scope.formData2 ),
												headers: {
													"Content-Type": "application/x-www-form-urlencoded"
												}
											} )
											.then( 
													function( result ) {

														$scope.message = result.data;

														console
																.log( "message"
																		+ $scope.message );
														$http( 
																{
																	method: "POST",
																	url: "Register",
																	data: $
																			.param( $scope.formData3 ),
																	headers: {
																		"Content-Type": "application/x-www-form-urlencoded"
																	}
																} )
																.then( 
																		function( 
																				result ) {

																			window.location.href = "dashboardUser0.jsp";
																			$scope.message = result;

																		},
																		function( 
																				error ) {
																			$scope.message = result;

																		} );
													},
													function( error ) {
														$scope.message = result.data;

													} );
								}, function( error ) {
									$scope.message = result.data;

								} );

			}
		} else if ( $scope.formData1.married == "No" ) {

			$scope.formData2.married = "No";
			$scope.formData3.married = "No";

			if ( !$scope.formData3.remainingMortgage ) {
				$scope.formData3.remainingMortgage = 0;
				$scope.formData3.remainingYearsMortgage = 0;
			}

			$http( {
				method: "POST",
				url: "Register",
				data: $.param( $scope.formData1 ),
				headers: {
					"Content-Type": "application/x-www-form-urlencoded"
				}
			} )
					.then( 
							function( result ) {

								// $scope.masked = false;
								$scope.message = result.data;
								console.log( "message" + $scope.message );
								$http( 
										{
											method: "POST",
											url: "Register",
											data: $.param( $scope.formData2 ),
											headers: {
												"Content-Type": "application/x-www-form-urlencoded"
											}
										} )
										.then( 
												function( result ) {

													$scope.message = result.data;
													console.log( "message"
															+ $scope.message );
													$http( 
															{
																method: "POST",
																url: "Register",
																data: $
																		.param( $scope.formData3 ),
																headers: {
																	"Content-Type": "application/x-www-form-urlencoded"
																}
															} )
															.then( 
																	function( 
																			result ) {
																		// /
																		// $(  "#PortfolioDetails" ).modal(  "show" );
																		// alert(  "ststus
																		// faillll>>>"+result.data.status );
																		if ( result.data.status == "fail" ) {
																			// alert(  "inside
																			// fail" );
																			$scope.masked = false;
																			// alert(  "assetsModal1" )
																			$( 
																					"#assetsModal" )
																					.modal( 
																							"show" );
																			$scope.message = result;

																		} else {

																			window.location.href = "dashboardUser0.jsp";
																		}

																	},
																	function( 
																			error ) {
																		$scope.message = result;

																	} );
												},
												function( error ) {
													$scope.message = result.data;

												} );

							}, function( error ) {
								$scope.message = result.data;

							} );

		} else {
			// alert(  "ajsx3 married" );
			if ( $scope.formData1.kidscount > 0 ) {

				for ( i = 0; i < $scope.formData1.kidscount; i++ ) {

					// alert(  $scope.formData1.kids[i].flag );
					if ( $scope.formData1.kids[i].flag == "" ) {
						$scope.formData1.kids[i].flag = "No";
					}

				}
			}
			if ( $scope.formData1.married == "Yes" ) {
				$scope.formData2.married = "Yes";
				$scope.formData3.married = "Yes";
				if ( ( $scope.formData3.Non_Taxable_Investments == "Yes" )
						&& ( $scope.formData3.u401 == null
								|| $scope.formData3.uIRA == null
								|| $scope.formData3.uRothIra == null || $scope.formData3.u529 == null ) ) {

					$scope.masked = false;
					$scope.errmessage = "Please Enter ASSETS details.....";
					$( "#myModal" ).modal( "show" );
					window.location.href = "#portfolio";
				}
				if ( ( $scope.formData3.Non_Taxable_Investments == "Yes" )
						&& ( $scope.formData3.s401 == null
								|| $scope.formData3.sIRA == null
								|| $scope.formData3.sRothIra == null || $scope.formData3.s529 == null ) ) {

					$scope.masked = false;
					$scope.errmessage = "Please Enter ASSETS details.....";
					$( "#myModal" ).modal( "show" );
					window.location.href = "#portfolio";
				}
			}
			if ( !$scope.formData3.remainingMortgage ) {
				$scope.formData3.remainingMortgage = 0;
				$scope.formData3.remainingYearsMortgage = 0;
			}
			$http( {
				method: "POST",
				url: "Register",
				data: $.param( $scope.formData1 ),
				headers: {
					"Content-Type": "application/x-www-form-urlencoded"
				}
			} )
					.then( 
							function( result ) {

								$scope.message = result.data;
								console.log( "message" + $scope.message );
								$http( 
										{
											method: "POST",
											url: "Register",
											data: $.param( $scope.formData2 ),
											headers: {
												"Content-Type": "application/x-www-form-urlencoded"
											}
										} )
										.then( 
												function( result ) {

													$scope.message = result.data;
													console.log( "message"
															+ $scope.message );
													$http( 
															{
																method: "POST",
																url: "Register",
																data: $
																		.param( $scope.formData3 ),
																headers: {
																	"Content-Type": "application/x-www-form-urlencoded"
																}
															} )
															.then( 
																	function( 
																			result ) {
																		if ( result.data.status == "fail" ) {
																			// alert(  "assetsModal" )
																			$scope.masked = false;
																			$( 
																					"#assetsModal" )
																					.modal( 
																							"show" );
																			$scope.message = result;
																			// window.location.href
																			// =
																			// "finInfo.jsp";
																		} else {
																			$( 
																					"#PortfolioDetails" )
																					.modal( 
																							"show" );
																			window.location.href = "dashboardUser0.jsp";
																			$scope.message = result;
																		}
																	},
																	function( 
																			error ) {
																		$scope.message = result;
																	} );
												},
												function( error ) {
													$scope.message = result.data;

												} );
							}, function( error ) {
								$scope.message = result.data;

							} );

		}

	}
}
