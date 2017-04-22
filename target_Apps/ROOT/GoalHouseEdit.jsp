<!DOCTYPE html>
<html lang="en" ng-app="goalHouseEdit">
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
       <script src="js/HouseGoal.js"></script>
      <script>
      var url = window.location.href;
      var hashes = url.split("=")[1];
      </script>

    <!--  <script type='text/javascript'>

function progress_bar() {  $('#progress_bar').css('width', '35%'); }
function progress_bar1() {  $('#progress_bar').css('width', '70%'); }
function progress_bar2() {  $('#progress_bar').css('width', '70%'); }
function progress_bar3() {  $('#progress_bar').css('width', '100%'); }p
function progress_bar4() {  $('#progress_bar').css('width', '100%'); }
/* border: 2px solid #73AD21 ; margin-left: 20px;*/

</script>  -->
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
 .form-group p{
	margin-bottom: 0px !important;
} 

#test1
	{
		font-size:100%;
	}

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
.bigform-content input[type=submit] {
    margin-top:10px;
}</style>
<script type="text/javascript">
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
   <body id="page-top"  ng-controller="GoalHouseEditController"  ng-init="load()" ng-cloak>
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
                     <a class="page-scroll"  href="#" ng-click="goDashboard()" >Home</a>
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
                            <li><a href="mortgageCalculator.jsp">Mortgage Calculator</a></li>
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
      <section class="bg-primary" style="padding-bottom: 22%;" id="about">
         <div class="container">
            <div class="row">
            	 <div style="display: none" class="alert alert-info text-center" id="fail-warning">
    <button type="button" class="close" ng-click="hideWarning()">x</button>
    <strong>Warning! Goal is not feasible currently with your income profile !! Please update your income profile in the Dashboard to make it feasible </strong>
   {{errmessage1}}
				</div>
            <div style="display: none" class="alert alert-danger text-center" id="fail-alert">
    <button type="button" class="close" ng-click="hideFail()">x</button>
    <strong>Fail! </strong>
   {{errmessage1}}
				</div>
            <div style="display: none" class="alert alert-success text-center" id="success-alert">
   				 <button type="button" class="close" ng-click="hideSuccess()">x</button>
   				 <strong>Success! </strong>
  					 {{ errmessage }}
			</div>
             <div class="title-createplan" >Buying a House  <button class="btn btn-primary pull-right" ng-click="backwithoutsave(formdata)" >Back</button>
    <button type="button" class="btn btn-primary pull-right" ng-click="DeleteGoal()" style="
    margin-right: 0.3%;
"><i class="fa fa-trash-o"></i>Delete</button>
              </div>
            
               <div >
                  <div class="side-panel" style="padding-bottom: 19%" >
					<div class="left_content">
					 <h1>
								
								<div class="sidebar-collapse">
								
								
								
                <ul class="nav" id="main-menu" style="font-size:17px">
                
               <li>     
