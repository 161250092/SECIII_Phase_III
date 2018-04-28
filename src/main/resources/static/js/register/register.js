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
                axios.get("/register", { params:{ username: this.username, password: this.password } }).then(function (userException) {
                    let wrongMessage = userException.data.wrongMessage.type;
                    let userId = userException.data.userId;
                    if(wrongMessage === 'SuccessfulRegister'){
                        sendUserId(userId);
                        sendUsername(_this.username);
                        jumpToAnotherPage(mainPageUrl);
                    }else{
                        alert("注册失败");
                        _this.password = "";
                        _this.confirmedPassword = "";
                    }
                });
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