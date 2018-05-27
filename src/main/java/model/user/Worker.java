package model.user;

import model.primitiveType.*;

import java.io.Serializable;

/**
 * 工人
 */
public class Worker extends User implements Serializable {
    //一段时间内，工人能接受的最大任务数
    private TaskNum maxAcceptedTaskNum;

    public Worker(UserId userId, Username username, Password password,
                  Email email, Phone phone, Cash cash,
                  Prestige prestige, TaskNum maxAcceptedTaskNum) {
        super(userId, username, password, email, phone, cash, prestige);
        this.maxAcceptedTaskNum = maxAcceptedTaskNum;
    }

    public TaskNum getMaxAcceptedTaskNum() {
        return maxAcceptedTaskNum;
    }

    public void setMaxAcceptedTaskNum(TaskNum maxAcceptedTaskNum) {
        this.maxAcceptedTaskNum = maxAcceptedTaskNum;
    }
}
