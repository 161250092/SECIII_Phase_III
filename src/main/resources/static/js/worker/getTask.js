window.onload=function(){
    //清空表格内容
    clearRow("TaskTable");
    getMyIntroductionTask(userId);

    for(var i=0;i<introductionTask.length;i++)
        addRow(introductionTask[i]);

}



function getMyIntroductionTask(userId){

	$.ajax({
			type: "GET",
			url:"/taskController/getMyIntroductionTask",
			data:{
				user:userId
			},

			success:function(myIntroductionTaskJson){
				introductionTask = JSON.parse(myIntroductionTaskJson);
			}

	});
}



function addRow(singleTask){
        
		var z =$("mytable").rows.length
//table 添加内容
        var tableRow=$("mytable").insertRow(z);
        
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


        var Cell_6=tableRow.insertCell(6);
        Cell_6.innerHTML='<input type="checkbox" id="'+singleTask.taskId+'">去做<br>';
        Cell_6.className="s6";

}




function addMyTask(userId){
	var count =0;

	for(var i=0;i<introductionTask.length;i++)
		//是否选中
		if($(introductionTask[i].taskId).checked == true){
			addedTask[count] = introductionTask[i]
			count++;
			}	

}


//提交按钮的动作
$("#send").click(function(){
   	
    addMyTask(userId);

    $.ajax({
        type : "POST",
        url : "/taskController/addTask", //利用ajax发起请求，这里写servlet的路径

        data : {
            addedTaskJson: JSON.stringify(addedTask)
            //areaLabelJson: "Hello"
        },
        success: function(data) {
            alert("Success!");
            //页面跳转
        },

        error: function () {
            alert("Wrong!");
        }
    });

});
