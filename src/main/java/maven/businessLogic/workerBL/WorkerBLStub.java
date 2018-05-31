package maven.businessLogic.workerBL;

import maven.exception.util.SuccessException;
import maven.model.primitiveType.*;
import maven.model.task.*;
import maven.model.user.Worker;
import maven.model.vo.AcceptedTaskVO;
import maven.model.vo.PublishedTaskVO;

import java.util.ArrayList;
import java.util.List;

public class WorkerBLStub implements WorkerBLService {

    @Override
    public List<AcceptedTaskVO> getAcceptedAndAccomplishedTaskList(UserId userId) {
        List<AcceptedTaskVO> list = new ArrayList<>();
        list.add(new AcceptedTaskVO( getAcceptedTaskListForTest().get(3), new Username("worker01"), new LabelType("ImageLabel"), new TaskDescription("hello"), new LabelScore(80)) );
        list.add(new AcceptedTaskVO( getAcceptedTaskListForTest().get(4), new Username("worker01"), new LabelType("ImageLabel"), new TaskDescription("hello"), new LabelScore(80)) );
        list.add(new AcceptedTaskVO( getAcceptedTaskListForTest().get(5), new Username("worker01"), new LabelType("ImageLabel"), new TaskDescription("hello"), new LabelScore(80)) );
        list.add(new AcceptedTaskVO( getAcceptedTaskListForTest().get(6), new Username("worker01"), new LabelType("ImageLabel"), new TaskDescription("hello"), new LabelScore(80)) );
        return list;
    }

    @Override
    public List<AcceptedTaskVO> getAcceptedButIncompleteTaskList(UserId userId) {
        List<AcceptedTaskVO> list = new ArrayList<>();
        list.add(new AcceptedTaskVO( getAcceptedTaskListForTest().get(0), new Username("worker01"), new LabelType("ImageLabel"), new TaskDescription("hello"), new LabelScore(80)) );
        list.add(new AcceptedTaskVO( getAcceptedTaskListForTest().get(1), new Username("worker01"), new LabelType("FrameLabel"), new TaskDescription("hello"), new LabelScore(80)) );
        list.add(new AcceptedTaskVO( getAcceptedTaskListForTest().get(2), new Username("worker01"), new LabelType("AreaLabel"), new TaskDescription("hello"), new LabelScore(80)) );
        return list;
    }

    @Override
    public List<PublishedTaskVO> getAvailableTaskList(UserId userId) {
        List<Filename> imageFilenameList = new ArrayList<>();
        imageFilenameList.add(new Filename("test01"));
        imageFilenameList.add(new Filename("test02"));
        imageFilenameList.add(new Filename("test03"));

        List <PublishedTaskDetail> publishedTaskDetailList = new ArrayList<>();
        publishedTaskDetailList.add(new PublishedTaskDetail(new WorkerNum(10), new Cash(100), null));

        PublishedTask publishedTask_1 = new PublishedTask(new TaskId("00000001_ImageLabel_1622440180000"), new UserId("00000001"), new LabelType("ImageLabel"),
                imageFilenameList, new TaskDescription("It's a published but incomplete Task"), new WorkerNum(0), new WorkerNum(0), null,
                publishedTaskDetailList,
                PublishedTaskState.INCOMPLETE);

        PublishedTask publishedTask_2 = new PublishedTask(new TaskId("00000001_FrameLabel_1622440200000"), new UserId("00000001"), new LabelType("FrameLabel"),
                imageFilenameList, new TaskDescription("It's a published but incomplete Task"), new WorkerNum(0), new WorkerNum(0), null,
                publishedTaskDetailList,
                PublishedTaskState.INCOMPLETE);

        PublishedTask publishedTask_3 = new PublishedTask(new TaskId("00000001_AreaLabel_1622440220000"), new UserId("00000001"), new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published but incomplete Task"), new WorkerNum(0), new WorkerNum(0), null,
                publishedTaskDetailList,
                PublishedTaskState.INCOMPLETE);

        List<PublishedTaskVO> list = new ArrayList<>();
        list.add(new PublishedTaskVO(publishedTask_1));
        list.add(new PublishedTaskVO(publishedTask_2));
        list.add(new PublishedTaskVO(publishedTask_3));
        return list;
    }

    @Override
    public Exception acceptTask(UserId userId, List<TaskId> taskIdList) {
        return new SuccessException();
    }

    @Override
    public Exception abandonTaskByWorker(UserId userId, TaskId taskId) {
        return new SuccessException();
    }

    @Override
    public AcceptedTask getAcceptedTaskById(UserId userId, TaskId taskId) {
        return getAcceptedTaskListForTest().get(0);
    }


    @Override
    public int getUserRanking(UserId userId) {
        return 0;
    }



    private List<AcceptedTask> getAcceptedTaskListForTest(){
        List<AcceptedTask> acceptedTaskList = new ArrayList<>();
        AcceptedTask acceptedTask_1 = new AcceptedTask(new UserId("worker01"), new TaskId("00000001_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED);
        AcceptedTask acceptedTask_2 = new AcceptedTask(new UserId("worker01"), new TaskId("00000001_FrameLabel_1622440190000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED);
        AcceptedTask acceptedTask_3 = new AcceptedTask(new UserId("worker01"), new TaskId("00000001_AreaLabel_1622440200000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED);

        //完成、待审核
        AcceptedTask acceptedTask_4 = new AcceptedTask(new UserId("worker01"), new TaskId("00000002_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.SUBMITTED);
        //通过
        AcceptedTask acceptedTask_5 = new AcceptedTask(new UserId("worker01"), new TaskId("00000003_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.PASSED);
        //驳回
        AcceptedTask acceptedTask_6 = new AcceptedTask(new UserId("worker01"), new TaskId("00000004_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.REJECTED);
        //废弃
        AcceptedTask acceptedTask_7 = new AcceptedTask(new UserId("worker01"), new TaskId("00000005_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.ABANDONED_BY_REQUESTOR);

        acceptedTaskList.add(acceptedTask_1);
        acceptedTaskList.add(acceptedTask_2);
        acceptedTaskList.add(acceptedTask_3);
        acceptedTaskList.add(acceptedTask_4);
        acceptedTaskList.add(acceptedTask_5);
        acceptedTaskList.add(acceptedTask_6);
        acceptedTaskList.add(acceptedTask_7);

        return acceptedTaskList;
    }

}
