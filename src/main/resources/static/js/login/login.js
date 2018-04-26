new Vue({
    el: "#loginContainer",
    data:{
        userName: "",
        password: ""
    },
    methods:{
        login:function () {
            const _this = this;
            axios.all([this.loginAxios(), this.getUserIdAxios()]).then(axios.spread(function (loginException, userIdObj) {
                let wrongMessage = loginException.data.wrongMessage.type;
                if(wrongMessage === 'OrdinaryLogin'){
                    sendUserId(userIdObj.data);
                    jumpToAnotherPage(mainPageUrl);
                }else if(wrongMessage === 'AdministerLogin'){
                    sendUserId(userIdObj.data);
                    jumpToAnotherPage(adminPageUrl);
                }else{
                    alert("登录失败");
                }
            }));
        },
        loginAxios: function () {
            return axios.get("/login", { params:{ userName: this.userName, password: this.password } });
        },
        getUserIdAxios: function () {
            return axios.get("/getUserId", {params: {userName: this.userName}});
        }
    }
});