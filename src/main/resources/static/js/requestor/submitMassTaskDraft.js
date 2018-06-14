new Vue({
    el:"#inputTaskInfo",
    data:{
        userId:"",
        selectedSolution:"",

        solutiontype1:true,
        solutiontype2:false,

        SolutionTypeList:[
            {solutionType:"maxTaskNum",solutionName:"最大化任务分配"},
            {solutionType:"leastPay",solutionName:"最小化支出"}
        ],

        labelTypeList:[
            {labelType: "ImageLabel", labelTypeName: "整体标注"},
            {labelType: "FrameLabel", labelTypeName: "方框标注"},
            {labelType: "AreaLabel", labelTypeName: "区域标注"}
        ],
        selectedLabelType: "",

        taskTypeList: [
            {taskType: "ORDINARY_LEVEL_LABEL_REQUIRED", taskTypeName: "普通标注质量"},
            {taskType: "HIGH_LEVEL_LABEL_REQUIRED", taskTypeName: "高等级标注质量"},
            {taskType: "VERY_HIGH_LEVEL_LABEL_REQUIRED", taskTypeName: "最高等级标注质量"},
        ],
        selectedTaskType: "",

        taskId: "",
        requiredWorkerNum: 1,
        taskDescription: "",


        imgList: [],
        size: 0,
        maxTaskNum_budget:0,
        leastPay_budget:0,
        leastPay_unitPrice:0.


    },
    mounted:function(){
        this.$nextTick(function () {
            this.selectedSolution = "maxTaskNum";
            this.selectedLabelType = "ImageLabel";
            this.selectedTaskType = "ORDINARY_LEVEL_LABEL_REQUIRED";

            this.userId = getUserId();
        });
    },

    methods: {

        getSolutionType1:function(){
            if(selectedSolution==="maxTaskNum")
                return true;
            else
                return false;
        },

        getSolutionType2:function(){
          if(selectedSolution==="leastPay")
              return true;
          else
              return false;
        },



        getMinScoreList: function () {
            let _this = this;
            axios.get('/requestor/getTaskUnitPriceList').then(function (response) {
                _this.minScoreList = response.data;
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

            if(this.minScoreList.length === 0) {
                minImageUnitPrice = 0;
            }else {
                switch (this.selectedTaskType) {
                    case "ORDINARY_LEVEL_LABEL_REQUIRED": minImageUnitPrice = this.minScoreList.ORDINARY_LEVEL_LABEL_REQUIRED.value; break;
                    case "HIGH_LEVEL_LABEL_REQUIRED": minImageUnitPrice = this.minScoreList.HIGH_LEVEL_LABEL_REQUIRED.value; break;
                    case "VERY_HIGH_LEVEL_LABEL_REQUIRED": minImageUnitPrice = this.minScoreList.VERY_HIGH_LEVEL_LABEL_REQUIRED.value; break;
                    default : minImageUnitPrice = -1; break;
                }
            }

            return minImageUnitPrice;
        }
    },
    watch: {

        selectedSolution:function(newselectedSolution,oldselectedSolution){
            if(newselectedSolution==="maxTaskNum")
            {
                this.solutiontype1=true;
                this.solutiontype2=false;
            }
            else{
                this.solutiontype1=false;
                this.solutiontype2=true;
            }


        },

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
            return this.imageUnitPrice * this.imgList.length;
        }
    }

});