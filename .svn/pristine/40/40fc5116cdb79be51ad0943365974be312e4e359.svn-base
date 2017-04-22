<!DOCTYPE html>
<html lang="en" ng-app="formApp2">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Balagopalan">
<title>WEALTHSETTER</title>
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
<script src="js/userProfile.js"></script>
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


</head>
<body id="page-top" ng-controller="formController2" ng-init="load()"
	ng-cloak>

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
				<a class="navbar-brand page-scroll" href="dashboardUser0.jsp">WealthSetter</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a class="page-scroll" href="dashboardUser0.jsp">Home</a>
					</li>
					<li><a class="page-scroll" href="#">How it works?</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Case Studies <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="CaseStudy1.jsp">Case Study 1</a></li>
                            <li><a href="CaseStudy2.jsp">Case Study 2</a></li>
                            <li><a href="CaseStudy3.jsp">Case Study 3</a></li>
						</ul>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Resources <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="mortgageCalculator.jsp"  >Mortgage Calculator</a></li>
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
					<li>
						<!--   <a class="page-scroll" href="Report.jsp">Reports</a> --> <a
						href="#" ng-click="report()">Reports</a>
					</li>
					<li><a class="page-scroll" href="#"><i
							class="fa fa-user-plus"></i> My Profile</a></li>

					<li><a class="page-scroll" href=#
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
	<div class="pdfsection" id="pdfsection">
	<section class="bg-primary" id="about">
		<div class="container">
			<div class="row">
			<div style="display: none" class="alert alert-warning text-center"
                    id="report-alert">
                    <button type="button" class="close" ng-click="hideWarning()">x</button>
                    <strong>Warning! </strong>{{SuccessMessage1}}
                </div>
			<!-- <script src="http://cdnjs.cloudflare.com/ajax/libs/jspdf/0.9.0rc1/jspdf.min.js"></script>
			
			
                  <script>
                  var doc = new jsPDF();
                  var specialElementHandlers = {
                      '#editor': function (element, renderer) {
                          return true;
                      }
                  };

                  $('#cmd').click(function () {
                      doc.fromHTML($('#about').html(), 15, 15, {
                          'width': 170,
                              'elementHandlers': specialElementHandlers
                      });
                      doc.save('sample-file.pdf');
                  });
                  </script>
                   <button class="pull-left" id="cmd">generate PDF</button> -->
				<div class="col-lg-10 col-lg-offset-1 text-center">
					<h2 class="section-heading">Basic Details</h2>
				</div>
				 <button class="btn btn-primary pull-right hideinpdf" onclick="generatePDF();">Save as PDF</button>
									<script>
									var generatePDF = function() {
										  kendo.drawing.drawDOM($("#pdfsection")).then(function(group) {
										    kendo.drawing.pdf.saveAs(group, "User Profile.pdf");
										  
										  });
										}
									</script>
				
				<br></br> <br></br> <a href="#" data-toggle="modal"
					data-target="#changePassword" class="pull-left hideinpdf">Change Password</a>
				<a href="#" data-toggle="modal" data-target="#editBasicDetails"
					class="pull-right hideinpdf">Edit</a>
				<div class="col-lg-12 col-lg-offset-2 center">
					<div class="col-md-6">
						<div class="form-group">
							<label for="FName">First Name :</label> {{userdetails.name}}
						</div>
						<div class="form-group">
							<label for="LName">Last Name : </label> {{userdetails.lastName}}
						</div>
						<div class="form-group">
							<label for="Age">Age : </label>{{userdetails.age}}
						</div>
					<!-- 	<div class="form-group">
							<label for="address1">Address 1 : </label>{{userdetails.address1}}
						</div> -->
						<div class="form-group">
							<label for="address2">City : </label> {{userdetails.city}}
						</div>
						<div class="form-group">
							<label for="address2">State : </label> {{userdetails.state}}
						</div>
						<!-- <div class="form-group">
							<label for="address2">County : </label> {{userdetails.county}}
						</div> -->
						<div class="form-group">
							<label for="exampleInputEmail1">Email address :</label>
							{{userdetails.email}}
						</div>
					</div>
					<div class="col-md-6">
						
						<!--    <div class="form-group">
                        <label for="DOB">DOB: </label> {{userdetails.dob}}
                     </div> -->
                     					<div class="form-group">
								<label for="marital Status"> Tax Filing Status: </label> {{userdetails.filingStatus}}
								</div>
						<div ng-hide="!SpouceDetail">
							<div class="form-group">
								<label for="Vertical">----Spouse Details----</label><br>
								<div class="form-group">
									<label for="FName">First Name :</label>
									{{userdetails.spouseName}}
								</div>
								<div class="form-group">
									<label for="LName">Last Name :</label>
									{{userdetails.spouseLastName}}
								</div>
								<div class="form-group">
									<label for="Age">Age :</label> {{userdetails.spouseAge}}
								</div>
								
							
							</div>
						</div>
							<div class="form-group">
									<label for="kidscount">Kids count:</label>
									{{userdetails.kidscount}}
								</div>
								 <div ng-repeat="kid in userdetails.childs">
									<div class="form-group">
									<label for="kidscount">Child's name:</label>
									{{kid.name}}
								</div>
								</div>
					</div>
				</div>
				<div class="col-lg-10 col-lg-offset-1 text-center">
					<a href="#portfolio" style="background-color: #E65319;"
						class="btn btn-primary btn-xl page-scroll ">Next</a>
				</div>
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
							<button type="button" ng-click="check1()" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
