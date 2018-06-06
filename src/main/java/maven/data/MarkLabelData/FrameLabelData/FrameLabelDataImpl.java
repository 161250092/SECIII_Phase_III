package maven.data.MarkLabelData.FrameLabelData;

import maven.model.label.frameLabel.FrameLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

import java.util.List;

public class FrameLabelDataImpl implements FrameLabelDataService{
    @Override
    public boolean saveLabelList(UserId userId, TaskId taskId, List<FrameLabel> imageLabel) {
        return false;
    }

    @Override
    public boolean deleteLabel(UserId userId, TaskId taskId) {
        return false;
    }

    @Override
    public List<FrameLabel> getLabelList(UserId userId, TaskId taskId) {
        return null;
    }
}
