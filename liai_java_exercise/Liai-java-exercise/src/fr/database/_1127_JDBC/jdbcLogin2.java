package fr.database._1127_JDBC;

import fr.database._1126_JDBC.jdbcUtils;

import java.sql.*;
import java.util.Scanner;

//Login1中存在安全问题，需要改进
public class jdbcLogin2 {

    public boolean login2 (String username, String password){
        if (username == null || password == null){  //如果直接为空的话都不用看了
            return false;
        }
        //这时要修改preparedStatement变量
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //判断用户名和密码是否在表中，也就是是否登陆成功 ⚠️ 这里没有else，不一定写了if就一定要写else的
        try {
            connection = jdbcUtils.getConnection();

            //⚠️之前就是这里出了问题，现在用？作为占位符 一定注意是英文状态下的？
            String sql = "select * from user_password where username = ? and password = ?";

            //使用prepareStatement()方法，并将sql传递进去，所以要先建立sql
            preparedStatement = connection.prepareStatement(sql);

            /**⚠️这里有一步关键的：给sql赋值
            setXxx方法：Xxx是数据类型
             */
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            //这里的executeQuery()就不需要参数了
            resultSet = preparedStatement.executeQuery();
            return resultSet.next(); //注意reseultSet.next()本身就是一个布尔值，不要写什么if，else的啰嗦写法，而且我们不需要打印resultset具体内容，只要看要后面有没有之就达到了我们的目的

        } catch (SQLException e) {
            e.printStackTrace();
        }finally { //别忘记了关闭资源，注意位置，在catch之后的finally里
            jdbcUtils.close(resultSet,connection,preparedStatement);
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
        boolean b = new jdbcLogin2().login2(username,password); //⚠️建立对象的时候jdbcLogin2后面的括号千万别丢
        System.out.println(b);
    }
}
/** 此时就解决了之前的问题
    please tape your username:
        nisigidjg
        your password
        a' or 'a' = 'a
       -->false
 */


