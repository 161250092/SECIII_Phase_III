package maven.data.UserData;

import maven.model.primitiveType.*;
import maven.model.user.User;
import maven.model.user.UserLevel;

import java.util.List;

public class UserDataImpl implements UserDataService{
    @Override
    public List<UserId> getAllUserIdList() {
        return null;
    }

    @Override
    public UserId getUserId(Username username) {
        return null;
    }

    @Override
    public User getUserByUserId(UserId userId) {
        return null;
    }

    //@Override
    public User getUserInfo(UserId userId) {
        return null;
    }

    @Override
    public boolean reviseUserEmail(UserId userId, Email email) {
        return false;
    }

    @Override
    public boolean reviseUserPhone(UserId userId, Phone phone) {
        return false;
    }

    @Override
    public boolean reviseCash(UserId userId, Cash cash) {
        return false;
    }

    @Override
    public boolean revisePrestige(UserId userId, UserLevel userLevel, Prestige prestige) {
        return false;
    }
}
