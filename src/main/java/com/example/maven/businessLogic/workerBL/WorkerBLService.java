package com.example.maven.businessLogic.workerBL;

import com.example.maven.model.po.Label;
import com.example.maven.model.po.Task;

import java.util.List;

public interface WorkerBLService {
    List<Task> getAcceptedAndAccomplishedTaskList(String userId);
    List<Task> getAcceptedButIncompleteTaskList(String userId);
    Task getTaskById(String taskId);
    boolean saveLabel(Label label);
    boolean submitAccomplishedTask(Task task);
    int getUserScore(String userId);
    int getUserRanking(String userId);
}
