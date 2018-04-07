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
var mainPageUrl = "MainPage.html";
var loginPageUrl = "login.html";
var registerPageUrl = "register.html";

var doTaskPageUrl = "doTask.html";
var getTaskPageUrl = "getTask.html";
var workerCheckTaskPageUrl = "WorkerCheckTask.html";

var checkMyTaskPageUrl = "CheckMyTask.html";
var uploadTaskPageUrl = "UploadTask.html";

var lineMarkPageUrl = "LineMarkPage.html";
var markFrameLabelPageUrl = "markFrameLabel.html";
var overAllPageUrl = "OverAllPage.html";

var checkFrameLabelPageUrl = "CheckFrameLabel.html";
var checkLineLabelPageUrl = "CheckLineLabel.html";
var checkOverAllLabelPageUrl = "CheckOverAllLabel.html";






