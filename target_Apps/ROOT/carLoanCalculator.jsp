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
<!-- <script src="js/app.js"></script>
<script src="js/dashboard.js"></script>
<script src="js/userProfile.js"></script>
<script src="js/calculators.js"></script>
 --><script src="js/carLoanCalculator.js"></script>
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

<script src="js/bootstrap-slider.js"></script>
<script src="js/bootstrap-slider.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-slider.css" />
<link rel="stylesheet" href="css/bootstrap-slider.min.css" />
<script type="text/javascript" src="js/d3.js"></script>

<script type="text/javascript" src="http://mbostock.github.com/d3/d3.js?2.4.5"></script>
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
<!-- 	<script src="assets/js/fusioncharts.theme.fint.js"></script>
	<script src="assets/js/fusioncharts.powercharts.js"></script>
	<script src="assets/js/fusioncharts.js"></script> -->
	<script type="text/javascript" src="http://static.fusioncharts.com/code/latest/themes/fusioncharts.theme.ocean.js"></script>

	
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

.percent-field::before {
    content: "%";
    position: absolute;
    font-size: 15px;
    display: block;
    right: 24%;
    bottom: 13%;
    padding: 3.4%;
}
.percent1-field::before {
    content: "%";
    position: absolute;
    font-size: 15px;
    display: block;
    right: 24%;
    bottom: 54%;
    padding: 3.4%;
}
.percent2-field::before {
    content: "years";
    position: absolute;
    font-size: 15px;
    display: block;
    right: 24%;
    bottom: 65%;
    padding: 3.4%;
}
/* .year-field::before {
    content: "year";
    position: absolute;
    font-size: 15px;
    display: block;
    right: 25%;
    bottom: 13%;
    padding: 3.4%;
} */


/* select, input
 {
 margin-top:1%;
 } */
 tr
 {
 	line-height: 3;
 }
