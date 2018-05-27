package maven.businessLogic.markLabelBL;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.AreaLabelSetVO;
import maven.model.vo.FrameLabelSetVO;
import maven.model.vo.ImageLabelSetVO;
import maven.model.vo.LabelSetVO;

/**
 * 为所有标注提供统一的接口
 */
public interface SetTaskAccomplishedBLService {
    /**
     * 将该任务设置为已完成
     * @param taskId 任务Id
     * @param userId 用户Id
     * @param isWorker 判断标注者是否为工人
     * @return 是否设置成功
     */
    boolean setTaskAccomplished(TaskId taskId, UserId userId, boolean isWorker);
}
