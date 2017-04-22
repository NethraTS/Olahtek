
<!DOCTYPE html>
<html lang="en" ng-app="dashboard">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Balagopalan">
<title>WEALTHSETTER</title>
<link rel="stylesheet" href="css/d3.slider.css" />
<!--     <script type="text/javascript" src="js/d3.slider.js"></script>
 -->
<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<!-- Custom Fonts -->
<link rel="stylesheet" href="css/fonts.googleapis.css" type="text/css">
<link rel="stylesheet" href="css/fonts.googleapis1.css" type="text/css">
<!--    this is used for load graphs from google 
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
	-->
<script src="js/jquery.min.js"></script>
<!-- LOAD ANGULAR -->
<script src="js/angular.min.js"></script>
<script src="js/app.js"></script>
<script src="js/dashboard.js"></script>
<link rel="stylesheet" href="css/nv.d3-1.8.3.min.css" />
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
.dropdown-submenu {
	position: relative;
}

.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: -6px;
	margin-left: -1px;
	-webkit-border-radius: 0 6px 6px 6px;
	-moz-border-radius: 0 6px 6px 6px;
	border-radius: 0 6px 6px 6px;
}

.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #cccccc;
	margin-top: 5px;
	margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
	border-left-color: #555;
}

.dropdown-submenu.pull-left {
	float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
	left: -100%;
	margin-left: 10px;
	-webkit-border-radius: 6px 0 6px 6px;
	-moz-border-radius: 6px 0 6px 6px;
	border-radius: 6px 0 6px 6px;
}

.loading {
	display: block;
	position: absolute;
	margin-left: 53%;
	margin-top: 12%;
	z-index: 100000000001;
	color: #2ECCFA;
}

.nvd3-svg {
	padding-left: 45px;
}

.dollar-field2::before {
	content: "$";
	position: absolute;
	font-size: 15px;
	display: block;
	right: 8%;
	bottom: 41%;
	/* padding: 3.4%; */
}

.dollar-field3::before {
	content: "$";
	position: absolute;
	font-size: 15px;
	display: block;
	right: 5%;
	bottom: 17%;
	/* padding: 3.4%; */
}

.dollar-field::before {
	content: "$";
	position: absolute;
	font-size: 15px;
	display: block;
	right: 11%;
	bottom: 62%;
	/* padding: 3.4%; */
}

.dollar-field1::before {
	content: "$";
	position: absolute;
	font-size: 15px;
	display: block;
	right: 6%;
	bottom: 27%;
	/* padding: 3.4%; */
}

#slider3textmin, #slider3textmax {
	display: none;
}

rect {
	fill: #fff;
}

.axis {
	font-family: inherit;
	font-size: 10px;
}

.legend {
	text-align: center;
	font-size: 10px;
	font-weight: bold;
	cursor: pointer;
}

circle, .line {
	fill: none;
	stroke: #328de4; /* blue for userIncome*/
	stroke-width: 4px;
}

circle {
	fill: white;
	fill-opacity: 1;
	stroke-width: 1px;
	cursor: move;
}

circle1, .line1 {
	fill: none;
	stroke: #e43234; /* red for expense */
	stroke-width: 4px;
}

circle1 {
	stroke: #e43234;
	stroke-width: 1px;
	fill: white;
	fill-opacity: 1;
	cursor: move;
}

.lineTax {
	fill: none;
	stroke: #ff7f0e; /* Orange for tax */
	stroke-width: 4px;
}

circle2, .line2 {
	fill: none;
	stroke: #32e2e2; /* steel blue for spouseIncome */
	stroke-width: 4px;
}

circle2 {
	stroke: #32e2e2;
	stroke-width: 1px;
	fill: white;
	fill-opacity: 1;
	cursor: move;
}

circle3, .line3 {
	fill: none;
	stroke: #b0c10c; /* green for combinedIncome */
	stroke-width: 4px;
}

circle3 {
	stroke: #b0c10c;
	stroke-width: 1px;
	fill: white;
	fill-opacity: 1;
	cursor: move;
}

/*           span
{
  display: none;
  }  */
div.tooltip {
	position: absolute;
	text-align: center;
	width: auto;
	height: auto;
	padding: 2px;
	font: 12px sans-serif;
	background: white;
	border: 1px solid black;
	color: black;
	/*  border-radius: 8px; */
	pointer-events: none;
}

@media ( min-width : 768px) {
	ul.nav li:hover>ul.dropdown-menu {
		display: block;
	}
	#navbar {
		text-align: center;
	}
}

.addGoals2 {
	padding: 4%;
	border: 1px solid #ebebeb;
	display: inline-block;
	width: 18%;
	height: 1px;
	vertical-align: top;
	background-color: rgb(46, 204, 250);;
}
/* .nvd3-svg{
padding-left:50px} */
nvd3.vertical-line {
	width: 1px !important;
	background-color: black !important;
	height: 100% !important;
	float: left !important;
}
</style>
<script>
	
</script>
<style>
.sel {
	color: green;
}

.MaskLayer {
	position: fixed;
	top: 0;
	left: 0;
	z-index: 10000;
	width: 100%;
	height: 100%;
	background: rgba(70, 70, 74, 0.5);
	-webkit-transition: opacity linear 0.25s;
	transition: opacity linear 0.25s;
	pointer-events: auto;
}

.MaskLayer.isClosed {
	opacity: 0;
	pointer-events: none;
}

