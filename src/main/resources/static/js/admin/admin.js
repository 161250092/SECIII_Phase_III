//miao add
var a=0;
var b=0;

new Vue({
    el:"#statisticsTable",
    data:{
        numOfUsers: 0,
        numOfTasks: 0,
        numOfIncompleteTasks: 0,
        numOfAccomplishedTask: 0,
        numOfRequestors:0,
        numOfWorkers:0,
        chargedCash:0,
        exchangedCash:0
    },
    mounted:function (ev) {
        this.$nextTick(function () {
            this.getWebsiteStatistics();
        })
    },
    methods: {
        getWebsiteStatistics: function () {
            var _this = this;
            axios.get("/admin/getWebsiteStatistics").then(function (response) {
                _this.numOfRequestors = response.data.numOfRequestors;
                _this.numOfWorkers = response.data.numOfWorkers;
                _this.numOfUsers = _this.numOfRequestors + _this.numOfWorkers;

                _this.numOfTasks = response.data.numOfTasks;

                _this.numOfIncompleteTasks = response.data.numOfIncompleteTasks;
                _this.numOfAccomplishedTask = response.data.numOfAccomplishedTask;


                _this.chargeCash = response.data.chargeCash;
                _this.exchangedCash = response.data.exchangedCash;
                _this.taskCharts(_this.numOfAccomplishedTask, _this.numOfIncompleteTasks,"container");
                _this.cashCharts(_this.chargeCash, _this.exchangedCash,"inAndout");
                _this.userCharts( _this.numOfRequestors,_this.numOfWorkers,"user");
            })


        },


        taskCharts:function(a,b,container){
                    var dom = document.getElementById(container);
                    var myChart = echarts.init(dom);
                    var app = {};
                    option = null;
                    app.title = '环形图';

                    option = {
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b}: {c} ({d}%)"
                        },
                        legend: {
                            orient: 'vertical',
                            x: 'left',
                            data:['完成','进行中']
                        },
                        series: [
                            {
                                name:'完成情况',
                                type:'pie',
                                radius: ['50%', '70%'],
                                avoidLabelOverlap: false,
                                label: {
                                    normal: {
                                        show: false,
                                        position: 'center'
                                    },
                                    emphasis: {
                                        show: true,
                                        textStyle: {
                                            fontSize: '15',
                                            fontWeight: 'bold'
                                        }
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data:[
                                    {value:a, name:'进行中'},
                                    {value:b, name:'完成'}

                                ]
                            }
                        ]
                    };

                    if (option && typeof option === "object") {
                        myChart.setOption(option, true);
                    }
        },

        cashCharts:function(a,b,container){
            var dom = document.getElementById(container);
            var myChart = echarts.init(dom);
            var app = {};
            option = null;
            app.title = '环形图';

            option = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data:['充值','收入']
                },
                series: [
                    {
                        name:'收支',
                        type:'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '15',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data:[
                            {value:a, name:'充值'},
                            {value:b, name:'兑换'}

                        ]
                    }
                ]
            };

            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        },

        userCharts:function(a,b,container){
            var dom = document.getElementById(container);
            var myChart = echarts.init(dom);
            var app = {};
            option = null;
            app.title = '环形图';

            option = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data:['发布者','工人']
                },
                series: [
                    {
                        name:'用户',
                        type:'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '15',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data:[
                            {value:a, name:'发布者'},
                            {value:b, name:'工人'}

                        ]
                    }
                ]
            };

            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        }


    }
});


