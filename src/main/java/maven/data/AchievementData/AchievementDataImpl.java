package maven.data.AchievementData;

import maven.data.MySQL.MySQLConnector;
import maven.model.message.Achievement;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.UserId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AchievementDataImpl implements AchievementDataService {
    private Connection conn;

    @Override
    public boolean init_user_achievement(ArrayList<Achievement> list) {
        conn = new MySQLConnector().getConnection("Achievement");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        for(Achievement a : list){
            try{
                sql = "insert into Achievement values (?,?,?,?,?,?,?,?)";

                stmt = conn.prepareStatement(sql);

                stmt.setString(1,a.getUserId().value);
                stmt.setString(2,a.getAchievementId());
                stmt.setString(3,a.getAchievementName());
                stmt.setString(4,a.getDescription());
                stmt.setDouble(5,a.getProcess());
                stmt.setBoolean(6,a.isFinished());
                stmt.setBoolean(7,a.isRewardGet());
                stmt.setDouble(8,a.getCash().value);

                stmt.executeUpdate();

                stmt.close();
                conn.close();

                result = true;

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public List<Achievement> getUserAchievement(UserId userId) {
        conn = new MySQLConnector().getConnection("Achievement");

        List<Achievement> achievements = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from Achievement where UserId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,userId.value);

            rs = stmt.executeQuery(sql);

            while (rs.next()){
                String achievementId = rs.getString("ID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                double process = rs.getDouble("Process");
                boolean finish = rs.getBoolean("Finish");
                boolean reward = rs.getBoolean("Reward");
                Cash cash = new Cash(rs.getDouble("Cash"));

                Achievement achievement = new Achievement(userId,achievementId,name,description,process,finish,reward,cash);

                achievements.add(achievement);
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return achievements;
    }

    @Override
    public boolean getAchievementCash(UserId userId, String achievementId) {
        conn = new MySQLConnector().getConnection("Achievement");

        boolean result = false;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        boolean r1 = false;
        Achievement achievement = null;
        try {
            sql = "select * from Achievement where UserId = ? and ID = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,userId.value);
            stmt.setString(2,achievementId);

            rs = stmt.executeQuery(sql);

            while(rs.next()){
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                double process = rs.getDouble("Process");
                boolean finish = rs.getBoolean("Finish");
                boolean reward = rs.getBoolean("Reward");
                Cash cash = new Cash(rs.getDouble("Cash"));

                achievement = new Achievement(userId,achievementId,name,description,process,finish,reward,cash);
                r1 = true;
            }

            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        if(r1){
            if((achievement.isFinished())&&(!achievement.isRewardGet())){

                try {
                    sql = "update Achievement set Reward = ? where UserId = ? and ID = ?";

                    stmt = conn.prepareStatement(sql);

                    stmt.setBoolean(1,true);
                    stmt.setString(2,userId.value);
                    stmt.setString(3,achievementId);

                    stmt.executeUpdate();

                    stmt.close();

                    conn.close();

                    result = true;
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

        return result;
    }

    @Override
    public boolean updateAchievementCash(UserId userId, String achievementId, double process) {
        conn = new MySQLConnector().getConnection("Achievement");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            if(process >= 1.0){
                sql = "update Achievement set precess = ? and Finish = ? where UserId = ? and ID = ?";

                stmt = conn.prepareStatement(sql);

                stmt.setDouble(1,process);
                stmt.setBoolean(2,true);
                stmt.setString(3,userId.value);
                stmt.setString(4,achievementId);

                stmt.executeUpdate();

                stmt.close();
                conn.close();

                result = true;
            }
            else{
                sql = "update Achievement set precess = ? where UserId = ? and ID = ?";

                stmt = conn.prepareStatement(sql);

                stmt.setDouble(1,process);
                stmt.setString(2,userId.value);
                stmt.setString(3,achievementId);

                stmt.executeUpdate();

                stmt.close();
                conn.close();

                result = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return result;
    }

}
