package maven.data.MarkLabelData.AreaLabelData;

import maven.model.label.areaLabel.AreaLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskState;

import java.util.List;

public class AreaLabelDataImpl implements AreaLabelDataService {
    @Override
    public boolean saveLabelList(UserId userId, TaskId taskId, List<AreaLabel> imageLabel) {
        return false;
    }

    @Override
    public boolean deleteLableList(UserId userId, TaskId taskId) {
        return false;
    }

    @Override
    public List<AreaLabel> getLabelList(UserId userId, TaskId taskId) {
        return null;
    }
}
