package com.example.maven.data.frameLabelData;

import com.example.maven.data.fileHelper.FileHelper;
import com.example.maven.model.po.frameLabel.FrameLabel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class FrameLabelDataImpl implements FrameLabelDataService{

    /**
     * filehelper是关于文件存储的类
     * path是存储路径
     */
    FileHelper fh = new FileHelper();
    String path = System.getProperty("user.dir").toString() + "/src/main/Label/FrameLabel.txt";

    @Override
    public boolean saveFrameLabel(FrameLabel frameLabel) {
        Gson gson = new GsonBuilder().create();
        String content = gson.toJson(frameLabel);

        ArrayList<FrameLabel> list = getAllFrameLabel();

        if(list.size()==0) {
            fh.writeTxtFile(content, path, true);
            return true;
        }


        for(int i=0;i<list.size();i++){
            if(list.get(i).getImageId().equals(frameLabel.getImageId())){
                list.set(i,frameLabel);
            }
        }


        for(int i=0;i<list.size();i++) {
            content = gson.toJson(list.get(i));
            fh.writeTxtFile(content, path, true);
        }


        return true;

    }

    @Override
    public FrameLabel getFrameLabelByUserId(String userId) {
        ArrayList<FrameLabel>  list = getAllFrameLabel();
        FrameLabel label= null;

        for(int i=0;i<list.size();i++){
            if(list.get(i).getUserId().equals(userId))
            {
                label = list.get(i);
                break;
            }
        }

        return label;



    }

    @Override
    public FrameLabel getFrameLabelByImageId(String imageId) {

        ArrayList<FrameLabel>  list = getAllFrameLabel();

        FrameLabel label= null;

        for(int i=0;i<list.size();i++){
            if(list.get(i).getImageId().equals(imageId))
            {
                label = list.get(i);
                break;
            }
        }


        return label;
    }


    public ArrayList<FrameLabel> getAllFrameLabel(){

        String[] all = fh.readTxtFile(path).split("\n");

        if(all.length==1&&all[0].equals(""))
            return  new ArrayList<FrameLabel>();

        ArrayList<FrameLabel> list = new ArrayList<FrameLabel>();

        Gson gson = new GsonBuilder().create();

        for(int i =0;i<all.length;i++){
            FrameLabel frameLabel = gson.fromJson(all[i], FrameLabel.class);
            list.add(frameLabel);
        }

        return list;
    }

}
