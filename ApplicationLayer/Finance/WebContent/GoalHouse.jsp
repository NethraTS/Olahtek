<!DOCTYPE html>
<html lang="en" ng-app="formApp">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="Balagopalan" >
      <title>WEALTHSETTER</title>
      <!-- Bootstrap Core CSS -->
      <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
      <!--  for Type ahead autocomplete -->
 <!-- <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.2/angular.min.js"></script> -->
<!--  <script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.12.0.js"></script>  -->
 <!-- <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.2/angular.min.js"></script> -->
<!--  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"  type="text/css" > 
      Custom Fonts -->
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
       <script src="js/HouseGoal.js"></script>
       <script>
			var url = window.location.href;
			var hashes = url.split("=")[1];
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
}

.money-fieldmodel input{
 	padding-left: 30px !important;
 }
</style>
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
        ////alert("Action with modal opened or closed");
    });
  //end just to prove actions outside modal
});

function isNumberKey(evt, obj) {

    var charCode = (evt.which) ? evt.which : event.keyCode
    var value = obj.value;
    var dotcontains = value.indexOf(".") != -1;
    if (dotcontains)
        if (charCode == 46) return false;
    if (charCode == 46) return true;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
</script>
   </head>
   <body id="page-top"  ng-controller="GoalPlane" ng-init="load()" ng-cloak>
   <div class="MaskLayer"
ng-class="{isClosed : !masked}" ng-init="onloadCreateData()">
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
             <div class="title-createplan" >Buying a House<button class="btn btn-primary pull-right" ng-click="goSelectGoal()">Back</button></div>
               <div >
                  <div class="side-panel" >
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
						<div>{{ errormessage }}</div>
						<div class="form-group" align="left" ng-show="show1>0">
                        	<p>Starting Year 
  									<select name="goalYear" ng-model="formdata.goalYear"  class="form-control"><option ng-repeat="item in items" value="{{item}}">{{item}}</option></select>
  								</p>
						
								</div>
                        <div class="form-group" align="left" ng-show="show1>1">
                        	<p>Location
  									<!-- <input type="text" class="form-control" allow-pattern="[a-z]" name="location" ng-model="formdata.location" > -->
  								 <select ng-model="formdata.location"  class="form-control">
                   				<option ng-selected="state.name == formdata.location " ng-repeat="state in states" ng-value="state.name" >{{state.name}}</option>
                     		 	</select>
                     		 	<!-- <input name="states" id="states" type="text" placeholder="enter a state" onkeypress="return onlyAlphabets(event,this)" class="input_bl-long" ng-model="formdata.location" typeahead="state for state in states | filter:$viewValue | limitTo:8" class="form-control"> -->
  								</p>
									<!--<div class="form-group" align="left">
								 <p> Marital Status <br>
								 <input type="radio" name="sex" value="male" checked>Single 
  								<input type="radio" name="sex" value="female"> Married</p>
  								</div>-->
							<!-- 	<div class="form-group" align="left" ng-show="show>2"><p>Anual Income
  									<input type="text" class="form-control" name="anualIncome" value="$20000"  ng-model="formdata.anualIncome"></p>
								</div> -->
								<!--<div class="form-group" align="left"> <p>Spouse Income
  									<input type="text" class="form-control" id="spouseIncome" value="0" ng-model="formdata.spouseIncome"></p>
								</div>-->
								<div class="form-group" align="left" ng-show="show1>2"> <p> House Value 
  									<div class="input-prepend">
 									<span class="money-field">
  									<input type="text" class="form-control" allow-pattern="\d" name="principal_amount"  maxlength="12" value="$0" ng-model="formdata.principal_amount"></p>
  									</span>
  									&nbsp
  									
  									</div>
									
								</div>
								<div class="form-group" align="left" ng-show="show1>3"><p> Down Payment 
								<div class="input-prepend">
 									<span class="percent-field">
  									<input type="text" allow-pattern="\d" class="form-control" name="downPayment"  maxlength="12" value="$5000" ng-model="formdata.downPayment"></p> 
  									</span>
  									</div>
									<div class="slider"></div>
								</div>	
								
								<div class="form-group" align="left" ng-show="show1>3">
								
								   <a type="button" style="cursor: pointer; cursor: hand;" data-toggle="collapse" data-target="#demo">ADVANCED</a>
 								 <div id="demo" class="collapse">
   						 <div class="form-group" align="left" >
                        	<p>Credit Score</p>
  									<input type="text" class="form-control" allow-pattern="\d" name="creditsc" value="$0" ng-model="formdata.creditsc">
  									
								</div>
								
									<!--<div class="form-group" align="left">
								 <p> Marital Status <br>
								 <input type="radio" name="sex" value="male" checked>Single 
  								<input type="radio" name="sex" value="female"> Married</p>
  								</div>-->
								<div class="form-group" align="left" ng-show="show>2"><p>Property Tax Rate
  									<input type="text" class="form-control" allow-pattern="\d" name="taxrate" maxlength="2"value="$20000"  ng-model="formdata.taxrate"></p>
  									
								</div>
								<!--<div class="form-group" align="left"> <p>Spouse Income
  									<input type="text" class="form-control" id="spouseIncome" value="0" ng-model="formdata.spouseIncome"></p>
								</div>-->
								
								<div class="form-group" align="left" ng-show="show>3"><p> PMI 
  									<input type="text" class="form-control" allow-pattern="\d" name="PMI" maxlength="2" value="$5000" ng-model="formdata.PMI"></p> 
  									
									<div class="slider"></div>
								</div>	
								<div class="form-group" align="left" ng-show="show>4"> <p> House Insurance 
  									<input type="text" class="form-control" allow-pattern="\d" name="ownerInsuranceAmount" maxlength="10" value="$0" ng-model="formdata.houseInsuranceAmount"></p>
  									
									
								</div> 
								<div class="form-group" align="left" ng-show="show>4"> <p> Loan Type
  								 	<!-- <input type="text" class="form-control" allow-pattern="\d" name="goalDuration" value="$0" ng-model="formdata.goalDuration"> --> </p>
  								 <select ng-model="formdata.goalDuration"  class="form-control">
                   				<option ng-selected="loan.name == formdata.goalDuration " ng-repeat="loan in loans" ng-value="loan.name" >{{loan.name}}</option>
                     		 	</select> 
 								
								</div> 
   									<input type=hidden ng-model="formdata.actionHomeType" value="form1" ng-init="formdata.actionHomeType='update'">
 									 </div><br>
 									&nbsp&nbsp<button type="button" class="btn btn-primary" ng-click="checkform1()">Update</button> 
								</div>
								
								</div>
								
								<!--<div class="form-group" align="left" ng-show="show>5"> <p> Credit Score
  									<input type="text" class="form-control" name="creditScore" value="Very Good" ng-model="formdata.creditScore"> </p>  
								</div>	-->							
								
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
                			 	 <div class="form-group" ng-show="secondHouseOption" >
        					 <span style="color:red; font-size: 90%;">{{msgerr}}</span>
      							 <p>Buying second house?</p>
       							<select ng-change="selectSecondHouseAs()" name="frequency" ng-model="formdata.frequency" class="form-control" style="width:45%;margin-left: auto;    margin-right: auto;" >
                        		<option ng-selected="frequency.string == formdata.frequency " ng-repeat="frequency in frequency" ng-value="frequency.number" >{{frequency.string}}</option>			
                    			    </select>   <br>
                    			     	<div style="font-size: 9px;" ng-hide="houseMarketValue">
												<span class="font-para-black">House market price</span> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12" ng-change="secondHouseCal()"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.houseMarketPrice" />
											</div>
										<!-- 	<div style="font-size: 9px;" ng-hide="brokerCommission">
												<span class="font-para-black">Broker commission</span> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.brokerCommissionAmount" />
											</div>
											<div style="font-size: 9px;" ng-hide="otherAmount">
												<span class="font-para-black">Other Cost</span> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.otherCost" />
											</div> -->
												<div style="font-size: 9px;" ng-hide="oldHouseValue">
												<span class="font-para-black">Old house market value</span> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.firstHousePrinciple" />
											</div>
											<div style="font-size: 9px;" ng-hide="rentExpense">
											<div class="input-prepend">
 									<span class="percent-fieldmodel">
												<span class="font-para-black">Rental expenses</span> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"  
													ng-model="formdata.rentalExpenses" />
													</span>
													</div>
											</div>
											<div style="font-size: 9px;" ng-hide="rentIncome">
												<span class="font-para-black">What is the expected monthly rental income?</span> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.rentalIncome" />
											</div>
													
											<div style="font-size: 9px;" ng-hide="rentActivity">
												<span class="font-para-black"> Actively participate in the rental activity ?</span>
													<div class="form-group">
														<div class="radio">
															<label> <input type="radio" name="rentActivity"
																 value="yes"
																ng-model="formdata.rentalActivity"> Yes
															</label>
														
													
															<label> <input type="radio" name="rentActivity"
																value="no" ng-model="formdata.rentalActivity">
																No
															</label>
														</div>
													</div>
											</div></br>
                    			  
       							<button type="button" class="btn btn-primary"  ng-click="progressbar0()">Next</button></br></br>
						 </div> 
						
                				<div class="form-group" ng-show="show==0" >
                		       <span style="color:red">{{msgerr}}</span>
       							<p>Goal starting year</p><select ng-change="getRemainingMortgage()"name="KidCollegeYear"  class="form-control" style="width:40%; margin-left: auto;    margin-right: auto;" ng-model="formdata.goalYear"><option ng-repeat="item in goalYears" value="{{item.year}}" ng-selected=" formdata.goalYear == item.year">{{item.year}}</option></select>
       							<!-- <input allow-pattern="\d" type="text" placeholder="Bangalore"  name="KidCollegeYear" ng-model="formdata.KidCollegeYear" ><span id="ad1"></span> --></br></br>
       							 
       								<div style="font-size: 9px;" ng-hide="brokerCommission">
												<span class="font-para-black">Broker commission</span> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.brokerCommissionAmount" />
											</div>
											<div style="font-size: 9px;" ng-hide="otherAmount">
												<span class="font-para-black">Other Cost</span> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.otherCost" />
											</div></br>
       							<button type="button" class="btn btn-primary"  ng-click="progressBar0()">Next</button></br></br>
    						</div>
                			  <div class="form-group" ng-show="show==1" >
                		       <span style="color:red">{{msgerr}}</span>
       							<p>What is your desired location?</p>
       							<div class="col-md-10">
       							<div class="col-md-6"><!-- <input allow-pattern="[a-z]" type="text" placeholder="Bangalore"  name="desired_location" ng-model="formdata.desired_location" > --><span id="ad1"></span>
       							  <select ng-model="formdata.desired_location" ng-change="getCities()" style="width:100%;" class="form-control" placeholder="please enter State" style="width:20%;margin-left:auto;margin-right:auto;">
                   				<option ng-selected="state.name == formdata.desired_location " ng-repeat="state in states" ng-value="state.name" >{{state.name}}</option>
                     		 	</select>
                     		 	</div>
                     		 	<div class="col-md-4">
                     		 	<select ng-model="formdata.desired_locationcity" ng-change="getHousevalue()" ng-disabled="disabledCity" style="width:200%;" placeholder="select City" class="form-control" style="width:20%; margin-left: auto;    margin-right: auto;">
                   				<option ng-repeat="city in citys"  >{{ city }}</option>
                     		 	</select>
                     		 	</div>
                     		 	</div></br></br></br>
       							<button type="button" class="btn btn-primary" onclick="progress_bar()"  ng-click="progressBar();"
>Next</button></br></br>
    						</div>
   							<span></span>
        					 <!-- <div class="form-group" ng-show="show==2" >
       							<p>What is your annual pre-tax income?</p> <input type="text" name="middlename"  placeholder="B" ng-model="formdata.firstname"></br></br>
       							 <input type="hidden" name="firstname"><button type="button" class="btn btn-primary" onclick="progress_bar1()" ng-click="progressBar1()">Next</button></br></br>
   							 </div> -->
    							 <span></span>
    							 										<div style="font-size: 9px;" ng-show="show==21">
    							 										<span style="color:red; font-size: small;">{{msgerr2}}</span>
												<p>Money received from selling first house(downpayment for second house)</p> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0" ng-change="downPaymentCalculation()"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.down_payment" /><br><br>
													
       							<button type="button" class="btn btn-primary" onclick="progress_bar2()" ng-click="showLoanOption()">Next</button></br></br>
											</div> 
        					 <div class="form-group" ng-show="show==4" >
        					 <span style="color:red">{{msgerr2}}</span>
      							<p>Down Payment</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
        						<!-- <input type="text" name="lastname" maxlength="12" allow-pattern="\d" placeholder="$20000" ng-model="formdata.down_payment"> -->
        						<div class="input-prepend">
 									<span class="percent-fieldmodel"><select ng-model="formdata.down_payment"  class="form-control" style="width:40%; margin-left: auto;    margin-right: auto;">
                   					<option ng-selected="downPayment.name == formdata.down_payment" ng-repeat="downPayment in downPayments" ng-value="downPayment.name" >{{downPayment.name}}</option>
                     		 		</select> </br></br>
                     		 		</span>
                     		 		</div>
       							<button type="button" class="btn btn-primary" onclick="progress_bar2()" ng-click="showLoanOption()">Next</button></br></br>
   							 </div>
                			 <div class="form-group" ng-show="show==3" >
                			 <span style="color:red">{{msgerr3}}</span>
      							 <p>What is your House Value?</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
        						<div class="input-prepend">
 									<span class="money-fieldmodel">
        						<input type="text" name="lastname" maxlength="12" ng-click="msgerr3=''" allow-pattern="\d" placeholder="200000"  ng-model="formdata.firstname2"/>&nbsp<i class="fa fa-undo" ng-click="formdata.firstname2=goalHouseData.housevalue"></i></br></br>
        						
        						</span>
        						</div>
        						<input type=hidden ng-model="formdata.actionHomeType" value="form1" ng-init="formdata.actionHomeType='insert'">
       							<button type="button" class="btn btn-primary" onclick="progress_bar3()" ng-click="progressBar2()" >Next</button></br></br>
       						
   							 </div>
   							 <div class="form-group" ng-show="show==22">
   							  <span style="color:red">{{msgerr4}}</span>
   							 <p>House value excluding land</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
        				<input type="text" style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0" 
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.propertyValForRent"  /><br><br>
                     		 			<button type="button" class="btn btn-primary"  ng-click="progressBar5()" >Next</button></br></br>
   							 </div>
			 <div class="form-group" ng-show="show==23" >
      							<p> Loan Type</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
 								  <select ng-model="formdata.goalDuration"  class="form-control" style="width:40%; margin-left: auto;    margin-right: auto;" >
                   				<option ng-selected="loan.name == formdata.goalDuration " ng-repeat="loan in loans" ng-value="loan.name" >{{loan.name}}</option>
                     		 	</select>  </br>
       							<button type="button" class="btn btn-primary"  ng-click="showHouseApprecition()" >Next</button></br></br>
   							 </div>
   							 		 <div class="form-group" ng-show="show==24" >
   							 		 <span style="color:red">{{msgerr2}}</span>
      							<p>Credit Score</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
 							<select ng-model="formdata.creditsc"  class="form-control" style="width:40%; margin-left: auto;    margin-right: auto;" >
                   				<option ng-selected="creditScore.name == formdata.creditsc " ng-repeat="creditScore in creditScores" ng-value="creditScore.name" >{{creditScore.name}}</option>
                     		 	</select>   </br>
       							<button type="button" class="btn btn-primary"  ng-click="progressBar1()" >Next</button></br></br>
   							 </div>
   							 	<div class="form-group" ng-show="show==25" >
   							 <p>House appreciation rate in percent</p>
        				<input type="text" class="form-group" style="width: 131px; height: 30px; font-size: 18px;"
												 maxlength="4"
													onkeypress="return isNumberKey(event,this)" min="0" 
													placeholder="" class="input_bl-long"
													ng-model="formdata.appreciationRate"  /><br><br>
                     		 			<button type="button" class="btn btn-primary" ng-click="showCreditScore()">Next</button></br></br>
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
								<p>Total yearly payment for house is $ {{ goalHouseData.mortgage_expense}}, which includes your other debt payments and property tax.</p>
 								
 								<table class="table" ng-show="show>4">
 								<tr>
 								<td>House Value</td><td>$ {{ goalHouseData.principalAmount }}</td></tr>
 								<td>Down Payment</td><td>$ {{ goalHouseData.downPayment }}</td></tr>
 								<td>Mortgage Amount</td><td>$ {{ (goalHouseData.principalAmount-goalHouseData.downPayment) }}</td></tr>
 								<td>Total Annual Expenses</td><td>$ {{ goalHouseData.mortgage_expense}}</td></tr>
 								<td>Mortgage Expenses</td><td>$ {{ goalHouseData.Anual_morgage}}</td></tr>
 								<td>Loan Type</td><td>{{ goalHouseData.year }} yr Fixed </td></tr>
 								<td>Interest</td><td>{{ goalHouseData.interest }}%</td></tr>
 								<td>Property Tax</td><td>$ {{ goalHouseData.property_tax/100 * goalHouseData.principalAmount }}</td></tr>
 								<td>Property Tax Rate</td><td>{{ goalHouseData.property_tax }}%</td></tr>
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
                <p>{{errMessage}}</p>
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
		<!-- <div id="loading">
        Loading....
		</div> -->
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