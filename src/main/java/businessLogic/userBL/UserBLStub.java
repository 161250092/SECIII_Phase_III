package businessLogic.userBL;

import model.primitiveType.*;
import model.user.User;

public class UserBLStub implements UserBLService{
    @Override
    public UserId getUserId(Username userName) {
        return new UserId("testUserId");
    }

    @Override
    public User getUserInfo(Username username) {
        return null;
    }

    @Override
    public Exception reviseUserEmail(Username username, Email email) {
        return null;
    }

    @Override
    public Exception reviseUserPhone(Username username, Phone phone) {
        return null;
    }

    @Override
    public Exception reduceCash(Username username, Cash cash) {
        return null;
    }

    @Override
    public Exception increaseCash(Username username, Cash cash) {
        return null;
    }

    @Override
    public Exception reducePrestige(Username username, Prestige prestige) {
        return null;
    }

    @Override
    public Exception increasePrestige(Username username, Prestige prestige) {
        return null;
    }
}
