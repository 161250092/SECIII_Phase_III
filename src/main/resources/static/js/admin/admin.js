//miao add
var a=0;
var b=0;

new Vue({
    el:"#statisticsTable",
    data:{
        numOfUsers: 0,
        numOfTasks: 0,
        numOfIncompleteTasks: 0,
        numOfAccomplishedTask: 0
    },
    mounted:function (ev) {
        this.$nextTick(function () {
            this.getWebsiteStatistics();
        })
    },
    methods:{
        getWebsiteStatistics:function () {
            var _this = this;
            axios.get("/getWebsiteStatistics").then(function (response) {
                _this.numOfUsers = response.data.numOfUsers;
                _this.numOfTasks = response.data.numOfTasks;
                _this.numOfIncompleteTasks = response.data.numOfIncompleteTasks;
                _this.numOfAccomplishedTask = response.data.numOfAccomplishedTask;

               //miao add
                a=_this.numOfIncompleteTasks;
                b= _this.numOfAccomplishedTask;
                var dom = document.getElementById("container");
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
                        // data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
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

                //end
            })
        }
    }


});


