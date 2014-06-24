'use strict';

angular.module('appRouter', ['webAppBridge'])
    .config([ '$routeProvider', 'webappRoot', function ($routeProvider, webappRoot) {
        webappRoot = webappRoot || '';
        $routeProvider.
            when('/home', {
                templateUrl: webappRoot + '/app/template/home.html',
                controller: 'homeController'
            }).
            when('/resources', {
                templateUrl: webappRoot + 'app/template/resources.html',
                controller: 'resourcesController'
            }).
            when('/resources/:resourceId', {
                templateUrl: webappRoot + 'app/template/resource-detail.html',
                controller: 'resourceDetailController'
            }).
            otherwise({
                redirectTo: '/home'
            });
    }]);
