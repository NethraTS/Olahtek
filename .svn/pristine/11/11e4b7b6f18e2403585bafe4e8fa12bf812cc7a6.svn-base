angular.module('wealthsetter').directive("scroll", function ($window,$state) {
    return function(scope, element, attrs) {
    		var page = $state.current.name.split('.')[0];
    		angular.element($window).bind("scroll", function() {
	             if (this.pageYOffset >= 50 ) {
	                 scope.boolChangeClass = true;              
	             }else if($state.current.name != "index"){
	            	 scope.boolChangeClass = true;
	             } else {
	                 scope.boolChangeClass = false;
	             }
	            scope.$apply();
	        });
    	}

        
   
});