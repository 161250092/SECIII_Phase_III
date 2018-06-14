package maven.data.WorkerData;

import jdk.nashorn.internal.runtime.ListAdapter;
import maven.data.TableInitializer;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.LabelScore;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTask;
import maven.model.task.AcceptedTaskState;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class WorkerDataImplTest {

    WorkerDataImpl impl = new WorkerDataImpl();
    TableInitializer initializer = new TableInitializer();


    public WorkerDataImplTest(){
        initializer.cleanAllTable();

        impl.acceptTask(new AcceptedTask(new UserId("worker01"), new TaskId("00000001_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80)));
        impl.acceptTask(new AcceptedTask(new UserId("worker01"), new TaskId("00000001_FrameLabel_1622440190000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80)));

        impl.acceptTask(new AcceptedTask(new UserId("worker02"), new TaskId("00000001_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80)));
        impl.acceptTask(new AcceptedTask(new UserId("worker02"), new TaskId("00000001_FrameLabel_1622440190000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80)));
    }

    @Test
    public void acceptTask() {
        UserId u1 = new UserId("worker01");
        TaskId t1 = new TaskId("00000001_ImageLabel_1622440180000");
        UserId u2 = new UserId("worker02");
        TaskId t2 = new TaskId("00000001_FrameLabel_1622440190000");
        AcceptedTask u1t1 = new AcceptedTask(new UserId("worker01"), new TaskId("00000001_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80));
        AcceptedTask u1t2 = new AcceptedTask(new UserId("worker01"), new TaskId("00000001_FrameLabel_1622440190000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80));

        AcceptedTask u2t1 = new AcceptedTask(new UserId("worker02"), new TaskId("00000001_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80));
        AcceptedTask u2t2 = new AcceptedTask(new UserId("worker02"), new TaskId("00000001_FrameLabel_1622440190000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80));

        assertTrue(impl.acceptTask(u1t1));
        assertTrue(impl.acceptTask(u1t2));
        assertTrue(impl.acceptTask(u2t1));
        assertTrue(impl.acceptTask(u2t2));
        assertEquals(u1t1,impl.getAcceptedTaskById(u1,t1));
        assertEquals(u1t2,impl.getAcceptedTaskById(u1,t2));
        assertEquals(u2t1,impl.getAcceptedTaskById(u2,t1));
        assertEquals(u2t2,impl.getAcceptedTaskById(u2,t2));
    }


    @Test
    public void getAcceptedTaskListByUserId(){
        UserId u1 = new UserId("worker01");
        UserId u2 = new UserId("worker02");

        AcceptedTask u1t1 = new AcceptedTask(new UserId("worker01"), new TaskId("00000001_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80));
        AcceptedTask u1t2 = new AcceptedTask(new UserId("worker01"), new TaskId("00000001_FrameLabel_1622440190000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80));

        AcceptedTask u2t1 = new AcceptedTask(new UserId("worker02"), new TaskId("00000001_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80));
        AcceptedTask u2t2 = new AcceptedTask(new UserId("worker02"), new TaskId("00000001_FrameLabel_1622440190000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80));

        Set<AcceptedTask> s = new HashSet<>();
        s.add(u1t1);
        s.add(u1t2);
        s.add(u2t1);
        s.add(u2t2);

        List<AcceptedTask> a1 = impl.getAcceptedTaskListByUserId(u1);
        List<AcceptedTask> a2 = impl.getAcceptedTaskListByUserId(u2);

        for(AcceptedTask a : a1){
            assertTrue(s.contains(a));
        }
        for(AcceptedTask a : a2){
            assertTrue(s.contains(a));
        }
    }

    @Test
    public void reviseAcceptedTaskState() {
        UserId u1 = new UserId("worker01");
        UserId u2 = new UserId("worker02");
        TaskId t1 = new TaskId("00000001_ImageLabel_1622440180000");
        AcceptedTaskState s = AcceptedTaskState.PASSED;
        assertTrue(impl.reviseAcceptedTaskState(u1,t1,s));
        assertEquals(s,impl.getAcceptedTaskById(u1,t1).getAcceptedTaskState());
        assertTrue(impl.reviseAcceptedTaskState(u2,t1,s));
        assertEquals(s,impl.getAcceptedTaskById(u2,t1).getAcceptedTaskState());

    }

    @Test
    public void saveLableScore() {
        UserId u = new UserId("worker01");
        TaskId t = new TaskId("00000001_ImageLabel_1622440180000");
        LabelScore l1 = new LabelScore(85.0);
        LabelScore l2 = new LabelScore(90.0);
        assertTrue(impl.saveLableScore(u,t,l1));
        assertEquals(l1,impl.getAcceptedTaskById(u,t).getLabelScore());
        assertTrue(impl.saveLableScore(u,t,l2));
        assertEquals(l2,impl.getAcceptedTaskById(u,t).getLabelScore());
    }

    @Test
    public void getAcceptedTaskById() {
        UserId u1 = new UserId("worker01");
        UserId u2 = new UserId("worker02");
        TaskId t1 = new TaskId("00000001_ImageLabel_1622440180000");
        AcceptedTask a1 = impl.getAcceptedTaskById(u1,t1);
        AcceptedTask a2 = impl.getAcceptedTaskById(u2,t1);
        AcceptedTask r1 = new AcceptedTask(new UserId("worker01"), new TaskId("00000001_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.PASSED, new LabelScore(90.0));
        AcceptedTask r2 = new AcceptedTask(new UserId("worker02"), new TaskId("00000001_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80));
        assertEquals(a1,r1);
        assertEquals(a2,r2);
    }

    @Test
    public void getAcceptedTaskList() {
        TaskId t1 = new TaskId("00000001_ImageLabel_1622440180000");
        List<AcceptedTask> a1 = impl.getAcceptedTaskList(t1);
        for(int i = 0;i < a1.size();i++){
            assertEquals(t1,a1.get(i).getTaskId());
        }

        TaskId t2 = new TaskId("00000001_ImageLabel_1622440180000");
        List<AcceptedTask> a2 = impl.getAcceptedTaskList(t2);
        for(int i = 0;i < a2.size();i++){
            assertEquals(t2,a2.get(i).getTaskId());
        }
    }
}