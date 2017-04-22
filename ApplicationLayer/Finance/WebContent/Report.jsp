<!DOCTYPE html>
<html lang="en" ng-app="formApp3">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Balagopalan">
<link rel="stylesheet" href="css/nv.d3-1.8.3.min.css"/>

<!--------------------------------------  -->
<style>
.dropdown-submenu {
	position: relative;
}

@media print {
    .hidthisinprint {
        display:none !important;
    }
}
nvd3 svg{
padding-left: 50px;
}
.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: -6px;
	margin-left: -1px;
	-webkit-border-radius: 0 6px 6px 6px;
	-moz-border-radius: 0 6px 6px 6px;
	border-radius: 0 6px 6px 6px;
}

.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #cccccc;
	margin-top: 5px;
	margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
	border-left-color: #555;
}

.dropdown-submenu.pull-left {
	float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
	left: -100%;
	margin-left: 10px;
	-webkit-border-radius: 6px 0 6px 6px;
	-moz-border-radius: 6px 0 6px 6px;
	border-radius: 6px 0 6px 6px;
}

@media ( min-width : 768px) {
	ul.nav li:hover>ul.dropdown-menu {
		display: block;
	}
	#navbar {
		text-align: center;
	}
}

.retirement1 {
	background-color: #6a6afb; . dropdown-submenu >.dropdown-menu { top : 0;
	left: 100%;
	margin-top: -6px;
	margin-left: -1px;
	-webkit-border-radius: 0 6px 6px 6px;
	-moz-border-radius: 0 6px 6px 6px;
	border-radius: 0 6px 6px 6px;
}

