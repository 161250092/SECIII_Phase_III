package com.example.maven.businessLogic.markLabel.markLabelBL;

import com.example.maven.model.primitiveType.TaskId;
import com.example.maven.model.primitiveType.UserId;
import com.example.maven.model.task.LabelVOSet;

/**
 * 为所有标注提供统一的接口
 */
public interface MarkLabelBLService {

    /**
     * 获取标注集合
     * @param taskId 任务Id
     * @param userId 用户Id
     * @return 标注集LabelVOSet的JSON字符串
     */
    String getLabelVOSet(TaskId taskId, UserId userId);


    /**
     * 保存标注集合
     * @param taskId 任务Id
     * @param userId 用户Id
     * @param labelVOSet 标注集合
     * @param isWorker 判断标注者是否为工人
     * @return 是否保存成功
     */
    boolean saveLabelSet(TaskId taskId, UserId userId, LabelVOSet labelVOSet, boolean isWorker);


    /**
     * 将该任务设置为已完成
     * @param taskId 任务Id
     * @param userId 用户Id
     * @param isWorker 判断标注者是否为工人
     * @return 是否设置成功
     */
    boolean setTaskAccomplished(String taskId, String userId, boolean isWorker);
}
