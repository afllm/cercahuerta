var autenticacionAdministrador = function ($q, $location, $http, sessionService) {
    var deferred = $q.defer();
    $http({
        method: 'GET',
        url: 'json?ob=usuario&op=check'
    }).then(function (response) {
        if (response.data.message.obj_tipoUsuario.id === 1) {
            sessionService.setSesion(response.data.message);
            sessionService.setSessionActive(response.data.message);
            sessionService.setUserName(response.data.message.nombre + " " + response.data.message.ape1);
            sessionService.setId(response.data.message.id);
            //sessionService.setSesion(response.data.message);
            //sessionService.setAdmin();
            sessionService.setTypeUserID(response.data.message.obj_tipoUsuario.id);
            deferred.resolve();
        } else {
            $location.path('/home');
        }
    }, function (response) {
        sessionService.setSessionInactive();
        $location.path('/home');
    });
    return deferred.promise;
}

var autenticacionUsuario = function ($q, $location, $http, sessionService) {
    var deferred = $q.defer();
    $http({
        method: 'GET',
        url: 'json?ob=usuario&op=check'
    }).then(function (response) {
        if (response.data.message.obj_tipoUsuario.id === 2) {
            sessionService.setSesion(response.data.message);
            sessionService.setSessionActive();
            sessionService.setUserName(response.data.message.nombre + " " + response.data.message.ape1);
            sessionService.setId(response.data.message.id);
            //sessionService.setSesion(response.data.message);
            sessionService.setTypeUserID(response.data.message.obj_tipoUsuario.id);
            deferred.resolve();
        } else {
            $location.path('/home');
        }
    }, function (response) {
        $location.path('/home');
    });
    return deferred.promise;

}

var autenticacionCualquieraLogueado = function ($q, $location, $http, sessionService) {
    var deferred = $q.defer();
    $http({
        method: 'GET',
        url: 'json?ob=usuario&op=check'
    }).then(function (response) {
        if (response.data.message.obj_tipoUsuario.id === 2 || response.data.message.obj_tipoUsuario.id === 1) {
            sessionService.setSesion(response.data.message);
            sessionService.setSessionActive();
            sessionService.setUserName(response.data.message.nombre + " " + response.data.message.ape1);
            sessionService.setId(response.data.message.id);
            //sessionService.setSesion(response.data.message);
            sessionService.setTypeUserID(response.data.message.obj_tipoUsuario.id);
            deferred.resolve();
        } else {
            $location.path('/home');
        }
    }, function (response) {
        $location.path('/home');
    });
    return deferred.promise;

}

var autenticacionCualquiera = function ($q, $location, $http, sessionService) {
    var deferred = $q.defer();
    $http({
        method: 'GET',
        url: 'json?ob=usuario&op=check'
    }).then(function (response) {
        if (response.data.message.obj_tipoUsuario.id === 2 || response.data.message.obj_tipoUsuario.id === 1) {
            sessionService.setSesion(response.data.message);
            sessionService.setSessionActive();
            sessionService.setUserName(response.data.message.nombre + " " + response.data.message.ape1);
            sessionService.setId(response.data.message.id);
            //sessionService.setSesion(response.data.message);
            sessionService.setTypeUserID(response.data.message.obj_tipoUsuario.id);
            //deferred.resolve();
        }

//        else {
//            $location.path('/home');
//        }
    }, function (response) {
        //$location.path('/home');
    });
    deferred.resolve();
    return deferred.promise;

}


