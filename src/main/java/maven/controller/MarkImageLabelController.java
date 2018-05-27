package maven.controller;

import maven.businessLogic.markLabelBL.*;
import maven.model.JsonConverter;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkImageLabelController {

    private MarkImageLabelBLService markImageLabelBL;
    private SetTaskAccomplishedBLService setTaskAccomplishedBL;

    public MarkImageLabelController() {
        //stub
        markImageLabelBL = new MarkImageLabelBLStub();
        setTaskAccomplishedBL = new SetTaskAccomplishedBLStub();
    }

    @RequestMapping(value = "/markLabel/getImageLabel", method = RequestMethod.GET)
    public ImageLabelSetVO getImageLabelSetVO(String taskId, String userId) {
        return markImageLabelBL.getImageLabelSetVO(new TaskId(taskId),new UserId(userId));
    }
    @RequestMapping(value = "/markLabel/saveImageLabel", method = RequestMethod.GET)
    public boolean saveImageLabelSetVO(String taskId, String userId, String imageLabelVOSetJSON, boolean isWorker) {
        return markImageLabelBL.saveImageLabelSet(new TaskId(taskId), new UserId(userId), (ImageLabelSetVO)JsonConverter.jsonToObject(imageLabelVOSetJSON, ImageLabelSetVO.class), isWorker);
    }


    @RequestMapping(value = "/markLabel/setTaskAccomplished", method = RequestMethod.GET)
    public boolean setTaskAccomplished(String taskId, String userId, boolean isWorker) {
        return setTaskAccomplishedBL.setTaskAccomplished(new TaskId(taskId), new UserId(userId), isWorker);
    }
}
