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
            axios.get("/message/getAllRequestorMessage",
                { params:{ userId:  _this.userId } })
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
            axios.get("/message/getAllRequestorMessage",
                { params:{ userId: _this.userId } })
                .then(function (response) {
                    _this.taskMessageInfo= response.data.taskMessageList;
                    _this.billMessageInfo = response.data.billMessageList;
                })
        },
        confirmTaskMessageInfo:function (index) {
            const _this = this;
            axios.get("/message/checkRequestorTaskMessage", {params: {messageId: _this.taskMessageInfo[index].messageId.value}}).then(function (response) {
                if(response.data === true) {
                    alert("已确认");
                    _this.updateMessage();
                }
                else
                    alert("error:fail to confirm task message");
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