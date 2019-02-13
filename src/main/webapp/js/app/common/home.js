'use strict'

moduleCommon.controller('homeController', ['$scope', '$http', '$location', 'toolService', 'sessionService',
    function ($scope, $http, $location, toolService, sessionService) {
        $scope.conectado = false;
        $scope.ruta = $location.path();
        $scope.isActive = toolService.isActive;
        $scope.orderURLServidorNews = "&order=id,desc";
        $scope.orderURLServidorGoods = "&order=existencias,desc";

   if (sessionService.getUserName() !== "") {
            $scope.conectado = true;
        }

        /********************** \/ NOTICIAS \/ ****************************/
//getcount
        $http({
            method: 'GET',
            url: 'json?ob=noticias&op=getcount'
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataNewsNum = response.data.message;
            $scope.totalPages = Math.ceil($scope.ajaxDataNewsNum / $scope.rpp);
            if ($scope.page > $scope.totalPages) {
                $scope.page = $scope.totalPages;
                $scope.update();
            }

        }, function (response) {
            $scope.ajaxDataNewsNum = response.data.message || 'Request failed';
            $scope.status = response.status;
        });

        $http({
            method: 'GET',
            url: 'json?ob=noticias&op=getpage&rpp=2&page=1' + $scope.orderURLServidorNews
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataNews = response.data.message;
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxDataNews = response.data.message || 'Request failed';
        });
        /********************** /\ NOTICIAS /\ ****************************/


        /********************** \/ PRODUCTOS \/ ****************************/

        //getcount
        $http({
            method: 'GET',
            url: 'json?ob=producto&op=getcount'
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataGoodsNum = response.data.message;
            $scope.totalPages = Math.ceil($scope.ajaxDataGoodsNum / $scope.rpp);
            if ($scope.page > $scope.totalPages) {
                $scope.page = $scope.totalPages;
                $scope.update();
            }
            
        }, function (response) {
            $scope.ajaxDataGoodsNum = response.data.message || 'Request failed';
            $scope.status = response.status;
        });

        $http({
            method: 'GET',
            url: 'json?ob=producto&op=getpage&rpp=3&page=1' + $scope.orderURLServidorGoods
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataGoods = response.data.message;
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxDataGoods = response.data.message || 'Request failed';
        });
        /********************** /\ PRODUCTOS /\ ****************************/
    }]);
