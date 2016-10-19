(function() {
    
    'use strict';
    
    angular.module('EmailApp')
        .controller('ReceivedEmailListController', ReceivedEmailListController);
    
    ReceivedEmailListController.$inject = ['emails', 'receivedUnreadEmailsCnt'];
    function ReceivedEmailListController(emails, receivedUnreadEmailsCnt) {
        var ctrl = this;
        
        console.log('emails: ', emails);
        console.log('receivedUnreadEmailsCnt: ', receivedUnreadEmailsCnt);
        
        ctrl.emails = emails;
    }
    
})();