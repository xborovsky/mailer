(function() {
    
    'use strict';
    
    angular.module('EmailApp')
        .controller('ReceivedEmailListController', ReceivedEmailListController)

    ReceivedEmailListController.$inject = ['emails', 'EmailService', '$state'];
    function ReceivedEmailListController(emails, EmailService, $state) {
        var ctrl = this;
        
        ctrl.emails = emails.data;
        
        ctrl.trashEmail = function(id) {
            EmailService.trashEmail(id).then(function() {
                $state.go('main.received_list', {}, {reload : true});
            });
        };
    }
    
})();