.cargoal1 {
	background-color: #8cff66; .
	dropdown-submenu >a: after{              
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #cccccc;
	margin-top: 5px;
	margin-right: -10px;
}

.kid1 {
	background-color: #ffff66;
	.
	dropdown-submenu
	.pull-left
	{
	float
	:
	none;
}

.customized1 {
	background-color: #66ffff; @media ( min-width : 768px) { ul .nav li 
	 :hover > ul .dropdown-menu {
	display: block;
}

#navbar {
	text-align: center;
}

}
.vacation1 {
	background-color: #009900;
	.
	cargoal1
	{
	background-color
	:
	#8cff66;
}

.college1 {
	background-color: #cc00cc;
	.
	customized1
	{
	background-color
	:
	#66ffff;
}

.emergency1 {
	background-color: #ff5c33;
	.
	college1
	{
	background-color
	:
	#cc00cc;
}

.housing1 {
	background-color: #0077b3;
	.
	housing1
	{
	background-color
	:
	#0077b3;
}

.Marriage1 {
	background-color: #ffa64d;
}
 
</style>

<title>WEALTHSETTER</title>
<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<!-- Custom Fonts -->

<link rel="stylesheet" href="css/fonts.googleapis.css" type="text/css">
<link rel="stylesheet" href="css/fonts.googleapis1.css" type="text/css">
<link rel="stylesheet" href="css/nv.d3-1.8.3.min.css"/>
<!--  ---------------------- This is for load graphs from google----------

 <link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'> 
	-------------------------------------------------------------->
<script src="js/jquery.min.js"></script>

<!-- LOAD ANGULAR -->
<script src="js/angular.min.js"></script>
<script src="js/report.js"></script>
<script src="js/fusionchart1.js"></script>
<script src="js/fusionchart2.js"></script>
<script src="js/fusionchart3.js"></script>
<script src="js/filesaver.js"></script>

<script src="js/app.js"></script>
<!--   <script src="https://rawgithub.com/eligrey/FileSaver.js/master/FileSaver.js" type="text/javascript"></script>-->
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css"
	type="text/css">
<!-- Plugin CSS -->
<link rel="stylesheet" href="css/animate.min.css" type="text/css">
<!-- Custom CSS -->
<link rel="stylesheet" href="css/creative1.css" type="text/css">



<style>
.sel {
	color: green;
}
</style>
</head>
<body id="page-top" ng-controller="formController3" ng-init="load();"
	ng-cloak>

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
					<li><a class="page-scroll" href="dashboardUser0.jsp">Home</a>
					</li>
					<li><a class="page-scroll" href="#">How it works?</a></li>
					<li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Case Studies <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="CaseStudy1.jsp">Case Study 1</a></li>
							<li><a href="CaseStudy2.jsp">Case Study 2</a></li>
							<li><a href="CaseStudy3.jsp">Case Study 3</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Resources <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="mortgageCalculator.jsp">Mortgage Calculator</a></li>
							<li class="dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Car
									Calculator</a>
								<ul class="dropdown-menu">
									<li><a href="carLoanCalculator.jsp">Car Loan
											Calculator</a></li>
									<li><a href="carLeaseCalculator.jsp">Car Lease
											Calculator</a></li>
								</ul></li>
							<li><a href="ssbCalculator.jsp">Social Security Benefits</a></li>
							<li><a href="FderalincomeTaxCalculator.jsp">Income Tax Calculator</a></li>
						</ul></li>
					<li>
						<!--   <a class="page-scroll" href="Report.jsp">Reports</a> --> <a
						href="#" ng-click="report()">Reports</a>
					</li>
					<li><a class="page-scroll" href="userProfile.jsp"><i
							class="fa fa-user-plus"></i> My Profile</a></li>

					<li><a href=# class="page-scroll"
						ng-click="deleteAllCookies()"><i class="fa fa-sign-out"></i>
							Logout</a></li>


					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<section class="bg-primary" id="about">
		<div class="container">
			<div class="row">
				<div style="display: none"
					class="//alert //alert-success text-center" id="success-//alert">
					<button type="button" class="close" ng-click="hideSuccess()">x</button>
					<strong>Success! </strong> {{SuccessMessage}}
				</div>


				<div class="title-createplan">
					REPORTS
					<button ng-hide="backbutton" class="btn btn-primary pull-right hidthisinprint"
						ng-click="goDashboard()">Back</button>
				</div>



				<div class="content-panel">
					<div class="side-panel">
						<div class="left_content">
							<h1>

								<div class="sidebar-collapse">




									<p>Plan names</p>

									<select class="form-control" id="sel1"
										ng-model="formdata.planName"
										ng-change="goaldetails(formdata.planName)">
										<option ng-selected="planName.name==formdata.planName"
											ng-repeat=" planName in planName" ng-value="planName.name">{{planName.name}}</option>

									</select></br>
									<!-- <button class="btn btn-primary pull-right" id="but"
										ng-click="getIncomeProfile()">INCOME PROFILE</button> -->


									<a href="#" ng-click="getIncomeProfile(incomeProfile.name)"
										class="pull-center hidthisinprint" style="font-size: 50%; padding-left: 13%;">INCOME
										PROFILE</a> <br></br>


								</div>
							</h1>
						</div>
					</div>

					<div class="center-content">
						

						<div id="reportGraph">
							<div class="container">
								<center>
								<button class="btn btn-primary pull-left hidthisinprint"
										onclick="print()">Print</button>
										
								<div id="goalsData" style="margin: 1%;font-weight:bold">Goals Details</div>
								<div id="exportable1" style="margin-left: -7%;">

									<table ng-repeat="roll in tablevaluesforgoal "
										class="table table-bordered table-striped"
										style="width: 35%; vertical-align: top; display: inline-table; margin-left: 1%">

										<thead >

											<tr>
											 <td id="no" colspan="2"
													style="padding-left: 29%; color: #2eccfa; font: 12%; font-size: 108%;">
													<h>{{ roll.golas }}</h> <!-- <td>{{ roll.Target_Saving}}</td> -->
													<!-- <td>{{ roll.Year_Accomplished }}</td>
														<td>{{ roll.Year_Accomplished_end}}</td>
 -->
											</td>
											</tr>

											<tr >
												<!--ng-click="sortType = 'hash'; sortReverse = !sortReverse-->


												<th>{{roll.collName}} <span
													ng-show="sortType == 'Target_Saving' && !sortReverse"
													class="fa fa-caret-down"></span> <span
													ng-show="sortType == 'Target_Saving' && sortReverse"
													class="fa fa-caret-up"></span>
												</th>
												<td id="no"><h>{{ roll.Target_Saving | number:2 }}</h> <!-- <td>{{ roll.Target_Saving}}</td> -->
													<!-- <td>{{ roll.Year_Accomplished }}</td>
														<td>{{ roll.Year_Accomplished_end}}</td>
 --></td>
											</tr>
											
											<tr>
												<!--ng-click="sortType = 'hash'; sortReverse = !sortReverse-->


												<th>Start Year: <span
													ng-show="sortType == 'Year_Accomplished' && !sortReverse"
													class="fa fa-caret-down"></span> <span
													ng-show="sortType == 'Year_Accomplished' && sortReverse"
													class="fa fa-caret-up"></span>
												</th>
												<td id="no"><h>{{ roll.Year_Accomplished }}</h> <!-- <td>{{ roll.Target_Saving}}</td> -->
													<!-- <td>{{ roll.Year_Accomplished }}</td>
														<td>{{ roll.Year_Accomplished_end}}</td>
 --></td>
											</tr>

											<tr>
												<!--ng-click="sortType = 'hash'; sortReverse = !sortReverse-->


												<th>End Year: <span
													ng-show="sortType == 'Year_Accomplished_end' && !sortReverse"
													class="fa fa-caret-down"></span> <span
													ng-show="sortType == 'Year_Accomplished_end' && sortReverse"
													class="fa fa-caret-up"></span>
												</th>
												<td id="no"><h>{{ roll.Year_Accomplished_end}}</h> <!-- <td>{{ roll.Target_Saving}}</td> -->
													<!-- <td>{{ roll.Year_Accomplished }}</td>
														<td>{{ roll.Year_Accomplished_end}}</td>
 --></td>
											</tr>

										</thead>

									</table>
				

								</div>
								<h2>Income-Expense graph will render here</h2>
									 <nvd3   options="lineOptions" data="datalineresult"></nvd3>
<div>
						</div>
									
								 <!-- <div id="chart-container2">Asset-Growth graph will render
										here</div>  -->
										
										
										<!-- <div id="areachart"></div> -->

     <h2 id="assetsGoalsTitle" style="display:none">Assets And Goals Chart</h2>
     <nvd3 options="optionsAreaAssetsandGoals" data="dataAssetsandGoals"></nvd3>
     
     
						
      <div class="row">
      <div class="col-md-5" style="position: relative;left: 250px;">
      <br><br>	
      <h2 style="text-align:center;position: relative;width: 600px;right:10px;">Assets Pie Chart</h2>
     
      <select style="width: 20%;position: relative;left: 300px;" data-toogle="tooltip"
									ng-model="reportsstartYearBR" class="form-control pull-right"
									ng-change="changeYearreportsBR(reportsstartYearBR)">
									<option ng-selected="year == reportsstartYearBR"
										ng-repeat="year in yearreportsBR" ng-value="year">{{year}}</option>
								</select> <span style="position: relative;left:285px;top: 7px;" class="pull-right">Select Year </span> 
   
      <nvd3 options="optionsPiereports" data="dataPieChartreportsBR"></nvd3>
      </div>
 <!--      <div class="col-md-6">
      <h2 style="text-align:center;">Before Retirement Assets</h2>
      <select style="width: 20%;" data-toogle="tooltip"
									ng-model="reportsstartYearAR" class="form-control pull-right"
									ng-change="changeYearreportsAR(reportsstartYearAR)">
									<option ng-selected="year == reportsstartYearAR"
										ng-repeat="year in yearreportsAR" ng-value="year">{{year}}</option>
								</select> <span class="pull-right">Select Year </span> 
   
      <nvd3 options="optionsPiereports" data="dataPieChartreportsAR"></nvd3>
      </div> -->
      </div>
  
<h2>Investment Portfolio</h2>
<div class="bg-primary" id="about" style="background-color:white;">
														<div class="container">
															<div class="row">
																<div class="col-md-12 text-center">
																
																		<div class="container-fluid">
																<table border="0" width="100%">
																<tr>					                                            
																<td class="left" style="font-size: 1.5rem;"><b>Risk Score:</b></td>
					                                             <td class="right">
						                                     <lable ng-model="riskScorePort" style="font-size: 1.5rem;position: relative;right: 75px;">{{showriskScore}}</lable> 	
					</td>
					  <td class="left" style="font-size: 1.5rem;position: relative;right: 35px;"><b>Risk Factor:</b></td>
					<td class="right">
						
								 <lable ng-model="filingStatusPort" style="font-size: 1.5rem;position: relative;right: 120px;">{{showfiling}}</lable>
										
								</td>
								
								  <td class="left" style="font-size: 1.5rem;position: relative;right: 100px;"><b>Age:</b></td>
								  <td>
								<lable ng-model="agePort" style="font-size: 1.5rem;position: relative;right: 100px;">{{agePort}}</lable>
								</td>
						
					           </table>
					           <br>
					           <table style="background-color: white; " ng-hide="!growthTable" class="table table-bordered">
																	<thead>
																		<tr>
																			
																			<th>Ticker</th>
																			<th>name</th>
																			<th>Growth Rates</th>
																			<th>Interest/Dividend Yields</th>
																		
																			
											</thread>							
										</tr>
										<tbody>
																		<!-- <tr ng-repeat="value1 in Porfolio_tableIncome "> -->
																			<!-- <th scope="row" >helo</th> -->
                                                                            <tr>
                                                                            <td>VTI</td>
																			<td>Vanguard Total Stock Market ETF</td>
																			<td>{{vtiGrowthRate}}%</td>
																			<td>{{vtiD}}%</td>
												
																		</tr>
																		   <tr>
                                                                            <td>VTV</td>
																			<td>Vanguard FTSE Emerging Markets ETF</td>
																			<td>{{vtvDGrowthRate}}%</td>
																			<td>{{vtvd}}%</td>
												
																		</tr>
																		   <tr>
                                                                            <td>VOE</td>
																			<td>iShares Core US Aggregate Bond</td>
																			<td>{{voeGrowthRate}}%</td>
																			<td>{{voeD}}%</td>
												
																		</tr>
																		   <tr>
                                                                            <td>VBR</td>
																			<td>Vanguard Total International Bond ETF</td>
																			<td>{{vbrDGrowthRate}}%</td>
																			<td>{{vbrd}}%</td>
												
																		</tr>
																		   <tr>
                                                                            <td>VEA</td>
																			<td>Vanguard S&P 500 ETF</td>
																			<td>{{veaDGrowthRate}}%</td>
																			<td>{{veaD}}%</td>
												
																		</tr>
																		   <tr>
                                                                            <td>VWO</td>
																			<td>Vanguard Small-Cap ETF</td>
																			<td>{{vwoDGrowthRate}}%</td>
																			<td>{{vwod}}%</td>
												
																		</tr>
																		   <tr>
                                                                            <td></td>
																			<td>(note: Rates and yields based on 10-year returns)</td>
																			<td></td>
																			<td></td>
												
																		</tr>
																	</tbody>
										</table><a></a>
										
										<div class="row">
										
										
										
										
										<div class="col-xs-6 col-md-4">
										 <div ng-hide="!Original1" class="ng-binding">
										 <label >Growth Rate :{{growthRate}}</label>
										&nbsp;
<!-- 										<span class="glyphicon glyphicon-pencil icon8" id="test" style="cursor: pointer; color: black;" ng-click="Original1=false;Edit1=true;disabled8=false;myStyle4={'color':'#2ECCFA'}" aria-hidden="true" ng-style="myStyle4"></span>
 -->										</div>
										<div ng-hide="!Edit1" class="ng-hide">
										 <label >Growth Rate :</label>
										<input type="number" maxlength="15" class="input_bl-long  ng-pristine ng-valid" ng-model="growthRate" allow-pattern="\d" ng-click="Original1=false;Edit1=true;disabled8=false;myStyle4={'color':'#2ECCFA'}" ng-disabled="disabled8" id="test1" disabled="disabled">%
										</div>
										</div>
										
										
										<div class="col-xs-6 col-md-4" >
										<div ng-hide="!Original2" class="ng-binding">
										<label >Dividend Yields :{{portfolioDividend}}</label>
										&nbsp;
<!-- 										<span class="glyphicon glyphicon-pencil icon8" id="test" style="cursor: pointer; color: black;" ng-click="Original2=false;Edit2=true;disabled8=false;myStyle4={'color':'#2ECCFA'}" aria-hidden="true" ng-style="myStyle4"></span>
 -->										</div>
										<div ng-hide="!Edit2" class="ng-hide">
										 <label >Dividend Yields :</label>
										<input type="number" maxlength="15" class="input_bl-long  ng-pristine ng-valid" ng-model="portfolioDividend" allow-pattern="\d" ng-click="Original2=false;Edit2=true;disabled8=false;myStyle4={'color':'#2ECCFA'}" ng-disabled="disabled8" id="test1" disabled="disabled">%</div>
										</div>
										
										<div class="col-xs-6 col-md-4">
										<div ng-hide="!Original3" class="ng-binding">
										<label >PortfolioInterest: {{portfolioInterest}}</label>
										&nbsp;
<!-- 										<span class="glyphicon glyphicon-pencil icon8" id="test" style="cursor: pointer; color: black;" ng-click="Original3=false;Edit3=true;disabled8=false;myStyle4={'color':'#2ECCFA'}" aria-hidden="true" ng-style="myStyle4"></span>
 -->										</div>
										<div ng-hide="!Edit3" class="ng-hide">
										 <label >Dividend Yields :</label>
										<input type="number" maxlength="15" class="input_bl-long  ng-pristine ng-valid" ng-model="portfolioInterest" allow-pattern="\d" ng-click="Original3=false;Edit3=true;disabled8=false;myStyle4={'color':'#2ECCFA'}" ng-disabled="disabled8" id="test1" disabled="disabled">%</div>
										
										</div>
				
										</div> 	
														<div>
															
														<!-- <div style="margin-top:2%" class="btn btn-primary btn-xl page-scroll pull-right">
															<button type="submit" class="btn btn-primary "ng-click="restore()">Reset</button>
														</div>	 -->
<!-- 														<a  style="background-color: #E65319;"class="btn btn-primary btn-xl page-scroll pull-left" ng-click="submitPortfolio2()" >Calculate</a>
 -->										
														</div>
										
													</div>

												</div>

                                           </div>
                                       </div>
                                 </div>

								</center>
								
							
											<div class="bg-primary1 hidthisinprint" id="about">
												<div class="container">
													<div class="row">
														<div class=" col-md-12 text-center">
															<br></br>
															<!-- <div ng-hide="!growthChart">
																<a href="#" ng-click="showtable()">Details </a>
															</div>
															<div ng-hide="!growthTable">
																<a href="#" ng-click="showChart()">Chart </a>
															</div> -->

                                                         
															<div  id="chart-container3" style="font-size: 2.5rem;">Investment PortFolio Table</div>

															

														</div>
														<table style="background-color: white;"
															class="table table-bordered">
															<thead>
																<tr>
																	<th>Year</th>
																	<th>Income</th>
																	<th ng-hide="!nospouse">Spouse Income</th>
												 					<th>Expense</th>
																	<th>FDI</th>
																	<th>User Fica SST</th>
																	<th ng-hide="!nospouse">Spouse Fica SST</th>
																	<th>Fica Medicare</th>
																	<th>State Tax</th>
																	<th>Saving</th>
																	<th>Ending Taxable Investment Amount</th>
																	<th>Taxable Investment Income</th>
																	<th>Ending Non Taxable Investment Amount</th>
																	<th>Non Taxable Investment Income</th>
																</tr>
															</thead>
															<tbody>
															
																<tr ng-repeat="value1 in Porfolio_tableIncome ">
																	<th scope="row">{{value1.year}}</th>
																	<td>{{Math.round(value1.value)}}</td>
																	<!-- <td>{{value1.year}}</td> -->
																	<td ng-hide="!nospouse">{{Math.round(value1.spouseValue)}}</td>
																	<td>{{Math.ceil(value1.expense)}}</td>
																	<td>{{Math.round(value1.federalTax)}}</td>
																	<td>{{Math.round(value1.userfICASocialSecurityTax)}}</td>
																	<td ng-hide="!nospouse">{{Math.round(value1.spousefICASocialSecurityTax)}}</td>
																	<td>{{Math.round(value1.fICAMedicareTax)}}</td>
																	<td>{{Math.round(value1.stateTax)}}</td>
																	<td>{{Math.round(value1.savings)}}</td>
																	<td>{{Math.round(value1.taxable_investment_amount)}}</td>
																	<td>{{Math.round(value1.taxable_investment_income)}}</td>
																	<td>{{Math.round(value1.nontaxable_investment_amount)}}</td>
																	<td>{{Math.round(value1.nonTaxableInvestmentIncome)}}</td>
																</tr>
															</tbody>
														</table>
													</div>
												</div>
											</div>
												
												 <h2 style="text-align:center;">Portfolio Growth chart</h2>
												<nvd3 options="options"  data="data"></nvd3>
													
	 <div class="row">
     <div class="col-md-5" style="position: relative;left: 250px;">
      <h2 style="text-align:center;position: relative;width: 600px;">Portfolio Assets chart</h2>
     
       <select style="width: 20%;position: relative;left: 300px;" data-toogle="tooltip"
				ng-model="startYearPorfolioBR" class="form-control pull-right"
				ng-change="changeYearPorfolioBR(startYearPorfolioBR)">
				<option ng-selected="year == startYearPorfolioBR"
					ng-repeat="year in yearreportsPorfolioBR" ng-value="year">{{year}}</option>
			</select> <span  style="position: relative;left:285px;top: 7px;"class="pull-right">Select Year </span> 

       <nvd3 options="optionsPiePorfolio" data="dataPieChartPorfolioBR" ></nvd3>
     </div>
    <!--  <div class="col-md-6">
      <h3 style="text-align:center;">After Retirement Assets (Investment Portfolio)</h2>
      <select style="width: 20%;" data-toogle="tooltip"
				ng-model="startYearPorfolioAR" class="form-control pull-right"
				ng-change="changeYearPorfolioAR(startYearPorfolioAR)">
				<option ng-selected="year == startYearPorfolioAR"
					ng-repeat="year in yearreportsPorfolioAR" ng-value="year">{{year}}</option>
			</select> <span class="pull-right">Select Year </span> 
			
    <nvd3 options="optionsPiePorfolio" data="dataPieChartPorfolioAR"></nvd3>
     </div> -->
     </div>
												
												
								
								<!-- <button class="btn btn-primary pull-right" id="but" ng-click="show()">Details</button>
 -->
								<!-- 	<div id="chart-container1"style="height: 300px">
									
									</div>
									 -->

								<!-- <div id="chart-container2"
									style="height: 300px;  margin:0 auto"> -->

								<!-- 	                <button class="btn btn-primary pull-right" id="but" ng-click="show()">Details</button> -->




								<div class="container" ng-show="InVisible">
									<center>{{message}}</center>

								</div>



								<div class="contentcenter-data">
									

									<div class="form-group" ng-show="IsVisible"
										style="FONT-SIZE: small;">
										<button type="button" ng-click="show1()"
											class="btn btn-primary pull-right">Back</button>
										<p>Details of goals</p>
										<form>
											<div class="form-group">
												<div class="input-group">
													<div class="input-group-addon">
														<i class="fa fa-search"></i>
													</div>
													<input type="text" class="form-control"
														placeholder="Search" ng-model="searchGoals">
												</div>
											</div>
										</form>
										<!--  <div id="exportable">
								<table class="table table-bordered table-striped">

									<thead>
										<tr>
											<td><a href="#"
												ng-click="sortType = 'name'; sortReverse = !sortReverse">
													Year <span ng-show="sortType == 'name' && !sortReverse"
													class="fa fa-caret-down"></span> <span
													ng-show="sortType == 'name' && sortReverse"
					    								class="fa fa-caret-up"></span>
											</a></td>
											<td><a href="#"
												ng-click="sortType = 'AMOUNT'; sortReverse = !sortReverse">
												Income 	 <span
													ng-show="sortType == 'AMOUNT' && !sortReverse"
													class="fa fa-caret-down"></span> <span
													ng-show="sortType == 'AMOUNT' && sortReverse"
													class="fa fa-caret-up"></span>
											</a></td>
											<td><a href="#"
												ng-click="sortType = 'STATUS'; sortReverse = !sortReverse">
											Spouse income	<span
													ng-show="sortType == 'STATUS' && !sortReverse"
													class="fa fa-caret-down"></span> <span
													ng-show="sortType == 'STATUS' && sortReverse"
													class="fa fa-caret-up"></span>
											</a></td>
													<td><a href="#"
												ng-click="sortType = 'STATUS1'; sortReverse = !sortReverse">
											Combined income	<span
													ng-show="sortType == 'STATUS1' && !sortReverse"
													class="fa fa-caret-down"></span> <span
													ng-show="sortType == 'STATUS1' && sortReverse"
													class="fa fa-caret-up"></span>
											</a></td>
												<td><a href="#"
												ng-click="sortType = 'AMOUNT1'; sortReverse = !sortReverse">
													Expense<span
													ng-show="sortType == 'AMOUNT1' && !sortReverse"
													class="fa fa-caret-down"></span> <span
													ng-show="sortType == 'AMOUNT1' && sortReverse"
													class="fa fa-caret-up"></span>
											</a></td>
										</tr>
									</thead>

									<tbody>
										<tr
											ng-repeat="roll in tableIncome | orderBy:sortType:sortReverse | filter:searchGoals">
											<td>{{ roll.year }}</td>
											<td>{{ roll.value}}</td>
											<td>{{ roll.spouseValue }}</td>
											<td>{{ roll.id }}</td>
											<td>{{ roll.expense }}</td>
										</tr>
									</tbody>

								</table>

									</div>
									<button ng-click="exportData()" class="btn btn-primary pull-right">Export</button>
							</div> -->
<!-- 										aa<div id="exportable">
											<table class="table table-bordered table-striped">

												<thead>

													<tr>
														ng-click="sortType = 'hash'; sortReverse = !sortReverse
														<td><a href="#"> #<span
																ng-show="sortType == 'hash' && !sortReverse"
																class="fa fa-caret-down"></span> <span
																ng-show="sortType == 'hash' && sortReverse"
																class="fa fa-caret-up"></span>
														</a></td>

														<td><a href="#"
															ng-click="sortType = 'golas'; sortReverse = !sortReverse">
																Goals <span
																ng-show="sortType == 'golas' && !sortReverse"
																class="fa fa-caret-down"></span> <span
																ng-show="sortType == 'golas' && sortReverse"
																class="fa fa-caret-up"></span>
														</a></td>
														<td><a href="#"
															ng-click="sortType = 'Target_Saving'; sortReverse = !sortReverse">
																Target Savings <span
																ng-show="sortType == 'Target_Saving' && !sortReverse"
																class="fa fa-caret-down"></span> <span
																ng-show="sortType == 'Target_Saving' && sortReverse"
																class="fa fa-caret-up"></span>
														</a></td>
														<td><a href="#"
															ng-click="sortType = 'Year_Accomplished'; sortReverse = !sortReverse">
																Year Goal Accomplished <span
																ng-show="sortType == 'Year_Accomplished' && !sortReverse"
																class="fa fa-caret-down"></span> <span
																ng-show="sortType == 'Year_Accomplished' && sortReverse"
																class="fa fa-caret-up"></span>
														</a></td>

													</tr>
												</thead>
												orderBy:sortType:sortReverse
												<tbody>
													<tr id="no"
														ng-repeat="roll in tablevalues  | orderBy:sortType:sortReverse | filter:searchGoals ">
														<td
															ng-class="{'retirement1' : $index ===0,'cargoal1':$index===1,'kid1':$index===2,'customized1':$index===3,'vacation1':$index===4,'college1':$index===5,'emergency1':$index===6,'housing1':$index===7,'Marriage1':$index>=8}"></td>
														<td>{{ roll.golas }}</td>
														<td>{{ roll.Target_Saving}}</td>
														<td>{{ roll.Year_Accomplished }}</td>

													</tr>
												</tbody>

											</table>

										</div> -->
										<button ng-click="exportData()"
											class="btn btn-primary pull-right">Export</button>
									</div>

								</div>

							</div>


						</div>
							<div class== "form-group" id="incomeProjection">
							<div class="btn btn-primary pull-right"
											style="background-color: #ffffff; border-color: #ffffff;">
											<button type="button" class="btn btn-default"
												ng-click="showReportGraph()" data-dismiss="modal">
												<i class="fa fa-arrow-circle-left" style="font-size: 36px"></i>
											</button>
										</div>
								<div class="input-group">
									<div class="input-group-addon"
										style="background-color: #fff; border: #fff">
										<button type="button" class="close" data-dismiss="modal"></button>
										<p>Income Projection</p>
										<span> Select an income profile</span> <span> Select an
											income profile</span> <select style="width: 25%;" id="sel1"
											ng-model="incomeProfile.name"
											ng-change="getIncomeProfile(incomeProfile.name)">
											<option ng-selected="profile==incomeProfile.name"
												ng-repeat="profile in incomeProfilesForReport"
												ng-value="profile" selected>{{profile}}</option>
										</select> <br> <br> <br>


										<!-- 		<select class="form-control ng-pristine ng-valid" id="sel1" ng-model="formdata.planName" ng-change="goaldetails(formdata.planName)">
										ngRepeat: planName in planName<option ng-selected="planName.name==formdata.planName" ng-repeat=" planName in planName" ng-value="planName.name" class="ng-scope ng-binding" value="pla" selected="selected">pla</option>end ngRepeat: planName in planName

									</select> -->


										<div id="chart-container3">Income Expense chart will
											render here</div>
                                  <nvd3 options="IncomeProfileoptions" data="incomeProfileData" class="with-3d-shadow with-transitions"></nvd3>                
									</div>
								</div>
							</div>
						<!-- 	<div class="modal fade" id="incomeProjection" role="dialog">
		<div class="modal-dialog modal-chartDetails">
			Modal content
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Income Projection</h4>
				</div>
				<div class="modal-body text-center">
					<span> Select an income profile</span> 
					<select style="width: 25%;"
						id="sel1" ng-model="incomeProfile.name"
						ng-change="getIncomeProfile(incomeProfile.name)">
						<option ng-repeat="profile in incomeProfilesForReport" ng-value="profile">{{profile}}</option>
					</select> <br>
					<div id="chart-container3">Income Expense chart will render
						here</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" ng-click="showReportGraph()" data-dismiss="modal" >Close</button>
				</div>
			</div>
		</div>
	</div> -->

						<!-- 	
	<form>
	<div class="form-group">
		<div class="input-group">
			<div class="input-group-addon">
				<i class="fa fa-search"></i>
					</div>
				<input type="text" class="form-control" placeholder="Search"
													ng-model="searchGoals">
							</div>
							</div>
				</form>
	 -->





					</div>
				</div>

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
								<button type="button" ng-click="check1()"
									class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				
				<div id="checkSession1" class="modal fade">
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
                                <button type="button" ng-click="check2()"
                                    class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>



				<!-- 	model for income profile  chart -->
				<!-- 	 	<div class="center-content" >
			<div class="container">
		<div class="modal fade" id="incomeProjection" role="dialog">
		<div class="modal-dialog modal-chartDetails">
			Modal content
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Income Projection</h4>
				</div>
				<div class="modal-body text-center">
					<span> Select an income profile</span> <select style="width: 25%;"
						id="sel1" ng-model="incomeProfile.name"
						ng-change="getIncomeProfile(incomeProfile.name)">
						<option ng-repeat="profile in incomeProfilesForReport" ng-value="profile">{{profile}}</option>
					</select> <br>
					<div id="chart-container3">Income Expense chart will render
						here</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" ng-click="showReportGraph()" data-dismiss="modal" >Close</button>
				</div>
			</div>
		</div>
	</div>
	</div>
			</div>	 -->

			</div>
	</section>
	<script type="text/javascript">
		function showModal() {
			$('#myModal').modal('show');
			$("#b1").click(
					function() {
						// $("#plan").html("<p class='test'>click me</p>")
						var aa = document.getElementById("i1").value;
						// $('#plan').append(aa+"<br>");
						$('#plan').append(
								'<li><a href="goals.jsp"><i class="fa fa-venus "></i>'
										+ aa + '</a></li>');
						$("#myModal").modal('hide');
					});
		}
	</script>
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
	<div id="chartModel" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Modify Income Details</h4>
				</div>
				<div class="modal-body">
					<div style="color: red">{{messageChangeIncome}}</div>
					<p>
					<div class="form-group">
						<label class="checkbox-inline"> <input type="checkbox"
							name="favoriteColors" ng-model="checkboxData.applyThisYear"
							ng-change="selectThisYear()"> Apply for this year only.
						</label> <br></br> <label class="checkbox-inline"> <input
							type="checkbox" name="favoriteColors"
							ng-model="checkboxData.applyGraduallyYear"
							ng-change="selectGraduallyYear()"> Increase the income
							gradually from current year to the changed year.
						</label> <br></br> <label class="checkbox-inline"> <input
							type="checkbox" name="favoriteColors"
							ng-model="checkboxData.applyFutureYear"
							ng-change="selectFutureYear()"> Change the income up to <select
							class="font-dropdown" style="width: 120px;"
							ng-model="checkboxData.year"><option
									ng-repeat="item in chartYear2" value="{{item.label}}">
									{{item.label}}</option>
						</select> year.
						</label> <br></br>
					</div>



					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" ng-click="checkCheckbox()"
						class="btn btn-default">Submit</button>
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

					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	<script src="assets/js/fusioncharts.theme.fint.js"></script>
	<script src="assets/js/fusioncharts.powercharts.js"></script>
	<script src="assets/js/fusioncharts.js"></script>
	<!-- Plugin JavaScript -->
	<script src="js/jquery.easing.min.js"></script>
	<script src="js/jquery.fittext.js"></script>
	<script src="js/wow.min.js"></script>
	<script type="text/javascript" src="assets/js/d3.v2.js"></script> 
 	  	<script src="assets/js/D3-3.5.6.min.js" charset="utf-8"></script>  
   	   <script src="assets/js/nvd3-1.8.3.min.js"></script>
     <script src="assets/js/angular-nvd3.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="js/creative.js"></script>
	

</body>
</html>
