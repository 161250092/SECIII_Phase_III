new Vue({
    el:"#registerContainer",
    data:{
        username:"",
        password:"",
        confirmedPassword:"",
        isUsernameExisted: false,
        isPasswordCorrect: true,
    },
    methods:{
        checkUsername: function () {
            const _this = this;
            axios.get("/isUsernameExist", { params:{ username: _this.username} }).then(function (response) {
                _this.isUsernameExisted = response.data;
            });
        },
        checkPassword: function () {
            this.isPasswordCorrect = (this.password === this.confirmedPassword);
        },
        register: function () {
            const _this = this;
            if(!this.isUsernameExisted && this.isPasswordCorrect){
                axios.all([this.registerAxios(), this.getUserIdAxios()]).then(axios.spread(function (isSuccessObj, userIdObj) {
                    if(isSuccessObj.data === true){
                        sendUserId(userIdObj.data);
                        sendUsername(_this.username);
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
            return axios.get("/register", { params:{ username: this.username, password: this.password } });
        },
        getUserIdAxios: function () {
            return axios.get("/getUserId", {params: {username: this.username}});
        }
    }
});