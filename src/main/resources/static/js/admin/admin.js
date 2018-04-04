function WebsiteStatistics(numOfUsers, numOfTasks, numOfIncompleteTasks, numOfAccomplishedTask){
    this.numOfUsers = numOfUsers;
    this.numOfTasks = numOfTasks;
    this.numOfIncompleteTasks = numOfIncompleteTasks;
    this.numOfAccomplishedTask = numOfAccomplishedTask;
}

window.onload = function (ev) {
    $.ajax({
        type: "GET",
        url: "/AdministerController/uploadImages",

        success:function (result) {
            var websiteStatistics = JSON.parse(result);
            $("#numOfUsers").html(websiteStatistics.numOfUsers);
            $("#numOfTasks").html(websiteStatistics.numOfTasks);
            $("#numOfIncompleteTasks").html(websiteStatistics.numOfIncompleteTasks);
            $("#numOfAccomplishedTask").html(websiteStatistics.numOfAccomplishedTask);
        }
    });
}