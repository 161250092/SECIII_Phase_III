package maven.data.MarkLabelData.ImageLabelData;

import maven.model.label.ImageLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskState;

import java.util.List;

public interface ImageLabelDataService {
    /**
     * 覆盖性保存标注信息
     * @param imageLabel
     * @return
     */
    public boolean saveLableList(UserId userId, TaskId taskId, List<ImageLabel> imageLabel);

    /**
     * 更改任务信息
     * @param userId
     * @param taskId
     * @param acceptedTaskState
     * @return
     */
    public boolean reviseTaskState(UserId userId, TaskId taskId, AcceptedTaskState acceptedTaskState);
}
