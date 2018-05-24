package businessLogic.markLabel.markFrameLabelBL;

public interface MarkFrameLabelBLService {
    /**
     * 获得任务的图片总数
     * @param taskId 任务ID
     * @return 某次任务的图片总数
     */
    int getTaskImageNumber(String taskId);

    /**
     * 获得标框标注的结果
     * @param imageIndex 图片下标
     * @param taskId 任务ID
     * @param userId 用户ID
     * @return 所需图片的标框标注结果(JSON)
     */
    String getFrameLabel(int imageIndex, String taskId, String userId);

    /**
     * 保存标框标注的结果
     * @param frameLabelVOJson 标框标注的结果
     * @return
     */
    String saveFrameLabel(String frameLabelVOJson);
}
