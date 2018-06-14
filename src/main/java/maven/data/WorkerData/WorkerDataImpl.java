package maven.data.WorkerData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maven.data.MySQL.MySQLConnector;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.LabelScore;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTask;
import maven.model.task.AcceptedTaskState;
import maven.model.task.WorkerDiscount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkerDataImpl implements WorkerDataService {
    private Connection conn;

    @Override
    public List<AcceptedTask> getAcceptedTaskListByUserId(UserId userId) {
        conn = new MySQLConnector().getConnection("AcceptedTask");

        List<AcceptedTask> acceptedTasks = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        Gson gson = new GsonBuilder().create();
        try{
            sql  = "select * from AcceptedTask where UserId = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,userId.value);

            rs = stmt.executeQuery();

            while(rs.next()){
                TaskId taskId = new TaskId(rs.getString("TaskId"));
                Date date = gson.fromJson(rs.getString("Date"),Date.class);
                Cash cash = new Cash(rs.getDouble("Cash"));
                WorkerDiscount discount = gson.fromJson(rs.getString("Discount"),WorkerDiscount.class);
                AcceptedTaskState state = AcceptedTaskState.valueOf(rs.getString("State"));
                LabelScore score = new LabelScore(rs.getDouble("Score"));

                AcceptedTask acceptedTask = new AcceptedTask(userId,taskId,date,cash,discount,state,score);

                acceptedTasks.add(acceptedTask);

            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return acceptedTasks;
    }

    @Override
    public boolean acceptTask(AcceptedTask acceptedTask) {
        conn = new MySQLConnector().getConnection("AcceptedTask");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        Gson gson = new GsonBuilder().create();
        try{
            sql = "insert into AcceptedTask values (?,?,?,?,?,?,?)";

            stmt = conn.prepareStatement(sql);

            String date = gson.toJson(acceptedTask.getStartTime());
            String discount = gson.toJson(acceptedTask.getWorkerDiscount());

            stmt.setString(1,acceptedTask.getUserId().value);
            stmt.setString(2,acceptedTask.getTaskId().value);
            stmt.setString(3,date);
            stmt.setDouble(4,acceptedTask.getActualTaskPrice().value);
            stmt.setString(5,acceptedTask.getAcceptedTaskState().toString());
            stmt.setString(6,discount);
            stmt.setDouble(7,acceptedTask.getLabelScore().value);

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
    public boolean reviseAcceptedTaskState(UserId userId, TaskId taskId, AcceptedTaskState acceptedTaskState) {
        conn = new MySQLConnector().getConnection("AcceptedTask");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update AcceptedTask set State = ? where UserId = ? and TaskId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,acceptedTaskState.toString());
            stmt.setString(2,userId.value);
            stmt.setString(3,taskId.value);

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
    public boolean saveLableScore(UserId userId, TaskId taskId, LabelScore labelScore) {
        conn = new MySQLConnector().getConnection("AcceptedTask");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update AcceptedTask set LabelScore = ? where UserId = ? and TaskId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1,labelScore.value);
            stmt.setString(2,userId.value);
            stmt.setString(3,taskId.value);

            stmt.executeUpdate();

            stmt.close();

            conn.close();

            result =  true;

        }catch (Exception e){
            e.printStackTrace();
        }


        return result;
    }

    @Override
    public AcceptedTask getAcceptedTaskById(UserId userId, TaskId taskId) {
        conn = new MySQLConnector().getConnection("AcceptedTask");

        AcceptedTask acceptedTask = null;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        Gson gson = new GsonBuilder().create();
        try{
            sql = "select * from AcceptedTask where UserId = ? and TaskId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,userId.value);
            stmt.setString(2,taskId.value);

            rs = stmt.executeQuery();


            while(rs.next()){
                Date date = gson.fromJson(rs.getString("Date"),Date.class);
                Cash cash = new Cash(rs.getDouble("Cash"));
                WorkerDiscount discount = gson.fromJson(rs.getString("Discount"),WorkerDiscount.class);
                AcceptedTaskState state = AcceptedTaskState.valueOf(rs.getString("State"));
                LabelScore score = new LabelScore(rs.getDouble("Score"));

                acceptedTask = new AcceptedTask(userId,taskId,date,cash,discount,state,score);

            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return acceptedTask;
    }

    @Override
    public List<AcceptedTask> getAcceptedTaskList(TaskId taskId) {
        conn = new MySQLConnector().getConnection("AcceptedTask");

        List<AcceptedTask> acceptedTasks = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        Gson gson = new GsonBuilder().create();
        try{
            sql = "select * from AcceptedTask where TaskId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,taskId.value);

            rs = stmt.executeQuery();

            while(rs.next()){
                UserId userId = new UserId(rs.getString("UserId"));
                Date date = gson.fromJson(rs.getString("Date"),Date.class);
                Cash cash = new Cash(rs.getDouble("Cash"));
                AcceptedTaskState state = AcceptedTaskState.valueOf(rs.getString("State"));
                WorkerDiscount discount = gson.fromJson(rs.getString("Discount"),WorkerDiscount.class);
                LabelScore score = new LabelScore(rs.getDouble("Score"));

                AcceptedTask acceptedTask = new AcceptedTask(userId,taskId,date,cash,discount,state,score);

                acceptedTasks.add(acceptedTask);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return acceptedTasks;
    }

}
