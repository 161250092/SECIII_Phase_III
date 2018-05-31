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
           axios.get('/requestor/getTaskDraftList"',{params: {userId: this.userId}}).then(function (response) {
               _this.taskDraftList = response.data;
           });
       }
   }
});