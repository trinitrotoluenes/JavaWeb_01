package cn.xust.web.srevlet;

import cn.xust.domain.PageBean;
import cn.xust.domain.User;
import cn.xust.service.UserService;
import cn.xust.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet("/findUserByPageServlet")


public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        //req.setCharacterEncoding("utf-8");
        //获取参数
        String currentPage = req.getParameter("currentPage");
        String rows = req.getParameter("rows");


        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
           rows = "8";
        }


        //获取条件查询参数
        Map<String,String[]> condition = req.getParameterMap();

        //调用service查询
        UserService service  = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage,rows,condition);

        System.out.println(pb);
        //将PageBean 对象存入req 对象域
        req.setAttribute("pb",pb);
        req.setAttribute("condition",condition);//将查询条件存入req
        //转发到list.jsp
        req.getRequestDispatcher("/list.jsp").forward(req,resp);





    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
