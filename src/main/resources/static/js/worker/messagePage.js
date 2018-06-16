new Vue({
    el:"#InfoContainer",
    data:{
        userId:"",
        username:"",
        taskMessageInfo:[],
        guyMessageInfo:[],
        billMessageInfo:[],
        achievementMessageInfo:[]

    },


    mounted:function(){
        const _this = this;
        this.$nextTick(function () {
            _this.userId = getUserId();
            _this.username =getUsername();
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

        updateMessage:function(){
            const _this = this;
            _this.userId = getUserId();
            axios.get("/message/getAllWorkerMessage",
                { params:{ userId: this.userId } })
                .then(function (response) {
                    _this.taskMessageInfo= response.data.taskMessageList;
                    _this.guyMessageInfo  = response.data.GuyMessageList;
                    _this.billMessageInfo = response.data.billMessageList;
                    _this.achievementMessageInfo = response.data.achievementMessageList;
                })
        },

        confirmTaskMessageInfo:function (index) {
            const _this = this;
            axios.get("/message/checkWorkerTaskMessage", {params: {messageId: _this.taskMessageInfo[index].messageId.value}}).then(function (response) {
                if(response.data === true) {
                    alert("已确认");
                    _this.updateMessage();
                }
                else
                    alert("error:fail to confirm task message");
            });
        },

        confirmGuyMessageInfo:function(index){
            const _this = this;
            axios.get("/message/checkGuyMessage", {params: {messageId: _this.guyMessageInfo[index].messageId.value}}).then(function (response) {
                if(response.data === true) {
                    alert("已确认");
                    _this.updateMessage();
                }
                else
                    alert("error:fail to confirm guy message");
            });


        },

        confirmCashMessageInfo:function(index){
            const _this = this;
            axios.get("/message/checkBillMessage", {params: {messageId: _this.billMessageInfo[index].messageId.value}}).then(function (response) {
                if(response.data === true) {
                    alert("已确认");
                    _this.updateMessage();
                }
                else
                    alert("error:fail to confirm cash message");
            });
        },

        confirmAchievementMessageInfo:function(index){
            const _this = this;
            axios.get("/message/checkAchievementMessage", {params: {messageId: _this.achievementMessageInfo[index].messageId.value}}).then(function (response) {
                if(response.data === true) {
                    alert("已确认");
                    _this.updateMessage();
                }
                else
                    alert("error:fail to confirm cash message");
            });
        }






    }


});