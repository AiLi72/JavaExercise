package fr.database._1215_DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo {
    public static void main(String[] args) throws Exception {
        //导入jar包
        //定义配置文件，起名为XXX.properties
        // 加载文件(类似于jdbcUtil的用法)
        Properties pro = new Properties();
        ClassLoader classLoader = DruidDemo.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("druid.properties");
        pro.load(is);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(pro);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

    }
}