<a  class="active-menu"><i class="fa fa-arrow "></i>Details</a>
</li> 
                
         
               </ul>     
            </div>
					</h1>
					<form name="goalDetails" id="goalForm">
						<br>
						<div></div>
						{{message}}
						<div class="form-group" align="left">
                        	<p>Starting Year 
  									<select name="goalYear" ng-change="calculateOldMortgage()" ng-model="formdata.goalYear"  class="form-control"><option ng-repeat="item in items" value="{{item}}">{{item}}</option></select>
  								</p>
						
								</div>
						 <div class="form-group" align="left" >
								<div class="form-group" ng-show="secondHouseOption" >
        					 <span style="color:red; font-size: 90%;">{{msgerr}}</span>
      							 <p>buying second house?</p>
       							<select ng-change="selectSecondHouseAs()" ng-disabled="houseoptionDisable" name="frequency" ng-model="formdata.frequency" class="form-control" >
                        		<option ng-selected="frequency.string == formdata.frequency " ng-repeat="frequency in frequency" ng-value="frequency.number" >{{frequency.string}}</option>			
                    			    </select>   <br>
                    			     	<div style="font-size: 9px;" ng-hide="houseMarketValue">
												<span class="font-para-black">House market price</span> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12" ng-change="costInSelling()"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.houseMarketPrice" />
											</div>
											<div style="font-size: 9px;" ng-hide="brokerCommission">
												<span class="font-para-black">Broker commission</span> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12" ng-change="costInSelling1()"
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
											</div>
											<div style="font-size: 9px;" ng-hide="oldHouseValue">
												<span class="font-para-black">Old house market value</span> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12" ng-change="RentalExpense()"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.firstHousePrinciple" />
											</div>
											<div style="font-size: 9px;" ng-hide="rentExpense">
												<span class="font-para-black">Rental expenses</span> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
												ng-disabled="reantalExpenseDisable" ng-model="formdata.rentalExpenses" />
											</div>
											<div style="font-size: 9px;" ng-hide="rentIncome">
												<span class="font-para-black">monthly rental income</span> 
												<input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.rentalIncome" />
											</div>
								
											<div style="font-size: 9px;" ng-hide="rentActivity">
												<span class="font-para-black"> rental activity </span>
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
											</div>
                    			 						 </div>
						 </div>  
                        <div class="form-group" align="left" >
                        	<p>Location
  									<!-- <input type="text" class="form-control" allow-pattern="[a-z]" name="location"   ng-model="formdata.location"> -->
								<select ng-model="formdata.location"  ng-change="getCities()" class="form-control">
                   				<option ng-selected="state.name == formdata.location"  ng-repeat="state in states" ng-value="state.name" >{{state.name}}</option>
                     		 	</select>
								
								</div></p>
								<div class="form-group" align="left" >
								<p>City
  									<!-- <input type="text" class="form-control" allow-pattern="[a-z]" name="location"   ng-model="formdata.location"> -->
								<select ng-model="formdata.locationcity" ng-disabled="disabledCity" ng-change="getHousevalue()"  class="form-control">
                   				<option ng-selected="city== formdata.locationcity" ng-repeat="city in citys" ng-value="city" >{{city}}</option>
                     		 	</select>
								
								</div></p>
								
								<div class="form-group" align="left" ><p> House Value
								<div class="input-prepend">
 									<span class="money-field">
  									<input type="text" class="form-control" maxlength="12" allow-pattern="\d" name="downPayment" ng-model="formdata.principal_amount"></p> 
								</span>
								</div>
									<div class="slider"></div>
								
								</div>
								
				<!-- 			<div class="form-group" align="left" ng-hide="rentPropertyValue">
   							 <p>House value excluding land</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
 								<select ng-model="formdata.propertyValForRent"  class="form-control" >
                   					<option ng-selected="property.name == formdata.propertyValForRent" ng-repeat="property in properties" ng-value="property.name" >{{property.name}}</option>
                     		 		</select> 
   							 </div>	 -->
   							 <div class="form-group" align="left" ng-hide="rentPropertyValue">
   							 <p>House value excluding land
								<div class="input-prepend">
 									<span class="money-field">
  									<input type="text" class="form-control" maxlength="12" allow-pattern="\d" name="downPayment" ng-model="formdata.propertyValForRent"></p> 
								</span>
								</div>
									<div class="slider"></div>
								</div>
								<div class="form-group" align="left" ng-hide="downPaymentDropdown"> <p> Down Payment 
  									<!-- <input type="text" class="form-control" maxlength="12" allow-pattern="\d" name="debitInfo"  ng-model="formdata.downPayment"></p> -->
								<div class="input-prepend">
 									<span class="percent-field">	
									<select ng-model="formdata.downPayment"  class="form-control">
                   					<option ng-selected="downPayment.name == formdata.downPayment" ng-repeat="downPayment in downPayments" ng-value="downPayment.name" >{{downPayment.name}}</option>
                     		 		</select> 
                     		 		</span>
                     		 		</div>
								</div>
									<div class="form-group" align="left" ng-hide="newDownPayment" ><p> Downpayment second house
								<div class="input-prepend">
 									<span class="money-field">
  									<input type="text" class="form-control" maxlength="12" ng-change="downPaymentCalculation()" allow-pattern="\d" name="downPayment" ng-model="formdata.downPayment"></p> 
								</span>
								</div>
									<div class="slider"></div>
								
								</div>	
								<div class="form-group" align="left">
   							 <p>Loan Type</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
 						  <select ng-model="formdata.goalDuration"  class="form-control">
                   				<option ng-selected="loan.name == formdata.goalDuration " ng-repeat="loan in loans" ng-value="loan.name" >{{loan.name}}</option>
                     		 	</select> 
   							 </div>
								 <div class="form-group" align="left" >
                        			<p>Credit Score</p>
  									<!-- <input type="text" class="form-control" allow-pattern="\d" name="debitInfo"  ng-model="formdata.creditsc"> -->
								<select ng-model="formdata.creditsc"  class="form-control">
                   				<option ng-selected="creditScore.name == formdata.creditsc " ng-repeat="creditScore in creditScores" ng-value="creditScore.name" >{{creditScore.name}}</option>
                     		 	</select> 
								</div>
                             <div class="form-group" align="left" ng-hide="houseAppriciationRate">
                             <p>House appreciation rate
                                <div class="input-prepend">
                                    <span class="percent-field">
                                    <input type="text"
                            onkeypress="return isNumberKey(event,this)" maxlength="4"
                            placeholder="" class="input_bl-long" name="downPayment" ng-model="formdata.appreciationRate"></p> 
                                </span>
                                </div>
                                    <div class="slider"></div>
                                </div>
								</form> 
					</div>
					</div>
			<div class="center-content" style="height: 750px;padding-bottom: 77%;"> 
					<div  class="center_panel"   >
					<div class="align-sidebar fade in " id="dialog_confirm_map"  role="dialog" aria-labelledby="dialog_confirm_mapLabel" aria-hidden="true">
            			<div class="modal-dialog">
            				<div class="modal-content"></br></br>
            				      <div class="progress">
  <div class="progress-bar progress-bar-striped active" id="progress_bar" ng-model="progressbar"  aria-valuemin="0" aria-valuemax="100" style="width:2%">
  </div>
