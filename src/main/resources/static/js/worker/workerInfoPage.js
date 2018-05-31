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
    el:"#workerInfo",
    data:{
        userId:"",
        username:"无",
        prestige:0,
        userLevel:"0",
        cash:0,
        email:"无",
        phone:"无",
        AllFinishedTask:[],
        AllUnfinishedTask:[],
        maxAcceptedTaskNum:0
    },
    mounted: function () {
        const _this = this;
        this.$nextTick(function () {
            _this.userId = getUserId();
            axios.get("/user/getUserInfo",
                { params:{ userId: this.userId } })
                .then(function (response) {
                    _this.userId = response.data.userId;
                    _this.username = response.data.username;
                    _this.prestige = response.data.prestige;
                    _this.userLevel = response.data.userLevel;
                    _this.cash = response.data.cash;
                    _this.email = response.data.email;
                    _this.phone = response.data.phone;
                    _this.maxAcceptedTaskNum = response.data.maxAcceptedTaskNum;
                })
            axios.get("/worker/getAcceptedButIncompleteTaskList",
                {params:{ userId: this.userId }})
                .then(function (response){
                    _this.AllUnfinishedTasks = response;
                })

            axios.get("/worker/getAcceptedAndAccomplishedTaskList",
                {params:{userId:this.userId}})
                .then(function (response){
                    _this.AllFinishedTasks = response;
                })

            countLabel();
            TaskNumcharts();
            unfinishedTaskCharts(Type1Num,Type2Num,Type3Num);
            finishedTaskCharts(finishedType1,finishedType2,finishedType3);
        })
    },
    methods: {
        editEmail: function () {
            const _this = this;
            axios.get("/user/reviseUserEmail", {params: {userId:getUserId(),email:_this.email}})
                .then(function (Exception) {
                    let message = Exception.data.WrongMessage.type;
                    if(message==="success")
                        alert("修改成功")
                    else
                        alert("修改失败")
                })
        },
        editPhone: function () {
            const _this = this;
            axios.get("/user/reviseUserPhone", {params: {userId:getUserId(),phone:_this.phone}})
                .then(function (Exception) {
                    let message = Exception.data.WrongMessage.type;
                    if(message==="success")
                        alert("修改成功")
                    else
                        alert("修改失败")
                })

        },
        exchange: function () {
            // const _this = this;
            // this.phone = "充钱是不可能充钱的，这辈子不可能充钱"
            alert("换钱是不可能换钱的，这辈子都不可能给你换钱")
        }
    },

    countLabel:function(){
        for(var i=0;i<AllUnfinishedTasks.length;i++){
            if(AllUnfinishedTasks[i].labelType===type1)
                Type1Num++;

            else if(AllUnfinishedTasks[i].labelType===type2)
                Type2Num++;

            else if(AllUnfinishedTasks[i].labelType===type3)
                Type3Num++;

        }

        for(var i=0;i<AllFinishedTasks.length;i++){
            if(AllFinishedTasks[i].labelType===type1)
                finishedType1++;

            else if(AllFinishedTasks[i].labelType===type2)
                finishedType2++;

            else if(AllFinishedTasks[i].labelType===type3)
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

                data:['接受的任务','可接受任务数']
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
                        {value:AllUnfinishedTasks.length, name:'已接受'},
                        {value:maxAcceptedTaskNum-AllUnfinishedTasks.length, name:'剩余'}

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




});



