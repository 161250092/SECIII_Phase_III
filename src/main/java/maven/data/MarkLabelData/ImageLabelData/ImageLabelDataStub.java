package maven.data.MarkLabelData.ImageLabelData;

import maven.model.label.ImageLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskState;

import java.util.List;

public class ImageLabelDataStub implements ImageLabelDataService {
    @Override
    public boolean acceptTask(UserId userId, TaskId taskId, List<ImageLabel> imageLabel) {
        return false;
    }

    @Override
    public boolean reviseTaskState(UserId userId, TaskId taskId, AcceptedTaskState acceptedTaskState) {
        return false;
    }
}
