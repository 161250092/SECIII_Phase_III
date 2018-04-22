//下拉框获取任务信息
var overall_user_Id;
var overall_task_Id;

var labelInfo;
//记录图片的最大索引值
var maxN = 2;
//当前图片的编号
var index = 0;
//当前展示的图片
var image = null;


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

            userId : line_user_Id


        },
        success: function(num) {
            maxN = num;
        },

        error: function () {
            alert("Wrong!");
        }
    });

    $.ajax({
        type : "POST",
        url : "/markLabel/getLabel", //利用ajax发起请求，这里写servlet的路径

        data : {
            //获得第一张图片，索引设为0
            imageIndex:0,
            taskId: line_task_Id,
            userId : line_user_Id,
            labelType: type1

        },
        success: function(picture) {
            var AreaInfo = JSON.parse(picture);
            image.src = AreaInfo.image;
            labelInfo = AreaInfo.label;

            index = 0;
        },

        error: function () {
            alert("Wrong!");
        }
    });

};


//改变图片源
//下一张图片
$("#next").click(function(){
    //先保存
    save();

    //索引值+1
    index++;

    $.ajax({
        type : "POST",
        url : "/markLabel/getLabel", //利用ajax发起请求，这里写servlet的路径

        data : {
            //获得下一张图片
            imageIndex:index,
            taskId: line_task_Id,
            userId : line_user_Id,
            labelType: type1
        },
        success: function(ImageLabelVOJSON) {

            var ImageInfo = JSON.parse(ImageLabelVOJSON);
            image.src = ImageInfo.image;
            labelInfo = ImageInfo.label;

            if (!labelInfo.euqals("")){
                console.log(labelInfo);
                $("#tagInput").val(labelInfo.toString());
            }

        },

        error: function () {
            alert("Wrong!");
        }
    });

});


//保存
function save(){

    var label = $("#tagInput").val();

    //alert(JSON.stringify(areaLabel));

    $.ajax({
        type : "POST",
        url : "/markLabel/saveLabel", //利用ajax发起请求，这里写servlet的路径

        data : {
            taskId: line_task_Id,
            userId : line_user_Id,
            imageIndex :index,
            type:type1,
            labelJson: label.toString()

        },
        success: function(data) {
            alert("Success!");
        },

        error: function () {
            alert("Wrong!");
        }
    });

}


