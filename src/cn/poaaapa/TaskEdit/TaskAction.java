package cn.poaaapa.TaskEdit;

import cn.poaaapa.db.Pa_db;
import cn.poaaapa.login.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskAction {

    public List<TaskEntity> queryAllTask (){
        try{
            Connection con= Pa_db.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Task");

            List<TaskEntity> tl = new ArrayList<TaskEntity>();
            while (rs.next()){
                TaskEntity t = new TaskEntity();
                t.setId(rs.getInt("id"));
                t.setTaskName(rs.getString("taskName"));
                t.setTaskType(rs.getInt("taskType"));
                t.setTaskState(rs.getInt("taskState"));
                t.setUrl(rs.getString("url"));
                t.setUrlType(rs.getInt("urlType"));
                t.setUserId(rs.getInt("userId"));
                t.setUrlRule(rs.getString("urlRule"));
                t.setComment(rs.getString("comment"));
                t.setCreateTime(rs.getDate("createTime"));
                t.setStartTime(rs.getDate("startTime"));
                tl.add(t);
            }
            return tl;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TaskEntity queryTask(int id){
        try{
            Connection con=Pa_db.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Task where id = "+id+";");

            List<TaskEntity> tl = new ArrayList<TaskEntity>();
            TaskEntity t = null;
            if (rs.next()){
                t = new TaskEntity();
                t.setId(rs.getInt("id"));
                t.setTaskName(rs.getString("taskName"));
                t.setTaskType(rs.getInt("taskType"));
                t.setTaskState(rs.getInt("taskState"));
                t.setUrl(rs.getString("url"));
                t.setUrlType(rs.getInt("urlType"));
                t.setComment(rs.getString("comment"));
                t.setUrlRule(rs.getString("urlRule"));
                t.setCreateTime(rs.getDate("createTime"));
                t.setStartTime(rs.getDate("startTime"));
                t.setUserId(rs.getInt("userId"));
            }
            return t;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteTask(int id){
        try {
            Connection con = Pa_db.getConnection();
            Statement stmt = con.createStatement();
            String sql = "delete from Task where id= " + id + ";";
            int r= stmt.executeUpdate(sql);
            return r>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTask(TaskEntity task ){
        try {
            Connection con = Pa_db.getConnection();
            String sql= "update Task set taskName =? ,taskType =? ,taskState =? ,url =? ,urlType =? ,comment =? ,userId =? ,createTime =?,urlRule=? where id =? ;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,task.getTaskName());
            pstmt.setInt(2,task.getTaskType());
            pstmt.setInt(3,task.getTaskState());
            pstmt.setString(4,task.getUrl());
            pstmt.setInt(5,task.getUrlType());
            pstmt.setString(6,task.getComment());
            pstmt.setInt(7,task.getUserId());
            pstmt.setString(8,task.getCreateTime().toString());
            pstmt.setString(9,task.getUrlRule());
            pstmt.setInt(10,task.getId());

            int r = pstmt.executeUpdate();
            return r>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean newTask(TaskEntity task){
        try {
            Connection conn = Pa_db.getConnection();
            String sql = "insert into task(taskName,taskType,taskState,url,urlType,comment,userId,createTime,urlRule) values (?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,task.getTaskName());
            pstmt.setInt(2,task.getTaskType());
            pstmt.setInt(3,task.getTaskState());
            pstmt.setString(4,task.getUrl());
            pstmt.setInt(5,task.getUrlType());
            pstmt.setString(6,task.getComment());
            pstmt.setInt(7,task.getUserId());
            pstmt.setDate(8,task.getCreateTime());
            pstmt.setString(9,task.getUrlRule());

            int execute = pstmt.executeUpdate();
            if(execute>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<TaskEntity> queryAll4User(int userid){
        List<TaskEntity> rl = new ArrayList<TaskEntity>();
        try {
            Connection conn = Pa_db.getConnection();
            Statement stmt =conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Task where urlType = 1 or userId ="+userid+";");
            while(rs.next()){
                TaskEntity task = new TaskEntity();
                TaskEntity t = new TaskEntity();
                t.setId(rs.getInt("id"));
                t.setTaskName(rs.getString("taskName"));
                t.setTaskType(rs.getInt("taskType"));
                t.setTaskState(rs.getInt("taskState"));
                t.setUrl(rs.getString("url"));
                t.setUrlType(rs.getInt("urlType"));
                t.setUserId(rs.getInt("userId"));
                t.setUrlRule(rs.getString("urlRule"));
                t.setComment(rs.getString("comment"));
                t.setCreateTime(rs.getDate("createTime"));
                t.setStartTime(rs.getDate("startTime"));
                rl.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rl;
    }

}
