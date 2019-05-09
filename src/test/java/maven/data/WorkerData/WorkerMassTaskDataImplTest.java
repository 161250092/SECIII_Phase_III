package maven.data.WorkerData;

import maven.data.TableInitializer;
import maven.model.massTask.ImageNum;
import maven.model.massTask.WorkerBid;
import maven.model.massTask.WorkerBidState;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class WorkerMassTaskDataImplTest {

    private WorkerMassTaskDataImpl impl = new WorkerMassTaskDataImpl();

    public WorkerMassTaskDataImplTest() {
        TableInitializer initializer = new TableInitializer();
        initializer.cleanAllTable();

        impl.saveWorkerBid(new WorkerBid(
                new UserId("wo1"),new TaskId("mT1"),0.2,
                new Cash(5.2),new ImageNum(50),WorkerBidState.WAITING)
        );
        impl.saveWorkerBid(new WorkerBid(
                new UserId("wo2"),new TaskId("mT1"),0.25,
                new Cash(10.2),new ImageNum(100),WorkerBidState.FAILED)
        );
        impl.saveWorkerBid(new WorkerBid(
                new UserId("wo3"),new TaskId("mT1"),0.3,
                new Cash(15.3),new ImageNum(150),WorkerBidState.SUCCESSFUL)
        );
        impl.saveWorkerBid(new WorkerBid(
                new UserId("wo1"),new TaskId("mT2"),0.4,
                new Cash(20.4),new ImageNum(200),WorkerBidState.WAITING)
        );
    }

    @Test
    public void getAllWorkerBidOfThisTask() {
        String[] ul = {"wo1", "wo2", "wo3"};
        double[] rl = {0.2, 0.25, 0.3};
        double[] cl = {5.2,10.2,15.3};
        int[] il = {50,100,150};

        List<WorkerBid> l = impl.getAllWorkerBidOfThisTask(new TaskId("mT1"));
        assertEquals("length", 3, l.size());
        for (int i = 0; i < l.size(); i++){
            assertEquals("worker"+i, ul[i], l.get(i).getWorkerId().value);
            assertEquals("task"+i, "mT1", l.get(i).getChosenTaskId().value);
            assertEquals("ratio"+i, rl[i], l.get(i).getRatioOfArrivedTime(), 0.001);
            assertEquals("unitPrice"+i, cl[i], l.get(i).getWantedUnitPrice().value, 0.001);
            assertEquals("imageNum"+i, il[i], l.get(i).getMaxWantedImageNum().value);
            assertEquals("state"+i, WorkerBidState.WAITING.name(), l.get(i).getWorkerBidState().name());
        }
    }

    @Test
    public void getAllBidOfThisWorker() {
        String[] tl = {"mT1","mT2"};
        double[] rl = {0.2,0.4};
        double[] cl = {5.2,20.4};
        int[] il = {50,200};

        List<WorkerBid> l = impl.getAllBidOfThisWorker(new UserId("wo1"));
        assertEquals("length", 2, l.size());
        for (int i = 0; i < l.size(); i++){
            assertEquals("worker"+i, "wo1", l.get(i).getWorkerId().value);
            assertEquals("task"+i, tl[i], l.get(i).getChosenTaskId().value);
            assertEquals("ratio"+i, rl[i], l.get(i).getRatioOfArrivedTime(), 0.001);
            assertEquals("unitPrice"+i, cl[i], l.get(i).getWantedUnitPrice().value, 0.001);
            assertEquals("imageNum"+i, il[i], l.get(i).getMaxWantedImageNum().value);
            assertEquals("state"+i, WorkerBidState.WAITING.name(), l.get(i).getWorkerBidState().name());
        }
    }

    @Test
    public void updateWorkerBidState() {
        impl.updateWorkerBidState(new UserId("wo1"), new TaskId("mT1"), WorkerBidState.SUCCESSFUL);
        impl.updateWorkerBidState(new UserId("wo2"), new TaskId("mT1"), WorkerBidState.SUCCESSFUL);
        impl.updateWorkerBidState(new UserId("wo3"), new TaskId("mT1"), WorkerBidState.FAILED);
        impl.updateWorkerBidState(new UserId("wo1"), new TaskId("mT2"), WorkerBidState.FAILED);

        WorkerBidState[] w1 = {WorkerBidState.SUCCESSFUL, WorkerBidState.SUCCESSFUL, WorkerBidState.FAILED};
        List<WorkerBid> l1 = impl.getAllWorkerBidOfThisTask(new TaskId("mT1"));
        assertEquals("length1", 3, l1.size());
        for (int i = 0; i < l1.size(); i++){
            assertEquals("state"+i, w1[i], l1.get(i).getWorkerBidState());
        }

        WorkerBidState[] w2 = {WorkerBidState.FAILED};
        List<WorkerBid> l2 = impl.getAllWorkerBidOfThisTask(new TaskId("mT2"));
        assertEquals("length2", 1, l2.size());
        for (int i = 0; i < l2.size(); i++){
            assertEquals("state"+i, w2[i], l2.get(i).getWorkerBidState());
        }
    }
}