angular.module('index', [ 'ngRoute' ]).config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		controller : 'welcome',
		controllerAs: 'controller'
	}).otherwise('/', {
		controller : 'welcome',
		controllerAs: 'controller'
	});

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).controller('welcome', function($rootScope) {

	// 初始化报表
	var handleInit = function() {
		// 基于准备好的dom，初始化echarts实例
        var testChart = echarts.init(document.getElementById('doughnut'));
        // 指定图表的配置项和数据
        option = {
        	    series: [/*{ //小表盘日
        	        type: 'gauge',
        	        center: ['50%', '68%'], //实际实用时应根据id="main"的div的大小进行调整
        	        radius: '50px', //仪表盘半径，实际实用时应根据id="main"的div的大小进行调整
        	        animation: 0,
        	        pointer: {
        	            width: 0
        	        },
        	        axisLine: {
        	            lineStyle: {
        	                show: 0,
        	                width: 0
        	            }
        	        },
        	        splitLine: {
        	            show: 0
        	        },
        	        axisTick: {
        	            show: 0
        	        },
        	        axisLabel: {
        	            show: 0
        	        },
        	        detail: {
        	            show: 1,
        	            formatter: function(e) {
        	                if (e < 10) return '0' + e;
        	            },
        	            offsetCenter: ['260%', -113],
        	            borderWidth: 2,
        	            borderColor: '#bfbbb8',
        	            backgroundColor: 'black',
        	            height: 24,
        	            width: 32,
        	            textStyle: {
        	                color: 'white',
        	                fontSize: 16,
        	                fontWeight: 'bold',
        	                fontFamily: 'Arial'
        	            },
        	        },
        	        data: [{}]
        	    }, */{ //大表盘时针
        	        name: 'clock',
        	        type: 'gauge',
        	        center: ['50%', '50%'], //实际实用时应根据id="main"的div的大小进行调整
        	        radius: '200px', //仪表盘半径，实际实用时应根据id="main"的div的大小进行调整
        	        min: 0,
        	        max: 12,
        	        startAngle: 90,
        	        endAngle: -269.9999,
        	        splitNumber: 12,
        	        animation: 0,
        	        pointer: {
        	            length: '70%',
        	            width: 6
        	        },
        	        itemStyle: {
        	            normal: {
        	                shadowColor: 'rgba(0, 0, 0, 0.5)',
        	                shadowBlur: 10,
        	                shadowOffsetX: 2,
        	                shadowOffsetY: 2
        	            }
        	        },
        	        axisLine: {
        	            show: 0,
        	            lineStyle: {
        	                color: [
        	                    [1, '#bfbbb8']
        	                ],
        	                width: 10,
        	                shadowColor: 'rgba(0, 0, 0, 0.5)',
        	                shadowBlur: 10,
        	                shadowOffsetX: 2,
        	                shadowOffsetY: 2
        	            }
        	        },
        	        splitLine: {
        	            length: 10,
        	            lineStyle: {
        	                width: 2
        	            }
        	        },
        	        axisTick: {
        	            show: true,
        	            splitNumber: 5,
        	            length: 5,
        	            lineStyle: {
        	                color: ['#ffffff']
        	            }
        	        },
        	        axisLabel: {
        	            show: 0
        	        },
        	        title: {
        	            show: 0
        	        },
        	        detail: {
        	            show: 0
        	        },
        	        data: [{}]
        	    }, { //大表盘分针
        	        name: 'clock',
        	        type: 'gauge',
        	        center: ['50%', '50%'], //实际实用时应根据id="main"的div的大小进行调整
        	        radius: '200px', //仪表盘半径，实际实用时应根据id="main"的div的大小进行调整
        	        min: 0,
        	        max: 12,
        	        startAngle: 90,
        	        endAngle: -269.9999,
        	        splitNumber: 12,
        	        animation: 0,
        	        pointer: {
        	            length: '80%',
        	            width: 6
        	        },
        	        itemStyle: {
        	            normal: {
        	                color: '#bfbbb8',
        	                shadowColor: 'rgba(0, 0, 0, 0.5)',
        	                shadowBlur: 10,
        	                shadowOffsetX: 2,
        	                shadowOffsetY: 2
        	            }
        	        },
        	        axisLine: {
        	            show: 0,
        	            lineStyle: {
        	                color: [
        	                    [0.5, '#bfbbb8'],
        	                    [1, '#337ab7']
        	                ],
        	                width: 10,
        	                shadowColor: 'rgba(0, 0, 0, 0.5)',
        	                shadowBlur: 10,
        	                shadowOffsetX: 2,
        	                shadowOffsetY: 2
        	            }
        	        },
        	        splitLine: {
        	            length: 10,
        	            lineStyle: {
        	                width: 2
        	            }
        	        },
        	        axisTick: {
        	            show: true,
        	            splitNumber: 5,
        	            length: 5,
        	            lineStyle: {
        	                color: ['#ffffff']
        	            }
        	        },
        	        axisLabel: {
        	            show: 0
        	        },
        	        title: {
        	            show: 0
        	        },
        	        detail: {
        	            show: 0
        	        },
        	        data: [{}]
        	    }, { ////大表盘秒针
        	        name: 'clock',
        	        type: 'gauge',
        	        center: ['50%', '50%'], //实际实用时应根据id="main"的div的大小进行调整
        	        radius: '200px', //仪表盘半径，实际实用时应根据id="main"的div的大小进行调整
        	        min: 0,
        	        max: 60,
        	        startAngle: 90,
        	        endAngle: -269.9999,
        	        splitNumber: 12,
        	        animation: 0,
        	        pointer: { //仪表盘指针
        	            show: true,
        	            length: '90%',
        	            width: 3
        	        },
        	        itemStyle: { //仪表盘指针样式
        	            normal: {
        	                color: '#bfbbb8',
        	                shadowColor: 'rgba(0, 0, 0, 0.5)',
        	                shadowBlur: 10,
        	                shadowOffsetX: 2,
        	                shadowOffsetY: 2
        	            }
        	        },
        	        axisLine: { //仪表盘轴线样式 
        	            lineStyle: {
        	                color: [
        	                    [0.5, 'black'],
        	                    [1, 'black']
        	                ],
        	                width: 10
        	            }
        	        },
        	        splitLine: { //分割线样式 
        	            length: 10,
        	            lineStyle: {
        	                width: 2
        	            }
        	        },
        	        axisTick: { //仪表盘刻度样式
        	            show: true,
        	            splitNumber: 5, //分隔线之间分割的刻度数
        	            length: 5, //刻度线长
        	            lineStyle: {
        	                color: ['#ffffff']
        	            }
        	        },
        	        axisLabel: { //刻度标签
        	            show: 1,
        	            distance: 6, //标签与刻度线的距离
        	            textStyle: {
        	                fontWeight: 'bold',
        	                fontSize: 16
        	            },
        	            formatter: function(t) {
        	                switch (t + '') {
        	                    case '5':
        	                        return '1';
        	                    case '10':
        	                        return '2';
        	                    case '15':
        	                        return '3';
        	                    case '20':
        	                        return '4';
        	                    case '25':
        	                        return '5';
        	                    case '30':
        	                        return '6';
        	                    case '35':
        	                        return '7';
        	                    case '40':
        	                        return '8';
        	                    case '45':
        	                        return '9';
        	                    case '50':
        	                        return '10';
        	                    case '55':
        	                        return '11';
        	                    case '60':
        	                        return '12';
        	                }
        	            }
        	        },
        	        title: { //仪表盘标题
        	            show: 0
        	        },
        	        detail: { //仪表盘显示数据
        	            show: 0
        	        },
        	        data: [{}]
        	    }]
        	};
        	testChart.setOption(option);
        	clearInterval(timeTicket);
        	var timeTicket = setInterval(function() {
        	    datetime = new Date();
        	    year = datetime.getFullYear();
        	    month = datetime.getMonth() + 1;
        	    date = datetime.getDate();
        	    h = datetime.getHours();
        	    m = datetime.getMinutes();
        	    s = datetime.getSeconds();
        	    week = datetime.getDay();
        	    ms = datetime.getMilliseconds();
        	    minutes = m + s / 60;
        	    hours_24 = h + m / 60;
        	    if (hours_24 > 12)
        	        hours_12 = hours_24 - 12;
        	    else
        	        hours_12 = hours_24;
        	    seconds = s + ms / 1000;
        	    //option.series[0].data[0].value = (date).toFixed(0);
        	    option.series[0].data[0].value = (hours_12).toFixed(2);
        	    option.series[1].data[0].value = (minutes / 5).toFixed(2);
        	    option.series[2].data[0].value = (seconds).toFixed(2);
        	    testChart.setOption(option, true);
        	}, 100);
		// 使用刚指定的配置项和数据显示图表。
        testChart.setOption(option);
	}

	handleInit();

});
