package maven.data.MarkLabelData;

import maven.model.label.ImageLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskState;

public interface ImageLabelDataService {
    /**
     * 接受任务
     * @param imageLabel
     * @return
     */
    public boolean acceptTask(ImageLabel imageLabel);

    /**
     * 更改任务信息
     * @param userId
     * @param taskId
     * @param acceptedTaskState
     * @return
     */
    public boolean reviseTaskState(UserId userId, TaskId taskId, AcceptedTaskState acceptedTaskState);
}
