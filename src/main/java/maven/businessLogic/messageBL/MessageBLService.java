package maven.businessLogic.messageBL;

import maven.model.message.RequestorMessage;
import maven.model.message.WorkerMessage;
import maven.model.primitiveType.MessageId;
import maven.model.primitiveType.UserId;

public interface MessageBLService {
    /**
     * 获取所有发布者未确认的消息
     * @param userId 发布者Id
     * @return 发布者消息
     */
    RequestorMessage getAllRequestorMessage(UserId userId);


    /**
     * 获取所有工人未确认的消息
     * @param userId 工人Id
     * @return 工人消息
     */
    WorkerMessage getAllWorkerMessage(UserId userId);

    /**
     * 发布者确认任务消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    boolean checkRequestorTaskMessage(MessageId messageId);

    /**
     * 工人确认任务消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    boolean checkWorkerTaskMessage(MessageId messageId);

    /**
     * 工人确认关注消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    boolean checkGuyMessage(MessageId messageId);

    /**
     * 用户确认账单消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    boolean checkBillMessage(MessageId messageId);

    /**
     * 用户确认成就消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    boolean checkAchievementMessage(MessageId messageId);
}
