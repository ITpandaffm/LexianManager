myApp.directive('pagination', function($timeout) {
    return {
        restrict: 'E',
        templateUrl: 'views/components/pagination.html',
        replace: true,
        scope: {
            pageSize: '=',
            pageNo: '=',
            totalSize: '@',
            pageNums: '@',
            startIndex: '@',
            endIndex: '@',
            refreshMethod: '&'
        },
        controller: function($scope) {
            $scope.prevPage = function() {
                if ($scope.pageNo - 1 > 0) {
                    $scope.pageNo--;
                    $scope.refreshMethod();
                } else {
                    alert('已经是第一页了！');
                }
            };
            $scope.nextPage = function() {
                if ($scope.pageNo + 1 <= $scope.pageNums) {
                    $scope.pageNo++;
                    $scope.refreshMethod();
                } else {
                    alert('已经是最后一页了！');
                }
            };
        }
    };
});

myApp.directive('confirmdeletemodal', function() {
    return {
        restrict: 'E',
        templateUrl: 'views/components/deleteModal.html',
        replace: true
    };
});

myApp.directive('changemodulenamemodal', function() {
    return {
        restrict: 'E',
        templateUrl: 'views/components/changeModuleNameModal.html',
        replace: true
    };
});

myApp.directive('addspecialmodal', function() {
    return {
        restrict: 'E',
        templateUrl: 'views/components/addSpecialModal.html',
        replace: true
    };
});

myApp.directive('fileModel', ['$parse', function($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function() {
                scope.$apply(function() {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

myApp.directive('ckeditor', function() {
    return {
        require: '?ngModel',
        link: function(scope, element, attrs, ngModel) {
            var ckeditor = CKEDITOR.replace(element[0], {

            });
            if (!ngModel) {
                return;
            }
            ckeditor.on('instanceReady', function() {
                ckeditor.setData(ngModel.$viewValue);
            });
            ckeditor.on('pasteState', function() {
                scope.$apply(function() {
                    ngModel.$setViewValue(ckeditor.getData());
                });
            });
            ngModel.$render = function(value) {
                ckeditor.setData(ngModel.$viewValue);
            };
        }
    };
});