package com.example.maven.businessLogic.markLabel.markFrameLabelBL;

public interface MarkFrameLabelBLService {
    /**
     * 保存标框标注的结果
     * @param frameLabelJson 标框标注的结果
     * @return
     */
    String saveFrameLabel(String frameLabelJson);

    /**
     * 获得标框标注的结果
     * @param userId 用户ID
     * @param imageId 图片ID
     * @return 标框标注的结果Json
     */
    String getFrameLabel(String userId, String imageId);


    /**
     * @param taskId
     * @return 某次任务的图片总数
     */
    int getTaskImageNumber(String taskId);


    /**
     * @param imageIndex 图片下标
     * @param taskId 任务Id
     * @param userId 用户Id
     * @return 图片源信息+已有标注 JSON
     */

    String getImageInfo(int imageIndex, String taskId, String userId);
}
