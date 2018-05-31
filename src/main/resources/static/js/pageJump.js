//各页面URL
const mainPageUrl = "../mainPage/MainPage.html";
const loginPageUrl = "../login/index.html";
const registerPageUrl = "../register/index.html";

const doTaskPageUrl = "../worker/doTask.html";
const getTaskPageUrl = "../worker/getTask.html";
const workerCheckTaskPageUrl = "../worker/WorkerCheckTask.html";

const checkMyTaskPageUrl = "../requestor/CheckMyTask.html";
const uploadTaskPageUrl = "../requestor/submitTaskDraft.html";

const markAreaLabelPageUrl = "../markLabel/markAreaLabel.html";
const markFrameLabelPageUrl = "../markLabel/markFrameLabel.html";
const markImageLabelPageUrl = "../markLabel/markImageLabel.html";

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

function sendUsername(username){
    sessionStorage.setItem('username', username);
}

function getUsername() {
    return sessionStorage.getItem('username');
}

function removeUsername() {
    sessionStorage.removeItem('username');
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
    sendTaskId(taskId);
    sendUserId(userId);
    window.location.href = url;
}

function sendUserType(userTypeStr){
    sessionStorage.setItem('userType',userTypeStr);
}

function getUserType() {
    return sessionStorage.getItem('userType');
}

function removeUserType() {
    sessionStorage.removeItem('userType');
}


function setUserCanLabel(boolean) {
    sessionStorage.setItem('isUserCanLabel',boolean);
}

function isUserCanLabel() {
    if(sessionStorage.getItem('isUserCanLabel') === "true"){
        return true;
    }else{
        return false;
    }
}

function removeAllParameter() {
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('taskId');
    sessionStorage.removeItem('isUserCanLabel');
    sessionStorage.removeItem('userType');
}