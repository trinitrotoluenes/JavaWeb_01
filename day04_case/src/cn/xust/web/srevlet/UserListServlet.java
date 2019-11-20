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
import java.util.List;

@WebServlet("/userListServlet")

public class UserListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

//        resp.setCharacterEncoding("utf-8");
//        resp.setContentType("text/html;charset=utf-8");
        //1.调用UserService完成查询
        UserService service = new UserServiceImpl();
        List<User> users = service.findAll();
        //2.将list存入request域
        req.setAttribute("users",users);
        //3.转发到list.jsp
      //  req.getRequestDispatcher("/list.jsp").forward(req,resp);
        resp.sendRedirect(req.getContextPath()+"/list.jsp");

}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      this.doPost(req, resp);
    }
}
