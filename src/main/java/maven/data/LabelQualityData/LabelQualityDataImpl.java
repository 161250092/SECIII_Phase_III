package maven.data.LabelQualityData;

import maven.data.MySQL.MySQLConnector;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskQuality;
import maven.model.task.AcceptedTaskState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LabelQualityDataImpl implements LabelQualityDataService{
    @Override
    public List<AcceptedTaskQuality> getAllAcceptedTaskQualityList() {
        Connection conn = new MySQLConnector().getConnection("AcceptedTask");

        List<AcceptedTaskQuality> acceptedTaskQualities = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try {
            sql = "select * from AcceptedTask where State = ? or State = ? or State = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, AcceptedTaskState.PASSED.toString());
            stmt.setString(2,AcceptedTaskState.ABANDONED_BY_REQUESTOR.toString());
            stmt.setString(3,AcceptedTaskState.REJECTED.toString());

            rs = stmt.executeQuery(sql);

            while(rs.next()){
                UserId worker = new UserId(rs.getString("UserId"));
                String[] taskId = rs.getString("TaskId").split("_");
                UserId requestorId = new UserId(taskId[0]);
                AcceptedTaskState state = AcceptedTaskState.valueOf(rs.getString("State"));

                AcceptedTaskQuality acceptedTaskQuality = new AcceptedTaskQuality(worker,requestorId,state);

                acceptedTaskQualities.add(acceptedTaskQuality);
            }

            stmt.close();

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return acceptedTaskQualities;
    }
}
