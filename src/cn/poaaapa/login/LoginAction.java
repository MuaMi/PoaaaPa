package cn.poaaapa.login;

import cn.poaaapa.crawler.SpiderImgs;
import cn.poaaapa.db.Pa_db;

import java.sql.*;

public class LoginAction {

    public static String Login(String phone,String pass) throws Exception{
        Connection conn = Pa_db.getConnection();
        Statement stm = conn.createStatement();
        String sql = "select user from user where user='"+phone+"'  and  password='"+pass+"'";
        ResultSet rs = stm.executeQuery(sql);

        if (rs.next()) {
            conn.close();
            return "success";
        }else{
            conn.close();
            return "sorry";
        }
    }


    //爬虫类
    public static void GetResult(String url) throws Exception{
        System.out.println(url);
     //   SpiderImgs spider = new SpiderImgs(url);
    }


    public static String regist(UserEntity user){
        String result = "failed";
        try {

            //验证用户名是否重复
            Connection conn = Pa_db.getConnection();
            String sqlSelect ="select * from User where user = ?";
            String sqlInsert = "insert into User(user,password,name,createtime) values (?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
            PreparedStatement pstmtInsert= conn.prepareStatement(sqlInsert);
            pstmt.setString(1,user.getUser());
            ResultSet rs= pstmt.executeQuery();
            if(rs.next()){
                conn.close();
                return "duplicated";
            }else {
                pstmtInsert.setString(1,user.getUser());
                pstmtInsert.setString(2,user.getPassword());
                pstmtInsert.setString(3,user.getName());
                pstmtInsert.setDate(4,user.getCreateTime()!=null?user.getCreateTime():new Date(System.currentTimeMillis()));
                pstmtInsert.execute();
                result = "success";
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getIdByName(String username){
        try {
            Connection conn = Pa_db.getConnection();
            String sql = "select id from user where user = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt("id");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static String getNameByUser(String username){
        try {
            Connection conn = Pa_db.getConnection();
            String sql = "select name from user where user = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }



}
