//下拉框获取任务信息
var line_user_Id;
var line_task_Id;
//标签框的内容
var labelInfo;
var lineStringList = new Array();
//加载页面时先获取该任务的图片数，首张图片
window.onload=function() {


    //获取画布 绘图的对象
    canvas = document.getElementById("drawingCanvas");
    context = canvas.getContext("2d");

    //获取图片对象
    image = document.getElementById("sourceImage");

    // 鼠标按下时的事件
    canvas.onmousedown = function () {
        indexOfPoint = -1;
        indexOfLine++;
        //arrayOfLine[indexOfLine] = new Array();
        // 在鼠标按下后触发鼠标移动事件
        canvas.onmousemove = move;
    }

    // 鼠标抬起取消鼠标移动的事件
    canvas.onmouseup = function () {
        canvas.onmousemove = null;
        stop();
    }

    // 鼠标移出画布时 移动事件也要取消。
    canvas.onmouseout = function () {
        canvas.onmousemove = null;
        //stop();

        var url = window.location.href;
        var temp = url.split("?");
        var infor = temp[1].split("&");


        line_user_Id = infor[0];
        line_task_Id = infor[1];
//图片最大数
        $.ajax({
            type: "POST",
            url: "/ImageBLImpl/getTaskImageNumber", //利用ajax发起请求，这里写servlet的路径

            data: {

                userId: line_user_Id


            },
            success: function (num) {
                maxN = num;
            },

            error: function () {
                alert("Wrong!");
            }
        });

        //图片信息
        $.ajax({
            type: "POST",
            url: "/markLabelBL/getLabel", //利用ajax发起请求，这里写servlet的路径

            data: {
                //获得第一张图片，索引设为0
                imageIndex: 0,
                taskId: line_task_Id,
                userId: line_user_Id,
                labelType: type2

            },
            success: function (picture) {
                var AreaInfo = JSON.parse(picture);
                image.src = AreaInfo.image;
                labelInfo = AreaInfo.label;
                lineStringList = AreaInfo.lineList;
                index = 0;
            },

            error: function () {
                alert("Wrong!");
            }
        });


    };
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
        url : "/markLabelBL/getLabel", //利用ajax发起请求，这里写servlet的路径

        data : {
            //获得下一张图片
            imageIndex:index,
            taskId: line_task_Id,
            userId : line_user_Id,
            labelType: type2
        },
        success: function(AreaLabelVOJSON) {

            var AreaInfo = JSON.parse(AreaLabelVOJSON);
            image.src = AreaInfo.image;
            labelInfo = AreaInfo.label;
            lineStringList = AreaInfo.lineList;

            if (!labelInfo.euqals("")){

            var myLineArray = new Array();
            //将字符串数组转化为 标线类Line对象的数组
            for (var i = 0; i < lineStringList.length; i++) {
                myLineArray[i] = getLineFromString(lineStringList[i]);
            }
            //还原图像
            restore(myLineArray);
            console.log(areaLabel.label.toString());
            $("#tagInput").val(areaLabel.label.toString());
            }

        },

        error: function () {
            alert("Wrong!");
        }
    });

});




//保存
function save() {

    var label = $("#tagInput").val();
    var areaLabel = new AreaLabel(label, arrayOfLine);
    //alert(JSON.stringify(areaLabel));

    $.ajax({
        type: "POST",
        url: "/markLabelBL/saveLabel", //利用ajax发起请求，这里写servlet的路径

        data: {
            taskId: line_task_Id,
            userId: line_user_Id,
            imageIndex: index,
            type: type2,
            areaLabelJson: JSON.stringify(areaLabel)
            //areaLabelJson: "Hello"
        },
        success: function (data) {
            alert("Success!");
        },

        error: function () {
            alert("Wrong!");
        }
    });

}


