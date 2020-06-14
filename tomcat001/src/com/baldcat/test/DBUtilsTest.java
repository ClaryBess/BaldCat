package com.baldcat.test;

import com.baldcat.entity.User;
import com.baldcat.util.JDBCTools;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBUtilsTest {

    private static ComboPooledDataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource();
    }

    public static void main(String[] args) {
        User user = findByDBUtils(1);
        System.out.println(user);
    }

    public static User findByDBUtils(Integer id){
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

    public List<User> findAll(){
        List<User> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from User";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            User user1 = null;
            while(resultSet.next()){
                Integer UserID = resultSet.getInt(1);
                String Name = resultSet.getString(2);
                String Password = resultSet.getString(3);
                String Email = resultSet.getString(4);
                String Gender = resultSet.getString(5) ;
                Date Birthday = resultSet.getDate(6);
                String Introduction = resultSet.getString(7);
                Integer FansNumber = resultSet.getInt(8);
                user1 = new User(UserID,Name,Password,Email,Gender,Birthday,Introduction,FansNumber);
                list.add(user1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return list;
    }

    public void add(String Name,String Password,String Email,String Gender,java.sql.Date Birthday,String Introduction){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            String sql = "insert into User(Name,Password,Email,Gender,Birthday,Introduction) values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,Name);
            preparedStatement.setString(2,Password);
            preparedStatement.setString(3,Email);
            preparedStatement.setString(4,Gender);
            preparedStatement.setDate(5,Birthday);
            preparedStatement.setString(6,Introduction);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }

    public void deleteById(Integer id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            String sql = "delete from User where UserID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }

    public User findById(Integer id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from User where UserID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Integer UserID = resultSet.getInt(1);
                String Name = resultSet.getString(2);
                String Password = resultSet.getString(3);
                String Email = resultSet.getString(4);
                String Gender = resultSet.getString(5) ;
                Date Birthday = resultSet.getDate(6);
                String Introduction = resultSet.getString(7);
                Integer FansNumber = resultSet.getInt(8);
                user = new User(UserID,Name,Password,Email,Gender,Birthday,Introduction,FansNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return user;
    }

    public void update(Integer UserID,String Password,String Introduction){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            String sql = "update User set Password = ?,Introduction = ? where UserID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,Password);
            preparedStatement.setString(2,Introduction);
            preparedStatement.setInt(3,UserID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }
}
