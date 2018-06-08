package maven.businessLogic.messageBL;

import maven.model.message.*;
import maven.model.primitiveType.*;
import maven.model.task.AcceptedTaskState;

import java.util.ArrayList;
import java.util.List;

public class MessageBLStub implements MessageBLService {
    @Override
    public RequestorMessage getAllRequestorMessage(UserId userId) {
        List<PublishedTaskMessage> taskMessageList = new ArrayList<>();
        taskMessageList.add(new PublishedTaskMessage(new MessageId("000000000001"), userId,
                new TaskId("00000001_ImageLabel_1622440180000"), new UserId("worker01"), new Username("worker02"), new Cash(100)));

        List<BillMessage> billMessageList = new ArrayList<>();
        billMessageList.add(new BillMessage(new MessageId("000000000002"), userId, BillType.OUT, BillReason.ASSIGN_TASK, new Cash(100)));

        List<AchievementMessage> achievementMessageList = new ArrayList<>();
        /**
         * 未完成 成就
         */
        achievementMessageList.add(new AchievementMessage(new MessageId("000000000003"), userId, "achievement0001"));

        RequestorMessage requestorMessage = new RequestorMessage(userId, taskMessageList, billMessageList, achievementMessageList);
        return requestorMessage;
    }

    @Override
    public WorkerMessage getAllWorkerMessage(UserId userId) {
        List<AcceptedTaskMessage> taskMessageList = new ArrayList<>();
        taskMessageList.add(new AcceptedTaskMessage(new MessageId("000000000001"), userId,
                new TaskId("00000001_ImageLabel_1622440180000"), new Cash(100), AcceptedTaskState.ACCEPTED));



        List<GuyMessage> guyMessageList = new ArrayList<>();

        List<BillMessage> billMessageList = new ArrayList<>();
        billMessageList.add(new BillMessage(new MessageId("000000000002"), userId, BillType.OUT, BillReason.ASSIGN_TASK, new Cash(100)));

        List<AchievementMessage> achievementMessageList = new ArrayList<>();
        /**
         * 未完成 成就
         */
        achievementMessageList.add(new AchievementMessage(new MessageId("000000000003"), userId, "achievement0001"));

        WorkerMessage workerMessage = new WorkerMessage(userId, taskMessageList, guyMessageList, billMessageList, achievementMessageList);
        return workerMessage;
    }

    @Override
    public boolean checkRequestorTaskMessage(MessageId messageId) {
        return true;
    }

    @Override
    public boolean checkWorkerTaskMessage(MessageId messageId) {
        return true;
    }

    @Override
    public boolean checkGuyMessage(MessageId messageId) {
        return true;
    }

    @Override
    public boolean checkBillMessage(MessageId messageId) {
        return true;
    }

    @Override
    public boolean checkAchievementMessage(MessageId messageId) {
        return true;
    }
}
