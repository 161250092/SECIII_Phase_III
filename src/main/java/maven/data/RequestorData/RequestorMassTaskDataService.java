package maven.data.RequestorData;

import maven.model.massTask.MassTaskDetail;
import maven.model.primitiveType.TaskId;

public interface RequestorMassTaskDataService {

    /**
     * 保存大任务信息
     * @param massTaskDetail 任务信息
     * @return 是否保存
     */
    boolean saveMassTaskDetail(MassTaskDetail massTaskDetail);

    /**
     * 获取大任务信息
     * @param taskId 任务ID
     * @return 任务信息
     */
    MassTaskDetail getMassTaskDetail(TaskId taskId);

    /**
     * 判断是否是大任务
     * @param taskId 任务ID
     * @return 是否是大任务
     */
    boolean isMassTask(TaskId taskId);

}
