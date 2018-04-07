document.write("<script language=javascript src='js/pageJump.js'></script>");
document.write("<script language=javascript src='js/user/user.js'></script>");

var $userNameEl = $("#userName");
var $passwordEl = $("#password");
var $confPasswordEl = $("#conf-password");

var $userNameFeedbackEl = $("#userNameFeedback");
var $confPasswordFeedbackEl = $("#confPasswordFeedback");


$userNameEl.blur(function (ev) {
    var userName = $userNameEl.val();

    $.ajax({
        type: "GET",
        url: "/RegisterController/isExist" ,
        data: {
            userName: userName
        },

        success:function (result) {
            if(result === false){
                $userNameFeedbackEl.text("该用户名已存在");
            }
        }
    });
});

$confPasswordEl.blur(function (ev) {
    var password = $passwordEl.val();
    var confPassword = $confPasswordEl.val();

    if(password !== confPassword){
        $confPasswordFeedbackEl.text("密码不一致");
    }else{
        $confPasswordFeedbackEl.text("");
    }
});


$("#registerButton").click(function (ev) {
    var userName = $userNameEl.val();
    var password = $passwordEl.val();

    $.ajax({
        type: "GET",
        url: "/",
        data: {
            userId: userName,
            password: password
        },
        // data: loginForm,
        success:function (result) {
            if(result === true){
                var userId = getUserId(userName);
                jumpToAnotherPage(mainPageUrl, userId);
            }else {
                alert("注册失败");
            }
        }
    });
});