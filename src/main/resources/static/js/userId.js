var userId;


$(function(){
    userId = sessionStorage.getItem('userId');
});

function sendUserId(userId){
    sessionStorage.setItem('userId',userId);
}




