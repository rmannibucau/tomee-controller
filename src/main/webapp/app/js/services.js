'use strict';

angular.module('appServices', [ 'ngResource' ])
    .factory('Phone', ['$resource', function($resource){
        return $resource(APP_CONTEXT + 'phones/:phoneId.json', {}, {
            query: {method:'GET', params:{phoneId:'phones'}, isArray:true}
        });
    }]);