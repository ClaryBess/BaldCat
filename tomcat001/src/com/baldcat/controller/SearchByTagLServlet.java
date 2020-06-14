package com.baldcat.controller;

import com.baldcat.Handler.Handler;
import com.baldcat.entity.Blog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/searchTL")
public class SearchByTagLServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;chartset=utf-8");
        Handler handler=new Handler();
        //String str=new String(req.getParameter("search").getBytes("iso-8859-1"), "utf-8");
        String str=req.getParameter("search");
        List<Blog> blogs=handler.searchByTag(str);
        HttpSession session=req.getSession();
        //if (blogs!=null&&blogs.size()>=10){
          //  session.setAttribute("blogs",blogs.stream().limit(10).collect(Collectors.toList()));
        //}else {
            session.setAttribute("blogs",blogs);
        //}
        req.setAttribute("searchContent",str);
        resp.sendRedirect("search_afterLogin.jsp");
    }
}
