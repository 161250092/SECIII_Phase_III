package com.example.maven.controller.markLabel;

import com.example.maven.businessLogic.markLabel.markFrameLabelBL.MarkFrameLabelBLImpl;
import com.example.maven.businessLogic.markLabel.markFrameLabelBL.MarkFrameLabelBLService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkFrameLabelController implements MarkFrameLabelBLService {

    private MarkFrameLabelBLService markFrameLabelBL;

    public MarkFrameLabelController(){
        markFrameLabelBL = new MarkFrameLabelBLImpl();
    }

    @RequestMapping(value = "/markFrameLabel/saveFrameLabel", method = RequestMethod.GET)
    public String saveFrameLabel(String frameLabelJson){
        return markFrameLabelBL.saveFrameLabel(frameLabelJson);
    }

    @RequestMapping(value = "/markFrameLabel/getFrameLabel", method = RequestMethod.GET)
    public String getFrameLabel(String userId, String imageId){
       return markFrameLabelBL.getFrameLabel(userId, imageId);
    }

    @Override
    public int getTaskImageNumber(String taskId) {
        return 0;
    }

    @Override
    public String getImageInfo(int imageIndex, String taskId, String userId) {
        return null;
    }

}
