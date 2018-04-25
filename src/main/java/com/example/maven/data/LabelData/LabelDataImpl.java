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
    /**
     * 获取用户已经标注的信息
     * @return 标签List 若未标注，图片对应位置返回null
     */
    public List<Label> getLabel(String userId, String taskId){
        List<Label> label = new ArrayList<Label>();

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

            File task = new File(filePath);
            File[] Task = task.listFiles();

            for(int i = 0;i < Task.length;i++){
                if(Task[i].getName().equals(taskId)){
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
                    if (s.equals("#"))
                        label.add(null);
                    else{
                        Gson gson = new GsonBuilder().create();

                        String[] name = taskId.split("_");

                        String type = name[1];

                        if(type.equals("ImageLabel")){
                            ImageLabel Label = gson.fromJson(s,ImageLabel.class);
                            label.add(Label);
                        }
                        else if(type.equals("AreaLabel")){
                            AreaLabel Label = gson.fromJson(s,AreaLabel.class);
                            label.add(Label);
                        }
                        else{
                            FrameLabel Label = gson.fromJson(s,FrameLabel.class);
                            label.add(Label);
                        }
                    }
                }

            }catch(IOException e){
                e.printStackTrace();
            }

        }

        return null;
    }

    /**
     * 保存用户标注信息
     * @param imageIndex 对应的图片的位置
     */
    public boolean saveLabel(String userId,String taskId,int imageIndex,Label label){
        boolean result = false;

        String filePath = System.getProperty("user.dir").toString() + "/src/main/User";

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
                if(task[i].getName().equals(taskId)){
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
                    bw.write(a.get(i) + "\r\n");
                }

                bw.close();
                fw.close();

            }catch(IOException e){
                e.printStackTrace();
            }

        }

        return result;
    }

    /**
     * 判断是否完成标注
     */
    public boolean isCompletedTask(String userId,String taskId){
        boolean result = true;

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

        if(userIsFind){

            filePath = filePath + "/AcceptedTask/IncompleteTask";

            File task = new File(filePath);
            File[] Task = task.listFiles();

            for(int i = 0;i < Task.length;i++){
                if(Task[i].getName().equals(taskId)){
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

        if(result){
            File delete = new File(filePath);

            String AA = System.getProperty("user.dir").toString() + "/src/main/User/" + userId +
                    "/AcceptTask/AccomplishedTask/" + taskId + ".txt";

            File move = new File(AA);

            FileReader fr =  null;
            BufferedReader br = null;

            FileWriter fw = null;
            BufferedWriter bw = null;

            try{
                fr = new FileReader(delete);
                br = new BufferedReader(fr);

                fw = new FileWriter(AA);
                bw = new BufferedWriter(fw);

                String s;
                while((s = br.readLine()) != null){
                    bw.write(s + "\r\n");
                }

                bw.close();
                fw.close();

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
        String filePath  = System.getProperty("user.dir").toString() + "/src/main/User";

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

            }catch(IOException e){
                e.printStackTrace();
            }

        }

        return false;
    }
}
