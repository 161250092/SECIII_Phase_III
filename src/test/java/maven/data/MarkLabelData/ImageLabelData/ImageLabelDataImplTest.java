package maven.data.MarkLabelData.ImageLabelData;

import maven.data.TableInitializer;
import maven.model.label.ImageLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ImageLabelDataImplTest {
    private ImageLabelDataImpl impl = new ImageLabelDataImpl();

    public ImageLabelDataImplTest(){
        TableInitializer initializer = new TableInitializer();
        initializer.cleanAllTable();

        List<ImageLabel> l = new ArrayList<>();
        List<String> tagList = new ArrayList<>();
        tagList.add("tag0-1");tagList.add("tag0-2");tagList.add("tag0-3");
        List<String> sl2 = new ArrayList<>();
        sl2.add("tag1-1");sl2.add("tag1-2");sl2.add("tag1-3");sl2.add("tag1-4");
        l.add(new ImageLabel(tagList));
        l.add(new ImageLabel(sl2));
        impl.saveLabelList(new UserId("wo1"), new TaskId("task1"), l);

        List<ImageLabel> l2 = new ArrayList<>();
        List<String> sl3 = new ArrayList<>();
        sl3.add("tag3-1");sl3.add("tag3-2");sl3.add("tag3-3");
        l2.add(new ImageLabel(sl3));
        impl.saveLabelList(new UserId("wo2"), new TaskId("task1"), l2);
    }

    @Test
    public void deleteLabel() {
        impl.deleteLabel(new UserId("wo1"), new TaskId("task1"));

        List<ImageLabel> l = impl.getLabelList(new UserId("wo1"), new TaskId("task1"));
        assertEquals(0, l.size());
    }

    @Test
    public void getLabelList() {
        List<ImageLabel> l = impl.getLabelList(new UserId("wo1"), new TaskId("task1"));
        String[][] t = {
                {"tag0-1", "tag0-2", "tag0-3"},
                {"tag1-1", "tag1-2", "tag1-3", "tag1-4"}
        };
        assertEquals(2, l.size());
        for (int i = 0; i < l.size(); i++){
            for (int j = 0; j < l.get(i).getTagList().size(); j++){
                assertEquals(t[i][j], l.get(i).getTagList().get(j));
            }
        }
    }
}