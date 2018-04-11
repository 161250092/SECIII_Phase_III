
window.onload=function(){
    //清空表格内容
    clearRow("TaskTable");
    //添加表格任务
    for(var i=0;i<AllUnfinishedTasks.length;i++){
        addTask(AllUnfinishedTasks[i],"TaskTable");
    }

}


function addTask(singleTask,id){
    var z =$(id).rows.length
//table 添加内容
    var tableRow=$(id).insertRow(z);


    var Cell_0=tableRow.insertCell(0);
    Cell_0.innerHTML='<input value= "'+singleTask.taskId +'"readonly="true"/>';
    Cell_0.className="s1";

    var Cell_1=tableRow.insertCell(1);
    Cell_1.innerHTML='<input value="'+singleTask.labelType+'"  readonly="true"/>';
    Cell_1.className="s2";

    var Cell_2=tableRow.insertCell(2);
    Cell_2.innerHTML='<input value="'+singleTask.introduction+'"  readonly="true"/>';
    Cell_2.className="s3";

    var Cell_3=tableRow.insertCell(3);
    Cell_3.innerHTML='<input value="'+singleTask.score+'"  readonly="true"/>';
    Cell_3.className="s4";


    if(singleTask.labelType==type1) {
        var Cell_4 = tableRow.insertCell(4);
        Cell_4.innerHTML = '<a href="OverAllPage.html"  readonly="true"/>';
        Cell_4.className = "s5";
    }

    else if(singleTask.labelType==type2){
        var Cell_4 = tableRow.insertCell(4);
        Cell_4.innerHTML = '<a href="markFrameLabel.html"  readonly="true"/>';
        Cell_4.className = "s5";
    }

    else if(singleTask.labelType==type3){
        var Cell_4 = tableRow.insertCell(4);
        Cell_4.innerHTML = '<a href="LineMarkPage.html"  readonly="true"/>';
        Cell_4.className = "s5";
    }


}

function clearRow(trid){
    var t=document.getElementById(trid);
//删除所有行
    t.firstChild.removeNode(true)
}




