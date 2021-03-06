new Vue({
    el: "#taskInfoContainer",
    data:{
        userId:"",
        username:"",
        AllFinishedTasks:[]
    },

    mounted: function () {
        const _this = this;

        this.$nextTick(function () {
            _this.username = getUsername();
            _this.userId = getUserId();
            axios.get("/worker/getAcceptedAndAccomplishedTaskList",
                { params:{ userId: this.userId } })
                .then(function (response) {
                    _this.AllFinishedTasks = response.data;
                })
        })
    },
    methods: {
        getChineseLabelType: function (labelType) {
            if (labelType === IMAGE_LABEL_TYPENAME) {
                return "整体标注";
            } else if (labelType === FRAME_LABEL_TYPENAME) {
                return "标框标注";
            } else if (labelType === AREA_LABEL_TYPENAME) {
                return "区域标注";
            }
        }
    }
});


new Vue({
    el: "#home",
    data: {
        username: ""
    },
    mounted: function () {
        this.$nextTick(function () {
            this.username = getUsername();
        });
    }
});