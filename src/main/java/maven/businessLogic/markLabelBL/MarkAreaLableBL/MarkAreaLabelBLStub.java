package maven.businessLogic.markLabelBL.MarkAreaLableBL;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.AreaLabelSetVO;


public class MarkAreaLabelBLStub implements MarkAreaLabelBLService {

    @Override
    public AreaLabelSetVO getAreaLabelSetVO(TaskId taskId, UserId userId) {
        return null;
    }

    @Override
    public boolean saveAreaLabelSet(TaskId taskId, UserId userId, AreaLabelSetVO areaLabelSetVO, boolean isWorker) {
        return false;
    }
}
