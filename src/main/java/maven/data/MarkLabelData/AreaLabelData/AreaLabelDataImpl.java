package maven.data.MarkLabelData.AreaLabelData;

import maven.model.label.areaLabel.AreaLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskState;

import java.util.List;

public class AreaLabelDataImpl implements AreaLabelDataService {
    @Override
    public boolean saveLableList(UserId userId, TaskId taskId, List<AreaLabel> imageLabel) {
        return false;
    }

    @Override
    public boolean reviseTaskState(UserId userId, TaskId taskId, AcceptedTaskState acceptedTaskState) {
        return false;
    }
}
