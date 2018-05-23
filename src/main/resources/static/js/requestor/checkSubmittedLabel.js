new Vue({
    el: '#taskInfoContainer',
    data: {
        submittedLabelListOfAllTask: [],
        selectedTask: -1,
    },
    mounted: function () {
        this.$nextTick(function () {
            const _this = this;
            axios.get('/test.json').then(function (response) {
                _this.submittedLabelListOfAllTask = response.data.list;
            });
        });
    },
    methods: {
        checkThisLabel: function (workerIndex) {
            alert(workerIndex)
        },
        passThisLabel: function (workerIndex) {
            alert(workerIndex)
        },
        rejectThisLabel: function (workerIndex) {
            alert(workerIndex)
        },
        abandonThisLabel: function (workerIndex) {
            alert(workerIndex)
        },
        setSelectedTaskIndex: function (value) {
            if(this.selectedTask === value){
                this.selectedTask = -1;
            }else{
                this.selectedTask = value;
            }
        },
        chineseLabelTypeName: function (labelType) {
            switch (labelType){
                case "ImageLabel": return "整体标注";
                case "FrameLabel": return "标框标注";
                case "AreaLabel": return "区域标注";
                default:
                    alert("标注类型错误");
                    break;
            }
        }
    },
});