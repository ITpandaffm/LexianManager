myApp.controller('timeController',['$scope','$interval',function ($scope,$interval) {
    $scope.startClock = function () {
        $interval(function () {
            var oDate = new Date();
            $scope.curTime = (new Date()).toLocaleString();
        }, 1000, false)
    };
    $scope.curTime = (new Date()).toLocaleString();
}]);


//authority
myApp.controller('queryusersController', ['$scope', '$state', function ($scope, $state) {
    $scope.addUser = function () {
        alert('ja');
        $state.go('authority/querymenu', {});
    }
}]);