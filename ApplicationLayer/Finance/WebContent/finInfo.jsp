<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Balagopalan">

<title>WEALTHSETTER</title>
<!--  for Type ahead autocomplete -->
<script	src="js/dependency1.js"></script>
<script	src="js/dependency2.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.2/angular.min.js"></script> -->
<link rel="stylesheet"	href="js/dependency3.css"	type="text/css">
<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">

<!-- Custom Fonts -->
<link rel="stylesheet" href="css/fonts.googleapis.css" type="text/css">
<link rel="stylesheet" href="css/fonts.googleapis1.css" type="text/css">
<!--   ------------------ this is used for load graphs from google -------------
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
	------------------------------------------------------------------------>
<script src="js/jquery.min.js"></script>
<!-- LOAD ANGULAR -->
<script src="js/angular.min.js"></script>
<script src="js/app.js"></script>
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css"
	type="text/css">

<!-- Plugin CSS -->
<link rel="stylesheet" href="css/animate.min.css" type="text/css">

<!-- Custom CSS -->
<link rel="stylesheet" href="css/creative1.css" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
.section-heading {
	color: black;
}
.dropdown-submenu{position:relative;}
.dropdown-submenu>.dropdown-menu{top:0;left:100%;margin-top:-6px;margin-left:-1px;-webkit-border-radius:0 6px 6px 6px;-moz-border-radius:0 6px 6px 6px;border-radius:0 6px 6px 6px;}
.dropdown-submenu>a:after{display:block;content:" ";float:right;width:0;height:0;border-color:transparent;border-style:solid;border-width:5px 0 5px 5px;border-left-color:#cccccc;margin-top:5px;margin-right:-10px;}
.dropdown-submenu:hover>a:after{border-left-color:#555;}
.dropdown-submenu.pull-left{float:none;}.dropdown-submenu.pull-left>.dropdown-menu{left:-100%;margin-left:10px;-webkit-border-radius:6px 0 6px 6px;-moz-border-radius:6px 0 6px 6px;border-radius:6px 0 6px 6px;}
@media (min-width: 768px) {
  ul.nav li:hover > ul.dropdown-menu {
    display: block;
  }
  #navbar {
    text-align: center;
  }
}
</style>
<script>
function onlyAlphabets(e,t) {
	var len=t.value.length;
            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
                if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) ||charCode==8||charCode==127 ||charCode==32)
                {
                	if(len==250)
                	{
                		if(charCode==8)
                		{
                			return true;	
                		}
                		else
                		{
                		alert('Only 25 characters allowed');
                		return false;
                		}	
                	}
                	else
                	{
                    return true;
                   }
                 }
                else
                    return false;
            }
            catch (err) {
                alert(err.Description);
            }
        }
function onlyNumbers(e,t) {
	var len=t.value.length;
            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
                if ((charCode >= 48 && charCode <= 57) ||charCode==8||charCode==127||charCode==46)
                {
                //limit to only one decimal point 
                	var parts = e.srcElement.value.split('.');
                	 //alert(e.srcElement.value);
                     if (parts.length > 1 && charCode == 46)
                    	 {
                    	// alert("inside");
                    	 return false;
                    	 }
                     else
                    	 {
                     
                 
                	if(len==250)
                	{
                		if(charCode==8)
                		{
                			return true;	
                		}
                		else
                		{
                		alert('Only 25 characters allowed');
                		return false;
                		}	
                	}
                    	 
                	else
                	{
                    return true;
                   }
                }
                 }
                else
                    return false;
               
            }
            catch (err) {
                alert(err.Description);
            }
        }
function onlyNumbersWithoutFloating(e,t) {
	var len=t.value.length;
            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
                if ((charCode >= 48 && charCode <= 57) ||charCode==8||charCode==127)
                {
                            	 {
                     
                 
                	if(len==250)
                	{
                		if(charCode==8)
                		{
                			return true;	
                		}
                		else
                		{
                		alert('Only 25 characters allowed');
                		return false;
                		}	
                	}
                    	 
                	else
                	{
                    return true;
                   }
                }
                 }
                else
                    return false;
               
            }
            catch (err) {
                alert(err.Description);
            }
        }
