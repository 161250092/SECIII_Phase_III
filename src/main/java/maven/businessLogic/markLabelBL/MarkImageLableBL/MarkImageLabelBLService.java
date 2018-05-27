package maven.businessLogic.markLabelBL.MarkImageLableBL;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.ImageLabelSetVO;

public interface MarkImageLabelBLService {
    /**
     * 获取标注集合
     * @param taskId 任务Id
     * @param userId 用户Id
     * @return 标注集LabelVOSet
     */
    ImageLabelSetVO getImageLabelSetVO(TaskId taskId, UserId userId);

    /**
     * 保存标注集合
     * @param taskId 任务Id
     * @param userId 用户Id
     * @param imageLabelSetVO 标注集合
     * @param isWorker 判断标注者是否为工人
     * @return 是否保存成功
     */
    boolean saveImageLabelSet(TaskId taskId, UserId userId, ImageLabelSetVO imageLabelSetVO, boolean isWorker);
}
