package cn.xust.web.srevlet;

import cn.xust.domain.Manager;
import cn.xust.domain.User;
import cn.xust.service.UserService;
import cn.xust.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addManagerServlet")

public class AddManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        //获取参数
        Map<String,String[]> map =  req.getParameterMap();
        //封装对象
        Manager manager = new Manager();
        System.out.println(map);

        try {
            BeanUtils.populate(manager,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        //调用service 保存
        UserService service = new UserServiceImpl();
        service.addManger(manager);
//        HttpSession session = req.getSession();
//        session.setAttribute("manager",manager);
        req.getSession().setAttribute("manager",manager.getShowName());
//        req.setAttribute("manager",manager);
        //跳转到registerSuccess.jsp
         req.getRequestDispatcher("/registerSuccess.jsp").forward(req,resp);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
