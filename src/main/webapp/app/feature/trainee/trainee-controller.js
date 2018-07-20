(function() {

    var TraineeController =  function($state, traineeService) {
        
    	var vm = this;

        vm.isHidden = false;

        vm.hideTable = function()
        {
            vm.isHidden = !vm.isHidden
        };

        function init() {
            traineeService.getTrainees().then(function (results) {
                vm.trainees = results;
                console.log(vm.trainees);
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        }

        vm.handleClick = function(trainee){
            traineeService.saveTrainee(trainee);
            $state.go('traineeInfo');
        }

        vm.selectTrainee = traineeService.getTrainee();

        init();

    };

    angular.module('traineeApp').controller('traineeController', ['$state','traineeService',TraineeController]);
}());