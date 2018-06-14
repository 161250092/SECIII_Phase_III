package maven.data.MarkLabelData.ImageLabelData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maven.data.MySQL.MySQLConnector;
import maven.model.label.ImageLabel;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImageLabelDataImpl implements ImageLabelDataService {
    private Connection conn;
   @Override
    public boolean saveLabelList(UserId userId, TaskId taskId, List<ImageLabel> imageLabel) {
       conn = new MySQLConnector().getConnection("Label");

       boolean result = false;

       PreparedStatement stmt;
       String sql;

       Gson gson = new GsonBuilder().create();
       for(int i = 0;i < imageLabel.size();i++){
           try{
               sql = "insert into iLabel values (?,?,?,?)";

               stmt = conn.prepareStatement(sql);

               String content = gson.toJson(imageLabel.get(i));

               stmt.setString(1,userId.value);
               stmt.setString(2,taskId.value);
               stmt.setInt(3,i);
               stmt.setString(4,content);

               stmt.executeUpdate();
               stmt.close();
               if(i == imageLabel.size() - 1)
                   result = true;
           }catch (Exception e){
               e.printStackTrace();
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

       try{
           sql = "delete from iLabel where UserId = ? and TaskId = ?";

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

        return result;
    }

    @Override
    public List<ImageLabel> getLabelList(UserId userId, TaskId taskId) {
       conn = new MySQLConnector().getConnection("Label");

       List<ImageLabel> imageLabels = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from iLabel where UserId = ? and TaskId = ? order by iNumber";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,userId.value);
            stmt.setString(2,taskId.value);

            rs = stmt.executeQuery();

            Gson gson =  new GsonBuilder().create();

            while(rs.next()){
                ImageLabel imageLabel = gson.fromJson(rs.getString("Content"),ImageLabel.class);
                imageLabels.add(imageLabel);
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return imageLabels;
    }
}
