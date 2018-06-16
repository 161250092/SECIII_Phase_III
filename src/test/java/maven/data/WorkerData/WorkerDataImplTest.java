package maven.data.WorkerData;

import maven.data.TableInitializer;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.LabelScore;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTask;
import maven.model.task.AcceptedTaskState;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class WorkerDataImplTest {

    private WorkerDataImpl impl = new WorkerDataImpl();


    public WorkerDataImplTest(){
        TableInitializer initializer = new TableInitializer();
        initializer.cleanAllTable();

        List<AcceptedTask> l = getAcceptedTaskListForTest();
        for (AcceptedTask a: l){
            impl.acceptTask(a);
        }
    }

    @Test
    public void getAcceptedTaskListByUserId(){
        Set<String> s1 = new HashSet<>();
        s1.add("00000001_ImageLabel_1622440180000");
        s1.add("00000001_FrameLabel_1622440190000");
        s1.add("00000001_AreaLabel_1622440200000");
        List<AcceptedTask> a1 = impl.getAcceptedTaskListByUserId(new UserId("worker01"));
        assertEquals("worker01", 3, a1.size());
        for (AcceptedTask anA1 : a1) {
            assertTrue("worker01", s1.contains(anA1.getTaskId().value));
        }

        Set<String> s2 = new HashSet<>();
        s2.add("00000002_ImageLabel_1922440180000");
        s2.add("00000001_ImageLabel_1622440180000");
        List<AcceptedTask> a2 = impl.getAcceptedTaskListByUserId(new UserId("worker02"));
        assertEquals("worker02", 2, a2.size());
        for (AcceptedTask anA2 : a2) {
            assertTrue("worker02", s2.contains(anA2.getTaskId().value));
        }

        Set<String> s3 = new HashSet<>();
        s3.add("00000004_ImageLabel_1622440180000");
        s3.add("00000001_FrameLabel_1622440190000");
        List<AcceptedTask> a3 = impl.getAcceptedTaskListByUserId(new UserId("worker03"));
        assertEquals("worker03", 2, a3.size());
        for (AcceptedTask anA3 : a3) {
            assertTrue("worker03", s3.contains(anA3.getTaskId().value));
        }
    }

    @Test
    public void reviseAcceptedTaskState() {
        assertEquals(AcceptedTaskState.ACCEPTED ,
                impl.getAcceptedTaskById(new UserId("worker01"),new TaskId("00000001_FrameLabel_1622440190000")).getAcceptedTaskState());
        impl.reviseAcceptedTaskState(new UserId("worker01"), new TaskId("00000001_FrameLabel_1622440190000"), AcceptedTaskState.PASSED);
        assertEquals(AcceptedTaskState.PASSED ,
                impl.getAcceptedTaskById(new UserId("worker01"),new TaskId("00000001_FrameLabel_1622440190000")).getAcceptedTaskState());

        assertEquals(AcceptedTaskState.SUBMITTED ,
                impl.getAcceptedTaskById(new UserId("worker02"),new TaskId("00000002_ImageLabel_1922440180000")).getAcceptedTaskState());
        impl.reviseAcceptedTaskState(new UserId("worker02"), new TaskId("00000002_ImageLabel_1922440180000"), AcceptedTaskState.REJECTED);
        assertEquals(AcceptedTaskState.REJECTED ,
                impl.getAcceptedTaskById(new UserId("worker02"),new TaskId("00000002_ImageLabel_1922440180000")).getAcceptedTaskState());
    }

    @Test
    public void saveLabelScore() {
        assertEquals(82 ,
                impl.getAcceptedTaskById(new UserId("worker01"), new TaskId("00000001_AreaLabel_1622440200000")).getLabelScore().value, 0.001);
        impl.saveLabelScore(new UserId("worker01"), new TaskId("00000001_AreaLabel_1622440200000"), new LabelScore(28));
        assertEquals(28 ,
                impl.getAcceptedTaskById(new UserId("worker01"), new TaskId("00000001_AreaLabel_1622440200000")).getLabelScore().value, 0.001);

        assertEquals(85 ,
                impl.getAcceptedTaskById(new UserId("worker03"), new TaskId("00000004_ImageLabel_1622440180000")).getLabelScore().value, 0.001);
        impl.saveLabelScore(new UserId("worker03"), new TaskId("00000004_ImageLabel_1622440180000"), new LabelScore(58));
        assertEquals(58 ,
                impl.getAcceptedTaskById(new UserId("worker03"), new TaskId("00000004_ImageLabel_1622440180000")).getLabelScore().value, 0.001);
    }

    @Test
    public void getAcceptedTaskById() {
        assertEquals("worker01",
                impl.getAcceptedTaskById(new UserId("worker01"), new TaskId("00000001_FrameLabel_1622440190000")).getUserId().value);
        assertEquals("worker02",
                impl.getAcceptedTaskById(new UserId("worker02"), new TaskId("00000002_ImageLabel_1922440180000")).getUserId().value);

        assertNull(impl.getAcceptedTaskById(new UserId("worker01"), new TaskId("00000003_ImageLabel_1622440180001")));
    }

    @Test
    public void getAcceptedTaskList() {
        Set<String> s1 = new HashSet<>();
        s1.add("worker01");s1.add("worker02");
        List<AcceptedTask> l1 = impl.getAcceptedTaskList(new TaskId("00000001_ImageLabel_1622440180000"));
        assertEquals(2, l1.size());
        for (AcceptedTask a1: l1){
            assertTrue(s1.contains(a1.getUserId().value));
        }

        Set<String> s2 = new HashSet<>();
        s2.add("worker01");s2.add("worker03");
        List<AcceptedTask> l2 = impl.getAcceptedTaskList(new TaskId("00000001_FrameLabel_1622440190000"));
        for (AcceptedTask a2: l2){
            assertTrue(s2.contains(a2.getUserId().value));
        }
    }

    private List<AcceptedTask> getAcceptedTaskListForTest(){
        List<AcceptedTask> acceptedTaskList = new ArrayList<>();
        AcceptedTask acceptedTask_1 = new AcceptedTask(new UserId("worker01"), new TaskId("00000001_ImageLabel_1622440180000"),
                null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80));
        AcceptedTask acceptedTask_2 = new AcceptedTask(new UserId("worker01"), new TaskId("00000001_FrameLabel_1622440190000"),
                null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(81));
        AcceptedTask acceptedTask_3 = new AcceptedTask(new UserId("worker01"), new TaskId("00000001_AreaLabel_1622440200000"),
                null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(82));

        //完成、待审核
        AcceptedTask acceptedTask_4 = new AcceptedTask(new UserId("worker02"), new TaskId("00000002_ImageLabel_1922440180000"),
                null, new Cash(100), null, AcceptedTaskState.SUBMITTED, new LabelScore(83));
        //通过
        AcceptedTask acceptedTask_5 = new AcceptedTask(new UserId("worker02"), new TaskId("00000001_ImageLabel_1622440180000"),
                null, new Cash(100), null, AcceptedTaskState.PASSED, new LabelScore(84));
        //驳回
        AcceptedTask acceptedTask_6 = new AcceptedTask(new UserId("worker03"), new TaskId("00000004_ImageLabel_1622440180000"),
                null, new Cash(100), null, AcceptedTaskState.REJECTED, new LabelScore(85));
        //废弃
        AcceptedTask acceptedTask_7 = new AcceptedTask(new UserId("worker03"), new TaskId("00000001_FrameLabel_1622440190000"),
                null, new Cash(100), null, AcceptedTaskState.ABANDONED_BY_REQUESTOR, new LabelScore(86));

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