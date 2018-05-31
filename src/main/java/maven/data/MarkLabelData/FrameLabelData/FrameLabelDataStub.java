package maven.data.MarkLabelData.FrameLabelData;

import maven.data.MarkLabelData.FrameLabelData.FrameLabelDataService;
import maven.model.label.frameLabel.FrameLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskState;

import java.util.List;

public class FrameLabelDataStub implements FrameLabelDataService {
    @Override
    public boolean acceptTask(UserId userId, TaskId taskId, List<FrameLabel> imageLabel) {
        return false;
    }

    @Override
    public boolean reviseTaskState(UserId userId, TaskId taskId, AcceptedTaskState acceptedTaskState) {
        return false;
    }
}
