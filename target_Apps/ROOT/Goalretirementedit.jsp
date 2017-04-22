<!DOCTYPE html>
<html lang="en" ng-app="retirementEditFormApp">
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
<script src="js/RetirementGoal.js"></script>

<script>
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
                 }
                else
                    return false;
               
            }
            catch (err) {
                alert(err.Description);
            }
        }

</script>
<script></script>
<script type='text/javascript'>


/* border: 2px solid #73AD21 ; margin-left: 20px;*/

</script>
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css"
	type="text/css">
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
.scrollbar1 {
    margin-left: 30px;
    float: left;
    height: 300px;
    width: 100px;
    background: #ffff;
    overflow-y: scroll;
    margin-bottom: 25px;
}

.left_content {
	height: 500px;
	border-radius: 25px;
}

.right_content {
	height: 500px;
	background: white;
	"
}

.progress {
	width: 50%;
	margin-left: 20%;
}

#container {
	display: block;
	margin: 5px 0;
}

.modal-content {
	/* Bootstrap sets the size of the modal in the modal-dialog class, we need to inherit it */
	width: inherit;
	height: inherit;
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
	margin: 0;
}

.bigform-content input[type=submit] {
	margin-top: 10px;
}

.myBox {
	border: none;
	padding: 5px;
	font: 24px/36px sans-serif;
	width: 200px;
	height: 200px;
	overflow: scroll;
}
header
{
	font-family: 'Lobster', cursive;
	text-align: center;
	font-size: 25px;	
}

#info
{
	font-size: 18px;
	color: #555;
	text-align: center;
	margin-bottom: 25px;
}

a{
	color: #074E8C;
}

.scrollbar
{
	margin-left: 30px;
	float: left;
	height: 300px;
	width: 65px;
	background: #F5F5F5;
	overflow-y: scroll;
	margin-bottom: 25px;
}

.force-overflow
{
	min-height: 450px;
}

#wrapper
{
	text-align: center;
	width: 500px;
	margin: auto;
}

/*
 *  STYLE 1
 */

#style-1::-webkit-scrollbar-track
{
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
	border-radius: 10px;
	background-color: #F5F5F5;
}

#style-1::-webkit-scrollbar
{
	width: 7px;
	background-color: #F5F5F5;
}

#style-1::-webkit-scrollbar-thumb
{
	border-radius: 10px;
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
	background-color: #AFABAB;;
}
</style>
<script>


