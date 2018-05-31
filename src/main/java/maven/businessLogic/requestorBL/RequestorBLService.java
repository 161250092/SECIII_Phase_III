package maven.businessLogic.requestorBL;

import maven.model.primitiveType.Cash;
import maven.model.primitiveType.Filename;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.PublishedTask;
import maven.model.user.Requestor;
import maven.model.user.User;
import maven.model.vo.AcceptedTaskVO;
import maven.model.vo.PublishedTaskVO;

import java.util.List;

public interface RequestorBLService {

    /**
     * 上传欲发布的任务信息
     * @param publishedTaskVO 发布的任务VO
     * @param imageFilenameList 任务所含图片的文件名列表
     * @return 上传任务信息的状态
     */
    Exception uploadTaskInfo(PublishedTaskVO publishedTaskVO, List<Filename> imageFilenameList);

    /**
     * 发布任务
     * @param taskId 任务Id
     * @return 后端处理任务发布请求的结果
     */
    Exception assignTask(TaskId taskId);

    /**
     * 撤销已发布的任务
     * @param taskId 任务Id
     * @return 后端处理任务撤销请求的结果
     */
    Exception revokeTask(TaskId taskId);

    /**
     * 修改已发布的任务（追加任务的悬赏金额）
     * @param taskId 任务Id
     * @param cash 追加的金额数
     * @return 后端处理任务修改请求的结果
     */
    Exception reviseTask(TaskId taskId, Cash cash);

    /**
     * 获取发布者已发布的任务
     * @param userId 发布者Id
     * @return 已发布的任务列表
     */
    List<PublishedTaskVO> getPublishedTaskList(UserId userId);

    /**
     * 获取工人已完成并待审核的任务列表
     * @param userId 发布者Id
     * @return 待审核的任务列表
     */
    List<AcceptedTaskVO> getSubmittedTaskList(TaskId taskId, UserId userId);

    /**
     * 审批工人的任务时 选择通过
     * @param taskId 任务Id
     * @param userId 工人Id
     * @return 后台处理的结果
     */
    Exception passTask(TaskId taskId, UserId userId);

    /**
     * 审批工人的任务时 选择驳回
     * @param taskId 任务Id
     * @param userId 工人Id
     * @return 后台处理的结果
     */
    Exception rejectTask(TaskId taskId, UserId userId);

    /**
     * 审批工人的任务时 选择废弃
     * @param taskId 任务Id
     * @param userId 工人Id
     * @return 后台处理的结果
     */
    Exception abandonTaskByRequestor(TaskId taskId, UserId userId);

    /**
     * 获取发布且已完成任务的列表
     * @param userId 发布者Id
     * @return 发布且已完成任务的列表
     */
    List<PublishedTaskVO> getAssignedAndAccomplishedTaskList(UserId userId);

    /**
     * 获取发布但未完成任务的列表
     * @param userId 发布者Id
     * @return 发布但未完成任务的列表
     */
    List<PublishedTaskVO> getAssignedButIncompleteTaskList(UserId userId);

    /**
     * 获取具体的发布任务的信息
     * @param userId 发布者Id
     * @param taskId 任务Id
     * @return 任务详情
     */
    PublishedTask getAssignedTask(UserId userId, TaskId taskId);

    /**
     * 查看已接受某任务的工人任务完成情况
     * @param userId 发布者Id
     * @param taskId 任务Id
     * @return 任务列表
     */
    List<AcceptedTaskVO> getAcceptedTaskVOList(UserId userId, TaskId taskId);


    /**
     * 获取发布者的个人信息
     * @param userId 发布者Id
     * @return Requestor对象
     */
    Requestor getRequestorInfo(UserId userId);
}