<!-- 	<br></br>
	<br></br>
 --><!-- 	<section id="services" class="bg-primary" ng-init="load1()">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1 text-center">
					<h2 class="section-heading">Expenses</h2>
				</div>
				<br></br> <br></br>
				<div class="col-md-12 center">
					<a href="" class="pull-right" ng-click="disabled=false">Edit</a> <a
						href="#" data-toggle="modal" data-target="#incomeProjection"
						class="pull-left hideinpdf">Advanced</a><br></br>
					<div>
						<div class="col-md-6">
							<input type="hidden" ng-disabled="disabled" required
								ng-init="disabled=true">

							<div class="form-group">
                        <label for="FName">User Monthly incomes :</label>
                        <input maxlength="15" type="text" class="form-control" id="fname" allow-pattern="\d" name="beforeIncomeTax" ng-model="incomeandexpenses.beforeIncomeTax"  placeholder="Enter before tax income" ng-disabled="disabled"/>
                     <span style="color: red">{{errorMail1}}</span>
                     </div>
							 <div class="form-group" id="myip" >
                        <label for="FName" >Spouse Monthly incomes :</label>
                        <input maxlength="15" type="text" class="form-control" allow-pattern="\d" name="spouseBeforeIncomeTax" ng-model="incomeandexpenses.spouseBeforeIncomeTax" id="fname"  placeholder="Enter spouse before tax income" ng-disabled="disabled" />
                    
                     </div>
							<div class="form-group">
								<label for="FName">Housing expense(Monthly) : </label> <input
									maxlength="15" type="text" class="form-control"
									allow-pattern="\d" name="rentalExpense"
									ng-model="incomeandexpenses.rentalExpense" id="fname"
									placeholder="Enter  rental expense" ng-disabled="disabled" />
								<span style="color: red">{{errorMail3}}</span>
							</div>
							<div ng-hide="!houseInfo">


								<div class="form-group">
									<label for="FName">House market value : </label> <input
										maxlength="15" type="text" class="form-control"
										allow-pattern="\d" name="houseMarketValue"
										ng-model="incomeandexpenses.houseMarketValue" id="fname"
										placeholder="Enter  rental expense" ng-disabled="disabled" />
									<span style="color: red">{{errorMail5}}</span>
								</div>
								<div class="form-group">
									<label for="FName">Remaining years of mortgage :</label> <input
										maxlength="15" type="text" class="form-control"
										allow-pattern="\d" name="remainingYearsMortgage"
										ng-model="incomeandexpenses.remainingYearsMortgage" id="fname"
										placeholder="Enter  rental expense" ng-disabled="disabled" />
									<span style="color: red">{{errorMail7}}</span>
								</div>


							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="FName"> Non-housing expense(Monthly) :</label> <input
									maxlength="15" type="text" class="form-control" id="fname"
									allow-pattern="\d" name="monthlyExpense"
									ng-model="incomeandexpenses.monthlyExpense"
									placeholder="Enter monthly expense" ng-disabled="disabled" />
								<span style="color: red">{{errorMail4}}</span>
							</div>

							 <div class="form-group">
                        <label for="FName">Housing expense(Monthly) : </label>
                        <input maxlength="15" type="text" class="form-control" allow-pattern="\d" name="rentalExpense" ng-model="incomeandexpenses.rentalExpense" id="fname"  placeholder="Enter  rental expense" ng-disabled="disabled"/>
                     <span style="color: red">{{errorMail3}}</span>
                     </div >-
							<div ng-hide="!houseInfo">
								<div class="form-group">
									<label for="FName">Remaining mortgage : </label> <input
										maxlength="15" type="text" class="form-control"
										allow-pattern="\d" name="remainingMortgage"
										ng-model="incomeandexpenses.remainingMortgage " id="fname"
										placeholder="Enter  rental expense" ng-disabled="disabled" />
									<span style="color: red">{{errorMail7}}</span>
								</div>
								<div class="form-group">
									<label for="FName">What is your current mortgage rate?
										: </label> <input type="number" class="form-control"
										max="100" name="whatIsYourCurrentMortgageRate" 										
										ng-model="incomeandexpenses.whatIsYourCurrentMortgageRate"
										id="fname" placeholder="Enter  rental expense"
										ng-disabled="disabled" /> <span style="color: red">{{errorMail6}}</span>
								</div>
								
								

							</div>
						</div>
					</div>


				</div>
				<div class="form-group pull-left">
					<input type=hidden ng-model="incomeandexpenses.form" value="form1"
						ng-init="incomeandexpenses.form='editincome'">
					<button type="submit" class="btn btn-default hideinpdf" data-dismiss="modal"
						ng-click="disabled=true;editincome();">Save</button>
				</div>
				<div class="form-group">
                        <label for="FName">Other Expense : </label>
                        <input maxlength="15" type="text" class="form-control" allow-pattern="\d" name="otherExpense" ng-model="incomeandexpenses.otherExpense" id="fname"  placeholder="Enter other expenses" ng-disabled="disabled" />
                     </div>


				</div>
				<div class="col-md-6">


				</div>


			</div>
			<div class="col-lg-10 col-lg-offset-1 text-center">
				<a href="#portfolio" style="background-color: #E65319;"
					class="btn btn-primary btn-xl page-scroll "
					ng-click="nextIncomeExpenses()">Next</a>
			</div>
		</div>
		
	</section> -->
	<br></br>
	<br></br>
	<section class="bg-primary" id="portfolio">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1 text-center">
					<h2 class="section-heading">Asset Details</h2>
					<a href="#" data-toggle="modal" data-target="#editAssetDetails"
						class="pull-right" ng-click="newAssetsEdit()">Edit </a>
						<br></br>
					<br></br>
					<div class="col-md-4">
						<div class="form-group">
							<label for="Age" >Cash or cash-equivalent : </label>{{userdetails.cash}}
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="Age">Taxable Investments : </label>{{userdetails.taxableInvestments}}
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="Age">Non Taxable Investments : </label>{{userdetails.nonTaxableInvestments}}
						</div>
					</div>
					<!-- 		<div class="col-md-4">
						<div class="form-group">

							<label>Tax-Deferred Retirement Plans:</label>{{userdetails.Taxdeferred}}<br>

						</div>

					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label> Roth Retirement Plans:</label>{{userdetails.rothRetirement}}<br>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label> 529 Plans:</label>{{fiveTnp}}
						</div>
					</div> -->
