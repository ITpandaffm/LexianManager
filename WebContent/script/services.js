/**
 * Created by 冯富铭 on 2017/7/6.
 */
myApp.factory('srefAndIconFatory', function() {
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

        71: 'statistics/goodsStatistics',
        45: 'welcome',
        47: 'welcome'
    };
    var aIcons = ['icon-authority', 'icon-vip', 'icon-goods', 'icon-store', 'icon-order', 'icon-activity', 'icon-statisitcs', 'icon-setting'];
    return {
        jSref: jSref,
        aIcons: aIcons
    };
});
myApp.service('httpService', ['$q', '$http', function($q, $http) {
    var getRequest = function(url, jConfig) {
        var deferred = $q.defer();
        var promise = deferred.promise;
        $http.get(url, jConfig)
            .success(function(data, status) {
                deferred.resolve(data);
            })
            .error(function(data, status) {
                deferred.reject(data);
            });
        return promise;
    };
    var postRequest = function(url, jData, config) {
        var deferred = $q.defer();
        var promise = deferred.promise;
        $http.post(url, jData, config)
            .success(function(data, status) {
                deferred.resolve(data);
            })
            .error(function(data, status) {
                deferred.reject(data);
            });
        return promise;
    };
    this.getRequest = getRequest;
    this.postRequest = postRequest;
}]);

myApp.service('orderService', ['$q', '$http', function($q, $http) {
    this.getOrdersByState = function(state, pageNo, pageSize) {
        var deferred = $q.defer();
        var promise = deferred.promise;
        var jParams = {
            pageNo: pageNo,
            pageSize: pageSize
        };
        if (state != 0) {
            //获取所有列表
            jParams.state = state;
        }
        jParams.pageNo = pageNo;
        $http.get('order/getOrderss.do', { params: jParams })
            .success(function(data) {
                deferred.resolve(data);
            })
            .error(function(error) {
                deferred.reject(error);
            });
        return promise;
    };
}]);

