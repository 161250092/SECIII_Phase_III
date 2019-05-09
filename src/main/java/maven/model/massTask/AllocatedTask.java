package maven.model.massTask;

import maven.model.primitiveType.Cash;
import maven.model.primitiveType.UserId;

/**
 * 分配给工人的任务详情
 */
public class AllocatedTask {
    //工人ID
    private UserId workerId;
    //给该工人所分配的图片单价
    private Cash imageUnitPrice;
    //给该工人的图片总数
    private ImageNum imageNum;

    public AllocatedTask(UserId workerId, Cash imageUnitPrice, ImageNum imageNum) {
        this.workerId = workerId;
        this.imageUnitPrice = imageUnitPrice;
        this.imageNum = imageNum;
    }

    public UserId getWorkerId() {
        return workerId;
    }

    public Cash getImageUnitPrice() {
        return imageUnitPrice;
    }

    public ImageNum getImageNum() {
        return imageNum;
    }
}
