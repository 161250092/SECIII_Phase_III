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
}
