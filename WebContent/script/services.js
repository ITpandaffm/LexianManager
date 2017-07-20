myApp.factory('srefAndIconFatory', function () {
    var jSref = {
        17: 'authority/queryauthority',
        18: 'authority/querymenu',
        19: 'authority/queryusers',
        20: 'authority/queryrole',
        21: 'vip/queryvip',
        22: 'goods/category',
        23: 'goods/info',
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
    var postRequest = function (url, jData, config) {
        var deferred = $q.defer();
        var promise = deferred.promise;
        $http.post(url, jData, config)
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

myApp.factory('isCheckFactory', function ($filter) {
    return {
        toggleCheck: function (id, arr) {
            if (($filter('filter')(arr, id)).length) {
                angular.forEach(arr, function (value, index) {
                    if (id == value) {
                        this.splice(index, 1);
                    }
                }, arr);
            } else {
                arr.push(id);
            }
        }
    };
});

myApp.factory('getFilterIdFactory', function ($filter) {
    return {
        getFilterArrById: function (aIds, aAllMenus) {
            //aIds是过滤了父项的子项id数组，下面这个aObjs是通过aIds去获得了所有对应的子项对象。
            var aChildren = $filter('filter')(aAllMenus, function (value) {
                return ($filter('filter')(aIds, value.id)).length;
            });
            //通过aObjs获得所有关联的父项数组，而且去重噢。
            var aParents = $filter('filter')(aAllMenus, function (value) {
                return ($filter('filter')(aChildren, {'parentId': value.id}).length)
            });
            //获取所有父项的id
            var aParentsId = [];
            angular.forEach(aParents, function (value) {
                aParentsId.push(value.id);
            });
            return aIds.concat(aParentsId);
        }
    };
});

myApp.factory('getCategoryArrByIdFactory', function ($filter) {
    return {
        getCategoryArrById: function (parentId, arrAll) {
            return $filter('filter')(arrAll, {'parentId': parentId});
        }
    }
});

myApp.service('orderSearchByDateService', function (httpService) {
    this.orderSearchByDate = function (fromDate, toDate, state, arr) {
        var jParams = {};
        if (0 == state) {
            jParams = {
                pageNo: 1,
                start: fromDate,
                end: toDate
            }
        } else {
            jParams = {
                state: state,
                pageNo: 1,
                start: fromDate,
                end: toDate
            }
        }
        httpService.getRequest('order/getOrderssByDate.do', {params: jParams}).then(function (data) {
            if (1 == data.code) {
                arr.length = 0;
                angular.forEach(data.data.data, function (value) {
                    arr.push(value);
                });
            }
        }, function (error) {
            console.log('order/getOrderssByDate.do error: ' + error);
        });
    }
});

//文件上传
myApp.service('fileUploadService', ['$http', '$q', function ($http, $q) {
    this.uploadFileToUrl = function (file, uploadUrl) {
        var fd = new FormData();
        fd.append('file', file);

        var deferred = $q.defer();
        var promise = deferred.promise;

        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
            .success(function (data) {
                deferred.resolve(data);
            })
            .error(function (error) {
                deferred.reject(error);
            });
        return promise;
    };
}]);