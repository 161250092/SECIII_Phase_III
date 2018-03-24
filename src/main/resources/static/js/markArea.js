//记录图片的数量
const maxN = 2;
//当前图片的编号
var index = 0;
//当前展示的图片
var image = null;

//记录标线过程中鼠标移动的落点坐标
var last = null;
//记录标线过程中线条的索引
var indexOfLine = -1;
//记录一条标线中鼠标落点的索引
var indexOfPoint = -1;
//记录标线过程中标线的数组
var arrayOfLine = new Array();
//暂时存放鼠标落地的横坐标的数组
var arrayOfX = new Array();
//暂时存放鼠标落地的纵坐标的数组
var arrayOfY = new Array();

//画布
var canvas;
//绘图环境
var context;

//区域标注类
function AreaLabel(imageId, userId, label, lineList) {
    this.imageId = imageId;
    this.userId = userId;
    this.label = label;
    this.lineList = lineList;
}

//标线类
function Line(cordinateOfX, cordinateOfY){
    this.cordinateOfX = cordinateOfX;
    this.cordinateOfY = cordinateOfY;
}

//获取标线类转换的字符串
function getStringOfLine(line){
    var cordinateOfX = line.cordinateOfX;
    var cordinateOfY = line.cordinateOfY;
    var stringOfLine = "";
    for(var i = 0; i < cordinateOfX.length; i++){
        stringOfLine += cordinateOfX[i];
        stringOfLine += ",";
        stringOfLine += cordinateOfY[i];
        stringOfLine += ";";
    }

    return stringOfLine;
}

//解析字符串得到的标线类
function getLineFromString(stringOfLine) {
    var arrX = new Array();
    var arrY = new Array();
    var pairs = stringOfLine.split(";");
    var temp = new Array();
    for(var i = 0; i < pairs.length - 1; i++){
        temp = pairs[i].split(",");
        arrX[i] = temp[0];
        arrY[i] = temp[1];
    }

    var ret = new Line(arrX, arrY);
    return ret;
}


window.onload = function(){

    //获取画布 绘图的对象
    canvas = document.getElementById("drawingCanvas");
    context = canvas.getContext("2d");

    //获取图片对象
    image = document.getElementById("sourceImage");

    // 鼠标按下时的事件
    canvas.onmousedown = function(){
        indexOfPoint = -1;
        indexOfLine++;
        //arrayOfLine[indexOfLine] = new Array();
        // 在鼠标按下后触发鼠标移动事件
        canvas.onmousemove = move;
    }

    // 鼠标抬起取消鼠标移动的事件
    canvas.onmouseup = function(){
        canvas.onmousemove = null;
        stop();
    }

    // 鼠标移出画布时 移动事件也要取消。
    canvas.onmouseout = function(){
        canvas.onmousemove = null;
        //stop();
    }

}

// 鼠标移动函数
function move(e){
    indexOfPoint++;

    //获取鼠标落点的横纵坐标
    var pointX = e.offsetX - canvas.offsetLeft;
    var pointY = e.offsetY - canvas.offsetTop;

    arrayOfX[indexOfPoint] = pointX;
    arrayOfY[indexOfPoint] = pointY;


    //每一次鼠标的移动都会从上一次鼠标落点到这一次的落点连结成一条线段
    if(last != null){
        context.beginPath();
        context.moveTo(last[0],last[1]);
        context.lineTo(e.offsetX, e.offsetY);
        context.stroke();
    }
    // 每一次触发这个函数，都把当前鼠标的落点坐标记录下来，作为下一次线段的起始点。
    last = [e.offsetX, e.offsetY];
}

//停止作图
function stop() {
    //重置落点数组的索引
    indexOfPoint = -1;

    //将鼠标落点的数组存入标线类
    var tempLine = new Line(arrayOfX, arrayOfY);
    //将标线存入标线数组
    arrayOfLine[indexOfLine] = getStringOfLine(tempLine);

    //重置数组
    arrayOfX = new Array();
    arrayOfY = new Array();

    //重置last
    last = null;
}


// 重置标注区域
function resetPainting() {
    //重置绘画
    context.clearRect(0, 0, canvas.width, canvas.height);
    //重置索引
    indexOfLine = -1;
    indexOfPoint = -1;
    //重置数组
    arrayOfLine = new Array();
    arrayOfX = new Array();
    arrayOfY = new Array();
    //重置选择标注的下拉框
    var selectT = document.getElementById("selectOfTags");
    selectT.value = "null";
}

//还原标注图像
function restore(myarray){
    resetPainting();
    arrayOfLine = myarray;
    indexOfLine = myarray.length - 1;


    for(var i = 0; i < indexOfLine + 1; i++){

        var xArray = arrayOfLine[i].cordinateOfX;
        var yArray = arrayOfLine[i].cordinateOfY;

        var xx = xArray[0];
        var yy = yArray[0];

        //开始作图
        context.beginPath();
        context.moveTo(xx, yy);

        //按照落点数组作图
        for(var j = 1; j < xArray.length; j++){
            xx = xArray[j];
            yy = xArray[j];
            context.lineTo(xx, yy);
            context.stroke();
            context.moveTo(xx,yy);
        }

    }
}

//展示图片
function showImage(type){
    resetPainting();

    //重置选择标注的下拉框
    var selectT = document.getElementById("selectOfTags");
    selectT.value = "null";

    if(type == 'before'){
        if(index <= 0){
            alert("没有更多的图片了!");
        }else{
            index--;
            image.src = "images/"+index+".jpg";
        }
    }
    else if(type == 'next'){
        if(index >= maxN){
            alert("没有更多的图片了!");
        }else{
            index++;
            image.src = "images/"+index+".jpg";
        }
    }
    else{
        image.src = "images/"+index+".jpg";
    }
}

//选取图片
function selectImage(){
    var selectI = document.getElementById('selectOfImages');
    index = selectI.selectedIndex;
    image.src = "images/"+index+".jpg";
    var arr = new Array();
    //arr[0] = new Line([0,15], [0,15]);
    //arr[1] = new Line([200,300], [400,300]);
    //restore(arr);
}
//选取标签
function selectTag(){
    var selectT = document.getElementById('selectOfTags');
}


//提交按钮的动作
$("#sendButton").click(function(){
    var label = $("#tagText").val();
    var areaLabel = new AreaLabel("000", "admin", label, arrayOfLine);
    alert(JSON.stringify(areaLabel));

    $.ajax({
        type : "POST",
        url : "/saveAreaLabel", //利用ajax发起请求，这里写servlet的路径

        data : {
            areaLabelJson: JSON.stringify(areaLabel)
        },
        success: function(data) {
            alert("Success!");
        },

        error: function () {
            alert("Wrong!");
        }
    });

})

$("#getButton").click(function(){
    $.ajax({
        type: "GET",
        url: "/getAreaLabel",

        data : {
            imageId: "001"
        },
        success: function(data) {
            alert("Success!");
        },

        error: function () {
            alert("Wrong!");
        }

    });
    getFrameLabel(userId, imageId);
});

