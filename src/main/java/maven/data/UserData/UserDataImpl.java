package maven.data.UserData;

import maven.data.MySQL.MySQLConnector;
import maven.model.primitiveType.*;
import maven.model.user.Requestor;
import maven.model.user.User;
import maven.model.user.UserLevel;
import maven.model.user.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class UserDataImpl implements UserDataService{

    private Connection conn = new MySQLConnector().getConnection("User");

    @Override
    public List<Username> getAllUsernameList() {
        List<Username> userName = new ArrayList<Username>();

        PreparedStatement stmt = null;
        String sql = null;
        ResultSet rs = null;
        try{

            sql = "select Username from Admin";
            stmt = conn.prepareStatement(sql);
            rs= stmt.executeQuery(sql);

            while(rs.next()){
                Username temp = new Username(rs.getString("Username"));
                userName.add(temp);
            }


            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        try{

            sql = "select Username from Requstor";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                Username temp = new Username(rs.getString("Username"));
                userName.add(temp);
            }

            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            sql = "select Username from Workrer";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                Username temp = new Username(rs.getString("Username"));
                userName.add(temp);
            }

            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return userName;
    }

    @Override
    public List<Worker> getAllWorker() {
        List<Worker> workers = new ArrayList<Worker>();

        PreparedStatement stmt = null;
        String sql = null;
        ResultSet rs = null;

        try{
            sql = "select * from Worker";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                UserId userId = new UserId(rs.getString("UserId"));
                Username username = new Username(rs.getString("Username"));
                Password password = new Password(rs.getString("Password"));
                Email email = new Email(rs.getString("Password"));
                Phone phone = new Phone(rs.getString("Phone"));
                Cash cash = new Cash(rs.getDouble("Cash"));
                Prestige prestige = new Prestige(rs.getDouble("Prestige"));
                TaskNum taskNum = new TaskNum(rs.getInt("TaskNum"));

                Worker worker = new Worker(userId,username,password,email,phone,cash,prestige,taskNum);
                workers.add(worker);
            }

            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return workers;
    }

    @Override
    public List<Requestor> getAllRequestor() {
        List<Requestor> requestors = new ArrayList<Requestor>();

        PreparedStatement stmt = null;
        String sql = null;
        ResultSet rs = null;

        try{
            sql = "select * from Requestor";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                UserId userId = new UserId(rs.getString("UserId"));
                Username username = new Username(rs.getString("Username"));
                Password password = new Password(rs.getString("Password"));
                Email email = new Email(rs.getString("Email"));
                Phone phone = new Phone(rs.getString("Phone"));
                Cash cash = new Cash(rs.getDouble("Cash"));
                Prestige prestige = new Prestige(rs.getDouble("Prestige"));
                TaskNum taskNum = new TaskNum(rs.getInt("TaskNum"));

                Requestor requestor = new Requestor(userId,username,password,email,phone,cash,prestige,taskNum);
                requestors.add(requestor);
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return requestors;
    }

    @Override
    public boolean saveRequestorInfo(Requestor requestor) {
        boolean result = false;

        PreparedStatement stmt = null;
        String sql = null;

        try{
            sql = "insert into Requestor values (?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,requestor.getUserId().value);
            stmt.setString(2,requestor.getUsername().value);
            stmt.setString(3,requestor.getPassword().value);
            stmt.setString(4,requestor.getEmail().address);
            stmt.setString(5,requestor.getPassword().value);
            stmt.setDouble(6,requestor.getCash().value);
            stmt.setDouble(7,requestor.getPrestige().value);
            stmt.setInt(8,requestor.getMaxPublishedTaskNum().value);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean saveWorkerInfo(Worker worker) {
        boolean result = false;

        PreparedStatement stmt = null;
        String sql = null;

        try{
            sql = "insert into Worker";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,worker.getUserId().value);
            stmt.setString(2,worker.getUsername().value);
            stmt.setString(3,worker.getPassword().value);
            stmt.setString(4,worker.getEmail().address);
            stmt.setString(5,worker.getPassword().value);
            stmt.setDouble(6,worker.getCash().value);
            stmt.setDouble(7,worker.getPrestige().value);
            stmt.setInt(8,worker.getMaxAcceptedTaskNum().value);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }


    @Override
    public List<UserId> getAllUserIdList() {
        List<UserId> userIds = new ArrayList<UserId>();

        PreparedStatement stmt = null;
        String sql = null;
        ResultSet rs = null;

        try{
            sql = "select UserId from Admin";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                UserId userId = new UserId(rs.getString("UserId"));
                userIds.add(userId);
            }

            sql = "select UserId from Worker";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                UserId userId = new UserId(rs.getString("UserId"));
                userIds.add(userId);
            }

            sql = "select UserId from Requestor";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                UserId userId = new UserId(rs.getString("UserId"));
                userIds.add(userId);
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return userIds;
    }

    @Override
    public UserId getUserId(Username username) {
        UserId result = null;

        PreparedStatement stmt = null;
        String sql = null;
        ResultSet rs = null;

        try{
            sql = "select UserId from Worker where Username = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,username.value);

            rs = stmt.executeQuery(sql);

            while(rs.next()){
                result = new UserId(rs.getString("UserId"));
            }

            sql = "select UserId from Requestor where Username = ?";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            stmt.setString(1,username.value);

            while(rs.next()){
                result = new UserId(rs.getString("UserId"));
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public User getUserByUserId(UserId userId) {
        User result = null;

        PreparedStatement stmt = null;
        String sql = null;
        ResultSet rs = null;

        try{
            sql = "select * from Worker where UserId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,userId.value);
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                Username username = new Username(rs.getString("Username"));
                Password password = new Password(rs.getString("Password"));
                Email email = new Email(rs.getString("Password"));
                Phone phone = new Phone(rs.getString("Phone"));
                Cash cash = new Cash(rs.getDouble("Cash"));
                Prestige prestige = new Prestige(rs.getDouble("Prestige"));
                TaskNum taskNum = new TaskNum(rs.getInt("TaskNum"));

                result = new Worker(userId,username,password,email,phone,cash,prestige,taskNum);
            }

            sql = "select * from Requestor where UserId = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userId.value);
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                Username username = new Username(rs.getString("Username"));
                Password password = new Password(rs.getString("Password"));
                Email email = new Email(rs.getString("Password"));
                Phone phone = new Phone(rs.getString("Phone"));
                Cash cash = new Cash(rs.getDouble("Cash"));
                Prestige prestige = new Prestige(rs.getDouble("Prestige"));
                TaskNum taskNum = new TaskNum(rs.getInt("TaskNum"));

                result = new Requestor(userId,username,password,email,phone,cash,prestige,taskNum);
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean reviseUserEmail(UserId userId, Email email) {
        boolean result = false;

        PreparedStatement stmt = null;
        String sql = null;

        try{
            sql = "revise Email = ? from  Worker where UserId = ?";
            stmt = conn.prepareStatement(sql);

        }catch (Exception e){
            e.printStackTrace();
        }


        return result;
    }

    @Override
    public boolean reviseUserPhone(UserId userId, Phone phone) {
        return false;
    }

    @Override
    public boolean reviseCash(UserId userId, Cash cash) {
        return false;
    }

    @Override
    public boolean revisePrestige(UserId userId, UserLevel userLevel, Prestige prestige) {
        return false;
    }
}
