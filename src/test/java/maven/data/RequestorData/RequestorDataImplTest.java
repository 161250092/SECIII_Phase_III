package maven.data.RequestorData;

import maven.data.TableInitializer;
import maven.model.primitiveType.*;
import maven.model.task.*;
import org.junit.Test;

import javax.xml.soap.SAAJResult;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class RequestorDataImplTest {

    private RequestorDataImpl impl = new RequestorDataImpl();

    public RequestorDataImplTest(){
        TableInitializer initializer = new TableInitializer();
        initializer.cleanAllTable();

        List<PublishedTask> list = this.getPublishedTaskListForTest();
        List<Sample> slist = this.getSampleForTest();

        for (PublishedTask publishedTask: list){
            impl.saveTaskInfo(publishedTask);
        }
        for (Sample s: slist){
            impl.saveTaskSampleInfo(s);
        }
    }

    @Test
    public void getSample() {
        Sample a = impl.getSample(new TaskId("00000001_ImageLabel_1622440180000"));
        assertEquals("00000001_ImageLabel_1622440180000 id",
                "00000001_ImageLabel_1622440180000", a.getTaskId().value);
        assertEquals("00000001_ImageLabel_1622440180000 number",
                3, a.getNumber());
        int[] al = {2, 5, 8};
        List<Integer> atl = a.getImageIndexList();
        for (int i = 0; i < atl.size(); i++){
            assertEquals(al[i], (int)atl.get(i));
        }

        Sample b = impl.getSample(new TaskId("00000001_AreaLabel_1622440220000"));
        assertEquals("00000001_AreaLabel_1622440220000 id",
                "00000001_AreaLabel_1622440220000", b.getTaskId().value);
        assertEquals("00000001_AreaLabel_1622440220000 number",
                4, b.getNumber());
        int[] bl = {0, 1, 2, 3};
        List<Integer> btl = b.getImageIndexList();
        for (int i = 0; i < atl.size(); i++){
            assertEquals(bl[i], (int)btl.get(i));
        }
    }

    @Test
    public void reviseTaskAcceptedWorkerNum() {
        assertEquals("00000001_ImageLabel_1622440190000",
                22, impl.getPublishedTask(new TaskId("00000001_ImageLabel_1622440190000")).getAcceptedWorkerNum().value);
        impl.reviseTaskAcceptedWorkerNum(new TaskId("00000001_ImageLabel_1622440190000"),new WorkerNum(222));
        assertEquals("00000001_ImageLabel_1622440190000",
                222, impl.getPublishedTask(new TaskId("00000001_ImageLabel_1622440190000")).getAcceptedWorkerNum().value);

        assertEquals("testTaskId7",
                77, impl.getPublishedTask(new TaskId("testTaskId7")).getAcceptedWorkerNum().value);
        impl.reviseTaskAcceptedWorkerNum(new TaskId("testTaskId7"),new WorkerNum(777));
        assertEquals("testTaskId7",
                777 , impl.getPublishedTask(new TaskId("testTaskId7")).getAcceptedWorkerNum().value);

        assertEquals("testTaskId10",
                100, impl.getPublishedTask(new TaskId("testTaskId10")).getAcceptedWorkerNum().value);
        impl.reviseTaskAcceptedWorkerNum(new TaskId("testTaskId10"),new WorkerNum(1000));
        assertEquals("testTaskId10",
                1000, impl.getPublishedTask(new TaskId("testTaskId10")).getAcceptedWorkerNum().value);
    }

    @Test
    public void reviseTaskFinishedWorkerNum() {
        assertEquals("00000001_ImageLabel_1622440180000",
                1, impl.getPublishedTask(new TaskId("00000001_ImageLabel_1622440180000")).getFinishedWorkerNum().value);
        impl.reviseTaskFinishedWorkerNum(new TaskId("00000001_ImageLabel_1622440180000"),new WorkerNum(11));
        assertEquals("00000001_ImageLabel_1622440180000",
                11, impl.getPublishedTask(new TaskId("00000001_ImageLabel_1622440180000")).getFinishedWorkerNum().value);

        assertEquals("00000001_AreaLabel_1622440230000",
                6, impl.getPublishedTask(new TaskId("00000001_AreaLabel_1622440230000")).getFinishedWorkerNum().value);
        impl.reviseTaskFinishedWorkerNum(new TaskId("00000001_AreaLabel_1622440230000"),new WorkerNum(66));
        assertEquals("00000001_AreaLabel_1622440230000",
                66 , impl.getPublishedTask(new TaskId("00000001_AreaLabel_1622440230000")).getFinishedWorkerNum().value);

        assertEquals("testTaskId9",
                9, impl.getPublishedTask(new TaskId("testTaskId9")).getFinishedWorkerNum().value);
        impl.reviseTaskFinishedWorkerNum(new TaskId("testTaskId9"),new WorkerNum(99));
        assertEquals("testTaskId9",
                99, impl.getPublishedTask(new TaskId("testTaskId9")).getFinishedWorkerNum().value);
    }

    @Test
    public void revisePublishedTaskState() {
        assertEquals("00000001_FrameLabel_1622440200000",
                PublishedTaskState.INCOMPLETE.name(), impl.getPublishedTask(new TaskId("00000001_FrameLabel_1622440200000")).getPublishedTaskState().name());
        impl.revisePublishedTaskState(new TaskId("00000001_FrameLabel_1622440200000"), PublishedTaskState.ACCOMPLISHED);
        assertEquals("00000001_FrameLabel_1622440200000",
                PublishedTaskState.ACCOMPLISHED.name(), impl.getPublishedTask(new TaskId("00000001_FrameLabel_1622440200000")).getPublishedTaskState().name());

        assertEquals("testTaskId7",
                PublishedTaskState.DRAFT_WITH_SAMPLE.name(), impl.getPublishedTask(new TaskId("testTaskId7")).getPublishedTaskState().name());
        impl.revisePublishedTaskState(new TaskId("testTaskId7"), PublishedTaskState.ACCOMPLISHED);
        assertEquals("testTaskId7",
                PublishedTaskState.ACCOMPLISHED.name(), impl.getPublishedTask(new TaskId("testTaskId7")).getPublishedTaskState().name());

        //不允许修改
        assertEquals("00000001_AreaLabel_1622440230000",
                PublishedTaskState.ACCOMPLISHED.name(), impl.getPublishedTask(new TaskId("00000001_AreaLabel_1622440230000")).getPublishedTaskState().name());
        impl.revisePublishedTaskState(new TaskId("00000001_AreaLabel_1622440230000"), PublishedTaskState.INCOMPLETE);
        assertEquals("00000001_AreaLabel_1622440230000",
                PublishedTaskState.INCOMPLETE.name(), impl.getPublishedTask(new TaskId("00000001_AreaLabel_1622440230000")).getPublishedTaskState().name());

        //不允许修改
        assertEquals("testTaskId10",
                PublishedTaskState.DRAFT_WITHOUT_SAMPLE.name(), impl.getPublishedTask(new TaskId("testTaskId10")).getPublishedTaskState().name());
        impl.revisePublishedTaskState(new TaskId("testTaskId10"), PublishedTaskState.ACCOMPLISHED);
        assertEquals("testTaskId10",
                PublishedTaskState.ACCOMPLISHED.name(), impl.getPublishedTask(new TaskId("testTaskId10")).getPublishedTaskState().name());
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
        Set<String> ex = new HashSet<>();
        ex.add("00000001_ImageLabel_1622440180000" + "_" + "ImageLabel");
        ex.add("00000001_ImageLabel_1622440190000" + "_" + "ImageLabel");
        ex.add("00000001_FrameLabel_1622440200000" + "_" + "FrameLabel");
        ex.add("00000001_FrameLabel_1622440210000" + "_" + "AreaLabel");

        List<PublishedTask> l = impl.getPublishedTaskList(new UserId("00000001"));
        for (PublishedTask t: l){
            assertTrue(t.getTaskId().value, ex.contains(t.getTaskId().value + "_" + t.getLabelType().value));
        }
    }

    @Test
    public void getPublishedTask() {
        PublishedTask p1 = impl.getPublishedTask(new TaskId("00000001_ImageLabel_1622440180000"));
        assertEquals("00000001_ImageLabel_1622440180000_ImageLabel_INCOMPLETE",
                p1.getTaskId().value + "_" + p1.getLabelType().value + "_" + p1.getPublishedTaskState().name());

        PublishedTask p2 = impl.getPublishedTask(new TaskId("00000001_ImageLabel_1622440190000"));
        assertEquals("00000001_ImageLabel_1622440190000_ImageLabel_ACCOMPLISHED",
                p2.getTaskId().value  + "_" + p2.getLabelType().value + "_" + p2.getPublishedTaskState().name());


        PublishedTask p3 = impl.getPublishedTask(new TaskId("testTaskId10"));
        assertEquals("testTaskId10_AreaLabel_DRAFT_WITHOUT_SAMPLE",
                p3.getTaskId().value  + "_" + p3.getLabelType().value + "_" + p3.getPublishedTaskState().name());
    }

    @Test
    public void getTaskType() {
        assertEquals("00000001_AreaLabel_1622440220000", TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, impl.getTaskType(new TaskId("00000001_AreaLabel_1622440220000")));
        assertEquals("00000001_ImageLabel_1622440190000", TaskType.HIGH_LEVEL_LABEL_REQUIRED, impl.getTaskType(new TaskId("00000001_ImageLabel_1622440190000")));
        assertEquals("testTaskId8", TaskType.VERY_HIGH_LEVEL_LABEL_REQUIRED, impl.getTaskType(new TaskId("testTaskId8")));
        assertEquals("testTaskId9", TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, impl.getTaskType(new TaskId("testTaskId9")));
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
                imageFilenameList, new TaskDescription("It's a published but incomplete Task"), new WorkerNum(11), new WorkerNum(1),
                publishedTaskDetailList,
                PublishedTaskState.INCOMPLETE);

        PublishedTask publishedTask_2 = new PublishedTask(new TaskId("00000001_ImageLabel_1622440190000"),
                new UserId("00000001"), TaskType.HIGH_LEVEL_LABEL_REQUIRED, new LabelType("ImageLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(22), new WorkerNum(2),
                publishedTaskDetailList,
                PublishedTaskState.ACCOMPLISHED);

        PublishedTask publishedTask_3 = new PublishedTask(new TaskId("00000001_FrameLabel_1622440200000"),
                new UserId("00000001"), TaskType.VERY_HIGH_LEVEL_LABEL_REQUIRED, new LabelType("FrameLabel"),
                imageFilenameList, new TaskDescription("It's a published but incomplete Task"), new WorkerNum(33), new WorkerNum(3),
                publishedTaskDetailList,
                PublishedTaskState.INCOMPLETE);

        PublishedTask publishedTask_4 = new PublishedTask(new TaskId("00000001_FrameLabel_1622440210000"),
                new UserId("00000001"), TaskType.HIGH_LEVEL_LABEL_REQUIRED, new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(44), new WorkerNum(4),
                publishedTaskDetailList,
                PublishedTaskState.ACCOMPLISHED);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        PublishedTask publishedTask_5 = new PublishedTask(new TaskId("00000001_AreaLabel_1622440220000"),
                new UserId("00000002"), TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published but incomplete Task"), new WorkerNum(55), new WorkerNum(5),
                publishedTaskDetailList,
                PublishedTaskState.INCOMPLETE);

        PublishedTask publishedTask_6 = new PublishedTask(new TaskId("00000001_AreaLabel_1622440230000"),
                new UserId("00000002"), TaskType.VERY_HIGH_LEVEL_LABEL_REQUIRED, new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(66), new WorkerNum(6),
                publishedTaskDetailList,
                PublishedTaskState.ACCOMPLISHED);

        PublishedTask publishedTask_7 = new PublishedTask(new TaskId("testTaskId7"),
                new UserId("00000002"), TaskType.HIGH_LEVEL_LABEL_REQUIRED, new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(77), new WorkerNum(7),
                publishedTaskDetailList,
                PublishedTaskState.DRAFT_WITH_SAMPLE);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        PublishedTask publishedTask_8 = new PublishedTask(new TaskId("testTaskId8"),
                new UserId("00000003"), TaskType.VERY_HIGH_LEVEL_LABEL_REQUIRED, new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(88), new WorkerNum(8),
                publishedTaskDetailList,
                PublishedTaskState.DRAFT_WITH_SAMPLE);

        PublishedTask publishedTask_9 = new PublishedTask(new TaskId("testTaskId9"),
                new UserId("00000003"), TaskType.ORDINARY_LEVEL_LABEL_REQUIRED, new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(99), new WorkerNum(9),
                publishedTaskDetailList,
                PublishedTaskState.DRAFT_WITHOUT_SAMPLE);

        PublishedTask publishedTask_10 = new PublishedTask(new TaskId("testTaskId10"),
                new UserId("00000003"), TaskType.HIGH_LEVEL_LABEL_REQUIRED, new LabelType("AreaLabel"),
                imageFilenameList, new TaskDescription("It's a published and accomplished Task"), new WorkerNum(100), new WorkerNum(10),
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

    private List<Sample> getSampleForTest(){
        List<Sample> l = new ArrayList<>();

        List<Integer> a = new ArrayList<>();
        a.add(2);a.add(5);a.add(8);
        l.add(new Sample(new TaskId("00000001_ImageLabel_1622440180000"), 3, a));

        List<Integer> b = new ArrayList<>();
        b.add(0);b.add(1);b.add(2);b.add(3);
        l.add(new Sample(new TaskId("00000001_AreaLabel_1622440220000"), 4, b));

        return l;
    }
}