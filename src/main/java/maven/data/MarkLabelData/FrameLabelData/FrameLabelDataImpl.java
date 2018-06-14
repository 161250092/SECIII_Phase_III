package maven.data.MarkLabelData.FrameLabelData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maven.data.MySQL.MySQLConnector;
import maven.model.label.frameLabel.Frame;
import maven.model.label.frameLabel.FrameLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FrameLabelDataImpl implements FrameLabelDataService{
    private Connection conn;
    @Override
    public boolean saveLabelList(UserId userId, TaskId taskId, List<FrameLabel> labelList) {
        conn = new MySQLConnector().getConnection("Label");

        boolean result = false;

        PreparedStatement stmt;
        String sql;
        Gson gson = new GsonBuilder().create();

        for(int i = 0;i < labelList.size();i++){
            for(int j = 0;j < labelList.get(i).getFrameList().size();i++){

                try{
                    sql = "insert into fLabel values (?,?,?,?,?)";
                    stmt = conn.prepareStatement(sql);

                    String content = gson.toJson(labelList.get(i).getFrameList().get(j));

                    stmt.setString(1,userId.value);
                    stmt.setString(2,taskId.value);
                    stmt.setInt(3,i);
                    stmt.setInt(4,j);
                    stmt.setString(5,content);

                    stmt.executeUpdate();

                    stmt.close();
                    if((i == labelList.size() - 1)&&(j == labelList.get(labelList.size() - 1).getFrameList().size() - 1))
                        result = true;
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

        return result;
    }

    @Override
    public boolean deleteLabel(UserId userId, TaskId taskId) {
        conn = new MySQLConnector().getConnection("Label");

        boolean result = false;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        boolean exist = false;
        try{
            sql = "select * from fLabel where UserId = ? and TaskId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,userId.value);
            stmt.setString(2,taskId.value);

            rs = stmt.executeQuery();

            while(rs.next()){
                exist = true;
            }

            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        if(exist){
            try{
                sql = "delete * from fLabel where UserId = ? and TaskId = ?";

                stmt = conn.prepareStatement(sql);

                stmt.setString(1,userId.value);
                stmt.setString(2,taskId.value);

                stmt.executeUpdate();

                stmt.close();
                conn.close();

                result = true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else
            result = true;

        return result;
    }

    @Override
    public List<FrameLabel> getLabelList(UserId userId, TaskId taskId) {
        conn = new MySQLConnector().getConnection("fLabel");

        List<FrameLabel> frameLabels = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        int num = 0;
        try{
            sql = "select max(iNumber) from fLabel where UserId = ? and TaskId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,userId.value);
            stmt.setString(2,taskId.value);

            rs = stmt.executeQuery();

            while(rs.next()){
                num = rs.getInt("iNumber");
            }

            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        Gson gson = new  GsonBuilder().create();
        for(int i = 0;i < num;i++){

            try{
                sql = "select * from fLabel where UserId = ? and TaskId = ? order by aNumber";
                stmt = conn.prepareStatement(sql);

                stmt.setString(1,userId.value);
                stmt.setString(2,taskId.value);

                rs = stmt.executeQuery();

                while(rs.next()){
                    List<Frame> frames = new ArrayList<>();
                    Frame frame = gson.fromJson(rs.getString("Content"),Frame.class);
                    frames.add(frame);
                    FrameLabel frameLabel = new FrameLabel(frames);
                    frameLabels.add(frameLabel);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return frameLabels;
    }
}
