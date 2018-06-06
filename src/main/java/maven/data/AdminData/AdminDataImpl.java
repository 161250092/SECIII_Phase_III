package maven.data.AdminData;

import maven.data.MySQL.MySQLConnector;
import maven.model.primitiveType.Password;
import maven.model.primitiveType.UserId;
import maven.model.primitiveType.Username;
import maven.model.task.PublishedTask;
import maven.model.user.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDataImpl implements AdminDataService {
    private Connection conn;

    @Override
    public List<PublishedTask> getAllPublishedTask() {
        return null;
    }

    @Override
    public List<Admin> getAllAdmin() {
        conn = new MySQLConnector().getConnection("User");

        List<Admin> admins = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from Admin";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                UserId userId = new UserId(rs.getString("UserId"));
                Username username = new Username(rs.getString("Username"));
                Password password = new Password(rs.getString("Password"));

                Admin admin = new Admin(username,userId,password);
                admins.add(admin);
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return admins;
    }

    @Override
    public boolean newAdmin(Admin admin) {
        conn = new MySQLConnector().getConnection("User");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "insert into Admin values (UserId = ?,Username = ?,Password = ?)";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,admin.getUserId().value);
            stmt.setString(2,admin.getUsername().value);
            stmt.setString(3,admin.getPassword().value);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }


        return result;
    }
}
