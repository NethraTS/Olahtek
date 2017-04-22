var formApp = angular.module('App', []);
$scope.main='Login Page';
		// create angular controller and pass in $scope and $http
		function formController($scope, $http) {
			// create a blank object to hold our form information
			// $scope will allow this to pass between controller and view
            
			$scope.formData = {};
			// process the form
			$scope.processForm = function() {

			  //  $scope.message=$scope.formData.name;
			   if(!$scope.formData.name)
			    {
			    $scope.errorName = 'Name is empty';
			    		    }
			    
			    else if(!$scope.formData.password)
			    {
			     $scope.errorSuperhero = "Password is empty";	
			    }
			    
else if($scope.formData.name=='bala')
			    {
			     $scope.message='Success';
			     $scope.errorName='';
$scope.errorSuperhero='';
			    }
			    else
			    {
              $scope.message='Fail';
$scope.errorName='';
$scope.errorSuperhero='';			    
			    }
			    };
		}