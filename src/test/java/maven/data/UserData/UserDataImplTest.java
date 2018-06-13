package maven.data.UserData;

import maven.model.primitiveType.*;
import maven.model.user.Requestor;
import maven.model.user.Worker;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDataImplTest {

    UserDataImpl impl = new UserDataImpl();

    public UserDataImplTest(){
        impl.saveRequestorInfo(new Requestor(new UserId("1"), new Username("requestor1"), new Password("123456") , new Email("re1@"), new Phone("1"), new Cash(100.5), new Prestige(60), new TaskNum(15)));
        impl.saveRequestorInfo(new Requestor(new UserId("2"), new Username("requestor2"), new Password("54623") , new Email("re2@"), new Phone("2"), new Cash(100.5), new Prestige(60), new TaskNum(15)));
        impl.saveRequestorInfo(new Requestor(new UserId("3"), new Username("requestor3"), new Password("987") , new Email("re3@"), new Phone("3"), new Cash(100.5), new Prestige(60), new TaskNum(15)));

        impl.saveWorkerInfo(new Worker(new UserId("4"), new Username("worker1"), new Password("123456") , new Email("wo1@"), new Phone("4"), new Cash(100.5), new Prestige(60), new TaskNum(15)));
        impl.saveWorkerInfo(new Worker(new UserId("5"), new Username("worker2"), new Password("123456") , new Email("wo1@"), new Phone("5"), new Cash(100.5), new Prestige(60), new TaskNum(15)));
    }

    @Test
    public void getAllUsernameList() {
        String[] ex = {"requestor1", "requestor2", "requestor3", "worker1", "worker2"};

        List<Username> l =  impl.getAllUsernameList();
        for (int i = 0; i < l.size(); i++){
            assertEquals(ex[i], l.get(i).value);
        }
    }

    @Test
    public void getAllWorker() {
    }

    @Test
    public void getAllRequestor() {
    }

    @Test
    public void saveRequestorInfo() {
    }

    @Test
    public void saveWorkerInfo() {
    }

    @Test
    public void getAllUserIdList() {
    }

    @Test
    public void getUserId() {
    }

    @Test
    public void getUserByUserId() {
    }

    @Test
    public void reviseUserEmail() {
    }

    @Test
    public void reviseUserPhone() {
    }

    @Test
    public void reviseCash() {
    }

    @Test
    public void revisePrestige() {
    }
}