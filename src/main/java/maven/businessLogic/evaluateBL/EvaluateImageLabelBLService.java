package maven.businessLogic.evaluateBL;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

public interface EvaluateImageLabelBLService {
    public double evaluate(TaskId taskId, UserId userId);
}
