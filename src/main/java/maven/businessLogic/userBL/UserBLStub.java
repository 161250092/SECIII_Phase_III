package maven.businessLogic.userBL;

import maven.exception.util.SuccessException;
import maven.model.primitiveType.*;
import maven.model.user.Requestor;
import maven.model.user.User;
import maven.model.user.Worker;

public class UserBLStub implements UserBLService{
    @Override
    public UserId getUserId(Username userName) {
        return new UserId("testUserId");
    }

    @Override
    public User getUserInfo(UserId userId) {
        Requestor requestor = new Requestor(new UserId("Test0000"), new Username("Test0000"), new Password("123456"),
                new Email("Test0000@smail.nju.educn"), new Phone("123456789"), new Cash(1000),
                new Prestige(70), new TaskNum(6));

        Worker worker = new Worker(new UserId("Test0001"), new Username("Test0001"), new Password("123456"),
                new Email("Test0001@smail.nju.educn"), new Phone("123456780"), new Cash(1000),
                new Prestige(70), new TaskNum(6));

        if(userId.value.equals("worker01"))
            return worker;
        else
            return requestor;
    }

    @Override
    public Exception reviseUserEmail(UserId userId, Email email) {
        return new SuccessException();
    }

    @Override
    public Exception reviseUserPhone(UserId userId, Phone phone) {
        return new SuccessException();
    }

    @Override
    public Exception reduceCash(UserId userId, Cash cash) {
        return new SuccessException();
    }

    @Override
    public Exception increaseCash(UserId userId, Cash cash) {
        return new SuccessException();
    }

    @Override
    public Exception reducePrestige(UserId userId, Prestige prestige) {
        return new SuccessException();
    }

    @Override
    public Exception increasePrestige(UserId userId, Prestige prestige) {
        return new SuccessException();
    }


}
