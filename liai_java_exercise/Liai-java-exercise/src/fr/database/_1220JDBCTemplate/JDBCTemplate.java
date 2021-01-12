/**
 * 以往我们用excuteQuery得到的是一个ReustlSet的结果集，但是如果想得到这个结果集中的内容很麻烦，需要创建对象，并给对象赋值
 * 现在我们用Spring框架中的JDBCTemplate对象，就会简单很多
 * 不需要申请连接，也不需要释放连接，Template对象都自己做好了
 * */

package fr.database._1220JDBCTemplate;

import fr.database._1220_JDBCDatasource.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class JDBCTemplate {
    public static void main(String[] args) {
        //导入jar包
        //创建jdbcTemplate对象（参数是一个datasource）
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        //调用方法
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from user");
        System.out.println(list);
    }
}
