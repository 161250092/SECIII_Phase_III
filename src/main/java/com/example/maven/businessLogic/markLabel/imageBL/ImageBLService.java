package com.example.maven.businessLogic.markLabel.imageBL;

/**
 * 获取任务中包含的图片信息
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

}