myApp.factory('isCheckFactory', function($filter) {
    return {
        toggleCheck: function(id, arr) {
            if (($filter('filter')(arr, id)).length) {
                angular.forEach(arr, function(value, index) {
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

myApp.factory('getFilterIdFactory', function($filter) {
    return {
        getFilterArrById: function(aIds, aAllMenus) {
            //aIds是过滤了父项的子项id数组，下面这个aObjs是通过aIds去获得了所有对应的子项对象。
            var aChildren = $filter('filter')(aAllMenus, function(value) {
                return ($filter('filter')(aIds, value.id)).length;
            });
            //通过aObjs获得所有关联的父项数组，而且去重噢。
            var aParents = $filter('filter')(aAllMenus, function(value) {
                return ($filter('filter')(aChildren, { 'parentId': value.id }).length)
            });
            //获取所有父项的id
            var aParentsId = [];
            angular.forEach(aParents, function(value) {
                aParentsId.push(value.id);
            });
            return aIds.concat(aParentsId);
        }
    };
});

myApp.factory('getCategoryArrByIdFactory', function($filter) {
    return {
        getCategoryArrById: function(parentId, arrAll) {
            return $filter('filter')(arrAll, { 'parentId': parentId });
        }
    }
});

myApp.service('orderSearchByDateService', function(httpService) {
    this.orderSearchByDate = function(fromDate, toDate, state, arr) {
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
        httpService.getRequest('order/getOrderssByDate.do', { params: jParams }).then(function(data) {
            if (1 == data.code) {
                arr.length = 0;
                angular.forEach(data.data.data, function(value) {
                    arr.push(value);
                });
            }
        }, function(error) {
            console.log('order/getOrderssByDate.do error: ' + error);
        });
    }
});

//文件上传
myApp.service('fileUploadService', ['$http', '$q', function($http, $q) {
    this.uploadFileToUrl = function(file, uploadUrl) {
        var fd = new FormData();
        fd.append('file', file);

        var deferred = $q.defer();
        var promise = deferred.promise;

        $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: { 'Content-Type': undefined }
            })
            .success(function(data) {
                deferred.resolve(data);
            })
            .error(function(error) {
                deferred.reject(error);
            });
        return promise;
    };
}]);

myApp.service('dateTimePickerService', function() {
    this.init = function() {
        
        angular.element('.form_time').datetimepicker({
            language: 'ch',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 1,
            minView: 0,
            maxView: 1,
            forceParse: 0
        });
    };
});

myApp.service('diagramService', function () {
   this.getPieOption = function (data) {
       let option = {
           backgroundColor: '#2c343c',
           visualMap: {
               show: false,
               min: 80,
               max: 600,
               inRange: {
                   colorLightness: [0, 1]
               }
           },
           tooltip: {},
           series : [
               {
                   name: '访问来源',
                   type: 'pie',
                   radius: '55%',
                   data:  data,
                   roseType: 'angle',
                   label: {
                       normal: {
                           textStyle: {
                               color: 'rgba(255, 255, 255, 0.3)'
                           }
                       }
                   },
                   labelLine: {
                       normal: {
                           lineStyle: {
                               color: 'rgba(255, 255, 255, 0.3)'
                           }
                       }
                   },
                   itemStyle: {
                       normal: {
                           color: '#c23531',
                           shadowBlur: 200,
                           shadowColor: 'rgba(0, 0, 0, 0.5)'
                       }
                   }
               }
           ]
       };
       return option;
   };
   this.getLineOption = function (dataName,dataCount) {
       let option = {
           backgroundColor: '#fff',
           tooltip: {trigger: 'axis'},
           legend: {
               data:['count']
           },
           toolbox: {
               show : true,
               feature : {
                   mark : {show: true},
                   dataView : {show: true, readOnly: false},
                   magicType : {show: true, type: ['line', 'bar']},
                   restore : {show: true},
                   saveAsImage : {show: true}
               }
           },
           xAxis: {
               axisLabel:{
                   interval:0,
                   rotate:15,//倾斜度 -90 至 90 默认为0
                   margin:15,
                   marginLeft: 100,
                   textStyle:{
                       fontWeight:"bolder",
                       color:"#000000"
                   }
               },
               data: dataName
           },
           yAxis: {
               splitArea : {show : true}
           },
           series: [{
               name: 'count',
               type: 'bar',
               data: dataCount,
               itemStyle: {
                   normal: {
                       label: {
                           show: true,//是否展示
                           textStyle: {
                               fontWeight:'bolder',
                               fontSize : '12',
                               fontFamily : '微软雅黑',
                           }
                       }
                   }
               },
               markLine : {
                   data : [
                       {type : 'average', name: '平均值'}
                   ]
               }
           }]
       };
       return option;
   }
});

myApp.service('mapService', function(){
	this.mapInit = function(oLocation){
		console.log(oLocation);
		if(0 == oLocation.longitude || 0 == oLocation.latitude){
			alert("请检查该门店的经纬度！");
		} else {
			// 百度地图API功能
			var map = new BMap.Map("storemap");
			var point = new BMap.Point(oLocation.longitude,oLocation.latitude);
			map.centerAndZoom(point,16);
			map.enableScrollWheelZoom(true);
			var marker = new BMap.Marker(point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			map.panTo(point); 
			var opts = {
					  width : 200,     // 信息窗口宽度
					  height: 80,     // 信息窗口高度
					  title : oLocation.storeName, // 信息窗口标题
					}
					var infoWindow = new BMap.InfoWindow("地址："+oLocation.storeAddress, opts);  // 创建信息窗口对象 
			map.openInfoWindow(infoWindow,point); //开启信息窗口
			marker.addEventListener("click", function(){          
						map.openInfoWindow(infoWindow,point); //开启信息窗口
					});
		}

	}
});