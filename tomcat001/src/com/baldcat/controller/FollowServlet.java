package com.baldcat.controller;

import com.baldcat.Handler.Handler;
import com.baldcat.entity.User;
import com.baldcat.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/follow")
public class FollowServlet extends HttpServlet {
    private static UserRepository userRepository;

    static {
        userRepository = new UserRepository();
    }

    /**
     *关注的业务逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Handler handler=new Handler();
        HttpSession session=req.getSession();
        User user=(User)session.getAttribute("user");
        User userV=(User)session.getAttribute("userV");
        handler.follow(user.getUserID(),userV.getUserID());
        User visit = userRepository.findById(userV.getUserID());
        req.setAttribute("visit",visit);
        session.setAttribute("user",userRepository.findById(user.getUserID()));
        session.setAttribute("userV",visit);
        req.getRequestDispatcher("profile_afterLogin.jsp").forward(req,resp);
    }
}
