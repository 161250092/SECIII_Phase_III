package com.example.maven.businessLogic.markLabel.markFrameLabelBL;

import com.example.maven.model.frameLabel.FrameLabel;
import com.example.maven.data.frameLabelData.FrameLabelDataImpl;
import com.example.maven.data.frameLabelData.FrameLabelDataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MarkFrameLabelBLImpl implements MarkFrameLabelBLService {

    private FrameLabelDataService frameLabelData;

    public MarkFrameLabelBLImpl(){
        frameLabelData = new FrameLabelDataImpl();
    }

    public String saveFrameLabel(String frameLabelJson){
        Gson gson = new GsonBuilder().create();
        FrameLabel frameLabel = gson.fromJson(frameLabelJson, FrameLabel.class);

        if(frameLabelData.saveFrameLabel(frameLabel)){
            return "Success";
        }else{
            return "FailInJava";
        }
    }

    public String getFrameLabel(String userId, String imageId){
        FrameLabel frameLabel = frameLabelData.getFrameLabelByImageId(imageId);

        Gson gson = new GsonBuilder().create();
        String objectToJson = gson.toJson(frameLabel);

        return objectToJson;
    }

}
