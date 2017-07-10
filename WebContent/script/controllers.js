myApp.controller('timeController',['$scope','$interval',function ($scope,$interval) {
    $scope.startClock = function () {
        $interval(function () {
            var oDate = new Date();
            $scope.curTime = (new Date()).toLocaleString();
        }, 1000, false)
    };
    $scope.curTime = (new Date()).toLocaleString();
}]);

myApp.controller('userCenterListController', ['$scope','$http',  function ($scope, $http) {
    $scope.getUserCenterList = function () {
        $http.get('manager/getMenus.do', {})
            .success(function (data, status) {
                console.log('success:'+data+', status:'+status);
                if(data.code == 1){
                    $scope.aCenterListData = data.data;
                }
            })
            .error(function (data, status) {
                console.log('error:'+data+', status:'+status);
            });
    }
    $scope.jSref  = {
    	17: 'authority/queryauthority',
        18: 'authority/querymenu',
        19: 'authority/queryusers',
        20: 'authority/queryrole',
        21: 'vip/queryvip',
        22: 'goods/category',
        23: 'goods/vipinfo',
        27: 'store/info',
        28: 'store/goods',
        30: 'order/list',
        31: 'order/unpaid',
        32: 'order/haspaid',
        33: 'order/hasdeliver',
        34:	'order/complete',
        64: 'activity/specify',

        71: 'welcome',
        45: 'welcome',
        47: 'welcome'
    };
    $scope.aIcons = ['icon-authority', 'icon-vip' ,'icon-goods', 'icon-store', 'icon-order', 'icon-activity', 'icon-statisitcs', 'icon-setting'];
    $scope.subItemClick = function ($event){
          console.log($event.target);
          angular.element('.panel-body a').removeClass('active');
          $($event.target).addClass('active');
    };
}]);

//authority
myApp.controller('queryusersController', ['$scope', '$state', function ($scope, $state) {
    $scope.addUser = function () {
        alert('ja');
        $state.go('authority/querymenu', {});
    }
}]);