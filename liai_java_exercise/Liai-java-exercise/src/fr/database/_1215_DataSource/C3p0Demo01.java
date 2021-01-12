package fr.database._1215_DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Demo01 {
    public static void main(String[] args) throws SQLException {
        //导入jar包(从网上下载然后导入)
        //创建数据库连接池对象（选择默认default的配置行，如果不想用默认的配置行，则在参数中写名Config的名称）
        DataSource ds = new ComboPooledDataSource();
        //获取连接对象
        Connection connection = ds.getConnection();
        //打印
        System.out.println(connection);
    }
}
