package maven.businessLogic.registerBL;

import maven.data.UserData.UserDataImpl;
import maven.data.UserData.UserDataService;
import maven.exception.RegisterException.RegisterSuccessException;
import maven.exception.util.FailureException;
import maven.model.primitiveType.*;
import maven.model.user.Requestor;
import maven.model.user.Worker;

import java.util.List;


public class RegisterBLImpl implements RegisterBLService {

    private UserDataService userDataService;

    public RegisterBLImpl(){
        userDataService = new UserDataImpl();
    }

    @Override
    public boolean isUsernameExist(Username username) {
        List<Username> usernameList = userDataService.getAllUsernameList();
        for(Username name : usernameList){
            if(username.value.equals(name.value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Exception registerRequesotr(Username username, Password password, Email email, Phone phone) {
        if(isUsernameExist(username))
            return new FailureException();
        List<UserId> userIdList = userDataService.getAllUserIdList();
        //获取已有的用户数目
        int numberOfUser =  userIdList.size();
        //将id改为 00000000 的形式，在numberOfUser不足八位时 在前面补零，得到id
        String id = String.format("%08d", numberOfUser);
        UserId userId = new UserId(id);
        Requestor requestor = new Requestor(userId, username, password, email, phone, new Cash(0), new Prestige(60), new TaskNum(5));
        if(userDataService.saveRequestorInfo(requestor))
            return new RegisterSuccessException(id);
        else
            return new FailureException();
    }

    @Override
    public Exception registerWorker(Username username, Password password, Email email, Phone phone) {
        if(isUsernameExist(username))
            return new FailureException();
        List<UserId> userIdList = userDataService.getAllUserIdList();
        //获取已有的用户数目
        int numberOfUser =  userIdList.size();
        //将id改为 00000000 的形式，在numberOfUser不足八位时 在前面补零，得到id
        String id = String.format("%08d", numberOfUser);
        UserId userId = new UserId(id);
        Worker worker = new Worker(userId, username, password, email, phone, new Cash(0), new Prestige(60), new TaskNum(5));
        if(userDataService.saveWorkerInfo(worker))
            return new RegisterSuccessException(id);
        else
            return new FailureException();
    }


}
