package maven.data.RequestorData;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTask;
import maven.model.task.AcceptedTaskState;
import maven.model.task.PublishedTask;
import maven.model.task.PublishedTaskDetail;
import maven.model.user.Requestor;

import java.util.List;

public class RequestorDataImpl implements RequestorDataService {
    @Override
    public boolean saveTaskInfo(PublishedTask publishedTask) {
        return false;
    }

    @Override
    public boolean reviseTaskInfo(PublishedTaskDetail publishedTaskDetail) {
        return false;
    }

    @Override
    public boolean revokeTask(TaskId taskId) {
        return false;
    }

    @Override
    public boolean reviseTask(TaskId taskId) {
        return false;
    }

    @Override
    public List<PublishedTask> getPublishedTaskList(UserId userId) {
        return null;
    }

    @Override
    public List<AcceptedTask> getSubmittedTaskList(TaskId taskId) {
        return null;
    }

    @Override
    public boolean approvalTask(TaskId taskId, UserId userId, AcceptedTaskState acceptedTaskState) {
        return false;
    }

    @Override
    public List<PublishedTask> getAssignedAndAccomplishedTaskList(UserId userId) {
        return null;
    }

    @Override
    public List<PublishedTask> getAssignedButIncompleteTaskList(UserId userId) {
        return null;
    }

    @Override
    public PublishedTask getAssignedTask(UserId userId, TaskId taskId) {
        return null;
    }

    @Override
    public List<AcceptedTask> getAcceptedTaskList(TaskId taskId) {
        return null;
    }

    @Override
    public Requestor getRequestorInfo(UserId userId) {
        return null;
    }
}
