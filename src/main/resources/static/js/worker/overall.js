window.onload=function(){

    for(var i=0;i<AllUnfinishedTasks.length;i++){
        if(AllUnfinishedTasks[i].labelType==type1)
            insertOption(AllUnfinishedTasks[i].taskId,type1);
    }
};


