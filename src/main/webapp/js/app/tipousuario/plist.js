'use strict'

moduleTipousuario.controller('tipousuarioPlistController', ['$scope', '$http', '$location', 'toolService', '$routeParams','sessionService',
    function ($scope, $http, $location, toolService, $routeParams, sessionService) {

        $scope.ob = "tipousuario";
        $scope.totalPages = 1;
//       if (sessionService.getUserName() !== "") {
//            $scope.loggeduser = sessionService.getUserName();
//            $scope.loggeduserid = sessionService.getId();
//            $scope.logged = true;
//            $scope.tipousuarioID = sessionService.getTypeUserID();
//        }

        if (!$routeParams.order) {
            $scope.orderURLServidor = "";
            $scope.orderURLCliente = "";
             $scope.align = "asc";
        } else {
            $scope.orderURLServidor = "&order=" + $routeParams.order;
            $scope.orderURLCliente = $routeParams.order;
             $scope.align = $routeParams.order.split(',')[1];
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


        $scope.resetOrder = function () {
            $location.url($scope.ob + `/plist/` + $scope.rpp + `/` + $scope.page);
        }

        $scope.view = function (id) {
            $location.url($scope.ob + `/view/${id}`);
        }

        $scope.remove = function (id) {
            $location.url($scope.ob + `/remove/${id}`);
        }

        $scope.edit = function (id) {
            $location.url($scope.ob + `/edit/${id}`);
        }


  $scope.ordena = function (order) {
            
            if ($scope.align == "desc") {
                $scope.align = "asc";

            } else {
                $scope.align = "desc";
            }

            $scope.orderURLServidor = "&order=" + order + "," + $scope.align;
            $scope.orderURLCliente = order + "," + $scope.align;
            $location.url($scope.ob + `/plist/` + $scope.rpp + `/` + $scope.page + `/` + $scope.orderURLCliente);
        }


        //getcount
        $http({
            method: 'GET',
            url: 'json?ob=' + $scope.ob + '&op=getcount'
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataUsuariosNumber = response.data.message;
            $scope.totalPages = Math.ceil($scope.ajaxDataUsuariosNumber / $scope.rpp);
            if ($scope.page > $scope.totalPages) {
                $scope.page = $scope.totalPages;
                $scope.update();
            }
            pagination2();
        }, function (response) {
            $scope.ajaxDataUsuariosNumber = response.data.message || 'Request failed';
            $scope.status = response.status;
        });

        $http({
            method: 'GET',
            url: 'json?ob=' + $scope.ob + '&op=getpage&rpp=' + $scope.rpp + '&page=' + $scope.page + $scope.orderURLServidor
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataUsuarios = response.data.message;
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxDataUsuarios = response.data.message || 'Request failed';
        });



        $scope.update = function () {
            $location.url($scope.ob + `/plist/` + $scope.rpp + `/` + $scope.page + '/' + $scope.orderURLCliente);
        }




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

        $scope.isActive = toolService.isActive;
       
    }
]);
