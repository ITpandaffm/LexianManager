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
                if (1 == data.code) {
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
                if(1 == data.code){
                    $scope.aPrivileges = data.data.data;
                }
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
                    if(1 == data.code){
                        $scope.oData = {
                            aCenterListData: data.data.menus,
                            jSref: srefAndIconFatory.jSref,
                            aIcons: srefAndIconFatory.aIcons
                        };
                    }
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
                if(1 == data.code){
                    $scope.aManagers = data.data.data;
                }
            }, function (error) {
                console.log('getManagers error:' + error);
            })
    };
    $scope.updateDeleteId = function (id) {
        $scope.deleteId = id;
    };
    $scope.confirmDelete = function () {
        alert('delteId:' + $scope.deleteId);
        if ($scope.deleteId) {
            httpService.getRequest('handleManager/deleteManager.do', {params: {id: $scope.deleteId}})
                .then(function (data) {
                    if (1 == data.code) {
                        alert(data.data);
                        $scope.getManagers();
                    }
                }, function (error) {
                    console.log('handleManager/deleteManager.do error:' + error);
                })
        }
    };
    $scope.addManager = function () {
        $state.go('authority/managers/addManager');
    };
    $scope.resetManagerPwd = function (id) {
        if (confirm('确定重置密码为123456?')) {
            httpService.getRequest('handleManager/updateManager.do', {params: {id: id, password: 123456}})
                .then(function (data) {
                    if (1 == data.code) {
                        alert(data.data);
                    } else {
                        alert(data.data);
                    }
                });
        }
    };
    $scope.updateManagerStatus = function (status, id, name) {
        var updateStatus = 0;
        if (!!status) {
            if (confirm('确定禁用用户' + name + '?')) {
                updateStatusRequest(id, updateStatus);
            }
        } else {
            if (confirm('确定激活用户' + name + '?')) {
                updateStatus = 1;
                updateStatusRequest(id, updateStatus);
            }
        }
        function updateStatusRequest(id, updateStatus) {
            httpService.getRequest('handleManager/updateManager.do', {params: {id: id, status: updateStatus}})
                .then(function (data) {
                    if (1 == data.code) {
                        alert(data.data);
                        //感觉浏览器读缓存了，画面没有刷新。使用$apply();?
                        // $state.go('authority/queryusers');
                        $scope.getManagers(); //刷新界面
                    } else {
                        alert(data.data);
                    }
                });
        }
    };
}]);

//添加用户
myApp.controller('addManagerController', ['$scope', '$state', 'httpService', 'isCheckFactory', function ($scope, $state, httpService, isCheckFactory) {
    $scope.getAllRoles = function () {
        httpService.getRequest('role/getAllRoles.do')
            .then(function (data) {
                $scope.aAllRoles = data.data;
            }, function (error) {
                console.log('getAllROles.do error' + error);
            })
    };
    $scope.roleId = [];
    $scope.checkboxStateChange = function (id) {
        isCheckFactory.toggleCheck(id, $scope.roleId);
    };
    $scope.submitNewManager = function () {
        console.log($scope.roleId);
        httpService.getRequest('handleManager/addManager.do', {
            params: {
                name: $scope.username,
                password: $scope.password,
                info: $scope.description,
                roleId: $scope.roleId
            }
        })
            .then(function (data) {
                if (1 == data.code) {
                    alert(data.data);
                    $state.go('authority/queryusers');
                } else if (-2 == data.code) {
                    alert(data.data);
                } else {
                    alert('操作失败！');
                }
            }, function (error) {
                console.log('handleManager/addManager.do error:' + error);
            })
    }
}]);

//修改用户
myApp.controller('editManagerController', ['$scope', '$stateParams', '$state', 'httpService', function ($scope, $stateParams, $state, httpService) {
    $scope.name = $stateParams.name;
    $scope.info = $stateParams.info;
    $scope.submitUpdatedManager = function () {
        httpService.getRequest('handleManager/updateManager.do', {
            params: {
                id: $stateParams.id,
                name: $scope.name,
                info: $scope.info
            }
        })
            .then(function (data) {
                if (1 == data.code) {
                    alert(data.data);
                    $state.go('authority/queryusers');
                } else {
                    alert(data.data);
                }
            });
    };
}]);

