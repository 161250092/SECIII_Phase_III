var userId;


$(function(){
    userId = sessionStorage.getItem('userId');
});

function sendUserId(userId){
    sessionStorage.setItem('userId',userId);
}

function jumpToAnotherPage(url,userId){
    sendUserId(userId);
    window.location.href = url;
}


//各页面URL
const mainPageUrl = "../../MainPage.html";
const loginPageUrl = "../login/index.html";
const registerPageUrl = "../register/index.html";

const doTaskPageUrl = "doTask.html";
const getTaskPageUrl = "getTask.html";
const workerCheckTaskPageUrl = "WorkerCheckTask.html";

const checkMyTaskPageUrl = "CheckMyTask.html";
const uploadTaskPageUrl = "UploadTask.html";

const lineMarkPageUrl = "LineMarkPage.html";
const markFrameLabelPageUrl = "markFrameLabel.html";
const overAllPageUrl = "OverAllPage.html";

const checkFrameLabelPageUrl = "CheckFrameLabel.html";
const checkLineLabelPageUrl = "CheckLineLabel.html";
const checkOverAllLabelPageUrl = "CheckOverAllLabel.html";

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
// const lineMarkPageUrl = "LineMarkPage.html";
// const markFrameLabelPageUrl = "markFrameLabel.html";
// const overAllPageUrl = "OverAllPage.html";
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





