'use strict'

moduleNoticias.controller("noticiasRemoveController", ['$scope', '$http', '$routeParams', '$window', 'sessionService','$location',
    function ($scope, $http, $routeParams, $window, sessionService, $location) {

        $scope.ob = "noticias";
        $scope.tabla = true;
        $scope.msgopcioneliminar = true;
        
//        if (sessionService.getUserName() !== "") {
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
        
        $scope.volverAtras ="noticias/view/" + $scope.id;

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


        $scope.eliminar = function (accion) {
            if (accion === "eliminar") {
                $http({
                    method: 'GET',
                    url: 'json?ob=' + $scope.ob + '&op=remove&id=' + $scope.id
                }).then(function (response) {
                    $scope.eliminarok = true;
                    $scope.msgopcioneliminar = false;
                    $scope.eliminarerror = false;
                    $scope.tabla = false;
                    $scope.status = response.status;
                    $scope.ajaxDataEliminar = response.data.message;
                    $scope.volverAtras = "noticias/plist/"+$scope.page;
                }, function (response) {
                    $scope.ajaxDataEliminar = response.data.message || 'Request failed';
                    $scope.status = response.status;
                    
                });
            } else {
                $scope.eliminarerror = true;
                $scope.msgopcioneliminar = false;
                $scope.eliminarok = false;
                $scope.tabla = true;                
            }

        };


        $scope.volver = function () {
            $location.path($scope.volverAtras);
        }

    }

]);
