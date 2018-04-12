package com.example.maven.businessLogic.markLabel.imageBL;

/**
 * 为所有标注提供统一的接口
 */

public interface ImageBLService {

    /**
     * 获得任务的图片总数
     * @param taskId 任务ID
     * @return 某次任务的图片总数
     */
    int getTaskImageNumber(String taskId);

    /**
     * 从后台获得图片和标注的信息
     * 标注类型可根据taskId判断
     * @param imageIndex 图片下标
     * @param taskId 任务ID
     * @param userId 用户ID
     * @return 所需图片的标注结果(JSON)
     */
    String getImageAndLabelnfo(int imageIndex, String taskId, String userId);

    /**
     * 保存一次标注的结果
     * 根据传入的参数决定标注类型
     * @param taskId 任务
     * @param userId 用户ID
     * @param type　标注类型
     * @param labelVOJson 标注的内容
     * @return 是否保存成功
     */
    boolean saveLabel(String taskId, String userId, String type, String labelVOJson);

    /**
     * 提交一次完成的任务
     * @param userId
     * @param taskId 任务
     * @return 是否提交成功
     */
    boolean submitAccomplishedTask(String userId, String taskId);

}
