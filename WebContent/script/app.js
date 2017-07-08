var myApp = angular.module('lx-app',['ui.router'])

.config(function($stateProvider, $urlRouterProvider){

    
    $urlRouterProvider.otherwise('welcome');

    $stateProvider
        .state('welcome', {
            url: '/welcome',
            templateUrl: 'views/welcome.html'
        })
        .state('authority/queryauthority',{
            url: '/authority/queryauthority',
            templateUrl: 'views/authority/authority_queryauthority.html'
        })
        .state('authority/querymenu',{
            url: '/authority/querymenu',
            templateUrl: 'views/authority/authority_querymenu.html'
        })
        .state('authority/queryrole',{
            url: '/authority/queryrole',
            templateUrl: 'views/authority/authority_queryrole.html'
        })
        .state('authority/queryusers',{
            url: '/authority/queryusers',
            templateUrl: 'views/authority/authority_queryusers.html'
        })
        .state('vip/queryvip',{
            url: '/vip/queryvip',
            templateUrl: 'views/vip/vip_queryvip.html'
        })
        .state('goods/category',{
            url: '/goods/category',
            templateUrl: 'views/goods/goods_category.html'
        })
        .state('goods/vipinfo',{
            url: '/goods/vipinfo',
            templateUrl: 'views/goods/goods_vipinfo.html'
        })
        .state('store/info',{
            url: '/store/info',
            templateUrl: 'views/store/store_info.html'
        }) 
        .state('store/goods',{
            url: '/store/goods',
            templateUrl: 'views/store/store_goods.html'
        })
        .state('order/list',{
            url: '/order/list',
            templateUrl: 'views/order/order_list.html'
        }) 
        .state('order/unpaid',{
            url: '/order/unpaid',
            templateUrl: 'views/order/order_unpaid.html'
        }) 
        .state('order/haspaid',{
            url: '/order/haspaid',
            templateUrl: 'views/order/order_haspaid.html'
        }) 
        .state('order/hasdeliver',{
            url: '/order/hasdeliver',
            templateUrl: 'views/order/order_hasdeliver.html'
        }) 
        .state('order/complete',{
            url: '/order/complete',
            templateUrl: 'views/order/order_complete.html'
        })
        .state('activity/specify',{
            url: '/activity/specify',
            templateUrl: 'views/activity/activity_specify.html'
        });
});

