package maven.data.AdminData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maven.data.MySQL.MySQLConnector;
import maven.data.RequestorData.RequestorDataImpl;
import maven.model.primitiveType.*;
import maven.model.task.*;
import maven.model.user.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminDataImpl implements AdminDataService {
    private Connection conn;

    @Override
    public List<PublishedTask> getAllPublishedTask() {
        conn = new MySQLConnector().getConnection("PublishedTask");
        List<PublishedTask> publishedTasks = new ArrayList<>();

        //查询任务基本信息
        PreparedStatement stmtP;
        String sqlP;
        ResultSet rsP;


        //查询图片名
        PreparedStatement stmtN;
        String sqlN;
        ResultSet rsN;

        //查询任务状态列表
        PreparedStatement stmtS;
        String sqlS;
        ResultSet rsS;


        try{
            //查询任务基本信息
            sqlP = "select * from PublishedTask";
            stmtP = conn.prepareStatement(sqlP);
            rsP = stmtP.executeQuery(sqlP);

            while(rsP.next()){

                UserId userId = new UserId(rsP.getString("UserId"));
                TaskId taskId = new TaskId(rsP.getString("TaskId"));
                TaskType taskType = new RequestorDataImpl().getTaskType(taskId);
                LabelType labelType = new LabelType(rsP.getString("LabelType"));
                TaskDescription taskDescription  = new TaskDescription(rsP.getString("Description"));
                WorkerNum aWorkerNum = new WorkerNum(rsP.getInt("aWorkerNum"));
                WorkerNum fWorkerNum = new WorkerNum(rsP.getInt("fWorkerNum"));
                PublishedTaskState state = PublishedTaskState.valueOf(rsP.getString("State"));
                List<Filename> filenames = new ArrayList<>();
                List<PublishedTaskDetail> publishedTaskDetails = new ArrayList<>();


                //查询图片名
                sqlN = "select * from FileName where TaskId = ?order by iNumber ASC";
                stmtN = conn.prepareStatement(sqlN);
                stmtN.setString(1,taskId.value);
                rsN = stmtN.executeQuery(sqlN);

                while(rsN.next()){
                    Filename fileName = new Filename(rsN.getString("Value"));
                    filenames.add(fileName);
                }

                stmtN.close();

                //查询任务状态列表
                sqlS = "select * from Detail where TaskId = ? order by iNumber ASC";

                stmtS = conn.prepareStatement(sqlS);
                stmtS.setString(1,taskId.value);

                rsS = stmtS.executeQuery(sqlS);

                while(rsS.next()){
                    Gson gson = new GsonBuilder().create();

                    Date date = gson.fromJson(rsS.getString("Date"),Date.class);
                    WorkerNum workerNum = new WorkerNum(rsS.getInt("WorkerNum"));
                    Cash cash = new Cash(rsS.getDouble("Cash"));
                    RequestorDiscount requestorDiscount = gson.fromJson(rsS.getString("Discount"),RequestorDiscount.class);

                    PublishedTaskDetail publishedTaskDetail = new PublishedTaskDetail(date,workerNum,cash,requestorDiscount);
                    publishedTaskDetails.add(publishedTaskDetail);
                }

                stmtS.close();
                PublishedTask publishedTask = new PublishedTask(taskId,userId,taskType,labelType,filenames,taskDescription,aWorkerNum,fWorkerNum,publishedTaskDetails,state);
                publishedTasks.add(publishedTask);
            }

            stmtP.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return publishedTasks;
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
