//标框类集合VO
function FrameLabelVO(labelList) {
    this.taskImageNum = taskImageNum;
    this.labelList = labelList;
    this.filenameList = filenameList;
}
//框中的标签类
function FrameLabelListItem(startX, startY, width, height, tag){
    this.startX = startX;
    this.startY = startY;
    this.width = width;
    this.height = height;
    this.tag = tag;
}

new Vue({
    el: "#markLabelContainer",
    data: {
        userId: "",
        username: "",

        labelType: "FrameLabel",
        taskId: "",
        taskImageNum: 0,
        labelList: [],
        filenameList: [],

        currentLabelIndex: 0,
        currentFrameLabelList: [],

        isUserCanLabel: false,

        canvas: undefined,
        canvasContext: undefined,
        tempImageData: undefined,

        //左上角的点
        topLeftX: 0,
        topLeftY: 0,
        //起始点
        currentStartX: 0,
        currentStartY: 0,
        //结束点
        currentEndX: 0,
        currentEndY: 0,
        isDrawing: false,
        canDraw: true,
        canInputTag: false,
    },
    mounted: function () {
        this.$nextTick(function () {
            this.canvas = this.$refs.canvas;
            this.canvasContext = this.canvas.getContext('2d');
            const rect = this.canvas.getBoundingClientRect();
            this.canvas.width = rect.width;
            this.canvas.height = rect.height;

            this.userId = getUserId();
            this.username = getUsername();
            this.taskId = getTaskId();
            this.isUserCanLabel = isUserCanLabel();
            //获得这个任务的图片数目
            const _this = this;
            axios.get("/markLabelBL/getTaskImageNumber", { params: { taskId: this.taskId } }).then(function (response) {
                _this.taskImageNum = response.data;
            });
            //获得第一张图片
            this.getLabel();
        });
    },
    methods: {
        //获得当前图片的标注记录
        getLabel: function () {
            const _this = this;
            axios.get("/markLabelBL/getLabel", { params:
                    { taskId: _this.taskId, userId: _this.userId,
                        labelType: _this.labelType, imageIndex: this.currentLabelIndex,} })
                .then(function (response) {
                    _this.currentImageUrl = 'url(' + '/getTaskImage/' + _this.taskId + '/' + response.data.image + ')';
                    _this.currentFrameLabelList = response.data.labelList;
                    _this.removeRecInCanvas();
                });
        },
        //重置当前图片的标注记录
        resetCurrentLabel: function () {
            this.currentFrameLabelList = [];

            this.topLeftX = 0;
            this.topLeftY = 0;
            this.currentStartX = 0;
            this.currentStartY = 0;
            this.currentEndX = 0;
            this.currentEndY = 0;

            this.isDrawing = false;
            this.canDraw = true;
            this.canInputTag = false;

            this.removeRecInCanvas();
        },
        //保存当前图片的标注记录
        saveCurrentLabel: function () {
            if(this.canInputTag === false){
                let frameLabelVO = new FrameLabelVO(this.currentFrameLabelList);
                let frameLabelVOJson = JSON.stringify(frameLabelVO);
                const _this = this;
                axios.get("/markLabelBL/saveLabel", { params:
                        { taskId: _this.taskId, userId: _this.userId,
                            labelType: _this.labelType, imageIndex: _this.currentLabelIndex,
                            labelVOJson: frameLabelVOJson } })
                    .then(function (response) {
                        result = response.data;
                        if(result === true) {
                            alert("保存成功");
                        }else{
                            alert("保存失败");
                        }
                    });
            }else {
                alert("请输入标签");
            }
        },
        //转到前一张图片
        getPreviousLabel: function () {
            //第一张图片时没有前一张图片
            if(this.currentLabelIndex > 0){
                this.currentImageUrl = "";
                this.resetCurrentLabel();
                this.currentLabelIndex--;
                this.getLabel();
            }else{
                alert("当前是第一张图片");
            }
        },
        //转到后一张图片
        getNextLabel: function () {
            //最后一张图片时没有后一张图片
            if(this.currentLabelIndex < (this.taskImageNum - 1)){
                this.currentImageUrl = "";
                this.resetCurrentLabel();
                this.currentLabelIndex++;
                this.getLabel();
            }else{
                alert("当前是最后一张图片");
            }
        },
        //提交任务
        setTaskAccomplished: function () {
            //提交任务
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

            if (this.canInputTag === true){
                //加到数组中
                let currentWidth = Math.abs(this.currentEndX - this.currentStartX);
                let currentLength = Math.abs(this.currentEndY - this.currentStartY);
                let temp = new FrameLabelListItem(this.topLeftX, this.topLeftY, currentWidth, currentLength, tag);
                this.currentFrameLabelList.push(temp);

                this.canInputTag = false;
                this.canDraw = true;
            }else{
                alert("请标注区域");
            }
        },
        removeTag: function (tagIndex) {
            this.currentFrameLabelList.splice(tagIndex, 1);
            this.removeRecInCanvas();
        },
        //将画布上的矩形标框移除
        removeRecInCanvas: function () {
            this.canvasContext.clearRect(0, 0, this.canvas.width, this.canvas.height);
            const _this = this;
            this.currentFrameLabelList.forEach(function (currentValue, index) {
                _this.canvasContext.strokeRect(currentValue.startX, currentValue.startY, currentValue.width, currentValue.height);
            });
        },
        //画板
        startDrawing: function (ev) {
            if(this.isDrawing === false && this.canDraw === true){
                this.isDrawing = true;
                this.currentStartX = this.getX(ev);
                this.currentStartY = this.getY(ev);
                this.canvasContext.moveTo(this.currentStartX, this.currentStartY);
                this.tempImageData = this.canvasContext.getImageData(0, 0, this.canvas.width, this.canvas.height);
            }
        },
        stopDrawing: function () {
            //防止鼠标只是经过canvas
            if(this.isDrawing === true){
                this.tempImageData = this.canvasContext.getImageData(0, 0, this.canvas.width, this.canvas.height);
                this.isDrawing = false;
                //要求添加标签内容，不允许再画
                this.canDraw = false;
                this.canInputTag = true;
            }
        },
        draw: function (ev) {
            if(this.isDrawing === true){
                this.canvasContext.putImageData(this.tempImageData, 0, 0);

                this.currentEndX = this.getX(ev);
                this.currentEndY = this.getY(ev);

                this.topLeftX = (this.currentEndX < this.currentStartX)? this.currentEndX: this.currentStartX;
                this.topLeftY = (this.currentEndY < this.currentStartY)? this.currentEndY: this.currentStartY;
                let currentWidth = Math.abs(this.currentEndX - this.currentStartX);
                let currentLength = Math.abs(this.currentEndY - this.currentStartY);

                this.canvasContext.strokeRect(this.topLeftX, this.topLeftY, currentWidth, currentLength);
            }
        },
        //获取坐标
        getX: function (ev) {
            const rect = this.$refs.canvas.getBoundingClientRect();
            return ev.clientX - rect.left;
        },
        getY: function (ev) {
            const rect = this.$refs.canvas.getBoundingClientRect();
            return ev.clientY - rect.top;
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
                return this.labelList[this.currentLabelIndex].frameList;
            }else{
                return [];
            }
        }
    }
});