package maven.data.WorkerData;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTask;
import maven.model.task.AcceptedTaskState;

import java.util.List;

public class WorkerDataImpl implements WorkerDataService {
    @Override
    public List<AcceptedTask> getAcceptedAndAccomplishedTaskList(UserId userId) {
        return null;
    }

    @Override
    public List<AcceptedTask> getAcceptedTaskListByUserId(UserId userId) {
        return null;
    }

    @Override
    public List<AcceptedTask> getAcceptedTaskListByTaskId(TaskId taskId) {
        return null;
    }

    @Override
    public boolean acceptTask(AcceptedTask acceptedTask) {
        return false;
    }

    @Override
    public boolean reviseAcceptedTaskState(UserId userId, TaskId taskId, AcceptedTaskState acceptedTaskState) {
        return false;
    }

    @Override
    public AcceptedTask getAcceptedTaskById(UserId userId, TaskId taskId) {
        return null;
    }
}
