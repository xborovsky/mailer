(function() {
    
    'use strict';
    
    angular.module('EmailApp')
        .service('EmailService', EmailService)
        .constant('ENDPOINT_URL', 'http://localhost:8080/mailer/rest');

    EmailService.$inject = ['$http', 'ENDPOINT_URL'];
    function EmailService($http, ENDPOINT_URL) {
        var service = this;
        
        service.getAllReceivedEmails = function() {
            console.log('XXX');
            return $http.get(ENDPOINT_URL + '/email/received/list')
                .success(function(receivedEmails) {
                    return receivedEmails.data;
                }); // TODO error handling
        };
        
        service.getAllSentEmails = function() {
            return $http.get(ENDPOINT_URL + '/email/sent/list')
                .success(function(sentEmails) {
                    return sentEmails.data;
                }); // TODO error handling
        };
        
        service.countReceivedUnreadEmails = function() {
            return $http.get(ENDPOINT_URL + '/email/received/count_unread');
        };
        
        service.makeEmailRead = function() {
            // TODO
        };
        
        service.sendEmail = function(emailData) {
            return $http.post(ENDPOINT_URL + '/email/send', JSON.stringify(emailData));
        };
    }
    
})();