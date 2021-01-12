package fr.database._0927_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcDemo02 {
    public static void main(String[] args) {
        Statement statement = null;
        Connection conn = null;
        // 1.导入驱动jar包 (将jar包copy到libs文件夹下，然后右键：Add As Library ) 在demo01中已经导过
        try {  //在demo年01中，我们没有解决异常，直接抛出，这里我们要用try catch finally 的语法
            // 2.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 3.获取数据库连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "123456");
            // 4.定义sql语句
            String sql = "insert into student values (3,'jenny',22,10.00,null,null)"; //注意sql语句中没有分号,注意如果前面每具体写给哪一列添加元素，后面就要写全，不知道的要用null代替
            // 5. 获取执行sql的对象 Statement
            statement = conn.createStatement();
            // 6.执行sql
            int i = statement.executeUpdate(sql); //⚠️ excuteUpdate方法用来执行DML(增删改查表中的数据)语句，返回值是影响的行数；如果是实行DML（创建，删除整个表），返回值是0
            // 7.处理结果
            if (i > 0) {
                System.out.println("ca marche");
            } else {
                System.out.println("ca marche pas");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 8.释放资源
        catch (SQLException e) {  // get catch clause
            e.printStackTrace();
        } finally {
            // statement.close();   注意错误写法❌ statement是之前定义的东西，在这个小括号（）中不适用了，所以要在开始的时候定义并且赋初值
            // conn.close();
            //赋完之后也要注意： 如果在给两个null赋新的只前，程序就已经报错了，那么后面的程序就不会执行，所以在finally中就不要释放，只有当他非空的时候才需要释放
            if (statement != null) {  //还有空指针异常
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
