package maven.data.AdminData;

import maven.model.task.PublishedTask;

import java.util.List;

public interface AdminDataService {
    public List<PublishedTask> getAllPublishedTask();
}
