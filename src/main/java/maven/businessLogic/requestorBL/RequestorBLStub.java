package maven.businessLogic.requestorBL;

import maven.exception.util.SuccessException;
import maven.model.label.ImageLabel;
import maven.model.label.Label;
import maven.model.primitiveType.*;
import maven.model.task.*;
import maven.model.user.Requestor;
import maven.model.vo.AcceptedTaskVO;
import maven.model.vo.PublishedTaskVO;

import java.util.ArrayList;
import java.util.List;

public class RequestorBLStub implements RequestorBLService {

    @Override
    public Exception uploadTaskInfo(PublishedTaskVO publishedTaskVO, List<Filename> imageFilenameList) {
        return new SuccessException();
    }

    @Override
    public List<PublishedTaskVO> getTaskDraftList(String userId) {
        List<PublishedTaskVO> publishedTaskVOList = new ArrayList<>();
        publishedTaskVOList.add(new PublishedTaskVO(getPublishedTaskListForTest().get(0)));
        publishedTaskVOList.add(new PublishedTaskVO(getPublishedTaskListForTest().get(2)));
        return publishedTaskVOList;
    }

    @Override
    public Exception assignTask(TaskId taskId) {
        return new SuccessException();
    }

    @Override
    public Exception revokeTask(TaskId taskId) {
        return new SuccessException();
    }

    @Override
    public Exception reviseTask(TaskId taskId, Cash cash) {
        return new SuccessException();
    }


    @Override
    public List<AcceptedTaskVO> getSubmittedTaskList(TaskId taskId, UserId userId) {
        ArrayList<AcceptedTaskVO> l = new ArrayList<>();
        l.add(new AcceptedTaskVO(getAcceptedTaskListForTest().get(0),new Username("userName"), new LabelType("ImageLabel"), new TaskDescription("hello"), new LabelScore(88)));
        return l;
    }

    @Override
    public Exception passTask(TaskId taskId, UserId userId) {
        return new SuccessException();
    }

    @Override
    public Exception rejectTask(TaskId taskId, UserId userId) {
        return new SuccessException();
    }

    @Override
    public Exception abandonTaskByRequestor(TaskId taskId, UserId userId) {
        return new SuccessException();
    }

    @Override
    public List<PublishedTaskVO> getAssignedAndAccomplishedTaskList(UserId userId) {
        List<PublishedTaskVO> publishedTaskVOList = new ArrayList<>();
        publishedTaskVOList.add(new PublishedTaskVO(getPublishedTaskListForTest().get(1)));
        publishedTaskVOList.add(new PublishedTaskVO(getPublishedTaskListForTest().get(3)));
        publishedTaskVOList.add(new PublishedTaskVO(getPublishedTaskListForTest().get(5)));

        return publishedTaskVOList;
    }

    @Override
    public List<PublishedTaskVO> getAssignedButIncompleteTaskList(UserId userId) {
        List<PublishedTaskVO> publishedTaskVOList = new ArrayList<>();
        publishedTaskVOList.add(new PublishedTaskVO(getPublishedTaskListForTest().get(0)));
        publishedTaskVOList.add(new PublishedTaskVO(getPublishedTaskListForTest().get(2)));
        publishedTaskVOList.add(new PublishedTaskVO(getPublishedTaskListForTest().get(4)));

        return publishedTaskVOList;
    }

    @Override
    public PublishedTask getAssignedTask(UserId userId, TaskId taskId) {
        if(userId.value.equals("0001"))
            return getPublishedTaskListForTest().get(0);
        else{
            System.out.println("Here is not such a task!");
        }
            return null;
    }

    @Override
    public List<AcceptedTaskVO> getAcceptedTaskVOList(UserId userId, TaskId taskId) {
        List<AcceptedTaskVO> acceptedTaskVOList = new ArrayList<>();
        acceptedTaskVOList.add(new AcceptedTaskVO(getAcceptedTaskListForTest().get(0),new Username("worker01"), new LabelType("ImageLabel"), new TaskDescription("hello"), new LabelScore(88)));
        acceptedTaskVOList.add(new AcceptedTaskVO(getAcceptedTaskListForTest().get(1),new Username("worker02"), new LabelType("ImageLabel"), new TaskDescription("hello"), new LabelScore(88)));
        return acceptedTaskVOList;
    }



