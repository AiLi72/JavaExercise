package fr.database._1126_JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//定义一个方法，查询某表的数据并将其封装成对象，然后装在集合，返回
public class jdbcExercise {

    public static void main(String[] args) {
        List<emp> list = new jdbcExercise().addAll();// 要先创建对象，才能用方法
        System.out.println(list); // 已经用过了toString重写
    }

    public List<emp> addAll() { // 注意方法的定义不在main函数的大括号中
        // ⚠️ 有一下四个值需要提前声明，提高作用域
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        List<emp> list = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "123456");
            statement = connection.createStatement();
            String sql = "select * from emp";
            resultSet = statement.executeQuery(sql);
            list = new ArrayList<emp>(); // list的创建得在while之前，不能在之中也不能在之后
            while (resultSet.next()){
                emp emp = new emp(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("gender"),resultSet.getInt("salaire"),resultSet.getDate("join_date"),resultSet.getInt("dept_id"));
            list.add(emp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
