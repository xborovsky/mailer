(function() {
    
    'use strict';
    
    NewEmailController.$inject = ['$state', 'EmailService'];
    angular.module('EmailApp')
        .controller('NewEmailController', NewEmailController);

    function NewEmailController($state, EmailService) {
        var ctrl = this;
        ctrl.recipient = '';
        ctrl.subject = '';
        ctrl.message = '';
        ctrl.sending = false;
        
        ctrl.sendEmail = function() {
            console.log('sending email...');
            ctrl.sending = true;
            EmailService.sendEmail({
                recipient : ctrl.recipient,
                subject : ctrl.subject,
                message : ctrl.message
            }).then(function() {
                console.log('email sent successfully!');
                $state.go('received_list');
            }, function() {
                console.error('could not send email!');
            });
        };

        ctrl.cancelEmail = function() {
            console.log('cancel email...');
            ctrl.recipient = '';
            ctrl.subject = '';
            ctrl.message = '';
            $state.go('received_list');
        };
    }
    
})();