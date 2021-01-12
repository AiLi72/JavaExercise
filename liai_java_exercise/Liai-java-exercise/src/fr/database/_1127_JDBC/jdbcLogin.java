package fr.database._1127_JDBC;

import fr.database._1126_JDBC.jdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**例子：
 * 1.通过键盘录入用户名和密码
 * 2.判断用户是否登陆成功
 * ----->其实就是在数据库中建一个"用户密码表"，如果输入的用户和密码在这个表中，则登陆成功
 * ⚠️这里的用户和密码千万别和登陆数据库的用户和密码搞混淆了，登陆数据库的用户密码只有数据库的管理员才有的，而某个表中的用户和密码只是存储用的
 */

public class jdbcLogin {
    //首先创建一个验证方法
    //在这里我们还是使用昨天的jdbcUtils类中的方法以简化编程
    public boolean login (String username, String password){
        if (username == null || password == null){  //如果直接为空的话都不用看了
            return false;
        }
        //⚠️ 别忘了把三个变量提出来
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        //判断用户名和密码是否在表中，也就是是否登陆成功 ⚠️ 这里没有else，不一定写了if就一定要写else的
        try {
            connection = jdbcUtils.getConnection();
            statement = connection.createStatement();
            //这是我们只需要将jdbc.properties文件中的数据库名字改一下即可，jdbcUtils类中丝毫不需要变！
            String sql = "select * from user_password where username = '"+username+"'and password = '"+password+"'"; //⚠️一定要注意这里的书写
            resultSet = statement.executeQuery(sql);
            return resultSet.next(); //注意reseultSet.next()本身就是一个布尔值，不要写什么if，else的啰嗦写法，而且我们不需要打印resultset具体内容，只要看要后面有没有之就达到了我们的目的

        } catch (SQLException e) {
            e.printStackTrace();
        }finally { //别忘记了关闭资源，注意位置，在catch之后的finally里
            jdbcUtils.close(resultSet,connection,statement);
        }
        return false; // ⚠️要考虑到如果遇到异常的情况，不满足刚才try中判断的情况，说明出错了，就直接返回false
    }

    //写一个主方法去验证一个这个方法
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("please tape your username:");
        String username = sc.nextLine();
        System.out.println("your password");
        String password = sc.nextLine();
        //使用刚才创建的方法，因为不是静态方法，所以需要创建对象：
        boolean b = new jdbcLogin().login(username, password); //⚠️建立对象的时候jdbcLogin后面的括号千万别丢
        System.out.println(b);
    }
}
/**但是这个程序有问题，下面是一种结果：
        please tape your username:
        niisn
        your password
        a' or 'a' = 'a
    ---> true
 */
