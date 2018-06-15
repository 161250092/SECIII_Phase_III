package maven.data.AchievementData;

import maven.data.TableInitializer;
import org.junit.Test;

import static org.junit.Assert.*;

public class AchievementDataImplTest {

    private AchievementDataImpl impl = new AchievementDataImpl();

    public AchievementDataImplTest(){
        TableInitializer initializer = new TableInitializer();
        initializer.cleanAllTable();


    }

    @Test
    public void init_user_achievement() {
    }

    @Test
    public void getUserAchievement() {
    }

    @Test
    public void getAchievementCash() {
    }

    @Test
    public void updateAchievementCash() {
    }
}