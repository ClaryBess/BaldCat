package com.baldcat.repository;

import com.baldcat.entity.Follow;
import com.baldcat.util.JDBCTools;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FollowRepository {

    private static ComboPooledDataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource();
    }

    public void add(Integer FollowUserID, Integer FollowedUserID){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "insert into Follow(FollowUserID,FollowedUserID) values(?,?)";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,FollowUserID,FollowedUserID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    public void deleteById(Integer FollowID){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "delete from Follow where FollowID = ?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,FollowID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    public Follow findByUU(Integer FollowUserID, Integer FollowedUserID){
        Connection connection = null;
        Follow follow = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from Follow where FollowUserID = ? and FollowedUserID = ?";
            QueryRunner queryRunner = new QueryRunner();
            follow = queryRunner.query(connection,sql,new BeanHandler<>(Follow.class),FollowUserID,FollowedUserID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return follow;
    }

    // 查看用户关注了谁
    public List<Follow> findFollow(Integer FollowUserID){
        List<Follow> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from Follow where FollowUserID = ?";
            QueryRunner queryRunner = new QueryRunner();
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Follow.class),FollowUserID);
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

    // 查看粉丝
    public List<Follow> findFans(Integer FollowedUserID){
        List<Follow> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from Follow where FollowedUserID = ?";
            QueryRunner queryRunner = new QueryRunner();
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Follow.class),FollowedUserID);
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

    public static void main(String[] args) {
        FollowRepository followRepository = new FollowRepository();
        System.out.println(followRepository.findFollow(2));
        System.out.println(followRepository.findFans(1));
    }
}
