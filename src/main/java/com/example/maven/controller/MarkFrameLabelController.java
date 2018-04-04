package com.example.maven.controller;

import com.example.maven.model.frameLabel.FrameLabel;
import com.example.maven.model.frameLabel.FrameLabelTagItem;
import com.example.maven.service.DataImpl.FrameLabelDataImpl;
import com.example.maven.service.DataService.FrameLabelDataService;
import com.google.gson.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MarkFrameLabelController {

    private FrameLabelDataService frameLabelData;

    public MarkFrameLabelController(){
        frameLabelData = new FrameLabelDataImpl();
    }

    @RequestMapping(value = "/markFrameLabel/saveFrameLabel", method = RequestMethod.GET)
    public String saveFrameLabel(@RequestParam(defaultValue="null") String frameLabelJson){
        Gson gson = new GsonBuilder().create();
        FrameLabel frameLabel = gson.fromJson(frameLabelJson, FrameLabel.class);

        if(frameLabelData.saveFrameLabel(frameLabel)){
            return "Success";
        }else{
            return "FailInJava";
        }
    }

    @RequestMapping(value = "/markFrameLabel/getFrameLabel", method = RequestMethod.GET)
    public String getFrameLabel(String userId, String imageId){
        FrameLabel frameLabel = frameLabelData.getFrameLabelByImageId(imageId);

        Gson gson = new GsonBuilder().create();
        String objectToJson = gson.toJson(frameLabel);

        return objectToJson;
    }




}
