package maven.controller;

import maven.businessLogic.markLabelBL.MarkAreaLableBL.MarkAreaLabelBLService;
import maven.businessLogic.markLabelBL.MarkAreaLableBL.MarkAreaLabelBLStub;
import maven.businessLogic.markLabelBL.SetTaskAccomplishedBL.SetTaskAccomplishedBLService;
import maven.businessLogic.markLabelBL.SetTaskAccomplishedBL.SetTaskAccomplishedBLStub;
import maven.model.JsonConverter;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.AreaLabelSetVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class MarkAreaLabelController {

    private MarkAreaLabelBLService markAreaLabelBL;
    private SetTaskAccomplishedBLService setTaskAccomplishedBL;

    public MarkAreaLabelController(){
        markAreaLabelBL = new MarkAreaLabelBLStub();
        setTaskAccomplishedBL = new SetTaskAccomplishedBLStub();
    }

    @RequestMapping(value = "/markLabel/getAreaLabel", method = RequestMethod.GET)
    public AreaLabelSetVO getAreaLabelSetVO(String taskId, String userId) {
        return markAreaLabelBL.getAreaLabelSetVO(new TaskId(taskId),new UserId(userId));
    }
    @RequestMapping(value = "/markLabel/saveAreaLabel", method = RequestMethod.GET)
    public boolean saveAreaLabelSetVO(String taskId, String userId, String areaLabelVOSetJSON, boolean isWorker) {
        System.out.println(areaLabelVOSetJSON);
        return markAreaLabelBL.saveAreaLabelSet(new TaskId(taskId), new UserId(userId), (AreaLabelSetVO)JsonConverter.jsonToObject(areaLabelVOSetJSON, AreaLabelSetVO.class), isWorker);
    }


    @RequestMapping(value = "/markLabel/setTaskAccomplished", method = RequestMethod.GET)
    public boolean setTaskAccomplished(String taskId, String userId, boolean isWorker) {
        return setTaskAccomplishedBL.setTaskAccomplished(new TaskId(taskId), new UserId(userId), isWorker);
    }
}
