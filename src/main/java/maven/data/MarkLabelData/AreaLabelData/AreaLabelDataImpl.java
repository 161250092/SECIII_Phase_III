package maven.data.MarkLabelData.AreaLabelData;

import maven.data.MySQL.MySQLConnector;
import maven.model.label.areaLabel.Area;
import maven.model.label.areaLabel.AreaLabel;
import maven.model.label.areaLabel.Pixel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaLabelDataImpl implements AreaLabelDataService {
    private Connection conn;
    @Override
    public boolean saveLabelList(UserId userId, TaskId taskId, List<AreaLabel> labelList) {
        boolean r_tag = false, r_pixel_list = false;
        for(int iNumber = 0;iNumber < labelList.size();iNumber++){
            for(int aNumber = 0;aNumber < labelList.get(iNumber).getAreaList().size(); aNumber++){
                String tag = labelList.get(iNumber).getAreaList().get(aNumber).getTag();
                List<Pixel> areaBorder = labelList.get(iNumber).getAreaList().get(aNumber).getAreaBorder();
                r_tag = this.saveAreaTag(userId, taskId, iNumber, aNumber, tag);
                r_pixel_list = this.saveAreaBorder(userId, taskId, iNumber, aNumber, areaBorder);
            }
        }
        return r_tag && r_pixel_list;
    }
    private boolean saveAreaTag(UserId userId, TaskId taskId, int iNumber, int aNumber, String tag){
        conn = new MySQLConnector().getConnection("Label");
        String sql;
        PreparedStatement stmt;
        try{
            sql = "insert into aLabel_tag values (?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId.value);
            stmt.setString(2, taskId.value);
            stmt.setInt(3, iNumber);
            stmt.setInt(4, aNumber);
            stmt.setString(5, tag);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private boolean saveAreaBorder(UserId userId, TaskId taskId, int iNumber, int aNumber, List<Pixel> areaBorder){
        conn = new MySQLConnector().getConnection("Label");
        String sql;
        PreparedStatement stmt;
        try {
            sql = "insert into aLabel_pixel values (?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            for (int pNumber = 0; pNumber < areaBorder.size(); pNumber++){
                stmt.setString(1, userId.value);
                stmt.setString(2, taskId.value);
                stmt.setInt(3, iNumber);
                stmt.setInt(4,aNumber);
                stmt.setInt(5, pNumber);
                stmt.setDouble(6, areaBorder.get(pNumber).x);
                stmt.setDouble(7, areaBorder.get(pNumber).y);

                stmt.executeUpdate();
                stmt.clearParameters();
            }
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteLabel(UserId userId, TaskId taskId) {
       boolean r_tag = false, r_pixel_list = false;
        if (this.isLabelExist(userId, taskId)) {
            r_tag = this.deleteAllAreaTags(userId, taskId);
            r_pixel_list = this.deleteAllAreaBorders(userId, taskId);
        }

        return r_tag && r_pixel_list;
    }
    private boolean isLabelExist(UserId userId, TaskId taskId){
        conn = new MySQLConnector().getConnection("Label");
        String sql;
        PreparedStatement stmt;
        ResultSet rs;
        boolean r1 = false;
        boolean r2 = false;

        try{
            sql = "select * from aLabel_tag where UserId = ? and TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userId.value);
            stmt.setString(2,taskId.value);
            rs = stmt.executeQuery();
            while(rs.next()){ r1 = true; }

            sql = "select * from aLabel_pixel where UserId = ? and TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userId.value);
            stmt.setString(2,taskId.value);
            rs = stmt.executeQuery();
            while(rs.next()){ r2 = true; }

            stmt.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return r1 && r2;
    }
    private boolean deleteAllAreaTags(UserId userId, TaskId taskId){
        conn = new MySQLConnector().getConnection("Label");
        String sql;
        PreparedStatement stmt;
        try{
            sql = "delete from aLabel_tag where UserId = ? and TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userId.value);
            stmt.setString(2,taskId.value);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    private boolean deleteAllAreaBorders(UserId userId, TaskId taskId){
        conn = new MySQLConnector().getConnection("Label");
        String sql;
        PreparedStatement stmt;
        try{
            sql = "delete from aLabel_pixel where UserId = ? and TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userId.value);
            stmt.setString(2,taskId.value);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<AreaLabel> getLabelList(UserId userId, TaskId taskId) {
        conn = new MySQLConnector().getConnection("Label");

        List<AreaLabel> areaLabels = new ArrayList<>();
        PreparedStatement stmt;
        String sql;
        ResultSet rs;
        int count_iNumber = 0;
        try{
            sql = "select max(iNumber) as max_iNumber from aLabel_tag where UserId = ? and TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userId.value);
            stmt.setString(2,taskId.value);
            rs = stmt.executeQuery();
            if (rs.next()){
                if (rs.getObject("max_iNumber") != null){
                    count_iNumber = rs.getInt("max_iNumber") + 1;
                }
            }
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        for (int iNumber = 0; iNumber < count_iNumber; iNumber++){
            areaLabels.add(new AreaLabel(this.getAreaList(userId, taskId, iNumber)));
        }

        return areaLabels;
    }
    private List<Area> getAreaList(UserId userId, TaskId taskId, int iNumber){
        conn = new MySQLConnector().getConnection("Label");
        List<Area> areaList = new ArrayList<>();
        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        int count_aNumber = 0;
        List<String> tagList = new ArrayList<>();
        try {
            //sql = "select count(distinct aNumber) as count_aNumber from aLabel_tag where userId = ? and taskId = ? and iNumber = ?";
            //stmt = conn.prepareStatement(sql);
            //stmt.setString(1,userId.value);
            //stmt.setString(2,taskId.value);
            //stmt.setInt(3, iNumber);
            //rs = stmt.executeQuery();
            //while (rs.next()){ count_aNumber = rs.getInt("count_aNumber");}

            sql = "select tag from aLabel_tag where userId = ? and taskId = ? and iNumber = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userId.value);
            stmt.setString(2,taskId.value);
            stmt.setInt(3, iNumber);
            rs = stmt.executeQuery();
            while (rs.next()){ tagList.add(rs.getString("tag")); }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int aNumber = 0; aNumber < tagList.size(); aNumber++){
            areaList.add(new Area(tagList.get(aNumber), this.getAreaBorder(userId, taskId, iNumber, aNumber)));
        }

        return areaList;
    }
    private List<Pixel> getAreaBorder(UserId userId, TaskId taskId, int iNumber, int aNumber){
        conn = new MySQLConnector().getConnection("Label");
        List<Pixel> areaBorder = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;
        try {
            sql = "select * from aLabel_pixel where UserId = ? and TaskId = ? and iNumber = ? and aNumber = ? " +
                    "order by pNumber";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId.value);
            stmt.setString(2, taskId.value);
            stmt.setInt(3, iNumber);
            stmt.setInt(4, aNumber);
            rs = stmt.executeQuery();
            while (rs.next()){
                areaBorder.add(new Pixel(rs.getDouble("x"), rs.getDouble("y")));
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return areaBorder;
    }
}
