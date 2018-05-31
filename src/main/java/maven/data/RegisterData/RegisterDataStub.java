package maven.data.RegisterData;

import maven.model.primitiveType.Username;
import maven.model.user.User;

import java.util.List;

public class RegisterDataStub implements RegisterDataService {
    /**
     *获取所有信息
     * @return username的list：管理员、用户、工人
     */
    public List<String> getAllUser(){
        return null;
    }

    /**
     * 注册：保存用户信息
     * @param user：用户信息
     * @return
     */
    public boolean Register(User user){
        return true;
    }

    @Override
    public List<Username> getAllUsernameList() {
        return null;
    }

    @Override
    public boolean saveUserInfo(User user) {
        return false;
    }
}
