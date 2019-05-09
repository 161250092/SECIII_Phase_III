package maven.data.MarkLabelData.FrameLabelData;

import maven.data.TableInitializer;
import maven.model.label.frameLabel.Frame;
import maven.model.label.frameLabel.FrameLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FrameLabelDataImplTest {
    private FrameLabelDataImpl impl = new FrameLabelDataImpl();

    public FrameLabelDataImplTest() {
        TableInitializer initializer = new TableInitializer();
        initializer.cleanAllTable();

        List<FrameLabel> l = new ArrayList<>();
        List<Frame> s1 = new ArrayList<>();
        s1.add(new Frame(100, 100, 100, 200, "tag0-1"));
        s1.add(new Frame(300, 400, 200, 50, "tag0-2"));
        l.add(new FrameLabel(s1));
        List<Frame> s2 = new ArrayList<>();
        s2.add(new Frame(0, 0, 300, 300, "tag1-1"));
        s2.add(new Frame(500, 500, 400, 100, "tag1-2"));
        l.add(new FrameLabel(s2));

        impl.saveLabelList(new UserId("wo1"), new TaskId("task1"), l);
    }

    @Test
    public void deleteLabel() {
        impl.deleteLabel(new UserId("wo1"), new TaskId("task1"));
        List<FrameLabel> l = impl.getLabelList(new UserId("wo1"), new TaskId("task1"));
        assertEquals(0, l.size());
    }

    @Test
    public void getLabelList() {
        int[][] sxl = {{100, 300}, {0, 500}};
        int[][] syl = {{100, 400}, {0, 500}};
        int[][] wl = {{100,200},{300,400}};
        int[][] hl = {{200,50},{300,100}};
        String[][] sl = {{"tag0-1", "tag0-2"}, {"tag1-1", "tag1-2"}};

        List<FrameLabel> l = impl.getLabelList(new UserId("wo1"), new TaskId("task1"));
        assertEquals(2, l.size());
        for (int i = 0; i < l.size(); i++){
            for (int j = 0; j < l.get(i).getFrameList().size(); j++){
                assertEquals(sxl[i][j], l.get(i).getFrameList().get(j).getStartX());
                assertEquals(syl[i][j], l.get(i).getFrameList().get(j).getStartY());
                assertEquals(wl[i][j], l.get(i).getFrameList().get(j).getWidth());
                assertEquals(hl[i][j], l.get(i).getFrameList().get(j).getHeight());
                assertEquals(sl[i][j], l.get(i).getFrameList().get(j).getTag());
            }
        }
    }
}