package businessLogic.markLabelBL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.label.ImageLabel;
import model.label.Label;
import model.primitiveType.Filename;
import model.primitiveType.TaskId;
import model.primitiveType.UserId;
import model.vo.LabelSetVO;

import java.util.ArrayList;
import java.util.List;

public class MarkImageLabelBLStub implements MarkLabelBLService {
    @Override
    public LabelSetVO getLabelSetVO(TaskId taskId, UserId userId) {
        System.out.println("taskId:" + taskId.value);
        System.out.println("userId:" + userId.value);

        List<Label> l = new ArrayList<>();
        l.add(new ImageLabel());
        l.add(new ImageLabel());
        l.add(new ImageLabel());

        List<Filename> fl = new ArrayList<>();
        fl.add(new Filename("test1.jpg"));
        fl.add(new Filename("test2.jpg"));
        fl.add(new Filename("test3.jpg"));

        return new LabelSetVO(3, l, fl);
    }

    @Override
    public boolean saveLabelSet(TaskId taskId, UserId userId, LabelSetVO labelSetVO, boolean isWorker) {
        return true;
    }

    @Override
    public boolean setTaskAccomplished(TaskId taskId, UserId userId, boolean isWorker) {
        return true;
    }
}
