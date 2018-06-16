//标框类集合VO
function FrameLabelVOSet(taskImageNum, labelList, filenameList) {
    this.taskImageNum = taskImageNum;
    this.labelList = labelList;
    this.filenameList = filenameList;
}
function FrameLabel(frameList) {
    this.frameList = frameList;
}
//框中的标签类
function Frame(startX, startY, width, height, tag){
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

        hasSavedChanges: true,
        isUserCanLabel: true,
        isWorker: true,

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

            this.userId = getWorkerIdWhenLabel();
            this.username = getUsername();
            this.taskId = getTaskId();
            this.isUserCanLabel = isUserCanLabel();
            //获得标注
            this.getLabel();
        });
    },
    methods: {
        //获得当前图片的标注记录
        getLabel: function () {
            const _this = this;
            axios.get("/markFrameLabel/getFrameLabel", { params: {taskId: _this.taskId, userId: _this.userId} }).then(function (response) {
                _this.taskImageNum = response.data.taskImageNum;
                _this.labelList = response.data.labelList;
                if (_this.labelList.length === 0){
                    _this.labelList.push(new FrameLabel([]));
                }
                _this.filenameList = response.data.filenameList;
                _this.paintFrameOnCanvas();
            });
        },
        //重置当前图片的标注记录
        resetCurrentLabel: function () {
            this.labelList[this.currentLabelIndex].frameList = [];

            this.topLeftX = 0;
            this.topLeftY = 0;
            this.currentStartX = 0;
            this.currentStartY = 0;
            this.currentEndX = 0;
            this.currentEndY = 0;

            this.isDrawing = false;
            this.canDraw = true;
            this.canInputTag = false;

            this.paintFrameOnCanvas();

            this.hasSavedChanges = false;
        },
        //保存当前图片的标注记录
        saveLabelList: function () {
            if(this.canInputTag === false && this.hasSavedChanges === false){
                let frameLabelVO = new FrameLabelVOSet(this.taskImageNum, this.labelList, this.filenameList);
                let frameLabelVOJson = JSON.stringify(frameLabelVO);
                const _this = this;
                axios.get("/markFrameLabel/saveFrameLabel", { params: { taskId: _this.taskId, userId: _this.userId,
                        frameLabelVOSetJSON: frameLabelVOJson, isWorker: _this.isWorker } }).then(function (response) {
                            let result = response.data;
                            if(result === true) {
                                alert("保存成功");
                                _this.hasSavedChanges = true;
                            }else{
                                alert("保存失败");
                            }
                        });
            }else if(this.canInputTag === true){
                alert("请输入标签");
            }else {
                alert("没有变更");
            }
        },
        //转到前一张图片
        previousLabel: function () {
            //第一张图片时没有前一张图片
            if(this.currentLabelIndex > 0){
                this.currentLabelIndex--;
                this.paintFrameOnCanvas();
            }else{
                alert("当前是第一张图片");
            }
        },
        //转到后一张图片n
        nextLabel: function () {
            //最后一张图片时没有后一张图片
            if(this.currentLabelIndex < (this.taskImageNum - 1)){
                this.currentLabelIndex++;
                if (this.labelList.length < this.currentLabelIndex + 1){
                    this.labelList.push(new FrameLabel([]));
                }
                this.paintFrameOnCanvas();
            }else{
                alert("当前是最后一张图片");
            }
        },
        //提交任务
        setTaskAccomplished: function () {
            if(this.isAllLabel() === false){
                alert("有图片未标注");
                return;
            }

            if(this.hasSavedChanges === true){
                //提交任务
                const _this = this;
                axios.get("/markFrameLabel/setTaskAccomplished", { params: { taskId: _this.taskId, userId: _this.userId } }).then(function (response) {
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

            if (this.canInputTag === true){
                //加到数组中
                let currentWidth = Math.abs(this.currentEndX - this.currentStartX);
                let currentLength = Math.abs(this.currentEndY - this.currentStartY);
                let temp = new Frame(this.topLeftX, this.topLeftY, currentWidth, currentLength, tag);
                this.labelList[this.currentLabelIndex].frameList.push(temp);

                this.canInputTag = false;
                this.canDraw = true;

                this.hasSavedChanges = false;
            }else{
                alert("请标注区域");
            }
        },
        removeTag: function (tagIndex) {
            this.labelList[this.currentLabelIndex].frameList.splice(tagIndex, 1);
            this.paintFrameOnCanvas();

            this.hasSavedChanges = false;
        },
        //将画布上的矩形标框移除
        paintFrameOnCanvas: function () {
            this.canvasContext.clearRect(0, 0, this.canvas.width, this.canvas.height);
            const _this = this;
            this.labelList[this.currentLabelIndex].frameList.forEach(function (currentFrame, index) {
                _this.canvasContext.strokeRect(currentFrame.startX, currentFrame.startY, currentFrame.width, currentFrame.height);
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
        },
        //判断是否标注了全部图片
        isAllLabel: function () {
            for(let i = 0; i < this.labelList.length; i++){
                if(this.labelList[i].frameList.length === 0){
                    return false;
                }
            }
            return true;
        },
        returnToMainPage: function () {
            let userType = getUserType();
            leaveTheTask();
            if(userType === USERTYPE_WORKER){
                jumpToAnotherPage(workerMainPageUrl);
            }else if(userType === USERTYPE_REQUESTOR){
                jumpToAnotherPage(requestorMainPageUrl);
            }
        }
    },
    computed:{
        currentImageUrl: function () {
            if(this.filenameList.length > 0){
                return 'url(/image/getTaskImage/' + this.taskId + '/' + this.filenameList[this.currentLabelIndex] + ')';
            }else{
                return 'url(/image/getTaskImage/errorTaskImage.jpg)';
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