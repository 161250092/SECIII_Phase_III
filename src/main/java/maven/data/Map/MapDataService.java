package maven.data.Map;

import java.util.Map;

public interface MapDataService {
    /**
     * 获取金额任务等级映射
     * @return 金额 任务等级映射
     */
    Map getCashTaskType();

    /**
     * 获取任务等级信誉值映射
     * @return 信誉 任务等级映射
     */
    Map getPrestigeTaskType();
}
