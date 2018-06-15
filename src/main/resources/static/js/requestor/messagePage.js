new Vue({
    el:"#InfoContainer",
    data:{
        userId:"",
        taskMessageInfo:[],
        billMessageInfo:[]
    },

    mounted:function(){
        const _this = this;
        this.$nextTick(function () {
            _this.userId = getUserId();
            axios.get("/message/getAllWorkerMessage",
                { params:{ userId: this.userId } })
                .then(function (response) {
                    _this.taskMessageInfo= response.data.taskMessageList;
                    _this.billMessageInfo = response.data.billMessageList;
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
                    _this.billMessageInfo = response.data.billMessageList;
                })
        },
        confirmTaskMessageInfo:function (index) {
            const _this = this;
            axios.get("/message/checkWorkerTaskMessage", {params: {messageId: _this.taskMessageInfo[index].messageId}}).then(function (response) {
                if(response === true) {
                    alert("已确认");
                    _this.updateAchievement();
                }
                else
                    alert("error:fail to confirm task message");
            });
        },

        confirmCashMessageInfo:function(index){
            const _this = this;
            axios.get("/message/checkBillMessage", {params: {messageId: _this.billMessageInfo[index].messageId}}).then(function (response) {
                if(response === true) {
                    alert("已确认");
                    _this.updateAchievement();
                }
                else
                    alert("error:fail to confirm cash message");
            });
        }






    }




})