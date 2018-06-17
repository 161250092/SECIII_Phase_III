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
        exchangedCash:0,




        startDate:"2018-06-12",
        endDate:"2018-06-24",

        publishTask:[],
        FinishedTask:[],
        charge:0,
        exchange:0
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

                _this.chargedCash = response.data.chargedCash;
                _this.exchangedCash = response.data.exchangedCash;
                _this.taskCharts(_this.numOfAccomplishedTask, _this.numOfIncompleteTasks,"container");
                _this.cashCharts(_this.chargeCash, _this.exchangedCash,"inAndout");
                _this.userCharts( _this.numOfRequestors,_this.numOfWorkers,"user");
                _this.flowCharts(['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],[820, 932, 901, 934, 1290, 1330, 1320],"flow");
                _this.flowCharts(['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],[820, 932, 901, 934, 1290, 1330, 1320],"flow1");
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
        },

        flowCharts:function(x,y,container){
            var dom = document.getElementById(container);
            var myChart = echarts.init(dom);
            var app = {};
            option = null;
            option = {
                xAxis: {
                    type: 'category',
                    data: x
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: y,
                    type: 'line'
                }]
            };
            ;
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }

        },


        checkFlow:function(){


           const _this = this;
            var xList1=[];
            var xList2=[];

            if(_this.compareDate(_this.startDate,_this.endDate)===false) {
                return;
            }
            axios.get("/admin/getWebsiteTrafficStatics",{params:{startDate:_this.startDate,endDate:_this.endDate}})
                .then(function (Statistics){
                _this.publishTask = Statistics.data.numOfPublishedTask;
                _this.FinishedTask = Statistics.data.numOfSubmittedAcceptedTask;

                _this.charge  = Statistics.data.chargedCash;
                _this.exchange = Statistics.data.exchangedCash;

                console.log(_this.publishTask);
                    xList1[0]=_this.startDate;
                    for(var i=1;i<_this.publishTask.length-1;i++)
                        xList1[i] = "~";
                    xList1[_this.publishTask.length-1] = _this.endDate;

                    xList2[0]=_this.startDate;
                    for(var i=1;i<_this.FinishedTask.length-1;i++)
                        xList2[i] = "~";
                    xList2[_this.FinishedTask.length-1] = _this.endDate;
                    console.log(xList1);


                    _this.flowCharts(xList1, _this.publishTask,"flow");

                    _this.flowCharts(xList2, _this.FinishedTask,"flow1");

                })

        },


        compareDate:function(startDate, endDate) {
            var arrStart = startDate.split("-");
            var startTime = new Date(arrStart[0], arrStart[1], arrStart[2]);
            var startTimes = startTime.getTime();
            var arrEnd = endDate.split("-");
            var endTime = new Date(arrEnd[0], arrEnd[1], arrEnd[2]);
            var endTimes = endTime.getTime();
            if (endTimes<startTimes) {
                alert("结束时间不能小于开始时间");
                return false;
            }
            return true;
}



    }

});


