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

        impl.saveRequestorInfo(new Requestor(new UserId("1"), new Username("requestor1"), new Password("123456") , new Email("re1@"), new Phone("1"), new Cash(100.1), new Prestige(10), new TaskNum(10)));
        impl.saveRequestorInfo(new Requestor(new UserId("2"), new Username("requestor2"), new Password("54623") , new Email("re2@"), new Phone("2"), new Cash(200.2), new Prestige(20), new TaskNum(20)));
        impl.saveRequestorInfo(new Requestor(new UserId("3"), new Username("requestor3"), new Password("987") , new Email("re3@"), new Phone("3"), new Cash(300.3), new Prestige(30), new TaskNum(30)));

        impl.saveWorkerInfo(new Worker(new UserId("4"), new Username("worker1"), new Password("123456") , new Email("wo1@"), new Phone("4"), new Cash(400.4), new Prestige(40), new TaskNum(40)));
        impl.saveWorkerInfo(new Worker(new UserId("5"), new Username("worker2"), new Password("123456") , new Email("wo2@"), new Phone("5"), new Cash(500.5), new Prestige(50), new TaskNum(50)));
    }

    @Test
    public void getAllUsernameList() {
        Set<String> ex = new HashSet<>();
        ex.add("requestor1");
        ex.add("requestor2");
        ex.add("requestor3");
        ex.add("worker1");
        ex.add("worker2");

        List<Username> l =  impl.getAllUsernameList();
        for (Username aL : l) {
            assertTrue(ex.contains(aL.value));
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
        assertEquals("worker2", impl.getUserByUserId(new UserId("5")).getUsername().value);
    }

    @Test
    public void reviseUserEmail() {
        assertEquals("re3@", impl.getUserByUserId(new UserId("3")).getEmail().address);
        impl.reviseUserEmail(new UserId("3"), new Email("hhhh@qq.com"));
        assertEquals("hhhh@qq.com", impl.getUserByUserId(new UserId("3")).getEmail().address);

        assertEquals("wo2@", impl.getUserByUserId(new UserId("5")).getEmail().address);
        impl.reviseUserEmail(new UserId("5"), new Email("jsg@qq.com"));
        assertEquals("jsg@qq.com", impl.getUserByUserId(new UserId("5")).getEmail().address);
    }

    @Test
    public void reviseUserPhone() {
        assertEquals("2", impl.getUserByUserId(new UserId("2")).getPhone().number);
        impl.reviseUserPhone(new UserId("2"), new Phone("1889num"));
        assertEquals("1889num", impl.getUserByUserId(new UserId("2")).getPhone().number);

        assertEquals("4", impl.getUserByUserId(new UserId("4")).getPhone().number);
        impl.reviseUserPhone(new UserId("4"), new Phone("kls58965"));
        assertEquals("kls58965", impl.getUserByUserId(new UserId("4")).getPhone().number);
    }

    @Test
    public void reviseCash() {
        assertEquals(100.1, impl.getUserByUserId(new UserId("1")).getCash().value, 0.001);
        impl.reviseCash(new UserId("1"), new Cash(10.1));
        assertEquals(10.1, impl.getUserByUserId(new UserId("1")).getCash().value, 0.001);

        assertEquals(400.4, impl.getUserByUserId(new UserId("4")).getCash().value, 0.001);
        impl.reviseCash(new UserId("4"), new Cash(40.4));
        assertEquals(40.4, impl.getUserByUserId(new UserId("4")).getCash().value, 0.001);
    }

    @Test
    public void revisePrestige() {
        assertEquals(30, impl.getUserByUserId(new UserId("3")).getPrestige().value, 0.001);
        impl.revisePrestige(new UserId("3"), new Prestige(-30));
        assertEquals(-30, impl.getUserByUserId(new UserId("3")).getPrestige().value, 0.001);

        assertEquals(40, impl.getUserByUserId(new UserId("4")).getPrestige().value, 0.001);
        impl.revisePrestige(new UserId("4"), new Prestige(-40));
        assertEquals(-40, impl.getUserByUserId(new UserId("4")).getPrestige().value, 0.001);
    }
}