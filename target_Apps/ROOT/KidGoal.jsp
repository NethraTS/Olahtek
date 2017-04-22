<!DOCTYPE html>
<html lang="en" ng-app="KidGoal">
   <head>
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
      <script src="js/jquery.min.js"></script>
      <!-- LOAD ANGULAR -->
      <script src="js/angular.min.js"></script>
      <script src="js/app.js"></script>
      
       <script src="js/kid.js"></script>



<script></script>
     <script type='text/javascript'>



</script> 
      <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css" type="text/css">
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
    height: 500px; border-radius: 25px;
    
}
 .right_content {
  height: 500px; background:white;" 
    
}/* 
      .modal-dialog {
    display:table;
    height: 20%;
    margin-top: 30%;
    width: 100%;
} */
.progress{
width:50%;
margin-left:20%;
}
#container{   display: block;   margin: 5px 0;}
.modal-content {
    /* Bootstrap sets the size of the modal in the modal-dialog class, we need to inherit it */
    width:inherit;
    height:inherit;
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
    margin:0;
}
p{



}
.bigform-content input[type=submit] {
    margin-top:10px;
}</style>
     <script>

$(function () {
  
  //getting click event to show modal
   
        $('#dialog_confirm_map').modal();
      
      //appending modal background inside the bigform-content
        $('.modal-backdrop').appendTo('.right_content');
      //removing body classes to able click events
        //$('body').removeClass();

  //just to prove actions outside modal
    $('#help-button').click(function () {
        alert("Action with modal opened or closed");
    });
  //end just to prove actions outside modal
});
</script>
   </head>
   <body id="page-top"  ng-controller="GoalKid" ng-init="load()" ng-cloak>
   <div class="MaskLayer"
ng-class="{isClosed : !masked}">
<div class="MaskLayer-Content">
<i class="fa fa-refresh fa-spin fa-3x"
										 style="position: absolute;    margin-left: 18%;    margin-top: 50%;    color: white;    z-index: 1;"></i>  
</div> 
</div>

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
               <a class="navbar-brand page-scroll" href="dashboardUser0.jsp">WealthSetter</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
               <ul class="nav navbar-nav navbar-right">
                  <li>
                     <a class="page-scroll"  href="#" ng-click="goDashboard()">Home</a>
                  </li>
                  <li>
                     <a class="page-scroll" href="#services">How it works?</a>
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
                  <li><a class="page-scroll" href="userProfile.jsp"><i class="fa fa-user-plus"></i> My Profile</a>
                        </li>
                 
                        <li><a href="#" class="page-scroll" ng-click="deleteAllCookies()"><i class="fa fa-sign-out"></i> Logout</a>
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
      <section class="bg-primary" id="about">
         <div class="container">
            <div class="row">
             <div class="title-createplan" >Raising A Kid<button class="btn btn-primary pull-right" ng-click="goSelectGoal()">Back</button></div>
               <div >
                  <div class="side-panel"  >
					<div class="left_content">
					 <h1>
								
								<div class="sidebar-collapse">
								
								
								
                <ul class="nav" id="main-menu" style="font-size:17px">
                
               <li>     
<a  class="active-menu"><i class="fa fa-arrow "></i>{{message}}</a>
</li> 
                
         
               </ul>     
            </div>
					</h1>
					<form name="goalDetails" id="goalForm">
						<br>
						
						
						 <!-- <div class="form-group" align="left" ng-show="show1>1">
                        	<p>Goal Name <input type="text" allow-pattern="[a-z]" maxlength="12" class="form-control"  ng-model="formdata.Goalname">
  						</p>
  						</div>
						 -->
                        <div class="form-group" align="left" ng-show="show1>2">
                        	<p>Start Year
  									<select class="form-control" ng-model="formdata.startYear" ><option ng-repeat="item in Goalstartingyear1" value="{{item.name}}" >{{item.name}}</option></select></br>
  						</p>
  						</div>
								
								
								<div class="form-group" align="left" ng-show="show1>3"><p> Cost of the goal(per month)
  									<input type="text" allow-pattern="\d" maxlength="12" class="form-control" name="Goalcost" value="$5000" ng-model="formdata.goalCost"></p> 
  									
									
								</div>	
								
							<!-- 	<div class="form-group" align="left" ng-show="show1>4"> 
  								
  								<p>Goal ending year</p>	<select class="form-control" ng-model="formdata.endYear" ><option ng-repeat="item in Goalendingyear1" value="{{item.name}}" >{{item.name}}</option></select>
  									
									
								</div>
							 -->
								
								
								</div>
													
								
                       </form> 
					</div>
					</div>
					<div class="center-content" style="height: 750px;" >
					<div  class="center_panel"   >
					<div class="align-sidebar fade in " id="dialog_confirm_map" data-backdrop="static" data-keyboard="false" role="dialog" aria-labelledby="dialog_confirm_mapLabel" aria-hidden="true">
            			<div class="modal-dialog">
            				<div class="modal-content"></br></br>
            				      <div class="progress">
  <div class="progress-bar progress-bar-striped active" id="progress_bar" ng-model="progressbar"  aria-valuemin="0" aria-valuemax="100" style="width:2%">
  </div>
