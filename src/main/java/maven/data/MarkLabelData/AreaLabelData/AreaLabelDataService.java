package maven.data.MarkLabelData.AreaLabelData;

import maven.model.label.areaLabel.AreaLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

import java.util.List;

public interface AreaLabelDataService {
    /**
     * 覆盖性保存标注信息
     * @param userId 工人Id
     * @param taskId 任务Id
     * @param lableList 标注列表
     * @return 是否保存成功
     */
    boolean saveLabelList(UserId userId, TaskId taskId, List<AreaLabel> lableList);

    /**
     * 删除标注信息
     * @param userId 工人Id
     * @param taskId 任务Id
     * @return 是否删除成功
     */
    boolean deleteLable(UserId userId, TaskId taskId);

    /**
     * 获取标注信息
     * @param userId 工人Id
     * @param taskId 任务Id
     * @return 标注列表
     */
    List<AreaLabel> getLabelList(UserId userId,TaskId taskId);
}
