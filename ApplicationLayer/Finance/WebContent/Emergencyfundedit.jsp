<!DOCTYPE html>
<html lang="en" ng-app="Emergencyfundedit">
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
<script src="js/emergencyfundedit.js"></script>

<script>
	
</script>

<script type='text/javascript'>
	function progress_bar() {
		$('#progress_bar').css('width', '35%');
	}
	function progress_bar1() {
		$('#progress_bar').css('width', '70%');
	}
	function progress_bar2() {
		$('#progress_bar').css('width', '70%');
	}
	function progress_bar3() {
		$('#progress_bar').css('width', '100%');
	}
	function progress_bar4() {
		$('#progress_bar').css('width', '100%');
	}
</script>
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
.left_content {
	height: 500px;
	border-radius: 25px;
}
@media (min-width: 768px) {
  ul.nav li:hover > ul.dropdown-menu {
    display: block;
  }
  #navbar {
    text-align: center;
  }
}
.right_content {
	height: 500px;
	background: white;
	"
}

.progress {
	width: 50%;
	margin-left: 20%;
}

#container {
	display: block;
	margin: 5px 0;
}

.modal-content {
	width: inherit;
	height: inherit;
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

.bigform-content input[type=submit] {
	margin-top: 10px;
}
</style>
<script>
	
</script>
</head>
<body id="page-top" ng-controller="Goalemergencyedit" ng-init="load()"
	ng-cloak>
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

					<li><a class="page-scroll" href="index.jsp"><i
							class="fa fa-sign-out"></i> Logout</a></li>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<section class="bg-primary" id="about">
		<div class="container">
			<div class="row">
				<div style="display: none" class="alert alert-info text-center"
					id="fail-warning">
					<button type="button" class="close" ng-click="hideWarning()">x</button>
					<strong>Warning! Goal is not feasible currently with your
						income profile !! Please update your income profile in the
						Dashboard to make it feasible </strong> {{errmessage1}}
				</div>
				<div style="display: none" class="alert alert-danger text-center"
					id="fail-alert">
					<button type="button" class="close" ng-click="hideFail()">x</button>
					<strong>Fail! </strong> {{errmessage1}}
				</div>
				<div style="display: none" class="alert alert-success text-center"
					id="success-alert">
					<button type="button" class="close" ng-click="hideSuccess()">x</button>
					<strong>Success! </strong> {{errmessage}}
				</div>
				<div class="title-createplan">
					Emergency Fund
					<button class="btn btn-primary pull-right"
						ng-click="backwithoutsave(formdata)">Back</button>
					<button type="button" class="btn btn-primary pull-right"
						ng-click="DeleteGoal()" style="margin-right: 0.3%;">
						<i class="fa fa-trash-o"></i>Delete
					</button>
				</div>
				<div>
					<div class="side-panel" style="padding-bottom: 19%">
						<div class="left_content">
							<h1>

								<div class="sidebar-collapse">



									<ul class="nav" id="main-menu" style="font-size: 17px">

										<li><a class="active-menu"><i class="fa fa-arrow "></i>Details</a>
										</li>


									</ul>
								</div>
							</h1>
							<form name="goalDetails" id="goalForm">
								<br>
								<div></div>

								<div class="form-group">
									<div class="radio">
										<label> <input type="radio" name="timePeriod"
											ng-click="progressBar()" value="Fix Amount"
											ng-model="formdata.timePeriod"> Fix amount
										</label>&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
									<div class="radio">
										<label> <input type="radio" name="timePeriod"
											ng-click="progressBar1()" value="Expense"
											ng-model="formdata.timePeriod"> Months of expenses <select
											ng-change="progressBar1()" class="font-dropdown"
											style="width: 105px; font-size: 16px;"
											ng-disabled="checkedexp" ng-model="formdata.month"><option
													ng-selected="item==formdata.month" ng-repeat="item in time"
													value="{{item}}">{{item}}</option>
										</select>
										</label><br> <br>
									</div>
									<div class="radio">
										<label> <input type="radio" name="timePeriod"
											ng-click="progressBar2()" value="Income"
											ng-model="formdata.timePeriod"> Months of income <select
											ng-change="progressBar2()" class="font-dropdown"
											style="width: 105px; font-size: 16px;"
											ng-disabled="checkedinc" ng-model="formdata.month1"><option
													ng-selected="item==formdata.month1"
													ng-repeat="item in time1" value="{{item}}">{{item}}</option>
										</select>
										</label>&nbsp;&nbsp;
									</div>
								</div>

								<div class="form-group" align="left">

									<p>
										Amount to be saved <span class="money-field"> <input
											class="form-control" type="text" name="lastname" disabled=""
											allow-pattern="\d" maxlength="10" placeholder="20"
											ng-model="formdata.amountSave"></span>
									</p>

								</div>
								<button type="button" class="btn btn-primary"
									ng-click="checkform1()">Update</button>
								</br> </br>
							</form>
						</div>
					</div>
					<div class="center-content" style="height: 750px;">
						<div class="center_panel">
							<div class="align-sidebar fade in " id="dialog_confirm_map"
								role="dialog" aria-labelledby="dialog_confirm_mapLabel"
								aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										</br> </br>
										<div class="progress">
											<div class="progress-bar progress-bar-striped active"
												id="progress_bar" ng-model="progressbar" aria-valuemin="0"
												aria-valuemax="100" style="width: 2%"></div>
										</div>


										<div class=" text-center">

											<div class="form-group" ng-show="show==1">

												<p>What is your desired location?</p>
												<input type="text" placeholder="Bangalore" name="firstname"
													ng-model="formdata.firstname"></br> </br>
												<button type="button" class="btn btn-primary"
													onclick="progress_bar()" ng-click="progressBar()">Next</button>
												</br> </br>
											</div>
											<span>{{formdata.firstname}}</span> <span>{{formdata.middlename}}</span>

											<div class="form-group" ng-show="show==4">
												<p>What are your monthly debt payments?</p>
												<input type="hidden" name="middlename"> <input
													type="hidden" name="firstname"> <input type="text"
													name="lastname" placeholder="$ 0"
													ng-model="formdata.firstname"></br> </br>
												<button type="button" class="btn btn-primary"
													onclick="progress_bar3()" ng-click="progressBar2()"
													data-dismiss="modal">Next</button>
												</br> </br>

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
									<i class="fa fa-home fa-5x"></i></br> </br>
									<p>{{messresult}}</p>

									<div class="form-group" ng-hide="newCalc">
										<!-- <div ng-hide="formdata.rentLease"> -->
					
											<div class="radio">
												<label> <a name="rentLease"
													ng-click="excludeInvestment()" value="exclude"
													ng-model="formdata.rentLease"> Exclude investment
													</a>
												</label>
											</div>
										<!-- </div> -->
										
										
									</div>
									
										<div class="form-group" ng-hide="includeinvestment">
											<div class="radio">
												<label> <a name="incinv"
													ng-click="includeInvestmentfunc()" value="include"
													ng-model="formdata.incinv"> Include investment
													</a>
												</label>
											</div>
										
									</div>
									
								</div>
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
							<h4 class="modal-title">Message</h4>
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
			<div class="modal fade" id="myModal1" role="dialog">
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
							<button type="button" ng-click="deletegoal()"
								class="btn btn-primary pull-right" data-dismiss="modal">
								<i class="fa fa-trash-o"></i>Delete
							</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal"
								style="background-color: #B7B7BB; margin-right: 0.3%;">Close</button>
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
			<div class="modal fade" id="myModalback" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Message</h4>
						</div>
						<div class="modal-body">
							<p>You had made some changes,are you sure you want to go back
								without updating this goal ?</p>
						</div>
						<div class="modal-footer">
							<button type="button" id="dialog-ok" ng-click="goDashboard()"
								class="btn btn-primary pull-right" data-dismiss="modal">Yes</button>

							<button type="button" class="btn btn-default"
								data-dismiss="modal">no</button>
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