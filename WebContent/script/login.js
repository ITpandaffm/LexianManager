var myApp = angular.module('singInApp', [])
    .controller('signController', ['$scope', '$http', function ($scope, $http) {
        $scope.signIn = function () {
            $http.get('manager/signIn.do', {params: {name: $scope.username, password: $scope.pwd}})
                .success(function (data, status) {
                    if (data.code == 1 && status == 200) {
                        alert('登录成功');
                        // sessionStorage.setItem('username', data.data.name);
                        window.location.pathname += 'home.html';
                    } else {
                        alert(data.data);
                        $scope.pwd = '';
                    }
                })
                .error(function (data, status) {
                    alert('登录失败：status为：' + status + '，返回data为：' + data);
                });
        };
        $scope.clickEnter = function ($events) {
            if($events.keyCode == 13){
                $scope.signIn();
            }
        }
    }]);