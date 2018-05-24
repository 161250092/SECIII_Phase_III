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

    @Override
    @RequestMapping(value = "/WorkerController/getAcceptedAndAccomplishedTaskList", method = RequestMethod.GET)
    public List<AcceptedTaskVO> getAcceptedAndAccomplishedTaskList(UserId userId) {
        return null;
    }

    @Override
    @RequestMapping(value = "/WorkerController/getAcceptedButIncompleteTaskList", method = RequestMethod.GET)
    public List<AcceptedTaskVO> getAcceptedButIncompleteTaskList(UserId userId) {
        return null;
    }

    @Override
    @RequestMapping(value = "/WorkerController/getAvailableTaskList", method = RequestMethod.GET)
    public List<PublishedTaskVO> getAvailableTaskList(UserId userId) {
        return null;
    }

    @Override
    @RequestMapping(value = "/WorkerController/acceptTask", method = RequestMethod.GET)
    public Exception acceptTask(UserId userId, TaskId[] taskIdList) {
        return null;
    }

    @Override
    public Exception abandonTaskByWorker(UserId userId, TaskId taskId) {
        return null;
    }

    @Override
    @RequestMapping(value = "/WorkerController/getAcceptedTaskById", method = RequestMethod.GET)
    public AcceptedTask getAcceptedTaskById(TaskId taskId) {
        return null;
    }

    @Override
    @RequestMapping(value = "/WorkerController/getUserRanking", method = RequestMethod.GET)
    public int getUserRanking(UserId userId) {
        return 0;
    }
}