</script>
</head>

<body id="page-top" ng-app="formApp" ng-controller="formController"
	ng-init="load()" ng-cloak>
	<div class="MaskLayer" ng-class="{isClosed : !masked}">
		<div class="MaskLayer-Content">
			<i class="fa fa-refresh fa-spin fa-3x"
				style="position: absolute; margin-left: 18%; margin-top: 50%; color: white; z-index: 1;"></i>
		</div>
	</div>

	<nav id="mainNav" style="color: #222;"
		class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand page-scroll" href="index.jsp">WealthSetter</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a class="page-scroll" href="home.jsp">Home</a></li>
					<li><a class="page-scroll" href="#">How it works</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Resources <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="mortgageCalculator.jsp" >Mortgage Calculator</a></li>
                            <li class="dropdown dropdown-submenu"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Car Calculator</a>
								<ul class="dropdown-menu">
									<li><a href="carLoanCalculator.jsp" >Car Loan Calculator</a></li>
									<li><a href="carLeaseCalculator.jsp" >Car Lease Calculator</a></li>
								</ul>
							</li>
							<li><a href="ssbCalculator.jsp">Social Security Benefits</a></li>
							<li><a href="FderalincomeTaxCalculator.jsp">Income Tax Calculator</a></li>
						</ul>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Case Studies <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="CaseStudy1_home.jsp">Case Study 1</a></li>
                            <li><a href="CaseStudy2_home.jsp">Case Study 2</a></li>
                            <li><a href="CaseStudy3_home.jsp">Case Study 3</a></li>
						</ul>
					</li>

					<li><a href=# class="page-scroll"
						ng-click="deleteAllCookies()"><i class="fa fa-sign-out"></i>
							Logout</a></li>
					<!---  <li>
                        <a class="page-scroll" href="#contact">Contact</a>
                    </li> ---->
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>



	<section class="bg-primary" id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1 text-center">
					<h2 class="section-heading">Welcome to WealthSetter!</h2>
					<br></br> <span class="font-para-black"> Where do you live?

					</span> <span class="font-para-black"> State </span>
					<!--  <select ng-model="formData1.city"  class="font-dropdown">
                   				<option ng-selected="state.name == formData1.city " ng-repeat="state in states" ng-value="state.name" >{{state.name}}</option>
                      	</select>
                                  <span  class="font-para-black" >  
                         , 
                         
                      	</span>     -->
					<input name="states" id="states" type="text" placeholder=""
						typeahead-on-select="autocomplete()"
						onkeypress="return onlyAlphabets(event,this)"
						class="input_bl-long" ng-model="formData1.city"
						ng-click="hideselectedcity()"
						typeahead="state for state in states | filter:$viewValue | limitTo:8"
						class="form-control"> <span class="font-para-black">
						City </span> <input type="tel" maxlength="15" placeholder=""
						class="input_bl-long"
						typeahead="city for city in citys | filter:$viewValue | limitTo:8"
						class="form-control" ng-model="formData1.country"
						typeahead-on-select="autocompleteForCounty()" />

					<!-- ng-click="autocomplete()"	<select class="font-dropdown" ng-model="formData1.country" >
                      	<option ng-selected="city.name == formData1.country " ng-repeat="city in cities" ng-value="city.name" >{{city.name}}</option>
                      	</select> -->


					<br> <span class="font-para-black"> Your Age? </span> <select
						ng-model="formData1.uage" class="font-dropdown-small">
						<option ng-selected="age.number == formData1.uage "
							ng-repeat="age in ages" ng-value="age.number">{{age.number}}</option>
					</select> <span class="font-para-black"> Are you married? </span> <select
						ng-model="formData1.married" ng-change="change()"
						class="font-dropdown-small">
						<option ng-selected="option.id == formData1.married "
							ng-repeat="option in options" ng-value="option.id">{{option.id}}</option>

					</select>
					     
						
						
					<div ng-hide="!marriedt">

						<span class="font-para-black"> Your spouse's name? </span> <input
							type="tel" onkeypress="return onlyAlphabets(event,this)"
							maxlength="15" placeholder="" maxlength="15"
							class="input_bl-long" ng-model="formData1.age" /> <br> <span
							class="font-para-black"> Your Spouse Age? </span> <select
							ng-model="formData1.sage" class="font-dropdown-small">
							<option ng-selected="age.number == formData1.sage "
								ng-repeat="age in ages" ng-value="age.number">{{age.number}}</option>
						</select>
						<div> 
						
