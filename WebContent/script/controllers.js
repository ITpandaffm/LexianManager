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
                $scope.aPrivileges = data.data;
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
                $scope.aManagers = data.data;
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