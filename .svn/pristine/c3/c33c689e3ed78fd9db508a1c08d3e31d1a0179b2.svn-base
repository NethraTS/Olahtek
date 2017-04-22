<!DOCTYPE html>
<html lang="en" ng-app="retirementFormApp">
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
<script src="js/app.js"></script>
<script src="js/RetirementGoal.js"></script>

<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css"
	type="text/css">
<!-- Plugin CSS -->
<link rel="stylesheet" href="css/animate.min.css" type="text/css">
<!-- Custom CSS -->
<link rel="stylesheet" href="css/creative1.css" type="text/css">
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
.left_content {
	height: 500px;
	border-radius: 25px;
}

.right_content {
	height: 500px;
	background: white;
	"
} /* .input_bl-long {
  color: #1F93EF;
  width: 190px;
  display: inline-block;
  text-align: center;
  padding: 0px;
  background-color: transparent;
  font-size: 25px;
  transition: all 0.3s ease-in-out 0s;
  border-width: 0px 0px 1px;
  border-style: none none solid;
  border-color: -moz-use-text-color -moz-use-text-color #BBB;
} */
.progress {
	width: 50%;
	margin-left: 20%;
}

#container {
	display: block;
	margin: 5px 0;
}

.modal-content {
	/* Bootstrap sets the size of the modal in the modal-dialog class, we need to inherit it */
	width: inherit;
	height: inherit;
	/* To center horizontally */
	margin: 0 auto;
	box-shadow: none;
	border: none;
}

.modal, .modal-backdrop {
	position: absolute !important;
	margin: 0 auto;
}

.bigform-content h1 {
	margin: 0;
}

p {
	
}

.bigform-content input[type=submit] {
	margin-top: 10px;
}
</style>
<script>


</script>
</head>
<body id="page-top" ng-controller="GoalPlane" ng-init="load()" ng-cloak>

