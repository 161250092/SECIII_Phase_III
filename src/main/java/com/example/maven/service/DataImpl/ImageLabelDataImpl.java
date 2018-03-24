package com.example.maven.service.DataImpl;

import com.example.maven.model.ImageLabel;
import com.example.maven.service.DataService.ImageLabelDataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;

public class ImageLabelDataImpl implements ImageLabelDataService{

    FileHelper fh = new FileHelper();
    String path = System.getProperty("user.dir").toString() + "/src/main/Label/ImageLabel.txt";


    @Override
    public boolean saveImageLabel(ImageLabel label) {


        Gson gson = new GsonBuilder().create();
        String content = gson.toJson(label);


        ArrayList<ImageLabel> list = getAllImageLabel();

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
    public ArrayList<ImageLabel> getAllImageLabel() {
        String[] all = fh.readTxtFile(path).split("\n");

        if(all.length==1&&all[0].equals(""))
            return  new ArrayList<ImageLabel>();

        ArrayList<ImageLabel> list = new ArrayList<ImageLabel>();
        Gson gson = new GsonBuilder().create();

        for(int i =0;i<all.length;i++){
            ImageLabel Label = gson.fromJson(all[i], ImageLabel.class);
            list.add(Label);
        }

        return list;
    }

    @Override
    public ImageLabel getAreaLabelByImageId(String ImageId) {
        ImageLabel image = new ImageLabel();

        ArrayList<ImageLabel> list = getAllImageLabel();

        for(int i=0;i<list.size();i++){
            if(list.get(i).getImageId().equals(ImageId))
            {
                image = list.get(i);
                break;
            }
        }
        return image;
    }

    //ImageLabel 变String；
    public static String labelToString(ImageLabel label){
        String result="";
        String split = "@#";
        result = label.getImageId()+split+label.getLabel()+split+label.getUserId();
        return result;
    }

    //String 变ImageLabel
    public static ImageLabel stringToLabel(String str){
        String[]  image= str.split("@#");
        ImageLabel  label = new ImageLabel();
        label.setImageId(image[0]);
        label.setLabel(image[1]);
        label.setUserId(image[2]);
        return label;
    }
}
