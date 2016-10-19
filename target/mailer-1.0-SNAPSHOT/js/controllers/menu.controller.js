(function() {
    
    'use strict';
    
    angular.module('EmailApp')
        .controller('MenuController', MenuController);

    MenuController.$inject = ['EmailService'];
    function MenuController(EmailService) {
        var ctrl = this;
        
        EmailService.countReceivedUnreadEmails().then(
            function(result) {
                ctrl.receivedUnreadEmailsCnt = result.data;
            }, function(error) {
                console.log('Could not get received unread emails count!');
            }
        );
    }
    
})();