package maven.data.UserData;

import maven.data.MySQL.MySQLConnector;
import maven.model.primitiveType.*;
import maven.model.user.Requestor;
import maven.model.user.User;
import maven.model.user.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class UserDataImpl implements UserDataService{

    private Connection conn;

    @Override
    public List<Username> getAllUsernameList() {
        conn = new MySQLConnector().getConnection("User");

        List<Username> userName = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;
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
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                Username temp = new Username(rs.getString("Username"));
                userName.add(temp);
            }

            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        try {
            sql = "select Username from Workrer";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Username temp = new Username(rs.getString("Username"));
                userName.add(temp);
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return userName;
    }

    @Override
    public List<Worker> getAllWorker() {
        conn = new MySQLConnector().getConnection("User");

        List<Worker> workers = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

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
        conn = new MySQLConnector().getConnection("User");

        List<Requestor> requestors = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

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
        conn = new MySQLConnector().getConnection("User");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "insert into Requestor values (?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,requestor.getUserId().value);
            stmt.setString(2,requestor.getUsername().value);
            stmt.setString(3,requestor.getPassword().value);
            stmt.setString(4,requestor.getEmail().address);
            stmt.setString(5,requestor.getPhone().number);
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
        conn = new MySQLConnector().getConnection("User");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "insert into Worker values (?,?,?,?,?,?,?,?)";
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
        conn = new MySQLConnector().getConnection("User");

        List<UserId> userIds = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select UserId from Admin";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                UserId userId = new UserId(rs.getString("UserId"));
                userIds.add(userId);
            }

            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            sql = "select UserId from Worker";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                UserId userId = new UserId(rs.getString("UserId"));
                userIds.add(userId);
            }

            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
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
        conn = new MySQLConnector().getConnection("User");

        UserId result = null;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select UserId from Worker where Username = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,username.value);

            rs = stmt.executeQuery(sql);

            while(rs.next()){
                result = new UserId(rs.getString("UserId"));
            }

            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        try{

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
        conn = new MySQLConnector().getConnection("User");

        User result = null;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

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

            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        try{

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
        conn = new MySQLConnector().getConnection("User");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update Worker set  Email = ? where UserId = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,email.address);
            stmt.setString(2,userId.value);

            stmt.executeUpdate();

            stmt.close();

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            sql = "update Requestor set  Email = ? where UserId = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,email.address);
            stmt.setString(2,userId.value);

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
    public boolean reviseUserPhone(UserId userId, Phone phone) {
        conn = new MySQLConnector().getConnection("User");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update Worker set Phone = ? where UserId = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,phone.number);
            stmt.setString(2,userId.value);

            stmt.executeUpdate();

            stmt.close();

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            sql = "update Requestor set Phone = ? where UserId = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,phone.number);
            stmt.setString(2,userId.value);

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
    public boolean reviseCash(UserId userId, Cash cash) {
        conn = new MySQLConnector().getConnection("User");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update Worker set Cash = ? where UserId = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1,cash.value);
            stmt.setString(2,userId.value);

            stmt.executeUpdate();

            stmt.close();

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            sql = "update Requestor set Cash = ? where UserId = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1,cash.value);
            stmt.setString(2,userId.value);

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
    public boolean revisePrestige(UserId userId, Prestige prestige) {
        conn = new MySQLConnector().getConnection("User");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update Worker set Prestige = ? where UserId = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1,prestige.value);
            stmt.setString(2,userId.value);

            stmt.executeUpdate();

            stmt.close();

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            sql = "update Requestor set Prestige = ? where UserId = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1,prestige.value);
            stmt.setString(2,userId.value);

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
