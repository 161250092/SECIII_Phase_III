document.write("<script language=javascript src='../../js/pageJump.js'></script>");
document.write("<script language=javascript src='../../js/user/user.js'></script>");

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
            axios.get("/RegisterController/isExist", { params:{ userName: _this.userName} }).then(function (response) {
                _this.isUserNameExisted = response.data;
            });
        },
        checkPassword: function () {
            this.isPasswordCorrect = (this.password === this.confirmedPassword);
        },
        register: function () {
            const _this = this;
            if(!this.isUserNameExisted && this.isPasswordCorrect){
                axios.get("/RegisterController/register", { params:{ userName: _this.userName, password: _this.password } }).then(function (response) {
                    if(response.data === true){
                        /**
                         * 未完成
                         */
                        jumpToAnotherPage(mainPageUrl, "testID");
                    } else{
                        alert("注册失败");
                        _this.password = "";
                        _this.confirmedPassword = "";
                    }
                });
            }
        }
    }
});