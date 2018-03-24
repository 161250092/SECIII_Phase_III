var canvas, context;
var img;

var imageId, userId;
var frameLabelTagItemList = [];
var tempFrameLabelTagItem;
var index = 0;

//标框类
function FrameLabel(imageId, userId, frameLabelTagItemList) {
    this.imageId = imageId;
    this.userId = userId;
    this.frameLabelTagItemList = frameLabelTagItemList;
}
//框中的标签类
function FrameLabelTagItem(startX, startY, width, height){
    this.startX = startX;
    this.startY = startY;
    this.width = width;
    this.height = height;
    this.tag = '';
}

window.addEventListener('load', function () {
    canvas = document.getElementById("drawingCanvas");
    context = canvas.getContext("2d");
    var addTagButton = document.getElementById("addTagButton");
    img = context.createImageData(canvas.width, canvas.height);

    const rect = canvas.getBoundingClientRect();
    canvas.width = rect.width;
    canvas.height = rect.height;

    canvas.onmousedown = startDrawing;
    canvas.onmouseup = stopDrawing;
    canvas.onmouseout = stopDrawing;
    canvas.onmousemove = draw;

    addTagButton.onclick = addNewTag;
}, false);

var isDrawing = false;
var isConfirmed = true;
var x, y, width, height;

function startDrawing(e) {
    console.log(isDrawing)
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
function stopDrawing() {
    isDrawing = false;
    isConfirmed = false;
    img = context.getImageData(0, 0, canvas.width, canvas.height);
    tempFrameLabelTagItem = new FrameLabelTagItem(x, y, width, height);
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

//获得坐标的函数
function getX(e){
    const rect = canvas.getBoundingClientRect();
    return e.clientX - rect.left;
}
function getY(e){
    const rect = canvas.getBoundingClientRect();
    return e.clientY - rect.top;
}

//在标签栏增加标签
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

    tempFrameLabelTagItem.tag = tag;
    frameLabelTagItemList[index] = tempFrameLabelTagItem;
    index += 1;
}
//删除标签栏中的标签
function deleteTag(e){
    var target, elList, removedTagIndex;
    target = e.target;

    if(target.nodeName.toLowerCase() === 'li'){
        removedTagIndex = target.value;

        delete recArray[removedTagIndex];
        removeRecInCanvas(recArray);
        removeTagFromCanvas(target.textContent);

        elList = target.parentNode;
        elList.removeChild(target);
    }
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
    var tagItemList = document.getElementsByClassName('tagInCanvas');
    var tagItem;
    for(var i = 0; i < tagItemList.length; i++){
        if(tagItemList[i].textContent === tag){
            tagItem = tagItemList[i];
            break;
        }
    }
    var elParent = tagItem.parentNode;
    elParent.removeChild(tagItem);
}
//删掉选中的框
function removeRecInCanvas(recArray) {
    context.clearRect(0, 0, canvas.width, canvas.height);
    for(var i = 0; i < recArray.length; i++){
        if(recArray[i] !== undefined){
            context.strokeRect(recArray[i].startX, recArray[i].startY, recArray[i].width, recArray[i].height);
        }
    }
}
//标签栏和画布上的标签
function purgeLabels(){
    context.clearRect(0, 0, canvas.width, canvas.height);
    console.log(frameLabelTagItemList)
    index = 0;
    //清空数组
    frameLabelTagItemList.length = 0;
    console.log(frameLabelTagItemList)
    //删除子节点
    var parentOfRemoveEl = document.getElementById("partOfTagInCanvas");
    while (parentOfRemoveEl.hasChildNodes()){
        parentOfRemoveEl.removeChild(parentOfRemoveEl.firstChild);
    }
    parentOfRemoveEl = document.getElementById("tagList");
    while (parentOfRemoveEl.hasChildNodes()){
        parentOfRemoveEl.removeChild(parentOfRemoveEl.firstChild);
    }

    isDrawing = false;
}

//双击标签栏
$("#tagListItem").dblclick(function () {
    deleteTag(e);
});

$("#purgeButton").click(function () {
    purgeLabels();
});

//发送标签数组
$("#saveButton").click(function(){
    var frameLabel = new FrameLabel(imageId, userId, frameLabelTagItemList);
    $.ajax({
        type: "GET",
        url: "/markFrameLabel/saveFrameLabel",
        data: {
            frameLabelJson: JSON.stringify(frameLabel)
        },
        success:function (result) {
            // $("div").html(result);
            alert(result);
        }
    });
});

$("#getButton").click(function(){
    var frameLabel;
    $.ajax({
        type: "GET",
        url: "/markFrameLabel/getFrameLabel",

        success:function (result) {
            frameLabel = JSON.parse(result);

            console.log(frameLabel);
            imageId = frameLabel.imageId;
            userId = frameLabel.userId;
            frameLabelTagItemList = frameLabel.frameLabelTagItemList;

            /*通过图片ID获得图片*/
            $.ajax({
                type: "GET",
                url: "/markFrameLabel/getImageById",
                data: {
                    imageId: 'imageId'
                },
                success: function (imageURL) {
                    document.getElementById("drawingCanvas").style.backgroundImage = imageURL;
                }
            });

            for(var i = 0; i < frameLabelTagItemList.length; i++){
                console.log(frameLabelTagItemList[i])
                putTagIntoCanvas(frameLabelTagItemList[i].startX, frameLabelTagItemList[i].startY, frameLabelTagItemList[i].tag);
                context.strokeRect(frameLabelTagItemList[i].startX, frameLabelTagItemList[i].startY, frameLabelTagItemList[i].width, frameLabelTagItemList[i].height);
            }
        }
    });
});

$("#nextButton").click(function(){
    $.ajax({
        type: "GET",
        url: "/markFrameLabel/getImageById",
        data: {
            imageId: '222'
        },

        success:function (result) {
            document.getElementById("drawingCanvas").style.backgroundImage = result;
            purgeLabels();
        }
    });
});