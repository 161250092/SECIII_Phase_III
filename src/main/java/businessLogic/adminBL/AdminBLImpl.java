package businessLogic.adminBL;

import data.TaskData.TaskDataImpl;
import data.TaskData.TaskDataService;
import data.UserData.UserDataImpl;
import data.UserData.UserDataService;
import model.po.User;
import model.po.WebsiteStatistics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class AdminBLImpl implements AdminBLService {

    private TaskDataService taskDataService;
    private UserDataService userDataService;

    public AdminBLImpl(){
        taskDataService = new TaskDataImpl();
        userDataService = new UserDataImpl();
    }


    public WebsiteStatistics getWebsiteStatistics(){
        int numOfUsers = 0;
        int numOfTasks = 0;
        int numOfIncompleteTasks = 0;
        int numOfAccomplishedTasks = 0;

        List<User> userList = userDataService.getAllUser();
        String userId = null;
        numOfUsers = userList.size();
        for(User user : userList){
            userId = user.getUserId();
            numOfAccomplishedTasks += taskDataService.getAllAccomplishedAssignedTaskID(userId).size();
            numOfIncompleteTasks += taskDataService.getAllIncompleteAssignedTaskID(userId).size();
        }
        numOfTasks = numOfIncompleteTasks + numOfAccomplishedTasks;

        return new WebsiteStatistics(numOfUsers, numOfTasks, numOfIncompleteTasks, numOfAccomplishedTasks);
    }
}