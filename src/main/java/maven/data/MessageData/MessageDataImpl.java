package maven.data.MessageData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maven.data.MySQL.MySQLConnector;
import maven.model.message.*;
import maven.model.primitiveType.*;
import maven.model.task.AcceptedTaskState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDataImpl implements  MessageDataService{
    private Connection conn;

    @Override
    public boolean savePublishedTaskMessage(PublishedTaskMessage publishedTaskMessage) {
        conn = new MySQLConnector().getConnection("Message");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "insert into PublishedTaskMessage values (?,?,?,?,?,?,?)";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,publishedTaskMessage.getMessageId().value);
            stmt.setString(2,publishedTaskMessage.getRequestorId().value);
            stmt.setString(3,publishedTaskMessage.getTaskId().value);
            stmt.setString(4,publishedTaskMessage.getWorkerId().value);
            stmt.setString(5,publishedTaskMessage.getWorkerName().value);
            stmt.setDouble(6,publishedTaskMessage.getCash().value);
            stmt.setBoolean(7,publishedTaskMessage.isChecked());

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
    public boolean saveAcceptedTaskMessage(AcceptedTaskMessage acceptedTaskMessage) {
        conn = new MySQLConnector().getConnection("Message");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "insert into AcceptedTaskMessage values (?,?,?,?,?,?)";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,acceptedTaskMessage.getMessageId().value);
            stmt.setString(2,acceptedTaskMessage.getWorkerId().value);
            stmt.setString(3,acceptedTaskMessage.getTaskId().value);
            stmt.setDouble(4,acceptedTaskMessage.getCash().value);
            stmt.setString(5,acceptedTaskMessage.getAcceptedTaskState().toString());
            stmt.setBoolean(6,acceptedTaskMessage.isChecked());


            stmt.executeUpdate();

            stmt.close();
            conn.close();

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }


        return  result;
    }

    @Override
    public boolean saveGuyMessage(GuyMessage guyMessage) {
        conn = new MySQLConnector().getConnection("Message");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "insert into GuyMessage values (?,?,?,?,?,?,?)";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,guyMessage.getMessageId().value);
            stmt.setString(2,guyMessage.getWorkerId().value);
            stmt.setString(3,guyMessage.getRequestorId().value);
            stmt.setString(4,guyMessage.getRequestorName().value);
            stmt.setString(5,guyMessage.getTaskId().value);
            stmt.setDouble(6,guyMessage.getCash().value);
            stmt.setBoolean(7,guyMessage.isChecked());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }


        return  result;
    }

    @Override
    public boolean saveBillMessage(BillMessage billMessage) {
        conn = new MySQLConnector().getConnection("Message");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        Gson gson = new GsonBuilder().create();
        try{
            sql = "insert into BillMessage values (?,?,?,?,?,?,?)";

            stmt = conn.prepareStatement(sql);

            String date = gson.toJson(billMessage.getDate());

            stmt.setString(1,billMessage.getMessageId().value);
            stmt.setString(2,billMessage.getUserId().value);
            stmt.setString(3,billMessage.getBillType().toString());
            stmt.setString(4,billMessage.getBillReason().toString());
            stmt.setDouble(5,billMessage.getCash().value);
            stmt.setString(6,date);
            stmt.setBoolean(7,billMessage.isConfirmed());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }


        return  result;
    }

    @Override
    public boolean saveAchievementMessage(AchievementMessage achievementMessage) {
        conn = new MySQLConnector().getConnection("Message");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "insert into AchievementMessage values (?,?,?,?)";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,achievementMessage.getMessageId().value);
            stmt.setString(2,achievementMessage.getUserId().value);
            stmt.setString(3,achievementMessage.getAchievementId());
            stmt.setBoolean(4,achievementMessage.isChecked());


            stmt.executeUpdate();

            stmt.close();
            conn.close();

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }


        return  result;
    }

    @Override
    public List<PublishedTaskMessage> getUncheckedPublishedTaskMessage(UserId userId) {
        conn = new MySQLConnector().getConnection("Message");

        List<PublishedTaskMessage> publishedTaskMessages = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from PublishedTaskMessage where UserId = ? and isChecked = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,userId.value);
            stmt.setBoolean(2,false);

            rs = stmt.executeQuery();

            while(rs.next()){
                MessageId messageId = new MessageId(rs.getString("MessageId"));
                TaskId taskId = new TaskId(rs.getString("TaskId"));
                UserId workerId = new UserId(rs.getString("WorkerId"));
                Username workerName = new Username(rs.getString("WorkerName"));
                Cash cash = new Cash(rs.getDouble("Cash"));

                PublishedTaskMessage publishedTaskMessage = new PublishedTaskMessage(messageId,userId,taskId,workerId,workerName,cash);

                publishedTaskMessages.add(publishedTaskMessage);
             }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return publishedTaskMessages;
    }

    @Override
    public List<AcceptedTaskMessage> getUncheckedAcceptedTaskMessage(UserId userId) {
        conn = new MySQLConnector().getConnection("Message");

        List<AcceptedTaskMessage> acceptedTaskMessages = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from AcceptedTaskMessage where UserId  = ? and isChecked = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,userId.value);
            stmt.setBoolean(2,false);

            rs = stmt.executeQuery();

            while(rs.next()){
                MessageId messageId = new MessageId(rs.getString("MessageId"));
                TaskId taskId = new TaskId(rs.getString("TaskId"));
                Cash cash = new Cash(rs.getDouble("Cash"));
                AcceptedTaskState state = AcceptedTaskState.valueOf(rs.getString("State"));

                AcceptedTaskMessage acceptedTaskMessage  = new AcceptedTaskMessage(messageId,userId,taskId,cash,state);
                acceptedTaskMessages.add(acceptedTaskMessage);

            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return acceptedTaskMessages;
    }

    @Override
    public List<GuyMessage> getUncheckedGuyMessage(UserId userId) {
        conn = new MySQLConnector().getConnection("Message");

        List<GuyMessage> guyMessages = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from GuyMessage where WorkerId = ? and isChecked = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,userId.value);
            stmt.setBoolean(2,false);

            rs = stmt.executeQuery();

            while(rs.next()){
                MessageId messageId = new MessageId(rs.getString("MessageId"));
                UserId requestorId = new UserId(rs.getString("RequestorId"));
                Username requestorName = new Username(rs.getString("RequestorName"));
                TaskId taskId = new TaskId(rs.getString("TaskId"));
                Cash cash = new Cash(rs.getDouble("Cash"));

                GuyMessage guyMessage = new GuyMessage(messageId,userId,requestorId,requestorName,taskId,cash);

                guyMessages.add(guyMessage);

            }


            stmt.close();

            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return guyMessages;
    }

    @Override
    public List<BillMessage> getUncheckedBillMessage(UserId userId) {
        conn = new MySQLConnector().getConnection("Message");

        List<BillMessage> billMessages = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from BillMessage where UserId = ? and isChecked = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,userId.value);
            stmt.setBoolean(2,false);

            rs = stmt.executeQuery();

            Gson gson = new GsonBuilder().create();
            while(rs.next()){
                MessageId messageId = new MessageId(rs.getString("MessageId"));
                BillType billType = BillType.valueOf(rs.getString("Type"));
                BillReason billReason = BillReason.valueOf(rs.getString("Reason"));
                Cash cash = new Cash(rs.getDouble("Cash"));
                Date date = gson.fromJson(rs.getString("Date"), Date.class);
                BillMessage billMessage = new BillMessage(messageId,userId,billType,billReason,cash,date);

                billMessages.add(billMessage);
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return billMessages;
    }

    @Override
    public List<AchievementMessage> getUncheckedAchievementMessage(UserId userId) {
        conn = new MySQLConnector().getConnection("Message");

        List<AchievementMessage> achievementMessages = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from AchievementMessage where UserId = ? and isChecked = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,userId.value);
            stmt.setBoolean(2,false);

            rs = stmt.executeQuery();

            while(rs.next()){
                MessageId messageId = new MessageId(rs.getString("MessageId"));
                String achievementId = rs.getString("AchievementId");

                AchievementMessage achievementMessage = new AchievementMessage(messageId,userId,achievementId);

                achievementMessages.add(achievementMessage);
            }


            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return achievementMessages;
    }

    @Override
    public boolean setRequestorTaskMessageChecked(MessageId messageId) {
        conn = new MySQLConnector().getConnection("Message");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update PublishedTaskMessage set isChecked = ? where MessageId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setBoolean(1,true);
            stmt.setString(2,messageId.value);

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
    public boolean setWorkerTaskMessageChecked(MessageId messageId) {
        conn = new MySQLConnector().getConnection("Message");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update AcceptedTaskMessage set isChecked = ? where MessageId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setBoolean(1,true);
            stmt.setString(2,messageId.value);

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
    public boolean setGuyMessageChecked(MessageId messageId) {
        conn = new MySQLConnector().getConnection("Message");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update GuyMessage set isChecked = ? where MessageId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setBoolean(1,true);
            stmt.setString(2,messageId.value);

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
    public boolean setBillMessageChecked(MessageId messageId) {
        conn = new MySQLConnector().getConnection("Message");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update BillMessage set isChecked  = ? where MessageId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setBoolean(1,true);
            stmt.setString(2,messageId.value);

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
    public boolean setAchievementMessageChecked(MessageId messageId) {
        conn = new MySQLConnector().getConnection("Message");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update AchievementMessage set isChecked = ? where MessageId = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setBoolean(1,true);
            stmt.setString(2,messageId.value);

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
    public List<BillMessage> getAllBillMessage() {
        conn = new MySQLConnector().getConnection("Message");

        List<BillMessage> billMessageList = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;


        Gson gson = new GsonBuilder().create();
        try{
            sql = "select * from BillMessage";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while(rs.next()){
                MessageId messageId = new MessageId(rs.getString("MessageId"));
                UserId userId = new UserId(rs.getString("UserId"));
                BillType billType = BillType.valueOf(rs.getString("Type"));
                BillReason billReason = BillReason.valueOf(rs.getString("Reason"));
                Cash cash = new Cash(rs.getDouble("Cash"));
                Date date = gson.fromJson(rs.getString("Date"), Date.class);
                boolean confirm = new Boolean(rs.getBoolean("isChecked"));

                BillMessage billMessage = new BillMessage(messageId,userId,billType,billReason,cash,date);

                billMessageList.add(billMessage);
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return billMessageList;
    }

    @Override
    public MessageId getMessageIdForCreateMessage(){
        conn = new MySQLConnector().getConnection("Message");

        int num = 0;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select Count(*) from PublishedTaskMessage";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while(rs.next()){
                num += rs.getInt(1);
            }
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            sql = "select Count(*) from AcceptedTaskMessage";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while(rs.next()){
                num += rs.getInt(1);
            }
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        try{
            sql = "select Count(*) from AchievementMessage";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while(rs.next()){
                num += rs.getInt(1);
            }
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        try{
            sql = "select Count(*) from BillMessage";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while(rs.next()){
                num += rs.getInt(1);
            }
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        try{
            sql = "select Count(*) from GuyMessage";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while(rs.next()){
                num += rs.getInt(1);
            }
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        int a = num;
        int b = 11;

        while(a > 1){
            a = a / 10;
            b--;
        }

        //  总长度12位
        String re = String.valueOf(num);
        for(int i = 0;i < b;i++)
            re = "0" + re;


        return new MessageId(re);

    }
}
