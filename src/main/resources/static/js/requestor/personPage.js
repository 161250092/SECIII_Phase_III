
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
            this.email = "尚未实现"
            alert("尚未实现")

        },
        editPhone: function () {
            const _this = this;
            this.phone = "尚未实现"
            alert("尚未实现")
        },

        charge: function () {
            // const _this = this;
            // this.phone = "充钱是不可能充钱的，这辈子不可能充钱"
            alert("充钱是不可能充钱的，这辈子不可能充钱")
        }



    }


});



