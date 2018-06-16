package maven.data.RequestorData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maven.businessLogic.algorithm.PricingAlgorithm;
import maven.data.MySQL.MySQLConnector;
import maven.model.massTask.MassTaskDetail;
import maven.model.massTask.MassTaskPricingMechanism;
import maven.model.massTask.WorkerBid;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.PublishedTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestorMassTaskDateImpl implements RequestorMassTaskDataService {
    private Connection conn;

    /*                  发布者                    */
    @Override
    public boolean saveMassTaskDetail(MassTaskDetail massTaskDetail) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        PreparedStatement stmt;
        String sql;
        try{
            sql = "insert into MassTaskDetail values (?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,massTaskDetail.getTaskId().value);
            stmt.setDouble(2,massTaskDetail.getGivenUnitPrice().value);
            stmt.setDouble(3,massTaskDetail.getBudget().value);
            stmt.setString(4,massTaskDetail.getMassTaskPricingMechanism().name());
            stmt.setLong(5,massTaskDetail.getStartTime());
            stmt.setLong(6,massTaskDetail.getEndTime());
            stmt.setBoolean(7, false);
            stmt.execute();

            stmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<MassTaskDetail> getAllMassTaskDetailOfThisRequestor(UserId requestorId) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        List<MassTaskDetail> massTaskDetails = null;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;
        try {
            sql = "select * from MassTaskDetail where TaskId like ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, requestorId.value + "%");
            rs = stmt.executeQuery();
            massTaskDetails = getMassTaskDetailListFromResultSet(rs);

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return massTaskDetails;
    }

    /*                  工人                   */
    @Override
    public List<MassTaskDetail> getAllAvailableMassTaskDetail(long searchDate) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        List<MassTaskDetail> massTaskDetails = null;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;
        try {
            sql = "select * from MassTaskDetail where ? between sDate and eDate";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, searchDate);
            rs = stmt.executeQuery();
            massTaskDetails = getMassTaskDetailListFromResultSet(rs);

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return massTaskDetails;
    }

    /*                  算法                   */
    @Override
    public List<MassTaskDetail> getAllExpiredAndNotAllocatedMassTaskDetail(long allocateTaskDate) {
        conn = new MySQLConnector().getConnection("PublishedTask");
        List<MassTaskDetail> massTaskDetails = null;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;
        try {
            sql = "select * from MassTaskDetail where eDate <= ? and isAllocated = false";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, allocateTaskDate);
            rs = stmt.executeQuery();
            massTaskDetails = getMassTaskDetailListFromResultSet(rs);

            sql = "update MassTaskDetail set isAllocated = true where eDate <= ? and isAllocated = false";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, allocateTaskDate);
            stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return massTaskDetails;
    }


    private List<MassTaskDetail> getMassTaskDetailListFromResultSet(ResultSet rs) throws SQLException {
        List<MassTaskDetail> massTaskDetails = new ArrayList<>();
        while (rs.next()){
            TaskId taskId = new TaskId(rs.getString("TaskId"));
            Cash givenUnitPrice = new Cash(rs.getDouble("UnitPrice"));
            Cash budget = new Cash(rs.getDouble("Budget"));
            MassTaskPricingMechanism mechanism = MassTaskPricingMechanism.valueOf(rs.getString("PricingMechanism"));
            long sDate = rs.getLong("sDate");
            long eDate = rs.getLong("eDate");

            if (mechanism.equals(MassTaskPricingMechanism.MAXIMIZE_TASKS)){
                massTaskDetails.add(new MassTaskDetail(taskId,budget,sDate,eDate));
            }else if (mechanism.equals(MassTaskPricingMechanism.MINIMIZE_PAYMENTS)){
                massTaskDetails.add(new MassTaskDetail(taskId, givenUnitPrice, budget, sDate, eDate));
            }
        }
        return massTaskDetails;
    }


    //@Override
    //public boolean isMassTask(TaskId taskId) {
    //    conn = new MySQLConnector().getConnection("PublishedTask");
    //
    //    boolean result = false;
    //
    //    PreparedStatement stmt;
    //    String sql;
    //    ResultSet rs;
    //    try{
    //        sql = "select * from MassTaskDetail where TaskId = ?";
    //        stmt = conn.prepareStatement(sql);
    //        stmt.setString(1,taskId.value);
    //        rs = stmt.executeQuery();
    //        while(rs.next()){ result = true; }
    //
    //        stmt.close();
    //    }catch (Exception e){
    //        e.printStackTrace();
    //    }
    //
    //    return result;
    //}

    //@Override
    //public MassTaskDetail getMassTaskDetail(TaskId taskId) {
    //    conn = new MySQLConnector().getConnection("PublishedTask");
    //
    //    MassTaskDetail result = null;
    //
    //    PreparedStatement stmt;
    //    String sql;
    //    ResultSet rs;
    //    try{
    //        sql = "select * from MassTaskDetail where TaskId = ?";
    //        stmt = conn.prepareStatement(sql);
    //        stmt.setString(1,taskId.value);
    //        rs = stmt.executeQuery();
    //        while(rs.next()){
    //            Cash unitPrice = new Cash(rs.getDouble("UnitPrice"));
    //            Cash budget = new Cash(rs.getDouble("Budget"));
    //            MassTaskPricingMechanism pricingMechanism = MassTaskPricingMechanism.valueOf(rs.getString("PricingMechanism"));
    //            long sDate = rs.getLong("sDate");
    //            long eDate = rs.getLong("eDate");
    //
    //            if(pricingMechanism.equals(MassTaskPricingMechanism.MAXIMIZE_TASKS)){
    //                result = new MassTaskDetail(taskId,budget,sDate,eDate);
    //            }else if (pricingMechanism.equals(MassTaskPricingMechanism.MINIMIZE_PAYMENTS)){
    //                result = new MassTaskDetail(taskId,unitPrice,budget,sDate,eDate);
    //            }
    //        }
    //        stmt.close();
    //    }catch (Exception e){
    //        e.printStackTrace();
    //    }
    //
    //    return result;
    //}
}
