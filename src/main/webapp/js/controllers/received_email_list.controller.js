(function() {
    
    'use strict';
    
    angular.module('EmailApp')
        .controller('ReceivedEmailListController', ReceivedEmailListController)
        .filter('substringFilter', SubstringFilter);
    
    ReceivedEmailListController.$inject = ['emails'];
    function ReceivedEmailListController(emails) {
        var ctrl = this;
        
        ctrl.emails = emails.data;
    }
    
    function SubstringFilter() {
        return function(input) {;
            return input.substring(0, 50) + (input.length > 50 ? '...' : '');
        };
    }
    
})();