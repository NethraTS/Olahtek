<!DOCTYPE html>
<html lang="en" ng-app="selectGoals">

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
<script src="js/selectGoals.js"></script>
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
<script>
	
</script>
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
.addGoals1 {
	padding: 5%;
	border: 1px solid #ebebeb;
	display: inline-block;
	width: 235px;
	height: 125px;
	vertical-align: top;
	background-color: rgba(239, 113, 5, 0.52);
}
</style>
</head>
<body id="page-top" ng-controller="selectGoalsController"
	ng-init="load()" ng-cloak>
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
					<li>
						<!--   <a class="page-scroll" href="Report.jsp">Reports</a> --> <a
						href="#" ng-click="report()">Reports</a>
					</li>
					<li><a class="page-scroll" href="userProfile.jsp"><i
							class="fa fa-user-plus"></i> My Profile</a></li>

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
				<div class="title-createplan" style="display: none">{{hashes.data}}</div>
				{{message}}

				<div class="content-panel">


					<!--	<div class="center-content" >-->


					<div class=" col-xs-6 col-sm-8 col-md-12 contentcenter-data">
						<h3>
							Select a Goal
							<button class="btn btn-primary pull-right"
								ng-click="goDashboard()">Back</button>
							<br>
							<br>
							<div class=" col-xs-6 col-sm-8 col-md-8">
								<div style="cursor: pointer; cursor: hand;" class="addGoals"
									ng-class="{'addGoals1' : EmergencyFund === 1}"
									ng-click="goEmergencyFund()">
									<i class="fa fa-cubes text-center">Emergency Fund </i>
								</div>
								<div style="cursor: pointer; cursor: hand;" class="addGoals"
									ng-class="{'addGoals1' : Retirement === 1}"
									ng-click="goRetirement()">
									<i class="fa fa-dashboard text-center">Retirement</i>
								</div>
								<div style="cursor: pointer; cursor: hand;" class="addGoals"
									 ng-click="goHouse()">
									<i class="fa fa-home text-center">Buying a House</i>
								</div>


								<div ng-hide="mustHide" style="cursor: pointer; cursor: hand;"
									class="addGoals" ng-class="{'addGoals1' : Married === 1}"
									ng-click="goMarried()">
									<i class="fa fa-diamond text-center">Getting Married </i>
								</div>
								<div style="cursor: pointer; cursor: hand;" class="addGoals"
									ng-class="{'addGoals1' : kids === 1}" ng-click="goKids()">
									<i class="fa fa-child text-center">Raising a Kid</i>
								</div>
								<div style="cursor: pointer; cursor: hand;" class="addGoals"
									ng-class="{'addGoals1' : College === 1}"
									ng-click="goCollegeEducation()">
									<i class="fa fa-university text-center">College Education</i>
								</div>



								<div style="cursor: pointer; cursor: hand;" class="addGoals"
									ng-class="{'addGoals1' : Vacation === 1}"
									ng-click="goVacation()">
									<i class="fa fa-photo text-center">Vacation</i>
								</div>
								<div style="cursor: pointer; cursor: hand;" class="addGoals" ng-click="goCar()">
									<i class="fa fa-car text-center">Buying a Car </i>
								</div>

								<div style="cursor: pointer; cursor: hand;" class="addGoals"
									ng-click="goCustomisedGoal()">
									<i class="fa fa-plus text-center">Customized Goal</i>
								</div>
						</h3>


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
								<button type="button" ng-click="check1()"
									class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<div id="ModalNotification" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">Message</h4>
							</div>
							<div class="modal-body">{{goalNotPossible}}</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				
				<div id="ModalNoMarriagegoalWarning" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">Message</h4>
							</div>
							<div class="modal-body">Currently you have no marriage goal in
								your profile !!!</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<div id="ModalNoKidNotification" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">Message</h4>
							</div>
							<div class="modal-body">Currently you have no raising a kid goal added in
								the current plan !!!</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
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
