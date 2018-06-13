package maven.data.WorkerData;

import maven.data.TableInitializer;

import maven.model.primitiveType.*;
import maven.model.task.AcceptedTask;
import maven.model.task.AcceptedTaskState;
import maven.model.vo.AcceptedTaskVO;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerDataImplTest {

    WorkerDataImpl impl = new WorkerDataImpl();
    TableInitializer initializer = new TableInitializer();


    public WorkerDataImplTest(){
        initializer.cleanAllTable();

        impl.acceptTask(new AcceptedTask(new UserId("worker01"), new TaskId("00000001_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80)));
        impl.acceptTask(new AcceptedTask(new UserId("worker01"), new TaskId("00000001_FrameLabel_1622440190000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80)));
        impl.acceptTask(new AcceptedTask(new UserId("worker01"), new TaskId("00000001_AreaLabel_1622440200000"), null, new Cash(100), null, AcceptedTaskState.ACCEPTED, new LabelScore(80)));
        impl.acceptTask(new AcceptedTask(new UserId("worker01"), new TaskId("00000002_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.SUBMITTED, new LabelScore(80)));
        impl.acceptTask(new AcceptedTask(new UserId("worker01"), new TaskId("00000003_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.PASSED, new LabelScore(80)));
        impl.acceptTask(new AcceptedTask(new UserId("worker01"), new TaskId("00000004_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.REJECTED, new LabelScore(80)));
        impl.acceptTask(new AcceptedTask(new UserId("worker01"), new TaskId("00000005_ImageLabel_1622440180000"), null, new Cash(100), null, AcceptedTaskState.ABANDONED_BY_REQUESTOR, new LabelScore(80)));

    }


}
