package maven.data.AdminData;

import maven.model.primitiveType.Password;
import maven.model.primitiveType.UserId;
import maven.model.primitiveType.Username;
import maven.model.user.Admin;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AdminDataImplTest {

    AdminDataImpl imp = new AdminDataImpl();

    @Test
    public void getAllPublishedTask() {
    }

    @Test
    public void getAllAdmin() {
        imp.newAdmin(new Admin(new Username("admin1"), new UserId("1"), new Password("123456")));
        imp.newAdmin(new Admin(new Username("admin2"), new UserId("2"), new Password("987654")));

        List<Admin> l = imp.getAllAdmin();

        assertEquals("admin1", l.get(0).getUsername().value);
        assertEquals("1", l.get(0).getUserId().value);
        assertEquals("123456", l.get(0).getPassword().value);
        assertEquals("admin2", l.get(1).getUsername().value);
        assertEquals("2", l.get(1).getUserId().value);
        assertEquals("987654", l.get(1).getPassword().value);
    }
}