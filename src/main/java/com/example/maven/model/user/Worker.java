package com.example.maven.model.user;

import com.example.maven.model.primitiveType.*;

/**
 * 工人
 */
abstract public class Worker extends User {
    //一段时间内，工人能接受的最大任务数
    TaskNum maxAcceptedTaskNum;

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
