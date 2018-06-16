function TaskVO(taskId, taskType, labelType, taskDescription, requiredWorkerNum, imageNum, taskPrice) {
    this.taskId = taskId;
    this.taskType = taskType;
    this.labelType = labelType;
    this.taskDescription = taskDescription;
    this.acceptedWorkerNum = 0;
    this.finishedWorkerNum = 0;
    this.requiredWorkerNum = requiredWorkerNum;
    this.imageNum = imageNum;
    this.taskPrice = taskPrice;
    this.publishedTaskState = "DRAFT_WITHOUT_SAMPLE";
}

new Vue({
    el:"#inputTaskInfo",
    data:{
        userId: "",

        labelTypeList:[
            {labelType: "ImageLabel", labelTypeName: "整体标注"},
            {labelType: "FrameLabel", labelTypeName: "方框标注"},
            {labelType: "AreaLabel", labelTypeName: "区域标注"}
        ],
        selectedLabelType: "ImageLabel",

        taskTypeList: [
            {taskType: "ORDINARY_LEVEL_LABEL_REQUIRED", taskTypeName: "普通标注质量"},
            {taskType: "HIGH_LEVEL_LABEL_REQUIRED", taskTypeName: "高等级标注质量"},
            {taskType: "VERY_HIGH_LEVEL_LABEL_REQUIRED", taskTypeName: "最高等级标注质量"},
        ],
        selectedTaskType: "ORDINARY_LEVEL_LABEL_REQUIRED",

        taskId: "",
        requiredWorkerNum: 1,
        taskDescription: "",

        minImageUnitPriceList: [],
        imageUnitPrice: 0,
        
        imgList: [],
        size: 0,
    },
    mounted: function () {
        this.$nextTick(function () {
            this.userId = getUserId();
            this.refreshTaskId();
            this.getMinImageUnitPriceList();
        });
    },
    methods:{
        getMinImageUnitPriceList: function () {
            let _this = this;
            axios.get('/requestor/getTaskUnitPriceList').then(function (response) {
                _this.minImageUnitPriceList = response.data;
                _this.imageUnitPrice = _this.getMinUnitPrice();
            });
        },
        submitTaskDraft: function (ev) {
            this.uploadImage(ev);

            let imageFileNameList = [];
            for (let i = 0; i < this.imgList.length; i++){
                imageFileNameList.push(this.imgList[i].name);
            }

            let taskPrice = this.getMinUnitPrice() * this.imgList.length;
            let taskVO = new TaskVO(this.taskId, this.selectedTaskType, this.selectedLabelType,
                this.taskDescription, this.requiredWorkerNum, imageFileNameList.length, taskPrice);
            let taskVOJson = JSON.stringify(taskVO);
            let imageFilenameListJSON = JSON.stringify(imageFileNameList);

            let _this = this;
            axios.get('/requestor/submitTaskDraft', {params: {publishedTaskVOJSON: taskVOJson, imageFilenameListJSON:imageFilenameListJSON}}).then(function (response) {
                if(response.data.wrongMessage.type === "Success"){
                    alert("保存成功，请标注样本");
                    jumpToAnotherPage(assignTaskPageUrl);
                }else{
                    alert("保存失败");
                }
            });
            this.refreshTaskId();
        },
        uploadImage: function (ev) {
            ev.preventDefault();
            let formData = new FormData();
            formData.append('taskId', this.taskId);
            for(let i = 0; i < this.imgList.length; i++){
                formData.append('fileList', this.imgList[i]);
            }
            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            };
            axios.post('/image/uploadTaskImage', formData, config).then(function (res) {

            });
        },
        fileClick() {
            document.getElementById('upload_file').click();
        },
        fileChange(el) {
            if (!el.target.files[0].size) return;
            this.fileList(el.target);
            el.target.value = ''
        },
        fileList(fileList) {
            let files = fileList.files;
            for (let i = 0; i < files.length; i++) {
                //判断是否为文件夹
                if (files[i].type !== '') {
                    this.fileAdd(files[i]);
                } else {
                    //文件夹处理
                    this.folders(fileList.items[i]);
                }
            }
        },
        //文件夹处理
        folders(files) {
            let _this = this;
            //判断是否为原生file
            if (files.kind) {
                files = files.webkitGetAsEntry();
            }
            files.createReader().readEntries(function (file) {
                for (let i = 0; i < file.length; i++) {
                    if (file[i].isFile) {
                        _this.foldersAdd(file[i]);
                    } else {
                        _this.folders(file[i]);
                    }
                }
            })
        },
        foldersAdd(entry) {
            let _this = this;
            entry.file(function (file) {
                _this.fileAdd(file)
            })
        },
        fileAdd(file) {
            //判断是否为图片文件
            if (file.type.indexOf('image') === -1) {
                alert("不是图片文件");
            } else {
                //总大小
                this.size = this.size + file.size;
                let reader = new FileReader();
                reader.vue = this;
                reader.readAsDataURL(file);
                reader.onload = function () {
                    file.src = this.result;
                    this.vue.imgList.push(file);
                }
            }
        },
        fileDel(index) {
            this.size = this.size - this.imgList[index].size;//总大小
            this.imgList.splice(index, 1);
        },
        bytesToSize(bytes) {
            if (bytes === 0) return '0 B';
            let k = 1000, // or 1024
                sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
                i = Math.floor(Math.log(bytes) / Math.log(k));
            return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
        },
        dragenter(el) {
            el.stopPropagation();
            el.preventDefault();
        },
        dragover(el) {
            el.stopPropagation();
            el.preventDefault();
        },
        drop(el) {
            el.stopPropagation();
            el.preventDefault();
            this.fileList(el.dataTransfer);
        },
        refreshTaskId: function () {
            let time = Date.parse(new Date());
            this.taskId = this.userId + '_' + this.selectedLabelType +
                '_' + time;
        },
        getMinUnitPrice: function() {
            let imageNum = this.imgList.length;
            let minImageUnitPrice;

            if(this.minImageUnitPriceList.length === 0) {
                minImageUnitPrice = 0;
            }else {
                switch (this.selectedTaskType) {
                    case "ORDINARY_LEVEL_LABEL_REQUIRED": minImageUnitPrice = this.minImageUnitPriceList.ORDINARY_LEVEL_LABEL_REQUIRED.value; break;
                    case "HIGH_LEVEL_LABEL_REQUIRED": minImageUnitPrice = this.minImageUnitPriceList.HIGH_LEVEL_LABEL_REQUIRED.value; break;
                    case "VERY_HIGH_LEVEL_LABEL_REQUIRED": minImageUnitPrice = this.minImageUnitPriceList.VERY_HIGH_LEVEL_LABEL_REQUIRED.value; break;
                    default : minImageUnitPrice = -1; break;
                }
            }

            return minImageUnitPrice;
        }
    },
    watch: {
        selectedLabelType: function (newSelectedLabelType, oldSelectedLabelType) {
            this.refreshTaskId();
        },
        requiredWorkerNum: function (newRequiredWorkerNum, oldRequiredWorkerNum) {
            if(newRequiredWorkerNum <= 0){
                this.requiredWorkerNum = 1;
            }
        },
        imgList: function (newImgList, oldImgList) {
            this.imageUnitPrice = this.getMinUnitPrice();
        },
        selectedTaskType: function (newSelectedTaskType, oldSelectedTaskType) {
            this.imageUnitPrice = this.getMinUnitPrice();
        },
        imageUnitPrice: function (newTaskUnitPrice, oldTaskUnitPrice) {
            if(newTaskUnitPrice > this.getMinUnitPrice()){
                this.imageUnitPrice = newTaskUnitPrice;
            }else {
                this.imageUnitPrice = this.getMinUnitPrice();
            }
        }
    },
    computed: {
        taskPrice: function () {
            return this.imageUnitPrice * this.imgList.length * this.requiredWorkerNum;
        }
    }
});