</script>
</head>
<body id="page-top" ng-controller="GoalPlane" ng-init="load()" ng-cloak>
<div class="MaskLayer"
ng-class="{isClosed : !masked}">
<div class="MaskLayer-Content">
<i class="fa fa-refresh fa-spin fa-3x"
										 style="position: absolute;    margin-left: 18%;    margin-top: 50%;    color: white;    z-index: 1;"></i>  
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
					<li><a class="page-scroll" href="#" ng-click="goDashboard()">Home</a>
					</li>
					<li><a class="page-scroll" href="#services">How it works?</a>
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
					<li><a class="page-scroll" href="userProfile.jsp"><i
							class="fa fa-user-plus"></i> My Profile</a></li>

					<li><a href="#" class="page-scroll"
						ng-click="deleteAllCookies()"><i class="fa fa-sign-out"></i>
							Logout</a></li>
					<!---  <li>
                     <a class="page-scroll" href="#contact">Contact</a>
                     </li> ---->
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<section class="bg-primary" style="padding-bottom: 30%;" id="about">
		<div class="container">
			<div class="row">
				<div style="display: none" class="alert alert-info text-center"
					id="fail-warning">
					<button type="button" class="close" ng-click="hideWarning()">x</button>
					<strong>Warning! Goal is not feasible currently with your
						income profile !! Please update your income profile in the
						Dashboard to make it feasible </strong> {{errmessage1}}
				</div>
				<div style="display: none" class="alert alert-danger text-center"
					id="fail-alert">
					<button type="button" class="close" ng-click="hideFail()">x</button>
					<strong>Fail! </strong> {{errmessage1}}
				</div>
				<div style="display: none" class="alert alert-success text-center"
					id="success-alert">
					<button type="button" class="close" ng-click="hideSuccess()">x</button>
					<strong>Success! </strong> {{errmessage}}
				</div>
				<div class="title-createplan">
					Retirement
					<button class="btn btn-primary pull-right"
						ng-click="backwithoutsave(formdata)">Back</button>
					<button type="button" class="btn btn-primary pull-right"
						ng-click="DeleteGoal()" style="margin-right: 0.3%;">
						<i class="fa fa-trash-o"></i>Delete
					</button>
				</div>
				<div>
					<div class="side-panel" style="padding-bottom: 14.6%; height: 750px;">
						<div class="left_content">

							<h1>

								<div class="sidebar-collapse">



									<ul class="nav" id="main-menu" style="font-size: 17px">

										<li><a class="active-menu"><i class="fa fa-arrow "></i>Details</a>
										</li>


									</ul>
								</div>
							</h1>
							<form name="goalDetails" id="goalForm" >
								<br>
								<div></div>
								<div class="form-group" align="left">
									<p>
										Retirement Age
										<!-- <input type="text" class="form-control" allow-pattern="\d" name="Retirementage" ng-model="formdata.Retirementage" onkeypress="return numbersonly(event)"> -->
										<select name="Retirementage" ng-model="formdata.Retirementage"
											class="form-control">
											<option ng-selected="age.number == formdata.Retirementage "
												ng-repeat="age in ages" ng-value="age.number">{{age.number}}</option>
										</select>
								</div>
								</p>


								<div class="form-group" align="left">
									<p>
										Planning Horizon <input type="text" class="form-control"
											allow-pattern="\d" name="LifeExpectancy" maxlength="2"
											ng-model="formdata.LifeExpectancy"
											onkeypress="return numbersonly(event)" />
									</p>
									<div class="slider"></div>
								</div>
                            
                             <div class="scrollbar1" id="style-1"    style= "width: 99%;margin-left: 0%;" bgcolor="#ffff">
						<div class="form-group" align="left">
									<a data-toggle="collapse" data-target="#contribution401k"
										style="cursor: pointer; cursor: hand;">401k DETAILS</a>
									<div class="collapse" id="contribution401k">
										<div class="radio">
										<label> <input type="radio" name="user401Amount"
												value="yes" ng-change="show401kDetails()" ng-model="formdata.user401Amount" /> Yes
											</label>
											<label> <input type="radio" name="user401Amount"
												value="no"  ng-change="hide401kDetails()" ng-model="formdata.user401Amount"> No
											</label> 
										
											<div class="card card-block" ng-hide="hideuser401">
												<div style="font-size: 9px;">
												<span class="font-para-black"> 
													Already saved in employer's plan? </span> <input
													type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.amountEmpSav" />
											    </div>
											    <div style="font-size: 9px;">
												<span class="font-para-black">cap for employer's contribution </span> 
													<select
														style="width: 131px; height: 30px; font-size: 18px;"
														name="Retirementage" ng-model="formdata.amountCap"
														class="font-dropdown">
														<option ng-selected="cap == formdata.amountCap "
															ng-repeat="cap in userCap" ng-value="age">{{cap}}</option>
													</select>
													
											</div>
																<div style="font-size: 9px;"
													ng-hide="emplyoerscontribution2">
													<span class="font-para-black"> employer match your contribution? </span> <select
														style="width: 131px; height: 30px; font-size: 18px;"
														name="Retirementage" ng-model="formdata.amountECost"
														class="font-dropdown">
														<option ng-selected="item == formdata.amountECost "
															ng-repeat="item in items" ng-value="age">{{item}}</option>
													</select>
												</div>
											<div style="font-size: 9px;" ng-hide="new401Cont">
											<p>Program calculate contribution automatically</p>

											<div class="radio">
											<label> <input type="radio" name="userCont401"
													value="yesFunc" ng-click="yesFunc()"
													ng-model="formdata.userCont401" /> Yes
												</label>
												<label> <input type="radio" name="userCont401"
													value="noFunc" ng-click="noFunc()" ng-model="formdata.userCont401">
												 No
												</label> 
											</div>
													<div style="font-size: 9px;" ng-hide="contrbutionPlan">
												<span class="font-para-black"> 
													contribute to the plan? </span> <input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.amountEmpCont" />
											</div>

											</div>
										</div>
									</div>
									<div class="slider"></div>
								</div><br><br>
						
								<div class="form-group" align="left">
									<a data-toggle="collapse" data-target="#ira"
										style="cursor: pointer; cursor: hand;">IRA DETAILS</a>
									<div class="collapse" id="ira">
									 Program calculate contribution automatically
										<div class="radio">
											<label> <input type="radio" name="userIraAmount"
												value="no1" ng-change="showIraDetails()" ng-model="formdata.userIraAmount"> Yes
											</label> <label> <input type="radio" name="userIraAmount"
												value="yes1" ng-change="hideIraDetails()" ng-model="formdata.userIraAmount" /> No
											</label>

											<div class="card card-block" >

												<div style="font-size: 9px;" ng-hide="iraDetailsShow">
													<span class="font-para-black"> Amount saved in IRA </span>
													<input type="text"
														style="width: 131px; height: 30px; font-size: 18px;"
														onkeypress="return onlyNumbers(event,this)" min="0"
														placeholder="" maxlength="15" class="input_bl-long"
														ng-model="formdata.iraSaved" />
												</div>
												<div style="font-size: 9px;" ng-hide="iraDetailsShowone">
													<span class="font-para-black"> Contribution to IRA </span>
													<input type="text"
														style="width: 131px; height: 30px; font-size: 18px;"
														onkeypress="return onlyNumbers(event,this)" min="0"
														placeholder="" maxlength="15" class="input_bl-long"
														ng-model="formdata.iraContribute " />
												</div>
											</div>
										</div>
									</div>
									<div class="slider"></div>
								</div>
								<div class="form-group" align="left">
									<a data-toggle="collapse" data-target="#rothira"
										style="cursor: pointer; cursor: hand;">ROTH IRA DETAILS</a>
									<div class="collapse" id="rothira">
										 Program calculate contribution automatically
										<div class="radio">
											<label> <input type="radio" name="userRothIraAmount"
												value="no2" ng-change="showRothIraDetails()" ng-model="formdata.userRothIraAmount"> Yes
											</label> <label> <input type="radio" name="userRothIraAmount"
												value="yes2" ng-change="hideRothIraDetails()" ng-model="formdata.userRothIraAmount"> No
											</label>

											<div class="card card-block" >

												<div style="font-size: 9px;" ng-hide="rothIraDetailsShow">
													<span class="font-para-black"> Amount saved in Roth
														IRA </span> <input type="text"
														style="width: 131px; height: 30px; font-size: 18px;"
														onkeypress="return onlyNumbers(event,this)" min="0"
														placeholder="" maxlength="15" class="input_bl-long"
														ng-model="formdata.rothIra" />
												</div>
												<div style="font-size: 9px;" ng-hide="rothIraDetailsShowone">
													<span class="font-para-black"> Contribution to Roth
														IRA </span> <input type="text"
														style="width: 131px; height: 30px; font-size: 18px;"
														onkeypress="return onlyNumbers(event,this)" min="0"
														placeholder="" maxlength="15" class="input_bl-long"
														ng-model="formdata.rothIraContribute" />
												</div>
												<br>
											</div>
										</div>
									</div>
									<div class="slider"></div>
								</div>

									<div class="form-group" align="left" ng-show="spouse401kd">
									<a data-toggle="collapse" data-target="#spouse401k"
										style="cursor: pointer; cursor: hand;">Spouse 401k DETAILS</a>
									<div class="collapse" id="spouse401k">
										<div class="radio">
										<label> <input type="radio" name="spouse401Amount"
													value="yes4" ng-click="spouseShowdetails()" ng-model="formdata.spouse401Amount">
													Yes
												</label> <label> <input type="radio" name="spouse401Amount"
													value="no4" allow-pattern="\d" maxlength="12"
													 ng-click="spouseHidedetails()" ng-model="formdata.spouse401Amount">
													No
												</label>
											<div class="card card-block" ng-hide="hidespouse401">
												<div style="font-size: 9px;">
												<span class="font-para-black"> 
													Spouse saved in employer's plan </span> <input
													type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.spouseAmount" />
											    </div>
											    <div style="font-size: 9px;">
												<span class="font-para-black">cap for spouse contribution</span> 
												 <select
														style="width: 131px; height: 30px; font-size: 18px;"
														name="Retirementage" ng-model="formdata.spouseCapAmount"
														class="font-dropdown">
														<option ng-selected="spouseCap == formdata.spouseCapAmount"
															ng-repeat="spouseCap in spouseCap" ng-value="age">{{spouseCap}}</option>
													</select>
											</div>
															<div style="font-size: 9px;"
													ng-hide="emplyoerscontribution2">
													<span class="font-para-black"> Spouse match your contribution </span>  <select
														style="width: 131px; height: 30px; font-size: 18px;"
														name="Retirementage" ng-model="formdata.amountSCost"
														class="font-dropdown">
														<option ng-selected="scopei == formdata.amountSCost "
															ng-repeat="scopei in sitems" ng-value="age">{{scopei}}</option>
													</select>
												</div>
																	<div style="font-size: 9px;" ng-hide="spouseNew401Cont">
											<p>Program calculate contribution automatically</p>

											<div class="radio">
											<label> <input type="radio" name="spouseCont401"
													value="yesFuncSpouse"ng-click="yesFuncSpouse()"
													ng-model="formdata.spouseCont401" /> Yes
												</label>
												<label> <input type="radio" name="spouseCont401"
													value="noFuncSpouse" ng-click="noFuncSpouse()" ng-model="formdata.spouseCont401">
												 No
												</label> 
											</div>
													<div style="font-size: 9px;" ng-hide="spouseContribution2">
												<span class="font-para-black"> 
													Spouse contribution to the plan? </span> <input type="text"
													style="width: 131px; height: 30px; font-size: 18px;"
													allow-pattern="\d" maxlength="12"
													onkeypress="return onlyNumbers(event,this)" min="0"
													placeholder="" maxlength="15" class="input_bl-long"
													ng-model="formdata.spouseCont" />
											</div>
										</div>
											</div>
										</div>
									</div>
									<div class="slider"></div>
								</div>
								<div class="form-group" align="left" ng-show="spouseirad">
									<a data-toggle="collapse" data-target="#spouseira" style="cursor: pointer; cursor: hand;">SPOUSE
										IRA DETAILS</a>
									<div class="collapse" id="spouseira">
										 Program calculate contribution automatically
										<div class="radio">
											<label> <input type="radio" name="spouseIraAmount"
												value="yes5" ng-change="showspouseIraDetails()"  ng-model="formdata.spouseIraAmount"> Yes
											</label> <label> <input type="radio" name="spouseIraAmount"
												value="no5" ng-change="hidespouseIraDetails()" ng-model="formdata.spouseIraAmount"> No
											</label>

											<div class="card card-block" >

												<div style="font-size: 9px;" ng-hide="spouseIraDetailsShow">
													<span class="font-para-black">Amount saved in spouse
														IRA </span> <input type="text"
														style="width: 131px; height: 30px; font-size: 18px;"
														onkeypress="return onlyNumbers(event,this)" min="0"
														placeholder="" maxlength="15" class="input_bl-long"
														ng-model="formdata.sIraSaved" />
												</div>
												<div style="font-size: 9px;" ng-hide="spouseIraDetailsShowone">
													<span class="font-para-black"> contribution to
														spouse IRA </span> <input type="text"
														style="width: 131px; height: 30px; font-size: 18px;"
														onkeypress="return onlyNumbers(event,this)" min="0"
														placeholder="" maxlength="15" class="input_bl-long"
														ng-model="formdata.sIraContribute" />
												</div>
												<br>
											</div>
										</div>
									</div>
									<div class="slider"></div>
								</div>
								<div class="form-group" align="left" ng-show="spouserothirad">
									<a data-toggle="collapse" data-target="#spouserothira" style="cursor: pointer; cursor: hand;">SPOUSE
										ROTH IRA DETAILS</a>
									<div class="collapse" id="spouserothira">
										 Program calculate contribution automatically
										<div class="radio">
											<label> <input type="radio" name="spouseRothAmount"
												value="yes6" ng-change="hidespouseRothIraDetails()" ng-model="formdata.spouseRothAmount"> Yes
											</label> <label> <input type="radio" name="spouseRothAmount"
												value="no6" ng-change="showspouseRothIraDetails()"  ng-model="formdata.spouseRothAmount"> No
											</label>

											<div class="card card-block" >

												<div style="font-size: 9px;"ng-hide="spouseRothIraDetailsShow">
													<span class="font-para-black"> Amount saved in
														spouse Roth IRA </span> <input type="text"
														style="width: 131px; height: 30px; font-size: 18px;"
														onkeypress="return onlyNumbers(event,this)" min="0"
														placeholder="" maxlength="15" class="input_bl-long"
														ng-model="formdata.sRothIra" />
												</div>
												<div style="font-size: 9px;"ng-hide="spouseRothIraDetailsShowone">
												
													<span class="font-para-black">Contribution to spouse
														Roth IRA </span> <input type="text"
														style="width: 131px; height: 30px; font-size: 18px;"
														onkeypress="return onlyNumbers(event,this)" min="0"
														placeholder="" maxlength="15" class="input_bl-long"
														ng-model="formdata.sRothIraContribute" />
												</div>
												<br>
											</div>
										</div>
									</div>
									<div class="slider"></div>
								</div>

								<div class="form-group" align="left">

									<a type="button" data-toggle="collapse" data-target="#demo"
										style="cursor: pointer; cursor: hand;">ADVANCED</a>


									<div id="demo" class="collapse">
										<div class="form-group" align="left">
											<p>Retirement Expense
											<span> <input type="text"
												class="form-control" allow-pattern="\d" maxlength="10"
												name="retirementExpense"
												ng-model="formdata.retirementExpense"
												onkeypress="return numbersonly(event)">
												</p>
											</span>
										</div>

										<!-- <div class="form-group" align="left">
											<p>
												Other Retirement Income <span > <input
													type="text" class="form-control" allow-pattern="\d"
													name="otherincome" maxlength="10"
													ng-model="formdata.otherincome"
													onkeypress="return numbersonly(event)">
											</p>
											</span>
										</div> -->

										<div ng-hide="!SpouceDetail">
											<div class="form-group" align="left">
												<p>
													Spouse Retirement Age <select name="Retirementage"
														ng-model="formdata.SpouseRA" class="form-control">
														<option ng-selected="age.number == formdata.SpouseRA "
															ng-repeat="age in ages" ng-value="age.number">{{age.number}}</option>
													</select>
												<div class="slider"></div>
											</div>
											<!-- <div class="form-group" align="left">
												<p>
													Spouse Other Retirement Income <span>
														<input type="text" class="form-control" allow-pattern="\d"
														name="SpouseRI" maxlength="10"
														ng-model="formdata.SpouseRI"
														onkeypress="return numbersonly(event)">
												</p>
												</span>
											</div> -->

										</div>
									</div>
									
								</div>
								 </div>
								 <br> &nbsp&nbsp
									
								 
    
						</div>

