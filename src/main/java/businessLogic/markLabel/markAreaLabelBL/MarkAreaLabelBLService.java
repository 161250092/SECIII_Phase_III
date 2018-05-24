package businessLogic.markLabel.markAreaLabelBL;

public interface MarkAreaLabelBLService {
    /**
     * 获得任务的图片总数
     * @param taskId 任务ID
     * @return 某次任务的图片总数
     */
    int getTaskImageNumber(String taskId);

    /**
     * 获得区域标注的结果
     * @param imageIndex 图片下标
     * @param taskId 任务ID
     * @param userId 用户ID
     * @return 所需图片的区域标注结果(JSON)
     */
    String getAreaLabel(int imageIndex, String taskId, String userId);

    /**
     * 保存区域标注的结果
     * @param areaLabelVOJson 区域标注的结果
     * @return
     */
    String saveAreaLabel(String areaLabelVOJson);
}
