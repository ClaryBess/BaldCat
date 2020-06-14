package com.baldcat.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceTest {
    public static void main(String[] args) {
        try {
            //创建C3P0
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
            //还回到数据库连接池中
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
