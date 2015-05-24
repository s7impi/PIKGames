'use strict';

/* App Module */

var boardgameApp = angular.module('boardgameApp', [
    'ngRoute',
    'boardgameAnimations',
    'boardgameControllers',
    'boardgameServices'
]);

boardgameApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/boardgames', {
                templateUrl: 'resources/partials/boardgame-list.html',
                controller: 'BoardgameListCtrl'
            }).
            when('/boardgames/:boardgameId', {
                templateUrl: 'resources/partials/boardgame-detail.html',
                controller: 'BoardgameDetailCtrl'
            }).
            otherwise({
                redirectTo: '/boardgames'
            });
    }]);
