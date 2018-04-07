
var AllUnfinishedTasks = new Array();

var AllFinishedTasks = new Array();

var score;

var ranking;


function getAllUnfinishedTasks(userId){
	$.ajax({
			type: "GET",
			url:"/WorkerController/getAllUnfinishedTasks",
			data:{
				user : userId
			},

			success:function(AllUnfinishedTasksJson){
				AllUnfinishedTasks = JSON.parse(AllUnfinishedTasksJson);
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

			success:function(AllFinishedTasksJson){
				AllFinishedTasks = JSON.parse(AllFinishedTasksJson);
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

			success:function(ScoreJson){
				score = JSON.parse(ScoreJson);
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

			success:function(RankingJson){
				ranking = JSON.parse(RankingJson);
			}

	});

}


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
    Cell_2.innerHTML='<input value="'+singleTask.introduction+'"  readonly="true"/>';
    Cell_2.className="s3";

    var Cell_3=tableRow.insertCell(5);
    Cell_3.innerHTML='<input value="'+singleTask.score+'"  readonly="true"/>';
    Cell_3.className="s4";
}

$("#check").click(function (){
	//
	getAllFinishedTasks(userId);
	getAllUnfinishedTasks(userId);
	getUserScore(userId);
	getUserRanking(userId);
	for(var i=0;i<AssignedIncompletedTaskList.length;i++){
		addRow(AssignedIncompletedTaskList[i],"incompleted");
        }

    for(var i=0;i<AssignedCompletedTaskList.length;i++){
            addRow(AssignedCompletedTaskList[i],"accomplished");
    }


    $("ranking").innerHTML = ranking;
    $("score").innerHTML = score;



});



