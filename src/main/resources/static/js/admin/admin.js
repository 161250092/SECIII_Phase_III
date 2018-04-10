var vm = new Vue({
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
            axios.get("/AdministerController/uploadImages").then(function (value) {
                _this.numOfUsers = value.data.numOfUsers;
                _this.numOfTasks = value.data.numOfTasks;
                _this.numOfIncompleteTasks = value.data.numOfIncompleteTasks;
                _this.numOfAccomplishedTask = value.data.numOfAccomplishedTask;
            })
        }
    }
});