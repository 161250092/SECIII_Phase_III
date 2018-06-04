package maven.model.message;

import maven.model.primitiveType.Cash;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.primitiveType.Username;

public class GuyMessage {
    //欲通知的工人Id
    private UserId workerId;
    //欲关注工人的发布者Id
    private UserId requestorId;
    //欲关注工人的发布者昵称
    private Username requestorName;
    //工人完成该发布者的任务Id
    private TaskId taskId;
    //工人完成该发布者后获得的金额
    private Cash cash;
}
