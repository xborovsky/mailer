(function() {
    'use strict';
    
    angular.module('EmailApp')
        .controller('TrashListController', TrashListController);
    
    TrashListController.$inject = ['emails', 'EmailService', '$state']; 
    function TrashListController(emails, EmailService, $state) {
        var ctrl = this;
        
        ctrl.emails = emails.data;
        
        ctrl.deleteEmail = function(id) {
            EmailService.deleteEmail(id).then(function() {
                console.log('Email deleted!');
                $state.go('main.trash_list', {}, {reload : true});
            });
        };
    }
    
})();