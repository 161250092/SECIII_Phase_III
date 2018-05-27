package businessLogic.userBL;

import model.primitiveType.*;
import model.user.User;

public class UserBLStub implements UserBLService{
    @Override
    public UserId getUserId(Username userName) {
        return new UserId("testUserId");
    }

    @Override
    public User getUserInfo(UserId userId) {
        return null;
    }

    @Override
    public Exception reviseUserEmail(UserId userId, Email email) {
        return null;
    }

    @Override
    public Exception reviseUserPhone(UserId userId, Phone phone) {
        return null;
    }

    @Override
    public Exception reduceCash(UserId userId, Cash cash) {
        return null;
    }

    @Override
    public Exception increaseCash(UserId userId, Cash cash) {
        return null;
    }

    @Override
    public Exception reducePrestige(UserId userId, Prestige prestige) {
        return null;
    }

    @Override
    public Exception increasePrestige(UserId userId, Prestige prestige) {
        return null;
    }


}
