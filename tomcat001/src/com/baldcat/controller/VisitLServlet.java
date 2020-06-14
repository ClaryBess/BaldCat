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

@WebServlet("/visitL")
public class VisitLServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Handler handler=new Handler();
        String visitID=req.getParameter("UserID");
        User visit=handler.findByUserId(visitID);
        HttpSession session = req.getSession();
        session.setAttribute("userV",visit);
        req.setAttribute("visit",visit);
        req.getRequestDispatcher("profile_afterLogin.jsp").forward(req,resp);
    }
}
