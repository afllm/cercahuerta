'use strict'

moduleCommon.controller('homeController', ['$scope','$http', '$location', 'toolService', 'sessionService',
    function ($scope, $http, $location, toolService, sessionService) {
        $scope.logged = false;
        $scope.ruta = $location.path();
        $scope.isActive = toolService.isActive;
        $scope.orderURLServidor = "&order=id,desc";
//
//   if (sessionService.getUserName() !== "") {
//            $scope.loggeduser = sessionService.getUserName();
//            $scope.loggeduserid = sessionService.getId();
//            $scope.logged = true;
//            $scope.tipousuarioID = sessionService.getTypeUserID();
//        }

        /********************** \/ NOTICIAS \/ ****************************/
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
            
        }, function (response) {
            $scope.ajaxDataNum = response.data.message || 'Request failed';
            $scope.status = response.status;
        });

        $http({
            method: 'GET',
            url: 'json?ob=noticias&op=getpage&rpp=3&page=1'+ $scope.orderURLServidor
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxData = response.data.message;
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxData = response.data.message || 'Request failed';
        });
        /********************** /\ NOTICIAS /\ ****************************/

    }]);
