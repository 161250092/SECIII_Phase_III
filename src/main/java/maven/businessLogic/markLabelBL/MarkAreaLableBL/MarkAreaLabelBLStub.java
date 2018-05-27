package maven.businessLogic.markLabelBL.MarkAreaLableBL;

import maven.model.label.areaLabel.Area;
import maven.model.label.areaLabel.AreaLabel;
import maven.model.label.areaLabel.Pixel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.AreaLabelSetVO;

import java.util.ArrayList;
import java.util.List;


public class MarkAreaLabelBLStub implements MarkAreaLabelBLService {

    @Override
    public AreaLabelSetVO getAreaLabelSetVO(TaskId taskId, UserId userId) {
        System.out.println("taskId:" + taskId.value);
        System.out.println("userId:" + userId.value);

        List<AreaLabel> l = new ArrayList<>();
        List<Area> s1 = new ArrayList<>();

        List<Pixel> p1 = new ArrayList<>();
        p1.add(new Pixel(100, 100));
        p1.add(new Pixel(200, 100));
        p1.add(new Pixel(200, 200));
        p1.add(new Pixel(100, 200));
        p1.add(new Pixel(100, 100));
        s1.add(new Area("tag0-1", p1));

        List<Pixel> p2 = new ArrayList<>();
        p2.add(new Pixel(400, 400));
        p2.add(new Pixel(500, 600));
        p2.add(new Pixel(400, 600));
        p2.add(new Pixel(400, 400));
        s1.add(new Area("tag0-2", p2));

        l.add(new AreaLabel(s1));
        l.add(new AreaLabel());
        l.add(new AreaLabel());

        List<String> fl = new ArrayList<>();
        fl.add("test11.jpg");
        fl.add("test8.jpg");
        fl.add("test9.jpg");

        return new AreaLabelSetVO(3, l, fl);
    }

    @Override
    public boolean saveAreaLabelSet(TaskId taskId, UserId userId, AreaLabelSetVO areaLabelSetVO, boolean isWorker) {
        return true;
    }
}
