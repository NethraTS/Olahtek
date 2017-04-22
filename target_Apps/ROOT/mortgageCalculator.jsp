<!DOCTYPE html>
<html lang="en" ng-app="formApp2" >
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Balagopalan">
<title>WEALTHSETTER</title>
<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<!-- Custom Fonts -->
<link rel="stylesheet" 
="css/fonts.googleapis.css" type="text/css">
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
 <!-- <script src="js/dashboard.js"></script>
<script src="js/userProfile.js"></script> -->
<script src="js/mortgagecalculator.js"></script>
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

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/9.3.0/bootstrap-slider.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/9.3.0/bootstrap-slider.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/9.3.0/css/bootstrap-slider.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/9.3.0/css/bootstrap-slider.min.css" />

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
	<script src="assets/js/fusioncharts.theme.fint.js"></script>
	<script src="assets/js/fusioncharts.powercharts.js"></script>
	<script src="assets/js/fusioncharts.js"></script>
    <script type="text/javascript" src="http://static.fusioncharts.com/code/latest/themes/fusioncharts.theme.ocean.js"></script>
	
	
	 <script src="//code.angularjs.org/snapshot/angular.min.js"></script>
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
/*  #theTable tr {
        border-top: 1px solid #ddd;
        border-bottom: 1px solid #ddd;
}
#theTable td {  
        min-width: 150px;
}

tr td:first-child {
    font-size:16px;
}
 */
// Here is the CSS to make the slider look pretty:
#slider-range, #slider-range2 {
  width:300px;
  margin-top:10px;
}
body {
  margin: 15px;
}
#slider-range2.ui-slider-horizontal {
    border: 0 none;
}
#slider-range2.ui-slider-horizontal .ui-slider-range, #slider-range2.ui-slider-horizontal .ui-slider-handle {
    background: url("http://unbug.ru/examples/jquery/slider/slide.png") repeat scroll 0 0 transparent;
}
#slider-range2.ui-slider-horizontal .ui-slider-range {
    background-position: 0 -42px;
    background-repeat: repeat-x;
    height: 21px;
}
#slider-range2.ui-slider-horizontal .ui-slider-handle {
    background-position: 0 0;
    background-repeat: no-repeat;
    border: 0 none;
    height: 21px;
    top: 0;
    width: 21px;
}
#slider-range2.ui-slider-horizontal .ui-slider-handle:focus {
    outline: 0 none;
}
#slider-range2.ui-slider-horizontal .ui-slider-handle + .ui-slider-handle {
    background-position: -21px 0;
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
.percent1-field::before {
    content: "%";
    position: absolute;
    font-size: 15px;
    display: block;
    right: 22%;
    bottom: 70%;
    padding: 3.4%;
}
 .percent-field::before {
    content: "";
    position: absolute;
    font-size: 15px;
    display: block;
    right: 22%;
    bottom: 70%;
    padding: 3.4%;
} 
.percent2-field::before {
    content: "%";
    position: absolute;
    font-size: 15px;
    display: block;
    right: 22%;
    bottom: 61%;
    padding: 3.4%;
} 
.percent3-field::before {
    content: "%";
    position: absolute;
    font-size: 15px;
    display: block;
    right: 22%;
    bottom: 56%;
    padding: 3.4%;
} 
.percent4-field::before {
    content: "%";
    position: absolute;
    font-size: 15px;
    display: block;
    right: 22%;
    bottom: 28%;
    padding: 3.4%;
} 

