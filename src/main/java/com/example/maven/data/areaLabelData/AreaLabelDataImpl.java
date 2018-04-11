package com.example.maven.data.areaLabelData;

import com.example.maven.data.fileHelper.FileHelper;
import com.example.maven.model.po.AreaLabel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class AreaLabelDataImpl implements AreaLabelDataService{
    FileHelper fh = new FileHelper();
    String path = System.getProperty("user.dir").toString() + "/src/main/Label/AreaLabel.txt";

    @Override
    public boolean saveAreaLabel(AreaLabel label) {

        ArrayList<AreaLabel> list = getAllAreaLabel();

        /**
         * 原来未声明content
         * @雷诚
         */
        Gson gson = new GsonBuilder().create();
        String content = gson.toJson(label);

        if(list.size()==0) {
            fh.writeTxtFile(content, path, true);
            return true;
        }


        for(int i=0;i<list.size();i++){
            if(list.get(i).getImageId().equals(label.getImageId())){
                list.set(i,label);
            }
        }


        for(int i=0;i<list.size();i++) {
            content = gson.toJson(list.get(i));
            fh.writeTxtFile(content, path, true);
        }

        return true;
    }

    @Override
    public ArrayList<AreaLabel> getAllAreaLabel() {
        String[] all = fh.readTxtFile(path).split("\n");

        if(all.length==1&&all[0].equals(""))
            return new ArrayList<AreaLabel>();

        ArrayList<AreaLabel> list = new ArrayList<AreaLabel>();

        Gson gson = new GsonBuilder().create();

        for(int i =0;i<all.length;i++){
            AreaLabel areaLabel = gson.fromJson(all[i], AreaLabel.class);
            list.add(areaLabel);
        }

        return list;
    }

    @Override
    public AreaLabel getAreaLabelByImageId(String ImageId) {
        ArrayList<AreaLabel>  list = getAllAreaLabel();
        AreaLabel label= null;

        for(int i=0;i<list.size();i++){
            if(list.get(i).getImageId().equals(ImageId))
            {
                label = list.get(i);
                break;
            }
        }

        return label;
    }


}
