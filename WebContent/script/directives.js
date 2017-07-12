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
        templateUrl: 'views/components/deleteModal.html',
        replace: true
    };
});