package com.example.maven.service.DataImpl;

import com.example.maven.model.frameLabel.FrameLabel;
import com.example.maven.service.DataService.FrameLabelDataService;
import src.main.java.com.example.maven.service.DataImpl.FileHelper;

import java.io.File;
import java.util.ArrayList;

public class FrameLabelDataImpl implements FrameLabelDataService{

    /**
     * filehelper是关于文件存储的类
     * path是存储路径
     */
    FileHelper fh = new FileHelper();
    String path = "框标注"+ File.separator+"框标.txt";

    @Override
    public boolean saveFrameLabel(FrameLabel frameLabel) {
        Gson gson = new GsonBuilder().create();
        String content = gson.toJson(frameLabel);


        if(fh.readTxtFile(path )==null)
            fh.writeTxtFile(content,path,true,true);
        else
            fh.writeTxtFile(content,path,true,false);

        return true;


        return false;
    }

    @Override
    public FrameLabel getFrameLabelByUserId(String userId) {
        ArrayList<FrameLabel>  list = getAllFrameLabel();
        FrameLabel label= new FrameLabel();

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
        FrameLabel label= new FrameLabel();

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

        ArrayList<FrameLabel> list = new ArrayList<FrameLabel>();

        Gson gson = new GsonBuilder().create();

        for(int i =0;i<all.length;i++){
            FrameLabel frameLabel = gson.fromJson(all[i], FrameLabel.class);
            list.add(frameLabel);
        }

        return list;
    }

}
