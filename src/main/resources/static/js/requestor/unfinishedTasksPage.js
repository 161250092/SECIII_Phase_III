new Vue({
    el:"#taskInfoContainer",
    data:{
        AllUnfinishedTasks:[],
        taskDescription:"请点击要查看的任务",
        reviseTaskId:"无",
        reviseTaskPrice:0,
    },
    mounted: function () {
        const _this = this;
        this.$nextTick(function () {
            _this.userId = getUserId();
            axios.get("/requestor/getAssignedButIncompleteTaskList",
                { params:{ userId: getUserId() } })
                .then(function (response) {
                    _this.AllUnfinishedTasks = response.data;
                })
        })
    },

    methods:{
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

        changeTaskPrice:function(index){
            this.reviseTaskId = this.AllUnfinishedTasks[index].taskId;
        },


        // exception unsovled
        recallTask:function(index){
            axios.get("/requestor/revokeTask", {params: {taskId:this.AllUnfinishedTasks[index].taskId}})
                .then(function (Exception) {
                    let message = Exception.data.wrongMessage.type;
                    if(message === "Success")
                        alert("撤销成功");
                    else
                        alert("撤销失败")

                });
        },

        addTaskPrice:function(){
            axios.get("/requestor/reviseTask", {params: {taskId:this.reviseTaskId,cash:this.reviseTaskPrice}})
                .then(function (Exception) {
                    let message = Exception.data.wrongMessage.type;
                    if(message==="Success")
                        alert("修改成功");
                    else
                        alert("修改失败")

                });
        }
    }
});
