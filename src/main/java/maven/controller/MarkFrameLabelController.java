package maven.controller;


import maven.businessLogic.markLabelBL.MarkFrameLableBL.MarkFrameLabelBLService;
import maven.businessLogic.markLabelBL.MarkFrameLableBL.MarkFrameLabelBLImpl;
import maven.businessLogic.markLabelBL.SetTaskAccomplishedBL.SetTaskAccomplishedBLService;
import maven.businessLogic.markLabelBL.SetTaskAccomplishedBL.SetTaskAccomplishedBLImpl;
import maven.model.JsonConverter;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.FrameLabelSetVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkFrameLabelController {

    private MarkFrameLabelBLService markFrameLabelBL;
    private SetTaskAccomplishedBLService setTaskAccomplishedBL;

    public MarkFrameLabelController(){
        markFrameLabelBL = new MarkFrameLabelBLImpl();
        setTaskAccomplishedBL = new SetTaskAccomplishedBLImpl();
    }

    @RequestMapping(value = "/markFrameLabel/getFrameLabel", method = RequestMethod.GET)
    public FrameLabelSetVO getFrameLabelSetVO(String taskId, String userId) {
        return markFrameLabelBL.getFrameLabelSetVO(new TaskId(taskId),new UserId(userId));
    }
    @RequestMapping(value = "/markFrameLabel/saveFrameLabel", method = RequestMethod.GET)
    public boolean saveFrameLabelSetVO(String taskId, String userId, String frameLabelVOSetJSON) {
        System.out.println(frameLabelVOSetJSON);
        return markFrameLabelBL.saveFrameLabelSet(new TaskId(taskId), new UserId(userId), (FrameLabelSetVO)JsonConverter.jsonToObject(frameLabelVOSetJSON, FrameLabelSetVO.class));
    }


    @RequestMapping(value = "/markFrameLabel/setTaskAccomplished", method = RequestMethod.GET)
    public boolean setTaskAccomplished(String taskId, String userId) {
        return setTaskAccomplishedBL.setTaskAccomplished(new TaskId(taskId), new UserId(userId));
    }
}
