(function() {
    
    'use strict';
    
    angular.module('EmailApp')
        .controller('MainController', MainController);

    MainController.$inject = ['receivedUnreadEmailsCnt'];
    function MainController(receivedUnreadEmailsCnt) {
        var ctrl = this;
        
        ctrl.receivedUnreadEmailsCnt = receivedUnreadEmailsCnt.data;
    }
    
})();