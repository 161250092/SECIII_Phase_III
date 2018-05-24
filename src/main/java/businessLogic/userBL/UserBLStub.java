package businessLogic.userBL;

import model.primitiveType.UserId;
import model.primitiveType.Username;

public class UserBLStub implements UserBLService{
    @Override
    public UserId getUserId(Username userName) {
        return new UserId("testUserId");
    }
}
