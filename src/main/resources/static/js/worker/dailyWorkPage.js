new Vue({
   el:"#achievement",
    data:{
       userId:"",
       achievementList:[],
        username:""

    },

   mounted:function() {
       const _this = this;
       _this.userId=getUserId();
       _this.username = getUsername();
       console.log(this.userId);
       this.$nextTick(function () {
           axios.get("/worker/getUserAchievement", {params: {userId: this.userId}})
               .then(function (response){
                   _this.achievementList=response.data;
               });

        })
   },
   methods:{
       getUserAchievement:function(){
           const _this = this;
           axios.get("/worker/getUserAchievement",
               {params: {userId: _this.userId}})
               .then(function (response){
                   _this.achievementList=response.data;
               })
       },

       getCash:function(index){
           const _this = this;
           axios.get("/worker/getAchievementCash",{params:{userId:_this.userId,achievementId:_this.achievementList[index].achievementId}})
               .then(function(response){
               if(response.data===true) {
                   alert("领取成功");
                   _this.updateAchievement();
               }
               else
                   alert("您未完成要求或者已经领取");
           })
       },

       updateAchievement:function(){
           const _this =this;
           axios.get("/worker/updateAchievementCash",{params:{userId:_this.userId}})
               .then(function(response){
                   if(response.data===true) {
                       alert("更新成功");
                   }
                   else
                       alert("更新失败");
               })
       },


       sign_in:function(){
           alert("签到成功");
       },

       checkMessage:function(){
           jumpToAnotherPage(messagePagePageUrl);
       },

       doTask:function(){
           jumpToAnotherPage(doTaskPageUrl);
       },

       getTask:function(){
           jumpToAnotherPage(getTaskPageUrl);
       }

   }

});