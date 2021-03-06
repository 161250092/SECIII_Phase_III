new Vue({
    el: "#loginContainer",
    data:{
        username: "",
        password: ""
    },
    methods:{
        login:function () {
            const _this = this;
            axios.get("/login/login", { params:{ username: this.username, password: this.password } }).then(function (userException) {
                let wrongMessage = userException.data.wrongMessage.type;
                let userId = userException.data.userId;
                if(wrongMessage === 'RequestorLogin'){
                    sendUserId(userId);
                    sendUsername(_this.username);
                    sendUserType(USERTYPE_REQUESTOR);
                    jumpToAnotherPage(requestorMainPageUrl);
                }else if(wrongMessage === 'WorkerLogin') {
                    sendUserId(userId);
                    sendUsername(_this.username);
                    sendUserType(USERTYPE_WORKER);
                    jumpToAnotherPage(workerMainPageUrl);
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