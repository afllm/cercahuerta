"use strict";

moduleUsuario.controller("usuarioRegistroController", [
    "$scope",
    "$http",
    "$routeParams",
    "toolService",
    "sessionService",
    "$window",
    "$location",
    function ($scope, $http, $routeParams, toolService, sessionService, $window, $location) {
        $scope.edited = false;
        $scope.logged = false;
        $scope.cargando=false;
        $scope.esperar=false;
        $scope.activado=false;
        $scope.activacionfallida=false;
//        $scope.obj_tipoUsuario = {
//            id: null,
//            desc: null
//        }
        $scope.id = null;

        $scope.mostrar = false;
        $scope.activar = true;
        $scope.ajaxData = "";

        $scope.obj = null;
        $scope.ob = 'usuario';
        $scope.op = 'create';
        $scope.result = null;
        $scope.title = "Registro de usuario";
        $scope.icon = "fa-file-text-o";
        
        if ($routeParams.token) {
            $scope.esperar=true;
            $scope.token = $routeParams.token;
            $http({
                method: 'GET', //http://localhost:8081/cercahuerta/json?ob=usuario&op=activar&token=" + token
                url: 'json?ob=' + $scope.ob + '&op=activar&token=' + $scope.token
            }).then(function (response) {
                $scope.status = response.status;
                $scope.ajaxData = response.data.message;
                $scope.esperar=false;
                $scope.activado=true;
            }, function (response) {
                $scope.activacionfallida=true;
                $scope.status = response.status;
                $scope.ajaxData = response.data.message || 'Request failed';
            });
        }



        $scope.isActive = toolService.isActive;

        $scope.update = function () {
            $scope.cargando=true;
            var json = {
                id: null,
                dni: $scope.dni,
                nombre: $scope.nombre,
                ape1: $scope.ape1,
                ape2: $scope.ape2,
                email: $scope.email,
                login: $scope.login2,
                pass: forge_sha256($scope.pass)//,
                //id_tipoUsuario: $scope.obj_tipoUsuario.id
            }
            
            $http({
                method: 'POST',
                url: 'json?ob=usuario&op=create',
                params: {json: JSON.stringify(json)}
            }).then(function () {
                $scope.cargando=false;
                $scope.edited = true;
            })
        }

        $scope.back = function () {
            $window.history.back();
        };
        $scope.close = function () {
            $location.path('/home');
        };
        $scope.plist = function () {
            $location.path('/' + $scope.ob + '/plist');
        };
        
    }
    
]);
