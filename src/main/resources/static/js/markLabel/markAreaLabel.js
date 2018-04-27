//区域标注类
function AreaLabelVO(label, lineList) {
    this.label = label;
    this.linelist = lineList;
}
//线中的像素点类
function Pixel(x, y){
    this.x = x;
    this.y = y;
}

new Vue({
    el: "#markLabelContainer",
    data: {
        labelType: "AreaLabel",
        userId: "",
        username: "",
        taskId: "",
        taskImageNum: 0,

        currentImageIndex: 0,
        currentImageUrl: "",
        currentTag: "",
        currentAreaList: [],

        canvas: undefined,
        canvasContext: undefined,
        //tempImageData: undefined,

        isDrawing: false,
        canDraw: true,
        canInputTag: false,
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

            this.canvas = this.$refs.canvas;
            this.canvasContext = this.canvas.getContext('2d');
            const rect = this.canvas.getBoundingClientRect();
            this.canvas.width = rect.width;
            this.canvas.height = rect.height;

            this.userId = getUserId();
            this.username = getUsername();
            this.taskId = getTaskId();
            //获得这个任务的图片数目
            const _this = this;
            axios.get("/markLabel/getTaskImageNumber", { params: { taskId: this.taskId } }).then(function (response) {
                _this.taskImageNum = response.data;
            });
            //获得第一张图片
            this.getLabel();
        });
    },
    methods: {
        getPixelListFromString: function (pixelListStr) {
            let tempArr;
            let pixelList = [];

            let pixelStrArray = pixelListStr.split(';');
            pixelStrArray = pixelStrArray.slice(0, pixelStrArray.length-1);

            const _this = this;
            pixelStrArray.forEach(function (currentPixelStr, index) {
                tempArr = currentPixelStr.split(',');
                pixelList.push(new Pixel(parseInt(tempArr[0]), parseInt(tempArr[1])));
            });
            return pixelList;
        },
        changePixelListIntoPixelString: function (pixelList) {
            let pixelListStr = "";
            pixelList.forEach(function (currentPixel, index) {
                pixelListStr = pixelListStr +
                    currentPixel.x + "," + currentPixel.y + ";";
            });
            return pixelListStr;
        },
        getLabel: function () {
            const _this = this;
            axios.get("/markLabel/getLabel", { params:
                    { taskId: _this.taskId, userId: _this.userId,
                        labelType: _this.labelType, imageIndex: this.currentImageIndex,} })
                .then(function (response) {
                    _this.currentImageUrl = 'url(' + '/getTaskImage/' + _this.taskId + '/' + response.data.image + ')';
                    _this.currentTag = response.data.label;
                    _this.currentAreaList = (_this.getPixelListFromString(response.data.lineList[0]));
                    _this.removeAreaInCanvas();
                });
        },
        resetCurrentLabel: function () {
            this.currentTag = "";
            this.currentAreaList = [];

            this.isDrawing = false;
            this.canDraw = true;
            this.canInputTag = false;

            this.removeAreaInCanvas();
        },
        saveCurrentLabel: function () {
            if(this.canInputTag === false){
                let currentAreaLabelVO = new AreaLabelVO(this.currentTag, this.changePixelListIntoPixelString(this.currentAreaList));
                let areaLabelVOJson = JSON.stringify(currentAreaLabelVO);
                const _this = this;
                axios.get("/markLabel/saveLabel", { params:
                        { taskId: _this.taskId, userId: _this.userId,
                            labelType: _this.labelType, imageIndex: _this.currentImageIndex,
                            labelVOJson: areaLabelVOJson } })
                    .then(function (response) {
                        result = response.data;
                        if(result === true) {
                            alert("保存成功");
                        }else{
                            alert("保存失败");
                        }
                    });
            }else{
                alert("请输入标签");
            }
        },
        //转到前一张图片
        getPreviousLabel: function () {
            //第一张图片时没有前一张图片
            if(this.currentImageIndex > 0){
                this.currentImageUrl = "";
                this.resetCurrentLabel();
                this.currentImageIndex--;
                this.getLabel();
            }else{
                alert("当前是第一张图片");
            }
        },
        //转到后一张图片
        getNextLabel: function () {
            //最后一张图片时没有后一张图片
            if(this.currentImageIndex < (this.taskImageNum - 1)){
                this.currentImageUrl = "";
                this.resetCurrentLabel();
                this.currentImageIndex++;
                this.getLabel();
            }else{
                alert("当前是最后一张图片");
            }
        },
        //提交任务
        setTaskAccomplished: function () {
            //提交任务
            const _this = this;
            axios.get("/markLabel/setTaskAccomplished", { params:
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
                this.currentTag = tag;
                this.canInputTag = false;
            }else{
                alert("请先标注区域");
            }
        },
        //将画布上的曲线移除
        removeAreaInCanvas: function () {
            this.canvasContext.clearRect(0, 0, this.canvas.width, this.canvas.height);
            if(this.currentAreaList.length > 0){
                let currentStartX = this.currentAreaList[0].x;
                let currentStartY = this.currentAreaList[0].y;
                //开始作图
                this.canvasContext.beginPath();
                this.canvasContext.moveTo(currentStartX, currentStartY);
                //逐点画线
                for(let i = 1; i < this.currentAreaList.length; i++){
                    currentStartX = this.currentAreaList[i].x;
                    currentStartY = this.currentAreaList[i].y;
                    this.canvasContext.lineTo(currentStartX, currentStartY);
                    this.canvasContext.stroke();
                }
                this.canvasContext.closePath();
            }
        },
        //画板
        startDrawing: function (ev) {
            if(this.isDrawing === false && this.canDraw === true){
                this.canvasContext.beginPath();

                this.isDrawing = true;
                let currentStartX = this.getX(ev);
                let currentStartY = this.getY(ev);
                this.canvasContext.moveTo(currentStartX, currentStartY);
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

                this.canvasContext.lineTo(currentStartX, currentStartY);
                this.canvasContext.stroke();

                this.currentAreaList.push(new Pixel(currentStartX, currentStartY));
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
    }
});