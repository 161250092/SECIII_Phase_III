var type1="ImageLabel";
var type2="AreaLabel";
var type3="FrameLabel";

var Type1Num=0;
var Type2Num=0;
var Type3Num=0;

var finishedType1=0;
var finishedType2=0;
var finishedType3=0;

new Vue({
    el:"#requestorInfo",
    data:{
        userId:"",
        username:"无",
        prestige:0,
        cash:0,
        email:"无",
        phone:"无",
        AllFinishedTasks:[],
        AllUnfinishedTasks:[],
        maxPublishedTaskNum:0,
        chargeCash:0

},
    mounted: function () {
        const _this = this;
        this.$nextTick(function () {
                _this.userId = getUserId();
                axios.all([this.getUserInfo(),this.getAssignedButIncompleteTaskList(),this.getAssignedAndAccomplishedTaskList()])
                    .then(axios.spread(function (userInfo, incompleteTaskList, accomplishedTaskList){
                        /* getUserInfo */
                        _this.username = userInfo.data.username.value;
                        _this.prestige = userInfo.data.prestige.value;
                        _this.cash = userInfo.data.cash.value;
                        _this.cash =_this.cash.toFixed(2);
                        _this.email = userInfo.data.email.address;
                        _this.phone = userInfo.data.phone.number;
                        _this.maxPublishedTaskNum = userInfo.data.taskNum.value;
                        /* getAcceptedButIncompleteTaskList */
                        _this.AllUnfinishedTasks = incompleteTaskList.data;

                        /* getAcceptedAndAccomplishedTaskList */
                        _this.AllFinishedTasks = accomplishedTaskList.data;

                        /* 显示图表 */
                        _this.countLabel();
                        _this.TaskNumcharts();
                        _this.unfinishedTaskCharts(Type1Num,Type2Num,Type3Num);
                        _this.finishedTaskCharts(finishedType1,finishedType2,finishedType3);
            }));
        })
    },
    methods: {
        getUserInfo:function(){
            return axios.get("/user/getUserInfo", { params:{ userId: this.userId } });
        },
        getAssignedButIncompleteTaskList:function(){
           return axios.get("/requestor/getAssignedButIncompleteTaskList", {params:{ userId: this.userId }});
        },
        getAssignedAndAccomplishedTaskList:function(){
            return axios.get("/requestor/getAssignedAndAccomplishedTaskList", {params:{userId:this.userId}});
        },

        editEmail: function () {
            const _this = this;
            axios.get("/user/reviseUserEmail", {params: {userId:getUserId(),email:_this.email}})
                .then(function (Exception) {
                    let message = Exception.data.wrongMessage.type;
                    if(message==="Success")
                        alert("修改成功");
                    else
                        alert("修改失败")
                });
        },
        editPhone: function () {
            const _this = this;
            axios.get("/user/reviseUserPhone", {params: {userId:getUserId(),phone:_this.phone}})
                .then(function (Exception) {
                    let message = Exception.data.wrongMessage.type;
                    if(message==="Success")
                        alert("修改成功");
                    else
                        alert("修改失败")
                });
        },

        charge: function () {
            const _this = this;
            axios.get("/requestor/charge", {params: {userId:getUserId(),cash:_this.chargeCash}})
                .then(function (Exception) {
                    let message = Exception.data;
                    if(message===true)
                        alert("充值成功");
                    else
                        alert("充值失败")
                });


        },

        countLabel: function () {
            const _this = this;
            for (var i = 0; i < _this.AllUnfinishedTasks.length; i++) {
                if (_this.AllUnfinishedTasks[i].labelType === type1)
                    Type1Num++;

                else if (_this.AllUnfinishedTasks[i].labelType === type2)
                    Type2Num++;

                else if (_this.AllUnfinishedTasks[i].labelType === type3)
                    Type3Num++;

            }

            for (var i = 0; i < _this.AllFinishedTasks.length; i++) {
                if (_this.AllFinishedTasks[i].labelType === type1)
                    finishedType1++;

                else if (_this.AllFinishedTasks[i].labelType === type2)
                    finishedType2++;

                else if (_this.AllFinishedTasks[i].labelType === type3)
                    finishedType3++;
            }

        },

        TaskNumcharts:function(){
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

                    data:['发布任务数','剩余可发布数']
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
                            {value:this.AllUnfinishedTasks.length, name:'已发布'},
                            {value:this.maxPublishedTaskNum-this.AllUnfinishedTasks.length, name:'剩余'}

                        ]
                    }
                ]
            };

            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        },

        unfinishedTaskCharts:function(a,b,c){
            var dom = document.getElementById("TaskType");
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
                    data:['整体标注','区域标注',"方框标注"]
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
                                    fontSize: '12',
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
                            {value:a, name:'整体标注'},
                            {value:b, name:'区域标注'},
                            {value:c, name:'方框标注'}

                        ]
                    }
                ]
            };

            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        },

        finishedTaskCharts:function(a,b,c){
            var dom = document.getElementById("FinishedLayout");
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
                    data:['整体标注','区域标注',"方框标注"]
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
                                    fontSize: '12',
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
                            {value:a, name:'整体标注'},
                            {value:b, name:'区域标注'},
                            {value:c, name:'方框标注'}

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

new Vue({
    el: "#home",
    data: {
        username: ""
    },
    mounted: function () {
        this.$nextTick(function () {
            this.username = getUsername();
        });
    }
});