cercahuerta.config(['$routeProvider', function ($routeProvider) {

        //HOME
        $routeProvider.when('/', {templateUrl: 'js/app/common/home.html', controller: 'homeController', resolve: {auth: autenticacionCualquiera}});

        //USUARIO
        $routeProvider.when('/usuario/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/usuario/plist.html', controller: 'usuarioPlistController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/usuario/view/:id', {templateUrl: 'js/app/usuario/view.html', controller: 'usuarioViewController', resolve: {auth: autenticacionCualquieraLogueado}});
        $routeProvider.when('/usuario/new', {templateUrl: 'js/app/usuario/new.html', controller: 'usuarioNewController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/usuario/edit/:id', {templateUrl: 'js/app/usuario/edit.html', controller: 'usuarioEditController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/usuario/remove/:id', {templateUrl: 'js/app/usuario/remove.html', controller: 'usuarioRemoveController', resolve: {auth: autenticacionAdministrador}});

        $routeProvider.when('/usuario/editpass/:id', {templateUrl: 'js/app/usuario/editpass.html', controller: 'usuarioEditpassController', resolve: {auth: autenticacionCualquieraLogueado}});

        $routeProvider.when('/usuario/login', {templateUrl: 'js/app/usuario/login.html', controller: 'usuarioLoginController', resolve: {auth: autenticacionCualquiera}});
        $routeProvider.when('/usuario/logout', {templateUrl: 'js/app/usuario/logout.html', controller: 'usuarioLogoutController', resolve: {auth: autenticacionCualquieraLogueado}});
        $routeProvider.when('/usuario/registro', {templateUrl: 'js/app/usuario/registro.html', controller: 'usuarioRegistroController', resolve: {auth: autenticacionCualquiera}});

        //TIPOUSUARIO
        $routeProvider.when('/tipousuario/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/tipousuario/plist.html', controller: 'tipousuarioPlistController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/tipousuario/view/:id', {templateUrl: 'js/app/tipousuario/view.html', controller: 'tipousuarioViewController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/tipousuario/edit/:id?', {templateUrl: 'js/app/tipousuario/edit.html', controller: 'tipousuarioEditController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/tipousuario/remove/:id', {templateUrl: 'js/app/tipousuario/remove.html', controller: 'tipousuarioRemoveController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/tipousuario/new', {templateUrl: 'js/app/tipousuario/new.html', controller: 'tipousuarioNewController', resolve: {auth: autenticacionAdministrador}});

        //TIPOPRODUCTO
        $routeProvider.when('/tipoproducto/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/tipoproducto/plist.html', controller: 'tipoproductoPlistController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/tipoproducto/remove/:id', {templateUrl: 'js/app/tipoproducto/remove.html', controller: 'tipoproductoRemoveController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/tipoproducto/view/:id?', {templateUrl: 'js/app/tipoproducto/view.html', controller: 'tipoproductoViewController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/tipoproducto/new', {templateUrl: 'js/app/tipoproducto/new.html', controller: 'tipoproductoNewController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/tipoproducto/edit/:id', {templateUrl: 'js/app/tipoproducto/edit.html', controller: 'tipoproductoEditController', resolve: {auth: autenticacionAdministrador}});

        //PRODUCTO
        $routeProvider.when('/producto/new', {templateUrl: 'js/app/producto/new.html', controller: 'productoNewController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/producto/edit/:id', {templateUrl: 'js/app/producto/edit.html', controller: 'productoEditController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/producto/remove/:id', {templateUrl: 'js/app/producto/remove.html', controller: 'productoRemoveController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/producto/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/producto/plist.html', controller: 'productoPlistController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/producto/view/:id?', {templateUrl: 'js/app/producto/view.html', controller: 'productoViewController', resolve: {auth: autenticacionCualquiera}});

        //LINEA
        $routeProvider.when('/linea/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/linea/plist.html', controller: 'lineaPlistController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/linea/view/:id', {templateUrl: 'js/app/linea/view.html', controller: 'lineaViewController', resolve: {auth: autenticacionCualquieraLogueado}});
        $routeProvider.when('/linea/remove/:id', {templateUrl: 'js/app/linea/remove.html', controller: 'lineaRemoveController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/linea/edit/:id', {templateUrl: 'js/app/linea/edit.html', controller: 'lineaEditController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/linea/new', {templateUrl: 'js/app/linea/new.html', controller: 'lineaNewController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/linea/plistxfactura/:rpp?/:page?/:id?/:order?', {templateUrl: 'js/app/linea/plistxfactura.html', controller: 'lineaplistxfacturaController', resolve: {auth: autenticacionCualquiera}});
        $routeProvider.when('/linea/newxfactura/:id', {templateUrl: 'js/app/linea/newxfactura.html', controller: 'lineanewxfacturaController', resolve: {auth: autenticacionAdministrador}});

        //FACTURA
        $routeProvider.when('/factura/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/factura/plist.html', controller: 'facturaPlistController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/factura/plistxusuario/:rpp?/:page?/:id?/:order?', {templateUrl: 'js/app/factura/plistxusuario.html', controller: 'facturaplistxusuarioController', resolve: {auth: autenticacionCualquieraLogueado}});
        $routeProvider.when('/factura/remove/:id', {templateUrl: 'js/app/factura/remove.html', controller: 'facturaRemoveController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/factura/view/:id?', {templateUrl: 'js/app/factura/view.html', controller: 'facturaViewController', resolve: {auth: autenticacionCualquieraLogueado}});
        $routeProvider.when('/factura/new', {templateUrl: 'js/app/factura/new.html', controller: 'facturaNewController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/factura/edit/:id', {templateUrl: 'js/app/factura/edit.html', controller: 'facturaEditController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/factura/newxusuario/:id', {templateUrl: 'js/app/factura/newxusuario.html', controller: 'facturanewxusuarioController', resolve: {auth: autenticacionAdministrador}});

        //CARRITO
        $routeProvider.when('/carrito/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/carrito/plist.html', controller: 'carritoPlistController', resolve: {auth: autenticacionUsuario}});
        $routeProvider.when('/carrito/carrito/:rpp?/:page?/:order?', {templateUrl: 'js/app/carrito/carrito.html', controller: 'carritoCarritoController', resolve: {auth: autenticacionUsuario}});
        $routeProvider.when('/carrito/facturacarrito/:id?', {templateUrl: 'js/app/carrito/facturacarrito.html', controller: 'facturaCarritoController', resolve: {auth: autenticacionUsuario}});

        //NOTICIAS
        $routeProvider.when('/noticias/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/noticias/plist.html', controller: 'noticiasPlistController', resolve: {auth: autenticacionCualquiera}});
        $routeProvider.when('/noticias/view/:id?/:page?', {templateUrl: 'js/app/noticias/view.html', controller: 'noticiasViewController', resolve: {auth: autenticacionCualquiera}});
        $routeProvider.when('/noticias/new', {templateUrl: 'js/app/noticias/new.html', controller: 'noticiasNewController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/noticias/edit/:id', {templateUrl: 'js/app/noticias/edit.html', controller: 'noticiasEditController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.when('/noticias/remove/:id/:page?', {templateUrl: 'js/app/noticias/remove.html', controller: 'noticiasRemoveController', resolve: {auth: autenticacionAdministrador}});
        $routeProvider.otherwise({redirectTo: '/', resolve: {auth: autenticacionCualquiera}});
    }]);
