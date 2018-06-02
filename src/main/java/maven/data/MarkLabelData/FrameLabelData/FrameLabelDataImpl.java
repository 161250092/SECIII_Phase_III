package maven.data.MarkLabelData.FrameLabelData;

import maven.model.label.frameLabel.FrameLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskState;

import java.util.List;

public class FrameLabelDataImpl implements FrameLabelDataService{
    @Override
    public boolean saveLabelList(UserId userId, TaskId taskId, List<FrameLabel> imageLabel) {
        return false;
    }

    @Override
    public boolean reviseTaskState(UserId userId, TaskId taskId, AcceptedTaskState acceptedTaskState) {
        return false;
    }

    @Override
    public List<FrameLabel> getLabelList(UserId userId, TaskId taskId) {
        return null;
    }
}
