new Vue({
    el: '#taskInfoContainer',
    data: {
        taskId: "",
        requestorId: "",

        submittedTaskList: [],
        selectedTaskIndex: -1,
        labelListOfSelectedTask:[],
    },
    mounted: function () {
        this.$nextTick(function () {
            this.requestorId = getUserId();
            const _this = this;
            axios.get('/requestor/getAssignedButIncompleteTaskList', {params:{userId: this.requestorId}}).then(function (response) {
                _this.submittedTaskList = response.data;
            });
        });
    },
    methods: {
        checkThisLabel: function (workerIndex) {
            let taskId = this.submittedTaskList[this.selectedTaskIndex].taskId;
            let workerId = this.labelListOfSelectedTask[workerIndex].userId;
            let labelType = this.labelListOfSelectedTask[workerIndex].labelType;

            switch (labelType){
                case "ImageLabel":
                    jumpToTask(markImageLabelPageUrl, workerId, taskId, USERTYPE_REQUESTOR, false);
                    break;
                case "FrameLabel":
                    jumpToTask(markFrameLabelPageUrl, workerId, taskId, USERTYPE_REQUESTOR, false);
                    break;
                case "AreaLabel":
                    jumpToTask(markAreaLabelPageUrl, workerId, taskId, USERTYPE_REQUESTOR, false);
                    break;
                default:
                    alert("标注类型错误");
                    break;
            }
        },
        passThisLabel: function (workerIndex) {
            let taskId = this.submittedTaskList[this.selectedTaskIndex].taskId;
            let workerId = this.labelListOfSelectedTask[workerIndex].userId;
            const _this = this;
            axios.get('/requestor/passTask', {params:{taskId: taskId, userId: workerId}}).then(function (response) {
                let wrongMessageType = response.data.wrongMessage.type;
                if (wrongMessageType === "Success"){
                    alert("成功");
                    _this.getSubmittedTaskList(taskId);
                } else if (wrongMessageType === "Failure") {
                    alert("失败");
                }
            });
        },
        rejectThisLabel: function (workerIndex) {
            let taskId = this.submittedTaskList[this.selectedTaskIndex].taskId;
            let workerId = this.labelListOfSelectedTask[workerIndex].userId;
            const _this = this;
            axios.get('/requestor/rejectTask', {params:{taskId: taskId, userId: workerId}}).then(function (response) {
                let wrongMessageType = response.data.wrongMessage.type;
                if (wrongMessageType === "Success"){
                    alert("成功");
                    _this.getSubmittedTaskList(taskId);
                } else if (wrongMessageType === "Failure") {
                    alert("失败");
                }
            });
        },
        abandonThisLabel: function (workerIndex) {
            let taskId = this.submittedTaskList[this.selectedTaskIndex].taskId;
            let workerId = this.labelListOfSelectedTask[workerIndex].userId;
            const _this = this;
            axios.get('/requestor/abandonTaskByRequestor', {params:{taskId: taskId, userId: workerId}}).then(function (response) {
                let wrongMessageType = response.data.wrongMessage.type;
                if (wrongMessageType === "Success"){
                    alert("成功");
                    _this.getSubmittedTaskList(taskId);
                } else if (wrongMessageType === "Failure") {
                    alert("失败");
                }
            });
        },
        showSelectedTask: function (taskIndex) {
            if(this.selectedTaskIndex === taskIndex){
                this.selectedTaskIndex = -1;
                this.labelListOfSelectedTask = [];
            }else{
                this.selectedTaskIndex = taskIndex;
                let taskId = this.submittedTaskList[taskIndex].taskId;
                this.getSubmittedTaskList(taskId);
            }
        },
        getSubmittedTaskList: function (taskId) {
            const _this = this;
            axios.get('/requestor/getSubmittedTaskList', {params:{taskId: taskId}}).then(function (response) {
                _this.labelListOfSelectedTask = response.data;
            });
        },
        chineseLabelTypeName: function (labelType) {
            switch (labelType){
                case "ImageLabel": return "整体标注";
                case "FrameLabel": return "标框标注";
                case "AreaLabel": return "区域标注";
                default:
                    alert("标注类型错误");
                    break;
            }
        }
    },
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