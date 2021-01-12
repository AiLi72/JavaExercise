package fr.database._1127_JDBC;

import fr.database._1126_JDBC.jdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * jdbc事务的例子：
 * aili给Adrien转账500：aili余额减少500，Adrien余额增加500
 * 如果不开启事务，当在aili余额减少之后如果出现错误，会出现只有aili减钱而Adrien没有增钱的情况
 * 为了避免这种情况发生，我们采用事务
 * 在执行sql之前开启事务
 * 当所有sql执行完后关闭事务
 * 在catch中回滚事务
 */
public class jdbcTransaction {

    // ⚠️ 要么在主方法里开始，要么定义一个方法--->无从下手
    public static void main(String[] args) {

        //无论如何第一步都是获取连接，开启事务这个方法是connection对象的方法
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        {
            try {
                connection = jdbcUtils.getConnection();
                //开启事务,参数是false表示开启事务
                connection.setAutoCommit(false);
                String sql1 = "update account set solde = solde - ? where name = ?";
                String sql2 = "update account set solde = solde + ? where name = ? ";

                preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement2 = connection.prepareStatement(sql2);
                
                //别忘了给？赋值
                preparedStatement1.setInt(1,500); //直接给出我们需要的金额，如果需要键盘输入的话，就像上一个练习一样
                preparedStatement1.setString(2,"aili");
                
                preparedStatement2.setInt(1,500);
                preparedStatement2.setString(2,"adrien");
                
                //修改用excuteupdate()
                preparedStatement1.executeUpdate();
                //故意在两个语句之间制造错误
                int a = 3 / 0; //(数学错误)
                preparedStatement2.executeUpdate();

                //关闭事务
                connection.commit();
            } //catch (SQLException e) {
            catch (Exception e) {
                //一旦有错误会在catch中抛出，所以我们事务的回滚要写在catch中，所以原本Catch中抛出的SQLException我们要改成较大的范围-->Exception

                try {
                    if (connection != null) { //但同时需要⚠️的是rollback本身也有异常，try catch之后还需要注意的一点是：要排除connection为null的可能性
                        connection.rollback();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();

                // 别忘了关闭资源！！！！
            }finally {
                jdbcUtils.close(connection,preparedStatement1);
                jdbcUtils.close(null,preparedStatement2); //这里其实代码写的并不完善，如果connection关两次会出现问题，所以我们把第二次改成null
            }
        }
    }
}
