new Vue({
    el:"#taskInfoContainer",
    data:{
        userId: "",
        AllUnfinishedTasks:[],
        taskDescription:"请点击要查看的任务",
        reviseTaskId:"无",
        reviseTaskPrice:"0",

        IMAGE_LABEL_TYPE: "ImageLabel",
        FRAME_LABEL_TYPE: "FrameLabel",
        AREA_LABEL_TYPE: "AreaLabel"
    },
    mounted: function () {
        const _this = this;
        this.$nextTick(function () {
            _this.userId = getUserId();
            axios.get("/requestor/getAssignedButIncompleteTaskList",
                { params:{ userId: this.userId } })
                .then(function (response) {
                    _this.AllUnfinishedTasks = response.data;
                })
        })
    },
    methods:{
        getChineseLabelType: function (labelType) {
            if(labelType === this.IMAGE_LABEL_TYPE){
                return "整体标注";
            }else if(labelType === this.FRAME_LABEL_TYPE){
                return "标框标注";
            }else if(labelType === this.AREA_LABEL_TYPE){
                return "区域标注";
            }
        },

        detailedInfo:function(index){
            this.taskDescription = this.AllFinishedTasks[index].taskDescription;
        },

        changeTaskPrice:function(index){
            this.reviseTaskId = this.AllFinishedTasks[index].taskId;
        },


        // exception unsovled
        recallTask:function(index){

            axios.get("/requestor/revokeTask", {params: {taskId:this.AllUnfinishedTasks[index].taskId}})
                .then(function (Exception) {
                    let message = Exception.data.WrongMessage.type;
                    if(message === "Success")
                        alert("撤销成功");
                    else
                        alert("撤销失败")
                });
        },

        addTaskPrice:function(){
            axios.get("/requestor/reviseTask", {params: {taskId:this.reviseTaskId,cash:this.reviseTaskPrice}})
                .then(function (Exception) {
                    let message = Exception.data.WrongMessage.type;
                    if(message === "Success")
                        alert("修改成功");
                    else
                        alert("修改失败")
                });
        }
    }
});
