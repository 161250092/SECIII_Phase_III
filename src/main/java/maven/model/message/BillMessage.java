package maven.model.message;

import maven.model.primitiveType.Cash;
import maven.model.primitiveType.MessageId;
import maven.model.primitiveType.UserId;

import java.util.Date;

public class BillMessage {
    //消息Id
    private MessageId messageId;
    //欲通知的用户Id
    private UserId userId;
    //账单类型
    private BillType billType;
    //账单原因
    private BillReason billReason;
    //流动的金额
    private Cash cash;
    //是否被用户确认查看过
    private boolean isConfirmed;
    //消息生成的时间
    private Date date;


    public UserId getUserId() {
        return userId;
    }

    public BillType getBillType() {
        return billType;
    }

    public BillReason getBillReason() {
        return billReason;
    }

    public Cash getCash() {
        return cash;
    }

    public MessageId getMessageId() {
        return messageId;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public Date getDate(){
        return date;
    }

    public BillMessage(MessageId messageId, UserId userId, BillType billType, BillReason billReason, Cash cash) {
        this.messageId = messageId;
        this.userId = userId;
        this.billType = billType;
        this.billReason = billReason;
        this.cash = cash;
        this.date = new Date();
        this.isConfirmed = false;
    }

    public BillMessage(MessageId messageId, UserId userId, BillType billType, BillReason billReason, Cash cash, Date date) {
        this.messageId = messageId;
        this.userId = userId;
        this.billType = billType;
        this.billReason = billReason;
        this.cash = cash;
        this.date = date;
        this.isConfirmed = false;
    }
}