<!-- 
					<div class="col-md-4 ">
						<div class="form-group ">
							<label for="Age">Real Estate : </label>{{userdetails.realEstate
							}}
						</div>
					</div> -->
					<div ng-hide="limit">
						<div class="col-md-4 ">
							<div class="form-group ">
								<label for="Age">Balance in 401(k) account : </label>{{userdetails.user401
								}}
							</div>
						</div>
						<div class="col-md-4 ">
							<div class="form-group ">
								<label for="Age">Balance in traditional IRA account: </label>{{userdetails.userIra
								}}
							</div>
						</div>
						<div class="col-md-4 ">
							<div class="form-group ">
								<label for="Age">Balance in Roth IRA account : </label>{{userdetails.userRothira
								}}
							</div>
						</div>
						<div class="col-md-4 ">
							<div class="form-group ">
								<label for="Age"> Balance in 529 plan: </label>{{userdetails.user559
								}}
							</div>
						</div>
					</div>
					<div ng-hide="limitSpouse">
						<div class="col-md-4 ">
							<div class="form-group ">
								<label for="Age">Balance in spouse 401(k) account : </label>{{userdetails.spouse401
								}}
							</div>
						</div>
						<div class="col-md-4 ">
							<div class="form-group ">
								<label for="Age">Balance in spouse IRA account: </label>{{userdetails.spouseIra
								}}
							</div>
						</div>
						<div class="col-md-4 ">
							<div class="form-group ">
								<label for="Age"> spouse Roth IRA account : </label>{{userdetails.spouseRothira
								}}
							</div>
						</div>
						<div class="col-md-4 ">
							<div class="form-group ">
								<label for="Age"> Balance in spouse 529 plan: </label>{{userdetails.spouse529
								}}
							</div>
						</div>
					</div>
				</div>



			</div>
		</div>
	</section>
	</div>
	</div>
	<div class="modal fade" id="incomeProjection" role="dialog">
		<div class="modal-dialog modal-chartDetails">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Income Projection</h4>
				</div>
				<div class="modal-body text-center">
					<span> Select an income profile</span> <select style="width: 25%;"
						id="sel1" ng-model="incomeProfile.name"
						ng-change="getIncomeProfile(incomeProfile.name)">
						<option ng-repeat="profile in incomeProfiles" ng-value="profile">{{profile}}</option>
					</select> <br>
					<div id="chart-container1">Income Expense chart will render
						here</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="editAssetDetails" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Edit Asset Details</h4>
				</div>
				<div class="modal-body">
					<div class="first-column">

						<label for="FName">Cash or cash-equivalent</label> <input
							maxlength="15" type="text" class="form-control"
							allow-pattern="\d" name="cashEquivalent"
							ng-model="editAssetDetails.cash" id="fname"
							placeholder="Cash or cash-equivalent" /> <label for="FName">Taxable
							Investments</label> <input maxlength="15" type="text"
							class="form-control" allow-pattern="\d" name="taxableInvestments"
							ng-model="editAssetDetails.taxableInvestments" id="fname"
							placeholder="Taxable Investments" />
						<!-- <label for="FName">Non
							Taxable Investments</label> <input maxlength="15" type="text"
							class="form-control" allow-pattern="\d" name="taxableInvestments"
							ng-model="editAssetDetails.nonTaxableInvestments" id="fname"
							placeholder="Taxable Investments" /> -->
						<!-- <span class="font-para-black">529 Plans and Retirement Accounts(401k, traditional IRA):</span> -->
					<!-- 	<label for="FName">529 Plans and Retirement Accounts(401k,
							traditional IRA):</label> <select
							ng-model="editAssetDetails.nonTaxableInvestments"
							ng-change="iralimits()" class="font-dropdown-small">
							<option
								ng-selected="limit.id ==editAssetDetails.nonTaxableInvestments"
								ng-repeat="limit in limits" ng-value="limit.id">{{limit.id}}</option>
						</select> -->
						
						<label  for="FName">529 Plans and Retirement Accounts(401k,
							traditional IRA): </label> <select
									ng-model="editAssetDetails.nonTaxableInvestments" ng-change="iralimits()"
									class="form-control">
									<option ng-selected="limit.id ==editAssetDetails.nonTaxableInvestments"
										ng-repeat="limit in limits" ng-value="limit.id">{{limit.id}}</option>

								</select>
									<div ng-hide="limitOption"> 
								<label for="FName">Balance
								in 401(k) account:</label> 
								<input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.u401" id="Age"
								placeholder="0" /><span style="color: red">{{errorMessage401}}</span>
								 <label for="FName">Balance
								in traditional IRA account:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.uIRA" id="fname"
								placeholder="0" /> <span style="color: red">{{errorMessageira}}</span>
								<label for="FName">Balance
								in Roth IRA account: </label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.uRothIra" id="fname"
								placeholder="0" /><span style="color: red">{{errorMessagerira}}</span></div>

					</div>
					<div class="second-column" >
						<div class="form-group">