.left
{
	padding-left : 2%;	
}
.right
{
	padding-right : 2%;	
}
 caption
 {
 	text-align:center;
 	color: black;
 	font-size: 18px;
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
#ex1Slider
{
		margin-left:5%;
}
#ex1Slider .slider-selection {
	background: #BABABA;
}
tr:nth-child(even) {background: #FFF}
tr:nth-child(odd)  {background: rgba(102, 152, 255, 0.52)}/* {background: #6698FF} */
* {
	font-size: 12px;
    font-family: "Lucida Grande","DejaVu Sans","Bitstream Vera Sans",Verdana,Arial,sans-serif;
}
.legend rect {
	fill:white;
	opacity:0.4;
}

path.slice {
	transition: fill-opacity .4s ease;
}
.legend
{
	width:400px;
	margin-left:15%;
}
.slice-label {
    cursor: pointer;
}
</style>
</head>
<body id="page-top" ng-controller="formController2"
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
					<!-- <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Case Studies <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="CaseStudy1.jsp">Case Study 1</a></li>
                            <li><a href="CaseStudy2.jsp">Case Study 2</a></li>
                            <li><a href="CaseStudy3.jsp">Case Study 3</a></li>
						</ul>
					</li> -->
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Resources <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="mortgageCalculator.jsp">Mortgage Calculator</a></li>
                            <li class="dropdown dropdown-submenu"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Car Calculator</a>
								<ul class="dropdown-menu">
									<li><a href="carLoanCalculator.jsp">Car Loan Calculator</a></li>
									<li><a href="carLeaseCalculator.jsp">Car Lease Calculator</a></li>
								</ul>
							</li>
							<li><a href="ssbCalculator.jsp" >Social Security Benefits</a></li>
							<li><a href="FderalincomeTaxCalculator.jsp" >Income Tax Calculator</a></li>
						</ul>
					</li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Case Studies <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="CaseStudy1.jsp">Case Study 1</a></li>
                            <li><a href="CaseStudy2.jsp">Case Study 2</a></li>
                            <li><a href="CaseStudy3.jsp">Case Study 3</a></li>
						</ul>
					</li>
					<li>
						<!--   <a class="page-scroll" href="Report.jsp">Reports</a> --> 
						<a ng-hide="guest" href="#" ng-click="report()">Reports</a>
					</li>


					<li><a ng-hide="guest" class="page-scroll" href="#" ng-click="checkSave()"><i
							class="fa fa-user-plus"></i> My Profile</a></li>

					<li><a ng-hide="guest"  href=# class="page-scroll"
						ng-click="deleteAllCookies()"><i class="fa fa-sign-out"></i>
							Logout</a></li>


					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	
	<!-- <section style="margin-left:1%;height: 150%;" id="about"> -->
	<div style="height:10%"></div>
	<div class="container-fluid">
	  	<div class="row">
	    	<div style="margin-right:-3%" class="col-sm-5">
			    <table border="0" width="80%">
				<caption>Car Loan Calculator</caption>
				<tr>
					<td style="padding-bottom: 2%;" class="left">Car Price</td>
			        <td class="right"><input type="text" onkeypress="return numbersonly(event)" maxlength="15" class="form-control" placeholder="$" ng-model="formdata.carPrice" /></td>
				</tr>
				<tr>
					<td style="padding-bottom: 2%;" class="left">Loan Term:</td>
					<td class="right"><span class="percent2-field"> 
					<span style="color:red">{{lt_msgerr}}</span>
					<select class="form-control ng-pristine ng-valid" 
							ng-model="formdata.loanTerm"
							>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							</select></span></td>
				</tr>
				<!-- <tr>
					<td style="padding-bottom: 2%;" class="left">Loan Term</td>
					<td class="right"><span class="percent2-field"> <input type="text" onkeypress="return numbersonly(event)" maxlength="4" class="form-control ng-pristine ng-valid" placeholder="years" ng-model="formdata.loanTerm" />
					</span></td>
				</tr> -->
				
				<tr>
					<td style="padding-bottom: 2%;" class="left">Interest rate</td>
					<td class="right"><span class="percent1-field"> 
					<input type="text" class="form-control ng-pristine ng-valid loan"  maxlength="5" ng-model="formdata.interestRate" />
					</span></td>
			    </tr>
				<tr>
					<td style="padding-bottom: 2%;" class="left">Down Payment</td>
					<td class="right"><input type="text" onkeypress="return numbersonly(event)" maxlength="15"  class="form-control" placeholder="$" ng-model="formdata.downPayment" /></td>
				</tr>
				<tr>
					<td style="padding-bottom: 2%;" class="left">Trade-in Value</td>
					<td class="right"> <input type="text" onkeypress="return numbersonly(event)" class="form-control"  maxlength="15" placeholder="$"   ng-model="formdata.tradeInValue" /></td>
				</tr>
				<tr>
					<td style="padding-bottom: 2%;" class="left">Doc,License&Registration Fees</td>
					<td class="right"><input type="text" onkeypress="return numbersonly(event)" maxlength="15"class="form-control" placeholder="$" ng-model="formdata.licenseFee" /></td>
				</tr>
				<tr>
					<td style="padding-bottom: 2%;" class="left">Sale Tax</td>
					<td class="right"><span class="percent-field"> 
                   <input type="text" class="form-control ng-pristine ng-valid loan" maxlength="5" ng-model="formdata.saleTax">
                </span></td>
				</tr>
				
				<!-- <tr>
					<td class="left">Title, Registration & Other fee</td>
					<td class="right"><input type="text" class="form-control" placeholder="$" ng-model="formdata.homeValue" /></td>
				</tr> -->
				</table>
				<!-- <div class="checkbox" style="margin-top:2%;text-align: center;">
					<label><input type="checkbox" value="">Include TT&L Fee in Loan</label>
				</div> -->
				<div style="margin-top:2%;margin-bottom:2%;color:red;text-align: center;">{{clc_msgerr}}</div><br>
				<div style="margin-top:2%;text-align: center;">
					<button  type="button" ng-click="calculate()" class="btn btn-primary">Calculate</button>
				</div>
		</div>
    	<div  class="col-sm-7">
	    	<div class="row">
	    		<div class="col-sm-6">
	    			<table width="105%">
					<caption>Payment Summary</caption>
					<tr>
						<th style="padding-bottom: 2%;" class="left">Loan Amount</th><br><br>
						<th style="padding-bottom: 2%;" class="right">Total Cost</th>
					</tr>
					<tr>
						<td style="padding-bottom: 2%;" class="left">{{netcapitalizedcost}}</td>
						<td style="padding-bottom: 2%;" class="right">{{loan}}</td>
					</tr>
					<tr>
						<th style="padding-bottom: 2%;" class="left">Total interests</th>
						<th style="padding-bottom: 2%;" class="right">Monthly Payment</th>
					</tr>
					<tr>
						<td style="padding-bottom: 2%;" class="left">{{interest1}}</td>
						<td style="padding-bottom: 2%;" class="right">{{monthlypayment}}</td>
					</tr>
					<!-- <tr>
						<th class="left">Total Loan Interest</th>
						<th class="right">Total Cost(price interest tax fee)</th>
					</tr>
					<tr>
						<td class="left">$7500</td>
						<td class="right">$15000</td>
					</tr> -->
					</table>
					
					<div style="margin-top:10%;margin-left:10%;text-align:center"><b>Monthly Pay:{{monthlypayment}}</b></div>
				<!-- <div style="color:black;text-align:center;">LoanBreakdown</div>	 -->
	    		</div>
	    		
	    		<div class="col-sm-6">
	    			<!-- <div id="chart-container">FusionCharts will render here</div> -->
	    			<div style="color:black;text-align:center;font-size:18px;">Loan Breakdown</div>
	    			<div id="ChartAccessAgesByCountD3" style="height:260px;" data-drilldown-destination="filelist_by_category" data-drilldown-key="atime"></div>
	    		
	    		</div>
	    	</div><br><br>
	    	<div style="margin-top:1%;" class="row">
	    		<div style="text-align:center;font-size: 18px;color:black;">Annual Amortization Schedule</div>
	    		<div style="margin-top:5%;text-align: center">
	    			<button type="button" ng-click="showAnnualData()" class="btn btn-primary" >Annual Schedule</button><!-- ng-click="Annual()"  -->
	    			<button type="button" ng-click="showMonthlyData()" class="btn btn-primary" >Monthly Schedule</button><!-- ng-click="Month()" -->
	    			<!-- <b>2016</b> <input id="ex2" type="text" class="span2" value="" data-slider-min="2016" data-slider-max="2099" data-slider-step="1" data-slider-value="[250,450]"/> <b>2099</b> -->
	    			<!-- <input id="ex1" data-slider-id='ex1Slider' type="text" data-slider-min="2017" data-slider-max="2099" data-slider-step="1" data-slider-value="2027"/> -->
	    			<!-- <div id="slider-range"></div> -->
	    			<!-- <script>
	    			var slider = new Slider('#ex1', {
	    				formatter: function(value) {
	    					return 'Current value: ' + value;
	    				}
	    			});
	    			</script> -->
	    		</div>
	    		
	    		 <div ng-hide="showAnnualTable" > 
	    		<table  style="margin-top:5%;"width="100%">
	    			<tr>
	    				<th class="left">YEAR</th>
	    				<th>Beginning Balance</th>
	    				<th>Interest</th>
	    				<th>Principal</th>
	    				<th class="right">Ending Balance</th>
	    			</tr>
	    			 <tr ng-repeat="ann in Annual">
	    				<td class="left">{{ann.year}}</td>
	    				<td>{{ann.s_balance}}</td>
	    				<td>{{ann.interest1}}</td>
	    				<td>{{ann.principal}}</td>
	    				<td class="right">{{ann.e_balance}}</td>
	    			</tr> 
	    			
	    				
	    		</table>
	    		</div > 
	    		<div ng-hide="showMonthlyTable"> 
	    		 <table style="margin-top:5%;"width="100%">
	    		<tr>
	    				<th class="left">MONTH</th>
	    				<th>Beginning Balance</th>
	    				<th>Interest</th>
	    				<th>Principal</th>
	    				<th class="right">Ending Balance</th>
	    			</tr>
	    			 <tr ng-repeat="ann1 in Annual1">
	    				<td class="left">{{ann1.year1}}</td>
	    				<td>{{ann1.s_balance1.toFixed(2)}}</td>
	    				<td>{{ann1.interest2.toFixed(2)}}</td>
	    				<td>{{ann1.principal1.toFixed(2)}}</td>
	    				<td class="right">{{ann1.e_balance1.toFixed(2)}}</td>
	    			</tr> 
	    			
	    				
	    		</table>
	    		</div> 
 	    	</div>
  	 </div>
  	</div>
</div><br><br>
	<!-- </section> -->

	<div class="modal fade" id="caseStudy" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Message</h4>
				</div>
				<div class="modal-body">
					<p>Under Construction</p>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-default"
						data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

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
<script>
 
 $(document).ready(function() {
    $(".loan").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)){
            e.preventDefault();
        }
    });
});
 </script>
</body>
</html>