<div class="MaskLayer"
ng-class="{isClosed : !masked}">
<div class="MaskLayer-Content">
<i class="fa fa-refresh fa-spin fa-3x"
										 style="position: absolute;    margin-left: 18%;    margin-top: 50%;    color: white;    z-index: 1;"></i>  
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
					<li><a class="page-scroll" href="#" ng-click="goDashboard()">Home</a>
					</li>
					<li><a class="page-scroll" href="#services">How it works?</a>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Case Studies <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="CaseStudy1.jsp">Case Study 1</a></li>
                            <li><a href="CaseStudy2.jsp">Case Study 2</a></li>
                            <li><a href="CaseStudy3.jsp">Case Study 3</a></li>
						</ul>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Resources <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="mortgageCalculator.jsp">Mortgage Calculator</a></li>
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
					<li><a class="page-scroll" href="userProfile.jsp"><i
							class="fa fa-user-plus"></i> My Profile</a></li>

					<li><a class="page-scroll" ng-click="deleteAllCookies()"><i
							class="fa fa-sign-out"></i> Logout</a></li>
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
				<div class="title-createplan">
					Retirement
					<button class="btn btn-primary pull-right"
						ng-click="goSelectGoal()">Back</button>
				</div>
				<div>
					<div class="side-panel">
						<div class="left_content">
							<h1>

								<div class="sidebar-collapse">



									<ul class="nav" id="main-menu" style="font-size: 17px">

										<li><a class="active-menu"><i class="fa fa-arrow ">{{Sidemessage}}</i></a>
										</li>


									</ul>
								</div>
							</h1>
							<form name="goalDetails" id="goalForm">
								<br>
								<div></div>

								<div class="form-group" align="left" ng-show="show1>1">
									<p>
										Retirement Age
										<!-- 		<input type="text" class="form-control" name="Retirementage" ng-model="formdata.Retirementage" onkeypress="return numbersonly(event)"> -->
										<select name="Retirementage" ng-model="formdata.Retirementage"
											class="form-control">
											<option ng-selected="age.number == formdata.Retirementage "
												ng-repeat="age in ages" ng-value="age.number">{{age.number}}</option>
										</select>
									</p>
							<!-- 		<p ng-show="showNote" style="color:red;font-size: 12px;padding-left: 3%;">
										Note : Retirement age should not be less than 62
									</p> -->	
								</div>
								


								<div class="form-group" align="left" ng-show="show1==3">
									<p>
										Employer's savings <input type="text" class="form-control"
											name="Employer's savins" value="80" maxlength="2"
											ng-model="formdata.amountEmpSav"
											onkeypress="return numbersonly(event)" />
									</p>
									<div class="slider"></div>
					
									<div class="form-group" align="left">
										<p>
											Employer's matching to the cotribution <input type="text"
												class="form-control" name="Employer's savins" value="80"
												maxlength="2" ng-model="formdata.amountECost"
												onkeypress="return numbersonly(event)" />
										</p>
										<div class="slider"></div>
									</div>
								</div>

								<div class="form-group" align="left" ng-show="show1==12">
										<p>
											Employer's contribution <input type="text"
												class="form-control" name="Employer's savins" value="80"
												maxlength="2" ng-model="formdata.amountEmpCont"
												onkeypress="return numbersonly(event)" />
										</p>
										<div class="slider"></div>
									</div>
								<div class="form-group" align="left" ng-show="show1==4">
									<!-- <p>
										Saved in your IRA <input type="text" class="form-control"
											name="Employer's savins" value="80" maxlength="2"
											ng-model="formdata.iraSaved"
											onkeypress="return numbersonly(event)" />
									</p> -->
									<div class="slider"></div>
									<div class="form-group" align="left">
										<p>
											Contribute to your IRA? <input type="text"
												class="form-control" name="Employer's savins" value="80"
												maxlength="2" ng-model="formdata.iraContribute"
												onkeypress="return numbersonly(event)" />
										</p>
										<div class="slider"></div>
									</div>

								</div>

								<div class="form-group" align="left" ng-show="show1==5">
									<!-- <p>
										saved in your Roth IRA <input type="text" class="form-control"
											name="Employer's savins" value="80" maxlength="2"
											ng-model="formdata.rothIra"
											onkeypress="return numbersonly(event)" />
									</p> -->
									<div class="slider"></div>
									<div class="form-group" align="left">
										<p>
											Contribute to your IRA? <input type="text"
												class="form-control" name="Employer's savins" value="80"
												maxlength="2" ng-model="formdata.rothIraContribute"
												onkeypress="return numbersonly(event)" />
										</p>
										<div class="slider"></div>
									</div>

								</div>
								<div class="form-group" align="left" ng-show="show1==6">
									<p>
										saved in your employer's Roth account <input type="text"
											class="form-control" name="Employer's savins" value="80"
											maxlength="2" ng-model="formdata.rothAccSave"
											onkeypress="return numbersonly(event)" />
									</p>
									<div class="slider"></div>
									<div class="form-group" align="left">
										<p>
											contribute to your employer-sponsored Roth account <input
												type="text" class="form-control" name="Employer's savins"
												value="80" maxlength="2"
												ng-model="formdata.rothAccContribute"
												onkeypress="return numbersonly(event)" />
										</p>
										<div class="slider"></div>
									</div>

								</div>
								<div class="form-group" align="left" ng-show="show1==7">
									<p>
										spouse already saved in your employer's plan <input
											type="text" class="form-control" name="Employer's savins"
											value="80" maxlength="2" ng-model="formdata.spouseAmount"
											onkeypress="return numbersonly(event)" />
									</p>
									<div class="slider"></div>
									<div class="form-group" align="left">
										<p>
											spouse's employer match your spouse's contribution <input
												type="text" class="form-control" name="Employer's savins"
												value="80" maxlength="2" ng-model="formdata.amountSCost"
												onkeypress="return numbersonly(event)" />
										</p>
										<div class="slider"></div>
									</div>
								</div>
									<div class="form-group" align="left" ng-show="show1==13">
										<p>
											spouse contribute to the plan <input type="text"
												class="form-control" name="Employer's savins" value="80"
												maxlength="2" ng-model="formdata.spouseCont"
												onkeypress="return numbersonly(event)" />
										</p>
										<div class="slider"></div>
									</div>
								<div class="form-group" align="left" ng-show="show1==8">
									<p>
										Spouse IRA <input type="text" class="form-control"
											name="Employer's savins" value="80" maxlength="2"
											ng-model="formdata.sIraSaved"
											onkeypress="return numbersonly(event)" />
									</p>
									<div class="slider"></div>
									<div class="form-group" align="left">
										<p>
											Spouse Contribute to IRA <input type="text"
												class="form-control" name="Employer's savins" value="80"
												maxlength="2" ng-model="formdata.sIraContribute"
												onkeypress="return numbersonly(event)" />
										</p>
										<div class="slider"></div>
									</div>

								</div>

								<div class="form-group" align="left" ng-show="show1==9">
									<p>
										Spouse Roth IRA <input type="text" class="form-control"
											name="Employer's savins" value="80" maxlength="2"
											ng-model="formdata.sRothIra"
											onkeypress="return numbersonly(event)" />
									</p>
									<div class="slider"></div>
									<div class="form-group" align="left">
										<p>
											Spouse Contribute to Roth IRA <input type="text"
												class="form-control" name="Employer's savins" value="80"
												maxlength="2" ng-model="formdata.sRothIraContribute"
												onkeypress="return numbersonly(event)" />
										</p>
										<div class="slider"></div>
									</div>

								</div>

								<div class="form-group" align="left" ng-show="show1==10">
									<p>
										Spouse contribution plans offer Roth type accounts <input
											type="text" class="form-control" name="Employer's savins"
											value="80" maxlength="2" ng-model="formdata.sRothSave"
											onkeypress="return numbersonly(event)" />
									</p>
									<div class="slider"></div>
									<div class="form-group" align="left">
										<p>
											contribute to your spouse's employer-sponsored Roth account <input
												type="text" class="form-control" name="Employer's savins"
												value="80" maxlength="2" ng-model="formdata.sRothContribute"
												onkeypress="return numbersonly(event)" />
										</p>
										<div class="slider"></div>
									</div>

								</div>
						</div>
						</form>
					</div>
				</div>
				<div id="firstId" class="center-content" style="height: 750px;">
					<div class="center_panel">
						<div class="align-sidebar fade in " id="dialog_confirm_map"
							data-backdrop="static" data-keyboard="false" role="dialog"
							aria-labelledby="dialog_confirm_mapLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									</br>
									</br>
									<div class="progress">
										<div class="progress-bar progress-bar-striped active"
											id="progress_bar" ng-model="progressbar" aria-valuemin="0"
											aria-valuemax="100" style="width: 2%"></div>
									</div>


									<div class=" text-center">

										<div class="form-group" ng-show="show==1">
											<span style="color: red; font-size: 90%;">{{msgerr}}</span>
											<p>What is your Retirement age?</p>
											<!-- <input type="text" placeholder="65" name="firstname" ng-model="formdata.Retirementage" onkeypress="return numbersonly(event)"/></br></br> -->
											<select name="Retirementage"
												ng-model="formdata.Retirementage" class="form-control"
												style="width: 85px; margin-left: auto; margin-right: auto;">
												<option ng-selected="age.number == formdata.Retirementage "
													ng-repeat="age in userAges" ng-value="age.number">{{age.number}}</option>
											</select> </br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar()" ng-click="progressBar()">Next</button>
											</br>
											</br>
										</div>
										<div class="form-group" ng-show="show==56">
											<span style="color: red; font-size: 90%;">{{msgerr}}</span>
											<p>What is your Spouse's Retirement age?</p>
											<!-- <input type="text" placeholder="65" name="firstname" ng-model="formdata.Retirementage" onkeypress="return numbersonly(event)"/></br></br> -->
											<select name="sRetirementage"
												ng-model="formdata.sRetirementage" class="form-control"
												style="width: 85px; margin-left: auto; margin-right: auto;">
												<option ng-selected="age.number == formdata.sRetirementage "
													ng-repeat="age in ages" ng-value="age.number">{{age.number}}</option>
											</select> </br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar()" ng-click="progressBar56()">Next</button>
											</br>
											</br>
										</div>
										<span></span>
										<div class="form-group" ng-show="show==2">
											<span style="color: red; font-size: 90%;">{{msgerr}}</span>

											<p>Do you have an employer sponsored plan such as
												401(k) or 403(b)?</p>

											<div class="radio">
												<label> <input type="radio" name="user401Amount"
													value="yes" ng-click="yes()" ng-model="formdata.user401Amount">
													Yes
												</label> <label> <input type="radio" name="user401Amount"
													value="no" ng-value="true" ng-click="no()"
													ng-model="formdata.user401Amount"> No
												</label>
											</div>
													<div style="font-size: 9px;" ng-hide="emplyoerscontribution">
												<span class="font-para-black">How much have you
													already saved in your employer's plan in {{date | date:'yyyy'}} </span> <input
													type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.amountEmpSav" />
											</div>
                                             <div style="font-size: 9px;" ng-hide="emplyoerscontribution">
												<span class="font-para-black">Specify the cap for employer's contribution </span> 
													<select
														style="width: 131px; height: 30px; font-size: 18px;"
														name="Retirementage" ng-model="formdata.amountCap"
														class="font-dropdown">
														<option ng-selected="cap == formdata.amountCap "
															ng-repeat="cap in userCap" ng-value="age">{{cap}}</option>
													</select>
													
											</div>
														<div style="font-size: 9px;" ng-hide="emplyoerscontribution1">
													<span class="font-para-black"> How much does your
														employer match your contribution? </span> <select
														style="width: 131px; height: 30px; font-size: 18px;"
														name="Retirementage" ng-model="formdata.amountECost"
														class="font-dropdown">
														<option ng-selected="item == formdata.amountECost "
															ng-repeat="item in items" ng-value="age">{{item}}</option>
													</select>
												</div>
													<div style="font-size: 9px;" ng-hide="new401Cont">
											<p>Program calculate contribution automatically</p>

											<div class="radio">
											<label> <input type="radio" name="userCont401"
													value="yesFunc" ng-value="true" ng-click="yesFunc()"
													ng-model="formdata.userCont401" /> Yes
												</label>
												<label> <input type="radio" name="userCont401"
													value="noFunc" ng-click="noFunc()" ng-model="formdata.userCont401">
												 No
												</label> 
											</div>
											
											<div style="font-size: 9px;" ng-hide="contrbutionPlan">
												<span class="font-para-black"> How much do you
													contribute to the plan in {{date | date:'yyyy'}}  </span> <input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.amountEmpCont" />
											</div>
											</div>
											<br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar()" ng-click="progressBar1()">Next</button>
											</br>
											</br>

										</div>
										<span></span>
										<div class="form-group" ng-show="show==3">
											<span style="color: red; font-size: 90%;">{{msgerr}}</span>
											<span style="color: red; font-size: 90%;">{{msgerrfinal}}</span>
										<div style="font-size: 9px;">
												<span class="font-para-black"> Beginning IRA balance in {{date | date:'yyyy'}} </span> <input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.iraSaved" />
											</div>
											<p>Program calculate contribution automatically</p>

											<div class="radio">
											<label> <input type="radio" name="userIraAmount"
													value="no1" ng-value="true" ng-click="no1()"
													ng-model="formdata.userIraAmount" /> Yes
												</label>
												<label> <input type="radio" name="userIraAmount"
													value="yes1" ng-click="yes1()" ng-model="formdata.userIraAmount">
												 No
												</label> 
											</div>

											<div style="font-size: 9px;" ng-hide="ira1">
												<span class="font-para-black"> How much do you
													contribute to your IRA? </span> <input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.iraContribute " />
											</div>
											<br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar()" ng-click="progressBar2()">Next</button>
											</br>
											</br>

										</div>

										<span></span>
										<div class="form-group" ng-show="show==4">
											<span style="color: red; font-size: 90%;">{{msgerr}}</span>
											<span style="color: red; font-size: 90%;">{{msgerrfinal}}</span>
										
												 	<div style="font-size: 9px;">
												<span class="font-para-black"> Beginning ROTH IRA balance in {{date | date:'yyyy'}} </span> <input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.rothIra" />
											</div> 
											<p>Program calculate contribution automatically</p>

											<div class="radio">
											<label> <input type="radio" name="userRothIraAmount"
													value="no2" ng-value="true" ng-click="no2()"
													ng-model="formdata.userRothIraAmount"> Yes
												</label>
												<label> <input type="radio" name="userRothIraAmount"
													value="yes2" ng-click="yes2()" ng-model="formdata.userRothIraAmount">
													No
												</label> 
											</div>

											<div style="font-size: 9px;" ng-hide="rothira1">
												<span class="font-para-black"> How much do you
													contribute to your Roth IRA? </span> <input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.rothIraContribute" />
											</div>
											<br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar()" ng-click="progressBar3()">Next</button>
											</br>
											</br>

										</div>

										<div class="form-group" ng-show="show==5">
											<span style="color: red; font-size: 90%;">{{msgerr}}</span>
											<p>Does your employer's defined contribution plans offer
												Roth type accounts?</p>

											<div class="radio">
												<label> <input type="radio" name="rothContribution"
													value="yes3" ng-click="yes3()" ng-model="formdata.rothContribution">
													Yes
												</label> <label> <input type="radio" name="rothContribution"
													value="no3" ng-value="true" ng-click="no3()"
													ng-model="formdata.rothContribution"> No
												</label>
											</div>

											<div style="font-size: 9px;" ng-hide="rothplan">
												<span class="font-para-black"> How much have you
													already saved in your employer's Roth account? </span> <input
													type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.rothAccSave" />
											</div>
											<div style="font-size: 9px;" ng-hide="rothplan1">
												<span class="font-para-black"> How much do you
													contribute to your employer-sponsored Roth account? </span> <input
													type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.rothAccContribute" />
											</div>
											<br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar()" ng-click="progressBar4()">Next</button>
											</br>
											</br>

										</div>

										<span></span>
										<div class="form-group" ng-show="show==6">
											<span style="color: red; font-size: 90%;">{{msgerr}}</span>
											<p>Does your spouse have an employer sponsored plan such
												as 401(k), 403(b) or 457(b)?</p>

											<div class="radio">
												<label> <input type="radio" name="spouse401Amount"
													value="yes4" ng-click="yes4()" ng-model="formdata.spouse401Amount">
													Yes
												</label> <label> <input type="radio" name="spouse401Amount"
													value="no4" allow-pattern="\d" maxlength="12"
													ng-value="true" ng-click="no4()" ng-model="formdata.spouse401Amount">
													No
												</label>
											</div>
														<div style="font-size: 9px;" ng-hide="spouseContribution">
												<span class="font-para-black">How much has your
													spouse already saved in your employer's plan in {{date | date:'yyyy'}} </span> <input
													type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.spouseAmount" />
											</div>
                                             <div style="font-size: 9px;" ng-hide="spouseContribution">
												<span class="font-para-black"> Specify the cap for spouse contribution </span>
													 <select
														style="width: 131px; height: 30px; font-size: 18px;"
														name="Retirementage" ng-model="formdata.spouseCapAmount"
														class="font-dropdown">
														<option ng-selected="spouseCap == formdata.spouseCapAmount"
															ng-repeat="spouseCap in spouseCap" ng-value="age">{{spouseCap}}</option>
													</select>
											</div>
												<div style="font-size: 9px;" ng-hide="spouseContribution1">
													<span class="font-para-black"> How much does your
														spouse's employer match your spouse's contribution? </span> <select
														style="width: 131px; height: 30px; font-size: 18px;"
														name="Retirementage" ng-model="formdata.amountSCost"
														class="font-dropdown">
														<option ng-selected="scopei == formdata.amountSCost "
															ng-repeat="scopei in sitems" ng-value="age">{{scopei}}</option>
													</select>
												</div>
											<div style="font-size: 9px;" ng-hide="spouseNew401Cont">
											<p>Program calculate contribution automatically</p>

											<div class="radio">
											<label> <input type="radio" name="spouseCont401"
													value="yesFuncSpouse" ng-value="true" ng-click="yesFuncSpouse()"
													ng-model="formdata.spouseCont401" /> Yes
												</label>
												<label> <input type="radio" name="spouseCont401"
													value="noFuncSpouse" ng-click="noFuncSpouse()" ng-model="formdata.spouseCont401">
												 No
												</label> 
											</div>
											<div style="font-size: 9px;" ng-hide="spouseContribution2">
												<span class="font-para-black"> How much does your
													spouse contribute to the plan? </span> <input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.spouseCont" />
											</div>
											</div>

											<br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar(); this.disabled=true;"
												ng-click="progressBar5()">Next</button>
											</br>
											</br>

										</div>
										<span></span>
										<div class="form-group" ng-show="show==7">
											<span style="color: red; font-size: 90%;">{{msgerr}}</span>
											<span style="color: red; font-size: 90%;">{{msgerrfinal}}</span>
										
											
											<div style="font-size: 9px;" >
												<span class="font-para-black">Beginning Spouse IRA balance in {{date | date:'yyyy'}}
										 </span> <input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.sIraSaved" />
											</div> 
											<p>Program calculate contribution automatically</p>


											<div class="radio">
											<label> <input  type="radio" name="spouseIraAmount"
													value="no5" ng-value="true" ng-click="no5()"
													ng-model="formdata.spouseIraAmount" onclick="setTimeout(no5, 1);">
													Yes
												</label>
												<label> <input type="radio" name="spouseIraAmount"
													value="yes5" ng-click="yes5()" ng-model="formdata.spouseIraAmount">
													No
												</label> 
											</div>
											<div style="font-size: 9px;" ng-hide="sIra1">
												<span class="font-para-black"> How much your spouse
													contribute to your IRA? </span> <input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.sIraContribute" />
											</div>
											<br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar()" ng-click="progressBar6()">Next</button>
											</br>
											</br>

										</div>


										<span></span>
										<div class="form-group" ng-show="show==8">
											<span style="color: red; font-size: 90%;">{{msgerr}}</span>
											<span style="color: red; font-size: 90%;">{{msgerrfinal}}</span>
										
											
										
											<div style="font-size: 9px;" >
												<span class="font-para-black">Beginning Spouse ROTH IRA balance in {{date | date:'yyyy'}}
											 </span> <input
													type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.sRothIra" />
											</div> 
											<p>Program calculate contribution automatically</p>

											<div class="radio">
											<label> <input type="radio" name="spouseRothAmount"
													value="no6" ng-value="true" ng-click="no6()"
													ng-model="formdata.spouseRothAmount"> Yes
												</label>
												<label> <input type="radio" name="spouseRothAmount"
													value="yes6" ng-click="yes6()" ng-model="formdata.spouseRothAmount">
													No
												</label> 
											</div>

											<div style="font-size: 9px;" ng-hide="srothira1">
												<span class="font-para-black"> How much do you (&
													your spouse) contribute to your spouse's Roth IRA? </span> <input
													type="text" allow-pattern="\d" maxlength="12"
													style="width: 131px; height: 30px; font-size: 18px;"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.sRothIraContribute" />
											</div>
											<br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar()" ng-click="progressBar7()">Next</button>
											</br>
											</br>

										</div>

										<div class="form-group" ng-show="show==9">
											<span style="color: red; font-size: 90%;">{{msgerr}}</span>
											<p>Does your spouse's employer's defined contribution
												plans offer Roth type accounts?</p>

											<div class="radio">
												<label> <input type="radio" name="spouseRothSaved"
													value="yes7" ng-click="yes7()" ng-model="formdata.spouseRothSaved">
													Yes
												</label> <label> <input type="radio" name="spouseRothSaved"
													value="no7" ng-value="true" ng-click="no7()"
													ng-model="formdata.spouseRothSaved"> No
												</label>
											</div>

											<div style="font-size: 9px;" ng-hide="srothplan">
												<span class="font-para-black"> How much has your
													spouse already saved in his/her employer's Roth account? </span> <input
													type="text" allow-pattern="\d" maxlength="12"
													style="width: 131px; height: 30px; font-size: 18px;"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.sRothSave" />
											</div>
											<div style="font-size: 9px;" ng-hide="srothplan1">
												<span class="font-para-black"> How much do you (&
													your spouse) contribute to your spouse's employer-sponsored
													Roth account? </span> <input type="text" allow-pattern="\d"
													maxlength="12"
													style="width: 131px; height: 30px; font-size: 18px;"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.sRothContribute" />
											</div>
											<br>
											<button id="btn1" type="button"
												class="btn btn-primary" onclick="progress_bar()"
												ng-click="progressBar8()" >Next</button>
											</br>
											</br>

										</div>
		
									</div>


								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- /.modal -->
						<div class="right_content">
							</br>
							<div class=" text-center">
								<i class="fa fa-home fa-5x"></i></br>
								</br>
								<p>Based on the submitted details the user SSB at the age
									{{formdata.Retirementage}} is as follows.</p>
								<p>$ {{RetirementData.userExpectedSSB}}</p>
								<table class="table table-striped" ng-show="show>2">
									<thead>
										<tr>
											<th style="text-align: center;">AGE</th>
											<th style="text-align: center;">USER SSB</th>

											<th style="text-align: center;"><div
													ng-hide="!SpouceDetail">SPOUSE SSB</div></th>

										</tr>
									</thead>
									<tbody>
										<tr>
											<td>62</td>
											<td>$ {{RetirementData.User62}}</td>
											<td><div ng-hide="!SpouceDetail">$</div>
												{{RetirementData.Spouse62}}</td>

										</tr>
										<tr>
											<td>63</td>
											<td>$ {{RetirementData.User63}}</td>
											<td><div ng-hide="!SpouceDetail">$</div>
												{{RetirementData.Spouse63}}</td>
										</tr>
										<tr>
											<td>64</td>
											<td>$ {{RetirementData.User64}}</td>
											<td><div ng-hide="!SpouceDetail">$</div>
												{{RetirementData.Spouse64}}</td>
										</tr>
										<tr>
											<td>65</td>
											<td>$ {{RetirementData.User65}}</td>
											<td><div ng-hide="!SpouceDetail">$</div>
												{{RetirementData.Spouse65}}</td>
										</tr>
										<tr>
											<td>66</td>
											<td>$ {{RetirementData.User66}}</td>
											<td><div ng-hide="!SpouceDetail">$</div>
												{{RetirementData.Spouse66}}</td>
										</tr>

										<tr>
											<td>67</td>
											<td>$ {{RetirementData.User67}}</td>
											<td><div ng-hide="!SpouceDetail">$</div>
												{{RetirementData.Spouse67}}</td>
										</tr>
										<tr>
											<td>68</td>
											<td>$ {{RetirementData.User68}}</td>
											<td><div ng-hide="!SpouceDetail">$</div>
												{{RetirementData.Spouse68}}</td>
										</tr>
										<tr>
											<td>69</td>
											<td>$ {{RetirementData.User69}}</td>
											<td><div ng-hide="!SpouceDetail">$</div>
												{{RetirementData.Spouse69}}</td>
										</tr>
										<tr>
											<td>70</td>
											<td>$ {{RetirementData.User70}}</td>
											<td><div ng-hide="!SpouceDetail">$</div>
												{{RetirementData.Spouse70}}</td>
										</tr>
									</tbody>

								</table>




							</div>
						</div>

					</div>

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
			<div id="myModal" class="modal fade">
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
								data-dismiss="modal" onclick="window.location.reload()">Close</button>
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
		</div>


	</section>
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
	<script type="text/javascript">
function numbersonly(e){
    var unicode=e.charCode? e.charCode : e.keyCode
    if (unicode!=8){ //if the key isn't the backspace key (which we should allow)
        if (unicode<48||unicode>57) //if not a number
            return false //disable key press
    }
}
</script>

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
	<script src="assets/js/fusioncharts.theme.fint.js"></script>
	<script src="assets/js/fusioncharts.powercharts.js"></script>
	<script src="assets/js/fusioncharts.js"></script>
</body>
</html>