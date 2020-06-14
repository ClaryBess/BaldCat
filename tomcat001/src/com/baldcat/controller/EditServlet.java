package com.baldcat.controller;

import com.baldcat.Handler.Handler;
import com.baldcat.entity.User;
import com.baldcat.repository.*;

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

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static UserRepository userRepository;
    static {
        userRepository = new UserRepository();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;chartset=utf-8");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String birthday=req.getParameter("birthday");
        try {
            Date date=sdf.parse(birthday);String intro=req.getParameter("slogan");
            java.sql.Date dateS = new java.sql.Date(date.getTime());
            String gender=req.getParameter("sex");
            Handler handler=new Handler();
            HttpSession session=req.getSession();
            User user=(User) session.getAttribute("user");
            handler.chintro(user.getUserID(),intro);
            handler.chGender(user.getUserID(),gender);
            handler.chintro(user.getUserID(),intro);
            handler.chBirthday(user.getUserID(), dateS);
            session.setAttribute("user",userRepository.findById(user.getUserID()));
            resp.sendRedirect("info.jsp");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
