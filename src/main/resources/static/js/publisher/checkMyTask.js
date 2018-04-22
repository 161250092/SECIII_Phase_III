//user提交的任务
var AssignedIncompletedTaskList = new Array();

var AssignedCompletedTaskList = new Array();


window.onload = function(){

    clearRow("incompleted");
    clearRow("accomplished");

    getAssignedIncompletedTaskList(userId);
    getAssignedAndAccomplishedTaskList(userId);

    for(var i=0;i<AssignedIncompletedTaskList.length;i++){
        addRow(AssignedIncompletedTaskList[i],"incompleted");
    }

    for(var i=0;i<AssignedCompletedTaskList.length;i++){
        addRow(AssignedCompletedTaskList[i],"accomplished");
    }

}


//从服务器获取一个user 的 incompleted task
function getAssignedIncompletedTaskList(userId){

	$.ajax({
			type: "GET",
			url:"/RequestorController/getAssignedButIncompleteTaskList",
			data:{
				user: userId
			},

			success:function(AssignedButIncompleteTaskListJson){
			     AssignedButIncompleteTaskList = JSON.parse(AssignedButIncompleteTaskListJson);
			}

	});
}

//从服务器获取一个user 的 accomplished task
function getAssignedAndAccomplishedTaskList(userId){

        $.ajax({
                        type: "GET",
                        url:"/RequestorController/getAssignedAndAccomplishedTaskList",
                        data:{
                                user : userId
                        },

                        success:function(AssignedAndAccomplishedTaskListJson){
                                AssignedCompletedTaskList  = JSON.parse(AssignedAndAccomplishedTaskListJson);
                        }

        });
}



//改变table内容
function addRow(singleTask,id){
        
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
        Cell_2.innerHTML='<input value="'+singleTask.requiredNumber+'"  readonly="true"/>';
        Cell_2.className="s3";


        var Cell_3=tableRow.insertCell(3);
        Cell_3.innerHTML='<input value="'+singleTask.finishedNumber+'"  readonly="true"/>';
        Cell_3.className="s4";


        var Cell_4=tableRow.insertCell(4);
        Cell_4.innerHTML='<input value="'+singleTask.introduction+'"  readonly="true"/>';
        Cell_4.className="s5";

        var Cell_5=tableRow.insertCell(5);
        Cell_5.innerHTML='<input value="'+singleTask.score+'"  readonly="true"/>';
        Cell_5.className="s6";

}

function clearRow(trid){
    var t=document.getElementById(trid);
//删除所有行
    t.firstChild.removeNode(true)
}
function getUserId(){

}

