package com.example.maven.data.LabelData;

import com.example.maven.data.fileHelper.FileHelper;
import com.example.maven.model.po.AreaLabel;
import com.example.maven.model.po.ImageLabel;
import com.example.maven.model.po.Label;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

import com.example.maven.model.po.frameLabel.FrameLabel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class LabelDataImpl implements LabelDataService{
    private String UserDirPath = null;

    public LabelDataImpl(){
        FileHelper fileHelper = new FileHelper();
        UserDirPath = fileHelper.getDirectoryPath() + "/User";
    }

    /**
     * 获取用户已经标注的信息
     * @return 标签List 若未标注，图片对应位置返回空对象
     */
    public List<Label> getLabel(String userId, String taskId){
        List<Label> label = new ArrayList<Label>();

//        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";
        String filePath = UserDirPath;

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

            File task = new File(filePath);
            File[] Task = task.listFiles();

            for(int i = 0;i < Task.length;i++){
                String name = Task[i].getName();
                if(name.equals(taskId+".txt")){
                    filePath = filePath + "/" + taskId + ".txt";
                    taskIsFind = true;
                }
            }

        }

        if(taskIsFind){

            File taskInformation = new File(filePath);

            FileReader fr = null;
            BufferedReader br = null;

            try{

                fr = new FileReader(taskInformation);
                br = new BufferedReader(fr);

                String s;
                while((s = br.readLine()) != null){
                    Gson gson = new GsonBuilder().create();

                    String[] name = taskId.split("_");

                    String type = name[1];

                    if(type.equals("ImageLabel")){
                        if (s.equals("#")) {
                            ImageLabel Label = new ImageLabel();
                            label.add(Label);
                        }
                        else{
                            ImageLabel Label = gson.fromJson(s,ImageLabel.class);
                            label.add(Label);
                        }
                    }
                    else if(type.equals("AreaLabel")){
                        if (s.equals("#")) {
                            AreaLabel Label = new AreaLabel();
                            label.add(Label);
                        }
                        else {
                            AreaLabel Label = gson.fromJson(s, AreaLabel.class);
                            label.add(Label);
                        }
                    }
                    else{
                        if (s.equals("#")) {
                            FrameLabel Label = new FrameLabel();
                            label.add(Label);
                        }
                        else {
                            FrameLabel Label = gson.fromJson(s, FrameLabel.class);
                            label.add(Label);
                        }
                    }

                }

                fr.close();
                br.close();

            }catch(IOException e){
                e.printStackTrace();
            }

        }

        return label;
    }

    /**
     * 保存用户标注信息
     * @param imageIndex 对应的图片的位置
     */
    public boolean saveLabel(String userId,String taskId,int imageIndex,Label label){
        boolean result = false;

//        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";
        String filePath = UserDirPath;

        File user = new File(filePath);
        File[] User = user.listFiles();

        boolean userIsFind = false;

        for(int i= 0;i < User.length;i++){
            if(User[i].getName().equals(userId)){
                filePath = filePath + "/" + userId;
                userIsFind = true;
            }
        }


        boolean taskIsFind = false;

        if(userIsFind){
            filePath = filePath + "/AcceptedTask/IncompleteTask";

            File Task = new File(filePath);
            File[] task = Task.listFiles();

            for(int i = 0;i < task.length;i++){
                if(task[i].getName().equals(taskId+".txt")){
                    filePath = filePath + "/" + taskId + ".txt";
                    taskIsFind = true;
                }
            }

        }

        if(taskIsFind){
            File revise = new File(filePath);

            ArrayList<String> a = new ArrayList<String>();

            FileReader fr = null;
            BufferedReader br = null;

            try{

                fr = new FileReader(revise);
                br = new BufferedReader(fr);

                String s;
                while((s = br.readLine()) != null){
                    a.add(s);
                }

                br.close();
                fr.close();

            }catch(IOException e){
                e.printStackTrace();
            }

            revise.delete();

            revise = new File(filePath);

            FileHelper fh = new FileHelper();
            fh.createFile(filePath);

            FileWriter fw = null;
            BufferedWriter bw = null;

            try{

                fw = new FileWriter(revise);
                bw = new BufferedWriter(fw);

                for(int i = 0;i < a.size();i++){
                    if(i != imageIndex) {
                        bw.write(a.get(i) + "\r\n");
                    }
                    else{
                        Gson gson = new GsonBuilder().create();
                        String content = gson.toJson(label);
                        bw.write(content + "\r\n");
                    }
                }

                bw.close();
                fw.close();

                result = true;
            }catch(IOException e){
                e.printStackTrace();
            }

        }

        return result;
    }

    /**
     * 判断是否完成标注
     */
    public boolean isAccomplished(String userId,String taskId){
        boolean result = true;

//        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";
        String filePath = UserDirPath;

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

        if(userIsFind){

            filePath = filePath + "/AcceptedTask/IncompleteTask";

            File task = new File(filePath);
            File[] Task = task.listFiles();

            for(int i = 0;i < Task.length;i++){
                if(Task[i].getName().equals(taskId+".txt")){
                    filePath = filePath + "/" + taskId + ".txt";
                    taskIsFind = true;
                }
            }

        }

        if(taskIsFind){
            FileReader fr = null;
            BufferedReader br = null;

            try{
                File tasK = new File(filePath);

                fr = new FileReader(tasK);
                br = new BufferedReader(fr);
                String s;
                while((s = br.readLine()) != null){
                    if(s.equals("#")) {
                        result = false;
                        break;
                    }
                }

            }catch(IOException e){
                e.printStackTrace();
            }

        }

        return result;
    }

    /**
     * 用户接受任务
     * 创建文件夹
     * @param number 任务的图片数量
     */
    public boolean acceptTask(String userId,String taskId,int number){
        boolean result = false;
//        String filePath  = System.getProperty("user.dir").toString() + "/src/main/User";
        String filePath = UserDirPath;

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
            filePath = filePath + "/AcceptedTask/IncompleteTask";

            filePath = filePath + "/" + taskId + ".txt";

            File accept = new File(filePath);

            FileHelper fh = new FileHelper();

            fh.createFile(filePath);

            FileWriter fw = null;
            BufferedWriter bw = null;

            try{
                fw = new FileWriter(accept);
                bw = new BufferedWriter(fw);

                for(int i = 0;i < number;i++){
                    bw.write("#\r\n");
                }

                bw.close();
                fw.close();

                result = true;
            }catch(IOException e){
                e.printStackTrace();
            }

        }

        return result;
    }
}
