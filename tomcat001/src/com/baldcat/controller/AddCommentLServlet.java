package com.baldcat.controller;

import com.baldcat.Handler.Handler;
import com.baldcat.entity.Blog;
import com.baldcat.entity.BlogComment;
import com.baldcat.entity.User;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/commentL")
public class AddCommentLServlet extends HttpServlet {
    /**
     * 发表评论的业务逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;chartset=utf-8");
        String comment=req.getParameter("comment");
        Handler handler=new Handler();
        HttpSession session=req.getSession();
        User user=(User)session.getAttribute("user");
        Blog blog=(Blog) session.getAttribute("blogP");
        handler.commentBlog(blog.getBlogID(),user.getUserID(),comment);
        List<BlogComment> comments=handler.commentsOfBlog(String.valueOf(blog.getBlogID()));
        session.setAttribute("comments",comments);
        resp.sendRedirect("postL?BlogID="+blog.getBlogID());
    }
}
