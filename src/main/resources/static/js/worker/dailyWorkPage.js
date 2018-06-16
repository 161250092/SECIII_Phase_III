new Vue({
   el:"#achievement",
    data:{
       userId:"",
       achievementList:[]

    },

   mounted:function() {
       const _this = this;
       _this.userId=getUserId();
       this.$nextTick(function () {
           _this.updateAchievement();
           axios.get("/worker/getUserAchievement",
               {param: {userId: _this.userId}})
               .then(function (response){
                        achievementList=response.data;
           })
        })
   },

   methods:{
       getCash:function(index){
           const _this = this;
           axios.get("/worker/getAchievementCash",{param:{userId:_this.userId,achievementId:_this.achievementList[index].achievementId}})
               .then(function(response){
               if(response.data===true)
                   alert("领取成功");
               else
                   alert("您可能已经领取过了");
           })
       },

       updateAchievement:function(){
           const _this =this;
           axios.get("/worker/updateAchievementCash",{param:{userId:_this.userId}})
               .then(function(response){
                   if(response.data===true)
                       console.log("更新成功");
                   else
                       alert("您可能已经领取过了");
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