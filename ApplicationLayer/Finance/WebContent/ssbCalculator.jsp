<!DOCTYPE html>
<html lang="en" ng-app="calculator">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Balagopalan">
<title>WEALTHSETTER</title>
<link rel="stylesheet" href="css/nv.d3-1.8.3.min.css"/>
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
	<!-- <script src="https://code.jquery.com/jquery-2.0.2.js"></script>
	<script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script> -->

<!-- LOAD ANGULAR -->

<script src="js/angular.min.js"></script>
<script src="js/app.js"></script>
<!-- <script src="js/dashboard.js"></script>
<script src="js/userProfile.js"></script> -->
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

		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>   
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
						<script type="text/javascript">
$('#ddt').ready(function() {
 $('#ddt').datetimepicker({ format: "MM/DD/YYYY" }); 
});

$('#ddt1').ready(function() {
	 $('#ddt1').datetimepicker({format: "MM/DD/YYYY"}); 
});
</script>
	<!-- <script>
	$('.right').datepicker({
	    autoclose: true
	});

	$('.right').on('show', function(e){
	    console.debug('show', e.date, $(this).data('stickyDate'));
	    
	    if ( e.date ) {
	         $(this).data('stickyDate', e.date);
	    }
	    else {
	         $(this).data('stickyDate', null);
	    }
	});

	$('.right').on('hide', function(e){
	    console.debug('hide', e.date, $(this).data('stickyDate'));
	    var stickyDate = $(this).data('stickyDate');
	    
	    if ( !e.date && stickyDate ) {
	        console.debug('restore stickyDate', stickyDate);
	        $(this).datepicker('setDate', stickyDate);
	        $(this).data('stickyDate', null);
	    }
	});</script>
	 -->
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

.row-centered {
    text-align:center;
}