<!-- 
							<!-- <input maxlength="15" type="text" class="form-control" allow-pattern="\d" name="nonTaxableInvestments" ng-model="editAssetDetails.nonTaxableInvestments" id="fname" placeholder="Non-Taxable Investments" />
							<label for="FName">Tax-Deferred Retirement Plans</label> <input
								maxlength="15" type="text" class="form-control"
								allow-pattern="\d" name="nonTaxableInvestments"
								ng-model="editAssetDetails.nonTaxableInvestments1" id="fname"
								placeholder="Deferred Retirement Plans" /> <label for="FName">Roth
								Retirement Plans</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.nonTaxableInvestments2" id="fname"
								placeholder="Roth Retirement Plans" /> <label for="FName">529
								Plans</label> <input maxlength="15" type="text" class="form-control"
								allow-pattern="\d" name="nonTaxableInvestments"
								ng-model="editAssetDetails.nonTaxableInvestments3" id="fname"
								placeholder="529 Plans" />  -->
								
						
		
								<div ng-hide="limitOption"> 
						<!-- 		<label for="FName">Balance
								in 401(k) account:</label> 
								<input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.u401" id="Age"
								placeholder="529 Plans" /> <label for="FName">Balance
								in traditional IRA account:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.uIRA" id="fname"
								placeholder="529 Plans" /> 
								<label for="FName">Balance
								in Roth IRA account: </label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.uRothIra" id="fname"
								placeholder="529 Plans" />  --><label for="FName"> Balance
								in 529 plan:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.u529" id="fname"
								placeholder="0" /><span style="color: red">{{errorMessage529}}</span></div>
								
								<div ng-hide="limitSpouseOption">
								 <label for="FName">Balance
								in spouse 401(k)account:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.s401" id="fname"
								placeholder="0" /><span style="color: red">{{errorMessages401}}</span> <label for="FName">Balance
								in spouse Roth IRA account:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.sIRA" id="fname"
								placeholder="0" /><span style="color: red">{{errorMessagesira}}</span> <label for="FName">spouse
								Roth IRA account:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.sRothIra" id="fname"
								placeholder="0" /> <label for="FName">Balance
								in spouse 529 plan:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.s529" id="fname"
								placeholder="0" />	<span style="color: red">{{errorMessages529}}</span></div>



							<!-- 			
								<div ng-hide="limit">
						      <br></br> <span class="font-para-black"> Balance in 401(k) account:
							  </span> <input type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12" type="number"
							placeholder="" maxlength="15" class="input_bl-long"
							ng-model="editAssetDetails.u401" /> <br></br> <span
							class="font-para-black">Balance in traditional IRA account: </span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12"  min="0"
							type="number" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="feditAssetDetails.uIRA" /> <br></br> <span
							class="font-para-black">Balance in Roth IRA account: </span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="editAssetDetails.uRothIra" /> <br></br> <span
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
							ng-model="editAssetDetails.s401" /> <br></br> <span
							class="font-para-black">`</span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12"  min="0"
							type="number" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="editAssetDetails.sIRA" /> <br></br> <span
							class="font-para-black">Balance in spouse Roth IRA account: </span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="editAssetDetails.sRothIra" /> <br></br> <span
							class="font-para-black"> Balance in spouse 529 plan:  </span> <input type="text"
							onkeypress="return onlyNumbers(event,this)" maxlength="13"
							placeholder="" maxlength="12" class="input_bl-long"
							ng-model="formData3.s529" /> <br></br>
						 -->
						</div>

						<!--  <label for="FName">Real Estate</label>
                  <input maxlength="15" type="text" class="form-control" allow-pattern="\d" name="nonTaxableInvestments" ng-model="editAssetDetails.realestate" id="fname" placeholder="Real Estate" /> -->
					</div>
					<br><br>
				
				</div>
				
					<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<input type=hidden ng-model="editAssetDetails.form" value="form1"
					ng-init="editAssetDetails.form='editAssets'">
				<button type="submit" class="btn btn-default" data-dismiss="modal"
					ng-click="editAssets()">Save</button>
			</div>
				<div class="form-group"></div>
			</div>
			
		</div>
	</div>
	
		
		<div class="modal fade" id="newEdit" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Edit Asset Details</h4>
				</div>
				<div class="modal-body">
					<div class="first-column" >

						<label for="FName">Cash or cash-equivalent</label> <input
							maxlength="15" type="text" class="form-control"
							allow-pattern="\d" name="cashEquivalent"
							ng-model="editAssetDetails.cash" id="fname"
							placeholder="Cash or cash-equivalent" /> <label for="FName">Taxable
							Investments</label> <input maxlength="15" type="text"
							class="form-control" allow-pattern="\d" name="taxableInvestments"
							ng-model="editAssetDetails.taxableInvestments" id="fname"
							placeholder="Taxable Investments" />
						<!-- <label for="FName">Non
							Taxable Investments</label> <input maxlength="15" type="text"
							class="form-control" allow-pattern="\d" name="taxableInvestments"
							ng-model="editAssetDetails.nonTaxableInvestments" id="fname"
							placeholder="Taxable Investments" /> -->
						<!-- <span class="font-para-black">529 Plans and Retirement Accounts(401k, traditional IRA):</span> -->
					<!-- 	<label for="FName">529 Plans and Retirement Accounts(401k,
							traditional IRA):</label> <select
							ng-model="editAssetDetails.nonTaxableInvestments"
							ng-change="iralimits()" class="font-dropdown-small">
							<option
								ng-selected="limit.id ==editAssetDetails.nonTaxableInvestments"
								ng-repeat="limit in limits" ng-value="limit.id">{{limit.id}}</option>
						</select> -->
						
						<label  for="FName">529 Plans and Retirement Accounts(401k,
							traditional IRA): </label> <select
									ng-model="editAssetDetails.nonTaxableInvestments" ng-change="iralimits()"
									class="form-control">
									<option ng-selected="limit.id ==editAssetDetails.nonTaxableInvestments"
										ng-repeat="limit in limits" ng-value="limit.id">{{limit.id}}</option>

								</select>
									<div ng-hide="limitOption"> 
								<label for="FName">Balance
								in 401(k) account:</label> 
								<input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.u401" id="Age"
								placeholder="0" />
								<span style="color: red">{{errorMessage401}}</span>
								 <label for="FName">Balance
								in traditional IRA account:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.uIRA" id="fname"
								placeholder="0" /> 
								<span style="color: red">{{errorMessageira}}</span>
								<label for="FName">Balance
								in Roth IRA account: </label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.uRothIra" id="fname"
								placeholder="0" />
								<span style="color: red">{{errorMessagerira}}</span>
								</div>

					</div>
					<div class="second-column" >
						<div class="form-group">
<!-- 
							<!-- <input maxlength="15" type="text" class="form-control" allow-pattern="\d" name="nonTaxableInvestments" ng-model="editAssetDetails.nonTaxableInvestments" id="fname" placeholder="Non-Taxable Investments" />
							<label for="FName">Tax-Deferred Retirement Plans</label> <input
								maxlength="15" type="text" class="form-control"
								allow-pattern="\d" name="nonTaxableInvestments"
								ng-model="editAssetDetails.nonTaxableInvestments1" id="fname"
								placeholder="Deferred Retirement Plans" /> <label for="FName">Roth
								Retirement Plans</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.nonTaxableInvestments2" id="fname"
								placeholder="Roth Retirement Plans" /> <label for="FName">529
								Plans</label> <input maxlength="15" type="text" class="form-control"
								allow-pattern="\d" name="nonTaxableInvestments"
								ng-model="editAssetDetails.nonTaxableInvestments3" id="fname"
								placeholder="529 Plans" />  -->
								
						
		
								<div ng-hide="limitOption"> 
						<!-- 		<label for="FName">Balance
								in 401(k) account:</label> 
								<input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.u401" id="Age"
								placeholder="529 Plans" /> <label for="FName">Balance
								in traditional IRA account:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.uIRA" id="fname"
								placeholder="529 Plans" /> 
								<label for="FName">Balance
								in Roth IRA account: </label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.uRothIra" id="fname"
								placeholder="529 Plans" />  --><label for="FName"> Balance
								in 529 plan:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.u529" id="fname"
								placeholder="0" />
									<span style="color: red">{{errorMessage529}}</span>
								</div>
								
								<div ng-hide="limitSpouseOption">
								 <label for="FName">Balance
								in spouse 401(k)account:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.s401" id="fname"
								placeholder="0" /> 
								<span style="color: red">{{errorMessages401}}</span>
								<label for="FName">Balance
								in spouse Roth IRA account:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.sIRA" id="fname"
								placeholder="0" />
								<span style="color: red">{{errorMessagesira}}</span>
								 <label for="FName">spouse
								Roth IRA account:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.sRothIra" id="fname"
								placeholder="0" /> 
								<span style="color: red">{{errorMessagesrira}}</span>
							<label for="FName">Balance
								in spouse 529 plan:</label> <input maxlength="15" type="text"
								class="form-control" allow-pattern="\d"
								name="nonTaxableInvestments"
								ng-model="editAssetDetails.s529" id="fname"
								placeholder="0" />
							<span style="color: red">{{errorMessages529}}</span>
								
								</div>



							<!-- 			
								<div ng-hide="limit">
						      <br></br> <span class="font-para-black"> Balance in 401(k) account:
							  </span> <input type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12" type="number"
							placeholder="" maxlength="15" class="input_bl-long"
							ng-model="editAssetDetails.u401" /> <br></br> <span
							class="font-para-black">Balance in traditional IRA account: </span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12"  min="0"
							type="number" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="feditAssetDetails.uIRA" /> <br></br> <span
							class="font-para-black">Balance in Roth IRA account: </span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="editAssetDetails.uRothIra" /> <br></br> <span
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
							ng-model="editAssetDetails.s401" /> <br></br> <span
							class="font-para-black">`</span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12"  min="0"
							type="number" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="editAssetDetails.sIRA" /> <br></br> <span
							class="font-para-black">Balance in spouse Roth IRA account: </span> <input
							type="text" onkeypress="return onlyNumbers(event,this)"
							maxlength="12" placeholder="" maxlength="15" class="input_bl-long"
							ng-model="editAssetDetails.sRothIra" /> <br></br> <span
							class="font-para-black"> Balance in spouse 529 plan:  </span> <input type="text"
							onkeypress="return onlyNumbers(event,this)" maxlength="13"
							placeholder="" maxlength="12" class="input_bl-long"
							ng-model="formData3.s529" /> <br></br>
						 -->
						</div>

						<!--  <label for="FName">Real Estate</label>
                  <input maxlength="15" type="text" class="form-control" allow-pattern="\d" name="nonTaxableInvestments" ng-model="editAssetDetails.realestate" id="fname" placeholder="Real Estate" /> -->
					</div>
					<br><br>
				
				</div>
				
					<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<input type=hidden ng-model="editAssetDetails.form" value="form1"
					ng-init="editAssetDetails.form='editAssets'">
				<button type="submit" class="btn btn-default" data-dismiss="modal"
					ng-click="editAssets()">Save</button>
			</div>
				<div class="form-group"></div>
			</div>
			
		</div>
