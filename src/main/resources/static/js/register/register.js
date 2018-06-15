new Vue({
    el:"#registerContainer",
    data:{
        username:"",
        password:"",
        confirmedPassword:"",
        emailAddress: "",
        phoneNumber: "",

        isUsernameExisted: false,
        isPasswordCorrect: true,
        gender:''
    },
    methods:{
        register: function () {
            const _this = this;
            if(!this.isUsernameExisted && this.isPasswordCorrect && this.checkAllInput()){
                let url;
                if(this.gender === 'worker')
                    url  = "/register/registerWorker";
                else if(this.gender === 'requestor')
                    url = "/register/registerRequestor";


                axios.get(url,
                    {params:{username: this.username, password: this.password, email: this.emailAddress, phone: this.phoneNumber}}).then(function (userException) {
                    let wrongMessage = userException.data.wrongMessage.type;
                    let userId = userException.data.userId;
                    if(wrongMessage === 'RegisterSuccess'){
                        sendUserId(userId);
                        sendUsername(_this.username);
                        if(_this.gender === 'worker'){
                            jumpToAnotherPage(workerMainPageUrl);
                        }else if (_this.gender === 'requestor'){
                            jumpToAnotherPage(requestorMainPageUrl);
                        }
                    }else{
                        alert("注册失败");
                        _this.password = "";
                        _this.confirmedPassword = "";
                    }
                });
            }else if (this.isUsernameExisted) {
                alert("该用户名已存在");
            }else if (!this.isPasswordCorrect) {
                alert("二次密码不相同");
            }else if (this.checkAllInput()) {
                alert("有信息未输入");
            }
        },
        registerAxios: function () {
            return axios.get("/register", { params:{ username: this.username, password: this.password } });
        },
        getUserIdAxios: function () {
            return axios.get("/getUserId", {params: {username: this.username}});
        },
        checkUsername: function () {
            const _this = this;
            axios.get("/register/isUsernameExist", { params:{ username: _this.username} }).then(function (response) {
                _this.isUsernameExisted = response.data;
            });
        },
        checkPassword: function () {
            this.isPasswordCorrect = (this.password === this.confirmedPassword);
        },
        checkAllInput: function () {
            return this.isUsernameInput && this.isEmailAddressInput && this.isPhoneNumberInput && this.isPasswordInput;
        }
    },
    computed:{
        isUsernameInput: function () {
            return this.username !== "";
        },
        isEmailAddressInput: function () {
            return this.emailAddress !== "";
        },
        isPhoneNumberInput: function () {
            return this.phoneNumber !== "";
        },
        isPasswordInput: function () {
            return this.password !== "";
        }
    }

});