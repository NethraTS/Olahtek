<!DOCTYPE html>
<html lang="en" >
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
<body id="page-top" ng-app="formApp1" ng-controller="formController1">
<!-- 
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
				<a class="navbar-brand page-scroll" href="home.jsp">WealthSetter</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a class="page-scroll" href="home.jsp">Home</a>
					</li>
					<li><a class="page-scroll" href="#">How it works?</a></li>
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
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Case Studies <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="CaseStudy1_home.jsp">Case Study 1</a></li>
                            <li><a href="CaseStudy2_home.jsp">Case Study 2</a></li>
                            <li><a href="CaseStudy3_home.jsp">Case Study 3</a></li>
						</ul>
					</li>
					
					<li>
                        <a class="page-scroll" href="#portfolio" data-toggle="modal" ng-click="clear()" data-target="#myModal">Sign In</a>
                    </li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<section style="background-image: url('img/CaseStudy3.jpg'); background-size: cover; height: 100%;" class="bg-casestudy" style="" id="about">
		<div class="container">
			<div class="row">
			<div class="col-lg-6 col-lg-offset-1 text-center"></div>
			<div class="col-lg-6 col-lg-offset-1 text-center"></div>
			<div class="col-lg-4 col-lg-offset-1 text-center">
				<h1 style="color: black; font-size: 65px;">Case Study Three - Martha, a single mom in her 50s</h1>
				
				<p style="color:black;">

Background: Martha divorced 18 years ago. She lives in Minneapolis with her daughter, Hannah, who is attending a local college right now. Martha struggled financially in the years following her divorce as she tried to support herself and her young daughter. She worked very hard but was not able to save much in those years. Her financial situation has improved gradually since she started working at the head quarter of a large retail company as an administrative assistant ten years ago. She has seen her salary gradually increase and is now making $56,000 a year. With less than $50,000 saved in her retirement accounts, Martha worries if she can save enough for her retirement years.
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
Income: Martha has worked at several divisions within the company and her bosses all like her, so she expects she will be able to retire from her job. However, because of her education and age, she doesn't think she will have any real raise in salary.
Her company has a very generous matching plan for its 401(k) plan. The company matches 100% the employee's first 5% contribution. Martha has been utilizing this benefit to build up her retirement savings and will continue to do so.
Expenses: Martha expect she can lower her non-housing expenses from $2,500 to $1,500 a month after Hannah graduates in two years.
</p>
<p style="color: black;">
Life-events:
-Martha hopes to retire at her full retirement age of 66.
</p>
<p style="color: black;">
Things that Martha told the planner:
name: Martha
age: 52
number of kids: 1
name of first kid: Hannah
age: 19
current income: $56,000 per year
currently renting
rent: $1,200 a month
other expenses: $2,500 (not including income taxes)
expenses in two years: $1,500
</p>
<p style="color: black;">
Assets:
Savings: $2,000
Taxable investments: $0
Retirement accounts: $46,000
</p>
<p style="color: black;">
Financial goals that Martha specified:
-college education: Martha wants to include the costs for Hannah's remaining two years in college.
-retirement: Martha wants to retire at here full retirement age of 66.</p>
<p style="color: black;">
Adjustments made by the planner**:
-taxes for different status during planning period (head-of-household w/ kid, single - after Hannah graduates from college, and during retirement years)
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
	<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" ng-click="clear()">&times;</button>
          <h4 class="modal-title">Sign in</h4>
        </div>
<!-- ---------------------------------------                Log in              -------------------------- -->        
        <div class="modal-body">
          
          
          <form ng-submit="processForm()">
		<div ng-show="show1>0&&show1<2">
		<!-- NAME --> <div style="color:red">{{message}}</div>
		<div id="name-group" class="form-group" ng-class="{ 'has-error' : errorName }">
			<label>Email</label>
			<input type="text" name="user" class="form-control"  ng-keypress="checkloginform()"   placeholder="" ng-model="formDatalogin.name" >
                        <span class="help-block" ng-show="errorName">{{ errorName }}</span>
		</div>

		<!-- SUPERHERO NAME -->
		<div id="superhero-group" class="form-group" ng-class="{ 'has-error' : errorSuperhero }">
			<label>Password</label>
			<input type="password" name="pass" class="form-control" ng-keypress="checkloginformemail()" placeholder="" ng-model="formDatalogin.password">
			<span class="help-block" ng-show="errorSuperhero">{{ errorSuperhero }}</span>
		</div>
		<button type="submit" class="btn btn-success pull-left" ng-model="formData.login"  ng-click="login()">
			 Log In!
		</button>
		<button type="button" class="btn btn-danger pull-right"  ng-model="formData.forgotPassword" ng-click="forgot()">
			 Forgot Password!
		</button>
		</div>
		<div ng-show="show1>1">
		<!-- NAME --> <div style="color:red">{{forgotPasswordResponse}}</div>
		<div id="name-group" class="form-group" ng-class="{ 'has-error' : errorNameForgotPassword }">
			<label>Email</label>
			<input type="text" name="forgotEmail" class="form-control"  ng-keypress="checkForgotPaswwordForm()"  placeholder=" " ng-model="formDatalogin.forgotMail">
                        <span class="help-block" ng-show="errorNameForgotPassword">{{ errorNameForgotPassword }}</span>
		</div>
		<button type="submit" class="btn btn-success pull-right"  ng-click="loginBack()">
			 Log In!
		</button>
		<button type="Submit" class="btn btn-danger pull-left"  ng-click="submitEmail()">
			 Forgot Password!
		</button>
		</div>
		
		
		
		<!-- SUBMIT BUTTON -->
		
	
	</form>
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


</body>
</html>
