'use strict'

moduleUsuario.controller('usuarioViewController', ['$scope', '$http', '$location', 'toolService', '$routeParams', 'sessionService', "$window",
    function ($scope, $http, $location, toolService, $routeParams, sessionService, $window) {

        $scope.ob = "usuario";
        $scope.permitido = false;

        if (!$routeParams.id) {
            $scope.id = 1;
        } else {
            $scope.id = $routeParams.id;
        }

        if (sessionService.getUserName() !== "") {
            $scope.id_usuario = sessionService.getId();
            $scope.id_tiposusario = sessionService.getTypeUserID();

            if ($scope.id_usuario == $scope.id || $scope.id_tiposusario == 1) {
                $scope.permitido = true;
                $http({
                    method: 'GET',
                    url: 'json?ob=' + $scope.ob + '&op=get&id=' + $scope.id
                }).then(function (response) {
                    $scope.status = response.status;
                    $scope.ajaxDataUsuarios = response.data.message;
                }, function (response) {
                    $scope.status = response.status;
                    $scope.ajaxDataUsuarios = response.data.message || 'Request failed';
                });

            }
        }





        $scope.volver = function () {
            $window.history.back();
        };


    }

]);
