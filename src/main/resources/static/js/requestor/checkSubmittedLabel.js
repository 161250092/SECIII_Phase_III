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
    }
});