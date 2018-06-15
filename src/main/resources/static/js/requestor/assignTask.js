new Vue({
   el: "#taskDraftInfoContainer",
   data:{
       userId: "",
       taskDraftListWithoutSample: [],
       taskDraftListWithSample: []
   },
   mounted: function () {
       this.$nextTick(function () {
           this.userId = getUserId();
           this.getTaskDraftList();
       });
   },
   methods:{
       getTaskDraftList: function () {
           this.taskDraftListWithoutSample = [];
           this.taskDraftListWithSample = [];

           let taskDraftList = [];
           let _this = this;
           axios.get('/requestor/getTaskDraftList',{params: {userId: this.userId}}).then(function (response) {
               taskDraftList = response.data;
               taskDraftList.forEach(function (taskDraft, index) {
                   if (taskDraft.publishedTaskState === "DRAFT_WITHOUT_SAMPLE"){
                       _this.taskDraftListWithoutSample.push(taskDraft);
                   }else if(taskDraft.publishedTaskState === "DRAFT_WITH_SAMPLE"){
                       _this.taskDraftListWithSample.push(taskDraft);
                   }
               });
           });
       },
       jumpToMarkSample: function (taskWithoutSampleIndex) {
           let taskId = this.taskDraftListWithoutSample[taskWithoutSampleIndex].taskId;
           let labelType = this.taskDraftListWithoutSample[taskWithoutSampleIndex].labelType;

           switch (labelType){
               case "ImageLabel":
                   jumpToTask(markImageLabelPageUrl, this.userId, taskId, USERTYPE_REQUESTOR, true);
                   break;
               case "FrameLabel":
                   jumpToTask(markFrameLabelPageUrl, this.userId, taskId, USERTYPE_REQUESTOR, true);
                   break;
               case "AreaLabel":
                   jumpToTask(markAreaLabelPageUrl, this.userId, taskId, USERTYPE_REQUESTOR, true);
                   break;
               default:
                   alert("标注类型错误");
                   break;
           }
       },
       assignTask: function (taskWithSampleIndex) {
           let taskId = this.taskDraftListWithSample[taskWithSampleIndex].taskId;

           let _this = this;
           axios.get('/requestor/assignTask',{params: {taskId: taskId}}).then(function (response) {
               let wrongMessageType = response.data.wrongMessage.type;
               if (wrongMessageType === "IncompleteSample"){
                   alert("未完成该任务的样本标注");
               }else if (wrongMessageType === "PrestigeNotEnough") {
                   alert("威望过低，无法发布任务");
               }else if (wrongMessageType === "CashNotEnough") {
                   alert("账户余额不足，无法发布任务");
               }else if (wrongMessageType === "AssignSuccess") {
                   alert("发布成功");
               }else {
                   alert("**返回类型错误**");
               }
               _this.getTaskDraftList();
           });
       },
       chineseLabelTypeName: function (labelType) {
           switch (labelType){
               case "ImageLabel": return "整体标注";
               case "FrameLabel": return "标框标注";
               case "AreaLabel": return "区域标注";
               default:
                   alert("标注类型错误");
                   break;
           }
       },
       chinesePublishedTaskState: function (publishedTaskState) {
           switch (publishedTaskState){
               case "DRAFT_WITHOUT_SAMPLE": return "未标注样本";
               case "DRAFT_WITH_SAMPLE": return "等待发布";
               case "INCOMPLETE": return "任务进行中";
               case "REVOKED": return "撤销";
               case  "ACCOMPLISHED": return "完成";
               default:
                   alert("标注类型错误");
                   break;
           }
       }
   }
});