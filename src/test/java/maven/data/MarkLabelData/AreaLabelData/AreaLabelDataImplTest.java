package maven.data.MarkLabelData.AreaLabelData;

import maven.data.TableInitializer;
import maven.model.label.areaLabel.Area;
import maven.model.label.areaLabel.AreaLabel;
import maven.model.label.areaLabel.Pixel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AreaLabelDataImplTest {
    private AreaLabelDataImpl impl = new AreaLabelDataImpl();

    public AreaLabelDataImplTest() {
        TableInitializer initializer = new TableInitializer();
        initializer.cleanAllTable();

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

        List<Area> s2 = new ArrayList<>();
        List<Pixel> p3 = new ArrayList<>();
        p3.add(new Pixel(4000, 4000));
        p3.add(new Pixel(5000, 6000));
        p3.add(new Pixel(4000, 7000));
        p3.add(new Pixel(3000, 5000));
        s2.add(new Area("tag1-1", p3));

        l.add(new AreaLabel(s2));

        impl.saveLabelList(new UserId("wo1"), new TaskId("task1"), l);



        List<AreaLabel> l2 = new ArrayList<>();
        List<Area> s21 = new ArrayList<>();

        List<Pixel> p21 = new ArrayList<>();
        p21.add(new Pixel(10, 10));
        p21.add(new Pixel(20, 10));
        p21.add(new Pixel(20, 20));
        p21.add(new Pixel(10, 20));
        p21.add(new Pixel(10, 10));
        s21.add(new Area("tag21-1", p21));
        l2.add(new AreaLabel(s21));

        impl.saveLabelList(new UserId("wo2"), new TaskId("task1"), l2);
    }


    @Test
    public void deleteLabel() {
        impl.deleteLabel(new UserId("wo1"), new TaskId("task1"));
        List<AreaLabel> l1 = impl.getLabelList(new UserId("wo1"), new TaskId("task1"));
        List<AreaLabel> l2 = impl.getLabelList(new UserId("wo2"), new TaskId("task1"));

        assertEquals(0, l1.size());
        assertEquals(1, l2.size());
    }

    @Test
    public void getLabelList() {
        double[][][][] i1 = {
                {
                    {{100,100},{200,100},{200,200},{100,200},{100,100}}, {{400,400},{500,600},{400,600},{400,400}},
                },
                {
                    {{4000,4000},{5000,6000},{4000,7000},{3000,5000}}
                }
        };
        String[][] s1 = {
                {"tag0-1","tag0-2"},
                {"tag1-1"}
        };
        List<AreaLabel> l1 = impl.getLabelList(new UserId("wo1"), new TaskId("task1"));
        for (int i = 0; i < l1.size(); i++){
            for (int j = 0; j < l1.get(i).getAreaList().size(); j++){
                assertEquals(s1[i][j], l1.get(i).getAreaList().get(j).getTag());
                for (int k = 0; k < l1.get(i).getAreaList().get(j).getAreaBorder().size(); k++){
                    assertEquals(i1[i][j][k][0], l1.get(i).getAreaList().get(j).getAreaBorder().get(k).x, 0.001);
                    assertEquals(i1[i][j][k][1], l1.get(i).getAreaList().get(j).getAreaBorder().get(k).y, 0.001);
                }
            }
        }


        double[][][][] i2 = {
                {
                    {{10,10},{20,10},{20,20},{10,20},{10,10}}
                }
        };
        String[][] s2 ={
                {"tag21-1"}
        };
        List<AreaLabel> l2 = impl.getLabelList(new UserId("wo2"), new TaskId("task1"));
        for (int i = 0; i < l2.size(); i++){
            for (int j = 0; j < l2.get(i).getAreaList().size(); j++){
                assertEquals(s2[i][j], l2.get(i).getAreaList().get(j).getTag());
                for (int k = 0; k < l2.get(i).getAreaList().get(j).getAreaBorder().size(); k++){
                    assertEquals(i2[i][j][k][0], l2.get(i).getAreaList().get(j).getAreaBorder().get(k).x, 0.001);
                    assertEquals(i2[i][j][k][1], l2.get(i).getAreaList().get(j).getAreaBorder().get(k).y, 0.001);
                }
            }
        }
    }
}