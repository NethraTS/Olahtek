<!DOCTYPE html>
<html lang="en" ng-app="Emergencyfund">
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
<script src="js/emergencyfund.js"></script>


<script></script>
<script type='text/javascript'>



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

$(function () {
  
  //getting click event to show modal
   
        $('#dialog_confirm_map').modal();
      

        $('.modal-backdrop').appendTo('.right_content');

    $('#help-button').click(function () {
        alert("Action with modal opened or closed");
    });

});
</script>
</head>
<body id="page-top" ng-controller="Goalemergency" ng-init="load()"
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

					<li><a class="page-scroll" ng-click="deleteAllCookies()"><i
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
				<div class="title-createplan">
					Emergency Fund
					<button class="btn btn-primary pull-right"
						ng-click="goSelectGoal()">Back</button>
				</div>
				<div>
					<div class="side-panel">
						<div class="left_content">
							<h1>

								<div class="sidebar-collapse">



									<ul class="nav" id="main-menu" style="font-size: 17px">

										<li><a class="active-menu"><i class="fa fa-arrow "></i>{{message}}</a>
										</li>


									</ul>
								</div>
							</h1>
							<form name="goalDetails" id="goalForm">
								<br>
								<div></div>
								<div class="form-group" align="left" ng-show="show1>1">
									<p>
										Amount to be saved <span class="money-field"> <input
											type="text" allow-pattern="\d" class="form-control"
											maxlength="12" name="marriagecost" value="$5000"
											ng-model="formdata.amountSave"></span>
									</p>



								<!-- 	<div class="form-group" align="left" ng-show="show1>2">
										<p>Time period (in Years)</p>
										<input type="text" allow-pattern="\d" class="form-control"
											maxlength="2" name="marriagecost" value="$5000"
											ng-model="formdata.amountSave1">
										</p>

										<div class="slider"></div>
									</div> -->

				<!-- 					<div class="form-group" align="left" ng-show="show1>2">

										<div id="demo" class="collapse">

											<input type=hidden ng-model="formdata.actionHomeType"
												value="form1" ng-init="formdata.actionHomeType='update'">
										</div>
										<br> &nbsp&nbsp
										<button type="button" class="btn btn-primary"
											ng-click="checkform1()">Update</button>
									</div> -->

								</div>



							</form>
						</div>
					</div>
					<div class="center-content" style="height: 750px;">
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
											<form name="myForm">
												<div class="form-group" ng-show="show==1">
													<span style="color: red">{{msgerr}}</span>

													<div class="form-group">
														<div class="radio">
															<label> <input type="radio" name="timePeriod"
																value="Fix Amount" ng-model="formdata.timePeriod">
																Fix amount
															</label>&nbsp;&nbsp;&nbsp;
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</div>
														<div class="radio">
															<label> <input type="radio" name="timePeriod"
																value="Expense" ng-model="formdata.timePeriod">
																Months of expenses <select class="font-dropdown"
																style="width: 120px;" ng-model="formdata.month"><option
																		ng-selected="item==formdata.month"
																		ng-repeat="item in time" value="{{item}}">{{item}}</option>
															</select>
															</label>
														</div>
														<div class="radio">
															<label> <input type="radio" name="timePeriod"
																value="Income" ng-model="formdata.timePeriod">
																Months of income <select class="font-dropdown"
																style="width: 120px;" ng-model="formdata.month1"><option
																		ng-selected="item==formdata.month1"
																		ng-repeat="item in time1" value="{{item}}">{{item}}</option>
															</select>
															</label>&nbsp;&nbsp;
														</div>
													</div>
													<button type="button" class="btn btn-primary"
														onclick="progress_bar()" ng-click="progressBar();">Next</button>
													</br>
													</br>
												</div>
												<span></span> <span></span>
												<div class="form-group" ng-show="show==3">
													<span style="color: red">{{msgerr2}}</span>
													<p>Amount to be saved</p>
													<input type="hidden" name="middlename"> <input
														type="hidden" name="firstname"> <span
														class="money-fieldmodel"> <input type="text"
														name="lastname" allow-pattern="\d" maxlength="2"
														placeholder="20" ng-model="formdata.amountSave"
														disabled=""></span></br>
													</br> <input type=hidden ng-model="formdata.actionHomeType"
														value="form1" ng-init="formdata.actionHomeType='insert'">
													<button type="button" class="btn btn-primary"
														onclick="progress_bar2()" ng-click="progressBar1()">Next</button>
													</br>
													</br>
												</div>

											</form>
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
									<p>
										You have to save <span>$</span> {{messresult}} per month to
										attain the requested Emergency fund goal.
									</p>
									<table class="table" ng-show="show>3">
										<tr>
											<td>Amount to be saved</td>
											<td><span>$</span> {{amountSave}}</td>
										</tr>
										<td>Time Period (in years)</td>
										<td>{{timePeriod}}</td>
										</tr>
										<td>EMI</td>
										<td><span>$</span> {{messresult}}</td>
										</tr>
										</tr>
									</table>

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