//下拉框获取任务信息

window.onload=function(){

    for(var i=0;i<AllUnfinishedTasks.length;i++){
        if(AllUnfinishedTasks[i].labelType==type1)
        insertOption(AllUnfinishedTasks[i].taskId,type2);
    }
};


function insertOption(taskId,type)
{
    var y=document.createElement('option');
    y.text=taskId;
    var x;

    if(type==type1)
        x=document.getElementById("overallTask");
    else if(type==type2)
        x=document.getElementById("frameTask");
    else if(type==type3)
        x=document.getElementById("lineTask");

    try
    {
        x.add(y,null); // standards compliant
    }
    catch(ex)
    {
        x.add(y); // IE only
    }
}

//改变图片源
$("#send").click(function(){




});
