new Vue({
    el:"#taskInfoContainer",
    data:{
        AllUnfinishedTasks:[],
        allUnfinishedMassTasks:[],

        taskDescription:"",
        massTaskDescription: "",
        reviseTaskId:"",
        reviseTaskPrice:0,
    },
    mounted: function () {
        const _this = this;
        this.$nextTick(function () {
            _this.userId = getUserId();
            _this.getAssignedButIncompleteTaskList();
        })
    },
    methods:{
        getAssignedButIncompleteTaskList: function () {
            const _this = this;
            axios.get("/requestor/getAssignedButIncompleteTaskList", { params:{ userId: getUserId() } })
                .then(function (response) {
                    _this.AllUnfinishedTasks = [];
                    _this.allUnfinishedMassTasks = [];
                    response.data.forEach(function (item, index) {
                        if (item.taskId.indexOf("MASS") === -1) {
                            _this.AllUnfinishedTasks.push(item);
                        }else {
                            _this.allUnfinishedMassTasks.push(item);
                        }
                    });
                });
        },
        getChineseLabelType: function (labelType) {
            if(labelType === IMAGE_LABEL_TYPENAME){
                return "整体标注";
            }else if(labelType === FRAME_LABEL_TYPENAME){
                return "标框标注";
            }else if(labelType === AREA_LABEL_TYPENAME){
                return "区域标注";
            }
        },
        detailedInfo:function(index){
            this.taskDescription = this.AllUnfinishedTasks[index].taskDescription;
        },
        massTaskDetailedInfo: function (index) {
            this.massTaskDescription = this.allUnfinishedMassTasks[index].taskDescription;
        },
        changeTaskPrice:function(index){
            this.reviseTaskId = this.AllUnfinishedTasks[index].taskId;
        },
        recallTask:function(index){
            this.terminateTask(this.AllUnfinishedTasks[index].taskId);
        },
        recallMassTask: function (index) {
            this.terminateTask(this.allUnfinishedMassTasks[index].taskId);
        },
        terminateTask : function (taskId) {
            const _this = this;
            axios.get("/requestor/terminateTask", {params: {taskId: taskId}})
                .then(function (Exception) {
                    let message = Exception.data.wrongMessage.type;
                    if(message === "Success"){
                        alert("撤销成功");
                    }else{
                        alert("撤销失败");
                    }
                    _this.getAssignedButIncompleteTaskList();
                });
        },
        addTaskPrice:function(){
            const _this = this;
            axios.get("/requestor/reviseTaskPrice", {params: {taskId:this.reviseTaskId,cash:this.reviseTaskPrice}})
                .then(function (Exception) {
                    let message = Exception.data.wrongMessage.type;
                    if(message==="Success"){
                        alert("修改成功");
                    }else {
                        alert("修改失败");
                    }
                    _this.getAssignedButIncompleteTaskList();
                });
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