package maven.data.AdminData;

import maven.model.task.PublishedTask;
import maven.model.user.Admin;

import java.util.List;

public interface AdminDataService {
    /**
     * 获取所有已经发布的任务信息
     * @return
     */
    List<PublishedTask> getAllPublishedTask();

    /**
     *获取管理员信息
     * @return
     */
    Admin getAllAdmin();
}
