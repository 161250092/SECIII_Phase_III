package maven.data.MarkLabelData.FrameLabelData;

import maven.model.label.frameLabel.FrameLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

import java.util.List;

public interface FrameLabelDataService {
    /**
     * 覆盖性保存标注信息
     * @param userId 工人Id
     * @param taskId 任务Id
     * @param labelList 标注列表
     * @return 是否保存成功
     */
    boolean saveLabelList(UserId userId, TaskId taskId, List<FrameLabel> labelList);

    /**
     * 删除标注信息
     * @param userId 工人Id
     * @param taskId 任务Id
     * @return 是否删除成功
     */
    boolean deleteLabel(UserId userId, TaskId taskId);

    /**
     * 获取标注信息
     * @param userId 工人Id
     * @param taskId 任务Id
     * @return 标注列表
     */
    List<FrameLabel> getLabelList(UserId userId,TaskId taskId);
}
