myApp.directive('pagination', function () {
    return {
        restrict: 'E',
        templateUrl: 'views/components/pagination.html',
        replace: true
    };
});

myApp.directive('confirmdeletemodal', function () {
    return {
        restrict: 'E',
        // scope: {
        //     deleteId: '@'
        // },
        templateUrl: 'views/components/deleteModal.html',
        replace: true
    };
});

myApp.directive('changemodulenamemodal', function () {
    return {
        restrict: 'E',
        templateUrl: 'views/components/changeModuleNameModal.html',
        replace: true
    };
});

myApp.directive('addspecialmodal', function () {
    return {
        restrict: 'E',
        templateUrl: 'views/components/addSpecialModal.html',
        replace: true
    };
});

myApp.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

myApp.directive('ckeditor', function() {
    return {
        require : '?ngModel',
        link : function(scope, element, attrs, ngModel) {
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