'use strict'

moduleNoticias.controller('noticiasPlistController', ['$scope', '$http', '$location', 'toolService', '$routeParams', 'sessionService',
    function ($scope, $http, $location, toolService, $routeParams, sessionService) {

        $scope.totalPages = 1;
        $scope.conectado = false;

//        if (sessionService.getUserName() !== "") {
//            $scope.loggeduser = sessionService.getUserName();
//            $scope.loggeduserid = sessionService.getId();
//            $scope.logged = true;
//            $scope.tipousuarioID = sessionService.getTypeUserID();
//        }

        if (!$routeParams.order) {
            $scope.orderURLServidor = "";
            $scope.orderURLCliente = "";
        } else {
            $scope.orderURLServidor = "&order=" + $routeParams.order;
            $scope.orderURLCliente = $routeParams.order;
        }

       
            $scope.rpp = "5";
        

        if (!$routeParams.page) {
            $scope.page = 1;
        } else {
            if ($routeParams.page >= 1) {
                $scope.page = $routeParams.page;
            } else {
                $scope.page = 1;
            }
        }

        


   

//
//        $scope.resetOrder = function () {
//            $location.url(`noticias/plist/` + $scope.rpp + `/` + $scope.page);
//        }


//        $scope.ordena = function (order, align) {
//            if ($scope.orderURLServidor == "") {
//                $scope.orderURLServidor = "&order=" + order + "," + align;
//                $scope.orderURLCliente = order + "," + align;
//            } else {
//                $scope.orderURLServidor = $scope.orderURLServidor + "-" + order + "," + align;
//                $scope.orderURLCliente = $scope.orderURLCliente + "-" + order + "," + align;
//            }
//            $location.url(`noticias/plist/` + $scope.rpp + `/` + $scope.page + `/` + $scope.orderURLCliente);
//        }

        //getcount
        $http({
            method: 'GET',
            url: 'json?ob=noticias&op=getcount'
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataNum = response.data.message;
            $scope.totalPages = Math.ceil($scope.ajaxDataNum / $scope.rpp);
            if ($scope.page > $scope.totalPages) {
                $scope.page = $scope.totalPages;
                $scope.update();
            }
            pagination2();
        }, function (response) {
            $scope.ajaxDataNum = response.data.message || 'Request failed';
            $scope.status = response.status;
        });

        $http({
            method: 'GET',
            url: 'json?ob=noticias&op=getpage&rpp=' + $scope.rpp + '&page=' + $scope.page + $scope.orderURLServidor
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxData = response.data.message;
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxData = response.data.message || 'Request failed';
        });



        $scope.update = function () {
            $location.url(`noticias/plist/` + $scope.rpp + `/` + $scope.page + '/' + $scope.orderURLCliente);
        };


      

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