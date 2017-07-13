myApp.factory('srefAndIconFatory', function () {
    var jSref = {
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
        34: 'order/complete',
        64: 'activity/specify',

        71: 'welcome',
        45: 'welcome',
        47: 'welcome'
    };
    var aIcons = ['icon-authority', 'icon-vip', 'icon-goods', 'icon-store', 'icon-order', 'icon-activity', 'icon-statisitcs', 'icon-setting'];
    return {
        jSref: jSref,
        aIcons: aIcons
    };
});
myApp.service('httpService', ['$q', '$http', function ($q, $http) {
    var getRequest = function (url, jConfig) {
        var deferred = $q.defer();
        var promise = deferred.promise;
        $http.get(url, jConfig)
            .success(function (data, status) {
                deferred.resolve(data);
            })
            .error(function (data, status) {
                deferred.reject(data);
            });
        return promise;
    };
    var postRequest = function (url, jData) {
        var deferred = $q.defer();
        var promise = deferred.promise;
        $http.post(url, jData)
            .success(function (data, status) {
                deferred.resolve(data);
            })
            .error(function (data, status) {
                deferred.reject(data);
            });
        return promise;
    };
    this.getRequest = getRequest;
    this.postRequest = postRequest;
}]);

myApp.service('orderService', ['$q', '$http', function ($q, $http) {
    this.getOrdersByState = function (state, pageNo) {
        var deferred = $q.defer();
        var promise = deferred.promise;
        var jParams = {};
        if (state != 0) {
            //获取所有列表
            jParams.state = state;
        }
        jParams.pageNo = pageNo;
        $http.get('order/getOrderss.do', {params: jParams})
            .success(function (data) {
                deferred.resolve(data);
            })
            .error(function (error) {
                deferred.reject(error);
            });
        return promise;
    };
}]);

myApp.factory('isCheckFactory', function () {
   return {
       toggleCheck: function (id,arr) {
           var bFind = false;
           angular.forEach(arr, function (value, index) {
               if(!bFind && id==value){
                   this.splice(index, 1);
                   bFind = true;
               }
           }, arr);
           if(!bFind){
               arr.push(id);
           }
       }
   };
});