package com.baldcat.repository;

import com.baldcat.entity.Likes;
import com.baldcat.util.JDBCTools;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class LikeRepository {

    private static ComboPooledDataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource();
    }

    /**
     * 添加点赞信息
     * @param blogID
     * @param userID
     */
    public void add(int blogID,int userID){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "insert into Likes(BlogID,UserID) values(?,?)";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,blogID,userID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    /**
     * 根据LikeID删除点赞
     * @param id
     */
    public void deleteById(Integer id){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "delete from Likes where LikeID = ?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    /**
     * 查找点赞关系
     * @param BlogID
     * @param UserID
     * @return
     */
    public Likes findLike(Integer BlogID, Integer UserID){
        Likes likes = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from Likes where BlogID = ? and UserID = ?";
            QueryRunner queryRunner = new QueryRunner();
            likes = queryRunner.query(connection,sql,new BeanHandler<>(Likes.class),BlogID,UserID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return likes;
    }
}
