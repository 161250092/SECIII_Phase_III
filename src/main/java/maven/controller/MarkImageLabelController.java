package maven.controller;

import maven.businessLogic.markLabelBL.MarkImageLableBL.MarkImageLabelBLService;
import maven.businessLogic.markLabelBL.MarkImageLableBL.MarkImageLabelBLImpl;
import maven.businessLogic.markLabelBL.SetTaskAccomplishedBL.SetTaskAccomplishedBLService;
import maven.businessLogic.markLabelBL.SetTaskAccomplishedBL.SetTaskAccomplishedBLImpl;
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
        markImageLabelBL = new MarkImageLabelBLImpl();
        setTaskAccomplishedBL = new SetTaskAccomplishedBLImpl();
    }

    @RequestMapping(value = "/markImageLabel/getImageLabel", method = RequestMethod.GET)
    public ImageLabelSetVO getImageLabelSetVO(String taskId, String userId) {
        return markImageLabelBL.getImageLabelSetVO(new TaskId(taskId),new UserId(userId));
    }
    @RequestMapping(value = "/markImageLabel/saveImageLabel", method = RequestMethod.GET)
    public boolean saveImageLabelSetVO(String taskId, String userId, String imageLabelVOSetJSON) {
        System.out.println(imageLabelVOSetJSON);
        return markImageLabelBL.saveImageLabelSet(new TaskId(taskId), new UserId(userId), (ImageLabelSetVO)JsonConverter.jsonToObject(imageLabelVOSetJSON, ImageLabelSetVO.class));
    }


    @RequestMapping(value = "/markImageLabel/setTaskAccomplished", method = RequestMethod.GET)
    public boolean setTaskAccomplished(String taskId, String userId) {
        return setTaskAccomplishedBL.setTaskAccomplished(new TaskId(taskId), new UserId(userId));
    }
}
