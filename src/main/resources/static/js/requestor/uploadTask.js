new Vue({
   el: "#taskDraftInfoContainer",
   data:{
       userId: "",
       taskDraftList: [],
   },
   mounted: function () {
       this.$nextTick(function () {
           this.userId = getUserId();
           this.getTaskDraftList();
       });
   },
   methods:{
       getTaskDraftList: function () {
           let _this = this;
           axios.get('/requestor/getTaskDraftList',{params: {userId: this.userId}}).then(function (response) {
               _this.taskDraftList = response.data;
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
               case "DRAFT": return "整体标注";
               case "INCOMPLETE": return "标框标注";
               case "REVOKED": return "区域标注";
               case  "ACCOMPLISHED": return "完成";
               default:
                   alert("标注类型错误");
                   break;
           }
       }
   }
});