</div>
	</div>



	<div class="modal fade " id="editBasicDetails" role="dialog">
		<div class="modal-dialog modal-basicDetails">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" ng-click="cleanEditBasicDetails()" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Edit Basic Details</h4>
				</div>
				<div class="modal-body">

					<form>
						<div class="first-column">
							<!-- Your first column here -->
							<div class="form-group">
								<label for="FName">First Name</label> <input maxlength="25"
									type="text" allow-pattern="[a-z]" class="form-control"
									id="fname" placeholder="Enter first name"
									ng-model="editBasicDetail.fname" /> <span style="color: red">{{errorname1}}</span>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Email address</label> <input
									maxlength="25" type="email" class="form-control"
									id="exampleInputEmail1" placeholder="Enter email"
									ng-model="editBasicDetail.Email" disabled /> <span
									style="color: red">{{errorname2}}</span>
							</div>
							<div class="form-group">
								<label for="address2">State</label> <select class="form-control"
									ng-model="editBasicDetail.state" ng-change="getCities()">
									<option ng-selected="state.name == editBasicDetail.state "
										ng-repeat="state in states" ng-value="state.name">{{state.name}}</option>

								</select>
							</div>
							<div class="form-group">
								<label for="marital Status"> Tax Filing Status </label> <select
									ng-model="editBasicDetail.filingStatus" class="form-control"
									ng-change="checkDependants()">
									<option
										ng-selected="filingOptions.id == editBasicDetail.filingStatus"
										ng-repeat="filingOptions in filingOptions"
										ng-value="filingOptions.id">{{filingOptions.id}}</option>

								</select>
							</div>
							
							<!-- <div class="form-group">
								<label for="address2">College Info</label>
								<textarea maxlength="100" class="form-control" rows="3"
									placeholder="Text Area" ng-model="editBasicDetail.college_info"></textarea>
							</div>
							<div class="form-group">
								<label for="address1">Address 1</label>
								<textarea maxlength="100" class="form-control" rows="3"
									placeholder="Text Area" ng-model="editBasicDetail.address1"></textarea>
							</div>
							<div class="form-group">
								<label for="address2">Address 2</label>
								<textarea maxlength="100" class="form-control" rows="3"
									placeholder="Text Area" ng-model="editBasicDetail.address2"></textarea>
							</div> -->

						</div>
						<div class="second-column">
							<div class="form-group">
								<label for="LName">Last Name</label> <input maxlength="25"
									type="lname" allow-pattern="[a-z]" class="form-control"
									id="lname" placeholder="Enter last name"
									ng-model="editBasicDetail.lname" />
								<!--    <span style="color: red">{{errorname4}}</span> -->
							</div>
							<div class="form-group">
								<label for="Age">Age</label>
								<select ng-model="editBasicDetail.age" class="form-control"  id="age">
						<option ng-selected="age.number == editBasicDetail.age"
							ng-repeat="age in ages" ng-value="age.number">{{age.number}}</option>
					</select>
					<!--  <input maxlength="3" type="text"
									allow-pattern="\d" min="0" class="form-control" id="age"
									placeholder="Enter Age" ng-model="editBasicDetail.age" />  --><span
									style="color: red">{{errorname3}}</span>
							</div>
							<div class="form-group">
								<label for="address2">City</label> <select class="form-control"
									ng-model="formdata.desired_locationcity"
									ng-change="getCounty()">
									<!--  <option ng-selected="state.name == editBasicDetail.state " ng-repeat="state in states" ng-value="state.name">{{state.name}}</option> -->
									<option ng-selected="city==formdata.desired_locationcity"
										ng-repeat="city in citys">{{ city }}</option>

								</select>
							</div>
							<!-- <div class="form-group">
								<label for="LName">County</label> <input type="text"
									class="form-control" id="county" placeholder=""
									ng-disabled="disabledCounty" ng-model="editBasicDetail.county" />
							</div> -->


							</select>
						
				
							<div class="form-group">
								<label for="marital Status"> Are you married? </label> <select
									ng-model="editBasicDetail.married" ng-change="change()"
									class="form-control">
									<option ng-selected="option.id == editBasicDetail.married "
										ng-repeat="option in options" ng-value="option.id">{{option.id}}</option>

								</select>
							</div>
							<!--     <div class="form-group">
                        <label for="DOB">DOB</label>
                        <input type="date" allow-pattern="\d" class="form-control" id="dob" placeholder="Enter the DOB" ng-model="editBasicDetail.dob"  />
                     </div> -->
							<div ng-hide="!marriedt">
								<div class="form-group">
									<label for="Vertical">----Spouse Details----</label><br>
									<div class="form-group">
										<label for="FName">First Name</label> <input maxlength="25"
											type="fname" allow-pattern="[a-z]" class="form-control"
											id="fname" placeholder="Enter first name"
											ng-model="editBasicDetail.spouse_fname" /> <span
											style="color: red">{{errorname4}}</span>
									</div>
									<div class="form-group">
										<label for="LName">Last Name</label> <input maxlength="25"
											type="lname" allow-pattern="[a-z]" class="form-control"
											id="lname" placeholder="Enter last name"
											ng-model="editBasicDetail.spouse_lname" />
										<!--  <span style="color: red">{{errorname5}}</span> -->
									</div>
									<div class="form-group">
										<label for="Age">Age</label> <input maxlength="25" type="text"
											allow-pattern="\d" min="0" class="form-control" id="age"
											placeholder="Enter Age" ng-model="editBasicDetail.spouse_age" />
										<span style="color: red">{{errorname5}}</span>
									</div>

									<!--         <label for="Kidscount">
                        Age How many kids do you have?
                         </label>
                        	
                        	<select ng-options="kid as kid.number for kid in kidsnumber" ng-model="editBasicDetail.kidscount" ng-change="addkids()" class="form-control">
                        							 	
                        	</select> 
                        		 <div ng-repeat="kid in formData1.kids" >
                        		 <div class="first-column">
	        									<label for="Kids Name">Kids name</label>
	        									<input onkeypress="return onlyAlphabets(event,this)" type="tel" placeholder="" maxlength="15" name="name" class="form-control" ng-model="editBasicDetail.name"/>
	        									</div>
	        									<div class="second-column">
	        									<label for="Kids age"> Age</label>
	        									<select ng-options="kidsage as kidsage.id for kidsage in kidsages"  class="form-control" name="age" ng-model="editBasicDetail.age">
	        	
	        									</select> 
	        									</div>
		    							</div> -->
                               <!--     <div ng-hide="!kidForSingle">  -->
									<label for="Kidscount">How many dependent child do you have? </label> <select
										ng-model="editBasicDetail.kidscount" ng-change="addkids()"
										class="form-control">
										<option ng-selected="kid.number==editBasicDetail.kidscount"
											ng-repeat="kid in kidsnumber" ng-value="kid.number">{{kid.number}}</option>

										<!--    <span style="color: red">{{errorMail8}}</span> -->
									</select> <span style="color: red">{{error1}}</span>

									<div ng-repeat="kid in editBasicDetail.kids">
									<div style="color: red">{{nameErrorMesg}}</div>

										<div class="col-md-5">
											<label for="Child's name">Child's name</label> <input
												onkeypress="return onlyAlphabets(event,this)" allow-pattern="[a-z]" type="tel"
												placeholder="" maxlength="15" name="name"
												class="form-control" ng-model="kid.name" />
										</div>
										<div class="col-md-3">
											<label for="Kids age"> Age</label> <select
												class="form-control" name="age" ng-model="kid.age" ng-change="studentType()">
												<option ng-selected="kidsage.id==kid.age"
													ng-repeat="kidsage in kidsages" ng-value="kidsage.id">{{kidsage.id}}</option>

											</select>
										</div>
										<div class="col-md-4" ng-hide="kidsType[{{$index}}]">
											<label for="Kids consideration">Student</label> <select
												class="form-control" name="age" ng-model="kid.flag">
												<option ng-selected="kid.flag==dependencies.id"
													ng-repeat="dependencies in dependency">{{dependencies.id}}</option>
											</select>
										</div>
									<!-- 	<div class="col-md-4" ng-hide="kidsType">
											<label for="Kids consideration">Student</label> <select
												class="form-control" name="age" ng-model="kid.flag">
												<option ng-selected="kid.flag==dependencies.id"
													ng-repeat="dependencies in dependency">{{dependencies.id}}</option>
											</select>
										</div> -->

									</div>
                                   

									<!--   <div class="form-group">
                           <label for="DOB">DOB</label>
                           <input type="date"  allow-pattern="\d" class="form-control" id="dob" placeholder="Enter the DOB" ng-model="editBasicDetail.spouse_dob"/>
                        </div> -->

								</div>
							</div>
							
							          <div ng-hide="!kidForSingle">
									<label for="Kidscount"> How many dependent child do you have? </label> <select
										ng-model="editBasicDetail.kidscount" ng-change="addkids()"
										class="form-control">
										<option ng-selected="kid.number==editBasicDetail.kidscount"
											ng-repeat="kid in kidsnumber" ng-value="kid.number">{{kid.number}}</option>

										<!--    <span style="color: red">{{errorMail8}}</span> -->
									</select> <span style="color: red">{{error1}}</span>

									<div ng-repeat="kid in editBasicDetail.kids">

										<div class="col-md-5">
											<label for="Kids Name">Child's name</label> <input
												onkeypress="return onlyAlphabets(event,this)"  allow-pattern="[a-z]" type="tel"
												placeholder="" maxlength="15" name="name"
												class="form-control" ng-model="kid.name" />

										</div>
										<div class="col-md-3">
											<label for="Kids age"> Age</label> <select
												class="form-control" name="age" ng-model="kid.age"  ng-change="studentType()">
												<option ng-selected="kidsage.id==kid.age"
													ng-repeat="kidsage in kidsages" ng-value="kidsage.id">{{kidsage.id}}</option>

											</select>
										</div>
										<div class="col-md-4" ng-hide="kidsType[{{$index}}]">
											<label for="Kids consideration">Student</label> <select
												class="form-control" name="age" ng-model="kid.flag">
												<option ng-selected="kid.flag==dependencies.id"
													ng-repeat="dependencies in dependency">{{dependencies.id}}</option>
											</select>
										</div>

									</div>
								
                                     </div>
                                     <br>
                                     			<div class="form-group" ng-hide="!dependants" >
								<div style="color: red;">{{dependantsErr}}</div>
								<label for="No. of dependents" style="margin-top: 4%"> No.of Other Dependents </label> <input
									maxlength="2" type="text" allow-pattern="\d" min="0"
									class="form-control" id="age" placeholder="Enter dependants"
									ng-model="editBasicDetail.dependants" />

							</div>
							<!-- Your second column here -->
						</div>
				</div>
				
				<div class="modal-footer">
					<input type=hidden ng-model="editBasicDetail.form"
						value="editBasicDetail"
						ng-init="editBasicDetail.form='editBasicDetail'">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						ng-click="cleanEditBasicDetails()">Close</button>
					<input type=hidden ng-model="editBasicDetail.form" value="form1"
						ng-init="editBasicDetail.form='editBasicDetails'">
					<button type="submit" class="btn btn-default"
						ng-click="editBasicDetails() ">Save</button>
				</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="assetsModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" ng-click="reloadFunction()">&times;</button>
					<h4 class="modal-title">Message</h4>
				</div>
				<div class="modal-body">
					<p>Entered value will result the assets to go negative ,kindly
						reenter some feasible value</p>
				</div>
				<div class="modal-footer">
					<button type="button" 
						class="btn btn-primary pull-right" data-dismiss="modal" ng-click="reloadFunction()">close</button>


				</div>
			</div>

		</div>
	</div>
	<div class="modal fade" id="assetsModalExpense" role="dialog">
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
					<button type="button" id="dialog-ok" ng-click="editExpense()"
						class="btn btn-primary pull-right" data-dismiss="modal">close</button>


				</div>
			</div>

		</div>
	</div>
	<div class="modal fade" id="assetsModalExpense" role="dialog">
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
					<button type="button" id="dialog-ok" ng-click="closeEditProfile()"
						class="btn btn-primary pull-right" data-dismiss="modal">close</button>


				</div>
			</div>

		</div>
	</div>
	<div id="myModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" ng-click="check1()" class="close"
						data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Error</h4>
				</div>
				<div class="modal-body">
					<p>Please enter basic details...</p>
				</div>
				<div class="modal-footer">
					<button type="button" ng-click="check1()" class="btn btn-default"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div id="myModal1" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" ng-click="editincome()" class="close"
						data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Message</h4>
				</div>
				<div class="modal-body">
					<p>You have made some changes! Do you want to save before
						proceeding?</p>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" ng-click="load1();"
						data-dismiss="modal">No</button>
					<button type="button" ng-click="editincome()"
						class="btn btn-default" data-dismiss="modal">Yes</button>
				</div>

			</div>
		</div>
	</div>
	<!--       ------------------------------------------------------------------------------------------------------
 -->
	<div class="modal fade" id="changePassword" role="dialog">
		<div class="modal-dialog">

			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Change Password</h4>
				</div>
				<div class="modal-body">
					<div style="color: red">{{msgerr}}</div>
					<div class="form-group">
						<label for="FName">Current Password</label> <input maxlength="15"
							type="password" class="form-control"
							ng-model="changePassword.currentPassword"
							ng-keypress="removeChangePasswordError()"
							placeholder="Enter the current password" />
					</div>
					<div class="form-group">
						<label for="FName">New-Password</label> <input maxlength="15"
							type="password" class="form-control"
							ng-model="changePassword.newPassword"
							ng-keypress="removeChangePasswordError()" id="fname"
							placeholder="Enter the new password" />
					</div>
					<div class="form-group">
						<label for="FName">Re-Enter New Password</label> <input
							maxlength="15" type="password" class="form-control"
							ng-model="changePassword.reEnterPassword"
							ng-keypress="removeChangePasswordError()" id="fname"
							placeholder="Re-Enter the new password" />
					</div>
					<div style="color: red">{{ passwordmissmatcherror }}</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type=hidden ng-model="changePassword.form" value="form1"
						ng-init="changePassword.form='changePassword'">
					<button type="submit" class="btn btn-default"
						ng-click="changepassword()">Submit</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="successPassword" role="dialog">
		<div class="modal-dialog">

			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Change Password</h4>
				</div>
				<div class="modal-body">{{ massageAfterChangePassword }}</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

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
	<div id="AlertModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" ng-click="editincome()" class="close"
						data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Warning</h4>
				</div>
				<div class="modal-body">
					<p>{{AlertModal}}</p>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

				</div>

			</div>
		</div>
	</div>
	<!--       -----
<!--       ======================================================================================
 -->
	<br></br>
	<br></br>
	<!-- Footer -->
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
	<script src="assets/js/jquery-1.12.4.min.js"></script>
    <script src="assets/js/bootstrap-3.3.7.min.js"></script>
    <script src="assets/js/kendo.all.min.js"></script>	
	<script src="assets/js/fusioncharts.theme.fint.js"></script>
	<script src="assets/js/fusioncharts.powercharts.js"></script>
	<script src="assets/js/fusioncharts.js"></script>
</body>
</html>
