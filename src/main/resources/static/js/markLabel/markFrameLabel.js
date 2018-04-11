//框中的标签类
function FrameLabelTagItem(startX, startY, width, height, tag){
    this.startX = startX;
    this.startY = startY;
    this.width = width;
    this.height = height;
    this.tag = tag;
}


new Vue({
    el: "#markFrameLabelContainer",
    data: {
        userId: "",
        taskId: "",
        taskImageNum: 0,
        currentImageIndex: 0,
        currentImage: "",
        currentFrameLabelList: [],
        currentStartX: 0,
        currentStartY: 0,
        currentWidth: 0,
        currentHeight: 0,
    },
    mounted: function () {
        this.$nextTick(function () {
            this.getFrameLabel(this.currentImageIndex);
        });
    },
    methods: {
        getFrameLabel: function (imageIndex) {
            const _this = this;
            axios.get("../../userData.json", { params:
                    { imageIndex: imageIndex, taskId: _this.taskId, userId: _this.userId} })
                .then(function (response) {
                    _this.currentImage = response.data.image;
                    _this.currentFrameLabelList = response.data.labelList;
                });
        },
        addTag: function () {
            var tag = this.$refs.inputTag.value;
            var temp = new FrameLabelTagItem(this.currentStartX, this.currentStartY, this.currentWidth, this.currentHeight, tag);
            this.currentFrameLabelList.push(temp);
        }
    },
});









