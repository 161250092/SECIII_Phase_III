var userId;


$(function(){
    userId = sessionStorage.getItem('userId');
});

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




//各页面URL
const mainPageUrl = "../mainPage/MainPage.html";
const loginPageUrl = "../login/index.html";
const registerPageUrl = "../register/index.html";

const doTaskPageUrl = "../worker/doTask.html";
const getTaskPageUrl = "../worker/getTask.html";
const workerCheckTaskPageUrl = "../worker/WorkerCheckTask.html";

const checkMyTaskPageUrl = "../requestor/CheckMyTask.html";
const uploadTaskPageUrl = "../requestor/UploadTask.html";

const lineMarkPageUrl = "../markLabel/markAreaLabel.html";
const markFrameLabelPageUrl = "../markLabel/markFrameLabel.html";
const overAllPageUrl = "../markLabel/markImageLabel.html";


const adminPageUrl = "../admin/index.html";


// const mainPageUrl = "../../MainPage.html";
// const loginPageUrl = "../loginBL/index.html";
// const registerPageUrl = "../registerBL/registerBL.html";
//
// const doTaskPageUrl = "doTask.html";
// const getTaskPageUrl = "getTask.html";
// const workerCheckTaskPageUrl = "WorkerCheckTask.html";
//
// const checkMyTaskPageUrl = "CheckMyTask.html";
// const uploadTaskPageUrl = "UploadTask.html";
//
// const lineMarkPageUrl = "markAreaLabel.html";
// const markFrameLabelPageUrl = "markFrameLabel.html";
// const overAllPageUrl = "markImageLabel.html";
//
// const checkFrameLabelPageUrl = "CheckFrameLabel.html";
// const checkLineLabelPageUrl = "CheckLineLabel.html";
// const checkOverAllLabelPageUrl = "CheckOverAllLabel.html";
//
// export default {
//     mainPageUrl,
//     loginPageUrl,
//     registerPageUrl,
//
//     doTaskPageUrl,
//     getTaskPageUrl,
//     workerCheckTaskPageUrl,
//
//     checkMyTaskPageUrl,
//     uploadTaskPageUrl,
//
//     lineMarkPageUrl,
//     markFrameLabelPageUrl,
//     overAllPageUrl,
//
//     checkFrameLabelPageUrl,
//     checkLineLabelPageUrl,
//     checkOverAllLabelPageUrl
// };





