package com.example.maven.businessLogic.workerBL;

import com.example.maven.data.LabelData.LabelDataImpl;
import com.example.maven.data.LabelData.LabelDataService;
import com.example.maven.data.TaskData.TaskDataImpl;
import com.example.maven.data.TaskData.TaskDataService;
import com.example.maven.data.UserData.UserDataImpl;
import com.example.maven.data.UserData.UserDataService;
import com.example.maven.model.po.Label;
import com.example.maven.model.po.Task;
import com.example.maven.model.po.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WorkerBLImpl implements WorkerBLService {
    private LabelDataService labelDataService;
    private TaskDataService taskDataService;
    private UserDataService userDataService;

    public WorkerBLImpl(){
        labelDataService = new LabelDataImpl();
        taskDataService = new TaskDataImpl();
        userDataService = new UserDataImpl();
    }

    @Override
    public List<Task> getAcceptedAndAccomplishedTaskList(String userId) {
        return getTaskList(userId, true);
    }

    @Override
    public List<Task> getAcceptedButIncompleteTaskList(String userId) {
        return  getTaskList(userId, false);
    }

    @Override
    public List<Task> getAvailableTaskList() {
        List<Task> taskList = new ArrayList<>();
        List<String> temp = null;
        List<User> userList = userDataService.getAllUser();

        for(User user : userList){
            //获取该用户发布且未完成的任务Id列表
            temp = taskDataService.getAllIncompleteAssignedTaskID(user.getUserId());
        }
        return taskList;
    }

    @Override
    public boolean acceptTask(String userId, String taskIdListJSON) {
        Gson gson = new GsonBuilder().create();
        List<String> taskIdList = gson.fromJson(taskIdListJSON, ArrayList.class);

        for(String taskId : taskIdList){
            //获取任务详情
            Task task = taskDataService.getTask(taskId);
            //若任务为null 则报错
            if(task == null)
                return false;

            //获取任务包含的图片数量
            int imageNumber = task.getImageInformation().length;

            //如果后端调用失败 则报错
            if(!labelDataService.acceptTask(userId, taskId, imageNumber))
                return false;
        }

        return true;
    }

    @Override
    public Task getTaskById(String taskId) {
        return taskDataService.getTask(taskId);
    }

    @Override
    public int getUserScore(String userId) {
        User user = userDataService.getUser(userId);
        return user.getScore();
    }

    @Override
    public int getUserRanking(String userId) {
        List<User> userList = userDataService.getAllUser();

        //自定义Comparator，为User类提供排序的比较方法
        Comparator comparator = new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                //按照积分比较 进行排序
                if((int)user1.getScore() <= user2.getScore())
                    return 1;
                else
                    return -1;
            }
        };

        //排序
        Collections.sort(userList, comparator);

        User user = userDataService.getUser(userId);

        return userList.indexOf(user)+1;
    }

    private List<Task> getTaskList(String userId, boolean isAccomplised){
        List<Task> taskList = new ArrayList<>();
        List<String> taskIdList = null;
        Task task = null;

        if(isAccomplised)
            taskIdList = taskDataService.getAllAccomplishedAcceptedTaskID(userId);
        else
            taskIdList = taskDataService.getAllIncompleteAcceptedTaskID(userId);

        if(taskIdList == null || taskIdList.size() == 0)
            return null;
        else{
            for(int i = 0; i < taskIdList.size(); i++){
                task = taskDataService.getTask(taskIdList.get(i));
                //假如无法通过taskId找到对应的Task，则返回null
                if(task == null)
                    return null;
                taskList.add(task);
            }
            return taskList;
        }
    }



}