<!-- 						
						<span class="font-para-black"> <br> How many kids do
							you have?

						</span> <select ng-model="formData1.kidscount" ng-change="addkids()"
							class="font-dropdown-small">
							<option ng-selected="kid.number==formData1.kidscount"
								ng-repeat="kid in kidsnumber" ng-value="kid.number">{{kid.number}}</option>
						</select>
						<div ng-repeat="kid in formData1.kids">
							<span class="font-para-black">Kids name</span> <input
								onkeypress="return onlyAlphabets(event,this)" type="tel"
								placeholder="" maxlength="15" name="name" class="input_bl-long"
								ng-model="kid.name" /> <span class="font-para-black">
								Age</span> <select class="font-dropdown-small" name="age"
								ng-model="kid.age">
								<option ng-selected="kidsage.id==kid.age"
									ng-repeat="kidsage in kidsages" ng-value="kidsage.id">{{kidsage.id}}</option>
							</select>
							<div ng-show="kid.age>18">
								<span class="font-para-black">Is the kid a full time
									student?</span> <select class="font-dropdown-small"
									ng-model="kid.flag">
									<option ng-selected="kid.flag" ng-repeat="student in student"
										ng-value="student.id">{{student.id}}</option>
								</select>
							</div>

						</div> -->



					</div>		
					</div>	
					
					     <div ng-hide="!hideStatus">
							<span class="font-para-black"> <br> Do you have any dependents?

						</span> <select ng-model="formData1.filingStatus" ng-change="funCheck()"
							class="font-dropdown-small">
							<option ng-selected="filingOptions.id[0]"
								ng-repeat="filingOptions in filingOptions" ng-value="filingOptions.id">{{filingOptions.id}}</option>
						</select>
						</div>
					<div ng-hide="!kidForSingle" style="margin-top: -2%;">
					
				<span class="font-para-black"> <br> How many dependent child do you have?

						</span> <select ng-model="formData1.kidscount" ng-change="addkids()"
							class="font-dropdown-small">
							<option ng-selected="kid.number==formData1.kidscount"
								ng-repeat="kid in kidsnumber" ng-value="kid.number">{{kid.number}}</option>
						</select>
						
						<div ng-repeat="kid in formData1.kids">
							<span class="font-para-black">Child's name</span> <input
								onkeypress="return onlyAlphabets(event,this)" type="tel"
								placeholder="" maxlength="15" name="name" class="input_bl-long"
								ng-model="kid.name" /> <span class="font-para-black">
								Age</span> <select class="font-dropdown-small" name="age"
								ng-model="kid.age">
								<option ng-selected="kidsage.id==kid.age"
									ng-repeat="kidsage in kidsages" ng-value="kidsage.id">{{kidsage.id}}</option>
							</select>
							<div ng-show="kid.age>18">
								<span class="font-para-black">Is the kid a full time
									student?</span> <select class="font-dropdown-small"
									ng-model="kid.flag">
									<option ng-selected="student.id==kid.flag" ng-repeat="student in student"
										ng-value="student.id">{{student.id}}</option>
										
							
										
										
										
								</select>
							</div>

						</div>
						</div>
							<!-- <div ng-hide="!dependants">  "
							<span class="font-para-black"  >Do you have any dependents other than your kids? </span> <input
						type="text" onkeypress="return onlyNumbers(event,this)"
						maxlength="2" placeholder="" class="input_bl-long"
						ng-model="formData1.dependants"  /></div> -->
						
						
					  <div ng-hide="!dependantsCount">
							<span class="font-para-black"> <br> Do you have any dependents other than your kids? 

						</span> <select ng-model="formData1.dependantsCount" ng-change="dependentCheck()"
							class="font-dropdown-small">
							<option ng-selected="filingOptions.id == formData1.dependantsCount"
								ng-repeat="filingOptions in filingOptions" ng-value="filingOptions.id">{{filingOptions.id}}</option>
						</select>
						</div> 
							<!-- 	  <div ng-hide="!dependantsCount1">
							<span class="font-para-black"> <br> How many dependent you have? 

						</span> <select ng-model="formData1.dependantsCount1" ng-change="dependentCheck()"
							class="font-dropdown-small">
							<option ng-selected="filingOptions.id == formData1.dependantsCount1"
								ng-repeat="filingOptions in filingOptions" ng-value="filingOptions.id">{{filingOptions.id}}</option>
						</select>
						</div>  -->
						<!-- <div ng-hide="!dependants">   -->
						<!-- 	<span class="font-para-black"  > dependents </span> <input
						type="text" onkeypress="return onlyNumbers(event,this)"
						maxlength="2" placeholder="" class="input_bl-long"
						ng-model="formData1.dependants"  /></div> -->
						<div ng-hide="!dependantsCount1">
						<span class="font-para-black"> <br>How many other dependents do you have?

						</span> <select 
						
						ng-model="formData1.dependants" 
							class="font-dropdown-small">
							<option ng-selected="kid.number==formData1.dependants" 
								ng-repeat="kid in kidsnumber" ng-value="kid.number">{{kid.number}}</option>
						</select>
						</div>
						
							<div ng-hide="!dependants">
						<span class="font-para-black"> <br>Number of dependents

						</span> <select ng-model="formData1.dependants" 
							class="font-dropdown-small">
							<option ng-selected="kid.number==formData1.dependants"
								ng-repeat="kid in kidsnumber" ng-value="kid.number">{{kid.number}}</option>
						</select>
						</div>
						
					<br></br> <input type=hidden ng-model="formData1.form"
						value="form1" ng-init="formData1.form='personalDetails'">
					<br> <br> <a href="#services"
						style="background-color: #E65319;" ng-click="checkform2()"
						class="btn btn-primary btn-xl page-scroll">Next</a>

				</div>
				<div id="checkSession" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">Status</h4>
							</div>
							<div class="modal-body">
								<p>{{errMessage}}</p>
							</div>
							<div class="modal-footer">
								<button type="button" ng-click="check1()"
									class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
	</section>
	<br></br>
	<br></br>

	<section id="services" class="bg-primary">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1 text-center">
					<h2 class="section-heading">Income and Expenses</h2>
					<br></br> <span class="font-para-black"> Monthly Income: </span> <input
						type="text" onkeypress="return onlyNumbers(event,this)"  ng-change="doneTyping()"
						maxlength="15" placeholder="" class="input_bl-long"
						ng-model="formData2.tax" />

					<div ng-hide="!marriedt">

						<span class="font-para-black"> Spouse's Monthly income: </span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="15" placeholder="" class="input_bl-long" ng-change="doneTyping()"							ng-model="formData2.stax" />
					</div>
					<span class="font-para-black"> Monthly non-housing expenses:
					</span> <input type="text" onkeypress="return onlyNumbers(event,this)"
						maxlength="15" placeholder="" maxlength="15" class="input_bl-long" 
						ng-model="formData2.expenses" /> <br>
						<span ng-show="kidcostFactor" class="font-para-black"> Cost for the kids:
                    </span> <input ng-show="kidcostFactor" type="text" onkeypress="return onlyNumbers(event,this)"
                        maxlength="15" placeholder="" maxlength="15" class="input_bl-long" 
                        ng-model="formData2.kidcostFactor" /> <br>
						
						
						
						 <span
						class="font-para-black"> Are you renting or owning a house?
					</span> <select ng-model="formData2.houseinfo" ng-change="check()"
						class="font-dropdown">
						<option ng-selected="house.type == formData2.houseinfo "
							ng-repeat="house in houses" ng-value="house.type">{{house.type}}</option>
					</select> <br>

					<!-- <div  ng-hide="!house"> -->
					<span class="font-para-black"> Monthly housing expenses: </span> <input
						type="text" onkeypress="return onlyNumbers(event,this)"
						maxlength="12" placeholder="" maxlength="15" class="input_bl-long"
						ng-model="formData2.houserent" />
					<!-- </div> -->
					<!--  <span class="font-para-black" >  
	                         Other expenses:
                         
                  </span>
                                            
                  <input onkeypress="return onlyNumbers(event,this)" min="0"  type="number" placeholder="" maxlength="15" class="input_bl-long" ng-model="formData2.otherexpense" /> -->
					<br></br> <input type=hidden ng-model="formData2.form"
						value="form3" ng-init="formData2.form='IncomeAndExpenses'">
					<a href="" style="background-color: #E65319;"
						ng-click="checkform3()" class="btn btn-primary btn-xl page-scroll">Next</a>
				</div>
			</div>
		</div>
	</section>
	<br></br>
	<br></br>

	<section class="bg-primary" id="portfolio">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1 text-center">
					<h2 class="section-heading">Assets</h2>
					<div ng-hide="!house">
						<br></br> <span class="font-para-black"> House market value
							: </span> <input type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12" ng-change="calculateEquity()" type="number"
							placeholder="" maxlength="15" class="input_bl-long"
							ng-model="formData3.houseValue" /> <br></br> <span
							class="font-para-black"> Remaining mortgage : </span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12" ng-change="calculateEquity()" min="0"
							type="number" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="formData3.remainingMortgage" /> <br></br> <span
							class="font-para-black"> Remaining years of mortgage : </span> <input
							type="text" onkeypress="return onlyNumbersWithoutFloating(event,this)"
							maxlength="2" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="formData3.remainingYearsMortgage" /> <br></br> <span
							class="font-para-black"> What is your current mortgage
							rate? </span> <input type="text"
							onkeypress="return onlyNumbers(event,this)" maxlength="5"
							placeholder="" maxlength="2" class="input_bl-long"
							ng-model="formData3.remainingMortgageInterestRate" /> <br></br>
							<span
                            class="font-para-black"> House appreciation rate</span> <input type="text"
                            onkeypress="return onlyNumbers(event,this)" maxlength="5"
                            placeholder="" maxlength="2" class="input_bl-long"
                            ng-model="formData3.houseAppreciationRate" /> <br></br>
						<span class="font-para-black"> Real Estate : </span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12" placeholder="" class="input_bl-long"
							ng-model="formData3.realestate" />

					</div>


					<br></br> <span class="font-para-black"> Cash or
						cash-equivalent (cash, savings account, checking account) : </span> <input
						type="text" onkeypress="return onlyNumbers(event,this)"
						maxlength="15" placeholder="" class="input_bl-long"
						ng-model="formData3.cash" /> <br></br> <span
						class="font-para-black"> Taxable Investments (regular
						brokerage account): </span> <input type="text"
						onkeypress="return onlyNumbers(event,this)" maxlength="15"
						placeholder="" class="input_bl-long"
						ng-model="formData3.Taxable_Investments" /> <br></br>
