'use strict';

/**
 * Route configuration for the RDash module.
 */
angular.module('wealthsetter').config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
        // For unmatched routes
        $urlRouterProvider.otherwise('/');

        // Application routes
        $stateProvider
            .state('index', {
                url: '/',
                templateUrl: 'templates/home.html',
                controller:'HomeCtrl'
            })
            .state('welcome', {
                url: '/welcome',
                templateUrl: 'templates/welcome.html',
                controller:'WelcomeCtrl'
            })
            .state('welcome.0', {
                url: '/basic',
                templateUrl: 'templates/welcome.basic.html'
            })
            .state('welcome.1', {
                url: '/income-expense',
                templateUrl: 'templates/welcome.income-expense.html'
            })
            .state('welcome.2', {
            	url : '/investmentportfolio',
            	templateUrl : 'templates/investmentportfolio.basic.html'
            })
            .state('welcome.3', {
            	url : '/stocks-bonds',
            	templateUrl : 'templates/investmentportfolio.stocks-bonds.html'
            })
            .state('welcome.4', {
            	url : '/user-interest',
            	templateUrl : 'templates/investmentportfolio.user-interest.html'
            })
            .state('welcome.5', {
            	url : '/etfs',
            	templateUrl : 'templates/investmentportfolio.etfs.html'
            })
            .state('welcome.6', {
            	url : '/profit-loss',
            	templateUrl : 'templates/investmentportfolio.profit-loss.html'
            })
            .state('welcome.7', {
                url: '/assets',
                templateUrl: 'templates/welcome.assets.html'
            })
            .state('dashboard', {
                url: '/dashboard',
                templateUrl: 'templates/dashboard.html',
                controller:'DashboardCtrl'
            })
        	.state('dashboard.home',{
        		url: '/home',
                templateUrl: 'templates/dashboard.home.html'
        	})
        	.state('goals',{
        		url: '/goals',
                templateUrl: 'templates/goals.html'
        	})
        	.state('goals.emergency',{
        		url: '/goals.emergency',
                templateUrl: 'templates/goals.emergency.html',
                controller:'EmergencyCtrl'
        	})
        	.state('dashboard.tables',{
                url: '/tables',
                templateUrl: 'templates/dashboard.tables.html'
            })
            .state('calculator', {
                url: '/calculator',
                templateUrl: 'templates/calculator.html'
               
            })
           /* .state('calculator.mortgage', {
                url: '/mortgage',
                templateUrl: 'templates/calculator.mortgage.html'
            })*/
           /* .state('carloan', {
                url: '/carloan',
                templateUrl: 'templates/calculator.carloan.html',
                controller:'CarloanCtrl'
            })*/
            /*.state('calculator.carlease', {
                url: '/carlease',
                templateUrl: 'templates/calculator.carlease.html'
            })*/
            .state('mortgageresult', {
                url: '/mortgage-result',
                templateUrl: 'templates/mortgage-result.html',
                controller:'MortgageCtrl'
            })
            .state('carloanresult', {
                url: '/carloan-result',
                templateUrl: 'templates/carloan-result.html',
                controller:'CarloanCtrl'
            })
            .state('ssb', {
                url: '/ssb-calculator',
                templateUrl: 'templates/calculator.ssb.html',
                controller : 'SsbCtrl'
            })
             .state('carleaseresult', {
                url: '/carlease-result',
                templateUrl: 'templates/carlease-result.html',
                controller:'CarleaseCtrl'
            })
	        .state('income-tax-calculator', {
	            url: '/income-tax-calculator',
	            templateUrl: 'templates/calculator.income-tax.html',
	            controller : 'IncometaxCtrl'
	        });
}
]);

function readCookie(name) {
	// ////alert( "hi" );
	var nameEQ = name + "=";
	var ca = document.cookie.split(";");
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == " ")
			c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) == 0)
			return c.substring(nameEQ.length, c.length);
	}
	return null;
}
angular.module('wealthsetter').run(['$rootScope', '$location','$state', 'Auth','$cookieStore', function ($rootScope, $location,$state, Auth,$cookieStore) {
	console.log(Auth.isLoggedIn());
	
	$rootScope.$on('$locationChangeStart', function (event) {
    	
		 if ($location.path() !== '/' && !Auth.isLoggedIn()) {
             $location.path('/');
         }
        
    });
}]);