myApp.controller('timeController',['$scope','$interval',function ($scope,$interval) {
    $scope.startClock = function () {
        $interval(function () {
            var oDate = new Date();
            $scope.curTime = (new Date()).toLocaleString();
        }, 1000, false)
    };
    $scope.curTime = (new Date()).toLocaleString();
}]);