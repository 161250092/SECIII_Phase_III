package businessLogic.markLabel.markImageLabelBL;

public interface MarkImageLabelBLService {
    /**
     * 获得任务的图片总数
     * @param taskId 任务ID
     * @return 某次任务的图片总数
     */
    int getTaskImageNumber(String taskId);

    /**
     * 获得整体标注的结果
     * @param imageIndex 图片下标
     * @param taskId 任务ID
     * @param userId 用户ID
     * @return 所需图片的整体标注结果(JSON)
     */
    String getImageLabel(int imageIndex, String taskId, String userId);

    /**
     * 保存整体标注的结果
     * @param imageLabelVOJson 整体标注的结果
     * @return
     */
    String saveImageLabel(String imageLabelVOJson);
}
