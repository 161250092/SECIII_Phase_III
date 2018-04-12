package com.example.maven.controller.markLabel;

import com.example.maven.businessLogic.markLabel.markLabelBL.MarkLabelBLService;
import com.example.maven.businessLogic.markLabel.markLabelBL.MarkLabelBLStub;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkLabelController implements MarkLabelBLService {

    private MarkLabelBLService markLabelBL;

    public MarkLabelController() {
        //stub
        markLabelBL = new MarkLabelBLStub();
    }

    @Override
    @RequestMapping(value = "/markLabel/getTaskImageNumber", method = RequestMethod.GET)
    public int getTaskImageNumber(String taskId) {
        return markLabelBL.getTaskImageNumber(taskId);
    }

    @Override
    @RequestMapping(value = "/markLabel/getLabel", method = RequestMethod.GET)
    public String getLabel(String taskId, String userId, String labelType, int imageIndex) {
        return markLabelBL.getLabel(taskId, userId, labelType, imageIndex);
    }

    @Override
    @RequestMapping(value = "/markLabel/saveLabel", method = RequestMethod.GET)
    public boolean saveLabel(String taskId, String userId, String labelType, String labelVOJson) {
        return markLabelBL.saveLabel(taskId, userId, labelType, labelVOJson);
    }

    @Override
    @RequestMapping(value = "/markLabel/setTaskAccomplished", method = RequestMethod.GET)
    public boolean setTaskAccomplished(String taskId, String userId) {
        return markLabelBL.setTaskAccomplished(taskId, userId);
    }
}