<span class="font-para-black">529 Plans and Retirement Accounts(401k, traditional IRA):</span><select ng-model="formData3.Non_Taxable_Investments" ng-change="iralimits()"
						class="font-dropdown-small">
						<option ng-selected="limit.id ==formData3.Non_Taxable_Investments"
							ng-repeat="limit in limits" ng-value="limit.id">{{limit.id}}</option>
					</select>
							<div ng-hide="limit">
						<br></br> <span class="font-para-black"> Balance in 401(k) account:
							 </span> <input type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12" type="number"
							placeholder="" maxlength="15" class="input_bl-long"
							ng-model="formData3.u401" /> <br></br> <span
							class="font-para-black">Balance in traditional IRA account: </span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12"  min="0"
							type="number" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="formData3.uIRA" /> <br></br> <span
							class="font-para-black">Balance in Roth IRA account: </span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="formData3.uRothIra" /> <br></br> <span
							class="font-para-black"> Balance in 529 plan:  </span> <input type="text"
							onkeypress="return onlyNumbers(event,this)" maxlength="13"
							placeholder="" maxlength="12" class="input_bl-long"
							ng-model="formData3.u529" /> <br></br>
						
					</div> 
								<div ng-hide="limitSpouse">
						<br></br> <span class="font-para-black"> Balance in spouse 401(k) account:
							 </span> <input type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12" type="number"
							placeholder="" maxlength="15" class="input_bl-long"
							ng-model="formData3.s401" /> <br></br> <span
							class="font-para-black">Balance in traditional spouse IRA account: </span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12"  min="0"
							type="number" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="formData3.sIRA" /> <br></br> <span
							class="font-para-black">Balance in spouse Roth IRA account: </span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="formData3.sRothIra" /> <br></br> <span
							class="font-para-black"> Balance in spouse 529 plan:  </span> <input type="text"
							onkeypress="return onlyNumbers(event,this)" maxlength="13"
							placeholder="" maxlength="12" class="input_bl-long"
							ng-model="formData3.s529" /> <br></br>
						
					</div><br></br> <br></br>
					<br></br> <input type=hidden ng-model="formData3.form"
						value="form4" ng-init="formData3.form='Assets'">
					<button ng-click="checkform4()" class="btn btn-success btn-lg">Save</button>
				</div>
			</div>
		</div>
	</section>
	<div id="myModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" ng-click="check1()" class="close"
						data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Error</h4>
				</div>
				<div class="modal-body">
					<p>{{errmessage}}</p>
				</div>
				<div class="modal-footer">
					<button type="button" ng-click="check1()" class="btn btn-default"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="assetsModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Message</h4>
				</div>
				<div class="modal-body">
					<p>Entered value will result the assets to go negative ,kindly
						reenter some feasible value</p>
				</div>
				<div class="modal-footer">
					<button type="button" id="dialog-ok" ng-click="checkform4Close()"
						class="btn btn-primary pull-right" data-dismiss="modal">close</button>


				</div>
			</div>

		</div>
	</div>
	
	<div id="PortfolioDetails" class="modal fade" role="dialog">
		<div class="modal-dialog modal-chartDetails">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Investment Portfolio</h4>
				</div>
				<div class="modal-body">

					<div
						style="background-image: url(http://cssslider.com/sliders/demo-10/data1/images/3.jpg); background-size: cover; height: 100%;"
						class="bg-primary" ng-show="show==0">
						<div class="container">
							<div class="row">
								<div class="col-lg-10 col-lg-offset-1 text-center">
									<br></br> <br></br>
									<h1 style="color: white; font-size: 65px;">Investment
										Portfolio</h1>
									<br></br>

									<p style="color: white;">In our planning process, we try to
										create customized portfolio that can match your risk appetite.
										We would like to ask you a few questions to have a better
										understanding of your view of market risk. At the end of the
										questionnaire, we will give you our assessment of your risk
										profile. You can modify it if you prefer. However, keep in
										mind that, we are structuring our portfolio for long term
										planning purpose with the emphasis on low cost and simplicity,
										so even our most aggressive risk profile is not suitable for
										speculative trading or market-timing.</p>

									<br></br> <br></br> <a ng-click="next()"
										style="background-color: #E65319;"
										class="btn btn-primary btn-xl page-scroll pull-right">Next</a>

								</div>

							</div>
						</div>
						<br></br> <br></br>
					</div>
					<div class="bg-primary">
						<div class="container">
							<div class="row">
								<div class="col-lg-10 col-lg-offset-1">
									<span class="font-para-black" ng-show="show==1"> <br></br>
										Do you feel comfortable reviewing the performance of a
										portfolio of stocks and bonds?
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="firstQuestion" ng-value="6"
												style="margin-top: 1%;">Very comfortable</label>
										</div>
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="firstQuestion" ng-value="5"
												style="margin-top: 1%;">Somewhat comfortable</label>
										</div>
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="firstQuestion" ng-value="4"
												style="margin-top: 1%;">Not comfortable at all</label>
										</div> <a style="background-color: #E65319;" ng-click="next()"
										class="btn btn-primary btn-xl page-scroll pull-right">Next</a>
										<br></br>
									</span> <span class="font-para-black" ng-show="show==2"> <br></br>
										In reviewing my portfolio, I would
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="secondQuestion" ng-value="2"
												style="margin-top: 1%;">focus on investments that
												have lost money.</label>
										</div>
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="secondQuestion" ng-value="6"
												style="margin-top: 1%;">equally focus on investment
												that have lost or gained money.</label>
										</div>
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="secondQuestion" ng-value="4"
												style="margin-top: 1%;">focus on investments that
												have gained money.</label>
										</div> <br> <a style="background-color: #E65319;"
										ng-click="next()"
										class="btn btn-primary btn-xl page-scroll pull-right">Next</a>
										<a style="background-color: #E65319;" ng-click="back()"
										class="btn btn-primary btn-xl page-scroll pull-left">Back</a>
										<br></br>
									</span> <span class="font-para-black" ng-show="show==3"> <br></br>
										In our recommended portfolio, we utilize exchange traded funds
										(ETFs) exclusively because of their market coverage, low cost,
										and easy to trade. How familiar are you with ETFs? (you can
										find out more information following this link)
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="thirdQuestion" ng-value="6"
												style="margin-top: 1%;">Very familiar (I know more
												than a half dozen popular ETFs)</label>
										</div>
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="thirdQuestion" ng-value="5"
												style="margin-top: 1%;">Familiar (Though I have not
												traded, or know many ETFs, I know what they are and know who are
												the sponsors of some of the most popular ETFs)</label>
										</div>
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="thirdQuestion" ng-value="4"
												style="margin-top: 1%;">Not very familiar (I know
												what is an ETF, but that's pretty much the limit of my
												knowledge.)</label>
										</div>
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="thirdQuestion" ng-value="3"
												style="margin-top: 1%;">Unfamiliar (What is ETF?)</label>
										</div> <br> <a style="background-color: #E65319;"
										ng-click="next()"
										class="btn btn-primary btn-xl page-scroll pull-right">Next</a>
										<a style="background-color: #E65319;" ng-click="back()"
										class="btn btn-primary btn-xl page-scroll pull-left">Back</a>
										<br></br>
									</span> <span class="font-para-black" ng-show="show==4"> <br></br>
										Market is often volatile. If your investments lose 20% of its
										value in a month, what would you do?
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="fourthQuestion" ng-value="1"
												style="margin-top: 1%;">sell all and keep everything
												in cash to be safe</label>
										</div>
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="fourthQuestion" ng-value="2"
												style="margin-top: 1%;">sell some to reduce the risk</label>
										</div>
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="fourthQuestion" ng-value="5"
												style="margin-top: 1%;">do nothing, just stick to
												the investment plan</label>
										</div>
										<div class="radio">
											<label style="font-size: 18px;"><input type="radio"
												ng-model="fourthQuestion" ng-value="8"
												style="margin-top: 1%;">buy more to take advantage
												of the low prices</label>
										</div> <br> <a style="background-color: #E65319;"
										ng-click="submitPortfolio1()"
										class="btn btn-primary btn-xl page-scroll pull-right">Submit</a>
										<a style="background-color: #E65319;" ng-click="back()"
										class="btn btn-primary btn-xl page-scroll pull-left">Back</a>
										<br></br>
									</span>



								</div>
							</div>
						</div>
					</div>


					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<!--  <button type="button"  ng-click="checkCheckbox()" class="btn btn-default" >Submit</button> -->
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->

	<br></br>
	<br></br>
	<br></br>
	<br></br>
	<br></br>
	<br></br>
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<br></br>
					<p style="color: white;">Copyright &copy; WealthSetter 2017. All
						Rights Reserved</p>
				</div>
			</div>
		</div>
	</footer>
	<div class="modal fade" id="caseStudy" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Message</h4>
				</div>
				<div class="modal-body">
					<p>Page Under Construction</p>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-default"
						data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="js/jquery.easing.min.js"></script>
	<script src="js/jquery.fittext.js"></script>
	<script src="js/wow.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/creative.js"></script>

</body>

</html>