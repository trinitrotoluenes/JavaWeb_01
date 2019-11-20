package cn.xust.web.srevlet;

import cn.xust.domain.User;
import cn.xust.service.UserService;
import cn.xust.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户id
        String id = req.getParameter("id");
        //调用service 进行查询
        UserService service = new UserServiceImpl();
       User user= service.findUserById(id);
       //将 user对象存入request 域
        req.setAttribute("user",user);
        //转发到update.jsp
        req.getRequestDispatcher("update.jsp").forward(req,resp);



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req, resp);
    }
}
