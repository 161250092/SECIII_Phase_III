var AllUnfinishedTasks = new Array();

var AllFinishedTasks = new Array();


//推送的任务
var introductionTask = new Array();
//接受的任务
var addedTask = new Array();



var score;

var ranking;

var type1="整体标注";
var type2="区域标注";
var type3="方框标注";


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

