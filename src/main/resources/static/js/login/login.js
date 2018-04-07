$("#loginButton").click(function (ev) {
    var userId = document.getElementById("userId").value;
    var password = document.getElementById("password").value;
    // var loginForm = new FormData(document.getElementById("loginForm"));
    $.ajax({
        type: "GET",
        url: "/LoginController/login",
        data: {
            userId: userId,
            password: password
        },
        // data: loginForm,
        success:function (result) {
            alert(result);
        }
    });
});