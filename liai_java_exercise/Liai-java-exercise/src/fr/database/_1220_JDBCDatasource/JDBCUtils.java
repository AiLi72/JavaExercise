package fr.database._1220_JDBCDatasource;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    //定义成员变量
   public static DataSource ds;

   //定义静态代码块
    static {
       try {
           Properties pro = new Properties();
           pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
           //因为之前已经定义过了ds,所以下面直接用ds,而不是再重新声明一个变量
           ds = DruidDataSourceFactory.createDataSource(pro);

       } catch (IOException e) {
           e.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
    //创建连接的方法 (永远记得方法位置，别写在静态代码块里面去了)(静态方法不用创建对象就可以直接使用)
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    //释放连接的方法
    public static void close(ResultSet rs,Connection conn, PreparedStatement pst){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close(); //归还连接，不是关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pst!=null){
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //当我们需要同一个方法，但是参数个数不同的时候就这样写，或者是创建两个不同的方法，分别写
    public static void close(Connection conn, PreparedStatement pst){
        close(null,conn,pst);
    }
    //获取连接池的方法,谁调用这个方法，就返回这个数据库连接池
    public static DataSource getDataSource(){
        return ds;
    }

}