//用户关联角色
myApp.controller('associateRoleController', ['$scope', '$stateParams', '$state', 'httpService', 'isCheckFactory', function ($scope, $stateParams, $state, httpService, isCheckFactory) {
    $scope.name = $stateParams.name;
    $scope.roleId = [];
    $scope.getAllRolesAndMine = function () {
        httpService.getRequest('role/getAllRoles.do')
            .then(function (data) {
                $scope.aAllRoles = data.data;
            }, function (error) {
                console.log('getAllROles.do error' + error);
            });
        httpService.getRequest('handleManager/getRoleByManagerId.do', {params: {managerId: $stateParams.id}})
            .then(function (data) {
                if (1 == data.code) {
                    var aMyRoles = data.data;
                    angular.forEach(aMyRoles, function (value) {
                        $scope.roleId.push(value.id);
                    });
                }
            }, function (error) {
                console.log('handleManager/getRoleByManagerId.do error' + error);
            });
    };
    $scope.isAssociateRole = function (id) {
        return $scope.roleId.indexOf(id) != -1;
    }
    $scope.checkboxStateChange = function (id) {
        isCheckFactory.toggleCheck(id, $scope.roleId);
    };
    $scope.submiteNewAssociateManager = function () {
        httpService.getRequest('handleManager/updateAssociatedRole.do', {
            params: {
                id: $stateParams.id,
                newRoleId: $scope.roleId
            }
        })
            .then(function (data) {
                if (1 == data.code) {
                    alert(data.data);
                    $state.go('authority/queryusers');
                }
            }, function (error) {
                console.log('handleManager/updateAssociatedRole.do error' + error);
            });
    }
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
        if ($events.keyCode == 13) {
            $scope.submitNewRole();
        }
    }
}]);

