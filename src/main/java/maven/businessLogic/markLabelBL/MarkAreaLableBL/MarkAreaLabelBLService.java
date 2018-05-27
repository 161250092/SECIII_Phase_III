package maven.businessLogic.markLabelBL.MarkAreaLableBL;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.AreaLabelSetVO;

public interface MarkAreaLabelBLService {
    /**
     * 获取标注集合
     * @param taskId 任务Id
     * @param userId 用户Id
     * @return 标注集LabelVOSet
     */
    AreaLabelSetVO getAreaLabelSetVO(TaskId taskId, UserId userId);

    /**
     * 保存标注集合
     * @param taskId 任务Id
     * @param userId 用户Id
     * @param areaLabelSetVO 标注集合
     * @param isWorker 判断标注者是否为工人
     * @return 是否保存成功
     */
    boolean saveAreaLabelSet(TaskId taskId, UserId userId, AreaLabelSetVO areaLabelSetVO, boolean isWorker);
}
