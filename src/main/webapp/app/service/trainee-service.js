"use strict";

(function () {


    function TraineeService (traineeDal) {

        var vm =this;

        this.getTrainees = function()
        {
            return traineeDal.getTrainees();
        };

        this.currTrainee;

        this.saveTrainee = function (trainee) {
            vm.currTrainee = trainee;
        }

        this.getTrainee = function () {
            return vm.currTrainee;
        }
    }

    angular.module("traineeApp").service("traineeService", ['traineeDal', TraineeService]);

}());