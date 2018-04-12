package com.example.maven.businessLogic.markLabel.markLabelBL;

/**
 * 为所有标注提供统一的接口
 */
public interface MarkLabelBL {

    /**
     * 保存一次标注的结果
     * 根据传入的参数决定标注类型
     * @param taskId 任务ID
     * @param userId 用户ID
     * @param type　标注类型
     * @param labelVOJson 标注的内容
     * @return 是否保存成功
     */
    boolean saveLabel(String taskId, String userId, String type, String labelVOJson);

    /**
     * 提交一次完成的任务
     * @param userId 用户ID
     * @param taskId 任务
     * @return 是否提交成功
     */
    boolean submitAccomplishedTask(String userId, String taskId);
}
