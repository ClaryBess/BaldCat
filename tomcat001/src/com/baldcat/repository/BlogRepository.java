package com.baldcat.repository;

import com.baldcat.entity.Blog;
import com.baldcat.entity.User;
import com.baldcat.util.JDBCTools;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogRepository {

    private static ComboPooledDataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource();
    }

    /**
     * 添加博客
     * @param userID
     * @param title
     * @param content
     * @param Path
     * @param tag1
     * @param tag2
     * @param tag3
     * @param tag4
     * @param tag5
     * @return
     */
    public Blog add(int userID, String title, String content, String Path, String tag1, String tag2, String tag3, String tag4, String tag5){
        Connection connection = null;
        Blog blog = null;
        try {
            connection = dataSource.getConnection();
            String sql = "insert into Blog(UserID,Title,Content,Path,Tag1,Tag2,Tag3,Tag4,Tag5) values(?,?,?,?,?,?,?,?,?)";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,userID,title,content,Path,tag1,tag2,tag3,tag4,tag5);
            sql = "select * from Blog where BlogID >= all (select BlogID from Blog)";
            blog = queryRunner.query(connection,sql,new BeanHandler<>(Blog.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return blog;
    }

    /**
     * 添加博客评论
     * @param BlogID
     */
    public void addComment(int BlogID){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "update Blog set CommentNumber = CommentNumber + 1 where BlogID = ?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,BlogID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    /**
     * 添加赞同
     * @param BlogID
     */
    public void addLike(int BlogID){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "update Blog set LikeNumber = LikeNumber + 1 where BlogID = ?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,BlogID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    /**
     * 查找所有博客
     * @return
     */
    public List<Blog> findAll(){
        List<Blog> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from Blog";
            QueryRunner queryRunner = new QueryRunner();
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Blog.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }

    /**
     * 通过tag查找博客
     * @param tag
     * @return
     */
    public List<Blog> findByTag(String tag){
        List<Blog> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from Blog where Tag1 = ? or Tag2 = ? or Tag3 = ? or Tag4 = ? or Tag5 = ?";
            QueryRunner queryRunner = new QueryRunner();
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Blog.class),tag,tag,tag,tag,tag);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }

    /**
     * 通过内容查找博客
     * @param subContent
     * @return
     */
    public List<Blog> findByContent(String subContent){
        List<Blog> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from Blog where Content like ?";
            String str = "%"+subContent+"%";
            QueryRunner queryRunner = new QueryRunner();
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Blog.class),str);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }

    /**
     * 通过用户查找博客
     * @param UserID
     * @return
     */
    public List<Blog> findByUser(String UserID){
        List<Blog> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from Blog where UserID = ?";
            QueryRunner queryRunner = new QueryRunner();
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Blog.class),UserID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }

    /**
     * 通过blogID查找博客
     * @param BlogID
     * @return
     */
    public Blog findById(String BlogID){
        Blog blog =new Blog();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from Blog where BlogID = ?";
            QueryRunner queryRunner = new QueryRunner();
            blog = queryRunner.query(connection,sql,new BeanHandler<>(Blog.class),BlogID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return blog;
    }

    /**
     * 通过赞同数量排序
     * @return
     */
    public List<Blog> findOrderByLike(){
        List<Blog> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from Blog order by LikeNumber desc";
            QueryRunner queryRunner = new QueryRunner();
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Blog.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 推荐博客
     * @return
     */
    public Blog recommend(){
        List<Blog> list = null;
        Blog blog = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from Blog where date_sub(curdate(),interval 7 day) <= date(DateTime)";
            QueryRunner queryRunner = new QueryRunner();
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Blog.class));
            int max = 0;
            for(int i=0;i<list.size();i++){
                if(list.get(i).getLikeNumber() >= max)
                    blog = list.get(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return blog;
    }

    /**
     * 通过ID删除
     * @param id
     */
    public void deleteById(Integer id){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "delete from Blog where BlogID = ?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    /**
     * 更新资源
     * @param id
     */
    public void updateResource(Integer id){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "update Blog set Path = ? where BlogID = ?";
            String path = null;
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,path,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    public static void main(String[] args) {
        BlogRepository blogRepository = new BlogRepository();
//        blogRepository.add(1,"acdsc","xxx","http://baiduyun",null,null,null,null,null);
//        blogRepository.add(1,"c++learn","aaaa","http://12345","c++",null,null,null,null);
        System.out.println(blogRepository.findByTag("c++"));
        System.out.println(blogRepository.findByContent("x"));
    }
}
