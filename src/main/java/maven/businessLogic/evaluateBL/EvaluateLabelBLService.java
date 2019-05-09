package maven.businessLogic.evaluateBL;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

public interface EvaluateLabelBLService {
    /**
     * 系统为标注评分
     * @param taskId 任务Id
     * @param userId 工人I
     * @return 是否保存评分成功
     */
    boolean evaluateLabel(TaskId taskId, UserId userId);
}
