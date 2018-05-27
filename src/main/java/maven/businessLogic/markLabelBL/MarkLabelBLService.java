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
    boolean saveImageLabelSet(TaskId taskId, UserId userId, ImageLabelSetVO labelSetVO, boolean isWorker);

    /**
     * 保存标注集合
     * @param taskId 任务Id
     * @param userId 用户Id
     * @param labelSetVO 标注集合
     * @param isWorker 判断标注者是否为工人
     * @return 是否保存成功
     */
    boolean saveFrameLabelSet(TaskId taskId, UserId userId, FrameLabelSetVO labelSetVO, boolean isWorker);

    /**
     * 保存标注集合
     * @param taskId 任务Id
     * @param userId 用户Id
     * @param labelSetVO 标注集合
     * @param isWorker 判断标注者是否为工人
     * @return 是否保存成功
     */
    boolean saveAreaLabelSet(TaskId taskId, UserId userId, AreaLabelSetVO labelSetVO, boolean isWorker);


    /**
     * 将该任务设置为已完成
     * @param taskId 任务Id
     * @param userId 用户Id
     * @param isWorker 判断标注者是否为工人
     * @return 是否设置成功
     */
    boolean setTaskAccomplished(TaskId taskId, UserId userId, boolean isWorker);
}
