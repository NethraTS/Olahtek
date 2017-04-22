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
   <!--  <script type="text/javascript" src="js/d3.slider.js"></script> -->
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
.dropdown-submenu{position:relative;}
.dropdown-submenu>.dropdown-menu{top:0;left:100%;margin-top:-6px;margin-left:-1px;-webkit-border-radius:0 6px 6px 6px;-moz-border-radius:0 6px 6px 6px;border-radius:0 6px 6px 6px;}
.dropdown-submenu>a:after{display:block;content:" ";float:right;width:0;height:0;border-color:transparent;border-style:solid;border-width:5px 0 5px 5px;border-left-color:#cccccc;margin-top:5px;margin-right:-10px;}
.dropdown-submenu:hover>a:after{border-left-color:#555;}
.dropdown-submenu.pull-left{float:none;}.dropdown-submenu.pull-left>.dropdown-menu{left:-100%;margin-left:10px;-webkit-border-radius:6px 0 6px 6px;-moz-border-radius:6px 0 6px 6px;border-radius:6px 0 6px 6px;}
body
{
	overflow-x:hidden;
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
.caret-up {
    width: 0; 
    height: 0; 
    border-left: 4px solid rgba(0, 0, 0, 0);
    border-right: 4px solid rgba(0, 0, 0, 0);
    border-bottom: 4px solid;
    
    display: inline-block;
    margin-left: 2px;
    vertical-align: middle;
}

 
/* .dropdown-submenu {
    position:relative;
}
.dropdown-submenu>.dropdown-menu {
   top:0;left:100%;
   margin-top:-6px;margin-left:-1px;
   -webkit-border-radius:0 6px 6px 6px;-moz-border-radius:0 6px 6px 6px;border-radius:0 6px 6px 6px;
 }
  
.dropdown-submenu > a:after {
  border-color: transparent transparent transparent #333;
  border-style: solid;
  border-width: 5px 0 5px 5px;
  content: " ";
  display: block;
  float: right;  
  height: 0;     
  margin-right: -10px;
  margin-top: 5px;
  width: 0;
}
 
.dropdown-submenu:hover>a:after {
    border-left-color:#555;
 }

.dropdown-menu > li > a:hover, .dropdown-menu > .active > a:hover {
  text-decoration: underline;
}  
  
@media (max-width: 767px) {
  .navbar-nav  {
     display: inline;
  }
  .navbar-default .navbar-brand {
    display: inline;
  }
  .navbar-default .navbar-toggle .icon-bar {
    background-color: #fff;
  }
  .navbar-default .navbar-nav .dropdown-menu > li > a {
    color: red;
    background-color: #ccc;
    border-radius: 4px;
    margin-top: 2px;   
  }
   .navbar-default .navbar-nav .open .dropdown-menu > li > a {
     color: #333;
   }
   .navbar-default .navbar-nav .open .dropdown-menu > li > a:hover,
   .navbar-default .navbar-nav .open .dropdown-menu > li > a:focus {
     background-color: #ccc;
   }

   .navbar-nav .open .dropdown-menu {
     border-bottom: 1px solid white; 
     border-radius: 0;
   }
  .dropdown-menu {
      padding-left: 10px;
  }
  .dropdown-menu .dropdown-menu {
      padding-left: 20px;
   }
   .dropdown-menu .dropdown-menu .dropdown-menu {
      padding-left: 30px;
   }
   li.dropdown.open {
    border: 0px solid red;
   }

} */
 
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
/* (function($){
	$(document).ready(function(){
		$('ul.dropdown-menu [data-toggle=dropdown]').on('click', function(event) {
			event.preventDefault(); 
			event.stopPropagation(); 
			$(this).parent().siblings().removeClass('open');
			$(this).parent().toggleClass('open');
		});
	});
})(jQuery);
 *//* $(function(){
    $(".dropdown").hover(            
            function() {
                $('.dropdown-menu', this).stop( true, true ).fadeIn("fast");
                $(this).toggleClass('open');
                $('b', this).toggleClass("caret caret-up");                
            },
            function() {
                $('.dropdown-menu', this).stop( true, true ).fadeOut("fast");
                $(this).toggleClass('open');
                $('b', this).toggleClass("caret caret-up");                
            },
            function() {
                $('.dropdown dropdown-submenu', this).stop( true, true ).fadeIn("fast");
                $(this).toggleClass('open');
                $('b', this).toggleClass("caret caret-up");                
            });
    }); */
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
	<section style="background-image: url('img/CaseStudy1.jpg'); background-size: cover; height: 100%;" id="about">
		<div class="container">
			<div class="row">
			<div class="col-lg-6 col-lg-offset-1 text-center"></div>
			<div class="col-lg-6 col-lg-offset-1 text-center"></div>
			<div class="col-lg-4 col-lg-offset-1 text-center" style="margin-left: 63%;margin-top: 4%;">
				<h1 style="color: black; font-size: 65px;">Case Study One - Steve, a recent MBA graduate</h1>
				
				<p style="color:black;">

Background: Steve is a recent MBA graduate from an ivy-league college. He just landed a well-paying analyst job at a financial institute in San Francisco. Steve is deeply analytic and likes to plan ahead. Even though he is very confident of his growing career and his ability to support all his future financial needs, he like to use meaWealth to help him plan and visualize his financial paths.
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
Income: Steve expects his income will rise rapidly in the coming years. He believes in fifteen years he will be running a department and hold the position of his boss's boss who, he estimates, is making $250,000 a year. Steve assumes this will stay the same in real dollar terms for the following 10 years. He also realizes competition from the younger workers will be more and more fierce, so he figures his income will actually drop after age 50 to $200,000 and drop again after age 55 to $150,000 a year until he retires at the age of 65.
Expenses: Steve currently spends $2,500 a month on rent and about $2,000 on other expenses. He knows his expenses will go up in the coming years, but not sure by how much, so he pegs it to the same changes as his income.
</p>
<p style="color: black;">
Lifetime events:
-getting married: Steve does not have a  serious relation, but he does like to get married and have a family some day. He thinks that five years probably is a good target to aim for.
-spouse income: When Steve grew up, both his parents had to work very hard to support him and his two brothers and were not able to spend much time with them. Steve wants his kids to have the luxury of a stay-home mom, so he assumes that his wife-to-be will not work after they have their first klid.  Just to be more conservative, Steve does not take into account of his wife-to-be's income in his financial planning. 
-kids - Steve has not thought about this, but two kids, two years apart, and having the first one couple years after getting married sounds like a reasonable plan.
-college costs - Since Steve's parents supported all his college education, Steve wants to do the same for his kids.
-house - Steve is currently renting. He has thought about buying a house, but the astronomically high prices in San Francisco has really discouraged him from seriously considering. However, since he wants to have a  family in five years' time, he realizes he needs to face this problem. So, hypothetically, he assumes he will buy a $800,000 house in six year with 10% down.
-retirement - Steve has not given any thoughts to this, but he is aware of the fact that things that are far into the future doesn't mean that they are not important, it just mean that you have more time to prepare for it. So he decided to use a retirement age of 65 to begin with, knowing that he can always refine it as time goes.
</p>
<p style="color: black;">
Things that Steve told the planner:
name: Steve
age: 25
city of residence: San Francisco
state: CA
current income: $ 10,000 a month ($120,000 per year)
currently renting
rent: $2,500 a month
other expenses: $2,000
future income profile (at today's dollar): goes up to $250,000 a year in 15 years, stay flat for 10 years, then drop to $200,000 for the next 5 years and to $150,000 for the following 10 years.
future expenses: rising proportionately as income.
savings: $0
</p>
<p style="color: black;">
Financial goals that Steve enlisted:
-getting married at age 30: cost of wedding $50,000 (note: program will give cost information to assist Steve in picking a budget)
-buy a house at age 31: cost $800,000; 10% down
-credit score (note: this affects his mortgage rate)
-having a baby at age 32 and 34
-support kids' college education between age 50 and 56 (between 50 and 54 for the first child and between age 52 and 56 for the second child) (note: program will give cost information for a few typical types of college cost to help Steve to pick or provide his own.)
-retire at age 65
</p>
<p style="color: black;">
Financial goal inserted by the planner:
-a rainy fund of six months' expenses
</p>
<p style="color: black;">
Adjustments made by the planner**:
-increase expenses after marriage
-age of Steve's wife-to-be same as his 
-expenses associated with raising a child (excluding college expenses)
-taxes for different status during planning period (single, married, married w/ kids, married w/ no depending kids - after kids graduated from college)
-costs associated with owning a house (property tax, home owner insurance, mortgage insurance)
-switch from standard deduction to itemized deduction and switching back when taking itemization deduction of state and local tax and mortgage interests is more beneficial
-social security income starting at retirement year
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
