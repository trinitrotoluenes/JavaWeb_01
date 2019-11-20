package cn.xust.web.srevlet;

import cn.xust.domain.Manager;
import cn.xust.service.UserService;
import cn.xust.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static org.springframework.beans.BeanUtils.*;

@WebServlet("/loginServlet")

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //设置编码
        req.setCharacterEncoding("utf-8");

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取数据
            //获取用户填写验证码
        String verifycode = req.getParameter("verifycode");
        //验证码校验
        HttpSession session = req.getSession();
        String checkcode_server=(String) session.getAttribute("CHECKCODE_SERVER");
        //将生成的验证码保存在字符串中后，移除session对象
        session.removeAttribute("CHECKCODE_SERVER");
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码错误
            //提示信息
            req.setAttribute("login_msg","验证码错误");
            //跳转到登录界面
          //req.getRequestDispatcher(req.getContextPath()+"/login.jsp").forward(req,resp);
       resp.sendRedirect(req.getContextPath()+"/login.jsp");

            return;
        }
        //封装user对象
        Map<String,String[]> map =req.getParameterMap();
       Manager manager= new Manager();
        try {
            org.apache.commons.beanutils.BeanUtils.populate(manager,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


        //调用service查询
        UserService service = new UserServiceImpl();
        Manager loginManager = service.login(manager);

        //判断是否登录成功
        if(loginManager != null){
            //登录成功
            //将登入用户存入session
            session.setAttribute("manager",loginManager);
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }else {
            //登录失败
            req.setAttribute("login_msg","用户名或密码错误");
            //跳转到登录界面
            req.getRequestDispatcher(req.getContextPath()+"/login.jsp").forward(req,resp);

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
