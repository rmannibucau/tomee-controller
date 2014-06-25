'use strict';

angular.module('appControllers', ['webAppBridge'])
    .controller('homeController', ['$scope', function ($scope, $http) {
        // no-op
    }])
    .controller('resourcesController', ['$scope', '$http', 'webappRoot', function ($scope, $http, webappRoot) {
        $http.get(webappRoot + '/api/resource/all.json')
            .success(function(data) {
                $scope.resources = data;
            });
    }])
    .controller('resourceDetailController', ['$scope', '$http', '$routeParams', 'webappRoot',
        function ($scope, $http, $routeParams, webappRoot) {
            $http.get(webappRoot + '/api/resource/' + $routeParams.resourceId + '.json')
                .success(function(data) {
                    $scope.resource = data;
                });
        }]);
