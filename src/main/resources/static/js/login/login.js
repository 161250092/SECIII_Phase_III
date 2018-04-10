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
            axios.get("/LoginController/loginBL", { params:{ userName: _this.userName, password: _this.password } }).then(function (response) {
                if(response.data === true){
                    /**
                     * 未完成
                     */
                    jumpToAnotherPage(mainPageUrl, "testID");
                }else {
                    alert("登录失败");
                }
            });
        },
    }
});





// $("#loginButton").click(function (ev) {
//     var userName = document.getElementById("userName").value;
//     var password = document.getElementById("password").value;
//     var userId = "";
//     // var loginForm = new FormData(document.getElementById("loginForm"));
//     $.ajax({
//         async: false,
//         type: "GET",
//         url: "/LoginController/loginBL",
//         data: {
//             userName: userName,
//             password: password
//         },
//         success:function (result) {
//             if(result === false){
//                 alert("登录失败");
//             }else{
//                 userId = getUserId(userName);
//                 jumpToAnotherPage(mainPageUrl, userId);
//             }
//         }
//     });
// });