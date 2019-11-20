package cn.xust.dao;

import cn.xust.domain.Manager;
import cn.xust.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的Dao
 */
public interface UserDao {
    public List<User> findAll();

    Manager findUserByUsernameAndPassword(String username, String password);

    void add(User user);

    void delete(int id);


    User findById(int id);

    void register(Manager manager);

    void update(User user);

    int findTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);

}
