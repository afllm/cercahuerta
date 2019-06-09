'use strict'

moduleNoticias.controller("noticiasViewController", ['$scope', '$http', '$routeParams', '$window', 'sessionService', '$location',
    function ($scope, $http, $routeParams, $window, sessionService, $location) {

        $scope.ob = "noticias";
        $scope.comentario = "";
        $scope.totalPages = 1;

        if (sessionService.getUserName() !== "") {
            $scope.id_tiposusario = sessionService.getTypeUserID();
            $scope.id_usuario = sessionService.getId();
        }
        
         if (!$routeParams.id) {
            $scope.id = 1;
        } else {
            $scope.id = $routeParams.id;
        }
        
        if (!$routeParams.order) {
            $scope.orderURLServidor = "";
            $scope.orderURLCliente = "";
        } else {
            $scope.orderURLServidor = "&order=" + $routeParams.order;
            $scope.orderURLCliente = $routeParams.order;
        }
        
         if (!$routeParams.rpp) {
            $scope.rpp = "100";
        } else {
            $scope.rpp = $routeParams.rpp;
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
            $location.path("noticias/plist/" + $scope.page);
        };

        $scope.editar = function () {
            $location.path('noticias/edit/' + $scope.id);
        };

        $scope.eliminar = function () {
            $location.path('noticias/remove/' + $scope.id + "/" + $scope.page);
        };
        
        $scope.newComment = function(){
            console.log($scope.comentario);
            $scope.saveComment();
            $scope.comentario = "";
            $scope.resetForm('commentForm');
        };
        
        $scope.resetForm = function (formName) {
            var form = $scope[formName];
            form.$setUntouched();
            form.$setPristine();
        };
        
        $scope.saveComment = function(){
            var json = {
                id: null,
                texto: $scope.comentario,
                id_usuario: $scope.id_usuario,
                id_noticia: $scope.id
            };

            $http({
                method: 'GET',
                header: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                url: 'json?ob=comentarios&op=create',
                params: {json: JSON.stringify(json)}
            }).then(function () {
                $scope.success = true;
                $scope.getComments();
            })
        };

        $scope.getComments = function () {
            $http({
                method: 'GET',
                url: 'json?ob=comentarios&op=getcountx&idajena=' + $scope.id
            }).then(function (response) {
                $scope.status = response.status;
                $scope.ajaxDataCommentsNumber = response.data.message;
                $scope.totalPages = Math.ceil($scope.ajaxDataUsuariosNumber / $scope.rpp);
                if ($scope.page > $scope.totalPages) {
                    $scope.page = $scope.totalPages;
                    $scope.update();
                }
                pagination2();
            }, function (response) {
                $scope.ajaxDataCommentsNumber = response.data.message || 'Request failed';
                $scope.status = response.status;
            });

            $http({
                method: 'GET',
                url: 'json?ob=comentarios&op=getpagex&rpp=' + $scope.rpp + '&page=' + $scope.page + '&idajena=' + $scope.id + $scope.orderURLServidor
            }).then(function (response) {
                $scope.status = response.status;
                $scope.ajaxDataComments = response.data.message;
                console.log($scope.ajaxDataComments);
                $scope.update();

            }, function (response) {
                $scope.status = response.status;
                $scope.ajaxDataComments = response.data.message || 'Request failed';
            });
            
        };
        
         $scope.getComments();

        //paginacion neighbourhood
        function pagination2() {
            $scope.list2 = [];
            $scope.neighborhood = 3;
            for (var i = 1; i <= $scope.totalPages; i++) {
                if (i === $scope.page) {
                    $scope.list2.push(i);
                } else if (i <= $scope.page && i >= ($scope.page - $scope.neighborhood)) {
                    $scope.list2.push(i);
                } else if (i >= $scope.page && i <= ($scope.page - -$scope.neighborhood)) {
                    $scope.list2.push(i);
                } else if (i === ($scope.page - $scope.neighborhood) - 1) {
                    $scope.list2.push("...");
                } else if (i === ($scope.page - -$scope.neighborhood) + 1) {
                    $scope.list2.push("...");
                }
            }
        }
        
        $scope.update = function () {
           $location.path($scope.ob + `/view/` + $scope.id + `/` + $scope.page);
        }

    }

]);
