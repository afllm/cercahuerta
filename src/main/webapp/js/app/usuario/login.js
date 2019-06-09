"use strict";

moduleUsuario.controller("usuarioLoginController", [
    "$scope",
    "$http",
    "toolService",
    "sessionService",
    "$window",
    "$location",
    function ($scope, $http, toolService, sessionService, $window, $location) {




        $scope.volver = function () {
            $window.history.back();
        }

        $scope.logged = false;
        $scope.failedlogin = false;
        $scope.error = "";
        
        $scope.logging = function () {
            sessionService.setSessionInactive();
            var login = $scope.login;
            var pass = forge_sha256($scope.pass);
            //var pass = $scope.pass;


            $http({
                method: 'GET',
                header: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                url: 'json?ob=usuario&op=login&user=' + login + '&pass=' + pass
            }).then(function (response) {
                if (response.data.message.id !== 0) {
                    
                    $scope.logged = true;
                    $scope.failedlogin = false;
                    sessionService.setSessionActive();
                    sessionService.setUserName(response.data.message.nombre + " " + response.data.message.ape1);
                    $scope.loggeduser = sessionService.getUserName();
                    $scope.loggeduserid = sessionService.setId(response.data.message.id);
                    sessionService.setTypeUserID(response.data.message.obj_tipoUsuario.id);
                    //$location.url('/home');
                    $scope.volver();
                } else {
                    $scope.failedlogin = true;
                    if(response.data.message !=null){
                        $scope.error = ": "+response.data.message;
                    }
                    
                }
   
            }, function (response) {
                $scope.failedlogin = true;
                $scope.logged = false;
                $scope.error = ": "+response.data.message;
            });
        }







        $scope.isActive = toolService.isActive;


    }
]);
