new Vue({
   el: "#taskDraftInfoContainer",
   data:{
       userId: "",
       taskDraftListWithoutSample: [],
       taskDraftListWithSample: [],
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
           axios.get('/requestor/getTaskDraftList',{params: {userId: this.userId}}).then(function (response) {
               taskDraftList = response.data;
           });

           let _this = this;
           taskDraftList.forEach(function (taskDraft, index) {
              if (taskDraft.publishedTaskState === "DRAFT_WITHOUT_SAMPLE"){
                  _this.taskDraftListWithoutSample.append(taskDraft);
              }else if(taskDraft.publishedTaskState === "DRAFT_WITH_SAMPLE"){
                  _this.taskDraftListWithSample.append(taskDraft);
              }
           });
       },
       jumpToMarkSample: function (taskWithoutSampleIndex) {
           let taskId = this.taskDraftListWithoutSample[taskWithoutSampleIndex].taskId;
           let labelType = this.taskDraftListWithoutSample[taskWithoutSampleIndex].labelType;

           sendUserId(this.userId);
           sendTaskId(taskId);
           setUserCanLabel(true);

           switch (labelType){
               case "ImageLabel":
                   jumpToAnotherPage(markImageLabelPageUrl);
                   break;
               case "FrameLabel":
                   jumpToAnotherPage(markFrameLabelPageUrl);
                   break;
               case "AreaLabel":
                   jumpToAnotherPage(markAreaLabelPageUrl);
                   break;
               default:
                   alert("标注类型错误");
                   break;
           }
       },
       assignTask: function (taskWithSampleIndex) {
           let taskId = this.taskDraftListWithSample[taskWithSampleIndex].taskId;
           axios.get('/requestor/assignTask',{params: {taskId: taskId}}).then(function (response) {

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