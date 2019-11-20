package cn.xust.dao.impl;

import cn.xust.dao.UserDao;
import cn.xust.domain.Manager;
import cn.xust.domain.User;
import cn.xust.util.JDBCUtils;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    public List<User> findAll() {
        //调用JDBC 实现数据库查询
        //定义sql
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public Manager findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from management where username = ? and password = ?";
            Manager manager = template.queryForObject(sql, new BeanPropertyRowMapper<Manager>(Manager.class), username, password);
            return manager;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void add(User user) {

        //1.定义sql
        String sql = "insert into user values(null,?,?,?,?,?,?)";
        //2.执行sql
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void delete(int id) {
        //1.定义sql
        String sql = "delete from user where id = ?";
        //2.执行sql
        template.update(sql, id);
    }



    @Override
    public User findById(int id) {
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id) ;

    }

    @Override
    public void register(Manager manager) {
        //定义sql
        String sql = "insert into management values(null,?,?,?)";
        //执行添加管理员sql
        template.update(sql,manager.getShowName(),manager.getUsername(),manager.getPassword());
    }

    @Override
    public void update(User user) {
        String sql = "update user set name = ?,gender = ?,age =?,address = ?,qq = ?,email = ? where id =?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //定义模板sql
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();


        for (String key:keySet
             ) {
            //排除分页参数
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%"); //?条件的值
            }
            
        }
        System.out.println(sb.toString());
        System.out.println(params);
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();


        for (String key:keySet
        ) {
            //排除分页参数
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ?");
                params.add("%"+value+"%"); //?条件的值
            }

        }

        //添加分页查找
        sb.append(" limit ?,? ");
        //添加分页查询的参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }




}



