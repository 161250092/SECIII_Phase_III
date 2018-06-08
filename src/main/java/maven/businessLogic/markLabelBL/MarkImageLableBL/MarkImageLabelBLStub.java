package maven.businessLogic.markLabelBL.MarkImageLableBL;

import maven.model.label.ImageLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.ImageLabelSetVO;

import java.util.ArrayList;
import java.util.List;

public class MarkImageLabelBLStub implements MarkImageLabelBLService {
    @Override
    public ImageLabelSetVO getImageLabelSetVO(TaskId taskId, UserId userId) {
        System.out.println("taskId:" + taskId.value);
        System.out.println("userId:" + userId.value);

        List<ImageLabel> l = new ArrayList<>();
        List<String> s1 = new ArrayList<>();
        s1.add("tag0-1");
        s1.add("tag0-2");
        l.add(new ImageLabel(s1));
        l.add(new ImageLabel());
        l.add(new ImageLabel());

        List<String> fl = new ArrayList<>();
        fl.add("test1.jpg");
        fl.add("test2.jpg");
        fl.add("test3.jpg");

        return new ImageLabelSetVO(3, l, fl);
    }

    @Override
    public boolean saveImageLabelSet(TaskId taskId, UserId userId, ImageLabelSetVO labelSetVO) {
        return true;
    }
}
