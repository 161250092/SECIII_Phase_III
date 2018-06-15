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
        impl.init_user_achievement(new UserId("wo1"));
    }

    @Test
    public void getUserAchievement() {
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
    public void testAndGetAchievementReward() {
        int[] il = {1,2,3,4,5,6,7,8};
        for (int i = 0; i < il.length; i++){
            assertFalse(impl.testAndGetAchievementReward(new UserId("wo1"), il[i]));
        }
    }

    @Test
    public void updateAchievementCash() {
        int[] il = {1,2,3,4,5,6,7,8};
        double[] dl = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8};
        for (int i = 0; i < il.length; i++){
            impl.updateAchievementCash(new UserId("wo1"), il[i], dl[i]);
        }
        List<Achievement> l = impl.getUserAchievement(new UserId("wo1"));
        assertEquals(il.length, l.size());
        for (int i = 0; i < l.size(); i++){
            assertEquals(dl[i], l.get(i).getProcess(), 0.001);
        }
    }
}