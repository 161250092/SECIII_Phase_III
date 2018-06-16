package maven.data.WorkerData;

import maven.data.MySQL.MySQLConnector;
import maven.model.massTask.ImageNum;
import maven.model.massTask.WorkerBid;
import maven.model.massTask.WorkerBidState;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerMassTaskDataImpl implements WorkerMassTaskDataService {
    private Connection conn;

    @Override
    public boolean saveWorkerBid(WorkerBid workerBid) {
        conn = new MySQLConnector().getConnection("AcceptedTask");

        PreparedStatement stmt;
        String sql;
        try{
            sql = "insert into WorkerBid values (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,workerBid.getWorkerId().value);
            stmt.setString(2,workerBid.getChosenTaskId().value);
            stmt.setDouble(3,workerBid.getRatioOfArrivedTime());
            stmt.setDouble(4,workerBid.getWantedUnitPrice().value);
            stmt.setInt(5,workerBid.getMaxWantedImageNum().value);
            stmt.setString(6,WorkerBidState.WAITING.name());
            stmt.executeUpdate();

            stmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<WorkerBid> getAllWorkerBidOfThisTask(TaskId taskId) {
        conn = new MySQLConnector().getConnection("AcceptedTask");

        List<WorkerBid> result = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;
        try {
            sql = "select * from WorkerBid where TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, taskId.value);
            rs = stmt.executeQuery();
            while(rs.next()){
                UserId workerId = new UserId(rs.getString("UserId"));
                double radio = rs.getDouble("Radio");
                Cash cash = new Cash(rs.getDouble("Cash"));
                ImageNum imageNum = new ImageNum(rs.getInt("ImageNum"));
                WorkerBidState workerBidState = WorkerBidState.valueOf(rs.getString("WorkerBidState"));

                result.add(new WorkerBid(workerId,taskId,radio,cash,imageNum, workerBidState));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<WorkerBid> getAllBidOfThisWorker(UserId workerId) {
        conn = new MySQLConnector().getConnection("AcceptedTask");

        List<WorkerBid> result = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;
        try{
            sql = "select * from WorkerBid where UserId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,workerId.value);
            rs = stmt.executeQuery();
            while(rs.next()){
                TaskId taskId = new TaskId(rs.getString("TaskId"));
                double radio = rs.getDouble("Radio");
                Cash cash = new Cash(rs.getDouble("Cash"));
                ImageNum imageNum = new ImageNum(rs.getInt("ImageNum"));
                WorkerBidState workerBidState = WorkerBidState.valueOf(rs.getString("WorkerBidState"));

                result.add(new WorkerBid(workerId,taskId,radio,cash,imageNum, workerBidState));
            }
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean updateWorkerBidState(UserId workerId, WorkerBidState workerBidState) {
        conn = new MySQLConnector().getConnection("AcceptedTask");

        PreparedStatement stmt;
        String sql;
        try {
            sql = "update WorkerBid set WorkerBidState = ? where UserId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, workerBidState.name());
            stmt.setString(2, workerId.value);
            stmt.executeUpdate();

            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
