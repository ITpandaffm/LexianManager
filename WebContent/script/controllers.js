myApp.controller('userController', ['$scope', '$interval', 'httpService', 'srefAndIconFatory',
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
                    console.log('getUserWithMenus error:' + error);
                });
        };

        $scope.subItemClick = function ($event) {
            angular.element('.panel-body a').removeClass('active');
            $($event.target).addClass('active');
        };

        $scope.signOut = function () {
            if (confirm('确定吗？' + $scope.oData.username)) {
                httpService.getRequest('manager/signOut.do', {})
                    .then(function (data) {
                        alert(data.data + '，请重新登录！');
                        window.location.hash = '';
                        window.location.pathname = '/LexianManager';
                    }, function (error) {
                        console.log('signOut error:' + error);
                    });
            }
        }

    }]);

//修改密码
myApp.controller('updatePwdController', ['$scope', '$state', 'httpService', function ($scope, $state, httpService) {
    $scope.updatePwd = function () {
        console.log($scope.oldPwd + ': ' + $scope.newPwd);
        httpService.getRequest('manager/updateManagerPassword.do', {
            params: {
                password: $scope.oldPwd,
                newPass: $scope.newPwd
            }
        })
            .then(function (data) {
                if (data.code == 1) {
                    alert(data.data);
                    $state.go('welcome');
                } else {
                    alert('旧密码错误！');
                }
            }, function (error) {
                console.log('updateManagerPassword error:' + error);
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
                console.log('getPrivileges error:' + error);
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
                    console.log('getUserWithMenus error:' + error);
                });
        }
    }]);

//查询后台用户
myApp.controller('queryUsersController', ['$scope', '$state', 'httpService', function ($scope, $state, httpService) {
    $scope.getManagers = function () {
        httpService.getRequest('handleManager/getManagers.do', {})
            .then(function (data) {
                $scope.aManagers = data.data.data;
            }, function (error) {
                console.log('getManagers error:' + error);
            })
    };
    $scope.addManager = function () {
        $state.go('authority/managers/addManager');
    }
}]);

//添加用户
myApp.controller('addManagerController', ['$scope', 'httpService', function ($scope, httpService) {
    $scope.getAllRoles = function () {
        httpService.getRequest('role/getAllRoles.do')
            .then(function (data) {
                $scope.aAllRoles = data.data;
            }, function (error) {
                console.log('getAllROles.do error'+error);
            })
    };
    $scope.addManager = function () {
        httpService.getRequest('handleManager/addManager.do')
    }
}]);

//修改用户
myApp.controller('editManagerController', ['$scope', '$stateParams', 'httpService', function ($scope, $stateParams, httpService) {
    $scope.name = $stateParams.name;
    $scope.info = $stateParams.info;
}]);

//查询角色
myApp.controller('queryRoleController', ['$scope', '$state', 'httpService', function ($scope, $state, httpService) {
    $scope.getRoles = function () {
        httpService.getRequest('role/getRoles.do', {})
            .then(function (data) {
                $scope.aRoles = data.data.data;
            }, function (error) {
                console.log('getRoles error:' + error);
            });
    };
    $scope.addRole = function () {
        $state.go('authority/role/addRole');
    };
}]);

//添加角色
myApp.controller('addRoleController', ['$scope', '$state', 'httpService', function ($scope, $state, httpService) {
    $scope.submitNewRole = function () {
        httpService.getRequest('role/addRole.do', {params: {name: $scope.name, description: $scope.description}})
            .then(function (data, status) {
                if (1 == data.code) {
                    var oNewRole = data.data;
                    alert('创建角色成功!欢迎您：' + oNewRole.name);
                    $state.go('authority/queryrole');
                } else {
                    alert('创建失败');
                }
            }, function (error) {
                console.log('addRole.do error' + error);
            });
    };
    $scope.clickEnter = function ($events) {
        if($events.keyCode == 13){
            $scope.submitNewRole();
        }
    }
}]);

