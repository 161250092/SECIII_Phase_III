
new Vue({
    el:"#requestorInfo",
    data:{
        userId:"",
        username:"无",
        prestige:0,
        userLevel:"0",
        cash:0,
        email:"无",
        phone:"无",
        AllFinishedTask:[],
        AllUnfinishedTask:[],
        maxPublishedTaskNum:0
    },
    mounted: function () {
        const _this = this;
        this.$nextTick(function () {
            _this.userId = getUserId();
            axios.get("/requestor/getRequestorInfo",
                { params:{ userId: this.userId } })
                .then(function (response) {
                    _this.userId = response.data.userId;
                    _this.username = response.data.username;
                    _this.prestige = response.data.prestige;
                    _this.userLevel = response.data.userLevel;
                    _this.cash = response.data.cash;
                    _this.email = response.data.email;
                    _this.phone = response.data.phone;
                    _this.maxPublishedTaskNum = response.data.maxPublishedTaskNum;
                })
            axios.get("/requestor/getAssignedButIncompleteTaskList",
                {params:{ userId: this.userId }})
                .then(function (response){
                _this.AllUnfinishedTasks = response;
             })

            axios.get("/requestor/getAssignedAndAccomplishedTaskList",
                {params:{userId:this.userId}})
                .then(function (response){
                  _this.AllFinishedTasks = response;
                })
        })
    },
    methods: {
        editEmail: function () {
            const _this = this;
            axios.get("/user/reviseUserEmail", {params: {userId:getUserId(),email:_this.email}})
                .then(function (Exception) {
                    let message = Exception.data.WrongMessage.type;
                    if(message==="success")
                        alert("修改成功")
                    else
                        alert("修改失败")


                })

        },
        editPhone: function () {

            const _this = this;
            axios.get("/user/reviseUserPhone", {params: {userId:getUserId(),phone:_this.phone}})
                .then(function (Exception) {
                    let message = Exception.data.WrongMessage.type;
                    if(message==="success")
                        alert("修改成功")
                    else
                        alert("修改失败")
                })

        },

        charge: function () {
            // const _this = this;
            // this.phone = "充钱是不可能充钱的，这辈子不可能充钱"
            alert("充钱是不可能充钱的，这辈子不可能充钱")
        }



    }


});



