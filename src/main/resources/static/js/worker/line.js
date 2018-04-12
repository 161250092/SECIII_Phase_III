//下拉框获取任务信息

window.onload=function(){
    var url = window.location.href;
    var temp = url.split("?");
    var infor = temp[1].split("&");


    var user_Id = infor[0];
    var task_Id = infor[1];
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
            taskId: task_id,
            userId : user_Id

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


// function insertOption(taskId,type)
// {
//     var y=document.createElement('option');
//     y.text=taskId;
//     var x;
//
//     if(type==type1)
//     //整体标注
//         x=document.getElementById("overallTask");
//
//     else if(type==type2)
//         x=document.getElementById("frameTask");
//
//     else if(type==type3)
//         x=document.getElementById("lineTask");
//
//     try
//     {
//         x.add(y,null); // standards compliant
//     }
//     catch(ex)
//     {
//         x.add(y); // IE only
//     }
// }

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
            taskId: task_id,
            userId : user_Id
        },
        success: function(AreaLabelVOJSON) {
            image.src =
        },

        error: function () {
            alert("Wrong!");
        }
    });



});
