new Vue({
   el: "#taskInfoContainer",
   data: {
       userId: "",
       AllUnfinishedTasks: [],

       IMAGE_LABEL_TYPE: "ImageLabel", 
       FRAME_LABEL_TYPE: "FrameLabel",
       AREA_LABEL_TYPE: "AreaLabel",
   },
    mounted: function () {
       const _this = this;
        this.$nextTick(function () {
            _this.userId = getUserId();
            axios.get("/WorkerController/getAcceptedButIncompleteTaskList", 
                { params:{ userId: this.userId } })
                .then(function (response) { 
                _this.AllUnfinishedTasks = response.data;
            })
        })
    },
    methods: {
       doTask: function (index) {
           let labelType = this.AllUnfinishedTasks[index].labelType;
           let taskId = this.AllUnfinishedTasks[index].taskId;
           if(labelType === this.IMAGE_LABEL_TYPE){
               jumToTask(markImageLabelPageUrl, this.userId, taskId);
           }else if(labelType === this.FRAME_LABEL_TYPE){
               jumToTask(markFrameLabelPageUrl, this.userId, taskId);
           }else if(labelType === this.AREA_LABEL_TYPE){
               jumToTask(markAreaLabelPageUrl, this.userId, taskId);
           }else {
               alert("该任务标注类型错误");
           }
       },
        getChineseLabelType: function (labelType) {
            if(labelType === this.IMAGE_LABEL_TYPE){
                return "整体标注";
            }else if(labelType === this.FRAME_LABEL_TYPE){
                return "标框标注";
            }else if(labelType === this.AREA_LABEL_TYPE){
                return "区域标注";
            }
        }
    },
});


// var IMAGE_LABEL_TYPE="ImageLabel";
// var type2="AreaLabel";
// var type3="FrameLabel";
// var oneTask = new Array();
// var count = 0 ;
// var AllUnfinishedTasks = new Array();
//
//
// window.onload=function(){
//     //清空表格内容
//     getAllUnfinishedTasks(getUserId());
//     //添加表格任务
//     console.log("before");
//
//     for(var i=0;i<AllUnfinishedTasks.length;i++){
//         console.log(AllUnfinishedTasks[i]);
//         addTask(AllUnfinishedTasks[i],"TaskTable",i);
//     }
//
// }
//
//
//
// function addTask(singleTask,id,count){
//
//     oneTask.push(singleTask);
//
//     var z =document.getElementById("TaskTable").rows.length;
// //table 添加内容
//     var tableRow=document.getElementById("TaskTable").insertRow(z);
//
//
//     var Cell_0=tableRow.insertCell(0);
//     Cell_0.innerHTML='<input value= "'+singleTask.taskId +'"readonly="true"/>';
//     Cell_0.className="s1";
//
//     var Cell_1=tableRow.insertCell(1);
//     Cell_1.innerHTML='<input value="'+singleTask.labelType+'"  readonly="true"/>';
//     Cell_1.className="s2";
//
//     var Cell_2=tableRow.insertCell(2);
//     Cell_2.innerHTML='<input value="'+singleTask.description+'"  readonly="true"/>';
//     Cell_2.className="s3";
//
//     var Cell_3=tableRow.insertCell(3);
//     Cell_3.innerHTML='<input value="'+singleTask.score+'"  readonly="true"/>';
//     Cell_3.className="s4";
//
//     // var IMAGE_LABEL_TYPE="ImageLabel";
//     // var type2="AreaLabel";
//     // var type3="FrameLabel";
//
//     if(singleTask.labelType===IMAGE_LABEL_TYPE) {
//         var Cell_4 = tableRow.insertCell(4);
//         Cell_4.innerHTML = '<a href="javascript:void(0)"  readonly="true" onclick="jumToTask(overAllPageUrl,getUserId(),oneTask[count].taskId)">do it</a>';
//         Cell_4.className = "s5";
//     }
//
//     else if(singleTask.labelType===type2){
//         var Cell_4 = tableRow.insertCell(4);
//         Cell_4.innerHTML = '<a href="javascript:void(0)"  readonly="true" onclick="jumToTask(lineMarkPageUrl,getUserId(),oneTask[count].taskId)">do it</a>';
//         Cell_4.className = "s5";
//     }
//
//     else if(singleTask.labelType===type3){
//         var Cell_4 = tableRow.insertCell(4);jumToTask(markFrameLabelPageU
// //         Cell_4.innerHTML = '<a href="javascript:void(0)"  readonly="true" onclick="rl,getUserId(),oneTask[count].taskId)">do it</a>';
//         Cell_4.className = "s5";
//     }
//
// }
//
//
// function getAllUnfinishedTasks(userId){
//     $.ajax({
//         type: "GET",
//         async: false,
//         url:"/WorkerController/getAcceptedButIncompleteTaskList",
//         data:{
//             userId : userId
//         },
//
//         success:function(data){
//             console.log(data);
//            AllUnfinishedTasks = data;
//         }
//
//     });
//
// }
//
