package com.ship.common.jdbc;

import java.sql.*;

/**
 * JDBC util
 */
public class JDBCUtil {

    protected Connection con = null;

    protected PreparedStatement ps = null;

    protected ResultSet rs = null;

    private  String url = "jdbc:mysql://localhost:3306/membershiptwo";

    private  String username = "root" ;

    private  String password = "root" ;

    static {
        try{
            Class.forName("com.mysql.jdbc.Driver") ;
        }catch(ClassNotFoundException e){
            e.printStackTrace() ;
        }
    }

    public   Connection openConnection(){
        try{
           return    DriverManager.getConnection(url,username,password);
        }catch(SQLException e){
            e.printStackTrace() ;
        }
        return null;
    }

    public void close(){
        try{
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }catch(SQLException e){
            e.printStackTrace() ;
        }
    }

}
