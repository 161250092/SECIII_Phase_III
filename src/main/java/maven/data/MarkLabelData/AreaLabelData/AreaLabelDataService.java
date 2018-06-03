package maven.data.MarkLabelData.AreaLabelData;

import maven.model.label.areaLabel.AreaLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskState;

import java.util.List;

public interface AreaLabelDataService {
    /**
     * 覆盖性保存标注信息
     * @param imageLabel
     * @return
     */
    boolean saveLabelList(UserId userId, TaskId taskId, List<AreaLabel> imageLabel);

    /**
     * 删除标注信息
     * @param userId
     * @param taskId
     * @return
     */
    boolean deleteLableList(UserId userId, TaskId taskId);

    /**
     * 获取标注信息
     * @param userId
     * @param taskId
     * @return
     */
    List<AreaLabel> getLabelList(UserId userId,TaskId taskId);
}
