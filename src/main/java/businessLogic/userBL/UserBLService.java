package businessLogic.userBL;

import model.primitiveType.UserId;
import model.primitiveType.Username;

public interface UserBLService {
    /**
     * 根据用户名获取用户Id
     * @param userName 用户名
     * @return 用户Id
     */
    UserId getUserId(Username userName);
}
