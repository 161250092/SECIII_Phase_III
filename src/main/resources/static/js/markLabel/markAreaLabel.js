//区域标注类集合VO
function AreaLabelSetVO(taskImageNum, labelList, filenameList) {
    this.taskImageNum = taskImageNum;
    this.labelList = labelList;
    this.filenameList = filenameList;
}
function Area(tag, areaBorder) {
    this.tag = tag;
    this.areaBorder = areaBorder;
}
//线中的像素点类
function Pixel(x, y){
    this.x = x;
    this.y = y;
}

new Vue({
    el: "#markLabelContainer",
    data: {
        userId: "",
        username: "",
        
        labelType: "AreaLabel",
        taskId: "",
        taskImageNum: 0,
        labelList: [],
        filenameList: [],

        currentLabelIndex: 0,
        currentAreaBorder: [],

        hasSavedChanges: true,
        isUserCanLabel: true,

        canvas: undefined,
        canvasContext: undefined,
        previousX: 0,
        previousY: 0,

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
            //获得标注
            this.getLabel();
        });
    },
    methods: {
        getLabel: function () {
            const _this = this;
            axios.get("/markAreaLabel/getAreaLabel", { params: { taskId: _this.taskId, userId: _this.userId} }).then(function (response) {
                _this.taskImageNum = response.data.taskImageNum;
                _this.labelList = response.data.labelList;
                _this.filenameList = response.data.filenameList;
                _this.paintAreaBorderOnCanvas();
            });
        },
        resetCurrentLabel: function () {
            this.labelList[this.currentLabelIndex].areaList = [];

            this.currentAreaBorder = [];

            this.isDrawing = false;
            this.canDraw = true;
            this.canInputTag = false;

            this.paintAreaBorderOnCanvas();

            this.hasSavedChanges = false;
        },
        saveLabelList: function () {
            if(this.canInputTag === false && this.hasSavedChanges === false){
                let areaLabelSetVO = new AreaLabelSetVO(this.taskImageNum, this.labelList, this.filenameList);
                let areaLabelSetVOJson = JSON.stringify(areaLabelSetVO);
                const _this = this;
                axios.get("/markAreaLabel/saveAreaLabel", { params: {taskId: _this.taskId, userId: _this.userId,
                        areaLabelVOSetJSON: areaLabelSetVOJson, isWorker: _this.isWorker} })
                    .then(function (response) {
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
                this.currentAreaBorder = [];
                this.currentLabelIndex--;
                this.paintAreaBorderOnCanvas();
            }else{
                alert("当前是第一张图片");
            }
        },
        //转到后一张图片
        nextLabel: function () {
            //最后一张图片时没有后一张图片
            if(this.currentLabelIndex < (this.taskImageNum - 1)){
                this.currentAreaBorder = [];
                this.currentLabelIndex++;
                this.paintAreaBorderOnCanvas();
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
                axios.get("/markAreaLabel/setTaskAccomplished", { params: {taskId: _this.taskId, userId: _this.userId, isWorker: _this.isWorker} })
                    .then(function (response) {
                        if(response.data === true){
                            alert("提交成功");
                            jumpToAnotherPage(mainPageUrl);
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

            if (this.canInputTag === true){
                let temp = new Area(tag, this.currentAreaBorder);
                this.labelList[this.currentLabelIndex].areaList.push(temp);
                
                this.canInputTag = false;
                this.canDraw = true;

                this.hasSavedChanges = false;
            }else{
                alert("请标注区域");
            }
        },
        removeTag: function (tagIndex) {
            this.labelList[this.currentLabelIndex].areaList.splice(tagIndex, 1);
            this.paintAreaBorderOnCanvas();

            this.hasSavedChanges = false;
        },
        //将画布上的曲线移除
        paintAreaBorderOnCanvas: function () {
            this.canvasContext.clearRect(0, 0, this.canvas.width, this.canvas.height);
            const _this = this;
            this.labelList[this.currentLabelIndex].areaList.forEach(function (currentArea, index) {
                let currentAreaBorder = currentArea.areaBorder;
                if(currentAreaBorder.length > 0){
                    //开始作图
                    _this.canvasContext.beginPath();
                    _this.canvasContext.moveTo(currentAreaBorder[0].x, currentAreaBorder[0].y);
                    //逐点画线
                    for(let i = 1; i < currentAreaBorder.length; i++){
                        _this.canvasContext.lineTo(currentAreaBorder[i].x, currentAreaBorder[i].y);
                        _this.canvasContext.stroke();
                    }
                    _this.canvasContext.closePath();
                }
            });

        },
        //画板
        startDrawing: function (ev) {
            if(this.isDrawing === false && this.canDraw === true){
                this.canvasContext.beginPath();

                this.isDrawing = true;
                this.previousX = this.getX(ev);
                this.previousY = this.getY(ev);
                this.canvasContext.moveTo(this.previousX, this.previousY);
                //this.tempImageData = this.canvasContext.getImageData(0, 0, this.canvas.width, this.canvas.height);
            }
        },
        stopDrawing: function () {
            //防止鼠标只是经过canvas
            if(this.isDrawing === true){
                //this.tempImageData = this.canvasContext.getImageData(0, 0, this.canvas.width, this.canvas.height);
                this.canvasContext.closePath();

                this.isDrawing = false;
                //要求添加标签内容，不允许再画
                this.canDraw = false;
                this.canInputTag = true;
            }
        },
        draw: function (ev) {
            if(this.isDrawing === true){
                let currentStartX = this.getX(ev);
                let currentStartY = this.getY(ev);

                if(Math.abs(this.previousX - currentStartX) >= 15 || Math.abs(this.previousY - currentStartY) >= 15){
                    this.canvasContext.lineTo(currentStartX, currentStartY);
                    this.canvasContext.stroke();

                    this.currentAreaBorder.push(new Pixel(currentStartX, currentStartY));

                    this.previousX = currentStartX;
                    this.previousY = currentStartY;
                }
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
                if(this.labelList[i].areaList.length === 0){
                    return false;
                }
            }
            return true;
        },
        returnToMainPage: function () {
            removeTaskId();
            let userType = getUserType();
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
                return this.labelList[this.currentLabelIndex].areaList;
            }else{
                return [];
            }
        }
    }
});