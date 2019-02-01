"use strict";

moduleFactura.controller("lineaNewController", [
    "$scope",
    "$http",
    "$routeParams",
    "toolService",
    "$window",
    'sessionService',
    function ($scope, $http, $routeParams, toolService, $window, sessionService) {

        $scope.edited = true;
        $scope.ob = "linea";

        $scope.obj = null;

        $scope.op = 'create';
        $scope.result = null;
        $scope.title = "Nueva linea";
        $scope.icon = "fa-file-text-o";


//        if (sessionService.getUserName() !== "") {
//            $scope.loggeduser = sessionService.getUserName();
//            $scope.loggeduserid = sessionService.getId();
//            $scope.logged = true;
//            $scope.tipousuarioID = sessionService.getTypeUserID();
//        }

            $scope.id = null;
    
    $scope.obj_producto = {
            id: null,
            nombre: null
        }

        $scope.isActive = toolService.isActive;

        $scope.update = function () {

            var json = {
              cantidad: $scope.cantidad,
              id_factura: $scope.id_factura,
              id_producto:  $scope.obj_producto.id

            }
            $http({
                method: 'GET',
                header: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                url: 'json?ob=' + $scope.ob + '&op=create',
                params: {json: JSON.stringify(json)}
            }).then(function () {
                $scope.edited = false;
            })
        }

        $scope.productoRefresh = function (f, consultar) {
            var form = f;
            if (consultar) {
                $http({
                    method: 'GET',
                    url: 'json?ob=producto&op=get&id=' + $scope.obj_producto.id
                }).then(function (response) {
                    $scope.obj_producto = response.data.message;
                    form.userForm.obj_producto.$setValidity('valid', true);
                }, function (response) {
                    form.userForm.obj_producto.$setValidity('valid', false);
                });
            } else {
                form.userForm.obj_producto.$setValidity('valid', true);
            }
        };
        
        $scope.facturaRefresh = function (f, consultar) {
            var form = f;
            if (consultar) {
                $http({
                    method: 'GET',
                    url: 'json?ob=factura&op=get&id=' + $scope.obj_factura.id
                }).then(function (response) {
                    $scope.obj_factura = response.data.message;
                    form.userForm.obj_factura.$setValidity('valid', true);
                }, function (response) {
                    form.userForm.obj_factura.$setValidity('valid', false);
                });
            } else {
                form.userForm.obj_factura.$setValidity('valid', true);
            }
        }

        $scope.back = function () {
            window.history.back();
        };
        $scope.close = function () {
            $location.path('/home');
        };
        $scope.plist = function () {
            $location.path('/' + $scope.ob + '/plist');
        };



        $scope.volver = function () {
            $window.history.back();
        }



    }
]);
