

window.onload=function(){

    getAllFinishedTasks(getUserId());
    getAllUnfinishedTasks(getUserId());
    getUserScore(getUserId());
    getUserRanking(getUserId());


    for(var i=0;i<AllUnfinishedTasks.length;i++){
        addRow(AllUnfinishedTasks[i],"incompleted");
    }

    for(var i=0;i<AllFinishedTasks.length;i++){
        addRow(AllFinishedTasks[i],"accomplished");
    }


    document.getElementById("ranking").value = ranking;
    document.getElementById("score").value = score;

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
				AllUnfinishedTasks = data;
			}

	});
}


function getAllFinishedTasks(userId){
	$.ajax({
			type: "GET",
        	async: false,
			url:"/WorkerController/getAcceptedAndAccomplishedTaskList",
			data:{
				user: userId
			},

			success:function(data1){
				AllFinishedTasks = data1;
			}

	});
}


function getUserScore(userId){
	$.ajax({
			type: "GET",
        	async: false,
			url:"/WorkerController/getUserScore",
			data:{
				user :  userId
			},

			success:function(Score){
				score = Score;
			}

	});
}



function getUserRanking(userId){
	$.ajax({
			type: "GET",
        	async: false,
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
	var z =document.getElementById(id).rows.length;

    var tableRow=document.getElementById(id).insertRow(z);


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
}




function add_Row(singleTask,id){
    var z =document.getElementById(id).rows.length;

    var tableRow=document.getElementById(id).insertRow(z);


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
}

