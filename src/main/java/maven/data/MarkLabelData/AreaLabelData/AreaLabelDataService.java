package maven.data.MarkLabelData.AreaLabelData;

import maven.model.label.areaLabel.AreaLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskState;

import java.util.List;

public interface AreaLabelDataService {
    /**
     * 接受任务或更改标注信息
     * @param imageLabel
     * @return
     */
    public boolean acceptTask(UserId userId, TaskId taskId, List<AreaLabel> imageLabel);


    /**
     * 更改任务信息
     * @param userId
     * @param taskId
     * @param acceptedTaskState
     * @return
     */
    public boolean reviseTaskState(UserId userId, TaskId taskId, AcceptedTaskState acceptedTaskState);
}
