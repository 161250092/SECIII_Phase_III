package maven.data.RequestorData;

import maven.model.massTask.MassTaskDetail;
import maven.model.primitiveType.TaskId;

public class RequestorMassDateImpl implements RequestorMassTaskDataService {
    @Override
    public boolean saveMassTaskDetail(MassTaskDetail massTaskDetail) {
        return false;
    }

    @Override
    public MassTaskDetail getMassTaskDetail(TaskId taskId) {
        return null;
    }

    @Override
    public boolean isMassTask(TaskId taskId) {
        return false;
    }
}
