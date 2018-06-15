package maven.businessLogic.massTaskBL.massTaskRequestorBL;

import maven.model.massTask.MassTaskDetail;
import maven.model.primitiveType.UserId;
import maven.model.vo.PublishedMassTaskVO;

import java.util.List;

public interface MassTaskRequestorBLService {

    /**
     * 发布者上传大任务细节
     * @param massTaskDetail 大任务细节
     * @return 是否上传成功
     */
    Exception uploadMassTaskDetail(MassTaskDetail massTaskDetail);

    List<PublishedMassTaskVO> getPublishedMassTaskVOList(UserId userId);

}
