new Vue({
    el: '#taskInfoContainer',
    data: {
        taskId: "",
        userId: "",

        submittedTaskList: [],
        selectedTaskIndex: -1,
        labelListOfSelectedTask:[],
    },
    mounted: function () {
        this.$nextTick(function () {
            this.userId = getUserId();

            const _this = this;
            axios.get('/requestor/getAssignedButIncompleteTaskList', {params:{userId: _this.userId}}).then(function (response) {
                _this.submittedTaskList = response.data;
            });
        });
    },
    methods: {
        checkThisLabel: function (workerIndex) {
            let taskId = this.submittedTaskList[this.selectedTaskIndex].taskId;
            let userId = this.labelListOfSelectedTask[workerIndex].userId;
            let labelType = this.labelListOfSelectedTask[workerIndex].labelType;

            //////////////////////////////////////////////////////////

            switch (labelType){
                case "ImageLabel":
                    jumpToTask(markImageLabelPageUrl, userId, taskId, USERTYPE_REQUESTOR, false);
                    break;
                case "FrameLabel":
                    jumpToTask(markFrameLabelPageUrl, userId, taskId, USERTYPE_REQUESTOR, false);
                    break;
                case "AreaLabel":
                    jumpToTask(markAreaLabelPageUrl, userId, taskId, USERTYPE_REQUESTOR, false);
                    break;
                default:
                    alert("标注类型错误");
                    break;
            }
        },
        passThisLabel: function (workerIndex) {
            let taskId = "temporary";
            let userId = "TEMPORARY";
            axios.get('/requestor/passTask', {params:{taskId: taskId, userId: userId}}).then(function (response) {

            });
            alert(workerIndex)
        },
        rejectThisLabel: function (workerIndex) {
            let taskId = "temporary";
            let userId = "TEMPORARY";
            axios.get('/requestor/rejectTask', {params:{taskId: taskId, userId: userId}}).then(function (response) {

            });
            alert(workerIndex)
        },
        abandonThisLabel: function (workerIndex) {
            let taskId = "temporary";
            let userId = "TEMPORARY";
            axios.get('/requestor/abandonTaskByRequestor', {params:{taskId: taskId, userId: userId}}).then(function (response) {

            });
            alert(workerIndex)
        },
        showSelectedTask: function (taskIndex) {
            if(this.selectedTaskIndex === taskIndex){
                this.selectedTaskIndex = -1;
                this.labelListOfSelectedTask = [];
            }else{
                this.selectedTaskIndex = taskIndex;
                let taskId = this.submittedTaskList[taskIndex].taskId;
                let _this = this;
                axios.get('/requestor/getSubmittedTaskList', {params:{taskId: taskId, userId: this.userId}}).then(function (response) {
                    _this.labelListOfSelectedTask = response.data;
                });
            }
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