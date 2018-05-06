package cn.poaaapa.login;

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


}
