myApp.controller('userController', ['$scope', '$interval','httpService', 'srefAndIconFatory',
                            function ($scope, $interval, httpService, srefAndIconFatory) {

    $scope.startClock = function () {
        $scope.curTime = new Date();
        $interval(function () {
            $scope.curTime = new Date();
        }, 1000, false);
    };

    $scope.getUserCenterList = function () {
        httpService.getRequest('manager/getUserWithMenus.do', {})
            .then(function (data) {
                $scope.oData = {
                    username: data.data.name,
                    aCenterListData: data.data.menus,
                    jSref: srefAndIconFatory.jSref,
                    aIcons: srefAndIconFatory.aIcons
                };
            }, function (error) {
                console.log('getUserWithMenus error:'+error);
            });
    };

    $scope.subItemClick = function ($event){
        angular.element('.panel-body a').removeClass('active');
        $($event.target).addClass('active');
    };

    $scope.signOut = function () {
        if(confirm('确定吗？'+$scope.oData.username)){
            httpService.getRequest('manager/signOut.do', {})
                .then(function (data) {
                    alert(data.data + '，请重新登录！');
                    window.location.hash = '';
                    window.location.pathname = '/LexianManager';
                }, function (error) {
                    console.log('signOut error:'+error);
                });
        }
    }

}]);

//修改密码
myApp.controller('updatePwdController', ['$scope', '$state', 'httpService',  function ($scope, $state, httpService) {
    $scope.updatePwd = function () {
        console.log($scope.oldPwd+': '+$scope.newPwd);
        httpService.getRequest('manager/updateManagerPassword.do', {params: {password: $scope.oldPwd, newPass: $scope.newPwd}})
            .then(function (data) {
                if (data.code == 1){
                    alert(data.data);
                    $state.go('welcome');
                } else {
                    alert('旧密码错误！');
                }
            }, function (error) {
                console.log('updateManagerPassword error:'+error);
            });
    }
}]);

//authority
//查询权限
myApp.controller('queryPrivilegesController', ['$scope', 'httpService', function ($scope, httpService) {
    $scope.getPrivileges = function () {
        httpService.getRequest('manager/getPrivileges.do', {})
            .then(function (data) {
                $scope.aPrivileges = data.data.data;
            }, function (error) {
                console.log('getPrivileges error:'+error);
            });
    }
}]);

//查询菜单
myApp.controller('queryMenuController', ['$scope', 'httpService', 'srefAndIconFatory',
    function ($scope, httpService, srefAndIconFatory) {
    $scope.getMenus = function () {
        httpService.getRequest('manager/getUserWithMenus.do', {})
            .then(function (data) {
                $scope.oData = {
                    aCenterListData: data.data.menus,
                    jSref: srefAndIconFatory.jSref,
                    aIcons: srefAndIconFatory.aIcons
                };
            }, function (error) {
                console.log('getUserWithMenus error:'+error);
            });
    }
}]);

//查询后台用户
myApp.controller('queryUsersController', ['$scope', 'httpService', function ($scope, httpService) {
    $scope.getManagers = function () {
        httpService.getRequest('handleManager/getManagers.do', {})
            .then(function (data) {
                $scope.aManagers = data.data.data;
            }, function (error) {
                console.log('getManagers error:'+error);
            })
    };
}]);

//查询角色
myApp.controller('queryRoleController', ['$scope', 'httpService', function ($scope, httpService) {
    $scope.getRoles = function () {
        httpService.getRequest('role/getRoles.do', {})
            .then(function (data) {
                $scope.aRoles = data.data;
            }, function (error) {
                console.log('getRoles error:' + error);
            });

    }
}]);

//vip
myApp.controller('queryVipController', ['$scope', 'httpService', function ($scope, httpService) {
    $scope.getVips = function () {
        httpService.getRequest('user/getUsers.do', {})
            .then(function (data) {
                $scope.aVips = data.data.data;
            }, function (error) {
                console.log('getUsers error:' + error);
            });

    }
}]);
//store
//门店信息
myApp.controller('storeInfoController', ['$scope', 'httpService', function ($scope, httpService) {
    $scope.getStoreInfo = function () {
        httpService.getRequest('store/getAllStore.do', {params: {pageNo: 1}})
            .then(function (data) {
                $scope.aStoreInfo = data.data.data;
            }, function (error) {
                console.log('getCommodityStoreByStoreNo error: '+error);
            })
    }
}]);

//门店商品
myApp.controller('storeGoodsController',  ['$scope', 'httpService', function ($scope, httpService) {
    $scope.getStoreGoods = function () {
        httpService.getRequest('store/getAllStore.do', {params: {pageNo: 1}})
            .then(function (data) {
                $scope.aStoreGoods = data.data.data;
            }, function (error) {
                console.log('getGoods error: '+error);
            })
    }
}]);

//order
//订单列表
myApp.controller('orderListController', ['$scope', 'orderService', function ($scope, orderService) {
    $scope.getOrderLists = function () {
        orderService.getOrdersByState(0,1)
          .then(function (data) {
              $scope.aOrderLists = data.data.data;
          }, function (error) {
              console.log('getOrderss error'+ error);
          });
    };
}]);

//未付款订单
myApp.controller('orderUnpaidController', ['$scope', 'orderService', function ($scope, orderService) {
    $scope.getUnpaidLists = function () {
        orderService.getOrdersByState(1, 1)
            .then(function (data) {
                $scope.aUnpaidLists = data.data.data;
            }, function (error) {
                console.log('orderUnpaid error'+ error);
            });
    };
}]);

//已付款订单
myApp.controller('orderPaidController', ['$scope', 'orderService', function ($scope, orderService) {
    $scope.getPaidLists = function () {
        orderService.getOrdersByState(2, 1)
            .then(function (data) {
                $scope.aPaidLists = data.data.data;
            }, function (error) {
                console.log('orderUnpaid error'+ error);
            });
    };
}]);

//已发货订单
myApp.controller('orderDeliverController', ['$scope', 'orderService', function ($scope, orderService) {
    $scope.getDeliverLists = function () {
        orderService.getOrdersByState(3, 1)
            .then(function (data) {
                $scope.aDeliverLists = data.data.data;
            }, function (error) {
                console.log('orderUnpaid error'+ error);
            });
    };
}]);

//已完成订单
myApp.controller('orderCompleteController', ['$scope', 'orderService', function ($scope, orderService) {
    $scope.getCompleteLists = function () {
        orderService.getOrdersByState(4, 1)
            .then(function (data) {
                $scope.aCompleteLists = data.data.data;
            }, function (error) {
                console.log('orderUnpaid error'+ error);
            });
    };
}]);

//activity
//特定活动
myApp.controller('activitySpecifyController', ['$scope', 'httpService', function ($scope, httpService) {
    $scope.getSpecials = function () {
        httpService.getRequest('special/getSpecial.do', {params: {pageNo: 1}})
            .then(function (data) {
                $scope.aSpecials = data.data.data.data;
            }, function (error) {
                console.log('getSpecial error'+ error);
            });
    }
    $scope.confirmDelete = function (id) {
        alert('yes');
    }
}]);
