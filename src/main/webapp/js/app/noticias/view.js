'use strict'

moduleNoticias.controller("noticiasViewController", ['$scope', '$http', '$routeParams', '$window', 'sessionService', '$location',
    function ($scope, $http, $routeParams, $window, sessionService, $location) {

        $scope.ob = "noticias";
//     if (sessionService.getUserName() !== "") {
//            $scope.loggeduser = sessionService.getUserName();
//            $scope.loggeduserid = sessionService.getId();
//            $scope.logged = true;
//            $scope.tipousuarioID = sessionService.getTypeUserID();
//        }


        if (!$routeParams.id) {
            $scope.id = 1;
        } else {
            $scope.id = $routeParams.id;
        }
        
        if (!$routeParams.page) {
            $scope.page = 1;
        } else {
            if ($routeParams.page >= 1) {
                $scope.page = $routeParams.page;
            } else {
                $scope.page = 1;
            }
        }

        $http({
            method: 'GET',
            url: 'json?ob=' + $scope.ob + '&op=get&id=' + $scope.id
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxData = response.data.message;
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxData = response.data.message || 'Request failed';
        });



        $scope.volver = function () {
            $window.history.back();
        };

        $scope.editar = function () {
            $location.path('noticias/edit/' + $scope.id);
        };

        $scope.eliminar = function () {
            $location.path('noticias/remove/' + $scope.id+"/"+$scope.page);
        };



    }

]);
