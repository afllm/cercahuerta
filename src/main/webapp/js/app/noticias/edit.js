"use strict";

moduleNoticias.controller("noticiasEditController", [
    "$scope",
    "$http",
    "$routeParams",
    "toolService",
    'sessionService',
    '$window',
    '$location',
    function ($scope, $http, $routeParams, toolService, sessionService, $window, $location) {

        $scope.edited = true;
        $scope.logged = false;

        if (!$routeParams.id) {
            $scope.id = 1;
        } else {
            $scope.id = $routeParams.id;
        }

        $scope.mostrar = false;
        $scope.activar = true;
        $scope.ajaxData = "";

    $scope.isActive = toolService.isActive;

        $http({
            method: "GET",
            url: 'json?ob=noticias&op=get&id=' + $scope.id
        }).then(function (response) {
            console.log(response);
//            $scope.status = response.status;
            $scope.id = response.data.message.id;
            $scope.titulo = response.data.message.titulo;
            $scope.mensaje = response.data.message.mensaje;
            $scope.foto = response.data.message.foto;
            $scope.obj_usuario = {
                id: response.data.message.obj_usuario.id,
                nombre: response.data.message.obj_usuario.nombre,
                ape1: response.data.message.obj_usuario.ape1
            }

        }), function (response) {
            console.log(response);
        };

       

        $scope.update = function () {
            var nombreFoto;

            if ($scope.fotoNueva !== undefined) {
                nombreFoto = $scope.fotoNueva.name;
                $scope.uploadFile(nombreFoto);
            } else {
                if ($scope.foto != '' || $scope.foto != null) {
                    nombreFoto = $scope.foto;
                } else {
                    nombreFoto = "default.jpeg";
                }
            }

            var json = {
                id: $scope.id,
                titulo: $scope.titulo,
                mensaje: $scope.mensaje,
                foto: nombreFoto,
                id_usuario: $scope.obj_usuario.id
            }

            $http({
                method: 'GET',
                header: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                url: 'json?ob=noticias&op=update',
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
                    //$scope.status = response.status;
                    form.userForm.obj_usuario.$setValidity('valid', false);
                });
            } else {
                form.userForm.obj_usuario.$setValidity('valid', true);
            }
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

        $scope.uploadFile = function (nombreFoto) {
            //Solucion mas cercana
            //https://stackoverflow.com/questions/37039852/send-formdata-with-other-field-in-angular
            var file = $scope.fotoNueva;
            //Cambiar el nombre del archivo
            //https://stackoverflow.com/questions/30733904/renaming-a-file-object-in-javascript
            file = new File([file], nombreFoto, {type: file.type});
            //console.log(file)
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
