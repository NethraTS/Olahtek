<!DOCTYPE html>
<html lang="en" ng-app="formApp3">
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
<script type="text/javascript" src="http://mbostock.github.com/d3/d3.js?2.4.5"></script>
   <script type="text/javascript" src="http://static.fusioncharts.com/code/latest/themes/fusioncharts.theme.ocean.js"></script>
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
<!-- <script src="js/app.js"></script> -->
<!-- <script src="js/dashboard.js"></script> -->
<script src="js/federalicometaxcal.js"></script>
<!-- <script src="js/userProfile.js"></script> -->
<!-- <script src="js/calculators.js"></script> -->
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
	<script type="text/javascript" src="http://mbostock.github.com/d3/d3.js?2.4.5"></script>
	
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
	width:25%;
	
}
.right
{
	padding-right : 2%;
	width:25%;	
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

	<!-- <div class="MaskLayer" ng-class="{isClosed : !masked}">

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
	</div> -->


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
					<li><a class="page-scroll" href="dashboardUser0.jsp"> <span class="glyphicon glyphicon-home"></span>&nbsp Home</a>
					</li>
					<li><a class="page-scroll" href="#"> <span class="glyphicon glyphicon-question-sign"></span>&nbsp How it works?</a></li>
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
							<li><a href="ssbCalculator.jsp">Social Security Benefits</a></li>
							<li><a href="FderalincomeTaxCalculator.jsp">Income Tax Calculator</a></li>
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
						<a ng-hide="guest" href="#" ng-click="report()"> <span class="fa fa-file-text" aria-hidden="false" style="font-size:12px;"></span>&nbsp Reports</a>
					</li>


					<li><a ng-hide="guest" class="page-scroll" href="#" ng-click="checkSave()"><i
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
	<div style="height:10%" align="center"></div>
	<div class="container-fluid">
	  	<div class="row">
	  	
	    	  <div class="col-sm-7" style="margin-right:-6%" >
			    <table border="0" width="100%">
				<caption>Income Tax Calculator </caption>
				<tr>
					<td class="left"style="padding-bottom: 2%">State:</td>
					<td class="right">
						<select class="form-control" ng-model="formdata.state"  
						typeahead-on-select="autocomplete()"  onkeypress="return onlyNumbers(event,this)"typeahead="state for state in states | filter:$viewValue | limitTo:8">
							<option ng-selected="state.name == editBasicDetail.state " 
							ng-repeat="state in states" ng-value="state.name">{{state.name}}</option>
						</select>
					</td>
					<td class="left" style="padding-bottom: 2%">Filling Status:</td>
					<td class="right">
						
								 <select
									ng-model="formdata.filingStatus" class="form-control"
									ng-change="update2()" >
									<option
										ng-selected="filingOptions.id == editBasicDetail.filingStatus"
										ng-repeat="filingOptions in filingOptions"
										ng-value="filingOptions.id">{{filingOptions.id}}</option>

								</select>
								</td>
				</tr>
			
				<tr >
					<td class="left" background=" #2ECCFA" style="padding-bottom: 2%">Gross Annual Income:</td>
					<td class="right" >
					 <input type="text" class="form-control" placeholder=""  maxlength="15" ng-model="formdata.homeValue" ng-change="update4()" onkeypress="return numbersonly(event,this)"  />
					</td>

					<td class="left"style="padding-bottom: 2%">Personal Exemption :</td>
					<td class="right">
						<input type="text"  class="form-control"  maxlength="3" placeholder="" ng-model="formdata.personalexe" ng-change="update3()" onkeypress="return numbersonly(event,this)"/>
					</td>
				</tr>
			
				</table>
				
			<br>
			
			<div style="margin-top:2%;text-align:center;margin-bottom:2%;color:red;">{{errormsg}}</div>
			 <div align="center">
					 <button  type="button" class="btn btn-primary"  ng-click="calculate();">Calculate</button>
				</div>
				  <div style="margin-top:2%;" align="center" >
					
                  <!--  <span> <h4 >Your Federal Income Tax Amount = {{TotalFDI}}</h4> </span>	 -->
                   <span style='font-weight: bold;font-size:14px;'>Your Total Taxes = {{TotalIncomeTaxes}}</span>
				</div>
				
				</div>
			</div>
				</div>
				<div style="height:4%"></div>
	<div class="container-fluid">
	  	<div class="row">
	    	 <div class="col-sm-6">
			    <table border="0" width="100%">
				<caption> Your Income Taxes </caption>
				<tr>
	    				<th class="left">Tax Type</th>
	    				
	    				<th class="right">Tax Amount</th>
	    			</tr>
	    			<tr>
	    				<td class="left"> Federal</td>
	    				
	    				<th class="right">{{federalTax}}</th>
	    				</tr> <tr>
	    				<td class="left">State</td>
	    				
	    				<th class="right">{{stateTax}}</th>
	    				</tr>
	    				<tr>
	    				<td class="left"> FICA</td>
	    				
	    				<th class="right">{{fICASocialSecurityTax}}</th>
	    				</tr><tr>
	    				<td class="left"> Medicare</td>
	    				
	    				<th class="right">{{fICAMedicareTax}}</th>
	    				</tr>
	    				<tr>
	    				<td class="left"> Total Income Taxes</td>
	    				 
	    				<th class="right">{{TotalIncomeTaxes}}</th>
	    				</tr><tr>
	    				<td class="left"> Income After Taxes</td>
	    				
	    				<th class="right">{{incomeAfterTaxes}}</th>
	    				</tr>
	    				
	    				</table>
	    				
	    				</div>
	    				<div class="col-sm-6" align="center"  style="margin-top:-10%">
	    			<!-- <div id="chart-container">FusionCharts will render here</div> -->
	    			<div style="color:black;text-align:center;font-size:18px;" >Total Estimated Tax Burden </div>
	    			<div id="ChartAccessAgesByCountD3" style="height:140px;" data-drilldown-destination="filelist_by_category" data-drilldown-key="atime"></div>
	    			
	    			<br><br><br><br><br><br><br> 
	    			<div style="color:black;text-align:center;font-size:18px;"> Total estimated Tax Burden {{TotalIncomeTaxes}}</div>
	    		</div>
	  	
	    	
	    	        </div>
	    				</div>
	   <br><br><br><br>	
	 
	    			
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
	
	    	
	
</body>
</html>