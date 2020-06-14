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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private Handler handler=new Handler();
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;chartset=utf-8");
        String name=req.getParameter("username");
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        Date date= null;
        try {
            date = sdf.parse("1900-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date dateS = new java.sql.Date(date.getTime());
        switch (handler.register(name,password,email,"S",dateS,null)){
            case 1:
                req.setAttribute("message","1");
                req.getRequestDispatcher("register.jsp").forward(req,resp);
                break;
            case 2:
                req.setAttribute("message","2");
                req.getRequestDispatcher("register.jsp").forward(req,resp);
                break;
            case 3:
                req.setAttribute("message","3");
                req.getRequestDispatcher("register.jsp").forward(req,resp);
                break;
            case 0:
                User user=handler.login(name,password);
                HttpSession session=req.getSession();
                session.setAttribute("user",user);
                resp.sendRedirect("index_afterLogin.jsp");
                break;
        }
    }
}
