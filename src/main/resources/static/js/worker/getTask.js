function WorkerBidVO(workerId,chosenTaskId,ratioOfArrivedTime,wantedUnitPrice,maxWantedImageNum) {
    this.workerId = workerId;
    this.chosenTaskId = chosenTaskId;
    this.ratioOfArrivedTime = ratioOfArrivedTime;
    this.wantedUnitPrice = wantedUnitPrice;
    this.maxWantedImageNum = maxWantedImageNum;
    this.workerBidState = "WAITING";
}

new Vue({
    el:"#taskListContainer",
    data:{
        workerId: "",

        availableTaskList:[],
        acceptedTaskIdSet: [],

        availableMassTaskList: [],
        selectedMassTaskIndex: -1,
        wantedUnitPrice: 0,
        maxWantedImageNum: 0,
    },
    mounted: function () {
        this.$nextTick(function () {
            this.workerId = getUserId();
            this.acceptedTaskIdSet = new Set();
            this.getAvailableTaskList();
        });
    },
    methods: {
        getAvailableTaskList: function () {
            const _this = this;
            axios.get('/worker/getAvailableTaskList', {params: {userId: this.workerId}}).then(function (response) {
                _this.availableTaskList = [];
                response.data.forEach(function (item, index) {
                    if (item.taskId.indexOf("MASS") === -1) {
                        _this.availableTaskList.push(item);
                    }
                });
            });
            axios.get('/workerMassTask/getAllAvailableMassTask', {params: {workerId: _this.workerId, searchDate: Date.parse(new Date())}})
                .then(function (response) {
                    _this.availableMassTaskList = response.data;
                })
        },
        operateOnTaskIdSet: function (taskIndex) {
            if(this.acceptedTaskIdSet.has(this.availableTaskList[taskIndex].taskId)){
                this.acceptedTaskIdSet.delete(this.availableTaskList[taskIndex].taskId);
            }else{
                this.acceptedTaskIdSet.add(this.availableTaskList[taskIndex].taskId);
            }
        },
        operateOnMassTaskIdSet : function (massTaskIndex) {
            this.selectedMassTaskIndex = massTaskIndex;
        },
        acceptTasks: function () {
            if (this.acceptedTaskIdSet.length !== 0){
                let taskIdList = [];
                this.acceptedTaskIdSet.forEach(function (element, sameElement, set) {
                    taskIdList.push(element);
                });

                let taskIdListJson = JSON.stringify(taskIdList);
                axios.get('/worker/acceptTask', {params: {userId: this.workerId, taskIdListJSON: taskIdListJson}}).then(function (response) {
                    if(response.data.wrongMessage.type === 'Success'){
                        alert("接受成功");
                        jumpToAnotherPage(workerMainPageUrl);
                    }else{
                        alert("接受失败");
                    }
                });
            } else {
                alert("请选择任务");
            }
        },
        bidForThisMassTask : function () {
            if (this.selectedMassTaskIndex >= 0){
                let workerId = this.workerId;
                let chosenTaskId = this.availableMassTaskList[this.selectedMassTaskIndex].publishedTaskVO.taskId;

                let currentTime = Date.parse(new Date());
                let startTime = this.availableMassTaskList[this.selectedMassTaskIndex].massTaskDetailVO.startTime;
                let endTime = this.availableMassTaskList[this.selectedMassTaskIndex].massTaskDetailVO.endTime;
                let ratioOfArrivedTime = (currentTime - startTime) / (endTime - startTime);

                let wantedUnitPrice = this.wantedUnitPrice;
                let maxWantedImageNum = this.maxWantedImageNum;

                let workerBidVO = new WorkerBidVO(workerId,chosenTaskId,ratioOfArrivedTime,wantedUnitPrice,maxWantedImageNum);
                let workerBidVOJson = JSON.stringify(workerBidVO);

                const _this = this;
                axios.get('/workerMassTask/bidForMassTask', {params: {workerBidVOJson: workerBidVOJson}})
                    .then(function (response) {
                        let wrongMessageType = response.data.wrongMessage.type;
                        if (wrongMessageType === "Success"){
                            alert("申请成功");
                            _this.getAvailableTaskList();
                        } else {
                            alert("申请失败");
                        }
                    })
            }
        },
        upByTaskPrice:function(){
            this.availableTaskList.sort(function (a, b) {
                return b.taskPrice - a.taskPrice;
            });
        },
        downByTaskPrice:function () {
            this.availableTaskList.sort(function (a, b) {
                return a.taskPrice - b.taskPrice;
            });
        },
        upByImageNum:function(){
            this.availableTaskList.sort(function (a, b) {
                return b.imageNum - a.imageNum;
            });
        },
        downByImageNum:function(){
            this.availableTaskList.sort(function (a, b) {
                return a.imageNum - b.imageNum;
            });
        },
        convertToDate : function (millisecond) {
            let time = new Date(millisecond);
            return time.toLocaleString();
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