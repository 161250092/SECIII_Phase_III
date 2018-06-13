package maven.data;

import maven.data.MySQL.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableInitializer {
    private Connection conn;
    
    public void cleanAllTable(){
        String sql;
        PreparedStatement stmt;
        try{
            conn = new MySQLConnector().getConnection("AcceptedTask");
            sql = "delete from AcceptedTask";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            stmt.close();

            conn = new MySQLConnector().getConnection("Achievement");
            sql = "delete from Achievement";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            stmt.close();

            conn = new MySQLConnector().getConnection("Label");
            sql = "delete from iLabel";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            sql = "delete from aLabel";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            sql = "delete from fLabel";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            stmt.close();

            conn = new MySQLConnector().getConnection("Message");
            sql = "delete from AcceptedTaskMessage";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            sql = "delete from AchievementMessage";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            sql = "delete from PublishedTaskMessage";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            sql = "delete from BillMessage";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            sql = "delete from GuyMessage";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            stmt.close();

            conn = new MySQLConnector().getConnection("PublishedTask");
            sql = "delete from PublishedTask";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            sql = "delete from Sample";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            sql = "delete from FileName";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            sql = "delete from Detail";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            sql = "delete from TaskType";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            stmt.close();

            conn = new MySQLConnector().getConnection("User");
            sql = "delete from Admin";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            sql = "delete from Worker";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            sql = "delete from Requestor";
            stmt = conn.prepareStatement(sql);
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
