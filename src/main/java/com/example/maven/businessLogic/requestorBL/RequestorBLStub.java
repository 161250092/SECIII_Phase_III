package com.example.maven.businessLogic.requestorBL;

import com.example.maven.exception.AssignException.SuccesfulAssignException;
import com.example.maven.model.po.Task;

import java.util.ArrayList;
import java.util.List;

public class RequestorBLStub implements RequestorBLService {
    @Override
    public void uploadImages() {

    }

    @Override
    public Exception assignTask(String taskJSON) {
        System.out.println(taskJSON);
        return new SuccesfulAssignException();
    }

    @Override
    public List<Task> getAssignedAndAccomplishedTaskList(String userId) {
        List<Task> taskList = new ArrayList<>();
        String[] imageInfo = {"test1","test2"};
        taskList.add(new Task("00000001_ImageLabel_1622440180000","ImageLabel",
                imageInfo,"This is an assigned and accomplished task",
                10,10,100 ));
        taskList.add(new Task("00000002_AreaLabel_1622440190000","AreaLabel",
                imageInfo,"This is an assigned and accomplished task",
                10,10,100 ));
        taskList.add(new Task("00000003_FrameLabel_1622440200000","FrameLabel",
                imageInfo,"This is an assigned and accomplished task",
                10,10,100 ));
        return taskList;
    }

    @Override
    public List<Task> getAssignedButIncompleteTaskList(String userId) {
        List<Task> taskList = new ArrayList<>();
        String[] imageInfo = {"test3","test4"};
        taskList.add(new Task("00000001_ImageLabel_1623440181000","ImageLabel",
                imageInfo,"This is an assigned but incomplete task",
                10,0,100 ));
        taskList.add(new Task("00000002_AreaLabel_1623440191000","AreaLabel",
                imageInfo,"This is an assigned but incomplete task",
                10,0,100 ));
        taskList.add(new Task("00000003_FrameLabel_1623440211000","FrameLabel",
                imageInfo,"This is an assigned but incomplete task",
                10,0,100 ));
        return taskList;
    }

    @Override
    public int getAssignedTaskNum() {
        return 3;
    }
}
