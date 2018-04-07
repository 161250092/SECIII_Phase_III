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





