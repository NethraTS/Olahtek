		var formApp = angular.module('portfolio', []);
		function prtfolioController($scope, $http) {
			// create a blank object to hold our form information
			// $scope will allow this to pass between controller and view
		$scope.show=1;
		$scope.firstQuestion="6";
		$scope.secondQuestion="6";
		$scope.myVar="";
		$scope.thirdQuestion="6";
		$scope.fourthQuestion="1";
		$scope.formData={};
		
		
		$scope.next=function()
		{
			$scope.show++;
			//alert($scope.thirdQuestion);
			
		}
		$scope.back=function()
		{
			$scope.show--;
			
		};
		$scope.submitPortfolio1=function(){
			$scope.firstQuestion;
			$scope.secondQuestion;
			$scope.thirdQuestion;
			$scope.fourthQuestion;
			//alert(($scope.fourthQuestion*1)+($scope.firstQuestion*1)+($scope.secondQuestion*1)+($scope.thirdQuestion*1));
			//$scope.sessionDelete.sessionID=readCookie("AhTwxlO");
			//$scope.visitordat.user_id="user_id"
			$scope.formData.riskScore=($scope.fourthQuestion*1)+($scope.firstQuestion*1)+($scope.secondQuestion*1)+($scope.thirdQuestion*1);
			 alert(JSON.stringify($scope.formData));	
			$http(
						{
							method : 'POST',
							url : 'investmentPortfolio',
							data : $.param($scope.formData),
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded'
							}
						}).then(function(result) {

					$scope.vistorcounts = result.data.visitorcount;

				}, function(error) {
					$scope.message = result.data;

				});
			

			
		};
		
		}