package maven.data.WorkerData;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTask;
import maven.model.task.PublishedTask;
import maven.model.user.Worker;

import java.util.List;

public class WorkerDataImpl implements WorkerDataService {
    @Override
    public List<AcceptedTask> getAcceptedAndAccomplishedTaskList(UserId userId) {
        return null;
    }

    @Override
    public List<AcceptedTask> getAcceptedTaskList(UserId userId) {
        return null;
    }


    //@Override
    public List<PublishedTask> getAvailableTaskList() {
        return null;
    }

    @Override
    public boolean acceptTask(AcceptedTask acceptedTask) {
        return false;
    }

    @Override
    public boolean abandonTaskByWorker(UserId userId, TaskId taskId) {
        return false;
    }

    @Override
    public AcceptedTask getAcceptedTaskById(UserId userId, TaskId taskId) {
        return null;
    }
}
