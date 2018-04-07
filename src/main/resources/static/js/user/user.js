function getUserId(userName) {
    var userId;

    $.ajax({
        async: false,
        type: "GET",
        url: "/UserController/getUserId",
        data: {
            userName: userName
        },

        success:function (result) {
            userId = result;
        }
    });

    return userId;
}