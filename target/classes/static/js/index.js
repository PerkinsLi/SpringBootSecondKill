(function(){
    var app = window.us = angular.module("secondKill", ['ui.router', 'ngCookies']);

    // 初始化一些$rootScope全局变量
  app.run(function($rootScope) {
    // 存储members信息
    $rootScope.goodsList = [];
    $rootScope.listPageParam = {"searchText":"","sort":"", "pageSize":"", "currentPage":"", "goods":""};
    $rootScope.user = {};
    $rootScope.pagination = {};
    $rootScope.wcome = "Hi, 请 ";
    $rootScope.name = "登录";
});

    // 路由
    app.config(function ($stateProvider, $urlRouterProvider){
        var loginState = {
            name: 'login',
            url: '/login',
            templateUrl: '../html/login.html',
            controller: 'login'
          };

        var goodsListState = {
            name: 'goodsList',
            url: '/goodsList',
            templateUrl: '../html/Goods_List.html',
            controller: 'goodsList'
          };

        $stateProvider.state(loginState);
        $stateProvider.state(goodsListState);
      
        $urlRouterProvider.otherwise('/login');
    });

    // loginController
    app.controller("login", function ($rootScope, $scope, $http, $state){
        $scope.login = function () {
             $http({
               method: "POST",
               url: "http://localhost:8080/login/do_login",
               data: { mobile: $scope.mobile, password: $scope.password }
             }).then(function (resp) {
                 var data = resp.data;
               if (data.code === 0) {
                 $state.go("goodsList");
               } else {
                 layer.open({
                   content: data.msg,
                   time: 2000,
                   offset:'400px'
                 });
               }
             });
            $state.go("goodsList");
          }
    });

    // goodsListController
    app.controller("goodsList", function ($rootScope, $scope, $http, $cookies, $location, $state, $stateParams){

       loadInformation(); 

            function loadInformation() {
                $http({
                    method: "GET",
                    url: "http://localhost:8080/goods/to_list",
                    data: { 
                        searchText: $rootScope.listPageParam.searchText,
                        sort: $rootScope.listPageParam.sort,
                        pageSize: $rootScope.listPageParam.pageSize,
                        currentPage: $rootScope.listPageParam.currentPage,
                        goods: $rootScope.listPageParam.goods
                    }
                  }).then(function (resp) {
                      var data = resp.data;
                    if (data != null) {
                      $rootScope.user = data.user;
                      $rootScope.goodsList = data.goodsList;
                      $rootScope.pagination = data.page;
                    } else {
                      layer.open({
                        content: "没有加载到商品信息！",
                        time: 2000,
                        offset:'400px'
                      });
                    }
                  });
            };

    });

})();