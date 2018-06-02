new Vue({
   el: "#taskInfoContainer",
   data: {
       userId: "",
       AllUnfinishedTasks: [],

       IMAGE_LABEL_TYPE: "ImageLabel", 
       FRAME_LABEL_TYPE: "FrameLabel",
       AREA_LABEL_TYPE: "AreaLabel",
   },
    mounted: function () {
        this.$nextTick(function () {
            this.userId = getUserId();
            this.getAllUnfinishedTask();
        })


    },
    methods: {
       doTask: function (index) {
           let labelType = this.AllUnfinishedTasks[index].labelType;
           let taskId = this.AllUnfinishedTasks[index].taskId;
           if(labelType === this.IMAGE_LABEL_TYPE){
               jumToTask(markImageLabelPageUrl, this.userId, taskId);
           }else if(labelType === this.FRAME_LABEL_TYPE){
               jumToTask(markFrameLabelPageUrl, this.userId, taskId);
           }else if(labelType === this.AREA_LABEL_TYPE){
               jumToTask(markAreaLabelPageUrl, this.userId, taskId);
           }else {
               alert("该任务标注类型错误");
           }
       },
        getChineseLabelType: function (labelType) {
            if(labelType === this.IMAGE_LABEL_TYPE){
                return "整体标注";
            }else if(labelType === this.FRAME_LABEL_TYPE){
                return "标框标注";
            }else if(labelType === this.AREA_LABEL_TYPE){
                return "区域标注";
            }
        },

        getAllUnfinishedTask:function () {
            const _this = this;
            axios.get("/worker/getAcceptedButIncompleteTaskList",
                { params:{ userId: this.userId } })
                .then(function (response) {
                    _this.AllUnfinishedTasks = response.data;
                })
        },

        recallTask:function(index){
            let  recallTaskId = this.AllUnfinishedTasks[index].taskId;
            const _this = this;
            axios.get("/worker/abandonTaskByWorker").then(function (Exception){

                let  message = Exception.data.WrongMessage.type;
                if(message==="Success") {
                    alert("修改成功");
                    _this.getAllUnfinishedTask();
                }
                else
                    alert("修改失败")
            })
       }
    }
});


