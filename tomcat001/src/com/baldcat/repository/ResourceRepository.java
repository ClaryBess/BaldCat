package com.baldcat.repository;

import com.baldcat.entity.Resource;
import com.baldcat.entity.User;
import com.baldcat.util.JDBCTools;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResourceRepository {

    private static ComboPooledDataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource();
    }

    /**
     * 添加资源信息
     * @param UserID
     * @param Path
     */
    public void add(int UserID,String Path){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "insert into Resource(UserID,Path) values(?,?)";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,UserID,Path);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    /**
     * 根据ResourceID删除资源
     * @param id
     */
    public void deleteById(Integer id){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "delete from Resource where ResourceID = ?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    /**
     * 查找所有资源
     * @return
     */
    public List<Resource> findAll(){
        List<Resource> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from Resource";
            QueryRunner queryRunner = new QueryRunner();
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Resource.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        ResourceRepository resourceRepository = new ResourceRepository();
//        resourceRepository.add(1,"http://baiduyun");
        System.out.println(resourceRepository.findAll());
    }
}
