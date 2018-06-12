package maven.model.task;

import maven.model.label.Label;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

import java.util.List;

/**
 * 发布者提供的标注样本类
 */
public class Sample {
    //任务Id
    private TaskId taskId;
    //样本内图片总数
    private int imageNum;
    //样本内图片 在原本的任务图片集合内的下标数组
    private List<Integer> imageIndexList;

    public int getNumber() {
        return imageNum;
    }

    public List<Integer> getImageIndexList() {
        return imageIndexList;
    }

    public Sample(TaskId taskId,int imageNum, List<Integer> imageIndexList) {
        this.taskId = taskId;
        this.imageNum = imageNum;
        this.imageIndexList = imageIndexList;
    }
}
