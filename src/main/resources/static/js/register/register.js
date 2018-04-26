new Vue({
    el:"#registerContainer",
    data:{
        userName:"",
        password:"",
        confirmedPassword:"",
        isUserNameExisted: false,
        isPasswordCorrect: true,
    },
    methods:{
        checkUserName: function () {
            const _this = this;
            axios.get("/isUserNameExist", { params:{ userName: _this.userName} }).then(function (response) {
                _this.isUserNameExisted = response.data;
            });
        },
        checkPassword: function () {
            this.isPasswordCorrect = (this.password === this.confirmedPassword);
        },
        register: function () {
            const _this = this;
            if(!this.isUserNameExisted && this.isPasswordCorrect){
                axios.all([this.registerAxios(), this.getUserIdAxios()]).then(axios.spread(function (isSuccessObj, userIdObj) {
                    if(isSuccessObj.data === true){
                        sendUserId(userIdObj.data);
                        jumpToAnotherPage(mainPageUrl);
                    } else{
                        alert("注册失败");
                        _this.password = "";
                        _this.confirmedPassword = "";
                    }
                }));
            }
        },
        registerAxios: function () {
            return axios.get("/register", { params:{ userName: this.userName, password: this.password } });
        },
        getUserIdAxios: function () {
            return axios.get("/getUserId", {params: {userName: this.userName}});
        }
    }
});