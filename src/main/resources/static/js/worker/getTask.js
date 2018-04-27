var oneTask = new Array();

window.onload=function(){
    getMyIntroductionTask(getUserId());

    for(var i=0;i<introductionTask.length;i++)
        addRow(introductionTask[i],i);

}

function getMyIntroductionTask(userId){

	$.ajax({
			type: "GET",
            async: false,
			url:"/WorkerController/getAvailableTaskList",
			data:{
			},

			success:function(data){
				introductionTask = data;
			}

	});
}



function addRow(singleTask,count){

        oneTask.push(singleTask);
		var z =document.getElementById("TaskTable").rows.length;
//table 添加内容
        var tableRow=document.getElementById("TaskTable").insertRow(z);
        
        var Cell_0=tableRow.insertCell(0);
        Cell_0.innerHTML='<input value= "'+oneTask[count].taskId +'"readonly="true"/>';
        Cell_0.className="s1";

        var Cell_1=tableRow.insertCell(1);
        Cell_1.innerHTML='<input value="'+oneTask[count].labelType+'"  readonly="true"/>';
        Cell_1.className="s2";

        var Cell_2=tableRow.insertCell(2);
        Cell_2.innerHTML='<input value="'+oneTask[count].requiredNumber+'"  readonly="true"/>';
        Cell_2.className="s3";


        var Cell_3=tableRow.insertCell(3);
        Cell_3.innerHTML='<input value="'+oneTask[count].finishedNumber+'"  readonly="true"/>';
        Cell_3.className="s4";


        var Cell_4=tableRow.insertCell(4);
        Cell_4.innerHTML='<input value="'+oneTask[count].description+'"  readonly="true"/>';
        Cell_4.className="s5";

        var Cell_5=tableRow.insertCell(5);
        Cell_5.innerHTML='<input value="'+oneTask[count].score+'"  readonly="true"/>';
        Cell_5.className="s6";


        var Cell_6=tableRow.insertCell(6);
        Cell_6.innerHTML='<input type="checkbox" id="'+oneTask[count].taskId+'">去做<br>';
        Cell_6.className="s6";

}


function addMyTask(){
	var count =0;

	for(var i=0;i<introductionTask.length;i++)
		//是否选中
		if(document.getElementById(introductionTask[i].taskId).checked === true){
			addedTask[count] = introductionTask[i].taskId;
			count++;
			}	

}


//提交按钮的动作
$("#send").click(function(){
   	
    addMyTask();

    $.ajax({
        type : "GET",
        async: false,
        url : "/WorkerController/acceptTask", //利用ajax发起请求，这里写servlet的路径

        data : {
            userId:getUserId(),
            taskId: JSON.stringify(addedTask)
            //areaLabelJson: "Hello"
        },
        success: function(data) {
            alert("Success!");
            jumpToAnotherPage(mainPageUrl);
            //页面跳转
        },

        error: function () {
            alert("Wrong!");
        }
    });

});
