package maven.data.LabelQualityData;

import maven.data.TableInitializer;
import maven.data.WorkerData.WorkerDataImpl;
import maven.model.primitiveType.*;
import maven.model.task.AcceptedTask;
import maven.model.task.AcceptedTaskQuality;
import maven.model.task.AcceptedTaskState;
import maven.model.task.WorkerDiscount;
import maven.model.vo.AcceptedTaskVO;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class LabelQualityDataImplTest {

    private LabelQualityDataImpl limpl = new LabelQualityDataImpl();
    private WorkerDataImpl wimpl = new WorkerDataImpl();

    public LabelQualityDataImplTest(){
        TableInitializer initializer = new TableInitializer();
        initializer.cleanAllTable();
    }

    @Test
    public void getAllAcceptedTaskQualityList() {
        wimpl.acceptTask(new AcceptedTask(new UserId("1"), new TaskId("00000001_ImageLabel_1622440180000"), new Date(), new Cash(100), new WorkerDiscount(), AcceptedTaskState.PASSED, new LabelScore(80)));
        wimpl.acceptTask(new AcceptedTask(new UserId("2"), new TaskId("00000002_ImageLabel_1622440180000"), new Date(), new Cash(50), new WorkerDiscount(), AcceptedTaskState.ABANDONED_BY_REQUESTOR, new LabelScore(90)));
        wimpl.acceptTask(new AcceptedTask(new UserId("1"), new TaskId("00000003_ImageLabel_1622440180000"), new Date(), new Cash(150), new WorkerDiscount(), AcceptedTaskState.ACCEPTED, new LabelScore(70)));
        wimpl.acceptTask(new AcceptedTask(new UserId("3"), new TaskId("00000001_ImageLabel_1622440180000"), new Date(), new Cash(200), new WorkerDiscount(), AcceptedTaskState.ACCEPTED, new LabelScore(60)));
        wimpl.acceptTask(new AcceptedTask(new UserId("4"), new TaskId("00000005_ImageLabel_1622440180000"), new Date(), new Cash(100), new WorkerDiscount(), AcceptedTaskState.ACCEPTED, new LabelScore(50)));

        Set<String> ex = new HashSet<>();
        ex.add("1" +  "_" + "00000001");
        ex.add("2" + "_" + "00000002");
        ex.add("1" + "_" + "00000003");
        ex.add("3" + "_" + "00000001");
        ex.add("4" + "_" + "00000005");

        List<AcceptedTaskQuality> l = limpl.getAllAcceptedTaskQualityList();
        for (AcceptedTaskQuality quality: l){
            assertTrue(quality.getWorkerId().value + "_" + quality.getRequestorId().value, ex.contains(quality.getWorkerId().value + "_" + quality.getRequestorId().value));
        }
    }
}