// var canvas, context;
// var img;
//
// var imageId = '1', userId = 'test';
// var frameLabelTagItemList = [];
// var tempFrameLabelTagItem;
// var index = 0;
//
// //标框类
// function FrameLabel(imageId, userId, frameLabelTagItemList) {
//     this.imageId = imageId;
//     this.userId = userId;
//     this.frameLabelTagItemList = frameLabelTagItemList;
// }
// //框中的标签类
// function FrameLabelTagItem(startX, startY, width, height){
//     this.startX = startX;
//     this.startY = startY;
//     this.width = width;
//     this.height = height;
//     this.tag = '';
// }
//
// window.addEventListener('load', function () {
//     canvas = document.getElementById("drawingCanvas");
//     context = canvas.getContext("2d");
//     var addTagButton = document.getElementById("addTagButton");
//     img = context.createImageData(canvas.width, canvas.height);
//
//     const rect = canvas.getBoundingClientRect();
//     canvas.width = rect.width;
//     canvas.height = rect.height;
//
//     canvas.onmousedown = startDrawing;
//     canvas.onmouseup = stopDrawing;
//     canvas.onmouseout = stopDrawing;
//     canvas.onmousemove = draw;
//
//     // addTagButton.onclick = addNewTagByButton;
//
//     getFrameLabel(userId, imageId);
// }, false);
//
// var isDrawing = false;
// var x, y, width, height;
//
// function startDrawing(e) {
//     if(isDrawing === false){
//         isDrawing = true;
//         x = getX(e);
//         y = getY(e);
//         context.moveTo(x, y);
//         img = context.getImageData(0, 0, canvas.width, canvas.height);
//     }
// }
// function stopDrawing() {
//     isDrawing = false;
//     img = context.getImageData(0, 0, canvas.width, canvas.height);
//     tempFrameLabelTagItem = new FrameLabelTagItem(x, y, width, height);
// }
// function draw(e) {
//     if(isDrawing === true){
//         context.putImageData(img, 0, 0);
//         width = Math.abs(getX(e) - x);
//         height = Math.abs(getY(e) - y);
//         if(width >= 10 && height >= 10){
//             context.strokeRect(x, y, width, height);
//         }
//     }
// }
//
// //获得坐标的函数
// function getX(e){
//     const rect = canvas.getBoundingClientRect();
//     return e.clientX - rect.left;
// }
// function getY(e){
//     const rect = canvas.getBoundingClientRect();
//     return e.clientY - rect.top;
// }
//
// //在标签栏增加标签
// function addTag(x, y, tag) {
//     //创建新的标签栏(index已加一)
//     addNewTagAndAddIndex(tag);
//     //在画布上放置标签
//     putTagIntoCanvas(x, y, tag);
// }
// //删除标签栏中的标签
// function deleteTag(e){
//     var target, elList, removedTagIndex;
//     target = e.target.parentNode;
//
//     if(target.nodeName.toLowerCase() === 'li'){
//         removedTagIndex = target.value;
//
//         delete frameLabelTagItemList[removedTagIndex];
//         removeRecInCanvas(frameLabelTagItemList);
//         removeTagFromCanvas(target.textContent);
//
//         elList = target.parentNode;
//         elList.removeChild(target);
//     }
// }
// //放置画布上的标签
// function putTagIntoCanvas(x, y, tag) {
//     //创建在画布上放置的标签
//     var newTagItem = document.createElement('p');
//     var newTagContext = document.createTextNode(tag);
//     newTagItem.appendChild(newTagContext);
//     newTagItem.setAttribute('class', 'tagInCanvas');
//     newTagItem.style.left = x + "px";
//     newTagItem.style.top = y + "px";
//     //在画布上放置标签栏
//     var tagPosition = document.getElementById('partOfTagInCanvas');
//     tagPosition.appendChild(newTagItem);
// }
// //删除画布中的标签
// function removeTagFromCanvas(tag) {
//     var tagItemList = document.getElementsByClassName('tagInCanvas');
//     var tagItem;
//     for(var i = 0; i < tagItemList.length; i++){
//         if(tagItemList[i].textContent === tag){
//             tagItem = tagItemList[i];
//             break;
//         }
//     }
//     var elParent = tagItem.parentNode;
//     elParent.removeChild(tagItem);
// }
// //删掉选中的框
// function removeRecInCanvas(frameLabelTagItemList) {
//     context.clearRect(0, 0, canvas.width, canvas.height);
//     for(var i = 0; i < frameLabelTagItemList.length; i++){
//         if(frameLabelTagItemList[i] !== undefined){
//             context.strokeRect(frameLabelTagItemList[i].startX, frameLabelTagItemList[i].startY, frameLabelTagItemList[i].width, frameLabelTagItemList[i].height);
//         }
//     }
// }
//
// function addNewTagAndAddIndex(tag) {
//     //创建新的标签栏
//     var newTagItem = document.createElement('li');
//     newTagItem.innerHTML = '<a>' + tag + '</a>';
//     // var newTagContext = document.createTextNode(tag);
//     // newTagItem.appendChild(newTagContext);
//     newTagItem.setAttribute('class', 'tagListItem');
//     newTagItem.value = index;
//     //注册事件
//     // newTagItem.ondblclick = deleteTag;
//     //放置标签栏
//     var tagPosition = document.getElementById('tagList');
//     tagPosition.appendChild(newTagItem);
//
//     index += 1;
// }
//
// //标签栏和画布上的标签
// function purgeLabels(){
//     context.clearRect(0, 0, canvas.width, canvas.height);
//     index = 0;
//     //清空数组
//     frameLabelTagItemList.length = 0;
//     //删除子节点
//     var parentOfRemoveEl = document.getElementById("partOfTagInCanvas");
//     while (parentOfRemoveEl.hasChildNodes()){
//         parentOfRemoveEl.removeChild(parentOfRemoveEl.firstChild);
//     }
//     parentOfRemoveEl = document.getElementById("tagList");
//     while (parentOfRemoveEl.hasChildNodes()){
//         parentOfRemoveEl.removeChild(parentOfRemoveEl.firstChild);
//     }
//
//     isDrawing = false;
// }
//
// //获得标框数据
// function getFrameLabel(userId, imageId) {
//     var frameLabel;
//     $.ajax({
//         type: "GET",
//         url: "/markFrameLabel/getFrameLabel",
//         data: {
//             userId: userId,
//             imageId: imageId
//         },
//
//         success:function (frameLabelJson) {
//             frameLabel = JSON.parse(frameLabelJson);
//
//             this.imageId = frameLabel.imageId;
//             this.userId = frameLabel.userId;
//             this.frameLabelTagItemList = frameLabel.frameLabelTagItemList;
//
//             /*通过图片ID获得图片*/
//             $.ajax({
//                 async: false,
//                 type: "GET",
//                 url: "/imageController/getImageById",
//                 data: {
//                     imageId: imageId
//                 },
//                 success: function (imageURL) {
//                     document.getElementById("drawingCanvas").style.backgroundImage = imageURL;
//                 }
//             });
//
//             index = 0;
//             for(var i = 0; i < frameLabelTagItemList.length; i++){
//                 addNewTagAndAddIndex(frameLabelTagItemList[i].tag);
//                 putTagIntoCanvas(frameLabelTagItemList[i].startX, frameLabelTagItemList[i].startY, frameLabelTagItemList[i].tag);
//                 context.strokeRect(frameLabelTagItemList[i].startX, frameLabelTagItemList[i].startY, frameLabelTagItemList[i].width, frameLabelTagItemList[i].height);
//             }
//         }
//     });
// }
//
// //获得下一个图片ID
// function getPreviousImageId(currentImageId) {
//     $.ajax({
//         type: "GET",
//         url: "/imageController/getPreviousImageId",
//         data: {
//             currentImageId: currentImageId
//         },
//
//         success:function (previousImageId) {
//             imageId = previousImageId;
//         }
//     });
// }
//
// //获得下一个图片ID
// function getNextImageId(currentImageId) {
//     $.ajax({
//         type: "GET",
//         url: "/imageController/getNextImageId",
//         data: {
//             currentImageId: currentImageId
//         },
//
//         success:function (nextImageId) {
//             imageId = nextImageId;
//         }
//     });
// }
//
// $("#tagList").dblclick(function (e) {
//     deleteTag(e);
// });
//
// $("#addTagButton").click(function (x, y) {
//     //获得标签
//     var tag = document.getElementById('tagInput').value;
//     //标签清零
//     document.getElementById('tagInput').value = '';
//
//     tempFrameLabelTagItem.tag = tag;
//     frameLabelTagItemList[index] = tempFrameLabelTagItem;
//
//     addTag(window.x, window.y ,tag);
// });
//
// $("#purgeButton").click(function () {
//     purgeLabels();
// });
//
// //发送标签数组
// $("#saveButton").click(function(){
//     var frameLabel = new FrameLabel(imageId, userId, frameLabelTagItemList);
//     $.ajax({
//         type: "GET",
//         url: "/markFrameLabel/saveFrameLabel",
//         data: {
//             frameLabelJson: JSON.stringify(frameLabel)
//         },
//         success:function (result) {
//             // $("div").html(result);
//             alert(result);
//         }
//     });
// });
//
// $("#previousButton").click(function(){
//     purgeLabels();
//     getPreviousImageId(imageId);
//     getFrameLabel(userId, imageId);
// });
//
// $("#nextButton").click(function(){
//     purgeLabels();
//     getNextImageId(imageId);
//     getFrameLabel(userId, imageId);
// });