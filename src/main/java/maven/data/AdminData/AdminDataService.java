package maven.data.AdminData;

import maven.model.task.PublishedTask;

import java.util.List;

public interface AdminDataService {
    /**
     * 获取所有已经发布的任务信息
     * @return
     */
    public List<PublishedTask> getAllPublishedTask();
}
