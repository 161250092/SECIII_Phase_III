

window.onload=function(){

    getAllFinishedTasks(getUserId());
    getAllUnfinishedTasks(getUserId());
    getUserScore(getUserId());
    getUserRanking(getUserId());

    for(var i=0;i<AssignedIncompletedTaskList.length;i++){
        addRow(AssignedIncompletedTaskList[i],"incompleted");
    }

    for(var i=0;i<AssignedCompletedTaskList.length;i++){
        addRow(AssignedCompletedTaskList[i],"accomplished");
    }


    document.getElementById("ranking").value = ranking;
    document.getElementById("score").value = score;

}



function getAllUnfinishedTasks(userId){
	$.ajax({
			type: "GET",
			url:"/WorkerController/getAllUnfinishedTasks",
			data:{
				user : userId
			},

			success:function(data){
				AllUnfinishedTasks = data;
			}

	});
}


function getAllFinishedTasks(userId){
	$.ajax({
			type: "GET",
			url:"/WorkerController/getAllFinishedTasks",
			data:{
				user: userId
			},

			success:function(data1){
				AllFinishedTasks = datq1;
			}

	});
}


function getUserScore(userId){
	$.ajax({
			type: "GET",
			url:"/WorkerController/getUserScore",
			data:{
				user :  userId
			},

			success:function(Score){
				score = Score;
			}

	});
}



function getUserRanking(UserId){
	$.ajax({
			type: "GET",
			url:"/WorkerController/getUserRanking",
			data:{
				user :  userId
			},

			success:function(Ranking){
				ranking = Ranking;
			}

	});

}


function addRow(singleTask,id){
	var z =document.getElementById(id).rows.length
//table 添加内容
    var tableRow=document.getElementById(id).insertRow(z);


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
}



