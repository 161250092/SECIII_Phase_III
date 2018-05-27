//整体标注类集合VO
function ImageLabelSetVO(taskImageNum, labelList, filenameList){
    this.taskImageNum = taskImageNum;
    this.labelList = labelList;
    this.filenameList = filenameList;
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
            this.userId = getUserId();
            this.username = getUsername();
            this.taskId = getTaskId();
            // this.isUserCanLabel = isUserCanLabel();
            //获得这个任务的标注信息
            this.getLabel();
        })
    },
    methods: {
        getLabel: function () {
            const _this = this;
            axios.get("/markLabel/getLabel", { params: {taskId: this.taskId ,userId: this.userId} }).then(function (response) {
                _this.taskImageNum = response.data.taskImageNum;
                _this.labelList = response.data.labelList;
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
                axios.get("/markLabel/saveImageLabel",
                    { params: { taskId: _this.taskId, userId: _this.userId, imageLabelVOSetJSON: imageLabelVOSetJSON, isWorker: _this.isWorker } })
                    .then(function (response) {
                        if(response.data === true) {
                            alert("保存成功");
                            _this.hasSavedChanges = true;
                        }else{
                            alert("保存失败");
                            _this.hasSavedChanges = false;
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
            }else{
                alert("当前是最后一张图片");
            }
        },
        setTaskAccomplished: function () {
            const _this = this;
            if(this.hasSavedChanges === true){
                axios.get("/markLabel/setTaskAccomplished", { params: { taskId: _this.taskId, userId: _this.userId, isWorker: _this.isWorker } })
                    .then(function (response) {
                        if(response.data === true){
                            alert("提交成功");
                            jumpToAnotherPage(mainPageUrl);
                        }else {
                            alert("提交失败");
                        }
                    });
            }else{
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