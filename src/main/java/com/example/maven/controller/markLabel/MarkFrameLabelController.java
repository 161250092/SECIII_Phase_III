package com.example.maven.controller.markLabel;

import com.example.maven.businessLogic.markFrame.MarkFrameLabelBL;
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

    private MarkFrameLabelBL markFrameLabelBL;

    public MarkFrameLabelController(){
        markFrameLabelBL = new MarkFrameLabelBL();
    }

    @RequestMapping(value = "/markFrameLabel/saveFrameLabel", method = RequestMethod.GET)
    public String saveFrameLabel(@RequestParam(defaultValue="null") String frameLabelJson){
        return markFrameLabelBL.saveFrameLabel(frameLabelJson);
    }

    @RequestMapping(value = "/markFrameLabel/getFrameLabel", method = RequestMethod.GET)
    public String getFrameLabel(String userId, String imageId){
       return markFrameLabelBL.getFrameLabel(userId, imageId);
    }

}
