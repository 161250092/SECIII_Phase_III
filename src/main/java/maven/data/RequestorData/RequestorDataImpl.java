package maven.data.RequestorData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maven.data.MySQL.MySQLConnector;
import maven.model.label.ImageLabel;
import maven.model.label.Label;
import maven.model.label.areaLabel.Area;
import maven.model.label.areaLabel.AreaLabel;
import maven.model.label.frameLabel.Frame;
import maven.model.label.frameLabel.FrameLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.primitiveType.WorkerNum;
import maven.model.task.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RequestorDataImpl implements RequestorDataService {
    @Override
    public boolean saveTaskInfo(PublishedTask publishedTask) {
        return false;
    }

    @Override
    public boolean saveTaskSampleInfo(TaskId taskId, int ImageNum, List<Integer> imageIndexList) {
        return false;
    }

    @Override
    public Sample getSample(TaskId taskId) {

        return null;
    }

    @Override
    public boolean saveTaskDetail(TaskId taskId, PublishedTaskDetail publishedTaskDetail) {
        return false;
    }

    @Override
    public boolean reviseTaskAcceptedWorkerNum(TaskId taskId, WorkerNum acceptedWorkerNum) {
        return false;
    }

    @Override
    public boolean reviseTaskFinishedWorkerNum(TaskId taskId, WorkerNum finishedWorkerNum) {
        return false;
    }

    @Override
    public boolean revisePublishedTaskState(TaskId taskId, PublishedTaskState publishedTaskState) {
        return false;
    }

    @Override
    public List<TaskId> getPublishedTaskIdList(UserId userId) {
        return null;
    }

    public boolean reviseTaskInfo(PublishedTaskDetail publishedTaskDetail) {
        return false;
    }

    public boolean revokeTask(TaskId taskId) {
        return false;
    }

    public boolean reviseTask(TaskId taskId) {
        return false;
    }

    public List<PublishedTask> getPublishedTaskList(UserId userId) {
        return null;
    }

    @Override
    public PublishedTask getPublishedTask(TaskId taskId) {
        return null;
    }

    @Override
    public List<AcceptedTask> getAcceptedTaskList(TaskId taskId) {
        return null;
    }

}
