"use strict";

(function () {

    angular.module('traineeApp').config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/dashboard");

        $stateProvider.state("dashboard", {
            url: "/dashboard",
            templateUrl: "app/feature/dashboard/dashboard.html"
        }).state("trainee", {
            url: "/trainee",
            templateUrl: "app/feature/trainee/trainee.html"
        }).state("traineeInfo", {
            url: "/traineeInfo",
            templateUrl: "app/feature/traineeInfo/traineeInfo.html"
        })
    });
}());