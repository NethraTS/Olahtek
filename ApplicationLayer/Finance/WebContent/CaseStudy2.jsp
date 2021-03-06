<!DOCTYPE html>
<html lang="en" ng-app="dashboard">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Balagopalan">
<title>WEALTHSETTER</title>
<!-- Bootstrap Core CSS -->
<!-- <script type="text/javascript" src="http://mbostock.github.com/d3/d3.v2.js"></script> -->
    <link rel="stylesheet" href="css/d3.slider.css" /> 
    <!-- <script type="text/javascript" src="js/d3.slider.js"></script> -->
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
<script src="js/dashboard.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.3/nv.d3.min.css"/>
<script src="js/casestudy.js"></script>
<script src="js/calculators.js"></script>
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
body
{
	overflow-x:hidden;
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

.loading {
	display: block;
	position: absolute;
	margin-left: 53%;
	margin-top: 12%;
	z-index: 100000000001;
	color: #2ECCFA;
}

.addGoals2 {
	padding: 5%;
	border: 1px solid #ebebeb;
	display: inline-block;
	width: 18%;
	height: 1px;
	vertical-align: top;
	background-color: rgb(46, 204, 250);;
}
</style>
<script>
/* (function($){
	$(document).ready(function(){
		$('ul.dropdown-menu [data-toggle=dropdown]').on('click', function(event) {
			event.preventDefault(); 
			event.stopPropagation(); 
			$(this).parent().siblings().removeClass('open');
			$(this).parent().toggleClass('open');
		});
	});
})(jQuery); */
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
							<li><a href="ssbCalculator.jsp" >Social Security Benefits</a></li>
							<li><a href="FderalincomeTaxCalculator.jsp" >Income Tax Calculator</a></li>
						</ul>
					</li>
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
	<section class="bg-casestudy" style="background-image: url('img/CaseStudy2.jpg'); background-size: cover; height: 100%;" id="about">
		<div class="container">
			<div class="row">
			
			<div class="col-lg-4 col-lg-offset-1 text-center"><br><br>
				<h1 style="color: black; font-size: 65px;">Case Study Two - John and Joan, working couple with two kids</h1>
				
				<p style="color:black;">

Background: John and Joan have been married for fifteen years with two daughters. They are both in their early forties. John works as a sales manager in an industrial company. Joan is a teacher at a kindergarten. As their children are going to college soon, John and Joan want to make sure they have saved enough to support their kids' education while leaving enough for their retirement. In additional to the equity of the house that they bought ten years ago, they have savings of about $200,000 spread among different accounts.
			</p>
			</div></div>
		</div>
	</section>
	<div style="height:1%"></div>
	<section class="bg-primary" id="about">
		<div style="margin-top:-8%" "class="container">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1 text-center">
					<br></br>
					<br></br>
					<!-- <h1 style="color: white; font-size: 65px;">Investment Portfolio</h1> -->
					<br></br>
					
					<p style="color: black;">Projections:
Income: John makes $85,000 a year while Joan makes $60,000 a year. Both John and Joan's jobs are very stable and they expect their salary will remain the same until they retire.
Expenses: 
Biggest upcoming expenses that John and Joan worry about is the cost of college education of their two daughters who are now in sixth grade and eighth grade.
</p>
<p style="color: black;">
Lifetime events: 
-owning a house: John and Joan bought their house ten years ago just before the housing market peaked and collapsed. One good consequence is they took advantage of the low interest rates that followed and refinanced the mortgage on the house five years ago to bring it down from a 6% 30-year mortgage to a 3% 15-year mortgage. This cause their monthly expenses to increase by about $300 because of the higher mortgage and higher federal taxes due to lower itemized deduction, but they can now own their house clean ten years faster.
-college costs: John and Joan want to fully support their daughters' college education which will happen in 5 and 7 years.
-retirement: They are both in very good health and don't have a specific year to retire, but figure it probably make sense to plan for somewhere between 65 and 70.
</p>
<p style="color: black;">
Things that John and Joan told the planner:
name: John
age: 43
spouse's name: Joan
age: 41
number of kids: 2
name of first kid: Beth
age: 13
name of second kid: Alice
age: 11
current income: $85,000 per year (John) and $60,000 per year (Joan)
currently owning a house
current house value: $400,000
current mortgage: $257,000
years of mortgage left: 10
housing expenses: $3,000 (including property tax)
other expenses: $5,500 (not including income taxes)
(note to Terralogic: we'll ask for housing expenses and other expenses. Within housing expenses, the program will generate the mortgage, mortgage insurance, home owner insurance and property tax using the information of current house value, years of mortgage left, mortgage amount. User can override these data with the actual costs. Similarly, within other expenses, the program will break it down into costs to raise each kid plus the residue other expenses. Again, user can override these costs to make them more accurate.
Spreadsheet is not 100% accurate to reflect the current house value, etc., but should give you a feel of how we need to populate all the tax and expenses fields.)
</p>
<p style="color: black;">
Financial goals that John and Joan specified:
-college education: Beth and Alice's college costs starting five years from now.
-retirement: John and Joan will retire at the same age, 68- and 66-year old, respectively.
</p>
<p style="color: black;">
Adjustments made by the planner**:
-taxes for different status during planning period (married w/ kids, married w/ no depending kids - after kids graduated from college, during retirement years)
-switching between standard deduction and itemized deduction when taking itemization deduction of state and local tax and mortgage interests is more beneficial.
-social security income and IRA withdrawal during retirement years.
-end of planning horizon at age 90
(** note: all these assumptions can be modified by the user to more accurately reflect or to update his actual financial situation.)</p>
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
		  <script type="text/javascript" src="assets/js/d3.v2.js"></script> 
<!-- 	  	<script src="assets/js/D3-3.5.6.min.js" charset="utf-8"></script>  
 -->  	   <script src="assets/js/nvd3-1.8.3.min.js"></script>
     <script src="assets/js/angular-nvd3.js"></script>
	<script src="assets/js/fusioncharts.theme.fint.js"></script>
	<script src="assets/js/fusioncharts.powercharts.js"></script>
	<script src="assets/js/fusioncharts.js"></script>

</body>
</html>
