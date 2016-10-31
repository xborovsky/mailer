(function() {
    
    'use strict';
    
    angular.module('EmailApp')
        .directive('confirmClick', ConfirmClick);

    function ConfirmClick() {
        return {
            priority : 1,
            terminal : true,
            link : function(scope, element, attr) {
                var msg = attr.confirmClick || "Are you sure?";
                var clickAction = attr.ngClick;
                element.bind('click', function() {
                    if (window.confirm(msg)) {
                        scope.$eval(clickAction);
                    }
                });
            }
        };
    }
    
})();