.MaskLayer-Content {
	position: absolute;
	top: 50%;
	left: 50%;
	padding: 10px;
	margin: -55px 0 0 -55px;
	height: 100px;
	width: 100px;
	text-align: center
}
</style>
</head>
<body id="page-top" ng-controller="dashboardController"
	ng-init="load();" ng-cloak>

	<div class="MaskLayer" ng-class="{isClosed : !masked}">

		<div class="MaskLayer-Content">
			<i class="fa fa-refresh fa-spin fa-3x"
				style="position: absolute; margin-left: 18%; margin-top: 50%; color: white; z-index: 1;"></i>
		</div>
	</div>
	<div class="MaskLayer" ng-class="{isClosed : !maskedPlan}">

		<div class="MaskLayer-Content">
			<i class="fa fa-refresh fa-spin fa-3x"
				style="position: absolute; margin-left: 18%; margin-top: 50%; color: white; z-index: 1;"></i>
		</div>
	</div>

	<div class="MaskLayer" ng-class="{isClosed : !maskedPlotChart}">

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

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Case Studies <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="CaseStudy1.jsp">Case Study 1</a></li>
							<li><a href="CaseStudy2.jsp">Case Study 2</a></li>
							<li><a href="CaseStudy3.jsp">Case Study 3</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Resources <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="mortgageCalculator.jsp">Mortgage
									Calculator</a></li>
							<!-- data-toggle="modal" data-target="#caseStudy" -->
							<li class="dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Car
									Calculator</a>
								<ul class="dropdown-menu">
									<li><a href="carLoanCalculator.jsp">Car Loan
											Calculator</a></li>
									<li><a href="carLeaseCalculator.jsp">Car Lease
											Calculator</a></li>
								</ul></li>
							<li><a href="ssbCalculator.jsp">Social Security Benefits</a></li>
							<li><a href="FderalincomeTaxCalculator.jsp">Income Tax
									Calculator</a></li>
						</ul></li>
					<li>
					<li>
						<!--   <a class="page-scroll" href="Report.jsp">Reports</a> --> <a
						href="#" ng-click="report()">Reports</a>
					</li>

					<li><a class="page-scroll" href="#" ng-click="checkSave()"><i
							class="fa fa-user-plus"></i> My Profile</a></li>

					<li><a href=# class="page-scroll"
						ng-click="deleteAllCookies()"><i class="fa fa-sign-out"></i>
							Logout</a></li>


					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<section class="bg-primary" id="about">
		<div class="container">
			<div class="row">
				<loading></loading>

				<div style="display: none" class="alert alert-warning text-center"
					id="warning-alert">
					<button type="button" class="close" ng-click="hideWarning()">x</button>
					<strong>Warning! </strong> {{SuccessMessage}}
				</div>
				<div style="display: none" class="alert alert-warning text-center"
					id="warning-alert2">
					<button type="button" class="close" ng-click="hideWarning()">x</button>
					<strong>Note: </strong> {{SuccessMessage_marriage}}
				</div>
				<div style="display: none" class="alert alert-success text-center"
					id="success-alertExp">
					<button type="button" class="close" ng-click="hideSuccess()">x</button>
					<strong>Success! </strong> {{messageUpdateExpenseAlert}}
				</div>
				<div style="display: none" class="alert alert-success text-center"
					id="success-alert">
					<button type="button" class="close" ng-click="hideSuccess()">x</button>
					<strong>Success! </strong> {{SuccessMessage}}
				</div>
				<!-- <div style="display: none" class="alert alert-warning text-center"
					id="expense-alert">
					<button type="button" class="close" ng-click="hideWarning()">x</button>
					<strong>Warning! </strong>{{messageUpdateExpenseAlert}}
				</div> -->
				<div style="display: none" class="alert alert-warning text-center"
					id="report-alert">
					<button type="button" class="close" ng-click="hideWarning()">x</button>
					<strong>Warning! </strong>{{SuccessMessage1}}
				</div>
				<div class="title-createplan">
					{{userName}}<a class="pull-right" style="font-size: 20px;" href="#"
						data-toggle="modal" data-target="#PortfolioDetails"
						ng-click="refreshPortfolio()">Investment Portfolio</a>
				</div>
				<div class="content-panel">
					<div class="side-panel" style="padding-bottom: 73.6%;">
						<div class="sidebar-collapse">
							<ul class="nav" id="main-menu">


								<li ng-click="planchange(planName.name,$index);clearGraph()"
									ng-repeat="planName in planNames1"
									ng-class="{'active-menu' : $index == selected}"><a
									style="cursor: pointer; cursor: hand;"> <!-- <input type="text" > -->
										</i>{{planName.name}}
								</a></li>

								<!--     <li>
                        <a href=#  onclick="return showModal()"></i>Create Plan</a>
                                              
                    </li> -->


								<li ng-hide="!createPlan">
									<div class="text-center">
										<br>
										<button type="button" class="btn btn-primary"
											ng-click="showEdit()">Create Plan</button>
										<br> <br>
										<div ng-hide="!planCountOnload">
											<button type="button" class="btn btn-primary"
												ng-click="renamePlan()">Rename Plan</button>
											</br> </br> </br>
										</div>
									</div>
								</li>


								<li ng-hide="!onClickCreatePlan">

									<div class="form-group floating-label-form-group controls">
										<span style="color: red; font-size: 85%;">{{emptyMassage}}</span>
										<input type="text" autocomplete="off"
											style="font-size: 15px; background-color: #FFFFFF; padding: 5%; margin-left: 2%; margin-top: 2%; width: 95%;"
											placeholder="Name your financial plan" class="form-control"
											data-toggle="tooltip" title="Name your financial plan here"
											onkeypress="return onlyAlphaNumbers(event,this)"
											maxlength="15" ng-model="formdata.planname">
										<div ng-hide="!hideCheckBox">



											<br> <input type="checkbox" ng-change="showPlanList()"
												ng-model="formdata.checkValue" />Copy an existing Plan


										</div>

										<div ng-hide="!onCheckedCopyPlans">

											<div ng-hide="!hideList">
												<select class="form-control" id="sel1" ng-model="plan">
													<option ng-selected="planName.name==plan"
														ng-repeat="planName in planNames1">{{planName.name}}</option>
												</select>
											</div>
											<br>

											<div ng-hide="!CopyPlanbutton" class="text-center">
												<button type="button" class="btn btn-primary"
													ng-click="copyPlan()">copy</button>
												<button type="button" class="btn btn-primary"
													ng-click="cancle()">Cancel</button>
											</div>
										</div>
									</div> <br>
									<div ng-hide="!CreatePlanbutton" class="text-center">
										<button class="btn btn-primary" ng-click="modalform()">Create</button>
										<button type="button" class="btn btn-primary"
											ng-click="cancle()">Cancel</button>
									</div>
								</li>



								<li ng-hide="!onCheckedRenamePlans">

									<div class="form-group floating-label-form-group controls">
										<span style="color: red; font-size: 85%;">{{emptyMassage}}</span>
										<input type="text" autocomplete="off"
											style="font-style: italic; background-color: #FFFFFF; padding: 5%; margin-left: 2%; margin-top: 2%; margin-bottom: 2%; width: 95%;"
											placeholder="Name your financial plan" class="form-control"
											data-toggle="tooltip" title="Name your financial plan here"
											onkeypress="return onlyAlphaNumbers(event,this)"
											maxlength="15" ng-model="formdata.planname"> <select
											class="form-control" id="sel1" ng-model="plan">
											<option ng-selected="planName.name==plan"
												ng-repeat="planName in planNames1">{{planName.name}}</option>
										</select> <br>
										<div class="text-center">
											<button type="button" class="btn btn-primary"
												ng-click="RenamePlanName()">Rename</button>
											<button type="button" class="btn btn-primary"
												ng-click="cancle()">cancel</button>
										</div>
									</div> <br>
								</li>



							</ul>
						</div>
					</div>

					<div class="center-content">






						<div>
							<!-- class="contentcenter-data" -->



							<br></br>

							<div class="col-xs-6 col-sm-8 col-md-12">
								<!-- {{income}} -->
								{{messagehome}}

								<div ng-hide="!notNewUser">
									<div ng-hide="!planCountOnload">
										<button type="button" class="btn btn-primary pull-left"
											ng-click="deletePlan()">Delete Plan</button>

										<button type="button" class="btn btn-primary pull-right"
											ng-click="goSelectGoals()">Add Goals</button>
										&nbsp

										<!-- <label >Select Project Year :</label> -->

									</div>
								</div>

								<br></br>

								<div class="col-md-12">
									<div ng-hide="!notNewUser">
										<div class="form-group">
											<div ng-hide="!noGoals">
												<span ng-repeat=" goals1 in goals1"> <span
													style="cursor: pointer; cursor: hand;" class="addGoals2"
													ng-click="changeGoals(goals1.id,goals1.name);goEditGoals()"><i
														class="fa fa-photo text-center"
														style="position: relative; left: -2px; top: -23px; white-space: normal; text-align: centre; word-break: break-word;">{{goals1.name}}</i>
												</span>

												</span>
												<!--  <label for="sel1">Select Goal:</label>
  <select  class="form-control" id="sel1" ng-model="firstgoal" ng-change="changeGoals(firstgoal)" >
   <option  ng-selected="goals1.name==firstgoal" ng-repeat=" goals1 in goals1" ng-value="goals1.name"  >{{goals1.name}}</option>
    
  </select> -->

											</div>
										</div>


									</div>

								</div>


								<div class="modal fade" id="myModal2" role="dialog">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Message</h4>
											</div>
											<div class="modal-body">
												<p>Are you sure you want to delete the goal</p>
											</div>
											<div class="modal-footer">
												<button type="button" id="dialog-ok"
													ng-click="goDeleteGoals()"
													class="btn btn-primary pull-right" data-dismiss="modal">
													<i class="fa fa-trash-o"></i>Delete
												</button>

												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
											</div>
										</div>

									</div>
								</div>
								<!-- model for finplan------------ -->
								<div class="modal fade" id="myModalFin" role="dialog">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Message</h4>
											</div>
											<div class="modal-body">
												<p>Are you sure you want to delete the plan?</p>
											</div>
											<div class="modal-footer">
												<button type="button" id="dialog-ok"
													ng-click="goDeletePlan()"
													class="btn btn-primary pull-right" data-dismiss="modal">
													<i class="fa fa-trash-o"></i>Delete
												</button>

												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
											</div>
										</div>

									</div>
								</div>
								<div class="modal fade" id="retirmentModal" role="dialog">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Assets are going negative!!</h4>
											</div>
											<div class="modal-body">
												<p>The plan cannot be created since you are using the
													same income profile having the retirement goal to create
													the new financial plan. Please delete the retirement goal
													and continue creating the new plan</p>
											</div>
											<div class="modal-footer">
												<button type="button" id="dialog-ok"
													href="window.location.reload()"
													class="btn btn-primary pull-right" data-dismiss="modal">
													Close</button>


											</div>
										</div>

									</div>
								</div>
								<!-- ----------------------------- -->
								<div id="chartDetails" class="modal fade" role="dialog">
									<div class="modal-dialog modal-chartDetails">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h4 class="modal-title">Income-Expense Details (Yearly)</h4>
											</div>
											<div class="modal-body">

												<table class="table table-bordered">
													<thead>
														<tr>
															<th>User Age</th>
															<th>Year</th>
															<th>Income</th>
															<th ng-hide="!nospouse">Spouse Income</th>
															<th>Expense</th>
															<th>FDI</th>
															<th>User Fica SST</th>
															<th ng-hide="!nospouse">Spouse Fica SST</th>
															<th>Fica Medicare</th>
															<th>State Tax</th>
															<th>Saving</th>
															<th>Taxable Investment Amount</th>
															<th>Tax-Advantaged Investments</th>

														</tr>
													</thead>
													<tbody>
														<tr ng-repeat="value1 in tableIncome  |  filter :limiting">
															<th scope="row">{{Math.round(value1.id)}}</th>
															<td>{{Math.round(value1.year)}}</td>
															<td>{{Math.round(value1.value)}}</td>
															<td ng-hide="!nospouse">{{Math.ceil(value1.spouseValue)}}</td>
															<td>{{Math.ceil(value1.expense)}}</td>
															<td>{{Math.round(value1.federalTax)}}</td>
															<td>{{Math.round(value1.userfICASocialSecurityTax)}}</td>
															<td ng-hide="!nospouse">{{Math.round(value1.spousefICASocialSecurityTax)}}</td>
															<td>{{Math.round(value1.fICAMedicareTax)}}</td>
															<td>{{Math.round(value1.stateTax)}}</td>
															<td>{{Math.round(value1.savings)}}</td>
															<td>{{Math.round(value1.taxable_investment_amount)}}</td>
															<td>{{Math.round(value1.nontaxable_investment_amount)}}</td>
														</tr>


													</tbody>
												</table>





											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
												<!--  <button type="button"  ng-click="checkCheckbox()" class="btn btn-default" >Submit</button> -->
											</div>
										</div>
									</div>
								</div>



								<div id="incomeProfileDetails" class="modal fade" role="dialog">
									<div class="modal-dialog modal-chartDetails">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h4 class="modal-title">Income-Profile Details (Yearly)</h4>
											</div>
											<div class="modal-body">

												<table class="table table-bordered">
													<thead>
														<tr>
															<th>User Age</th>
															<th>Year</th>
															<th>Income</th>
															<th ng-hide="!maritalFlag_imp">Spouse Income</th>
															<th>Expense</th>
															<th>FDI</th>
															<th>User Fica SST</th>
															<th ng-hide="!maritalFlag_imp">Spouse Fica SST</th>
															<th>Fica Medicare</th>
															<th>State Tax</th>
															<th>Saving</th>
															<th>Taxable Investment Amount</th>
															<th>Tax-Advantaged Investments</th>

														</tr>
													</thead>
													<tbody>
														<tr
															ng-repeat="value1 in tableIncome_imp   |  filter :limiting">
															<th scope="row">{{Math.round(value1.id)}}</th>
															<td>{{Math.round(value1.year)}}</td>
															<td>{{Math.round(value1.value)}}</td>
															<td ng-hide="!maritalFlag_imp">{{Math.ceil(value1.spouseValue)}}</td>
															<td>{{Math.ceil(value1.expense)}}</td>
															<td>{{Math.round(value1.federalTax)}}</td>
															<td>{{Math.round(value1.userfICASocialSecurityTax)}}</td>
															<td ng-hide="!maritalFlag_imp">{{Math.round(value1.spousefICASocialSecurityTax)}}</td>
															<td>{{Math.round(value1.fICAMedicareTax)}}</td>
															<td>{{Math.round(value1.stateTax)}}</td>
															<td>{{Math.round(value1.savings)}}</td>
															<td>{{Math.round(value1.taxable_investment_amount)}}</td>
															<td>{{Math.round(value1.nontaxable_investment_amount)}}</td>
														</tr>


													</tbody>
												</table>





											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
												<!--  <button type="button"  ng-click="checkCheckbox()" class="btn btn-default" >Submit</button> -->
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
												<div ng-hide="!afterSubmit">
													<div
														style="background-image: url(http://cssslider.com/sliders/demo-10/data1/images/3.jpg); background-size: cover; height: 100%;"
														class="bg-primary" ng-show="show==0">
														<!-- 														<div class="container">
															<div class="row">
																<div class="col-lg-10 col-lg-offset-1 text-center">
																	<br></br>
																	<br></br>
																	<h1 style="color: white; font-size: 65px;">Investment Portfolio</h1>
																	<br></br>

																	<p style="color: white;">In our planning process,
																		we try to create customized portfolio that can match
																		your risk appetite. We would like to ask you a few
																		questions to have a better understand of your view of
																		market risk. At the end of the questionnaire, we will
																		give you our assessment of your risk profile. You can
																		modify it if you prefer. However, keep in mind that,
																		we are structuring our portfolio for long term
																		planning purpose with the emphasis on low cost and
																		simplicity, so even our most aggressive risk profile
																		is not suitable for speculative trading or
																		market-timing.</p>

																	<br></br>
																	<br></br> <a ng-click="next()"
																		style="background-color: #E65319;"
																		class="btn btn-primary btn-xl page-scroll pull-right">Next</a>

																</div>

															</div>
														</div> -->
														<br></br> <br></br>
													</div>
													<div class="bg-primary">
														<div class="container">
															<div class="row">
																<div class="col-lg-10 col-lg-offset-1">
																	<span class="font-para-black" ng-show="show==1">
																		<br></br> Do you feel comfortable reviewing the
																		performance of a portfolio of stocks and bonds?
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="firstQuestion" ng-value="6"
																				style="margin-top: 1%;">Very comfortable</label>
																		</div>
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="firstQuestion" ng-value="5"
																				style="margin-top: 1%;">Somewhat comfortable</label>
																		</div>
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="firstQuestion" ng-value="4"
																				style="margin-top: 1%;">Not comfortable at
																				all</label>
																		</div> <a style="background-color: #E65319;"
																		ng-click="next()"
																		class="btn btn-primary btn-xl page-scroll pull-right">Next</a>
																		<br></br>
																	</span> <span class="font-para-black" ng-show="show==2">
																		<br></br> In reviewing my portfolio, I would
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="secondQuestion" ng-value="2"
																				style="margin-top: 1%;">focus on investments
																				that have lost money.</label>
																		</div>
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="secondQuestion" ng-value="6"
																				style="margin-top: 1%;">equally focus on
																				investment that have lost or gained money.</label>
																		</div>
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="secondQuestion" ng-value="4"
																				style="margin-top: 1%;">focus on investments
																				that have gained money.</label>
																		</div> <br> <a style="background-color: #E65319;"
																		ng-click="next()"
																		class="btn btn-primary btn-xl page-scroll pull-right">Next</a>
																		<a style="background-color: #E65319;"
																		ng-click="back()"
																		class="btn btn-primary btn-xl page-scroll pull-left">Back</a>
																		<br></br>
																	</span> <span class="font-para-black" ng-show="show==3">
																		<br></br> In our recommended portfolio, we utilize
																		exchange traded funds (ETFs) exclusively because of
																		their market coverage, low cost, and easy to trade.
																		How familiar are you with ETFs? (you can find out more
																		information following this link)
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="thirdQuestion" ng-value="6"
																				style="margin-top: 1%;">Very familiar (I
																				know more than a half dozen popular ETFs)</label>
																		</div>
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="thirdQuestion" ng-value="5"
																				style="margin-top: 1%;">Familiar (Though I
																				have not traded, or know many ETFs, I know what they
																				and know who are the sponsors of some of the most
																				popular ETFs)</label>
																		</div>
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="thirdQuestion" ng-value="4"
																				style="margin-top: 1%;">Not very familiar (I
																				know what is an ETF, but that's pretty much the
																				limit of my knowledge.)</label>
																		</div>
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="thirdQuestion" ng-value="3"
																				style="margin-top: 1%;">Unfamiliar (What is
																				ETF?)</label>
																		</div> <br> <a style="background-color: #E65319;"
																		ng-click="next()"
																		class="btn btn-primary btn-xl page-scroll pull-right">Next</a>
																		<a style="background-color: #E65319;"
																		ng-click="back()"
																		class="btn btn-primary btn-xl page-scroll pull-left">Back</a>
																		<br></br>
																	</span> <span class="font-para-black" ng-show="show==4">
																		<br></br> Market is often volatile. If your
																		investments loss 20% of its value in a month, what
																		would you do?
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="fourthQuestion" ng-value="1"
																				style="margin-top: 1%;">sell all and keep
																				everything in cash to be safe</label>
																		</div>
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="fourthQuestion" ng-value="2"
																				style="margin-top: 1%;">sell some to reduce
																				the risk</label>
																		</div>
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="fourthQuestion" ng-value="5"
																				style="margin-top: 1%;">do nothing, just
																				stick to the investment plan</label>
																		</div>
																		<div class="radio">
																			<label style="font-size: 18px;"><input
																				type="radio" ng-model="fourthQuestion" ng-value="8"
																				style="margin-top: 1%;">buy more to take
																				advantage of the low prices</label>
																		</div> <br> <a style="background-color: #E65319;"
																		ng-click="submitPortfolio1(1)"
																		class="btn btn-primary btn-xl page-scroll pull-right">Submit</a>
																		<a style="background-color: #E65319;"
																		ng-click="back()"
																		class="btn btn-primary btn-xl page-scroll pull-left">Back</a>
																		<br></br>
																	</span>



																</div>
															</div>
														</div>
													</div>
												</div>

												<div ng-hide="!beforeSubmit">
													<div class="bg-primary" id="about">
														<div class="container">
															<div class="row">
																<div class="col-xs-6 col-sm-8 col-md-12 text-center">
																	<div class="container-fluid">
																		<table border="0" width="100%">
																			<tr>
																				<td class="left" style="padding-bottom: 2%">Rsik
																					Score:</td>
																				<td class="right"><select class="form-control"
																					ng-model="riskScorePort"
																					onkeypress="return onlyNumbers(event,this)"
																					typeahead="submitPortfolio1(0)"
																					ng-change="submitPortfolio1(0)">
																						<option ng-selected=" fo1==riskScorePort "
																							ng-repeat="fo1 in filingOptions1"
																							ng-value="fo1.id">{{fo1.id}}</option>
																				</select></td>
																				<td class="left" style="padding-bottom: 2%">Risk
																					Factor:</td>
																				<td class="right"><select
																					ng-model="filingStatusPort" class="form-control"
																					ng-change="submitPortfolio1(0)">
																						<option ng-selected=" fo==filingStatusPort"
																							ng-repeat="fo in filingOptions" ng-value="fo.id">{{fo.id}}</option>

																				</select></td>

																				<td class="left" style="padding-bottom: 2%">Age:</td>
																				<td><select ng-model="agePort"
																					class="form-control"
																					ng-change="submitPortfolio1(0)">
																						<option ng-selected="fo == agePort"
																							ng-repeat="fo in ages" ng-value="fo.number">{{fo.number}}</option>

																				</select></td>
																		</table>
																		<br>
																		<table style="background-color: white;"
																			ng-hide="!growthTable" class="table table-bordered">
																			<thead>
																				<tr>

																					<th>Ticker</th>
																					<th>name</th>
																					<th>Growth Rates</th>
																					<th>Interest/Dividend Yields</th>


																					</thread>
																				</tr>
																			<tbody>
																				<!-- <tr ng-repeat="value1 in Porfolio_tableIncome "> -->
																				<!-- <th scope="row" >helo</th> -->
																				<tr>
																					<td>VTI</td>
																					<td>Vanguard Total Stock Market ETF</td>
																					<td>{{vtiGrowthRate}}%</td>
																					<td>{{vtiD}}%</td>

																				</tr>
																				<tr>
																					<td>VTV</td>
																					<td>Vanguard FTSE Emerging Markets ETF</td>
																					<td>{{vtvDGrowthRate}}%</td>
																					<td>{{vtvd}}%</td>

																				</tr>
																				<tr>
																					<td>VOE</td>
																					<td>iShares Core US Aggregate Bond</td>
																					<td>{{voeGrowthRate}}%</td>
																					<td>{{voeD}}%</td>

																				</tr>
																				<tr>
																					<td>VBR</td>
																					<td>Vanguard Total International Bond ETF</td>
																					<td>{{vbrDGrowthRate}}%</td>
																					<td>{{vbrd}}%</td>

																				</tr>
																				<tr>
																					<td>VEA</td>
																					<td>Vanguard S&P 500 ETF</td>
																					<td>{{veaDGrowthRate}}%</td>
																					<td>{{veaD}}%</td>

																				</tr>
																				<tr>
																					<td>VWO</td>
																					<td>Vanguard Small-Cap ETF</td>
																					<td>{{vwoDGrowthRate}}%</td>
																					<td>{{vwod}}%</td>

																				</tr>
																				<tr>
																					<td></td>
																					<td>(note: Rates and yields based on 10-year
																						returns)</td>
																					<td></td>
																					<td></td>

																				</tr>
																			</tbody>
																		</table>
																		<a></a>

																		<div class="row">




																			<div class="col-xs-6 col-md-4">
																				<div ng-hide="!Original1" class="ng-binding">
																					<label>Growth Rate :{{growthRate}}</label> &nbsp;
																					<span class="glyphicon glyphicon-pencil icon8"
																						id="test" style="cursor: pointer; color: black;"
																						ng-click="Original1=false;Edit1=true;disabled8=false;myStyle4={'color':'#2ECCFA'}"
																						aria-hidden="true" ng-style="myStyle4"></span>
																				</div>
																				<div ng-hide="!Edit1" class="ng-hide">
																					<label>Growth Rate :</label> <input type="number"
																						maxlength="15"
																						class="input_bl-long  ng-pristine ng-valid"
																						ng-model="growthRate" allow-pattern="\d"
																						ng-click="Original1=false;Edit1=true;disabled8=false;myStyle4={'color':'#2ECCFA'}"
																						ng-disabled="disabled8" id="test1"
																						disabled="disabled">%
																				</div>
																			</div>


																			<div class="col-xs-6 col-md-4">
																				<div ng-hide="!Original2" class="ng-binding">
																					<label>Dividend Yields
																						:{{portfolioDividend}}</label> &nbsp; <span
																						class="glyphicon glyphicon-pencil icon8" id="test"
																						style="cursor: pointer; color: black;"
																						ng-click="Original2=false;Edit2=true;disabled8=false;myStyle4={'color':'#2ECCFA'}"
																						aria-hidden="true" ng-style="myStyle4"></span>
																				</div>
																				<div ng-hide="!Edit2" class="ng-hide">
																					<label>Dividend Yields :</label> <input
																						type="number" maxlength="15"
																						class="input_bl-long  ng-pristine ng-valid"
																						ng-model="portfolioDividend" allow-pattern="\d"
																						ng-click="Original2=false;Edit2=true;disabled8=false;myStyle4={'color':'#2ECCFA'}"
																						ng-disabled="disabled8" id="test1"
																						disabled="disabled">%
																				</div>
																			</div>

																			<div class="col-xs-6 col-md-4">
																				<div ng-hide="!Original3" class="ng-binding">
																					<label>PortfolioInterest:
																						{{portfolioInterest}}</label> &nbsp; <span
																						class="glyphicon glyphicon-pencil icon8" id="test"
																						style="cursor: pointer; color: black;"
																						ng-click="Original3=false;Edit3=true;disabled8=false;myStyle4={'color':'#2ECCFA'}"
																						aria-hidden="true" ng-style="myStyle4"></span>
																				</div>
																				<div ng-hide="!Edit3" class="ng-hide">
																					<label>Dividend Yields :</label> <input
																						type="number" maxlength="15"
																						class="input_bl-long  ng-pristine ng-valid"
																						ng-model="portfolioInterest" allow-pattern="\d"
																						ng-click="Original3=false;Edit3=true;disabled8=false;myStyle4={'color':'#2ECCFA'}"
																						ng-disabled="disabled8" id="test1"
																						disabled="disabled">%
																				</div>

																			</div>

																		</div>




																		<div>
																			<br></br>
																			<div style="margin-top: 2%"
																				class="btn btn-primary btn-xl page-scroll pull-right">
																				<button type="submit" class="btn btn-primary "
																					ng-click="restore1()
									">Reset</button>

																			</div>
																			<span style="color: red; font-size: 85%;">{{investMessage}}</span>

																			<a style="background-color: #E65319;"
																				class="btn btn-primary btn-xl page-scroll pull-left"
																				ng-click="submitPortfolio2()">Calculate</a>

																		</div>

																	</div>

																</div>

															</div>
														</div>
													</div>
												</div>


												<div ng-hide="!beforeSubmit">
													<div class="bg-primary" id="about">
														<div class="container">
															<div class="row">
																<div class="col-xs-6 col-sm-8 col-md-12 text-center">
																	<br></br>
																	<!-- <div ng-hide="!growthChart">
																		<a href="#" ng-click="showtable()">Details </a>
																	</div>
																	<div ng-hide="!growthTable">
																		<a href="#" ng-click="showChart()">Chart </a>
																	</div> -->


																	<div ng-hide="!growthChart" id="chart-container3">




																		Income-Expense graph will render here</div>

																	<br>

																</div>



																<table style="background-color: white;"
																	ng-show="growthTable1" class="table table-bordered">
																	<thead>
																		<tr>
																			<!-- 			<th>User Age</th> -->
																			<th>Year</th>
																			<th>Income</th>
																			<th ng-hide="!maritalFlag_imp1">Spouse Income</th>
																			<th>Expense</th>
																			<th>FDI</th>
																			<th>User Fica SST</th>
																			<th ng-hide="!maritalFlag_imp1">Spouse Fica SST</th>
																			<th>Fica Medicare</th>
																			<th>State Tax</th>
																			<th>Saving</th>
																			<th>Ending Taxable Investment Amount</th>
																			<th>Taxable Investment Income</th>
																			<th>Ending Tax-Advantaged Investments</th>
																			<th>Tax-Advantaged Investments</th>

																		</tr>
																	</thead>
																	<tbody>
																		<tr ng-repeat="value1 in Porfolio_tableIncome ">
																			<!-- <th scope="row" >helo</th> -->


																			<th scope="row">{{value1.year}}</th>
																			<td>{{Math.round(value1.value)}}</td>
																			<!-- <td>{{value1.year}}</td> -->

																			<td ng-hide="!nospouse">{{Math.round(value1.spouseValue)}}</td>
																			<td>{{Math.round(value1.expense)}}</td>
																			<td>{{Math.round(value1.federalTax)}}</td>
																			<td>{{Math.round(value1.userfICASocialSecurityTax)}}</td>
																			<td ng-hide="!nospouse">{{Math.round(value1.spousefICASocialSecurityTax)}}</td>

																			<td>{{Math.round(value1.fICAMedicareTax)}}</td>
																			<td>{{Math.round(value1.stateTax)}}</td>

																			<td>{{Math.round(value1.savings)}}</td>
																			<td>{{Math.round(value1.taxable_investment_amount)}}</td>
																			<td>{{Math.round(value1.taxable_investment_income)}}</td>
																			<td>{{Math.round(value1.nontaxable_investment_amount)}}</td>
																			<td>{{Math.round(value1.nonTaxableInvestmentIncome)}}</td>
																		</tr>


																	</tbody>
																</table>





																<!-- 

																<table style="background-color: white;"
																	ng-show="growthTable1" class="table table-bordered">
																	<thead>
																		<tr>
																						<th>User Age</th>
																			<th>Year</th>
																			<th>Income</th>
																			<th ng-hide="maritalFlag_imp1">Spouse Income</th>
																				<th>Expense</th>
																			<th>FDI</th>
																			<th>User Fica SST</th>
																			<th ng-hide="maritalFlag_imp1">Spouse Fica SST</th>
																			<th>Fica Medicare</th>
																			<th>State Tax</th>
																			<th>Saving</th>
																			<th>Ending Taxable Investment Amount</th>
																			<th>Taxable Investment Income</th>
																			<th>Ending Non Taxable Investment Amount</th>
																			<th>Non Taxable Investment Income</th>

																		</tr>
																	</thead>
																	<tbody>
																		<tr ng-repeat="value1 in Porfolio_tableIncome ">
																			<th scope="row" >helo</th>
                                                                           

																			<th scope="row">{{value1.year}}</th>
																			<td>{{Math.round(value1.value)}}</td>
																			<td>{{value1.year}}</td>

																			<td ng-hide="!nospouse">{{Math.round(value1.spouseValue)}}</td>
																			<td>{{Math.round(value1.expense)}}</td>
																			<td>{{Math.round(value1.federalTax)}}</td>
																			<td>{{Math.round(value1.userfICASocialSecurityTax)}}</td>
																			<td ng-hide="!nospouse">{{Math.round(value1.spousefICASocialSecurityTax)}}</td>

																				<td>{{Math.round(value1.fICAMedicareTax)}}</td>
																			<td>{{Math.round(value1.stateTax)}}</td>

																			<td>{{Math.round(value1.savings)}}</td>
																			<td>{{Math.round(value1.taxable_investment_amount)}}</td>
																			<td>{{Math.round(value1.taxable_investment_income)}}</td>
																			<td>{{Math.round(value1.nontaxable_investment_amount)}}</td>
																			<td>{{Math.round(value1.nonTaxableInvestmentIncome)}}</td>
																		</tr>


																	</tbody>
																</table>
 -->






																<!-- 
												<table class="table table-bordered">
													<thead>
														<tr>
															<th>User Age</th>
															<th>Year</th>
															<th>Income</th>
															<th ng-hide="!nospouse">Spouse Income</th>
															<th>Expense</th>
															<th>FDI</th>
															<th>User Fica SST</th>
															<th ng-hide="!nospouse">Spouse Fica SST</th>
															<th>Fica Medicare</th>
															<th>State Tax</th>
															<th>Saving</th>
															<th>Taxable Investment Amount</th>
															<th>Non Taxable Investment Amount</th>

														</tr>
													</thead>
													<tbody>
														<tr ng-repeat="value1 in tableIncome">
															<th scope="row">{{Math.round(value1.id)}}</th>
															<td>{{Math.round(value1.year)}}</td>
															<td>{{Math.round(value1.value)}}</td>
															<td ng-hide="!nospouse">{{Math.ceil(value1.spouseValue)}}</td>
															<td>{{Math.ceil(value1.expense)}}</td>
															<td>{{Math.round(value1.federalTax)}}</td>
															<td>{{Math.round(value1.userfICASocialSecurityTax)}}</td>
															<td ng-hide="!nospouse">{{Math.round(value1.spousefICASocialSecurityTax)}}</td>
															<td>{{Math.round(value1.fICAMedicareTax)}}</td>
															<td>{{Math.round(value1.stateTax)}}</td>
															<td>{{Math.round(value1.savings)}}</td>
															<td>{{Math.round(value1.taxable_investment_amount)}}</td>
															<td>{{Math.round(value1.nontaxable_investment_amount)}}</td>
														</tr>


													</tbody>
												</table>
 -->


															</div>
														</div>
													</div>

												</div>
												<div class="row" ng-show="dashboardportfoliohide">
													<div class="col-md-5"
														style="position: relative; left: 250px;">
														<h2
															style="text-align: center; position: relative; left: 90px;">Assets
															Chart</h2>
														<select
															style="width: 20%; position: relative; left: 300px;"
															data-toogle="tooltip" ng-model="startYearPorfolioBR"
															class="form-control pull-right"
															ng-change="changeYearPorfolioBR(startYearPorfolioBR)">
															<option ng-selected="year == startYearPorfolioBR"
																ng-repeat="year in yearreportsPorfolioBR"
																ng-value="year">{{year}}</option>
														</select> <span style="position: relative; left: 280px; top: 7px;"
															class="pull-right">Select Year </span>

														<nvd3 options="optionsPiePorfolio"
															data="dataPieChartPorfolioBR"></nvd3>
													</div>
													<!--      <div class="col-md-6">
      <h2 style="text-align:center;">After Retirement ASsets</h2>
      <select style="width: 20%;" data-toogle="tooltip"
				ng-model="startYearPorfolioAR" class="form-control pull-right"
				ng-change="changeYearPorfolioAR(startYearPorfolioAR)">
				<option ng-selected="year == startYearPorfolioAR"
					ng-repeat="year in yearreportsPorfolioAR" ng-value="year">{{year}}</option>
			</select> <span class="pull-right">Select Year </span> 
			
    <nvd3 options="optionsPiePorfolio" data="dataPieChartPorfolioAR"></nvd3>
     </div> -->
												</div>







												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">Close</button>
													<!--  <button type="button"  ng-click="checkCheckbox()" class="btn btn-default" >Submit</button> -->
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<!--   <div class="col-md-6">
  
 <div ng-hide="!notNewUser">
	<div class="form-group">

   <div ng-hide="!noGoals">
   <br>
  <button type="submit" class="btn btn-primary" ng-click="goEditGoals()">Edit</button>
  <button type="submit" class="btn btn-primary" ng-click="DeleteGoal()"><i class="fa fa-trash-o"></i>&nbsp Delete</button>
  
 
  </div>
  </div>
	
	
	</div>
  </div> -->

							<div class="col-md-12 form-inline text-center">
								<!-- <a href="#"  data-toggle="modal" data-target="#chartDetails">Details </a>  -->

								<span class="pull-left" style="padding-top: 0.5%;">Select
									an income profile : </span> <select style="width: 18%;"
									class="form-control pull-left" id="sel1"
									ng-model="incomeProfilesChart"
									ng-change="changeIncomeProfile(incomeProfilesChart);clearGraph();">
									<option ng-selected="profile == incomeProfilesChart"
										ng-repeat="profile in incomeProfiles" ng-value="profile">{{profile}}</option>

								</select> <a style="cursor: pointer; cursor: hand;" data-toogle="tooltip"
									title="Click here to delete the income profile"> <i
									class="fa fa-trash-o pull-left"
									ng-click="deleteIncomeProfile()" style="padding: 1%;"
									aria-hidden="true"></i></a> <a style="padding-right: 6%;"
									ng-hide="!finPlanDetails" href="#" data-toggle="modal"
									data-target="#chartDetails">Finplan Details </a> <a
									ng-hide="!incomeProfileDetails" href="#" data-toggle="modal"
									data-target="#incomeProfileDetails"> Income Profile Details
								</a>

								<!-- <select style="width: 8%;" data-toogle="tooltip"
									title="Please select an age to view the income projection"
									ng-model="startAge" class="form-control pull-right"
									ng-change="changeAge()">
									<option ng-selected="age.number == startAge"
										ng-repeat="age in ages" ng-value="age.number">{{age.number}}</option>
								</select> <span class="pull-right">Select an age: </span> -->




							</div>
							<br>
							<div class="col-xs-6 col-sm-8 col-md-12 text-center">
								<!-- <nvd3 options="optionsIncomeExpense" data="dataIncomeExpense"></nvd3> -->
								<div id="d3-chart-container1" style="width: auto; height: auto"></div>
								<!-- <div style="width:900px;margin-left:8%;" id="slider3"></div>

    <span id="slider3textmin"></span>
    <span id="slider3textmax"></span> -->



								<br>
								<div style="margin-top: 2%; text-align: left;">
									<p style="font-size: 12px;">Graph Usage</p>
									<ul style="font-size: 11px;">
										<li>Click on the graph to introduce the data point on the
											line graph.</li>
										<li>Click and drag the bubble on the line graph to change
											the income value.</li>
										<li>Shift + Click on on the bubble to remove the data
											point.</li>
										<li>Please note that the "Current Year", "Retirement
											Year" and "Life Expectency Year" bubbles can not be removed
											from the graph.</li>
									</ul>
								</div>
								<div style="margin-top: 2%">
									<button type="submit" class="btn btn-primary "
										ng-click="restore()">Reset</button>
									<button type="submit" class="btn btn-primary"
										ng-click="clearMessageExpenses()" data-toggle="modal"
										data-target="#chartModelUpdate">Edit Expenses</button>
									<!--  -->
									<button type="submit" class="btn btn-primary "
										ng-click="saveShowPopUp()">Save</button>
									<!--  data-toggle="modal" data-target="#chartModel" -->
								</div>
								<br></br>
								<nvd3 options="optionsGrowhtAsset" data="dataAssetGrowth"></nvd3>

								<br>
								<!-- <div  id="d3-chart-container2" style="width:auto;height:auto"></div>
								 
								 <div style="margin-top:2%">
								<button type="submit" class="btn btn-primary "
									ng-click="restoreExpenses()">Reset</button>
								<button type="submit" class="btn btn-primary "
									data-toggle="modal" data-target="#chartModel">Save</button>
								</div> -->
								<div id="areachart"></div>






								<!--  nvd3 chart for Assets -->

								<h3>Area Chart for Assets</h3>
								<nvd3 options="options" data="dataBRAsset"></nvd3>
								<!--      <h3>Before retirement Taxes</h3>
     <nvd3 options="options"  data="dataBRTax"></nvd3>
     
     <h3>After retirement Taxes</h3>
     <nvd3 options="options"  data="dataARTax"></nvd3>
     <h3>Before retirement Assets</h3>
     <nvd3 options="options" data="dataBRAsset"></nvd3>
     
     <h3>After retirement Assets</h3>
     <nvd3 options="options" data="dataARAsset"></nvd3>  -->

								<div class="row">
									<div class="col-md-5" style="position: relative; left: 250px;">
										<h2
											style="text-align: center; position: relative; left: 90px;">Assets
											Chart</h2>
										<select style="width: 20%; position: relative; left: 300px;"
											data-toogle="tooltip"
											title="Please select an age to view the income projection"
											ng-model="startYearBR" class="form-control pull-right"
											ng-change="changeYearBR(startYearBR)">
											<option ng-selected="year == startYearBR"
												ng-repeat="year in yearBR" ng-value="year">{{year}}</option>
										</select> <span style="position: relative; left: 280px; top: 7px;"
											class="pull-right">Select Year </span>


										<nvd3 options="optionsPie" data="dataPieChartBR"></nvd3>
									</div>
								</div>
								<h3>Area Chart for Taxes</h3>
								<nvd3 options="optionsTax" data="dataTax"></nvd3>
								<br>
								<br>

								<!--   <div class="col-md-6">
      <h2 style="text-align:center;">After Retirement Assets</h2>
      <select style="width: 20%;" data-toogle="tooltip"
									title="Please select an age to view the income projection"
									ng-model="startYearAR" class="form-control pull-right"
									ng-change="changeYearAR(startYearAR)">
									<option ng-selected="year == startYearAR"
										ng-repeat="year in yearAR" ng-value="year">{{year}}</option>
								</select> <span class="pull-right">Select Year </span> 
   
   
    <nvd3 options="optionsPie" data="dataPieChartAR"></nvd3>
     </div> -->
							</div>
							<!-- End of nvd3 chart -->
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
		</div>
	</section>
	<script type="text/javascript">
		function showModal() {
			$('#myModal').modal('show');
			$("#b1").click(
					function() {
						// $("#plan").html("<p class='test'>click me</p>")
						var aa = document.getElementById("i1").value;
						// $('#plan').append(aa+"<br>");
						$('#plan').append(
								'<li><a href="goals.jsp"><i class="fa fa-venus "></i>'
										+ aa + '</a></li>');
						$("#myModal").modal('hide');
					});
		}
	</script>
	<!-- Footer -->
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<br></br>
					<p style="color: white;">Copyright &copy; WealthSetter 2017.
						All Rights Reserved</p>
				</div>
			</div>
		</div>
	</footer>

	<div id="chartModelEdit" class="modal fade">
		<div class="modal-dialog" style="width: 60%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" ng-click="clearExpValues()">&times;</button>
					<h4 class="modal-title">Modify Expense Details</h4>
				</div>
				<div class="modal-body">
					<div style="color: red">{{messageChangeExpense}}</div>
					<br>
					<div style="text-align: center">Do you want to adjust your
						expenses according to the new income profile?</div>
					<p>
					<div class="form-group">
						<p style="text-align: center">
							<label class="checkbox-inline"> <input type="radio"
								name="editExpense" ng-model="checkboxData.editExp"
								ng-change="showEditExpense('yes')" value="yes"> Yes
							</label> <label class="checkbox-inline"> <input type="radio"
								name="editExpense" ng-model="checkboxData.editExp"
								ng-change="showEditExpense('no')" value="no"> No
							</label>
						</p>
						<p ng-show="showEditValues" style="text-align: center">
							For every $1 change in income, do you want to change the housing
							expense <span class="dollar-field2"> <select
								ng-change="clearMessageExpenses()" class="font-dropdown-small"
								ng-model="checkboxData.housingExpense">
									<option ng-repeat="val in expenseValues"
										ng-selected="housingExpense == val" ng-value="{{val}}">{{val}}</option>
							</select>
							</span> <br>
							<br> For every $1 change in income, do you want to change
							the non-housing expense <span class="dollar-field3"> <select
								ng-change="clearMessageExpenses()" class="font-dropdown-small"
								ng-model="checkboxData.nonHousingExpense">
									<option ng-repeat="val in expenseValues"
										ng-selected="nonHousingExpense == val" ng-value="{{val}}">{{val}}</option>
							</select>
							</span>
						</p>
					</div>
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" ng-click="clearExpValues()"
						class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" ng-click="proceedEditExpenses()"
						class="btn btn-default">Submit</button>
				</div>
			</div>
		</div>
	</div>

	<div id="chartModelUpdate" class="modal fade">
		<div class="modal-dialog" style="width: 60%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Modify Expense Details</h4>
				</div>
				<div class="modal-body">
					<div style="color: red">{{messageUpdateExpense}}</div>
					<p>
					<div class="form-group">
						<p>
							For every $1 change in income, do you want to change the housing
							expense <span class="dollar-field"> <select
								ng-change="clearMessageExpenses()" class="font-dropdown-small"
								ng-model="checkboxData.housingExpense">
									<option ng-repeat="val in expenseValues"
										ng-selected="housingExpense == val" ng-value="{{val}}">{{val}}</option>
							</select>
							</span> <br>
							<br> For every $1 change in income, do you want to change
							the non-housing expense <span class="dollar-field1"> <select
								ng-change="clearMessageExpenses()" class="font-dropdown-small"
								ng-model="checkboxData.nonHousingExpense">
									<option ng-repeat="val in expenseValues"
										ng-selected="nonHousingExpense == val" ng-value="{{val}}">{{val}}</option>
							</select>
							</span>
						</p>
					</div>
					</p>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" ng-click="proceedUpdateExpenses()"
						class="btn btn-default">Submit</button>
				</div>
			</div>
		</div>
	</div>

	<div id="chartModel" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Modify Income Details</h4>
				</div>
				<div class="modal-body">
					<div style="color: red">{{messageChangeIncome}}</div>
					<p>
					<div class="form-group">
						<label class="checkbox-inline"> <input type="radio"
							name="favoriteColors" ng-model="checkboxData.applyThisYear"
							ng-value="true" ng-change="selectThisYear()"> Change the
							existing income profile.
						</label>
						<!-- <br></br>
        <label class="checkbox-inline">
            <input type="checkbox" name="favoriteColors" ng-model="checkboxData.applyGraduallyYear" ng-change="selectGraduallyYear()"> Increase the income gradually from current year to the changed year.
        </label> -->
						<br></br> <label class="checkbox-inline"> <input
							type="radio" name="favoriteColors"
							ng-model="checkboxData.applyFutureYear" ng-value="true"
							ng-change="selectFutureYear()"> Create a new income
							profile.

							<div ng-hide="!incomeProfileName" class="class="text-center">
								Please enter the name of the new income profile. <input
									type="tel" maxlength="15" placeholder="" class="input_bl-long"
									class="form-control" ng-model="checkboxData.profileName" />


							</div>
						</label>
					</div>



					</p>
				</div>
				<div class="modal-footer">
					<div class="pull-left" ng-hide="!plansExist">
						<label class="checkbox-inline"> <input type="checkbox"
							name="favoriteColors" ng-model="checkboxData.applyPlan">
							Apply for current financial plan.
						</label>
					</div>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" ng-click="save()" class="btn btn-default">Submit</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="deleteIncomeProfile" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Income Profile Delete</h4>
				</div>
				<div class="modal-body">
					<p>All the financial plans associated with the income profile
						will be deleted. Are you sure you want to delete?</p>
				</div>
				<div class="modal-footer">
					<button type="button" id="dialog-ok"
						ng-click="goDeleteIncomeProfile()"
						class="btn btn-primary pull-right" data-dismiss="modal">
						<i class="fa fa-trash-o"></i>Delete
					</button>

					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	<div class="modal fade" id="myModalback" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Message</h4>
				</div>
				<div class="modal-body">
					<p>You had made some changes in the income profile, do you want
						to proceed?</p>
				</div>
				<div class="modal-footer">
					<button type="button" id="dialog-ok" ng-click="gouserProfile()"
						class="btn btn-primary pull-right" data-dismiss="modal">Yes</button>

					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
				</div>
			</div>

		</div>
	</div>

	<div class="modal fade" id="negativeAssets" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Message</h4>
				</div>
				<div class="modal-body">
					<p>Your assets are going negative. Hence the income profile
						will not be updated.</p>
				</div>
				<div class="modal-footer">
					<button type="button" id="dialog-ok" ng-click="refresh()"
						class="btn btn-primary pull-right" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<div class="modal fade" id="zeroIncome" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Message</h4>
				</div>
				<div class="modal-body">
					<p>Since the income is zero you cannot create plan if you want
						to create plan please update the incomeprofile.</p>
				</div>
				<div class="modal-footer">
					<button type="button" id="dialog-ok" ng-click="refresh()"
						class="btn btn-primary pull-right" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	<div class="modal fade" id="negativeAssets1" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Message</h4>
				</div>
				<div class="modal-body">
					<p>Your assets are going negative. Hence the income profile
						will not be updated.</p>
				</div>
				<div class="modal-footer">
					<button type="button" id="dialog-ok"
						class="btn btn-primary pull-right" data-dismiss="modal">Close</button>
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

					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>



	<!--       <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      Modal content
       
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">New Plan</h4>
        </div>
        <form name="sentMessage">
        <div class="modal-body">
        <div style="color:red">{{message1}}</div>
         <h3>Enter the name for the new financial plan<br></br> 
         					    			
			                <input type="text" class="form-control" placeholder="Enter name" name="username" onkeypress="return onlyAlphaNumbers(event,this)" maxlength="15" ng-model="formdata.planname" required ><span style="color:red; font-size: 50%;" ng-show="sentMessage.username.$dirty && sentMessage.username.$invalid">
			<span ng-show="sentMessage.username.$error.required">Financial plan name is required</span>
			</span></p>
			<input type=hidden ng-model="formdata.form" value="form1" ng-init="formdata.form='createNewPlan'">
			                 	<button type="button" class="btn btn-success" id="b1"  ng-disabled="sentMessage.username.$dirty && sentMessage.username.$invalid || sentMessage.$invalid"  ng-click="modalform()">Next</button>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      </form>
    </div>
  </div> -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Plugin JavaScript -->
	<script src="js/jquery.easing.min.js"></script>
	<script src="js/jquery.fittext.js"></script>
	<script src="js/wow.min.js"></script>
	<!-- Custom Theme JavaScript -->



	<script type="text/javascript" charset="utf-8" src="assets/js/d3.v2.js"></script>
	<script src="assets/js/nv.d3.js"></script>
	<script src="assets/js/angular-nvd3.js"></script>
	<script src="js/creative.js"></script>

</body>
</html>
