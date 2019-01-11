'use strict'

cercahuerta.config(['$locationProvider', function ($locationProvider) {
        $locationProvider.html5Mode(true);
    }]);
cercahuerta.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
    }]);