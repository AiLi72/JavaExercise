package fr.database._1126_JDBC;
// JDBC工具类

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class jdbcUtils { //注意要在这个类里创建方法，不能直接以上来就.
    //创建静态变量，方便用到静态代码块和静态方法中
    public static String url;
    public static String user;
    public static String password;
    public static String driver;

    //我们使用一个静态代码块，使得配置文件只读一次就可以获取到我们所需要的东西
    static {
        //读取资源文件，获取值
        try {
            //1.创建Properties集合类,专门用来读取Properties文件的
            Properties properties = new Properties();
            //2.加载文件
            // properties.load(new FileReader());//注意路径：可以写绝对路径找到文件所在位置，但是太麻烦，此时我们使用ClassLoader类加载器
            // 2.1 获取src路径下的文件---->ClassLoader类加载器
            ClassLoader classLoader = jdbcUtils.class.getClassLoader();
            URL resource = classLoader.getResource("jdbc.properties");
            String path = resource.getPath();
            //加载文件
            properties.load(new FileReader(path));
            //3.获取数据，赋值
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            //4.注册驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取连接的方法,用static字段就变成了静态方法，将来方便调用
    public static Connection getConnection () throws SQLException { // 返回值是Connection对象，这样调用这个方法就可以得到数据连接之后的对象
        /**  返回值： return DriverManager.getConnection()
         * 括号里参数写什么？
         * 1. 还是写 "jdbc:mysql://localhost:3306/db1", "root", "123456"吗？那这样的话将来换数据库怎么办？❌
         * 2. 将需要的参数传递进来？ 太麻烦，和用jdbcUtils类之前无差别，调用这个方法的时候还是得写一大串地址  ❌
         * 3. 使用properties配置文件，将需要的数据加载进内存，我们再去获取所需的数据  ✅ -->不传递参数，又实现了工具类的通用性
         * ⚠️ 第三点以后经常用!
         */
        //注意下面这种用返回值的方法向外传递参数：
        return DriverManager.getConnection(url,user,password); //所以我们需要将文件加载进内存，读取其中的内容，而且只需要读一次即可拿到其中所有的值-->静态代码块
 }

    //释放资源的方法,释放资源不需要有返回值，但是这里有两种可能性：是增删改语句还是查询语句？前者需要释放connection和statement两个资源，后者需要释放resultset和connection和statement资源
    public static void close (Connection connection, Statement statement) {
        if (statement != null ){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null ){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    //释放三种资源的方法
    public static void close (ResultSet resultSet, Connection connection, Statement statement) {
        if (resultSet != null ){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null ){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null ){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
