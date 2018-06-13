package maven.data.AdminData;

import maven.data.RequestorData.RequestorDataImpl;
import maven.data.TableInitializer;
import maven.model.primitiveType.*;
import maven.model.task.PublishedTask;
import maven.model.task.PublishedTaskDetail;
import maven.model.task.PublishedTaskState;
import maven.model.task.TaskType;
import maven.model.user.Admin;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class AdminDataImplTest {

    AdminDataImpl imp = new AdminDataImpl();
    RequestorDataImpl rimpl = new RequestorDataImpl();
    TableInitializer initializer = new TableInitializer();

    public AdminDataImplTest(){
        initializer.cleanAllTable();
    }

    @Test
    public void getAllPublishedTask() {
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

        rimpl.saveTaskInfo(publishedTask_1);
        rimpl.saveTaskInfo(publishedTask_2);

        Set<String> ex = new HashSet<>();
        ex.add("00000001_ImageLabel_1622440180000");
        ex.add("00000001_ImageLabel_1622440190000");

        List<PublishedTask> l = imp.getAllPublishedTask();
        for (PublishedTask aL: l){
            assertTrue(ex.contains(aL.getTaskId().value));
        }
    }

    @Test
    public void getAllAdmin() {
        imp.newAdmin(new Admin(new Username("admin1"), new UserId("1"), new Password("123456")));
        imp.newAdmin(new Admin(new Username("admin2"), new UserId("2"), new Password("987654")));

        List<Admin> l = imp.getAllAdmin();

        assertEquals("admin1", l.get(0).getUsername().value);
        assertEquals("1", l.get(0).getUserId().value);
        assertEquals("123456", l.get(0).getPassword().value);
        assertEquals("admin2", l.get(1).getUsername().value);
        assertEquals("2", l.get(1).getUserId().value);
        assertEquals("987654", l.get(1).getPassword().value);
    }
}