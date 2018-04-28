new Vue({
    el: "#loginContainer",
    data:{
        username: "",
        password: ""
    },
    methods:{
        login:function () {
            const _this = this;
            axios.get("/login", { params:{ username: this.username, password: this.password } }).then(function (userException) {
                let wrongMessage = userException.data.wrongMessage.type;
                let userId = userException.data.userId;
                if(wrongMessage === 'OrdinaryLogin'){
                    sendUserId(userId);
                    sendUsername(_this.username);
                    jumpToAnotherPage(mainPageUrl);
                }else if(wrongMessage === 'AdministerLogin'){
                    sendUserId(userId);
                    sendUsername(_this.username);
                    jumpToAnotherPage(adminPageUrl);
                }else{
                    alert("登录失败");
                }
            });
        }
    }
});