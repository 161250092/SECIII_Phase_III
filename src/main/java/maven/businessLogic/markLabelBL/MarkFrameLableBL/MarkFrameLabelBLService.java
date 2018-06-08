package maven.businessLogic.markLabelBL.MarkFrameLableBL;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.FrameLabelSetVO;

public interface MarkFrameLabelBLService{
    /**
     * 获取标注集合
     * @param taskId 任务Id
     * @param userId 用户Id
     * @return 标注集LabelVOSet
     */
    FrameLabelSetVO getFrameLabelSetVO(TaskId taskId, UserId userId);

    /**
     * 保存标注集合
     * @param taskId 任务Id
     * @param userId 用户Id
     * @param frameLabelSetVO 标注集合
     * @return 是否保存成功
     */
    boolean saveFrameLabelSet(TaskId taskId, UserId userId, FrameLabelSetVO frameLabelSetVO);
}
