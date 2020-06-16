package com.baldcat.repository;

import com.baldcat.entity.*;
import com.baldcat.util.JDBCTools;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static ComboPooledDataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource();
    }

    /**
     * 查找所有用户
     * @return
     */
    public List<User> findAll(){
        List<User> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from User";
            QueryRunner queryRunner = new QueryRunner();
            list = queryRunner.query(connection,sql,new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }

    /**
     * 添加用户
     * @param Name
     * @param Password
     * @param Email
     * @param Gender
     * @param Birthday
     * @param Introduction
     */
    public void add(String Name,String Password,String Email,String Gender,java.sql.Date Birthday,String Introduction){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "insert into User(Name,Password,Email,Gender,Birthday,Introduction) values(?,?,?,?,?,?)";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,Name,Password,Email,Gender,Birthday,Introduction);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    /**
     * 根据UserID删除用户
     * @param id
     */
    public void deleteById(Integer id){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "delete from User where UserID = ?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    /**
     * 根据UserID查找用户
     * @param id
     * @return
     */
    public User findById(Integer id){
        User user = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from User where UserID = ?";
            QueryRunner queryRunner = new QueryRunner();
            user = queryRunner.query(connection,sql,new BeanHandler<>(User.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    /**
     * 根据邮箱查找，防止重复注册
     * @param Email
     * @return
     */
    public User findByEmail(String Email){
        User user = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from User where Email = ?";
            QueryRunner queryRunner = new QueryRunner();
            user = queryRunner.query(connection,sql,new BeanHandler<>(User.class),Email);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    /**
     * 根据用户名查找
     * @param Name
     * @return
     */
    public User findByName(String Name){
        User user = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from User where Name = ?";
            QueryRunner queryRunner = new QueryRunner();
            user = queryRunner.query(connection,sql,new BeanHandler<>(User.class),Name);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    /**
     * 查找用户名和密码都匹配的记录，用于登录
     * @param Name
     * @param Password
     * @return
     */
    public User findAsLogin(String Name,String Password){
        User user = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from User where Name = ? and Password = ?";
            QueryRunner queryRunner = new QueryRunner();
            user = queryRunner.query(connection,sql,new BeanHandler<>(User.class),Name,Password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    /**
     * 修改密码
     * @param UserID
     * @param Password
     */
    public void updatePassword(Integer UserID,String Password){
        Connection connection = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "update User set Password = ? where UserID = ?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,Password,UserID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    /**
     * 修改个人介绍
     * @param UserID
     * @param Introduction
     */
    public void updateIntroduction(Integer UserID,String Introduction){
        Connection connection = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "update User set Introduction = ? where UserID = ?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,Introduction,UserID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    /**
     * 修改粉丝数
     * @param UserID
     */
    public void updateFans(Integer UserID){
        Connection connection = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "update User set FansNumber = FansNumber + 1 where UserID = ?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,UserID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    /**
     * 修改生日
     * @param UserID
     * @param Birthday
     */
    public void updateBirthday(Integer UserID, Date Birthday){
        Connection connection = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "update User set Birthday = ? where UserID = ?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,Birthday,UserID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改性别
     * @param UserID
     * @param Gender
     */
    public void updateGender(Integer UserID,String Gender){
        Connection connection = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "update User set Gender = ? where UserID = ?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,Gender,UserID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
//        userRepository.add("xxy","000","123@qq.com","F",null,null);
        System.out.println(userRepository.findAll());
    }
}
