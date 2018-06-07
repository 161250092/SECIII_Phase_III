new Vue({
    el:"#InfoContainer",
    data:{
        userId:"",
        taskMessageInfo:[],
        guyMessageInfo:[],
        billMessageInfo:[],
        achievementMessageInfo:[],
        IMAGE_LABEL_TYPE: "ImageLabel",
        FRAME_LABEL_TYPE: "FrameLabel",
        AREA_LABEL_TYPE: "AreaLabel"
    },


    mounted:function(){
        const _this = this;
        this.$nextTick(function () {
            _this.userId = getUserId();
            axios.get("/message/getAllWorkerMessage",
                { params:{ userId: this.userId } })
                .then(function (response) {
                    _this.taskMessageInfo= response.data.taskMessageList;
                    _this.guyMessageInfo  = response.data.GuyMessageList;
                    _this.billMessageInfo = response.data.billMessageList;
                    _this.achievementMessageInfo = response.data.achievementMessageList;
                })
        })
    },


    methods: {

        confirmTaskMessageInfo:function (index) {
            const _this = this;
            axios.get("/message/checkWorkerTaskMessage", {params: {messageId: _this.taskMessageInfo[index].messageId}}).then(function (response) {
                if(response === true)
                    alert("已确认");
                else
                    alert("error:fail to confirm task message");
            });
        },

        confirmGuyMessageInfo:function(index){
            const _this = this;
            axios.get("/message/checkGuyMessage", {params: {messageId: _this.guyMessageInfo[index].messageId}}).then(function (response) {
                if(response === true)
                    alert("已确认");
                else
                    alert("error:fail to confirm guy message");
            });


        },

        confirmCashMessageInfo:function(index){
            const _this = this;
            axios.get("/message/checkBillMessage", {params: {messageId: _this.billMessageInfo[index].messageId}}).then(function (response) {
                if(response === true)
                    alert("已确认");
                else
                    alert("error:fail to confirm cash message");
            });
        },

        confirmAchievementMessageInfo:function(index){
            const _this = this;
            axios.get("/message/checkAchievementMessage", {params: {messageId: _this.achievementMessageInfo[index].messageId}}).then(function (response) {
                if(response === true)
                    alert("已确认");
                else
                    alert("error:fail to confirm cash message");
            });
        }






    }


});