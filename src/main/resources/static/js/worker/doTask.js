var type1="ImageLabel";
var type2="AreaLabel";
var type3="FrameLabel";
var oneTask = new Array();
var count = 0;
var AllUnfinishedTasks = new Array();


window.onload=function(){
    //清空表格内容
    getAllUnfinishedTasks(getUserId());
    //添加表格任务
    console.log("before");

    for(var i=0;i<AllUnfinishedTasks.length;i++){
        console.log(AllUnfinishedTasks[i]);
        addTask(AllUnfinishedTasks[i],"TaskTable",i);
    }

}



function addTask(singleTask,id,count){

    oneTask.push(singleTask);

    var z =document.getElementById("TaskTable").rows.length;
//table 添加内容
    var tableRow=document.getElementById("TaskTable").insertRow(z);


    var Cell_0=tableRow.insertCell(0);
    Cell_0.innerHTML='<input value= "'+singleTask.taskId +'"readonly="true"/>';
    Cell_0.className="s1";

    var Cell_1=tableRow.insertCell(1);
    Cell_1.innerHTML='<input value="'+singleTask.labelType+'"  readonly="true"/>';
    Cell_1.className="s2";

    var Cell_2=tableRow.insertCell(2);
    Cell_2.innerHTML='<input value="'+singleTask.description+'"  readonly="true"/>';
    Cell_2.className="s3";

    var Cell_3=tableRow.insertCell(3);
    Cell_3.innerHTML='<input value="'+singleTask.score+'"  readonly="true"/>';
    Cell_3.className="s4";


    if(singleTask.labelType===type1) {
        var Cell_4 = tableRow.insertCell(4);
        Cell_4.innerHTML = '<a href="javascript:void(0)"  readonly="true" onclick="jumToTask(overAllPageUrl,getUserId(),oneTask[count].taskId)">do it</a>';
        Cell_4.className = "s5";
    }

    else if(singleTask.labelType===type2){
        var Cell_4 = tableRow.insertCell(4);
        Cell_4.innerHTML = '<a href="javascript:void(0)"  readonly="true" onclick="jumToTask(markFrameLabelPageUrl,getUserId(),oneTask[count].taskId)">do it</a>';
        Cell_4.className = "s5";
    }

    else if(singleTask.labelType===type3){
        var Cell_4 = tableRow.insertCell(4);
        Cell_4.innerHTML = '<a href="javascript:void(0)"  readonly="true" onclick="jumToTask(lineMarkPageUrl,getUserId(),oneTask[count].taskId)">do it</a>';
        Cell_4.className = "s5";
    }

}


function getAllUnfinishedTasks(userId){
    $.ajax({
        type: "GET",
        async: false,
        url:"/WorkerController/getAcceptedButIncompleteTaskList",
        data:{
            user : userId
        },

        success:function(data){
            console.log(data);
           AllUnfinishedTasks = data;
        }

    });

}

