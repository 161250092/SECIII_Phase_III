//各页面URL
const mainPageUrl = "../mainPage/MainPage.html";
const loginPageUrl = "../login/index.html";
const registerPageUrl = "../register/index.html";

const doTaskPageUrl = "../worker/doTask.html";
const getTaskPageUrl = "../worker/getTask.html";
const workerCheckTaskPageUrl = "../worker/WorkerCheckTask.html";

const checkMyTaskPageUrl = "../requestor/CheckMyTask.html";
const uploadTaskPageUrl = "../requestor/uploadTask.html";

const lineMarkPageUrl = "../markLabel/markAreaLabel.html";
const markFrameLabelPageUrl = "../markLabel/markFrameLabel.html";
const overAllPageUrl = "../markLabel/markImageLabel.html";

const adminPageUrl = "../admin/index.html";


function sendUserId(userId){
    sessionStorage.setItem('userId',userId);
}

function getUserId() {
    return sessionStorage.getItem('userId');
}

function removeUserId() {
    sessionStorage.removeItem('userId');
}

function sendTaskId(taskId){
    sessionStorage.setItem('taskId',taskId);
}

function getTaskId(taskId) {
    return sessionStorage.getItem('taskId');
}

function removeTaskId() {
    sessionStorage.removeItem('taskId');
}

function jumpToAnotherPage(url){
    window.location.href = url;
}

function jumToTask(url,userId,taskId){
    sendUserId(taskId);
    sendUserId(userId);
    window.location.href = url;
}