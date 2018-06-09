new Vue({
   el:"#achievement",
    data:{
       achievementList:[]

    },

   mounted:function() {
       const _this = this;
       this.$nextTick(function () {
           axios.get("/worker/getUserAchievement",
               {param: {userId: getUserId()}})
               .then(function (response){
                        achievementList=response.data;
           })

        })
   },

   methods:{
       getCash:function(index){
           const _this = this;
           axios.get("/worker/getAchievementCash",{param:{userId:getUserId(),achievementId:_this.achievementList[index].achievementId}})
               .then(function(response){
               if(response.data===true)
                   alert("领取成功");
               else
                   alert("您可能已经领取过了");
           })
       },

       updateAchievement:function(index){
           axios.get("/worker/updateAchievementCash",{param:{userId:getUserId()}})
               .then(function(response){
                   if(response.data===true)
                       alert("更新成功");
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