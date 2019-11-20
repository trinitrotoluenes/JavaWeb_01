package cn.xust.service;

import cn.xust.domain.Manager;
import cn.xust.domain.PageBean;
import cn.xust.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */

public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();
    Manager login(Manager  manager);

    /**
     * 保存user
     * @param user
     */
    public void addUser(User user);

    /**
     * 记录管理员（保存数据）
     * @param id
     */
    public void addManger(Manager manager);

    public void deleteUser(String id);

    /**
     * 根据id查询
     * @param id
     * @return
     */

     public User findUserById(String id);
    /**
     * 修改个人信息
     */
    public void updateUser(User user);

    void delSelectUser(String[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */

    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
