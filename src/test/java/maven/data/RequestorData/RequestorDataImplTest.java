package maven.data.RequestorData;

import maven.data.TableInitializer;
import maven.model.primitiveType.*;
import maven.model.task.PublishedTask;
import maven.model.task.PublishedTaskDetail;
import maven.model.task.PublishedTaskState;
import maven.model.task.TaskType;
import org.junit.Test;

import javax.xml.soap.SAAJResult;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class RequestorDataImplTest {

    RequestorDataImpl impl = new RequestorDataImpl();
    List<PublishedTask> list;

    public RequestorDataImplTest(){
        TableInitializer initializer = new TableInitializer();
        initializer.cleanAllTable();

        list = this.getPublishedTaskListForTest();

        for (PublishedTask publishedTask: list){
            impl.saveTaskInfo(publishedTask);
        }
    }

    @Test
    public void getSample() {

    }

    @Test
    public void reviseTaskAcceptedWorkerNum() {
    }

    @Test
    public void reviseTaskFinishedWorkerNum() {
    }

    @Test
    public void revisePublishedTaskState() {
    }

    @Test
    public void getPublishedTaskIdList1() {
        Set<String> ex = new HashSet<>();
        ex.add("00000001_ImageLabel_1622440180000");
        ex.add("00000001_ImageLabel_1622440190000");
        ex.add("00000001_FrameLabel_1622440200000");
        ex.add("00000001_FrameLabel_1622440210000");

        List<TaskId> l = impl.getPublishedTaskIdList(new UserId("00000001"));
        for (TaskId t: l){
            assertTrue(t.value, ex.contains(t.value));
        }
    }
    @Test
    public void getPublishedTaskIdList2() {
        Set<String> ex = new HashSet<>();
        ex.add("00000001_AreaLabel_1622440220000");
        ex.add("00000001_AreaLabel_1622440230000");
        ex.add("testTaskId7");

        List<TaskId> l = impl.getPublishedTaskIdList(new UserId("00000002"));
        for (TaskId t: l){
            assertTrue(t.value, ex.contains(t.value));
        }
    }
    @Test
    public void getPublishedTaskIdList3() {
        Set<String> ex = new HashSet<>();
        ex.add("testTaskId8");
        ex.add("testTaskId9");
        ex.add("testTaskId10");

        List<TaskId> l = impl.getPublishedTaskIdList(new UserId("00000003"));
        for (TaskId t: l){
            assertTrue(t.value, ex.contains(t.value));
        }
    }

    @Test
    public void getPublishedTaskList() {
    }

    @Test
    public void getPublishedTask() {
    }

    @Test
    public void getTaskType() {
    }


    private List<PublishedTask> getPublishedTaskListForTest(){
        List<Filename> imageFilenameList = new ArrayList<>();
        imageFilenameList.add(new Filename("test01"));
        imageFilenameList.add(new Filename("test02"));
        imageFilenameList.add(new Filename("test03"));
        imageFilenameList.add(new Filename("test04"));
        imageFilenameList.add(new Filename("test05"));

        List <PublishedTaskDetail> publishedTaskDetailList = new ArrayList<>();
        publishedTaskDetailList.add(new PublishedTaskDetail(new WorkerNum(10), new Cash(100), null));

        PublishedTask publishedTask_1 = new PublishedTask(new TaskId("00000001_ImageLabel_1622440180000"),
                new UserId("00000001"),TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, new LabelType("ImageLabel"),
                imageFilenameList, new TaskDescription("It's a published but incomplete Task"), new WorkerNum(0), new WorkerNum(0),
                publishedTaskDetailList,
                PublishedTaskState.INCOMPLETE);

        PublishedTask publishedTask_2 = new PublishedTask(new TaskId("00000001_ImageLabel_1622440190000"),
                new UserId("00000001"), TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, new LabelType("ImageLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(10), new WorkerNum(10),
                publishedTaskDetailList,
                PublishedTaskState.ACCOMPLISHED);

        PublishedTask publishedTask_3 = new PublishedTask(new TaskId("00000001_FrameLabel_1622440200000"),
                new UserId("00000001"), TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, new LabelType("FrameLabel"),
                imageFilenameList, new TaskDescription("It's a published but incomplete Task"), new WorkerNum(0), new WorkerNum(0),
                publishedTaskDetailList,
                PublishedTaskState.INCOMPLETE);

        PublishedTask publishedTask_4 = new PublishedTask(new TaskId("00000001_FrameLabel_1622440210000"),
                new UserId("00000001"), TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, new LabelType("FrameLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(10), new WorkerNum(10),
                publishedTaskDetailList,
                PublishedTaskState.ACCOMPLISHED);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        PublishedTask publishedTask_5 = new PublishedTask(new TaskId("00000001_AreaLabel_1622440220000"),
                new UserId("00000002"), TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published but incomplete Task"), new WorkerNum(0), new WorkerNum(0),
                publishedTaskDetailList,
                PublishedTaskState.INCOMPLETE);

        PublishedTask publishedTask_6 = new PublishedTask(new TaskId("00000001_AreaLabel_1622440230000"),
                new UserId("00000002"), TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(10), new WorkerNum(10),
                publishedTaskDetailList,
                PublishedTaskState.ACCOMPLISHED);

        PublishedTask publishedTask_7 = new PublishedTask(new TaskId("testTaskId7"),
                new UserId("00000002"), TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(10), new WorkerNum(10),
                publishedTaskDetailList,
                PublishedTaskState.DRAFT_WITH_SAMPLE);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        PublishedTask publishedTask_8 = new PublishedTask(new TaskId("testTaskId8"),
                new UserId("00000003"), TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(10), new WorkerNum(10),
                publishedTaskDetailList,
                PublishedTaskState.DRAFT_WITH_SAMPLE);

        PublishedTask publishedTask_9 = new PublishedTask(new TaskId("testTaskId9"),
                new UserId("00000003"), TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(10), new WorkerNum(10),
                publishedTaskDetailList,
                PublishedTaskState.DRAFT_WITHOUT_SAMPLE);

        PublishedTask publishedTask_10 = new PublishedTask(new TaskId("testTaskId10"),
                new UserId("00000003"), TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(10), new WorkerNum(10),
                publishedTaskDetailList,
                PublishedTaskState.DRAFT_WITHOUT_SAMPLE);

        List<PublishedTask> list = new ArrayList<>();
        list.add(publishedTask_1);
        list.add(publishedTask_2);
        list.add(publishedTask_3);
        list.add(publishedTask_4);
        list.add(publishedTask_5);
        list.add(publishedTask_6);
        list.add(publishedTask_7);
        list.add(publishedTask_8);
        list.add(publishedTask_9);
        list.add(publishedTask_10);

        return list;
    }
}