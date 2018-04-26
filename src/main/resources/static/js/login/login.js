document.write("<script language=javascript src='../../js/pageJump.js'></script>");
//document.write("<script language=javascript src='../../js/user/user.js'></script>");

new Vue({
    el: "#loginContainer",
    data:{
        userName: "",
        password: ""
    },
    methods:{
        login:function () {
            const _this = this;
            axios.all([this.loginAxios(), this.getUserIdAxios()]).then(axios.spread(function (isLoginObj, userIdObj) {
                let isLogin = isLoginObj.data;
                let userId = userIdObj.data;
            }));
            // axios.get("/login", { params:{ userName: _this.userName, password: _this.password } }).then(function (response) {
            //     if(response.data === true){
            //         let userId;
            //         axios.get("/getUserId", {params: {userName: this.userName}}).then(function (response) {
            //             userId = response.data;
            //         });
            //         sendUserId(userId);
            //         jumpToAnotherPage(mainPageUrl);
            //     }else {
            //         alert("登录失败");
            //     }
            // });
        },
        loginAxios: function () {
            return axios.get("/login", { params:{ userName: this.userName, password: this.password } });
        },
        getUserIdAxios: function () {
            return axios.get("/getUserId", {params: {userName: this.userName}});
        }
    }
});