//修改角色
myApp.controller('editRoleController', ['$scope', '$stateParams', '$state', 'httpService', function ($scope, $stateParams, $state, httpService) {
    $scope.name = $stateParams.name;
    $scope.description = $stateParams.description;
    $scope.updateRole = function () {
        httpService.getRequest('role/updateRole.do', {
            params: {
                id: $stateParams.id,
                name: $scope.name,
                description: $scope.description
            }
        })
            .then(function (data) {
                if (1 == data.code) {
                    alert(data.data);
                    $state.go('authority/queryrole');
                } else {
                    alert('修改失败!');
                }
            }, function (error) {
                console.log('updateRole.do error:' + error);
            })
    };
    $scope.clickEnter = function ($events) {
        if ($events.keyCode == 13) {
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
                        if (value.parentId != null) {
                            this.push(value.id);
                        }
                    }, $scope.aMyMenusId);
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
            httpService.getRequest('role/updateMenus.do?', {params: {id: $stateParams.id, menuId: arrFiler}})
                .then(function (data) {
                    if (1 == data.code) {
                        alert(data.data);
                        $state.go('authority/queryrole');
                    }
                }, function (error) {
                    console.log('role/updateMenus.do error:' + error);
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
    };
    $scope.updateUsersStatus = function (id, status) {
        var updatedStatus = -1;
        if (1 == status) {
            if (confirm('确定禁用该用户？')) {
                updateVipStatus(id, updatedStatus);
            }
        } else {
            if (confirm('确定激活该用户？')) {
                updatedStatus = 1;
                updateVipStatus(id, updatedStatus);
            }
        }
        function updateVipStatus(id, updatedStatus) {
            httpService.getRequest('user/updateUser.do?', {params: {id: id, status: updatedStatus}})
                .then(function (data) {
                    if (1 == data.code) {
                        alert(data.data);
                        $scope.getVips();
                    } else {
                        alert(data.data);
                    }
                }, function (error) {
                    console.log('user/updateUser.do error:' + error);
                });
        }
    }
}]);

//goods
//分类管理
myApp.controller('goodsCategoryController', [
    '$scope',
    'httpService',
    function($scope, httpService) {
        //一级分类
        $scope.getFirstCategory = function() {
            httpService.getRequest('categoryView/getFirstCategoryView.do', {params:{pageNo:1}}).then(
                function(data) {
                    if(1 == data.code){
                        $scope.aFirstCategory = data.data.data;
                    }
                }, function(error) {
                    console.log('getFirstCategoryView error:' + error);
                });
        }
        //二级分类
        $scope.getSecondCategory = function(){
            httpService.getRequest('categoryView/getSecondCategoryView.do', {params:{pageNo:1}}).then(
                function(data) {
                    if(1 == data.code){
                        $scope.aSecondCategory = data.data.data;
                    }
                }, function(error) {
                    console.log('getSecondCategoryView error:' + error);
                });
        }
        //三级分类
        $scope.getThirdCategory = function(){
            httpService.getRequest('categoryView/getThirdCategoryView.do', {params:{pageNo:1}}).then(
                function(data) {
                    if(1 == data.code){
                        $scope.aThirdCategory = data.data.data;
                        console.log(data);
                    }
                }, function(error) {
                    console.log('getThirdCategoryView error:' + error);
                });
        }
    } ]);
//goodsinfo
//商品信息管理
myApp.controller('goodsInfoController', [ '$scope', 'httpService',
    function($scope, httpService) {
        $scope.getGoodsInfo = function() {
            httpService.getRequest('commodity/getCommodities.do', {
                params : {
                    pageNo : 1
                }
            }).then(function(data) {
                if(1 == data.code){
                    $scope.aGoodsInfo = data.data.data;
                    console.log(data.data.data);
                }
            }, function(error) {
                console.log('getCommodityStoreByStoreNo error: ' + error);
            })
        }
    } ]);

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

//订单详情
myApp.controller('orderDetailController', ['$scope', '$stateParams', 'httpService', function ($scope, $stateParams, httpService) {
    $scope.getDetail = function () {
        httpService.getRequest('order/getOrderDetail.do', {params: {id: $stateParams.id}})
            .then(function (data) {
                if(1==data.code){
                    $scope.oDetail = data.data;
                }
            }, function (error) {
                    console.log('order/getOrderDetail.do error:'+error);
            })
    }
}]);

// activity
// 特定活动
myApp.controller('activitySpecifyController', ['$scope', '$state','httpService', function ($scope,$state, httpService) {
        $scope.getSpecials = function () {
            httpService.getRequest('special/getSpecial.do', {
                params: {
                    pageNo: 1
                }
            }).then(function (data) {
                if(1 == data.code){
                    $scope.aSpecials = data.data.data;
                }
            }, function (error) {
                console.log('getSpecial error' + error);
            });
        };
        //添加活动
        $scope.SpeName = "";
        $scope.addSpe = function () {
            httpService.getRequest('special/addSpecial.do', {params: {name: $scope.SpeName}})
                .then(function (data) {
                    if (1 == data.code) {
                        $scope.getSpecials();
                        $scope.SpeName = '';
                    } else {
                        alert("该模块已存在");
                    }
                }, function (error) {
                    console.log('addSpe error' + error);
                });
        };
        //删除活动
        $scope.getSpeDel = function (id) {
            $scope.getSpeComDel = id;
        };
        $scope.confirmDelete = function () {
            httpService.getRequest('special/deleteSpecial.do', {params: {id: $scope.getSpeComDel}})
                .then(function (data) {
                    if (1 == data.code) {
                        $scope.getSpecials();
                    } else {
                        alert("请先删除子项");
                    }
                }, function (error) {
                    console.log('confirmDelete error' + error);
                })
        };
        //修改模块名称
        $scope.changeModuleName = function (id, name) {
            $scope.activityName = name;
            $scope.activityID = id;
        };
        $scope.updateSpecialName = function () {
            httpService.getRequest('special/updateSpecial.do', {
                params: {
                    id: $scope.activityID,
                    name: $scope.activityName
                }
            }).then(function (data) {
                if(1 == data.code){
                    $scope.getSpecials();
                }
            }, function (error) {
                console.log('updateSpecial error' + error);
            });
        };
}]);

// 查看活动商品
myApp.controller('getSpecialCommoditiesController', ['$scope', '$stateParams', 'httpService',
                                            function ($scope, $stateParams, httpService) {
        $scope.getSpecialCommodities = function () {
            httpService.getRequest('speCommodity/getSpecialCommodities.do',
                {
                    params: {
                        id: $stateParams.aSpecialsID,
                        pageNo: 1
                    }
                }).then(
                function (data) {
                    if(1 == data.code){
                        $scope.aSpecialCommodities = data.data.data;
                    }
                },
                function (error) {
                    console.log('getSpecialCommoditiesController error'
                        + error);
                });
        };
        //添加活动商品
        $scope.getAllCategories = function () {

            $scope.commodity = [];
            $scope.fid = -1;
            $scope.sid = -1;
            $scope.tid = -1;
            $scope.gid = -1;
            $scope.firstCategories = [];
            $scope.thirdCategories = [];
            $scope.secondCategories = [];
            $scope.commodityNo = null;
            httpService.getRequest('sort/getAllCategories.do').then(
                function (data) {
                    if(1 == data.code){
                        $scope.allCategories = data.data;
                        $scope.firstCategories = [];
                        for (var i = 0; i < $scope.allCategories.length; i++) {
                            if ($scope.allCategories[i].parentId == null) {
                                $scope.firstCategories.push($scope.allCategories[i]);
                            }
                        }
                    }
                },
                function (error) {
                    console.log('getSpecialCommoditiesController error'
                        + error);
                });
        };

        $scope.changeSecCat = function (parentId) {
            $scope.sid = -1;
            $scope.tid = -1;
            $scope.gid = -1;
            $scope.secondCategories = [];
            $scope.thirdCategories = [];
            $scope.commodity = [];
            $scope.commodityNo = null;
            for (var i = 0; i < $scope.allCategories.length; i++) {
                if ($scope.allCategories[i].parentId == parentId) {
                    $scope.secondCategories.push($scope.allCategories[i]);
                }
            }
        };

        $scope.changeThiCat = function (parentId) {
            $scope.tid = -1;
            $scope.gid = -1;
            $scope.thirdCategories = [];
            $scope.commodity = [];
            $scope.commodityNo = null;
            for (var i = 0; i < $scope.allCategories.length; i++) {
                if ($scope.allCategories[i].parentId == parentId) {
                    $scope.thirdCategories.push($scope.allCategories[i]);
                }
            }
        };

        $scope.getGoodsName = function (categoryId) {
            $scope.gid = -1;
            $scope.commodity = [];
            $scope.commodityNo = null;
            httpService.getRequest('commodity/getCommodityByCategoryId.do', {params: {categoryId: categoryId}}).then(
                function (data) {
                    if(1 == data.code){
                        $scope.commodity = data.data;
                    }
                },
                function (error) {
                    console.log('getSpecialCommoditiesController error'
                        + error);
                });
        };

        $scope.getGoodsId = function (goodsId) {
            $scope.commodityNo = null;
            for (var i = 0; i < $scope.commodity.length; i++) {
                if ($scope.commodity[i].id == goodsId) {
                    $scope.commodityNo = $scope.commodity[i].commodityNo;
                    break;
                }
            }
        };

        $scope.addCommodity = function () {
            httpService.getRequest('speCommodity/addSpecialCommodities.do', {
                params: {
                    commodityNo: $scope.commodityNo,
                    specialId: $stateParams.aSpecialsID
                }
            })
                .then(function (data) {
                    if (1 == data.code) {
                        $scope.getSpecialCommodities();
                    } else {
                        alert("该商品已存在");
                        $scope.getSpecialCommodities();
                    }
                }, function (error) {
                    console.log('addCommodity error' + error)
                })
        };
        //删除活动商品
        $scope.getDelID = function (id) {
            $scope.getComDelID = id;
        };
        $scope.confirmDelete = function () {
            httpService.getRequest('speCommodity/deleteSpeCommodity.do', {params: {id: $scope.getComDelID}})
                .then(function (data) {
                    if(1 == data.code){
                        $scope.getSpecialCommodities();
                    }
                }, function (error) {
                    console.log('confirmDelete error' + error);
                })
        };
    }]);