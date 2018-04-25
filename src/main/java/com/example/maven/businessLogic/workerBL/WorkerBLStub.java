package com.example.maven.businessLogic.workerBL;

import com.example.maven.data.TaskData.TaskDataImpl;
import com.example.maven.data.TaskData.TaskDataService;
import com.example.maven.model.po.Label;
import com.example.maven.model.po.Task;

import java.util.ArrayList;
import java.util.List;

public class WorkerBLStub implements WorkerBLService {

    @Override
    public List<Task> getAcceptedAndAccomplishedTaskList(String userId) {
        List<Task> taskList = new ArrayList<>();
        String[] imageInfo = {"image000","image001"};
        taskList.add(new Task("00000001_ImageLabel_1622440191000","ImageLabel",
                imageInfo,"This is an accepted and accomplished task",
                10,10,100 ));
        taskList.add(new Task("00000002_AreaLabel_1623440181000","AreaLabel",
                imageInfo,"This is an accepted and accomplished task",
                10,10,100 ));
        return taskList;
    }

    @Override
    public List<Task> getAcceptedButIncompleteTaskList(String userId) {
        List<Task> taskList = new ArrayList<>();
        String[] imageInfo = {"image000","image001"};
        taskList.add(new Task("00000001_ImageLabel_1622440191000","ImageLabel",
                imageInfo,"This is an accepted and accomplished task",
                10,10,100 ));
        taskList.add(new Task("00000002_AreaLabel_1623440181000","AreaLabel",
                imageInfo,"This is an accepted but incompleted task",
                10,10,100 ));
        return taskList;
    }

    @Override
    public Task getTaskById(String taskId) {
        String[] imageInfo = {"image000","image001"};
        return new Task("00000002_AreaLabel_1628440181000","AreaLabel",
                imageInfo,"This is a task",
                10,10,100 );
    }

    @Override
    public int getUserScore(String userId) {
        return 0;
    }

    @Override
    public int getUserRanking(String userId) {
        return 1;
    }


}
