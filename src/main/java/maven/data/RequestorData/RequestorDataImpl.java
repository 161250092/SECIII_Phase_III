package maven.data.RequestorData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maven.data.AdminData.AdminDataImpl;
import maven.data.MySQL.MySQLConnector;
import maven.model.primitiveType.*;
import maven.model.task.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RequestorDataImpl implements RequestorDataService {
    private Connection conn;
    @Override
    public boolean saveTaskInfo(PublishedTask publishedTask) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        boolean result = false;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;
        try{
            sql = "select * from PublishedTask where TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, publishedTask.getTaskId().value);
            rs = stmt.executeQuery();
            while(rs.next()){
                //删除任务信息
                this.deleteTaskInfo(publishedTask.getTaskId());
            }

            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        boolean r1 = false;
        try{
            sql = "insert into PublishedTask values (?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,publishedTask.getUserId().value);
            stmt.setString(2,publishedTask.getTaskId().value);
            stmt.setString(3,publishedTask.getLabelType().value);
            stmt.setString(4,publishedTask.getTaskDescription().value);
            stmt.setDouble(5,publishedTask.getAcceptedWorkerNum().value);
            stmt.setDouble(6,publishedTask.getFinishedWorkerNum().value);
            stmt.setString(7,publishedTask.getPublishedTaskState().toString());

            stmt.executeUpdate();
            stmt.close();
            r1 = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        boolean r2 = false;
        for(int i = 0;i < publishedTask.getImageFilenameList().size();i++){
            try{
                sql = "insert into FileName values (?,?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1,publishedTask.getTaskId().value);
                stmt.setInt(2,i);
                stmt.setString(3,publishedTask.getImageFilenameList().get((i)).value);
                stmt.executeUpdate();

                stmt.close();
                r2 = true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        boolean r3 = false;
        Gson gson = new GsonBuilder().create();
        for(int i = 0;i < publishedTask.getPublishedTaskDetailList().size();i++){
            try{
                sql = "insert into Detail values (?,?,?,?,?,?)";
                stmt = conn.prepareStatement(sql);
                String date = gson.toJson(publishedTask.getPublishedTaskDetailList().get(i).getStartTime());
                String discount = gson.toJson(publishedTask.getPublishedTaskDetailList().get(i).getRequestorDiscount());
                stmt.setString(1,publishedTask.getTaskId().value);
                stmt.setInt(2,i);
                stmt.setString(3,date);
                stmt.setInt(4,publishedTask.getPublishedTaskDetailList().get(i).getRequiredWorkerNum().value);
                stmt.setString(5,discount);
                stmt.setDouble(6,publishedTask.getPublishedTaskDetailList().get(i).getTaskPricePerWorker().value);
                stmt.executeUpdate();

                stmt.close();
                if(i == publishedTask.getPublishedTaskDetailList().size() - 1) { r3 = true; }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean r4 = saveTaskType(publishedTask.getTaskId(), publishedTask.getTaskType());

        return r1 && r2 && r3 && r4;
    }

    private boolean deleteTaskInfo(TaskId taskId) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        PreparedStatement stmt;
        String sql;

        boolean r1 = false;
        try{
            sql = "delete from PublishedTask where TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,taskId.value);
            stmt.executeUpdate();

            stmt.close();
            r1 = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        boolean r2 = false;
        try{
            sql = "delete from FileName where TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,taskId.value);
            stmt.executeUpdate();

            stmt.close();
            r2 = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            sql = "delete from Detail where TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,taskId.value);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return r1 && r2;
    }

    @Override
    public boolean saveTaskSampleInfo(Sample taskSample) {
        TaskId taskId = taskSample.getTaskId();
        int ImageNum = taskSample.getNumber();
        List<Integer> imageIndexList = taskSample.getImageIndexList();

        conn = new MySQLConnector().getConnection("PublishedTask");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "insert into Sample values (?,?,?)";
            stmt = conn.prepareStatement(sql);

            StringBuilder index = new StringBuilder("");
            for(int i = 0;i < imageIndexList.size();i++){
                index.append(imageIndexList.get(i).toString());
                if(i < imageIndexList.size() - 1)
                    index.append(",");
            }

            stmt.setString(1,taskId.value);
            stmt.setInt(2,ImageNum);
            stmt.setString(3,index.toString());

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
    public Sample getSample(TaskId taskId) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        Sample sample;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        int Num = 0;
        List<Integer> Index = new ArrayList<>();

        try{
            sql = "select * from Sample where TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,taskId.value);
            rs = stmt.executeQuery();
            while(rs.next()){
                Num = rs.getInt("Num");
                String[] index = rs.getString("Index").split(",");
                for(String s : index)
                    Index.add(Integer.parseInt(s));
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        sample = new Sample(taskId,Num,Index);

        return sample;
    }

    @Override
    public boolean saveTaskDetail(TaskId taskId, PublishedTaskDetail publishedTaskDetail) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        boolean result = false;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        int time = 0;

        try{
            sql = "select max(iNumber) as max_iNumber from Detail where TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,taskId.value);
            rs = stmt.executeQuery();
            while(rs.next()){
                time = rs.getInt("max_iNumber") + 1;
            }

            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        Gson gson = new GsonBuilder().create();
        try{
            sql = "insert into Detail values (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            String date = gson.toJson(publishedTaskDetail.getStartTime());
            String discount = gson.toJson(publishedTaskDetail.getRequestorDiscount());

            stmt.setString(1,taskId.value);
            stmt.setInt(2,time);
            stmt.setString(3,date);
            stmt.setInt(4,publishedTaskDetail.getRequiredWorkerNum().value);
            stmt.setString(5,discount);
            stmt.setDouble(6,publishedTaskDetail.getTaskPricePerWorker().value);

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
    public boolean reviseTaskAcceptedWorkerNum(TaskId taskId, WorkerNum acceptedWorkerNum) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update PublishedTask set aWorkerNum = ? where TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,acceptedWorkerNum.value);
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
    public boolean reviseTaskFinishedWorkerNum(TaskId taskId, WorkerNum finishedWorkerNum) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update PublishedTask set fWorkerNum = ? where TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,finishedWorkerNum.value);
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
    public boolean revisePublishedTaskState(TaskId taskId, PublishedTaskState publishedTaskState) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "update PublishedTask set State= ? where TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,publishedTaskState.toString());
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
    public List<TaskId> getPublishedTaskIdList(UserId userId) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        List<TaskId> taskIds = new ArrayList<>();

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from PublishedTask where UserId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userId.value);
            rs = stmt.executeQuery();
            while(rs.next()){
                TaskId taskId = new TaskId(rs.getString("TaskId"));
                taskIds.add(taskId);
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return taskIds;
    }

    @Override
    public List<PublishedTask> getPublishedTaskList(UserId userId) {
        List<PublishedTask> publishedTasks = new AdminDataImpl().getAllPublishedTask();
        List<PublishedTask> result = new ArrayList<>();

        for(PublishedTask p : publishedTasks){
            if(p.getUserId().value.equals(userId.value))
                result.add(p);
        }

        return result;
    }

    @Override
    public PublishedTask getPublishedTask(TaskId taskId) {
        List<PublishedTask> publishedTasks = new AdminDataImpl().getAllPublishedTask();

        PublishedTask publishedTask = null;
        for(PublishedTask p : publishedTasks){
            if(p.getTaskId().value.equals(taskId.value))
                publishedTask = p;
        }

        return publishedTask;
    }

    @Override
    public TaskType getTaskType(TaskId taskId) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        TaskType taskType = null;

        PreparedStatement stmt;
        String sql;
        ResultSet rs;

        try{
            sql = "select * from TaskType where TaskId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,taskId.value);
            rs = stmt.executeQuery();
            while(rs.next()){
                taskType = TaskType.valueOf(rs.getString("TaskType"));
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return taskType;
    }

    //@Override
    private boolean saveTaskType(TaskId taskId, TaskType taskType) {
        conn = new MySQLConnector().getConnection("PublishedTask");

        boolean result = false;

        PreparedStatement stmt;
        String sql;

        try{
            sql = "insert into TaskType values (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,taskId.value);
            stmt.setString(2,taskType.toString());
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
