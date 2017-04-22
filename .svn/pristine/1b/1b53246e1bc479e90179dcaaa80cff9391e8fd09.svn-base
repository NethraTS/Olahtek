<!DOCTYPE html>
<html lang="en" ng-app="portfolio" ng-controller="prtfolioController" ng-init="load()">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Balagopalan" >
     
    <title>WEALTHSETTER</title>
    <!--  for Type ahead autocomplete -->
<script src="js/angular.min.js"></script>
<script src="js/ui-bootstrap-tpls-0.12.0.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.2/angular.min.js"></script> -->
<!-- <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"  type="text/css" > -->
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
	 <script src="js/PortfolioManagement.js"></script>	 
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css" type="text/css">

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
      .section-heading {
    color:black;
    
}
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
function onlyAlphabets(e,t) {
	var len=t.value.length;
            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
                if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) ||charCode==8||charCode==127 ||charCode==32)
                {
                	if(len==250)
                	{
                		if(charCode==8)
                		{
                			return true;	
                		}
                		else
                		{
                		alert('Only 25 characters allowed');
                		return false;
                		}	
                	}
                	else
                	{
                    return true;
                   }
                 }
                else
                    return false;
            }
            catch (err) {
                alert(err.Description);
            }
        }
function onlyNumbers(e,t) {
	var len=t.value.length;
            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
                if ((charCode >= 48 && charCode <= 57) ||charCode==8||charCode==127)
                {
                	if(len==250)
                	{
                		if(charCode==8)
                		{
                			return true;	
                		}
                		else
                		{
                		alert('Only 25 characters allowed');
                		return false;
                		}	
                	}
                	else
                	{
                    return true;
                   }
                 }
                else
                    return false;
            }
            catch (err) {
                alert(err.Description);
            }
        }

</script>
</head>

<body id="page-top" ng-cloak>

    <nav id="mainNav" style="color: #222;" class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="index.jsp">WealthSetter</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a class="page-scroll"  href="home.jsp">Home</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#">How it works</a>
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
									<li><a href="carLoanCalculator.jsp" >Car Lease Calculator</a></li>
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
                    <li><a href=# class="page-scroll" ng-click="deleteAllCookies()"><i class="fa fa-sign-out"></i> Logout</a>
                        </li>
                  <!---  <li>
                        <a class="page-scroll" href="#contact">Contact</a>
                    </li> ---->
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>



    <section  style="background-image: url(img/3.jpg);    background-size: cover;
    height: 100%;" class="bg-primary" id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1 text-center">
                <br></br><br></br>
                  <h1>Portfolio Management</h1>
                <br></br>
                
<p >In our planning process, we try to create customized portfolio that can match your risk appetite. We would like to ask you a few questions to have a better understand of your view of market risk. At the end of the questionnaire, we will give you our assessment of your risk profile. You can modify it if you prefer. However, keep in mind that, we are structuring our portfolio for long term planning purpose with the emphasis on low cost and simplicity, so even our most aggressive risk profile is not suitable for speculative trading or market-timing.</p>
                				                                    
              <br></br><br></br>    <a href="#services" style="background-color: #E65319;" class="btn btn-primary btn-xl page-scroll pull-right">Next</a>  
      
            </div>
            <div id="checkSession" class="modal fade">
    	</div>
        </div>
    </section>
<br></br>
<br></br>

    <section id="services" class="bg-primary">
        <div class="container">
        <div class="row">
                <div class="col-lg-10 col-lg-offset-1 text-center">
       	<span class="font-para-black" ng-show="show==1">
				   Do you feel comfortable reviewing the performance of a portfolio of stocks and bonds?
<div class="radio">
  <label style="font-size: 18px;"><input type="radio"   ng-model="firstQuestion" ng-value="6" style=" margin-top: 1%;">Very comfortable</label>
</div>
<div class="radio">
  <label style="font-size: 18px;"><input type="radio"  ng-model="firstQuestion" ng-value="5" style=" margin-top: 1%;">Somewhat comfortable</label>
</div>
<div class="radio">
  <label style="font-size: 18px;"><input type="radio"  ng-model="firstQuestion" ng-value="4" style="margin-top: 1%;">Not comfortable at all</label>
