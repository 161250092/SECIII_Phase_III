package maven.data.AchievementData;

import maven.data.TableInitializer;
import maven.model.message.Achievement;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTask;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AchievementDataImplTest {
    private AchievementDataImpl impl = new AchievementDataImpl();

    public AchievementDataImplTest() {
        TableInitializer initializer = new TableInitializer();
        initializer.cleanAllTable();
    }

    @Test
    public void init_user_achievement() {
        impl.init_user_achievement(new UserId("wo1"));
        int[] il = {1,2,3,4,5,6,7,8};

        List<Achievement> l = impl.getUserAchievement(new UserId("wo1"));
        assertEquals(il.length, l.size());
        for (int i = 0; i < l.size(); i++){
            assertEquals(il[i], l.get(i).getAchievementId());
            assertEquals(0.0, l.get(i).getProcess(), 0.001);
            assertFalse(l.get(i).isFinished());
            assertFalse(l.get(i).isRewardGet());
        }
    }

    @Test
    public void getUserAchievement() {
    }

    @Test
    public void testAndGetAchievementReward() {
    }

    @Test
    public void updateAchievementCash() {
    }
}