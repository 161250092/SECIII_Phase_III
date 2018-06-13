package maven.data.UserData;

import maven.data.TableInitializer;
import maven.model.primitiveType.*;
import maven.model.user.Requestor;
import maven.model.user.Worker;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class UserDataImplTest {

    UserDataImpl impl = new UserDataImpl();
    TableInitializer initializer = new TableInitializer();

    public UserDataImplTest(){
        initializer.cleanAllTable();

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
        assertEquals(ex.length, l.size());
        for (int i = 0; i < l.size(); i++){
            assertEquals(ex[i], l.get(i).value);
        }
    }

    @Test
    public void getAllWorker() {
        Set<String> ex = new HashSet<>();
        ex.add("4");ex.add("5");

        List<Worker> l = impl.getAllWorker();
        for (Worker aL: l){
            assertTrue(ex.contains(aL.getUserId().value));
        }
    }

    @Test
    public void getAllRequestor() {
        Set<String> ex = new HashSet<>();
        ex.add("1");ex.add("2");ex.add("3");

        List<Requestor> l = impl.getAllRequestor();
        for (Requestor aL: l){
            assertTrue(ex.contains(aL.getUserId().value));
        }
    }

    @Test
    public void getAllUserIdList() {
        Set<String> ex = new HashSet<>();
        ex.add("1");ex.add("2");ex.add("3");ex.add("4");ex.add("5");

        List<UserId> l = impl.getAllUserIdList();
        for (UserId aL : l) {
            assertTrue(ex.contains(aL.value));
        }
    }

    @Test
    public void getUserId() {
        assertEquals("2", impl.getUserId(new Username("requestor2")).value);
        assertEquals("4", impl.getUserId(new Username("worker1")).value);
    }

    @Test
    public void getUserByUserId() {
        assertEquals("requestor2", impl.getUserByUserId(new UserId("2")).getUsername().value);
        assertEquals("worker2", impl.getUserByUserId(new UserId("2")).getUsername().value);
    }

    @Test
    public void reviseUserEmail() {
        impl.reviseUserEmail(new UserId("3"), new Email("hhhh@qq.com"));
        assertEquals("hhhh@qq.com", impl.getUserByUserId(new UserId("2")).getEmail().address);
        impl.reviseUserEmail(new UserId("3"), new Email("jsg@qq.com"));
        //assertEquals("worker2", impl.getUserByUserId(new UserId("2")).getUsername().value);
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