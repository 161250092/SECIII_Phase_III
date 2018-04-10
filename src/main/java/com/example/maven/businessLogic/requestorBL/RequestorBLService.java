package com.example.maven.businessLogic.requestorBL;

import com.example.maven.model.Task;

import java.util.List;

public interface RequestorBLService {
    void uploadImages();
    Exception assignTask(Task task);
    List<Task> getAssignedAndAccomplishedTaskList(String userId);
    List<Task> getAssignedButIncompleteTaskList(String userId);
    int getAssignedTaskNum();
}
