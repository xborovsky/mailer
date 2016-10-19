(function() {
    
    'use strict';
    
    angular.module('EmailApp')
        .config(RoutesConfig);
    
    RoutesConfig.$inject = ['$stateProvider', '$urlRouterProvider'];
    function RoutesConfig($stateProvider, $urlRouterProvider) {
        
        $urlRouterProvider.otherwise('/received/list');
        
        $stateProvider
            .state('received_list', {
                url : '/received/list',
                templateUrl : 'partials/received-list.html',
                resolve : {
                    emails : ['EmailService', function(EmailService) {
                            console.log('XXX');
                        return EmailService.getAllReceivedEmails();
                    }],
                    receivedUnreadEmailsCnt : ['EmailService', function(EmailService) {
                        return EmailService.countReceivedUnreadEmails();
                    }]
                },
                controller : 'ReceivedEmailListController as receivedListCtrl'
            })
            .state('sent_list', {
                url : '/sent/list',
                template : '',
                resolve : {
                    emails : ['EmailService', function(EmailService) {
                        return EmailService.getAllSentEmails();
                    }]
                },
                controller : 'SentEmailListController as sentListCtrl'
            })
            .state('new_email', {
                url : '/new',
                templateUrl : 'partials/new-email.html',
                controller : 'NewEmailController as newMailCtrl'
            });
        
    }
    
})();