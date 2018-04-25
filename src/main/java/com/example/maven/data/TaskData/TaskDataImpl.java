package com.example.maven.data.TaskData;

import com.example.maven.data.fileHelper.FileHelper;
import com.example.maven.model.po.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TaskDataImpl implements TaskDataService{
    /**
     *获取用户发布的所有未完成任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllIncompleteAssignedTaskID(String userId){
        List<String> PITask = new ArrayList<String>();

        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        File user = new File(filePath);
        File[] User = user.listFiles();

        boolean isFind = false;

        for(int i = 0;i < User.length;i++){
            if(User[i].getName().equals(userId)){
                filePath = filePath + "/" + userId;
                isFind = true;
            }
        }

        if(isFind){
            filePath = filePath + "/PublishedTask/IncompleteTask";

            File piTask = new File(filePath);
            File[] pITask = piTask.listFiles();

            for(int i = 0;i < pITask.length;i++){
                String name = pITask[i].getName();
                if(!name.equals("null.txt")){
                    String[] Name = name.split(",");
                    PITask.add(Name[0]);
                }
            }
        }

        return PITask;

    }

    /**
     *获取用户发布的所有完成的任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllAccomplishedAssignedTaskID(String userId){
       List<String> PATask = new ArrayList<String>();

       String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

       File user = new File(filePath);
       File[] User = user.listFiles();

       boolean isFind = false;
       for(int i = 0;i < User.length;i++){
           if(User[i].getName().equals(userId)){
               filePath = filePath + "/" + userId;
               isFind = true;
           }
       }

       if (isFind){
           filePath = filePath + "/PublishedTask/AccomplishedTask";

           File paTask = new File(filePath);
           File[] pATask = paTask.listFiles();

           for(int i = 0;i < pATask.length;i++){
               String name = pATask[i].getName();
               if(!name.equals("null.txt")){
                   String[] Name = name.split(",");
                   PATask.add(Name[0]);
               }
           }

       }

       return PATask;
    }

    /**
     * 获取用户接受的所有未完成任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllIncompleteAcceptedTaskID(String userId){
        List<String> AITask = new ArrayList<String>();

        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        File user = new File(filePath);
        File[] User = user.listFiles();

        boolean isFind = false;
        for(int i = 0;i < User.length;i++){
            if(User[i].getName().equals(userId)) {
                filePath = filePath + "/" + userId;
                isFind = true;
            }
        }

        if(isFind){
            filePath = filePath + "/AcceptedTask/IncompleteTask";
            File aiTask = new File(filePath);

            File[] aITask = aiTask.listFiles();

            for(int i = 0;i < aITask.length;i++){
                String name = aITask[i].getName();
                if(!name.equals("null.txt")){
                    String[] Name = name.split(",");
                    AITask.add(Name[0]);
                }
            }
        }

        return AITask;
    }

    /**
     * 获取用户接受的所有完成任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllAccomplishedAcceptedTaskID(String userId){
        List<String> AATask = new ArrayList<String>();

        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        File user = new File(filePath);
        File[] User = user.listFiles();

        boolean isFind = false;
        for(int i = 0;i < User.length;i++){
            if(User[i].getName().equals(userId)){
                filePath = filePath +"/" + userId;
                isFind = true;
            }
        }

        if(isFind){
            filePath = filePath + "/AcceptTask/AccomplishedTask";

            File aaTask = new File(filePath);
            File[] aATask = aaTask.listFiles();

            for(int i = 0;i < aATask.length;i++){
                String name = aATask[i].getName();
                if(!name.equals("null.txt")){
                    String[] Name = name.split(",");
                    AATask.add(Name[0]);
                }
            }

        }

        return AATask;
    }

    /**
     * 任务发布时保存任务信息
     */
    public Boolean saveTask(String userId,Task task){
        boolean result = false;

        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        File user = new File(filePath);
        File[] User = user.listFiles();

        boolean isFind = false;
        for(int i = 0;i < User.length;i++){
            if(User[i].getName().equals(userId)){
                filePath = filePath +"/" + userId;
                isFind = true;
            }
        }

        filePath = filePath + "/PublishedTask/IncompleteTask";

        Gson gson = new GsonBuilder().create();
        String content = gson.toJson(task);

        filePath = filePath + "/" + task.getTaskId() + ".txt";

        FileHelper fh = new FileHelper();
        result = fh.writeFile(filePath,content);

        return result;
    }

    /**
     * 修改任务信息：完成度+1
     */
    public Boolean reviseTask(String taskId){
        boolean result = false;

        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        String[] taskInformation = taskId.split("_");
        String userId = taskInformation[0];

        File user = new File(filePath);
        File[] User = user.listFiles();

        boolean isFind = false;

        for(int i = 0;i < User.length;i++){
            if(User[i].getName().equals(userId)){
                filePath = filePath + "/" + userId;
                isFind = true;
            }
        }

        if(isFind){
            String PITaskFile = filePath + "/PublishedTask/IncompleteTask";

            PITaskFile = PITaskFile + "/" + taskId + ".txt";

            File PITask = new File(filePath);

            FileHelper fh = new FileHelper();

            String PITaskInformation = fh.readFile(PITaskFile);

            Gson gson = new GsonBuilder().create();

            Task task = gson.fromJson(PITaskInformation,Task.class);

            if(task.getRequiredNumber() > task.getFinishedNumber()){

                task.setFinishedNumber(task.getFinishedNumber() + 1);
                PITaskInformation = gson.toJson(task);

                if(task.getFinishedNumber() < task.getRequiredNumber()){

                    fh.writeFile(PITaskInformation,PITaskFile);
                }
                else{
                    String PATaskFile = filePath + "/PublishedTask/AccomplishedTask" + "/" + taskId  + ".txt";

                    File piTask = new File(PITaskFile);
                    piTask.delete();

                    fh.writeFile(PITaskInformation,PATaskFile);
                }

                result = true;
            }

        }

        return result;
    }

    /**
     * 获取单个任务信息
     */
    public Task getTask(String taskId){
        Task task = null;

        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        String[] taskInformation = taskId.split("_");
        String userId = taskInformation[0];

        boolean userIsFind = false;

        File user = new File(filePath);
        File[] User = user.listFiles();

        for(int i = 0;i < User.length;i++){
            if(User[i].getName().equals(userId)){
                filePath = filePath + "/" + userId;
                userIsFind = true;
            }
        }

        boolean taskIsFind = false;

        if(userIsFind){

            String PA = filePath + "/PublishedTask/AccomplishedTask";
            String PI = filePath + "/PublishedTask/IncompleteTask";

            File Pa = new File(PA);
            File[] pa = Pa.listFiles();

            for(int i = 0;i < pa.length;i++){
                if(pa[i].getName().equals(taskId)){
                    filePath = PA + "/" + taskId + ".txt";
                    taskIsFind = true;
                 }
            }

            File Pi = new File(PI);
            File[] pi = Pi.listFiles();

            for(int i = 0;i < pi.length;i++){
                if(pi[i].getName().equals(taskId)){
                    filePath = PI + "/" + taskId + ".txt";
                    taskIsFind = true;
                }
            }

        }

        if(taskIsFind){
            FileHelper fh = new FileHelper();

            String content = fh.readFile(filePath);

            Gson gson = new GsonBuilder().create();

            task = gson.fromJson(content,Task.class);
        }

        return task;
    }
}
