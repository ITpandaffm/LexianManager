myApp.factory('srefAndIconFatory', function () {
    var jSref  = {
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
    var aIcons = ['icon-authority', 'icon-vip' ,'icon-goods', 'icon-store', 'icon-order', 'icon-activity', 'icon-statisitcs', 'icon-setting'];
    return {
        jSref: jSref,
        aIcons: aIcons
    };
});
myApp.factory('httpService', ['$q', '$http', function ($q, $http) {
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
    return {
        getRequest: getRequest,
        postRequest: postRequest
    };
}]);