nvd3 svg{
padding-left: 45px;
margin-top : 3%;
}
.col-centered {
    display:inline-block;
    float:none;
    /* reset the text-align */
    text-align:left;
    /* inline-block space fix */
    margin-right:-4px;
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
	width : 50%;	
}
.right
{
	padding-right : 2%;		
	width : 50%;
}
 caption
 {
 	text-align:center;
 	color: black;
 	font-size: 18px;
 	margin-bottom: 5%;
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
/* tr:nth-child(even) {background: #FFF}
tr:nth-child(odd) {background: rgba(102, 152, 255, 0.52)} */
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
					<li><a class="page-scroll" href="{{homeLocation}}">Home</a>
					</li>
					<li><a class="page-scroll" href="#">How it works?</a></li>
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
                            <li><a href="{{caseStudy1}}">Case Study 1</a></li>
                            <li><a href="{{caseStudy2}}">Case Study 2</a></li>
                            <li><a href="{{caseStudy3}}">Case Study 3</a></li>
						</ul>
					</li>
					<li ng-show="userFlag">
						<!--   <a class="page-scroll" href="Report.jsp">Reports</a> --> <a
						href="#" ng-click="report()">Reports</a>
					</li>


					<li ng-show="userFlag"><a class="page-scroll" href="#" ng-click="checkSave()"><i
							class="fa fa-user-plus"></i> My Profile</a></li>

					<li ng-show="userFlag"><a href=# class="page-scroll"
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
	
	  	<!-- <div class="row">
	  		
	    	<div class="col-sm-5"> -->
	    	<p ng-show="showMessageReg" align="center" style="margin-left:25%;margin-right:25%;font-size: 11px;text-align:center;margin-top:2%;">AIME is the average indexed monthly earnings that SSA used to calculate your benefits. Enter your current monthly income if you do not know your AIME. You can also sign-in and create an income profile and let us calculate your benefits based on your income projection.</p>
			    <table border="0"  width="50%" align="center">
				<caption>Social Security Benefits</caption>
				
				<tr ng-show="showIncomeProfileRadio">
					<td class="left">Do you want to use a saved income profile? </td>
					<td class="right">
						<label><input type="radio" ng-change="RadioChangeIncome('Yes')" ng-model="formdata.income" value="ShowIncome" name="optradio2">Yes</label>
						<label><input type="radio" ng-change="RadioChangeIncome('No')" ng-model="formdata.income" value="HideIncome" name="optradio2">No</label>
					</td>
				</tr>
				
				<tr ng-show="formdata.income == 'ShowIncome'">
					<td class="left">Select an Income Profile</td>
					<td class="right">
						<select class="form-control" ng-model="formdata.income_prof" ng-change="changeIncome()">
							<option 
							ng-repeat="income in incomeProfile" ng-value="{{income}}">{{ income }}</option>
						</select>
					</td>
				</tr>
				<tr ng-show="selectedFromIncome">
					<td class="left">Birth Year</td>
					<td class="right">

					<!-- <input class="form-control" id= "ddt"  data-format="dd/MM/yyyy"> -->
						<input ng-show="showUserBirth" id="text" class="form-control" placeholder="YYYY" ng-model="formdata.dob" readonly/>
						<!-- <input ng-hide="showUserBirth" id="ddt" class="form-control" placeholder="MM/dd/YYYY" ng-model="formdata.dob" ng-change="userDOBChange()"/> -->
						<input type="text" ng-hide="showUserBirth" onkeypress="return numbersonly(event)" maxlength="4"  class="form-control" placeholder="YYYY" ng-model="formdata.dob" ng-change="userDOBChange()"/>
					</td>
				</tr>
				<tr ng-show="formdata.income == 'HideIncome'">
					<td class="left">Do you know your AIME?</td>
					<td class="right">
						<label><input type="radio" ng-change="RadioChangeUserAIME('Yes')" ng-model="formdata.useraime" value="Yes" name="useraime">Yes</label>
						<label><input type="radio" ng-change="RadioChangeUserAIME('No')" ng-model="formdata.useraime" value="No" name="useraime">No</label>
					</td>
				</tr>
				<tr ng-show="showLabelUser" >
					<td class="left">{{ userLabel }}</td>
					<td class="right">
						<input type="text" onkeypress="return numbersonly(event)" maxlength="15" class="form-control" ng-change="hideMessages()" placeholder="$" ng-model="formdata.annaul_income" />
					</td>
				</tr>
				<tr ng-show="selectedFromIncome">
					<td class="left">Retirement Age</td>
					<td class="right">
						<select class="form-control" ng-change="hideMessages()" ng-model="formdata.ret_age">
							<option ng-selected="defaultUserRetAge == age"
							ng-repeat="age in retirementAge" ng-value="age">{{ age }}</option>
						</select>
					</td>
				</tr>
				<tr ng-show="selectedFromIncome">
					<td class="left">Marital Status</td>
					<td class="right">
						<label><input type="radio" ng-change="RadioChangeMarital('Single')" ng-model="formdata.martial" value="Single" name="optradio">Single</label>
						<label><input type="radio" ng-change="RadioChangeMarital('Married')" ng-model="formdata.martial" value="Married" name="optradio">Married</label>
					</td>
				</tr>
				<tr ng-show="showThis3"><!-- formdata.martial == 'Married' && formdata.income == 'HideIncome' || showThis1 -->
					<td class="left">Is Spouse Working?</td>
					<td class="right">
						<label><input type="radio" ng-change="RadioChange1('Working')" ng-model="formdata.spousework" value="Yes" name="optradio1">Yes</label>
						<label><input type="radio" ng-change="RadioChange1('Not Working')" ng-model="formdata.spousework" value="No" name="optradio1">No</label>
					</td>
				</tr>
				<tr ng-show="(showThis || showThis4) && selectedFromIncome">
					<td class="left">Spouse Birth Year</td>
					<td class="right">
						<input ng-show="showSpouseBirth" type="text" class="form-control" placeholder="YYYY" ng-model="formdata.spousedob"  readonly/>
						<!-- <input ng-hide="showSpouseBirth" id="ddt1" class="form-control" placeholder="MM/dd/YYYY" ng-model="formdata.spousedob"  ng-change="spouseDOBChange()" /> -->
						<input type="text" ng-hide="showSpouseBirth" onkeypress="return numbersonly(event)" maxlength="4"  class="form-control" placeholder="YYYY" ng-model="formdata.spousedob" ng-change="spouseDOBChange()"/>
					</td>
				</tr>
				<tr ng-show="showThis">
					<td class="left">Do you know your spouse AIME?</td>
					<td class="right">
						<label><input type="radio" ng-change="RadioChangeSpouseAIME('Yes')" ng-model="formdata.spouseaime" value="Yes" name="spouseaime">Yes</label>
						<label><input type="radio" ng-change="RadioChangeSpouseAIME('No')" ng-model="formdata.spouseaime" value="No" name="spouseaime">No</label>
					</td>
				</tr>
				<tr ng-show="showLabelSpouse" >
					<td class="left">{{ spouseLabel }}</td>
					<td class="right">
						<input type="text"  onkeypress="return numbersonly(event)" maxlength="15" class="form-control" placeholder="$" ng-change="hideMessages()" ng-model="formdata.spouseannaul_income" />
					</td>
				</tr>

				<tr ng-show="(showThis || showThis2) && selectedFromIncome">
					<td class="left">Spouse Retirement Age</td>
					<td class="right">
						<select class="form-control" ng-change="hideMessages()" ng-model="formdata.spouseret_age">
							<option  ng-selected="defaultSpouseRetAte == age1"
							ng-repeat="age1 in retirementAge" ng-value="age">{{ age1 }}</option>
						</select>
					</td>
				</tr>
				<!-- <tr>
					<td><a href="" data-ng-click="">Advanced</a></td>
				</tr> -->
				<!-- <tr>
					<td class="left">Annual General Inflation</td>
					<td class="right"><input type="text" class="form-control" placeholder="$" ng-model="formdata.homeValue" /></td>
				</tr> -->
				</table>
				<div style="margin-top:2%;margin-bottom:2%;color:red;text-align: center;">{{errormsg}}</div>
				<div style="margin-top:5%;text-align: center;">
					<button type="button" ng-click="ssb(formdata)" class="btn btn-primary">Calculate</button>
				</div>
		<!-- </div>
    	<div class="col-sm-7"> -->
    		
 	<div ng-show="userSSB" style="margin-top:5%;text-align:center"><b>You will receive &#36;{{userssb}} monthly social security benefits at age {{ formdata.ret_age }}.</b></div>
 	<div ng-show="spouseSSB2" style="margin-top:5%;text-align:center"><b>Your spouse will receive &#36;{{spousessb}} monthly social security benefits at age {{ formdata.spouseret_age }}.</b></div>
 	<div ng-show="spouseSSB3" style="margin-top:5%;text-align:center"><b>Your spouse will receive &#36;{{spousessb}} monthly social security benefits.</b></div>
 	<div ng-show="spouseSSB4" style="margin-top:5%;text-align:center"><b>Since spouse SSB is going negative, Your spouse will receive &#36;{{spousessb}} (half of user SSB) monthly social security benefits {{ formdata.spouseret_age }}</b></div>
 	<!-- <div ng-show="spouseSSB5" style="margin-top:5%;text-align:center"><b>Since spouse SSB is less than half of user SSB, Spouse will receive {{spousessb}} (half of user SSB) in annual social security payments at age {{ formdata.spouseret_age }}</b></div> -->
	<div ng-show="userNegative" style="margin-top:5%;text-align:center"><b>User SSB is going negative at ages {{ userNegativeShow.toString() }}.</b></div>
	<div ng-show="spouseNegative" style="margin-top:5%;text-align:center"><b>Spouse SSB is going negative at ages  {{ spouseNegativeShow.toString() }}.</b></div>
  	<nvd3 ng-show="showChart" options="optionsSSB" data="dataSSB" ></nvd3> 
  	
  	<!-- <div style="margin-top:5%"" class="row row-centered" ng-show="userSSB">
	<div align="center" style="margin-bottom: 3%;font-size: 170%;">User SSB</div>
	  	<div align="center" ng-show="showUser62" class="col-sm-4 col-centered" >
  			<div style="background: rgba(102, 152, 255, 0.52);text-align: center;"><b>SSB at age : 62</b></div>
  			<div style="text-align: center;padding-top: 2%"><b>{{user62}}</b></div>
	  	</div>
	  	
	  	<div align="center" ng-show="showUser66" class="col-sm-4 col-centered" >
  			<div style="background: rgba(102, 152, 255, 0.52);text-align: center;"><b>SSB at age : 66</b></div>
  			<div style="text-align: center;padding-top: 2%"><b>{{user66}}</b></div>
	  	</div>
	
	  	<div align="center" ng-show="showUser70" class="col-sm-4 col-centered" >
  			<div style="background: rgba(102, 152, 255, 0.52);text-align: center;"><b>SSB at age : 70</b></div>
  			<div style="text-align: center;padding-top: 2%"><b>{{user70}}</b></div>
	  	</div>
	</div> -->

	
  	<!-- <div style="margin-top:5%"" class="row row-centered" ng-show="spouseSSB">
  		
  	<div align="center" style="margin-bottom: 3%;font-size: 170%;">Spouse SSB</div>
	  	<div ng-show="showSpouse62" class="col-sm-4 col-centered" >
  			<div style="background: rgba(102, 152, 255, 0.52);text-align: center;"><b>SSB at age : 62</b></div>
  			<div style="text-align: center;padding-top: 2%"><b>{{spouse62}}</b></div>
	  	</div>

	  	<div ng-show="showSpouse66" class="col-sm-4 col-centered" >
  			<div style="background: rgba(102, 152, 255, 0.52);text-align: center;"><b>SSB at age : 66</b></div>
  			<div style="text-align: center;padding-top: 2%"><b>{{spouse66}}</b></div>
	  	</div>

	  	<div ng-show="showSpouse70" class="col-sm-4 col-centered" >
  			<div style="background: rgba(102, 152, 255, 0.52);text-align: center;"><b>SSB at age : 70</b></div>
  			<div style="text-align: center;padding-top: 2%"><b>{{spouse70}}</b></div>
	  	</div> -->
	<!-- </div>
</div> -->
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

	<footer style="margin-top:5%" class="footer">
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

	<script type="text/javascript" src="assets/js/d3.v2.js"></script> 
	<script src="assets/js/D3-3.5.6.min.js" charset="utf-8"></script>  
	<!-- <script src="assets/js/nvd3-1.8.3.min.js"></script> -->
	<script src="assets/js/nv.d3.js"></script>
	<script src="assets/js/angular-nvd3.js"></script>
</body>
</html>
