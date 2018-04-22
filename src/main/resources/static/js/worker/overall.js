//下拉框获取任务信息
var overall_user_Id;
var overall_task_Id;

window.onload=function(){
    var url = window.location.href;
    var temp = url.split("?");
    var infor = temp[1].split("&");


    overall_user_Id = infor[0];
    overall_task_Id = infor[1];
//图片最大数
    $.ajax({
        type : "POST",
        url : "/ImageBLImpl/getTaskImageNumber", //利用ajax发起请求，这里写servlet的路径

        data : {
            userId : user_Id
        },
        success: function(num) {
            maxN = num;

        },

        error: function () {
            alert("Wrong!");
        }
    });

    //图片信息
    $.ajax({
        type : "POST",
        url : "/ImageBLImpl/getImageAndLabelnfo", //利用ajax发起请求，这里写servlet的路径

        data : {
            //获得第一张图片，索引设为0
            imageIndex:0,
            taskId: overall_task_Id,
            userId : overall_user_Id

        },
        success: function(picture) {
            image.src = picture;
            index = 0;
        },

        error: function () {
            alert("Wrong!");
        }
    });

};


//改变图片源
$("#next").click(function(){
    //索引值+1
    index++;

    $.ajax({
        type : "POST",
        url : "/ImageBLImpl/getImageAndLabelnfo", //利用ajax发起请求，这里写servlet的路径

        data : {
            //获得第一张图片，索引设为0
            imageIndex:index,
            taskId: overall_task_Id,
            userId : overall_user_Id
        },
        success: function(AreaLabelVOJSON) {

        },

        error: function () {
            alert("Wrong!");
        }
    });



});
