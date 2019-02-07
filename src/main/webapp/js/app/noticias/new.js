"use strict";

moduleNoticias.controller("noticiasNewController", [
    "$scope",
    "$http",
    "$routeParams",
    "toolService",
    'sessionService',
    function ($scope, $http, $routeParams, toolService, sessionService) {

        $scope.edited = true;
        $scope.logged = false;

        if (!$routeParams.id) {
            $scope.id = 1;
        } else {
            $scope.id = $routeParams.id;
        }
        $scope.obj_usuario = {
            id: null,
            nombre: null,
            ape1:null
        }
        $scope.mostrar = false;
        $scope.activar = true;
        $scope.ajaxData = "";


        $scope.isActive = toolService.isActive;

        $scope.update = function () {
            $scope.uploadFile();
            var nombreFoto;
            if ($scope.myFile === undefined) {
                nombreFoto = "default.jpeg";
            } else {
                nombreFoto = $scope.myFile.name;
            }


            var json = {
                id: null,
                titulo: $scope.titulo,
                mensaje: $scope.mensaje,
                foto: nombreFoto,
                id_usuario: $scope.obj_usuario.id //cambiar a usuario activo
            }

            $http({
                method: 'GET',
                header: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                url: 'json?ob=noticias&op=create',
                params: {json: JSON.stringify(json)}
            }).then(function () {
                $scope.edited = false;
            })
        }

     $scope.usuarioRefresh = function (f, consultar) {
            var form = f;
            if (consultar) {
                $http({
                    method: 'GET',
                    url: 'json?ob=usuario&op=get&id=' + $scope.obj_usuario.id
                }).then(function (response) {
                    $scope.obj_usuario = response.data.message;
                    form.userForm.obj_usuario.$setValidity('valid', true);
                }, function (response) {
                    form.userForm.obj_usuario.$setValidity('valid', false);
                });
            } else {
                form.userForm.obj_usuario.$setValidity('valid', true);
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
        $scope.uploadFile = function () {
            var file;
            //Solucion mas cercana
            //https://stackoverflow.com/questions/37039852/send-formdata-with-other-field-in-angular
            file = $scope.myFile;

            //Api FormData 
            //https://developer.mozilla.org/es/docs/Web/API/XMLHttpRequest/FormData
            var oFormData = new FormData();
            oFormData.append('file', file);
            $http({
                headers: {'Content-Type': undefined},
                method: 'POST',
                data: oFormData,
                url: `json?ob=noticias&op=loadimage`
            })
            /*.then(function (response) {
             console.log(response);
             }, function (response) {
             console.log(response);
             });*/
        };
    }]).directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        }
    }]);