</div>
           
                			
                			<div class=" text-center" >
                			<form name="myForm">
                		<!-- 	  -->
    						
    						 <div class="form-group text-center"  ng-show="show==1" >
                		       <span style="color:red">{{msgerr}}</span>
       							<p>Name of the goal:	<br> </br>
  									<input type="text" maxlength="12" allow-pattern="[a-z]" name="Goalname" style="width: 40%;" ng-model="formdata.Goalname" >
  							</p> 
       							<button type="button" class="btn btn-primary" onclick="progress_bar()"  ng-click="progressBar();">Next</button></br></br>
    						</div>
    						
    						 <div class="form-group" ng-show="show==2" >
                		       <span style="color:red">{{msgerr}}</span>
       							<p>Start year?</p> <select class="form-control" ng-model="formdata.startYear" style="width:90px; margin-left: auto;margin-right:auto"><option ng-selected="item.name==formdata.startYear"ng-repeat="item in Goalstartingyear1" ng-value="{{item.name}}" >{{item.name}}</option></select></br>
       							 
       							<button type="button" class="btn btn-primary"   ng-click="progressBar1();">Next</button></br></br>
    						</div>
   						
        				
    							 <span></span>
        					 <div class="form-group" ng-show="show==3" >
        					 <span style="color:red">{{msgerr2}}</span>
      							 <p>How much will it cost per month?</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
        						<div class="input-prepend">
 									<span class="money-fieldmodel">
        						<input type="text" name="lastname" maxlength="12" allow-pattern="\d" placeholder="20000" ng-model="formdata.goalCost"></br></br>
       							</span>
       							</div>
       							<!-- <button type="button" class="btn btn-primary" onclick="progress_bar2()" ng-click="progressBar1()">Next</button></br></br> -->
       							<input type=hidden ng-model="formdata.actionHomeType" value="form1" ng-init="formdata.actionHomeType='insert'">
       							<button type="button" class="btn btn-primary" onclick="progress_bar3()" ng-click="progressBar2()" >Next</button></br></br>
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
								<div class=" text-center" >
								<i class="fa fa-home fa-5x"></i></br></br>
								<p>For the goal {{Goalname}} the annual expense is <span>$</span> {{Goalcost}}</p>
 							<table class="table" ng-show="show>4">
 								<tr>
 								<td>Name of the goal</td><td> {{Goalname}}</td></tr>
 								<td>Cost of the goal</td><td><span>$</span> {{Goalcost}}</td></tr>
 								<td>Goal starting year</td><td>{{Goalstartingyear}}</td></tr>
 								<td>Goal ending year</td><td>{{Goalendingyear}}</td></tr>
                            
 								</tr>
 								</table>
                                     
                                     </div></div> 
 
 						
									</div>
 
                  </div>
               </div>
               </div>
             <!--    <div class="col-lg-10 col-lg-offset-1 text-center">
              <input id="submit-button" type="submit" value="Submit my form" class="btn btn-default" />
                <a href="#services" style="background-color: #E65319;" class="btn btn-primary btn-xl page-scroll ">Next</a> 
					</div>-->
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
			<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Message</h4>
            </div>
            <div class="modal-body">
                <p>{{errmessage}}</p>
               </div>
            <div class="modal-footer">
                <button type="button"  ng-click="check1()" class="btn btn-default" data-dismiss="modal" onclick="window.location.reload()">Close</button>
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
      <script src="assets/js/fusioncharts.theme.fint.js"></script>
      <script src="assets/js/fusioncharts.powercharts.js"></script>
      <script src="assets/js/fusioncharts.js"></script>
   </body>
</html>