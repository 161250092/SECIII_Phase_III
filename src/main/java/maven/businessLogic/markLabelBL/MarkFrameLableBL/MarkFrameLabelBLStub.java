package maven.businessLogic.markLabelBL.MarkFrameLableBL;

import maven.model.label.frameLabel.Frame;
import maven.model.label.frameLabel.FrameLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.FrameLabelSetVO;

import java.util.ArrayList;
import java.util.List;

public class MarkFrameLabelBLStub implements MarkFrameLabelBLService {
    @Override
    public FrameLabelSetVO getFrameLabelSetVO(TaskId taskId, UserId userId) {
        System.out.println("taskId:" + taskId.value);
        System.out.println("userId:" + userId.value);

        List<FrameLabel> l = new ArrayList<>();
        List<Frame> s1 = new ArrayList<>();
        s1.add(new Frame(100, 100, 100, 200, "tag0-1"));
        s1.add(new Frame(300, 400, 200, 50, "tag0-2"));
        l.add(new FrameLabel(s1));
        l.add(new FrameLabel());
        l.add(new FrameLabel());


        List<String> fl = new ArrayList<>();
        fl.add("test4.jpg");
        fl.add("test5.jpg");
        fl.add("test6.jpg");

        return new FrameLabelSetVO(3, l, fl);
    }

    @Override
    public boolean saveFrameLabelSet(TaskId taskId, UserId userId, FrameLabelSetVO labelSetVO) {
        return true;
    }
}
