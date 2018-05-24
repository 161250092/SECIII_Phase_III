package businessLogic.workerBL;

import data.TaskData.TaskDataImpl;
import data.TaskData.TaskDataService;
import model.po.Label;
import model.po.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class WorkerBLStub implements WorkerBLService {

    @Override
    public List<Task> getAcceptedAndAccomplishedTaskList(String userId) {
        List<Task> taskList = new ArrayList<>();
        String[] imageInfo = {"test1","test2"};
        taskList.add(new Task("00000001_ImageLabel_1622440180000","ImageLabel",
                imageInfo,"This is an accepted and accomplished task",
                10,10,100 ));
        taskList.add(new Task("00000002_AreaLabel_1622440181000","AreaLabel",
                imageInfo,"This is an accepted and accomplished task",
                10,10,100 ));
        taskList.add(new Task("00000003_FrameLabel_1622440200000","FrameLabel",
                imageInfo,"This is an assigned and accomplished task",
                10,10,100 ));
        return taskList;
    }

    @Override
    public List<Task> getAcceptedButIncompleteTaskList(String userId) {
        List<Task> taskList = new ArrayList<>();
        String[] imageInfo = {"test3","test4"};
        taskList.add(new Task("00000001_ImageLabel_1623440190000","ImageLabel",
                imageInfo,"This is an accepted but incomplete task",
                10,10,100 ));
        taskList.add(new Task("00000002_AreaLabel_1623440201000","AreaLabel",
                imageInfo,"This is an accepted but incomplete task",
                10,10,100 ));
        taskList.add(new Task("00000003_FrameLabel_1623440210000","FrameLabel",
                imageInfo,"This is an accepted but incomplete task",
                10,10,100 ));
        return taskList;
    }

    @Override
    public List<Task> getAvailableTaskList(String userId) {
        List<Task> taskList = new ArrayList<>();
        String[] imageInfo = {"test7","test8"};
        taskList.add(new Task("00000001_ImageLabel_1822440180000","ImageLabel",
                imageInfo,"This is a task",
                10,0,100 ));
        taskList.add(new Task("00000002_AreaLabel_1822440181000","AreaLabel",
                imageInfo,"This is a task",
                10,0,100 ));
        taskList.add(new Task("00000003_FrameLabel_1822440200000","FrameLabel",
                imageInfo,"This is a task",
                10,0,100 ));
        return taskList;
    }

    @Override
    public boolean acceptTask(String userId, String taskIdListJSON) {
        System.out.println("userId: "+userId);
        System.out.println("taskIdListJSON: "+taskIdListJSON);

        Gson gson = new GsonBuilder().create();
        List<String> taskIdList = gson.fromJson(taskIdListJSON, ArrayList.class);
        System.out.println(taskIdList.get(0));

        return false;
    }

    @Override
    public Task getTaskById(String taskId) {
        String[] imageInfo = {"test5","test6"};
        return new Task("00000001_ImageLabel_1628440180000","ImageLabel",
                imageInfo,"This is a task",
                10,0,100 );
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