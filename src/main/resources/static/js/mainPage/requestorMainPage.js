new Vue({
    el:"#mainPage",
    data: {
        userId:"",
        username:""
    },
    mounted: function () {
            const _this = this;
            this.$nextTick(function () {
                _this.userId = getUserId();
                axios.get("/user/getUserInfo",
                    {params: {userId: getUserId()}})
                    .then(function (response) {
                        _this.username = response.data.username.value;
                        sendUsername(_this.username);
                    })
            })

        }

});