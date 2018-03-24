package com.example.maven.service.DataImpl;

import com.example.maven.model.AreaLabel;
import com.example.maven.service.DataService.AreaLabelDataService;
import src.main.java.com.example.maven.service.DataImpl.FileHelper;

import java.io.File;
import java.util.ArrayList;

public class AreaLabelDataImpl implements AreaLabelDataService{
    FileHelper fh = new FileHelper();
    String path = "线标注"+ File.separator+"线标.txt";

    @Override
    public boolean saveAreaLabel(AreaLabel label) {

        Gson gson = new GsonBuilder().create();
        String content = gson.toJson(label);

        if(fh.readTxtFile(path )==null)
            fh.writeTxtFile(content,path,true,true);
        else
            fh.writeTxtFile(content,path,true,false);

        return true;

    }

    @Override
    public ArrayList<AreaLabel> getAllAreaLabel() {
        String[] all = fh.readTxtFile(path).split("\n");

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
        AreaLabel label= new AreaLabel();

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
