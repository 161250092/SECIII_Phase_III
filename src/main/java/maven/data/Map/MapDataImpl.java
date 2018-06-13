package maven.data.Map;

import maven.data.MySQL.MySQLConnector;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.Prestige;
import maven.model.task.TaskType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class MapDataImpl implements MapDataService{
    private Connection conn;

    @Override
    public Map<TaskType,Cash> getCashTaskType() {
        conn = new MySQLConnector().getConnection("Map");

        Map<TaskType,Cash> result = new HashMap<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from TypeCash";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while(rs.next()){
                TaskType taskType = TaskType.valueOf(rs.getString("TaskType"));
                Cash cash = new Cash(rs.getDouble("Cash"));
                result.put(taskType,cash);
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Map<TaskType,Prestige> getPrestigeTaskType() {
        conn = new MySQLConnector().getConnection("Map");

        Map<TaskType,Prestige> result = new HashMap<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from TypePrestige";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while(rs.next()){
                TaskType taskType = TaskType.valueOf(rs.getString("Type"));
                Prestige prestige = new Prestige(rs.getDouble("Prestige"));
                result.put(taskType,prestige);
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Prestige getMaxPrestige(){
        conn = new MySQLConnector().getConnection("Map");

        Prestige prestige = null;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from MaxPrestige";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while(rs.next()){
                prestige = new Prestige(rs.getDouble("Prestige"));

            }


            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }


        return  prestige;
    }
}
