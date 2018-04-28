package com.example.maven.data.TaskData;

import com.example.maven.data.fileHelper.FileHelper;
import com.example.maven.model.po.Task;

import java.io.*;
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

        //用户文件夹
        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        File user = new File(filePath);
        File[] User = user.listFiles();

        //找用户
        boolean isFind = false;

        for(int i = 0;i < User.length;i++){
            if(User[i].getName().equals(userId)){
                filePath = filePath + "/" + userId;
                isFind = true;
            }
        }

        //找到用户
        if(isFind){
            //加载任务
            filePath = filePath + "/PublishedTask/IncompleteTask";

            File piTask = new File(filePath);
            File[] pITask = piTask.listFiles();

            for(int i = 0;i < pITask.length;i++){
                String name = pITask[i].getName();
                if(!name.equals("null.txt")){
                    String[] Name = name.split("\\.");
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

       //用户文件夹
       String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

       //找用户
       File user = new File(filePath);
       File[] User = user.listFiles();

       boolean isFind = false;
       for(int i = 0;i < User.length;i++){
           if(User[i].getName().equals(userId)){
               filePath = filePath + "/" + userId;
               isFind = true;
           }
       }

       //找到用户
       if (isFind){
           //加载任务数据
           filePath = filePath + "/PublishedTask/AccomplishedTask";

           File paTask = new File(filePath);
           File[] pATask = paTask.listFiles();

           for(int i = 0;i < pATask.length;i++){
               String name = pATask[i].getName();
               if(!name.equals("null.txt")){
                   String[] Name = name.split("\\.");
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

        //用户文件夹
        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        //找用户
        File user = new File(filePath);
        File[] User = user.listFiles();

        boolean isFind = false;
        for(int i = 0;i < User.length;i++){
            if(User[i].getName().equals(userId)) {
                filePath = filePath + "/" + userId;
                isFind = true;
            }
        }

        //找到用户
        if(isFind){
            //加载任务数据
            filePath = filePath + "/AcceptedTask/IncompleteTask";
            File aiTask = new File(filePath);

            File[] aITask = aiTask.listFiles();

            for(int i = 0;i < aITask.length;i++){
                String name = aITask[i].getName();
                if(!name.equals("null.txt")){
                    String[] Name = name.split("\\.");
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

        //用户文件夹
        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        //找用户
        File user = new File(filePath);
        File[] User = user.listFiles();

        boolean isFind = false;
        for(int i = 0;i < User.length;i++){
            if(User[i].getName().equals(userId)){
                filePath = filePath +"/" + userId;
                isFind = true;
            }
        }

        //找到用户
        if(isFind){
            //加载任务数据
            filePath = filePath + "/AcceptedTask/AccomplishedTask";

            File aaTask = new File(filePath);
            File[] aATask = aaTask.listFiles();

            for(int i = 0;i < aATask.length;i++){
                String name = aATask[i].getName();
                if(!name.equals("null.txt")){
                    String[] Name = name.split("\\.");
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

        //用户文件夹
        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        //找用户
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
            filePath = filePath + "/PublishedTask/IncompleteTask";

            //Gson转化task对象
            Gson gson = new GsonBuilder().create();
            String content = gson.toJson(task);

            filePath = filePath + "/" + task.getTaskId() + ".txt";

            FileHelper fh = new FileHelper();
            result = fh.writeFile(filePath,content);
        }

        return result;
    }

    /**
     * 修改任务信息：完成度+1
     */
    public Boolean reviseTask(String taskId){
        boolean result = false;

        //用户文件夹
        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        //用户ID
        String[] taskInformation = taskId.split("_");
        String userId = taskInformation[0];

        //找用户
        File user = new File(filePath);
        File[] User = user.listFiles();

        boolean isFind = false;

        for(int i = 0;i < User.length;i++){
            if(User[i].getName().equals(userId)){
                filePath = filePath + "/" + userId;
                isFind = true;
            }
        }

        //找到用户
        if(isFind){
            //任务文件夹
            String PITaskFile = filePath + "/PublishedTask/IncompleteTask";

            PITaskFile = PITaskFile + "/" + taskId + ".txt";

            File PITask = new File(filePath);

            FileHelper fh = new FileHelper();

            String PITaskInformation = fh.readFile(PITaskFile);

            //gson读取task对象
            Gson gson = new GsonBuilder().create();

            Task task = gson.fromJson(PITaskInformation,Task.class);

            //任务未完成
            if(task.getRequiredNumber() > task.getFinishedNumber()){


                task.setFinishedNumber(task.getFinishedNumber() + 1);
                PITaskInformation = gson.toJson(task);

                //加一后还未完成
                if(task.getFinishedNumber() < task.getRequiredNumber()){

                    fh.writeFile(PITaskFile, PITaskInformation);
                }
                //加一后完成
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
                String[] name = pa[i].getName().split("\\.");
                if(name[0].equals(taskId)){
                    filePath = PA + "/" + taskId + ".txt";
                    taskIsFind = true;
                 }
            }

            File Pi = new File(PI);
            File[] pi = Pi.listFiles();

            for(int i = 0;i < pi.length;i++){
                String name = pi[i].getName();
                if(name.split("\\.")[0].equals(taskId)){
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

    /**
     * 转移已经完成标注的任务标注数据
     * @param userId
     * @param taskId
     * @return
     */
    public boolean setAcceptedTaskAccomplished(String userId,String taskId){
        boolean result = false;
        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        File user = new File(filePath);
        File[] User = user.listFiles();

        boolean userIsFind = false;

        for(int i = 0;i < User.length;i++){
            if(User[i].getName().equals(userId)){
                filePath = filePath + "/" + userId;
                userIsFind = true;
            }
        }

        boolean taskIsFind = false;

        filePath = filePath + "/AcceptedTask/IncompleteTask";
        if(userIsFind){

            File Task = new File(filePath);
            File[] task = Task.listFiles();

            for(int i = 0;i < task.length;i++){
                String Name = task[i].getName();
                if(Name.split("\\.")[0].equals(taskId)){
                    filePath = filePath + "/" + taskId + ".txt";
                    taskIsFind = true;
                }
            }

        }

        if(taskIsFind){
            File delete = new File(filePath);

            String AA = System.getProperty("user.dir").toString() + "/src/main/User/" + userId +
                    "/AcceptedTask/AccomplishedTask/" + taskId + ".txt";

            File move = new File(AA);

            FileHelper fh = new FileHelper();
            fh.createFile(AA);

            FileReader fr =  null;
            BufferedReader br = null;

            FileWriter fw = null;
            BufferedWriter bw = null;

            try{
                fr = new FileReader(delete);
                br = new BufferedReader(fr);

                fw = new FileWriter(move);
                bw = new BufferedWriter(fw);

                String s;
                while((s = br.readLine()) != null){
                    bw.write(s + "\r\n");
                }

                br.close();
                fr.close();
                bw.close();
                fw.close();

                boolean temp = delete.delete();
                System.out.println(temp);

                result = true;

            }catch(IOException e){
                e.printStackTrace();
            }
        }

        return result;
    }
}
