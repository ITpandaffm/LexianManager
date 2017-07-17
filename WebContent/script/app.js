var myApp = angular.module('lx-app',['ui.router'])

.config(function($stateProvider, $urlRouterProvider){

    $urlRouterProvider.otherwise('welcome');

    $stateProvider
        .state('welcome', {
            url: '/welcome',
            templateUrl: 'views/welcome.html'
        })
        .state('updatePwd', {
            url: '/updatePwd',
            templateUrl: 'views/updatePwd.html'
        })

        //权限管理模块
        .state('authority/queryauthority', {
            url: '/authority/queryauthority',
            templateUrl: 'views/authority/authority_queryauthority.html'
        })
        .state('authority/querymenu',{
            url: '/authority/querymenu',
            templateUrl: 'views/authority/authority_querymenu.html'
        })
        .state('authority/queryusers',{
            url: '/authority/queryusers',
            templateUrl: 'views/authority/authority_queryusers.html'
        })
        .state('authority/queryrole',{
            url: '/authority/queryrole',
            templateUrl: 'views/authority/authority_queryrole.html'
        })
        //权限管理模块的二级路由
        .state('authority/role/addRole', {
            url: '/authority/role/addRole',
            templateUrl: 'views/authority/level2/addRole.html',
            controller: 'addRoleController'
        })
        .state('authority/role/editRole', {
            url: '/authority/role/editRole/:id/:name/:description',
            templateUrl: 'views/authority/level2/editRole.html',
            controller: 'editRoleController'
        })
        .state('authority/role/editRoleMenu', {
            url: '/authority/role/editRoleMenu/:id/:name',
            templateUrl: 'views/authority/level2/editRoleMenu.html',
            controller: 'editRoleMenuController'
        })
        .state('authority/managers/addManager', {
            url: '/authority/managers/addManager',
            templateUrl: 'views/authority/level2/addManager.html',
            controller: 'addManagerController'
        })
        .state('authority/managers/editManager', {
            url: '/authority/managers/editManager/:id/:name/:info',
            templateUrl: 'views/authority/level2/editManager.html',
            controller: 'editManagerController'
        })
        .state('authority/managers/associateRole', {
            url: '/authority/managers/associateRole/:id/:name',
            templateUrl: 'views/authority/level2/associateRole.html',
            controller: 'associateRoleController'
        })
        //会员模块
        .state('vip/queryvip',{
            url: '/vip/queryvip',
            templateUrl: 'views/vip/vip_queryvip.html'
        })
        //商品管理模块
        .state('goods/category',{
            url: '/goods/category',
            templateUrl: 'views/goods/goods_category.html'
        })
        .state('goods/info',{
            url: '/goods/info',
            templateUrl: 'views/goods/goods_info.html'
        })
        //门店模块
        .state('store/info',{
            url: '/store/info',
            templateUrl: 'views/store/store_info.html'
        }) 
        .state('store/goods',{
            url: '/store/goods',
            templateUrl: 'views/store/store_goods.html'
        })
        //订单模块
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
        .state('order/detail', {
            url: '/order/detail/:id',
            templateUrl: 'views/order/level2/order_detail.html',
            controller: 'orderDetailController'
        })
        //板块管理
        .state('activity/specify',{
            url: '/activity/specify',
            templateUrl: 'views/activity/activity_specify.html'
        })
        //查看活动商品
        .state('activity/special/getSpecialCommodities', {
            url: '/activity/special/getSpecialCommodities/:aSpecialsID',
            templateUrl: 'views/activity/level2/getSpecialCommodities.html',
            controller: 'getSpecialCommoditiesController'
        });

});

