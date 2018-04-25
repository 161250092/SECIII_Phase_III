//整体标注类
function ImageLabelVO(labelList){
    this.labelList = labelList;
}

new Vue({
    el: "#markImageLabelContainer",
    data: {
        labelType: "ImageLabel",
        userId: "",
        taskId: "",
        taskImageNum: 0,

        currentImageIndex: 0,
        currentImageUrl: "http://dl.renzhemao.com/d/file/dlzj_new/sgdq/ts/2014-03-11/8d60a184ccb59b8b6852d29aa5e968ec.jpg",
        currentImageLabelList: [],
    },
    mounted: function () {
        this.$nextTick(function () {
            /**
             * 临时用
             */
            //-------------------------------------
            sessionStorage.setItem('userId',"testUserID");
            sessionStorage.setItem('taskId',"testTaskID");
            //-------------------------------------
            this.userId = getUserId();
            this.taskId = getTaskId();
            //获得这个任务的图片数目
            const _this = this;
            axios.get("/markLabel/getTaskImageNumber", { params: { taskId: this.taskId } }).then(function (response) {
                _this.taskImageNum = response.data;
            });
            //获得第一张图片
            this.getImageLabel();
        })
    },
    methods: {
        getImageLabel: function () {
            const _this = this;
            axios.get("/markLabel/getLabel", { params:
                    { taskId: _this.taskId, userId: _this.userId,
                        labelType: _this.labelType, imageIndex: this.currentImageIndex,} })
                .then(function (response) {
                    //图片传输未解决
                    //_this.currentImageUrl =  "url(" + response.data.image + ")";
                    //_this.currentImageLabelList = response.data.labelList;
                    _this.currentImageLabelList = response.data.labelList;
                });
        },
        resetCurrentImageLabel: function () {
            this.currentImageLabelList = [];
        },
        saveCurrentImageLabel: function () {
            let imageLabelVO = new ImageLabelVO(this.currentImageLabelList);
            let imageLabelVOJson = JSON.stringify(imageLabelVO);
            const _this = this;
            axios.get("/markLabel/saveLabel", { params:
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
        getPreviousImageLabel: function () {
            this.saveCurrentImageLabel();
            if(this.currentImageIndex > 0){
                this.resetCurrentImageLabel();
                this.currentImageIndex--;
                this.getImageLabel();
            }else{
                alert("当前是第一张图片");
            }
        },
        getNextImageLabel: function () {
            this.saveCurrentImageLabel();
            if(this.currentImageIndex < (this.taskImageNum - 1)){
                this.resetCurrentImageLabel();
                this.currentImageIndex++;
                this.getImageLabel();
            }else{
                alert("当前是最后一张图片");
            }
        },
        setTaskAccomplished: function () {
            //保存最后一张照片的结果
            this.saveCurrentImageLabel();
            const _this = this;
            axios.get("/markLabel/setTaskAccomplished", { params:
                    { taskId: _this.taskId, userId: _this.userId } })
                .then(function (response) {
                    if(response.data === true){
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