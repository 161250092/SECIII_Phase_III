package maven.data.AdminData;

import maven.model.task.PublishedTask;
import maven.model.user.Admin;

import java.util.List;

public class AdminDataImpl implements AdminDataService {
    @Override
    public List<PublishedTask> getAllPublishedTask() {
        return null;
    }

    @Override
    public List<Admin> getAllAdmin() {
        return null;
    }

    @Override
    public boolean newAdmin(Admin admin) {
        return false;
    }
}
