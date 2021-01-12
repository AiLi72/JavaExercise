package fr.database._1126_JDBC;

import java.sql.*;

/** 我们发现之前每次创建连接对象和最后释放资源的代码十分重复，所以要改良：
 * 在一个jdbcUtils类中将这两个方法提取出来，每次只需要用这个工具类中的方法就可以了-->也可以用在其他解决冗余的问题上
 */

public class jdbcUtilsExercise {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcUtils.getConnection(); //用这一句代替之前的好几句，因为.getConnection是静态方法，所以可以直接使用，否则要先创建对象
            String sql = "select * from student";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {  //光标移动到下一行，如果不是最后一行则打印
                System.out.println(resultSet.getInt(1) + resultSet.getString(2) + resultSet.getInt(3));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.close(resultSet,connection,statement); // 代替之前的一大串，将我们需要释放的对象当作参数传递进去
        }
    }
}
