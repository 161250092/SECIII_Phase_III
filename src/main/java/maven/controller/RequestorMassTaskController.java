package maven.controller;

import maven.businessLogic.requestorBL.RequestorMassTaskBLImpl;
import maven.businessLogic.requestorBL.RequestorMassTaskBLService;
import maven.model.JsonConverter;
import maven.model.massTask.MassTaskDetail;
import maven.model.primitiveType.UserId;
import maven.model.vo.MassTaskDetailVO;
import maven.model.vo.PublishedMassTaskVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestorMassTaskController {
    private RequestorMassTaskBLService requestorMassTaskBL;

    public RequestorMassTaskController() {
        requestorMassTaskBL = new RequestorMassTaskBLImpl();
    }

    /**
     * 发布者上传大任务细节
     * @param massTaskDetailVOJson 大任务细节VOJson
     * @return 是否上传成功
     */
    @RequestMapping(value = "/requestorMassTask/uploadMassTaskDetail", method = RequestMethod.GET)
    Exception uploadMassTaskDetail(String massTaskDetailVOJson){
        System.out.println(massTaskDetailVOJson);
        MassTaskDetailVO massTaskDetailVO = (MassTaskDetailVO) JsonConverter.jsonToObject(massTaskDetailVOJson, MassTaskDetailVO.class);
        return requestorMassTaskBL.uploadMassTaskDetail(new MassTaskDetail(massTaskDetailVO));
    }

    /**
     * 查看发布者自己发的大任务
     * @param requestorId 发布者ID
     * @return 该发布者自己发的大任务
     */
    @RequestMapping(value = "/requestorMassTask/getPublishedMassTaskVOList", method = RequestMethod.GET)
    List<PublishedMassTaskVO> getPublishedMassTaskVOList(String requestorId){
        return requestorMassTaskBL.getPublishedMassTaskVOList(new UserId(requestorId));
    }
}
