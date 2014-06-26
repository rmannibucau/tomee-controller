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
    }])
    .controller('newResourceController', ['$scope', '$http', 'webappRoot', function ($scope, $http, webappRoot) {
            $scope.resource = {"properties":[]};

            $scope.increaseForm = function () {
                $scope.resource.properties.push({'key':'', 'value':''});
            };

            $scope.create = function () {
                var resource = $scope.resource;
                if (resource.resourceId === undefined) {
                    // TODO: modal window instead
                    alert('no resourceId defined');
                    return;
                }

                $http.post(webappRoot + '/api/resource/create', angular.toJson(resource, true))
                    .success(function () {
                        alert('Success');
                    }).
                    error(function (data, status) {
                        alert('Error ' + status);
                    });
            };
        }]);
