package com.example.maven.service.DataImpl;

import com.example.maven.model.AreaLabel;
import com.example.maven.service.DataService.AreaLabelDataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;

public class AreaLabelDataImpl implements AreaLabelDataService{
    FileHelper fh = new FileHelper();
    String path = System.getProperty("user.dir").toString() + "/src/main/Label/AreaLabel.txt";

    @Override
    public boolean saveAreaLabel(AreaLabel label) {

        ArrayList<AreaLabel> list = getAllAreaLabel();

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
