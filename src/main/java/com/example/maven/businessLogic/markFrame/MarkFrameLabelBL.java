package com.example.maven.businessLogic.markFrame;

import com.example.maven.model.AreaLabel;
import com.example.maven.model.frameLabel.FrameLabel;
import com.example.maven.service.DataImpl.FrameLabelDataImpl;
import com.example.maven.service.DataService.FrameLabelDataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public class MarkFrameLabelBL {

    private FrameLabelDataService frameLabelData;

    public MarkFrameLabelBL(){
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
