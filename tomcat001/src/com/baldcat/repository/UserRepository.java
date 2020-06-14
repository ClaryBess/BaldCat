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

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
//        userRepository.add("xxy","000","123@qq.com","F",null,null);
        System.out.println(userRepository.findAll());
    }
}
