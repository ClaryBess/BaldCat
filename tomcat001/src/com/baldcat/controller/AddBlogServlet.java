package com.baldcat.controller;

import com.baldcat.Handler.Handler;
import com.baldcat.entity.Blog;
import com.baldcat.entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/addBlog")
public class AddBlogServlet extends HttpServlet {
    /**
     * 处理发布博文的业务逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;chartset=utf-8");
        req.setCharacterEncoding("utf-8");
        // 上传的用户名
        String value = null;
        // 上传的文件名
        String fileName = null;
        // 上传的目标路径
        String filePath = req.getServletContext().getRealPath("/upload");
        // 1.判断表单上传的编码方式
        if(ServletFileUpload.isMultipartContent(req)){
            // 2.创建fileItem工厂
            FileItemFactory factory = new DiskFileItemFactory();
            // 3.创建上传解析对象
            ServletFileUpload sfu = new ServletFileUpload(factory);
            // 4.解析上传的表单
            List<FileItem> fileItemList = null;
            try {
                fileItemList = sfu.parseRequest(req);
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Blog blog = new Blog();
            for(FileItem f : fileItemList){
                if(f.isFormField()){// 普通表单元素
                    String name = f.getFieldName();
                    value = f.getString("utf-8");
                    if (name.equals("title"))
                        blog.setTitle(value);
                    if (name.equals("content"))
                        blog.setContent(value);
                    if (name.equals("Tag1")){
                        blog.setTag1(value);
                    }
                }else{// 文件
                    File file1 = new File(filePath);
                    if (!file1.exists())
                        file1.mkdirs();
                    fileName = f.getName();
                    File file = new File(filePath + "/" + fileName);
                    blog.setPath(filePath + "/" + fileName);
                    try {
                        f.write(file);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

            String tag1 = blog.getTag1();
            int i =0,k=0;
            if (tag1.contains(";")){
                k = tag1.indexOf(";");
                blog.setTag1(tag1.substring(0,k));
                tag1 = tag1.substring(k+1);
            }
            if (tag1.contains(";")){
                k = tag1.indexOf(";");
                blog.setTag2(tag1.substring(0,k));
                tag1 = tag1.substring(k+1);
            }
            if (tag1.contains(";")){
                k = tag1.indexOf(";");
                blog.setTag3(tag1.substring(0,k));
                tag1 = tag1.substring(k+1);
            }
            if (tag1.contains(";")){
                k = tag1.indexOf(";");
                blog.setTag4(tag1.substring(0,k));
                tag1 = tag1.substring(k+1);
            }
            if (tag1.contains(";")){
                k = tag1.indexOf(";");
                blog.setTag5(tag1.substring(0,k));
            }
            Handler handler = new Handler();
            HttpSession session=req.getSession();
            User user=(User) session.getAttribute("user");
            Blog blog1 = handler.addBlog(user.getUserID(),blog.getTitle(),blog.getContent(),blog.getPath(),blog.getTag1(),
                    blog.getTag2(),blog.getTag3(),blog.getTag4(),blog.getTag5());
            String BlogID=Integer.toString(blog1.getBlogID());
            resp.sendRedirect("postL?BlogID="+BlogID);
        }
    }
}