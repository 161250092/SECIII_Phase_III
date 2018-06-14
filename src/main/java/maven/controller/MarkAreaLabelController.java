package maven.controller;

import maven.businessLogic.markLabelBL.MarkAreaLableBL.MarkAreaLabelBLService;
import maven.businessLogic.markLabelBL.MarkAreaLableBL.MarkAreaLabelBLImpl;
import maven.businessLogic.markLabelBL.SetTaskAccomplishedBL.SetTaskAccomplishedBLService;
import maven.businessLogic.markLabelBL.SetTaskAccomplishedBL.SetTaskAccomplishedBLImpl;
import maven.model.JsonConverter;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.AreaLabelSetVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkAreaLabelController {

    private MarkAreaLabelBLService markAreaLabelBL;
    private SetTaskAccomplishedBLService setTaskAccomplishedBL;

    public MarkAreaLabelController(){
        markAreaLabelBL = new MarkAreaLabelBLImpl();
        setTaskAccomplishedBL = new SetTaskAccomplishedBLImpl();
    }

    @RequestMapping(value = "/markAreaLabel/getAreaLabel", method = RequestMethod.GET)
    public AreaLabelSetVO getAreaLabelSetVO(String taskId, String userId) {
        return markAreaLabelBL.getAreaLabelSetVO(new TaskId(taskId),new UserId(userId));
    }
    @RequestMapping(value = "/markAreaLabel/saveAreaLabel", method = RequestMethod.GET)
    public boolean saveAreaLabelSetVO(String taskId, String userId, String areaLabelVOSetJSON) {
        System.out.println(areaLabelVOSetJSON);
        return markAreaLabelBL.saveAreaLabelSet(new TaskId(taskId), new UserId(userId), (AreaLabelSetVO)JsonConverter.jsonToObject(areaLabelVOSetJSON, AreaLabelSetVO.class));
    }


    @RequestMapping(value = "/markAreaLabel/setTaskAccomplished", method = RequestMethod.GET)
    public boolean setTaskAccomplished(String taskId, String userId) {
        return setTaskAccomplishedBL.setTaskAccomplished(new TaskId(taskId), new UserId(userId));
    }
}
