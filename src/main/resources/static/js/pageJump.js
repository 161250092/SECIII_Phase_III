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

function removeUserCanLabel() {
    sessionStorage.removeItem('isUserCanLabel');
}

function isUserCanLabel() {
    return sessionStorage.getItem('isUserCanLabel') === "true";
}

function setWorkerIdWhenLabel(workerId){
    sessionStorage.setItem('workerIdWhenLabel',workerId);
}
function getWorkerIdWhenLabel(workerId) {
    return sessionStorage.getItem('workerIdWhenLabel');
}
function removeWorkerIdWhenLabel() {
    sessionStorage.removeItem('workerIdWhenLabel')
}

function jumpToTask(url,workerId,taskId,userType,isUserCanLabel){
    sendTaskId(taskId);
    setWorkerIdWhenLabel(workerId);
    sendUserType(userType);
    setUserCanLabel(isUserCanLabel);
    jumpToAnotherPage(url);
}

function leaveTheTask() {
    removeTaskId();
    removeWorkerIdWhenLabel();
    removeUserCanLabel();
}

function removeAllParameter() {
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('taskId');
    sessionStorage.removeItem('isUserCanLabel');
    sessionStorage.removeItem('userType');
}


//各页面URL
/* admin */
const adminPageUrl = "../admin/index.html";

/* login */
const loginPageUrl = "../login/login.html";

/* mainPage */
const requestorMainPageUrl = "../mainPage/requestorMainPage.html";
const workerMainPageUrl = "../mainPage/workerMainPage.html";

/* markLabel */
const markAreaLabelPageUrl = "../markLabel/markAreaLabel.html";
const markFrameLabelPageUrl = "../markLabel/markFrameLabel.html";
const markImageLabelPageUrl = "../markLabel/markImageLabel.html";

/* register */
const registerPageUrl = "../register/login.html";

/* requestor */
const assignTaskPageUrl = "../requestor/assignTask.html";
const checkSubmittedLabelPageUrl = "../requestor/checkSubmittedLabel.html";
const finishedTasksPageUrl = "../requestor/finishedTasksPage.html";
const personPageUrl = "../requestor/personPage.html";
const submitTaskDraftPageUrl = "../requestor/submitTaskDraft.html";
const unfinishedTasksPage = "../requestor/unfinishedTasksPage.html";

/* worker */
const doTaskPageUrl = "../worker/doTask.html";
const getTaskPageUrl = "../worker/getTask.html";
const workerFinishedTaskPageUrl = "../worker/workerFinishedTaskPage.html";
const workerInfoPageUrl = "../worker/workerInfoPage.html";

const messagePagePageUrl ="../worker/messagePage.html";




const IMAGE_LABEL_TYPENAME = "ImageLabel";
const FRAME_LABEL_TYPENAME = "FrameLabel";
const AREA_LABEL_TYPENAME = "AreaLabel";



const USERTYPE_REQUESTOR = "requestor";
const USERTYPE_WORKER = "worker";