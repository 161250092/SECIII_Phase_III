//整体标注类
function ImageLabelVO(labelList){
    this.labelList = labelList;
}

new Vue({
    el: "#markLabelContainer",
    data: {
        labelType: "ImageLabel",
        userId: "",
        username: "",
        taskId: "",
        taskImageNum: 0,

        currentImageIndex: 0,
        currentImageSrc: "",
        currentImageLabelList: [],

        isUserCanLabel: false,
    },
    mounted: function () {
        this.$nextTick(function () {
            this.userId = getUserId();
            this.username = getUsername();
            this.taskId = getTaskId();
            this.isUserCanLabel = isUserCanLabel();
            //获得这个任务的图片数目
            const _this = this;
            axios.get("/markLabelBL/getLabel", { params: {taskId: this.taskId ,userId: this.userId} }).then(function (response) {
                _this.taskImageNum = response.data;
            });
            //获得第一张图片
            this.getLabel();
        })
    },
    methods: {
        getLabel: function () {
            const _this = this;
            axios.get("/markLabelBL/getLabel", { params:
                    { taskId: _this.taskId, userId: _this.userId,
                        labelType: _this.labelType, imageIndex: this.currentImageIndex,} })
                .then(function (response) {
                    _this.currentImageSrc = '/getTaskImage/' + _this.taskId + '/' + response.data.image;
                    _this.currentImageLabelList = response.data.labelList;
                });
        },
        resetCurrentLabel: function () {
            this.currentImageLabelList = [];
        },
        saveCurrentLabel: function () {
            let imageLabelVO = new ImageLabelVO(this.currentImageLabelList);
            let imageLabelVOJson = JSON.stringify(imageLabelVO);
            const _this = this;
            axios.get("/markLabelBL/saveLabel", { params:
                    { taskId: _this.taskId, userId: _this.userId,
                        labelType: _this.labelType, imageIndex: _this.currentImageIndex,
                        labelVOJson: imageLabelVOJson } })
                .then(function (response) {
                    if(response.data === true) {
                        alert("保存成功");
                    }else{
                        alert("保存失败");
                    }
                });
        },
        getPreviousLabel: function () {
            if(this.currentImageIndex > 0){
                this.currentImageUrl = "";
                this.resetCurrentLabel();
                this.currentImageIndex--;
                this.getLabel();
            }else{
                alert("当前是第一张图片");
            }
        },
        getNextLabel: function () {
            if(this.currentImageIndex < (this.taskImageNum - 1)){
                this.currentImageUrl = "";
                this.resetCurrentLabel();
                this.currentImageIndex++;
                this.getLabel();
            }else{
                alert("当前是最后一张图片");
            }
        },
        setTaskAccomplished: function () {
            const _this = this;
            axios.get("/markLabelBL/setTaskAccomplished", { params:
                    { taskId: _this.taskId, userId: _this.userId } })
                .then(function (response) {
                    if(response.data === true){
                        alert("提交成功");
                        jumpToAnotherPage(mainPageUrl);
                    }else {
                        alert("提交失败");
                    }
                });
        },
        //对标签的操作
        addTag: function () {
            let inputTagEl = this.$refs.inputTag;
            //获得输入
            let tag = inputTagEl.value;
            //清空输入
            inputTagEl.value = "";

            //加到数组中
            this.currentImageLabelList.push(tag);
        },
        removeTag: function (tagIndex) {
            this.currentImageLabelList.splice(tagIndex, 1);
        }
    }
});