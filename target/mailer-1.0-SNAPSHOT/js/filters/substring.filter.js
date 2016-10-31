(function() {
    
    'use strict';
    
    angular.module('EmailApp')
        .filter('substringFilter', SubstringFilter);
    
    function SubstringFilter() {
        return function(input) {;
            return input.substring(0, 50) + (input.length > 50 ? '...' : '');
        };
    }
    
})();