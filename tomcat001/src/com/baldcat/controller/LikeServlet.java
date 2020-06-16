package com.baldcat.controller;

import com.baldcat.Handler.Handler;
import com.baldcat.entity.Blog;
import com.baldcat.entity.User;
import com.baldcat.repository.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {
    private static BlogRepository blogRepository;

    static {
        blogRepository = new BlogRepository();
    }

    /**
     *给文章点赞的业务逻辑
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
        Blog blog=(Blog)session.getAttribute("blogP");
        if(!handler.hasLike(blog.getBlogID(),user.getUserID()))
            handler.likeBlog(blog.getBlogID(),user.getUserID());
        session.setAttribute("blogP",blogRepository.findById(String.valueOf(blog.getBlogID())));
        req.getRequestDispatcher("blog-post_afterLogin.jsp").forward(req,resp);
    }
}
