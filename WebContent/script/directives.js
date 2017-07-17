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