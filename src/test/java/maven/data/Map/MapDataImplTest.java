package maven.data.Map;

import maven.data.TableInitializer;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.Prestige;
import maven.model.task.TaskType;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class MapDataImplTest {

    MapDataImpl impl = new MapDataImpl();

    public MapDataImplTest(){
        TableInitializer initializer = new TableInitializer();
        initializer.cleanAllTable();
    }

    @Test
    public void getCashTaskType() {
        Map<TaskType,Cash> map = impl.getCashTaskType();

        assertEquals(0.1, map.get(TaskType.ORDINARY_LEVEL_LABEL_REQUIRED).value, 0.001);
        assertEquals(0.3, map.get(TaskType.HIGH_LEVEL_LABEL_REQUIRED).value, 0.001);
        assertEquals(0.5, map.get(TaskType.VERY_HIGH_LEVEL_LABEL_REQUIRED).value, 0.001);
    }

    @Test
    public void getPrestigeTaskType() {
        Map<TaskType,Prestige> map = impl.getPrestigeTaskType();

        assertEquals(60, map.get(TaskType.ORDINARY_LEVEL_LABEL_REQUIRED).value, 0.001);
        assertEquals(80, map.get(TaskType.HIGH_LEVEL_LABEL_REQUIRED).value, 0.001);
        assertEquals(90, map.get(TaskType.VERY_HIGH_LEVEL_LABEL_REQUIRED).value, 0.001);
    }

    @Test
    public void getMaxPrestige() {
        assertEquals(100, impl.getMaxPrestige().value, 0.001);
    }
}