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

@WebServlet("/visit")
public class VisitServlet extends HttpServlet {
    /**
     * 处理未登陆时访问用户主页的逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Handler handler=new Handler();
        String visitID=req.getParameter("UserID");
        User visit=handler.findByUserId(visitID);
        HttpSession session = req.getSession();
        session.setAttribute("userV",visit);
        req.setAttribute("visit",visit);
        req.getRequestDispatcher("profile.jsp").forward(req,resp);
    }
}