//修改角色
myApp.controller('editRoleController', ['$scope', '$stateParams', '$state', 'httpService', function ($scope, $stateParams, $state, httpService) {
    $scope.name = $stateParams.name;
    $scope.description = $stateParams.description;
    $scope.updateRole = function () {
        httpService.getRequest('role/updateRole.do', {params: {
            id: $stateParams.id,
            name: $scope.name,
            description: $scope.description
        }})
            .then(function (data) {
                if(1 == data.code){
                    alert(data.data);
                    $state.go('authority/queryrole');
                } else {
                    alert('修改失败!');
                }
            }, function (error) {
                console.log('updateRole.do error:'+error);
            })
    };
    $scope.clickEnter = function ($events) {
        if($events.keyCode == 13){
            $scope.updateRole();
        }
    }
}]);

//修改角色菜单
myApp.controller('editRoleMenuController', ['$scope', '$stateParams', '$filter', '$state', 'httpService', 'isCheckFactory', 'getFilterIdFactory',
                     function ($scope, $stateParams, $filter, $state, httpService, isCheckFactory, getFilterIdFactory) {
    $scope.name = $stateParams.name;
    $scope.aMyMenusId = [];
    $scope.getMenus = function () {
        httpService.getRequest('role/getMenus.do', {params: {id: $stateParams.id}})
            .then(function (data) {
                $scope.aAllMenus = data.data[0];
                angular.forEach(data.data[1], function (value) {
                    //因为只需要勾选子项，所以把父项过滤，留下纯净的子项id数组，利于后面代码
                    if(value.parentId != null){
                        this.push(value.id);
                    }
                }, $scope.aMyMenusId);
                console.log($scope.aMyMenusId);
            }, function (error) {
                console.log('role/getMenus.do error: ' + error);
            });
    };
    $scope.isDefaultSelected = function (id) {
        return $scope.aMyMenusId.indexOf(id) != -1;
    };
    $scope.toggleCheck = function (id) {
        isCheckFactory.toggleCheck(id, $scope.aMyMenusId);
    };
    $scope.updateRoleMenu = function () {
        var arrFiler = getFilterIdFactory.getFilterArrById($scope.aMyMenusId, $scope.aAllMenus);
        console.log(arrFiler);
        httpService.getRequest('role/updateMenus.do?', {params: {id: $stateParams.id, menuId: arrFiler}})
            .then(function (data) {
                if (1 == data.code){
                    alert(data.data);
                    $state.go('authority/queryrole');
                }
            }, function (error) {
                console.log('role/updateMenus.do error:'+error);
            });
    };
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
                console.log('getCommodityStoreByStoreNo error: ' + error);
            })
    }
}]);

//门店商品
myApp.controller('storeGoodsController', ['$scope', 'httpService', function ($scope, httpService) {
    $scope.getStoreGoods = function () {
        httpService.getRequest('store/getAllStore.do', {params: {pageNo: 1}})
            .then(function (data) {
                $scope.aStoreGoods = data.data.data;
            }, function (error) {
                console.log('getGoods error: ' + error);
            })
    }
}]);

//order
//订单列表
myApp.controller('orderListController', ['$scope', 'orderService', function ($scope, orderService) {
    $scope.getOrderLists = function () {
        orderService.getOrdersByState(0, 1)
            .then(function (data) {
                $scope.aOrderLists = data.data.data;
            }, function (error) {
                console.log('getOrderss error' + error);
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
                console.log('orderUnpaid error' + error);
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
                console.log('orderUnpaid error' + error);
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
                console.log('orderUnpaid error' + error);
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
                console.log('orderUnpaid error' + error);
            });
    };
}]);

//activity
//特定活动
myApp.controller('activitySpecifyController', ['$scope', 'httpService', function ($scope, httpService) {
    $scope.getSpecials = function () {
        httpService.getRequest('special/getSpecial.do', {params: {pageNo: 1}})
            .then(function (data) {
                $scope.aSpecials = data.data.data;
            }, function (error) {
                console.log('getSpecial error' + error);
            });
    }
    $scope.confirmDelete = function (id) {
        alert('yes');
    }
}]);

//查看活动商品
myApp.controller('getSpecialCommoditiesController', ['$scope', '$stateParams', 'httpService', function ($scope, $stateParams, httpService) {
    $scope.getSpecialCommodities = function () {
        httpService.getRequest('speCommodity/getSpecialCommodities.do', {params: {id: $stateParams.aSpecialsID, pageNo: 1}})
            .then(function (data) {
                $scope.aSpecialCommodities = data.data.data;
            }, function (error) {
                console.log('getSpecialCommoditiesController error' + error);
            });
    };
}]);