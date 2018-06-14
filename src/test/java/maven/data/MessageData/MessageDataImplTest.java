package maven.data.MessageData;

import maven.model.message.*;
import maven.model.primitiveType.*;
import maven.model.task.AcceptedTaskState;
import maven.runner.TableInitializer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MessageDataImplTest {

    MessageDataImpl impl = new MessageDataImpl();
    TableInitializer initializer = new TableInitializer();

    public MessageDataImplTest(){
        initializer.cleanAllTable();
        List<PublishedTaskMessage> publishedTaskMessages = getPublishedTaskMessageListForTest();
        List<AcceptedTaskMessage> acceptedTaskMessages = getAcceptedTaskListForTest();
        List<GuyMessage> guyMessages = getGuyMessageListForTest();
        List<BillMessage> billMessages = getBillMessageForTest();
        List<AchievementMessage> achievementMessages = getAchievementMessageListForTest();

        for(PublishedTaskMessage publishedTaskMessage:publishedTaskMessages)
            impl.savePublishedTaskMessage(publishedTaskMessage);
        for(AcceptedTaskMessage acceptedTaskMessage:acceptedTaskMessages)
            impl.saveAcceptedTaskMessage(acceptedTaskMessage);
        for(GuyMessage guyMessage:guyMessages)
            impl.saveGuyMessage(guyMessage);
        for(BillMessage billMessage:billMessages)
            impl.saveBillMessage(billMessage);
        for(AchievementMessage achievementMessage:achievementMessages)
            impl.saveAchievementMessage(achievementMessage);
    }

    @Test
    public void getUncheckedPublishedTaskMessage(){
        List<PublishedTaskMessage> l1 = impl.getUncheckedPublishedTaskMessage(new UserId("00000001"));
        List<PublishedTaskMessage> l2 = impl.getUncheckedPublishedTaskMessage(new UserId("00000002"));
        assertEquals("The size of l1", 2, l1.size());
        assertEquals("000000000001 MessageId",
                "000000000001", l1.get(0).getMessageId().value);
        assertEquals("00000001_ImageLabel_1622440180000 TaskId",
                "00000001_ImageLabel_1622440180000", l1.get(0).getTaskId().value);
        assertEquals("worker0001 UserId",
                "worker0001", l1.get(0).getWorkerId().value);
        assertEquals("worker01 Username",
                "worker01", l1.get(0).getWorkerName().value);
        assertEquals("200 Cash",
                200, l1.get(1).getCash().value, 0.001);

        assertEquals("The size of l2", 1, l2.size());
        assertEquals("000000000003 MessageId",
                "000000000003", l2.get(0).getMessageId().value);
        assertEquals("00000001_ImageLabel_1622440190000 TaskId",
                "00000001_ImageLabel_1622440190000", l2.get(0).getTaskId().value);
        assertEquals("worker0002 UserId",
                "worker0002", l2.get(0).getWorkerId().value);
        assertEquals("worker02 Username",
                "worker02", l2.get(0).getWorkerName().value);
        assertEquals("300 Cash",
                300, l2.get(0).getCash().value, 0.001);
    }

    @Test
    public void getUncheckedAcceptedTaskMessage(){
        List<AcceptedTaskMessage> l1 = impl.getUncheckedAcceptedTaskMessage(new UserId("worker0001"));
        List<AcceptedTaskMessage> l2 = impl.getUncheckedAcceptedTaskMessage(new UserId("worker0002"));
        List<AcceptedTaskMessage> l3 = impl.getUncheckedAcceptedTaskMessage(new UserId("worker0003"));
        List<AcceptedTaskMessage> l4 = impl.getUncheckedAcceptedTaskMessage(new UserId("worker0004"));

        assertEquals("The size of l1", 1, l1.size());
        assertEquals("000000000004 MessageId",
                "000000000004", l1.get(0).getMessageId().value);
        assertEquals("00000001_ImageLabel_1622440180000 TaskId",
                "00000001_ImageLabel_1622440180000", l1.get(0).getTaskId().value);
        assertEquals("100 Cash",
                100, l1.get(0).getCash().value, 0.01);
        assertEquals("PASSED",
                "PASSED", l1.get(0).getAcceptedTaskState().toString());

        assertEquals("The size of l2", 1, l2.size());
        assertEquals("000000000005 MessageId",
                "000000000005", l2.get(0).getMessageId().value);
        assertEquals("REJECTED",
                "REJECTED", l2.get(0).getAcceptedTaskState().toString());

        assertEquals("The size of l3", 1, l3.size());
        assertEquals("000000000006 MessageId",
                "000000000006", l3.get(0).getMessageId().value);
        assertEquals("ABANDONED_BY_REQUESTOR",
                "ABANDONED_BY_REQUESTOR", l3.get(0).getAcceptedTaskState().toString());

        assertEquals("The size of l4", 1, l4.size());
        assertEquals("000000000007 MessageId",
                "000000000007", l4.get(0).getMessageId().value);
        assertEquals("TERMINATED",
                "TERMINATED", l4.get(0).getAcceptedTaskState().toString());

    }

    @Test
    public void getUncheckedGuyMessage(){
       List<GuyMessage> l1 = impl.getUncheckedGuyMessage(new UserId("worker0001"));
       assertEquals("The size of l1",
               1, l1.size());
        assertEquals("000000000008 MessageId",
                "000000000008", l1.get(0).getMessageId().value);
        assertEquals("worker0001 workerId",
                "worker0001", l1.get(0).getWorkerId().value);
        assertEquals("requestor0001 requstorId",
                "requestor0001", l1.get(0).getRequestorId().value);
        assertEquals("requstor01 requstorName",
                "requstor01", l1.get(0).getRequestorName().value);
        assertEquals("00000001_ImageLabel_1622440180000 TaskId",
                "00000001_ImageLabel_1622440180000", l1.get(0).getTaskId().value);
        assertEquals("100 Cash",
                100, l1.get(0).getCash().value, 0.01);
    }

    @Test
    public void getUncheckedBillMessage(){
        List<BillMessage> l1 = impl.getUncheckedBillMessage(new UserId("requestor0001"));
        List<BillMessage> l2 = impl.getUncheckedBillMessage(new UserId("worker0001"));

        assertEquals("The size of l1",
                4, l1.size());
        assertEquals("000000000009 MessageId",
                "000000000009", l1.get(0).getMessageId().value);
        assertEquals("100 Cash",
                100, l1.get(0).getCash().value, 0.01);
        assertEquals("OUT",
                "OUT", l1.get(0).getBillType().toString());
        assertEquals("ASSIGN_TASK",
                "ASSIGN_TASK", l1.get(0).getBillReason().toString());

        assertEquals("OUT",
                "OUT", l1.get(1).getBillType().toString());
        assertEquals("SUPPLEMENT_TASK_CASH",
                "SUPPLEMENT_TASK_CASH", l1.get(1).getBillReason().toString());

        assertEquals("OUT",
                "OUT", l1.get(2).getBillType().toString());
        assertEquals("SUPPLEMENT_TASK_REQUIRED_NUM",
                "SUPPLEMENT_TASK_REQUIRED_NUM", l1.get(2).getBillReason().toString());

        assertEquals("IN",
                "IN", l1.get(3).getBillType().toString());
        assertEquals("TERMINATE_TASK",
                "TERMINATE_TASK", l1.get(3).getBillReason().toString());

        assertEquals("The size of l2",
                1, l2.size());
        assertEquals("000000000013 MessageId",
                "000000000013", l2.get(0).getMessageId().value);
        assertEquals("500 Cash",
                500, l2.get(0).getCash().value, 0.01);
        assertEquals("IN",
                "IN", l2.get(0).getBillType().toString());
        assertEquals("ACCOMPLISH_TASK",
                "ACCOMPLISH_TASK", l2.get(0).getBillReason().toString());
    }

    @Test
    public void getUncheckedAchievementMessage(){
        List<AchievementMessage> l1 = impl.getUncheckedAchievementMessage(new UserId("requestor0001"));
        List<AchievementMessage> l2 = impl.getUncheckedAchievementMessage(new UserId("worker0001"));
        assertEquals("The size of l1",
                1, l1.size());
        assertEquals("000000000014 MessageId",
                "000000000014", l1.get(0).getMessageId().value);
        assertEquals("achievement0001",
                "achievement0001", l1.get(0).getAchievementId());

        assertEquals("The size of l2",
                1, l2.size());
        assertEquals("000000000000 MessageId",
                "000000000000", l2.get(0).getMessageId().value);
        assertEquals("achievement0002",
                "achievement0002", l2.get(0).getAchievementId());
    }

    @Test
    public void getMessageId(){
        MessageId messageId = impl.getMessageIdForCreateMessage();
        assertEquals("000000000015", "000000000015", messageId.value);
    }

    @Test
    public void setRequestorTaskMessageChecked(){
        impl.setRequestorTaskMessageChecked(new MessageId("000000000001"));
        impl.setRequestorTaskMessageChecked(new MessageId("000000000003"));
        List<PublishedTaskMessage> l1 = impl.getUncheckedPublishedTaskMessage(new UserId("00000001"));
        List<PublishedTaskMessage> l2 = impl.getUncheckedPublishedTaskMessage(new UserId("00000002"));
        assertEquals("The size of l1", 1, l1.size());
        assertEquals("The size of l2", 0, l2.size());
    }

    @Test
    public void setWorkerTaskMessageChecked(){
        impl.setWorkerTaskMessageChecked(new MessageId("000000000004"));
        impl.setWorkerTaskMessageChecked(new MessageId("000000000005"));
        List<AcceptedTaskMessage> l1 = impl.getUncheckedAcceptedTaskMessage(new UserId("worker0001"));
        List<AcceptedTaskMessage> l2 = impl.getUncheckedAcceptedTaskMessage(new UserId("worker0002"));
        assertEquals("The size of l1", 0, l1.size());
        assertEquals("The size of l2", 0, l2.size());
    }

    @Test
    public void setGuyMessageChecked(){
        impl.setGuyMessageChecked(new MessageId("000000000008"));
        List<GuyMessage> l1 = impl.getUncheckedGuyMessage(new UserId("worker0001"));
        assertEquals("The size of l1", 0, l1.size());
    }

    @Test
    public void setBillMessageChecked(){
        impl.setBillMessageChecked(new MessageId("000000000009"));
        impl.setBillMessageChecked(new MessageId("000000000013"));
        List<BillMessage> l1 = impl.getUncheckedBillMessage(new UserId("requestor0001"));
        List<BillMessage> l2 = impl.getUncheckedBillMessage(new UserId("worker0001"));

        assertEquals("The size of l1", 3, l1.size());
        assertEquals("The size of l2", 0, l2.size());
    }

    @Test
    public void setAchievementMessageChecked(){
        impl.setAchievementMessageChecked(new MessageId("000000000014"));
        impl.setAchievementMessageChecked(new MessageId("000000000000"));

        List<AchievementMessage> l1 = impl.getUncheckedAchievementMessage(new UserId("requestor0001"));
        List<AchievementMessage> l2 = impl.getUncheckedAchievementMessage(new UserId("worker0001"));

        assertEquals("The size of l1", 0, l1.size());
        assertEquals("The size of l2", 0, l2.size());
    }

    private List<PublishedTaskMessage> getPublishedTaskMessageListForTest(){
        List<PublishedTaskMessage> list = new ArrayList<>();
        list.add(new PublishedTaskMessage(new MessageId("000000000001"), new UserId("00000001"),
                new TaskId("00000001_ImageLabel_1622440180000"), new UserId("worker0001"),
                new Username("worker01"), new Cash(100)));

        list.add(new PublishedTaskMessage(new MessageId("000000000002"), new UserId("00000001"),
                new TaskId("00000001_ImageLabel_1622440180000"), new UserId("worker0002"),
                new Username("worker02"), new Cash(200)));

        list.add(new PublishedTaskMessage(new MessageId("000000000003"), new UserId("00000002"),
                new TaskId("00000001_ImageLabel_1622440190000"), new UserId("worker0002"),
                new Username("worker02"), new Cash(300)));

        return list;
    }

    private List<AcceptedTaskMessage> getAcceptedTaskListForTest(){
        List<AcceptedTaskMessage> list = new ArrayList<>();

        list.add(new AcceptedTaskMessage(new MessageId("000000000004"),  new UserId("worker0001"),
                new TaskId("00000001_ImageLabel_1622440180000"), new Cash(100), AcceptedTaskState.PASSED));

        list.add(new AcceptedTaskMessage(new MessageId("000000000005"),  new UserId("worker0002"),
                new TaskId("00000001_ImageLabel_1622440180000"), new Cash(100), AcceptedTaskState.REJECTED));

        list.add(new AcceptedTaskMessage(new MessageId("000000000006"),  new UserId("worker0003"),
                new TaskId("00000001_ImageLabel_1622440180000"), new Cash(100), AcceptedTaskState.ABANDONED_BY_REQUESTOR));

        list.add(new AcceptedTaskMessage(new MessageId("000000000007"),  new UserId("worker0004"),
                new TaskId("00000001_ImageLabel_1622440180000"), new Cash(100), AcceptedTaskState.TERMINATED));

        return list;
    }

    private List<GuyMessage> getGuyMessageListForTest(){
        List<GuyMessage> list = new ArrayList<>();

        list.add(new GuyMessage(new MessageId("000000000008"), new UserId("worker0001"),
                new UserId("requestor0001"), new Username("requstor01"),
                new TaskId("00000001_ImageLabel_1622440180000"), new Cash(100)));

        return list;
    }

    private List<BillMessage> getBillMessageForTest(){
        List<BillMessage> list = new ArrayList<>();

        list.add(new BillMessage(new MessageId("000000000009"), new UserId("requestor0001"),
                BillType.OUT, BillReason.ASSIGN_TASK, new Cash(100)));
        list.add(new BillMessage(new MessageId("000000000010"), new UserId("requestor0001"),
                BillType.OUT, BillReason.SUPPLEMENT_TASK_CASH, new Cash(200)));
        list.add(new BillMessage(new MessageId("000000000011"), new UserId("requestor0001"),
                BillType.OUT, BillReason.SUPPLEMENT_TASK_REQUIRED_NUM, new Cash(300)));
        list.add(new BillMessage(new MessageId("000000000012"), new UserId("requestor0001"),
                BillType.IN, BillReason.TERMINATE_TASK, new Cash(400)));
        list.add(new BillMessage(new MessageId("000000000013"), new UserId("worker0001"),
                BillType.IN, BillReason.ACCOMPLISH_TASK, new Cash(500)));

        return list;
    }

    private List<AchievementMessage> getAchievementMessageListForTest(){
        List<AchievementMessage> list = new ArrayList<>();

        list.add(new AchievementMessage(new MessageId("000000000014"), new UserId("requestor0001"), "achievement0001"));
        list.add(new AchievementMessage(new MessageId("000000000000"), new UserId("worker0001"), "achievement0002"));

        return  list;
    }
}
