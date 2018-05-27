package businessLogic.markLabelBL;

import model.primitiveType.TaskId;
import model.primitiveType.UserId;
import model.vo.LabelSetVO;

/**
 * 为所有标注提供统一的接口
 */
public interface MarkLabelBLService {

    /**
     * 获取标注集合
     * @param taskId 任务Id
     * @param userId 用户Id
     * @return 标注集LabelVOSet
     */
    LabelSetVO getLabelSetVO(TaskId taskId, UserId userId);


    /**
     * 保存标注集合
     * @param taskId 任务Id
     * @param userId 用户Id
     * @param labelSetVO 标注集合
     * @param isWorker 判断标注者是否为工人
     * @return 是否保存成功
     */
    boolean saveLabelSet(TaskId taskId, UserId userId, LabelSetVO labelSetVO, boolean isWorker);


    /**
     * 将该任务设置为已完成
     * @param taskId 任务Id
     * @param userId 用户Id
     * @param isWorker 判断标注者是否为工人
     * @return 是否设置成功
     */
    boolean setTaskAccomplished(TaskId taskId, UserId userId, boolean isWorker);
}
