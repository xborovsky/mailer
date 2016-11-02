(function() {
    
    'use strict';
    
    angular.module('EmailApp')
        .config(RoutesConfig);
    
    RoutesConfig.$inject = ['$stateProvider', '$urlRouterProvider'];
    function RoutesConfig($stateProvider, $urlRouterProvider) {
        
        $urlRouterProvider.otherwise('/received/list');
        
        $stateProvider
            .state('main', {
                templateUrl : 'partials/main.html',
                controller : 'MainController as mainCtrl',
                resolve : {
                    receivedUnreadEmailsCnt : ['EmailService', function(EmailService) {
                        return EmailService.countReceivedUnreadEmails();
                    }]
                }
            })
            .state('main.received_list', {
                url : '/received/list',
                templateUrl : 'partials/received-list.html',
                resolve : {
                    emails : ['EmailService', function(EmailService) {
                        return EmailService.getAllReceivedEmails();
                    }]
                },
                controller : 'ReceivedEmailListController as receivedListCtrl'
            })
            .state('main.sent_list', {
                url : '/sent/list',
                templateUrl : 'partials/sent-list.html',
                resolve : {
                    emails : ['EmailService', function(EmailService) {
                        return EmailService.getAllSentEmails();
                    }]
                },
                controller : 'SentEmailListController as sentListCtrl'
            })
            .state('main.new_email', {
                url : '/new',
                templateUrl : 'partials/new-email.html',
                controller : 'NewEmailController as newMailCtrl'
            })
            .state('main.trash_list', {
                url : '/trash/list',
                templateUrl : 'partials/trash-list.html',
                resolve : {
                    emails : ['EmailService', function(EmailService) {
                        return EmailService.getTrashEmails();
                    }]
                },
                controller : 'TrashListController as trashListCtrl'
            });
        
    }
    
})();