</div>
           
                			
                			<div class=" text-center" >
                			
                			  <div class="form-group" ng-show="show==1" >
                		
       							<p>What is your desired location?</p> <input type="text" placeholder="Bangalore" name="firstname" ng-model="formdata.firstname"></br></br>
       							<button type="button" class="btn btn-primary" onclick="progress_bar()"  ng-click="progressBar()" >Next</button></br></br>
    						</div>
   							<span>{{formdata.firstname}}</span>
        					 <!-- <div class="form-group" ng-show="show==2" >
       							<p>What is your annual pre-tax income?</p> <input type="text" name="middlename"  placeholder="B" ng-model="formdata.firstname"></br></br>
       							 <input type="hidden" name="firstname"><button type="button" class="btn btn-primary" onclick="progress_bar1()" ng-click="progressBar1()">Next</button></br></br>
   							 </div> -->
    							 <span>{{formdata.middlename}}</span>
        					 <div class="form-group" ng-show="show==3" >
      							 <p>Down Payment</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
        						<input type="text" name="lastname"  placeholder="$20000" ng-model="formdata.firstname"></br></br>
       							<button type="button" class="btn btn-primary" onclick="progress_bar2()" ng-click="progressBar1()">Next</button></br></br>
   							 </div>
                			 <div class="form-group" ng-show="show==4" >
      							 <p>What are your monthly debt payments?</p>
       							<input type="hidden" name="middlename">
        						<input type="hidden" name="firstname">
        						<input type="text" name="lastname" placeholder="$ 0" ng-model="formdata.firstname"></br></br>
       							<button type="button" class="btn btn-primary" onclick="progress_bar3()" ng-click="progressBar2()" data-dismiss="modal" >Next</button></br></br>
       						
   							 </div>
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
								<input type="hidden" ng-disabled="disabled" required ng-init="disabled=true;disabled1=true;disabled2=true;disabled3=true;disabled4=true;disabled5=true;disabled6=true;disabled7=true;disabled8=true;disabled9=true;disabled10=true;">
								<input type=hidden ng-model="formdata.actionHomeType" value="form1" ng-init="formdata.actionHomeType='update'">
								<i class="fa fa-home fa-5x"></i></br></br>
								<p>Based on annual income we believe you can comfortably afford a total yearly payment of <p style="color:#2ECCFA;">$ {{ goalHouseEditData.mortgage_expense }}</p> which includes your other debt payments and property tax.</p>
 								<!-- ng-click="disabledemo()" -->
								<table class="table">
								<tr>
										<td></td>
										<td>Loan Type</td>
										<td>
										<div ng-hide="!Original5">
										 {{ formdata.goalDuration }}
										&nbsp
								<!-- 		<span class="glyphicon glyphicon-pencil" id="test" 
											style="cursor: pointer; cursor: hand;"
											ng-click="Original5=false;Edit5=true;myStyle={'color':'#2ECCFA'}" ng-style="myStyle" aria-hidden="true"></span> -->
										</div>
										 <div ng-hide="!Edit5">
										<select ng-model="formdata.goalDuration" id="test1"
												ng-click="Original5=false;Edit5=true;myStyle={'color':'#2ECCFA'}"
												class="form-control" style="width:70%;right:20%;" ng-change="getInterestRate()">
												<option ng-selected="loan.name == formdata.goalDuration "
													ng-repeat="loan in loans" ng-value="loan.name" 
													>{{loan.name}}</option>
										</select></div></td>
									</tr>
									<tr>
										<td></td>
										<td>Interest</td>
										<td><div ng-hide="!Original3">
												{{ goalHouseEditData.interest }}%
												&nbsp
										<span
											class="glyphicon glyphicon-pencil icon8" id="test"
											style="cursor: pointer; cursor: hand;"
											ng-click="Original3=false;Edit3=true;disabled7=false;myStyle1={'color':'#2ECCFA'}"aria-hidden="true" ng-style="myStyle1"></span>
											&nbsp &nbsp
											<span ng-hide="revertHide"><i class="fa fa-undo" ng-click="goalHouseEditData.interest=temp.interest"></i>
											</span>
										</div>
										<div ng-hide="!Edit3">
										<input type="text"
											class="input_bl-long"
											ng-model="goalHouseEditData.interest" allow-pattern="\d" maxlength="2"
											ng-click="Original3=false;Edit3=true;disabled7=false;myStyle1={'color':'#2ECCFA'}"
											ng-disabled="disabled7" id="test1">%</td>
											</div>
									</tr>
									<tr>
										<td></td>
										<td>Property Tax Rate</td>
										<td><div ng-hide="!Original1">
										{{ formdata.taxrate }} %
										&nbsp
										<span
											class="glyphicon glyphicon-pencil icon8 decimalAllow" id="test"
											style="cursor: pointer; cursor: hand;"
											ng-click="Original1=false;Edit1=true;disabled8=false;myStyle4={'color':'#2ECCFA'}"aria-hidden="true" ng-style="myStyle4"></span>
										</div>
										<div ng-hide="!Edit1">
										<input type="text" maxlength="15"
											class="input_bl-long decimalAllow" ng-pattern="/^[1-9][0-9]{0,2}(?:,?[0-9]{3}){0,3}(?:\.[0-9]{1,2})?$/"
											ng-model="formdata.taxrate" maxlength="2"
											ng-click="Original1=false;Edit1=true;disabled8=false;myStyle4={'color':'#2ECCFA'}"
											ng-disabled="disabled8" id="test1">%</td>
											</div>
									</tr>
									<tr>
										<td></td>
										<td>PMI</td>
										<td>
										<div ng-hide="!Original2">
										 {{ formdata.PMI }} %
										&nbsp
										<span
											class="glyphicon glyphicon-pencil decimalAllow" id="test"
											style="cursor: pointer; cursor: hand;"
											ng-click="Original2=false;Edit2=true;disabled10=false;myStyle2={'color':'#2ECCFA'}"  aria-hidden="true" ng-style="myStyle2"></span>
										</div>
										<div ng-hide="!Edit2">
										 <input type="text" maxlength="15"
											class="input_bl-long "
											ng-model="formdata.PMI" allow-pattern="\d" maxlength="2"
											ng-click="Original2=false;Edit2=true;disabled10=false;myStyle2={'color':'#2ECCFA'}"
											ng-disabled="disabled10" id="test1">%
										</div></td>
									</tr>
									<tr>
										<td></td>
										<td>Home Insurance</td>
										<td>
										<div ng-hide="!Original">
										$ {{formdata.houseInsuranceAmount}}
										&nbsp
										<span
											class="glyphicon glyphicon-pencil" id="test"
											style="cursor: pointer; cursor: hand;"
											ng-click="Original=false;Edit=true;disabled9=false;myStyle3={'color':'#2ECCFA'}"  aria-hidden="true" ng-style="myStyle3"></span>
										</div>
										<div ng-hide="!Edit">
										$ <input type="text"
											class="input_bl-long" allow-pattern="\d" maxlength="10"
											ng-model="formdata.houseInsuranceAmount"
											ng-click="Original=false;Edit=true;disabled9=false;myStyle3={'color':'#2ECCFA'}"
											ng-disabled="disabled9" id="test1">
										</div>
											</td>
									</tr>
									<tr>
										<td></td>
										<td>House Value</td>
										<td>$ {{ goalHouseEditData.principalAmount }}
										</td>
									</tr>
									<tr>
										<td></td>
										<td>Down Payment</td>
										<td>$ {{ goalHouseEditData.downPayment }}</td>
									</tr>
									<tr>
										<td></td>
										<td>Mortgage Amount</td>
										<td>$ {{ (goalHouseEditData.principalAmount-goalHouseEditData.downPayment) }}</td>
									</tr>
									<tr>
										<td></td>
										<td>Total Annual Expenses</td>
										<td>$ {{ goalHouseEditData.mortgage_expense }}</td>
									</tr>
									<tr>
										<td></td>
										<td>Mortgage Expenses</td>
										<td>$ {{ goalHouseEditData.Anual_morgage}}</td>
									</tr>
									<tr>
										<td></td>
										<td>Property Tax</td>
										<td>$ {{ goalHouseEditData.propertyTax }}</td>
									</tr>
										<input type=hidden ng-model="formdata.actionHomeType" value="form1" ng-init="formdata.actionHomeType='update'">
								
									<tr>
								
										<td colspan="3">
											<button type="button" class="btn btn-primary"
												ng-click="checkform1()">Update</button>
										</td>
									</tr>
								</table>

							</div></div>
 						
									</div>
 
                  </div>
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
                 <button type="button"  ng-click="reload()" class="btn btn-default" data-dismiss="modal" >Close</button>
               </div>
        </div>
    </div>
		</div> 
		
		<div id="creditScoreModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Message</h4>
            </div>
            <div class="modal-body">
                <p> Your credit score is outside the normal range that banks would accept for mortgage application. If you prefer to proceed with this goal, please be cautioned that you may not be able to obtain a loan.<a href="">Visit here</a> to see ways to improve your credit score.</p>
               </div>
            <div class="modal-footer">
                <button type="button"  ng-click="check1()" class="btn btn-default" data-dismiss="modal">Close</button>
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
		<div class="modal fade" id="myModal1" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Message</h4>
						</div>
						<div class="modal-body">
							<p>Are you sure you want to delete the goal</p>
						</div>
						<div class="modal-footer">
							<button type="button" ng-click="deletegoal()"
								class="btn btn-primary pull-right" data-dismiss="modal">
								<i class="fa fa-trash-o"></i>Delete
							</button>

		<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #B7B7BB;margin-right: 0.3%;">Close</button>
						</div>
					</div>

				</div>
			</div>
				<div class="modal fade" id="myModalback" role="dialog"
				style="FONT-FAMILY: 'Bitstream Charter';">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Message</h4>
						</div>
						<div class="modal-body">
							<p>You had made some changes, are you sure you want to go
								back without updating this goal ?</p>
						</div>
						<div class="modal-footer">
							<button type="button" id="dialog-ok" ng-click="goDashboard()"
								class="btn btn-primary pull-right" data-dismiss="modal">Yes</button>

							<button type="button" class="btn btn-default"
								data-dismiss="modal">No</button>
						</div>
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
      
            <script>
 
 $(document).ready(function() {
    $(".decimalAllow").keydown(function (e) {
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
