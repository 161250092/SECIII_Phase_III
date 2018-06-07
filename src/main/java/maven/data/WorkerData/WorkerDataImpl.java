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
        return null;
    }

    @Override
    public boolean acceptTask(AcceptedTask acceptedTask) {
        return false;
    }

    @Override
    public boolean reviseAcceptedTaskState(UserId userId, TaskId taskId, AcceptedTaskState acceptedTaskState) {
        return false;
    }

    @Override
    public AcceptedTask getAcceptedTaskById(UserId userId, TaskId taskId) {
        return null;
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

            rs = stmt.executeQuery(sql);

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
