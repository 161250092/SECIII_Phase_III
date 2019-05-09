package maven.data.AdminData;

import maven.model.task.PublishedTask;
import maven.model.user.Admin;

import java.util.List;

public interface AdminDataService {
    /**
     * 获取所有已经发布的任务信息
     * @return 任务信息
     */
    List<PublishedTask> getAllPublishedTask();

    /**
     *获取管理员信息
     * @return 管理员信息
     */
    List<Admin> getAllAdmin();

    /**
     * 新建管理员信息
     * @param admin 管理员信息
     * @return 是否新建
     */
    boolean newAdmin(Admin admin);
}
