package com.baldcat.controller;

import com.baldcat.Handler.Handler;
import com.baldcat.entity.Blog;
import com.baldcat.entity.BlogComment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/postL")
public class PostLServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String BlogID=req.getParameter("BlogID");
        Handler handler=new Handler();
        Blog blog=handler.findById(BlogID);
        List<BlogComment> comments=handler.commentsOfBlog(BlogID);
        HttpSession session = req.getSession();
        session.setAttribute("blogP",blog);
        req.setAttribute("blogP",blog);
        session.setAttribute("comments",comments);
        req.getRequestDispatcher("blog-post_afterLogin.jsp").forward(req,resp);
    }
}
