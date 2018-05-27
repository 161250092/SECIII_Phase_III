package maven.businessLogic.markLabelBL.SetTaskAccomplishedBL;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

public class SetTaskAccomplishedBLStub implements SetTaskAccomplishedBLService {
    @Override
    public boolean setTaskAccomplished(TaskId taskId, UserId userId, boolean isWorker) {
        return false;
    }
}
