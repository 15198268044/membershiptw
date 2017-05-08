package com.ship.common.jdbc;
import com.ship.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *   by yang on 2017/4/16.
 */
public class UserDao  extends JDBCUtil{

    public User getUser(Long userId){
          Connection con =  openConnection();
            User user = new User();
            String sql = "select id,headUrl from vipuser where id = ?";
            try{
                PreparedStatement ps  =  con.prepareStatement(sql);
                 ps.setLong(1,userId);
                ResultSet rs =  ps.executeQuery();
                while (rs.next()){
                    user.setUserId(rs.getLong("id"));
                    user.setHeadUrl(rs.getString("headUrl"));
                }
                return  user;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                close();
            }
        return  user;
    }

    public int updateHead(User user){
        try {
            Connection con =  openConnection();
            String sql = "update vipuser set headUrl = ? where id = ? ";
            PreparedStatement ps  =  con.prepareStatement(sql);
            ps.setString(1,user.getHeadUrl());
            ps.setLong(2,user.getUserId());
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close();
        }
        return  0;
    }

}

