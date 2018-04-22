var AllUnfinishedTasks = new Array();

var AllFinishedTasks = new Array();


//推送的任务
var introductionTask = new Array();
//接受的任务
var addedTask = new Array();



var score;

var ranking;

var type1="ImageLabel";
var type2="AreaLabel";
var type3="FrameLabel";


//task class defination
function Task(taskId,labelType,image,introduction,requiredNumber,finishedNumber,score)
{
    this.taskId = taskId;
    this.labelType = labelType;
    this.image = image;
    this.introduction = introduction;
    this.requiredNumber = requiredNumber;
    this.finishedNumber = finishedNumber;
    this.score = score;
}

//TASKVO
function TaskVO(taskId,labelType,ntroduction,requiredNumber,finishedNumber,score) {
    this.taskId = taskId;
    this.labelType = labelType;
    this.introduction = introduction;
    this.requiredNumber = requiredNumber;
    this.finishedNumber = finishedNumber;
    this.score = score;
}



function AreaLabelVO(image,label,linelist){
    this.image  = iamge;
    this.label = label;
    this.linelist = linelist;
}


