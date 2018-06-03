package maven.businessLogic.adminBL;

import maven.data.AdminData.AdminDataService;
import maven.model.statistics.WebsiteStatistics;

import java.util.List;

public class AdminBLImpl implements AdminBLService {

    private AdminDataService adminDataService;

    public AdminBLImpl(){
        //taskDataService = new TaskDataImpl();
        //userDataService = new UserDataImpl();
    }

    @Override
    public WebsiteStatistics getWebsiteStatistics() {
        return null;
    }


    //public WebsiteStatistics getWebsiteStatistics(){
    //    int numOfUsers = 0;
    //    int numOfTasks = 0;
    //    int numOfIncompleteTasks = 0;
    //    int numOfAccomplishedTasks = 0;
    //
    //    List<User> userList = userDataService.getAllUser();
    //    String userId = null;
    //    numOfUsers = userList.size();
    //    for(User user : userList){
    //        userId = user.getUserId();
    //        numOfAccomplishedTasks += taskDataService.getAllAccomplishedAssignedTaskID(userId).size();
    //        numOfIncompleteTasks += taskDataService.getAllIncompleteAssignedTaskID(userId).size();
    //    }
    //    numOfTasks = numOfIncompleteTasks + numOfAccomplishedTasks;
    //
    //    return new WebsiteStatistics(numOfUsers, numOfTasks, numOfIncompleteTasks, numOfAccomplishedTasks);
    //}
}