/* .percent-field::before {
    content: "%";
    position: absolute;
    font-size: 15px;
    display: block;
    right: 5%;
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
tr:nth-child(odd) {background: rgba(102, 152, 255, 0.52)}
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
				id="bs-example-navbar-collapse-1" >
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
                            <li><a href="mortgageCalculator.jsp">Mortage Calculator</a></li>
                          <li class="dropdown dropdown-submenu"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Car Calculator</a>
								<ul class="dropdown-menu">
									<li><a href="carLoanCalculator.jsp">Car Loan Calculator</a></li>
									<li><a href="carLeaseCalculator.jsp">Car Lease Calculator</a></li>
								</ul>
							</li>
                            <li><a href="ssbCalculator.jsp">Social Security Benefits</a></li>
							<li><a href="FderalincomeTaxCalculator.jsp">Income Tax Calculator</a></li>
                             <!-- <li><a href="" data-toggle="modal" data-target="#caseStudy">Calculator 2</a></li> -->
                            <!-- <li class="dropdown dropdown-submenu"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Calculator 3</a> -->
								<!-- <ul class="dropdown-menu">
									<li><a href="#" data-toggle="modal" data-target="#caseStudy">Calculator 4</a></li>
									<li><a href="#" data-toggle="modal" data-target="#caseStudy">Calculator 5</a></li>
								</ul> -->
							</li>
						
						</ul>
					</li>
					<li>
						  <!--  <a class="page-scroll" href="Report.jsp">Reports</a>  -->
					       <a ng-hide="guest" href="#" ng-click="report()">Reports</a>
					</li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Case Studies <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="CaseStudy1.jsp">Case Study 1</a></li>
                            <li><a href="CaseStudy2.jsp">Case Study 2</a></li>
                            <li><a href="CaseStudy3.jsp">Case Study 3</a></li>
						</ul>
					</li>

                   
					<li><a ng-hide="guest" class="page-scroll" href="userProfile.jsp" ng-click="checkSave()"><i
							class="fa fa-user-plus"></i> My Profile</a></li>
                    
                    
                    
					<li><a ng-hide="guest" href=# class="page-scroll"
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
			    <table border="0" width="85%">
				<caption>Mortgage Calculator</caption>
				<!-- <nvd3 options="options" data="data"></nvd3>
    
    <br><a href="http://plnkr.co/edit/EVW5qF?p=preview" target="_blank">Simple data example</a><a href="http://krispo.github.io/angular-nvd3/" target="_blank" style="float: right;">See more</a> -->
    <tr>
                    <td style="padding-bottom: 2%;" class="left">Classify The House:</td>
                    <td class="right">
                    <span style="color:red">{{ch_msgerr}}</span>
                    <select class="form-control"
                            ng-model="formdata.forRental" ng-change="rental()"
                            >
                                  <option   ng-repeat="rental in rentals" ng-value="rental" >{{rental}}</option> 
                                   // ng-selected=" rental[0] "
                    </select></td>
                </tr>
               
				<tr>
					<td style="padding-bottom: 2%;" class="left">State:</td>
					<td  class="right">
					<span style="color:red">{{st_msgerr}}</span>
						<select class="form-control" ng-model="formdata.state"  ng-change="getStates()"  ng-change="getCities()">
							<option  ng-selected="state == editBasicDetail.state " 
							ng-repeat="state in states" ng-value="state">{{state}}</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="padding-bottom: 2%;" class="left">City:</td>
					<td class="right">
					<span style="color:red">{{ci_msgerr}}</span>
						<select class="form-control"
							ng-model="formdata.desired_locationcity"
							ng-change="getCounty()">
							<!--  <option ng-selected="state.name == editBasicDetail.state " ng-repeat="state in states" ng-value="state.name">{{state.name}}</option> -->
							<option ng-selected="city==formdata.desired_locationcity"
								ng-repeat="city in citys">{{ city }}</option>
			
					</select>
					</td>
				</tr>
				
				<tr>
					<td  style="padding-bottom: 2%;" class="left">Home Value:</td>
					<td class="right">
					 <span style="color:red">{{hv_msgerr}}</span>
						<input type="text" onkeypress="return numbersonly(event)" maxlength="9" class="form-control" placeholder="$ " ng-model="formdata.homeValue" />
					</td>
				</tr>
				<tr>
                    <td style="padding-bottom: 2%;" class="left">Down Payment:</td>
                    <td class="right"><span class="percent1-field"> 
                    <select class="form-control ng-pristine ng-valid"   ng-model="formdata.downPayment">
                     <option ng-repeat="downpayment in downpayments" ng-value="downpayment">{{downpayment}}</option> 
                    </select></span></td>
                </tr>
				
                
				<tr>
					<td style="padding-bottom: 2%;" class="left">Loan Type:</td>
					<td class="right"><span class="percent-field"> 
					<span style="color:red">{{lt_msgerr}}</span>
					<select class="form-control ng-pristine ng-valid" 
							ng-model="formdata.loanTerm" ng-change="loanTerms()"
							>
							<option  ng-repeat="loan in loans" ng-value="loan.name" >{{loan.name}}</option>
							</select> </span></td>
				</tr>
				<tr>
					<td  style="padding-bottom: 2%;" class="left">Interest rate:</td>
					<td class="right"><span class="percent2-field">
					<span style="color:red">{{ir_msgerr}}</span>
					<input type="text"  maxlength="5" class="form-control ng-pristine ng-valid mort"  ng-model="formdata.interestRate"  /></span></td><!-- onkeypress="return numbersonly(event)" -->
					
				</tr>
				
				<tr>
			
					<td style="padding-bottom: 2%;" class="left">Property Tax:</td>
					<td class="right"><span class="percent3-field"> 
					<span style="color:red">{{pt_msgerr}}</span>
					<input  type="number"    class="form-control ng-pristine ng-valid mort"   ng-model="formdata.propertyTax"/>
					 </span></td>
						
				</tr>
				<tr>
					<td style="padding-bottom: 2%;" class="left">PMI:</td>
					<td class="right">
					<span style="color:red">{{pmi_msgerr}}</span>
					<input type="text" onkeypress="return numbersonly(event)" maxlength="6" class="form-control" placeholder="$ " ng-model="formdata.pmi" /></td>
				</tr>
				
				<tr>
					<td style="padding-bottom: 2%;" class="left">Property Type:</td>
					<td class="right">
					<span style="color:red">{{py_msgerr}}</span>
					<select class="form-control"
							ng-model="formdata.propertyType"
							ng-change="getHOA()">
							<!--  <option ng-selected="state.name == editBasicDetail.state " ng-repeat="state in states" ng-value="state.name">{{state.name}}</option> -->
							<!-- <option ng-selected="city==formdata.desired_locationcity"
								ng-repeat="loan in loans">{{ loan }}</option> -->
			                      <option>Townhouse</option> 
			                      <option>Singlefamily</option>     
					</select></td>
				</tr>
				<tr>
					<td style="padding-bottom: 2%;" class="left">HOA fee:</td>
					<td class="right">
					<span style="color:red">{{hf_msgerr}}</span>
					<input type="text" class="form-control" onkeypress="return numbersonly(event)" placeholder="$ " maxlength="6" ng-model="formdata.hoaFee"/></td>
				</tr>
				<tr>
					<td style="padding-bottom: 2%;" class="left">Maintenance:</td>
					<td class="right">
					<span style="color:red">{{m_msgerr}}</span>
					<input type="text"onkeypress="return numbersonly(event)" maxlength="6" class="form-control" placeholder=" $" ng-model="formdata.maintenance" /></td>
				</tr>
				<tr>
					<td style="padding-bottom: 2%;" class="left">Home Insurance:</td>
					<td class="right">
					<span style="color:red">{{hi_msgerr}}</span>
					<input type="text" onkeypress="return numbersonly(event)" maxlength="9" class="form-control" placeholder=" 0" ng-model="formdata.homeInsurance"/></td>
				</tr>
				
				<tr>
					<td style="padding-bottom: 2%;" class="left">Highest Tax Rate:</td>
					<td class="right"><span class="percent4-field"> 
					<!-- <span style="color:red">{{py_msgerr}}</span> -->
					<select class="form-control ng-pristine ng-valid" ng-model="formdata.highestTaxRate">
			          <option ng-repeat="highesttax in highesttaxs" ng-value="highesttax">{{highesttax}}</option> 
					</select></span></td>
				</tr>
                <tr ng-show="owner">
                    <td style="padding-bottom: 2%;" class="left">Property Value Excluding Land:</td>
                    <td class="right">
                    <span style="color:red">{{pl_msgerr}}</span>
                    <input type="text" onkeypress="return numbersonly(event)" maxlength="9" class="form-control"  ng-model="formdata.propertyLand"/></td>
                </tr>
                <tr ng-show="owner">
                    <td style="padding-bottom: 2%;" class="left">Rental Income:</td>
                    <td class="right">
                    <span style="color:red">{{ri_msgerr}}</span>
                    <input type="text" onkeypress="return numbersonly(event)" maxlength="9" class="form-control"  ng-model="formdata.rentalIncome"/></td>
                </tr >
                 <tr ng-show="owner">
                    <td style="padding-bottom: 2%;" class="left">Management Fee:</td>
                    <td class="right">
                    <span style="color:red">{{mf_msgerr}}</span>
                    <input type="text" onkeypress="return numbersonly(event)" maxlength="9" class="form-control"  ng-model="formdata.managementFee"/></td>
                </tr>
                <tr ng-hide="taxguest">
                    <td style="padding-bottom: 2%;" class="left">Filing Status:</td>
                    <td class="right">
                    <span style="color:red">{{py_msgerr}}</span>
                    <select class="form-control" ng-model="formdata.filingStatus">
                                  <option>Single</option> 
                                  <option>Married Filing Jointly</option>
                                  <option>Head of Household</option> 
                    </select></td>
                </tr>
				</table><br><br>
				<!-- <a href="" data-ng-click="showAdvanced()">Advanced</a> -->
				<div style="margin-top:2%;margin-bottom:2%;color:red;text-align: center;">{{mc_msgerr}}</div><br>
				<div  style="margin-top:2%;text-align: center;margin-left : -10%">
					<button  type="button" ng-click="calculate()" class="btn btn-primary">Calculate</button><br><br><br>
				</div>
		</div>
    	<div  class="col-sm-7" ><!-- ng-show="showResults"  -->
	    	<div class="row"><!-- ng-show="showResults" -->
	    		<div ng-show="showResults" class="col-sm-6">
	    			<table width="105%">
					<caption>Mortgage Payment Summary</caption>
					
					
					<tr>
						<th style="padding-bottom: 2%;" class="left">Loan pay-off date</th>
						<th style="padding-bottom: 2%;" class="right">PMI</th>
					</tr>
				
					<tr>
						<td style="padding-bottom: 2%;" class="left">{{loanpayoff}}</td>
						<td style="padding-bottom: 2%;" class="right"> <span> $ {{pmi | number:0}} </span></td>
					</tr>
					<tr>
						
						<th style="padding-bottom: 2%;" class="left">Others<br>(HOA+Maintenance+PMI+HI)</th>
						<th style="padding-bottom: 2%;" class="right">Monthly Mortgage</th>
						
						
					</tr>
					<tr>
						<td style="padding-bottom: 2%;" class="left"><span> $ {{others | number:0}} </span></td>
						<td style="padding-bottom: 2%;" class="right"><span> $ {{monthlymortgage | number:0}} </span></td>
					</tr>
					<tr>
						<th style="padding-bottom: 2%;" class="left">Total interest paid</th>
						<th style="padding-bottom: 2%;" class="right">Property Tax</th>
					</tr>
					<tr>
						<td style="padding-bottom: 2%;" class="left" id="totalinterest"><span>$ {{totalinterest | number:0}}</span></td>
						<td style="padding-bottom: 2%;" class="right"><span>$ {{proptax | number:0}}</span></td>
					</tr>
					<tr>
						<th style="padding-bottom: 2%;" class="left">Annual payment amount</th>
						<th style="padding-bottom: 2%;" class="right">Total of 360 payments<par></th>
					</tr>
					<tr>
						<td style="padding-bottom: 2%;" class="left"><span>$ {{exactAnual_morgage | number:0}} </span></td>
						<td style="padding-bottom: 2%;" class="right" id="tot_annual_exp"><span>$ {{totalAnualhouseExpense | number:0}} </span></td>
					</tr>
					<tr>
						<th style="padding-bottom: 2%;" class="left">Taxable Deduction</th>
						<th style="padding-bottom: 2%;" class="right">Tax Saving</th>
					</tr>
					<tr>
						<td style="padding-bottom: 2%;" class="left"><span>$ {{taxablededuction | number:0}}<span></td>
						<td style="padding-bottom: 2%;" class="right"><span>$ {{taxsaving | number:0}}<span></td>
					</tr>
					
					<tr ng-show="income">
                        
                        <th style="padding-bottom: 2%;" class="left">Additional Tax</th>
                        <th style="padding-bottom: 2%;" class="right">Income After Tax</th>
                    </tr >
                    <tr ng-show="income">
                        
                        <td style="padding-bottom: 2%;" class="left">$ {{additionaltax | number:0}}</td>
                        <td style="padding-bottom: 2%;" class="right"><span>$ {{incomeaftertaxes | number:0}}<span></td>
                    </tr>
                    <tr ng-show="income">
                        
                        <th style="padding-bottom: 2%;" class="left">Tax benefit</th>
                        <th style="padding-bottom: 2%;" class="right">Cost After Tax</th>
                    </tr>
                    <tr ng-show="income">
                        
                        <td style="padding-bottom: 2%;" class="left">$ {{taxbenefit | number:0}}</td>
                        <td style="padding-bottom: 2%;" class="right"><span>$ {{costaftertax | number:0}}<span></td>
                    </tr>
                    <tr>
                        
                        <th style="padding-bottom: 2%;" class="left">Interest Paid in the First Year</th>
                        <th style="padding-bottom: 2%;" class="right"></th>
                    </tr>
                    <tr>
                        
                        <td style="padding-bottom: 2%;" class="left"><span>$ {{Firstyearinterest1 | number:0}}<span> </td>
                        <td style="padding-bottom: 2%;" class="right"></td>
                    </tr>
					</table>
	    		</div >
	    	        <div  class="col-sm-6" > <!-- class="col-sm-6" -->
	    		        <div ng-show="showResults"  style="color:black;text-align:center;font-size:18px;">Loan Breakdown</div><!-- ng-show="showResults" -->
	    			     <div  id="ChartAccessAgesByCountD3" style="height:260px;" data-drilldown-destination="filelist_by_category" data-drilldown-key="atime"></div>
	    		 	 </div> 
	    		 	 
	    		 	 <nvd3 options="options" data="data"></nvd3>
    
    <!-- <br><a href="http://plnkr.co/edit/EVW5qF?p=preview" target="_blank">Simple data example</a><a href="http://krispo.github.io/angular-nvd3/" target="_blank" style="float: right;">See more</a> -->
	    	</div>   	
	    	
	    	<div ng-show="showResults" style="margin-top:3%;" class="row"><!-- ng-show="showResults" -->
	    		<div style="text-align:center;font-size: 18px;color:black;">Annual Amortization Schedule</div><br>
	    	<!---------------------slider------------------------------------------------>
	    	 <!--  <div style="margin-top:2%;text-align: center"> 
	    	 <input id="ex1"  ng-model="slider007"  type="text" data-slider-min="2017" data-slider-max="2046" data-slider-step="1" data-slider-value="2030" ng-mouseleave="sliderChange()" />
	    	  </div > -->
	    	<!---------------------slider------------------------------------------------>	
	    			 <!-- <button type="button" class="btn btn-primary">Annual Schedule</button>
	    			<button type="button" class="btn btn-primary">Monthly Schedule</button> -->
	    			<!-- <b>2016</b> <input id="ex2" type="text" class="span2" value="" data-slider-min="2016" data-slider-max="2099" data-slider-step="1" data-slider-value="[250,450]"/> <b>2099</b> -->
	    			<div  style="margin-top:2%;text-align: center;margin-left : -2%">
	    			<button type="button" ng-click="showTable()" ng-show="showButton" class="btn btn-primary">Show Amortization Schedule</button>
	    			<button type="button" ng-click="hideTable()" ng-show="hideButton" class="btn btn-primary">Hide Amortization Schedule</button>
	    			</div><br><br><br>
	    		
	    			<div ng-hide="Annual" >
	    			<table  style="margin-top:2%;"width="100%">
	    		  
	    			<tr>
	    				<th class="left">YEAR</th>
	    				<th>RATE</th>
	    				<th>MONTHLY Mortgage</th>
	    				<th>REMAINING Mortgage</th>
	    				<th class="right">HOME EQUITY</th>
	    			</tr>
	    			<!------slider--------------------------------------------------->  
	    		<!-- <tr ng-repeat="arr in remainingmortgage|limitTo:sliderValue"> -->
	    			<tr ng-repeat="arr in remainingmortgage">
	    				<td class="left">{{arr.year}}</td>
	    				<td>{{Interestrate.toFixed(2)}}%</td>
	    				<td><span>$ {{monthlymortgage | number:0}}</span></td>
	    				<td><span>$ {{arr.remmort.toFixed(0) | number:0}}</span></td>
	    				<td class="right"><span>$ {{arr.houseEquity.toFixed(0) | number:0}}</span></td>
	    			</tr>
                        
                   <!--  </div>    --> 
	    			
	    		
	    				 
	    		</table><br><br><br>
	    		
	    	</div>
  	</div>
  	</div>
</div>


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

<script>document.write('<base href="' + document.location + '" />');</script>
<!-- <link rel="stylesheet" href="style.css" /> -->
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.2/nv.d3.min.css"/>
 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.9/angular.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.6/d3.min.js" charset="utf-8"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.2/nv.d3.min.js"></script>
 <script src="https://rawgit.com/krispo/angular-nvd3/v1.0.6/dist/angular-nvd3.js"></script>
 <script>
 
 $(document).ready(function() {
    $(".mort").keydown(function (e) {
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
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
});
 </script>
</body>

</html>
