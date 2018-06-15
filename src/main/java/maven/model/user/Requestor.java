package maven.model.user;

import maven.model.primitiveType.*;

import java.io.Serializable;

/**
 * 发布者
 */
public class Requestor extends User implements Serializable {
    //一段时间内，发布者能发布的最大任务数
    private TaskNum maxPublishedTaskNum;

    public Requestor(UserId userId, Username username, Password password,
                     Email email, Phone phone, Cash cash,
                     Prestige prestige, TaskNum maxPublishedTaskNum) {
        super(userId, username, password, email, phone, cash, prestige, maxPublishedTaskNum);
        this.maxPublishedTaskNum = maxPublishedTaskNum;
    }

    public TaskNum getMaxPublishedTaskNum() {
        return maxPublishedTaskNum;
    }

    public void setMaxPublishedTaskNum(TaskNum maxPublishedTaskNum) {
        this.maxPublishedTaskNum = maxPublishedTaskNum;
    }
}
