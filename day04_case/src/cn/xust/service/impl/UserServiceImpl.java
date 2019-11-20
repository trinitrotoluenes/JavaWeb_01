package cn.xust.service.impl;

import cn.xust.dao.UserDao;
import cn.xust.dao.impl.UserDaoImpl;
import cn.xust.domain.Manager;
import cn.xust.domain.PageBean;
import cn.xust.domain.User;
import cn.xust.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    public List<User> findAll(){
        //调用Dao完成查询
        return dao.findAll();
    }

    @Override
    public Manager login(Manager manager) {
        return dao.findUserByUsernameAndPassword(manager.getUsername(),manager.getPassword());
    }



    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void addManger(Manager manager) {
        dao.register(manager);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {


        return dao.findById(Integer.parseInt(id));

    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectUser(String[] ids) {
        if(ids!=null && ids.length>1){

            for ( String id: ids
            ) {
                dao.delete(Integer.parseInt(id));
            }
        }



    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int  rows = Integer.parseInt(_rows);

        if(currentPage<=0){
            currentPage = 1;
        }

        //创建新的PageBean对象
        PageBean<User> pb = new PageBean<User>();

        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //调用dao查询总记录数
        int totalCount  = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //调用dao查询list 集合
        //计算开始的记录索引
        int start = (currentPage-1)*rows;
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);

        //计算总页码数
        int totalPage = (totalCount % rows) == 0? totalCount/rows:(totalCount/rows)+1;
        pb.setTotalPage(totalPage);


        return pb;
    }


}