    private List<PublishedTask> getPublishedTaskListForTest(){
        List<Filename> imageFilenameList = new ArrayList<>();
        imageFilenameList.add(new Filename("test01"));
        imageFilenameList.add(new Filename("test02"));
        imageFilenameList.add(new Filename("test03"));
        imageFilenameList.add(new Filename("test04"));
        imageFilenameList.add(new Filename("test05"));

        List<Integer> imageInedxList = new ArrayList<>();
        imageInedxList.add(0);
        imageInedxList.add(1);
        imageInedxList.add(2);

//        List<String> tagList_1 = new ArrayList<>();
//        tagList_1.add("ImageLabel01");
//        tagList_1.add("ImageLabel02");
//
//        List<String> tagList_2 = new ArrayList<>();
//        tagList_2.add("ImageLabel03");
//        tagList_2.add("ImageLabel04");
//
//        List<String> tagList_3 = new ArrayList<>();
//        tagList_3.add("ImageLabel05");
//        tagList_3.add("ImageLabel06");
//
//        List<Label> labelList = new ArrayList<>();
//        labelList.add(new ImageLabel(tagList_1));
//        labelList.add(new ImageLabel(tagList_2));
//        labelList.add(new ImageLabel(tagList_3));
//
//        Sample sample = new Sample(3, imageInedxList, labelList);

        List < PublishedTaskDetail > publishedTaskDetailList = new ArrayList<>();
        publishedTaskDetailList.add(new PublishedTaskDetail(new WorkerNum(10), new Cash(100), null));

        PublishedTask publishedTask_1 = new PublishedTask(new TaskId("00000001_ImageLabel_1622440180000"), new UserId("00000001"), new LabelType("ImageLabel"),
                imageFilenameList, new TaskDescription("It's a published but incomplete Task"), new WorkerNum(0), new WorkerNum(0), null,
                publishedTaskDetailList,
                PublishedTaskState.INCOMPLETE);

        PublishedTask publishedTask_2 = new PublishedTask(new TaskId("00000001_ImageLabel_1622440190000"), new UserId("00000001"), new LabelType("ImageLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(10), new WorkerNum(10), null,
                publishedTaskDetailList,
                PublishedTaskState.ACOMPLISHED);

        PublishedTask publishedTask_3 = new PublishedTask(new TaskId("00000001_FrameLabel_1622440200000"), new UserId("00000001"), new LabelType("FrameLabel"),
                imageFilenameList, new TaskDescription("It's a published but incomplete Task"), new WorkerNum(0), new WorkerNum(0), null,
                publishedTaskDetailList,
                PublishedTaskState.INCOMPLETE);

        PublishedTask publishedTask_4 = new PublishedTask(new TaskId("00000001_FrameLabel_1622440210000"), new UserId("00000001"), new LabelType("FrameLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(10), new WorkerNum(10), null,
                publishedTaskDetailList,
                PublishedTaskState.ACOMPLISHED);

        PublishedTask publishedTask_5 = new PublishedTask(new TaskId("00000001_AreaLabel_1622440220000"), new UserId("00000001"), new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published but incomplete Task"), new WorkerNum(0), new WorkerNum(0), null,
                publishedTaskDetailList,
                PublishedTaskState.INCOMPLETE);

        PublishedTask publishedTask_6 = new PublishedTask(new TaskId("00000001_AreaLabel_1622440230000"), new UserId("00000001"), new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(10), new WorkerNum(10), null,
                publishedTaskDetailList,
                PublishedTaskState.ACOMPLISHED);

        List<PublishedTask> list = new ArrayList<>();
        list.add(publishedTask_1);
        list.add(publishedTask_2);
        list.add(publishedTask_3);
        list.add(publishedTask_4);
        list.add(publishedTask_5);
        list.add(publishedTask_6);
        return list;
    }

    private List<AcceptedTask> getAcceptedTaskListForTest(){
        List<AcceptedTask> acceptedTaskList = new ArrayList<>();
        AcceptedTask acceptedTask_1 = new AcceptedTask(new UserId("worker01"), new TaskId("00000001_ImageLabel_1622440190000"), null, new Cash(100), null, AcceptedTaskState.SUBMITTED);
        AcceptedTask acceptedTask_2 = new AcceptedTask(new UserId("worker02"), new TaskId("00000001_ImageLabel_1622440190000"), null, new Cash(100), null, AcceptedTaskState.SUBMITTED);
        acceptedTaskList.add(acceptedTask_1);
        acceptedTaskList.add(acceptedTask_2);
        return acceptedTaskList;
    }



}

