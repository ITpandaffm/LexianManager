/**
 * Created by 冯富铭 on 2017/7/6.
 */
myApp.filter('pieChartFilter', function () {
    return function (input) {
        let arr = [];
        input.map(function (item) {
            arr.push({
                value: item.count,
                name: item.commodity.name
            });
        });
        return arr;
    }
});
myApp.filter('lineChartNameFilter', function () {
    return function (input) {
        let arr = [];
        input.map( (item) => {
            arr.push(item.commodity.name);
        });
        return arr;
    }
});
myApp.filter('lineChartCountFilter', function () {
    return function (input) {
        let arr = [];
        input.map((item)=>{
            arr.push(item.count);
        });
        return arr;
    }
});