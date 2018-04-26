new Vue({
    el:"#statisticsTable",
    data:{
        numOfUsers: 0,
        numOfTasks: 0,
        numOfIncompleteTasks: 0,
        numOfAccomplishedTask: 0
    },
    mounted:function (ev) {
        this.$nextTick(function () {
            this.getWebsiteStatistics();
        })
    },
    methods:{
        getWebsiteStatistics:function () {
            var _this = this;
            axios.get("/getWebsiteStatistics").then(function (response) {
                _this.numOfUsers = response.data.numOfUsers;
                _this.numOfTasks = response.data.numOfTasks;
                _this.numOfIncompleteTasks = response.data.numOfIncompleteTasks;
                _this.numOfAccomplishedTask = response.data.numOfAccomplishedTask;
            })
        }
    }
});