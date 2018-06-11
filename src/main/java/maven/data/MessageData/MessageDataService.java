package maven.data.MessageData;

import maven.model.message.*;
import maven.model.primitiveType.MessageId;
import maven.model.primitiveType.UserId;

import java.util.List;

public interface MessageDataService {
    /**
     * 保存 发布任务消息
     * @param publishedTaskMessage 发布任务消息
     * @return 后台处理结果
     */
    boolean savePublishedTaskMessage(PublishedTaskMessage publishedTaskMessage);

    /**
     * 保存 接受任务消息
     * @param acceptedTaskMessage 接受任务消息
     * @return 后台处理结果
     */
    boolean saveAcceptedTaskMessage(AcceptedTaskMessage acceptedTaskMessage);

    /**
     * 保存 关注消息
     * @param guyMessage 关注消息
     * @return 后台处理结果
     */
    boolean saveGuyMessage(GuyMessage guyMessage);

    /**
     * 保存 账单消息
     * @param billMessage 账单消息
     * @return 后台处理结果
     */
    boolean saveBillMessage(BillMessage billMessage);

    /**
     * 保存 成就消息
     * @param achievementMessage 成就消息
     * @return 后台处理结果
     */
    boolean saveAchievementMessage(AchievementMessage achievementMessage);

    /**
     * 获取所有发布者未确认的已发布任务消息
     * @param userId 发布者Id
     * @return 发布任务消息的列表
     */
    List<PublishedTaskMessage> getUncheckedPublishedTaskMessage(UserId userId);

    /**
     * 获取所有工人未确认的已接受任务消息
     * @param userId 发布者Id
     * @return 接受任务消息的列表
     */
    List<AcceptedTaskMessage> getUncheckedAcceptedTaskMessage(UserId userId);

    /**
     * 获取所有关注消息
     * @param userId 工人Id
     * @return 关注消息的列表
     */
    List<GuyMessage> getUncheckedGuyMessage(UserId userId);

    /**
     * 获取所有未查看账单消息
     * @param userId 用户Id
     * @return 账单消息的列表
     */
    List<BillMessage> getUncheckedBillMessage(UserId userId);


    /**
     * 获取所有未查看成就消息
     * @param userId 用户Id
     * @return 成就消息的列表
     */
    List<AchievementMessage> getUncheckedAchievementMessage(UserId userId);

    /**
     * 发布者确认任务消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    boolean setRequestorTaskMessageChecked(MessageId messageId);

    /**
     * 工人确认任务消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    boolean setWorkerTaskMessageChecked(MessageId messageId);

    /**
     * 工人确认关注消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    boolean setGuyMessageChecked(MessageId messageId);

    /**
     * 用户确认账单消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    boolean setBillMessageChecked(MessageId messageId);

    /**
     * 用户确认成就消息
     * @param messageId 消息Id
     * @return 后台处理结果
     */
    boolean setAchievementMessageChecked(MessageId messageId);

    /**
     * 新建消息时 获取messageId
     * 根据已有的Message数量 生成新的Id
     * @return messageId
     */
    MessageId getMessageIdForCreateMessage();
}
