package com.example.maven.data.UserData;


import com.example.maven.model.po.User;
import com.example.maven.data.fileHelper.FileHelper;

import java.util.ArrayList;

import java.util.List;
import java.io.File;

public class UserDataImpl implements UserDataService{
    /**
     *获取用户信息
     */
    public List<User> getAllUser(){
        String FilePath = System.getProperty("user.dir").toString() + "/src/main/User";

        File file = new File(FilePath);
        File[] listOfUser = file.listFiles();

        List<User> User = new ArrayList<User>();

        for(int j = 0;j < listOfUser.length;j++){

            String filePath = FilePath + "/" + listOfUser[j].getName();

            String userInformation = filePath + "/" + listOfUser[j].getName() + ".txt";

            String AcceptedTask = filePath + "/AcceptedTask";
            String AAccomplishedTask = AcceptedTask + "/AccomplishedTask";
            String AIncompleteTask = AcceptedTask + "/IncompleteTask";

            String PublishedTask = filePath + "/PublishedTask";
            String PAccomplishedTask = PublishedTask + "/AccomplishedTask";
            String PIncompleteTask = PublishedTask + "/IncompleteTask";

            ArrayList<String> publishedTask = new ArrayList<String>();
            ArrayList<String> acceptedTask = new ArrayList<String>();

            File pATask = new File(PAccomplishedTask);
            File[] PATask = pATask.listFiles();

            File pITask = new File(PIncompleteTask);
            File[] PITask = pITask.listFiles();

            File aATask = new File(AAccomplishedTask);
            File[] AATask = aATask.listFiles();

            File aITask = new File(AIncompleteTask);
            File[] AITask = aITask.listFiles();

            for(int i = 0;i < PATask.length;i++) {
                String name = PATask[i].getName();
                String[] Name = name.split(".");
                publishedTask.add(Name[0]);
            }

            for(int i = 0; i < PITask.length;i++) {
                String name = PITask[i].getName();
                String[] Name = name.split(".");
                publishedTask.add(Name[0]);
            }

            for(int i = 0; i < AATask.length;i++){
                String name = AATask[i].getName();
                String[] Name = name.split(".");
                acceptedTask.add(Name[0]);
            }

            for(int i = 0;i < AITask.length;i++){
                String name = AITask[i].getName();
                String[] Name = name.split(".");
                acceptedTask.add(Name[0]);
            }


            FileHelper fh = new FileHelper();
            String useR = fh.readFile(userInformation);
            String[] usER = useR.split(",");
            User user = new User(usER[0],usER[1],usER[2],publishedTask,acceptedTask,Integer.parseInt(usER[3]),Boolean.getBoolean(usER[4]));

            User.add(user);
        }

        return User;
    }

    /**
     *创建单个用户
     *创建存储数据需要的文件夹
     * @return 用户ID
     */
    public String newUser(String userName,String password){
        //文件地址
        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        //获取用户ID
        File User = new File(filePath);
        File[] listOfUser = User.listFiles();

        int ID = listOfUser.length;

        int a = ID;
        int count = 0;
        while(a != 0){
            a = a / 10;
            count++;
        }

        String userId = String.valueOf(ID);
        for(int i = 0;i < 8 - count;i++)
            userId = "0" + userId;

        //创建用户文件夹
        filePath = filePath + "/" + userId;
        FileHelper fh = new FileHelper();

        fh.createFile(filePath);

        //创建用户数据txt文件
        String userInformation = filePath + "/" + userId + ".txt";

        String AcceptedTask = filePath + "/AcceptedTask";
        String AAccomplishedTask = AcceptedTask + "/AccomplishedTask";
        String AIncompleteTask = AcceptedTask + "/IncompleteTask";

        String PublishedTask = filePath + "/PublishedTask";
        String PAccomplishedTask = PublishedTask + "/AccomplishedTask";
        String PIncompleteTask = PublishedTask + "/IncompleteTask";

        fh.createFile(userInformation);

        fh.createFile(AcceptedTask);
        fh.createFile(AAccomplishedTask);
        fh.createFile(AIncompleteTask);

        fh.createFile(PublishedTask);
        fh.createFile(PAccomplishedTask);
        fh.createFile(PIncompleteTask);

        //写用户数据
        //文件内容：用户ID，用户名，密码，积分，是否为管理员
        String user = userId + "," +  userName + "," + password + ",0,false\r\n";

        fh.writeFile(userInformation,user);

        return userId;
    }

