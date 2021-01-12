/**
 * 如果我们每写一个小方法就去建一个main方法太麻烦了，我们可以使用Junit注解@Test用来测试方法
 * 在每个单元测试类中可以有很多测试的小方法，每一个都可以独立的去执行，执行的时候点旁边的绿色箭头就可以
 * 结果：绿色代表没有错误，红色代表有错误
 */
package fr.database._1220JDBCTemplate;

import fr.database._1220_JDBCDatasource.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class JDBCTemplateTests {
    //因为以下每一个小测试方法都要用到template，所以我们把创建template对象的语句放在所有方法的外面，作为成员变量，这样可以简化
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //一般测试方法没有返回值
    @Test
    //增加一条语句：update->执行增删改，注意参数:用？来代替所传进去的参数是为了防止sql的安全问题
    public void test1(){
        int i = template.update("insert into user(id,username) values(?,?)", 12, "max");
        System.out.println(i);
    }

    @Test
    //删除一条语句
    public void test2(){
        int i = template.update("delete from user where id = ?", 12);
        System.out.println(i);
    }

    @Test
    //将id=10的结果封装成map集合并打印
    public void test3(){
        Map<String, Object> map = template.queryForMap("select * from user where id = ?", 10);
        System.out.println(map);
        //{id=10, username=sol, password=123, sex=femme, math=15, english=18}
        // 我们发现得到的结果是一个map集合，里面的内容用KV形式表达，所以只能存储一条记录，如果0条或者多条就不可用queryForMap方法了
    }

    @Test
    //将id=9和10的结果封装成list并打
    public void test4(){
        List<Map<String, Object>> list = template.queryForList("select * from user where id = ? or id = ?", 9, 10);
        System.out.println(list);
        //[{id=9, username=rosali, password=7777, sex=femme, math=19, english=12}, {id=10, username=sol, password=123, sex=femme, math=15, english=18}]
        //得到的结果中有两条数据
    }

    @Test
    /**
     * 重点！！！常用
     * 将所有数据封装成JavaBean对象，并装到list集合中并打印
     * query方法的第一个参数是sql,第二个参数是一个接口的实现类对象RowMapper，我们既可以选择自己重写接口，也可以用它提供的接口，它提供的比较方便
     */
    public void test5(){
        List<User> querylist = template.query("select * from user", new BeanPropertyRowMapper<User>(User.class));
        //用增强for来打印的话得到的是一行一行的数据，方便查看
        //如果直接打印querylist得到的是一行很长很长的数据，不方便观看
        for (User user : querylist){
            System.out.println(user);
        }
    }

    @Test
    //查询一共有多少条记录
    // queryFOrObject一般来执行一些聚合函数,比如count
    public void test6(){
        Long aLong = template.queryForObject("select count(id) from user", Long.class);
        System.out.println(aLong); //10
    }

}
