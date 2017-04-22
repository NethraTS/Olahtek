<!DOCTYPE html>
<html lang="en" ng-app="dashboard">
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
<script src="js/dashboardAdmin.js"></script>
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
</style>
<script>
	
</script>
<style>
.sel {
	color: green;
}
</style>
</head>
<body id="page-top" ng-controller="dashboardController"
	onload="editchart()" ng-init="load();" ng-cloak>
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
				<a class="navbar-brand page-scroll" href="Admindashboard.jsp">WealthSetter</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a class="page-scroll" href="Admindashboard.jsp">Home</a>
					</li>
					<!-- <li><a class="page-scroll" href="#">How it works?</a></li>-->
					<li> 
						<!--   <a class="page-scroll" href="Report.jsp">Reports</a> --> <a
						href="#" ng-click="report()">Reports</a>
					</li>


				<!-- 	<li><a class="page-scroll" href="#" ng-click="checkSave()"><i
							class="fa fa-user-plus"></i> My Profile</a></li> -->

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


				<div style="display: none" class="alert alert-warning text-center"
					id="warning-alert">
					<button type="button" class="close" ng-click="hideWarning()">x</button>
					<strong>Warning! </strong> {{SuccessMessage}}
				</div>

				<div style="display: none" class="alert alert-success text-center"
					id="success-alert">
					<button type="button" class="close" ng-click="hideSuccess()">x</button>
					<strong>Success! </strong> {{SuccessMessage}}
				</div>
				<div style="display: none" class="alert alert-success text-center"
					id="report-alert">
					<button type="button" class="close" ng-click="hideSuccess()">x</button>

					{{SuccessMessage1}}
				</div>
				<div class="title-createplan">{{userName}}</div>
				<div class="content-panel">
					<div class="side-panel">
						<div class="sidebar-collapse">
							<ul class="nav" id="main-menu">


								<li ng-click="planchange(planName.name,$index)"
									ng-repeat="planName in planNames1"
									ng-class="{'active-menu' : $index == selected}"><a
									style="cursor: pointer; cursor: hand;"> <!-- <input type="text" > -->
										</i>{{planName.name}}
								</a></li>

								<!--     <li>
                        <a href=#  onclick="return showModal()"></i>Create Plan</a>
                                              
                    </li> -->


								<li ng-hide="!createPlan">
									<div class="text-center">
										<br>
									<!-- <label>No PLans</label> -->
										<br> <br>
										<div ng-hide="!planCountOnload">
											
											</br>
											</br> </br>
										</div>
									</div>
								</li>


								<li ng-hide="!onClickCreatePlan">

									<div class="form-group floating-label-form-group controls">
										<span style="color: red; font-size: 85%;">{{emptyMassage}}</span>
										<input type="text" autocomplete="off" style="font-size: 15px;"
											placeholder="Name your financial plan" class="form-control"
											data-toggle="tooltip" title="Name your financial plan here"
											onkeypress="return onlyAlphaNumbers(event,this)"
											maxlength="15" ng-model="formdata.planname">
										<div ng-hide="!hideCheckBox">



											<br> <input type="checkbox" ng-change="showPlanList()"
												ng-model="formdata.checkValue" />Copy an existing Plan


										</div>

										<div ng-hide="!onCheckedCopyPlans">

											<div ng-hide="!hideList">
												<select class="form-control" id="sel1" ng-model="plan">
													<option ng-selected="planName.name==plan"
														ng-repeat="planName in planNames1">{{planName.name}}</option>
												</select>
											</div>
											<br>

											<div ng-hide="!CopyPlanbutton" class="text-center">
												<button type="button" class="btn btn-primary"
													ng-click="copyPlan()">copy</button>
												<button type="button" class="btn btn-primary"
													ng-click="cancle()">Cancel</button>
											</div>
										</div>
									</div> <br>
									<div ng-hide="!CreatePlanbutton" class="text-center">
										<button class="btn btn-primary" ng-click="modalform()">Create</button>
										<button type="button" class="btn btn-primary"
											ng-click="cancle()">Cancel</button>
									</div>
								</li>



								<li ng-hide="!onCheckedRenamePlans">

									<div class="form-group floating-label-form-group controls">
										<span style="color: red; font-size: 85%;">{{emptyMassage}}</span>
										<input type="text" autocomplete="off"
											style="font-style: italic;"
											placeholder="Name your financial plan" class="form-control"
											data-toggle="tooltip" title="Name your financial plan here"
											onkeypress="return onlyAlphaNumbers(event,this)"
											maxlength="15" ng-model="formdata.planname"> <select
											class="form-control" id="sel1" ng-model="plan">
											<option ng-selected="planName.name==plan"
												ng-repeat="planName in planNames1">{{planName.name}}</option>
										</select> <br>
										<div class="text-center">
											<button type="button" class="btn btn-primary"
												ng-click="RenamePlanName()">Rename</button>
											<button type="button" class="btn btn-primary"
												ng-click="cancle()">cancel</button>
										</div>
									</div> <br>
								</li>



							</ul>
						</div>
					</div>

					<div class="center-content">






						<div class="contentcenter-data">



							<br></br>
								<div class="col-xs-6 col-sm-8 col-md-12" ng-hide="!registrationIncomplete">
								Currently the user has not completed the registration completely. After his registration is complete you will be able to view his income profile and plans.
								</div>

							<div class="col-xs-6 col-sm-8 col-md-12" ng-hide="!registrationComplete">
								<!-- {{income}} -->
								{{messagehome}}

								<div ng-hide="!notNewUser">
									<div ng-hide="!planCountOnload">
										<!-- <button type="button" class="btn btn-primary pull-left"
											ng-click="deletePlan()">Delete Plan</button> -->

										<!-- <button type="button" class="btn btn-primary pull-right"
											ng-click="goSelectGoals()">Add Goals</button> -->
										&nbsp

										<!-- <label >Select Project Year :</label> -->

									</div>
								</div>

								<br></br>

								<div class="col-md-12">
									<div ng-hide="!notNewUser">
										<div class="form-group">
											<div ng-hide="!noGoals">
												<span ng-repeat=" goals1 in goals1"> <span
													style="cursor: pointer; cursor: hand;" class="addGoals2"
													ng-click="changeGoals(goals1.name);goEditGoals()"><i
														class="fa fa-photo text-center">{{goals1.name}}</i> </span>

												</span>
												<!--  <label for="sel1">Select Goal:</label>
  <select  class="form-control" id="sel1" ng-model="firstgoal" ng-change="changeGoals(firstgoal)" >
   <option  ng-selected="goals1.name==firstgoal" ng-repeat=" goals1 in goals1" ng-value="goals1.name"  >{{goals1.name}}</option>
    
  </select> -->

											</div>
										</div>


									</div>

								</div>


								<div class="modal fade" id="myModal2" role="dialog">
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
												<button type="button" id="dialog-ok"
													ng-click="goDeleteGoals()"
													class="btn btn-primary pull-right" data-dismiss="modal">
													<i class="fa fa-trash-o"></i>Delete
												</button>

												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
											</div>
										</div>

									</div>
								</div>

								<div id="chartDetails" class="modal fade" role="dialog">
									<div class="modal-dialog modal-chartDetails">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h4 class="modal-title">Income-Expense Details (Yearly)</h4>
											</div>
											<div class="modal-body">

												<table class="table table-bordered">
													<thead>
														<tr>
															<th>User Age</th>
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
															<th>Taxable Investment Amount</th>
															<th>Non Taxable Investment Amount</th>

														</tr>
													</thead>
													<tbody>
														<tr ng-repeat="value1 in tableIncome">
															<th scope="row">{{Math.ceil(value1.id)}}</th>
															<td>{{Math.ceil(value1.year)}}</td>
															<td>{{Math.ceil(value1.value)}}</td>
															<td ng-hide="!nospouse">{{Math.ceil(value1.spouseValue)}}</td>
															<td>{{Math.ceil(value1.expense)}}</td>
															<td>{{Math.ceil(value1.federalTax)}}</td>
															<td>{{Math.ceil(value1.userfICASocialSecurityTax)}}</td>
															<td ng-hide="!nospouse">{{Math.ceil(value1.spousefICASocialSecurityTax)}}</td>
															<td>{{Math.ceil(value1.fICAMedicareTax)}}</td>
															<td>{{Math.ceil(value1.stateTax)}}</td>
															<td>{{Math.ceil(value1.savings)}}</td>
															<td>{{Math.ceil(value1.taxable_investment_amount)}}</td>
															<td>{{Math.ceil(value1.nontaxable_investment_amount)}}</td>
														</tr>


													</tbody>
												</table>





											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
												<!--  <button type="button"  ng-click="checkCheckbox()" class="btn btn-default" >Submit</button> -->
											</div>
										</div>
									</div>
								</div>



								<!--   <div class="col-md-6">
  
 <div ng-hide="!notNewUser">
	<div class="form-group">

   <div ng-hide="!noGoals">
   <br>
  <button type="submit" class="btn btn-primary" ng-click="goEditGoals()">Edit</button>
  <button type="submit" class="btn btn-primary" ng-click="DeleteGoal()"><i class="fa fa-trash-o"></i>&nbsp Delete</button>
  
 
  </div>
  </div>
	
	
	</div>
  </div> -->

								<div class="col-md-12 form-inline text-center">
									<!-- <a href="#"  data-toggle="modal" data-target="#chartDetails">Details </a>  -->

									<span class="pull-left">Select an income profile</span> <select
										style="width: 18%;" class="form-control pull-left" id="sel1"
										ng-model="incomeProfilesChart"
										ng-change="changeIncomeProfile(incomeProfilesChart)">
										<option ng-selected="profile == incomeProfilesChart"
											ng-repeat="profile in incomeProfiles" ng-value="profile">{{profile}}</option>
									</select> <a href="#" data-toggle="modal" data-target="#chartDetails">Details
									</a> <select style="width: 8%;" data-toogle="tooltip"
										title="Please select an age to view the income projection"
										ng-model="startAge" class="form-control pull-right"
										ng-change="changeAge()">
										<option ng-selected="age.number == startAge"
											ng-repeat="age in ages" ng-value="age.number">{{age.number}}</option>
									</select> <span class="pull-right">Please select an age to change
										your income projection : </span>




								</div>
								<br>
								<div class="col-xs-6 col-sm-8 col-md-12 text-center">
									<div id="chart-container1">Income-Expense graph will
										render here</div>

									<br>
								<!-- 	<button type="submit" class="btn btn-primary "
										ng-click="restore()">Reset</button>
									<button type="submit" class="btn btn-primary "
										data-toggle="modal" data-target="#chartModel">Save</button> -->

									<br></br>
									<div id="chart-container2">Asset-Growth graph will
										render here</div>
									<br>
								</div>
							</div>






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
							ng-change="selectThisYear()"> Change the existing income
							profile.
						</label>
						<!-- <br></br>
        <label class="checkbox-inline">
            <input type="checkbox" name="favoriteColors" ng-model="checkboxData.applyGraduallyYear" ng-change="selectGraduallyYear()"> Increase the income gradually from current year to the changed year.
        </label> -->
						<br></br> <label class="checkbox-inline"> <input
							type="checkbox" name="favoriteColors"
							ng-model="checkboxData.applyFutureYear"
							ng-change="selectFutureYear()"> Create a new income
							profile.

							<div ng-hide="!incomeProfileName" class="class="text-center">
								Please enter the name of the new income profile. <input
									type="tel" maxlength="15" placeholder="" class="input_bl-long"
									class="form-control" ng-model="checkboxData.profileName" />


							</div>
						</label> <br></br>
						<div ng-hide="!plansExist">
							<label class="checkbox-inline"> <input type="checkbox"
								name="favoriteColors" ng-model="checkboxData.applyPlan">
								Apply for current financial plan.
							</label>
						</div>
					</div>



					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" ng-click="save()" class="btn btn-default">Submit</button>
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
					<p>You had made some changes in the income profile, do you want
						to proceed?</p>
				</div>
				<div class="modal-footer">
					<button type="button" id="dialog-ok" ng-click="gouserProfile()"
						class="btn btn-primary pull-right" data-dismiss="modal">Yes</button>

					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
				</div>
			</div>

		</div>
	</div>






	<!--       <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      Modal content
       
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">New Plan</h4>
        </div>
        <form name="sentMessage">
        <div class="modal-body">
        <div style="color:red">{{message1}}</div>
         <h3>Enter the name for the new financial plan<br></br> 
         					    			
			                <input type="text" class="form-control" placeholder="Enter name" name="username" onkeypress="return onlyAlphaNumbers(event,this)" maxlength="15" ng-model="formdata.planname" required ><span style="color:red; font-size: 50%;" ng-show="sentMessage.username.$dirty && sentMessage.username.$invalid">
			<span ng-show="sentMessage.username.$error.required">Financial plan name is required</span>
			</span></p>
			<input type=hidden ng-model="formdata.form" value="form1" ng-init="formdata.form='createNewPlan'">
			                 	<button type="button" class="btn btn-success" id="b1"  ng-disabled="sentMessage.username.$dirty && sentMessage.username.$invalid || sentMessage.$invalid"  ng-click="modalform()">Next</button>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      </form>
    </div>
  </div> -->

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
