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
        
        isUsernameInput: true,
        isPasswordInput: true,
        isEmailAddressInput: true,
        isPhoneNumberInput: true,
    },
    methods:{
        register: function () {
            const _this = this;
            if(!this.isUsernameExisted && this.isPasswordCorrect && this.checkAllInput()){
                axios.get("/register/register",
                    {params:{username: this.username, password: this.password, email: this.emailAddress, phone: this.phoneNumber}}).then(function (userException) {
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
            this.isUsernameInput = this.username !== "";
            this.isEmailAddressInput = this.emailAddress !== "";
            this.isPhoneNumberInput = this.phoneNumber !== "";
            this.isPasswordInput = this.password !== "";

            return this.isUsernameInput && this.isEmailAddressInput && this.isPhoneNumberInput && this.isPasswordInput;
        }
    }
});