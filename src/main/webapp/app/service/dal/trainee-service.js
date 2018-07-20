"use strict";

(function () {

    function TraineeDal (dal) {

        this.getTrainees = function () {
            return dal.http.GET("rest/trainee/json");
        };

/*        this.saveAccount = function (accountToSave) {
            return dal.http.POST("rest/trainee/json", accountToSave);
        };

        this.updateAccount = function (accountToUpdate) {
            return dal.http.PUT("rest/trainee/json/", accountToUpdate);
        };

        this.deleteAccount = function (accountToDelete) {
            return dal.http.DELETE("/rest/trainee/json/", accountToDelete);
        };*/
    }

    angular.module("traineeApp").service("traineeDal", ["dal", TraineeDal]);
}());