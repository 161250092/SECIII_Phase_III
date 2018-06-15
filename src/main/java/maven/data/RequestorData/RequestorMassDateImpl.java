package maven.data.RequestorData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maven.data.MySQL.MySQLConnector;
import maven.model.massTask.MassTaskDetail;
import maven.model.massTask.MassTaskPricingMechanism;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.TaskId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class RequestorMassDateImpl implements RequestorMassTaskDataService {
    private Connection conn;

    @Override
    public boolean saveMassTaskDetail(MassTaskDetail massTaskDetail) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        Gson gson = new GsonBuilder().create();
        try{
            sql = "insert into MassTaskDetail values (?,?,?,?,?,?)";

            stmt = conn.prepareStatement(sql);

            String sDate = gson.toJson(massTaskDetail.getStartTime());
            String eDate = gson.toJson(massTaskDetail.getEndTime());

            stmt.setString(1,massTaskDetail.getTaskId().value);
            stmt.setDouble(2,massTaskDetail.getGivenUnitPrice().value);
            stmt.setDouble(3,massTaskDetail.getBudget().value);
            stmt.setString(4,massTaskDetail.getMassTaskPricingMechanism().toString());
            stmt.setString(5,sDate);
            stmt.setString(6,eDate);

            stmt.close();
            conn.close();

            result = true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public MassTaskDetail getMassTaskDetail(TaskId taskId) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        MassTaskDetail result = null;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        Gson gson = new GsonBuilder().create();
        try{
            sql = "select * from MassTaskDetail where TaskId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,taskId.value);

            rs = stmt.executeQuery();

            while(rs.next()){
                Cash unitPrice = new Cash(rs.getDouble("UnitPrice"));
                Cash budget = new Cash(rs.getDouble("Budget"));
                MassTaskPricingMechanism pricingMechanism = MassTaskPricingMechanism.valueOf(rs.getString("PricingMechanism"));
                Date sDate = gson.fromJson(rs.getString("sDate"),Date.class);
                Date eDate = gson.fromJson(rs.getString("eDate"),Date.class);

                if(pricingMechanism.equals(MassTaskPricingMechanism.MAXIMIZE_TASKS))
                    result = new MassTaskDetail(taskId,unitPrice,sDate,eDate);
                else
                    result = new MassTaskDetail(taskId,unitPrice,budget,sDate,eDate);

            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean isMassTask(TaskId taskId) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        boolean result = false;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from MassTaskDetail where TaskId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,taskId.value);

            rs = stmt.executeQuery();

            while(rs.next()){
                result = true;
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
