package controller;

import businessLogic.workerBL.WorkerBLImpl;
import businessLogic.workerBL.WorkerBLService;
import businessLogic.workerBL.WorkerBLStub;
import model.po.Label;
import model.po.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 众包工人控制器
 */
@RestController
public class WorkerController implements WorkerBLService {

    private WorkerBLService workerBLService;

    public WorkerController(){
        workerBLService = new WorkerBLImpl();
    }

    @Override
    @RequestMapping(value = "/WorkerController/getAcceptedAndAccomplishedTaskList", method = RequestMethod.GET)
    public List<Task> getAcceptedAndAccomplishedTaskList(String userId){
        return workerBLService.getAcceptedAndAccomplishedTaskList(userId);
    }

    @Override
    @RequestMapping(value = "/WorkerController/getAcceptedButIncompleteTaskList", method = RequestMethod.GET)
    public List<Task> getAcceptedButIncompleteTaskList(String userId){
        return workerBLService.getAcceptedButIncompleteTaskList(userId);
    }

    @Override
    @RequestMapping(value = "/WorkerController/getAvailableTaskList", method = RequestMethod.GET)
    public List<Task> getAvailableTaskList(String userId) {
        return workerBLService.getAvailableTaskList(userId);
    }

    @Override
    @RequestMapping(value = "/WorkerController/acceptTask", method = RequestMethod.GET)
    public boolean acceptTask(String userId, String taskId) {
        return workerBLService.acceptTask(userId, taskId);
    }

    @Override
    @RequestMapping(value = "/WorkerController/getTaskById", method = RequestMethod.GET)
    public Task getTaskById(String taskId){
        return workerBLService.getTaskById(taskId);
    }

    @Override
    @RequestMapping(value = "/WorkerController/getUserScore", method = RequestMethod.GET)
    public int getUserScore(String userId){
        return workerBLService.getUserScore(userId);
    }

    @Override
    @RequestMapping(value = "/WorkerController/getUserRanking", method = RequestMethod.GET)
    public int getUserRanking(String userId){
        return workerBLService.getUserRanking(userId);
    }
}
