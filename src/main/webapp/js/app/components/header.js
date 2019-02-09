moduloDirectivas.component('headerComponent', {
    templateUrl: 'js/app/components/header.html',
    bindings: {
    },
    controllerAs: 'c',
    controller: js
});


function js(toolService, sessionService, $http, $route, $window) {
    var self = this;


    self.loggeduser = sessionService.getUserName();
    self.loggeduserid = sessionService.getId();
    self.logged = sessionService.isSessionActive();
    self.tipousuarioID = sessionService.getTypeUserID();
    self.isActive = toolService.isActive;
    self.limpiar = sessionService.isSessionActive();

    self.endOfDocumentTop = 134;
    self.size = 0;

     $window.onscroll = function () {
         setTimeout(growShrinkLogo(), 2000);
    };

    // var Logo = document.getElementById("Logo");
    var endOfDocumentTop = 134;
    var size = 0;

    function growShrinkLogo() {
        var scroll = $window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;

        if (size === 0 && scroll > endOfDocumentTop) {
            document.getElementById("Logo").className = 'smallLogo';
            size = 1;
        } else if (size === 1 && scroll <= endOfDocumentTop) {
            document.getElementById("Logo").className = 'largeLogo';
            size = 0;
        }
    }
}
