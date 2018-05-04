var finished=0;
var unfinished=0;

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

    finished=AllFinishedTasks.length;
    unfinished=AllUnfinishedTasks.length;

    document.getElementById("ranking").value = ranking;
    document.getElementById("score").value = score;


    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    app.title = '环形图';

    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            // data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
            data:['完成','未完成']
        },
        series: [
            {
                name:'完成情况',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '15',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:finished, name:'完成'},
                    {value:unfinished, name:'未完成'},

                ]
            }
        ]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }


}



function getAllUnfinishedTasks(userId){
	$.ajax({
			type: "GET",
        	async: false,
			url:"/WorkerController/getAcceptedButIncompleteTaskList",
			data:{
                userId : userId
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
				userId: userId
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
                userId :  userId
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
                userId :  userId
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




