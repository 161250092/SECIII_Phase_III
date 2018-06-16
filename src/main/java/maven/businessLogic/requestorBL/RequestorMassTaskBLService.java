package maven.businessLogic.requestorBL;

import maven.model.massTask.MassTaskDetail;
import maven.model.primitiveType.UserId;
import maven.model.vo.PublishedMassTaskVO;

import java.util.List;

public interface RequestorMassTaskBLService {

    /**
     * 发布者上传大任务细节
     * @param massTaskDetail 大任务细节
     * @return 是否上传成功
     */
    Exception uploadMassTaskDetail(MassTaskDetail massTaskDetail);

    /**
     * 查看发布者自己发的大任务
     * @param requestorId 发布者ID
     * @return 该发布者自己发的大任务
     */
    List<PublishedMassTaskVO> getPublishedMassTaskVOList(UserId requestorId);

}
