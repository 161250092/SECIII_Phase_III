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
       const _this = this;
        this.$nextTick(function () {
            _this.userId = getUserId();
            axios.get("/WorkerController/getAcceptedButIncompleteTaskList", 
                { params:{ userId: this.userId } })
                .then(function (response) { 
                _this.AllUnfinishedTasks = response.data;
            })
        })

        console.log(AllUnfinishedTasks.length);
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
        }
    },


        recallTask:function(index){
            let  recallTaskId = this.AllUnfinishedTasks[index].taskId;
            axios.get("/getWebsiteStatistics").then(function (response)



        }

});


