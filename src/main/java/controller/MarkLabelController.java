package controller;

import businessLogic.markLabel.markLabelBL.*;
import model.JsonConverter;
import model.primitiveType.TaskId;
import model.primitiveType.UserId;
import model.vo.LabelSetVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkLabelController {

    private MarkLabelBLService markLabelBL;

    public MarkLabelController() {
        //stub
        markLabelBL = new MarkLabelBLImpl();
    }

    @RequestMapping(value = "/markLabel/getLabel", method = RequestMethod.GET)
    public String getLabelVOSet(String taskId, String userId) {
        return JsonConverter.toJson(markLabelBL.getLabelSetVO(new TaskId(taskId),new UserId(userId)));
    }

    @RequestMapping(value = "/markLabel/saveLabel", method = RequestMethod.GET)
    public boolean saveLabelSet(String taskId, String userId, String labelVOSetJSON, boolean isWorker) {
        return markLabelBL.saveLabelSet(new TaskId(taskId), new UserId(userId), (LabelSetVO)JsonConverter.toObject(labelVOSetJSON, LabelSetVO.class), isWorker);
    }

    @RequestMapping(value = "/markLabel/setTaskAccomplished", method = RequestMethod.GET)
    public boolean setTaskAccomplished(String taskId, String userId, boolean isWorker) {
        return markLabelBL.setTaskAccomplished(new TaskId(taskId), new UserId(userId), isWorker);
    }
}
