new Vue({
    el:"#taskInfoContainer",
    data:{
        userId: "",
        AllUnfinishedTasks:[],
        IMAGE_LABEL_TYPE: "ImageLabel",
        FRAME_LABEL_TYPE: "FrameLabel",
        AREA_LABEL_TYPE: "AreaLabel"
    },
    mounted: function () {
        const _this = this;
        this.$nextTick(function () {
            _this.userId = getUserId();
            axios.get("/requestor/getAssignedButIncompleteTaskList",
                { params:{ userId: this.userId } })
                .then(function (response) {
                    _this.AllUnfinishedTasks = response.data;
                })
        })
    },
    methods:{
        getChineseLabelType: function (labelType) {
            if(labelType === this.IMAGE_LABEL_TYPE){
                return "整体标注";
            }else if(labelType === this.FRAME_LABEL_TYPE){
                return "标框标注";
            }else if(labelType === this.AREA_LABEL_TYPE){
                return "区域标注";
            }
        }
    }

});