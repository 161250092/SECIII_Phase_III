package maven.data.AchievementData;

import maven.data.MySQL.MySQLConnector;
import maven.model.message.Achievement;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.UserId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AchievementDataImpl implements AchievementDataService {
    private Connection conn;

    private static int[] achievementIdList = {1, 2 ,3, 4, 5, 6, 7, 8};
    private static int NAME_INDEX = 0;
    private static int DESCRIPTION_INDEX = 1;
    private static int CASH_INDEX = 2;

    @Override
    public boolean init_user_achievement(UserId userId){
        conn = new MySQLConnector().getConnection("Achievement");

        PreparedStatement stmt;
        String sql;
        boolean result = false;
        for(int achievementId : achievementIdList){
            try{
                sql = "insert into UserAchievement values (?,?,?,?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1,userId.value);
                stmt.setInt(2, achievementId);
                stmt.setDouble(3, 0);
                stmt.setBoolean(4, false);
                stmt.setBoolean(5, false);
                stmt.executeUpdate();
                stmt.close();
                result = true;
            }catch (Exception e){
                e.printStackTrace();
                result = false;
                break;
            }
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
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

        List<List<String>> achievementCharacteristic = new ArrayList<>();
        try{
            sql = "select * from AchievementCharacteristic order by ID";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                List<String> temp = new ArrayList<>();
                temp.add(rs.getString("Name"));
                temp.add(rs.getString("Description"));
                temp.add(String.valueOf(rs.getDouble("Cash")));
                achievementCharacteristic.add(temp);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try{
            sql = "select * from UserAchievement where UserId = ? order by ID";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userId.value);
            rs = stmt.executeQuery();
            while (rs.next()){
                int achievementId = rs.getInt("ID");
                double process = rs.getDouble("rateOfProgress");
                boolean finish = rs.getBoolean("Finish");
                boolean reward = rs.getBoolean("Reward");

                String name = achievementCharacteristic.get(achievementId - 1).get(NAME_INDEX);
                String description = achievementCharacteristic.get(achievementId - 1).get(DESCRIPTION_INDEX);
                double cash = Double.parseDouble(achievementCharacteristic.get(achievementId - 1).get(CASH_INDEX));

                Achievement achievement = new Achievement(userId, achievementId, name,description,process, finish,reward,new Cash(cash));
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
    public boolean testAndGetAchievementReward(UserId userId, int achievementId) {
        conn = new MySQLConnector().getConnection("Achievement");

        boolean result = false;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        boolean finish = false, reward = false;
        Achievement achievement = null;
        try {
            sql = "select Finish, Reward from UserAchievement where UserId = ? and ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId.value);
            stmt.setInt(2, achievementId);

            rs = stmt.executeQuery();
            while(rs.next()){
                finish = rs.getBoolean("Finish");
                reward = rs.getBoolean("Reward");
            }
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        if(finish && !reward){
            try {
                sql = "update UserAchievement set Reward = ? where UserId = ? and ID = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setBoolean(1, true);
                stmt.setString(2, userId.value);
                stmt.setInt(3, achievementId);
                stmt.executeUpdate();

                stmt.close();
                conn.close();
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }else {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean updateAchievementCash(UserId userId, int achievementId, double process) {
        conn = new MySQLConnector().getConnection("Achievement");

        PreparedStatement stmt;
        String sql;
        try{
            boolean finish = process >= 1.0;

            sql = "update UserAchievement set rateOfProgress = ?, Finish = ? where UserId = ? and ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1,process);
            stmt.setBoolean(2,finish);
            stmt.setString(3,userId.value);
            stmt.setInt(4,achievementId);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