<button type="button" ng-click="processForm1()"
										class="btn btn-primary" style="    margin-left: 25%;">Update</button>
						</form>
					</div>
				</div>
				<div class="center-content" style="height: 750px;">
					<div class="center_panel">
						<div class="align-sidebar fade in " id="dialog_confirm_map"
							role="dialog" aria-labelledby="dialog_confirm_mapLabel"
							aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									</br> </br>
									<div class="progress">
										<div class="progress-bar progress-bar-striped active"
											id="progress_bar" ng-model="progressbar" aria-valuemin="0"
											aria-valuemax="100" style="width: 2%"></div>
									</div>


									<div class=" text-center">

										<div class="form-group" ng-show="show==1">

											<p>What is your desired location?</p>
											<input type="text" placeholder="Bangalore" name="firstname"
												ng-model="formdata.firstname"></br> </br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar()" ng-click="progressBar()">Next</button>
											</br> </br>
										</div>
										<span>{{formdata.firstname}}</span> <span>{{formdata.middlename}}</span>
										<div class="form-group" ng-show="show==3">
											<p>Down Payment</p>
											<input type="hidden" name="middlename"> <input
												type="hidden" name="firstname"> <input type="text"
												name="lastname" placeholder="$20000"
												ng-model="formdata.firstname"></br> </br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar2()" ng-click="progressBar1()">Next</button>
											</br> </br>
										</div>
										<div class="form-group" ng-show="show==4">
											<p>What are your monthly debt payments?</p>
											<input type="hidden" name="middlename"> <input
												type="hidden" name="firstname"> <input type="text"
												name="lastname" placeholder="$ 0"
												ng-model="formdata.firstname"></br> </br>
											<button type="button" class="btn btn-primary"
												onclick="progress_bar3()" ng-click="progressBar2()"
												data-dismiss="modal">Next</button>
											</br> </br>

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
							<div class=" text-center">
								<i class="fa fa-home fa-5x"></i></br> </br>
								<p>Based on the submitted details the user SSB at the age
									{{formdata.Retirementage}} is as follows.</p>
								<p>$ {{Math.round(RetirementData.userExpectedSSB*1)}}</p>
								<table class="table table-striped">
									<thead>
										<tr>
											<th style="text-align: center;">AGE</th>
											<th style="text-align: center;">USER SSB</th>
											<th style="text-align: center;"><div
													ng-hide="!SpouceDetail">SPOUSE SSB</div></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>62</td>
											<td>$ {{Math.round(RetirementData.User62)}}</td>
											<td><div ng-hide="!SpouceDetail">$
													{{Math.round(RetirementData.Spouse62)}}</div></td>

										</tr>
										<tr>
											<td>63</td>
											<td>$ {{Math.round(RetirementData.User63)}}</td>
											<td><div ng-hide="!SpouceDetail">$
													{{Math.round(RetirementData.Spouse63)}}</div></td>
										</tr>
										<tr>
											<td>64</td>
											<td>$ {{Math.round(RetirementData.User64)}}</td>
											<td><div ng-hide="!SpouceDetail">$
													{{Math.round(RetirementData.Spouse64)}}</div></td>
										</tr>
										<tr>
											<td>65</td>
											<td>$ {{Math.round(RetirementData.User65)}}</td>
											<td><div ng-hide="!SpouceDetail">$
													{{Math.round(RetirementData.Spouse65)}}</div></td>
										</tr>
										<tr>
											<td>66</td>
											<td>$ {{Math.round(RetirementData.User66)}}</td>
											<td><div ng-hide="!SpouceDetail">$
													{{Math.round(RetirementData.Spouse66)}}</div></td>
										</tr>

										<tr>
											<td>67</td>
											<td>$ {{Math.round(RetirementData.User67)}}</td>
											<td><div ng-hide="!SpouceDetail">$
													{{Math.round(RetirementData.Spouse67)}}</div></td>
										</tr>
										<tr>
											<td>68</td>
											<td>$ {{Math.round(RetirementData.User68)}}</td>
											<td><div ng-hide="!SpouceDetail">$
													{{Math.round(RetirementData.Spouse68)}}</div></td>
										</tr>
										<tr>
											<td>69</td>
											<td>$ {{Math.round(RetirementData.User69)}}</td>
											<td><div ng-hide="!SpouceDetail">$
													{{Math.round(RetirementData.Spouse69)}}</div></td>
										</tr>
										<tr>
											<td>70</td>
											<td>$ {{Math.round(RetirementData.User70)}}</td>
											<td><div ng-hide="!SpouceDetail">$
													{{Math.round(RetirementData.Spouse70)}}</div></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>

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
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">Status</h4>
					</div>
					<div class="modal-body">
						<p>{{errMessage}}</p>
					</div>
					<div class="modal-footer">
						<button type="button" ng-click="check1()" class="btn btn-default"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<div id="myModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">Status</h4>
					</div>
					<div class="modal-body">
						<p>{{errMessage}}</p>
					</div>
					<div class="modal-footer">
						<button type="button" ng-click="reload()" class="btn btn-default"
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
		<div class="modal fade" id="myModalback" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Message</h4>
					</div>
					<div class="modal-body">
						<p>You had made some changes,are you sure you want to go back
							without updating this goal ?</p>
					</div>
					<div class="modal-footer">
						<button type="button" ng-click="goDashboard()"
							class="btn btn-primary pull-right" data-dismiss="modal">Yes</button>

						<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
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
					<p style="color: white;">Copyright &copy; WealthSetter 2017. All
						Rights Reserved</p>
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