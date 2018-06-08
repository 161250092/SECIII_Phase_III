package maven.businessLogic.markLabelBL.SetTaskAccomplishedBL;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

/**
 * 为所有标注提供统一的接口
 */
public interface SetTaskAccomplishedBLService {
    /**
     * 将该任务设置为已完成
     * @param taskId 任务Id
     * @param userId 用户Id
     * @return 是否设置成功
     */
    boolean setTaskAccomplished(TaskId taskId, UserId userId);
}
