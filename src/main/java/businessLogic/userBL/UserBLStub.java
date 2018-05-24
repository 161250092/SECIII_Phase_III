package businessLogic.userBL;

public class UserBLStub implements UserBLService{
    @Override
    public String getUserId(String userName) {
        return "00000001";
    }
}
