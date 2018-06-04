package maven.model.message;

import maven.model.primitiveType.Cash;
import maven.model.primitiveType.UserId;

public class BillMessage {
    //欲通知的用户Id
    private UserId userId;
    //账单类型
    private BillType billType;
    //账单原因
    private BillReason billReason;
    //流动的金额
    private Cash cash;

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

    public BillMessage(UserId userId, BillType billType, BillReason billReason, Cash cash) {
        this.userId = userId;
        this.billType = billType;
        this.billReason = billReason;
        this.cash = cash;
    }
}
