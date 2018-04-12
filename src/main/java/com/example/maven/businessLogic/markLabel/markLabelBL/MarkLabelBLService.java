package com.example.maven.businessLogic.markLabel.markLabelBL;

/**
 * 为所有标注提供统一的接口
 */
public interface MarkLabelBLService {

    /**
     * 获得任务的图片总数
     * @param taskId 任务ID
     * @return 某次任务的图片总数
     */
    int getTaskImageNumber(String taskId);

    /**
     * 获得标框标注的结果
     * @param taskId 任务ID
     * @param userId 用户ID
     * @param type 标注类型
     * @param imageIndex 图片下标
     * @return 所需图片的标框标注结果(JSON)
     */
    String getLabel(String taskId, String userId, String type, int imageIndex);

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
     * 将该任务设置为已完成
     * @param taskId 任务ID
     * @param userId 用户ID
     * @return 是否设置成功
     */
    boolean setTaskAccomplished(String taskId, String userId);
}
