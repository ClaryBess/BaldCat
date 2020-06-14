package com.baldcat.repository;

import com.baldcat.entity.BlogComment;
import com.baldcat.util.JDBCTools;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogCommentRepository {

    private static ComboPooledDataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource();
    }

    public void add(int blogID, int userID, String content){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "insert into BlogComment(BlogID,UserID,Content) values(?,?,?)";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,blogID,userID,content);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    public void delete(Integer CommentID){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "delete from BlogComment where CommentID = ?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,CommentID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    public List<BlogComment> findByBlog(String BlogID){
        List<BlogComment> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from BlogComment where BlogID = ?";
            QueryRunner queryRunner = new QueryRunner();
            list = queryRunner.query(connection,sql,new BeanListHandler<>(BlogComment.class),BlogID);
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
}
