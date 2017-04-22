<!DOCTYPE html>
<html lang="en">

<head>
<script src="js/angular.min.js"></script>
<script src="js/app.js"></script>
<script src="js/cbpAnimatedHeader.js"></script>
<script src="js/classie.js"></script>
<script src="js/creative.js"></script>
<script src="js/jquery.easing.min.js"></script>
<script src="js/jquery.fittext.js"></script>
<script src="js/jquery.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/wow.min.js"></script>	
<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
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
               // if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) ||charCode==8||charCode==127)
                {
//                	if(len==250)
                	{
                		if(charCode==32)
                		{
                			return false;	
                		}
                		else
                		{
                //		alert('Only 25 characters allowed');
                		return true;
                		}	
                	}
                //	else
                	{
                  //  return true;
                   }
                 }
                //else
                    //return false;
            }
            catch (err) {
                alert(err.Description);
            }
        }
function onlyAlphabetsUsername(e,t) {
	var len=t.value.length;

            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
                if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode==8 || charCode==9 || charCode==127 || charCode==32)
                {
        			return true;	
                }
                	else
                	{
              return false;
                		}	
                	}
               	
            catch (err) {
                alert(err.Description);
            }
        }
</script>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Balagopalan" >

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
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css" type="text/css">

    <!-- Plugin CSS -->
    <link rel="stylesheet" href="css/animate.min.css" type="text/css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/creative.css" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">

<style type="text/css">
html {
overflow: hidden;
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
</style>
</head>


</style>
</head>

<body  ng-app="formApp1" ng-controller="formController1" id="page-top" ng-init="load()" ng-cloak>

    <nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" >WealthSetter</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a class="page-scroll" href="#">Home</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#">How it works</a>
                    </li>
                    
					
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Resources <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="mortgageCalculator.jsp">Mortgage Calculator</a></li>
                            <!-- data-toggle="modal" data-target="#caseStudy" -->
                            <li class="dropdown dropdown-submenu"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Car Calculator</a>
								<ul class="dropdown-menu">
									<li><a href="carLoanCalculator.jsp" >Car Loan Calculator</a></li>
									<li><a href="carLeaseCalculator.jsp">Car Lease Calculator</a></li>
								</ul>
							</li>
							<li><a href="ssbCalculator.jsp">Social Security Benefits</a></li>
							<li><a href="FderalincomeTaxCalculator.jsp">Income Tax Calculator</a></li>
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
                  <!---  <li>
                        <a class="page-scroll" href="#contact">Contact</a>
                    </li> ---->
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <header>
        <div class="header-content">
            <div class="header-content-inner">
                <h1>Start Your Financial Planning</h1>
                <hr>
                <p>WealthSetter can help you plan your financial goals in a better way!</p>
                <a href="#about" style="background-color: #E65319;" class="btn btn-primary btn-xl page-scroll">Start Here</a>
            </div>
        </div>
    </header>

    <section class="bg-primary" id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1 text-center">
                    <h2 style="color:#2ECCFA;" class="section-heading">Welcome to WealthSetter!</h2>
                    <hr class="light">
                    <p class="text-faded">We are going to ask you a few questions in the following page in order to collect the most basic information about yourself to create a tailored financial plan for you. Remember, you can always use the menu bar on the left to review and refine your data. First, please create an account:</p>
                 </div>
                  <div class="col-lg-6 col-lg-offset-3 text-center">
                    <form name="sentMessage" id="contactForm" novalidate>
                        <div class="row control-group">

			<span style="color:red" > {{ errorNameregister }}</span>
			<br></br>
			<input type=hidden ng-model="formData.form" value="form1" ng-init="formData.form='Userform'" >
                         <label > FIRST NAME :</label>       <input type="text" class="form-control" name="username" placeholder="Name" onkeypress="return onlyAlphabetsUsername(event,this)"  ng-click="usercheck()" ng-model="formData.username" required ><span style="color:red" ng-show="sentMessage.username.$dirty && sentMessage.username.$invalid">
                        
		</span> </div>
			 <div class="row control-group">
			 <label > LAST NAME :</label>       <input type="text" class="form-control" name="username" placeholder="Last Name" onkeypress="return onlyAlphabetsUsername(event,this)"  ng-click="usercheck()" ng-model="formData.userLname" required ><span style="color:red" ng-show="sentMessage.username.$dirty && sentMessage.username.$invalid">
                                <p class="help-block text-danger"></p>
                       <!--      </div> -->
                       </span>
                       </div>
                        <div class="row control-group">
                        <label > E-MAIL :</label>    
                                 <input type="text" autocomplete="off" onkeypress="return onlyAlphabets(event,this)" placeholder="E-Mail" class="form-control"  ng-keypress="emailcheck()"   name="email" ng-click="usernamecheck()" ng-model="formData.email"  >
                                <p class="help-block text-danger"><span style="color:red" ng-show="sentMessage.email.$dirty && sentMessage.email.$invalid">
			
			</span></p>
                       
                        </div>
                        <div class="row control-group">
                           <!--  <div class="form-group col-xs-12 floating-label-form-group controls"> -->
                             <label > PASSWORD :</label>    
                             <input type="password" data-toggle="tooltip" title="Password should have atleast 8 characters consists of lower case, upper case and number, e.g. Example123" onkeypress="return onlyAlphabets(event,this)" class="form-control" placeholder="Password" name="password" ng-keypress="errorclean()"  ng-click="passworderror()"ng-model="formData.password" required ><span style="color:red" ng-show="sentMessage.password.$dirty && sentMessage.password.$invalid">
			              <!--    <span ng-show="sentMessage.password.$error.required">Password is required</span> --></span>
                                <p class="help-block text-danger"></p>
                            <!-- </div> -->
                        </div>

                        <div class="row control-group">
                           <!--  <div class="form-group col-xs-12 floating-label-form-group controls"> -->
                             <label > CONFIRM PASSWORD :</label>    
                             <input type="password" data-toggle="tooltip" title="Password should have atleast 8 characters consists of lower case, upper case and number, e.g. Example123" onkeypress="return onlyAlphabets(event,this)" class="form-control" placeholder="Confirm Password" name="confirm_password" ng-keypress="errorclean()"  ng-click="passworderror()"ng-model="formData.confirmpassword" required ><span style="color:red" ng-show="sentMessage.password.$dirty && sentMessage.password.$invalid">
			              <!--    <span ng-show="sentMessage.password.$error.required">Password is required</span> --></span>
                                <p class="help-block text-danger"></p>
                            <!-- </div> -->
                        </div>
                       
                        <br>
                        <div class="row">
                            <div class="form-group col-xs-12">
                      <!--     <input type="submit"  data-toggle="tooltip"  ng-click="validate()" ng-disabled="sentMessage.username.$dirty && sentMessage.username.$invalid || sentMessage.$invalid" value="create"  class="btn btn-success btn-lg">-->
                              <input type="submit"  data-toggle="tooltip"  ng-click="validate()"  value="create"  ng-model="formData.create" class="btn btn-success btn-lg">  
                            </div>
                        </div>
                    </form> 
                   
                     
               </div>
            </div>
             <div id="checkSession" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Status</h4>
            </div>
            <div class="modal-body">
                <p>{{errMessage}}</p>
               </div>
            <div class="modal-footer">
                <button type="button"  ng-click="check1()" class="btn btn-default" data-dismiss="modal">Close</button>
               </div>
        </div>
    </div>
		</div>
        </div>
    </section>
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
