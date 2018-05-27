package maven.controller;

import maven.businessLogic.markLabelBL.MarkImageLabelBLStub;
import maven.businessLogic.markLabelBL.MarkLabelBLImpl;
import maven.businessLogic.markLabelBL.MarkLabelBLService;
import maven.model.JsonConverter;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.LabelSetVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkLabelController {

    private MarkLabelBLService markLabelBL;

    public MarkLabelController() {
        //stub
        markLabelBL = new MarkImageLabelBLStub();
    }

    @RequestMapping(value = "/markLabel/getLabel", method = RequestMethod.GET)
    public String getLabelSetVO(String taskId, String userId) {
        return JsonConverter.objectToJson(markLabelBL.getLabelSetVO(new TaskId(taskId),new UserId(userId)));
    }

    @RequestMapping(value = "/markLabel/saveLabel", method = RequestMethod.GET)
    public boolean saveLabelSet(String taskId, String userId, String labelVOSetJSON, boolean isWorker) {
        return markLabelBL.saveLabelSet(new TaskId(taskId), new UserId(userId), (LabelSetVO)JsonConverter.jsonToObject(labelVOSetJSON, LabelSetVO.class), isWorker);
    }

    @RequestMapping(value = "/markLabel/setTaskAccomplished", method = RequestMethod.GET)
    public boolean setTaskAccomplished(String taskId, String userId, boolean isWorker) {
        return markLabelBL.setTaskAccomplished(new TaskId(taskId), new UserId(userId), isWorker);
    }
}
