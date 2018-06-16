//整体标注类集合VO
function ImageLabelSetVO(taskImageNum, labelList, filenameList){
    this.taskImageNum = taskImageNum;
    this.labelList = labelList;
    this.filenameList = filenameList;
}
function ImageLabel(tagList){
    this.tagList = tagList;
}

new Vue({
    el: "#markLabelContainer",
    data: {
        userId: "",
        username: "",

        labelType: "ImageLabel",
        taskId: "",
        taskImageNum: 0,
        labelList: [],
        filenameList: [],

        currentLabelIndex: 0,
        hasSavedChanges: true,
        isUserCanLabel: true,
        isWorker: false,
    },
    mounted: function () {
        this.$nextTick(function () {
            this.userId = getWorkerIdWhenLabel();
            this.username = getUsername();
            this.taskId = getTaskId();
            this.isUserCanLabel = isUserCanLabel();
            //获得这个任务的标注信息
            this.getLabel();
        })
    },
    methods: {
        getLabel: function () {
            const _this = this;
            axios.get("/markImageLabel/getImageLabel", { params: {taskId: this.taskId ,userId: this.userId} }).then(function (response) {
                _this.taskImageNum = response.data.taskImageNum;
                _this.labelList = response.data.labelList;
                if(_this.labelList.length === 0){
                    _this.labelList.push(new ImageLabel([]));
                }
                _this.filenameList = response.data.filenameList;
            });
        },
        resetCurrentLabel: function () {
            this.labelList[this.currentLabelIndex].tagList = [];
            this.hasSavedChanges = false;
        },
        saveLabelList: function () {
            if(this.hasSavedChanges === false){
                let imageLabelSetVO = new ImageLabelSetVO(this.taskImageNum, this.labelList, this.filenameList);
                let imageLabelVOSetJSON = JSON.stringify(imageLabelSetVO);
                const _this = this;
                axios.get("/markImageLabel/saveImageLabel",
                    { params: { taskId: _this.taskId, userId: _this.userId, imageLabelVOSetJSON: imageLabelVOSetJSON, isWorker: _this.isWorker } })
                    .then(function (response) {
                        if(response.data === true) {
                            alert("保存成功");
                            _this.hasSavedChanges = true;
                        }else{
                            alert("保存失败");
                        }
                    });
            }else {
                alert("没有变更");
            }
        },
        previousLabel: function () {
            if(this.currentLabelIndex > 0){
                this.currentLabelIndex--;
            }else{
                alert("当前是第一张图片");
            }
        },
        nextLabel: function () {
            if(this.currentLabelIndex < (this.taskImageNum - 1)){
                this.currentLabelIndex++;
                if(this.labelList.length < this.currentLabelIndex + 1){
                    this.labelList.push(new ImageLabel([]));
                }
            }else{
                alert("当前是最后一张图片");
            }
        },
        setTaskAccomplished: function () {
            if(this.isAllLabel() === false){
                alert("有图片未标注");
                return;
            }

            if(this.hasSavedChanges === true){
                const _this = this;
                axios.get("/markImageLabel/setTaskAccomplished", { params: { taskId: _this.taskId, userId: _this.userId, isWorker: _this.isWorker } })
                    .then(function (response) {
                        if(response.data === true){
                            alert("提交成功");
                            if (getUserType() === USERTYPE_REQUESTOR) {
                                jumpToAnotherPage(requestorMainPageUrl);
                            }else if (getUserType() === USERTYPE_WORKER){
                                jumpToAnotherPage(workerMainPageUrl)
                            }
                        }else {
                            alert("提交失败");
                        }
                    });
            }else {
                alert("未保存修改");
            }
        },
        //对标签的操作
        addTag: function () {
            let inputTagEl = this.$refs.inputTag;
            //获得输入
            let tag = inputTagEl.value;
            //清空输入
            inputTagEl.value = "";

            //加到数组中
            this.labelList[this.currentLabelIndex].tagList.push(tag);
            this.hasSavedChanges = false;
        },
        removeTag: function (tagIndex) {
            this.labelList[this.currentLabelIndex].tagList.splice(tagIndex, 1);
            this.hasSavedChanges = false;
        },
        //判断是否标注了全部图片
        isAllLabel: function () {
            for(let i = 0; i < this.labelList.length; i++){
                if(this.labelList[i].tagList.length === 0){
                    return false;
                }
            }
            return true;
        },
        returnToMainPage: function () {
            let userType = getUserType();
            let isUserCanLabel = isUserCanLabel();
            leaveTheTask();
            if(userType === USERTYPE_WORKER){
                if (isUserCanLabel === true){
                    jumpToAnotherPage(assignTaskPageUrl);
                }else {
                    jumpToAnotherPage(checkSubmittedLabelPageUrl);
                }
            }else if(userType === USERTYPE_REQUESTOR){
                jumpToAnotherPage(requestorMainPageUrl);
            }
        }
    },
    computed:{
        currentImageSrc: function () {
            if(this.filenameList.length > 0){
                return '/image/getTaskImage/' + this.taskId + '/' + this.filenameList[this.currentLabelIndex];
            }else{
                return '/image/getTaskImage/errorTaskImage.jpg'
            }
        },
        currentLabel: function () {
            if(this.labelList.length > 0){
                return this.labelList[this.currentLabelIndex].tagList;
            }else{
                return [];
            }
        }
    }
});