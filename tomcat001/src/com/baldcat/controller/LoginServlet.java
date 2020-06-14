package com.baldcat.controller;

import com.baldcat.Handler.Handler;
import com.baldcat.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Handler handler=new Handler();
     /**
     * 处理登录的业务逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;chartset=utf-8");
        String name=req.getParameter("username");
        String password=req.getParameter("password");
        User user=handler.login(name,password);
        if(user!=null){
            HttpSession session=req.getSession();
            session.setAttribute("user",user);
            resp.sendRedirect("index_afterLogin.jsp");
        }
        else{
            req.setAttribute("message", "fail");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