    /**
     *获取单个用户信息
     */
    public User getUser(String userId){

        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        File User = new File(filePath);
        File[] listOfUser = User.listFiles();


        boolean isFind = false;
        for(int i = 0; i < listOfUser.length;i++){
            if(userId.equals(listOfUser[i].getName())) {
                filePath = filePath + "/" + userId;
                isFind = true;
            }
        }

        String AcceptedTask = filePath + "/AcceptedTask";
        String AAccomplishedTask = AcceptedTask + "/AccomplishedTask";
        String AIncompleteTask = AcceptedTask + "/IncompleteTask";

        String PublishedTask = filePath + "/PublishedTask";
        String PAccomplishedTask = PublishedTask + "/AccomplishedTask";
        String PIncompleteTask = PublishedTask + "/IncompleteTask";

        if(isFind) {

            ArrayList<String> publishedTask = new ArrayList<String>();
            ArrayList<String> acceptedTask = new ArrayList<String>();

            File pATask = new File(PAccomplishedTask);
            File[] PATask = pATask.listFiles();

            File pITask = new File(PIncompleteTask);
            File[] PITask = pITask.listFiles();

            File aATask = new File(AAccomplishedTask);
            File[] AATask = aATask.listFiles();

            File aITask = new File(AIncompleteTask);
            File[] AITask = aITask.listFiles();

            for (int i = 0; i < PATask.length; i++) {
                String name = PATask[i].getName();
                String[] Name = name.split(".");
                publishedTask.add(Name[0]);
            }

            for (int i = 0; i < PITask.length; i++) {
                String name = PITask[i].getName();
                String[] Name = name.split(".");
                publishedTask.add(Name[0]);
            }

            for (int i = 0; i < AATask.length; i++) {
                String name = AATask[i].getName();
                String[] Name = name.split(".");
                acceptedTask.add(Name[0]);
            }

            for (int i = 0; i < AITask.length; i++) {
                String name = AITask[i].getName();
                String[] Name = name.split(".");
                acceptedTask.add(Name[0]);
            }

            FileHelper fh = new FileHelper();
            String userInformation = filePath + "/" + userId + ".txt";
            String useR = fh.readFile(filePath);
            String[] usER = useR.split(",");
            User user = new User(usER[0], usER[1], usER[2], publishedTask, acceptedTask, Integer.parseInt(usER[3]), Boolean.getBoolean(usER[4]));

            return user;
        }
        else
            return null;
    }

    /**
     *修改用户积分
     * @param score 积分更改数值
     */
    public boolean reviseScore(String userId,int score){
        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

        boolean result = false;

        boolean isFind = false;

        File User = new File(filePath);
        File[] listOfUser = User.listFiles();

        for(int i = 0; i < listOfUser.length;i++){
            if(userId.equals(listOfUser[i].getName())){
                filePath = filePath + "/userId";
                isFind = true;
            }

        }

        if(isFind){

            FileHelper fh = new FileHelper();

            filePath = filePath + "/" + userId + ".txt";

            String useR = fh.readFile(filePath);
            String[] usER = useR.split(",");

            int Score = Integer.parseInt(usER[3]);

            Score = Score + score;

            usER[3] = String.valueOf(Score);

            String user = usER[0] + "," + usER[1] + "," + usER[2] + "," + usER[3] + "," + usER[4];

            result = fh.writeFile(filePath,user);
        }

        return result;
    }
}
