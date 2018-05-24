package controller;

import businessLogic.workerBL.WorkerBLImpl;
import businessLogic.workerBL.WorkerBLService;
import model.primitiveType.TaskId;
import model.primitiveType.UserId;
import model.task.AcceptedTask;
import model.vo.AcceptedTaskVO;
import model.vo.PublishedTaskVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 众包工人控制器
 */
@RestController
public class WorkerController implements WorkerBLService{

    private WorkerBLService workerBL;

    public WorkerController(){
        workerBL = new WorkerBLImpl();
    }

    @RequestMapping(value = "/WorkerController/getAcceptedAndAccomplishedTaskList", method = RequestMethod.GET)
    public List<Task> getAcceptedAndAccomplishedTaskList(String userId) {
        return workerBL.getAcceptedAndAccomplishedTaskList(new UserId(userId));
    }

    @RequestMapping(value = "/WorkerController/getAcceptedButIncompleteTaskList", method = RequestMethod.GET)
    public List<Task> getAcceptedButIncompleteTaskList(String userId) {
        return workerBL.getAcceptedButIncompleteTaskList(new UserId(userId));
    }

    @RequestMapping(value = "/WorkerController/getAvailableTaskList", method = RequestMethod.GET)
    public List<Task> getAvailableTaskList(String userId) {
        return workerBL.getAvailableTaskList(new UserId(userId));
    }

    @RequestMapping(value = "/WorkerController/acceptTask", method = RequestMethod.GET)
    public boolean acceptTask(String userId, String taskIListJSON) {
        return workerBL.acceptTask(new UserId(userId), taskIListJSON);
    }

    @RequestMapping(value = "/WorkerController/getTaskById", method = RequestMethod.GET)
    public Task getTaskById(String taskId) {
        return workerBL.getTaskById(new TaskId(taskId));
    }

    @RequestMapping(value = "/WorkerController/getUserScore", method = RequestMethod.GET)
    public int getUserScore(String userId) {
        return workerBL.getUserScore(new UserId(userId));
    }

    @RequestMapping(value = "/WorkerController/getUserRanking", method = RequestMethod.GET)
    public int getUserRanking(String userId) {
        return workerBL.getUserRanking(new UserId(userId));
    }

    @Override
    public List<AcceptedTaskVO> getAcceptedAndAccomplishedTaskList(UserId userId) {
        return null;
    }

    @Override
    public List<AcceptedTaskVO> getAcceptedButIncompleteTaskList(UserId userId) {
        return null;
    }

    @Override
    public List<PublishedTaskVO> getAvailableTaskList(UserId userId) {
        return null;
    }

    @Override
    public Exception acceptTask(UserId userId, TaskId[] taskIdList) {
        return null;
    }

    @Override
    public Exception abandonTaskByWorker(UserId userId, TaskId taskId) {
        return null;
    }

    @Override
    public AcceptedTask getAcceptedTaskById(TaskId taskId) {
        return null;
    }

    @Override
    public int getUserRanking(UserId userId) {
        return 0;
    }
}
