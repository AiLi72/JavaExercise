package fr.database._1220_JDBCDatasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
    public static void main(String[] args) {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from user";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,conn,pst);
        }
    }
}
