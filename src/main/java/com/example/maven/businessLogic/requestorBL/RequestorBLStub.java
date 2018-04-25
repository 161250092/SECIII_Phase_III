package com.example.maven.businessLogic.requestorBL;

import com.example.maven.model.po.Task;

import java.util.List;

public class RequestorBLStub implements RequestorBLService {
    @Override
    public void uploadImages() {

    }

    @Override
    public Exception assignTask(String taskJSON) {
        return null;
    }

    @Override
    public List<Task> getAssignedAndAccomplishedTaskList(String userId) {
        return null;
    }

    @Override
    public List<Task> getAssignedButIncompleteTaskList(String userId) {
        return null;
    }

    @Override
    public int getAssignedTaskNum() {
        return 0;
    }
}
