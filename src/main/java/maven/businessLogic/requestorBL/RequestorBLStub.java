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
    public List<PublishedTaskVO> getPublishedTaskList(UserId userId) {
        return null;
    }

    @Override
    public List<AcceptedTaskVO> getSubmittedTaskList(TaskId taskId, UserId userId) {
        return null;
    }

    @Override
    public Exception passTask(TaskId taskId, UserId userId) {
        return null;
    }

    @Override
    public Exception rejectTask(TaskId taskId, UserId userId) {
        return null;
    }

    @Override
    public Exception abandonTaskByRequestor(TaskId taskId, UserId userId) {
        return null;
    }

    @Override
    public List<PublishedTaskVO> getAssignedAndAccomplishedTaskList(UserId userId) {
        return null;
    }

    @Override
    public List<PublishedTaskVO> getAssignedButIncompleteTaskList(UserId userId) {
        return null;
    }

    @Override
    public PublishedTask getAssignedTask(UserId userId, TaskId taskId) {
        return null;
    }

    @Override
    public List<AcceptedTaskVO> getAcceptedTaskVOList(UserId userId, TaskId taskId) {
        return null;
    }

    @Override
    public Requestor getRequestorInfo(UserId userId) {
        return null;
    }

    private PublishedTask getPublishedTaskForTest(){
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

        List<String> tagList_1 = new ArrayList<>();
        tagList_1.add("ImageLabel01");
        tagList_1.add("ImageLabel02");

        List<String> tagList_2 = new ArrayList<>();
        tagList_2.add("ImageLabel03");
        tagList_2.add("ImageLabel04");

        List<String> tagList_3 = new ArrayList<>();
        tagList_3.add("ImageLabel05");
        tagList_3.add("ImageLabel06");

        List<Label> labelList = new ArrayList<>();
        labelList.add(new ImageLabel(tagList_1));
        labelList.add(new ImageLabel(tagList_2));
        labelList.add(new ImageLabel(tagList_3));

        Sample sample = new Sample(3, imageInedxList, labelList);

        List < PublishedTaskDetail > publishedTaskDetailList = new ArrayList<>();
        publishedTaskDetailList.add(new PublishedTaskDetail(new WorkerNum(10), new Cash(100), null));

        PublishedTask publishedTask = new PublishedTask(new TaskId("00000001_ImageLabel_1622440180000"), new UserId("00000001"), new LabelType("ImageLabel"),
                imageFilenameList, new TaskDescription("It's a published Task"), new WorkerNum(0), new WorkerNum(0), sample,
                new Cash(100), publishedTaskDetailList,
                PublishedTaskState.INCOMPLETE);

        return publishedTask;
    }

}

