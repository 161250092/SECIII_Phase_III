new Vue({
    el: "#taskInfoContainer",
    data:{
        userId:"",
    AllFinishedTasks:[]
    },

    mounted: function () {
        const _this = this;
        this.$nextTick(function () {
            _this.userId = getUserId();
            axios.get("/WorkerController/getAcceptedAndAccomplishedTaskList",
                { params:{ userId: this.userId } })
                .then(function (response) {
                    _this.AllFinishedTasks = response.data;
                })
        })
    },
    methods: {
        getChineseLabelType: function (labelType) {
            if (labelType === this.IMAGE_LABEL_TYPE) {
                return "整体标注";
            } else if (labelType === this.FRAME_LABEL_TYPE) {
                return "标框标注";
            } else if (labelType === this.AREA_LABEL_TYPE) {
                return "区域标注";
            }
        }
    }


});
