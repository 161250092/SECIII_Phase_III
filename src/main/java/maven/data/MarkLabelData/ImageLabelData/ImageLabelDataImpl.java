package maven.data.MarkLabelData.ImageLabelData;

import maven.model.label.ImageLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

import java.util.List;

public class ImageLabelDataImpl implements ImageLabelDataService {
   @Override
    public boolean saveLabelList(UserId userId, TaskId taskId, List<ImageLabel> imageLabel) {
        return false;
    }

    @Override
    public boolean deleteLable(UserId userId, TaskId taskId) {
        return false;
    }

    @Override
    public List<ImageLabel> getLabelList(UserId userId, TaskId taskId) {
        return null;
    }
}