</div>
                  <a style="background-color: #E65319;"  ng-click="next()" class="btn btn-primary btn-xl page-scroll pull-right">Next</a>
        </span>
        
        <span class="font-para-black" ng-show="show==2">
				   In reviewing my portfolio, I would
<div class="radio">
  <label style="font-size: 18px;"><input type="radio" ng-model="secondQuestion" ng-value="2"  style=" margin-top: 1%;">focus on investments that have lost money.</label>
</div>
<div class="radio">
  <label style="font-size: 18px;"><input type="radio" ng-model="secondQuestion" ng-value="6"  style=" margin-top: 1%;">equally focus on investment that have lost or gained money.</label>
</div>
<div class="radio">
  <label style="font-size: 18px;"><input type="radio" ng-model="secondQuestion" ng-value="4"  style=" margin-top: 1%;">focus on investments that have gained money.</label>
</div>
<a style="background-color: #E65319;"  ng-click="next()" class="btn btn-primary btn-xl page-scroll pull-right">Next</a>
<a style="background-color: #E65319;"  ng-click="back()" class="btn btn-primary btn-xl page-scroll pull-left">Back</a>
        </span>
        
        <span class="font-para-black" ng-show="show==3">
				   In our recommended portfolio, we utilize exchange traded funds (ETFs) exclusively because of their market coverage, low cost, and easy to trade. How familiar are you with ETFs? (you can find out more information following this link)
<div class="radio">
  <label style="font-size: 18px;"><input type="radio" ng-model="thirdQuestion" ng-value="6"  style=" margin-top: 1%;">Very familiar (I know more than a half dozen popular ETFs)</label>
</div>
<div class="radio">
  <label style="font-size: 18px;"><input type="radio" ng-model="thirdQuestion" ng-value="5"  style="   margin-top: 1%;">Familiar (Though I have not traded, or know many ETFs, I know what they and know who are the sponsors of some of the most popular ETFs)</label>
</div>
<div class="radio">
  <label style="font-size: 18px;"><input type="radio" ng-model="thirdQuestion" ng-value="4" style=" margin-top: 1%;">Not very familiar (I know what is an ETF, but that's pretty much the limit of my knowledge.)</label>
</div>
<div class="radio">
  <label style="font-size: 18px;"><input type="radio" ng-model="thirdQuestion" ng-value="3" style="    margin-top: 1%;">Unfamiliar (What is ETF?)</label>
</div>
<a style="background-color: #E65319;"  ng-click="next()" class="btn btn-primary btn-xl page-scroll pull-right">Next</a>
<a style="background-color: #E65319;"  ng-click="back()" class="btn btn-primary btn-xl page-scroll pull-left">Back</a>
        </span>
        
        
<span class="font-para-black" ng-show="show==4">
				   Market is often volatile. If your investments loss 20% of its value in a month, what would you do?
<div class="radio">
  <label style="font-size: 18px;"><input type="radio" ng-model="fourthQuestion" ng-value="1" style=" margin-top: 1%;">sell all and keep everything in cash to be safe</label>
</div>
<div class="radio">
  <label style="font-size: 18px;"><input type="radio" ng-model="fourthQuestion" ng-value="2" style=" margin-top: 1%;">sell some to reduce the risk</label>
</div>
<div class="radio">
  <label style="font-size: 18px;"><input type="radio" ng-model="fourthQuestion" ng-value="5" style="margin-top: 1%;">do nothing, just stick to the investment plan</label>
</div>
<div class="radio">
  <label style="font-size: 18px;"><input type="radio" ng-model="fourthQuestion" ng-value="8" style="   margin-top: 1%;">buy more to take advantage of the low prices</label>
</div>
<a style="background-color: #E65319;"  ng-click="submitPortfolio1()" class="btn btn-primary btn-xl page-scroll pull-right">Submit</a>
<a style="background-color: #E65319;"  ng-click="back()" class="btn btn-primary btn-xl page-scroll pull-left">Back</a>
        </span>
        

                       
              </div>
       </div>
  	</div>                 
</section> 

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
    

 <!-- Footer -->
 
 
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <br></br>
                    <p style="color:white;">Copyright &copy; WealthSetter 2017. All Rights Reserved</p>
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

</body>

</html>
