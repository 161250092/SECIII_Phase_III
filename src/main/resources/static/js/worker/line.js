//下拉框获取任务信息
var line_user_Id;
var line_task_Id;


//加载页面时先获取该任务的图片数，首张图片
window.onload=function(){
    var url = window.location.href;
    var temp = url.split("?");
    var infor = temp[1].split("&");


    line_user_Id = infor[0];
    line_task_Id = infor[1];
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
            taskId: line_task_Id,
            userId : line_user_Id

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

//恢复线条标记，如果没有则不显示
function restoreLabel(AreaLabelVO){

    restore();

}



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

//下一张图片
$("#next").click(function(){
    //先保存
    save();

    //索引值+1
    index++;

    $.ajax({
        type : "POST",
        url : "/ImageBLImpl/getImageAndLabelnfo", //利用ajax发起请求，这里写servlet的路径

        data : {
            //获得第一张图片，索引设为0
            imageIndex:index,
            taskId: line_task_Id,
            userId : line_user_Id
        },
        success: function(AreaLabelVOJSON) {
                var arealabel = JSON.parse(AreaLabelVOJSON);
                restoreLabel(arealabel);

        },

        error: function () {
            alert("Wrong!");
        }
    });

});




//保存
function save(){

    var label = $("#tagInput").val();
    var areaLabel = new AreaLabel(index, "test", label, arrayOfLine);
    //alert(JSON.stringify(areaLabel));

    $.ajax({
        type : "POST",
        url : "/markAreaLabel/saveAreaLabel", //利用ajax发起请求，这里写servlet的路径

        data : {
            areaLabelJson: JSON.stringify(areaLabel)
            //areaLabelJson: "Hello"
        },
        success: function(data) {
            alert("Success!");
        },

        error: function () {
            alert("Wrong!");
        }
    });

}

