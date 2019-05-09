package maven.model.user;

import maven.model.primitiveType.*;

import java.io.Serializable;

/**
 * 用户
 */
abstract public class User implements Serializable{
    //用户Id
    private UserId userId;
    //用户昵称
    private Username username;
    //密码
    private Password password;

    //电子邮件，其中有邮件地址
    private Email email;
    //电话，其中有电话号码
    private Phone phone;

    //该用户账户中的金额
    private Cash cash;
    //威望值
    private Prestige prestige;
    //最大任务数
    private TaskNum taskNum;

    public User(UserId userId, Username username, Password password, Email email, Phone phone, Cash cash, Prestige prestige, TaskNum taskNum) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.cash = cash;
        this.prestige = prestige;
        this.taskNum = taskNum;
    }

    public UserId getUserId() {
        return userId;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public Email getEmail() {
        return email;
    }

    public Phone getPhone() {
        return phone;
    }

    public Cash getCash() {
        return cash;
    }

    public Prestige getPrestige() {
        return prestige;
    }

    public TaskNum getTaskNum() {
        return taskNum;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public void setCash(Cash cash) {
        if(cash.value >= 0) {
            this.cash = cash;
        }
    }

    public void setPrestige(Prestige prestige) {
        if(prestige.value >= 0){
            this.prestige = prestige;
        }
    }
}
