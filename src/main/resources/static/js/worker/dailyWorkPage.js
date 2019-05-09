new Vue({
   el:"#achievement",
    data:{
       userId:"",
       achievementList:[],
    },

   mounted:function() {
       this.$nextTick(function () {
           this.userId = getUserId();
           this.updateAchievement();

           this.getUserAchievement();
        });
   },
   methods:{
       getUserAchievement:function(){
           const _this = this;
           axios.get("/worker/getUserAchievement", {params: {userId: _this.userId}})
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
                       _this.getUserAchievement();
                   }
                   else
                       alert("更新失败");
               });
           this.getUserAchievement();
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
       },
       getProcessInPercent: function (processNum) {
           let num;
           if (processNum > 1){ num = 1; }
           else { num = processNum; }
           return Number(num * 100).toFixed(2) + "%";
       }
   }
});

new Vue({
    el: "#home",
    data: {
        username: ""
    },
    mounted: function () {
        this.$nextTick(function () {
            this.username = getUsername();
        });
    }
});