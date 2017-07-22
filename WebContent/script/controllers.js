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
                if (1 == data.code) {
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
                    if (1 == data.code) {
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
                if (1 == data.code) {
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
    };
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

// goods
// 分类管理
myApp.controller('goodsCategoryController', ['$scope', '$state', 'httpService', function ($scope, $state, httpService) {
    // 一级分类
    $scope.getFirstCategory = function () {
        httpService.getRequest('categoryView/getFirstCategoryView.do', {params: {pageNo: 1}})
            .then(function (data) {
                if (1 == data.code) {
                    $scope.aFirstCategory = data.data.data;
                }
            }, function (error) {
                console.log('getFirstCategoryView error:' + error);
            });
    };
    // 二级分类
    $scope.getSecondCategory = function () {
        httpService.getRequest('categoryView/getSecondCategoryView.do', {params: {pageNo: 1}})
            .then(function (data) {
                if (1 == data.code) {
                    $scope.aSecondCategory = data.data.data;
                }
            }, function (error) {
                console.log('getSecondCategoryView error:' + error);
            });
    };
    // 三级分类
    $scope.getThirdCategory = function () {
        httpService.getRequest('categoryView/getThirdCategoryView.do', {params: {pageNo: 1}})
            .then(function (data) {
                if (1 == data.code) {
                    $scope.aThirdCategory = data.data.data;
                }
            }, function (error) {
                console.log('getThirdCategoryView error:' + error);
            });
    };
    // 二级路由
    $scope.addCategory = function () {
        $state.go('goods/category/addCategory');
    };
    // 删除分类
    $scope.updateDeleteId = function (id, level) {
        $scope.delId = id;
        $scope.deleteInLevel = level;
    };
    $scope.confirmDelete = function () {
        httpService.getRequest('sort/deleteCategory.do', {params: {id: $scope.delId}})
            .then(function (data) {
                if (1 == data.code) {
                    alert(data.data);
                    switch ($scope.deleteInLevel) {
                        case 1:
                            $scope.getFirstCategory();
                            break;
                        case 2:
                            $scope.getSecondCategory();
                            break;
                        case 3:
                            $scope.getThirdCategory();
                            break;
                    }
                } else if (-3 == data.code) {
                    alert("该类下还有子项，删除失败");
                }
            }, function (error) {
                console.log('deleteSpecial error:' + error);
            });
    };
}]);
// 添加商品分类
myApp.controller('addCategoryController', ['$scope', '$state', '$filter', 'httpService', function ($scope, $state, $filter, httpService) {
    $scope.getAllCategories = function () {
        httpService.getRequest('sort/getCategories.do')
            .then(function (data) {
                if (1 == data.code) {
                    $scope.secondeCategories = $filter('filter')(data.data, {'type': 2});
                    $scope.firstCategories = $filter('filter')(data.data, {'type': 1});

                }
            }, function (error) {
                console.log('getAllCategories error:' + error);
            });
    };
    $scope.categoryLevel = 0;
    $scope.updateCategoryLevel = function () {
        switch ($scope.categoryLevel) {
            case '1':
                $scope.currentCategory = $scope.firstCategories;
                $scope.parentId = $scope.currentCategory[0].id;
                break;
            case '2':
                $scope.currentCategory = $scope.secondeCategories;
                $scope.parentId = $scope.currentCategory[0].id;
                break;
            default:
        }
    };
    $scope.addCategory = function () {
        httpService.getRequest('sort/addCategory.do', {
            params: {
                categoryName: $scope.categoryName,
                type: ++$scope.categoryLevel,
                parentId: $scope.parentId
            }
        }).then(function (data) {
            if (1 == data.code) {
                alert(data.data);
                $state.go('goods/category');
            }
        }, function (error) {
            console.log('addCategory error:' + error);
        });

    };
}]);
// 修改商品分类
myApp.controller('changeCategoryController', ['$scope', '$stateParams', '$state', 'httpService', function ($scope, $stateParams, $state, httpService) {
    $scope.categoryName = $stateParams.categoryName;
    $scope.submitNewCategoryName = function () {
        httpService.getRequest('sort/updateCategory.do', {
            params: {
                id: $stateParams.id,
                categoryName: $scope.categoryName
            }
        }).then(function (data) {
            if (1 == data.code) {
                alert(data.data);
                $state.go('goods/category');
            }
        }, function (error) {
            console.log('changeCategory error:' + error);
        });
    }
}]);

// goodsinfo
// 商品信息管理
myApp.controller('goodsInfoController', ['$scope', '$state', 'httpService', function ($scope, $state, httpService) {
    $scope.getGoodsInfo = function () {
        httpService.getRequest('commodity/getCommodities.do', {params: {pageNo: 1}})
            .then(function (data) {
                if (1 == data.code) {
                    $scope.aGoodsInfo = data.data.data;
                }
            }, function (error) {
                console.log('getCommodityStoreByStoreNo error: ' + error);
            })
    };
    $scope.addNewGoods = function () {
        $state.go('goods/info/addNewGoods');
    }
}]);

myApp.controller('addNewGoodsController', ['$scope', '$state', '$filter', 'httpService', function ($scope, $state, $filter, httpService) {
    $scope.getAllCategories = function () {
        httpService.getRequest('sort/getCategories.do')
            .then(function (data) {
                if (1 == data.code) {
                    $scope.firstCategories = $filter('filter')(data.data, {'type': 1});
                    $scope.secondAllCategories = $filter('filter')(data.data, {'type': 2});
                    $scope.thirdAllCategories = $filter('filter')(data.data, {'type': 3});
                }
            }, function (error) {
                console.log('sort/getCategories.do error:' + error);
            });
        //采用$watch而不是ng-change来监听，是因为如果设置默认值第一项，那么如果真的就想选第一项的话，得点击下拉框重新选中第一项，才会触发ng-change，后面的下拉框才会显示
        $scope.$watch('level1', function (newValue) {
            if (newValue) {
                $scope.secondCategories = $filter('filter')($scope.secondAllCategories, {'parentId': newValue.id});
                $scope.level2 = $scope.secondCategories[0];
            } else {
                //为null，即选中了默认项，“--请选择一级分类--”
                $scope.secondCategories.length = 0;
                $scope.thirdCategories.length = 0;
                $scope.level3 = null;
            }
        });
        $scope.$watch('level2', function (newValue) {
            if (newValue) {
                $scope.thirdCategories = $filter('filter')($scope.thirdAllCategories, {'parentId': newValue.id});
                $scope.level3 = $scope.thirdCategories[0];
            }
        })
    };
    $scope.bSelectDirty = false;
    $scope.secondCategories = [];
    $scope.thirdCategories = [];
    $scope.level1Change = function () {
        if (!$scope.bSelectDirty) {
            $scope.bSelectDirty = !$scope.bSelectDirty;
        }
    };
    $scope.submitNewCommodity = function () {
        httpService.getRequest('commodity/addCommodity.do', {
            params: {
                commodityNo: $scope.commodityNo,
                name: $scope.name,
                categoryId: $scope.level3.id,
                introduce: $scope.introduce
            }
        }).then(function (data) {
            if (1 == data.code) {
                alert(data.data);
                $state.go('goods/info');
            } else if (-3 == data.code) {
                alert(data.data);
            } else {
                alert('操作失败');
            }
        }, function (error) {
            console.log('commodity/addCommodity.do error:' + error);
        });
    };
}]);

myApp.controller('updateGoodsInfoController', ['$scope', '$state', '$stateParams', '$filter', '$interval', 'httpService', 'fileUploadService',
    function ($scope, $state, $stateParams, $filter, $interval, httpService, fileUploadService) {

        $scope.aSubPicUrl = [];     //配图
        $scope.commodtySpecs = [];  //规格信息
        $scope.mainPicBtn = '上传主图';
        $scope.bMainPicBtnActive = false;
        $scope.subPicBtn = '上传配图';
        $scope.bSubPicBtnActive = false;
        $scope.getGoodsInfoById = function () {

            $scope.getAllCategories();  //先初始化下拉列表

            httpService.getRequest('commodity/getCommodityById.do', {params: {id: $stateParams.id}})
                .then(function (data) {
                    if (1 == data.code) {
                        //上传主图按钮


                        var obj = data.data;
                        $scope.name = obj.name;
                        $scope.introduce = obj.introduce;

                        $scope.commodityNo = obj.commodityNo;

                        $scope.secondCategories = $filter('filter')($scope.secondAllCategories, {'parentId': obj.categoryView.firstId});
                        $scope.thirdCategories = $filter('filter')($scope.thirdAllCategories, {'parentId': obj.categoryView.secondtId});

                        $scope.level1 = ($filter('filter')($scope.firstCategories, {'id': obj.categoryView.firstId}))[0];
                        if ($scope.secondCategories.length) {
                            $scope.level2 = ($filter('filter')($scope.secondCategories, {'id': obj.categoryView.secondtId}))[0];
                        }
                        if ($scope.thirdCategories.length) {
                            $scope.level3 = ($filter('filter')($scope.thirdCategories, {'id': obj.categoryView.thirdId}))[0];
                        }

                        //规格信息
                        if (obj.commodtySpecs.length) {
                            $scope.commodtySpecs = obj.commodtySpecs;
                        }
                        //激活状态
                        if (1 == obj.states) {
                            $scope.states = true;
                        } else if (-1 == obj.states) {
                            $scope.states = false;
                        }
                        //取主图
                        $scope.pictureUrl = obj.pictureUrl;
                        //配图
                        if (obj.commodityPicuture.length) {
                            $scope.aSubPicUrl = obj.commodityPicuture;
                        }
                        //商品详情初始化
                        $scope.detailed = obj.detailed;
                    }
                }, function (error) {
                    console.log('commodity/getCommodityById.do error:' + error);
                });
        };
        $scope.getAllCategories = function () {
            httpService.getRequest('sort/getCategories.do')
                .then(function (data) {
                    if (1 == data.code) {
                        $scope.firstCategories = $filter('filter')(data.data, {'type': 1});
                        $scope.secondAllCategories = $filter('filter')(data.data, {'type': 2});
                        $scope.thirdAllCategories = $filter('filter')(data.data, {'type': 3});
                    }
                }, function (error) {
                    console.log('sort/getCategories.do error:' + error);
                });
        };
        $scope.level1Change = function () {
            $scope.secondCategories = $filter('filter')($scope.secondAllCategories, {'parentId': $scope.level1.id});
            if ($scope.secondCategories.length) {
                $scope.level2 = $scope.secondCategories[0];
            }
            $scope.thirdCategories = $filter('filter')($scope.thirdAllCategories, {'parentId': $scope.level2.id});
            if ($scope.thirdCategories.length) {
                $scope.level3 = $scope.thirdCategories[0];
            } else {
                $scope.level3 = null;
            }
        };
        $scope.level2Change = function () {
            $scope.thirdCategories = $filter('filter')($scope.thirdAllCategories, {'parentId': $scope.level2.id});
            if ($scope.thirdCategories.length) {
                $scope.level3 = $scope.thirdCategories[0];
            } else {
                $scope.level3 = null;
            }
        };
        $scope.addInfoBlock = function () {
            $scope.commodtySpecs.push({
                commodityNo: $scope.commodityNo,
                specGroup: $scope.specGroup,
                specName: $scope.specName
            });
        };
        $scope.removeInfoBlock = function (specGroup, specName) {
            angular.forEach($scope.commodtySpecs, function (value, index) {
                if (value.specGroup == specGroup && value.specName == specName) {
                    $scope.commodtySpecs.splice(index, 1);
                }
            });
        };

        $scope.uploadMainPic = function () {
            let file = $scope.mainPicFile;
            if (!file) {
                alert('上传图片为空！');
                return;
            }
            let uploadUrl = 'uploadPicture/uploadSinglePic.do';
            $scope.mainPicBtn = '上传主图中';
            $scope.bMainPicBtnActive = true;
            let count = 0;
            var timer = $interval(function () {
                $scope.mainPicBtn += '.';
                count++
                if (count == 4) {
                    const index = $scope.mainPicBtn.indexOf('.');
                    $scope.mainPicBtn = $scope.mainPicBtn.substring(0, index);
                    count = 0;
                }
            }, 1000);

            fileUploadService.uploadFileToUrl(file, uploadUrl)
                .then(function (data) {
                    if (1 == data.code) {
                        alert('上传成功！')
                        $scope.pictureUrl = data.data;
                        $scope.bMainPicBtnActive = false;
                        $interval.cancel(timer);
                        $scope.mainPicBtn = '上传主图';
                        angular.element('#mainPicInput').val('');
                    }
                }, function (error) {
                    console.log('mainpic UploadPicture/uploadMainPic.do error:' + error);
                });
        };
        $scope.uploadSubPic = function () {
            let file = $scope.subPicFile;
            if (!file) {
                alert('上传图片为空！');
                return;
            }
            let uploadUrl = 'uploadPicture/uploadSinglePic.do';

            $scope.bSubPicBtnActive = true;
            $scope.subPicBtn = '上传配图中';
            let count = 0;
            var timer = $interval(function () {
                $scope.subPicBtn += '.';
                count++
                if (count == 4) {
                    const index = $scope.subPicBtn.indexOf('.');
                    $scope.subPicBtn = $scope.subPicBtn.substring(0, index);
                    count = 0;
                }
            }, 1000);
            fileUploadService.uploadFileToUrl(file, uploadUrl)
                .then(function (data) {
                    if (1 == data.code) {
                        alert('上传成功!');
                        $scope.aSubPicUrl.push(data.data);
                        $scope.bSubPicBtnActive = false;
                        $interval.cancel(timer);
                        $scope.subPicBtn = '上传配图';
                        angular.element('#subPicInput').val('');
                    }
                }, function (error) {
                    console.log('subpic uploadPicture/uploadSinglePic.do error:' + error);
                });
        };
        $scope.removeSubPic = function (url) {
            angular.forEach($scope.aSubPicUrl, function (value, index) {
                if(value == url){
                    $scope.aSubPicUrl.splice(index, 1);
                }
            })
        };
        //富文本编辑器初始化
        $scope.submitUpdatedInfo = function () {
            httpService.postRequest('commodity/updateCommodity.do',{
                commodityNo: $scope.commodityNo,
                name: $scope.name,
                states: $scope.states,
                categoryId: $scope.level3.id,
                introduce: $scope.introduce,
                detailed: $scope.detailed,
                pictureUrl: $scope.pictureUrl,
                commodtySpecs: $scope.commodtySpecs,
                commodityPicuture: $scope.aSubPicUrl
            }).then(function (data) {
                console.log(data);
                if(1==data.code){
                    alert(data.data);
                } else {
                    alert(data.data);
                }
            }, function (error) {
                console.log('commodity/updateCommodity.do error:'+error);
            })
        };
    }]);
// store
// 门店信息
myApp.controller('storeInfoController', ['$scope', '$state', 'httpService',
    function ($scope, $state, httpService) {
        $scope.getStoreInfo = function () {
            httpService.getRequest('store/getAllStore.do', { params: { pageNo: 1 } })
                .then(function (data) {
                if(1==data.code){
                    $scope.aStoreInfo = data.data.data;
                }
            }, function (error) {
                console.log('getCommodityStoreByStoreNo error: ' + error);
            })
        };
        $scope.forbiddenStore = function (id, status) {
            status = -status;
            httpService.getRequest('store/updateStore.do', {params: {id: id, status: status}})
                .then(function (data) {
                    if(1==data.code){
                        $scope.getStoreInfo();
                    }
                }, function (error) {
                    console.log('store/updateStore.do' + error);
                })
        };
        // 二级路由
        $scope.addStore = function () {
            $state.go('store/info/addStore');
        };
    }]);
// 添加门店
myApp.controller('addStoreController', [ '$scope', '$filter', 'httpService', function ($scope, $filter, httpService) {
        // 城市三级联动世
        $scope.getProvince = function () {
            httpService.getRequest('city/getCities.do', {})
                .then(function (data) {
                    if (1 == data.code) {
                        $scope.aProvince = data.data;
                        $scope.provinceId = 0;
                    }
                }, function (error) {
                    console.log('city/getProvince error: ' + error);
                });
        };
        $scope.updateProvince = function () {
            $scope.aCounty = [];
            httpService.getRequest('city/getCities.do', { params: { parentId: $scope.provinceId }})
                .then(function (data) {
                if (1 == data.code) {
                    $scope.aCitys = data.data;
                    $scope.citysId = $scope.aCitys[0].id;
                }
            }, function (error) {
                console.log('city/getCities.do error: ' + error);
            });
        };
        $scope.getCounty = function (citysId) {
            httpService.getRequest('city/getCities.do', {
                params: {
                    parentId: citysId
                }
            }).then(function (data) {
                if (1 == data.code) {
                    $scope.aCounty = data.data;
                    $scope.countyId = $scope.aCounty[0].id;
                    console.log(data.data);
                }
            }, function (error) {
                console.log('city/getCities.do error: ' + error);
            });
        };
        // 添加门店
        $scope.submitNewStore = function () {
            httpService.getRequest(
                'store/addStore.do',
                {
                    params: {
                        storeNo: $scope.storeNo,
                        storeName: $scope.storeName,
                        storeAddress: $scope.storeAddress,
                        introduce: $scope.storeIntroduce,
                        startTime: $filter('date')($scope.startTime,
                            'HH:mm:ss'),
                        closeTime: $filter('date')($scope.closeTime,
                            'HH:mm:ss'),
                        maxLatItude: $scope.maxLatitude,
                        minLatItude: $scope.minLatitude,
                        maxLongItude: $scope.maxLongitude,
                        minLongItude: $scope.minLongitude,
                        provinceId: $scope.provinceId,
                        cityId: $scope.citysId,
                        countyId: $scope.countyId,
                        status: 1
                    }
                }).then(function (data) {
                if (1 == data.code) {
                    alert("success");
                }
            }, function (error) {
                console.log('addStore error: ' + error);
            });
        }
    }]);
// 修改门店信息
myApp.controller('changeStoreController', [
    '$scope',
    '$filter',
    '$stateParams',
    'httpService',
    function ($scope, $filter, $stateParams, httpService) {
        $scope.storeNo = $stateParams.storeNo;
        console.log($scope.storeNo);
        httpService.getRequest('store/getStoreByStoreNo.do', {
            params: {
                storeNo: $scope.storeNo
            }
        }).then(function (data) {
            if (1 == data.code) {
                console.log(data.data);
                $scope.storeNo = data.data.storeNo;
                $scope.storeName = data.data.storeName;
                $scope.storeAddress = data.data.storeAddress;
                $scope.storeIntroduce = data.data.introduce;
                $scope.startTime = $filter('date')(data.data.startTime, 'HH:mm');
                $scope.closeTime = $filter('date')(data.data.closeTime, 'HH:mm');
                $scope.maxLatitude = data.data.maxLatItude;
                $scope.minLatitude = data.data.minLatItude;
                $scope.maxLongitude = data.data.maxLongItude;
                $scope.minLongitude = data.data.minLongItude;
                $scope.provinceId = data.data.provinceId;
                $scope.citysId = data.data.cityId;
                $scope.countyId = data.data.countyId;
                console.log($scope.startTime, $scope.closeTime);
            }
        }, function (error) {
            console.log('getStoreByStoreNo error: ' + error);
        });

        // 城市三级联动世
        $scope.getProvince = function () {
            httpService.getRequest('city/getCities.do', {}).then(
                function (data) {
                    if (1 == data.code) {
                        $scope.aProvince = data.data;
                    }
                }, function (error) {
                    console.log('getGoods error: ' + error);
                });
        };
        $scope.getCitys = function () {
            $scope.aCounty = [];
            httpService.getRequest('city/getCities.do', {
                params: {
                    parentId: $scope.provinceId
                }
            }).then(function (data) {
                if (1 == data.code) {
                    $scope.aCitys = data.data;
                    $scope.citysId = $scope.aCitys[0].id;
                    console.log(data.data);
                }
            }, function (error) {
                console.log('getGoods error: ' + error);
            });
        };
        $scope.getCounty = function (citysId) {
            httpService.getRequest('city/getCities.do', {
                params: {
                    parentId: citysId
                }
            }).then(function (data) {
                if (1 == data.code) {
                    $scope.aCounty = data.data;
                    $scope.countyId = $scope.aCounty[0].id;
                    console.log(data.data);
                }
            }, function (error) {
                console.log('getGoods error: ' + error);
            });
        };
    }]);
// 门店商品
myApp.controller('storeGoodsController', ['$scope', 'httpService',
    function ($scope, httpService) {
        $scope.getStoreGoods = function () {
            httpService.getRequest('store/getAllStore.do', {
                params: {
                    pageNo: 1
                }
            }).then(function (data) {
                if (1 == data.code) {
                    $scope.aStoreGoods = data.data.data;
                }
            }, function (error) {
                console.log('getGoods error: ' + error);
            });
        };
    }]);
// 店铺商品管理
myApp.controller('manageStoreController', ['$scope', '$state', '$stateParams',
    'httpService', function ($scope, $state, $stateParams, httpService) {
        $scope.storeId = $stateParams.aStoreGoodsId;
        $scope.storeName = $stateParams.aStoreGoodsName;
        $scope.getCommodityStore = function () {
            httpService.getRequest('commoditystore/getCommodityStoreByStoreNo.do', {
                params: {
                    storeNo: $scope.storeId,
                    pageNo: 1
                }
            }).then(function (data) {
                if (1 == data.code) {
                    $scope.commodityStore = data.data.data;
                    console.log($scope.commodityStore);
                }
            }, function (error) {
                console.log('getCommodityStoreByStoreNo error' + error);
            });
        };

        $scope.changeCommodityType = function (commodity) {
            $scope.commodityInfo = commodity;
            $scope.commodityInfo.type = -commodity.type;
            httpService.getRequest('commoditystore/updateCommodityStore.do', {
                params: {
                    type: $scope.commodityInfo.type,
                }
            }).then(function (data) {
                if (1 == data.code) {
                }
            }, function (error) {
                console.log('updateCommodityStore error' + error);
            });
        };

        $scope.registerGoods = function () {
            $state.go('store/goods/manageStore/registerGoods');
        }
    }]);
//注册新商品
myApp.controller('registerGoodsController', ['$scope', '$stateParams', 'httpService',
    function ($scope, $stateParams, httpService) {
//		$scope.storeName = $stateParams.storeName;
    }]);
//修改价格
myApp.controller('changePriceController', ['$scope', '$stateParams', 'httpService',
    function ($scope, $stateParams, httpService) {
        $scope.storeName = $stateParams.storeName;
        $scope.commodotyPrice = $stateParams.commodotyPrice;
        $scope.realPrice = $stateParams.realPrice;
        $scope.id = $stateParams.id;
        console.log($scope.id);
        //更改价格
        $scope.changePrice = function () {
            httpService.getRequest('commoditystore/updateCommodityStore.do', {
                params: {
                    id: $scope.id,
                    commodotyPrice: $scope.commodotyPrice,
                    realPrice: $scope.realPrice
                }
            }).then(function (data) {
                if (1 == data.code) {
                    alert("success");
                }
            }, function (error) {
                console.log('updateCommodityStore error' + error);
            });
        };
    }]);
//修改库存
myApp.controller('changeAmontController', ['$scope', '$stateParams', 'httpService',
    function ($scope, $stateParams, httpService) {
        $scope.storeName = $stateParams.storeName;
        $scope.id = $stateParams.id;
        $scope.commodityAmont = $stateParams.commodityAmont;
        $scope.changeAmont = function () {
            httpService.getRequest('commoditystore/updateCommodityStore.do', {
                params: {
                    id: $scope.id,
                    commodityAmont: $scope.commodityAmont
                }
            }).then(function (data) {
                if (1 == data.code) {
                    alert("success");
                }
            }, function (error) {
                console.log('updateCommodityStore error' + error);
            });
        }
    }]);

//order
//订单列表
myApp.controller('orderListController', ['$scope', '$filter', 'orderService', 'orderSearchByDateService', function ($scope, $filter, orderService, orderSearchByDateService) {
    $scope.getOrderLists = function () {
        orderService.getOrdersByState(0, 1)
            .then(function (data) {
                $scope.aOrderLists = data.data.data;
            }, function (error) {
                console.log('getOrderss error' + error);
            });
        angular.element('.datePicker-btn').daterangepicker(null, function (start, end) {
            var oStartDate = new Date(start._d);
            var oEndDate = new Date(end._d);
            $scope.fromDate = $filter('date')(oStartDate, 'yyyy-MM-dd');
            $scope.toDate = $filter('date')(oEndDate, 'yyyy-MM-dd');
        });
    };

    $scope.searchByDate = function () {
        orderSearchByDateService.orderSearchByDate($scope.fromDate, $scope.toDate, 0, $scope.aOrderLists);
    };
}]);

//未付款订单
myApp.controller('orderUnpaidController', ['$scope', '$filter', 'orderService', 'orderSearchByDateService', function ($scope, $filter, orderService, orderSearchByDateService) {
    $scope.getUnpaidLists = function () {
        orderService.getOrdersByState(1, 1)
            .then(function (data) {
                $scope.aUnpaidLists = data.data.data;
            }, function (error) {
                console.log('orderUnpaid error' + error);
            });
        angular.element('.datePicker-btn').daterangepicker(null, function (start, end) {
            var oStartDate = new Date(start._d);
            var oEndDate = new Date(end._d);
            $scope.fromDate = $filter('date')(oStartDate, 'yyyy-MM-dd');
            $scope.toDate = $filter('date')(oEndDate, 'yyyy-MM-dd');
        });
    };
    $scope.searchByDate = function () {
        orderSearchByDateService.orderSearchByDate($scope.fromDate, $scope.toDate, 1, $scope.aUnpaidLists);
    };
}]);

//已付款订单
myApp.controller('orderPaidController', ['$scope', '$filter', 'httpService', 'orderService', 'orderSearchByDateService', function ($scope, $filter, httpService, orderService, orderSearchByDateService) {
    $scope.getPaidLists = function () {
        orderService.getOrdersByState(2, 1)
            .then(function (data) {
                $scope.aPaidLists = data.data.data;
            }, function (error) {
                console.log('orderUnpaid error' + error);
            });
        angular.element('.datePicker-btn').daterangepicker(null, function (start, end) {
            var oStartDate = new Date(start._d);
            var oEndDate = new Date(end._d);
            $scope.fromDate = $filter('date')(oStartDate, 'yyyy-MM-dd');
            $scope.toDate = $filter('date')(oEndDate, 'yyyy-MM-dd');
        });
    };
    $scope.deliverGoods = function (id) {
        httpService.getRequest('order/updateOrders.do', {params: {id: id, states: 3}})
            .then(function (data) {
                if (1 == data.code) {
                    alert(data.data);
                    $scope.getPaidLists();
                }
            }, function (error) {
                console.log('order/updateOrders.do error:' + error);
            })
    };
    $scope.searchByDate = function () {
        orderSearchByDateService.orderSearchByDate($scope.fromDate, $scope.toDate, 2, $scope.aPaidLists);
    };
}]);

//已发货订单
myApp.controller('orderDeliverController', ['$scope', '$filter', 'orderService', 'orderSearchByDateService', function ($scope, $filter, orderService, orderSearchByDateService) {
    $scope.getDeliverLists = function () {
        orderService.getOrdersByState(3, 1)
            .then(function (data) {
                $scope.aDeliverLists = data.data.data;
            }, function (error) {
                console.log('orderUnpaid error' + error);
            });
        angular.element('.datePicker-btn').daterangepicker(null, function (start, end) {
            var oStartDate = new Date(start._d);
            var oEndDate = new Date(end._d);
            $scope.fromDate = $filter('date')(oStartDate, 'yyyy-MM-dd');
            $scope.toDate = $filter('date')(oEndDate, 'yyyy-MM-dd');
        });
    };
    $scope.searchByDate = function () {
        orderSearchByDateService.orderSearchByDate($scope.fromDate, $scope.toDate, 3, $scope.aDeliverLists);
    };
}]);

//已完成订单
myApp.controller('orderCompleteController', ['$scope', '$filter', 'orderService', 'orderSearchByDateService', function ($scope, $filter, orderService, orderSearchByDateService) {
    $scope.getCompleteLists = function () {
        orderService.getOrdersByState(4, 1)
            .then(function (data) {
                $scope.aCompleteLists = data.data.data;
            }, function (error) {
                console.log('orderUnpaid error' + error);
            });
        angular.element('.datePicker-btn').daterangepicker(null, function (start, end) {
            var oStartDate = new Date(start._d);
            var oEndDate = new Date(end._d);
            $scope.fromDate = $filter('date')(oStartDate, 'yyyy-MM-dd');
            $scope.toDate = $filter('date')(oEndDate, 'yyyy-MM-dd');
        });
    };
    $scope.searchByDate = function () {
        orderSearchByDateService.orderSearchByDate($scope.fromDate, $scope.toDate, 4, $scope.aCompleteLists);
    };
}]);

//订单详情
myApp.controller('orderDetailController', ['$scope', '$stateParams', 'httpService', function ($scope, $stateParams, httpService) {
    $scope.getDetail = function () {
        httpService.getRequest('order/getOrderDetail.do', {params: {id: $stateParams.id}})
            .then(function (data) {
                if (1 == data.code) {
                    $scope.oDetail = data.data;
                }
            }, function (error) {
                console.log('order/getOrderDetail.do error:' + error);
            })
    };
}]);

// activity
// 特定活动
myApp.controller('activitySpecifyController', ['$scope', '$state', 'httpService', function ($scope, $state, httpService) {
    $scope.getSpecials = function () {
        httpService.getRequest('special/getSpecial.do', {
            params: {
                pageNo: 1
            }
        }).then(function (data) {
            if (1 == data.code) {
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
            if (1 == data.code) {
                $scope.getSpecials();
            }
        }, function (error) {
            console.log('updateSpecial error' + error);
        });
    };
}]);

// 查看活动商品
myApp.controller('getSpecialCommoditiesController', ['$scope', '$stateParams', '$filter', 'httpService', 'getCategoryArrByIdFactory',
    function ($scope, $stateParams, $filter, httpService, getCategoryArrByIdFactory) {

        $scope.getSpecialCommodities = function () {
            httpService.getRequest('speCommodity/getSpecialCommodities.do',
                {
                    params: {
                        id: $stateParams.aSpecialsID,
                        pageNo: 1
                    }
                }).then(
                function (data) {
                    if (1 == data.code) {
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
            $scope.secondSelected = '';
            $scope.thirdSelected = [];
            $scope.commodities = [];
            $scope.commodityNo = '';
            httpService.getRequest('sort/getCategories.do')
                .then(function (data) {
                        if (1 == data.code) {
                            $scope.firstCategories = $filter('filter')(data.data, {'type': 1});
                            $scope.secondTypeCategories = $filter('filter')(data.data, {'type': 2});
                            $scope.thirdTypeCategories = $filter('filter')(data.data, {'type': 3});
                        }
                    },
                    function (error) {
                        console.log('getSpecialCommoditiesController error' + error);
                    });
        };

        $scope.changeSecondCat = function () {
            $scope.secondCategories = getCategoryArrByIdFactory.getCategoryArrById($scope.firstSelected.id, $scope.secondTypeCategories);
            $scope.thirdCategories = [];
            $scope.commodities = [];
            $scope.commodityNo = '';
        };

        $scope.changeThirdCat = function () {
            $scope.thirdCategories = getCategoryArrByIdFactory.getCategoryArrById($scope.secondSelected.id, $scope.thirdTypeCategories);
            $scope.commodities = [];
            $scope.commodityNo = '';
        };

        $scope.getGoodsName = function () {
            $scope.commodityNo = '';
            httpService.getRequest('commodity/getCommodityByCategoryId.do', {params: {categoryId: $scope.thirdSelected.id}})
                .then(function (data) {
                        if (1 == data.code) {
                            $scope.commodities = data.data;
                        }
                    },
                    function (error) {
                        console.log('getSpecialCommoditiesController error' + error);
                    });
        };

        $scope.getGoodsId = function () {
            $scope.commodityNo = ($filter('filter')($scope.commodities, {'id': $scope.goodSelected.id}))[0].commodityNo;
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
                    if (1 == data.code) {
                        $scope.getSpecialCommodities();
                    }
                }, function (error) {
                    console.log('confirmDelete error' + error);
                })
        };
    }]);