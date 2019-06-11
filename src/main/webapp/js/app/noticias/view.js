'use strict'

moduleNoticias.controller("noticiasViewController", ['$scope', '$http', '$routeParams', '$window', 'sessionService', '$location',
    function ($scope, $http, $routeParams, $window, sessionService, $location) {

        $scope.ob = "noticias";
        $scope.comentario = "";
        $scope.totalPages = 1;
        $scope.canComment = false;
        $scope.noComments=false;
        
        

        if (sessionService.getUserName() !== "") {
            $scope.id_tiposusario = sessionService.getTypeUserID();
            $scope.id_usuario = sessionService.getId();
            if($scope.id_tiposusario==1 || $scope.id_tiposusario==2){
                $scope.canComment = true;
            }
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
            $scope.rpp = "10";
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
        
         if (!$routeParams.pageComments) {
            $scope.pageComments = 1;
        } else {
            if ($routeParams.page >= 1) {
                $scope.pageComments = $routeParams.pageComments;
            } else {
                $scope.pageComments = 1;
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
        
                $scope.getComments = function () {
            $http({
                method: 'GET',
                url: 'json?ob=comentarios&op=getcountx&idajena=' + $scope.id
            }).then(function (response) {
                $scope.status = response.status;
                $scope.ajaxDataCommentsNumber = response.data.message;
                console.log('$scope.totalPages: ' + $scope.totalPages);
                console.log('$scope.ajaxDataCommentsNumber: ' + $scope.ajaxDataCommentsNumber);
                $scope.totalPages = Math.ceil($scope.ajaxDataCommentsNumber / $scope.rpp);
                console.log('$scope.totalPages: ' + $scope.totalPages);
                if ($scope.pageComments > $scope.totalPages) {
                    $scope.pageComments = $scope.totalPages;
                    $scope.update();
                }
                if($scope.ajaxDataCommentsNumber==0){
                    $scope.noComments=true;
                }else{
                  $scope.noComments=false;  
                }
                pagination2();
            }, function (response) {
                $scope.ajaxDataCommentsNumber = response.data.message || 'Request failed';
                $scope.status = response.status;
            });
            var urlprueba= 'json?ob=comentarios&op=getpagex&rpp=' + $scope.rpp + '&page=' + $scope.pageComments  + '&idajena=' + $scope.id + '&order=id,desc';
            
                console.log('urlprueba: ' + urlprueba);
            $http({
                method: 'GET',
                url: 'json?ob=comentarios&op=getpagex&rpp=' + $scope.rpp + '&page=' + $scope.pageComments  + '&idajena=' + $scope.id + '&order=id,desc'
            }).then(function (response) {
                $scope.status = response.status;
                $scope.ajaxDataComments = response.data.message;
                $scope.update();

            }, function (response) {
                $scope.status = response.status;
                $scope.ajaxDataComments = response.data.message || 'Request failed';
            });
            
        };
        
         $scope.getComments();
        
        $scope.newComment = function(){
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
                $scope.pageComments = 1;
                $scope.getComments();
                //$location.path($scope.ob + `/view/` + $scope.id + `/` + $scope.page + `/1`);
            })
        };



        //paginacion neighbourhood
        function pagination2() {
            $scope.list2 = [];
            $scope.neighborhood = 3;
            for (var i = 1; i <= $scope.totalPages; i++) {
                if (i === $scope.pageComments) {
                    $scope.list2.push(i);
                } else if (i <= $scope.pageComments && i >= ($scope.pageComments - $scope.neighborhood)) {
                    $scope.list2.push(i);
                } else if (i >= $scope.pageComments && i <= ($scope.pageComments - -$scope.neighborhood)) {
                    $scope.list2.push(i);
                } else if (i === ($scope.pageComments - $scope.neighborhood) - 1) {
                    $scope.list2.push("...");
                } else if (i === ($scope.pageComments - -$scope.neighborhood) + 1) {
                    $scope.list2.push("...");
                }
            }
        }
        
        $scope.update = function () {
           $location.path($scope.ob + `/view/` + $scope.id + `/` + $scope.page + `/` + $scope.pageComments);
           console.log('$scope.pageComments: ' + $scope.pageComments);
        }

    }

]);
