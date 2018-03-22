var canvas, context;
var confirmButton;
var img;

var recArray = new Array();
var tempRec;
var index = 0;

//矩形类
function RectangleFrame(startX, startY, width, height){
    this.startX = startX;
    this.startY = startY;
    this.width = width;
    this.height = height;
    this.tag = '';
}

//坐标类
function coordinate(x, y){
    this.x = x;
    this.y = y;
}
//获得坐标的函数
function getX(e){
    const rect = canvas.getBoundingClientRect();
    return e.clientX - rect.left;
}
function getY(e){
    const rect = canvas.getBoundingClientRect();
    return e.clientY - rect.top;
}

window.addEventListener('load', function () {
    canvas = document.getElementById("drawingCanvas");
    context = canvas.getContext("2d");
    confirmButton = document.getElementById("confirmButton");
    img = context.createImageData(canvas.width, canvas.height);

    const rect = canvas.getBoundingClientRect();
    canvas.width = rect.width;
    canvas.height = rect.height;

    canvas.onmousedown = startDrawing;
    canvas.onmouseup = stopDrawing;
    canvas.onmouseout = stopDrawing;
    canvas.onmousemove = draw;

    confirmButton.onclick = addNewTag;
}, false);


var isDrawing = false;
var isConfirmed = true;
var x, y, width, height;

function startDrawing(e) {
    if(isConfirmed === true){
        isDrawing = true;
        x = getX(e);
        y = getY(e);
        context.moveTo(x, y);
        img = context.getImageData(0, 0, canvas.width, canvas.height);
    }else{
        alert("请输入标签");
    }
}

function stopDrawing(e) {
    isDrawing = false;
    isConfirmed = false;
    img = context.getImageData(0, 0, canvas.width, canvas.height);
    tempRec = new RectangleFrame(x, y, width, height);
}

function draw(e) {
    if(isDrawing === true){
        context.putImageData(img, 0, 0);
        width = Math.abs(getX(e) - x);
        height = Math.abs(getY(e) - y);
        if(width >= 10 && height >= 10){
            context.strokeRect(x, y, width, height);
        }
    }
}

function addNewTag() {
    isConfirmed = true;
    //获得标签
    var tag = document.getElementById('tagInput').value;
    //标签清零
    document.getElementById('tagInput').value = '';

    //创建新的标签栏
    var newTagItem = document.createElement('li');
    var newTagContext = document.createTextNode(tag);
    newTagItem.appendChild(newTagContext);
    newTagItem.setAttribute('class', 'tagListItem');
    newTagItem.value = index;
    //放置标签栏
    var tagPosition = document.getElementById('tagList');
    tagPosition.appendChild(newTagItem);

    //在画布上放置标签
    putTagIntoCanvas(x, y, tag);

    tempRec.tag = tag;
    console.log(tempRec)
    recArray[index] = tempRec;
    index += 1;
}
//放置画布上的标签
function putTagIntoCanvas(x, y, tag) {
    //创建在画布上放置的标签
    var newTagItem = document.createElement('p');
    var newTagContext = document.createTextNode(tag);
    newTagItem.appendChild(newTagContext);
    newTagItem.setAttribute('class', 'tagInCanvas');
    newTagItem.style.left = x + "px";
    newTagItem.style.top = y + "px";
    //在画布上放置标签栏
    var tagPosition = document.getElementById('partOfTagInCanvas');
    tagPosition.appendChild(newTagItem);
}
//删除画布中的标签
function removeTagFromCanvas(tag) {
    console.log(tag)
    var tagItemList = document.getElementsByClassName('tagInCanvas');
    var tagItem;
    console.log(tagItemList)
    for(var i = 0; i < tagItemList.length; i++){
        console.log(tagItemList[i])
        if(tagItemList[i].textContent === tag){
            tagItem = tagItemList[i];
            break;
        }
    }
    console.log(tagItem)
    var elParent = tagItem.parentNode;
    elParent.removeChild(tagItem);
}

//删除标签栏
function deleteTag(e){
    var target, elList, removedTagIndex;
    target = e.target;

    if(target.nodeName.toLowerCase() === 'li'){
        removedTagIndex = target.value;

        delete recArray[removedTagIndex];
        removeRecInCanvas(recArray);
        removeTagFromCanvas(target.textContent)

        elList = target.parentNode;
        elList.removeChild(target);
    }
}
var el = document.getElementById("tagList");
el.addEventListener('dblclick', function (e) {
    deleteTag(e);
}, false);

//删掉选中的框
function removeRecInCanvas(recArray) {
    context.clearRect(0, 0, canvas.width, canvas.height);
    for(var i = 0; i < recArray.length; i++){
        if(recArray[i] === undefined){
            continue
        }else{
            context.strokeRect(recArray[i].startX, recArray[i].startY, recArray[i].width, recArray[i].height);
        }
    }
}

$("#sendButton").click(function(){
    console.log(recArray)
    $.ajax({
        type: "GET",
        url: "/sendRec",
        data: {
            recFrame: JSON.stringify(recArray)
        },
        success:function (result) {
            $("div").html(result);
        }
    });
});

$("#getButton").click(function(){
    $.ajax({
        type: "GET",
        url: "/getRec",

        success:function (result) {
            recArray = JSON.parse(result);
            console.log(recArray);
            for(var i = 0; i < recArray.length; i++){
                console.log(recArray[i])
                putTagIntoCanvas(recArray[i].startX, recArray[i].startY, recArray[i].tag);
                context.strokeRect(recArray[i].startX, recArray[i].startY, recArray[i].width, recArray[i].height);
                
            }

        }
    });
});